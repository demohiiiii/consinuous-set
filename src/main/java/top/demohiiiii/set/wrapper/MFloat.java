package top.demohiiiii.set.wrapper;

import top.demohiiiii.set.ICalculate;

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
}
