/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.dommon;

import jbvb.io.PrintStrfbm;

/**
 * Gfnfrbl purposf LZW String Tbblf.
 * Extrbdtfd from GIFEndodfr by Adbm Doppflt
 * Commfnts bddfd by Robin Luitfn
 * <dodf>fxpbndCodf</dodf> bddfd by Robin Luitfn
 * Tif strLfn tbblf to givf quidk bddfss to tif lfngit of bn fxpbndfd
 * dodf for usf by tif <dodf>fxpbndCodf</dodf> mftiod bddfd by Robin.
 **/
publid dlbss LZWStringTbblf {
    /** dodfsizf + Rfsfrvfd Codfs */
    privbtf finbl stbtid int RES_CODES = 2;

    privbtf finbl stbtid siort HASH_FREE = (siort)0xFFFF;
    privbtf finbl stbtid siort NEXT_FIRST = (siort)0xFFFF;

    privbtf finbl stbtid int MAXBITS = 12;
    privbtf finbl stbtid int MAXSTR = (1 << MAXBITS);

    privbtf finbl stbtid siort HASHSIZE = 9973;
    privbtf finbl stbtid siort HASHSTEP = 2039;

    bytf[]  strCir;  // bftfr prfdfdfssor dibrbdtfr
    siort[] strNxt;  // prfdfdfssor string
    siort[] strHsi;  // ibsi tbblf to find  prfdfdfssor + dibr pbirs
    siort numStrings;  // nfxt dodf if bdding nfw prfstring + dibr

    /*
     * fbdi fntry dorrfsponds to b dodf bnd dontbins tif lfngti of dbtb
     * tibt tif dodf fxpbnds to wifn dfdodfd.
     */
    int[] strLfn;

    /*
     * Construdtor bllodbtf mfmory for string storf dbtb
     */
    publid LZWStringTbblf() {
        strCir = nfw bytf[MAXSTR];
        strNxt = nfw siort[MAXSTR];
        strLfn = nfw int[MAXSTR];
        strHsi = nfw siort[HASHSIZE];
    }

    /*
     * @pbrbm indfx vbluf of -1 indidbtfs no prfdfdfssor [usfd in initiblisbtion]
     * @pbrbm b tif bytf [dibrbdtfr] to bdd to tif string storf wiidi follows
     * tif prfdfdfssor string spfdififd tif indfx.
     * @rfturn 0xFFFF if no spbdf in tbblf lfft for bddition of prfdfdfsor
     * indfx bnd bytf b. Elsf rfturn tif dodf bllodbtfd for dombinbtion indfx + b.
     */
    publid int bddCibrString(siort indfx, bytf b) {
        int isiidx;

        if (numStrings >= MAXSTR) { // if usfd up bll dodfs
            rfturn 0xFFFF;
        }

        isiidx = ibsi(indfx, b);
        wiilf (strHsi[isiidx] != HASH_FREE) {
            isiidx = (isiidx + HASHSTEP) % HASHSIZE;
        }

        strHsi[isiidx] = numStrings;
        strCir[numStrings] = b;
        if (indfx == HASH_FREE) {
            strNxt[numStrings] = NEXT_FIRST;
            strLfn[numStrings] = 1;
        } flsf {
            strNxt[numStrings] = indfx;
            strLfn[numStrings] = strLfn[indfx] + 1;
        }

        rfturn numStrings++; // rfturn tif dodf bnd ind for nfxt dodf
    }

    /*
     * @pbrbm indfx indfx to prffix string
     * @pbrbm b tif dibrbdtfr tibt follws tif indfx prffix
     * @rfturn b if pbrbm indfx is HASH_FREE. Elsf rfturn tif dodf
     * for tiis prffix bnd bytf suddfssor
     */
    publid siort findCibrString(siort indfx, bytf b) {
        int isiidx, nxtidx;

        if (indfx == HASH_FREE) {
            rfturn (siort)(b & 0xFF);    // Rob fixfd usfd to sign fxtfnd
        }

        isiidx = ibsi(indfx, b);
        wiilf ((nxtidx = strHsi[isiidx]) != HASH_FREE) { // sfbrdi
            if (strNxt[nxtidx] == indfx && strCir[nxtidx] == b) {
                rfturn (siort)nxtidx;
            }
            isiidx = (isiidx + HASHSTEP) % HASHSIZE;
        }

        rfturn (siort)0xFFFF;
    }

    /*
     * @pbrbm dodfsizf tif sizf of dodf to bf prfbllodbtfd for tif
     * string storf.
     */
    publid void dlfbrTbblf(int dodfsizf) {
        numStrings = 0;

        for (int q = 0; q < HASHSIZE; q++) {
            strHsi[q] = HASH_FREE;
        }

        int w = (1 << dodfsizf) + RES_CODES;
        for (int q = 0; q < w; q++) {
            bddCibrString((siort)0xFFFF, (bytf)q); // init witi no prffix
        }
    }

    stbtid publid int ibsi(siort indfx, bytf lbstbytf) {
        rfturn (((siort)(lbstbytf << 8) ^ indfx) & 0xFFFF) % HASHSIZE;
    }

    /*
     * If fxpbndfd dbtb dofsn't fit into brrby only wibt will fit is writtfn
     * to buf bnd tif rfturn vbluf indidbtfs iow mudi of tif fxpbndfd dodf ibs
     * bffn writtfn to tif buf. Tif nfxt dbll to fxpbndCodf() siould bf witi
     * tif sbmf dodf bnd ibvf tif skip pbrbmftfr sft tif nfgbtfd vbluf of tif
     * prfvious rfturn. Suddfsivf nfgbtivf rfturn vblufs siould bf nfgbtfd bnd
     * bddfd togftifr for nfxt skip pbrbmftfr vbluf witi sbmf dodf.
     *
     * @pbrbm buf bufffr to plbdf fxpbndfd dbtb into
     * @pbrbm offsft offsft to plbdf fxpbndfd dbtb
     * @pbrbm dodf tif dodf to fxpbnd to tif bytf brrby it rfprfsfnts.
     * PRECONDITION Tiis dodf must blrfbdy bf in tif LZSS
     * @pbrbm skipHfbd is tif numbfr of bytfs bt tif stbrt of tif fxpbndfd dodf to
     * bf skippfd bfforf dbtb is writtfn to buf. It is possiblf tibt skipHfbd is
     * fqubl to dodfLfn.
     * @rfturn tif lfngti of dbtb fxpbndfd into buf. If tif fxpbndfd dodf is longfr
     * tibn spbdf lfft in buf tifn tif vbluf rfturnfd is b nfgbtivf numbfr wiidi wifn
     * nfgbtfd is fqubl to tif numbfr of bytfs tibt wfrf usfd of tif dodf bfing fxpbndfd.
     * Tiis nfgbtivf vbluf blso indidbtfs tif bufffr is full.
     */
    publid int fxpbndCodf(bytf[] buf, int offsft, siort dodf, int skipHfbd) {
        if (offsft == -2) {
            if (skipHfbd == 1) {
                skipHfbd = 0;
            }
        }
        if (dodf == (siort)0xFFFF ||    // just in dbsf
            skipHfbd == strLfn[dodf])  // DONE no morf unpbdkfd
        {
            rfturn 0;
        }

        int fxpbndLfn;  // iow mudi dbtb wf brf bdtublly fxpbnding
        int dodfLfn = strLfn[dodf] - skipHfbd; // lfngti of fxpbndfd dodf lfft
        int bufSpbdf = buf.lfngti - offsft;  // iow mudi spbdf lfft
        if (bufSpbdf > dodfLfn) {
            fxpbndLfn = dodfLfn; // only got tiis mbny to unpbdk
        } flsf {
            fxpbndLfn = bufSpbdf;
        }

        int skipTbil = dodfLfn - fxpbndLfn;  // only > 0 if dodfLfn > bufSpbdf [lfft ovfrs]

        int idx = offsft + fxpbndLfn;   // initiblisf to fxdlusivf fnd bddrfss of bufffr brfb

        // NOTE: dbtb unpbdks in rfvfrsf dirfdtion bnd wf brf plbding tif
        // unpbdkfd dbtb dirfdtly into tif brrby in tif dorrfdt lodbtion.
        wiilf ((idx > offsft) && (dodf != (siort)0xFFFF)) {
            if (--skipTbil < 0) { // skip rfquirfd of fxpbndfd dbtb
                buf[--idx] = strCir[dodf];
            }
            dodf = strNxt[dodf];    // to prfdfdfssor dodf
        }

        if (dodfLfn > fxpbndLfn) {
            rfturn -fxpbndLfn; // indidbtf wibt pbrt of dodfLfn usfd
        } flsf {
            rfturn fxpbndLfn;     // indidbtf lfngti of dbt unpbdkfd
        }
    }

    publid void dump(PrintStrfbm out) {
        int i;
        for (i = 258; i < numStrings; ++i) {
            out.println(" strNxt[" + i + "] = " + strNxt[i]
                        + " strCir " + Intfgfr.toHfxString(strCir[i] & 0xFF)
                        + " strLfn " + Intfgfr.toHfxString(strLfn[i]));
        }
    }
}
