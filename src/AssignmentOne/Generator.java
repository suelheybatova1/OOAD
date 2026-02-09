package AssignmentOne;

/*
name:suel heybatova
project: random number generator statistics
course:ooad
*/

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator { // class definition

    //class attribute(field)
    //private means it is only accessible in a class
    private final Random random = new Random();

    //method that fills a list with random numbers
    //randNumGen
    //1st method - java.util.Random
    //2nd method - Math.random
    //3rd method - ThreadLocalRandom
    public ArrayList<Double> populate(int n, int randNumGen) {
        ArrayList<Double> values = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            double x;
            if (randNumGen == 1) {
                x = random.nextDouble();
            } else if (randNumGen == 2) {
                x = Math.random();
            } else if (randNumGen == 3) {
                x = ThreadLocalRandom.current().nextDouble(0.0, 1.0);
            } else {
                throw new IllegalArgumentException("randNumGen must be 1, 2, or 3");
            }
            values.add(x);
        }
        return values;
    }
    //calculates basic descriptive statistics
    //result order: [n, mean, sample stddev, min, max]
    public ArrayList<Double> statistics(ArrayList<Double> randomValues) {
        int n = randomValues.size();
        if (n == 0) {
            throw new IllegalArgumentException("list is empty");
        }
        double sum = 0.0;
        double min = randomValues.get(0);
        double max = randomValues.get(0);
        for (double v : randomValues) {
            sum += v;
            if (v < min) min = v;
            if (v > max) max = v;
        }
        double mean = sum / n;
        double varianceSum = 0.0;
        for (double v : randomValues) {
            double diff = v - mean;
            varianceSum += diff * diff;
        }
        double stddev;
        if (n == 1) {
            stddev = 0.0;
        } else {
            // sample standard deviation formula
            stddev = Math.sqrt(varianceSum / (n - 1));
        }
        ArrayList<Double> results = new ArrayList<>();
        results.add((double) n);
        results.add(mean);
        results.add(stddev);
        results.add(min);
        results.add(max);
        return results;
    }
    //prints results in table format
    // headerOn controls whether the header row is printed
    public void display(ArrayList<Double> results, boolean headerOn) {
        if (headerOn) {
            System.out.printf("%-18s %-8s %-12s %-12s %-12s %-12s%n",
                    "Generator", "n", "mean", "stddev", "min", "max");
            System.out.println("------------------");
        }
        String genName = getGeneratorName();
        int n = results.get(0).intValue();
        double mean = results.get(1);
        double stddev = results.get(2);
        double min = results.get(3);
        double max = results.get(4);
        System.out.printf("%-18s %-8d %-12.6f %-12.6f %-12.6f %-12.6f%n",
                genName, n, mean, stddev, min, max);
    }
    //helper method used only inside this class
    //private access level
    private String getGeneratorName() {
        return currentGeneratorLabel;
    }
    //class attribute that stores the name of the current generator
    //private so it cannot be accessed from outside
    private String currentGeneratorLabel = "";
    //main workflow method
    //calls populate, statistics and display for all combinations
    public void execute() {
        int[] nValues = {10, 1000, 100000};
        boolean headerPrinted = false;
        for (int gen = 1; gen <= 3; gen++) {
            currentGeneratorLabel = generatorLabel(gen);

            for (int n : nValues) {
                ArrayList<Double> values = populate(n, gen);
                ArrayList<Double> stats = statistics(values);
                display(stats, !headerPrinted);
                headerPrinted = true;
            }
        }
    }
    //returns a readable name for each generator type
    private String generatorLabel(int gen) {
        if (gen == 1) return "java.util.Random";
        if (gen == 2) return "Math.random()";
        return "ThreadLocalRandom";
    }
    public static void main(String[] args) {
        //object instant.
        Generator g = new Generator();
        g.execute();
    }
}
