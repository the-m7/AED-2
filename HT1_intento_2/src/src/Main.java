package src;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	/**
	 * Clase Main
	 * @param args
	 */
	public static void main(String[] args) {
		IRadio miRad = new Radio1();
		Scanner scan = new Scanner(System.in);
		
		boolean Encendido = false;
		boolean esAM = true; // True para AM, False para FM
		
		int[] Lista_AM = new int[12];
		int Estacion_AM = 530;
		
		double[] Lista_FM = new double[12];
		double Estacion_FM = 87.9;
		
		boolean bmenu = true;
		while(bmenu){
			if(Encendido) {
				System.out.println("La Radio esta encendida");
				if(esAM) {
					System.out.println("Modo: AM\nEstacion: "+Estacion_AM);
					
				}else {
					System.out.println("Modo: FM\nEstacion: "+ String.format("%.1f", Estacion_FM));				
				}

				System.out.println("Menu");
				System.out.println(" 1. Apagar la Radio");
				System.out.println(" 2. Avanzar Estacion");
				System.out.println(" 3. Guardar en boton");
				System.out.println(" 4. Cargar un boton");
				System.out.println(" 5. Cambiar de Modo");
				System.out.println(" 6. Salir del programa");
	
				int sel=-1;
				try {sel = scan.nextInt();}
				catch(InputMismatchException e) {scan.next();}
				
				switch(sel) {
				case 1:
					System.out.println("Apagando...");
					Encendido = miRad.cambioBinario(Encendido);
					break;
				case 5:
					//cambiar de modo
					esAM = miRad.cambioBinario(esAM);
					break;
				case 6:
					System.out.println("Saliendo del Programa...");
					bmenu = false;
					break;
				default:
					if(esAM) {
						switch(sel) {
						case 2:
							//avanzar
							Estacion_AM = miRad.avanzarAM(Estacion_AM);
							break;
						case 3:
							//guardar
							System.out.println("Que boton desea guardar?");
							for(int i=0;i<12;i++) {
								System.out.print(" "+(i+1)+". "+Lista_AM[i]+" |");
							}
							int gsel=-1;
							try {gsel = scan.nextInt()-1;
							
//							System.out.println(Lista_AM[gsel]);
							if(0<=gsel&&gsel<12) {
								Lista_AM = miRad.guardarAM(Lista_AM.clone(), gsel, Estacion_AM);
								for(int i=0;i<12;i++) {
									System.out.print(" "+(i+1)+". "+Lista_AM[i]+" |");
								}
//								System.out.println(Lista_AM[gsel]);
							}}
							catch(InputMismatchException e) {scan.next();}
							break;
						case 4:
							//cargar
							System.out.println("Que boton desea cargar?");
							for(int i=0;i<12;i++) {
								System.out.print(" "+(i+1)+". "+Lista_AM[i]+" |");
							}
							int bsel=-1;
							try {bsel = scan.nextInt()-1;
							
							if(0<=bsel&&bsel<12) {
								if(Lista_AM[bsel]!=0) {
									Estacion_AM = Lista_AM[bsel];
								}
							}
							}
							catch(InputMismatchException e) {scan.next();}
							break;
						}
					}else {
						switch(sel) {
						case 2:
							//avanzar
							Estacion_FM=miRad.avanzarFM(Estacion_FM);
							break;
						case 3:
							//guardar
							System.out.println("Que boton desea guardar?");
							for(int i=0;i<12;i++) {
								System.out.print(" "+(i+1)+". "+String.format("%.1f", Lista_FM[i])+" |");
							}
							System.out.println();
							int gsel=-1;
							try {gsel = scan.nextInt()-1;
							
//							System.out.println(Lista_FM[gsel]);
							if(0<=gsel&&gsel<12) {
								Lista_FM = miRad.guardarFM(Lista_FM.clone(), gsel, Estacion_FM);
								for(int i=0;i<12;i++) {
									System.out.print(" "+(i+1)+". "+String.format("%.1f", Lista_FM[i])+" |");
								}
							}}
							catch(InputMismatchException e) {scan.next();}
							break;
						case 4:
							//cargar
							System.out.println("Que boton desea cargar?");
							for(int i=0;i<12;i++) {
								System.out.print(" "+(i+1)+". "+String.format("%.1f", Lista_FM[i])+" |");
							}
							int bsel=-1;
							try {bsel = scan.nextInt()-1;
							
							if(0<=bsel&&bsel<12) {
								if(Lista_FM[bsel]!=0) {
									Estacion_FM = Lista_FM[bsel];
								}
							}
							}
							catch(InputMismatchException e) {scan.next();}
							break;		
					}}				
				}
				

				
			}else {
				System.out.println("La Radio esta apagada");
				System.out.println("Menu");
				System.out.println(" 1. Encender la Radio");
				System.out.println(" 2. Salir del programa");
	
				int sel=-1;
				try {sel = scan.nextInt();}
				catch(InputMismatchException e) {scan.next();}
				
				switch(sel) {
				case 1:
					System.out.println("Encendiendo...");
					Encendido = miRad.cambioBinario(Encendido);
					break;
				case 2:
					System.out.println("Saliendo del Programa...");
					bmenu = false;
					break;
				}
			}
	}
	scan.close();	
	}
	
}
