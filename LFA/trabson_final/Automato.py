from estado import Estado

class Automato:
    # Todos os estados do automato
    estados = []
    alfabeto = []
    estadoInicial: Estado = None
    estadosFinais = []
    
    def __init__(self) -> None:
        pass

    def definirEstadoInicial(self, e: Estado):
        self.estadoInicial = e

    # insere um estado como final
    def insertEstadoFinal(self, e: Estado):
        # confere se o estado esta na lista dos estado
        if e not in self.estados:
            return
        # conefere se o estado já esta na lista de estados finais
        if(self.hasEstado(e,self.estadosFinais)):
            return
        self.estadosFinais.append(e)


    def insertEstado(self,e:Estado):
        if(self.hasEstado(e,self.estados)):
            return
        self.estados.append(e)
    
    def insertAlfabeto(self,letra):
        if(self.hasLetra(letra)):
            return
        self.alfabeto.append(letra)

    def hasEstado(self,e:Estado, lista: list) -> bool:
        for estado in lista:
            if(estado == e or estado.nome == e.nome):
                return True
        return False
    
    def hasLetra(self,letra) -> bool:
        if letra in self.alfabeto:
            return True
        
        return False

a = Automato()
e = Estado(1)
e1 = Estado(1)
a.insertEstado(e)
print(a.estadosFinais)
a.insertEstadoFinal(e1)
print(a.estadosFinais)
a.insertEstadoFinal(e)
print(a.estadosFinais)