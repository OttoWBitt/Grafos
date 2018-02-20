import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Index {

	/**
	 * Método responsável pelo carregamento de um grafo a partir
	 * de um arquivo externo.
	 * @return Grafo carregado e montado
	 * @throws IOException   Erro ao ler arquivo
	 */
	public static Grafo carregarGrafo() throws IOException {
		Grafo grafoRetorno;

		BufferedReader reader = null;
		boolean isDirecionado;
		int nroVertices;
		String[] relacoesVertices;

		try {
		    File file = new File("grafos/grafo-default.in");
		    reader = new BufferedReader(new FileReader(file));

		    isDirecionado = (reader.readLine().equals("1") ? true : false);
		    
		    nroVertices = Integer.parseInt(reader.readLine());
		    relacoesVertices = new String[(nroVertices*nroVertices)];

		    int i = 0;
		    for(String linha = reader.readLine(); linha != null; linha = reader.readLine()) {
		    	relacoesVertices[i] = linha;
		    	i++;
		    }

		    grafoRetorno = new Grafo(isDirecionado, nroVertices, relacoesVertices);
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("Erro ao ler arquivo.");
		    grafoRetorno = new Grafo();
		} finally {
		    reader.close();
		}

		return grafoRetorno;
	}

	/**
	 * Método responsável por chamar o método [grafo].isArestaPresente()
	 * e tratar o seu retorno.
	 * @param  grafo      Grafo a ser analisado
	 * @param  p_verticeA Vértice A
	 * @param  p_verticeB Vértice B
	 * @return            Mensagem de retorno
	 */
	public static String verificarExistenciaAresta(Grafo grafo, int p_verticeA, int p_verticeB) {
		String retorno = "";

		if(grafo.isArestaPresente(p_verticeA, p_verticeB)) {
			retorno = "A aresta entre os vértices " + p_verticeA + " e " + p_verticeB + " está presente";
		} else {
			retorno = "A aresta entre os vértices " + p_verticeA + " e " + p_verticeB + " NÃO está presente";
		}

		return retorno;
	}

	/**
	 * Método responsável por chamar o método [grafo].getGrauVertice()
	 * e tratar o seu retorno
	 * @param  grafo     Grafo a ser analisado
	 * @param  p_vertice Vértice a ser analisado
	 * @return           Mensagem de retorno
	 */
	public static String getGrauVertice(Grafo grafo, int p_vertice) {
		return ("\nGrau do vértice {" + p_vertice + "}: " + grafo.getGrauVertice(2));
	}

	/**
	 * Metódo responsável por executar o método [grafo].isCompleto()
	 * e tratar o seu retorno
	 * @param  grafo Grafo a ser analisado
	 * @return       Mensagem de retorno
	 */
	public static String verificarGrafoCompleto(Grafo grafo) {
		String retorno = "";

		if(grafo.isCompleto()) {
			retorno = "O grafo em questão é completo";
		} else {
			retorno = "O grafo em questão NÃO é completo";
		}

		return retorno;
	}

	public static void main(String[] args) throws IOException {
		Grafo grafo = carregarGrafo();
		grafo.imprimirMatrizComGuias();

		System.out.println("\n# # # # INFORMAÇÕES SOBRE O GRAFO CARREGADO # # # #");
		
		System.out.println(getGrauVertice(grafo, 2));
		System.out.println(verificarExistenciaAresta(grafo, 2, 1));
		System.out.println("Número total de arestas do grafo: " + grafo.getNroTotalArestas());
		System.out.println(verificarGrafoCompleto(grafo));
		System.out.println("\nO grafo complementar do grafo carregado é: ");
		grafo.gerarGrafoComplementar().imprimirMatrizComGuias();
		
		System.out.println("\n# # # # # # # # # # # # FIM # # # # # # # # # # # #");
	}
}