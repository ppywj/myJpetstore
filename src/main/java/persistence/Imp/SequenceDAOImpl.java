package persistence.Imp;

import domain.Sequence;
import persistence.SequenceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static persistence.DatabaseUtil.close;
import static persistence.DatabaseUtil.getConnection;

public class SequenceDAOImpl implements SequenceDAO {
    private static final String getSequence = "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";
    private static final String updateSequence = "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";

    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence checkSequence = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(getSequence);
            preparedStatement.setString(1, sequence.getName());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                checkSequence = new Sequence();
                checkSequence.setName(resultSet.getString(1));
                checkSequence.setNextId(resultSet.getInt(2));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet, preparedStatement, connection);
        }
        return checkSequence;
    }

    @Override
    public boolean updateSequence(Sequence sequence) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(updateSequence);
            preparedStatement.setString(2, sequence.getName());
            preparedStatement.setInt(1, sequence.getNextId());

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet, preparedStatement, connection);
        }

        return flag;
    }
}
