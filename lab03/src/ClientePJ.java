package lab03;

import java.util.Date;
import java.text.SimpleDateFormat;

public class ClientePJ extends Cliente{
    private String cnpj;
    private Date dataFundacao;
    
    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao){
        super(nome, endereco, "PJ");
        this.dataFundacao = dataFundacao;
		this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getdataFundacao() {
        return dataFundacao;
    }

    public void setdataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

	public static String trataData(Date data){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data);
		return (dataFormatada);
	}

    public String toString() {
        return super.toString() + ", cnpj= " + cnpj + ", dataFundacao= " + trataData(dataFundacao);
    }

    private String corrigeCNPJ(String cnpjBruto) {
		return cnpjBruto.replaceAll("[^0-9]", "");
	}
	
    private boolean tamanhoChecker(String cnpjAnalise) {
		if(cnpjAnalise.length()==14) {
			return true;
		}else {
			return false;
		}
	}

    private boolean igualChecker(String cnpjAnalise) {
		char caracter = cnpjAnalise.charAt(0), proximo;
		for (int i=1; i<14; i++) {
			proximo = cnpjAnalise.charAt(i);
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
		for (int i=0; i<4; i++) {
			soma += Character.getNumericValue(cpfAnalise.charAt(i))*(5-i);  
		}
        for (int i=0; i<8; i++) {
            soma += Character.getNumericValue(cpfAnalise.charAt(i+4))*(9-i);
        }
		resto = soma%11;
		if (resto<2) {
			return 0;
		}
		return 11-resto;
	}
	
	private boolean validaPrimeiro(String cpfAnalise) {
		int primeiroDigito = Character.getNumericValue(cpfAnalise.charAt(12));

		if (primeiroDigito == primeiroDigitoCorreto(cpfAnalise)) {
			return true;
		}
		return false;
	}

	//Valida segundo digito
	private int segundoDigitoCorreto(String cnpjAnalise) {
		int soma=0, resto;
		for (int i=0; i<5; i++) {
			soma += Character.getNumericValue(cnpjAnalise.charAt(i))*(6-i);  
		}
        for(int i=0; i<8; i++){
            soma+=Character.getNumericValue(cnpjAnalise.charAt(i+5))*(9-i);
        }
		resto = soma%11;
		if (resto <2) {
			return 0;
		}
		return 11-resto;
	}
	
	private boolean validaSegundo(String cnpjAnalise) {
		int segundoDigito = Character.getNumericValue(cnpjAnalise.charAt(13));

		if (segundoDigito == segundoDigitoCorreto(cnpjAnalise)) {
			return true;
		}
		return false;
	}
	

    private boolean digitoChecker(String cpfAnalise) {
		if (validaPrimeiro(cpfAnalise) && validaSegundo(cpfAnalise)) {
			return true;
		}
		return false;
	}

	//Valida CNPJ
	public boolean validarCNPJ(String cnpj){
		cnpj = corrigeCNPJ(cnpj);

		if (!tamanhoChecker(cnpj)){
			System.out.println("Tamanho invalido");
			return false;
		} if (!igualChecker(cnpj)){
			System.out.println("CNPJ INVALIDO!");
			return false;
		} if (!digitoChecker(cnpj)){
			System.out.println("CNPJ INVALIDO!");
			return false;
		}
		System.out.println("CNPJ VALIDO!");
		return true;
    }
}