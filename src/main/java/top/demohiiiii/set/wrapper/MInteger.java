package top.demohiiiii.set.wrapper;

import top.demohiiiii.set.ICalculate;

import java.util.Objects;

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

    public static MInteger of(int value) {
        return new MInteger(value);
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
        return new MInteger(this.value + m.value, this.one.value);
    }

    @Override
    public MInteger sub(MInteger m) {
        return new MInteger(this.value - m.value, this.one.value);
    }

    @Override
    public double norm() {
        return value;
    }

    @Override
    public int compareTo(MInteger o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MInteger mInteger = (MInteger) o;
        return value == mInteger.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
