
package simulador_avl;

public class Main {

	private static Construcao_grafica_arvore grafica;
	private static Arvore_AVL<Integer> model;

	public static void main(String[] args) {

		model = new Arvore_AVL<>();

		grafica = new Construcao_grafica_arvore(model);

		new Interface(model, grafica);

	}

}
