package net.trique.wardentools.item.custom;

import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class WardenTemplateItem extends SmithingTemplateItem {
    private static final Formatting TITLE_FORMATTING;
    private static final Formatting DESCRIPTION_FORMATTING;
    private static final String TRANSLATION_KEY;
    private static final Text INGREDIENTS_TEXT;
    private static final Text APPLIES_TO_TEXT;
    private static final Text WARDEN_UPGRADE_TEXT;
    private static final Text WARDEN_UPGRADE_APPLIES_TO_TEXT;
    private static final Text WARDEN_UPGRADE_INGREDIENTS_TEXT;
    private static final Text WARDEN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT;
    private static final Text WARDEN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT;
    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE;
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE;
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE;
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE;
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE;
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE;
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE;
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE;
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE;
    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE;
    private final Text baseSlotDescriptionText;
    private final Text additionsSlotDescriptionText;
    private final List<Identifier> emptyBaseSlotTextures;
    private final List<Identifier> emptyAdditionsSlotTextures;

    public WardenTemplateItem(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures);
        this.baseSlotDescriptionText = baseSlotDescriptionText;
        this.additionsSlotDescriptionText = additionsSlotDescriptionText;
        this.emptyBaseSlotTextures = emptyBaseSlotTextures;
        this.emptyAdditionsSlotTextures = emptyAdditionsSlotTextures;
    }

    public static WardenTemplateItem createWardenUpgrade() {
        return new WardenTemplateItem(WARDEN_UPGRADE_APPLIES_TO_TEXT, WARDEN_UPGRADE_INGREDIENTS_TEXT, WARDEN_UPGRADE_TEXT, WARDEN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT, WARDEN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT, getWardenUpgradeEmptyBaseSlotTextures(), getWardenUpgradeEmptyAdditionsSlotTextures());
    }

    private static List<Identifier> getWardenUpgradeEmptyBaseSlotTextures() {
        return List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_SLOT_SWORD_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_SLOT_PICKAXE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_SLOT_AXE_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE, EMPTY_SLOT_HOE_TEXTURE, EMPTY_SLOT_SHOVEL_TEXTURE);
    }

    private static List<Identifier> getWardenUpgradeEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_INGOT_TEXTURE);
    }

    public Text getBaseSlotDescription() {
        return this.baseSlotDescriptionText;
    }

    public Text getAdditionsSlotDescription() {
        return this.additionsSlotDescriptionText;
    }

    public List<Identifier> getEmptyBaseSlotTextures() {
        return this.emptyBaseSlotTextures;
    }

    public List<Identifier> getEmptyAdditionsSlotTextures() {
        return this.emptyAdditionsSlotTextures;
    }

    public String getTranslationKey() {
        return TRANSLATION_KEY;
    }

    static {
        TITLE_FORMATTING = Formatting.GRAY;
        DESCRIPTION_FORMATTING = Formatting.BLUE;
        TRANSLATION_KEY = Util.createTranslationKey("item", new Identifier("smithing_template"));
        INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.ingredients"))).formatted(TITLE_FORMATTING);
        APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.applies_to"))).formatted(TITLE_FORMATTING);
        WARDEN_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", new Identifier("warden_upgrade"))).formatted(TITLE_FORMATTING);
        WARDEN_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.warden_upgrade.applies_to"))).formatted(DESCRIPTION_FORMATTING);
        WARDEN_UPGRADE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.warden_upgrade.ingredients"))).formatted(DESCRIPTION_FORMATTING);
        WARDEN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.warden_upgrade.base_slot_description")));
        WARDEN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.warden_upgrade.additions_slot_description")));
        EMPTY_ARMOR_SLOT_HELMET_TEXTURE = new Identifier("minecraft:item/empty_armor_slot_helmet");
        EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = new Identifier("minecraft:item/empty_armor_slot_chestplate");
        EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = new Identifier("minecraft:item/empty_armor_slot_leggings");
        EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = new Identifier("minecraft:item/empty_armor_slot_boots");
        EMPTY_SLOT_HOE_TEXTURE = new Identifier("minecraft:item/empty_slot_hoe");
        EMPTY_SLOT_AXE_TEXTURE = new Identifier("minecraft:item/empty_slot_axe");
        EMPTY_SLOT_SWORD_TEXTURE = new Identifier("minecraft:item/empty_slot_sword");
        EMPTY_SLOT_SHOVEL_TEXTURE = new Identifier("minecraft:item/empty_slot_shovel");
        EMPTY_SLOT_PICKAXE_TEXTURE = new Identifier("minecraft:item/empty_slot_pickaxe");
        EMPTY_SLOT_INGOT_TEXTURE = new Identifier("minecraft:item/empty_slot_ingot");
    }
}
