import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizFrame extends JFrame implements ActionListener {

    JLabel questionLabel;
    JLabel timerLabel;

    JRadioButton option1, option2, option3, option4;

    ButtonGroup bg;

    JButton nextButton;

    Timer timer;

    int timeLeft = 15;

    int score = 0;
    int currentQuestion = 0;

    QuestionData data = new QuestionData();

    QuizFrame() {

        setTitle("Deepika Online Quiz Application");

        setSize(600, 400);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.DARK_GRAY);

        questionLabel = new JLabel();

        questionLabel.setBounds(50, 50, 500, 30);

        questionLabel.setForeground(Color.WHITE);

        add(questionLabel);

        timerLabel = new JLabel("Time Left: 15");

        timerLabel.setBounds(400, 20, 150, 30);

        timerLabel.setForeground(Color.WHITE);

        add(timerLabel);

        option1 = new JRadioButton();

        option1.setBounds(50, 100, 200, 30);

        option1.setForeground(Color.WHITE);

        option1.setBackground(Color.DARK_GRAY);

        add(option1);

        option2 = new JRadioButton();

        option2.setBounds(50, 140, 200, 30);

        option2.setForeground(Color.WHITE);

        option2.setBackground(Color.DARK_GRAY);

        add(option2);

        option3 = new JRadioButton();

        option3.setBounds(50, 180, 200, 30);

        option3.setForeground(Color.WHITE);

        option3.setBackground(Color.DARK_GRAY);

        add(option3);

        option4 = new JRadioButton();

        option4.setBounds(50, 220, 200, 30);

        option4.setForeground(Color.WHITE);

        option4.setBackground(Color.DARK_GRAY);

        add(option4);

        bg = new ButtonGroup();

        bg.add(option1);

        bg.add(option2);

        bg.add(option3);

        bg.add(option4);

        nextButton = new JButton("Next");

        nextButton.setBounds(250, 300, 100, 30);

        nextButton.addActionListener(this);

        add(nextButton);

        loadQuestion(currentQuestion);

        startTimer();

        setVisible(true);
    }

    void loadQuestion(int index) {

        questionLabel.setText((index + 1) + ". " + data.questions[index][0]);

        option1.setText(data.questions[index][1]);

        option2.setText(data.questions[index][2]);

        option3.setText(data.questions[index][3]);

        option4.setText(data.questions[index][4]);
    }

    void startTimer() {

        timeLeft = 15;

        timerLabel.setText("Time Left: " + timeLeft);

        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                timeLeft--;

                timerLabel.setText("Time Left: " + timeLeft);

                if(timeLeft <= 0) {

                    timer.stop();

                    currentQuestion++;

                    bg.clearSelection();

                    if(currentQuestion < data.questions.length) {

                        loadQuestion(currentQuestion);

                        startTimer();

                    } else {

                        JOptionPane.showMessageDialog(null,
                                "Quiz Completed!\nYour Score: " + score);

                        System.exit(0);
                    }
                }
            }
        });

        timer.start();
    }

    public void actionPerformed(ActionEvent e) {

        timer.stop();

        String selectedAnswer = "";

        if(option1.isSelected())
            selectedAnswer = option1.getText();

        else if(option2.isSelected())
            selectedAnswer = option2.getText();

        else if(option3.isSelected())
            selectedAnswer = option3.getText();

        else if(option4.isSelected())
            selectedAnswer = option4.getText();

        if(selectedAnswer.equals(data.answers[currentQuestion])) {

            score += 10;
        }

        currentQuestion++;

        bg.clearSelection();

        if(currentQuestion < data.questions.length) {

            loadQuestion(currentQuestion);

            startTimer();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Quiz Completed!\nYour Score: " + score);

            System.exit(0);
        }
    }
}
