
package simulador_avl;

public class Arvore_AVL<T extends Comparable<T>> {
	
	public No<T> raiz;

    //construtor
    public Arvore_AVL( )
    {
        raiz = null;
    }

    //insere na arvore. valores duplicados são ignorados
    //variavel x é o valor a ser inserido
    public void insere( T x )
    {
    	makeAllOld(raiz);
        raiz = Arvore_AVL.this.insere(x, raiz );
    }

	//encontra o menor item da arvore
    public T procura_Min( )
    {
        return dado_no(procura_min(raiz ) );
    }

    //encontra o maior item da arvore
    public T procura_Max( )
    {
        return dado_no(procura_max(raiz ) );
    }

    //encontra um item x na arvore
    public T procura( T x )
    {
            makeAllOld(raiz);
            nodo_encontrado(Arvore_AVL.this.procura(x, raiz ));
            
        return dado_no(Arvore_AVL.this.procura(x, raiz ) );
    }

    //deixa a arvore vazia
    public void Esvazia_arvore( )
    {
        raiz = null;
    }

    //verifica se a arvore esta vazia
    public boolean isEmpty( )
    {
        return raiz == null;
    }

    //imprime o conteudo da arvore em ordem
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Arvore vazia" );
        else
            imprime_arvore(raiz );
    }
    
    //remove valor x da arvore
    public void remove( T x )
    {
    	makeAllOld(raiz);
        raiz = remove(x, raiz);
    }
    
    //metodo interno para remover elemento da sub arvore
    private No<T> remove( T x, No<T> t) {
    	if(t == null) return null;
    	if( x.compareTo( t.getDados() ) < 0 )
            t.setEsquerda(remove( x, t.getEsquerda()));
        else if ( x.compareTo( t.getDados() ) > 0 )
            t.setDireita(remove( x, t.getDireita()));
        else { // x == t.getDado()
        	No<T> q = t.getEsquerda();
        	No<T> r = t.getDireita();
        	t = null;
        	if(r == null) {
        		return q;
        	}
        	No<T> min = procura_min(r);
        	min.setEstado(Estado.ALTERADO);
        	min.setDireita(remove_Min(r));
        	min.setEsquerda(q);
        	return balanceamento(min);
        }
    	return balanceamento(t);
    }

    
     // remove o menor elemento da sub arvore
    
    private No<T> remove_Min(No<T> p) {
		if(p.getEsquerda() == null) return p.getDireita();
		p.setEsquerda(remove_Min(p.getEsquerda()));
		return balanceamento(p);
	}

    
     // Muda o estados dos nodos para OLD
    private static void makeAllOld(No<?> node) {
		if(node == null) return;
		node.setEstado(Estado.VELHO);
		
		makeAllOld(node.getEsquerda());
		makeAllOld(node.getDireita());
	}
    private static void nodo_encontrado(No<?> node) {
		if(node == null) return;
		node.setEstado(Estado.ENCONTRADO);
	}
    
    //metodo interno para inserir elemento na sub arvore
    private No<T> insere( T x, No<T> t ) {
        if( t == null )
            return new No<T>( x );
        
        if( x.compareTo( t.getDados() ) < 0 )
            t.setEsquerda(Arvore_AVL.this.insere( x, t.getEsquerda()));
        else
            t.setDireita(Arvore_AVL.this.insere( x, t.getDireita()));
   
        return balanceamento(t);
    }
    
    //calcula o fator de balanceamento da subarvore
    private static int fator_balanceamento(No<?> node) {
    	return altura( node.getDireita() ) - altura( node.getEsquerda() );
    }
    
    //atualiza a altura do nodo de acordo com as alturas das sub arvores esquerda e direita do nodo
    private static void atualiza_altura(No<?> node) {
    	node.setAltura(altura_max( altura( node.getEsquerda() ), altura( node.getDireita() ) ) + 1);
    }
    
    //balanceia a sub arvore se necessario
    private No<T> balanceamento(No<T> node) {
    	atualiza_altura(node);
    	if(fator_balanceamento(node) == 2)
    	{
    		if(fator_balanceamento(node.getDireita()) < 0)
    			node.setDireita(rotacao_simples_esquerda(node.getDireita()));
	    	return rotacao_simples_direita(node);
    	}
    	if(fator_balanceamento(node) == -2)
    	{
	    	if(fator_balanceamento(node.getEsquerda()) > 0)
	    		node.setEsquerda(rotacao_simples_direita(node.getEsquerda()));	    	
    		return rotacao_simples_esquerda(node);
    	}	
    	return node;
    }

    // metodo interno para encontrar o menor item da sub arvore
    private No<T> procura_min( No<T> t )
    {
        if( t == null )
            return t;

        while( t.getEsquerda() != null )
            t = t.getEsquerda();
        return t;
    }

  
    // metodo interno para encontrar o maior item da sub arvore
    private No<T> procura_max( No<T> t )
    {
        if( t == null )
            return t;

        while( t.getDireita() != null )
            t = t.getDireita();
        return t;
    }

    
    
    // metodo interno para encontrar um item especifico da sub arvore
    private No<T> procura( T x, No<T> t )
    {
        while( t != null )
            if( x.compareTo( t.getDados() ) < 0 )
                t = t.getEsquerda();
            else if( x.compareTo( t.getDados() ) > 0 )
                t = t.getDireita();
            else
                return t;    // encontrado
       
        return null;   // nao encontrado
    }
    
  
    //metodo interno para pegar o campo de um nó
    private T dado_no( No<T> t )
    {
        return t == null ? null : t.getDados();
    }

    //metodo interno para imprimir uam sub arvore em ordem 
    private static void imprime_arvore( No<?> t )
    {
        if( t != null )
        {
            imprime_arvore( t.getEsquerda() );
            System.out.println( t );
            imprime_arvore( t.getDireita() );
        }
    }

   
    //retorna a altura do nó t ou 0 se for null
    private static int altura( No<?> t )
    {
        return t == null ? 0 : t.getAltura();
    }

    //retorna a altura maxima da esquerda e da direita
    private static int altura_max( int lhs, int rhs )
    {
        return lhs > rhs ? lhs : rhs;
    }

    
    //rotacao simples a esquerda
    //atualiza a altura e retorna a nova raiz
    private No<T> rotacao_simples_esquerda( No<T> k2 )
    {
        No<T> k1 = k2.getEsquerda();
        k2.setEsquerda(k1.getDireita());
        k1.setDireita(k2);
        atualiza_altura(k2);
        atualiza_altura(k1);
        Estado_alterado(k1);
        Estado_alterado(k2);
        return k1;
    }
    
    private static void Estado_alterado(No<?> node) {
    	if (node.getEstado() == Estado.NOVO) return;
    	node.setEstado(Estado.ALTERADO);
    }

    //rotacao simples a direita
    //atualiza a altura e retorna a nova raiz
    private No<T> rotacao_simples_direita( No<T> k1 )
    {
        No<T> k2 = k1.getDireita();
        k1.setDireita(k2.getEsquerda());
        k2.setEsquerda(k1);
        atualiza_altura(k1);
        atualiza_altura(k2);
        Estado_alterado(k1);
        Estado_alterado(k2);
        return k2;
    }
}
