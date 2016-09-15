/*
Same steps as mongodb -> only difference is there is schema
schema is like Class -> initialize object using own value

1. connect to uri 
2. create mongoose model using schema + initialize model
3. insert into db -> user.save
4. query -> User.find()
*/

// mapping object into document
var mongoose = require('mongoose');

// module.exports = new mongoose.Schema
var schema = require('./schema'); 


// connection: object representing sockets connected to Mongodb server
mongoose.connect('mongodb://localhost:27017/test');

// Parameters are: model name, schema, collection name
// ODM: collection + schema -> model -> docuemnt (instantiation of model)
var User = mongoose.model('User', schema, 'users'); // User: model
var user = new User({ // user: document
  name: 'John Smith',
  email: 'john@smith.io'
});



// save() persist user document to mongodb
user.save(function(error) {
  if (error) {
    console.log(error);
    process.exit(1);
  }
  User.find({ email: 'john@smith.io' }, function(error, docs) {
    if (error) {
      console.log(error);
      process.exit(1);
    }
    console.log(require('util').inspect(docs));
    process.exit(0);
  });
});