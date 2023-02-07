package com.flipkart.dao;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NetBankingDAOImpl implements NetBankingDAO{
    private static volatile NetBankingDAOImpl instance=null;

    private NetBankingDAOImpl(){}

    public static NetBankingDAOImpl getInstance()
    {
        if(instance == null)
        {
            synchronized(NetBankingDAOImpl.class){
                instance = new NetBankingDAOImpl();
            }
        }
        return instance;
    }

    private PreparedStatement statement = null;
    Connection connection = DBUtils.getConnection();

    /**
     * @param refId
     * @param accountNo
     * @param ifsc
     */
    @Override
    public void addTransaction(int refId, int accountNo, String ifsc) {
        try{
            String sql= SQLQueriesConstants.INSERT_NET_BANKING;
            statement=connection.prepareStatement(sql);
            statement.setInt(1,refId);
            statement.setInt(2,accountNo);
            statement.setString(3,ifsc);
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
