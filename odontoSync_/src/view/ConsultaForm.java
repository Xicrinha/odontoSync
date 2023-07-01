package view;

import controller.ConsultaController;
import enums.Genero;
import model.Consulta;
import validation.Validator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConsultaForm extends JFrame {
    private JTextField textFieldNome;
    private JTextField textFieldSobrenome;
    private JTextField textFieldDataNascimento;
    private JTextField textFieldEndereco;
    private JRadioButton radioButtonMasculino;
    private JRadioButton radioButtonFeminino;
    private JRadioButton radioButtonOutro;
    private JTextField textFieldData;
    private JTextField textFieldHora;

    private ButtonGroup buttonGroupGenero;

    public ConsultaForm() {
        setTitle("Cadastro de Consulta Odontológica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 350));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel labelNome = new JLabel("Nome:");
        textFieldNome = new JTextField();

        JLabel labelSobrenome = new JLabel("Sobrenome:");
        textFieldSobrenome = new JTextField();

        JLabel labelDataNascimento = new JLabel("Data de Nascimento:");
        textFieldDataNascimento = new JTextField();

        JLabel labelEndereco = new JLabel("Endereço:");
        textFieldEndereco = new JTextField();

        JLabel labelGenero = new JLabel("Gênero:");
        radioButtonMasculino = new JRadioButton("Masculino");
        radioButtonFeminino = new JRadioButton("Feminino");
        radioButtonOutro = new JRadioButton("Outro");
        buttonGroupGenero = new ButtonGroup();
        buttonGroupGenero.add(radioButtonMasculino);
        buttonGroupGenero.add(radioButtonFeminino);
        buttonGroupGenero.add(radioButtonOutro);

        JLabel labelData = new JLabel("Data:");
        textFieldData = new JTextField();

        JLabel labelHora = new JLabel("Hora:");
        textFieldHora = new JTextField();

        JButton buttonSalvar = new JButton("Salvar");
        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarConsulta();
            }
        });

        formPanel.add(labelNome);
        formPanel.add(textFieldNome);
        formPanel.add(labelSobrenome);
        formPanel.add(textFieldSobrenome);
        formPanel.add(labelDataNascimento);
        formPanel.add(textFieldDataNascimento);
        formPanel.add(labelEndereco);
        formPanel.add(textFieldEndereco);
        formPanel.add(labelGenero);
        formPanel.add(radioButtonMasculino);
        formPanel.add(new JLabel());
        formPanel.add(radioButtonFeminino);
        formPanel.add(new JLabel());
        formPanel.add(radioButtonOutro);
        formPanel.add(labelData);
        formPanel.add(textFieldData);
        formPanel.add(labelHora);
        formPanel.add(textFieldHora);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(buttonSalvar);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
        pack();
    }

    private void salvarConsulta() {
        String nome = textFieldNome.getText();
        String sobrenome = textFieldSobrenome.getText();
        String dataNascimento = textFieldDataNascimento.getText();
        String endereco = textFieldEndereco.getText();

        Genero genero = Genero.OUTRO;
        if (radioButtonMasculino.isSelected()) {
            genero = Genero.MASCULINO;
        } else if (radioButtonFeminino.isSelected()) {
            genero = Genero.FEMININO;
        }

        String data = textFieldData.getText();
        String hora = textFieldHora.getText();

        if (!Validator.validarCampoVazio(nome)) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o campo nome.");
            return;
        }

        if (!Validator.validarCampoVazio(sobrenome)) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o campo sobrenome.");
            return;
        }

        if (!Validator.validarData(dataNascimento)) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o campo data de nascimento.");
            return;
        }

        if (!Validator.validarCampoVazio(endereco)) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o campo endereço.");
            return;
        }

        if (!Validator.validarData(data)) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o campo data.");
            return;
        }

        if (!Validator.validarHorario(hora)) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o campo hora.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimentoLD = LocalDate.parse(dataNascimento,formatter);
        LocalDate dataLD = LocalDate.parse(data,formatter);
        LocalTime horaLT = LocalTime.parse(hora);

        ConsultaController consultaController = new ConsultaController();

        try {
            Consulta consulta = new Consulta(nome, sobrenome, dataNascimentoLD, endereco, genero, dataLD, horaLT);
            consultaController.salvarConsulta(consulta);

            JOptionPane.showMessageDialog(this, "Consulta salva com sucesso!");
            limparCampos();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar a consulta: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        textFieldNome.setText("");
        textFieldSobrenome.setText("");
        textFieldDataNascimento.setText("");
        textFieldEndereco.setText("");
        buttonGroupGenero.clearSelection();
        textFieldData.setText("");
        textFieldHora.setText("");
    }
}
