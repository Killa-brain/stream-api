package utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamUtils {

    /**
     * Нахождение 3-го наибольшего числа
     */
    public static int findThirdLargestNumber(List<Integer> numbers) {
        return numbers.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .skip(2)
                .findFirst()
                .orElseThrow();
    }

    /**
     ** Реализуйте удаление из листа всех дубликатов
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        return list.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Найдите в списке целых чисел 3-е наибольшее «уникальное» число
     */
    public static int findThirdLargestUniqueNumber(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .skip(2)
                .findFirst()
                .orElseThrow();
    }

    /**
     *  Имеется список объектов типа Сотрудник (имя, возраст, должность),
     *  необходимо получить список имен 3 самых старших сотрудников с
     *  должностью «Инженер», в порядке убывания возраста
     */
    public static <T, R> List<R> filterAndSortFirstThree(List<T> list,
                                                         Predicate<T> predicate,
                                                         Comparator<T> comparator,
                                                         Function<T, R> mapper) {
        return list.stream()
                .filter(predicate)
                .sorted(comparator)
                .limit(3)
                .map(mapper)
                .collect(Collectors.toList());
    }

    /**
     * Имеется список объектов типа Сотрудник (имя, возраст, должность),
     * посчитайте средний возраст сотрудников с должностью «Инженер»
     */
    public static <T> Double findAverageField(List<T> list, Predicate<T> predicate, Function<T, Integer> mapper) {
        return list.stream()
                .filter(predicate)
                .map(mapper)
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0);
    }

    /**
     * Найдите в списке слов самое длинное
     */
    public static String getLongestWord(List<String> list) {
        return list.stream()
                .max(Comparator.comparing(String::length))
                .orElse("");
    }

    /**
     * Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
     * Постройте хеш-мапы, в которой будут хранится пары: слово - сколько
     * раз оно встречается во входной строке
     */
    public static Map<String, Integer> getFrequencyWords(String text) {
        return Arrays.stream(text.split(" "))
                .collect(Collectors.groupingBy(word -> word, Collectors.summingInt(word -> 1)));
    }

    /**
     * Отпечатайте в консоль строки из списка в порядке увеличения длины слова,
     * если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
     */
    public static void printListByOrder(List<String> list) {
        list.stream()
                .sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }

    /**
     * Имеется массив строк, в каждой из которых лежит набор из 5 строк,
     * разделенных пробелом, найдите среди всех слов самое длинное,
     * если таких слов несколько, получите любое из них
     */
    public static String getLongestWordFromArrays(String[] arrays) {
        return Arrays.stream(arrays)
                .map(array -> array.split(" "))
                .flatMap(Arrays::stream)
                .max(Comparator.comparing(String::length))
                .orElse("");
    }
}
