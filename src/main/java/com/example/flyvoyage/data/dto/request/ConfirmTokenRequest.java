package com.example.flyvoyage.data.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
public record ConfirmTokenRequest(@NotNull String token, @NotNull String emailAddress) {
}
