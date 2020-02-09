package Empregados;

import Exceptions.ExceptionsAndGetString;

public class Assalariado extends Empregado {
    private double salario;

    public Assalariado(int codigo, String nome) {
        super(codigo, nome);
        setSalario();
    }

    public void setSalario() {
        System.out.println("Salário Mensal:");
        this.salario = ExceptionsAndGetString.tryingCatchDouble(900,9000);
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Salário - " + this.salario + "\n";
    }
}
