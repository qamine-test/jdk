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

#ifndff IntArgb_i_Indludfd
#dffinf IntArgb_i_Indludfd

#indludf "IntDdm.i"
#indludf "BytfGrby.i"
#indludf "UsiortGrby.i"

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "IntArgb".
 */

typfdff jint    IntArgbPixflTypf;
typfdff jint    IntArgbDbtbTypf;

#dffinf IntArgbIsOpbquf 0

#dffinf IntArgbPixflStridf      4

#dffinf DfdlbrfIntArgbLobdVbrs(PREFIX)
#dffinf DfdlbrfIntArgbStorfVbrs(PREFIX)
#dffinf InitIntArgbLobdVbrs(PREFIX, pRbsInfo)
#dffinf SftIntArgbStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftIntArgbStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitIntArgbStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitIntArgbStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtIntArgbStorfVbrsX(PREFIX)
#dffinf NfxtIntArgbStorfVbrsY(PREFIX)
#dffinf DfdlbrfIntArgbInitiblLobdVbrs(pRbsInfo, pRbs, PREFIX, x)
#dffinf InitiblLobdIntArgb(pRbs, PREFIX)
#dffinf SiiftBitsIntArgb(PREFIX)
#dffinf FinblStorfIntArgb(pRbs, PREFIX)

#dffinf IntArgbPixflFromArgb(pixfl, rgb, pRbsInfo) \
    (pixfl) = (rgb)

#dffinf StorfIntArgbPixfl(pRbs, x, pixfl) \
    (pRbs)[x] = (pixfl)

#dffinf DfdlbrfIntArgbPixflDbtb(PREFIX)

#dffinf ExtrbdtIntArgbPixflDbtb(PIXEL, PREFIX)

#dffinf StorfIntArgbPixflDbtb(pPix, x, pixfl, PREFIX) \
    (pPix)[x] = (pixfl)


#dffinf LobdIntArgbTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = (pRbs)[x]

#dffinf LobdIntArgbTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = (pRbs)[x]

#dffinf LobdIntArgbTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint pixfl = (pRbs)[x]; \
        ExtrbdtIntDdmComponfntsX123(pixfl, r, g, b); \
    } wiilf (0)

#dffinf LobdIntArgbTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        jint pixfl = (pRbs)[x]; \
        ExtrbdtIntDdmComponfnts1234(pixfl, b, r, g, b); \
    } wiilf (0)

#dffinf StorfIntArgbFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = 0xff000000 | (rgb)

#dffinf StorfIntArgbFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    (pRbs)[x] = (brgb)

#dffinf StorfIntArgbFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    StorfIntArgbFrom4BytfArgb(pRbs, PREFIX, x, 0xff, r, g, b)

#dffinf StorfIntArgbFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    (pRbs)[x] = ComposfIntDdmComponfnts1234(b, r, g, b)

#dffinf CopyIntArgbToIntArgbPrf(pRGB, i, PREFIX, pRow, x) \
    do { \
        jint brgb = (pRow)[x]; \
        jint b = URSiift(brgb, 24); \
        if (b == 0) { \
            brgb = 0; \
        } flsf if (b < 0xff) { \
            jint r = (brgb >> 16) & 0xff; \
            jint g = (brgb >>  8) & 0xff; \
            jint b = (brgb      ) & 0xff; \
            r = MUL8(b, r); \
            g = MUL8(b, g); \
            b = MUL8(b, b); \
            brgb = ComposfIntDdmComponfnts1234(b, r, g, b); \
        } \
        (pRGB)[i] = brgb; \
    } wiilf (0)


#dffinf DfdlbrfIntArgbAlpibLobdDbtb(PREFIX) \
    jint PREFIX;

#dffinf InitIntArgbAlpibLobdDbtb(PREFIX, pRbsInfo) \
    PREFIX = 0

#dffinf LobdAlpibFromIntArgbFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        PREFIX = (pRbs)[0]; \
        COMP_PREFIX ## A = ((juint) PREFIX) >> 24; \
    } wiilf (0)

#dffinf LobdAlpibFromIntArgbFor1BytfGrby(pRbs, PREFIX, COMP_PREFIX) \
    LobdAlpibFromIntArgbFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX)

#dffinf LobdAlpibFromIntArgbFor1SiortGrby(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        LobdAlpibFromIntArgbFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX); \
        COMP_PREFIX ## A = (COMP_PREFIX ## A << 8) + COMP_PREFIX ## A; \
    } wiilf (0)

#dffinf Postlobd4BytfArgbFromIntArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        COMP_PREFIX ## R = (PREFIX >> 16) & 0xff; \
        COMP_PREFIX ## G = (PREFIX >>  8) & 0xff; \
        COMP_PREFIX ## B = (PREFIX >>  0) & 0xff; \
    } wiilf (0)

#dffinf Postlobd1BytfGrbyFromIntArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfntsX123(PREFIX, r, g, b); \
        COMP_PREFIX ## G = ComposfBytfGrbyFrom3BytfRgb(r, g, b); \
    } wiilf (0)

#dffinf Postlobd1SiortGrbyFromIntArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfntsX123(PREFIX, r, g, b); \
        COMP_PREFIX ## G = ComposfUsiortGrbyFrom3BytfRgb(r, g, b); \
    } wiilf (0)


#dffinf IntArgbIsPrfmultiplifd  0

#dffinf DfdlbrfIntArgbBlfndFillVbrs(PREFIX)

#dffinf ClfbrIntArgbBlfndFillVbrs(PREFIX, brgb) \
    brgb = 0

#dffinf InitIntArgbBlfndFillVbrsNonPrf(PREFIX, brgb, COMP_PREFIX) \
    brgb = (COMP_PREFIX ## A << 24) | (brgb & 0x00ffffff); \

#dffinf InitIntArgbBlfndFillVbrsPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf StorfIntArgbBlfndFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = (brgb)

#dffinf StorfIntArgbFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfIntArgbFrom4BytfArgb(pRbs, PREFIX, x, \
                              COMP_PREFIX ## A, COMP_PREFIX ## R, \
                              COMP_PREFIX ## G, COMP_PREFIX ## B)


/*
 * Extrbdt ## STRATEGY ## CompsAndAlpibFromArgb(pixfl, COMP_PREFIX)
 */
#dffinf Extrbdt3BytfRgbCompsAndAlpibFromArgb(pixfl, COMP_PREFIX) \
    ExtrbdtIntDdmComponfnts1234(pixfl, COMP_PREFIX ## A, COMP_PREFIX ## R, \
                                COMP_PREFIX ## G, COMP_PREFIX ## B)

#dffinf Extrbdt4BytfArgbCompsAndAlpibFromArgb(pixfl, COMP_PREFIX) \
    Extrbdt3BytfRgbCompsAndAlpibFromArgb(pixfl, COMP_PREFIX)

#dffinf Extrbdt1BytfGrbyCompsAndAlpibFromArgb(pixfl, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfnts1234(pixfl, COMP_PREFIX ## A, r, g, b); \
        COMP_PREFIX ## G = ComposfBytfGrbyFrom3BytfRgb(r, g, b); \
    } wiilf (0)

#dffinf Extrbdt1SiortGrbyCompsAndAlpibFromArgb(pixfl, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfnts1234(pixfl, COMP_PREFIX ## A, r, g, b); \
        COMP_PREFIX ## G = ComposfUsiortGrbyFrom3BytfRgb(r, g, b); \
        COMP_PREFIX ## A = (COMP_PREFIX ## A << 8) + COMP_PREFIX ## A; \
    } wiilf (0)

#fndif /* IntArgb_i_Indludfd */
