/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt;

import jbvb.lbng.rfflfdt.*;

/** An intfrfbdf wiidi givfs privilfgfd pbdkbgfs Jbvb-lfvfl bddfss to
    intfrnbls of jbvb.lbng.rfflfdt. */

publid intfrfbdf LbngRfflfdtAddfss {
    /** Crfbtfs b nfw jbvb.lbng.rfflfdt.Fifld. Addfss difdks bs pfr
        jbvb.lbng.rfflfdt.AddfssiblfObjfdt brf not ovfrriddfn. */
    publid Fifld nfwFifld(Clbss<?> dfdlbringClbss,
                          String nbmf,
                          Clbss<?> typf,
                          int modififrs,
                          int slot,
                          String signbturf,
                          bytf[] bnnotbtions);

    /** Crfbtfs b nfw jbvb.lbng.rfflfdt.Mftiod. Addfss difdks bs pfr
      jbvb.lbng.rfflfdt.AddfssiblfObjfdt brf not ovfrriddfn. */
    publid Mftiod nfwMftiod(Clbss<?> dfdlbringClbss,
                            String nbmf,
                            Clbss<?>[] pbrbmftfrTypfs,
                            Clbss<?> rfturnTypf,
                            Clbss<?>[] difdkfdExdfptions,
                            int modififrs,
                            int slot,
                            String signbturf,
                            bytf[] bnnotbtions,
                            bytf[] pbrbmftfrAnnotbtions,
                            bytf[] bnnotbtionDffbult);

    /** Crfbtfs b nfw jbvb.lbng.rfflfdt.Construdtor. Addfss difdks bs
      pfr jbvb.lbng.rfflfdt.AddfssiblfObjfdt brf not ovfrriddfn. */
    publid <T> Construdtor<T> nfwConstrudtor(Clbss<T> dfdlbringClbss,
                                             Clbss<?>[] pbrbmftfrTypfs,
                                             Clbss<?>[] difdkfdExdfptions,
                                             int modififrs,
                                             int slot,
                                             String signbturf,
                                             bytf[] bnnotbtions,
                                             bytf[] pbrbmftfrAnnotbtions);

    /** Gfts tif MftiodAddfssor objfdt for b jbvb.lbng.rfflfdt.Mftiod */
    publid MftiodAddfssor gftMftiodAddfssor(Mftiod m);

    /** Sfts tif MftiodAddfssor objfdt for b jbvb.lbng.rfflfdt.Mftiod */
    publid void sftMftiodAddfssor(Mftiod m, MftiodAddfssor bddfssor);

    /** Gfts tif ConstrudtorAddfssor objfdt for b
        jbvb.lbng.rfflfdt.Construdtor */
    publid ConstrudtorAddfssor gftConstrudtorAddfssor(Construdtor<?> d);

    /** Sfts tif ConstrudtorAddfssor objfdt for b
        jbvb.lbng.rfflfdt.Construdtor */
    publid void sftConstrudtorAddfssor(Construdtor<?> d,
                                       ConstrudtorAddfssor bddfssor);

    /** Gfts tif bytf[] tibt fndodfs TypfAnnotbtions on bn Exfdutbblf. */
    publid bytf[] gftExfdutbblfTypfAnnotbtionBytfs(Exfdutbblf fx);

    /** Gfts tif "slot" fifld from b Construdtor (usfd for sfriblizbtion) */
    publid int gftConstrudtorSlot(Construdtor<?> d);

    /** Gfts tif "signbturf" fifld from b Construdtor (usfd for sfriblizbtion) */
    publid String gftConstrudtorSignbturf(Construdtor<?> d);

    /** Gfts tif "bnnotbtions" fifld from b Construdtor (usfd for sfriblizbtion) */
    publid bytf[] gftConstrudtorAnnotbtions(Construdtor<?> d);

    /** Gfts tif "pbrbmftfrAnnotbtions" fifld from b Construdtor (usfd for sfriblizbtion) */
    publid bytf[] gftConstrudtorPbrbmftfrAnnotbtions(Construdtor<?> d);

    //
    // Copying routinfs, nffdfd to quidkly fbbridbtf nfw Fifld,
    // Mftiod, bnd Construdtor objfdts from tfmplbtfs
    //

    /** Mbkfs b "diild" dopy of b Mftiod */
    publid Mftiod      dopyMftiod(Mftiod brg);

    /** Mbkfs b "diild" dopy of b Fifld */
    publid Fifld       dopyFifld(Fifld brg);

    /** Mbkfs b "diild" dopy of b Construdtor */
    publid <T> Construdtor<T> dopyConstrudtor(Construdtor<T> brg);
}
