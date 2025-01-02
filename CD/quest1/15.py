class Veiculo:
    def __init__(self, nome='', valor=0) -> None:
        self.nome = nome
        self.valor = valor
    
    def __str__(self) -> str:
        return self.nome
    
    def get_valor(self):
        return self.valor

class Carro(Veiculo):
    def __init__(self, portas, nome='', valor=0) -> None:
        super().__init__(nome, valor)
        self.portas = portas
    
    def get_portas(self):
        return self.portas

c2 = Carro(4, "Ka")
print(c2)
print(c2.get_valor())
print(c2.get_portas())

print("get_valor" in Veiculo.__dict__)
print("get_valor" in Carro.__dict__)
print("__str__" in Veiculo.__dict__)
print("__str__" in Carro.__dict__)
print("get_portas" in Veiculo.__dict__)
print("get_portas" in Carro.__dict__)