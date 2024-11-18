package org.example;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    @Test
    void check1() {
        runTest("case1.%s");
    }

    @Test
    void check2() {
        runTest("case2.%s");
    }



    private void runTest(String pattern) {
        execute(pattern);
        assertRun(pattern);
    }

    @SneakyThrows
    private void assertRun(String pattern) {
        String answer = Files.readString(Path.of("target/" + String.format(pattern, "answer")));
        String out = Files.readString(Path.of("task/" + String.format(pattern, "out")));
        assertEquals(out, answer.trim());
    }

    @SneakyThrows
    private void execute(String pattern) {
        try (
                InputStream in = new FileInputStream("task/" + String.format(pattern, "in"));
                OutputStream answer = new FileOutputStream("target/" + String.format(pattern, "answer"), false);
        ) {
            HomeWork hw = new HomeWork();
            hw.championship(in, answer);

        }

    }

}