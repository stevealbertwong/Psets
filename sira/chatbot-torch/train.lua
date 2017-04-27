--[[
see: https://github.com/llSourcell/Chatbot-AI

usage: th train.lua --cuda --dataset 50000 --hiddenSize 1000
th ~/Desktop/Psets/sira/chatbot-torch/train.lua -gpu -1

For OpenCL, use --opencl instead of --cuda. To train on CPU, 
don't provide any of those two. Use the --dataset NUMBER option to 
control the size of the dataset. Training on the full dataset takes 
about 5h for a single epoch. The model will be saved to data/model.t7 
after each epoch if it has improved (error decreased).

Took 3 days to train on my GeForce GTX 780M

--]]



require 'neuralconvo' -- No LuaRocks module found for neuralconvo
require 'xlua'

-- checking and parsing arguments
cmd = torch.CmdLine()
cmd:text('Options:')
cmd:option('--dataset', 0, 'approximate size of dataset to use (0 = all)')

cmd:text()
options = cmd:parse(arg)


-- Load Dataset
dataset = neuralconvo.DataSet(neuralconvo.CornellMovieDialogs("data/cornell_movie_dialogs"), {
	loadFirst = options.dataset,
	minWordFreq = options.minWordFreq
	})


-- Load the Model


