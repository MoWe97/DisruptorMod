package theDisruptor.cards.DisruptorDeck;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.scene.LightFlareLEffect;
import theDisruptor.DefaultMod;
import theDisruptor.cards.AbstractDynamicCard;
import theDisruptor.characters.TheDefault;

import java.lang.reflect.Array;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theDisruptor.DefaultMod.makeCardPath;


//Base: Remove all Powers from everybody
//Upgrade: Retain
public class DisruptorRareSkillEMP extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(DisruptorRareSkillEMP.class.getSimpleName());
    //public static final String IMG = makeCardPath("EMP.png");

    public static final String IMG = makeCardPath("Skill.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 3;
    //private static final int UPGRADED_COST = 3;
    private static final boolean EXHAUSTS = true;
    private static final boolean UPGRADED_EXHAUSTS = true;

    private static final boolean RETAIN = false;
    private static final boolean UPGRADED_RETAIN = true;

    // /STAT DECLARATION/


    public DisruptorRareSkillEMP() {
        super(ID, new RegionName("blue/attack/core_surge"), COST, TYPE, COLOR, RARITY, TARGET);
        exhaustOnUseOnce = EXHAUSTS;
        retain = RETAIN;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //Effects
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(p.hb.cX, p.hb.cY)));
        //Remove Player Powers
        AbstractDungeon.actionManager.addToBottom(new RemoveAllPowersAction(p, false));

        //Remove Enemy Powers
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(mo.hb.cX, mo.hb.cY)));
            AbstractDungeon.actionManager.addToBottom(new RemoveAllPowersAction(mo, false));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.exhaustOnUseOnce = UPGRADED_EXHAUSTS;
            this.retain = UPGRADED_RETAIN;
            initializeDescription();
        }
    }
}
