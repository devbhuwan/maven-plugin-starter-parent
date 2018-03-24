package org.developerbhuwan.test.maven.junit.jupiter;

import lombok.NonNull;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;

/**
 * @author Bhuwan Prasad Upadhyay
 */
public class MavenPluginExtension implements
        BeforeAllCallback, AfterAllCallback, TestInstancePostProcessor,
        BeforeEachCallback, AfterEachCallback, BeforeTestExecutionCallback,
        AfterTestExecutionCallback, ParameterResolver {

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        getTestContextManager(context).afterTestClass();
    }

    private TestContextManager getTestContextManager(@NonNull ExtensionContext context) {
        Class<?> testClass = context.getRequiredTestClass();
        MojoJUnitConfig config = testClass.getAnnotation(MojoJUnitConfig.class);
        return new TestContextManager(config);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Object testInstance = context.getRequiredTestInstance();
        Method testMethod = context.getRequiredTestMethod();
        Throwable testException = context.getExecutionException().orElse(null);
        getTestContextManager(context).afterTestMethod(testInstance, testMethod, testException);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {

    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {

    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {

    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext context) throws ParameterResolutionException {
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context) throws ParameterResolutionException {
        return null;
    }

    @Override
    public void postProcessTestInstance(Object o, ExtensionContext context) throws Exception {

    }
}
