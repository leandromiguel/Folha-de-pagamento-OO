package Empregados;

import Exceptions.ExceptionsAndGetString;

public class Comissionado extends Assalariado {
    private double porcentagemComissao;
    private double quantidadeVendas;

    public Comissionado(int codigo, String nome) {
        super(codigo, nome);
        this.quantidadeVendas = 0;
        setPorcentagemComissao();
    }

    public void setPorcentagemComissao() {
        System.out.println("Porcentagem de Comissão por Venda:");
        this.porcentagemComissao = ExceptionsAndGetString.tryingCatchDouble(0,100);
    }

    public void setQuantidadeVendas(double valor) {
        this.quantidadeVendas += valor;
    }

    public void resetQuantidadeVendas() {
        this.quantidadeVendas = 0;
    }

    public double getPorcentagemComissao() {
        return porcentagemComissao;
    }

    public double getQuantidadeVendas() {
        return quantidadeVendas;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Porcentagem de Comissão - " + this.porcentagemComissao + "%\n"
                + "Valor Total de Vendas Desde o Último Pagamento - R$" + this.quantidadeVendas + "\n";
    }
}