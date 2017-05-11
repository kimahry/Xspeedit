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

        List<Box> completedBoxes = new LinkedList<>();

        if (args.length == 1 && StringUtils.isNumeric(args[0])) {
            LOGGER.info("Processing colis: " + args[0]);
            Map<Integer, Integer> mapListColis = Utils.parseColisToMapList(args[0]);

            // We search the map for each colis size, starting by the biggest one
            for (int i = BOX_SIZE - 1; i > 0; i--) {

                if (mapListColis.containsKey(i)) {

                    while (mapListColis.get(i) != null) {
                        // We create a new Box
                        Box box = new Box(BOX_SIZE);
                        // We insert the first colis
                        box.addColis(i);
                        // We remove the colis from the map
                        Utils.removeColisFromMap(mapListColis, i);
                        // We search the map for other colis to complete the box
                        searchColisToFillTheBox(box, mapListColis);
                        // We add the box to the list of completed boxes
                        completedBoxes.add(box);
                    }
                }

            }

            LOGGER.info("Number of box:" + completedBoxes.size());
            StringBuilder stringCompletedBoxes = new StringBuilder();
            int index = 0;
            for (Box completedBox : completedBoxes) {
                stringCompletedBoxes.append(completedBox.toString());
                if (index < completedBoxes.size()) {
                    stringCompletedBoxes.append("/");
                }
            }
            LOGGER.info("Content of the box: " + stringCompletedBoxes.toString());

        } else {
            LOGGER.error("Invalid argument, it must be a string of number");
        }

    }

    /**
     * @param box          The current box to fill up
     * @param mapListColis The map of list of available colis
     */
    private static void searchColisToFillTheBox(final Box box, final Map<Integer, Integer> mapListColis) {

        // We lookup the map to find colis to fill the space left
        for (int i = box.getSpaceLeft(); i > 0; i--) {

            if (mapListColis.containsKey(i) && box.getSpaceLeft() - i >= 0) {
                // We add the colis to the box
                box.addColis(i);
                // We remove the colis from the map
                Utils.removeColisFromMap(mapListColis, i);
                // If there is still some space left, we call the function again
                if (box.getSpaceLeft() > 0) {
                    searchColisToFillTheBox(box, mapListColis);
                } else {
                    // If no space left, we stop the search
                    return;
                }
            }
        }
    }

}



