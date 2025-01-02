def dias(mes, bissexto = False):
    if mes == 2:
        if bissexto:
            return 29
        return 28
    
    if mes in [1,3,5,7,8,10,12]:
        return 31
    
    return 30

print(dias(12,True))