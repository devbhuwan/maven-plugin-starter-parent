package org.developerbhuwan.maven.test.junit.jupiter;

import io.takari.maven.testing.executor.MavenExecution;
import io.takari.maven.testing.executor.MavenRuntime;
import lombok.Getter;
import lombok.NonNull;

import java.io.File;

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

    static MavenRuntimeTestContextManager create(@NonNull MojoTest config) {
        MavenRuntime runtime = MavenRuntime.builder(MAVEN_HOME, null).forkedBuilder().build();
        return new MavenRuntimeTestContextManager(runtime.forProject(new File(config.project())));
    }

}
