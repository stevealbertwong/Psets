"""
https://www.youtube.com/watch?v=cdLUzrjnlr4
"""

import copy, numpy as np


def sigmoid(x):
    output = 1/(1+np.exp(-x))
    return  output

# derivative is slope of sigmoid at given point -> calculate error
def sigmoid_output_to_derivative(output):
    return output*(1-output)

# training dataset generation
# look up table map integers to binary representation -> i.e. 11011001 -> 217
int2binary = {}
binary_dim = 8 # max length of binary numbers

largest_number = pow(2, binary_dim) # 2^8 pow = power
binary = np.unpackbits(np.array([range(largest_number)], dtype=np.uint8).T, axis=1) # literally 8 bits binary table
for i in range(largest_number):
    int2binary[i] = binary[i]
# print(int2binary)

# dimentionality
alpha = 0.1
input_dim = 2 # input 2 numbers
hidden_dim = 16
output_dim = 1 # output 1 number

# weights
synapse_0 = 2*np.random.random((input_dim, hidden_dim)) -1 # connect input layer to hidden layer <2, 16>
synapse_1 = 2*np.random.random((hidden_dim, output_dim)) -1 # <16, 1>

# connect hidden layer in previous timestep to hidden layer in current timestep + current timestep to next timestep
synapse_h = 2*np.random.random((hidden_dim, hidden_dim)) -1

# store synapse update before updating the matrices
synapse_0_update = np.zeros_like(synapse_0)
synapse_1_update = np.zeros_like(synapse_1)
synapse_h_update = np.zeros_like(synapse_h)

# 10000 -> epochs/training samples
for j in range(10000):

    # input -> a_bin, b_bin
    a_int = np.random.randint(largest_number/2)
    a_bin = int2binary[a_int]
    b_int = np.random.randint(largest_number/2)
    b_bin = int2binary[b_int]

    # output -> c_bin
    c_int = a_int + b_int
    c_bin = int2binary[c_int]

    # store nn prediction in empty binary array
    d = np.zeros_like(c_bin)

    overallError = 0

    # MEMORY PART OF NEURAL NETWORK
    # store previous time step hidden layer -> SEQUENTIAL MEMORY
    layer_2_deltas = list() # derivatives
    layer_1_values = list() #
    layer_1_values.append(np.zeros(hidden_dim)) # since layer 1 does not have previous layer

    # FORWARD PROPAGATION
    # moving along positions in binary encoding i.e. 8
    for position in range(binary_dim):
        # generate input and output
        X = np.array([[a_bin[binary_dim - position -1 ], b_bin[binary_dim - position -1]]])
        y = np.array([[c_bin[binary_dim - position - 1]]]).T

        # FEED FORWARD
        # l1 = Wx * x + Wh * Prev_hid_lay
        layer_1 = sigmoid(np.dot(X, synapse_0)) + np.dot(layer_1_values[-1], synapse_h) # layer_1-> <1, 16>
        # OUTPUT
        # y = Wy * l1 ->
        layer_2 = sigmoid(np.dot(layer_1, synapse_1)) # layer_2 -> <1, 1>

        # LOSS
        layer_2_error = y - layer_2
        # GRADIENTS OF OUTPUT * LOSS -> store it in a list for updates
        layer_2_deltas.append((layer_2_error)*sigmoid_output_to_derivative(layer_2)) # on sigmoid graph, if +ve error, go -ve/downhil, if -ve error, go +ve/uphill, using error as step size/magtitute
        overallError += np.abs(layer_2_error[0]) # |error| so it is +ve -> track propagation

        # store in d -> decode estimate so we could print it out
        d[binary_dim - position - 1] = np.round(layer_2[0][0])

        # STORE HIDDEN LAYER FOR NEXT TIMESTEP
        layer_1_values.append(copy.deepcopy(layer_1))

    future_layer_1_delta = np.zeros(hidden_dim)

    # BACKPROPAGATE
    for position in range(binary_dim):
        X = np.array([[a_bin[position], b_bin[position]]]) # select input
        layer_1 = layer_1_values[-position-1] # select current layer
        prev_layer_1 = layer_1_values[-position-2] # select previous layer

        layer_2_delta = layer_2_deltas[-position-1] # select output layer error
        # select hidden layer error
        # CHAIN RULE -> backpropagate
        # ?????????? why add future_layer_1_delta.dot(synapse_h.T)
        # add future layer_1_delta
        layer_1_delta = (future_layer_1_delta.dot(synapse_h.T)+layer_2_delta.dot(synapse_1.T))*sigmoid_output_to_derivative(layer_1)

        # STORING WEIGHTS UPDATES
        # ??????????? why layer_1.T.dot(layer_2_delta) -> assigning responsibilities to mistakes
        # ???????????? how to update synapse h
        synapse_1_update += np.atleast_2d(layer_1).T.dot(layer_2_delta)
        synapse_h_update += np.atleast_2d(prev_layer_1).T.dot(layer_1_delta)
        synapse_0 += X.T.dot(layer_1_delta)

        future_layer_1_delta = layer_1_delta

    # ACTUALLY UPDATES THE WEIGHT
    synapse_0 += synapse_0_update * alpha
    synapse_1 += synapse_1_update * alpha
    synapse_h += synapse_h_update * alpha

    synapse_0_update *= 0
    synapse_h_update *= 0
    synapse_1_update *= 0

    # printing out results
    if(j % 1000 == 0):
        print("Err", str(overallError))
        print("Pred", str(d))
        print("True", str(c_bin))
        out = 0
        for index,x in enumerate(reversed(d)):
            out += x*pow(2, index)
            print(str(a_int), "+", str(b_int), " = ", str(out))
            print "--------------------"