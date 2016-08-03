package com.healthint.administration;

import java.util.Date;

public class Document {

	private String content;
	private Date receivedDate;

	public Document(String content, Date receivedDate) {
		super();
		this.content = content;
		this.receivedDate = receivedDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
}
