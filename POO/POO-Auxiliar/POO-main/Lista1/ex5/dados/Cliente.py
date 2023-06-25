import string
from Endereco import Endereco
from Veiculo import Veiculo

class Cliente:

    def __init__(self):
        self.__nome = ""
        self.__cpf = ""
        self.__endereco = Endereco()
        self.__veiculo = Veiculo()

    def getNome(self):
        return self.__nome

    def setNome(self, nome: string):
        self.__nome = nome

    def getCpf(self):
        return self.__cpf

    def setCpf(self, cpf : string):
        self.__cpf = cpf

    def getEndereco(self):
        return self.__endereco

    def setEndereco(self, endereco: Endereco):
        self.__endereco = endereco

    def getVeiculo(self):
        return self.__veiculo

    def setVeiculo(self, veiculo : Veiculo):
        self.__veiculo = Veiculo

    def __str__(self):
        return 'Nome: {} \n Cpf: {} \n Endereco: {} \n Veiculo Alugado: {}'.format(self.__nome, self.__cpf, self.__endereco, self.__veiculo)
