#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "headers/pilhas.h"

int main() {
  PSE *pse = criarPSE();
  empilharPSE(pse, 1);
  empilharPSE(pse, 2);
  empilharPSE(pse, 3);

  PE *pe = PSE_PE(pse);

  printf("PSE original: \n");
  imprimirPSE(pse);
  printf("PE criada:\n");
  imprimirPE(pe);

  excluirPSE(pse);
}