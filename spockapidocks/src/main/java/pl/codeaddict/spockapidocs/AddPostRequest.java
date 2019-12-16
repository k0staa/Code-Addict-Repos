package pl.codeaddict.spockapidocs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
class AddPostRequest {
   private String text = null;
}
