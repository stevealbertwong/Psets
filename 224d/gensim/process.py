# process the xml format wikipedia to text format
# python process_wiki.py enwiki-latest-pages-articles.xml.bz2 wiki.en.text

import logging
import os.path
import sys

from gensim.corpora import WikiCorpus

if __name__ == '__main__':
    program = os.path.basename(sys.argv[0])
    logger = logging.getLogger(program)

    logging.basicConfig(format='%(asctime)s: %(levelname)s: %(message)s')
    logging.root.setLevel(level=logging.INFO)
    logger.info("running %s" % ' '.join(sys.argv))

    # check and process input arguments
    if len(sys.argv) < 3:
        print globals()['__doc__'] % locals()
        sys.exit(1)

    # inp = file binary version input
    # outp = text file output
    inp, outp = sys.argv[1:3]

    space = " "
    i = 0
    output = open(outp, 'w')

    # set the lemmatize to False to not use pattern, cause pattern could slow the process deeply
    # this also creates the dictionary in the corpus
    wiki = WikiCorpus(inp, lemmatize=False, dictionary={})
    for text in wiki.get_texts():
        output.write(space.join(text) + "\n")
        i = i + 1
        if (i % 10000 == 0):
            logger.info("Saved " + str(i) + " articles")

    output.close()
    logger.info("Finished Saved " + str(i) + " articles")