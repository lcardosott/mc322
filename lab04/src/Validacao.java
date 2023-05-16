import java.util.Calendar;
import java.util.GregorianCalendar;

public class Validacao {

    //Valida CPF***************************************
    public static boolean validarCPF(String cpf){
		cpf = corrigeCPF(cpf);

		if (!tamanhoCheckerCPF(cpf)){
			System.out.println("CPF INVALIDO");
			return false;
		} if (!igualCheckerCPF(cpf)){
			System.out.println("CPF INVALIDO!");
			return false;
		} if (!digitoCheckerCPF(cpf)){
			System.out.println("CPF INVALIDO!");
			return false;
		}
		System.out.println("CPF VALIDO!");
		return true;
	}
    public static String corrigeCPF(String cpfBruto) {
		return cpfBruto.replaceAll("[^0-9]", "");
	}
	
	private static boolean tamanhoCheckerCPF(String cpfAnalise) {
		if(cpfAnalise.length()==11) {
			return true;
		}else {
			return false;
		}
	}
	
	private static boolean igualCheckerCPF(String cpfAnalise) {
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
	private static int primeiroDigitoCorretoCPF(String cpfAnalise) {
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
	
	private static boolean validaPrimeiroCPF(String cpfAnalise) {
		int primeiroDigito = Character.getNumericValue(cpfAnalise.charAt(9));

		if (primeiroDigito == primeiroDigitoCorretoCPF(cpfAnalise)) {
			return true;
		}
		return false;
	}
	
	//Valida segundo digito
	private static int segundoDigitoCorretoCPF(String cpfAnalise) {
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
	
	private static boolean validaSegundoCPF(String cpfAnalise) {
		int segundoDigito = Character.getNumericValue(cpfAnalise.charAt(10));

		if (segundoDigito == segundoDigitoCorretoCPF(cpfAnalise)) {
			return true;
		}
		return false;
	}
	
	private static boolean digitoCheckerCPF(String cpfAnalise) {
		if (validaPrimeiroCPF(cpfAnalise) && validaSegundoCPF(cpfAnalise)) {
			return true;
		}
		return false;
	}

    //Valida CNPJ***********************************************
	public static boolean validarCNPJ(String cnpj){
		cnpj = corrigeCNPJ(cnpj);

		if (!tamanhoCheckerCNPJ(cnpj)){
			System.out.println("CNPJ INVALIDO");
			return false;
		} if (!igualCheckerCNPJ(cnpj)){
			System.out.println("CNPJ INVALIDO!");
			return false;
		} if (!digitoCheckerCNPJ(cnpj)){
			System.out.println("CNPJ INVALIDO!");
			return false;
		}
		System.out.println("CNPJ VALIDO!");
		return true;
    }

    public static String corrigeCNPJ(String cnpjBruto) {
		return cnpjBruto.replaceAll("[^0-9]", "");
	}
	
    private static boolean tamanhoCheckerCNPJ(String cnpjAnalise) {
		if(cnpjAnalise.length()==14) {
			return true;
		}else {
			return false;
		}
	}

    private static boolean igualCheckerCNPJ(String cnpjAnalise) {
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
	private static int primeiroDigitoCorretoCNPJ(String cnpjAnalise) {
		int soma=0, resto;
		for (int i=0; i<4; i++) {
			soma += Character.getNumericValue(cnpjAnalise.charAt(i))*(5-i);  
		}
        for (int i=0; i<8; i++) {
            soma += Character.getNumericValue(cnpjAnalise.charAt(i+4))*(9-i);
        }
		resto = soma%11;
		if (resto<2) {
			return 0;
		}
		return 11-resto;
	}
	
	private static boolean validaPrimeiroCNPJ(String cnpjAnalise) {
		int primeiroDigito = Character.getNumericValue(cnpjAnalise.charAt(12));

		if (primeiroDigito == primeiroDigitoCorretoCNPJ(cnpjAnalise)) {
			return true;
		}
		return false;
	}

	//Valida segundo digito
	private static int segundoDigitoCorretoCNPJ(String cnpjAnalise) {
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
	
	private static boolean validaSegundoCNPJ(String cnpjAnalise) {
		int segundoDigito = Character.getNumericValue(cnpjAnalise.charAt(13));

		if (segundoDigito == segundoDigitoCorretoCNPJ(cnpjAnalise)) {
			return true;
		}
		return false;
	}
	

    private static boolean digitoCheckerCNPJ(String cnpjAnalise) {
		if (validaPrimeiroCNPJ(cnpjAnalise) && validaSegundoCNPJ(cnpjAnalise)) {
			return true;
		}
		return false;
	}

	public static int calculaIdade(java.util.Date dataNasc){
		Calendar nascimento = new GregorianCalendar();
		nascimento.setTime(dataNasc);

		Calendar hoje = Calendar.getInstance();

		int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
		nascimento.add(Calendar.YEAR, idade);

		if (hoje.before(nascimento)) {
			idade--;
		}
		return idade;
}

	public static boolean validarNome(String nome){
		if (!nome.matches("[a-zA-Z]+")){
			System.out.println("NOME INVALIDO");
		}
    	return nome.matches("[a-zA-Z]+");
	}
}
