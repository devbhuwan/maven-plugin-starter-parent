package org.developerbhuwan.maven.test.junit.jupiter;

import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;

/**
 * @author Bhuwan Prasad Upadhyay
 */
class MavenPluginExtension implements
        BeforeAllCallback, AfterAllCallback, TestInstancePostProcessor,
        BeforeEachCallback, AfterEachCallback, BeforeTestExecutionCallback,
        AfterTestExecutionCallback, ParameterResolver {

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        ContextStore.runtimeManager.afterTestClass();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Object testInstance = context.getRequiredTestInstance();
        Method testMethod = context.getRequiredTestMethod();
        Throwable testException = context.getExecutionException().orElse(null);
        ContextStore.runtimeManager.afterTestMethod(testInstance, testMethod, testException);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {

    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Class<?> testClass = context.getRequiredTestClass();
        ContextStore.runtimeManager = MavenRuntimeTestContextManager.create(testClass);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        context.getTestInstance().ifPresent(ContextStore.runtimeManager::initializeMojoContexts);
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

    private static class ContextStore {
        private static volatile MavenRuntimeTestContextManager runtimeManager;
    }
}
