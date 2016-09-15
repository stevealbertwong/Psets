var mongodb = require('mongodb'); // mongodb nodejs driver i.e. let you write nodejs code to talk to mongodb

// mongodb collection string, tells the driver which mongod to connect to 
// i.e. connect to mongod running on localhost:27017 example database 
var uri = 'mongodb://localhost:27017/example'; 

/*
Art of callback:
1. wait MongoClient to connect to uri -> return db handle
2. creates doc to insert
3. db.collection.insert -> return result
4. wait til insert succeed, db.collection.find() -> return doc
*/


mongodb.MongoClient.connect(uri, function(error, db) {
  if (error) {
    console.log(error);
    process.exit(1);
  }

  var doc = {
    title: 'jaw',
    year:1975,
    rating: 'PG',
    ratings:{
      critics:80,
      audience:90
    },
    screenplay:['steven wong ', 'andi sullivan']

    }
  
  

  db.collection('sample').insert(doc, function(error, result) {
    if (error) {
      console.log(error);
      process.exit(1);
    }

    // db.collection('sample').createIndex('title':1); // unexpected token : error

    var query = {year:1975};
    var query2 = {'ratings.audience' : {'$gte':90}}; // 90 or greater

    // find() return a cursor rather than a set of documents
    // cursor is an object you could call next on to get next document
    // toArray exhaust the cursor for you and return an array of documents
    db.collection('sample').find(query).toArray(function(error, docs) {
      if (error) {
        console.log(error);
        process.exit(1);
      }

      console.log('Found docs:');
      docs.forEach(function(doc) {
        console.log(JSON.stringify(doc));
      });
      process.exit(0);
    });
  });
});