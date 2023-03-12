/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Connect.Myconnection;
import com.sun.source.tree.CatchTree;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author bee
 */
public class DAO {

    Myconnection m = new Myconnection();
    Connection con = m.getConnection();

    public boolean addNguoiDung(NguoiDung nd) {
        String sql = ("insert into NGUOIDUNG(userName, passWord, MaNV, TenPQ) values(?,?,?,?)");
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nd.getUserName());
            ps.setString(2, nd.getPassWord());
            ps.setString(3, nd.getMaNV());
            ps.setString(4, nd.getTenPQ());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addNhanVien(NhanVien nv) {
        String sql = ("insert into NHANVIEN(MaNV, HoTen, GioiTinh, CMT, Email, NgaySinh, DiaChi, NgayVaoLam, DienThoai, MaTD, MaCV, MaPB) values (?,?,?,?,?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoTen());
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getCmt());
            ps.setDate(6, new Date(nv.getNgaySinh().getTime()));
            ps.setString(7, nv.getDiaChi());
            ps.setString(9, nv.getDienThoai());
            ps.setString(5, nv.getEmail());
            ps.setDate(8, new Date(nv.getNgayVaoLam().getTime()));
            ps.setString(10, nv.getMaTD());
            ps.setString(11, nv.getMaCV());
            ps.setString(12, nv.getMaPB());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<NhanVien> getListNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "select * from NHANVIEN";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setCmt(rs.getString("CMT"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setEmail(rs.getString("Email"));

                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addChucVu(ChucVu cv) {
        String sql = ("insert into CHUCVU(MaCV, TenChucVu, LuongCoBan) values (?,?,?)");
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cv.getMaCV());
            ps.setString(2, cv.getTenChucVu());
            ps.setFloat(3, cv.getLuongCoBan());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addKhenThuong(KhenThuong kt) {
        String sql = ("insert into KHENTHUONG(MaKhenThuong, MaNV, TienThuongPhat, MaHinhThuc, Note) values (?,?,?,?,?)");
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kt.getMaKhenThuong());
            ps.setString(2, kt.getMaNV());
            ps.setFloat(3, kt.getTienThuongPhat());
            ps.setInt(4, kt.getMaHinhThuc());
            ps.setString(5, kt.getNote());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addLuong(Luong l) {
        String sql = ("insert into KHENTHUONG(MaLuong, MaNV, HesoLuong, LuongCB) values (?,?,?,?)");
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, l.getMaLuong());
            ps.setString(2, l.getMaNV());
            ps.setFloat(3, l.getHeSoLuong());
            ps.setFloat(4, l.getLuongCB());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addPhongBan(PhongBan pb) {
        String sql = ("insert into PHONGBAN(MaPB, TenPB, DienThoai) values (?,?,?)");
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pb.getMaPB());
            ps.setString(2, pb.getTenPB());
            ps.setString(3, pb.getDienThoai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addTrinhDo(TrinhDo td) {
        String sql = ("insert into TRINHDO(MaTD, TenTD) values (?,?)");
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, td.getMaTD());
            ps.setString(2, td.getTenTD());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updKhenThuong(KhenThuong kt) throws SQLException {
        String sql = "update KHENTHUONG set TienThuongPhat=?,Note=? where MaKhenThuong=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(3, kt.getMaKhenThuong());
        ps.setFloat(1, kt.getTienThuongPhat());
        ps.setString(2, kt.getNote());
        return ps.executeUpdate() > 0;
    }

    public boolean updTrinhDo(TrinhDo td) throws SQLException {
        String sql = "update TRINHDO set MaTD=?,TenTD=? where MaTD=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(3, td.getMaTD());
        ps.setString(1, td.getMaTD());
        ps.setString(2, String.valueOf(td.getTenTD()));
        return ps.executeUpdate() > 0;

    }

    public boolean updNhanVien(NhanVien nv) throws SQLException {
        String sql = ("update NHANVIEN set MaNV=?, HoTen=?, GioiTinh=?, CMT=?, Email=?, NgaySinh=?, DiaChi=?, NgayVaoLam=?, DienThoai=?, MaTD=?, MaCV=?, MaPB=? where MaNV=?");
        PreparedStatement ps = con.prepareStatement(sql);
        try {
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoTen());
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getCmt());
            ps.setString(5, nv.getEmail());
            ps.setDate(6, new Date(nv.getNgaySinh().getTime()));
            ps.setString(7, nv.getDiaChi());
            ps.setDate(8, new Date(nv.getNgayVaoLam().getTime()));
            ps.setString(9, nv.getDienThoai());
            ps.setString(10, nv.getMaTD());
            ps.setString(11, nv.getMaCV());
            ps.setString(12, nv.getMaPB());
            ps.setString(13, nv.getMaNV());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updLuong(Luong l) throws SQLException {
        String sql = ("update LUONG set MaNV=?, HesoLuong=?, LuongCB=? where MaLuong=?");
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(4, l.getMaLuong());
        ps.setString(1, l.getMaNV());
        ps.setFloat(2, l.getHeSoLuong());
        ps.setFloat(3, l.getLuongCB());
        return ps.executeUpdate() > 0;
    }

    public boolean updPhongBan(PhongBan pb) throws SQLException {
        String sql = "UPDATE  PhongBan set MaPB=?,TenPB=? WHERE MaPB=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(3, pb.getMaPB());
        ps.setString(1, pb.getMaPB());
        ps.setString(2, String.valueOf(pb.getTenPB()));
        return ps.executeUpdate() > 0;
    }

    public boolean updNguoiDung(NguoiDung nd) throws SQLException {
        String sql = "update NGUOIDUNG set userName=?, passWord=?, TenPQ=? where MaNV=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nd.getUserName());
        ps.setString(2, nd.getPassWord());
        ps.setString(3, nd.getTenPQ());
        ps.setString(4, nd.getMaNV());
        return ps.executeUpdate() > 0;
    }

    public boolean updChucVu(ChucVu cv) throws SQLException {
        String sql = "update CHUCVU set MaCV=?,TenChucVu=?,LuongCoBan=? where MaCV=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(4, cv.getMaCV());
        ps.setString(1, cv.getMaCV());
        ps.setString(2, String.valueOf(cv.getTenChucVu()));
        ps.setFloat(3, cv.getLuongCoBan());
        return ps.executeUpdate() > 0;
    }

    public boolean delNguoiDung(String ID) throws ClassNotFoundException, SQLException {
        String sql = "delete from NGUOIDUNG where MaNV= ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ID);
        return ps.executeUpdate() > 0;
    }

    public boolean delTrinhDo(String ID) throws ClassNotFoundException, SQLException {
        String sql = "delete from TRINHDO where MaTD= ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ID);
        return ps.executeUpdate() > 0;
    }

    public boolean delPhongBan(String ID) throws ClassNotFoundException, SQLException {
        String sql = "delete from PHONGBAN where MaPB= ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ID);
        return ps.executeUpdate() > 0;
    }

    public boolean delChucVu(String ID) throws ClassNotFoundException, SQLException {
        String sql = "delete from CHUCVU where MaCV=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ID);
        return ps.executeUpdate() > 0;
    }

    public boolean delNhanVien(String ID) throws ClassNotFoundException, SQLException {
        String sql = "delete from NHANVIEN where MaNV=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ID);
        return ps.executeUpdate() > 0;
    }

    public boolean delLuong(int ID) throws ClassNotFoundException, SQLException {
        String sql = "delete from LUONG where MaLuong=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ID);
        return ps.executeUpdate() > 0;
    }

    public boolean delThuong(String ID) throws ClassNotFoundException, SQLException {
        String sql = "delete from KHENTHUONG where MaKhenThuong=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ID);
        return ps.executeUpdate() > 0;
    }
}
