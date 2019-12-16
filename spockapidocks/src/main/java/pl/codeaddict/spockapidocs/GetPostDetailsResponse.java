package pl.codeaddict.spockapidocs;

import lombok.Value;

@Value
class GetPostDetailsResponse {
    private String id;
    private String text;
}
