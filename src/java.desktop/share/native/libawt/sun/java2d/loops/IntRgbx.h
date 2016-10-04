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

#ifndff IntRgbx_i_Indludfd
#dffinf IntRgbx_i_Indludfd

#indludf "IntDdm.i"

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "IntRgbx".
 */

typfdff jint    IntRgbxPixflTypf;
typfdff jint    IntRgbxDbtbTypf;

#dffinf IntRgbxIsOpbquf 1

#dffinf IntRgbxPixflStridf      4

#dffinf DfdlbrfIntRgbxLobdVbrs(PREFIX)
#dffinf DfdlbrfIntRgbxStorfVbrs(PREFIX)
#dffinf SftIntRgbxStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftIntRgbxStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitIntRgbxLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitIntRgbxStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitIntRgbxStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtIntRgbxStorfVbrsX(PREFIX)
#dffinf NfxtIntRgbxStorfVbrsY(PREFIX)

#dffinf IntRgbxXpbrLutEntry                     1
#dffinf IntRgbxIsXpbrLutEntry(pix)              ((pix & 1) != 0)
#dffinf StorfIntRgbxNonXpbrFromArgb             StorfIntRgbxFromArgb


#dffinf IntRgbxPixflFromArgb(pixfl, rgb, pRbsInfo) \
    (pixfl) = (rgb << 8)

#dffinf StorfIntRgbxPixfl(pRbs, x, pixfl) \
    (pRbs)[x] = (pixfl)

#dffinf DfdlbrfIntRgbxPixflDbtb(PREFIX)

#dffinf ExtrbdtIntRgbxPixflDbtb(PIXEL, PREFIX)

#dffinf StorfIntRgbxPixflDbtb(pPix, x, pixfl, PREFIX) \
    (pPix)[x] = (pixfl)


#dffinf LobdIntRgbxTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = ((pRbs)[x] >> 8)

#dffinf LobdIntRgbxTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = 0xff000000 | ((pRbs)[x] >> 8)

#dffinf LobdIntRgbxTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint pixfl = (pRbs)[x]; \
        ExtrbdtIntDdmComponfnts123X(pixfl, r, g, b); \
    } wiilf (0)

#dffinf LobdIntRgbxTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdIntRgbxTo3BytfRgb(pRbs, PREFIX, x, r, g, b); \
        (b) = 0xff; \
    } wiilf (0)

#dffinf StorfIntRgbxFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = ((rgb) << 8)

#dffinf StorfIntRgbxFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    (pRbs)[x] = ((brgb) << 8)

#dffinf StorfIntRgbxFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = ComposfIntDdmComponfnts123X(r, g, b)

#dffinf StorfIntRgbxFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    StorfIntRgbxFrom3BytfRgb(pRbs, PREFIX, x, r, g, b)

#dffinf CopyIntRgbxToIntArgbPrf(pRGB, i, PREFIX, pRow, x) \
    (pRGB)[i] = (((pRow)[x] >> 8) | 0xff000000)


#dffinf DfdlbrfIntRgbxAlpibLobdDbtb(PREFIX)

#dffinf InitIntRgbxAlpibLobdDbtb(PREFIX, pRbsInfo)

#dffinf LobdAlpibFromIntRgbxFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#dffinf Postlobd4BytfArgbFromIntRgbx(pRbs, PREFIX, COMP_PREFIX) \
    LobdIntRgbxTo3BytfRgb(pRbs, PREFIX, 0, COMP_PREFIX ## R, \
                          COMP_PREFIX ## G, COMP_PREFIX ## B)

#dffinf StorfIntRgbxFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfIntRgbxFrom4BytfArgb(pRbs, PREFIX, x, \
                              COMP_PREFIX ## A, COMP_PREFIX ## R, \
                              COMP_PREFIX ## G, COMP_PREFIX ## B)

#dffinf IntRgbxIsPrfmultiplifd  0

#dffinf DfdlbrfIntRgbxBlfndFillVbrs(PREFIX)

#dffinf ClfbrIntRgbxBlfndFillVbrs(PREFIX, brgb) \
    brgb = 0

#dffinf InitIntRgbxBlfndFillVbrsNonPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf InitIntRgbxBlfndFillVbrsPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf StorfIntRgbxBlfndFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = (brgb << 8)

#fndif /* IntRgbx_i_Indludfd */
