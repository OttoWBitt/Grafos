/*
Algoritimos em Grafos 2/2017
Nome:Otto Wilke Diniz Mariani Bittencourt
Matricula:504654
*/
import java.io.*;
import java.*;
import java.nio.charset.*;
import java.util.Formatter;
import java.util.Scanner;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Grafos {
	private boolean tipo;
	private int numVertices;
	private int[][] matriz;
	//private Grafos complementar = new Grafos(tipo, numVertices);
	private int [][] complementar = new int [6][6];



	public Grafos(boolean otipo, int onumVertices) {
		tipo = otipo;
		numVertices = onumVertices;
		matriz = new int[numVertices][numVertices];
		enchercomzero();
	}

	public Grafos(boolean otipo, int onumVertices, String[] asRelacoes) {
		tipo = otipo;
		numVertices = onumVertices;
		matriz = new int[numVertices][numVertices];

		enchercomzero();
		String[] spli;
		int origem, destino, peso;
		for (int i = 0; asRelacoes[i] != null; i++) {
			spli = asRelacoes[i].split(",");
			origem = Integer.parseInt(spli[0]);
			destino = Integer.parseInt(spli[1]);
			peso = Integer.parseInt(spli[2]);
			encherMatriz(origem, destino, peso);
		}
	}

	public void enchercomzero() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = 0;
			}
		}
	}

	public void encherMatriz(int origem, int destino, int peso) {
		matriz[origem][destino] = peso;
		if (tipo == false) {
			matriz[destino][origem] = peso;
		}
	}

	public int getGrauVertice(int vertice) {
		int resp = 0;

		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i][vertice] != 0) {
				resp++;
			}
		}
		if (tipo == true) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[vertice][j] != 0) {
					resp++;
				}
			}
		}
		return resp;
	}

	public boolean arrestaIsPresente(int origem,int destino) {
		boolean resp = false;

		if (matriz[origem][destino] != 0) {
			resp = true;
		}
		return resp;
	}

	public int getNumArestas() {
		int resp = 0;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] != 0) {
					resp++;
				}
			}
		}
		return resp/2;
	}

	public boolean grafoIsComplete() {
		boolean resp = true;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == 0) {
					resp = false;
					break;
				}
			}
		}
		return resp;
	}

	public void imprimir (){
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.println("");
		}
	}

	
	public void complementar() {
		
		for (int i = 0; i < complementar.length; i++) {
			for (int j = 0; j < complementar.length; j++) {
				if (matriz[i][j] == 0 && i!=j) {
					if (!tipo) {
						complementar[i][j] = 1;
					}
				}
			}
		}
	}

	public void printComplementar(){
		for (int i = 0;i<complementar.length;i++) {
			for (int j = 0;j<complementar.length;j++) {
				if (complementar[i][j] != 0 && i < j) {
					System.out.println(i + "," + j + "," + complementar[i][j]);
				}
			}
		}
	}
	

}

class GrafoMain {

	static BufferedReader read = null;

	public static Grafos montarGrafo() throws Exception {


		read = new BufferedReader(new InputStreamReader(System.in));
		Grafos resp = null;
		boolean tipo = false; //se 0(false) == grafo. se 1(true) digrafo
		int numVertices;
		String[] relacoes;
		int i = 0;

		try {
			int type = Integer.parseInt(read.readLine());
			if (type == 1) {
				tipo = true;
			}
			numVertices = Integer.parseInt(read.readLine());

			relacoes = new String[(numVertices * numVertices)];

			String str = read.readLine();


			do {
				relacoes[i] = str;
				i++;
				str = read.readLine();
			} while (!str.equals("FIM"));

			resp = new Grafos(tipo, numVertices, relacoes);
		}
		catch (Exception e){
			System.out.println("ERRO!");
		}

		return resp;
	}

	public static void main(String[] args)throws Exception {
		int verticeAverificar;
		String arestaAverificar;
		read = new BufferedReader(new InputStreamReader(System.in));

		Grafos grafo =  montarGrafo();

		//grafo.imprimir();
				
		
		verticeAverificar = Integer.parseInt(read.readLine());
		arestaAverificar = read.readLine();
		System.out.println(grafo.getGrauVertice(verticeAverificar));
		
		String[] aux = arestaAverificar.split(",");
		int origem = Integer.parseInt(aux[0]);
		int destino = Integer.parseInt(aux[1]);

		if (grafo.arrestaIsPresente(origem,destino) == true) {
			System.out.println("SIM");
		}
		else if (grafo.arrestaIsPresente(origem,destino) == false) {
			System.out.println("NAO");
		}

		System.out.println(grafo.getNumArestas());
		if(grafo.grafoIsComplete() == true){
			System.out.println("SIM");
		}
		else if (grafo.grafoIsComplete() == false) {
			System.out.println("NAO");
		}
		grafo.complementar();
		grafo.printComplementar();
	}
}