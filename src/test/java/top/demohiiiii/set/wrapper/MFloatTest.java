package top.demohiiiii.set.wrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MFloatTest {
    @Test
    void test() {
        MFloat v1 = MFloat.of(22.3f);
        Assertions.assertEquals(22.3f, v1.getValue());

        MFloat v2 = new MFloat(22.3f, 1.1f);
        Assertions.assertEquals(1.1f, v2.one().getValue());
        Assertions.assertEquals(MFloat.of(44.6f), v1.add(v2));
        Assertions.assertEquals(MFloat.of(0), v1.sub(v2));
        Assertions.assertEquals(22.3f, v1.norm());
        Assertions.assertEquals(0, v1.compareTo(v2));
    }
}
