/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weightedavgwithexceptions;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.ArrayList;

import java.util.Collections;

import java.util.Scanner;

/**
 *
 * @author rakshitgarg
 */
public class WeightedAvgWithExceptions {

    public static void main(String[] args) {

        ArrayList<Double> inputValues = getData();


        double weightedAvg = calcWeightedAvg(inputValues);

        printResults(inputValues, weightedAvg);

    }

    static ArrayList<Double> getData() {

        ArrayList<Double> inputVals = new ArrayList<>();

        System.out.println("Enter data.txt for input file:");

        Scanner sc = new Scanner(System.in);

        String filename = sc.nextLine();

        File file = new File(filename);

        if (filename.equals("data.txt")) {

            try {

                sc = new Scanner(file);

                while (sc.hasNextDouble()) {
                    inputVals.add(sc.nextDouble());
                }

            } catch (FileNotFoundException e) {


                System.out.println("File Not Found at the path or filename is incorrect");

            } catch (Exception e) {

                e.printStackTrace();

            } finally {


            }

        }

        return inputVals;

    }

    public static double calcWeightedAvg(ArrayList<Double> inputValues) {

        double weightedAvg = 0;

        double weight = inputValues.get(0);

        int noOfElementsToSkip = (int) inputValues.get(1).doubleValue();

        ArrayList<Double> vals = new ArrayList<Double>();

        for (int i = 2; i < inputValues.size(); i++) {
            vals.add(inputValues.get(i));
        }

        Collections.sort(vals);

        for (int i = noOfElementsToSkip; i < vals.size(); i++) {
            weightedAvg += weight * vals.get(i);
        }


        weightedAvg = weightedAvg / (vals.size() - noOfElementsToSkip);


        return weightedAvg;

    }

    public static void printResults(ArrayList<Double> inputValues, double weightedAvg) {

        System.out.println("Enter the file name for output:");

        Scanner sc = new Scanner(System.in);

        String filename = sc.nextLine();

        try {

            PrintWriter out = new PrintWriter(filename);

            out.println("Weighted Average is: " + weightedAvg);

            out.println("Input Values provided are: " + inputValues);

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

        sc.close();

    }

}
