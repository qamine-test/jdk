/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdpbrbm.d
 *
 * Copyrigit (C) 1991-1998, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins optionbl dffbult-sftting dodf for tif JPEG domprfssor.
 * Applidbtions do not ibvf to usf tiis filf, but tiosf tibt don't usf it
 * must know b lot morf bbout tif innbrds of tif JPEG dodf.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/*
 * Qubntizbtion tbblf sftup routinfs
 */

GLOBAL(void)
jpfg_bdd_qubnt_tbblf (j_domprfss_ptr dinfo, int wiidi_tbl,
                      donst unsignfd int *bbsid_tbblf,
                      int sdblf_fbdtor, boolfbn fordf_bbsflinf)
/* Dffinf b qubntizbtion tbblf fqubl to tif bbsid_tbblf timfs
 * b sdblf fbdtor (givfn bs b pfrdfntbgf).
 * If fordf_bbsflinf is TRUE, tif domputfd qubntizbtion tbblf fntrifs
 * brf limitfd to 1..255 for JPEG bbsflinf dompbtibility.
 */
{
  JQUANT_TBL ** qtblptr;
  int i;
  long tfmp;

  /* Sbffty difdk to fnsurf stbrt_domprfss not dbllfd yft. */
  if (dinfo->globbl_stbtf != CSTATE_START)
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);

  if (wiidi_tbl < 0 || wiidi_tbl >= NUM_QUANT_TBLS)
    ERREXIT1(dinfo, JERR_DQT_INDEX, wiidi_tbl);

  qtblptr = & dinfo->qubnt_tbl_ptrs[wiidi_tbl];

  if (*qtblptr == NULL)
    *qtblptr = jpfg_bllod_qubnt_tbblf((j_dommon_ptr) dinfo);

  for (i = 0; i < DCTSIZE2; i++) {
    tfmp = ((long) bbsid_tbblf[i] * sdblf_fbdtor + 50L) / 100L;
    /* limit tif vblufs to tif vblid rbngf */
    if (tfmp <= 0L) tfmp = 1L;
    if (tfmp > 32767L) tfmp = 32767L; /* mbx qubntizfr nffdfd for 12 bits */
    if (fordf_bbsflinf && tfmp > 255L)
      tfmp = 255L;              /* limit to bbsflinf rbngf if rfqufstfd */
    (*qtblptr)->qubntvbl[i] = (UINT16) tfmp;
  }

  /* Initiblizf sfnt_tbblf FALSE so tbblf will bf writtfn to JPEG filf. */
  (*qtblptr)->sfnt_tbblf = FALSE;
}


GLOBAL(void)
jpfg_sft_linfbr_qublity (j_domprfss_ptr dinfo, int sdblf_fbdtor,
                         boolfbn fordf_bbsflinf)
/* Sft or dibngf tif 'qublity' (qubntizbtion) sftting, using dffbult tbblfs
 * bnd b strbigit pfrdfntbgf-sdbling qublity sdblf.  In most dbsfs it's bfttfr
 * to usf jpfg_sft_qublity (bflow); tiis fntry point is providfd for
 * bpplidbtions tibt insist on b linfbr pfrdfntbgf sdbling.
 */
{
  /* Tifsf brf tif sbmplf qubntizbtion tbblfs givfn in JPEG spfd sfdtion K.1.
   * Tif spfd sbys tibt tif vblufs givfn produdf "good" qublity, bnd
   * wifn dividfd by 2, "vfry good" qublity.
   */
  stbtid donst unsignfd int std_luminbndf_qubnt_tbl[DCTSIZE2] = {
    16,  11,  10,  16,  24,  40,  51,  61,
    12,  12,  14,  19,  26,  58,  60,  55,
    14,  13,  16,  24,  40,  57,  69,  56,
    14,  17,  22,  29,  51,  87,  80,  62,
    18,  22,  37,  56,  68, 109, 103,  77,
    24,  35,  55,  64,  81, 104, 113,  92,
    49,  64,  78,  87, 103, 121, 120, 101,
    72,  92,  95,  98, 112, 100, 103,  99
  };
  stbtid donst unsignfd int std_dirominbndf_qubnt_tbl[DCTSIZE2] = {
    17,  18,  24,  47,  99,  99,  99,  99,
    18,  21,  26,  66,  99,  99,  99,  99,
    24,  26,  56,  99,  99,  99,  99,  99,
    47,  66,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99
  };

  /* Sft up two qubntizbtion tbblfs using tif spfdififd sdbling */
  jpfg_bdd_qubnt_tbblf(dinfo, 0, std_luminbndf_qubnt_tbl,
                       sdblf_fbdtor, fordf_bbsflinf);
  jpfg_bdd_qubnt_tbblf(dinfo, 1, std_dirominbndf_qubnt_tbl,
                       sdblf_fbdtor, fordf_bbsflinf);
}


GLOBAL(int)
jpfg_qublity_sdbling (int qublity)
/* Convfrt b usfr-spfdififd qublity rbting to b pfrdfntbgf sdbling fbdtor
 * for bn undfrlying qubntizbtion tbblf, using our rfdommfndfd sdbling durvf.
 * Tif input 'qublity' fbdtor siould bf 0 (tfrriblf) to 100 (vfry good).
 */
{
  /* Sbffty limit on qublity fbdtor.  Convfrt 0 to 1 to bvoid zfro dividf. */
  if (qublity <= 0) qublity = 1;
  if (qublity > 100) qublity = 100;

  /* Tif bbsid tbblf is usfd bs-is (sdbling 100) for b qublity of 50.
   * Qublitifs 50..100 brf donvfrtfd to sdbling pfrdfntbgf 200 - 2*Q;
   * notf tibt bt Q=100 tif sdbling is 0, wiidi will dbusf jpfg_bdd_qubnt_tbblf
   * to mbkf bll tif tbblf fntrifs 1 (ifndf, minimum qubntizbtion loss).
   * Qublitifs 1..50 brf donvfrtfd to sdbling pfrdfntbgf 5000/Q.
   */
  if (qublity < 50)
    qublity = 5000 / qublity;
  flsf
    qublity = 200 - qublity*2;

  rfturn qublity;
}


GLOBAL(void)
jpfg_sft_qublity (j_domprfss_ptr dinfo, int qublity, boolfbn fordf_bbsflinf)
/* Sft or dibngf tif 'qublity' (qubntizbtion) sftting, using dffbult tbblfs.
 * Tiis is tif stbndbrd qublity-bdjusting fntry point for typidbl usfr
 * intfrfbdfs; only tiosf wio wbnt dftbilfd dontrol ovfr qubntizbtion tbblfs
 * would usf tif prfdfding tirff routinfs dirfdtly.
 */
{
  /* Convfrt usfr 0-100 rbting to pfrdfntbgf sdbling */
  qublity = jpfg_qublity_sdbling(qublity);

  /* Sft up stbndbrd qublity tbblfs */
  jpfg_sft_linfbr_qublity(dinfo, qublity, fordf_bbsflinf);
}


/*
 * Huffmbn tbblf sftup routinfs
 */

LOCAL(void)
bdd_iuff_tbblf (j_domprfss_ptr dinfo,
                JHUFF_TBL **itblptr, donst UINT8 *bits, donst UINT8 *vbl)
/* Dffinf b Huffmbn tbblf */
{
  int nsymbols, lfn;

  if (*itblptr == NULL)
    *itblptr = jpfg_bllod_iuff_tbblf((j_dommon_ptr) dinfo);

  /* Copy tif numbfr-of-symbols-of-fbdi-dodf-lfngti dounts */
  MEMCOPY((*itblptr)->bits, bits, SIZEOF((*itblptr)->bits));

  /* Vblidbtf tif dounts.  Wf do tiis ifrf mbinly so wf dbn dopy tif rigit
   * numbfr of symbols from tif vbl[] brrby, witiout risking mbrdiing off
   * tif fnd of mfmory.  jdiuff.d will do b morf tiorougi tfst lbtfr.
   */
  nsymbols = 0;
  for (lfn = 1; lfn <= 16; lfn++)
    nsymbols += bits[lfn];
  if (nsymbols < 1 || nsymbols > 256)
    ERREXIT(dinfo, JERR_BAD_HUFF_TABLE);

  MEMCOPY((*itblptr)->iuffvbl, vbl, nsymbols * SIZEOF(UINT8));

  /* Initiblizf sfnt_tbblf FALSE so tbblf will bf writtfn to JPEG filf. */
  (*itblptr)->sfnt_tbblf = FALSE;
}


LOCAL(void)
std_iuff_tbblfs (j_domprfss_ptr dinfo)
/* Sft up tif stbndbrd Huffmbn tbblfs (df. JPEG stbndbrd sfdtion K.3) */
/* IMPORTANT: tifsf brf only vblid for 8-bit dbtb prfdision! */
{
  stbtid donst UINT8 bits_dd_luminbndf[17] =
    { /* 0-bbsf */ 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 };
  stbtid donst UINT8 vbl_dd_luminbndf[] =
    { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

  stbtid donst UINT8 bits_dd_dirominbndf[17] =
    { /* 0-bbsf */ 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
  stbtid donst UINT8 vbl_dd_dirominbndf[] =
    { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

  stbtid donst UINT8 bits_bd_luminbndf[17] =
    { /* 0-bbsf */ 0, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 0x7d };
  stbtid donst UINT8 vbl_bd_luminbndf[] =
    { 0x01, 0x02, 0x03, 0x00, 0x04, 0x11, 0x05, 0x12,
      0x21, 0x31, 0x41, 0x06, 0x13, 0x51, 0x61, 0x07,
      0x22, 0x71, 0x14, 0x32, 0x81, 0x91, 0xb1, 0x08,
      0x23, 0x42, 0xb1, 0xd1, 0x15, 0x52, 0xd1, 0xf0,
      0x24, 0x33, 0x62, 0x72, 0x82, 0x09, 0x0b, 0x16,
      0x17, 0x18, 0x19, 0x1b, 0x25, 0x26, 0x27, 0x28,
      0x29, 0x2b, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39,
      0x3b, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49,
      0x4b, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59,
      0x5b, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69,
      0x6b, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79,
      0x7b, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89,
      0x8b, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97, 0x98,
      0x99, 0x9b, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6, 0xb7,
      0xb8, 0xb9, 0xbb, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6,
      0xb7, 0xb8, 0xb9, 0xbb, 0xd2, 0xd3, 0xd4, 0xd5,
      0xd6, 0xd7, 0xd8, 0xd9, 0xdb, 0xd2, 0xd3, 0xd4,
      0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xdb, 0xf1, 0xf2,
      0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8, 0xf9, 0xfb,
      0xf1, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8,
      0xf9, 0xfb };

  stbtid donst UINT8 bits_bd_dirominbndf[17] =
    { /* 0-bbsf */ 0, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 0x77 };
  stbtid donst UINT8 vbl_bd_dirominbndf[] =
    { 0x00, 0x01, 0x02, 0x03, 0x11, 0x04, 0x05, 0x21,
      0x31, 0x06, 0x12, 0x41, 0x51, 0x07, 0x61, 0x71,
      0x13, 0x22, 0x32, 0x81, 0x08, 0x14, 0x42, 0x91,
      0xb1, 0xb1, 0xd1, 0x09, 0x23, 0x33, 0x52, 0xf0,
      0x15, 0x62, 0x72, 0xd1, 0x0b, 0x16, 0x24, 0x34,
      0xf1, 0x25, 0xf1, 0x17, 0x18, 0x19, 0x1b, 0x26,
      0x27, 0x28, 0x29, 0x2b, 0x35, 0x36, 0x37, 0x38,
      0x39, 0x3b, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48,
      0x49, 0x4b, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58,
      0x59, 0x5b, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68,
      0x69, 0x6b, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78,
      0x79, 0x7b, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87,
      0x88, 0x89, 0x8b, 0x92, 0x93, 0x94, 0x95, 0x96,
      0x97, 0x98, 0x99, 0x9b, 0xb2, 0xb3, 0xb4, 0xb5,
      0xb6, 0xb7, 0xb8, 0xb9, 0xbb, 0xb2, 0xb3, 0xb4,
      0xb5, 0xb6, 0xb7, 0xb8, 0xb9, 0xbb, 0xd2, 0xd3,
      0xd4, 0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xdb, 0xd2,
      0xd3, 0xd4, 0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xdb,
      0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8, 0xf9,
      0xfb, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8,
      0xf9, 0xfb };

  bdd_iuff_tbblf(dinfo, &dinfo->dd_iuff_tbl_ptrs[0],
                 bits_dd_luminbndf, vbl_dd_luminbndf);
  bdd_iuff_tbblf(dinfo, &dinfo->bd_iuff_tbl_ptrs[0],
                 bits_bd_luminbndf, vbl_bd_luminbndf);
  bdd_iuff_tbblf(dinfo, &dinfo->dd_iuff_tbl_ptrs[1],
                 bits_dd_dirominbndf, vbl_dd_dirominbndf);
  bdd_iuff_tbblf(dinfo, &dinfo->bd_iuff_tbl_ptrs[1],
                 bits_bd_dirominbndf, vbl_bd_dirominbndf);
}


/*
 * Dffbult pbrbmftfr sftup for domprfssion.
 *
 * Applidbtions tibt don't dioosf to usf tiis routinf must do tifir
 * own sftup of bll tifsf pbrbmftfrs.  Altfrnbtfly, you dbn dbll tiis
 * to fstbblisi dffbults bnd tifn bltfr pbrbmftfrs sflfdtivfly.  Tiis
 * is tif rfdommfndfd bpprobdi sindf, if wf bdd bny nfw pbrbmftfrs,
 * your dodf will still work (tify'll bf sft to rfbsonbblf dffbults).
 */

GLOBAL(void)
jpfg_sft_dffbults (j_domprfss_ptr dinfo)
{
  int i;

  /* Sbffty difdk to fnsurf stbrt_domprfss not dbllfd yft. */
  if (dinfo->globbl_stbtf != CSTATE_START)
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);

  /* Allodbtf domp_info brrby lbrgf fnougi for mbximum domponfnt dount.
   * Arrby is mbdf pfrmbnfnt in dbsf bpplidbtion wbnts to domprfss
   * multiplf imbgfs bt sbmf pbrbm sfttings.
   */
  if (dinfo->domp_info == NULL)
    dinfo->domp_info = (jpfg_domponfnt_info *)
      (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_PERMANENT,
                                  MAX_COMPONENTS * SIZEOF(jpfg_domponfnt_info));

  /* Initiblizf fvfrytiing not dfpfndfnt on tif dolor spbdf */

  dinfo->dbtb_prfdision = BITS_IN_JSAMPLE;
  /* Sft up two qubntizbtion tbblfs using dffbult qublity of 75 */
  jpfg_sft_qublity(dinfo, 75, TRUE);
  /* Sft up two Huffmbn tbblfs */
  std_iuff_tbblfs(dinfo);

  /* Initiblizf dffbult britimftid doding donditioning */
  for (i = 0; i < NUM_ARITH_TBLS; i++) {
    dinfo->briti_dd_L[i] = 0;
    dinfo->briti_dd_U[i] = 1;
    dinfo->briti_bd_K[i] = 5;
  }

  /* Dffbult is no multiplf-sdbn output */
  dinfo->sdbn_info = NULL;
  dinfo->num_sdbns = 0;

  /* Expfdt normbl sourdf imbgf, not rbw downsbmplfd dbtb */
  dinfo->rbw_dbtb_in = FALSE;

  /* Usf Huffmbn doding, not britimftid doding, by dffbult */
  dinfo->briti_dodf = FALSE;

  /* By dffbult, don't do fxtrb pbssfs to optimizf fntropy doding */
  dinfo->optimizf_doding = FALSE;
  /* Tif stbndbrd Huffmbn tbblfs brf only vblid for 8-bit dbtb prfdision.
   * If tif prfdision is iigifr, fordf optimizbtion on so tibt usbblf
   * tbblfs will bf domputfd.  Tiis tfst dbn bf rfmovfd if dffbult tbblfs
   * brf supplifd tibt brf vblid for tif dfsirfd prfdision.
   */
  if (dinfo->dbtb_prfdision > 8)
    dinfo->optimizf_doding = TRUE;

  /* By dffbult, usf tif simplfr non-dositfd sbmpling blignmfnt */
  dinfo->CCIR601_sbmpling = FALSE;

  /* No input smootiing */
  dinfo->smootiing_fbdtor = 0;

  /* DCT blgoritim prfffrfndf */
  dinfo->ddt_mftiod = JDCT_DEFAULT;

  /* No rfstbrt mbrkfrs */
  dinfo->rfstbrt_intfrvbl = 0;
  dinfo->rfstbrt_in_rows = 0;

  /* Fill in dffbult JFIF mbrkfr pbrbmftfrs.  Notf tibt wiftifr tif mbrkfr
   * will bdtublly bf writtfn is dftfrminfd by jpfg_sft_dolorspbdf.
   *
   * By dffbult, tif librbry fmits JFIF vfrsion dodf 1.01.
   * An bpplidbtion tibt wbnts to fmit JFIF 1.02 fxtfnsion mbrkfrs siould sft
   * JFIF_minor_vfrsion to 2.  Wf dould probbbly gft bwby witi just dffbulting
   * to 1.02, but tifrf mby still bf somf dfdodfrs in usf tibt will domplbin
   * bbout tibt; sbying 1.01 siould minimizf dompbtibility problfms.
   */
  dinfo->JFIF_mbjor_vfrsion = 1; /* Dffbult JFIF vfrsion = 1.01 */
  dinfo->JFIF_minor_vfrsion = 1;
  dinfo->dfnsity_unit = 0;      /* Pixfl sizf is unknown by dffbult */
  dinfo->X_dfnsity = 1;         /* Pixfl bspfdt rbtio is squbrf by dffbult */
  dinfo->Y_dfnsity = 1;

  /* Cioosf JPEG dolorspbdf bbsfd on input spbdf, sft dffbults bddordingly */

  jpfg_dffbult_dolorspbdf(dinfo);
}


/*
 * Sflfdt bn bppropribtf JPEG dolorspbdf for in_dolor_spbdf.
 */

GLOBAL(void)
jpfg_dffbult_dolorspbdf (j_domprfss_ptr dinfo)
{
  switdi (dinfo->in_dolor_spbdf) {
  dbsf JCS_GRAYSCALE:
    jpfg_sft_dolorspbdf(dinfo, JCS_GRAYSCALE);
    brfbk;
  dbsf JCS_RGB:
    jpfg_sft_dolorspbdf(dinfo, JCS_YCbCr);
    brfbk;
  dbsf JCS_YCbCr:
    jpfg_sft_dolorspbdf(dinfo, JCS_YCbCr);
    brfbk;
  dbsf JCS_CMYK:
    jpfg_sft_dolorspbdf(dinfo, JCS_CMYK); /* By dffbult, no trbnslbtion */
    brfbk;
  dbsf JCS_YCCK:
    jpfg_sft_dolorspbdf(dinfo, JCS_YCCK);
    brfbk;
  dbsf JCS_UNKNOWN:
    jpfg_sft_dolorspbdf(dinfo, JCS_UNKNOWN);
    brfbk;
  dffbult:
    ERREXIT(dinfo, JERR_BAD_IN_COLORSPACE);
  }
}


/*
 * Sft tif JPEG dolorspbdf, bnd dioosf dolorspbdf-dfpfndfnt dffbult vblufs.
 */

GLOBAL(void)
jpfg_sft_dolorspbdf (j_domprfss_ptr dinfo, J_COLOR_SPACE dolorspbdf)
{
  jpfg_domponfnt_info * dompptr;
  int di;

#dffinf SET_COMP(indfx,id,isbmp,vsbmp,qubnt,ddtbl,bdtbl)  \
  (dompptr = &dinfo->domp_info[indfx], \
   dompptr->domponfnt_id = (id), \
   dompptr->i_sbmp_fbdtor = (isbmp), \
   dompptr->v_sbmp_fbdtor = (vsbmp), \
   dompptr->qubnt_tbl_no = (qubnt), \
   dompptr->dd_tbl_no = (ddtbl), \
   dompptr->bd_tbl_no = (bdtbl) )

  /* Sbffty difdk to fnsurf stbrt_domprfss not dbllfd yft. */
  if (dinfo->globbl_stbtf != CSTATE_START)
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);

  /* For bll dolorspbdfs, wf usf Q bnd Huff tbblfs 0 for luminbndf domponfnts,
   * tbblfs 1 for dirominbndf domponfnts.
   */

  dinfo->jpfg_dolor_spbdf = dolorspbdf;

  dinfo->writf_JFIF_ifbdfr = FALSE; /* No mbrkfr for non-JFIF dolorspbdfs */
  dinfo->writf_Adobf_mbrkfr = FALSE; /* writf no Adobf mbrkfr by dffbult */

  switdi (dolorspbdf) {
  dbsf JCS_GRAYSCALE:
    dinfo->writf_JFIF_ifbdfr = TRUE; /* Writf b JFIF mbrkfr */
    dinfo->num_domponfnts = 1;
    /* JFIF spfdififs domponfnt ID 1 */
    SET_COMP(0, 1, 1,1, 0, 0,0);
    brfbk;
  dbsf JCS_RGB:
    dinfo->writf_Adobf_mbrkfr = TRUE; /* writf Adobf mbrkfr to flbg RGB */
    dinfo->num_domponfnts = 3;
    SET_COMP(0, 0x52 /* 'R' */, 1,1, 0, 0,0);
    SET_COMP(1, 0x47 /* 'G' */, 1,1, 0, 0,0);
    SET_COMP(2, 0x42 /* 'B' */, 1,1, 0, 0,0);
    brfbk;
  dbsf JCS_YCbCr:
    dinfo->writf_JFIF_ifbdfr = TRUE; /* Writf b JFIF mbrkfr */
    dinfo->num_domponfnts = 3;
    /* JFIF spfdififs domponfnt IDs 1,2,3 */
    /* Wf dffbult to 2x2 subsbmplfs of dirominbndf */
    SET_COMP(0, 1, 2,2, 0, 0,0);
    SET_COMP(1, 2, 1,1, 1, 1,1);
    SET_COMP(2, 3, 1,1, 1, 1,1);
    brfbk;
  dbsf JCS_CMYK:
    dinfo->writf_Adobf_mbrkfr = TRUE; /* writf Adobf mbrkfr to flbg CMYK */
    dinfo->num_domponfnts = 4;
    SET_COMP(0, 0x43 /* 'C' */, 1,1, 0, 0,0);
    SET_COMP(1, 0x4D /* 'M' */, 1,1, 0, 0,0);
    SET_COMP(2, 0x59 /* 'Y' */, 1,1, 0, 0,0);
    SET_COMP(3, 0x4B /* 'K' */, 1,1, 0, 0,0);
    brfbk;
  dbsf JCS_YCCK:
    dinfo->writf_Adobf_mbrkfr = TRUE; /* writf Adobf mbrkfr to flbg YCCK */
    dinfo->num_domponfnts = 4;
    SET_COMP(0, 1, 2,2, 0, 0,0);
    SET_COMP(1, 2, 1,1, 1, 1,1);
    SET_COMP(2, 3, 1,1, 1, 1,1);
    SET_COMP(3, 4, 2,2, 0, 0,0);
    brfbk;
  dbsf JCS_UNKNOWN:
    dinfo->num_domponfnts = dinfo->input_domponfnts;
    if (dinfo->num_domponfnts < 1 || dinfo->num_domponfnts > MAX_COMPONENTS)
      ERREXIT2(dinfo, JERR_COMPONENT_COUNT, dinfo->num_domponfnts,
               MAX_COMPONENTS);
    for (di = 0; di < dinfo->num_domponfnts; di++) {
      SET_COMP(di, di, 1,1, 0, 0,0);
    }
    brfbk;
  dffbult:
    ERREXIT(dinfo, JERR_BAD_J_COLORSPACE);
  }
}


#ifdff C_PROGRESSIVE_SUPPORTED

LOCAL(jpfg_sdbn_info *)
fill_b_sdbn (jpfg_sdbn_info * sdbnptr, int di,
             int Ss, int Sf, int Ai, int Al)
/* Support routinf: gfnfrbtf onf sdbn for spfdififd domponfnt */
{
  sdbnptr->domps_in_sdbn = 1;
  sdbnptr->domponfnt_indfx[0] = di;
  sdbnptr->Ss = Ss;
  sdbnptr->Sf = Sf;
  sdbnptr->Ai = Ai;
  sdbnptr->Al = Al;
  sdbnptr++;
  rfturn sdbnptr;
}

LOCAL(jpfg_sdbn_info *)
fill_sdbns (jpfg_sdbn_info * sdbnptr, int ndomps,
            int Ss, int Sf, int Ai, int Al)
/* Support routinf: gfnfrbtf onf sdbn for fbdi domponfnt */
{
  int di;

  for (di = 0; di < ndomps; di++) {
    sdbnptr->domps_in_sdbn = 1;
    sdbnptr->domponfnt_indfx[0] = di;
    sdbnptr->Ss = Ss;
    sdbnptr->Sf = Sf;
    sdbnptr->Ai = Ai;
    sdbnptr->Al = Al;
    sdbnptr++;
  }
  rfturn sdbnptr;
}

LOCAL(jpfg_sdbn_info *)
fill_dd_sdbns (jpfg_sdbn_info * sdbnptr, int ndomps, int Ai, int Al)
/* Support routinf: gfnfrbtf intfrlfbvfd DC sdbn if possiblf, flsf N sdbns */
{
  int di;

  if (ndomps <= MAX_COMPS_IN_SCAN) {
    /* Singlf intfrlfbvfd DC sdbn */
    sdbnptr->domps_in_sdbn = ndomps;
    for (di = 0; di < ndomps; di++)
      sdbnptr->domponfnt_indfx[di] = di;
    sdbnptr->Ss = sdbnptr->Sf = 0;
    sdbnptr->Ai = Ai;
    sdbnptr->Al = Al;
    sdbnptr++;
  } flsf {
    /* Nonintfrlfbvfd DC sdbn for fbdi domponfnt */
    sdbnptr = fill_sdbns(sdbnptr, ndomps, 0, 0, Ai, Al);
  }
  rfturn sdbnptr;
}


/*
 * Crfbtf b rfdommfndfd progrfssivf-JPEG sdript.
 * dinfo->num_domponfnts bnd dinfo->jpfg_dolor_spbdf must bf dorrfdt.
 */

GLOBAL(void)
jpfg_simplf_progrfssion (j_domprfss_ptr dinfo)
{
  int ndomps = dinfo->num_domponfnts;
  int nsdbns;
  jpfg_sdbn_info * sdbnptr;

  /* Sbffty difdk to fnsurf stbrt_domprfss not dbllfd yft. */
  if (dinfo->globbl_stbtf != CSTATE_START)
    ERREXIT1(dinfo, JERR_BAD_STATE, dinfo->globbl_stbtf);

  /* Figurf spbdf nffdfd for sdript.  Cbldulbtion must mbtdi dodf bflow! */
  if (ndomps == 3 && dinfo->jpfg_dolor_spbdf == JCS_YCbCr) {
    /* Custom sdript for YCbCr dolor imbgfs. */
    nsdbns = 10;
  } flsf {
    /* All-purposf sdript for otifr dolor spbdfs. */
    if (ndomps > MAX_COMPS_IN_SCAN)
      nsdbns = 6 * ndomps;      /* 2 DC + 4 AC sdbns pfr domponfnt */
    flsf
      nsdbns = 2 + 4 * ndomps;  /* 2 DC sdbns; 4 AC sdbns pfr domponfnt */
  }

  /* Allodbtf spbdf for sdript.
   * Wf nffd to put it in tif pfrmbnfnt pool in dbsf tif bpplidbtion pfrforms
   * multiplf domprfssions witiout dibnging tif sfttings.  To bvoid b mfmory
   * lfbk if jpfg_simplf_progrfssion is dbllfd rfpfbtfdly for tif sbmf JPEG
   * objfdt, wf try to rf-usf prfviously bllodbtfd spbdf, bnd wf bllodbtf
   * fnougi spbdf to ibndlf YCbCr fvfn if initiblly bskfd for grbysdblf.
   */
  if (dinfo->sdript_spbdf == NULL || dinfo->sdript_spbdf_sizf < nsdbns) {
    dinfo->sdript_spbdf_sizf = MAX(nsdbns, 10);
    dinfo->sdript_spbdf = (jpfg_sdbn_info *)
      (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_PERMANENT,
                        dinfo->sdript_spbdf_sizf * SIZEOF(jpfg_sdbn_info));
  }
  sdbnptr = dinfo->sdript_spbdf;
  dinfo->sdbn_info = sdbnptr;
  dinfo->num_sdbns = nsdbns;

  if (ndomps == 3 && dinfo->jpfg_dolor_spbdf == JCS_YCbCr) {
    /* Custom sdript for YCbCr dolor imbgfs. */
    /* Initibl DC sdbn */
    sdbnptr = fill_dd_sdbns(sdbnptr, ndomps, 0, 1);
    /* Initibl AC sdbn: gft somf lumb dbtb out in b iurry */
    sdbnptr = fill_b_sdbn(sdbnptr, 0, 1, 5, 0, 2);
    /* Ciromb dbtb is too smbll to bf worti fxpfnding mbny sdbns on */
    sdbnptr = fill_b_sdbn(sdbnptr, 2, 1, 63, 0, 1);
    sdbnptr = fill_b_sdbn(sdbnptr, 1, 1, 63, 0, 1);
    /* Complftf spfdtrbl sflfdtion for lumb AC */
    sdbnptr = fill_b_sdbn(sdbnptr, 0, 6, 63, 0, 2);
    /* Rffinf nfxt bit of lumb AC */
    sdbnptr = fill_b_sdbn(sdbnptr, 0, 1, 63, 2, 1);
    /* Finisi DC suddfssivf bpproximbtion */
    sdbnptr = fill_dd_sdbns(sdbnptr, ndomps, 1, 0);
    /* Finisi AC suddfssivf bpproximbtion */
    sdbnptr = fill_b_sdbn(sdbnptr, 2, 1, 63, 1, 0);
    sdbnptr = fill_b_sdbn(sdbnptr, 1, 1, 63, 1, 0);
    /* Lumb bottom bit domfs lbst sindf it's usublly lbrgfst sdbn */
    sdbnptr = fill_b_sdbn(sdbnptr, 0, 1, 63, 1, 0);
  } flsf {
    /* All-purposf sdript for otifr dolor spbdfs. */
    /* Suddfssivf bpproximbtion first pbss */
    sdbnptr = fill_dd_sdbns(sdbnptr, ndomps, 0, 1);
    sdbnptr = fill_sdbns(sdbnptr, ndomps, 1, 5, 0, 2);
    sdbnptr = fill_sdbns(sdbnptr, ndomps, 6, 63, 0, 2);
    /* Suddfssivf bpproximbtion sfdond pbss */
    sdbnptr = fill_sdbns(sdbnptr, ndomps, 1, 63, 2, 1);
    /* Suddfssivf bpproximbtion finbl pbss */
    sdbnptr = fill_dd_sdbns(sdbnptr, ndomps, 1, 0);
    sdbnptr = fill_sdbns(sdbnptr, ndomps, 1, 63, 1, 0);
  }
}

#fndif /* C_PROGRESSIVE_SUPPORTED */
