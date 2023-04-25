package lab02;

import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String 	endereco;
	
	//Construtor
	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
	}
	
	//Getters e setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	public String toString() {
		return "Nome= " + nome + ", CPF= " + cpf + ", Data de Nascimento= " + dataNascimento + ", Idade= " + idade
				+ ", Endereco= " + endereco;
	}

	private String corrigeCPF(String cpfBruto) {
		return cpfBruto.replaceAll("[^0-9]", "");
	}
	
	private boolean tamanhoChecker(String cpfAnalise) {
		if(cpfAnalise.length()==11) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean igualChecker(String cpfAnalise) {
		char caracter = cpfAnalise.charAt(0), proximo;
		for (int i=1; i<11; i++) {
			proximo = cpfAnalise.charAt(i);
			if (caracter == proximo) {
				caracter = proximo;
			}else {
				return true;
			}
		}
		return false;	
	}
	//Valida primeiro digito
	private int primeiroDigitoCorreto(String cpfAnalise) {
		int soma=0, resto;
		for (int i=0; i<9; i++) {
			soma += Character.getNumericValue(cpfAnalise.charAt(i))*(10-i);  
		}
		resto = soma%11;
		if (resto == 0||resto ==1) {
			return 0;
		}
		return 11-resto;
	}
	
	private boolean validaPrimeiro(String cpfAnalise) {
		int primeiroDigito = Character.getNumericValue(cpfAnalise.charAt(9));

		if (primeiroDigito == primeiroDigitoCorreto(cpfAnalise)) {
			return true;
		}
		return false;
	}
	
	//Valida segundo digito
	private int segundoDigitoCorreto(String cpfAnalise) {
		int soma=0, resto;
		for (int i=0; i<9; i++) {
			soma += Character.getNumericValue(cpfAnalise.charAt(1+i))*(10-i);  
		}
		resto = soma%11;
		if (resto == 0||resto ==1) {
			return 0;
		}
		return 11-resto;
	}
	
	private boolean validaSegundo(String cpfAnalise) {
		int segundoDigito = Character.getNumericValue(cpfAnalise.charAt(10));

		if (segundoDigito == segundoDigitoCorreto(cpfAnalise)) {
			return true;
		}
		return false;
	}
	
	//Valida CPF
	private boolean digitoChecker(String cpfAnalise) {
		if (validaPrimeiro(cpfAnalise)&& validaSegundo(cpfAnalise)) {
			return true;
		}
		return false;
	}


	public boolean validarCPF(String cpf){
		cpf = corrigeCPF(cpf);
		if (tamanhoChecker(cpf) && igualChecker(cpf) && digitoChecker(cpf)) {
			return true;
		}return false;
	}	
}
	
	
	
	

