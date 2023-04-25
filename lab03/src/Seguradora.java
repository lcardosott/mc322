package lab03;

import java.util.ArrayList;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Cliente> listaClientes;
	
	//Construtor
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSinistros = new ArrayList<Sinistro>();
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

	public boolean cadastrarCliente(Cliente cliente){
		if (listaClientes.contains(cliente)){
			return false;
		}
		listaClientes.add(cliente);
		return true;
	}

	public boolean gerarSinistro(Sinistro sinistro){
		if (listaSinistros.contains(sinistro)){
			return false;
		}
		listaSinistros.add(sinistro);
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
		System.out.println("Clientes da Seguradora:");
		for (int i = 0; i < listaClientes.size(); i++) {
			if ((listaClientes.get(i)).tipoCliente == tipo){
				System.out.println((listaClientes.get(i).getNome()).toString());
			}
		}
	}

	public boolean visualizarSinistro(Cliente cliente){
		int j=0;
		for(int i=0; i<listaSinistros.size(); i++){
			if (listaSinistros.get(i).getCliente() == cliente){
				System.out.println(listaSinistros.get(i).toString());
			}else{
				j++;
			}
		}
		if(j != 0){
			return true;
		}
		return false;
	}
	
	public void listarSinistros(){
		System.out.println("Sinistros da Seguradora():");
		for (int i = 0; i < listaSinistros.size(); i++) {
				System.out.println((listaSinistros.get(i).getId()));
			}
		}
	
	@Override
	public String toString() {
		return "Dados Seguradora:\nNome= " + nome + ", Telefone= " + telefone + ", email= " + email + ", Endereco= " + endereco + ", ListaSinistros= " + listaSinistros + ", ListaClientes= " + listaClientes;
	}
}

