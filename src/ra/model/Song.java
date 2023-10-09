package ra.model;

import java.util.Date;
import java.util.Scanner;

public class Song {
    private static int idAuto = 100;
    private String songId;
    private String songName;
    private String descriptions;
    private Singer singer;
    private String songWriter;
    private final Date createdDate = new Date();
    private boolean songStatus;

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getSongWriter() {
        return songWriter;
    }

    public void setSongWriter(String songWriter) {
        this.songWriter = songWriter;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public boolean isSongStatus() {
        return songStatus;
    }

    public void setSongStatus(boolean songStatus) {
        this.songStatus = songStatus;
    }

    public Song() {
        this.songId = "S" + (Song.idAuto++);
    }

    public void inputData(Scanner scanner, Singer singer) {
        do {
            System.out.println("Tên bài hát (Không được để trống):");
            this.songName = scanner.nextLine();
        }while (this.songName.trim().isEmpty());
        do {
            System.out.println("Mô tả:");
            this.descriptions = scanner.nextLine();
        }while (this.descriptions.trim().isEmpty());
        this.singer = singer;
        do {
            System.out.println("Người sáng tác (Không được để trống):");
            this.songWriter = scanner.nextLine();
        }while (this.songWriter.trim().isEmpty());
        System.out.println("""
                Trạng thái:
                1. true
                2.false
                """);
        this.songStatus = (Integer.parseInt(scanner.nextLine()) == 1);
    }
    public void displayData() {
        System.out.println( "Song{" +
                "songId='" + songId + '\'' +
                ", songName='" + songName + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", singer=" + singer.getSingerName() +
                ", songWriter='" + songWriter + '\'' +
                ", createdDate=" + createdDate +
                ", songStatus=" + songStatus +
                '}');
    }
}
