package fr.nantes.xspeedit;

import fr.nantes.xspeedit.Xspeedit;
import org.junit.Test;

/**
 * Created by laurent on 08/05/2017.
 */
public class XspeeditTest {

    @Test
    public void main_case1() throws Exception {
        String[] args = {"55"};
        Xspeedit.main(args);
    }

    @Test
    public void main_case2() throws Exception {
        String[] args = {"163841689525773"};
        Xspeedit.main(args);
    }

    @Test
    public void main_case3() throws Exception {
        String[] args = {"442211111"};
        Xspeedit.main(args);
    }

    @Test
    public void main_case4() throws Exception {
        String[] args = {"1638112349078765557678768979023848238423843241689525773"};
        Xspeedit.main(args);
    }

    @Test
    public void main_case5() throws Exception {
        String[] args = {"32111111"};
        Xspeedit.main(args);
    }

    @Test
    public void main_case6() throws Exception {
        String[] args = {"16381123490787189489218791451121564748712121212154778321548792123265987452121111111112222222255555544778999962222445454878111983065557678768979023848238423843241689525773"};
        Xspeedit.main(args);
    }

    @Test
    public void main_case7() throws Exception {
        String[] args = {"32pl111111"};
        Xspeedit.main(args);
    }

    @Test
    public void main_case8() throws Exception {
        String[] args = {"32l+111111"};
        Xspeedit.main(args);
    }


}