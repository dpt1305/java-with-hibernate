package aden.dev.site.service;

import java.util.List;

import aden.dev.site.entity.Classroom;
import aden.dev.site.exception.DatabaseOperationException;
import aden.dev.site.exception.NotFoundException;
import aden.dev.site.repository.ClassroomRepository;

public class ClassroomService {
    private final ClassroomRepository repository;

    public ClassroomService() {
        this.repository = new ClassroomRepository();
    }

    public String create(String classroomName) throws DatabaseOperationException {
        return repository.create(classroomName);

    }

    public Classroom getClassroom(String id) throws NotFoundException {
        Classroom classroom = repository.getById(id);
        if (classroom == null) {
            throw new NotFoundException("Not found entity!");
        } else {
            return classroom;
        }
    }

    public List<Classroom> getClassroomByName(String className, int page, int pageSize) {
        String condition = String.format("className = '%s' ", className);
        return repository.getWithConditions(condition, page, pageSize);
    }

    public List<Classroom> getAllClassrooms() {
        return repository.getAll();
    }

    public void deleteById(String id) {
        repository.delete(id);
    }
}
