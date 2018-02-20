class Grafo {
	private boolean isDirecionado;
	private int nroVertices;
	private int[][] matrizAdjacencias;
	
	/**
	 * Método responsável pela criação de um grafo 4x4 sem relações
	 * @return Grafo Montado
	 */
	public Grafo() {
		isDirecionado = false;
		nroVertices = 4;
		matrizAdjacencias = new int[nroVertices][nroVertices];
		preencherMatrizComZeros();
	}

	/**
	 * Método responsável pela criação de um grafo somente com o
	 * número de vértices e se ele é direcionado ou não
	 * @param  p_isDirecionado Caso TRUE o grafo é direcionado
	 * @param  p_nroVertices   Número de vértices do grafo
	 * @return                 Grafo montado
	 */
	public Grafo(boolean p_isDirecionado, int p_nroVertices) {
		isDirecionado = p_isDirecionado;
		nroVertices = p_nroVertices;
		matrizAdjacencias = new int[nroVertices][nroVertices];
		preencherMatrizComZeros();
	}

	/**
	 * Método construtor destinado a criação do Grafo
	 * @param  p_isDirecionado    Caso TRUE o grafo é direcionado
	 * @param  p_nroVertices      Número de vértices do grafo
	 * @param  p_relacoesVertices Array de relação entre os vértices
	 * @return                    Grafo montado
	 */
	public Grafo(boolean p_isDirecionado, int p_nroVertices, String[] p_relacoesVertices) {
		isDirecionado = p_isDirecionado;
		nroVertices = p_nroVertices;
		matrizAdjacencias = new int[nroVertices][nroVertices];
		preencherMatriz(p_relacoesVertices);
	} 

	/**
	 * Informa se o grafo em questão é direcionado ou não
	 * @return Caso TRUE, o grafo é direcionado
	 */
	public boolean isDirecionado() {
		return isDirecionado;
	}

	/**
	 * Responsável por informar o número de vértices do grafo
	 * @return Número de vértices do grafo
	 */
	public int getNroVertices() {
		return nroVertices;
	}

	/**
	 * Responsável por retornar a matriz de adjacências do grafo
	 * @return Matriz de Adjacências
	 */
	public int[][] matrizAdjacencias() {
		return matrizAdjacencias;
	}

	/**
	 * Método responsável preencher a matriz de adjacências
	 * com todos as relações zeradas
	 */
	public void preencherMatrizComZeros() {
		for(int i = 0; i < matrizAdjacencias.length; i++) {
			for(int j = 0; j < matrizAdjacencias.length; j++) {
				matrizAdjacencias[i][j] = 0;
			}
		}
	}

	/**
	 * Método responsável por preencher a matriz de adjacências
	 * com os valores recebidos como parâmetro
	 * @param p_relacoesVertices Array de relação entre os vértices 
	 */
	public void preencherMatriz(String[] p_relacoesVertices) {
		preencherMatrizComZeros();
		String[] aux;
		int linha, coluna, peso;
		for(int i = 0; p_relacoesVertices[i] != null; i++) {
			aux = p_relacoesVertices[i].split(",");
			linha = Integer.parseInt(aux[0]) - 1;
			coluna = Integer.parseInt(aux[1]) - 1;
			peso = Integer.parseInt(aux[2]);

			matrizAdjacencias[linha][coluna] = peso;
			if(!isDirecionado) {
				matrizAdjacencias[coluna][linha] = peso;
			}
		}
	}

	/**
	 * Método responsável por imprimir a matriz de adjacências
	 * com guias(números) referente às linhas e colunas
	 */
	public void imprimirMatrizComGuias() {
		for(int i = 0; i < nroVertices; i++) {
			System.out.print("\t{" + (i+1) + "}");		
		}
		System.out.println();

		for(int i = 0; i < matrizAdjacencias.length; i++) {
			for(int j = 0; j < matrizAdjacencias[i].length; j++) {
				if(j == 0) {
					System.out.print("{" + (i+1) + "}");
				}
				System.out.print("\t " + matrizAdjacencias[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Método responsável por imprimir a matriz de adjacências sem
	 * as guias de referências
	 */
	public void imprimirMatriz() {
		for(int i = 0; i < matrizAdjacencias.length; i++) {
			for(int j = 0; j < matrizAdjacencias[i].length; j++) {
				System.out.print("\t " + matrizAdjacencias[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Retorna o grau do vértice recebido como parâmetro
	 * @param  p_vertice Vértice
	 * @return           Grau do vértice
	 */
	public int getGrauVertice(int p_vertice) {
		int grau = 0;
		int vertice = p_vertice - 1;

		for(int i = 0; i < matrizAdjacencias.length; i++) {
			if(matrizAdjacencias[i][vertice] != 0) {
				grau++;
			}
		}
		
		if(!isDirecionado) {
			for(int i = 0; i < matrizAdjacencias.length; i++) {
				if(matrizAdjacencias[vertice][i] != 0) {
					grau++;
				}
			}
		}

		return grau;
	}

	/**
	 * Verifica a existência de uma aresta entre dois vértices
	 * @param  p_verticeA Vértice Origem
	 * @param  p_verticeB Vértice Destino
	 * @return            Caso TRUE, aresta está presente
	 */
	public boolean isArestaPresente(int p_verticeA, int p_verticeB) {
		return (matrizAdjacencias[p_verticeA-1][p_verticeB-1] != 0 ? true : false);
	}

	/**
	 * Método responsável por retornar o número total de arestas
	 * do grafo
	 * @return Número total de arestas do grafo
	 */
	public int getNroTotalArestas() {
		int nroTotalArestas = 0;

		for(int i = 0; i < matrizAdjacencias.length; i++) {
			for(int j = 0; j < matrizAdjacencias[i].length; j++) {
				if(matrizAdjacencias[i][j] != 0) {
					nroTotalArestas++;
				}
			}
		}

		return nroTotalArestas;
	}

	/**
	 * Método responsável por avaliar se o grafo em questão é
	 * completo ou não
	 * @return Caso TRUE, o grafo é completo
	 */
	public boolean isCompleto() {
		boolean retorno = true;

		for(int i = 0; i < matrizAdjacencias.length; i++) {
			for(int j = 0; j < matrizAdjacencias[i].length; j++) {
				if(i != j) {
					if(matrizAdjacencias[i][j] == 0) {
						retorno = false;
						break;
					}
				}
			}
		}

		return retorno;
	}

	/**
	 * Método responsável por gerar o grafo complementar 
	 * do grafo em questão
	 * @return Grafo complementar
	 */
	public Grafo gerarGrafoComplementar() {
		Grafo grafoComplementar = new Grafo(isDirecionado, nroVertices);

		for(int i = 0; i < matrizAdjacencias.length; i++) {
			for(int j = 0; j < matrizAdjacencias[i].length; j++) {
				if(matrizAdjacencias[i][j] == 0) {
					if(i != j) {
						grafoComplementar.matrizAdjacencias[i][j] = 1;	
					}
				}
			}
		}

		return grafoComplementar;
	}
}