import gensim
model = gensim.models.Word2Vec.load("stanford.word2vec.model")
query = model.most_similar("happy")

print query