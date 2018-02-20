/*
Algoritimos em Grafos 2/2017
Nome:Otto Wilke Diniz Mariani Bittencourt
Matricula:504654
*/

/*
CORES:
0 - Branco
1 - Cinza
2 - Preto
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
	private int [][] matriz;
	private int [] cor;
	private int [] distancia;
	private int [] pai;
	Queue<Integer> fila;


	public Grafos(boolean otipo, int onumVertices) {
		tipo = otipo;
		numVertices = onumVertices;
		matriz = new int [numVertices][numVertices];
		enchercomzero();
	}

	public Grafos(boolean otipo, int onumVertices, String[] asRelacoes) {
		tipo = otipo;
		numVertices = onumVertices;
		matriz = new int [numVertices][numVertices];
		cor = new int[numVertices];
		distancia = new int[numVertices];
		pai = new int[numVertices];

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

		for ( int i = 0; i < numVertices; i ++ ) {
			cor[i] = 0;
			distancia[i] = 9999;
			pai[i] = -1;
		}
		cor[0] = 1;
		distancia[0] = 0;
		fila = new ArrayDeque<>();
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

	public void imprimir () {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.println("");
		}
	}

	public void buscaPorLargura ( ) {
		int u = 0;
		fila.add(0);
		while ( !fila.isEmpty() ) {
			u = fila.remove();
			for ( int v = 0; v < numVertices; v ++ ) {
				if ( matriz[u][v] != 0 && cor[v] == 0 ) {
					cor[v] = 1;
					distancia[v] = distancia[u] + 1;
					pai[v] = u;
					fila.add(v);
				}
			}
		}
		cor[u] = 2;
	}

	
	public void imprime ( ) {
		System.out.println("Imprimindo o vetor do pai: " );
		for ( int i = 0; i < pai.length; i ++ ) {
			System.out.print(pai[i] + "\t");
		}
		System.out.println();
		for ( int i = 0; i < pai.length; i ++ ) {
			System.out.print(i+ "\t");
		}
	}

	public void imprimirCaminho(){

		ArrayList<String> caminho = new ArrayList<>();

		for (int i = numVertices-1; pai[i] != -1; i = pai[i]) {
			caminho.add(pai[i]+" --> "+i);
		}
		Collections.reverse(caminho);

		for (String pass : caminho) {
			System.out.println(pass);
		}
	}
	

}

class Tp2 {

	static BufferedReader read = null;



	public static Grafos montarGrafo() throws Exception {


		read = new BufferedReader(new InputStreamReader(System.in));
		Grafos resp = null;
		boolean tipo = false; //se 0(false) == grafo. se 1(true) digrafo
		int numVertices;
		String[] relacoes;
		int i = 0;

		int type = Integer.parseInt(read.readLine());
		if (type == 1) {
			tipo = true;
		}
		numVertices = Integer.parseInt(read.readLine());

		relacoes = new String[(numVertices * numVertices)];

		String str = read.readLine();

		try {

			do {
				relacoes[i] = str;
				i++;
				str = read.readLine();
			} while (!str.equals("FIM"));

			resp = new Grafos(tipo, numVertices, relacoes);

		} catch (Exception e) {
			System.out.println("ERRO!");
		}

		return resp;
	}

	public static void main(String[] args)throws Exception {
		int verticeAverificar;
		String arestaAverificar;
		read = new BufferedReader(new InputStreamReader(System.in));

		Grafos grafo =  montarGrafo();
		
		grafo.buscaPorLargura();
		grafo.imprimirCaminho();
	}
}