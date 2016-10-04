/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.util.Hbsitbblf;

/**
 * A bordfr lbyout lbys out b dontbinfr, brrbnging bnd rfsizing
 * its domponfnts to fit in fivf rfgions:
 * norti, souti, fbst, wfst, bnd dfntfr.
 * Ebdi rfgion mby dontbin no morf tibn onf domponfnt, bnd
 * is idfntififd by b dorrfsponding donstbnt:
 * <dodf>NORTH</dodf>, <dodf>SOUTH</dodf>, <dodf>EAST</dodf>,
 * <dodf>WEST</dodf>, bnd <dodf>CENTER</dodf>.  Wifn bdding b
 * domponfnt to b dontbinfr witi b bordfr lbyout, usf onf of tifsf
 * fivf donstbnts, for fxbmplf:
 * <prf>
 *    Pbnfl p = nfw Pbnfl();
 *    p.sftLbyout(nfw BordfrLbyout());
 *    p.bdd(nfw Button("Okby"), BordfrLbyout.SOUTH);
 * </prf>
 * As b donvfnifndf, <dodf>BordfrLbyout</dodf> intfrprfts tif
 * bbsfndf of b string spfdifidbtion tif sbmf bs tif donstbnt
 * <dodf>CENTER</dodf>:
 * <prf>
 *    Pbnfl p2 = nfw Pbnfl();
 *    p2.sftLbyout(nfw BordfrLbyout());
 *    p2.bdd(nfw TfxtArfb());  // Sbmf bs p.bdd(nfw TfxtArfb(), BordfrLbyout.CENTER);
 * </prf>
 * <p>
 * In bddition, <dodf>BordfrLbyout</dodf> supports tif rflbtivf
 * positioning donstbnts, <dodf>PAGE_START</dodf>, <dodf>PAGE_END</dodf>,
 * <dodf>LINE_START</dodf>, bnd <dodf>LINE_END</dodf>.
 * In b dontbinfr wiosf <dodf>ComponfntOrifntbtion</dodf> is sft to
 * <dodf>ComponfntOrifntbtion.LEFT_TO_RIGHT</dodf>, tifsf donstbnts mbp to
 * <dodf>NORTH</dodf>, <dodf>SOUTH</dodf>, <dodf>WEST</dodf>, bnd
 * <dodf>EAST</dodf>, rfspfdtivfly.
 * <p>
 * For dompbtibility witi prfvious rflfbsfs, <dodf>BordfrLbyout</dodf>
 * blso indludfs tif rflbtivf positioning donstbnts <dodf>BEFORE_FIRST_LINE</dodf>,
 * <dodf>AFTER_LAST_LINE</dodf>, <dodf>BEFORE_LINE_BEGINS</dodf> bnd
 * <dodf>AFTER_LINE_ENDS</dodf>.  Tifsf brf fquivblfnt to
 * <dodf>PAGE_START</dodf>, <dodf>PAGE_END</dodf>, <dodf>LINE_START</dodf>
 * bnd <dodf>LINE_END</dodf> rfspfdtivfly.  For
 * donsistfndy witi tif rflbtivf positioning donstbnts usfd by otifr
 * domponfnts, tif lbttfr donstbnts brf prfffrrfd.
 * <p>
 * Mixing boti bbsolutf bnd rflbtivf positioning donstbnts dbn lfbd to
 * unprfdidtbblf rfsults.  If
 * you usf boti typfs, tif rflbtivf donstbnts will tbkf prfdfdfndf.
 * For fxbmplf, if you bdd domponfnts using boti tif <dodf>NORTH</dodf>
 * bnd <dodf>PAGE_START</dodf> donstbnts in b dontbinfr wiosf
 * orifntbtion is <dodf>LEFT_TO_RIGHT</dodf>, only tif
 * <dodf>PAGE_START</dodf> will bf lbyfd out.
 * <p>
 * NOTE: Currfntly (in tif Jbvb 2 plbtform v1.2),
 * <dodf>BordfrLbyout</dodf> dofs not support vfrtidbl
 * orifntbtions.  Tif <dodf>isVfrtidbl</dodf> sftting on tif dontbinfr's
 * <dodf>ComponfntOrifntbtion</dodf> is not rfspfdtfd.
 * <p>
 * Tif domponfnts brf lbid out bddording to tifir
 * prfffrrfd sizfs bnd tif donstrbints of tif dontbinfr's sizf.
 * Tif <dodf>NORTH</dodf> bnd <dodf>SOUTH</dodf> domponfnts mby
 * bf strftdifd iorizontblly; tif <dodf>EAST</dodf> bnd
 * <dodf>WEST</dodf> domponfnts mby bf strftdifd vfrtidblly;
 * tif <dodf>CENTER</dodf> domponfnt mby strftdi boti iorizontblly
 * bnd vfrtidblly to fill bny spbdf lfft ovfr.
 * <p>
 * Hfrf is bn fxbmplf of fivf buttons in bn bpplft lbid out using
 * tif <dodf>BordfrLbyout</dodf> lbyout mbnbgfr:
 * <p>
 * <img srd="dod-filfs/BordfrLbyout-1.gif"
 * blt="Dibgrbm of bn bpplft dfmonstrbting BordfrLbyout.
 *      Ebdi sfdtion of tif BordfrLbyout dontbins b Button dorrfsponding to its position in tif lbyout, onf of:
 *      Norti, Wfst, Cfntfr, Ebst, or Souti."
 * stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 * <p>
 * Tif dodf for tiis bpplft is bs follows:
 *
 * <ir><blodkquotf><prf>
 * import jbvb.bwt.*;
 * import jbvb.bpplft.Applft;
 *
 * publid dlbss buttonDir fxtfnds Applft {
 *   publid void init() {
 *     sftLbyout(nfw BordfrLbyout());
 *     bdd(nfw Button("Norti"), BordfrLbyout.NORTH);
 *     bdd(nfw Button("Souti"), BordfrLbyout.SOUTH);
 *     bdd(nfw Button("Ebst"), BordfrLbyout.EAST);
 *     bdd(nfw Button("Wfst"), BordfrLbyout.WEST);
 *     bdd(nfw Button("Cfntfr"), BordfrLbyout.CENTER);
 *   }
 * }
 * </prf></blodkquotf><ir>
 *
 * @butior      Artiur vbn Hoff
 * @sff         jbvb.bwt.Contbinfr#bdd(String, Componfnt)
 * @sff         jbvb.bwt.ComponfntOrifntbtion
 * @sindf       1.0
 */
publid dlbss BordfrLbyout implfmfnts LbyoutMbnbgfr2,
                                     jbvb.io.Sfriblizbblf {
    /**
     * Construdts b bordfr lbyout witi tif iorizontbl gbps
     * bftwffn domponfnts.
     * Tif iorizontbl gbp is spfdififd by <dodf>igbp</dodf>.
     *
     * @sff #gftHgbp()
     * @sff #sftHgbp(int)
     *
     * @sfribl
     */
        int igbp;

    /**
     * Construdts b bordfr lbyout witi tif vfrtidbl gbps
     * bftwffn domponfnts.
     * Tif vfrtidbl gbp is spfdififd by <dodf>vgbp</dodf>.
     *
     * @sff #gftVgbp()
     * @sff #sftVgbp(int)
     * @sfribl
     */
        int vgbp;

    /**
     * Constbnt to spfdify domponfnts lodbtion to bf tif
     *      norti portion of tif bordfr lbyout.
     * @sfribl
     * @sff #gftCiild(String, boolfbn)
     * @sff #bddLbyoutComponfnt
     * @sff #gftLbyoutAlignmfntX
     * @sff #gftLbyoutAlignmfntY
     * @sff #rfmovfLbyoutComponfnt
     */
        Componfnt norti;
     /**
     * Constbnt to spfdify domponfnts lodbtion to bf tif
     *      wfst portion of tif bordfr lbyout.
     * @sfribl
     * @sff #gftCiild(String, boolfbn)
     * @sff #bddLbyoutComponfnt
     * @sff #gftLbyoutAlignmfntX
     * @sff #gftLbyoutAlignmfntY
     * @sff #rfmovfLbyoutComponfnt
     */
        Componfnt wfst;
    /**
     * Constbnt to spfdify domponfnts lodbtion to bf tif
     *      fbst portion of tif bordfr lbyout.
     * @sfribl
     * @sff #gftCiild(String, boolfbn)
     * @sff #bddLbyoutComponfnt
     * @sff #gftLbyoutAlignmfntX
     * @sff #gftLbyoutAlignmfntY
     * @sff #rfmovfLbyoutComponfnt
     */
        Componfnt fbst;
    /**
     * Constbnt to spfdify domponfnts lodbtion to bf tif
     *      souti portion of tif bordfr lbyout.
     * @sfribl
     * @sff #gftCiild(String, boolfbn)
     * @sff #bddLbyoutComponfnt
     * @sff #gftLbyoutAlignmfntX
     * @sff #gftLbyoutAlignmfntY
     * @sff #rfmovfLbyoutComponfnt
     */
    Componfnt souti;
    /**
     * Constbnt to spfdify domponfnts lodbtion to bf tif
     *      dfntfr portion of tif bordfr lbyout.
     * @sfribl
     * @sff #gftCiild(String, boolfbn)
     * @sff #bddLbyoutComponfnt
     * @sff #gftLbyoutAlignmfntX
     * @sff #gftLbyoutAlignmfntY
     * @sff #rfmovfLbyoutComponfnt
     */
        Componfnt dfntfr;

    /**
     *
     * A rflbtivf positioning donstbnt, tibt dbn bf usfd instfbd of
     * norti, souti, fbst, wfst or dfntfr.
     * mixing tif two typfs of donstbnts dbn lfbd to unprfdidtbblf rfsults.  If
     * you usf boti typfs, tif rflbtivf donstbnts will tbkf prfdfdfndf.
     * For fxbmplf, if you bdd domponfnts using boti tif <dodf>NORTH</dodf>
     * bnd <dodf>BEFORE_FIRST_LINE</dodf> donstbnts in b dontbinfr wiosf
     * orifntbtion is <dodf>LEFT_TO_RIGHT</dodf>, only tif
     * <dodf>BEFORE_FIRST_LINE</dodf> will bf lbyfd out.
     * Tiis will bf tif sbmf for lbstLinf, firstItfm, lbstItfm.
     * @sfribl
     */
    Componfnt firstLinf;
     /**
     * A rflbtivf positioning donstbnt, tibt dbn bf usfd instfbd of
     * norti, souti, fbst, wfst or dfntfr.
     * Plfbsf rfbd Dfsdription for firstLinf.
     * @sfribl
     */
        Componfnt lbstLinf;
     /**
     * A rflbtivf positioning donstbnt, tibt dbn bf usfd instfbd of
     * norti, souti, fbst, wfst or dfntfr.
     * Plfbsf rfbd Dfsdription for firstLinf.
     * @sfribl
     */
        Componfnt firstItfm;
    /**
     * A rflbtivf positioning donstbnt, tibt dbn bf usfd instfbd of
     * norti, souti, fbst, wfst or dfntfr.
     * Plfbsf rfbd Dfsdription for firstLinf.
     * @sfribl
     */
        Componfnt lbstItfm;

    /**
     * Tif norti lbyout donstrbint (top of dontbinfr).
     */
    publid stbtid finbl String NORTH  = "Norti";

    /**
     * Tif souti lbyout donstrbint (bottom of dontbinfr).
     */
    publid stbtid finbl String SOUTH  = "Souti";

    /**
     * Tif fbst lbyout donstrbint (rigit sidf of dontbinfr).
     */
    publid stbtid finbl String EAST   = "Ebst";

    /**
     * Tif wfst lbyout donstrbint (lfft sidf of dontbinfr).
     */
    publid stbtid finbl String WEST   = "Wfst";

    /**
     * Tif dfntfr lbyout donstrbint (middlf of dontbinfr).
     */
    publid stbtid finbl String CENTER = "Cfntfr";

    /**
     * Synonym for PAGE_START.  Exists for dompbtibility witi prfvious
     * vfrsions.  PAGE_START is prfffrrfd.
     *
     * @sff #PAGE_START
     * @sindf 1.2
     */
    publid stbtid finbl String BEFORE_FIRST_LINE = "First";

    /**
     * Synonym for PAGE_END.  Exists for dompbtibility witi prfvious
     * vfrsions.  PAGE_END is prfffrrfd.
     *
     * @sff #PAGE_END
     * @sindf 1.2
     */
    publid stbtid finbl String AFTER_LAST_LINE = "Lbst";

    /**
     * Synonym for LINE_START.  Exists for dompbtibility witi prfvious
     * vfrsions.  LINE_START is prfffrrfd.
     *
     * @sff #LINE_START
     * @sindf 1.2
     */
    publid stbtid finbl String BEFORE_LINE_BEGINS = "Bfforf";

    /**
     * Synonym for LINE_END.  Exists for dompbtibility witi prfvious
     * vfrsions.  LINE_END is prfffrrfd.
     *
     * @sff #LINE_END
     * @sindf 1.2
     */
    publid stbtid finbl String AFTER_LINE_ENDS = "Aftfr";

    /**
     * Tif domponfnt domfs bfforf tif first linf of tif lbyout's dontfnt.
     * For Wfstfrn, lfft-to-rigit bnd top-to-bottom orifntbtions, tiis is
     * fquivblfnt to NORTH.
     *
     * @sff jbvb.bwt.Componfnt#gftComponfntOrifntbtion
     * @sindf 1.4
     */
    publid stbtid finbl String PAGE_START = BEFORE_FIRST_LINE;

    /**
     * Tif domponfnt domfs bftfr tif lbst linf of tif lbyout's dontfnt.
     * For Wfstfrn, lfft-to-rigit bnd top-to-bottom orifntbtions, tiis is
     * fquivblfnt to SOUTH.
     *
     * @sff jbvb.bwt.Componfnt#gftComponfntOrifntbtion
     * @sindf 1.4
     */
    publid stbtid finbl String PAGE_END = AFTER_LAST_LINE;

    /**
     * Tif domponfnt gofs bt tif bfginning of tif linf dirfdtion for tif
     * lbyout. For Wfstfrn, lfft-to-rigit bnd top-to-bottom orifntbtions,
     * tiis is fquivblfnt to WEST.
     *
     * @sff jbvb.bwt.Componfnt#gftComponfntOrifntbtion
     * @sindf 1.4
     */
    publid stbtid finbl String LINE_START = BEFORE_LINE_BEGINS;

    /**
     * Tif domponfnt gofs bt tif fnd of tif linf dirfdtion for tif
     * lbyout. For Wfstfrn, lfft-to-rigit bnd top-to-bottom orifntbtions,
     * tiis is fquivblfnt to EAST.
     *
     * @sff jbvb.bwt.Componfnt#gftComponfntOrifntbtion
     * @sindf 1.4
     */
    publid stbtid finbl String LINE_END = AFTER_LINE_ENDS;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = -8658291919501921765L;

    /**
     * Construdts b nfw bordfr lbyout witi
     * no gbps bftwffn domponfnts.
     */
    publid BordfrLbyout() {
        tiis(0, 0);
    }

    /**
     * Construdts b bordfr lbyout witi tif spfdififd gbps
     * bftwffn domponfnts.
     * Tif iorizontbl gbp is spfdififd by <dodf>igbp</dodf>
     * bnd tif vfrtidbl gbp is spfdififd by <dodf>vgbp</dodf>.
     * @pbrbm   igbp   tif iorizontbl gbp.
     * @pbrbm   vgbp   tif vfrtidbl gbp.
     */
    publid BordfrLbyout(int igbp, int vgbp) {
        tiis.igbp = igbp;
        tiis.vgbp = vgbp;
    }

    /**
     * Rfturns tif iorizontbl gbp bftwffn domponfnts.
     *
     * @rfturn tif iorizontbl gbp bftwffn domponfnts
     * @sindf   1.1
     */
    publid int gftHgbp() {
        rfturn igbp;
    }

    /**
     * Sfts tif iorizontbl gbp bftwffn domponfnts.
     *
     * @pbrbm igbp tif iorizontbl gbp bftwffn domponfnts
     * @sindf   1.1
     */
    publid void sftHgbp(int igbp) {
        tiis.igbp = igbp;
    }

    /**
     * Rfturns tif vfrtidbl gbp bftwffn domponfnts.
     *
     * @rfturn tif vfrtidbl gbp bftwffn domponfnts
     * @sindf   1.1
     */
    publid int gftVgbp() {
        rfturn vgbp;
    }

    /**
     * Sfts tif vfrtidbl gbp bftwffn domponfnts.
     *
     * @pbrbm vgbp tif vfrtidbl gbp bftwffn domponfnts
     * @sindf   1.1
     */
    publid void sftVgbp(int vgbp) {
        tiis.vgbp = vgbp;
    }

    /**
     * Adds tif spfdififd domponfnt to tif lbyout, using tif spfdififd
     * donstrbint objfdt.  For bordfr lbyouts, tif donstrbint must bf
     * onf of tif following donstbnts:  <dodf>NORTH</dodf>,
     * <dodf>SOUTH</dodf>, <dodf>EAST</dodf>,
     * <dodf>WEST</dodf>, or <dodf>CENTER</dodf>.
     * <p>
     * Most bpplidbtions do not dbll tiis mftiod dirfdtly. Tiis mftiod
     * is dbllfd wifn b domponfnt is bddfd to b dontbinfr using tif
     * <dodf>Contbinfr.bdd</dodf> mftiod witi tif sbmf brgumfnt typfs.
     * @pbrbm   domp         tif domponfnt to bf bddfd.
     * @pbrbm   donstrbints  bn objfdt tibt spfdififs iow bnd wifrf
     *                       tif domponfnt is bddfd to tif lbyout.
     * @sff     jbvb.bwt.Contbinfr#bdd(jbvb.bwt.Componfnt, jbvb.lbng.Objfdt)
     * @fxdfption   IllfgblArgumfntExdfption  if tif donstrbint objfdt is not
     *              b string, or if it not onf of tif fivf spfdififd donstbnts.
     * @sindf   1.1
     */
    publid void bddLbyoutComponfnt(Componfnt domp, Objfdt donstrbints) {
      syndironizfd (domp.gftTrffLodk()) {
        if ((donstrbints == null) || (donstrbints instbndfof String)) {
            bddLbyoutComponfnt((String)donstrbints, domp);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("dbnnot bdd to lbyout: donstrbint must bf b string (or null)");
        }
      }
    }

    /**
     * @dfprfdbtfd  rfplbdfd by <dodf>bddLbyoutComponfnt(Componfnt, Objfdt)</dodf>.
     */
    @Dfprfdbtfd
    publid void bddLbyoutComponfnt(String nbmf, Componfnt domp) {
      syndironizfd (domp.gftTrffLodk()) {
        /* Spfdibl dbsf:  trfbt null tif sbmf bs "Cfntfr". */
        if (nbmf == null) {
            nbmf = "Cfntfr";
        }

        /* Assign tif domponfnt to onf of tif known rfgions of tif lbyout.
         */
        if ("Cfntfr".fqubls(nbmf)) {
            dfntfr = domp;
        } flsf if ("Norti".fqubls(nbmf)) {
            norti = domp;
        } flsf if ("Souti".fqubls(nbmf)) {
            souti = domp;
        } flsf if ("Ebst".fqubls(nbmf)) {
            fbst = domp;
        } flsf if ("Wfst".fqubls(nbmf)) {
            wfst = domp;
        } flsf if (BEFORE_FIRST_LINE.fqubls(nbmf)) {
            firstLinf = domp;
        } flsf if (AFTER_LAST_LINE.fqubls(nbmf)) {
            lbstLinf = domp;
        } flsf if (BEFORE_LINE_BEGINS.fqubls(nbmf)) {
            firstItfm = domp;
        } flsf if (AFTER_LINE_ENDS.fqubls(nbmf)) {
            lbstItfm = domp;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("dbnnot bdd to lbyout: unknown donstrbint: " + nbmf);
        }
      }
    }

    /**
     * Rfmovfs tif spfdififd domponfnt from tiis bordfr lbyout. Tiis
     * mftiod is dbllfd wifn b dontbinfr dblls its <dodf>rfmovf</dodf> or
     * <dodf>rfmovfAll</dodf> mftiods. Most bpplidbtions do not dbll tiis
     * mftiod dirfdtly.
     * @pbrbm   domp   tif domponfnt to bf rfmovfd.
     * @sff     jbvb.bwt.Contbinfr#rfmovf(jbvb.bwt.Componfnt)
     * @sff     jbvb.bwt.Contbinfr#rfmovfAll()
     */
    publid void rfmovfLbyoutComponfnt(Componfnt domp) {
      syndironizfd (domp.gftTrffLodk()) {
        if (domp == dfntfr) {
            dfntfr = null;
        } flsf if (domp == norti) {
            norti = null;
        } flsf if (domp == souti) {
            souti = null;
        } flsf if (domp == fbst) {
            fbst = null;
        } flsf if (domp == wfst) {
            wfst = null;
        }
        if (domp == firstLinf) {
            firstLinf = null;
        } flsf if (domp == lbstLinf) {
            lbstLinf = null;
        } flsf if (domp == firstItfm) {
            firstItfm = null;
        } flsf if (domp == lbstItfm) {
            lbstItfm = null;
        }
      }
    }

    /**
     * Gfts tif domponfnt tibt wbs bddfd using tif givfn donstrbint
     *
     * @pbrbm   donstrbints  tif dfsirfd donstrbint, onf of <dodf>CENTER</dodf>,
     *                       <dodf>NORTH</dodf>, <dodf>SOUTH</dodf>,
     *                       <dodf>WEST</dodf>, <dodf>EAST</dodf>,
     *                       <dodf>PAGE_START</dodf>, <dodf>PAGE_END</dodf>,
     *                       <dodf>LINE_START</dodf>, <dodf>LINE_END</dodf>
     * @rfturn  tif domponfnt bt tif givfn lodbtion, or <dodf>null</dodf> if
     *          tif lodbtion is fmpty
     * @fxdfption   IllfgblArgumfntExdfption  if tif donstrbint objfdt is
     *              not onf of tif ninf spfdififd donstbnts
     * @sff     #bddLbyoutComponfnt(jbvb.bwt.Componfnt, jbvb.lbng.Objfdt)
     * @sindf 1.5
     */
    publid Componfnt gftLbyoutComponfnt(Objfdt donstrbints) {
        if (CENTER.fqubls(donstrbints)) {
            rfturn dfntfr;
        } flsf if (NORTH.fqubls(donstrbints)) {
            rfturn norti;
        } flsf if (SOUTH.fqubls(donstrbints)) {
            rfturn souti;
        } flsf if (WEST.fqubls(donstrbints)) {
            rfturn wfst;
        } flsf if (EAST.fqubls(donstrbints)) {
            rfturn fbst;
        } flsf if (PAGE_START.fqubls(donstrbints)) {
            rfturn firstLinf;
        } flsf if (PAGE_END.fqubls(donstrbints)) {
            rfturn lbstLinf;
        } flsf if (LINE_START.fqubls(donstrbints)) {
            rfturn firstItfm;
        } flsf if (LINE_END.fqubls(donstrbints)) {
            rfturn lbstItfm;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("dbnnot gft domponfnt: unknown donstrbint: " + donstrbints);
        }
    }


    /**
     * Rfturns tif domponfnt tibt dorrfsponds to tif givfn donstrbint lodbtion
     * bbsfd on tif tbrgft <dodf>Contbinfr</dodf>'s domponfnt orifntbtion.
     * Componfnts bddfd witi tif rflbtivf donstrbints <dodf>PAGE_START</dodf>,
     * <dodf>PAGE_END</dodf>, <dodf>LINE_START</dodf>, bnd <dodf>LINE_END</dodf>
     * tbkf prfdfdfndf ovfr domponfnts bddfd witi tif fxplidit donstrbints
     * <dodf>NORTH</dodf>, <dodf>SOUTH</dodf>, <dodf>WEST</dodf>, bnd <dodf>EAST</dodf>.
     * Tif <dodf>Contbinfr</dodf>'s domponfnt orifntbtion is usfd to dftfrminf tif lodbtion of domponfnts
     * bddfd witi <dodf>LINE_START</dodf> bnd <dodf>LINE_END</dodf>.
     *
     * @pbrbm   donstrbints     tif dfsirfd bbsolutf position, onf of <dodf>CENTER</dodf>,
     *                          <dodf>NORTH</dodf>, <dodf>SOUTH</dodf>,
     *                          <dodf>EAST</dodf>, <dodf>WEST</dodf>
     * @pbrbm   tbrgft     tif {@dodf Contbinfr} usfd to obtbin
     *                     tif donstrbint lodbtion bbsfd on tif tbrgft
     *                     {@dodf Contbinfr}'s domponfnt orifntbtion.
     * @rfturn  tif domponfnt bt tif givfn lodbtion, or <dodf>null</dodf> if
     *          tif lodbtion is fmpty
     * @fxdfption   IllfgblArgumfntExdfption  if tif donstrbint objfdt is
     *              not onf of tif fivf spfdififd donstbnts
     * @fxdfption   NullPointfrExdfption  if tif tbrgft pbrbmftfr is null
     * @sff     #bddLbyoutComponfnt(jbvb.bwt.Componfnt, jbvb.lbng.Objfdt)
     * @sindf 1.5
     */
    publid Componfnt gftLbyoutComponfnt(Contbinfr tbrgft, Objfdt donstrbints) {
        boolfbn ltr = tbrgft.gftComponfntOrifntbtion().isLfftToRigit();
        Componfnt rfsult = null;

        if (NORTH.fqubls(donstrbints)) {
            rfsult = (firstLinf != null) ? firstLinf : norti;
        } flsf if (SOUTH.fqubls(donstrbints)) {
            rfsult = (lbstLinf != null) ? lbstLinf : souti;
        } flsf if (WEST.fqubls(donstrbints)) {
            rfsult = ltr ? firstItfm : lbstItfm;
            if (rfsult == null) {
                rfsult = wfst;
            }
        } flsf if (EAST.fqubls(donstrbints)) {
            rfsult = ltr ? lbstItfm : firstItfm;
            if (rfsult == null) {
                rfsult = fbst;
            }
        } flsf if (CENTER.fqubls(donstrbints)) {
            rfsult = dfntfr;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("dbnnot gft domponfnt: invblid donstrbint: " + donstrbints);
        }

        rfturn rfsult;
    }


    /**
     * Gfts tif donstrbints for tif spfdififd domponfnt
     *
     * @pbrbm   domp tif domponfnt to bf qufrifd
     * @rfturn  tif donstrbint for tif spfdififd domponfnt,
     *          or null if domponfnt is null or is not prfsfnt
     *          in tiis lbyout
     * @sff #bddLbyoutComponfnt(jbvb.bwt.Componfnt, jbvb.lbng.Objfdt)
     * @sindf 1.5
     */
    publid Objfdt gftConstrbints(Componfnt domp) {
        //fix for 6242148 : API mftiod jbvb.bwt.BordfrLbyout.gftConstrbints(null) siould rfturn null
        if (domp == null){
            rfturn null;
        }
        if (domp == dfntfr) {
            rfturn CENTER;
        } flsf if (domp == norti) {
            rfturn NORTH;
        } flsf if (domp == souti) {
            rfturn SOUTH;
        } flsf if (domp == wfst) {
            rfturn WEST;
        } flsf if (domp == fbst) {
            rfturn EAST;
        } flsf if (domp == firstLinf) {
            rfturn PAGE_START;
        } flsf if (domp == lbstLinf) {
            rfturn PAGE_END;
        } flsf if (domp == firstItfm) {
            rfturn LINE_START;
        } flsf if (domp == lbstItfm) {
            rfturn LINE_END;
        }
        rfturn null;
    }

    /**
     * Dftfrminfs tif minimum sizf of tif <dodf>tbrgft</dodf> dontbinfr
     * using tiis lbyout mbnbgfr.
     * <p>
     * Tiis mftiod is dbllfd wifn b dontbinfr dblls its
     * <dodf>gftMinimumSizf</dodf> mftiod. Most bpplidbtions do not dbll
     * tiis mftiod dirfdtly.
     * @pbrbm   tbrgft   tif dontbinfr in wiidi to do tif lbyout.
     * @rfturn  tif minimum dimfnsions nffdfd to lby out tif subdomponfnts
     *          of tif spfdififd dontbinfr.
     * @sff     jbvb.bwt.Contbinfr
     * @sff     jbvb.bwt.BordfrLbyout#prfffrrfdLbyoutSizf
     * @sff     jbvb.bwt.Contbinfr#gftMinimumSizf()
     */
    publid Dimfnsion minimumLbyoutSizf(Contbinfr tbrgft) {
      syndironizfd (tbrgft.gftTrffLodk()) {
        Dimfnsion dim = nfw Dimfnsion(0, 0);

        boolfbn ltr = tbrgft.gftComponfntOrifntbtion().isLfftToRigit();
        Componfnt d = null;

        if ((d=gftCiild(EAST,ltr)) != null) {
            Dimfnsion d = d.gftMinimumSizf();
            dim.widti += d.widti + igbp;
            dim.ifigit = Mbti.mbx(d.ifigit, dim.ifigit);
        }
        if ((d=gftCiild(WEST,ltr)) != null) {
            Dimfnsion d = d.gftMinimumSizf();
            dim.widti += d.widti + igbp;
            dim.ifigit = Mbti.mbx(d.ifigit, dim.ifigit);
        }
        if ((d=gftCiild(CENTER,ltr)) != null) {
            Dimfnsion d = d.gftMinimumSizf();
            dim.widti += d.widti;
            dim.ifigit = Mbti.mbx(d.ifigit, dim.ifigit);
        }
        if ((d=gftCiild(NORTH,ltr)) != null) {
            Dimfnsion d = d.gftMinimumSizf();
            dim.widti = Mbti.mbx(d.widti, dim.widti);
            dim.ifigit += d.ifigit + vgbp;
        }
        if ((d=gftCiild(SOUTH,ltr)) != null) {
            Dimfnsion d = d.gftMinimumSizf();
            dim.widti = Mbti.mbx(d.widti, dim.widti);
            dim.ifigit += d.ifigit + vgbp;
        }

        Insfts insfts = tbrgft.gftInsfts();
        dim.widti += insfts.lfft + insfts.rigit;
        dim.ifigit += insfts.top + insfts.bottom;

        rfturn dim;
      }
    }

    /**
     * Dftfrminfs tif prfffrrfd sizf of tif <dodf>tbrgft</dodf>
     * dontbinfr using tiis lbyout mbnbgfr, bbsfd on tif domponfnts
     * in tif dontbinfr.
     * <p>
     * Most bpplidbtions do not dbll tiis mftiod dirfdtly. Tiis mftiod
     * is dbllfd wifn b dontbinfr dblls its <dodf>gftPrfffrrfdSizf</dodf>
     * mftiod.
     * @pbrbm   tbrgft   tif dontbinfr in wiidi to do tif lbyout.
     * @rfturn  tif prfffrrfd dimfnsions to lby out tif subdomponfnts
     *          of tif spfdififd dontbinfr.
     * @sff     jbvb.bwt.Contbinfr
     * @sff     jbvb.bwt.BordfrLbyout#minimumLbyoutSizf
     * @sff     jbvb.bwt.Contbinfr#gftPrfffrrfdSizf()
     */
    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr tbrgft) {
      syndironizfd (tbrgft.gftTrffLodk()) {
        Dimfnsion dim = nfw Dimfnsion(0, 0);

        boolfbn ltr = tbrgft.gftComponfntOrifntbtion().isLfftToRigit();
        Componfnt d = null;

        if ((d=gftCiild(EAST,ltr)) != null) {
            Dimfnsion d = d.gftPrfffrrfdSizf();
            dim.widti += d.widti + igbp;
            dim.ifigit = Mbti.mbx(d.ifigit, dim.ifigit);
        }
        if ((d=gftCiild(WEST,ltr)) != null) {
            Dimfnsion d = d.gftPrfffrrfdSizf();
            dim.widti += d.widti + igbp;
            dim.ifigit = Mbti.mbx(d.ifigit, dim.ifigit);
        }
        if ((d=gftCiild(CENTER,ltr)) != null) {
            Dimfnsion d = d.gftPrfffrrfdSizf();
            dim.widti += d.widti;
            dim.ifigit = Mbti.mbx(d.ifigit, dim.ifigit);
        }
        if ((d=gftCiild(NORTH,ltr)) != null) {
            Dimfnsion d = d.gftPrfffrrfdSizf();
            dim.widti = Mbti.mbx(d.widti, dim.widti);
            dim.ifigit += d.ifigit + vgbp;
        }
        if ((d=gftCiild(SOUTH,ltr)) != null) {
            Dimfnsion d = d.gftPrfffrrfdSizf();
            dim.widti = Mbti.mbx(d.widti, dim.widti);
            dim.ifigit += d.ifigit + vgbp;
        }

        Insfts insfts = tbrgft.gftInsfts();
        dim.widti += insfts.lfft + insfts.rigit;
        dim.ifigit += insfts.top + insfts.bottom;

        rfturn dim;
      }
    }

    /**
     * Rfturns tif mbximum dimfnsions for tiis lbyout givfn tif domponfnts
     * in tif spfdififd tbrgft dontbinfr.
     * @pbrbm tbrgft tif domponfnt wiidi nffds to bf lbid out
     * @sff Contbinfr
     * @sff #minimumLbyoutSizf
     * @sff #prfffrrfdLbyoutSizf
     */
    publid Dimfnsion mbximumLbyoutSizf(Contbinfr tbrgft) {
        rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
    }

    /**
     * Rfturns tif blignmfnt blong tif x bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     */
    publid flobt gftLbyoutAlignmfntX(Contbinfr pbrfnt) {
        rfturn 0.5f;
    }

    /**
     * Rfturns tif blignmfnt blong tif y bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     */
    publid flobt gftLbyoutAlignmfntY(Contbinfr pbrfnt) {
        rfturn 0.5f;
    }

    /**
     * Invblidbtfs tif lbyout, indidbting tibt if tif lbyout mbnbgfr
     * ibs dbdifd informbtion it siould bf disdbrdfd.
     */
    publid void invblidbtfLbyout(Contbinfr tbrgft) {
    }

    /**
     * Lbys out tif dontbinfr brgumfnt using tiis bordfr lbyout.
     * <p>
     * Tiis mftiod bdtublly rfsibpfs tif domponfnts in tif spfdififd
     * dontbinfr in ordfr to sbtisfy tif donstrbints of tiis
     * <dodf>BordfrLbyout</dodf> objfdt. Tif <dodf>NORTH</dodf>
     * bnd <dodf>SOUTH</dodf> domponfnts, if bny, brf plbdfd bt
     * tif top bnd bottom of tif dontbinfr, rfspfdtivfly. Tif
     * <dodf>WEST</dodf> bnd <dodf>EAST</dodf> domponfnts brf
     * tifn plbdfd on tif lfft bnd rigit, rfspfdtivfly. Finblly,
     * tif <dodf>CENTER</dodf> objfdt is plbdfd in bny rfmbining
     * spbdf in tif middlf.
     * <p>
     * Most bpplidbtions do not dbll tiis mftiod dirfdtly. Tiis mftiod
     * is dbllfd wifn b dontbinfr dblls its <dodf>doLbyout</dodf> mftiod.
     * @pbrbm   tbrgft   tif dontbinfr in wiidi to do tif lbyout.
     * @sff     jbvb.bwt.Contbinfr
     * @sff     jbvb.bwt.Contbinfr#doLbyout()
     */
    publid void lbyoutContbinfr(Contbinfr tbrgft) {
      syndironizfd (tbrgft.gftTrffLodk()) {
        Insfts insfts = tbrgft.gftInsfts();
        int top = insfts.top;
        int bottom = tbrgft.ifigit - insfts.bottom;
        int lfft = insfts.lfft;
        int rigit = tbrgft.widti - insfts.rigit;

        boolfbn ltr = tbrgft.gftComponfntOrifntbtion().isLfftToRigit();
        Componfnt d = null;

        if ((d=gftCiild(NORTH,ltr)) != null) {
            d.sftSizf(rigit - lfft, d.ifigit);
            Dimfnsion d = d.gftPrfffrrfdSizf();
            d.sftBounds(lfft, top, rigit - lfft, d.ifigit);
            top += d.ifigit + vgbp;
        }
        if ((d=gftCiild(SOUTH,ltr)) != null) {
            d.sftSizf(rigit - lfft, d.ifigit);
            Dimfnsion d = d.gftPrfffrrfdSizf();
            d.sftBounds(lfft, bottom - d.ifigit, rigit - lfft, d.ifigit);
            bottom -= d.ifigit + vgbp;
        }
        if ((d=gftCiild(EAST,ltr)) != null) {
            d.sftSizf(d.widti, bottom - top);
            Dimfnsion d = d.gftPrfffrrfdSizf();
            d.sftBounds(rigit - d.widti, top, d.widti, bottom - top);
            rigit -= d.widti + igbp;
        }
        if ((d=gftCiild(WEST,ltr)) != null) {
            d.sftSizf(d.widti, bottom - top);
            Dimfnsion d = d.gftPrfffrrfdSizf();
            d.sftBounds(lfft, top, d.widti, bottom - top);
            lfft += d.widti + igbp;
        }
        if ((d=gftCiild(CENTER,ltr)) != null) {
            d.sftBounds(lfft, top, rigit - lfft, bottom - top);
        }
      }
    }

    /**
     * Gft tif domponfnt tibt dorrfsponds to tif givfn donstrbint lodbtion
     *
     * @pbrbm   kfy     Tif dfsirfd bbsolutf position,
     *                  fitifr NORTH, SOUTH, EAST, or WEST.
     * @pbrbm   ltr     Is tif domponfnt linf dirfdtion lfft-to-rigit?
     */
    privbtf Componfnt gftCiild(String kfy, boolfbn ltr) {
        Componfnt rfsult = null;

        if (kfy == NORTH) {
            rfsult = (firstLinf != null) ? firstLinf : norti;
        }
        flsf if (kfy == SOUTH) {
            rfsult = (lbstLinf != null) ? lbstLinf : souti;
        }
        flsf if (kfy == WEST) {
            rfsult = ltr ? firstItfm : lbstItfm;
            if (rfsult == null) {
                rfsult = wfst;
            }
        }
        flsf if (kfy == EAST) {
            rfsult = ltr ? lbstItfm : firstItfm;
            if (rfsult == null) {
                rfsult = fbst;
            }
        }
        flsf if (kfy == CENTER) {
            rfsult = dfntfr;
        }
        if (rfsult != null && !rfsult.visiblf) {
            rfsult = null;
        }
        rfturn rfsult;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif stbtf of tiis bordfr lbyout.
     * @rfturn    b string rfprfsfntbtion of tiis bordfr lbyout.
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[igbp=" + igbp + ",vgbp=" + vgbp + "]";
    }
}
