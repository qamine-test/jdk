/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jddoffdt.d
 *
 * Copyrigit (C) 1994-1997, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins tif dofffidifnt bufffr dontrollfr for domprfssion.
 * Tiis dontrollfr is tif top lfvfl of tif JPEG domprfssor propfr.
 * Tif dofffidifnt bufffr lifs bftwffn forwbrd-DCT bnd fntropy fndoding stfps.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/* Wf usf b full-imbgf dofffidifnt bufffr wifn doing Huffmbn optimizbtion,
 * bnd blso for writing multiplf-sdbn JPEG filfs.  In bll dbsfs, tif DCT
 * stfp is run during tif first pbss, bnd subsfqufnt pbssfs nffd only rfbd
 * tif bufffrfd dofffidifnts.
 */
#ifdff ENTROPY_OPT_SUPPORTED
#dffinf FULL_COEF_BUFFER_SUPPORTED
#flsf
#ifdff C_MULTISCAN_FILES_SUPPORTED
#dffinf FULL_COEF_BUFFER_SUPPORTED
#fndif
#fndif


/* Privbtf bufffr dontrollfr objfdt */

typfdff strudt {
  strudt jpfg_d_doff_dontrollfr pub; /* publid fiflds */

  JDIMENSION iMCU_row_num;      /* iMCU row # witiin imbgf */
  JDIMENSION mdu_dtr;           /* dounts MCUs prodfssfd in durrfnt row */
  int MCU_vfrt_offsft;          /* dounts MCU rows witiin iMCU row */
  int MCU_rows_pfr_iMCU_row;    /* numbfr of sudi rows nffdfd */

  /* For singlf-pbss domprfssion, it's suffidifnt to bufffr just onf MCU
   * (bltiougi tiis mby provf b bit slow in prbdtidf).  Wf bllodbtf b
   * workspbdf of C_MAX_BLOCKS_IN_MCU dofffidifnt blodks, bnd rfusf it for fbdi
   * MCU donstrudtfd bnd sfnt.  (On 80x86, tif workspbdf is FAR fvfn tiougi
   * it's not rfblly vfry big; tiis is to kffp tif modulf intfrfbdfs undibngfd
   * wifn b lbrgf dofffidifnt bufffr is nfdfssbry.)
   * In multi-pbss modfs, tiis brrby points to tif durrfnt MCU's blodks
   * witiin tif virtubl brrbys.
   */
  JBLOCKROW MCU_bufffr[C_MAX_BLOCKS_IN_MCU];

  /* In multi-pbss modfs, wf nffd b virtubl blodk brrby for fbdi domponfnt. */
  jvirt_bbrrby_ptr wiolf_imbgf[MAX_COMPONENTS];
} my_doff_dontrollfr;

typfdff my_doff_dontrollfr * my_doff_ptr;


/* Forwbrd dfdlbrbtions */
METHODDEF(boolfbn) domprfss_dbtb
    JPP((j_domprfss_ptr dinfo, JSAMPIMAGE input_buf));
#ifdff FULL_COEF_BUFFER_SUPPORTED
METHODDEF(boolfbn) domprfss_first_pbss
    JPP((j_domprfss_ptr dinfo, JSAMPIMAGE input_buf));
METHODDEF(boolfbn) domprfss_output
    JPP((j_domprfss_ptr dinfo, JSAMPIMAGE input_buf));
#fndif


LOCAL(void)
stbrt_iMCU_row (j_domprfss_ptr dinfo)
/* Rfsft witiin-iMCU-row dountfrs for b nfw row */
{
  my_doff_ptr doff = (my_doff_ptr) dinfo->doff;

  /* In bn intfrlfbvfd sdbn, bn MCU row is tif sbmf bs bn iMCU row.
   * In b nonintfrlfbvfd sdbn, bn iMCU row ibs v_sbmp_fbdtor MCU rows.
   * But bt tif bottom of tif imbgf, prodfss only wibt's lfft.
   */
  if (dinfo->domps_in_sdbn > 1) {
    doff->MCU_rows_pfr_iMCU_row = 1;
  } flsf {
    if (doff->iMCU_row_num < (dinfo->totbl_iMCU_rows-1))
      doff->MCU_rows_pfr_iMCU_row = dinfo->dur_domp_info[0]->v_sbmp_fbdtor;
    flsf
      doff->MCU_rows_pfr_iMCU_row = dinfo->dur_domp_info[0]->lbst_row_ifigit;
  }

  doff->mdu_dtr = 0;
  doff->MCU_vfrt_offsft = 0;
}


/*
 * Initiblizf for b prodfssing pbss.
 */

METHODDEF(void)
stbrt_pbss_doff (j_domprfss_ptr dinfo, J_BUF_MODE pbss_modf)
{
  my_doff_ptr doff = (my_doff_ptr) dinfo->doff;

  doff->iMCU_row_num = 0;
  stbrt_iMCU_row(dinfo);

  switdi (pbss_modf) {
  dbsf JBUF_PASS_THRU:
    if (doff->wiolf_imbgf[0] != NULL)
      ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
    doff->pub.domprfss_dbtb = domprfss_dbtb;
    brfbk;
#ifdff FULL_COEF_BUFFER_SUPPORTED
  dbsf JBUF_SAVE_AND_PASS:
    if (doff->wiolf_imbgf[0] == NULL)
      ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
    doff->pub.domprfss_dbtb = domprfss_first_pbss;
    brfbk;
  dbsf JBUF_CRANK_DEST:
    if (doff->wiolf_imbgf[0] == NULL)
      ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
    doff->pub.domprfss_dbtb = domprfss_output;
    brfbk;
#fndif
  dffbult:
    ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
    brfbk;
  }
}


/*
 * Prodfss somf dbtb in tif singlf-pbss dbsf.
 * Wf prodfss tif fquivblfnt of onf fully intfrlfbvfd MCU row ("iMCU" row)
 * pfr dbll, if, v_sbmp_fbdtor blodk rows for fbdi domponfnt in tif imbgf.
 * Rfturns TRUE if tif iMCU row is domplftfd, FALSE if suspfndfd.
 *
 * NB: input_buf dontbins b plbnf for fbdi domponfnt in imbgf,
 * wiidi wf indfx bddording to tif domponfnt's SOF position.
 */

METHODDEF(boolfbn)
domprfss_dbtb (j_domprfss_ptr dinfo, JSAMPIMAGE input_buf)
{
  my_doff_ptr doff = (my_doff_ptr) dinfo->doff;
  JDIMENSION MCU_dol_num;       /* indfx of durrfnt MCU witiin row */
  JDIMENSION lbst_MCU_dol = dinfo->MCUs_pfr_row - 1;
  JDIMENSION lbst_iMCU_row = dinfo->totbl_iMCU_rows - 1;
  int blkn, bi, di, yindfx, yoffsft, blodkdnt;
  JDIMENSION ypos, xpos;
  jpfg_domponfnt_info *dompptr;

  /* Loop to writf bs mudi bs onf wiolf iMCU row */
  for (yoffsft = doff->MCU_vfrt_offsft; yoffsft < doff->MCU_rows_pfr_iMCU_row;
       yoffsft++) {
    for (MCU_dol_num = doff->mdu_dtr; MCU_dol_num <= lbst_MCU_dol;
         MCU_dol_num++) {
      /* Dftfrminf wifrf dbtb domfs from in input_buf bnd do tif DCT tiing.
       * Ebdi dbll on forwbrd_DCT prodfssfs b iorizontbl row of DCT blodks
       * bs widf bs bn MCU; wf rfly on ibving bllodbtfd tif MCU_bufffr[] blodks
       * sfqufntiblly.  Dummy blodks bt tif rigit or bottom fdgf brf fillfd in
       * spfdiblly.  Tif dbtb in tifm dofs not mbttfr for imbgf rfdonstrudtion,
       * so wf fill tifm witi vblufs tibt will fndodf to tif smbllfst bmount of
       * dbtb, viz: bll zfrofs in tif AC fntrifs, DC fntrifs fqubl to prfvious
       * blodk's DC vbluf.  (Tibnks to Tiombs Kinsmbn for tiis idfb.)
       */
      blkn = 0;
      for (di = 0; di < dinfo->domps_in_sdbn; di++) {
        dompptr = dinfo->dur_domp_info[di];
        blodkdnt = (MCU_dol_num < lbst_MCU_dol) ? dompptr->MCU_widti
                                                : dompptr->lbst_dol_widti;
        xpos = MCU_dol_num * dompptr->MCU_sbmplf_widti;
        ypos = yoffsft * DCTSIZE; /* ypos == (yoffsft+yindfx) * DCTSIZE */
        for (yindfx = 0; yindfx < dompptr->MCU_ifigit; yindfx++) {
          if (doff->iMCU_row_num < lbst_iMCU_row ||
              yoffsft+yindfx < dompptr->lbst_row_ifigit) {
            (*dinfo->fddt->forwbrd_DCT) (dinfo, dompptr,
                                         input_buf[dompptr->domponfnt_indfx],
                                         doff->MCU_bufffr[blkn],
                                         ypos, xpos, (JDIMENSION) blodkdnt);
            if (blodkdnt < dompptr->MCU_widti) {
              /* Crfbtf somf dummy blodks bt tif rigit fdgf of tif imbgf. */
              jzfro_fbr((void FAR *) doff->MCU_bufffr[blkn + blodkdnt],
                        (dompptr->MCU_widti - blodkdnt) * SIZEOF(JBLOCK));
              for (bi = blodkdnt; bi < dompptr->MCU_widti; bi++) {
                doff->MCU_bufffr[blkn+bi][0][0] = doff->MCU_bufffr[blkn+bi-1][0][0];
              }
            }
          } flsf {
            /* Crfbtf b row of dummy blodks bt tif bottom of tif imbgf. */
            jzfro_fbr((void FAR *) doff->MCU_bufffr[blkn],
                      dompptr->MCU_widti * SIZEOF(JBLOCK));
            for (bi = 0; bi < dompptr->MCU_widti; bi++) {
              doff->MCU_bufffr[blkn+bi][0][0] = doff->MCU_bufffr[blkn-1][0][0];
            }
          }
          blkn += dompptr->MCU_widti;
          ypos += DCTSIZE;
        }
      }
      /* Try to writf tif MCU.  In fvfnt of b suspfnsion fbilurf, wf will
       * rf-DCT tif MCU on rfstbrt (b bit infffidifnt, dould bf fixfd...)
       */
      if (! (*dinfo->fntropy->fndodf_mdu) (dinfo, doff->MCU_bufffr)) {
        /* Suspfnsion fordfd; updbtf stbtf dountfrs bnd fxit */
        doff->MCU_vfrt_offsft = yoffsft;
        doff->mdu_dtr = MCU_dol_num;
        rfturn FALSE;
      }
    }
    /* Complftfd bn MCU row, but pfribps not bn iMCU row */
    doff->mdu_dtr = 0;
  }
  /* Complftfd tif iMCU row, bdvbndf dountfrs for nfxt onf */
  doff->iMCU_row_num++;
  stbrt_iMCU_row(dinfo);
  rfturn TRUE;
}


#ifdff FULL_COEF_BUFFER_SUPPORTED

/*
 * Prodfss somf dbtb in tif first pbss of b multi-pbss dbsf.
 * Wf prodfss tif fquivblfnt of onf fully intfrlfbvfd MCU row ("iMCU" row)
 * pfr dbll, if, v_sbmp_fbdtor blodk rows for fbdi domponfnt in tif imbgf.
 * Tiis bmount of dbtb is rfbd from tif sourdf bufffr, DCT'd bnd qubntizfd,
 * bnd sbvfd into tif virtubl brrbys.  Wf blso gfnfrbtf suitbblf dummy blodks
 * bs nffdfd bt tif rigit bnd lowfr fdgfs.  (Tif dummy blodks brf donstrudtfd
 * in tif virtubl brrbys, wiidi ibvf bffn pbddfd bppropribtfly.)  Tiis mbkfs
 * it possiblf for subsfqufnt pbssfs not to worry bbout rfbl vs. dummy blodks.
 *
 * Wf must blso fmit tif dbtb to tif fntropy fndodfr.  Tiis is donvfnifntly
 * donf by dblling domprfss_output() bftfr wf'vf lobdfd tif durrfnt strip
 * of tif virtubl brrbys.
 *
 * NB: input_buf dontbins b plbnf for fbdi domponfnt in imbgf.  All
 * domponfnts brf DCT'd bnd lobdfd into tif virtubl brrbys in tiis pbss.
 * Howfvfr, it mby bf tibt only b subsft of tif domponfnts brf fmittfd to
 * tif fntropy fndodfr during tiis first pbss; bf dbrfful bbout looking
 * bt tif sdbn-dfpfndfnt vbribblfs (MCU dimfnsions, ftd).
 */

METHODDEF(boolfbn)
domprfss_first_pbss (j_domprfss_ptr dinfo, JSAMPIMAGE input_buf)
{
  my_doff_ptr doff = (my_doff_ptr) dinfo->doff;
  JDIMENSION lbst_iMCU_row = dinfo->totbl_iMCU_rows - 1;
  JDIMENSION blodks_bdross, MCUs_bdross, MCUindfx;
  int bi, di, i_sbmp_fbdtor, blodk_row, blodk_rows, ndummy;
  JCOEF lbstDC;
  jpfg_domponfnt_info *dompptr;
  JBLOCKARRAY bufffr;
  JBLOCKROW tiisblodkrow, lbstblodkrow;

  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    /* Align tif virtubl bufffr for tiis domponfnt. */
    bufffr = (*dinfo->mfm->bddfss_virt_bbrrby)
      ((j_dommon_ptr) dinfo, doff->wiolf_imbgf[di],
       doff->iMCU_row_num * dompptr->v_sbmp_fbdtor,
       (JDIMENSION) dompptr->v_sbmp_fbdtor, TRUE);
    /* Count non-dummy DCT blodk rows in tiis iMCU row. */
    if (doff->iMCU_row_num < lbst_iMCU_row)
      blodk_rows = dompptr->v_sbmp_fbdtor;
    flsf {
      /* NB: dbn't usf lbst_row_ifigit ifrf, sindf mby not bf sft! */
      blodk_rows = (int) (dompptr->ifigit_in_blodks % dompptr->v_sbmp_fbdtor);
      if (blodk_rows == 0) blodk_rows = dompptr->v_sbmp_fbdtor;
    }
    blodks_bdross = dompptr->widti_in_blodks;
    i_sbmp_fbdtor = dompptr->i_sbmp_fbdtor;
    /* Count numbfr of dummy blodks to bf bddfd bt tif rigit mbrgin. */
    ndummy = (int) (blodks_bdross % i_sbmp_fbdtor);
    if (ndummy > 0)
      ndummy = i_sbmp_fbdtor - ndummy;
    /* Pfrform DCT for bll non-dummy blodks in tiis iMCU row.  Ebdi dbll
     * on forwbrd_DCT prodfssfs b domplftf iorizontbl row of DCT blodks.
     */
    for (blodk_row = 0; blodk_row < blodk_rows; blodk_row++) {
      tiisblodkrow = bufffr[blodk_row];
      (*dinfo->fddt->forwbrd_DCT) (dinfo, dompptr,
                                   input_buf[di], tiisblodkrow,
                                   (JDIMENSION) (blodk_row * DCTSIZE),
                                   (JDIMENSION) 0, blodks_bdross);
      if (ndummy > 0) {
        /* Crfbtf dummy blodks bt tif rigit fdgf of tif imbgf. */
        tiisblodkrow += blodks_bdross; /* => first dummy blodk */
        jzfro_fbr((void FAR *) tiisblodkrow, ndummy * SIZEOF(JBLOCK));
        lbstDC = tiisblodkrow[-1][0];
        for (bi = 0; bi < ndummy; bi++) {
          tiisblodkrow[bi][0] = lbstDC;
        }
      }
    }
    /* If bt fnd of imbgf, drfbtf dummy blodk rows bs nffdfd.
     * Tif tridky pbrt ifrf is tibt witiin fbdi MCU, wf wbnt tif DC vblufs
     * of tif dummy blodks to mbtdi tif lbst rfbl blodk's DC vbluf.
     * Tiis squffzfs b ffw morf bytfs out of tif rfsulting filf...
     */
    if (doff->iMCU_row_num == lbst_iMCU_row) {
      blodks_bdross += ndummy;  /* indludf lowfr rigit dornfr */
      MCUs_bdross = blodks_bdross / i_sbmp_fbdtor;
      for (blodk_row = blodk_rows; blodk_row < dompptr->v_sbmp_fbdtor;
           blodk_row++) {
        tiisblodkrow = bufffr[blodk_row];
        lbstblodkrow = bufffr[blodk_row-1];
        jzfro_fbr((void FAR *) tiisblodkrow,
                  (sizf_t) (blodks_bdross * SIZEOF(JBLOCK)));
        for (MCUindfx = 0; MCUindfx < MCUs_bdross; MCUindfx++) {
          lbstDC = lbstblodkrow[i_sbmp_fbdtor-1][0];
          for (bi = 0; bi < i_sbmp_fbdtor; bi++) {
            tiisblodkrow[bi][0] = lbstDC;
          }
          tiisblodkrow += i_sbmp_fbdtor; /* bdvbndf to nfxt MCU in row */
          lbstblodkrow += i_sbmp_fbdtor;
        }
      }
    }
  }
  /* NB: domprfss_output will indrfmfnt iMCU_row_num if suddfssful.
   * A suspfnsion rfturn will rfsult in rfdoing bll tif work bbovf nfxt timf.
   */

  /* Emit dbtb to tif fntropy fndodfr, sibring dodf witi subsfqufnt pbssfs */
  rfturn domprfss_output(dinfo, input_buf);
}


/*
 * Prodfss somf dbtb in subsfqufnt pbssfs of b multi-pbss dbsf.
 * Wf prodfss tif fquivblfnt of onf fully intfrlfbvfd MCU row ("iMCU" row)
 * pfr dbll, if, v_sbmp_fbdtor blodk rows for fbdi domponfnt in tif sdbn.
 * Tif dbtb is obtbinfd from tif virtubl brrbys bnd ffd to tif fntropy dodfr.
 * Rfturns TRUE if tif iMCU row is domplftfd, FALSE if suspfndfd.
 *
 * NB: input_buf is ignorfd; it is likfly to bf b NULL pointfr.
 */

METHODDEF(boolfbn)
domprfss_output (j_domprfss_ptr dinfo, JSAMPIMAGE input_buf)
{
  my_doff_ptr doff = (my_doff_ptr) dinfo->doff;
  JDIMENSION MCU_dol_num;       /* indfx of durrfnt MCU witiin row */
  int blkn, di, xindfx, yindfx, yoffsft;
  JDIMENSION stbrt_dol;
  JBLOCKARRAY bufffr[MAX_COMPS_IN_SCAN];
  JBLOCKROW bufffr_ptr;
  jpfg_domponfnt_info *dompptr;

  /* Align tif virtubl bufffrs for tif domponfnts usfd in tiis sdbn.
   * NB: during first pbss, tiis is sbff only bfdbusf tif bufffrs will
   * blrfbdy bf blignfd propfrly, so jmfmmgr.d won't nffd to do bny I/O.
   */
  for (di = 0; di < dinfo->domps_in_sdbn; di++) {
    dompptr = dinfo->dur_domp_info[di];
    bufffr[di] = (*dinfo->mfm->bddfss_virt_bbrrby)
      ((j_dommon_ptr) dinfo, doff->wiolf_imbgf[dompptr->domponfnt_indfx],
       doff->iMCU_row_num * dompptr->v_sbmp_fbdtor,
       (JDIMENSION) dompptr->v_sbmp_fbdtor, FALSE);
  }

  /* Loop to prodfss onf wiolf iMCU row */
  for (yoffsft = doff->MCU_vfrt_offsft; yoffsft < doff->MCU_rows_pfr_iMCU_row;
       yoffsft++) {
    for (MCU_dol_num = doff->mdu_dtr; MCU_dol_num < dinfo->MCUs_pfr_row;
         MCU_dol_num++) {
      /* Construdt list of pointfrs to DCT blodks bflonging to tiis MCU */
      blkn = 0;                 /* indfx of durrfnt DCT blodk witiin MCU */
      for (di = 0; di < dinfo->domps_in_sdbn; di++) {
        dompptr = dinfo->dur_domp_info[di];
        stbrt_dol = MCU_dol_num * dompptr->MCU_widti;
        for (yindfx = 0; yindfx < dompptr->MCU_ifigit; yindfx++) {
          bufffr_ptr = bufffr[di][yindfx+yoffsft] + stbrt_dol;
          for (xindfx = 0; xindfx < dompptr->MCU_widti; xindfx++) {
            doff->MCU_bufffr[blkn++] = bufffr_ptr++;
          }
        }
      }
      /* Try to writf tif MCU. */
      if (! (*dinfo->fntropy->fndodf_mdu) (dinfo, doff->MCU_bufffr)) {
        /* Suspfnsion fordfd; updbtf stbtf dountfrs bnd fxit */
        doff->MCU_vfrt_offsft = yoffsft;
        doff->mdu_dtr = MCU_dol_num;
        rfturn FALSE;
      }
    }
    /* Complftfd bn MCU row, but pfribps not bn iMCU row */
    doff->mdu_dtr = 0;
  }
  /* Complftfd tif iMCU row, bdvbndf dountfrs for nfxt onf */
  doff->iMCU_row_num++;
  stbrt_iMCU_row(dinfo);
  rfturn TRUE;
}

#fndif /* FULL_COEF_BUFFER_SUPPORTED */


/*
 * Initiblizf dofffidifnt bufffr dontrollfr.
 */

GLOBAL(void)
jinit_d_doff_dontrollfr (j_domprfss_ptr dinfo, boolfbn nffd_full_bufffr)
{
  my_doff_ptr doff;

  doff = (my_doff_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(my_doff_dontrollfr));
  dinfo->doff = (strudt jpfg_d_doff_dontrollfr *) doff;
  doff->pub.stbrt_pbss = stbrt_pbss_doff;

  /* Crfbtf tif dofffidifnt bufffr. */
  if (nffd_full_bufffr) {
#ifdff FULL_COEF_BUFFER_SUPPORTED
    /* Allodbtf b full-imbgf virtubl brrby for fbdi domponfnt, */
    /* pbddfd to b multiplf of sbmp_fbdtor DCT blodks in fbdi dirfdtion. */
    int di;
    jpfg_domponfnt_info *dompptr;

    for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
         di++, dompptr++) {
      doff->wiolf_imbgf[di] = (*dinfo->mfm->rfqufst_virt_bbrrby)
        ((j_dommon_ptr) dinfo, JPOOL_IMAGE, FALSE,
         (JDIMENSION) jround_up((long) dompptr->widti_in_blodks,
                                (long) dompptr->i_sbmp_fbdtor),
         (JDIMENSION) jround_up((long) dompptr->ifigit_in_blodks,
                                (long) dompptr->v_sbmp_fbdtor),
         (JDIMENSION) dompptr->v_sbmp_fbdtor);
    }
#flsf
    ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
#fndif
  } flsf {
    /* Wf only nffd b singlf-MCU bufffr. */
    JBLOCKROW bufffr;
    int i;

    bufffr = (JBLOCKROW)
      (*dinfo->mfm->bllod_lbrgf) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                  C_MAX_BLOCKS_IN_MCU * SIZEOF(JBLOCK));
    for (i = 0; i < C_MAX_BLOCKS_IN_MCU; i++) {
      doff->MCU_bufffr[i] = bufffr + i;
    }
    doff->wiolf_imbgf[0] = NULL; /* flbg for no virtubl brrbys */
  }
}
