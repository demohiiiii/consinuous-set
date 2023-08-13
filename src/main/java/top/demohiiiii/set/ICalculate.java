package top.demohiiiii.set;

public interface ICalculate<T> extends Comparable<T> {
    T one();
    T add(T m);
    T sub(T m);
    double norm();
}
