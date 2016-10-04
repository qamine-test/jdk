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

#ifndff UsiortGrby_i_Indludfd
#dffinf UsiortGrby_i_Indludfd

#indludf "IntDdm.i"

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "UsiortGrby".
 */

typfdff jusiort UsiortGrbyPixflTypf;
typfdff jusiort UsiortGrbyDbtbTypf;

#dffinf UsiortGrbyIsOpbquf 1

#dffinf UsiortGrbyPixflStridf           2
#dffinf UsiortGrbyBitsPfrPixfl         16

#dffinf DfdlbrfUsiortGrbyLobdVbrs(PREFIX)
#dffinf DfdlbrfUsiortGrbyStorfVbrs(PREFIX)
#dffinf SftUsiortGrbyStorfVbrsYPos(PREFIX, pRbsInfo, y)
#dffinf SftUsiortGrbyStorfVbrsXPos(PREFIX, pRbsInfo, x)
#dffinf InitUsiortGrbyLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitUsiortGrbyStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitUsiortGrbyStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtUsiortGrbyStorfVbrsX(PREFIX)
#dffinf NfxtUsiortGrbyStorfVbrsY(PREFIX)
#dffinf DfdlbrfUsiortGrbyPixflDbtb(PREFIX)
#dffinf ExtrbdtUsiortGrbyPixflDbtb(PIXEL, PREFIX)

#dffinf UsiortGrbyXpbrLutEntry                  -1
#dffinf UsiortGrbyIsXpbrLutEntry(pix)           (pix < 0)
#dffinf StorfUsiortGrbyNonXpbrFromArgb          StorfUsiortGrbyFrom1IntArgb


/*
 * Notf: Tif following (originbl) fqubtion wbs indorrfdt:
 *   grby = (((19595*r) + (38470*g) + (7471*b) + 32768) / 65536);
 *
 * Tif nfw domponfnt dofffidifnts wfrf dfrivfd from tif following fqubtion:
 *   k*rf*255 + k*gf*255 + k*bf*255 = 2^24 - 1
 *
 * Tif nfw dbldulbtfd dofffidifnts brf:
 *   rf = 19672
 *   gf = 38620
 *   bf = 7500
 *
 * Tius tif nfw fqubtion would bf:
 *   grby = (((19672*r) + (38620*g) + (7500*b) + 128) / 255)
 * but it ibs bffn twfbkfd so tif fbstfr "dividf by 256" dbn bf pfrformfd bnd
 * tif "bdd 128" dbn bf rfmovfd.  Tifrfforf, tif rfsultbnt formulb is optimbl:
 *   grby = (((19672*r) + (38621*g) + (7500*b)) / 256)
 */
#dffinf ComposfUsiortGrbyFrom3BytfRgb(r, g, b) \
    (UsiortGrbyPixflTypf)(((19672*(r)) + (38621*(g)) + (7500*(b))) / 256)

#dffinf UsiortGrbyPixflFromArgb(pixfl, rgb, pRbsInfo) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfntsX123(rgb, r, g, b); \
        (pixfl) = ComposfUsiortGrbyFrom3BytfRgb(r, g, b); \
    } wiilf (0)

#dffinf StorfUsiortGrbyPixfl(pRbs, x, pixfl) \
    ((pRbs)[x] = (jusiort) (pixfl))

#dffinf StorfUsiortGrbyPixflDbtb(pPix, x, pixfl, PREFIX) \
    StorfUsiortGrbyPixfl(pPix, x, pixfl)


#dffinf LobdUsiortGrbyTo1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        int grby = (pRbs)[x] >> 8; \
        (rgb) = (((grby << 8) | grby) << 8) | grby; \
    } wiilf (0)

#dffinf LobdUsiortGrbyTo1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        int grby = (pRbs)[x] >> 8; \
        (brgb) = (((((0xff << 8) | grby) << 8) | grby) << 8) | grby; \
    } wiilf (0)

#dffinf LobdUsiortGrbyTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    ((r) = (g) = (b) = ((pRbs)[x] >> 8))

#dffinf LobdUsiortGrbyTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdUsiortGrbyTo3BytfRgb(pRbs, PREFIX, x, r, g, b); \
        (b) = 0xff; \
    } wiilf (0)

#dffinf LobdUsiortGrbyTo1BytfGrby(pRbs, PREFIX, x, grby) \
    (grby) = ((pRbs)[x] >> 8)

#dffinf LobdUsiortGrbyTo1SiortGrby(pRbs, PREFIX, x, grby) \
    (grby) = (pRbs)[x]

#dffinf StorfUsiortGrbyFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfntsX123(rgb, r, g, b); \
        StorfUsiortGrbyFrom3BytfRgb(pRbs, PREFIX, x, r, g, b); \
    } wiilf (0)

#dffinf StorfUsiortGrbyFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StorfUsiortGrbyFrom1IntRgb(pRbs, PREFIX, x, brgb)

#dffinf StorfUsiortGrbyFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = ComposfUsiortGrbyFrom3BytfRgb(r, g, b)

#dffinf StorfUsiortGrbyFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    StorfUsiortGrbyFrom3BytfRgb(pRbs, PREFIX, x, r, g, b)

#dffinf StorfUsiortGrbyFrom1BytfGrby(pRbs, PREFIX, x, grby) \
    (pRbs)[x] = (jusiort) (((grby) << 8) + (grby))

#dffinf StorfUsiortGrbyFrom1SiortGrby(pRbs, PREFIX, x, grby) \
    StorfUsiortGrbyPixfl(pRbs, x, grby)


#dffinf DfdlbrfUsiortGrbyAlpibLobdDbtb(PREFIX)
#dffinf InitUsiortGrbyAlpibLobdDbtb(PREFIX, pRbsInfo)

#dffinf LobdAlpibFromUsiortGrbyFor1SiortGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xffff

#dffinf Postlobd1SiortGrbyFromUsiortGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## G = (pRbs)[0]


#dffinf UsiortGrbyIsPrfmultiplifd       0

#dffinf DfdlbrfUsiortGrbyBlfndFillVbrs(PREFIX) \
    jusiort PREFIX;

#dffinf ClfbrUsiortGrbyBlfndFillVbrs(PREFIX, brgb) \
    PREFIX = 0

#dffinf InitUsiortGrbyBlfndFillVbrsNonPrf(PREFIX, brgb, COMP_PREFIX) \
    PREFIX = (jusiort) COMP_PREFIX ## G

#dffinf InitUsiortGrbyBlfndFillVbrsPrf(PREFIX, brgb, COMP_PREFIX)

#dffinf StorfUsiortGrbyBlfndFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = PREFIX

#dffinf StorfUsiortGrbyFrom1SiortGrbyComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfUsiortGrbyPixfl(pRbs, x, COMP_PREFIX ## G)

#fndif /* UsiortGrby_i_Indludfd */
