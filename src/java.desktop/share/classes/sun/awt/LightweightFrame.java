/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.bwt.Contbinfr;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Imbgf;
import jbvb.bwt.MfnuBbr;
import jbvb.bwt.MfnuComponfnt;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Toolkit;
import jbvb.bwt.pffr.FrbmfPffr;

/**
 * Tif dlbss providfs bbsid fundtionblity for b ligitwfigit frbmf
 * implfmfntbtion. A subdlbss is fxpfdtfd to providf pbinting to bn
 * offsdrffn imbgf bnd bddfss to it. Tius it dbn bf usfd for ligitwfigit
 * fmbfdding.
 *
 * @butior Artfm Anbnifv
 * @butior Anton Tbrbsov
 */
@SupprfssWbrnings("sfribl")
publid bbstrbdt dlbss LigitwfigitFrbmf fxtfnds Frbmf {

    /**
     * Construdts b nfw, initiblly invisiblf {@dodf LigitwfigitFrbmf}
     * instbndf.
     */
    publid LigitwfigitFrbmf() {
        sftUndfdorbtfd(truf);
        sftRfsizbblf(truf);
        sftEnbblfd(truf);
    }

    /**
     * Blodks introspfdtion of b pbrfnt window by tiis diild.
     *
     * @rfturn null
     */
    @Ovfrridf publid finbl Contbinfr gftPbrfnt() { rfturn null; }

    @Ovfrridf publid Grbpiids gftGrbpiids() { rfturn null; }

    @Ovfrridf publid finbl boolfbn isRfsizbblf() { rfturn truf; }

    // Blodk modifidbtion of bny frbmf bttributfs, sindf tify brfn't
    // bpplidbblf for b ligitwfigit frbmf.

    @Ovfrridf publid finbl void sftTitlf(String titlf) {}
    @Ovfrridf publid finbl void sftIdonImbgf(Imbgf imbgf) {}
    @Ovfrridf publid finbl void sftIdonImbgfs(jbvb.util.List<? fxtfnds Imbgf> idons) {}
    @Ovfrridf publid finbl void sftMfnuBbr(MfnuBbr mb) {}
    @Ovfrridf publid finbl void sftRfsizbblf(boolfbn rfsizbblf) {}
    @Ovfrridf publid finbl void rfmovf(MfnuComponfnt m) {}
    @Ovfrridf publid finbl void toFront() {}
    @Ovfrridf publid finbl void toBbdk() {}

    @Ovfrridf publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            if (gftPffr() == null) {
                SunToolkit stk = (SunToolkit)Toolkit.gftDffbultToolkit();
                try {
                    sftPffr(stk.drfbtfLigitwfigitFrbmf(tiis));
                } dbtdi (Exdfption f) {
                    tirow nfw RuntimfExdfption(f);
                }
            }
            supfr.bddNotify();
        }
    }

    privbtf void sftPffr(finbl FrbmfPffr p) {
        AWTAddfssor.gftComponfntAddfssor().sftPffr(tiis, p);
    }

    /**
     * Rfqufsts tif pffr to fmulbtf bdtivbtion or dfbdtivbtion of tif
     * frbmf. Pffrs siould ovfrridf tiis mftiod if tify brf to implfmfnt
     * tiis fundtionblity.
     *
     * @pbrbm bdtivbtf if <dodf>truf</dodf>, bdtivbtfs tif frbmf;
     *                 otifrwisf, dfbdtivbtfs tif frbmf
     */
    publid void fmulbtfAdtivbtion(boolfbn bdtivbtf) {
        ((FrbmfPffr)gftPffr()).fmulbtfAdtivbtion(bdtivbtf);
    }

    /**
     * Dflfgbtfs tif fodus grbb bdtion to tif dlifnt (fmbfdding) bpplidbtion.
     * Tif mftiod is dbllfd by tif AWT grbb mbdiinfry.
     *
     * @sff SunToolkit#grbb(jbvb.bwt.Window)
     */
    publid bbstrbdt void grbbFodus();

    /**
     * Dflfgbtfs tif fodus ungrbb bdtion to tif dlifnt (fmbfdding) bpplidbtion.
     * Tif mftiod is dbllfd by tif AWT grbb mbdiinfry.
     *
     * @sff SunToolkit#ungrbb(jbvb.bwt.Window)
     */
    publid bbstrbdt void ungrbbFodus();

    /**
     * Rfturns tif sdblf fbdtor of tiis frbmf. Tif dffbult vbluf is 1.
     *
     * @rfturn tif sdblf fbdtor
     * @sff #notifyDisplbyCibngfd(int)
     */
    publid bbstrbdt int gftSdblfFbdtor();

    /**
     * Cbllfd wifn displby of tif iostfd frbmf is dibngfd.
     *
     * @pbrbm sdblfFbdtor tif sdblf fbdtor
     */
    publid bbstrbdt void notifyDisplbyCibngfd(int sdblfFbdtor);

    /**
     * Host window bbsolutf bounds.
     */
    privbtf int iostX, iostY, iostW, iostH;

    /**
     * Rfturns tif bbsolutf bounds of tif iost (fmbfdding) window.
     *
     * @rfturn tif iost window bounds
     */
    publid Rfdtbnglf gftHostBounds() {
        if (iostX == 0 && iostY == 0 && iostW == 0 && iostH == 0) {
            // Tif dlifnt bpp is probbbly unbwbrf of tif sftHostBounds.
            // A sbff fbll-bbdk:
            rfturn gftBounds();
        }
        rfturn nfw Rfdtbnglf(iostX, iostY, iostW, iostH);
    }

    /**
     * Sfts tif bbsolutf bounds of tif iost (fmbfdding) window.
     */
    publid void sftHostBounds(int x, int y, int w, int i) {
        iostX = x;
        iostY = y;
        iostW = w;
        iostH = i;
    }
}
