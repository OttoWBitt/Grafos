/*
Algoritimos em Grafos 2/2017
Nome:Otto Wilke Diniz Mariani Bittencourt
Nome:Ana Letícia Camargos
Nome:Rafael Câmara Magalhaẽs
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

class Grafo {
	private int numVertices;
	private int [][] matriz;
	private int [] corDeCadaVertice;

	/**
	 * Metodo de construcao
	 * @param  onumVertices n de vertices
	 * @return
	 */
	public Grafo(int onumVertices) {
		numVertices = onumVertices;
		matriz = new int [numVertices][numVertices];
		corDeCadaVertice = new int[numVertices];
		enchercomzero();
	}


	/**
	 * Metodo que enche a matriz com zeros
	 */
	public void enchercomzero() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = 0;
			}
		}
	}

	/**
	 * Metodo que monta as arestas do grafo
	 * @param entrada Entrada das relacoes do grafo
	 */
	public void montarAresta ( String entrada ) {
		String [] vetorPosicao = entrada.split(",");
		int origem = Integer.parseInt(vetorPosicao[0]);
		int destino = Integer.parseInt(vetorPosicao[1]);
		matriz[origem][destino] = 1;
		matriz[destino][origem] = 1;
	}



	/**
	 * Metodo para imprimir a matriz
	 */
	public void imprimir () {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.println("");
		}
	}

	/**
	 * Metodo de coloraçao do algoritimo naive
	 */
	public void colorir () {
		int numCor = 1;
		corDeCadaVertice[0] = numCor;
		int k = 0;
		ArrayList<Integer> cores = new ArrayList<>();
		cores.add(numCor);

		for ( int i = 1; i < numVertices; i ++) {
			int aux = checkColour(cores, i);
			if ( aux == -1) {
				numCor ++;
				corDeCadaVertice[i] = numCor;
				cores.add(numCor);
			} else {
				k = aux;
				corDeCadaVertice[i] = k;
			}
		}
	}

	/**
	 * Metodo que retorna a cor que nao pertence a nenhum dos seus vertices adjacentes 
	 * @param  cores   ArrayList de cores usadas ate o momento
	 * @param  vertice Vertice a ser verificado
	 * @return         retorno do resultado
	 */
	public int checkColour (ArrayList<Integer> cores, int vertice) {
		int retorno = -1;
		ArrayList<Integer> adjacentes = retornaAdjacentes(vertice);
		for ( int i = 0; i < cores.size(); i ++) {
			if ( comparaCores(cores.get(i), adjacentes) == false) {
				retorno = cores.get(i);
			}
		}
		return retorno;
	}

	/**
	 * Metodo que faz as comparaçoes da cor com o vetor de vertices adjacente
	 * @param  cor        Cor a ser verificada
	 * @param  adjacentes Vetor de vertices adjacentes
	 * @return            retorno da resposta true or false
	 */
	public boolean comparaCores ( int cor, ArrayList<Integer> adjacentes) {
		boolean retorno = false;
		for ( int i = 0; i < adjacentes.size(); i ++) {
			if (cor == corDeCadaVertice[adjacentes.get(i)]) {
				retorno = true;
			} 
		}
		return retorno;
	}

	/**
	 * Metodo que retona quais sao os verticies adjacentes a um certo vertice
	 * @param  vertice vertice a ser usado de referencia
	 * @return         Retorno do array de adjacentes
	 */
	public ArrayList<Integer> retornaAdjacentes (int vertice) {
		ArrayList<Integer>  adjacentes = new ArrayList<Integer>();
		for ( int i = 0; i < matriz.length; i ++) {
			if (matriz[vertice][i] == 1) {
				adjacentes.add(i);
			}
		}
		return adjacentes;
	}

	/**
	 * Metodo que retorna o numero de cores do Grafo 		
	 * @return 		Retorno da resposta
	 */
	public int numeroDeCores () {
		int max = corDeCadaVertice[0];
		for ( int i = 1; i < corDeCadaVertice.length; i ++) {
			if ( corDeCadaVertice[i] > max) {
				max = corDeCadaVertice[i];
			}
		}
		return max;
	}

}

class TrabalhoPratico4 {

	static BufferedReader ler = null;
	public static void main(String[] args)throws Exception {
	
		ler = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> entrada = new ArrayList<String>();

		//Camara S2
		String leitura = ler.readLine();

		int tamanho = Integer.parseInt(leitura);
		Grafo grafo = new Grafo(tamanho);

		leitura = ler.readLine();

		// adicionando linhas de entrada ao arraylist
		while ( leitura != null && leitura != "FIM") {

			entrada.add(leitura);
			leitura = ler.readLine();
		}

		tamanho = entrada.contains("FIM") ? entrada.size() - 1 : entrada.size();

		// montando grafo
		for ( int i = 0; i < tamanho; i ++) {
			String aux = entrada.get(i);
			grafo.montarAresta(aux);
		}

		//grafo.imprimir();
		grafo.colorir();
		System.out.println("Numero de cores do grafo: " + grafo.numeroDeCores());

	}
}