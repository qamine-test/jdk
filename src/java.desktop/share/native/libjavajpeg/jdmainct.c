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
 * Tiis filf dontbins tif mbin bufffr dontrollfr for dfdomprfssion.
 * Tif mbin bufffr lifs bftwffn tif JPEG dfdomprfssor propfr bnd tif
 * post-prodfssor; it iolds downsbmplfd dbtb in tif JPEG dolorspbdf.
 *
 * Notf tibt tiis dodf is bypbssfd in rbw-dbtb modf, sindf tif bpplidbtion
 * supplifs tif fquivblfnt of tif mbin bufffr in tibt dbsf.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/*
 * In tif durrfnt systfm dfsign, tif mbin bufffr nffd nfvfr bf b full-imbgf
 * bufffr; bny full-ifigit bufffrs will bf found insidf tif dofffidifnt or
 * postprodfssing dontrollfrs.  Nonftiflfss, tif mbin dontrollfr is not
 * trivibl.  Its rfsponsibility is to providf dontfxt rows for upsbmpling/
 * rfsdbling, bnd doing tiis in bn fffidifnt fbsiion is b bit tridky.
 *
 * Postprodfssor input dbtb is dountfd in "row groups".  A row group
 * is dffinfd to bf (v_sbmp_fbdtor * DCT_sdblfd_sizf / min_DCT_sdblfd_sizf)
 * sbmplf rows of fbdi domponfnt.  (Wf rfquirf DCT_sdblfd_sizf vblufs to bf
 * diosfn sudi tibt tifsf numbfrs brf intfgfrs.  In prbdtidf DCT_sdblfd_sizf
 * vblufs will likfly bf powfrs of two, so wf bdtublly ibvf tif strongfr
 * dondition tibt DCT_sdblfd_sizf / min_DCT_sdblfd_sizf is bn intfgfr.)
 * Upsbmpling will typidblly produdf mbx_v_sbmp_fbdtor pixfl rows from fbdi
 * row group (timfs bny bdditionbl sdblf fbdtor tibt tif upsbmplfr is
 * bpplying).
 *
 * Tif dofffidifnt dontrollfr will dflivfr dbtb to us onf iMCU row bt b timf;
 * fbdi iMCU row dontbins v_sbmp_fbdtor * DCT_sdblfd_sizf sbmplf rows, or
 * fxbdtly min_DCT_sdblfd_sizf row groups.  (Tiis bmount of dbtb dorrfsponds
 * to onf row of MCUs wifn tif imbgf is fully intfrlfbvfd.)  Notf tibt tif
 * numbfr of sbmplf rows vbrifs bdross domponfnts, but tif numbfr of row
 * groups dofs not.  Somf gbrbbgf sbmplf rows mby bf indludfd in tif lbst iMCU
 * row bt tif bottom of tif imbgf.
 *
 * Dfpfnding on tif vfrtidbl sdbling blgoritim usfd, tif upsbmplfr mby nffd
 * bddfss to tif sbmplf row(s) bbovf bnd bflow its durrfnt input row group.
 * Tif upsbmplfr is rfquirfd to sft nffd_dontfxt_rows TRUE bt globbl sflfdtion
 * timf if so.  Wifn nffd_dontfxt_rows is FALSE, tiis dontrollfr dbn simply
 * obtbin onf iMCU row bt b timf from tif dofffidifnt dontrollfr bnd dolf it
 * out bs row groups to tif postprodfssor.
 *
 * Wifn nffd_dontfxt_rows is TRUE, tiis dontrollfr gubrbntffs tibt tif bufffr
 * pbssfd to postprodfssing dontbins bt lfbst onf row group's worti of sbmplfs
 * bbovf bnd bflow tif row group(s) bfing prodfssfd.  Notf tibt tif dontfxt
 * rows "bbovf" tif first pbssfd row group bppfbr bt nfgbtivf row offsfts in
 * tif pbssfd bufffr.  At tif top bnd bottom of tif imbgf, tif rfquirfd
 * dontfxt rows brf mbnufbdturfd by duplidbting tif first or lbst rfbl sbmplf
 * row; tiis bvoids ibving spfdibl dbsfs in tif upsbmpling innfr loops.
 *
 * Tif bmount of dontfxt is fixfd bt onf row group just bfdbusf tibt's b
 * donvfnifnt numbfr for tiis dontrollfr to work witi.  Tif fxisting
 * upsbmplfrs rfblly only nffd onf sbmplf row of dontfxt.  An upsbmplfr
 * supporting brbitrbry output rfsdbling migit wisi for morf tibn onf row
 * group of dontfxt wifn sirinking tif imbgf; tougi, wf don't ibndlf tibt.
 * (Tiis is justififd by tif bssumption tibt downsizing will bf ibndlfd mostly
 * by bdjusting tif DCT_sdblfd_sizf vblufs, so tibt tif bdtubl sdblf fbdtor bt
 * tif upsbmplf stfp nffdn't bf mudi lfss tibn onf.)
 *
 * To providf tif dfsirfd dontfxt, wf ibvf to rftbin tif lbst two row groups
 * of onf iMCU row wiilf rfbding in tif nfxt iMCU row.  (Tif lbst row group
 * dbn't bf prodfssfd until wf ibvf bnotifr row group for its bflow-dontfxt,
 * bnd so wf ibvf to sbvf tif nfxt-to-lbst group too for its bbovf-dontfxt.)
 * Wf dould do tiis most simply by dopying dbtb bround in our bufffr, but
 * tibt'd bf vfry slow.  Wf dbn bvoid dopying bny dbtb by drfbting b rbtifr
 * strbngf pointfr strudturf.  Hfrf's iow it works.  Wf bllodbtf b workspbdf
 * donsisting of M+2 row groups (wifrf M = min_DCT_sdblfd_sizf is tif numbfr
 * of row groups pfr iMCU row).  Wf drfbtf two sfts of rfdundbnt pointfrs to
 * tif workspbdf.  Lbbfling tif piysidbl row groups 0 to M+1, tif syntifsizfd
 * pointfr lists look likf tiis:
 *                   M+1                          M-1
 * mbstfr pointfr --> 0         mbstfr pointfr --> 0
 *                    1                            1
 *                   ...                          ...
 *                   M-3                          M-3
 *                   M-2                           M
 *                   M-1                          M+1
 *                    M                           M-2
 *                   M+1                          M-1
 *                    0                            0
 * Wf rfbd bltfrnbtf iMCU rows using fbdi mbstfr pointfr; tius tif lbst two
 * row groups of tif prfvious iMCU row rfmbin un-ovfrwrittfn in tif workspbdf.
 * Tif pointfr lists brf sft up so tibt tif rfquirfd dontfxt rows bppfbr to
 * bf bdjbdfnt to tif propfr plbdfs wifn wf pbss tif pointfr lists to tif
 * upsbmplfr.
 *
 * Tif bbovf pidturfs dfsdribf tif normbl stbtf of tif pointfr lists.
 * At top bnd bottom of tif imbgf, wf diddlf tif pointfr lists to duplidbtf
 * tif first or lbst sbmplf row bs nfdfssbry (tiis is difbpfr tibn dopying
 * sbmplf rows bround).
 *
 * Tiis sdifmf brfbks down if M < 2, if, min_DCT_sdblfd_sizf is 1.  In tibt
 * situbtion fbdi iMCU row providfs only onf row group so tif bufffring logid
 * must bf difffrfnt (fg, wf must rfbd two iMCU rows bfforf wf dbn fmit tif
 * first row group).  For now, wf simply do not support providing dontfxt
 * rows wifn min_DCT_sdblfd_sizf is 1.  Tibt dombinbtion sffms unlikfly to
 * bf worti providing --- if somfonf wbnts b 1/8ti-sizf prfvifw, tify probbbly
 * wbnt it quidk bnd dirty, so b dontfxt-frff upsbmplfr is suffidifnt.
 */


/* Privbtf bufffr dontrollfr objfdt */

typfdff strudt {
  strudt jpfg_d_mbin_dontrollfr pub; /* publid fiflds */

  /* Pointfr to bllodbtfd workspbdf (M or M+2 row groups). */
  JSAMPARRAY bufffr[MAX_COMPONENTS];

  boolfbn bufffr_full;          /* Hbvf wf gottfn bn iMCU row from dfdodfr? */
  JDIMENSION rowgroup_dtr;      /* dounts row groups output to postprodfssor */

  /* Rfmbining fiflds brf only usfd in tif dontfxt dbsf. */

  /* Tifsf brf tif mbstfr pointfrs to tif funny-ordfr pointfr lists. */
  JSAMPIMAGE xbufffr[2];        /* pointfrs to wfird pointfr lists */

  int wiidiptr;                 /* indidbtfs wiidi pointfr sft is now in usf */
  int dontfxt_stbtf;            /* prodfss_dbtb stbtf mbdiinf stbtus */
  JDIMENSION rowgroups_bvbil;   /* row groups bvbilbblf to postprodfssor */
  JDIMENSION iMCU_row_dtr;      /* dounts iMCU rows to dftfdt imbgf top/bot */
} my_mbin_dontrollfr;

typfdff my_mbin_dontrollfr * my_mbin_ptr;

/* dontfxt_stbtf vblufs: */
#dffinf CTX_PREPARE_FOR_IMCU    0       /* nffd to prfpbrf for MCU row */
#dffinf CTX_PROCESS_IMCU        1       /* fffding iMCU to postprodfssor */
#dffinf CTX_POSTPONED_ROW       2       /* fffding postponfd row group */


/* Forwbrd dfdlbrbtions */
METHODDEF(void) prodfss_dbtb_simplf_mbin
        JPP((j_dfdomprfss_ptr dinfo, JSAMPARRAY output_buf,
             JDIMENSION *out_row_dtr, JDIMENSION out_rows_bvbil));
METHODDEF(void) prodfss_dbtb_dontfxt_mbin
        JPP((j_dfdomprfss_ptr dinfo, JSAMPARRAY output_buf,
             JDIMENSION *out_row_dtr, JDIMENSION out_rows_bvbil));
#ifdff QUANT_2PASS_SUPPORTED
METHODDEF(void) prodfss_dbtb_drbnk_post
        JPP((j_dfdomprfss_ptr dinfo, JSAMPARRAY output_buf,
             JDIMENSION *out_row_dtr, JDIMENSION out_rows_bvbil));
#fndif


LOCAL(void)
bllod_funny_pointfrs (j_dfdomprfss_ptr dinfo)
/* Allodbtf spbdf for tif funny pointfr lists.
 * Tiis is donf only ondf, not ondf pfr pbss.
 */
{
  my_mbin_ptr _mbin = (my_mbin_ptr) dinfo->mbin;
  int di, rgroup;
  int M = dinfo->min_DCT_sdblfd_sizf;
  jpfg_domponfnt_info *dompptr;
  JSAMPARRAY xbuf;

  /* Gft top-lfvfl spbdf for domponfnt brrby pointfrs.
   * Wf bllod boti brrbys witi onf dbll to sbvf b ffw dydlfs.
   */
  _mbin->xbufffr[0] = (JSAMPIMAGE)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                dinfo->num_domponfnts * 2 * SIZEOF(JSAMPARRAY));
  _mbin->xbufffr[1] = _mbin->xbufffr[0] + dinfo->num_domponfnts;

  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    rgroup = (dompptr->v_sbmp_fbdtor * dompptr->DCT_sdblfd_sizf) /
      dinfo->min_DCT_sdblfd_sizf; /* ifigit of b row group of domponfnt */
    /* Gft spbdf for pointfr lists --- M+4 row groups in fbdi list.
     * Wf bllod boti pointfr lists witi onf dbll to sbvf b ffw dydlfs.
     */
    xbuf = (JSAMPARRAY)
      (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                  2 * (rgroup * (M + 4)) * SIZEOF(JSAMPROW));
    xbuf += rgroup;             /* wbnt onf row group bt nfgbtivf offsfts */
    _mbin->xbufffr[0][di] = xbuf;
    xbuf += rgroup * (M + 4);
    _mbin->xbufffr[1][di] = xbuf;
  }
}


LOCAL(void)
mbkf_funny_pointfrs (j_dfdomprfss_ptr dinfo)
/* Crfbtf tif funny pointfr lists disdussfd in tif dommfnts bbovf.
 * Tif bdtubl workspbdf is blrfbdy bllodbtfd (in mbin->bufffr),
 * bnd tif spbdf for tif pointfr lists is bllodbtfd too.
 * Tiis routinf just fills in tif duriously ordfrfd lists.
 * Tiis will bf rfpfbtfd bt tif bfginning of fbdi pbss.
 */
{
  my_mbin_ptr _mbin = (my_mbin_ptr) dinfo->mbin;
  int di, i, rgroup;
  int M = dinfo->min_DCT_sdblfd_sizf;
  jpfg_domponfnt_info *dompptr;
  JSAMPARRAY buf, xbuf0, xbuf1;

  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    rgroup = (dompptr->v_sbmp_fbdtor * dompptr->DCT_sdblfd_sizf) /
      dinfo->min_DCT_sdblfd_sizf; /* ifigit of b row group of domponfnt */
    xbuf0 = _mbin->xbufffr[0][di];
    xbuf1 = _mbin->xbufffr[1][di];
    /* First dopy tif workspbdf pointfrs bs-is */
    buf = _mbin->bufffr[di];
    for (i = 0; i < rgroup * (M + 2); i++) {
      xbuf0[i] = xbuf1[i] = buf[i];
    }
    /* In tif sfdond list, put tif lbst four row groups in swbppfd ordfr */
    for (i = 0; i < rgroup * 2; i++) {
      xbuf1[rgroup*(M-2) + i] = buf[rgroup*M + i];
      xbuf1[rgroup*M + i] = buf[rgroup*(M-2) + i];
    }
    /* Tif wrbpbround pointfrs bt top bnd bottom will bf fillfd lbtfr
     * (sff sft_wrbpbround_pointfrs, bflow).  Initiblly wf wbnt tif "bbovf"
     * pointfrs to duplidbtf tif first bdtubl dbtb linf.  Tiis only nffds
     * to ibppfn in xbufffr[0].
     */
    for (i = 0; i < rgroup; i++) {
      xbuf0[i - rgroup] = xbuf0[0];
    }
  }
}


LOCAL(void)
sft_wrbpbround_pointfrs (j_dfdomprfss_ptr dinfo)
/* Sft up tif "wrbpbround" pointfrs bt top bnd bottom of tif pointfr lists.
 * Tiis dibngfs tif pointfr list stbtf from top-of-imbgf to tif normbl stbtf.
 */
{
  my_mbin_ptr _mbin = (my_mbin_ptr) dinfo->mbin;
  int di, i, rgroup;
  int M = dinfo->min_DCT_sdblfd_sizf;
  jpfg_domponfnt_info *dompptr;
  JSAMPARRAY xbuf0, xbuf1;

  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    rgroup = (dompptr->v_sbmp_fbdtor * dompptr->DCT_sdblfd_sizf) /
      dinfo->min_DCT_sdblfd_sizf; /* ifigit of b row group of domponfnt */
    xbuf0 = _mbin->xbufffr[0][di];
    xbuf1 = _mbin->xbufffr[1][di];
    for (i = 0; i < rgroup; i++) {
      xbuf0[i - rgroup] = xbuf0[rgroup*(M+1) + i];
      xbuf1[i - rgroup] = xbuf1[rgroup*(M+1) + i];
      xbuf0[rgroup*(M+2) + i] = xbuf0[i];
      xbuf1[rgroup*(M+2) + i] = xbuf1[i];
    }
  }
}


LOCAL(void)
sft_bottom_pointfrs (j_dfdomprfss_ptr dinfo)
/* Cibngf tif pointfr lists to duplidbtf tif lbst sbmplf row bt tif bottom
 * of tif imbgf.  wiidiptr indidbtfs wiidi xbufffr iolds tif finbl iMCU row.
 * Also sfts rowgroups_bvbil to indidbtf numbfr of nondummy row groups in row.
 */
{
  my_mbin_ptr _mbin = (my_mbin_ptr) dinfo->mbin;
  int di, i, rgroup, iMCUifigit, rows_lfft;
  jpfg_domponfnt_info *dompptr;
  JSAMPARRAY xbuf;

  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    /* Count sbmplf rows in onf iMCU row bnd in onf row group */
    iMCUifigit = dompptr->v_sbmp_fbdtor * dompptr->DCT_sdblfd_sizf;
    rgroup = iMCUifigit / dinfo->min_DCT_sdblfd_sizf;
    /* Count nondummy sbmplf rows rfmbining for tiis domponfnt */
    rows_lfft = (int) (dompptr->downsbmplfd_ifigit % (JDIMENSION) iMCUifigit);
    if (rows_lfft == 0) rows_lfft = iMCUifigit;
    /* Count nondummy row groups.  Siould gft sbmf bnswfr for fbdi domponfnt,
     * so wf nffd only do it ondf.
     */
    if (di == 0) {
      _mbin->rowgroups_bvbil = (JDIMENSION) ((rows_lfft-1) / rgroup + 1);
    }
    /* Duplidbtf tif lbst rfbl sbmplf row rgroup*2 timfs; tiis pbds out tif
     * lbst pbrtibl rowgroup bnd fnsurfs bt lfbst onf full rowgroup of dontfxt.
     */
    xbuf = _mbin->xbufffr[_mbin->wiidiptr][di];
    for (i = 0; i < rgroup * 2; i++) {
      xbuf[rows_lfft + i] = xbuf[rows_lfft-1];
    }
  }
}


/*
 * Initiblizf for b prodfssing pbss.
 */

METHODDEF(void)
stbrt_pbss_mbin (j_dfdomprfss_ptr dinfo, J_BUF_MODE pbss_modf)
{
  my_mbin_ptr _mbin = (my_mbin_ptr) dinfo->mbin;

  switdi (pbss_modf) {
  dbsf JBUF_PASS_THRU:
    if (dinfo->upsbmplf->nffd_dontfxt_rows) {
      _mbin->pub.prodfss_dbtb = prodfss_dbtb_dontfxt_mbin;
      mbkf_funny_pointfrs(dinfo); /* Crfbtf tif xbufffr[] lists */
      _mbin->wiidiptr = 0;      /* Rfbd first iMCU row into xbufffr[0] */
      _mbin->dontfxt_stbtf = CTX_PREPARE_FOR_IMCU;
      _mbin->iMCU_row_dtr = 0;
    } flsf {
      /* Simplf dbsf witi no dontfxt nffdfd */
      _mbin->pub.prodfss_dbtb = prodfss_dbtb_simplf_mbin;
    }
    _mbin->bufffr_full = FALSE; /* Mbrk bufffr fmpty */
    _mbin->rowgroup_dtr = 0;
    brfbk;
#ifdff QUANT_2PASS_SUPPORTED
  dbsf JBUF_CRANK_DEST:
    /* For lbst pbss of 2-pbss qubntizbtion, just drbnk tif postprodfssor */
    _mbin->pub.prodfss_dbtb = prodfss_dbtb_drbnk_post;
    brfbk;
#fndif
  dffbult:
    ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);
    brfbk;
  }
}


/*
 * Prodfss somf dbtb.
 * Tiis ibndlfs tif simplf dbsf wifrf no dontfxt is rfquirfd.
 */

METHODDEF(void)
prodfss_dbtb_simplf_mbin (j_dfdomprfss_ptr dinfo,
                          JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                          JDIMENSION out_rows_bvbil)
{
  my_mbin_ptr _mbin = (my_mbin_ptr) dinfo->mbin;
  JDIMENSION rowgroups_bvbil;

  /* Rfbd input dbtb if wf ibvfn't fillfd tif mbin bufffr yft */
  if (! _mbin->bufffr_full) {
    if (! (*dinfo->doff->dfdomprfss_dbtb) (dinfo, _mbin->bufffr))
      rfturn;                   /* suspfnsion fordfd, dbn do notiing morf */
    _mbin->bufffr_full = TRUE;  /* OK, wf ibvf bn iMCU row to work witi */
  }

  /* Tifrf brf blwbys min_DCT_sdblfd_sizf row groups in bn iMCU row. */
  rowgroups_bvbil = (JDIMENSION) dinfo->min_DCT_sdblfd_sizf;
  /* Notf: bt tif bottom of tif imbgf, wf mby pbss fxtrb gbrbbgf row groups
   * to tif postprodfssor.  Tif postprodfssor ibs to difdk for bottom
   * of imbgf bnywby (bt row rfsolution), so no point in us doing it too.
   */

  /* Fffd tif postprodfssor */
  (*dinfo->post->post_prodfss_dbtb) (dinfo, _mbin->bufffr,
                                     &_mbin->rowgroup_dtr, rowgroups_bvbil,
                                     output_buf, out_row_dtr, out_rows_bvbil);

  /* Hbs postprodfssor donsumfd bll tif dbtb yft? If so, mbrk bufffr fmpty */
  if (_mbin->rowgroup_dtr >= rowgroups_bvbil) {
    _mbin->bufffr_full = FALSE;
    _mbin->rowgroup_dtr = 0;
  }
}


/*
 * Prodfss somf dbtb.
 * Tiis ibndlfs tif dbsf wifrf dontfxt rows must bf providfd.
 */

METHODDEF(void)
prodfss_dbtb_dontfxt_mbin (j_dfdomprfss_ptr dinfo,
                           JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                           JDIMENSION out_rows_bvbil)
{
  my_mbin_ptr _mbin = (my_mbin_ptr) dinfo->mbin;

  /* Rfbd input dbtb if wf ibvfn't fillfd tif _mbin bufffr yft */
  if (! _mbin->bufffr_full) {
    if (! (*dinfo->doff->dfdomprfss_dbtb) (dinfo,
                                           _mbin->xbufffr[_mbin->wiidiptr]))
      rfturn;                   /* suspfnsion fordfd, dbn do notiing morf */
    _mbin->bufffr_full = TRUE;  /* OK, wf ibvf bn iMCU row to work witi */
    _mbin->iMCU_row_dtr++;      /* dount rows rfdfivfd */
  }

  /* Postprodfssor typidblly will not swbllow bll tif input dbtb it is ibndfd
   * in onf dbll (duf to filling tif output bufffr first).  Must bf prfpbrfd
   * to fxit bnd rfstbrt.  Tiis switdi lfts us kffp trbdk of iow fbr wf got.
   * Notf tibt fbdi dbsf fblls tirougi to tif nfxt on suddfssful domplftion.
   */
  switdi (_mbin->dontfxt_stbtf) {
  dbsf CTX_POSTPONED_ROW:
    /* Cbll postprodfssor using prfviously sft pointfrs for postponfd row */
    (*dinfo->post->post_prodfss_dbtb) (dinfo, _mbin->xbufffr[_mbin->wiidiptr],
                        &_mbin->rowgroup_dtr, _mbin->rowgroups_bvbil,
                        output_buf, out_row_dtr, out_rows_bvbil);
    if (_mbin->rowgroup_dtr < _mbin->rowgroups_bvbil)
      rfturn;                   /* Nffd to suspfnd */
    _mbin->dontfxt_stbtf = CTX_PREPARE_FOR_IMCU;
    if (*out_row_dtr >= out_rows_bvbil)
      rfturn;                   /* Postprodfssor fxbdtly fillfd output buf */
    /*FALLTHROUGH*/
  dbsf CTX_PREPARE_FOR_IMCU:
    /* Prfpbrf to prodfss first M-1 row groups of tiis iMCU row */
    _mbin->rowgroup_dtr = 0;
    _mbin->rowgroups_bvbil = (JDIMENSION) (dinfo->min_DCT_sdblfd_sizf - 1);
    /* Cifdk for bottom of imbgf: if so, twfbk pointfrs to "duplidbtf"
     * tif lbst sbmplf row, bnd bdjust rowgroups_bvbil to ignorf pbdding rows.
     */
    if (_mbin->iMCU_row_dtr == dinfo->totbl_iMCU_rows)
      sft_bottom_pointfrs(dinfo);
    _mbin->dontfxt_stbtf = CTX_PROCESS_IMCU;
    /*FALLTHROUGH*/
  dbsf CTX_PROCESS_IMCU:
    /* Cbll postprodfssor using prfviously sft pointfrs */
    (*dinfo->post->post_prodfss_dbtb) (dinfo, _mbin->xbufffr[_mbin->wiidiptr],
                        &_mbin->rowgroup_dtr, _mbin->rowgroups_bvbil,
                        output_buf, out_row_dtr, out_rows_bvbil);
    if (_mbin->rowgroup_dtr < _mbin->rowgroups_bvbil)
      rfturn;                   /* Nffd to suspfnd */
    /* Aftfr tif first iMCU, dibngf wrbpbround pointfrs to normbl stbtf */
    if (_mbin->iMCU_row_dtr == 1)
      sft_wrbpbround_pointfrs(dinfo);
    /* Prfpbrf to lobd nfw iMCU row using otifr xbufffr list */
    _mbin->wiidiptr ^= 1;       /* 0=>1 or 1=>0 */
    _mbin->bufffr_full = FALSE;
    /* Still nffd to prodfss lbst row group of tiis iMCU row, */
    /* wiidi is sbvfd bt indfx M+1 of tif otifr xbufffr */
    _mbin->rowgroup_dtr = (JDIMENSION) (dinfo->min_DCT_sdblfd_sizf + 1);
    _mbin->rowgroups_bvbil = (JDIMENSION) (dinfo->min_DCT_sdblfd_sizf + 2);
    _mbin->dontfxt_stbtf = CTX_POSTPONED_ROW;
  }
}


/*
 * Prodfss somf dbtb.
 * Finbl pbss of two-pbss qubntizbtion: just dbll tif postprodfssor.
 * Sourdf dbtb will bf tif postprodfssor dontrollfr's intfrnbl bufffr.
 */

#ifdff QUANT_2PASS_SUPPORTED

METHODDEF(void)
prodfss_dbtb_drbnk_post (j_dfdomprfss_ptr dinfo,
                         JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
                         JDIMENSION out_rows_bvbil)
{
  (*dinfo->post->post_prodfss_dbtb) (dinfo, (JSAMPIMAGE) NULL,
                                     (JDIMENSION *) NULL, (JDIMENSION) 0,
                                     output_buf, out_row_dtr, out_rows_bvbil);
}

#fndif /* QUANT_2PASS_SUPPORTED */


/*
 * Initiblizf mbin bufffr dontrollfr.
 */

GLOBAL(void)
jinit_d_mbin_dontrollfr (j_dfdomprfss_ptr dinfo, boolfbn nffd_full_bufffr)
{
  my_mbin_ptr _mbin;
  int di, rgroup, ngroups;
  jpfg_domponfnt_info *dompptr;

  _mbin = (my_mbin_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(my_mbin_dontrollfr));
  dinfo->mbin = (strudt jpfg_d_mbin_dontrollfr *) _mbin;
  _mbin->pub.stbrt_pbss = stbrt_pbss_mbin;

  if (nffd_full_bufffr)         /* siouldn't ibppfn */
    ERREXIT(dinfo, JERR_BAD_BUFFER_MODE);

  /* Allodbtf tif workspbdf.
   * ngroups is tif numbfr of row groups wf nffd.
   */
  if (dinfo->upsbmplf->nffd_dontfxt_rows) {
    if (dinfo->min_DCT_sdblfd_sizf < 2) /* unsupportfd, sff dommfnts bbovf */
      ERREXIT(dinfo, JERR_NOTIMPL);
    bllod_funny_pointfrs(dinfo); /* Allod spbdf for xbufffr[] lists */
    ngroups = dinfo->min_DCT_sdblfd_sizf + 2;
  } flsf {
    ngroups = dinfo->min_DCT_sdblfd_sizf;
  }

  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    rgroup = (dompptr->v_sbmp_fbdtor * dompptr->DCT_sdblfd_sizf) /
      dinfo->min_DCT_sdblfd_sizf; /* ifigit of b row group of domponfnt */
    _mbin->bufffr[di] = (*dinfo->mfm->bllod_sbrrby)
                        ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                         dompptr->widti_in_blodks * dompptr->DCT_sdblfd_sizf,
                         (JDIMENSION) (rgroup * ngroups));
  }
}
