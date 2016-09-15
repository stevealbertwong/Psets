/*
Mocha uses behavior driven development
i.e. decribe and it function

command: ./node_modules/.bin/mocha

./node_modules/.bin/mocha -g "fail" test.js 
./node_modules/.bin/mocha -R dot test.js
./node_modules/.bin/mocha -R xunit test.js
./node_modules/.bin/mocha -R nyan test.js

*/

var assert = require('assert');



describe('my feature', function() {
  // it -> shows tick
  it('works', function() {
    assert.equal('A', 'A');
  });

  it('fails gracefully', function() {
    assert.throws(function() {
      throw 'Error!';
    });
  });
});



describe('my other feature', function() {
  it('async', function(done) {
    setTimeout(function() {
      done();
    }, 25);
  });
});