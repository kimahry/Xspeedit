package fr.nantes.xspeedit;

import fr.nantes.xspeedit.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;


/**
 * Created by laurent on 08/05/2017.
 */
public class UtilsTest {


    @Test
    public void parseColisToListMap() throws Exception {

        Map<Integer, List<Integer>> mapListColis = Utils.parseColisToMapList("163841689525773");
        Assert.assertNotNull(mapListColis);
        Assert.assertTrue(mapListColis.get(9).equals(Arrays.asList(9)));
        Assert.assertTrue(mapListColis.get(8).equals(Arrays.asList(8, 8)));
        Assert.assertTrue(mapListColis.get(7).equals(Arrays.asList(7, 7)));
        Assert.assertTrue(mapListColis.get(6).equals(Arrays.asList(6, 6)));
        Assert.assertTrue(mapListColis.get(5).equals(Arrays.asList(5, 5)));
        Assert.assertTrue(mapListColis.get(4).equals(Arrays.asList(4)));
        Assert.assertTrue(mapListColis.get(3).equals(Arrays.asList(3, 3)));
        Assert.assertTrue(mapListColis.get(2).equals(Arrays.asList(2)));
        Assert.assertTrue(mapListColis.get(1).equals(Arrays.asList(1, 1)));

    }

    @Test
    public void parseColisToMapList() throws Exception {
        Map<Integer, List<Integer>> mapListColis = Utils.parseColisToMapList("163841689525773");

        Utils.removeColisFromMap(mapListColis, 2);
        Assert.assertNull(mapListColis.get(2));

        Utils.removeColisFromMap(mapListColis, 6);
        Assert.assertEquals(mapListColis.get(6).size(),1);

    }
}