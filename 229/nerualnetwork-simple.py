# a simple implementation of neural network in numpy
# see: https://www.youtube.com/watch?v=h3l4qz76JhQ
# see: http://iamtrask.github.io/2015/07/12/basic-python-network/

import numpy as np

# sigmoid / non-linear classifier
def sigmoid(x,derivative=False):
    if(derivative==True):
        return x*(1-x)
    return 1/(1+np.exp(-x))

# input datasets X
X = np.array([  [0,0,1],
                [0,1,1],
                [1,0,1],
                [1,1,1] ])


# weights/synapses
y = np.array([[0,0,1,1]]).T

# good practice to seed your random numbers. Your numbers will still be randomly distributed, 
# but they'll be randomly distributed in exactly the same way each time you train.
# This makes it easier to see how your changes affect the network
np.random.seed(1)

# 4x1
weight1 = 2*np.random.random((4,1)) - 1
# 4x1
weight0 = 2*np.random.random((3,4)) - 1

# training
#
# error-weighted derivative: 
# slight improvement of learning rate upadte as still 0.0x + 0.05 updates more than 0.2 or 0.8
# multiply slope/rate of change with error -> reduce the error of high confidence predictions-> as higher error contribute more to correction + 0.5 low confidence prediction contribute more
# RATE OF CHANGE: for 0.8 or 0.2 prediction -> derivatives of sigmoid x(1-x) both gives 0.16
# it updates 0.5 more heavily to make it take a position x(1-x)
# there are more mathematically precise ways than error-weighted derivatives
#
# confidence-weighted error: to establish error for previous layer
# simply sends weighted error across from layer 2 back to layer 1
# gradient -> descent
# here gradient is not very intuitive SINCE we are not minmizing gradients
# the lowest gradient value of hidden layer does not give us appropriate weight
# for error-weighted derivative -> intuition: 0.5 updates more aggressively
# for error -> no intuition as explained

for j in xrange(60000):

	# 4x3
	layer0 = X # input, each time new data input
	# 4x4
	layer1 = sigmoid(np.dot(layer0,weight0)) # hidden layer
	# 4x1
	layer2 = sigmoid(np.dot(layer1,weight1)) # output layer
	

	# backprop
	
	# 4x1
	layer2_delta = y - layer2
	
	if (j% 10000) == 0:
		print "Error:" + str(np.mean(np.abs(layer2_delta)))
	# 4x1
	layer2_error_weighted_derivative = layer2_delta*sigmoid(layer2, derivative=True) # element-wise
	# 1x1
	layer1_delta = np.dot(layer2_error_weighted_derivative, weight1.T)
	# 4x4
	layer1_error_weighted_derivative = layer1_delta*sigmoid(layer1, derivative=True) # element-wise

	# intuition-> pendunlum pic in http://iamtrask.github.io/2015/07/12/basic-python-network/
	# attribute error to each coreponding node in hidden layer -> update exact corresponding weight node
	# 4x1
	weight1 += np.dot(layer1.T, layer2_error_weighted_derivative)
	# 3x4
	weight0 += np.dot(layer0.T, layer1_error_weighted_derivative)


print "output"
print "layer1 : "  
print layer1
print "layer2 : " 
print layer2



