from Pessoa import Pessoa

class Professor(Pessoa):

    def __init__(self, nome, salario):
        self.__nome = nome
        self.__salario = salario

    def __repr__(self) :
        return "Nome: " + self.__nome + "\nSalario: R$ " + str(self.__salario) + "\n"