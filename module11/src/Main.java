import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    //first 2 tasks interface

    private interface NeedToBeSorted {
        public Object sortable(List<String> names);
    }

//task1

    //private String noTwo = "";
    static NeedToBeSorted sorted1 = names -> {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(index -> index + ". " + names.get(index))
                .collect(Collectors.joining(", "));
    };

//task2

    static NeedToBeSorted sorted2 = names -> {
        return names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    };

//task3

    public static String sortedArray(String[] arr){
        return Arrays
                .stream(arr)
                .map(s -> s.replaceAll("\\D", ""))
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .sorted()
                .collect(Collectors.joining(", "));
    }

//task4

    public static Stream<Long> infifniteNumbers(){
        long a = 25214903917L;
        long c = 11;
        long m = (long) Math.pow(2, 48);
        long seed = 0L;

        return Stream.iterate(seed, x -> (a * (x + c) % m));
    }

//task5

    public static Stream<Integer> zip(Stream<Integer> first, Stream<Integer> second){
        List<Integer> firstList = first.collect(Collectors.toList());
        List<Integer> secondList = second.collect(Collectors.toList());
        int minSize = Math.min(firstList.size(), secondList.size());
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < minSize; i++) {
            result.add(firstList.get(i));
            result.add(secondList.get(i));
        }
        return result.stream();
    }

    //tester

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Stas");
        names.add("Greg");
        names.add("Nina");
        names.add("Maria");
        names.add("Andriy");
        names.add("Maks");
        names.add("Illya");
        System.out.println(sorted1.sortable(names));
        System.out.println(sorted2.sortable(names));
        String[] numbers = {"1, 2, 0", "4, 5"};
        System.out.println(sortedArray(numbers));
        System.out.println(infifniteNumbers().limit(10).toArray().toString());
        Stream<Integer> first = Stream.of(1,2,3,4,5);
        Stream<Integer> second = Stream.of(10,20,30,40,50,60,70);
        zip(first,second)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}
