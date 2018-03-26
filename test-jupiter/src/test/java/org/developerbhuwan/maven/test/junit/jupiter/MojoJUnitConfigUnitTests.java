package org.developerbhuwan.maven.test.junit.jupiter;

import org.junit.jupiter.api.Test;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@MojoJUnitConfig(project = "src/projects/mojo-junit")
class MojoJUnitConfigUnitTests {

    @MojoContext
    private Mojo mojo;

    @Test
    void name() {
        mojo.execute("install").assertErrorFreeLog();
    }
}