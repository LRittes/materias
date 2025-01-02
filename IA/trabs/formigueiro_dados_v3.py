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


def getData():
    data = []
    with open("./dataset_4rot.txt", "r") as file:
        res = file.readlines()
    for line in res:
        x, y, rot = line.split('\t')
        data.append({'x': float(x.replace(',', '.')), 'y': float(
            y.replace(',', '.')), 'rot': int(rot[0])})
    return data


def generate_unique_pairs(n, lower_bound, upper_bound):
    ritems = []
    seen_pairs = set()

    while len(seen_pairs) < n:
        # Gera um par de valores aleatórios dentro dos limites especificados
        pair = (random.randint(lower_bound, upper_bound),
                random.randint(lower_bound, upper_bound))

        # Verifica se o par gerado não está na lista de pares já vistos
        if pair not in seen_pairs:
            seen_pairs.add(pair)
            ritems.append(pair)

    return ritems


def genVasilha(size, amountItem, amountAnt):
    global ants
    global items

    vasilha = [[symbEmpty for _ in range(size)] for _ in range(size)]
    # uniquePair = generate_unique_pairs(amountItem, 0, 9)
    data = getData()
    nData = []

    if amountItem == 400:
        nData = data
    else:
        for n in range(amountItem):
            nData.append(data[random.randint(0, len(data) - 1)])

    locItems = random.sample(
        [(i, j) for i in range(size) for j in range(size)], amountItem)

    for idx, pos in enumerate(locItems):
        items[pos] = (nData[idx]['x'], nData[idx]['y'], nData[idx]['rot'])

    for pos in list(items.keys()):
        vasilha[pos[0]][pos[1]] = items[pos]

    ant_positions = random.sample([(i, j) for i in range(size) for j in range(
        size) if (i, j) not in locItems], amountAnt)
    for pos in ant_positions:
        vasilha[pos[0]][pos[1]] = symbAnt
        ants.append(Ant(pos))  # Convert tuple to list

    return vasilha


vasilha = genVasilha(64, 400, 100)


def drawVasilha(remove=False):
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
    return math.sqrt(math.pow(currentCell[0] - anotherCell[0], 2) + math.pow(currentCell[1] - anotherCell[1], 2))


def f(amountData, listCellAround, currentCell, alfa):
    if amountData == 0:
        return 0

    sumED = 0
    for cell in listCellAround:
        sumED += (1 - (euclidianDistance(currentCell, cell) / alfa))

    result = 1/math.pow(amountData, 2) * sumED

    if result > 0:
        return result

    return 0


def pickItem(ant: Ant, item, k1, alfa):
    global items

    itemsAround = []
    cells = getCellAround(ant)
    for cell in cells:
        if type(vasilha[cell[0]][cell[1]]) is tuple:
            itemsAround.append(vasilha[cell[0]][cell[1]])

    problaly = math.pow(
        k1/(k1 + f(len(itemsAround), itemsAround, item, alfa)), 2)

    pick = random.uniform(k1/(k1+1), 1)

    # print('prob ', problaly)
    # print('pick ', pick)

    if pick <= problaly:
        ant.hasItem = True

        try:
            item = items.pop(tuple(ant.loc))
            ant.item = item
        except:
            print(
                f"Warning: Tried to remove {ant.loc} from locItems, but it wasn't there.")

    return ant.hasItem


def dropItem(ant: Ant,  item: tuple, k2, alfa):
    itemsAround = []
    cells = getCellAround(ant)
    for cell in cells:
        if type(vasilha[cell[0]][cell[1]]) is tuple:
            itemsAround.append(vasilha[cell[0]][cell[1]])

    resF = f(len(itemsAround), itemsAround, item, alfa)

    problaly = math.pow(resF/(k2 + resF), 2)

    drop = random.uniform(0, (1/(k2+1)))

    # print('prob ', problaly)
    # print('drop ', drop)

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
        lin = ant.loc[1] + random.randrange(-1, 2, 2)
    return lin


def moveH(ant: Ant):
    if ant.loc[0] == 0:
        col = ant.loc[0] + 1
    elif ant.loc[0] == len(vasilha) - 1:
        col = ant.loc[0] - 1
    else:
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


# def getCellAround(ant: Ant):
#     cellsAround = []
#     rows = len(vasilha)
#     cols = len(vasilha[0])
#     x, y = ant.loc

#     for i in range(x - raio, x + raio + 1):
#         for j in range(y - raio, y + raio + 1):
#             if [i, j] != [x, y] and 0 <= i < rows and 0 <= j < cols:
#                 cellsAround.append([i, j])
#     return cellsAround

def getCellAround(ant: Ant):
    cellsAround = []
    rows = len(vasilha)
    cols = len(vasilha[0])
    x, y = ant.loc

    for i in range(x - raio, x + raio + 1):
        for j in range(y - raio, y + raio + 1):
            if [i, j] != [x, y]:
                # Ajustar as coordenadas para considerar o wrap-around
                wrapped_i = i % rows
                wrapped_j = j % cols
                cellsAround.append([wrapped_i, wrapped_j])

    return cellsAround


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
        # line += '\n'
        # printVasi += line[1:]
        file.write(line[1:] + '\n')
    file.write('\n')
    file.write('-' * len(vasilha) * 2)
    file.write('\n')

    # print(printVasi)

    # for row in vasilha:
    # print()


def initAnt(ant: Ant, k1, k2, alfa, cod):
    epoch = 0
    maxEpoch = 200_000
    while epoch < maxEpoch or (ant.hasItem and epoch >= maxEpoch):
        newLocAnt = moveAnt(ant)
        drawVasilha()

        cellHasItem = checkCellForItem(newLocAnt)

        lock.acquire()
        if cellHasItem and not newLocAnt.hasItem and epoch < maxEpoch:
            pickItem(newLocAnt, items[tuple(newLocAnt.loc)], k1, alfa)
        elif newLocAnt.hasItem and not cellHasItem:
            dropItem(newLocAnt, newLocAnt.item, k2, alfa)
        drawVasilha()
        lock.release()

        if epoch % 1_000 == 0:
            print(f'ant {cod}: {epoch}')
        
        epoch += 1
    drawVasilha(True)


if __name__ == '__main__':
    with open("./formigasTexto.txt", "w") as file:

        # print("Initial state:")
        print_vasilha(vasilha, file)

    # initAnt(ants[0],0.01,0.9,40)
    threads = []
    for idx, ant in enumerate(ants):
        thread = Thread(target=initAnt, args=(ant, 0.0001, 0.001, 10, idx))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    with open("./formigasTexto.txt", "a") as file:

        # print("Final state:")
        print_vasilha(vasilha, file)
    # data = getData()

    # vasilha = [[(data[0]['x'],data[0]['y'],data[0]['rot']),(data[1]['x'],data[1]['y'],data[1]['rot']),' '],
    #         #    (data[2]['x'],data[2]['y'],data[2]['rot'])],
    #            ['x',' ',' '],
    #             # (data[3]['x'],data[3]['y'],data[3]['rot']),(data[4]['x'],data[4]['y'],data[4]['rot'])],
    #         #    [(data[5]['x'],data[5]['y'],data[5]['rot']),(data[6]['x'],data[6]['y'],data[6]['rot']),(data[7]['x'],data[7]['y'],data[7]['rot'])]]
    #         [' ',' ',' ']]
    # print(vasilha)
    # ants = [Ant([1,0])]
    # k1 = 0.0001
    # k2 = 0.001
    # alfa = 10
    # item = (data[98]['x'],data[98]['y'],data[98]['rot'])
    # # pickItem(ants[0],item, k1,alfa)
    # dropItem(ants[0],item, k2,alfa)

    # print(getCellAround(ants[0]))
