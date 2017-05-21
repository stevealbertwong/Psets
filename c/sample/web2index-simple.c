/*
output <M> <term_1>:<count> <term_2>:<count> ... <term_N>:<count>

usage: 
./web2index-simple web-query.txt original-train.txt save-file.txt index-reference.txt
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <pthread.h>

#define MAX_STRING 100
const int vocab_hash_size = 30000000;  // Maximum 30 * 0.7 = 21M words in the vocabulary

struct vocab_word {
  long long cn;  
  char *word;
};
struct vocab_word *vocab;

// int *vocab_hash;
long long *vocab_hash;
long long vocab_max_size = 1000, vocab_size = 0;


// char const* const train_file, save_file;

char train_file[MAX_STRING], content_file[MAX_STRING]; 
char save_file[MAX_STRING], index2vocab_file[MAX_STRING];



// Reads a single word from a file into a string word[a], assuming space + tab + EOL to be word boundaries
void ReadWord(char *word, FILE *fin) {  
  int a = 0, ch;
  
  // read one word
  while (!feof(fin)) {
  	
    ch = fgetc(fin);
    if (ch == 13) continue; // \r == 13, \n=10 => i.e. ignore

    if ((ch == ' ') || (ch == '\t') || (ch == '\n')) {
      break;
    }

    word[a] = ch;
    a++;
    if (a >= MAX_STRING - 1) a--;   // Truncate too long words
  }
  word[a] = 0;
}


// Reads a single word from a file into a string word[a], assuming space + tab + EOL to be word boundaries
void ReadContentWord(char *word, FILE *fin) {  
  int a = 0, ch;
  
  // read one word
  while (!feof(fin)) {
    
    ch = fgetc(fin);
    printf("%c\n", ch);
    if (ch == 13) continue; // \r == 13, \n=10 => i.e. ignore
    
    if ((ch == ' ') || (ch == '\t') || (ch == '\n')) {
      
      // ' ' or '\t' or '\n' will break => if '\n' also ungetc + a set to 0 and bypass this statement
      if (a > 0) {
        if (ch == '\n') ungetc(ch, fin); // put back \n => break => jumps back to outer loop if(ch=='\n')
        printf("I am going to break\n");
        break;
      }
      
      // if first char is \n
      printf("I finally appear\n");
      if (ch == '\n') {       
        // strcpy(word, (char *)"</s>");
        long long i;
        long long ii;
        long long c =0;
        FILE *fo = fopen(save_file, "a");
        
        for (ii = 0; ii < vocab_size; ii++) {
         if(vocab[ii].cn != 0){
          c++;
         }  
        }
        if (c == 0) continue;
        
        else {

          fprintf(fo, "%lld ", c);

          for (i = 0; i < vocab_size-1; i++) {
            if(vocab[i].cn != 0){
              fprintf(fo, "%lld:%lld ", i, vocab[i].cn);
            }
          }
          fprintf(fo, "\n");      
          fclose(fo);



          // destroy count => so next line could have fresh count
          int aa;
          for (aa = 0; aa <=vocab_size; aa++) {
            vocab[aa].cn = 0;
          }

          // clear the word
          strcpy(word, (char *)"</s>");
        }

        return;
      } else continue;
    }

    word[a] = ch;
    a++;
    if (a >= MAX_STRING - 1) a--;   // Truncate too long words
  }


  word[a] = 0;
}



// Returns hash value of a word
int GetWordHash(char *word) {
  unsigned long long a, hash = 0;
  for (a = 0; a < strlen(word); a++) hash = hash * 257 + word[a]; // TODO: why gurantee no collision?
  // printf("hash before amb is %llu\n", hash);
  hash = hash % vocab_hash_size;
  // printf("hash after amb is %llu\n", hash);
  return hash;
}

/* Reads a word and returns its index in the vocabulary */
// Returns position of a word in the vocabulary; if the word is not found, returns -1
int SearchVocab(char *word) {
  unsigned int hash = GetWordHash(word);
  while (1) {  	
    if (vocab_hash[hash] == 0) return -1;
    if (!strcmp(word, "</s>"))	return 0;
    if (!strcmp(word, vocab[vocab_hash[hash]].word)) return vocab_hash[hash]; // strcmp returns 0 if matches    
    hash = (hash + 1) % vocab_hash_size;    
  }
  return -1;
}

// Adds a word to the vocabulary => return the index/sequence it is stored
// vocab is arranged by sequence => vocab's sequence is then hashed into corresponding vocab_hash 
int AddWordToVocab(char *word) {
  
  unsigned int hash, length = strlen(word) + 1;
  if (length > MAX_STRING) length = MAX_STRING;

  vocab[vocab_size].word = (char *)calloc(length, sizeof(char));
  strcpy(vocab[vocab_size].word, word);  
  
  vocab[vocab_size].cn = 0;

  // Reallocate memory if needed
  vocab_size++;  
  if (vocab_size + 2 >= vocab_max_size) {
    vocab_max_size += 1000;
    vocab = (struct vocab_word *)realloc(vocab, vocab_max_size * sizeof(struct vocab_word));
  }

  // storing sequence in hash
  hash = GetWordHash(word);
  while (vocab_hash[hash] != 0) hash = (hash + 1) % vocab_hash_size;

  // printf("new hash index for vocab_hash is %u\n", hash);
  // printf("added word %lld\n", vocab_size);
  vocab_hash[hash] = vocab_size - 1; // vocab's hash position in vocab
  return vocab_size - 1;
}



void ReadFile(){
	
	char word[MAX_STRING];
	FILE *fin;
	fin = fopen(train_file, "rb");
	long long a, i;
	
	AddWordToVocab("</s>"); // my own addition / invented word to fix bug
	// counts of vocab
	while(1){
		
		ReadWord(word, fin); // copying one word from the file at a time						
    if (feof(fin)) break; // one loops through io until it fails => then check indicator foef is set								
		else {
			// i => word's index in vocab
			// a => a new word's fresh index in vocab
			i = SearchVocab(word); 			
			// if word is a new vocab		
			if (i == -1) {
	      a = AddWordToVocab(word);	      
	    } 

      // strcpy(word, (char *)' ');
    	
    }
	}
}


void ReadContentFile(){
  
  char word[MAX_STRING];
  FILE *fin;
  fin = fopen(content_file, "rb");
  long long i;
    
  while(1){
    
    ReadContentWord(word, fin); // copying one word from the file at a time        
    

    if (feof(fin)) break; // one loops through io until it fails => then check indicator foef is set            
    if (!strcmp(word, "</s>")) continue;


    else {
      // i => word's index in vocab
      // a => a new word's fresh index in vocab
      i = SearchVocab(word); 
      
      // if word is a new vocab
      if (i == -1) {
        // a = AddWordToVocab(word);
        // vocab[a].cn = 1;
        printf("%s does not exist in existing dictionary ! \n", word);
      } else if (i == 0){ // if found </s>
          continue;
      } else vocab[i].cn++;
      
    }
  }
}

void DestroyCount() {
  int aaa;
  for (aaa = 0; aaa <=vocab_size; aaa++) {
    vocab[aaa].cn = 0;    
  }

}

void Index2Vocab(){
	
	long long i;			
	FILE *fvocab = fopen(index2vocab_file, "a");
	for (i = 0; i < vocab_size; i++) {		
		fprintf(fvocab, "%lld:%s\n", i, vocab[i].word);		
	}
}

int main(int argc, char const *argv[])
{
	
	// train_file = 'testing.txt';
	// save_file = 'testing-output.txt';
	strcpy(content_file, argv[1]); // 10th word => vocab for self checking index file
  strcpy(train_file, argv[2]); // original input article
	strcpy(save_file, argv[3]); // save file	
  strcpy(index2vocab_file, argv[4]); // 10th word => vocab for self checking index file
	
	vocab = (struct vocab_word *)calloc(vocab_max_size, sizeof(struct vocab_word));
	vocab_hash = (long long *)calloc(vocab_hash_size, sizeof(long long));

	// build vocab.word + vocab.cn
	// after training save word, count per document on one line
	ReadFile();	
  
  DestroyCount();
  
  ReadContentFile();
  
	Index2Vocab();
	
	free(vocab_hash);
	free(vocab);

	

	return 0;
}