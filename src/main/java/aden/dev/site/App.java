package aden.dev.site;

import java.util.List;
import java.util.logging.Logger;

import aden.dev.site.entity.Classroom;
import aden.dev.site.service.ClassroomService;

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
        ClassroomService service = new ClassroomService();
        List<Classroom> classList = service.getClassroomByName("abc", 1, 5);

        for (Classroom classroom : classList) {
            logger.info(classroom.getClassName());
        }
    }
}
