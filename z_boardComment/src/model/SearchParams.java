package model;

public class SearchParams {

	private int searchSel;
	private String searchInput;
	public int getSearchSel() {
		return searchSel;
	}
	public void setSearchSel(int searchSel) {
		this.searchSel = searchSel;
	}
	public String getSearchInput() {
		return searchInput;
	}
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}
	@Override
	public String toString() {
		return "SearchParams [searchSel=" + searchSel + ", searchInput=" + searchInput + "]";
	}
	
	
}
