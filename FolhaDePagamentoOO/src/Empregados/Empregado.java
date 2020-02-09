package Empregados;

import Exceptions.*;
import Informacoes.*;
import Main.InterfaceUsuario;
import java.util.ArrayList;

public class Empregado
{
    private int codigo;
    private ArrayList<Integer> diaPagamento;
    private String nome;
    private String funcao;
    private Endereco endereco;
    private FormaPagamento formaPagamento;
    private boolean sindicato;
    private double sindicatoValor;
    private ArrayList<Double> taxaServico = new ArrayList<>();
    private ArrayList<String> descricaoServico = new ArrayList<>();

    public Empregado(int codigo, String nome)
    {
        this.codigo = codigo;
        diaPagamento = new ArrayList<>();
        setDiaPagamento(InterfaceUsuario.setDiaPagamentoMenu());
        setNome(nome);
        setFuncao();
        endereco = new Endereco();
        setFormaPagamento();
        setSindicato();
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setDiaPagamento(int dias)
    {
        switch(dias)
        {
            case 1:
                System.out.println("Dia de pagamento:");
                this.diaPagamento.add(ExceptionsAndGetString.tryingCatchInt(1,31));
                break;
            case 2:
                System.out.println("O pagamento será realizado nos dias 01 e 15 de cada mês.");
                this.diaPagamento.add(1);
                this.diaPagamento.add(15);
                break;
            case 3:
                System.out.println("O pagamento será realizado nos dias 01, 07, 15 e 23 de cada mês.");
                this.diaPagamento.add(1);
                this.diaPagamento.add(7);
                this.diaPagamento.add(15);
                this.diaPagamento.add(23);
                break;
        }
    }

    public void setFuncao()
    {
        System.out.println("Função:");
        this.funcao = ExceptionsAndGetString.getString();
    }

    public void setFormaPagamento()
    {
        int option = InterfaceUsuario.formaPagamentoMenu();
        switch(option)
        {
            case 1:
                this.formaPagamento = FormaPagamento.ChequePorCorreios;
                break;
            case 2:
                this.formaPagamento = FormaPagamento.ChequeEmMaos;
                break;
            case 3:
                this.formaPagamento = FormaPagamento.Deposito;
                break;
        }
    }

    public void setSindicato()
    {
        if(InterfaceUsuario.setSindicatoMenu() == 1)
        {
            this.sindicato = true;
            System.out.println("Taxa sindical:");
            this.sindicatoValor = ExceptionsAndGetString.tryingCatchDouble(0,20);
        }
        else
        {
            this.sindicato = false;
            this.sindicatoValor = 0;
        }
    }

    public void setTaxaServico(double valor)
    {
        this.taxaServico.add(valor);
    }

    public void setDescricaoServico(String descricao)
    {
        this.descricaoServico.add(descricao);
    }

    public String getNome()
    {
        return this.nome;
    }

    public int getCode()
    {
        return this.codigo;
    }

    public ArrayList<Integer> getDiaPagamento()
    {
        return diaPagamento;
    }

    public ArrayList<Double> getTaxaServico()
    {
        return this.taxaServico;
    }

    public ArrayList<String> getDescricaoServico()
    {
        return this.descricaoServico;
    }

    public double getSindicatoValor()
    {
        return sindicatoValor;
    }

    public String getFuncao()
    {
        return funcao;
    }

    public boolean eSindicalista()
    {
        return sindicato;
    }

    public void setRua()
    {
        endereco.setRua();
    }

    public void setBairro()
    {
        endereco.setBairro();
    }

    public void setCep()
    {
        endereco.setCep();
    }

    public void setNumeroCasa()
    {
        endereco.setNumeroCasa();
    }

    public void setNovoDiaPagamento(int dias)
    {
        System.out.println("Dias de pagamento:");
        int[] array = new int[30];
        for(int i = 0; i < dias; i++)
            array[i] = ExceptionsAndGetString.tryingCatchInt(1,31);
        if(!InterfaceUsuario.undo())
        {
            diaPagamento.clear();
            for(int i = 0; i < dias; i++)
                this.diaPagamento.add(array[i]);
        }
    }

    @Override
    public String toString()
    {
        return "Código - " + this.codigo + "\n"
                + "Funcionário - " + this.nome + "\n"
                + "Função - " + this.funcao + "\n"
                + endereco.toString()
                + "Método de Pagamento - " + this.formaPagamento + "\n"
                + "Participação em Sindicato - " + this.sindicato + "\n"
                + "Contribuição Sindical - R$" + this.sindicatoValor + "\n";
    }
}