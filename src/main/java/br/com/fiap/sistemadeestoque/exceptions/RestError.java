package br.com.fiap.sistemadeestoque.exceptions;

public record RestError (
    int cod,
    String message
){}
