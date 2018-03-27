package org.developerbhuwan.maven.test.junit.jupiter;

import io.takari.maven.testing.executor.MavenExecution;
import io.takari.maven.testing.executor.MavenRuntime;
import lombok.Getter;
import lombok.NonNull;

import java.io.File;

import static java.lang.System.getenv;
import static java.util.List.of;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.reflect.FieldUtils.getFieldsListWithAnnotation;
import static org.apache.commons.lang3.reflect.FieldUtils.writeField;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@Getter
class MavenRuntimeTestContextManager {

    private final Mojo mojo;

    private MavenRuntimeTestContextManager(@NonNull String project) {
        final File mavenHome = new File(PathDiscovery.discover());
        if (!mavenHome.exists())
            throw new IllegalArgumentException("Please defined MAVEN_HOME environment variable");
        MavenRuntime runtime = MavenRuntime.builder(mavenHome, null).forkedBuilder().build();
        MavenExecution mavenExecution = runtime.forProject(new File(project));
        this.mojo = new Mojo(mavenExecution);
    }

    static MavenRuntimeTestContextManager create(@NonNull String project) {
        return new MavenRuntimeTestContextManager(project);
    }

    void initializeMojoContexts(Object testInstance) {
        ofNullable(getFieldsListWithAnnotation(testInstance.getClass(), MojoContext.class))
                .orElse(of())
                .forEach(field -> {
                    try {
                        writeField(field, testInstance, getMojo(), true);
                    } catch (IllegalAccessException e) {
                        throw new IllegalCallerException(e);
                    }
                });
    }

    private static class PathDiscovery {

        static String discover() {
            return ofNullable(getenv("MAVEN_HOME")).orElse(tryToFindMavenHome());
        }

        private static String tryToFindMavenHome() {
            return "";
        }
    }
}
