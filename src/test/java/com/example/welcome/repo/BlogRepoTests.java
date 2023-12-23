package com.example.welcome.repo;

import com.example.welcome.model.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BlogRepoTests {

    @Autowired
    BlogRepo blogRepo;

    @Test
    void Should_Return_One_Blog_On_Find_By_Id() {
        Blog blog = new Blog("A test Blog", "Welcome to the blog \n I am testing something here", "Sparsh Sethi");
        Blog storedBlog = blogRepo.save(blog);

        Blog foundBlog = blogRepo.findById(blog.getId()).orElse(null);

        assertThat(foundBlog).isNotNull();
        assertThat(foundBlog.getId()).isEqualTo(storedBlog.getId());
    }


}
