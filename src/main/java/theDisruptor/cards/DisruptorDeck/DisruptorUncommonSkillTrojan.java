package theDisruptor.cards.DisruptorDeck;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDisruptor.DefaultMod;
import theDisruptor.cards.AbstractDynamicCard;
import theDisruptor.characters.TheDefault;
import theDisruptor.powers.AntiArtifactPower;

import static theDisruptor.DefaultMod.makeCardPath;

public class DisruptorUncommonSkillTrojan extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(DisruptorUncommonSkillTrojan.class.getSimpleName());
    //public static final String IMG = makeCardPath("DisruptorUncommonSkillTrojan.png");
    public static final String IMG = makeCardPath("skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int AMOUNT = 1;
    private static final int UPGRADED_AMOUNT = 1;

    // /STAT DECLARATION/


    public DisruptorUncommonSkillTrojan() {
        super(ID, new RegionName("curse/parasite"), COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = AMOUNT;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new AntiArtifactPower(m, p, magicNumber)));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADED_AMOUNT);
            initializeDescription();
        }
    }
}
