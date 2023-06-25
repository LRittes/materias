from Aluno import Aluno
from Professor import Professor

#professores
Professores = []
Professores.append(Professor("Arthur", 3000))
Professores.append(Professor("Brenda", 1000))

#alunos
Alunos = []
Alunos.append(Aluno("Joao", [0, 3.5,0.5,2,1]))
Alunos.append(Aluno("Alexandre", [8.5, 9, 8.5, 9, 9.5]))
Alunos.append(Aluno("Juliana", [7, 8, 6.5, 8.25, 7.5]))
Alunos.append(Aluno("Natalia", [5.25, 7.5, 6.5, 7, 8]))
Alunos.append(Aluno("Felipinho", [2,5,3,0,4]))

#exibir
print("Professores: ")
for professor in Professores:
    print(professor)


print("Alunos: ")
for aluno in Alunos:
    print(aluno)