from dados.Cliente import Cliente
from dados.Data import Data
from dados.Endereco import Endereco
from dados.Veiculo import Veiculo
from negocio.Negocio import Negocio

class Principal:

    sistema = Negocio()
    cliente1 = Cliente()
    endereco1 = Endereco()
    veiculo1 = Veiculo()
    data1 = Data()

    cliente1.setNome("Joao")
    cliente1.setCpf("1239871")

    endereco1.setRua("Joao Colin")
    endereco1.setNumero(123)
    endereco1.setBairro("Gloria")
    endereco1.setCidade("Joinville")
    endereco1.setEstado("SC")
    endereco1.setCep("2938423")
    cliente1.setEndereco(endereco1)

    veiculo1.setModelo("Sedan")
    veiculo1.setMarca("Fiat Pulse")
    veiculo1.setAno(2022)
    data1.setDia(20)
    data1.setMes(5)
    data1.setAno(2022)
    veiculo1.setData(data1)
    cliente1.setVeiculoAlugado(veiculo1)

    sistema.setContAlugados()
    sistema.setVeiculo(veiculo1)
    sistema.setContClientes()
    sistema.setClientes(cliente1)



