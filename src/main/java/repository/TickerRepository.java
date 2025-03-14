package repository;

import model.Ticker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TickerRepository implements CrudRepository<Ticker> {

    private final Connection connection;

    public TickerRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Ticker> findById(Long id) {
        String sql = "SELECT id, symbol, name FROM ticker WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Ticker ticker = new Ticker(
                            rs.getLong("id"),
                            rs.getString("symbol"),
                            rs.getString("name")
                    );
                    return Optional.of(ticker);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Ticker save(Ticker entity) {
        // Если id == null => INSERT, иначе UPDATE
        if (entity.getId() == null) {
            return insert(entity);
        } else {
            return update(entity);
        }
    }

    private Ticker insert(Ticker ticker) {
        String sql = "INSERT INTO ticker (symbol, name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, ticker.getSymbol());
            statement.setString(2, ticker.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ticker.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticker;
    }

    private Ticker update(Ticker ticker) {
        String sql = "UPDATE ticker SET symbol = ?, name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ticker.getSymbol());
            statement.setString(2, ticker.getName());
            statement.setLong(3, ticker.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticker;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM ticker WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticker> findAll() {
        List<Ticker> result = new ArrayList<>();
        String sql = "SELECT id, symbol, name FROM ticker";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Ticker ticker = new Ticker(
                        rs.getLong("id"),
                        rs.getString("symbol"),
                        rs.getString("name")
                );
                result.add(ticker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
