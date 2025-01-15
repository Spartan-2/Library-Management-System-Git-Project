import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class staffframe extends JFrame {
    private JTable booksTable;
    private DefaultTableModel tableModel;
    private JTextField titleField, authorField, priceField, stockField, bookIdField;

    public staffframe() {
        // Set up JFrame
        setTitle("Staff Panel");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Staff Panel - Manage Books", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Table to Display Books
        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Price", "Stock"}, 0);
        booksTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(booksTable);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel for Actions
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(5, 1));  // Adjust grid layout to accommodate the new button

        // Add Book Panel
        JPanel addPanel = new JPanel(new GridLayout(1, 5));
        titleField = new JTextField();
        authorField = new JTextField();
        priceField = new JTextField();
        stockField = new JTextField();
        JButton addButton = new JButton("Add Book");
        addPanel.add(new JLabel("Title:"));
        addPanel.add(titleField);
        addPanel.add(new JLabel("Author:"));
        addPanel.add(authorField);
        addPanel.add(new JLabel("Price:"));
        addPanel.add(priceField);
        addPanel.add(new JLabel("Stock:"));
        addPanel.add(stockField);
        addPanel.add(addButton);
        actionPanel.add(addPanel);

        // Delete Book Panel
        JPanel deletePanel = new JPanel(new GridLayout(1, 2));
        bookIdField = new JTextField();
        JButton deleteButton = new JButton("Delete Book");
        deletePanel.add(new JLabel("Book ID:"));
        deletePanel.add(bookIdField);
        deletePanel.add(deleteButton);
        actionPanel.add(deletePanel);

        // Show Receipt History Button
        JButton showReceiptsButton = new JButton("Show Receipt History");
        actionPanel.add(showReceiptsButton);

        // Logout Button
        JButton logoutButton = new JButton("Logout");
        actionPanel.add(logoutButton);

        add(actionPanel, BorderLayout.SOUTH);

        // Load Books Initially
        loadBooks();

        // Add Book Action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        // Delete Book Action
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        // Show Receipt History Action
        showReceiptsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReceiptHistoryFrame();  // Open ReceiptHistoryFrame when clicked
            }
        });
