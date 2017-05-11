package fr.nantes.xspeedit;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
     * Transform the String representing the size of the colis to a map a colis grouped by the number of colis of each size
     *
     * @param colis A string representing the size of the colis
     * @return {@link Map<Integer,Integer>} A sorted map of colis grouped by the number of colis of each size
     */
    static Map<Integer, Integer> parseColisToMapList(String colis) {

        Collector<Integer, ?, Integer> c = Collectors.reducing(0, Integer::intValue, (a, b) -> a + 1);

        return Arrays.stream(colis.trim().split("(?<=.)"))
                .map(Integer::valueOf)
                .sorted((f1, f2) -> Integer.compare(f2, f1))
                .collect(Collectors.groupingBy(Integer::intValue, LinkedHashMap::new, c));
    }

    /**
     * Remove the colis from the map
     *
     * @param mapListColis the map of list of colis
     * @param colisSize    the colis to remove
     */
    static void removeColisFromMap(Map<Integer, Integer> mapListColis, int colisSize) {
        if (mapListColis.get(colisSize) > 1) {
            mapListColis.put(colisSize, mapListColis.get(colisSize) - 1);
        } else {
            mapListColis.remove(colisSize);
        }
    }
}
