import java.util.Scanner;

public class SistemaPetShop{
    
    private static Veterinario  [] veterinarios = new Veterinario[50];
    private static int quantidadeVeterinarios = 0;
    static Scanner s = new Scanner(System.in);

    private SistemaPetShop(){

    }

    public static void cadastrarVeterinario(){

        Veterinario vet1 = new Veterinario();
            
        System.out.println("Digite o nome do veterinario: ");
        vet1.setNome(s.nextLine());
        System.out.println("Digite o salario do veterinario: ");
        vet1.setSalario(Integer.valueOf(s.nextLine()));
        vet1.setQuantidadeAnimais(0);


        veterinarios[quantidadeVeterinarios] = vet1;
        quantidadeVeterinarios++;
        
    }

    public static void mostrarVeterinario(){
        
        for(int i = 0 ; i < quantidadeVeterinarios ; i++){
            
            System.out.print("\n");
            System.out.println("------Veterinario(a) " + (i+1) + "------");
            System.out.println("Nome: " + veterinarios[i].getNome());
            System.out.println("Salario: " + veterinarios[i].getSalario());
            System.out.println("Quantidade de animais: " + veterinarios[i].getQuantidadeAnimais());
            System.out.println("\n");
        
        }
    }

    public static void cadastrarEnderecoVeterinario(int n_vet){

        Endereco vet1 = new Endereco();
            
        System.out.println("Digite a rua: ");
        vet1.setRua(s.nextLine());
        System.out.println("Digite o numero da casa/predio: ");
        vet1.setNumero(Integer.valueOf(s.nextLine()));
        System.out.println("Digite a bairro: ");
        vet1.setBairro(s.nextLine());
        System.out.println("Digite a cidade: ");
        vet1.setCidade(s.nextLine());
        System.out.println("Digite a Estado: ");
        vet1.setEstado(s.nextLine());
        System.out.println("Digite a Cep: ");
        vet1.setCep(s.nextLine());

        veterinarios[n_vet - 1].setEndereco(vet1);

    }

    public static void cadastrarAnimal(int n_vet){

        Animal pet = new Animal();

        System.out.println("Digite a nome do animal: ");
        pet.setNome(s.nextLine());
        System.out.println("Digite a especie: ");
        pet.setEspecie(s.nextLine());
        System.out.println("Digite a descrição: ");
        pet.setDescricao(s.nextLine());

        int n_animais = veterinarios[n_vet - 1].getQuantidadeAnimais();

        veterinarios[n_vet - 1].setAnimais(pet, n_animais);
        veterinarios[n_vet - 1].setQuantidadeAnimais(1);

    }
    
    public static void mostarAnimal(int n_vet){

        if(veterinarios[n_vet-1].getQuantidadeAnimais() != 0){
            System.out.println("------Animais------");
            for(int j = 0 ; j < veterinarios[n_vet - 1].getQuantidadeAnimais() ; j++){
                System.out.println("Animal " + (j+1) +":\n");
                System.out.println("Nome: " + veterinarios[n_vet - 1].getAnimais()[j].getNome());
                if(veterinarios[n_vet - 1].getAnimais()[j].getDono() != null){
                System.out.println("Dono: " + veterinarios[n_vet - 1].getAnimais()[j].getDono().getNome());
                System.out.println("CPF: " + veterinarios[n_vet - 1].getAnimais()[j].getDono().getCpf());
                }
                System.out.println("Especie: " + veterinarios[n_vet - 1].getAnimais()[j].getEspecie());
                System.out.println("Descrição: " + veterinarios[n_vet - 1].getAnimais()[j].getDescricao());
                System.out.print("\n");
            }

        }

    }

    public static void cadastrarDono(int n_vet, int n_animal){

        Dono dono = new Dono();

        System.out.println("Digite o nome do dono: ");
        dono.setNome(s.nextLine());
        System.out.println("CPF: ");
        dono.setCpf(s.nextLine());

        veterinarios[n_vet - 1].getAnimais()[n_animal - 1].setDono(dono);

    }

    public static void cadastrarEnderecoDono(int n_vet, int n_animal){

        Endereco dono = new Endereco();

        System.out.println("Digite a rua: ");
        dono.setRua(s.nextLine());
        System.out.println("Digite o numero da casa/predio: ");
        dono.setNumero(Integer.valueOf(s.nextLine()));
        System.out.println("Digite a bairro: ");
        dono.setBairro(s.nextLine());
        System.out.println("Digite a cidade: ");
        dono.setCidade(s.nextLine());
        System.out.println("Digite a Estado: ");
        dono.setEstado(s.nextLine());
        System.out.println("Digite a Cep: ");
        dono.setCep(s.nextLine());

        veterinarios[n_vet - 1].getAnimais()[n_animal - 1].getDono().setEndereco(dono);

    }

    public static void main(String[] args) {
        
        int escolha = 0;
        int n_vet = 0;
        int n_animal = 0;

        do{

            System.out.println("--------MENU--------");
            System.out.println("1 - Cadastrar Veterinario");
            System.out.println("2 - Mostar Veterinarios");
            System.out.println("3 - Cadastrar Endereco Veterinario");
            System.out.println("4 - Cadastrar Animal");
            System.out.println("5 - Mostrar Animais");
            System.out.println("6 - Cadastrar Dono Animal");
            System.out.println("7 - Cadastrar Endereço Dono Animal");
            System.out.println("0 - Sair do programa");

            System.out.println("Escolha uma opção do menu: ");
            escolha = Integer.valueOf(s.nextLine());

            switch(escolha){
                case 1:
                cadastrarVeterinario();
                break;

                case 2:
                mostrarVeterinario();
                break;

                case 3:
                mostrarVeterinario();

                System.out.println("Digite o numero do veterinario que \nvoce quer cadastrar o endereço: ");
                n_vet = Integer.valueOf(s.nextLine());

                cadastrarEnderecoVeterinario(n_vet);
                break;

                case 4:
                mostrarVeterinario();

                System.out.println("Digite o numero do veterinario que \nvoce quer cadastrar um animal: ");
                n_vet = Integer.valueOf(s.nextLine());

                cadastrarAnimal(n_vet);
                break;

                case 5:
                mostrarVeterinario();

                System.out.println("Digite o numero do veterinario que \nvoce quer ver os animais atendidos: ");
                n_vet = Integer.valueOf(s.nextLine());

                mostarAnimal(n_vet);
                break;

                case 6:
                mostrarVeterinario();

                System.out.println("Digite o numero do veterinario que \nvoce quer ver os animais atendidos: ");
                n_vet = Integer.valueOf(s.nextLine());

                mostarAnimal(n_vet);

                System.out.println("Digite o numero do animal que\ndeseja cadastrar um dono: ");
                n_animal = Integer.valueOf(s.nextLine());

                cadastrarDono(n_vet, n_animal);
                break;

                case 7:
                    mostrarVeterinario();

                    System.out.println("Digite o numero do veterinario que \nvoce quer ver os animais atendidos: ");
                    n_vet = Integer.valueOf(s.nextLine());

                    mostarAnimal(n_vet);

                    System.out.println("Digite o numero do animal que\ndeseja cadastrar o endereço do dono: ");
                    n_animal = Integer.valueOf(s.nextLine());

                    cadastrarEnderecoDono(n_vet, n_animal);
                    break;

            }
        }while (escolha != 0);

    }
}
