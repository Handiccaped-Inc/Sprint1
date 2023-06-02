package co.unicauca.payment.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.unicauca.payment.domain.Transaction;

/**
 * Clase repositorio de la transaccion
 */
public class TransactionRepository implements ITransactionRepository {

    private Connection conn;

    public TransactionRepository() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Transaction newTransaction) {
        try {
            String sql = "INSERT INTO transactions (transactions_date, transactions_ammount, account_id_sender, account_id_receiver) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newTransaction.getDate().toString());
            pstmt.setLong(2, newTransaction.getAmmount());
            pstmt.setLong(3, newTransaction.getSender().getId());
            pstmt.setLong(4, newTransaction.getReceiver().getId());
            return pstmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TransactionRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
