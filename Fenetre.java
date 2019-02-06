import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class Fenetre extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[][] plateau;
	private JButton[] choix;
	private Model m;
	
	public Fenetre(final Model m){
		this.m = m;
		this.setLayout(new GridLayout(Start.HAUTEUR+1,Start.LARGEUR));
		plateau = new JLabel[Start.HAUTEUR][Start.LARGEUR];
		choix = new JButton[Start.LARGEUR];
		for(int j =0; j<this.choix.length; j++){
			choix[j]= new JButton();
			//choix[j].setText("j: "+j);
			choix[j].setBorder(BorderFactory.createLineBorder(Color.black));
			final int colo = j;
			choix[j].addActionListener(new ActionListener(){
				int colonne = colo;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					m.actionColonne(colonne);
				}
			});
			this.add(choix[j]);
		}
		
		for(int i =0; i<this.plateau.length; i++){
			for(int j =0; j<this.plateau[0].length; j++){
				plateau[i][j]= new JLabel();
				//plateau[i][j].setText("i: "+i+" j: "+j);
				plateau[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.add(plateau[i][j]);
			}
		}
		m.addObserver(this); 	
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		for(int i =0; i<this.plateau.length; i++){
			for(int j =0; j<this.plateau[0].length; j++){
				if(this.m.getEtat().getPlateau()[i][j]== 1){
					plateau[i][j].setOpaque(true);
					plateau[i][j].setBackground(Color.yellow);
				}
				else if(this.m.getEtat().getPlateau()[i][j]== -1){
					plateau[i][j].setOpaque(true);
					plateau[i][j].setBackground(Color.red);
				}
				
			}
		}
		for(int i = 0; i<this.choix.length;i++){
			if(this.m.getEtat().getPlateau()[0][i] != 0){
				this.choix[i].setEnabled(false);
			}
		}
		if(m.getEtat().testFin() != FinDePartie.NON){
			JOptionPane.showMessageDialog(null,m.getEtat().testFin());
		}
	}
	
	
	
	
	
}
