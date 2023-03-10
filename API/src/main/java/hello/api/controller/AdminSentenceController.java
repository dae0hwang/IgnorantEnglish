package hello.api.controller;

import hello.api.dto.AdminSentenceDto;
import hello.api.dto.AdminSentenceRequest;
import hello.api.dto.AdminSentenceSuccess;
import hello.api.service.AdminSentenceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/sentence")
public class AdminSentenceController {

    private final AdminSentenceService adminSentenceService;

    @PostMapping("/add")
    public ResponseEntity<AdminSentenceSuccess> addSentence(
        @RequestBody @Validated AdminSentenceRequest request) {
        adminSentenceService.saveSentence(request.getKorean(), request.getEnglish(),
            request.getGrammar(), request.getSituation());
        AdminSentenceSuccess success = new AdminSentenceSuccess(201, null, null, null, null);
        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }

    @GetMapping("/selection")
    public ResponseEntity<AdminSentenceSuccess> findSelectValue() {
        List<String> grammarValues = adminSentenceService.findGrammarValues();
        List<String> situationValues = adminSentenceService.findSituationValues();
        AdminSentenceSuccess success = new AdminSentenceSuccess(200, null, null, grammarValues,
            situationValues);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<AdminSentenceSuccess> findAll() {
        List<AdminSentenceDto> dtoList = adminSentenceService.findAll();
        AdminSentenceSuccess success = new AdminSentenceSuccess(200, null, dtoList, null, null);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AdminSentenceSuccess> delete(@PathVariable("id") Long id) {
        adminSentenceService.delete(id);
        AdminSentenceSuccess success = new AdminSentenceSuccess(200, null, null, null, null);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AdminSentenceSuccess> find(@PathVariable("id") Long id) {
        AdminSentenceDto adminSentenceDto = adminSentenceService.findById(id);
        AdminSentenceSuccess success = new AdminSentenceSuccess(200, adminSentenceDto, null, null,
            null);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdminSentenceSuccess> update(@PathVariable("id") Long id,
        @RequestBody@Validated AdminSentenceRequest request) {
        adminSentenceService.update(id,request.getKorean(), request.getEnglish(),
            request.getGrammar(), request.getSituation());
        AdminSentenceSuccess success = new AdminSentenceSuccess(200, null, null, null,
            null);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}
