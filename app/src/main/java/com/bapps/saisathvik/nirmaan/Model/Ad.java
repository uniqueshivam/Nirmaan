package com.bapps.saisathvik.nirmaan.Model;

/**
 * Created by Sai Sathvik on 3/31/2018.
 */

public class Ad {

    String postId;
    String post;

    public Ad(){}

    public String getPostId() {
        return postId;
    }

    public String getPost() {
        return post;
    }

    public Ad(String postId, String post) {

        this.postId = postId;
        this.post = post;
    }
}
