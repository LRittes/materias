//gcc apuracao.c -o apuracao -pthread
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <assert.h>
#include <string.h>
#include <ctype.h>
#include "utils.h"

char arquivo[64];

typedef struct listaID{
  unsigned int id;
  struct listaID *next;
}Lvotos;

typedef struct listaNomes{
  unsigned int id;
  char nome[30];
  int nrVotos;
  struct listaNomes *next;
}Lcandidatos;

//Listas globais
Lvotos **cabecaLvotos;
Lcandidatos **cabecaLcand;

//Variavel global parar guardar nome do arquivo
char arquivo[64];

//Mutex para controlar a inserção e remoção na lista de votos
pthread_mutex_t lock;

//Vetor global, utilizado para main continuar procurando votos, mesmo que encontre a lista de votos vazia em uma primeira ocasião
int *t_inserindo;

/*
Remove espaços de começo de uma string, sinceramente não sei o que acontece mas funciona
https://stackoverflow.com/questions/352055/best-algorithm-to-strip-leading-and-trailing-spaces-in-c
*/
void RemoveSpaces(char* string){
    assert(string);

    const char* firstNonSpace = string;

    while(*firstNonSpace != '\0' && isspace(*firstNonSpace))
    {
         ++firstNonSpace;
    }

    size_t len = strlen(firstNonSpace)+1;

    memmove(string, firstNonSpace, len);
}

//Função para criar um novo nó na lista de candidatos
Lcandidatos *cria_no_lista_candidatos(){
  Lcandidatos *novo_no;
  novo_no = (Lcandidatos*)malloc(sizeof(Lcandidatos));

  if(novo_no == NULL){
    printf("Erro alocando novo no");
    exit(-1);
  }
  return novo_no;
}

//Função para criar um novo nó na lista de votos
Lvotos *cria_no_lista_votos(){
  Lvotos *novo_no;
  novo_no = (Lvotos*)malloc(sizeof(Lvotos));//

  if(novo_no == NULL){
    printf("Erro alocando novo no");
    exit(-1);
  }
  return novo_no;
}



/*
 Função auxiliar do bubble sort para ordenar a
 lista de candidatos de maior para menor voto.
 No inicio foi ordernar literalmente os nós da lista de candidatos
 porem estava gerando muitos erros e ficando desnecessariamente complexo.
 Por fim foi optado apenas por trocar os valores de nó para nó.
*/
void swap(Lcandidatos *a, Lcandidatos *b){
  int temp_votos = a->nrVotos;
  a->nrVotos = b->nrVotos;
  b->nrVotos = temp_votos;

  int temp_id = a->id;
  a->id = b->id;
  b->id = temp_id;

  char string_temp[30];
  strcpy(string_temp, a->nome);
  strcpy(a->nome, b->nome);
  strcpy(b->nome, string_temp);
}

/*
 Apenas foi escolhido o bubble sort por ser fácil e
 estamos desconsiderando um número grande de candidatos
 */
void bubbleSort(){
  int swapped, i;
  Lcandidatos *ptr1;
  Lcandidatos *lptr = NULL;

  if ((*cabecaLcand) == NULL)
      return;

  do
  {
      swapped = 0;
      ptr1 = (*cabecaLcand);

      while (ptr1->next != lptr)
      {
          if (ptr1->nrVotos < ptr1->next->nrVotos)
          {
              swap(ptr1, ptr1->next);
              swapped = 1;
          }
          ptr1 = ptr1->next;
      }
      lptr = ptr1;
  }
  while (swapped);
}

int tamanho_lista_votos(){
  int i = 0;
  Lvotos *aux;

  for(aux = *(cabecaLvotos); aux != NULL; i++){
    aux = aux->next;
  }
  return i;
}


//Função para adicionar um voto na lista cabecaLvotos
void append_lista_votos(unsigned int id) {
	Lvotos *prox, *aux;
	prox = cria_no_lista_votos();

	prox->id=id;
	prox->next=NULL;

	if(*(cabecaLvotos) == NULL){
		*(cabecaLvotos) = prox;
	}
	else{
		aux = *(cabecaLvotos);

		for( ; aux->next != NULL;){
			aux=aux->next;
		}
		aux->next=prox;
	}
}

//Função para remover um voto na lista cabecaLvotos
Lvotos* pop_lista_votos(){
    if (*cabecaLvotos == NULL){
        //printf("Lista de votos vazia\n");
        return NULL;
    }
    Lvotos * aux = *cabecaLvotos;
    Lvotos *retorno = (*cabecaLvotos);
    cabecaLvotos = &(aux->next);

    return retorno;
}

//Função para exibir votos
void exibe_lista_votos(){
	Lvotos *aux;
	aux = *cabecaLvotos;
	if (aux == NULL){
		//printf("\nLista de votos Vazia\n");
	}
	else{
		while(aux!=NULL){
			//printf("ID: %d\n",aux->id);
			aux = aux->next;
		}
	}
}

//Função para exibir candidatos
void exibe_lista_candidatos(){
    Lcandidatos * current;
    if (*cabecaLcand == NULL){
  		printf("Lista de candidatos não inicializada\n");
      return;
  	}
    for(current = *(cabecaLcand); current ; current=current->next){
        printf("%u %s %d\n", current->id,current->nome, current->nrVotos);
    }
}

//Função que inicializa a lista de candidatos
void inicializa_lista_candidatos(){
    FILE *fp;
    char line[256];
    Lcandidatos *current;
    current = NULL;

    fp = fopen(arquivo, "r");

    //Lendo o arquivo linha por linha, guardando a linha em 'line'
    while(fgets(line, sizeof(line), fp) != NULL){
      unsigned int temp_id = 0;
      char temp_nome[64];
      Lcandidatos *no;
      no = cria_no_lista_candidatos();

      //Lendo line, e armazenando os valores em variaveis temporarias
      sscanf(line, "%d%[^\n]s", &temp_id, temp_nome);

      //Atribuindo valores para o nó temporario atual
      RemoveSpaces(temp_nome);
      strcpy(no->nome, temp_nome);
      no->id = temp_id;
      no->nrVotos = 0;
      no->next = NULL;

      int checa_candidato_existe = 0;
      if((*cabecaLcand) == NULL){
        (*cabecaLcand) = no;
      }else{
        checa_candidato_existe = 0;
        for(current = (*cabecaLcand); current->next != NULL; current = current->next){
            //printf("Current: %d|| No: %d\n", current->id, no->id);
            if(current->id == no->id){
              printf("\nCandidatos com o mesmo ID.\nCandidato %s ID: %d == Candidato %s ID: %d\nCandidato %s será descartado.\n", current->nome, current->id,  no->nome, no->id, no->nome);
              checa_candidato_existe = 1;
              break;
            }else continue;
	      }
        if(checa_candidato_existe == 1);
        else{
          current->next = no;
        }
      }
    }

    fclose(fp);
}

// Threads precisam contar o nr de votos validos / invalidos
// concorrencia está ao inserir na lista de VOTOS;
void *processaVotos(void *thread_id){
  char votoString[30];
  long tid = (long)thread_id;

  /*
   Vetor para guardar o total de votos e votos invalidos contabilizados pela thread
   totais[0] = total de votos contabilizados pela thread
   totais[1] = votos invalidos contabilizados pela thread
  */
  unsigned long *totais = (unsigned long*)malloc((2)*sizeof(unsigned long));
  totais[0] = 0;
  totais[1] = 0;
  char novoArq[64];
  char line[128];
  Lcandidatos *current;
  FILE *fp;

  //Concatenando nome do arquivo de entrada com o id recebido de cada thread
  sprintf(novoArq, "%s-%ld", arquivo, tid);

  //Abrindo o novoArq, se ele não existir informa erro e finaliza a thread atual
  fp = fopen(novoArq, "r");
  if(fp==NULL){
    printf("\nArquivo inexistente: %s --> Finalizando Thread[%ld]\n", novoArq, tid);
    *(t_inserindo + (tid-1)) = 0;
    pthread_exit((void*)totais);
  }
  //Caso exista, o programa continua e le linha por linha
  while(fgets(line, sizeof(line), fp) != NULL){
    /*
    Lendo valor guardado em line pela função fgets
    armazenando temporariamente em vetorString
    */
    sscanf(line, "%s", votoString);

    /*
    Convertendo de string para unsigned int com a função strtoul()
    caso seja uma string, valor de voto = 0
    */
    unsigned int voto = strtoul(votoString,NULL,10);

    /*
    Contabilizando votos totais
    Primeiro, caso voto = 0, é verificado se o voto era realmente 0 ou era uma string,
    que retornou 0 pela função strtoul.

    Então, caso o voto seja válido, é adicionado a lista de votos,
    se não, é contabilizado como voto invalido, incrementando totais[1]
    */
    totais[0]++;
    //printf("Voto recebido de line: %s ---> Voto convertido: %u\n", votoString, voto);
    if(strncmp(line, "0", 1) != 0 && voto == 0){
      totais[1]++;
      //printf("IF Voto Invalido: %u\n",voto);
    }
    else{
      /*
      Inicio da região critica das threads, que ao percorrerem a lista de votos,
      podem gerar inconsistência pois irão adicionar novos nó a lista de votos
      */
      pthread_mutex_lock(&lock);
      for(current = *(cabecaLcand); current ; current=current->next){
        if(current->id == voto){
          append_lista_votos(voto);
          break;
        }
        else if(current->next == NULL && current->id != voto){
          //printf("ELIF Voto Invalido: %u\n",voto);
          totais[1]++;
        }
      }
      pthread_mutex_unlock(&lock);
    }
  }
  fclose(fp);



  /*
  Ao fim de cada thread, retorna o vetor totais, com o total de votos
  que a thread leu em totais[0] e os votos invalidos em totais[1].
  Também é alterado o valor da posição atual(numéro da Thread)
  do vetor t_inserindo
  */
  *(t_inserindo + (tid-1)) = 0;
  pthread_exit((void*)totais);
}

int main(int argc, char **argv){
  if(argc < 3){
      printf("Usage: ./apuracao NUM_THREADS NOME_ARQUIVO\n");
      return -1;
  }
  // Alocando listas globais
  cabecaLvotos = (Lvotos **)malloc(sizeof(Lvotos *));
  cabecaLcand = (Lcandidatos **)malloc(sizeof(Lcandidatos *));

  //Listas auxilares para percorrer lista de candidatos e lista de votos
  Lcandidatos *current;
  Lvotos *aux;

// Variavel para checar erros de inicialização de Threads e do mutex
  int rc;

  //Variavél que armazena o maior número de nós da lista de votos
  int maior_tamanho_lista = 0;

  long tid = 0;
  long NUM_THREADS;
  unsigned long *totais;
  unsigned long total_votos_thr = 0, total_invalidos_thr = 0, votos_total = 0, votos_invalidos = 0;
  void *ret;
  /*
  Utilizando variavel para armazenar numero de threads
  Convertendo argv[1] de string para int e depois long
  */
  NUM_THREADS = (long)atoi(argv[1]);
  pthread_t threads[NUM_THREADS];
  t_inserindo = (int*)malloc(sizeof(int)*NUM_THREADS);

  for(int i = 0; i < NUM_THREADS; i++){
    *(t_inserindo + i) = 1;
  }


  if(NUM_THREADS < 1){
    printf("Nr. de threads precisa ser maior ou igual a 1\n");
    exit(-1);
  }

  //Atruibuindo o valor de todas as posiçoes de t_inserindo como 0
  for(int i = 0; i < NUM_THREADS; i++){
    *(t_inserindo + i) = 1;
  }

  //Passando nome do arquivo para variavel global arquivo
  strcpy(arquivo, argv[2]);

  inicializa_lista_candidatos();

  //Inicializando mutex
  rc = pthread_mutex_init(&lock, NULL);
  if(rc != 0){
    printf("\n Erro iniciando mutex\n");
    exit(-1);
  }


  //Inicializando threads
  for(tid=0; tid<NUM_THREADS; tid++){
    rc = pthread_create(&threads[tid], NULL, processaVotos, (void *)tid+1);
    if(rc){
      printf("ERRO CRIANDO THREAD - rc = %d\n", rc);
      exit(-1);

    }
  }

  /*
  Variaveis que garantem que a main irá iterar novamente após a
  conclusão das threads caso ainda hajam votos não processados
  */
  int soma_vetor_threads = 1;
  int main_while = 1;
  int contador = 0;

  while(main_while){
    soma_vetor_threads = 0;
    /*
    A variavel soma_vetor_threads recebe a soma dos valores das posições
    do vetor global t_inserindo, inicializado em 0,
    que ao final de cada thread é atribuido o valor 1.
    */
    for(int i = 0; i < NUM_THREADS; i++){
      soma_vetor_threads += *(t_inserindo + i);
    }
    /*
    Inicio da região crítica da main, onde vai ser verificado se a lista de votos não é nula,
    para assim, percorrer a lista de candidatos e de votos, atribuindo o voto para o candidato correto.
    O mutex é utilizado justamente para evitar as Threads acessem a lista de votos ao mesmo tempo,
    o que deixaria o programa inconsistente e eventualmente resultaria na perca de votos.
    */
    pthread_mutex_lock(&lock);
    while(*(cabecaLvotos) != NULL){
      //Comparando o tamanho atual do número máximo de nós da lista de votos, caso seja maior, é atribuido para a váriavel maior_tamanho_lista
      if(tamanho_lista_votos() > maior_tamanho_lista){
        maior_tamanho_lista = tamanho_lista_votos();
      }
      aux = pop_lista_votos();

      for(current = *(cabecaLcand); current != NULL; current=current->next){
        if(aux == NULL) break;
        if(aux->id == current->id){
          current->nrVotos = current->nrVotos + 1;
        }
      }
    }
    if(soma_vetor_threads == 0){
      contador++;
    }
    if(contador > 1){
      main_while = 0;
    }

    pthread_mutex_unlock(&lock);
  }


  /*
  Recebendo o retorno das threads,
  Contabilizando número total de votos e votos invalidos
 */
  for(tid=0; tid<NUM_THREADS; tid++){
    pthread_join(threads[tid], &ret);
    totais = (unsigned long*)ret;

    total_votos_thr = totais[0];
    total_invalidos_thr = totais[1];

    votos_total += total_votos_thr;
    votos_invalidos += total_invalidos_thr;
  }

  bubbleSort();
  /* Exibindo resultados, utilizando fmtcabvoto e fmtvoto de utils.h */
  printf("\nTotal de votos processados: %ld\n", votos_total);
  printf("Votos validos: %ld (%.2f %%)\n", (votos_total - votos_invalidos), ((float) (votos_total-votos_invalidos) / (votos_total)) * 100);
  printf("Tamanho maximo da fila: %d nos\n\n", maior_tamanho_lista);
  printf(fmtcabvoto);
  for(current = *(cabecaLcand); current != NULL; current = current->next){
    printf(fmtvoto, current->id, current->nome, current->nrVotos, ((float)current->nrVotos/(votos_total - votos_invalidos)) * 100);
  }

  pthread_exit(NULL);
}
