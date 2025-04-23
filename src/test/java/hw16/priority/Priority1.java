package hw16.priority;

/*
Create a separate package priority. Inside it, create the class Priority1.
In this class, create tests named a, b, c, d, e, f, g.
Make sure that when running this class, the tests execute in reverse alphabetical order.
Come up with at least two ways to achieve this.
 */

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Priority1 {
    @Test(priority = 7)
    public void a() {
        assertTrue(true);
    }

    @Test(priority = 6)
    public void b() {
        assertTrue(true);
    }

    @Test(priority = 5)
    public void c() {
        assertTrue(true);
    }

    @Test(priority = 4)
    public void d() {
        assertTrue(true);
    }

    @Test(priority = 3)
    public void e() {
        assertTrue(true);
    }

    @Test(priority = 2)
    public void f() {
        assertTrue(true);
    }

    @Test(priority = 1)
    public void g() {
        assertTrue(true);
    }
}