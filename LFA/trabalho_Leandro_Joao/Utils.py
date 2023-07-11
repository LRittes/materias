from colorama import init, Fore, Style
import Automatos

flag = True
automato = "automato"
buffer = "buffer"

init()


def mostrarBaixoNivel(maquina, state, transicao, cor):
    print(
        cor + f">>> [{maquina}] indo para o estado {state} com a transição {transicao}")
    print(Style.RESET_ALL, end="")


def checkOpcao(opcoes: str):
    resp = str(input())

    while resp not in opcoes:
        print("Opção inválida!")
        resp = str(input())

    return resp


def isSinc(alfssinc: list, alf: str):
    if alf in alfssinc:
        return True
    return False

#  ---- Se consegue prosseguir no automato principal


def podeProximoEstado(S: dict, state: str, letter: str) -> bool:
    try:
        if state != None:
            state = S[state][letter]
            return True
        else:
            return False
    except Exception:
        return False

#  ---- Se consegue prosseguir no automato do buffer


def podeProximoEstadoBuffer(S: dict, state: str, letter: str) -> bool:
    if (state == "0"):
        return False
    return True


def pegarSaidaBuffer(estados: list[dict], estadoAtual: str):
    for estado in estados:
        for chave in estado.keys():
            if chave == estadoAtual:
                return estado[estadoAtual]


def vaiProximoEstado(S: dict, state: str, transicao: str):
    return S[state][transicao]


def reporEstoque(currentStateBuffer, currentState, rep, check, transicao, funcoes,estadosSaidas):
    currentState = vaiProximoEstado(Automatos.S1, currentState, transicao)
    if (flag):
        mostrarBaixoNivel(automato, currentState, transicao, Fore.YELLOW)

    transicao = rep
    currentStateBuffer = vaiProximoEstado(
        funcoes, currentStateBuffer, transicao)
    if (flag):
        mostrarBaixoNivel(buffer, currentStateBuffer, transicao, Fore.GREEN)
    currentState = vaiProximoEstado(Automatos.S1, currentState, transicao)
    if (flag):
        mostrarBaixoNivel(automato, currentState, transicao, Fore.YELLOW)

    resp = None
    while resp != "2":

        print("Deseja colocar mais 1 unidade?")
        print("1 - sim")
        print("2 - não")
        resp = checkOpcao("12")
        opcoes = {
            "1": "mais1",
            "2": "r_man"
        }
        if resp == "1":
            try:

                transicao = opcoes[resp]

                currentStateBuffer = vaiProximoEstado(
                    funcoes, currentStateBuffer, check)
                if (flag):
                    mostrarBaixoNivel(
                        buffer, currentStateBuffer, check, Fore.GREEN)
                currentState = vaiProximoEstado(
                    Automatos.S1, currentState, transicao)
                if (flag):
                    mostrarBaixoNivel(automato, currentState,
                                      transicao, Fore.YELLOW)

                currentState = vaiProximoEstado(
                    Automatos.S1, currentState, check)
                if (flag):
                    mostrarBaixoNivel(automato, currentState,
                                      check, Fore.YELLOW)

                transicao = pegarSaidaBuffer(estadosSaidas, currentStateBuffer)
                currentState = vaiProximoEstado(
                    Automatos.S1, currentState, transicao)
                if (flag):
                    mostrarBaixoNivel(automato, currentState,
                                      transicao, Fore.YELLOW)

                currentStateBuffer = vaiProximoEstado(
                    funcoes, currentStateBuffer, rep)
                if (flag):
                    mostrarBaixoNivel(
                        buffer, currentStateBuffer, rep, Fore.GREEN)

                transicao = rep
                currentState = vaiProximoEstado(
                    Automatos.S1, currentState, transicao)
                if (flag):
                    mostrarBaixoNivel(automato, currentState,
                                      transicao, Fore.YELLOW)

            except Exception:
                print("Estoque cheio!")
                return {"currentState": currentState, "transicao": transicao, "currentStateBuffer": currentStateBuffer}

        elif resp == "2":
            transicao = opcoes[resp]
            currentState = vaiProximoEstado(
                Automatos.S1, currentState, transicao)
            if (flag):
                mostrarBaixoNivel(automato, currentState,
                                  transicao, Fore.YELLOW)
            return {"currentState": currentState, "transicao": transicao, "currentStateBuffer": currentStateBuffer}


def reporTudo(currentStateBuffer, currentState, rep_tudo, funcoes,estadosSaidas) -> dict:
    transicao = pegarSaidaBuffer(estadosSaidas, currentStateBuffer)
    currentState = vaiProximoEstado(Automatos.S1, currentState, transicao)
    if (flag):
        mostrarBaixoNivel(automato, currentState, transicao, Fore.YELLOW)

    transicao = rep_tudo

    currentStateBuffer = vaiProximoEstado(
        funcoes, currentStateBuffer, transicao)
    if (flag):
        mostrarBaixoNivel(buffer, currentStateBuffer, transicao, Fore.GREEN)

    currentState = vaiProximoEstado(Automatos.S1, currentState, transicao)
    if (flag):
        mostrarBaixoNivel(automato, currentState, transicao, Fore.YELLOW)

    transicao = pegarSaidaBuffer(estadosSaidas, currentStateBuffer)

    currentState = vaiProximoEstado(Automatos.S1, currentState, transicao)
    if (flag):
        mostrarBaixoNivel(automato, currentState, transicao, Fore.YELLOW)
    return {"currentState": currentState, "transicao": transicao, "currentStateBuffer": currentStateBuffer}
