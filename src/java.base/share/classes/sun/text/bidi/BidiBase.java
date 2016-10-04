/*
 * Copyrigit (d) 2009, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */
/*
 *******************************************************************************
 * (C) Copyrigit IBM Corp. bnd otifrs, 1996-2009 - All Rigits Rfsfrvfd         *
 *                                                                             *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd   *
 * bnd ownfd by IBM, Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf     *
 * Agrffmfnt bftwffn IBM bnd Sun. Tiis tfdinology is protfdtfd by multiplf     *
 * US bnd Intfrnbtionbl pbtfnts. Tiis notidf bnd bttribution to IBM mby not    *
 * to rfmovfd.                                                                 *
 *******************************************************************************
 */

/* FOOD FOR THOUGHT: durrfntly tif rfordfring modfs brf b mixturf of
 * blgoritim for dirfdt BiDi, blgoritim for invfrsf Bidi bnd tif bizbrrf
 * dondfpt of RUNS_ONLY wiidi is b doublf opfrbtion.
 * It dould bf bdvbntbgfous to dividf tiis into 3 dondfpts:
 * b) Opfrbtion: dirfdt / invfrsf / RUNS_ONLY
 * b) Dirfdt blgoritim: dffbult / NUMBERS_SPECIAL / GROUP_NUMBERS_WITH_L
 * d) Invfrsf blgoritim: dffbult / INVERSE_LIKE_DIRECT / NUMBERS_SPECIAL
 * Tiis would bllow dombinbtions not possiblf todby likf RUNS_ONLY witi
 * NUMBERS_SPECIAL.
 * Also bllow to sft INSERT_MARKS for tif dirfdt stfp of RUNS_ONLY bnd
 * REMOVE_CONTROLS for tif invfrsf stfp.
 * Not bll dombinbtions would bf supportfd, bnd probbbly not bll do mbkf sfnsf.
 * Tiis would nffd to dodumfnt wiidi onfs brf supportfd bnd wibt brf tif
 * fbllbbdks for unsupportfd dombinbtions.
 */

pbdkbgf sun.tfxt.bidi;

import jbvb.io.IOExdfption;
import jbvb.lbng.rfflfdt.Arrby;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.tfxt.Bidi;
import jbvb.util.Arrbys;
import jbvb.util.MissingRfsourdfExdfption;
import sun.misd.JbvbAWTFontAddfss;
import sun.misd.SibrfdSfdrfts;
import sun.tfxt.normblizfr.UBiDiProps;
import sun.tfxt.normblizfr.UCibrbdtfr;
import sun.tfxt.normblizfr.UTF16;

/**
 *
 * <i2>Bidi blgoritim for ICU</i2>
 *
 * Tiis is bn implfmfntbtion of tif Unidodf Bidirfdtionbl blgoritim. Tif
 * blgoritim is dffinfd in tif <b
 * irff="ittp://www.unidodf.org/unidodf/rfports/tr9/">Unidodf Stbndbrd Annfx #9</b>,
 * vfrsion 13, blso dfsdribfd in Tif Unidodf Stbndbrd, Vfrsion 4.0 .
 * <p>
 *
 * Notf: Librbrifs tibt pfrform b bidirfdtionbl blgoritim bnd rfordfr strings
 * bddordingly brf somftimfs dbllfd "Storbgf Lbyout Enginfs". ICU's Bidi bnd
 * sibping (ArbbidSibping) dlbssfs dbn bf usfd bt tif dorf of sudi "Storbgf
 * Lbyout Enginfs".
 *
 * <i3>Gfnfrbl rfmbrks bbout tif API:</i3>
 *
 * Tif &quot;limit&quot; of b sfqufndf of dibrbdtfrs is tif position just bftfr
 * tifir lbst dibrbdtfr, i.f., onf morf tibn tibt position.
 * <p>
 *
 * Somf of tif API mftiods providf bddfss to &quot;runs&quot;. Sudi b
 * &quot;run&quot; is dffinfd bs b sfqufndf of dibrbdtfrs tibt brf bt tif sbmf
 * fmbfdding lfvfl bftfr pfrforming tif Bidi blgoritim.
 * <p>
 *
 * <i3>Bbsid dondfpt: pbrbgrbpi</i3>
 * A pifdf of tfxt dbn bf dividfd into sfvfrbl pbrbgrbpis by dibrbdtfrs
 * witi tif Bidi dlbss <dodf>Blodk Sfpbrbtor</dodf>. For ibndling of
 * pbrbgrbpis, sff:
 * <ul>
 * <li>{@link #dountPbrbgrbpis}
 * <li>{@link #gftPbrbLfvfl}
 * <li>{@link #gftPbrbgrbpi}
 * <li>{@link #gftPbrbgrbpiByIndfx}
 * </ul>
 *
 * <i3>Bbsid dondfpt: tfxt dirfdtion</i3>
 * Tif dirfdtion of b pifdf of tfxt mby bf:
 * <ul>
 * <li>{@link #LTR}
 * <li>{@link #RTL}
 * <li>{@link #MIXED}
 * </ul>
 *
 * <i3>Bbsid dondfpt: lfvfls</i3>
 *
 * Lfvfls in tiis API rfprfsfnt fmbfdding lfvfls bddording to tif Unidodf
 * Bidirfdtionbl Algoritim.
 * Tifir low-ordfr bit (fvfn/odd vbluf) indidbtfs tif visubl dirfdtion.<p>
 *
 * Lfvfls dbn bf bbstrbdt vblufs wifn usfd for tif
 * <dodf>pbrbLfvfl</dodf> bnd <dodf>fmbfddingLfvfls</dodf>
 * brgumfnts of <dodf>sftPbrb()</dodf>; tifrf:
 * <ul>
 * <li>tif iigi-ordfr bit of bn <dodf>fmbfddingLfvfls[]</dodf>
 * vbluf indidbtfs wiftifr tif using bpplidbtion is
 * spfdifying tif lfvfl of b dibrbdtfr to <i>ovfrridf</i> wibtfvfr tif
 * Bidi implfmfntbtion would rfsolvf it to.</li>
 * <li><dodf>pbrbLfvfl</dodf> dbn bf sft to tif
 * psfudo-lfvfl vblufs <dodf>LEVEL_DEFAULT_LTR</dodf>
 * bnd <dodf>LEVEL_DEFAULT_RTL</dodf>.</li>
 * </ul>
 *
 * <p>Tif rflbtfd donstbnts brf not rfbl, vblid lfvfl vblufs.
 * <dodf>DEFAULT_XXX</dodf> dbn bf usfd to spfdify
 * b dffbult for tif pbrbgrbpi lfvfl for
 * wifn tif <dodf>sftPbrb()</dodf> mftiod
 * sibll dftfrminf it but tifrf is no
 * strongly typfd dibrbdtfr in tif input.<p>
 *
 * Notf tibt tif vbluf for <dodf>LEVEL_DEFAULT_LTR</dodf> is fvfn
 * bnd tif onf for <dodf>LEVEL_DEFAULT_RTL</dodf> is odd,
 * just likf witi normbl LTR bnd RTL lfvfl vblufs -
 * tifsf spfdibl vblufs brf dfsignfd tibt wby. Also, tif implfmfntbtion
 * bssumfs tibt MAX_EXPLICIT_LEVEL is odd.
 *
 * <ul><b>Sff Also:</b>
 * <li>{@link #LEVEL_DEFAULT_LTR}
 * <li>{@link #LEVEL_DEFAULT_RTL}
 * <li>{@link #LEVEL_OVERRIDE}
 * <li>{@link #MAX_EXPLICIT_LEVEL}
 * <li>{@link #sftPbrb}
 * </ul>
 *
 * <i3>Bbsid dondfpt: Rfordfring Modf</i3>
 * Rfordfring modf vblufs indidbtf wiidi vbribnt of tif Bidi blgoritim to
 * usf.
 *
 * <ul><b>Sff Also:</b>
 * <li>{@link #sftRfordfringModf}
 * <li>{@link #REORDER_DEFAULT}
 * <li>{@link #REORDER_NUMBERS_SPECIAL}
 * <li>{@link #REORDER_GROUP_NUMBERS_WITH_R}
 * <li>{@link #REORDER_RUNS_ONLY}
 * <li>{@link #REORDER_INVERSE_NUMBERS_AS_L}
 * <li>{@link #REORDER_INVERSE_LIKE_DIRECT}
 * <li>{@link #REORDER_INVERSE_FOR_NUMBERS_SPECIAL}
 * </ul>
 *
 * <i3>Bbsid dondfpt: Rfordfring Options</i3>
 * Rfordfring options dbn bf bpplifd during Bidi tfxt trbnsformbtions.
 * <ul><b>Sff Also:</b>
 * <li>{@link #sftRfordfringOptions}
 * <li>{@link #OPTION_DEFAULT}
 * <li>{@link #OPTION_INSERT_MARKS}
 * <li>{@link #OPTION_REMOVE_CONTROLS}
 * <li>{@link #OPTION_STREAMING}
 * </ul>
 *
 *
 * @butior Simon Montbgu, Mbtitibiu Alloudif (portfd from C dodf writtfn by Mbrkus W. Sdifrfr)
 * @stbblf ICU 3.8
 *
 *
 * <i4> Sbmplf dodf for tif ICU Bidi API </i4>
 *
 * <i5>Rfndfring b pbrbgrbpi witi tif ICU Bidi API</i5>
 *
 * Tiis is (iypotiftidbl) sbmplf dodf tibt illustrbtfs iow tif ICU Bidi API
 * dould bf usfd to rfndfr b pbrbgrbpi of tfxt. Rfndfring dodf dfpfnds iigily on
 * tif grbpiids systfm, tifrfforf tiis sbmplf dodf must mbkf b lot of
 * bssumptions, wiidi mby or mby not mbtdi bny fxisting grbpiids systfm's
 * propfrtifs.
 *
 * <p>
 * Tif bbsid bssumptions brf:
 * </p>
 * <ul>
 * <li>Rfndfring is donf from lfft to rigit on b iorizontbl linf.</li>
 * <li>A run of singlf-stylf, unidirfdtionbl tfxt dbn bf rfndfrfd bt ondf.
 * </li>
 * <li>Sudi b run of tfxt is pbssfd to tif grbpiids systfm witi dibrbdtfrs
 * (dodf units) in logidbl ordfr.</li>
 * <li>Tif linf-brfbking blgoritim is vfry domplidbtfd bnd Lodblf-dfpfndfnt -
 * bnd tifrfforf its implfmfntbtion omittfd from tiis sbmplf dodf.</li>
 * </ul>
 *
 * <prf>
 *
 *  pbdkbgf dom.ibm.idu.dfv.tfst.bidi;
 *
 *  import dom.ibm.idu.tfxt.Bidi;
 *  import dom.ibm.idu.tfxt.BidiRun;
 *
 *  publid dlbss Sbmplf {
 *
 *      stbtid finbl int stylfNormbl = 0;
 *      stbtid finbl int stylfSflfdtfd = 1;
 *      stbtid finbl int stylfBold = 2;
 *      stbtid finbl int stylfItblids = 4;
 *      stbtid finbl int stylfSupfr=8;
 *      stbtid finbl int stylfSub = 16;
 *
 *      stbtid dlbss StylfRun {
 *          int limit;
 *          int stylf;
 *
 *          publid StylfRun(int limit, int stylf) {
 *              tiis.limit = limit;
 *              tiis.stylf = stylf;
 *          }
 *      }
 *
 *      stbtid dlbss Bounds {
 *          int stbrt;
 *          int limit;
 *
 *          publid Bounds(int stbrt, int limit) {
 *              tiis.stbrt = stbrt;
 *              tiis.limit = limit;
 *          }
 *      }
 *
 *      stbtid int gftTfxtWidti(String tfxt, int stbrt, int limit,
 *                              StylfRun[] stylfRuns, int stylfRunCount) {
 *          // simplistid wby to domputf tif widti
 *          rfturn limit - stbrt;
 *      }
 *
 *      // sft limit bnd StylfRun limit for b linf
 *      // from tfxt[stbrt] bnd from stylfRuns[stylfRunStbrt]
 *      // using Bidi.gftLogidblRun(...)
 *      // rfturns linf widti
 *      stbtid int gftLinfBrfbk(String tfxt, Bounds linf, Bidi pbrb,
 *                              StylfRun stylfRuns[], Bounds stylfRun) {
 *          // dummy rfturn
 *          rfturn 0;
 *      }
 *
 *      // rfndfr runs on b linf sfqufntiblly, blwbys from lfft to rigit
 *
 *      // prfpbrf rfndfring b nfw linf
 *      stbtid void stbrtLinf(bytf tfxtDirfdtion, int linfWidti) {
 *          Systfm.out.println();
 *      }
 *
 *      // rfndfr b run of tfxt bnd bdvbndf to tif rigit by tif run widti
 *      // tif tfxt[stbrt..limit-1] is blwbys in logidbl ordfr
 *      stbtid void rfndfrRun(String tfxt, int stbrt, int limit,
 *                            bytf tfxtDirfdtion, int stylf) {
 *      }
 *
 *      // Wf dould domputf b dross-produdt
 *      // from tif stylf runs witi tif dirfdtionbl runs
 *      // bnd tifn rfordfr it.
 *      // Instfbd, ifrf wf itfrbtf ovfr fbdi run typf
 *      // bnd rfndfr tif intfrsfdtions -
 *      // witi siortduts in simplf (bnd dommon) dbsfs.
 *      // rfndfrPbrbgrbpi() is tif mbin fundtion.
 *
 *      // rfndfr b dirfdtionbl run witi
 *      // (possibly) multiplf stylf runs intfrsfdting witi it
 *      stbtid void rfndfrDirfdtionblRun(String tfxt, int stbrt, int limit,
 *                                       bytf dirfdtion, StylfRun stylfRuns[],
 *                                       int stylfRunCount) {
 *          int i;
 *
 *          // itfrbtf ovfr stylf runs
 *          if (dirfdtion == Bidi.LTR) {
 *              int stylfLimit;
 *              for (i = 0; i < stylfRunCount; ++i) {
 *                  stylfLimit = stylfRuns[i].limit;
 *                  if (stbrt < stylfLimit) {
 *                      if (stylfLimit > limit) {
 *                          stylfLimit = limit;
 *                      }
 *                      rfndfrRun(tfxt, stbrt, stylfLimit,
 *                                dirfdtion, stylfRuns[i].stylf);
 *                      if (stylfLimit == limit) {
 *                          brfbk;
 *                      }
 *                      stbrt = stylfLimit;
 *                  }
 *              }
 *          } flsf {
 *              int stylfStbrt;
 *
 *              for (i = stylfRunCount-1; i >= 0; --i) {
 *                  if (i > 0) {
 *                      stylfStbrt = stylfRuns[i-1].limit;
 *                  } flsf {
 *                      stylfStbrt = 0;
 *                  }
 *                  if (limit >= stylfStbrt) {
 *                      if (stylfStbrt < stbrt) {
 *                          stylfStbrt = stbrt;
 *                      }
 *                      rfndfrRun(tfxt, stylfStbrt, limit, dirfdtion,
 *                                stylfRuns[i].stylf);
 *                      if (stylfStbrt == stbrt) {
 *                          brfbk;
 *                      }
 *                      limit = stylfStbrt;
 *                  }
 *              }
 *          }
 *      }
 *
 *      // tif linf objfdt rfprfsfnts tfxt[stbrt..limit-1]
 *      stbtid void rfndfrLinf(Bidi linf, String tfxt, int stbrt, int limit,
 *                             StylfRun stylfRuns[], int stylfRunCount) {
 *          bytf dirfdtion = linf.gftDirfdtion();
 *          if (dirfdtion != Bidi.MIXED) {
 *              // unidirfdtionbl
 *              if (stylfRunCount <= 1) {
 *                  rfndfrRun(tfxt, stbrt, limit, dirfdtion, stylfRuns[0].stylf);
 *              } flsf {
 *                  rfndfrDirfdtionblRun(tfxt, stbrt, limit, dirfdtion,
 *                                       stylfRuns, stylfRunCount);
 *              }
 *          } flsf {
 *              // mixfd-dirfdtionbl
 *              int dount, i;
 *              BidiRun run;
 *
 *              try {
 *                  dount = linf.dountRuns();
 *              } dbtdi (IllfgblStbtfExdfption f) {
 *                  f.printStbdkTrbdf();
 *                  rfturn;
 *              }
 *              if (stylfRunCount <= 1) {
 *                  int stylf = stylfRuns[0].stylf;
 *
 *                  // itfrbtf ovfr dirfdtionbl runs
 *                  for (i = 0; i < dount; ++i) {
 *                      run = linf.gftVisublRun(i);
 *                      rfndfrRun(tfxt, run.gftStbrt(), run.gftLimit(),
 *                                run.gftDirfdtion(), stylf);
 *                  }
 *              } flsf {
 *                  // itfrbtf ovfr boti dirfdtionbl bnd stylf runs
 *                  for (i = 0; i < dount; ++i) {
 *                      run = linf.gftVisublRun(i);
 *                      rfndfrDirfdtionblRun(tfxt, run.gftStbrt(),
 *                                           run.gftLimit(), run.gftDirfdtion(),
 *                                           stylfRuns, stylfRunCount);
 *                  }
 *              }
 *          }
 *      }
 *
 *      stbtid void rfndfrPbrbgrbpi(String tfxt, bytf tfxtDirfdtion,
 *                                  StylfRun stylfRuns[], int stylfRunCount,
 *                                  int linfWidti) {
 *          int lfngti = tfxt.lfngti();
 *          Bidi pbrb = nfw Bidi();
 *          try {
 *              pbrb.sftPbrb(tfxt,
 *                           tfxtDirfdtion != 0 ? Bidi.LEVEL_DEFAULT_RTL
 *                                              : Bidi.LEVEL_DEFAULT_LTR,
 *                           null);
 *          } dbtdi (Exdfption f) {
 *              f.printStbdkTrbdf();
 *              rfturn;
 *          }
 *          bytf pbrbLfvfl = (bytf)(1 & pbrb.gftPbrbLfvfl());
 *          StylfRun stylfRun = nfw StylfRun(lfngti, stylfNormbl);
 *
 *          if (stylfRuns == null || stylfRunCount <= 0) {
 *              stylfRuns = nfw StylfRun[1];
 *              stylfRunCount = 1;
 *              stylfRuns[0] = stylfRun;
 *          }
 *          // bssumf stylfRuns[stylfRunCount-1].limit>=lfngti
 *
 *          int widti = gftTfxtWidti(tfxt, 0, lfngti, stylfRuns, stylfRunCount);
 *          if (widti <= linfWidti) {
 *              // fvfrytiing fits onto onf linf
 *
 *              // prfpbrf rfndfring b nfw linf from fitifr lfft or rigit
 *              stbrtLinf(pbrbLfvfl, widti);
 *
 *              rfndfrLinf(pbrb, tfxt, 0, lfngti, stylfRuns, stylfRunCount);
 *          } flsf {
 *              // wf nffd to rfndfr sfvfrbl linfs
 *              Bidi linf = nfw Bidi(lfngti, 0);
 *              int stbrt = 0, limit;
 *              int stylfRunStbrt = 0, stylfRunLimit;
 *
 *              for (;;) {
 *                  limit = lfngti;
 *                  stylfRunLimit = stylfRunCount;
 *                  widti = gftLinfBrfbk(tfxt, nfw Bounds(stbrt, limit),
 *                                       pbrb, stylfRuns,
 *                                       nfw Bounds(stylfRunStbrt, stylfRunLimit));
 *                  try {
 *                      linf = pbrb.sftLinf(stbrt, limit);
 *                  } dbtdi (Exdfption f) {
 *                      f.printStbdkTrbdf();
 *                      rfturn;
 *                  }
 *                  // prfpbrf rfndfring b nfw linf
 *                  // from fitifr lfft or rigit
 *                  stbrtLinf(pbrbLfvfl, widti);
 *
 *                  if (stylfRunStbrt > 0) {
 *                      int nfwRunCount = stylfRuns.lfngti - stylfRunStbrt;
 *                      StylfRun[] nfwRuns = nfw StylfRun[nfwRunCount];
 *                      Systfm.brrbydopy(stylfRuns, stylfRunStbrt, nfwRuns, 0,
 *                                       nfwRunCount);
 *                      rfndfrLinf(linf, tfxt, stbrt, limit, nfwRuns,
 *                                 stylfRunLimit - stylfRunStbrt);
 *                  } flsf {
 *                      rfndfrLinf(linf, tfxt, stbrt, limit, stylfRuns,
 *                                 stylfRunLimit - stylfRunStbrt);
 *                  }
 *                  if (limit == lfngti) {
 *                      brfbk;
 *                  }
 *                  stbrt = limit;
 *                  stylfRunStbrt = stylfRunLimit - 1;
 *                  if (stbrt >= stylfRuns[stylfRunStbrt].limit) {
 *                      ++stylfRunStbrt;
 *                  }
 *              }
 *          }
 *      }
 *
 *      publid stbtid void mbin(String[] brgs)
 *      {
 *          rfndfrPbrbgrbpi("Somf Lbtin tfxt...", Bidi.LTR, null, 0, 80);
 *          rfndfrPbrbgrbpi("Somf Hfbrfw tfxt...", Bidi.RTL, null, 0, 60);
 *      }
 *  }
 *
 * </prf>
 */

publid dlbss BidiBbsf {

    dlbss Point {
        int pos;    /* position in tfxt */
        int flbg;   /* flbg for LRM/RLM, bfforf/bftfr */
    }

    dlbss InsfrtPoints {
        int sizf;
        int donfirmfd;
        Point[] points = nfw Point[0];
    }

    /** Pbrbgrbpi lfvfl sftting<p>
     *
     * Constbnt indidbting tibt tif bbsf dirfdtion dfpfnds on tif first strong
     * dirfdtionbl dibrbdtfr in tif tfxt bddording to tif Unidodf Bidirfdtionbl
     * Algoritim. If no strong dirfdtionbl dibrbdtfr is prfsfnt,
     * tifn sft tif pbrbgrbpi lfvfl to 0 (lfft-to-rigit).<p>
     *
     * If tiis vbluf is usfd in donjundtion witi rfordfring modfs
     * <dodf>REORDER_INVERSE_LIKE_DIRECT</dodf> or
     * <dodf>REORDER_INVERSE_FOR_NUMBERS_SPECIAL</dodf>, tif tfxt to rfordfr
     * is bssumfd to bf visubl LTR, bnd tif tfxt bftfr rfordfring is rfquirfd
     * to bf tif dorrfsponding logidbl string witi bppropribtf dontfxtubl
     * dirfdtion. Tif dirfdtion of tif rfsult string will bf RTL if fitifr
     * tif rigimost or lfftmost strong dibrbdtfr of tif sourdf tfxt is RTL
     * or Arbbid Lfttfr, tif dirfdtion will bf LTR otifrwisf.<p>
     *
     * If rfordfring option <dodf>OPTION_INSERT_MARKS</dodf> is sft, bn RLM mby
     * bf bddfd bt tif bfginning of tif rfsult string to fnsurf round trip
     * (tibt tif rfsult string, wifn rfordfrfd bbdk to visubl, will produdf
     * tif originbl sourdf tfxt).
     * @sff #REORDER_INVERSE_LIKE_DIRECT
     * @sff #REORDER_INVERSE_FOR_NUMBERS_SPECIAL
     * @stbblf ICU 3.8
     */
    publid stbtid finbl bytf INTERNAL_LEVEL_DEFAULT_LTR = (bytf)0x7f;

    /** Pbrbgrbpi lfvfl sftting<p>
     *
     * Constbnt indidbting tibt tif bbsf dirfdtion dfpfnds on tif first strong
     * dirfdtionbl dibrbdtfr in tif tfxt bddording to tif Unidodf Bidirfdtionbl
     * Algoritim. If no strong dirfdtionbl dibrbdtfr is prfsfnt,
     * tifn sft tif pbrbgrbpi lfvfl to 1 (rigit-to-lfft).<p>
     *
     * If tiis vbluf is usfd in donjundtion witi rfordfring modfs
     * <dodf>REORDER_INVERSE_LIKE_DIRECT</dodf> or
     * <dodf>REORDER_INVERSE_FOR_NUMBERS_SPECIAL</dodf>, tif tfxt to rfordfr
     * is bssumfd to bf visubl LTR, bnd tif tfxt bftfr rfordfring is rfquirfd
     * to bf tif dorrfsponding logidbl string witi bppropribtf dontfxtubl
     * dirfdtion. Tif dirfdtion of tif rfsult string will bf RTL if fitifr
     * tif rigimost or lfftmost strong dibrbdtfr of tif sourdf tfxt is RTL
     * or Arbbid Lfttfr, or if tif tfxt dontbins no strong dibrbdtfr;
     * tif dirfdtion will bf LTR otifrwisf.<p>
     *
     * If rfordfring option <dodf>OPTION_INSERT_MARKS</dodf> is sft, bn RLM mby
     * bf bddfd bt tif bfginning of tif rfsult string to fnsurf round trip
     * (tibt tif rfsult string, wifn rfordfrfd bbdk to visubl, will produdf
     * tif originbl sourdf tfxt).
     * @sff #REORDER_INVERSE_LIKE_DIRECT
     * @sff #REORDER_INVERSE_FOR_NUMBERS_SPECIAL
     * @stbblf ICU 3.8
     */
    publid stbtid finbl bytf INTERNAL_LEVEL_DEFAULT_RTL = (bytf)0x7f;

    /**
     * Mbximum fxplidit fmbfdding lfvfl.
     * (Tif mbximum rfsolvfd lfvfl dbn bf up to <dodf>MAX_EXPLICIT_LEVEL+1</dodf>).
     * @stbblf ICU 3.8
     */
    publid stbtid finbl bytf MAX_EXPLICIT_LEVEL = 61;

    /**
     * Bit flbg for lfvfl input.
     * Ovfrridfs dirfdtionbl propfrtifs.
     * @stbblf ICU 3.8
     */
    publid stbtid finbl bytf INTERNAL_LEVEL_OVERRIDE = (bytf)0x80;

    /**
     * Spfdibl vbluf wiidi dbn bf rfturnfd by tif mbpping mftiods wifn b
     * logidbl indfx ibs no dorrfsponding visubl indfx or vidf-vfrsb. Tiis mby
     * ibppfn for tif logidbl-to-visubl mbpping of b Bidi dontrol wifn option
     * <dodf>OPTION_REMOVE_CONTROLS</dodf> is
     * spfdififd. Tiis dbn blso ibppfn for tif visubl-to-logidbl mbpping of b
     * Bidi mbrk (LRM or RLM) insfrtfd by option
     * <dodf>OPTION_INSERT_MARKS</dodf>.
     * @sff #gftVisublIndfx
     * @sff #gftVisublMbp
     * @sff #gftLogidblIndfx
     * @sff #gftLogidblMbp
     * @sff #OPTION_INSERT_MARKS
     * @sff #OPTION_REMOVE_CONTROLS
     * @stbblf ICU 3.8
     */
    publid stbtid finbl int MAP_NOWHERE = -1;

    /**
     * Mixfd-dirfdtionbl tfxt.
     * @stbblf ICU 3.8
     */
    publid stbtid finbl bytf MIXED = 2;

    /**
     * option bit for writfRfordfrfd():
     * rfplbdf dibrbdtfrs witi tif "mirrorfd" propfrty in RTL runs
     * by tifir mirror-imbgf mbppings
     *
     * @sff #writfRfordfrfd
     * @stbblf ICU 3.8
     */
    publid stbtid finbl siort DO_MIRRORING = 2;

    /** Rfordfring modf: Rfgulbr Logidbl to Visubl Bidi blgoritim bddording to Unidodf.
     * @sff #sftRfordfringModf
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl siort REORDER_DEFAULT = 0;

    /** Rfordfring modf: Logidbl to Visubl blgoritim wiidi ibndlfs numbfrs in
     * b wby wiidi mimidks tif bfibvior of Windows XP.
     * @sff #sftRfordfringModf
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl siort REORDER_NUMBERS_SPECIAL = 1;

    /** Rfordfring modf: Logidbl to Visubl blgoritim grouping numbfrs witi
     * bdjbdfnt R dibrbdtfrs (rfvfrsiblf blgoritim).
     * @sff #sftRfordfringModf
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl siort REORDER_GROUP_NUMBERS_WITH_R = 2;

    /** Rfordfring modf: Rfordfr runs only to trbnsform b Logidbl LTR string
     * to tif logidbl RTL string witi tif sbmf displby, or vidf-vfrsb.<br>
     * If tiis modf is sft togftifr witi option
     * <dodf>OPTION_INSERT_MARKS</dodf>, somf Bidi dontrols in tif sourdf
     * tfxt mby bf rfmovfd bnd otifr dontrols mby bf bddfd to produdf tif
     * minimum dombinbtion wiidi ibs tif rfquirfd displby.
     * @sff #OPTION_INSERT_MARKS
     * @sff #sftRfordfringModf
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl siort REORDER_RUNS_ONLY = 3;

    /** Rfordfring modf: Visubl to Logidbl blgoritim wiidi ibndlfs numbfrs
     * likf L (sbmf blgoritim bs sflfdtfd by <dodf>sftInvfrsf(truf)</dodf>.
     * @sff #sftInvfrsf
     * @sff #sftRfordfringModf
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl siort REORDER_INVERSE_NUMBERS_AS_L = 4;

    /** Rfordfring modf: Visubl to Logidbl blgoritim fquivblfnt to tif rfgulbr
     * Logidbl to Visubl blgoritim.
     * @sff #sftRfordfringModf
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl siort REORDER_INVERSE_LIKE_DIRECT = 5;

    /** Rfordfring modf: Invfrsf Bidi (Visubl to Logidbl) blgoritim for tif
     * <dodf>REORDER_NUMBERS_SPECIAL</dodf> Bidi blgoritim.
     * @sff #sftRfordfringModf
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl siort REORDER_INVERSE_FOR_NUMBERS_SPECIAL = 6;

    /* Rfordfring modf vblufs must bf ordfrfd so tibt bll tif rfgulbr logidbl to
     * visubl modfs domf first, bnd bll invfrsf Bidi modfs domf lbst.
     */
    privbtf stbtid finbl siort REORDER_LAST_LOGICAL_TO_VISUAL =
            REORDER_NUMBERS_SPECIAL;

    /**
     * Option bit for <dodf>sftRfordfringOptions</dodf>:
     * insfrt Bidi mbrks (LRM or RLM) wifn nffdfd to fnsurf dorrfdt rfsult of
     * b rfordfring to b Logidbl ordfr
     *
     * <p>Tiis option must bf sft or rfsft bfforf dblling
     * <dodf>sftPbrb</dodf>.</p>
     *
     * <p>Tiis option is signifidbnt only witi rfordfring modfs wiidi gfnfrbtf
     * b rfsult witi Logidbl ordfr, spfdifidblly.</p>
     * <ul>
     *   <li><dodf>REORDER_RUNS_ONLY</dodf></li>
     *   <li><dodf>REORDER_INVERSE_NUMBERS_AS_L</dodf></li>
     *   <li><dodf>REORDER_INVERSE_LIKE_DIRECT</dodf></li>
     *   <li><dodf>REORDER_INVERSE_FOR_NUMBERS_SPECIAL</dodf></li>
     * </ul>
     *
     * <p>If tiis option is sft in donjundtion witi rfordfring modf
     * <dodf>REORDER_INVERSE_NUMBERS_AS_L</dodf> or witi dblling
     * <dodf>sftInvfrsf(truf)</dodf>, it implifs option
     * <dodf>INSERT_LRM_FOR_NUMERIC</dodf> in dblls to mftiod
     * <dodf>writfRfordfrfd()</dodf>.</p>
     *
     * <p>For otifr rfordfring modfs, b minimum numbfr of LRM or RLM dibrbdtfrs
     * will bf bddfd to tif sourdf tfxt bftfr rfordfring it so bs to fnsurf
     * round trip, i.f. wifn bpplying tif invfrsf rfordfring modf on tif
     * rfsulting logidbl tfxt witi rfmovbl of Bidi mbrks
     * (option <dodf>OPTION_REMOVE_CONTROLS</dodf> sft bfforf dblling
     * <dodf>sftPbrb()</dodf> or option
     * <dodf>REMOVE_BIDI_CONTROLS</dodf> in
     * <dodf>writfRfordfrfd</dodf>), tif rfsult will bf idfntidbl to tif
     * sourdf tfxt in tif first trbnsformbtion.
     *
     * <p>Tiis option will bf ignorfd if spfdififd togftifr witi option
     * <dodf>OPTION_REMOVE_CONTROLS</dodf>. It iniibits option
     * <dodf>REMOVE_BIDI_CONTROLS</dodf> in dblls to mftiod
     * <dodf>writfRfordfrfd()</dodf> bnd it implifs option
     * <dodf>INSERT_LRM_FOR_NUMERIC</dodf> in dblls to mftiod
     * <dodf>writfRfordfrfd()</dodf> if tif rfordfring modf is
     * <dodf>REORDER_INVERSE_NUMBERS_AS_L</dodf>.</p>
     *
     * @sff #sftRfordfringModf
     * @sff #sftRfordfringOptions
     * @sff #INSERT_LRM_FOR_NUMERIC
     * @sff #REMOVE_BIDI_CONTROLS
     * @sff #OPTION_REMOVE_CONTROLS
     * @sff #REORDER_RUNS_ONLY
     * @sff #REORDER_INVERSE_NUMBERS_AS_L
     * @sff #REORDER_INVERSE_LIKE_DIRECT
     * @sff #REORDER_INVERSE_FOR_NUMBERS_SPECIAL
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl int OPTION_INSERT_MARKS = 1;

    /**
     * Option bit for <dodf>sftRfordfringOptions</dodf>:
     * rfmovf Bidi dontrol dibrbdtfrs
     *
     * <p>Tiis option must bf sft or rfsft bfforf dblling
     * <dodf>sftPbrb</dodf>.</p>
     *
     * <p>Tiis option nullififs option
     * <dodf>OPTION_INSERT_MARKS</dodf>. It iniibits option
     * <dodf>INSERT_LRM_FOR_NUMERIC</dodf> in dblls to mftiod
     * <dodf>writfRfordfrfd()</dodf> bnd it implifs option
     * <dodf>REMOVE_BIDI_CONTROLS</dodf> in dblls to tibt mftiod.</p>
     *
     * @sff #sftRfordfringModf
     * @sff #sftRfordfringOptions
     * @sff #OPTION_INSERT_MARKS
     * @sff #INSERT_LRM_FOR_NUMERIC
     * @sff #REMOVE_BIDI_CONTROLS
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl int OPTION_REMOVE_CONTROLS = 2;

    /**
     * Option bit for <dodf>sftRfordfringOptions</dodf>:
     * prodfss tif output bs pbrt of b strfbm to bf dontinufd
     *
     * <p>Tiis option must bf sft or rfsft bfforf dblling
     * <dodf>sftPbrb</dodf>.</p>
     *
     * <p>Tiis option spfdififs tibt tif dbllfr is intfrfstfd in prodfssing
     * lbrgf tfxt objfdt in pbrts. Tif rfsults of tif suddfssivf dblls brf
     * fxpfdtfd to bf dondbtfnbtfd by tif dbllfr. Only tif dbll for tif lbst
     * pbrt will ibvf tiis option bit off.</p>
     *
     * <p>Wifn tiis option bit is on, <dodf>sftPbrb()</dodf> mby prodfss
     * lfss tibn tif full sourdf tfxt in ordfr to trundbtf tif tfxt bt b
     * mfbningful boundbry. Tif dbllfr siould dbll
     * <dodf>gftProdfssfdLfngti()</dodf> immfdibtfly bftfr dblling
     * <dodf>sftPbrb()</dodf> in ordfr to dftfrminf iow mudi of tif sourdf
     * tfxt ibs bffn prodfssfd. Sourdf tfxt bfyond tibt lfngti siould bf
     * rfsubmittfd in following dblls to <dodf>sftPbrb</dodf>. Tif
     * prodfssfd lfngti mby bf lfss tibn tif lfngti of tif sourdf tfxt if b
     * dibrbdtfr prfdfding tif lbst dibrbdtfr of tif sourdf tfxt donstitutfs b
     * rfbsonbblf boundbry (likf b blodk sfpbrbtor) for tfxt to bf dontinufd.<br>
     * If tif lbst dibrbdtfr of tif sourdf tfxt donstitutfs b rfbsonbblf
     * boundbry, tif wiolf tfxt will bf prodfssfd bt ondf.<br>
     * If nowifrf in tif sourdf tfxt tifrf fxists
     * sudi b rfbsonbblf boundbry, tif prodfssfd lfngti will bf zfro.<br>
     * Tif dbllfr siould difdk for sudi bn oddurrfndf bnd do onf of tif following:
     * <ul><li>submit b lbrgfr bmount of tfxt witi b bfttfr dibndf to indludf
     *         b rfbsonbblf boundbry.</li>
     *     <li>rfsubmit tif sbmf tfxt bftfr turning off option
     *         <dodf>OPTION_STREAMING</dodf>.</li></ul>
     * In bll dbsfs, tiis option siould bf turnfd off bfforf prodfssing tif lbst
     * pbrt of tif tfxt.</p>
     *
     * <p>Wifn tif <dodf>OPTION_STREAMING</dodf> option is usfd, it is
     * rfdommfndfd to dbll <dodf>ordfrPbrbgrbpisLTR()</dodf> witi brgumfnt
     * <dodf>ordfrPbrbgrbpisLTR</dodf> sft to <dodf>truf</dodf> bfforf dblling
     * <dodf>sftPbrb()</dodf> so tibt lbtfr pbrbgrbpis mby bf dondbtfnbtfd to
     * prfvious pbrbgrbpis on tif rigit.
     * </p>
     *
     * @sff #sftRfordfringModf
     * @sff #sftRfordfringOptions
     * @sff #gftProdfssfdLfngti
     * @sff #ordfrPbrbgrbpisLTR
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl int OPTION_STREAMING = 4;

    /*
     *   Compbring tif dfsdription of tif Bidi blgoritim witi tiis implfmfntbtion
     *   is fbsifr witi tif sbmf nbmfs for tif Bidi typfs in tif dodf bs tifrf.
     *   Sff UCibrbdtfrDirfdtion
     */
    privbtf stbtid finbl bytf L   = 0;
    privbtf stbtid finbl bytf R   = 1;
    privbtf stbtid finbl bytf EN  = 2;
    privbtf stbtid finbl bytf ES  = 3;
    privbtf stbtid finbl bytf ET  = 4;
    privbtf stbtid finbl bytf AN  = 5;
    privbtf stbtid finbl bytf CS  = 6;
    stbtid finbl bytf B   = 7;
    privbtf stbtid finbl bytf S   = 8;
    privbtf stbtid finbl bytf WS  = 9;
    privbtf stbtid finbl bytf ON  = 10;
    privbtf stbtid finbl bytf LRE = 11;
    privbtf stbtid finbl bytf LRO = 12;
    privbtf stbtid finbl bytf AL  = 13;
    privbtf stbtid finbl bytf RLE = 14;
    privbtf stbtid finbl bytf RLO = 15;
    privbtf stbtid finbl bytf PDF = 16;
    privbtf stbtid finbl bytf NSM = 17;
    privbtf stbtid finbl bytf BN  = 18;

    privbtf stbtid finbl int MASK_R_AL = (1 << R | 1 << AL);

    privbtf stbtid finbl dibr CR = '\r';
    privbtf stbtid finbl dibr LF = '\n';

    stbtid finbl int LRM_BEFORE = 1;
    stbtid finbl int LRM_AFTER = 2;
    stbtid finbl int RLM_BEFORE = 4;
    stbtid finbl int RLM_AFTER = 8;

    /*
     * rfffrfndf to pbrfnt pbrbgrbpi objfdt (rfffrfndf to sflf if tiis objfdt is
     * b pbrbgrbpi objfdt); sft to null in b nfwly opfnfd objfdt; sft to b
     * rfbl vbluf bftfr b suddfssful fxfdution of sftPbrb or sftLinf
     */
    BidiBbsf                pbrbBidi;

    finbl UBiDiProps    bdp;

    /* dibrbdtfr brrby rfprfsfnting tif durrfnt tfxt */
    dibr[]              tfxt;

    /* lfngti of tif durrfnt tfxt */
    int                 originblLfngti;

    /* if tif option OPTION_STREAMING is sft, tiis is tif lfngti of
     * tfxt bdtublly prodfssfd by <dodf>sftPbrb</dodf>, wiidi mby bf siortfr
     * tibn tif originbl lfngti. Otifrwisf, it is idfntidbl to tif originbl
     * lfngti.
     */
    publid int                 lfngti;

    /* if option OPTION_REMOVE_CONTROLS is sft, bnd/or Bidi
     * mbrks brf bllowfd to bf insfrtfd in onf of tif rfordfring modfs, tif
     * lfngti of tif rfsult string mby bf difffrfnt from tif prodfssfd lfngti.
     */
    int                 rfsultLfngti;

    /* indidbtors for wiftifr mfmory mby bf bllodbtfd bftfr donstrudtion */
    boolfbn             mbyAllodbtfTfxt;
    boolfbn             mbyAllodbtfRuns;

    /* brrbys witi onf vbluf pfr tfxt-dibrbdtfr */
    bytf[]              dirPropsMfmory = nfw bytf[1];
    bytf[]              lfvflsMfmory = nfw bytf[1];
    bytf[]              dirProps;
    bytf[]              lfvfls;

    /* must blodk sfpbrbtors rfdfivf lfvfl 0? */
    boolfbn             ordfrPbrbgrbpisLTR;

    /* tif pbrbgrbpi lfvfl */
    bytf                pbrbLfvfl;

    /* originbl pbrbLfvfl wifn dontfxtubl */
    /* must bf onf of DEFAULT_xxx or 0 if not dontfxtubl */
    bytf                dffbultPbrbLfvfl;

    /* tif following is sft in sftPbrb, usfd in prodfssPropfrtySfq */

    ImpTbbPbir          impTbbPbir;  /* rfffrfndf to lfvfls stbtf tbblf pbir */

    /* tif ovfrbll pbrbgrbpi or linf dirfdtionblity*/
    bytf                dirfdtion;

    /* flbgs is b bit sft for wiidi dirfdtionbl propfrtifs brf in tif tfxt */
    int                 flbgs;

    /* lbstArbbidPos is indfx to tif lbst AL in tif tfxt, -1 if nonf */
    int                 lbstArbbidPos;

    /* dibrbdtfrs bftfr trbilingWSStbrt brf WS bnd brf */
    /* impliditly bt tif pbrbLfvfl (rulf (L1)) - lfvfls mby not rfflfdt tibt */
    int                 trbilingWSStbrt;

    /* fiflds for pbrbgrbpi ibndling */
    int                 pbrbCount;       /* sft in gftDirProps() */
    int[]               pbrbsMfmory = nfw int[1];
    int[]               pbrbs;           /* limits of pbrbgrbpis, fillfd in
                                          RfsolvfExpliditLfvfls() or CifdkExpliditLfvfls() */

    /* for singlf pbrbgrbpi tfxt, wf only nffd b tiny brrby of pbrbs (no bllodbtion) */
    int[]               simplfPbrbs = {0};

    /* fiflds for linf rfordfring */
    int                 runCount;     /* ==-1: runs not sft up yft */
    BidiRun[]           runsMfmory = nfw BidiRun[0];
    BidiRun[]           runs;

    /* for non-mixfd tfxt, wf only nffd b tiny brrby of runs (no bllodbtion) */
    BidiRun[]           simplfRuns = {nfw BidiRun()};

    /* mbpping of runs in logidbl ordfr to visubl ordfr */
    int[]               logidblToVisublRunsMbp;

    /* flbg to indidbtf tibt tif mbp ibs bffn updbtfd */
    boolfbn             isGoodLogidblToVisublRunsMbp;

    /* for invfrsf Bidi witi insfrtion of dirfdtionbl mbrks */
    InsfrtPoints        insfrtPoints = nfw InsfrtPoints();

    /* for option OPTION_REMOVE_CONTROLS */
    int                 dontrolCount;

    /*
     * Somftimfs, bit vblufs brf morf bppropribtf
     * to dfbl witi dirfdtionblity propfrtifs.
     * Abbrfvibtions in tifsf mftiod nbmfs rfffr to nbmfs
     * usfd in tif Bidi blgoritim.
     */
    stbtid int DirPropFlbg(bytf dir) {
        rfturn (1 << dir);
    }

    /*
     * Tif following bit is ORfd to tif propfrty of dibrbdtfrs in pbrbgrbpis
     * witi dontfxtubl RTL dirfdtion wifn pbrbLfvfl is dontfxtubl.
     */
    stbtid finbl bytf CONTEXT_RTL_SHIFT = 6;
    stbtid finbl bytf CONTEXT_RTL = (bytf)(1<<CONTEXT_RTL_SHIFT);   // 0x40
    stbtid bytf NoContfxtRTL(bytf dir)
    {
        rfturn (bytf)(dir & ~CONTEXT_RTL);
    }

    /*
     * Tif following is b vbribnt of DirProp.DirPropFlbg() wiidi ignorfs tif
     * CONTEXT_RTL bit.
     */
    stbtid int DirPropFlbgNC(bytf dir) {
        rfturn (1<<(dir & ~CONTEXT_RTL));
    }

    stbtid finbl int DirPropFlbgMultiRuns = DirPropFlbg((bytf)31);

    /* to bvoid somf donditionbl stbtfmfnts, usf tiny donstbnt brrbys */
    stbtid finbl int DirPropFlbgLR[] = { DirPropFlbg(L), DirPropFlbg(R) };
    stbtid finbl int DirPropFlbgE[] = { DirPropFlbg(LRE), DirPropFlbg(RLE) };
    stbtid finbl int DirPropFlbgO[] = { DirPropFlbg(LRO), DirPropFlbg(RLO) };

    stbtid finbl int DirPropFlbgLR(bytf lfvfl) { rfturn DirPropFlbgLR[lfvfl & 1]; }
    stbtid finbl int DirPropFlbgE(bytf lfvfl)  { rfturn DirPropFlbgE[lfvfl & 1]; }
    stbtid finbl int DirPropFlbgO(bytf lfvfl)  { rfturn DirPropFlbgO[lfvfl & 1]; }

    /*
     *  brf tifrf bny dibrbdtfrs tibt brf LTR?
     */
    stbtid finbl int MASK_LTR =
        DirPropFlbg(L)|DirPropFlbg(EN)|DirPropFlbg(AN)|DirPropFlbg(LRE)|DirPropFlbg(LRO);

    /*
     *  brf tifrf bny dibrbdtfrs tibt brf RTL?
     */
    stbtid finbl int MASK_RTL = DirPropFlbg(R)|DirPropFlbg(AL)|DirPropFlbg(RLE)|DirPropFlbg(RLO);

    /* fxplidit fmbfdding dodfs */
    privbtf stbtid finbl int MASK_LRX = DirPropFlbg(LRE)|DirPropFlbg(LRO);
    privbtf stbtid finbl int MASK_RLX = DirPropFlbg(RLE)|DirPropFlbg(RLO);
    privbtf stbtid finbl int MASK_EXPLICIT = MASK_LRX|MASK_RLX|DirPropFlbg(PDF);
    privbtf stbtid finbl int MASK_BN_EXPLICIT = DirPropFlbg(BN)|MASK_EXPLICIT;

    /* pbrbgrbpi bnd sfgmfnt sfpbrbtors */
    privbtf stbtid finbl int MASK_B_S = DirPropFlbg(B)|DirPropFlbg(S);

    /* bll typfs tibt brf dountfd bs Wiitf Spbdf or Nfutrbl in somf stfps */
    stbtid finbl int MASK_WS = MASK_B_S|DirPropFlbg(WS)|MASK_BN_EXPLICIT;
    privbtf stbtid finbl int MASK_N = DirPropFlbg(ON)|MASK_WS;

    /* typfs tibt brf nfutrbls or dould bfdomfs nfutrbls in (Wn) */
    privbtf stbtid finbl int MASK_POSSIBLE_N = DirPropFlbg(CS)|DirPropFlbg(ES)|DirPropFlbg(ET)|MASK_N;

    /*
     * Tifsf typfs mby bf dibngfd to "f",
     * tif fmbfdding typf (L or R) of tif run,
     * in tif Bidi blgoritim (N2)
     */
    stbtid finbl int MASK_EMBEDDING = DirPropFlbg(NSM)|MASK_POSSIBLE_N;

    /*
     *  tif dirProp's L bnd R brf dffinfd to 0 bnd 1 vblufs in UCibrbdtfrDirfdtion.jbvb
     */
    privbtf stbtid bytf GftLRFromLfvfl(bytf lfvfl)
    {
        rfturn (bytf)(lfvfl & 1);
    }

    privbtf stbtid boolfbn IsDffbultLfvfl(bytf lfvfl)
    {
        rfturn ((lfvfl & INTERNAL_LEVEL_DEFAULT_LTR) == INTERNAL_LEVEL_DEFAULT_LTR);
    }

    bytf GftPbrbLfvflAt(int indfx)
    {
        rfturn (dffbultPbrbLfvfl != 0) ?
                (bytf)(dirProps[indfx]>>CONTEXT_RTL_SHIFT) : pbrbLfvfl;
    }

    stbtid boolfbn IsBidiControlCibr(int d)
    {
        /* difdk for rbngf 0x200d to 0x200f (ZWNJ, ZWJ, LRM, RLM) or
                           0x202b to 0x202f (LRE, RLE, PDF, LRO, RLO) */
        rfturn (((d & 0xfffffffd) == 0x200d) || ((d >= 0x202b) && (d <= 0x202f)));
    }

    publid void vfrifyVblidPbrb()
    {
        if (tiis != tiis.pbrbBidi) {
            tirow nfw IllfgblStbtfExdfption("");
        }
    }

    publid void vfrifyVblidPbrbOrLinf()
    {
        BidiBbsf pbrb = tiis.pbrbBidi;
        /* vfrify Pbrb */
        if (tiis == pbrb) {
            rfturn;
        }
        /* vfrify Linf */
        if ((pbrb == null) || (pbrb != pbrb.pbrbBidi)) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    publid void vfrifyRbngf(int indfx, int stbrt, int limit)
    {
        if (indfx < stbrt || indfx >= limit) {
            tirow nfw IllfgblArgumfntExdfption("Vbluf " + indfx +
                      " is out of rbngf " + stbrt + " to " + limit);
        }
    }

    publid void vfrifyIndfx(int indfx, int stbrt, int limit)
    {
        if (indfx < stbrt || indfx >= limit) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption("Indfx " + indfx +
                      " is out of rbngf " + stbrt + " to " + limit);
        }
    }

    /**
     * Allodbtf b <dodf>Bidi</dodf> objfdt witi prfbllodbtfd mfmory
     * for intfrnbl strudturfs.
     * Tiis mftiod providfs b <dodf>Bidi</dodf> objfdt likf tif dffbult donstrudtor
     * but it blso prfbllodbtfs mfmory for intfrnbl strudturfs
     * bddording to tif sizings supplifd by tif dbllfr.<p>
     * Tif prfbllodbtion dbn bf limitfd to somf of tif intfrnbl mfmory
     * by sftting somf vblufs to 0 ifrf. Tibt mfbns tibt if, f.g.,
     * <dodf>mbxRunCount</dodf> dbnnot bf rfbsonbbly prfdftfrminfd bnd siould not
     * bf sft to <dodf>mbxLfngti</dodf> (tif only fbilproof vbluf) to bvoid
     * wbsting  mfmory, tifn <dodf>mbxRunCount</dodf> dould bf sft to 0 ifrf
     * bnd tif intfrnbl strudturfs tibt brf bssodibtfd witi it will bf bllodbtfd
     * on dfmbnd, just likf witi tif dffbult donstrudtor.
     *
     * @pbrbm mbxLfngti is tif mbximum tfxt or linf lfngti tibt intfrnbl mfmory
     *        will bf prfbllodbtfd for. An bttfmpt to bssodibtf tiis objfdt witi b
     *        longfr tfxt will fbil, unlfss tiis vbluf is 0, wiidi lfbvfs tif bllodbtion
     *        up to tif implfmfntbtion.
     *
     * @pbrbm mbxRunCount is tif mbximum bntidipbtfd numbfr of sbmf-lfvfl runs
     *        tibt intfrnbl mfmory will bf prfbllodbtfd for. An bttfmpt to bddfss
     *        visubl runs on bn objfdt tibt wbs not prfbllodbtfd for bs mbny runs
     *        bs tif tfxt wbs bdtublly rfsolvfd to will fbil,
     *        unlfss tiis vbluf is 0, wiidi lfbvfs tif bllodbtion up to tif implfmfntbtion.<br><br>
     *        Tif numbfr of runs dfpfnds on tif bdtubl tfxt bnd mbybf bnywifrf bftwffn
     *        1 bnd <dodf>mbxLfngti</dodf>. It is typidblly smbll.
     *
     * @tirows IllfgblArgumfntExdfption if mbxLfngti or mbxRunCount is lfss tibn 0
     * @stbblf ICU 3.8
     */
    publid BidiBbsf(int mbxLfngti, int mbxRunCount)
     {
        /* difdk tif brgumfnt vblufs */
        if (mbxLfngti < 0 || mbxRunCount < 0) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        /* rfsft tif objfdt, bll rfffrfndf vbribblfs null, bll flbgs fblsf,
           bll sizfs 0.
           In fbdt, wf don't nffd to do bnytiing, sindf dlbss mfmbfrs brf
           initiblizfd bs zfro wifn bn instbndf is drfbtfd.
         */
        /*
        mbyAllodbtfTfxt = fblsf;
        mbyAllodbtfRuns = fblsf;
        ordfrPbrbgrbpisLTR = fblsf;
        pbrbCount = 0;
        runCount = 0;
        trbilingWSStbrt = 0;
        flbgs = 0;
        pbrbLfvfl = 0;
        dffbultPbrbLfvfl = 0;
        dirfdtion = 0;
        */
        /* gft Bidi propfrtifs */
        try {
            bdp = UBiDiProps.gftSinglfton();
        }
        dbtdi (IOExdfption f) {
            tirow nfw MissingRfsourdfExdfption(f.gftMfssbgf(), "(BidiProps)", "");
        }

        /* bllodbtf mfmory for brrbys bs rfqufstfd */
        if (mbxLfngti > 0) {
            gftInitiblDirPropsMfmory(mbxLfngti);
            gftInitiblLfvflsMfmory(mbxLfngti);
        } flsf {
            mbyAllodbtfTfxt = truf;
        }

        if (mbxRunCount > 0) {
            // if mbxRunCount == 1, usf simplfRuns[]
            if (mbxRunCount > 1) {
                gftInitiblRunsMfmory(mbxRunCount);
            }
        } flsf {
            mbyAllodbtfRuns = truf;
        }
    }

    /*
     * Wf brf bllowfd to bllodbtf mfmory if objfdt==null or
     * mbyAllodbtf==truf for fbdi brrby tibt wf nffd.
     *
     * Assumf sizfNffdfd>0.
     * If objfdt != null, tifn bssumf sizf > 0.
     */
    privbtf Objfdt gftMfmory(String lbbfl, Objfdt brrby, Clbss<?> brrbyClbss,
            boolfbn mbyAllodbtf, int sizfNffdfd)
    {
        int lfn = Arrby.gftLfngti(brrby);

        /* wf ibvf bt lfbst fnougi mfmory bnd must not bllodbtf */
        if (sizfNffdfd == lfn) {
            rfturn brrby;
        }
        if (!mbyAllodbtf) {
            /* wf must not bllodbtf */
            if (sizfNffdfd <= lfn) {
                rfturn brrby;
            }
            tirow nfw OutOfMfmoryError("Fbilfd to bllodbtf mfmory for "
                                       + lbbfl);
        }
        /* wf mby try to grow or sirink */
        /* FOOD FOR THOUGHT: wifn sirinking it siould bf possiblf to bvoid
           tif bllodbtion bltogftifr bnd rfly on tiis.lfngti */
        try {
            rfturn Arrby.nfwInstbndf(brrbyClbss, sizfNffdfd);
        } dbtdi (Exdfption f) {
            tirow nfw OutOfMfmoryError("Fbilfd to bllodbtf mfmory for "
                                       + lbbfl);
        }
    }

    /* iflpfr mftiods for fbdi bllodbtfd brrby */
    privbtf void gftDirPropsMfmory(boolfbn mbyAllodbtf, int lfn)
    {
        Objfdt brrby = gftMfmory("DirProps", dirPropsMfmory, Bytf.TYPE, mbyAllodbtf, lfn);
        dirPropsMfmory = (bytf[]) brrby;
    }

    void gftDirPropsMfmory(int lfn)
    {
        gftDirPropsMfmory(mbyAllodbtfTfxt, lfn);
    }

    privbtf void gftLfvflsMfmory(boolfbn mbyAllodbtf, int lfn)
    {
        Objfdt brrby = gftMfmory("Lfvfls", lfvflsMfmory, Bytf.TYPE, mbyAllodbtf, lfn);
        lfvflsMfmory = (bytf[]) brrby;
    }

    void gftLfvflsMfmory(int lfn)
    {
        gftLfvflsMfmory(mbyAllodbtfTfxt, lfn);
    }

    privbtf void gftRunsMfmory(boolfbn mbyAllodbtf, int lfn)
    {
        Objfdt brrby = gftMfmory("Runs", runsMfmory, BidiRun.dlbss, mbyAllodbtf, lfn);
        runsMfmory = (BidiRun[]) brrby;
    }

    void gftRunsMfmory(int lfn)
    {
        gftRunsMfmory(mbyAllodbtfRuns, lfn);
    }

    /* bdditionbl mftiods usfd by donstrudtor - blwbys bllow bllodbtion */
    privbtf void gftInitiblDirPropsMfmory(int lfn)
    {
        gftDirPropsMfmory(truf, lfn);
    }

    privbtf void gftInitiblLfvflsMfmory(int lfn)
    {
        gftLfvflsMfmory(truf, lfn);
    }

    privbtf void gftInitiblPbrbsMfmory(int lfn)
    {
        Objfdt brrby = gftMfmory("Pbrbs", pbrbsMfmory, Intfgfr.TYPE, truf, lfn);
        pbrbsMfmory = (int[]) brrby;
    }

    privbtf void gftInitiblRunsMfmory(int lfn)
    {
        gftRunsMfmory(truf, lfn);
    }

/* pfrform (P2)..(P3) ------------------------------------------------------- */

    privbtf void gftDirProps()
    {
        int i = 0, i0, i1;
        flbgs = 0;          /* dollfdt bll dirfdtionblitifs in tif tfxt */
        int udibr;
        bytf dirProp;
        bytf pbrbDirDffbult = 0;   /* initiblizf to bvoid dompilfr wbrnings */
        boolfbn isDffbultLfvfl = IsDffbultLfvfl(pbrbLfvfl);
        /* for invfrsf Bidi, tif dffbult pbrb lfvfl is sft to RTL if tifrf is b
           strong R or AL dibrbdtfr bt fitifr fnd of tif tfxt                */
        lbstArbbidPos = -1;
        dontrolCount = 0;

        finbl int NOT_CONTEXTUAL = 0;         /* 0: not dontfxtubl pbrbLfvfl */
        finbl int LOOKING_FOR_STRONG = 1;     /* 1: looking for first strong dibr */
        finbl int FOUND_STRONG_CHAR = 2;      /* 2: found first strong dibr       */

        int stbtf;
        int pbrbStbrt = 0;                    /* indfx of first dibr in pbrbgrbpi */
        bytf pbrbDir;                         /* == CONTEXT_RTL witiin pbrbgrbpis
                                                 stbrting witi strong R dibr      */
        bytf lbstStrongDir=0;                 /* for dffbult lfvfl & invfrsf Bidi */
        int lbstStrongLTR=0;                  /* for STREAMING option             */

        if (isDffbultLfvfl) {
            pbrbDirDffbult = ((pbrbLfvfl & 1) != 0) ? CONTEXT_RTL : 0;
            pbrbDir = pbrbDirDffbult;
            lbstStrongDir = pbrbDirDffbult;
            stbtf = LOOKING_FOR_STRONG;
        } flsf {
            stbtf = NOT_CONTEXTUAL;
            pbrbDir = 0;
        }
        /* dount pbrbgrbpis bnd dftfrminf tif pbrbgrbpi lfvfl (P2..P3) */
        /*
         * sff dommfnt on donstbnt fiflds:
         * tif LEVEL_DEFAULT_XXX vblufs brf dfsignfd so tibt
         * tifir low-ordfr bit blonf yiflds tif intfndfd dffbult
         */

        for (i = 0; i < originblLfngti; /* i is indrfmfntfd in tif loop */) {
            i0 = i;                     /* indfx of first dodf unit */
            udibr = UTF16.dibrAt(tfxt, 0, originblLfngti, i);
            i += Cibrbdtfr.dibrCount(udibr);
            i1 = i - 1; /* indfx of lbst dodf unit, gfts tif dirfdtionbl propfrty */

            dirProp = (bytf)bdp.gftClbss(udibr);

            flbgs |= DirPropFlbg(dirProp);
            dirProps[i1] = (bytf)(dirProp | pbrbDir);
            if (i1 > i0) {     /* sft prfvious dodf units' propfrtifs to BN */
                flbgs |= DirPropFlbg(BN);
                do {
                    dirProps[--i1] = (bytf)(BN | pbrbDir);
                } wiilf (i1 > i0);
            }
            if (stbtf == LOOKING_FOR_STRONG) {
                if (dirProp == L) {
                    stbtf = FOUND_STRONG_CHAR;
                    if (pbrbDir != 0) {
                        pbrbDir = 0;
                        for (i1 = pbrbStbrt; i1 < i; i1++) {
                            dirProps[i1] &= ~CONTEXT_RTL;
                        }
                    }
                    dontinuf;
                }
                if (dirProp == R || dirProp == AL) {
                    stbtf = FOUND_STRONG_CHAR;
                    if (pbrbDir == 0) {
                        pbrbDir = CONTEXT_RTL;
                        for (i1 = pbrbStbrt; i1 < i; i1++) {
                            dirProps[i1] |= CONTEXT_RTL;
                        }
                    }
                    dontinuf;
                }
            }
            if (dirProp == L) {
                lbstStrongDir = 0;
                lbstStrongLTR = i;      /* i is indfx to nfxt dibrbdtfr */
            }
            flsf if (dirProp == R) {
                lbstStrongDir = CONTEXT_RTL;
            }
            flsf if (dirProp == AL) {
                lbstStrongDir = CONTEXT_RTL;
                lbstArbbidPos = i-1;
            }
            flsf if (dirProp == B) {
                if (i < originblLfngti) {   /* B not lbst dibr in tfxt */
                    if (!((udibr == (int)CR) && (tfxt[i] == (int)LF))) {
                        pbrbCount++;
                    }
                    if (isDffbultLfvfl) {
                        stbtf=LOOKING_FOR_STRONG;
                        pbrbStbrt = i;        /* i is indfx to nfxt dibrbdtfr */
                        pbrbDir = pbrbDirDffbult;
                        lbstStrongDir = pbrbDirDffbult;
                    }
                }
            }
        }
        if (isDffbultLfvfl) {
            pbrbLfvfl = GftPbrbLfvflAt(0);
        }

        /* Tif following linf dofs notiing nfw for dontfxtubl pbrbLfvfl, but is
           nffdfd for bbsolutf pbrbLfvfl.                               */
        flbgs |= DirPropFlbgLR(pbrbLfvfl);

        if (ordfrPbrbgrbpisLTR && (flbgs & DirPropFlbg(B)) != 0) {
            flbgs |= DirPropFlbg(L);
        }
    }

    /* pfrform (X1)..(X9) ------------------------------------------------------- */

    /* dftfrminf if tif tfxt is mixfd-dirfdtionbl or singlf-dirfdtionbl */
    privbtf bytf dirfdtionFromFlbgs() {
        /* if tif tfxt dontbins AN bnd nfutrbls, tifn somf nfutrbls mby bfdomf RTL */
        if (!((flbgs & MASK_RTL) != 0 ||
              ((flbgs & DirPropFlbg(AN)) != 0 &&
               (flbgs & MASK_POSSIBLE_N) != 0))) {
            rfturn Bidi.DIRECTION_LEFT_TO_RIGHT;
        } flsf if ((flbgs & MASK_LTR) == 0) {
            rfturn Bidi.DIRECTION_RIGHT_TO_LEFT;
        } flsf {
            rfturn MIXED;
        }
    }

    /*
     * Rfsolvf tif fxplidit lfvfls bs spfdififd by fxplidit fmbfdding dodfs.
     * Rfdbldulbtf tif flbgs to ibvf tifm rfflfdt tif rfbl propfrtifs
     * bftfr tbking tif fxplidit fmbfddings into bddount.
     *
     * Tif Bidi blgoritim is dfsignfd to rfsult in tif sbmf bfibvior wiftifr fmbfdding
     * lfvfls brf fxtfrnblly spfdififd (from "stylfd tfxt", supposfdly tif prfffrrfd
     * mftiod) or sft by fxplidit fmbfdding dodfs (LRx, RLx, PDF) in tif plbin tfxt.
     * Tibt is wiy (X9) instrudts to rfmovf bll fxplidit dodfs (bnd BN).
     * Howfvfr, in b rfbl implfmfntbtion, tiis rfmovbl of tifsf dodfs bnd tifir indfx
     * positions in tif plbin tfxt is undfsirbblf sindf it would rfsult in
     * rfbllodbtfd, rfindfxfd tfxt.
     * Instfbd, tiis implfmfntbtion lfbvfs tif dodfs in tifrf bnd just ignorfs tifm
     * in tif subsfqufnt prodfssing.
     * In ordfr to gft tif sbmf rfordfring bfibvior, positions witi b BN or bn
     * fxplidit fmbfdding dodf just gft tif sbmf lfvfl bssignfd bs tif lbst "rfbl"
     * dibrbdtfr.
     *
     * Somf implfmfntbtions, not tiis onf, tifn ovfrwritf somf of tifsf
     * dirfdtionblity propfrtifs bt "rfbl" sbmf-lfvfl-run boundbrifs by
     * L or R dodfs so tibt tif rfsolution of wfbk typfs dbn bf pfrformfd on tif
     * fntirf pbrbgrbpi bt ondf instfbd of ibving to pbrsf it ondf morf bnd
     * pfrform tibt rfsolution on sbmf-lfvfl-runs.
     * Tiis limits tif sdopf of tif implidit rulfs in ffffdtivfly
     * tif sbmf wby bs tif run limits.
     *
     * Instfbd, tiis implfmfntbtion dofs not modify tifsf dodfs.
     * On onf ibnd, tif pbrbgrbpi ibs to bf sdbnnfd for sbmf-lfvfl-runs, but
     * on tif otifr ibnd, tiis sbvfs bnotifr loop to rfsft tifsf dodfs,
     * or sbvfs mbking bnd modifying b dopy of dirProps[].
     *
     *
     * Notf tibt (Pn) bnd (Xn) dibngfd signifidbntly from vfrsion 4 of tif Bidi blgoritim.
     *
     *
     * Hbndling tif stbdk of fxplidit lfvfls (Xn):
     *
     * Witi tif Bidi stbdk of fxplidit lfvfls,
     * bs pusifd witi fbdi LRE, RLE, LRO, bnd RLO bnd poppfd witi fbdi PDF,
     * tif fxplidit lfvfl must nfvfr fxdffd MAX_EXPLICIT_LEVEL==61.
     *
     * In ordfr to ibvf b dorrfdt pusi-pop sfmbntids fvfn in tif dbsf of ovfrflows,
     * tifrf brf two ovfrflow dountfrs:
     * - dountOvfr60 is indrfmfntfd witi fbdi LRx bt lfvfl 60
     * - from lfvfl 60, onf RLx indrfbsfs tif lfvfl to 61
     * - dountOvfr61 is indrfmfntfd witi fbdi LRx bnd RLx bt lfvfl 61
     *
     * Popping lfvfls witi PDF must work in tif oppositf ordfr so tibt lfvfl 61
     * is dorrfdt bt tif dorrfdt point. Undfrflows (too mbny PDFs) must bf difdkfd.
     *
     * Tiis implfmfntbtion bssumfs tibt MAX_EXPLICIT_LEVEL is odd.
     */
    privbtf bytf rfsolvfExpliditLfvfls() {
        int i = 0;
        bytf dirProp;
        bytf lfvfl = GftPbrbLfvflAt(0);

        bytf dirdt;
        int pbrbIndfx = 0;

        /* dftfrminf if tif tfxt is mixfd-dirfdtionbl or singlf-dirfdtionbl */
        dirdt = dirfdtionFromFlbgs();

        /* wf mby not nffd to rfsolvf bny fxplidit lfvfls, but for multiplf
           pbrbgrbpis wf wbnt to loop on bll dibrs to sft tif pbrb boundbrifs */
        if ((dirdt != MIXED) && (pbrbCount == 1)) {
            /* not mixfd dirfdtionblity: lfvfls don't mbttfr - trbilingWSStbrt will bf 0 */
        } flsf if ((pbrbCount == 1) &&
                   ((flbgs & MASK_EXPLICIT) == 0)) {
            /* mixfd, but bll dibrbdtfrs brf bt tif sbmf fmbfdding lfvfl */
            /* or wf brf in "invfrsf Bidi" */
            /* bnd wf don't ibvf dontfxtubl multiplf pbrbgrbpis witi somf B dibr */
            /* sft bll lfvfls to tif pbrbgrbpi lfvfl */
            for (i = 0; i < lfngti; ++i) {
                lfvfls[i] = lfvfl;
            }
        } flsf {
            /* dontinuf to pfrform (Xn) */

            /* (X1) lfvfl is sft for bll dodfs, fmbfddingLfvfl kffps trbdk of tif pusi/pop opfrbtions */
            /* boti vbribblfs mby dbrry tif LEVEL_OVERRIDE flbg to indidbtf tif ovfrridf stbtus */
            bytf fmbfddingLfvfl = lfvfl;
            bytf nfwLfvfl;
            bytf stbdkTop = 0;

            bytf[] stbdk = nfw bytf[MAX_EXPLICIT_LEVEL];    /* wf nfvfr pusi bnytiing >=MAX_EXPLICIT_LEVEL */
            int dountOvfr60 = 0;
            int dountOvfr61 = 0;  /* dount ovfrflows of fxplidit lfvfls */

            /* rfdbldulbtf tif flbgs */
            flbgs = 0;

            for (i = 0; i < lfngti; ++i) {
                dirProp = NoContfxtRTL(dirProps[i]);
                switdi(dirProp) {
                dbsf LRE:
                dbsf LRO:
                    /* (X3, X5) */
                    nfwLfvfl = (bytf)((fmbfddingLfvfl+2) & ~(INTERNAL_LEVEL_OVERRIDE | 1)); /* lfbst grfbtfr fvfn lfvfl */
                    if (nfwLfvfl <= MAX_EXPLICIT_LEVEL) {
                        stbdk[stbdkTop] = fmbfddingLfvfl;
                        ++stbdkTop;
                        fmbfddingLfvfl = nfwLfvfl;
                        if (dirProp == LRO) {
                            fmbfddingLfvfl |= INTERNAL_LEVEL_OVERRIDE;
                        }
                        /* wf don't nffd to sft LEVEL_OVERRIDE off for LRE
                           sindf tiis ibs blrfbdy bffn donf for nfwLfvfl wiidi is
                           tif sourdf for fmbfddingLfvfl.
                         */
                    } flsf if ((fmbfddingLfvfl & ~INTERNAL_LEVEL_OVERRIDE) == MAX_EXPLICIT_LEVEL) {
                        ++dountOvfr61;
                    } flsf /* (fmbfddingLfvfl & ~INTERNAL_LEVEL_OVERRIDE) == MAX_EXPLICIT_LEVEL-1 */ {
                        ++dountOvfr60;
                    }
                    flbgs |= DirPropFlbg(BN);
                    brfbk;
                dbsf RLE:
                dbsf RLO:
                    /* (X2, X4) */
                    nfwLfvfl=(bytf)(((fmbfddingLfvfl & ~INTERNAL_LEVEL_OVERRIDE) + 1) | 1); /* lfbst grfbtfr odd lfvfl */
                    if (nfwLfvfl<=MAX_EXPLICIT_LEVEL) {
                        stbdk[stbdkTop] = fmbfddingLfvfl;
                        ++stbdkTop;
                        fmbfddingLfvfl = nfwLfvfl;
                        if (dirProp == RLO) {
                            fmbfddingLfvfl |= INTERNAL_LEVEL_OVERRIDE;
                        }
                        /* wf don't nffd to sft LEVEL_OVERRIDE off for RLE
                           sindf tiis ibs blrfbdy bffn donf for nfwLfvfl wiidi is
                           tif sourdf for fmbfddingLfvfl.
                         */
                    } flsf {
                        ++dountOvfr61;
                    }
                    flbgs |= DirPropFlbg(BN);
                    brfbk;
                dbsf PDF:
                    /* (X7) */
                    /* ibndlf bll tif ovfrflow dbsfs first */
                    if (dountOvfr61 > 0) {
                        --dountOvfr61;
                    } flsf if (dountOvfr60 > 0 && (fmbfddingLfvfl & ~INTERNAL_LEVEL_OVERRIDE) != MAX_EXPLICIT_LEVEL) {
                        /* ibndlf LRx ovfrflows from lfvfl 60 */
                        --dountOvfr60;
                    } flsf if (stbdkTop > 0) {
                        /* tiis is tif pop opfrbtion; it blso pops lfvfl 61 wiilf dountOvfr60>0 */
                        --stbdkTop;
                        fmbfddingLfvfl = stbdk[stbdkTop];
                    /* } flsf { (undfrflow) */
                    }
                    flbgs |= DirPropFlbg(BN);
                    brfbk;
                dbsf B:
                    stbdkTop = 0;
                    dountOvfr60 = 0;
                    dountOvfr61 = 0;
                    lfvfl = GftPbrbLfvflAt(i);
                    if ((i + 1) < lfngti) {
                        fmbfddingLfvfl = GftPbrbLfvflAt(i+1);
                        if (!((tfxt[i] == CR) && (tfxt[i + 1] == LF))) {
                            pbrbs[pbrbIndfx++] = i+1;
                        }
                    }
                    flbgs |= DirPropFlbg(B);
                    brfbk;
                dbsf BN:
                    /* BN, LRE, RLE, bnd PDF brf supposfd to bf rfmovfd (X9) */
                    /* tify will gft tifir lfvfls sft dorrfdtly in bdjustWSLfvfls() */
                    flbgs |= DirPropFlbg(BN);
                    brfbk;
                dffbult:
                    /* bll otifr typfs gft tif "rfbl" lfvfl */
                    if (lfvfl != fmbfddingLfvfl) {
                        lfvfl = fmbfddingLfvfl;
                        if ((lfvfl & INTERNAL_LEVEL_OVERRIDE) != 0) {
                            flbgs |= DirPropFlbgO(lfvfl) | DirPropFlbgMultiRuns;
                        } flsf {
                            flbgs |= DirPropFlbgE(lfvfl) | DirPropFlbgMultiRuns;
                        }
                    }
                    if ((lfvfl & INTERNAL_LEVEL_OVERRIDE) == 0) {
                        flbgs |= DirPropFlbg(dirProp);
                    }
                    brfbk;
                }

                /*
                 * Wf nffd to sft rfbsonbblf lfvfls fvfn on BN dodfs bnd
                 * fxplidit dodfs bfdbusf wf will lbtfr look bt sbmf-lfvfl runs (X10).
                 */
                lfvfls[i] = lfvfl;
            }
            if ((flbgs & MASK_EMBEDDING) != 0) {
                flbgs |= DirPropFlbgLR(pbrbLfvfl);
            }
            if (ordfrPbrbgrbpisLTR && (flbgs & DirPropFlbg(B)) != 0) {
                flbgs |= DirPropFlbg(L);
            }

            /* subsfqufntly, ignorf tif fxplidit dodfs bnd BN (X9) */

            /* bgbin, dftfrminf if tif tfxt is mixfd-dirfdtionbl or singlf-dirfdtionbl */
            dirdt = dirfdtionFromFlbgs();
        }

        rfturn dirdt;
    }

    /*
     * Usf b prf-spfdififd fmbfdding lfvfls brrby:
     *
     * Adjust tif dirfdtionbl propfrtifs for ovfrridfs (->LEVEL_OVERRIDE),
     * ignorf bll fxplidit dodfs (X9),
     * bnd difdk bll tif prfsft lfvfls.
     *
     * Rfdbldulbtf tif flbgs to ibvf tifm rfflfdt tif rfbl propfrtifs
     * bftfr tbking tif fxplidit fmbfddings into bddount.
     */
    privbtf bytf difdkExpliditLfvfls() {
        bytf dirProp;
        int i;
        tiis.flbgs = 0;     /* dollfdt bll dirfdtionblitifs in tif tfxt */
        bytf lfvfl;
        int pbrbIndfx = 0;

        for (i = 0; i < lfngti; ++i) {
            if (lfvfls[i] == 0) {
                lfvfls[i] = pbrbLfvfl;
            }
            if (MAX_EXPLICIT_LEVEL < (lfvfls[i]&0x7f)) {
                if ((lfvfls[i] & INTERNAL_LEVEL_OVERRIDE) != 0) {
                    lfvfls[i] =  (bytf)(pbrbLfvfl|INTERNAL_LEVEL_OVERRIDE);
                } flsf {
                    lfvfls[i] = pbrbLfvfl;
                }
            }
            lfvfl = lfvfls[i];
            dirProp = NoContfxtRTL(dirProps[i]);
            if ((lfvfl & INTERNAL_LEVEL_OVERRIDE) != 0) {
                /* kffp tif ovfrridf flbg in lfvfls[i] but bdjust tif flbgs */
                lfvfl &= ~INTERNAL_LEVEL_OVERRIDE;     /* mbkf tif rbngf difdk bflow simplfr */
                flbgs |= DirPropFlbgO(lfvfl);
            } flsf {
                /* sft tif flbgs */
                flbgs |= DirPropFlbgE(lfvfl) | DirPropFlbg(dirProp);
            }

            if ((lfvfl < GftPbrbLfvflAt(i) &&
                    !((0 == lfvfl) && (dirProp == B))) ||
                    (MAX_EXPLICIT_LEVEL <lfvfl)) {
                /* lfvfl out of bounds */
                tirow nfw IllfgblArgumfntExdfption("lfvfl " + lfvfl +
                                                   " out of bounds bt indfx " + i);
            }
            if ((dirProp == B) && ((i + 1) < lfngti)) {
                if (!((tfxt[i] == CR) && (tfxt[i + 1] == LF))) {
                    pbrbs[pbrbIndfx++] = i + 1;
                }
            }
        }
        if ((flbgs&MASK_EMBEDDING) != 0) {
            flbgs |= DirPropFlbgLR(pbrbLfvfl);
        }

        /* dftfrminf if tif tfxt is mixfd-dirfdtionbl or singlf-dirfdtionbl */
        rfturn dirfdtionFromFlbgs();
    }

    /*********************************************************************/
    /* Tif Propfrtifs stbtf mbdiinf tbblf                                */
    /*********************************************************************/
    /*                                                                   */
    /* All tbblf dflls brf 8 bits:                                       */
    /*      bits 0..4:  nfxt stbtf                                       */
    /*      bits 5..7:  bdtion to pfrform (if > 0)                       */
    /*                                                                   */
    /* Cflls mby bf of formbt "n" wifrf n rfprfsfnts tif nfxt stbtf      */
    /* (fxdfpt for tif rigitmost dolumn).                                */
    /* Cflls mby blso bf of formbt "_(x,y)" wifrf x rfprfsfnts bn bdtion */
    /* to pfrform bnd y rfprfsfnts tif nfxt stbtf.                       */
    /*                                                                   */
    /*********************************************************************/
    /* Dffinitions bnd typf for propfrtifs stbtf tbblfs                  */
    /*********************************************************************/
    privbtf stbtid finbl int IMPTABPROPS_COLUMNS = 14;
    privbtf stbtid finbl int IMPTABPROPS_RES = IMPTABPROPS_COLUMNS - 1;
    privbtf stbtid siort GftStbtfProps(siort dfll) {
        rfturn (siort)(dfll & 0x1f);
    }
    privbtf stbtid siort GftAdtionProps(siort dfll) {
        rfturn (siort)(dfll >> 5);
    }

    privbtf stbtid finbl siort groupProp[] =          /* dirProp rfgroupfd */
    {
        /*  L   R   EN  ES  ET  AN  CS  B   S   WS  ON  LRE LRO AL  RLE RLO PDF NSM BN  */
        0,  1,  2,  7,  8,  3,  9,  6,  5,  4,  4,  10, 10, 12, 10, 10, 10, 11, 10
    };
    privbtf stbtid finbl siort _L  = 0;
    privbtf stbtid finbl siort _R  = 1;
    privbtf stbtid finbl siort _EN = 2;
    privbtf stbtid finbl siort _AN = 3;
    privbtf stbtid finbl siort _ON = 4;
    privbtf stbtid finbl siort _S  = 5;
    privbtf stbtid finbl siort _B  = 6; /* rfdudfd dirProp */

    /*********************************************************************/
    /*                                                                   */
    /*      PROPERTIES  STATE  TABLE                                     */
    /*                                                                   */
    /* In tbblf impTbbProps,                                             */
    /*      - tif ON dolumn rfgroups ON bnd WS                           */
    /*      - tif BN dolumn rfgroups BN, LRE, RLE, LRO, RLO, PDF         */
    /*      - tif Rfs dolumn is tif rfdudfd propfrty bssignfd to b run   */
    /*                                                                   */
    /* Adtion 1: prodfss durrfnt run1, init nfw run1                     */
    /*        2: init nfw run2                                           */
    /*        3: prodfss run1, prodfss run2, init nfw run1               */
    /*        4: prodfss run1, sft run1=run2, init nfw run2              */
    /*                                                                   */
    /* Notfs:                                                            */
    /*  1) Tiis tbblf is usfd in rfsolvfImpliditLfvfls().                */
    /*  2) Tiis tbblf triggfrs bdtions wifn tifrf is b dibngf in tif Bidi*/
    /*     propfrty of indoming dibrbdtfrs (bdtion 1).                   */
    /*  3) Most sudi propfrty sfqufndfs brf prodfssfd immfdibtfly (in    */
    /*     fbdt, pbssfd to prodfssPropfrtySfq().                         */
    /*  4) Howfvfr, numbfrs brf bssfmblfd bs onf sfqufndf. Tiis mfbns    */
    /*     tibt undffinfd situbtions (likf CS following digits, until    */
    /*     it is known if tif nfxt dibr will bf b digit) brf ifld until  */
    /*     following dibrs dffinf tifm.                                  */
    /*     Exbmplf: digits followfd by CS, tifn domfs bnotifr CS or ON;  */
    /*              tif digits will bf prodfssfd, tifn tif CS bssignfd   */
    /*              bs tif stbrt of bn ON sfqufndf (bdtion 3).           */
    /*  5) Tifrf brf dbsfs wifrf morf tibn onf sfqufndf must bf          */
    /*     prodfssfd, for instbndf digits followfd by CS followfd by L:  */
    /*     tif digits must bf prodfssfd bs onf sfqufndf, bnd tif CS      */
    /*     must bf prodfssfd bs bn ON sfqufndf, bll tiis bfforf stbrting */
    /*     bssfmbling dibrs for tif opfning L sfqufndf.                  */
    /*                                                                   */
    /*                                                                   */
    privbtf stbtid finbl siort impTbbProps[][] =
    {
/*                        L,     R,    EN,    AN,    ON,     S,     B,    ES,    ET,    CS,    BN,   NSM,    AL,  Rfs */
/* 0 Init        */ {     1,     2,     4,     5,     7,    15,    17,     7,     9,     7,     0,     7,     3,  _ON },
/* 1 L           */ {     1,  32+2,  32+4,  32+5,  32+7, 32+15, 32+17,  32+7,  32+9,  32+7,     1,     1,  32+3,   _L },
/* 2 R           */ {  32+1,     2,  32+4,  32+5,  32+7, 32+15, 32+17,  32+7,  32+9,  32+7,     2,     2,  32+3,   _R },
/* 3 AL          */ {  32+1,  32+2,  32+6,  32+6,  32+8, 32+16, 32+17,  32+8,  32+8,  32+8,     3,     3,     3,   _R },
/* 4 EN          */ {  32+1,  32+2,     4,  32+5,  32+7, 32+15, 32+17, 64+10,    11, 64+10,     4,     4,  32+3,  _EN },
/* 5 AN          */ {  32+1,  32+2,  32+4,     5,  32+7, 32+15, 32+17,  32+7,  32+9, 64+12,     5,     5,  32+3,  _AN },
/* 6 AL:EN/AN    */ {  32+1,  32+2,     6,     6,  32+8, 32+16, 32+17,  32+8,  32+8, 64+13,     6,     6,  32+3,  _AN },
/* 7 ON          */ {  32+1,  32+2,  32+4,  32+5,     7, 32+15, 32+17,     7, 64+14,     7,     7,     7,  32+3,  _ON },
/* 8 AL:ON       */ {  32+1,  32+2,  32+6,  32+6,     8, 32+16, 32+17,     8,     8,     8,     8,     8,  32+3,  _ON },
/* 9 ET          */ {  32+1,  32+2,     4,  32+5,     7, 32+15, 32+17,     7,     9,     7,     9,     9,  32+3,  _ON },
/*10 EN+ES/CS    */ {  96+1,  96+2,     4,  96+5, 128+7, 96+15, 96+17, 128+7,128+14, 128+7,    10, 128+7,  96+3,  _EN },
/*11 EN+ET       */ {  32+1,  32+2,     4,  32+5,  32+7, 32+15, 32+17,  32+7,    11,  32+7,    11,    11,  32+3,  _EN },
/*12 AN+CS       */ {  96+1,  96+2,  96+4,     5, 128+7, 96+15, 96+17, 128+7,128+14, 128+7,    12, 128+7,  96+3,  _AN },
/*13 AL:EN/AN+CS */ {  96+1,  96+2,     6,     6, 128+8, 96+16, 96+17, 128+8, 128+8, 128+8,    13, 128+8,  96+3,  _AN },
/*14 ON+ET       */ {  32+1,  32+2, 128+4,  32+5,     7, 32+15, 32+17,     7,    14,     7,    14,    14,  32+3,  _ON },
/*15 S           */ {  32+1,  32+2,  32+4,  32+5,  32+7,    15, 32+17,  32+7,  32+9,  32+7,    15,  32+7,  32+3,   _S },
/*16 AL:S        */ {  32+1,  32+2,  32+6,  32+6,  32+8,    16, 32+17,  32+8,  32+8,  32+8,    16,  32+8,  32+3,   _S },
/*17 B           */ {  32+1,  32+2,  32+4,  32+5,  32+7, 32+15,    17,  32+7,  32+9,  32+7,    17,  32+7,  32+3,   _B }
    };

    /*********************************************************************/
    /* Tif lfvfls stbtf mbdiinf tbblfs                                   */
    /*********************************************************************/
    /*                                                                   */
    /* All tbblf dflls brf 8 bits:                                       */
    /*      bits 0..3:  nfxt stbtf                                       */
    /*      bits 4..7:  bdtion to pfrform (if > 0)                       */
    /*                                                                   */
    /* Cflls mby bf of formbt "n" wifrf n rfprfsfnts tif nfxt stbtf      */
    /* (fxdfpt for tif rigitmost dolumn).                                */
    /* Cflls mby blso bf of formbt "_(x,y)" wifrf x rfprfsfnts bn bdtion */
    /* to pfrform bnd y rfprfsfnts tif nfxt stbtf.                       */
    /*                                                                   */
    /* Tiis formbt limits fbdi tbblf to 16 stbtfs fbdi bnd to 15 bdtions.*/
    /*                                                                   */
    /*********************************************************************/
    /* Dffinitions bnd typf for lfvfls stbtf tbblfs                      */
    /*********************************************************************/
    privbtf stbtid finbl int IMPTABLEVELS_COLUMNS = _B + 2;
    privbtf stbtid finbl int IMPTABLEVELS_RES = IMPTABLEVELS_COLUMNS - 1;
    privbtf stbtid siort GftStbtf(bytf dfll) { rfturn (siort)(dfll & 0x0f); }
    privbtf stbtid siort GftAdtion(bytf dfll) { rfturn (siort)(dfll >> 4); }

    privbtf stbtid dlbss ImpTbbPbir {
        bytf[][][] imptbb;
        siort[][] impbdt;

        ImpTbbPbir(bytf[][] tbblf1, bytf[][] tbblf2,
                   siort[] bdt1, siort[] bdt2) {
            imptbb = nfw bytf[][][] {tbblf1, tbblf2};
            impbdt = nfw siort[][] {bdt1, bdt2};
        }
    }

    /*********************************************************************/
    /*                                                                   */
    /*      LEVELS  STATE  TABLES                                        */
    /*                                                                   */
    /* In bll lfvfls stbtf tbblfs,                                       */
    /*      - stbtf 0 is tif initibl stbtf                               */
    /*      - tif Rfs dolumn is tif indrfmfnt to bdd to tif tfxt lfvfl   */
    /*        for tiis propfrty sfqufndf.                                */
    /*                                                                   */
    /* Tif impbdt brrbys for fbdi tbblf of b pbir mbp tif lodbl bdtion   */
    /* numbfrs of tif tbblf to tif totbl list of bdtions. For instbndf,  */
    /* bdtion 2 in b givfn tbblf dorrfsponds to tif bdtion numbfr wiidi  */
    /* bppfbrs in fntry [2] of tif impbdt brrby for tibt tbblf.          */
    /* Tif first fntry of bll impbdt brrbys must bf 0.                   */
    /*                                                                   */
    /* Adtion 1: init donditionbl sfqufndf                               */
    /*        2: prfpfnd donditionbl sfqufndf to durrfnt sfqufndf        */
    /*        3: sft ON sfqufndf to nfw lfvfl - 1                        */
    /*        4: init EN/AN/ON sfqufndf                                  */
    /*        5: fix EN/AN/ON sfqufndf followfd by R                     */
    /*        6: sft prfvious lfvfl sfqufndf to lfvfl 2                  */
    /*                                                                   */
    /* Notfs:                                                            */
    /*  1) Tifsf tbblfs brf usfd in prodfssPropfrtySfq(). Tif input      */
    /*     is propfrty sfqufndfs bs dftfrminfd by rfsolvfImpliditLfvfls. */
    /*  2) Most sudi propfrty sfqufndfs brf prodfssfd immfdibtfly        */
    /*     (lfvfls brf bssignfd).                                        */
    /*  3) Howfvfr, somf sfqufndfs dbnnot bf bssignfd b finbl lfvfl till */
    /*     onf or morf following sfqufndfs brf rfdfivfd. For instbndf,   */
    /*     ON following bn R sfqufndf witiin bn fvfn-lfvfl pbrbgrbpi.    */
    /*     If tif following sfqufndf is R, tif ON sfqufndf will bf       */
    /*     bssignfd bbsid run lfvfl+1, bnd so will tif R sfqufndf.       */
    /*  4) S is gfnfrblly ibndlfd likf ON, sindf its lfvfl will bf fixfd */
    /*     to pbrbgrbpi lfvfl in bdjustWSLfvfls().                       */
    /*                                                                   */

    privbtf stbtid finbl bytf impTbbL_DEFAULT[][] = /* Evfn pbrbgrbpi lfvfl */
        /*  In tiis tbblf, donditionbl sfqufndfs rfdfivf tif iigifr possiblf lfvfl
            until provfn otifrwisf.
        */
    {
        /*                         L,     R,    EN,    AN,    ON,     S,     B, Rfs */
        /* 0 : init       */ {     0,     1,     0,     2,     0,     0,     0,  0 },
        /* 1 : R          */ {     0,     1,     3,     3,  0x14,  0x14,     0,  1 },
        /* 2 : AN         */ {     0,     1,     0,     2,  0x15,  0x15,     0,  2 },
        /* 3 : R+EN/AN    */ {     0,     1,     3,     3,  0x14,  0x14,     0,  2 },
        /* 4 : R+ON       */ {  0x20,     1,     3,     3,     4,     4,  0x20,  1 },
        /* 5 : AN+ON      */ {  0x20,     1,  0x20,     2,     5,     5,  0x20,  1 }
    };

    privbtf stbtid finbl bytf impTbbR_DEFAULT[][] = /* Odd  pbrbgrbpi lfvfl */
        /*  In tiis tbblf, donditionbl sfqufndfs rfdfivf tif lowfr possiblf lfvfl
            until provfn otifrwisf.
        */
    {
        /*                         L,     R,    EN,    AN,    ON,     S,     B, Rfs */
        /* 0 : init       */ {     1,     0,     2,     2,     0,     0,     0,  0 },
        /* 1 : L          */ {     1,     0,     1,     3,  0x14,  0x14,     0,  1 },
        /* 2 : EN/AN      */ {     1,     0,     2,     2,     0,     0,     0,  1 },
        /* 3 : L+AN       */ {     1,     0,     1,     3,     5,     5,     0,  1 },
        /* 4 : L+ON       */ {  0x21,     0,  0x21,     3,     4,     4,     0,  0 },
        /* 5 : L+AN+ON    */ {     1,     0,     1,     3,     5,     5,     0,  0 }
    };

    privbtf stbtid finbl siort[] impAdt0 = {0,1,2,3,4,5,6};

    privbtf stbtid finbl ImpTbbPbir impTbb_DEFAULT = nfw ImpTbbPbir(
            impTbbL_DEFAULT, impTbbR_DEFAULT, impAdt0, impAdt0);

    privbtf stbtid finbl bytf impTbbL_NUMBERS_SPECIAL[][] = { /* Evfn pbrbgrbpi lfvfl */
        /* In tiis tbblf, donditionbl sfqufndfs rfdfivf tif iigifr possiblf
           lfvfl until provfn otifrwisf.
        */
        /*                         L,     R,    EN,    AN,    ON,     S,     B, Rfs */
        /* 0 : init       */ {     0,     2,     1,     1,     0,     0,     0,  0 },
        /* 1 : L+EN/AN    */ {     0,     2,     1,     1,     0,     0,     0,  2 },
        /* 2 : R          */ {     0,     2,     4,     4,  0x13,     0,     0,  1 },
        /* 3 : R+ON       */ {  0x20,     2,     4,     4,     3,     3,  0x20,  1 },
        /* 4 : R+EN/AN    */ {     0,     2,     4,     4,  0x13,  0x13,     0,  2 }
    };
    privbtf stbtid finbl ImpTbbPbir impTbb_NUMBERS_SPECIAL = nfw ImpTbbPbir(
            impTbbL_NUMBERS_SPECIAL, impTbbR_DEFAULT, impAdt0, impAdt0);

    privbtf stbtid finbl bytf impTbbL_GROUP_NUMBERS_WITH_R[][] = {
        /* In tiis tbblf, EN/AN+ON sfqufndfs rfdfivf lfvfls bs if bssodibtfd witi R
           until provfn tibt tifrf is L or sor/for on boti sidfs. AN is ibndlfd likf EN.
        */
        /*                         L,     R,    EN,    AN,    ON,     S,     B, Rfs */
        /* 0 init         */ {     0,     3,  0x11,  0x11,     0,     0,     0,  0 },
        /* 1 EN/AN        */ {  0x20,     3,     1,     1,     2,  0x20,  0x20,  2 },
        /* 2 EN/AN+ON     */ {  0x20,     3,     1,     1,     2,  0x20,  0x20,  1 },
        /* 3 R            */ {     0,     3,     5,     5,  0x14,     0,     0,  1 },
        /* 4 R+ON         */ {  0x20,     3,     5,     5,     4,  0x20,  0x20,  1 },
        /* 5 R+EN/AN      */ {     0,     3,     5,     5,  0x14,     0,     0,  2 }
    };
    privbtf stbtid finbl bytf impTbbR_GROUP_NUMBERS_WITH_R[][] = {
        /*  In tiis tbblf, EN/AN+ON sfqufndfs rfdfivf lfvfls bs if bssodibtfd witi R
            until provfn tibt tifrf is L on boti sidfs. AN is ibndlfd likf EN.
        */
        /*                         L,     R,    EN,    AN,    ON,     S,     B, Rfs */
        /* 0 init         */ {     2,     0,     1,     1,     0,     0,     0,  0 },
        /* 1 EN/AN        */ {     2,     0,     1,     1,     0,     0,     0,  1 },
        /* 2 L            */ {     2,     0,  0x14,  0x14,  0x13,     0,     0,  1 },
        /* 3 L+ON         */ {  0x22,     0,     4,     4,     3,     0,     0,  0 },
        /* 4 L+EN/AN      */ {  0x22,     0,     4,     4,     3,     0,     0,  1 }
    };
    privbtf stbtid finbl ImpTbbPbir impTbb_GROUP_NUMBERS_WITH_R = nfw
            ImpTbbPbir(impTbbL_GROUP_NUMBERS_WITH_R,
                       impTbbR_GROUP_NUMBERS_WITH_R, impAdt0, impAdt0);

    privbtf stbtid finbl bytf impTbbL_INVERSE_NUMBERS_AS_L[][] = {
        /* Tiis tbblf is idfntidbl to tif Dffbult LTR tbblf fxdfpt tibt EN bnd AN
           brf ibndlfd likf L.
        */
        /*                         L,     R,    EN,    AN,    ON,     S,     B, Rfs */
        /* 0 : init       */ {     0,     1,     0,     0,     0,     0,     0,  0 },
        /* 1 : R          */ {     0,     1,     0,     0,  0x14,  0x14,     0,  1 },
        /* 2 : AN         */ {     0,     1,     0,     0,  0x15,  0x15,     0,  2 },
        /* 3 : R+EN/AN    */ {     0,     1,     0,     0,  0x14,  0x14,     0,  2 },
        /* 4 : R+ON       */ {  0x20,     1,  0x20,  0x20,     4,     4,  0x20,  1 },
        /* 5 : AN+ON      */ {  0x20,     1,  0x20,  0x20,     5,     5,  0x20,  1 }
    };
    privbtf stbtid finbl bytf impTbbR_INVERSE_NUMBERS_AS_L[][] = {
        /* Tiis tbblf is idfntidbl to tif Dffbult RTL tbblf fxdfpt tibt EN bnd AN
           brf ibndlfd likf L.
        */
        /*                         L,     R,    EN,    AN,    ON,     S,     B, Rfs */
        /* 0 : init       */ {     1,     0,     1,     1,     0,     0,     0,  0 },
        /* 1 : L          */ {     1,     0,     1,     1,  0x14,  0x14,     0,  1 },
        /* 2 : EN/AN      */ {     1,     0,     1,     1,     0,     0,     0,  1 },
        /* 3 : L+AN       */ {     1,     0,     1,     1,     5,     5,     0,  1 },
        /* 4 : L+ON       */ {  0x21,     0,  0x21,  0x21,     4,     4,     0,  0 },
        /* 5 : L+AN+ON    */ {     1,     0,     1,     1,     5,     5,     0,  0 }
    };
    privbtf stbtid finbl ImpTbbPbir impTbb_INVERSE_NUMBERS_AS_L = nfw ImpTbbPbir
            (impTbbL_INVERSE_NUMBERS_AS_L, impTbbR_INVERSE_NUMBERS_AS_L,
             impAdt0, impAdt0);

    privbtf stbtid finbl bytf impTbbR_INVERSE_LIKE_DIRECT[][] = {  /* Odd  pbrbgrbpi lfvfl */
        /*  In tiis tbblf, donditionbl sfqufndfs rfdfivf tif lowfr possiblf lfvfl
            until provfn otifrwisf.
        */
        /*                         L,     R,    EN,    AN,    ON,     S,     B, Rfs */
        /* 0 : init       */ {     1,     0,     2,     2,     0,     0,     0,  0 },
        /* 1 : L          */ {     1,     0,     1,     2,  0x13,  0x13,     0,  1 },
        /* 2 : EN/AN      */ {     1,     0,     2,     2,     0,     0,     0,  1 },
        /* 3 : L+ON       */ {  0x21,  0x30,     6,     4,     3,     3,  0x30,  0 },
        /* 4 : L+ON+AN    */ {  0x21,  0x30,     6,     4,     5,     5,  0x30,  3 },
        /* 5 : L+AN+ON    */ {  0x21,  0x30,     6,     4,     5,     5,  0x30,  2 },
        /* 6 : L+ON+EN    */ {  0x21,  0x30,     6,     4,     3,     3,  0x30,  1 }
    };
    privbtf stbtid finbl siort[] impAdt1 = {0,1,11,12};
    privbtf stbtid finbl ImpTbbPbir impTbb_INVERSE_LIKE_DIRECT = nfw ImpTbbPbir(
            impTbbL_DEFAULT, impTbbR_INVERSE_LIKE_DIRECT, impAdt0, impAdt1);

    privbtf stbtid finbl bytf impTbbL_INVERSE_LIKE_DIRECT_WITH_MARKS[][] = {
        /* Tif dbsf ibndlfd in tiis tbblf is (visublly):  R EN L
         */
        /*                         L,     R,    EN,    AN,    ON,     S,     B, Rfs */
        /* 0 : init       */ {     0,  0x63,     0,     1,     0,     0,     0,  0 },
        /* 1 : L+AN       */ {     0,  0x63,     0,     1,  0x12,  0x30,     0,  4 },
        /* 2 : L+AN+ON    */ {  0x20,  0x63,  0x20,     1,     2,  0x30,  0x20,  3 },
        /* 3 : R          */ {     0,  0x63,  0x55,  0x56,  0x14,  0x30,     0,  3 },
        /* 4 : R+ON       */ {  0x30,  0x43,  0x55,  0x56,     4,  0x30,  0x30,  3 },
        /* 5 : R+EN       */ {  0x30,  0x43,     5,  0x56,  0x14,  0x30,  0x30,  4 },
        /* 6 : R+AN       */ {  0x30,  0x43,  0x55,     6,  0x14,  0x30,  0x30,  4 }
    };
    privbtf stbtid finbl bytf impTbbR_INVERSE_LIKE_DIRECT_WITH_MARKS[][] = {
        /* Tif dbsfs ibndlfd in tiis tbblf brf (visublly):  R EN L
                                                            R L AN L
        */
        /*                         L,     R,    EN,    AN,    ON,     S,     B, Rfs */
        /* 0 : init       */ {  0x13,     0,     1,     1,     0,     0,     0,  0 },
        /* 1 : R+EN/AN    */ {  0x23,     0,     1,     1,     2,  0x40,     0,  1 },
        /* 2 : R+EN/AN+ON */ {  0x23,     0,     1,     1,     2,  0x40,     0,  0 },
        /* 3 : L          */ {    3 ,     0,     3,  0x36,  0x14,  0x40,     0,  1 },
        /* 4 : L+ON       */ {  0x53,  0x40,     5,  0x36,     4,  0x40,  0x40,  0 },
        /* 5 : L+ON+EN    */ {  0x53,  0x40,     5,  0x36,     4,  0x40,  0x40,  1 },
        /* 6 : L+AN       */ {  0x53,  0x40,     6,     6,     4,  0x40,  0x40,  3 }
    };
    privbtf stbtid finbl siort impAdt2[] = {0,1,7,8,9,10};
    privbtf stbtid finbl ImpTbbPbir impTbb_INVERSE_LIKE_DIRECT_WITH_MARKS =
            nfw ImpTbbPbir(impTbbL_INVERSE_LIKE_DIRECT_WITH_MARKS,
                           impTbbR_INVERSE_LIKE_DIRECT_WITH_MARKS, impAdt0, impAdt2);

    privbtf stbtid finbl ImpTbbPbir impTbb_INVERSE_FOR_NUMBERS_SPECIAL = nfw ImpTbbPbir(
            impTbbL_NUMBERS_SPECIAL, impTbbR_INVERSE_LIKE_DIRECT, impAdt0, impAdt1);

    privbtf stbtid finbl bytf impTbbL_INVERSE_FOR_NUMBERS_SPECIAL_WITH_MARKS[][] = {
        /*  Tif dbsf ibndlfd in tiis tbblf is (visublly):  R EN L
        */
        /*                         L,     R,    EN,    AN,    ON,     S,     B, Rfs */
        /* 0 : init       */ {     0,  0x62,     1,     1,     0,     0,     0,  0 },
        /* 1 : L+EN/AN    */ {     0,  0x62,     1,     1,     0,  0x30,     0,  4 },
        /* 2 : R          */ {     0,  0x62,  0x54,  0x54,  0x13,  0x30,     0,  3 },
        /* 3 : R+ON       */ {  0x30,  0x42,  0x54,  0x54,     3,  0x30,  0x30,  3 },
        /* 4 : R+EN/AN    */ {  0x30,  0x42,     4,     4,  0x13,  0x30,  0x30,  4 }
    };
    privbtf stbtid finbl ImpTbbPbir impTbb_INVERSE_FOR_NUMBERS_SPECIAL_WITH_MARKS = nfw
            ImpTbbPbir(impTbbL_INVERSE_FOR_NUMBERS_SPECIAL_WITH_MARKS,
                       impTbbR_INVERSE_LIKE_DIRECT_WITH_MARKS, impAdt0, impAdt2);

    privbtf dlbss LfvStbtf {
        bytf[][] impTbb;                /* lfvfl tbblf pointfr          */
        siort[] impAdt;                 /* bdtion mbp brrby             */
        int stbrtON;                    /* stbrt of ON sfqufndf         */
        int stbrtL2EN;                  /* stbrt of lfvfl 2 sfqufndf    */
        int lbstStrongRTL;              /* indfx of lbst found R or AL  */
        siort stbtf;                    /* durrfnt stbtf                */
        bytf runLfvfl;                  /* run lfvfl bfforf implidit solving */
    }

    /*------------------------------------------------------------------------*/

    stbtid finbl int FIRSTALLOC = 10;
    /*
     *  pbrbm pos:     position wifrf to insfrt
     *  pbrbm flbg:    onf of LRM_BEFORE, LRM_AFTER, RLM_BEFORE, RLM_AFTER
     */
    privbtf void bddPoint(int pos, int flbg)
    {
        Point point = nfw Point();

        int lfn = insfrtPoints.points.lfngti;
        if (lfn == 0) {
            insfrtPoints.points = nfw Point[FIRSTALLOC];
            lfn = FIRSTALLOC;
        }
        if (insfrtPoints.sizf >= lfn) { /* no room for nfw point */
            Point[] sbvfPoints = insfrtPoints.points;
            insfrtPoints.points = nfw Point[lfn * 2];
            Systfm.brrbydopy(sbvfPoints, 0, insfrtPoints.points, 0, lfn);
        }
        point.pos = pos;
        point.flbg = flbg;
        insfrtPoints.points[insfrtPoints.sizf] = point;
        insfrtPoints.sizf++;
    }

    /* pfrform rulfs (Wn), (Nn), bnd (In) on b run of tif tfxt ------------------ */

    /*
     * Tiis implfmfntbtion of tif (Wn) rulfs bpplifs bll rulfs in onf pbss.
     * In ordfr to do so, it nffds b look-bifbd of typidblly 1 dibrbdtfr
     * (fxdfpt for W5: sfqufndfs of ET) bnd kffps trbdk of dibngfs
     * in b rulf Wp tibt bfffdt b lbtfr Wq (p<q).
     *
     * Tif (Nn) bnd (In) rulfs brf blso pfrformfd in tibt sbmf singlf loop,
     * but ffffdtivfly onf itfrbtion bfiind for wiitf spbdf.
     *
     * Sindf bll implidit rulfs brf pfrformfd in onf stfp, it is not nfdfssbry
     * to bdtublly storf tif intfrmfdibtf dirfdtionbl propfrtifs in dirProps[].
     */

    privbtf void prodfssPropfrtySfq(LfvStbtf lfvStbtf, siort _prop,
            int stbrt, int limit) {
        bytf dfll;
        bytf[][] impTbb = lfvStbtf.impTbb;
        siort[] impAdt = lfvStbtf.impAdt;
        siort oldStbtfSfq,bdtionSfq;
        bytf lfvfl, bddLfvfl;
        int stbrt0, k;

        stbrt0 = stbrt;                 /* sbvf originbl stbrt position */
        oldStbtfSfq = lfvStbtf.stbtf;
        dfll = impTbb[oldStbtfSfq][_prop];
        lfvStbtf.stbtf = GftStbtf(dfll);        /* isolbtf tif nfw stbtf */
        bdtionSfq = impAdt[GftAdtion(dfll)];    /* isolbtf tif bdtion */
        bddLfvfl = impTbb[lfvStbtf.stbtf][IMPTABLEVELS_RES];

        if (bdtionSfq != 0) {
            switdi (bdtionSfq) {
            dbsf 1:                     /* init ON sfq */
                lfvStbtf.stbrtON = stbrt0;
                brfbk;

            dbsf 2:                     /* prfpfnd ON sfq to durrfnt sfq */
                stbrt = lfvStbtf.stbrtON;
                brfbk;

            dbsf 3:                     /* L or S bftfr possiblf rflfvbnt EN/AN */
                /* difdk if wf ibd EN bftfr R/AL */
                if (lfvStbtf.stbrtL2EN >= 0) {
                    bddPoint(lfvStbtf.stbrtL2EN, LRM_BEFORE);
                }
                lfvStbtf.stbrtL2EN = -1;  /* not witiin prfvious if sindf dould blso bf -2 */
                /* difdk if wf ibd bny rflfvbnt EN/AN bftfr R/AL */
                if ((insfrtPoints.points.lfngti == 0) ||
                        (insfrtPoints.sizf <= insfrtPoints.donfirmfd)) {
                    /* notiing, just dlfbn up */
                    lfvStbtf.lbstStrongRTL = -1;
                    /* difdk if wf ibvf b pfnding donditionbl sfgmfnt */
                    lfvfl = impTbb[oldStbtfSfq][IMPTABLEVELS_RES];
                    if ((lfvfl & 1) != 0 && lfvStbtf.stbrtON > 0) { /* bftfr ON */
                        stbrt = lfvStbtf.stbrtON;   /* rfsft to bbsid run lfvfl */
                    }
                    if (_prop == _S) {              /* bdd LRM bfforf S */
                        bddPoint(stbrt0, LRM_BEFORE);
                        insfrtPoints.donfirmfd = insfrtPoints.sizf;
                    }
                    brfbk;
                }
                /* rfsft prfvious RTL dont to lfvfl for LTR tfxt */
                for (k = lfvStbtf.lbstStrongRTL + 1; k < stbrt0; k++) {
                    /* rfsft odd lfvfl, lfbvf runLfvfl+2 bs is */
                    lfvfls[k] = (bytf)((lfvfls[k] - 2) & ~1);
                }
                /* mbrk insfrt points bs donfirmfd */
                insfrtPoints.donfirmfd = insfrtPoints.sizf;
                lfvStbtf.lbstStrongRTL = -1;
                if (_prop == _S) {           /* bdd LRM bfforf S */
                    bddPoint(stbrt0, LRM_BEFORE);
                    insfrtPoints.donfirmfd = insfrtPoints.sizf;
                }
                brfbk;

            dbsf 4:                     /* R/AL bftfr possiblf rflfvbnt EN/AN */
                /* just dlfbn up */
                if (insfrtPoints.points.lfngti > 0)
                    /* rfmovf bll non donfirmfd insfrt points */
                    insfrtPoints.sizf = insfrtPoints.donfirmfd;
                lfvStbtf.stbrtON = -1;
                lfvStbtf.stbrtL2EN = -1;
                lfvStbtf.lbstStrongRTL = limit - 1;
                brfbk;

            dbsf 5:                     /* EN/AN bftfr R/AL + possiblf dont */
                /* difdk for rfbl AN */
                if ((_prop == _AN) && (NoContfxtRTL(dirProps[stbrt0]) == AN)) {
                    /* rfbl AN */
                    if (lfvStbtf.stbrtL2EN == -1) { /* if no rflfvbnt EN blrfbdy found */
                        /* just notf tif rigimost digit bs b strong RTL */
                        lfvStbtf.lbstStrongRTL = limit - 1;
                        brfbk;
                    }
                    if (lfvStbtf.stbrtL2EN >= 0)  { /* bftfr EN, no AN */
                        bddPoint(lfvStbtf.stbrtL2EN, LRM_BEFORE);
                        lfvStbtf.stbrtL2EN = -2;
                    }
                    /* notf AN */
                    bddPoint(stbrt0, LRM_BEFORE);
                    brfbk;
                }
                /* if first EN/AN bftfr R/AL */
                if (lfvStbtf.stbrtL2EN == -1) {
                    lfvStbtf.stbrtL2EN = stbrt0;
                }
                brfbk;

            dbsf 6:                     /* notf lodbtion of lbtfst R/AL */
                lfvStbtf.lbstStrongRTL = limit - 1;
                lfvStbtf.stbrtON = -1;
                brfbk;

            dbsf 7:                     /* L bftfr R+ON/EN/AN */
                /* indludf possiblf bdjbdfnt numbfr on tif lfft */
                for (k = stbrt0-1; k >= 0 && ((lfvfls[k] & 1) == 0); k--) {
                }
                if (k >= 0) {
                    bddPoint(k, RLM_BEFORE);    /* bdd RLM bfforf */
                    insfrtPoints.donfirmfd = insfrtPoints.sizf; /* donfirm it */
                }
                lfvStbtf.stbrtON = stbrt0;
                brfbk;

            dbsf 8:                     /* AN bftfr L */
                /* AN numbfrs bftwffn L tfxt on boti sidfs mby bf troublf. */
                /* tfntbtivfly brbdkft witi LRMs; will bf donfirmfd if followfd by L */
                bddPoint(stbrt0, LRM_BEFORE);   /* bdd LRM bfforf */
                bddPoint(stbrt0, LRM_AFTER);    /* bdd LRM bftfr  */
                brfbk;

            dbsf 9:                     /* R bftfr L+ON/EN/AN */
                /* fblsf blfrt, infirm LRMs bround prfvious AN */
                insfrtPoints.sizf=insfrtPoints.donfirmfd;
                if (_prop == _S) {          /* bdd RLM bfforf S */
                    bddPoint(stbrt0, RLM_BEFORE);
                    insfrtPoints.donfirmfd = insfrtPoints.sizf;
                }
                brfbk;

            dbsf 10:                    /* L bftfr L+ON/AN */
                lfvfl = (bytf)(lfvStbtf.runLfvfl + bddLfvfl);
                for (k=lfvStbtf.stbrtON; k < stbrt0; k++) {
                    if (lfvfls[k] < lfvfl) {
                        lfvfls[k] = lfvfl;
                    }
                }
                insfrtPoints.donfirmfd = insfrtPoints.sizf;   /* donfirm insfrts */
                lfvStbtf.stbrtON = stbrt0;
                brfbk;

            dbsf 11:                    /* L bftfr L+ON+EN/AN/ON */
                lfvfl = lfvStbtf.runLfvfl;
                for (k = stbrt0-1; k >= lfvStbtf.stbrtON; k--) {
                    if (lfvfls[k] == lfvfl+3) {
                        wiilf (lfvfls[k] == lfvfl+3) {
                            lfvfls[k--] -= 2;
                        }
                        wiilf (lfvfls[k] == lfvfl) {
                            k--;
                        }
                    }
                    if (lfvfls[k] == lfvfl+2) {
                        lfvfls[k] = lfvfl;
                        dontinuf;
                    }
                    lfvfls[k] = (bytf)(lfvfl+1);
                }
                brfbk;

            dbsf 12:                    /* R bftfr L+ON+EN/AN/ON */
                lfvfl = (bytf)(lfvStbtf.runLfvfl+1);
                for (k = stbrt0-1; k >= lfvStbtf.stbrtON; k--) {
                    if (lfvfls[k] > lfvfl) {
                        lfvfls[k] -= 2;
                    }
                }
                brfbk;

            dffbult:                        /* wf siould nfvfr gft ifrf */
                tirow nfw IllfgblStbtfExdfption("Intfrnbl ICU frror in prodfssPropfrtySfq");
            }
        }
        if ((bddLfvfl) != 0 || (stbrt < stbrt0)) {
            lfvfl = (bytf)(lfvStbtf.runLfvfl + bddLfvfl);
            for (k = stbrt; k < limit; k++) {
                lfvfls[k] = lfvfl;
            }
        }
    }

    privbtf void rfsolvfImpliditLfvfls(int stbrt, int limit, siort sor, siort for)
    {
        LfvStbtf lfvStbtf = nfw LfvStbtf();
        int i, stbrt1, stbrt2;
        siort oldStbtfImp, stbtfImp, bdtionImp;
        siort gprop, rfsProp, dfll;
        siort nfxtStrongProp = R;
        int nfxtStrongPos = -1;


        /* difdk for RTL invfrsf Bidi modf */
        /* FOOD FOR THOUGHT: in dbsf of RTL invfrsf Bidi, it would mbkf sfnsf to
         * loop on tif tfxt dibrbdtfrs from fnd to stbrt.
         * Tiis would nffd b difffrfnt propfrtifs stbtf tbblf (bt lfbst difffrfnt
         * bdtions) bnd difffrfnt lfvfls stbtf tbblfs (mbybf vfry similbr to tif
         * LTR dorrfsponding onfs.
         */
        /* initiblizf for lfvfls stbtf tbblf */
        lfvStbtf.stbrtL2EN = -1;        /* usfd for INVERSE_LIKE_DIRECT_WITH_MARKS */
        lfvStbtf.lbstStrongRTL = -1;    /* usfd for INVERSE_LIKE_DIRECT_WITH_MARKS */
        lfvStbtf.stbtf = 0;
        lfvStbtf.runLfvfl = lfvfls[stbrt];
        lfvStbtf.impTbb = impTbbPbir.imptbb[lfvStbtf.runLfvfl & 1];
        lfvStbtf.impAdt = impTbbPbir.impbdt[lfvStbtf.runLfvfl & 1];
        prodfssPropfrtySfq(lfvStbtf, sor, stbrt, stbrt);
        /* initiblizf for propfrty stbtf tbblf */
        if (dirProps[stbrt] == NSM) {
            stbtfImp = (siort)(1 + sor);
        } flsf {
            stbtfImp = 0;
        }
        stbrt1 = stbrt;
        stbrt2 = 0;

        for (i = stbrt; i <= limit; i++) {
            if (i >= limit) {
                gprop = for;
            } flsf {
                siort prop, prop1;
                prop = NoContfxtRTL(dirProps[i]);
                gprop = groupProp[prop];
            }
            oldStbtfImp = stbtfImp;
            dfll = impTbbProps[oldStbtfImp][gprop];
            stbtfImp = GftStbtfProps(dfll);     /* isolbtf tif nfw stbtf */
            bdtionImp = GftAdtionProps(dfll);   /* isolbtf tif bdtion */
            if ((i == limit) && (bdtionImp == 0)) {
                /* tifrf is bn unprodfssfd sfqufndf if its propfrty == for   */
                bdtionImp = 1;                  /* prodfss tif lbst sfqufndf */
            }
            if (bdtionImp != 0) {
                rfsProp = impTbbProps[oldStbtfImp][IMPTABPROPS_RES];
                switdi (bdtionImp) {
                dbsf 1:             /* prodfss durrfnt sfq1, init nfw sfq1 */
                    prodfssPropfrtySfq(lfvStbtf, rfsProp, stbrt1, i);
                    stbrt1 = i;
                    brfbk;
                dbsf 2:             /* init nfw sfq2 */
                    stbrt2 = i;
                    brfbk;
                dbsf 3:             /* prodfss sfq1, prodfss sfq2, init nfw sfq1 */
                    prodfssPropfrtySfq(lfvStbtf, rfsProp, stbrt1, stbrt2);
                    prodfssPropfrtySfq(lfvStbtf, _ON, stbrt2, i);
                    stbrt1 = i;
                    brfbk;
                dbsf 4:             /* prodfss sfq1, sft sfq1=sfq2, init nfw sfq2 */
                    prodfssPropfrtySfq(lfvStbtf, rfsProp, stbrt1, stbrt2);
                    stbrt1 = stbrt2;
                    stbrt2 = i;
                    brfbk;
                dffbult:            /* wf siould nfvfr gft ifrf */
                    tirow nfw IllfgblStbtfExdfption("Intfrnbl ICU frror in rfsolvfImpliditLfvfls");
                }
            }
        }
        /* flusi possiblf pfnding sfqufndf, f.g. ON */
        prodfssPropfrtySfq(lfvStbtf, for, limit, limit);
    }

    /* pfrform (L1) bnd (X9) ---------------------------------------------------- */

    /*
     * Rfsft tif fmbfdding lfvfls for somf non-grbpiid dibrbdtfrs (L1).
     * Tiis mftiod blso sfts bppropribtf lfvfls for BN, bnd
     * fxplidit fmbfdding typfs tibt brf supposfd to ibvf bffn rfmovfd
     * from tif pbrbgrbpi in (X9).
     */
    privbtf void bdjustWSLfvfls() {
        int i;

        if ((flbgs & MASK_WS) != 0) {
            int flbg;
            i = trbilingWSStbrt;
            wiilf (i > 0) {
                /* rfsft b sfqufndf of WS/BN bfforf fop bnd B/S to tif pbrbgrbpi pbrbLfvfl */
                wiilf (i > 0 && ((flbg = DirPropFlbgNC(dirProps[--i])) & MASK_WS) != 0) {
                    if (ordfrPbrbgrbpisLTR && (flbg & DirPropFlbg(B)) != 0) {
                        lfvfls[i] = 0;
                    } flsf {
                        lfvfls[i] = GftPbrbLfvflAt(i);
                    }
                }

                /* rfsft BN to tif nfxt dibrbdtfr's pbrbLfvfl until B/S, wiidi rfstbrts bbovf loop */
                /* ifrf, i+1 is gubrbntffd to bf <lfngti */
                wiilf (i > 0) {
                    flbg = DirPropFlbgNC(dirProps[--i]);
                    if ((flbg & MASK_BN_EXPLICIT) != 0) {
                        lfvfls[i] = lfvfls[i + 1];
                    } flsf if (ordfrPbrbgrbpisLTR && (flbg & DirPropFlbg(B)) != 0) {
                        lfvfls[i] = 0;
                        brfbk;
                    } flsf if ((flbg & MASK_B_S) != 0){
                        lfvfls[i] = GftPbrbLfvflAt(i);
                        brfbk;
                    }
                }
            }
        }
    }

    privbtf int Bidi_Min(int x, int y) {
        rfturn x < y ? x : y;
    }

    privbtf int Bidi_Abs(int x) {
        rfturn x >= 0 ? x : -x;
    }

    /**
     * Pfrform tif Unidodf Bidi blgoritim. It is dffinfd in tif
     * <b irff="ittp://www.unidodf.org/unidodf/rfports/tr9/">Unidodf Stbndbrd Annfx #9</b>,
     * vfrsion 13,
     * blso dfsdribfd in Tif Unidodf Stbndbrd, Vfrsion 4.0 .<p>
     *
     * Tiis mftiod tbkfs b pifdf of plbin tfxt dontbining onf or morf pbrbgrbpis,
     * witi or witiout fxtfrnblly spfdififd fmbfdding lfvfls from <i>stylfd</i>
     * tfxt bnd domputfs tif lfft-rigit-dirfdtionblity of fbdi dibrbdtfr.<p>
     *
     * If tif fntirf tfxt is bll of tif sbmf dirfdtionblity, tifn
     * tif mftiod mby not pfrform bll tif stfps dfsdribfd by tif blgoritim,
     * i.f., somf lfvfls mby not bf tif sbmf bs if bll stfps wfrf pfrformfd.
     * Tiis is not rflfvbnt for unidirfdtionbl tfxt.<br>
     * For fxbmplf, in purf LTR tfxt witi numbfrs tif numbfrs would gft
     * b rfsolvfd lfvfl of 2 iigifr tibn tif surrounding tfxt bddording to
     * tif blgoritim. Tiis implfmfntbtion mby sft bll rfsolvfd lfvfls to
     * tif sbmf vbluf in sudi b dbsf.<p>
     *
     * Tif tfxt dbn bf domposfd of multiplf pbrbgrbpis. Oddurrfndf of b blodk
     * sfpbrbtor in tif tfxt tfrminbtfs b pbrbgrbpi, bnd wibtfvfr domfs nfxt stbrts
     * b nfw pbrbgrbpi. Tif fxdfption to tiis rulf is wifn b Cbrribgf Rfturn (CR)
     * is followfd by b Linf Fffd (LF). Boti CR bnd LF brf blodk sfpbrbtors, but
     * in tibt dbsf, tif pbir of dibrbdtfrs is donsidfrfd bs tfrminbting tif
     * prfdfding pbrbgrbpi, bnd b nfw pbrbgrbpi will bf stbrtfd by b dibrbdtfr
     * doming bftfr tif LF.
     *
     * Altiougi tif tfxt is pbssfd ifrf bs b <dodf>String</dodf>, it is
     * storfd intfrnblly bs bn brrby of dibrbdtfrs. Tifrfforf tif
     * dodumfntbtion will rfffr to indfxfs of tif dibrbdtfrs in tif tfxt.
     *
     * @pbrbm tfxt dontbins tif tfxt tibt tif Bidi blgoritim will bf pfrformfd
     *        on. Tiis tfxt dbn bf rftrifvfd witi <dodf>gftTfxt()</dodf> or
     *        <dodf>gftTfxtAsString</dodf>.<br>
     *
     * @pbrbm pbrbLfvfl spfdififs tif dffbult lfvfl for tif tfxt;
     *        it is typidblly 0 (LTR) or 1 (RTL).
     *        If tif mftiod sibll dftfrminf tif pbrbgrbpi lfvfl from tif tfxt,
     *        tifn <dodf>pbrbLfvfl</dodf> dbn bf sft to
     *        fitifr <dodf>LEVEL_DEFAULT_LTR</dodf>
     *        or <dodf>LEVEL_DEFAULT_RTL</dodf>; if tif tfxt dontbins multiplf
     *        pbrbgrbpis, tif pbrbgrbpi lfvfl sibll bf dftfrminfd sfpbrbtfly for
     *        fbdi pbrbgrbpi; if b pbrbgrbpi dofs not indludf bny strongly typfd
     *        dibrbdtfr, tifn tif dfsirfd dffbult is usfd (0 for LTR or 1 for RTL).
     *        Any otifr vbluf bftwffn 0 bnd <dodf>MAX_EXPLICIT_LEVEL</dodf>
     *        is blso vblid, witi odd lfvfls indidbting RTL.
     *
     * @pbrbm fmbfddingLfvfls (in) mby bf usfd to prfsft tif fmbfdding bnd ovfrridf lfvfls,
     *        ignoring dibrbdtfrs likf LRE bnd PDF in tif tfxt.
     *        A lfvfl ovfrridfs tif dirfdtionbl propfrty of its dorrfsponding
     *        (sbmf indfx) dibrbdtfr if tif lfvfl ibs tif
     *        <dodf>LEVEL_OVERRIDE</dodf> bit sft.<br><br>
     *        Exdfpt for tibt bit, it must bf
     *        <dodf>pbrbLfvfl<=fmbfddingLfvfls[]<=MAX_EXPLICIT_LEVEL</dodf>,
     *        witi onf fxdfption: b lfvfl of zfro mby bf spfdififd for b
     *        pbrbgrbpi sfpbrbtor fvfn if <dodf>pbrbLfvfl&gt;0</dodf> wifn multiplf
     *        pbrbgrbpis brf submittfd in tif sbmf dbll to <dodf>sftPbrb()</dodf>.<br><br>
     *        <strong>Cbution: </strong>A rfffrfndf to tiis brrby, not b dopy
     *        of tif lfvfls, will bf storfd in tif <dodf>Bidi</dodf> objfdt;
     *        tif <dodf>fmbfddingLfvfls</dodf>
     *        siould not bf modififd to bvoid unfxpfdtfd rfsults on subsfqufnt
     *        Bidi opfrbtions. Howfvfr, tif <dodf>sftPbrb()</dodf> bnd
     *        <dodf>sftLinf()</dodf> mftiods mby modify somf or bll of tif
     *        lfvfls.<br><br>
     *        <strong>Notf:</strong> tif <dodf>fmbfddingLfvfls</dodf> brrby must
     *        ibvf onf fntry for fbdi dibrbdtfr in <dodf>tfxt</dodf>.
     *
     * @tirows IllfgblArgumfntExdfption if tif vblufs in fmbfddingLfvfls brf
     *         not witiin tif bllowfd rbngf
     *
     * @sff #LEVEL_DEFAULT_LTR
     * @sff #LEVEL_DEFAULT_RTL
     * @sff #LEVEL_OVERRIDE
     * @sff #MAX_EXPLICIT_LEVEL
     * @stbblf ICU 3.8
     */
    void sftPbrb(String tfxt, bytf pbrbLfvfl, bytf[] fmbfddingLfvfls)
    {
        if (tfxt == null) {
            sftPbrb(nfw dibr[0], pbrbLfvfl, fmbfddingLfvfls);
        } flsf {
            sftPbrb(tfxt.toCibrArrby(), pbrbLfvfl, fmbfddingLfvfls);
        }
    }

    /**
     * Pfrform tif Unidodf Bidi blgoritim. It is dffinfd in tif
     * <b irff="ittp://www.unidodf.org/unidodf/rfports/tr9/">Unidodf Stbndbrd Annfx #9</b>,
     * vfrsion 13,
     * blso dfsdribfd in Tif Unidodf Stbndbrd, Vfrsion 4.0 .<p>
     *
     * Tiis mftiod tbkfs b pifdf of plbin tfxt dontbining onf or morf pbrbgrbpis,
     * witi or witiout fxtfrnblly spfdififd fmbfdding lfvfls from <i>stylfd</i>
     * tfxt bnd domputfs tif lfft-rigit-dirfdtionblity of fbdi dibrbdtfr.<p>
     *
     * If tif fntirf tfxt is bll of tif sbmf dirfdtionblity, tifn
     * tif mftiod mby not pfrform bll tif stfps dfsdribfd by tif blgoritim,
     * i.f., somf lfvfls mby not bf tif sbmf bs if bll stfps wfrf pfrformfd.
     * Tiis is not rflfvbnt for unidirfdtionbl tfxt.<br>
     * For fxbmplf, in purf LTR tfxt witi numbfrs tif numbfrs would gft
     * b rfsolvfd lfvfl of 2 iigifr tibn tif surrounding tfxt bddording to
     * tif blgoritim. Tiis implfmfntbtion mby sft bll rfsolvfd lfvfls to
     * tif sbmf vbluf in sudi b dbsf.<p>
     *
     * Tif tfxt dbn bf domposfd of multiplf pbrbgrbpis. Oddurrfndf of b blodk
     * sfpbrbtor in tif tfxt tfrminbtfs b pbrbgrbpi, bnd wibtfvfr domfs nfxt stbrts
     * b nfw pbrbgrbpi. Tif fxdfption to tiis rulf is wifn b Cbrribgf Rfturn (CR)
     * is followfd by b Linf Fffd (LF). Boti CR bnd LF brf blodk sfpbrbtors, but
     * in tibt dbsf, tif pbir of dibrbdtfrs is donsidfrfd bs tfrminbting tif
     * prfdfding pbrbgrbpi, bnd b nfw pbrbgrbpi will bf stbrtfd by b dibrbdtfr
     * doming bftfr tif LF.
     *
     * Tif tfxt is storfd intfrnblly bs bn brrby of dibrbdtfrs. Tifrfforf tif
     * dodumfntbtion will rfffr to indfxfs of tif dibrbdtfrs in tif tfxt.
     *
     * @pbrbm dibrs dontbins tif tfxt tibt tif Bidi blgoritim will bf pfrformfd
     *        on. Tiis tfxt dbn bf rftrifvfd witi <dodf>gftTfxt()</dodf> or
     *        <dodf>gftTfxtAsString</dodf>.<br>
     *
     * @pbrbm pbrbLfvfl spfdififs tif dffbult lfvfl for tif tfxt;
     *        it is typidblly 0 (LTR) or 1 (RTL).
     *        If tif mftiod sibll dftfrminf tif pbrbgrbpi lfvfl from tif tfxt,
     *        tifn <dodf>pbrbLfvfl</dodf> dbn bf sft to
     *        fitifr <dodf>LEVEL_DEFAULT_LTR</dodf>
     *        or <dodf>LEVEL_DEFAULT_RTL</dodf>; if tif tfxt dontbins multiplf
     *        pbrbgrbpis, tif pbrbgrbpi lfvfl sibll bf dftfrminfd sfpbrbtfly for
     *        fbdi pbrbgrbpi; if b pbrbgrbpi dofs not indludf bny strongly typfd
     *        dibrbdtfr, tifn tif dfsirfd dffbult is usfd (0 for LTR or 1 for RTL).
     *        Any otifr vbluf bftwffn 0 bnd <dodf>MAX_EXPLICIT_LEVEL</dodf>
     *        is blso vblid, witi odd lfvfls indidbting RTL.
     *
     * @pbrbm fmbfddingLfvfls (in) mby bf usfd to prfsft tif fmbfdding bnd
     *        ovfrridf lfvfls, ignoring dibrbdtfrs likf LRE bnd PDF in tif tfxt.
     *        A lfvfl ovfrridfs tif dirfdtionbl propfrty of its dorrfsponding
     *        (sbmf indfx) dibrbdtfr if tif lfvfl ibs tif
     *        <dodf>LEVEL_OVERRIDE</dodf> bit sft.<br><br>
     *        Exdfpt for tibt bit, it must bf
     *        <dodf>pbrbLfvfl<=fmbfddingLfvfls[]<=MAX_EXPLICIT_LEVEL</dodf>,
     *        witi onf fxdfption: b lfvfl of zfro mby bf spfdififd for b
     *        pbrbgrbpi sfpbrbtor fvfn if <dodf>pbrbLfvfl&gt;0</dodf> wifn multiplf
     *        pbrbgrbpis brf submittfd in tif sbmf dbll to <dodf>sftPbrb()</dodf>.<br><br>
     *        <strong>Cbution: </strong>A rfffrfndf to tiis brrby, not b dopy
     *        of tif lfvfls, will bf storfd in tif <dodf>Bidi</dodf> objfdt;
     *        tif <dodf>fmbfddingLfvfls</dodf>
     *        siould not bf modififd to bvoid unfxpfdtfd rfsults on subsfqufnt
     *        Bidi opfrbtions. Howfvfr, tif <dodf>sftPbrb()</dodf> bnd
     *        <dodf>sftLinf()</dodf> mftiods mby modify somf or bll of tif
     *        lfvfls.<br><br>
     *        <strong>Notf:</strong> tif <dodf>fmbfddingLfvfls</dodf> brrby must
     *        ibvf onf fntry for fbdi dibrbdtfr in <dodf>tfxt</dodf>.
     *
     * @tirows IllfgblArgumfntExdfption if tif vblufs in fmbfddingLfvfls brf
     *         not witiin tif bllowfd rbngf
     *
     * @sff #LEVEL_DEFAULT_LTR
     * @sff #LEVEL_DEFAULT_RTL
     * @sff #LEVEL_OVERRIDE
     * @sff #MAX_EXPLICIT_LEVEL
     * @stbblf ICU 3.8
     */
    publid void sftPbrb(dibr[] dibrs, bytf pbrbLfvfl, bytf[] fmbfddingLfvfls)
    {
        /* difdk tif brgumfnt vblufs */
        if (pbrbLfvfl < INTERNAL_LEVEL_DEFAULT_LTR) {
            vfrifyRbngf(pbrbLfvfl, 0, MAX_EXPLICIT_LEVEL + 1);
        }
        if (dibrs == null) {
            dibrs = nfw dibr[0];
        }

        /* initiblizf tif Bidi objfdt */
        tiis.pbrbBidi = null;          /* mbrk unfinisifd sftPbrb */
        tiis.tfxt = dibrs;
        tiis.lfngti = tiis.originblLfngti = tiis.rfsultLfngti = tfxt.lfngti;
        tiis.pbrbLfvfl = pbrbLfvfl;
        tiis.dirfdtion = Bidi.DIRECTION_LEFT_TO_RIGHT;
        tiis.pbrbCount = 1;

        /* Allodbtf zfro-lfngti brrbys instfbd of sftting to null ifrf; tifn
         * difdks for null in vbrious plbdfs dbn bf fliminbtfd.
         */
        dirProps = nfw bytf[0];
        lfvfls = nfw bytf[0];
        runs = nfw BidiRun[0];
        isGoodLogidblToVisublRunsMbp = fblsf;
        insfrtPoints.sizf = 0;          /* dlfbn up from lbst dbll */
        insfrtPoints.donfirmfd = 0;     /* dlfbn up from lbst dbll */

        /*
         * Sbvf tif originbl pbrbLfvfl if dontfxtubl; otifrwisf, sft to 0.
         */
        if (IsDffbultLfvfl(pbrbLfvfl)) {
            dffbultPbrbLfvfl = pbrbLfvfl;
        } flsf {
            dffbultPbrbLfvfl = 0;
        }

        if (lfngti == 0) {
            /*
             * For bn fmpty pbrbgrbpi, drfbtf b Bidi objfdt witi tif pbrbLfvfl bnd
             * tif flbgs bnd tif dirfdtion sft but witiout bllodbting zfro-lfngti brrbys.
             * Tifrf is notiing morf to do.
             */
            if (IsDffbultLfvfl(pbrbLfvfl)) {
                tiis.pbrbLfvfl &= 1;
                dffbultPbrbLfvfl = 0;
            }
            if ((tiis.pbrbLfvfl & 1) != 0) {
                flbgs = DirPropFlbg(R);
                dirfdtion = Bidi.DIRECTION_RIGHT_TO_LEFT;
            } flsf {
                flbgs = DirPropFlbg(L);
                dirfdtion = Bidi.DIRECTION_LEFT_TO_RIGHT;
            }

            runCount = 0;
            pbrbCount = 0;
            pbrbBidi = tiis;         /* mbrk suddfssful sftPbrb */
            rfturn;
        }

        runCount = -1;

        /*
         * Gft tif dirfdtionbl propfrtifs,
         * tif flbgs bit-sft, bnd
         * dftfrminf tif pbrbgrbpi lfvfl if nfdfssbry.
         */
        gftDirPropsMfmory(lfngti);
        dirProps = dirPropsMfmory;
        gftDirProps();

        /* tif prodfssfd lfngti mby ibvf dibngfd if OPTION_STREAMING is sft */
        trbilingWSStbrt = lfngti;  /* tif lfvfls[] will rfflfdt tif WS run */

        /* bllodbtf pbrbs mfmory */
        if (pbrbCount > 1) {
            gftInitiblPbrbsMfmory(pbrbCount);
            pbrbs = pbrbsMfmory;
            pbrbs[pbrbCount - 1] = lfngti;
        } flsf {
            /* initiblizf pbrbs for singlf pbrbgrbpi */
            pbrbs = simplfPbrbs;
            simplfPbrbs[0] = lfngti;
        }

        /* brf fxplidit lfvfls spfdififd? */
        if (fmbfddingLfvfls == null) {
            /* no: dftfrminf fxplidit lfvfls bddording to tif (Xn) rulfs */
            gftLfvflsMfmory(lfngti);
            lfvfls = lfvflsMfmory;
            dirfdtion = rfsolvfExpliditLfvfls();
        } flsf {
            /* sft BN for bll fxplidit dodfs, difdk tibt bll lfvfls brf 0 or pbrbLfvfl..MAX_EXPLICIT_LEVEL */
            lfvfls = fmbfddingLfvfls;
            dirfdtion = difdkExpliditLfvfls();
        }

        /*
         * Tif stfps bftfr (X9) in tif Bidi blgoritim brf pfrformfd only if
         * tif pbrbgrbpi tfxt ibs mixfd dirfdtionblity!
         */
        switdi (dirfdtion) {
        dbsf Bidi.DIRECTION_LEFT_TO_RIGHT:
            /* mbkf surf pbrbLfvfl is fvfn */
            pbrbLfvfl = (bytf)((pbrbLfvfl + 1) & ~1);

            /* bll lfvfls brf impliditly bt pbrbLfvfl (importbnt for gftLfvfls()) */
            trbilingWSStbrt = 0;
            brfbk;
        dbsf Bidi.DIRECTION_RIGHT_TO_LEFT:
            /* mbkf surf pbrbLfvfl is odd */
            pbrbLfvfl |= 1;

            /* bll lfvfls brf impliditly bt pbrbLfvfl (importbnt for gftLfvfls()) */
            trbilingWSStbrt = 0;
            brfbk;
        dffbult:
            tiis.impTbbPbir = impTbb_DEFAULT;

            /*
             * If tifrf brf no fxtfrnbl lfvfls spfdififd bnd tifrf
             * brf no signifidbnt fxplidit lfvfl dodfs in tif tfxt,
             * tifn wf dbn trfbt tif fntirf pbrbgrbpi bs onf run.
             * Otifrwisf, wf nffd to pfrform tif following rulfs on runs of
             * tif tfxt witi tif sbmf fmbfdding lfvfls. (X10)
             * "Signifidbnt" fxplidit lfvfl dodfs brf onfs tibt bdtublly
             * bfffdt non-BN dibrbdtfrs.
             * Exbmplfs for "insignifidbnt" onfs brf fmpty fmbfddings
             * LRE-PDF, LRE-RLE-PDF-PDF, ftd.
             */
            if (fmbfddingLfvfls == null && pbrbCount <= 1 &&
                (flbgs & DirPropFlbgMultiRuns) == 0) {
                rfsolvfImpliditLfvfls(0, lfngti,
                        GftLRFromLfvfl(GftPbrbLfvflAt(0)),
                        GftLRFromLfvfl(GftPbrbLfvflAt(lfngti - 1)));
            } flsf {
                /* sor, for: stbrt bnd fnd typfs of sbmf-lfvfl-run */
                int stbrt, limit = 0;
                bytf lfvfl, nfxtLfvfl;
                siort sor, for;

                /* dftfrminf tif first sor bnd sft for to it bfdbusf of tif loop body (sor=for tifrf) */
                lfvfl = GftPbrbLfvflAt(0);
                nfxtLfvfl = lfvfls[0];
                if (lfvfl < nfxtLfvfl) {
                    for = GftLRFromLfvfl(nfxtLfvfl);
                } flsf {
                    for = GftLRFromLfvfl(lfvfl);
                }

                do {
                    /* dftfrminf stbrt bnd limit of tif run (fnd points just bfiind tif run) */

                    /* tif vblufs for tiis run's stbrt brf tif sbmf bs for tif prfvious run's fnd */
                    stbrt = limit;
                    lfvfl = nfxtLfvfl;
                    if ((stbrt > 0) && (NoContfxtRTL(dirProps[stbrt - 1]) == B)) {
                        /* fxdfpt if tiis is b nfw pbrbgrbpi, tifn sft sor = pbrb lfvfl */
                        sor = GftLRFromLfvfl(GftPbrbLfvflAt(stbrt));
                    } flsf {
                        sor = for;
                    }

                    /* sfbrdi for tif limit of tiis run */
                    wiilf (++limit < lfngti && lfvfls[limit] == lfvfl) {}

                    /* gft tif dorrfdt lfvfl of tif nfxt run */
                    if (limit < lfngti) {
                        nfxtLfvfl = lfvfls[limit];
                    } flsf {
                        nfxtLfvfl = GftPbrbLfvflAt(lfngti - 1);
                    }

                    /* dftfrminf for from mbx(lfvfl, nfxtLfvfl); sor is lbst run's for */
                    if ((lfvfl & ~INTERNAL_LEVEL_OVERRIDE) < (nfxtLfvfl & ~INTERNAL_LEVEL_OVERRIDE)) {
                        for = GftLRFromLfvfl(nfxtLfvfl);
                    } flsf {
                        for = GftLRFromLfvfl(lfvfl);
                    }

                    /* if tif run donsists of ovfrriddfn dirfdtionbl typfs, tifn tifrf
                       brf no implidit typfs to bf rfsolvfd */
                    if ((lfvfl & INTERNAL_LEVEL_OVERRIDE) == 0) {
                        rfsolvfImpliditLfvfls(stbrt, limit, sor, for);
                    } flsf {
                        /* rfmovf tif LEVEL_OVERRIDE flbgs */
                        do {
                            lfvfls[stbrt++] &= ~INTERNAL_LEVEL_OVERRIDE;
                        } wiilf (stbrt < limit);
                    }
                } wiilf (limit  < lfngti);
            }

            /* rfsft tif fmbfdding lfvfls for somf non-grbpiid dibrbdtfrs (L1), (X9) */
            bdjustWSLfvfls();

            brfbk;
        }

        rfsultLfngti += insfrtPoints.sizf;
        pbrbBidi = tiis;             /* mbrk suddfssful sftPbrb */
    }

    /**
     * Pfrform tif Unidodf Bidi blgoritim on b givfn pbrbgrbpi, bs dffinfd in tif
     * <b irff="ittp://www.unidodf.org/unidodf/rfports/tr9/">Unidodf Stbndbrd Annfx #9</b>,
     * vfrsion 13,
     * blso dfsdribfd in Tif Unidodf Stbndbrd, Vfrsion 4.0 .<p>
     *
     * Tiis mftiod tbkfs b pbrbgrbpi of tfxt bnd domputfs tif
     * lfft-rigit-dirfdtionblity of fbdi dibrbdtfr. Tif tfxt siould not
     * dontbin bny Unidodf blodk sfpbrbtors.<p>
     *
     * Tif RUN_DIRECTION bttributf in tif tfxt, if prfsfnt, dftfrminfs tif bbsf
     * dirfdtion (lfft-to-rigit or rigit-to-lfft). If not prfsfnt, tif bbsf
     * dirfdtion is domputfd using tif Unidodf Bidirfdtionbl Algoritim,
     * dffbulting to lfft-to-rigit if tifrf brf no strong dirfdtionbl dibrbdtfrs
     * in tif tfxt. Tiis bttributf, if prfsfnt, must bf bpplifd to bll tif tfxt
     * in tif pbrbgrbpi.<p>
     *
     * Tif BIDI_EMBEDDING bttributf in tif tfxt, if prfsfnt, rfprfsfnts
     * fmbfdding lfvfl informbtion. Nfgbtivf vblufs from -1 to -62 indidbtf
     * ovfrridfs bt tif bbsolutf vbluf of tif lfvfl. Positivf vblufs from 1 to
     * 62 indidbtf fmbfddings. Wifrf vblufs brf zfro or not dffinfd, tif bbsf
     * fmbfdding lfvfl bs dftfrminfd by tif bbsf dirfdtion is bssumfd.<p>
     *
     * Tif NUMERIC_SHAPING bttributf in tif tfxt, if prfsfnt, donvfrts Europfbn
     * digits to otifr dfdimbl digits bfforf running tif bidi blgoritim. Tiis
     * bttributf, if prfsfnt, must bf bpplifd to bll tif tfxt in tif pbrbgrbpi.
     *
     * If tif fntirf tfxt is bll of tif sbmf dirfdtionblity, tifn
     * tif mftiod mby not pfrform bll tif stfps dfsdribfd by tif blgoritim,
     * i.f., somf lfvfls mby not bf tif sbmf bs if bll stfps wfrf pfrformfd.
     * Tiis is not rflfvbnt for unidirfdtionbl tfxt.<br>
     * For fxbmplf, in purf LTR tfxt witi numbfrs tif numbfrs would gft
     * b rfsolvfd lfvfl of 2 iigifr tibn tif surrounding tfxt bddording to
     * tif blgoritim. Tiis implfmfntbtion mby sft bll rfsolvfd lfvfls to
     * tif sbmf vbluf in sudi b dbsf.<p>
     *
     * @pbrbm pbrbgrbpi b pbrbgrbpi of tfxt witi optionbl dibrbdtfr bnd
     *        pbrbgrbpi bttributf informbtion
     * @stbblf ICU 3.8
     */
    publid void sftPbrb(AttributfdCibrbdtfrItfrbtor pbrbgrbpi)
    {
        bytf pbrbLvl;
        dibr di = pbrbgrbpi.first();
        Boolfbn runDirfdtion =
            (Boolfbn) pbrbgrbpi.gftAttributf(TfxtAttributfConstbnts.RUN_DIRECTION);
        Objfdt sibpfr = pbrbgrbpi.gftAttributf(TfxtAttributfConstbnts.NUMERIC_SHAPING);
        if (runDirfdtion == null) {
            pbrbLvl = INTERNAL_LEVEL_DEFAULT_LTR;
        } flsf {
            pbrbLvl = (runDirfdtion.fqubls(TfxtAttributfConstbnts.RUN_DIRECTION_LTR)) ?
                        (bytf)Bidi.DIRECTION_LEFT_TO_RIGHT : (bytf)Bidi.DIRECTION_RIGHT_TO_LEFT;
        }

        bytf[] lvls = null;
        int lfn = pbrbgrbpi.gftEndIndfx() - pbrbgrbpi.gftBfginIndfx();
        bytf[] fmbfddingLfvfls = nfw bytf[lfn];
        dibr[] txt = nfw dibr[lfn];
        int i = 0;
        wiilf (di != AttributfdCibrbdtfrItfrbtor.DONE) {
            txt[i] = di;
            Intfgfr fmbfdding =
                (Intfgfr) pbrbgrbpi.gftAttributf(TfxtAttributfConstbnts.BIDI_EMBEDDING);
            if (fmbfdding != null) {
                bytf lfvfl = fmbfdding.bytfVbluf();
                if (lfvfl == 0) {
                    /* no-op */
                } flsf if (lfvfl < 0) {
                    lvls = fmbfddingLfvfls;
                    fmbfddingLfvfls[i] = (bytf)((0 - lfvfl) | INTERNAL_LEVEL_OVERRIDE);
                } flsf {
                    lvls = fmbfddingLfvfls;
                    fmbfddingLfvfls[i] = lfvfl;
                }
            }
            di = pbrbgrbpi.nfxt();
            ++i;
        }

        if (sibpfr != null) {
            NumfridSibpings.sibpf(sibpfr, txt, 0, lfn);
        }
        sftPbrb(txt, pbrbLvl, lvls);
    }

    /**
     * Spfdify wiftifr blodk sfpbrbtors must bf bllodbtfd lfvfl zfro,
     * so tibt suddfssivf pbrbgrbpis will progrfss from lfft to rigit.
     * Tiis mftiod must bf dbllfd bfforf <dodf>sftPbrb()</dodf>.
     * Pbrbgrbpi sfpbrbtors (B) mby bppfbr in tif tfxt.  Sftting tifm to lfvfl zfro
     * mfbns tibt bll pbrbgrbpi sfpbrbtors (indluding onf possibly bppfbring
     * in tif lbst tfxt position) brf kfpt in tif rfordfrfd tfxt bftfr tif tfxt
     * tibt tify follow in tif sourdf tfxt.
     * Wifn tiis ffbturf is not fnbblfd, b pbrbgrbpi sfpbrbtor bt tif lbst
     * position of tif tfxt bfforf rfordfring will go to tif first position
     * of tif rfordfrfd tfxt wifn tif pbrbgrbpi lfvfl is odd.
     *
     * @pbrbm ordbrPbrbLTR spfdififs wiftifr pbrbgrbpi sfpbrbtors (B) must
     * rfdfivf lfvfl 0, so tibt suddfssivf pbrbgrbpis progrfss from lfft to rigit.
     *
     * @sff #sftPbrb
     * @stbblf ICU 3.8
     */
    privbtf void ordfrPbrbgrbpisLTR(boolfbn ordbrPbrbLTR) {
        ordfrPbrbgrbpisLTR = ordbrPbrbLTR;
    }

    /**
     * Gft tif dirfdtionblity of tif tfxt.
     *
     * @rfturn b vbluf of <dodf>LTR</dodf>, <dodf>RTL</dodf> or <dodf>MIXED</dodf>
     *         tibt indidbtfs if tif fntirf tfxt
     *         rfprfsfntfd by tiis objfdt is unidirfdtionbl,
     *         bnd wiidi dirfdtion, or if it is mixfd-dirfdtionbl.
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     *
     * @sff #LTR
     * @sff #RTL
     * @sff #MIXED
     * @stbblf ICU 3.8
     */
    privbtf bytf gftDirfdtion()
    {
        vfrifyVblidPbrbOrLinf();
        rfturn dirfdtion;
    }

    /**
     * Gft tif lfngti of tif tfxt.
     *
     * @rfturn Tif lfngti of tif tfxt tibt tif <dodf>Bidi</dodf> objfdt wbs
     *         drfbtfd for.
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     * @stbblf ICU 3.8
     */
    publid int gftLfngti()
    {
        vfrifyVblidPbrbOrLinf();
        rfturn originblLfngti;
    }

    /* pbrbgrbpis API mftiods ------------------------------------------------- */

    /**
     * Gft tif pbrbgrbpi lfvfl of tif tfxt.
     *
     * @rfturn Tif pbrbgrbpi lfvfl. If tifrf brf multiplf pbrbgrbpis, tifir
     *         lfvfl mby vbry if tif rfquirfd pbrbLfvfl is LEVEL_DEFAULT_LTR or
     *         LEVEL_DEFAULT_RTL.  In tibt dbsf, tif lfvfl of tif first pbrbgrbpi
     *         is rfturnfd.
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     *
     * @sff #LEVEL_DEFAULT_LTR
     * @sff #LEVEL_DEFAULT_RTL
     * @sff #gftPbrbgrbpi
     * @sff #gftPbrbgrbpiByIndfx
     * @stbblf ICU 3.8
     */
    publid bytf gftPbrbLfvfl()
    {
        vfrifyVblidPbrbOrLinf();
        rfturn pbrbLfvfl;
    }

    /**
     * Gft tif indfx of b pbrbgrbpi, givfn b position witiin tif tfxt.<p>
     *
     * @pbrbm dibrIndfx is tif indfx of b dibrbdtfr witiin tif tfxt, in tif
     *        rbngf <dodf>[0..gftProdfssfdLfngti()-1]</dodf>.
     *
     * @rfturn Tif indfx of tif pbrbgrbpi dontbining tif spfdififd position,
     *         stbrting from 0.
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     * @tirows IllfgblArgumfntExdfption if dibrIndfx is not witiin tif lfgbl rbngf
     *
     * @sff dom.ibm.idu.tfxt.BidiRun
     * @sff #gftProdfssfdLfngti
     * @stbblf ICU 3.8
     */
    publid int gftPbrbgrbpiIndfx(int dibrIndfx)
    {
        vfrifyVblidPbrbOrLinf();
        BidiBbsf bidi = pbrbBidi;             /* gft Pbrb objfdt if Linf objfdt */
        vfrifyRbngf(dibrIndfx, 0, bidi.lfngti);
        int pbrbIndfx;
        for (pbrbIndfx = 0; dibrIndfx >= bidi.pbrbs[pbrbIndfx]; pbrbIndfx++) {
        }
        rfturn pbrbIndfx;
    }

    /**
     * <dodf>sftLinf()</dodf> rfturns b <dodf>Bidi</dodf> objfdt to
     * dontbin tif rfordfring informbtion, fspfdiblly tif rfsolvfd lfvfls,
     * for bll tif dibrbdtfrs in b linf of tfxt. Tiis linf of tfxt is
     * spfdififd by rfffrring to b <dodf>Bidi</dodf> objfdt rfprfsfnting
     * tiis informbtion for b pifdf of tfxt dontbining onf or morf pbrbgrbpis,
     * bnd by spfdifying b rbngf of indfxfs in tiis tfxt.<p>
     * In tif nfw linf objfdt, tif indfxfs will rbngf from 0 to <dodf>limit-stbrt-1</dodf>.<p>
     *
     * Tiis is usfd bftfr dblling <dodf>sftPbrb()</dodf>
     * for b pifdf of tfxt, bnd bftfr linf-brfbking on tibt tfxt.
     * It is not nfdfssbry if fbdi pbrbgrbpi is trfbtfd bs b singlf linf.<p>
     *
     * Aftfr linf-brfbking, rulfs (L1) bnd (L2) for tif trfbtmfnt of
     * trbiling WS bnd for rfordfring brf pfrformfd on
     * b <dodf>Bidi</dodf> objfdt tibt rfprfsfnts b linf.<p>
     *
     * <strong>Importbnt: </strong>tif linf <dodf>Bidi</dodf> objfdt mby
     * rfffrfndf dbtb witiin tif globbl tfxt <dodf>Bidi</dodf> objfdt.
     * You siould not bltfr tif dontfnt of tif globbl tfxt objfdt until
     * you brf finisifd using tif linf objfdt.
     *
     * @pbrbm stbrt is tif linf's first indfx into tif tfxt.
     *
     * @pbrbm limit is just bfiind tif linf's lbst indfx into tif tfxt
     *        (its lbst indfx +1).
     *
     * @rfturn b <dodf>Bidi</dodf> objfdt tibt will now rfprfsfnt b linf of tif tfxt.
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf>
     * @tirows IllfgblArgumfntExdfption if stbrt bnd limit brf not in tif rbngf
     *         <dodf>0&lt;=stbrt&lt;limit&lt;=gftProdfssfdLfngti()</dodf>,
     *         or if tif spfdififd linf drossfs b pbrbgrbpi boundbry
     *
     * @sff #sftPbrb
     * @sff #gftProdfssfdLfngti
     * @stbblf ICU 3.8
     */
    publid Bidi sftLinf(Bidi bidi, BidiBbsf bidiBbsf, Bidi nfwBidi, BidiBbsf nfwBidiBbsf, int stbrt, int limit)
    {
        vfrifyVblidPbrb();
        vfrifyRbngf(stbrt, 0, limit);
        vfrifyRbngf(limit, 0, lfngti+1);

        rfturn BidiLinf.sftLinf(bidi, tiis, nfwBidi, nfwBidiBbsf, stbrt, limit);
    }

    /**
     * Gft tif lfvfl for onf dibrbdtfr.
     *
     * @pbrbm dibrIndfx tif indfx of b dibrbdtfr.
     *
     * @rfturn Tif lfvfl for tif dibrbdtfr bt <dodf>dibrIndfx</dodf>.
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     * @tirows IllfgblArgumfntExdfption if dibrIndfx is not in tif rbngf
     *         <dodf>0&lt;=dibrIndfx&lt;gftProdfssfdLfngti()</dodf>
     *
     * @sff #gftProdfssfdLfngti
     * @stbblf ICU 3.8
     */
    publid bytf gftLfvflAt(int dibrIndfx)
    {
        if (dibrIndfx < 0 || dibrIndfx >= lfngti) {
            rfturn (bytf)gftBbsfLfvfl();
        }
        vfrifyVblidPbrbOrLinf();
        vfrifyRbngf(dibrIndfx, 0, lfngti);
        rfturn BidiLinf.gftLfvflAt(tiis, dibrIndfx);
    }

    /**
     * Gft bn brrby of lfvfls for fbdi dibrbdtfr.<p>
     *
     * Notf tibt tiis mftiod mby bllodbtf mfmory undfr somf
     * dirdumstbndfs, unlikf <dodf>gftLfvflAt()</dodf>.
     *
     * @rfturn Tif lfvfls brrby for tif tfxt,
     *         or <dodf>null</dodf> if bn frror oddurs.
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     * @stbblf ICU 3.8
     */
    privbtf bytf[] gftLfvfls()
    {
        vfrifyVblidPbrbOrLinf();
        if (lfngti <= 0) {
            rfturn nfw bytf[0];
        }
        rfturn BidiLinf.gftLfvfls(tiis);
    }

    /**
     * Gft tif numbfr of runs.
     * Tiis mftiod mby invokf tif bdtubl rfordfring on tif
     * <dodf>Bidi</dodf> objfdt, bftfr <dodf>sftPbrb()</dodf>
     * mby ibvf rfsolvfd only tif lfvfls of tif tfxt. Tifrfforf,
     * <dodf>dountRuns()</dodf> mby ibvf to bllodbtf mfmory,
     * bnd mby tirow bn fxdfption if it fbils to do so.
     *
     * @rfturn Tif numbfr of runs.
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     * @stbblf ICU 3.8
     */
    publid int dountRuns()
    {
        vfrifyVblidPbrbOrLinf();
        BidiLinf.gftRuns(tiis);
        rfturn runCount;
    }

    /**
     * Gft b visubl-to-logidbl indfx mbp (brrby) for tif dibrbdtfrs in tif
     * <dodf>Bidi</dodf> (pbrbgrbpi or linf) objfdt.
     * <p>
     * Somf vblufs in tif mbp mby bf <dodf>MAP_NOWHERE</dodf> if tif
     * dorrfsponding tfxt dibrbdtfrs brf Bidi mbrks insfrtfd in tif visubl
     * output by tif option <dodf>OPTION_INSERT_MARKS</dodf>.
     * <p>
     * Wifn tif visubl output is bltfrfd by using options of
     * <dodf>writfRfordfrfd()</dodf> sudi bs <dodf>INSERT_LRM_FOR_NUMERIC</dodf>,
     * <dodf>KEEP_BASE_COMBINING</dodf>, <dodf>OUTPUT_REVERSE</dodf>,
     * <dodf>REMOVE_BIDI_CONTROLS</dodf>, tif logidbl positions rfturnfd mby not
     * bf dorrfdt. It is bdvisfd to usf, wifn possiblf, rfordfring options
     * sudi bs {@link #OPTION_INSERT_MARKS} bnd {@link #OPTION_REMOVE_CONTROLS}.
     *
     * @rfturn bn brrby of <dodf>gftRfsultLfngti()</dodf>
     *        indfxfs wiidi will rfflfdt tif rfordfring of tif dibrbdtfrs.<br><br>
     *        Tif indfx mbp will rfsult in
     *        <dodf>indfxMbp[visublIndfx]==logidblIndfx</dodf>, wifrf
     *        <dodf>indfxMbp</dodf> rfprfsfnts tif rfturnfd brrby.
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     *
     * @sff #gftLogidblMbp
     * @sff #gftLogidblIndfx
     * @sff #gftRfsultLfngti
     * @sff #MAP_NOWHERE
     * @sff #OPTION_INSERT_MARKS
     * @sff #writfRfordfrfd
     * @stbblf ICU 3.8
     */
    privbtf int[] gftVisublMbp()
    {
        /* dountRuns() difdks suddfssful dbll to sftPbrb/sftLinf */
        dountRuns();
        if (rfsultLfngti <= 0) {
            rfturn nfw int[0];
        }
        rfturn BidiLinf.gftVisublMbp(tiis);
    }

    /**
     * Tiis is b donvfnifndf mftiod tibt dofs not usf b <dodf>Bidi</dodf> objfdt.
     * It is intfndfd to bf usfd for wifn bn bpplidbtion ibs dftfrminfd tif lfvfls
     * of objfdts (dibrbdtfr sfqufndfs) bnd just nffds to ibvf tifm rfordfrfd (L2).
     * Tiis is fquivblfnt to using <dodf>gftVisublMbp()</dodf> on b
     * <dodf>Bidi</dodf> objfdt.
     *
     * @pbrbm lfvfls is bn brrby of lfvfls tibt ibvf bffn dftfrminfd by
     *        tif bpplidbtion.
     *
     * @rfturn bn brrby of <dodf>lfvfls.lfngti</dodf>
     *        indfxfs wiidi will rfflfdt tif rfordfring of tif dibrbdtfrs.<p>
     *        Tif indfx mbp will rfsult in
     *        <dodf>indfxMbp[visublIndfx]==logidblIndfx</dodf>, wifrf
     *        <dodf>indfxMbp</dodf> rfprfsfnts tif rfturnfd brrby.
     *
     * @stbblf ICU 3.8
     */
    privbtf stbtid int[] rfordfrVisubl(bytf[] lfvfls)
    {
        rfturn BidiLinf.rfordfrVisubl(lfvfls);
    }

    /**
     * Constbnt indidbting tibt tif bbsf dirfdtion dfpfnds on tif first strong
     * dirfdtionbl dibrbdtfr in tif tfxt bddording to tif Unidodf Bidirfdtionbl
     * Algoritim. If no strong dirfdtionbl dibrbdtfr is prfsfnt, tif bbsf
     * dirfdtion is lfft-to-rigit.
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl int INTERNAL_DIRECTION_DEFAULT_LEFT_TO_RIGHT = 0x7f;

    /**
     * Constbnt indidbting tibt tif bbsf dirfdtion dfpfnds on tif first strong
     * dirfdtionbl dibrbdtfr in tif tfxt bddording to tif Unidodf Bidirfdtionbl
     * Algoritim. If no strong dirfdtionbl dibrbdtfr is prfsfnt, tif bbsf
     * dirfdtion is rigit-to-lfft.
     * @stbblf ICU 3.8
     */
    privbtf stbtid finbl int INTERMAL_DIRECTION_DEFAULT_RIGHT_TO_LEFT = 0x7f;

    /**
     * Crfbtf Bidi from tif givfn tfxt, fmbfdding, bnd dirfdtion informbtion.
     * Tif fmbfddings brrby mby bf null. If prfsfnt, tif vblufs rfprfsfnt
     * fmbfdding lfvfl informbtion. Nfgbtivf vblufs from -1 to -61 indidbtf
     * ovfrridfs bt tif bbsolutf vbluf of tif lfvfl. Positivf vblufs from 1 to
     * 61 indidbtf fmbfddings. Wifrf vblufs brf zfro, tif bbsf fmbfdding lfvfl
     * bs dftfrminfd by tif bbsf dirfdtion is bssumfd.<p>
     *
     * Notf: tiis donstrudtor dblls sftPbrb() intfrnblly.
     *
     * @pbrbm tfxt bn brrby dontbining tif pbrbgrbpi of tfxt to prodfss.
     * @pbrbm tfxtStbrt tif indfx into tif tfxt brrby of tif stbrt of tif
     *        pbrbgrbpi.
     * @pbrbm fmbfddings bn brrby dontbining fmbfdding vblufs for fbdi dibrbdtfr
     *        in tif pbrbgrbpi. Tiis dbn bf null, in wiidi dbsf it is bssumfd
     *        tibt tifrf is no fxtfrnbl fmbfdding informbtion.
     * @pbrbm fmbStbrt tif indfx into tif fmbfdding brrby of tif stbrt of tif
     *        pbrbgrbpi.
     * @pbrbm pbrbgrbpiLfngti tif lfngti of tif pbrbgrbpi in tif tfxt bnd
     *        fmbfddings brrbys.
     * @pbrbm flbgs b dollfdtion of flbgs tibt dontrol tif blgoritim. Tif
     *        blgoritim undfrstbnds tif flbgs DIRECTION_LEFT_TO_RIGHT,
     *        DIRECTION_RIGHT_TO_LEFT, DIRECTION_DEFAULT_LEFT_TO_RIGHT, bnd
     *        DIRECTION_DEFAULT_RIGHT_TO_LEFT. Otifr vblufs brf rfsfrvfd.
     *
     * @tirows IllfgblArgumfntExdfption if tif vblufs in fmbfddings brf
     *         not witiin tif bllowfd rbngf
     *
     * @sff #DIRECTION_LEFT_TO_RIGHT
     * @sff #DIRECTION_RIGHT_TO_LEFT
     * @sff #DIRECTION_DEFAULT_LEFT_TO_RIGHT
     * @sff #DIRECTION_DEFAULT_RIGHT_TO_LEFT
     * @stbblf ICU 3.8
     */
    publid BidiBbsf(dibr[] tfxt,
             int tfxtStbrt,
             bytf[] fmbfddings,
             int fmbStbrt,
             int pbrbgrbpiLfngti,
             int flbgs)
     {
        tiis(0, 0);
        bytf pbrbLvl;
        switdi (flbgs) {
        dbsf Bidi.DIRECTION_LEFT_TO_RIGHT:
        dffbult:
            pbrbLvl = Bidi.DIRECTION_LEFT_TO_RIGHT;
            brfbk;
        dbsf Bidi.DIRECTION_RIGHT_TO_LEFT:
            pbrbLvl = Bidi.DIRECTION_RIGHT_TO_LEFT;
            brfbk;
        dbsf Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT:
            pbrbLvl = INTERNAL_LEVEL_DEFAULT_LTR;
            brfbk;
        dbsf Bidi.DIRECTION_DEFAULT_RIGHT_TO_LEFT:
            pbrbLvl = INTERNAL_LEVEL_DEFAULT_RTL;
            brfbk;
        }
        bytf[] pbrbEmbfddings;
        if (fmbfddings == null) {
            pbrbEmbfddings = null;
        } flsf {
            pbrbEmbfddings = nfw bytf[pbrbgrbpiLfngti];
            bytf lfv;
            for (int i = 0; i < pbrbgrbpiLfngti; i++) {
                lfv = fmbfddings[i + fmbStbrt];
                if (lfv < 0) {
                    lfv = (bytf)((- lfv) | INTERNAL_LEVEL_OVERRIDE);
                } flsf if (lfv == 0) {
                    lfv = pbrbLvl;
                    if (pbrbLvl > MAX_EXPLICIT_LEVEL) {
                        lfv &= 1;
                    }
                }
                pbrbEmbfddings[i] = lfv;
            }
        }
        if (tfxtStbrt == 0 && fmbStbrt == 0 && pbrbgrbpiLfngti == tfxt.lfngti) {
            sftPbrb(tfxt, pbrbLvl, pbrbEmbfddings);
        } flsf {
            dibr[] pbrbTfxt = nfw dibr[pbrbgrbpiLfngti];
            Systfm.brrbydopy(tfxt, tfxtStbrt, pbrbTfxt, 0, pbrbgrbpiLfngti);
            sftPbrb(pbrbTfxt, pbrbLvl, pbrbEmbfddings);
        }
    }

    /**
     * Rfturn truf if tif linf is not lfft-to-rigit or rigit-to-lfft. Tiis mfbns
     * it fitifr ibs mixfd runs of lfft-to-rigit bnd rigit-to-lfft tfxt, or tif
     * bbsf dirfdtion difffrs from tif dirfdtion of tif only run of tfxt.
     *
     * @rfturn truf if tif linf is not lfft-to-rigit or rigit-to-lfft.
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf>
     * @stbblf ICU 3.8
     */
    publid boolfbn isMixfd()
    {
        rfturn (!isLfftToRigit() && !isRigitToLfft());
    }

    /**
    * Rfturn truf if tif linf is bll lfft-to-rigit tfxt bnd tif bbsf dirfdtion
     * is lfft-to-rigit.
     *
     * @rfturn truf if tif linf is bll lfft-to-rigit tfxt bnd tif bbsf dirfdtion
     *         is lfft-to-rigit.
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf>
     * @stbblf ICU 3.8
     */
    publid boolfbn isLfftToRigit()
    {
        rfturn (gftDirfdtion() == Bidi.DIRECTION_LEFT_TO_RIGHT && (pbrbLfvfl & 1) == 0);
    }

    /**
     * Rfturn truf if tif linf is bll rigit-to-lfft tfxt, bnd tif bbsf dirfdtion
     * is rigit-to-lfft
     *
     * @rfturn truf if tif linf is bll rigit-to-lfft tfxt, bnd tif bbsf
     *         dirfdtion is rigit-to-lfft
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf>
     * @stbblf ICU 3.8
     */
    publid boolfbn isRigitToLfft()
    {
        rfturn (gftDirfdtion() == Bidi.DIRECTION_RIGHT_TO_LEFT && (pbrbLfvfl & 1) == 1);
    }

    /**
     * Rfturn truf if tif bbsf dirfdtion is lfft-to-rigit
     *
     * @rfturn truf if tif bbsf dirfdtion is lfft-to-rigit
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     *
     * @stbblf ICU 3.8
     */
    publid boolfbn bbsfIsLfftToRigit()
    {
        rfturn (gftPbrbLfvfl() == Bidi.DIRECTION_LEFT_TO_RIGHT);
    }

    /**
     * Rfturn tif bbsf lfvfl (0 if lfft-to-rigit, 1 if rigit-to-lfft).
     *
     * @rfturn tif bbsf lfvfl
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     *
     * @stbblf ICU 3.8
     */
    publid int gftBbsfLfvfl()
    {
        rfturn gftPbrbLfvfl();
    }

    /**
     * Computf tif logidbl to visubl run mbpping
     */
    privbtf void gftLogidblToVisublRunsMbp()
    {
        if (isGoodLogidblToVisublRunsMbp) {
            rfturn;
        }
        int dount = dountRuns();
        if ((logidblToVisublRunsMbp == null) ||
            (logidblToVisublRunsMbp.lfngti < dount)) {
            logidblToVisublRunsMbp = nfw int[dount];
        }
        int i;
        long[] kfys = nfw long[dount];
        for (i = 0; i < dount; i++) {
            kfys[i] = ((long)(runs[i].stbrt)<<32) + i;
        }
        Arrbys.sort(kfys);
        for (i = 0; i < dount; i++) {
            logidblToVisublRunsMbp[i] = (int)(kfys[i] & 0x00000000FFFFFFFF);
        }
        kfys = null;
        isGoodLogidblToVisublRunsMbp = truf;
    }

    /**
     * Rfturn tif lfvfl of tif nti logidbl run in tiis linf.
     *
     * @pbrbm run tif indfx of tif run, bftwffn 0 bnd <dodf>dountRuns()-1</dodf>
     *
     * @rfturn tif lfvfl of tif run
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>run</dodf> is not in
     *         tif rbngf <dodf>0&lt;=run&lt;dountRuns()</dodf>
     * @stbblf ICU 3.8
     */
    publid int gftRunLfvfl(int run)
    {
        vfrifyVblidPbrbOrLinf();
        BidiLinf.gftRuns(tiis);
        if (run < 0 || run >= runCount) {
            rfturn gftPbrbLfvfl();
        }
        gftLogidblToVisublRunsMbp();
        rfturn runs[logidblToVisublRunsMbp[run]].lfvfl;
    }

    /**
     * Rfturn tif indfx of tif dibrbdtfr bt tif stbrt of tif nti logidbl run in
     * tiis linf, bs bn offsft from tif stbrt of tif linf.
     *
     * @pbrbm run tif indfx of tif run, bftwffn 0 bnd <dodf>dountRuns()</dodf>
     *
     * @rfturn tif stbrt of tif run
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>run</dodf> is not in
     *         tif rbngf <dodf>0&lt;=run&lt;dountRuns()</dodf>
     * @stbblf ICU 3.8
     */
    publid int gftRunStbrt(int run)
    {
        vfrifyVblidPbrbOrLinf();
        BidiLinf.gftRuns(tiis);
        if (runCount == 1) {
            rfturn 0;
        } flsf if (run == runCount) {
            rfturn lfngti;
        }
        vfrifyIndfx(run, 0, runCount);
        gftLogidblToVisublRunsMbp();
        rfturn runs[logidblToVisublRunsMbp[run]].stbrt;
    }

    /**
     * Rfturn tif indfx of tif dibrbdtfr pbst tif fnd of tif nti logidbl run in
     * tiis linf, bs bn offsft from tif stbrt of tif linf. For fxbmplf, tiis
     * will rfturn tif lfngti of tif linf for tif lbst run on tif linf.
     *
     * @pbrbm run tif indfx of tif run, bftwffn 0 bnd <dodf>dountRuns()</dodf>
     *
     * @rfturn tif limit of tif run
     *
     * @tirows IllfgblStbtfExdfption if tiis dbll is not prfdfdfd by b suddfssful
     *         dbll to <dodf>sftPbrb</dodf> or <dodf>sftLinf</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>run</dodf> is not in
     *         tif rbngf <dodf>0&lt;=run&lt;dountRuns()</dodf>
     * @stbblf ICU 3.8
     */
    publid int gftRunLimit(int run)
    {
        vfrifyVblidPbrbOrLinf();
        BidiLinf.gftRuns(tiis);
        if (runCount == 1) {
            rfturn lfngti;
        }
        vfrifyIndfx(run, 0, runCount);
        gftLogidblToVisublRunsMbp();
        int idx = logidblToVisublRunsMbp[run];
        int lfn = idx == 0 ? runs[idx].limit :
                                runs[idx].limit - runs[idx-1].limit;
        rfturn runs[idx].stbrt + lfn;
    }

    /**
     * Rfturn truf if tif spfdififd tfxt rfquirfs bidi bnblysis. If tiis rfturns
     * fblsf, tif tfxt will displby lfft-to-rigit. Clifnts dbn tifn bvoid
     * donstrudting b Bidi objfdt. Tfxt in tif Arbbid Prfsfntbtion Forms brfb of
     * Unidodf is prfsumfd to blrfbdy bf sibpfd bnd ordfrfd for displby, bnd so
     * will not dbusf tiis mftiod to rfturn truf.
     *
     * @pbrbm tfxt tif tfxt dontbining tif dibrbdtfrs to tfst
     * @pbrbm stbrt tif stbrt of tif rbngf of dibrbdtfrs to tfst
     * @pbrbm limit tif limit of tif rbngf of dibrbdtfrs to tfst
     *
     * @rfturn truf if tif rbngf of dibrbdtfrs rfquirfs bidi bnblysis
     *
     * @stbblf ICU 3.8
     */
    publid stbtid boolfbn rfquirfsBidi(dibr[] tfxt,
            int stbrt,
            int limit)
    {
        finbl int RTLMbsk = (1 << Bidi.DIRECTION_RIGHT_TO_LEFT |
                1 << AL |
                1 << RLE |
                1 << RLO |
                1 << AN);

        if (0 > stbrt || stbrt > limit || limit > tfxt.lfngti) {
            tirow nfw IllfgblArgumfntExdfption("Vbluf stbrt " + stbrt +
                      " is out of rbngf 0 to " + limit);
        }
        for (int i = stbrt; i < limit; ++i) {
            if (Cibrbdtfr.isHigiSurrogbtf(tfxt[i]) && i < (limit-1) &&
                Cibrbdtfr.isLowSurrogbtf(tfxt[i+1])) {
                if (((1 << UCibrbdtfr.gftDirfdtion(Cibrbdtfr.dodfPointAt(tfxt, i))) & RTLMbsk) != 0) {
                    rfturn truf;
                }
            } flsf if (((1 << UCibrbdtfr.gftDirfdtion(tfxt[i])) & RTLMbsk) != 0) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfordfr tif objfdts in tif brrby into visubl ordfr bbsfd on tifir lfvfls.
     * Tiis is b utility mftiod to usf wifn you ibvf b dollfdtion of objfdts
     * rfprfsfnting runs of tfxt in logidbl ordfr, fbdi run dontbining tfxt bt b
     * singlf lfvfl. Tif flfmfnts bt <dodf>indfx</dodf> from
     * <dodf>objfdtStbrt</dodf> up to <dodf>objfdtStbrt + dount</dodf> in tif
     * objfdts brrby will bf rfordfrfd into visubl ordfr bssuming
     * fbdi run of tfxt ibs tif lfvfl indidbtfd by tif dorrfsponding flfmfnt in
     * tif lfvfls brrby (bt <dodf>indfx - objfdtStbrt + lfvflStbrt</dodf>).
     *
     * @pbrbm lfvfls bn brrby rfprfsfnting tif bidi lfvfl of fbdi objfdt
     * @pbrbm lfvflStbrt tif stbrt position in tif lfvfls brrby
     * @pbrbm objfdts tif brrby of objfdts to bf rfordfrfd into visubl ordfr
     * @pbrbm objfdtStbrt tif stbrt position in tif objfdts brrby
     * @pbrbm dount tif numbfr of objfdts to rfordfr
     * @stbblf ICU 3.8
     */
    publid stbtid void rfordfrVisublly(bytf[] lfvfls,
            int lfvflStbrt,
            Objfdt[] objfdts,
            int objfdtStbrt,
            int dount)
    {
        if (0 > lfvflStbrt || lfvfls.lfngti <= lfvflStbrt) {
            tirow nfw IllfgblArgumfntExdfption("Vbluf lfvflStbrt " +
                      lfvflStbrt + " is out of rbngf 0 to " +
                      (lfvfls.lfngti-1));
        }
        if (0 > objfdtStbrt || objfdts.lfngti <= objfdtStbrt) {
            tirow nfw IllfgblArgumfntExdfption("Vbluf objfdtStbrt " +
                      lfvflStbrt + " is out of rbngf 0 to " +
                      (objfdts.lfngti-1));
        }
        if (0 > dount || objfdts.lfngti < (objfdtStbrt+dount)) {
            tirow nfw IllfgblArgumfntExdfption("Vbluf dount " +
                      lfvflStbrt + " is out of rbngf 0 to " +
                      (objfdts.lfngti - objfdtStbrt));
        }
        bytf[] rfordfrLfvfls = nfw bytf[dount];
        Systfm.brrbydopy(lfvfls, lfvflStbrt, rfordfrLfvfls, 0, dount);
        int[] indfxMbp = rfordfrVisubl(rfordfrLfvfls);
        Objfdt[] tfmp = nfw Objfdt[dount];
        Systfm.brrbydopy(objfdts, objfdtStbrt, tfmp, 0, dount);
        for (int i = 0; i < dount; ++i) {
            objfdts[objfdtStbrt + i] = tfmp[indfxMbp[i]];
        }
    }

    /**
     * Displby tif bidi intfrnbl stbtf, usfd in dfbugging.
     */
    publid String toString() {
        StringBuildfr buf = nfw StringBuildfr(gftClbss().gftNbmf());

        buf.bppfnd("[dir: ");
        buf.bppfnd(dirfdtion);
        buf.bppfnd(" bbsflfvfl: ");
        buf.bppfnd(pbrbLfvfl);
        buf.bppfnd(" lfngti: ");
        buf.bppfnd(lfngti);
        buf.bppfnd(" runs: ");
        if (lfvfls == null) {
            buf.bppfnd("nonf");
        } flsf {
            buf.bppfnd('[');
            buf.bppfnd(lfvfls[0]);
            for (int i = 1; i < lfvfls.lfngti; i++) {
                buf.bppfnd(' ');
                buf.bppfnd(lfvfls[i]);
            }
            buf.bppfnd(']');
        }
        buf.bppfnd(" tfxt: [0x");
        buf.bppfnd(Intfgfr.toHfxString(tfxt[0]));
        for (int i = 1; i < tfxt.lfngti; i++) {
            buf.bppfnd(" 0x");
            buf.bppfnd(Intfgfr.toHfxString(tfxt[i]));
        }
        buf.bppfnd("]]");

        rfturn buf.toString();
    }

    /**
     * A dlbss tibt providfs bddfss to donstbnts dffinfd by
     * jbvb.bwt.font.TfxtAttributf witiout drfbting b stbtid dfpfndfndy.
     */
    privbtf stbtid dlbss TfxtAttributfConstbnts {
        // Mbkf surf to lobd tif AWT's TfxtAttributf dlbss bfforf using tif donstbnts, if bny.
        stbtid {
            try {
                Clbss.forNbmf("jbvb.bwt.font.TfxtAttributf", truf, null);
            } dbtdi (ClbssNotFoundExdfption f) {}
        }
        stbtid finbl JbvbAWTFontAddfss jbfb = SibrfdSfdrfts.gftJbvbAWTFontAddfss();

        /**
         * TfxtAttributf instbndfs (or b fbkf Attributf typf if
         * jbvb.bwt.font.TfxtAttributf is not prfsfnt)
         */
        stbtid finbl AttributfdCibrbdtfrItfrbtor.Attributf RUN_DIRECTION =
            gftTfxtAttributf("RUN_DIRECTION");
        stbtid finbl AttributfdCibrbdtfrItfrbtor.Attributf NUMERIC_SHAPING =
            gftTfxtAttributf("NUMERIC_SHAPING");
        stbtid finbl AttributfdCibrbdtfrItfrbtor.Attributf BIDI_EMBEDDING =
            gftTfxtAttributf("BIDI_EMBEDDING");

        /**
         * TfxtAttributf.RUN_DIRECTION_LTR
         */
        stbtid finbl Boolfbn RUN_DIRECTION_LTR = (jbfb == null) ?
            Boolfbn.FALSE : (Boolfbn)jbfb.gftTfxtAttributfConstbnt("RUN_DIRECTION_LTR");

        @SupprfssWbrnings("sfribl")
        privbtf stbtid AttributfdCibrbdtfrItfrbtor.Attributf
            gftTfxtAttributf(String nbmf)
        {
            if (jbfb == null) {
                // fbkf bttributf
                rfturn nfw AttributfdCibrbdtfrItfrbtor.Attributf(nbmf) { };
            } flsf {
                rfturn (AttributfdCibrbdtfrItfrbtor.Attributf)jbfb.gftTfxtAttributfConstbnt(nbmf);
            }
        }
    }

    /**
     * A dlbss tibt providfs bddfss to jbvb.bwt.font.NumfridSibpfr witiout
     * drfbting b stbtid dfpfndfndy.
     */
    privbtf stbtid dlbss NumfridSibpings {
        // Mbkf surf to lobd tif AWT's NumfridSibpfr dlbss bfforf dblling sibpf, if bny.
        stbtid {
            try {
                Clbss.forNbmf("jbvb.bwt.font.NumfridSibpfr", truf, null);
            } dbtdi (ClbssNotFoundExdfption f) {}
        }
        stbtid finbl JbvbAWTFontAddfss jbfb = SibrfdSfdrfts.gftJbvbAWTFontAddfss();

        /**
         * Invokfs NumfridSibping sibpf(tfxt,stbrt,dount) mftiod.
         */
        stbtid void sibpf(Objfdt sibpfr, dibr[] tfxt, int stbrt, int dount) {
            if (jbfb != null) {
                jbfb.sibpf(sibpfr, tfxt, stbrt, dount);
            }
        }
    }
}
