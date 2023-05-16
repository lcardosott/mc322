import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void printaMenu(MenuOperacoes menu){
	//Funcao responsável por organizar e imprimir determinado menu
		switch (menu){
			case MENU_PRINCIPAL:
				System.out.println("****************************");
				System.out.println("      MENU PRINCIPAL");
				System.out.println("****************************");
				System.out.println("1 - Cadastros");
				System.out.println("2 - Listar");
				System.out.println("3 - Excluir");
				System.out.println("4 - Gerar Sinistro");
				System.out.println("5 - Transferir Seguro");
				System.out.println("6 - Calcular Receita Seguradora");
				System.out.println("7 - Trocar de Seguradora");
				System.out.println("0 - Sair");
				System.out.println("****************************");
				break;

			case MENU_CADASTROS:
				System.out.println("****************************");
				System.out.println("    OPCOES DE CADASTROS");
				System.out.println("****************************");
				System.out.println("1 - Cadastrar Cliente PF/PJ");
				System.out.println("2 - Cadastrar Veiculo");
				System.out.println("3 - Cadastrar Seguradora");
				System.out.println("4 - Voltar");
				System.out.println("****************************");
				break;

			case MENU_EXCLUIR:
				System.out.println("****************************");
				System.out.println("    OPCOES DE EXCLUSAO");
				System.out.println("****************************");
				System.out.println("1 - Excluir Cliente");
				System.out.println("2 - Excluir Veiculo");
				System.out.println("3 - Excluir Sinistro");
				System.out.println("4 - Voltar");
				System.out.println("****************************");
				break;
			
			case MENU_LISTAR:
				System.out.println("****************************");
				System.out.println("    OPCOES DE LISTAGEM");
				System.out.println("****************************");
				System.out.println("1 - Listar Cliente (PF/PJ) por Seg.");
				System.out.println("2 - Listar Sinistros por Seguradora");
				System.out.println("3 - Listar Sinistro por Cliente");
				System.out.println("4 - Listar Veiculo por Cliente");
				System.out.println("5 - Listar Veiculo por Seguradora");
				System.out.println("6 - Voltar");				
				System.out.println("****************************");
				break;

			case MENU_TIPOCLIENTE:
				System.out.println("****************************");
				System.out.println("    TIPO CLIENTE");
				System.out.println("****************************");
				System.out.println("1 - Cliente PF");
				System.out.println("2 - Cliente PJ");
				System.out.println("3 - Voltar");				
				System.out.println("****************************");
				break;

			default:
				break;
		}
	}

	public static MenuOperacoes encontraOperacao(int numero) {
		//Funcao responsável por encontrar a funcao que o usuario deseja acessar atraves de um numero inserido
		for (MenuOperacoes operacao : MenuOperacoes.values()){
			if (operacao.getOperacao() == numero){
				return operacao;
			}
		}
		return null;
	}

	public static void acessaInformacao(MenuOperacoes opcao, Scanner scanner, Seguradora seguradora, ArrayList<Seguradora> listaSeguradora){
		//Funcao responsável por direcionar o usuario a sua funcao desejada
		int numero;
		switch(opcao){
			case CADASTRAR:
				printaMenu(MenuOperacoes.MENU_CADASTROS);
				numero = Integer.parseInt(scanner.nextLine());
				acessaInformacao(encontraOperacao(numero+10), scanner, seguradora, listaSeguradora);
				break;

			case CADASTRAR_CLIENTEPFPJ:
				printaMenu(MenuOperacoes.MENU_TIPOCLIENTE);
				Cliente cliente = null;
				numero = Integer.parseInt(scanner.nextLine());

				if (numero == 1){
					cliente = cadastraClientePF(scanner);
				}
				else if (numero ==2){
					cliente = cadastraClientePJ(scanner);
				}else {
					acessaInformacao(encontraOperacao(11), scanner, seguradora, listaSeguradora);
				}
				
				if (cliente == null){
					System.out.println("Cliente não cadastrado");
				} else{
					seguradora.cadastrarCliente(cliente);
				}
				break;

			case CADASTRAR_VEICULO:
				Veiculo veiculo;
				if (!verificaExistenciaCliente(seguradora, 1)){
					break;
				}

				System.out.println("****************************");
				System.out.println("       Dono do veiculo");
				System.out.println("****************************");
				printaClientes(seguradora);
				numero = Integer.parseInt(scanner.nextLine());
				veiculo = coletaInfoVeiculos(scanner);
				seguradora.getListaClientes().get(numero-1).adicionaVeiculoCliente(veiculo);
				seguradora.cadastrarVeiculo(veiculo);
				seguradora.calcularPrecoSeguroCliente(seguradora.getListaClientes().get(numero-1));
				System.out.println("Veiculo cadastrado com sucesso");
				break;
			
			case CADASTRAR_SEGURADORA:
				Seguradora seguradoraNova = criaSeguradora(scanner);
				listaSeguradora.add(seguradoraNova);
				System.out.println("Seguradora cadastrada com sucesso");
				break;	

			case VOLTAR_CADASTRAR:
				break;
				
			case LISTAR:
				printaMenu(MenuOperacoes.MENU_LISTAR);
				numero = Integer.parseInt(scanner.nextLine());
				acessaInformacao(encontraOperacao(numero+20), scanner, seguradora, listaSeguradora);
				break;

			case LISTAR_CLIENTE:
				seguradora.listarClientes();
				break;

			case LISTAR_SINISTROS_SEGURADORA:
				seguradora.listarSinistros();
				break;

			case LISTAR_SINISTROS_CLIENTE:
				if (!verificaExistenciaCliente(seguradora, 1)){
					break;
				}
				System.out.println("****************************");
				System.out.println("  Cliente a ser analizado:");
				System.out.println("****************************");
				printaClientes(seguradora);
				numero = Integer.parseInt(scanner.nextLine());
				seguradora.listarSinistrosCliente(seguradora.getListaClientes().get(numero-1));
				System.out.println("****************************");
				break;
			
			case LISTAR_VEICULO_CLIENTE:
				if (!verificaExistenciaCliente(seguradora, 1)){
					break;
				}
				System.out.println("****************************");
				System.out.println("  Cliente a ser analizado:");
				System.out.println("****************************");
				printaClientes(seguradora);
				numero = Integer.parseInt(scanner.nextLine());

				System.out.println("****************************");
				System.out.println("  Veiculos de " + seguradora.getListaClientes().get(numero-1).getNome() + ":");
				System.out.println("****************************");
				seguradora.getListaClientes().get(numero-1).listarVeiculosCliente();
				System.out.println("****************************");
				break;

			case LISTAR_VEICULO_SEGURADORA:
				seguradora.listarVeiculos();
				break;
				
			case VOLTAR_LISTAR:
				break;

			case EXCLUIR:
				printaMenu(MenuOperacoes.MENU_EXCLUIR);
				numero = Integer.parseInt(scanner.nextLine());
				acessaInformacao(encontraOperacao(numero+30), scanner, seguradora, listaSeguradora);
				break;

			case EXCLUIR_CLIENTE:
				if (!verificaExistenciaCliente(seguradora, 1)){
					break;
				}
				System.out.println("****************************");
				System.out.println("  Cliente a ser removido:");
				System.out.println("****************************");
				printaClientes(seguradora);
				numero = Integer.parseInt(scanner.nextLine());
				seguradora.removerCliente(seguradora.getListaClientes().get(numero-1));
				break;

			case EXCLUIR_VEICULO:
				if (!verificaExistenciaCliente(seguradora, 1)){
					break;
				}
				int numeroVeiculo=0;

				System.out.println("****************************");
				System.out.println("  Dono do veículo:");
				System.out.println("****************************");
				printaClientes(seguradora);
				System.out.println("****************************");
				numero = Integer.parseInt(scanner.nextLine());

				System.out.println("  Remover Veiculo de" + seguradora.getListaClientes().get(numero-1).getNome());
				System.out.println("****************************");
				(seguradora.getListaClientes().get(numero-1)).listarVeiculosCliente();
				System.out.println("****************************");
				numeroVeiculo = Integer.parseInt(scanner.nextLine());

				seguradora.getListaClientes().get(numero-1).removerVeiculoCliente(seguradora.getListaClientes().get(numero-1).getListaVeiculo().get(numeroVeiculo-1));
				seguradora.removerVeiculo(seguradora.getListaClientes().get(numero-1).getListaVeiculo().get(numeroVeiculo-1));

				seguradora.calcularPrecoSeguroCliente(seguradora.getListaClientes().get(numero-1));
				break;
				
			case EXCLUIR_SINISTRO:
				if (!verificaExistenciaCliente(seguradora, 1)){
					break;
				}
				int numeroSinistro=0;
				System.out.println("****************************");
				System.out.println(" Responsavel pelo Sinistro");
				System.out.println("****************************");
				printaClientes(seguradora);
				System.out.println("****************************");
				numero = Integer.parseInt(scanner.nextLine());
				
				System.out.println("  Remover sinistro de" + seguradora.getListaClientes().get(numero-1).getNome());
				System.out.println("****************************");
				seguradora.listarSinistrosCliente(seguradora.getListaClientes().get(numero-1));
				System.out.println("****************************");
				numeroSinistro = Integer.parseInt(scanner.nextLine());

				seguradora.removerSinistro(seguradora.getListaSinistros().get(numeroSinistro-1));

				seguradora.calcularPrecoSeguroCliente(seguradora.getListaClientes().get(numero-1));

			case VOLTAR_EXCLUIR:
				break;

			case GERAR_SINISTRO:
				if (!verificaExistenciaCliente(seguradora, 1)){
					break;
				}

				Sinistro sinistro;
				Date data;
				String endereco, ocorrido;
				Cliente clienteCausa;
				Veiculo veiculoSinistro;

				System.out.println("****************************");
				System.out.println("  Responsavel pelo Sinistro:");
				System.out.println("****************************");
				printaClientes(seguradora);
				System.out.println("****************************");
				numero = Integer.parseInt(scanner.nextLine());
				clienteCausa = seguradora.getListaClientes().get(numero-1);

				System.out.println("  Veiculo de" + seguradora.getListaClientes().get(numero-1).getNome() + "envolvido");
				System.out.println("****************************");
				if (!(seguradora.getListaClientes().get(numero-1)).listarVeiculosCliente())	{break;
				}
				System.out.println("****************************");
				numero = Integer.parseInt(scanner.nextLine());
				veiculoSinistro = seguradora.getListaVeiculos().get(numero-1);

				System.out.println("\nData do ocorrido(dd/mm/yyyy):");
				do {
					ocorrido = scanner.nextLine();
					data = trataData(ocorrido);
				}while(data == null);


				System.out.println("\nEndereço do Cliente:");
				endereco = scanner.nextLine();
				sinistro = new Sinistro(data, endereco, seguradora, veiculoSinistro, clienteCausa);
				seguradora.gerarSinistro(sinistro);
				
			case TRANSFERIR_SEGURO:
				int numero2=1000;

				if (!verificaExistenciaCliente(seguradora, 1)){
					break;
				}
				System.out.println("****************************");
				System.out.println("       Antigo dono:");
				System.out.println("****************************");
				printaClientes(seguradora);
				System.out.println("****************************");
				numero = Integer.parseInt(scanner.nextLine());
				Cliente antigoDono = seguradora.getListaClientes().get(numero-1);
				do{
				System.out.println("****************************");
				System.out.println("Novo dono: (diferente de" + antigoDono.getNome() + ")");
				System.out.println("****************************");
				printaClientes(seguradora);
				System.out.println("****************************");
				numero2 = Integer.parseInt(scanner.nextLine());
				}while(numero == numero2);
				Cliente novoDono = seguradora.getListaClientes().get(numero2-1);
				seguradora.transfereSeguro(antigoDono, novoDono);
				antigoDono.setValorSeguro(0);
				seguradora.calcularPrecoSeguroCliente(novoDono);
				break;


			case CALCULA_RECEITA:
				double valorTotal = 0;
				valorTotal = seguradora.calcularReceita();
				System.out.println("****************************");
				System.out.println("    RECEITA SEGURADORA:");
				System.out.println("****************************");
				System.out.println("R$"+valorTotal);
				break;

			case TROCA_SEGURADORA:
				if (!verificaExistenciaSeguradora(listaSeguradora, 2)){
					break;
				}
				System.out.println("****************************");
				System.out.println(" Seguradoras disponiveis:");
				System.out.println("****************************");
				printaSeguradoras(listaSeguradora);
				numero = Integer.parseInt(scanner.nextLine());
				Collections.swap(listaSeguradora, 0, numero);

			case SAIR:
				break;

			default:
				break;
			}
		}

	public static Date trataData(String dataStr){
		//transforma uma string em uma Date
		Date data;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
            data = formato.parse(dataStr);
            return data;
        } catch (ParseException e) {
            System.out.println("Data inserida fora do padrão (tente dd/mm/yyyy):");
		}
		return null;
	}
	
	public static ClientePF cadastraClientePF(Scanner scanner){
		//Funcao responsável por recolher informacoes para cadastrar um clientePF
		Date dataLicensa, dataNascimento;
		String nome, endereco, cpf, educacao, nascimento, licensa;
		char genero, classeEconomica;
		System.out.println("\nNome do Cliente (apenas caracteres):");
		nome = scanner.nextLine();
		while((!Validacao.validarNome(nome))){
			if (nome.equals("0")){
				return null;
			} else{
				System.out.println("\nNome do Cliente ou 0 para cancelar operação:");
				nome = scanner.nextLine();
			}
		}

		System.out.println("\nEndereço do Cliente:");
		endereco = scanner.nextLine();
		System.out.println("\nCPF do Cliente:");
		
		cpf = scanner.nextLine();
		while((!Validacao.validarCPF(cpf))){
			if (cpf.equals("0")){
				return null;
			} else{
				System.out.println("\nCPF do Cliente ou 0 para cancelar operação:");
				cpf = scanner.nextLine();
			}
		}
		System.out.println("\nData da licensa do Cliente(dd/mm/yyyy):");
		do {
			licensa = scanner.nextLine();
			dataLicensa = trataData(licensa);
		}while(dataLicensa == null);

		System.out.println("\nData de nascimento do Cliente(dd/mm/yyyy):");
		do {
			nascimento = scanner.nextLine();
			dataNascimento = trataData(nascimento);
		}while(dataNascimento == null);

		genero = recolheGenero(scanner);
		educacao = recolheEducacao(scanner);
		classeEconomica = recolheClasse(scanner);
		
		ClientePF cliente = new ClientePF(nome, endereco, Validacao.corrigeCPF(cpf), genero, dataLicensa, educacao, dataNascimento, classeEconomica);
		return cliente;
	}

	public static ClientePJ cadastraClientePJ(Scanner scanner){
		//Funcao responsável por recolher informacoes para cadastrar um clientePF
		String nome, endereco, cnpj, fundacao;
		int qtdeFuncionarios;
		Date dataFundacao;

		System.out.println("\nNome da Empresa:");
		nome = scanner.nextLine();
		while((!Validacao.validarNome(nome))){
			if (nome.equals("0")){
				return null;
			} else{
				System.out.println("\nNome do Cliente ou 0 para cancelar operação:");
				nome = scanner.nextLine();
			}
		}
		System.out.println("\nEndereço da Empresa:");
		endereco = scanner.nextLine();
		System.out.println("\nCNPJ da Empresa");
		cnpj = scanner.nextLine();
		while((!Validacao.validarCNPJ(cnpj))){
			if (cnpj.equals("0")){
				return null;
			} else{
				System.out.println("\nCNPJ da empresa ou 0 para cancelar operação");
				cnpj = scanner.nextLine();
			}
		}
		System.out.println("\nData de Fundação da Empresa:");
		do {
			fundacao = scanner.nextLine();
			dataFundacao = trataData(fundacao);
		}while(dataFundacao == null);
		System.out.println("\nQuantidade de Funcionarios:");
		qtdeFuncionarios =  Integer.parseInt(scanner.nextLine());
		ClientePJ cliente = new ClientePJ(nome, endereco, Validacao.corrigeCNPJ(cnpj), dataFundacao, qtdeFuncionarios);
		return cliente;
	}

	public static Veiculo coletaInfoVeiculos(Scanner scanner) {
		//Funcao responsável por coletar informacoes necessarias para cadastrar um veiculo
		String placa, marca, modelo;
		int anoFabricacao;
		Veiculo veiculo;
		System.out.println("Insira a placa do veículo");
		placa = scanner.nextLine();
		System.out.println("Insira a marca do veículo");
		marca = scanner.nextLine();
		System.out.println("Insira o modelo do veículo");
		modelo = scanner.nextLine();
		System.out.println("Insira o ano de fabricação do veículo");
		anoFabricacao = Integer.parseInt(scanner.nextLine());
		veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
		return veiculo;
	}
	
	public static void printaClientes(Seguradora seguradora){//Imprime uma lista de clientes de uma determinada seguradora
		//funcao responsavel por imprimir todos os clientes de uma seguradora
		for (int i=1; i< seguradora.getListaClientes().size()+1; i++) {	
			System.out.println(i + " - " +(seguradora.getListaClientes().get(i-1)).getNome());
		}
		System.out.println("****************************");
	}

	public static void printaSeguradoras(ArrayList<Seguradora>listaSeguradora){
		//funcao responsavel por imprimir todas as seguradoras cadastradas
		for (int i=1; i< listaSeguradora.size(); i++) {	
			System.out.println(i + " - " +(listaSeguradora.get(i)).getNome());
		}
		System.out.println("****************************");
	}

	public static boolean verificaExistenciaCliente(Seguradora seguradora, int minimo){
		//Verifica se a seguradora ja instanciou ao menos um cliente
		if (seguradora.getListaClientes().isEmpty() || seguradora.getListaClientes().size()<minimo){
			System.out.println("Cadastre ao menos "+ minimo+" clientes antes de realizar essa operação");
			return false;
		}return true;
	}

	public static boolean verificaExistenciaSeguradora(ArrayList<Seguradora> listaSeguradoras, int minimo){
		//Verifica se a seguradora ja instanciou ao menos um cliente
		if (listaSeguradoras.size()<minimo){
			System.out.println("Cadastre ao menos "+ minimo+" seguradoras antes de realizar essa operação");
			return false;
		}return true;
	}

	public static char recolheClasse(Scanner scanner){
		//padroniza o recolhimento de informacoes relacionadas a classe
		int valor =0;
		System.out.println("\nClasse economica:");
		System.out.println("1 - A");
		System.out.println("2 - B");
		System.out.println("3 - C");
		System.out.println("4 - D");
		System.out.println("5 - E");
		valor = Integer.parseInt(scanner.nextLine());

		if (valor == 1){
			return 'A';
		}else if (valor == 2){
			return 'B';
		}else if (valor == 3){
			return 'C';
		}else if (valor == 4){
			return 'D';
		}else if (valor == 5){
			return 'E';
		}
		return '-';
	}

	public static String recolheEducacao(Scanner scanner){
		//padroniza o recolhimento de informacoes relacionadas a educacao
		int valor =0;
		System.out.println("\nNivel Educacao:");
		System.out.println("1 - Ensino Fundamental Completo");
		System.out.println("2 - Ensino Fundamental Incompleto");
		System.out.println("3 - Ensino Médio Completo");
		System.out.println("4 - Ensino Médio Incompleto");
		System.out.println("5 - Ensino Superior Completo");
		System.out.println("6 - Ensino Superior Incompleto");
		valor = Integer.parseInt(scanner.nextLine());

		if (valor == 1){
			return "FC";
		}else if (valor == 2){
			return "FI";
		}else if (valor == 3){
			return "MC";
		}else if (valor == 4){
			return "MI";
		}else if (valor == 5){
			return "SC";
		}else if (valor == 6){
			return "SI";
		}
		return "-";
	}

	public static char recolheGenero(Scanner scanner){
		//padroniza o recolhimento de informacoes relacionadas a genero
		int valor =0;
		System.out.println("\nGenero do Cliente:");
		System.out.println("1 - Masculino");
		System.out.println("2 - Feminino");
		System.out.println("3 - Outros");
		valor = Integer.parseInt(scanner.nextLine());
		if (valor == 1){
			return 'M';
		}else if (valor == 2){
			return 'F';
		}else if (valor == 3){
			return '-';
		}
		return '-';
	}

	public static Seguradora criaSeguradora(Scanner scanner){
		//recolhe informacoes necessarias para criar uma seguradora
		String nome,telefone,email,endereco;
		System.out.println("Insira o nome da seguradora:");
		nome = scanner.nextLine();
		System.out.println("Insira o telefone da seguradora:");
		telefone = scanner.nextLine();
		System.out.println("Insira o email da seguradora:");
		email = scanner.nextLine();
		System.out.println("Insira o endereco da seguradora:");
		endereco = scanner.nextLine();
		Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
		return seguradora;
	}

	public static void primeiroUso(ArrayList<Seguradora> listaSeguradora, Scanner scanner){
		//referente a uma inicializacao do programa, checa se uma seguradora ja foi instanciada
		if (listaSeguradora.isEmpty()){
			Seguradora seguradora =  criaSeguradora(scanner);
			listaSeguradora.add(seguradora);
		}
		System.out.println("****************************");
		System.out.println("   Seja bem vindo " +listaSeguradora.get(0).getNome());
	}

	public static void main(String[] args) {

		//instanciando objetos basicos
		Seguradora seguradora = new Seguradora("SEGURA", "19994796176",	"segura@gmail.com", "Rua Unicamp");

		Veiculo veiculo1 = new Veiculo("AMS-3581", "Chevrolet", "Onix", 2010);
		Veiculo veiculo2 = new Veiculo("AMS-9142", "Chevrolet", "Prisma", 2012);

		ClientePJ clientePJ = new ClientePJ("TechNow", "Rua Hermantino Coelho", "33.024.746/0001-94",trataData("03/10/2018"), 10);
		ClientePF clientePF = new ClientePF("Lucas Cardoso", "Rua Vilela 325", "49315089871", 'M', trataData("18/04/2022"), "SI", trataData("02/03/2004"), 'C');

		clientePJ.adicionaVeiculoCliente(veiculo1);
		clientePF.adicionaVeiculoCliente(veiculo2);

		seguradora.cadastrarCliente(clientePJ);
		seguradora.cadastrarCliente(clientePF);

		Sinistro sinistro1 = new Sinistro(trataData("22/08/2021"), "Rua Roxo Moreira 1297", seguradora, veiculo1, clientePJ);
		Sinistro sinistro2 = new Sinistro(trataData("10/10/2023"), "Rua Roxo Moreira 1305", seguradora, veiculo2, clientePF);

		seguradora.gerarSinistro(sinistro1);
		seguradora.gerarSinistro(sinistro2);

		seguradora.listarClientes();

		seguradora.listarSinistros();
		System.out.println("Receita seguradora: R$" + seguradora.calcularReceita());

		//note que o valor é zero, precisamos atualizar o valor;
		for (Cliente cliente : seguradora.getListaClientes()){
			seguradora.calcularPrecoSeguroCliente(cliente);
		}

		System.out.println("Receita seguradora: R$" + seguradora.calcularReceita());

		//inicio da aplicacao

		boolean on = true;
		Scanner scanner = new Scanner(System.in);

		ArrayList<Seguradora> listaSeguradora = new ArrayList<Seguradora>();
		listaSeguradora.add(seguradora);
		primeiroUso(listaSeguradora, scanner);

		while(on){
			printaMenu(MenuOperacoes.MENU_PRINCIPAL);
			int numero = Integer.parseInt(scanner.nextLine());
			acessaInformacao(encontraOperacao(numero), scanner, listaSeguradora.get(0), listaSeguradora);
		}
		scanner.close();	
	}
}
