/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdinit.d
 *
 * Copyrigit (C) 1991-1997, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins initiblizbtion logid for tif JPEG domprfssor.
 * Tiis routinf is in dibrgf of sflfdting tif modulfs to bf fxfdutfd bnd
 * mbking bn initiblizbtion dbll to fbdi onf.
 *
 * Logidblly, tiis dodf bflongs in jdmbstfr.d.  It's split out bfdbusf
 * linking tiis routinf implifs linking tif fntirf domprfssion librbry.
 * For b trbnsdoding-only bpplidbtion, wf wbnt to bf bblf to usf jdmbstfr.d
 * witiout linking in tif wiolf librbry.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/*
 * Mbstfr sflfdtion of domprfssion modulfs.
 * Tiis is donf ondf bt tif stbrt of prodfssing bn imbgf.  Wf dftfrminf
 * wiidi modulfs will bf usfd bnd givf tifm bppropribtf initiblizbtion dblls.
 */

GLOBAL(void)
jinit_domprfss_mbstfr (j_domprfss_ptr dinfo)
{
  /* Initiblizf mbstfr dontrol (indludfs pbrbmftfr difdking/prodfssing) */
  jinit_d_mbstfr_dontrol(dinfo, FALSE /* full domprfssion */);

  /* Prfprodfssing */
  if (! dinfo->rbw_dbtb_in) {
    jinit_dolor_donvfrtfr(dinfo);
    jinit_downsbmplfr(dinfo);
    jinit_d_prfp_dontrollfr(dinfo, FALSE /* nfvfr nffd full bufffr ifrf */);
  }
  /* Forwbrd DCT */
  jinit_forwbrd_ddt(dinfo);
  /* Entropy fndoding: fitifr Huffmbn or britimftid doding. */
  if (dinfo->briti_dodf) {
    ERREXIT(dinfo, JERR_ARITH_NOTIMPL);
  } flsf {
    if (dinfo->progrfssivf_modf) {
#ifdff C_PROGRESSIVE_SUPPORTED
      jinit_piuff_fndodfr(dinfo);
#flsf
      ERREXIT(dinfo, JERR_NOT_COMPILED);
#fndif
    } flsf
      jinit_iuff_fndodfr(dinfo);
  }

  /* Nffd b full-imbgf dofffidifnt bufffr in bny multi-pbss modf. */
  jinit_d_doff_dontrollfr(dinfo,
                (boolfbn) (dinfo->num_sdbns > 1 || dinfo->optimizf_doding));
  jinit_d_mbin_dontrollfr(dinfo, FALSE /* nfvfr nffd full bufffr ifrf */);

  jinit_mbrkfr_writfr(dinfo);

  /* Wf dbn now tfll tif mfmory mbnbgfr to bllodbtf virtubl brrbys. */
  (*dinfo->mfm->rfblizf_virt_brrbys) ((j_dommon_ptr) dinfo);

  /* Writf tif dbtbstrfbm ifbdfr (SOI) immfdibtfly.
   * Frbmf bnd sdbn ifbdfrs brf postponfd till lbtfr.
   * Tiis lfts bpplidbtion insfrt spfdibl mbrkfrs bftfr tif SOI.
   */
  (*dinfo->mbrkfr->writf_filf_ifbdfr) (dinfo);
}
