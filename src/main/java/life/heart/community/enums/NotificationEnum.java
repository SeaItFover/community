package life.heart.community.enums;

public enum NotificationEnum {
    REPLY_QUESTION(1, "回复了问题"),
    REPLY_COMMENT(1, "回复了评论"),
    ;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    private int type;
    private String name;

    NotificationEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
