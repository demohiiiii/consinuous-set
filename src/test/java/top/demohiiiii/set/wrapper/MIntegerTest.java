package top.demohiiiii.set.wrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MIntegerTest {

    @Test
    void test() {
        MInteger v1 = MInteger.of(22);
        Assertions.assertEquals(22, v1.getValue());

        MInteger v2 = new MInteger(22, 2);
        Assertions.assertEquals(2, v2.one().getValue());
        Assertions.assertEquals(MInteger.of(44), v2.add(v1));
        Assertions.assertEquals(MInteger.of(0), v2.sub(v1));
        Assertions.assertEquals(22, v2.norm());
        Assertions.assertEquals(0, v1.compareTo(v2));
    }
}
