package de.nineteenbytes;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;

/**
 * JUnit 3 (Variante b)
 */
public class MyMojoTestCase extends AbstractMojoTestCase {
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSomething() throws Exception {
        File pom = getTestFile("src/test/resources/project-to-test/pom.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        MyMojo myMojo = (MyMojo) lookupMojo("touch", pom);
        assertNotNull(myMojo);
        myMojo.execute();

        File outputDirectory = (File) getVariableValueFromObject(myMojo, "outputDirectory");
        assertNotNull(outputDirectory);
        assertTrue(outputDirectory.exists());

        File touch = new File(outputDirectory, "touch.txt");
        assertTrue(touch.exists());
    }
}
