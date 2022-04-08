
package simulador_avl;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Linha_conexao {
	
	public static List<Line2D> linha;
	
	public static List<Line2D> getLines(GNo GNode_raiz) {
		linha = new ArrayList<>();
		add_linha(GNode_raiz); 
		
		return linha;
	}
	
	private static void add_linha(GNo no_pai) {
		if(no_pai == null) return;
		GNo no_filho = no_pai.getEsquerda();
		if (no_filho != null && !no_filho.isEmpty) {
			Point p1 = no_pai.getPonto_conexao_inferior(); 
			Point p2 = no_filho.getPonto_conexao_superior();
			linha.add(new Line2D.Double(p1, p2));
			add_linha(no_filho);
		}
		no_filho = no_pai.getDireita();
		if (no_filho != null && !no_filho.isEmpty) {
			Point p1 = no_pai.getPonto_conexao_inferior(); 
			Point p2 = no_filho.getPonto_conexao_superior();
			linha.add(new Line2D.Double(p1, p2));
			add_linha(no_filho);
		}
	}

}
