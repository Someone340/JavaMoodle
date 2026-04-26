package org.spring.assemblers;

import org.spring.DTO.UserDTO;
import org.spring.controller.UserController;
import org.spring.entity.UserEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserEntity, UserDTO> {

    public UserAssembler() {
        super(UserController.class, UserDTO.class);
    }

    @Override
    public UserDTO toModel(UserEntity userEntity) {
        UserDTO dto = new UserDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getAge()
        );

        dto.add(linkTo(methodOn(UserController.class).getAll()).withRel("all_users"));
        dto.add(linkTo(methodOn(UserController.class).findById(userEntity.getId())).withSelfRel());
        dto.add(linkTo(methodOn(UserController.class).delete(userEntity.getId())).withRel("delete"));
        dto.add(linkTo(methodOn(UserController.class).update(userEntity.getId(), null)).withRel("update"));

        return dto;
    }
}
