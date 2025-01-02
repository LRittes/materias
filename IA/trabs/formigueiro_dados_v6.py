import random
import math
import time
from multiprocessing import Process, Manager, Lock

class Ant:
    def __init__(self, loc):
        self.loc = loc
        self.hasItem = False
        self.item = ()

symbAnt = 'x'
raio = 1
symbItem = 1
symbEmpty = ' '

size = 4
amountItems = 6
amountAnts = 2
k1 = 0.01
k2 = 0.0001
alfa = 10
maxEpoch = 5

def getData():
    data = []
    with open("./dataset_4rot.txt", "r") as file:
        res = file.readlines()
    for line in res:
        x, y, rot = line.split('\t')
        data.append({'x': float(x.replace(',', '.')), 'y': float(y.replace(',', '.')), 'rot': int(rot.replace('\n', ''))})
    return data

def genVasilha(size, amountItem, amountAnt, items, ants):
    vasilha = [[symbEmpty for _ in range(size)] for _ in range(size)]
    data = getData()
    nData = []

    if amountItem == 600:
        nData = data
    else:
        for n in range(amountItem):
            nData.append(data[random.randint(0, len(data) - 1)])

    locItems = random.sample([(i, j) for i in range(size) for j in range(size)], amountItem)

    for idx, pos in enumerate(locItems):
        items[pos] = (nData[idx]['x'], nData[idx]['y'], nData[idx]['rot'])

    for pos in list(items.keys()):
        vasilha[pos[0]][pos[1]] = items[pos]

    ant_positions = random.sample([(i, j) for i in range(size) for j in range(size) if (i, j) not in locItems], amountAnt)
    for pos in ant_positions:
        vasilha[pos[0]][pos[1]] = symbAnt
        ants.append(Ant(pos))

    return vasilha

def drawVasilha( remove=False):
    global vasilha, items, ants
    
    lenVasilha = len(vasilha)
    for i in range(lenVasilha):
        for j in range(lenVasilha):
            vasilha[i][j] = symbEmpty

    for pos in list(items.keys()):
        vasilha[pos[0]][pos[1]] = items[pos]

    if not remove:
        for ant in ants:
            vasilha[ant.loc[0]][ant.loc[1]] = symbAnt

def euclidianDistance(currentCell, anotherCell):
    return math.sqrt(math.pow(currentCell[0] - anotherCell[0], 2) + math.pow(currentCell[1] - anotherCell[1], 2))

def f(amountData, listCellAround, currentCell, alfa):
    if amountData == 0:
        return 0

    sumED = 0
    for cell in listCellAround:
        sumED += (1 - (euclidianDistance(currentCell, cell) / alfa))

    result = 1 / math.pow(amountData, 2) * sumED

    if result > 0:
        return result

    return 0

def pickItem(ant, items, vasilha, k1, alfa):
    itemsAround = []
    cells = getCellAround(ant, vasilha)
    for cell in cells:
        if type(vasilha[cell[0]][cell[1]]) is tuple:
            itemsAround.append(vasilha[cell[0]][cell[1]])

    problaly = math.pow(k1 / (k1 + f(len(itemsAround), itemsAround, items[tuple(ant.loc)], alfa)), 2)
    pick = random.uniform(k1 / (k1 + 1), 1)

    if pick <= problaly:
        ant.hasItem = True
        try:
            ant.item = items.pop(tuple(ant.loc))
        except KeyError:
            print(f"Warning: Tried to remove {ant.loc} from items, but it wasn't there.")

    return ant.hasItem

def dropItem(ant, items, vasilha, k2, alfa):
    itemsAround = []
    cells = getCellAround(ant, vasilha)
    for cell in cells:
        if type(vasilha[cell[0]][cell[1]]) is tuple:
            itemsAround.append(vasilha[cell[0]][cell[1]])

    resF = f(len(itemsAround), itemsAround, ant.item, alfa)
    problaly = math.pow(resF / (k2 + resF), 2)
    drop = random.uniform(0, (1 / (k2 + 1)))

    if drop <= problaly:
        ant.hasItem = False
        items[tuple(ant.loc)] = ant.item
        ant.item = ()

    return ant.hasItem

def moveV(ant):
    if ant.loc[1] == 0:
        lin = ant.loc[1] + 1
    elif ant.loc[1] == len(vasilha) - 1:
        lin = ant.loc[1] - 1
    else:
        lin = ant.loc[1] + random.randrange(-1, 2, 2)
    return lin

def moveH(ant):
    if ant.loc[0] == 0:
        col = ant.loc[0] + 1
    elif ant.loc[0] == len(vasilha) - 1:
        col = ant.loc[0] - 1
    else:
        col = ant.loc[0] + random.randrange(-1, 2, 2)
    return col

def moveAnt(ant):
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

    while checkCellForAnt([newLin, newCol], ants):
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

def checkCellForAnt(loc, ants):
    for ant in ants:
        if ant.loc == loc:
            return True
    return False

def checkCellForItem(ant, items):
    try:
        items[tuple(ant.loc)]
        return True
    except KeyError:
        return False

def getCellAround(ant, vasilha):
    cellsAround = []
    rows = len(vasilha)
    cols = len(vasilha[0])
    x, y = ant.loc

    for i in range(x - raio, x + raio + 1):
        for j in range(y - raio, y + raio + 1):
            if [i, j] != [x, y]:
                wrapped_i = i % rows
                wrapped_j = j % cols
                cellsAround.append([wrapped_i, wrapped_j])

    return cellsAround

def initAnt(ant, items, ants, lock, k1, k2, alfa, cod):
    global vasilha
    epoch = 0
    start = time.time()
    while epoch < maxEpoch:
        # or (ant.hasItem and epoch >= maxEpoch):
        newLocAnt = moveAnt(ant)
        drawVasilha()

        lock.acquire()
        cellHasItem = checkCellForItem(newLocAnt, items)

        if cellHasItem and not newLocAnt.hasItem and epoch < maxEpoch:
            pickItem(newLocAnt, items, vasilha, k1, alfa)
        elif newLocAnt.hasItem and not cellHasItem:
            dropItem(newLocAnt, items, vasilha, k2, alfa)
        
        # Atualizar diretamente o estado da vasilha
        # ant.loc = newLocAnt.loc
        
        print('-'*10)
        print_vasilha(vasilha)
        lock.release()
        
        # if epoch % 50 == 0:
        #     end = time.time()
            
        print(f'ant {cod}, loc {ant.loc} hasItem {ant.hasItem}, epoch {epoch}')
        # , time (seg) {end - start}')

        epoch += 1

def print_vasilha(vasilha, file=None):
    max_width = max(len(str(cell)) for row in vasilha for cell in row)
    printVasi = ''

    for row in vasilha:
        line = ''
        for ele in row:
            if type(ele) is tuple:
                line += f' {ele[2]}'
            else:
                line += f' {ele}'
        printVasi += line[1:] + '\n'
        
    #     file.write(line[1:] + '\n')
    # file.write('\n')
    # file.write('-' * len(vasilha) * 2)
    # file.write('\n')
    print(printVasi)

if __name__ == '__main__':
    with Manager() as manager:
        items = manager.dict()
        ants = manager.list()
        vasilha = manager.list(genVasilha(size, amountItems, amountAnts, items, ants))
        
        lock = Lock()

        # with open(f'./{size}_{amountItems}_{amountAnts}_{k1}_{k2}_{alfa}_{maxEpoch}.txt', "w") as file:
        #     print_vasilha(vasilha, file)

        process = []
        for idx, ant in enumerate(ants):
            procs = Process(target=initAnt, args=(ant, items, ants, lock, k1, k2, alfa, idx))
            process.append(procs)
            procs.start()

        for procs in process:
            procs.join()

        # with open(f'./{size}_{amountItems}_{amountAnts}_{k1}_{k2}_{alfa}_{maxEpoch}.txt', "a") as file:
        #     print_vasilha(vasilha, file)