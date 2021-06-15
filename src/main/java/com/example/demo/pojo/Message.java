package com.example.demo.pojo;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "t_message")
public class Message implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "昵称不能为空!")
    @Length(min = 2, max = 12, message = "限2~12字")
    private String nickname;
    @Email
    @Length(min = 2, max = 64, message = "长度限制为：2~64")
    private String email;
    @NotBlank
    @Length(min = 2, max = 255, message = "限2~255字")
    private String content;
    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @OneToMany(mappedBy = "parentMessage")
    private List<Message> replyMessage = new ArrayList<Message>();
    @ManyToOne
    private Message parentMessage;

    public Message() {
        // TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public List<Message> getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(List<Message> replyMessage) {
        this.replyMessage = replyMessage;
    }

    public Message getParentMessage() {
        return parentMessage;
    }

    public void setParentMessage(Message parentMessage) {
        this.parentMessage = parentMessage;
    }
}
