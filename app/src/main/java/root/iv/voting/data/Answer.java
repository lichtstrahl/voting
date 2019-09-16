package root.iv.voting.data;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Answer {
    @SerializedName("id")
    private Long id;
    @SerializedName("content")
    private String content;
}
