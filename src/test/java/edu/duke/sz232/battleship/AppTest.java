package edu.duke.sz232.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

public class AppTest {
    

    @Test
    
    @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
    void test_main() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bytes, true);
        InputStream input = getClass().getClassLoader().getResourceAsStream("input.txt");
        assertNotNull(input);
        InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output.txt");
        assertNotNull(expectedStream);
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;
        try {
            System.setIn(input);
            System.setOut(out);
            App.main(new String[0]);
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
        String expected = new String(expectedStream.readAllBytes());

        String actual = bytes.toString();

        assertEquals(expected, actual);
    }

    @Test
    @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
    void test_Computer_player() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bytes, true);
        InputStream input = getClass().getClassLoader().getResourceAsStream("input_computer.txt");
        assertNotNull(input);
        InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output_computer.txt");
        assertNotNull(expectedStream);
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;
        try {
            System.setIn(input);
            System.setOut(out);
            App.main(new String[0]);
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
        String expected = new String(expectedStream.readAllBytes());

        String actual = bytes.toString();

        assertEquals(expected, actual);
    }

    @Test
    @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
    void test_lose() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bytes, true);
        InputStream input = getClass().getClassLoader().getResourceAsStream("input_lose.txt");
        assertNotNull(input);
        InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output_lose.txt");
        assertNotNull(expectedStream);
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;
        try {
            System.setIn(input);
            System.setOut(out);
            App.main(new String[0]);
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
        String expected = new String(expectedStream.readAllBytes());

        String actual = bytes.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void test_app_init(){
        StringReader sr1 = new StringReader("FM\nA1\n");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bytes, true);
        Board<Character> b = new BattleShipBoard<Character>(10, 20, 'X');
        TextPlayer player1 = new TextPlayer("A", b, new BufferedReader(sr1), ps, new V1ShipFactory());
        TextPlayer player2 = new TextPlayer("A", b, new BufferedReader(sr1), ps, new V1ShipFactory());
        App app = new App(player1, player2);
    }

    @Test
    @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
    void test_not_valid_input() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bytes, true);
        InputStream input = getClass().getClassLoader().getResourceAsStream("input_not_valid_player.txt");
        assertNotNull(input);
        InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output_not_valid_player.txt");
        assertNotNull(expectedStream);
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;
        try {
            System.setIn(input);
            System.setOut(out);
            App.main(new String[0]);
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
        String expected = new String(expectedStream.readAllBytes());

        String actual = bytes.toString();

        assertEquals(expected, actual);
    }
}
