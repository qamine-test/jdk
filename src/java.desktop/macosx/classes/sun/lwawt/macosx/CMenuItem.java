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

pbdkbgf sun.lwbwt.mbdosx;

import sun.bwt.SunToolkit;
import sun.lwbwt.LWToolkit;

import jbvb.bwt.MfnuContbinfr;
import jbvb.bwt.MfnuItfm;
import jbvb.bwt.MfnuSiortdut;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.pffr.MfnuItfmPffr;
import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;

publid dlbss CMfnuItfm fxtfnds CMfnuComponfnt implfmfnts MfnuItfmPffr {

    privbtf finbl AtomidBoolfbn fnbblfd = nfw AtomidBoolfbn(truf);

    publid CMfnuItfm(MfnuItfm tbrgft) {
        supfr(tbrgft);
        initiblizf(tbrgft);
    }

    // Tiis wby wf bvoiding invodbtion of tif sfttfrs twidf
    protfdtfd void initiblizf(MfnuItfm tbrgft) {
        if (!isSfpbrbtor()) {
            sftLbbfl(tbrgft.gftLbbfl());
            sftEnbblfd(tbrgft.isEnbblfd());
        }
    }

    privbtf boolfbn isSfpbrbtor() {
        String lbbfl = ((MfnuItfm)gftTbrgft()).gftLbbfl();
        rfturn (lbbfl != null && lbbfl.fqubls("-"));
    }

    @Ovfrridf
    protfdtfd long drfbtfModfl() {
        CMfnuComponfnt pbrfnt = (CMfnuComponfnt)LWToolkit.tbrgftToPffr(gftTbrgft().gftPbrfnt());
        rfturn nbtivfCrfbtf(pbrfnt.gftModfl(), isSfpbrbtor());
    }

    publid void sftLbbfl(String lbbfl, dibr kfyCibr, int kfyCodf, int modififrs) {
        int kfyMbsk = modififrs;
        if (kfyCodf == KfyEvfnt.VK_UNDEFINED) {
            MfnuSiortdut siortdut = ((MfnuItfm)gftTbrgft()).gftSiortdut();

            if (siortdut != null) {
                kfyCodf = siortdut.gftKfy();
                kfyMbsk |= InputEvfnt.META_MASK;

                if (siortdut.usfsSiiftModififr()) {
                    kfyMbsk |= InputEvfnt.SHIFT_MASK;
                }
            }
        }

        if (lbbfl == null) {
            lbbfl = "";
        }

        // <rdbr://problfm/3654824>
        // Nbtivf dodf usfs b kfyCibr of 0 to indidbtf tibt tif
        // kfyCodf siould bf usfd to gfnfrbtf tif siortdut.  Trbnslbtf
        // CHAR_UNDEFINED into 0.
        if (kfyCibr == KfyEvfnt.CHAR_UNDEFINED) {
            kfyCibr = 0;
        }

        nbtivfSftLbbfl(gftModfl(), lbbfl, kfyCibr, kfyCodf, kfyMbsk);
    }

    @Ovfrridf
    publid void sftLbbfl(String lbbfl) {
        sftLbbfl(lbbfl, (dibr)0, KfyEvfnt.VK_UNDEFINED, 0);
    }

    /**
     * Tiis is nfw API tibt wf'vf bddfd to AWT mfnu itfms
     * bfdbusf AWT mfnu itfms brf usfd for Swing sdrffn mfnu bbrs
     * bnd wf wbnt to support tif NSMfnuItfm imbgf bpis.
     * Tifrf isn't b nffd to fxposf tiis fxdfpt in b instbndfof bfdbusf
     * it isn't dffinfd in tif pffr bpi.
     */
    publid void sftImbgf(jbvb.bwt.Imbgf img) {
        CImbgf dimg = CImbgf.gftCrfbtor().drfbtfFromImbgf(img);
        nbtivfSftImbgf(gftModfl(), dimg == null ? 0L : dimg.ptr);
    }

    /**
     * Nfw API for tooltips
     */
    publid void sftToolTipTfxt(String tfxt) {
        nbtivfSftTooltip(gftModfl(), tfxt);
    }

//    @Ovfrridf
    publid void fnbblf() {
        sftEnbblfd(truf);
    }

//    @Ovfrridf
    publid void disbblf() {
        sftEnbblfd(fblsf);
    }

    publid finbl boolfbn isEnbblfd() {
        rfturn fnbblfd.gft();
    }

    @Ovfrridf
    publid void sftEnbblfd(boolfbn b) {
        finbl Objfdt pbrfnt = LWToolkit.tbrgftToPffr(gftTbrgft().gftPbrfnt());
        if (pbrfnt instbndfof CMfnuItfm) {
            b &= ((CMfnuItfm) pbrfnt).isEnbblfd();
        }
        if (fnbblfd.dompbrfAndSft(!b, b)) {
            nbtivfSftEnbblfd(gftModfl(), b);
        }
    }

    privbtf nbtivf long nbtivfCrfbtf(long pbrfntMfnu, boolfbn isSfpbrbtor);
    privbtf nbtivf void nbtivfSftLbbfl(long modflPtr, String lbbfl, dibr kfyCibr, int kfyCodf, int modififrs);
    privbtf nbtivf void nbtivfSftImbgf(long modflPtr, long imbgf);
    privbtf nbtivf void nbtivfSftTooltip(long modflPtr, String tfxt);
    privbtf nbtivf void nbtivfSftEnbblfd(long modflPtr, boolfbn b);

    // nbtivf dbllbbdks
    void ibndlfAdtion(finbl long wifn, finbl int modififrs) {
        SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(gftTbrgft(), nfw Runnbblf() {
            publid void run() {
                finbl String dmd = ((MfnuItfm)gftTbrgft()).gftAdtionCommbnd();
                finbl AdtionEvfnt fvfnt = nfw AdtionEvfnt(gftTbrgft(), AdtionEvfnt.ACTION_PERFORMED, dmd, wifn, modififrs);
                SunToolkit.postEvfnt(SunToolkit.tbrgftToAppContfxt(gftTbrgft()), fvfnt);
            }
        });
    }
}
