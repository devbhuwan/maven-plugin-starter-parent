package org.developerbhuwan.maven.test.junit.jupiter;

import io.takari.maven.testing.executor.MavenExecution;
import lombok.NonNull;

/**
 * @author Bhuwan Prasad Upadhyay
 */
public class Mojo {

    private final MavenExecution exec;

    public Mojo(@NonNull MavenExecution exec) {
        this.exec = exec;
    }

    public ExecuteResult execute(String goal) {
        try {
            return new ExecuteResult(exec.execute(goal));
        } catch (Exception e) {
            throw new IllegalCallerException(e);
        }
    }

}
