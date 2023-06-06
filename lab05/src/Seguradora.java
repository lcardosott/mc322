
import java.util.ArrayList;

public class Seguradora {
	private final String cnpj;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Seguro> listaSeguros;
	
		
	public Seguradora(String cnpj, String nome, String telefone, String endereco, String email) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSeguros = new ArrayList<Seguro>();
	}

	//Getters e setters
	public String getCnpj() {
        return cnpj;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

	public boolean listarClientes(){

		if ((!Validacao.verificaExistenciaCliente(this, 1))){
			return false;
		}
		//lista todos os clientes de uma seguradora
		System.out.println("****************************");
		System.out.println("  Clientes da Seguradora:");
		System.out.println("****************************");
		for (int i = 0; i < listaClientes.size(); i++) {
				System.out.println((i+1) +"-"+(listaClientes.get(i).getNome()));
			}
		System.out.println("****************************");	
		return true;
	}

	public boolean listarSeguros(){
			if ((!Validacao.verificaExistenciaSeguro(this, 1))){
				return false;
			}
			//lista todos os clientes de uma seguradora
			System.out.println("****************************");
			System.out.println("  Seguros da Seguradora:");
			System.out.println("****************************");
			for (int i = 0; i < listaSeguros.size(); i++) {
				System.out.println(i+1 + "-" +(listaSeguros.get(i).getId()) + " : " + listaSeguros.get(i).getCliente().toString());
			}
			System.out.println("****************************");	
			return true;
	}

	public boolean listaSeguroPorCliente(Cliente cliente){
		if ((!Validacao.verificaSeguroCliente(this, cliente, 1))){
			return false;
		}
		int i=0;
		System.out.println("****************************");
		System.out.println("  Seguros de "+ cliente.getNome() + ":");
		System.out.println("****************************");
        for (Seguro seguro: getListaSeguros()){
			if(seguro.getCliente()==cliente){
				System.out.println(i+1 + " - " + seguro.toString());
				i+=1;
			}
        }
		System.out.println("****************************");
        return true;
    }

	public boolean listaSinistroPorCliente(Cliente cliente){
		if ((!Validacao.verificaSeguroCliente(this, cliente, 1))){
			return false;
		}
		int i=0;
		for (Seguro seguro: getListaSeguros()){
			if(seguro.getCliente()==cliente){
				seguro.listaSinistroPorSeguro(seguro);
				i+=1;
			}
        }
		return true;
	}


    public boolean gerarSeguro(Seguro seguro) {
        if(seguro != null && !listaSeguros.contains(seguro)){
            listaSeguros.add(seguro);
			System.out.println("Seguro cadastrado com sucesso");
            return true;
        }else{
            System.out.println("Seguro não cadastrado");
            return false;
        }
    }

    public boolean cancelarSeguro(Seguro seguro){
        if(seguro != null && listaSeguros.contains(seguro)){
            listaSeguros.remove(seguro);
			System.out.println("Seguro cancelado com sucesso");
            return true;
        }else{
            System.out.println("Seguro fora do banco de dador");
            return false;
        }
	}

	public void cadastrarCliente(Cliente cliente){
		//adiciona um cliente na lista de clientes de uma seguradora
		if (listaClientes.contains(cliente)){
			System.out.println("Cliente já cadastrado");
			return;
		}
		listaClientes.add(cliente);
		System.out.println("Cliente cadastrado com sucesso");
	}
	
	public void removerCliente(Cliente cliente){
		// remove um cliente da lista de clientes de uma seguradora
		if (!listaClientes.contains(cliente)){
			System.out.println("Cliente fora do banco de dados");
			return;
		}
		listaClientes.remove(cliente);
		removerSegurosCliente(cliente);
		System.out.println("Cliente removido com sucesso");
	}

	public void removerSegurosCliente(Cliente cliente) {
		for(Seguro seguro:listaSeguros){
			if (seguro.getCliente() == cliente){
				listaSeguros.remove(seguro);
			}
		}
	}

	public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente){
		ArrayList<Seguro> seguros = new ArrayList<Seguro>();
		for (Seguro seguro : getListaSeguros()){
			if (seguro.getCliente() == cliente){
				seguros.add(seguro);
			}
		}
		return seguros;
	}

	public Seguro getSeguroDoCliente(Cliente cliente, int i){
		int aux=0;
        for (Seguro seguro: getListaSeguros()){
			if(seguro.getCliente()==cliente){
				aux+=1;
				if(aux==i){
					return seguro;
				} 
			}
        }
		return null;
    }


	public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente){
		ArrayList<Sinistro> sinistros = new ArrayList<Sinistro>();
		for (Seguro seguro : getListaSeguros()){
			if (seguro.getCliente() == cliente){
				for (Sinistro sinistro: seguro.getListaSinistros()){
					sinistros.add(sinistro);
				}
			}
		}
		return sinistros;
	}

	public double calcularReceita(){
		double valor = 0, parcela;
		for(Seguro seguro: getListaSeguros()){

			parcela = seguro.calcularValor(seguro.getCliente(), this);
			seguro.setValorMensal(parcela);
			valor += parcela;
		}
		return valor;
	}

	public boolean listaClientePorSeguradora(){
        for (int i=0; i<getListaClientes().size(); i++){
            System.out.println(i+1 + " - " + getListaClientes().get(i).toString());
        }
        return true;
    }
}

