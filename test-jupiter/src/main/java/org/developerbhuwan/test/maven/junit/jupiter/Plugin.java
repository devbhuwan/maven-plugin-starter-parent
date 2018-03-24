package org.developerbhuwan.test.maven.junit.jupiter;

import lombok.Getter;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@Getter
public class Plugin {

    private MavenProject project;
    private MavenSession session;
    public ExecuteResult execute(String goal) {
        return null;
    }
}
