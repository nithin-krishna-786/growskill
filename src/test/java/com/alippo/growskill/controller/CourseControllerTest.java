package com.alippo.growskill.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.alippo.growskill.controller.CourseController;
import com.alippo.growskill.dto.CourseDTO;
import com.alippo.growskill.entities.Course;
import com.alippo.growskill.enums.Specialization;
import com.alippo.growskill.service.CourseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CourseService courseService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CourseController courseController;

    @Test
    public void testGetAllCourses() throws Exception {
        // Mock data
    
    	Course c1 = new Course();
    	c1.setId(1);
    	c1.setSpecialization(Specialization.BAKING);
    	
      	Course c2 = new Course();
    	c2.setId(2);
    	c2.setSpecialization(Specialization.HANDICRAFTS);
    	
        List<Course> courses = Arrays.asList(c1,c2);
        
        CourseDTO courseDTO1 = new CourseDTO();
        courseDTO1.setId(1);
        courseDTO1.setSpecialization(Specialization.BAKING);

        CourseDTO courseDTO2 = new CourseDTO();
        courseDTO2.setId(1);
        courseDTO2.setSpecialization(Specialization.HANDICRAFTS);

        List<CourseDTO> courseDTOs = Arrays.asList(courseDTO1,courseDTO2);

        // Mock service method
        when(courseService.getAllCourses()).thenReturn(courses);

        // Mock mapping
        when(modelMapper.map(courses.get(0), CourseDTO.class)).thenReturn(courseDTOs.get(0));
        when(modelMapper.map(courses.get(1), CourseDTO.class)).thenReturn(courseDTOs.get(1));

        // Perform GET request
        mockMvc.perform(get("/api/course")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].name").value("Course1"))
//                .andExpect(jsonPath("$[1].id").value(2))
//                .andExpect(jsonPath("$[1].name").value("Course2"));
    }
}
