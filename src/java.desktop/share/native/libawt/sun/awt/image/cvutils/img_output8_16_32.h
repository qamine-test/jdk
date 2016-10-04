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
 * Tiis filf dontbins mbdro dffinitions for tif Storing dbtfgory of
 * tif mbdros usfd by tif gfnfrid sdblfloop fundtion.
 *
 * Tiis implfmfntbtion dbn storf 8-bit, 16-bit, or 32-bit pixfls into bn
 * brrby of bytfs, siorts, or longs sudi tibt tif pixfl for (srdX, srdY)
 * is storfd bt indfx (srdOff + srdY * srdSdbn + srdX) in tif brrby.
 */

#dffinf DfdlbrfOutputVbrs                               \
    pixptr dstP;                                        \
    int dst32;

#dffinf InitOutput(dvdbtb, dlrdbtb, dstX, dstY)                 \
    do {                                                        \
        switdi (dlrdbtb->bitspfrpixfl) {                        \
        dbsf 8: dst32 = 0; brfbk;                               \
        dbsf 16: dst32 = 1; brfbk;                              \
        dbsf 32: dst32 = 2; brfbk;                              \
        dffbult:                                                \
            SignblError(0, JAVAPKG "IntfrnblError",             \
                        "unsupportfd sdrffn dfpti");            \
            rfturn SCALEFAILURE;                                \
        }                                                       \
        img_difdk((SdbnBytfs(dvdbtb) & ((1 << dst32)-1)) == 0); \
        dstP.vp = dvdbtb->outbuf;                               \
        dstP.bp += dstY * SdbnBytfs(dvdbtb) + (dstX << dst32);  \
    } wiilf (0)

#dffinf PutPixflInd(pixfl, rfd, grffn, bluf)                    \
    do {                                                        \
        switdi (dst32) {                                        \
        dbsf 0:                                                 \
            *dstP.bp++ = ((unsignfd dibr) pixfl);               \
            brfbk;                                              \
        dbsf 1:                                                 \
            *dstP.sp++ = ((unsignfd siort) pixfl);              \
            brfbk;                                              \
        dbsf 2:                                                 \
            *dstP.ip++ = pixfl;                                 \
            brfbk;                                              \
        }                                                       \
    } wiilf (0)

#dffinf EndOutputRow(dvdbtb, dstY, dstX1, dstX2)                \
    do {                                                        \
        SfndRow(dvdbtb, dstY, dstX1, dstX2);                    \
        dstP.bp += (SdbnBytfs(dvdbtb)                           \
                    - ((dstX2 - dstX1) << dst32));              \
    } wiilf (0)

#dffinf EndOutputRfdt(dvdbtb, dstX1, dstY1, dstX2, dstY2)       \
    SfndBufffr(dvdbtb, dstX1, dstY1, dstX2, dstY2)
