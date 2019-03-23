/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.criptografia.proccess.uninorte.edu.co;


import java.math.BigDecimal;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.ArrayList;
/**
 *
 * @author Jorge
 */
public class Logaritmo_discreto {
    
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("ingrese numero primo: \n");
        BigInteger p, m, m2, g;
        ArrayList<Integer> Zp = new ArrayList<Integer>();
        boolean Generador;
        //m1 double value to obtain a sqrt
        double m1; 
        //m2 value of m1 convert on biginteger.
        p = new BigInteger(sc.nextLine()); 
        boolean prime; 
        Zp = mostrarZp(p);
        System.out.println("ingrese el valor del generador g: \n");
        g = new BigInteger(sc.nextLine());
        Generador = gesgenerador(g, p);
        if (Generador) {
            System.out.println("el valor de g: "+ g +" resulto " + Generador + ", entonces Si es un generador de Zp");
            prime = p.isProbablePrime(200);
            
            if (prime) {
                m = (p.subtract(BigInteger.ONE));
                System.out.println("m: "+ m);
                m1 = Math.sqrt(m.doubleValue());
                System.out.println("sqrt de m: "+ m1);

            }else{
                System.out.println("El numero "+ p +" ingresado no es primo.");
            }
        }else{
            System.out.println("el valor de g: "+ g +" resulto " +  Generador +  "entonces No es un generador de Zp");
        }
            
        
        
        
    }

    /***
     * mostrar el grupo fimito ciclico definido por p
     * @param p
     * @return 
     */
    private static ArrayList<Integer> mostrarZp(BigInteger p) {
        ArrayList<Integer> Zp = new ArrayList<Integer>();
        String Z = "Zp={"; 
        
        for (int i = 1; i <= p.byteValue() - 1; i++) {
            Zp.add(i);
        }
        for (int i = 0; i < Zp.size(); i++) {
            Z = Z + Zp.get(i) +",";
        }
        Z = Z.substring(0, Z.length()-1);
        Z = Z + "}"; 
        System.out.println(Z);
        return Zp;
    }

    private static boolean gesgenerador(BigInteger g, BigInteger p) {
        int n = p.intValue();
        int ng = g.intValue();
        int ip, ig;
        boolean Generador = false; 
        ArrayList<Integer> divp = new ArrayList<Integer>();
        ArrayList<Integer> divg = new ArrayList<Integer>();
        for (int i = 1; i <= n ; i++) {
            if (n % i == 0) {
                divp.add(i);
            }
        }
        for (int i = 1; i <= ng; i++) {
            if (ng % i == 0) {
                divg.add(i);
            }
        }
        
        for (int i = 0; i < divp.size(); i++) {
            ip = divp.get(i);
            for (int j = 0; j < divg.size(); j++) {
                ig = divg.get(j);
                if (ig == ip) {
                    Generador = false;                    
                }else{
                    Generador = true; 
                }
            }
        }
        
        return Generador; 
    }
}
