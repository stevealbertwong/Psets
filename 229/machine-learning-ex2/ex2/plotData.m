function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure 
%   PLOTDATA(x,y) plots the data points with + for the positive examples
%   and o for the negative examples. X is assumed to be a Mx2 matrix.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%




% for y = 1, plot(x, 'rx'), for y = 0, plot(y, 'co')
positiveExample = find(y==1); % this return all the rows in matrix that equals 1
plot(X(positiveExample, 1), X(positiveExample, 2),'k+', 'LineWidth',2, 'MarkerSize', 10);

negativeExample = find(y==0);
plot(X(negativeExample, 1), X(negativeExample, 2), 'ko','MarkerFaceColor', 'y', 'MarkerSize', 10);


% =========================================================================



hold off;

end
