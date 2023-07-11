import Automatos
from colorama import init, Fore
from Utils import *

# Inicializa o função de colorir
init()


currentStateBufferPipoca = Automatos.q0bp
currentStateBufferAgua = Automatos.q0ba
currentStateBufferRefri = Automatos.q0br
currentState = Automatos.q01
transicao = "inicial"

if (flag):
    print(Fore.MAGENTA + f"{automato}s e {buffer}s inicializados")
    print(Fore.RESET)
resp = None

while resp != 2:
    print("1 - Manutenção")
    print("2 - Compra")
    resp = str(checkOpcao("12"))
    if (flag):
        mostrarBaixoNivel(automato, currentState, transicao, Fore.YELLOW)

    if resp == "2":
        print("Escolha o produto:")
        print("1 - Pipoca")
        print("2 - água")
        print("3 - Refrigerante")
        resp = checkOpcao("123")
        opcoes = {
            "1": "sel_pip",
            "2": "sel_agua",
            "3": "sel_refri"
        }
        currentState = vaiProximoEstado(
            Automatos.S1, currentState, opcoes[resp])
        if (flag):
            mostrarBaixoNivel(automato, currentState,
                              opcoes[resp], Fore.YELLOW)

        if (resp == "1"):
            transicao = "check_pip"

            currentStateBufferPipoca = vaiProximoEstado(
                Automatos.Sbp, currentStateBufferPipoca, transicao)
            if (flag):
                mostrarBaixoNivel(
                    buffer, currentStateBufferPipoca, transicao, Fore.GREEN)

            currentState = vaiProximoEstado(
                Automatos.S1, currentState, transicao)
            if (flag):
                mostrarBaixoNivel(automato, currentState,
                                  transicao, Fore.YELLOW)

            transicao = pegarSaidaBuffer(
                Automatos.Qbp, currentStateBufferPipoca)

            if transicao != pegarSaidaBuffer(Automatos.Qbp, "0"):

                currentState = vaiProximoEstado(
                    Automatos.S1, currentState, transicao)
                if (flag):
                    mostrarBaixoNivel(automato, currentState,
                                      transicao, Fore.YELLOW)

                print("Método de pagamento:")
                print("1 - Cartão")
                print("2 - Dinheiro")
                print("3 - Moeda")
                resp = checkOpcao("123")

                opcoes = {
                    "1": "cartao",
                    "2": "dinheiro",
                    "3": "moeda",
                }

                if (resp == "1"):
                    transicao = opcoes[resp]
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    # Colocar aleatóriedade ou deixar o usuário escolher

                    transicao = "pag_ok"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    transicao = "vend_pip"
                    currentStateBufferPipoca = vaiProximoEstado(
                        Automatos.Sbp, currentStateBufferPipoca, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            buffer, currentStateBufferPipoca, transicao, Fore.GREEN)

                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                        print(Fore.CYAN +
                              ">>> {automato} Entregando o produto!")
                        print(Fore.RESET)

                    transicao = "retorno"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                elif (resp == "2"):
                    transicao = opcoes[resp]
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    # Colocar aleatóriedade ou deixar o usuário escolher

                    transicao = "pag_ok"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    transicao = "vend_pip"
                    currentStateBufferPipoca = vaiProximoEstado(
                        Automatos.Sbp, currentStateBufferPipoca, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            buffer, currentStateBufferPipoca, transicao, Fore.GREEN)

                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                        print(Fore.CYAN +
                              ">>> {automato} Entregando o produto!")
                        print(Fore.RESET)

                    transicao = "retorno"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                elif (resp == "3"):
                    transicao = opcoes[resp]
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    # Colocar aleatóriedade ou deixar o usuário escolher

                    transicao = "pag_ok"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    transicao = "vend_pip"
                    currentStateBufferPipoca = vaiProximoEstado(
                        Automatos.Sbp, currentStateBufferPipoca, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            buffer, currentStateBufferPipoca, transicao, Fore.GREEN)

                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                        print(Fore.CYAN +
                              ">>> {automato} Entregando o produto!")
                        print(Fore.RESET)

                    transicao = "retorno"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

            else:
                transicao = pegarSaidaBuffer(
                    Automatos.Qbp, currentStateBufferPipoca)

                currentState = vaiProximoEstado(
                    Automatos.S1, currentState, transicao)
                if (flag):
                    mostrarBaixoNivel(automato, currentState,
                                      transicao, Fore.YELLOW)

                print("Não há produto!")
        elif (resp == "2"):
            transicao = "check_agua"

            currentStateBufferAgua = vaiProximoEstado(
                Automatos.Sba, currentStateBufferAgua, transicao)
            if (flag):
                mostrarBaixoNivel(
                    buffer, currentStateBufferAgua, transicao, Fore.GREEN)

            currentState = vaiProximoEstado(
                Automatos.S1, currentState, transicao)
            if (flag):
                mostrarBaixoNivel(automato, currentState,
                                  transicao, Fore.YELLOW)

            transicao = pegarSaidaBuffer(
                Automatos.Qba, currentStateBufferAgua)

            if transicao != pegarSaidaBuffer(Automatos.Qba, "0"):

                currentState = vaiProximoEstado(
                    Automatos.S1, currentState, transicao)
                if (flag):
                    mostrarBaixoNivel(automato, currentState,
                                      transicao, Fore.YELLOW)

                print("Método de pagamento:")
                print("1 - Cartão")
                print("2 - Dinheiro")
                print("3 - Moeda")
                resp = checkOpcao("123")

                opcoes = {
                    "1": "cartao",
                    "2": "dinheiro",
                    "3": "moeda",
                }

                if (resp == "1"):
                    transicao = opcoes[resp]
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    # Colocar aleatóriedade ou deixar o usuário escolher

                    transicao = "pag_ok"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    transicao = "vend_agua"
                    currentStateBufferAgua = vaiProximoEstado(
                        Automatos.Sba, currentStateBufferAgua, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            buffer, currentStateBufferAgua, transicao, Fore.GREEN)

                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                        print(Fore.CYAN +
                              ">>> {automato} Entregando o produto!")
                        print(Fore.RESET)

                    transicao = "retorno"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                elif (resp == "2"):
                    transicao = opcoes[resp]
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    # Colocar aleatóriedade ou deixar o usuário escolher

                    transicao = "pag_ok"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    transicao = "vend_agua"
                    currentStateBufferAgua = vaiProximoEstado(
                        Automatos.Sba, currentStateBufferAgua, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            buffer, currentStateBufferAgua, transicao, Fore.GREEN)

                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                        print(Fore.CYAN +
                              ">>> {automato} Entregando o produto!")
                        print(Fore.RESET)

                    transicao = "retorno"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                elif (resp == "3"):
                    transicao = opcoes[resp]
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    # Colocar aleatóriedade ou deixar o usuário escolher

                    transicao = "pag_ok"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    transicao = "vend_agua"
                    currentStateBufferAgua = vaiProximoEstado(
                        Automatos.Sba, currentStateBufferAgua, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            buffer, currentStateBufferAgua, transicao, Fore.GREEN)

                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                        print(Fore.CYAN +
                              ">>> {automato} Entregando o produto!")
                        print(Fore.RESET)

                    transicao = "retorno"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
        elif (resp == "3"):
            transicao = "check_refri"

            currentStateBufferRefri = vaiProximoEstado(
                Automatos.Sbr, currentStateBufferRefri, transicao)
            if (flag):
                mostrarBaixoNivel(
                    buffer, currentStateBufferRefri, transicao, Fore.GREEN)

            currentState = vaiProximoEstado(
                Automatos.S1, currentState, transicao)
            if (flag):
                mostrarBaixoNivel(automato, currentState,
                                  transicao, Fore.YELLOW)

            transicao = pegarSaidaBuffer(
                Automatos.Qbr, currentStateBufferRefri)

            if transicao != pegarSaidaBuffer(Automatos.Qbr, "0"):

                currentState = vaiProximoEstado(
                    Automatos.S1, currentState, transicao)
                if (flag):
                    mostrarBaixoNivel(automato, currentState,
                                      transicao, Fore.YELLOW)

                print("Método de pagamento:")
                print("1 - Cartão")
                print("2 - Dinheiro")
                print("3 - Moeda")
                resp = checkOpcao("123")

                opcoes = {
                    "1": "cartao",
                    "2": "dinheiro",
                    "3": "moeda",
                }

                if (resp == "1"):
                    transicao = opcoes[resp]
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    # Colocar aleatóriedade ou deixar o usuário escolher

                    transicao = "pag_ok"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    transicao = "vend_refri"
                    currentStateBufferRefri = vaiProximoEstado(
                        Automatos.Sbr, currentStateBufferRefri, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            buffer, currentStateBufferRefri, transicao, Fore.GREEN)

                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                        print(Fore.CYAN +
                              ">>> {automato} Entregando o produto!")
                        print(Fore.RESET)

                    transicao = "retorno"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                elif (resp == "2"):
                    transicao = opcoes[resp]
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    # Colocar aleatóriedade ou deixar o usuário escolher

                    transicao = "pag_ok"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    transicao = "vend_refri"
                    currentStateBufferRefri = vaiProximoEstado(
                        Automatos.Sbr, currentStateBufferRefri, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            buffer, currentStateBufferRefri, transicao, Fore.GREEN)

                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                        print(Fore.CYAN +
                              ">>> {automato} Entregando o produto!")
                        print(Fore.RESET)

                    transicao = "retorno"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                elif (resp == "3"):
                    transicao = opcoes[resp]
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    # Colocar aleatóriedade ou deixar o usuário escolher

                    transicao = "pag_ok"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

                    transicao = "vend_refri"
                    currentStateBufferRefri = vaiProximoEstado(
                        Automatos.Sbr, currentStateBufferRefri, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            buffer, currentStateBufferRefri, transicao, Fore.GREEN)

                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)
                        print(Fore.CYAN +
                              ">>> {automato} Entregando o produto!")
                        print(Fore.RESET)

                    transicao = "retorno"
                    currentState = vaiProximoEstado(
                        Automatos.S1, currentState, transicao)
                    if (flag):
                        mostrarBaixoNivel(
                            automato, currentState, transicao, Fore.YELLOW)

    else:
        transicao = "man"
        currentState = vaiProximoEstado(Automatos.S1, currentState, transicao)
        if (flag):
            mostrarBaixoNivel(automato, currentState, transicao, Fore.YELLOW)
        transicao = "ini_man"
        currentState = vaiProximoEstado(Automatos.S1, currentState, transicao)
        if (flag):
            mostrarBaixoNivel(automato, currentState, transicao, Fore.YELLOW)

        print("Escolha o produto:")
        print("1 - Pipoca")
        print("2 - água")
        print("3 - Refrigerante")
        resp = checkOpcao("123")
        opcoes = {
            "1": "check_pip",
            "2": "check_agua",
            "3": "check_refri"
        }

        if resp == "1":
            currentStateBufferPipoca = vaiProximoEstado(
                Automatos.Sbp, currentStateBufferPipoca, opcoes[resp])
            if (flag):
                mostrarBaixoNivel(buffer, currentStateBufferPipoca,
                                  opcoes[resp], Fore.GREEN)

            transicao = pegarSaidaBuffer(
                Automatos.Qbp, currentStateBufferPipoca)
            currentState = vaiProximoEstado(
                Automatos.S1, currentState, opcoes[resp])
            if (flag):
                mostrarBaixoNivel(automato, currentState,
                                  opcoes[resp], Fore.YELLOW)

            if transicao == pegarSaidaBuffer(Automatos.Qbp, "0"):
                print("O estoque do produto está vazio, deseja repor tudo?")
                print("1 - sim")
                print("2 - não")
                resp = checkOpcao("12")
                opcoes = {
                    "1": "rep5_pip",
                    "2": "pip1",
                }

                if resp == "2":
                    transicao = opcoes[resp]
                    retorno = reporEstoque(
                        currentStateBufferPipoca, currentState, "rep_pip", "check_pip", transicao, Automatos.Sbp,Automatos.Qbp)
                    currentState = retorno["currentState"]
                    transicao = retorno["transicao"]
                    currentStateBufferPipoca = retorno["currentStateBuffer"]

                else:
                    retorno = reporTudo(currentStateBufferPipoca,
                                        currentState, opcoes[resp], Automatos.Sbp,Automatos.Qbp)
                    currentState = retorno["currentState"]
                    transicao = retorno["transicao"]
                    currentStateBufferPipoca = retorno["currentStateBuffer"]
            else:

                retorno = reporEstoque(
                    currentStateBufferPipoca, currentState, "rep_pip", "check_pip", "pip1", Automatos.Sbp,Automatos.Qbp)
                currentState = retorno["currentState"]
                transicao = retorno["transicao"]
                currentStateBufferPipoca = retorno["currentStateBuffer"]

        elif resp == "2":
            currentStateBufferAgua = vaiProximoEstado(
                Automatos.Sba, currentStateBufferAgua, opcoes[resp])
            if (flag):
                mostrarBaixoNivel(buffer, currentStateBufferAgua,
                                  opcoes[resp], Fore.GREEN)

            transicao = pegarSaidaBuffer(Automatos.Qba, currentStateBufferAgua)
            currentState = vaiProximoEstado(
                Automatos.S1, currentState, opcoes[resp])
            if (flag):
                mostrarBaixoNivel(automato, currentState,
                                  opcoes[resp], Fore.YELLOW)

            if transicao == pegarSaidaBuffer(Automatos.Qba, "0"):
                print("O estoque do produto está vazio, deseja repor tudo?")
                print("1 - sim")
                print("2 - não")
                resp = checkOpcao("12")
                opcoes = {
                    "1": "rep5_agua",
                    "2": "agua1",
                }

                if resp == "2":
                    transicao = opcoes[resp]
                    retorno = reporEstoque(
                        currentStateBufferAgua, currentState, "rep_agua", "check_agua", transicao, Automatos.Sba,Automatos.Qba)
                    currentState = retorno["currentState"]
                    transicao = retorno["transicao"]
                    currentStateBufferAgua = retorno["currentStateBuffer"]

                else:
                    retorno = reporTudo(currentStateBufferAgua,
                                        currentState, opcoes[resp], Automatos.Sba,Automatos.Qba)
                    currentState = retorno["currentState"]
                    transicao = retorno["transicao"]
                    currentStateBufferAgua = retorno["currentStateBuffer"]
            else:

                retorno = reporEstoque(
                    currentStateBufferAgua, currentState, "rep_agua", "check_agua", "agua1", Automatos.Sba,Automatos.Qba)
                currentState = retorno["currentState"]
                transicao = retorno["transicao"]
                currentStateBufferAgua = retorno["currentStateBuffer"]
        
        elif resp == "3":
            currentStateBufferRefri = vaiProximoEstado(
                Automatos.Sbr, currentStateBufferRefri, opcoes[resp])
            if (flag):
                mostrarBaixoNivel(buffer, currentStateBufferRefri,
                                  opcoes[resp], Fore.GREEN)

            transicao = pegarSaidaBuffer(Automatos.Qbr, currentStateBufferRefri)
            currentState = vaiProximoEstado(
                Automatos.S1, currentState, opcoes[resp])
            if (flag):
                mostrarBaixoNivel(automato, currentState,
                                  opcoes[resp], Fore.YELLOW)

            if transicao == pegarSaidaBuffer(Automatos.Qbr, "0"):
                print("O estoque do produto está vazio, deseja repor tudo?")
                print("1 - sim")
                print("2 - não")
                resp = checkOpcao("12")
                opcoes = {
                    "1": "rep10_refri",
                    "2": "refri1",
                }

                if resp == "2":
                    transicao = opcoes[resp]
                    retorno = reporEstoque(
                        currentStateBufferRefri, currentState, "rep_refri", "check_refri", transicao, Automatos.Sbr,Automatos.Qbr)
                    currentState = retorno["currentState"]
                    transicao = retorno["transicao"]
                    currentStateBufferRefri = retorno["currentStateBuffer"]

                else:
                    retorno = reporTudo(currentStateBufferRefri,
                                        currentState, opcoes[resp], Automatos.Sbr,Automatos.Qbr)
                    currentState = retorno["currentState"]
                    transicao = retorno["transicao"]
                    currentStateBufferRefri = retorno["currentStateBuffer"]
            else:

                retorno = reporEstoque(
                    currentStateBufferRefri, currentState, "rep_refri", "check_refri", "refri1", Automatos.Sbr,Automatos.Qbr)
                currentState = retorno["currentState"]
                transicao = retorno["transicao"]
                currentStateBufferRefri = retorno["currentStateBuffer"]

        print("Deseja voltar ao painel?")
        print("1 - sim")
        print("2 - não")
        resp = checkOpcao("12")
        opcoes = {
            "1": "re",
            "2": "ini_man",
        }

        if resp == "1":
            currentState = vaiProximoEstado(
                Automatos.S1, currentState, opcoes[resp])
            if (flag):
                mostrarBaixoNivel(automato, currentState,
                                  opcoes[resp], Fore.YELLOW)
        else:
            resp = 2
