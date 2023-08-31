package br.com.fiap.sistemadeestoque.models;

public record Token(
    String token,
    String type,
    String prefix
) {}