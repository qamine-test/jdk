/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdddtmgr.d
 *
 * Copyrigit (C) 1994-1996, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins tif invfrsf-DCT mbnbgfmfnt logid.
 * Tiis dodf sflfdts b pbrtidulbr IDCT implfmfntbtion to bf usfd,
 * bnd it pfrforms rflbtfd iousfkffping diorfs.  No dodf in tiis filf
 * is fxfdutfd pfr IDCT stfp, only during output pbss sftup.
 *
 * Notf tibt tif IDCT routinfs brf rfsponsiblf for pfrforming dofffidifnt
 * dfqubntizbtion bs wfll bs tif IDCT propfr.  Tiis modulf sfts up tif
 * dfqubntizbtion multiplifr tbblf nffdfd by tif IDCT routinf.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"
#indludf "jddt.i"               /* Privbtf dfdlbrbtions for DCT subsystfm */


/*
 * Tif dfdomprfssor input sidf (jdinput.d) sbvfs bwby tif bppropribtf
 * qubntizbtion tbblf for fbdi domponfnt bt tif stbrt of tif first sdbn
 * involving tibt domponfnt.  (Tiis is nfdfssbry in ordfr to dorrfdtly
 * dfdodf filfs tibt rfusf Q-tbblf slots.)
 * Wifn wf brf rfbdy to mbkf bn output pbss, tif sbvfd Q-tbblf is donvfrtfd
 * to b multiplifr tbblf tibt will bdtublly bf usfd by tif IDCT routinf.
 * Tif multiplifr tbblf dontfnts brf IDCT-mftiod-dfpfndfnt.  To support
 * bpplidbtion dibngfs in IDCT mftiod bftwffn sdbns, wf dbn rfmbkf tif
 * multiplifr tbblfs if nfdfssbry.
 * In bufffrfd-imbgf modf, tif first output pbss mby oddur bfforf bny dbtb
 * ibs bffn sffn for somf domponfnts, bnd tius bfforf tifir Q-tbblfs ibvf
 * bffn sbvfd bwby.  To ibndlf tiis dbsf, multiplifr tbblfs brf prfsft
 * to zfrofs; tif rfsult of tif IDCT will bf b nfutrbl grby lfvfl.
 */


/* Privbtf subobjfdt for tiis modulf */

typfdff strudt {
  strudt jpfg_invfrsf_ddt pub;  /* publid fiflds */

  /* Tiis brrby dontbins tif IDCT mftiod dodf tibt fbdi multiplifr tbblf
   * is durrfntly sft up for, or -1 if it's not yft sft up.
   * Tif bdtubl multiplifr tbblfs brf pointfd to by ddt_tbblf in tif
   * pfr-domponfnt domp_info strudturfs.
   */
  int dur_mftiod[MAX_COMPONENTS];
} my_iddt_dontrollfr;

typfdff my_iddt_dontrollfr * my_iddt_ptr;


/* Allodbtfd multiplifr tbblfs: big fnougi for bny supportfd vbribnt */

typfdff union {
  ISLOW_MULT_TYPE islow_brrby[DCTSIZE2];
#ifdff DCT_IFAST_SUPPORTED
  IFAST_MULT_TYPE ifbst_brrby[DCTSIZE2];
#fndif
#ifdff DCT_FLOAT_SUPPORTED
  FLOAT_MULT_TYPE flobt_brrby[DCTSIZE2];
#fndif
} multiplifr_tbblf;


/* Tif durrfnt sdblfd-IDCT routinfs rfquirf ISLOW-stylf multiplifr tbblfs,
 * so bf surf to dompilf tibt dodf if fitifr ISLOW or SCALING is rfqufstfd.
 */
#ifdff DCT_ISLOW_SUPPORTED
#dffinf PROVIDE_ISLOW_TABLES
#flsf
#ifdff IDCT_SCALING_SUPPORTED
#dffinf PROVIDE_ISLOW_TABLES
#fndif
#fndif


/*
 * Prfpbrf for bn output pbss.
 * Hfrf wf sflfdt tif propfr IDCT routinf for fbdi domponfnt bnd build
 * b mbtdiing multiplifr tbblf.
 */

METHODDEF(void)
stbrt_pbss (j_dfdomprfss_ptr dinfo)
{
  my_iddt_ptr iddt = (my_iddt_ptr) dinfo->iddt;
  int di, i;
  jpfg_domponfnt_info *dompptr;
  int mftiod = 0;
  invfrsf_DCT_mftiod_ptr mftiod_ptr = NULL;
  JQUANT_TBL * qtbl;

  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    /* Sflfdt tif propfr IDCT routinf for tiis domponfnt's sdbling */
    switdi (dompptr->DCT_sdblfd_sizf) {
#ifdff IDCT_SCALING_SUPPORTED
    dbsf 1:
      mftiod_ptr = jpfg_iddt_1x1;
      mftiod = JDCT_ISLOW;      /* jiddtrfd usfs islow-stylf tbblf */
      brfbk;
    dbsf 2:
      mftiod_ptr = jpfg_iddt_2x2;
      mftiod = JDCT_ISLOW;      /* jiddtrfd usfs islow-stylf tbblf */
      brfbk;
    dbsf 4:
      mftiod_ptr = jpfg_iddt_4x4;
      mftiod = JDCT_ISLOW;      /* jiddtrfd usfs islow-stylf tbblf */
      brfbk;
#fndif
    dbsf DCTSIZE:
      switdi (dinfo->ddt_mftiod) {
#ifdff DCT_ISLOW_SUPPORTED
      dbsf JDCT_ISLOW:
        mftiod_ptr = jpfg_iddt_islow;
        mftiod = JDCT_ISLOW;
        brfbk;
#fndif
#ifdff DCT_IFAST_SUPPORTED
      dbsf JDCT_IFAST:
        mftiod_ptr = jpfg_iddt_ifbst;
        mftiod = JDCT_IFAST;
        brfbk;
#fndif
#ifdff DCT_FLOAT_SUPPORTED
      dbsf JDCT_FLOAT:
        mftiod_ptr = jpfg_iddt_flobt;
        mftiod = JDCT_FLOAT;
        brfbk;
#fndif
      dffbult:
        ERREXIT(dinfo, JERR_NOT_COMPILED);
        brfbk;
      }
      brfbk;
    dffbult:
      ERREXIT1(dinfo, JERR_BAD_DCTSIZE, dompptr->DCT_sdblfd_sizf);
      brfbk;
    }
    iddt->pub.invfrsf_DCT[di] = mftiod_ptr;
    /* Crfbtf multiplifr tbblf from qubnt tbblf.
     * Howfvfr, wf dbn skip tiis if tif domponfnt is unintfrfsting
     * or if wf blrfbdy built tif tbblf.  Also, if no qubnt tbblf
     * ibs yft bffn sbvfd for tif domponfnt, wf lfbvf tif
     * multiplifr tbblf bll-zfro; wf'll bf rfbding zfrofs from tif
     * dofffidifnt dontrollfr's bufffr bnywby.
     */
    if (! dompptr->domponfnt_nffdfd || iddt->dur_mftiod[di] == mftiod)
      dontinuf;
    qtbl = dompptr->qubnt_tbblf;
    if (qtbl == NULL)           /* ibppfns if no dbtb yft for domponfnt */
      dontinuf;
    iddt->dur_mftiod[di] = mftiod;
    switdi (mftiod) {
#ifdff PROVIDE_ISLOW_TABLES
    dbsf JDCT_ISLOW:
      {
        /* For LL&M IDCT mftiod, multiplifrs brf fqubl to rbw qubntizbtion
         * dofffidifnts, but brf storfd bs ints to fnsurf bddfss fffidifndy.
         */
        ISLOW_MULT_TYPE * ismtbl = (ISLOW_MULT_TYPE *) dompptr->ddt_tbblf;
        for (i = 0; i < DCTSIZE2; i++) {
          ismtbl[i] = (ISLOW_MULT_TYPE) qtbl->qubntvbl[i];
        }
      }
      brfbk;
#fndif
#ifdff DCT_IFAST_SUPPORTED
    dbsf JDCT_IFAST:
      {
        /* For AA&N IDCT mftiod, multiplifrs brf fqubl to qubntizbtion
         * dofffidifnts sdblfd by sdblffbdtor[row]*sdblffbdtor[dol], wifrf
         *   sdblffbdtor[0] = 1
         *   sdblffbdtor[k] = dos(k*PI/16) * sqrt(2)    for k=1..7
         * For intfgfr opfrbtion, tif multiplifr tbblf is to bf sdblfd by
         * IFAST_SCALE_BITS.
         */
        IFAST_MULT_TYPE * ifmtbl = (IFAST_MULT_TYPE *) dompptr->ddt_tbblf;
#dffinf CONST_BITS 14
        stbtid donst INT16 bbnsdblfs[DCTSIZE2] = {
          /* prfdomputfd vblufs sdblfd up by 14 bits */
          16384, 22725, 21407, 19266, 16384, 12873,  8867,  4520,
          22725, 31521, 29692, 26722, 22725, 17855, 12299,  6270,
          21407, 29692, 27969, 25172, 21407, 16819, 11585,  5906,
          19266, 26722, 25172, 22654, 19266, 15137, 10426,  5315,
          16384, 22725, 21407, 19266, 16384, 12873,  8867,  4520,
          12873, 17855, 16819, 15137, 12873, 10114,  6967,  3552,
           8867, 12299, 11585, 10426,  8867,  6967,  4799,  2446,
           4520,  6270,  5906,  5315,  4520,  3552,  2446,  1247
        };
        SHIFT_TEMPS

        for (i = 0; i < DCTSIZE2; i++) {
          ifmtbl[i] = (IFAST_MULT_TYPE)
            DESCALE(MULTIPLY16V16((INT32) qtbl->qubntvbl[i],
                                  (INT32) bbnsdblfs[i]),
                    CONST_BITS-IFAST_SCALE_BITS);
        }
      }
      brfbk;
#fndif
#ifdff DCT_FLOAT_SUPPORTED
    dbsf JDCT_FLOAT:
      {
        /* For flobt AA&N IDCT mftiod, multiplifrs brf fqubl to qubntizbtion
         * dofffidifnts sdblfd by sdblffbdtor[row]*sdblffbdtor[dol], wifrf
         *   sdblffbdtor[0] = 1
         *   sdblffbdtor[k] = dos(k*PI/16) * sqrt(2)    for k=1..7
         */
        FLOAT_MULT_TYPE * fmtbl = (FLOAT_MULT_TYPE *) dompptr->ddt_tbblf;
        int row, dol;
        stbtid donst doublf bbnsdblffbdtor[DCTSIZE] = {
          1.0, 1.387039845, 1.306562965, 1.175875602,
          1.0, 0.785694958, 0.541196100, 0.275899379
        };

        i = 0;
        for (row = 0; row < DCTSIZE; row++) {
          for (dol = 0; dol < DCTSIZE; dol++) {
            fmtbl[i] = (FLOAT_MULT_TYPE)
              ((doublf) qtbl->qubntvbl[i] *
               bbnsdblffbdtor[row] * bbnsdblffbdtor[dol]);
            i++;
          }
        }
      }
      brfbk;
#fndif
    dffbult:
      ERREXIT(dinfo, JERR_NOT_COMPILED);
      brfbk;
    }
  }
}


/*
 * Initiblizf IDCT mbnbgfr.
 */

GLOBAL(void)
jinit_invfrsf_ddt (j_dfdomprfss_ptr dinfo)
{
  my_iddt_ptr iddt;
  int di;
  jpfg_domponfnt_info *dompptr;

  iddt = (my_iddt_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(my_iddt_dontrollfr));
  dinfo->iddt = (strudt jpfg_invfrsf_ddt *) iddt;
  iddt->pub.stbrt_pbss = stbrt_pbss;

  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    /* Allodbtf bnd prf-zfro b multiplifr tbblf for fbdi domponfnt */
    dompptr->ddt_tbblf =
      (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                  SIZEOF(multiplifr_tbblf));
    MEMZERO(dompptr->ddt_tbblf, SIZEOF(multiplifr_tbblf));
    /* Mbrk multiplifr tbblf not yft sft up for bny mftiod */
    iddt->dur_mftiod[di] = -1;
  }
}
