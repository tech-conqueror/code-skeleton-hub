package com.techconqueror.tool.codeskeletonhub.java;

import java.util.*;

public class Algorithm {

    /**
     * Determines whether a given integer is a prime number.
     * <p>
     * A prime number is a positive integer greater than 1 that has no positive integer
     * divisors other than 1 and itself. This method checks if the given number is prime
     * by testing divisibility from 2 up to the square root of the number.
     * </p>
     *
     * @param n the integer to be tested for primality
     * @return {@code true} if the integer is a prime number, {@code false} otherwise
     * @throws IllegalArgumentException if the integer is less than 1
     * @see <a href="https://en.wikipedia.org/wiki/Prime_number">Prime number (Wikipedia)</a>
     */
    public static boolean isPrime(int n) {
//        if (n < 1) {
//            throw new IllegalArgumentException("Input must be a positive integer greater than 0.");
//        }

        if (n == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Counts the number of 1s in the binary representation of a given integer.
     * <p>
     * This method takes an integer, converts it to its binary form, and counts the number of 1 bits in that binary representation.
     * For example, the binary representation of the integer 13 is 1101, which contains three 1s. Hence, the method would return 3 for this input.
     * </p>
     *
     * @param n the integer whose binary representation is to be analyzed
     * @return the number of 1s in the binary representation of the input integer
     * @throws IllegalArgumentException if the input integer is non-positive
     */
    public static int countOnes(int n) {
        int dividedResult = n;
        int totalOnes = 0;

        if (n <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer greater than 0.");
        }

        while (dividedResult != 0) {
            if (dividedResult % 2 == 1) {
                totalOnes++;
            }

            dividedResult /= 2;
        }

        return totalOnes;
    }

    /**
     * Determines whether the given array is a "Daphne" array.
     * A "Daphne" array is defined as an array that contains only even numbers or only odd numbers.
     * If the array contains both even and odd numbers, it is not considered a "Daphne" array.
     *
     * <p>The method throws an exception if the input array is empty.</p>
     *
     * @param a the array of integers to be checked. Must be a non-empty array.
     * @return {@code true} if the array contains only even numbers or only odd numbers;
     * {@code false} if the array contains both even and odd numbers.
     * @throws IllegalArgumentException if the input array is empty.
     */
    public static boolean isDaphne(int[] a) {
        boolean containsEven = false;
        boolean containsOdd = false;

        if (a.length == 0) {
            throw new IllegalArgumentException("Input must be a non-empty array.");
        }

        for (int i : a) {
            if (i % 2 == 0) {
                containsEven = true;
            } else {
                containsOdd = true;
            }

            if (containsEven && containsOdd) {
                return false;
            }
        }

        return true;
    }

    /**
     * Determines if the given array contains at least one element that appears more than once
     * and at least one odd number.
     *
     * <p>The method works as follows:
     * <ul>
     *   <li>Checks if the input array is empty and throws an {@link IllegalArgumentException} if it is.</li>
     *   <li>Iterates over the array, counting occurrences of each element.</li>
     *   <li>Tracks whether any element occurs more than once and whether any odd number is present.</li>
     *   <li>Returns {@code true} if both conditions are met:
     *       <ul>
     *           <li>At least one element appears more than once.</li>
     *           <li>At least one odd number is present in the array.</li>
     *       </ul>
     *   </li>
     *   <li>Returns {@code false} if either condition is not met.</li>
     * </ul>
     * </p>
     *
     * @param a the array to be checked; must be a non-empty array
     * @return {@code true} if the array contains at least one element that appears more than once
     * and at least one odd number; {@code false} otherwise
     * @throws IllegalArgumentException if the input array is empty
     */
    public static boolean isOddValent(int[] a) {
        boolean occurrencesMoreThanOne = false;
        boolean containsOdd = false;

        if (a.length == 0) {
            throw new IllegalArgumentException("Input must be a non-empty array.");
        }

        Map<Integer, Integer> elementOccurrences = new HashMap<>();
        for (int i : a) {
            elementOccurrences.put(i, elementOccurrences.getOrDefault(i, 0) + 1);
            Integer occurrence = elementOccurrences.get(i);

            if (occurrence > 1) {
                occurrencesMoreThanOne = true;
            }

            if (i % 2 != 0) {
                containsOdd = true;
            }

            if (occurrencesMoreThanOne && containsOdd) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines if a given integer is "normal" according to specific criteria.
     * <p>
     * An integer {@code n} is considered "normal" if it is not divisible by any
     * odd integer greater than 1 and less than {@code n}. In other words, if there
     * exists any odd divisor of {@code n} (other than 1), the method returns {@code false}.
     * If no such odd divisor exists, the method returns {@code true}.
     * <p>
     * Note: The method will return {@code true} for integers less than 2, as there are
     * no valid divisors in that range.
     *
     * @param n the integer to be checked.
     * @return {@code true} if {@code n} is "normal" (i.e., has no odd divisors other
     * than 1), {@code false} otherwise.
     */
    public static boolean isNormal(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0 && i % 2 != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Determines whether the given array contains all integers from 0 to {@code array.length - 1}.
     * <p>
     * This method checks if the array contains every integer in the range from 0 to the length of the array
     * minus one. It returns {@code true} if every integer in this range is present exactly once; otherwise,
     * it returns {@code false}. For instance, for an array of length 3, the method checks if the array
     * contains the integers 0, 1, and 2.
     * </p>
     *
     * @param a an array of integers to be checked
     * @return {@code true} if the array contains all integers from 0 to {@code array.length - 1};
     * {@code false} otherwise
     * @throws NullPointerException if {@code array} is {@code null}
     */
    public static boolean isAllPossibilities(int[] a) {
        if (a.length == 0) {
            return false;
        }

        int n = a.length;
        Set<Integer> set = new HashSet<>();

        for (int num : a) {
            set.add(num);
        }

        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Determines if the given array of integers meets specific criteria based on the presence of certain values.
     * <p>
     * The criteria are as follows:
     * <ul>
     *     <li>The array must contain at least 2 elements; otherwise, an {@link IllegalArgumentException} is thrown.</li>
     *     <li>The method returns {@code true} if either of the following conditions is met:
     *         <ul>
     *             <li>The array contains both the values 9 and 11.</li>
     *             <li>The array contains the value 7 and does not contain the value 13.</li>
     *         </ul>
     *     </li>
     *     <li>If none of these conditions are met, the method returns {@code false}.</li>
     * </ul>
     * </p>
     *
     * @param a the array of integers to be checked; must contain at least 2 elements
     * @return {@code true} if the array meets the specified criteria; {@code false} otherwise
     * @throws IllegalArgumentException if the input array has fewer than 2 elements
     */
    public static boolean isFilter(int[] a) {
        boolean contains9 = false;
        boolean contains11 = false;
        boolean contains7 = false;
        boolean contains13 = false;

        if (a.length < 2) {
            throw new IllegalArgumentException("The input array must contains at least 2 elements.");
        }

        for (int i : a) {
            switch (i) {
                case 9:
                    contains9 = true;
                    break;
                case 11:
                    contains11 = true;
                    break;
                case 7:
                    contains7 = true;
                    break;
                case 13:
                    contains13 = true;
                    break;
                default:
                    break;
            }
        }

        return (contains9 && contains11) || (contains7 && !contains13);
    }

    /**
     * Determines if the sum of the digits of a given number is less than a specified threshold.
     *
     * <p>This method first checks if either the number or the threshold is negative. If either is negative,
     * the method returns -1 to indicate an error. Otherwise, it calculates the sum of the digits of the given number,
     * and then compares this sum to the provided threshold.</p>
     *
     * <p>If the sum of the digits is less than the threshold, the method returns 1. If the sum is greater than or
     * equal to the threshold, it returns 0.</p>
     *
     * @param number    The integer whose digits' sum is to be calculated. Must be non-negative.
     * @param threshold The threshold value to compare the sum of the digits against. Must be non-negative.
     * @return 1 if the sum of the digits of {@code number} is less than {@code threshold};
     * 0 if the sum is greater than or equal to {@code threshold};
     * -1 if either {@code number} or {@code threshold} is negative.
     */
    public static int isDigitSum(int number, int threshold) {
        int digitSum = 0;

        // Check if either number or threshold is negative
        if (number < 0 || threshold < 0) {
            return -1;
        }

        // Convert the number to its string representation
        String numberStr = String.valueOf(number);

        // Calculate the sum of the digits of the number
        for (int index = 0; index < numberStr.length(); index++) {
            int digit = Character.getNumericValue(numberStr.charAt(index));
            digitSum += digit;
        }

        // Compare the sum of the digits with the threshold
        return digitSum < threshold ? 1 : 0;
    }

    public static boolean isFineArray(int[] a) {
        Set<Integer> primes = new HashSet<>();

        for (int i : a) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }

        if (primes.isEmpty()) {
            return true;
        }

        for (Integer prime : primes) {
            if (primes.contains(prime - 2) || primes.contains(prime + 2)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isBalanced1(int[] a) {
        for (int i : a) {
            boolean isContainOpposite = false;

            for (int j : a) {
                if (j == (-1 * i)) {
                    isContainOpposite = true;
                    break;
                }
            }

            if (!isContainOpposite) {
                return false;
            }
        }

        return true;
    }

    public static boolean isEvens(int n) {
        while (n != 0) {
            // Extract the last digit
            int lastDigit = n % 10;

            if (lastDigit % 2 != 0) {
                return false;
            }

            // Remove the last digit from the number
            n /= 10;
        }

        return true;
    }

    public static boolean isMagic(int[] a) {
        int sumOfPrimes = 0;

        for (int i : a) {
            if (isPrime(i)) {
                sumOfPrimes += i;
            }
        }

        return sumOfPrimes == a[0];
    }

    public static boolean isComplete1(int[] a) {
        boolean containsEven = false;
        int minEven = a[0];
        int maxEven = a[0];
        Set<Integer> set = new HashSet<>();

        for (int i : a) {
            if (i % 2 == 0) {
                containsEven = true;

                if (minEven % 2 != 0 || minEven > i) {
                    minEven = i;
                }

                if (maxEven % 2 != 0 || maxEven < i) {
                    maxEven = i;
                }
            }

            set.add(i);
        }

        if (!containsEven || minEven == maxEven) {
            return false;
        }

        for (int i = minEven + 1; i < maxEven; i++) {
            if (!set.contains(i)) {
                return false;
            }
        }

        return true;
    }

    public static void isPrimeProduct(int n) {

    }

    public static boolean isBalanced2(int[] a) {
        for (int i = 0; i < a.length; i += 2) {
            if (a[i] % 2 != 0) {
                return false;
            }
        }

        for (int i = 1; i < a.length; i += 2) {
            if (a[i] % 2 == 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isCentered(int[] a) {
        if (a.length % 2 == 0) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (i == a.length / 2) {
                continue;
            }

            if (a[a.length / 2] >= a[i]) {
                return false;
            }
        }

        return true;
    }

    public static void hasKSmallFactors(int k, int n) {

    }

    public static int[] fill(int[] a, int k, int n) {
        int[] result = new int[n];
        int i = 0;

        while (i < n) {
            for (int j = 0; j < k; j++) {
                if (i >= n) {
                    break;
                }

                result[i] = a[j];
                i++;
            }
        }

        return result;
    }

    public static boolean isHollow(int[] a) {
        if (a.length % 2 == 0 || a[a.length / 2] != 0) {
            return false;
        }

        int i = 1;

        while (a[a.length / 2 - i] == 0) {
            if (a[a.length / 2 + i] != 0) {
                return false;
            }

            i++;
        }

        int totalZerosInMiddle = ((i - 1) * 2) + 1;

        if ((a.length - totalZerosInMiddle) / 2 < totalZerosInMiddle) {
            return false;
        }

        int leftStart = a.length / 2 - i;
        int rightStart = a.length / 2 + i;

        while (leftStart >= 0) {
            if (a[leftStart] == 0 || a[rightStart] == 0) {
                return false;
            }

            leftStart--;
            rightStart++;
        }

        return true;
    }

    public static int minDistance(int n) {
        int previousFactor = 1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                if ((i - previousFactor) < minDistance) {
                    minDistance = i - previousFactor;
                }

                previousFactor = i;
            }
        }

        return minDistance;
    }

    public static boolean isWave(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                break;
            }

            if (a[i] % 2 == 0 && a[i + 1] % 2 == 0) {
                return false;
            }

            if (a[i] % 2 != 0 && a[i + 1] % 2 != 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isBeanArray1(int[] a) {
        Set<Integer> set = new HashSet<>();

        for (int i : a) {
            set.add(i);
        }

        return (set.contains(9) && set.contains(13)) || (set.contains(7) && !set.contains(16));
    }

    public static int countDigit(int n, int digit) {
        int numberOfOcurrences = 0;

        while (n != 0) {
            // Extract the last digit
            int lastDigit = n % 10;

            if (lastDigit == digit) {
                numberOfOcurrences++;
            }

            // Remove the last digit from the number
            n /= 10;
        }

        return numberOfOcurrences;
    }

    public static boolean isBunkerArray1(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                break;
            }

            if (a[i] % 2 != 0 && isPrime(a[i + 1])) {
                return true;
            }
        }

        return false;
    }

    public static boolean isMeeraArray1(int[] a) {
        Set<Integer> set = new HashSet<>();

        for (int i : a) {
            set.add(i);
        }

        for (int i : a) {
            if (set.contains(i * 2)) {
                return false;
            }
        }

        return true;
    }

    public static void isMeeraNumber(int n) {

    }

    public static boolean isBunkerArray2(int[] a) {
        Set<Integer> set = new HashSet<>();

        for (int i : a) {
            set.add(i);
        }

        for (int i : a) {
            if (isPrime(i) && set.contains(1)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNiceArray1(int[] a) {
        Set<Integer> set = new HashSet<>();

        for (int i : a) {
            set.add(i);
        }

        for (int i : a) {
            if (!set.contains(i + 1) && !set.contains(i - 1)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isContinuousFactored(int n) {
        int multiply = 1;
        int countFactor = 0;
        Integer lastFactor = null;

        for (int i = 2; i < n; i++) {
            if (countFactor > 1) {
                return true;
            }

            if (n % i != 0) {
                continue;
            }

            if ((lastFactor != null && i - lastFactor > 1) || multiply * i > n) {
                multiply = i;
                countFactor = 1;
            } else {
                multiply *= i;
                countFactor++;
            }

            lastFactor = i;
        }

        return false;
    }

    public static boolean isTwin(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int i : a) {
            set.add(i);
        }

        for (int i : a) {
            if (isPrime(i) && ((isPrime(i - 2) && !set.contains(i - 2)) || (isPrime(i + 2) && !set.contains(i + 2)))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isSetEqual(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }

        Set<Integer> foundIndexes = new HashSet<>();

        for (int i : a) {
            boolean isFound = false;

            for (int j = 0; j < b.length; j++) {
                if (i == b[j]) {
                    isFound = true;
                    foundIndexes.add(j);
                    break;
                }
            }

            if (!isFound) {
                return false;
            }
        }

        return foundIndexes.size() == b.length;
    }

    public static void isSmart(int n) {

    }

    public static boolean isNiceArray2(int[] a) {
        int sum = 0;

        for (int i : a) {
            if (isPrime(i)) {
                sum += i;
            }
        }

        return (sum == 0 && a[0] == 0) || (sum > 0 && sum == a[0]);
    }

    public static boolean isComplete2(int[] a) {
        int maxEvenNumber = -1;
        Set<Integer> evenNumbers = new HashSet<>();

        for (int i : a) {
            if (i <= 0) {
                return false;
            }

            if (i % 2 == 0) {
                if (maxEvenNumber < i) {
                    maxEvenNumber = i;
                }
                evenNumbers.add(i);
            }
        }

        for (int i = 2; i < maxEvenNumber; i += 2) {
            if (!evenNumbers.contains(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean factorEqual(int n, int m) {
        int totalFactorsOfN = 0;
        int totalFactorsOfM = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                totalFactorsOfN++;
            }
        }

        for (int i = 1; i <= m; i++) {
            if (n % i == 0) {
                totalFactorsOfM++;
            }
        }

        return totalFactorsOfN == totalFactorsOfM;
    }

    public static boolean isMeeraArray2(int[] a) {
        int sum = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] > i) {
                return false;
            }

            sum += a[i];
        }

        return sum == 0;
    }

    public static boolean isTriple(int[] a) {
        Map<Integer, Integer> numberOfOccurrences = new HashMap<>();

        for (int i : a) {
            numberOfOccurrences.put(i, numberOfOccurrences.getOrDefault(i, 0) + 1);
        }

        for (int occurrences : numberOfOccurrences.values()) {
            if (occurrences != 3) {
                return false;
            }
        }

        return true;
    }

    public static boolean isFibonacci(int n) {
        int previousFibonacci = 1;
        int lastFibonacci = 1;

        while (lastFibonacci < n) {
            int temp = lastFibonacci;
            lastFibonacci += previousFibonacci;
            previousFibonacci = temp;


            if (lastFibonacci == n) {
                return true;
            }
        }

        return false;
    }

    public static boolean isMeeraArray3(int[] a) {
        boolean containsPrime = false;
        boolean containsZero = false;

        for (int i : a) {
            if (isPrime(i)) {
                containsPrime = true;
            }

            if (i == 0) {
                containsZero = true;
            }
        }

        return (containsPrime && containsZero) || (!containsPrime && !containsZero);
    }
}
