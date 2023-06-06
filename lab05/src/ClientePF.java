import java.util.Date;
import java.util.ArrayList;

public class ClientePF extends Cliente{
    private final String cpf;
    private char genero;
    private String educacao;
    private Date dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;


    public ClientePF(String nome, String telefone, String endereco, String email,String cpf, char genero, String educacao, Date dataNascimento){
		super(nome,telefone, endereco, email);
        this.cpf =cpf;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;	
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    public String getCpf() {
        return cpf;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
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

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    
    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if(veiculo != null && !listaVeiculos.contains(veiculo)){
            listaVeiculos.add(veiculo);
            System.out.println("Veiculo cadastrado");
            return true;
        }else{
            System.out.println("Veiculo não cadastrado");
            return false;
        }
    }

    public boolean removeVeiculo(Veiculo veiculo){
        if(veiculo != null && listaVeiculos.contains(veiculo)){
            listaVeiculos.remove(veiculo);
            System.out.println("Veiculo removido");
            return true;
        }else{
            System.out.println("Veiculo não removido");
            return false;
        }
    }

    public boolean listaVeiculoPorCliente(){
        if(!Validacao.verificaExistenciaVeiculo(this, 1)){
            return false;
        }
        System.out.println("****************************");
        System.out.println("  Veiculos de " + getNome() + ":");
        System.out.println("****************************");
        for (int i=0; i<getListaVeiculos().size(); i++){
            System.out.println(i+1 + " - " + getListaVeiculos().get(i).toString());
        }
        System.out.println("****************************");
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", CPF:" + cpf + ", Genero: " + genero + ", educacao: " + educacao + ", Data Nascimento: " + Validacao.trataData(dataNascimento);
    }
}