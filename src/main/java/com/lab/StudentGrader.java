package com.lab;

import java.util.List;

public class StudentGrader {


    public double calculateAverage(List<Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        // Vòng lặp duyệt qua danh sách điểm
        for (double score : scores) {
            total += score;
        }

        return total / scores.size();
    }

    public String classify(double average) {
        // Lệnh rẽ nhánh if-else if
        if (average >= 9.0) {
            return "Xuất sắc";
        } else if (average >= 8.0) {
            return "Giỏi";
        } else if (average >= 6.5) {
            return "Khá";
        } else if (average >= 5.0) {
            return "Trung bình";
        } else {
            return "Yếu";
        }
    }

    public String getResult(List<Double> scores) {
        double avg = calculateAverage(scores);
        String rank = classify(avg);
        return String.format("Điểm TB: %.2f - Xếp loại: %s", avg, rank);
    }

    public static void main(String[] args) {
        StudentGrader grader = new StudentGrader();

        List<Double> scores = List.of(8.5, 9.0, 7.5, 8.0, 6.5);
        System.out.println("Danh sách điểm: " + scores);
        System.out.println(grader.getResult(scores));
    }
}