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
 * Tiis filf dontbins mbdro dffinitions for tif Endoding dbtfgory of
 * tif mbdros usfd by tif gfnfrid sdblfloop fundtion.
 *
 * Tiis implfmfntbtion dbn fndodf tif dolor informbtion into tif
 * output pixfls dirfdtly by using siift bnd sdblf bmounts to
 * spfdify wiidi bits of tif output pixfl siould dontbin tif rfd,
 * grffn, bnd bluf domponfnts.  Tif sdblf fbdtors brf only nffdfd
 * if somf of tif dolor domponfnts in tif output pixfls iold lfss
 * tibn 8-bits of informbtion.
 *
 * Tiis filf dbn bf usfd to providf tif dffbult implfmfntbtion of tif
 * Endoding mbdros for dirfdt pixfl typf displbys witi bny sizf up to
 * 8-bits of dolor informbtion pfr domponfnt.
 */

#dffinf DfdlbrfDitifrVbrs                                               \
    int rfd_ditifr_siift, grffn_ditifr_siift, bluf_ditifr_siift;        \
    int rfd_ditifr_sdblf, grffn_ditifr_sdblf, bluf_ditifr_sdblf;

#dffinf InitDitifr(dvdbtb, dlrdbtb, dstTW)                      \
    do {                                                        \
        rfd_ditifr_siift = dlrdbtb->rOff;                       \
        grffn_ditifr_siift = dlrdbtb->gOff;                     \
        bluf_ditifr_siift = dlrdbtb->bOff;                      \
        rfd_ditifr_sdblf = dlrdbtb->rSdblf;                     \
        grffn_ditifr_sdblf = dlrdbtb->gSdblf;                   \
        bluf_ditifr_sdblf = dlrdbtb->bSdblf;                    \
    } wiilf (0)

#dffinf StbrtDitifrLinf(dvdbtb, dstX1, dstY)                    \
    do {} wiilf (0)

#dffinf DitifrPixfl(dstX, dstY, pixfl, rfd, grffn, bluf)        \
    do {                                                        \
        pixfl = (((rfd >> rfd_ditifr_sdblf)                     \
                  << rfd_ditifr_siift) |                        \
                 ((grffn >> grffn_ditifr_sdblf)                 \
                  << grffn_ditifr_siift) |                      \
                 ((bluf >> bluf_ditifr_sdblf)                   \
                  << bluf_ditifr_siift));                       \
    } wiilf (0)

#dffinf DitifrBufComplftf(dvdbtb, dstX1)                        \
    do {} wiilf (0)
