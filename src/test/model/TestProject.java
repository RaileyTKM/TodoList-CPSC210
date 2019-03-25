package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.Status.DONE;
import static model.Status.TODO;
import static org.junit.jupiter.api.Assertions.*;

public class TestProject {

    private Project project;

    @BeforeEach
    public void setUp() {
        project = new Project("pro");
    }

    @Test
    public void testConstructor() {
        try {
            assertEquals("pro", project.getDescription());
            assertEquals(0, project.getNumberOfTasks());
            assertEquals(0, project.getEstimatedTimeToComplete());
            assertEquals(0, project.getProgress());
        } catch (Exception e) {
            fail("EmptyStringException should not be thrown here");
        }
    }

    @Test
    public void testEmptyConstructor() {
        try {
            project = new Project("");
            fail("EmptyStringException expected");
        } catch (EmptyStringException e) {
            // expected
        }
        try {
            String d = null;
            project = new Project(d);
            fail("EmptyStringException expected");
        } catch (EmptyStringException e) {
            // expected
        }
    }

    @Test
    public void testAdd() {
        Task t1 = new Task("t1");
        Task t2 = new Task("t2");
        try {
            project.add(t1);
            project.add(t2);
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        assertEquals(2,project.getNumberOfTasks());
        try {
            assertTrue(project.contains(t1));
            assertTrue(project.contains(t2));
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        try {
            project.add(t1);
            project.add(t2);
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        assertEquals(2,project.getNumberOfTasks());
        try {
            assertTrue(project.contains(t1));
            assertTrue(project.contains(t2));
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
    }

    @Test
    public void testAddNull() {
        Task t0 = null;
        try {
            project.add(t0);
            fail("NullArgumentException expected");
        } catch (NullArgumentException e) {
            // expected
        }
    }

    @Test
    public void testContainsNull() {
        Task t0 = null;
        try {
            project.contains(t0);
            fail("NullArgumentException expected");
        } catch (NullArgumentException e) {
            // expected
        }
    }

    @Test
    public void testRemove() {
        Task t1 = new Task("t1");
        Task t2 = new Task("t2");
        try {
            project.add(t1);
            project.add(t2);
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        try {
            project.remove(t1);
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        assertEquals(1, project.getNumberOfTasks());
        try {
            assertFalse(project.contains(t1));
            assertTrue(project.contains(t2));
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        project.remove(t1);
        assertEquals(1, project.getNumberOfTasks());
        try {
            assertFalse(project.contains(t1));
            assertTrue(project.contains(t2));
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
    }

    @Test
    public void testRemoveNull() {
        Task t0 = null;
        try {
            project.remove(t0);
            fail("NullArgumentException expected");
        } catch (NullArgumentException e) {
            // expected
        }
    }

    @Test
    public void testGet0Progress() {
        assertEquals(100, project.getProgress());
        Task t1 = new Task("t1");
        Task t2 = new Task("t2");
        project.add(t1);
        project.add(t2);
        assertEquals(0, project.getProgress());
    }

    @Test
    public void testGetSomeProgress() {
        assertEquals(100, project.getProgress());
        Task t1 = new Task("t1");
        Task t2 = new Task("t2");
        assertEquals(TODO, t1.getStatus());
        t1.setStatus(DONE);
        project.add(t1);
        project.add(t2);
        assertEquals(50, project.getProgress());
    }

    @Test
    public void testGet100Progress() {
        Task t1 = new Task("t1");
        Task t2 = new Task("t2");
        t1.setStatus(Status.DONE);
        t2.setStatus(Status.DONE);
        project.add(t1);
        project.add(t2);
        assertEquals(100, project.getProgress());
    }

    @Test
    public void testNoTaskIsComplete() {
        assertFalse(project.isCompleted());
    }

    @Test
    public void testPartiallyCompleted() {
        assertFalse(project.isCompleted());
        Task t1 = new Task("t1");
        Task t2 = new Task("t2");
        t1.setStatus(Status.DONE);
        project.add(t1);
        project.add(t2);
        assertFalse(project.isCompleted());
    }

    @Test
    public void testCompleted() {
        assertFalse(project.isCompleted());
        Task t1 = new Task("t1");
        Task t2 = new Task("t2");
        t1.setStatus(Status.DONE);
        t2.setStatus(Status.DONE);
        project.add(t1);
        project.add(t2);
        assertTrue(project.isCompleted());
    }

    @Test
    public void testGetTasks() {
        assertEquals(0, project.getNumberOfTasks());
        Task t1 = new Task("t1");
        Task t2 = new Task("t2");
        project.add(t1);
        project.add(t2);
        assertTrue(project.contains(t1));
        assertTrue(project.contains(t2));
        List<Task> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        assertEquals(2, project.getNumberOfTasks());
//        try {
//            project.getTasks();
//            fail("UnsupportedOperationException expected");
//        } catch (Exception e) {
//            // expected
//        }
    }

    @Test
    public void testEquals() {
        assertTrue(project.equals(new Project("pro")));
        assertTrue(project.equals(project));
        assertFalse(project.equals(new Task("what")));
        project.hashCode();
    }


}