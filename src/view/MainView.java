package view;


import model.BankAccount;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;



public class MainView extends JFrame implements ActionListener {

    private static final String PROJECT_NAME = "Các vấn đề về luồng thông qua việc mô phỏng Hệ Thống Giao Dịch Ngân Hàng";
    private final JLabel projectName = new JLabel();
    JButton nonSyncBtn = new JButton("Bất đồng bộ");
    JButton mutualExclusiveBtn = new JButton("Mutual Exclusion");
    JButton cooperationBtn = new JButton("Cooperation");
    JButton deadLockBtn = new JButton("Deadlock");
    JButton solveDeadlockBtn = new JButton("Giải quyết DL");
    JButton stopThreadBtn = new JButton("Dừng luồng");
    JButton statusThreadBtn = new JButton("Kiểm tra luồng");
    JLabel amountLabel = new JLabel("Số dư: 0");
    JTextArea notificationArea = new JTextArea();
    JLabel initAmount = new JLabel("Khởi tạo số dư: ");
    JTextField initAmountTF = new JTextField();
    JLabel user1Withdraw = new JLabel("Số tiền User1 rút: ");
    JTextField user1WithdrawTF = new JTextField();
    JLabel user2Withdraw = new JLabel("Số tiền User2 rút: ");
    JTextField user2WithdrawTF = new JTextField();
    JLabel user3Transfer = new JLabel("Số tiền User3 chuyển: ");
    JTextField user3TransferTF = new JTextField();
    JLabel user2Transfer = new JLabel("Số tiền User2 chuyển: ");
    JTextField user2TransferTF = new JTextField();

    JLabel user2Deposit = new JLabel("Số tiền User2 nạp: ");
    JTextField user2DepositTF = new JTextField();

    public MainView() {
        super(PROJECT_NAME);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setResizable(false);
        init();
        this.setVisible(true);
    }

    /**
     * Hàm định nghĩa các thành phần giao diện
     * và thêm vào cửa sổ giao diện
     */
    private void init() {
        /// Dòng 1 ///
        // thêm tên của đề tài vào giao diện
        projectName.setText(PROJECT_NAME);
        projectName.setFont(new Font(null, Font.BOLD, 15));
        projectName.setBounds(0, 0, 800, 50);
        projectName.setBackground(Color.PINK);
        projectName.setHorizontalAlignment(SwingConstants.CENTER);
        projectName.setVerticalAlignment(SwingConstants.CENTER);
        projectName.setOpaque(true);
        this.add(projectName);

        /// Dòng 2 ///
        // thêm nhãn số dư của tài khoản được khởi tạo
        amountLabel.setFont(new Font(null, Font.BOLD, 15));
        amountLabel.setBounds(0, 51, 800, 30);
        amountLabel.setBackground(Color.PINK);
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setVerticalAlignment(SwingConstants.CENTER);
        amountLabel.setOpaque(true);
        this.add(amountLabel);

        /// Dòng 3 ///
        // nút mô phỏng bất đồng bộ
        nonSyncBtn.setBounds(0, 81, 200, 30);
        nonSyncBtn.setFocusPainted(false);
        nonSyncBtn.addActionListener(this);
        this.add(nonSyncBtn);

        // nút mô phỏng đồng bộ mutual exclusive
        mutualExclusiveBtn.setBounds(201, 81, 200, 30);
        mutualExclusiveBtn.setFocusPainted(false);
        mutualExclusiveBtn.addActionListener(this);
        this.add(mutualExclusiveBtn);

        // nút mô phỏng đồng bộ cooperation
        cooperationBtn.setBounds(401, 81, 200, 30);
        cooperationBtn.setFocusPainted(false);
        cooperationBtn.addActionListener(this);
        this.add(cooperationBtn);

        // nút mô phỏng deadlock
        deadLockBtn.setBounds(601, 81, 200, 30);
        deadLockBtn.setFocusPainted(false);
        deadLockBtn.addActionListener(this);
        this.add(deadLockBtn);

        /// Dòng 4 ///
        // nút dừng luồng
        stopThreadBtn.setBounds(0, 111, 200, 30);
        stopThreadBtn.setFocusPainted(false);
        stopThreadBtn.addActionListener(this);
        this.add(stopThreadBtn);

        // nút xem trạng thái luồng
        statusThreadBtn.setBounds(201, 111, 200, 30);
        statusThreadBtn.setFocusPainted(false);
        statusThreadBtn.addActionListener(this);
        this.add(statusThreadBtn);

        // nút mô phỏng giải quyết deadlock
        solveDeadlockBtn.setBounds(401, 111, 200, 30);
        solveDeadlockBtn.setFocusPainted(false);
        solveDeadlockBtn.addActionListener(this);
        this.add(solveDeadlockBtn);


        /// Dòng 5 ///
        // dòng nhập khởi tạo số dư tài khoản
        initAmount.setBounds(0, 141, 200, 30);
        initAmount.setHorizontalAlignment(SwingConstants.CENTER);
        initAmount.setVerticalAlignment(SwingConstants.CENTER);
        this.add(initAmount);
        initAmountTF.setBounds(201, 141, 200, 30);
        initAmountTF.setText("10000000");
        this.add(initAmountTF);
        // dòng nhập giả định số tiền USER2 sẽ chuyển đi cho USER3
        // trong việc mô phỏng deadlock và giải quyết deadlock
        user2Transfer.setBounds(401, 141, 200, 30);
        user2Transfer.setHorizontalAlignment(SwingConstants.CENTER);
        user2Transfer.setVerticalAlignment(SwingConstants.CENTER);
        this.add(user2Transfer);
        user2TransferTF.setBounds(601, 141, 200, 30);
        user2TransferTF.setText("10000000");
        this.add(user2TransferTF);

        /// Dòng 6 ///
        // dòng nhập giả định số tiền USER1 sẽ rút trong bất đồng bộ
        // đồng bộ mutual exclusion và cooperation
        user1Withdraw.setBounds(0, 171, 200, 30);
        user1Withdraw.setHorizontalAlignment(SwingConstants.CENTER);
        user1Withdraw.setVerticalAlignment(SwingConstants.CENTER);
        this.add(user1Withdraw);
        user1WithdrawTF.setBounds(201, 171, 200, 30);
        user1WithdrawTF.setText("8000000");
        this.add(user1WithdrawTF);

        // dòng nhập giả định số tiền USER2 sẽ rút trong bất đồng bộ
        // và đồng bộ mutual exclusion
        user2Withdraw.setBounds(401, 171, 200, 30);
        user2Withdraw.setHorizontalAlignment(SwingConstants.CENTER);
        user2Withdraw.setVerticalAlignment(SwingConstants.CENTER);
        this.add(user2Withdraw);
        user2WithdrawTF.setBounds(601, 171, 200, 30);
        user2WithdrawTF.setText("10000000");
        this.add(user2WithdrawTF);

        // dòng nhập giả định số tiền USER3 sẽ chuyển đi cho USER2
        // trong việc mô phỏng deadlock và giải quyết deadlock
        user3Transfer.setBounds(0, 201, 200, 30);
        user3Transfer.setHorizontalAlignment(SwingConstants.CENTER);
        user3Transfer.setVerticalAlignment(SwingConstants.CENTER);
        this.add(user3Transfer);
        user3TransferTF.setBounds(201, 201, 200, 30);
        user3TransferTF.setText("3000000");
        this.add(user3TransferTF);

        // dòng nhập giả định số tiền USER2 sẽ nạp vào tài khoản
        // trong việc mô phỏng cooperation
        user2Deposit.setBounds(401, 201, 200, 30);
        user2Deposit.setHorizontalAlignment(SwingConstants.CENTER);
        user2Deposit.setVerticalAlignment(SwingConstants.CENTER);
        this.add(user2Deposit);
        user2DepositTF.setBounds(601, 201, 200, 30);
        user2DepositTF.setText("5000000");
        this.add(user2DepositTF);

        // vùng hiển thị các trạng thái
        notificationArea.setBounds(0, 261, 800, 540);
        notificationArea.setFont(new Font(null, Font.BOLD, 15));
        notificationArea.setEditable(false);
        this.add(notificationArea);
    }

    private BankAccount bankAccount1;
    private BankAccount bankAccount2;

    private Thread thread1 = null;
    private Thread thread2 = null;

    @Override
    public void actionPerformed(ActionEvent e) {

        long amount;
        long user1Withdraw;
        long user2Withdraw;
        long user2Deposit ;
        long user2Transfer;
        long user3Transfer;
        try {
            amount = Long.parseLong(initAmountTF.getText());
            user1Withdraw = Long.parseLong(user1WithdrawTF.getText());
            user2Withdraw = Long.parseLong(user2WithdrawTF.getText());
            user2Deposit = Long.parseLong(user2DepositTF.getText());
            user2Transfer = Long.parseLong(user2TransferTF.getText());
            user3Transfer = Long.parseLong(user3TransferTF.getText());
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this,
                    "Bạn cần nhập thông số hợp lệ!",
                    "Cảnh bảo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }


        // nếu nút này được chọn thì mô phỏng bất đồng bộ
        if (e.getSource() == nonSyncBtn) {
            // xóa các trạng thái trong vùng trạng thái
            this.notificationArea.setText("[MÔ PHỎNG RÚT TIỀN BẤT ĐỒNG BỘ]\n");

            // gán số dư vào giao diên
            amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
            // trong mô phỏng này chỉ sử dụng 1 tài khoản ngân hàng
            // nên chỉ khởi tạo bankAccount1
            bankAccount1 = new BankAccount(amount, "1", notificationArea, amountLabel);

            // khởi tạo hai luồng cùng thực hiện rút tiền bất đồng bộ vào 1 tài khoản
            thread1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.withdrawWithoutSync("USER1", user1Withdraw);
                }
            };

            thread2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.withdrawWithoutSync("USER2", user2Withdraw);
                }
            };

            thread1.start();
            thread2.start();

        }

        // nếu nút này được chọn thì mô phỏng đồng bộ cooperation
        if (e.getSource() == cooperationBtn) {
            // xóa các trạng thái trong vùng trạng thái
            this.notificationArea.setText("[MÔ PHỎNG ĐỒNG BỘ COOPERATION]\n");

            // gán số dư vào giao diên
            amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
            // trong mô phỏng này chỉ sử dụng 1 tài khoản ngân hàng
            // nên chỉ khởi tạo bankAccount1
            bankAccount1 = new BankAccount(amount, "1", notificationArea, amountLabel);
            // khởi tạo hai luồng
            // luồng thứ nhất đặt lệnh thanh toán khi đủ số dư
            // luồng thứ hai nạp tiền vào tài khoản
            thread1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.payWhenBalanceEnoughWithSync("USER1", user1Withdraw);
                }
            };
            thread2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.depositWithSync("USER2", user2Deposit);
                }
            };

            thread1.start();
            thread2.start();
        }

        // nếu nút này được chọn thì mô phỏng deadlock
        if (e.getSource() == deadLockBtn) {
            // xóa các trạng thái trong vùng trạng thái
            this.notificationArea.setText("[MÔ PHỎNG XẢY RA DEADLOCK]\n");

            // gán số dư vào giao diên
            amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
            // khởi tạo 2 tài khoản ngân hàng để mô phỏng việc cả 2 đồng thời
            // chuyển tiền cho nhau xảy ra deadlock
            bankAccount1 = new BankAccount(amount, "1", notificationArea, amountLabel);
            bankAccount2 = new BankAccount(amount, "2", notificationArea, null);
            // khởi tạo 2 luồng để 2 tài khoản chuyển tiền cho nhau
            thread1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.deadlockTransfer(bankAccount2, user2Transfer, "USER2");
                }
            };

            thread2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount2.deadlockTransfer(bankAccount1, user3Transfer, "USER3");
                }
            };

            thread1.start();
            thread2.start();
        }

        // nếu nút này được chọn thì dừng luồng
        // cảnh báo: chức năng này không thực sự dừng các luồng đang chạy
        // nó chỉ hủy tham chiếu của 2 biến đang tham chiếu tới luồng đang chạy
        // thực tế thì luồng chỉ thực sự bị ngắt nếu thoát chương trình
        if (e.getSource() == stopThreadBtn) {
            // nếu chưa có tham chiếu nào thì thông báo ra màn hình
            if (thread1 == null && thread2 == null) {
                JOptionPane.showMessageDialog(this,
                        "Chưa khỏi tạo luồng!",
                        "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            // xóa các thông tin trạng thái
            this.notificationArea.setText("");
            // hủy tham chiếu 2 luồng
            thread2 = null;
            thread1 = null;
        }


        // nếu nút này được chọn thì mô phỏng đồng bộ mutual exclusive
        if (e.getSource() == mutualExclusiveBtn) {
            // xóa các trạng thái trong vùng trạng thái
            this.notificationArea.setText("[MÔ PHỎNG ĐỒNG BỘ MUTUAL EXCLUSIVE]\n");
            // gán số dư vào giao diên
            amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
            // trong mô phỏng này chỉ sử dụng 1 tài khoản ngân hàng
            // nên chỉ khởi tạo bankAccount1
            bankAccount1 = new BankAccount(amount, "1", notificationArea, amountLabel);
            // tạo 2 luồng cùng thực hiện rút tiền khỏi tài khoản bằng cách đồng bộ mutual exclusive
            thread1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.withdrawWithSync("USER1", user1Withdraw);
                }
            };

            thread2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.withdrawWithSync("USER2", user2Withdraw);
                }
            };

            thread1.start();
            thread2.start();

        }


        // nếu nút này được chọn thì mô phỏng giải quyết deadlock
        if (e.getSource() == solveDeadlockBtn) {
            // xóa các trạng thái trong vùng trạng thái
            this.notificationArea.setText("[MÔ PHỎNG GIẢI QUYẾT DEADLOCK]\n");

            // gán số dư vào giao diên
            amountLabel.setText(MessageFormat.format("Số dư: {0}", amount));
            // khởi tạo 2 tài khoản ngân hàng để mô phỏng việc cả 2 đồng thời
            bankAccount1 = new BankAccount(amount, "1", notificationArea, amountLabel);
            bankAccount2 = new BankAccount(amount, "2", notificationArea, null);
            // khởi tạo 2 luồng để 2 tài khoản chuyển tiền cho nhau
            thread1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount1.deadlockSolvedTransfer(bankAccount2, user2Transfer, "USER2");
                }
            };

            thread2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    bankAccount2.deadlockSolvedTransfer(bankAccount1, user3Transfer, "USER3");
                }
            };

            thread1.start();
            thread2.start();
        }


        // nếu nút này được chọn thì hiện thị trạng thái các luồng
        if (e.getSource() == statusThreadBtn) {
            if (thread1 == null && thread2 == null) {
                JOptionPane.showMessageDialog(this,
                        "Chưa khỏi tạo luồng!",
                        "Cảnh bảo",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            assert thread1 != null;
            if (thread1.isAlive() && thread2.isAlive()) {
                JOptionPane.showMessageDialog(this,
                        "Cả 2 luồng đang chạy!",
                        "Cảnh bảo",
                        JOptionPane.WARNING_MESSAGE);

            } else if (thread1.isAlive()) {
                JOptionPane.showMessageDialog(this,
                        "Luồng 1 đang chạy!",
                        "Cảnh bảo",
                        JOptionPane.WARNING_MESSAGE);
            } else if (thread2.isAlive()) {
                JOptionPane.showMessageDialog(this,
                        "Luồng 2 đang chạy!",
                        "Cảnh bảo",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Không có luồng nào đang chạy!",
                        "Cảnh bảo",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

    }

}
