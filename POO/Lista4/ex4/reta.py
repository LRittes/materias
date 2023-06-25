from ponto2D import Ponto2D
class Reta:
    def __init__(self, a, b):
        self.a = a
        self.b = b

    def f(self, x):
        return self.a*x + self.b

    def intercepta(self, reta):
        
        coefAngularA = (self.f(5) - self.f(1))/(5-1)
        coefAngularB = (reta.f(5) - reta.f(1))/(5-1)
        if coefAngularA == coefAngularB:
            
            if reta.f(1) == self.f(1):
                return True
            else:
                False
        else:
            return True

    def estaNaReta(self, ponto):
        if self.f(ponto.x) == ponto.y:
            return True
        else:
            return False