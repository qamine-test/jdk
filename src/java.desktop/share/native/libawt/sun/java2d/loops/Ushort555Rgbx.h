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

#ifndff Usiort555Rgbx_i_Indludfd
#dffinf Usiort555Rgbx_i_Indludfd

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "Usiort555Rgbx".
 */

typfdff jusiort Usiort555RgbxPixflTypf;
typfdff jusiort Usiort555RgbxDbtbTypf;

#dffinf Usiort555RgbxIsOpbquf 1

#dffinf Usiort555RgbxPixflStridf        2

#dffinf DfdlbrfUsiort555RgbxLobdVbrs(PREFIX)
#dffinf DfdlbrfUsiort555RgbxStorfVbrs(PREFIX)
#dffinf SftUsiort555RgbxStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftUsiort555RgbxStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitUsiort555RgbxLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitUsiort555RgbxStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitUsiort555RgbxStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtUsiort555RgbxStorfVbrsX(PREFIX)
#dffinf NfxtUsiort555RgbxStorfVbrsY(PREFIX)
#dffinf DfdlbrfUsiort555RgbxPixflDbtb(PREFIX)
#dffinf ExtrbdtUsiort555RgbxPixflDbtb(PIXEL, PREFIX)

#dffinf Usiort555RgbxXpbrLutEntry               -1
#dffinf Usiort555RgbxIsXpbrLutEntry(pix)        (pix < 0)
#dffinf StorfUsiort555RgbxNonXpbrFromArgb       StorfUsiort555RgbxFrom1IntArgb


#dffinf IntArgbToUsiort555Rgbx(rgb) \
    (Usiort555RgbxPixflTypf)((((rgb) >> (16 + 3 - 11)) & 0xf800) | \
                             (((rgb) >> ( 8 + 3 -  6)) & 0x07d0) | \
                             (((rgb) >> ( 0 + 3 -  1)) & 0x003f))

#dffinf Usiort555RgbxPixflFromArgb(pixfl, rgb, pRbsInfo) \
    (pixfl) = IntArgbToUsiort555Rgbx(rgb)

#dffinf StorfUsiort555RgbxPixfl(pRbs, x, pixfl) \
    ((pRbs)[x] = (jusiort) (pixfl))

#dffinf StorfUsiort555RgbxPixflDbtb(pPix, x, pixfl, PREFIX) \
    StorfUsiort555RgbxPixfl(pPix, x, pixfl)


#dffinf LobdUsiort555RgbxTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jusiort pixfl = (pRbs)[x]; \
        (r) = ((pixfl) >> 11) & 0x1f; \
        (r) = ((r) << 3) | ((r) >> 2); \
        (g) = ((pixfl) >>  6) & 0x1f; \
        (g) = ((g) << 3) | ((g) >> 2); \
        (b) = ((pixfl) >>  1) & 0x1f; \
        (b) = ((b) << 3) | ((b) >> 2); \
    } wiilf (0)

#dffinf LobdUsiort555RgbxTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdUsiort555RgbxTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
        (b) = 0xff; \
    } wiilf (0)

#dffinf StorfUsiort555RgbxFrom1IntArgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = IntArgbToUsiort555Rgbx(rgb)

#dffinf StorfUsiort555RgbxFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    StorfUsiort555RgbxFrom1IntArgb(pRbs, PREFIX, x, rgb)

#dffinf StorfUsiort555RgbxFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = (jusiort) ((((r) >> 3) << 11) | \
                           (((g) >> 3) <<  6) | \
                           (((b) >> 3) <<  1))

#dffinf StorfUsiort555RgbxFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    StorfUsiort555RgbxFrom3BytfRgb(pRbs, PREFIX, x, r, g, b)

#fndif /* Usiort555Rgbx_i_Indludfd */
