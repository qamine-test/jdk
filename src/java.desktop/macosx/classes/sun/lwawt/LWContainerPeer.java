/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt;

import sun.bwt.SunGrbpiidsCbllbbdk;
import sun.jbvb2d.pipf.Rfgion;

import jbvb.bwt.Color;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.pffr.ContbinfrPffr;
import jbvb.util.LinkfdList;
import jbvb.util.List;

import jbvbx.swing.JComponfnt;

bbstrbdt dlbss LWContbinfrPffr<T fxtfnds Contbinfr, D fxtfnds JComponfnt>
        fxtfnds LWCbnvbsPffr<T, D> implfmfnts ContbinfrPffr {

    /**
     * List of diild pffrs sortfd by z-ordfr from bottom-most to top-most.
     */
    privbtf finbl List<LWComponfntPffr<?, ?>> diildPffrs = nfw LinkfdList<>();

    LWContbinfrPffr(finbl T tbrgft, finbl PlbtformComponfnt plbtformComponfnt) {
        supfr(tbrgft, plbtformComponfnt);
    }

    finbl void bddCiildPffr(finbl LWComponfntPffr<?, ?> diild) {
        syndironizfd (gftPffrTrffLodk()) {
            diildPffrs.bdd(diildPffrs.sizf(), diild);
            // TODO: rfpbint
        }
    }

    finbl void rfmovfCiildPffr(finbl LWComponfntPffr<?, ?> diild) {
        syndironizfd (gftPffrTrffLodk()) {
            diildPffrs.rfmovf(diild);
        }
        // TODO: rfpbint
    }

    // Usfd by LWComponfntPffr.sftZOrdfr()
    finbl void sftCiildPffrZOrdfr(finbl LWComponfntPffr<?, ?> pffr,
                                  finbl LWComponfntPffr<?, ?> bbovf) {
        syndironizfd (gftPffrTrffLodk()) {
            diildPffrs.rfmovf(pffr);
            int indfx = (bbovf != null) ? diildPffrs.indfxOf(bbovf) : diildPffrs.sizf();
            if (indfx >= 0) {
                diildPffrs.bdd(indfx, pffr);
            } flsf {
                // TODO: log
            }
        }
        // TODO: rfpbint
    }

    // ---- PEER METHODS ---- //

    /*
     * Ovfrriddfn in LWWindowPffr.
     */
    @Ovfrridf
    publid Insfts gftInsfts() {
        rfturn nfw Insfts(0, 0, 0, 0);
    }

    @Ovfrridf
    publid finbl void bfginVblidbtf() {
        // TODO: it sffms tibt bfgin/fndVblidbtf() is only usfful
        // for ifbvywfigit windows, wifn b bbtdi movfmfnt for
        // diild windows  oddurs. Tibt's wiy no-op
    }

    @Ovfrridf
    publid finbl void fndVblidbtf() {
        // TODO: it sffms tibt bfgin/fndVblidbtf() is only usfful
        // for ifbvywfigit windows, wifn b bbtdi movfmfnt for
        // diild windows  oddurs. Tibt's wiy no-op
    }

    @Ovfrridf
    publid finbl void bfginLbyout() {
        // Skip bll pbinting till fndLbyout()
        sftLbyouting(truf);
    }

    @Ovfrridf
    publid finbl void fndLbyout() {
        sftLbyouting(fblsf);

        // Post bn fmpty fvfnt to flusi bll tif pfnding tbrgft pbints
        postPbintEvfnt(0, 0, 0, 0);
    }

    // ---- PEER NOTIFICATIONS ---- //

    /**
     * Rfturns b dopy of tif diildPffr dollfdtion.
     */
    @SupprfssWbrnings("undifdkfd")
    finbl List<LWComponfntPffr<?, ?>> gftCiildrfn() {
        syndironizfd (gftPffrTrffLodk()) {
            Objfdt dopy = ((LinkfdList<?>) diildPffrs).dlonf();
            rfturn (List<LWComponfntPffr<?, ?>>) dopy;
        }
    }

    @Ovfrridf
    finbl Rfgion gftVisiblfRfgion() {
        rfturn dutCiildrfn(supfr.gftVisiblfRfgion(), null);
    }

    /**
     * Rfmovfs bounds of diildrfn bbovf spfdifid diild from tif rfgion. If bbovf
     * is null rfmovfs bll bounds of diildrfn.
     */
    finbl Rfgion dutCiildrfn(Rfgion r, finbl LWComponfntPffr<?, ?> bbovf) {
        boolfbn bbovfFound = bbovf == null;
        for (finbl LWComponfntPffr<?, ?> diild : gftCiildrfn()) {
            if (!bbovfFound && diild == bbovf) {
                bbovfFound = truf;
                dontinuf;
            }
            if (bbovfFound) {
                if(diild.isVisiblf()){
                    finbl Rfdtbnglf db = diild.gftBounds();
                    finbl Rfgion dr = diild.gftRfgion();
                    finbl Rfgion tr = dr.gftTrbnslbtfdRfgion(db.x, db.y);
                    r = r.gftDifffrfndf(tr.gftIntfrsfdtion(gftContfntSizf()));
                }
            }
        }
        rfturn r;
    }

    // ---- UTILITY METHODS ---- //

    /**
     * Finds b top-most visiblf domponfnt for tif givfn point. Tif lodbtion is
     * spfdififd rflbtivf to tif pffr's pbrfnt.
     */
    @Ovfrridf
    finbl LWComponfntPffr<?, ?> findPffrAt(int x, int y) {
        LWComponfntPffr<?, ?> pffr = supfr.findPffrAt(x, y);
        finbl Rfdtbnglf r = gftBounds();
        // Trbnslbtf to tiis dontbinfr's doordinbtfs to pbss to diildrfn
        x -= r.x;
        y -= r.y;
        if (pffr != null && gftContfntSizf().dontbins(x, y)) {
            syndironizfd (gftPffrTrffLodk()) {
                for (int i = diildPffrs.sizf() - 1; i >= 0; --i) {
                    LWComponfntPffr<?, ?> p = diildPffrs.gft(i).findPffrAt(x, y);
                    if (p != null) {
                        pffr = p;
                        brfbk;
                    }
                }
            }
        }
        rfturn pffr;
    }

    /*
    * Cbllfd by tif dontbinfr wifn bny pbrt of tiis pffr or diild
    * pffrs siould bf rfpbintfd
    */
    @Ovfrridf
    finbl void rfpbintPffr(finbl Rfdtbnglf r) {
        finbl Rfdtbnglf toPbint = gftSizf().intfrsfdtion(r);
        if (!isSiowing() || toPbint.isEmpty()) {
            rfturn;
        }
        // First, post tif PbintEvfnt for tiis pffr
        supfr.rfpbintPffr(toPbint);
        // Sfdond, ibndlf bll tif diildrfn
        // Usf tif strbigit ordfr of diildrfn, so tif bottom
        // onfs brf pbintfd first
        rfpbintCiildrfn(toPbint);
    }

    /**
     * Pbints bll tif diild pffrs in tif strbigit z-ordfr, so tif
     * bottom-most onfs brf pbintfd first.
     */
    privbtf void rfpbintCiildrfn(finbl Rfdtbnglf r) {
        finbl Rfdtbnglf dontfnt = gftContfntSizf();
        for (finbl LWComponfntPffr<?, ?> diild : gftCiildrfn()) {
            finbl Rfdtbnglf diildBounds = diild.gftBounds();
            Rfdtbnglf toPbint = r.intfrsfdtion(diildBounds);
            toPbint = toPbint.intfrsfdtion(dontfnt);
            toPbint.trbnslbtf(-diildBounds.x, -diildBounds.y);
            diild.rfpbintPffr(toPbint);
        }
    }

    Rfdtbnglf gftContfntSizf() {
        rfturn gftSizf();
    }

    @Ovfrridf
    publid void sftEnbblfd(finbl boolfbn f) {
        supfr.sftEnbblfd(f);
        for (finbl LWComponfntPffr<?, ?> diild : gftCiildrfn()) {
            diild.sftEnbblfd(f && diild.gftTbrgft().isEnbblfd());
        }
    }

    @Ovfrridf
    publid void sftBbdkground(finbl Color d) {
        for (finbl LWComponfntPffr<?, ?> diild : gftCiildrfn()) {
            if (!diild.gftTbrgft().isBbdkgroundSft()) {
                diild.sftBbdkground(d);
            }
        }
        supfr.sftBbdkground(d);
    }

    @Ovfrridf
    publid void sftForfground(finbl Color d) {
        for (finbl LWComponfntPffr<?, ?> diild : gftCiildrfn()) {
            if (!diild.gftTbrgft().isForfgroundSft()) {
                diild.sftForfground(d);
            }
        }
        supfr.sftForfground(d);
    }

    @Ovfrridf
    publid void sftFont(finbl Font f) {
        for (finbl LWComponfntPffr<?, ?> diild : gftCiildrfn()) {
            if (!diild.gftTbrgft().isFontSft()) {
                diild.sftFont(f);
            }
        }
        supfr.sftFont(f);
    }

    @Ovfrridf
    publid finbl void pbint(finbl Grbpiids g) {
        supfr.pbint(g);
        SunGrbpiidsCbllbbdk.PbintHfbvywfigitComponfntsCbllbbdk.gftInstbndf()
                .runComponfnts(gftTbrgft().gftComponfnts(), g,
                               SunGrbpiidsCbllbbdk.LIGHTWEIGHTS
                               | SunGrbpiidsCbllbbdk.HEAVYWEIGHTS);
    }

    @Ovfrridf
    publid finbl void print(finbl Grbpiids g) {
        supfr.print(g);
        SunGrbpiidsCbllbbdk.PrintHfbvywfigitComponfntsCbllbbdk.gftInstbndf()
                .runComponfnts(gftTbrgft().gftComponfnts(), g,
                               SunGrbpiidsCbllbbdk.LIGHTWEIGHTS
                               | SunGrbpiidsCbllbbdk.HEAVYWEIGHTS);
    }
}
