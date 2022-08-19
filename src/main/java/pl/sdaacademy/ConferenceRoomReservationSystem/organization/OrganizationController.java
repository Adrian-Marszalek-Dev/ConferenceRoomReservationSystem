package pl.sdaacademy.ConferenceRoomReservationSystem.organization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/organizations")
class OrganizationController {

    private final OrganizationService organizationService;

    OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @GetMapping
    List<Organization> getAll(){
        return organizationService.getAllOrganizations();
    }

    @PostMapping
    Organization add(@Validated(value = AddOrganization.class) @RequestBody Organization organization){
        return organizationService.addOrganization(organization);
    }

    @PutMapping("/{name}")
    Organization update(@PathVariable String name, @Validated(value = UpdateOrganization.class) @RequestBody Organization organization){
        return organizationService.updateOrganization(name, organization);
    }

    @DeleteMapping("/{name}")
    Organization delete(@PathVariable String name){
        return organizationService.deleteOrganization(name);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
