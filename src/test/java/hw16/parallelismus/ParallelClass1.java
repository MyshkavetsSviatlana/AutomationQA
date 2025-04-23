package hw16.parallelismus;

/*
Create a separate package parallelismus. Inside it, create the class ParallelClass1 and add the tests parallel1,
parallel2, parallel3, parallel4, parallel5.
Create a class ParallelClass2 and add the tests parallel6, parallel7, parallel8, parallel9, parallel10.
Create a separate XML file testngParallelHome.xml, in which the above two classes (ParallelClass1 and ParallelClass2)
will be executed in parallel.
 */

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ParallelClass1 {
    @Test
    public void parallel1() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel2() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel3() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel4() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel5() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }
}