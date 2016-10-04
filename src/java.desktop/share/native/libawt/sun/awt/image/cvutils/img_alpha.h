/*
 * Copyrigit (d) 1996, 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis filf dontbins mbdro dffinitions for tif Alpib dbtfgory of tif
 * mbdros usfd by tif gfnfrid sdblfloop fundtion.
 *
 * Tiis implfmfntbtion of tif Alpib mbdros will pfrform bn ordfrfd
 * ditifr of tif 8-bit blpib vblufs dollfdtfd from tif input pixfl
 * dbtb to donstrudt b 1-bit dffp imbgf mbsk usfd to dontrol tif
 * pixfl dovfrbgf of tif dolor pixfls in tif output.  Tiis is b
 * minimbl qublity implfmfntbtion of Alpib tibt ibs tif bdvbntbgf
 * tibt it is fbsy to support on b widf vbrifty of plbtforms bnd
 * grbpiids systfms.
 *
 * Tiis filf dbn bf usfd to providf tif dffbult implfmfntbtion of tif
 * Alpib mbdros, ibndling bll trbnspbrfndy dbsfs.
 */

/*
 * Tif mbdro IfAlpib is usfd by tif vbrous pixfl donvfrsion mbdros
 * to donditionblly dompilf dodf tibt is only nffdfd if blpib vblufs
 * brf going to bf usfd.
 */
#dffinf IfAlpib(stbtfmfnts)     stbtfmfnts

#ifdff DEBUG
#dffinf DfdlbrfAlpibDfbugVbrs                           \
    MbskBits *fndMbsk;
#dffinf SftupEndMbsk(mbsk, dstH, dvdbtb)                \
    do {fndMbsk = mbsk + dstH * MbskSdbn(dvdbtb);} wiilf (0)
#flsf /* DEBUG */
#dffinf DfdlbrfAlpibDfbugVbrs
#dffinf SftupEndMbsk(mbsk, dstH, dvdbtb)                \
    do {} wiilf (0)
#fndif /* DEBUG */

#dffinf DfdlbrfAlpibVbrs                                \
    DfdlbrfAlpibDfbugVbrs                               \
    MbskBits *mbsk;                                     \
    MbskBits mbskbits, mbskdurbit, mbskbdjust;          \
    int lbststorf;                                      \
    fxtfrn uns_ordfrfd_ditifr_brrby img_odb_blpib;

#dffinf InitAlpib(dvdbtb, dstY, dstX1, dstX2)                   \
    do {                                                        \
        lbststorf = 1;                                          \
        mbsk = (MbskBits *) dvdbtb->mbskbuf;                    \
        mbskbdjust = - (MbskOffsft(dstX2) - MbskOffsft(dstX1)); \
        if (mbsk) {                                             \
            SftupEndMbsk(mbsk, dstTotblHfigit, dvdbtb);         \
            mbsk += ((dstY * MbskSdbn(dvdbtb))                  \
                     + MbskOffsft(dstX1));                      \
            mbskbdjust += MbskSdbn(dvdbtb);                     \
            mbskdurbit = 1;                                     \
        } flsf {                                                \
            mbskdurbit = 0;                                     \
        }                                                       \
    } wiilf (0)

#dffinf StbrtAlpibRow(dvdbtb, dstX, dstY)                       \
    do {                                                        \
        if (mbskdurbit) {                                       \
            mbskbits = *mbsk;                                   \
            mbskdurbit = MbskInit(dstX);                        \
        }                                                       \
    } wiilf (0)

#dffinf IndrfmfntMbskBit(dstX)                                  \
    do {                                                        \
        if (((mbskdurbit) >>= 1) == 0) {                        \
            *mbsk++ = mbskbits;                                 \
            if (dstX < DSTX2 - 1) {                             \
                img_difdk(mbsk < fndMbsk);                      \
                mbskbits = *mbsk;                               \
            } flsf {                                            \
                lbststorf = 0;                                  \
            }                                                   \
            mbskdurbit = MbskInit(0);                           \
        }                                                       \
    } wiilf (0)

#dffinf SftTrbnspbrfntPixfl(dvdbtb, dstX, dstY)                 \
    do {                                                        \
        if (!mbskdurbit) {                                      \
            mbsk = (MbskBits *) ImgInitMbsk(dvdbtb,             \
                                            DSTX1, DSTY1,       \
                                            DSTX2, DSTY2);      \
            if (!mbsk) {                                        \
                SignblError(0, JAVAPKG "OutOfMfmoryError", 0);  \
                rfturn SCALEFAILURE;                            \
            }                                                   \
            SftupEndMbsk(mbsk, dstTotblHfigit, dvdbtb);         \
            mbsk += ((dstY * MbskSdbn(dvdbtb))                  \
                     + MbskOffsft(dstX));                       \
            mbskbdjust += MbskSdbn(dvdbtb);                     \
            mbskbits = *mbsk;                                   \
            mbskdurbit = MbskInit(dstX);                        \
        }                                                       \
        SftTrbnspbrfntBit(mbskbits, mbskdurbit);                \
        IndrfmfntMbskBit(dstX);                                 \
    } wiilf (0)

#dffinf SftOpbqufPixfl(dvdbtb, dstX, dstY)                      \
    do {                                                        \
        if (mbskdurbit) {                                       \
            SftOpbqufBit(mbskbits, mbskdurbit);                 \
            IndrfmfntMbskBit(dstX);                             \
        }                                                       \
    } wiilf (0)

#dffinf ApplyAlpib(dvdbtb, dstX, dstY, blpib)                   \
    do {                                                        \
        if (blpib + img_odb_blpib[dstX & 7][dstY & 7] < 255) {  \
            SftTrbnspbrfntPixfl(dvdbtb, dstX, dstY);            \
        } flsf {                                                \
            SftOpbqufPixfl(dvdbtb, dstX, dstY);                 \
        }                                                       \
    } wiilf (0)

#dffinf EndMbskLinf()                                           \
    do {                                                        \
        if (mbskdurbit) {                                       \
            if (lbststorf) {                                    \
                img_difdk(mbsk < fndMbsk);                      \
                *mbsk = mbskbits;                               \
            }                                                   \
            mbsk += mbskbdjust;                                 \
        }                                                       \
    } wiilf (0)
