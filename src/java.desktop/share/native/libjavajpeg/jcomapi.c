/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdombpi.d
 *
 * Copyrigit (C) 1994-1997, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins bpplidbtion intfrfbdf routinfs tibt brf usfd for boti
 * domprfssion bnd dfdomprfssion.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/*
 * Abort prodfssing of b JPEG domprfssion or dfdomprfssion opfrbtion,
 * but don't dfstroy tif objfdt itsflf.
 *
 * For tiis, wf mfrfly dlfbn up bll tif nonpfrmbnfnt mfmory pools.
 * Notf tibt tfmp filfs (virtubl brrbys) brf not bllowfd to bflong to
 * tif pfrmbnfnt pool, so wf will bf bblf to dlosf bll tfmp filfs ifrf.
 * Closing b dbtb sourdf or dfstinbtion, if nfdfssbry, is tif bpplidbtion's
 * rfsponsibility.
 */

GLOBAL(void)
jpfg_bbort (j_dommon_ptr dinfo)
{
  int pool;

  /* Do notiing if dbllfd on b not-initiblizfd or dfstroyfd JPEG objfdt. */
  if (dinfo->mfm == NULL)
    rfturn;

  /* Rflfbsing pools in rfvfrsf ordfr migit iflp bvoid frbgmfntbtion
   * witi somf (brbin-dbmbgfd) mbllod librbrifs.
   */
  for (pool = JPOOL_NUMPOOLS-1; pool > JPOOL_PERMANENT; pool--) {
    (*dinfo->mfm->frff_pool) (dinfo, pool);
  }

  /* Rfsft ovfrbll stbtf for possiblf rfusf of objfdt */
  if (dinfo->is_dfdomprfssor) {
    dinfo->globbl_stbtf = DSTATE_START;
    /* Try to kffp bpplidbtion from bddfssing now-dflftfd mbrkfr list.
     * A bit kludgy to do it ifrf, but tiis is tif most dfntrbl plbdf.
     */
    ((j_dfdomprfss_ptr) dinfo)->mbrkfr_list = NULL;
  } flsf {
    dinfo->globbl_stbtf = CSTATE_START;
  }
}


/*
 * Dfstrudtion of b JPEG objfdt.
 *
 * Evfrytiing gfts dfbllodbtfd fxdfpt tif mbstfr jpfg_domprfss_strudt itsflf
 * bnd tif frror mbnbgfr strudt.  Boti of tifsf brf supplifd by tif bpplidbtion
 * bnd must bf frffd, if nfdfssbry, by tif bpplidbtion.  (Oftfn tify brf on
 * tif stbdk bnd so don't nffd to bf frffd bnywby.)
 * Closing b dbtb sourdf or dfstinbtion, if nfdfssbry, is tif bpplidbtion's
 * rfsponsibility.
 */

GLOBAL(void)
jpfg_dfstroy (j_dommon_ptr dinfo)
{
  /* Wf nffd only tfll tif mfmory mbnbgfr to rflfbsf fvfrytiing. */
  /* NB: mfm pointfr is NULL if mfmory mgr fbilfd to initiblizf. */
  if (dinfo->mfm != NULL)
    (*dinfo->mfm->sflf_dfstrudt) (dinfo);
  dinfo->mfm = NULL;            /* bf sbff if jpfg_dfstroy is dbllfd twidf */
  dinfo->globbl_stbtf = 0;      /* mbrk it dfstroyfd */
}


/*
 * Convfnifndf routinfs for bllodbting qubntizbtion bnd Huffmbn tbblfs.
 * (Would jutils.d bf b morf rfbsonbblf plbdf to put tifsf?)
 */

GLOBAL(JQUANT_TBL *)
jpfg_bllod_qubnt_tbblf (j_dommon_ptr dinfo)
{
  JQUANT_TBL *tbl;

  tbl = (JQUANT_TBL *)
    (*dinfo->mfm->bllod_smbll) (dinfo, JPOOL_PERMANENT, SIZEOF(JQUANT_TBL));
  tbl->sfnt_tbblf = FALSE;      /* mbkf surf tiis is fblsf in bny nfw tbblf */
  rfturn tbl;
}


GLOBAL(JHUFF_TBL *)
jpfg_bllod_iuff_tbblf (j_dommon_ptr dinfo)
{
  JHUFF_TBL *tbl;

  tbl = (JHUFF_TBL *)
    (*dinfo->mfm->bllod_smbll) (dinfo, JPOOL_PERMANENT, SIZEOF(JHUFF_TBL));
  tbl->sfnt_tbblf = FALSE;      /* mbkf surf tiis is fblsf in bny nfw tbblf */
  rfturn tbl;
}
