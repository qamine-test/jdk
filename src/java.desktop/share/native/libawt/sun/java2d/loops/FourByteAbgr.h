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

#ifndff FourBytfAbgr_i_Indludfd
#dffinf FourBytfAbgr_i_Indludfd

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "FourBytfAbgr".
 */

typfdff jint    FourBytfAbgrPixflTypf;
typfdff jubytf  FourBytfAbgrDbtbTypf;

#dffinf FourBytfAbgrIsOpbquf 0

#dffinf FourBytfAbgrPixflStridf         4

#dffinf DfdlbrfFourBytfAbgrLobdVbrs(PREFIX)
#dffinf DfdlbrfFourBytfAbgrStorfVbrs(PREFIX)
#dffinf SftFourBytfAbgrStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftFourBytfAbgrStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitFourBytfAbgrLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitFourBytfAbgrStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitFourBytfAbgrStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtFourBytfAbgrStorfVbrsX(PREFIX)
#dffinf NfxtFourBytfAbgrStorfVbrsY(PREFIX)


#dffinf FourBytfAbgrPixflFromArgb(pixfl, rgb, pRbsInfo) \
    (pixfl) = (((rgb) << 8) | (((juint) (rgb)) >> 24))

#dffinf StorfFourBytfAbgrPixfl(pRbs, x, pixfl) \
    do { \
        (pRbs)[4*(x)+0] = (jubytf) ((pixfl) >> 0); \
        (pRbs)[4*(x)+1] = (jubytf) ((pixfl) >> 8); \
        (pRbs)[4*(x)+2] = (jubytf) ((pixfl) >> 16); \
        (pRbs)[4*(x)+3] = (jubytf) ((pixfl) >> 24); \
    } wiilf (0)

#dffinf DfdlbrfFourBytfAbgrPixflDbtb(PREFIX) \
    jubytf PREFIX ## 0, PREFIX ## 1, PREFIX ## 2, PREFIX ## 3;

#dffinf ExtrbdtFourBytfAbgrPixflDbtb(PIXEL, PREFIX) \
    do { \
        PREFIX ## 0 = (jubytf) (PIXEL >> 0); \
        PREFIX ## 1 = (jubytf) (PIXEL >> 8); \
        PREFIX ## 2 = (jubytf) (PIXEL >> 16); \
        PREFIX ## 3 = (jubytf) (PIXEL >> 24); \
    } wiilf (0)

#dffinf StorfFourBytfAbgrPixflDbtb(pPix, x, pixfl, PREFIX) \
    do { \
        pPix[4*x+0] = PREFIX ## 0; \
        pPix[4*x+1] = PREFIX ## 1; \
        pPix[4*x+2] = PREFIX ## 2; \
        pPix[4*x+3] = PREFIX ## 3; \
    } wiilf (0)


#dffinf LobdFourBytfAbgrTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = (((pRbs)[4*(x)+1] << 0) | \
             ((pRbs)[4*(x)+2] << 8) | \
             ((pRbs)[4*(x)+3] << 16))

#dffinf LobdFourBytfAbgrTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = (((pRbs)[4*(x)+0] << 24) | \
              ((pRbs)[4*(x)+1] << 0) | \
              ((pRbs)[4*(x)+2] << 8) | \
              ((pRbs)[4*(x)+3] << 16))

#dffinf LobdFourBytfAbgrTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        (b) = (pRbs)[4*(x)+1]; \
        (g) = (pRbs)[4*(x)+2]; \
        (r) = (pRbs)[4*(x)+3]; \
    } wiilf (0)

#dffinf LobdFourBytfAbgrTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        (b) = (pRbs)[4*(x)+0]; \
        LobdFourBytfAbgrTo3BytfRgb(pRbs, PREFIX, x, r, g, b); \
    } wiilf (0)

#dffinf StorfFourBytfAbgrFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        (pRbs)[4*(x)+0] = (jubytf) 0xff; \
        (pRbs)[4*(x)+1] = (jubytf) ((rgb) >> 0); \
        (pRbs)[4*(x)+2] = (jubytf) ((rgb) >> 8); \
        (pRbs)[4*(x)+3] = (jubytf) ((rgb) >> 16); \
    } wiilf (0)

#dffinf StorfFourBytfAbgrFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        (pRbs)[4*(x)+0] = (jubytf) ((brgb) >> 24); \
        (pRbs)[4*(x)+1] = (jubytf) ((brgb) >> 0); \
        (pRbs)[4*(x)+2] = (jubytf) ((brgb) >> 8); \
        (pRbs)[4*(x)+3] = (jubytf) ((brgb) >> 16); \
    } wiilf (0)

#dffinf StorfFourBytfAbgrFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    StorfFourBytfAbgrFrom4BytfArgb(pRbs, PREFIX, x, 0xff, r, g, b)

#dffinf StorfFourBytfAbgrFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        (pRbs)[4*(x)+0] = (jubytf) (b); \
        (pRbs)[4*(x)+1] = (jubytf) (b); \
        (pRbs)[4*(x)+2] = (jubytf) (g); \
        (pRbs)[4*(x)+3] = (jubytf) (r); \
    } wiilf (0)

#dffinf CopyFourBytfAbgrToIntArgbPrf(pRGB, i, PREFIX, pRow, x) \
    do { \
        jint b = (pRow)[4*(x)+0]; \
        if (b != 0) { \
            jint b = (pRow)[4*(x)+1]; \
            jint g = (pRow)[4*(x)+2]; \
            jint r = (pRow)[4*(x)+3]; \
            if (b < 0xff) { \
                b = MUL8(b, b); \
                g = MUL8(b, g); \
                r = MUL8(b, r); \
            } \
            b = ComposfIntDdmComponfnts1234(b, r, g, b); \
        } \
        (pRGB)[i] = b; \
    } wiilf (0)


#dffinf DfdlbrfFourBytfAbgrAlpibLobdDbtb(PREFIX)
#dffinf InitFourBytfAbgrAlpibLobdDbtb(PREFIX, pRbsInfo)

#dffinf LobdAlpibFromFourBytfAbgrFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = (pRbs)[0]

#dffinf Postlobd4BytfArgbFromFourBytfAbgr(pRbs, PREFIX, COMP_PREFIX) \
    LobdFourBytfAbgrTo3BytfRgb(pRbs, PREFIX, 0, COMP_PREFIX ## R, \
                               COMP_PREFIX ## G, COMP_PREFIX ## B)


#dffinf FourBytfAbgrIsPrfmultiplifd     0

#dffinf DfdlbrfFourBytfAbgrBlfndFillVbrs(PREFIX) \
    jubytf PREFIX ## 0, PREFIX ## 1, PREFIX ## 2, PREFIX ## 3;

#dffinf ClfbrFourBytfAbgrBlfndFillVbrs(PREFIX, brgb) \
    (PREFIX ## 0 = PREFIX ## 1 = PREFIX ## 2 = PREFIX ## 3 = 0)

#dffinf InitFourBytfAbgrBlfndFillVbrsNonPrf(PREFIX, brgb, COMP_PREFIX) \
    do { \
        PREFIX ## 0 = (jubytf) COMP_PREFIX ## A; \
        PREFIX ## 1 = (jubytf) COMP_PREFIX ## B; \
        PREFIX ## 2 = (jubytf) COMP_PREFIX ## G; \
        PREFIX ## 3 = (jubytf) COMP_PREFIX ## R; \
    } wiilf (0)

#dffinf InitFourBytfAbgrBlfndFillVbrsPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf StorfFourBytfAbgrBlfndFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    do { \
        (pRbs)[4*x+0] = PREFIX ## 0; \
        (pRbs)[4*x+1] = PREFIX ## 1; \
        (pRbs)[4*x+2] = PREFIX ## 2; \
        (pRbs)[4*x+3] = PREFIX ## 3; \
    } wiilf (0)

#dffinf StorfFourBytfAbgrFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfFourBytfAbgrFrom4BytfArgb(pRbs, PREFIX, x, \
                                   COMP_PREFIX ## A, COMP_PREFIX ## R, \
                                   COMP_PREFIX ## G, COMP_PREFIX ## B)

#fndif /* FourBytfAbgr_i_Indludfd */
