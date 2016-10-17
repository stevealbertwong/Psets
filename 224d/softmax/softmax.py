import numpy as np
import random

def softmax(x):
    tmp = np.max(x)
    x -= tmp
    x = np.exp(x)
    sum = np.sum(x)
    x = x/sum
    print x

softmax(np.array([3,4,4,2,1]))


# def test_softmax():
# if __name__ == "__main__":
#     test_softmax()