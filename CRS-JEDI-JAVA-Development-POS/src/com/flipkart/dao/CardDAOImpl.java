package com.flipkart.dao;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public void addCard(int refId, int cardNo, String type, int cvv) {
        try{
            String sql= SQLQueriesConstants.INSERT_CARD;
            statement=connection.prepareStatement(sql);
            statement.setInt(1,refId);
            statement.setInt(2,cardNo);
            statement.setString(3,type);
            statement.setInt(4,cvv);
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
