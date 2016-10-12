/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc;

/**
 *
 * @author mzijlstra
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Test t = new Test("Hello");
        t.doThings(); // the function doThings(t) happens
    }
    
}
