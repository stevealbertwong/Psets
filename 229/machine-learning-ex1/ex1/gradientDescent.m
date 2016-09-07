function [theta, J_history] = gradientDescent(X, y, theta, alpha, num_iters)


%GRADIENTDESCENT Performs gradient descent to learn theta
%   theta = GRADIENTDESENT(X, y, theta, alpha, num_iters) updates theta by 
%   taking num_iters gradient steps with learning rate alpha

% Initialize some useful values
m = length(y); % number of training examples
J_history = zeros(num_iters, 1);

% fprintf('History of Gradient descent for debug: %f', J_history);

for iter = 1:num_iters

    % ====================== YOUR CODE HERE ======================
    % Instructions: Perform a single gradient step on the parameter vector
    %               theta. 
    %
    % Hint: While debugging, it can be useful to print out the values
    %       of the cost function (computeCost) and gradient here.
    %
    theta_constant = theta(1) - alpha/m * sum(X * theta - y); %partial derivative on theta_constant
    theta_slope = theta(2) - alpha/m * sum((X * theta - y).*X(:, 2)); % partial derivative on on theta_slope
    theta = [theta_constant; theta_slope];
    
    
    % ============================================================

    % Save the cost J in every iteration    
    J_history(iter) = computeCost(X, y, theta);
    

end
% fprintf([repmat('%f\t', 1, size(J_history, 2)) '\n'], J_history');



end
