package jeff.question1.objects;

import static jeff.question1.util.Utils.COMMA;

public class Difference {
    private Long id;
    private String tag;
    private String source;
    private String target;

    public Difference(Long id, String tag, String source, String target) {
        this.id = id;
        this.tag = tag;
        this.source = source;
        this.target = target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(id).append(COMMA)
                .append(tag).append(COMMA)
                .append(source).append(COMMA)
                .append(target);
        return str.toString();
    }

}
