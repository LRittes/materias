import random
import math

vasilha = [[1,1,0,0,0],
           ['x',1,0,0,0],
           [1,0,0,1,0],
           [1,0,0,0,0],
           [0,0,1,1,0],
           ]

class Ant():
    def __init__(self,loc):
        self.loc = loc
        self.hasItem = False

ant = 'x'
ants = [Ant([1,0])]
raio = 1
item = 1
empty = 0
locItems = [[0,0],[2,0],[0,1],[2,3],[3,0],[4,2],[4,3]]

def pickItem(ant: Ant):
    amountItems = 0
    cells = getCellAround(ant)
    for cell in cells:
        if vasilha[cell[0]][cell[1]] == 1:
            amountItems += 1

    pick = random.random() * 100
    print(random.randint(0, math.ceil((1 - (amountItems / len(cells))) * 100)))
    # if pick <= random.randint(0, math.ceil((1 - (amountItems / len(cells))) * 100)) :
    #     ant.hasItem = True
    # else:
    #     ant.hasItem = False
    return ant.hasItem

def dropItem(a):
    pass

def moveV(ant: Ant):
    if ant.loc[1] == 0:
        lin = ant.loc[1] + 1
    elif ant.loc[1] == len(vasilha) - 1: 
        lin = ant.loc[1] - 1
    else:
        # sortea entre -1 e 1
        lin = ant.loc[1] + random.randrange(-1, 2, 2)
    return lin

def moveH(ant: Ant):
    if ant.loc[0] == 0:
        col = ant.loc[0] + 1
    elif ant.loc[0] == len(vasilha) - 1: 
        col = ant.loc[0] - 1
    else:
        # sortea entre -1 e 1
        col = ant.loc[0] + random.randrange(-1, 2, 2)

    return col

def moveAnt(ant: Ant):
    if random.randint(0,1) == 0:
        x = random.randint(0,1)
        if x == 0:
            newLin = ant.loc[0]
            newCol = moveV(ant)
        else:
            newLin = moveH(ant)
            newCol = ant.loc[1]
    else:
        y = random.randint(0,1)
        if y == 0:
            newCol = ant.loc[1]
            newLin = moveH(ant)
        else:
            newCol = moveV(ant)
            newLin = ant.loc[0]

    ant.loc = [newLin,newCol]
    
    return ant

def checkCell(ant: Ant):
    for locItem in locItems:
        if locItem[0] == ant.loc[0] and locItem[1] == ant.loc[1]:
            return True
    return False 

def getCellAround(ant: Ant):
    cellsAround = []
    rows = len(vasilha)
    cols = len(vasilha[0])
    x, y = ant.loc

    for i in range(x - 1, x + 2):
        for j in range(y - 1, y + 2):
            if [i, j] != [x, y] and 0 <= i < rows and 0 <= j < cols:
                cellsAround.append([i, j])

    return cellsAround

for ant in ants:
    
    vasilha[ant.loc[0]][ant.loc[1]] = 0
    
    newLocAnt = moveAnt(ant)
    vasilha[newLocAnt.loc[0]][newLocAnt.loc[1]] = 'x'

    for i in range(10):
        if checkCell(newLocAnt):
            pickItem(newLocAnt)

for loc in vasilha:
    print(loc)