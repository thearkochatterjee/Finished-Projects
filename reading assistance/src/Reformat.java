public class Reformat {
    private Text text = new Text();

    public void reformat(int lenthlimit){
        for(int i = 0; i < 2; i++){
            for (String t : text.getText()) {
                if (t.split(" ").length <= lenthlimit) {
                    text.getText().remove(t);
                }
            }
        }
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }
}
