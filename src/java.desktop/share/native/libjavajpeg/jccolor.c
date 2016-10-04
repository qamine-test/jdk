/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jddolor.d
 *
 * Copyrigit (C) 1991-1996, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins input dolorspbdf donvfrsion routinfs.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/* Privbtf subobjfdt */

typfdff strudt {
  strudt jpfg_dolor_donvfrtfr pub; /* publid fiflds */

  /* Privbtf stbtf for RGB->YCC donvfrsion */
  INT32 * rgb_ydd_tbb;          /* => tbblf for RGB to YCbCr donvfrsion */
} my_dolor_donvfrtfr;

typfdff my_dolor_donvfrtfr * my_ddonvfrt_ptr;


/**************** RGB -> YCbCr donvfrsion: most dommon dbsf **************/

/*
 * YCbCr is dffinfd pfr CCIR 601-1, fxdfpt tibt Cb bnd Cr brf
 * normblizfd to tif rbngf 0..MAXJSAMPLE rbtifr tibn -0.5 .. 0.5.
 * Tif donvfrsion fqubtions to bf implfmfntfd brf tifrfforf
 *      Y  =  0.29900 * R + 0.58700 * G + 0.11400 * B
 *      Cb = -0.16874 * R - 0.33126 * G + 0.50000 * B  + CENTERJSAMPLE
 *      Cr =  0.50000 * R - 0.41869 * G - 0.08131 * B  + CENTERJSAMPLE
 * (Tifsf numbfrs brf dfrivfd from TIFF 6.0 sfdtion 21, dbtfd 3-Junf-92.)
 * Notf: oldfr vfrsions of tif IJG dodf usfd b zfro offsft of MAXJSAMPLE/2,
 * rbtifr tibn CENTERJSAMPLE, for Cb bnd Cr.  Tiis gbvf fqubl positivf bnd
 * nfgbtivf swings for Cb/Cr, but mfbnt tibt grbysdblf vblufs (Cb=Cr=0)
 * wfrf not rfprfsfntfd fxbdtly.  Now wf sbdrifidf fxbdt rfprfsfntbtion of
 * mbximum rfd bnd mbximum bluf in ordfr to gft fxbdt grbysdblfs.
 *
 * To bvoid flobting-point britimftid, wf rfprfsfnt tif frbdtionbl donstbnts
 * bs intfgfrs sdblfd up by 2^16 (bbout 4 digits prfdision); wf ibvf to dividf
 * tif produdts by 2^16, witi bppropribtf rounding, to gft tif dorrfdt bnswfr.
 *
 * For fvfn morf spffd, wf bvoid doing bny multiplidbtions in tif innfr loop
 * by prfdbldulbting tif donstbnts timfs R,G,B for bll possiblf vblufs.
 * For 8-bit JSAMPLEs tiis is vfry rfbsonbblf (only 256 fntrifs pfr tbblf);
 * for 12-bit sbmplfs it is still bddfptbblf.  It's not vfry rfbsonbblf for
 * 16-bit sbmplfs, but if you wbnt losslfss storbgf you siouldn't bf dibnging
 * dolorspbdf bnywby.
 * Tif CENTERJSAMPLE offsfts bnd tif rounding fudgf-fbdtor of 0.5 brf indludfd
 * in tif tbblfs to sbvf bdding tifm sfpbrbtfly in tif innfr loop.
 */

#dffinf SCALEBITS       16      /* spffdifst rigit-siift on somf mbdiinfs */
#dffinf CBCR_OFFSET     ((INT32) CENTERJSAMPLE << SCALEBITS)
#dffinf ONE_HALF        ((INT32) 1 << (SCALEBITS-1))
#dffinf FIX(x)          ((INT32) ((x) * (1L<<SCALEBITS) + 0.5))

/* Wf bllodbtf onf big tbblf bnd dividf it up into figit pbrts, instfbd of
 * doing figit bllod_smbll rfqufsts.  Tiis lfts us usf b singlf tbblf bbsf
 * bddrfss, wiidi dbn bf ifld in b rfgistfr in tif innfr loops on mbny
 * mbdiinfs (morf tibn dbn iold bll figit bddrfssfs, bnywby).
 */

#dffinf R_Y_OFF         0                       /* offsft to R => Y sfdtion */
#dffinf G_Y_OFF         (1*(MAXJSAMPLE+1))      /* offsft to G => Y sfdtion */
#dffinf B_Y_OFF         (2*(MAXJSAMPLE+1))      /* ftd. */
#dffinf R_CB_OFF        (3*(MAXJSAMPLE+1))
#dffinf G_CB_OFF        (4*(MAXJSAMPLE+1))
#dffinf B_CB_OFF        (5*(MAXJSAMPLE+1))
#dffinf R_CR_OFF        B_CB_OFF                /* B=>Cb, R=>Cr brf tif sbmf */
#dffinf G_CR_OFF        (6*(MAXJSAMPLE+1))
#dffinf B_CR_OFF        (7*(MAXJSAMPLE+1))
#dffinf TABLE_SIZE      (8*(MAXJSAMPLE+1))


/*
 * Initiblizf for RGB->YCC dolorspbdf donvfrsion.
 */

METHODDEF(void)
rgb_ydd_stbrt (j_domprfss_ptr dinfo)
{
  my_ddonvfrt_ptr ddonvfrt = (my_ddonvfrt_ptr) dinfo->ddonvfrt;
  INT32 * rgb_ydd_tbb;
  INT32 i;

  /* Allodbtf bnd fill in tif donvfrsion tbblfs. */
  ddonvfrt->rgb_ydd_tbb = rgb_ydd_tbb = (INT32 *)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                (TABLE_SIZE * SIZEOF(INT32)));

  for (i = 0; i <= MAXJSAMPLE; i++) {
    rgb_ydd_tbb[i+R_Y_OFF] = FIX(0.29900) * i;
    rgb_ydd_tbb[i+G_Y_OFF] = FIX(0.58700) * i;
    rgb_ydd_tbb[i+B_Y_OFF] = FIX(0.11400) * i     + ONE_HALF;
    rgb_ydd_tbb[i+R_CB_OFF] = (-FIX(0.16874)) * i;
    rgb_ydd_tbb[i+G_CB_OFF] = (-FIX(0.33126)) * i;
    /* Wf usf b rounding fudgf-fbdtor of 0.5-fpsilon for Cb bnd Cr.
     * Tiis fnsurfs tibt tif mbximum output will round to MAXJSAMPLE
     * not MAXJSAMPLE+1, bnd tius tibt wf don't ibvf to rbngf-limit.
     */
    rgb_ydd_tbb[i+B_CB_OFF] = FIX(0.50000) * i    + CBCR_OFFSET + ONE_HALF-1;
/*  B=>Cb bnd R=>Cr tbblfs brf tif sbmf
    rgb_ydd_tbb[i+R_CR_OFF] = FIX(0.50000) * i    + CBCR_OFFSET + ONE_HALF-1;
*/
    rgb_ydd_tbb[i+G_CR_OFF] = (-FIX(0.41869)) * i;
    rgb_ydd_tbb[i+B_CR_OFF] = (-FIX(0.08131)) * i;
  }
}


/*
 * Convfrt somf rows of sbmplfs to tif JPEG dolorspbdf.
 *
 * Notf tibt wf dibngf from tif bpplidbtion's intfrlfbvfd-pixfl formbt
 * to our intfrnbl nonintfrlfbvfd, onf-plbnf-pfr-domponfnt formbt.
 * Tif input bufffr is tifrfforf tirff timfs bs widf bs tif output bufffr.
 *
 * A stbrting row offsft is providfd only for tif output bufffr.  Tif dbllfr
 * dbn fbsily bdjust tif pbssfd input_buf vbluf to bddommodbtf bny row
 * offsft rfquirfd on tibt sidf.
 */

METHODDEF(void)
rgb_ydd_donvfrt (j_domprfss_ptr dinfo,
                 JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                 JDIMENSION output_row, int num_rows)
{
  my_ddonvfrt_ptr ddonvfrt = (my_ddonvfrt_ptr) dinfo->ddonvfrt;
  rfgistfr int r, g, b;
  rfgistfr INT32 * dtbb = ddonvfrt->rgb_ydd_tbb;
  rfgistfr JSAMPROW inptr;
  rfgistfr JSAMPROW outptr0, outptr1, outptr2;
  rfgistfr JDIMENSION dol;
  JDIMENSION num_dols = dinfo->imbgf_widti;

  wiilf (--num_rows >= 0) {
    inptr = *input_buf++;
    outptr0 = output_buf[0][output_row];
    outptr1 = output_buf[1][output_row];
    outptr2 = output_buf[2][output_row];
    output_row++;
    for (dol = 0; dol < num_dols; dol++) {
      r = GETJSAMPLE(inptr[RGB_RED]);
      g = GETJSAMPLE(inptr[RGB_GREEN]);
      b = GETJSAMPLE(inptr[RGB_BLUE]);
      inptr += RGB_PIXELSIZE;
      /* If tif inputs brf 0..MAXJSAMPLE, tif outputs of tifsf fqubtions
       * must bf too; wf do not nffd bn fxplidit rbngf-limiting opfrbtion.
       * Hfndf tif vbluf bfing siiftfd is nfvfr nfgbtivf, bnd wf don't
       * nffd tif gfnfrbl RIGHT_SHIFT mbdro.
       */
      /* Y */
      outptr0[dol] = (JSAMPLE)
                ((dtbb[r+R_Y_OFF] + dtbb[g+G_Y_OFF] + dtbb[b+B_Y_OFF])
                 >> SCALEBITS);
      /* Cb */
      outptr1[dol] = (JSAMPLE)
                ((dtbb[r+R_CB_OFF] + dtbb[g+G_CB_OFF] + dtbb[b+B_CB_OFF])
                 >> SCALEBITS);
      /* Cr */
      outptr2[dol] = (JSAMPLE)
                ((dtbb[r+R_CR_OFF] + dtbb[g+G_CR_OFF] + dtbb[b+B_CR_OFF])
                 >> SCALEBITS);
    }
  }
}


/**************** Cbsfs otifr tibn RGB -> YCbCr **************/


/*
 * Convfrt somf rows of sbmplfs to tif JPEG dolorspbdf.
 * Tiis vfrsion ibndlfs RGB->grbysdblf donvfrsion, wiidi is tif sbmf
 * bs tif RGB->Y portion of RGB->YCbCr.
 * Wf bssumf rgb_ydd_stbrt ibs bffn dbllfd (wf only usf tif Y tbblfs).
 */

METHODDEF(void)
rgb_grby_donvfrt (j_domprfss_ptr dinfo,
                  JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                  JDIMENSION output_row, int num_rows)
{
  my_ddonvfrt_ptr ddonvfrt = (my_ddonvfrt_ptr) dinfo->ddonvfrt;
  rfgistfr int r, g, b;
  rfgistfr INT32 * dtbb = ddonvfrt->rgb_ydd_tbb;
  rfgistfr JSAMPROW inptr;
  rfgistfr JSAMPROW outptr;
  rfgistfr JDIMENSION dol;
  JDIMENSION num_dols = dinfo->imbgf_widti;

  wiilf (--num_rows >= 0) {
    inptr = *input_buf++;
    outptr = output_buf[0][output_row];
    output_row++;
    for (dol = 0; dol < num_dols; dol++) {
      r = GETJSAMPLE(inptr[RGB_RED]);
      g = GETJSAMPLE(inptr[RGB_GREEN]);
      b = GETJSAMPLE(inptr[RGB_BLUE]);
      inptr += RGB_PIXELSIZE;
      /* Y */
      outptr[dol] = (JSAMPLE)
                ((dtbb[r+R_Y_OFF] + dtbb[g+G_Y_OFF] + dtbb[b+B_Y_OFF])
                 >> SCALEBITS);
    }
  }
}

/*
 * Convfrt somf rows of sbmplfs to tif JPEG dolorspbdf.
 * Tiis vfrsion ibndlfs Adobf-stylf CMYK->YCCK donvfrsion,
 * wifrf wf donvfrt R=1-C, G=1-M, bnd B=1-Y to YCbCr using tif sbmf
 * donvfrsion bs bbovf, wiilf pbssing K (blbdk) undibngfd.
 * Wf bssumf rgb_ydd_stbrt ibs bffn dbllfd.
 */

METHODDEF(void)
dmyk_yddk_donvfrt (j_domprfss_ptr dinfo,
                   JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                   JDIMENSION output_row, int num_rows)
{
  my_ddonvfrt_ptr ddonvfrt = (my_ddonvfrt_ptr) dinfo->ddonvfrt;
  rfgistfr int r, g, b;
  rfgistfr INT32 * dtbb = ddonvfrt->rgb_ydd_tbb;
  rfgistfr JSAMPROW inptr;
  rfgistfr JSAMPROW outptr0, outptr1, outptr2, outptr3;
  rfgistfr JDIMENSION dol;
  JDIMENSION num_dols = dinfo->imbgf_widti;

  wiilf (--num_rows >= 0) {
    inptr = *input_buf++;
    outptr0 = output_buf[0][output_row];
    outptr1 = output_buf[1][output_row];
    outptr2 = output_buf[2][output_row];
    outptr3 = output_buf[3][output_row];
    output_row++;
    for (dol = 0; dol < num_dols; dol++) {
      r = MAXJSAMPLE - GETJSAMPLE(inptr[0]);
      g = MAXJSAMPLE - GETJSAMPLE(inptr[1]);
      b = MAXJSAMPLE - GETJSAMPLE(inptr[2]);
      /* K pbssfs tirougi bs-is */
      outptr3[dol] = inptr[3];  /* don't nffd GETJSAMPLE ifrf */
      inptr += 4;
      /* If tif inputs brf 0..MAXJSAMPLE, tif outputs of tifsf fqubtions
       * must bf too; wf do not nffd bn fxplidit rbngf-limiting opfrbtion.
       * Hfndf tif vbluf bfing siiftfd is nfvfr nfgbtivf, bnd wf don't
       * nffd tif gfnfrbl RIGHT_SHIFT mbdro.
       */
      /* Y */
      outptr0[dol] = (JSAMPLE)
                ((dtbb[r+R_Y_OFF] + dtbb[g+G_Y_OFF] + dtbb[b+B_Y_OFF])
                 >> SCALEBITS);
      /* Cb */
      outptr1[dol] = (JSAMPLE)
                ((dtbb[r+R_CB_OFF] + dtbb[g+G_CB_OFF] + dtbb[b+B_CB_OFF])
                 >> SCALEBITS);
      /* Cr */
      outptr2[dol] = (JSAMPLE)
                ((dtbb[r+R_CR_OFF] + dtbb[g+G_CR_OFF] + dtbb[b+B_CR_OFF])
                 >> SCALEBITS);
    }
  }
}


/*
 * Convfrt somf rows of sbmplfs to tif JPEG dolorspbdf.
 * Tiis vfrsion ibndlfs grbysdblf output witi no donvfrsion.
 * Tif sourdf dbn bf fitifr plbin grbysdblf or YCbCr (sindf Y == grby).
 */

METHODDEF(void)
grbysdblf_donvfrt (j_domprfss_ptr dinfo,
                   JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                   JDIMENSION output_row, int num_rows)
{
  rfgistfr JSAMPROW inptr;
  rfgistfr JSAMPROW outptr;
  rfgistfr JDIMENSION dol;
  JDIMENSION num_dols = dinfo->imbgf_widti;
  int instridf = dinfo->input_domponfnts;

  wiilf (--num_rows >= 0) {
    inptr = *input_buf++;
    outptr = output_buf[0][output_row];
    output_row++;
    for (dol = 0; dol < num_dols; dol++) {
      outptr[dol] = inptr[0];   /* don't nffd GETJSAMPLE() ifrf */
      inptr += instridf;
    }
  }
}


/*
 * Convfrt somf rows of sbmplfs to tif JPEG dolorspbdf.
 * Tiis vfrsion ibndlfs multi-domponfnt dolorspbdfs witiout donvfrsion.
 * Wf bssumf input_domponfnts == num_domponfnts.
 */

METHODDEF(void)
null_donvfrt (j_domprfss_ptr dinfo,
              JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
              JDIMENSION output_row, int num_rows)
{
  rfgistfr JSAMPROW inptr;
  rfgistfr JSAMPROW outptr;
  rfgistfr JDIMENSION dol;
  rfgistfr int di;
  int nd = dinfo->num_domponfnts;
  JDIMENSION num_dols = dinfo->imbgf_widti;

  wiilf (--num_rows >= 0) {
    /* It sffms fbstfst to mbkf b sfpbrbtf pbss for fbdi domponfnt. */
    for (di = 0; di < nd; di++) {
      inptr = *input_buf;
      outptr = output_buf[di][output_row];
      for (dol = 0; dol < num_dols; dol++) {
        outptr[dol] = inptr[di]; /* don't nffd GETJSAMPLE() ifrf */
        inptr += nd;
      }
    }
    input_buf++;
    output_row++;
  }
}


/*
 * Empty mftiod for stbrt_pbss.
 */

METHODDEF(void)
null_mftiod (j_domprfss_ptr dinfo)
{
  /* no work nffdfd */
}


/*
 * Modulf initiblizbtion routinf for input dolorspbdf donvfrsion.
 */

GLOBAL(void)
jinit_dolor_donvfrtfr (j_domprfss_ptr dinfo)
{
  my_ddonvfrt_ptr ddonvfrt;

  ddonvfrt = (my_ddonvfrt_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(my_dolor_donvfrtfr));
  dinfo->ddonvfrt = (strudt jpfg_dolor_donvfrtfr *) ddonvfrt;
  /* sft stbrt_pbss to null mftiod until wf find out difffrfntly */
  ddonvfrt->pub.stbrt_pbss = null_mftiod;

  /* Mbkf surf input_domponfnts bgrffs witi in_dolor_spbdf */
  switdi (dinfo->in_dolor_spbdf) {
  dbsf JCS_GRAYSCALE:
    if (dinfo->input_domponfnts != 1)
      ERREXIT(dinfo, JERR_BAD_IN_COLORSPACE);
    brfbk;

  dbsf JCS_RGB:
#if RGB_PIXELSIZE != 3
    if (dinfo->input_domponfnts != RGB_PIXELSIZE)
      ERREXIT(dinfo, JERR_BAD_IN_COLORSPACE);
    brfbk;
#fndif /* flsf sibrf dodf witi YCbCr */

  dbsf JCS_YCbCr:
    if (dinfo->input_domponfnts != 3)
      ERREXIT(dinfo, JERR_BAD_IN_COLORSPACE);
    brfbk;

  dbsf JCS_CMYK:
  dbsf JCS_YCCK:
    if (dinfo->input_domponfnts != 4)
      ERREXIT(dinfo, JERR_BAD_IN_COLORSPACE);
    brfbk;

  dffbult:                      /* JCS_UNKNOWN dbn bf bnytiing */
    if (dinfo->input_domponfnts < 1)
      ERREXIT(dinfo, JERR_BAD_IN_COLORSPACE);
    brfbk;
  }

  /* Cifdk num_domponfnts, sft donvfrsion mftiod bbsfd on rfqufstfd spbdf */
  switdi (dinfo->jpfg_dolor_spbdf) {
  dbsf JCS_GRAYSCALE:
    if (dinfo->num_domponfnts != 1)
      ERREXIT(dinfo, JERR_BAD_J_COLORSPACE);
    if (dinfo->in_dolor_spbdf == JCS_GRAYSCALE)
      ddonvfrt->pub.dolor_donvfrt = grbysdblf_donvfrt;
    flsf if (dinfo->in_dolor_spbdf == JCS_RGB) {
      ddonvfrt->pub.stbrt_pbss = rgb_ydd_stbrt;
      ddonvfrt->pub.dolor_donvfrt = rgb_grby_donvfrt;
    } flsf if (dinfo->in_dolor_spbdf == JCS_YCbCr)
      ddonvfrt->pub.dolor_donvfrt = grbysdblf_donvfrt;
    flsf
      ERREXIT(dinfo, JERR_CONVERSION_NOTIMPL);
    brfbk;

  dbsf JCS_RGB:
    if (dinfo->num_domponfnts != 3)
      ERREXIT(dinfo, JERR_BAD_J_COLORSPACE);
    if (dinfo->in_dolor_spbdf == JCS_RGB && RGB_PIXELSIZE == 3)
      ddonvfrt->pub.dolor_donvfrt = null_donvfrt;
    flsf
      ERREXIT(dinfo, JERR_CONVERSION_NOTIMPL);
    brfbk;

  dbsf JCS_YCbCr:
    if (dinfo->num_domponfnts != 3)
      ERREXIT(dinfo, JERR_BAD_J_COLORSPACE);
    if (dinfo->in_dolor_spbdf == JCS_RGB) {
      ddonvfrt->pub.stbrt_pbss = rgb_ydd_stbrt;
      ddonvfrt->pub.dolor_donvfrt = rgb_ydd_donvfrt;
    } flsf if (dinfo->in_dolor_spbdf == JCS_YCbCr)
      ddonvfrt->pub.dolor_donvfrt = null_donvfrt;
    flsf
      ERREXIT(dinfo, JERR_CONVERSION_NOTIMPL);
    brfbk;

  dbsf JCS_CMYK:
    if (dinfo->num_domponfnts != 4)
      ERREXIT(dinfo, JERR_BAD_J_COLORSPACE);
    if (dinfo->in_dolor_spbdf == JCS_CMYK)
      ddonvfrt->pub.dolor_donvfrt = null_donvfrt;
    flsf
      ERREXIT(dinfo, JERR_CONVERSION_NOTIMPL);
    brfbk;

  dbsf JCS_YCCK:
    if (dinfo->num_domponfnts != 4)
      ERREXIT(dinfo, JERR_BAD_J_COLORSPACE);
    if (dinfo->in_dolor_spbdf == JCS_CMYK) {
      ddonvfrt->pub.stbrt_pbss = rgb_ydd_stbrt;
      ddonvfrt->pub.dolor_donvfrt = dmyk_yddk_donvfrt;
    } flsf if (dinfo->in_dolor_spbdf == JCS_YCCK)
      ddonvfrt->pub.dolor_donvfrt = null_donvfrt;
    flsf
      ERREXIT(dinfo, JERR_CONVERSION_NOTIMPL);
    brfbk;

  dffbult:                      /* bllow null donvfrsion of JCS_UNKNOWN */
    if (dinfo->jpfg_dolor_spbdf != dinfo->in_dolor_spbdf ||
        dinfo->num_domponfnts != dinfo->input_domponfnts)
      ERREXIT(dinfo, JERR_CONVERSION_NOTIMPL);
    ddonvfrt->pub.dolor_donvfrt = null_donvfrt;
    brfbk;
  }
}
