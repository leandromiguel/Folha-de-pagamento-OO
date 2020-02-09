package Main;

import Operacoes.OperacoesEmpregado;

public class Main
{
    public static void main(String args[])
    {
        int opcao;
        OperacoesEmpregado operacoesEmpregado = new OperacoesEmpregado();

        do
        {
            opcao = InterfaceUsuario.mainMenu();
            switch(opcao)
            {
                case 1:
                    operacoesEmpregado.addEmpregado(InterfaceUsuario.addEmpregadoMenu());
                    break;
                case 2:
                    operacoesEmpregado.removeEmpregado(InterfaceUsuario.empregadoCodeMenu());
                    break;
                case 3:
                    operacoesEmpregado.printEmpregados();
                    break;
                case 4:
                    operacoesEmpregado.setCartaoPonto(InterfaceUsuario.empregadoCodeMenu());
                    break;
                case 5:
                    operacoesEmpregado.setResultadoVendas(InterfaceUsuario.empregadoCodeMenu());
                    break;
                case 6:
                    operacoesEmpregado.setTaxaServico(InterfaceUsuario.empregadoCodeMenu());
                    break;
                case 7:
                    operacoesEmpregado.alterarDados(InterfaceUsuario.alterarDadosMenu(),InterfaceUsuario.empregadoCodeMenu());
                    break;
                case 8:
                    operacoesEmpregado.setPagamento(InterfaceUsuario.diaPagamentoMenu());
                    break;
                case 9:
                    operacoesEmpregado.setNovoDiaPagamento(InterfaceUsuario.empregadoCodeMenu());
                    break;
            }
        }while(opcao != 10);
    }
}