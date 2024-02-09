package com.mjc.school.repository.model;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Component
@Table(name = "tags")
public class TagModel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false, name = "name")
    @Size(min = 3, max = 15)
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<NewsModel> news = new ArrayList<>();

    public TagModel() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<NewsModel> getNews() {
        return news;
    }

    public void setNews(List<NewsModel> news) {
        this.news = news;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        TagModel tagModel = (TagModel) obj;
        return id == tagModel.id &&
                (name == tagModel.name || (name != null && name.equals(tagModel.getName())));
    }

    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String toString() {
        return "Tag's ID: " + id + ", tag's name: " + name;
    }
}
