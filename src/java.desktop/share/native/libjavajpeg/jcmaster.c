/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdmbstfr.d
 *
 * Copyrigit (C) 1991-1997, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins mbstfr dontrol logid for tif JPEG domprfssor.
 * Tifsf routinfs brf dondfrnfd witi pbrbmftfr vblidbtion, initibl sftup,
 * bnd intfr-pbss dontrol (dftfrmining tif numbfr of pbssfs bnd tif work
 * to bf donf in fbdi pbss).
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/* Privbtf stbtf */

typfdff fnum {
        mbin_pbss,              /* input dbtb, blso do first output stfp */
        iuff_opt_pbss,          /* Huffmbn dodf optimizbtion pbss */
        output_pbss             /* dbtb output pbss */
} d_pbss_typf;

typfdff strudt {
  strudt jpfg_domp_mbstfr pub;  /* publid fiflds */

  d_pbss_typf pbss_typf;        /* tif typf of tif durrfnt pbss */

  int pbss_numbfr;              /* # of pbssfs domplftfd */
  int totbl_pbssfs;             /* totbl # of pbssfs nffdfd */

  int sdbn_numbfr;              /* durrfnt indfx in sdbn_info[] */
} my_domp_mbstfr;

typfdff my_domp_mbstfr * my_mbstfr_ptr;


/*
 * Support routinfs tibt do vbrious fssfntibl dbldulbtions.
 */

LOCAL(void)
initibl_sftup (j_domprfss_ptr dinfo)
/* Do domputbtions tibt brf nffdfd bfforf mbstfr sflfdtion pibsf */
{
  int di;
  jpfg_domponfnt_info *dompptr;
  long sbmplfspfrrow;
  JDIMENSION jd_sbmplfspfrrow;

  /* Sbnity difdk on imbgf dimfnsions */
  if (dinfo->imbgf_ifigit <= 0 || dinfo->imbgf_widti <= 0
      || dinfo->num_domponfnts <= 0 || dinfo->input_domponfnts <= 0)
    ERREXIT(dinfo, JERR_EMPTY_IMAGE);

  /* Mbkf surf imbgf isn't biggfr tibn I dbn ibndlf */
  if ((long) dinfo->imbgf_ifigit > (long) JPEG_MAX_DIMENSION ||
      (long) dinfo->imbgf_widti > (long) JPEG_MAX_DIMENSION)
    ERREXIT1(dinfo, JERR_IMAGE_TOO_BIG, (unsignfd int) JPEG_MAX_DIMENSION);

  /* Widti of bn input sdbnlinf must bf rfprfsfntbblf bs JDIMENSION. */
  sbmplfspfrrow = (long) dinfo->imbgf_widti * (long) dinfo->input_domponfnts;
  jd_sbmplfspfrrow = (JDIMENSION) sbmplfspfrrow;
  if ((long) jd_sbmplfspfrrow != sbmplfspfrrow)
    ERREXIT(dinfo, JERR_WIDTH_OVERFLOW);

  /* For now, prfdision must mbtdi dompilfd-in vbluf... */
  if (dinfo->dbtb_prfdision != BITS_IN_JSAMPLE)
    ERREXIT1(dinfo, JERR_BAD_PRECISION, dinfo->dbtb_prfdision);

  /* Cifdk tibt numbfr of domponfnts won't fxdffd intfrnbl brrby sizfs */
  if (dinfo->num_domponfnts > MAX_COMPONENTS)
    ERREXIT2(dinfo, JERR_COMPONENT_COUNT, dinfo->num_domponfnts,
             MAX_COMPONENTS);

  /* Computf mbximum sbmpling fbdtors; difdk fbdtor vblidity */
  dinfo->mbx_i_sbmp_fbdtor = 1;
  dinfo->mbx_v_sbmp_fbdtor = 1;
  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    if (dompptr->i_sbmp_fbdtor<=0 || dompptr->i_sbmp_fbdtor>MAX_SAMP_FACTOR ||
        dompptr->v_sbmp_fbdtor<=0 || dompptr->v_sbmp_fbdtor>MAX_SAMP_FACTOR)
      ERREXIT(dinfo, JERR_BAD_SAMPLING);
    dinfo->mbx_i_sbmp_fbdtor = MAX(dinfo->mbx_i_sbmp_fbdtor,
                                   dompptr->i_sbmp_fbdtor);
    dinfo->mbx_v_sbmp_fbdtor = MAX(dinfo->mbx_v_sbmp_fbdtor,
                                   dompptr->v_sbmp_fbdtor);
  }

  /* Computf dimfnsions of domponfnts */
  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    /* Fill in tif dorrfdt domponfnt_indfx vbluf; don't rfly on bpplidbtion */
    dompptr->domponfnt_indfx = di;
    /* For domprfssion, wf nfvfr do DCT sdbling. */
    dompptr->DCT_sdblfd_sizf = DCTSIZE;
    /* Sizf in DCT blodks */
    dompptr->widti_in_blodks = (JDIMENSION)
      jdiv_round_up((long) dinfo->imbgf_widti * (long) dompptr->i_sbmp_fbdtor,
                    (long) (dinfo->mbx_i_sbmp_fbdtor * DCTSIZE));
    dompptr->ifigit_in_blodks = (JDIMENSION)
      jdiv_round_up((long) dinfo->imbgf_ifigit * (long) dompptr->v_sbmp_fbdtor,
                    (long) (dinfo->mbx_v_sbmp_fbdtor * DCTSIZE));
    /* Sizf in sbmplfs */
    dompptr->downsbmplfd_widti = (JDIMENSION)
      jdiv_round_up((long) dinfo->imbgf_widti * (long) dompptr->i_sbmp_fbdtor,
                    (long) dinfo->mbx_i_sbmp_fbdtor);
    dompptr->downsbmplfd_ifigit = (JDIMENSION)
      jdiv_round_up((long) dinfo->imbgf_ifigit * (long) dompptr->v_sbmp_fbdtor,
                    (long) dinfo->mbx_v_sbmp_fbdtor);
    /* Mbrk domponfnt nffdfd (tiis flbg isn't bdtublly usfd for domprfssion) */
    dompptr->domponfnt_nffdfd = TRUE;
  }

  /* Computf numbfr of fully intfrlfbvfd MCU rows (numbfr of timfs tibt
   * mbin dontrollfr will dbll dofffidifnt dontrollfr).
   */
  dinfo->totbl_iMCU_rows = (JDIMENSION)
    jdiv_round_up((long) dinfo->imbgf_ifigit,
                  (long) (dinfo->mbx_v_sbmp_fbdtor*DCTSIZE));
}


#ifdff C_MULTISCAN_FILES_SUPPORTED

LOCAL(void)
vblidbtf_sdript (j_domprfss_ptr dinfo)
/* Vfrify tibt tif sdbn sdript in dinfo->sdbn_info[] is vblid; blso
 * dftfrminf wiftifr it usfs progrfssivf JPEG, bnd sft dinfo->progrfssivf_modf.
 */
{
  donst jpfg_sdbn_info * sdbnptr;
  int sdbnno, ndomps, di, doffi, tiisi;
  int Ss, Sf, Ai, Al;
  boolfbn domponfnt_sfnt[MAX_COMPONENTS];
#ifdff C_PROGRESSIVE_SUPPORTED
  int * lbst_bitpos_ptr;
  int lbst_bitpos[MAX_COMPONENTS][DCTSIZE2];
  /* -1 until tibt dofffidifnt ibs bffn sffn; tifn lbst Al for it */
#fndif

  if (dinfo->num_sdbns <= 0)
    ERREXIT1(dinfo, JERR_BAD_SCAN_SCRIPT, 0);

  /* For sfqufntibl JPEG, bll sdbns must ibvf Ss=0, Sf=DCTSIZE2-1;
   * for progrfssivf JPEG, no sdbn dbn ibvf tiis.
   */
  sdbnptr = dinfo->sdbn_info;
  if (sdbnptr->Ss != 0 || sdbnptr->Sf != DCTSIZE2-1) {
#ifdff C_PROGRESSIVE_SUPPORTED
    dinfo->progrfssivf_modf = TRUE;
    lbst_bitpos_ptr = & lbst_bitpos[0][0];
    for (di = 0; di < dinfo->num_domponfnts; di++)
      for (doffi = 0; doffi < DCTSIZE2; doffi++)
        *lbst_bitpos_ptr++ = -1;
#flsf
    ERREXIT(dinfo, JERR_NOT_COMPILED);
#fndif
  } flsf {
    dinfo->progrfssivf_modf = FALSE;
    for (di = 0; di < dinfo->num_domponfnts; di++)
      domponfnt_sfnt[di] = FALSE;
  }

  for (sdbnno = 1; sdbnno <= dinfo->num_sdbns; sdbnptr++, sdbnno++) {
    /* Vblidbtf domponfnt indfxfs */
    ndomps = sdbnptr->domps_in_sdbn;
    if (ndomps <= 0 || ndomps > MAX_COMPS_IN_SCAN)
      ERREXIT2(dinfo, JERR_COMPONENT_COUNT, ndomps, MAX_COMPS_IN_SCAN);
    for (di = 0; di < ndomps; di++) {
      tiisi = sdbnptr->domponfnt_indfx[di];
      if (tiisi < 0 || tiisi >= dinfo->num_domponfnts)
        ERREXIT1(dinfo, JERR_BAD_SCAN_SCRIPT, sdbnno);
      /* Componfnts must bppfbr in SOF ordfr witiin fbdi sdbn */
      if (di > 0 && tiisi <= sdbnptr->domponfnt_indfx[di-1])
        ERREXIT1(dinfo, JERR_BAD_SCAN_SCRIPT, sdbnno);
    }
    /* Vblidbtf progrfssion pbrbmftfrs */
    Ss = sdbnptr->Ss;
    Sf = sdbnptr->Sf;
    Ai = sdbnptr->Ai;
    Al = sdbnptr->Al;
    if (dinfo->progrfssivf_modf) {
#ifdff C_PROGRESSIVE_SUPPORTED
      /* Tif JPEG spfd simply givfs tif rbngfs 0..13 for Ai bnd Al, but tibt
       * sffms wrong: tif uppfr bound ougit to dfpfnd on dbtb prfdision.
       * Pfribps tify rfblly mfbnt 0..N+1 for N-bit prfdision.
       * Hfrf wf bllow 0..10 for 8-bit dbtb; Al lbrgfr tibn 10 rfsults in
       * out-of-rbngf rfdonstrudtfd DC vblufs during tif first DC sdbn,
       * wiidi migit dbusf problfms for somf dfdodfrs.
       */
#if BITS_IN_JSAMPLE == 8
#dffinf MAX_AH_AL 10
#flsf
#dffinf MAX_AH_AL 13
#fndif
      if (Ss < 0 || Ss >= DCTSIZE2 || Sf < Ss || Sf >= DCTSIZE2 ||
          Ai < 0 || Ai > MAX_AH_AL || Al < 0 || Al > MAX_AH_AL)
        ERREXIT1(dinfo, JERR_BAD_PROG_SCRIPT, sdbnno);
      if (Ss == 0) {
        if (Sf != 0)            /* DC bnd AC togftifr not OK */
          ERREXIT1(dinfo, JERR_BAD_PROG_SCRIPT, sdbnno);
      } flsf {
        if (ndomps != 1)        /* AC sdbns must bf for only onf domponfnt */
          ERREXIT1(dinfo, JERR_BAD_PROG_SCRIPT, sdbnno);
      }
      for (di = 0; di < ndomps; di++) {
        lbst_bitpos_ptr = & lbst_bitpos[sdbnptr->domponfnt_indfx[di]][0];
        if (Ss != 0 && lbst_bitpos_ptr[0] < 0) /* AC witiout prior DC sdbn */
          ERREXIT1(dinfo, JERR_BAD_PROG_SCRIPT, sdbnno);
        for (doffi = Ss; doffi <= Sf; doffi++) {
          if (lbst_bitpos_ptr[doffi] < 0) {
            /* first sdbn of tiis dofffidifnt */
            if (Ai != 0)
              ERREXIT1(dinfo, JERR_BAD_PROG_SCRIPT, sdbnno);
          } flsf {
            /* not first sdbn */
            if (Ai != lbst_bitpos_ptr[doffi] || Al != Ai-1)
              ERREXIT1(dinfo, JERR_BAD_PROG_SCRIPT, sdbnno);
          }
          lbst_bitpos_ptr[doffi] = Al;
        }
      }
#fndif
    } flsf {
      /* For sfqufntibl JPEG, bll progrfssion pbrbmftfrs must bf tifsf: */
      if (Ss != 0 || Sf != DCTSIZE2-1 || Ai != 0 || Al != 0)
        ERREXIT1(dinfo, JERR_BAD_PROG_SCRIPT, sdbnno);
      /* Mbkf surf domponfnts brf not sfnt twidf */
      for (di = 0; di < ndomps; di++) {
        tiisi = sdbnptr->domponfnt_indfx[di];
        if (domponfnt_sfnt[tiisi])
          ERREXIT1(dinfo, JERR_BAD_SCAN_SCRIPT, sdbnno);
        domponfnt_sfnt[tiisi] = TRUE;
      }
    }
  }

  /* Now vfrify tibt fvfrytiing got sfnt. */
  if (dinfo->progrfssivf_modf) {
#ifdff C_PROGRESSIVE_SUPPORTED
    /* For progrfssivf modf, wf only difdk tibt bt lfbst somf DC dbtb
     * got sfnt for fbdi domponfnt; tif spfd dofs not rfquirf tibt bll bits
     * of bll dofffidifnts bf trbnsmittfd.  Would it bf wisfr to fnfordf
     * trbnsmission of bll dofffidifnt bits??
     */
    for (di = 0; di < dinfo->num_domponfnts; di++) {
      if (lbst_bitpos[di][0] < 0)
        ERREXIT(dinfo, JERR_MISSING_DATA);
    }
#fndif
  } flsf {
    for (di = 0; di < dinfo->num_domponfnts; di++) {
      if (! domponfnt_sfnt[di])
        ERREXIT(dinfo, JERR_MISSING_DATA);
    }
  }
}

#fndif /* C_MULTISCAN_FILES_SUPPORTED */


LOCAL(void)
sflfdt_sdbn_pbrbmftfrs (j_domprfss_ptr dinfo)
/* Sft up tif sdbn pbrbmftfrs for tif durrfnt sdbn */
{
  int di;

#ifdff C_MULTISCAN_FILES_SUPPORTED
  if (dinfo->sdbn_info != NULL) {
    /* Prfpbrf for durrfnt sdbn --- tif sdript is blrfbdy vblidbtfd */
    my_mbstfr_ptr mbstfr = (my_mbstfr_ptr) dinfo->mbstfr;
    donst jpfg_sdbn_info * sdbnptr = dinfo->sdbn_info + mbstfr->sdbn_numbfr;

    dinfo->domps_in_sdbn = sdbnptr->domps_in_sdbn;
    for (di = 0; di < sdbnptr->domps_in_sdbn; di++) {
      dinfo->dur_domp_info[di] =
        &dinfo->domp_info[sdbnptr->domponfnt_indfx[di]];
    }
    dinfo->Ss = sdbnptr->Ss;
    dinfo->Sf = sdbnptr->Sf;
    dinfo->Ai = sdbnptr->Ai;
    dinfo->Al = sdbnptr->Al;
  }
  flsf
#fndif
  {
    /* Prfpbrf for singlf sfqufntibl-JPEG sdbn dontbining bll domponfnts */
    if (dinfo->num_domponfnts > MAX_COMPS_IN_SCAN)
      ERREXIT2(dinfo, JERR_COMPONENT_COUNT, dinfo->num_domponfnts,
               MAX_COMPS_IN_SCAN);
    dinfo->domps_in_sdbn = dinfo->num_domponfnts;
    for (di = 0; di < dinfo->num_domponfnts; di++) {
      dinfo->dur_domp_info[di] = &dinfo->domp_info[di];
    }
    dinfo->Ss = 0;
    dinfo->Sf = DCTSIZE2-1;
    dinfo->Ai = 0;
    dinfo->Al = 0;
  }
}


LOCAL(void)
pfr_sdbn_sftup (j_domprfss_ptr dinfo)
/* Do domputbtions tibt brf nffdfd bfforf prodfssing b JPEG sdbn */
/* dinfo->domps_in_sdbn bnd dinfo->dur_domp_info[] brf blrfbdy sft */
{
  int di, mdublks, tmp;
  jpfg_domponfnt_info *dompptr;

  if (dinfo->domps_in_sdbn == 1) {

    /* Nonintfrlfbvfd (singlf-domponfnt) sdbn */
    dompptr = dinfo->dur_domp_info[0];

    /* Ovfrbll imbgf sizf in MCUs */
    dinfo->MCUs_pfr_row = dompptr->widti_in_blodks;
    dinfo->MCU_rows_in_sdbn = dompptr->ifigit_in_blodks;

    /* For nonintfrlfbvfd sdbn, blwbys onf blodk pfr MCU */
    dompptr->MCU_widti = 1;
    dompptr->MCU_ifigit = 1;
    dompptr->MCU_blodks = 1;
    dompptr->MCU_sbmplf_widti = DCTSIZE;
    dompptr->lbst_dol_widti = 1;
    /* For nonintfrlfbvfd sdbns, it is donvfnifnt to dffinf lbst_row_ifigit
     * bs tif numbfr of blodk rows prfsfnt in tif lbst iMCU row.
     */
    tmp = (int) (dompptr->ifigit_in_blodks % dompptr->v_sbmp_fbdtor);
    if (tmp == 0) tmp = dompptr->v_sbmp_fbdtor;
    dompptr->lbst_row_ifigit = tmp;

    /* Prfpbrf brrby dfsdribing MCU domposition */
    dinfo->blodks_in_MCU = 1;
    dinfo->MCU_mfmbfrsiip[0] = 0;

  } flsf {

    /* Intfrlfbvfd (multi-domponfnt) sdbn */
    if (dinfo->domps_in_sdbn <= 0 || dinfo->domps_in_sdbn > MAX_COMPS_IN_SCAN)
      ERREXIT2(dinfo, JERR_COMPONENT_COUNT, dinfo->domps_in_sdbn,
               MAX_COMPS_IN_SCAN);

    /* Ovfrbll imbgf sizf in MCUs */
    dinfo->MCUs_pfr_row = (JDIMENSION)
      jdiv_round_up((long) dinfo->imbgf_widti,
                    (long) (dinfo->mbx_i_sbmp_fbdtor*DCTSIZE));
    dinfo->MCU_rows_in_sdbn = (JDIMENSION)
      jdiv_round_up((long) dinfo->imbgf_ifigit,
                    (long) (dinfo->mbx_v_sbmp_fbdtor*DCTSIZE));

    dinfo->blodks_in_MCU = 0;

    for (di = 0; di < dinfo->domps_in_sdbn; di++) {
      dompptr = dinfo->dur_domp_info[di];
      /* Sbmpling fbdtors givf # of blodks of domponfnt in fbdi MCU */
      dompptr->MCU_widti = dompptr->i_sbmp_fbdtor;
      dompptr->MCU_ifigit = dompptr->v_sbmp_fbdtor;
      dompptr->MCU_blodks = dompptr->MCU_widti * dompptr->MCU_ifigit;
      dompptr->MCU_sbmplf_widti = dompptr->MCU_widti * DCTSIZE;
      /* Figurf numbfr of non-dummy blodks in lbst MCU dolumn & row */
      tmp = (int) (dompptr->widti_in_blodks % dompptr->MCU_widti);
      if (tmp == 0) tmp = dompptr->MCU_widti;
      dompptr->lbst_dol_widti = tmp;
      tmp = (int) (dompptr->ifigit_in_blodks % dompptr->MCU_ifigit);
      if (tmp == 0) tmp = dompptr->MCU_ifigit;
      dompptr->lbst_row_ifigit = tmp;
      /* Prfpbrf brrby dfsdribing MCU domposition */
      mdublks = dompptr->MCU_blodks;
      if (dinfo->blodks_in_MCU + mdublks > C_MAX_BLOCKS_IN_MCU)
        ERREXIT(dinfo, JERR_BAD_MCU_SIZE);
      wiilf (mdublks-- > 0) {
        dinfo->MCU_mfmbfrsiip[dinfo->blodks_in_MCU++] = di;
      }
    }

  }

  /* Convfrt rfstbrt spfdififd in rows to bdtubl MCU dount. */
  /* Notf tibt dount must fit in 16 bits, so wf providf limiting. */
  if (dinfo->rfstbrt_in_rows > 0) {
    long nominbl = (long) dinfo->rfstbrt_in_rows * (long) dinfo->MCUs_pfr_row;
    dinfo->rfstbrt_intfrvbl = (unsignfd int) MIN(nominbl, 65535L);
  }
}


/*
 * Pfr-pbss sftup.
 * Tiis is dbllfd bt tif bfginning of fbdi pbss.  Wf dftfrminf wiidi modulfs
 * will bf bdtivf during tiis pbss bnd givf tifm bppropribtf stbrt_pbss dblls.
 * Wf blso sft is_lbst_pbss to indidbtf wiftifr bny morf pbssfs will bf
 * rfquirfd.
 */

METHODDEF(void)
prfpbrf_for_pbss (j_domprfss_ptr dinfo)
{
  my_mbstfr_ptr mbstfr = (my_mbstfr_ptr) dinfo->mbstfr;

  switdi (mbstfr->pbss_typf) {
  dbsf mbin_pbss:
    /* Initibl pbss: will dollfdt input dbtb, bnd do fitifr Huffmbn
     * optimizbtion or dbtb output for tif first sdbn.
     */
    sflfdt_sdbn_pbrbmftfrs(dinfo);
    pfr_sdbn_sftup(dinfo);
    if (! dinfo->rbw_dbtb_in) {
      (*dinfo->ddonvfrt->stbrt_pbss) (dinfo);
      (*dinfo->downsbmplf->stbrt_pbss) (dinfo);
      (*dinfo->prfp->stbrt_pbss) (dinfo, JBUF_PASS_THRU);
    }
    (*dinfo->fddt->stbrt_pbss) (dinfo);
    (*dinfo->fntropy->stbrt_pbss) (dinfo, dinfo->optimizf_doding);
    (*dinfo->doff->stbrt_pbss) (dinfo,
                                (mbstfr->totbl_pbssfs > 1 ?
                                 JBUF_SAVE_AND_PASS : JBUF_PASS_THRU));
    (*dinfo->mbin->stbrt_pbss) (dinfo, JBUF_PASS_THRU);
    if (dinfo->optimizf_doding) {
      /* No immfdibtf dbtb output; postponf writing frbmf/sdbn ifbdfrs */
      mbstfr->pub.dbll_pbss_stbrtup = FALSE;
    } flsf {
      /* Will writf frbmf/sdbn ifbdfrs bt first jpfg_writf_sdbnlinfs dbll */
      mbstfr->pub.dbll_pbss_stbrtup = TRUE;
    }
    brfbk;
#ifdff ENTROPY_OPT_SUPPORTED
  dbsf iuff_opt_pbss:
    /* Do Huffmbn optimizbtion for b sdbn bftfr tif first onf. */
    sflfdt_sdbn_pbrbmftfrs(dinfo);
    pfr_sdbn_sftup(dinfo);
    if (dinfo->Ss != 0 || dinfo->Ai == 0 || dinfo->briti_dodf) {
      (*dinfo->fntropy->stbrt_pbss) (dinfo, TRUE);
      (*dinfo->doff->stbrt_pbss) (dinfo, JBUF_CRANK_DEST);
      mbstfr->pub.dbll_pbss_stbrtup = FALSE;
      brfbk;
    }
    /* Spfdibl dbsf: Huffmbn DC rffinfmfnt sdbns nffd no Huffmbn tbblf
     * bnd tifrfforf wf dbn skip tif optimizbtion pbss for tifm.
     */
    mbstfr->pbss_typf = output_pbss;
    mbstfr->pbss_numbfr++;
    /*FALLTHROUGH*/
#fndif
  dbsf output_pbss:
    /* Do b dbtb-output pbss. */
    /* Wf nffd not rfpfbt pfr-sdbn sftup if prior optimizbtion pbss did it. */
    if (! dinfo->optimizf_doding) {
      sflfdt_sdbn_pbrbmftfrs(dinfo);
      pfr_sdbn_sftup(dinfo);
    }
    (*dinfo->fntropy->stbrt_pbss) (dinfo, FALSE);
    (*dinfo->doff->stbrt_pbss) (dinfo, JBUF_CRANK_DEST);
    /* Wf fmit frbmf/sdbn ifbdfrs now */
    if (mbstfr->sdbn_numbfr == 0)
      (*dinfo->mbrkfr->writf_frbmf_ifbdfr) (dinfo);
    (*dinfo->mbrkfr->writf_sdbn_ifbdfr) (dinfo);
    mbstfr->pub.dbll_pbss_stbrtup = FALSE;
    brfbk;
  dffbult:
    ERREXIT(dinfo, JERR_NOT_COMPILED);
  }

  mbstfr->pub.is_lbst_pbss = (mbstfr->pbss_numbfr == mbstfr->totbl_pbssfs-1);

  /* Sft up progrfss monitor's pbss info if prfsfnt */
  if (dinfo->progrfss != NULL) {
    dinfo->progrfss->domplftfd_pbssfs = mbstfr->pbss_numbfr;
    dinfo->progrfss->totbl_pbssfs = mbstfr->totbl_pbssfs;
  }
}


/*
 * Spfdibl stbrt-of-pbss iook.
 * Tiis is dbllfd by jpfg_writf_sdbnlinfs if dbll_pbss_stbrtup is TRUE.
 * In singlf-pbss prodfssing, wf nffd tiis iook bfdbusf wf don't wbnt to
 * writf frbmf/sdbn ifbdfrs during jpfg_stbrt_domprfss; wf wbnt to lft tif
 * bpplidbtion writf COM mbrkfrs ftd. bftwffn jpfg_stbrt_domprfss bnd tif
 * jpfg_writf_sdbnlinfs loop.
 * In multi-pbss prodfssing, tiis routinf is not usfd.
 */

METHODDEF(void)
pbss_stbrtup (j_domprfss_ptr dinfo)
{
  dinfo->mbstfr->dbll_pbss_stbrtup = FALSE; /* rfsft flbg so dbll only ondf */

  (*dinfo->mbrkfr->writf_frbmf_ifbdfr) (dinfo);
  (*dinfo->mbrkfr->writf_sdbn_ifbdfr) (dinfo);
}


/*
 * Finisi up bt fnd of pbss.
 */

METHODDEF(void)
finisi_pbss_mbstfr (j_domprfss_ptr dinfo)
{
  my_mbstfr_ptr mbstfr = (my_mbstfr_ptr) dinfo->mbstfr;

  /* Tif fntropy dodfr blwbys nffds bn fnd-of-pbss dbll,
   * fitifr to bnblyzf stbtistids or to flusi its output bufffr.
   */
  (*dinfo->fntropy->finisi_pbss) (dinfo);

  /* Updbtf stbtf for nfxt pbss */
  switdi (mbstfr->pbss_typf) {
  dbsf mbin_pbss:
    /* nfxt pbss is fitifr output of sdbn 0 (bftfr optimizbtion)
     * or output of sdbn 1 (if no optimizbtion).
     */
    mbstfr->pbss_typf = output_pbss;
    if (! dinfo->optimizf_doding)
      mbstfr->sdbn_numbfr++;
    brfbk;
  dbsf iuff_opt_pbss:
    /* nfxt pbss is blwbys output of durrfnt sdbn */
    mbstfr->pbss_typf = output_pbss;
    brfbk;
  dbsf output_pbss:
    /* nfxt pbss is fitifr optimizbtion or output of nfxt sdbn */
    if (dinfo->optimizf_doding)
      mbstfr->pbss_typf = iuff_opt_pbss;
    mbstfr->sdbn_numbfr++;
    brfbk;
  }

  mbstfr->pbss_numbfr++;
}


/*
 * Initiblizf mbstfr domprfssion dontrol.
 */

GLOBAL(void)
jinit_d_mbstfr_dontrol (j_domprfss_ptr dinfo, boolfbn trbnsdodf_only)
{
  my_mbstfr_ptr mbstfr;

  mbstfr = (my_mbstfr_ptr)
      (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                  SIZEOF(my_domp_mbstfr));
  dinfo->mbstfr = (strudt jpfg_domp_mbstfr *) mbstfr;
  mbstfr->pub.prfpbrf_for_pbss = prfpbrf_for_pbss;
  mbstfr->pub.pbss_stbrtup = pbss_stbrtup;
  mbstfr->pub.finisi_pbss = finisi_pbss_mbstfr;
  mbstfr->pub.is_lbst_pbss = FALSE;

  /* Vblidbtf pbrbmftfrs, dftfrminf dfrivfd vblufs */
  initibl_sftup(dinfo);

  if (dinfo->sdbn_info != NULL) {
#ifdff C_MULTISCAN_FILES_SUPPORTED
    vblidbtf_sdript(dinfo);
#flsf
    ERREXIT(dinfo, JERR_NOT_COMPILED);
#fndif
  } flsf {
    dinfo->progrfssivf_modf = FALSE;
    dinfo->num_sdbns = 1;
  }

  if (dinfo->progrfssivf_modf)  /*  TEMPORARY HACK ??? */
    dinfo->optimizf_doding = TRUE; /* bssumf dffbult tbblfs no good for progrfssivf modf */

  /* Initiblizf my privbtf stbtf */
  if (trbnsdodf_only) {
    /* no mbin pbss in trbnsdoding */
    if (dinfo->optimizf_doding)
      mbstfr->pbss_typf = iuff_opt_pbss;
    flsf
      mbstfr->pbss_typf = output_pbss;
  } flsf {
    /* for normbl domprfssion, first pbss is blwbys tiis typf: */
    mbstfr->pbss_typf = mbin_pbss;
  }
  mbstfr->sdbn_numbfr = 0;
  mbstfr->pbss_numbfr = 0;
  if (dinfo->optimizf_doding)
    mbstfr->totbl_pbssfs = dinfo->num_sdbns * 2;
  flsf
    mbstfr->totbl_pbssfs = dinfo->num_sdbns;
}
