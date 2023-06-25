class Barco:
    def __init__(self, marca, valor, comprimento):
        self.marca = marca
        self.valor = valor
        self.comprimento = comprimento
    
    def __str__(self):
        print(self.marca, self.valor, self.comprimento)

