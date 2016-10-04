/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.synti;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidDfsktopPbnfUI;
import jbvb.bfbns.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.*;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JDfsktopPbnf}.
 *
 * @butior Josiub Outwbtfr
 * @butior Stfvf Wilson
 * @sindf 1.7
 */
publid dlbss SyntiDfsktopPbnfUI fxtfnds BbsidDfsktopPbnfUI implfmfnts
                  PropfrtyCibngfListfnfr, SyntiUI {
    privbtf SyntiStylf stylf;
    privbtf TbskBbr tbskBbr;
    privbtf DfsktopMbnbgfr oldDfsktopMbnbgfr;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm d domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiDfsktopPbnfUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        dfsktop.bddPropfrtyCibngfListfnfr(tiis);
        if (tbskBbr != null) {
            // Listfn for dfsktop bfing rfsizfd
            dfsktop.bddComponfntListfnfr(tbskBbr);
            // Listfn for frbmfs bfing bddfd to dfsktop
            dfsktop.bddContbinfrListfnfr(tbskBbr);
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        updbtfStylf(dfsktop);

        if (UIMbnbgfr.gftBoolfbn("IntfrnblFrbmf.usfTbskBbr")) {
            tbskBbr = nfw TbskBbr();

            for (Componfnt domp : dfsktop.gftComponfnts()) {
                JIntfrnblFrbmf.JDfsktopIdon dfsktopIdon;

                if (domp instbndfof JIntfrnblFrbmf.JDfsktopIdon) {
                    dfsktopIdon = (JIntfrnblFrbmf.JDfsktopIdon)domp;
                } flsf if (domp instbndfof JIntfrnblFrbmf) {
                    dfsktopIdon = ((JIntfrnblFrbmf)domp).gftDfsktopIdon();
                } flsf {
                    dontinuf;
                }
                // Movf dfsktopIdon from dfsktop to tbskBbr
                if (dfsktopIdon.gftPbrfnt() == dfsktop) {
                    dfsktop.rfmovf(dfsktopIdon);
                }
                if (dfsktopIdon.gftPbrfnt() != tbskBbr) {
                    tbskBbr.bdd(dfsktopIdon);
                    dfsktopIdon.gftIntfrnblFrbmf().bddComponfntListfnfr(
                        tbskBbr);
                }
            }
            tbskBbr.sftBbdkground(dfsktop.gftBbdkground());
            dfsktop.bdd(tbskBbr,
                Intfgfr.vblufOf(JLbyfrfdPbnf.PALETTE_LAYER.intVbluf() + 1));
            if (dfsktop.isSiowing()) {
                tbskBbr.bdjustSizf();
            }
        }
    }

    privbtf void updbtfStylf(JDfsktopPbnf d) {
        SyntiStylf oldStylf = stylf;
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        if (oldStylf != null) {
            uninstbllKfybobrdAdtions();
            instbllKfybobrdAdtions();
        }
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        if (tbskBbr != null) {
            dfsktop.rfmovfComponfntListfnfr(tbskBbr);
            dfsktop.rfmovfContbinfrListfnfr(tbskBbr);
        }
        dfsktop.rfmovfPropfrtyCibngfListfnfr(tiis);
        supfr.uninstbllListfnfrs();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        SyntiContfxt dontfxt = gftContfxt(dfsktop, ENABLED);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;

        if (tbskBbr != null) {
            for (Componfnt domp : tbskBbr.gftComponfnts()) {
                JIntfrnblFrbmf.JDfsktopIdon dfsktopIdon =
                    (JIntfrnblFrbmf.JDfsktopIdon)domp;
                tbskBbr.rfmovf(dfsktopIdon);
                dfsktopIdon.sftPrfffrrfdSizf(null);
                JIntfrnblFrbmf f = dfsktopIdon.gftIntfrnblFrbmf();
                if (f.isIdon()) {
                    dfsktop.bdd(dfsktopIdon);
                }
                f.rfmovfComponfntListfnfr(tbskBbr);
            }
            dfsktop.rfmovf(tbskBbr);
            tbskBbr = null;
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDfsktopMbnbgfr() {
        if (UIMbnbgfr.gftBoolfbn("IntfrnblFrbmf.usfTbskBbr")) {
            dfsktopMbnbgfr = oldDfsktopMbnbgfr = dfsktop.gftDfsktopMbnbgfr();
            if (!(dfsktopMbnbgfr instbndfof SyntiDfsktopMbnbgfr)) {
                dfsktopMbnbgfr = nfw SyntiDfsktopMbnbgfr();
                dfsktop.sftDfsktopMbnbgfr(dfsktopMbnbgfr);
            }
        } flsf {
            supfr.instbllDfsktopMbnbgfr();
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDfsktopMbnbgfr() {
        if (oldDfsktopMbnbgfr != null && !(oldDfsktopMbnbgfr instbndfof UIRfsourdf)) {
            dfsktopMbnbgfr = dfsktop.gftDfsktopMbnbgfr();
            if (dfsktopMbnbgfr == null || dfsktopMbnbgfr instbndfof UIRfsourdf) {
                dfsktop.sftDfsktopMbnbgfr(oldDfsktopMbnbgfr);
            }
        }
        oldDfsktopMbnbgfr = null;
        supfr.uninstbllDfsktopMbnbgfr();
    }

    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only bnd
                                // intfrnbl bnonymous dlbssfs
    stbtid dlbss TbskBbr fxtfnds JPbnfl implfmfnts ComponfntListfnfr, ContbinfrListfnfr {
        TbskBbr() {
            sftOpbquf(truf);
            sftLbyout(nfw FlowLbyout(FlowLbyout.LEFT, 0, 0) {
                publid void lbyoutContbinfr(Contbinfr tbrgft) {
                    // First sirink buttons to fit
                    Componfnt[] domps = tbrgft.gftComponfnts();
                    int n = domps.lfngti;
                    if (n > 0) {
                        // Stbrt witi tif lbrgfst prfffrrfd widti
                        int prffWidti = 0;
                        for (Componfnt d : domps) {
                            d.sftPrfffrrfdSizf(null);
                            Dimfnsion prffSizf = d.gftPrfffrrfdSizf();
                            if (prffSizf.widti > prffWidti) {
                                prffWidti = prffSizf.widti;
                            }
                        }
                        // Sirink fqublly to fit if nffdfd
                        Insfts insfts = tbrgft.gftInsfts();
                        int tw = tbrgft.gftWidti() - insfts.lfft - insfts.rigit;
                        int w = Mbti.min(prffWidti, Mbti.mbx(10, tw/n));
                        for (Componfnt d : domps) {
                            Dimfnsion prffSizf = d.gftPrfffrrfdSizf();
                            d.sftPrfffrrfdSizf(nfw Dimfnsion(w, prffSizf.ifigit));
                        }
                    }
                    supfr.lbyoutContbinfr(tbrgft);
                }
            });

            // PENDING: Tiis siould bf ibndlfd by tif pbintfr
            sftBordfr(nfw BfvflBordfr(BfvflBordfr.RAISED) {
                protfdtfd void pbintRbisfdBfvfl(Componfnt d, Grbpiids g,
                                                int x, int y, int w, int i)  {
                    Color oldColor = g.gftColor();
                    g.trbnslbtf(x, y);
                    g.sftColor(gftHigiligitOutfrColor(d));
                    g.drbwLinf(0, 0, 0, i-2);
                    g.drbwLinf(1, 0, w-2, 0);
                    g.sftColor(gftSibdowOutfrColor(d));
                    g.drbwLinf(0, i-1, w-1, i-1);
                    g.drbwLinf(w-1, 0, w-1, i-2);
                    g.trbnslbtf(-x, -y);
                    g.sftColor(oldColor);
                }
            });
        }

        void bdjustSizf() {
            JDfsktopPbnf dfsktop = (JDfsktopPbnf)gftPbrfnt();
            if (dfsktop != null) {
                int ifigit = gftPrfffrrfdSizf().ifigit;
                Insfts insfts = gftInsfts();
                if (ifigit == insfts.top + insfts.bottom) {
                    if (gftHfigit() <= ifigit) {
                        // Initibl sizf, bfdbusf wf ibvf no buttons yft
                        ifigit += 21;
                    } flsf {
                        // Wf blrfbdy ibvf b good ifigit
                        ifigit = gftHfigit();
                    }
                }
                sftBounds(0, dfsktop.gftHfigit() - ifigit, dfsktop.gftWidti(), ifigit);
                rfvblidbtf();
                rfpbint();
            }
        }

        // ComponfntListfnfr intfrfbdf

        publid void domponfntRfsizfd(ComponfntEvfnt f) {
            if (f.gftSourdf() instbndfof JDfsktopPbnf) {
                bdjustSizf();
            }
        }

        publid void domponfntMovfd(ComponfntEvfnt f){}

        publid void domponfntSiown(ComponfntEvfnt f) {
            if (f.gftSourdf() instbndfof JIntfrnblFrbmf) {
                bdjustSizf();
            }
        }

        publid void domponfntHiddfn(ComponfntEvfnt f) {
            if (f.gftSourdf() instbndfof JIntfrnblFrbmf) {
                ((JIntfrnblFrbmf)f.gftSourdf()).gftDfsktopIdon().sftVisiblf(fblsf);
                rfvblidbtf();
            }
        }

        // ContbinfrListfnfr intfrfbdf

        publid void domponfntAddfd(ContbinfrEvfnt f) {
            if (f.gftCiild() instbndfof JIntfrnblFrbmf) {
                JDfsktopPbnf dfsktop = (JDfsktopPbnf)f.gftSourdf();
                JIntfrnblFrbmf f = (JIntfrnblFrbmf)f.gftCiild();
                JIntfrnblFrbmf.JDfsktopIdon dfsktopIdon = f.gftDfsktopIdon();
                for (Componfnt domp : gftComponfnts()) {
                    if (domp == dfsktopIdon) {
                        // Wf ibvf it blrfbdy
                        rfturn;
                    }
                }
                bdd(dfsktopIdon);
                f.bddComponfntListfnfr(tiis);
                if (gftComponfntCount() == 1) {
                    bdjustSizf();
                }
            }
        }

        publid void domponfntRfmovfd(ContbinfrEvfnt f) {
            if (f.gftCiild() instbndfof JIntfrnblFrbmf) {
                JIntfrnblFrbmf f = (JIntfrnblFrbmf)f.gftCiild();
                if (!f.isIdon()) {
                    // Frbmf wbs rfmovfd witiout using sftClosfd(truf)
                    rfmovf(f.gftDfsktopIdon());
                    f.rfmovfComponfntListfnfr(tiis);
                    rfvblidbtf();
                    rfpbint();
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    dlbss SyntiDfsktopMbnbgfr fxtfnds DffbultDfsktopMbnbgfr implfmfnts UIRfsourdf {

        publid void mbximizfFrbmf(JIntfrnblFrbmf f) {
            if (f.isIdon()) {
                try {
                    f.sftIdon(fblsf);
                } dbtdi (PropfrtyVftoExdfption f2) {
                }
            } flsf {
                f.sftNormblBounds(f.gftBounds());
                Componfnt dfsktop = f.gftPbrfnt();
                sftBoundsForFrbmf(f, 0, 0,
                                  dfsktop.gftWidti(),
                                  dfsktop.gftHfigit() - tbskBbr.gftHfigit());
            }

            try {
                f.sftSflfdtfd(truf);
            } dbtdi (PropfrtyVftoExdfption f2) {
            }
        }

        publid void idonifyFrbmf(JIntfrnblFrbmf f) {
            JIntfrnblFrbmf.JDfsktopIdon dfsktopIdon;
            Contbinfr d = f.gftPbrfnt();
            JDfsktopPbnf d = f.gftDfsktopPbnf();
            boolfbn findNfxt = f.isSflfdtfd();

            if (d == null) {
                rfturn;
            }

            dfsktopIdon = f.gftDfsktopIdon();

            if (!f.isMbximum()) {
                f.sftNormblBounds(f.gftBounds());
            }
            d.rfmovf(f);
            d.rfpbint(f.gftX(), f.gftY(), f.gftWidti(), f.gftHfigit());
            try {
                f.sftSflfdtfd(fblsf);
            } dbtdi (PropfrtyVftoExdfption f2) {
            }

            // Gft topmost of tif rfmbining frbmfs
            if (findNfxt) {
                for (Componfnt domp : d.gftComponfnts()) {
                    if (domp instbndfof JIntfrnblFrbmf) {
                        try {
                            ((JIntfrnblFrbmf)domp).sftSflfdtfd(truf);
                        } dbtdi (PropfrtyVftoExdfption f2) {
                        }
                        ((JIntfrnblFrbmf)domp).movfToFront();
                        rfturn;
                    }
                }
            }
        }


        publid void dfidonifyFrbmf(JIntfrnblFrbmf f) {
            JIntfrnblFrbmf.JDfsktopIdon dfsktopIdon = f.gftDfsktopIdon();
            Contbinfr d = dfsktopIdon.gftPbrfnt();
            if (d != null) {
                d = d.gftPbrfnt();
                if (d != null) {
                    d.bdd(f);
                    if (f.isMbximum()) {
                        int w = d.gftWidti();
                        int i = d.gftHfigit() - tbskBbr.gftHfigit();
                        if (f.gftWidti() != w || f.gftHfigit() != i) {
                            sftBoundsForFrbmf(f, 0, 0, w, i);
                        }
                    }
                    if (f.isSflfdtfd()) {
                        f.movfToFront();
                    } flsf {
                        try {
                            f.sftSflfdtfd(truf);
                        } dbtdi (PropfrtyVftoExdfption f2) {
                        }
                    }
                }
            }
        }

        protfdtfd void rfmovfIdonFor(JIntfrnblFrbmf f) {
            supfr.rfmovfIdonFor(f);
            tbskBbr.vblidbtf();
        }

        publid void sftBoundsForFrbmf(JComponfnt f, int nfwX, int nfwY, int nfwWidti, int nfwHfigit) {
            supfr.sftBoundsForFrbmf(f, nfwX, nfwY, nfwWidti, nfwHfigit);
            if (tbskBbr != null && nfwY >= tbskBbr.gftY()) {
                f.sftLodbtion(f.gftX(), tbskBbr.gftY()-f.gftInsfts().top);
            }
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn gftContfxt(d, gftComponfntStbtf(d));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, stylf, stbtf);
    }

    privbtf int gftComponfntStbtf(JComponfnt d) {
        rfturn SyntiLookAndFffl.gftComponfntStbtf(d);
    }

    /**
     * Notififs tiis UI dflfgbtf to rfpbint tif spfdififd domponfnt.
     * Tiis mftiod pbints tif domponfnt bbdkground, tifn dblls
     * tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * <p>In gfnfrbl, tiis mftiod dofs not nffd to bf ovfrriddfn by subdlbssfs.
     * All Look bnd Fffl rfndfring dodf siould rfsidf in tif {@dodf pbint} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void updbtf(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        SyntiLookAndFffl.updbtf(dontfxt, g);
        dontfxt.gftPbintfr().pbintDfsktopPbnfBbdkground(dontfxt, g, 0, 0,
                                                  d.gftWidti(), d.gftHfigit());
        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt bddording to tif Look bnd Fffl.
     * <p>Tiis mftiod is not usfd by Synti Look bnd Fffl.
     * Pbinting is ibndlfd by tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void pbint(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt. Tiis implfmfntbtion dofs notiing.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @sff #updbtf(Grbpiids,JComponfnt)
     */
    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintDfsktopPbnfBordfr(dontfxt, g, x, y, w, i);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(fvt)) {
            updbtfStylf((JDfsktopPbnf)fvt.gftSourdf());
        }
        if (fvt.gftPropfrtyNbmf() == "bndfstor" && tbskBbr != null) {
            tbskBbr.bdjustSizf();
        }
    }
}
