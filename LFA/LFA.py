E = ['a','b','c']
Q = ['q0','q1']
q0 = 'q0'
S = {
    "q0": {
          "a": "q0",
          "b": "-1",
          "c": "q1"
         },
  "q1": {
          "a": "q1",
          "b": "q0",
          "c": "-1"
         },
}
F = ["q1"]

# abc [a,b,c]
# print("Forneça o alfabeto: (ex: abc) ")
# alfabeto = input()
# for letter in alfabeto:
#     E.append(letter)

# print("Quantos estados: (ex: 3) ")
# sizeStates = int(input())
# for i in range(sizeStates):
#     print(f"Forneça o estado {i}: (ex: q0) ")
#     Q.append(input())

# print("Qual estado inicial: ")
# q0 = input()

# # {
# #   "q0": {
# #           "a": "q1",
# #           "b": "q3",
# #          },
# #   "q1": {
# #           "a": "q1",
# #           "b": "q3",
# #          },
# # }

# for state in Q:
#     S.update({state: {}})
#     for alfabeto in E:
#         print(f"Do estado {state} ele trasita com '{alfabeto}' para: (ex: q1 ou -1 caso não tenha nenhuma transição)")
#         stateAux = input()
#         if stateAux != "-1":
#             S[state].update({alfabeto: stateAux})
#         else:
#              S[state].update({alfabeto: None})
        
# print("Quantos estados finais: (ex: 3) ")
# sizeStates = int(input())
# for i in range(sizeStates):
#     print(f"Forneça o estado final {i}: (ex: q0) ")
#     F.append(input())

fita = []
print("Insira uma linguagem: ")
linguagem = input()
for letter in linguagem:
    fita.append(letter)

auxState = q0

for letter in fita:
    try:
        if auxState != None:
            auxState = S[auxState][letter] 
        else:
            break
    except Exception:
        pass    

hit = False
if auxState != None:
    for finalState in F:
        if auxState == finalState:
            print("Linguagem válida")
            hit = True
            break       
    if hit != True:
        print("Linguagem inválida")      
else:
    print("Linguagem inválida")