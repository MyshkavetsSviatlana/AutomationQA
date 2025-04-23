package hw16.priority;

/*
Create a separate package priority. Inside it, create the class Priority1.
In this class, create tests named a, b, c, d, e, f, g.
Make sure that when running this class, the tests execute in reverse alphabetical order.
Come up with at least two ways to achieve this.
 */

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Priority2 {
    @Test(dependsOnMethods = "b")
    public void a() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "c")
    public void b() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "d")
    public void c() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "e")
    public void d() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "f")
    public void e() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "g")
    public void f() {
        assertTrue(true);
    }

    @Test(priority = 1)
    public void g() {
        assertTrue(true);
    }
}