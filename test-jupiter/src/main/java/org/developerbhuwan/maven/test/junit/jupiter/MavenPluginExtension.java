package org.developerbhuwan.maven.test.junit.jupiter;

import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;

import static org.developerbhuwan.maven.test.junit.jupiter.MavenRuntimeTestContextManager.create;

/**
 * @author Bhuwan Prasad Upadhyay
 */
class MavenPluginExtension implements AfterEachCallback, ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext ctx, ExtensionContext context) throws ParameterResolutionException {
        return ctx.getParameter().getType() == Mojo.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context) throws ParameterResolutionException {
        Method method = context.getRequiredTestMethod();
        return create(method.getAnnotation(MojoTest.class)).getMojo();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
    }
}
