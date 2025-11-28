package academiadigital.servicio_reporte.util;

public class ApiConstants {
    private ApiConstants(){
    }

    //FEING CLIENTES
    public static final String FEING_ESTUDIANTES_NAME = "servicio-estudiantes";
    public static final String FEING_ESTUDIANTES_URL = "http://localhost:8081";
    public static final String FEING_ESTUDIANTES_PATH = "/api/v1/students";

    public static final String FEING_CURSOS_NAME = "servicio-cursos";
    public static final String FEING_CURSOS_URL = "http://localhost:8082";
    public static final String FEING_CURSOS_PATH = "/api/v1/courses";
    public static final String FEING_CURSOS_MAPPING_COURSE_ID = "/{courseId}";
    public static final String FEING_CURSOS_MAPPING_STUDENTS_ID_BY_COURSE = "/api/v1/courses/{courseId}/students";

    public static final String FEING_PROGRESO_NAME = "servicio-progreso";
    public static final String FEING_PROGRESO_URL = "http://localhost:8083";
    public static final String FEING_PROGRESO_PATH = "/api/v1/progress";
    public static final String FEING_PROGRESO_MAPPING_RAW_EVENTS_BY_COURSE = "/course/{courseId}/events";

    //EXCEPTIONS
    public static final String EXCEPTION_COURSE_ID_NO_EXIST = "El ID del curso no existe";
}
