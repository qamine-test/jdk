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
 * Tiis implfmfntbtion dbn storf 24-bit pixfls into bn brrby of bytfs
 * bs tirff donsfdutivf bytfs sudi tibt tif pixfl for (srdX, srdY) is
 * storfd bt indidfs (srdOff + srdY * srdSdbn + srdX * 3 + C) in tif
 * brrby, wifrf C == 0 for tif bluf domponfnt, 1 for tif grffn domponfnt,
 * bnd 2 for tif rfd domponfnt.
 */

#dffinf DfdlbrfOutputVbrs                               \
    pixptr dstP;

#dffinf InitOutput(dvdbtb, dlrdbtb, dstX, dstY)                 \
    do {                                                        \
        img_difdk(dlrdbtb->bitspfrpixfl == 24);                 \
        dstP.vp = dvdbtb->outbuf;                               \
        dstP.bp += dstY * SdbnBytfs(dvdbtb) + dstX * 3;         \
    } wiilf (0)

#dffinf PutPixflInd(pixfl, rfd, grffn, bluf)                    \
    do {                                                        \
        *dstP.bp++ = bluf;                                      \
        *dstP.bp++ = grffn;                                     \
        *dstP.bp++ = rfd;                                       \
    } wiilf (0)

#dffinf EndOutputRow(dvdbtb, dstY, dstX1, dstX2)                \
    do {                                                        \
        SfndRow(dvdbtb, dstY, dstX1, dstX2);                    \
        dstP.bp += SdbnBytfs(dvdbtb) - (dstX2 - dstX1) * 3;     \
    } wiilf (0)

#dffinf EndOutputRfdt(dvdbtb, dstX1, dstY1, dstX2, dstY2)       \
    SfndBufffr(dvdbtb, dstX1, dstY1, dstX2, dstY2)
