package com.ga;

import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
    private final String gene;
    private final int fitness;

    /* Target Gene to achieve with mutations */
    private static final char[] TARGET_GENE = "GA GA hahahaha!".toCharArray();

    private static final Random rand = new Random(System.currentTimeMillis());

    /**
     * Default Constructor
     *
     * @param gene The gene representing this <code>Chromosome</code>
     */
    private Chromosome(String gene) {
        this.gene = gene;
        this.fitness = calculateFitness(gene);
    }

    /**
     * Method for retrieve the gene for this <code>Chromosome</code>
     *
     * @return The Gene for this <code>Chromosome</code>
     */
    String getGene() {
        return this.gene;
    }

    /**
     * Method for retrieve the fitness of this <code>Chromosome</code>
     *
     * @return fitness of this <code>Chromosome</code>
     */
    int getFitness() {
        return fitness;
    }

    /**
     * Helper method used to calculate the fitness for a given gene.  The
     * fitness is defined as being the sum of the absolute value of the
     * difference between the current gene and the target gene.
     *
     * @param gene genome to compare with the target value
     * @return integer containing fitness value
     */
    private int calculateFitness(String gene) {
        int fitness = 0;
        char[] arr  = gene.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            fitness += Math.abs(((int) arr[i]) - ((int) TARGET_GENE[i]));
        }

        return fitness;
    }

    /**
     * Method to generate a new <code>Chromosome</code> that is a random mutation of this <code>Chromosome</code>.
     * This method randomly selects one character in the <code>Chromosome</code>s gene, then replaces it with
     * another random (but valid) character.  Note that this method returns a new <code>Chromosome</code>, it does not
     * modify the existing <code>Chromosome</code>.
     *
     * @return A mutated version of this <code>Chromosome</code>.
     */
    Chromosome mutate() {
        char[] arr = gene.toCharArray();
        int idx = rand.nextInt(arr.length);
        int delta = (rand.nextInt() % 90) + 32;
        arr[idx] = (char) ((arr[idx] + delta) % 122);

        return new Chromosome(String.valueOf(arr));
    }

    /**
     * Method used to mate this <code>Chromosome</code> with another.  The
     * resulting child <code>Chromosome</code>s are returned.
     *
     * @param mate The <code>Chromosome</code> to mate with.
     * @return The resulting <code>Chromosome</code> children.
     */
    Chromosome[] mate(Chromosome mate) {
        // Convert the genes to arrays to make thing easier.
        char[] arr1 = gene.toCharArray();
        char[] arr2 = mate.gene.toCharArray();

        // Select a random pivot point for the mating
        int pivot = rand.nextInt(arr1.length);

        // Provide a container for the child gene data
        char[] child1 = new char[gene.length()];
        char[] child2 = new char[gene.length()];

        // Copy the data from each gene to the first child.
        System.arraycopy(arr1, 0, child1, 0, pivot);
        System.arraycopy(arr2, pivot, child1, pivot, (child1.length - pivot));

        // Repeat for the second child, but in reverse order.
        System.arraycopy(arr2, 0, child2, 0, pivot);
        System.arraycopy(arr1, pivot, child2, pivot, (child2.length - pivot));

        return new Chromosome[]{
                new Chromosome(String.valueOf(child1)),
                new Chromosome(String.valueOf(child2))
        };
    }

    /**
     * A convenience method to generate a random <code>Chromosome</code>.
     *
     * @return A randomly generated <code>Chromosome</code>.
     */
    /* package */
    static Chromosome generateRandom() {
        char[] arr = new char[TARGET_GENE.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) (rand.nextInt(90) + 32);
        }

        return new Chromosome(String.valueOf(arr));
    }

    /**
     * Method to allow for comparing <code>Chromosome</code> objects with
     * one another based on fitness.  <code>Chromosome</code> ordering is
     * based on the natural ordering of the fitnesses of the
     * <code>Chromosome</code>s.
     */
    @Override
    public int compareTo(Chromosome c) {
        if (fitness < c.fitness) {
            return -1;
        } else if (fitness > c.fitness) {
            return 1;
        }

        return 0;
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Chromosome)) {
            return false;
        }

        Chromosome c = (Chromosome) o;
        return (gene.equals(c.gene) && fitness == c.fitness);
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (gene + fitness).hashCode();
    }
}
