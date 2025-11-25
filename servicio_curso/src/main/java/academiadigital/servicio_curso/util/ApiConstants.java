package academiadigital.servicio_curso.util;

public class ApiConstants {
    private ApiConstants(){
    }
    //ERROR VALIDATION MESSAGES
    public static final String VALIDATE_ERR_GENERAL = "Error de Validacion";
    public static final String VALIDATE_ERR_BLANKS_MESSAGE = "El campo no puede estar vacio";
    public static final String VALIDATE_ERR_UNEXPECTED = "Ocurrio un error inesperado";
    public static final String VALIDATE_ERR_DATA_INTEGRITY = "No se puede eliminar el recurso, tiene datos asociados";

    //BUSINESS ERROR MESSAGE
    public static final String BUSINESS_ERR_ID_NOT_FOUND = "El ID no esta registrado en el sistema";
    public static final String BUSINESS_ERR_TITLE_NOT_FOUND = "El titulo no esta registrado en el sistema";
    public static final String BUSINESS_ERR_DUPLICATED_TITLE = "El titulo del curso ya esta en uso";

    //STATUS ERROR
    public static final String STATUS_ERR_NOT_FOUD = "Recurso no encontrado";
    public static final String STATUS_ERR_DUPLICATED = "Conflicto de datos";
    public static final String STATUS_ERR_SERVER = "Error interno del servidor";

    //CONTROLLER PATH
    public static final String CONTROLLER_MAP_BASE = "/api/v1/courses";
    public static final String CONTROLLER_MAP_CREATE = "/create";
    public static final String CONTROLLER_MAP_GET_BY_ID = "/{id}";
    public static final String CONTROLLER_MAP_GET_BY_TITLE = "title/{title}";
    public static final String CONTROLLER_MAP_GET_ALL = "/all";
    public static final String CONTROLLER_MAP_UPDATE = "/update/{id}";
    public static final String CONTROLLER_MAP_DELETE = "/delete/{id}";
    public static final String CONTROLLER_MAP_ENROLLMENT = "/enrollment";

    // RUTAS FEING CLIENT
    public static final String FEING_V1_ESTUDIANTES_NAME = "servicio-estudiantes";
    public static final String FEING_V1_ESTUDIANTES_URL = "http://localhost:8081";
    public static final String FEING_V1_ESTUDIANTES_PATH = "/api/v1/estudiantes";

    //APP TRACEABILITY
    public static final String TRACE_ID_HEADER = "X-Trace-Id";
    public static final String TRACE_ID_MDC_KEY = "traceId";
}

