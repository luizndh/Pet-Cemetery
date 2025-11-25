package com.petcemetery.petcemetery.core.constants;

/**
 * Constantes para validações usadas em toda a aplicação.
 * Centralizar constantes facilita manutenção e evita números mágicos.
 */
public final class ValidationConstants {

    private ValidationConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    // Jazigo
    public static final int MAX_MENSAGEM_JAZIGO_LENGTH = 80;

    // Reunião
    public static final int MIN_DIAS_ANTECEDENCIA_REUNIAO = 2;

    // Mensagens de erro
    public static final String ERRO_CAMPO_VAZIO = "O campo %s não pode estar vazio";
    public static final String ERRO_TAMANHO_INVALIDO = "O campo %s deve ter entre %d e %d caracteres";
    public static final String ERRO_DATA_PASSADO = "A data informada não pode ser no passado";
    public static final String ERRO_SENHA_ATUAL_INCORRETA = "Senha atual incorreta";
}
