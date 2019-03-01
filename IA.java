import java.util.ArrayList;
import java.util.Random;


public class IA {

	public IA(){
		
	}
	
	public int UTC(Etat e){
		long tempsDebut = System.currentTimeMillis();
		int duree = 1000*Start.TEMPS;
		int i = 0;
		ArrayList<Noeud> listNoeud = new ArrayList<Noeud>();
		Noeud racine = new Noeud(new Etat(e), 0);
		listNoeud.add(racine);
		for(int c: coupsPossible(e)){
			Noeud enfant = new Noeud(listNoeud.get(0).getEtat().coups(c), 0, listNoeud.get(0).getJoueur(),c);
			listNoeud.add(enfant);
			racine.ajouterEnfant(c,listNoeud.size()-1);
		}
		int current, currentbis;
		int indexRand;
		while(System.currentTimeMillis()< (tempsDebut+duree)){//boucle tant qu'il reste du temps
			current = 0;//retour à la racine
			while(listNoeud.get(current).enfantTousDeveloppe(listNoeud)){//recherche d'un noeud pour continuer

				current = MaxB(current, listNoeud);// selection du noeud suivant
			}
			while(listNoeud.get(current).getEtat().testFin() == FinDePartie.NON){//Marche aléatoire
				boolean pasFin = true;

				if(listNoeud.get(current).getNb_enfants() == 0){
					for(int c: coupsPossible(listNoeud.get(current).getEtat())){
						Noeud enfant = new Noeud(listNoeud.get(current).getEtat().coups(c), current, listNoeud.get(current).getJoueur(),c);
						listNoeud.add(enfant);
						listNoeud.get(current).ajouterEnfant(c,listNoeud.size()-1);

						if(enfant.getEtat().testFin() != FinDePartie.NON){//test si fin de partie possible plutot que aléatoire
							current =listNoeud.size()-1;
							pasFin = false;
						}
					}
				}
				if(pasFin){
					indexRand = (int) (Math.random() * listNoeud.get(current).getNb_enfants());
					current = listNoeud.get(current).getEnfant(indexRand);
				}
			}
			boolean vic=false;
			if(listNoeud.get(current).getEtat().testFin() == FinDePartie.ORDI_GAGNE){
				vic = true;
			}
			currentbis = current;
			while(currentbis != 0){
				if(vic){
					listNoeud.get(currentbis).setVictoire();
				}
				listNoeud.get(currentbis).setPassage();
				currentbis= listNoeud.get(currentbis).getParent();
			}

			listNoeud.get(currentbis).setPassage();
			listNoeud.get(currentbis).setVictoire();
			while(current != 0){//remonté de b
				listNoeud.get(current).calcB(listNoeud);
				current= listNoeud.get(current).getParent();
			}
			
			i++;
		}
//		System.out.println("NB itération: "+i+" Probabilité de victoire: "+listNoeud.get(MaxMax(0,listNoeud)).probaVic());
		System.out.println("Max: "+listNoeud.get(MaxMax(0,listNoeud)).getCoup()+" "+listNoeud.get(MaxMax(0,listNoeud)).probaVic()+" Robuste: "+listNoeud.get(MaxRobuste(0,listNoeud)).getCoup()+" "+listNoeud.get(MaxRobuste(0,listNoeud)).probaVic());
		
		while(listNoeud.get(MaxMax(0,listNoeud)).getCoup() != listNoeud.get(MaxRobuste(0,listNoeud)).getCoup()){//tant que max et robuste sont pas identique
			current = 0;//retour à la racine
			while(listNoeud.get(current).enfantTousDeveloppe(listNoeud)){//recherche d'un noeud pour continuer

				current = MaxB(current, listNoeud);// selection du noeud suivant
			}
			while(listNoeud.get(current).getEtat().testFin() == FinDePartie.NON){//Marche aléatoire
				boolean pasFin = true;
				if(listNoeud.get(current).getNb_enfants() == 0){
					for(int c: coupsPossible(listNoeud.get(current).getEtat())){
						Noeud enfant = new Noeud(listNoeud.get(current).getEtat().coups(c), current, listNoeud.get(current).getJoueur(),c);
						listNoeud.add(enfant);
						listNoeud.get(current).ajouterEnfant(c,listNoeud.size()-1);

						if(enfant.getEtat().testFin() == FinDePartie.NON){//test si fin de partie possible plutot que aléatoire
							current =listNoeud.size()-1;
							pasFin = false;
						}

					}
				}
				if(pasFin){
					indexRand = (int) (Math.random() * listNoeud.get(current).getNb_enfants());
					current = listNoeud.get(current).getEnfant(indexRand);
				}
			}
			boolean vic=false;
			if(listNoeud.get(current).getEtat().testFin() == FinDePartie.ORDI_GAGNE){
				vic = true;
			}

			currentbis = current;
			while(currentbis != 0){
				if(vic){
					listNoeud.get(currentbis).setVictoire();
				}
				listNoeud.get(currentbis).setPassage();
				currentbis= listNoeud.get(currentbis).getParent();
			}

			listNoeud.get(currentbis).setPassage();
			listNoeud.get(currentbis).setVictoire();
			while(current != 0){//remonté de b
				listNoeud.get(current).calcB(listNoeud);
				current= listNoeud.get(current).getParent();
			}
			
			i++;
			
		}

		
		
		
//		System.out.println("Max: "+listNoeud.get(MaxMax(0,listNoeud)).getCoup()+" "+listNoeud.get(MaxMax(0,listNoeud)).probaVic()+" Robuste: "+listNoeud.get(MaxRobuste(0,listNoeud)).getCoup()+" "+listNoeud.get(MaxRobuste(0,listNoeud)).probaVic());
		System.out.println("NB itération: "+i+" Probabilité de victoire: "+listNoeud.get(MaxMax(0,listNoeud)).probaVic());
		return listNoeud.get(MaxMax(0,listNoeud)).getCoup();
	}		

	private int MaxRobuste(int n, ArrayList<Noeud> lNoeud){
		int max = -1;
		for(int i=0; i<lNoeud.get(n).getNb_enfants(); i++){
			if(max==-1){
				max=lNoeud.get(n).getEnfant(i);
			}
			if(lNoeud.get(max).getPassage()<lNoeud.get(lNoeud.get(n).getEnfant(i)).getPassage()){
				max = lNoeud.get(n).getEnfant(i);
			}
		}
		return max;
	}
	
	private int MaxMax(int n, ArrayList<Noeud> lNoeud) {
		int max = -1;
		for(int i=0; i<lNoeud.get(n).getNb_enfants(); i++){
			if(max==-1){
				max=lNoeud.get(n).getEnfant(i);
			}

			if(lNoeud.get(max).probaVic()<lNoeud.get(lNoeud.get(n).getEnfant(i)).probaVic()){

				max = lNoeud.get(n).getEnfant(i);
			}
		}
		return max;
	}

	public int MaxB(int n, ArrayList<Noeud> lNoeud){
		int max = -1;
		ArrayList<Integer> lN = new ArrayList<Integer>();
		for(int i=0; i<lNoeud.get(n).getNb_enfants(); i++){//Recuperation de noeud non développer
			if(lNoeud.get(lNoeud.get(n).getEnfant(i)).getB() == 0){
				lN.add(lNoeud.get(n).getEnfant(i));
			}			
		}
		if(lN.size()>0){
			int indexRand = (int) (Math.random() *lN.size() );
			return lN.get(indexRand);//retour d'un noeud non développer tirer aléatoirement
		}

		for(int i=0; i<lNoeud.get(n).getNb_enfants(); i++){
			if(max==-1){
				max=lNoeud.get(n).getEnfant(i);
			}
			if(lNoeud.get(max).getB()<lNoeud.get(lNoeud.get(n).getEnfant(i)).getB()){
				max = lNoeud.get(n).getEnfant(i);
			}
		}
		return max;
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

