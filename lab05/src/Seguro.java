import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public abstract class Seguro{
    private static ArrayList<Integer> ids = new ArrayList<Integer>();
	private static final int MAX = 100;// Define o valor maximo de clientes
    final private int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;
    private double valorMensal;

    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora, double valorMensal, Cliente cliente) {
        this.id = idGenerator();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaCondutores = new ArrayList<Condutor>();
        this.listaCondutores.add(new Condutor(null, cliente.getNome(), null, null, null, null));
        this.valorMensal = valorMensal;
    }

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

    public abstract boolean desautorizarCondutor(Condutor condutor);
    public abstract boolean autorizarCondutor(Condutor condutor);

    public abstract double calcularValor(Cliente cliente, Seguradora seguradora);

    public abstract boolean gerarSinistro(Condutor condutor, Sinistro sinistro);

    
    public int getId() {
        return id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }
    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }
    public double getValorMensal() {
        return valorMensal;
    }
    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public Cliente getCliente() {
        return null;
    }

    public boolean listaSinistroPorSeguro(Seguro seguro){
        if(!Validacao.verificaExistenciaSeguroCliente(seguro , 1)){
            return false;
        }
        System.out.println("****************************");
		System.out.println("  Sinistros de " + seguro.getId() + ":");
		System.out.println("****************************");
        for (int i=0; i<getListaSinistros().size(); i++){
            System.out.println(i+1 + " - " + getListaSinistros().get(i).getId());
        }
        return true;
    }

    public boolean listaCondutorPorSeguro(){
        if(!Validacao.verificaExistenciaCondutor(this, 1)){
            return false;
        }
        System.out.println("****************************");
		System.out.println("  Condutores de " + getCliente().getNome() + ":");
		System.out.println("****************************");
        for (int i=0; i<getListaCondutores().size(); i++){
            System.out.println(i+1 + " - " + getListaCondutores().get(i).getNome());
        }
        System.out.println("****************************");
        return true;
    }

    public String toString() {
        return "Id:" + id + ", dataInicio=" + Validacao.trataData(dataInicio) + ", dataFim: " + Validacao.trataData(dataFim)+ ", Valor Mensal: "+ valorMensal;
    }
    
}