import java.sql.*;
import java.util.Scanner;

public class jdbcMovieManager {
    public static void main(String[] args) throws SQLException {
        try (
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MovieManager","root","");
            PreparedStatement pstmtInsert = conn.prepareStatement(
                    "insert into movie value (?,?,?,?,?)");
            PreparedStatement pstmtUpdate = conn.prepareStatement(
                    "update movie set id = ? where name = ?");
            PreparedStatement pstmtDelete = conn.prepareStatement(
                    "delete from movie where name = ?");
            PreparedStatement pstmtSelect = conn.prepareStatement(
                    "select * from movie where name = ?");
            PreparedStatement pstmtUpdate2 = conn.prepareStatement(
                    "update movie set timekc = ? where name = ?")
        ){
            conn.setAutoCommit(false);
           try {
               int type;
               int type2;
               int maphim;
               String tenphim;
               String date;
               String daodien;
               String thoigian;
               Scanner ip = new Scanner(System.in);
                do{
                    System.out.println("Enter do you want : ");
                    System.out.println("1:Thêm\t\t2:Sửa\t\t3:Xoá\t\t4:Tìm Kiếm");
                    type=ip.nextInt();
                    if (type==1){
                        System.out.println("==== Them phim ====");
                        System.out.println("Enter id film: ");
                        maphim = ip.nextInt();
                        System.out.println("enter film name:");
                        tenphim = ip.nextLine();
                        System.out.println("Enter premiere date:");
                        date= ip.nextLine();
                        System.out.println("Enter director's name:");
                        daodien =ip.nextLine();
                        System.out.println("Enter movie duration:");
                        thoigian=ip.nextLine();
                        pstmtInsert.setInt(1, maphim);
                        pstmtInsert.setString(2, tenphim);
                        pstmtInsert.setString(3, date);
                        pstmtInsert.setString(4, daodien);
                        pstmtInsert.setString  (5, (thoigian));
                        pstmtInsert.executeUpdate();
                        System.out.println("SUCCESS");
                    }else if(type==2){
                        System.out.println("=== SUA THONG TIN PHIM ===");
                        ip.nextLine();
                        System.out.println("Nhap ten phim muon sua: ");
                        tenphim = ip.nextLine();
                        System.out.println("\nBan muon sua thong tin gi:");
                        System.out.println("1.Ma Phim\t\t2.Thoi gian chieu");
                        type2 = ip.nextInt();
                        if (type2 == 1){
                            System.out.println("== SUA MA PHIM == ");
                            ip.nextLine();
                            System.out.println("Nhap ma phim: ");
                            maphim = ip.nextInt();
                            pstmtUpdate.setInt(1, maphim);
                            pstmtUpdate.setString(2, tenphim);
                            pstmtUpdate.executeUpdate();
                        }
                        if (type2 == 2){
                            System.out.println("== SUA THOI GIAN CHIEU PHIM ==");
                            ip.nextLine();
                            System.out.println("Nhap thoi gian: ");
                            date = ip.nextLine();
                            pstmtUpdate2.setString(1,date);
                            pstmtUpdate2.setString(2,tenphim);
                            pstmtUpdate2.executeUpdate();
                        }
                    }
                    else if(type==3){
                        System.out.println("=== XOA PHIM === ");
                        ip.nextLine();
                        System.out.println("\nNhap ten phim muon xoa: ");
                        tenphim = ip.nextLine();
                        pstmtDelete.setString(1, tenphim);
                        pstmtDelete.executeUpdate();
                    }
                    else if (type==4){
                        System.out.println("=== TIM KIEM THONG TIN PHIM ===");
                        ip.nextLine();
                        System.out.println("\nNhap ten phim muon tim: ");
                        tenphim = ip.nextLine();
                        pstmtSelect.setString(1, tenphim);
                        ResultSet rset = pstmtSelect.executeQuery();
                        while (rset.next()){
                            System.out.println(rset.getString("maphim") + ", "
                                    + rset.getString("tenphim") + ", "
                                    + rset.getString("date") + ", "
                                    + rset.getString(" daodien") + ", "
                                    + rset.getInt("thoigian"));
                        }
                    }
                    conn.commit();
                    System.out.println("Ban co muon tiep tuc khong: ");
                    System.out.println("5.Co\t\t6.Khong");
                    type = ip.nextInt();
                    if (type == 6){
                        break;
                    }
                }while(type != 1 || type != 2 || type != 3 || type !=4 || type != 5);
           } catch (SQLException ex) {
               System.out.println("nhap sai thong tin ");
               conn.rollback();
            ex.printStackTrace();
        }
    }
  }
}
