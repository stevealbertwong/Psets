function g = sigmoid(z)
%SIGMOID Compute sigmoid functoon
%   J = SIGMOID(z) computes the sigmoid of z.

% period represents element wise operation
g = 1.0 ./ (1.0 + exp(-z));
end
