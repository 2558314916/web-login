package com.example.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author mucd
 * 2021年05月22日  下午 07:19
 */

public class Utils {
    static DataSource dataSource = new DruidDataSource();
    static Properties properties = new Properties();
    static{
        try {
            InputStream in = Utils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static DataSource getDataSource(){
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getDataSource().getConnection());
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        int update = queryRunner.update(
                "insert into  mucd.login(username, password) VALUES (?,?)"
                , "asd", "qwe"
        );
        System.out.println("update = " + update);
    }

    /*public static void main(String[] args) {
        Connection conn;
        PreparedStatement preparedStatement;
        String name = "hg";
        String sex = "h";
        String sql = "insert into mucd.webperson (name, sex) VALUES (?,?)";
        try {
            conn = Utils.getDataSource().getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,sex) ;
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
