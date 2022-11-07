package jeff.question1.process;

import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testProcess(){
        try {
            String absolutePath = Paths.get(this.getClass().getClassLoader()
                    .getResource("employees1.xml").toURI()).toFile().getAbsolutePath();

            String targetFile = Paths.get(this.getClass().getClassLoader()
                    .getResource("employees2.xml").toURI()).toFile().getAbsolutePath();

            String destinationFie = System.getProperty("java.io.tmpdir")+"output";

            String[] args = String.format("-s %s -t %s -d %s",absolutePath,targetFile,destinationFie).split(" ");

            Main.main(args);

            File output = new File(destinationFie);
            assertTrue(output.exists());

        } catch (IOException e) {
            fail();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            fail();
        }
    }
}
