package Empregados;

import Exceptions.*;

public class Horista extends Empregado
{
    private double horistaSalario;
    private double tempoTrabalho;
    private double horaextra;

    public Horista(int codigo, String nome)
    {
        super(codigo,nome);
        setHoristaSalario();
        this.tempoTrabalho = 0;
        this.horaextra = 0;
    }

    public void setHoristaSalario()
    {
        System.out.println("Salário por hora:");
        this.horistaSalario = ExceptionsAndGetString.tryingCatchDouble(5,50);
    }

    public void resetTempo()
    {
        this.horaextra = 0;
        this.tempoTrabalho = 0;
    }

    public double getHoristaSalario()
    {
        return horistaSalario;
    }

    public double getTempoTrabalho()
    {
        return tempoTrabalho;
    }

    public double getHoraExtra()
    {
        return horaextra;
    }

    public void addTempoTrabalho(double tempo)
    {
        if(tempo > 8)
        {
            this.tempoTrabalho += 8;
            this.horaextra += tempo-8;
        }
        else this.tempoTrabalho += tempo;
    }

    @Override
    public String toString()
    {
        return super.toString()
                + "Salário por Hora - R$" + this.horistaSalario + "\n"
                + "Horas Trabalhadas Desde o Último Pagamento - " + (this.tempoTrabalho+this.horaextra) + "h\n";
    }
}