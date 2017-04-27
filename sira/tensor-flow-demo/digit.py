# Import MNIST data

# input_data class -> standard python class that downloads datasets + splits into training and testing data + formats it
import input_data
mnist = input_data.read_data_sets("/tmp/data/", one_hot=True)

import tensorflow as tf

# hyperparameters
learning_rate = 0.01 # learned decent rate for this problem
training_iteration = 30
batch_size = 100
display_step = 2

# Model is represented as data-flow graph
# Graph constains set of nodes called Operations
# Operations are units of computations
# Each operation takes in Tensor as input and outputs a Tensor
# Tensor is data/np.ndarray/multidimensional array

# TF graph input

# Placeholder operation
# Placeholder is variable we will assign data to later -> never initialize, do not contain data
# placeholder vs constant in computation graph
x = tf.placeholder("float", shape=[None, 784], name='Input') # mnist data image of shape 28*28 = 784
y = tf.placeholder("float", shape=[None, 10], name='Output') # 10 classes -> 0-9 digits recognition


# Model weights
W = tf.Variable(tf.zeros([784, 10]))
b = tf.Variable(tf.zeros([10])) # biase shift regression line to better fit the data

# name scope helps us organize nodes for graph visualization
with tf.name_scope('Wx_b') as scope:

    # FEEDFORWARD + SOFTMAX
    model = tf.nn.softmax(tf.matmul(x, W) +b)

# visualize distribution
w_h = tf.histogram_summary("weights", W)
b_h = tf.histogram_summary("biases", b)


with tf.name_scope("cost_function") as scope:

    # CROSS ENTROPY
    cost_function = tf.reduce_sum(y * tf.log(model))
    # visualize
    tf.scalar_summary('cost_function', cost_function)

with tf.name_scope('train') as scope:
    # GRADIENT DESCENT
    optimizer = tf.train.GradientDescentOptimizer(learning_rate).minimize(cost_function)


# initialize variables when finish computation graph
init = tf.initialize_all_variables()

# merge all summaries into single operator
merged_summary_op = tf.merge_all_summaries()

# Execute the computation graph
with tf.Session() as sess:
    sess.run(init)
    summary_writer = tf.train.SummaryWriter('', graph_def=sess.graph_def)

    #
    for iteration in range(training_iteration):
        avg_cost = 0
        total_batch = int(mnist.train.num_examples/batch_size)

        #
        for i in range(total_batch):
            batch_xs, batch_ys = mnist.train.next_batch(batch_size)

            # GRADIENT DESCENT
            sess.run(optimizer, feed_dict={x:batch_xs, y:batch_ys})

            # FEEDING IN x and y to compute avg cost
            avg_cost += sess.run(cost_function, feed_dict={x:batch_xs, y:batch_ys})/total_batch

            # ?????
            summary_str = sess.run(merged_summary_op, feed_dict={x:batch_xs, y:batch_ys})
            summary_writer.add_summary(summary_str, iteration*total_batch + i)

        if iteration % display_step ==0:
            print('Iteration:', '%04d' %(iteration + 1), 'cost=', '{:.9f}'.format(avg_cost))
