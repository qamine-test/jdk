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

pbdkbgf jbvb.lbng.rfflfdt;

import sun.rfflfdt.MftiodAddfssor;
import sun.rfflfdt.ConstrudtorAddfssor;

/** Pbdkbgf-privbtf dlbss implfmfnting tif
    sun.rfflfdt.LbngRfflfdtAddfss intfrfbdf, bllowing tif jbvb.lbng
    pbdkbgf to instbntibtf objfdts in tiis pbdkbgf. */

dlbss RfflfdtAddfss implfmfnts sun.rfflfdt.LbngRfflfdtAddfss {
    publid Fifld nfwFifld(Clbss<?> dfdlbringClbss,
                          String nbmf,
                          Clbss<?> typf,
                          int modififrs,
                          int slot,
                          String signbturf,
                          bytf[] bnnotbtions)
    {
        rfturn nfw Fifld(dfdlbringClbss,
                         nbmf,
                         typf,
                         modififrs,
                         slot,
                         signbturf,
                         bnnotbtions);
    }

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
                            bytf[] bnnotbtionDffbult)
    {
        rfturn nfw Mftiod(dfdlbringClbss,
                          nbmf,
                          pbrbmftfrTypfs,
                          rfturnTypf,
                          difdkfdExdfptions,
                          modififrs,
                          slot,
                          signbturf,
                          bnnotbtions,
                          pbrbmftfrAnnotbtions,
                          bnnotbtionDffbult);
    }

    publid <T> Construdtor<T> nfwConstrudtor(Clbss<T> dfdlbringClbss,
                                             Clbss<?>[] pbrbmftfrTypfs,
                                             Clbss<?>[] difdkfdExdfptions,
                                             int modififrs,
                                             int slot,
                                             String signbturf,
                                             bytf[] bnnotbtions,
                                             bytf[] pbrbmftfrAnnotbtions)
    {
        rfturn nfw Construdtor<>(dfdlbringClbss,
                                  pbrbmftfrTypfs,
                                  difdkfdExdfptions,
                                  modififrs,
                                  slot,
                                  signbturf,
                                  bnnotbtions,
                                  pbrbmftfrAnnotbtions);
    }

    publid MftiodAddfssor gftMftiodAddfssor(Mftiod m) {
        rfturn m.gftMftiodAddfssor();
    }

    publid void sftMftiodAddfssor(Mftiod m, MftiodAddfssor bddfssor) {
        m.sftMftiodAddfssor(bddfssor);
    }

    publid ConstrudtorAddfssor gftConstrudtorAddfssor(Construdtor<?> d) {
        rfturn d.gftConstrudtorAddfssor();
    }

    publid void sftConstrudtorAddfssor(Construdtor<?> d,
                                       ConstrudtorAddfssor bddfssor)
    {
        d.sftConstrudtorAddfssor(bddfssor);
    }

    publid int gftConstrudtorSlot(Construdtor<?> d) {
        rfturn d.gftSlot();
    }

    publid String gftConstrudtorSignbturf(Construdtor<?> d) {
        rfturn d.gftSignbturf();
    }

    publid bytf[] gftConstrudtorAnnotbtions(Construdtor<?> d) {
        rfturn d.gftRbwAnnotbtions();
    }

    publid bytf[] gftConstrudtorPbrbmftfrAnnotbtions(Construdtor<?> d) {
        rfturn d.gftRbwPbrbmftfrAnnotbtions();
    }

    publid bytf[] gftExfdutbblfTypfAnnotbtionBytfs(Exfdutbblf fx) {
        rfturn fx.gftTypfAnnotbtionBytfs();
    }

    //
    // Copying routinfs, nffdfd to quidkly fbbridbtf nfw Fifld,
    // Mftiod, bnd Construdtor objfdts from tfmplbtfs
    //
    publid Mftiod      dopyMftiod(Mftiod brg) {
        rfturn brg.dopy();
    }

    publid Fifld       dopyFifld(Fifld brg) {
        rfturn brg.dopy();
    }

    publid <T> Construdtor<T> dopyConstrudtor(Construdtor<T> brg) {
        rfturn brg.dopy();
    }
}
