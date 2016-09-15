var mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/test');

// same code as wagner.js
var userSchema = new mongoose.Schema({
  name: String
});
var User = mongoose.model('User', userSchema);

myUserFunction(User);

function myUserFunction(User) {
  // User.create instead of user.save
  User.create({ name: 'John' }, function(error, doc) {
    console.log(require('util').inspect(doc));
  });
}
