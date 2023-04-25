package lab02;

public class Main {
	public static void main(String[] args) {
		
		Cliente cliente = new Cliente("Lucas Cardoso", "493150898-71", "02/02/2004", 19, "Rua dos Uapes 175");
		Sinistro sinistro = new Sinistro("02/02/2022", "R. Sérgio Boarque de Holanda");
		Veiculo veiculo = new Veiculo("FFL-3295","Ford" ,"Ka");
		Seguradora seguradora = new Seguradora("Valida Seguros", "3232-9919", "validaseguros@gmail.com", "Rua 2");
		boolean verificador = cliente.validarCPF(cliente.getCpf());
		
		
		//System.out.println(cliente.toString());
		System.out.println(cliente.toString());
		if (verificador) {
			System.out.println("CPF VALIDO");
		} else {
			System.out.println("CPF INVALIDO");
		}
		
	}
}
