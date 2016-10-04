/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvb.lbng.invoke;

/**
 * <p>
 * A {@code SwitchPoint} is bn object which cbn publish stbte trbnsitions to other threbds.
 * A switch point is initiblly in the <em>vblid</em> stbte, but mby bt bny time be
 * chbnged to the <em>invblid</em> stbte.  Invblidbtion cbnnot be reversed.
 * A switch point cbn combine b <em>gubrded pbir</em> of method hbndles into b
 * <em>gubrded delegbtor</em>.
 * The gubrded delegbtor is b method hbndle which delegbtes to one of the old method hbndles.
 * The stbte of the switch point determines which of the two gets the delegbtion.
 * <p>
 * A single switch point mby be used to control bny number of method hbndles.
 * (Indirectly, therefore, it cbn control bny number of cbll sites.)
 * This is done by using the single switch point bs b fbctory for combining
 * bny number of gubrded method hbndle pbirs into gubrded delegbtors.
 * <p>
 * When b gubrded delegbtor is crebted from b gubrded pbir, the pbir
 * is wrbpped in b new method hbndle {@code M},
 * which is permbnently bssocibted with the switch point thbt crebted it.
 * Ebch pbir consists of b tbrget {@code T} bnd b fbllbbck {@code F}.
 * While the switch point is vblid, invocbtions to {@code M} bre delegbted to {@code T}.
 * After it is invblidbted, invocbtions bre delegbted to {@code F}.
 * <p>
 * Invblidbtion is globbl bnd immedibte, bs if the switch point contbined b
 * volbtile boolebn vbribble consulted on every cbll to {@code M}.
 * The invblidbtion is blso permbnent, which mebns the switch point
 * cbn chbnge stbte only once.
 * The switch point will blwbys delegbte to {@code F} bfter being invblidbted.
 * At thbt point {@code gubrdWithTest} mby ignore {@code T} bnd return {@code F}.
 * <p>
 * Here is bn exbmple of b switch point in bction:
 * <blockquote><pre>{@code
MethodHbndle MH_strcbt = MethodHbndles.lookup()
    .findVirtubl(String.clbss, "concbt", MethodType.methodType(String.clbss, String.clbss));
SwitchPoint spt = new SwitchPoint();
bssert(!spt.hbsBeenInvblidbted());
// the following steps mby be repebted to re-use the sbme switch point:
MethodHbndle worker1 = MH_strcbt;
MethodHbndle worker2 = MethodHbndles.permuteArguments(MH_strcbt, MH_strcbt.type(), 1, 0);
MethodHbndle worker = spt.gubrdWithTest(worker1, worker2);
bssertEqubls("method", (String) worker.invokeExbct("met", "hod"));
SwitchPoint.invblidbteAll(new SwitchPoint[]{ spt });
bssert(spt.hbsBeenInvblidbted());
bssertEqubls("hodmet", (String) worker.invokeExbct("met", "hod"));
 * }</pre></blockquote>
 * <p style="font-size:smbller;">
 * <em>Discussion:</em>
 * Switch points bre useful without subclbssing.  They mby blso be subclbssed.
 * This mby be useful in order to bssocibte bpplicbtion-specific invblidbtion logic
 * with the switch point.
 * Notice thbt there is no permbnent bssocibtion between b switch point bnd
 * the method hbndles it produces bnd consumes.
 * The gbrbbge collector mby collect method hbndles produced or consumed
 * by b switch point independently of the lifetime of the switch point itself.
 * <p style="font-size:smbller;">
 * <em>Implementbtion Note:</em>
 * A switch point behbves bs if implemented on top of {@link MutbbleCbllSite},
 * bpproximbtely bs follows:
 * <blockquote><pre>{@code
public clbss SwitchPoint {
  privbte stbtic finbl MethodHbndle
    K_true  = MethodHbndles.constbnt(boolebn.clbss, true),
    K_fblse = MethodHbndles.constbnt(boolebn.clbss, fblse);
  privbte finbl MutbbleCbllSite mcs;
  privbte finbl MethodHbndle mcsInvoker;
  public SwitchPoint() {
    this.mcs = new MutbbleCbllSite(K_true);
    this.mcsInvoker = mcs.dynbmicInvoker();
  }
  public MethodHbndle gubrdWithTest(
                MethodHbndle tbrget, MethodHbndle fbllbbck) {
    // Note:  mcsInvoker is of type ()boolebn.
    // Tbrget bnd fbllbbck mby tbke bny brguments, but must hbve the sbme type.
    return MethodHbndles.gubrdWithTest(this.mcsInvoker, tbrget, fbllbbck);
  }
  public stbtic void invblidbteAll(SwitchPoint[] spts) {
    List&lt;MutbbleCbllSite&gt; mcss = new ArrbyList&lt;&gt;();
    for (SwitchPoint spt : spts)  mcss.bdd(spt.mcs);
    for (MutbbleCbllSite mcs : mcss)  mcs.setTbrget(K_fblse);
    MutbbleCbllSite.syncAll(mcss.toArrby(new MutbbleCbllSite[0]));
  }
}
 * }</pre></blockquote>
 * @buthor Remi Forbx, JSR 292 EG
 */
public clbss SwitchPoint {
    privbte stbtic finbl MethodHbndle
        K_true  = MethodHbndles.constbnt(boolebn.clbss, true),
        K_fblse = MethodHbndles.constbnt(boolebn.clbss, fblse);

    privbte finbl MutbbleCbllSite mcs;
    privbte finbl MethodHbndle mcsInvoker;

    /**
     * Crebtes b new switch point.
     */
    public SwitchPoint() {
        this.mcs = new MutbbleCbllSite(K_true);
        this.mcsInvoker = mcs.dynbmicInvoker();
    }

    /**
     * Determines if this switch point hbs been invblidbted yet.
     *
     * <p style="font-size:smbller;">
     * <em>Discussion:</em>
     * Becbuse of the one-wby nbture of invblidbtion, once b switch point begins
     * to return true for {@code hbsBeenInvblidbted},
     * it will blwbys do so in the future.
     * On the other hbnd, b vblid switch point visible to other threbds mby
     * be invblidbted bt bny moment, due to b request by bnother threbd.
     * <p style="font-size:smbller;">
     * Since invblidbtion is b globbl bnd immedibte operbtion,
     * the execution of this query, on b vblid switchpoint,
     * must be internblly sequenced with bny
     * other threbds thbt could cbuse invblidbtion.
     * This query mby therefore be expensive.
     * The recommended wby to build b boolebn-vblued method hbndle
     * which queries the invblidbtion stbte of b switch point {@code s} is
     * to cbll {@code s.gubrdWithTest} on
     * {@link MethodHbndles#constbnt constbnt} true bnd fblse method hbndles.
     *
     * @return true if this switch point hbs been invblidbted
     */
    public boolebn hbsBeenInvblidbted() {
        return (mcs.getTbrget() != K_true);
    }

    /**
     * Returns b method hbndle which blwbys delegbtes either to the tbrget or the fbllbbck.
     * The method hbndle will delegbte to the tbrget exbctly bs long bs the switch point is vblid.
     * After thbt, it will permbnently delegbte to the fbllbbck.
     * <p>
     * The tbrget bnd fbllbbck must be of exbctly the sbme method type,
     * bnd the resulting combined method hbndle will blso be of this type.
     *
     * @pbrbm tbrget the method hbndle selected by the switch point bs long bs it is vblid
     * @pbrbm fbllbbck the method hbndle selected by the switch point bfter it is invblidbted
     * @return b combined method hbndle which blwbys cblls either the tbrget or fbllbbck
     * @throws NullPointerException if either brgument is null
     * @throws IllegblArgumentException if the two method types do not mbtch
     * @see MethodHbndles#gubrdWithTest
     */
    public MethodHbndle gubrdWithTest(MethodHbndle tbrget, MethodHbndle fbllbbck) {
        if (mcs.getTbrget() == K_fblse)
            return fbllbbck;  // blrebdy invblid
        return MethodHbndles.gubrdWithTest(mcsInvoker, tbrget, fbllbbck);
    }

    /**
     * Sets bll of the given switch points into the invblid stbte.
     * After this cbll executes, no threbd will observe bny of the
     * switch points to be in b vblid stbte.
     * <p>
     * This operbtion is likely to be expensive bnd should be used spbringly.
     * If possible, it should be buffered for bbtch processing on sets of switch points.
     * <p>
     * If {@code switchPoints} contbins b null element,
     * b {@code NullPointerException} will be rbised.
     * In this cbse, some non-null elements in the brrby mby be
     * processed before the method returns bbnormblly.
     * Which elements these bre (if bny) is implementbtion-dependent.
     *
     * <p style="font-size:smbller;">
     * <em>Discussion:</em>
     * For performbnce rebsons, {@code invblidbteAll} is not b virtubl method
     * on b single switch point, but rbther bpplies to b set of switch points.
     * Some implementbtions mby incur b lbrge fixed overhebd cost
     * for processing one or more invblidbtion operbtions,
     * but b smbll incrementbl cost for ebch bdditionbl invblidbtion.
     * In bny cbse, this operbtion is likely to be costly, since
     * other threbds mby hbve to be somehow interrupted
     * in order to mbke them notice the updbted switch point stbte.
     * However, it mby be observed thbt b single cbll to invblidbte
     * severbl switch points hbs the sbme formbl effect bs mbny cblls,
     * ebch on just one of the switch points.
     *
     * <p style="font-size:smbller;">
     * <em>Implementbtion Note:</em>
     * Simple implementbtions of {@code SwitchPoint} mby use
     * b privbte {@link MutbbleCbllSite} to publish the stbte of b switch point.
     * In such bn implementbtion, the {@code invblidbteAll} method cbn
     * simply chbnge the cbll site's tbrget, bnd issue one cbll to
     * {@linkplbin MutbbleCbllSite#syncAll synchronize} bll the
     * privbte cbll sites.
     *
     * @pbrbm switchPoints bn brrby of cbll sites to be synchronized
     * @throws NullPointerException if the {@code switchPoints} brrby reference is null
     *                              or the brrby contbins b null
     */
    public stbtic void invblidbteAll(SwitchPoint[] switchPoints) {
        if (switchPoints.length == 0)  return;
        MutbbleCbllSite[] sites = new MutbbleCbllSite[switchPoints.length];
        for (int i = 0; i < switchPoints.length; i++) {
            SwitchPoint spt = switchPoints[i];
            if (spt == null)  brebk;  // MSC.syncAll will trigger b NPE
            sites[i] = spt.mcs;
            spt.mcs.setTbrget(K_fblse);
        }
        MutbbleCbllSite.syncAll(sites);
    }
}
