package com.lab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class StudentGraderPathTest {

    private StudentGrader grader;

    @BeforeEach
    void setUp() {
        grader = new StudentGrader();
    }

    // === calculateAverage ===

    // P1: đường đi khi scores == null
    @Test
    void testPath_calculateAverage_null() {
        assertEquals(0.0, grader.calculateAverage(null));
    }

    // P2: đường đi khi scores rỗng
    @Test
    void testPath_calculateAverage_empty() {
        assertEquals(0.0, grader.calculateAverage(List.of()));
    }

    // P3a: đường đi vòng lặp chạy đúng 1 lần (1 phần tử)
    @Test
    void testPath_calculateAverage_singleElement() {
        assertEquals(7.0, grader.calculateAverage(List.of(7.0)), 0.001);
    }

    // P3b: đường đi vòng lặp chạy nhiều lần (nhiều phần tử)
    @Test
    void testPath_calculateAverage_multipleElements() {
        List<Double> scores = List.of(6.0, 8.0, 10.0);
        assertEquals(8.0, grader.calculateAverage(scores), 0.001);
    }

    // === classify ===

    // P4: đường đi average >= 9.0 → "Xuất sắc"
    @Test
    void testPath_classify_exactlyNine() {
        assertEquals("Xuất sắc", grader.classify(9.0));
    }

    @Test
    void testPath_classify_aboveNine() {
        assertEquals("Xuất sắc", grader.classify(10.0));
    }

    // P5: đường đi 8.0 <= average < 9.0 → "Giỏi"
    @Test
    void testPath_classify_exactlyEight() {
        assertEquals("Giỏi", grader.classify(8.0));
    }

    @Test
    void testPath_classify_betweenEightAndNine() {
        assertEquals("Giỏi", grader.classify(8.9));
    }

    // P6: đường đi 6.5 <= average < 8.0 → "Khá"
    @Test
    void testPath_classify_exactlySixPointFive() {
        assertEquals("Khá", grader.classify(6.5));
    }

    @Test
    void testPath_classify_betweenSixFiveAndEight() {
        assertEquals("Khá", grader.classify(7.5));
    }

    // P7: đường đi 5.0 <= average < 6.5 → "Trung bình"
    @Test
    void testPath_classify_exactlyFive() {
        assertEquals("Trung bình", grader.classify(5.0));
    }

    @Test
    void testPath_classify_betweenFiveAndSixFive() {
        assertEquals("Trung bình", grader.classify(6.0));
    }

    // P8: đường đi average < 5.0 → "Yếu"
    @Test
    void testPath_classify_belowFive() {
        assertEquals("Yếu", grader.classify(4.9));
    }

    @Test
    void testPath_classify_zero() {
        assertEquals("Yếu", grader.classify(0.0));
    }

    // === getResult (kết hợp đường đi) ===

    @Test
    void testPath_getResult_emptyScores() {
        // P2 + P8: danh sách rỗng → avg=0.0 → "Yếu"
        String result = grader.getResult(List.of());
        assertTrue(result.contains("0.00"));
        assertTrue(result.contains("Yếu"));
    }

    @Test
    void testPath_getResult_xuatSac() {
        // P3b + P4
        String result = grader.getResult(List.of(9.5, 9.5));
        assertTrue(result.contains("Xuất sắc"));
    }

    @Test
    void testPath_getResult_trungBinh() {
        // P3b + P7
        String result = grader.getResult(List.of(5.0, 6.0));
        assertTrue(result.contains("Trung bình"));
    }
}