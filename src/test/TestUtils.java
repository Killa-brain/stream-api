package test;

import domain.Employee;
import utils.StreamUtils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static domain.Role.*;

public class TestUtils {
    public static void main(String[] args) {

        List<Integer> ints = List.of(2, 5, 4, 16, 34, 22, 11, 11, 5, 23, 23, 12);
        List<String> words = List.of("tratata", "bandura", "harley", "masteritsa", "chaos", "alpha");
        String text = "текст тест проверка тест исправление обучение навык использование текст исправление функция" +
                " поиск направление тест тест обучение дисциплина";
        String[] arrays = {
                "rollback forward test start knockout",
                "text search out if else",
                "test reverse rise fortuna gambling",
                "lucky knowledge rocket pepsi brain",
                "feel bingo delta pilot query"
        };
        List<Employee> employeeList = List.of(
                new Employee("John", 30, WORKER),
                new Employee("Lito", 22, ENGINEER),
                new Employee("Roe", 26, ENGINEER),
                new Employee("Bart", 29, WORKER),
                new Employee("Fryea", 35, ENGINEER),
                new Employee("Marta", 36, BOSS),
                new Employee("Elba", 26, WORKER),
                new Employee("Patrick", 37, ENGINEER),
                new Employee("Patrick", 37, WORKER),
                new Employee("Phil", 32, WORKER)
        );


        //return without 5, 11, 23
        var result = StreamUtils.removeDuplicates(ints);
        System.out.println(result);

        //return 22
        int thirdLargestUniqueNumber = StreamUtils.findThirdLargestUniqueNumber(ints);
        System.out.println(thirdLargestUniqueNumber);

        //return 23
        int thirdLargestNumber = StreamUtils.findThirdLargestNumber(ints);
        System.out.println(thirdLargestNumber);

        // must return:
//        Patrick
//        Fryea
//        Roe
        Predicate<Employee> predicate = (e) -> ENGINEER == e.getRole();
        var employees = StreamUtils
                .filterAndSortFirstThree(employeeList,
                        predicate,
                        Comparator.comparing(Employee::getAge, Comparator.reverseOrder()),
                        Employee::getName);
        employees.forEach(System.out::println);

        //return 30.0
        double averageAge = StreamUtils.findAverageField(employeeList, predicate, Employee::getAge);
        System.out.println(averageAge);

        // return masteritsa
        var longest = StreamUtils.getLongestWord(words);
        System.out.println(longest);

        //{функция=1, использование=1, поиск=1, текст=2, направление=1, исправление=2, проверка=1, дисциплина=1, тест=4, обучение=2, навык=1}
        var mapFrequency = StreamUtils.getFrequencyWords(text);
        System.out.println(mapFrequency);

        /**
         * alpha
         * chaos
         * harley
         * bandura
         * tratata
         * masteritsa
         */
        StreamUtils.printListByOrder(words);

        // knowledge
        var longestWordFromArrays = StreamUtils.getLongestWordFromArrays(arrays);
        System.out.println("longestWordFromArrays: " + longestWordFromArrays);
    }
}
