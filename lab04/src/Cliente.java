
import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String 	endereco;
	private ArrayList<Veiculo> listaVeiculos;
	private String tipoCliente;
	private double valorSeguro;

	
	//Construtor
	public Cliente(String nome, String endereco, String tipoCliente) {
		this.listaVeiculos = new ArrayList<Veiculo>();
		this.nome = nome;
		this.endereco = endereco;
		this.tipoCliente = tipoCliente; 
		this.valorSeguro = 0;
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
	public ArrayList<Veiculo> getListaVeiculo() {
		return listaVeiculos;
	}

	public void setListaVeiculo(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos=listaVeiculos;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }	
	

	public void adicionaVeiculoCliente(Veiculo veiculo){
		listaVeiculos.add(veiculo);
	}

	public void removerVeiculoCliente(Veiculo veiculo){
		listaVeiculos.remove(veiculo);
	}

	public boolean listarVeiculosCliente(){
		if (getListaVeiculo().size() ==0){
			System.out.println("O cliente mencionado não possui veiculos");
			return false;
		}
		for (int i=0; i<listaVeiculos.size(); i++){
			System.out.println((i+1) + " - " + listaVeiculos.get(i).toString());
		}
		return true;
	}

	public double calculaScore(){
        return 0;	
    }

	public String toString() {
		return nome + "  Endereco: " + endereco + "  Tipo Cliente: " + tipoCliente + "  Valor Seguro" + valorSeguro;
	}
	
}

