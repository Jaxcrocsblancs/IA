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
		if(etatActuel.testFin()== FinDePartie.NON){
			//action joueur
			if(etatActuel.getJoueur() == 1){
				etatActuel = etatActuel.coups(j);
			}
//			else{
//				etatActuel = etatActuel.coups(j);
//			}
			System.out.println(etatActuel.testFin());
			this.update();
			
			if(etatActuel.testFin()== FinDePartie.NON){
				int coup = ia.UTC(etatActuel);
				etatActuel = etatActuel.coups(coup);
			}
			this.update();
		}
	}

	
	
	public Etat getEtat() {
		return this.etatActuel;
	}	
}