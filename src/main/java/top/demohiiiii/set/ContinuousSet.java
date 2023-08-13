package top.demohiiiii.set;

import org.apache.commons.collections4.CollectionUtils;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ContinuousSet<T extends ICalculate<T>> implements Set<T> {

    private final Pair<T, T> range;

    public ContinuousSet(T first, T last) {
        range = new Pair<>(first, last);
    }

    public T first() {
        return range.getValue0();
    }


    public T last() {
        return range.getValue1();
    }

    public Relation relation(ContinuousSet<T> that) {
        if (that == null) {
            return Relation.A_PROPER_SUBSET_B;
        }
        if (this == that) return Relation.EQUALITY;
        T A0 = this.range.getValue0();
        T A1 = this.range.getValue1();
        T B0 = that.range.getValue0();
        T B1 = that.range.getValue1();
        if (A0.compareTo(B0) == 0 && A1.compareTo(B1) == 0) {
            return Relation.EQUALITY;
        }
        if (A0.compareTo(B0) <= 0 && A1.compareTo(B1) >= 0) {
            return Relation.A_PROPER_SUBSET_B;
        } else if (A0.compareTo(B0) >= 0 && A1.compareTo(B1) <= 0) {
            return Relation.B_PROPER_SUBSET_A;
        } else if (A1.compareTo(B0) < 0 || A0.compareTo(B1) > 0) {
            return Relation.DISJOINT;
        } else {
            return Relation.INTERSECTING_NON_SUBSET;
        }
    }

    @Override
    public int size() {
        return (int)((range.getValue1().norm() - range.getValue0().norm())/range.getValue0().one().norm()) + 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ContinuousSet<?>) {
            ContinuousSet<T> that = (ContinuousSet<T>) o;
            Relation relation = relation(that);
            return relation.equals(Relation.A_PROPER_SUBSET_B) || relation.equals(Relation.EQUALITY);
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr(range);
    }

    @Override
    public Object[] toArray() {
        return new ArrayList<>(this).toArray();
    }

    @Override
    public <K> K[] toArray(K[] a) {
        return new ArrayList<>(this).toArray(a);
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c instanceof ContinuousSet) {
            ContinuousSet that = (ContinuousSet) c;
            return Relation.A_PROPER_SUBSET_B.equals(this.relation(that)) || Relation.EQUALITY.equals(this.relation(that));
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ContinuousSet) {
            ContinuousSet that = (ContinuousSet) o;
            return Relation.EQUALITY.equals(this.relation(that));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(range.getValue0(), range.getValue1());
    }

    public static <T extends ICalculate<T>> Relation relation(ContinuousSet<T> A, ContinuousSet<T> B) {
        if (A != null) {
            return A.relation(B);
        } else if (B != null) {
            return Relation.B_PROPER_SUBSET_A;
        }

        return Relation.EQUALITY;
    }

    public static <T extends ICalculate<T>> Relation relation(List<ContinuousSet<T>> A, List<ContinuousSet<T>> B) {
        if (CollectionUtils.isEmpty(A) && CollectionUtils.isEmpty(B)) {
            return Relation.EQUALITY;
        } else if (CollectionUtils.isEmpty(A)) {
            return Relation.B_PROPER_SUBSET_A;
        } else if (CollectionUtils.isEmpty(B)) {
            return Relation.A_PROPER_SUBSET_B;
        }
        Map<ContinuousSet<T>, Relation> ARelation = new HashMap<>();
        Map<ContinuousSet<T>, Relation> BRelation = new HashMap<>();
        for(ContinuousSet<T> value1 : A) {
            boolean bHaveA = false;
            boolean isOverlap = false;
            for (ContinuousSet<T> value2 : B) {
                Relation relation = value1.relation(value2);
                if (relation == Relation.A_PROPER_SUBSET_B || relation == Relation.B_PROPER_SUBSET_A || relation == Relation.EQUALITY) {
                    ARelation.put(value1, relation);
                    BRelation.put(value2, relation);
                    bHaveA = true;
                } else if (relation == Relation.INTERSECTING_NON_SUBSET) {
                    isOverlap = true;
                }
            }
            if (isOverlap && !bHaveA) {
                return Relation.INTERSECTING_NON_SUBSET;
            }
        }
        boolean AHaveAll = true;
        Relation resRelation = null;
        for(ContinuousSet<T> value : A) {
            if(ARelation.containsKey(value)) {
                Relation relation = ARelation.get(value);
                if (resRelation == null) {
                    resRelation = relation;
                } else if (relation != Relation.EQUALITY && resRelation != Relation.EQUALITY) {
                    if (resRelation != relation) {
                        return Relation.INTERSECTING_NON_SUBSET;
                    }
                }
                if (relation != Relation.EQUALITY) {
                    resRelation = relation;
                }
            } else {
                AHaveAll = false;
            }
        }
        boolean BHaveAll = true;
        for(ContinuousSet<T> value : B) {
            if (!BRelation.containsKey(value)) {
                BHaveAll = false;
                break;
            }
        }
        if (resRelation == null) {
            return Relation.DISJOINT;
        }
        switch (resRelation) {
            case A_PROPER_SUBSET_B: return BHaveAll?Relation.A_PROPER_SUBSET_B :Relation.INTERSECTING_NON_SUBSET;
            case B_PROPER_SUBSET_A: return AHaveAll?Relation.B_PROPER_SUBSET_A :Relation.INTERSECTING_NON_SUBSET;
            case EQUALITY:
                if (AHaveAll && BHaveAll) {
                    return Relation.EQUALITY;
                } else if (AHaveAll) {
                    return Relation.B_PROPER_SUBSET_A;
                } else if (BHaveAll) {
                    return Relation.A_PROPER_SUBSET_B;
                }
                return Relation.INTERSECTING_NON_SUBSET;
            default: return Relation.DISJOINT;
        }
    }

    private class Itr implements Iterator<T> {

        T current;
        final T last;

        protected Itr (Pair<T, T> range) {
            current = range.getValue0();
            last = range.getValue1();
        }

        @Override
        public boolean hasNext() {
            return current.compareTo(last()) < 0;
        }

        @Override
        public T next() {
            current = current.add(current.one());
            return current;
        }
    }
}
