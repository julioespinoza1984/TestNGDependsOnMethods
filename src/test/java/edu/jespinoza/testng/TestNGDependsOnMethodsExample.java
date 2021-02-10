package edu.jespinoza.testng;

import edu.jespinoza.testng.impl.CalculatorImpl;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNGDependsOnMethodsExample {
    private static final Logger logger = Logger.getLogger(TestNGDependsOnMethodsExample.class);

    @BeforeMethod
    public void beforeMethod() {
        logger.info("En beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        logger.info("En afterMethod");
    }

    @Test
    public void testAdd() {
        logger.info("Iniciando testAdd");
        long start = System.currentTimeMillis();
        Calculator calculator = CalculatorImpl.getInstance();
        Assert.assertEquals(calculator.add(15, 2) , 17);
        logger.info("testAdd - Ejecutado en " + (System.currentTimeMillis() - start)
            + " milisegundos");
    }

    @Test
    public void testDivide() {
        logger.info("Iniciando testDivide");
        long start = System.currentTimeMillis();
        Calculator calculator = CalculatorImpl.getInstance();
        Assert.assertEquals(calculator.divide(16, 0), 16);
        logger.info("testDivide - Ejecutado en " + (System.currentTimeMillis() - start)
                + " milisegundos");
    }

    @Test(dependsOnMethods={"testAdd", "testDivide"})
    public void testProcessRealNumbers() {
        logger.info("En testProcessRealNumbers");
    }

    @Test(dependsOnMethods={"testAdd", "testDivide"}, alwaysRun=true)
    public void testProcessEvenNumbers() {
        logger.info("En testProcessEvenNumbers()");
    }
}
