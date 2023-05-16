package lab03;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main {

	public static void printaMenu(int menu){
		if (menu == 0){
			System.out.println("\n***********************\nConsulta de dados: \n\n1- Cadastrar Cliente \n0- Fechar aplicação\n***********************\n");
		}if (menu==1){
			System.out.println("\n***********************\nCadastrar Cliente: \n\n1- Cadastrar Cliente PF \n2- Cadastrar Cliente PJ\n0- Retornar\n***********************\n");
		}if (menu==2){
			System.out.println("\n***********************\n\nAdicionar carro? \n\n1- Sim \n2- Não\n***********************\n");
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
		printaMenu(1);
		String linha = scanner.nextLine();
		int tipoCliente = Integer.parseInt(linha);

		if (tipoCliente ==0){
			return null;

		}if (tipoCliente ==1){
			Date dataLicensa, dataNascimento;
			String nome, endereco, cpf, genero, educacao, classeEconomica, nascimento, licensa;

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

			ClientePF cliente = new ClientePF(nome, endereco, cpf, genero, dataLicensa, educacao, dataNascimento, classeEconomica);
			return cliente;

		}if (tipoCliente ==2){
			String nome, endereco, cnpj, fundacao;
			Date dataFundacao;

			System.out.println("\nNome da Empresa:");
			nome = scanner.nextLine();
			System.out.println("\nEndereço da Empresa:");
			endereco = scanner.nextLine();
			System.out.println("\nCNPJ da Empresa");
			cnpj = scanner.nextLine();
			System.out.println("\nData de Fundação da Empresa:");
			fundacao = scanner.nextLine();
			dataFundacao = trataData(fundacao);
			ClientePJ cliente = new ClientePJ(nome, endereco, cnpj, dataFundacao);
			return cliente;
		}
		return null;
	}


	public static void acessaInformacao(int numero, Scanner scanner){
		if(numero == 1){
			Cliente novoCliente = coletaInfoCliente(scanner);
			if (novoCliente == null){
				return;
			}else{

				System.out.println("Cliente cadastrado com sucesso");
			}
		}if(numero == 2){

		}if(numero == 3){

		}if(numero == 4){

		}
	}
	public static void main(String[] args) {

// Instanciação e teste de funções pedidas no lab
		ClientePJ clientePJ = new ClientePJ("TechNow", "Rua Hermantino Coelho", "33.024.746/0001-94",trataData("03/10/2018"));
		ClientePF clientePF = new ClientePF("Lucas Cardoso", "Rua Vilela 325", "49315089871", "M", trataData("18/04/2022"), "Basica", trataData("02/03/2004"), "Média");

		clientePJ.validarCNPJ(clientePJ.getCnpj());
		clientePF.validarCPF(clientePF.getCpf());

		Veiculo veiculo = new Veiculo("", "Ford", "Ka", 2010);
		clientePF.adicionaVeiculo(veiculo);

		Seguradora seguradora = new Seguradora("LiveFree", "3278-9956", "caiogestor@livefree.com", "Avenida Brasil 815");

		seguradora.cadastrarCliente(clientePF);
		seguradora.cadastrarCliente(clientePJ);

		Sinistro sinistro = new Sinistro(trataData("22/08/2021"), "Rua Roxo Moreira 1297", seguradora, veiculo, clientePF);

		seguradora.gerarSinistro(sinistro);

		System.out.println(clientePF.toString()+ '\n');
		System.out.println(clientePJ.toString()+ '\n');
		System.out.println(seguradora.toString()+ '\n');
		System.out.println(sinistro.toString()+ '\n');
		System.out.println(seguradora.toString()+ '\n');

		seguradora.listarClientes("PF");
		System.out.println('\n');
		seguradora.listarSinistros();
		System.out.println('\n');
		seguradora.visualizarSinistro(clientePF);
		System.out.println('\n');


// Implementação de menu interativo
		boolean on = true;
		Scanner scanner = new Scanner(System.in);
		while(on){
			printaMenu(0);
			int numero = Integer.parseInt(scanner.nextLine());
			if(numero == 0){
				on = false;
			}
			acessaInformacao(numero, scanner);
		}

	}
}
