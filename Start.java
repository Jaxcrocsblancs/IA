import javax.swing.JFrame;


public class Start extends JFrame{
	public static int LARGEUR_MAX=9;	// nb max de fils pour un noeud (= nb max de coups possibles)
	public static int TEMPS=5;		// temps de calcul pour un coup avec MCTS (en secondes)
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
///*
//Canvas pour algorithmes de jeux à 2 joueurs
//
//joueur 1 : humain
//joueur -1 : ordinateur
//		
//*/
//
//
//
//
//
//
//
//
//
//
//
////Modifier l'état en jouant un coup 
////retourne 0 si le coup n'est pas possible
//int jouerCoup( Etat * etat, Coup * coup ) {
//
//// TODO: à compléter
//
///* par exemple : */
//if ( etat->plateau[coup->ligne][coup->colonne] != ' ' )
//	return 0;
//else {
//	etat->plateau[coup->ligne][coup->colonne] = etat->joueur ? 'O' : 'X';
//	
//	// à l'autre joueur de jouer
//	etat->joueur = AUTRE_JOUEUR(etat->joueur); 	
//
//	return 1;
//}	
//}
//
////Retourne une liste de coups possibles à partir d'un etat 
////(tableau de pointeurs de coups se terminant par NULL)
//Coup ** coups_possibles( Etat * etat ) {
//
//Coup ** coups = (Coup **) malloc((1+LARGEUR_MAX) * sizeof(Coup *) );
//
//int k = 0;
//
//// TODO: à compléter
//
///* par exemple */
//int i,j;
//for(i=0; i < 3; i++) {
//	for (j=0; j < 3; j++) {
//		if ( etat->plateau[i][j] == ' ' ) {
//			coups[k] = nouveauCoup(i,j); 
//			k++;
//		}
//	}
//}
///* fin de l'exemple */
//
//coups[k] = NULL;
//
//return coups;
//}
//
//
////Definition du type Noeud 
//typedef struct NoeudSt {
//	
//int joueur; // joueur qui a joué pour arriver ici
//Coup * coup;   // coup joué par ce joueur pour arriver ici
//
//Etat * etat; // etat du jeu
//		
//struct NoeudSt * parent; 
//struct NoeudSt * enfants[LARGEUR_MAX]; // liste d'enfants : chaque enfant correspond à un coup possible
//int nb_enfants;	// nb d'enfants présents dans la liste
//
//// POUR MCTS:
//int nb_victoires;
//int nb_simus;
//
//} Noeud;
//
//
////Créer un nouveau noeud en jouant un coup à partir d'un parent 
////utiliser nouveauNoeud(NULL, NULL) pour créer la racine
//Noeud * nouveauNoeud (Noeud * parent, Coup * coup ) {
//Noeud * noeud = (Noeud *)malloc(sizeof(Noeud));
//
//if ( parent != NULL && coup != NULL ) {
//	noeud->etat = copieEtat ( parent->etat );
//	jouerCoup ( noeud->etat, coup );
//	noeud->coup = coup;			
//	noeud->joueur = AUTRE_JOUEUR(parent->joueur);		
//}
//else {
//	noeud->etat = NULL;
//	noeud->coup = NULL;
//	noeud->joueur = 0; 
//}
//noeud->parent = parent; 
//noeud->nb_enfants = 0; 
//
//// POUR MCTS:
//noeud->nb_victoires = 0;
//noeud->nb_simus = 0;	
//
//
//return noeud; 	
//}
//
////Ajouter un enfant à un parent en jouant un coup
////retourne le pointeur sur l'enfant ajouté
//Noeud * ajouterEnfant(Noeud * parent, Coup * coup) {
//Noeud * enfant = nouveauNoeud (parent, coup ) ;
//parent->enfants[parent->nb_enfants] = enfant;
//parent->nb_enfants++;
//return enfant;
//}
//
//void freeNoeud ( Noeud * noeud) {
//if ( noeud->etat != NULL)
//	free (noeud->etat);
//	
//while ( noeud->nb_enfants > 0 ) {
//	freeNoeud(noeud->enfants[noeud->nb_enfants-1]);
//	noeud->nb_enfants --;
//}
//if ( noeud->coup != NULL)
//	free(noeud->coup); 
//
//free(noeud);
//}
//
//
//
//
//	
//
//
//
////Calcule et joue un coup de l'ordinateur avec MCTS-UCT
////en tempsmax secondes
//void ordijoue_mcts(Etat * etat, int tempsmax) {
//
//clock_t tic, toc;
//tic = clock();
//int temps;
//
//Coup ** coups;
//Coup * meilleur_coup ;
//
//// Créer l'arbre de recherche
//Noeud * racine = nouveauNoeud(NULL, NULL);	
//racine->etat = copieEtat(etat); 
//
//// créer les premiers noeuds:
//coups = coups_possibles(racine->etat); 
//int k = 0;
//Noeud * enfant;
//while ( coups[k] != NULL) {
//	enfant = ajouterEnfant(racine, coups[k]);
//	k++;
//}
//
//
//meilleur_coup = coups[ rand()%k ]; // choix aléatoire
//
///*  TODO :
//	- supprimer la sélection aléatoire du meilleur coup ci-dessus
//	- implémenter l'algorithme MCTS-UCT pour déterminer le meilleur coup ci-dessous
//
//int iter = 0;
//
//do {
//
//
//
//	// à compléter par l'algorithme MCTS-UCT... 
//
//
//
//
//	toc = clock(); 
//	temps = (int)( ((double) (toc - tic)) / CLOCKS_PER_SEC );
//	iter ++;
//} while ( temps < tempsmax );
//
///* fin de l'algorithme  */ 
//
//// Jouer le meilleur premier coup
//jouerCoup(etat, meilleur_coup );
//
//// Penser à libérer la mémoire :
//freeNoeud(racine);
//free (coups);
//}
//
//int main(void) {
//
//Coup * coup;
//FinDePartie fin;
//
//// initialisation
//Etat * etat = etat_initial(); 
//
//// Choisir qui commence : 
//printf("Qui commence (0 : humain, 1 : ordinateur) ? ");
//scanf("%d", &(etat->joueur) );
//
//// boucle de jeu
//do {
//	printf("\n");
//	afficheJeu(etat);
//	
//	if ( etat->joueur == 0 ) {
//		// tour de l'humain
//		
//		do {
//			coup = demanderCoup();
//		} while ( !jouerCoup(etat, coup) );
//								
//	}
//	else {
//		// tour de l'Ordinateur
//		
//		ordijoue_mcts( etat, TEMPS );
//		
//	}
//	
//	fin = testFin( etat );
//}	while ( fin == NON ) ;
//
//printf("\n");
//afficheJeu(etat);
//	
//if ( fin == ORDI_GAGNE )
//	printf( "** L'ordinateur a gagné **\n");
//else if ( fin == MATCHNUL )
//	printf(" Match nul !  \n");
//else
//	printf( "** BRAVO, l'ordinateur a perdu  **\n");
//return 0;
//}
