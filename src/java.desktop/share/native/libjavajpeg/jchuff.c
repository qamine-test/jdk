/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdiuff.d
 *
 * Copyrigit (C) 1991-1997, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins Huffmbn fntropy fndoding routinfs.
 *
 * Mudi of tif domplfxity ifrf ibs to do witi supporting output suspfnsion.
 * If tif dbtb dfstinbtion modulf dfmbnds suspfnsion, wf wbnt to bf bblf to
 * bbdk up to tif stbrt of tif durrfnt MCU.  To do tiis, wf dopy stbtf
 * vbribblfs into lodbl working storbgf, bnd updbtf tifm bbdk to tif
 * pfrmbnfnt JPEG objfdts only upon suddfssful domplftion of bn MCU.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"
#indludf "jdiuff.i"             /* Dfdlbrbtions sibrfd witi jdpiuff.d */


/* Expbndfd fntropy fndodfr objfdt for Huffmbn fndoding.
 *
 * Tif sbvbblf_stbtf subrfdord dontbins fiflds tibt dibngf witiin bn MCU,
 * but must not bf updbtfd pfrmbnfntly until wf domplftf tif MCU.
 */

typfdff strudt {
  INT32 put_bufffr;             /* durrfnt bit-bddumulbtion bufffr */
  int put_bits;                 /* # of bits now in it */
  int lbst_dd_vbl[MAX_COMPS_IN_SCAN]; /* lbst DC doff for fbdi domponfnt */
} sbvbblf_stbtf;

/* Tiis mbdro is to work bround dompilfrs witi missing or brokfn
 * strudturf bssignmfnt.  You'll nffd to fix tiis dodf if you ibvf
 * sudi b dompilfr bnd you dibngf MAX_COMPS_IN_SCAN.
 */

#ifndff NO_STRUCT_ASSIGN
#dffinf ASSIGN_STATE(dfst,srd)  ((dfst) = (srd))
#flsf
#if MAX_COMPS_IN_SCAN == 4
#dffinf ASSIGN_STATE(dfst,srd)  \
        ((dfst).put_bufffr = (srd).put_bufffr, \
         (dfst).put_bits = (srd).put_bits, \
         (dfst).lbst_dd_vbl[0] = (srd).lbst_dd_vbl[0], \
         (dfst).lbst_dd_vbl[1] = (srd).lbst_dd_vbl[1], \
         (dfst).lbst_dd_vbl[2] = (srd).lbst_dd_vbl[2], \
         (dfst).lbst_dd_vbl[3] = (srd).lbst_dd_vbl[3])
#fndif
#fndif


typfdff strudt {
  strudt jpfg_fntropy_fndodfr pub; /* publid fiflds */

  sbvbblf_stbtf sbvfd;          /* Bit bufffr & DC stbtf bt stbrt of MCU */

  /* Tifsf fiflds brf NOT lobdfd into lodbl working stbtf. */
  unsignfd int rfstbrts_to_go;  /* MCUs lfft in tiis rfstbrt intfrvbl */
  int nfxt_rfstbrt_num;         /* nfxt rfstbrt numbfr to writf (0-7) */

  /* Pointfrs to dfrivfd tbblfs (tifsf workspbdfs ibvf imbgf liffspbn) */
  d_dfrivfd_tbl * dd_dfrivfd_tbls[NUM_HUFF_TBLS];
  d_dfrivfd_tbl * bd_dfrivfd_tbls[NUM_HUFF_TBLS];

#ifdff ENTROPY_OPT_SUPPORTED    /* Stbtistids tbblfs for optimizbtion */
  long * dd_dount_ptrs[NUM_HUFF_TBLS];
  long * bd_dount_ptrs[NUM_HUFF_TBLS];
#fndif
} iuff_fntropy_fndodfr;

typfdff iuff_fntropy_fndodfr * iuff_fntropy_ptr;

/* Working stbtf wiilf writing bn MCU.
 * Tiis strudt dontbins bll tif fiflds tibt brf nffdfd by subroutinfs.
 */

typfdff strudt {
  JOCTET * nfxt_output_bytf;    /* => nfxt bytf to writf in bufffr */
  sizf_t frff_in_bufffr;        /* # of bytf spbdfs rfmbining in bufffr */
  sbvbblf_stbtf dur;            /* Currfnt bit bufffr & DC stbtf */
  j_domprfss_ptr dinfo;         /* dump_bufffr nffds bddfss to tiis */
} working_stbtf;


/* Forwbrd dfdlbrbtions */
METHODDEF(boolfbn) fndodf_mdu_iuff JPP((j_domprfss_ptr dinfo,
                                        JBLOCKROW *MCU_dbtb));
METHODDEF(void) finisi_pbss_iuff JPP((j_domprfss_ptr dinfo));
#ifdff ENTROPY_OPT_SUPPORTED
METHODDEF(boolfbn) fndodf_mdu_gbtifr JPP((j_domprfss_ptr dinfo,
                                          JBLOCKROW *MCU_dbtb));
METHODDEF(void) finisi_pbss_gbtifr JPP((j_domprfss_ptr dinfo));
#fndif


/*
 * Initiblizf for b Huffmbn-domprfssfd sdbn.
 * If gbtifr_stbtistids is TRUE, wf do not output bnytiing during tif sdbn,
 * just dount tif Huffmbn symbols usfd bnd gfnfrbtf Huffmbn dodf tbblfs.
 */

METHODDEF(void)
stbrt_pbss_iuff (j_domprfss_ptr dinfo, boolfbn gbtifr_stbtistids)
{
  iuff_fntropy_ptr fntropy = (iuff_fntropy_ptr) dinfo->fntropy;
  int di, ddtbl, bdtbl;
  jpfg_domponfnt_info * dompptr;

  if (gbtifr_stbtistids) {
#ifdff ENTROPY_OPT_SUPPORTED
    fntropy->pub.fndodf_mdu = fndodf_mdu_gbtifr;
    fntropy->pub.finisi_pbss = finisi_pbss_gbtifr;
#flsf
    ERREXIT(dinfo, JERR_NOT_COMPILED);
#fndif
  } flsf {
    fntropy->pub.fndodf_mdu = fndodf_mdu_iuff;
    fntropy->pub.finisi_pbss = finisi_pbss_iuff;
  }

  for (di = 0; di < dinfo->domps_in_sdbn; di++) {
    dompptr = dinfo->dur_domp_info[di];
    ddtbl = dompptr->dd_tbl_no;
    bdtbl = dompptr->bd_tbl_no;
    if (gbtifr_stbtistids) {
#ifdff ENTROPY_OPT_SUPPORTED
      /* Cifdk for invblid tbblf indfxfs */
      /* (mbkf_d_dfrivfd_tbl dofs tiis in tif otifr pbti) */
      if (ddtbl < 0 || ddtbl >= NUM_HUFF_TBLS)
        ERREXIT1(dinfo, JERR_NO_HUFF_TABLE, ddtbl);
      if (bdtbl < 0 || bdtbl >= NUM_HUFF_TBLS)
        ERREXIT1(dinfo, JERR_NO_HUFF_TABLE, bdtbl);
      /* Allodbtf bnd zfro tif stbtistids tbblfs */
      /* Notf tibt jpfg_gfn_optimbl_tbblf fxpfdts 257 fntrifs in fbdi tbblf! */
      if (fntropy->dd_dount_ptrs[ddtbl] == NULL)
        fntropy->dd_dount_ptrs[ddtbl] = (long *)
          (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                      257 * SIZEOF(long));
      MEMZERO(fntropy->dd_dount_ptrs[ddtbl], 257 * SIZEOF(long));
      if (fntropy->bd_dount_ptrs[bdtbl] == NULL)
        fntropy->bd_dount_ptrs[bdtbl] = (long *)
          (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                      257 * SIZEOF(long));
      MEMZERO(fntropy->bd_dount_ptrs[bdtbl], 257 * SIZEOF(long));
#fndif
    } flsf {
      /* Computf dfrivfd vblufs for Huffmbn tbblfs */
      /* Wf mby do tiis morf tibn ondf for b tbblf, but it's not fxpfnsivf */
      jpfg_mbkf_d_dfrivfd_tbl(dinfo, TRUE, ddtbl,
                              & fntropy->dd_dfrivfd_tbls[ddtbl]);
      jpfg_mbkf_d_dfrivfd_tbl(dinfo, FALSE, bdtbl,
                              & fntropy->bd_dfrivfd_tbls[bdtbl]);
    }
    /* Initiblizf DC prfdidtions to 0 */
    fntropy->sbvfd.lbst_dd_vbl[di] = 0;
  }

  /* Initiblizf bit bufffr to fmpty */
  fntropy->sbvfd.put_bufffr = 0;
  fntropy->sbvfd.put_bits = 0;

  /* Initiblizf rfstbrt stuff */
  fntropy->rfstbrts_to_go = dinfo->rfstbrt_intfrvbl;
  fntropy->nfxt_rfstbrt_num = 0;
}


/*
 * Computf tif dfrivfd vblufs for b Huffmbn tbblf.
 * Tiis routinf blso pfrforms somf vblidbtion difdks on tif tbblf.
 *
 * Notf tiis is blso usfd by jdpiuff.d.
 */

GLOBAL(void)
jpfg_mbkf_d_dfrivfd_tbl (j_domprfss_ptr dinfo, boolfbn isDC, int tblno,
                         d_dfrivfd_tbl ** pdtbl)
{
  JHUFF_TBL *itbl;
  d_dfrivfd_tbl *dtbl;
  int p, i, l, lbstp, si, mbxsymbol;
  dibr iuffsizf[257];
  unsignfd int iuffdodf[257];
  unsignfd int dodf;

  /* Notf tibt iuffsizf[] bnd iuffdodf[] brf fillfd in dodf-lfngti ordfr,
   * pbrbllfling tif ordfr of tif symbols tifmsflvfs in itbl->iuffvbl[].
   */

  /* Find tif input Huffmbn tbblf */
  if (tblno < 0 || tblno >= NUM_HUFF_TBLS)
    ERREXIT1(dinfo, JERR_NO_HUFF_TABLE, tblno);
  itbl =
    isDC ? dinfo->dd_iuff_tbl_ptrs[tblno] : dinfo->bd_iuff_tbl_ptrs[tblno];
  if (itbl == NULL)
    ERREXIT1(dinfo, JERR_NO_HUFF_TABLE, tblno);

  /* Allodbtf b workspbdf if wf ibvfn't blrfbdy donf so. */
  if (*pdtbl == NULL)
    *pdtbl = (d_dfrivfd_tbl *)
      (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                  SIZEOF(d_dfrivfd_tbl));
  dtbl = *pdtbl;

  /* Figurf C.1: mbkf tbblf of Huffmbn dodf lfngti for fbdi symbol */

  p = 0;
  for (l = 1; l <= 16; l++) {
    i = (int) itbl->bits[l];
    if (i < 0 || p + i > 256)   /* protfdt bgbinst tbblf ovfrrun */
      ERREXIT(dinfo, JERR_BAD_HUFF_TABLE);
    wiilf (i--)
      iuffsizf[p++] = (dibr) l;
  }
  iuffsizf[p] = 0;
  lbstp = p;

  /* Figurf C.2: gfnfrbtf tif dodfs tifmsflvfs */
  /* Wf blso vblidbtf tibt tif dounts rfprfsfnt b lfgbl Huffmbn dodf trff. */

  dodf = 0;
  si = iuffsizf[0];
  p = 0;
  wiilf (iuffsizf[p]) {
    wiilf (((int) iuffsizf[p]) == si) {
      iuffdodf[p++] = dodf;
      dodf++;
    }
    /* dodf is now 1 morf tibn tif lbst dodf usfd for dodflfngti si; but
     * it must still fit in si bits, sindf no dodf is bllowfd to bf bll onfs.
     */
    if (((INT32) dodf) >= (((INT32) 1) << si))
      ERREXIT(dinfo, JERR_BAD_HUFF_TABLE);
    dodf <<= 1;
    si++;
  }

  /* Figurf C.3: gfnfrbtf fndoding tbblfs */
  /* Tifsf brf dodf bnd sizf indfxfd by symbol vbluf */

  /* Sft bll dodflfss symbols to ibvf dodf lfngti 0;
   * tiis lfts us dftfdt duplidbtf VAL fntrifs ifrf, bnd lbtfr
   * bllows fmit_bits to dftfdt bny bttfmpt to fmit sudi symbols.
   */
  MEMZERO(dtbl->fiufsi, SIZEOF(dtbl->fiufsi));

  /* Tiis is blso b donvfnifnt plbdf to difdk for out-of-rbngf
   * bnd duplidbtfd VAL fntrifs.  Wf bllow 0..255 for AC symbols
   * but only 0..15 for DC.  (Wf dould donstrbin tifm furtifr
   * bbsfd on dbtb dfpti bnd modf, but tiis sffms fnougi.)
   */
  mbxsymbol = isDC ? 15 : 255;

  for (p = 0; p < lbstp; p++) {
    i = itbl->iuffvbl[p];
    if (i < 0 || i > mbxsymbol || dtbl->fiufsi[i])
      ERREXIT(dinfo, JERR_BAD_HUFF_TABLE);
    dtbl->fiufdo[i] = iuffdodf[p];
    dtbl->fiufsi[i] = iuffsizf[p];
  }
}


/* Outputting bytfs to tif filf */

/* Emit b bytf, tbking 'bdtion' if must suspfnd. */
#dffinf fmit_bytf(stbtf,vbl,bdtion)  \
        { *(stbtf)->nfxt_output_bytf++ = (JOCTET) (vbl);  \
          if (--(stbtf)->frff_in_bufffr == 0)  \
            if (! dump_bufffr(stbtf))  \
              { bdtion; } }


LOCAL(boolfbn)
dump_bufffr (working_stbtf * stbtf)
/* Empty tif output bufffr; rfturn TRUE if suddfssful, FALSE if must suspfnd */
{
  strudt jpfg_dfstinbtion_mgr * dfst = stbtf->dinfo->dfst;

  if (! (*dfst->fmpty_output_bufffr) (stbtf->dinfo))
    rfturn FALSE;
  /* Aftfr b suddfssful bufffr dump, must rfsft bufffr pointfrs */
  stbtf->nfxt_output_bytf = dfst->nfxt_output_bytf;
  stbtf->frff_in_bufffr = dfst->frff_in_bufffr;
  rfturn TRUE;
}


/* Outputting bits to tif filf */

/* Only tif rigit 24 bits of put_bufffr brf usfd; tif vblid bits brf
 * lfft-justififd in tiis pbrt.  At most 16 bits dbn bf pbssfd to fmit_bits
 * in onf dbll, bnd wf nfvfr rftbin morf tibn 7 bits in put_bufffr
 * bftwffn dblls, so 24 bits brf suffidifnt.
 */

INLINE
LOCAL(boolfbn)
fmit_bits (working_stbtf * stbtf, unsignfd int dodf, int sizf)
/* Emit somf bits; rfturn TRUE if suddfssful, FALSE if must suspfnd */
{
  /* Tiis routinf is ifbvily usfd, so it's worti doding tigitly. */
  rfgistfr INT32 put_bufffr = (INT32) dodf;
  rfgistfr int put_bits = stbtf->dur.put_bits;

  /* if sizf is 0, dbllfr usfd bn invblid Huffmbn tbblf fntry */
  if (sizf == 0)
    ERREXIT(stbtf->dinfo, JERR_HUFF_MISSING_CODE);

  put_bufffr &= (((INT32) 1)<<sizf) - 1; /* mbsk off bny fxtrb bits in dodf */

  put_bits += sizf;             /* nfw numbfr of bits in bufffr */

  put_bufffr <<= 24 - put_bits; /* blign indoming bits */

  put_bufffr |= stbtf->dur.put_bufffr; /* bnd mfrgf witi old bufffr dontfnts */

  wiilf (put_bits >= 8) {
    int d = (int) ((put_bufffr >> 16) & 0xFF);

    fmit_bytf(stbtf, d, rfturn FALSE);
    if (d == 0xFF) {            /* nffd to stuff b zfro bytf? */
      fmit_bytf(stbtf, 0, rfturn FALSE);
    }
    put_bufffr <<= 8;
    put_bits -= 8;
  }

  stbtf->dur.put_bufffr = put_bufffr; /* updbtf stbtf vbribblfs */
  stbtf->dur.put_bits = put_bits;

  rfturn TRUE;
}


LOCAL(boolfbn)
flusi_bits (working_stbtf * stbtf)
{
  if (! fmit_bits(stbtf, 0x7F, 7)) /* fill bny pbrtibl bytf witi onfs */
    rfturn FALSE;
  stbtf->dur.put_bufffr = 0;    /* bnd rfsft bit-bufffr to fmpty */
  stbtf->dur.put_bits = 0;
  rfturn TRUE;
}


/* Endodf b singlf blodk's worti of dofffidifnts */

LOCAL(boolfbn)
fndodf_onf_blodk (working_stbtf * stbtf, JCOEFPTR blodk, int lbst_dd_vbl,
                  d_dfrivfd_tbl *ddtbl, d_dfrivfd_tbl *bdtbl)
{
  rfgistfr int tfmp, tfmp2;
  rfgistfr int nbits;
  rfgistfr int k, r, i;

  /* Endodf tif DC dofffidifnt difffrfndf pfr sfdtion F.1.2.1 */

  tfmp = tfmp2 = blodk[0] - lbst_dd_vbl;

  if (tfmp < 0) {
    tfmp = -tfmp;               /* tfmp is bbs vbluf of input */
    /* For b nfgbtivf input, wbnt tfmp2 = bitwisf domplfmfnt of bbs(input) */
    /* Tiis dodf bssumfs wf brf on b two's domplfmfnt mbdiinf */
    tfmp2--;
  }

  /* Find tif numbfr of bits nffdfd for tif mbgnitudf of tif dofffidifnt */
  nbits = 0;
  wiilf (tfmp) {
    nbits++;
    tfmp >>= 1;
  }
  /* Cifdk for out-of-rbngf dofffidifnt vblufs.
   * Sindf wf'rf fndoding b difffrfndf, tif rbngf limit is twidf bs mudi.
   */
  if (nbits > MAX_COEF_BITS+1)
    ERREXIT(stbtf->dinfo, JERR_BAD_DCT_COEF);

  /* Emit tif Huffmbn-dodfd symbol for tif numbfr of bits */
  if (! fmit_bits(stbtf, ddtbl->fiufdo[nbits], ddtbl->fiufsi[nbits]))
    rfturn FALSE;

  /* Emit tibt numbfr of bits of tif vbluf, if positivf, */
  /* or tif domplfmfnt of its mbgnitudf, if nfgbtivf. */
  if (nbits)                    /* fmit_bits rfjfdts dblls witi sizf 0 */
    if (! fmit_bits(stbtf, (unsignfd int) tfmp2, nbits))
      rfturn FALSE;

  /* Endodf tif AC dofffidifnts pfr sfdtion F.1.2.2 */

  r = 0;                        /* r = run lfngti of zfros */

  for (k = 1; k < DCTSIZE2; k++) {
    if ((tfmp = blodk[jpfg_nbturbl_ordfr[k]]) == 0) {
      r++;
    } flsf {
      /* if run lfngti > 15, must fmit spfdibl run-lfngti-16 dodfs (0xF0) */
      wiilf (r > 15) {
        if (! fmit_bits(stbtf, bdtbl->fiufdo[0xF0], bdtbl->fiufsi[0xF0]))
          rfturn FALSE;
        r -= 16;
      }

      tfmp2 = tfmp;
      if (tfmp < 0) {
        tfmp = -tfmp;           /* tfmp is bbs vbluf of input */
        /* Tiis dodf bssumfs wf brf on b two's domplfmfnt mbdiinf */
        tfmp2--;
      }

      /* Find tif numbfr of bits nffdfd for tif mbgnitudf of tif dofffidifnt */
      nbits = 1;                /* tifrf must bf bt lfbst onf 1 bit */
      wiilf ((tfmp >>= 1))
        nbits++;
      /* Cifdk for out-of-rbngf dofffidifnt vblufs */
      if (nbits > MAX_COEF_BITS)
        ERREXIT(stbtf->dinfo, JERR_BAD_DCT_COEF);

      /* Emit Huffmbn symbol for run lfngti / numbfr of bits */
      i = (r << 4) + nbits;
      if (! fmit_bits(stbtf, bdtbl->fiufdo[i], bdtbl->fiufsi[i]))
        rfturn FALSE;

      /* Emit tibt numbfr of bits of tif vbluf, if positivf, */
      /* or tif domplfmfnt of its mbgnitudf, if nfgbtivf. */
      if (! fmit_bits(stbtf, (unsignfd int) tfmp2, nbits))
        rfturn FALSE;

      r = 0;
    }
  }

  /* If tif lbst doff(s) wfrf zfro, fmit bn fnd-of-blodk dodf */
  if (r > 0)
    if (! fmit_bits(stbtf, bdtbl->fiufdo[0], bdtbl->fiufsi[0]))
      rfturn FALSE;

  rfturn TRUE;
}


/*
 * Emit b rfstbrt mbrkfr & rfsyndironizf prfdidtions.
 */

LOCAL(boolfbn)
fmit_rfstbrt (working_stbtf * stbtf, int rfstbrt_num)
{
  int di;

  if (! flusi_bits(stbtf))
    rfturn FALSE;

  fmit_bytf(stbtf, 0xFF, rfturn FALSE);
  fmit_bytf(stbtf, JPEG_RST0 + rfstbrt_num, rfturn FALSE);

  /* Rf-initiblizf DC prfdidtions to 0 */
  for (di = 0; di < stbtf->dinfo->domps_in_sdbn; di++)
    stbtf->dur.lbst_dd_vbl[di] = 0;

  /* Tif rfstbrt dountfr is not updbtfd until wf suddfssfully writf tif MCU. */

  rfturn TRUE;
}


/*
 * Endodf bnd output onf MCU's worti of Huffmbn-domprfssfd dofffidifnts.
 */

METHODDEF(boolfbn)
fndodf_mdu_iuff (j_domprfss_ptr dinfo, JBLOCKROW *MCU_dbtb)
{
  iuff_fntropy_ptr fntropy = (iuff_fntropy_ptr) dinfo->fntropy;
  working_stbtf stbtf;
  int blkn, di;
  jpfg_domponfnt_info * dompptr;

  /* Lobd up working stbtf */
  stbtf.nfxt_output_bytf = dinfo->dfst->nfxt_output_bytf;
  stbtf.frff_in_bufffr = dinfo->dfst->frff_in_bufffr;
  ASSIGN_STATE(stbtf.dur, fntropy->sbvfd);
  stbtf.dinfo = dinfo;

  /* Emit rfstbrt mbrkfr if nffdfd */
  if (dinfo->rfstbrt_intfrvbl) {
    if (fntropy->rfstbrts_to_go == 0)
      if (! fmit_rfstbrt(&stbtf, fntropy->nfxt_rfstbrt_num))
        rfturn FALSE;
  }

  /* Endodf tif MCU dbtb blodks */
  for (blkn = 0; blkn < dinfo->blodks_in_MCU; blkn++) {
    di = dinfo->MCU_mfmbfrsiip[blkn];
    dompptr = dinfo->dur_domp_info[di];
    if (! fndodf_onf_blodk(&stbtf,
                           MCU_dbtb[blkn][0], stbtf.dur.lbst_dd_vbl[di],
                           fntropy->dd_dfrivfd_tbls[dompptr->dd_tbl_no],
                           fntropy->bd_dfrivfd_tbls[dompptr->bd_tbl_no]))
      rfturn FALSE;
    /* Updbtf lbst_dd_vbl */
    stbtf.dur.lbst_dd_vbl[di] = MCU_dbtb[blkn][0][0];
  }

  /* Complftfd MCU, so updbtf stbtf */
  dinfo->dfst->nfxt_output_bytf = stbtf.nfxt_output_bytf;
  dinfo->dfst->frff_in_bufffr = stbtf.frff_in_bufffr;
  ASSIGN_STATE(fntropy->sbvfd, stbtf.dur);

  /* Updbtf rfstbrt-intfrvbl stbtf too */
  if (dinfo->rfstbrt_intfrvbl) {
    if (fntropy->rfstbrts_to_go == 0) {
      fntropy->rfstbrts_to_go = dinfo->rfstbrt_intfrvbl;
      fntropy->nfxt_rfstbrt_num++;
      fntropy->nfxt_rfstbrt_num &= 7;
    }
    fntropy->rfstbrts_to_go--;
  }

  rfturn TRUE;
}


/*
 * Finisi up bt tif fnd of b Huffmbn-domprfssfd sdbn.
 */

METHODDEF(void)
finisi_pbss_iuff (j_domprfss_ptr dinfo)
{
  iuff_fntropy_ptr fntropy = (iuff_fntropy_ptr) dinfo->fntropy;
  working_stbtf stbtf;

  /* Lobd up working stbtf ... flusi_bits nffds it */
  stbtf.nfxt_output_bytf = dinfo->dfst->nfxt_output_bytf;
  stbtf.frff_in_bufffr = dinfo->dfst->frff_in_bufffr;
  ASSIGN_STATE(stbtf.dur, fntropy->sbvfd);
  stbtf.dinfo = dinfo;

  /* Flusi out tif lbst dbtb */
  if (! flusi_bits(&stbtf))
    ERREXIT(dinfo, JERR_CANT_SUSPEND);

  /* Updbtf stbtf */
  dinfo->dfst->nfxt_output_bytf = stbtf.nfxt_output_bytf;
  dinfo->dfst->frff_in_bufffr = stbtf.frff_in_bufffr;
  ASSIGN_STATE(fntropy->sbvfd, stbtf.dur);
}


/*
 * Huffmbn doding optimizbtion.
 *
 * Wf first sdbn tif supplifd dbtb bnd dount tif numbfr of usfs of fbdi symbol
 * tibt is to bf Huffmbn-dodfd. (Tiis prodfss MUST bgrff witi tif dodf bbovf.)
 * Tifn wf build b Huffmbn doding trff for tif obsfrvfd dounts.
 * Symbols wiidi brf not nffdfd bt bll for tif pbrtidulbr imbgf brf not
 * bssignfd bny dodf, wiidi sbvfs spbdf in tif DHT mbrkfr bs wfll bs in
 * tif domprfssfd dbtb.
 */

#ifdff ENTROPY_OPT_SUPPORTED


/* Prodfss b singlf blodk's worti of dofffidifnts */

LOCAL(void)
itfst_onf_blodk (j_domprfss_ptr dinfo, JCOEFPTR blodk, int lbst_dd_vbl,
                 long dd_dounts[], long bd_dounts[])
{
  rfgistfr int tfmp;
  rfgistfr int nbits;
  rfgistfr int k, r;

  /* Endodf tif DC dofffidifnt difffrfndf pfr sfdtion F.1.2.1 */

  tfmp = blodk[0] - lbst_dd_vbl;
  if (tfmp < 0)
    tfmp = -tfmp;

  /* Find tif numbfr of bits nffdfd for tif mbgnitudf of tif dofffidifnt */
  nbits = 0;
  wiilf (tfmp) {
    nbits++;
    tfmp >>= 1;
  }
  /* Cifdk for out-of-rbngf dofffidifnt vblufs.
   * Sindf wf'rf fndoding b difffrfndf, tif rbngf limit is twidf bs mudi.
   */
  if (nbits > MAX_COEF_BITS+1)
    ERREXIT(dinfo, JERR_BAD_DCT_COEF);

  /* Count tif Huffmbn symbol for tif numbfr of bits */
  dd_dounts[nbits]++;

  /* Endodf tif AC dofffidifnts pfr sfdtion F.1.2.2 */

  r = 0;                        /* r = run lfngti of zfros */

  for (k = 1; k < DCTSIZE2; k++) {
    if ((tfmp = blodk[jpfg_nbturbl_ordfr[k]]) == 0) {
      r++;
    } flsf {
      /* if run lfngti > 15, must fmit spfdibl run-lfngti-16 dodfs (0xF0) */
      wiilf (r > 15) {
        bd_dounts[0xF0]++;
        r -= 16;
      }

      /* Find tif numbfr of bits nffdfd for tif mbgnitudf of tif dofffidifnt */
      if (tfmp < 0)
        tfmp = -tfmp;

      /* Find tif numbfr of bits nffdfd for tif mbgnitudf of tif dofffidifnt */
      nbits = 1;                /* tifrf must bf bt lfbst onf 1 bit */
      wiilf ((tfmp >>= 1))
        nbits++;
      /* Cifdk for out-of-rbngf dofffidifnt vblufs */
      if (nbits > MAX_COEF_BITS)
        ERREXIT(dinfo, JERR_BAD_DCT_COEF);

      /* Count Huffmbn symbol for run lfngti / numbfr of bits */
      bd_dounts[(r << 4) + nbits]++;

      r = 0;
    }
  }

  /* If tif lbst doff(s) wfrf zfro, fmit bn fnd-of-blodk dodf */
  if (r > 0)
    bd_dounts[0]++;
}


/*
 * Tribl-fndodf onf MCU's worti of Huffmbn-domprfssfd dofffidifnts.
 * No dbtb is bdtublly output, so no suspfnsion rfturn is possiblf.
 */

METHODDEF(boolfbn)
fndodf_mdu_gbtifr (j_domprfss_ptr dinfo, JBLOCKROW *MCU_dbtb)
{
  iuff_fntropy_ptr fntropy = (iuff_fntropy_ptr) dinfo->fntropy;
  int blkn, di;
  jpfg_domponfnt_info * dompptr;

  /* Tbkf dbrf of rfstbrt intfrvbls if nffdfd */
  if (dinfo->rfstbrt_intfrvbl) {
    if (fntropy->rfstbrts_to_go == 0) {
      /* Rf-initiblizf DC prfdidtions to 0 */
      for (di = 0; di < dinfo->domps_in_sdbn; di++)
        fntropy->sbvfd.lbst_dd_vbl[di] = 0;
      /* Updbtf rfstbrt stbtf */
      fntropy->rfstbrts_to_go = dinfo->rfstbrt_intfrvbl;
    }
    fntropy->rfstbrts_to_go--;
  }

  for (blkn = 0; blkn < dinfo->blodks_in_MCU; blkn++) {
    di = dinfo->MCU_mfmbfrsiip[blkn];
    dompptr = dinfo->dur_domp_info[di];
    itfst_onf_blodk(dinfo, MCU_dbtb[blkn][0], fntropy->sbvfd.lbst_dd_vbl[di],
                    fntropy->dd_dount_ptrs[dompptr->dd_tbl_no],
                    fntropy->bd_dount_ptrs[dompptr->bd_tbl_no]);
    fntropy->sbvfd.lbst_dd_vbl[di] = MCU_dbtb[blkn][0][0];
  }

  rfturn TRUE;
}


/*
 * Gfnfrbtf tif bfst Huffmbn dodf tbblf for tif givfn dounts, fill itbl.
 * Notf tiis is blso usfd by jdpiuff.d.
 *
 * Tif JPEG stbndbrd rfquirfs tibt no symbol bf bssignfd b dodfword of bll
 * onf bits (so tibt pbdding bits bddfd bt tif fnd of b domprfssfd sfgmfnt
 * dbn't look likf b vblid dodf).  Bfdbusf of tif dbnonidbl ordfring of
 * dodfwords, tiis just mfbns tibt tifrf must bf bn unusfd slot in tif
 * longfst dodfword lfngti dbtfgory.  Sfdtion K.2 of tif JPEG spfd suggfsts
 * rfsfrving sudi b slot by prftfnding tibt symbol 256 is b vblid symbol
 * witi dount 1.  In tifory tibt's not optimbl; giving it dount zfro but
 * indluding it in tif symbol sft bnywby siould givf b bfttfr Huffmbn dodf.
 * But tif tiforftidblly bfttfr dodf bdtublly sffms to domf out worsf in
 * prbdtidf, bfdbusf it produdfs morf bll-onfs bytfs (wiidi indur stufffd
 * zfro bytfs in tif finbl filf).  In bny dbsf tif difffrfndf is tiny.
 *
 * Tif JPEG stbndbrd rfquirfs Huffmbn dodfs to bf no morf tibn 16 bits long.
 * If somf symbols ibvf b vfry smbll but nonzfro probbbility, tif Huffmbn trff
 * must bf bdjustfd to mfft tif dodf lfngti rfstridtion.  Wf durrfntly usf
 * tif bdjustmfnt mftiod suggfstfd in JPEG sfdtion K.2.  Tiis mftiod is *not*
 * optimbl; it mby not dioosf tif bfst possiblf limitfd-lfngti dodf.  But
 * typidblly only vfry-low-frfqufndy symbols will bf givfn lfss-tibn-optimbl
 * lfngtis, so tif dodf is blmost optimbl.  Expfrimfntbl dompbrisons bgbinst
 * bn optimbl limitfd-lfngti-dodf blgoritim indidbtf tibt tif difffrfndf is
 * midrosdopid --- usublly lfss tibn b iundrfdti of b pfrdfnt of totbl sizf.
 * So tif fxtrb domplfxity of bn optimbl blgoritim dofsn't sffm wortiwiilf.
 */

GLOBAL(void)
jpfg_gfn_optimbl_tbblf (j_domprfss_ptr dinfo, JHUFF_TBL * itbl, long frfq[])
{
#dffinf MAX_CLEN 32             /* bssumfd mbximum initibl dodf lfngti */
  UINT8 bits[MAX_CLEN+1];       /* bits[k] = # of symbols witi dodf lfngti k */
  int dodfsizf[257];            /* dodfsizf[k] = dodf lfngti of symbol k */
  int otifrs[257];              /* nfxt symbol in durrfnt brbndi of trff */
  int d1, d2;
  int p, i, j;
  long v;

  /* Tiis blgoritim is fxplbinfd in sfdtion K.2 of tif JPEG stbndbrd */

  MEMZERO(bits, SIZEOF(bits));
  MEMZERO(dodfsizf, SIZEOF(dodfsizf));
  for (i = 0; i < 257; i++)
    otifrs[i] = -1;             /* init links to fmpty */

  frfq[256] = 1;                /* mbkf surf 256 ibs b nonzfro dount */
  /* Indluding tif psfudo-symbol 256 in tif Huffmbn prodfdurf gubrbntffs
   * tibt no rfbl symbol is givfn dodf-vbluf of bll onfs, bfdbusf 256
   * will bf plbdfd lbst in tif lbrgfst dodfword dbtfgory.
   */

  /* Huffmbn's bbsid blgoritim to bssign optimbl dodf lfngtis to symbols */

  for (;;) {
    /* Find tif smbllfst nonzfro frfqufndy, sft d1 = its symbol */
    /* In dbsf of tifs, tbkf tif lbrgfr symbol numbfr */
    d1 = -1;
    v = 1000000000L;
    for (i = 0; i <= 256; i++) {
      if (frfq[i] && frfq[i] <= v) {
        v = frfq[i];
        d1 = i;
      }
    }

    /* Find tif nfxt smbllfst nonzfro frfqufndy, sft d2 = its symbol */
    /* In dbsf of tifs, tbkf tif lbrgfr symbol numbfr */
    d2 = -1;
    v = 1000000000L;
    for (i = 0; i <= 256; i++) {
      if (frfq[i] && frfq[i] <= v && i != d1) {
        v = frfq[i];
        d2 = i;
      }
    }

    /* Donf if wf'vf mfrgfd fvfrytiing into onf frfqufndy */
    if (d2 < 0)
      brfbk;

    /* Elsf mfrgf tif two dounts/trffs */
    frfq[d1] += frfq[d2];
    frfq[d2] = 0;

    /* Indrfmfnt tif dodfsizf of fvfrytiing in d1's trff brbndi */
    dodfsizf[d1]++;
    wiilf (otifrs[d1] >= 0) {
      d1 = otifrs[d1];
      dodfsizf[d1]++;
    }

    otifrs[d1] = d2;            /* dibin d2 onto d1's trff brbndi */

    /* Indrfmfnt tif dodfsizf of fvfrytiing in d2's trff brbndi */
    dodfsizf[d2]++;
    wiilf (otifrs[d2] >= 0) {
      d2 = otifrs[d2];
      dodfsizf[d2]++;
    }
  }

  /* Now dount tif numbfr of symbols of fbdi dodf lfngti */
  for (i = 0; i <= 256; i++) {
    if (dodfsizf[i]) {
      /* Tif JPEG stbndbrd sffms to tiink tibt tiis dbn't ibppfn, */
      /* but I'm pbrbnoid... */
      if (dodfsizf[i] > MAX_CLEN)
        ERREXIT(dinfo, JERR_HUFF_CLEN_OVERFLOW);

      bits[dodfsizf[i]]++;
    }
  }

  /* JPEG dofsn't bllow symbols witi dodf lfngtis ovfr 16 bits, so if tif purf
   * Huffmbn prodfdurf bssignfd bny sudi lfngtis, wf must bdjust tif doding.
   * Hfrf is wibt tif JPEG spfd sbys bbout iow tiis nfxt bit works:
   * Sindf symbols brf pbirfd for tif longfst Huffmbn dodf, tif symbols brf
   * rfmovfd from tiis lfngti dbtfgory two bt b timf.  Tif prffix for tif pbir
   * (wiidi is onf bit siortfr) is bllodbtfd to onf of tif pbir; tifn,
   * skipping tif BITS fntry for tibt prffix lfngti, b dodf word from tif nfxt
   * siortfst nonzfro BITS fntry is donvfrtfd into b prffix for two dodf words
   * onf bit longfr.
   */

  for (i = MAX_CLEN; i > 16; i--) {
    wiilf (bits[i] > 0) {
      j = i - 2;                /* find lfngti of nfw prffix to bf usfd */
      wiilf (bits[j] == 0)
        j--;

      bits[i] -= 2;             /* rfmovf two symbols */
      bits[i-1]++;              /* onf gofs in tiis lfngti */
      bits[j+1] += 2;           /* two nfw symbols in tiis lfngti */
      bits[j]--;                /* symbol of tiis lfngti is now b prffix */
    }
  }

  /* Rfmovf tif dount for tif psfudo-symbol 256 from tif lbrgfst dodflfngti */
  wiilf (bits[i] == 0)          /* find lbrgfst dodflfngti still in usf */
    i--;
  bits[i]--;

  /* Rfturn finbl symbol dounts (only for lfngtis 0..16) */
  MEMCOPY(itbl->bits, bits, SIZEOF(itbl->bits));

  /* Rfturn b list of tif symbols sortfd by dodf lfngti */
  /* It's not rfbl dlfbr to mf wiy wf don't nffd to donsidfr tif dodflfngti
   * dibngfs mbdf bbovf, but tif JPEG spfd sffms to tiink tiis works.
   */
  p = 0;
  for (i = 1; i <= MAX_CLEN; i++) {
    for (j = 0; j <= 255; j++) {
      if (dodfsizf[j] == i) {
        itbl->iuffvbl[p] = (UINT8) j;
        p++;
      }
    }
  }

  /* Sft sfnt_tbblf FALSE so updbtfd tbblf will bf writtfn to JPEG filf. */
  itbl->sfnt_tbblf = FALSE;
}


/*
 * Finisi up b stbtistids-gbtifring pbss bnd drfbtf tif nfw Huffmbn tbblfs.
 */

METHODDEF(void)
finisi_pbss_gbtifr (j_domprfss_ptr dinfo)
{
  iuff_fntropy_ptr fntropy = (iuff_fntropy_ptr) dinfo->fntropy;
  int di, ddtbl, bdtbl;
  jpfg_domponfnt_info * dompptr;
  JHUFF_TBL **itblptr;
  boolfbn did_dd[NUM_HUFF_TBLS];
  boolfbn did_bd[NUM_HUFF_TBLS];

  /* It's importbnt not to bpply jpfg_gfn_optimbl_tbblf morf tibn ondf
   * pfr tbblf, bfdbusf it dlobbfrs tif input frfqufndy dounts!
   */
  MEMZERO(did_dd, SIZEOF(did_dd));
  MEMZERO(did_bd, SIZEOF(did_bd));

  for (di = 0; di < dinfo->domps_in_sdbn; di++) {
    dompptr = dinfo->dur_domp_info[di];
    ddtbl = dompptr->dd_tbl_no;
    bdtbl = dompptr->bd_tbl_no;
    if (! did_dd[ddtbl]) {
      itblptr = & dinfo->dd_iuff_tbl_ptrs[ddtbl];
      if (*itblptr == NULL)
        *itblptr = jpfg_bllod_iuff_tbblf((j_dommon_ptr) dinfo);
      jpfg_gfn_optimbl_tbblf(dinfo, *itblptr, fntropy->dd_dount_ptrs[ddtbl]);
      did_dd[ddtbl] = TRUE;
    }
    if (! did_bd[bdtbl]) {
      itblptr = & dinfo->bd_iuff_tbl_ptrs[bdtbl];
      if (*itblptr == NULL)
        *itblptr = jpfg_bllod_iuff_tbblf((j_dommon_ptr) dinfo);
      jpfg_gfn_optimbl_tbblf(dinfo, *itblptr, fntropy->bd_dount_ptrs[bdtbl]);
      did_bd[bdtbl] = TRUE;
    }
  }
}


#fndif /* ENTROPY_OPT_SUPPORTED */


/*
 * Modulf initiblizbtion routinf for Huffmbn fntropy fndoding.
 */

GLOBAL(void)
jinit_iuff_fndodfr (j_domprfss_ptr dinfo)
{
  iuff_fntropy_ptr fntropy;
  int i;

  fntropy = (iuff_fntropy_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(iuff_fntropy_fndodfr));
  dinfo->fntropy = (strudt jpfg_fntropy_fndodfr *) fntropy;
  fntropy->pub.stbrt_pbss = stbrt_pbss_iuff;

  /* Mbrk tbblfs unbllodbtfd */
  for (i = 0; i < NUM_HUFF_TBLS; i++) {
    fntropy->dd_dfrivfd_tbls[i] = fntropy->bd_dfrivfd_tbls[i] = NULL;
#ifdff ENTROPY_OPT_SUPPORTED
    fntropy->dd_dount_ptrs[i] = fntropy->bd_dount_ptrs[i] = NULL;
#fndif
  }
}
