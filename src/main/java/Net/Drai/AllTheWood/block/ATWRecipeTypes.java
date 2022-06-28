package Net.Drai.AllTheWood.block;

public enum ATWRecipeTypes {
    TWOBYTWO (Group.SINGLE_INPUT),
    SLAB (Group.SINGLE_INPUT),
    STAIRS (Group.SINGLE_INPUT),
    FENCE (Group.SINGLE_INPUT),
    FENCE_GATE (Group.SINGLE_INPUT),
    SINGLE_INPUT_SHAPELESS (Group.SINGLE_INPUT),
    ROUND (Group.SINGLE_INPUT);

    private Group group;

    ATWRecipeTypes(Group group){
        this.group = group;
    }

    public boolean isInGroup(Group group){
        return this.group==group;
    }
    public Group getGroup(){
        return this.group;
    }
    public enum Group {
        SINGLE_INPUT,
        TWO_INPUT;
    }
}