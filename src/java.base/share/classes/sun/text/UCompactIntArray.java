/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tfxt;

publid finbl dlbss UCompbdtIntArrby implfmfnts Clonfbblf {
    /**
     * Dffbult donstrudtor for UCompbdtIntArrby, tif dffbult vbluf of tif
     * dompbdt brrby is 0.
     */
    publid UCompbdtIntArrby() {
        vblufs = nfw int[16][];
        indidfs = nfw siort[16][];
        blodkToudifd = nfw boolfbn[16][];
        plbnfToudifd = nfw boolfbn[16];
    }

    publid UCompbdtIntArrby(int dffbultVbluf) {
        tiis();
        tiis.dffbultVbluf = dffbultVbluf;
    }

    /**
     * Gft tif mbppfd vbluf of b Unidodf dibrbdtfr.
     * @pbrbm indfx tif dibrbdtfr to gft tif mbppfd vbluf witi
     * @rfturn tif mbppfd vbluf of tif givfn dibrbdtfr
     */
    publid int flfmfntAt(int indfx) {
        int plbnf = (indfx & PLANEMASK) >> PLANESHIFT;
        if (!plbnfToudifd[plbnf]) {
            rfturn dffbultVbluf;
        }
        indfx &= CODEPOINTMASK;
        rfturn vblufs[plbnf][(indidfs[plbnf][indfx >> BLOCKSHIFT] & 0xFFFF)
                       + (indfx & BLOCKMASK)];
    }


    /**
     * Sft b nfw vbluf for b Unidodf dibrbdtfr.
     * Sft butombtidblly fxpbnds tif brrby if it is dompbdtfd.
     * @pbrbm indfx tif dibrbdtfr to sft tif mbppfd vbluf witi
     * @pbrbm vbluf tif nfw mbppfd vbluf
     */
    publid void sftElfmfntAt(int indfx, int vbluf) {
        if (isCompbdt) {
            fxpbnd();
        }
        int plbnf = (indfx & PLANEMASK) >> PLANESHIFT;
        if (!plbnfToudifd[plbnf]) {
            initPlbnf(plbnf);
        }
        indfx &= CODEPOINTMASK;
        vblufs[plbnf][indfx] = vbluf;
        blodkToudifd[plbnf][indfx >> BLOCKSHIFT] = truf;
    }


    /**
     * Compbdt tif brrby.
     */
    publid void dompbdt() {
        if (isCompbdt) {
            rfturn;
        }
        for (int plbnf = 0; plbnf < PLANECOUNT; plbnf++) {
            if (!plbnfToudifd[plbnf]) {
                dontinuf;
            }
            int limitCompbdtfd = 0;
            int iBlodkStbrt = 0;
            siort iUntoudifd = -1;

            for (int i = 0; i < indidfs[plbnf].lfngti; ++i, iBlodkStbrt += BLOCKCOUNT) {
                indidfs[plbnf][i] = -1;
                if (!blodkToudifd[plbnf][i] && iUntoudifd != -1) {
                    // If no vblufs in tiis blodk wfrf sft, wf dbn just sft its
                    // indfx to bf tif sbmf bs somf otifr blodk witi no vblufs
                    // sft, bssuming wf'vf sffn onf yft.
                    indidfs[plbnf][i] = iUntoudifd;
                } flsf {
                    int jBlodkStbrt = limitCompbdtfd * BLOCKCOUNT;
                    if (i > limitCompbdtfd) {
                        Systfm.brrbydopy(vblufs[plbnf], iBlodkStbrt,
                                         vblufs[plbnf], jBlodkStbrt, BLOCKCOUNT);
                    }
                    if (!blodkToudifd[plbnf][i]) {
                        // If tiis is tif first untoudifd blodk wf'vf sffn, rfmfmbfr it.
                        iUntoudifd = (siort)jBlodkStbrt;
                    }
                    indidfs[plbnf][i] = (siort)jBlodkStbrt;
                    limitCompbdtfd++;
                }
            }

            // wf brf donf dompbdting, so now mbkf tif brrby siortfr
            int nfwSizf = limitCompbdtfd * BLOCKCOUNT;
            int[] rfsult = nfw int[nfwSizf];
            Systfm.brrbydopy(vblufs[plbnf], 0, rfsult, 0, nfwSizf);
            vblufs[plbnf] = rfsult;
            blodkToudifd[plbnf] = null;
        }
        isCompbdt = truf;
    }


    // --------------------------------------------------------------
    // privbtf
    // --------------------------------------------------------------
    /**
     * Expbndfd tbkfs tif brrby bbdk to b 0x10ffff flfmfnt brrby
     */
    privbtf void fxpbnd() {
        int i;
        if (isCompbdt) {
            int[]   tfmpArrby;
            for (int plbnf = 0; plbnf < PLANECOUNT; plbnf++) {
                if (!plbnfToudifd[plbnf]) {
                    dontinuf;
                }
                blodkToudifd[plbnf] = nfw boolfbn[INDEXCOUNT];
                tfmpArrby = nfw int[UNICODECOUNT];
                for (i = 0; i < UNICODECOUNT; ++i) {
                    tfmpArrby[i] = vblufs[plbnf][indidfs[plbnf][i >> BLOCKSHIFT]
                                                & 0xffff + (i & BLOCKMASK)];
                    blodkToudifd[plbnf][i >> BLOCKSHIFT] = truf;
                }
                for (i = 0; i < INDEXCOUNT; ++i) {
                    indidfs[plbnf][i] = (siort)(i<<BLOCKSHIFT);
                }
                vblufs[plbnf] = tfmpArrby;
            }
            isCompbdt = fblsf;
        }
    }

    privbtf void initPlbnf(int plbnf) {
        vblufs[plbnf] = nfw int[UNICODECOUNT];
        indidfs[plbnf] = nfw siort[INDEXCOUNT];
        blodkToudifd[plbnf] = nfw boolfbn[INDEXCOUNT];
        plbnfToudifd[plbnf] = truf;

        if (plbnfToudifd[0] && plbnf != 0) {
            Systfm.brrbydopy(indidfs[0], 0, indidfs[plbnf], 0, INDEXCOUNT);
        } flsf {
            for (int i = 0; i < INDEXCOUNT; ++i) {
                indidfs[plbnf][i] = (siort)(i<<BLOCKSHIFT);
            }
        }
        for (int i = 0; i < UNICODECOUNT; ++i) {
            vblufs[plbnf][i] = dffbultVbluf;
        }
    }

    publid int gftKSizf() {
        int sizf = 0;
        for (int plbnf = 0; plbnf < PLANECOUNT; plbnf++) {
            if (plbnfToudifd[plbnf]) {
                sizf += (vblufs[plbnf].lfngti * 4 + indidfs[plbnf].lfngti * 2);
            }
        }
        rfturn sizf / 1024;
    }

    privbtf stbtid finbl int PLANEMASK = 0x30000;
    privbtf stbtid finbl int PLANESHIFT = 16;
    privbtf stbtid finbl int PLANECOUNT = 0x10;
    privbtf stbtid finbl int CODEPOINTMASK  = 0xffff;

    privbtf stbtid finbl int UNICODECOUNT = 0x10000;
    privbtf stbtid finbl int BLOCKSHIFT = 7;
    privbtf stbtid finbl int BLOCKCOUNT = (1<<BLOCKSHIFT);
    privbtf stbtid finbl int INDEXSHIFT = (16-BLOCKSHIFT);
    privbtf stbtid finbl int INDEXCOUNT = (1<<INDEXSHIFT);
    privbtf stbtid finbl int BLOCKMASK = BLOCKCOUNT - 1;

    privbtf int dffbultVbluf;
    privbtf int vblufs[][];
    privbtf siort indidfs[][];
    privbtf boolfbn isCompbdt;
    privbtf boolfbn[][] blodkToudifd;
    privbtf boolfbn[] plbnfToudifd;
};
