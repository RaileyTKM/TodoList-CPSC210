package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTag {
    // TODO: design tests for new behaviour added to Tag class

    private Tag tag;

    @BeforeEach
    public void setUp() {
        tag = new Tag("tag1");
    }

    @Test
    public void testConstructor() {
        try {
            assertEquals("tag1", tag.getName());
        } catch (Exception e) {
            fail("EmptyStringException should not be thrown here");
        }
    }

    @Test
    public void testNullConstructor() {
        try {
            String name = "";
            tag = new Tag(name);
            fail("EmptyStringException expected");
        } catch (EmptyStringException e) {
            // expected
        }
        try {
            String name = null;
            tag = new Tag(name);
            fail("EmptyStringException expected");
        } catch (EmptyStringException e) {
            // expected
        }
    }

    @Test
    public void testTwoTagsEqual() {
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        assertTrue(tag1.equals(tag1));
        assertTrue(tag.equals(tag1));
        assertFalse(tag.equals(new Task("what")));
        assertFalse(tag.equals(tag2));
    }

    @Test
    public void testAddTaskExceptionExpected() {
        Task task = null;
        try {
            tag.addTask(task);
            fail("NullArgumentException expected");
        } catch (NullArgumentException e) {
            //expected
        }
    }

    @Test
    public void testAddTaskNoException() {
        Task task = new Task("a new task");
        try {
            tag.addTask(task);
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        assertTrue(tag.containsTask(task));
        assertTrue(task.containsTag(tag));
    }

    @Test
    public void testAddManyTasks() {
        Task task1 = new Task("a new task1");
        Task task2 = new Task("a new task2");
        Task task3 = new Task("a new task3");
        try {
            tag.addTask(task1);
            tag.addTask(task2);
            tag.addTask(task3);
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        assertTrue(tag.containsTask(new Task("a new task1")));
        assertTrue(tag.containsTask(task2));
        assertTrue(tag.containsTask(task3));
        assertTrue(task1.containsTag(tag));
        assertTrue(task2.containsTag(tag));
        assertTrue(task3.containsTag(tag));
    }

    @Test
    public void testAddRepeatedTasks() {
        Task task1 = new Task("a new task1");
        Task task2 = new Task("a new task2");
        try {
            tag.addTask(task1);
            tag.addTask(task2);
            tag.addTask(task1);
            tag.addTask(task2);
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        assertEquals(2, tag.getTasks().size());
        assertTrue(tag.containsTask(task1));
        assertTrue(tag.containsTask(new Task("a new task2")));
        assertTrue(task1.containsTag(tag));
        assertTrue(task2.containsTag(tag));
    }

    @Test
    public void testRemoveTaskExceptionExpected() {
        Task task = null;
        try {
            tag.removeTask(task);
            fail("NullArgumentException expected");
        } catch (NullArgumentException e) {
            //expected
        }
    }

    @Test
    public void testRemoveTaskNoException() {
        Task task = new Task("a new task");
        tag.addTask(task);
        assertTrue(tag.containsTask(task));
        assertTrue(task.containsTag(tag));
        try {
            tag.removeTask(task);
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        assertFalse(tag.containsTask(task));
        assertFalse(task.containsTag(tag));
    }

    @Test
    public void testRemoveManyTasks() {
        Task task1 = new Task("a new task1");
        Task task2 = new Task("a new task2");
        Task task3 = new Task("a new task3");
        tag.addTask(task1);
        tag.addTask(task2);
        tag.addTask(task3);
        assertTrue(task1.containsTag(tag));
        assertTrue(task2.containsTag(tag));
        assertTrue(task3.containsTag(tag));
        try {
            tag.removeTask(task1);
            tag.removeTask(task2);
            tag.removeTask(task3);
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        assertEquals(0, tag.getTasks().size());
        assertFalse(tag.containsTask(task1));
        assertFalse(tag.containsTask(task2));
        assertFalse(tag.containsTask(task3));
        assertFalse(task1.containsTag(tag));
        assertFalse(task2.containsTag(tag));
        assertFalse(task3.containsTag(tag));
    }

    @Test
    public void testRemoveNonExistingTask() {
        Task task = new Task("a new task");
        tag.addTask(task);
        try {
            tag.removeTask(new Task("another task"));
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be thrown here");
        }
        assertEquals(1, tag.getTasks().size());
        assertTrue(tag.containsTask(task));
        assertTrue(task.containsTag(tag));
    }

    @Test
    public void testToString() {
        assertEquals("#tag1", tag.toString());
    }
}
