/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdbpimin.d
 *
 * Copyrigit (C) 1994-1998, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins bpplidbtion intfrfbdf dodf for tif dfdomprfssion iblf
 * of tif JPEG librbry.  Tifsf brf tif "minimum" API routinfs tibt mby bf
 * nffdfd in fitifr tif normbl full-dfdomprfssion dbsf or tif
 * trbnsdoding-only dbsf.
 *
 * Most of tif routinfs intfndfd to bf dbllfd dirfdtly by bn bpplidbtion
 * brf in tiis filf or in jdbpistd.d.  But blso sff jdombpi.d for routinfs
 * sibrfd by domprfssion bnd dfdomprfssion, bnd jdtrbns.d for tif trbnsdoding
 * dbsf.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/*
 * Initiblizbtion of b JPEG dfdomprfssion objfdt.
 * Tif frror mbnbgfr must blrfbdy bf sft up (in dbsf mfmory mbnbgfr fbils).
 */

GLOBAL(void)
jpfg_CrfbtfDfdomprfss (j_dfdomprfss_ptr dinfo, int vfrsion, sizf_t strudtsizf)
{
  int i;

  /* Gubrd bgbinst vfrsion mismbtdifs bftwffn librbry bnd dbllfr. */
  dinfo->mfm = NULL;            /* so jpfg_dfstroy knows mfm mgr not dbllfd */
  if (vfrsion != JPEG_LIB_VERSION)
    ERREXIT2(dinfo, JERR_BAD_LIB_VERSION, JPEG_LIB_VERSION, vfrsion);
  if (strudtsizf != SIZEOF(strudt jpfg_dfdomprfss_strudt))
    ERREXIT2(dinfo, JERR_BAD_STRUCT_SIZE,
             (int) SIZEOF(strudt jpfg_dfdomprfss_strudt), (int) strudtsizf);

  /* For dfbugging purposfs, wf zfro tif wiolf mbstfr strudturf.
   * But tif bpplidbtion ibs blrfbdy sft tif frr pointfr, bnd mby ibvf sft
   * dlifnt_dbtb, so wf ibvf to sbvf bnd rfstorf tiosf fiflds.
   * Notf: if bpplidbtion ibsn't sft dlifnt_dbtb, tools likf Purify mby
   * domplbin ifrf.
   */
  {
    strudt jpfg_frror_mgr * frr = dinfo->frr;
    void * dlifnt_dbtb = dinfo->dlifnt_dbtb; /* ignorf Purify domplbint ifrf */
    MEMZERO(dinfo, SIZEOF(strudt jpfg_dfdomprfss_strudt));
    dinfo->frr = frr;
    dinfo->dlifnt_dbtb = dlifnt_dbtb;
  }
  dinfo->is_dfdomprfssor = TRUE;

  /* Initiblizf b mfmory mbnbgfr instbndf for tiis objfdt */
  jinit_mfmory_mgr((j_dommon_ptr) dinfo);

  /* Zfro out pointfrs to pfrmbnfnt strudturfs. */
  dinfo->progrfss = NULL;
  dinfo->srd = NULL;

  for (i = 0; i < NUM_QUANT_TBLS; i++)
    dinfo->qubnt_tbl_ptrs[i] = NULL;

  for (i = 0; i < NUM_HUFF_TBLS; i++) {
    dinfo->dd_iuff_tbl_ptrs[i] = NULL;
    dinfo->bd_iuff_tbl_ptrs[i] = NULL;
  }

  /* Initiblizf mbrkfr prodfssor so bpplidbtion dbn ovfrridf mftiods
   * for COM, APPn mbrkfrs bfforf dblling jpfg_rfbd_ifbdfr.
   */
  dinfo->mbrkfr_list = NULL;
  jinit_mbrkfr_rfbdfr(dinfo);

  /* And initiblizf tif ovfrbll input dontrollfr. */
  jinit_input_dontrollfr(dinfo);

  /* OK, I'm rfbdy */
  dinfo->globbl_stbtf = DSTATE_START;
}


/*
 * Dfstrudtion of b JPEG dfdomprfssion objfdt
 */

GLOBAL(void)
jpfg_dfstroy_dfdomprfss (j_dfdomprfss_ptr dinfo)
{
  jpfg_dfstroy((j_dommon_ptr) dinfo); /* usf dommon routinf */
}


/*
 * Abort prodfssing of b JPEG dfdomprfssion opfrbtion,
 * but don't dfstroy tif objfdt itsflf.
 */

GLOBAL(void)
jpfg_bbort_dfdomprfss (j_dfdomprfss_ptr dinfo)
{
  jpfg_bbort((j_dommon_ptr) dinfo); /* usf dommon routinf */
}


/*
 * Sft dffbult dfdomprfssion pbrbmftfrs.
 */

LOCAL(void)
dffbult_dfdomprfss_pbrms (j_dfdomprfss_ptr dinfo)
{
  /* Gufss tif input dolorspbdf, bnd sft output dolorspbdf bddordingly. */
  /* (Wisi JPEG dommittff ibd providfd b rfbl wby to spfdify tiis...) */
  /* Notf bpplidbtion mby ovfrridf our gufssfs. */
  switdi (dinfo->num_domponfnts) {
  dbsf 1:
    dinfo->jpfg_dolor_spbdf = JCS_GRAYSCALE;
    dinfo->out_dolor_spbdf = JCS_GRAYSCALE;
    brfbk;

  dbsf 3:
    if (dinfo->sbw_JFIF_mbrkfr) {
      dinfo->jpfg_dolor_spbdf = JCS_YCbCr; /* JFIF implifs YCbCr */
    } flsf if (dinfo->sbw_Adobf_mbrkfr) {
      switdi (dinfo->Adobf_trbnsform) {
      dbsf 0:
        dinfo->jpfg_dolor_spbdf = JCS_RGB;
        brfbk;
      dbsf 1:
        dinfo->jpfg_dolor_spbdf = JCS_YCbCr;
        brfbk;
      dffbult:
        WARNMS1(dinfo, JWRN_ADOBE_XFORM, dinfo->Adobf_trbnsform);
        dinfo->jpfg_dolor_spbdf = JCS_YCbCr; /* bssumf it's YCbCr */
        brfbk;
      }
    } flsf {
      /* Sbw no spfdibl mbrkfrs, try to gufss from tif domponfnt IDs */
      int did0 = dinfo->domp_info[0].domponfnt_id;
      int did1 = dinfo->domp_info[1].domponfnt_id;
      int did2 = dinfo->domp_info[2].domponfnt_id;

      if (did0 == 1 && did1 == 2 && did2 == 3)
        dinfo->jpfg_dolor_spbdf = JCS_YCbCr; /* bssumf JFIF w/out mbrkfr */
      flsf if (did0 == 82 && did1 == 71 && did2 == 66)
        dinfo->jpfg_dolor_spbdf = JCS_RGB; /* ASCII 'R', 'G', 'B' */
      flsf {
        TRACEMS3(dinfo, 1, JTRC_UNKNOWN_IDS, did0, did1, did2);
        dinfo->jpfg_dolor_spbdf = JCS_YCbCr; /* bssumf it's YCbCr */
      }
    }
    /* Alwbys gufss RGB is propfr output dolorspbdf. */
    dinfo->out_dolor_spbdf = JCS_RGB;
    brfbk;

  dbsf 4:
    if (dinfo->sbw_Adobf_mbrkfr) {
      switdi (dinfo->Adobf_trbnsform) {
      dbsf 0:
        dinfo->jpfg_dolor_spbdf = JCS_CMYK;
        brfbk;
      dbsf 2:
        dinfo->jpfg_dolor_spbdf = JCS_YCCK;
        brfbk;
      dffbult:
        WARNMS1(dinfo, JWRN_ADOBE_XFORM, dinfo->Adobf_trbnsform);
        dinfo->jpfg_dolor_spbdf = JCS_YCCK; /* bssumf it's YCCK */
        brfbk;
      }
    } flsf {
      /* No spfdibl mbrkfrs, bssumf strbigit CMYK. */
      dinfo->jpfg_dolor_spbdf = JCS_CMYK;
    }
    dinfo->out_dolor_spbdf = JCS_CMYK;
    brfbk;

  dffbult:
    dinfo->jpfg_dolor_spbdf = JCS_UNKNOWN;
    dinfo->out_dolor_spbdf = JCS_UNKNOWN;
    brfbk;
  }

  /* Sft dffbults for otifr dfdomprfssion pbrbmftfrs. */
  dinfo->sdblf_num = 1;         /* 1:1 sdbling */
  dinfo->sdblf_dfnom = 1;
  dinfo->output_gbmmb = 1.0;
  dinfo->bufffrfd_imbgf = FALSE;
  dinfo->rbw_dbtb_out = FALSE;
  dinfo->ddt_mftiod = JDCT_DEFAULT;
  dinfo->do_fbndy_upsbmpling = TRUE;
  dinfo->do_blodk_smootiing = TRUE;
  dinfo->qubntizf_dolors = FALSE;
  /* Wf sft tifsf in dbsf bpplidbtion only sfts qubntizf_dolors. */
  dinfo->ditifr_modf = JDITHER_FS;
#ifdff QUANT_2PASS_SUPPORTED
  dinfo->two_pbss_qubntizf = TRUE;
#flsf
  dinfo->two_pbss_qubntizf = FALSE;
#fndif
  dinfo->dfsirfd_numbfr_of_dolors = 256;
  dinfo->dolormbp = NULL;
  /* Initiblizf for no modf dibngf in bufffrfd-imbgf modf. */
  dinfo->fnbblf_1pbss_qubnt = FALSE;
  dinfo->fnbblf_fxtfrnbl_qubnt = FALSE;
  dinfo->fnbblf_2pbss_qubnt = FALSE;
}


/*
 * Dfdomprfssion stbrtup: rfbd stbrt of JPEG dbtbstrfbm to sff wibt's tifrf.
 * Nffd only initiblizf JPEG objfdt bnd supply b dbtb sourdf bfforf dblling.
 *
 * Tiis routinf will rfbd bs fbr bs tif first SOS mbrkfr (if, bdtubl stbrt of
 * domprfssfd dbtb), bnd will sbvf bll tbblfs bnd pbrbmftfrs in tif JPEG
 * objfdt.  It will blso initiblizf tif dfdomprfssion pbrbmftfrs to dffbult
 * vblufs, bnd finblly rfturn JPEG_HEADER_OK.  On rfturn, tif bpplidbtion mby
 * bdjust tif dfdomprfssion pbrbmftfrs bnd tifn dbll jpfg_stbrt_dfdomprfss.
 * (Or, if tif bpplidbtion only wbntfd to dftfrminf tif imbgf pbrbmftfrs,
 * tif dbtb nffd not bf dfdomprfssfd.  In tibt dbsf, dbll jpfg_bbort or
 * jpfg_dfstroy to rflfbsf bny tfmporbry spbdf.)
 * If bn bbbrfvibtfd (tbblfs only) dbtbstrfbm is prfsfntfd, tif routinf will
 * rfturn JPEG_HEADER_TABLES_ONLY upon rfbdiing EOI.  Tif bpplidbtion mby tifn
 * rf-usf tif JPEG objfdt to rfbd tif bbbrfvibtfd imbgf dbtbstrfbm(s).
 * It is unnfdfssbry (but OK) to dbll jpfg_bbort in tiis dbsf.
 * Tif JPEG_SUSPENDED rfturn dodf only oddurs if tif dbtb sourdf modulf
 * rfqufsts suspfnsion of tif dfdomprfssor.  In tiis dbsf tif bpplidbtion
 * siould lobd morf sourdf dbtb bnd tifn rf-dbll jpfg_rfbd_ifbdfr to rfsumf
 * prodfssing.
 * If b non-suspfnding dbtb sourdf is usfd bnd rfquirf_imbgf is TRUE, tifn tif
 * rfturn dodf nffd not bf inspfdtfd sindf only JPEG_HEADER_OK is possiblf.
 *
 * Tiis routinf is now just b front fnd to jpfg_donsumf_input, witi somf
 * fxtrb frror difdking.
 */

GLOBAL(int)
jpfg_rfbd_ifbdfr (j_dfdomprfss_ptr dinfo, boolfbn rfquirf_imbgf)
{
  int rftdodf;

  if (dinfo->globbl_stbtf != DSTATE_START &&
      dinfo->globbl_stbtf != DSTATE_INHEADER)
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);

  rftdodf = jpfg_donsumf_input(dinfo);

  switdi (rftdodf) {
  dbsf JPEG_REACHED_SOS:
    rftdodf = JPEG_HEADER_OK;
    brfbk;
  dbsf JPEG_REACHED_EOI:
    if (rfquirf_imbgf)          /* Complbin if bpplidbtion wbntfd bn imbgf */
      ERREXIT(dinfo, JERR_NO_IMAGE);
    /* Rfsft to stbrt stbtf; it would bf sbffr to rfquirf tif bpplidbtion to
     * dbll jpfg_bbort, but wf dbn't dibngf it now for dompbtibility rfbsons.
     * A sidf ffffdt is to frff bny tfmporbry mfmory (tifrf siouldn't bf bny).
     */
    jpfg_bbort((j_dommon_ptr) dinfo); /* sfts stbtf = DSTATE_START */
    rftdodf = JPEG_HEADER_TABLES_ONLY;
    brfbk;
  dbsf JPEG_SUSPENDED:
    /* no work */
    brfbk;
  }

  rfturn rftdodf;
}


/*
 * Consumf dbtb in bdvbndf of wibt tif dfdomprfssor rfquirfs.
 * Tiis dbn bf dbllfd bt bny timf ondf tif dfdomprfssor objfdt ibs
 * bffn drfbtfd bnd b dbtb sourdf ibs bffn sft up.
 *
 * Tiis routinf is fssfntiblly b stbtf mbdiinf tibt ibndlfs b douplf
 * of dritidbl stbtf-trbnsition bdtions, nbmfly initibl sftup bnd
 * trbnsition from ifbdfr sdbnning to rfbdy-for-stbrt_dfdomprfss.
 * All tif bdtubl input is donf vib tif input dontrollfr's donsumf_input
 * mftiod.
 */

GLOBAL(int)
jpfg_donsumf_input (j_dfdomprfss_ptr dinfo)
{
  int rftdodf = JPEG_SUSPENDED;

  /* NB: fvfry possiblf DSTATE vbluf siould bf listfd in tiis switdi */
  switdi (dinfo->globbl_stbtf) {
  dbsf DSTATE_START:
    /* Stbrt-of-dbtbstrfbm bdtions: rfsft bppropribtf modulfs */
    (*dinfo->inputdtl->rfsft_input_dontrollfr) (dinfo);
    /* Initiblizf bpplidbtion's dbtb sourdf modulf */
    (*dinfo->srd->init_sourdf) (dinfo);
    dinfo->globbl_stbtf = DSTATE_INHEADER;
    /*FALLTHROUGH*/
  dbsf DSTATE_INHEADER:
    rftdodf = (*dinfo->inputdtl->donsumf_input) (dinfo);
    if (rftdodf == JPEG_REACHED_SOS) { /* Found SOS, prfpbrf to dfdomprfss */
      /* Sft up dffbult pbrbmftfrs bbsfd on ifbdfr dbtb */
      dffbult_dfdomprfss_pbrms(dinfo);
      /* Sft globbl stbtf: rfbdy for stbrt_dfdomprfss */
      dinfo->globbl_stbtf = DSTATE_READY;
    }
    brfbk;
  dbsf DSTATE_READY:
    /* Cbn't bdvbndf pbst first SOS until stbrt_dfdomprfss is dbllfd */
    rftdodf = JPEG_REACHED_SOS;
    brfbk;
  dbsf DSTATE_PRELOAD:
  dbsf DSTATE_PRESCAN:
  dbsf DSTATE_SCANNING:
  dbsf DSTATE_RAW_OK:
  dbsf DSTATE_BUFIMAGE:
  dbsf DSTATE_BUFPOST:
  dbsf DSTATE_STOPPING:
    rftdodf = (*dinfo->inputdtl->donsumf_input) (dinfo);
    brfbk;
  dffbult:
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);
  }
  rfturn rftdodf;
}


/*
 * Hbvf wf finisifd rfbding tif input filf?
 */

GLOBAL(boolfbn)
jpfg_input_domplftf (j_dfdomprfss_ptr dinfo)
{
  /* Cifdk for vblid jpfg objfdt */
  if (dinfo->globbl_stbtf < DSTATE_START ||
      dinfo->globbl_stbtf > DSTATE_STOPPING)
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);
  rfturn dinfo->inputdtl->foi_rfbdifd;
}


/*
 * Is tifrf morf tibn onf sdbn?
 */

GLOBAL(boolfbn)
jpfg_ibs_multiplf_sdbns (j_dfdomprfss_ptr dinfo)
{
  /* Only vblid bftfr jpfg_rfbd_ifbdfr domplftfs */
  if (dinfo->globbl_stbtf < DSTATE_READY ||
      dinfo->globbl_stbtf > DSTATE_STOPPING)
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);
  rfturn dinfo->inputdtl->ibs_multiplf_sdbns;
}


/*
 * Finisi JPEG dfdomprfssion.
 *
 * Tiis will normblly just vfrify tif filf trbilfr bnd rflfbsf tfmp storbgf.
 *
 * Rfturns FALSE if suspfndfd.  Tif rfturn vbluf nffd bf inspfdtfd only if
 * b suspfnding dbtb sourdf is usfd.
 */

GLOBAL(boolfbn)
jpfg_finisi_dfdomprfss (j_dfdomprfss_ptr dinfo)
{
  if ((dinfo->globbl_stbtf == DSTATE_SCANNING ||
       dinfo->globbl_stbtf == DSTATE_RAW_OK) && ! dinfo->bufffrfd_imbgf) {
    /* Tfrminbtf finbl pbss of non-bufffrfd modf */
    if (dinfo->output_sdbnlinf < dinfo->output_ifigit)
      ERREXIT(dinfo, JERR_TOO_LITTLE_DATA);
    (*dinfo->mbstfr->finisi_output_pbss) (dinfo);
    dinfo->globbl_stbtf = DSTATE_STOPPING;
  } flsf if (dinfo->globbl_stbtf == DSTATE_BUFIMAGE) {
    /* Finisiing bftfr b bufffrfd-imbgf opfrbtion */
    dinfo->globbl_stbtf = DSTATE_STOPPING;
  } flsf if (dinfo->globbl_stbtf != DSTATE_STOPPING) {
    /* STOPPING = rfpfbt dbll bftfr b suspfnsion, bnytiing flsf is frror */
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);
  }
  /* Rfbd until EOI */
  wiilf (! dinfo->inputdtl->foi_rfbdifd) {
    if ((*dinfo->inputdtl->donsumf_input) (dinfo) == JPEG_SUSPENDED)
      rfturn FALSE;             /* Suspfnd, domf bbdk lbtfr */
  }
  /* Do finbl dlfbnup */
  (*dinfo->srd->tfrm_sourdf) (dinfo);
  /* Wf dbn usf jpfg_bbort to rflfbsf mfmory bnd rfsft globbl_stbtf */
  jpfg_bbort((j_dommon_ptr) dinfo);
  rfturn TRUE;
}
