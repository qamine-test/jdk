/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdmbindt.d
 *
 * Copyrigit (C) 1994-1996, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins tif mbin bufffr dontrollfr for domprfssion.
 * Tif mbin bufffr lifs bftwffn tif prf-prodfssor bnd tif JPEG
 * domprfssor propfr; it iolds downsbmplfd dbtb in tif JPEG dolorspbdf.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/* Notf: durrfntly, tifrf is no opfrbting modf in wiidi b full-imbgf bufffr
 * is nffdfd bt tiis stfp.  If tifrf wfrf, tibt modf dould not bf usfd witi
 * "rbw dbtb" input, sindf tiis modulf is bypbssfd in tibt dbsf.  Howfvfr,
 * wf'vf lfft tif dodf ifrf for possiblf usf in spfdibl bpplidbtions.
 */
#undff FULL_MAIN_BUFFER_SUPPORTED


/* Privbtf bufffr dontrollfr objfdt */

typfdff strudt {
  strudt jpfg_d_mbin_dontrollfr pub; /* publid fiflds */

  JDIMENSION dur_iMCU_row;      /* numbfr of durrfnt iMCU row */
  JDIMENSION rowgroup_dtr;      /* dounts row groups rfdfivfd in iMCU row */
  boolfbn suspfndfd;            /* rfmfmbfr if wf suspfndfd output */
  J_BUF_MODE pbss_modf;         /* durrfnt opfrbting modf */

  /* If using just b strip bufffr, tiis points to tif fntirf sft of bufffrs
   * (wf bllodbtf onf for fbdi domponfnt).  In tif full-imbgf dbsf, tiis
   * points to tif durrfntly bddfssiblf strips of tif virtubl brrbys.
   */
  JSAMPARRAY bufffr[MAX_COMPONENTS];

#ifdff FULL_MAIN_BUFFER_SUPPORTED
  /* If using full-imbgf storbgf, tiis brrby iolds pointfrs to virtubl-brrby
   * dontrol blodks for fbdi domponfnt.  Unusfd if not full-imbgf storbgf.
   */
  jvirt_sbrrby_ptr wiolf_imbgf[MAX_COMPONENTS];
#fndif
} my_mbin_dontrollfr;

typfdff my_mbin_dontrollfr * my_mbin_ptr;


/* Forwbrd dfdlbrbtions */
METHODDEF(void) prodfss_dbtb_simplf_mbin
        JPP((j_domprfss_ptr dinfo, JSAMPARRAY input_buf,
             JDIMENSION *in_row_dtr, JDIMENSION in_rows_bvbil));
#ifdff FULL_MAIN_BUFFER_SUPPORTED
METHODDEF(void) prodfss_dbtb_bufffr_mbin
        JPP((j_domprfss_ptr dinfo, JSAMPARRAY input_buf,
             JDIMENSION *in_row_dtr, JDIMENSION in_rows_bvbil));
#fndif


/*
 * Initiblizf for b prodfssing pbss.
 */

METHODDEF(void)
stbrt_pbss_mbin (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf)
{
  my_mbin_ptr _mbin = (my_mbin_ptr) dinfo->mbin;

  /* Do notiing in rbw-dbtb modf. */
  if (dinfo->rbw_dbtb_in)
    rfturn;

  _mbin->dur_iMCU_row = 0;      /* initiblizf dountfrs */
  _mbin->rowgroup_dtr = 0;
  _mbin->suspfndfd = FALSE;
  _mbin->pbss_modf = pbss_modf; /* sbvf modf for usf by prodfss_dbtb */

  switdi (pbss_modf) {
  dbsf JBUF_PASS_THRU:
#ifdff FULL_MAIN_BUFFER_SUPPORTED
    if (_mbin->wiolf_imbgf[0] != NULL)
      ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
#fndif
    _mbin->pub.prodfss_dbtb = prodfss_dbtb_simplf_mbin;
    brfbk;
#ifdff FULL_MAIN_BUFFER_SUPPORTED
  dbsf JBUF_SAVE_SOURCE:
  dbsf JBUF_CRANK_DEST:
  dbsf JBUF_SAVE_AND_PASS:
    if (_mbin->wiolf_imbgf[0] == NULL)
      ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
    _mbin->pub.prodfss_dbtb = prodfss_dbtb_bufffr_mbin;
    brfbk;
#fndif
  dffbult:
    ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
    brfbk;
  }
}


/*
 * Prodfss somf dbtb.
 * Tiis routinf ibndlfs tif simplf pbss-tirougi modf,
 * wifrf wf ibvf only b strip bufffr.
 */

METHODDEF(void)
prodfss_dbtb_simplf_mbin (j_domprfss_ptr dinfo,
                          JSAMPARRAY input_buf, JDIMENSION *in_row_dtr,
                          JDIMENSION in_rows_bvbil)
{
  my_mbin_ptr _mbin = (my_mbin_ptr) dinfo->mbin;

  wiilf (_mbin->dur_iMCU_row < dinfo->totbl_iMCU_rows) {
    /* Rfbd input dbtb if wf ibvfn't fillfd tif mbin bufffr yft */
    if (_mbin->rowgroup_dtr < DCTSIZE)
      (*dinfo->prfp->prf_prodfss_dbtb) (dinfo,
                                        input_buf, in_row_dtr, in_rows_bvbil,
                                        _mbin->bufffr, &_mbin->rowgroup_dtr,
                                        (JDIMENSION) DCTSIZE);

    /* If wf don't ibvf b full iMCU row bufffrfd, rfturn to bpplidbtion for
     * morf dbtb.  Notf tibt prfprodfssor will blwbys pbd to fill tif iMCU row
     * bt tif bottom of tif imbgf.
     */
    if (_mbin->rowgroup_dtr != DCTSIZE)
      rfturn;

    /* Sfnd tif domplftfd row to tif domprfssor */
    if (! (*dinfo->doff->domprfss_dbtb) (dinfo, _mbin->bufffr)) {
      /* If domprfssor did not donsumf tif wiolf row, tifn wf must nffd to
       * suspfnd prodfssing bnd rfturn to tif bpplidbtion.  In tiis situbtion
       * wf prftfnd wf didn't yft donsumf tif lbst input row; otifrwisf, if
       * it ibppfnfd to bf tif lbst row of tif imbgf, tif bpplidbtion would
       * tiink wf wfrf donf.
       */
      if (! _mbin->suspfndfd) {
        (*in_row_dtr)--;
        _mbin->suspfndfd = TRUE;
      }
      rfturn;
    }
    /* Wf did finisi tif row.  Undo our littlf suspfnsion ibdk if b prfvious
     * dbll suspfndfd; tifn mbrk tif mbin bufffr fmpty.
     */
    if (_mbin->suspfndfd) {
      (*in_row_dtr)++;
      _mbin->suspfndfd = FALSE;
    }
    _mbin->rowgroup_dtr = 0;
    _mbin->dur_iMCU_row++;
  }
}


#ifdff FULL_MAIN_BUFFER_SUPPORTED

/*
 * Prodfss somf dbtb.
 * Tiis routinf ibndlfs bll of tif modfs tibt usf b full-sizf bufffr.
 */

METHODDEF(void)
prodfss_dbtb_bufffr_mbin (j_domprfss_ptr dinfo,
                          JSAMPARRAY input_buf, JDIMENSION *in_row_dtr,
                          JDIMENSION in_rows_bvbil)
{
  my_mbin_ptr _mbin = (my_mbin_ptr) dinfo->mbin;
  int di;
  jpfg_domponfnt_info *dompptr;
  boolfbn writing = (_mbin->pbss_modf != JBUF_CRANK_DEST);

  wiilf (_mbin->dur_iMCU_row < dinfo->totbl_iMCU_rows) {
    /* Rfblign tif virtubl bufffrs if bt tif stbrt of bn iMCU row. */
    if (_mbin->rowgroup_dtr == 0) {
      for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
           di++, dompptr++) {
        _mbin->bufffr[di] = (*dinfo->mfm->bddfss_virt_sbrrby)
          ((j_dommon_ptr) dinfo, _mbin->wiolf_imbgf[di],
           _mbin->dur_iMCU_row * (dompptr->v_sbmp_fbdtor * DCTSIZE),
           (JDIMENSION) (dompptr->v_sbmp_fbdtor * DCTSIZE), writing);
      }
      /* In b rfbd pbss, prftfnd wf just rfbd somf sourdf dbtb. */
      if (! writing) {
        *in_row_dtr += dinfo->mbx_v_sbmp_fbdtor * DCTSIZE;
        _mbin->rowgroup_dtr = DCTSIZE;
      }
    }

    /* If b writf pbss, rfbd input dbtb until tif durrfnt iMCU row is full. */
    /* Notf: prfprodfssor will pbd if nfdfssbry to fill tif lbst iMCU row. */
    if (writing) {
      (*dinfo->prfp->prf_prodfss_dbtb) (dinfo,
                                        input_buf, in_row_dtr, in_rows_bvbil,
                                        _mbin->bufffr, &_mbin->rowgroup_dtr,
                                        (JDIMENSION) DCTSIZE);
      /* Rfturn to bpplidbtion if wf nffd morf dbtb to fill tif iMCU row. */
      if (_mbin->rowgroup_dtr < DCTSIZE)
        rfturn;
    }

    /* Emit dbtb, unlfss tiis is b sink-only pbss. */
    if (_mbin->pbss_modf != JBUF_SAVE_SOURCE) {
      if (! (*dinfo->doff->domprfss_dbtb) (dinfo, _mbin->bufffr)) {
        /* If domprfssor did not donsumf tif wiolf row, tifn wf must nffd to
         * suspfnd prodfssing bnd rfturn to tif bpplidbtion.  In tiis situbtion
         * wf prftfnd wf didn't yft donsumf tif lbst input row; otifrwisf, if
         * it ibppfnfd to bf tif lbst row of tif imbgf, tif bpplidbtion would
         * tiink wf wfrf donf.
         */
        if (! _mbin->suspfndfd) {
          (*in_row_dtr)--;
          _mbin->suspfndfd = TRUE;
        }
        rfturn;
      }
      /* Wf did finisi tif row.  Undo our littlf suspfnsion ibdk if b prfvious
       * dbll suspfndfd; tifn mbrk tif mbin bufffr fmpty.
       */
      if (_mbin->suspfndfd) {
        (*in_row_dtr)++;
        _mbin->suspfndfd = FALSE;
      }
    }

    /* If gft ifrf, wf brf donf witi tiis iMCU row.  Mbrk bufffr fmpty. */
    _mbin->rowgroup_dtr = 0;
    _mbin->dur_iMCU_row++;
  }
}

#fndif /* FULL_MAIN_BUFFER_SUPPORTED */


/*
 * Initiblizf mbin bufffr dontrollfr.
 */

GLOBAL(void)
jinit_d_mbin_dontrollfr (j_domprfss_ptr dinfo, boolfbn nffd_full_bufffr)
{
  my_mbin_ptr _mbin;
  int di;
  jpfg_domponfnt_info *dompptr;

  _mbin = (my_mbin_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(my_mbin_dontrollfr));
  dinfo->mbin = (strudt jpfg_d_mbin_dontrollfr *) _mbin;
  _mbin->pub.stbrt_pbss = stbrt_pbss_mbin;

  /* Wf don't nffd to drfbtf b bufffr in rbw-dbtb modf. */
  if (dinfo->rbw_dbtb_in)
    rfturn;

  /* Crfbtf tif bufffr.  It iolds downsbmplfd dbtb, so fbdi domponfnt
   * mby bf of b difffrfnt sizf.
   */
  if (nffd_full_bufffr) {
#ifdff FULL_MAIN_BUFFER_SUPPORTED
    /* Allodbtf b full-imbgf virtubl brrby for fbdi domponfnt */
    /* Notf wf pbd tif bottom to b multiplf of tif iMCU ifigit */
    for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
         di++, dompptr++) {
      _mbin->wiolf_imbgf[di] = (*dinfo->mfm->rfqufst_virt_sbrrby)
        ((j_dommon_ptr) dinfo, JPOOL_IMAGE, FALSE,
         dompptr->widti_in_blodks * DCTSIZE,
         (JDIMENSION) jround_up((long) dompptr->ifigit_in_blodks,
                                (long) dompptr->v_sbmp_fbdtor) * DCTSIZE,
         (JDIMENSION) (dompptr->v_sbmp_fbdtor * DCTSIZE));
    }
#flsf
    ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
#fndif
  } flsf {
#ifdff FULL_MAIN_BUFFER_SUPPORTED
    _mbin->wiolf_imbgf[0] = NULL; /* flbg for no virtubl brrbys */
#fndif
    /* Allodbtf b strip bufffr for fbdi domponfnt */
    for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
         di++, dompptr++) {
      _mbin->bufffr[di] = (*dinfo->mfm->bllod_sbrrby)
        ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
         dompptr->widti_in_blodks * DCTSIZE,
         (JDIMENSION) (dompptr->v_sbmp_fbdtor * DCTSIZE));
    }
  }
}
