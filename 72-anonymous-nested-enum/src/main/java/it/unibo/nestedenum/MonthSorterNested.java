package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private final static Comparator<String> daysSort = new SortByDays();
    private final static Comparator<String> orderSort = new SortByOrder();

    public static class SortByDays implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            final Month m1 = Month.fromString(o1);
            final Month m2 = Month.fromString(o2);
            return Integer.compare(m1.days, m2.days);
        }
    }

    public static class SortByOrder implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            final Month m1 = Month.fromString(o1);
            final Month m2 = Month.fromString(o2);
            return Integer.compare(m1.ordinal(), m2.ordinal());
        }
    }
    
    @Override
    public Comparator<String> sortByDays() {
        return daysSort;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return orderSort;
    }

    private enum Month {
        JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30), JULY(31),
        AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

        private final int days;

        Month(int days){
            this.days = days;
        }

        public static Month fromString(final String s){ 

            Objects.requireNonNull(s);
        
            String input = s.toLowerCase(Locale.ROOT);

            Month matched = null;
            for (Month month : Month.values()){
                String name = month.name().toLowerCase(Locale.ROOT);
                if (name.startsWith(input)){
                    if (matched != null){
                        throw new IllegalArgumentException("ambiguous: " + month);
                    }
                    matched = month;
                }
            }
            if (matched == null){
                throw new IllegalArgumentException("not a month: " + s);
            }
            return matched;
        }
    }
}

