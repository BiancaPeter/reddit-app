package com.reddit.app.model;

public enum VoteType {
    UP_VOTE(1),
    DOWN_VOTE(-1);

    private final int value;

    VoteType(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

}
