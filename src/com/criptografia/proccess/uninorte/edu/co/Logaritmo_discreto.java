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
import java.util.Hashtable;

/**
 *
 * @author Jorge
 */
public class Logaritmo_discreto {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("ingrese numero primo: \n");
        BigInteger p, m, y, g, row1;
        int mi; 
        ArrayList<Integer> Zp = new ArrayList<Integer>();
        boolean Generador;
        //m1 double value to obtain a sqrt
        Double m1;

        p = new BigInteger(sc.nextLine());
        boolean prime;

        prime = p.isProbablePrime(200);

        if (prime) {
            Zp = mostrarZp(p);
            System.out.println("ingrese el valor del generador g: ");
            g = new BigInteger(sc.nextLine());
            Generador = gesgenerador(g, p);
            if (Generador) {
                System.out.println("el valor de g: " + g + " resulto " + Generador + ", entonces Si es un generador de Zp");
                System.out.println("ingrese el elemento y: ");
                y = new BigInteger(sc.nextLine());
                while (!elemY(y, Zp)) {
                    System.out.println("ingrese el elemento y valido: ");
                    y = new BigInteger(sc.nextLine());
                }
                System.out.println("el elemento " + y + " pertenece a Zp");
                m = (p.subtract(BigInteger.ONE));
                System.out.println("m: " + m);
                m1 = Math.sqrt(m.doubleValue());
                System.out.println("sqrt de m: " + m1);
                m1 = Math.ceil(m1);
                mi = m1.intValue();
                System.out.println("mi = "+ mi);
               
                //definicion de la hashtable. 
                Hashtable<Integer, BigInteger> table1 = new Hashtable<Integer, BigInteger>();
                for (int j = 0; j < mi; j++) {
                    row1 = g.pow(j).mod(p); 
                    table1.put(j, row1);
                }
                for (int i = 0; i < mi; i++) {
                    System.out.println(table1.get(i));
                }
                
            } else {
                System.out.println("el valor de g: " + g + " resulto " + Generador + "entonces No es un generador de Zp");
            }
        } else {
            System.out.println("El numero " + p + " ingresado no es primo.");
        }
    }

    /**
     * *
     * mostrar el grupo finito ciclico definido por p
     *
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
            Z = Z + Zp.get(i) + ",";
        }
        Z = Z.substring(0, Z.length() - 1);
        Z = Z + "}";
        System.out.println(Z);
        return Zp;
    }

    /**
     * *
     * valida si g es un generador del grupo finito ciclico.
     *
     * @param g
     * @param p
     * @return
     */
    private static boolean gesgenerador(BigInteger g, BigInteger p) {
        int n = p.intValue();
        int ng = g.intValue();
        int ip, ig;
        boolean Generador = false;
        ArrayList<Integer> divp = new ArrayList<Integer>();
        ArrayList<Integer> divg = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
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
                } else {
                    Generador = true;
                }
            }
        }

        return Generador;
    }

    /**
     * *
     * valida si el elemento y ingresado se encuentra dentro del grupo finito
     * ciclico.
     *
     * @param y
     * @param Zp
     * @return
     */
    private static boolean elemY(BigInteger y, ArrayList<Integer> Zp) {
        boolean state = false;
        for (int i = 0; i < Zp.size(); i++) {
            if (y.intValue() == (Zp.get(i))) {
                state = true;
            }
        }
        return state;
    }
}
