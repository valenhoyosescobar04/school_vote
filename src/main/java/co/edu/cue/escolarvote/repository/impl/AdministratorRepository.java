package co.edu.cue.escolarvote.repository.impl;

import co.edu.cue.escolarvote.domain.entities.Administrator;
import co.edu.cue.escolarvote.repository.PostgresConnection;
import co.edu.cue.escolarvote.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorRepository implements Repository<Administrator> {
    private Administrator createAdmin(ResultSet resultSet) throws SQLException{
        Administrator administrator = new Administrator();
        administrator.setId(resultSet.getLong("id"));
        administrator.setUsername(resultSet.getString("username"));
        administrator.setPassword(resultSet.getString("password"));
        return administrator;
    }

    @Override
    public List<Administrator> list() {
        List<Administrator> administrators = new ArrayList<>();
        try (Statement statement= Repository.getConnection().createStatement();
             ResultSet resultSet=statement.executeQuery("SELECT * FROM administrator")
        ){
            while (resultSet.next()) {
                Administrator administrator = createAdmin(resultSet);
                administrators.add(administrator);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return administrators;
    }

    @Override
    public Administrator byId(Long id) {
        Administrator administrator = null;
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("SELECT * FROM administrator where id=?")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                administrator = createAdmin(resultSet);
            }
            resultSet.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return administrator;
    }

    @Override
    public void save(Administrator administrator) {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("INSERT INTO administrator(username,password) VALUES (?,?)")){
            preparedStatement.setString(1,administrator.getUsername());
            preparedStatement.setString(2,administrator.getPassword());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("DELETE FROM administrator WHERE id =?")){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Administrator administrator) {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("UPDATE administrator SET username=? ,password=? where id=?")){
            preparedStatement.setString(1,administrator.getUsername());
            preparedStatement.setString(2,administrator.getPassword());
            preparedStatement.setLong(3,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
