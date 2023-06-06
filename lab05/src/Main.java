import java.util.Scanner;
import java.util.Date;
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
				System.out.println("4 - Configuracoes Seguradora");
				System.out.println("0 - Sair");
				System.out.println("****************************");
				break;

			case MENU_CADASTROS:
				System.out.println("****************************");
				System.out.println("    OPCOES DE CADASTROS");
				System.out.println("****************************");
				System.out.println("1 - Cadastrar Cliente PF/PJ");
				System.out.println("2 - Cadastrar Veiculo");
				System.out.println("3 - Cadastrar Seguro");
				System.out.println("4 - Cadastrar Condutor");
				System.out.println("5 - Cadastrar Sinistro");
				System.out.println("0 - Voltar");
				System.out.println("****************************");
				break;

			case MENU_LISTAR:
				System.out.println("****************************");
				System.out.println("    OPCOES DE LISTAGEM");
				System.out.println("****************************");
				System.out.println("1 - Listar Cliente (PF/PJ) por Seg.");
				System.out.println("2 - Listar Seguros por Seguradora");
				System.out.println("3 - Listar Seguros por Cliente");
				System.out.println("4 - Listar Sinistros por Cliente");
				System.out.println("5 - Listar Veiculo por Cliente");
				System.out.println("6 - Listar Condutores por Cliente");
				System.out.println("0 - Voltar");				
				System.out.println("****************************");
				break;

			case MENU_EXCLUIR:
				System.out.println("****************************");
				System.out.println("    OPCOES DE EXCLUSAO");
				System.out.println("****************************");
				System.out.println("1 - Excluir Cliente");
				System.out.println("2 - Excluir Veiculo");
				System.out.println("3 - Excluir Sinistro");
				System.out.println("4 - Excluir Condutor");
				System.out.println("5 - Excluir Seguro");
				System.out.println("0 - Voltar");
				System.out.println("****************************");
				break;

			case MENU_TIPOCLIENTE:
				System.out.println("****************************");
				System.out.println("    TIPO CLIENTE");
				System.out.println("****************************");
				System.out.println("1 - Cliente PF");
				System.out.println("2 - Cliente PJ");
				System.out.println("0 - Voltar");				
				System.out.println("****************************");
				break;

				case MENU_CONFIGURACOES_SEGURADORA:
				System.out.println("****************************");
				System.out.println("  CONFIGURACOES SEGURADORA");
				System.out.println("****************************");
				System.out.println("1 - Cadastrar Seguradora");
				System.out.println("2 - Remover Seguradora");
				System.out.println("3 - Trocar Seguradora");
				System.out.println("4 - Calcular Receita");
				System.out.println("0 - Voltar");				
				System.out.println("****************************");
				break;

			default:
				break;
		}
	}

	public static void acessaInformacao(MenuOperacoes opcao, Scanner scanner, Seguradora seguradora, ArrayList<Seguradora> listaSeguradora){
		//Funcao responsável por direcionar o usuario a sua funcao desejada
		int numero;
		switch(opcao){
			case CADASTRAR:
				printaMenu(MenuOperacoes.MENU_CADASTROS);
				numero = Integer.parseInt(scanner.nextLine());
				acessaInformacao(Validacao.encontraOperacao(numero+10), scanner, seguradora, listaSeguradora);
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
					break;
				}
				if (cliente == null){
					System.out.println("Cliente não cadastrado");
				} else{
					seguradora.cadastrarCliente(cliente);
				}
				
				break;

			case CADASTRAR_VEICULO:
				Veiculo veiculo;
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				cliente = seguradora.getListaClientes().get(numero-1);

				if (cliente instanceof ClientePJ) {
					if (!((ClientePJ)cliente).listaFrotaPorCliente()){
						break;
					}
					numero = Integer.parseInt(scanner.nextLine());
					Frota frota = ((ClientePJ)cliente).getListaFrota().get(numero-1);

					veiculo = coletaInfoVeiculos(scanner);
					frota.addVeiculo(veiculo);
					System.out.println("Veiculo cadastrado com sucesso");
					break;
				}
					
				veiculo = coletaInfoVeiculos(scanner);
				((ClientePF) seguradora.getListaClientes().get(numero-1)).getListaVeiculos().add(veiculo);
				System.out.println("Veiculo cadastrado com sucesso");
				break;

			case CADASTRAR_SEGURO:
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				cliente = seguradora.getListaClientes().get(numero-1);
				Seguro seguro = coletaInfoSeguro(scanner, seguradora, cliente);
				if (seguro == null){
					System.out.println("Seguro não cadastrado");
					break;
				}
				seguradora.gerarSeguro(seguro);
				break;
			
			case CADASTRAR_CONDUTOR:
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				Cliente clienteCondutor = seguradora.getListaClientes().get(numero-1);

				if (!seguradora.listaSeguroPorCliente(clienteCondutor)){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				seguro = seguradora.getSegurosPorCliente(clienteCondutor).get(numero-1);

				Condutor condutor = coletaInfoCondutor(scanner);
				seguro.autorizarCondutor(condutor);
				break;

			case CADASTRAR_SINISTRO:
				//Valida se a operação é válida
				if (!seguradora.listarClientes()){
					break;
				}
				cadastraSinistro(scanner, seguradora);
				break;
			
			case VOLTAR_CADASTRAR:
				break;
				
			case LISTAR:
				printaMenu(MenuOperacoes.MENU_LISTAR);
				numero = Integer.parseInt(scanner.nextLine());
				acessaInformacao(Validacao.encontraOperacao(numero+20), scanner, seguradora, listaSeguradora);
				break;

			case LISTAR_CLIENTE:
				seguradora.listarClientes();
				break;

			case LISTAR_SEGUROS_SEGURADORA:
				seguradora.listarSeguros();
				break;

			case LISTAR_SEGUROS_CLIENTE:
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				seguradora.listaSeguroPorCliente(seguradora.getListaClientes().get(numero-1));
				break;

			case LISTAR_SINISTROS_CLIENTE:
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				seguradora.listaSinistroPorCliente(seguradora.getListaClientes().get(numero-1));
				break;
			
			case LISTAR_VEICULO_CLIENTE:
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				cliente = seguradora.getListaClientes().get(numero-1);

				if (cliente instanceof ClientePJ){
					if (!((ClientePJ)cliente).listaFrotaPorCliente()){
						break;
					}
					numero = Integer.parseInt(scanner.nextLine());
					Frota frota = ((ClientePJ)cliente).getListaFrota().get(numero-1);

					((ClientePJ)cliente).listaVeiculoPorFrota(frota);
					break;
				}
				((ClientePF)cliente).listaVeiculoPorCliente();
				break;
				
			case LISTAR_CONDUTORES:
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				cliente = seguradora.getListaClientes().get(numero-1);

				if(!seguradora.listaSeguroPorCliente(cliente)){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				seguro = seguradora.getSeguroDoCliente(cliente, numero);

				seguro.listaCondutorPorSeguro();
				break;

			case VOLTAR_LISTAR:
				break;

			case EXCLUIR:
				printaMenu(MenuOperacoes.MENU_EXCLUIR);
				numero = Integer.parseInt(scanner.nextLine());
				acessaInformacao(Validacao.encontraOperacao(numero+30), scanner, seguradora, listaSeguradora);
				break;

			case EXCLUIR_CLIENTE:
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				cliente = seguradora.getListaClientes().get(numero-1);
				seguradora.removerCliente(cliente);
				seguradora.removerSegurosCliente(cliente);
				break;

			case EXCLUIR_VEICULO:
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				cliente = seguradora.getListaClientes().get(numero-1);

				if (cliente instanceof ClientePJ){
					((ClientePJ)cliente).atualizarFrota(scanner);
					break;
				}

				if(!((ClientePF)cliente).listaVeiculoPorCliente()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				veiculo = ((ClientePF)cliente).getListaVeiculos().get(numero-1);

				((ClientePF)cliente).getListaVeiculos().remove(veiculo);
				break;
				
			case EXCLUIR_SINISTRO:
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				cliente = seguradora.getListaClientes().get(numero-1);

				if(!seguradora.listaSeguroPorCliente(cliente)){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				seguro = seguradora.getSeguroDoCliente(cliente, numero);

				if(!seguro.listaSinistroPorSeguro(seguro)){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				Sinistro sinistro = seguro.getListaSinistros().get(numero-1);
				seguro.getListaSinistros().remove(sinistro);

			case EXCLUIR_CONDUTOR:
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				cliente = seguradora.getListaClientes().get(numero-1);

				if(!seguradora.listaSeguroPorCliente(cliente)){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				seguro = seguradora.getSeguroDoCliente(cliente, numero);

				if(!seguro.listaCondutorPorSeguro()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				if(numero ==1){
					System.out.println("Nao e possivel excluir o condutor dono do seguro");
					break;
				}
				condutor = seguro.getListaCondutores().get(numero-1);
				seguro.desautorizarCondutor(condutor);
				break;

			case EXCLUIR_SEGURO:
				if (!seguradora.listarClientes()){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				cliente = seguradora.getListaClientes().get(numero-1);

				if(!seguradora.listaSeguroPorCliente(cliente)){
					break;
				}
				numero = Integer.parseInt(scanner.nextLine());
				seguro = seguradora.getSeguroDoCliente(cliente, numero);
				seguradora.cancelarSeguro(seguro);
				break;

			case VOLTAR_EXCLUIR:
				break;
				
			case CONFIGURACOES_SEGURADORA:
				printaMenu(MenuOperacoes.MENU_CONFIGURACOES_SEGURADORA);
				numero = Integer.parseInt(scanner.nextLine());
				acessaInformacao(Validacao.encontraOperacao(numero+40), scanner, seguradora, listaSeguradora);
				break;

			case CADASTRAR_SEGURADORA:
				Seguradora seguradoraNova = criaSeguradora(scanner);
				listaSeguradora.add(seguradoraNova);
				System.out.println("Seguradora cadastrada com sucesso");
				break;	
			
			case REMOVER_SEGURADORA:
				if (!Validacao.verificaExistenciaSeguradora(listaSeguradora, 2)){
					break;
				}
				printaSeguradoras(listaSeguradora);
				numero = Integer.parseInt(scanner.nextLine());
				listaSeguradora.remove(listaSeguradora.get(numero-1));
				System.out.println("****************************");
				System.out.println("   Seja bem vindo " +listaSeguradora.get(0).getNome());
				System.out.println("****************************");
				break;

			case TROCAR_SEGURADORA:
				if (!Validacao.verificaExistenciaSeguradora(listaSeguradora, 2)){
					break;
				}
				printaSeguradoras(listaSeguradora);
				numero = Integer.parseInt(scanner.nextLine());
				Collections.swap(listaSeguradora, 0, numero);
				System.out.println("****************************");
				System.out.println("   Seja bem vindo " +listaSeguradora.get(0).getNome());
				System.out.println("****************************");
				break;
		
			case CALCULAR_RECEITA:
				double valorTotal = 0;
				valorTotal = seguradora.calcularReceita();
				System.out.println("****************************");
				System.out.println("    RECEITA SEGURADORA:");
				System.out.println("****************************");
				System.out.println("R$"+valorTotal);
				break;

			case VOLTAR_CONFIGURACOES:
				break;

			case SAIR:
				break;

			default:
				break;
			}
		}


	public static Seguro coletaInfoSeguro(Scanner scanner, Seguradora seguradora, Cliente cliente){
		String inicio, fim;
		Date dataInicio, dataFim;
		int numero;
		
		System.out.println("\nData de inicio de contrato(dd/mm/yyyy):");
		do {
			inicio = scanner.nextLine();
			dataInicio = Validacao.trataData(inicio);
		}while(dataInicio == null);
		
		System.out.println("\nData de fim de contrato(dd/mm/yyyy):");
		do {
			fim = scanner.nextLine();
			dataFim = Validacao.trataData(fim);
		}while(dataFim == null);

		if (cliente instanceof ClientePJ){
			if (cliente instanceof ClientePJ){
				if (!((ClientePJ)cliente).listaFrotaPorCliente()){
					return null;
				}
				numero = Integer.parseInt(scanner.nextLine());
				Frota frota = ((ClientePJ)cliente).getListaFrota().get(numero-1);
			
				SeguroPJ seguro = new SeguroPJ(dataInicio, dataFim, seguradora, frota, ((ClientePJ) cliente));
//				Condutor condutor = new Condutor(null, cliente.getNome(), null, null, null,null )
//				seguro.autorizarCondutor(condutor)
				return seguro;
			}
		}
		if(!((ClientePF)cliente).listaVeiculoPorCliente()){
			return null;
		}
		numero = Integer.parseInt(scanner.nextLine());
		Veiculo veiculo = ((ClientePF)cliente).getListaVeiculos().get(numero-1);
		SeguroPF seguro = new SeguroPF(dataInicio, dataFim, seguradora, veiculo, ((ClientePF)cliente));
		Condutor condutor = new Condutor(null, cliente.getNome(), null, null, null,null );
		seguro.autorizarCondutor(condutor);

		return seguro;
	}

	public static Condutor coletaInfoCondutor(Scanner scanner){
		Date dataNascimento;
		String nome, telefone, endereco, email ,cpf, nascimento;
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
		System.out.println("\nTelefone do Cliente:");
		telefone = scanner.nextLine();

		System.out.println("\nEndereço do Cliente:");
		endereco = scanner.nextLine();

		System.out.println("\nEmail do Cliente:");
		email = scanner.nextLine();

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

		System.out.println("\nEndereço do Cliente:");
		endereco = scanner.nextLine();

		System.out.println("\nData de nascimento do Cliente(dd/mm/yyyy):");
		do {
			nascimento = scanner.nextLine();
			dataNascimento = Validacao.trataData(nascimento);
		}while(dataNascimento == null);

		Condutor condutor = new Condutor(Validacao.corrigeCPF(cpf), nome, telefone, endereco, email, dataNascimento);
		return condutor;
	}
	
	public static void cadastraSinistro(Scanner scanner, Seguradora seguradora){
		int numero;
		Date data;
        Condutor condutor;
        String endereco, ocorrido;

		//Recolhe o cliente responsável
		numero = Integer.parseInt(scanner.nextLine());
		Cliente clienteCausa = seguradora.getListaClientes().get(numero-1);

		//Recolhe o seguro
		if (!seguradora.listaSeguroPorCliente(clienteCausa)){
			return;
		}
		numero = Integer.parseInt(scanner.nextLine());
		Seguro seguro = seguradora.getSeguroDoCliente(clienteCausa, numero);


		if (!seguro.listaCondutorPorSeguro()){
			return;
		}
        numero = Integer.parseInt(scanner.nextLine());
        condutor = seguro.getListaCondutores().get(numero-1);

        System.out.println("\nData do ocorrido(dd/mm/yyyy):");
        do {
            ocorrido = scanner.nextLine();
            data = Validacao.trataData(ocorrido);
        }while(data == null);

        System.out.println("\nEndereço:");
        endereco = scanner.nextLine();

		Sinistro sinistro = new Sinistro(data, endereco, condutor, seguro); 
		seguro.gerarSinistro(condutor, sinistro);
	}

	public static ClientePF cadastraClientePF(Scanner scanner){
		//Funcao responsável por recolher informacoes para cadastrar um clientePF
		Date dataNascimento;
		String nome, telefone, endereco, email ,cpf, educacao, nascimento;
		char genero;
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
		System.out.println("\nTelefone do Cliente:");
		telefone = scanner.nextLine();

		System.out.println("\nEndereço do Cliente:");
		endereco = scanner.nextLine();

		System.out.println("\nEmail do Cliente:");
		email = scanner.nextLine();

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

		System.out.println("\nEndereço do Cliente:");
		endereco = scanner.nextLine();

		System.out.println("\nData de nascimento do Cliente(dd/mm/yyyy):");
		do {
			nascimento = scanner.nextLine();
			dataNascimento = Validacao.trataData(nascimento);
		}while(dataNascimento == null);

		genero = recolheGenero(scanner);
		educacao = recolheEducacao(scanner);

		ClientePF cliente = new ClientePF(nome, telefone, endereco, email, Validacao.corrigeCPF(cpf), genero, educacao, dataNascimento);
		return cliente;
	}

	public static ClientePJ cadastraClientePJ(Scanner scanner){
		//Funcao responsável por recolher informacoes para cadastrar um clientePF
		String nome, telefone, endereco, email ,cnpj, fundacao;
		Date dataFundacao;

		System.out.println("\nNome da Empresa (apenas caracteres):");
		nome = scanner.nextLine();
		while((!Validacao.validarNome(nome))){
			if (nome.equals("0")){
				return null;
			} else{
				System.out.println("\nNome da empresa ou 0 para cancelar operação:");
				nome = scanner.nextLine();
			}
		}
		System.out.println("\nTelefone da Empresa:");
		telefone = scanner.nextLine();

		System.out.println("\nEndereço da Empresa:");
		endereco = scanner.nextLine();

		System.out.println("\nEmail da Empresa:");
		email = scanner.nextLine();

		System.out.println("\nCNPJ da Empresa:");
		cnpj = scanner.nextLine();
		while((!Validacao.validarCNPJ(cnpj))){
			if (cnpj.equals("0")){
				return null;
			} else{
				System.out.println("\nCPF do Cliente ou 0 para cancelar operação:");
				cnpj = scanner.nextLine();
			}
		}
		
		System.out.println("\nData de Fundação da Empresa:");
		do {
			fundacao = scanner.nextLine();
			dataFundacao = Validacao.trataData(fundacao);
		}while(dataFundacao == null);

		ClientePJ cliente = new ClientePJ(nome, telefone, endereco, email, Validacao.corrigeCNPJ(cnpj), dataFundacao);
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
	
	public static void printaSeguradoras(ArrayList<Seguradora>listaSeguradora){
		//funcao responsavel por imprimir todas as seguradoras cadastradas
		System.out.println("****************************");
		System.out.println(" Seguradoras disponiveis:");
		System.out.println("****************************");
		for (int i=1; i< listaSeguradora.size(); i++) {	
			System.out.println(i + " - " +(listaSeguradora.get(i)).getNome());
		}
		System.out.println("****************************");
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
		String nome,telefone,email,endereco, cnpj;

		System.out.println("\nCNPJ da Seguradora:");
		cnpj = scanner.nextLine();
		while((!Validacao.validarCNPJ(cnpj))){
			if (cnpj.equals("0")){
				return null;
			} else{
				System.out.println("\nCNPJ da seguradora ou 0 para cancelar operação:");
				cnpj = scanner.nextLine();
			}
		}

		System.out.println("Insira o nome da seguradora:");
		nome = scanner.nextLine();
		System.out.println("Insira o telefone da seguradora:");
		telefone = scanner.nextLine();
		System.out.println("Insira o email da seguradora:");
		email = scanner.nextLine();
		System.out.println("Insira o endereco da seguradora:");
		endereco = scanner.nextLine();
		Seguradora seguradora = new Seguradora(cnpj ,nome, telefone, endereco, email);
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
		System.out.println("****************************");
	}

	public static void main(String[] args) {

		//instanciando objetos basicos
		Seguradora seguradora = new Seguradora("70810245000160", "Segura", "19938217", "Rua Hermantino Coelho", "segura@gmail.com" );

		Veiculo veiculo1 = new Veiculo("AMS-3581", "Chevrolet", "Onix", 2010);

		Veiculo veiculo2 = new Veiculo("AMS-9142", "Chevrolet", "Prisma", 2012);

		ClientePJ clientePJ = new ClientePJ("TechNow", "19994798776","Avenida Prestes Maia", "technow@gmail.com","33024746000194",Validacao.trataData("03/10/2018"));

		ClientePF clientePF = new ClientePF("Lucas Cardoso", "19994796176","Rua Vilela 325", "lucas@gmail.com","49315089871", 'M', "SI", Validacao.trataData("02/03/2004"));

		Frota frota = new Frota("Funcionarios");
		frota.addVeiculo(veiculo2);
		clientePJ.cadastrarFrota(frota);
		
		clientePF.cadastrarVeiculo(veiculo1);

		SeguroPF seguroPF = new SeguroPF(Validacao.trataData("20/05/2023"), Validacao.trataData("20/05/2024"), seguradora, veiculo1, clientePF);

		seguradora.cadastrarCliente(clientePF);
		seguradora.gerarSeguro(seguroPF);

		SeguroPJ seguroPJ = new SeguroPJ(Validacao.trataData("20/05/2023"), Validacao.trataData("20/05/2024"), seguradora, frota, clientePJ);
		seguradora.cadastrarCliente(clientePJ);
		seguradora.gerarSeguro(seguroPJ);

		Condutor condutorPF = new Condutor("32412393871", "Rogério Santos", "19994796175", "Rua Piracicabana", "rogerio@gmail.com", Validacao.trataData("07/12/1993"));

		Condutor condutorPJ = new Condutor("22531030808", "Valeria Almeida", "1999479421", "Rua Campo Belo", "valeria@gmail.com", Validacao.trataData("23/05/1984"));

		seguroPJ.autorizarCondutor(condutorPJ);
		seguroPF.autorizarCondutor(condutorPF);

		Sinistro sinistroPF = new Sinistro(Validacao.trataData("02/06/2023"), "Avenida Brasil", condutorPF, seguroPF);
		seguroPF.gerarSinistro(condutorPF, sinistroPF);

		Sinistro sinistroPJ = new Sinistro(Validacao.trataData("25/05/2023"), "Avenida Norte Sul", condutorPJ, seguroPJ);
		seguroPF.gerarSinistro(condutorPJ, sinistroPJ);

		System.out.println(seguradora.toString());
		System.out.println("****************************");
		System.out.println(veiculo1.toString());
		System.out.println("****************************");
		System.out.println(veiculo2.toString());
		System.out.println("****************************");
		System.out.println(clientePJ.toString());
		clientePJ.listaFrotaPorCliente();
		System.out.println("****************************");
		System.out.println(clientePF.toString());
		clientePF.listaVeiculoPorCliente();
		System.out.println("****************************");
		System.out.println(frota.toString());
		frota.listaVeiculoPorFrota();
		System.out.println("****************************");
		System.out.println(seguroPF.toString());
		System.out.println("****************************");
		System.out.println(seguroPJ.toString());
		System.out.println("****************************");
		System.out.println(condutorPF.toString());
		System.out.println("****************************");
		System.out.println(condutorPJ.toString());
		System.out.println("****************************");
		System.out.println(sinistroPF.toString());
		System.out.println("****************************");
		System.out.println(sinistroPJ.toString());
		System.out.println("****************************");

		System.out.println("R$"+seguradora.calcularReceita());

		//inicio da aplicacao

		boolean on = true;
		Scanner scanner = new Scanner(System.in);

		ArrayList<Seguradora> listaSeguradora = new ArrayList<Seguradora>();
		listaSeguradora.add(seguradora);
		primeiroUso(listaSeguradora, scanner);

		while(on){
			printaMenu(MenuOperacoes.MENU_PRINCIPAL);
			int numero = Integer.parseInt(scanner.nextLine());
			acessaInformacao(Validacao.encontraOperacao(numero), scanner, listaSeguradora.get(0), listaSeguradora);
		}
		scanner.close();

	}
}
