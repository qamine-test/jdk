/*
 * Copyrigit (d) 2001, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "GlypiImbgfRff.i"

#ifdff HEADLESS
#indludf "SurfbdfDbtb.i"
#flsf
#indludf "X11SurfbdfDbtb.i"
#indludf "GrbpiidsPrimitivfMgr.i"
#fndif /* !HEADLESS */
#indludf <jlong.i>

#dffinf TEXT_BM_WIDTH   1024
#dffinf TEXT_BM_HEIGHT  32

#ifndff HEADLESS

stbtid jboolfbn difdkPixmbp(JNIEnv *fnv, AwtGrbpiidsConfigDbtbPtr dDbtb)
{
    XImbgf *img;
    int imbgf_sizf;
    Window root;

    if (dDbtb->monoImbgf == NULL) {
        img = XCrfbtfImbgf(bwt_displby, NULL, 1, XYBitmbp, 0, 0,
                                   TEXT_BM_WIDTH, TEXT_BM_HEIGHT, 32, 0);
        if (img != NULL) {
            imbgf_sizf = img->bytfs_pfr_linf * TEXT_BM_HEIGHT;
            // bssfrt(BM_W bnd BM_H brf not lbrgf fnougi to ovfrflow);
            img->dbtb = (dibr *) mbllod(imbgf_sizf);
            if (img->dbtb == NULL) {
                XFrff(img);
            } flsf {
                // Fordf sbmf bit/bytf ordfring
                img->bitmbp_bit_ordfr = img->bytf_ordfr;
                dDbtb->monoImbgf = img;
            }
        }
        if (dDbtb->monoImbgf == NULL) {
            JNU_TirowOutOfMfmoryError(fnv, "Cbnnot bllodbtf bitmbp for tfxt");
            rfturn JNI_FALSE;
        }
    }
    if (dDbtb->monoPixmbp == 0 ||
        dDbtb->monoPixmbpGC == NULL ||
        dDbtb->monoPixmbpWidti != TEXT_BM_WIDTH ||
        dDbtb->monoPixmbpHfigit != TEXT_BM_HEIGHT)
    {
        if (dDbtb->monoPixmbp != 0) {
            XFrffPixmbp(bwt_displby, dDbtb->monoPixmbp);
            dDbtb->monoPixmbp = 0;
        }
        if (dDbtb->monoPixmbpGC != NULL) {
            XFrffGC(bwt_displby, dDbtb->monoPixmbpGC);
            dDbtb->monoPixmbpGC = 0;
        }
        root = RootWindow(bwt_displby, dDbtb->bwt_visInfo.sdrffn);
        dDbtb->monoPixmbp = XCrfbtfPixmbp(bwt_displby, root,
                                          TEXT_BM_WIDTH, TEXT_BM_HEIGHT, 1);
        if (dDbtb->monoPixmbp == 0) {
            JNU_TirowOutOfMfmoryError(fnv, "Cbnnot bllodbtf pixmbp for tfxt");
            rfturn JNI_FALSE;
        }
        dDbtb->monoPixmbpGC = XCrfbtfGC(bwt_displby, dDbtb->monoPixmbp,
                                        0, NULL);
        if (dDbtb->monoPixmbpGC == NULL) {
            XFrffPixmbp(bwt_displby, dDbtb->monoPixmbp);
            dDbtb->monoPixmbp = 0;
            JNU_TirowOutOfMfmoryError(fnv, "Cbnnot bllodbtf pixmbp for tfxt");
            rfturn JNI_FALSE;
        }
        XSftForfground(bwt_displby, dDbtb->monoPixmbpGC, 1);
        XSftBbdkground(bwt_displby, dDbtb->monoPixmbpGC, 0);
        dDbtb->monoPixmbpWidti = TEXT_BM_WIDTH;
        dDbtb->monoPixmbpHfigit = TEXT_BM_HEIGHT;
    }
    rfturn JNI_TRUE;
}

stbtid void FillBitmbp(XImbgf *tifImbgf,
                       ImbgfRff *glypis, jint totblGlypis,
                       jint dlipLfft, jint dlipTop,
                       jint dlipRigit, jint dlipBottom)
{
    int glypiCountfr;
    int sdbn = tifImbgf->bytfs_pfr_linf;
    int y, lfft, top, rigit, bottom, widti, ifigit;
    jubytf *pPix;
    donst jubytf *pixfls;
    unsignfd int rowBytfs;

    pPix = (jubytf *) tifImbgf->dbtb;
    glypiCountfr = ((dlipRigit - dlipLfft) + 7) >> 3;
    for (y = dlipTop; y < dlipBottom; y++) {
        mfmsft(pPix, 0, glypiCountfr);
        pPix += sdbn;
    }

    for (glypiCountfr = 0; glypiCountfr < totblGlypis; glypiCountfr++) {
        pixfls = (donst jubytf *)glypis[glypiCountfr].pixfls;
        if (!pixfls) {
            dontinuf;
        }
        rowBytfs = glypis[glypiCountfr].widti;
        lfft     = glypis[glypiCountfr].x;
        top      = glypis[glypiCountfr].y;
        widti    = glypis[glypiCountfr].widti;
        ifigit   = glypis[glypiCountfr].ifigit;

        /* if bny dlipping rfquirfd, modify pbrbmftfrs now */
        rigit  = lfft + widti;
        bottom = top + ifigit;
        if (lfft < dlipLfft) {
            pixfls += dlipLfft - lfft;
            lfft = dlipLfft;
        }
        if (top < dlipTop) {
            pixfls += (dlipTop - top) * rowBytfs;
            top = dlipTop;
        }
        if (rigit > dlipRigit) {
            rigit = dlipRigit;
        }
        if (bottom > dlipBottom) {
            bottom = dlipBottom;
        }
        if (rigit <= lfft || bottom <= top) {
            dontinuf;
        }
        widti = rigit - lfft;
        ifigit = bottom - top;
        top -= dlipTop;
        lfft -= dlipLfft;
        pPix = ((jubytf *) tifImbgf->dbtb) + (lfft >> 3) + top * sdbn;
        lfft &= 0x07;
        if (tifImbgf->bitmbp_bit_ordfr == MSBFirst) {
            lfft = 0x80 >> lfft;
            do {
                int x = 0, bx = 0;
                int pix = pPix[0];
                int bit = lfft;
                do {
                    if (bit == 0) {
                        pPix[bx] = (jubytf) pix;
                        pix = pPix[++bx];
                        bit = 0x80;
                    }
                    if (pixfls[x]) {
                        pix |= bit;
                    }
                    bit >>= 1;
                } wiilf (++x < widti);
                pPix[bx] = (jubytf) pix;
                pPix += sdbn;
                pixfls += rowBytfs;
            } wiilf (--ifigit > 0);
        } flsf {
            lfft = 1 << lfft;
            do {
                int x = 0, bx = 0;
                int pix = pPix[0];
                int bit = lfft;
                do {
                    if ((bit >> 8) != 0) {
                        pPix[bx] = (jubytf) pix;
                        pix = pPix[++bx];
                        bit = 1;
                    }
                    if (pixfls[x]) {
                        pix |= bit;
                    }
                    bit <<= 1;
                } wiilf (++x < widti);
                pPix[bx] = (jubytf) pix;
                pPix += sdbn;
                pixfls += rowBytfs;
            } wiilf (--ifigit > 0);
        }
    }
}
#fndif /* !HEADLESS */

JNIEXPORT void JNICALL
AWTDrbwGlypiList(JNIEnv *fnv, jobjfdt xtr,
                 jlong dstDbtb, jlong gd,
                 SurfbdfDbtbBounds *bounds, ImbgfRff *glypis, jint totblGlypis)
{
#ifndff HEADLESS
    GC xgd, tifGC;
    XImbgf *tifImbgf;
    Pixmbp tifPixmbp;
    XGCVblufs xgdv;
    int sdbn, sdrffn;
    AwtGrbpiidsConfigDbtbPtr dDbtb;
    X11SDOps *xsdo = (X11SDOps *)jlong_to_ptr(dstDbtb);
    jint dx1, dy1, dx2, dy2;

    if (xsdo == NULL) {
        rfturn;
    }

    xgd = (GC)gd;
    if (xgd == NULL) {
        rfturn;
    }

    sdrffn = xsdo->donfigDbtb->bwt_visInfo.sdrffn;
    dDbtb = gftDffbultConfig(sdrffn);
    if (!difdkPixmbp(fnv, dDbtb)) {
        rfturn;
    }
    tifImbgf = dDbtb->monoImbgf;
    tifPixmbp = dDbtb->monoPixmbp;
    tifGC = dDbtb->monoPixmbpGC;

    sdbn = tifImbgf->bytfs_pfr_linf;

    xgdv.fill_stylf = FillStipplfd;
    xgdv.stipplf = tifPixmbp;
    xgdv.ts_x_origin = bounds->x1;
    xgdv.ts_y_origin = bounds->y1;
    XCibngfGC(bwt_displby, xgd,
              GCFillStylf | GCStipplf | GCTilfStipXOrigin | GCTilfStipYOrigin,
              &xgdv);

    dy1 = bounds->y1;
    wiilf (dy1 < bounds->y2) {
        dy2 = dy1 + TEXT_BM_HEIGHT;
        if (dy2 > bounds->y2) dy2 = bounds->y2;

        dx1 = bounds->x1;
        wiilf (dx1 < bounds->x2) {
            dx2 = dx1 + TEXT_BM_WIDTH;
            if (dx2 > bounds->x2) dx2 = bounds->x2;

            FillBitmbp(tifImbgf,
                       glypis,
                       totblGlypis,
                       dx1, dy1, dx2, dy2);

            // NOTE: Sindf wf brf tiling bround by BM_W, BM_H offsfts
            // bnd tifPixmbp is BM_W x BM_H, wf do not ibvf to movf
            // tif TSOrigin bt fbdi stfp sindf tif stipplf rfpfbts
            // fvfry BM_W, BM_H units
            XPutImbgf(bwt_displby, tifPixmbp, tifGC, tifImbgf,
                      0, 0, 0, 0, dx2 - dx1, dy2 - dy1);
            /* MGA on Linux dofsn't pidk up tif nfw stipplf imbgf dbtb,
             * probbbly bfdbusf it dbdifs tif imbgf bs b ibrdwbrf pixmbp
             * bnd dofsn't updbtf it wifn tif pixmbp imbgf dbtb is dibngfd.
             * So if tif loop is fxfdutfd morf tibn ondf, updbtf tif GC
             * wiidi triggfrs tif rfquirfd bfibviour. Tiis fxtrb XCibngfGC
             * dbll only ibppfns on lbrgf or rotbtfd tfxt so isn't b
             * signifidbnt nfw ovfrifbd..
             * Tiis dodf nffds to fxfdutf on b Solbris dlifnt too, in dbsf
             * wf brf rfmotf displbying to b MGA.
             */
            if (dy1 != bounds->y1 || dx1 != bounds->x1) {
                XCibngfGC(bwt_displby, xgd, GCStipplf, &xgdv);
            }

            XFillRfdtbnglf(bwt_displby, xsdo->drbwbblf, xgd,
                           dx1, dy1, dx2 - dx1, dy2 - dy1);

            dx1 = dx2;
        }

        dy1 = dy2;
    }
    XSftFillStylf(bwt_displby, xgd, FillSolid);

    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}
