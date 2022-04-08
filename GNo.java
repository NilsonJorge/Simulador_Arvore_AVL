
package simulador_avl;


import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;

//@SuppressWarnings("serial")
public class GNo extends JComponent {

	public static Dimension DEFAULT_SIZE = new Dimension(20, 20);
	public int altura;
	public boolean isEmpty = true;
	public int localizacao_horizontal_arvore;

	private No<Integer> node;
	private GNo GNode_pai;
	private GNo esquerda;
	private GNo direita;
	private String formato = "Rectangle2D";
	

	public void setFormato(String shapeType) {
		this.formato = shapeType;
	}

	public GNo(GNo GNode_pai) {
		setPreferredSize(DEFAULT_SIZE);
		this.setGNode_pai(GNode_pai);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(isEmpty) return;
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3.f));

		RectangularShape rShape = getFormato();
		
		switch (getNode().getEstado()) {
			case NOVO : 
				g2.setPaint(new Color(0,0,150));//cor do ultimo nodo inserido
				break;
                        case ENCONTRADO : 
				g2.setPaint(new Color(150,0,0));//cor do ultimo nodo encontrado
				break;        
			case VELHO : 
				g2.setPaint(new Color(70,70,70));//cor dos nós antigos
				break;
			case ALTERADO : 
				g2.setPaint(new Color(0, 150, 0));//cor dos nós que sofreram rotação
				break;
			default : break;
		}
		
		g2.fill(rShape);
		g2.setPaint(new Color(255, 210, 0));//cor do caracter
		texto_do_retangulo(g2, rShape, String.valueOf(getNode().getDados()));
	}

	public Point getPonto_conexao_superior() {
		return new Point(getLocation().x + getWidth() / 2, getLocation().y);
	}

	public Point getPonto_conexao_inferior() {
		return new Point(getLocation().x + getWidth() / 2, getLocation().y
				+ getHeight() - 1);
	}
	
	public boolean isFilhoEsquerdo(GNo child) {
		if (child == getEsquerda()) return true;
		return false;
	}
	
	public int getLargura_arvore() {		
		return (int) Math.pow(2, altura-1);
	}

	public void setDados(No<Integer> node) {
		this.setNode(node);
		isEmpty = false;
		setVisible(true);
	}

	public GNo getDireita() {
		return direita;
	}

	public void setDireita(GNo right) {
		this.direita = right;
	}

	public GNo getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(GNo left) {
		this.esquerda = left;
	}

	public GNo getGNode_pai() {
		return GNode_pai;
	}

	public void setGNode_pai(GNo GNode_pai) {
		this.GNode_pai = GNode_pai;
	}

	public No<Integer> getNode() {
		return node;
	}

	public void setNode(No<Integer> node) {
		this.node = node;
	}

	private RectangularShape getFormato() {
		RectangularShape rShape = null;
		switch (formato) {
		case "Rectangle2D":
			rShape = new Rectangle2D.Double(0, 0, getWidth() - 1,
					getHeight() - 1);
			break;
		case "RoundRectangle2D":
			rShape = new RoundRectangle2D.Double(0, 0, getWidth() - 1,
					getHeight() - 1, 4, 4);
			break;
		default:
			rShape = new Rectangle2D.Double(0, 0, getWidth() - 1,
					getHeight() - 1);
			break;
		}

		return rShape;
	}
	
	private void texto_do_retangulo(Graphics2D g2,
			RectangularShape rShape, String text) {
		Font sansbold14 = new Font("SansSerif", Font.BOLD, 10);
		g2.setFont(sansbold14);

		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = sansbold14.getStringBounds(text, context);

		double x = rShape.getCenterX() - bounds.getWidth() / 2, y = rShape
				.getCenterY() - bounds.getHeight() / 2, ascent = -bounds.getY(), baseY = y
				+ ascent;

		g2.drawString(text, (int) x, (int) baseY);
	}
}