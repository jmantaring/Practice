package java8.functional;

import java.util.*;
import java.util.function.BiFunction;


public class MastersExercise3 {

    public static void main(String[] args) {

        final List<EmployeeDetails> employeeDetails = Arrays.asList(
                EmployeeDetails.create("emp1", "af", "ma", "la", "1"),
                EmployeeDetails.create("emp2", "bf", "mb", "lb", "2"),
                EmployeeDetails.create("emp3", "cf", "mc", "lc", "3")
        );

        final Optional<EmployeeDetails> found = SearchUtil.search(employeeDetails, SearchUtil.Criteria.FIRST_NAME, "cf");
        found.ifPresent(System.out::println);
    }

    public static class SearchUtil {

        enum Criteria {

            EMPLOYEE_NUMBER((employeeDetails, searchKey) -> Objects.equals(employeeDetails.getEmployeeNumber(), searchKey)),
            FIRST_NAME((employeeDetails, searchKey) -> Objects.equals(employeeDetails.getFirstName(), searchKey)),
            LAST_NAME((employeeDetails, searchKey) -> Objects.equals(employeeDetails.getLastName(), searchKey)),
            MIDDLE_NAME((employeeDetails, searchKey) -> Objects.equals(employeeDetails.getMiddleName(), searchKey)),
            HIRING_DATE((employeeDetails, searchKey) -> Objects.equals(employeeDetails.getHiringDate(), searchKey))

            ;

            private BiFunction<EmployeeDetails, String, Boolean> evaluator;

            Criteria(BiFunction<EmployeeDetails, String, Boolean> evaluator) {
                this.evaluator = evaluator;
            }

            public boolean evaluate(EmployeeDetails employeeDetails, String searchKey) {
                return evaluator.apply(employeeDetails, searchKey);
            }

        };

        public static Optional<EmployeeDetails> search(List<EmployeeDetails> employeeDetailsList, Criteria criteria, String searchKey) {
            return employeeDetailsList.stream()
                    .filter(employeeDetails -> criteria.evaluate(employeeDetails, searchKey))
                    .findFirst();
        }
    }


    private static class EmployeeDetails {
        String employeeNumber;
        String firstName;
        String middleName;
        String lastName;
        String hiringDate;

        public String getEmployeeNumber() {
            return employeeNumber;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getHiringDate() {
            return hiringDate;
        }

        String getFullName() {
            return String.format("%s, %s %s");
        }

        @Override
        public String toString() {
            return "Employee [firstName=" + firstName + ", lastName=" + lastName + "]";
        }

        static EmployeeDetails create(
                String employeeNumber,
                String firstName,
                String middleName,
                String lastName,
                String hiringDate
        ) {
            final EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.employeeNumber = employeeNumber;
            employeeDetails.firstName = firstName;
            employeeDetails.middleName = middleName;
            employeeDetails.lastName = lastName;
            employeeDetails.hiringDate = hiringDate;
            return employeeDetails;
        }
    }
}
