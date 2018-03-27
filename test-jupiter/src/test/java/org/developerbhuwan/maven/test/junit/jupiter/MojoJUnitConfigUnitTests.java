package org.developerbhuwan.maven.test.junit.jupiter;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@MojoJunitConfig
class MojoJUnitConfigUnitTests {

    @MojoTest(project = "src/projects/mojo-junit")
    void definedCorrectPropertyValuesWithMojoThenBuildSuccessful(Mojo mojo) throws Exception {
        mojo.begin()
                .execute("package").assertErrorFreeLog();
    }

}