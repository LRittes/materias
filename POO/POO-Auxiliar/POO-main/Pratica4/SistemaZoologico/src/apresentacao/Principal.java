package apresentacao;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import dados.Animal;
import dados.Aquario;
import dados.Peixe;
import dados.Viveiro;
import negocio.Zoologico;

public class Principal{

    static Zoologico sistema = new Zoologico();
    static Scanner s = new Scanner(System.in);

    public static void menu(){
        System.out.println("--------MENU--------");
        System.out.println("1- Cadastrar Viveiro;");
        System.out.println("2- Cadastrar Animal;");
        System.out.println("3- Alocar Animal;");
        System.out.println("4- Mostrar Aquarios;");
        System.out.println("5- Mostrar Viveiros;");
        System.out.println("0- Sair;");
    }

    public static Viveiro dadosViveiro(){

        System.out.println("Voce deseja cadastrar um: \n1- Viveiro\n2- Aquario");
        int escolha = Integer.valueOf(s.nextLine());

        if(escolha == 1){
            Viveiro viveiro = new Viveiro();

            System.out.println("Nome: ");
            viveiro.setNome(s.nextLine());
            System.out.println("Comprimento: ");
            viveiro.setComprimento(Float.valueOf(s.nextLine()));
            System.out.println("Largura: ");
            viveiro.setLargura(Float.valueOf(s.nextLine()));

            return viveiro;
        }else{
            Aquario aquario = new Aquario();

            System.out.println("Nome: ");
            aquario.setNome(s.nextLine());
            System.out.println("Comprimento: ");
            aquario.setComprimento(Float.valueOf(s.nextLine()));
            System.out.println("Largura: ");
            aquario.setLargura(Float.valueOf(s.nextLine()));
            System.out.println("Altura: ");
            aquario.setAltura(Float.valueOf(s.nextLine()));
            System.out.println("Temperatura: ");
            aquario.setTemperatura(Float.valueOf(s.nextLine()));

            return aquario;
        }

    }

    public static Animal dadosAnimal(){

        System.out.println("Voce deseja cadastrar:\n1- Animal Terrestre\n2- Peixe");
        int escolha = Integer.valueOf(s.nextLine());

        if(escolha == 1){
            Animal animal = new Animal();

            System.out.println("Nome: ");
            animal.setNome(s.nextLine());
            System.out.println("Cor: ");
            animal.setCor(s.nextLine());
            System.out.println("Especie: ");
            animal.setEspecie(s.nextLine());
            System.out.println("Idade: ");
            animal.setIdade(Integer.valueOf(s.nextLine()));
            System.out.println("Largura: ");
            animal.setLargura(Float.valueOf(s.nextLine()));
            System.out.println("Comprimento: ");
            animal.setComprimento(Float.valueOf(s.nextLine()));
            System.out.println("Altura: ");
            animal.setAltura(Float.valueOf(s.nextLine()));

            return animal;
        }else{
            Peixe peixe = new Peixe();

            System.out.println("Nome: ");
            peixe.setNome(s.nextLine());
            System.out.println("Cor: ");
            peixe.setCor(s.nextLine());
            System.out.println("Especie: ");
            peixe.setEspecie(s.nextLine());
            System.out.println("Idade: ");
            peixe.setIdade(Integer.valueOf(s.nextLine()));
            System.out.println("Largura: ");
            peixe.setLargura(Float.valueOf(s.nextLine()));
            System.out.println("Comprimento: ");
            peixe.setComprimento(Float.valueOf(s.nextLine()));
            System.out.println("Altura: ");
            peixe.setAltura(Float.valueOf(s.nextLine()));
            System.out.println("Temperatura Ideal: ");
            peixe.setTemperaturaIdeal(Float.valueOf(s.nextLine()));

            return peixe;
        }

    }

    public static void alocarAnimal(){

        System.out.println(sistema.mostraAnimal());
        System.out.println("Seleciona o numero do animal que voce quer alocar num viveiro: ");
        int escolhaAnimal = Integer.valueOf(s.nextLine()) - 1;

        System.out.println(sistema.mostraViveiro());
        System.out.println("Seleciona o numero do viveiro ou aquario que voce quer alocar o animal: ");
        int escolhaViveiro = Integer.valueOf(s.nextLine()) - 1;

        if(sistema.alocarAnimal(sistema.getAnimais().get(escolhaAnimal), sistema.getViveiros().get(escolhaViveiro)) == true){
            System.out.println("Animal alocado!!!");
        }else{
            System.out.println("Animal nao foi alocado!!!");
        }
    }

    public static void mostraSoAquarios(){

        List<Aquario> aquarios = new ArrayList<Aquario>();

        aquarios =  sistema.getSoAquarios();

        System.out.println(aquarios.toString());
    }

    public static void mostraSoViveiro(){

        List<Viveiro> viveiros = new ArrayList<Viveiro>();

        viveiros = sistema.getSoViveiros();

        System.out.println(viveiros.toString());
    }

    public static void main(String[] args) {

        int opcao = 0;

        do{

            menu();
            System.out.println(("Digite a opcao que voce quer executar: "));
            opcao = Integer.valueOf(s.nextLine());

            switch(opcao){

                case 1:
                    sistema.cadastrarViveiro(dadosViveiro());
                    break;

                case 2:
                    sistema.cadastrarAnimais(dadosAnimal());
                    break;

                case 3:
                    alocarAnimal();
                    break;

                case 4:
                    mostraSoAquarios();
                    break;

                case 5:
                    mostraSoViveiro();
                    break;

            }
        }while(opcao != 0);

    }

}
