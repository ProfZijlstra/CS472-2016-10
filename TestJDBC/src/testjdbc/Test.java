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
public class Test {
    private String something;

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }

    public Test(String something) {
        this.something = something;
    }
    
    // really the function:  doThings(Test this)
    public void doThings() {
        System.out.println(this.something + " was here!");
    }
    
    
}


