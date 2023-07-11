
E1 = ['sel_pip', 'check_pip', 'n_pip', 'pip1', 'pipc', 'moeda', 'n_moeda', 'n_dinheiro', 'cartao', 'n_cartao', 'dinheiro', 'tem_troco_moeda',
      'vend_pip', 'tem_troco_dinheiro', 'pag_ok', 'retorno', "man", "rep5_pip", "rep_pip", 're', 'sel_agua', "check_agua", 'n_agua', 'aguac', "rep5_agua",'rep_refri', 'vend_refri', 'check_refri', 'rep10_refri']
Q1 = ['SP', 'E', 'P', 'MP', 'M', 'D', 'C', 'T', 'POK', 'ET', 'SA', 'A',]
q01 = 'E'
S1 = {
    "E": {
        "sel_pip": "SP",
        "sel_agua": "SA",
        "sel_refri": "SR",
        "man": "MAN"
    },
    #  -------- Parte da manutenção
    "MAN": {
        "ini_man": "INI_MAN",
        "re": "E"
    },
    "INI_MAN": {
        "check_pip": "Ck",
        "check_agua": "Ck",
        "check_refri": "Ck",
    },
    "Ck": {
        "n_pip": "NP",
        "n_agua": "NP",
        "n_refri": "NP",
        "pipc": "MAN",
        "aguac": "MAN",
        "refric": "MAN",
        "pip1": "PP",
        "agua1": "PP",
        "refri1": "PP",
    },
    "NP": {
        "rep5_pip": "REPT",
        "rep5_agua": "REPT",
        "rep10_refri": "REPT",
    },
    "REPT": {
        "pipc": "MAN",
        "aguac": "MAN",
        "refric": "MAN",
    },
    "PP": {
        "rep_pip": "REP1",
        "rep_agua": "REP1",
        "rep_refri": "REP1",
    },

    "REP1": {
        "r_man": "MAN",
        "mais1": "INI_MAN"
    },
    #  ----- parte da pipoca
    "SP": {
        "check_pip": "P"
    },
    "P": {
        "n_pip": "E",
        "pip1": "MP",
        "pipc": "MP"
    },
     #  ----- parte da pipoca
    "SA": {
        "check_agua": "A"
    },
    "A": {
        "n_agua": "E",
        "agua1": "MP",
        "aguac": "MP"
    },
     #  ----- parte da pipoca
    "SR": {
        "check_refri": "R"
    },
    "R": {
        "n_refri": "E",
        "refri1": "MP",
        "refric": "MP"
    },
    #  ---- Pagamento
    "MP": {
        "moeda": "M",
        "cartao": "C",
        "dinheiro": "D"
    },
    "M": {
        "n_moeda": "MP",
        "tem_troco_moeda": "T",
        "pag_ok": "POK"
    },
    "D": {
        "n_dinheiro": "MP",
        "pag_ok": "POK",
        "tem_troco_dinheiro": "T"
    },
    "C": {
        "n_cartao": "MP",
        "pag_ok": "POK"
    },
    "POK": {
        "vend_pip": "ET",
        "vend_agua": "ET",
        "vend_refri": "ET",
        
    },
    "ET": {
        "retorno": "E"
    },
}

F = ["ET", "E"]

Ebp = ['rep_pip', 'vend_pip', 'check_pip', 'rep5_pip']
Qbp = [{'0': 'n_pip'}, {'1': 'pip1'}, {'2': 'pip1'},
       {'3': 'pip1'}, {'4': 'pip1'}, {'5': 'pipc'}]
q0bp = '0'
Sbp = {
    '0': {
        'rep_pip': '1',
        'check_pip': '0',
        'rep5_pip': '5'
    },
    '1': {
        'rep_pip': '2',
        'check_pip': '1',
        'vend_pip': '0',
    },
    '2': {
        'rep_pip': '3',
        'check_pip': '2',
        'vend_pip': '1',
    },
    '3': {
        'rep_pip': '4',
        'check_pip': '3',
        'vend_pip': '2',
    },
    '4': {
        'rep_pip': '5',
        'check_pip': '4',
        'vend_pip': '3',
    },
    '5': {
        'vend_pip': '4',
        'check_pip': '5',
    },
}

# ----- estados finais do buffer pipoca --------
Fbp = ["0", "1", "2", "3", "4", "5"]

#  --------- BUFFER AGUA ---------------
Eba = ['rep_agua', 'vend_agua', 'check_agua', 'rep5_agua']
Qba = [{'0': 'n_agua'}, {'1': 'agua1'}, {'2': 'agua1'},
       {'3': 'agua1'}, {'4': 'agua1'}, {'5': 'aguac'}]
q0ba = '0'
Sba = {
    '0': {
        'rep_agua': '1',
        'check_agua': '0',
        'rep5_agua': '5'
    },
    '1': {
        'rep_agua': '2',
        'check_agua': '1',
        'vend_agua': '0',
    },
    '2': {
        'rep_agua': '3',
        'check_agua': '2',
        'vend_agua': '1',
    },
    '3': {
        'rep_agua': '4',
        'check_agua': '3',
        'vend_agua': '2',
    },
    '4': {
        'rep_agua': '5',
        'check_agua': '4',
        'vend_agua': '3',
    },
    '5': {
        'vend_agua': '4',
        'check_agua': '5',
    },
}

# ----- estados finais do buffer agua --------
Fba = ["0", "1", "2", "3", "4", "5"]

#  --------- BUFFER AGUA ---------------
Ebr = ['rep_refri', 'vend_refri', 'check_refri', 'rep10_refri']
Qbr = [{'0': 'n_refri'}, {'1': 'refri1'}, {'2': 'refri1'},
       {'3': 'refri1'}, {'4': 'refri1'}, {'5': 'refri1'},{'6': 'n_refri'}, {'7': 'refri1'}, {'8': 'refri1'},
       {'9': 'refri1'}, {'10': 'refric'}]
q0br = '0'
Sbr = {
    '0': {
        'rep_refri': '1',
        'check_refri': '0',
        'rep10_refri': '10'
    },
    '1': {
        'rep_refri': '2',
        'check_refri': '1',
        'vend_refri': '0',
    },
    '2': {
        'rep_refri': '3',
        'check_refri': '2',
        'vend_refri': '1',
    },
    '3': {
        'rep_refri': '4',
        'check_refri': '3',
        'vend_refri': '2',
    },
    '4': {
        'rep_refri': '5',
        'check_refri': '4',
        'vend_refri': '3',
    },
    '5': {
        'rep_refri': '6',
        'vend_refri': '5',
        'check_refri': '4',
    },
    '6': {
        'rep_refri': '7',
        'check_refri': '6',
        'vend_refri': '5',
    },
    '7': {
        'rep_refri': '8',
        'check_refri': '7',
        'vend_refri': '6',
    },
    '8': {
        'rep_refri': '9',
        'check_refri': '8',
        'vend_refri': '7',
    },
    '9': {
        'rep_refri': '10',
        'check_refri': '9',
        'vend_refri': '8',
    },
    '10': {
        'vend_refri': '9',
        'check_refri': '10',
    }
}

# ----- estados finais do buffer agua --------
Fbp = ["0", "1", "2", "3", "4", "5"]