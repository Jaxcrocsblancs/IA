import java.util.ArrayList;

public class Noeud {

	private int joueur; // joueur qui a joué pour arriver ici 
	private int coup;   // coup joué par ce joueur pour arriver ici
	private Etat  etat; // etat du jeu
	
	private int parent; 
	private ArrayList<Noeud> enfants; // liste d'enfants : chaque enfant correspond à un coup possible
	private int nb_enfants;	// nb d'enfants présents dans la liste
	
	private double b;
	private double nb_victoires;
	private double nbPassage;
	
	public Noeud(Etat e, int i){//racine
		this.etat = e;
		this.enfants = new ArrayList<Noeud>();
		this.parent = -1;
		this.coup = -1;
		this.joueur =  1;
		this.nb_enfants = 0; 
		
		// POUR MCTS:
		this.b = 0;
		this.nb_victoires = 0;
		this.nbPassage = 0;	
	}
	
	public Noeud(Etat e, int parent,int joueur,int coup){
		this.etat = new Etat(e);
		this.parent = parent;
		this.coup = coup;
		this.enfants = new ArrayList<Noeud>();
		this.joueur = Start.autreJoueur(joueur);
		this.nb_enfants = 0; 
		
		// POUR MCTS:
		this.b=0;
		this.nb_victoires = 0;
		this.nbPassage = 0;	
	}
		
	public int getJoueur() {
		return this.joueur;
	}
	
	

	public Noeud getEnfant(int c) {
		return this.enfants.get(c);
	}

	public double getB() {
		return b;
	}

	public Etat getEtat() {
		return this.etat;
	}

	public int getNb_enfants() {
		return this.nb_enfants;
	}

	public int getCoup() {
		return this.coup;
	}
	
	public boolean enfantTousDeveloppé(){
		if(this.enfants.size()==0){
			return false;
		}
		for(Noeud e: this.enfants){
			if (e.getNbPassage()==0){
				return false;
			}
		}
		return true;
	}

	public Noeud getParent() {
		return this.parent;
	}

	public void calcB() {
		double c = Math.sqrt(2);
		double rep = c*Math.sqrt(Math.log(this.parent.getNbPassage())/this.nbPassage);
		double u = this.nb_victoires/this.nbPassage;
		if(this.getParent().getJoueur() == -1){
			this.b = rep +u ;
		}
		else{
			this.b = rep -u;
		}
	}
	private double getNbPassage() {
		return this.nbPassage;
	}

	public void setVictoire() {
		this.nb_victoires++;
	}

	public void setPassage() {
		this.nbPassage ++;
	}

	public double getVictoire() {
		return this.nb_victoires;
	}

	public double getPassage() {
		return this.nbPassage;
	}
}
