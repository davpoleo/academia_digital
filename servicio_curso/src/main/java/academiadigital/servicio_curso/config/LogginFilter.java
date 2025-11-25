package academiadigital.servicio_curso.config;

import academiadigital.servicio_curso.util.ApiConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class LogginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        //1. Codigo para generar el TRACE ID
        //este codigo asume que ApiConstants.TRACE_ID_HEADER es "X-Trace-Id"
        String traceId = request.getHeader(ApiConstants.TRACE_ID_HEADER);
        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString(); //
        }

        try {
            // 2. Se asigna el Thread Local al MDC
            MDC.put(ApiConstants.TRACE_ID_MDC_KEY, traceId); // Asume que ApiConstants.TRACE_ID_MDC_KEY es "traceId"

            // 3. AÑADIR a la respuesta HTTP (para propagación a otros microservicios/cliente)
            response.addHeader(ApiConstants.TRACE_ID_HEADER, traceId);
            filterChain.doFilter(request, response);
        } finally {
            //4. LIMPIAR EL MDC (CRITICO)
            MDC.remove(ApiConstants.TRACE_ID_MDC_KEY);
        }
    }
}
