package domini.classes_compartides;

import java.io.Serializable;

public class Tablero_comp implements Serializable {
       
		private static final long serialVersionUID = 1L;
        protected Casilla_comp[][] tauler;
        protected int mida;
       
        public Tablero_comp() {}
        /**
         * Crea un tauler de mida NxN
         */
        public Tablero_comp(int N)
        {
                tauler = new Casilla_comp[N][N];
                mida = N;
       
                int i,j;
                for (i = 0; i < N; ++i) {
                        for (j = 0; j < N; ++j) {
                                tauler[i][j] = new Casilla_comp(mida+1,false);
                        }
                }
        }
       
        /**
         * Retorna la mida del tauler
         */
        public int getMida()
        {
                return mida;
        }
       
        /**
         * Retorna el valor de la casella a la posicio
         * x,y del tauler
         */
        public int getValorTauler(int x, int y)
        {
                return tauler[x][y].getValor();
        }
       
        /**
         * Posa n a la casella que hi ha a la posicio
         * x,y del tauler
         */
        public void setValorTauler(int x, int y, int n)
        {
                if (!tauler[x][y].isPor_defecto()) tauler[x][y].setValor(n);
        }
       
        /**
         * Esborra el numero de la casella que hi ha
         * a la posicio x,y del tauler
         */
        public void esborrarValorTauler(int x, int y)
        {
                if (!tauler[x][y].isPor_defecto()) tauler[x][y].setValor(0); //Considerem 0 = casella no te valor
        }
       
        /**
         * Retorna els candidats de la casella que hi ha
         * a la posicio x,y del tauler
         */
        public boolean[] getCandidatsTauler(int x, int y)
        {
                return tauler[x][y].getCandidatos();
        }
       
        /**
         * Posa n com a candidat de la casella que hi ha
         * a la posicio x,y del tauler
         */
        public void setCandidatTauler(int x, int y, int n)
        {
                tauler[x][y].setCandidat(n);
        }
     
       
        /**
         * Esborra n dels candidats de la casella que hi
         * ha a la posiciï¿½ x,y del tauler
         */
        public void esborrarCandidatTauler(int x, int y, int n)
        {
                tauler[x][y].esborrarCandidat(n);
        }
       
        /**
         * Posa la casella de la posicio x,y del tauler com
         * a defecte o no
         */
        public void setCasellaPor_defecto(int x, int y, boolean b)
        {
                tauler[x][y].setPor_defecto(b);
        }
}