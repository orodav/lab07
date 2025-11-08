package it.unibo.functional.api;

//import it.unibo.functional.Function;

public class FunctionImpl {

    public static <T> Function<T, T> identity() {
        return new Function<T, T>() {
            @Override
            public T call(T input) {
                return input;
            }
        };
    }

}