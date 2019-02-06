import java.util.Observable;

public class Model extends Observable {
	private Etat etatActuel;
	private IA ia;
	public Model(){
		etatActuel = new Etat(1);
		ia = new IA();
	}
	
	public void update(){
		setChanged();
		notifyObservers();
		clearChanged();	
	}

	public void actionColonne(int j) {
		//action joueur
		if(etatActuel.getJoueur() == 1){
			etatActuel = etatActuel.coups(j);
			
		}
		else{
			etatActuel = etatActuel.coups(j);
		}
		System.out.println(etatActuel.testFin());
		this.update();
		int coup = ia.UTC(etatActuel);
		etatActuel = etatActuel.coups(coup);
		this.update();
	}

	
	
	public Etat getEtat() {
		return this.etatActuel;
	}

	
}
/*	
testfin première version

int cp = 0;
for(int i= 0; i< e.plateau.length; i++){
	for(int j=0; j< e.plateau[i].length; j++){
		if(e.plateau[i][j]!=0){
			cp++;
			int joueur = e.plateau[i][j];
			int cpt = 1;
			//horizontal
			int k=1;//droite
			while(j+k<e.plateau[0].length && e.plateau[i][j+k] == joueur){
				cpt++;
				k++;
			}
			k=-1;//gauche
			while(j+k>0 && e.plateau[i][j+k] == joueur){
				cpt++;
				k--;
			}
			if(cpt>=4){
				if(joueur == 1){
					return FinDePartie.HUMAIN_GAGNE;
				}
				else if(joueur == -1){
					return FinDePartie.ORDI_GAGNE;
				}
			}
			//vertical
			cpt = 1;
			k=1;//droite
			while(i+k<e.plateau.length && e.plateau[i+k][j] == joueur){
				cpt++;
				k++;
			}
			k=-1;//gauche
			while(i+k>0 && e.plateau[i+k][j] == joueur){
				cpt++;
				k--;
			}
			if(cpt>=4){
				if(joueur == 1){
					return FinDePartie.HUMAIN_GAGNE;
				}
				else if(joueur == -1){
					return FinDePartie.ORDI_GAGNE;
				}
			}
			//diagonal hgtobd
			cpt = 1;
			k=1;//droite
			while(i+k<e.plateau.length && j+k<e.plateau[0].length && e.plateau[i+k][j+k] == joueur){
				cpt++;
				k++;
			}
			k=-1;//gauche
			while(i+k>0 && j+k>0 && e.plateau[i+k][j+k] == joueur){
				cpt++;
				k--;
			}
			if(cpt>=4){
				if(joueur == 1){
					return FinDePartie.HUMAIN_GAGNE;
				}
				else if(joueur == -1){
					return FinDePartie.ORDI_GAGNE;
				}
			}
			//diagonal hdtobg
			cpt = 1;
			k=1;//droite
			while(i-k>0 && j+k<e.plateau[0].length && e.plateau[i-k][j+k] == joueur){
				cpt++;
				k++;
			}
			k=-1;//gauche
			while(i-k<e.plateau.length && j+k>0 && e.plateau[i-k][j+k] == joueur){
				cpt++;
				k--;
			}
			if(cpt>=4){
				if(joueur == 1){
					return FinDePartie.HUMAIN_GAGNE;
				}
				else if(joueur == -1){
					return FinDePartie.ORDI_GAGNE;
				}
			}
			
		}
	}
}*/