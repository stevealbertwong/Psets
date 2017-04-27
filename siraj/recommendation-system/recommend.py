import numpy as np
import scipy as sp

from lightfm.datasets import fetch_movielens # import specific method from sub-modules
from lightfm import LightFM # creating model

# MovieLens: 5 star movie ratings -> csv file with 1m+ movie ratings from 100k+ users with 1.7k movies
# userId(1-1384493), movieId(1-71619), rating(1-5), timestamp
# 1, 2 ,3.5 , 1112486027
# 1, 28 ,4 , 1112486027
# fetch data and format it
data = fetch_movielens(min_rating=4.0) # only collecting movies with rating 4 or higher -> create a interaction co-existence matrix and store as a dictionary


for key, value in data.items():
    print(key, value)

# print training and testing data
print(repr(data['train']))
print(repr(data['test']))
print(data)

# WARP -> looking at existing user weighting pairs at predicting users ranking
# HYBRID RECOMENDATION -> takes into account BOTH user's past rating history(content-based) + similar users ratings(collaborative)

model = LightFM(loss='warp')
# train model
model.fit(data['train'], epochs=30)

def sample_recommendation(model, data, user_ids):
    # using shape method to get number of users and movies in training data
    n_users, n_items = data['train'].shape

    # generate recommendations for each user we input
    for user_id in user_ids:

        # THIS IS HOW YOU get known positives/title of movies for each user in compressed sparse row format
        # tocsr() + scipy.sparse -> scipy sparse matrices
        # train is a matrix with user_id as row + movie id as col + rating as its value
        # item_labels -> title of the movie
        known_positives = data['item_labels'][data['train'].tocsr()[user_id].indices]

        # generate recommendation from our model
        scores = model.predict(user_id, np.arrange(n_items))

