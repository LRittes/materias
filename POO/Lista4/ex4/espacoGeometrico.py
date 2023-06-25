from reta import Reta
from ponto2D import Ponto2D
from objetoSobrepostoException import ObjetoSobrepostoException


class EspacoGeometrico:
    def __init__(self):
        self.retas = []
        self.pontos = []

    def adicionarReta(self, reta):
        try:
            for r in self.retas:
                if r.intercepta(reta):
                    raise ObjetoSobrepostoException
            self.retas.append(reta)
        except ObjetoSobrepostoException:
            print("ObjetoSobrepostoException: reta intersepta outra reta do espaco")


    def adicionaPonto(self, ponto):
        try:
            for r in self.retas:
                if r.estaNaReta(ponto):
                    raise ObjetoSobrepostoException
            self.pontos.append(ponto)
        except ObjetoSobrepostoException:
            print("ObjetoSobrepostoException: ponto esta em uma reta do espaco")



espaco = EspacoGeometrico()

y1 = Reta(1, 2)
y2 = Reta(1, 1)
y3 = Reta(2, -2)

a = Ponto2D(3,4)
b = Ponto2D(2,3)
c = Ponto2D(4,6)
d = Ponto2D(3,2)

espaco.adicionarReta(y1)
espaco.adicionarReta(y2)
espaco.adicionarReta(y3)

espaco.adicionaPonto(a)
espaco.adicionaPonto(b)
espaco.adicionaPonto(c)
espaco.adicionaPonto(d)

print("Retas contidas no espaço")
for r in espaco.retas:
    print(f'{r.a}x + {r.b} = y')

print("Pontos contidos no espaço")
for p in espaco.pontos:
    print(f'({p.x},{p.y})')