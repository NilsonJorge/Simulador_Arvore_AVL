
package simulador_avl;

public class No<T extends Comparable<T>> implements Comparable<No<T>> {

	private T dados;
	private No<T> esquerda;
	private No<T> direita;
	private int altura = 1;
	private Estado estado;

	public No(T dados) {
		this(dados, null, null);
	}

	public No(T dados, No<T> left, No<T> right) {
		super();
		setEstado(Estado.NOVO);
		this.dados = dados;
		this.esquerda = left;
		this.direita = right;
	}

	public T getDados() {
		return dados;
	}

	public void setDados(T data) {
		this.dados = data;
	}

	public No<T> getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(No<T> left) {
		this.esquerda = left;
	}

	public No<T> getDireita() {
		return direita;
	}

	public void setDireita(No<T> node) {
		this.direita = node;
	}

	/**
	 * @return the height
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * @param height
	 *            
	 */
	public void setAltura(int height) {
		this.altura = height;
	}

	@Override
	public int compareTo(No<T> o) {
		return this.dados.compareTo(o.dados);
	}

	@Override
	public String toString() {
		return "Altura: " + altura + ", Dados: " + dados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
