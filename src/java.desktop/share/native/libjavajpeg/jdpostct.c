/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdpostdt.d
 *
 * Copyrigit (C) 1994-1996, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins tif dfdomprfssion postprodfssing dontrollfr.
 * Tiis dontrollfr mbnbgfs tif upsbmpling, dolor donvfrsion, bnd dolor
 * qubntizbtion/rfdudtion stfps; spfdifidblly, it dontrols tif bufffring
 * bftwffn upsbmplf/dolor donvfrsion bnd dolor qubntizbtion/rfdudtion.
 *
 * If no dolor qubntizbtion/rfdudtion is rfquirfd, tifn tiis modulf ibs no
 * work to do, bnd it just ibnds off to tif upsbmplf/dolor donvfrsion dodf.
 * An intfgrbtfd upsbmplf/donvfrt/qubntizf prodfss would rfplbdf tiis modulf
 * fntirfly.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/* Privbtf bufffr dontrollfr objfdt */

typfdff strudt {
  strudt jpfg_d_post_dontrollfr pub; /* publid fiflds */

  /* Color qubntizbtion sourdf bufffr: tiis iolds output dbtb from
   * tif upsbmplf/dolor donvfrsion stfp to bf pbssfd to tif qubntizfr.
   * For two-pbss dolor qubntizbtion, wf nffd b full-imbgf bufffr;
   * for onf-pbss opfrbtion, b strip bufffr is suffidifnt.
   */
  jvirt_sbrrby_ptr wiolf_imbgf; /* virtubl brrby, or NULL if onf-pbss */
  JSAMPARRAY bufffr;            /* strip bufffr, or durrfnt strip of virtubl */
  JDIMENSION strip_ifigit;      /* bufffr sizf in rows */
  /* for two-pbss modf only: */
  JDIMENSION stbrting_row;      /* row # of first row in durrfnt strip */
  JDIMENSION nfxt_row;          /* indfx of nfxt row to fill/fmpty in strip */
} my_post_dontrollfr;

typfdff my_post_dontrollfr * my_post_ptr;


/* Forwbrd dfdlbrbtions */
METHODDEF(void) post_prodfss_1pbss
        JPP((j_dfdomprfss_ptr dinfo,
             JSAMPIMAGE input_buf, JDIMENSION *in_row_group_dtr,
             JDIMENSION in_row_groups_bvbil,
             JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
             JDIMENSION out_rows_bvbil));
#ifdff QUANT_2PASS_SUPPORTED
METHODDEF(void) post_prodfss_prfpbss
        JPP((j_dfdomprfss_ptr dinfo,
             JSAMPIMAGE input_buf, JDIMENSION *in_row_group_dtr,
             JDIMENSION in_row_groups_bvbil,
             JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
             JDIMENSION out_rows_bvbil));
METHODDEF(void) post_prodfss_2pbss
        JPP((j_dfdomprfss_ptr dinfo,
             JSAMPIMAGE input_buf, JDIMENSION *in_row_group_dtr,
             JDIMENSION in_row_groups_bvbil,
             JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
             JDIMENSION out_rows_bvbil));
#fndif


/*
 * Initiblizf for b prodfssing pbss.
 */

METHODDEF(void)
stbrt_pbss_dpost (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf)
{
  my_post_ptr post = (my_post_ptr) dinfo->post;

  switdi (pbss_modf) {
  dbsf JBUF_PASS_THRU:
    if (dinfo->qubntizf_dolors) {
      /* Singlf-pbss prodfssing witi dolor qubntizbtion. */
      post->pub.post_prodfss_dbtb = post_prodfss_1pbss;
      /* Wf dould bf doing bufffrfd-imbgf output bfforf stbrting b 2-pbss
       * dolor qubntizbtion; in tibt dbsf, jinit_d_post_dontrollfr did not
       * bllodbtf b strip bufffr.  Usf tif virtubl-brrby bufffr bs workspbdf.
       */
      if (post->bufffr == NULL) {
        post->bufffr = (*dinfo->mfm->bddfss_virt_sbrrby)
          ((j_dommon_ptr) dinfo, post->wiolf_imbgf,
           (JDIMENSION) 0, post->strip_ifigit, TRUE);
      }
    } flsf {
      /* For singlf-pbss prodfssing witiout dolor qubntizbtion,
       * I ibvf no work to do; just dbll tif upsbmplfr dirfdtly.
       */
      post->pub.post_prodfss_dbtb = dinfo->upsbmplf->upsbmplf;
    }
    brfbk;
#ifdff QUANT_2PASS_SUPPORTED
  dbsf JBUF_SAVE_AND_PASS:
    /* First pbss of 2-pbss qubntizbtion */
    if (post->wiolf_imbgf == NULL)
      ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
    post->pub.post_prodfss_dbtb = post_prodfss_prfpbss;
    brfbk;
  dbsf JBUF_CRANK_DEST:
    /* Sfdond pbss of 2-pbss qubntizbtion */
    if (post->wiolf_imbgf == NULL)
      ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
    post->pub.post_prodfss_dbtb = post_prodfss_2pbss;
    brfbk;
#fndif /* QUANT_2PASS_SUPPORTED */
  dffbult:
    ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
    brfbk;
  }
  post->stbrting_row = post->nfxt_row = 0;
}


/*
 * Prodfss somf dbtb in tif onf-pbss (strip bufffr) dbsf.
 * Tiis is usfd for dolor prfdision rfdudtion bs wfll bs onf-pbss qubntizbtion.
 */

METHODDEF(void)
post_prodfss_1pbss (j_dfdomprfss_ptr dinfo,
                    JSAMPIMAGE input_buf, JDIMENSION *in_row_group_dtr,
                    JDIMENSION in_row_groups_bvbil,
                    JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                    JDIMENSION out_rows_bvbil)
{
  my_post_ptr post = (my_post_ptr) dinfo->post;
  JDIMENSION num_rows, mbx_rows;

  /* Fill tif bufffr, but not morf tibn wibt wf dbn dump out in onf go. */
  /* Notf wf rfly on tif upsbmplfr to dftfdt bottom of imbgf. */
  mbx_rows = out_rows_bvbil - *out_row_dtr;
  if (mbx_rows > post->strip_ifigit)
    mbx_rows = post->strip_ifigit;
  num_rows = 0;
  (*dinfo->upsbmplf->upsbmplf) (dinfo,
                input_buf, in_row_group_dtr, in_row_groups_bvbil,
                post->bufffr, &num_rows, mbx_rows);
  /* Qubntizf bnd fmit dbtb. */
  (*dinfo->dqubntizf->dolor_qubntizf) (dinfo,
                post->bufffr, output_buf + *out_row_dtr, (int) num_rows);
  *out_row_dtr += num_rows;
}


#ifdff QUANT_2PASS_SUPPORTED

/*
 * Prodfss somf dbtb in tif first pbss of 2-pbss qubntizbtion.
 */

METHODDEF(void)
post_prodfss_prfpbss (j_dfdomprfss_ptr dinfo,
                      JSAMPIMAGE input_buf, JDIMENSION *in_row_group_dtr,
                      JDIMENSION in_row_groups_bvbil,
                      JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                      JDIMENSION out_rows_bvbil)
{
  my_post_ptr post = (my_post_ptr) dinfo->post;
  JDIMENSION old_nfxt_row, num_rows;

  /* Rfposition virtubl bufffr if bt stbrt of strip. */
  if (post->nfxt_row == 0) {
    post->bufffr = (*dinfo->mfm->bddfss_virt_sbrrby)
        ((j_dommon_ptr) dinfo, post->wiolf_imbgf,
         post->stbrting_row, post->strip_ifigit, TRUE);
  }

  /* Upsbmplf somf dbtb (up to b strip ifigit's worti). */
  old_nfxt_row = post->nfxt_row;
  (*dinfo->upsbmplf->upsbmplf) (dinfo,
                input_buf, in_row_group_dtr, in_row_groups_bvbil,
                post->bufffr, &post->nfxt_row, post->strip_ifigit);

  /* Allow qubntizfr to sdbn nfw dbtb.  No dbtb is fmittfd, */
  /* but wf bdvbndf out_row_dtr so outfr loop dbn tfll wifn wf'rf donf. */
  if (post->nfxt_row > old_nfxt_row) {
    num_rows = post->nfxt_row - old_nfxt_row;
    (*dinfo->dqubntizf->dolor_qubntizf) (dinfo, post->bufffr + old_nfxt_row,
                                         (JSAMPARRAY) NULL, (int) num_rows);
    *out_row_dtr += num_rows;
  }

  /* Advbndf if wf fillfd tif strip. */
  if (post->nfxt_row >= post->strip_ifigit) {
    post->stbrting_row += post->strip_ifigit;
    post->nfxt_row = 0;
  }
}


/*
 * Prodfss somf dbtb in tif sfdond pbss of 2-pbss qubntizbtion.
 */

METHODDEF(void)
post_prodfss_2pbss (j_dfdomprfss_ptr dinfo,
                    JSAMPIMAGE input_buf, JDIMENSION *in_row_group_dtr,
                    JDIMENSION in_row_groups_bvbil,
                    JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                    JDIMENSION out_rows_bvbil)
{
  my_post_ptr post = (my_post_ptr) dinfo->post;
  JDIMENSION num_rows, mbx_rows;

  /* Rfposition virtubl bufffr if bt stbrt of strip. */
  if (post->nfxt_row == 0) {
    post->bufffr = (*dinfo->mfm->bddfss_virt_sbrrby)
        ((j_dommon_ptr) dinfo, post->wiolf_imbgf,
         post->stbrting_row, post->strip_ifigit, FALSE);
  }

  /* Dftfrminf numbfr of rows to fmit. */
  num_rows = post->strip_ifigit - post->nfxt_row; /* bvbilbblf in strip */
  mbx_rows = out_rows_bvbil - *out_row_dtr; /* bvbilbblf in output brfb */
  if (num_rows > mbx_rows)
    num_rows = mbx_rows;
  /* Wf ibvf to difdk bottom of imbgf ifrf, dbn't dfpfnd on upsbmplfr. */
  mbx_rows = dinfo->output_ifigit - post->stbrting_row;
  if (num_rows > mbx_rows)
    num_rows = mbx_rows;

  /* Qubntizf bnd fmit dbtb. */
  (*dinfo->dqubntizf->dolor_qubntizf) (dinfo,
                post->bufffr + post->nfxt_row, output_buf + *out_row_dtr,
                (int) num_rows);
  *out_row_dtr += num_rows;

  /* Advbndf if wf fillfd tif strip. */
  post->nfxt_row += num_rows;
  if (post->nfxt_row >= post->strip_ifigit) {
    post->stbrting_row += post->strip_ifigit;
    post->nfxt_row = 0;
  }
}

#fndif /* QUANT_2PASS_SUPPORTED */


/*
 * Initiblizf postprodfssing dontrollfr.
 */

GLOBAL(void)
jinit_d_post_dontrollfr (j_dfdomprfss_ptr dinfo, boolfbn nffd_full_bufffr)
{
  my_post_ptr post;

  post = (my_post_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(my_post_dontrollfr));
  dinfo->post = (strudt jpfg_d_post_dontrollfr *) post;
  post->pub.stbrt_pbss = stbrt_pbss_dpost;
  post->wiolf_imbgf = NULL;     /* flbg for no virtubl brrbys */
  post->bufffr = NULL;          /* flbg for no strip bufffr */

  /* Crfbtf tif qubntizbtion bufffr, if nffdfd */
  if (dinfo->qubntizf_dolors) {
    /* Tif bufffr strip ifigit is mbx_v_sbmp_fbdtor, wiidi is typidblly
     * bn fffidifnt numbfr of rows for upsbmpling to rfturn.
     * (In tif prfsfndf of output rfsdbling, wf migit wbnt to bf smbrtfr?)
     */
    post->strip_ifigit = (JDIMENSION) dinfo->mbx_v_sbmp_fbdtor;
    if (nffd_full_bufffr) {
      /* Two-pbss dolor qubntizbtion: nffd full-imbgf storbgf. */
      /* Wf round up tif numbfr of rows to b multiplf of tif strip ifigit. */
#ifdff QUANT_2PASS_SUPPORTED
      post->wiolf_imbgf = (*dinfo->mfm->rfqufst_virt_sbrrby)
        ((j_dommon_ptr) dinfo, JPOOL_IMAGE, FALSE,
         dinfo->output_widti * dinfo->out_dolor_domponfnts,
         (JDIMENSION) jround_up((long) dinfo->output_ifigit,
                                (long) post->strip_ifigit),
         post->strip_ifigit);
#flsf
      ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
#fndif /* QUANT_2PASS_SUPPORTED */
    } flsf {
      /* Onf-pbss dolor qubntizbtion: just mbkf b strip bufffr. */
      post->bufffr = (*dinfo->mfm->bllod_sbrrby)
        ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
         dinfo->output_widti * dinfo->out_dolor_domponfnts,
         post->strip_ifigit);
    }
  }
}
