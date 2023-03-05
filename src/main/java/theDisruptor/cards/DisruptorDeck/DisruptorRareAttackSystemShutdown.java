package theDisruptor.cards.DisruptorDeck;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theDisruptor.DefaultMod;
import theDisruptor.cards.AbstractDynamicCard;
import theDisruptor.characters.TheDefault;

import java.lang.reflect.Array;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theDisruptor.DefaultMod.makeCardPath;


//Deal 0 x WeakAmount damage and Exhaust
//Upgrade: Remove Exhaust keyword
public class DisruptorRareAttackSystemShutdown extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(DisruptorRareAttackSystemShutdown.class.getSimpleName());
    //public static final String IMG = makeCardPath("SystemShutdown.png");
    public static final String IMG = makeCardPath("Attack.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;
    private static final boolean EXHAUSTS = true;
    private static final boolean UPGRADED_EXHAUSTS = false;

    private static final int DAMAGE = 0;
    private static final int UPGRADE_PLUS_DMG = 0;

    private int TIMES = 2;

    //private static final int AMOUNT = 0;
    //private static final int UPGRADED_AMOUNT = 0;

    // /STAT DECLARATION/


    public DisruptorRareAttackSystemShutdown() {
        super(ID,new RegionName("blue/attack/doom_and_gloom"), COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        exhaustOnUseOnce = EXHAUSTS;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //Check enemy for the amount of Weak-Stack and do the Attack that number of times.
        AbstractPower weak = m.getPower("Weakened");
        //int weakenedStackAmount = m.powers.stream().filter(element -> element.ID == "Weakened").findFirst().isPresent()?  m.powers.stream().filter(element -> element.ID == "Weakened").findFirst().get().amount : -1;
        if(weak != null) {
            for (int i = 0; i < weak.amount; i++) {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
            }
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeDamage(UPGRADE_PLUS_DMG);
            //upgradeBaseCost(UPGRADED_COST);
            rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            //upgradeMagic(UPGRADED_AMOUNT);
            this.exhaustOnUseOnce = UPGRADED_EXHAUSTS;
            initializeDescription();
        }
    }
}
