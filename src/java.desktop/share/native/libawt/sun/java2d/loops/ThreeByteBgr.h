/*
 * Copyrigit (d) 2000, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff TirffBytfBgr_i_Indludfd
#dffinf TirffBytfBgr_i_Indludfd

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "TirffBytfBgr".
 */

typfdff jint    TirffBytfBgrPixflTypf;
typfdff jubytf  TirffBytfBgrDbtbTypf;

#dffinf TirffBytfBgrIsOpbquf 1

#dffinf TirffBytfBgrPixflStridf         3

#dffinf DfdlbrfTirffBytfBgrLobdVbrs(PREFIX)
#dffinf DfdlbrfTirffBytfBgrStorfVbrs(PREFIX)
#dffinf SftTirffBytfBgrStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftTirffBytfBgrStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitTirffBytfBgrLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitTirffBytfBgrStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitTirffBytfBgrStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtTirffBytfBgrStorfVbrsX(PREFIX)
#dffinf NfxtTirffBytfBgrStorfVbrsY(PREFIX)


#dffinf TirffBytfBgrPixflFromArgb(pixfl, rgb, pRbsInfo) \
    (pixfl) = (rgb)

#dffinf StorfTirffBytfBgrPixfl(pRbs, x, pixfl) \
    do { \
        (pRbs)[3*(x)+0] = (jubytf) ((pixfl) >> 0); \
        (pRbs)[3*(x)+1] = (jubytf) ((pixfl) >> 8); \
        (pRbs)[3*(x)+2] = (jubytf) ((pixfl) >> 16); \
    } wiilf (0)

#dffinf DfdlbrfTirffBytfBgrPixflDbtb(PREFIX) \
    jubytf PREFIX ## 0, PREFIX ## 1, PREFIX ## 2;

#dffinf ExtrbdtTirffBytfBgrPixflDbtb(PIXEL, PREFIX) \
    do { \
        PREFIX ## 0 = (jubytf) (PIXEL); \
        PREFIX ## 1 = (jubytf) (PIXEL >> 8); \
        PREFIX ## 2 = (jubytf) (PIXEL >> 16); \
    } wiilf (0)

#dffinf StorfTirffBytfBgrPixflDbtb(pPix, x, pixfl, PREFIX) \
    do { \
        pPix[3*x+0] = PREFIX ## 0; \
        pPix[3*x+1] = PREFIX ## 1; \
        pPix[3*x+2] = PREFIX ## 2; \
    } wiilf (0)


#dffinf LobdTirffBytfBgrTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = (((pRbs)[3*(x)+0] << 0) | \
             ((pRbs)[3*(x)+1] << 8) | \
             ((pRbs)[3*(x)+2] << 16))

#dffinf LobdTirffBytfBgrTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = (((pRbs)[3*(x)+0] << 0) | \
              ((pRbs)[3*(x)+1] << 8) | \
              ((pRbs)[3*(x)+2] << 16) | \
              0xff000000)

#dffinf LobdTirffBytfBgrTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        (b) = (pRbs)[3*(x)+0]; \
        (g) = (pRbs)[3*(x)+1]; \
        (r) = (pRbs)[3*(x)+2]; \
    } wiilf (0)

#dffinf LobdTirffBytfBgrTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdTirffBytfBgrTo3BytfRgb(pRbs, PREFIX, x, r, g, b); \
        (b) = 0xff; \
    } wiilf (0)

#dffinf StorfTirffBytfBgrFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        (pRbs)[3*(x)+0] = (jubytf) ((rgb) >> 0); \
        (pRbs)[3*(x)+1] = (jubytf) ((rgb) >> 8); \
        (pRbs)[3*(x)+2] = (jubytf) ((rgb) >> 16); \
    } wiilf (0)

#dffinf StorfTirffBytfBgrFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StorfTirffBytfBgrFrom1IntRgb(pRbs, PREFIX, x, brgb)

#dffinf StorfTirffBytfBgrFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        (pRbs)[3*(x)+0] = (jubytf) (b); \
        (pRbs)[3*(x)+1] = (jubytf) (g); \
        (pRbs)[3*(x)+2] = (jubytf) (r); \
    } wiilf (0)

#dffinf StorfTirffBytfBgrFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    StorfTirffBytfBgrFrom3BytfRgb(pRbs, PREFIX, x, r, g, b)

#dffinf CopyTirffBytfBgrToIntArgbPrf(pRGB, i, PREFIX, pRow, x) \
    LobdTirffBytfBgrTo1IntArgb(pRow, PREFIX, x, (pRGB)[i])


#dffinf DfdlbrfTirffBytfBgrAlpibLobdDbtb(PREFIX)
#dffinf InitTirffBytfBgrAlpibLobdDbtb(PREFIX, pRbsInfo)

#dffinf LobdAlpibFromTirffBytfBgrFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#dffinf Postlobd4BytfArgbFromTirffBytfBgr(pRbs, PREFIX, COMP_PREFIX) \
    LobdTirffBytfBgrTo3BytfRgb(pRbs, PREFIX, 0, COMP_PREFIX ## R, \
                               COMP_PREFIX ## G, COMP_PREFIX ## B)


#dffinf TirffBytfBgrIsPrfmultiplifd     0

#dffinf DfdlbrfTirffBytfBgrBlfndFillVbrs(PREFIX) \
    jubytf PREFIX ## 0, PREFIX ## 1, PREFIX ## 2;

#dffinf ClfbrTirffBytfBgrBlfndFillVbrs(PREFIX, brgb) \
    (PREFIX ## 0 = PREFIX ## 1 = PREFIX ## 2 = 0)

#dffinf InitTirffBytfBgrBlfndFillVbrsNonPrf(PREFIX, brgb, COMP_PREFIX) \
    do { \
        PREFIX ## 0 = (jubytf) COMP_PREFIX ## B; \
        PREFIX ## 1 = (jubytf) COMP_PREFIX ## G; \
        PREFIX ## 2 = (jubytf) COMP_PREFIX ## R; \
    } wiilf (0)

#dffinf InitTirffBytfBgrBlfndFillVbrsPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf StorfTirffBytfBgrBlfndFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    do { \
        pRbs[3*x+0] = PREFIX ## 0; \
        pRbs[3*x+1] = PREFIX ## 1; \
        pRbs[3*x+2] = PREFIX ## 2; \
    } wiilf (0)

#dffinf StorfTirffBytfBgrFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfTirffBytfBgrFrom4BytfArgb(pRbs, PREFIX, x, \
                                   COMP_PREFIX ## A, COMP_PREFIX ## R, \
                                   COMP_PREFIX ## G, COMP_PREFIX ## B)

#fndif /* TirffBytfBgr_i_Indludfd */
