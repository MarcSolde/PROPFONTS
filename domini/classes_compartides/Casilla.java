package domini.classes_compartides;
import java.util.*;
 
 
 
/**
 * Created by estebanpdc on 8/10/15.
 */
public class Casilla {
     private Set<Integer> candidatos;
      private boolean por_defecto; // si esta en true pertenece al sudoku.
      private int valor;
 
 
      public Casilla() {
      }
 
      public Casilla(Set<Integer> candidatos, boolean por_defecto, int valor) {
            this.candidatos = candidatos;
            this.por_defecto = por_defecto;
            this.valor = valor;
      }
 
      public Set<Integer> getCandidatos() {
            return candidatos;
      }
 
      public void setCandidatos(Set<Integer> candidatos) {
            this.candidatos = candidatos;
      }
 
      public boolean isPor_defecto() {
            return por_defecto;
      }
 
      public void setPor_defecto(boolean por_defecto) {
            this.por_defecto = por_defecto;
      }
 
      public int getValor() {
            return valor;
      }
 
      public void setValor(int valor) {
            this.valor = valor;
      }
 
   
}