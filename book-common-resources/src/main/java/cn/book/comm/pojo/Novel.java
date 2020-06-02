package cn.book.comm.pojo;

public class Novel {
    private String novelId;

    private String novelname;

    private String categoryId;

    private String novelinfo;

    private Integer status;

    private Integer score;
    
    private String novelanthor;
    
    private String novelImg;
    
    public String getNovelImg() {
		return novelImg;
	}

	public void setNovelImg(String novelImg) {
		this.novelImg = novelImg;
	}

	public String getNovelanthor() {
		return novelanthor;
	}

	public void setNovelanthor(String novelanthor) {
		this.novelanthor = novelanthor;
	}

	public String getNovelId() {
        return novelId;
    }

    public void setNovelId(String novelId) {
        this.novelId = novelId == null ? null : novelId.trim();
    }

    public String getNovelname() {
        return novelname;
    }

    public void setNovelname(String novelname) {
        this.novelname = novelname == null ? null : novelname.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getNovelinfo() {
        return novelinfo;
    }

    public void setNovelinfo(String novelinfo) {
        this.novelinfo = novelinfo == null ? null : novelinfo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}