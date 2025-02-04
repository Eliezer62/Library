package com.buixzy.mylibrary.dtos;

import com.buixzy.mylibrary.entities.Author;

public record AuthorDTO(
    String name,
    String bibliography,
    String nationality
) {
    public Author toAuthor()
    {
        Author author = new Author();
        author.setName(name);
        author.setBibliography(bibliography);
        author.setNationality(nationality);

        return author;
    }
}
