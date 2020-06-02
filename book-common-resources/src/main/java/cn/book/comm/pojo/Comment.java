package cn.book.comm.pojo;

import java.util.Date;

public class Comment {
    private Integer commentId;

    private String userId;

    private Integer ctype;

    private Date cdate;

    private String cword;

    private String cbackId;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getCtype() {
        return ctype;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getCword() {
        return cword;
    }

    public void setCword(String cword) {
        this.cword = cword == null ? null : cword.trim();
    }

    public String getCbackId() {
        return cbackId;
    }

    public void setCbackId(String cbackId) {
        this.cbackId = cbackId == null ? null : cbackId.trim();
    }
}