package org.developerbhuwan.test.maven.junit.jupiter;

import io.takari.maven.testing.executor.MavenRuntime;
import lombok.Getter;
import lombok.NonNull;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@Getter
public class MavenRuntimeTestContextManager {

    private static final File MAVEN_HOME;

    static {
        MAVEN_HOME = new File("/home/developerbhuwan/.sdkman/candidates/maven/current");
    }

    private final MavenRuntime runtime;

    private MavenRuntimeTestContextManager(@NonNull MavenRuntime runtime) {
        this.runtime = runtime;
    }

    public static MavenRuntimeTestContextManager create(ExtensionContext context) {
        Class<?> testClass = context.getRequiredTestClass();
        MojoJUnitConfig config = testClass.getAnnotation(MojoJUnitConfig.class);
        MavenRuntime runtime = MavenRuntime.builder(MAVEN_HOME, null).forkedBuilder().build();
        runtime.forProject(new File(config.project()));
        return new MavenRuntimeTestContextManager(runtime);
    }

    public ExecuteResult execute(String goal) {
        return null;
    }

    public void afterTestClass() {
    }

    public void afterTestMethod(Object testInstance, Method testMethod, Throwable testException) {

    }
}
