import random
import math
import concurrent.futures
from threading import Thread

class Ant():
    def __init__(self, loc):
        self.loc = loc
        self.hasItem = False

symbAnt = 'x'
ants = []
raio = 1
symbItem = 1
symbEmpty = 0
locItems = []

def genVasilha(size, amountItem, amountAnt):
    global locItems
    global ants

    vasilha = [[symbEmpty for _ in range(size)] for _ in range(size)]

    item_positions = random.sample(
        [(i, j) for i in range(size) for j in range(size)], amountItem)
    for pos in item_positions:
        vasilha[pos[0]][pos[1]] = symbItem
        locItems.append(list(pos))  # Convert tuple to list

    ant_positions = random.sample([(i, j) for i in range(size) for j in range(
        size) if (i, j) not in item_positions], amountAnt)
    for pos in ant_positions:
        vasilha[pos[0]][pos[1]] = symbAnt
        ants.append(Ant(list(pos)))  # Convert tuple to list

    return vasilha

vasilha = genVasilha(7, 30, 5)

def drawVasilha():
    global vasilha
    lenVasilha = len(vasilha)
    vasilha = [[symbEmpty for _ in range(lenVasilha)]
               for _ in range(lenVasilha)]

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

    if pick <= math.ceil((1 - (amountItems / len(cells))) * 100):
        ant.hasItem = True
        if ant.loc in locItems:  # Check if the item is in locItems before removing
            locItems.remove(ant.loc)
        else:
            print(f"Warning: Tried to remove {ant.loc} from locItems, but it wasn't there.")

    return ant.hasItem

def dropItem(ant):
    amountItems = 0
    cells = getCellAround(ant)
    for cell in cells:
        if vasilha[cell[0]][cell[1]] == symbItem:
            amountItems += 1

    drop = random.random() * 100

    if drop <= math.ceil((amountItems / len(cells)) * 100):
        ant.hasItem = False
        locItems.append(ant.loc[:])  # Append a copy of the location

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
    if random.randint(0, 1) == 0:
        x = random.randint(0, 1)
        if x == 0:
            newLin = ant.loc[0]
            newCol = moveV(ant)
        else:
            newLin = moveH(ant)
            newCol = ant.loc[1]
    else:
        y = random.randint(0, 1)
        if y == 0:
            newCol = ant.loc[1]
            newLin = moveH(ant)
        else:
            newCol = moveV(ant)
            newLin = ant.loc[0]

    while (checkCellForAnt([newLin, newCol])):
        if random.randint(0, 1) == 0:
            x = random.randint(0, 1)
            if x == 0:
                newLin = ant.loc[0]
                newCol = moveV(ant)
            else:
                newLin = moveH(ant)
                newCol = ant.loc[1]
        else:
            y = random.randint(0, 1)
            if y == 0:
                newCol = ant.loc[1]
                newLin = moveH(ant)
            else:
                newCol = moveV(ant)
                newLin = ant.loc[0]

    ant.loc = [newLin, newCol]

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

def print_vasilha(vasilha):
    for row in vasilha:
        print([str(cell) for cell in row])
    print() 

def initAnt(ant):
    for epoch in range(100):
        newLocAnt = moveAnt(ant)
        drawVasilha()

        cellHasItem = checkCellForItem(newLocAnt)
        if cellHasItem and not newLocAnt.hasItem:
            pickItem(newLocAnt)
        elif newLocAnt.hasItem and not cellHasItem:
            dropItem(newLocAnt)
        drawVasilha()

if __name__ == '__main__':
    print("Initial state:")
    print_vasilha(vasilha)

    threads = []
    for ant in ants:
        thread = Thread(target=initAnt, kwargs={'ant': ant})
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    print("Final state:")
    print_vasilha(vasilha)