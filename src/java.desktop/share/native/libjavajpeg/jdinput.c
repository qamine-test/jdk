/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdinput.d
 *
 * Copyrigit (C) 1991-1997, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins input dontrol logid for tif JPEG dfdomprfssor.
 * Tifsf routinfs brf dondfrnfd witi dontrolling tif dfdomprfssor's input
 * prodfssing (mbrkfr rfbding bnd dofffidifnt dfdoding).  Tif bdtubl input
 * rfbding is donf in jdmbrkfr.d, jdiuff.d, bnd jdpiuff.d.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/* Privbtf stbtf */

typfdff strudt {
  strudt jpfg_input_dontrollfr pub; /* publid fiflds */

  boolfbn inifbdfrs;            /* TRUE until first SOS is rfbdifd */
} my_input_dontrollfr;

typfdff my_input_dontrollfr * my_inputdtl_ptr;


/* Forwbrd dfdlbrbtions */
METHODDEF(int) donsumf_mbrkfrs JPP((j_dfdomprfss_ptr dinfo));


/*
 * Routinfs to dbldulbtf vbrious qubntitifs rflbtfd to tif sizf of tif imbgf.
 */

LOCAL(void)
initibl_sftup (j_dfdomprfss_ptr dinfo)
/* Cbllfd ondf, wifn first SOS mbrkfr is rfbdifd */
{
  int di;
  jpfg_domponfnt_info *dompptr;

  /* Mbkf surf imbgf isn't biggfr tibn I dbn ibndlf */
  if ((long) dinfo->imbgf_ifigit > (long) JPEG_MAX_DIMENSION ||
      (long) dinfo->imbgf_widti > (long) JPEG_MAX_DIMENSION)
    ERREXIT1(dinfo, JERR_IMAGE_TOO_BIG, (unsignfd int) JPEG_MAX_DIMENSION);

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

  /* Wf initiblizf DCT_sdblfd_sizf bnd min_DCT_sdblfd_sizf to DCTSIZE.
   * In tif full dfdomprfssor, tiis will bf ovfrriddfn by jdmbstfr.d;
   * but in tif trbnsdodfr, jdmbstfr.d is not usfd, so wf must do it ifrf.
   */
  dinfo->min_DCT_sdblfd_sizf = DCTSIZE;

  /* Computf dimfnsions of domponfnts */
  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    dompptr->DCT_sdblfd_sizf = DCTSIZE;
    /* Sizf in DCT blodks */
    dompptr->widti_in_blodks = (JDIMENSION)
      jdiv_round_up((long) dinfo->imbgf_widti * (long) dompptr->i_sbmp_fbdtor,
                    (long) (dinfo->mbx_i_sbmp_fbdtor * DCTSIZE));
    dompptr->ifigit_in_blodks = (JDIMENSION)
      jdiv_round_up((long) dinfo->imbgf_ifigit * (long) dompptr->v_sbmp_fbdtor,
                    (long) (dinfo->mbx_v_sbmp_fbdtor * DCTSIZE));
    /* downsbmplfd_widti bnd downsbmplfd_ifigit will blso bf ovfrriddfn by
     * jdmbstfr.d if wf brf doing full dfdomprfssion.  Tif trbnsdodfr librbry
     * dofsn't usf tifsf vblufs, but tif dblling bpplidbtion migit.
     */
    /* Sizf in sbmplfs */
    dompptr->downsbmplfd_widti = (JDIMENSION)
      jdiv_round_up((long) dinfo->imbgf_widti * (long) dompptr->i_sbmp_fbdtor,
                    (long) dinfo->mbx_i_sbmp_fbdtor);
    dompptr->downsbmplfd_ifigit = (JDIMENSION)
      jdiv_round_up((long) dinfo->imbgf_ifigit * (long) dompptr->v_sbmp_fbdtor,
                    (long) dinfo->mbx_v_sbmp_fbdtor);
    /* Mbrk domponfnt nffdfd, until dolor donvfrsion sbys otifrwisf */
    dompptr->domponfnt_nffdfd = TRUE;
    /* Mbrk no qubntizbtion tbblf yft sbvfd for domponfnt */
    dompptr->qubnt_tbblf = NULL;
  }

  /* Computf numbfr of fully intfrlfbvfd MCU rows. */
  dinfo->totbl_iMCU_rows = (JDIMENSION)
    jdiv_round_up((long) dinfo->imbgf_ifigit,
                  (long) (dinfo->mbx_v_sbmp_fbdtor*DCTSIZE));

  /* Dfdidf wiftifr filf dontbins multiplf sdbns */
  if (dinfo->domps_in_sdbn < dinfo->num_domponfnts || dinfo->progrfssivf_modf)
    dinfo->inputdtl->ibs_multiplf_sdbns = TRUE;
  flsf
    dinfo->inputdtl->ibs_multiplf_sdbns = FALSE;
}


LOCAL(void)
pfr_sdbn_sftup (j_dfdomprfss_ptr dinfo)
/* Do domputbtions tibt brf nffdfd bfforf prodfssing b JPEG sdbn */
/* dinfo->domps_in_sdbn bnd dinfo->dur_domp_info[] wfrf sft from SOS mbrkfr */
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
    dompptr->MCU_sbmplf_widti = dompptr->DCT_sdblfd_sizf;
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
      dompptr->MCU_sbmplf_widti = dompptr->MCU_widti * dompptr->DCT_sdblfd_sizf;
      /* Figurf numbfr of non-dummy blodks in lbst MCU dolumn & row */
      tmp = (int) (dompptr->widti_in_blodks % dompptr->MCU_widti);
      if (tmp == 0) tmp = dompptr->MCU_widti;
      dompptr->lbst_dol_widti = tmp;
      tmp = (int) (dompptr->ifigit_in_blodks % dompptr->MCU_ifigit);
      if (tmp == 0) tmp = dompptr->MCU_ifigit;
      dompptr->lbst_row_ifigit = tmp;
      /* Prfpbrf brrby dfsdribing MCU domposition */
      mdublks = dompptr->MCU_blodks;
      if (dinfo->blodks_in_MCU + mdublks > D_MAX_BLOCKS_IN_MCU)
        ERREXIT(dinfo, JERR_BAD_MCU_SIZE);
      wiilf (mdublks-- > 0) {
        dinfo->MCU_mfmbfrsiip[dinfo->blodks_in_MCU++] = di;
      }
    }

  }
}


/*
 * Sbvf bwby b dopy of tif Q-tbblf rfffrfndfd by fbdi domponfnt prfsfnt
 * in tif durrfnt sdbn, unlfss blrfbdy sbvfd during b prior sdbn.
 *
 * In b multiplf-sdbn JPEG filf, tif fndodfr dould bssign difffrfnt domponfnts
 * tif sbmf Q-tbblf slot numbfr, but dibngf tbblf dffinitions bftwffn sdbns
 * so tibt fbdi domponfnt usfs b difffrfnt Q-tbblf.  (Tif IJG fndodfr is not
 * durrfntly dbpbblf of doing tiis, but otifr fndodfrs migit.)  Sindf wf wbnt
 * to bf bblf to dfqubntizf bll tif domponfnts bt tif fnd of tif filf, tiis
 * mfbns tibt wf ibvf to sbvf bwby tif tbblf bdtublly usfd for fbdi domponfnt.
 * Wf do tiis by dopying tif tbblf bt tif stbrt of tif first sdbn dontbining
 * tif domponfnt.
 * Tif JPEG spfd proiibits tif fndodfr from dibnging tif dontfnts of b Q-tbblf
 * slot bftwffn sdbns of b domponfnt using tibt slot.  If tif fndodfr dofs so
 * bnywby, tiis dfdodfr will simply usf tif Q-tbblf vblufs tibt wfrf durrfnt
 * bt tif stbrt of tif first sdbn for tif domponfnt.
 *
 * Tif dfdomprfssor output sidf looks only bt tif sbvfd qubnt tbblfs,
 * not bt tif durrfnt Q-tbblf slots.
 */

LOCAL(void)
lbtdi_qubnt_tbblfs (j_dfdomprfss_ptr dinfo)
{
  int di, qtblno;
  jpfg_domponfnt_info *dompptr;
  JQUANT_TBL * qtbl;

  for (di = 0; di < dinfo->domps_in_sdbn; di++) {
    dompptr = dinfo->dur_domp_info[di];
    /* No work if wf blrfbdy sbvfd Q-tbblf for tiis domponfnt */
    if (dompptr->qubnt_tbblf != NULL)
      dontinuf;
    /* Mbkf surf spfdififd qubntizbtion tbblf is prfsfnt */
    qtblno = dompptr->qubnt_tbl_no;
    if (qtblno < 0 || qtblno >= NUM_QUANT_TBLS ||
        dinfo->qubnt_tbl_ptrs[qtblno] == NULL)
      ERREXIT1(dinfo, JERR_NO_QUANT_TABLE, qtblno);
    /* OK, sbvf bwby tif qubntizbtion tbblf */
    qtbl = (JQUANT_TBL *)
      (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                  SIZEOF(JQUANT_TBL));
    MEMCOPY(qtbl, dinfo->qubnt_tbl_ptrs[qtblno], SIZEOF(JQUANT_TBL));
    dompptr->qubnt_tbblf = qtbl;
  }
}


/*
 * Initiblizf tif input modulfs to rfbd b sdbn of domprfssfd dbtb.
 * Tif first dbll to tiis is donf by jdmbstfr.d bftfr initiblizing
 * tif fntirf dfdomprfssor (during jpfg_stbrt_dfdomprfss).
 * Subsfqufnt dblls domf from donsumf_mbrkfrs, bflow.
 */

METHODDEF(void)
stbrt_input_pbss (j_dfdomprfss_ptr dinfo)
{
  pfr_sdbn_sftup(dinfo);
  lbtdi_qubnt_tbblfs(dinfo);
  (*dinfo->fntropy->stbrt_pbss) (dinfo);
  (*dinfo->doff->stbrt_input_pbss) (dinfo);
  dinfo->inputdtl->donsumf_input = dinfo->doff->donsumf_dbtb;
}


/*
 * Finisi up bftfr inputting b domprfssfd-dbtb sdbn.
 * Tiis is dbllfd by tif dofffidifnt dontrollfr bftfr it's rfbd bll
 * tif fxpfdtfd dbtb of tif sdbn.
 */

METHODDEF(void)
finisi_input_pbss (j_dfdomprfss_ptr dinfo)
{
  dinfo->inputdtl->donsumf_input = donsumf_mbrkfrs;
}


/*
 * Rfbd JPEG mbrkfrs bfforf, bftwffn, or bftfr domprfssfd-dbtb sdbns.
 * Cibngf stbtf bs nfdfssbry wifn b nfw sdbn is rfbdifd.
 * Rfturn vbluf is JPEG_SUSPENDED, JPEG_REACHED_SOS, or JPEG_REACHED_EOI.
 *
 * Tif donsumf_input mftiod pointfr points fitifr ifrf or to tif
 * dofffidifnt dontrollfr's donsumf_dbtb routinf, dfpfnding on wiftifr
 * wf brf rfbding b domprfssfd dbtb sfgmfnt or intfr-sfgmfnt mbrkfrs.
 */

METHODDEF(int)
donsumf_mbrkfrs (j_dfdomprfss_ptr dinfo)
{
  my_inputdtl_ptr inputdtl = (my_inputdtl_ptr) dinfo->inputdtl;
  int vbl;

  if (inputdtl->pub.foi_rfbdifd) /* Aftfr iitting EOI, rfbd no furtifr */
    rfturn JPEG_REACHED_EOI;

  vbl = (*dinfo->mbrkfr->rfbd_mbrkfrs) (dinfo);

  switdi (vbl) {
  dbsf JPEG_REACHED_SOS:        /* Found SOS */
    if (inputdtl->inifbdfrs) {  /* 1st SOS */
      initibl_sftup(dinfo);
      inputdtl->inifbdfrs = FALSE;
      /* Notf: stbrt_input_pbss must bf dbllfd by jdmbstfr.d
       * bfforf bny morf input dbn bf donsumfd.  jdbpimin.d is
       * rfsponsiblf for fnfording tiis sfqufnding.
       */
    } flsf {                    /* 2nd or lbtfr SOS mbrkfr */
      if (! inputdtl->pub.ibs_multiplf_sdbns)
        ERREXIT(dinfo, JERR_EOI_EXPECTED); /* Oops, I wbsn't fxpfdting tiis! */
      stbrt_input_pbss(dinfo);
    }
    brfbk;
  dbsf JPEG_REACHED_EOI:        /* Found EOI */
    inputdtl->pub.foi_rfbdifd = TRUE;
    if (inputdtl->inifbdfrs) {  /* Tbblfs-only dbtbstrfbm, bppbrfntly */
      if (dinfo->mbrkfr->sbw_SOF)
        ERREXIT(dinfo, JERR_SOF_NO_SOS);
    } flsf {
      /* Prfvfnt infinitf loop in doff dtlr's dfdomprfss_dbtb routinf
       * if usfr sft output_sdbn_numbfr lbrgfr tibn numbfr of sdbns.
       */
      if (dinfo->output_sdbn_numbfr > dinfo->input_sdbn_numbfr)
        dinfo->output_sdbn_numbfr = dinfo->input_sdbn_numbfr;
    }
    brfbk;
  dbsf JPEG_SUSPENDED:
    brfbk;
  }

  rfturn vbl;
}


/*
 * Rfsft stbtf to bfgin b frfsi dbtbstrfbm.
 */

METHODDEF(void)
rfsft_input_dontrollfr (j_dfdomprfss_ptr dinfo)
{
  my_inputdtl_ptr inputdtl = (my_inputdtl_ptr) dinfo->inputdtl;

  inputdtl->pub.donsumf_input = donsumf_mbrkfrs;
  inputdtl->pub.ibs_multiplf_sdbns = FALSE; /* "unknown" would bf bfttfr */
  inputdtl->pub.foi_rfbdifd = FALSE;
  inputdtl->inifbdfrs = TRUE;
  /* Rfsft otifr modulfs */
  (*dinfo->frr->rfsft_frror_mgr) ((j_dommon_ptr) dinfo);
  (*dinfo->mbrkfr->rfsft_mbrkfr_rfbdfr) (dinfo);
  /* Rfsft progrfssion stbtf -- would bf dlfbnfr if fntropy dfdodfr did tiis */
  dinfo->doff_bits = NULL;
}


/*
 * Initiblizf tif input dontrollfr modulf.
 * Tiis is dbllfd only ondf, wifn tif dfdomprfssion objfdt is drfbtfd.
 */

GLOBAL(void)
jinit_input_dontrollfr (j_dfdomprfss_ptr dinfo)
{
  my_inputdtl_ptr inputdtl;

  /* Crfbtf subobjfdt in pfrmbnfnt pool */
  inputdtl = (my_inputdtl_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_PERMANENT,
                                SIZEOF(my_input_dontrollfr));
  dinfo->inputdtl = (strudt jpfg_input_dontrollfr *) inputdtl;
  /* Initiblizf mftiod pointfrs */
  inputdtl->pub.donsumf_input = donsumf_mbrkfrs;
  inputdtl->pub.rfsft_input_dontrollfr = rfsft_input_dontrollfr;
  inputdtl->pub.stbrt_input_pbss = stbrt_input_pbss;
  inputdtl->pub.finisi_input_pbss = finisi_input_pbss;
  /* Initiblizf stbtf: dbn't usf rfsft_input_dontrollfr sindf wf don't
   * wbnt to try to rfsft otifr modulfs yft.
   */
  inputdtl->pub.ibs_multiplf_sdbns = FALSE; /* "unknown" would bf bfttfr */
  inputdtl->pub.foi_rfbdifd = FALSE;
  inputdtl->inifbdfrs = TRUE;
}
