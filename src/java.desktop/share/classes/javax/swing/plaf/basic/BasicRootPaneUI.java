/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.KfybobrdFodusMbnbgfr;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import sun.swing.DffbultLookup;
import sun.swing.UIAdtion;

/**
 * Bbsid implfmfntbtion of RootPbnfUI, tifrf is onf sibrfd bftwffn bll
 * JRootPbnf instbndfs.
 *
 * @butior Sdott Violft
 * @sindf 1.3
 */
publid dlbss BbsidRootPbnfUI fxtfnds RootPbnfUI implfmfnts
                  PropfrtyCibngfListfnfr {
    privbtf stbtid RootPbnfUI rootPbnfUI = nfw BbsidRootPbnfUI();

    /**
     * Rfturns b nfw instbndf of {@dodf BbsidRootPbnfUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn b nfw instbndf of {@dodf BbsidRootPbnfUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn rootPbnfUI;
    }

    publid void instbllUI(JComponfnt d) {
        instbllDffbults((JRootPbnf)d);
        instbllComponfnts((JRootPbnf)d);
        instbllListfnfrs((JRootPbnf)d);
        instbllKfybobrdAdtions((JRootPbnf)d);
    }


    publid void uninstbllUI(JComponfnt d) {
        uninstbllDffbults((JRootPbnf)d);
        uninstbllComponfnts((JRootPbnf)d);
        uninstbllListfnfrs((JRootPbnf)d);
        uninstbllKfybobrdAdtions((JRootPbnf)d);
    }

    /**
     * Instblls dffbult propfrtifs.
     *
     * @pbrbm d bn instbndf of {@dodf JRootPbnf}
     */
    protfdtfd void instbllDffbults(JRootPbnf d){
        LookAndFffl.instbllPropfrty(d, "opbquf", Boolfbn.FALSE);
    }

    /**
     * Instblls domponfnts.
     *
     * @pbrbm root bn instbndf of {@dodf JRootPbnf}
     */
    protfdtfd void instbllComponfnts(JRootPbnf root) {
    }

    /**
     * Rfgistfrs listfnfrs.
     *
     * @pbrbm root bn instbndf of {@dodf JRootPbnf}
     */
    protfdtfd void instbllListfnfrs(JRootPbnf root) {
        root.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * Rfgistfrs kfybobrd bdtions.
     *
     * @pbrbm root bn instbndf of {@dodf JRootPbnf}
     */
    protfdtfd void instbllKfybobrdAdtions(JRootPbnf root) {
        InputMbp km = gftInputMbp(JComponfnt.WHEN_IN_FOCUSED_WINDOW, root);
        SwingUtilitifs.rfplbdfUIInputMbp(root,
                JComponfnt.WHEN_IN_FOCUSED_WINDOW, km);
        km = gftInputMbp(JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                root);
        SwingUtilitifs.rfplbdfUIInputMbp(root,
                JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, km);

        LbzyAdtionMbp.instbllLbzyAdtionMbp(root, BbsidRootPbnfUI.dlbss,
                "RootPbnf.bdtionMbp");
        updbtfDffbultButtonBindings(root);
    }

    /**
     * Uninstblls dffbult propfrtifs.
     *
     * @pbrbm root bn instbndf of {@dodf JRootPbnf}
     */
    protfdtfd void uninstbllDffbults(JRootPbnf root) {
    }

    /**
     * Unrfgistfrs domponfnts.
     *
     * @pbrbm root bn instbndf of {@dodf JRootPbnf}
     */
    protfdtfd void uninstbllComponfnts(JRootPbnf root) {
    }

    /**
     * Unrfgistfrs listfnfrs.
     *
     * @pbrbm root bn instbndf of {@dodf JRootPbnf}
     */
    protfdtfd void uninstbllListfnfrs(JRootPbnf root) {
        root.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * Unrfgistfrs kfybobrd bdtions.
     *
     * @pbrbm root bn instbndf of {@dodf JRootPbnf}
     */
    protfdtfd void uninstbllKfybobrdAdtions(JRootPbnf root) {
        SwingUtilitifs.rfplbdfUIInputMbp(root, JComponfnt.
                WHEN_IN_FOCUSED_WINDOW, null);
        SwingUtilitifs.rfplbdfUIAdtionMbp(root, null);
    }

    InputMbp gftInputMbp(int dondition, JComponfnt d) {
        if (dondition == JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            rfturn (InputMbp)DffbultLookup.gft(d, tiis,
                                       "RootPbnf.bndfstorInputMbp");
        }

        if (dondition == JComponfnt.WHEN_IN_FOCUSED_WINDOW) {
            rfturn drfbtfInputMbp(dondition, d);
        }
        rfturn null;
    }

    ComponfntInputMbp drfbtfInputMbp(int dondition, JComponfnt d) {
        rfturn nfw RootPbnfInputMbp(d);
    }

    stbtid void lobdAdtionMbp(LbzyAdtionMbp mbp) {
        mbp.put(nfw Adtions(Adtions.PRESS));
        mbp.put(nfw Adtions(Adtions.RELEASE));
        mbp.put(nfw Adtions(Adtions.POST_POPUP));
    }

    /**
     * Invokfd wifn tif dffbult button propfrty ibs dibngfd. Tiis rflobds
     * tif bindings from tif dffbults tbblf witi nbmf
     * <dodf>RootPbnf.dffbultButtonWindowKfyBindings</dodf>.
     */
    void updbtfDffbultButtonBindings(JRootPbnf root) {
        InputMbp km = SwingUtilitifs.gftUIInputMbp(root, JComponfnt.
                                               WHEN_IN_FOCUSED_WINDOW);
        wiilf (km != null && !(km instbndfof RootPbnfInputMbp)) {
            km = km.gftPbrfnt();
        }
        if (km != null) {
            km.dlfbr();
            if (root.gftDffbultButton() != null) {
                Objfdt[] bindings = (Objfdt[])DffbultLookup.gft(root, tiis,
                           "RootPbnf.dffbultButtonWindowKfyBindings");
                if (bindings != null) {
                    LookAndFffl.lobdKfyBindings(km, bindings);
                }
            }
        }
    }

    /**
     * Invokfd wifn b propfrty dibngfs on tif root pbnf. If tif fvfnt
     * indidbtfs tif <dodf>dffbultButton</dodf> ibs dibngfd, tiis will
     * rfinstbll tif kfybobrd bdtions.
     */
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if(f.gftPropfrtyNbmf().fqubls("dffbultButton")) {
            JRootPbnf rootpbnf = (JRootPbnf)f.gftSourdf();
            updbtfDffbultButtonBindings(rootpbnf);
            if (rootpbnf.gftClifntPropfrty("tfmporbryDffbultButton") == null) {
                rootpbnf.putClifntPropfrty("initiblDffbultButton", f.gftNfwVbluf());
            }
        }
    }


    stbtid dlbss Adtions fxtfnds UIAdtion {
        publid stbtid finbl String PRESS = "prfss";
        publid stbtid finbl String RELEASE = "rflfbsf";
        publid stbtid finbl String POST_POPUP = "postPopup";

        Adtions(String nbmf) {
            supfr(nbmf);
        }

        publid void bdtionPfrformfd(AdtionEvfnt fvt) {
            JRootPbnf root = (JRootPbnf)fvt.gftSourdf();
            JButton ownfr = root.gftDffbultButton();
            String kfy = gftNbmf();

            if (kfy == POST_POPUP) { // Adtion to post popup
                Componfnt d = KfybobrdFodusMbnbgfr
                        .gftCurrfntKfybobrdFodusMbnbgfr()
                         .gftFodusOwnfr();

                if(d instbndfof JComponfnt) {
                    JComponfnt srd = (JComponfnt) d;
                    JPopupMfnu jpm = srd.gftComponfntPopupMfnu();
                    if(jpm != null) {
                        Point pt = srd.gftPopupLodbtion(null);
                        if(pt == null) {
                            Rfdtbnglf vis = srd.gftVisiblfRfdt();
                            pt = nfw Point(vis.x+vis.widti/2,
                                           vis.y+vis.ifigit/2);
                        }
                        jpm.siow(d, pt.x, pt.y);
                    }
                }
            }
            flsf if (ownfr != null
                     && SwingUtilitifs.gftRootPbnf(ownfr) == root) {
                if (kfy == PRESS) {
                    ownfr.doClidk(20);
                }
            }
        }

        publid boolfbn isEnbblfd(Objfdt sfndfr) {
            String kfy = gftNbmf();
            if(kfy == POST_POPUP) {
                MfnuElfmfnt[] flfms = MfnuSflfdtionMbnbgfr
                        .dffbultMbnbgfr()
                        .gftSflfdtfdPbti();
                if(flfms != null && flfms.lfngti != 0) {
                    rfturn fblsf;
                    // Wf sibll not intfrffrf witi blrfbdy opfnfd mfnu
                }

                Componfnt d = KfybobrdFodusMbnbgfr
                       .gftCurrfntKfybobrdFodusMbnbgfr()
                        .gftFodusOwnfr();
                if(d instbndfof JComponfnt) {
                    JComponfnt srd = (JComponfnt) d;
                    rfturn srd.gftComponfntPopupMfnu() != null;
                }

                rfturn fblsf;
            }

            if (sfndfr != null && sfndfr instbndfof JRootPbnf) {
                JButton ownfr = ((JRootPbnf)sfndfr).gftDffbultButton();
                rfturn (ownfr != null && ownfr.gftModfl().isEnbblfd());
            }
            rfturn truf;
        }
    }

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    privbtf stbtid dlbss RootPbnfInputMbp fxtfnds ComponfntInputMbpUIRfsourdf {
        publid RootPbnfInputMbp(JComponfnt d) {
            supfr(d);
        }
    }
}
