package theDisruptor.cards.DisruptorDeck;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDisruptor.DefaultMod;
import theDisruptor.cards.AbstractDynamicCard;
import theDisruptor.characters.TheDefault;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theDisruptor.DefaultMod.makeCardPath;

public class DisruptorCommonSkillBackup extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(DisruptorCommonSkillBackup.class.getSimpleName());
    //public static final String IMG = makeCardPath("DisruptorCommonSkillBackup.png");
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final boolean RETAIN = true;

    private static final boolean PERSIST = false;
    private static final boolean UPGRADE_PERSIST = true;

    private static final int BLOCK = 15;
    private static final int UPGRADED_BLOCK = 1;

    // /STAT DECLARATION/


    public DisruptorCommonSkillBackup() {
        //super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        super(ID, new RegionName("blue/power/machine_learning"), COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        retain = RETAIN;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADED_BLOCK);
            returnToHand = UPGRADE_PERSIST;
            //upgradeMagicNumber(UPGRADED_AMOUNT);
            rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
