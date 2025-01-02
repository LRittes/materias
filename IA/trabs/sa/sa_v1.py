import random

def getData(path):
    data = []
    with open(path, "r") as f:
        res = f.readlines()
    info = res[7].strip().split(' ')
    data.append(int(info[2]))
    data.append(int(info[4]))

    for line in res[8:data[1] + 8]:
        v1, v2, v3, _  = line.strip().split('\n')[0].split(' ')
        
        data.append([int(v1),int(v2),int(v3)])
    return data

def genFirstSolution(amountVars):
    firstSol = []
    for n in range(amountVars):
        firstSol.append(random.randint(0,1))
    
    return firstSol

def fo(sol, claus):
    res = []
    for clau in claus:
        resClau = []
        for c in clau:
            if c < 0:
                resClau.append(bool(not sol[abs(c) - 1]))
            else:
                resClau.append(bool(sol[c - 1]))
        res.append(resClau)
    
    amountTrue = 0
    for r in res:
        if r.count(True) > 0:
            amountTrue += 1

    return amountTrue

# def sa(f,n,)

# print(getData("uf20-01.cnf"))
# getData("uf20-01.cnf")
# print(genFirstSolution(getData("uf20-01.cnf")[0]))

# print(fo([1,1,1], [[-1,-2,-3],[-1,-2,3]]))
