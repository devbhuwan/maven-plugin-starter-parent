package org.developerbhuwan.maven.core;

import org.developerbhuwan.test.maven.junit.jupiter.MojoJUnitConfig;
import org.developerbhuwan.test.maven.junit.jupiter.Plugin;
import org.junit.jupiter.api.Test;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@MojoJUnitConfig(pom = "pom.xml")
class PluginMojoTest {

    public static final String CLEAN_INSTALL = "clean install";
    private Plugin plugin;

    @Test
    void execute() {
    }
}