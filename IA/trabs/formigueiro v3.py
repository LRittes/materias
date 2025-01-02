import random
import math

import concurrent.futures
from threading import Thread

class Ant():
    def __init__(self,loc):
        self.loc = loc
        self.hasItem = False

# Fazer paralelismo para cada formiga

symbAnt = 'x'
ants = []
# [Ant([1,0]),Ant([1,1]),Ant([1,2]),Ant([0,2]),Ant([2,1])]
raio = 1
symbItem = 1
symbEmpty = 0
locItems = []
# [[0,0],[0,1],[2,0],[2,3],[3,0],[4,2],[4,3]]

def genVasilha(size, amountItem, amountAnt):
    global locItems
    global ants
    
    vasilha = [[symbEmpty for _ in range(size)] for _ in range(size)]
    
    item_positions = random.sample([(i, j) for i in range(size) for j in range(size)], amountItem)
    for pos in item_positions:
        vasilha[pos[0]][pos[1]] = symbItem
        locItems.append(pos)

    ant_positions = random.sample([(i, j) for i in range(size) for j in range(size) if (i, j) not in item_positions], amountAnt)
    for pos in ant_positions:
        vasilha[pos[0]][pos[1]] = symbAnt
        ants.append(Ant(pos))
    
    return vasilha
    
vasilha = genVasilha(5,10,1)
# [[1,1,'x',0,0],
#            ['x','x','x',0,0],
#            [1,'x',0,1,0],
#            [1,0,0,0,0],
#            [0,0,1,1,0],
#            ]


def drawVasilha():
    global vasilha
    lenVasilha = len(vasilha)
    vasilha = [[symbEmpty for _ in range(lenVasilha)] for _ in range(lenVasilha)]

    for item in locItems:
        vasilha[item[0]][item[1]] = symbItem
    
    for ant in ants:
        vasilha[ant.loc[0]][ant.loc[1]] = symbAnt

def pickItem(ant: Ant):
    amountItems = 0
    cells = getCellAround(ant)
    for cell in cells:
        if vasilha[cell[0]][cell[1]] == symbItem:
            amountItems += 1

    pick = random.random() * 100

    if pick <= math.ceil((1 - (amountItems / len(cells))) * 100) :
        ant.hasItem = True
        locItems.remove(ant.loc)
    
    return ant.hasItem

def dropItem(ant):
    amountItems = 0
    cells = getCellAround(ant)
    for cell in cells:
        if vasilha[cell[0]][cell[1]] == symbItem:
            amountItems += 1

    drop = random.random() * 100

    if drop <= math.ceil((amountItems / len(cells)) * 100) :
        ant.hasItem = False
        locItems.append(ant.loc)
    
    return ant.hasItem

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

    while(checkCellForAnt([newLin,newCol])):
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

def checkCellForAnt(loc):
    for ant in ants:
        if ant.loc == loc:
            return True
    return False

def checkCellForItem(ant: Ant):
    for locItem in locItems:
        if locItem[0] == ant.loc[0] and locItem[1] == ant.loc[1]:
            return True
    return False 

def getCellAround(ant: Ant):
    cellsAround = []
    rows = len(vasilha)
    cols = len(vasilha[0])
    x, y = ant.loc

    for i in range(x - raio, x + raio + 1):
        for j in range(y - raio, y + raio + 1):
            if [i, j] != [x, y] and 0 <= i < rows and 0 <= j < cols:
                cellsAround.append([i, j])

    return cellsAround

def initAnt(ant):
    for epoch in range(100):
        
        newLocAnt = moveAnt(ant)
        drawVasilha()        

        cellHasItem = checkCellForItem(newLocAnt) 
        if cellHasItem and ant.hasItem == False:
            pickItem(newLocAnt)
        else:
            if newLocAnt.hasItem == True and not cellHasItem:
                dropItem(newLocAnt)
        drawVasilha()

        # for loc in vasilha:
        #     print(loc)
        # print(20 * "-")

if __name__ == '__main__':
    for loc in vasilha:
        print(loc)
    print(20 * "-")

    # with concurrent.futures.ProcessPoolExecutor() as executer:
    #     executer.map(initAnt, ants)
    
    for ant in ants:
        Thread(target=initAnt,kwargs={'ant': ant}).start()

    print(20 * "-")
    for loc in vasilha:
        print(loc)