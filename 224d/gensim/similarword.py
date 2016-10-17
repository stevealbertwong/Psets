# usage
# query = "throne"
# execfile('similarword.py')

import logging
import os.path
import sys
from difflib import SequenceMatcher
from gensim.models import Word2Vec

if __name__ == '__main__':

    # logging
    # program = os.path.basename(sys.argv[0]) # program -> similarword.py
    # logger = logging.getLogger(program)

    # model = Word2Vec.load("/Volumes/LaCie/en_1000_no_stem/en.model")

    # query_word = sys.argv[1]
    array = model.most_similar(positive=[query], topn=100)

    # array = [(u'abdicated', 0.6221423745155334), (u'ascended', 0.6150252819061279), (u'king', 0.6141045093536377), (u'abdicate', 0.5801976919174194), (u'throne;', 0.5789340734481812), (u'deposed', 0.5715526342391968), (u'reign', 0.5707478523254395), (u'regent', 0.5702908635139465), (u'ruler', 0.5687152147293091), (u'throne)', 0.5618861317634583), (u'emperor', 0.5532035827636719), (u'heir-apparent', 0.5518378615379333), (u'pretender', 0.548481822013855), (u'co-regent', 0.5483530759811401), (u'reigned', 0.5446689128875732), (u'princes', 0.5381931662559509), (u'abdication', 0.5335710048675537), (u'prince', 0.5315124988555908), (u'deposing', 0.5234893560409546), (u'thrones', 0.5199713110923767), (u'vassal', 0.5194846391677856), (u'monarch', 0.5153012871742249), (u'princess', 0.5140842199325562), (u'DBPEDIA_ID/throne', 0.5137819051742554), (u'(reigned', 0.5129504799842834), (u'kingdom', 0.5116955637931824), (u'throne:', 0.5109990835189819), (u'depose', 0.5057234168052673), (u"king's", 0.5056567192077637), (u'Zhongzong)', 0.5041105151176453), (u'Thihathura', 0.5036110877990723), (u'co-ruler', 0.5016666650772095), (u'heir', 0.49866434931755066), (u'rightful', 0.4953981041908264), (u"emperor's", 0.4947551488876343), (u'kingship', 0.49465441703796387), (u'kings', 0.4937146306037903), (u'dethronement', 0.49368101358413696), (u'DBPEDIA_ID/pretender', 0.492718905210495), (u'concubine', 0.4920976161956787), (u'Katavola', 0.49205300211906433), (u'(r', 0.4903205335140228), (u'consort', 0.4893872141838074), (u'enthrone', 0.48866981267929077), (u'rulership', 0.4885309934616089), (u'Bavandid', 0.4848771095275879), (u'pretenders', 0.48470884561538696), (u'half-brother', 0.4837384521961212), (u'rebelled', 0.48290932178497314), (u'dynastic', 0.4815725088119507), (u'co-regents', 0.47958630323410034), (u'sonless', 0.4793925881385803), (u'Thihathu', 0.4793810248374939), (u'Jingsui', 0.47876274585723877), (u'DBPEDIA_ID/regent', 0.47742006182670593), (u'DBPEDIA_ID/Emperor_Jingzong_of_Tang', 0.47725993394851685), (u'usurper', 0.47681304812431335), (u'co-ruled', 0.4754467010498047), (u"Jingzong's", 0.47471821308135986), (u"I's", 0.47170424461364746), (u'Narapati', 0.47141286730766296), (u"Yizong's", 0.47063225507736206), (u'coronation', 0.47062385082244873), (u'Emperor', 0.470292866230011), (u'coregent', 0.47010987997055054), (u'courtiers', 0.47010570764541626), (u"Wuzong's", 0.4697520136833191), (u'enthroned', 0.4697386622428894), (u'DBPEDIA_ID/Emperor_Suzong_of_Tang', 0.4693540036678314), (u'Swasawke', 0.46854525804519653), (u'Ruizong)', 0.468028724193573), (u'ascension', 0.4666733145713806), (u'Shunzong', 0.46564340591430664), (u'empress', 0.4648382067680359), (u'Narathu', 0.46457159519195557), (u'Jayanegara', 0.4642893970012665), (u'DBPEDIA_ID/crown_prince', 0.4642502963542938), (u'accession', 0.46354490518569946), (u'king\u2019s', 0.46320468187332153), (u'Thadominbya', 0.46302634477615356), (u'DBPEDIA_ID/Emperor_Ai_of_Tang', 0.4626716375350952), (u'Iulon', 0.4611740708351135), (u'Taizong)', 0.4606245160102844), (u'abdicating', 0.46007686853408813), (u'Bavand', 0.45986124873161316), (u'claimant', 0.4595239758491516), (u'dowager', 0.4587804079055786), (u'Ramesuan', 0.45849093794822693), (u'nobles', 0.4573668837547302), (u'throne"', 0.456307053565979), (u'Kyawswa', 0.4560779333114624), (u'DBPEDIA_ID/Emperor_Shunzong_of_Tang', 0.4558967351913452), (u'Chingumbe', 0.45533955097198486), (u'Gyeongjong', 0.4545373022556305), (u'(reign:', 0.45402058959007263), (u'Abba-El', 0.4531608819961548), (u'coronated', 0.4518541395664215), (u'DBPEDIA_ID/Emperor_Xuan_of_Northern_Zhou', 0.4516884684562683), (u'nephew', 0.4514389634132385), (u'Vikramabahu', 0.4508833885192871)]
    for item in array:
        similar_word = item[0].encode('utf8') # get rid of u unicode
        if SequenceMatcher(None, similar_word, query).ratio() < 0.4:
            if "DBPEDIA_ID" not in similar_word:
                print similar_word + " : " + str(item[1])

