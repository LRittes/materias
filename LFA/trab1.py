       
def iniciarAutomato(q0,S,F):
    fita = ["sel_pip","check_pip","pip1","moeda","pag_ok","vend_pip"]
    # print("Insira uma linguagem: ")
    # linguagem = input()
    # for letter in linguagem:
        # fita.append(letter)

    auxState = q0

    for letter in fita:
        try:
            if auxState != None:
                auxState = S[auxState][letter] 
            else:
                break
        except Exception:
            auxState = None
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

  