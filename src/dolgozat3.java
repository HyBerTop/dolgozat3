import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class dolgozat3 extends JFrame {

    private JTextArea textArea;

    public dolgozat3() {
        setTitle("Dolgozat 3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Serif", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        loadFile("berkft.txt");
    }

    private void loadFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            List<String> categories = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 5) {
                    String nev = parts[0];
                    String telepules = parts[1];
                    String cim = parts[2];
                    String szuletes = parts[3];
                    String fizetes = parts[4];

                    String category = "Név: " + nev + "\n"
                            + "Település: " + telepules + "\n"
                            + "Cím: " + cim + "\n"
                            + "Születési dátum: " + szuletes + "\n"
                            + "Fizetés: " + fizetes + " -Ft" + "\n";

                    categories.add(category);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (String category : categories) {
                sb.append(category).append("\n");
            }
            textArea.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            dolgozat3 viewer = new dolgozat3();
            viewer.setVisible(true);
        });
    }
}