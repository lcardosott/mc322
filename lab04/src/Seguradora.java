
import java.util.ArrayList;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Veiculo> listaVeiculos;
	
	//Construtor
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSinistros = new ArrayList<Sinistro>();
		this.listaVeiculos = new ArrayList<Veiculo>();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}	
		
	//Getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public void cadastrarVeiculo(Veiculo veiculo) {
		//cadastra um veiculo na lista de veiculo da seguradora
		if (listaVeiculos.contains(veiculo)){
			System.out.println("Veiculo já cadastrado");
			return;
		}
		listaVeiculos.add(veiculo);
		System.out.println("Veículo adicionado com sucesso");
	}

	public void removerVeiculo(Veiculo veiculo){
		//remove um veiculo da lista de veiculos da seguradora
		if (!listaVeiculos.contains(veiculo)){
			System.out.println("Veículo fora do banco de dados");
			return;
		}
		listaVeiculos.remove(veiculo);
		System.out.println("Veículo removido com sucesso");
	}

	public void removerTodosVeiculos(Cliente cliente){
		//remove todos os veiculos de um determinado cliente
		for(Veiculo veiculo : listaVeiculos){
			if(cliente.getListaVeiculo().contains(veiculo)){
				listaVeiculos.remove(listaVeiculos.indexOf(veiculo));
			}
		}
	}

	public void listarVeiculos(){
		//lista todos os veiculos de uma seguradora
		System.out.println("****************************");
		System.out.println("  Veiculos da Seguradora:");
		System.out.println("****************************");
		for (int i = 0; i < listaVeiculos.size(); i++) {
				System.out.println(i+1+ " - " + listaVeiculos.get(i).toString());
			}
		System.out.println("****************************");	
	}

	public void cadastrarCliente(Cliente cliente){
		//adiciona um cliente na lista de clientes de uma seguradora
		if (listaClientes.contains(cliente)){
			System.out.println("Cliente já cadastrado");
			return;
		}
		if(!cliente.getListaVeiculo().isEmpty()){
			for (int i=0; i<cliente.getListaVeiculo().size(); i++){
				cadastrarVeiculo(cliente.getListaVeiculo().get(i));
			}
		}
		listaClientes.add(cliente);
		System.out.println("Cliente cadastrado com sucesso");

	}
	
	public void removerCliente(Cliente cliente){
		// remove um cliente da lista de clientes de uma seguradora
		if (!listaClientes.contains(cliente)){
			System.out.println("Cliente fora do banco de dados");
			return;
		}
		listaClientes.remove(cliente);
		removerTodosVeiculos(cliente);
		removerTodosSinistro(cliente);
		System.out.println("Cliente removido com sucesso");
	}

	public void listarClientes(){
		//lista todos os clientes de uma seguradora
		System.out.println("****************************");
		System.out.println("  Clientes da Seguradora:");
		System.out.println("****************************");
		for (int i = 0; i < listaClientes.size(); i++) {
				System.out.println((listaClientes.get(i).getNome()).toString());
			}
		System.out.println("****************************");	
	}

	public void listarClientes(String tipo){
		//lista todos os clientes de uma seguradora com base em seu tipo
		System.out.println("****************************");
		System.out.println("  Clientes da Seguradora:");
		System.out.println("****************************");
		for (int i = 0; i < listaClientes.size(); i++) {
			if ((listaClientes.get(i)).getTipoCliente() == tipo){
				System.out.println((listaClientes.get(i).getNome()).toString());
			}
		}
		System.out.println("****************************");
	}

	public void gerarSinistro(Sinistro sinistro){
		//adiciona o sinistro a lista de sinistros da seguradora
		if (listaSinistros.contains(sinistro)){
			System.out.println("Sinistro já cadastrado");
			return;
		}
		listaSinistros.add(sinistro);
		System.out.println("Sinistro cadastrado com sucesso");
	}

	public void listarSinistrosCliente(Cliente cliente){
		//lista todos os sinistros de um dado cliente
		int j=0;
		System.out.println("****************************");
		System.out.println("  Sinistros de " + cliente.getNome()+ ":");
		System.out.println("****************************");
		for(int i=0; i<listaSinistros.size(); i++){
			if (listaSinistros.get(i).getCliente() == cliente){
				System.out.println(listaSinistros.get(i).toString());
			}else{
				j++;
			}
		}
		if(j == 0){
			System.out.println("O cliente mencionado não possui sinistros");
		}
	}

	public int contarSinistros(Cliente cliente){
		//conta sinistros gerados por um determinado cliente
		int j=0;
		for(int i=0; i<listaSinistros.size(); i++){
			if (listaSinistros.get(i).getCliente() == cliente){
				j++;
			}else{
				j++;
			}
		}
		return j;
	}

	public void listarSinistros(){
		//lista todos os sinistros de uma seguradora
		System.out.println("****************************");
		System.out.println("  Sinistros da Seguradora:");
		System.out.println("****************************");
		for (int i = 0; i < listaSinistros.size(); i++) {
				System.out.println((listaSinistros.get(i).getId()) + " - "+ listaSinistros.get(i).getCliente().getNome());
			}
		System.out.println("****************************");
		}

	public void removerTodosSinistro(Cliente cliente){
		//remove todos os sinistros de um dado cliente
		for(Sinistro sinistro : listaSinistros){
			if(sinistro.getCliente() == cliente){
				listaSinistros.remove(listaSinistros.indexOf(sinistro));
			}
		}
	}

	public void removerSinistro(Sinistro sinistro){
		//remove um sinistro da lista de sinistros da seguradora
		if (!listaSinistros.contains(sinistro)){
			System.out.println("Sinistro fora do banco de dados");
			return;
		}
		listaSinistros.remove(sinistro);
		System.out.println("Sinistro removido com sucesso");
	}

	public void transfereSeguro(Cliente clienteAntigo, Cliente clienteNovo){
		//transfere o seguro de um cliente para o outro
		for (int i=0; i<clienteAntigo.getListaVeiculo().size(); i++){
			clienteNovo.getListaVeiculo().add(clienteAntigo.getListaVeiculo().get(i));
		}
		clienteAntigo.getListaVeiculo().clear();
	}

	public void calcularPrecoSeguroCliente(Cliente cliente){
		//calcula e seta o valor do seguro de um determinado cliente
		double preco=0;
		preco = cliente.calculaScore() * (1+ contarSinistros(cliente));
		cliente.setValorSeguro(preco);
	}

	public double calcularReceita(){
		//calcula a receita total da seguradora
		double valorTotal = 0;
		for(Cliente contribuinte: getListaClientes()){
			valorTotal += contribuinte.getValorSeguro();
		}
		return valorTotal;
	}
	@Override
	public String toString() {
		return "Dados Seguradora:\nNome= " + nome + ", Telefone= " + telefone + ", email= " + email + ", Endereco= " + endereco + ", ListaSinistros= " + listaSinistros + ", ListaClientes= " + listaClientes;
	}




}

