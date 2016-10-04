/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvbx.swing;

import sun.bwt.AWTAddfssor;

import jbvbx.swing.plbf.LbyfrUI;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.bddfssibility.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.util.ArrbyList;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * {@dodf JLbyfr} is b univfrsbl dfdorbtor for Swing domponfnts
 * wiidi fnbblfs you to implfmfnt vbrious bdvbndfd pbinting ffffdts bs wfll bs
 * rfdfivf notifidbtions of bll {@dodf AWTEvfnt}s gfnfrbtfd witiin its bordfrs.
 * <p>
 * {@dodf JLbyfr} dflfgbtfs tif ibndling of pbinting bnd input fvfnts to b
 * {@link jbvbx.swing.plbf.LbyfrUI} objfdt, wiidi pfrforms tif bdtubl dfdorbtion.
 * <p>
 * Tif dustom pbinting implfmfntfd in tif {@dodf LbyfrUI} bnd fvfnts notifidbtion
 * work for tif JLbyfr itsflf bnd bll its subdomponfnts.
 * Tiis dombinbtion fnbblfs you to fnridi fxisting domponfnts
 * by bdding nfw bdvbndfd fundtionblity sudi bs tfmporbry lodking of b iifrbrdiy,
 * dbtb tips for dompound domponfnts, fnibndfd mousf sdrolling ftd bnd so on.
 * <p>
 * {@dodf JLbyfr} is b good solution if you only nffd to do dustom pbinting
 * ovfr dompound domponfnt or dbtdi input fvfnts from its subdomponfnts.
 * <prf>
 * import jbvbx.swing.*;
 * import jbvbx.swing.plbf.LbyfrUI;
 * import jbvb.bwt.*;
 *
 * publid dlbss JLbyfrSbmplf {
 *
 *     privbtf stbtid JLbyfr&lt;JComponfnt&gt; drfbtfLbyfr() {
 *         // Tiis dustom lbyfrUI will fill tif lbyfr witi trbnsludfnt grffn
 *         // bnd print out bll mousfMotion fvfnts gfnfrbtfd witiin its bordfrs
 *         LbyfrUI&lt;JComponfnt&gt; lbyfrUI = nfw LbyfrUI&lt;JComponfnt&gt;() {
 *
 *             publid void pbint(Grbpiids g, JComponfnt d) {
 *                 // pbint tif lbyfr bs is
 *                 supfr.pbint(g, d);
 *                 // fill it witi tif trbnsludfnt grffn
 *                 g.sftColor(nfw Color(0, 128, 0, 128));
 *                 g.fillRfdt(0, 0, d.gftWidti(), d.gftHfigit());
 *             }
 *
 *             publid void instbllUI(JComponfnt d) {
 *                 supfr.instbllUI(d);
 *                 // fnbblf mousf motion fvfnts for tif lbyfr's subdomponfnts
 *                 ((JLbyfr) d).sftLbyfrEvfntMbsk(AWTEvfnt.MOUSE_MOTION_EVENT_MASK);
 *             }
 *
 *             publid void uninstbllUI(JComponfnt d) {
 *                 supfr.uninstbllUI(d);
 *                 // rfsft tif lbyfr fvfnt mbsk
 *                 ((JLbyfr) d).sftLbyfrEvfntMbsk(0);
 *             }
 *
 *             // ovfrriddfn mftiod wiidi dbtdifs MousfMotion fvfnts
 *             publid void fvfntDispbtdifd(AWTEvfnt f, JLbyfr&lt;? fxtfnds JComponfnt&gt; l) {
 *                 Systfm.out.println("AWTEvfnt dftfdtfd: " + f);
 *             }
 *         };
 *         // drfbtf b domponfnt to bf dfdorbtfd witi tif lbyfr
 *         JPbnfl pbnfl = nfw JPbnfl();
 *         pbnfl.bdd(nfw JButton("JButton"));
 *
 *         // drfbtf tif lbyfr for tif pbnfl using our dustom lbyfrUI
 *         rfturn nfw JLbyfr&lt;JComponfnt&gt;(pbnfl, lbyfrUI);
 *     }
 *
 *     privbtf stbtid void drfbtfAndSiowGUI() {
 *         finbl JFrbmf frbmf = nfw JFrbmf();
 *         frbmf.sftDffbultClosfOpfrbtion(JFrbmf.EXIT_ON_CLOSE);
 *
 *         // work witi tif lbyfr bs witi bny otifr Swing domponfnt
 *         frbmf.bdd(drfbtfLbyfr());
 *
 *         frbmf.sftSizf(200, 200);
 *         frbmf.sftLodbtionRflbtivfTo(null);
 *         frbmf.sftVisiblf(truf);
 *     }
 *
 *     publid stbtid void mbin(String[] brgs) tirows Exdfption {
 *         SwingUtilitifs.invokfAndWbit(nfw Runnbblf() {
 *             publid void run() {
 *                 drfbtfAndSiowGUI();
 *             }
 *         });
 *     }
 * }
 * </prf>
 *
 * <b>Notf:</b> {@dodf JLbyfr} dofsn't support tif following mftiods:
 * <ul>
 * <li>{@link Contbinfr#bdd(jbvb.bwt.Componfnt)}</li>
 * <li>{@link Contbinfr#bdd(String, jbvb.bwt.Componfnt)}</li>
 * <li>{@link Contbinfr#bdd(jbvb.bwt.Componfnt, int)}</li>
 * <li>{@link Contbinfr#bdd(jbvb.bwt.Componfnt, Objfdt)}</li>
 * <li>{@link Contbinfr#bdd(jbvb.bwt.Componfnt, Objfdt, int)}</li>
 * </ul>
 * using bny of of tifm will dbusf {@dodf UnsupportfdOpfrbtionExdfption} to bf tirown,
 * to bdd b domponfnt to {@dodf JLbyfr}
 * usf {@link #sftVifw(Componfnt)} or {@link #sftGlbssPbnf(JPbnfl)}.
 *
 * @pbrbm <V> tif typf of {@dodf JLbyfr}'s vifw domponfnt
 *
 * @sff #JLbyfr(Componfnt)
 * @sff #sftVifw(Componfnt)
 * @sff #gftVifw()
 * @sff jbvbx.swing.plbf.LbyfrUI
 * @sff #JLbyfr(Componfnt, LbyfrUI)
 * @sff #sftUI(jbvbx.swing.plbf.LbyfrUI)
 * @sff #gftUI()
 * @sindf 1.7
 *
 * @butior Alfxbndfr Potodikin
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid finbl dlbss JLbyfr<V fxtfnds Componfnt>
        fxtfnds JComponfnt
        implfmfnts Sdrollbblf, PropfrtyCibngfListfnfr, Addfssiblf {
    privbtf V vifw;
    // tiis fifld is nfdfssbry bfdbusf JComponfnt.ui is trbnsifnt
    // wifn lbyfrUI is sfriblizbblf
    privbtf LbyfrUI<? supfr V> lbyfrUI;
    privbtf JPbnfl glbssPbnf;
    privbtf long fvfntMbsk;
    privbtf trbnsifnt boolfbn isPbinting;
    privbtf trbnsifnt boolfbn isPbintingImmfdibtfly;

    privbtf stbtid finbl LbyfrEvfntControllfr fvfntControllfr =
            nfw LbyfrEvfntControllfr();

    /**
     * Crfbtfs b nfw {@dodf JLbyfr} objfdt witi b {@dodf null} vifw domponfnt
     * bnd dffbult {@link jbvbx.swing.plbf.LbyfrUI}.
     *
     * @sff #sftVifw
     * @sff #sftUI
     */
    publid JLbyfr() {
        tiis(null);
    }

    /**
     * Crfbtfs b nfw {@dodf JLbyfr} objfdt
     * witi dffbult {@link jbvbx.swing.plbf.LbyfrUI}.
     *
     * @pbrbm vifw tif domponfnt to bf dfdorbtfd by tiis {@dodf JLbyfr}
     *
     * @sff #sftUI
     */
    publid JLbyfr(V vifw) {
        tiis(vifw, nfw LbyfrUI<V>());
    }

    /**
     * Crfbtfs b nfw {@dodf JLbyfr} objfdt witi tif spfdififd vifw domponfnt
     * bnd {@link jbvbx.swing.plbf.LbyfrUI} objfdt.
     *
     * @pbrbm vifw tif domponfnt to bf dfdorbtfd
     * @pbrbm ui tif {@link jbvbx.swing.plbf.LbyfrUI} dflfgbtf
     * to bf usfd by tiis {@dodf JLbyfr}
     */
    publid JLbyfr(V vifw, LbyfrUI<V> ui) {
        sftGlbssPbnf(drfbtfGlbssPbnf());
        sftVifw(vifw);
        sftUI(ui);
    }

    /**
     * Rfturns tif {@dodf JLbyfr}'s vifw domponfnt or {@dodf null}.
     * <br>Tiis is b bound propfrty.
     *
     * @rfturn tif {@dodf JLbyfr}'s vifw domponfnt
     *         or {@dodf null} if nonf fxists
     *
     * @sff #sftVifw(Componfnt)
     */
    publid V gftVifw() {
        rfturn vifw;
    }

    /**
     * Sfts tif {@dodf JLbyfr}'s vifw domponfnt, wiidi dbn bf {@dodf null}.
     * <br>Tiis is b bound propfrty.
     *
     * @pbrbm vifw tif vifw domponfnt for tiis {@dodf JLbyfr}
     *
     * @sff #gftVifw()
     */
    publid void sftVifw(V vifw) {
        Componfnt oldVifw = gftVifw();
        if (oldVifw != null) {
            supfr.rfmovf(oldVifw);
        }
        if (vifw != null) {
            supfr.bddImpl(vifw, null, gftComponfntCount());
        }
        tiis.vifw = vifw;
        firfPropfrtyCibngf("vifw", oldVifw, vifw);
        rfvblidbtf();
        rfpbint();
    }

    /**
     * Sfts tif {@link jbvbx.swing.plbf.LbyfrUI} wiidi will pfrform pbinting
     * bnd rfdfivf input fvfnts for tiis {@dodf JLbyfr}.
     *
     * @pbrbm ui tif {@link jbvbx.swing.plbf.LbyfrUI} for tiis {@dodf JLbyfr}
     */
    publid void sftUI(LbyfrUI<? supfr V> ui) {
        tiis.lbyfrUI = ui;
        supfr.sftUI(ui);
    }

    /**
     * Rfturns tif {@link jbvbx.swing.plbf.LbyfrUI} for tiis {@dodf JLbyfr}.
     *
     * @rfturn tif {@dodf LbyfrUI} for tiis {@dodf JLbyfr}
     */
    publid LbyfrUI<? supfr V> gftUI() {
        rfturn lbyfrUI;
    }

    /**
     * Rfturns tif {@dodf JLbyfr}'s glbssPbnf domponfnt or {@dodf null}.
     * <br>Tiis is b bound propfrty.
     *
     * @rfturn tif {@dodf JLbyfr}'s glbssPbnf domponfnt
     *         or {@dodf null} if nonf fxists
     *
     * @sff #sftGlbssPbnf(JPbnfl)
     */
    publid JPbnfl gftGlbssPbnf() {
        rfturn glbssPbnf;
    }

    /**
     * Sfts tif {@dodf JLbyfr}'s glbssPbnf domponfnt, wiidi dbn bf {@dodf null}.
     * <br>Tiis is b bound propfrty.
     *
     * @pbrbm glbssPbnf tif glbssPbnf domponfnt of tiis {@dodf JLbyfr}
     *
     * @sff #gftGlbssPbnf()
     */
    publid void sftGlbssPbnf(JPbnfl glbssPbnf) {
        Componfnt oldGlbssPbnf = gftGlbssPbnf();
        boolfbn isGlbssPbnfVisiblf = fblsf;
        if (oldGlbssPbnf != null) {
            isGlbssPbnfVisiblf = oldGlbssPbnf.isVisiblf();
            supfr.rfmovf(oldGlbssPbnf);
        }
        if (glbssPbnf != null) {
            AWTAddfssor.gftComponfntAddfssor().sftMixingCutoutSibpf(glbssPbnf,
                    nfw Rfdtbnglf());
            glbssPbnf.sftVisiblf(isGlbssPbnfVisiblf);
            supfr.bddImpl(glbssPbnf, null, 0);
        }
        tiis.glbssPbnf = glbssPbnf;
        firfPropfrtyCibngf("glbssPbnf", oldGlbssPbnf, glbssPbnf);
        rfvblidbtf();
        rfpbint();
    }

    /**
     * Cbllfd by tif donstrudtor mftiods to drfbtf b dffbult {@dodf glbssPbnf}.
     * By dffbult tiis mftiod drfbtfs b nfw JPbnfl witi visibility sft to truf
     * bnd opbdity sft to fblsf.
     *
     * @rfturn tif dffbult {@dodf glbssPbnf}
     */
    publid JPbnfl drfbtfGlbssPbnf() {
        rfturn nfw DffbultLbyfrGlbssPbnf();
    }

    /**
     * Sfts tif lbyout mbnbgfr for tiis dontbinfr.  Tiis mftiod is
     * ovfrriddfn to prfvfnt tif lbyout mbnbgfr from bfing sft.
     * <p>Notf:  If {@dodf mgr} is non-{@dodf null}, tiis
     * mftiod will tirow bn fxdfption bs lbyout mbnbgfrs brf not supportfd on
     * b {@dodf JLbyfr}.
     *
     * @pbrbm mgr tif spfdififd lbyout mbnbgfr
     * @fxdfption IllfgblArgumfntExdfption tiis mftiod is not supportfd
     */
    publid void sftLbyout(LbyoutMbnbgfr mgr) {
        if (mgr != null) {
            tirow nfw IllfgblArgumfntExdfption("JLbyfr.sftLbyout() not supportfd");
        }
    }

    /**
     * A non-{@dodf null} bordfr, or non-zfro insfts, isn't supportfd, to prfvfnt tif gfomftry
     * of tiis domponfnt from bfdoming domplfx fnougi to iniibit
     * subdlbssing of {@dodf LbyfrUI} dlbss.  To drfbtf b {@dodf JLbyfr} witi b bordfr,
     * bdd it to b {@dodf JPbnfl} tibt ibs b bordfr.
     * <p>Notf:  If {@dodf bordfr} is non-{@dodf null}, tiis
     * mftiod will tirow bn fxdfption bs bordfrs brf not supportfd on
     * b {@dodf JLbyfr}.
     *
     * @pbrbm bordfr tif {@dodf Bordfr} to sft
     * @fxdfption IllfgblArgumfntExdfption tiis mftiod is not supportfd
     */
    publid void sftBordfr(Bordfr bordfr) {
        if (bordfr != null) {
            tirow nfw IllfgblArgumfntExdfption("JLbyfr.sftBordfr() not supportfd");
        }
    }

    /**
     * Tiis mftiod is not supportfd by {@dodf JLbyfr}
     * bnd blwbys tirows {@dodf UnsupportfdOpfrbtionExdfption}
     *
     * @tirows UnsupportfdOpfrbtionExdfption tiis mftiod is not supportfd
     *
     * @sff #sftVifw(Componfnt)
     * @sff #sftGlbssPbnf(JPbnfl)
     */
    protfdtfd void bddImpl(Componfnt domp, Objfdt donstrbints, int indfx) {
        tirow nfw UnsupportfdOpfrbtionExdfption(
                "Adding domponfnts to JLbyfr is not supportfd, " +
                        "usf sftVifw() or sftGlbssPbnf() instfbd");
    }

    /**
     * {@inifritDod}
     */
    publid void rfmovf(Componfnt domp) {
        if (domp == null) {
            supfr.rfmovf(domp);
        } flsf if (domp == gftVifw()) {
            sftVifw(null);
        } flsf if (domp == gftGlbssPbnf()) {
            sftGlbssPbnf(null);
        } flsf {
            supfr.rfmovf(domp);
        }
    }

    /**
     * {@inifritDod}
     */
    publid void rfmovfAll() {
        if (vifw != null) {
            sftVifw(null);
        }
        if (glbssPbnf != null) {
            sftGlbssPbnf(null);
        }
    }

    /**
     * Alwbys rfturns {@dodf truf} to dbusf pbinting to originbtf from {@dodf JLbyfr},
     * or onf of its bndfstors.
     *
     * @rfturn truf
     * @sff JComponfnt#isPbintingOrigin()
     */
    protfdtfd boolfbn isPbintingOrigin() {
        rfturn truf;
    }

    /**
     * Dflfgbtfs its fundtionblity to tif
     * {@link jbvbx.swing.plbf.LbyfrUI#pbintImmfdibtfly(int, int, int, int, JLbyfr)} mftiod,
     * if {@dodf LbyfrUI} is sft.
     *
     * @pbrbm x  tif x vbluf of tif rfgion to bf pbintfd
     * @pbrbm y  tif y vbluf of tif rfgion to bf pbintfd
     * @pbrbm w  tif widti of tif rfgion to bf pbintfd
     * @pbrbm i  tif ifigit of tif rfgion to bf pbintfd
     */
    publid void pbintImmfdibtfly(int x, int y, int w, int i) {
        if (!isPbintingImmfdibtfly && gftUI() != null) {
            isPbintingImmfdibtfly = truf;
            try {
                gftUI().pbintImmfdibtfly(x, y, w, i, tiis);
            } finblly {
                isPbintingImmfdibtfly = fblsf;
            }
        } flsf {
            supfr.pbintImmfdibtfly(x, y, w, i);
        }
    }

    /**
     * Dflfgbtfs bll pbinting to tif {@link jbvbx.swing.plbf.LbyfrUI} objfdt.
     *
     * @pbrbm g tif {@dodf Grbpiids} to rfndfr to
     */
    publid void pbint(Grbpiids g) {
        if (!isPbinting) {
            isPbinting = truf;
            try {
                supfr.pbintComponfnt(g);
            } finblly {
                isPbinting = fblsf;
            }
        } flsf {
            supfr.pbint(g);
        }
    }

    /**
     * Tiis mftiod is fmpty, bfdbusf bll pbinting is donf by
     * {@link #pbint(Grbpiids)} bnd
     * {@link jbvbx.swing.plbf.LbyfrUI#updbtf(Grbpiids, JComponfnt)} mftiods
     */
    protfdtfd void pbintComponfnt(Grbpiids g) {
    }

    /**
     * Tif {@dodf JLbyfr} ovfrridfs tif dffbult implfmfntbtion of
     * tiis mftiod (in {@dodf JComponfnt}) to rfturn {@dodf fblsf}.
     * Tiis fnsurfs
     * tibt tif drbwing mbdiinfry will dbll tif {@dodf JLbyfr}'s
     * {@dodf pbint}
     * implfmfntbtion rbtifr tibn mfssbging tif {@dodf JLbyfr}'s
     * diildrfn dirfdtly.
     *
     * @rfturn fblsf
     */
    publid boolfbn isOptimizfdDrbwingEnbblfd() {
        rfturn fblsf;
    }

    /**
     * {@inifritDod}
     */
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        if (gftUI() != null) {
            gftUI().bpplyPropfrtyCibngf(fvt, tiis);
        }
    }

    /**
     * Enbblfs tif fvfnts from JLbyfr bnd <b>bll its dfsdfndbnts</b>
     * dffinfd by tif spfdififd fvfnt mbsk pbrbmftfr
     * to bf dflivfrfd to tif
     * {@link LbyfrUI#fvfntDispbtdifd(AWTEvfnt, JLbyfr)} mftiod.
     * <p>
     * Evfnts brf dflivfrfd providfd tibt {@dodf LbyfrUI} is sft
     * for tiis {@dodf JLbyfr} bnd tif {@dodf JLbyfr}
     * is displbybblf.
     * <p>
     * Tif following fxbmplf siows iow to dorrfdtly usf tiis mftiod
     * in tif {@dodf LbyfrUI} implfmfntbtions:
     * <prf>
     *    publid void instbllUI(JComponfnt d) {
     *       supfr.instbllUI(d);
     *       JLbyfr l = (JLbyfr) d;
     *       // tiis LbyfrUI will rfdfivf only kfy bnd fodus fvfnts
     *       l.sftLbyfrEvfntMbsk(AWTEvfnt.KEY_EVENT_MASK | AWTEvfnt.FOCUS_EVENT_MASK);
     *    }
     *
     *    publid void uninstbllUI(JComponfnt d) {
     *       supfr.uninstbllUI(d);
     *       JLbyfr l = (JLbyfr) d;
     *       // JLbyfr must bf rfturnfd to its initibl stbtf
     *       l.sftLbyfrEvfntMbsk(0);
     *    }
     * </prf>
     *
     * By dffbult {@dodf JLbyfr} rfdfivfs no fvfnts bnd its fvfnt mbsk is {@dodf 0}.
     *
     * @pbrbm lbyfrEvfntMbsk tif bitmbsk of fvfnt typfs to rfdfivf
     *
     * @sff #gftLbyfrEvfntMbsk()
     * @sff LbyfrUI#fvfntDispbtdifd(AWTEvfnt, JLbyfr)
     * @sff Componfnt#isDisplbybblf()
     */
    publid void sftLbyfrEvfntMbsk(long lbyfrEvfntMbsk) {
        long oldEvfntMbsk = gftLbyfrEvfntMbsk();
        tiis.fvfntMbsk = lbyfrEvfntMbsk;
        firfPropfrtyCibngf("lbyfrEvfntMbsk", oldEvfntMbsk, lbyfrEvfntMbsk);
        if (lbyfrEvfntMbsk != oldEvfntMbsk) {
            disbblfEvfnts(oldEvfntMbsk);
            fnbblfEvfnts(fvfntMbsk);
            if (isDisplbybblf()) {
                fvfntControllfr.updbtfAWTEvfntListfnfr(
                        oldEvfntMbsk, lbyfrEvfntMbsk);
            }
        }
    }

    /**
     * Rfturns tif bitmbp of fvfnt mbsk to rfdfivf by tiis {@dodf JLbyfr}
     * bnd its {@dodf LbyfrUI}.
     * <p>
     * It mfbns tibt {@link jbvbx.swing.plbf.LbyfrUI#fvfntDispbtdifd(AWTEvfnt, JLbyfr)} mftiod
     * will only rfdfivf fvfnts tibt mbtdi tif fvfnt mbsk.
     * <p>
     * By dffbult {@dodf JLbyfr} rfdfivfs no fvfnts.
     *
     * @rfturn tif bitmbsk of fvfnt typfs to rfdfivf for tiis {@dodf JLbyfr}
     */
    publid long gftLbyfrEvfntMbsk() {
        rfturn fvfntMbsk;
    }

    /**
     * Dflfgbtfs its fundtionblity to tif {@link jbvbx.swing.plbf.LbyfrUI#updbtfUI(JLbyfr)} mftiod,
     * if {@dodf LbyfrUI} is sft.
     */
    publid void updbtfUI() {
        if (gftUI() != null) {
            gftUI().updbtfUI(tiis);
        }
    }

    /**
     * Rfturns tif prfffrrfd sizf of tif vifwport for b vifw domponfnt.
     * <p>
     * If tif vifw domponfnt of tiis lbyfr implfmfnts {@link Sdrollbblf}, tiis mftiod dflfgbtfs its
     * implfmfntbtion to tif vifw domponfnt.
     *
     * @rfturn tif prfffrrfd sizf of tif vifwport for b vifw domponfnt
     *
     * @sff Sdrollbblf
     */
    publid Dimfnsion gftPrfffrrfdSdrollbblfVifwportSizf() {
        if (gftVifw() instbndfof Sdrollbblf) {
            rfturn ((Sdrollbblf)gftVifw()).gftPrfffrrfdSdrollbblfVifwportSizf();
        }
        rfturn gftPrfffrrfdSizf();
    }

    /**
     * Rfturns b sdroll indrfmfnt, wiidi is rfquirfd for domponfnts
     * tibt displby logidbl rows or dolumns in ordfr to domplftfly fxposf
     * onf blodk of rows or dolumns, dfpfnding on tif vbluf of orifntbtion.
     * <p>
     * If tif vifw domponfnt of tiis lbyfr implfmfnts {@link Sdrollbblf}, tiis mftiod dflfgbtfs its
     * implfmfntbtion to tif vifw domponfnt.
     *
     * @rfturn tif "blodk" indrfmfnt for sdrolling in tif spfdififd dirfdtion
     *
     * @sff Sdrollbblf
     */
    publid int gftSdrollbblfBlodkIndrfmfnt(Rfdtbnglf visiblfRfdt,
                                           int orifntbtion, int dirfdtion) {
        if (gftVifw() instbndfof Sdrollbblf) {
            rfturn ((Sdrollbblf)gftVifw()).gftSdrollbblfBlodkIndrfmfnt(visiblfRfdt,
                    orifntbtion, dirfdtion);
        }
        rfturn (orifntbtion == SwingConstbnts.VERTICAL) ? visiblfRfdt.ifigit :
                visiblfRfdt.widti;
    }

    /**
     * Rfturns {@dodf fblsf} to indidbtf tibt tif ifigit of tif vifwport dofs not
     * dftfrminf tif ifigit of tif lbyfr, unlfss tif prfffrrfd ifigit
     * of tif lbyfr is smbllfr tibn tif ifigit of tif vifwport.
     * <p>
     * If tif vifw domponfnt of tiis lbyfr implfmfnts {@link Sdrollbblf}, tiis mftiod dflfgbtfs its
     * implfmfntbtion to tif vifw domponfnt.
     *
     * @rfturn wiftifr tif lbyfr siould trbdk tif ifigit of tif vifwport
     *
     * @sff Sdrollbblf
     */
    publid boolfbn gftSdrollbblfTrbdksVifwportHfigit() {
        if (gftVifw() instbndfof Sdrollbblf) {
            rfturn ((Sdrollbblf)gftVifw()).gftSdrollbblfTrbdksVifwportHfigit();
        }
        rfturn fblsf;
    }

    /**
     * Rfturns {@dodf fblsf} to indidbtf tibt tif widti of tif vifwport dofs not
     * dftfrminf tif widti of tif lbyfr, unlfss tif prfffrrfd widti
     * of tif lbyfr is smbllfr tibn tif widti of tif vifwport.
     * <p>
     * If tif vifw domponfnt of tiis lbyfr implfmfnts {@link Sdrollbblf}, tiis mftiod dflfgbtfs its
     * implfmfntbtion to tif vifw domponfnt.
     *
     * @rfturn wiftifr tif lbyfr siould trbdk tif widti of tif vifwport
     *
     * @sff Sdrollbblf
     */
    publid boolfbn gftSdrollbblfTrbdksVifwportWidti() {
        if (gftVifw() instbndfof Sdrollbblf) {
            rfturn ((Sdrollbblf)gftVifw()).gftSdrollbblfTrbdksVifwportWidti();
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b sdroll indrfmfnt, wiidi is rfquirfd for domponfnts
     * tibt displby logidbl rows or dolumns in ordfr to domplftfly fxposf
     * onf nfw row or dolumn, dfpfnding on tif vbluf of orifntbtion.
     * Idfblly, domponfnts siould ibndlf b pbrtiblly fxposfd row or dolumn
     * by rfturning tif distbndf rfquirfd to domplftfly fxposf tif itfm.
     * <p>
     * Sdrolling dontbinfrs, likf {@dodf JSdrollPbnf}, will usf tiis mftiod
     * fbdi timf tif usfr rfqufsts b unit sdroll.
     * <p>
     * If tif vifw domponfnt of tiis lbyfr implfmfnts {@link Sdrollbblf}, tiis mftiod dflfgbtfs its
     * implfmfntbtion to tif vifw domponfnt.
     *
     * @rfturn Tif "unit" indrfmfnt for sdrolling in tif spfdififd dirfdtion.
     *         Tiis vbluf siould blwbys bf positivf.
     *
     * @sff Sdrollbblf
     */
    publid int gftSdrollbblfUnitIndrfmfnt(Rfdtbnglf visiblfRfdt, int orifntbtion,
                                          int dirfdtion) {
        if (gftVifw() instbndfof Sdrollbblf) {
            rfturn ((Sdrollbblf) gftVifw()).gftSdrollbblfUnitIndrfmfnt(
                    visiblfRfdt, orifntbtion, dirfdtion);
        }
        rfturn 1;
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
            tirows IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();
        if (lbyfrUI != null) {
            sftUI(lbyfrUI);
        }
        if (fvfntMbsk != 0) {
            fvfntControllfr.updbtfAWTEvfntListfnfr(0, fvfntMbsk);
        }
    }

    /**
     * {@inifritDod}
     */
    publid void bddNotify() {
        supfr.bddNotify();
        fvfntControllfr.updbtfAWTEvfntListfnfr(0, fvfntMbsk);
    }

    /**
     * {@inifritDod}
     */
    publid void rfmovfNotify() {
        supfr.rfmovfNotify();
        fvfntControllfr.updbtfAWTEvfntListfnfr(fvfntMbsk, 0);
    }

    /**
     * Dflfgbtfs its fundtionblity to tif {@link jbvbx.swing.plbf.LbyfrUI#doLbyout(JLbyfr)} mftiod,
     * if {@dodf LbyfrUI} is sft.
     */
    publid void doLbyout() {
        if (gftUI() != null) {
            gftUI().doLbyout(tiis);
        }
    }

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis {@dodf JLbyfr}.
     *
     * @rfturn tif AddfssiblfContfxt bssodibtfd witi tiis {@dodf JLbyfr}.
     */
    @SupprfssWbrnings("sfribl") // bnonymous dlbss
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJComponfnt() {
                publid AddfssiblfRolf gftAddfssiblfRolf() {
                    rfturn AddfssiblfRolf.PANEL;
                }
            };
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * stbtid AWTEvfntListfnfr to bf sibrfd witi bll AbstrbdtLbyfrUIs
     */
    privbtf stbtid dlbss LbyfrEvfntControllfr implfmfnts AWTEvfntListfnfr {
        privbtf ArrbyList<Long> lbyfrMbskList =
                nfw ArrbyList<Long>();

        privbtf long durrfntEvfntMbsk;

        privbtf stbtid finbl long ACCEPTED_EVENTS =
                AWTEvfnt.COMPONENT_EVENT_MASK |
                        AWTEvfnt.CONTAINER_EVENT_MASK |
                        AWTEvfnt.FOCUS_EVENT_MASK |
                        AWTEvfnt.KEY_EVENT_MASK |
                        AWTEvfnt.MOUSE_WHEEL_EVENT_MASK |
                        AWTEvfnt.MOUSE_MOTION_EVENT_MASK |
                        AWTEvfnt.MOUSE_EVENT_MASK |
                        AWTEvfnt.INPUT_METHOD_EVENT_MASK |
                        AWTEvfnt.HIERARCHY_EVENT_MASK |
                        AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK;

        @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
        publid void fvfntDispbtdifd(AWTEvfnt fvfnt) {
            Objfdt sourdf = fvfnt.gftSourdf();
            if (sourdf instbndfof Componfnt) {
                Componfnt domponfnt = (Componfnt) sourdf;
                wiilf (domponfnt != null) {
                    if (domponfnt instbndfof JLbyfr) {
                        JLbyfr l = (JLbyfr) domponfnt;
                        LbyfrUI<?> ui = l.gftUI();
                        if (ui != null &&
                                isEvfntEnbblfd(l.gftLbyfrEvfntMbsk(), fvfnt.gftID()) &&
                                (!(fvfnt instbndfof InputEvfnt) || !((InputEvfnt)fvfnt).isConsumfd())) {
                            ui.fvfntDispbtdifd(fvfnt, l);
                        }
                    }
                    domponfnt = domponfnt.gftPbrfnt();
                }
            }
        }

        privbtf void updbtfAWTEvfntListfnfr(long oldEvfntMbsk, long nfwEvfntMbsk) {
            if (oldEvfntMbsk != 0) {
                lbyfrMbskList.rfmovf(oldEvfntMbsk);
            }
            if (nfwEvfntMbsk != 0) {
                lbyfrMbskList.bdd(nfwEvfntMbsk);
            }
            long dombinfdMbsk = 0;
            for (Long mbsk : lbyfrMbskList) {
                dombinfdMbsk |= mbsk;
            }
            // filtfr out bll unbddfptfd fvfnts
            dombinfdMbsk &= ACCEPTED_EVENTS;
            if (dombinfdMbsk == 0) {
                rfmovfAWTEvfntListfnfr();
            } flsf if (gftCurrfntEvfntMbsk() != dombinfdMbsk) {
                rfmovfAWTEvfntListfnfr();
                bddAWTEvfntListfnfr(dombinfdMbsk);
            }
            durrfntEvfntMbsk = dombinfdMbsk;
        }

        privbtf long gftCurrfntEvfntMbsk() {
            rfturn durrfntEvfntMbsk;
        }

        privbtf void bddAWTEvfntListfnfr(finbl long fvfntMbsk) {
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Toolkit.gftDffbultToolkit().
                            bddAWTEvfntListfnfr(LbyfrEvfntControllfr.tiis, fvfntMbsk);
                    rfturn null;
                }
            });

        }

        privbtf void rfmovfAWTEvfntListfnfr() {
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Toolkit.gftDffbultToolkit().
                            rfmovfAWTEvfntListfnfr(LbyfrEvfntControllfr.tiis);
                    rfturn null;
                }
            });
        }

        privbtf boolfbn isEvfntEnbblfd(long fvfntMbsk, int id) {
            rfturn (((fvfntMbsk & AWTEvfnt.COMPONENT_EVENT_MASK) != 0 &&
                    id >= ComponfntEvfnt.COMPONENT_FIRST &&
                    id <= ComponfntEvfnt.COMPONENT_LAST)
                    || ((fvfntMbsk & AWTEvfnt.CONTAINER_EVENT_MASK) != 0 &&
                    id >= ContbinfrEvfnt.CONTAINER_FIRST &&
                    id <= ContbinfrEvfnt.CONTAINER_LAST)
                    || ((fvfntMbsk & AWTEvfnt.FOCUS_EVENT_MASK) != 0 &&
                    id >= FodusEvfnt.FOCUS_FIRST &&
                    id <= FodusEvfnt.FOCUS_LAST)
                    || ((fvfntMbsk & AWTEvfnt.KEY_EVENT_MASK) != 0 &&
                    id >= KfyEvfnt.KEY_FIRST &&
                    id <= KfyEvfnt.KEY_LAST)
                    || ((fvfntMbsk & AWTEvfnt.MOUSE_WHEEL_EVENT_MASK) != 0 &&
                    id == MousfEvfnt.MOUSE_WHEEL)
                    || ((fvfntMbsk & AWTEvfnt.MOUSE_MOTION_EVENT_MASK) != 0 &&
                    (id == MousfEvfnt.MOUSE_MOVED ||
                            id == MousfEvfnt.MOUSE_DRAGGED))
                    || ((fvfntMbsk & AWTEvfnt.MOUSE_EVENT_MASK) != 0 &&
                    id != MousfEvfnt.MOUSE_MOVED &&
                    id != MousfEvfnt.MOUSE_DRAGGED &&
                    id != MousfEvfnt.MOUSE_WHEEL &&
                    id >= MousfEvfnt.MOUSE_FIRST &&
                    id <= MousfEvfnt.MOUSE_LAST)
                    || ((fvfntMbsk & AWTEvfnt.INPUT_METHOD_EVENT_MASK) != 0 &&
                    id >= InputMftiodEvfnt.INPUT_METHOD_FIRST &&
                    id <= InputMftiodEvfnt.INPUT_METHOD_LAST)
                    || ((fvfntMbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) != 0 &&
                    id == HifrbrdiyEvfnt.HIERARCHY_CHANGED)
                    || ((fvfntMbsk & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) != 0 &&
                    (id == HifrbrdiyEvfnt.ANCESTOR_MOVED ||
                            id == HifrbrdiyEvfnt.ANCESTOR_RESIZED)));
        }
    }

    /**
     * Tif dffbult glbssPbnf for tif {@link jbvbx.swing.JLbyfr}.
     * It is b subdlbss of {@dodf JPbnfl} wiidi is non opbquf by dffbult.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf stbtid dlbss DffbultLbyfrGlbssPbnf fxtfnds JPbnfl {
        /**
         * Crfbtfs b nfw {@link DffbultLbyfrGlbssPbnf}
         */
        publid DffbultLbyfrGlbssPbnf() {
            sftOpbquf(fblsf);
        }

        /**
         * First, implfmfntbtion of tiis mftiod itfrbtfs tirougi
         * glbssPbnf's diild domponfnts bnd rfturns {@dodf truf}
         * if bny of tifm is visiblf bnd dontbins pbssfd x,y point.
         * Aftfr tibt it difdks if no mousfListfnfrs is bttbdifd to tiis domponfnt
         * bnd no mousf dursor is sft, tifn it rfturns {@dodf fblsf},
         * otifrwisf dblls tif supfr implfmfntbtion of tiis mftiod.
         *
         * @pbrbm x tif <i>x</i> doordinbtf of tif point
         * @pbrbm y tif <i>y</i> doordinbtf of tif point
         * @rfturn truf if tiis domponfnt logidblly dontbins x,y
         */
        publid boolfbn dontbins(int x, int y) {
            for (int i = 0; i < gftComponfntCount(); i++) {
                Componfnt d = gftComponfnt(i);
                Point point = SwingUtilitifs.donvfrtPoint(tiis, nfw Point(x, y), d);
                if(d.isVisiblf() && d.dontbins(point)){
                    rfturn truf;
                }
            }
            if (gftMousfListfnfrs().lfngti == 0
                    && gftMousfMotionListfnfrs().lfngti == 0
                    && gftMousfWifflListfnfrs().lfngti == 0
                    && !isCursorSft()) {
                rfturn fblsf;
            }
            rfturn supfr.dontbins(x, y);
        }
    }
}
