package fr.nantes.xspeedit;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by laurent on 08/05/2017.
 */
final class Utils {

    /**
     * Default constructor
     */
    private Utils() {

    }

    /**
     * Transform the String representing the size of the colis to a map a colis grouped by their size
     *
     * @param colis A string representing the size of the colis
     * @return {@link Map<Integer,List<Integer>} A sorted map of colis grouped by their size
     */
    static Map<Integer, List<Integer>> parseColisToMapList(String colis) {

        return Arrays.stream(colis.trim().split("(?<=.)"))
                .map(Integer::valueOf)
                .sorted((f1, f2) -> Integer.compare(f2, f1))
                .collect(Collectors.groupingBy(Integer::intValue, LinkedHashMap::new, toList()));
    }

    /**
     * Remove the colis from the map
     *
     * @param mapListColis the map of list of colis
     * @param colisSize    the colis to remove
     */
    static void removeColisFromMap(Map<Integer, List<Integer>> mapListColis, int colisSize) {
        if (mapListColis.get(colisSize).size() > 1) {
            mapListColis.get(colisSize).remove(1);
        } else {
            mapListColis.remove(colisSize);
        }
    }
}
