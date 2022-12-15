package com.example.reliability;/*      Example Four
         Example of the use of the class RungeKutta demonstrating the use of instance methods in
          solving a set of three ordinary differential equation describing two consecutive first order processes:
                  y[0] --(k1)--> y[1] --(k2)--> y[2]
                   k1= rate constant for first process, k2 = rate constant for second process
                   dy[0]/dt = -k1.y[0]
                   dy[1]/dt = k1.y[0] - k2.y[1]
                   dy[2]/dt = -k2.y[1]

       Michael Thomas Flanagan
       m.flanagan@ee.ucl.ac.uk

       Updated  January 2010
*/


import flanagan.integration.RungeKutta;
import flanagan.integration.DerivnFunction;


// Class to demonstrate instance methods in class RungeKutta.
public class RungeKuttaExampleFour{

    public static void main(String[ ] arg){

        // Create instance of the class holding the derivative evaluation method
        Derivn1 dn = new Derivn1();

        // Assign values to constants in the derivative function
        double k1 = 5.0D;
        double k2 = 2.0D;
        dn.setKvalues(k1, k2);

        // Variable declarations
        int n = 3;                            // number of differential equations
        double h = 0.01;                      // step size
        double x0 = 0.0D;                     // initial value of x
        double xn = 1.0D;                     // final value of x
        double[ ] y0 = new double[n];         // initial values of the y[i]
        double[ ] yn = new double[n];         // returned value of the y[i] at x = xn


        // Assign initial values of y[i]
        y0[0] = 5.0D;
        y0[1] = 1.0D;
        y0[2] = 0.5D;

        // Create and instance of RungeKutta
        RungeKutta rk = new RungeKutta();

        // Set values needed by fixed step size method
        rk.setInitialValueOfX(x0);
        rk.setFinalValueOfX(xn);
        rk.setInitialValuesOfY(y0);
        rk.setStepSize(h);

        // Call Fourth Order Runge-Kutta method
        yn = rk.fourthOrder(dn);

        // Output the results
        System.out.println("Fourth order Runge-Kutta procedure");
        System.out.println("The value of y[0] at x = " + xn + " is " + yn[0]);
        System.out.println("The value of y[1] at x = " + xn + " is " + yn[1]);
        System.out.println("The value of y[2] at x = " + xn + " is " + yn[2]);
        System.out.println("Number of iterations = " + rk.getNumberOfIterations());

        // Set values needed by fixed step size method
        rk.setToleranceScalingFactor(1e-5);
        rk.setToleranceAdditionFactor(1e-3);

        // Call Fehlberg method
        yn = rk.fehlberg(dn);

        // Output the results
        System.out.println("\nFehlberg-Runge-Kutta procedure");
        System.out.println("The value of y[0] at x = " + xn + " is " + yn[0]);
        System.out.println("The value of y[1] at x = " + xn + " is " + yn[1]);
        System.out.println("The value of y[2] at x = " + xn + " is " + yn[2]);
        System.out.println("Number of iterations = " + rk.getNumberOfIterations());

        // Call Cash Karp method
        yn = rk.cashKarp(dn);

        // Output the results
        System.out.println("\nCash-Karp-Runge-Kutta procedure");
        System.out.println("The value of y[0] at x = " + xn + " is " + yn[0]);
        System.out.println("The value of y[1] at x = " + xn + " is " + yn[1]);
        System.out.println("The value of y[2] at x = " + xn + " is " + yn[2]);
        System.out.println("Number of iterations = " + rk.getNumberOfIterations());

    }
}

// Class to evaluate the three derivatives for given values of x and the three y[i].
class Derivn1 implements DerivnFunction{

    private double k1 = 0.0D, k2 = 0.0D;

    public double[ ] derivn(double x, double[ ] y){
        double[ ] dydx = new double [y.length];

        dydx[0] = -k1*y[0];
        dydx[1] =  k1*y[0] - k2*y[1];
        dydx[2] =  k2*y[1];

        return dydx;
    }

    public void setKvalues(double k1, double k2){
        this.k1 = k1;
        this.k2 = k2;
    }
}