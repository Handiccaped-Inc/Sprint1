package co.unicauca.payment.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.unicauca.payment.domain.Account;

public class AccountRepository implements IAccountRepository {

    private Connection conn;

    public AccountRepository() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Account findByCard(String cardNumber) {
        try {
            String sql = "SELECT * FROM account WHERE account_card = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cardNumber);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                Account account = new Account();
                account.setId(res.getLong("account_id"));
                account.setCard(res.getString("account_card"));
                account.setAvailableMoney(res.getLong("account_available_money"));
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(Account account) {
        try {
            String sql = "UPDATE account SET account_available_money = ? WHERE account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, account.getAvailableMoney());
            pstmt.setLong(2, account.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}