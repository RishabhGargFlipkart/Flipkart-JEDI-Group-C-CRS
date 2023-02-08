package com.flipkart.dao;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChequeDAOImpl implements ChequeDAO {
    private static volatile ChequeDAOImpl instance=null;

    private ChequeDAOImpl(){}

    public static ChequeDAOImpl getInstance()
    {
        if(instance == null)
        {
            synchronized(ChequeDAOImpl.class){
                instance = new ChequeDAOImpl();
            }
        }
        return instance;
    }
    private PreparedStatement statement = null;
    Connection connection = DBUtils.getConnection();

    /**
     * @param refId
     * @param chequeNo
     */
    @Override
    public void addCheque(int refId,String chequeNo) {
        try {
            String sql= SQLQueriesConstants.INSERT_CHEQUE;
            statement=connection.prepareStatement(sql);
            statement.setInt(1,refId);
            statement.setString(2,chequeNo);
            statement.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
