# see: https://radimrehurek.com/gensim/tut1.html
# from string to vector

import logging
from gensim import corpora, models, similarities

logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s', level=logging.INFO)

# 1 tiny corpus, 9 documents each 1 sentence
documents = ["Human machine interface for lab abc computer applications",
             "A survey of user opinion of computer system response time",
             "The EPS user interface management system",
             "System and human system engineering testing of EPS",
             "Relation of user perceived response time to error measurement",
             "The generation of random binary unordered trees",
             "The intersection graph of paths in trees",
             "Graph minors IV Widths of trees and well quasi ordering",
             "Graph minors A survey"]


# tokenize document
# remove common words
commonword = set('for a of the and to in'.split())
commonword2 = 'for a of the and to in'.split()


texts = [[word for word in document.lower().split() if word not in commonword]
         for document in documents]

texts2 = [[word for word in document.lower().split() if word not in commonword2]
         for document in documents]

from collections import defaultdict
frequency = defaultdict(int)

for text in texts:
    for token in text:
        frequency[token] += 1
print frequency
print texts

# remove words that only appears once
texts = [[token for token in text if frequency[token] > 1]
         for text in texts]
print texts