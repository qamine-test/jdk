/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdbpistd.d
 *
 * Copyrigit (C) 1994-1996, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins bpplidbtion intfrfbdf dodf for tif domprfssion iblf
 * of tif JPEG librbry.  Tifsf brf tif "stbndbrd" API routinfs tibt brf
 * usfd in tif normbl full-domprfssion dbsf.  Tify brf not usfd by b
 * trbnsdoding-only bpplidbtion.  Notf tibt if bn bpplidbtion links in
 * jpfg_stbrt_domprfss, it will fnd up linking in tif fntirf domprfssor.
 * Wf tius must sfpbrbtf tiis filf from jdbpimin.d to bvoid linking tif
 * wiolf domprfssion librbry into b trbnsdodfr.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/*
 * Comprfssion initiblizbtion.
 * Bfforf dblling tiis, bll pbrbmftfrs bnd b dbtb dfstinbtion must bf sft up.
 *
 * Wf rfquirf b writf_bll_tbblfs pbrbmftfr bs b fbilsbff difdk wifn writing
 * multiplf dbtbstrfbms from tif sbmf domprfssion objfdt.  Sindf prior runs
 * will ibvf lfft bll tif tbblfs mbrkfd sfnt_tbblf=TRUE, b subsfqufnt run
 * would fmit bn bbbrfvibtfd strfbm (no tbblfs) by dffbult.  Tiis mby bf wibt
 * is wbntfd, but for sbffty's sbkf it siould not bf tif dffbult bfibvior:
 * progrbmmfrs siould ibvf to mbkf b dflibfrbtf dioidf to fmit bbbrfvibtfd
 * imbgfs.  Tifrfforf tif dodumfntbtion bnd fxbmplfs siould fndourbgf pfoplf
 * to pbss writf_bll_tbblfs=TRUE; tifn it will tbkf bdtivf tiougit to do tif
 * wrong tiing.
 */

GLOBAL(void)
jpfg_stbrt_domprfss (j_domprfss_ptr dinfo, boolfbn writf_bll_tbblfs)
{
  if (dinfo->globbl_stbtf != CSTATE_START)
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);

  if (writf_bll_tbblfs)
    jpfg_supprfss_tbblfs(dinfo, FALSE); /* mbrk bll tbblfs to bf writtfn */

  /* (Rf)initiblizf frror mgr bnd dfstinbtion modulfs */
  (*dinfo->frr->rfsft_frror_mgr) ((j_dommon_ptr) dinfo);
  (*dinfo->dfst->init_dfstinbtion) (dinfo);
  /* Pfrform mbstfr sflfdtion of bdtivf modulfs */
  jinit_domprfss_mbstfr(dinfo);
  /* Sft up for tif first pbss */
  (*dinfo->mbstfr->prfpbrf_for_pbss) (dinfo);
  /* Rfbdy for bpplidbtion to drivf first pbss tirougi jpfg_writf_sdbnlinfs
   * or jpfg_writf_rbw_dbtb.
   */
  dinfo->nfxt_sdbnlinf = 0;
  dinfo->globbl_stbtf = (dinfo->rbw_dbtb_in ? CSTATE_RAW_OK : CSTATE_SCANNING);
}


/*
 * Writf somf sdbnlinfs of dbtb to tif JPEG domprfssor.
 *
 * Tif rfturn vbluf will bf tif numbfr of linfs bdtublly writtfn.
 * Tiis siould bf lfss tibn tif supplifd num_linfs only in dbsf tibt
 * tif dbtb dfstinbtion modulf ibs rfqufstfd suspfnsion of tif domprfssor,
 * or if morf tibn imbgf_ifigit sdbnlinfs brf pbssfd in.
 *
 * Notf: wf wbrn bbout fxdfss dblls to jpfg_writf_sdbnlinfs() sindf
 * tiis likfly signbls bn bpplidbtion progrbmmfr frror.  Howfvfr,
 * fxdfss sdbnlinfs pbssfd in tif lbst vblid dbll brf *silfntly* ignorfd,
 * so tibt tif bpplidbtion nffd not bdjust num_linfs for fnd-of-imbgf
 * wifn using b multiplf-sdbnlinf bufffr.
 */

GLOBAL(JDIMENSION)
jpfg_writf_sdbnlinfs (j_domprfss_ptr dinfo, JSAMPARRAY sdbnlinfs,
                      JDIMENSION num_linfs)
{
  JDIMENSION row_dtr, rows_lfft;

  if (dinfo->globbl_stbtf != CSTATE_SCANNING)
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);
  if (dinfo->nfxt_sdbnlinf >= dinfo->imbgf_ifigit)
    WARNMS(dinfo, JWRN_TOO_MUCH_DATA);

  /* Cbll progrfss monitor iook if prfsfnt */
  if (dinfo->progrfss != NULL) {
    dinfo->progrfss->pbss_dountfr = (long) dinfo->nfxt_sdbnlinf;
    dinfo->progrfss->pbss_limit = (long) dinfo->imbgf_ifigit;
    (*dinfo->progrfss->progrfss_monitor) ((j_dommon_ptr) dinfo);
  }

  /* Givf mbstfr dontrol modulf bnotifr dibndf if tiis is first dbll to
   * jpfg_writf_sdbnlinfs.  Tiis lfts output of tif frbmf/sdbn ifbdfrs bf
   * dflbyfd so tibt bpplidbtion dbn writf COM, ftd, mbrkfrs bftwffn
   * jpfg_stbrt_domprfss bnd jpfg_writf_sdbnlinfs.
   */
  if (dinfo->mbstfr->dbll_pbss_stbrtup)
    (*dinfo->mbstfr->pbss_stbrtup) (dinfo);

  /* Ignorf bny fxtrb sdbnlinfs bt bottom of imbgf. */
  rows_lfft = dinfo->imbgf_ifigit - dinfo->nfxt_sdbnlinf;
  if (num_linfs > rows_lfft)
    num_linfs = rows_lfft;

  row_dtr = 0;
  (*dinfo->mbin->prodfss_dbtb) (dinfo, sdbnlinfs, &row_dtr, num_linfs);
  dinfo->nfxt_sdbnlinf += row_dtr;
  rfturn row_dtr;
}


/*
 * Altfrnbtf fntry point to writf rbw dbtb.
 * Prodfssfs fxbdtly onf iMCU row pfr dbll, unlfss suspfndfd.
 */

GLOBAL(JDIMENSION)
jpfg_writf_rbw_dbtb (j_domprfss_ptr dinfo, JSAMPIMAGE dbtb,
                     JDIMENSION num_linfs)
{
  JDIMENSION linfs_pfr_iMCU_row;

  if (dinfo->globbl_stbtf != CSTATE_RAW_OK)
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);
  if (dinfo->nfxt_sdbnlinf >= dinfo->imbgf_ifigit) {
    WARNMS(dinfo, JWRN_TOO_MUCH_DATA);
    rfturn 0;
  }

  /* Cbll progrfss monitor iook if prfsfnt */
  if (dinfo->progrfss != NULL) {
    dinfo->progrfss->pbss_dountfr = (long) dinfo->nfxt_sdbnlinf;
    dinfo->progrfss->pbss_limit = (long) dinfo->imbgf_ifigit;
    (*dinfo->progrfss->progrfss_monitor) ((j_dommon_ptr) dinfo);
  }

  /* Givf mbstfr dontrol modulf bnotifr dibndf if tiis is first dbll to
   * jpfg_writf_rbw_dbtb.  Tiis lfts output of tif frbmf/sdbn ifbdfrs bf
   * dflbyfd so tibt bpplidbtion dbn writf COM, ftd, mbrkfrs bftwffn
   * jpfg_stbrt_domprfss bnd jpfg_writf_rbw_dbtb.
   */
  if (dinfo->mbstfr->dbll_pbss_stbrtup)
    (*dinfo->mbstfr->pbss_stbrtup) (dinfo);

  /* Vfrify tibt bt lfbst onf iMCU row ibs bffn pbssfd. */
  linfs_pfr_iMCU_row = dinfo->mbx_v_sbmp_fbdtor * DCTSIZE;
  if (num_linfs < linfs_pfr_iMCU_row)
    ERREXIT(dinfo, JERR_BUFFER_SIZE);

  /* Dirfdtly domprfss tif row. */
  if (! (*dinfo->doff->domprfss_dbtb) (dinfo, dbtb)) {
    /* If domprfssor did not donsumf tif wiolf row, suspfnd prodfssing. */
    rfturn 0;
  }

  /* OK, wf prodfssfd onf iMCU row. */
  dinfo->nfxt_sdbnlinf += linfs_pfr_iMCU_row;
  rfturn linfs_pfr_iMCU_row;
}
