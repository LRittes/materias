class Filme:

    def __init__(self, titulo = None, ano = None, classificacao = None, nota = None):
        self.titulo = titulo
        self.ano = ano
        self.classificacao = classificacao
        self.nota = nota
        pass

    def __str__(self):
        return f'Titulo: {self.titulo}\nAno: {self.ano}\nClassificacao: {self.classificacao}\nNota: {self.nota}'
        pass

    def getTitulo(self):
        return self.titulo
    def getAno(self):
        return self.ano
    def getClassificacao(self):
        return self.classificacao
    def getNota(self):
        return self.nota

    def setitulo(self, titulo):
        self.titulo
    def setAno(self, ano):
        self.ano = ano    
    def setClassificacao(self, classificao):
        self.classificacao = classificao
    def setNota(self, nota):
        self.nota = nota
