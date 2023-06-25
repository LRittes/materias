import json
from dados import Filme
from negocio.Sistema import Sistema

sistema = Sistema()

while True:    
    print("Menu: ")
    print("1 - Ler arquivo Json")
    print("2 - Mostrar dados")
    print("0 - Sair")

    option: int = int(input())

    if(option == 1):
        while True:
            print("Nome do arquivo: ")
            print("> ")
            arq: str = str(input())
            arq += ".json"
            try:
                with open(arq) as my_json:
                    sistema.leJson(my_json)
                    break
            except:
                print("Erro ao abrir o arquivo tente novamente")

    if(option == 2):
        print("Filmes:\n")
        l = sistema.getListaDeFilmes()
        if l == 0:
            print("Nenhum dado encontrado")
        else:
            for i in l:
                print(i)
                print("\n")
    
    if(option == 0):
        break