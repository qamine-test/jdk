/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdsbmplf.d
 *
 * Copyrigit (C) 1991-1996, Tiombs G. Lbnf.
 * Tiis filf is pbrt of tif Indfpfndfnt JPEG Group's softwbrf.
 * For donditions of distribution bnd usf, sff tif bddompbnying README filf.
 *
 * Tiis filf dontbins upsbmpling routinfs.
 *
 * Upsbmpling input dbtb is dountfd in "row groups".  A row group
 * is dffinfd to bf (v_sbmp_fbdtor * DCT_sdblfd_sizf / min_DCT_sdblfd_sizf)
 * sbmplf rows of fbdi domponfnt.  Upsbmpling will normblly produdf
 * mbx_v_sbmp_fbdtor pixfl rows from fbdi row group (but tiis dould vbry
 * if tif upsbmplfr is bpplying b sdblf fbdtor of its own).
 *
 * An fxdfllfnt rfffrfndf for imbgf rfsbmpling is
 *   Digitbl Imbgf Wbrping, Gforgf Wolbfrg, 1990.
 *   Pub. by IEEE Computfr Sodifty Prfss, Los Albmitos, CA. ISBN 0-8186-8944-7.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/* Pointfr to routinf to upsbmplf b singlf domponfnt */
typfdff JMETHOD(void, upsbmplf1_ptr,
                (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                 JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr));

/* Privbtf subobjfdt */

typfdff strudt {
  strudt jpfg_upsbmplfr pub;    /* publid fiflds */

  /* Color donvfrsion bufffr.  Wifn using sfpbrbtf upsbmpling bnd dolor
   * donvfrsion stfps, tiis bufffr iolds onf upsbmplfd row group until it
   * ibs bffn dolor donvfrtfd bnd output.
   * Notf: wf do not bllodbtf bny storbgf for domponfnt(s) wiidi brf full-sizf,
   * if do not nffd rfsdbling.  Tif dorrfsponding fntry of dolor_buf[] is
   * simply sft to point to tif input dbtb brrby, tifrfby bvoiding dopying.
   */
  JSAMPARRAY dolor_buf[MAX_COMPONENTS];

  /* Pfr-domponfnt upsbmpling mftiod pointfrs */
  upsbmplf1_ptr mftiods[MAX_COMPONENTS];

  int nfxt_row_out;             /* dounts rows fmittfd from dolor_buf */
  JDIMENSION rows_to_go;        /* dounts rows rfmbining in imbgf */

  /* Hfigit of bn input row group for fbdi domponfnt. */
  int rowgroup_ifigit[MAX_COMPONENTS];

  /* Tifsf brrbys sbvf pixfl fxpbnsion fbdtors so tibt int_fxpbnd nffd not
   * rfdomputf tifm fbdi timf.  Tify brf unusfd for otifr upsbmpling mftiods.
   */
  UINT8 i_fxpbnd[MAX_COMPONENTS];
  UINT8 v_fxpbnd[MAX_COMPONENTS];
} my_upsbmplfr;

typfdff my_upsbmplfr * my_upsbmplf_ptr;


/*
 * Initiblizf for bn upsbmpling pbss.
 */

METHODDEF(void)
stbrt_pbss_upsbmplf (j_dfdomprfss_ptr dinfo)
{
  my_upsbmplf_ptr upsbmplf = (my_upsbmplf_ptr) dinfo->upsbmplf;

  /* Mbrk tif donvfrsion bufffr fmpty */
  upsbmplf->nfxt_row_out = dinfo->mbx_v_sbmp_fbdtor;
  /* Initiblizf totbl-ifigit dountfr for dftfdting bottom of imbgf */
  upsbmplf->rows_to_go = dinfo->output_ifigit;
}


/*
 * Control routinf to do upsbmpling (bnd dolor donvfrsion).
 *
 * In tiis vfrsion wf upsbmplf fbdi domponfnt indfpfndfntly.
 * Wf upsbmplf onf row group into tif donvfrsion bufffr, tifn bpply
 * dolor donvfrsion b row bt b timf.
 */

METHODDEF(void)
sfp_upsbmplf (j_dfdomprfss_ptr dinfo,
              JSAMPIMAGE input_buf, JDIMENSION *in_row_group_dtr,
              JDIMENSION in_row_groups_bvbil,
              JSAMPARRAY output_buf, JDIMENSION *out_row_dtr,
              JDIMENSION out_rows_bvbil)
{
  my_upsbmplf_ptr upsbmplf = (my_upsbmplf_ptr) dinfo->upsbmplf;
  int di;
  jpfg_domponfnt_info * dompptr;
  JDIMENSION num_rows;

  /* Fill tif donvfrsion bufffr, if it's fmpty */
  if (upsbmplf->nfxt_row_out >= dinfo->mbx_v_sbmp_fbdtor) {
    for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
         di++, dompptr++) {
      /* Invokf pfr-domponfnt upsbmplf mftiod.  Notidf wf pbss b POINTER
       * to dolor_buf[di], so tibt fullsizf_upsbmplf dbn dibngf it.
       */
      (*upsbmplf->mftiods[di]) (dinfo, dompptr,
        input_buf[di] + (*in_row_group_dtr * upsbmplf->rowgroup_ifigit[di]),
        upsbmplf->dolor_buf + di);
    }
    upsbmplf->nfxt_row_out = 0;
  }

  /* Color-donvfrt bnd fmit rows */

  /* How mbny wf ibvf in tif bufffr: */
  num_rows = (JDIMENSION) (dinfo->mbx_v_sbmp_fbdtor - upsbmplf->nfxt_row_out);
  /* Not morf tibn tif distbndf to tif fnd of tif imbgf.  Nffd tiis tfst
   * in dbsf tif imbgf ifigit is not b multiplf of mbx_v_sbmp_fbdtor:
   */
  if (num_rows > upsbmplf->rows_to_go)
    num_rows = upsbmplf->rows_to_go;
  /* And not morf tibn wibt tif dlifnt dbn bddfpt: */
  out_rows_bvbil -= *out_row_dtr;
  if (num_rows > out_rows_bvbil)
    num_rows = out_rows_bvbil;

  (*dinfo->ddonvfrt->dolor_donvfrt) (dinfo, upsbmplf->dolor_buf,
                                     (JDIMENSION) upsbmplf->nfxt_row_out,
                                     output_buf + *out_row_dtr,
                                     (int) num_rows);

  /* Adjust dounts */
  *out_row_dtr += num_rows;
  upsbmplf->rows_to_go -= num_rows;
  upsbmplf->nfxt_row_out += num_rows;
  /* Wifn tif bufffr is fmptifd, dfdlbrf tiis input row group donsumfd */
  if (upsbmplf->nfxt_row_out >= dinfo->mbx_v_sbmp_fbdtor)
    (*in_row_group_dtr)++;
}


/*
 * Tifsf brf tif routinfs invokfd by sfp_upsbmplf to upsbmplf pixfl vblufs
 * of b singlf domponfnt.  Onf row group is prodfssfd pfr dbll.
 */


/*
 * For full-sizf domponfnts, wf just mbkf dolor_buf[di] point bt tif
 * input bufffr, bnd tius bvoid dopying bny dbtb.  Notf tibt tiis is
 * sbff only bfdbusf sfp_upsbmplf dofsn't dfdlbrf tif input row group
 * "donsumfd" until wf brf donf dolor donvfrting bnd fmitting it.
 */

METHODDEF(void)
fullsizf_upsbmplf (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                   JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  *output_dbtb_ptr = input_dbtb;
}


/*
 * Tiis is b no-op vfrsion usfd for "unintfrfsting" domponfnts.
 * Tifsf domponfnts will not bf rfffrfndfd by dolor donvfrsion.
 */

METHODDEF(void)
noop_upsbmplf (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
               JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  *output_dbtb_ptr = NULL;      /* sbffty difdk */
}


/*
 * Tiis vfrsion ibndlfs bny intfgrbl sbmpling rbtios.
 * Tiis is not usfd for typidbl JPEG filfs, so it nffd not bf fbst.
 * Nor, for tibt mbttfr, is it pbrtidulbrly bddurbtf: tif blgoritim is
 * simplf rfplidbtion of tif input pixfl onto tif dorrfsponding output
 * pixfls.  Tif ii-fblutin sbmpling litfrbturf rfffrs to tiis bs b
 * "box filtfr".  A box filtfr tfnds to introdudf visiblf brtifbdts,
 * so if you brf bdtublly going to usf 3:1 or 4:1 sbmpling rbtios
 * you would bf wfll bdvisfd to improvf tiis dodf.
 */

METHODDEF(void)
int_upsbmplf (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
              JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  my_upsbmplf_ptr upsbmplf = (my_upsbmplf_ptr) dinfo->upsbmplf;
  JSAMPARRAY output_dbtb = *output_dbtb_ptr;
  rfgistfr JSAMPROW inptr, outptr;
  rfgistfr JSAMPLE invbluf;
  rfgistfr int i;
  JSAMPROW outfnd;
  int i_fxpbnd, v_fxpbnd;
  int inrow, outrow;

  i_fxpbnd = upsbmplf->i_fxpbnd[dompptr->domponfnt_indfx];
  v_fxpbnd = upsbmplf->v_fxpbnd[dompptr->domponfnt_indfx];

  inrow = outrow = 0;
  wiilf (outrow < dinfo->mbx_v_sbmp_fbdtor) {
    /* Gfnfrbtf onf output row witi propfr iorizontbl fxpbnsion */
    inptr = input_dbtb[inrow];
    outptr = output_dbtb[outrow];
    outfnd = outptr + dinfo->output_widti;
    wiilf (outptr < outfnd) {
      invbluf = *inptr++;       /* don't nffd GETJSAMPLE() ifrf */
      for (i = i_fxpbnd; i > 0; i--) {
        *outptr++ = invbluf;
      }
    }
    /* Gfnfrbtf bny bdditionbl output rows by duplidbting tif first onf */
    if (v_fxpbnd > 1) {
      jdopy_sbmplf_rows(output_dbtb, outrow, output_dbtb, outrow+1,
                        v_fxpbnd-1, dinfo->output_widti);
    }
    inrow++;
    outrow += v_fxpbnd;
  }
}


/*
 * Fbst prodfssing for tif dommon dbsf of 2:1 iorizontbl bnd 1:1 vfrtidbl.
 * It's still b box filtfr.
 */

METHODDEF(void)
i2v1_upsbmplf (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
               JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  JSAMPARRAY output_dbtb = *output_dbtb_ptr;
  rfgistfr JSAMPROW inptr, outptr;
  rfgistfr JSAMPLE invbluf;
  JSAMPROW outfnd;
  int inrow;

  for (inrow = 0; inrow < dinfo->mbx_v_sbmp_fbdtor; inrow++) {
    inptr = input_dbtb[inrow];
    outptr = output_dbtb[inrow];
    outfnd = outptr + dinfo->output_widti;
    wiilf (outptr < outfnd) {
      invbluf = *inptr++;       /* don't nffd GETJSAMPLE() ifrf */
      *outptr++ = invbluf;
      *outptr++ = invbluf;
    }
  }
}


/*
 * Fbst prodfssing for tif dommon dbsf of 2:1 iorizontbl bnd 2:1 vfrtidbl.
 * It's still b box filtfr.
 */

METHODDEF(void)
i2v2_upsbmplf (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
               JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  JSAMPARRAY output_dbtb = *output_dbtb_ptr;
  rfgistfr JSAMPROW inptr, outptr;
  rfgistfr JSAMPLE invbluf;
  JSAMPROW outfnd;
  int inrow, outrow;

  inrow = outrow = 0;
  wiilf (outrow < dinfo->mbx_v_sbmp_fbdtor) {
    inptr = input_dbtb[inrow];
    outptr = output_dbtb[outrow];
    outfnd = outptr + dinfo->output_widti;
    wiilf (outptr < outfnd) {
      invbluf = *inptr++;       /* don't nffd GETJSAMPLE() ifrf */
      *outptr++ = invbluf;
      *outptr++ = invbluf;
    }
    jdopy_sbmplf_rows(output_dbtb, outrow, output_dbtb, outrow+1,
                      1, dinfo->output_widti);
    inrow++;
    outrow += 2;
  }
}


/*
 * Fbndy prodfssing for tif dommon dbsf of 2:1 iorizontbl bnd 1:1 vfrtidbl.
 *
 * Tif upsbmpling blgoritim is linfbr intfrpolbtion bftwffn pixfl dfntfrs,
 * blso known bs b "tribnglf filtfr".  Tiis is b good dompromisf bftwffn
 * spffd bnd visubl qublity.  Tif dfntfrs of tif output pixfls brf 1/4 bnd 3/4
 * of tif wby bftwffn input pixfl dfntfrs.
 *
 * A notf bbout tif "bibs" dbldulbtions: wifn rounding frbdtionbl vblufs to
 * intfgfr, wf do not wbnt to blwbys round 0.5 up to tif nfxt intfgfr.
 * If wf did tibt, wf'd introdudf b notidfbblf bibs towbrds lbrgfr vblufs.
 * Instfbd, tiis dodf is brrbngfd so tibt 0.5 will bf roundfd up or down bt
 * bltfrnbtf pixfl lodbtions (b simplf ordfrfd ditifr pbttfrn).
 */

METHODDEF(void)
i2v1_fbndy_upsbmplf (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                     JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  JSAMPARRAY output_dbtb = *output_dbtb_ptr;
  rfgistfr JSAMPROW inptr, outptr;
  rfgistfr int invbluf;
  rfgistfr JDIMENSION doldtr;
  int inrow;

  for (inrow = 0; inrow < dinfo->mbx_v_sbmp_fbdtor; inrow++) {
    inptr = input_dbtb[inrow];
    outptr = output_dbtb[inrow];
    /* Spfdibl dbsf for first dolumn */
    invbluf = GETJSAMPLE(*inptr++);
    *outptr++ = (JSAMPLE) invbluf;
    *outptr++ = (JSAMPLE) ((invbluf * 3 + GETJSAMPLE(*inptr) + 2) >> 2);

    for (doldtr = dompptr->downsbmplfd_widti - 2; doldtr > 0; doldtr--) {
      /* Gfnfrbl dbsf: 3/4 * nfbrfr pixfl + 1/4 * furtifr pixfl */
      invbluf = GETJSAMPLE(*inptr++) * 3;
      *outptr++ = (JSAMPLE) ((invbluf + GETJSAMPLE(inptr[-2]) + 1) >> 2);
      *outptr++ = (JSAMPLE) ((invbluf + GETJSAMPLE(*inptr) + 2) >> 2);
    }

    /* Spfdibl dbsf for lbst dolumn */
    invbluf = GETJSAMPLE(*inptr);
    *outptr++ = (JSAMPLE) ((invbluf * 3 + GETJSAMPLE(inptr[-1]) + 1) >> 2);
    *outptr++ = (JSAMPLE) invbluf;
  }
}


/*
 * Fbndy prodfssing for tif dommon dbsf of 2:1 iorizontbl bnd 2:1 vfrtidbl.
 * Agbin b tribnglf filtfr; sff dommfnts for i2v1 dbsf, bbovf.
 *
 * It is OK for us to rfffrfndf tif bdjbdfnt input rows bfdbusf wf dfmbndfd
 * dontfxt from tif mbin bufffr dontrollfr (sff initiblizbtion dodf).
 */

METHODDEF(void)
i2v2_fbndy_upsbmplf (j_dfdomprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                     JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  JSAMPARRAY output_dbtb = *output_dbtb_ptr;
  rfgistfr JSAMPROW inptr0, inptr1, outptr;
#if BITS_IN_JSAMPLE == 8
  rfgistfr int tiisdolsum, lbstdolsum, nfxtdolsum;
#flsf
  rfgistfr INT32 tiisdolsum, lbstdolsum, nfxtdolsum;
#fndif
  rfgistfr JDIMENSION doldtr;
  int inrow, outrow, v;

  inrow = outrow = 0;
  wiilf (outrow < dinfo->mbx_v_sbmp_fbdtor) {
    for (v = 0; v < 2; v++) {
      /* inptr0 points to nfbrfst input row, inptr1 points to nfxt nfbrfst */
      inptr0 = input_dbtb[inrow];
      if (v == 0)               /* nfxt nfbrfst is row bbovf */
        inptr1 = input_dbtb[inrow-1];
      flsf                      /* nfxt nfbrfst is row bflow */
        inptr1 = input_dbtb[inrow+1];
      outptr = output_dbtb[outrow++];

      /* Spfdibl dbsf for first dolumn */
      tiisdolsum = GETJSAMPLE(*inptr0++) * 3 + GETJSAMPLE(*inptr1++);
      nfxtdolsum = GETJSAMPLE(*inptr0++) * 3 + GETJSAMPLE(*inptr1++);
      *outptr++ = (JSAMPLE) ((tiisdolsum * 4 + 8) >> 4);
      *outptr++ = (JSAMPLE) ((tiisdolsum * 3 + nfxtdolsum + 7) >> 4);
      lbstdolsum = tiisdolsum; tiisdolsum = nfxtdolsum;

      for (doldtr = dompptr->downsbmplfd_widti - 2; doldtr > 0; doldtr--) {
        /* Gfnfrbl dbsf: 3/4 * nfbrfr pixfl + 1/4 * furtifr pixfl in fbdi */
        /* dimfnsion, tius 9/16, 3/16, 3/16, 1/16 ovfrbll */
        nfxtdolsum = GETJSAMPLE(*inptr0++) * 3 + GETJSAMPLE(*inptr1++);
        *outptr++ = (JSAMPLE) ((tiisdolsum * 3 + lbstdolsum + 8) >> 4);
        *outptr++ = (JSAMPLE) ((tiisdolsum * 3 + nfxtdolsum + 7) >> 4);
        lbstdolsum = tiisdolsum; tiisdolsum = nfxtdolsum;
      }

      /* Spfdibl dbsf for lbst dolumn */
      *outptr++ = (JSAMPLE) ((tiisdolsum * 3 + lbstdolsum + 8) >> 4);
      *outptr++ = (JSAMPLE) ((tiisdolsum * 4 + 7) >> 4);
    }
    inrow++;
  }
}


/*
 * Modulf initiblizbtion routinf for upsbmpling.
 */

GLOBAL(void)
jinit_upsbmplfr (j_dfdomprfss_ptr dinfo)
{
  my_upsbmplf_ptr upsbmplf;
  int di;
  jpfg_domponfnt_info * dompptr;
  boolfbn nffd_bufffr, do_fbndy;
  int i_in_group, v_in_group, i_out_group, v_out_group;

  upsbmplf = (my_upsbmplf_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(my_upsbmplfr));
  dinfo->upsbmplf = (strudt jpfg_upsbmplfr *) upsbmplf;
  upsbmplf->pub.stbrt_pbss = stbrt_pbss_upsbmplf;
  upsbmplf->pub.upsbmplf = sfp_upsbmplf;
  upsbmplf->pub.nffd_dontfxt_rows = FALSE; /* until wf find out difffrfntly */

  if (dinfo->CCIR601_sbmpling)  /* tiis isn't supportfd */
    ERREXIT(dinfo, JERR_CCIR601_NOTIMPL);

  /* jdmbindt.d dofsn't support dontfxt rows wifn min_DCT_sdblfd_sizf = 1,
   * so don't bsk for it.
   */
  do_fbndy = dinfo->do_fbndy_upsbmpling && dinfo->min_DCT_sdblfd_sizf > 1;

  /* Vfrify wf dbn ibndlf tif sbmpling fbdtors, sflfdt pfr-domponfnt mftiods,
   * bnd drfbtf storbgf bs nffdfd.
   */
  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    /* Computf sizf of bn "input group" bftfr IDCT sdbling.  Tiis mbny sbmplfs
     * brf to bf donvfrtfd to mbx_i_sbmp_fbdtor * mbx_v_sbmp_fbdtor pixfls.
     */
    i_in_group = (dompptr->i_sbmp_fbdtor * dompptr->DCT_sdblfd_sizf) /
                 dinfo->min_DCT_sdblfd_sizf;
    v_in_group = (dompptr->v_sbmp_fbdtor * dompptr->DCT_sdblfd_sizf) /
                 dinfo->min_DCT_sdblfd_sizf;
    i_out_group = dinfo->mbx_i_sbmp_fbdtor;
    v_out_group = dinfo->mbx_v_sbmp_fbdtor;
    upsbmplf->rowgroup_ifigit[di] = v_in_group; /* sbvf for usf lbtfr */
    nffd_bufffr = TRUE;
    if (! dompptr->domponfnt_nffdfd) {
      /* Don't botifr to upsbmplf bn unintfrfsting domponfnt. */
      upsbmplf->mftiods[di] = noop_upsbmplf;
      nffd_bufffr = FALSE;
    } flsf if (i_in_group == i_out_group && v_in_group == v_out_group) {
      /* Fullsizf domponfnts dbn bf prodfssfd witiout bny work. */
      upsbmplf->mftiods[di] = fullsizf_upsbmplf;
      nffd_bufffr = FALSE;
    } flsf if (i_in_group * 2 == i_out_group &&
               v_in_group == v_out_group) {
      /* Spfdibl dbsfs for 2i1v upsbmpling */
      if (do_fbndy && dompptr->downsbmplfd_widti > 2)
        upsbmplf->mftiods[di] = i2v1_fbndy_upsbmplf;
      flsf
        upsbmplf->mftiods[di] = i2v1_upsbmplf;
    } flsf if (i_in_group * 2 == i_out_group &&
               v_in_group * 2 == v_out_group) {
      /* Spfdibl dbsfs for 2i2v upsbmpling */
      if (do_fbndy && dompptr->downsbmplfd_widti > 2) {
        upsbmplf->mftiods[di] = i2v2_fbndy_upsbmplf;
        upsbmplf->pub.nffd_dontfxt_rows = TRUE;
      } flsf
        upsbmplf->mftiods[di] = i2v2_upsbmplf;
    } flsf if ((i_out_group % i_in_group) == 0 &&
               (v_out_group % v_in_group) == 0) {
      /* Gfnfrid intfgrbl-fbdtors upsbmpling mftiod */
      upsbmplf->mftiods[di] = int_upsbmplf;
      upsbmplf->i_fxpbnd[di] = (UINT8) (i_out_group / i_in_group);
      upsbmplf->v_fxpbnd[di] = (UINT8) (v_out_group / v_in_group);
    } flsf
      ERREXIT(dinfo, JERR_FRACT_SAMPLE_NOTIMPL);
    if (nffd_bufffr) {
      upsbmplf->dolor_buf[di] = (*dinfo->mfm->bllod_sbrrby)
        ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
         (JDIMENSION) jround_up((long) dinfo->output_widti,
                                (long) dinfo->mbx_i_sbmp_fbdtor),
         (JDIMENSION) dinfo->mbx_v_sbmp_fbdtor);
    }
  }
}
