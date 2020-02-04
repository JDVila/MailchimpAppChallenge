package dev.jdvila.josevilamailchimpapp.views;

import dev.jdvila.josevilamailchimpapp.model.Member;

public class MemberListItem extends ListItem {

    private final Member member;

    public MemberListItem(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    @Override
    public int getType() {
        return MEMBER_ITEM;
    }
}
