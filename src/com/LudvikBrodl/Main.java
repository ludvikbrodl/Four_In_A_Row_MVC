package com.LudvikBrodl;

public class Main {

    public static void main(String[] args) {

        Model m = new  Model();
        Controller c = new Controller(m);
        View v = new View(c, m);
        v.open();
    }
}
