package org.xtremeturmoil.hadoop.helper;

public class Entity {

	private String word;
	private int start;
	private int end;
	private String tag;

	public Entity(String word, String tag) {
		this.setTag(tag);
		this.setWord(word);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
