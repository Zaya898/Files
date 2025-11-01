package BiyDaalt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class RegistrationTest {

    Registration reg;

    @BeforeEach
    public void setUp() {
        reg = new Registration();
        reg.subjectList.add(new Subject("CS101", "Programming", 3));
        reg.subjectList.add(new Subject("CS102", "Database", 3));

        reg.majorList.add(new Major("1910", "Computer Science"));

        Student st1 = new Student("B241910026");
        st1.addLesson(new Lessons(reg.findSubject("CS101"), 95));
        st1.addLesson(new Lessons(reg.findSubject("CS102"), 85));
        reg.studentList.add(st1);

        Student st2 = new Student("B241910025");
        st2.addLesson(new Lessons(reg.findSubject("CS101"), 55));
        reg.studentList.add(st2);
    }

    @Test
    public void testFindSubject() {
        Subject subj = reg.findSubject("CS101");
        assertNotNull(subj);
        assertEquals("Programming", subj.name);
    }

    @Test
    public void testFindStudent() {
        Student st = reg.findStudent("B241910026");
        assertNotNull(st);
        assertEquals(2, st.lessons.size());
    }

    @Test
    public void testCalculateGPA() {
        Student st = reg.findStudent("B241910026");
        st.calculateGPA();
        assertEquals(3.35f, st.GPA, 0.01f);
    }

    @Test
    public void testFailedStudents() {
        ArrayList<String> failed = new ArrayList<>();
        for (Student s : reg.studentList) {
            for (Lessons l : s.lessons) {
                if (l.score < 60) {
                    failed.add(s.code);
                }
            }
        }
        assertTrue(failed.contains("B241910025"));
        assertFalse(failed.contains("B241910026"));
    }

    @Test
    public void testAverageGPA() {
        for (Student s : reg.studentList) s.calculateGPA();
        float avg = Student.calculateAverageGPA(reg.studentList);
        // st1 = 3.35, st2 = 0.0 → дундаж = 1.675
        assertEquals(1.675f, avg, 0.01f);
    }
}
