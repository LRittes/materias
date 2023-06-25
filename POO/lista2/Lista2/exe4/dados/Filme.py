class Filme:
    def __init__(self):
        self.__title: str = ""
        self.__age: int = 0
        self.__classif: str = " "
        self.__nota: float = 0.0

    def getTitulo(self)->str:
        return self.__title
    
    def setTitulo(self, title: str):
        self.__title = title
    
    def getAno(self)->int:
        return self.__age
    
    def setAno(self, age: int):
        self.__age = age
    
    def getClassificacao(self)->str:
        return self.__classif
    
    def setClassificacao(self, classif: str):
        self.__classif = classif

    def getNota(self)->float:
        return self.__nota
    
    def setNota(self, nota: float):
        self.__nota = nota

    def __str__(self) -> str:
        return """Titulo: {}, \nAno: {}, \nClassificação: {}, \nNota: {}""".format(self.__title, self.__age, self.__classif, self.__nota)
    