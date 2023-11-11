package model;


import javax.swing.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccount {

    // số dư của tài khoản ngân hàng
    private long amount;
    // tên của tài khoản ngân hàng
    private final String accountName;
    // Format ngày tháng
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    // lấy giá trị thời gian hiện hành trong các thao tác
    LocalDateTime now;

    // gán các trạng thái vào khu vực trạng thái của giao diện chính
    private final JTextArea notificationTA;
    // gán số dư vào giao diện chính (nếu cần thiết)
    private final JLabel amountLabel;

    public String getAccountName() {
        return accountName;
    }

    public BankAccount(long amount, String accountName, JTextArea notificationTA, JLabel amountLabel) {
        this.amount = amount;
        this.accountName = accountName;
        this.notificationTA = notificationTA;
        this.amountLabel = amountLabel;
        this.notificationTA.append(MessageFormat.format(">>>[Khởi tạo tài khoản {0} với số dư {1}]<<<\n\n", accountName, amount));
    }

    /**
     * Hàm kiểm tra số dư tài khoản có đủ điều kiện để thưc hiện giao dịch
     *
     * @param withdrawAmount : số tiền cần rút
     * @return true nếu có thể rút tiền ngược lại false
     */
    private boolean checkAccountBalance(long withdrawAmount, String user) {
        // hiện thị thông tin
        now = LocalDateTime.now();
        this.notificationTA.append(
                MessageFormat.format(
                        "[{0}][{1}] Đang kiểm tra số dư...\n\n", dtf.format(now), user
                )
        );
        // giả lập đọc database
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // nếu số dư lớn hơn hoặc bằng số tiền yêu cầu rút sẽ có thể rút
        return amount >= withdrawAmount;
    }

    /**
     * Hàm thực hiện rút tiền
     *
     * @param user           tên của người đang thực hiện rút tiền
     * @param withdrawAmount số tiền cần rút
     */
    private boolean withdraw(String user, long withdrawAmount) {
        boolean status = false;

        // In thông tin người rút
        now = LocalDateTime.now();
        this.notificationTA.append(MessageFormat.format("[{0}][{1}][{2}] cần rút: {3}\n\n", dtf.format(now), user, this.amount, withdrawAmount));
        // giả lập xử lý kiểm tra số dư và thực hiện giao dịch
        // nếu số dư đủ thì tiến hành rút
        // ngược lại hiện thông báo không thể rút
        if (checkAccountBalance(withdrawAmount, user)) {
            // giả lập thay đổi số dư trong database
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.amount -= withdrawAmount;
            status = true;
        } else {
            now = LocalDateTime.now();
            this.notificationTA.append(
                    MessageFormat.format(
                            "[{0}][{1}] không thể thực hiện giao dịch do số dư không đủ\n\n", dtf.format(now), user
                    )
            );
        }
        // hiển thị số dư còn lại
        now = LocalDateTime.now();
        if (amountLabel != null) amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
        this.notificationTA.append(MessageFormat.format(
                        "[{0}][{1}] số dư: {2}\n\n", dtf.format(now), user, this.amount
                )
        );
        return status;
    }

    /**
     * Hàm thực hiện nạp tiền
     * @param user tên người dùng đang thực hiện nạp
     * @param depositAmount số tiền nạp vào
     */
    private void deposit(String user, long depositAmount) {
        // in thông tin người rút
        now = LocalDateTime.now();
        this.notificationTA.append(MessageFormat.format("[{0}][{1}][{2}] đang nạp: {3}\n\n", dtf.format(now), user, this.amount, depositAmount));

        // giả lập đọc database và thay đổi số dư
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.amount += depositAmount;
        now = LocalDateTime.now();
        if (amountLabel != null) amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
        this.notificationTA.append(MessageFormat.format(
                        "[{0}][{1}] số dư: {2}\n\n", dtf.format(now), user, this.amount
                )
        );
        // gọi đối tượng đang chờ để thanh toán
        notify();
    }

    /**
     * Hàm thực hiện thanh toán khi số dư khả dụng
     * @param user tên người dùng đặt lệnh thanh toán
     * @param payAmount số tiền cần thanh toán
     */
    private void payWhenBalanceEnough(String user, long payAmount) {
        // In thông tin người thanh toán
        now = LocalDateTime.now();
        this.notificationTA.append(MessageFormat.format("[{0}][{1}][{2}] cần thanh toán: {3}\n\n", dtf.format(now), user, this.amount, payAmount));

        while (!checkAccountBalance(payAmount, user)) {
            // Nếu không đủ tiền, thì đợi cho đến khi có đủ tiền thì thanh toán
            now = LocalDateTime.now();
            this.notificationTA.append(MessageFormat.format("[{0}][{1}] đang chờ đủ số dư để thực hiện thanh toán...\n\n", dtf.format(now), user));
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Đủ tiền, hoặc không còn đợi nữa, thì được phép rút
        // Giả lập thời gian rút tiền và
        // cập nhật số tiền còn lại vào database
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.amount -= payAmount;
        now = LocalDateTime.now();
        this.notificationTA.append(MessageFormat.format("[{0}][{1}][{2}] thanh toán: {3}\n\n", dtf.format(now), user, this.amount, payAmount));
        if (amountLabel != null) amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
        this.notificationTA.append(MessageFormat.format(
                        "[{0}][{1}] số dư: {2}\n\n", dtf.format(now), user, this.amount
                )
        );
    }

    /**
     * Hàm chuyển tiền sẽ xảy ra deadlock
     * @param toAccount : tài khoản nhận tiền
     * @param transferAmount : số tiền được chuyển
     * @param user : người dùng thực hiện chuyển tiền
     */
    public void deadlockTransfer(BankAccount toAccount, long transferAmount, String user) {
        synchronized (this) {
            // Rút tiền từ tài khoản này
            if(this.withdrawWithSync(user, transferAmount)){
                // Nạp tiền vào toAccount
                toAccount.depositWithSync(toAccount.getAccountName(), transferAmount);
            }
        }
    }
    /**
     * Hàm chuyển tiền giải quyết deadlock
     * @param toAccount : tài khoản nhận tiền
     * @param transferAmount : số tiền được chuyển
     * @param user : người dùng thực hiện chuyển tiền
     */
    public void deadlockSolvedTransfer(BankAccount toAccount, long transferAmount, String user) {
        // Rút tiền từ tài khoản này
        if(this.withdrawWithSync(user, transferAmount)){
            // Nạp tiền vào toAccount
            toAccount.depositWithSync(toAccount.getAccountName(), transferAmount);
        }

    }


    /**
     * Hàm thực hiện rút tiền theo cơ chế bất đồng bộ
     * Hàm này sẽ được gọi trong luồng
     * @param user tên người thực hiện rút
     * @param withdrawAmount số tiền cần rút
     */
    public void withdrawWithoutSync(String user, long withdrawAmount) {
        this.withdraw(user, withdrawAmount);
    }

    /**
     * Hàm thực hiện rút tiền theo cơ chế mutual exclusive
     * Hàm này sẽ được gọi trong luồng
     * @param user tên người thực hiện rút
     * @param withdrawAmount số tiền cần rút
     */
    public synchronized boolean withdrawWithSync(String user, long withdrawAmount) {
        return this.withdraw(user, withdrawAmount);
    }

    /**
     * Hàm nạp tiền được đồng bộ
     * Hàm này được gọi trong luồng
     * @param user          tên của người đang thực hiện nạp tiền
     * @param depositAmount số tiền cần nạp
     */
    public synchronized void depositWithSync(String user, long depositAmount) {
        deposit(user, depositAmount);
    }

    /**
     * Đặt lệnh tự động thanh toán theo cơ chế đồng bộ hóa
     * @param user      tên người đang đặt lệnh thanh toán
     * @param payAmount số tiền cần thanh toán
     */
    public synchronized void payWhenBalanceEnoughWithSync(String user, long payAmount) {
        payWhenBalanceEnough(user, payAmount);
    }

}
