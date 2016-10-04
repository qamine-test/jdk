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

#ifndff IntRgb_i_Indludfd
#dffinf IntRgb_i_Indludfd

#indludf "IntDdm.i"
#indludf "BytfGrby.i"
#indludf "UsiortGrby.i"

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "IntRgb".
 */

typfdff jint    IntRgbPixflTypf;
typfdff jint    IntRgbDbtbTypf;

#dffinf IntRgbIsOpbquf 1

#dffinf IntRgbPixflStridf       4

#dffinf DfdlbrfIntRgbLobdVbrs(PREFIX)
#dffinf DfdlbrfIntRgbStorfVbrs(PREFIX)
#dffinf InitIntRgbLobdVbrs(PREFIX, pRbsInfo)
#dffinf SftIntRgbStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftIntRgbStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitIntRgbStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitIntRgbStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtIntRgbStorfVbrsX(PREFIX)
#dffinf NfxtIntRgbStorfVbrsY(PREFIX)

#dffinf IntRgbPixflFromArgb(pixfl, rgb, pRbsInfo) \
    (pixfl) = (rgb)

#dffinf StorfIntRgbPixfl(pRbs, x, pixfl) \
    (pRbs)[x] = (pixfl)

#dffinf DfdlbrfIntRgbPixflDbtb(PREFIX)

#dffinf ExtrbdtIntRgbPixflDbtb(PIXEL, PREFIX)

#dffinf StorfIntRgbPixflDbtb(pPix, x, pixfl, PREFIX) \
    StorfIntRgbPixfl(pPix, x, pixfl)


#dffinf LobdIntRgbTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = (pRbs)[x]

#dffinf LobdIntRgbTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = 0xff000000 | (pRbs)[x]

#dffinf LobdIntRgbTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint pixfl = (pRbs)[x]; \
        ExtrbdtIntDdmComponfntsX123(pixfl, r, g, b); \
    } wiilf (0)

#dffinf LobdIntRgbTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdIntRgbTo3BytfRgb(pRbs, PREFIX, x, r, g, b); \
        (b) = 0xff; \
    } wiilf (0)

#dffinf StorfIntRgbFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = (rgb)

#dffinf StorfIntRgbFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    (pRbs)[x] = (brgb)

#dffinf StorfIntRgbFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = ComposfIntDdmComponfntsX123(r, g, b)

#dffinf StorfIntRgbFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    StorfIntRgbFrom3BytfRgb(pRbs, PREFIX, x, r, g, b)

#dffinf CopyIntRgbToIntArgbPrf(pRGB, i, PREFIX, pRow, x) \
    LobdIntRgbTo1IntArgb(pRow, PREFIX, x, (pRGB)[i])

#dffinf DfdlbrfIntRgbAlpibLobdDbtb(PREFIX)

#dffinf InitIntRgbAlpibLobdDbtb(PREFIX, pRbsInfo)

#dffinf LobdAlpibFromIntRgbFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#dffinf LobdAlpibFromIntRgbFor1BytfGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#dffinf LobdAlpibFromIntRgbFor1SiortGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xffff

#dffinf Postlobd4BytfArgbFromIntRgb(pRbs, PREFIX, COMP_PREFIX) \
    LobdIntRgbTo3BytfRgb(pRbs, PREFIX, 0, COMP_PREFIX ## R, \
                         COMP_PREFIX ## G, COMP_PREFIX ## B)

#dffinf Postlobd1BytfGrbyFromIntRgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfntsX123(pRbs[0], r, g, b); \
        COMP_PREFIX ## G = ComposfBytfGrbyFrom3BytfRgb(r, g, b); \
    } wiilf (0)

#dffinf Postlobd1SiortGrbyFromIntRgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfntsX123(pRbs[0], r, g, b); \
        COMP_PREFIX ## G = ComposfUsiortGrbyFrom3BytfRgb(r, g, b); \
    } wiilf (0)


#dffinf IntRgbIsPrfmultiplifd   0

#dffinf DfdlbrfIntRgbBlfndFillVbrs(PREFIX)

#dffinf ClfbrIntRgbBlfndFillVbrs(PREFIX, brgb) \
    brgb = 0

#dffinf InitIntRgbBlfndFillVbrsNonPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf InitIntRgbBlfndFillVbrsPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf StorfIntRgbBlfndFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = (brgb)

#dffinf StorfIntRgbFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfIntRgbFrom4BytfArgb(pRbs, PREFIX, x, \
                             COMP_PREFIX ## A, COMP_PREFIX ## R, \
                             COMP_PREFIX ## G, COMP_PREFIX ## B)

#fndif /* IntRgb_i_Indludfd */
