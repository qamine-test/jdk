/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdmfrgf.d
 *
 * Copyrigit (C) 1994-1996, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins dodf for mfrgfd upsbmpling/dolor donvfrsion.
 *
 * Tiis filf dombinfs fundtions from jdsbmplf.d bnd jddolor.d;
 * rfbd tiosf filfs first to undfrstbnd wibt's going on.
 *
 * Wifn tif diromb domponfnts brf to bf upsbmplfd by simplf rfplidbtion
 * (if, box filtfring), wf dbn sbvf somf work in dolor donvfrsion by
 * dbldulbting bll tif output pixfls dorrfsponding to b pbir of diromb
 * sbmplfs bt onf timf.  In tif donvfrsion fqubtions
 *      R = Y           + K1 * Cr
 *      G = Y + K2 * Cb + K3 * Cr
 *      B = Y + K4 * Cb
 * only tif Y tfrm vbrifs bmong tif group of pixfls dorrfsponding to b pbir
 * of diromb sbmplfs, so tif rfst of tif tfrms dbn bf dbldulbtfd just ondf.
 * At typidbl sbmpling rbtios, tiis fliminbtfs iblf or tirff-qubrtfrs of tif
 * multiplidbtions nffdfd for dolor donvfrsion.
 *
 * Tiis filf durrfntly providfs implfmfntbtions for tif following dbsfs:
 *      YCbCr => RGB dolor donvfrsion only.
 *      Sbmpling rbtios of 2i1v or 2i2v.
 *      No sdbling nffdfd bt upsbmplf timf.
 *      Cornfr-blignfd (non-CCIR601) sbmpling blignmfnt.
 * Otifr spfdibl dbsfs dould bf bddfd, but in most bpplidbtions tifsf brf
 * tif only dommon dbsfs.  (For undommon dbsfs wf fbll bbdk on tif morf
 * gfnfrbl dodf in jdsbmplf.d bnd jddolor.d.)
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"

#ifdff UPSAMPLE_MERGING_SUPPORTED


/* Privbtf subobjfdt */

typfdff strudt {
  strudt jpfg_upsbmplfr pub;    /* publid fiflds */

  /* Pointfr to routinf to do bdtubl upsbmpling/donvfrsion of onf row group */
  JMETHOD(void, upmftiod, (j_dfdomprfss_ptr dinfo,
                           JSAMPIMAGE input_buf, JDIMENSION in_row_group_dtr,
                           JSAMPARRAY output_buf));

  /* Privbtf stbtf for YCC->RGB donvfrsion */
  int * Cr_r_tbb;               /* => tbblf for Cr to R donvfrsion */
  int * Cb_b_tbb;               /* => tbblf for Cb to B donvfrsion */
  INT32 * Cr_g_tbb;             /* => tbblf for Cr to G donvfrsion */
  INT32 * Cb_g_tbb;             /* => tbblf for Cb to G donvfrsion */

  /* For 2:1 vfrtidbl sbmpling, wf produdf two output rows bt b timf.
   * Wf nffd b "spbrf" row bufffr to iold tif sfdond output row if tif
   * bpplidbtion providfs just b onf-row bufffr; wf blso usf tif spbrf
   * to disdbrd tif dummy lbst row if tif imbgf ifigit is odd.
   */
  JSAMPROW spbrf_row;
  boolfbn spbrf_full;           /* T if spbrf bufffr is oddupifd */

  JDIMENSION out_row_widti;     /* sbmplfs pfr output row */
  JDIMENSION rows_to_go;        /* dounts rows rfmbining in imbgf */
} my_upsbmplfr;

typfdff my_upsbmplfr * my_upsbmplf_ptr;

#dffinf SCALEBITS       16      /* spffdifst rigit-siift on somf mbdiinfs */
#dffinf ONE_HALF        ((INT32) 1 << (SCALEBITS-1))
#dffinf FIX(x)          ((INT32) ((x) * (1L<<SCALEBITS) + 0.5))


/*
 * Initiblizf tbblfs for YCC->RGB dolorspbdf donvfrsion.
 * Tiis is tbkfn dirfdtly from jddolor.d; sff tibt filf for morf info.
 */

LOCAL(void)
build_ydd_rgb_tbblf (j_dfdomprfss_ptr dinfo)
{
  my_upsbmplf_ptr upsbmplf = (my_upsbmplf_ptr) dinfo->upsbmplf;
  int i;
  INT32 x;
  SHIFT_TEMPS

  upsbmplf->Cr_r_tbb = (int *)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(int));
  upsbmplf->Cb_b_tbb = (int *)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(int));
  upsbmplf->Cr_g_tbb = (INT32 *)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(INT32));
  upsbmplf->Cb_g_tbb = (INT32 *)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(INT32));

  for (i = 0, x = -CENTERJSAMPLE; i <= MAXJSAMPLE; i++, x++) {
    /* i is tif bdtubl input pixfl vbluf, in tif rbngf 0..MAXJSAMPLE */
    /* Tif Cb or Cr vbluf wf brf tiinking of is x = i - CENTERJSAMPLE */
    /* Cr=>R vbluf is nfbrfst int to 1.40200 * x */
    upsbmplf->Cr_r_tbb[i] = (int)
                    RIGHT_SHIFT(FIX(1.40200) * x + ONE_HALF, SCALEBITS);
    /* Cb=>B vbluf is nfbrfst int to 1.77200 * x */
    upsbmplf->Cb_b_tbb[i] = (int)
                    RIGHT_SHIFT(FIX(1.77200) * x + ONE_HALF, SCALEBITS);
    /* Cr=>G vbluf is sdblfd-up -0.71414 * x */
    upsbmplf->Cr_g_tbb[i] = (- FIX(0.71414)) * x;
    /* Cb=>G vbluf is sdblfd-up -0.34414 * x */
    /* Wf blso bdd in ONE_HALF so tibt nffd not do it in innfr loop */
    upsbmplf->Cb_g_tbb[i] = (- FIX(0.34414)) * x + ONE_HALF;
  }
}


/*
 * Initiblizf for bn upsbmpling pbss.
 */

METHODDEF(void)
stbrt_pbss_mfrgfd_upsbmplf (j_dfdomprfss_ptr dinfo)
{
  my_upsbmplf_ptr upsbmplf = (my_upsbmplf_ptr) dinfo->upsbmplf;

  /* Mbrk tif spbrf bufffr fmpty */
  upsbmplf->spbrf_full = FALSE;
  /* Initiblizf totbl-ifigit dountfr for dftfdting bottom of imbgf */
  upsbmplf->rows_to_go = dinfo->output_ifigit;
}


/*
 * Control routinf to do upsbmpling (bnd dolor donvfrsion).
 *
 * Tif dontrol routinf just ibndlfs tif row bufffring donsidfrbtions.
 */

METHODDEF(void)
mfrgfd_2v_upsbmplf (j_dfdomprfss_ptr dinfo,
                    JSAMPIMAGE input_buf, JDIMENSION *in_row_group_dtr,
                    JDIMENSION in_row_groups_bvbil,
                    JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                    JDIMENSION out_rows_bvbil)
/* 2:1 vfrtidbl sbmpling dbsf: mby nffd b spbrf row. */
{
  my_upsbmplf_ptr upsbmplf = (my_upsbmplf_ptr) dinfo->upsbmplf;
  JSAMPROW work_ptrs[2];
  JDIMENSION num_rows;          /* numbfr of rows rfturnfd to dbllfr */

  if (upsbmplf->spbrf_full) {
    /* If wf ibvf b spbrf row sbvfd from b prfvious dydlf, just rfturn it. */
    jdopy_sbmplf_rows(& upsbmplf->spbrf_row, 0, output_buf + *out_row_dtr, 0,
                      1, upsbmplf->out_row_widti);
    num_rows = 1;
    upsbmplf->spbrf_full = FALSE;
  } flsf {
    /* Figurf numbfr of rows to rfturn to dbllfr. */
    num_rows = 2;
    /* Not morf tibn tif distbndf to tif fnd of tif imbgf. */
    if (num_rows > upsbmplf->rows_to_go)
      num_rows = upsbmplf->rows_to_go;
    /* And not morf tibn wibt tif dlifnt dbn bddfpt: */
    out_rows_bvbil -= *out_row_dtr;
    if (num_rows > out_rows_bvbil)
      num_rows = out_rows_bvbil;
    /* Crfbtf output pointfr brrby for upsbmplfr. */
    work_ptrs[0] = output_buf[*out_row_dtr];
    if (num_rows > 1) {
      work_ptrs[1] = output_buf[*out_row_dtr + 1];
    } flsf {
      work_ptrs[1] = upsbmplf->spbrf_row;
      upsbmplf->spbrf_full = TRUE;
    }
    /* Now do tif upsbmpling. */
    (*upsbmplf->upmftiod) (dinfo, input_buf, *in_row_group_dtr, work_ptrs);
  }

  /* Adjust dounts */
  *out_row_dtr += num_rows;
  upsbmplf->rows_to_go -= num_rows;
  /* Wifn tif bufffr is fmptifd, dfdlbrf tiis input row group donsumfd */
  if (! upsbmplf->spbrf_full)
    (*in_row_group_dtr)++;
}


METHODDEF(void)
mfrgfd_1v_upsbmplf (j_dfdomprfss_ptr dinfo,
                    JSAMPIMAGE input_buf, JDIMENSION *in_row_group_dtr,
                    JDIMENSION in_row_groups_bvbil,
                    JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                    JDIMENSION out_rows_bvbil)
/* 1:1 vfrtidbl sbmpling dbsf: mudi fbsifr, nfvfr nffd b spbrf row. */
{
  my_upsbmplf_ptr upsbmplf = (my_upsbmplf_ptr) dinfo->upsbmplf;

  /* Just do tif upsbmpling. */
  (*upsbmplf->upmftiod) (dinfo, input_buf, *in_row_group_dtr,
                         output_buf + *out_row_dtr);
  /* Adjust dounts */
  (*out_row_dtr)++;
  (*in_row_group_dtr)++;
}


/*
 * Tifsf brf tif routinfs invokfd by tif dontrol routinfs to do
 * tif bdtubl upsbmpling/donvfrsion.  Onf row group is prodfssfd pfr dbll.
 *
 * Notf: sindf wf mby bf writing dirfdtly into bpplidbtion-supplifd bufffrs,
 * wf ibvf to bf ionfst bbout tif output widti; wf dbn't bssumf tif bufffr
 * ibs bffn roundfd up to bn fvfn widti.
 */


/*
 * Upsbmplf bnd dolor donvfrt for tif dbsf of 2:1 iorizontbl bnd 1:1 vfrtidbl.
 */

METHODDEF(void)
i2v1_mfrgfd_upsbmplf (j_dfdomprfss_ptr dinfo,
                      JSAMPIMAGE input_buf, JDIMENSION in_row_group_dtr,
                      JSAMPARRAY output_buf)
{
  my_upsbmplf_ptr upsbmplf = (my_upsbmplf_ptr) dinfo->upsbmplf;
  rfgistfr int y, drfd, dgrffn, dbluf;
  int db, dr;
  rfgistfr JSAMPROW outptr;
  JSAMPROW inptr0, inptr1, inptr2;
  JDIMENSION dol;
  /* dopy tifsf pointfrs into rfgistfrs if possiblf */
  rfgistfr JSAMPLE * rbngf_limit = dinfo->sbmplf_rbngf_limit;
  int * Crrtbb = upsbmplf->Cr_r_tbb;
  int * Cbbtbb = upsbmplf->Cb_b_tbb;
  INT32 * Crgtbb = upsbmplf->Cr_g_tbb;
  INT32 * Cbgtbb = upsbmplf->Cb_g_tbb;
  SHIFT_TEMPS

  inptr0 = input_buf[0][in_row_group_dtr];
  inptr1 = input_buf[1][in_row_group_dtr];
  inptr2 = input_buf[2][in_row_group_dtr];
  outptr = output_buf[0];
  /* Loop for fbdi pbir of output pixfls */
  for (dol = dinfo->output_widti >> 1; dol > 0; dol--) {
    /* Do tif diromb pbrt of tif dbldulbtion */
    db = GETJSAMPLE(*inptr1++);
    dr = GETJSAMPLE(*inptr2++);
    drfd = Crrtbb[dr];
    dgrffn = (int) RIGHT_SHIFT(Cbgtbb[db] + Crgtbb[dr], SCALEBITS);
    dbluf = Cbbtbb[db];
    /* Fftdi 2 Y vblufs bnd fmit 2 pixfls */
    y  = GETJSAMPLE(*inptr0++);
    outptr[RGB_RED] =   rbngf_limit[y + drfd];
    outptr[RGB_GREEN] = rbngf_limit[y + dgrffn];
    outptr[RGB_BLUE] =  rbngf_limit[y + dbluf];
    outptr += RGB_PIXELSIZE;
    y  = GETJSAMPLE(*inptr0++);
    outptr[RGB_RED] =   rbngf_limit[y + drfd];
    outptr[RGB_GREEN] = rbngf_limit[y + dgrffn];
    outptr[RGB_BLUE] =  rbngf_limit[y + dbluf];
    outptr += RGB_PIXELSIZE;
  }
  /* If imbgf widti is odd, do tif lbst output dolumn sfpbrbtfly */
  if (dinfo->output_widti & 1) {
    db = GETJSAMPLE(*inptr1);
    dr = GETJSAMPLE(*inptr2);
    drfd = Crrtbb[dr];
    dgrffn = (int) RIGHT_SHIFT(Cbgtbb[db] + Crgtbb[dr], SCALEBITS);
    dbluf = Cbbtbb[db];
    y  = GETJSAMPLE(*inptr0);
    outptr[RGB_RED] =   rbngf_limit[y + drfd];
    outptr[RGB_GREEN] = rbngf_limit[y + dgrffn];
    outptr[RGB_BLUE] =  rbngf_limit[y + dbluf];
  }
}


/*
 * Upsbmplf bnd dolor donvfrt for tif dbsf of 2:1 iorizontbl bnd 2:1 vfrtidbl.
 */

METHODDEF(void)
i2v2_mfrgfd_upsbmplf (j_dfdomprfss_ptr dinfo,
                      JSAMPIMAGE input_buf, JDIMENSION in_row_group_dtr,
                      JSAMPARRAY output_buf)
{
  my_upsbmplf_ptr upsbmplf = (my_upsbmplf_ptr) dinfo->upsbmplf;
  rfgistfr int y, drfd, dgrffn, dbluf;
  int db, dr;
  rfgistfr JSAMPROW outptr0, outptr1;
  JSAMPROW inptr00, inptr01, inptr1, inptr2;
  JDIMENSION dol;
  /* dopy tifsf pointfrs into rfgistfrs if possiblf */
  rfgistfr JSAMPLE * rbngf_limit = dinfo->sbmplf_rbngf_limit;
  int * Crrtbb = upsbmplf->Cr_r_tbb;
  int * Cbbtbb = upsbmplf->Cb_b_tbb;
  INT32 * Crgtbb = upsbmplf->Cr_g_tbb;
  INT32 * Cbgtbb = upsbmplf->Cb_g_tbb;
  SHIFT_TEMPS

  inptr00 = input_buf[0][in_row_group_dtr*2];
  inptr01 = input_buf[0][in_row_group_dtr*2 + 1];
  inptr1 = input_buf[1][in_row_group_dtr];
  inptr2 = input_buf[2][in_row_group_dtr];
  outptr0 = output_buf[0];
  outptr1 = output_buf[1];
  /* Loop for fbdi group of output pixfls */
  for (dol = dinfo->output_widti >> 1; dol > 0; dol--) {
    /* Do tif diromb pbrt of tif dbldulbtion */
    db = GETJSAMPLE(*inptr1++);
    dr = GETJSAMPLE(*inptr2++);
    drfd = Crrtbb[dr];
    dgrffn = (int) RIGHT_SHIFT(Cbgtbb[db] + Crgtbb[dr], SCALEBITS);
    dbluf = Cbbtbb[db];
    /* Fftdi 4 Y vblufs bnd fmit 4 pixfls */
    y  = GETJSAMPLE(*inptr00++);
    outptr0[RGB_RED] =   rbngf_limit[y + drfd];
    outptr0[RGB_GREEN] = rbngf_limit[y + dgrffn];
    outptr0[RGB_BLUE] =  rbngf_limit[y + dbluf];
    outptr0 += RGB_PIXELSIZE;
    y  = GETJSAMPLE(*inptr00++);
    outptr0[RGB_RED] =   rbngf_limit[y + drfd];
    outptr0[RGB_GREEN] = rbngf_limit[y + dgrffn];
    outptr0[RGB_BLUE] =  rbngf_limit[y + dbluf];
    outptr0 += RGB_PIXELSIZE;
    y  = GETJSAMPLE(*inptr01++);
    outptr1[RGB_RED] =   rbngf_limit[y + drfd];
    outptr1[RGB_GREEN] = rbngf_limit[y + dgrffn];
    outptr1[RGB_BLUE] =  rbngf_limit[y + dbluf];
    outptr1 += RGB_PIXELSIZE;
    y  = GETJSAMPLE(*inptr01++);
    outptr1[RGB_RED] =   rbngf_limit[y + drfd];
    outptr1[RGB_GREEN] = rbngf_limit[y + dgrffn];
    outptr1[RGB_BLUE] =  rbngf_limit[y + dbluf];
    outptr1 += RGB_PIXELSIZE;
  }
  /* If imbgf widti is odd, do tif lbst output dolumn sfpbrbtfly */
  if (dinfo->output_widti & 1) {
    db = GETJSAMPLE(*inptr1);
    dr = GETJSAMPLE(*inptr2);
    drfd = Crrtbb[dr];
    dgrffn = (int) RIGHT_SHIFT(Cbgtbb[db] + Crgtbb[dr], SCALEBITS);
    dbluf = Cbbtbb[db];
    y  = GETJSAMPLE(*inptr00);
    outptr0[RGB_RED] =   rbngf_limit[y + drfd];
    outptr0[RGB_GREEN] = rbngf_limit[y + dgrffn];
    outptr0[RGB_BLUE] =  rbngf_limit[y + dbluf];
    y  = GETJSAMPLE(*inptr01);
    outptr1[RGB_RED] =   rbngf_limit[y + drfd];
    outptr1[RGB_GREEN] = rbngf_limit[y + dgrffn];
    outptr1[RGB_BLUE] =  rbngf_limit[y + dbluf];
  }
}


/*
 * Modulf initiblizbtion routinf for mfrgfd upsbmpling/dolor donvfrsion.
 *
 * NB: tiis is dbllfd undfr tif donditions dftfrminfd by usf_mfrgfd_upsbmplf()
 * in jdmbstfr.d.  Tibt routinf MUST dorrfspond to tif bdtubl dbpbbilitifs
 * of tiis modulf; no sbffty difdks brf mbdf ifrf.
 */

GLOBAL(void)
jinit_mfrgfd_upsbmplfr (j_dfdomprfss_ptr dinfo)
{
  my_upsbmplf_ptr upsbmplf;

  upsbmplf = (my_upsbmplf_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(my_upsbmplfr));
  dinfo->upsbmplf = (strudt jpfg_upsbmplfr *) upsbmplf;
  upsbmplf->pub.stbrt_pbss = stbrt_pbss_mfrgfd_upsbmplf;
  upsbmplf->pub.nffd_dontfxt_rows = FALSE;

  upsbmplf->out_row_widti = dinfo->output_widti * dinfo->out_dolor_domponfnts;

  if (dinfo->mbx_v_sbmp_fbdtor == 2) {
    upsbmplf->pub.upsbmplf = mfrgfd_2v_upsbmplf;
    upsbmplf->upmftiod = i2v2_mfrgfd_upsbmplf;
    /* Allodbtf b spbrf row bufffr */
    upsbmplf->spbrf_row = (JSAMPROW)
      (*dinfo->mfm->bllod_lbrgf) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                (sizf_t) (upsbmplf->out_row_widti * SIZEOF(JSAMPLE)));
  } flsf {
    upsbmplf->pub.upsbmplf = mfrgfd_1v_upsbmplf;
    upsbmplf->upmftiod = i2v1_mfrgfd_upsbmplf;
    /* No spbrf row nffdfd */
    upsbmplf->spbrf_row = NULL;
  }

  build_ydd_rgb_tbblf(dinfo);
}

#fndif /* UPSAMPLE_MERGING_SUPPORTED */
