
package simulador_avl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.*;

@SuppressWarnings("serial")
public class Interface extends JFrame {

	public static int TELA_LARGURA;
	public static int TELA_ALTURA;

	private Controles controles;
	private View visualizacao;

	public Interface(Arvore_AVL<Integer> model, Construcao_grafica_arvore gtc) {
		super("Simulador Arvore AVL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		salva_tamanho_tela();
		setHalfScreenFrameSize();
		setLocationByPlatform(true);
		setLayout(new GridBagLayout());

		visualizacao = new View(gtc);
		visualizacao.setBackground(new Color(220,220,220));

		controles = new Controles(model, visualizacao);
		controles.setBackground(Color.LIGHT_GRAY);
		
		add(visualizacao, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		add(controles, new GridBagConstraints(1, 0, 1, 1, 0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));

		setDefaultLookAndFeelDecorated(true);
		setVisible(true);
	}

	private static void salva_tamanho_tela() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		TELA_LARGURA = screenSize.width;
		TELA_ALTURA = screenSize.height;
	}

	private void setHalfScreenFrameSize() {
		setSize(TELA_LARGURA / 2, TELA_ALTURA / 2);
	}
}

@SuppressWarnings("serial")
class Controles extends JPanel {

	private Arvore_AVL<Integer> model;
	private View visualizacao;
	private JTextField Insere_dados_text;
	private JTextField deleta_dados_text;
        private JTextField busca_dados_text;
	private JButton botao_insere;
	private JButton botao_limpar;
	private JButton botao_deleta;
        private JButton botao_busca;
	private JCheckBox Aleatorio_Check;
	private ArrayList<Integer> inserted = new ArrayList<>();
	private Random rand = new Random();
	private AtomicInteger at = new AtomicInteger();

	public Controles(Arvore_AVL<Integer> m, View v) {
		
		model = m;
		visualizacao = v;
		
		setLayout(new GridBagLayout());
		
		Aleatorio_Check = new JCheckBox("Aleatorio");
                
                        
		Insere_dados_text = new JTextField();
		Insere_dados_text.setText(String.valueOf(at.get()));
		Insere_dados_text.setColumns(5);
		Insere_dados_text.setHorizontalAlignment(JTextField.CENTER);
                
                busca_dados_text = new JTextField();
		busca_dados_text.setText(String.valueOf(at.get()));
                busca_dados_text.setColumns(5);
		busca_dados_text.setHorizontalAlignment(JTextField.CENTER);
                
		deleta_dados_text = new JTextField();
		deleta_dados_text.setText("Vazia");
		deleta_dados_text.setEditable(false);
		deleta_dados_text.setColumns(5);
		deleta_dados_text.setHorizontalAlignment(JTextField.CENTER);
		
		Aleatorio_Check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Aleatorio_Check.isSelected())//insere um valor aleatorio no campo de inserção se o checkbox estiver ativado
					Insere_dados_text.setText(String.valueOf((int)(rand.nextDouble()*100)));
				else
					Insere_dados_text.setText(String.valueOf(at.get()));
				
			}
		});

		botao_insere = new JButton("Inserir");
		botao_insere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insere(Insere_dados_text.getText());
				visualizacao.Atualiza_Arvore();
			}
		});
                
                botao_busca = new JButton("Buscar");
                botao_busca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscar(busca_dados_text.getText());
				visualizacao.Atualiza_Arvore();
			}
		});
		
		botao_deleta = new JButton("Excluir");
		botao_deleta.setEnabled(false);
		botao_deleta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleta(deleta_dados_text.getText());
				visualizacao.Atualiza_Arvore();
			}
		});
		
		botao_limpar = new JButton("Limpar");
		botao_limpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				limpa();
				visualizacao.Atualiza_Arvore();
			}
		});

		add(Aleatorio_Check, new GridBagConstraints(0, 0, 1, 1, 1.0, 0, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(7,7,7,7), 0, 0));
		add(Insere_dados_text, new GridBagConstraints(0, 1, 1, 1, 1.0, 0, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(7,7,7,7), 0, 0));
		add(botao_insere, new GridBagConstraints(0, 2, 1, 1, 1.0, 0, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(7,7,7,7), 0, 0));
		add(deleta_dados_text, new GridBagConstraints(0, 3, 1, 1, 1.0, 0, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(7,7,7,7), 0, 0));
		add(botao_deleta, new GridBagConstraints(0, 4, 1, 1, 1.0, 0, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(7,7,7,7), 0, 0));
		add(botao_limpar, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(7,7,7,7), 0, 0));
                
                add(busca_dados_text, new GridBagConstraints(0, 6, 1, 1, 1.0, 0, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(7,7,7,7), 0, 0));
		add(botao_busca, new GridBagConstraints(0, 7, 1, 1, 1.0, 0, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(7,7,7,7), 0, 0));
		
	}

	private void limpa() {
		model.Esvazia_arvore();
		
		inserted.clear();
		at.set(0);
		Insere_dados_text.setText(String.valueOf(at.get()));
		
		deleta_dados_text.setText("Vazia");
		deleta_dados_text.setEditable(false);
		botao_deleta.setEnabled(false);
	}

	private void insere(String dados) {
		Integer in;
		try {
			in = Integer.valueOf(dados);
		}
		catch (Exception ex) {return;}
		
		model.insere(in);
		inserted.add(in);
		
		if(!deleta_dados_text.isEditable()) {
			deleta_dados_text.setEditable(true);
			deleta_dados_text.setText(Insere_dados_text.getText());
			botao_deleta.setEnabled(true);
		}
		
		if(Aleatorio_Check.isSelected())
			Insere_dados_text.setText(String.valueOf((int)(rand.nextDouble()*100)));
		else
			Insere_dados_text.setText(String.valueOf(at.addAndGet(1)));
	}
        
        private void buscar(String data) {
		Integer in;
		try {
			in = Integer.valueOf(data);
		}
		catch (Exception ex) {return;}
		if(!(inserted.contains(in))){
                    JOptionPane.showMessageDialog(null, "Dado não foi encontrado na arvore","AVISO",JOptionPane.ERROR_MESSAGE);
                }else{
                    model.procura(in);
                    JOptionPane.showMessageDialog(null, "Dado foi encontrado na arvore","AVISO",JOptionPane.DEFAULT_OPTION);
                }
                
	}
	
	private void deleta(String data) {
		Integer in;
		try {
			in = Integer.valueOf(data);
		}
		catch (Exception ex) {return;}
		if(!(inserted.contains(in))){
                    JOptionPane.showMessageDialog(null, "Dado não existe na arvore","AVISO",JOptionPane.ERROR_MESSAGE);
                }
		model.remove(in);
		inserted.remove(in);
		
		if (inserted.isEmpty()) {
			deleta_dados_text.setText("Vazia");
			deleta_dados_text.setEditable(false);
			botao_deleta.setEnabled(false);
		}
		else 
			deleta_dados_text.setText(String.valueOf(inserted.get((int)(rand.nextDouble()*(inserted.size()-1)))));
	}

}

@SuppressWarnings("serial")
class View extends JPanel {

	private Construcao_grafica_arvore gtc;

	public View(Construcao_grafica_arvore gtc) {
		this.gtc = gtc;
		setLayout(new GridBagLayout());
	}

	public void Atualiza_Arvore() {
		removeAll();
		gtc.constroi();
		Desenha_raiz();
		revalidate();
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(2.f));
		for(Line2D line : Linha_conexao.getLines(gtc.getGnodo_raiz())) {
			g2.draw(line);
		}
	}
	
	private void Desenha_raiz() {
		GNo raiz = gtc.getGnodo_raiz();
		if(raiz == null) return;
		int localizacao_vertical_arvore = 0;
		raiz.localizacao_horizontal_arvore = 0;
		int gridwidth = raiz.getLargura_arvore();
		add(raiz, new GridBagConstraints(raiz.localizacao_horizontal_arvore, localizacao_vertical_arvore, gridwidth, 1, 1.0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		proximo_nivel(raiz, 1); // recursivo
	}
	
	private void proximo_nivel(GNo Gnode_pai,int localizacao_vertical_arvore) {
		GNo filho_esquerdo = Gnode_pai.getEsquerda();
		if (filho_esquerdo != null) {
			int gridwidth = filho_esquerdo.getLargura_arvore();
			filho_esquerdo.localizacao_horizontal_arvore = Gnode_pai.localizacao_horizontal_arvore;
			add(filho_esquerdo, new GridBagConstraints(filho_esquerdo.localizacao_horizontal_arvore, localizacao_vertical_arvore, gridwidth, 1, 1.0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(40,0,0,0), 0, 0));
			proximo_nivel(filho_esquerdo, localizacao_vertical_arvore+1);
		}
		GNo filho_direito = Gnode_pai.getDireita();
		if (filho_direito != null) {
			int gridwidth = filho_direito.getLargura_arvore();
			filho_direito.localizacao_horizontal_arvore = Gnode_pai.localizacao_horizontal_arvore + gridwidth;
			add(filho_direito, new GridBagConstraints(filho_direito.localizacao_horizontal_arvore, localizacao_vertical_arvore, gridwidth, 1, 1.0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(40,0,0,0), 0, 0));
			proximo_nivel(filho_direito, localizacao_vertical_arvore+1);
		}
	}
	
}