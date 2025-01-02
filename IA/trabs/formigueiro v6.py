import random
import math
from threading import Thread, Lock

class Ant():
    def __init__(self, loc):
        self.loc = loc
        self.hasItem = False
        self.item = ()

symbAnt = 'x'
symbAntItem = 'x1'
ants = []
raio = 1
symbItem = 1
symbEmpty = ' '
items = {}
lock = Lock()


def generate_unique_pairs(n, lower_bound, upper_bound):
    ritems = []
    seen_pairs = set()
    
    while len(seen_pairs) < n:
        # Gera um par de valores aleatórios dentro dos limites especificados
        pair = (random.randint(lower_bound, upper_bound), random.randint(lower_bound, upper_bound))
        
        # Verifica se o par gerado não está na lista de pares já vistos
        if pair not in seen_pairs:
            seen_pairs.add(pair)
            ritems.append(pair)

    return ritems

def genVasilha(size, amountItem, amountAnt):
    global ants
    global items

    vasilha = [[symbEmpty for _ in range(size)] for _ in range(size)]
    uniquePair = generate_unique_pairs(amountItem,0,9)
    
    locItems = random.sample(
        [(i, j) for i in range(size) for j in range(size)], amountItem)
    
    for idx, pos in enumerate(locItems):
        items[pos] = uniquePair[idx]
    
    for pos in list(items.keys()):
        vasilha[pos[0]][pos[1]] = items[pos]

    ant_positions = random.sample([(i, j) for i in range(size) for j in range(
        size) if (i, j) not in locItems], amountAnt)
    for pos in ant_positions:
        vasilha[pos[0]][pos[1]] = symbAnt
        ants.append(Ant(pos))  # Convert tuple to list

    return vasilha

vasilha = genVasilha(10,30,10)

def drawVasilha(remove = False):
    global vasilha
    global items
    global ants

    lenVasilha = len(vasilha)
    vasilha = [[symbEmpty for _ in range(lenVasilha)]
               for _ in range(lenVasilha)]

    for pos in list(items.keys()):
        vasilha[pos[0]][pos[1]] = items[pos]

    if not remove:
        for ant in ants:
            vasilha[ant.loc[0]][ant.loc[1]] = symbAnt

def euclidianDistance(currentCell, anotherCell):
    return math.sqrt(math.pow(currentCell[0] - anotherCell[0],2) + math.pow(currentCell[1] - anotherCell[1],2))

def f(amountData, listCellAround, currentCell, alfa):
    sumED = 0
    for cell in listCellAround:
        sumED += (1 - (euclidianDistance(currentCell,cell) / alfa))
    
    result = 1/math.pow(amountData,2) * sumED
    
    if result > 0:
        return result
    
    return 0

def pickItem(ant: Ant, item, k1,alfa):
    global items
    
    itemsAround = []
    cells = getCellAround(ant)
    for cell in cells:
        if type(vasilha[cell[0]][cell[1]]) is tuple :
            itemsAround.append(vasilha[cell[0]][cell[1]])

    problaly = math.pow(k1/(k1 + f(len(cells),cells,item,alfa)),2) 
    
    pick = random.random() 
    

    if pick <= problaly:
        ant.hasItem = True
        
        try: 
            item = items.pop(tuple(ant.loc))
            ant.item = item
        except:
            print(f"Warning: Tried to remove {ant.loc} from locItems, but it wasn't there.")

    return ant.hasItem

def dropItem(ant: Ant,  item: tuple, k2,alfa):
    itemsAround = []
    cells = getCellAround(ant)
    for cell in cells:
        if type(vasilha[cell[0]][cell[1]]) is tuple:
            itemsAround.append(vasilha[cell[0]][cell[1]])

    resF = f(len(cells),cells,item,alfa)
    
    problaly = math.pow(resF/(k2 + resF),2) 
    
    drop = random.random()
    
    if drop <= problaly:
        ant.hasItem = False
        items[tuple(ant.loc)] = item
        ant.item = ()

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
    try:
        items[tuple(ant.loc)]
        return True
    except:
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
    # Calcula a largura máxima de uma célula (considerando o conteúdo mais longo)
    max_width = max(len(str(cell)) for row in vasilha for cell in row)
    
    for row in vasilha:
        print(' '.join([f'{str(cell):^{max_width}}' for cell in row]))
    print()

def initAnt(ant: Ant,k1,k2,alfa):
    epoch = 0
    maxEpoch = 500
    while epoch < maxEpoch or (ant.hasItem and epoch >= maxEpoch):
        # with lock:
        newLocAnt = moveAnt(ant)
        drawVasilha()

        lock.acquire()
        cellHasItem = checkCellForItem(newLocAnt)
        if cellHasItem and not newLocAnt.hasItem and epoch < maxEpoch:
            # print('current Item ', items[locItems.index(newLocAnt.loc)])
            pickItem(newLocAnt,items[tuple(newLocAnt.loc)],k1,alfa)
        elif newLocAnt.hasItem and not cellHasItem:
            # print('current Item ', items[locItems.index(newLocAnt.loc)])
            dropItem(newLocAnt,newLocAnt.item,k2,alfa)
        drawVasilha()
        lock.release()
        # print('-'*50)
        # print_vasilha(vasilha)
        # print('-'*50)
        # print(items)
        epoch += 1
    drawVasilha(True)


if __name__ == '__main__':
    print("Initial state:")
    print_vasilha(vasilha)
    # print(ants)
    # print(items)
    
    # initAnt(ants[0],0.6,0.6,30)
    threads = []
    for ant in ants:
        thread = Thread(target=initAnt, args=(ant,0.3,0.6,11.8))
        threads.append(thread)
        thread.start()


    for thread in threads:
        thread.join()
    # print(len(locItems))

        

    print("Final state:")
    print_vasilha(vasilha)
    # print(items)
    
    # pickItem(Ant([1,1]),[6,1],1,30)
    # print(locItems)
    # vasilha = genVasilha(3,3,1)
    # print_vasilha(vasilha)
    # print(locItems)
    # print(items)
    # print(items[locItems.index(locItems[2])])