package academiadigital.servicio_estudiante.util;

//Aqui se crearan las constantes de la aplicacion para evitar lo mas posible el uso de Magic-Strings
public class ApiConstants {
    private ApiConstants(){
    }

    //ERROR VALIDATION MESSAGES
    public static final String VALIDATE_ERR_GENERAL = "Error de Validacion";
    public static final String VALIDATE_ERR_BLANKS_MESSAGE = "El campo no puede estar vacio";
    public static final String VALIDATE_ERR_EMAIL_MESSAGE = "Se debe ingresar un email valido";
    public static final String VALIDATE_ERR_EMAIL_NOT_FOUND = "El email no se encuenta registrado en el sistema";
    public static final String VALIDATE_ERR_DUPLICATED_RESOURCE = "Conflicto de datos";
    public static final String VALIDATE_ERR_SERVER_ERROR = "Error interno del servidor";
    public static final String VALIDATE_ERR_UNEXPECTED = "Ocurrio un error inesperado";

    //BUSINESS ERROR MESSAGE
    public static final String BUSINESS_ERR_ID_NOT_FOUND = "El ID no esta registrado en el sistema";
    public static final String BUSINESS_ERR_EMAIL_NOT_FOUND = "El email no esta registrado en el sistema";
    public static final String BUSINESS_ERR_DUPLICATED_EMAIL = "El email ya esta en uso, ingrese uno nuevo.";

    //CONSTANTES DEL CONTROLLER
    public static final String CONTROLLER_MAP_BASE = "/api/v1/students";
    public static final String CONTROLLER_MAP_CREATE = "/create";
    public static final String CONTROLLER_MAP_GET_BY_ID = "/{id}";
    public static final String CONTROLLER_MAP_GET_BY_EMAIL = "email/{email}";
    public static final String CONTROLLER_MAP_GET_ALL = "/all";
    public static final String CONTROLLER_MAP_UPDATE = "/update/{id}";
    public static final String CONTROLLER_MAP_DELETE = "/delete/{id}";

    //APP TRACEABILITY
    public static final String TRACE_ID_HEADER = "X-Trace-Id";
    public static final String TRACE_ID_MDC_KEY = "traceId";
}
