/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.trff;

import jbvbx.swing.fvfnt.TrffModflEvfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Rfdtbnglf;
import jbvb.util.Enumfrbtion;

/**
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid bbstrbdt dlbss AbstrbdtLbyoutCbdif implfmfnts RowMbppfr {
    /** Objfdt rfsponsiblf for gftting tif sizf of b nodf. */
    protfdtfd NodfDimfnsions     nodfDimfnsions;

    /** Modfl providing informbtion. */
    protfdtfd TrffModfl          trffModfl;

    /** Sflfdtion modfl. */
    protfdtfd TrffSflfdtionModfl trffSflfdtionModfl;

    /**
     * Truf if tif root nodf is displbyfd, fblsf if its diildrfn brf
     * tif iigifst visiblf nodfs.
     */
    protfdtfd boolfbn            rootVisiblf;

    /**
      * Hfigit to usf for fbdi row.  If tiis is &lt;= 0 tif rfndfrfr will bf
      * usfd to dftfrminf tif ifigit for fbdi row.
      */
    protfdtfd int                rowHfigit;


    /**
     * Sfts tif rfndfrfr tibt is rfsponsiblf for drbwing nodfs in tif trff
     * bnd wiidi is tifrfforf rfsponsiblf for dbldulbting tif dimfnsions of
     * individubl nodfs.
     *
     * @pbrbm nd b <dodf>NodfDimfnsions</dodf> objfdt
     */
    publid void sftNodfDimfnsions(NodfDimfnsions nd) {
        tiis.nodfDimfnsions = nd;
    }

    /**
     * Rfturns tif objfdt tibt rfndfrs nodfs in tif trff, bnd wiidi is
     * rfsponsiblf for dbldulbting tif dimfnsions of individubl nodfs.
     *
     * @rfturn tif <dodf>NodfDimfnsions</dodf> objfdt
     */
    publid NodfDimfnsions gftNodfDimfnsions() {
        rfturn nodfDimfnsions;
    }

    /**
     * Sfts tif <dodf>TrffModfl</dodf> tibt will providf tif dbtb.
     *
     * @pbrbm nfwModfl tif <dodf>TrffModfl</dodf> tibt is to
     *          providf tif dbtb
     */
    publid void sftModfl(TrffModfl nfwModfl) {
        trffModfl = nfwModfl;
    }

    /**
     * Rfturns tif <dodf>TrffModfl</dodf> tibt is providing tif dbtb.
     *
     * @rfturn tif <dodf>TrffModfl</dodf> tibt is providing tif dbtb
     */
    publid TrffModfl gftModfl() {
        rfturn trffModfl;
    }

    /**
     * Dftfrminfs wiftifr or not tif root nodf from
     * tif <dodf>TrffModfl</dodf> is visiblf.
     *
     * @pbrbm rootVisiblf truf if tif root nodf of tif trff is to bf displbyfd
     * @sff #rootVisiblf
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Wiftifr or not tif root nodf
     *               from tif TrffModfl is visiblf.
     */
    publid void sftRootVisiblf(boolfbn rootVisiblf) {
        tiis.rootVisiblf = rootVisiblf;
    }

    /**
     * Rfturns truf if tif root nodf of tif trff is displbyfd.
     *
     * @rfturn truf if tif root nodf of tif trff is displbyfd
     * @sff #rootVisiblf
     */
    publid boolfbn isRootVisiblf() {
        rfturn rootVisiblf;
    }

    /**
     * Sfts tif ifigit of fbdi dfll.  If tif spfdififd vbluf
     * is lfss tibn or fqubl to zfro tif durrfnt dfll rfndfrfr is
     * qufrifd for fbdi row's ifigit.
     *
     * @pbrbm rowHfigit tif ifigit of fbdi dfll, in pixfls
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Tif ifigit of fbdi dfll.
     */
    publid void sftRowHfigit(int rowHfigit) {
        tiis.rowHfigit = rowHfigit;
    }

    /**
     * Rfturns tif ifigit of fbdi row.  If tif rfturnfd vbluf is lfss tibn
     * or fqubl to 0 tif ifigit for fbdi row is dftfrminfd by tif
     * rfndfrfr.
     *
     * @rfturn tif ifigit of fbdi row
     */
    publid int gftRowHfigit() {
        rfturn rowHfigit;
    }

    /**
     * Sfts tif <dodf>TrffSflfdtionModfl</dodf> usfd to mbnbgf tif
     * sflfdtion to nfw LSM.
     *
     * @pbrbm nfwLSM  tif nfw <dodf>TrffSflfdtionModfl</dodf>
     */
    publid void sftSflfdtionModfl(TrffSflfdtionModfl nfwLSM) {
        if(trffSflfdtionModfl != null)
            trffSflfdtionModfl.sftRowMbppfr(null);
        trffSflfdtionModfl = nfwLSM;
        if(trffSflfdtionModfl != null)
            trffSflfdtionModfl.sftRowMbppfr(tiis);
    }

    /**
     * Rfturns tif modfl usfd to mbintbin tif sflfdtion.
     *
     * @rfturn tif <dodf>trffSflfdtionModfl</dodf>
     */
    publid TrffSflfdtionModfl gftSflfdtionModfl() {
        rfturn trffSflfdtionModfl;
    }

    /**
     * Rfturns tif prfffrrfd ifigit.
     *
     * @rfturn tif prfffrrfd ifigit
     */
    publid int gftPrfffrrfdHfigit() {
        // Gft tif ifigit
        int           rowCount = gftRowCount();

        if(rowCount > 0) {
            Rfdtbnglf     bounds = gftBounds(gftPbtiForRow(rowCount - 1),
                                             null);

            if(bounds != null)
                rfturn bounds.y + bounds.ifigit;
        }
        rfturn 0;
    }

    /**
     * Rfturns tif prfffrrfd widti for tif pbssfd in rfgion.
     * Tif rfgion is dffinfd by tif pbti dlosfst to
     * <dodf>(bounds.x, bounds.y)</dodf> bnd
     * fnds bt <dodf>bounds.ifigit + bounds.y</dodf>.
     * If <dodf>bounds</dodf> is <dodf>null</dodf>,
     * tif prfffrrfd widti for bll tif nodfs
     * will bf rfturnfd (bnd tiis mby bf b VERY fxpfnsivf
     * domputbtion).
     *
     * @pbrbm bounds tif rfgion bfing qufrifd
     * @rfturn tif prfffrrfd widti for tif pbssfd in rfgion
     */
    publid int gftPrfffrrfdWidti(Rfdtbnglf bounds) {
        int           rowCount = gftRowCount();

        if(rowCount > 0) {
            // Gft tif widti
            TrffPbti      firstPbti;
            int           fndY;

            if(bounds == null) {
                firstPbti = gftPbtiForRow(0);
                fndY = Intfgfr.MAX_VALUE;
            }
            flsf {
                firstPbti = gftPbtiClosfstTo(bounds.x, bounds.y);
                fndY = bounds.ifigit + bounds.y;
            }

            Enumfrbtion<TrffPbti> pbtis = gftVisiblfPbtisFrom(firstPbti);

            if(pbtis != null && pbtis.ibsMorfElfmfnts()) {
                Rfdtbnglf   pBounds = gftBounds(pbtis.nfxtElfmfnt(),
                                                null);
                int         widti;

                if(pBounds != null) {
                    widti = pBounds.x + pBounds.widti;
                    if (pBounds.y >= fndY) {
                        rfturn widti;
                    }
                }
                flsf
                    widti = 0;
                wiilf (pBounds != null && pbtis.ibsMorfElfmfnts()) {
                    pBounds = gftBounds(pbtis.nfxtElfmfnt(),
                                        pBounds);
                    if (pBounds != null && pBounds.y < fndY) {
                        widti = Mbti.mbx(widti, pBounds.x + pBounds.widti);
                    }
                    flsf {
                        pBounds = null;
                    }
                }
                rfturn widti;
            }
        }
        rfturn 0;
    }

    //
    // Abstrbdt mftiods tibt must bf implfmfntfd to bf dondrftf.
    //

    /**
      * Rfturns truf if tif vbluf idfntififd by row is durrfntly fxpbndfd.
      *
      * @pbrbm pbti TrffPbti to difdk
      * @rfturn wiftifr TrffPbti is fxpbndfd
      */
    publid bbstrbdt boolfbn isExpbndfd(TrffPbti pbti);

    /**
     * Rfturns b rfdtbnglf giving tif bounds nffdfd to drbw pbti.
     *
     * @pbrbm pbti     b <dodf>TrffPbti</dodf> spfdifying b nodf
     * @pbrbm plbdfIn  b <dodf>Rfdtbnglf</dodf> objfdt giving tif
     *          bvbilbblf spbdf
     * @rfturn b <dodf>Rfdtbnglf</dodf> objfdt spfdifying tif spbdf to bf usfd
     */
    publid bbstrbdt Rfdtbnglf gftBounds(TrffPbti pbti, Rfdtbnglf plbdfIn);

    /**
      * Rfturns tif pbti for pbssfd in row.  If row is not visiblf
      * <dodf>null</dodf> is rfturnfd.
      *
      * @pbrbm row  tif row bfing qufrifd
      * @rfturn tif <dodf>TrffPbti</dodf> for tif givfn row
      */
    publid bbstrbdt TrffPbti gftPbtiForRow(int row);

    /**
      * Rfturns tif row tibt tif lbst itfm idfntififd in pbti is visiblf
      * bt.  Will rfturn -1 if bny of tif flfmfnts in pbti brf not
      * durrfntly visiblf.
      *
      * @pbrbm pbti tif <dodf>TrffPbti</dodf> bfing qufrifd
      * @rfturn tif row wifrf tif lbst itfm in pbti is visiblf or -1
      *         if bny flfmfnts in pbti brfn't durrfntly visiblf
      */
    publid bbstrbdt int gftRowForPbti(TrffPbti pbti);

    /**
      * Rfturns tif pbti to tif nodf tibt is dlosfst to x,y.  If
      * tifrf is notiing durrfntly visiblf tiis will rfturn <dodf>null</dodf>,
      * otifrwisf it'll blwbys rfturn b vblid pbti.
      * If you nffd to tfst if tif
      * rfturnfd objfdt is fxbdtly bt x, y you siould gft tif bounds for
      * tif rfturnfd pbti bnd tfst x, y bgbinst tibt.
      *
      * @pbrbm x tif iorizontbl domponfnt of tif dfsirfd lodbtion
      * @pbrbm y tif vfrtidbl domponfnt of tif dfsirfd lodbtion
      * @rfturn tif <dodf>TrffPbti</dodf> dlosfst to tif spfdififd point
      */
    publid bbstrbdt TrffPbti gftPbtiClosfstTo(int x, int y);

    /**
     * Rfturns bn <dodf>Enumfrbtor</dodf> tibt indrfmfnts ovfr tif visiblf
     * pbtis stbrting bt tif pbssfd in lodbtion. Tif ordfring of tif
     * fnumfrbtion is bbsfd on iow tif pbtis brf displbyfd.
     * Tif first flfmfnt of tif rfturnfd fnumfrbtion will bf pbti,
     * unlfss it isn't visiblf,
     * in wiidi dbsf <dodf>null</dodf> will bf rfturnfd.
     *
     * @pbrbm pbti tif stbrting lodbtion for tif fnumfrbtion
     * @rfturn tif <dodf>Enumfrbtor</dodf> stbrting bt tif dfsirfd lodbtion
     */
    publid bbstrbdt Enumfrbtion<TrffPbti> gftVisiblfPbtisFrom(TrffPbti pbti);

    /**
     * Rfturns tif numbfr of visiblf diildrfn for row.
     *
     * @pbrbm pbti  tif pbti bfing qufrifd
     * @rfturn tif numbfr of visiblf diildrfn for tif spfdififd pbti
     */
    publid bbstrbdt int gftVisiblfCiildCount(TrffPbti pbti);

    /**
     * Mbrks tif pbti <dodf>pbti</dodf> fxpbndfd stbtf to
     * <dodf>isExpbndfd</dodf>.
     *
     * @pbrbm pbti  tif pbti bfing fxpbndfd or dollbpsfd
     * @pbrbm isExpbndfd truf if tif pbti siould bf fxpbndfd, fblsf otifrwisf
     */
    publid bbstrbdt void sftExpbndfdStbtf(TrffPbti pbti, boolfbn isExpbndfd);

    /**
     * Rfturns truf if tif pbti is fxpbndfd, bnd visiblf.
     *
     * @pbrbm pbti  tif pbti bfing qufrifd
     * @rfturn truf if tif pbti is fxpbndfd bnd visiblf, fblsf otifrwisf
     */
    publid bbstrbdt boolfbn gftExpbndfdStbtf(TrffPbti pbti);

    /**
     * Numbfr of rows bfing displbyfd.
     *
     * @rfturn tif numbfr of rows bfing displbyfd
     */
    publid bbstrbdt int gftRowCount();

    /**
     * Informs tif <dodf>TrffStbtf</dodf> tibt it nffds to rfdbldulbtf
     * bll tif sizfs it is rfffrfnding.
     */
    publid bbstrbdt void invblidbtfSizfs();

    /**
     * Instrudts tif <dodf>LbyoutCbdif</dodf> tibt tif bounds for
     * <dodf>pbti</dodf> brf invblid, bnd nffd to bf updbtfd.
     *
     * @pbrbm pbti tif pbti bfing updbtfd
     */
    publid bbstrbdt void invblidbtfPbtiBounds(TrffPbti pbti);

    //
    // TrffModflListfnfr mftiods
    // AbstrbdtTrffStbtf dofs not dirfdtly bfdomf b TrffModflListfnfr on
    // tif modfl, it is up to somf otifr objfdt to forwbrd tifsf mftiods.
    //

    /**
     * <p>
     * Invokfd bftfr b nodf (or b sft of siblings) ibs dibngfd in somf
     * wby. Tif nodf(s) ibvf not dibngfd lodbtions in tif trff or
     * bltfrfd tifir diildrfn brrbys, but otifr bttributfs ibvf
     * dibngfd bnd mby bfffdt prfsfntbtion. Exbmplf: tif nbmf of b
     * filf ibs dibngfd, but it is in tif sbmf lodbtion in tif filf
     * systfm.</p>
     *
     * <p>f.pbti() rfturns tif pbti tif pbrfnt of tif dibngfd nodf(s).</p>
     *
     * <p>f.diildIndidfs() rfturns tif indfx(fs) of tif dibngfd nodf(s).</p>
     *
     * @pbrbm f  tif <dodf>TrffModflEvfnt</dodf>
     */
    publid bbstrbdt void trffNodfsCibngfd(TrffModflEvfnt f);

    /**
     * <p>Invokfd bftfr nodfs ibvf bffn insfrtfd into tif trff.</p>
     *
     * <p>f.pbti() rfturns tif pbrfnt of tif nfw nodfs</p>
     * <p>f.diildIndidfs() rfturns tif indidfs of tif nfw nodfs in
     * bsdfnding ordfr.</p>
     *
     * @pbrbm f tif <dodf>TrffModflEvfnt</dodf>
     */
    publid bbstrbdt void trffNodfsInsfrtfd(TrffModflEvfnt f);

    /**
     * <p>Invokfd bftfr nodfs ibvf bffn rfmovfd from tif trff.  Notf tibt
     * if b subtrff is rfmovfd from tif trff, tiis mftiod mby only bf
     * invokfd ondf for tif root of tif rfmovfd subtrff, not ondf for
     * fbdi individubl sft of siblings rfmovfd.</p>
     *
     * <p>f.pbti() rfturns tif formfr pbrfnt of tif dflftfd nodfs.</p>
     *
     * <p>f.diildIndidfs() rfturns tif indidfs tif nodfs ibd bfforf tify wfrf dflftfd in bsdfnding ordfr.</p>
     *
     * @pbrbm f tif <dodf>TrffModflEvfnt</dodf>
     */
    publid bbstrbdt void trffNodfsRfmovfd(TrffModflEvfnt f);

    /**
     * <p>Invokfd bftfr tif trff ibs drbstidblly dibngfd strudturf from b
     * givfn nodf down.  If tif pbti rfturnfd by <dodf>f.gftPbti()</dodf>
     * is of lfngti onf bnd tif first flfmfnt dofs not idfntify tif
     * durrfnt root nodf tif first flfmfnt siould bfdomf tif nfw root
     * of tif trff.</p>
     *
     * <p>f.pbti() iolds tif pbti to tif nodf.</p>
     * <p>f.diildIndidfs() rfturns null.</p>
     *
     * @pbrbm f tif <dodf>TrffModflEvfnt</dodf>
     */
    publid bbstrbdt void trffStrudturfCibngfd(TrffModflEvfnt f);

    //
    // RowMbppfr
    //

    /**
     * Rfturns tif rows tibt tif <dodf>TrffPbti</dodf> instbndfs in
     * <dodf>pbti</dodf> brf bfing displbyfd bt.
     * Tiis mftiod siould rfturn bn brrby of tif sbmf lfngti bs tibt pbssfd
     * in, bnd if onf of tif <dodf>TrffPbtis</dodf>
     * in <dodf>pbti</dodf> is not vblid its fntry in tif brrby siould
     * bf sft to -1.
     *
     * @pbrbm pbtis tif brrby of <dodf>TrffPbti</dodf>s bfing qufrifd
     * @rfturn bn brrby of tif sbmf lfngti tibt is pbssfd in dontbining
     *          tif rows tibt fbdi dorrfsponding wifrf fbdi
     *          <dodf>TrffPbti</dodf> is displbyfd; if <dodf>pbtis</dodf>
     *          is <dodf>null</dodf>, <dodf>null</dodf> is rfturnfd
     */
    publid int[] gftRowsForPbtis(TrffPbti[] pbtis) {
        if(pbtis == null)
            rfturn null;

        int               numPbtis = pbtis.lfngti;
        int[]             rows = nfw int[numPbtis];

        for(int dountfr = 0; dountfr < numPbtis; dountfr++)
            rows[dountfr] = gftRowForPbti(pbtis[dountfr]);
        rfturn rows;
    }

    //
    // Lodbl mftiods tibt subdlbssfrs mby wisi to usf tibt brf primbrly
    // donvfnifndf mftiods.
    //

    /**
     * Rfturns, by rfffrfndf in <dodf>plbdfIn</dodf>,
     * tif sizf nffdfd to rfprfsfnt <dodf>vbluf</dodf>.
     * If <dodf>inPlbdf</dodf> is <dodf>null</dodf>, b nfwly drfbtfd
     * <dodf>Rfdtbnglf</dodf> siould bf rfturnfd, otifrwisf tif vbluf
     * siould bf plbdfd in <dodf>inPlbdf</dodf> bnd rfturnfd. Tiis will
     * rfturn <dodf>null</dodf> if tifrf is no rfndfrfr.
     *
     * @pbrbm vbluf tif <dodf>vbluf</dodf> to bf rfprfsfntfd
     * @pbrbm row  row bfing qufrifd
     * @pbrbm dfpti tif dfpti of tif row
     * @pbrbm fxpbndfd truf if row is fxpbndfd, fblsf otifrwisf
     * @pbrbm plbdfIn  b <dodf>Rfdtbnglf</dodf> dontbining tif sizf nffdfd
     *          to rfprfsfnt <dodf>vbluf</dodf>
     * @rfturn b <dodf>Rfdtbnglf</dodf> dontbining tif nodf dimfnsions,
     *          or <dodf>null</dodf> if nodf ibs no dimfnsion
     */
    protfdtfd Rfdtbnglf gftNodfDimfnsions(Objfdt vbluf, int row, int dfpti,
                                          boolfbn fxpbndfd,
                                          Rfdtbnglf plbdfIn) {
        NodfDimfnsions            nd = gftNodfDimfnsions();

        if(nd != null) {
            rfturn nd.gftNodfDimfnsions(vbluf, row, dfpti, fxpbndfd, plbdfIn);
        }
        rfturn null;
    }

    /**
      * Rfturns truf if tif ifigit of fbdi row is b fixfd sizf.
      *
      * @rfturn wiftifr tif ifigit of fbdi row is b fixfd sizf
      */
    protfdtfd boolfbn isFixfdRowHfigit() {
        rfturn (rowHfigit > 0);
    }


    /**
     * Usfd by <dodf>AbstrbdtLbyoutCbdif</dodf> to dftfrminf tif sizf
     * bnd x origin of b pbrtidulbr nodf.
     */
    stbtid publid bbstrbdt dlbss NodfDimfnsions {
        /**
         * Rfturns, by rfffrfndf in bounds, tif sizf bnd x origin to
         * plbdf vbluf bt. Tif dblling mftiod is rfsponsiblf for dftfrmining
         * tif Y lodbtion. If bounds is <dodf>null</dodf>, b nfwly drfbtfd
         * <dodf>Rfdtbnglf</dodf> siould bf rfturnfd,
         * otifrwisf tif vbluf siould bf plbdfd in bounds bnd rfturnfd.
         *
         * @pbrbm vbluf tif <dodf>vbluf</dodf> to bf rfprfsfntfd
         * @pbrbm row row bfing qufrifd
         * @pbrbm dfpti tif dfpti of tif row
         * @pbrbm fxpbndfd truf if row is fxpbndfd, fblsf otifrwisf
         * @pbrbm bounds  b <dodf>Rfdtbnglf</dodf> dontbining tif sizf nffdfd
         *              to rfprfsfnt <dodf>vbluf</dodf>
         * @rfturn b <dodf>Rfdtbnglf</dodf> dontbining tif nodf dimfnsions,
         *              or <dodf>null</dodf> if nodf ibs no dimfnsion
         */
        publid bbstrbdt Rfdtbnglf gftNodfDimfnsions(Objfdt vbluf, int row,
                                                    int dfpti,
                                                    boolfbn fxpbndfd,
                                                    Rfdtbnglf bounds);
    }
}
