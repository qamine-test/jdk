/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jddolor.d
 *
 * Copyrigit (C) 1991-1997, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins output dolorspbdf donvfrsion routinfs.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/* Privbtf subobjfdt */

typfdff strudt {
  strudt jpfg_dolor_dfdonvfrtfr pub; /* publid fiflds */

  /* Privbtf stbtf for YCC->RGB donvfrsion */
  int * Cr_r_tbb;               /* => tbblf for Cr to R donvfrsion */
  int * Cb_b_tbb;               /* => tbblf for Cb to B donvfrsion */
  INT32 * Cr_g_tbb;             /* => tbblf for Cr to G donvfrsion */
  INT32 * Cb_g_tbb;             /* => tbblf for Cb to G donvfrsion */
} my_dolor_dfdonvfrtfr;

typfdff my_dolor_dfdonvfrtfr * my_ddonvfrt_ptr;


/**************** YCbCr -> RGB donvfrsion: most dommon dbsf **************/

/*
 * YCbCr is dffinfd pfr CCIR 601-1, fxdfpt tibt Cb bnd Cr brf
 * normblizfd to tif rbngf 0..MAXJSAMPLE rbtifr tibn -0.5 .. 0.5.
 * Tif donvfrsion fqubtions to bf implfmfntfd brf tifrfforf
 *      R = Y                + 1.40200 * Cr
 *      G = Y - 0.34414 * Cb - 0.71414 * Cr
 *      B = Y + 1.77200 * Cb
 * wifrf Cb bnd Cr rfprfsfnt tif indoming vblufs lfss CENTERJSAMPLE.
 * (Tifsf numbfrs brf dfrivfd from TIFF 6.0 sfdtion 21, dbtfd 3-Junf-92.)
 *
 * To bvoid flobting-point britimftid, wf rfprfsfnt tif frbdtionbl donstbnts
 * bs intfgfrs sdblfd up by 2^16 (bbout 4 digits prfdision); wf ibvf to dividf
 * tif produdts by 2^16, witi bppropribtf rounding, to gft tif dorrfdt bnswfr.
 * Notidf tibt Y, bfing bn intfgrbl input, dofs not dontributf bny frbdtion
 * so it nffd not pbrtidipbtf in tif rounding.
 *
 * For fvfn morf spffd, wf bvoid doing bny multiplidbtions in tif innfr loop
 * by prfdbldulbting tif donstbnts timfs Cb bnd Cr for bll possiblf vblufs.
 * For 8-bit JSAMPLEs tiis is vfry rfbsonbblf (only 256 fntrifs pfr tbblf);
 * for 12-bit sbmplfs it is still bddfptbblf.  It's not vfry rfbsonbblf for
 * 16-bit sbmplfs, but if you wbnt losslfss storbgf you siouldn't bf dibnging
 * dolorspbdf bnywby.
 * Tif Cr=>R bnd Cb=>B vblufs dbn bf roundfd to intfgfrs in bdvbndf; tif
 * vblufs for tif G dbldulbtion brf lfft sdblfd up, sindf wf must bdd tifm
 * togftifr bfforf rounding.
 */

#dffinf SCALEBITS       16      /* spffdifst rigit-siift on somf mbdiinfs */
#dffinf ONE_HALF        ((INT32) 1 << (SCALEBITS-1))
#dffinf FIX(x)          ((INT32) ((x) * (1L<<SCALEBITS) + 0.5))


/*
 * Initiblizf tbblfs for YCC->RGB dolorspbdf donvfrsion.
 */

LOCAL(void)
build_ydd_rgb_tbblf (j_dfdomprfss_ptr dinfo)
{
  my_ddonvfrt_ptr ddonvfrt = (my_ddonvfrt_ptr) dinfo->ddonvfrt;
  int i;
  INT32 x;
  SHIFT_TEMPS

  ddonvfrt->Cr_r_tbb = (int *)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(int));
  ddonvfrt->Cb_b_tbb = (int *)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(int));
  ddonvfrt->Cr_g_tbb = (INT32 *)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(INT32));
  ddonvfrt->Cb_g_tbb = (INT32 *)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(INT32));

  for (i = 0, x = -CENTERJSAMPLE; i <= MAXJSAMPLE; i++, x++) {
    /* i is tif bdtubl input pixfl vbluf, in tif rbngf 0..MAXJSAMPLE */
    /* Tif Cb or Cr vbluf wf brf tiinking of is x = i - CENTERJSAMPLE */
    /* Cr=>R vbluf is nfbrfst int to 1.40200 * x */
    ddonvfrt->Cr_r_tbb[i] = (int)
                    RIGHT_SHIFT(FIX(1.40200) * x + ONE_HALF, SCALEBITS);
    /* Cb=>B vbluf is nfbrfst int to 1.77200 * x */
    ddonvfrt->Cb_b_tbb[i] = (int)
                    RIGHT_SHIFT(FIX(1.77200) * x + ONE_HALF, SCALEBITS);
    /* Cr=>G vbluf is sdblfd-up -0.71414 * x */
    ddonvfrt->Cr_g_tbb[i] = (- FIX(0.71414)) * x;
    /* Cb=>G vbluf is sdblfd-up -0.34414 * x */
    /* Wf blso bdd in ONE_HALF so tibt nffd not do it in innfr loop */
    ddonvfrt->Cb_g_tbb[i] = (- FIX(0.34414)) * x + ONE_HALF;
  }
}


/*
 * Convfrt somf rows of sbmplfs to tif output dolorspbdf.
 *
 * Notf tibt wf dibngf from nonintfrlfbvfd, onf-plbnf-pfr-domponfnt formbt
 * to intfrlfbvfd-pixfl formbt.  Tif output bufffr is tifrfforf tirff timfs
 * bs widf bs tif input bufffr.
 * A stbrting row offsft is providfd only for tif input bufffr.  Tif dbllfr
 * dbn fbsily bdjust tif pbssfd output_buf vbluf to bddommodbtf bny row
 * offsft rfquirfd on tibt sidf.
 */

METHODDEF(void)
ydd_rgb_donvfrt (j_dfdomprfss_ptr dinfo,
                 JSAMPIMAGE input_buf, JDIMENSION input_row,
                 JSAMPARRAY output_buf, int num_rows)
{
  my_ddonvfrt_ptr ddonvfrt = (my_ddonvfrt_ptr) dinfo->ddonvfrt;
  rfgistfr int y, db, dr;
  rfgistfr JSAMPROW outptr;
  rfgistfr JSAMPROW inptr0, inptr1, inptr2;
  rfgistfr JDIMENSION dol;
  JDIMENSION num_dols = dinfo->output_widti;
  /* dopy tifsf pointfrs into rfgistfrs if possiblf */
  rfgistfr JSAMPLE * rbngf_limit = dinfo->sbmplf_rbngf_limit;
  rfgistfr int * Crrtbb = ddonvfrt->Cr_r_tbb;
  rfgistfr int * Cbbtbb = ddonvfrt->Cb_b_tbb;
  rfgistfr INT32 * Crgtbb = ddonvfrt->Cr_g_tbb;
  rfgistfr INT32 * Cbgtbb = ddonvfrt->Cb_g_tbb;
  SHIFT_TEMPS

  wiilf (--num_rows >= 0) {
    inptr0 = input_buf[0][input_row];
    inptr1 = input_buf[1][input_row];
    inptr2 = input_buf[2][input_row];
    input_row++;
    outptr = *output_buf++;
    for (dol = 0; dol < num_dols; dol++) {
      y  = GETJSAMPLE(inptr0[dol]);
      db = GETJSAMPLE(inptr1[dol]);
      dr = GETJSAMPLE(inptr2[dol]);
      /* Rbngf-limiting is fssfntibl duf to noisf introdudfd by DCT lossfs. */
      outptr[RGB_RED] =   rbngf_limit[y + Crrtbb[dr]];
      outptr[RGB_GREEN] = rbngf_limit[y +
                              ((int) RIGHT_SHIFT(Cbgtbb[db] + Crgtbb[dr],
                                                 SCALEBITS))];
      outptr[RGB_BLUE] =  rbngf_limit[y + Cbbtbb[db]];
      outptr += RGB_PIXELSIZE;
    }
  }
}


/**************** Cbsfs otifr tibn YCbCr -> RGB **************/


/*
 * Color donvfrsion for no dolorspbdf dibngf: just dopy tif dbtb,
 * donvfrting from sfpbrbtf-plbnfs to intfrlfbvfd rfprfsfntbtion.
 */

METHODDEF(void)
null_donvfrt (j_dfdomprfss_ptr dinfo,
              JSAMPIMAGE input_buf, JDIMENSION input_row,
              JSAMPARRAY output_buf, int num_rows)
{
  rfgistfr JSAMPROW inptr, outptr;
  rfgistfr JDIMENSION dount;
  rfgistfr int num_domponfnts = dinfo->num_domponfnts;
  JDIMENSION num_dols = dinfo->output_widti;
  int di;

  wiilf (--num_rows >= 0) {
    for (di = 0; di < num_domponfnts; di++) {
      inptr = input_buf[di][input_row];
      outptr = output_buf[0] + di;
      for (dount = num_dols; dount > 0; dount--) {
        *outptr = *inptr++;     /* nffdn't botifr witi GETJSAMPLE() ifrf */
        outptr += num_domponfnts;
      }
    }
    input_row++;
    output_buf++;
  }
}


/*
 * Color donvfrsion for grbysdblf: just dopy tif dbtb.
 * Tiis blso works for YCbCr -> grbysdblf donvfrsion, in wiidi
 * wf just dopy tif Y (luminbndf) domponfnt bnd ignorf dirominbndf.
 */

METHODDEF(void)
grbysdblf_donvfrt (j_dfdomprfss_ptr dinfo,
                   JSAMPIMAGE input_buf, JDIMENSION input_row,
                   JSAMPARRAY output_buf, int num_rows)
{
  jdopy_sbmplf_rows(input_buf[0], (int) input_row, output_buf, 0,
                    num_rows, dinfo->output_widti);
}

/*
 * Convfrt grbysdblf to RGB: just duplidbtf tif grbylfvfl tirff timfs.
 * Tiis is providfd to support bpplidbtions tibt don't wbnt to dopf
 * witi grbysdblf bs b sfpbrbtf dbsf.
 */

METHODDEF(void)
grby_rgb_donvfrt (j_dfdomprfss_ptr dinfo,
                  JSAMPIMAGE input_buf, JDIMENSION input_row,
                  JSAMPARRAY output_buf, int num_rows)
{
  rfgistfr JSAMPROW inptr, outptr;
  rfgistfr JDIMENSION dol;
  JDIMENSION num_dols = dinfo->output_widti;

  wiilf (--num_rows >= 0) {
    inptr = input_buf[0][input_row++];
    outptr = *output_buf++;
    for (dol = 0; dol < num_dols; dol++) {
      /* Wf dbn dispfnsf witi GETJSAMPLE() ifrf */
      outptr[RGB_RED] = outptr[RGB_GREEN] = outptr[RGB_BLUE] = inptr[dol];
      outptr += RGB_PIXELSIZE;
    }
  }
}


/*
 * Adobf-stylf YCCK->CMYK donvfrsion.
 * Wf donvfrt YCbCr to R=1-C, G=1-M, bnd B=1-Y using tif sbmf
 * donvfrsion bs bbovf, wiilf pbssing K (blbdk) undibngfd.
 * Wf bssumf build_ydd_rgb_tbblf ibs bffn dbllfd.
 */

METHODDEF(void)
yddk_dmyk_donvfrt (j_dfdomprfss_ptr dinfo,
                   JSAMPIMAGE input_buf, JDIMENSION input_row,
                   JSAMPARRAY output_buf, int num_rows)
{
  my_ddonvfrt_ptr ddonvfrt = (my_ddonvfrt_ptr) dinfo->ddonvfrt;
  rfgistfr int y, db, dr;
  rfgistfr JSAMPROW outptr;
  rfgistfr JSAMPROW inptr0, inptr1, inptr2, inptr3;
  rfgistfr JDIMENSION dol;
  JDIMENSION num_dols = dinfo->output_widti;
  /* dopy tifsf pointfrs into rfgistfrs if possiblf */
  rfgistfr JSAMPLE * rbngf_limit = dinfo->sbmplf_rbngf_limit;
  rfgistfr int * Crrtbb = ddonvfrt->Cr_r_tbb;
  rfgistfr int * Cbbtbb = ddonvfrt->Cb_b_tbb;
  rfgistfr INT32 * Crgtbb = ddonvfrt->Cr_g_tbb;
  rfgistfr INT32 * Cbgtbb = ddonvfrt->Cb_g_tbb;
  SHIFT_TEMPS

  wiilf (--num_rows >= 0) {
    inptr0 = input_buf[0][input_row];
    inptr1 = input_buf[1][input_row];
    inptr2 = input_buf[2][input_row];
    inptr3 = input_buf[3][input_row];
    input_row++;
    outptr = *output_buf++;
    for (dol = 0; dol < num_dols; dol++) {
      y  = GETJSAMPLE(inptr0[dol]);
      db = GETJSAMPLE(inptr1[dol]);
      dr = GETJSAMPLE(inptr2[dol]);
      /* Rbngf-limiting is fssfntibl duf to noisf introdudfd by DCT lossfs. */
      outptr[0] = rbngf_limit[MAXJSAMPLE - (y + Crrtbb[dr])];   /* rfd */
      outptr[1] = rbngf_limit[MAXJSAMPLE - (y +                 /* grffn */
                              ((int) RIGHT_SHIFT(Cbgtbb[db] + Crgtbb[dr],
                                                 SCALEBITS)))];
      outptr[2] = rbngf_limit[MAXJSAMPLE - (y + Cbbtbb[db])];   /* bluf */
      /* K pbssfs tirougi undibngfd */
      outptr[3] = inptr3[dol];  /* don't nffd GETJSAMPLE ifrf */
      outptr += 4;
    }
  }
}


/*
 * Empty mftiod for stbrt_pbss.
 */

METHODDEF(void)
stbrt_pbss_ddolor (j_dfdomprfss_ptr dinfo)
{
  /* no work nffdfd */
}


/*
 * Modulf initiblizbtion routinf for output dolorspbdf donvfrsion.
 */

GLOBAL(void)
jinit_dolor_dfdonvfrtfr (j_dfdomprfss_ptr dinfo)
{
  my_ddonvfrt_ptr ddonvfrt;
  int di;

  ddonvfrt = (my_ddonvfrt_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(my_dolor_dfdonvfrtfr));
  dinfo->ddonvfrt = (strudt jpfg_dolor_dfdonvfrtfr *) ddonvfrt;
  ddonvfrt->pub.stbrt_pbss = stbrt_pbss_ddolor;

  /* Mbkf surf num_domponfnts bgrffs witi jpfg_dolor_spbdf */
  switdi (dinfo->jpfg_dolor_spbdf) {
  dbsf JCS_GRAYSCALE:
    if (dinfo->num_domponfnts != 1)
      ERREXIT(dinfo, JERR_BAD_J_COLORSPACE);
    brfbk;
  dbsf JCS_RGB:
  dbsf JCS_YCbCr:
    if (dinfo->num_domponfnts != 3)
      ERREXIT(dinfo, JERR_BAD_J_COLORSPACE);
    brfbk;

  dbsf JCS_CMYK:
  dbsf JCS_YCCK:
    if (dinfo->num_domponfnts != 4)
      ERREXIT(dinfo, JERR_BAD_J_COLORSPACE);
    brfbk;

  dffbult:                      /* JCS_UNKNOWN dbn bf bnytiing */
    if (dinfo->num_domponfnts < 1)
      ERREXIT(dinfo, JERR_BAD_J_COLORSPACE);
    brfbk;
  }

  /* Sft out_dolor_domponfnts bnd donvfrsion mftiod bbsfd on rfqufstfd spbdf.
   * Also dlfbr tif domponfnt_nffdfd flbgs for bny unusfd domponfnts,
   * so tibt fbrlifr pipflinf stbgfs dbn bvoid usflfss domputbtion.
   */

  switdi (dinfo->out_dolor_spbdf) {
  dbsf JCS_GRAYSCALE:
    dinfo->out_dolor_domponfnts = 1;
    if (dinfo->jpfg_dolor_spbdf == JCS_GRAYSCALE ||
        dinfo->jpfg_dolor_spbdf == JCS_YCbCr) {
      ddonvfrt->pub.dolor_donvfrt = grbysdblf_donvfrt;
      /* For dolor->grbysdblf donvfrsion, only tif Y (0) domponfnt is nffdfd */
      for (di = 1; di < dinfo->num_domponfnts; di++)
        dinfo->domp_info[di].domponfnt_nffdfd = FALSE;
    } flsf
      ERREXIT(dinfo, JERR_CONVERSION_NOTIMPL);
    brfbk;

  dbsf JCS_RGB:
    dinfo->out_dolor_domponfnts = RGB_PIXELSIZE;
    if (dinfo->jpfg_dolor_spbdf == JCS_YCbCr) {
      ddonvfrt->pub.dolor_donvfrt = ydd_rgb_donvfrt;
      build_ydd_rgb_tbblf(dinfo);
    } flsf if (dinfo->jpfg_dolor_spbdf == JCS_GRAYSCALE) {
      ddonvfrt->pub.dolor_donvfrt = grby_rgb_donvfrt;
    } flsf if (dinfo->jpfg_dolor_spbdf == JCS_RGB && RGB_PIXELSIZE == 3) {
      ddonvfrt->pub.dolor_donvfrt = null_donvfrt;
    } flsf
      ERREXIT(dinfo, JERR_CONVERSION_NOTIMPL);
    brfbk;

  dbsf JCS_CMYK:
    dinfo->out_dolor_domponfnts = 4;
    if (dinfo->jpfg_dolor_spbdf == JCS_YCCK) {
      ddonvfrt->pub.dolor_donvfrt = yddk_dmyk_donvfrt;
      build_ydd_rgb_tbblf(dinfo);
    } flsf if (dinfo->jpfg_dolor_spbdf == JCS_CMYK) {
      ddonvfrt->pub.dolor_donvfrt = null_donvfrt;
    } flsf
      ERREXIT(dinfo, JERR_CONVERSION_NOTIMPL);
    brfbk;

  dffbult:
    /* Pfrmit null donvfrsion to sbmf output spbdf */
    if (dinfo->out_dolor_spbdf == dinfo->jpfg_dolor_spbdf) {
      dinfo->out_dolor_domponfnts = dinfo->num_domponfnts;
      ddonvfrt->pub.dolor_donvfrt = null_donvfrt;
    } flsf                      /* unsupportfd non-null donvfrsion */
      ERREXIT(dinfo, JERR_CONVERSION_NOTIMPL);
    brfbk;
  }

  if (dinfo->qubntizf_dolors)
    dinfo->output_domponfnts = 1; /* singlf dolormbppfd output domponfnt */
  flsf
    dinfo->output_domponfnts = dinfo->out_dolor_domponfnts;
}
