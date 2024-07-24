package aden.dev.site;

import java.util.List;
import java.util.logging.Logger;

import aden.dev.site.entity.Classroom;
import aden.dev.site.entity.Student;
import aden.dev.site.repository.StudentRepository;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        logger.info("Hello World!");
        StudentRepository repo = new StudentRepository();

        // var classRepo = new ClassroomRepository();
        String classId = repo.create(new Classroom("class5"));
        Classroom clr = (Classroom) repo.getById(Classroom.class, classId);

        Student std = new Student(
                "tung5");
        std.setClassroom(clr);
        // String uuid = UUID.randomUUID().toString();
        // std.setId();

        repo.create(std);

        logger.info("done create");

        @SuppressWarnings("unchecked")
        List<Classroom> classroomList = (List<Classroom>) repo.getWithConditions("from Classroom ", Classroom.class, 1,
                10);
        for (Classroom classroom : classroomList) {
            String msg = classroom.toString();
            logger.warning(msg);
        }

    }
}
