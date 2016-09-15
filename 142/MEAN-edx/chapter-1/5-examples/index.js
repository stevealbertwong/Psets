var fn = require('./myfile.js'); // file level scoping
fn();

// when call on directory nodejs look for index.js in that directory by default
var otherFn = require('./test').other; 
otherFn();