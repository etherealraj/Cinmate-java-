import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Cinemate {
    public static void main(String[] args) {
        new MainDashboard();
    }
}

// 🎬 Main Dashboard with Movie Posters
class MainDashboard {
    JFrame frame;
    JPanel moviePanel;

    String[] movieNames = {"Movie 1", "Movie 2", "Movie 3", "Movie 4", "Movie 5", "Movie 6"};
    String[] posterPaths = {
        "assets/Movie1.jpg",
        "assets/Movie2.jpg",
        "assets/Movie3.jpg",
        "assets/Movie4.jpg",
        "assets/Movie5.jpg",
        "assets/Movie6.jpg"
    };

    public MainDashboard() {
        frame = new JFrame("Cinemate - Movie Reservation");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("🎥 Select a Movie", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        moviePanel = new JPanel(new GridLayout(2, 3, 10, 10));

        for (int i = 0; i < movieNames.length; i++) {
            final int index = i;
            ImageIcon icon = new ImageIcon(posterPaths[i]);
            Image img = icon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(img);

            JButton movieButton = new JButton(movieNames[i], resizedIcon);
            movieButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            movieButton.setHorizontalTextPosition(SwingConstants.CENTER);
            movieButton.setFont(new Font("Arial", Font.PLAIN, 16));

            movieButton.addActionListener(e -> {
                frame.dispose();
                new SeatSelectionScreen(movieNames[index]);
            });

            moviePanel.add(movieButton);
        }

        frame.add(moviePanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

// 🎟 Seat Selection Screen
class SeatSelectionScreen {
    JFrame frame;
    JPanel seatPanel;
    List<String> selectedSeats = new ArrayList<>();

    public SeatSelectionScreen(String movieName) {
        frame = new JFrame("Cinemate - Seat Selection");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Select Seats for " + movieName, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(title, BorderLayout.NORTH);

        seatPanel = new JPanel(new GridLayout(5, 5, 10, 10));

        for (int i = 1; i <= 25; i++) {
            JButton seat = new JButton("S" + i);
            seat.setBackground(Color.LIGHT_GRAY);
            seat.setFont(new Font("Arial", Font.BOLD, 14));

            seat.addActionListener(e -> toggleSeat(seat));

            seatPanel.add(seat);
        }

        frame.add(seatPanel, BorderLayout.CENTER);

        JButton proceedButton = new JButton("Proceed to Payment");
        proceedButton.setFont(new Font("Arial", Font.BOLD, 16));
        proceedButton.setBackground(Color.GREEN);
        proceedButton.setForeground(Color.WHITE);

        proceedButton.addActionListener(e -> {
            if (selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please select at least one seat.");
            } else {
                frame.dispose();
                new PaymentScreen(selectedSeats);
            }
        });

        frame.add(proceedButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void toggleSeat(JButton seat) {
        String seatNumber = seat.getText();
        if (selectedSeats.contains(seatNumber)) {
            selectedSeats.remove(seatNumber);
            seat.setBackground(Color.LIGHT_GRAY);
        } else {
            selectedSeats.add(seatNumber);
            seat.setBackground(Color.GREEN);
        }
    }
}

// 💳 Payment Screen
class PaymentScreen {
    JFrame frame;

    public PaymentScreen(List<String> selectedSeats) {
        frame = new JFrame("Cinemate - Payment");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));

        JLabel detailsLabel = new JLabel("Seats: " + selectedSeats, SwingConstants.CENTER);
        frame.add(detailsLabel);

        String[] paymentMethods = {"Credit/Debit Card", "UPI", "Net Banking"};
        JComboBox<String> paymentDropdown = new JComboBox<>(paymentMethods);
        frame.add(paymentDropdown);

        JButton payButton = new JButton("Pay Now");
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.setBackground(Color.BLUE);
        payButton.setForeground(Color.WHITE);

        payButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Payment Successful!");
            frame.dispose();
        });

        frame.add(payButton);
        frame.setVisible(true);
    }
}
