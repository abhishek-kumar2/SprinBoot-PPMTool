package io.kanban.board.ppmtool.web;

import io.kanban.board.ppmtool.domain.Project;
import io.kanban.board.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    //public ResponseEntity<Project> createNewProject(@Valid @RequestBody Project project, BindingResult result){
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){ //in case of has error check response entity is string so here use '?' to make generic
        if(result.hasErrors()){
            return new ResponseEntity<String>("Invalid project object", HttpStatus.BAD_REQUEST);
        }

        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
