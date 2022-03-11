package com.intercorp.microservicio.controller;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n,s=0,x;
        double p;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingresar cantidad de elementos:");
        n=teclado.nextInt();
   
        for(int i=1;i<=n;i++)
        { 
         System.out.println("Elemento ["+i+"]=");
         x=teclado.nextInt();
         s=s+x;                
        }   
        p=Double.valueOf(s/n);
        
         System.out.println("El Promedio es:" +p);  
		
		
		
		
		
		
	}

}
