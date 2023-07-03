class Estado:
    # Inicia como estado não final
    final = False
    # transições para outros estados
    transicoes: list = []
    nome: str = None
    
    def __init__(self,nome:str) -> None:
        self.nome = str(nome)
    
    # insere os estados (e) com na lista das suas transições
    def insertTransicao(self,transicao:any,strEstado:any):
        if(self.hasEstado(strEstado)):
            if(self.hasTransition(self.pickEstados(strEstado)[strEstado],transicao)):
                return
            else:
                self.pickListafromEstado(strEstado).append(transicao)
                return
        self.transicoes.append({strEstado: [transicao]})

    # 
    def hasEstado(self,e) -> bool:
        if(self.pickEstados(e) != None):
            return True
        
        return False

    def hasTransition(self,lista:list,t) -> bool:
        for trans in lista:
            if(trans == t):
                return True    
        return False
    
    def pickEstados(self,e) -> dict:
        for estado in self.transicoes:
            if(estado.get(e) != None):
                return estado
        return None
    
    def pickListafromEstado(self,strEstado) -> list:
       return self.pickEstados(strEstado)[strEstado]
 