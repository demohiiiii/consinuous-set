package top.demohiiiii.set.wrapper;

import top.demohiiiii.set.ICalculate;

public class MInteger implements ICalculate<MInteger> {

    private int value;

    private MInteger one;

    private MInteger() {}

    public MInteger(int value) {
        this(value, 1);
    }

    public MInteger(int value, int stepLength) {
        this.value = value;
        this.one = new MInteger();
        this.one.value = stepLength;
    }

    public int getValue() {
        return value;
    }

    @Override
    public MInteger one() {
        return one;
    }

    @Override
    public MInteger add(MInteger m) {
        return new MInteger(this.value + m.value);
    }

    @Override
    public MInteger sub(MInteger m) {
        return new MInteger(this.value - m.value);
    }

    @Override
    public double norm() {
        return value;
    }

    @Override
    public int compareTo(MInteger o) {
        return Integer.compare(this.value, o.value);
    }
}
