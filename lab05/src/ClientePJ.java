import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientePJ extends Cliente{
    private final String cnpj;
    private Date dataFundacao;
    private ArrayList<Frota> listaFrota;
    
    public ClientePJ(String nome, String telefone,String endereco, String email, String cnpj, Date dataFundacao){
        super(nome,telefone, endereco, email);
		this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.listaFrota = new ArrayList<Frota>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public Date getdataFundacao() {
        return dataFundacao;
    }
    
    public void setdataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public ArrayList<Frota> getListaFrota() {
        return listaFrota;
    }

    public void setListaFrota(ArrayList<Frota> listaFrota) {
        this.listaFrota = listaFrota;
    }

    public boolean cadastrarFrota(Frota frota) {
        if(frota != null && !listaFrota.contains(frota)){
            listaFrota.add(frota);
            System.out.println("Frota cadastrada");
            return true;
        }else{
            System.out.println("Frota não cadastrada");
            return false;
        }
    }





    
    public boolean atualizarFrota(Scanner scanner){
        int numero;
        if (!listaFrotaPorCliente()){
            return false;
        }
        numero = Integer.parseInt(scanner.nextLine());
        Frota frota = getListaFrota().get(numero-1);
        System.out.println("****************************");
        System.out.println("      OPERACAO:");
        System.out.println("****************************");
        System.out.println("1 - Remover Frota");
        System.out.println("2 - Remover Veiculo");
        System.out.println("****************************");
        numero = Integer.parseInt(scanner.nextLine());

        if (numero==1){
            getListaFrota().remove(frota); 
            System.out.println("Frota removida com sucesso");   

        }if (numero==2){
            if(!listaVeiculoPorFrota(frota)){
                return false;
            }
            numero = Integer.parseInt(scanner.nextLine());
            Veiculo veiculo = frota.getListaVeiculos().get(numero-1);
            frota.removeVeiculo(veiculo);
        }
        return true;
    }



        


    public boolean listaVeiculoPorFrota(Frota frota){
        if(!Validacao.verificaExistenciaVeiculoFrota(frota, 1)){
            return false;
        }
        System.out.println("****************************");
        System.out.println("  Veiculos de " + frota.getCode() + ":");
        System.out.println("****************************");
        for (int i=0; i<frota.getListaVeiculos().size(); i++){
            System.out.println(i+1 + " - " + frota.getListaVeiculos().get(i).toString());
        }
        System.out.println("****************************");
        return true;
    }

    public boolean listaFrotaPorCliente(){
        if(!Validacao.verificaExistenciaFrota(this, 1)){
            return false;
        }
        System.out.println("****************************");
        System.out.println("  Frotas de " + getNome() + ":");
        System.out.println("****************************");
        for (int i=0; i<getListaFrota().size(); i++){
            System.out.println(i+1 + " - " + getListaFrota().get(i).getCode());
        }
        System.out.println("****************************");
        return true;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    @Override
    public String toString() {
        return super.toString() +", CNPJ: " + cnpj + ", Data Fundacao: " + Validacao.trataData(dataFundacao);
    }
}