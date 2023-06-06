import java.util.Date;

public class SeguroPF extends Seguro{
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente){
        super(dataInicio, dataFim, seguradora, 0, cliente);
        this.veiculo = veiculo;
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
        int idade = Validacao.calculaIdade(((ClientePF)cliente).getDataNascimento());
        int quantidadeVeiculos = ((ClientePF)cliente).getListaVeiculos().size();

        int quantidadeSinistrosCliente = getListaCondutores().get(0).getListaSinistros().size();
        int quantidadeSinistrosCondutor = 0;
        for (Condutor condutor: getListaCondutores()){
            quantidadeSinistrosCondutor += condutor.getListaSinistros().size();
        }
        quantidadeSinistrosCondutor -= quantidadeSinistrosCliente;

        if (idade<30){
            valorSeguro = CalcSeguro.VALOR_BASE.getTaxa()* CalcSeguro.FATOR_18_30.getTaxa()* (1+1/(quantidadeVeiculos+2))*(2+(quantidadeSinistrosCliente/10)) * (5+(quantidadeSinistrosCondutor/10));
        }else if(idade >30 && idade<60){
            valorSeguro = CalcSeguro.VALOR_BASE.getTaxa()* CalcSeguro.FATOR_30_60.getTaxa()* (1+1/(quantidadeVeiculos+2))*(2+(quantidadeSinistrosCliente/10)) * (5+(quantidadeSinistrosCondutor/10));
        }else if(idade>60){
            valorSeguro = CalcSeguro.VALOR_BASE.getTaxa()* CalcSeguro.FATOR_60_90.getTaxa()* (1+1/(quantidadeVeiculos+2))*(2+(quantidadeSinistrosCliente/10)) * (5+(quantidadeSinistrosCondutor/10));
        }
        return valorSeguro;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    @Override
    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return super.toString() + " ,Veiculo: " + veiculo.getModelo() + ", Cliente: " + cliente.getNome();
    }


    
}
