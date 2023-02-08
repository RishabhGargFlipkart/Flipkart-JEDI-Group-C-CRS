package com.flipkart.dao;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class CardDAOImpl implements CardDAO{
    private static volatile CardDAOImpl instance=null;

    private CardDAOImpl(){}

    public static CardDAOImpl getInstance()
    {
        if(instance == null)
        {
            synchronized(CardDAOImpl.class){
                instance = new CardDAOImpl();
            }
        }
        return instance;
    }

    private PreparedStatement statement = null;
    Connection connection = DBUtils.getConnection();

    /**
     * @param refId
     * @param cardNo
     * @param type
     * @param cvv
     */
    @Override
    public void addCard(int refId, String cardNo, String type, int cvv, Date date) {
        try{
            String sql= SQLQueriesConstants.INSERT_CARD;
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            statement=connection.prepareStatement(sql);
            statement.setInt(1,refId);
            statement.setString(2,cardNo);
            statement.setString(3,type);
            statement.setInt(4,cvv);
            statement.setDate(5, sqlDate);
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println("hi");
            System.out.println(e.getMessage());
        }
    }
}
