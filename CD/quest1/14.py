class Racional:
    def __init__(self, num, den): 
        self.num = num
        self.den = den
        
    def __str__(self):
        return f'{self.num}/{self.den}'
    
    def get_denominador(self):
        return self.den
    
    def get_numerador(self):
        return self.num

    def __int__(self):
        return int(self.num / self.den)

    def __float__(self):
        return float(self.num / self.den)

    def __add__(self, obj2):
        if isinstance(obj2, Racional):
            num = self.num * obj2.den + self.den * obj2.num
            den = self.den * obj2.den
            return Racional(num, den)
        elif isinstance(obj2, (int, float)):
            return self.num / self.den + obj2
        else:
            raise TypeError("Operação não suportada")

    def __eq__(self, obj2):
        return self.num == obj2.num and obj2.den == self.den

    def __ne__(self, obj2):
        return not self.__eq__(obj2)

    def __lt__(self, obj2):
        return self.num / self.den  <  obj2.num / obj2.den

    def __gt__(self, obj2):
        return self.num / self.den  >  obj2.num / obj2.den

    def __le__(self, obj2):
        return self.num / self.den  <=  obj2.num / obj2.den

    def __ge__(self, obj2):
        return self.num / self.den  >=  obj2.num / obj2.den

a = Racional(1, 2)
b = Racional(3, 4)
print(a+b)
print(a < b)
print(a.get_numerador())
print(a.get_denominador())
print(a == b)
print(a != b)
a = Racional(6, 2)
print(int(a))