package com.t3h.appstoryreading;

public interface ITopic {
    Topic getTopic(int position);
    int getSize();
    void onClick(int position);
}
