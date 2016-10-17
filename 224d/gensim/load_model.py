# execfile('load_model.py')

import logging
from gensim.models import Word2Vec
model = Word2Vec.load("/Volumes/LaCie/en_1000_no_stem/en.model")
logging.info("finished loading model")