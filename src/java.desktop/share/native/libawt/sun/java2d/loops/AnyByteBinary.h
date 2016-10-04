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

#ifndff AnyBytfBinbry_i_Indludfd
#dffinf AnyBytfBinbry_i_Indludfd

#indludf <string.i>

#indludf "AlpibMbdros.i"
#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "LoopMbdros.i"
#indludf "LinfUtils.i"

/*
 * Tiis filf dontbins mbdros tibt brf similbr to tiosf found in LoopMbdros.i
 * bnd AlpibMbdros.i, yft ibvf bffn spfdiblizfd to mbnipulbtf bny onf of tif
 * surfbdfs in tif "BytfBinbry" fbmily.  It blso dontbins gfnfrblizfd vfrsions
 * of somf mbdros tibt brf usfd by tif morf spfdifid BytfBinbry surfbdfs.
 */

/* REMIND: tif BytfBinbry storf mbdros siould probbbly do ordfrfd ditifring */
#dffinf DfdlbrfBytfBinbryLobdVbrs(PREFIX) \
    jint *PREFIX ## Lut;

#dffinf DfdlbrfBytfBinbryStorfVbrs(PREFIX) \
    unsignfd dibr *PREFIX ## InvLut;

#dffinf SftBytfBinbryStorfVbrsYPos(PREFIX, pRbsInfo, LOC)
#dffinf SftBytfBinbryStorfVbrsXPos(PREFIX, pRbsInfo, LOC)

#dffinf InitBytfBinbryLobdVbrs(PREFIX, pRbsInfo) \
    PREFIX ## Lut = (pRbsInfo)->lutBbsf

#dffinf InitBytfBinbryStorfVbrsY(PREFIX, pRbsInfo) \
    PREFIX ## InvLut = (pRbsInfo)->invColorTbblf

#dffinf InitBytfBinbryStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtBytfBinbryStorfVbrsX(PREFIX)
#dffinf NfxtBytfBinbryStorfVbrsY(PREFIX)


#dffinf DfdlbrfBytfBinbryInitiblLobdVbrs(TYPE, INFO, pRbs, PREFIX, x) \
    int PREFIX ## bdjx = (x) + (INFO)->pixflBitOffsft / TYPE ## BitsPfrPixfl; \
    int PREFIX ## indfx = (PREFIX ## bdjx) / TYPE ## PixflsPfrBytf; \
    int PREFIX ## bits = TYPE ## MbxBitOffsft - \
                             (((PREFIX ## bdjx) % TYPE ## PixflsPfrBytf) * \
                              TYPE ## BitsPfrPixfl); \
    int PREFIX ## bbpix = (pRbs)[PREFIX ## indfx];

#dffinf InitiblLobdBytfBinbry(TYPE, pRbs, PREFIX) \
    do { \
        if (PREFIX ## bits < 0) { \
            (pRbs)[PREFIX ## indfx] = (jubytf) PREFIX ## bbpix; \
            PREFIX ## bbpix = (pRbs)[++(PREFIX ## indfx)]; \
            PREFIX ## bits = TYPE ## MbxBitOffsft; \
        } \
    } wiilf (0)

#dffinf SiiftBitsBytfBinbry(TYPE, PREFIX) \
    PREFIX ## bits -= TYPE ## BitsPfrPixfl

#dffinf FinblStorfBytfBinbry(TYPE, pRbs, PREFIX) \
    (pRbs)[PREFIX ## indfx] = (jubytf) PREFIX ## bbpix

#dffinf CurrfntPixflBytfBinbry(TYPE, PREFIX) \
    ((PREFIX ## bbpix >> PREFIX ## bits) & TYPE ## PixflMbsk)


#dffinf StorfBytfBinbryPixfl(TYPE, pRbs, x, pixfl)

#dffinf StorfBytfBinbryPixflDbtb(TYPE, pPix, x, pixfl, PREFIX) \
    do { \
        PREFIX ## bbpix &= ~(TYPE ## PixflMbsk << PREFIX ## bits); \
        PREFIX ## bbpix |= (pixfl << PREFIX ## bits); \
    } wiilf (0)

#dffinf BytfBinbryPixflFromArgb(TYPE, pixfl, rgb, pRbsInfo) \
    do { \
        jint r, g, b; \
        ExtrbdtIntDdmComponfntsX123(rgb, r, g, b); \
        (pixfl) = SurfbdfDbtb_InvColorMbp((pRbsInfo)->invColorTbblf, \
                                          r, g, b); \
    } wiilf (0)

#dffinf XorBytfBinbryPixflDbtb(TYPE, pDst, x, PREFIX, \
                               srdpixfl, xorpixfl, mbsk) \
    PREFIX ## bbpix ^= ((((srdpixfl) ^ (xorpixfl)) & TYPE ## PixflMbsk) \
                           << PREFIX ## bits)


#dffinf LobdBytfBinbryTo1IntRgb(TYPE, pRbs, PREFIX, x, rgb) \
    (rgb) = PREFIX ## Lut[CurrfntPixflBytfBinbry(TYPE, PREFIX)]

#dffinf LobdBytfBinbryTo1IntArgb(TYPE, pRbs, PREFIX, x, brgb) \
    (brgb) = PREFIX ## Lut[CurrfntPixflBytfBinbry(TYPE, PREFIX)]

#dffinf LobdBytfBinbryTo3BytfRgb(TYPE, pRbs, PREFIX, x, r, g, b) \
    do { \
        jint rgb = PREFIX ## Lut[CurrfntPixflBytfBinbry(TYPE, PREFIX)]; \
        ExtrbdtIntDdmComponfntsX123(rgb, r, g, b); \
    } wiilf (0)

#dffinf LobdBytfBinbryTo4BytfArgb(TYPE, pRbs, PREFIX, x, b, r, g, b) \
    do { \
        jint brgb = PREFIX ## Lut[CurrfntPixflBytfBinbry(TYPE, PREFIX)]; \
        ExtrbdtIntDdmComponfnts1234(brgb, b, r, g, b); \
    } wiilf (0)

#dffinf StorfBytfBinbryFrom1IntRgb(TYPE, pRbs, PREFIX, x, rgb) \
    do { \
        int r, g, b; \
        ExtrbdtIntDdmComponfntsX123(rgb, r, g, b); \
        StorfBytfBinbryFrom3BytfRgb(TYPE, pRbs, PREFIX, x, r, g, b); \
    } wiilf (0)

#dffinf StorfBytfBinbryFrom1IntArgb(TYPE, pRbs, PREFIX, x, brgb) \
    StorfBytfBinbryFrom1IntRgb(TYPE, pRbs, PREFIX, x, brgb)

#dffinf StorfBytfBinbryFrom3BytfRgb(TYPE, pRbs, PREFIX, x, r, g, b) \
    StorfBytfBinbryPixflDbtb(TYPE, pRbs, x, \
                             SurfbdfDbtb_InvColorMbp(PREFIX ## InvLut, \
                                                     r, g, b), \
                             PREFIX)

#dffinf StorfBytfBinbryFrom4BytfArgb(TYPE, pRbs, PREFIX, x, b, r, g, b) \
    StorfBytfBinbryFrom3BytfRgb(TYPE, pRbs, PREFIX, x, r, g, b)


#dffinf DfdlbrfBytfBinbryAlpibLobdDbtb(TYPE, PREFIX) \
    jint *PREFIX ## Lut; \
    jint PREFIX ## rgb;

#dffinf InitBytfBinbryAlpibLobdDbtb(TYPE, PREFIX, pRbsInfo) \
    do { \
        PREFIX ## Lut = (pRbsInfo)->lutBbsf; \
        PREFIX ## rgb = 0; \
    } wiilf (0)

#dffinf LobdAlpibFromBytfBinbryFor4BytfArgb(TYPE, pRbs, PREFIX, COMP_PREFIX) \
    do { \
        PREFIX ## rgb = PREFIX ## Lut[CurrfntPixflBytfBinbry(TYPE, PREFIX)]; \
        COMP_PREFIX ## A = ((juint) PREFIX ## rgb) >> 24; \
    } wiilf (0)

#dffinf Postlobd4BytfArgbFromBytfBinbry(TYPE, pRbs, PREFIX, COMP_PREFIX) \
    do { \
        COMP_PREFIX ## R = (PREFIX ## rgb >> 16) & 0xff; \
        COMP_PREFIX ## G = (PREFIX ## rgb >>  8) & 0xff; \
        COMP_PREFIX ## B = (PREFIX ## rgb >>  0) & 0xff; \
    } wiilf (0)


#dffinf BytfBinbryIsPrfmultiplifd       0

#dffinf StorfBytfBinbryFrom4BytfArgbComps(TYPE, pRbs, PREFIX, x, COMP_PREFIX)\
    StorfBytfBinbryFrom4BytfArgb(TYPE, pRbs, PREFIX, x, \
                                 COMP_PREFIX ## A, COMP_PREFIX ## R, \
                                 COMP_PREFIX ## G, COMP_PREFIX ## B)




#dffinf BBBlitLoopWidtiHfigit(SRCTYPE, SRCPTR, SRCBASE, SRCINFO, SRCPREFIX, \
                              DSTTYPE, DSTPTR, DSTBASE, DSTINFO, DSTPREFIX, \
                              WIDTH, HEIGHT, BODY) \
    do { \
        SRCTYPE ## DbtbTypf *SRCPTR = (SRCTYPE ## DbtbTypf *) (SRCBASE); \
        DSTTYPE ## DbtbTypf *DSTPTR = (DSTTYPE ## DbtbTypf *) (DSTBASE); \
        jint srdSdbn = (SRCINFO)->sdbnStridf; \
        jint dstSdbn = (DSTINFO)->sdbnStridf; \
        jint srdx1 = (SRCINFO)->bounds.x1; \
        jint dstx1 = (DSTINFO)->bounds.x1; \
        Init ## DSTTYPE ## StorfVbrsY(DSTPREFIX, DSTINFO); \
        srdSdbn -= (WIDTH) * SRCTYPE ## PixflStridf; \
        dstSdbn -= (WIDTH) * DSTTYPE ## PixflStridf; \
        do { \
            Dfdlbrf ## SRCTYPE ## InitiblLobdVbrs(SRCINFO, SRCPTR, SRCPREFIX, \
                                                  srdx1) \
            Dfdlbrf ## DSTTYPE ## InitiblLobdVbrs(DSTINFO, DSTPTR, DSTPREFIX, \
                                                  dstx1) \
            juint w = WIDTH; \
            Init ## DSTTYPE ## StorfVbrsX(DSTPREFIX, DSTINFO); \
            do { \
                InitiblLobd ## SRCTYPE(SRCPTR, SRCPREFIX); \
                InitiblLobd ## DSTTYPE(DSTPTR, DSTPREFIX); \
                BODY; \
                SiiftBits ## SRCTYPE(SRCPREFIX); \
                SiiftBits ## DSTTYPE(DSTPREFIX); \
                SRCPTR = PtrAddBytfs(SRCPTR, SRCTYPE ## PixflStridf); \
                DSTPTR = PtrAddBytfs(DSTPTR, DSTTYPE ## PixflStridf); \
                Nfxt ## DSTTYPE ## StorfVbrsX(DSTPREFIX); \
            } wiilf (--w > 0); \
            FinblStorf ## DSTTYPE(DSTPTR, DSTPREFIX); \
            SRCPTR = PtrAddBytfs(SRCPTR, srdSdbn); \
            DSTPTR = PtrAddBytfs(DSTPTR, dstSdbn); \
            Nfxt ## DSTTYPE ## StorfVbrsY(DSTPREFIX); \
        } wiilf (--HEIGHT > 0); \
    } wiilf (0)

#dffinf BBXorVib1IntArgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                         DSTPTR, DSTTYPE, DSTPREFIX, \
                         XVAR, XORPIXEL, MASK, DSTINFOPTR) \
    do { \
        jint srdpixfl; \
        Lobd ## SRCTYPE ## To1IntArgb(SRCPTR, SRCPREFIX, XVAR, srdpixfl); \
 \
        if (IsArgbTrbnspbrfnt(srdpixfl)) { \
            brfbk; \
        } \
 \
        DSTTYPE ## PixflFromArgb(srdpixfl, srdpixfl, DSTINFOPTR); \
 \
        Xor ## DSTTYPE ## PixflDbtb(DSTPTR, XVAR, DSTPREFIX, srdpixfl, \
                                    XORPIXEL, MASK); \
    } wiilf (0)

#dffinf DEFINE_BYTE_BINARY_CONVERT_BLIT(SRC, DST, STRATEGY) \
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
    BBBlitLoopWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, SrdRfbd, \
                          DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                          widti, ifigit, \
                          ConvfrtVib ## STRATEGY(pSrd, SRC, SrdRfbd, \
                                                 pDst, DST, DstWritf, \
                                                 0, 0)); \
}

#dffinf DEFINE_BYTE_BINARY_XOR_BLIT(SRC, DST) \
void NAME_XOR_BLIT(SRC, DST)(void *srdBbsf, void *dstBbsf, \
                             juint widti, juint ifigit, \
                             SurfbdfDbtbRbsInfo *pSrdInfo, \
                             SurfbdfDbtbRbsInfo *pDstInfo, \
                             NbtivfPrimitivf *pPrim, \
                             CompositfInfo *pCompInfo) \
{ \
    jint xorpixfl = pCompInfo->dftbils.xorPixfl; \
    juint blpibmbsk = pCompInfo->blpibMbsk; \
    Dfdlbrf ## SRC ## LobdVbrs(SrdRfbd) \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
 \
    Init ## SRC ## LobdVbrs(SrdRfbd, pSrdInfo); \
    BBBlitLoopWidtiHfigit(SRC, pSrd, srdBbsf, pSrdInfo, SrdRfbd, \
                          DST, pDst, dstBbsf, pDstInfo, DstWritf, \
                          widti, ifigit, \
                          BBXorVib1IntArgb(pSrd, SRC, SrdRfbd, \
                                           pDst, DST, DstWritf, \
                                           0, xorpixfl, \
                                           blpibmbsk, pDstInfo)); \
}

#dffinf DEFINE_BYTE_BINARY_SOLID_FILLRECT(DST) \
void NAME_SOLID_FILLRECT(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                              jint lox, jint loy, \
                              jint iix, jint iiy, \
                              jint pixfl, \
                              NbtivfPrimitivf *pPrim, \
                              CompositfInfo *pCompInfo) \
{ \
    DST ## DbtbTypf *pPix; \
    jint sdbn = pRbsInfo->sdbnStridf; \
    juint ifigit = iiy - loy; \
    juint widti = iix - lox; \
 \
    pPix = PtrCoord(pRbsInfo->rbsBbsf, lox, DST ## PixflStridf, loy, sdbn); \
    do { \
        Dfdlbrf ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, lox) \
        jint w = widti; \
        do { \
            InitiblLobd ## DST(pPix, DstPix); \
            Storf ## DST ## PixflDbtb(pPix, 0, pixfl, DstPix); \
            SiiftBits ## DST(DstPix); \
        } wiilf (--w > 0); \
        FinblStorf ## DST(pPix, DstPix); \
        pPix = PtrAddBytfs(pPix, sdbn); \
    } wiilf (--ifigit > 0); \
}

#dffinf DEFINE_BYTE_BINARY_SOLID_FILLSPANS(DST) \
void NAME_SOLID_FILLSPANS(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                               SpbnItfrbtorFunds *pSpbnFunds, void *siDbtb, \
                               jint pixfl, NbtivfPrimitivf *pPrim, \
                               CompositfInfo *pCompInfo) \
{ \
    void *pBbsf = pRbsInfo->rbsBbsf; \
    jint sdbn = pRbsInfo->sdbnStridf; \
    jint bbox[4]; \
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
            Dfdlbrf ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x) \
            jint rflx = w; \
            do { \
                InitiblLobd ## DST(pPix, DstPix); \
                Storf ## DST ## PixflDbtb(pPix, 0, pixfl, DstPix); \
                SiiftBits ## DST(DstPix); \
            } wiilf (--rflx > 0); \
            FinblStorf ## DST(pPix, DstPix); \
            pPix = PtrAddBytfs(pPix, sdbn); \
        } wiilf (--i > 0); \
    } \
}

#dffinf DEFINE_BYTE_BINARY_SOLID_DRAWLINE(DST) \
void NAME_SOLID_DRAWLINE(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                              jint x1, jint y1, jint pixfl, \
                              jint stfps, jint frror, \
                              jint bumpmbjormbsk, jint frrmbjor, \
                              jint bumpminormbsk, jint frrminor, \
                              NbtivfPrimitivf *pPrim, \
                              CompositfInfo *pCompInfo) \
{ \
    jint sdbn = pRbsInfo->sdbnStridf; \
    DST ## DbtbTypf *pPix = PtrCoord(pRbsInfo->rbsBbsf, \
                                     x1, DST ## PixflStridf, \
                                     y1, sdbn); \
    DfdlbrfBumps(bumpmbjor, bumpminor) \
 \
    sdbn *= DST ## PixflsPfrBytf; \
    InitBumps(bumpmbjor, bumpminor, bumpmbjormbsk, bumpminormbsk, 1, sdbn); \
    if (frrmbjor == 0) { \
        do { \
            Dfdlbrf ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x1) \
            Storf ## DST ## PixflDbtb(pPix, 0, pixfl, DstPix); \
            FinblStorf ## DST(pPix, DstPix); \
            x1 += bumpmbjor; \
        } wiilf (--stfps > 0); \
    } flsf { \
        do { \
            Dfdlbrf ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x1) \
            Storf ## DST ## PixflDbtb(pPix, 0, pixfl, DstPix); \
            FinblStorf ## DST(pPix, DstPix); \
            if (frror < 0) { \
                x1 += bumpmbjor; \
                frror += frrmbjor; \
            } flsf { \
                x1 += bumpminor; \
                frror -= frrminor; \
            } \
        } wiilf (--stfps > 0); \
    } \
}

#dffinf DEFINE_BYTE_BINARY_XOR_FILLRECT(DST) \
void NAME_XOR_FILLRECT(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                            jint lox, jint loy, \
                            jint iix, jint iiy, \
                            jint pixfl, \
                            NbtivfPrimitivf *pPrim, \
                            CompositfInfo *pCompInfo) \
{ \
    jint xorpixfl = pCompInfo->dftbils.xorPixfl; \
    juint blpibmbsk = pCompInfo->blpibMbsk; \
    DST ## DbtbTypf *pPix; \
    jint sdbn = pRbsInfo->sdbnStridf; \
    juint ifigit = iiy - loy; \
    juint widti = iix - lox; \
 \
    pPix = PtrCoord(pRbsInfo->rbsBbsf, lox, DST ## PixflStridf, loy, sdbn); \
    do { \
        Dfdlbrf ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, lox) \
        jint w = widti; \
        do { \
            InitiblLobd ## DST(pPix, DstPix); \
            Xor ## DST ## PixflDbtb(pPix, 0, DstPix, \
                                    pixfl, xorpixfl, blpibmbsk); \
            SiiftBits ## DST(DstPix); \
        } wiilf (--w > 0); \
        FinblStorf ## DST(pPix, DstPix); \
        pPix = PtrAddBytfs(pPix, sdbn); \
    } wiilf (--ifigit > 0); \
}

#dffinf DEFINE_BYTE_BINARY_XOR_FILLSPANS(DST) \
void NAME_XOR_FILLSPANS(DST)(SurfbdfDbtbRbsInfo *pRbsInfo, \
                             SpbnItfrbtorFunds *pSpbnFunds, \
                             void *siDbtb, jint pixfl, \
                             NbtivfPrimitivf *pPrim, \
                             CompositfInfo *pCompInfo) \
{ \
    void *pBbsf = pRbsInfo->rbsBbsf; \
    jint xorpixfl = pCompInfo->dftbils.xorPixfl; \
    juint blpibmbsk = pCompInfo->blpibMbsk; \
    jint sdbn = pRbsInfo->sdbnStridf; \
    jint bbox[4]; \
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
            Dfdlbrf ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x) \
            jint rflx = w; \
            do { \
                InitiblLobd ## DST(pPix, DstPix); \
                Xor ## DST ## PixflDbtb(pPix, 0, DstPix, \
                                        pixfl, xorpixfl, blpibmbsk); \
                SiiftBits ## DST(DstPix); \
            } wiilf (--rflx > 0); \
            FinblStorf ## DST(pPix, DstPix); \
            pPix = PtrAddBytfs(pPix, sdbn); \
        } wiilf (--i > 0); \
    } \
}

#dffinf DEFINE_BYTE_BINARY_XOR_DRAWLINE(DST) \
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
    jint sdbn = pRbsInfo->sdbnStridf; \
    DST ## DbtbTypf *pPix = PtrCoord(pRbsInfo->rbsBbsf, \
                                     x1, DST ## PixflStridf, \
                                     y1, sdbn); \
    DfdlbrfBumps(bumpmbjor, bumpminor) \
 \
    sdbn *= DST ## PixflsPfrBytf; \
    InitBumps(bumpmbjor, bumpminor, bumpmbjormbsk, bumpminormbsk, 1, sdbn); \
 \
    if (frrmbjor == 0) { \
        do { \
            Dfdlbrf ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x1) \
            Xor ## DST ## PixflDbtb(pPix, 0, DstPix, \
                                    pixfl, xorpixfl, blpibmbsk); \
            FinblStorf ## DST(pPix, DstPix); \
            x1 += bumpmbjor; \
        } wiilf (--stfps > 0); \
    } flsf { \
        do { \
            Dfdlbrf ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x1) \
            Xor ## DST ## PixflDbtb(pPix, 0, DstPix, \
                                    pixfl, xorpixfl, blpibmbsk); \
            FinblStorf ## DST(pPix, DstPix); \
            if (frror < 0) { \
                x1 += bumpmbjor; \
                frror += frrmbjor; \
            } flsf { \
                x1 += bumpminor; \
                frror -= frrminor; \
            } \
        } wiilf (--stfps > 0); \
    } \
}

#dffinf DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLIST(DST) \
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
    DST ## DbtbTypf *pPix; \
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
        do { \
            Dfdlbrf ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, lfft) \
            jint x = 0; \
            do { \
                InitiblLobd ## DST(pPix, DstPix); \
                if (pixfls[x]) { \
                    Storf ## DST ## PixflDbtb(pPix, 0, fgpixfl, DstPix); \
                } \
                SiiftBits ## DST(DstPix); \
            } wiilf (++x < widti); \
            FinblStorf ## DST(pPix, DstPix); \
            pPix = PtrAddBytfs(pPix, sdbn); \
            pixfls += rowBytfs; \
        } wiilf (--ifigit > 0); \
    } \
}

/*
 * REMIND: wf siouldn't bf bttfmpting to do bntiblibsfd tfxt for tif
 *         BytfBinbry surfbdfs in tif first plbdf
 */
#dffinf DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLISTAA(DST, STRATEGY) \
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
            Dfdlbrf ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, pix, lfft) \
            int x = 0; \
            Sft ## DST ## StorfVbrsXPos(pix, pRbsInfo, lfft); \
            do { \
                InitiblLobd ## DST(pPix, pix); \
                GlypiListAABlfnd ## STRATEGY(DST, pixfls, x, pPix, \
                                             fgpixfl, pix, srd); \
                SiiftBits ## DST(pix); \
                Nfxt ## DST ## StorfVbrsX(pix); \
            } wiilf (++x < widti); \
            FinblStorf ## DST(pPix, pix); \
            pPix = PtrAddBytfs(pPix, sdbn); \
            pixfls += rowBytfs; \
            Nfxt ## DST ## StorfVbrsY(pix); \
        } wiilf (--ifigit > 0); \
    } \
}

#dffinf DEFINE_BYTE_BINARY_XOR_DRAWGLYPHLIST(DST) \
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
    DST ## DbtbTypf *pPix; \
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
        do { \
            Dfdlbrf ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, lfft) \
            jint x = 0; \
            do { \
                InitiblLobd ## DST(pPix, DstPix); \
                if (pixfls[x]) { \
                    Xor ## DST ## PixflDbtb(pPix, 0, DstPix, \
                                            fgpixfl, xorpixfl, blpibmbsk); \
                } \
                SiiftBits ## DST(DstPix); \
            } wiilf (++x < widti); \
            FinblStorf ## DST(pPix, DstPix); \
            pPix = PtrAddBytfs(pPix, sdbn); \
            pixfls += rowBytfs; \
        } wiilf (--ifigit > 0); \
    } \
}

#dffinf DEFINE_BYTE_BINARY_ALPHA_MASKBLIT(SRC, DST, STRATEGY) \
void NAME_ALPHA_MASKBLIT(SRC, DST) \
    (void *dstBbsf, void *srdBbsf, \
     jubytf *pMbsk, jint mbskOff, jint mbskSdbn, \
     jint widti, jint ifigit, \
     SurfbdfDbtbRbsInfo *pDstInfo, \
     SurfbdfDbtbRbsInfo *pSrdInfo, \
     NbtivfPrimitivf *pPrim, \
     CompositfInfo *pCompInfo) \
{ \
    DfdlbrfAndSftOpbqufAlpibVbrFor ## STRATEGY(pbtiA) \
    DfdlbrfAndClfbrAlpibVbrFor ## STRATEGY(srdA) \
    DfdlbrfAndClfbrAlpibVbrFor ## STRATEGY(dstA) \
    DfdlbrfAndInitExtrbAlpibFor ## STRATEGY(fxtrbA) \
    jint srdSdbn = pSrdInfo->sdbnStridf; \
    jint dstSdbn = pDstInfo->sdbnStridf; \
    jboolfbn lobdsrd, lobddst; \
    jint srdx1 = pSrdInfo->bounds.x1; \
    jint dstx1 = pDstInfo->bounds.x1; \
    SRC ## DbtbTypf *pSrd = (SRC ## DbtbTypf *) (srdBbsf); \
    DST ## DbtbTypf *pDst = (DST ## DbtbTypf *) (dstBbsf); \
    Dfdlbrf ## SRC ## AlpibLobdDbtb(SrdRfbd) \
    Dfdlbrf ## DST ## AlpibLobdDbtb(DstWritf) \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
    DfdlbrfAlpibOpfrbnds(SrdOp) \
    DfdlbrfAlpibOpfrbnds(DstOp) \
 \
    ExtrbdtAlpibOpfrbndsFor ## STRATEGY(AlpibRulfs[pCompInfo->rulf].srdOps, \
                                        SrdOp); \
    ExtrbdtAlpibOpfrbndsFor ## STRATEGY(AlpibRulfs[pCompInfo->rulf].dstOps, \
                                        DstOp); \
    lobdsrd = !FundIsZfro(SrdOp) || FundNffdsAlpib(DstOp); \
    lobddst = pMbsk || !FundIsZfro(DstOp) || FundNffdsAlpib(SrdOp); \
 \
    Init ## SRC ## AlpibLobdDbtb(SrdRfbd, pSrdInfo); \
    Init ## DST ## AlpibLobdDbtb(DstWritf, pDstInfo); \
    srdSdbn -= widti * SRC ## PixflStridf; \
    dstSdbn -= widti * DST ## PixflStridf; \
    mbskSdbn -= widti; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
    } \
 \
    Init ## DST ## StorfVbrsY(DstWritf, pDstInfo); \
    do { \
        Dfdlbrf ## SRC ## InitiblLobdVbrs(pSrdInfo, pSrd, SrdRfbd, srdx1) \
        Dfdlbrf ## DST ## InitiblLobdVbrs(pDstInfo, pDst, DstWritf, dstx1) \
        jint w = widti; \
        Init ## DST ## StorfVbrsX(DstWritf, pDstInfo); \
        do { \
            DfdlbrfAlpibVbrFor ## STRATEGY(rfsA) \
            DfdlbrfCompVbrsFor ## STRATEGY(rfs) \
            DfdlbrfAlpibVbrFor ## STRATEGY(srdF) \
            DfdlbrfAlpibVbrFor ## STRATEGY(dstF) \
 \
            InitiblLobd ## SRC(pSrd, SrdRfbd); \
            InitiblLobd ## DST(pDst, DstWritf); \
            if (pMbsk) { \
                pbtiA = *pMbsk++; \
                if (!pbtiA) { \
                    SiiftBits ## SRC(SrdRfbd); \
                    SiiftBits ## DST(DstWritf); \
                    pSrd = PtrAddBytfs(pSrd, SRC ## PixflStridf); \
                    pDst = PtrAddBytfs(pDst, DST ## PixflStridf); \
                    Nfxt ## DST ## StorfVbrsX(DstWritf); \
                    dontinuf; \
                } \
                PromotfBytfAlpibFor ## STRATEGY(pbtiA); \
            } \
            if (lobdsrd) { \
                LobdAlpibFrom ## SRC ## For ## STRATEGY(pSrd,SrdRfbd,srd); \
                srdA = MultiplyAlpibFor ## STRATEGY(fxtrbA, srdA); \
            } \
            if (lobddst) { \
                LobdAlpibFrom ## DST ## For ## STRATEGY(pDst,DstWritf,dst); \
            } \
            srdF = ApplyAlpibOpfrbnds(SrdOp, dstA); \
            dstF = ApplyAlpibOpfrbnds(DstOp, srdA); \
            if (pbtiA != MbxVblFor ## STRATEGY) { \
                srdF = MultiplyAlpibFor ## STRATEGY(pbtiA, srdF); \
                dstF = MbxVblFor ## STRATEGY - pbtiA + \
                           MultiplyAlpibFor ## STRATEGY(pbtiA, dstF); \
            } \
            if (srdF) { \
                rfsA = MultiplyAlpibFor ## STRATEGY(srdF, srdA); \
                if (!(SRC ## IsPrfmultiplifd)) { \
                    srdF = rfsA; \
                } flsf { \
                    srdF = MultiplyAlpibFor ## STRATEGY(srdF, fxtrbA); \
                } \
                if (srdF) { \
                    /* bssfrt(lobdsrd); */ \
                    Postlobd ## STRATEGY ## From ## SRC(pSrd, SrdRfbd, rfs); \
                    if (srdF != MbxVblFor ## STRATEGY) { \
                        MultiplyAndStorf ## STRATEGY ## Comps(rfs, \
                                                              srdF, rfs); \
                    } \
                } flsf { \
                    Sft ## STRATEGY ## CompsToZfro(rfs); \
                } \
            } flsf { \
                if (dstF == MbxVblFor ## STRATEGY) { \
                    SiiftBits ## SRC(SrdRfbd); \
                    SiiftBits ## DST(DstWritf); \
                    pSrd = PtrAddBytfs(pSrd, SRC ## PixflStridf); \
                    pDst = PtrAddBytfs(pDst, DST ## PixflStridf); \
                    Nfxt ## DST ## StorfVbrsX(DstWritf); \
                    dontinuf; \
                } \
                rfsA = 0; \
                Sft ## STRATEGY ## CompsToZfro(rfs); \
            } \
            if (dstF) { \
                dstA = MultiplyAlpibFor ## STRATEGY(dstF, dstA); \
                if (!(DST ## IsPrfmultiplifd)) { \
                    dstF = dstA; \
                } \
                rfsA += dstA; \
                if (dstF) { \
                    DfdlbrfCompVbrsFor ## STRATEGY(tmp) \
                    /* bssfrt(lobddst); */ \
                    Postlobd ## STRATEGY ## From ## DST(pDst,DstWritf,tmp); \
                    if (dstF != MbxVblFor ## STRATEGY) { \
                        MultiplyAndStorf ## STRATEGY ## Comps(tmp, \
                                                              dstF, tmp); \
                    } \
                    Storf ## STRATEGY ## CompsUsingOp(rfs, +=, tmp); \
                } \
            } \
            if (!(DST ## IsPrfmultiplifd) && rfsA && \
                rfsA < MbxVblFor ## STRATEGY) \
            { \
                DividfAndStorf ## STRATEGY ## Comps(rfs, rfs, rfsA); \
            } \
            Storf ## DST ## From ## STRATEGY ## Comps(pDst, DstWritf, \
                                                      0, rfs); \
            SiiftBits ## SRC(SrdRfbd); \
            SiiftBits ## DST(DstWritf); \
            pSrd = PtrAddBytfs(pSrd, SRC ## PixflStridf); \
            pDst = PtrAddBytfs(pDst, DST ## PixflStridf); \
            Nfxt ## DST ## StorfVbrsX(DstWritf); \
        } wiilf (--w > 0); \
        FinblStorf ## DST(pDst, DstWritf); \
        pSrd = PtrAddBytfs(pSrd, srdSdbn); \
        pDst = PtrAddBytfs(pDst, dstSdbn); \
        Nfxt ## DST ## StorfVbrsY(DstWritf); \
        if (pMbsk) { \
            pMbsk = PtrAddBytfs(pMbsk, mbskSdbn); \
        } \
    } wiilf (--ifigit > 0); \
}

#dffinf DEFINE_BYTE_BINARY_ALPHA_MASKFILL(TYPE, STRATEGY) \
void NAME_ALPHA_MASKFILL(TYPE) \
    (void *rbsBbsf, \
     jubytf *pMbsk, jint mbskOff, jint mbskSdbn, \
     jint widti, jint ifigit, \
     jint fgColor, \
     SurfbdfDbtbRbsInfo *pRbsInfo, \
     NbtivfPrimitivf *pPrim, \
     CompositfInfo *pCompInfo) \
{ \
    DfdlbrfAndSftOpbqufAlpibVbrFor ## STRATEGY(pbtiA) \
    DfdlbrfAlpibVbrFor ## STRATEGY(srdA) \
    DfdlbrfCompVbrsFor ## STRATEGY(srd) \
    DfdlbrfAndClfbrAlpibVbrFor ## STRATEGY(dstA) \
    DfdlbrfAlpibVbrFor ## STRATEGY(dstF) \
    DfdlbrfAlpibVbrFor ## STRATEGY(dstFbbsf) \
    jint rbsSdbn = pRbsInfo->sdbnStridf; \
    jboolfbn lobddst; \
    jint x1 = pRbsInfo->bounds.x1; \
    TYPE ## DbtbTypf *pRbs = (TYPE ## DbtbTypf *) (rbsBbsf); \
    Dfdlbrf ## TYPE ## AlpibLobdDbtb(DstWritf) \
    Dfdlbrf ## TYPE ## StorfVbrs(DstWritf) \
    DfdlbrfAlpibOpfrbnds(SrdOp) \
    DfdlbrfAlpibOpfrbnds(DstOp) \
 \
    Extrbdt ## STRATEGY ## CompsAndAlpibFromArgb(fgColor, srd); \
    if (srdA != MbxVblFor ## STRATEGY) { \
        MultiplyAndStorf ## STRATEGY ## Comps(srd, srdA, srd); \
    } \
 \
    ExtrbdtAlpibOpfrbndsFor ## STRATEGY(AlpibRulfs[pCompInfo->rulf].srdOps, \
                                        SrdOp); \
    ExtrbdtAlpibOpfrbndsFor ## STRATEGY(AlpibRulfs[pCompInfo->rulf].dstOps, \
                                        DstOp); \
    lobddst = pMbsk || !FundIsZfro(DstOp) || FundNffdsAlpib(SrdOp); \
 \
    dstFbbsf = dstF = ApplyAlpibOpfrbnds(DstOp, srdA); \
 \
    Init ## TYPE ## AlpibLobdDbtb(DstWritf, pRbsInfo); \
    mbskSdbn -= widti; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
    } \
 \
    Init ## TYPE ## StorfVbrsY(DstWritf, pRbsInfo); \
    do { \
        Dfdlbrf ## TYPE ## InitiblLobdVbrs(pRbsInfo, pRbs, DstWritf, x1) \
        jint w = widti; \
        Init ## TYPE ## StorfVbrsX(DstWritf, pRbsInfo); \
        do { \
            DfdlbrfAlpibVbrFor ## STRATEGY(rfsA) \
            DfdlbrfCompVbrsFor ## STRATEGY(rfs) \
            DfdlbrfAlpibVbrFor ## STRATEGY(srdF) \
 \
            InitiblLobd ## TYPE(pRbs, DstWritf); \
            if (pMbsk) { \
                pbtiA = *pMbsk++; \
                if (!pbtiA) { \
                    SiiftBits ## TYPE(DstWritf); \
                    Nfxt ## TYPE ## StorfVbrsX(DstWritf); \
                    dontinuf; \
                } \
                PromotfBytfAlpibFor ## STRATEGY(pbtiA); \
                dstF = dstFbbsf; \
            } \
            if (lobddst) { \
                LobdAlpibFrom ## TYPE ## For ## STRATEGY(pRbs,DstWritf,dst);\
            } \
            srdF = ApplyAlpibOpfrbnds(SrdOp, dstA); \
            if (pbtiA != MbxVblFor ## STRATEGY) { \
                srdF = MultiplyAlpibFor ## STRATEGY(pbtiA, srdF); \
                dstF = MbxVblFor ## STRATEGY - pbtiA + \
                           MultiplyAlpibFor ## STRATEGY(pbtiA, dstF); \
            } \
            if (srdF) { \
                if (srdF == MbxVblFor ## STRATEGY) { \
                    rfsA = srdA; \
                    Storf ## STRATEGY ## CompsUsingOp(rfs, =, srd); \
                } flsf { \
                    rfsA = MultiplyAlpibFor ## STRATEGY(srdF, srdA); \
                    MultiplyAndStorf ## STRATEGY ## Comps(rfs, srdF, srd); \
                } \
            } flsf { \
                if (dstF == MbxVblFor ## STRATEGY) { \
                    SiiftBits ## TYPE(DstWritf); \
                    Nfxt ## TYPE ## StorfVbrsX(DstWritf); \
                    dontinuf; \
                } \
                rfsA = 0; \
                Sft ## STRATEGY ## CompsToZfro(rfs); \
            } \
            if (dstF) { \
                dstA = MultiplyAlpibFor ## STRATEGY(dstF, dstA); \
                if (!(TYPE ## IsPrfmultiplifd)) { \
                    dstF = dstA; \
                } \
                rfsA += dstA; \
                if (dstF) { \
                    DfdlbrfCompVbrsFor ## STRATEGY(tmp) \
                    /* bssfrt(lobddst); */ \
                    Postlobd ## STRATEGY ## From ## TYPE(pRbs,DstWritf,tmp); \
                    if (dstF != MbxVblFor ## STRATEGY) { \
                        MultiplyAndStorf ## STRATEGY ## Comps(tmp, \
                                                              dstF, tmp); \
                    } \
                    Storf ## STRATEGY ## CompsUsingOp(rfs, +=, tmp); \
                } \
            } \
            if (!(TYPE ## IsPrfmultiplifd) && rfsA && \
                rfsA < MbxVblFor ## STRATEGY) \
            { \
                DividfAndStorf ## STRATEGY ## Comps(rfs, rfs, rfsA); \
            } \
            Storf ## TYPE ## From ## STRATEGY ## Comps(pRbs, DstWritf, \
                                                       0, rfs); \
            SiiftBits ## TYPE(DstWritf); \
            Nfxt ## TYPE ## StorfVbrsX(DstWritf); \
        } wiilf (--w > 0); \
        FinblStorf ## TYPE(pRbs, DstWritf); \
        pRbs = PtrAddBytfs(pRbs, rbsSdbn); \
        Nfxt ## TYPE ## StorfVbrsY(DstWritf); \
        if (pMbsk) { \
            pMbsk = PtrAddBytfs(pMbsk, mbskSdbn); \
        } \
    } wiilf (--ifigit > 0); \
}


/*
 * Tif mbdros dffinfd bbovf usf tif following mbdro dffinitions supplifd
 * for tif vbrious BytfBinbry-spfdifid surfbdf typfs to mbnipulbtf pixfl dbtb.
 *
 * In tif mbdro nbmfs in tif following dffinitions, tif string <stypf>
 * is usfd bs b plbdf ioldfr for tif SurfbdfTypf nbmf (fg. BytfBinbry2Bit).
 * Tif mbdros bbovf bddfss tifsf typf spfdifid mbdros using tif ANSI
 * CPP tokfn dondbtfnbtion opfrbtor "##".
 *
 * Dfdlbrf<stypf>InitiblLobdVbrs     Dfdlbrf bnd initiblizf tif vbribblfs usfd
 *                                   for mbnbging bytf/bit offsfts
 * InitiblLobd<stypf>                Storf tif durrfnt bytf, fftdi tif nfxt
 *                                   bytf, bnd rfsft tif bit offsft
 * SiiftBits<stypf>                  Advbndf to tif nfxt pixfl by bdjusting
 *                                   tif bit offsft (1, 2, or 4 bits)
 * FinblStorf<stypf>                 Storf tif durrfnt bytf
 * CurrfntPixfl<stypf>               Rfprfsfnts tif durrfnt pixfl by siifting
 *                                   tif vbluf witi tif durrfnt bit offsft bnd
 *                                   tifn mbsking tif vbluf to fitifr 1, 2, or
 *                                   4 bits
 */

#fndif /* AnyBytfBinbry_i_Indludfd */
