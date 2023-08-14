package top.demohiiiii.set.wrapper;

import top.demohiiiii.set.ICalculate;

import java.util.Objects;

public class MFloat implements ICalculate<MFloat> {

    private float value;

    private MFloat one;

    private MFloat() {}

    public MFloat(float value) {
        this(value, 1f);
    }

    public MFloat(float value, float stepLength) {
        this.value = value;
        one = new MFloat();
        one.value = stepLength;
    }

    public static MFloat of(float value) {
        return new MFloat(value);
    }

    public float getValue() {
        return value;
    }

    @Override
    public MFloat one() {
        return one;
    }

    @Override
    public MFloat add(MFloat m) {
        return new MFloat(this.value + m.value, this.one.value);
    }

    @Override
    public MFloat sub(MFloat m) {
        return new MFloat(this.value - m.value, this.one.value);
    }

    @Override
    public double norm() {
        return value;
    }

    @Override
    public int compareTo(MFloat o) {
        return Float.compare(this.value, o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MFloat mFloat = (MFloat) o;
        return Float.compare(mFloat.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
