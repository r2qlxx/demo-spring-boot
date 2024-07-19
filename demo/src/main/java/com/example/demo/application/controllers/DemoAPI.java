package com.example.demo.application.controllers;

import com.example.demo.application.resources.DemoReq;
import com.example.demo.application.resources.DemoRes;
import com.example.demo.domain.services.DemoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DemoAPI {
    private final DemoService demoService;

    // --------------------------
    // -- Basic http requests. --
    // --------------------------
    // GET    - retrieves data.
    // POST   - creates data.
    // PUT    - updates data entirely.
    // PATCH  - allows partially updating data.
    // DELETE - removes data.

    @GetMapping("/")
    // @PostMapping("/")
    // @PutMapping("/")
    // @PatchMapping("/")
    // @DeleteMapping("/")
    public ResponseEntity<DemoRes> http() {
        demoService.http();
        String message = demoService.doSomething();
        DemoRes demoRes = new DemoRes(message);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<DemoRes>(demoRes, headers, HttpStatus.OK);
    }

    // ------------------------
    // -- Parameter control. --
    // ------------------------
    @GetMapping("/rc")
    public ResponseEntity<DemoRes> reqParam(@RequestParam int id) {
        String message = demoService.doSomething(id);
        DemoRes demoRes = new DemoRes(message);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<DemoRes>(demoRes, headers, HttpStatus.OK);
    }

    @GetMapping("/rc/{id}")
    public ResponseEntity<DemoRes> pathVar(@PathVariable int id) {
        String message = demoService.doSomething(id);
        DemoRes demoRes = new DemoRes(message);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<DemoRes>(demoRes, headers, HttpStatus.OK);
    }

    @PostMapping("/rc")
    public ResponseEntity<DemoRes> reqMapping(DemoReq demoReq) {
        String message = demoService.doSomething(demoReq.getId());
        DemoRes demoRes = new DemoRes(message);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<DemoRes>(demoRes, headers, HttpStatus.OK);
    }

    // -------------------------
    // -- Validation Samples. --
    // -------------------------
    @GetMapping("/validation")
    public ResponseEntity<Void> reqParam(
            @RequestParam("id") @Min(0) @Max(1) int id,
            @RequestParam("name") @Size(min = 1, max = 5) String name) {
        // snip
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @GetMapping("/validation/{id}/{name}")
    public ResponseEntity<Void> pathVar(
            @PathVariable("id") @Min(0) @Max(1) int id,
            @PathVariable("name") @Size(min = 1, max = 5) String name) {
        // snip
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @PostMapping("/validation")
    public ResponseEntity<Void> doPost(@RequestBody @Valid DemoReq demoReq) {
        // snip
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }
}
