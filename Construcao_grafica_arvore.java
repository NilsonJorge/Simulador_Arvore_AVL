package simulador_avl;



public class Construcao_grafica_arvore {

	private GNo gnodo_raiz;
	private Arvore_AVL<Integer> model;

	public Construcao_grafica_arvore(Arvore_AVL<Integer> modelo_arvore_avl) {
		model = modelo_arvore_avl;
	}
	
	public GNo getGnodo_raiz() {
		return gnodo_raiz;
	}

	public void constroi() {
		if (model.raiz == null) { gnodo_raiz = null; return; }
		inicia(model.raiz);
	}

	private void inicia(No<Integer> raiz) {
		gnodo_raiz = criaGnodo_raiz(raiz);
		constroi_niveis(gnodo_raiz, raiz.getAltura()); // recursively
	}
	
	private GNo criaGnodo_raiz(No<Integer> raiz) {		
		GNo rootGNode = new GNo(null);
		rootGNode.setGNode_pai(rootGNode);
		rootGNode.setDados(raiz);
		rootGNode.altura = raiz.getAltura();
		return rootGNode;
	}

	private void constroi_niveis(GNo GnodoPai, int altura) {
		if(--altura < 1) return;
		
		// para a esquerda
		GnodoPai.setEsquerda(new GNo(GnodoPai));
		GnodoPai.getEsquerda().altura = altura;
		if(GnodoPai.getNode() != null) {
			No<Integer> next = GnodoPai.getNode().getEsquerda();
			if (next != null)
				GnodoPai.getEsquerda().setDados(next);
		}
		constroi_niveis(GnodoPai.getEsquerda(), altura);
		
		//para a direita
		GnodoPai.setDireita(new GNo(GnodoPai));
		GnodoPai.getDireita().altura = altura;
		if(GnodoPai.getNode() != null) {
			No<Integer> next = GnodoPai.getNode().getDireita();
			if (next != null)
				GnodoPai.getDireita().setDados(next);
		}
		constroi_niveis(GnodoPai.getDireita(), altura);
	}
	
}
