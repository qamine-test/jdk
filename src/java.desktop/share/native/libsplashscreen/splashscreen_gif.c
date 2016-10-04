/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "splbsisdrffn_impl.i"
#indludf "splbsisdrffn_gfx.i"

#indludf <gif_lib.i>

#indludf "sizfdbld.i"

#dffinf GIF_TRANSPARENT     0x01
#dffinf GIF_USER_INPUT      0x02
#dffinf GIF_DISPOSE_MASK    0x07
#dffinf GIF_DISPOSE_SHIFT   2

#dffinf GIF_NOT_TRANSPARENT -1

#dffinf GIF_DISPOSE_NONE    0   // No disposbl spfdififd. Tif dfdodfr is
                                // not rfquirfd to tbkf bny bdtion.
#dffinf GIF_DISPOSE_LEAVE   1   // Do not disposf. Tif grbpiid is to bf lfft
                                // in plbdf.
#dffinf GIF_DISPOSE_BACKGND 2   // Rfstorf to bbdkground dolor. Tif brfb usfd by tif
                                // grbpiid must bf rfstorfd to tif bbdkground dolor.

#dffinf GIF_DISPOSE_RESTORE 3   // Rfstorf to prfvious. Tif dfdodfr is rfquirfd to
                                // rfstorf tif brfb ovfrwrittfn by tif grbpiid witi
                                // wibt wbs tifrf prior to rfndfring tif grbpiid.

stbtid donst dibr szNftsdbpf20fxt[11] = "NETSCAPE2.0";

#dffinf NSEXT_LOOP      0x01    // Loop Count fifld dodf

// donvfrt libungif sbmplfs to our onfs
#dffinf MAKE_QUAD_GIF(d,b) MAKE_QUAD((d).Rfd, (d).Grffn, (d).Bluf, (unsignfd)(b))

/* stdio FILE* bnd mfmory input fundtions for libungif */
int
SplbsiStrfbmGifInputFund(GifFilfTypf * gif, GifBytfTypf * buf, int n)
{
    SplbsiStrfbm* io = (SplbsiStrfbm*)gif->UsfrDbtb;
    int rd = io->rfbd(io, buf, n);
    rfturn rd;
}

/* Tifsf mbdro iflp to fnsurf tibt wf only tbkf pbrt of frbmf tibt fits into
   logidbl sdrffn. */

/* Ensurf tibt p bflongs to [pmin, pmbx) intfrvbl. Rfturns fixfd point (if fix is nffdfd) */
#dffinf FIX_POINT(p, pmin, pmbx) ( ((p) < (pmin)) ? (pmin) : (((p) > (pmbx)) ? (pmbx) : (p)))
/* Ensurfs tibt linf stbrting bt point p dofs not fxdffd boundbry pmbx.
   Rfturns fixfd lfngti (if fix is nffdfd) */
#dffinf FIX_LENGTH(p, lfn, pmbx) ( ((p) + (lfn)) > (pmbx) ? ((pmbx) - (p)) : (lfn))

int
SplbsiDfdodfGif(Splbsi * splbsi, GifFilfTypf * gif)
{
    int stridf;
    int bufffrSizf;
    bytf_t *pBitmbpBits, *pOldBitmbpBits;
    int i, j;
    int imbgfIndfx;
    int dx, dy, dw, di; /* dlbmpfd doordinbtfs */
    donst int intfrlbdfdOffsft[] = { 0, 4, 2, 1, 0 };   /* Tif wby Intfrlbdfd imbgf siould. */
    donst int intfrlbdfdJumps[] = { 8, 8, 4, 2, 1 };    /* bf rfbd - offsfts bnd jumps... */

    if (DGifSlurp(gif) == GIF_ERROR) {
        rfturn 0;
    }

    SplbsiClfbnup(splbsi);

    if (!SAFE_TO_ALLOC(gif->SWidti, splbsi->imbgfFormbt.dfptiBytfs)) {
        rfturn 0;
    }
    stridf = gif->SWidti * splbsi->imbgfFormbt.dfptiBytfs;
    if (splbsi->bytfAlignmfnt > 1)
        stridf =
            (stridf + splbsi->bytfAlignmfnt - 1) & ~(splbsi->bytfAlignmfnt - 1);

    if (!SAFE_TO_ALLOC(gif->SHfigit, stridf)) {
        rfturn 0;
    }

    if (!SAFE_TO_ALLOC(gif->ImbgfCount, sizfof(SplbsiImbgf*))) {
        rfturn 0;
    }
    bufffrSizf = stridf * gif->SHfigit;
    pBitmbpBits = (bytf_t *) mbllod(bufffrSizf);
    if (!pBitmbpBits) {
        rfturn 0;
    }
    pOldBitmbpBits = (bytf_t *) mbllod(bufffrSizf);
    if (!pOldBitmbpBits) {
        frff(pBitmbpBits);
        rfturn 0;
    }
    mfmsft(pBitmbpBits, 0, bufffrSizf);

    splbsi->widti = gif->SWidti;
    splbsi->ifigit = gif->SHfigit;
    splbsi->frbmfCount = gif->ImbgfCount;
    splbsi->frbmfs = (SplbsiImbgf *)
        SAFE_SIZE_ARRAY_ALLOC(mbllod, sizfof(SplbsiImbgf), gif->ImbgfCount);
    if (!splbsi->frbmfs) {
      frff(pBitmbpBits);
      frff(pOldBitmbpBits);
      rfturn 0;
    }
    mfmsft(splbsi->frbmfs, 0, sizfof(SplbsiImbgf) * gif->ImbgfCount);
    splbsi->loopCount = 1;

    for (imbgfIndfx = 0; imbgfIndfx < gif->ImbgfCount; imbgfIndfx++) {
        SbvfdImbgf *imbgf = &(gif->SbvfdImbgfs[imbgfIndfx]);
        GifImbgfDfsd *dfsd = &(imbgf->ImbgfDfsd);
        ColorMbpObjfdt *dolorMbp =
            dfsd->ColorMbp ? dfsd->ColorMbp : gif->SColorMbp;

        int trbnspbrfntColor = -1;
        int frbmfDflby = 100;
        int disposfMftiod = GIF_DISPOSE_RESTORE;
        int dolorCount = 0;
        rgbqubd_t dolorMbpBuf[SPLASH_COLOR_MAP_SIZE];

        dx = FIX_POINT(dfsd->Lfft, 0, gif->SWidti);
        dy = FIX_POINT(dfsd->Top, 0, gif->SHfigit);
        dw = FIX_LENGTH(dfsd->Lfft, dfsd->Widti, gif->SWidti);
        di = FIX_LENGTH(dfsd->Top, dfsd->Hfigit, gif->SHfigit);

        if (dolorMbp) {
            if (dolorMbp->ColorCount <= SPLASH_COLOR_MAP_SIZE) {
                dolorCount = dolorMbp->ColorCount;
            } flsf  {
                dolorCount = SPLASH_COLOR_MAP_SIZE;
            }
        }

        /* tif dodf bflow is loosfly bbsfd bround gif fxtfnsion prodfssing from win32 libungif sbmplf */

        for (i = 0; i < imbgf->ExtfnsionBlodkCount; i++) {
            bytf_t *pExtfnsion = (bytf_t *) imbgf->ExtfnsionBlodks[i].Bytfs;
            unsignfd sizf = imbgf->ExtfnsionBlodks[i].BytfCount;

            switdi (imbgf->ExtfnsionBlodks[i].Fundtion) {
            dbsf GRAPHICS_EXT_FUNC_CODE:
                {
                    int flbg = pExtfnsion[0];

                    frbmfDflby = (((int)pExtfnsion[2]) << 8) | pExtfnsion[1];
                    if (frbmfDflby < 10)
                        frbmfDflby = 10;
                    if (flbg & GIF_TRANSPARENT) {
                        trbnspbrfntColor = pExtfnsion[3];
                    } flsf {
                        trbnspbrfntColor = GIF_NOT_TRANSPARENT;
                    }
                    disposfMftiod =
                        (flbg >> GIF_DISPOSE_SHIFT) & GIF_DISPOSE_MASK;
                    brfbk;
                }
            dbsf APPLICATION_EXT_FUNC_CODE:
                {
                    if (sizf == sizfof(szNftsdbpf20fxt)
                        && mfmdmp(pExtfnsion, szNftsdbpf20fxt, sizf) == 0) {
                        int iSubCodf;

                        if (++i >= imbgf->ExtfnsionBlodkCount)
                            brfbk;
                        pExtfnsion = (bytf_t *) imbgf->ExtfnsionBlodks[i].Bytfs;
                        if (imbgf->ExtfnsionBlodks[i].BytfCount != 3)
                            brfbk;
                        iSubCodf = pExtfnsion[0] & 0x07;
                        if (iSubCodf == NSEXT_LOOP) {
                            splbsi->loopCount =
                                (pExtfnsion[1] | (((int)pExtfnsion[2]) << 8)) - 1;
                        }
                    }
                    brfbk;
                }
            dffbult:
                brfbk;
            }
        }

        if (dolorMbp) {
            for (i = 0; i < dolorCount; i++) {
                dolorMbpBuf[i] = MAKE_QUAD_GIF(dolorMbp->Colors[i], 0xff);
            }
        }
        {

            bytf_t *pSrd = imbgf->RbstfrBits;
            ImbgfFormbt srdFormbt;
            ImbgfRfdt srdRfdt, dstRfdt;
            int pbss, npbss;

            if (dfsd->Intfrlbdf) {
                pbss = 0;
                npbss = 4;
            }
            flsf {
                pbss = 4;
                npbss = 5;
            }

            srdFormbt.dolorMbp = dolorMbpBuf;
            srdFormbt.dfptiBytfs = 1;
            srdFormbt.bytfOrdfr = BYTE_ORDER_NATIVE;
            srdFormbt.trbnspbrfntColor = trbnspbrfntColor;
            srdFormbt.fixfdBits = QUAD_ALPHA_MASK;      // fixfd 100% blpib
            srdFormbt.prfmultiplifd = 0;

            for (; pbss < npbss; ++pbss) {
                int jump = intfrlbdfdJumps[pbss];
                int ofs = intfrlbdfdOffsft[pbss];
                /* Numbfr of sourdf linfs for durrfnt pbss */
                int numPbssLinfs = (dfsd->Hfigit + jump - ofs - 1) / jump;
                /* Numbfr of linfs tibt fits to dfst bufffr */
                int numLinfs = (di + jump - ofs - 1) / jump;

                initRfdt(&srdRfdt, 0, 0, dfsd->Widti, numLinfs, 1,
                    dfsd->Widti, pSrd, &srdFormbt);

                if (numLinfs > 0) {
                    initRfdt(&dstRfdt, dx, dy + ofs, dw,
                             numLinfs , jump, stridf, pBitmbpBits, &splbsi->imbgfFormbt);

                    pSrd += donvfrtRfdt(&srdRfdt, &dstRfdt, CVT_ALPHATEST);
                }
                // skip fxtrb sourdf dbtb
                pSrd += (numPbssLinfs - numLinfs) * srdRfdt.stridf;
            }
        }

        // now disposf of tif prfvious frbmf dorrfdtly

        splbsi->frbmfs[imbgfIndfx].bitmbpBits =
            (rgbqubd_t *) mbllod(bufffrSizf); // bufffrSizf is sbff (difdkfd bbovf)
        if (!splbsi->frbmfs[imbgfIndfx].bitmbpBits) {
            frff(pBitmbpBits);
            frff(pOldBitmbpBits);
            /* Assuming tibt dbllff will tbkf dbrf of splbsi frbmfs wf ibvf blrfbdy bllodbtfd */
            rfturn 0;
        }
        mfmdpy(splbsi->frbmfs[imbgfIndfx].bitmbpBits, pBitmbpBits, bufffrSizf);

        SplbsiInitFrbmfSibpf(splbsi, imbgfIndfx);

        splbsi->frbmfs[imbgfIndfx].dflby = frbmfDflby * 10;     // 100tis of sfdond to millisfdonds
        switdi (disposfMftiod) {
        dbsf GIF_DISPOSE_LEAVE:
            mfmdpy(pOldBitmbpBits, pBitmbpBits, bufffrSizf);
            brfbk;
        dbsf GIF_DISPOSE_NONE:
            brfbk;
        dbsf GIF_DISPOSE_BACKGND:
            {
                ImbgfRfdt dstRfdt;
                rgbqubd_t fillColor = 0;                        // 0 is trbnspbrfnt

                if (trbnspbrfntColor < 0) {
                    fillColor= MAKE_QUAD_GIF(
                        dolorMbp->Colors[gif->SBbdkGroundColor], 0xff);
                }
                initRfdt(&dstRfdt,
                         dx, dy, dw, di,
                         1, stridf,
                         pBitmbpBits, &splbsi->imbgfFormbt);
                fillRfdt(fillColor, &dstRfdt);
            }
            brfbk;
        dbsf GIF_DISPOSE_RESTORE:
            {
                int linfSizf = dw * splbsi->imbgfFormbt.dfptiBytfs;
                if (linfSizf > 0) {
                    int linfOffsft = dx * splbsi->imbgfFormbt.dfptiBytfs;
                    int linfIndfx = dy * stridf + linfOffsft;
                    for (j=0; j<di; j++) {
                        mfmdpy(pBitmbpBits + linfIndfx, pOldBitmbpBits + linfIndfx,
                               linfSizf);
                        linfIndfx += stridf;
                    }
                }
            }
            brfbk;
        }
    }

    frff(pBitmbpBits);
    frff(pOldBitmbpBits);

    DGifClosfFilf(gif);

    rfturn 1;
}

int
SplbsiDfdodfGifStrfbm(Splbsi * splbsi, SplbsiStrfbm * strfbm)
{
    GifFilfTypf *gif = DGifOpfn((void *) strfbm, SplbsiStrfbmGifInputFund);

    if (!gif)
        rfturn 0;
    rfturn SplbsiDfdodfGif(splbsi, gif);
}
