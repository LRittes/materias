class Data:

    def __init__(self):
        self.__dia = 0
        self.__mes = 0
        self.__ano = 0

    def getDia(self):
        return self.__dia

    def setDia(self, dia: int):
        self.__dia = dia

    def getMes(self):
        return self.__mes

    def setMes(self, mes: int):
        self.__mes = mes

    def getAno(self):
        return self.__ano

    def setAno(self, ano: int):
        self.__ano = ano

    def __str__(self):
        return 'Dia: {} \n Mes: {} \n Ano: {}'.format(self.__dia, self.__mes,self.__ano)