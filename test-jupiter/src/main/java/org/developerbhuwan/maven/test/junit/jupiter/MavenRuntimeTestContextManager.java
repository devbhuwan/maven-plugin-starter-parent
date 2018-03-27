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
import static org.developerbhuwan.maven.test.junit.jupiter.MavenRuntimeTestContextManager.PathDiscovery.*;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@Getter
class MavenRuntimeTestContextManager {

    private final Mojo mojo;

    private MavenRuntimeTestContextManager(@NonNull String project) {
        final File mavenHome = new File(PathDiscovery.discover());
        if (!mavenHome.exists())
            throw new IllegalArgumentException(String.format("Please defined %s, %s environment variable or %s in system properties", MAVEN_HOME, M2_HOME, MAVEN_DOT_HOME));
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

    static class PathDiscovery {

        static final String MAVEN_HOME = "MAVEN_HOME";
        static final String M2_HOME = "M2_HOME";
        static final String MAVEN_DOT_HOME = "maven.home";

        static String discover() {
            return ofNullable(getenv(MAVEN_HOME)).orElse(tryToFindMavenHome());
        }

        private static String tryToFindMavenHome() {
            return ofNullable(getenv(M2_HOME)).orElse(System.getProperty(MAVEN_DOT_HOME));
        }

    }
}
