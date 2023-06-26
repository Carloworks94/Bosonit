package com.bosonit.block7crudvalidation.student.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IStudentMapper {
    IStudentMapper INSTANCE = Mappers.getMapper(IStudentMapper.class);

//    @Mapping(target = "profesor", ignore = true)
//    StudentOutputDTO studentToStudentOutputDTO(Student student);

}
