package de.nineteenbytes;

import org.apache.maven.plugin.testing.junit5.InjectMojo;
import org.apache.maven.plugin.testing.junit5.MojoParameter;
import org.apache.maven.plugin.testing.junit5.MojoTest;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@MojoTest
class MyMojoTest {
    private static final String POM = "<project>"
            + "<build>"
            + " <plugins>"
            + "  <plugin>"
            + "   <artifactId>blog-article-junit5-maven-plugin</artifactId>"
            + "   <configuration>"
            + "   </configuration>"
            + "  </plugin>"
            + " </plugins>"
            + "</build>" + "</project>";

    private static final String PLUGIN_NAME = "de.nineteenbytes:blog-article-junit5-maven-plugin:1.0-SNAPSHOT";

    private static final String TOUCH_GOAL = PLUGIN_NAME + ":touch";

    @Test
    @InjectMojo(goal = TOUCH_GOAL, pom = POM)
    // default values werden nicht berücksichtigt
    @MojoParameter(name = "outputDirectory", value = "./target")
    void testSomething(MyMojo mojo) {
        assertNotNull(mojo);
        assertDoesNotThrow(mojo::execute);


        File outputDirectory = new File("./target");
        assertNotNull(outputDirectory);
        assertTrue(outputDirectory.exists());

        File touch = new File(outputDirectory, "touch.txt");
        assertTrue(touch.exists());

    }

}
