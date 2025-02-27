package com.datn.qlct.entity.CustomeIDGenerator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhomSinhVienIdGenerator implements IdentifierGenerator {
    private static final Logger LOGGER = Logger.getLogger(NhomSinhVienIdGenerator.class.getName());

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        String prefix = "NH";
        String suffix = "";

        try {
            Connection connection = sharedSessionContractImplementor
                    .getJdbcConnectionAccess()
                    .obtainConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT nhom_sinh_vien_seq.NEXTVAL FROM DUAL");

            if (rs.next()) {
                int nextVal = rs.getInt(1);
                suffix = String.format("%05d", nextVal); // Format the number with leading zeros
            }

            connection.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error generating ID", e);
            throw new RuntimeException("Error generating ID", e);
        }

        return prefix + suffix;
    }
}
