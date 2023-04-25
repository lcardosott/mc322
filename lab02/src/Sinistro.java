package lab02;

import java.util.Random;

public class Sinistro {
	private int id;
	private String data;
	private String endereco;
	
	//Construtor
	public Sinistro(String data, String endereco) {
		this.id = idGenerator();
		this.data = data;
		this.endereco = endereco;
	}
	
	private int idGenerator() {
		Random random = new Random();
		int numero = random.nextInt(100);
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
}
