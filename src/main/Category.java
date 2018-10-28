package main;

public class Category implements Comparable<Integer>{
	private String name;
	private int votes = 0;
	
	public Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void incVotes() {
		votes++;
	}

	public int getVotes() {
		return votes;
	}

	@Override
	public int compareTo(Integer votes2) {
		Integer votes1 = new Integer(votes);
		return votes1.compareTo(votes2);
	}
}
