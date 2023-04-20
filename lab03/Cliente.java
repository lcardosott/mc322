import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String 	endereco;
	public ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	public String tipoCliente;
	
	//Construtor
	public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String tipoCliente) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = listaVeiculos;
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

	public String toString() {
		return "Nome= " + nome + ", Endereco= " + endereco + ",ListaVeiculos =" + listaVeiculos;
	}
}
	
	
	
	

