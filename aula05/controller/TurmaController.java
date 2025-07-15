package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import model.Repositorio;
import model.Turma;
import model.Disciplina;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

public class TurmaController extends AbstractCrudController<model.Turma, view.Turma, Integer> 
    implements Initializable, TurmaObserver.TurmaUpdateListener {

    @FXML
    private TableView<view.Turma> tabela;

    @FXML
    private TableColumn<view.Turma, Integer> idCol;
    @FXML
    private TableColumn<view.Turma, String> codigoCol;
    @FXML
    private TableColumn<view.Turma, String> disciplinaCol;
    @FXML
    private TableColumn<view.Turma, Integer> alunosMatriculadosCol;
    @FXML
    private TableColumn<view.Turma, Integer> vagasDisponiveisCol;

    @FXML
    private TextField idField;
    @FXML
    private TextField codigoField;
    @FXML
    private ComboBox<Disciplina> disciplinaComboBox;
    @FXML
    private Label alunosMatriculadosLabel;
    @FXML
    private TextField vagasDisponiveisField;

    @FXML
    private Button adicionarButton;
    @FXML
    private Button atualizarButton;
    @FXML
    private Button deletarButton;
    @FXML
    private Button cancelarButton;
    @FXML
    private Button salvarButton;

    private static final Repositorio<model.Turma, Integer> turmaRepo = model.Repositorios.TURMA;
    private static final Repositorio<model.Disciplina, Integer> disciplinaRepo = model.Repositorios.DISCIPLINA;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar colunas
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        disciplinaCol.setCellValueFactory(new PropertyValueFactory<>("disciplinaCodigo"));
        alunosMatriculadosCol.setCellValueFactory(new PropertyValueFactory<>("alunosMatriculados"));
        vagasDisponiveisCol.setCellValueFactory(new PropertyValueFactory<>("vagasDisponiveis"));

        // Carregar disciplinas no ComboBox
        List<Disciplina> disciplinas = disciplinaRepo.loadAll();
        disciplinaComboBox.setItems(FXCollections.observableArrayList(disciplinas));
        disciplinaComboBox.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(Disciplina d, boolean empty) {
                super.updateItem(d, empty);
                setText(empty || d == null ? "" : d.getCodigo());
            }
        });
        disciplinaComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Disciplina d, boolean empty) {
                super.updateItem(d, empty);
                setText(empty || d == null ? "" : d.getCodigo());
            }
        });

        super.initialize();
        
        // Registrar como listener para mudanças na turma
        TurmaObserver.addListener(this);
    }

    @Override
    protected Repositorio<model.Turma, Integer> getRepositorio() {
        return turmaRepo;
    }

    @Override
    protected view.Turma modelToView(model.Turma t) {
        // Calcular alunos matriculados dinamicamente
        int alunosMatriculados = calcularAlunosMatriculados(t.getId());
        t.setAlunosMatriculados(alunosMatriculados);
        
        return new view.Turma(
            t.getId(),
            t.getCodigo(),
            t.getDisciplina() != null ? t.getDisciplina().getId() : 0,
            t.getDisciplina() != null ? t.getDisciplina().getCodigo() : "",
            t.getVagasDisponiveis(),
            t.getAlunosMatriculados()
        );
    }

    /**
     * Calcula o número de alunos matriculados em uma turma
     * @param turmaId ID da turma
     * @return Número de alunos matriculados
     */
    private int calcularAlunosMatriculados(int turmaId) {
        try {
            List<model.Matricula> todasMatriculas = model.Repositorios.MATRICULA.loadAll();
            int count = 0;
            
            for (model.Matricula m : todasMatriculas) {
                if (m.getTurma() != null && m.getTurma().getId() == turmaId) {
                    // Conta apenas matrículas confirmadas ou solicitadas
                    if (m.getStatus() == model.StatusMatricula.CONFIRMADA || 
                        m.getStatus() == model.StatusMatricula.SOLICITADA) {
                        count++;
                    }
                }
            }
            
            return count;
        } catch (Exception e) {
            System.err.println("Erro ao calcular alunos matriculados para turma " + turmaId + ": " + e.getMessage());
            return 0;
        }
    }

    @Override
    protected model.Turma viewToModel() {
        model.Turma t = new model.Turma();
        t.setCodigo(codigoField.getText());
        t.setDisciplina(disciplinaComboBox.getValue());

        // Parsing seguro
        String vagasText = vagasDisponiveisField.getText();
        int vagas = 0;
        if (vagasText != null && !vagasText.isBlank()) {
            try {
                vagas = Integer.parseInt(vagasText);
            } catch (NumberFormatException ex) {
                vagas = 0;
            }
        }
        t.setVagasDisponiveis(vagas);

        // alunosMatriculados é derivado
        return t;
    }

    @Override
    protected void preencherCampos(view.Turma turma) {
        idField.setText(String.valueOf(turma.getId()));
        codigoField.setText(turma.getCodigo());
        alunosMatriculadosLabel.setText(String.valueOf(turma.getAlunosMatriculados()));
        vagasDisponiveisField.setText(String.valueOf(turma.getVagasDisponiveis()));

        // Selecionar disciplina por ID
        Disciplina d = disciplinaRepo.loadFromId(turma.getDisciplinaId());
        disciplinaComboBox.setValue(d);
    }

    @Override
    protected void limparCampos() {
        idField.clear();
        codigoField.clear();
        alunosMatriculadosLabel.setText("");
        vagasDisponiveisField.clear();
        disciplinaComboBox.setValue(null);
    }

    @Override
    protected void desabilitarCampos(boolean desabilitado) {
        codigoField.setDisable(desabilitado);
        disciplinaComboBox.setDisable(desabilitado);
        vagasDisponiveisField.setDisable(desabilitado);
        // Label alunosMatriculados é só leitura
    }

    @Override
    protected void desabilitarBotoes(boolean adicionar, boolean atualizar, boolean deletar, boolean cancelar, boolean salvar) {
        adicionarButton.setDisable(adicionar);
        atualizarButton.setDisable(atualizar);
        deletarButton.setDisable(deletar);
        cancelarButton.setDisable(cancelar);
        salvarButton.setDisable(salvar);
    }

    @Override
    protected TableView<view.Turma> getTabela() {
        return tabela;
    }

    @Override
    protected Integer getIdFromViewModel(view.Turma viewModel) {
        return viewModel.getId();
    }

    @Override
    protected void setIdOnEntity(model.Turma entidade, Integer id) {
        entidade.setId(id);
    }

    @FXML
    public void onAdicionar() {
        super.onAdicionar();
    }

    @FXML
    public void onSalvar() {
        super.onSalvar();
    }

    @FXML
    public void onAtualizar() {
        super.onAtualizar();
    }

    @FXML
    public void onDeletar() {
        super.onDeletar();
    }

    @FXML
    public void onCancelar() {
        super.onCancelar();
    }

    /**
     * Implementação do TurmaUpdateListener
     * Chamado quando uma turma é atualizada devido a mudanças nas matrículas
     * @param turmaId ID da turma que foi atualizada
     */
    @Override
    public void onTurmaUpdated(int turmaId) {
        // Recarregar dados da turma específica primeiro
        try {
            model.Turma turmaAtualizada = turmaRepo.loadFromId(turmaId);
            if (turmaAtualizada != null) {
                System.out.println("Atualizando turma " + turmaId + " com " + turmaAtualizada.getAlunosMatriculados() + " alunos");
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar turma " + turmaId + ": " + e.getMessage());
        }
        
        // Recarregar todos os dados da tabela para refletir as mudanças
        getTabela().setItems(loadAll());
        getTabela().refresh();
        
        // Forçar atualização da interface
        javafx.application.Platform.runLater(() -> {
            getTabela().refresh();
        });
        
        System.out.println("Tabela de turmas atualizada devido a mudanças na turma " + turmaId);
    }
}
