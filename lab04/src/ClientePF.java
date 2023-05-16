import java.util.Date;
import java.text.SimpleDateFormat;


public class ClientePF extends Cliente{
    private String cpf;
    private char genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private char classeEconomica;

    public ClientePF(String nome, String endereco,String cpf, char genero, Date dataLicenca,String educacao,Date dataNascimento, char classeEconomica){
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

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
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

    public char getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(char classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

	public String trataData(Date data){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data);
		return (dataFormatada);
	}

    @Override
    public double calculaScore(){
        int idade = Validacao.calculaIdade(dataNascimento);
        double score = 0;
        if (idade<30){
            score = CalcSeguro.VALOR_BASE.getTaxa() *CalcSeguro.FATOR_18_30.getTaxa() * getListaVeiculo().size();
        }else if(idade >30 && idade<60){
            score = CalcSeguro.VALOR_BASE.getTaxa() *CalcSeguro.FATOR_30_60.getTaxa()* getListaVeiculo().size();
        }else if(idade>60){
            score = CalcSeguro.VALOR_BASE.getTaxa() *CalcSeguro.FATOR_60_90.getTaxa()* getListaVeiculo().size();
        }
        return score;
    }
    @Override
    public String toString() {
        return super.toString() + "  CPF: " + cpf + "  Genero: "+ genero + "  Data Licensa: " +trataData(dataLicenca) + "  Educacao: " + educacao + "  Data Nascimento: " + trataData(dataNascimento) + "  Classe Economica: " + classeEconomica;
    }
}