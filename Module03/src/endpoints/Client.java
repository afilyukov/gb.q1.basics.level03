import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.ListIterator;

public class Client extends JFrame {
    private JTextArea chatArea;
    private JTextField msgInputField;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private FileHandler fh;
    private String login;
    private String password;
    private String nickName;


    public Client(String login, String password, String nickName) {
        this.login = login;
        this.password = password;
        this.nickName = nickName;
        drawGUI();
        fh = new FileHandler(nickName);
        try {
            open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void open() throws IOException {
        Socket socket = new Socket("localhost", Server1.PORT);
        System.out.println("Connected to server: " + socket);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loadHistory();
                    out.writeUTF("/auth " + login + " " + password);
                    while (true) {String message = in.readUTF();
                        if (message.startsWith("/authok")) {
                            chatArea.append("***Service message: You are authorized!***");
                            chatArea.append("\n");
                            String[] reply = message.split("\\s");
                            setTitle(reply[1]);
                            break;
                        }
                    }
                    while (true) {
                        try {
                            String message = in.readUTF();
                            if (message.equals("/end")) {
                                chatArea.append("***Service message: Session closed. Cau!***");
                                chatArea.append("\n");
                                break;
                            }
                            chatArea.append(message);
                            chatArea.append("\n");
                            savetoHistory(message + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }

            }
        });
        thread.start();
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savetoHistory(String message){
        try
            {
                fh.appendToFile(message);
            } catch (Exception e) {
                    chatArea.append("***Service message: Cannot save history***");
                    e.printStackTrace();
            }
    }

    public void loadHistory(){
        java.util.List<String> history;
        try {
            history = fh.readFromFile(nickName);
            if (!history.isEmpty()) {
                int start = history.size() - 100;
                if (start<0){ start = 0;}
                ListIterator<String> listIterator = history.listIterator(start);
                while(listIterator.hasNext()) {
                    chatArea.append(listIterator.next());
                    chatArea.append("\n");
                }
            }
        } catch (Exception e) {
            chatArea.append("Cannot read history from file\n");
        }
    }

    public void drawGUI() {
        setBounds(600, 300, 500, 500);
        setTitle(nickName);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Отправить");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputField = new JTextField();
        msgInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputField, BorderLayout.CENTER);
        btnSendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setVisible(true);
    }

    public void sendMessage() {
        if (!msgInputField.getText().trim().isEmpty()) {
            try {
                out.writeUTF(msgInputField.getText());
                msgInputField.setText("");
                msgInputField.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
            }
        }
    }

}
