
import java.util.Random;
import java.util.ArrayList;
import java.util.Date;

public class Sinistro {
	private static ArrayList<Integer> ids = new ArrayList<Integer>();
	private static final int MAX = 100;// Define o valor maximo de clientes
	private final int id;
	private Date data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;
	
	//Construtor
	public Sinistro(Date data, String endereco, Condutor condutor, Seguro seguro) {
		this.id = idGenerator();

		this.data = data;
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
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
	public Condutor getCondutor() {
        return condutor;
    }
    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
    public Seguro getSeguro() {
        return seguro;
    }
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

	public static ArrayList<Integer> getIds() {
		return ids;
	}
	public static void setIds(ArrayList<Integer> ids) {
		Sinistro.ids = ids;
	}

	@Override
	public String toString() {
		return "Id: " + id + ", Data: " + Validacao.trataData(data) + ", Endereco: " + endereco + ", Condutor: " + condutor.getNome() + ", Seguro: " + seguro.getId();
	}
}
