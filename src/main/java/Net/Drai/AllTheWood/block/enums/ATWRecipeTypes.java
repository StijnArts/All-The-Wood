package Net.Drai.AllTheWood.block.enums;

public enum ATWRecipeTypes {
    TWOBYTWO,
    SLAB,
    STAIRS,
    FENCE,
    FENCE_GATE,
    SINGLE_INPUT_SHAPELESS,
    ROUND,
    LADDER,
    VERTICAL_LINE,
    PRESSURE_PLATE,
    SIGN,
    BARREL (Group.TWO_INPUT);


    private Group group;

    ATWRecipeTypes(Group group){
        this.group = group;
    }

    ATWRecipeTypes(){
        this.group = Group.SINGLE_INPUT;
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