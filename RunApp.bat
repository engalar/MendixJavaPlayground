@echo off
set M2EE_ADMIN_PASS=1
set M2EE_ADMIN_PORT=8091
set M2EE_CONSOLE_PATH=%ProgramFiles%/Mendix/10.12.1.39914/modeler/MendixConsoleLog.exe
set MX_INSTALL_PATH=%ProgramFiles%/Mendix/10.12.1.39914
set MXCONSOLE_BASE_PATH=D:/Users/eg/Documents/GitHub/MendixJavaPlayground/deployment
set MXCONSOLE_RUNTIME_PATH=%ProgramFiles%/Mendix/10.12.1.39914/runtime
set MXCONSOLE_RUNTIME_PORT=8081
set MXCONSOLE_RUNTIME_LISTEN_ADDRESSES="*"
set MXCONSOLE_SERVER_URL="http://127.0.0.1:8091/"

set CLASSPATH="%ProgramFiles%/Mendix/10.12.1.39914/runtime/launcher/runtimelauncher.jar"
set VM_ARGUMENTS=-DMX_LOG_LEVEL=INFO ^
    -Djava.library.path="%ProgramFiles%/Mendix/10.12.1.39914/runtime/lib/x64;D:/Users/eg/Documents/GitHub/MendixJavaPlayground/deployment/model/lib/userlib" ^
    -Dfile.encoding=UTF-8 ^
    -Djava.io.tmpdir="D:/Users/eg/Documents/GitHub/MendixJavaPlayground/deployment/data/tmp" ^
    -javaagent:"D:/Users/eg/Documents/GitHub/MendixJavaPlayground/userlib/opentelemetry-javaagent.jar" ^
    -javaagent:"D:/Users/eg/Documents/GitHub/MendixJavaPlayground/userlib/agent.jar" ^
    -Dotel.service.name=my_mendix_app ^
    -Dotel.exporter=jaeger ^
    -Dotel.exporter.jaeger.endpoint=http://localhost:14268/api/traces ^
    -Dotel.traces.sampler=always_on

set PROGRAM_ARGUMENTS="D:/Users/eg/Documents/GitHub/MendixJavaPlayground/deployment"

"%ProgramFiles%/Java/jdk-17.0.2/bin/java.exe" %VM_ARGUMENTS% -cp %CLASSPATH% com.mendix.container.boot.Main %PROGRAM_ARGUMENTS%

pause

