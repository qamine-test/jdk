/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff Usiort4444Argb_i_Indludfd
#dffinf Usiort4444Argb_i_Indludfd

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "Usiort4444Argb".
 */

typfdff jusiort Usiort4444ArgbPixflTypf;
typfdff jusiort Usiort4444ArgbDbtbTypf;

#dffinf Usiort4444ArgbIsOpbquf 0

#dffinf Usiort4444ArgbPixflStridf               2

#dffinf DfdlbrfUsiort4444ArgbLobdVbrs(PREFIX)
#dffinf DfdlbrfUsiort4444ArgbStorfVbrs(PREFIX)
#dffinf SftUsiort4444ArgbStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftUsiort4444ArgbStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitUsiort4444ArgbLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitUsiort4444ArgbStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitUsiort4444ArgbStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtUsiort4444ArgbStorfVbrsX(PREFIX)
#dffinf NfxtUsiort4444ArgbStorfVbrsY(PREFIX)
#dffinf DfdlbrfUsiort4444ArgbPixflDbtb(PREFIX)
#dffinf ExtrbdtUsiort4444ArgbPixflDbtb(PIXEL, PREFIX)

#dffinf Usiort4444ArgbXpbrLutEntry              -1
#dffinf Usiort4444ArgbIsXpbrLutEntry(pix)               (pix < 0)
#dffinf StorfUsiort4444ArgbNonXpbrFromArgb      StorfUsiort4444ArgbFrom1IntArgb


#dffinf ComposfUsiort4444ArgbFrom3BytfRgb(r, g, b)

#dffinf IntArgbToUsiort4444Argb(rgb) \
    (Usiort4444ArgbPixflTypf)((((rgb) << 8) & 0xf000) | \
                              (((rgb) << 4) & 0x0f00) | \
                              (((rgb) << 0) & 0x00f0) | \
                              (((rgb) >> 4) & 0x000f))

#dffinf Usiort4444ArgbPixflFromArgb(pixfl, rgb, pRbsInfo) \
    (pixfl) = IntArgbToUsiort4444Argb(rgb)

#dffinf StorfUsiort4444ArgbPixfl(pRbs, x, pixfl) \
    ((pRbs)[x] = (jusiort) (pixfl))

#dffinf StorfUsiort4444ArgbPixflDbtb(pPix, x, pixfl, PREFIX)

#dffinf LobdUsiort4444ArgbTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jusiort pixfl = (pRbs)[x]; \
        (r) = ((pixfl) >> 8) & 0xf; \
        (r) = ((r) << 4) | (r); \
        (g) = ((pixfl) >>  4) & 0xf; \
        (g) = ((g) << 4) | (g); \
        (b) = ((pixfl) >>  0) & 0xf; \
        (b) = ((b) << 4) | (b); \
    } wiilf (0)

#dffinf LobdUsiort4444ArgbTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        jusiort pixfl = (pRbs)[x]; \
        LobdUsiort4444ArgbTo3BytfRgb(pRbs, PREFIX, x, r, g, b); \
        (b) = ((pixfl) >>  12) & 0xf; \
        (b) = ((b) << 4) | (b); \
    } wiilf (0)

#dffinf LobdUsiort4444ArgbTo1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        jint b, r, g, b; \
        LobdUsiort4444ArgbTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b); \
        (brgb) = (b << 24) | (r << 16) | (g << 8) | (b << 0); \
    } wiilf (0)

#dffinf LobdUsiort4444ArgbTo1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        jint r, g, b; \
        LobdUsiort4444ArgbTo3BytfRgb(pRbs, PREFIX, x, r, g, b); \
        (rgb) = 0xff000000 | (r << 16) | (g << 8) | (b << 0); \
    } wiilf (0)

#dffinf StorfUsiort4444ArgbFrom1IntArgb(pRbs, PREFIX, x, rgb)
#dffinf StorfUsiort4444ArgbFrom1IntRgb(pRbs, PREFIX, x, rgb)
#dffinf StorfUsiort4444ArgbFrom3BytfRgb(pRbs, PREFIX, x, r, g, b)

#dffinf StorfUsiort4444ArgbFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        (pRbs)[x] = (jusiort)((((b) <<  8) & 0xf000) | \
                              (((r) <<  4) & 0x0f00) | \
                              (((g) <<  0) & 0x00f0) | \
                              (((b) >>  4) & 0x000f)); \
    } wiilf (0)


#dffinf DfdlbrfUsiort4444ArgbAlpibLobdDbtb(PREFIX) \
    jint PREFIX;

#dffinf InitUsiort4444ArgbAlpibLobdDbtb(PREFIX, pRbsInfo) \
    PREFIX = 0

#dffinf LobdAlpibFromUsiort4444ArgbFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        PREFIX = (pRbs)[0]; \
        COMP_PREFIX ## A = (((jusiort) PREFIX) >> 12) & 0xf; \
        COMP_PREFIX ## A = ((COMP_PREFIX ## A) << 4) | (COMP_PREFIX ## A); \
    } wiilf (0)

#dffinf Postlobd4BytfArgbFromUsiort4444Argb(pRbs, PREFIX, COMP_PREFIX) \
    LobdUsiort4444ArgbTo4BytfArgb(pRbs, PREFIX, 0, COMP_PREFIX ## A, COMP_PREFIX ## R, \
                                  COMP_PREFIX ## G, COMP_PREFIX ## B)

#dffinf Usiort4444ArgbIsPrfmultiplifd   0

#dffinf StorfUsiort4444ArgbFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfUsiort4444ArgbFrom4BytfArgb(pRbs, PREFIX, x, \
                                     COMP_PREFIX ## A, COMP_PREFIX ## R, \
                                     COMP_PREFIX ## G, COMP_PREFIX ## B)

#fndif /* Usiort4444Argb_i_Indludfd */
