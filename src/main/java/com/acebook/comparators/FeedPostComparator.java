package com.acebook.comparators;

import java.util.Comparator;

import com.acebook.entities.WallPost;

public class FeedPostComparator implements Comparator<WallPost>{

	@Override
	public int compare(WallPost arg0, WallPost arg1) {
		return arg1.getPostTime().compareTo(arg0.getPostTime());
	}

}
