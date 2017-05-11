package fr.nantes.xspeedit;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by laurent on 11/05/2017.
 */
public class Box {

    private int spaceLeft;

    private List<Integer> listColis;

    public Box(final Integer spaceLeft) {
        this.spaceLeft = spaceLeft;
        this.listColis = new LinkedList<>();
    }

    public int getSpaceLeft() {
        return spaceLeft;
    }

    public void addColis(Integer colis) {
        this.listColis.add(colis);
        // We calculate the space left in the box
        this.spaceLeft -= colis;
    }

    @Override
    public String toString() {
        StringBuilder stringColis = new StringBuilder();
        for (Integer colis : listColis) {
            stringColis.append(colis);
        }
        return stringColis.toString();
    }
}
