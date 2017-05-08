package fr.nantes.xspeedit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by laurent on 08/05/2017.
 */
public class Xspeedit {

    private final static Logger LOGGER = Logger.getLogger(Xspeedit.class);
    private final static Integer BOX_SIZE = 10;

    public static void main(String[] args) {

        StringBuilder completedBox = new StringBuilder();
        Integer boxNumber = 0;

        if (args.length == 1 && StringUtils.isNumeric(args[0])) {
            LOGGER.info("Processing colis: " + args[0]);
            Map<Integer, List<Integer>> mapListColis = Utils.parseColisToMapList(args[0]);

            // We search the map for each colis size, starting by the biggest one
            for (int i = BOX_SIZE; i > 0; i--) {

                if (mapListColis.containsKey(i)) {

                    while (mapListColis.get(i) != null) {

                        List<Integer> box = new LinkedList<>();
                        // We insert the first colis
                        box.add(i);
                        // We remove the colis from the map
                        Utils.removeColisFromMap(mapListColis, i);
                        // We calculate the space left in the box
                        int spaceLeft = BOX_SIZE - i;
                        // We search the map for other colis to complete the box
                        searchColisToFillTheBox(spaceLeft, mapListColis, box);
                        // We append the colis to the list of completed boxes
                        for (Integer colis : box) {
                            completedBox.append(colis);
                        }

                        if (mapListColis.size() > 0) {
                            completedBox.append("/");
                        }
                        // Count the number of box
                        boxNumber++;
                    }

                }

            }

            LOGGER.info("Number of box:" + boxNumber);
            LOGGER.info("Content of the box: " + completedBox.toString());
        } else {
            LOGGER.error("Invalid argument, it must be a string of number");
        }


    }

    /**
     * @param spaceLeft    The space left in the box
     * @param mapListColis The map of list of available colis
     * @return {@link List<Integer>} A list of the colis to fill the box
     */
    private static Integer searchColisToFillTheBox(Integer spaceLeft, final Map<Integer, List<Integer>> mapListColis, List<Integer> currentBox) {

        // If no space left, we stop the search
        if (spaceLeft == 0) {
            return spaceLeft;
        }

        // We lookup the map to find colis to fill the space left
        for (int i = spaceLeft; i > 0; i--) {
            if (mapListColis.containsKey(i) && spaceLeft - i >= 0) {
                // We add the colis to the box
                currentBox.add(i);
                // We update the space left inside the box
                spaceLeft = spaceLeft - i;
                // We remove the colis from the map
                Utils.removeColisFromMap(mapListColis, i);
                // If there is still some space left, we call the function again
                if (spaceLeft > 0) {
                    spaceLeft = searchColisToFillTheBox(spaceLeft, mapListColis, currentBox);
                }
            }
        }
        return spaceLeft;
    }


}
