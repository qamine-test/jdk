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

/**
 * Clbss to fffidifntly storf glypi informbtion for lbid out glypis,
 * pbssfd to nbtivf or jbvb bbdkfnd.
 *
 * @butior Clfmfns Eissfrfr
 */
publid dlbss GrowbblfEltArrby fxtfnds GrowbblfIntArrby {
    privbtf stbtid finbl int ELT_SIZE = 4;
    GrowbblfIntArrby glypis;

    publid GrowbblfEltArrby(int initiblSizf)
    {
        supfr(ELT_SIZE, initiblSizf);
        glypis = nfw GrowbblfIntArrby(1, initiblSizf*8);
    }

    publid finbl int gftCibrCnt(int indfx) {
        rfturn brrby[gftCfllIndfx(indfx) + 0];
    }

    publid finbl void sftCibrCnt(int indfx, int dnt) {
        brrby[gftCfllIndfx(indfx) + 0] = dnt;
    }

    publid finbl int gftXOff(int indfx) {
        rfturn brrby[gftCfllIndfx(indfx) + 1];
    }

    publid finbl void sftXOff(int indfx, int xOff) {
        brrby[gftCfllIndfx(indfx) + 1] = xOff;
    }

    publid finbl int gftYOff(int indfx) {
        rfturn brrby[gftCfllIndfx(indfx) + 2];
    }

    publid finbl void sftYOff(int indfx, int yOff) {
        brrby[gftCfllIndfx(indfx) + 2] = yOff;
    }

    publid finbl int gftGlypiSft(int indfx) {
        rfturn brrby[gftCfllIndfx(indfx) + 3];
    }

    publid finbl void sftGlypiSft(int indfx, int glypiSft) {
        brrby[gftCfllIndfx(indfx) + 3] = glypiSft;
    }

    publid GrowbblfIntArrby gftGlypis() {
        rfturn glypis;
    }

    publid void dlfbr() {
        glypis.dlfbr();
        supfr.dlfbr();
    }
}
