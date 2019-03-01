import javax.swing.JFrame;


public class Start extends JFrame{
	public static int LARGEUR_MAX=9;	// nb max de fils pour un noeud (= nb max de coups possibles)
	public static int TEMPS=3;		// temps de calcul pour un coup avec MCTS (en secondes)
	public static int LARGEUR = 7;
	public static int HAUTEUR = 6;
	
	//Paramètres du jeu
	
	public Start(){
		JFrame f = new JFrame("Puissance 4");
		f.setSize(700,700);
		Model m = new Model();
		Fenetre fenetre = new Fenetre(m);
		f.getContentPane().add(fenetre);
		f.setVisible(true);
	}
	//joueur 1 : humain
	//joueur -1 : ordinateur
	public static int autreJoueur(int i){
		if (i == 1){
			return -1;
		}
		else if(i==-1){
			return 1;
		}
		return 0;
	}
	
	public static void main(String[] arg){
		Start s = new Start();
	}	
}


