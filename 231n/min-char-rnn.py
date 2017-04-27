"""
Minimal character-level Vanilla RNN model. Written by Andrej Karpathy (@karpathy)
BSD License
"""
import numpy as np

# data I/O
data = open('input.txt', 'r').read() # large collection of sequence of characters
chars = list(set(data))
data_size, vocab_size = len(data), len(chars)
print 'data has %d characters, %d unique.' % (data_size, vocab_size)
char_to_ix = { ch:i for i,ch in enumerate(chars) }
ix_to_char = { i:ch for i,ch in enumerate(chars) }


# hyperparameters
hidden_size = 100 # size of hidden layer of neurons
seq_length = 25 # number of steps to unroll the RNN for
learning_rate = 1e-1

# model parameters
Wxh = np.random.randn(hidden_size, vocab_size)*0.01 # input to hidden
Whh = np.random.randn(hidden_size, hidden_size)*0.01 # hidden to hidden
Why = np.random.randn(vocab_size, hidden_size)*0.01 # hidden to output
bh = np.zeros((hidden_size, 1)) # hidden bias
by = np.zeros((vocab_size, 1)) # output bias



# forward + backward pass
# forward -> compute loss
# backward -> compute param gradient
def lossFun(inputs, targets, hprev):
  """
  inputs,targets are both list of integers.
  hprev is Hx1 array of initial hidden state
  returns the loss, gradients on model parameters, and last hidden state
  """
  # dictionary { 0 : np.zeros((vocab_size,1)), 1 : np.zeros((vocab_size,1)), 2 : np.zeros((vocab_size,1))} ....
  xs, hs, ys, ps = {}, {}, {}, {}
  # transit and store previous chunk's hidden state -> hidden state is correctly propagated feeding forward
  # but only 25 times steps when backprop
  hs[-1] = np.copy(hprev) 
  loss = 0

  # forward pass
  # char_to_ix = { ch:i for i,ch in enumerate(chars) } 
  # inputs = [char_to_ix[ch] for ch in data[p:p+seq_length]]  -> a list of index
  for t in xrange(len(inputs)):
    
    # create input vector
    # xs[t] -> each word in input words
    xs[t] = np.zeros((vocab_size,1)) # encode in 1-of-k representation i.e. one hot vector    
    xs[t][inputs[t]] = 1 # one-hot ecoding

    # WTFFFFFFFFFFFFFFF IS GOING ON ????????????
    # could we lower previous word's voting ??????????
    # could we include more than one previous word's voting ????????????????
    # why are we taking the output state of previous word rather than hidden state of previous word ??????????
    # e.g. promisingly utilize -> promisingly gets the vote of other similar verb (governmental verb on resources) like utilize
    # intuition -> Whh is encoding the sequence weight/significance 
    # calculating hidden state at current timestep using previous timestep and current input
    hs[t] = np.tanh(np.dot(Wxh, xs[t]) + np.dot(Whh, hs[t-1]) + bh) 

    ys[t] = np.dot(Why, hs[t]) + by # unnormalized log probabilities for next chars
    ps[t] = np.exp(ys[t]) / np.sum(np.exp(ys[t])) # softmax - >probabilities for next chars
    loss += -np.log(ps[t][targets[t],0]) # loss -> (cross-entropy loss)






  # backward pass: compute gradients going backwards
  # d stands for gradients
  # zeros_like : Return an array of zeros with the same shape and type as a given array -> initializing weight same type same shape
  dWxh, dWhh, dWhy = np.zeros_like(Wxh), np.zeros_like(Whh), np.zeros_like(Why)
  dbh, dby = np.zeros_like(bh), np.zeros_like(by)
  dhnext = np.zeros_like(hs[0])

  # loop backward
  for t in reversed(xrange(len(inputs))):
    dy = np.copy(ps[t]) # last word in sequence -> prediction on next word

    # WTF GOING ON ???????????
    # dL/df = how much loss given function output changes
    # targets = [char_to_ix[ch] for ch in data[p+1:p+seq_length+1]]
    dy[targets[t]] -= 1 # backprop into y. see http://cs231n.github.io/neural-networks-case-study/#grad if confused here
    
    # dot product -> y x h
    # accmulating the loss
    # dy : word's feedback, hs[t] : combined theories of sequence of words
    dWhy += np.dot(dy, hs[t].T) # updating significance of each theory(hs) to each output word
    
    dby += dy

    dh = np.dot(Why.T, dy) + dhnext # backprop into h
    
    dhraw = (1 - hs[t] * hs[t]) * dh # backprop through tanh nonlinearity/activation function
    
    dbh += dhraw

    dWxh += np.dot(dhraw, xs[t].T) # updating each thoery
    dWhh += np.dot(dhraw, hs[t-1].T) # updating sequential weight on theory

    dhnext = np.dot(Whh.T, dhraw)

  for dparam in [dWxh, dWhh, dWhy, dbh, dby]:
    np.clip(dparam, -5, 5, out=dparam) # clip to mitigate exploding gradients
  return loss, dWxh, dWhh, dWhy, dbh, dby, hs[len(inputs)-1] # return gradients 






# demo
# get RNN to generate new text data 
def sample(h, seed_ix, n):
  """ 
  sample a sequence of integers from the model 
  h is memory state, seed_ix is seed letter for first time step
  """
  x = np.zeros((vocab_size, 1))
  x[seed_ix] = 1
  ixes = []
  for t in xrange(n):
    h = np.tanh(np.dot(Wxh, x) + np.dot(Whh, h) + bh)
    y = np.dot(Why, h) + by
    p = np.exp(y) / np.sum(np.exp(y))
    ix = np.random.choice(range(vocab_size), p=p.ravel())
    x = np.zeros((vocab_size, 1))
    x[ix] = 1
    ixes.append(ix)
  return ixes













n, p = 0, 0
mWxh, mWhh, mWhy = np.zeros_like(Wxh), np.zeros_like(Whh), np.zeros_like(Why)
mbh, mby = np.zeros_like(bh), np.zeros_like(by) # memory variables for Adagrad
smooth_loss = -np.log(1.0/vocab_size)*seq_length # loss at iteration 0

# Main Loop
# sampling a batch of data -> take 25 chars out of datasets
while True:
  # prepare inputs (we're sweeping from left to right in steps seq_length long)
  if p+seq_length+1 >= len(data) or n == 0: 
    hprev = np.zeros((hidden_size,1)) # reset RNN memory
    p = 0 # go from start of data

  # seq_length -> no. words you want to be relevant in predicting the next word + number of steps to unroll the RNN for
  # also because of memory limitation -> sample a batch of data
  inputs = [char_to_ix[ch] for ch in data[p:p+seq_length]] # list -> 25 chars in index
  targets = [char_to_ix[ch] for ch in data[p+1:p+seq_length+1]] # target is the next word of inputs



  # sample from the model now and then 
  # for debug -> output current predicted char at each time step based off current matrices
  if n % 100 == 0:
    sample_ix = sample(hprev, inputs[0], 200)
    txt = ''.join(ix_to_char[ix] for ix in sample_ix)
    print '----\n %s \n----' % (txt, )



  # forward seq_length characters through the net and fetch gradient
  # hprev: hidden state from previous chunk of 25 words
  loss, dWxh, dWhh, dWhy, dbh, dby, hprev = lossFun(inputs, targets, hprev)
  smooth_loss = smooth_loss * 0.999 + loss * 0.001
  if n % 100 == 0: print 'iter %d, loss: %f' % (n, smooth_loss) # print progress
  


  # perform parameter/gradients update with Adagrad 
  for param, dparam, mem in zip([Wxh, Whh, Why, bh, by], 
                                [dWxh, dWhh, dWhy, dbh, dby], 
                                [mWxh, mWhh, mWhy, mbh, mby]):
    mem += dparam * dparam # gradient squred
    param += -learning_rate * dparam / np.sqrt(mem + 1e-8) # adagrad update

  p += seq_length # move data pointer
  n += 1 # iteration counter 













  # gradient checking
from random import uniform
def gradCheck(inputs, target, hprev):
  global Wxh, Whh, Why, bh, by
  num_checks, delta = 10, 1e-5
  _, dWxh, dWhh, dWhy, dbh, dby, _ = lossFun(inputs, targets, hprev)
  for param,dparam,name in zip([Wxh, Whh, Why, bh, by], [dWxh, dWhh, dWhy, dbh, dby], ['Wxh', 'Whh', 'Why', 'bh', 'by']):
    s0 = dparam.shape
    s1 = param.shape
    assert s0 == s1, 'Error dims dont match: %s and %s.' % (`s0`, `s1`)
    print name
    for i in xrange(num_checks):
      ri = int(uniform(0,param.size))
      # evaluate cost at [x + delta] and [x - delta]
      old_val = param.flat[ri]
      param.flat[ri] = old_val + delta
      cg0, _, _, _, _, _, _ = lossFun(inputs, targets, hprev)
      param.flat[ri] = old_val - delta
      cg1, _, _, _, _, _, _ = lossFun(inputs, targets, hprev)
      param.flat[ri] = old_val # reset old value for this parameter
      # fetch both numerical and analytic gradient
      grad_analytic = dparam.flat[ri]
      grad_numerical = (cg0 - cg1) / ( 2 * delta )
      rel_error = abs(grad_analytic - grad_numerical) / abs(grad_numerical + grad_analytic)
      print '%f, %f => %e ' % (grad_numerical, grad_analytic, rel_error)
      # rel_error should be on order of 1e-7 or less