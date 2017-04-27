require 'torch'
require 'nn'
require 'rnn'

neuralconvo = {}

-- torch.include('neuralconvo', 'cornell_movie_dialogs.lua')

torch.include('neuralconvo', 'seq2seq.lua')