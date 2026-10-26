public class AcademicEvaluator {
    public static void main(String[] args) {
        double gwa = 94.5;
        int lowestGrade = 88;

        if (gwa >= 90.0) {
            if (lowestGrade >= 85) {
                if (gwa >= 98.0) {
                    System.out.println("Academic Status: With Highest Honors");
                } else if (gwa >= 95.0) {
                    System.out.println("Academic Status: With High Honors");
              } else if (gwa >= 90.0) {
                System.out.println("Academic Status: With Honors");
              }
        } else {
            System.out.println("Academic Status: Honors Disqulified due to grade below minimum requirement");
        }
    } else {
        System.out.println("Academic Status: Regular Passing Grade");
    }
}
}
