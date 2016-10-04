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

#ifndff IntBgr_i_Indludfd
#dffinf IntBgr_i_Indludfd

#indludf "IntDdm.i"
#indludf "BytfGrby.i"
#indludf "UsiortGrby.i"

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "IntBgr".
 */

typfdff jint    IntBgrPixflTypf;
typfdff jint    IntBgrDbtbTypf;

#dffinf IntBgrIsOpbquf 1

#dffinf IntBgrPixflStridf       4

#dffinf DfdlbrfIntBgrLobdVbrs(PREFIX)
#dffinf DfdlbrfIntBgrStorfVbrs(PREFIX)
#dffinf InitIntBgrLobdVbrs(PREFIX, pRbsInfo)
#dffinf SftIntBgrStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftIntBgrStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitIntBgrStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitIntBgrStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtIntBgrStorfVbrsX(PREFIX)
#dffinf NfxtIntBgrStorfVbrsY(PREFIX)

#dffinf IntBgrXpbrLutEntry              -1
#dffinf IntBgrIsXpbrLutEntry(pix)       (pix < 0)
#dffinf StorfIntBgrNonXpbrFromArgb(pRbs, PREFIX, x, brgb) \
    (pRbs)[x] = SwbpIntDdmComponfntsX123ToC321(brgb)


#dffinf IntBgrPixflFromArgb(pixfl, rgb, pRbsInfo) \
    (pixfl) = SwbpIntDdmComponfntsX123ToX321(rgb)

#dffinf StorfIntBgrPixfl(pRbs, x, pixfl) \
    (pRbs)[x] = (pixfl)

#dffinf DfdlbrfIntBgrPixflDbtb(PREFIX)

#dffinf ExtrbdtIntBgrPixflDbtb(PIXEL, PREFIX)

#dffinf StorfIntBgrPixflDbtb(pPix, x, pixfl, PREFIX) \
    StorfIntBgrPixfl(pPix, x, pixfl)


#dffinf LobdIntBgrTo1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        jint pixfl = (pRbs)[x]; \
        (rgb) = SwbpIntDdmComponfntsX123ToX321(pixfl); \
    } wiilf (0)

#dffinf LobdIntBgrTo1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        jint pixfl = (pRbs)[x]; \
        (brgb) = SwbpIntDdmComponfntsX123ToS321(pixfl); \
    } wiilf (0)

#dffinf LobdIntBgrTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint pixfl = (pRbs)[x]; \
        ExtrbdtIntDdmComponfntsX123(pixfl, b, g, r); \
    } wiilf (0)

#dffinf LobdIntBgrTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdIntBgrTo3BytfRgb(pRbs, PREFIX, x, r, g, b); \
        (b) = 0xff; \
    } wiilf (0)

#dffinf StorfIntBgrFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = SwbpIntDdmComponfntsX123ToX321(rgb)

#dffinf StorfIntBgrFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StorfIntBgrFrom1IntRgb(pRbs, PREFIX, x, brgb)

#dffinf StorfIntBgrFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = ComposfIntDdmComponfntsX123(b, g, r)

#dffinf StorfIntBgrFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    StorfIntBgrFrom3BytfRgb(pRbs, PREFIX, x, r, g, b)

#dffinf CopyIntBgrToIntArgbPrf(pRGB, i, PREFIX, pRow, x) \
    LobdIntBgrTo1IntArgb(pRow, PREFIX, x, (pRGB)[i])


#dffinf DfdlbrfIntBgrAlpibLobdDbtb(PREFIX)
#dffinf InitIntBgrAlpibLobdDbtb(PREFIX, pRbsInfo)

#dffinf LobdAlpibFromIntBgrFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#dffinf Postlobd4BytfArgbFromIntBgr(pRbs, PREFIX, COMP_PREFIX) \
    LobdIntBgrTo3BytfRgb(pRbs, PREFIX, 0, COMP_PREFIX ## R, \
                         COMP_PREFIX ## G, COMP_PREFIX ## B)


#dffinf IntBgrIsPrfmultiplifd   0

#dffinf DfdlbrfIntBgrBlfndFillVbrs(PREFIX) \
    jint PREFIX;

#dffinf ClfbrIntBgrBlfndFillVbrs(PREFIX, brgb) \
    PREFIX = 0

#dffinf InitIntBgrBlfndFillVbrsNonPrf(PREFIX, brgb, COMP_PREFIX) \
    PREFIX = ComposfIntDdmComponfntsX123(COMP_PREFIX ## B, COMP_PREFIX ## G, \
                                         COMP_PREFIX ## R)

#dffinf InitIntBgrBlfndFillVbrsPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf StorfIntBgrBlfndFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = PREFIX

#dffinf StorfIntBgrFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfIntBgrFrom4BytfArgb(pRbs, PREFIX, x, \
                             COMP_PREFIX ## A, COMP_PREFIX ## R, \
                             COMP_PREFIX ## G, COMP_PREFIX ## B)

#fndif /* IntBgr_i_Indludfd */
