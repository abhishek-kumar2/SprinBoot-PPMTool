package io.kanban.board.ppmtool.web;

import io.kanban.board.ppmtool.domain.Project;
import io.kanban.board.ppmtool.services.MapValidationErrorService;
import io.kanban.board.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    //public ResponseEntity<Project> createNewProject(@Valid @RequestBody Project project, BindingResult result){
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){ /* in case of has error check response entity is string so here use '?' to make generic */
        /*if(result.hasErrors()){
            //return new ResponseEntity<String>("Invalid project object", HttpStatus.BAD_REQUEST);
            //return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);

            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error: result.getFieldErrors()){ // these stuffs put in MapValidationErrorService 
                System.out.println("project =>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + project + ", result = " + result);
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }*/

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
