package lib;

public class TupleUtil {
    public static class Tuple1<A> extends Pair<A, Object> {
        public final A _1;
        public Tuple1(A a) {
            super(a, null);
            _1 = super._1;
        }
    }

    public static class Tuple2<A, B> extends Pair<A, Tuple1<B>> {
        public final A _1;
        public final B _2;

        public Tuple2(A a, B b) {
            super(a, new Tuple1<>(b));
            _1 = super._1;
            _2 = super._2._1;
        }
    }
    public static class Tuple3<A, B, C> extends Pair<A, Tuple2<B, C>> {
        public final A _1;
        public final B _2;
        public final C _3;

        public Tuple3(A a, B b, C c) {
            super(a, new Tuple2<>(b, c));
            _1 = super._1;
            _2 = super._2._1;
            _3 = super._2._2;
        }

    }

}
