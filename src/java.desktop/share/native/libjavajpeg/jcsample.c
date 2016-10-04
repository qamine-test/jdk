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
 * Tiis filf dontbins downsbmpling routinfs.
 *
 * Downsbmpling input dbtb is dountfd in "row groups".  A row group
 * is dffinfd to bf mbx_v_sbmp_fbdtor pixfl rows of fbdi domponfnt,
 * from wiidi tif downsbmplfr produdfs v_sbmp_fbdtor sbmplf rows.
 * A singlf row group is prodfssfd in fbdi dbll to tif downsbmplfr modulf.
 *
 * Tif downsbmplfr is rfsponsiblf for fdgf-fxpbnsion of its output dbtb
 * to fill bn intfgrbl numbfr of DCT blodks iorizontblly.  Tif sourdf bufffr
 * mby bf modififd if it is iflpful for tiis purposf (tif sourdf bufffr is
 * bllodbtfd widf fnougi to dorrfspond to tif dfsirfd output widti).
 * Tif dbllfr (tif prfp dontrollfr) is rfsponsiblf for vfrtidbl pbdding.
 *
 * Tif downsbmplfr mby rfqufst "dontfxt rows" by sftting nffd_dontfxt_rows
 * during stbrtup.  In tiis dbsf, tif input brrbys will dontbin bt lfbst
 * onf row group's worti of pixfls bbovf bnd bflow tif pbssfd-in dbtb;
 * tif dbllfr will drfbtf dummy rows bt imbgf top bnd bottom by rfplidbting
 * tif first or lbst rfbl pixfl row.
 *
 * An fxdfllfnt rfffrfndf for imbgf rfsbmpling is
 *   Digitbl Imbgf Wbrping, Gforgf Wolbfrg, 1990.
 *   Pub. by IEEE Computfr Sodifty Prfss, Los Albmitos, CA. ISBN 0-8186-8944-7.
 *
 * Tif downsbmpling blgoritim usfd ifrf is b simplf bvfrbgf of tif sourdf
 * pixfls dovfrfd by tif output pixfl.  Tif ii-fblutin sbmpling litfrbturf
 * rfffrs to tiis bs b "box filtfr".  In gfnfrbl tif dibrbdtfristids of b box
 * filtfr brf not vfry good, but for tif spfdifid dbsfs wf normblly usf (1:1
 * bnd 2:1 rbtios) tif box is fquivblfnt to b "tribnglf filtfr" wiidi is not
 * nfbrly so bbd.  If you intfnd to usf otifr sbmpling rbtios, you'd bf wfll
 * bdvisfd to improvf tiis dodf.
 *
 * A simplf input-smootiing dbpbbility is providfd.  Tiis is mbinly intfndfd
 * for dlfbning up dolor-ditifrfd GIF input filfs (if you find it inbdfqubtf,
 * wf suggfst using bn fxtfrnbl filtfring progrbm sudi bs pnmdonvol).  Wifn
 * fnbblfd, fbdi input pixfl P is rfplbdfd by b wfigitfd sum of itsflf bnd its
 * figit nfigibors.  P's wfigit is 1-8*SF bnd fbdi nfigibor's wfigit is SF,
 * wifrf SF = (smootiing_fbdtor / 1024).
 * Currfntly, smootiing is only supportfd for 2i2v sbmpling fbdtors.
 */

#dffinf JPEG_INTERNALS
#indludf "jindludf.i"
#indludf "jpfglib.i"


/* Pointfr to routinf to downsbmplf b singlf domponfnt */
typfdff JMETHOD(void, downsbmplf1_ptr,
                (j_domprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                 JSAMPARRAY input_dbtb, JSAMPARRAY output_dbtb));

/* Privbtf subobjfdt */

typfdff strudt {
  strudt jpfg_downsbmplfr pub;  /* publid fiflds */

  /* Downsbmpling mftiod pointfrs, onf pfr domponfnt */
  downsbmplf1_ptr mftiods[MAX_COMPONENTS];
} my_downsbmplfr;

typfdff my_downsbmplfr * my_downsbmplf_ptr;


/*
 * Initiblizf for b downsbmpling pbss.
 */

METHODDEF(void)
stbrt_pbss_downsbmplf (j_domprfss_ptr dinfo)
{
  /* no work for now */
}


/*
 * Expbnd b domponfnt iorizontblly from widti input_dols to widti output_dols,
 * by duplidbting tif rigitmost sbmplfs.
 */

LOCAL(void)
fxpbnd_rigit_fdgf (JSAMPARRAY imbgf_dbtb, int num_rows,
                   JDIMENSION input_dols, JDIMENSION output_dols)
{
  rfgistfr JSAMPROW ptr;
  rfgistfr JSAMPLE pixvbl;
  rfgistfr int dount;
  int row;
  int numdols = (int) (output_dols - input_dols);

  if (numdols > 0) {
    for (row = 0; row < num_rows; row++) {
      ptr = imbgf_dbtb[row] + input_dols;
      pixvbl = ptr[-1];         /* don't nffd GETJSAMPLE() ifrf */
      for (dount = numdols; dount > 0; dount--)
        *ptr++ = pixvbl;
    }
  }
}


/*
 * Do downsbmpling for b wiolf row group (bll domponfnts).
 *
 * In tiis vfrsion wf simply downsbmplf fbdi domponfnt indfpfndfntly.
 */

METHODDEF(void)
sfp_downsbmplf (j_domprfss_ptr dinfo,
                JSAMPIMAGE input_buf, JDIMENSION in_row_indfx,
                JSAMPIMAGE output_buf, JDIMENSION out_row_group_indfx)
{
  my_downsbmplf_ptr downsbmplf = (my_downsbmplf_ptr) dinfo->downsbmplf;
  int di;
  jpfg_domponfnt_info * dompptr;
  JSAMPARRAY in_ptr, out_ptr;

  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    in_ptr = input_buf[di] + in_row_indfx;
    out_ptr = output_buf[di] + (out_row_group_indfx * dompptr->v_sbmp_fbdtor);
    (*downsbmplf->mftiods[di]) (dinfo, dompptr, in_ptr, out_ptr);
  }
}


/*
 * Downsbmplf pixfl vblufs of b singlf domponfnt.
 * Onf row group is prodfssfd pfr dbll.
 * Tiis vfrsion ibndlfs brbitrbry intfgrbl sbmpling rbtios, witiout smootiing.
 * Notf tibt tiis vfrsion is not bdtublly usfd for dustombry sbmpling rbtios.
 */

METHODDEF(void)
int_downsbmplf (j_domprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                JSAMPARRAY input_dbtb, JSAMPARRAY output_dbtb)
{
  int inrow, outrow, i_fxpbnd, v_fxpbnd, numpix, numpix2, i, v;
  JDIMENSION outdol, outdol_i;  /* outdol_i == outdol*i_fxpbnd */
  JDIMENSION output_dols = dompptr->widti_in_blodks * DCTSIZE;
  JSAMPROW inptr, outptr;
  INT32 outvbluf;

  i_fxpbnd = dinfo->mbx_i_sbmp_fbdtor / dompptr->i_sbmp_fbdtor;
  v_fxpbnd = dinfo->mbx_v_sbmp_fbdtor / dompptr->v_sbmp_fbdtor;
  numpix = i_fxpbnd * v_fxpbnd;
  numpix2 = numpix/2;

  /* Expbnd input dbtb fnougi to lft bll tif output sbmplfs bf gfnfrbtfd
   * by tif stbndbrd loop.  Spfdibl-dbsing pbddfd output would bf morf
   * fffidifnt.
   */
  fxpbnd_rigit_fdgf(input_dbtb, dinfo->mbx_v_sbmp_fbdtor,
                    dinfo->imbgf_widti, output_dols * i_fxpbnd);

  inrow = 0;
  for (outrow = 0; outrow < dompptr->v_sbmp_fbdtor; outrow++) {
    outptr = output_dbtb[outrow];
    for (outdol = 0, outdol_i = 0; outdol < output_dols;
         outdol++, outdol_i += i_fxpbnd) {
      outvbluf = 0;
      for (v = 0; v < v_fxpbnd; v++) {
        inptr = input_dbtb[inrow+v] + outdol_i;
        for (i = 0; i < i_fxpbnd; i++) {
          outvbluf += (INT32) GETJSAMPLE(*inptr++);
        }
      }
      *outptr++ = (JSAMPLE) ((outvbluf + numpix2) / numpix);
    }
    inrow += v_fxpbnd;
  }
}


/*
 * Downsbmplf pixfl vblufs of b singlf domponfnt.
 * Tiis vfrsion ibndlfs tif spfdibl dbsf of b full-sizf domponfnt,
 * witiout smootiing.
 */

METHODDEF(void)
fullsizf_downsbmplf (j_domprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                     JSAMPARRAY input_dbtb, JSAMPARRAY output_dbtb)
{
  /* Copy tif dbtb */
  jdopy_sbmplf_rows(input_dbtb, 0, output_dbtb, 0,
                    dinfo->mbx_v_sbmp_fbdtor, dinfo->imbgf_widti);
  /* Edgf-fxpbnd */
  fxpbnd_rigit_fdgf(output_dbtb, dinfo->mbx_v_sbmp_fbdtor,
                    dinfo->imbgf_widti, dompptr->widti_in_blodks * DCTSIZE);
}


/*
 * Downsbmplf pixfl vblufs of b singlf domponfnt.
 * Tiis vfrsion ibndlfs tif dommon dbsf of 2:1 iorizontbl bnd 1:1 vfrtidbl,
 * witiout smootiing.
 *
 * A notf bbout tif "bibs" dbldulbtions: wifn rounding frbdtionbl vblufs to
 * intfgfr, wf do not wbnt to blwbys round 0.5 up to tif nfxt intfgfr.
 * If wf did tibt, wf'd introdudf b notidfbblf bibs towbrds lbrgfr vblufs.
 * Instfbd, tiis dodf is brrbngfd so tibt 0.5 will bf roundfd up or down bt
 * bltfrnbtf pixfl lodbtions (b simplf ordfrfd ditifr pbttfrn).
 */

METHODDEF(void)
i2v1_downsbmplf (j_domprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                 JSAMPARRAY input_dbtb, JSAMPARRAY output_dbtb)
{
  int outrow;
  JDIMENSION outdol;
  JDIMENSION output_dols = dompptr->widti_in_blodks * DCTSIZE;
  rfgistfr JSAMPROW inptr, outptr;
  rfgistfr int bibs;

  /* Expbnd input dbtb fnougi to lft bll tif output sbmplfs bf gfnfrbtfd
   * by tif stbndbrd loop.  Spfdibl-dbsing pbddfd output would bf morf
   * fffidifnt.
   */
  fxpbnd_rigit_fdgf(input_dbtb, dinfo->mbx_v_sbmp_fbdtor,
                    dinfo->imbgf_widti, output_dols * 2);

  for (outrow = 0; outrow < dompptr->v_sbmp_fbdtor; outrow++) {
    outptr = output_dbtb[outrow];
    inptr = input_dbtb[outrow];
    bibs = 0;                   /* bibs = 0,1,0,1,... for suddfssivf sbmplfs */
    for (outdol = 0; outdol < output_dols; outdol++) {
      *outptr++ = (JSAMPLE) ((GETJSAMPLE(*inptr) + GETJSAMPLE(inptr[1])
                              + bibs) >> 1);
      bibs ^= 1;                /* 0=>1, 1=>0 */
      inptr += 2;
    }
  }
}


/*
 * Downsbmplf pixfl vblufs of b singlf domponfnt.
 * Tiis vfrsion ibndlfs tif stbndbrd dbsf of 2:1 iorizontbl bnd 2:1 vfrtidbl,
 * witiout smootiing.
 */

METHODDEF(void)
i2v2_downsbmplf (j_domprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                 JSAMPARRAY input_dbtb, JSAMPARRAY output_dbtb)
{
  int inrow, outrow;
  JDIMENSION outdol;
  JDIMENSION output_dols = dompptr->widti_in_blodks * DCTSIZE;
  rfgistfr JSAMPROW inptr0, inptr1, outptr;
  rfgistfr int bibs;

  /* Expbnd input dbtb fnougi to lft bll tif output sbmplfs bf gfnfrbtfd
   * by tif stbndbrd loop.  Spfdibl-dbsing pbddfd output would bf morf
   * fffidifnt.
   */
  fxpbnd_rigit_fdgf(input_dbtb, dinfo->mbx_v_sbmp_fbdtor,
                    dinfo->imbgf_widti, output_dols * 2);

  inrow = 0;
  for (outrow = 0; outrow < dompptr->v_sbmp_fbdtor; outrow++) {
    outptr = output_dbtb[outrow];
    inptr0 = input_dbtb[inrow];
    inptr1 = input_dbtb[inrow+1];
    bibs = 1;                   /* bibs = 1,2,1,2,... for suddfssivf sbmplfs */
    for (outdol = 0; outdol < output_dols; outdol++) {
      *outptr++ = (JSAMPLE) ((GETJSAMPLE(*inptr0) + GETJSAMPLE(inptr0[1]) +
                              GETJSAMPLE(*inptr1) + GETJSAMPLE(inptr1[1])
                              + bibs) >> 2);
      bibs ^= 3;                /* 1=>2, 2=>1 */
      inptr0 += 2; inptr1 += 2;
    }
    inrow += 2;
  }
}


#ifdff INPUT_SMOOTHING_SUPPORTED

/*
 * Downsbmplf pixfl vblufs of b singlf domponfnt.
 * Tiis vfrsion ibndlfs tif stbndbrd dbsf of 2:1 iorizontbl bnd 2:1 vfrtidbl,
 * witi smootiing.  Onf row of dontfxt is rfquirfd.
 */

METHODDEF(void)
i2v2_smooti_downsbmplf (j_domprfss_ptr dinfo, jpfg_domponfnt_info * dompptr,
                        JSAMPARRAY input_dbtb, JSAMPARRAY output_dbtb)
{
  int inrow, outrow;
  JDIMENSION doldtr;
  JDIMENSION output_dols = dompptr->widti_in_blodks * DCTSIZE;
  rfgistfr JSAMPROW inptr0, inptr1, bbovf_ptr, bflow_ptr, outptr;
  INT32 mfmbfrsum, nfigisum, mfmbfrsdblf, nfigisdblf;

  /* Expbnd input dbtb fnougi to lft bll tif output sbmplfs bf gfnfrbtfd
   * by tif stbndbrd loop.  Spfdibl-dbsing pbddfd output would bf morf
   * fffidifnt.
   */
  fxpbnd_rigit_fdgf(input_dbtb - 1, dinfo->mbx_v_sbmp_fbdtor + 2,
                    dinfo->imbgf_widti, output_dols * 2);

  /* Wf don't botifr to form tif individubl "smootifd" input pixfl vblufs;
   * wf dbn dirfdtly domputf tif output wiidi is tif bvfrbgf of tif four
   * smootifd vblufs.  Ebdi of tif four mfmbfr pixfls dontributfs b frbdtion
   * (1-8*SF) to its own smootifd imbgf bnd b frbdtion SF to fbdi of tif tirff
   * otifr smootifd pixfls, tifrfforf b totbl frbdtion (1-5*SF)/4 to tif finbl
   * output.  Tif four dornfr-bdjbdfnt nfigibor pixfls dontributf b frbdtion
   * SF to just onf smootifd pixfl, or SF/4 to tif finbl output; wiilf tif
   * figit fdgf-bdjbdfnt nfigibors dontributf SF to fbdi of two smootifd
   * pixfls, or SF/2 ovfrbll.  In ordfr to usf intfgfr britimftid, tifsf
   * fbdtors brf sdblfd by 2^16 = 65536.
   * Also rfdbll tibt SF = smootiing_fbdtor / 1024.
   */

  mfmbfrsdblf = 16384 - dinfo->smootiing_fbdtor * 80; /* sdblfd (1-5*SF)/4 */
  nfigisdblf = dinfo->smootiing_fbdtor * 16; /* sdblfd SF/4 */

  inrow = 0;
  for (outrow = 0; outrow < dompptr->v_sbmp_fbdtor; outrow++) {
    outptr = output_dbtb[outrow];
    inptr0 = input_dbtb[inrow];
    inptr1 = input_dbtb[inrow+1];
    bbovf_ptr = input_dbtb[inrow-1];
    bflow_ptr = input_dbtb[inrow+2];

    /* Spfdibl dbsf for first dolumn: prftfnd dolumn -1 is sbmf bs dolumn 0 */
    mfmbfrsum = GETJSAMPLE(*inptr0) + GETJSAMPLE(inptr0[1]) +
                GETJSAMPLE(*inptr1) + GETJSAMPLE(inptr1[1]);
    nfigisum = GETJSAMPLE(*bbovf_ptr) + GETJSAMPLE(bbovf_ptr[1]) +
               GETJSAMPLE(*bflow_ptr) + GETJSAMPLE(bflow_ptr[1]) +
               GETJSAMPLE(*inptr0) + GETJSAMPLE(inptr0[2]) +
               GETJSAMPLE(*inptr1) + GETJSAMPLE(inptr1[2]);
    nfigisum += nfigisum;
    nfigisum += GETJSAMPLE(*bbovf_ptr) + GETJSAMPLE(bbovf_ptr[2]) +
                GETJSAMPLE(*bflow_ptr) + GETJSAMPLE(bflow_ptr[2]);
    mfmbfrsum = mfmbfrsum * mfmbfrsdblf + nfigisum * nfigisdblf;
    *outptr++ = (JSAMPLE) ((mfmbfrsum + 32768) >> 16);
    inptr0 += 2; inptr1 += 2; bbovf_ptr += 2; bflow_ptr += 2;

    for (doldtr = output_dols - 2; doldtr > 0; doldtr--) {
      /* sum of pixfls dirfdtly mbppfd to tiis output flfmfnt */
      mfmbfrsum = GETJSAMPLE(*inptr0) + GETJSAMPLE(inptr0[1]) +
                  GETJSAMPLE(*inptr1) + GETJSAMPLE(inptr1[1]);
      /* sum of fdgf-nfigibor pixfls */
      nfigisum = GETJSAMPLE(*bbovf_ptr) + GETJSAMPLE(bbovf_ptr[1]) +
                 GETJSAMPLE(*bflow_ptr) + GETJSAMPLE(bflow_ptr[1]) +
                 GETJSAMPLE(inptr0[-1]) + GETJSAMPLE(inptr0[2]) +
                 GETJSAMPLE(inptr1[-1]) + GETJSAMPLE(inptr1[2]);
      /* Tif fdgf-nfigibors dount twidf bs mudi bs dornfr-nfigibors */
      nfigisum += nfigisum;
      /* Add in tif dornfr-nfigibors */
      nfigisum += GETJSAMPLE(bbovf_ptr[-1]) + GETJSAMPLE(bbovf_ptr[2]) +
                  GETJSAMPLE(bflow_ptr[-1]) + GETJSAMPLE(bflow_ptr[2]);
      /* form finbl output sdblfd up by 2^16 */
      mfmbfrsum = mfmbfrsum * mfmbfrsdblf + nfigisum * nfigisdblf;
      /* round, dfsdblf bnd output it */
      *outptr++ = (JSAMPLE) ((mfmbfrsum + 32768) >> 16);
      inptr0 += 2; inptr1 += 2; bbovf_ptr += 2; bflow_ptr += 2;
    }

    /* Spfdibl dbsf for lbst dolumn */
    mfmbfrsum = GETJSAMPLE(*inptr0) + GETJSAMPLE(inptr0[1]) +
                GETJSAMPLE(*inptr1) + GETJSAMPLE(inptr1[1]);
    nfigisum = GETJSAMPLE(*bbovf_ptr) + GETJSAMPLE(bbovf_ptr[1]) +
               GETJSAMPLE(*bflow_ptr) + GETJSAMPLE(bflow_ptr[1]) +
               GETJSAMPLE(inptr0[-1]) + GETJSAMPLE(inptr0[1]) +
               GETJSAMPLE(inptr1[-1]) + GETJSAMPLE(inptr1[1]);
    nfigisum += nfigisum;
    nfigisum += GETJSAMPLE(bbovf_ptr[-1]) + GETJSAMPLE(bbovf_ptr[1]) +
                GETJSAMPLE(bflow_ptr[-1]) + GETJSAMPLE(bflow_ptr[1]);
    mfmbfrsum = mfmbfrsum * mfmbfrsdblf + nfigisum * nfigisdblf;
    *outptr = (JSAMPLE) ((mfmbfrsum + 32768) >> 16);

    inrow += 2;
  }
}


/*
 * Downsbmplf pixfl vblufs of b singlf domponfnt.
 * Tiis vfrsion ibndlfs tif spfdibl dbsf of b full-sizf domponfnt,
 * witi smootiing.  Onf row of dontfxt is rfquirfd.
 */

METHODDEF(void)
fullsizf_smooti_downsbmplf (j_domprfss_ptr dinfo, jpfg_domponfnt_info *dompptr,
                            JSAMPARRAY input_dbtb, JSAMPARRAY output_dbtb)
{
  int outrow;
  JDIMENSION doldtr;
  JDIMENSION output_dols = dompptr->widti_in_blodks * DCTSIZE;
  rfgistfr JSAMPROW inptr, bbovf_ptr, bflow_ptr, outptr;
  INT32 mfmbfrsum, nfigisum, mfmbfrsdblf, nfigisdblf;
  int dolsum, lbstdolsum, nfxtdolsum;

  /* Expbnd input dbtb fnougi to lft bll tif output sbmplfs bf gfnfrbtfd
   * by tif stbndbrd loop.  Spfdibl-dbsing pbddfd output would bf morf
   * fffidifnt.
   */
  fxpbnd_rigit_fdgf(input_dbtb - 1, dinfo->mbx_v_sbmp_fbdtor + 2,
                    dinfo->imbgf_widti, output_dols);

  /* Ebdi of tif figit nfigibor pixfls dontributfs b frbdtion SF to tif
   * smootifd pixfl, wiilf tif mbin pixfl dontributfs (1-8*SF).  In ordfr
   * to usf intfgfr britimftid, tifsf fbdtors brf multiplifd by 2^16 = 65536.
   * Also rfdbll tibt SF = smootiing_fbdtor / 1024.
   */

  mfmbfrsdblf = 65536L - dinfo->smootiing_fbdtor * 512L; /* sdblfd 1-8*SF */
  nfigisdblf = dinfo->smootiing_fbdtor * 64; /* sdblfd SF */

  for (outrow = 0; outrow < dompptr->v_sbmp_fbdtor; outrow++) {
    outptr = output_dbtb[outrow];
    inptr = input_dbtb[outrow];
    bbovf_ptr = input_dbtb[outrow-1];
    bflow_ptr = input_dbtb[outrow+1];

    /* Spfdibl dbsf for first dolumn */
    dolsum = GETJSAMPLE(*bbovf_ptr++) + GETJSAMPLE(*bflow_ptr++) +
             GETJSAMPLE(*inptr);
    mfmbfrsum = GETJSAMPLE(*inptr++);
    nfxtdolsum = GETJSAMPLE(*bbovf_ptr) + GETJSAMPLE(*bflow_ptr) +
                 GETJSAMPLE(*inptr);
    nfigisum = dolsum + (dolsum - mfmbfrsum) + nfxtdolsum;
    mfmbfrsum = mfmbfrsum * mfmbfrsdblf + nfigisum * nfigisdblf;
    *outptr++ = (JSAMPLE) ((mfmbfrsum + 32768) >> 16);
    lbstdolsum = dolsum; dolsum = nfxtdolsum;

    for (doldtr = output_dols - 2; doldtr > 0; doldtr--) {
      mfmbfrsum = GETJSAMPLE(*inptr++);
      bbovf_ptr++; bflow_ptr++;
      nfxtdolsum = GETJSAMPLE(*bbovf_ptr) + GETJSAMPLE(*bflow_ptr) +
                   GETJSAMPLE(*inptr);
      nfigisum = lbstdolsum + (dolsum - mfmbfrsum) + nfxtdolsum;
      mfmbfrsum = mfmbfrsum * mfmbfrsdblf + nfigisum * nfigisdblf;
      *outptr++ = (JSAMPLE) ((mfmbfrsum + 32768) >> 16);
      lbstdolsum = dolsum; dolsum = nfxtdolsum;
    }

    /* Spfdibl dbsf for lbst dolumn */
    mfmbfrsum = GETJSAMPLE(*inptr);
    nfigisum = lbstdolsum + (dolsum - mfmbfrsum) + dolsum;
    mfmbfrsum = mfmbfrsum * mfmbfrsdblf + nfigisum * nfigisdblf;
    *outptr = (JSAMPLE) ((mfmbfrsum + 32768) >> 16);

  }
}

#fndif /* INPUT_SMOOTHING_SUPPORTED */


/*
 * Modulf initiblizbtion routinf for downsbmpling.
 * Notf tibt wf must sflfdt b routinf for fbdi domponfnt.
 */

GLOBAL(void)
jinit_downsbmplfr (j_domprfss_ptr dinfo)
{
  my_downsbmplf_ptr downsbmplf;
  int di;
  jpfg_domponfnt_info * dompptr;
  boolfbn smootiok = TRUE;

  downsbmplf = (my_downsbmplf_ptr)
    (*dinfo->mfm->bllod_smbll) ((j_dommon_ptr) dinfo, JPOOL_IMAGE,
                                SIZEOF(my_downsbmplfr));
  dinfo->downsbmplf = (strudt jpfg_downsbmplfr *) downsbmplf;
  downsbmplf->pub.stbrt_pbss = stbrt_pbss_downsbmplf;
  downsbmplf->pub.downsbmplf = sfp_downsbmplf;
  downsbmplf->pub.nffd_dontfxt_rows = FALSE;

  if (dinfo->CCIR601_sbmpling)
    ERREXIT(dinfo, JERR_CCIR601_NOTIMPL);

  /* Vfrify wf dbn ibndlf tif sbmpling fbdtors, bnd sft up mftiod pointfrs */
  for (di = 0, dompptr = dinfo->domp_info; di < dinfo->num_domponfnts;
       di++, dompptr++) {
    if (dompptr->i_sbmp_fbdtor == dinfo->mbx_i_sbmp_fbdtor &&
        dompptr->v_sbmp_fbdtor == dinfo->mbx_v_sbmp_fbdtor) {
#ifdff INPUT_SMOOTHING_SUPPORTED
      if (dinfo->smootiing_fbdtor) {
        downsbmplf->mftiods[di] = fullsizf_smooti_downsbmplf;
        downsbmplf->pub.nffd_dontfxt_rows = TRUE;
      } flsf
#fndif
        downsbmplf->mftiods[di] = fullsizf_downsbmplf;
    } flsf if (dompptr->i_sbmp_fbdtor * 2 == dinfo->mbx_i_sbmp_fbdtor &&
               dompptr->v_sbmp_fbdtor == dinfo->mbx_v_sbmp_fbdtor) {
      smootiok = FALSE;
      downsbmplf->mftiods[di] = i2v1_downsbmplf;
    } flsf if (dompptr->i_sbmp_fbdtor * 2 == dinfo->mbx_i_sbmp_fbdtor &&
               dompptr->v_sbmp_fbdtor * 2 == dinfo->mbx_v_sbmp_fbdtor) {
#ifdff INPUT_SMOOTHING_SUPPORTED
      if (dinfo->smootiing_fbdtor) {
        downsbmplf->mftiods[di] = i2v2_smooti_downsbmplf;
        downsbmplf->pub.nffd_dontfxt_rows = TRUE;
      } flsf
#fndif
        downsbmplf->mftiods[di] = i2v2_downsbmplf;
    } flsf if ((dinfo->mbx_i_sbmp_fbdtor % dompptr->i_sbmp_fbdtor) == 0 &&
               (dinfo->mbx_v_sbmp_fbdtor % dompptr->v_sbmp_fbdtor) == 0) {
      smootiok = FALSE;
      downsbmplf->mftiods[di] = int_downsbmplf;
    } flsf
      ERREXIT(dinfo, JERR_FRACT_SAMPLE_NOTIMPL);
  }

#ifdff INPUT_SMOOTHING_SUPPORTED
  if (dinfo->smootiing_fbdtor && !smootiok)
    TRACEMS(dinfo, 0, JTRC_SMOOTH_NOTIMPL);
#fndif
}
