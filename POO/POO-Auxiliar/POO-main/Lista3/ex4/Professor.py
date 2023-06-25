from Pessoa import Pessoa

class Professor(Pessoa):

    def __init__(self, nome, salario):
        self.__nome = nome
        self.__salario = salario

    def __repr__(self) -> str:
        return "Nome: " + self.__nome + "\nSalario: " + str(self.__salario)