package pl.edu.uj.javaframe;

public class Factorize implements Applayable {
    @Override
    public void apply(Series v) {
        for(int i = 0; i < v.values.size(); i++) {
            Int value = (Int) v.values.get(i);         // przechowuje obiekt
            int number = (Integer) value.value;
            int sumOfPrimeFactors = sumPrimeFactors(number);
            value.value = sumOfPrimeFactors;
        }
    }

    private int sumPrimeFactors(int num) {
        int sum = 0;

        while (num % 2 == 0) {
            sum += 2;
            num /= 2;
        }

        for (int i = 3; i < Math.sqrt(num); i += 2) {
            while (num % i == 0) {
                sum += i;
                num /= i;
            }
        }
        if (num > 2) {
            sum += num;
        }

        return sum;
    }
}
