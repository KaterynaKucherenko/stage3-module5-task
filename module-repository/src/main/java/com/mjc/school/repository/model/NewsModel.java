package com.mjc.school.repository.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "news")
@Component
@AllArgsConstructor
public class NewsModel implements BaseEntity<Long>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false, name = "title")
    @Size(min = 5, max = 30)
    private String title;
    @Column(nullable = false, name = "content")
    @Size(min = 5, max = 255)
    private String content;
    @CreatedDate
    @Column(name = "createDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    private LocalDateTime createDate;
    @LastModifiedDate
    @Column(name = "lastUpdateDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    private LocalDateTime lastUpdateDate;
    @Column(nullable = false, name = "authorId")
    private Long authorId;
    @ManyToOne
    @JoinColumn(nullable = false)
    private AuthorModel authorModel;
    @OneToMany(mappedBy = "newsModel", cascade = CascadeType.REMOVE)
    private List<CommentModel> comments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tags_of_news", joinColumns = @JoinColumn(name = "news_id"), inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private List<TagModel> tags = new ArrayList<>();

    public NewsModel() {

    }


    public String getTitle() {
        return title;
    }

    @Required
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    @Required
    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }


    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }


    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getAuthorId() {
        return authorId;
    }


    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    @Required
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        NewsModel newsModel = (NewsModel) obj;
        return id == newsModel.id &&
                (title == newsModel.title || (title != null && title.equals(newsModel.getTitle()))) &&
                (content == newsModel.content || (content != null && content.equals(newsModel.getContent()))) &&
                (createDate == newsModel.createDate || (createDate != null && createDate.equals(newsModel.getCreateDate()))) &&
                (lastUpdateDate == newsModel.lastUpdateDate || (lastUpdateDate != null && lastUpdateDate.equals(newsModel.getLastUpdateDate()))) &&
                (authorId == newsModel.authorId || (authorId != null && authorId.equals(newsModel.getAuthorId())));
    }

    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdateDate, authorId);
    }

    public String toString() {
        return "news ID: " + id + ", title: " + title + ", content: " + content + ", create date: " + createDate + ", last update date: " + lastUpdateDate + ", author's ID: " + authorId;
    }


    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }
}