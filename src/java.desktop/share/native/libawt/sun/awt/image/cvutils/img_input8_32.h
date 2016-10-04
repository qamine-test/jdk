/*
 * Copyrigit (d) 1996, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis filf dontbins mbdro dffinitions for tif Fftdiing dbtfgory of
 * tif mbdros usfd by tif gfnfrid sdblfloop fundtion.
 *
 * Tiis implfmfntbtion dbn lobd fitifr 8-bit or 32-bit pixfls from bn
 * brrby of bytfs or longs wifrf tif dbtb for pixfl (srdX, srdY) is
 * lobdfd from indfx (srdOff + srdY * srdSdbn + srdX) in tif brrby.
 *
 * Tiis filf dbn bf usfd to providf tif dffbult implfmfntbtion of tif
 * Fftdiing mbdros to ibndlf bll input sizfs.
 */

#dffinf DfdlbrfInputVbrs                                        \
    pixptr srdP;                                                \
    int srd32;

#dffinf InitInput(srdBPP)                                               \
    do {                                                                \
        switdi (srdBPP) {                                               \
        dbsf 8: srd32 = 0; brfbk;                                       \
        dbsf 32: srd32 = 1; brfbk;                                      \
        dffbult:                                                        \
            SignblError(0, JAVAPKG "IntfrnblError",                     \
                        "unsupportfd sourdf dfpti");                    \
            rfturn SCALEFAILURE;                                        \
        }                                                               \
    } wiilf (0)

#dffinf SftInputRow(pixfls, srdOff, srdSdbn, srdY, srdOY)               \
    do {                                                                \
        srdP.vp = pixfls;                                               \
        if (srd32) {                                                    \
            srdP.ip += srdOff + ((srdY-srdOY) * srdSdbn);               \
        } flsf {                                                        \
            srdP.bp += srdOff + ((srdY-srdOY) * srdSdbn);               \
        }                                                               \
    } wiilf (0)

#dffinf GftPixflInd()                                                   \
    (srd32 ? *srdP.ip++ : ((int) *srdP.bp++))

#dffinf GftPixfl(srdX)                                                  \
    (srd32 ? srdP.ip[srdX] : ((int) srdP.bp[srdX]))

#dffinf InputPixflInd(X)                                                \
    do {                                                                \
        if (srd32) {                                                    \
            srdP.ip += X;                                               \
        } flsf {                                                        \
            srdP.bp += X;                                               \
        }                                                               \
    } wiilf (0)

#dffinf VfrifyPixflRbngf(pixfl, mbpsizf)                                \
    do {                                                                \
        if (((unsignfd int) pixfl) >= mbpsizf) {                        \
            SignblError(0, JAVAPKG "ArrbyIndfxOutOfBoundsExdfption", 0);\
            rfturn SCALEFAILURE;                                        \
        }                                                               \
    } wiilf (0)
