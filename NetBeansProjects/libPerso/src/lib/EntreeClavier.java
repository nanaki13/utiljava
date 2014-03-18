package lib;

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class  EntreeClavier
{ 
	 public static void main (String arg [] )   
	{ 
		String chaine ;       	
		int entier;    
		double nombreDouble ;
		
		chaine = null;
		entier = 0;
		nombreDouble = 0;
		
		EntreeClavier.afficher( " Entrer une chaine de caract�res : ");
		chaine 	= EntreeClavier.lireString();
		EntreeClavier.afficher( " la cha�ne entree est : " + chaine );
		             	
		EntreeClavier.afficher( " Entrer un entier : ");
                try {
                    entier 	= EntreeClavier.lireInteger();
                } catch (ErreurSaisieNombre ex) {
                    EntreeClavier.afficher( ex.getMessage() );  
                }
		EntreeClavier.afficher( " l'entier entre est : " + entier );      
		
		EntreeClavier.afficher( " Entrer un double : ");
                try {
                    nombreDouble  	= EntreeClavier.lireDouble();
                } catch (ErreurSaisieNombre ex) {
                    EntreeClavier.afficher( ex.getMessage() );  
                }
		EntreeClavier.afficher( " le double entre est : " +nombreDouble );  
	}
	
	public static void afficher(Object o)
	{
		System.out.println(o);
	}
	public static String lireString()
	{
		String ret;
		Scanner sc = new Scanner(System.in);
		ret = sc.nextLine();
		return ret;
	}
	public static int lireInteger() throws ErreurSaisieNombre
	{
		int ret = 0;
		Scanner sc = new Scanner(System.in);
		try
		{
			ret = sc.nextInt();
		}
		catch(InputMismatchException e )
		{
			ErreurSaisieNombre ex = new ErreurSaisieNombre("la saisi de l'entier n'est pas numéric");
                        throw  ex;
			//ret = lireInteger();
		}
		return ret;
	}
	public static double lireDouble() throws ErreurSaisieNombre
	{
		double ret = 0;
		Scanner sc = new Scanner(System.in);
		try
		{
			ret = sc.nextDouble();
		}
		catch(InputMismatchException e )
		{
			ErreurSaisieNombre ex = new ErreurSaisieNombre("la saisi du double n'est pas numéric");
                        throw  ex;
			//ret = lireInteger();
		}
		return ret;
	}
	
	
}

