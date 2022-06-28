package Net.Drai.AllTheWood.block;

public enum BlockTypes {
    //TODO Add proper groups
    BARS, //PaneBlock
    BARS_CROSSED, //PaneBlock Render Type:Cutout
    BARS_HORIZONTAL, //HorizontalBarsSlabBlock
    BARS_CROSSED_HORIZONTAL, //HorizontalBarsSlabBlock
    BAR_STOOL, //BNBarStool
    CHAIR, //BNNormalChair
    TABURET, //BNTaburet
    BOOKCASE, //Bookcases
    CRATE_CHARM, //CrateBlockEntity */
    PLANKS_CHIPPED_1 (Group.BLOCKS),
    PLANKS_CHIPPED_2 (Group.BLOCKS),
    PLANKS_CHIPPED_3 (Group.BLOCKS),
    PLANKS_CHIPPED_4 (Group.BLOCKS),
    PLANKS_CHIPPED_5 (Group.BLOCKS),
    PLANKS_CHIPPED_6 (Group.BLOCKS),
    PLANKS_CHIPPED_7 (Group.BLOCKS),
    PLANKS_CHIPPED_8 (Group.BLOCKS),
    PLANKS_CHIPPED_9 (Group.BLOCKS),
    PLANKS_CHIPPED_10 (Group.BLOCKS),
    PLANKS_CHIPPED_11 (Group.BLOCKS),
    PLANKS_CHIPPED_12 (Group.BLOCKS),
    PLANKS_CHIPPED_13 (Group.BLOCKS),
    PLANKS_CHIPPED_14 (Group.BLOCKS),
    PLANKS_CHIPPED_15 (Group.BLOCKS),
    PLANKS_CHIPPED_16 (Group.BLOCKS),
    PLANKS_CHIPPED_17 (Group.BLOCKS),
    PLANKS_CHIPPED_18 (Group.BLOCKS),
    SAWMILL,
    WINDOW_CREATE,
    WINDOW_PANE_CREATE,
    BEAM (Group.AXIS_BLOCKS),
    LATTICE,
    PALISADE,
    SEAT,
    SUPPORT,
    PARQUET (Group.BLOCKS),
    PARQUET_SLAB (Group.SLABS),
    PARQUET_STAIRS,
    WOOD_CARVED,
    BOAT_WITH_CHEST,
    BOAT_WITH_FURNACE,
    LARGE_BOAT,
    BRIDGE,
    BRIDGE_RAIL,
    BRIDGE_ROPE,
    BRIDGE_STAIR,
    BRIDGE_STAIR_ROPE,
    BRIDGE_SUPPORT,
    DOOR_BARK_GLASS,
    DOOR_BARN_GLASSED,
    DOOR_BARN,
    DOOR_BEACH,
    DOOR_CLASSIC,
    DOOR_COTTAGE,
    DOOR_FOUR_SEGMENT,
    DOOR_GLASS,
    DOOR_MODERN,
    DOOR_MYSTIC,
    DOOR_NETHER,
    DOOR_PAPER,
    DOOR_SHOJI,
    DOOR_STABLE_HORSE,
    DOOR_STABLE,
    DOOR_TROPICAL,
    DOOR_WESTERN,
    FENCE_HORSE,
    FENCE_PICKET,
    FENCE_WIRED,
    GATE_HIGHLEY,
    GATE_PYRAMID,
    TRAP_DOOR_BARK,
    TRAP_DOOR_BARN,
    TRAP_DOOR_BARRED,
    TRAP_DOOR_BEACH,
    TRAP_DOOR_COTTAGE,
    TRAP_DOOR_FOUR_PANEL,
    TRAP_DOOR_GLASS,
    TRAP_DOOR_MYSTIC,
    TRAP_DOOR_PAPER,
    TRAP_DOOR_TROPICAL,
    BLINDS,
    PARAPET,
    WINDOW_MACAW,
    WINDOW_GRILLE,
    WINDOW_PLANK_GRILLE,
    WINDOW_PLANK,
    WINDOW_STRIPPED_GRILLE,
    WINDOW_STRIPPED,
    CRATE_MARKET_CRATES,
    SUSPENSION,
    SUSPENSION_TOP,
    TRESSEL,
    TRESSEL_TOP,
    PLANKS_VERTICAL (Group.BLOCKS),
    POST,
    POST_STRIPPED,
    SLAB_VERTICAL,
    ITEM_SHELF,
    SIGN_HANGING,
    SIGN_POST,
    BARREL,
    BOAT,
    BOOKSHELF,
    BUTTON,
    CAMPFIRE,
    CAMPFIRE_SOUL,
    CHEST,
    CHEST_TRAPPED,
    COMPOSTER,
    CRAFTING_TABLE,
    DOOR,
    FENCE,
    FENCE_GATE,
    LADDER,
    LOG (Group.AXIS_BLOCKS),
    STRIPPED_LOG,
    PLANKS (Group.BLOCKS),
    PRESSURE_PLATE,
    SIGN,
    SLAB,
    STAIRS,
    TRAP_DOOR,
    WALL,
    WOOD  (Group.BLOCKS),
    STRIPPED_WOOD(Group.BLOCKS),
    GARDEN_PLANKS,
    PARQUET_SLAB_VERTICAL,
    DOOR_TALL;

    private Group group;

    BlockTypes(Group group){
        this.group = group;
    }
    BlockTypes(){
        this(Group.NO_GROUP);
    }

    public boolean isInGroup(Group group){
        return this.group==group;
    }
    public Group getGroup(){
        return this.group;
    }
    public enum Group {
        NO_GROUP,
        BLOCKS, //Implemented
        AXIS_BLOCKS,
        SLABS,
        STAIRS,
        FENCES,
        FENCE_GATES;
    }
}
