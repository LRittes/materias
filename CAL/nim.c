#include <stdio.h>
#include <stdlib.h>


#define MAX_MOVE 3 

int findBestMove2(int nCoins){
    printf("nCoins: %i\n", nCoins);

    int nTaken;
    int limit = (nCoins < MAX_MOVE) ? nCoins : MAX_MOVE;
    for (nTaken = 1; nTaken <= limit; nTaken++){
        if(nCoins - nTaken == 1) {
            printf("fim: %i\n", nTaken);
            return nTaken;
        }
        if(findBestMove2(nCoins - nTaken) == -1 )
        {
            printf("rec: %i\n", nTaken);
            return nTaken;
        } 
    }
    printf("fim func: -1\n");

    return -1;
}

int main(){

    findBestMove2(11);


    return 0;
}