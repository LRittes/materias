from dados.Veiculo import Veiculo
from dados.Cliente import Cliente

class Negocio:

    def __init__(self):
        self.__veiculos =  []
        self.__contAlugados = 0
        self.__clientes = []
        self.__contClientes = 0

    def getVeiculo(self):
        return self.__veiculos

    def setVeiculo(self, veiculo : Veiculo):
        self.__veiculos.append(veiculo)

    def getContAlugados(self):
        return self.__contAlugados

    def setContAlugados(self):
        self.__contAlugados = self.__contAlugados + 1

    def getContClientes(self):
        return self.__contClientes

    def setContClientes(self):
        self.__contClientes = self.__contClientes + 1

    def getClientes(self):
        return self.__clientes

    def setClientes(self, cliente : Cliente):
        self.__clientes.append(cliente)

    def __str__(self):
        return 'Veiculos: {} \n Clientes: {}'.format(self.__veiculos, self.__clientes)