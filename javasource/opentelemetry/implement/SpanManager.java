package opentelemetry.implement;

import java.util.HashMap;
import java.util.Map;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;


public class SpanManager {
    private static final Tracer tracer = GlobalOpenTelemetry.getTracer("customTracer");

    // 存储所有 span，键是 spanId，值是对应的 Span 对象
    private static final Map<String, Span> spanMap = new HashMap<>();

    /**
     * 创建一个新的 Span，使用 parentSpanId 作为父 Span（如果存在）。
     */
    public static String createSpan(String name, String parentSpanId) {
        Span span;
        if (parentSpanId != null && spanMap.containsKey(parentSpanId)) {
            // 如果有父 Span，使用父 Span 创建子 Span
            Span parentSpan = spanMap.get(parentSpanId);
            span = tracer.spanBuilder(name)
                    .setParent(Context.current().with(parentSpan))
                    .startSpan();
        } else {
            // 如果没有父 Span，直接创建根 Span
            span = tracer.spanBuilder(name).startSpan();
        }
        
        String spanId = span.getSpanContext().getSpanId();
        spanMap.put(spanId, span);
        return spanId;
    }

    /**
     * 为指定的 Span 添加事件。
     */
    public static void addEvent(String spanId, String event) {
        Span span = spanMap.get(spanId);
        if (span != null) {
            span.addEvent(event);
        } else {
            System.err.println("Span not found for ID: " + spanId);
        }
    }

    /**
     * 结束指定的 Span 并设置状态。
     */
    public static void endSpan(String spanId, StatusCode status) {
        Span span = spanMap.get(spanId);
        if (span != null) {
            span.setStatus(status);
            span.end();
            // 结束后从 Map 中移除该 Span
            spanMap.remove(spanId);
        } else {
            System.err.println("Span not found for ID: " + spanId);
        }
    }

    /**
     * 为指定的 Span 记录异常。
     */
    public static void recordException(String spanId, String exception) {
        Span span = spanMap.get(spanId);
        if (span != null) {
            span.recordException(new Exception(exception));
        } else {
            System.err.println("Span not found for ID: " + spanId);
        }
    }

    /**
     * 为指定的 Span 设置一个属性。
     */
    public static void setAttribute(String spanId, String attributeName, String attributeValue) {
        Span span = spanMap.get(spanId);
        if (span != null) {
            span.setAttribute(attributeName, attributeValue);
        } else {
            System.err.println("Span not found for ID: " + spanId);
        }
    }
}
