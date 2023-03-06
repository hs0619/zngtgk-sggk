<%@page import="com.baosight.iplat4j.core.monitor.DiagnosticJob" %>
<%@page import="com.baosight.iplat4j.core.monitor.DiagnosticJobs" %>
<%@page import="com.baosight.iplat4j.core.monitor.Diagnostics" %>
<%@page import="com.baosight.iplat4j.core.util.ExceptionUtil" %>
<%@page import="java.lang.reflect.Field"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
ThreadGroup tg = Thread.currentThread().getThreadGroup();
while (tg.getParent() != null)
    tg = tg.getParent();

// Note that ThreadGroup.enumerate() silently ignores all threads that
// does not fit into array
int guessThreadCount = tg.activeCount() + 50;
Thread[] threads = new Thread[guessThreadCount];
int actualThreadCount = tg.enumerate(threads);
while (actualThreadCount == guessThreadCount) { // Map was filled, there
    // may be more
    guessThreadCount *= 2;
    threads = new Thread[guessThreadCount];
    actualThreadCount = tg.enumerate(threads);
}

    for (Thread t : threads) {
        if (t != null) {
            out.write("<hr>");
            DiagnosticJobs jobs = null;
            try {
                Field diagnosticsField = Diagnostics.class.getDeclaredField("diagnostics");
                diagnosticsField.setAccessible(true);
                Object diagnostics = diagnosticsField.get(null);

                Method getMap = ThreadLocal.class.getDeclaredMethod("getMap", Thread.class);
                getMap.setAccessible(true);
                Object threadLocalMap = getMap.invoke(diagnostics, t);

                if (threadLocalMap != null) {
                    try {
                        Method get = Class.forName("java.lang.ThreadLocal$ThreadLocalMap")
                                .getDeclaredMethod("get", ThreadLocal.class);
                        get.setAccessible(true);
                        jobs =  (DiagnosticJobs) get.invoke(threadLocalMap, diagnostics);
                    } catch (Exception e) {
                        Method getEntry = Class.forName("java.lang.ThreadLocal$ThreadLocalMap")
                                .getDeclaredMethod("getEntry", ThreadLocal.class);
                        getEntry.setAccessible(true);
                        Object entry = getEntry.invoke(threadLocalMap, diagnostics);
                        if (entry != null) {
                            Field value = Class.forName("java.lang.ThreadLocal$ThreadLocalMap$Entry")
                                    .getDeclaredField("value");
                            value.setAccessible(true);
                            jobs = (DiagnosticJobs) value.get(entry);
                        }
                    }
                }
            } catch (Exception e) {
                out.write("ERROR!!!: " + e.getMessage() + "<p>");
            }
            if (jobs != null) {
                String errorMsg = "";
                List<DiagnosticJob> copyJobs = new ArrayList<DiagnosticJob>(jobs.jobs.size());
                try {
                    for (DiagnosticJob job : jobs.jobs) {
                        copyJobs.add(new DiagnosticJob(job));
                    }
                } catch (Exception e) {
                    errorMsg = ExceptionUtil.getRootCauseMessage(e);
                    out.write("ERROR!!>>>>" + errorMsg + "<p>");
                }
                if (!copyJobs.isEmpty()) {
                    Map info = copyJobs.get(0).toMap();
                    String threadName = t.getName()
                            + (Thread.currentThread() == t ? " [current]" : "") + " ["
                            + errorMsg + "]";
                    out.write(threadName+", startTime: "+copyJobs.get(copyJobs.size() - 1).toMap().get("time")+ "<p>");

                    for (DiagnosticJob job : copyJobs) {
                        out.write(job.toString()+ "<p>");
                    }
                    for (StackTraceElement ste : t.getStackTrace()) {
                        out.write(ste.toString()+ "<p>");
                    }
                }
            } else {
                String threadName = t.getName()
                        + (Thread.currentThread() == t ? " [current]" : "");
                out.write(threadName+ "<p>");
                int i= 0;
                for (StackTraceElement ste : t.getStackTrace()) {
                    out.write(ste.toString()+ "<p>");
                    i++;
                    if (i>5) {
                        break;
                    }
                }

            }
        } } %>

</body>
</html>