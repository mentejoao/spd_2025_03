package controller;

import java.util.List;
import java.util.ArrayList;

/**
 * Observer para notificar mudanças em turmas.
 * Implementa o padrão Observer para comunicar automaticamente
 * quando uma turma precisa ser atualizada.
 */
public class TurmaObserver {
    
    private static List<TurmaUpdateListener> listeners = new ArrayList<>();
    private static List<EstudanteUpdateListener> estudanteListeners = new ArrayList<>();
    
    /**
     * Interface para ouvintes de mudanças na turma
     */
    public interface TurmaUpdateListener {
        void onTurmaUpdated(int turmaId);
    }
    
    /**
     * Interface para ouvintes de mudanças em estudantes
     */
    public interface EstudanteUpdateListener {
        void onEstudanteCreated();
        void onEstudanteUpdated();
        void onEstudanteDeleted();
    }
    
    /**
     * Adiciona um listener para ser notificado das mudanças
     * @param listener O listener a ser adicionado
     */
    public static void addListener(TurmaUpdateListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Remove um listener
     * @param listener O listener a ser removido
     */
    public static void removeListener(TurmaUpdateListener listener) {
        listeners.remove(listener);
    }
    
    /**
     * Adiciona um listener para mudanças em estudantes
     * @param listener O listener a ser adicionado
     */
    public static void addEstudanteListener(EstudanteUpdateListener listener) {
        estudanteListeners.add(listener);
    }
    
    /**
     * Remove um listener de estudantes
     * @param listener O listener a ser removido
     */
    public static void removeEstudanteListener(EstudanteUpdateListener listener) {
        estudanteListeners.remove(listener);
    }
    
    /**
     * Notifica todos os listeners sobre mudanças na turma
     * @param turmaId ID da turma que foi atualizada
     */
    public static void notifyTurmaUpdated(int turmaId) {
        for (TurmaUpdateListener listener : listeners) {
            listener.onTurmaUpdated(turmaId);
        }
    }
    
    /**
     * Notifica criação de novo estudante
     */
    public static void notifyEstudanteCreated() {
        for (EstudanteUpdateListener listener : estudanteListeners) {
            listener.onEstudanteCreated();
        }
    }
    
    /**
     * Notifica atualização de estudante
     */
    public static void notifyEstudanteUpdated() {
        for (EstudanteUpdateListener listener : estudanteListeners) {
            listener.onEstudanteUpdated();
        }
    }
    
    /**
     * Notifica deleção de estudante
     */
    public static void notifyEstudanteDeleted() {
        for (EstudanteUpdateListener listener : estudanteListeners) {
            listener.onEstudanteDeleted();
        }
    }
}
