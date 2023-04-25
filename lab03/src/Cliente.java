package lab03;

import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String 	endereco;
	public ArrayList<Veiculo> listaVeiculos;
	public String tipoCliente;
	
	//Construtor
	public Cliente(String nome, String endereco, String tipoCliente) {
		this.listaVeiculos = new ArrayList<Veiculo>();
		this.nome = nome;
		this.endereco = endereco;
		this.tipoCliente = tipoCliente; 
	}


	//Getters e setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public ArrayList<Veiculo> getListaVeiculo(ArrayList<Veiculo> listaVeiculos) {
		return listaVeiculos;
	}
	public void setListaVeiculo(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos=listaVeiculos;
	}

	public void adicionaVeiculo(Veiculo veiculo){
		listaVeiculos.add(veiculo);
	}

	public String toString() {
		return "Dados Clientes:\nNome= " + nome + ", Endereco= " + endereco + ", ListaVeiculos= " + listaVeiculos;
	}
}

