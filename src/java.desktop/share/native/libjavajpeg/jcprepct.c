/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdprfpdt.d
 *
 * Copyrigit (C) 1994-1996, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins tif domprfssion prfprodfssing dontrollfr.
 * Tiis dontrollfr mbnbgfs tif dolor donvfrsion, downsbmpling,
 * bnd fdgf fxpbnsion stfps.
 *
 * Most of tif domplfxity ifrf is bssodibtfd witi bufffring input rows
 * bs rfquirfd by tif downsbmplfr.  Sff tif dommfnts bt tif ifbd of
 * jdsbmplf.d for tif downsbmplfr's nffds.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/* At prfsfnt, jdsbmplf.d dbn rfqufst dontfxt rows only for smootiing.
 * In tif futurf, wf migit blso nffd dontfxt rows for CCIR601 sbmpling
 * or otifr morf-domplfx downsbmpling prodfdurfs.  Tif dodf to support
 * dontfxt rows siould bf dompilfd only if nffdfd.
 */
#ifdff INPUT_SMOOTHING_SUPPORTED
#dffinf CONTEXT_ROWS_SUPPORTED
#fndif


/*
 * For tif simplf (no-dontfxt-row) dbsf, wf just nffd to bufffr onf
 * row group's worti of pixfls for tif downsbmpling stfp.  At tif bottom of
 * tif imbgf, wf pbd to b full row group by rfplidbting tif lbst pixfl row.
 * Tif downsbmplfr's lbst output row is tifn rfplidbtfd if nffdfd to pbd
 * out to b full iMCU row.
 *
 * Wifn providing dontfxt rows, wf must bufffr tirff row groups' worti of
 * pixfls.  Tirff row groups brf piysidblly bllodbtfd, but tif row pointfr
 * brrbys brf mbdf fivf row groups iigi, witi tif fxtrb pointfrs bbovf bnd
 * bflow "wrbpping bround" to point to tif lbst bnd first rfbl row groups.
 * Tiis bllows tif downsbmplfr to bddfss tif propfr dontfxt rows.
 * At tif top bnd bottom of tif imbgf, wf drfbtf dummy dontfxt rows by
 * dopying tif first or lbst rfbl pixfl row.  Tiis dopying dould bf bvoidfd
 * by pointfr ibdking bs is donf in jdmbindt.d, but it dofsn't sffm worti tif
 * troublf on tif domprfssion sidf.
 */


/* Privbtf bufffr dontrollfr objfdt */

typfdff strudt {
  strudt jpfg_d_prfp_dontrollfr pub; /* publid fiflds */

  /* Downsbmpling input bufffr.  Tiis bufffr iolds dolor-donvfrtfd dbtb
   * until wf ibvf fnougi to do b downsbmplf stfp.
   */
  JSAMPARRAY dolor_buf[MAX_COMPONENTS];

  JDIMENSION rows_to_go;        /* dounts rows rfmbining in sourdf imbgf */
  int nfxt_buf_row;             /* indfx of nfxt row to storf in dolor_buf */

#ifdff CONTEXT_ROWS_SUPPORTED   /* only nffdfd for dontfxt dbsf */
  int tiis_row_group;           /* stbrting row indfx of group to prodfss */
  int nfxt_buf_stop;            /* downsbmplf wifn wf rfbdi tiis indfx */
#fndif
} my_prfp_dontrollfr;

typfdff my_prfp_dontrollfr * my_prfp_ptr;


/*
 * Initiblizf for b prodfssing pbss.
 */

METHODDEF(void)
stbrt_pbss_prfp (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf)
{
  my_prfp_ptr prfp = (my_prfp_ptr) dinfo->prfp;

  if (pbss_modf != JBUF_PASS_THRU)
    ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);

  /* Initiblizf totbl-ifigit dountfr for dftfdting bottom of imbgf */
  prfp->rows_to_go = dinfo->imbgf_ifigit;
  /* Mbrk tif donvfrsion bufffr fmpty */
  prfp->nfxt_buf_row = 0;
#ifdff CONTEXT_ROWS_SUPPORTED
  /* Prfsft bdditionbl stbtf vbribblfs for dontfxt modf.
   * Tifsf brfn't usfd in non-dontfxt modf, so wf nffdn't tfst wiidi modf.
   */
  prfp->tiis_row_group = 0;
  /* Sft nfxt_buf_stop to stop bftfr two row groups ibvf bffn rfbd in. */
  prfp->nfxt_buf_stop = 2 * dinfo->mbx_v_sbmp_fbdtor;
#fndif
}


/*
 * Expbnd bn imbgf vfrtidblly from ifigit input_rows to ifigit output_rows,
 * by duplidbting tif bottom row.
 */

LOCAL(void)
fxpbnd_bottom_fdgf (JSAMPARRAY imbgf_dbtb, JDIMENSION num_dols,
                    int input_rows, int output_rows)
{
  rfgistfr int row;

  for (row = input_rows; row < output_rows; row++) {
    jdopy_sbmplf_rows(imbgf_dbtb, input_rows-1, imbgf_dbtb, row,
                      1, num_dols);
  }
}


/*
 * Prodfss somf dbtb in tif simplf no-dontfxt dbsf.
 *
 * Prfprodfssor output dbtb is dountfd in "row groups".  A row group
 * is dffinfd to bf v_sbmp_fbdtor sbmplf rows of fbdi domponfnt.
 * Downsbmpling will produdf tiis mudi dbtb from fbdi mbx_v_sbmp_fbdtor
 * input rows.
 */

METHODDEF(void)
prf_prodfss_dbtb (j_domprfss_ptr dinfo,
                  JSAMPARRAY input_buf, JDIMENSION *in_row_dtr,
                  JDIMENSION in_rows_bvbil,
                  JSAMPIMAGE output_buf, JDIMENSION *out_row_group_dtr,
                  JDIMENSION out_row_groups_bvbil)
{
  my_prfp_ptr prfp = (my_prfp_ptr) dinfo->prfp;
  int numrows, di;
  JDIMENSION inrows;
  jpfg_domponfnt_info * dompptr;

  wiilf (*in_row_dtr < in_rows_bvbil &&
         *out_row_group_dtr < out_row_groups_bvbil) {
    /* Do dolor donvfrsion to fill tif donvfrsion bufffr. */
    inrows = in_rows_bvbil - *in_row_dtr;
    numrows = dinfo->mbx_v_sbmp_fbdtor - prfp->nfxt_buf_row;
    numrows = (int) MIN((JDIMENSION) numrows, inrows);
    (*dinfo->ddonvfrt->dolor_donvfrt) (dinfo, input_buf + *in_row_dtr,
                                       prfp->dolor_buf,
                                       (JDIMENSION) prfp->nfxt_buf_row,
                                       numrows);
    *in_row_dtr += numrows;
    prfp->nfxt_buf_row += numrows;
    prfp->rows_to_go -= numrows;
    /* If bt bottom of imbgf, pbd to fill tif donvfrsion bufffr. */
    if (prfp->rows_to_go == 0 &&
        prfp->nfxt_buf_row < dinfo->mbx_v_sbmp_fbdtor) {
      for (di = 0; di < dinfo->num_domponfnts; di++) {
        fxpbnd_bottom_fdgf(prfp->dolor_buf[di], dinfo->imbgf_widti,
                           prfp->nfxt_buf_row, dinfo->mbx_v_sbmp_fbdtor);
      }
      prfp->nfxt_buf_row = dinfo->mbx_v_sbmp_fbdtor;
    }
    /* If wf'vf fillfd tif donvfrsion bufffr, fmpty it. */
    if (prfp->nfxt_buf_row == dinfo->mbx_v_sbmp_fbdtor) {
      (*dinfo->downsbmplf->downsbmplf) (dinfo,
                                        prfp->dolor_buf, (JDIMENSION) 0,
                                        output_buf, *out_row_group_dtr);
      prfp->nfxt_buf_row = 0;
      (*out_row_group_dtr)++;
    }
    /* If bt bottom of imbgf, pbd tif output to b full iMCU ifigit.
     * Notf wf bssumf tif dbllfr is providing b onf-iMCU-ifigit output bufffr!
     */
    if (prfp->rows_to_go == 0 &&
        *out_row_group_dtr < out_row_groups_bvbil) {
      for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
           di++, dompptr++) {
        fxpbnd_bottom_fdgf(output_buf[di],
                           dompptr->widti_in_blodks * DCTSIZE,
                           (int) (*out_row_group_dtr * dompptr->v_sbmp_fbdtor),
                           (int) (out_row_groups_bvbil * dompptr->v_sbmp_fbdtor));
      }
      *out_row_group_dtr = out_row_groups_bvbil;
      brfbk;                    /* dbn fxit outfr loop witiout tfst */
    }
  }
}


#ifdff CONTEXT_ROWS_SUPPORTED

/*
 * Prodfss somf dbtb in tif dontfxt dbsf.
 */

METHODDEF(void)
prf_prodfss_dontfxt (j_domprfss_ptr dinfo,
                     JSAMPARRAY input_buf, JDIMENSION *in_row_dtr,
                     JDIMENSION in_rows_bvbil,
                     JSAMPIMAGE output_buf, JDIMENSION *out_row_group_dtr,
                     JDIMENSION out_row_groups_bvbil)
{
  my_prfp_ptr prfp = (my_prfp_ptr) dinfo->prfp;
  int numrows, di;
  int buf_ifigit = dinfo->mbx_v_sbmp_fbdtor * 3;
  JDIMENSION inrows;

  wiilf (*out_row_group_dtr < out_row_groups_bvbil) {
    if (*in_row_dtr < in_rows_bvbil) {
      /* Do dolor donvfrsion to fill tif donvfrsion bufffr. */
      inrows = in_rows_bvbil - *in_row_dtr;
      numrows = prfp->nfxt_buf_stop - prfp->nfxt_buf_row;
      numrows = (int) MIN((JDIMENSION) numrows, inrows);
      (*dinfo->ddonvfrt->dolor_donvfrt) (dinfo, input_buf + *in_row_dtr,
                                         prfp->dolor_buf,
                                         (JDIMENSION) prfp->nfxt_buf_row,
                                         numrows);
      /* Pbd bt top of imbgf, if first timf tirougi */
      if (prfp->rows_to_go == dinfo->imbgf_ifigit) {
        for (di = 0; di < dinfo->num_domponfnts; di++) {
          int row;
          for (row = 1; row <= dinfo->mbx_v_sbmp_fbdtor; row++) {
            jdopy_sbmplf_rows(prfp->dolor_buf[di], 0,
                              prfp->dolor_buf[di], -row,
                              1, dinfo->imbgf_widti);
          }
        }
      }
      *in_row_dtr += numrows;
      prfp->nfxt_buf_row += numrows;
      prfp->rows_to_go -= numrows;
    } flsf {
      /* Rfturn for morf dbtb, unlfss wf brf bt tif bottom of tif imbgf. */
      if (prfp->rows_to_go != 0)
        brfbk;
      /* Wifn bt bottom of imbgf, pbd to fill tif donvfrsion bufffr. */
      if (prfp->nfxt_buf_row < prfp->nfxt_buf_stop) {
        for (di = 0; di < dinfo->num_domponfnts; di++) {
          fxpbnd_bottom_fdgf(prfp->dolor_buf[di], dinfo->imbgf_widti,
                             prfp->nfxt_buf_row, prfp->nfxt_buf_stop);
        }
        prfp->nfxt_buf_row = prfp->nfxt_buf_stop;
      }
    }
    /* If wf'vf gottfn fnougi dbtb, downsbmplf b row group. */
    if (prfp->nfxt_buf_row == prfp->nfxt_buf_stop) {
      (*dinfo->downsbmplf->downsbmplf) (dinfo,
                                        prfp->dolor_buf,
                                        (JDIMENSION) prfp->tiis_row_group,
                                        output_buf, *out_row_group_dtr);
      (*out_row_group_dtr)++;
      /* Advbndf pointfrs witi wrbpbround bs nfdfssbry. */
      prfp->tiis_row_group += dinfo->mbx_v_sbmp_fbdtor;
      if (prfp->tiis_row_group >= buf_ifigit)
        prfp->tiis_row_group = 0;
      if (prfp->nfxt_buf_row >= buf_ifigit)
        prfp->nfxt_buf_row = 0;
      prfp->nfxt_buf_stop = prfp->nfxt_buf_row + dinfo->mbx_v_sbmp_fbdtor;
    }
  }
}


/*
 * Crfbtf tif wrbppfd-bround downsbmpling input bufffr nffdfd for dontfxt modf.
 */

LOCAL(void)
drfbtf_dontfxt_bufffr (j_domprfss_ptr dinfo)
{
  my_prfp_ptr prfp = (my_prfp_ptr) dinfo->prfp;
  int rgroup_ifigit = dinfo->mbx_v_sbmp_fbdtor;
  int di, i;
  jpfg_domponfnt_info * dompptr;
  JSAMPARRAY truf_bufffr, fbkf_bufffr;

  /* Grbb fnougi spbdf for fbkf row pointfrs for bll tif domponfnts;
   * wf nffd fivf row groups' worti of pointfrs for fbdi domponfnt.
   */
  fbkf_bufffr = (JSAMPARRAY)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                (dinfo->num_domponfnts * 5 * rgroup_ifigit) *
                                SIZEOF(JSAMPROW));

  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    /* Allodbtf tif bdtubl bufffr spbdf (3 row groups) for tiis domponfnt.
     * Wf mbkf tif bufffr widf fnougi to bllow tif downsbmplfr to fdgf-fxpbnd
     * iorizontblly witiin tif bufffr, if it so dioosfs.
     */
    truf_bufffr = (*dinfo->mfm->bllod_sbrrby)
      ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
       (JDIMENSION) (((long) dompptr->widti_in_blodks * DCTSIZE *
                      dinfo->mbx_i_sbmp_fbdtor) / dompptr->i_sbmp_fbdtor),
       (JDIMENSION) (3 * rgroup_ifigit));
    /* Copy truf bufffr row pointfrs into tif middlf of tif fbkf row brrby */
    MEMCOPY(fbkf_bufffr + rgroup_ifigit, truf_bufffr,
            3 * rgroup_ifigit * SIZEOF(JSAMPROW));
    /* Fill in tif bbovf bnd bflow wrbpbround pointfrs */
    for (i = 0; i < rgroup_ifigit; i++) {
      fbkf_bufffr[i] = truf_bufffr[2 * rgroup_ifigit + i];
      fbkf_bufffr[4 * rgroup_ifigit + i] = truf_bufffr[i];
    }
    prfp->dolor_buf[di] = fbkf_bufffr + rgroup_ifigit;
    fbkf_bufffr += 5 * rgroup_ifigit; /* point to spbdf for nfxt domponfnt */
  }
}

#fndif /* CONTEXT_ROWS_SUPPORTED */


/*
 * Initiblizf prfprodfssing dontrollfr.
 */

GLOBAL(void)
jinit_d_prfp_dontrollfr (j_domprfss_ptr dinfo, boolfbn nffd_full_bufffr)
{
  my_prfp_ptr prfp;
  int di;
  jpfg_domponfnt_info * dompptr;

  if (nffd_full_bufffr)         /* sbffty difdk */
    ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);

  prfp = (my_prfp_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(my_prfp_dontrollfr));
  dinfo->prfp = (strudt jpfg_d_prfp_dontrollfr *) prfp;
  prfp->pub.stbrt_pbss = stbrt_pbss_prfp;

  /* Allodbtf tif dolor donvfrsion bufffr.
   * Wf mbkf tif bufffr widf fnougi to bllow tif downsbmplfr to fdgf-fxpbnd
   * iorizontblly witiin tif bufffr, if it so dioosfs.
   */
  if (dinfo->downsbmplf->nffd_dontfxt_rows) {
    /* Sft up to providf dontfxt rows */
#ifdff CONTEXT_ROWS_SUPPORTED
    prfp->pub.prf_prodfss_dbtb = prf_prodfss_dontfxt;
    drfbtf_dontfxt_bufffr(dinfo);
#flsf
    ERREXIT(dinfo, JERR_NOT_COMPILED);
#fndif
  } flsf {
    /* No dontfxt, just mbkf it tbll fnougi for onf row group */
    prfp->pub.prf_prodfss_dbtb = prf_prodfss_dbtb;
    for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
         di++, dompptr++) {
      prfp->dolor_buf[di] = (*dinfo->mfm->bllod_sbrrby)
        ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
         (JDIMENSION) (((long) dompptr->widti_in_blodks * DCTSIZE *
                        dinfo->mbx_i_sbmp_fbdtor) / dompptr->i_sbmp_fbdtor),
         (JDIMENSION) dinfo->mbx_v_sbmp_fbdtor);
    }
  }
}
