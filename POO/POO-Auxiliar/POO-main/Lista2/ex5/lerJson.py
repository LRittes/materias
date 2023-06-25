import json
from filme import Filme

f = open("POO/Lista2/ex5/filmes.json", "r")
texto = f.read()
listaFilmesJson = json.loads(texto)

listaFilmesObjetos = []

for filme in listaFilmesJson:
    add = Filme(filme['titulo'], filme['ano'], filme['classificacao'], filme['nota'])
    listaFilmesObjetos.append(add)

print("\nAntes de ordenar\n")
for filme in listaFilmesObjetos:
    print(filme)

listaFilmesObjetos = sorted(listaFilmesObjetos, key=Filme.getNota)

print('\nDepois de ordenar\n')
for filme in listaFilmesObjetos:
    print(filme)