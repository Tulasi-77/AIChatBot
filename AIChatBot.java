import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class AIChatBot extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private Map<String, String> faqMap;

    // Constructor
    public AIChatBot() {

        // FAQ Training Data
        faqMap = new HashMap<>();

        faqMap.put("what is ai", "Artificial Intelligence enables machines to think and learn.");
        faqMap.put("what is java", "Java is a powerful object-oriented programming language.");
        faqMap.put("who developed java", "Java was developed by Sun Microsystems.");
        faqMap.put("what is chatbot", "A chatbot is a software application that interacts with users.");
        faqMap.put("what is nlp", "NLP stands for Natural Language Processing.");
        faqMap.put("hello", "Hello! How can I help you?");
        faqMap.put("hi", "Hi there!");
        faqMap.put("how are you", "I am functioning perfectly!");
        faqMap.put("bye", "Goodbye! Have a great day.");

        // GUI Setup
        setTitle("AI ChatBot");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Chat Area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 16));
        chatArea.setBackground(Color.BLACK);
        chatArea.setForeground(Color.GREEN);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Input Field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));

        // Send Button
        sendButton = new JButton("Send");
        sendButton.setBackground(Color.BLUE);
        sendButton.setForeground(Color.WHITE);

        // Bottom Panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        // Add Components
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Button Action
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Enter Key Action
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Initial Message
        botReply("Hello! I am your AI ChatBot.");

        setVisible(true);
    }

    // Send Message
    private void sendMessage() {

        String userInput = inputField.getText().trim();

        if (userInput.isEmpty()) {
            return;
        }

        // Display User Message
        chatArea.append("You: " + userInput + "\n");

        // Process Input using NLP-like preprocessing
        String processedInput = processInput(userInput);

        // Generate Bot Response
        String response = generateResponse(processedInput);

        // Display Bot Response
        botReply(response);

        // Clear Input
        inputField.setText("");
    }

    // Bot Reply
    private void botReply(String message) {
        chatArea.append("Bot: " + message + "\n\n");
    }

    // NLP Processing
    private String processInput(String input) {

        input = input.toLowerCase();

        input = input.replaceAll("[^a-zA-Z0-9 ]", "");

        input = input.trim();

        return input;
    }

    // AI Response Logic
    private String generateResponse(String input) {

        // FAQ Matching
        for (String key : faqMap.keySet()) {

            if (input.contains(key)) {
                return faqMap.get(key);
            }
        }

        // Rule-Based AI Responses
        if (input.contains("time")) {
            return "I cannot access live time, but you can check your system clock.";
        }

        if (input.contains("weather")) {
            return "I cannot access live weather data currently.";
        }

        if (input.contains("college")) {
            return "Please tell me your college name.";
        }

        if (input.contains("project")) {
            return "This is an AI ChatBot project developed in Java.";
        }

        if (input.contains("machine learning")) {
            return "Machine Learning allows systems to learn automatically from data.";
        }

        if (input.contains("artificial intelligence")) {
            return "Artificial Intelligence simulates human intelligence using machines.";
        }

        // Default Response
        return "Sorry, I don't understand. Please try another question.";
    }

    // Main Method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AIChatBot();
            }
        });
    }
}