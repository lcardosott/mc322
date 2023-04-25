package lab03;

import java.util.Date;
import java.text.SimpleDateFormat;


public class ClientePF extends Cliente{
    private String cpf;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;

    public ClientePF(String nome, String endereco,String cpf, String genero, Date dataLicenca,String educacao,Date dataNascimento, String classeEconomica){
		super(nome, endereco, "PF");
        this.genero = genero;
        this.dataLicenca = dataLicenca; 
        this.educacao = educacao;
		this.cpf =cpf;
        this.dataNascimento = dataNascimento;
        this.classeEconomica = classeEconomica;		
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

	public String trataData(Date data){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data);
		return (dataFormatada);
	}

    @Override
    public String toString() {
        return super.toString() + "CPF= " + cpf + "Genero= "+ genero + "Data Licensa= " +trataData(dataLicenca) + "Educacao= " + educacao + "Data Nascimento= " + trataData(dataNascimento) + "Classe Economica= " + classeEconomica;
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
		if (validaPrimeiro(cpfAnalise) && validaSegundo(cpfAnalise)) {
			return true;
		}
		return false;
	}


	public boolean validarCPF(String cpf){
		cpf = corrigeCPF(cpf);

		if (!tamanhoChecker(cpf)){
			System.out.println("Tamanho invalido");
			return false;
		} if (!igualChecker(cpf)){
			System.out.println("CPF INVALIDO!");
			return false;
		} if (!digitoChecker(cpf)){
			System.out.println("CPF INVALIDO!");
			return false;
		}
		System.out.println("CPF VALIDO!");
		return true;
	}	

}