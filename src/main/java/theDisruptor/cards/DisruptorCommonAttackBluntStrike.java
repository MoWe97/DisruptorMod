package theDisruptor.cards;


import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDisruptor.DefaultMod;
import theDisruptor.characters.TheDefault;

import static theDisruptor.DefaultMod.makeCardPath;

@AutoAdd.Ignore
public class DisruptorCommonAttackBluntStrike extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(DisruptorCommonAttackBluntStrike.class.getSimpleName());
    //public static final String IMG = makeCardPath("DisruptorCommonAttackBluntStrike.png");
    public static final String IMG = makeCardPath("attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 1;
    private static final int UPGRADE_PLUS_DMG = 1;

    //private static final int AMOUNT = 1;
    //private static final int UPGRADED_AMOUNT = 1;

    // /STAT DECLARATION/


    public DisruptorCommonAttackBluntStrike() {
        super(ID, new RegionName("blue/power/machine_learning"), COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            //upgradeMagicNumber(UPGRADED_AMOUNT);
            initializeDescription();
        }
    }
}
