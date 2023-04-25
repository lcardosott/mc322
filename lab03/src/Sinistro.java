package lab03;

import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Sinistro {
	private static ArrayList<Integer> ids = new ArrayList<Integer>();
	private static final int MAX = 100;// Define o valor maximo de clientes
	private int id;
	private Date data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	
	//Construtor
	public Sinistro(Date data, String endereco, Seguradora seguradora, Veiculo veiculo,Cliente cliente) {
		this.id = idGenerator();

		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}
	
	//idGenerator cria um id aleatorio e confere se esse id já foi usado previamente
	private int idGenerator() {
		Random random = new Random();
		int numero = random.nextInt(MAX), contador=0;

		while (ids.contains(numero) && contador<MAX){
			numero = random.nextInt(MAX);
			contador ++;
		}

		if (contador==MAX-1){
			System.out.println("Numero de Sinistros excedido");
			return 0;
		}

		ids.add(numero);
		return numero;
	}
	
	//Getters e setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public static ArrayList<Integer> getIds() {
		return ids;
	}

	public static void setIds(ArrayList<Integer> ids) {
		Sinistro.ids = ids;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}
	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String trataData(Date data){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data);
		return (dataFormatada);
	}

    public String toString() {
        return "Dados Sinistro:\nId= " + id + ", Data= " + trataData(data) + ", Endereco= " + endereco + ", Seguradora= " + seguradora.getNome() + ", Veiculo= " + veiculo + ", Cliente= " + cliente.getNome();
    }

}
