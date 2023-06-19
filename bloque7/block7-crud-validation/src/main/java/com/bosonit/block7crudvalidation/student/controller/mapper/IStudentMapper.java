package com.bosonit.block7crudvalidation.student.controller.mapper;

import com.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDTO;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDTO;
import com.bosonit.block7crudvalidation.student.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IStudentMapper {
    IStudentMapper INSTANCE = Mappers.getMapper(IStudentMapper.class);

//    @Mapping(target = "profesor", ignore = true)
//    StudentOutputDTO studentToStudentOutputDTO(Student student);

}
