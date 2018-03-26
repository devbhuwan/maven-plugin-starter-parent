package org.developerbhuwan.maven.test.junit.jupiter;

import io.takari.maven.testing.executor.MavenExecution;
import io.takari.maven.testing.executor.MavenRuntime;
import lombok.Getter;
import lombok.NonNull;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Optional;

import static java.util.List.of;
import static org.apache.commons.lang3.reflect.FieldUtils.getFieldsListWithAnnotation;
import static org.apache.commons.lang3.reflect.FieldUtils.writeField;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@Getter
class MavenRuntimeTestContextManager {

    private static final File MAVEN_HOME;

    static {
        MAVEN_HOME = new File("/home/developerbhuwan/.sdkman/candidates/maven/current");
    }

    private final Mojo mojo;

    private MavenRuntimeTestContextManager(@NonNull MavenExecution execution) {
        this.mojo = new Mojo(execution);
    }

    static MavenRuntimeTestContextManager create(Class<?> testInstanceClass) {
        MavenRuntime runtime = MavenRuntime.builder(MAVEN_HOME, null).forkedBuilder().build();
        MojoJUnitConfig config = testInstanceClass.getDeclaredAnnotation(MojoJUnitConfig.class);
        return new MavenRuntimeTestContextManager(runtime.forProject(new File(config.project())));
    }

    void initializeMojoContexts(Object testInstance) {
        Optional.ofNullable(getFieldsListWithAnnotation(testInstance.getClass(), MojoContext.class))
                .orElse(of())
                .forEach(field -> {
                    try {
                        writeField(field, testInstance, getMojo(), true);
                    } catch (IllegalAccessException e) {
                        throw new IllegalCallerException(e);
                    }
                });
    }

    void afterTestClass() {
    }

    void afterTestMethod(Object testInstance, Method testMethod, Throwable testException) {

    }
}
