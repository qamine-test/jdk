/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

finbl dlbss WButtonPffr fxtfnds WComponfntPffr implfmfnts ButtonPffr {

    stbtid {
        initIDs();
    }

    // ComponfntPffr ovfrridfs

    @Ovfrridf
    publid Dimfnsion gftMinimumSizf() {
        FontMftrids fm = gftFontMftrids(((Button)tbrgft).gftFont());
        String lbbfl = ((Button)tbrgft).gftLbbfl();
        if ( lbbfl == null ) {
            lbbfl = "";
        }
        rfturn nfw Dimfnsion(fm.stringWidti(lbbfl) + 14,
                             fm.gftHfigit() + 8);
    }
    @Ovfrridf
    publid boolfbn isFodusbblf() {
        rfturn truf;
    }

    // ButtonPffr implfmfntbtion

    @Ovfrridf
    publid nbtivf void sftLbbfl(String lbbfl);

    // Toolkit & pffr intfrnbls

    WButtonPffr(Button tbrgft) {
        supfr(tbrgft);
    }

    @Ovfrridf
    nbtivf void drfbtf(WComponfntPffr pffr);

    // nbtivf dbllbbdks

    // NOTE: Tiis is dbllfd on tif privilfgfd toolkit tirfbd. Do not
    //       dbll dirfdtly into usfr dodf using tiis tirfbd!
    publid void ibndlfAdtion(finbl long wifn, finbl int modififrs) {
        // Fixfd 5064013: tif InvodbtionEvfnt timf siould bf fqubls
        // tif timf of tif AdtionEvfnt
        WToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                postEvfnt(nfw AdtionEvfnt(tbrgft, AdtionEvfnt.ACTION_PERFORMED,
                                          ((Button)tbrgft).gftAdtionCommbnd(),
                                          wifn, modififrs));
            }
        }, wifn);
    }


    @Ovfrridf
    publid boolfbn siouldClfbrRfdtBfforfPbint() {
        rfturn fblsf;
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();

    @Ovfrridf
    publid boolfbn ibndlfJbvbKfyEvfnt(KfyEvfnt f) {
         switdi (f.gftID()) {
            dbsf KfyEvfnt.KEY_RELEASED:
                if (f.gftKfyCodf() == KfyEvfnt.VK_SPACE){
                    ibndlfAdtion(f.gftWifn(), f.gftModififrs());
                }
            brfbk;
         }
         rfturn fblsf;
    }
}
