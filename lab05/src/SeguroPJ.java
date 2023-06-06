import java.util.Date;

public class SeguroPJ extends Seguro{
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente){
        super(dataInicio, dataFim, seguradora, 0, cliente);
        this.frota = frota;
        this.cliente = cliente;
    }
    
    public boolean autorizarCondutor(Condutor condutor) {
        getListaCondutores().add(condutor);
        System.out.println("Condutor autorizado");
        return true;
    }

    public boolean desautorizarCondutor(Condutor condutor){
        for(Condutor condutores: getListaCondutores()){
			if (condutores == condutor){
				getListaCondutores().remove(condutor);
                System.out.println("Condutor removido");
                return true;
			}
		}
        System.out.println("Condutor inexistente");
        return false;
    }

    @Override
    public boolean gerarSinistro(Condutor condutor, Sinistro sinistro){
        condutor.getListaSinistros().add(sinistro);
        this.getListaSinistros().add(sinistro);
        System.out.println("Sinistro gerado");
        return true;
    }

    public double calcularValor(Cliente cliente, Seguradora seguradora){
        double valorSeguro = 0;
        int quantidadeFuncionarios = getListaCondutores().size();
        int quantidadeVeiculos = 0;
        int AnosPosFundacao = Validacao.calculaIdade(((ClientePJ)cliente).getDataFundacao());

//        for(Frota frota: ((ClientePJ)cliente).getListaFrota()){
//            quantidadeVeiculos+= frota.getListaVeiculos().size();
//        }

        quantidadeVeiculos = getFrota().getListaVeiculos().size();


        int quantidadeSinistrosCliente = getListaCondutores().get(0).getListaSinistros().size();
        
        int quantidadeSinistrosCondutor = 0;
        for (Condutor condutor: getListaCondutores()){
            quantidadeSinistrosCondutor += condutor.getListaSinistros().size();
        }
        quantidadeSinistrosCondutor -= quantidadeSinistrosCliente;

        valorSeguro = CalcSeguro.VALOR_BASE.getTaxa()*(10+quantidadeFuncionarios)*(1+1/(quantidadeVeiculos+2))*(1+1/(AnosPosFundacao+2)) * (2+ (quantidadeSinistrosCliente/10)) * (5+(quantidadeSinistrosCondutor/10));
        return valorSeguro;
    }

    public Frota getFrota() {
        return frota;
    }
    public void setFrota(Frota frota) {
        this.frota = frota;
    }
    @Override
    public ClientePJ getCliente() {
        return cliente;
    }
    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return super.toString() + ",Frota: " + frota.getCode()+ ", Cliente: " + cliente.getNome();
    }
    
    
}