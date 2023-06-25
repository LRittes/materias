import string
from Data import Data

class Veiculo:

    def __init__(self):
        self.__modelo = ""
        self.__marca = ""
        self.__ano = 0
        self.__dataVencimento = Data()

    def getModelo(self):
        return self.__modelo
    
    def setModelo(self, modelo: string):
        self.__modelo = modelo

    def getMarca(self):
        return self.__marca

    def setMarca(self, marca: string):
        self.__marca = marca

    def getAno(self):
        return self.__ano

    def setAno(self, ano: int):
        self.__ano = ano

    def getDataVencimento(self):
        return self.__dataVencimento

    def setDataVencimento(self, data: Data):
        self.__dataVencimento = data

    def __str__(self):
        return 'Modelo: {} \n Marca: {} \n Ano: {} \n Data: {}'.format(self.__modelo, self.__marca, self.__ano, self.__dataVencimento)