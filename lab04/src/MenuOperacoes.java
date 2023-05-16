public enum MenuOperacoes {
    CADASTRAR (1),
    CADASTRAR_CLIENTEPFPJ(11),
    TIPO_CLIENTE(15),
    CADASTRAR_VEICULO(12),
    CADASTRAR_SEGURADORA(13),
    VOLTAR_CADASTRAR(14),
    LISTAR(2),
    LISTAR_CLIENTE(21),
    LISTAR_SINISTROS_SEGURADORA(22),
    LISTAR_SINISTROS_CLIENTE(23),
    LISTAR_VEICULO_CLIENTE(24),
    LISTAR_VEICULO_SEGURADORA(25),
    VOLTAR_LISTAR(26),
    EXCLUIR (3),
    EXCLUIR_CLIENTE(31),
    EXCLUIR_VEICULO(32),
    EXCLUIR_SINISTRO(33),
    VOLTAR_EXCLUIR(34),
    GERAR_SINISTRO(4),
    TRANSFERIR_SEGURO(5),
    CALCULA_RECEITA(6),
    TROCA_SEGURADORA(7),
    SAIR (0),
    MENU_PRINCIPAL(100),
    MENU_CADASTROS(101),
    MENU_LISTAR(102),
    MENU_EXCLUIR(103),
    MENU_TIPOCLIENTE(104);

    public final int operacao ;
    
    MenuOperacoes (int operacao){
        this.operacao = operacao ;
    }
    public int getOperacao (){
        return this.operacao ;
    }
}
