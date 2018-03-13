package model;

public class BoardFile {
	private int id;
	private String originFileName;
	private int size;
	private String uri;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	@Override
	public String toString() {
		return "BoardFile [id=" + id + ", originFileName=" + originFileName + ", size=" + size + ", uri=" + uri + "]";
	}
	
}
