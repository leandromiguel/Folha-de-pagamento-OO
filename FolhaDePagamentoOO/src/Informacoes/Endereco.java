package Informacoes;

import Exceptions.ExceptionsAndGetString;

public class Endereco
{
    private String rua;
    private String bairro;
    private int numeroCasa;
    private String cep;

    public Endereco()
    {
        setRua();
        setBairro();
        setCep();
        setNumeroCasa();
    }

    public void setRua()
    {
        System.out.println("Rua:");
        this.rua = ExceptionsAndGetString.getString();
    }

    public void setBairro()
    {
        System.out.println("Bairro:");
        this.bairro = ExceptionsAndGetString.getString();
    }

    public void setCep()
    {
        System.out.println("CEP:");
        this.cep = ExceptionsAndGetString.getString();
    }

    public void setNumeroCasa()
    {
        System.out.println("Número da casa:");
        this.numeroCasa = ExceptionsAndGetString.tryingCatchInt();
    }

    @Override
    public String toString()
    {
        return "Endereço:" + "\n"
                + "Rua - " + this.rua + "\n"
                + "Bairro - " + this.bairro + "\n"
                + "Número - " + this.numeroCasa + "\n"
                + "CEP - " + this.cep + "\n";
    }
}