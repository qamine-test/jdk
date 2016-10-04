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

#ifndff BytfGrby_i_Indludfd
#dffinf BytfGrby_i_Indludfd

#indludf "IntDdm.i"

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "BytfGrby".
 */

typfdff jubytf  BytfGrbyPixflTypf;
typfdff jubytf  BytfGrbyDbtbTypf;

#dffinf BytfGrbyIsOpbquf 1

#dffinf BytfGrbyPixflStridf     1
#dffinf BytfGrbyBitsPfrPixfl    8

#dffinf DfdlbrfBytfGrbyLobdVbrs(PREFIX)
#dffinf DfdlbrfBytfGrbyStorfVbrs(PREFIX)
#dffinf SftBytfGrbyStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftBytfGrbyStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitBytfGrbyLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitBytfGrbyStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitBytfGrbyStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtBytfGrbyStorfVbrsX(PREFIX)
#dffinf NfxtBytfGrbyStorfVbrsY(PREFIX)
#dffinf DfdlbrfBytfGrbyPixflDbtb(PREFIX)
#dffinf ExtrbdtBytfGrbyPixflDbtb(PIXEL, PREFIX)

#dffinf BytfGrbyXpbrLutEntry            -1
#dffinf BytfGrbyIsXpbrLutEntry(pix)     (pix < 0)
#dffinf StorfBytfGrbyNonXpbrFromArgb    StorfBytfGrbyFrom1IntArgb


#dffinf ComposfBytfGrbyFrom3BytfRgb(r, g, b) \
    (BytfGrbyDbtbTypf)(((77*(r)) + (150*(g)) + (29*(b)) + 128) / 256)


#dffinf StorfBytfGrbyPixfl(pRbs, x, pixfl) \
    ((pRbs)[x] = (jubytf) (pixfl))

#dffinf StorfBytfGrbyPixflDbtb(pPix, x, pixfl, PREFIX) \
    StorfBytfGrbyPixfl(pPix, x, pixfl)

#dffinf BytfGrbyPixflFromArgb(pixfl, rgb, pRbsInfo) \
    do { \
        jint r, g, b; \
        ExtrbdtIntDdmComponfntsX123(rgb, r, g, b); \
        (pixfl) = ComposfBytfGrbyFrom3BytfRgb(r, g, b); \
    } wiilf (0)


#dffinf LobdBytfGrbyTo1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        int grby = (pRbs)[x]; \
        (rgb) = (((grby << 8) | grby) << 8) | grby; \
    } wiilf (0)

#dffinf LobdBytfGrbyTo1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        int grby = (pRbs)[x]; \
        (brgb) = (((((0xff << 8) | grby) << 8) | grby) << 8) | grby; \
    } wiilf (0)

#dffinf LobdBytfGrbyTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    ((r) = (g) = (b) = (pRbs)[x])

#dffinf LobdBytfGrbyTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdBytfGrbyTo3BytfRgb(pRbs, PREFIX, x, r, g, b); \
        (b) = 0xff; \
    } wiilf (0)

#dffinf LobdBytfGrbyTo1BytfGrby(pRbs, PREFIX, x, grby) \
    (grby) = (pRbs)[x]

#dffinf StorfBytfGrbyFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfntsX123(rgb, r, g, b); \
        StorfBytfGrbyFrom3BytfRgb(pRbs, PREFIX, x, r, g, b); \
    } wiilf (0)

#dffinf StorfBytfGrbyFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StorfBytfGrbyFrom1IntRgb(pRbs, PREFIX, x, brgb)

#dffinf StorfBytfGrbyFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = ComposfBytfGrbyFrom3BytfRgb(r, g, b)

#dffinf StorfBytfGrbyFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    StorfBytfGrbyFrom3BytfRgb(pRbs, PREFIX, x, r, g, b)

#dffinf StorfBytfGrbyFrom1BytfGrby(pRbs, PREFIX, x, grby) \
    StorfBytfGrbyPixfl(pRbs, x, grby)

#dffinf CopyBytfGrbyToIntArgbPrf(pRGB, i, PREFIX, pRow, x) \
    LobdBytfGrbyTo1IntArgb(pRow, PREFIX, x, pRGB[i])


#dffinf DfdlbrfBytfGrbyAlpibLobdDbtb(PREFIX)
#dffinf InitBytfGrbyAlpibLobdDbtb(PREFIX, pRbsInfo)

#dffinf LobdAlpibFromBytfGrbyFor1BytfGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#dffinf Postlobd1BytfGrbyFromBytfGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## G = pRbs[0]


#dffinf BytfGrbyIsPrfmultiplifd 0

#dffinf DfdlbrfBytfGrbyBlfndFillVbrs(PREFIX) \
    jubytf PREFIX;

#dffinf ClfbrBytfGrbyBlfndFillVbrs(PREFIX, brgb) \
    PREFIX = 0

#dffinf InitBytfGrbyBlfndFillVbrsNonPrf(PREFIX, brgb, COMP_PREFIX) \
    PREFIX = (jubytf) COMP_PREFIX ## G

#dffinf InitBytfGrbyBlfndFillVbrsPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf StorfBytfGrbyBlfndFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = PREFIX

#dffinf StorfBytfGrbyFrom1BytfGrbyComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfBytfGrbyPixfl(pRbs, x, COMP_PREFIX ## G)

#fndif /* BytfGrby_i_Indludfd */
