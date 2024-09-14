package opentelemetry.implement;

import java.util.HashMap;
import java.util.Map;

import io.opentelemetry.javaagent.shaded.io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.javaagent.shaded.io.opentelemetry.api.trace.Span;
import io.opentelemetry.javaagent.shaded.io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.javaagent.shaded.io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.javaagent.shaded.io.opentelemetry.context.Context;
import io.opentelemetry.javaagent.shaded.io.opentelemetry.context.Scope;

public class SpanManager {
    private static final Tracer tracer = GlobalOpenTelemetry.getTracer("customTracer");

    private static final Map<String, Span> spanMap = new HashMap<>();
    private static final Map<String, Scope> scopeMap = new HashMap<>();

    public static String createSpan(String name, String parentSpanId) {
        Span span;
        Scope scope;
        if (parentSpanId != null && spanMap.containsKey(parentSpanId)) {
            Span parentSpan = spanMap.get(parentSpanId);
            span = tracer.spanBuilder(name)
                    .setParent(Context.current().with(parentSpan))
                    .startSpan();
        } else {
            span = tracer.spanBuilder(name).setParent(Context.current()).startSpan();
        }

        String spanId = span.getSpanContext().getSpanId();
        spanMap.put(spanId, span);
        scopeMap.put(spanId, span.makeCurrent());
        return spanId;
    }

    public static void addEvent(String spanId, String event) {
        Span span = spanMap.get(spanId);
        if (span != null) {
            span.addEvent(event);
        } else {
            System.err.println("Span not found for ID: " + spanId);
        }
    }

    public static void endSpan(String spanId, StatusCode status) {
        Span span = spanMap.get(spanId);
        Scope scope = scopeMap.get(spanId);
        if(scope != null) {
            scope.close();
            scopeMap.remove(spanId);
        }
        if (span != null) {
            span.setStatus(status);
            span.end();
            // 结束后从 Map 中移除该 Span
            spanMap.remove(spanId);
        } else {
            System.err.println("Span not found for ID: " + spanId);
        }
    }

    public static void recordException(String spanId, String exception) {
        Span span = spanMap.get(spanId);
        if (span != null) {
            span.recordException(new Exception(exception));
        } else {
            System.err.println("Span not found for ID: " + spanId);
        }
    }

    public static void setAttribute(String spanId, String attributeName, String attributeValue) {
        Span span = spanMap.get(spanId);
        if (span != null) {
            span.setAttribute(attributeName, attributeValue);
        } else {
            System.err.println("Span not found for ID: " + spanId);
        }
    }
}
