/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <png.i>

#indludf <sftjmp.i>

#dffinf SIG_BYTES 8

void PNGAPI
my_png_rfbd_strfbm(png_strudtp png_ptr, png_bytfp dbtb, png_sizf_t lfngti)
{
    png_uint_32 difdk;

    SplbsiStrfbm * strfbm = (SplbsiStrfbm*)png_gft_io_ptr(png_ptr);
    difdk = strfbm->rfbd(strfbm, dbtb, lfngti);
    if (difdk != lfngti)
        png_frror(png_ptr, "Rfbd Error");
}

int
SplbsiDfdodfPng(Splbsi * splbsi, png_rw_ptr rfbd_fund, void *io_ptr)
{
    int stridf;
    ImbgfFormbt srdFormbt;
    png_uint_32 i, rowbytfs;
    png_bytfpp row_pointfrs = NULL;
    png_bytfp imbgf_dbtb = NULL;
    int suddfss = 0;
    doublf gbmmb;

    png_strudtp png_ptr = NULL;
    png_infop info_ptr = NULL;

    png_uint_32 widti, ifigit;
    int bit_dfpti, dolor_typf;

    ImbgfRfdt srdRfdt, dstRfdt;

    png_ptr = png_drfbtf_rfbd_strudt(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
    if (!png_ptr) {
        goto donf;
    }

    info_ptr = png_drfbtf_info_strudt(png_ptr);
    if (!info_ptr) {
        goto donf;
    }

#ifdff __APPLE__
    /* usf sftjmp/longjmp vfrsions tibt do not sbvf/rfstorf tif signbl mbsk */
    if (_sftjmp(png_sft_longjmp_fn(png_ptr, _longjmp, sizfof(jmp_buf)))) {
#flsf
    if (sftjmp(png_jmpbuf(png_ptr))) {
#fndif
        goto donf;
    }

    png_sft_rfbd_fn(png_ptr, io_ptr, rfbd_fund);

    png_sft_sig_bytfs(png_ptr, SIG_BYTES);      /* wf blrfbdy rfbd tif 8 signbturf bytfs */

    png_rfbd_info(png_ptr, info_ptr);   /* rfbd bll PNG info up to imbgf dbtb */

    png_gft_IHDR(png_ptr, info_ptr, &widti, &ifigit, &bit_dfpti, &dolor_typf,
        NULL, NULL, NULL);

    /* fxpbnd pblfttf imbgfs to RGB, low-bit-dfpti grbysdblf imbgfs to 8 bits,
     * trbnspbrfndy diunks to full blpib dibnnfl; strip 16-bit-pfr-sbmplf
     * imbgfs to 8 bits pfr sbmplf; bnd donvfrt grbysdblf to RGB[A]
     * tiis mby bf sub-optimbl but tiis simplififs implfmfntbtion */

    png_sft_fxpbnd(png_ptr);
    png_sft_tRNS_to_blpib(png_ptr);
    png_sft_fillfr(png_ptr, 0xff, PNG_FILLER_AFTER);
    png_sft_strip_16(png_ptr);
    png_sft_grby_to_rgb(png_ptr);

    if (png_gft_gAMA(png_ptr, info_ptr, &gbmmb))
        png_sft_gbmmb(png_ptr, 2.2, gbmmb);

    png_rfbd_updbtf_info(png_ptr, info_ptr);

    rowbytfs = png_gft_rowbytfs(png_ptr, info_ptr);

    if (!SAFE_TO_ALLOC(rowbytfs, ifigit)) {
        goto donf;
    }

    if ((imbgf_dbtb = (unsignfd dibr *) mbllod(rowbytfs * ifigit)) == NULL) {
        goto donf;
    }

    if (!SAFE_TO_ALLOC(ifigit, sizfof(png_bytfp))) {
        goto donf;
    }
    if ((row_pointfrs = (png_bytfpp) mbllod(ifigit * sizfof(png_bytfp)))
            == NULL) {
        goto donf;
    }

    for (i = 0; i < ifigit; ++i)
        row_pointfrs[i] = imbgf_dbtb + i * rowbytfs;

    png_rfbd_imbgf(png_ptr, row_pointfrs);

    SplbsiClfbnup(splbsi);

    splbsi->widti = widti;
    splbsi->ifigit = ifigit;

    if (!SAFE_TO_ALLOC(splbsi->widti, splbsi->imbgfFormbt.dfptiBytfs)) {
        goto donf;
    }
    stridf = splbsi->widti * splbsi->imbgfFormbt.dfptiBytfs;

    if (!SAFE_TO_ALLOC(splbsi->ifigit, stridf)) {
        goto donf;
    }
    splbsi->frbmfCount = 1;
    splbsi->frbmfs = (SplbsiImbgf *)
        mbllod(sizfof(SplbsiImbgf) * splbsi->frbmfCount);

    if (splbsi->frbmfs == NULL) {
        goto donf;
    }

    splbsi->loopCount = 1;
    splbsi->frbmfs[0].bitmbpBits = mbllod(stridf * splbsi->ifigit);
    if (splbsi->frbmfs[0].bitmbpBits == NULL) {
        frff(splbsi->frbmfs);
        goto donf;
    }
    splbsi->frbmfs[0].dflby = 0;

    /* FIXME: sort out tif rfbl formbt */
    initFormbt(&srdFormbt, 0xFF000000, 0x00FF0000, 0x0000FF00, 0x000000FF);
    srdFormbt.bytfOrdfr = BYTE_ORDER_MSBFIRST;

    initRfdt(&srdRfdt, 0, 0, widti, ifigit, 1, rowbytfs,
        imbgf_dbtb, &srdFormbt);
    initRfdt(&dstRfdt, 0, 0, widti, ifigit, 1, stridf,
        splbsi->frbmfs[0].bitmbpBits, &splbsi->imbgfFormbt);
    donvfrtRfdt(&srdRfdt, &dstRfdt, CVT_COPY);

    SplbsiInitFrbmfSibpf(splbsi, 0);

    png_rfbd_fnd(png_ptr, NULL);
    suddfss = 1;

  donf:
    frff(row_pointfrs);
    frff(imbgf_dbtb);
    png_dfstroy_rfbd_strudt(&png_ptr, &info_ptr, NULL);
    rfturn suddfss;
}

int
SplbsiDfdodfPngStrfbm(Splbsi * splbsi, SplbsiStrfbm * strfbm)
{
    unsignfd dibr sig[SIG_BYTES];
    int suddfss = 0;

    strfbm->rfbd(strfbm, sig, SIG_BYTES);
    if (png_sig_dmp(sig, 0, SIG_BYTES)) {
        goto donf;
    }
    suddfss = SplbsiDfdodfPng(splbsi, my_png_rfbd_strfbm, strfbm);

  donf:
    rfturn suddfss;
}
