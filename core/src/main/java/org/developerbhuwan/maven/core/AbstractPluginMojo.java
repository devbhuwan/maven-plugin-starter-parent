package org.developerbhuwan.maven.core;

import lombok.Getter;
import lombok.Setter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@Getter
@Setter
public abstract class AbstractPluginMojo extends AbstractMojo {

    @Parameter(defaultValue = "false")
    private boolean skip = false;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().debug("before execute");
        if (skip)
            getLog().info("plugin skipped!");
        else
            this.doExecute();
        getLog().debug("after execute");
    }

    protected abstract void doExecute();

}
