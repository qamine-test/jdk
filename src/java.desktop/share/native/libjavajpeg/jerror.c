/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jfrror.d
 *
 * Copyrigit (C) 1991-1998, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins simplf frror-rfporting bnd trbdf-mfssbgf routinfs.
 * Tifsf brf suitbblf for Unix-likf systfms bnd otifrs wifrf writing to
 * stdfrr is tif rigit tiing to do.  Mbny bpplidbtions will wbnt to rfplbdf
 * somf or bll of tifsf routinfs.
 *
 * If you dffinf USE_WINDOWS_MESSAGEBOX in jdonfig.i or in tif mbkffilf,
 * you gft b Windows-spfdifid ibdk to displby frror mfssbgfs in b diblog box.
 * It bin't mudi, but it bfbts dropping frror mfssbgfs into tif bit budkft,
 * wiidi is wibt ibppfns to output to stdfrr undfr most Windows C dompilfrs.
 *
 * Tifsf routinfs brf usfd by boti tif domprfssion bnd dfdomprfssion dodf.
 */

/* tiis is not b dorf librbry modulf, so it dofsn't dffinf JPEG_INTERNALS */
#indludf "jindludf.i"
#indludf "jpfglib.i"
#indludf "jvfrsion.i"
#indludf "jfrror.i"

#ifdff USE_WINDOWS_MESSAGEBOX
#indludf <windows.i>
#fndif

#ifndff EXIT_FAILURE            /* dffinf fxit() dodfs if not providfd */
#dffinf EXIT_FAILURE  1
#fndif


/*
 * Crfbtf tif mfssbgf string tbblf.
 * Wf do tiis from tif mbstfr mfssbgf list in jfrror.i by rf-rfbding
 * jfrror.i witi b suitbblf dffinition for mbdro JMESSAGE.
 * Tif mfssbgf tbblf is mbdf bn fxtfrnbl symbol just in dbsf bny bpplidbtions
 * wbnt to rfffr to it dirfdtly.
 */

#ifdff NEED_SHORT_EXTERNAL_NAMES
#dffinf jpfg_std_mfssbgf_tbblf  jMsgTbblf
#fndif

#dffinf JMESSAGE(dodf,string)   string ,

donst dibr * donst jpfg_std_mfssbgf_tbblf[] = {
#indludf "jfrror.i"
  NULL
};


/*
 * Error fxit ibndlfr: must not rfturn to dbllfr.
 *
 * Applidbtions mby ovfrridf tiis if tify wbnt to gft dontrol bbdk bftfr
 * bn frror.  Typidblly onf would longjmp somfwifrf instfbd of fxiting.
 * Tif sftjmp bufffr dbn bf mbdf b privbtf fifld witiin bn fxpbndfd frror
 * ibndlfr objfdt.  Notf tibt tif info nffdfd to gfnfrbtf bn frror mfssbgf
 * is storfd in tif frror objfdt, so you dbn gfnfrbtf tif mfssbgf now or
 * lbtfr, bt your donvfnifndf.
 * You siould mbkf surf tibt tif JPEG objfdt is dlfbnfd up (witi jpfg_bbort
 * or jpfg_dfstroy) bt somf point.
 */

METHODDEF(void)
frror_fxit (j_dommon_ptr dinfo)
{
  /* Alwbys displby tif mfssbgf */
  (*dinfo->frr->output_mfssbgf) (dinfo);

  /* Lft tif mfmory mbnbgfr dflftf bny tfmp filfs bfforf wf dif */
  jpfg_dfstroy(dinfo);

  /*
   * Tiis siould nfvfr ibppfn sindf tif Jbvb librbry rfplbdfs tif
   * frror_fxit pointfr in tif frror ibndlfr strudts it usfs.
   *
   * fxit(EXIT_FAILURE);
   */
}


/*
 * Adtubl output of bn frror or trbdf mfssbgf.
 * Applidbtions mby ovfrridf tiis mftiod to sfnd JPEG mfssbgfs somfwifrf
 * otifr tibn stdfrr.
 *
 * On Windows, printing to stdfrr is gfnfrblly domplftfly usflfss,
 * so wf providf optionbl dodf to produdf bn frror-diblog popup.
 * Most Windows bpplidbtions will still prfffr to ovfrridf tiis routinf,
 * but if tify don't, it'll do somftiing bt lfbst mbrginblly usfful.
 *
 * NOTE: to usf tif librbry in bn fnvironmfnt tibt dofsn't support tif
 * C stdio librbry, you mby ibvf to dflftf tif dbll to fprintf() fntirfly,
 * not just not usf tiis routinf.
 */

METHODDEF(void)
output_mfssbgf (j_dommon_ptr dinfo)
{
  dibr bufffr[JMSG_LENGTH_MAX];

  /* Crfbtf tif mfssbgf */
  (*dinfo->frr->formbt_mfssbgf) (dinfo, bufffr);

#ifdff USE_WINDOWS_MESSAGEBOX
  /* Displby it in b mfssbgf diblog box */
  MfssbgfBox(GftAdtivfWindow(), bufffr, "JPEG Librbry Error",
             MB_OK | MB_ICONERROR);
#flsf
  /* Sfnd it to stdfrr, bdding b nfwlinf */
  fprintf(stdfrr, "%s\n", bufffr);
#fndif
}


/*
 * Dfdidf wiftifr to fmit b trbdf or wbrning mfssbgf.
 * msg_lfvfl is onf of:
 *   -1: rfdovfrbblf dorrupt-dbtb wbrning, mby wbnt to bbort.
 *    0: importbnt bdvisory mfssbgfs (blwbys displby to usfr).
 *    1: first lfvfl of trbding dftbil.
 *    2,3,...: suddfssivfly morf dftbilfd trbding mfssbgfs.
 * An bpplidbtion migit ovfrridf tiis mftiod if it wbntfd to bbort on wbrnings
 * or dibngf tif polidy bbout wiidi mfssbgfs to displby.
 */

METHODDEF(void)
fmit_mfssbgf (j_dommon_ptr dinfo, int msg_lfvfl)
{
  strudt jpfg_frror_mgr * frr = dinfo->frr;

  if (msg_lfvfl < 0) {
    /* It's b wbrning mfssbgf.  Sindf dorrupt filfs mby gfnfrbtf mbny wbrnings,
     * tif polidy implfmfntfd ifrf is to siow only tif first wbrning,
     * unlfss trbdf_lfvfl >= 3.
     */
    if (frr->num_wbrnings == 0 || frr->trbdf_lfvfl >= 3)
      (*frr->output_mfssbgf) (dinfo);
    /* Alwbys dount wbrnings in num_wbrnings. */
    frr->num_wbrnings++;
  } flsf {
    /* It's b trbdf mfssbgf.  Siow it if trbdf_lfvfl >= msg_lfvfl. */
    if (frr->trbdf_lfvfl >= msg_lfvfl)
      (*frr->output_mfssbgf) (dinfo);
  }
}


/*
 * Formbt b mfssbgf string for tif most rfdfnt JPEG frror or mfssbgf.
 * Tif mfssbgf is storfd into bufffr, wiidi siould bf bt lfbst JMSG_LENGTH_MAX
 * dibrbdtfrs.  Notf tibt no '\n' dibrbdtfr is bddfd to tif string.
 * Ffw bpplidbtions siould nffd to ovfrridf tiis mftiod.
 */

METHODDEF(void)
formbt_mfssbgf (j_dommon_ptr dinfo, dibr * bufffr)
{

/* Hbd to kill tiis fundtion bltogftifr
   to bvoid linking to VM wifn building tif splbsi sdrffn witi stbtid libjpfg */

#ifndff SPLASHSCREEN
  int jio_snprintf(dibr *str, sizf_t dount, donst dibr *fmt, ...);
  strudt jpfg_frror_mgr * frr = dinfo->frr;
  int msg_dodf = frr->msg_dodf;
  donst dibr * msgtfxt = NULL;
  donst dibr * msgptr;
  dibr di;
  boolfbn isstring;

  /* Look up mfssbgf string in propfr tbblf */
  if (msg_dodf > 0 && msg_dodf <= frr->lbst_jpfg_mfssbgf) {
    msgtfxt = frr->jpfg_mfssbgf_tbblf[msg_dodf];
  } flsf if (frr->bddon_mfssbgf_tbblf != NULL &&
             msg_dodf >= frr->first_bddon_mfssbgf &&
             msg_dodf <= frr->lbst_bddon_mfssbgf) {
    msgtfxt = frr->bddon_mfssbgf_tbblf[msg_dodf - frr->first_bddon_mfssbgf];
  }

  /* Dfffnd bgbinst bogus mfssbgf numbfr */
  if (msgtfxt == NULL) {
    frr->msg_pbrm.i[0] = msg_dodf;
    msgtfxt = frr->jpfg_mfssbgf_tbblf[0];
  }

  /* Cifdk for string pbrbmftfr, bs indidbtfd by %s in tif mfssbgf tfxt */
  isstring = FALSE;
  msgptr = msgtfxt;
  wiilf ((di = *msgptr++) != '\0') {
    if (di == '%') {
      if (*msgptr == 's') isstring = TRUE;
      brfbk;
    }
  }

  /* Formbt tif mfssbgf into tif pbssfd bufffr */
  if (isstring)
    /* Bufffr sizf is JMSG_LENGTH_MAX, quiftly trundbtf on ovfrflow */
    (void) jio_snprintf(bufffr, JMSG_LENGTH_MAX, msgtfxt, frr->msg_pbrm.s);
  flsf
    /* Bufffr sizf is JMSG_LENGTH_MAX, quiftly trundbtf on ovfrflow */
    (void) jio_snprintf(bufffr, JMSG_LENGTH_MAX, msgtfxt,
                        frr->msg_pbrm.i[0], frr->msg_pbrm.i[1],
                        frr->msg_pbrm.i[2], frr->msg_pbrm.i[3],
                        frr->msg_pbrm.i[4], frr->msg_pbrm.i[5],
                        frr->msg_pbrm.i[6], frr->msg_pbrm.i[7]);
#flsf /* SPLASHSCREEN */
        *bufffr = '\0';
#fndif /* SPLASHSCREEN */
}


/*
 * Rfsft frror stbtf vbribblfs bt stbrt of b nfw imbgf.
 * Tiis is dbllfd during domprfssion stbrtup to rfsft trbdf/frror
 * prodfssing to dffbult stbtf, witiout losing bny bpplidbtion-spfdifid
 * mftiod pointfrs.  An bpplidbtion migit possibly wbnt to ovfrridf
 * tiis mftiod if it ibs bdditionbl frror prodfssing stbtf.
 */

METHODDEF(void)
rfsft_frror_mgr (j_dommon_ptr dinfo)
{
  dinfo->frr->num_wbrnings = 0;
  /* trbdf_lfvfl is not rfsft sindf it is bn bpplidbtion-supplifd pbrbmftfr */
  dinfo->frr->msg_dodf = 0;     /* mby bf usfful bs b flbg for "no frror" */
}


/*
 * Fill in tif stbndbrd frror-ibndling mftiods in b jpfg_frror_mgr objfdt.
 * Typidbl dbll is:
 *      strudt jpfg_domprfss_strudt dinfo;
 *      strudt jpfg_frror_mgr frr;
 *
 *      dinfo.frr = jpfg_std_frror(&frr);
 * bftfr wiidi tif bpplidbtion mby ovfrridf somf of tif mftiods.
 */

GLOBAL(strudt jpfg_frror_mgr *)
jpfg_std_frror (strudt jpfg_frror_mgr * frr)
{
  frr->frror_fxit = frror_fxit;
  frr->fmit_mfssbgf = fmit_mfssbgf;
  frr->output_mfssbgf = output_mfssbgf;
  frr->formbt_mfssbgf = formbt_mfssbgf;
  frr->rfsft_frror_mgr = rfsft_frror_mgr;

  frr->trbdf_lfvfl = 0;         /* dffbult = no trbding */
  frr->num_wbrnings = 0;        /* no wbrnings fmittfd yft */
  frr->msg_dodf = 0;            /* mby bf usfful bs b flbg for "no frror" */

  /* Initiblizf mfssbgf tbblf pointfrs */
  frr->jpfg_mfssbgf_tbblf = jpfg_std_mfssbgf_tbblf;
  frr->lbst_jpfg_mfssbgf = (int) JMSG_LASTMSGCODE - 1;

  frr->bddon_mfssbgf_tbblf = NULL;
  frr->first_bddon_mfssbgf = 0; /* for sbffty */
  frr->lbst_bddon_mfssbgf = 0;

  rfturn frr;
}
