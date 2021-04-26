/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsEx2;

import java.io.Serializable;
import java.util.List;
import java.util.Random;


public class Advertising implements Serializable{
    private double tv;
    private double radio;
    private double newspaper;
    private double sales;

    public Advertising(double tv, double radio, double newspaper, double sales) {
        this.tv = tv;
        this.radio = radio;
        this.newspaper = newspaper;
        this.sales = sales;
    }

    @Override
    public String toString() {
        return tv + "_" + radio + "_" + newspaper + "_" + sales;
    }

    public double getTV() {
        return tv;
    }

    public double getRadio() {
        return radio;
    }
    
    public double getNewspaper() {
        return newspaper;
    }
    
    public double getSales() {
        return sales;
    }
    
    public static double getTotalSales(List<Advertising> advertisings){
    	int i = 0;
    	double totalSales = 0;
    	while (i < advertisings.size()) {
    		totalSales += advertisings.get(i).getSales();
            i++;
        }
		return totalSales;
    }
}
