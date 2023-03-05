package theDisruptor.cards.DisruptorDeck;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theDisruptor.DefaultMod;
import theDisruptor.cards.AbstractDynamicCard;
import theDisruptor.characters.TheDefault;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

import static theDisruptor.DefaultMod.makeCardPath;

public class DisruptorUncommonSkillShackle extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(DisruptorUncommonSkillShackle.class.getSimpleName());
    //public static final String IMG = makeCardPath("DisruptorUncommonSkill.png");
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final boolean EXHAUSTS = true;
    private static final boolean UPGRADED_EXHAUSTS = true;

    //private static final int DAMAGE = 0;
    //private static final int UPGRADE_PLUS_DMG = 0;

    private static final int AMOUNT = 1;
    private static final int UPGRADED_AMOUNT = 1;

    // /STAT DECLARATION/


    public DisruptorUncommonSkillShackle() {
        super(ID, new RegionName("curse/normality"), COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber =  AMOUNT;
        exhaustOnUseOnce = EXHAUSTS;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //get amount of current weakened
        AbstractPower weak = m.getPower("Weakened");
        if(weak != null && weak.amount > 0) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(m, p, new WeakPower(m, weak.amount * magicNumber, false), weak.amount * magicNumber));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADED_AMOUNT);
            rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.exhaustOnUseOnce = UPGRADED_EXHAUSTS;
            initializeDescription();
        }
    }
}
