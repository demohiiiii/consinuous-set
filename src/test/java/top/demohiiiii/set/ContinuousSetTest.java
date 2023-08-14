package top.demohiiiii.set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import top.demohiiiii.set.wrapper.MFloat;
import top.demohiiiii.set.wrapper.MInteger;

class ContinuousSetTest {

    @Test
    void baseTest() {
        ContinuousSet<MInteger> range1 = new ContinuousSet<>(MInteger.of(1), MInteger.of(10));
        Assertions.assertEquals(10, range1.size());
        Assertions.assertEquals(MInteger.of(1), range1.first());
        Assertions.assertEquals(MInteger.of(10), range1.last());

        ContinuousSet<MFloat> range2 = new ContinuousSet<>(MFloat.of(-1.5f), MFloat.of(10.11f));
        Assertions.assertEquals(12, range2.size());
        Assertions.assertEquals(MFloat.of(-1.5f), range2.first());
        Assertions.assertEquals(MFloat.of(10.11f), range2.last());

        MFloat value = range2.first();
        for (MFloat mFloat : range2) {
            Assertions.assertEquals(value, mFloat);
            value = value.add(value.one());
            if (value.compareTo(range2.last()) > 0) {
                value = range2.last();
            }
        }
    }

    @Test
    void relationTest() {
        ContinuousSet<MInteger> range1 = new ContinuousSet<>(MInteger.of(1), MInteger.of(10));
        ContinuousSet<MInteger> range2 = new ContinuousSet<>(MInteger.of(2), MInteger.of(10));
        Assertions.assertEquals(Relation.A_PROPER_SUBSET_B, range1.relation(range2));
        Assertions.assertTrue(range1.contains(range2));
    }
}
