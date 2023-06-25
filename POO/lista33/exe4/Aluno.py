from Pessoa import Pessoa


class Aluno(Pessoa):    
    def __init__(self, nome, notas):
        self._notas = notas
        self._nome = nome

    def calcularMedia(self):    
        media = 0
        for nota in self._notas:
            media += nota
        return media / len(self._notas)

    def __repr__(self):
        res = ""
        res += "Nome: " + self._nome + "\n"
        for i in range(len(self._notas)):
            res += "Nota " + str(i+1) + ": " + str(self._notas[i])
            res += "\n"
        res += "Média: " + str(self.calcularMedia())
        res += "\nAluno está: " + ("Aprovado\n" if self.calcularMedia() >=7 else "Reprovado\n")

        return res
