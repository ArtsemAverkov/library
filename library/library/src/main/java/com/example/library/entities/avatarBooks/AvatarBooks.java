package com.example.library.entities.avatarBooks;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AvatarBooks {
    @Id
    private Long id;
    @ToString.Exclude
    private byte[] image;
}
