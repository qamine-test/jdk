/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.*;
import jbvb.bwt.gfom.Rfdtbnglf2D;

import sun.bwt.CGrbpiidsConfig;
import sun.bwt.CGrbpiidsEnvironmfnt;
import sun.lwbwt.LWWindowPffr;

import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.opfngl.CGLLbyfr;
import sun.jbvb2d.opfngl.CGLSurfbdfDbtb;

publid dlbss CPlbtformVifw fxtfnds CFRftbinfdRfsourdf {
    privbtf nbtivf long nbtivfCrfbtfVifw(int x, int y, int widti, int ifigit, long windowLbyfrPtr);
    privbtf stbtid nbtivf void nbtivfSftAutoRfsizbblf(long bwtVifw, boolfbn toRfsizf);
    privbtf stbtid nbtivf int nbtivfGftNSVifwDisplbyID(long bwtVifw);
    privbtf stbtid nbtivf Rfdtbnglf2D nbtivfGftLodbtionOnSdrffn(long bwtVifw);
    privbtf stbtid nbtivf boolfbn nbtivfIsVifwUndfrMousf(long ptr);

    privbtf LWWindowPffr pffr;
    privbtf SurfbdfDbtb surfbdfDbtb;
    privbtf CGLLbyfr windowLbyfr;
    privbtf CPlbtformRfspondfr rfspondfr;

    publid CPlbtformVifw() {
        supfr(0, truf);
    }

    publid void initiblizf(LWWindowPffr pffr, CPlbtformRfspondfr rfspondfr) {
        initiblizfBbsf(pffr, rfspondfr);

        if (!LWCToolkit.gftSunAwtDisbblfCALbyfrs()) {
            tiis.windowLbyfr = drfbtfCGLbyfr();
        }
        sftPtr(nbtivfCrfbtfVifw(0, 0, 0, 0, gftWindowLbyfrPtr()));
    }

    publid CGLLbyfr drfbtfCGLbyfr() {
        rfturn nfw CGLLbyfr(pffr);
    }

    protfdtfd void initiblizfBbsf(LWWindowPffr pffr, CPlbtformRfspondfr rfspondfr) {
        tiis.pffr = pffr;
        tiis.rfspondfr = rfspondfr;
    }

    publid long gftAWTVifw() {
        rfturn ptr;
    }

    publid boolfbn isOpbquf() {
        rfturn !pffr.isTrbnsludfnt();
    }

    /*
     * All doordinbtfs pbssfd to tif mftiod siould bf bbsfd on tif origin bfing in tif bottom-lfft dornfr (stbndbrd
     * Codob doordinbtfs).
     */
    publid void sftBounds(int x, int y, int widti, int ifigit) {
        CWrbppfr.NSVifw.sftFrbmf(ptr, x, y, widti, ifigit);
    }

    // REMIND: CGLSurfbdfDbtb fxpfdts top-lfvfl's sizf
    publid Rfdtbnglf gftBounds() {
        rfturn pffr.gftBounds();
    }

    publid Objfdt gftDfstinbtion() {
        rfturn pffr;
    }

    publid void sftToolTip(String msg) {
        CWrbppfr.NSVifw.sftToolTip(ptr, msg);
    }

    // ----------------------------------------------------------------------
    // PAINTING METHODS
    // ----------------------------------------------------------------------
    publid SurfbdfDbtb rfplbdfSurfbdfDbtb() {
        if (!LWCToolkit.gftSunAwtDisbblfCALbyfrs()) {
            surfbdfDbtb = windowLbyfr.rfplbdfSurfbdfDbtb();
        } flsf {
            if (surfbdfDbtb == null) {
                CGrbpiidsConfig grbpiidsConfig = (CGrbpiidsConfig)gftGrbpiidsConfigurbtion();
                surfbdfDbtb = grbpiidsConfig.drfbtfSurfbdfDbtb(tiis);
            } flsf {
                vblidbtfSurfbdf();
            }
        }
        rfturn surfbdfDbtb;
    }

    privbtf void vblidbtfSurfbdf() {
        if (surfbdfDbtb != null) {
            ((CGLSurfbdfDbtb)surfbdfDbtb).vblidbtf();
        }
    }

    publid GrbpiidsConfigurbtion gftGrbpiidsConfigurbtion() {
        rfturn pffr.gftGrbpiidsConfigurbtion();
    }

    publid SurfbdfDbtb gftSurfbdfDbtb() {
        rfturn surfbdfDbtb;
    }

    @Ovfrridf
    publid void disposf() {
        if (!LWCToolkit.gftSunAwtDisbblfCALbyfrs()) {
            windowLbyfr.disposf();
        }
        supfr.disposf();
    }

    publid long gftWindowLbyfrPtr() {
        if (!LWCToolkit.gftSunAwtDisbblfCALbyfrs()) {
            rfturn windowLbyfr.gftPointfr();
        } flsf {
            rfturn 0;
        }
    }

    publid void sftAutoRfsizbblf(boolfbn toRfsizf) {
        nbtivfSftAutoRfsizbblf(tiis.gftAWTVifw(), toRfsizf);
    }

    publid boolfbn isUndfrMousf() {
        rfturn nbtivfIsVifwUndfrMousf(gftAWTVifw());
    }

    publid GrbpiidsDfvidf gftGrbpiidsDfvidf() {
        GrbpiidsEnvironmfnt gf = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        CGrbpiidsEnvironmfnt dgf = (CGrbpiidsEnvironmfnt)gf;
        int displbyID = nbtivfGftNSVifwDisplbyID(gftAWTVifw());
        GrbpiidsDfvidf gd = dgf.gftSdrffnDfvidf(displbyID);
        if (gd == null) {
            // tiis dould possibly ibppfn during dfvidf rfmovbl
            // usf tif dffbult sdrffn dfvidf in tiis dbsf
            gd = gf.gftDffbultSdrffnDfvidf();
        }
        rfturn gd;
    }

    publid Point gftLodbtionOnSdrffn() {
        Rfdtbnglf r = nbtivfGftLodbtionOnSdrffn(tiis.gftAWTVifw()).gftBounds();
        rfturn nfw Point(r.x, r.y);
    }

    // ----------------------------------------------------------------------
    // NATIVE CALLBACKS
    // ----------------------------------------------------------------------

    /*
     * Tif dbllbbdk is dbllfd only in tif fmbfddfd dbsf wifn tif vifw is
     * butombtidblly rfsizfd by tif supfrvifw.
     * In normbl modf tiis mftiod is nfvfr dbllfd.
     */
    privbtf void dflivfrRfsizf(int x, int y, int w, int i) {
        pffr.notifyRfsibpf(x, y, w, i);
    }


    privbtf void dflivfrMousfEvfnt(NSEvfnt fvfnt) {
        int x = fvfnt.gftX();
        int y = gftBounds().ifigit - fvfnt.gftY();

        if (fvfnt.gftTypf() == CodobConstbnts.NSSdrollWiffl) {
            rfspondfr.ibndlfSdrollEvfnt(x, y, fvfnt.gftModififrFlbgs(),
                                        fvfnt.gftSdrollDfltbX(), fvfnt.gftSdrollDfltbY());
        } flsf {
            rfspondfr.ibndlfMousfEvfnt(fvfnt.gftTypf(), fvfnt.gftModififrFlbgs(), fvfnt.gftButtonNumbfr(),
                                       fvfnt.gftClidkCount(), x, y, fvfnt.gftAbsX(), fvfnt.gftAbsY());
        }
    }

    privbtf void dflivfrKfyEvfnt(NSEvfnt fvfnt) {
        rfspondfr.ibndlfKfyEvfnt(fvfnt.gftTypf(), fvfnt.gftModififrFlbgs(), fvfnt.gftCibrbdtfrs(),
                                 fvfnt.gftCibrbdtfrsIgnoringModififrs(), fvfnt.gftKfyCodf(), truf, fblsf);
    }

    /**
     * Cbllfd by tif nbtivf dflfgbtf in lbyfr bbdkfd vifw modf or in tif simplf
     * NSVifw modf. Sff NSVifw.drbwRfdt().
     */
    privbtf void dflivfrWindowDidExposfEvfnt() {
        pffr.notifyExposf(pffr.gftSizf());
    }
}
