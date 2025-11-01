package BiyDaalt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testCalculateGPA() {
        Student st = new Student("S001");

        Subject subj1 = new Subject("CS101", "Programming", 3);
        Subject subj2 = new Subject("CS102", "Database", 3);

        st.addLesson(new Lessons(subj1, 95));
        st.addLesson(new Lessons(subj2, 85));

        st.calculateGPA();

        assertEquals(3.35f, st.GPA, 0.01f, "GPA тооцоолол буруу байна");
    }
}
