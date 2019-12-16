package pl.codeaddict.spockapidocs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BlogApiController {

    @PostMapping("/login")
    public ResponseEntity<Void> login(LoginRequest loginRequest) {
        return ResponseEntity.ok().header("Set-Auth-Token", "TOKEN").build();
    }

    @PostMapping("/post")
    public ResponseEntity<AddPostResponse> addPost(AddPostRequest addPostRequest) {
        return ResponseEntity.ok().body(new AddPostResponse("111-333"));
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<Void> modifyPost(@PathVariable String id, ModifyPostRequest modifyPostRequest) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<GetPostDetailsResponse> getPostDetails(@PathVariable String id) {
        return ResponseEntity.ok().body(new GetPostDetailsResponse("111-333","Blog post text"));
    }

}
