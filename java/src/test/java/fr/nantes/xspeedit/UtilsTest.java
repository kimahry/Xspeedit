package fr.nantes.xspeedit;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;


/**
 * Created by laurent on 08/05/2017.
 */
public class UtilsTest {


    @Test
    public void parseColisToListMap() throws Exception {

        Map<Integer, Integer> mapListColis = Utils.parseColisToMapList("163841689525773");
        Assert.assertNotNull(mapListColis);
        Assert.assertTrue(mapListColis.get(9).equals(1));
        Assert.assertTrue(mapListColis.get(8).equals(2));
        Assert.assertTrue(mapListColis.get(7).equals(2));
        Assert.assertTrue(mapListColis.get(6).equals(2));
        Assert.assertTrue(mapListColis.get(5).equals(2));
        Assert.assertTrue(mapListColis.get(4).equals(1));
        Assert.assertTrue(mapListColis.get(3).equals(2));
        Assert.assertTrue(mapListColis.get(2).equals(1));
        Assert.assertTrue(mapListColis.get(1).equals(2));

    }

    @Test
    public void parseColisToMapList() throws Exception {
        Map<Integer, Integer> mapListColis = Utils.parseColisToMapList("163841689525773");

        Utils.removeColisFromMap(mapListColis, 2);
        Assert.assertNull(mapListColis.get(2));

        Utils.removeColisFromMap(mapListColis, 6);
        Assert.assertEquals((int) mapListColis.get(6), 1);

    }
}