package hexlet.code.controller;

import hexlet.code.dto.UserRequestDTO;
import hexlet.code.dto.UserResponseDTO;
import hexlet.code.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "@userService.findById(#id).getEmail() == authentication.name")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = userService.getAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(users.size()));
        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid UserRequestDTO userRequestDto) {
        return new ResponseEntity<>(userService.create(userRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "@userService.findById(#id).getEmail() == authentication.name")
    public ResponseEntity<UserResponseDTO> updateById(@PathVariable Long id,
                                                      @Valid @RequestBody UserRequestDTO userRequestDto) {
        return ResponseEntity.ok(userService.update(userRequestDto, id));
    }
}
