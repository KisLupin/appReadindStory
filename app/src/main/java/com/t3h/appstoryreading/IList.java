package com.t3h.appstoryreading;

public interface IList {
    ListStory getStoryName(int position);
    int getSize();
    void onClick(int position);
}
