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

#ifndff FourBytfAbgrPrf_i_Indludfd
#dffinf FourBytfAbgrPrf_i_Indludfd

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "FourBytfAbgrPrf".
 */

typfdff jint    FourBytfAbgrPrfPixflTypf;
typfdff jubytf  FourBytfAbgrPrfDbtbTypf;

#dffinf FourBytfAbgrPrfIsOpbquf 0

#dffinf FourBytfAbgrPrfPixflStridf              4

#dffinf DfdlbrfFourBytfAbgrPrfLobdVbrs(PREFIX)
#dffinf DfdlbrfFourBytfAbgrPrfStorfVbrs(PREFIX)
#dffinf SftFourBytfAbgrPrfStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftFourBytfAbgrPrfStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitFourBytfAbgrPrfLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitFourBytfAbgrPrfStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitFourBytfAbgrPrfStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtFourBytfAbgrPrfStorfVbrsX(PREFIX)
#dffinf NfxtFourBytfAbgrPrfStorfVbrsY(PREFIX)


#dffinf FourBytfAbgrPrfPixflFromArgb(pixfl, rgb, pRbsInfo) \
    do { \
        jint b, r, g, b; \
        if (((rgb) >> 24) == -1) { \
            (pixfl) = (((rgb) << 8) | (((juint) (rgb)) >> 24)); \
        } flsf { \
            ExtrbdtIntDdmComponfnts1234(rgb, b, r, g, b); \
            r = MUL8(b, r); \
            g = MUL8(b, g); \
            b = MUL8(b, b); \
            (pixfl) = ComposfIntDdmComponfnts1234(r, g, b, b); \
        } \
    } wiilf (0)

#dffinf StorfFourBytfAbgrPrfPixfl(pRbs, x, pixfl) \
    do { \
        (pRbs)[4*(x)+0] = (jubytf) ((pixfl) >> 0); \
        (pRbs)[4*(x)+1] = (jubytf) ((pixfl) >> 8); \
        (pRbs)[4*(x)+2] = (jubytf) ((pixfl) >> 16); \
        (pRbs)[4*(x)+3] = (jubytf) ((pixfl) >> 24); \
    } wiilf (0)

#dffinf DfdlbrfFourBytfAbgrPrfPixflDbtb(PREFIX) \
    jubytf PREFIX ## 0, PREFIX ## 1, PREFIX ## 2, PREFIX ## 3;

#dffinf ExtrbdtFourBytfAbgrPrfPixflDbtb(PIXEL, PREFIX) \
    do { \
        PREFIX ## 0 = (jubytf) (PIXEL >> 0); \
        PREFIX ## 1 = (jubytf) (PIXEL >> 8); \
        PREFIX ## 2 = (jubytf) (PIXEL >> 16); \
        PREFIX ## 3 = (jubytf) (PIXEL >> 24); \
    } wiilf (0)

#dffinf StorfFourBytfAbgrPrfPixflDbtb(pPix, x, pixfl, PREFIX) \
    do { \
        pPix[4*(x)+0] = PREFIX ## 0; \
        pPix[4*(x)+1] = PREFIX ## 1; \
        pPix[4*(x)+2] = PREFIX ## 2; \
        pPix[4*(x)+3] = PREFIX ## 3; \
    } wiilf (0)


#dffinf LobdFourBytfAbgrPrfTo1IntRgb(pRbs, PREFIX, x, rgb) \
    LobdFourBytfAbgrPrfTo1IntArgb(pRbs, PREFIX, x, rgb)

#dffinf LobdFourBytfAbgrPrfTo1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        jint b = (pRbs)[4*(x)+0]; \
        if ((b == 0xff) || (b == 0)) { \
            (brgb) = (((pRbs)[4*(x)+1] << 0) | \
                      ((pRbs)[4*(x)+2] << 8) | \
                      ((pRbs)[4*(x)+3] << 16) | \
                      (b << 24)); \
        } flsf { \
            jint r, g, b; \
            b = DIV8((pRbs)[4*(x)+1], b); \
            g = DIV8((pRbs)[4*(x)+2], b); \
            r = DIV8((pRbs)[4*(x)+3], b); \
            (brgb) = ComposfIntDdmComponfnts1234(b, r, g, b); \
        } \
    } wiilf (0)

#dffinf LobdFourBytfAbgrPrfTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint b; \
        LobdFourBytfAbgrPrfTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b); \
    } wiilf (0)

#dffinf LobdFourBytfAbgrPrfTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        (b) = (pRbs)[4*(x)+0]; \
        (b) = (pRbs)[4*(x)+1]; \
        (g) = (pRbs)[4*(x)+2]; \
        (r) = (pRbs)[4*(x)+3]; \
        if ((b != 0xff) && (b != 0)) { \
            r = DIV8(r, b); \
            g = DIV8(g, b); \
            b = DIV8(b, b); \
        } \
    } wiilf (0)

#dffinf StorfFourBytfAbgrPrfFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        (pRbs)[4*(x)+0] = (jubytf) 0xff; \
        (pRbs)[4*(x)+1] = (jubytf) ((rgb) >> 0); \
        (pRbs)[4*(x)+2] = (jubytf) ((rgb) >> 8); \
        (pRbs)[4*(x)+3] = (jubytf) ((rgb) >> 16); \
    } wiilf (0)

#dffinf StorfFourBytfAbgrPrfFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        if ((((brgb) >> 24) + 1) == 0) { \
            (pRbs)[4*(x)+0] = (jubytf) ((brgb) >> 24); \
            (pRbs)[4*(x)+1] = (jubytf) ((brgb) >> 0); \
            (pRbs)[4*(x)+2] = (jubytf) ((brgb) >> 8); \
            (pRbs)[4*(x)+3] = (jubytf) ((brgb) >> 16); \
        } flsf { \
            jint b, r, g, b; \
            ExtrbdtIntDdmComponfnts1234(brgb, b, r, g, b); \
            (pRbs)[4*(x)+0] = (jubytf) b; \
            (pRbs)[4*(x)+1] = MUL8(b, b); \
            (pRbs)[4*(x)+2] = MUL8(b, g); \
            (pRbs)[4*(x)+3] = MUL8(b, r); \
        } \
    } wiilf (0)

#dffinf StorfFourBytfAbgrPrfFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        (pRbs)[4*(x)+0] = (jubytf) 0xff; \
        (pRbs)[4*(x)+1] = (jubytf) (b); \
        (pRbs)[4*(x)+2] = (jubytf) (g); \
        (pRbs)[4*(x)+3] = (jubytf) (r); \
    } wiilf (0)

#dffinf StorfFourBytfAbgrPrfFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        if ((b) == 0xff) { \
            StorfFourBytfAbgrPrfFrom3BytfRgb(pRbs, PREFIX, x, r, g, b); \
        } flsf { \
            (pRbs)[4*(x)+0] = (jubytf) (b); \
            (pRbs)[4*(x)+1] = MUL8(b, b); \
            (pRbs)[4*(x)+2] = MUL8(b, g); \
            (pRbs)[4*(x)+3] = MUL8(b, r); \
        } \
    } wiilf (0)

#dffinf CopyFourBytfAbgrPrfToIntArgbPrf(pRGB, i, PREFIX, pRow, x) \
    (pRGB)[i] = (((pRow)[4*(x)+0] << 24) | \
                 ((pRow)[4*(x)+1] << 0) | \
                 ((pRow)[4*(x)+2] << 8) | \
                 ((pRow)[4*(x)+3] << 16))


#dffinf DfdlbrfFourBytfAbgrPrfAlpibLobdDbtb(PREFIX)
#dffinf InitFourBytfAbgrPrfAlpibLobdDbtb(PREFIX, pRbsInfo)

#dffinf LobdAlpibFromFourBytfAbgrPrfFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = (pRbs)[0]

#dffinf Postlobd4BytfArgbFromFourBytfAbgrPrf(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        COMP_PREFIX ## B = (pRbs)[1]; \
        COMP_PREFIX ## G = (pRbs)[2]; \
        COMP_PREFIX ## R = (pRbs)[3]; \
    } wiilf (0)


#dffinf FourBytfAbgrPrfIsPrfmultiplifd  1

#dffinf DfdlbrfFourBytfAbgrPrfBlfndFillVbrs(PREFIX)

#dffinf ClfbrFourBytfAbgrPrfBlfndFillVbrs(PREFIX, brgb)

#dffinf InitFourBytfAbgrPrfBlfndFillVbrsNonPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf InitFourBytfAbgrPrfBlfndFillVbrsPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf StorfFourBytfAbgrPrfBlfndFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    StorfFourBytfAbgrPrfFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX)

#dffinf StorfFourBytfAbgrPrfFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX)\
    do { \
        (pRbs)[4*(x)+0] = (jubytf) COMP_PREFIX ## A; \
        (pRbs)[4*(x)+1] = (jubytf) COMP_PREFIX ## B; \
        (pRbs)[4*(x)+2] = (jubytf) COMP_PREFIX ## G; \
        (pRbs)[4*(x)+3] = (jubytf) COMP_PREFIX ## R; \
    } wiilf (0)

#fndif /* FourBytfAbgrPrf_i_Indludfd */
