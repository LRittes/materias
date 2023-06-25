from dados.Filme import Filme
import json
from typing import List

# Tipo de dado de uma lista de filmes
Filmes = List[object]

class Sistema:
    def __init__(self) -> None:
        self.__listaDeFilmes: Filmes = []
        
    def adicionaFilme(self, filme):
        self.__listaDeFilmes.append(filme)

    def leJson(self, string_json):
        lista_json = json.load(string_json)

        for i in lista_json:
            filme = Filme()
            filme.setTitulo(i['Titulo'])
            filme.setAno(i['Ano'])
            filme.setClassificacao(i['Classificacao'])
            filme.setNota(i['Nota'])
            self.adicionaFilme(filme)
        
        self.ordenarPorNota()
        

    def ordenarPorNota(self):
        self.__listaDeFilmes.sort(key=lambda x: x.getNota())

    
    def getListaDeFilmes(self)->Filmes:
        if(len(self.__listaDeFilmes) == 0):
            return 0
        else: 
            return self.__listaDeFilmes

        
    
