package com.classroom.LMS.classroomActivity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Date commentedOn;

    private String username;

    private String content;


    private Long parentId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comment_replies",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "reply_id")
    )
    private Set<Comment> replies = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="notice_id", nullable=false)
    private Notice notice;

    public Comment(String username, Set<Comment> replies, Notice notice) {
        this.username = username;
        this.replies = replies;
        this.notice = notice;
    }

    public Comment() {
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(Date commentedOn) {
        this.commentedOn = commentedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Comment> getReplies() {
        return replies;
    }

    public void setReplies(Set<Comment> replies) {
        this.replies = replies;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
}
