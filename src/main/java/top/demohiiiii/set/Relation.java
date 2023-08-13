package top.demohiiiii.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public enum Relation {
    A_PROPER_SUBSET_B, B_PROPER_SUBSET_A, EQUALITY, DISJOINT, INTERSECTING_NON_SUBSET;

    public Relation relation(Relation relation) {
        return relation(this, relation);
    }

    public static Relation relation(Relation first, Relation second) {
        if (first == Relation.A_PROPER_SUBSET_B) {
            if (second == Relation.A_PROPER_SUBSET_B || second == Relation.EQUALITY) {
                return Relation.A_PROPER_SUBSET_B;
            }
        } else if (first == Relation.B_PROPER_SUBSET_A) {
            if (second == Relation.B_PROPER_SUBSET_A || second == Relation.EQUALITY) {
                return Relation.B_PROPER_SUBSET_A;
            }
        } else if (first == Relation.EQUALITY) {
            if (second == Relation.A_PROPER_SUBSET_B || second == Relation.B_PROPER_SUBSET_A || second == Relation.EQUALITY) {
                return second;
            }
        } else if (first == Relation.DISJOINT || second == Relation.DISJOINT) {
            return Relation.DISJOINT;
        }
        return Relation.INTERSECTING_NON_SUBSET;
    }

    public static Relation relation(Relation... relations) {
        if (relations == null || relations.length == 0) {
            return null;
        }
        if (relations.length == 1) {
            return relations[0];
        }
        Relation lastRelation = relations[0];
        for (int i = 1; i < relations.length; i++) {
            lastRelation = relation(relations[i], lastRelation);
        }
        return lastRelation;
    }

    public static Relation stringRelation(List<String> A, List<String> B) {
        if (Collections.disjoint(A, B)) {
            return Relation.DISJOINT;
        }
        boolean a_c_b = new HashSet<>(A).containsAll(B);
        boolean b_c_a = new HashSet<>(B).containsAll(A);
        if (a_c_b && b_c_a) {
            return Relation.EQUALITY;
        } else if (a_c_b) {
            return Relation.A_PROPER_SUBSET_B;
        } else if (b_c_a) {
            return Relation.B_PROPER_SUBSET_A;
        }
        return Relation.INTERSECTING_NON_SUBSET;
    }

    public static boolean isSubsetAB(Relation relation) {
        return relation.equals(A_PROPER_SUBSET_B) || relation.equals(EQUALITY);
    }

    public boolean isSubsetAB() {
        return isSubsetAB(this);
    }

    public static boolean isSubsetBA(Relation relation) {
        return relation.equals(B_PROPER_SUBSET_A) || relation.equals(EQUALITY);
    }

    public boolean isSubsetBA() {
        return isSubsetBA(this);
    }

    public static boolean isIntersecting(Relation relation) {
        return relation.equals(B_PROPER_SUBSET_A) || relation.equals(EQUALITY) || relation.equals(A_PROPER_SUBSET_B) || relation.equals(INTERSECTING_NON_SUBSET);
    }

    public boolean isIntersecting() {
        return isIntersecting(this);
    }
}
