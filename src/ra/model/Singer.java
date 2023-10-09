package ra.model;

import java.util.Scanner;

public class Singer {
   private int singerId;
   private String singerName;
   private int age;
   private String nationality;
   private boolean gender;
   private String genre;
   private static int count =1;
    public Singer() {
        this.singerId = Singer.count++;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void inputData(Scanner scanner) {
        do {
            System.out.println("Tên ca sĩ  (Không được để trống):");
            this.singerName = scanner.nextLine();
        }while (this.singerName.trim().isEmpty());
        do {
            System.out.println("Tuổi ca sĩ (Phải lớn hơn 0):");
            this.age = Integer.parseInt(scanner.nextLine());
        }while (this.age <= 0);
        do {
            System.out.println("Quốc tịch (không được để trống):");
            this.nationality = scanner.nextLine();
        }while (this.nationality.trim().isEmpty());
        System.out.println("""
                Chọn giới tính:
                1. nam (true)
                2. nữ (false)
                """);
        int gdr = Integer.parseInt(scanner.nextLine());
        this.gender = (gdr==1);
        do {
            System.out.println("Thể loại (Không được để trống):");
            this.genre = scanner.nextLine();
        }while (this.genre.trim().isEmpty());
    }

    public void displayData() {
        System.out.println(
                "Singer{" +
                "singerId=" + singerId +
                ", singerName='" + singerName + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                ", gender=" + gender +
                ", genre='" + genre + '\'' +
                '}');
    }
}
