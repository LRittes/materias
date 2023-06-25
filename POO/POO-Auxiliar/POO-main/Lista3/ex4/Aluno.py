from Pessoa import Pessoa

class Aluno(Pessoa):

    def __init__(self, nome, notas):
        self.__nome = nome
        self.__notas = notas

    def calcularMedia(self):
        somatorio = 0

        for nota in self.__notas:
            somatorio += nota

        return somatorio/len(self.__notas)

    def __repr__(self):
        s = ""
        s += "Nome: " + self.__nome + "\n"
        for i in range(len(self.__notas)):
            s += "Nota: " + str(i+1) + ":" + str(self.__notas[i])
            s += "\n"
        s+= "Media: " + str(self.calcularMedia())
        s+= "\nEstado atual: " + ("Aprovado" if self.calcularMedia() >=7 else "Reprovado")

        return s