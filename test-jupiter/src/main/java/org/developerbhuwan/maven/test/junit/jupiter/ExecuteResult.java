package org.developerbhuwan.maven.test.junit.jupiter;

import io.takari.maven.testing.executor.MavenExecutionResult;

/**
 * @author Bhuwan Prasad Upadhyay
 */
public class ExecuteResult {
    private final MavenExecutionResult result;

    public ExecuteResult(MavenExecutionResult result) {
        this.result = result;
    }

    public void assertErrorFreeLog() {
        try {
            result.assertErrorFreeLog();
        } catch (Exception e) {
            throw new IllegalCallerException(e);
        }
    }
}
