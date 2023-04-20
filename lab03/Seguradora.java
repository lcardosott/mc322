import java.util.ArrayList;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Cliente> listaClientes;
	
	//Construtor
	public Seguradora(String nome, String telefone, String email, String endereco, ArrayList<Sinistro> listaSinistros, ArrayList<Cliente> listaClientes) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSinistros = listaSinistros;
		this.listaClientes = listaClientes;
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

	public String toString() {
		return "nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", endereco=" + endereco + ", listaSinistros=" + listaSinistros + ", listaClientes=" + listaClientes;
	}

	public boolean cadastrarCliente(Cliente cliente){
		if (listaClientes.contains(cliente)){
			return false;
		}
		listaClientes.add(cliente);
		return true;
	}
	public boolean removerCliente(Cliente cliente){
		if (!listaClientes.contains(cliente)){
			return false;
		}
		listaClientes.remove(cliente);
		return true;
	}
	public void listarClientes(String tipo){
		for (int i = 0; i < listaClientes.size(); i++) {
			if ((listaClientes.get(i)).tipoCliente == tipo){
				(listaClientes.get(i)).toString();
			}
		}
	}

	public boolean gerarSinistro(Sinistro sinistro){
		if (listaSinistros.contains(sinistro)){
			return false;
		}
		listaSinistros.add(sinistro);
		return true;
	}
	public boolean visualizarSinistro(Sinistro sinistro){
		if (!listaSinistros.contains(sinistro)){
			return false;
		}
		System.out.println(sinistro.getId());;
		return true;
	}
	public void listarSinistros(){
		for (int i = 0; i < listaClientes.size(); i++) {
				(listaClientes.get(i)).toString();
			}
		}
}
