import string

class Endereco:

    def __init__(self):
        self.__rua = ""
        self.__numero = 0
        self.__bairro = ""
        self.__cidade = ""
        self.__estado = ""
        self.__cep = ""

    def getRua(self):
        return self.__rua
    
    def setRua(self, rua: string):
        self.__rua = rua

    def getNumero(self):
        return self.__numero
    
    def setNumero(self, numero: int):
        self.__numero = numero

    def getBairro(self):
        return self.__bairro

    def setBairro(self, bairro : string):
        self.__bairro = bairro

    def getCidade(self):
        return self.__cidade

    def setCidade(self, cidade: string):
        self.__cidade = cidade

    def getEstado(self):
        return self.__estado

    def setEstado(self, estado : string):
        self.__estado = estado

    def getCep(self):
        return self.__cep
    
    def setCep(self, cep :string):
        self.__cep = cep

    def __str__(self):
        return 'Rua: {} \n Numero: {} \n Bairro: {} \n Cidade: {} \n Estado: {} \n Cep: {}'.format(self.__rua, self.__numero, self.__bairro, self.__cidade, self.__estado, self.__cep)