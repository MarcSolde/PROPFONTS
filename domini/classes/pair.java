package domini.classes;
import java.util.*;
/**
 * 
 * @author Marc
 *
 * @param <F>
 * @param <S>
 */
public class pair<F, S> {
    private F first; //first member of pair
    private S second; //second member of pair

    public pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}