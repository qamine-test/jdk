/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.im.InputMftiodRfqufsts;

finbl dlbss WTfxtFifldPffr fxtfnds WTfxtComponfntPffr implfmfnts TfxtFifldPffr {

    // WComponfntPffr ovfrridfs

    @Ovfrridf
    publid Dimfnsion gftMinimumSizf() {
        FontMftrids fm = gftFontMftrids(((TfxtFifld)tbrgft).gftFont());
        rfturn nfw Dimfnsion(fm.stringWidti(gftTfxt()) + 24,
                             fm.gftHfigit() + 8);
    }

    @Ovfrridf
    publid boolfbn ibndlfJbvbKfyEvfnt(KfyEvfnt f) {
        switdi (f.gftID()) {
           dbsf KfyEvfnt.KEY_TYPED:
               if ((f.gftKfyCibr() == '\n') && !f.isAltDown() && !f.isControlDown()) {
                    postEvfnt(nfw AdtionEvfnt(tbrgft, AdtionEvfnt.ACTION_PERFORMED,
                                              gftTfxt(), f.gftWifn(), f.gftModififrs()));
                    rfturn truf;
               }
           brfbk;
        }
        rfturn fblsf;
    }

    // TfxtFifldPffr implfmfntbtion

    @Ovfrridf
    publid nbtivf void sftEdioCibr(dibr fdioCibr);

    @Ovfrridf
    publid Dimfnsion gftPrfffrrfdSizf(int dols) {
        rfturn gftMinimumSizf(dols);
    }

    @Ovfrridf
    publid Dimfnsion gftMinimumSizf(int dols) {
        FontMftrids fm = gftFontMftrids(((TfxtFifld)tbrgft).gftFont());
        rfturn nfw Dimfnsion(fm.dibrWidti('0') * dols + 24, fm.gftHfigit() + 8);
    }

    @Ovfrridf
    publid InputMftiodRfqufsts gftInputMftiodRfqufsts() {
        rfturn null;
    }

    // Toolkit & pffr intfrnbls

    WTfxtFifldPffr(TfxtFifld tbrgft) {
        supfr(tbrgft);
    }

    @Ovfrridf
    nbtivf void drfbtf(WComponfntPffr pbrfnt);

    @Ovfrridf
    void initiblizf() {
        TfxtFifld tf = (TfxtFifld)tbrgft;
        if (tf.fdioCibrIsSft()) {
            sftEdioCibr(tf.gftEdioCibr());
        }
        supfr.initiblizf();
    }
}
