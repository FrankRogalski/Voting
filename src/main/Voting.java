package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class Voting {
	private ArrayList<Category> categories = new ArrayList<Category>();
	Scanner sc = new Scanner(System.in);

	public void start(String path) {
		getData(path);
		do {
			vote();
		} while (keepVoting());
		showResults();
	}
	
	private boolean keepVoting() {
		System.out.print("Wollen mehr Leute Wählen(j/n): ");
		return sc.nextLine().charAt(0) == 'j';
	}

	private void getData(String path) {
		try {
			Scanner sc = new Scanner(new File(path));
			while (sc.hasNextLine()) {
				categories.add(new Category(sc.nextLine()));
			}
			sc.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void vote() {
		System.out.println("Wähle deine Kategorien:");
		for (int i = 0; i < categories.size(); i++) {
			Category cat = categories.get(i);
			System.out.println(i + ": " + cat.getName());
		}
		System.out.println();
		System.out.print("Welche Kategorien wählen sie: ");
		processResults(sc.nextLine());
	}
	
	private void processResults(String results) {
		HashSet<String> votes = new HashSet<String>();
		votes.addAll(Arrays.asList(results.split(" ")));
		for(String vote : votes) {
			int wish = Integer.valueOf(vote);
			categories.get(wish).incVotes();
		}
	}
	
	private void showResults() {
		Collections.sort(categories, new Comparator<Category>() {
            @Override
            public int compare(Category category1, Category category2) {
                return Integer.valueOf(category1.compareTo(category2.getVotes()));
            }
        });
		Collections.reverse(categories);
		for(Category cat : categories) {
			System.out.println(cat.getVotes() + " : " + cat.getName());
		}
	}
}
