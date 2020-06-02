package cn.book.comm.pojo;

import java.util.List;

public class Conllection {
    private String collectionId;

    private String userId;

    private String novelId;

    private String chapterId;
    
    private List<Conllection> collections;

    public List<Conllection> getCollections() {
		return collections;
	}

	public void setCollections(List<Conllection> collections) {
		this.collections = collections;
	}

	public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId == null ? null : collectionId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getNovelId() {
        return novelId;
    }

    public void setNovelId(String novelId) {
        this.novelId = novelId == null ? null : novelId.trim();
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId == null ? null : chapterId.trim();
    }
}