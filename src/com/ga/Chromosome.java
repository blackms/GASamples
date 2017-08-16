package com.ga;

import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
    private final String gene;
    private final int fitness;

    /* Target Gene to achieve with mutations */
    private static final char[] TARGET_GENE = "This is GA Example!".toCharArray();

    private static final Random rand = new Random(System.currentTimeMillis());

    /**
     * Default Constructor
     * @param gene The gene representing this <code>Chromosome</code>
     */
    public Chromosome(String gene) {
            this.gene = gene;
            this.fitness = calculateFitness(gene);
    }

    /**
     * Method for retrieve the gene for this <code>Chromosome</code>
     * @return The Gene for this <code>Chromosome</code>
     */
    public String getGene() {
        return this.gene;
    }

    /**
     * Method for retrieve the fitness of this <code>Chromosome</code>
     * @return fitness of this <code>Chromosome</code>
     */
    public int getFitness() {
        return fitness;
    }


    private int calculateFitness(String gene) {
        return 0;
    }

    @Override
    public int compareTo(Chromosome o) {
        return 0;
    }
}
