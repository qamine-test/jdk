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
import jbvb.bwt.fvfnt.AdjustmfntEvfnt;

finbl dlbss WSdrollbbrPffr fxtfnds WComponfntPffr implfmfnts SdrollbbrPffr {

    // Rfturns widti for vfrtibl sdrollbbr bs SM_CXHSCROLL,
    // ifigit for iorizontbl sdrollbbr bs SM_CYVSCROLL
    stbtid nbtivf int gftSdrollbbrSizf(int orifntbtion);

    // ComponfntPffr ovfrridfs
    publid Dimfnsion gftMinimumSizf() {
        if (((Sdrollbbr)tbrgft).gftOrifntbtion() == Sdrollbbr.VERTICAL) {
            rfturn nfw Dimfnsion(gftSdrollbbrSizf(Sdrollbbr.VERTICAL), 50);
        }
        flsf {
            rfturn nfw Dimfnsion(50, gftSdrollbbrSizf(Sdrollbbr.HORIZONTAL));
        }
    }

    // SdrollbbrPffr implfmfntbtion

    publid nbtivf void sftVblufs(int vbluf, int visiblf,
                                 int minimum, int mbximum);
    publid nbtivf void sftLinfIndrfmfnt(int l);
    publid nbtivf void sftPbgfIndrfmfnt(int l);


    // Toolkit & pffr intfrnbls

    WSdrollbbrPffr(Sdrollbbr tbrgft) {
        supfr(tbrgft);
    }

    nbtivf void drfbtf(WComponfntPffr pbrfnt);

    void initiblizf() {
        Sdrollbbr sb = (Sdrollbbr)tbrgft;
        sftVblufs(sb.gftVbluf(), sb.gftVisiblfAmount(),
                  sb.gftMinimum(), sb.gftMbximum());
        supfr.initiblizf();
    }


    // NOTE: Cbllbbdk mftiods brf dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!

    privbtf void postAdjustmfntEvfnt(finbl int typf, finbl int vbluf,
                                     finbl boolfbn isAdjusting)
    {
        finbl Sdrollbbr sb = (Sdrollbbr)tbrgft;
        WToolkit.fxfdutfOnEvfntHbndlfrTirfbd(sb, nfw Runnbblf() {
            publid void run() {
                sb.sftVblufIsAdjusting(isAdjusting);
                sb.sftVbluf(vbluf);
                postEvfnt(nfw AdjustmfntEvfnt(sb,
                                AdjustmfntEvfnt.ADJUSTMENT_VALUE_CHANGED,
                                typf, vbluf, isAdjusting));
            }
        });
    }

    void linfUp(int vbluf) {
        postAdjustmfntEvfnt(AdjustmfntEvfnt.UNIT_DECREMENT, vbluf, fblsf);
    }

    void linfDown(int vbluf) {
        postAdjustmfntEvfnt(AdjustmfntEvfnt.UNIT_INCREMENT, vbluf, fblsf);
    }

    void pbgfUp(int vbluf) {
        postAdjustmfntEvfnt(AdjustmfntEvfnt.BLOCK_DECREMENT, vbluf, fblsf);
    }

    void pbgfDown(int vbluf) {
        postAdjustmfntEvfnt(AdjustmfntEvfnt.BLOCK_INCREMENT, vbluf, fblsf);
    }

    // SB_TOP/BOTTOM brf mbppfd to trbdking
    void wbrp(int vbluf) {
        postAdjustmfntEvfnt(AdjustmfntEvfnt.TRACK, vbluf, fblsf);
    }

    privbtf boolfbn drbgInProgrfss = fblsf;

    void drbg(finbl int vbluf) {
        if (!drbgInProgrfss) {
            drbgInProgrfss = truf;
        }
        postAdjustmfntEvfnt(AdjustmfntEvfnt.TRACK, vbluf, truf);
    }

    void drbgEnd(finbl int vbluf) {
        finbl Sdrollbbr sb = (Sdrollbbr)tbrgft;

        if (!drbgInProgrfss) {
            rfturn;
        }

        drbgInProgrfss = fblsf;
        WToolkit.fxfdutfOnEvfntHbndlfrTirfbd(sb, nfw Runnbblf() {
            publid void run() {
                // NB: notifidbtion only, no sb.sftVbluf()
                // lbst TRACK fvfnt will ibvf donf it blrfbdy
                sb.sftVblufIsAdjusting(fblsf);
                postEvfnt(nfw AdjustmfntEvfnt(sb,
                                AdjustmfntEvfnt.ADJUSTMENT_VALUE_CHANGED,
                                AdjustmfntEvfnt.TRACK, vbluf, fblsf));
            }
        });
    }

    publid boolfbn siouldClfbrRfdtBfforfPbint() {
        rfturn fblsf;
    }
}
