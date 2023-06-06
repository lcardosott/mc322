import java.util.ArrayList;

public class Frota {
    private String code;
    private ArrayList<Veiculo> listaVeiculos;

    public Frota(String code) {
        this.code = code;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    public boolean addVeiculo(Veiculo veiculo) {
        if(veiculo != null && !listaVeiculos.contains(veiculo)){
            System.out.println("Veiculo cadastrado");
            listaVeiculos.add(veiculo);

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
            System.out.println("Veiculo não encontrado");
            return false;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public boolean listaVeiculoPorFrota(){
        System.out.println("****************************");
		System.out.println("  Veiculos de " + getCode() + ":");
		System.out.println("****************************");
        for (int i=0; i<getListaVeiculos().size(); i++){
            System.out.println(i+1 + " - " + getListaVeiculos().get(i).toString());
        }
        return true;
    }

    @Override
    public String toString() {
        return "Code: " + code;
    }

    
}
