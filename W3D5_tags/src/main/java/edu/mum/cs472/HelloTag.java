/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs472;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author mzijlstra
 */
public class HelloTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        StringWriter sw = new StringWriter();
        sw.append("Hello ");
        getJspBody().invoke(sw); // Writer, or if null print to response
        getJspContext().getOut().println(sw.toString());
    }
}
