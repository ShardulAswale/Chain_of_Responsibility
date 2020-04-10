package chain_of_responsibility;

import java.util.Scanner;

interface Chain {

    public abstract void setNext(Chain nextInChain);

    public abstract void process(Loan request);

}

class Loan {

    private final double number;

    public Loan(double number) {
        this.number = number;
    }

    public double getLoan() {
        return number;
    }

}

class Clerk implements Chain {

    private Chain nextInChain;
    private final double max;

    Clerk(double max) {
        this.max = max;
    }

    @Override
    public void setNext(Chain c) {
        this.nextInChain = c;
    }

    @Override
    public void process(Loan request) {
        if (request.getLoan() <= this.max) {
            System.out.println(this.max + "Your loan is approved by Clerk for amount Rs." + request.getLoan());
        } 
        else {
            nextInChain.process(request);
        }
    }
}

class Manager implements Chain {

    private Chain nextInChain;
    private final double max;

    Manager(double max) {
        this.max = max;
    }

    @Override
    public void setNext(Chain c) {
        this.nextInChain = c;
    }

    @Override
    public void process(Loan request) {
        if (request.getLoan() <= this.max) {
            System.out.println(this.max + "Your loan is approved by Manager for amount Rs." + request.getLoan());
        } else {
            nextInChain.process(request);
        }
    }
}

class Director implements Chain {

    private Chain nextInChain;
    private final double max;

    Director(double max) {
        this.max = max;
    }

    @Override
    public void setNext(Chain c) {
        this.nextInChain = c;
    }

    @Override
    public void process(Loan request) {
        if (request.getLoan() <= this.max) {
            System.out.println(this.max + "The loan is approved by Director for amount Rs." + request.getLoan());
        } else {
            nextInChain.process(request);
        }
    }
}

class Vice_President implements Chain {

    private Chain nextInChain;

    @Override
    public void setNext(Chain c) {
        this.nextInChain = c;
    }

    @Override
    public void process(Loan request) {
        System.out.println("The loan is approved by Vice President for amount Rs." + request.getLoan());
    }
}

class Demo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("enter the limit for clerk");
        Chain c1 = new Clerk(s.nextDouble());
        System.out.println("Do you want to add manager to the chain");
        if (s.nextBoolean() == true) {
            System.out.println("enter the limit for Manager");
            Chain c2 = new Manager(s.nextDouble());
            c1.setNext(c2);
            System.out.println("Do you want to add director to the chain");
            if (s.nextBoolean() == true) {
                System.out.println("enter the limit for Director");
                Chain c3 = new Director(s.nextDouble());
                c2.setNext(c3);
                Chain c4 = new Vice_President();
                c3.setNext(c4);
            } else {
                Chain c4 = new Vice_President();
                c2.setNext(c4);
            }
        } else {
            System.out.println("Do you want to add director to the chain");
            if (s.nextBoolean() == true) {
                System.out.println("enter the limit for Director");
                Chain c3 = new Director(s.nextDouble());
                c1.setNext(c3);
                Chain c4 = new Vice_President();
                c3.setNext(c4);
            } else {
                Chain c4 = new Vice_President();
                c1.setNext(c4);
            }
        }
        while (true) {
            System.out.println("Do you want to take loan enter 1 is yes 2 is no");
            if (s.nextInt() == 2) {
                break;
            } else {
                System.out.println("Enter the loan amount ");
                c1.process(new Loan(s.nextDouble()));
            }
        }

        System.out.println("Thank you for choosing us");

    }
}
