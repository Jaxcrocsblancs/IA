import java.util.ArrayList;
import java.util.Random;


public class IA {

	public IA(){
		
	}
	
	public int UTC(Etat e){
		long tempsDébut = System.currentTimeMillis();
		int durée = 3000;
		int i = 0;
		ArrayList<Noeud> listNoeud = new ArrayList<Noeud>();
		Noeud racine = new Noeud(new Etat(e), 0);
		listNoeud.add(racine);
		for(int c: coupsPossible(e)){
			racine.ajouterEnfant(c);
		}
		Noeud current, currentbis;
		int indexRand;
		while(System.currentTimeMillis()< (tempsDébut+durée)){//boucle tant qu'il reste du temps
			current = racine;//retour à la racine
			while(current.enfantTousDeveloppé()){//recherche d'un noeud pour continuer
				if(current.getNb_enfants() == 0){
					for(int c: coupsPossible(current.getEtat())){
						current.ajouterEnfant(c);
					}
				}
				current = MaxB(current);// selection du noeud suivant
			}
			while(current.getEtat().testFin() == FinDePartie.NON){//Marche aléatoire
				ArrayList<Integer>lC = this.coupsPossible(current.getEtat());
				indexRand = (int) (Math.random() * lC.size());
				current = new Noeud(current.getEtat().coups(lC.get(indexRand)),current, lC.get(indexRand));
			}
			boolean vic=false;
			if(current.getEtat().testFin() == FinDePartie.ORDI_GAGNE){
				vic = true;
			}
			//System.out.println(racine + " "+current);
			
			currentbis = current;
			while(currentbis != racine){
				if(vic){
					currentbis.setVictoire();
				}
				currentbis.setPassage();
				currentbis= currentbis.getParent();
			}

			currentbis.setPassage();currentbis.setVictoire();
			while(current != racine){//remonté de b
				current.calcB();
				current = current.getParent();
			}
			
			i++;
			System.out.println("NB itération: "+i);
		}
		for(int i1 =0; i1<racine.getNb_enfants();i1++){
			System.out.println(i1+" "+racine.getEnfant(i1).getB()+" "+racine.getEnfant(i1).getVictoire()+" "+racine.getEnfant(i1).getPassage());
		}
		return Max(racine).getCoup();
	}		

	private Noeud Max(Noeud n) {
		int max = -1;
		for(int i=0; i<n.getNb_enfants(); i++){
			if(max==-1){
				max=i;
			}
			if(n.getEnfant(max).getB()<n.getEnfant(i).getB()){
				max = i;
			}
		}
		return n.getEnfant(max);
	}

	public Noeud MaxB(Noeud n){
		Noeud max = null;
		ArrayList<Noeud> lN = new ArrayList<Noeud>();
		for(int i=0; i<n.getNb_enfants(); i++){//Recuperation de noeud non développer
			if(n.getEnfant(i).getB() == 0){
				lN.add(n.getEnfant(i));
			}			
		}
		if(lN.size()>0){
			int indexRand = (int) (Math.random() *lN.size() );
			return lN.get(indexRand);//retour d'un noeud non développer tirer aléatoirement
		}
		return Max(n);
	}
	
	
	public ArrayList<Integer> coupsPossible(Etat e){
		ArrayList<Integer> rep = new ArrayList<Integer>();
		for (int i =0; i <e.getPlateau()[0].length; i++){
			if(e.getPlateau()[0][i] == 0){
				rep.add(i);
			}
		}
		
		return rep;
	}

}
