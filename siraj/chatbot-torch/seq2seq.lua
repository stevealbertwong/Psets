-- Based on https://github.com/Element-Research/rnn/blob/master/examples/encoder-decoder-coupling.lua

-- create Seq2Seq class
local Seq2Seq = torch.class("neuralconvo.Seq2Seq")

-- initialization
function Seq2Seq:__init(vocabSize, hiddenSize) -- dataset.wordsCount, options.hiddenSize
	self.vocabSize = assert(vocabSize, "vocabSize required at arg #1")
	self.hiddenSize = assert(hiddenSize, "hiddenSize required at arg #2")

	-- call buildModel()
	self:buildModel()
end

-- more like setting config
function Seq2Seq:buildModel()
	self.encoder = nn.Sequential()
	-- WHAT LOOKUPTABLE FOR???
	self.encoder.add(nn.LookupTable(self.vocabSize, self.hiddenSize))
	-- WHY SPLIT TABLE ????
	self.encoder.add(nn.SplitTable(1, 2))
	
	self.encoderLSTM = nn.LSTM(self.hiddenSize, self.hiddenSize)
	--???
	self.encoder.add(nn.Sequencer(self.encoderLSTM))
	-- ????
	self.encoder:add(nn.SelectTable(-1))



	self.decoder = nn.Sequential()
		-- WHAT LOOKUPTABLE FOR???
	self.decoder.add(nn.LookupTable(self.vocabSize, self.hiddenSize))
	-- WHY SPLIT TABLE ????
	self.decoder.add(nn.SplitTable(1, 2))

	self.decoderLSTM = nn.LSTM(self.hiddenSize, self.hiddenSize)

	self.decoder.add(nn.Sequencer(self.decoderLSTM))

	-- WHY LINEAR ????
	self.decoder:add(nn.Sequencer(nn.Linear(self.hiddenSize, self.vocabSize)))
	-- SOFTMAX ?????
	self.decoder:add(nn.Sequencer(nn.LogSoftMax()))
	

	self.encoder:zeroGradParameters()
  	self.decoder:zeroGradParameters()





end

function Seq2Seq:cuda()
	self.encoder:cuda()
end


function Seq2Seq:cl()
	self.encoder:cl()
end


-- forward coupling -> copy encoder cell and output to decoder LSTM
function Seq2Seq:forwardConnect(inputSeqLen)
	self.decoderLSTM.userPrevOutput
end

-- backward coupling -> copy decoder gradients to encoder LSTM





