from abc import ABC
import string

class Pessoa(ABC):

    def __init__(self):
        self.__nome = ""

    def getNome(self):
        return self.__nome

    def setNome(self, nome: string):
        self.__nome = nome