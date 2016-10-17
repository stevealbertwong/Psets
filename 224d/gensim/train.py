import os
import sys
import logging
import multiprocessing
import bz2
import gensim

# complete waste of time way dealing with path
# alternatively-> inp, outp = sys.argv[1:3] + model = Word2Vec(LineSentence(inp), size=400, window=5, min_count=5, workers=multiprocessing.cpu_count())
# SCRIPT_PATH = os.path.dirname(os.path.realpath(__file__))
# DATA_PATH   = os.path.join(SCRIPT_PATH, 'data/')
# MODEL_PATH  = os.path.join(SCRIPT_PATH, 'model/')
EXTERNAL_HARD_DISK_PATH = "/Volumes/LaCie/"

# DICTIONARY_FILEPATH = os.path.join(DATA_PATH, 'wiki-english_wordids.txt.bz2')
# WIKI_DUMP_FILEPATH = os.path.join(DATA_PATH, 'enwiki-latest-pages-articles.xml.bz2')

DICTIONARY_FILEPATH = os.path.join(EXTERNAL_HARD_DISK_PATH, 'wiki-english_wordids.txt.bz2')
WIKI_DUMP_FILEPATH = os.path.join(EXTERNAL_HARD_DISK_PATH, 'enwiki-latest-pages-articles.xml.bz2')




if __name__ == '__main__':

    # Check if the required files have been downloaded
    if not WIKI_DUMP_FILEPATH:
        print('Wikipedia articles dump could not be found..')
        sys.exit()

    # Get number of available cpus
    cores = multiprocessing.cpu_count()

    # Create model directory
    # if not os.path.exists(MODEL_PATH):
    #     os.makedirs(MODEL_PATH)

    # Initialize logging
    logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s', level=logging.INFO)



    # Create Dictionary
    # Dictionary is all tokens vector
    # Dictionary is constructed when contructing corpus
    # https://radimrehurek.com/gensim/tut1.html
    if not os.path.isfile(DICTIONARY_FILEPATH):
        logging.info('Dictionary has not been created yet..')
        logging.info('Creating dictionary (takes about 9h)..')

        # Construct corpus using binary
        wiki = gensim.corpora.WikiCorpus(WIKI_DUMP_FILEPATH)
        logging.info('contructed corpus using binary')

        # Remove words occuring less than 20 times, and words occuring in more
        # than 10% of the documents. (keep_n is the vocabulary size)
        wiki.dictionary.filter_extremes(no_below=20, no_above=0.1, keep_n=100000)

        # Save dictionary to file
        wiki.dictionary.save_as_text(DICTIONARY_FILEPATH)
        del wiki

    # Load dictionary from file
    dictionary = gensim.corpora.Dictionary.load_from_text(DICTIONARY_FILEPATH)

    # Construct corpus using dictionary
    wiki = gensim.corpora.WikiCorpus(WIKI_DUMP_FILEPATH, dictionary=dictionary)


    class SentencesIterator:
        def __init__(self, wiki):
            self.wiki = wiki

        def __iter__(self):
            for sentence in self.wiki.get_texts():
                yield list(map(lambda x: x.decode('utf-8'), sentence))

    # Initialize simple sentence iterator required for the Word2Vec model
    sentences = SentencesIterator(wiki)

    logging.info('Training word2vec model..')



    # train the model
    # size is dimentionality of feature vector
    model = gensim.models.Word2Vec(sentences=sentences, size=300, min_count=1, window=5, workers=cores)

    # Save model
    logging.info('Saving model..')

    # trim unneeded model memory = use (much) less RAM
    model.init_sims(replace=True)
    model.save(os.path.join(EXTERNAL_HARD_DISK_PATH, 'word2vec.model'))
    # model.save(os.path.join(MODEL_PATH, 'word2vec.model'))
    logging.info('Done training word2vec model!')