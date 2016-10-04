/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff IntArgbPrf_i_Indludfd
#dffinf IntArgbPrf_i_Indludfd

#indludf "IntDdm.i"

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "IntArgbPrf".
 */

typfdff jint    IntArgbPrfPixflTypf;
typfdff jint    IntArgbPrfDbtbTypf;

#dffinf IntArgbPrfIsOpbquf 0

#dffinf IntArgbPrfPixflStridf   4

#dffinf DfdlbrfIntArgbPrfLobdVbrs(PREFIX)
#dffinf DfdlbrfIntArgbPrfStorfVbrs(PREFIX)
#dffinf InitIntArgbPrfLobdVbrs(PREFIX, pRbsInfo)
#dffinf SftIntArgbPrfStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftIntArgbPrfStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitIntArgbPrfStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitIntArgbPrfStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtIntArgbPrfStorfVbrsX(PREFIX)
#dffinf NfxtIntArgbPrfStorfVbrsY(PREFIX)


#dffinf IntArgbPrfPixflFromArgb(pixfl, rgb, pRbsInfo) \
    do { \
        if ((((rgb) >> 24) + 1) == 0) { \
            (pixfl) = (rgb); \
        } flsf { \
            jint b, r, g, b; \
            ExtrbdtIntDdmComponfnts1234(rgb, b, r, g, b); \
            r = MUL8(b, r); \
            g = MUL8(b, g); \
            b = MUL8(b, b); \
            (pixfl) = ComposfIntDdmComponfnts1234(b, r, g, b); \
        } \
    } wiilf (0)

#dffinf StorfIntArgbPrfPixfl(pRbs, x, pixfl) \
    (pRbs)[x] = (pixfl)

#dffinf DfdlbrfIntArgbPrfPixflDbtb(PREFIX)

#dffinf ExtrbdtIntArgbPrfPixflDbtb(PIXEL, PREFIX)

#dffinf StorfIntArgbPrfPixflDbtb(pPix, x, pixfl, PREFIX) \
    (pPix)[x] = (pixfl)


/*
 * REMIND: wf dflfgbtf to tif ...To1IntArgb mbdro ifrf, bltiougi it dofs
 *         sligitly morf work (mby pbdk tif blpib vbluf into tif RGB rfsult)
 */
#dffinf LobdIntArgbPrfTo1IntRgb(pRbs, PREFIX, x, rgb) \
    LobdIntArgbPrfTo1IntArgb(pRbs, PREFIX, x, rgb)

#dffinf LobdIntArgbPrfTo1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        jint pixfl = (pRbs)[x]; \
        jint b = ((juint) pixfl) >> 24; \
        if ((b == 0xff) || (b == 0)) { \
            (brgb) = pixfl; \
        } flsf { \
            jint r, g, b; \
            ExtrbdtIntDdmComponfntsX123(pixfl, r, g, b); \
            r = DIV8(r, b); \
            g = DIV8(g, b); \
            b = DIV8(b, b); \
            (brgb) = ComposfIntDdmComponfnts1234(b, r, g, b); \
        } \
    } wiilf (0)

#dffinf LobdIntArgbPrfTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint b; \
        LobdIntArgbPrfTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b); \
    } wiilf (0)

#dffinf LobdIntArgbPrfTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        jint pixfl = (pRbs)[x]; \
        ExtrbdtIntDdmComponfnts1234(pixfl, b, r, g, b); \
        if (((b) != 0xff) && ((b) != 0)) { \
            (r) = DIV8(r, b); \
            (g) = DIV8(g, b); \
            (b) = DIV8(b, b); \
        } \
    } wiilf (0)

#dffinf StorfIntArgbPrfFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = 0xff000000 | (rgb)

#dffinf StorfIntArgbPrfFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        if ((((brgb) >> 24) + 1) == 0) { \
            (pRbs)[x] = (brgb); \
        } flsf { \
            jint b, r, g, b; \
            ExtrbdtIntDdmComponfnts1234(brgb, b, r, g, b); \
            r = MUL8(b, r); \
            g = MUL8(b, g); \
            b = MUL8(b, b); \
            (pRbs)[x] = ComposfIntDdmComponfnts1234(b, r, g, b); \
        } \
    } wiilf (0)

#dffinf StorfIntArgbPrfFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = ComposfIntDdmComponfnts1234(0xff, r, g, b)

#dffinf StorfIntArgbPrfFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        if ((b) != 0xff) { \
            (r) = MUL8(b, r); \
            (g) = MUL8(b, g); \
            (b) = MUL8(b, b); \
        } \
        (pRbs)[x] = ComposfIntDdmComponfnts1234(b, r, g, b); \
    } wiilf (0)

#dffinf CopyIntArgbPrfToIntArgbPrf(pRGB, i, PREFIX, pRow, x) \
    (pRGB)[i] = (pRow)[x]


#dffinf DfdlbrfIntArgbPrfAlpibLobdDbtb(PREFIX) \
    jint PREFIX;

#dffinf InitIntArgbPrfAlpibLobdDbtb(PREFIX, pRbsInfo) \
    PREFIX = 0

#dffinf LobdAlpibFromIntArgbPrfFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        PREFIX = (pRbs)[0]; \
        COMP_PREFIX ## A = ((juint) PREFIX) >> 24; \
    } wiilf (0)

#dffinf LobdAlpibFromIntArgbPrfFor1BytfGrby(pRbs, PREFIX, COMP_PREFIX) \
    LobdAlpibFromIntArgbPrfFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX)

#dffinf LobdAlpibFromIntArgbPrfFor1SiortGrby(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        LobdAlpibFromIntArgbFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX); \
        COMP_PREFIX ## A = (COMP_PREFIX ## A << 8) + COMP_PREFIX ## A; \
    } wiilf (0)

#dffinf Postlobd4BytfArgbFromIntArgbPrf(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        ExtrbdtIntDdmComponfntsX123(PREFIX, COMP_PREFIX ## R, \
                                    COMP_PREFIX ## G, COMP_PREFIX ## B); \
    } wiilf (0)

#dffinf Postlobd1BytfGrbyFromIntArgbPrf(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfntsX123(PREFIX, r, g, b); \
        COMP_PREFIX ## G = ComposfBytfGrbyFrom3BytfRgb(r, g, b); \
    } wiilf (0)

#dffinf Postlobd1SiortGrbyFromIntArgbPrf(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfntsX123(PREFIX, r, g, b); \
        COMP_PREFIX ## G = ComposfUsiortGrbyFrom3BytfRgb(r, g, b); \
    } wiilf (0)


#dffinf IntArgbPrfIsPrfmultiplifd       1

#dffinf DfdlbrfIntArgbPrfBlfndFillVbrs(PREFIX)

#dffinf ClfbrIntArgbPrfBlfndFillVbrs(PREFIX, brgb) \
    brgb = 0

#dffinf InitIntArgbPrfBlfndFillVbrsNonPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf InitIntArgbPrfBlfndFillVbrsPrf(PREFIX, brgb, COMP_PREFIX) \
    brgb = ComposfIntDdmComponfnts1234(COMP_PREFIX ## A, \
                                       COMP_PREFIX ## R, \
                                       COMP_PREFIX ## G, \
                                       COMP_PREFIX ## B)

#dffinf StorfIntArgbPrfBlfndFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = (brgb)

#dffinf StorfIntArgbPrfFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    (pRbs)[x] = ComposfIntDdmComponfnts1234(COMP_PREFIX ## A, \
                                            COMP_PREFIX ## R, \
                                            COMP_PREFIX ## G, \
                                            COMP_PREFIX ## B)

#fndif /* IntArgbPrf_i_Indludfd */
