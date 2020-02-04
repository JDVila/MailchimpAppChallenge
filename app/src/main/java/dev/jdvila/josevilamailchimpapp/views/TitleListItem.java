package dev.jdvila.josevilamailchimpapp.views;

public class TitleListItem extends ListItem {

    private final String title;

    public TitleListItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getType() {
        return TITLE_ITEM;
    }
}
