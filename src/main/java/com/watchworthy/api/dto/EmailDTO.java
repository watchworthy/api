package com.watchworthy.api.dto;

import com.watchworthy.api.model.EmailType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String recipientEmail;
    private String recipientName;
    private EmailType emailType;
}
