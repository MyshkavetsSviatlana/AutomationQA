package hw16.grouping;

/*
Create a separate package grouping. Inside it, create the class Groups.
In this class, create tests named one, two, three, four, five, six, seven, eight.
Make sure that tests with odd-numbered names belong to the "first" group,
and tests with even-numbered names belong to the "second" group.
Create a separate XML file testngGroupingHome.xml, which will first execute the tests from the "first" group
and then execute the tests from the "second" group.
P.S. Tests in each group should run in ascending order.
 */

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Groups {
    @Test(groups = {"first"}, priority = 1)
    public void one() {
        assertTrue(true);
    }

    @Test(groups = {"second"}, priority = 5)
    public void two() {
        assertTrue(true);
    }

    @Test(groups = {"first"}, priority = 2)
    public void three() {
        assertTrue(true);
    }

    @Test(groups = {"second"}, priority = 6)
    public void four() {
        assertTrue(true);
    }

    @Test(groups = {"first"}, priority = 3)
    public void five() {
        assertTrue(true);
    }

    @Test(groups = {"second"}, priority = 7)
    public void six() {
        assertTrue(true);
    }

    @Test(groups = {"first"}, priority = 4)
    public void seven() {
        assertTrue(true);
    }

    @Test(groups = {"second"}, priority = 8)
    public void eight() {
        assertTrue(true);
    }
}