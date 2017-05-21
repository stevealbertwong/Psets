/*
./kmeans /Volumes/LaCie/new-wiki-model/en.model.bin
./pagerank -t graph.txt
*/

#include <stdio.h>
#include <string.h>
#include <math.h>
// #include <malloc.h>
#include <stdlib.h>

const long long max_size = 2000;         // max length of strings
const long long N = 40;                  // number of closest words that will be shown
const long long max_w = 50;              // max length of vocabulary entries

long long search_vocabs_size = 3;
// const char *search_vocabs[search_vocabs_size]; // see: http://stackoverflow.com/questions/1088622/how-do-i-create-an-array-of-strings-in-c 

int main(int argc, char **argv) {
  FILE *f, *f_output;
  char st1[max_size];
  char *bestw[N];
  char file_name[max_size], st[100][max_size], output_file_name[max_size];
  float dist, len, bestd[N], vec[search_vocabs_size][max_size];
  long long words, size, a, b, c, d, cn, bi[100];
  char ch;
  float *M;
  char *vocab;

  // TODO: read all nouns in a file => array  
  const char *search_vocabs[search_vocabs_size];
  search_vocabs[0] = "steve_jobs"; // fixed size allocation
  search_vocabs[1] = "apple";
  search_vocabs[2] = "something";

  strcpy(file_name, argv[1]);
  f = fopen(file_name, "rb");


  if (f == NULL) {
    printf("Input file not found\n");
    return -1; // exit immediately
  }

  fscanf(f, "%lld", &words); // no. words
  fscanf(f, "%lld", &size); // size of word vector
  printf("total no. of words in model %lld\n", words);
  printf("size of word vector %lld\n", size);


  vocab = (char *)malloc((long long)words * max_w * sizeof(char));
  for (a = 0; a < N; a++) bestw[a] = (char *)malloc(max_size * sizeof(char)); // comparing 40 closest words 
  M = (float *)malloc((long long)words * (long long)size * sizeof(float));

  if (M == NULL) {
    printf("Cannot allocate memory: %lld MB    %lld  %lld\n", (long long)words * size * sizeof(float) / 1048576, words, size);
    return -1;
  }

  // read words into vocab
  for (b = 0; b < words; b++) {
    a = 0;
    while (1) {
      vocab[b * max_w + a] = fgetc(f);
      if (feof(f) || (vocab[b * max_w + a] == ' ')) break;
      if ((a < max_w) && (vocab[b * max_w + a] != '\n')) a++;
    }
    vocab[b * max_w + a] = 0;
    for (a = 0; a < size; a++) fread(&M[a + b * size], sizeof(float), 1, f);
    len = 0;
    for (a = 0; a < size; a++) len += M[a + b * size] * M[a + b * size];
    len = sqrt(len);
    for (a = 0; a < size; a++) M[a + b * size] /= len;
  }
  fclose(f);




  f_output = fopen("graph.txt", "w");

  


  // TODO: loop through noun phrases array to compare

  // filtered words not in vocabulary
  // looking for the right word in vocab
  for (a = 0; a < search_vocabs_size; a++) {            
    // TODO: looking at input article's each word => find their respective word vector index b
    for (b = 0; b < words; b++) if (!strcmp(&vocab[b * max_w], search_vocabs[a])) break;
    
    // readch the end but cound not find that word
    if (b == words) b = -1;

    // YEA FOUND IT => bi stores the position of search_vocab in vocab
    bi[a] = b;
    printf("\nWord: %s  Position in vocabulary: %lld\n", search_vocabs[a], bi[a]);

    // FILTERED not found word in vocab
    if (b == -1) {
      printf("noun phrase %s not found in vocabulary \n", search_vocabs[a]);
      // break;
    }
  }
  
  // if (b == -1) continue; // if word not found => start over 


  
  // COMPUTE WORD VECTOR DISTANCE with found bi(position in vocab)    
      
  // store vector value in vec    
  for (b = 0; b < search_vocabs_size; b++) {
    if (bi[b] == -1) continue; // skip the stored word if its zero
    for (a = 0; a < size; a++) vec[b][a] = 0;
    for (a = 0; a < size; a++) vec[b][a] = M[a + bi[b] * size]; // += is confusing but seems the same as == here => FUCK I GET IT SINCE IT IS AVERAGING MULTIPLE WORD VECTORS

    // unit vector of all search words vec[word][word_vector_unit_value]
    len = 0;    
    for (a = 0; a < size; a++) len += vec[b][a] * vec[b][a]; // here += makes sense
    len = sqrt(len);
    for (a = 0; a < size; a++) vec[b][a] /= len;
  }



  // compare all words' cosine similarity => store them like "steven=>karen@@0.39"
  for (b = 0; b < search_vocabs_size; b++) {
    for (c = 1; c < search_vocabs_size; c++){
      dist = 0;
      for (a = 0; a < size; a++) dist += vec[b][a] * vec[c][a];
        // not comparing itself or 
        if (strcmp(&vocab[bi[b] * max_w], &vocab[bi[c] * max_w]) && bi[b] != -1 && bi[c] != -1){
          printf("%s and %s 's distance is %f\n", &vocab[bi[b] * max_w], &vocab[bi[c] * max_w], dist);
          // write to a file
          fprintf(f_output, "%s=>%s@@%f\n", &vocab[bi[b] * max_w], &vocab[bi[c] * max_w], dist);
        };
      

      // TODO: use data structure to store it => turn dist into an array
    }
  }




    


    // TODO: extract only noun
    // TODO: run a text rank


  
  return 0;
}