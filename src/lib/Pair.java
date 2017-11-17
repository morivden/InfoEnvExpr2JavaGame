package lib;

public class Pair<A, B> {
    public final A car;
    public final B cdr;
    public Pair(A car_, B cdr_) {car = car_; cdr = cdr_;}

    private static boolean eq(Object o1, Object o2) {return o1 == null ? o2 == null : o1.equals(o2);}
    private static int hc(Object o) {return o == null ? 0 : o.hashCode();}

    @Override public boolean equals(Object o) {
        if (! (o instanceof Pair)) return false;
        Pair<?, ?> rhs = (Pair<?, ?>) o;
        return eq(car, rhs.car) && eq(cdr, rhs.cdr);
    }
    @Override public int hashCode() {return hc(car) ^ hc(cdr);}
}
