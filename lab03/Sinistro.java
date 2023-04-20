import java.util.Random;
import java.util.ArrayList;


public class Sinistro {
	private static ArrayList<Integer> ids = new ArrayList<Integer>();
	private static final int MAX = 100;// Define o valor maximo de clientes
	private int id;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	
	//Construtor
	public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo,Cliente cliente) {
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
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

	@Override
	public String toString() {
		return "id=" + id + ", data=" + data + ", endereco=" + endereco + ", seguradora=" + seguradora+ ", veiculo=" + veiculo + ", cliente=" + cliente;
	}
}
