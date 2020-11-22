package model;


public class BoardVO {
	private String board_id;
	private String company_name;
	private String write_date   ;
	private String title;
	private String writer;
	private String content;
	private String password;
	private Integer first;
	private Integer last;
	private String views;
	private String upload_file;
	private Integer cnt;
	
	public BoardVO() {
		
	}
	
	public BoardVO(String board_id, String company_name, String write_date, String title, String writer, String content,
			String password, Integer first, Integer last, String views, String upload_file, Integer cnt) {
		super();
		this.board_id = board_id;
		this.company_name = company_name;
		this.write_date = write_date;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.password = password;
		this.first = first;
		this.last = last;
		this.views = views;
		this.upload_file = upload_file;
		this.cnt = cnt;
	}


	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getWrite_date() {
		return write_date;
	}

	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Integer getLast() {
		return last;
	}

	public void setLast(Integer last) {
		this.last = last;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getUpload_file() {
		return upload_file;
	}

	public void setUpload_file(String upload_file) {
		this.upload_file = upload_file;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	
	
}
