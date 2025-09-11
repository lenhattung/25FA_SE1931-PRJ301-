/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tungi
 */
public class MyCalculator {

    private double a, b;
    private String op;

    public MyCalculator() {
    }

    public MyCalculator(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public MyCalculator(double a, double b, String op) {
        this.a = a;
        this.b = b;
        this.op = op;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public double add() {
        return this.a + this.b;
    }

    public double substract() {
        return this.a - this.b;
    }

    public double multiply() {
        return this.a * this.b;
    }

    public double divide() {
        return this.a / this.b;
    }

    public double calculate(String op) {
        if (op.equals("+")) {
            return this.add();
        } else if (op.equals("-")) {
            return this.substract();
        } else if (op.equals("*")) {
            return this.multiply();
        } else if (op.equals("/")) {
            return this.divide();
        }
        return 0;
    }

}
