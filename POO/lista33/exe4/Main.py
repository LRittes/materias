from Aluno import Aluno
from Professor import Professor

Alunos = []
Alunos.append(Aluno("Joao", [9, 7, 6, 1, 7]))
Alunos.append(Aluno("Pedro", [1, 2, 4.5, 5, 10]))
Alunos.append(Aluno("Luiz", [4, 8, 2, 0.5, 1.5]))
Alunos.append(Aluno("Luisa", [3.5, 2.5, 8, 9.5, 7]))
Alunos.append(Aluno("Maria", [8, 8, 10, 9, 9]))

Professores = []
Professores.append(Professor("Tadeu", 2500))
Professores.append(Professor("Nirda", 5000))

print("Professores: ")
for professor in Professores:
    print(professor)


print("Alunos: ")
for aluno in Alunos:
    print(aluno)