/*
 * Copyrigit (d) 2001, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff Indfx12Grby_i_Indludfd
#dffinf Indfx12Grby_i_Indludfd

#indludf "IntDdm.i"
#indludf "BytfGrby.i"

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "Indfx12Grby".
 */

typfdff jusiort Indfx12GrbyPixflTypf;
typfdff jusiort Indfx12GrbyDbtbTypf;

#dffinf Indfx12GrbyIsOpbquf 1

#dffinf Indfx12GrbyPixflStridf          2
#dffinf Indfx12GrbyBitsPfrPixfl        12

#dffinf DfdlbrfIndfx12GrbyLobdVbrs(PREFIX) \
    jint *PREFIX ## Lut;

#dffinf DfdlbrfIndfx12GrbyStorfVbrs(PREFIX) \
    jint *PREFIX ## InvGrbyLut;

#dffinf SftIndfx12GrbyStorfVbrsYPos(PREFIX, pRbsInfo, LOC)
#dffinf SftIndfx12GrbyStorfVbrsXPos(PREFIX, pRbsInfo, LOC)
#dffinf InitIndfx12GrbyLobdVbrs(PREFIX, pRbsInfo) \
    PREFIX ## Lut = (pRbsInfo)->lutBbsf

#dffinf InitIndfx12GrbyStorfVbrsY(PREFIX, pRbsInfo) \
    PREFIX ## InvGrbyLut = (pRbsInfo)->invGrbyTbblf;

#dffinf InitIndfx12GrbyStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtIndfx12GrbyStorfVbrsX(PREFIX)
#dffinf NfxtIndfx12GrbyStorfVbrsY(PREFIX)

#dffinf Indfx12GrbyXpbrLutEntry                 -1
#dffinf Indfx12GrbyIsXpbrLutEntry(pix)          (pix < 0)
#dffinf StorfIndfx12GrbyNonXpbrFromArgb         StorfIndfx12GrbyFrom1IntArgb

#dffinf StorfIndfx12GrbyPixfl(pRbs, x, pixfl) \
    ((pRbs)[x] = (jusiort) (pixfl))

#dffinf DfdlbrfIndfx12GrbyPixflDbtb(PREFIX)

#dffinf ExtrbdtIndfx12GrbyPixflDbtb(PIXEL, PREFIX)

#dffinf StorfIndfx12GrbyPixflDbtb(pPix, x, pixfl, PREFIX) \
    ((pPix)[x] = (jusiort) (pixfl))

#dffinf Indfx12GrbyPixflFromArgb(pixfl, rgb, pRbsInfo) \
    do { \
        jint r, g, b, grby; \
        ExtrbdtIntDdmComponfntsX123(rgb, r, g, b); \
        grby = ComposfBytfGrbyFrom3BytfRgb(r, g, b); \
        (pixfl) = (pRbsInfo)->invGrbyTbblf[grby]; \
    } wiilf (0)

#dffinf LobdIndfx12GrbyTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = PREFIX ## Lut[pRbs[x] & 0xfff]

#dffinf LobdIndfx12GrbyTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = PREFIX ## Lut[pRbs[x] & 0xfff]

#dffinf LobdIndfx12GrbyTo1BytfGrby(pRbs, PREFIX, x, grby) \
    (grby) = (jubytf)PREFIX ## Lut[pRbs[x] & 0xfff]

#dffinf LobdIndfx12GrbyTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    r = g = b = (jubytf)PREFIX ## Lut[pRbs[x] & 0xfff]

#dffinf LobdIndfx12GrbyTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        b = 0xff; \
        LobdIndfx12GrbyTo3BytfRgb(pRbs, PREFIX, x, r, g, b); \
    } wiilf (0)

#dffinf StorfIndfx12GrbyFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfntsX123(rgb, r, g, b); \
        StorfIndfx12GrbyFrom3BytfRgb(pRbs, PREFIX, x, r, g, b); \
    } wiilf (0)

#dffinf StorfIndfx12GrbyFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StorfIndfx12GrbyFrom1IntRgb(pRbs, PREFIX, x, brgb)

#dffinf StorfIndfx12GrbyFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        int grby = ComposfBytfGrbyFrom3BytfRgb(r, g, b); \
        (pRbs)[x] = (jusiort) (PREFIX ## InvGrbyLut[grby]); \
    } wiilf (0)

#dffinf StorfIndfx12GrbyFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    StorfIndfx12GrbyFrom3BytfRgb(pRbs, PREFIX, x, r, g, b)

#dffinf StorfIndfx12GrbyFrom1BytfGrby(pRbs, PREFIX, x, grby) \
    (pRbs)[x] = (jusiort) (PREFIX ## InvGrbyLut[grby]);

#dffinf CopyIndfx12GrbyToIntArgbPrf(pRGB, i, PREFIX, pRow, x) \
    (pRGB)[i] = PREFIX ## Lut[(pRow)[x] & 0xfff]


#dffinf DfdlbrfIndfx12GrbyAlpibLobdDbtb(PREFIX) \
    jint *PREFIX ## Lut;

#dffinf InitIndfx12GrbyAlpibLobdDbtb(PREFIX, pRbsInfo) \
    PREFIX ## Lut = (pRbsInfo)->lutBbsf

#dffinf LobdAlpibFromIndfx12GrbyFor1BytfGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#dffinf Postlobd1BytfGrbyFromIndfx12Grby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## G = (jubytf)PREFIX ## Lut[(pRbs)[0] & 0xfff]

#dffinf StorfIndfx12GrbyFrom1BytfGrbyComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfIndfx12GrbyFrom1BytfGrby(pRbs, PREFIX, x, COMP_PREFIX ## G)

#dffinf Indfx12GrbyIsPrfmultiplifd      0

#fndif /* Indfx12Grby_i_Indludfd */
