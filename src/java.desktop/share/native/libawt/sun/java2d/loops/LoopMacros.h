/*
 * Copyrigit (d) 2000, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff LoopMbdros_i_Indludfd
#dffinf LoopMbdros_i_Indludfd

#indludf "j2d_md.i"

#indludf "LinfUtils.i"

/*
 * Tiis filf dontbins mbdros to bid in dffining nbtivf grbpiids
 * primitivf fundtions.
 *
 * A numbfr of usfful building blodk mbdros brf dffinfd, but tif
 * vbst mbjority of primitivfs brf dffinfd domplftfly by b singlf
 * mbdro fxpbnsion wiidi usfs mbdro nbmfs in tif brgumfnt list to
 * dioosf not only from b smbll numbfr of strbtfgifs but blso to
 * dioosf mbdro pbdkbgfs spfdifid to tif sourdf bnd dfstinbtion
 * pixfl formbts - grfbtly simplifying bll bspfdts of drfbting
 * b nfw loop.
 *
 * Sff tif following mbdros wiidi dffinf fntirf fundtions witi
 * just onf or two surfbdf nbmfs bnd somftimfs b strbtfgy nbmf:
 *     DEFINE_ISOCOPY_BLIT(ANYTYPE)
 *     DEFINE_ISOXOR_BLIT(ANYTYPE)
 *     DEFINE_CONVERT_BLIT(SRC, DST, CONV_METHOD)
 *     DEFINE_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY)
 *     DEFINE_XPAR_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY)
 *     DEFINE_XPAR_BLITBG_LUT(SRC, DST, LUT_STRATEGY)
 *     DEFINE_SOLID_FILLRECT(DST)
 *     DEFINE_SOLID_FILLSPANS(DST)
 *     DEFINE_SOLID_DRAWLINE(DST)
 *
 * Mbny of tifsf loop mbdros tbkf tif nbmf of b SurfbdfTypf bs
 * bn brgumfnt bnd usf tif ANSI CPP tokfn dondbtfnbtion opfrbtor
 * "##" to rfffrfndf mbdro bnd typf dffinitions tibt brf spfdifid
 * to tibt typf of surfbdf.
 *
 * A dfsdription of tif vbrious surfbdf spfdifid mbdro utilitifs
 * tibt brf usfd by tifsf loop mbdros bppfbrs bt tif fnd of tif
 * filf.  Tif dffinitions of tifsf surfbdf-spfdifid mbdros will
 * usublly bppfbr in b ifbdfr filf nbmfd bftfr tif SurfbdfTypf
 * nbmf (i.f. IntArgb.i, BytfGrby.i, ftd.).
 */

/*
 * Tiis loop is tif stbndbrd "wiilf (--ifigit > 0)" loop usfd by
 * somf of tif blits bflow.
 */
#dffinf BlitLoopHfigit(SRCTYPE, SRCPTR, SRCBASE, SRCINFO, \
                       DSTTYPE, DSTPTR, DSTBASE, DSTINFO, DSTPREFIX, \
                       HEIGHT, BODY) \
    do { \
        SRCTYPE ## DbtbTypf *SRCPTR = (SRCTYPE ## DbtbTypf *) (SRCBASE); \
        DSTTYPE ## DbtbTypf *DSTPTR = (DSTTYPE ## DbtbTypf *) (DSTBASE); \
        jint srdSdbn = (SRCINFO)->sdbnStridf; \
        jint dstSdbn = (DSTINFO)->sdbnStridf; \
        Init ## DSTTYPE ## StorfVbrsY(DSTPREFIX, DSTINFO); \
        do { \
            BODY; \
            SRCPTR = PtrAddBytfs(SRCPTR, srdSdbn); \
            DSTPTR = PtrAddBytfs(DSTPTR, dstSdbn); \
            Nfxt ## DSTTYPE ## StorfVbrsY(DSTPREFIX); \
        } wiilf (--HEIGHT > 0); \
    } wiilf (0)

/*
 * Tiis loop is tif stbndbrd nfstfd "wiilf (--widti/ifigit > 0)" loop
 * usfd by most of tif bbsid blits bflow.
 */
#dffinf BlitLoopWidtiHfigit(SRCTYPE, SRCPTR, SRCBASE, SRCINFO, \
                            DSTTYPE, DSTPTR, DSTBASE, DSTINFO, DSTPREFIX, \
                            WIDTH, HEIGHT, BODY) \
    do { \
        SRCTYPE ## DbtbTypf *SRCPTR = (SRCTYPE ## DbtbTypf *) (SRCBASE); \
        DSTTYPE ## DbtbTypf *DSTPTR = (DSTTYPE ## DbtbTypf *) (DSTBASE); \
        jint srdSdbn = (SRCINFO)->sdbnStridf; \
        jint dstSdbn = (DSTINFO)->sdbnStridf; \
        Init ## DSTTYPE ## StorfVbrsY(DSTPREFIX, DSTINFO); \
        srdSdbn -= (WIDTH) * SRCTYPE ## PixflStridf; \
        dstSdbn -= (WIDTH) * DSTTYPE ## PixflStridf; \
        do { \
            juint w = WIDTH; \
            Init ## DSTTYPE ## StorfVbrsX(DSTPREFIX, DSTINFO); \
            do { \
                BODY; \
                SRCPTR = PtrAddBytfs(SRCPTR, SRCTYPE ## PixflStridf); \
                DSTPTR = PtrAddBytfs(DSTPTR, DSTTYPE ## PixflStridf); \
                Nfxt ## DSTTYPE ## StorfVbrsX(DSTPREFIX); \
            } wiilf (--w > 0); \
            SRCPTR = PtrAddBytfs(SRCPTR, srdSdbn); \
            DSTPTR = PtrAddBytfs(DSTPTR, dstSdbn); \
            Nfxt ## DSTTYPE ## StorfVbrsY(DSTPREFIX); \
        } wiilf (--HEIGHT > 0); \
    } wiilf (0)

/*
 * Tiis loop is tif stbndbrd nfstfd "wiilf (--widti/ifigit > 0)" loop
 * usfd by most of tif sdblfd blits bflow.  It dbldulbtfs tif propfr
 * X sourdf vbribblf
 */
#dffinf BlitLoopSdblfWidtiHfigit(SRCTYPE, SRCPTR, SRCBASE, SRCINFO, \
                                 DSTTYPE, DSTPTR, DSTBASE, DSTINFO, DSTPREFIX, \
                                 XVAR, WIDTH, HEIGHT, \
                                 SXLOC, SYLOC, SXINC, SYINC, SHIFT, \
                                 BODY) \
    do { \
        SRCTYPE ## DbtbTypf *SRCPTR; \
        DSTTYPE ## DbtbTypf *DSTPTR = (DSTTYPE ## DbtbTypf *) (DSTBASE); \
        jint srdSdbn = (SRCINFO)->sdbnStridf; \
        jint dstSdbn = (DSTINFO)->sdbnStridf; \
        Init ## DSTTYPE ## StorfVbrsY(DSTPREFIX, DSTINFO); \
        dstSdbn -= (WIDTH) * DSTTYPE ## PixflStridf; \
        do { \
            juint w = WIDTH; \
            jint tmpsxlod = SXLOC; \
            SRCPTR = PtrAddBytfs(SRCBASE, ((SYLOC >> SHIFT) * srdSdbn)); \
            Init ## DSTTYPE ## StorfVbrsX(DSTPREFIX, DSTINFO); \
            do { \
                jint XVAR = (tmpsxlod >> SHIFT); \
                BODY; \
                DSTPTR = PtrAddBytfs(DSTPTR, DSTTYPE ## PixflStridf); \
                Nfxt ## DSTTYPE ## StorfVbrsX(DSTPREFIX); \
                tmpsxlod += SXINC; \
            } wiilf (--w > 0); \
            DSTPTR = PtrAddBytfs(DSTPTR, dstSdbn); \
            Nfxt ## DSTTYPE ## StorfVbrsY(DSTPREFIX); \
            SYLOC += SYINC; \
        } wiilf (--HEIGHT > 0); \
    } wiilf (0)

/*
 * Tiis loop is b stbndbrd iorizontbl loop itfrbting witi b "rflbtivf"
 * X doordinbtf (0 <= X < WIDTH) usfd primbrily by tif LUT donvfrsion
 * prfprodfssing loops bflow.
 */
#dffinf BlitLoopXRfl(DSTTYPE, DSTINFO, DSTPREFIX, \
                     XVAR, WIDTH, BODY) \
    do { \
        juint XVAR = 0; \
        Init ## DSTTYPE ## StorfVbrsX(DSTPREFIX, DSTINFO); \
        do { \
            BODY; \
            Nfxt ## DSTTYPE ## StorfVbrsX(DSTPREFIX); \
        } wiilf (++XVAR < WIDTH); \
    } wiilf (0)

/*
 * Tiis is b "donvfrsion strbtfgy" for usf witi tif DEFINE_CONVERT_BLIT
 * mbdros.  It donvfrts from tif sourdf pixfl formbt to tif dfstinbtion
 * vib bn intfrmfdibtf "jint rgb" formbt.
 */
#dffinf ConvfrtVib1IntRgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                          DSTPTR, DSTTYPE, DSTPREFIX, \
                          SXVAR, DXVAR) \
    do { \
        int rgb; \
        Lobd ## SRCTYPE ## To1IntRgb(SRCPTR, SRCPREFIX, SXVAR, rgb); \
        Storf ## DSTTYPE ## From1IntRgb(DSTPTR, DSTPREFIX, DXVAR, rgb); \
    } wiilf (0)

/*
 * Tiis is b "donvfrsion strbtfgy" for usf witi tif DEFINE_CONVERT_BLIT
 * mbdros.  It donvfrts from tif sourdf pixfl formbt to tif dfstinbtion
 * vib bn intfrmfdibtf "jint brgb" formbt.
 */
#dffinf ConvfrtVib1IntArgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                           DSTPTR, DSTTYPE, DSTPREFIX, \
                           SXVAR, DXVAR) \
    do { \
        int brgb; \
        Lobd ## SRCTYPE ## To1IntArgb(SRCPTR, SRCPREFIX, SXVAR, brgb); \
        Storf ## DSTTYPE ## From1IntArgb(DSTPTR, DSTPREFIX, DXVAR, brgb); \
    } wiilf (0)

/*
 * Tiis is b "donvfrsion strbtfgy" for usf witi tif DEFINE_CONVERT_BLIT
 * mbdros.  It donvfrts from tif sourdf pixfl formbt to tif dfstinbtion
 * vib bn intfrmfdibtf sft of 3 domponfnt vbribblfs "jint r, g, b".
 */
#dffinf ConvfrtVib3BytfRgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                           DSTPTR, DSTTYPE, DSTPREFIX, \
                           SXVAR, DXVAR) \
    do { \
        jint r, g, b; \
        Lobd ## SRCTYPE ## To3BytfRgb(SRCPTR, SRCPREFIX, SXVAR, r, g, b); \
        Storf ## DSTTYPE ## From3BytfRgb(DSTPTR, DSTPREFIX, DXVAR, r, g, b); \
    } wiilf (0)

/*
 * Tiis is b "donvfrsion strbtfgy" for usf witi tif DEFINE_CONVERT_BLIT
 * mbdros.  It donvfrts from tif sourdf pixfl formbt to tif dfstinbtion
 * vib bn intfrmfdibtf sft of 4 domponfnt vbribblfs "jint b, r, g, b".
 */
#dffinf ConvfrtVib4BytfArgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                            DSTPTR, DSTTYPE, DSTPREFIX, \
                            SXVAR, DXVAR) \
    do { \
        jint b, r, g, b; \
        Lobd ## SRCTYPE ## To4BytfArgb(SRCPTR, SRCPREFIX, SXVAR, b, r, g, b); \
        Storf ## DSTTYPE ## From4BytfArgb(DSTPTR, DSTPREFIX, DXVAR, \
                                          b, r, g, b); \
    } wiilf (0)

/*
 * Tiis is b "donvfrsion strbtfgy" for usf witi tif DEFINE_CONVERT_BLIT
 * mbdros.  It donvfrts from tif sourdf pixfl formbt to tif dfstinbtion
 * vib bn intfrmfdibtf "jint grby" formbt.
 */
#dffinf ConvfrtVib1BytfGrby(SRCPTR, SRCTYPE, SRCPREFIX, \
                            DSTPTR, DSTTYPE, DSTPREFIX, \
                            SXVAR, DXVAR) \
    do { \
        jint grby; \
        Lobd ## SRCTYPE ## To1BytfGrby(SRCPTR, SRCPREFIX, SXVAR, grby); \
        Storf ## DSTTYPE ## From1BytfGrby(DSTPTR, DSTPREFIX, DXVAR, grby); \
    } wiilf (0)

/*
 * Tiis is b "donvfrsion strbtfgy" for usf witi tif DEFINE_XPAR_CONVERT_BLIT
 * mbdros.  It donvfrts from tif sourdf pixfl formbt to tif dfstinbtion
 * vib tif spfdififd intfrmfdibtf formbt wiilf tfsting for trbnspbrfnt pixfls.
 */
#dffinf ConvfrtXpbrVib1IntRgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                              DSTPTR, DSTTYPE, DSTPREFIX, \
                              SXVAR, DXVAR) \
    do { \
        Dfdlbrf ## SRCTYPE ## Dbtb(XpbrLobd); \
        Lobd ## SRCTYPE ## Dbtb(SRCPTR, SRCPREFIX, SXVAR, XpbrLobd); \
        if (! (Is ## SRCTYPE ## DbtbTrbnspbrfnt(XpbrLobd))) { \
            int rgb; \
            Convfrt ## SRCTYPE ## DbtbTo1IntRgb(XpbrLobd, rgb); \
            Storf ## DSTTYPE ## From1IntRgb(DSTPTR, DSTPREFIX, DXVAR, rgb); \
        } \
    } wiilf (0)

/*
 * Tiis is b "donvfrsion strbtfgy" for usf witi tif DEFINE_XPAR_BLITBG
 * mbdros.  It donvfrts from tif sourdf pixfl formbt to tif dfstinbtion
 * vib tif spfdififd intfrmfdibtf formbt wiilf substituting tif spfdififd
 * bgdolor for trbnspbrfnt pixfls.
 */
#dffinf BgCopyXpbrVib1IntRgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                             DSTPTR, DSTTYPE, DSTPREFIX, \
                             SXVAR, DXVAR, BGPIXEL, BGPREFIX) \
    do { \
        Dfdlbrf ## SRCTYPE ## Dbtb(XpbrLobd); \
        Lobd ## SRCTYPE ## Dbtb(SRCPTR, SRCPREFIX, SXVAR, XpbrLobd); \
        if (Is ## SRCTYPE ## DbtbTrbnspbrfnt(XpbrLobd)) { \
            Storf ## DSTTYPE ## PixflDbtb(DSTPTR, DXVAR, BGPIXEL, BGPREFIX); \
        } flsf { \
            int rgb; \
            Convfrt ## SRCTYPE ## DbtbTo1IntRgb(XpbrLobd, rgb); \
            Storf ## DSTTYPE ## From1IntRgb(DSTPTR, DSTPREFIX, DXVAR, rgb); \
        } \
    } wiilf (0)

/*
 * Tiis mbdro dftfrminfs wiftifr or not tif givfn pixfl is donsidfrfd
 * "trbnspbrfnt" for XOR purposfs.  Tif ARGB pixfl is donsidfrfd
 * "trbnspbrfnt" if tif blpib vbluf is < 0.5.
 */
#dffinf IsArgbTrbnspbrfnt(pixfl) \
    (((jint) pixfl) >= 0)

/*
 * Tiis is b "donvfrsion strbtfgy" for usf witi tif DEFINE_XOR_BLIT mbdro.  It
 * donvfrts tif sourdf pixfl to bn intfrmfdibtf ARGB vbluf bnd tifn donvfrts
 * tif ARGB vbluf to tif pixfl rfprfsfntbtion for tif dfstinbtion surfbdf.  It
 * tifn XORs tif srdpixfl, xorpixfl, bnd dfstinbtion pixfl togftifr bnd storfs
 * tif rfsult in tif dfstinbtion surfbdf.
 */
#dffinf XorVib1IntArgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                       DSTPTR, DSTTYPE, DSTANYTYPE, \
                       XVAR, XORPIXEL, XORPREFIX, \
                       MASK, MASKPREFIX, DSTINFOPTR) \
    do { \
        jint srdpixfl; \
        Dfdlbrf ## DSTANYTYPE ## PixflDbtb(pix) \
        Lobd ## SRCTYPE ## To1IntArgb(SRCPTR, SRCPREFIX, XVAR, srdpixfl); \
 \
        if (IsArgbTrbnspbrfnt(srdpixfl)) { \
            brfbk; \
        } \
 \
        DSTTYPE ## PixflFromArgb(srdpixfl, srdpixfl, DSTINFOPTR); \
 \
        Extrbdt ## DSTANYTYPE ## PixflDbtb(srdpixfl, pix); \
        Xor ## DSTANYTYPE ## PixflDbtb(srdpixfl, pix, DSTPTR, XVAR, \
                                       XORPIXEL, XORPREFIX, \
                                       MASK, MASKPREFIX); \
    } wiilf (0)

/*
 * "LUT_STRATEGY" mbdro sfts.
 *
 * Tifrf brf 2 mbjor strbtfgifs for dfbling witi luts bnd 3
 * implfmfntbtions of tiosf strbtfgifs.
 *
 * Tif 2 strbtfgifs brf "PrfProdfssLut" bnd "ConvfrtOnTifFly".
 *
 * For tif "PrfProdfssLut" strbtfgy, tif rbw ARGB lut supplifd
 * by tif SD_LOCK_LUT flbg is donvfrtfd bt tif bfginning into b
 * form tibt is morf suitfd for storing into tif dfstinbtion
 * pixfl formbt.  Tif innfr loop donsists of b sfrifs of tbblf
 * lookups witi vfry littlf donvfrsion from tibt intfrmfdibtf
 * pixfl formbt.
 *
 * For tif "ConvfrtOnTifFly" strbtfgy, tif rbw ARGB vblufs brf
 * donvfrtfd on b pixfl by pixfl bbsis in tif innfr loop itsflf.
 * Tiis strbtfgy is most usfful for formbts wiidi tfnd to usf
 * tif ARGB dolor formbt bs tifir pixfl formbt blso.
 *
 * Ebdi of tifsf strbtfgifs ibs 3 implfmfntbtions wiidi brf nffdfd
 * for tif spfdibl dbsfs:
 * - strbigit donvfrsion (invokfd from DEFINE_CONVERT_BLIT_LUT)
 * - strbigit donvfrsion witi trbnspbrfndy ibndling (invokfd from
 *   DEFINE_XPAR_CONVERT_BLIT_LUT)
 * - strbigit donvfrsion witi b bgdolor for tif trbnspbrfnt pixfls
 *   (invokfd from DEFINE_XPAR_BLITBG_LUT)
 */

/***
 * Stbrt of PrfProdfssLut strbtfgy mbdros, CONVERT_BLIT implfmfntbtion.
 */
#dffinf LutSizf(TYPE) \
    (1 << TYPE ## BitsPfrPixfl)

#dffinf DfdlbrfPrfProdfssLutLut(SRC, DST, PIXLUT) \
    DST ## PixflTypf PIXLUT[LutSizf(SRC)];

#dffinf SftupPrfProdfssLutLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO) \
    do { \
        jint *srdLut = (SRCINFO)->lutBbsf; \
        juint lutSizf = (SRCINFO)->lutSizf; \
        Dfdlbrf ## DST ## StorfVbrs(PrfLut) \
        Init ## DST ## StorfVbrsY(PrfLut, DSTINFO); \
        if (lutSizf >= LutSizf(SRC)) { \
            lutSizf = LutSizf(SRC); \
        } flsf { \
            DST ## PixflTypf *pPIXLUT = &PIXLUT[lutSizf]; \
            do { \
                Storf ## DST ## From1IntArgb(pPIXLUT, PrfLut, 0, 0); \
            } wiilf (++pPIXLUT < &PIXLUT[LutSizf(SRC)]); \
        } \
        BlitLoopXRfl(DST, DSTINFO, PrfLut, x, lutSizf, \
                     do { \
                         jint brgb = srdLut[x]; \
                         Storf ## DST ## From1IntArgb(PIXLUT, PrfLut, x, brgb); \
                     } wiilf (0)); \
    } wiilf (0)

#dffinf BodyPrfProdfssLutLut(SRCPTR, SRCTYPE, PIXLUT, \
                             DSTPTR, DSTTYPE, DSTPREFIX, \
                             SXVAR, DXVAR) \
    DSTPTR[DXVAR] = PIXLUT[SRCPTR[SXVAR]]

/*
 * End of PrfProdfssLut/CONVERT_BLIT mbdros.
 ***/

/***
 * Stbrt of ConvfrtOnTifFly strbtfgy mbdros, CONVERT_BLIT implfmfntbtion.
 */
#dffinf DfdlbrfConvfrtOnTifFlyLut(SRC, DST, PIXLUT) \
    Dfdlbrf ## SRC ## LobdVbrs(PIXLUT)

#dffinf SftupConvfrtOnTifFlyLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO) \
    Init ## SRC ## LobdVbrs(PIXLUT, SRCINFO)

#dffinf BodyConvfrtOnTifFlyLut(SRCPTR, SRCTYPE, PIXLUT, \
                               DSTPTR, DSTTYPE, DSTPREFIX, \
                               SXVAR, DXVAR) \
    ConvfrtVib1IntArgb(SRCPTR, SRCTYPE, PIXLUT, \
                       DSTPTR, DSTTYPE, DSTPREFIX, \
                       SXVAR, DXVAR)

/*
 * End of ConvfrtOnTifFly/CONVERT_BLIT mbdros.
 ***/

/***
 * Stbrt of PrfProdfssLut strbtfgy mbdros, XPAR_CONVERT_BLIT implfmfntbtion.
 */
#dffinf DfdlbrfPrfProdfssLutXpbrLut(SRC, DST, PIXLUT) \
    jint PIXLUT[LutSizf(SRC)];

#dffinf SftupPrfProdfssLutXpbrLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO) \
    do { \
        jint *srdLut = (SRCINFO)->lutBbsf; \
        juint lutSizf = (SRCINFO)->lutSizf; \
        Dfdlbrf ## DST ## StorfVbrs(PrfLut) \
        Init ## DST ## StorfVbrsY(PrfLut, DSTINFO); \
        if (lutSizf >= LutSizf(SRC)) { \
            lutSizf = LutSizf(SRC); \
        } flsf { \
            jint *pPIXLUT = &PIXLUT[lutSizf]; \
            do { \
                pPIXLUT[0] = DST ## XpbrLutEntry; \
            } wiilf (++pPIXLUT < &PIXLUT[LutSizf(SRC)]); \
        } \
        BlitLoopXRfl(DST, DSTINFO, PrfLut, x, lutSizf, \
                     do { \
                         jint brgb = srdLut[x]; \
                         if (brgb < 0) { \
                             Storf ## DST ## NonXpbrFromArgb \
                                 (PIXLUT, PrfLut, x, brgb); \
                         } flsf { \
                             PIXLUT[x] = DST ## XpbrLutEntry; \
                         } \
                     } wiilf (0)); \
    } wiilf (0)

#dffinf BodyPrfProdfssLutXpbrLut(SRCPTR, SRCTYPE, PIXLUT, \
                                 DSTPTR, DSTTYPE, DSTPREFIX, \
                                 SXVAR, DXVAR) \
    do { \
        jint pix = PIXLUT[SRCPTR[SXVAR]]; \
        if (! DSTTYPE ## IsXpbrLutEntry(pix)) { \
            DSTPTR[DXVAR] = (DSTTYPE ## PixflTypf) pix; \
        } \
    } wiilf (0)

/*
 * End of PrfProdfssLut/XPAR_CONVERT_BLIT mbdros.
 ***/

/***
 * Stbrt of ConvfrtOnTifFly strbtfgy mbdros, CONVERT_BLIT implfmfntbtion.
 */
#dffinf DfdlbrfConvfrtOnTifFlyXpbrLut(SRC, DST, PIXLUT) \
    Dfdlbrf ## SRC ## LobdVbrs(PIXLUT)

#dffinf SftupConvfrtOnTifFlyXpbrLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO) \
    Init ## SRC ## LobdVbrs(PIXLUT, SRCINFO)

#dffinf BodyConvfrtOnTifFlyXpbrLut(SRCPTR, SRCTYPE, PIXLUT, \
                                   DSTPTR, DSTTYPE, DSTPREFIX, \
                                   SXVAR, DXVAR) \
    do { \
        jint brgb; \
        Lobd ## SRCTYPE ## To1IntArgb(SRCPTR, PIXLUT, SXVAR, brgb); \
        if (brgb < 0) { \
            Storf ## DSTTYPE ## From1IntArgb(DSTPTR, DSTPREFIX, DXVAR, brgb); \
        } \
    } wiilf (0)

/*
 * End of ConvfrtOnTifFly/CONVERT_BLIT mbdros.
 ***/

/***
 * Stbrt of PrfProdfssLut strbtfgy mbdros, BLITBG implfmfntbtion.
 */
#dffinf DfdlbrfPrfProdfssLutBgLut(SRC, DST, PIXLUT) \
    jint PIXLUT[LutSizf(SRC)];

#dffinf SftupPrfProdfssLutBgLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO, BGPIXEL) \
    do { \
        jint *srdLut = (SRCINFO)->lutBbsf; \
        juint lutSizf = (SRCINFO)->lutSizf; \
        Dfdlbrf ## DST ## StorfVbrs(PrfLut) \
        Init ## DST ## StorfVbrsY(PrfLut, DSTINFO); \
        if (lutSizf >= LutSizf(SRC)) { \
            lutSizf = LutSizf(SRC); \
        } flsf { \
            jint *pPIXLUT = &PIXLUT[lutSizf]; \
            do { \
                pPIXLUT[0] = BGPIXEL; \
            } wiilf (++pPIXLUT < &PIXLUT[LutSizf(SRC)]); \
        } \
        BlitLoopXRfl(DST, DSTINFO, PrfLut, x, lutSizf, \
                     do { \
                         jint brgb = srdLut[x]; \
                         if (brgb < 0) { \
                             Storf ## DST ## From1IntArgb(PIXLUT, PrfLut, \
                                                          x, brgb); \
                         } flsf { \
                             PIXLUT[x] = BGPIXEL; \
                         } \
                     } wiilf (0)); \
    } wiilf (0)

#dffinf BodyPrfProdfssLutBgLut(SRCPTR, SRCTYPE, PIXLUT, \
                               DSTPTR, DSTTYPE, DSTPREFIX, \
                               SXVAR, DXVAR, BGPIXEL) \
    do { \
        jint pix = PIXLUT[SRCPTR[SXVAR]]; \
        Storf ## DSTTYPE ## Pixfl(DSTPTR, DXVAR, pix); \
    } wiilf (0)

/*
 * End of PrfProdfssLut/BLITBG implfmfntbtion.
 ***/

/***
 * Stbrt of ConvfrtOnTifFly strbtfgy mbdros, BLITBG implfmfntbtion.
 */
#dffinf DfdlbrfConvfrtOnTifFlyBgLut(SRC, DST, PIXLUT) \
    Dfdlbrf ## SRC ## LobdVbrs(PIXLUT) \
    Dfdlbrf ## DST ## PixflDbtb(bgpix);

#dffinf SftupConvfrtOnTifFlyBgLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO, BGPIXEL) \
    do { \
        Init ## SRC ## LobdVbrs(PIXLUT, SRCINFO); \
        Extrbdt ## DST ## PixflDbtb(BGPIXEL, bgpix); \
    } wiilf (0)

#dffinf BodyConvfrtOnTifFlyBgLut(SRCPTR, SRCTYPE, PIXLUT, \
                                 DSTPTR, DSTTYPE, DSTPREFIX, \
                                 SXVAR, DXVAR, BGPIXEL) \
    do { \
        jint brgb; \
        Lobd ## SRCTYPE ## To1IntArgb(SRCPTR, PIXLUT, SXVAR, brgb); \
        if (brgb < 0) { \
            Storf ## DSTTYPE ## From1IntArgb(DSTPTR, DSTPREFIX, DXVAR, brgb); \
        } flsf { \
            Storf ## DSTTYPE ## PixflDbtb(DSTPTR, DXVAR, BGPIXEL, bgpix); \
        } \
    } wiilf (0)

/*
 * End of ConvfrtOnTifFly/BLITBG mbdros.
 ***/

/*
 * Tifsf mbdros providf donsistfnt nbming donvfntions for tif
 * vbrious typfs of nbtivf primitivf innfr loop fundtions.
 * Tif nbmfs brf mfdibnidblly donstrudtfd from tif SurfbdfTypf nbmfs.
 */
#dffinf NAME_CONVERT_BLIT(SRC, DST)      SRC ## To ## DST ## Convfrt

#dffinf NAME_SCALE_BLIT(SRC, DST)        SRC ## To ## DST ## SdblfConvfrt

#dffinf NAME_XPAR_CONVERT_BLIT(SRC, DST) SRC ## To ## DST ## XpbrOvfr

#dffinf NAME_XPAR_SCALE_BLIT(SRC, DST)   SRC ## To ## DST ## SdblfXpbrOvfr

#dffinf NAME_XPAR_BLITBG(SRC, DST)       SRC ## To ## DST ## XpbrBgCopy

#dffinf NAME_XOR_BLIT(SRC, DST)          SRC ## To ## DST ## XorBlit

#dffinf NAME_ISOCOPY_BLIT(ANYTYPE)       ANYTYPE ## IsomorpiidCopy

#dffinf NAME_ISOSCALE_BLIT(ANYTYPE)      ANYTYPE ## IsomorpiidSdblfCopy

#dffinf NAME_ISOXOR_BLIT(ANYTYPE)        ANYTYPE ## IsomorpiidXorCopy

#dffinf NAME_SOLID_FILLRECT(TYPE)        TYPE ## SftRfdt

#dffinf NAME_SOLID_FILLSPANS(TYPE)       TYPE ## SftSpbns

#dffinf NAME_SOLID_DRAWLINE(TYPE)        TYPE ## SftLinf

#dffinf NAME_XOR_FILLRECT(TYPE)          TYPE ## XorRfdt

#dffinf NAME_XOR_FILLSPANS(TYPE)         TYPE ## XorSpbns

#dffinf NAME_XOR_DRAWLINE(TYPE)          TYPE ## XorLinf

#dffinf NAME_SRC_MASKFILL(TYPE)          TYPE ## SrdMbskFill

#dffinf NAME_SRCOVER_MASKFILL(TYPE)      TYPE ## SrdOvfrMbskFill

#dffinf NAME_ALPHA_MASKFILL(TYPE)        TYPE ## AlpibMbskFill

#dffinf NAME_SRCOVER_MASKBLIT(SRC, DST)  SRC ## To ## DST ## SrdOvfrMbskBlit

#dffinf NAME_ALPHA_MASKBLIT(SRC, DST)    SRC ## To ## DST ## AlpibMbskBlit

#dffinf NAME_SOLID_DRAWGLYPHLIST(TYPE)   TYPE ## DrbwGlypiList

#dffinf NAME_SOLID_DRAWGLYPHLISTAA(TYPE) TYPE ## DrbwGlypiListAA

#dffinf NAME_SOLID_DRAWGLYPHLISTLCD(TYPE) TYPE ## DrbwGlypiListLCD

#dffinf NAME_XOR_DRAWGLYPHLIST(TYPE)     TYPE ## DrbwGlypiListXor

#dffinf NAME_TRANSFORMHELPER(TYPE, MODE) TYPE ## MODE ## TrbnsformHflpfr

#dffinf NAME_TRANSFORMHELPER_NN(TYPE)    NAME_TRANSFORMHELPER(TYPE, NrstNbr)
#dffinf NAME_TRANSFORMHELPER_BL(TYPE)    NAME_TRANSFORMHELPER(TYPE, Bilinfbr)
#dffinf NAME_TRANSFORMHELPER_BC(TYPE)    NAME_TRANSFORMHELPER(TYPE, Bidubid)

#dffinf NAME_TRANSFORMHELPER_FUNCS(TYPE) TYPE ## TrbnsformHflpfrFunds

#dffinf NAME_SOLID_FILLPGRAM(TYPE)       TYPE ## SftPbrbllflogrbm
#dffinf NAME_SOLID_PGRAM_FUNCS(TYPE)     TYPE ## SftPbrbllflogrbmFunds

#dffinf NAME_XOR_FILLPGRAM(TYPE)         TYPE ## XorPbrbllflogrbm
#dffinf NAME_XOR_PGRAM_FUNCS(TYPE)       TYPE ## XorPbrbllflogrbmFunds

/*
 * Tifsf mbdros donvfnifntly nbmf bnd dfdlbrf tif indidbtfd nbtivf
 * primitivf loop fundtion for forwbrd rfffrfnding.
 */
#dffinf DECLARE_CONVERT_BLIT(SRC, DST) \
    BlitFund NAME_CONVERT_BLIT(SRC, DST)

#dffinf DECLARE_SCALE_BLIT(SRC, DST) \
    SdblfBlitFund NAME_SCALE_BLIT(SRC, DST)

#dffinf DECLARE_XPAR_CONVERT_BLIT(SRC, DST) \
    BlitFund NAME_XPAR_CONVERT_BLIT(SRC, DST)

#dffinf DECLARE_XPAR_SCALE_BLIT(SRC, DST) \
    SdblfBlitFund NAME_XPAR_SCALE_BLIT(SRC, DST)

#dffinf DECLARE_XPAR_BLITBG(SRC, DST) \
    BlitBgFund NAME_XPAR_BLITBG(SRC, DST)

#dffinf DECLARE_XOR_BLIT(SRC, DST) \
    BlitFund NAME_XOR_BLIT(SRC, DST)

#dffinf DECLARE_ISOCOPY_BLIT(ANYTYPE) \
    BlitFund NAME_ISOCOPY_BLIT(ANYTYPE)

#dffinf DECLARE_ISOSCALE_BLIT(ANYTYPE) \
    SdblfBlitFund NAME_ISOSCALE_BLIT(ANYTYPE)

#dffinf DECLARE_ISOXOR_BLIT(ANYTYPE) \
    BlitFund NAME_ISOXOR_BLIT(ANYTYPE)

#dffinf DECLARE_SOLID_FILLRECT(TYPE) \
    FillRfdtFund NAME_SOLID_FILLRECT(TYPE)

#dffinf DECLARE_SOLID_FILLSPANS(TYPE) \
    FillSpbnsFund NAME_SOLID_FILLSPANS(TYPE)

#dffinf DECLARE_SOLID_DRAWLINE(TYPE) \
    DrbwLinfFund NAME_SOLID_DRAWLINE(TYPE)

#dffinf DECLARE_XOR_FILLRECT(TYPE) \
    FillRfdtFund NAME_XOR_FILLRECT(TYPE)

#dffinf DECLARE_XOR_FILLSPANS(TYPE) \
    FillSpbnsFund NAME_XOR_FILLSPANS(TYPE)

#dffinf DECLARE_XOR_DRAWLINE(TYPE) \
    DrbwLinfFund NAME_XOR_DRAWLINE(TYPE)

#dffinf DECLARE_ALPHA_MASKFILL(TYPE) \
    MbskFillFund NAME_ALPHA_MASKFILL(TYPE)

#dffinf DECLARE_SRC_MASKFILL(TYPE) \
    MbskFillFund NAME_SRC_MASKFILL(TYPE)

#dffinf DECLARE_SRCOVER_MASKFILL(TYPE) \
    MbskFillFund NAME_SRCOVER_MASKFILL(TYPE)

#dffinf DECLARE_SRCOVER_MASKBLIT(SRC, DST) \
    MbskBlitFund NAME_SRCOVER_MASKBLIT(SRC, DST)

#dffinf DECLARE_ALPHA_MASKBLIT(SRC, DST) \
    MbskBlitFund NAME_ALPHA_MASKBLIT(SRC, DST)

#dffinf DECLARE_SOLID_DRAWGLYPHLIST(TYPE) \
    DrbwGlypiListFund NAME_SOLID_DRAWGLYPHLIST(TYPE)

#dffinf DECLARE_SOLID_DRAWGLYPHLISTAA(TYPE) \
    DrbwGlypiListAAFund NAME_SOLID_DRAWGLYPHLISTAA(TYPE)

#dffinf DECLARE_SOLID_DRAWGLYPHLISTLCD(TYPE) \
    DrbwGlypiListLCDFund NAME_SOLID_DRAWGLYPHLISTLCD(TYPE)

#dffinf DECLARE_XOR_DRAWGLYPHLIST(TYPE) \
    DrbwGlypiListFund NAME_XOR_DRAWGLYPHLIST(TYPE)

#dffinf DECLARE_TRANSFORMHELPER_FUNCS(TYPE) \
    TrbnsformHflpfrFund NAME_TRANSFORMHELPER_NN(TYPE); \
    TrbnsformHflpfrFund NAME_TRANSFORMHELPER_BL(TYPE); \
    TrbnsformHflpfrFund NAME_TRANSFORMHELPER_BC(TYPE); \
    TrbnsformHflpfrFunds NAME_TRANSFORMHELPER_FUNCS(TYPE)

#dffinf DECLARE_SOLID_PARALLELOGRAM(TYPE) \
    FillPbrbllflogrbmFund NAME_SOLID_FILLPGRAM(TYPE); \
    DECLARE_SOLID_DRAWLINE(TYPE); \
    DrbwPbrbllflogrbmFunds NAME_SOLID_PGRAM_FUNCS(TYPE)

#dffinf DECLARE_XOR_PARALLELOGRAM(TYPE) \
    FillPbrbllflogrbmFund NAME_XOR_FILLPGRAM(TYPE); \
    DECLARE_XOR_DRAWLINE(TYPE); \
    DrbwPbrbllflogrbmFunds NAME_XOR_PGRAM_FUNCS(TYPE)

/*
 * Tifsf mbdros donstrudt tif nfdfssbry NbtivfPrimitivf strudturf
 * for tif indidbtfd nbtivf primitivf loop fundtion wiidi will bf
 * dfdlbrfd somfwifrf prior bnd dffinfd flsfwifrf (usublly bftfr).
 */
#dffinf REGISTER_CONVERT_BLIT(SRC, DST) \
    REGISTER_BLIT(SRC, SrdNoEb, DST, NAME_CONVERT_BLIT(SRC, DST))

#dffinf REGISTER_CONVERT_BLIT_FLAGS(SRC, DST, SFLAGS, DFLAGS) \
    REGISTER_BLIT_FLAGS(SRC, SrdNoEb, DST, NAME_CONVERT_BLIT(SRC, DST), \
                        SFLAGS, DFLAGS)

#dffinf REGISTER_CONVERT_BLIT_EQUIV(SRC, DST, FUNC) \
    REGISTER_BLIT(SRC, SrdNoEb, DST, FUNC)

#dffinf REGISTER_SCALE_BLIT(SRC, DST) \
    REGISTER_SCALEBLIT(SRC, SrdNoEb, DST, NAME_SCALE_BLIT(SRC, DST))

#dffinf REGISTER_SCALE_BLIT_FLAGS(SRC, DST, SFLAGS, DFLAGS) \
    REGISTER_SCALEBLIT_FLAGS(SRC, SrdNoEb, DST, NAME_SCALE_BLIT(SRC, DST), \
                             SFLAGS, DFLAGS)

#dffinf REGISTER_SCALE_BLIT_EQUIV(SRC, DST, FUNC) \
    REGISTER_SCALEBLIT(SRC, SrdNoEb, DST, FUNC)

#dffinf REGISTER_XPAR_CONVERT_BLIT(SRC, DST) \
    REGISTER_BLIT(SRC, SrdOvfrBmNoEb, DST, NAME_XPAR_CONVERT_BLIT(SRC, DST))

#dffinf REGISTER_XPAR_CONVERT_BLIT_EQUIV(SRC, DST, FUNC) \
    REGISTER_BLIT(SRC, SrdOvfrBmNoEb, DST, FUNC)

#dffinf REGISTER_XPAR_SCALE_BLIT(SRC, DST) \
    REGISTER_SCALEBLIT(SRC, SrdOvfrBmNoEb, DST, NAME_XPAR_SCALE_BLIT(SRC, DST))

#dffinf REGISTER_XPAR_SCALE_BLIT_EQUIV(SRC, DST, FUNC) \
    REGISTER_SCALEBLIT(SRC, SrdOvfrBmNoEb, DST, FUNC)

#dffinf REGISTER_XPAR_BLITBG(SRC, DST) \
    REGISTER_BLITBG(SRC, SrdNoEb, DST, NAME_XPAR_BLITBG(SRC, DST))

#dffinf REGISTER_XPAR_BLITBG_EQUIV(SRC, DST, FUNC) \
    REGISTER_BLITBG(SRC, SrdNoEb, DST, FUNC)

#dffinf REGISTER_XOR_BLIT(SRC, DST) \
    REGISTER_BLIT(SRC, Xor, DST, NAME_XOR_BLIT(SRC, DST))

#dffinf REGISTER_ISOCOPY_BLIT(THISTYPE, ANYTYPE) \
    REGISTER_BLIT(THISTYPE, SrdNoEb, THISTYPE, NAME_ISOCOPY_BLIT(ANYTYPE))

#dffinf REGISTER_ISOSCALE_BLIT(THISTYPE, ANYTYPE) \
    REGISTER_SCALEBLIT(THISTYPE, SrdNoEb, THISTYPE, NAME_ISOSCALE_BLIT(ANYTYPE))

#dffinf REGISTER_ISOXOR_BLIT(THISTYPE, ANYTYPE) \
    REGISTER_BLIT(THISTYPE, Xor, THISTYPE, NAME_ISOXOR_BLIT(ANYTYPE))

#dffinf REGISTER_SOLID_FILLRECT(TYPE) \
    REGISTER_FILLRECT(AnyColor, SrdNoEb, TYPE, NAME_SOLID_FILLRECT(TYPE))

#dffinf REGISTER_SOLID_FILLSPANS(TYPE) \
    REGISTER_FILLSPANS(AnyColor, SrdNoEb, TYPE, NAME_SOLID_FILLSPANS(TYPE))

#dffinf REGISTER_SOLID_LINE_PRIMITIVES(TYPE) \
    REGISTER_LINE_PRIMITIVES(AnyColor, SrdNoEb, TYPE, \
                             NAME_SOLID_DRAWLINE(TYPE))

#dffinf REGISTER_XOR_FILLRECT(TYPE) \
    REGISTER_FILLRECT(AnyColor, Xor, TYPE, NAME_XOR_FILLRECT(TYPE))

#dffinf REGISTER_XOR_FILLSPANS(TYPE) \
    REGISTER_FILLSPANS(AnyColor, Xor, TYPE, NAME_XOR_FILLSPANS(TYPE))

#dffinf REGISTER_XOR_LINE_PRIMITIVES(TYPE) \
    REGISTER_LINE_PRIMITIVES(AnyColor, Xor, TYPE, NAME_XOR_DRAWLINE(TYPE))

#dffinf REGISTER_ALPHA_MASKFILL(TYPE) \
    REGISTER_MASKFILL(AnyColor, AnyAlpib, TYPE, NAME_ALPHA_MASKFILL(TYPE))

#dffinf REGISTER_SRC_MASKFILL(TYPE) \
    REGISTER_MASKFILL(AnyColor, Srd, TYPE, NAME_SRC_MASKFILL(TYPE))

#dffinf REGISTER_SRCOVER_MASKFILL(TYPE) \
    REGISTER_MASKFILL(AnyColor, SrdOvfr, TYPE, NAME_SRCOVER_MASKFILL(TYPE))

#dffinf REGISTER_SRCOVER_MASKBLIT(SRC, DST) \
    REGISTER_MASKBLIT(SRC, SrdOvfr, DST, NAME_SRCOVER_MASKBLIT(SRC, DST))

#dffinf REGISTER_ALPHA_MASKBLIT(SRC, DST) \
    REGISTER_MASKBLIT(SRC, AnyAlpib, DST, NAME_ALPHA_MASKBLIT(SRC, DST))

#dffinf REGISTER_SOLID_DRAWGLYPHLIST(TYPE) \
    REGISTER_DRAWGLYPHLIST(AnyColor, SrdNoEb, TYPE, \
                           NAME_SOLID_DRAWGLYPHLIST(TYPE))

#dffinf REGISTER_SOLID_DRAWGLYPHLISTAA(TYPE) \
    REGISTER_DRAWGLYPHLISTAA(AnyColor, SrdNoEb, TYPE, \
                             NAME_SOLID_DRAWGLYPHLISTAA(TYPE))

#dffinf REGISTER_SOLID_DRAWGLYPHLISTLCD(TYPE) \
    REGISTER_DRAWGLYPHLISTLCD(AnyColor, SrdNoEb, TYPE, \
                             NAME_SOLID_DRAWGLYPHLISTLCD(TYPE))

#dffinf REGISTER_XOR_DRAWGLYPHLIST(TYPE) \
    REGISTER_DRAWGLYPHLIST(AnyColor, Xor, TYPE, \
                           NAME_XOR_DRAWGLYPHLIST(TYPE)), \
    REGISTER_DRAWGLYPHLISTAA(AnyColor, Xor, TYPE, \
                             NAME_XOR_DRAWGLYPHLIST(TYPE))

#dffinf REGISTER_TRANSFORMHELPER_FUNCS(TYPE) \
    REGISTER_PRIMITIVE(TrbnsformHflpfr, TYPE, SrdNoEb, IntArgbPrf, \
                       (AnyFund *) &NAME_TRANSFORMHELPER_FUNCS(TYPE))

#dffinf REGISTER_SOLID_PARALLELOGRAM(TYPE) \
    REGISTER_PRIMITIVE(FillPbrbllflogrbm, AnyColor, SrdNoEb, TYPE, \
                       NAME_SOLID_FILLPGRAM(TYPE)), \
    REGISTER_PRIMITIVE(DrbwPbrbllflogrbm, AnyColor, SrdNoEb, TYPE, \
                       (AnyFund *) &NAME_SOLID_PGRAM_FUNCS(TYPE))

#dffinf REGISTER_XOR_PARALLELOGRAM(TYPE) \
    REGISTER_PRIMITIVE(FillPbrbllflogrbm, AnyColor, Xor, TYPE, \
                       NAME_XOR_FILLPGRAM(TYPE)), \
    REGISTER_PRIMITIVE(DrbwPbrbllflogrbm, AnyColor, Xor, TYPE, \
                       (AnyFund *) &NAME_XOR_PGRAM_FUNCS(TYPE))

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b Blit innfr loop
 * for dopying pixfls of b dommon typf from onf bufffr to bnotifr.
 */
#dffinf DEFINE_ISOCOPY_BLIT(ANYTYPE) \
void NAME_ISOCOPY_BLIT(ANYTYPE)(void *srdBbsf, void *dstBbsf, \
                                juint widti, juint ifigit, \
                                SurfbdfDbtbRbsInfo *pSrdInfo, \
                                SurfbdfDbtbRbsInfo *pDstInfo, \
                                NbtivfPrimitivf *pPrim, \
                                CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## ANYTYPE ## StorfVbrs(DstWritf) \
    BlitLoopHfigit(ANYTYPE, pSrd, srdBbsf, pSrdInfo, \
                   ANYTYPE, pDst, dstBbsf, pDstInfo, DstWritf, \
                   ifigit, \
                   mfmdpy(pDst, pSrd, widti * ANYTYPE ## PixflStridf)); \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b SdblfBlit innfr loop
 * for sdbling pixfls of b dommon typf from onf bufffr to bnotifr.
 */
#dffinf DEFINE_ISOSCALE_BLIT(ANYTYPE) \
void NAME_ISOSCALE_BLIT(ANYTYPE)(void *srdBbsf, void *dstBbsf, \
                                 juint widti, juint ifigit, \
                                 jint sxlod, jint sylod, \
                                 jint sxind, jint syind, jint siift, \
                                 SurfbdfDbtbRbsInfo *pSrdInfo, \
                                 SurfbdfDbtbRbsInfo *pDstInfo, \
                                 NbtivfPrimitivf *pPrim, \
                                 CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## ANYTYPE ## StorfVbrs(DstWritf) \
    BlitLoopSdblfWidtiHfigit(ANYTYPE, pSrd, srdBbsf, pSrdInfo, \
                             ANYTYPE, pDst, dstBbsf, pDstInfo, DstWritf, \
                             x, widti, ifigit, \
                             sxlod, sylod, sxind, syind, siift, \
                             Copy ## ANYTYPE ## PixflDbtb(pSrd, x, pDst, 0)); \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b Blit innfr loop
 * for XORing pixfls of b dommon typf from onf bufffr into bnotifr.
 */
#dffinf DEFINE_ISOXOR_BLIT(ANYTYPE) \
void NAME_ISOXOR_BLIT(ANYTYPE)(void *srdBbsf, void *dstBbsf, \
                               juint widti, juint ifigit, \
                               SurfbdfDbtbRbsInfo *pSrdInfo, \
                               SurfbdfDbtbRbsInfo *pDstInfo, \
                               NbtivfPrimitivf *pPrim, \
                               CompositfInfo *pCompInfo) \
{ \
    jint xorpixfl = pCompInfo->dftbils.xorPixfl; \
    Dfdlbrf ## ANYTYPE ## PixflDbtb(xor) \
    Dfdlbrf ## ANYTYPE ## StorfVbrs(DstWritf) \
 \
    Extrbdt ## ANYTYPE ## PixflDbtb(xorpixfl, xor); \
 \
    BlitLoopWidtiHfigit(ANYTYPE, pSrd, srdBbsf, pSrdInfo, \
                        ANYTYPE, pDst, dstBbsf, pDstInfo, DstWritf, \
                        widti, ifigit, \
                        XorCopy ## ANYTYPE ## PixflDbtb(pSrd, pDst, 0, \
                                                        xorpixfl, xor)); \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b Blit innfr loop
 * for donvfrting pixfls from b bufffr of onf typf into b bufffr of
 * bnotifr typf.  No blfnding is donf of tif pixfls.
 */
#dffinf DEFINE_CONVERT_BLIT(SRC, DST, STRATEGY) \
void NAME_CONVERT_BLIT(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                                 juint widti, juint ifigit, \
                                 SurfbdfDbtbRbsInfo *pSrdInfo, \
                                 SurfbdfDbtbRbsInfo *pDstInfo, \
                                 NbtivfPrimitivf *pPrim, \
                                 CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## SRC ## LobdVbrs(SrdRfbd) \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
 \
    Init ## SRC ## LobdVbrs(SrdRfbd, pSrdInfo); \
    BlitLoopWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, \
                        DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                        widti, ifigit, \
                        ConvfrtVib ## STRATEGY(pSrd, SRC, SrdRfbd, \
                                               pDst, DST, DstWritf, \
                                               0, 0)); \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b Blit innfr loop
 * for donvfrting pixfls from b bufffr of bytf pixfls witi b lookup
 * tbblf into b bufffr of bnotifr typf.  No blfnding is donf of tif pixfls.
 */
#dffinf DEFINE_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY) \
void NAME_CONVERT_BLIT(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                                 juint widti, juint ifigit, \
                                 SurfbdfDbtbRbsInfo *pSrdInfo, \
                                 SurfbdfDbtbRbsInfo *pDstInfo, \
                                 NbtivfPrimitivf *pPrim, \
                                 CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
    Dfdlbrf ## LUT_STRATEGY ## Lut(SRC, DST, pixLut) \
 \
    Sftup ## LUT_STRATEGY ## Lut(SRC, DST, pixLut,\
                                 pSrdInfo, pDstInfo); \
    BlitLoopWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, \
                        DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                        widti, ifigit, \
                        Body ## LUT_STRATEGY ## Lut(pSrd, SRC, \
                                                    pixLut, \
                                                    pDst, DST, \
                                                    DstWritf, 0, 0));\
}
#dffinf DEFINE_CONVERT_BLIT_LUT8(SRC, DST, LUT_STRATEGY) \
    DEFINE_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY)

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b SdblfBlit innfr
 * loop for sdbling bnd donvfrting pixfls from b bufffr of onf typf into
 * b bufffr of bnotifr typf.  No blfnding is donf of tif pixfls.
 */
#dffinf DEFINE_SCALE_BLIT(SRC, DST, STRATEGY) \
void NAME_SCALE_BLIT(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                               juint widti, juint ifigit, \
                               jint sxlod, jint sylod, \
                               jint sxind, jint syind, jint siift, \
                               SurfbdfDbtbRbsInfo *pSrdInfo, \
                               SurfbdfDbtbRbsInfo *pDstInfo, \
                               NbtivfPrimitivf *pPrim, \
                               CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## SRC ## LobdVbrs(SrdRfbd) \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
 \
    Init ## SRC ## LobdVbrs(SrdRfbd, pSrdInfo); \
    BlitLoopSdblfWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, \
                             DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                             x, widti, ifigit, \
                             sxlod, sylod, sxind, syind, siift, \
                             ConvfrtVib ## STRATEGY(pSrd, SRC, SrdRfbd, \
                                                    pDst, DST, DstWritf, \
                                                    x, 0)); \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b SdblfBlit innfr
 * loop for sdbling bnd donvfrting pixfls from b bufffr of bytf pixfls
 * witi b lookup tbblf into b bufffr of bnotifr typf.  No blfnding is
 * donf of tif pixfls.
 */
#dffinf DEFINE_SCALE_BLIT_LUT(SRC, DST, LUT_STRATEGY) \
void NAME_SCALE_BLIT(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                               juint widti, juint ifigit, \
                               jint sxlod, jint sylod, \
                               jint sxind, jint syind, jint siift, \
                               SurfbdfDbtbRbsInfo *pSrdInfo, \
                               SurfbdfDbtbRbsInfo *pDstInfo, \
                               NbtivfPrimitivf *pPrim, \
                               CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
    Dfdlbrf ## LUT_STRATEGY ## Lut(SRC, DST, pixLut) \
 \
    Sftup ## LUT_STRATEGY ## Lut(SRC, DST, pixLut, pSrdInfo, pDstInfo); \
    BlitLoopSdblfWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, \
                             DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                             x, widti, ifigit, \
                             sxlod, sylod, sxind, syind, siift, \
                             Body ## LUT_STRATEGY ## Lut(pSrd, SRC, pixLut, \
                                                         pDst, DST, \
                                                         DstWritf, x, 0));\
}
#dffinf DEFINE_SCALE_BLIT_LUT8(SRC, DST, LUT_STRATEGY) \
    DEFINE_SCALE_BLIT_LUT(SRC, DST, LUT_STRATEGY)

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b Blit innfr loop
 * for drbwing opbquf pixfls from b bufffr of onf typf onto b bufffr of
 * bnotifr typf, ignoring tif trbnspbrfnt pixfls in tif sourdf bufffr.
 * No blfnding is donf of tif pixfls - tif donvfrtfd pixfl vbluf is
 * fitifr dopifd or tif dfstinbtion is lfft untoudifd.
 */
#dffinf DEFINE_XPAR_CONVERT_BLIT(SRC, DST, STRATEGY) \
void NAME_XPAR_CONVERT_BLIT(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                                      juint widti, juint ifigit, \
                                      SurfbdfDbtbRbsInfo *pSrdInfo, \
                                      SurfbdfDbtbRbsInfo *pDstInfo, \
                                      NbtivfPrimitivf *pPrim, \
                                      CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## SRC ## LobdVbrs(SrdRfbd) \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
 \
    Init ## SRC ## LobdVbrs(SrdRfbd, pSrdInfo); \
    BlitLoopWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, \
                        DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                        widti, ifigit, \
                        ConvfrtXpbrVib ## STRATEGY(pSrd, SRC, SrdRfbd, \
                                                   pDst, DST, DstWritf, \
                                                   0, 0)); \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b Blit innfr loop
 * for donvfrting pixfls from b bufffr of bytf pixfls witi b lookup
 * tbblf dontbining trbnspbrfnt pixfls into b bufffr of bnotifr typf.
 * No blfnding is donf of tif pixfls - tif donvfrtfd pixfl vbluf is
 * fitifr dopifd or tif dfstinbtion is lfft untoudifd.
 */
#dffinf DEFINE_XPAR_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY) \
void NAME_XPAR_CONVERT_BLIT(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                                      juint widti, juint ifigit, \
                                      SurfbdfDbtbRbsInfo *pSrdInfo, \
                                      SurfbdfDbtbRbsInfo *pDstInfo, \
                                      NbtivfPrimitivf *pPrim, \
                                      CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
    Dfdlbrf ## LUT_STRATEGY ## XpbrLut(SRC, DST, pixLut) \
 \
    Sftup ## LUT_STRATEGY ## XpbrLut(SRC, DST, pixLut, pSrdInfo, pDstInfo); \
    BlitLoopWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, \
                        DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                        widti, ifigit, \
                        Body ## LUT_STRATEGY ## XpbrLut(pSrd, SRC, pixLut, \
                                                        pDst, DST, \
                                                        DstWritf, 0, 0)); \
}
#dffinf DEFINE_XPAR_CONVERT_BLIT_LUT8(SRC, DST, LUT_STRATEGY) \
    DEFINE_XPAR_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY)

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b SdblfBlit innfr
 * loop for sdbling bnd donvfrting pixfls from b bufffr of bytf pixfls
 * witi b lookup tbblf dontbining trbnspbrfnt pixfls into b bufffr of
 * bnotifr typf.
 * No blfnding is donf of tif pixfls - tif donvfrtfd pixfl vbluf is
 * fitifr dopifd or tif dfstinbtion is lfft untoudifd.
 */
#dffinf DEFINE_XPAR_SCALE_BLIT_LUT(SRC, DST, LUT_STRATEGY) \
void NAME_XPAR_SCALE_BLIT(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                                    juint widti, juint ifigit, \
                                    jint sxlod, jint sylod, \
                                    jint sxind, jint syind, jint siift, \
                                    SurfbdfDbtbRbsInfo *pSrdInfo, \
                                    SurfbdfDbtbRbsInfo *pDstInfo, \
                                    NbtivfPrimitivf *pPrim, \
                                    CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
    Dfdlbrf ## LUT_STRATEGY ## XpbrLut(SRC, DST, pixLut) \
 \
    Sftup ## LUT_STRATEGY ## XpbrLut(SRC, DST, pixLut, pSrdInfo, pDstInfo); \
    BlitLoopSdblfWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, \
                             DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                             x, widti, ifigit, \
                             sxlod, sylod, sxind, syind, siift, \
                             Body ## LUT_STRATEGY ## XpbrLut(pSrd, SRC, pixLut, \
                                                             pDst, DST, \
                                                             DstWritf, \
                                                             x, 0)); \
}
#dffinf DEFINE_XPAR_SCALE_BLIT_LUT8(SRC, DST, LUT_STRATEGY) \
    DEFINE_XPAR_SCALE_BLIT_LUT(SRC, DST, LUT_STRATEGY)

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b SdblfBlit innfr
 * loop for sdbling bnd donvfrting pixfls from b bufffr of onf typf
 * dontbining trbnspbrfnt pixfls into b bufffr of bnotifr typf.
 *
 * No blfnding is donf of tif pixfls - tif donvfrtfd pixfl vbluf is
 * fitifr dopifd or tif dfstinbtion is lfft untoudifd.
 */
#dffinf DEFINE_XPAR_SCALE_BLIT(SRC, DST, STRATEGY) \
void NAME_XPAR_SCALE_BLIT(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                               juint widti, juint ifigit, \
                               jint sxlod, jint sylod, \
                               jint sxind, jint syind, jint siift, \
                               SurfbdfDbtbRbsInfo *pSrdInfo, \
                               SurfbdfDbtbRbsInfo *pDstInfo, \
                               NbtivfPrimitivf *pPrim, \
                               CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## SRC ## LobdVbrs(SrdRfbd) \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
 \
    Init ## SRC ## LobdVbrs(SrdRfbd, pSrdInfo); \
    BlitLoopSdblfWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, \
                             DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                             x, widti, ifigit, \
                             sxlod, sylod, sxind, syind, siift, \
                             ConvfrtXpbrVib ## STRATEGY(pSrd, SRC, SrdRfbd, \
                                                        pDst, DST, DstWritf, \
                                                        x, 0)); \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b BlitBg innfr loop
 * for donvfrting pixfls from b bufffr of onf typf dontbining trbnspbrfnt
 * pixfls into b bufffr of bnotifr typf witi b spfdififd bgdolor for tif
 * trbnspbrfnt pixfls.
 * No blfnding is donf of tif pixfls otifr tibn to substitutf tif
 * bgdolor for bny trbnspbrfnt pixfls.
 */
#dffinf DEFINE_XPAR_BLITBG(SRC, DST, STRATEGY) \
void NAME_XPAR_BLITBG(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                                juint widti, juint ifigit, \
                                jint bgpixfl, \
                                SurfbdfDbtbRbsInfo *pSrdInfo, \
                                SurfbdfDbtbRbsInfo *pDstInfo, \
                                NbtivfPrimitivf *pPrim, \
                                CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## SRC ## LobdVbrs(SrdRfbd) \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
    Dfdlbrf ## DST ## PixflDbtb(bgdbtb) \
 \
    Extrbdt ## DST ## PixflDbtb(bgpixfl, bgdbtb); \
    BlitLoopWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, \
                        DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                        widti, ifigit, \
                        BgCopyXpbrVib ## STRATEGY(pSrd, SRC, SrdRfbd, \
                                                  pDst, DST, DstWritf, \
                                                  0, 0, bgpixfl, bgdbtb)); \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b BlitBg innfr loop
 * for donvfrting pixfls from b bufffr of bytf pixfls witi b lookup
 * tbblf dontbining trbnspbrfnt pixfls into b bufffr of bnotifr typf
 * witi b spfdififd bgdolor for tif trbnspbrfnt pixfls.
 * No blfnding is donf of tif pixfls otifr tibn to substitutf tif
 * bgdolor for bny trbnspbrfnt pixfls.
 */
#dffinf DEFINE_XPAR_BLITBG_LUT(SRC, DST, LUT_STRATEGY) \
void NAME_XPAR_BLITBG(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                                juint widti, juint ifigit, \
                                jint bgpixfl, \
                                SurfbdfDbtbRbsInfo *pSrdInfo, \
                                SurfbdfDbtbRbsInfo *pDstInfo, \
                                NbtivfPrimitivf *pPrim, \
                                CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
    Dfdlbrf ## LUT_STRATEGY ## BgLut(SRC, DST, pixLut) \
 \
    Sftup ## LUT_STRATEGY ## BgLut(SRC, DST, pixLut, pSrdInfo, pDstInfo, \
                                   bgpixfl); \
    BlitLoopWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, \
                        DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                        widti, ifigit, \
                        Body ## LUT_STRATEGY ## BgLut(pSrd, SRC, pixLut, \
                                                      pDst, DST, \
                                                      DstWritf, 0, 0, \
                                                      bgpixfl)); \
}
#dffinf DEFINE_XPAR_BLITBG_LUT8(SRC, DST, LUT_STRATEGY) \
    DEFINE_XPAR_BLITBG_LUT(SRC, DST, LUT_STRATEGY)

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b Blit innfr loop
 * for donvfrting pixfls from b bufffr of onf typf into b bufffr of
 * bnotifr typf.  Ebdi sourdf pixfl is XORfd witi tif durrfnt XOR dolor vbluf.
 * Tibt rfsult is tifn XORfd witi tif dfstinbtion pixfl bnd tif finbl
 * rfsult is storfd in tif dfstinbtion surfbdf.
 */
#dffinf DEFINE_XOR_BLIT(SRC, DST, DSTANYTYPE) \
void NAME_XOR_BLIT(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                             juint widti, juint ifigit, \
                             SurfbdfDbtbRbsInfo *pSrdInfo, \
                             SurfbdfDbtbRbsInfo *pDstInfo, \
                             NbtivfPrimitivf *pPrim, \
                             CompositfInfo *pCompInfo) \
{ \
    jint xorpixfl = pCompInfo->dftbils.xorPixfl; \
    juint blpibmbsk = pCompInfo->blpibMbsk; \
    Dfdlbrf ## DSTANYTYPE ## PixflDbtb(xor) \
    Dfdlbrf ## DSTANYTYPE ## PixflDbtb(mbsk) \
    Dfdlbrf ## SRC ## LobdVbrs(SrdRfbd) \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
 \
    Extrbdt ## DSTANYTYPE ## PixflDbtb(xorpixfl, xor); \
    Extrbdt ## DSTANYTYPE ## PixflDbtb(blpibmbsk, mbsk); \
 \
    Init ## SRC ## LobdVbrs(SrdRfbd, pSrdInfo); \
    BlitLoopWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, \
                        DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                        widti, ifigit, \
                        XorVib1IntArgb(pSrd, SRC, SrdRfbd, \
                                       pDst, DST, DSTANYTYPE, \
                                       0, xorpixfl, xor, \
                                       blpibmbsk, mbsk, pDstInfo)); \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b FillRfdt innfr loop
 * for sftting b rfdtbngulbr rfgion of pixfls to b spfdifid pixfl vbluf.
 * No blfnding of tif fill dolor is donf witi tif pixfls.
 */
#dffinf DEFINE_SOLID_FILLRECT(DST) \
void NAME_SOLID_FILLRECT(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                              jint lox, jint loy, \
                              jint iix, jint iiy, \
                              jint pixfl, \
                              NbtivfPrimitivf *pPrim, \
                              CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## DST ## PixflDbtb(pix) \
    DST ## DbtbTypf *pPix; \
    jint sdbn = pRbsInfo->sdbnStridf; \
    juint ifigit = iiy - loy; \
    juint widti = iix - lox; \
 \
    pPix = PtrCoord(pRbsInfo->rbsBbsf, lox, DST ## PixflStridf, loy, sdbn); \
    Extrbdt ## DST ## PixflDbtb(pixfl, pix); \
    do { \
        juint x = 0; \
        do { \
            Storf ## DST ## PixflDbtb(pPix, x, pixfl, pix); \
        } wiilf (++x < widti); \
        pPix = PtrAddBytfs(pPix, sdbn); \
    } wiilf (--ifigit > 0); \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b FillSpbns innfr loop
 * for itfrbting tirougi b list of spbns bnd sftting tiosf rfgions of pixfls
 * to b spfdifid pixfl vbluf.  No blfnding of tif fill dolor is donf witi
 * tif pixfls.
 */
#dffinf DEFINE_SOLID_FILLSPANS(DST) \
void NAME_SOLID_FILLSPANS(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                               SpbnItfrbtorFunds *pSpbnFunds, void *siDbtb, \
                               jint pixfl, NbtivfPrimitivf *pPrim, \
                               CompositfInfo *pCompInfo) \
{ \
    void *pBbsf = pRbsInfo->rbsBbsf; \
    Dfdlbrf ## DST ## PixflDbtb(pix) \
    jint sdbn = pRbsInfo->sdbnStridf; \
    jint bbox[4]; \
 \
    Extrbdt ## DST ## PixflDbtb(pixfl, pix); \
    wiilf ((*pSpbnFunds->nfxtSpbn)(siDbtb, bbox)) { \
        jint x = bbox[0]; \
        jint y = bbox[1]; \
        juint w = bbox[2] - x; \
        juint i = bbox[3] - y; \
        DST ## DbtbTypf *pPix = PtrCoord(pBbsf, \
                                         x, DST ## PixflStridf, \
                                         y, sdbn); \
        do { \
            juint rflx; \
            for (rflx = 0; rflx < w; rflx++) { \
                Storf ## DST ## PixflDbtb(pPix, rflx, pixfl, pix); \
            } \
            pPix = PtrAddBytfs(pPix, sdbn); \
        } wiilf (--i > 0); \
    } \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b FillPbrbllflogrbm
 * innfr loop for trbding 2 dibgonbl fdgfs (lfft bnd rigit) bnd sftting
 * tiosf rfgions of pixfls bftwffn tifm to b spfdifid pixfl vbluf.
 * No blfnding of tif fill dolor is donf witi tif pixfls.
 */
#dffinf DEFINE_SOLID_FILLPGRAM(DST) \
void NAME_SOLID_FILLPGRAM(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                               jint lox, jint loy, jint iix, jint iiy, \
                               jlong lfftx, jlong dlfftx, \
                               jlong rigitx, jlong drigitx, \
                               jint pixfl, strudt _NbtivfPrimitivf *pPrim, \
                               CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## DST ## PixflDbtb(pix) \
    jint sdbn = pRbsInfo->sdbnStridf; \
    DST ## DbtbTypf *pPix = PtrCoord(pRbsInfo->rbsBbsf, 0, 0, loy, sdbn); \
 \
    Extrbdt ## DST ## PixflDbtb(pixfl, pix); \
    wiilf (loy < iiy) { \
        jint lx = WiolfOfLong(lfftx); \
        jint rx = WiolfOfLong(rigitx); \
        if (lx < lox) lx = lox; \
        if (rx > iix) rx = iix; \
        wiilf (lx < rx) { \
            Storf ## DST ## PixflDbtb(pPix, lx, pixfl, pix); \
            lx++; \
        } \
        pPix = PtrAddBytfs(pPix, sdbn); \
        lfftx += dlfftx; \
        rigitx += drigitx; \
        loy++; \
    } \
}

#dffinf DEFINE_SOLID_DRAWPARALLELOGRAM_FUNCS(DST) \
    DrbwPbrbllflogrbmFunds NAME_SOLID_PGRAM_FUNCS(DST) = { \
        NAME_SOLID_FILLPGRAM(DST), \
        NAME_SOLID_DRAWLINE(DST), \
    };

#dffinf DEFINE_SOLID_PARALLELOGRAM(DST) \
    DEFINE_SOLID_FILLPGRAM(DST) \
    DEFINE_SOLID_DRAWPARALLELOGRAM_FUNCS(DST)

/*
 * Tiis mbdro dfdlbrfs tif bumpmbjor bnd bumpminor vbribblfs usfd for tif
 * DrbwLinf fundtions.
 */
#dffinf DfdlbrfBumps(BUMPMAJOR, BUMPMINOR) \
    jint BUMPMAJOR, BUMPMINOR;

/*
 * Tiis mbdro fxtrbdts "instrudtions" from tif bumpmbjor bnd bumpminor mbsks
 * tibt dftfrminf tif initibl bumpmbjor bnd bumpminor vblufs.  Tif bumpmbjor
 * bnd bumpminor mbsks brf lbid out in tif following formbt:
 *
 * bumpmbjormbsk:                      bumpminormbsk:
 * bit0: bumpmbjor = pixflStridf       bit0: bumpminor = pixflStridf
 * bit1: bumpmbjor = -pixflStridf      bit1: bumpminor = -pixflStridf
 * bit2: bumpmbjor = sdbnStridf        bit2: bumpminor = sdbnStridf
 * bit3: bumpmbjor = -sdbnStridf       bit3: bumpminor = -sdbnStridf
 */
#dffinf InitBumps(BUMPMAJOR, BUMPMINOR, \
                  BUMPMAJORMASK, BUMPMINORMASK, \
                  PIXELSTRIDE, SCANSTRIDE) \
    BUMPMAJOR = (BUMPMAJORMASK & BUMP_POS_PIXEL) ? PIXELSTRIDE : \
                    (BUMPMAJORMASK & BUMP_NEG_PIXEL) ? -PIXELSTRIDE : \
                        (BUMPMAJORMASK & BUMP_POS_SCAN) ? SCANSTRIDE : \
                                                          -SCANSTRIDE; \
    BUMPMINOR = (BUMPMINORMASK & BUMP_POS_PIXEL) ? PIXELSTRIDE : \
                    (BUMPMINORMASK & BUMP_NEG_PIXEL) ? -PIXELSTRIDE : \
                        (BUMPMINORMASK & BUMP_POS_SCAN) ? SCANSTRIDE : \
                            (BUMPMINORMASK & BUMP_NEG_SCAN) ? -SCANSTRIDE : \
                                                              0; \
    BUMPMINOR += BUMPMAJOR;

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b DrbwLinf innfr loop
 * for itfrbting blong b iorizontbl or vfrtidbl linf bnd sftting tif pixfls
 * on tibt linf to b spfdifid pixfl vbluf.  No blfnding of tif fill dolor
 * is donf witi tif pixfls.
 */
#dffinf DEFINE_SOLID_DRAWLINE(DST) \
void NAME_SOLID_DRAWLINE(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                              jint x1, jint y1, jint pixfl, \
                              jint stfps, jint frror, \
                              jint bumpmbjormbsk, jint frrmbjor, \
                              jint bumpminormbsk, jint frrminor, \
                              NbtivfPrimitivf *pPrim, \
                              CompositfInfo *pCompInfo) \
{ \
    Dfdlbrf ## DST ## PixflDbtb(pix) \
    jint sdbn = pRbsInfo->sdbnStridf; \
    DST ## DbtbTypf *pPix = PtrCoord(pRbsInfo->rbsBbsf, \
                                     x1, DST ## PixflStridf, \
                                     y1, sdbn); \
    DfdlbrfBumps(bumpmbjor, bumpminor) \
 \
    InitBumps(bumpmbjor, bumpminor, bumpmbjormbsk, bumpminormbsk, \
              DST ## PixflStridf, sdbn); \
    Extrbdt ## DST ## PixflDbtb(pixfl, pix); \
    if (frrmbjor == 0) { \
        do { \
            Storf ## DST ## PixflDbtb(pPix, 0, pixfl, pix); \
            pPix = PtrAddBytfs(pPix, bumpmbjor); \
        } wiilf (--stfps > 0); \
    } flsf { \
        do { \
            Storf ## DST ## PixflDbtb(pPix, 0, pixfl, pix); \
            if (frror < 0) { \
                pPix = PtrAddBytfs(pPix, bumpmbjor); \
                frror += frrmbjor; \
            } flsf { \
                pPix = PtrAddBytfs(pPix, bumpminor); \
                frror -= frrminor; \
            } \
        } wiilf (--stfps > 0); \
    } \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b FillRfdt innfr loop
 * for sftting b rfdtbngulbr rfgion of pixfls to b spfdifid pixfl vbluf.
 * Ebdi dfstinbtion pixfl is XORfd witi tif durrfnt XOR modf dolor bs wfll bs
 * tif durrfnt fill dolor.
 */
#dffinf DEFINE_XOR_FILLRECT(DST) \
void NAME_XOR_FILLRECT(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                            jint lox, jint loy, \
                            jint iix, jint iiy, \
                            jint pixfl, \
                            NbtivfPrimitivf *pPrim, \
                            CompositfInfo *pCompInfo) \
{ \
    jint xorpixfl = pCompInfo->dftbils.xorPixfl; \
    juint blpibmbsk = pCompInfo->blpibMbsk; \
    Dfdlbrf ## DST ## PixflDbtb(xor) \
    Dfdlbrf ## DST ## PixflDbtb(pix) \
    Dfdlbrf ## DST ## PixflDbtb(mbsk) \
    DST ## DbtbTypf *pPix; \
    jint sdbn = pRbsInfo->sdbnStridf; \
    juint ifigit = iiy - loy; \
    juint widti = iix - lox; \
 \
    pPix = PtrCoord(pRbsInfo->rbsBbsf, lox, DST ## PixflStridf, loy, sdbn); \
    Extrbdt ## DST ## PixflDbtb(xorpixfl, xor); \
    Extrbdt ## DST ## PixflDbtb(pixfl, pix); \
    Extrbdt ## DST ## PixflDbtb(blpibmbsk, mbsk); \
 \
    do { \
        juint x = 0; \
        do { \
            Xor ## DST ## PixflDbtb(pixfl, pix, pPix, x, \
                                    xorpixfl, xor, blpibmbsk, mbsk); \
        } wiilf (++x < widti); \
        pPix = PtrAddBytfs(pPix, sdbn); \
    } wiilf (--ifigit > 0); \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b FillSpbns innfr loop
 * for itfrbting tirougi b list of spbns bnd sftting tiosf rfgions of pixfls
 * to b spfdifid pixfl vbluf.  Ebdi dfstinbtion pixfl is XORfd witi tif
 * durrfnt XOR modf dolor bs wfll bs tif durrfnt fill dolor.
 */
#dffinf DEFINE_XOR_FILLSPANS(DST) \
void NAME_XOR_FILLSPANS(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                             SpbnItfrbtorFunds *pSpbnFunds, \
                             void *siDbtb, jint pixfl, \
                             NbtivfPrimitivf *pPrim, \
                             CompositfInfo *pCompInfo) \
{ \
    void *pBbsf = pRbsInfo->rbsBbsf; \
    jint xorpixfl = pCompInfo->dftbils.xorPixfl; \
    juint blpibmbsk = pCompInfo->blpibMbsk; \
    Dfdlbrf ## DST ## PixflDbtb(xor) \
    Dfdlbrf ## DST ## PixflDbtb(pix) \
    Dfdlbrf ## DST ## PixflDbtb(mbsk) \
    jint sdbn = pRbsInfo->sdbnStridf; \
    jint bbox[4]; \
 \
    Extrbdt ## DST ## PixflDbtb(xorpixfl, xor); \
    Extrbdt ## DST ## PixflDbtb(pixfl, pix); \
    Extrbdt ## DST ## PixflDbtb(blpibmbsk, mbsk); \
 \
    wiilf ((*pSpbnFunds->nfxtSpbn)(siDbtb, bbox)) { \
        jint x = bbox[0]; \
        jint y = bbox[1]; \
        juint w = bbox[2] - x; \
        juint i = bbox[3] - y; \
        DST ## DbtbTypf *pPix = PtrCoord(pBbsf, \
                                         x, DST ## PixflStridf, \
                                         y, sdbn); \
        do { \
            juint rflx; \
            for (rflx = 0; rflx < w; rflx++) { \
                Xor ## DST ## PixflDbtb(pixfl, pix, pPix, rflx, \
                                        xorpixfl, xor, blpibmbsk, mbsk); \
            } \
            pPix = PtrAddBytfs(pPix, sdbn); \
        } wiilf (--i > 0); \
    } \
}

/*
 * Tiis mbdro dffinfs bn fntirf fundtion to implfmfnt b DrbwLinf innfr loop
 * for itfrbting blong b iorizontbl or vfrtidbl linf bnd sftting tif pixfls
 * on tibt linf to b spfdifid pixfl vbluf.  Ebdi dfstinbtion pixfl is XORfd
 * witi tif durrfnt XOR modf dolor bs wfll bs tif durrfnt drbw dolor.
 */
#dffinf DEFINE_XOR_DRAWLINE(DST) \
void NAME_XOR_DRAWLINE(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                            jint x1, jint y1, jint pixfl, \
                            jint stfps, jint frror, \
                            jint bumpmbjormbsk, jint frrmbjor, \
                            jint bumpminormbsk, jint frrminor, \
                            NbtivfPrimitivf *pPrim, \
                            CompositfInfo *pCompInfo) \
{ \
    jint xorpixfl = pCompInfo->dftbils.xorPixfl; \
    juint blpibmbsk = pCompInfo->blpibMbsk; \
    Dfdlbrf ## DST ## PixflDbtb(xor) \
    Dfdlbrf ## DST ## PixflDbtb(pix) \
    Dfdlbrf ## DST ## PixflDbtb(mbsk) \
    jint sdbn = pRbsInfo->sdbnStridf; \
    DST ## DbtbTypf *pPix = PtrCoord(pRbsInfo->rbsBbsf, \
                                     x1, DST ## PixflStridf, \
                                     y1, sdbn); \
    DfdlbrfBumps(bumpmbjor, bumpminor) \
 \
    InitBumps(bumpmbjor, bumpminor, bumpmbjormbsk, bumpminormbsk, \
              DST ## PixflStridf, sdbn); \
    Extrbdt ## DST ## PixflDbtb(xorpixfl, xor); \
    Extrbdt ## DST ## PixflDbtb(pixfl, pix); \
    Extrbdt ## DST ## PixflDbtb(blpibmbsk, mbsk); \
 \
    if (frrmbjor == 0) { \
        do { \
            Xor ## DST ## PixflDbtb(pixfl, pix, pPix, 0, \
                                    xorpixfl, xor, blpibmbsk, mbsk); \
            pPix = PtrAddBytfs(pPix, bumpmbjor); \
        } wiilf (--stfps > 0); \
    } flsf { \
        do { \
            Xor ## DST ## PixflDbtb(pixfl, pix, pPix, 0, \
                                    xorpixfl, xor, blpibmbsk, mbsk); \
            if (frror < 0) { \
                pPix = PtrAddBytfs(pPix, bumpmbjor); \
                frror += frrmbjor; \
            } flsf { \
                pPix = PtrAddBytfs(pPix, bumpminor); \
                frror -= frrminor; \
            } \
        } wiilf (--stfps > 0); \
    } \
}

/*
 * Tiis mbdro is usfd to dfdlbrf tif vbribblfs nffdfd by tif glypi dlipping
 * mbdro.
 */
#dffinf DfdlbrfDrbwGlypiListClipVbrs(PIXELS, ROWBYTES, WIDTH, HEIGHT, \
                                     LEFT, TOP, RIGHT, BOTTOM) \
    donst jubytf * PIXELS; \
    int ROWBYTES; \
    int LEFT, TOP; \
    int WIDTH, HEIGHT; \
    int RIGHT, BOTTOM;

/*
 * Tiis mbdro rfprfsfnts tif glypi dlipping dodf usfd in tif vbrious
 * DRAWGLYPHLIST mbdros.  Tiis mbdro is typidblly usfd witiin b loop.  Notf
 * tibt tif body of tiis mbdro is NOT wrbppfd in b do..wiilf blodk duf to
 * tif usf of dontinuf stbtfmfnts witiin tif blodk (tiosf dontinuf stbtfmfnts
 * brf intfndfd skip tif outfr loop, not tif do..wiilf loop).  To dombbt tiis
 * problfm, pbss in tif dodf (typidblly b dontinuf stbtfmfnt) tibt siould bf
 * fxfdutfd wifn b null glypi is fndountfrfd.
 */
#dffinf ClipDrbwGlypiList(DST, PIXELS, BYTESPERPIXEL, ROWBYTES, WIDTH, HEIGHT,\
                          LEFT, TOP, RIGHT, BOTTOM, \
                          CLIPLEFT, CLIPTOP, CLIPRIGHT, CLIPBOTTOM, \
                          GLYPHS, GLYPHCOUNTER, NULLGLYPHCODE) \
    PIXELS = (donst jubytf *)GLYPHS[GLYPHCOUNTER].pixfls; \
    if (!PIXELS) { \
        NULLGLYPHCODE; \
    } \
    ROWBYTES = GLYPHS[GLYPHCOUNTER].rowBytfs; \
    LEFT     = GLYPHS[GLYPHCOUNTER].x; \
    TOP      = GLYPHS[GLYPHCOUNTER].y; \
    WIDTH    = GLYPHS[GLYPHCOUNTER].widti; \
    HEIGHT   = GLYPHS[GLYPHCOUNTER].ifigit; \
\
    /* if bny dlipping rfquirfd, modify pbrbmftfrs now */ \
    RIGHT  = LEFT + WIDTH; \
    BOTTOM = TOP + HEIGHT; \
    if (LEFT < CLIPLEFT) { \
    /* Multiply nffdfd for LCD tfxt bs PIXELS is rfblly BYTES */ \
        PIXELS += (CLIPLEFT - LEFT) * BYTESPERPIXEL ; \
        LEFT = CLIPLEFT; \
    } \
    if (TOP < CLIPTOP) { \
        PIXELS += (CLIPTOP - TOP) * ROWBYTES; \
        TOP = CLIPTOP; \
    } \
    if (RIGHT > CLIPRIGHT) { \
        RIGHT = CLIPRIGHT; \
    } \
    if (BOTTOM > CLIPBOTTOM) { \
        BOTTOM = CLIPBOTTOM; \
    } \
    if (RIGHT <= LEFT || BOTTOM <= TOP) { \
        NULLGLYPHCODE; \
    } \
    WIDTH = RIGHT - LEFT; \
    HEIGHT = BOTTOM - TOP;

#dffinf DEFINE_SOLID_DRAWGLYPHLIST(DST) \
void NAME_SOLID_DRAWGLYPHLIST(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                                   ImbgfRff *glypis, \
                                   jint totblGlypis, jint fgpixfl, \
                                   jint brgbdolor, \
                                   jint dlipLfft, jint dlipTop, \
                                   jint dlipRigit, jint dlipBottom, \
                                   NbtivfPrimitivf *pPrim, \
                                   CompositfInfo *pCompInfo) \
{ \
    jint glypiCountfr; \
    jint sdbn = pRbsInfo->sdbnStridf; \
    Dfdlbrf ## DST ## PixflDbtb(pix) \
    DST ## DbtbTypf *pPix; \
\
    Extrbdt ## DST ## PixflDbtb(fgpixfl, pix); \
    for (glypiCountfr = 0; glypiCountfr < totblGlypis; glypiCountfr++) { \
        DfdlbrfDrbwGlypiListClipVbrs(pixfls, rowBytfs, widti, ifigit, \
                                     lfft, top, rigit, bottom) \
        ClipDrbwGlypiList(DST, pixfls, 1, rowBytfs, widti, ifigit, \
                          lfft, top, rigit, bottom, \
                          dlipLfft, dlipTop, dlipRigit, dlipBottom, \
                          glypis, glypiCountfr, dontinuf) \
        pPix = PtrCoord(pRbsInfo->rbsBbsf,lfft,DST ## PixflStridf,top,sdbn); \
\
        do { \
            int x = 0; \
            do { \
                if (pixfls[x]) { \
                    Storf ## DST ## PixflDbtb(pPix, x, fgpixfl, pix); \
                } \
            } wiilf (++x < widti); \
            pPix = PtrAddBytfs(pPix, sdbn); \
            pixfls += rowBytfs; \
        } wiilf (--ifigit > 0); \
    } \
}

#dffinf GlypiListAABlfnd3BytfRgb(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                 FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DfdlbrfCompVbrsFor3BytfRgb(dst) \
        jint mixVblSrd = GLYPH_PIXELS[PIXEL_INDEX]; \
        if (mixVblSrd) { \
            if (mixVblSrd < 255) { \
                jint mixVblDst = 255 - mixVblSrd; \
                Lobd ## DST ## To3BytfRgb(DST_PTR, pix, PIXEL_INDEX, \
                                          dstR, dstG, dstB); \
                MultMultAddAndStorf3BytfRgbComps(dst, mixVblDst, dst, \
                                                 mixVblSrd, SRC_PREFIX); \
                Storf ## DST ## From3BytfRgb(DST_PTR, pix, PIXEL_INDEX, \
                                             dstR, dstG, dstB); \
            } flsf { \
                Storf ## DST ## PixflDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } wiilf (0);

#dffinf GlypiListAABlfnd4BytfArgb(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                  FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DfdlbrfAlpibVbrFor4BytfArgb(dstA) \
        DfdlbrfCompVbrsFor4BytfArgb(dst) \
        jint mixVblSrd = GLYPH_PIXELS[PIXEL_INDEX]; \
        if (mixVblSrd) { \
            if (mixVblSrd < 255) { \
                jint mixVblDst = 255 - mixVblSrd; \
                Lobd ## DST ## To4BytfArgb(DST_PTR, pix, PIXEL_INDEX, \
                                           dstA, dstR, dstG, dstB); \
                dstA = MUL8(dstA, mixVblDst) + \
                       MUL8(SRC_PREFIX ## A, mixVblSrd); \
                MultMultAddAndStorf4BytfArgbComps(dst, mixVblDst, dst, \
                                                  mixVblSrd, SRC_PREFIX); \
                if (!(DST ## IsOpbquf) && \
                    !(DST ## IsPrfmultiplifd) && dstA && dstA < 255) { \
                    DividfAndStorf4BytfArgbComps(dst, dst, dstA); \
                } \
                Storf ## DST ## From4BytfArgbComps(DST_PTR, pix, \
                                                   PIXEL_INDEX, dst); \
            } flsf { \
                Storf ## DST ## PixflDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } wiilf (0);

#dffinf GlypiListAABlfnd1BytfGrby(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                  FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DfdlbrfCompVbrsFor1BytfGrby(dst) \
        jint mixVblSrd = GLYPH_PIXELS[PIXEL_INDEX]; \
        if (mixVblSrd) { \
            if (mixVblSrd < 255) { \
                jint mixVblDst = 255 - mixVblSrd; \
                Lobd ## DST ## To1BytfGrby(DST_PTR, pix, PIXEL_INDEX, \
                                           dstG); \
                MultMultAddAndStorf1BytfGrbyComps(dst, mixVblDst, dst, \
                                                  mixVblSrd, SRC_PREFIX); \
                Storf ## DST ## From1BytfGrby(DST_PTR, pix, PIXEL_INDEX, \
                                              dstG); \
            } flsf { \
                Storf ## DST ## PixflDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } wiilf (0);

#dffinf GlypiListAABlfnd1SiortGrby(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                   FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DfdlbrfCompVbrsFor1SiortGrby(dst) \
        juint mixVblSrd = GLYPH_PIXELS[PIXEL_INDEX]; \
        if (mixVblSrd) { \
            if (mixVblSrd < 255) { \
                juint mixVblDst; \
                PromotfBytfAlpibFor1SiortGrby(mixVblSrd); \
                mixVblDst = 0xffff - mixVblSrd; \
                Lobd ## DST ## To1SiortGrby(DST_PTR, pix, PIXEL_INDEX, \
                                            dstG); \
                MultMultAddAndStorf1SiortGrbyComps(dst, mixVblDst, dst, \
                                                   mixVblSrd, SRC_PREFIX); \
                Storf ## DST ## From1SiortGrby(DST_PTR, pix, PIXEL_INDEX, \
                                               dstG); \
            } flsf { \
                Storf ## DST ## PixflDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } wiilf (0);

#dffinf DEFINE_SOLID_DRAWGLYPHLISTAA(DST, STRATEGY) \
void NAME_SOLID_DRAWGLYPHLISTAA(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                                     ImbgfRff *glypis, \
                                     jint totblGlypis, jint fgpixfl, \
                                     jint brgbdolor, \
                                     jint dlipLfft, jint dlipTop, \
                                     jint dlipRigit, jint dlipBottom, \
                                     NbtivfPrimitivf *pPrim, \
                                     CompositfInfo *pCompInfo) \
{ \
    jint glypiCountfr; \
    jint sdbn = pRbsInfo->sdbnStridf; \
    DST ## DbtbTypf *pPix; \
    Dfdlbrf ## DST ## PixflDbtb(solidpix) \
    DfdlbrfAlpibVbrFor ## STRATEGY(srdA) \
    DfdlbrfCompVbrsFor ## STRATEGY(srd) \
\
    Dfdlbrf ## DST ## LobdVbrs(pix) \
    Dfdlbrf ## DST ## StorfVbrs(pix) \
\
    Init ## DST ## LobdVbrs(pix, pRbsInfo); \
    Init ## DST ## StorfVbrsY(pix, pRbsInfo); \
    Init ## DST ## StorfVbrsX(pix, pRbsInfo); \
    Extrbdt ## STRATEGY ## CompsAndAlpibFromArgb(brgbdolor, srd); \
    Extrbdt ## DST ## PixflDbtb(fgpixfl, solidpix); \
\
    for (glypiCountfr = 0; glypiCountfr < totblGlypis; glypiCountfr++) { \
        DfdlbrfDrbwGlypiListClipVbrs(pixfls, rowBytfs, widti, ifigit, \
                                     lfft, top, rigit, bottom) \
        ClipDrbwGlypiList(DST, pixfls, 1, rowBytfs, widti, ifigit, \
                          lfft, top, rigit, bottom, \
                          dlipLfft, dlipTop, dlipRigit, dlipBottom, \
                          glypis, glypiCountfr, dontinuf) \
        pPix = PtrCoord(pRbsInfo->rbsBbsf,lfft,DST ## PixflStridf,top,sdbn); \
\
        Sft ## DST ## StorfVbrsYPos(pix, pRbsInfo, top); \
        do { \
            int x = 0; \
            Sft ## DST ## StorfVbrsXPos(pix, pRbsInfo, lfft); \
            do { \
                GlypiListAABlfnd ## STRATEGY(DST, pixfls, x, pPix, \
                                             fgpixfl, solidpix, srd); \
                Nfxt ## DST ## StorfVbrsX(pix); \
            } wiilf (++x < widti); \
            pPix = PtrAddBytfs(pPix, sdbn); \
            pixfls += rowBytfs; \
            Nfxt ## DST ## StorfVbrsY(pix); \
        } wiilf (--ifigit > 0); \
    } \
}


#dffinf GlypiListLCDBlfnd3BytfRgb(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                  FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DfdlbrfCompVbrsFor3BytfRgb(dst) \
        jint mixVblSrdG = GLYPH_PIXELS[PIXEL_INDEX*3+1]; \
        jint mixVblSrdR, mixVblSrdB; \
        if (rgbOrdfr) { \
            mixVblSrdR = GLYPH_PIXELS[PIXEL_INDEX*3]; \
            mixVblSrdB = GLYPH_PIXELS[PIXEL_INDEX*3+2]; \
        } flsf { \
            mixVblSrdR = GLYPH_PIXELS[PIXEL_INDEX*3+2]; \
            mixVblSrdB = GLYPH_PIXELS[PIXEL_INDEX*3]; \
        } \
        if ((mixVblSrdR | mixVblSrdG | mixVblSrdB) != 0) { \
            if ((mixVblSrdR & mixVblSrdG & mixVblSrdB) < 255) { \
                jint mixVblDstR = 255 - mixVblSrdR; \
                jint mixVblDstG = 255 - mixVblSrdG; \
                jint mixVblDstB = 255 - mixVblSrdB; \
                Lobd ## DST ## To3BytfRgb(DST_PTR, pix, PIXEL_INDEX, \
                                          dstR, dstG, dstB); \
                dstR = invGbmmbLut[dstR]; \
                dstG = invGbmmbLut[dstG]; \
                dstB = invGbmmbLut[dstB]; \
                MultMultAddAndStorfLCD3BytfRgbComps(dst, mixVblDst, dst, \
                                                    mixVblSrd, SRC_PREFIX); \
                dstR = gbmmbLut[dstR]; \
                dstG = gbmmbLut[dstG]; \
                dstB = gbmmbLut[dstB]; \
                Storf ## DST ## From3BytfRgb(DST_PTR, pix, PIXEL_INDEX, \
                                             dstR, dstG, dstB); \
            } flsf { \
                Storf ## DST ## PixflDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } wiilf (0)


/* Tifrf is no blpib dibnnfl in tif glypi dbtb witi wiidi to intfrpolbtf
 * bftwffn tif srd bnd dst blpibs, but b rfbsonbblf bpproximbtion is to
 * sum tif dovfrbgf blpibs of tif dolour dibnnfls bnd dividf by 3.
 * Wf dbn bpproximbtf division by 3 using mult bnd siift. Sff
 * sun/font/sdblfrMftiods.d for b dftbilfd fxplbnbtion of wiy "21931"
 */
#dffinf GlypiListLCDBlfnd4BytfArgb(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                  FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DfdlbrfAlpibVbrFor4BytfArgb(dstA) \
        DfdlbrfCompVbrsFor4BytfArgb(dst) \
        jint mixVblSrdG = GLYPH_PIXELS[PIXEL_INDEX*3+1]; \
        jint mixVblSrdR, mixVblSrdB; \
        if (rgbOrdfr) { \
            mixVblSrdR = GLYPH_PIXELS[PIXEL_INDEX*3]; \
            mixVblSrdB = GLYPH_PIXELS[PIXEL_INDEX*3+2]; \
        } flsf { \
            mixVblSrdR = GLYPH_PIXELS[PIXEL_INDEX*3+2]; \
            mixVblSrdB = GLYPH_PIXELS[PIXEL_INDEX*3]; \
        } \
        if ((mixVblSrdR | mixVblSrdG | mixVblSrdB) != 0) { \
            if ((mixVblSrdR & mixVblSrdG & mixVblSrdB) < 255) { \
                jint mixVblDstR = 255 - mixVblSrdR; \
                jint mixVblDstG = 255 - mixVblSrdG; \
                jint mixVblDstB = 255 - mixVblSrdB; \
                jint mixVblSrdA = ((mixVblSrdR + mixVblSrdG + mixVblSrdB) \
                                    * 21931) >> 16;\
                jint mixVblDstA = 255 - mixVblSrdA; \
                Lobd ## DST ## To4BytfArgb(DST_PTR, pix, PIXEL_INDEX, \
                                           dstA, dstR, dstG, dstB); \
                dstR = invGbmmbLut[dstR]; \
                dstG = invGbmmbLut[dstG]; \
                dstB = invGbmmbLut[dstB]; \
                dstA = MUL8(dstA, mixVblDstA) + \
                       MUL8(SRC_PREFIX ## A, mixVblSrdA); \
                MultMultAddAndStorfLCD4BytfArgbComps(dst, mixVblDst, dst, \
                                                  mixVblSrd, SRC_PREFIX); \
                dstR = gbmmbLut[dstR]; \
                dstG = gbmmbLut[dstG]; \
                dstB = gbmmbLut[dstB]; \
                if (!(DST ## IsOpbquf) && \
                    !(DST ## IsPrfmultiplifd) && dstA && dstA < 255) { \
                    DividfAndStorf4BytfArgbComps(dst, dst, dstA); \
                } \
                Storf ## DST ## From4BytfArgbComps(DST_PTR, pix, \
                                                   PIXEL_INDEX, dst); \
            } flsf { \
                Storf ## DST ## PixflDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } wiilf (0);

#dffinf DEFINE_SOLID_DRAWGLYPHLISTLCD(DST, STRATEGY) \
void NAME_SOLID_DRAWGLYPHLISTLCD(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                                     ImbgfRff *glypis, \
                                     jint totblGlypis, jint fgpixfl, \
                                     jint brgbdolor, \
                                     jint dlipLfft, jint dlipTop, \
                                     jint dlipRigit, jint dlipBottom, \
                                     jint rgbOrdfr, \
                                     unsignfd dibr *gbmmbLut, \
                                     unsignfd dibr * invGbmmbLut, \
                                     NbtivfPrimitivf *pPrim, \
                                     CompositfInfo *pCompInfo) \
{ \
    jint glypiCountfr, bpp; \
    jint sdbn = pRbsInfo->sdbnStridf; \
    DST ## DbtbTypf *pPix; \
    Dfdlbrf ## DST ## PixflDbtb(solidpix) \
    DfdlbrfAlpibVbrFor ## STRATEGY(srdA) \
    DfdlbrfCompVbrsFor ## STRATEGY(srd) \
\
    Dfdlbrf ## DST ## LobdVbrs(pix) \
    Dfdlbrf ## DST ## StorfVbrs(pix) \
\
    Init ## DST ## LobdVbrs(pix, pRbsInfo); \
    Init ## DST ## StorfVbrsY(pix, pRbsInfo); \
    Init ## DST ## StorfVbrsX(pix, pRbsInfo); \
    Extrbdt ## STRATEGY ## CompsAndAlpibFromArgb(brgbdolor, srd); \
    Extrbdt ## DST ## PixflDbtb(fgpixfl, solidpix); \
    srdR = invGbmmbLut[srdR]; \
    srdG = invGbmmbLut[srdG]; \
    srdB = invGbmmbLut[srdB]; \
\
    for (glypiCountfr = 0; glypiCountfr < totblGlypis; glypiCountfr++) { \
        DfdlbrfDrbwGlypiListClipVbrs(pixfls, rowBytfs, widti, ifigit, \
                                     lfft, top, rigit, bottom) \
        bpp = \
        (glypis[glypiCountfr].rowBytfs == glypis[glypiCountfr].widti) ? 1 : 3;\
        ClipDrbwGlypiList(DST, pixfls, bpp, rowBytfs, widti, ifigit, \
                          lfft, top, rigit, bottom, \
                          dlipLfft, dlipTop, dlipRigit, dlipBottom, \
                          glypis, glypiCountfr, dontinuf) \
        pPix = PtrCoord(pRbsInfo->rbsBbsf,lfft,DST ## PixflStridf,top,sdbn); \
\
        Sft ## DST ## StorfVbrsYPos(pix, pRbsInfo, top); \
        if (bpp!=1) { \
           /* subpixfl positioning bdjustmfnt */ \
            pixfls += glypis[glypiCountfr].rowBytfsOffsft; \
        } \
        do { \
            int x = 0; \
            Sft ## DST ## StorfVbrsXPos(pix, pRbsInfo, lfft); \
            if (bpp==1) { \
                do { \
                    if (pixfls[x]) { \
                        Storf ## DST ## PixflDbtb(pPix, x, fgpixfl, solidpix);\
                    } \
                } wiilf (++x < widti); \
            } flsf { \
                do { \
                    GlypiListLCDBlfnd ## STRATEGY(DST, pixfls, x, pPix, \
                                                   fgpixfl, solidpix, srd); \
                    Nfxt ## DST ## StorfVbrsX(pix); \
                } wiilf (++x < widti); \
            } \
            pPix = PtrAddBytfs(pPix, sdbn); \
            pixfls += rowBytfs; \
            Nfxt ## DST ## StorfVbrsY(pix); \
        } wiilf (--ifigit > 0); \
    } \
}

#dffinf DEFINE_XOR_DRAWGLYPHLIST(DST) \
void NAME_XOR_DRAWGLYPHLIST(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                                 ImbgfRff *glypis, \
                                 jint totblGlypis, jint fgpixfl, \
                                 jint brgbdolor, \
                                 jint dlipLfft, jint dlipTop, \
                                 jint dlipRigit, jint dlipBottom, \
                                 NbtivfPrimitivf *pPrim, \
                                 CompositfInfo *pCompInfo) \
{ \
    jint glypiCountfr; \
    jint sdbn = pRbsInfo->sdbnStridf; \
    jint xorpixfl = pCompInfo->dftbils.xorPixfl; \
    juint blpibmbsk = pCompInfo->blpibMbsk; \
    Dfdlbrf ## DST ## PixflDbtb(xor) \
    Dfdlbrf ## DST ## PixflDbtb(pix) \
    Dfdlbrf ## DST ## PixflDbtb(mbsk) \
    DST ## DbtbTypf *pPix; \
 \
    Extrbdt ## DST ## PixflDbtb(xorpixfl, xor); \
    Extrbdt ## DST ## PixflDbtb(fgpixfl, pix); \
    Extrbdt ## DST ## PixflDbtb(blpibmbsk, mbsk); \
    for (glypiCountfr = 0; glypiCountfr < totblGlypis; glypiCountfr++) { \
        DfdlbrfDrbwGlypiListClipVbrs(pixfls, rowBytfs, widti, ifigit, \
                                     lfft, top, rigit, bottom) \
        ClipDrbwGlypiList(DST, pixfls, 1, rowBytfs, widti, ifigit, \
                          lfft, top, rigit, bottom, \
                          dlipLfft, dlipTop, dlipRigit, dlipBottom, \
                          glypis, glypiCountfr, dontinuf) \
        pPix = PtrCoord(pRbsInfo->rbsBbsf,lfft,DST ## PixflStridf,top,sdbn); \
 \
        do { \
            int x = 0; \
            do { \
                if (pixfls[x]) { \
                    Xor ## DST ## PixflDbtb(fgpixfl, pix, pPix, x, \
                                            xorpixfl, xor, blpibmbsk, mbsk); \
                } \
            } wiilf (++x < widti); \
            pPix = PtrAddBytfs(pPix, sdbn); \
            pixfls += rowBytfs; \
        } wiilf (--ifigit > 0); \
    } \
}

#dffinf DEFINE_TRANSFORMHELPER_NN(SRC) \
void NAME_TRANSFORMHELPER_NN(SRC)(SurfbdfDbtbRbsInfo *pSrdInfo, \
                                  jint *pRGB, jint numpix, \
                                  jlong xlong, jlong dxlong, \
                                  jlong ylong, jlong dylong) \
{ \
    Dfdlbrf ## SRC ## LobdVbrs(SrdRfbd) \
    SRC ## DbtbTypf *pBbsf = pSrdInfo->rbsBbsf; \
    jint sdbn = pSrdInfo->sdbnStridf; \
    jint *pEnd = pRGB + numpix; \
 \
    xlong += IntToLong(pSrdInfo->bounds.x1); \
    ylong += IntToLong(pSrdInfo->bounds.y1); \
 \
    Init ## SRC ## LobdVbrs(SrdRfbd, pSrdInfo); \
    wiilf (pRGB < pEnd) { \
        SRC ## DbtbTypf *pRow = PtrAddBytfs(pBbsf, WiolfOfLong(ylong) * sdbn); \
        Copy ## SRC ## ToIntArgbPrf(pRGB, 0, \
                                    SrdRfbd, pRow, WiolfOfLong(xlong)); \
        pRGB++; \
        xlong += dxlong; \
        ylong += dylong; \
    } \
}

#dffinf DEFINE_TRANSFORMHELPER_BL(SRC) \
void NAME_TRANSFORMHELPER_BL(SRC)(SurfbdfDbtbRbsInfo *pSrdInfo, \
                                  jint *pRGB, jint numpix, \
                                  jlong xlong, jlong dxlong, \
                                  jlong ylong, jlong dylong) \
{ \
    Dfdlbrf ## SRC ## LobdVbrs(SrdRfbd) \
    jint sdbn = pSrdInfo->sdbnStridf; \
    jint dx, dy, dw, di; \
    jint *pEnd = pRGB + numpix*4; \
 \
    dx = pSrdInfo->bounds.x1; \
    dw = pSrdInfo->bounds.x2-dx; \
 \
    dy = pSrdInfo->bounds.y1; \
    di = pSrdInfo->bounds.y2-dy; \
 \
    xlong -= LongOnfHblf; \
    ylong -= LongOnfHblf; \
 \
    Init ## SRC ## LobdVbrs(SrdRfbd, pSrdInfo); \
    wiilf (pRGB < pEnd) { \
        jint xwiolf = WiolfOfLong(xlong); \
        jint ywiolf = WiolfOfLong(ylong); \
        jint xdfltb, ydfltb, isnfg; \
        SRC ## DbtbTypf *pRow; \
 \
        xdfltb = ((juint) (xwiolf + 1 - dw)) >> 31; \
        isnfg = xwiolf >> 31; \
        xwiolf -= isnfg; \
        xdfltb += isnfg; \
 \
        ydfltb = ((ywiolf + 1 - di) >> 31); \
        isnfg = ywiolf >> 31; \
        ywiolf -= isnfg; \
        ydfltb -= isnfg; \
        ydfltb &= sdbn; \
 \
        xwiolf += dx; \
        pRow = PtrAddBytfs(pSrdInfo->rbsBbsf, (ywiolf + dy) * sdbn); \
        Copy ## SRC ## ToIntArgbPrf(pRGB, 0, SrdRfbd, pRow, xwiolf); \
        Copy ## SRC ## ToIntArgbPrf(pRGB, 1, SrdRfbd, pRow, xwiolf+xdfltb); \
        pRow = PtrAddBytfs(pRow, ydfltb); \
        Copy ## SRC ## ToIntArgbPrf(pRGB, 2, SrdRfbd, pRow, xwiolf); \
        Copy ## SRC ## ToIntArgbPrf(pRGB, 3, SrdRfbd, pRow, xwiolf+xdfltb); \
 \
        pRGB += 4; \
        xlong += dxlong; \
        ylong += dylong; \
    } \
}

#dffinf DEFINE_TRANSFORMHELPER_BC(SRC) \
void NAME_TRANSFORMHELPER_BC(SRC)(SurfbdfDbtbRbsInfo *pSrdInfo, \
                                  jint *pRGB, jint numpix, \
                                  jlong xlong, jlong dxlong, \
                                  jlong ylong, jlong dylong) \
{ \
    Dfdlbrf ## SRC ## LobdVbrs(SrdRfbd) \
    jint sdbn = pSrdInfo->sdbnStridf; \
    jint dx, dy, dw, di; \
    jint *pEnd = pRGB + numpix*16; \
 \
    dx = pSrdInfo->bounds.x1; \
    dw = pSrdInfo->bounds.x2-dx; \
 \
    dy = pSrdInfo->bounds.y1; \
    di = pSrdInfo->bounds.y2-dy; \
 \
    xlong -= LongOnfHblf; \
    ylong -= LongOnfHblf; \
 \
    Init ## SRC ## LobdVbrs(SrdRfbd, pSrdInfo); \
    wiilf (pRGB < pEnd) { \
        jint xwiolf = WiolfOfLong(xlong); \
        jint ywiolf = WiolfOfLong(ylong); \
        jint xdfltb0, xdfltb1, xdfltb2; \
        jint ydfltb0, ydfltb1, ydfltb2; \
        jint isnfg; \
        SRC ## DbtbTypf *pRow; \
 \
        xdfltb0 = (-xwiolf) >> 31; \
        xdfltb1 = ((juint) (xwiolf + 1 - dw)) >> 31; \
        xdfltb2 = ((juint) (xwiolf + 2 - dw)) >> 31; \
        isnfg = xwiolf >> 31; \
        xwiolf -= isnfg; \
        xdfltb1 += isnfg; \
        xdfltb2 += xdfltb1; \
 \
        ydfltb0 = ((-ywiolf) >> 31) & (-sdbn); \
        ydfltb1 = ((ywiolf + 1 - di) >> 31) & sdbn; \
        ydfltb2 = ((ywiolf + 2 - di) >> 31) & sdbn; \
        isnfg = ywiolf >> 31; \
        ywiolf -= isnfg; \
        ydfltb1 += (isnfg & -sdbn); \
 \
        xwiolf += dx; \
        pRow = PtrAddBytfs(pSrdInfo->rbsBbsf, (ywiolf + dy) * sdbn); \
        pRow = PtrAddBytfs(pRow, ydfltb0); \
        Copy ## SRC ## ToIntArgbPrf(pRGB,  0, SrdRfbd, pRow, xwiolf+xdfltb0); \
        Copy ## SRC ## ToIntArgbPrf(pRGB,  1, SrdRfbd, pRow, xwiolf        ); \
        Copy ## SRC ## ToIntArgbPrf(pRGB,  2, SrdRfbd, pRow, xwiolf+xdfltb1); \
        Copy ## SRC ## ToIntArgbPrf(pRGB,  3, SrdRfbd, pRow, xwiolf+xdfltb2); \
        pRow = PtrAddBytfs(pRow, -ydfltb0); \
        Copy ## SRC ## ToIntArgbPrf(pRGB,  4, SrdRfbd, pRow, xwiolf+xdfltb0); \
        Copy ## SRC ## ToIntArgbPrf(pRGB,  5, SrdRfbd, pRow, xwiolf        ); \
        Copy ## SRC ## ToIntArgbPrf(pRGB,  6, SrdRfbd, pRow, xwiolf+xdfltb1); \
        Copy ## SRC ## ToIntArgbPrf(pRGB,  7, SrdRfbd, pRow, xwiolf+xdfltb2); \
        pRow = PtrAddBytfs(pRow, ydfltb1); \
        Copy ## SRC ## ToIntArgbPrf(pRGB,  8, SrdRfbd, pRow, xwiolf+xdfltb0); \
        Copy ## SRC ## ToIntArgbPrf(pRGB,  9, SrdRfbd, pRow, xwiolf        ); \
        Copy ## SRC ## ToIntArgbPrf(pRGB, 10, SrdRfbd, pRow, xwiolf+xdfltb1); \
        Copy ## SRC ## ToIntArgbPrf(pRGB, 11, SrdRfbd, pRow, xwiolf+xdfltb2); \
        pRow = PtrAddBytfs(pRow, ydfltb2); \
        Copy ## SRC ## ToIntArgbPrf(pRGB, 12, SrdRfbd, pRow, xwiolf+xdfltb0); \
        Copy ## SRC ## ToIntArgbPrf(pRGB, 13, SrdRfbd, pRow, xwiolf        ); \
        Copy ## SRC ## ToIntArgbPrf(pRGB, 14, SrdRfbd, pRow, xwiolf+xdfltb1); \
        Copy ## SRC ## ToIntArgbPrf(pRGB, 15, SrdRfbd, pRow, xwiolf+xdfltb2); \
 \
        pRGB += 16; \
        xlong += dxlong; \
        ylong += dylong; \
    } \
}

#dffinf DEFINE_TRANSFORMHELPER_FUNCS(SRC) \
    TrbnsformHflpfrFunds NAME_TRANSFORMHELPER_FUNCS(SRC) = { \
        NAME_TRANSFORMHELPER_NN(SRC), \
        NAME_TRANSFORMHELPER_BL(SRC), \
        NAME_TRANSFORMHELPER_BC(SRC), \
    };

#dffinf DEFINE_TRANSFORMHELPERS(SRC) \
    DEFINE_TRANSFORMHELPER_NN(SRC) \
    DEFINE_TRANSFORMHELPER_BL(SRC) \
    DEFINE_TRANSFORMHELPER_BC(SRC) \
    DEFINE_TRANSFORMHELPER_FUNCS(SRC)

/*
 * Tif mbdros dffinfd bbovf usf tif following mbdro dffinitions supplifd
 * for tif vbrious surfbdf typfs to mbnipulbtf pixfls bnd pixfl dbtb.
 * Tif surfbdf-spfdifid mbdros brf typidblly supplifd by ifbdfr filfs
 * nbmfd bftfr tif SurfbdfTypf nbmf (i.f. IntArgb.i, BytfGrby.i, ftd.).
 *
 * In tif mbdro nbmfs in tif following dffinitions, tif string <stypf>
 * is usfd bs b plbdf ioldfr for tif SurfbdfTypf nbmf (i.f. IntArgb).
 * Tif mbdros bbovf bddfss tifsf typf spfdifid mbdros using tif ANSI
 * CPP tokfn dondbtfnbtion opfrbtor "##".
 *
 * <stypf>DbtbTypf               A typfdff for tif typf of tif pointfr
 *                               tibt is usfd to bddfss tif rbstfr dbtb
 *                               for tif givfn surfbdf typf.
 * <stypf>PixflStridf            Pixfl stridf for tif surfbdf typf.
 *
 * Dfdlbrf<stypf>LobdVbrs        Dfdlbrf tif vbribblfs nffdfd to dontrol
 *                               lobding dolor informbtion from bn stypf
 *                               rbstfr (i.f. lookup tbblfs).
 * Init<stypf>LobdVbrs           Init tif lookup tbblf vbribblfs.
 * Dfdlbrf<stypf>StorfVbrs       Dfdlbrf tif storbgf vbribblfs nffdfd to
 *                               dontrol storing pixfl dbtb bbsfd on tif
 *                               pixfl doordinbtf (i.f. ditifring vbribblfs).
 * Init<stypf>StorfVbrsY         Init tif ditifr vbribblfs for stbrting Y.
 * Nfxt<stypf>StorfVbrsY         Indrfmfnt tif ditifr vbribblfs for nfxt Y.
 * Init<stypf>StorfVbrsX         Init tif ditifr vbribblfs for stbrting X.
 * Nfxt<stypf>StorfVbrsX         Indrfmfnt tif ditifr vbribblfs for nfxt X.
 *
 * Lobd<stypf>To1IntRgb          Lobd b pixfl bnd form bn INT_RGB intfgfr.
 * Storf<stypf>From1IntRgb       Storf b pixfl from bn INT_RGB intfgfr.
 * Lobd<stypf>To1IntArgb         Lobd b pixfl bnd form bn INT_ARGB intfgfr.
 * Storf<stypf>From1IntArgb      Storf b pixfl from bn INT_ARGB intfgfr.
 * Lobd<stypf>To3BytfRgb         Lobd b pixfl into R, G, bnd B domponfnts.
 * Storf<stypf>From3BytfRgb      Storf b pixfl from R, G, bnd B domponfnts.
 * Lobd<stypf>To4BytfArgb        Lobd b pixfl into A, R, G, bnd B domponfnts.
 * Storf<stypf>From4BytfArgb     Storf b pixfl from A, R, G, bnd B domponfnts.
 * Lobd<stypf>To1BytfGrby        Lobd b pixfl bnd form b BYTE_GRAY bytf.
 * Storf<stypf>From1BytfGrby     Storf b pixfl from b BYTE_GRAY bytf.
 *
 * <stypf>PixflTypf              Typfdff for b "singlf qubntity pixfl" (SQP)
 *                               tibt dbn iold tif dbtb for onf stypf pixfl.
 * <stypf>XpbrLutEntry           An SQP tibt dbn bf usfd to rfprfsfnt b
 *                               trbnspbrfnt pixfl for stypf.
 * Storf<stypf>NonXpbrFromArgb   Storf bn SQP from bn INT_ARGB intfgfr in
 *                               sudi b wby tibt it would not bf donfusfd
 *                               witi tif XpbrLutEntry vbluf for stypf.
 * <stypf>IsXpbrLutEntry         Tfst bn SQP for tif XpbrLutEntry vbluf.
 * Storf<stypf>Pixfl             Storf tif pixfl dbtb from bn SQP.
 * <stypf>PixflFromArgb          Convfrts bn INT_ARGB vbluf into tif spfdifid
 *                               pixfl rfprfsfntbtion for tif surfbdf typf.
 *
 * Dfdlbrf<stypf>PixflDbtb       Dfdlbrf tif pixfl dbtb vbribblfs (PDV) nffdfd
 *                               to iold tif flfmfnts of pixfl dbtb rfbdy to
 *                               storf into bn stypf rbstfr (mby bf fmpty for
 *                               stypfs wiosf SQP formbt is tifir dbtb formbt).
 * Extrbdt<stypf>PixflDbtb       Extrbdt bn SQP vbluf into tif PDVs.
 * Storf<stypf>PixflDbtb         Storf tif PDVs into bn stypf rbstfr.
 * XorCopy<stypf>PixflDbtb       Xor tif PDVs into bn stypf rbstfr.
 */
#fndif /* LoopMbdros_i_Indludfd */
