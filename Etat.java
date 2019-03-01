public class Etat {
	
	//Definition du type Etat (état/position du jeu)
	int[][] plateau;
	int joueur; 

	public Etat(int [][] plateau, int joueur){
		this.plateau = plateau;
		this.joueur = joueur;
		
	}
	
	public Etat(int joueur){
		this.plateau = this.initPlateau();
		this.joueur = joueur;
	}

	public Etat(Etat e){
		this.plateau = new int[e.getPlateau().length][e.getPlateau()[0].length];
		for(int i=0; i<this.plateau.length;i++){
			for(int j=0; j<this.plateau[i].length;j++){
				this.plateau[i][j] = e.getPlateau()[i][j];
			}
		}

		this.joueur = e.getJoueur();
	}
	
	public int[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(int[][] plateau) {
		this.plateau = plateau;
	}

	public int getJoueur() {
		return joueur;
	}

	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}
	
	private int[][] initPlateau() {
		int [][] rep = new int[Start.HAUTEUR][Start.LARGEUR];
		for(int i = 0; i <rep.length; i++){
			for(int j =0; j< rep[i].length; j++){
				rep[i][j] = 0;
			}
		}
		return rep;
	}

	public FinDePartie testFin(){
		int compteur;
		int joueur=0;
		//test horizontal
		for(int i= 0; i<this.plateau.length; i++){
			compteur =0;
			for(int j = 0; j<this.plateau[i].length; j++){
				if(this.plateau[i][j]==joueur){
					compteur++;
				}
				else{
					joueur = this.plateau[i][j];
					compteur = 1;
				}
				if(compteur >= 4){
					if(joueur == 1){
						return FinDePartie.HUMAIN_GAGNE;
					}
					else if(joueur == -1){
						return FinDePartie.ORDI_GAGNE;
					}
				}
			}
		}
		//test vertical
		for(int j= 0; j<this.plateau[0].length; j++){
			compteur =0;
			for(int i = 0; i<this.plateau.length; i++){
				if(this.plateau[i][j]==joueur){
					compteur++;
				}
				else{
					joueur = this.plateau[i][j];
					compteur = 1;
				}
				if(compteur >= 4){
					if(joueur == 1){
						return FinDePartie.HUMAIN_GAGNE;
					}
					else if(joueur == -1){
						return FinDePartie.ORDI_GAGNE;
					}
				}
			}
		}
		//test haut gauche bas droite
		int i=3,j=0;
		while(i!=0 || j!=3){
			if(i!=0){
				i--;
			}else{
				j++;
			}
			compteur = 0;
			for(int k=0; i+k<this.plateau.length && j+k<this.plateau[0].length; k++){
				if(this.plateau[i+k][j+k]==joueur){
					compteur++;
				}
				else{
					joueur = this.plateau[i+k][j+k];
					compteur = 1;
				}
				if(compteur >= 4){
					if(joueur == 1){
						return FinDePartie.HUMAIN_GAGNE;
					}
					else if(joueur == -1){
						return FinDePartie.ORDI_GAGNE;
					}
				}
			}
		}
		//test diagonal haut droit bas gauche
		i=3;
		j=6;
		while(i!=0 || j!=3){
			if(i!=0){
				i--;
			}else{
				j--;
			}
			compteur = 0;
			for(int k=0; i+k<this.plateau.length && j-k>=0; k++){
				if(this.plateau[i+k][j-k]==joueur){
					compteur++;
				}
				else{
					joueur = this.plateau[i+k][j-k];
					compteur = 1;
				}
				if(compteur >= 4){
					if(joueur == 1){
						return FinDePartie.HUMAIN_GAGNE;
					}
					else if(joueur == -1){
						return FinDePartie.ORDI_GAGNE;
					}
				}
			}
		}
		//test match nul
		int cp=0;
		for(i = 0; i<Start.LARGEUR; i++){
			if(this.plateau[0][i]!= 0){
				cp++;
			}
		}
		if(cp == this.plateau[0].length){// si toute les cases du haut sont occupé match nul
			return FinDePartie.MATCHNUL;
		}
		return FinDePartie.NON;
	}
	
	public String toString(){
		StringBuilder rep = new StringBuilder();
		rep.append("Tour du joueur: "+this.joueur+"\n");
		for(int i = 0; i <plateau.length; i++){
			rep.append("\n");
			for(int j =0; j< plateau[i].length; j++){
				if(plateau[i][j]==-1)
					rep.append(" "+2);
				else
					rep.append(" "+plateau[i][j]);
			}
		}
		return rep.toString();
	}

	public Etat coups(int coup) {
		Etat etatSuivant = null;
		if(this.getPlateau()[0][coup]==0){
			int i = 0;
			while (i<Start.HAUTEUR-1 && this.getPlateau()[i+1][coup]==0){
				i++;
			}
			int[][] p = new int[plateau.length][plateau[0].length];
			for(int k=0; k<this.plateau.length;k++){
				for(int j=0; j<this.plateau[k].length;j++){
					p[k][j] = this.getPlateau()[k][j];
				}
			}
			p[i][coup]= this.getJoueur();
			etatSuivant = new Etat(p, Start.autreJoueur(this.getJoueur()));
		}
		return etatSuivant;
	}

}