package ra.run;

import ra.model.Singer;
import ra.model.Song;

import java.util.Arrays;
import java.util.Scanner;

public class MusicManagement {
    static Singer[] singers = new Singer[100];
    static int indexSinger = 0;
    static Song[] songs = new Song[100];
    static int indexSong = 0;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true){
            System.out.println("""
                    ************************MUSIC-MANAGEMENT*************************
                    1. Quản lý ca sĩ
                    2. Quản lý bài hát
                    3. Tìm kiếm bài hát
                    4. Thoát
                    """);
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    handleSingerManagement();
                    break;
                case 2:
                    handleSongManagement();
                    break;
                case 3:
                    handleSearch();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Không hợp lệ.");
                    break;
            }
        }
    }

    private static void handleSearch() {
        while (true){
            System.out.println("""
                    *********************SEARCH-MANAGEMENT************************
                    1.Tìm kiếm bài hát theo tên ca sĩ hoặc thể loại
                    2.Tìm kiếm ca sĩ theo tên hoặc thể loại
                    3.Hiển thị danh sách bài hát theo thứ tự tên tăng dần
                    4.Hiển thị 10 bài hát được đăng mới nhất
                    5.Thoát
                    """);
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    findSongBySingerNameOrGenre();
                    break;
                case 2:
                    findSingerBySingerNameOrGenre();
                    break;
                case 3:
                    showSongsByName();
                    break;
                case 4:
                    showTenNewSongs();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Không hợp lệ.");
                    break;
            }
        }
    }

    private static void showSongsByName() {
        Arrays.sort(songs, 0, indexSong, (b1, b2) -> b1.getSongName().compareTo(b2.getSongName()));
        handleShowSong();
    }

    private static void showTenNewSongs() {
        System.out.println("10 bài hát mới nhất là");
        for (int i = indexSong - 1, j = 0; i >= 0 && j < 10; i--,j++) {
            songs[i].displayData();
        }
    }

    private static void findSongBySingerNameOrGenre() {
        System.out.println("Nhập tên ca sĩ hoặc thể loại");
        String findText = scanner.nextLine();
        System.out.println("Kết quả tìm kiếm: ");
        for (int i = 0; i < indexSong; i++) {
            if (songs[i].getSinger().getSingerName().toLowerCase().contains(findText.toLowerCase())
            || songs[i].getSinger().getGenre().toLowerCase().contains(findText.toLowerCase())) {
                songs[i].displayData();
            }
        }
    }

    private static void findSingerBySingerNameOrGenre() {
        System.out.println("Nhập tên ca sĩ hoặc thể loại");
        String findText = scanner.nextLine();
        System.out.println("Kết quả tìm kiếm: ");
        for (int i = 0; i < indexSinger; i++) {
            if (singers[i].getSingerName().toLowerCase().contains(findText.toLowerCase())
            || singers[i].getGenre().toLowerCase().contains(findText.toLowerCase())) {
                singers[i].displayData();
            }
        }
    }

    private static void handleSongManagement() {
        while (true){
            System.out.println("""
                **********************SONG-MANAGEMENT*************************
                1.Nhập vào số lượng bài hát cần thêm và nhập thông tin cần thêm mới
                2.Hiển thị danh sách tất cả bài hát đã lưu trữ
                3.Thay đổi thông tin bài hát theo mã id
                4.Xóa bài hát theo mã id
                5.Thoát
                """);
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    handleAddSong();
                    break;
                case 2:
                    handleShowSong();
                    break;
                case 3:
                    handleUpdateSong();
                    break;
                case 4:
                    handleDeleteSong();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Không hợp lệ.");
                    break;
            }
        }
    }

    private static void handleDeleteSong() {
        System.out.println("Nhập id bài hát muốn xóa");
        String idDelete = scanner.nextLine();
        for (int i = 0; i < indexSong; i++) {
            if (songs[i].getSongId().equalsIgnoreCase(idDelete)){
                for (int j = i; j < indexSong; j++) {
                    songs[j] = songs[j+1];
                }
                System.out.println("Xóa thành công");
                indexSong--;
                return;
            }
        }
        System.out.println("Không tìm thấy bài hát có id: " + idDelete);
    }

    private static void handleUpdateSong() {
        System.out.println("Nhập id bài hát muốn sửa");
        String idEdit = scanner.nextLine();
        for (int i = 0; i < indexSong; i++) {
            if (songs[i].getSongId().equalsIgnoreCase(idEdit)){
                songs[i].displayData();
                System.out.println("nhập thông tin mới");
                songs[i].inputData(scanner, songs[i].getSinger());
                System.out.println("Cập nhật thành công");
                return;
            }
        }
        System.out.println("Không tìm thấy bài hát có id: " + idEdit);
    }

    private static void handleShowSong() {
        for (int i = 0; i < indexSong; i++) {
            songs[i].displayData();
        }
    }

    private static void handleAddSong() {
        System.out.println("Nhập số lượng bài hát muốn thêm:");
        int contSong = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < contSong; i++) {
                System.out.println("Thêm bài hát" + (i+1));
                System.out.println("Nhập id ca sĩ thể hiện");
                int indicator = Integer.parseInt(scanner.nextLine());
                boolean flag = false;
                for (int j = 0; j < indexSinger; j++) {
                    if (singers[j].getSingerId() == indicator){
                        Song song = new Song();
                        song.inputData(scanner, singers[i]);
                        songs[indexSong++] = song;
                        System.out.println("Thêm bài hát thành công");
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    System.out.println("""
                    Ca sĩ không tồn tại, Tạo mới ca sĩ?
                    1. Có
                    2. Không
                    """);
                    int selected = Integer.parseInt(scanner.nextLine());
                    if (selected == 1){
                        handleAddSinger();
                        System.out.println("Tiếp tục tạo bài hát");
                        handleAddSong();
                        return;
                    }
                }
            }

    }

    private static void handleSingerManagement() {
        while (true){
            System.out.println("""
                **********************SINGER-MANAGEMENT*************************
                1.Nhập vào số lượng ca sĩ cần thêm và nhập thông tin cần thêm mới
                2.Hiển thị danh sách tất cả ca sĩ đã lưu trữ
                3.Thay đổi thông tin ca sĩ theo mã id
                4.Xóa ca sĩ theo mã id
                5.Thoát
                """);
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    handleAddSinger();
                    break;
                case 2:
                    handleShowSinger();
                    break;
                case 3:
                    handleUpdateSinger();
                    break;
                case 4:
                    handleDeleteSinger();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Không hợp lệ.");
                    break;
            }
        }
    }

    private static void handleDeleteSinger() {
        System.out.println("Nhập id ca sĩ muốn xóa");
        int idDelete = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < indexSong; i++) {
            if (songs[i].getSinger().getSingerId() == idDelete){
                System.out.println("Xóa hết bài hát của ca sĩ trước");
                return;
            }
        }
        for (int i = 0; i < indexSinger; i++) {
            if (singers[i].getSingerId() == idDelete){
                for (int j = i; j < indexSinger; j++) {
                    singers[j] = singers[j+1];
                }
                System.out.println("Xóa thành công");
                indexSinger--;
                return;
            }
        }
        System.out.println("Không tìm được ca sĩ có id: " + idDelete);
    }

    private static void handleUpdateSinger() {
        System.out.println("Nhập id ca sĩ muốn sửa");
        int idEdit = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < indexSinger; i++) {
            if (singers[i].getSingerId() == idEdit){
                System.out.println("Thông tin ca sĩ:");
                singers[i].displayData();
                singers[i].inputData(scanner);
                System.out.println("Cập nhật thành công");
                return;
            }
        }
        System.out.println("Không tìm được ca sĩ có id: " + idEdit);
    }

    private static void handleShowSinger() {
        for (int i = 0; i < indexSinger; i++) {
            singers[i].displayData();
        }
    }

    private static void handleAddSinger() {
        System.out.println("Nhập số lượng ca sĩ muốn thêm");
        int countAdd = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < countAdd; i++) {
            System.out.println("Thêm ca sĩ " + (i+1));
            Singer singer = new Singer();
            singer.inputData(scanner);
            singers[indexSinger++] = singer;
        }
    }
}
