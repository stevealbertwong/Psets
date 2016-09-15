/*
gulp waits for a file changes -> runs test + report test results -> waits again
gulp is leading task runner and builder system

automatically runs test.js in the background
*/

var gulp = require('gulp');
var mocha = require('gulp-mocha');

gulp.task('test', function() {
  gulp.
    src('./test.js').
    pipe(mocha()). // equal to npm test
    on('error', function(err) {
      this.emit('end');
    });
});

gulp.task('watch', function() {
	
  // watches every js files and re-run test task if any changes made
  gulp.watch('./*.js', ['test']); 
});
