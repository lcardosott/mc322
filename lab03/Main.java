import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main {

	public static void printaMenu(int menu){
		if (menu == 0){
			System.out.println("\nConsulta de dados: \n\n1- Cadastrar Cliente \n2- Remover Cliente\n3- Listar Clientes\n4- Visualizar Sinistros\n0- Fechar aplicação");
		}if (menu==1){
			System.out.println("\nCadastrar Cliente: \n\n1- Cadastrar Cliente PF \n2- Cadastrar Cliente PJ\n0- Retornar");
		}if (menu==2){
			System.out.println("\nAdicionar carro? \n\n1- Sim \n2- Não");
		}
	}

	public static ArrayList<Veiculo> coletaInfoVeiculos(Scanner scanner) {
		boolean continua = true;
		String placa, marca, modelo;
		int anoFabricacao;
		int escolha;
		ArrayList<Veiculo> listaVeiculos= new ArrayList<Veiculo>(); 
		do{
			printaMenu(3);
			escolha = Integer.parseInt(scanner.nextLine());
			if (escolha == 1){
				System.out.println("Insira a placa do veículo");
				placa = scanner.nextLine();
				System.out.println("Insira a marca do veículo");
				marca = scanner.nextLine();
				System.out.println("Insira o modelo do veículo");
				modelo = scanner.nextLine();
				System.out.println("Insira o ano de fabricação do veículo");
				anoFabricacao = Integer.parseInt(scanner.nextLine());
				Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
				listaVeiculos.add(veiculo);
			}else{
				continua = false;
			}
		}while (continua);
		return listaVeiculos;

	}
	public static Date trataData(String dataStr){
		Date data;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
            data = formato.parse(dataStr);
            return data;
        } catch (ParseException e) {
            System.out.println("Data inserida fora do padrão (tente dd/MM/yyyy) " + e.getMessage());
        }return null;
	}

	public static Cliente coletaInfoCliente(Scanner scanner){
		Date dataLicensa, dataNascimento;
		printaMenu(1);
		String linha = scanner.nextLine(), nome, endereco, cpf, genero, educacao, classeEconomica, nascimento, licensa;
		int tipoCliente = Integer.parseInt(linha);
		if (tipoCliente ==0){
			return null;

		}if (tipoCliente ==1){
			System.out.println("\nNome do Cliente:");
			nome = scanner.nextLine();
			System.out.println("\nEndereço do Cliente:");
			endereco = scanner.nextLine();
			System.out.println("\nCPF do Cliente:");
			cpf = scanner.nextLine();
			System.out.println("\nGenero do Cliente:");
			genero = scanner.nextLine();
			System.out.println("\nData da licensa do Cliente:");
			licensa = scanner.nextLine();
			dataLicensa = trataData(licensa);

			System.out.println("\nEducação do Cliente:");
			educacao = scanner.nextLine();
			System.out.println("\nData de nascimento do Cliente:");
			nascimento = scanner.nextLine();
			dataNascimento = trataData(nascimento);
			
			System.out.println("\nClasse economica do Cliente:");
			classeEconomica = scanner.nextLine();
			ArrayList<Veiculo> listaVeiculos = coletaInfoVeiculos(scanner);

			ClientePF cliente = new ClientePF(nome, endereco, listaVeiculos, cpf, genero, dataLicensa, educacao, dataNascimento, classeEconomica );
			return cliente;

		}if (tipoCliente ==2){
			
		}
		return null;
	}


	public static void acessaInformacao(int numero, Scanner scanner){
		if(numero == 1){
			Cliente novoCliente = coletaInfoCliente(scanner);
		}if(numero == 2){

		}if(numero == 3){

		}if(numero == 4){

		}
	}

	public static void main(String[] args) {
		int menu_atual = 0;
		Scanner scanner = new Scanner(System.in);
		while(menu_atual>=0){
			printaMenu(menu_atual);
			int numero = Integer.parseInt(scanner.nextLine());
			if(numero == 0){
				menu_atual --;
			}
			acessaInformacao(numero, scanner);
		}

	}
}
