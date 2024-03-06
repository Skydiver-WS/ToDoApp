package com.example.todoapp.web.request.note;

import com.example.todoapp.config.anotations.valid.ValidVisibly;
import com.example.todoapp.config.enums.Tag;
import com.example.todoapp.config.anotations.valid.ValidTag;
import com.example.todoapp.entity.CheckListItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на обновление записи.")
public class UpdateNoteRequest {
    @Schema(description = "Уникальный заголовок записи.", example = "This is BAD news!!!")
    @Size(min = 1, max = 30)

    private String title;
    @Schema(description = "Описание записи.", example = "VERY GOOD")
    private String description;

    @Schema(description = "Видимость.", example = "false")
    @ValidVisibly
    @NotNull
    private Boolean visibility;

    @Schema(description = "Тег", example = "ARCHIVE")
    @ValidTag
    @NotNull
    private Tag tag;

    @Schema(description = "Чек лист", example = " checkListItems : [\n\titem : Case1,\n\titem : Case2, \n\titem : Case3\n]")
    private List<CheckListItem> checkListItems;
}
