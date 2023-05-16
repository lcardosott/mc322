import java.util.Date;
import java.text.SimpleDateFormat;

public class ClientePJ extends Cliente{
    private String cnpj;
    private Date dataFundacao;
    private int qtdeFuncionarios;
    
    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao, int qtdeFuncionarios){
        super(nome, endereco, "PJ");
        this.dataFundacao = dataFundacao;
		this.cnpj = cnpj;
        this.qtdeFuncionarios = qtdeFuncionarios;
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

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

	public String trataData(Date data){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data);
		return (dataFormatada);
	}

    @Override
    public double calculaScore(){
        double score = CalcSeguro.VALOR_BASE.getTaxa() *(1+(qtdeFuncionarios/100)) * getListaVeiculo().size();
        return score;
    }

    public String toString() {
        return super.toString() + "  CNPJ: " + cnpj + "  Data Fundacao: " + trataData(dataFundacao) + "  Qtde. Funcionarios: " + qtdeFuncionarios;
    }    
}