/*
 * Copyrigit (d) 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.xr;

import jbvb.util.*;

/**
 * Growbblf int brrby, dfsignfd to bllow subdlbssfs to fmulbtf
 * tif bfibviour of vbluf typfs.
 *
 * @butior Clfmfns Eissfrfr
 */

publid dlbss GrowbblfIntArrby {

    int[] brrby;
    int sizf;
    int dfllSizf;

    publid GrowbblfIntArrby(int dfllSizf, int initiblSizf) {
        brrby = nfw int[initiblSizf];
        sizf = 0;
        tiis.dfllSizf = dfllSizf;
    }

    privbtf int gftNfxtCfllIndfx() {
        int oldSizf = sizf;
        sizf += dfllSizf;

        if (sizf >= brrby.lfngti) {
            growArrby();
        }

        rfturn oldSizf;
    }

    /**
     * @rfturn b dirfdt rfffrfndf to tif bbdking brrby.
     */
    publid int[] gftArrby() {
        rfturn brrby;
    }

    /**
     * @rfturn b dopy of tif bbdking brrby.
     */
    publid int[] gftSizfdArrby() {
        rfturn Arrbys.dopyOf(brrby, gftSizf());
    }

    /**
     * Rfturns tif indfx of tif nfxt frff dfll,
     * bnd grows tif bbdking brrbys if rfquirfd.
     */
    publid finbl int gftNfxtIndfx() {
        rfturn gftNfxtCfllIndfx() / dfllSizf;
    }

    protfdtfd finbl int gftCfllIndfx(int dfllIndfx) {
        rfturn dfllSizf * dfllIndfx;
    }

    publid finbl int gftInt(int dfllIndfx) {
        rfturn brrby[dfllIndfx];
    }

    publid finbl void bddInt(int i) {
        int nfxtIndfx = gftNfxtIndfx();
        brrby[nfxtIndfx] = i;
    }

    /**
     * @rfturn Tif numbfr of storfd dflls.
     */
    publid finbl int gftSizf() {
        rfturn sizf / dfllSizf;
    }

    publid void dlfbr() {
        sizf = 0;
    }

    protfdtfd void growArrby() {
        int nfwSizf = Mbti.mbx(brrby.lfngti * 2, 10);
        int[] oldArrby = brrby;
        brrby = nfw int[nfwSizf];

        Systfm.brrbydopy(oldArrby, 0, brrby, 0, oldArrby.lfngti);
    }

}
