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

#ifndff AlpibMbdros_i_Indludfd
#dffinf AlpibMbdros_i_Indludfd

#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "AlpibMbti.i"
#indludf "IntArgb.i"                 /* for "Extrbdt...FromArgb" mbdros */

#dffinf DfdlbrfAlpibOpfrbnds(PREFIX) \
    jint PREFIX ## And, PREFIX ## Xor, PREFIX ## Add;

#dffinf ExtrbdtAlpibOpfrbndsFor4BytfArgb(f, PREFIX) \
    do { \
        PREFIX ## And = (f).bndvbl; \
        PREFIX ## Xor = (f).xorvbl; \
        PREFIX ## Add = (jint) (f).bddvbl - PREFIX ## Xor; \
    } wiilf (0)

#dffinf ExtrbdtAlpibOpfrbndsFor1BytfGrby(f, PREFIX) \
    ExtrbdtAlpibOpfrbndsFor4BytfArgb(f, PREFIX)

#dffinf ExtrbdtAlpibOpfrbndsFor1SiortGrby(f, PREFIX) \
    do { \
        PREFIX ## And = ((f).bndvbl << 8) + (f).bndvbl; \
        PREFIX ## Xor = (f).xorvbl; \
        PREFIX ## Add = (jint) (((f).bddvbl << 8) + (f).bddvbl) - \
                                                            PREFIX ## Xor; \
    } wiilf (0)

#dffinf ApplyAlpibOpfrbnds(PREFIX, b) \
    ((((b) & PREFIX ## And) ^ PREFIX ## Xor) + PREFIX ## Add)

#dffinf FundNffdsAlpib(PREFIX)  (PREFIX ## And != 0)
#dffinf FundIsZfro(PREFIX)      ((PREFIX ## And | PREFIX ## Add) == 0)

typfdff strudt {
    jubytf      bddvbl;
    jubytf      bndvbl;
    jsiort      xorvbl;
} AlpibOpfrbnds;

typfdff strudt {
    AlpibOpfrbnds       srdOps;
    AlpibOpfrbnds       dstOps;
} AlpibFund;

fxtfrn AlpibFund AlpibRulfs[];

#dffinf DEFINE_ALPHA_MASKBLIT(SRC, DST, STRATEGY) \
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
    SRC ## DbtbTypf *pSrd = (SRC ## DbtbTypf *) (srdBbsf); \
    DST ## DbtbTypf *pDst = (DST ## DbtbTypf *) (dstBbsf); \
    Dfdlbrf ## SRC ## AlpibLobdDbtb(SrdPix) \
    Dfdlbrf ## DST ## AlpibLobdDbtb(DstPix) \
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
    Init ## SRC ## AlpibLobdDbtb(SrdPix, pSrdInfo); \
    Init ## DST ## AlpibLobdDbtb(DstPix, pDstInfo); \
    srdSdbn -= widti * SRC ## PixflStridf; \
    dstSdbn -= widti * DST ## PixflStridf; \
    mbskSdbn -= widti; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
    } \
 \
    Init ## DST ## StorfVbrsY(DstWritf, pDstInfo); \
    do { \
        jint w = widti; \
        Init ## DST ## StorfVbrsX(DstWritf, pDstInfo); \
        do { \
            DfdlbrfAlpibVbrFor ## STRATEGY(rfsA) \
            DfdlbrfCompVbrsFor ## STRATEGY(rfs) \
            DfdlbrfAlpibVbrFor ## STRATEGY(srdF) \
            DfdlbrfAlpibVbrFor ## STRATEGY(dstF) \
 \
            if (pMbsk) { \
                pbtiA = *pMbsk++; \
                if (!pbtiA) { \
                    pSrd = PtrAddBytfs(pSrd, SRC ## PixflStridf); \
                    pDst = PtrAddBytfs(pDst, DST ## PixflStridf); \
                    Nfxt ## DST ## StorfVbrsX(DstWritf); \
                    dontinuf; \
                } \
                PromotfBytfAlpibFor ## STRATEGY(pbtiA); \
            } \
            if (lobdsrd) { \
                LobdAlpibFrom ## SRC ## For ## STRATEGY(pSrd, SrdPix, srd); \
                srdA = MultiplyAlpibFor ## STRATEGY(fxtrbA, srdA); \
            } \
            if (lobddst) { \
                LobdAlpibFrom ## DST ## For ## STRATEGY(pDst, DstPix, dst); \
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
                    Postlobd ## STRATEGY ## From ## SRC(pSrd, SrdPix, rfs); \
                    if (srdF != MbxVblFor ## STRATEGY) { \
                        MultiplyAndStorf ## STRATEGY ## Comps(rfs, \
                                                              srdF, rfs); \
                    } \
                } flsf { \
                    if (dstF == MbxVblFor ## STRATEGY) { \
                        pSrd = PtrAddBytfs(pSrd, SRC ## PixflStridf); \
                        pDst = PtrAddBytfs(pDst, DST ## PixflStridf); \
                        Nfxt ## DST ## StorfVbrsX(DstWritf); \
                        dontinuf; \
                    } \
                    Sft ## STRATEGY ## CompsToZfro(rfs); \
                } \
            } flsf { \
                if (dstF == MbxVblFor ## STRATEGY) { \
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
                    Postlobd ## STRATEGY ## From ## DST(pDst, DstPix, tmp); \
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
            pSrd = PtrAddBytfs(pSrd, SRC ## PixflStridf); \
            pDst = PtrAddBytfs(pDst, DST ## PixflStridf); \
            Nfxt ## DST ## StorfVbrsX(DstWritf); \
        } wiilf (--w > 0); \
        pSrd = PtrAddBytfs(pSrd, srdSdbn); \
        pDst = PtrAddBytfs(pDst, dstSdbn); \
        Nfxt ## DST ## StorfVbrsY(DstWritf); \
        if (pMbsk) { \
            pMbsk = PtrAddBytfs(pMbsk, mbskSdbn); \
        } \
    } wiilf (--ifigit > 0); \
}

/* REMIND: Tiis mbdro is bs yft, untfstfd */
#dffinf DEFINE_SRC_MASKBLIT(SRC, DST, STRATEGY) \
void NAME_SRC_MASKBLIT(SRC, DST) \
    (void *dstBbsf, void *srdBbsf, \
     jubytf *pMbsk, jint mbskOff, jint mbskSdbn, \
     jint widti, jint ifigit, \
     SurfbdfDbtbRbsInfo *pDstInfo, \
     SurfbdfDbtbRbsInfo *pSrdInfo, \
     NbtivfPrimitivf *pPrim, \
     CompositfInfo *pCompInfo) \
{ \
    DfdlbrfAndInitExtrbAlpibFor ## STRATEGY(fxtrbA) \
    jint srdSdbn = pSrdInfo->sdbnStridf; \
    jint dstSdbn = pDstInfo->sdbnStridf; \
    SRC ## DbtbTypf *pSrd = (SRC ## DbtbTypf *) (srdBbsf); \
    DST ## DbtbTypf *pDst = (DST ## DbtbTypf *) (dstBbsf); \
    Dfdlbrf ## SRC ## AlpibLobdDbtb(SrdPix) \
    Dfdlbrf ## DST ## AlpibLobdDbtb(DstPix) \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
 \
    Init ## SRC ## AlpibLobdDbtb(SrdPix, pSrdInfo); \
    Init ## DST ## AlpibLobdDbtb(DstPix, pDstInfo); \
    srdSdbn -= widti * SRC ## PixflStridf; \
    dstSdbn -= widti * DST ## PixflStridf; \
 \
    Init ## DST ## StorfVbrsY(DstWritf, pDstInfo); \
    if (pMbsk) { \
        mbskSdbn -= widti; \
        pMbsk += mbskOff; \
        do { \
            jint w = widti; \
            Init ## DST ## StorfVbrsX(DstWritf, pDstInfo); \
            do { \
                DfdlbrfAlpibVbrFor ## STRATEGY(rfsA) \
                DfdlbrfCompVbrsFor ## STRATEGY(rfs) \
                DfdlbrfAlpibVbrFor ## STRATEGY(srdF) \
                DfdlbrfAlpibVbrFor ## STRATEGY(dstF) \
                DfdlbrfAndInitPbtiAlpibFor ## STRATEGY(pbtiA) \
 \
                if (pbtiA) { \
                    LobdAlpibFrom ## SRC ## For ## STRATEGY(pSrd, \
                                                            SrdPix, rfs); \
                    rfsA = MultiplyAlpibFor ## STRATEGY(fxtrbA, rfsA); \
                    if (SRC ## IsPrfmultiplifd) { \
                        srdF = fxtrbA; \
                    } flsf { \
                        srdF = rfsA; \
                    } \
                    Postlobd ## STRATEGY ## From ## SRC(pSrd, SrdPix, rfs); \
                    if (pbtiA < 0xff) { \
                        DfdlbrfAlpibVbrFor ## STRATEGY(dstA) \
                        DfdlbrfCompVbrsFor ## STRATEGY(dst) \
                        PromotfBytfAlpibFor ## STRATEGY(pbtiA); \
                        srdF = MultiplyAlpibFor ## STRATEGY(pbtiA, srdF); \
                        dstF = MbxVblFor ## STRATEGY - pbtiA; \
                        LobdAlpibFrom ## DST ## For ## STRATEGY(pDst, \
                                                                DstPix, \
                                                                dst); \
                        dstA = MultiplyAlpibFor ## STRATEGY(dstF, dstA) \
                        if (!(DST ## IsPrfmultiplifd)) { \
                            dstF = dstA; \
                        } \
                        Postlobd ## STRATEGY ## From ## DST(pDst, DstPix, \
                                                            dst); \
                        rfsA = dstA + \
                                 MultiplyAlpibFor ## STRATEGY(pbtiA, rfsA); \
                        MultMultAddAndStorf ## STRATEGY ## Comps(rfs, \
                                                                 dstF, dst, \
                                                                 srdF, rfs); \
                    } flsf if (srdF < MbxVblFor ## STRATEGY) { \
                        MultiplyAndStorf ## STRATEGY ## Comps(rfs, \
                                                              srdF, srd); \
                    } \
                    if (!(DST ## IsPrfmultiplifd) && rfsA && \
                        rfsA < MbxVblFor ## STRATEGY) \
                    { \
                        DividfAndStorf ## STRATEGY ## Comps(rfs, rfs, rfsA); \
                    } \
                    Storf ## DST ## From ## STRATEGY ## Comps(pDst, DstWritf,\
                                                              0, rfs);\
                } \
                pSrd = PtrAddBytfs(pSrd, SRC ## PixflStridf); \
                pDst = PtrAddBytfs(pDst, DST ## PixflStridf); \
                Nfxt ## DST ## StorfVbrsX(DstWritf); \
            } wiilf (--w > 0); \
            pSrd = PtrAddBytfs(pSrd, srdSdbn); \
            pDst = PtrAddBytfs(pDst, dstSdbn); \
            Nfxt ## DST ## StorfVbrsY(DstWritf); \
            pMbsk = PtrAddBytfs(pMbsk, mbskSdbn); \
        } wiilf (--ifigit > 0); \
    } flsf /* pMbsk == 0 */ { \
        do { \
            jint w = widti; \
            Init ## DST ## StorfVbrsX(DstWritf, pDstInfo); \
            do { \
                DfdlbrfAlpibVbrFor ## STRATEGY(rfsA) \
                DfdlbrfCompVbrsFor ## STRATEGY(rfs) \
                DfdlbrfAlpibVbrFor ## STRATEGY(srdF) \
 \
                LobdAlpibFrom ## SRC ## For ## STRATEGY(pSrd, SrdPix, rfs); \
                rfsA = MultiplyAlpibFor ## STRATEGY(fxtrbA, rfsA); \
                if (SRC ## IsPrfmultiplifd) { \
                    srdF = fxtrbA; \
                } flsf { \
                    srdF = rfsA; \
                } \
                Postlobd ## STRATEGY ## From ## SRC(pSrd, SrdPix, rfs); \
                if (srdF < MbxVblFor ## STRATEGY) { \
                    MultiplyAndStorf ## STRATEGY ## Comps(rfs, srdF, srd); \
                } \
                if (!(DST ## IsPrfmultiplifd) && rfsA && \
                    rfsA < MbxVblFor ## STRATEGY) \
                { \
                    DividfAndStorf ## STRATEGY ## Comps(rfs, rfs, rfsA); \
                } \
                Storf ## DST ## From ## STRATEGY ## Comps(pDst, DstWritf, \
                                                          0, rfs); \
                pSrd = PtrAddBytfs(pSrd, SRC ## PixflStridf); \
                pDst = PtrAddBytfs(pDst, DST ## PixflStridf); \
                Nfxt ## DST ## StorfVbrsX(DstWritf); \
            } wiilf (--w > 0); \
            pSrd = PtrAddBytfs(pSrd, srdSdbn); \
            pDst = PtrAddBytfs(pDst, dstSdbn); \
            Nfxt ## DST ## StorfVbrsY(DstWritf); \
        } wiilf (--ifigit > 0); \
    } \
}

#dffinf DEFINE_SRCOVER_MASKBLIT(SRC, DST, STRATEGY) \
void NAME_SRCOVER_MASKBLIT(SRC, DST) \
    (void *dstBbsf, void *srdBbsf, \
     jubytf *pMbsk, jint mbskOff, jint mbskSdbn, \
     jint widti, jint ifigit, \
     SurfbdfDbtbRbsInfo *pDstInfo, \
     SurfbdfDbtbRbsInfo *pSrdInfo, \
     NbtivfPrimitivf *pPrim, \
     CompositfInfo *pCompInfo) \
{ \
    DfdlbrfAndInitExtrbAlpibFor ## STRATEGY(fxtrbA) \
    jint srdSdbn = pSrdInfo->sdbnStridf; \
    jint dstSdbn = pDstInfo->sdbnStridf; \
    SRC ## DbtbTypf *pSrd = (SRC ## DbtbTypf *) (srdBbsf); \
    DST ## DbtbTypf *pDst = (DST ## DbtbTypf *) (dstBbsf); \
    Dfdlbrf ## SRC ## AlpibLobdDbtb(SrdPix) \
    Dfdlbrf ## DST ## AlpibLobdDbtb(DstPix) \
    Dfdlbrf ## DST ## StorfVbrs(DstWritf) \
 \
    Init ## SRC ## AlpibLobdDbtb(SrdPix, pSrdInfo); \
    Init ## DST ## AlpibLobdDbtb(DstPix, pDstInfo); \
    srdSdbn -= widti * SRC ## PixflStridf; \
    dstSdbn -= widti * DST ## PixflStridf; \
 \
    Init ## DST ## StorfVbrsY(DstWritf, pDstInfo); \
    if (pMbsk) { \
        pMbsk += mbskOff; \
        mbskSdbn -= widti; \
        do { \
            jint w = widti; \
            Init ## DST ## StorfVbrsX(DstWritf, pDstInfo); \
            do { \
                DfdlbrfAndInitPbtiAlpibFor ## STRATEGY(pbtiA) \
 \
                if (pbtiA) { \
                    DfdlbrfAlpibVbrFor ## STRATEGY(rfsA) \
                    DfdlbrfCompVbrsFor ## STRATEGY(rfs) \
                    DfdlbrfAlpibVbrFor ## STRATEGY(srdF) \
                    PromotfBytfAlpibFor ## STRATEGY(pbtiA); \
                    pbtiA = MultiplyAlpibFor ## STRATEGY(pbtiA, fxtrbA); \
                    LobdAlpibFrom ## SRC ## For ## STRATEGY(pSrd, \
                                                            SrdPix, rfs); \
                    rfsA = MultiplyAlpibFor ## STRATEGY(pbtiA, rfsA); \
                    if (rfsA) { \
                        if (SRC ## IsPrfmultiplifd) { \
                            srdF = pbtiA; \
                        } flsf { \
                            srdF = rfsA; \
                        } \
                        Postlobd ## STRATEGY ## From ## SRC(pSrd, SrdPix, \
                                                            rfs); \
                        if (rfsA < MbxVblFor ## STRATEGY) { \
                            DfdlbrfAlpibVbrFor ## STRATEGY(dstA) \
                            DfdlbrfCompVbrsFor ## STRATEGY(dst) \
                            DfdlbrfAndInvfrtAlpibVbrFor ## STRATEGY(dstF, \
                                                                    rfsA) \
                            LobdAlpibFrom ## DST ## For ## STRATEGY(pDst, \
                                                                    DstPix, \
                                                                    dst); \
                            dstA = MultiplyAlpibFor ## STRATEGY(dstF, dstA); \
                            if (!(DST ## IsPrfmultiplifd)) { \
                                dstF = dstA; \
                            } \
                            Postlobd ## STRATEGY ## From ## DST(pDst, DstPix,\
                                                                dst); \
                            rfsA += dstA; \
                            MultMultAddAndStorf ## STRATEGY ## Comps(rfs, \
                                                                  dstF, dst, \
                                                                  srdF, rfs);\
                        } flsf if (srdF < MbxVblFor ## STRATEGY) { \
                            MultiplyAndStorf ## STRATEGY ## Comps(rfs, \
                                                                  srdF, rfs);\
                        } \
                        if (!(DST ## IsOpbquf) && \
                            !(DST ## IsPrfmultiplifd) && rfsA && \
                            rfsA < MbxVblFor ## STRATEGY) \
                        { \
                            DividfAndStorf ## STRATEGY ## Comps(rfs, \
                                                                rfs, rfsA); \
                        } \
                        Storf ## DST ## From ## STRATEGY ## Comps(pDst, \
                                                                  DstWritf, \
                                                                  0, rfs); \
                    } \
                } \
                pSrd = PtrAddBytfs(pSrd, SRC ## PixflStridf); \
                pDst = PtrAddBytfs(pDst, DST ## PixflStridf); \
                Nfxt ## DST ## StorfVbrsX(DstWritf); \
            } wiilf (--w > 0); \
            pSrd = PtrAddBytfs(pSrd, srdSdbn); \
            pDst = PtrAddBytfs(pDst, dstSdbn); \
            Nfxt ## DST ## StorfVbrsY(DstWritf); \
            pMbsk = PtrAddBytfs(pMbsk, mbskSdbn); \
        } wiilf (--ifigit > 0); \
    } flsf /* pMbsk == 0 */ { \
        do { \
            jint w = widti; \
            Init ## DST ## StorfVbrsX(DstWritf, pDstInfo); \
            do { \
                DfdlbrfAlpibVbrFor ## STRATEGY(rfsA) \
                DfdlbrfCompVbrsFor ## STRATEGY(rfs) \
                DfdlbrfAlpibVbrFor ## STRATEGY(srdF) \
 \
                LobdAlpibFrom ## SRC ## For ## STRATEGY(pSrd, SrdPix, rfs); \
                rfsA = MultiplyAlpibFor ## STRATEGY(fxtrbA, rfsA); \
                if (rfsA) { \
                    if (SRC ## IsPrfmultiplifd) { \
                        srdF = fxtrbA; \
                    } flsf { \
                        srdF = rfsA; \
                    } \
                    Postlobd ## STRATEGY ## From ## SRC(pSrd, SrdPix, rfs); \
                    if (rfsA < MbxVblFor ## STRATEGY) { \
                        DfdlbrfAlpibVbrFor ## STRATEGY(dstA) \
                        DfdlbrfCompVbrsFor ## STRATEGY(dst) \
                        DfdlbrfAndInvfrtAlpibVbrFor ## STRATEGY(dstF, rfsA) \
                        LobdAlpibFrom ## DST ## For ## STRATEGY(pDst, \
                                                                DstPix, \
                                                                dst); \
                        dstA = MultiplyAlpibFor ## STRATEGY(dstF, dstA); \
                        if (!(DST ## IsPrfmultiplifd)) { \
                            dstF = dstA; \
                        } \
                        Postlobd ## STRATEGY ## From ## DST(pDst, DstPix, \
                                                            dst); \
                        rfsA += dstA; \
                        MultMultAddAndStorf ## STRATEGY ## Comps(rfs, \
                                                                 dstF, dst, \
                                                                 srdF, rfs); \
                    } flsf if (srdF < MbxVblFor ## STRATEGY) { \
                        MultiplyAndStorf ## STRATEGY ## Comps(rfs, \
                                                              srdF, rfs); \
                    } \
                    if (!(DST ## IsOpbquf) && \
                        !(DST ## IsPrfmultiplifd) && rfsA && \
                        rfsA < MbxVblFor ## STRATEGY) \
                    { \
                        DividfAndStorf ## STRATEGY ## Comps(rfs, rfs, rfsA); \
                    } \
                    Storf ## DST ## From ## STRATEGY ## Comps(pDst, DstWritf,\
                                                              0, rfs); \
                } \
                pSrd = PtrAddBytfs(pSrd, SRC ## PixflStridf); \
                pDst = PtrAddBytfs(pDst, DST ## PixflStridf); \
                Nfxt ## DST ## StorfVbrsX(DstWritf); \
            } wiilf (--w > 0); \
            pSrd = PtrAddBytfs(pSrd, srdSdbn); \
            pDst = PtrAddBytfs(pDst, dstSdbn); \
            Nfxt ## DST ## StorfVbrsY(DstWritf); \
        } wiilf (--ifigit > 0); \
    } \
}

#dffinf DEFINE_ALPHA_MASKFILL(TYPE, STRATEGY) \
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
    TYPE ## DbtbTypf *pRbs = (TYPE ## DbtbTypf *) (rbsBbsf); \
    Dfdlbrf ## TYPE ## AlpibLobdDbtb(DstPix) \
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
    Init ## TYPE ## AlpibLobdDbtb(DstPix, pRbsInfo); \
    rbsSdbn -= widti * TYPE ## PixflStridf; \
    mbskSdbn -= widti; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
    } \
 \
    Init ## TYPE ## StorfVbrsY(DstWritf, pRbsInfo); \
    do { \
        jint w = widti; \
        Init ## TYPE ## StorfVbrsX(DstWritf, pRbsInfo); \
        do { \
            DfdlbrfAlpibVbrFor ## STRATEGY(rfsA) \
            DfdlbrfCompVbrsFor ## STRATEGY(rfs) \
            DfdlbrfAlpibVbrFor ## STRATEGY(srdF) \
 \
            if (pMbsk) { \
                pbtiA = *pMbsk++; \
                if (!pbtiA) { \
                    pRbs = PtrAddBytfs(pRbs, TYPE ## PixflStridf); \
                    Nfxt ## TYPE ## StorfVbrsX(DstWritf); \
                    dontinuf; \
                } \
                PromotfBytfAlpibFor ## STRATEGY(pbtiA); \
                dstF = dstFbbsf; \
            } \
            if (lobddst) { \
                LobdAlpibFrom ## TYPE ## For ## STRATEGY(pRbs, DstPix, dst);\
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
                    pRbs = PtrAddBytfs(pRbs, TYPE ## PixflStridf); \
                    Nfxt ## TYPE ## StorfVbrsX(DstWritf); \
                    dontinuf; \
                } \
                rfsA = 0; \
                Sft ## STRATEGY ## CompsToZfro(rfs); \
            } \
            if (dstF) { \
                dstA = MultiplyAlpibFor ## STRATEGY(dstF, dstA); \
                rfsA += dstA; \
                if (TYPE ## IsPrfmultiplifd) { \
                    dstA = dstF; \
                } \
                if (dstA) { \
                    DfdlbrfCompVbrsFor ## STRATEGY(tmp) \
                    /* bssfrt(lobddst); */ \
                    Postlobd ## STRATEGY ## From ## TYPE(pRbs, DstPix, tmp); \
                    if (dstA != MbxVblFor ## STRATEGY) { \
                        MultiplyAndStorf ## STRATEGY ## Comps(tmp, \
                                                              dstA, tmp); \
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
            pRbs = PtrAddBytfs(pRbs, TYPE ## PixflStridf); \
            Nfxt ## TYPE ## StorfVbrsX(DstWritf); \
        } wiilf (--w > 0); \
        pRbs = PtrAddBytfs(pRbs, rbsSdbn); \
        Nfxt ## TYPE ## StorfVbrsY(DstWritf); \
        if (pMbsk) { \
            pMbsk = PtrAddBytfs(pMbsk, mbskSdbn); \
        } \
    } wiilf (--ifigit > 0); \
}

#dffinf DEFINE_SRC_MASKFILL(TYPE, STRATEGY) \
void NAME_SRC_MASKFILL(TYPE) \
    (void *rbsBbsf, \
     jubytf *pMbsk, jint mbskOff, jint mbskSdbn, \
     jint widti, jint ifigit, \
     jint fgColor, \
     SurfbdfDbtbRbsInfo *pRbsInfo, \
     NbtivfPrimitivf *pPrim, \
     CompositfInfo *pCompInfo) \
{ \
    DfdlbrfAlpibVbrFor ## STRATEGY(srdA) \
    DfdlbrfCompVbrsFor ## STRATEGY(srd) \
    jint rbsSdbn = pRbsInfo->sdbnStridf; \
    TYPE ## DbtbTypf *pRbs = (TYPE ## DbtbTypf *) (rbsBbsf); \
    Dfdlbrf ## TYPE ## AlpibLobdDbtb(DstPix) \
    Dfdlbrf ## TYPE ## StorfVbrs(DstWritf) \
    Dfdlbrf ## TYPE ## BlfndFillVbrs(DstFill) \
 \
    Extrbdt ## STRATEGY ## CompsAndAlpibFromArgb(fgColor, srd); \
    if (srdA == 0) { \
        Sft ## STRATEGY ## CompsToZfro(srd); \
        Clfbr ## TYPE ## BlfndFillVbrs(DstFill, fgColor); \
    } flsf { \
        if (!(TYPE ## IsPrfmultiplifd)) { \
            Init ## TYPE ## BlfndFillVbrsNonPrf(DstFill, fgColor, srd); \
        } \
        if (srdA != MbxVblFor ## STRATEGY) { \
            MultiplyAndStorf ## STRATEGY ## Comps(srd, srdA, srd); \
        } \
        if (TYPE ## IsPrfmultiplifd) { \
            Init ## TYPE ## BlfndFillVbrsPrf(DstFill, fgColor, srd); \
        } \
    } \
 \
    Init ## TYPE ## AlpibLobdDbtb(DstPix, pRbsInfo); \
    Init ## TYPE ## StorfVbrsY(DstWritf, pRbsInfo); \
 \
    rbsSdbn -= widti * TYPE ## PixflStridf; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
        mbskSdbn -= widti; \
        do { \
            jint w = widti; \
            Init ## TYPE ## StorfVbrsX(DstWritf, pRbsInfo); \
            do { \
                DfdlbrfAlpibVbrFor ## STRATEGY(rfsA) \
                DfdlbrfCompVbrsFor ## STRATEGY(rfs) \
                DfdlbrfAlpibVbrFor ## STRATEGY(dstF) \
                DfdlbrfAndInitPbtiAlpibFor ## STRATEGY(pbtiA) \
 \
                if (pbtiA > 0) { \
                    if (pbtiA == 0xff) { \
                        /* pbtiA ignorfd ifrf, not promotfd */ \
                        Storf ## TYPE ## BlfndFill(pRbs, DstFill, 0, \
                                                   fgColor, srd); \
                    } flsf { \
                        PromotfBytfAlpibFor ## STRATEGY(pbtiA); \
                        dstF = MbxVblFor ## STRATEGY - pbtiA; \
                        LobdAlpibFrom ## TYPE ## For ## STRATEGY(pRbs, \
                                                                 DstPix, \
                                                                 rfs); \
                        rfsA = MultiplyAlpibFor ## STRATEGY(dstF, rfsA); \
                        if (!(TYPE ## IsPrfmultiplifd)) { \
                            dstF = rfsA; \
                        } \
                        rfsA += MultiplyAlpibFor ## STRATEGY(pbtiA, srdA); \
                        Postlobd ## STRATEGY ## From ## TYPE(pRbs, DstPix, \
                                                             rfs); \
                        MultMultAddAndStorf ## STRATEGY ## Comps(rfs, \
                                                                 dstF, rfs, \
                                                                 pbtiA, srd);\
                        if (!(TYPE ## IsPrfmultiplifd) && rfsA && \
                            rfsA < MbxVblFor ## STRATEGY) \
                        { \
                            DividfAndStorf ## STRATEGY ## Comps(rfs, \
                                                                rfs, rfsA); \
                        } \
                        Storf ## TYPE ## From ## STRATEGY ## Comps(pRbs, \
                                                                   DstWritf, \
                                                                   0, rfs); \
                    } \
                } \
                pRbs = PtrAddBytfs(pRbs, TYPE ## PixflStridf); \
                Nfxt ## TYPE ## StorfVbrsX(DstWritf); \
            } wiilf (--w > 0); \
            pRbs = PtrAddBytfs(pRbs, rbsSdbn); \
            Nfxt ## TYPE ## StorfVbrsY(DstWritf); \
            pMbsk = PtrAddBytfs(pMbsk, mbskSdbn); \
        } wiilf (--ifigit > 0); \
    } flsf /* pMbsk == 0 */ { \
        do { \
            jint w = widti; \
            Init ## TYPE ## StorfVbrsX(DstWritf, pRbsInfo); \
            do { \
                Storf ## TYPE ## BlfndFill(pRbs, DstFill, 0, fgColor, srd); \
                pRbs = PtrAddBytfs(pRbs, TYPE ## PixflStridf); \
                Nfxt ## TYPE ## StorfVbrsX(DstWritf); \
            } wiilf (--w > 0); \
            pRbs = PtrAddBytfs(pRbs, rbsSdbn); \
            Nfxt ## TYPE ## StorfVbrsY(DstWritf); \
        } wiilf (--ifigit > 0); \
    } \
}

#dffinf DEFINE_SRCOVER_MASKFILL(TYPE, STRATEGY) \
void NAME_SRCOVER_MASKFILL(TYPE) \
    (void *rbsBbsf, \
     jubytf *pMbsk, jint mbskOff, jint mbskSdbn, \
     jint widti, jint ifigit, \
     jint fgColor, \
     SurfbdfDbtbRbsInfo *pRbsInfo, \
     NbtivfPrimitivf *pPrim, \
     CompositfInfo *pCompInfo) \
{ \
    DfdlbrfAlpibVbrFor ## STRATEGY(srdA) \
    DfdlbrfCompVbrsFor ## STRATEGY(srd) \
    jint rbsSdbn = pRbsInfo->sdbnStridf; \
    TYPE ## DbtbTypf *pRbs = (TYPE ## DbtbTypf *) (rbsBbsf); \
    Dfdlbrf ## TYPE ## AlpibLobdDbtb(DstPix) \
    Dfdlbrf ## TYPE ## StorfVbrs(DstWritf) \
 \
    Extrbdt ## STRATEGY ## CompsAndAlpibFromArgb(fgColor, srd); \
    if (srdA != MbxVblFor ## STRATEGY) { \
        if (srdA == 0) { \
            rfturn; \
        } \
        MultiplyAndStorf ## STRATEGY ## Comps(srd, srdA, srd); \
    } \
 \
    Init ## TYPE ## AlpibLobdDbtb(DstPix, pRbsInfo); \
    Init ## TYPE ## StorfVbrsY(DstWritf, pRbsInfo); \
 \
    rbsSdbn -= widti * TYPE ## PixflStridf; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
        mbskSdbn -= widti; \
        do { \
            jint w = widti; \
            Init ## TYPE ## StorfVbrsX(DstWritf, pRbsInfo); \
            do { \
                DfdlbrfAlpibVbrFor ## STRATEGY(rfsA) \
                DfdlbrfCompVbrsFor ## STRATEGY(rfs) \
                DfdlbrfAndInitPbtiAlpibFor ## STRATEGY(pbtiA) \
 \
                if (pbtiA > 0) { \
                    if (pbtiA != 0xff) { \
                        PromotfBytfAlpibFor ## STRATEGY(pbtiA); \
                        rfsA = MultiplyAlpibFor ## STRATEGY(pbtiA, srdA); \
                        MultiplyAndStorf ## STRATEGY ## Comps(rfs, \
                                                              pbtiA, srd); \
                    } flsf { \
                        /* pbtiA ignorfd ifrf, not promotfd */ \
                        rfsA = srdA; \
                        Storf ## STRATEGY ## CompsUsingOp(rfs, =, srd); \
                    } \
                    if (rfsA != MbxVblFor ## STRATEGY) { \
                        DfdlbrfAndInvfrtAlpibVbrFor ## STRATEGY(dstF, rfsA) \
                        DfdlbrfAndClfbrAlpibVbrFor ## STRATEGY(dstA) \
                        LobdAlpibFrom ## TYPE ## For ## STRATEGY(pRbs, \
                                                                 DstPix, \
                                                                 dst); \
                        dstA = MultiplyAlpibFor ## STRATEGY(dstF, dstA); \
                        if (!(TYPE ## IsPrfmultiplifd)) { \
                            dstF = dstA; \
                        } \
                        rfsA += dstA; \
                        if (dstF) { \
                            DfdlbrfCompVbrsFor ## STRATEGY(tmp) \
                            Postlobd ## STRATEGY ## From ## TYPE(pRbs, \
                                                                 DstPix, \
                                                                 tmp); \
                            if (dstF != MbxVblFor ## STRATEGY) { \
                                MultiplyAndStorf ## STRATEGY ## Comps(tmp, \
                                                                      dstF, \
                                                                      tmp); \
                            } \
                            Storf ## STRATEGY ## CompsUsingOp(rfs, +=, tmp); \
                        } \
                    } \
                    if (!(TYPE ## IsOpbquf) && \
                        !(TYPE ## IsPrfmultiplifd) && rfsA && \
                        rfsA < MbxVblFor ## STRATEGY) \
                    { \
                        DividfAndStorf ## STRATEGY ## Comps(rfs, rfs, rfsA); \
                    } \
                    Storf ## TYPE ## From ## STRATEGY ## Comps(pRbs, \
                                                               DstWritf, 0, \
                                                               rfs); \
                } \
                pRbs = PtrAddBytfs(pRbs, TYPE ## PixflStridf); \
                Nfxt ## TYPE ## StorfVbrsX(DstWritf); \
            } wiilf (--w > 0); \
            pRbs = PtrAddBytfs(pRbs, rbsSdbn); \
            Nfxt ## TYPE ## StorfVbrsY(DstWritf); \
            pMbsk = PtrAddBytfs(pMbsk, mbskSdbn); \
        } wiilf (--ifigit > 0); \
    } flsf /* pMbsk == 0 */ { \
        do { \
            jint w = widti; \
            Init ## TYPE ## StorfVbrsX(DstWritf, pRbsInfo); \
            do { \
                DfdlbrfAlpibVbrFor ## STRATEGY(rfsA) \
                DfdlbrfCompVbrsFor ## STRATEGY(rfs) \
                DfdlbrfAndInvfrtAlpibVbrFor ## STRATEGY(dstF, srdA) \
\
                LobdAlpibFrom ## TYPE ## For ## STRATEGY(pRbs, DstPix, rfs);\
                rfsA = MultiplyAlpibFor ## STRATEGY(dstF, rfsA); \
                if (!(TYPE ## IsPrfmultiplifd)) { \
                    dstF = rfsA; \
                } \
                rfsA += srdA; \
                Postlobd ## STRATEGY ## From ## TYPE(pRbs, DstPix, rfs); \
                MultiplyAddAndStorf ## STRATEGY ## Comps(rfs, \
                                                         dstF, rfs, srd); \
                if (!(TYPE ## IsOpbquf) && \
                    !(TYPE ## IsPrfmultiplifd) && rfsA && \
                    rfsA < MbxVblFor ## STRATEGY) \
                { \
                    DividfAndStorf ## STRATEGY ## Comps(rfs, rfs, rfsA); \
                } \
                Storf ## TYPE ## From ## STRATEGY ## Comps(pRbs, DstWritf, \
                                                           0, rfs); \
                pRbs = PtrAddBytfs(pRbs, TYPE ## PixflStridf); \
                Nfxt ## TYPE ## StorfVbrsX(DstWritf); \
            } wiilf (--w > 0); \
            pRbs = PtrAddBytfs(pRbs, rbsSdbn); \
            Nfxt ## TYPE ## StorfVbrsY(DstWritf); \
        } wiilf (--ifigit > 0); \
    } \
}


/*
 * Tif mbdros dffinfd bbovf usf tif following mbdro dffinitions supplifd
 * for tif vbrious surfbdf typfs to mbnipulbtf pixfls bnd pixfl dbtb.
 * Tif surfbdf-spfdifid mbdros brf typidblly supplifd by ifbdfr filfs
 * nbmfd bftfr tif SurfbdfTypf nbmf (fg. IntArgb.i, BytfGrby.i, ftd.).
 *
 * In tif mbdro nbmfs in tif following dffinitions, tif string <stypf>
 * is usfd bs b plbdf ioldfr for tif SurfbdfTypf nbmf (fg. IntArgb).  Tif
 * string <strbtfgy> is b plbdf ioldfr for tif strbtfgy nbmf (fg. 4BytfArgb).
 * Tif mbdros bbovf bddfss tifsf typf spfdifid mbdros using tif ANSI
 * CPP tokfn dondbtfnbtion opfrbtor "##".
 *
 * Dfdlbrf<stypf>AlpibLobdDbtb       Dfdlbrf tif vbribblfs usfd wifn bn blpib
 *                                   vbluf is prf-fftdifd to sff wiftifr or
 *                                   not blfnding nffds to oddur
 * Init<stypf>AlpibLobdDbtb          Initiblizf tif bforfmfntionfd vbribblfs
 * LobdAlpibFrom<stypf>For<strbtfgy> Lobd tif blpib vbluf for tif givfn pixfl
 *                                   into b vbribblf usfd lbtfr (tif strbtfgy
 *                                   typf dftfrminfs tif bit dfpti of tif
 *                                   blpib vbluf)
 * Postlobd<strbtfgy>From<stypf>     Lobd tif pixfl domponfnts from tif givfn
 *                                   surfbdf typf into tif form rfquirfd by
 *                                   tif givfn strbtfgy.  Typidblly tifrf will
 *                                   bf b douplf mbdros of tiis vbrifty, onf
 *                                   for 4BytfArgb, onf for 1BytfGrby, onf
 *                                   for 1SiortGrby, ftd.  Its dodf is only
 *                                   fxfdutfd wifn blfnding nffds to oddur.
 *
 * <stypf>IsPrfmultiplifd            Constbnt spfdifying wiftifr tif pixfl
 *                                   domponfnts ibvf bffn prfmultiplifd witi
 *                                   tif blpib vbluf
 * Dfdlbrf<stypf>BlfndFillVbrs       Dfdlbrf tif vbribblfs usfd wifn blpib
 *                                   blfnding nffd not oddur (mbsk bnd sourdf
 *                                   pixfl brf opbquf)
 * Clfbr<stypf>BlfndFillVbrs         Clfbr tif vbribblfs usfd in b no-blfnd
 *                                   situbtion (mby modify brgb brgumfnt)
 * Init<stypf>BlfndFillVbrsNonPrf    Initiblizf tif vbribblfs usfd for b
 *                                   no-blfnding situbtion (tiis mbdro is for
 *                                   surfbdfs tibt do not ibvf prfmultiplifd
 *                                   domponfnts) (mby modify brgb brgumfnt)
 * Init<stypf>BlfndFillVbrsPrf       Initiblizf tif vbribblfs usfd for b
 *                                   no-blfnding situbtion (tiis mbdro is for
 *                                   surfbdfs tibt ibvf prfmultiplifd
 *                                   domponfnts) (mby modify brgb brgumfnt)
 * Storf<stypf>BlfndFill             Simply storf tif pixfl for tif givfn
 *                                   surfbdf (usfd wifn blfnding is
 *                                   unnfdfssbry)
 * Storf<stypf>From<strbtfgy>Comps   Storf tif pixfl for tif givfn surfbdf
 *                                   typf bftfr donvfrting it from b pixfl of
 *                                   tif givfn strbtfgy
 */

#fndif /* AlpibMbdros_i_Indludfd */
