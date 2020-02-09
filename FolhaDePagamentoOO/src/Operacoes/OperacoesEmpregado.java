package Operacoes;

import Empregados.*;
import Exceptions.ExceptionsAndGetString;
import Main.InterfaceUsuario;

/* import javax.jws.soap.SOAPBinding; */
import java.util.*;

public class OperacoesEmpregado
{
    private int codigo;
    private ArrayList<Empregado> empregados;

    public OperacoesEmpregado()
    {
        this.codigo = 0;
        this.empregados = new ArrayList<>();
    }

    private int findEmpregado(int codigo)
    {
        for(int i = 0; i < empregados.size(); i++)
            if(empregados.get(i).getCode() == codigo) return i;
        System.out.println("Funcionário não encontrado.");
        return -1;
    }

    private String checkNome()
    {
        System.out.println("*Não é possível adicionar funcionários com o mesmo nome.");
        System.out.println("Nome:");
        String nome = ExceptionsAndGetString.getString();
        for(int i = 0; i < empregados.size(); i++)
            if(empregados.get(i).getNome().equals(nome)) return checkNome();
        return nome;
    }

    public void addEmpregado(int tipo)
    {
        if(empregados.size() == 10) System.out.println("Limite máximo de 10 funcionários atigido.");
        else
        {
            switch(tipo)
            {
                case 1:
                    empregados.add(new Horista(codigo,checkNome()));
                    break;
                case 2:
                    empregados.add(new Assalariado(codigo,checkNome()));
                    break;
                case 3:
                    empregados.add(new Comissionado(codigo,checkNome()));
                    break;
            }
            codigo++;
            System.out.println("Funcionário adicionado com sucesso.");
        }
        if(InterfaceUsuario.undo()) empregados.remove(empregados.size()-1);
    }

    public void removeEmpregado(int codigo)
    {
        if(!InterfaceUsuario.undo())
        {
            int i = findEmpregado(codigo);
            if(i != -1)
            {
                empregados.remove(i);
                System.out.println("Funcionário demitido com sucesso.");
            }
        }
    }

    public void printEmpregados()
    {
        for(int i = 0; i < empregados.size(); i++)
            System.out.println(empregados.get(i).toString());
    }

    public void setCartaoPonto(int codigo)
    {
        if(!InterfaceUsuario.undo())
        {
            int i = findEmpregado(codigo);
            if(i != -1)
            {
                if(empregados.get(i) instanceof Horista)
                {
                    double check = InterfaceUsuario.addTempoTrabalhoMenu();
                    if(!InterfaceUsuario.undo())((Horista) empregados.get(i)).addTempoTrabalho(check);
                }
                else System.out.println("Funcionário selecionado não é do tipo horista.");
            }
        }
    }

    public void setResultadoVendas(int codigo)
    {
        int i = findEmpregado(codigo);
        if(i != -1)
        {
            if(empregados.get(i) instanceof Comissionado)
            {
                double check = InterfaceUsuario.addResultadoVendasMenu();
                if(!InterfaceUsuario.undo())((Comissionado) empregados.get(i)).setQuantidadeVendas(check);
            }
            else System.out.println("Funcionário selecionado não é do tipo comissaoado.");
        }
    }

    public void setTaxaServico(int codigo)
    {
        int i = findEmpregado(codigo);
        if(i != -1)
        {
            if(empregados.get(i).eSindicalista())
            {
                double taxa = InterfaceUsuario.taxaServicoMenu();
                String descricao = InterfaceUsuario.descricaoServicoMenu();
                if(!InterfaceUsuario.undo())
                {
                    empregados.get(i).setTaxaServico(taxa);
                    empregados.get(i).setDescricaoServico(descricao);
                }
            }
            else System.out.println("Funcionário selecionado não faz parte do sindicato.");
        }
    }

    public void alterarDados(int opcao, int codigo)
    {
        int i = findEmpregado(codigo);
        if(i != -1)
        {
            switch(opcao)
            {
                case 1:
                    empregados.get(i).setNome(checkNome());
                    break;
                case 2:
                    empregados.get(i).setFuncao();
                    break;
                case 3:
                    empregados.get(i).setRua();
                    break;
                case 4:
                    empregados.get(i).setBairro();
                    break;
                case 5:
                    empregados.get(i).setNumeroCasa();
                    break;
                case 6:
                    empregados.get(i).setCep();
                    break;
                case 7:
                    empregados.get(i).setDiaPagamento(InterfaceUsuario.setDiaPagamentoMenu());
                    break;
                case 8:
                    empregados.get(i).setFormaPagamento();
                    break;
                case 9:
                    empregados.get(i).setSindicato();
                    break;
                case 10:
                    if(empregados.get(i) instanceof Comissionado) ((Comissionado) empregados.get(i)).setPorcentagemComissao();
                    else System.out.println("Funcionário escolhido não é do tipo comissaoado.");
                    break;
                case 11:
                    if(empregados.get(i) instanceof Horista) ((Horista) empregados.get(i)).setHoristaSalario();
                    else System.out.println("Funcionário escolhido não é do tipo horista.");
                    break;
                case 12:
                    if(empregados.get(i) instanceof Assalariado) ((Assalariado) empregados.get(i)).setSalario();
                    else System.out.println("Funcionário escolhido não é do tipo assalariado.");
                    break;
            }
        }
    }

    private boolean checkDiaPagamento(int index, int dia)
    {
        for(int i = 0; i < empregados.get(index).getDiaPagamento().size(); i++)
            if(empregados.get(index).getDiaPagamento().get(i) == dia) return true;
        return false;
    }

    private double checkDescontos(double total, int index)
    {
        for(int i = 0; i < empregados.get(index).getTaxaServico().size(); i++)
        {
            if((total - empregados.get(index).getTaxaServico().get(i)) > 0)
            {
                total -= empregados.get(index).getTaxaServico().get(i);
                System.out.println("- R$" + empregados.get(index).getTaxaServico().get(i) + "\tDescrição: " + empregados.get(index).getDescricaoServico().get(i));
                empregados.get(index).getTaxaServico().remove(i);
                empregados.get(index).getDescricaoServico().remove(i);
                i--;
            }
            else
            {
                System.out.println("Demais cobranças serão feitas na próxima folha pois o salário do funcionário não pode ser negativo.");
                return total;
            }
        }
        return total;
    }

    public void setPagamento(int dia)
    {
        if(!InterfaceUsuario.undoPagamento(dia))
        {
            for(int i = 0; i < empregados.size(); i++)
            {
                if(checkDiaPagamento(i,dia))
                {
                    double total;
                    System.out.println("Nome: " + empregados.get(i).getNome());
                    System.out.println("Função: " + empregados.get(i).getFuncao());
                    if(empregados.get(i) instanceof Comissionado)
                    {
                        total = ((Assalariado) empregados.get(i)).getSalario();
                        System.out.println("Salário Bruto: R$" + total);
                        double comissao = ((Comissionado) empregados.get(i)).getQuantidadeVendas()*((Comissionado) empregados.get(i)).getPorcentagemComissao()/100;
                        total += comissao;
                        System.out.println("+ R$" + comissao + "\tDescrição: Comissão");
                        ((Comissionado) empregados.get(i)).resetQuantidadeVendas();
                        if(empregados.get(i).eSindicalista())
                        {
                            System.out.println("- R$" + empregados.get(i).getSindicatoValor() + "\tDescrição: Taxa Sindical");
                            total -= empregados.get(i).getSindicatoValor();
                        }
                        total = checkDescontos(total,i);
                        System.out.println("Salário Líquido: R$" + total);
                    }
                    else if(empregados.get(i) instanceof Assalariado)
                    {
                        total = ((Assalariado) empregados.get(i)).getSalario();
                        System.out.println("Salário Bruto: R$" + total);
                        if(empregados.get(i).eSindicalista())
                        {
                            System.out.println("- R$" + empregados.get(i).getSindicatoValor() + "\tDescrição: Taxa Sindical");
                            total -= empregados.get(i).getSindicatoValor();
                        }
                        total = checkDescontos(total,i);
                        System.out.println("Salário Líquido: R$" + total);
                    }
                    if(empregados.get(i) instanceof Horista)
                    {
                        double tempoTrabalho = ((Horista) empregados.get(i)).getTempoTrabalho()*((Horista) empregados.get(i)).getHoristaSalario();
                        double horaextra = ((Horista) empregados.get(i)).getHoraExtra()*((Horista) empregados.get(i)).getHoristaSalario()*1.5;
                        total = tempoTrabalho + horaextra;
                        System.out.println("+ R$" + tempoTrabalho + "\tDescrição: Horas Trabalhadas");
                        System.out.println("+ R$" + horaextra + "\tDescrição: Horas Extras");
                        ((Horista) empregados.get(i)).resetTempo();
                        if(empregados.get(i).eSindicalista())
                        {
                            System.out.println("- R$" + empregados.get(i).getSindicatoValor() + "\tDescrição: Taxa Sindical");
                            total -= empregados.get(i).getSindicatoValor();
                        }
                        total = checkDescontos(total,i);
                        System.out.println("Salário Líquido: R$" + total);
                    }
                    System.out.println();
                }
            }
        }
    }

    public void setNovoDiaPagamento(int codigo)
    {
        codigo = findEmpregado(codigo);
        if(codigo != -1) empregados.get(codigo).setNovoDiaPagamento(InterfaceUsuario.setNovoDiaPagamentoMenu());
    }
}