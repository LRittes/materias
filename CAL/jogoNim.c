#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_MOVE 3

// Função para encontrar a melhor jogada para o computador
int findBestMove(int nCoins) {
    int nTaken;
    int limit = (nCoins < MAX_MOVE) ? nCoins : MAX_MOVE;
    for (nTaken = 1; nTaken <= limit; nTaken++) {
        if (nCoins - nTaken == 1) {
            return nTaken;
        }
        if (findBestMove(nCoins - nTaken) == -1) {
            return nTaken;
        }
    }
    return -1;
}

int main() {
    int nCoins = 0;
    srand(time(NULL)); // Inicializa a semente do gerador de números aleatórios

    printf("Bem-vindo ao jogo de retirar moedas!\n");
    printf("Digite o número inicial de moedas no monte: ");
    scanf("%d", &nCoins);

    int currentPlayer = 1; // 1 para o jogador, 2 para o computador

    while (nCoins > 0) {
        printf("Moedas no monte: %d\n", nCoins);

        if (currentPlayer == 1) {
            // Vez do jogador
            int playerMove;
            printf("Jogador, quantas moedas deseja retirar (1 a %d)? ", MAX_MOVE);
            scanf("%d", &playerMove);

            if (playerMove < 1 || playerMove > MAX_MOVE || playerMove > nCoins) {
                printf("Jogada inválida. Escolha um número válido de moedas para retirar.\n");
                continue;
            }

            nCoins -= playerMove;
        } else {
            // Vez do computador
            int computerMove = findBestMove(nCoins);
            printf("O computador retirou %d moeda(s).\n", computerMove);
            nCoins -= computerMove;
        }

        // Alternar o jogador
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    // Determinar o vencedor
    if (currentPlayer == 1) {
        printf("O jogador venceu!\n");
    } else {
        printf("O computador venceu!\n");
    }

    return 0;
}

