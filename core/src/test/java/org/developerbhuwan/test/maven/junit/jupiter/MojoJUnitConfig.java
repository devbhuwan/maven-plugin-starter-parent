package org.developerbhuwan.test.maven.junit.jupiter;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@ExtendWith(MavenPluginExtension.class)
@Documented
@Inherited
@Retention(RUNTIME)
@Target(TYPE)
public @interface MojoJUnitConfig {

    String pom() default EMPTY;

}
