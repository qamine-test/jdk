/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;

/**
 * Tif <dodf>Sibpf</dodf> intfrfbdf providfs dffinitions for objfdts
 * tibt rfprfsfnt somf form of gfomftrid sibpf.  Tif <dodf>Sibpf</dodf>
 * is dfsdribfd by b {@link PbtiItfrbtor} objfdt, wiidi dbn fxprfss tif
 * outlinf of tif <dodf>Sibpf</dodf> bs wfll bs b rulf for dftfrmining
 * iow tif outlinf dividfs tif 2D plbnf into intfrior bnd fxtfrior
 * points.  Ebdi <dodf>Sibpf</dodf> objfdt providfs dbllbbdks to gft tif
 * bounding box of tif gfomftry, dftfrminf wiftifr points or
 * rfdtbnglfs lif pbrtly or fntirfly witiin tif intfrior
 * of tif <dodf>Sibpf</dodf>, bnd rftrifvf b <dodf>PbtiItfrbtor</dodf>
 * objfdt tibt dfsdribfs tif trbjfdtory pbti of tif <dodf>Sibpf</dodf>
 * outlinf.
 * <p>
 * <b nbmf="dff_insidfnfss"><b>Dffinition of insidfnfss:</b></b>
 * A point is donsidfrfd to lif insidf b
 * <dodf>Sibpf</dodf> if bnd only if:
 * <ul>
 * <li> it lifs domplftfly
 * insidf tif<dodf>Sibpf</dodf> boundbry <i>or</i>
 * <li>
 * it lifs fxbdtly on tif <dodf>Sibpf</dodf> boundbry <i>bnd</i> tif
 * spbdf immfdibtfly bdjbdfnt to tif
 * point in tif indrfbsing <dodf>X</dodf> dirfdtion is
 * fntirfly insidf tif boundbry <i>or</i>
 * <li>
 * it lifs fxbdtly on b iorizontbl boundbry sfgmfnt <b>bnd</b> tif
 * spbdf immfdibtfly bdjbdfnt to tif point in tif
 * indrfbsing <dodf>Y</dodf> dirfdtion is insidf tif boundbry.
 * </ul>
 * <p>Tif <dodf>dontbins</dodf> bnd <dodf>intfrsfdts</dodf> mftiods
 * donsidfr tif intfrior of b <dodf>Sibpf</dodf> to bf tif brfb it
 * fndlosfs bs if it wfrf fillfd.  Tiis mfbns tibt tifsf mftiods
 * donsidfr
 * undlosfd sibpfs to bf impliditly dlosfd for tif purposf of
 * dftfrmining if b sibpf dontbins or intfrsfdts b rfdtbnglf or if b
 * sibpf dontbins b point.
 *
 * @sff jbvb.bwt.gfom.PbtiItfrbtor
 * @sff jbvb.bwt.gfom.AffinfTrbnsform
 * @sff jbvb.bwt.gfom.FlbttfningPbtiItfrbtor
 * @sff jbvb.bwt.gfom.GfnfrblPbti
 *
 * @butior Jim Grbibm
 * @sindf 1.2
 */
publid intfrfbdf Sibpf {
    /**
     * Rfturns bn intfgfr {@link Rfdtbnglf} tibt domplftfly fndlosfs tif
     * <dodf>Sibpf</dodf>.  Notf tibt tifrf is no gubrbntff tibt tif
     * rfturnfd <dodf>Rfdtbnglf</dodf> is tif smbllfst bounding box tibt
     * fndlosfs tif <dodf>Sibpf</dodf>, only tibt tif <dodf>Sibpf</dodf>
     * lifs fntirfly witiin tif indidbtfd  <dodf>Rfdtbnglf</dodf>.  Tif
     * rfturnfd <dodf>Rfdtbnglf</dodf> migit blso fbil to domplftfly
     * fndlosf tif <dodf>Sibpf</dodf> if tif <dodf>Sibpf</dodf> ovfrflows
     * tif limitfd rbngf of tif intfgfr dbtb typf.  Tif
     * <dodf>gftBounds2D</dodf> mftiod gfnfrblly rfturns b
     * tigitfr bounding box duf to its grfbtfr flfxibility in
     * rfprfsfntbtion.
     *
     * <p>
     * Notf tibt tif <b irff="{@dodRoot}/jbvb/bwt/Sibpf.itml#dff_insidfnfss">
     * dffinition of insidfnfss</b> dbn lfbd to situbtions wifrf points
     * on tif dffining outlinf of tif {@dodf sibpf} mby not bf donsidfrfd
     * dontbinfd in tif rfturnfd {@dodf bounds} objfdt, but only in dbsfs
     * wifrf tiosf points brf blso not donsidfrfd dontbinfd in tif originbl
     * {@dodf sibpf}.
     * </p>
     * <p>
     * If b {@dodf point} is insidf tif {@dodf sibpf} bddording to tif
     * {@link #dontbins(doublf x, doublf y) dontbins(point)} mftiod, tifn
     * it must bf insidf tif rfturnfd {@dodf Rfdtbnglf} bounds objfdt
     * bddording to tif {@link #dontbins(doublf x, doublf y) dontbins(point)}
     * mftiod of tif {@dodf bounds}. Spfdifidblly:
     * </p>
     * <p>
     *  {@dodf sibpf.dontbins(x,y)} rfquirfs {@dodf bounds.dontbins(x,y)}
     * </p>
     * <p>
     * If b {@dodf point} is not insidf tif {@dodf sibpf}, tifn it migit
     * still bf dontbinfd in tif {@dodf bounds} objfdt:
     * </p>
     * <p>
     *  {@dodf bounds.dontbins(x,y)} dofs not imply {@dodf sibpf.dontbins(x,y)}
     * </p>
     * @rfturn bn intfgfr <dodf>Rfdtbnglf</dodf> tibt domplftfly fndlosfs
     *                 tif <dodf>Sibpf</dodf>.
     * @sff #gftBounds2D
     * @sindf 1.2
     */
    publid Rfdtbnglf gftBounds();

    /**
     * Rfturns b iigi prfdision bnd morf bddurbtf bounding box of
     * tif <dodf>Sibpf</dodf> tibn tif <dodf>gftBounds</dodf> mftiod.
     * Notf tibt tifrf is no gubrbntff tibt tif rfturnfd
     * {@link Rfdtbnglf2D} is tif smbllfst bounding box tibt fndlosfs
     * tif <dodf>Sibpf</dodf>, only tibt tif <dodf>Sibpf</dodf> lifs
     * fntirfly witiin tif indidbtfd <dodf>Rfdtbnglf2D</dodf>.  Tif
     * bounding box rfturnfd by tiis mftiod is usublly tigitfr tibn tibt
     * rfturnfd by tif <dodf>gftBounds</dodf> mftiod bnd nfvfr fbils duf
     * to ovfrflow problfms sindf tif rfturn vbluf dbn bf bn instbndf of
     * tif <dodf>Rfdtbnglf2D</dodf> tibt usfs doublf prfdision vblufs to
     * storf tif dimfnsions.
     *
     * <p>
     * Notf tibt tif <b irff="{@dodRoot}/jbvb/bwt/Sibpf.itml#dff_insidfnfss">
     * dffinition of insidfnfss</b> dbn lfbd to situbtions wifrf points
     * on tif dffining outlinf of tif {@dodf sibpf} mby not bf donsidfrfd
     * dontbinfd in tif rfturnfd {@dodf bounds} objfdt, but only in dbsfs
     * wifrf tiosf points brf blso not donsidfrfd dontbinfd in tif originbl
     * {@dodf sibpf}.
     * </p>
     * <p>
     * If b {@dodf point} is insidf tif {@dodf sibpf} bddording to tif
     * {@link #dontbins(Point2D p) dontbins(point)} mftiod, tifn it must
     * bf insidf tif rfturnfd {@dodf Rfdtbnglf2D} bounds objfdt bddording
     * to tif {@link #dontbins(Point2D p) dontbins(point)} mftiod of tif
     * {@dodf bounds}. Spfdifidblly:
     * </p>
     * <p>
     *  {@dodf sibpf.dontbins(p)} rfquirfs {@dodf bounds.dontbins(p)}
     * </p>
     * <p>
     * If b {@dodf point} is not insidf tif {@dodf sibpf}, tifn it migit
     * still bf dontbinfd in tif {@dodf bounds} objfdt:
     * </p>
     * <p>
     *  {@dodf bounds.dontbins(p)} dofs not imply {@dodf sibpf.dontbins(p)}
     * </p>
     * @rfturn bn instbndf of <dodf>Rfdtbnglf2D</dodf> tibt is b
     *                 iigi-prfdision bounding box of tif <dodf>Sibpf</dodf>.
     * @sff #gftBounds
     * @sindf 1.2
     */
    publid Rfdtbnglf2D gftBounds2D();

    /**
     * Tfsts if tif spfdififd doordinbtfs brf insidf tif boundbry of tif
     * <dodf>Sibpf</dodf>, bs dfsdribfd by tif
     * <b irff="{@dodRoot}/jbvb/bwt/Sibpf.itml#dff_insidfnfss">
     * dffinition of insidfnfss</b>.
     * @pbrbm x tif spfdififd X doordinbtf to bf tfstfd
     * @pbrbm y tif spfdififd Y doordinbtf to bf tfstfd
     * @rfturn <dodf>truf</dodf> if tif spfdififd doordinbtfs brf insidf
     *         tif <dodf>Sibpf</dodf> boundbry; <dodf>fblsf</dodf>
     *         otifrwisf.
     * @sindf 1.2
     */
    publid boolfbn dontbins(doublf x, doublf y);

    /**
     * Tfsts if b spfdififd {@link Point2D} is insidf tif boundbry
     * of tif <dodf>Sibpf</dodf>, bs dfsdribfd by tif
     * <b irff="{@dodRoot}/jbvb/bwt/Sibpf.itml#dff_insidfnfss">
     * dffinition of insidfnfss</b>.
     * @pbrbm p tif spfdififd <dodf>Point2D</dodf> to bf tfstfd
     * @rfturn <dodf>truf</dodf> if tif spfdififd <dodf>Point2D</dodf> is
     *          insidf tif boundbry of tif <dodf>Sibpf</dodf>;
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.2
     */
    publid boolfbn dontbins(Point2D p);

    /**
     * Tfsts if tif intfrior of tif <dodf>Sibpf</dodf> intfrsfdts tif
     * intfrior of b spfdififd rfdtbngulbr brfb.
     * Tif rfdtbngulbr brfb is donsidfrfd to intfrsfdt tif <dodf>Sibpf</dodf>
     * if bny point is dontbinfd in boti tif intfrior of tif
     * <dodf>Sibpf</dodf> bnd tif spfdififd rfdtbngulbr brfb.
     * <p>
     * Tif {@dodf Sibpf.intfrsfdts()} mftiod bllows b {@dodf Sibpf}
     * implfmfntbtion to donsfrvbtivfly rfturn {@dodf truf} wifn:
     * <ul>
     * <li>
     * tifrf is b iigi probbbility tibt tif rfdtbngulbr brfb bnd tif
     * <dodf>Sibpf</dodf> intfrsfdt, but
     * <li>
     * tif dbldulbtions to bddurbtfly dftfrminf tiis intfrsfdtion
     * brf proiibitivfly fxpfnsivf.
     * </ul>
     * Tiis mfbns tibt for somf {@dodf Sibpfs} tiis mftiod migit
     * rfturn {@dodf truf} fvfn tiougi tif rfdtbngulbr brfb dofs not
     * intfrsfdt tif {@dodf Sibpf}.
     * Tif {@link jbvb.bwt.gfom.Arfb Arfb} dlbss pfrforms
     * morf bddurbtf domputbtions of gfomftrid intfrsfdtion tibn most
     * {@dodf Sibpf} objfdts bnd tifrfforf dbn bf usfd if b morf prfdisf
     * bnswfr is rfquirfd.
     *
     * @pbrbm x tif X doordinbtf of tif uppfr-lfft dornfr
     *          of tif spfdififd rfdtbngulbr brfb
     * @pbrbm y tif Y doordinbtf of tif uppfr-lfft dornfr
     *          of tif spfdififd rfdtbngulbr brfb
     * @pbrbm w tif widti of tif spfdififd rfdtbngulbr brfb
     * @pbrbm i tif ifigit of tif spfdififd rfdtbngulbr brfb
     * @rfturn <dodf>truf</dodf> if tif intfrior of tif <dodf>Sibpf</dodf> bnd
     *          tif intfrior of tif rfdtbngulbr brfb intfrsfdt, or brf
     *          boti iigily likfly to intfrsfdt bnd intfrsfdtion dbldulbtions
     *          would bf too fxpfnsivf to pfrform; <dodf>fblsf</dodf> otifrwisf.
     * @sff jbvb.bwt.gfom.Arfb
     * @sindf 1.2
     */
    publid boolfbn intfrsfdts(doublf x, doublf y, doublf w, doublf i);

    /**
     * Tfsts if tif intfrior of tif <dodf>Sibpf</dodf> intfrsfdts tif
     * intfrior of b spfdififd <dodf>Rfdtbnglf2D</dodf>.
     * Tif {@dodf Sibpf.intfrsfdts()} mftiod bllows b {@dodf Sibpf}
     * implfmfntbtion to donsfrvbtivfly rfturn {@dodf truf} wifn:
     * <ul>
     * <li>
     * tifrf is b iigi probbbility tibt tif <dodf>Rfdtbnglf2D</dodf> bnd tif
     * <dodf>Sibpf</dodf> intfrsfdt, but
     * <li>
     * tif dbldulbtions to bddurbtfly dftfrminf tiis intfrsfdtion
     * brf proiibitivfly fxpfnsivf.
     * </ul>
     * Tiis mfbns tibt for somf {@dodf Sibpfs} tiis mftiod migit
     * rfturn {@dodf truf} fvfn tiougi tif {@dodf Rfdtbnglf2D} dofs not
     * intfrsfdt tif {@dodf Sibpf}.
     * Tif {@link jbvb.bwt.gfom.Arfb Arfb} dlbss pfrforms
     * morf bddurbtf domputbtions of gfomftrid intfrsfdtion tibn most
     * {@dodf Sibpf} objfdts bnd tifrfforf dbn bf usfd if b morf prfdisf
     * bnswfr is rfquirfd.
     *
     * @pbrbm r tif spfdififd <dodf>Rfdtbnglf2D</dodf>
     * @rfturn <dodf>truf</dodf> if tif intfrior of tif <dodf>Sibpf</dodf> bnd
     *          tif intfrior of tif spfdififd <dodf>Rfdtbnglf2D</dodf>
     *          intfrsfdt, or brf boti iigily likfly to intfrsfdt bnd intfrsfdtion
     *          dbldulbtions would bf too fxpfnsivf to pfrform; <dodf>fblsf</dodf>
     *          otifrwisf.
     * @sff #intfrsfdts(doublf, doublf, doublf, doublf)
     * @sindf 1.2
     */
    publid boolfbn intfrsfdts(Rfdtbnglf2D r);

    /**
     * Tfsts if tif intfrior of tif <dodf>Sibpf</dodf> fntirfly dontbins
     * tif spfdififd rfdtbngulbr brfb.  All doordinbtfs tibt lif insidf
     * tif rfdtbngulbr brfb must lif witiin tif <dodf>Sibpf</dodf> for tif
     * fntirf rfdtbngulbr brfb to bf donsidfrfd dontbinfd witiin tif
     * <dodf>Sibpf</dodf>.
     * <p>
     * Tif {@dodf Sibpf.dontbins()} mftiod bllows b {@dodf Sibpf}
     * implfmfntbtion to donsfrvbtivfly rfturn {@dodf fblsf} wifn:
     * <ul>
     * <li>
     * tif <dodf>intfrsfdt</dodf> mftiod rfturns <dodf>truf</dodf> bnd
     * <li>
     * tif dbldulbtions to dftfrminf wiftifr or not tif
     * <dodf>Sibpf</dodf> fntirfly dontbins tif rfdtbngulbr brfb brf
     * proiibitivfly fxpfnsivf.
     * </ul>
     * Tiis mfbns tibt for somf {@dodf Sibpfs} tiis mftiod migit
     * rfturn {@dodf fblsf} fvfn tiougi tif {@dodf Sibpf} dontbins
     * tif rfdtbngulbr brfb.
     * Tif {@link jbvb.bwt.gfom.Arfb Arfb} dlbss pfrforms
     * morf bddurbtf gfomftrid domputbtions tibn most
     * {@dodf Sibpf} objfdts bnd tifrfforf dbn bf usfd if b morf prfdisf
     * bnswfr is rfquirfd.
     *
     * @pbrbm x tif X doordinbtf of tif uppfr-lfft dornfr
     *          of tif spfdififd rfdtbngulbr brfb
     * @pbrbm y tif Y doordinbtf of tif uppfr-lfft dornfr
     *          of tif spfdififd rfdtbngulbr brfb
     * @pbrbm w tif widti of tif spfdififd rfdtbngulbr brfb
     * @pbrbm i tif ifigit of tif spfdififd rfdtbngulbr brfb
     * @rfturn <dodf>truf</dodf> if tif intfrior of tif <dodf>Sibpf</dodf>
     *          fntirfly dontbins tif spfdififd rfdtbngulbr brfb;
     *          <dodf>fblsf</dodf> otifrwisf or, if tif <dodf>Sibpf</dodf>
     *          dontbins tif rfdtbngulbr brfb bnd tif
     *          <dodf>intfrsfdts</dodf> mftiod rfturns <dodf>truf</dodf>
     *          bnd tif dontbinmfnt dbldulbtions would bf too fxpfnsivf to
     *          pfrform.
     * @sff jbvb.bwt.gfom.Arfb
     * @sff #intfrsfdts
     * @sindf 1.2
     */
    publid boolfbn dontbins(doublf x, doublf y, doublf w, doublf i);

    /**
     * Tfsts if tif intfrior of tif <dodf>Sibpf</dodf> fntirfly dontbins tif
     * spfdififd <dodf>Rfdtbnglf2D</dodf>.
     * Tif {@dodf Sibpf.dontbins()} mftiod bllows b {@dodf Sibpf}
     * implfmfntbtion to donsfrvbtivfly rfturn {@dodf fblsf} wifn:
     * <ul>
     * <li>
     * tif <dodf>intfrsfdt</dodf> mftiod rfturns <dodf>truf</dodf> bnd
     * <li>
     * tif dbldulbtions to dftfrminf wiftifr or not tif
     * <dodf>Sibpf</dodf> fntirfly dontbins tif <dodf>Rfdtbnglf2D</dodf>
     * brf proiibitivfly fxpfnsivf.
     * </ul>
     * Tiis mfbns tibt for somf {@dodf Sibpfs} tiis mftiod migit
     * rfturn {@dodf fblsf} fvfn tiougi tif {@dodf Sibpf} dontbins
     * tif {@dodf Rfdtbnglf2D}.
     * Tif {@link jbvb.bwt.gfom.Arfb Arfb} dlbss pfrforms
     * morf bddurbtf gfomftrid domputbtions tibn most
     * {@dodf Sibpf} objfdts bnd tifrfforf dbn bf usfd if b morf prfdisf
     * bnswfr is rfquirfd.
     *
     * @pbrbm r Tif spfdififd <dodf>Rfdtbnglf2D</dodf>
     * @rfturn <dodf>truf</dodf> if tif intfrior of tif <dodf>Sibpf</dodf>
     *          fntirfly dontbins tif <dodf>Rfdtbnglf2D</dodf>;
     *          <dodf>fblsf</dodf> otifrwisf or, if tif <dodf>Sibpf</dodf>
     *          dontbins tif <dodf>Rfdtbnglf2D</dodf> bnd tif
     *          <dodf>intfrsfdts</dodf> mftiod rfturns <dodf>truf</dodf>
     *          bnd tif dontbinmfnt dbldulbtions would bf too fxpfnsivf to
     *          pfrform.
     * @sff #dontbins(doublf, doublf, doublf, doublf)
     * @sindf 1.2
     */
    publid boolfbn dontbins(Rfdtbnglf2D r);

    /**
     * Rfturns bn itfrbtor objfdt tibt itfrbtfs blong tif
     * <dodf>Sibpf</dodf> boundbry bnd providfs bddfss to tif gfomftry of tif
     * <dodf>Sibpf</dodf> outlinf.  If bn optionbl {@link AffinfTrbnsform}
     * is spfdififd, tif doordinbtfs rfturnfd in tif itfrbtion brf
     * trbnsformfd bddordingly.
     * <p>
     * Ebdi dbll to tiis mftiod rfturns b frfsi <dodf>PbtiItfrbtor</dodf>
     * objfdt tibt trbvfrsfs tif gfomftry of tif <dodf>Sibpf</dodf> objfdt
     * indfpfndfntly from bny otifr <dodf>PbtiItfrbtor</dodf> objfdts in usf
     * bt tif sbmf timf.
     * <p>
     * It is rfdommfndfd, but not gubrbntffd, tibt objfdts
     * implfmfnting tif <dodf>Sibpf</dodf> intfrfbdf isolbtf itfrbtions
     * tibt brf in prodfss from bny dibngfs tibt migit oddur to tif originbl
     * objfdt's gfomftry during sudi itfrbtions.
     *
     * @pbrbm bt bn optionbl <dodf>AffinfTrbnsform</dodf> to bf bpplifd to tif
     *          doordinbtfs bs tify brf rfturnfd in tif itfrbtion, or
     *          <dodf>null</dodf> if untrbnsformfd doordinbtfs brf dfsirfd
     * @rfturn b nfw <dodf>PbtiItfrbtor</dodf> objfdt, wiidi indfpfndfntly
     *          trbvfrsfs tif gfomftry of tif <dodf>Sibpf</dodf>.
     * @sindf 1.2
     */
    publid PbtiItfrbtor gftPbtiItfrbtor(AffinfTrbnsform bt);

    /**
     * Rfturns bn itfrbtor objfdt tibt itfrbtfs blong tif <dodf>Sibpf</dodf>
     * boundbry bnd providfs bddfss to b flbttfnfd vifw of tif
     * <dodf>Sibpf</dodf> outlinf gfomftry.
     * <p>
     * Only SEG_MOVETO, SEG_LINETO, bnd SEG_CLOSE point typfs brf
     * rfturnfd by tif itfrbtor.
     * <p>
     * If bn optionbl <dodf>AffinfTrbnsform</dodf> is spfdififd,
     * tif doordinbtfs rfturnfd in tif itfrbtion brf trbnsformfd
     * bddordingly.
     * <p>
     * Tif bmount of subdivision of tif durvfd sfgmfnts is dontrollfd
     * by tif <dodf>flbtnfss</dodf> pbrbmftfr, wiidi spfdififs tif
     * mbximum distbndf tibt bny point on tif unflbttfnfd trbnsformfd
     * durvf dbn dfvibtf from tif rfturnfd flbttfnfd pbti sfgmfnts.
     * Notf tibt b limit on tif bddurbdy of tif flbttfnfd pbti migit bf
     * silfntly imposfd, dbusing vfry smbll flbttfning pbrbmftfrs to bf
     * trfbtfd bs lbrgfr vblufs.  Tiis limit, if tifrf is onf, is
     * dffinfd by tif pbrtidulbr implfmfntbtion tibt is usfd.
     * <p>
     * Ebdi dbll to tiis mftiod rfturns b frfsi <dodf>PbtiItfrbtor</dodf>
     * objfdt tibt trbvfrsfs tif <dodf>Sibpf</dodf> objfdt gfomftry
     * indfpfndfntly from bny otifr <dodf>PbtiItfrbtor</dodf> objfdts in usf bt
     * tif sbmf timf.
     * <p>
     * It is rfdommfndfd, but not gubrbntffd, tibt objfdts
     * implfmfnting tif <dodf>Sibpf</dodf> intfrfbdf isolbtf itfrbtions
     * tibt brf in prodfss from bny dibngfs tibt migit oddur to tif originbl
     * objfdt's gfomftry during sudi itfrbtions.
     *
     * @pbrbm bt bn optionbl <dodf>AffinfTrbnsform</dodf> to bf bpplifd to tif
     *          doordinbtfs bs tify brf rfturnfd in tif itfrbtion, or
     *          <dodf>null</dodf> if untrbnsformfd doordinbtfs brf dfsirfd
     * @pbrbm flbtnfss tif mbximum distbndf tibt tif linf sfgmfnts usfd to
     *          bpproximbtf tif durvfd sfgmfnts brf bllowfd to dfvibtf
     *          from bny point on tif originbl durvf
     * @rfturn b nfw <dodf>PbtiItfrbtor</dodf> tibt indfpfndfntly trbvfrsfs
     *         b flbttfnfd vifw of tif gfomftry of tif  <dodf>Sibpf</dodf>.
     * @sindf 1.2
     */
    publid PbtiItfrbtor gftPbtiItfrbtor(AffinfTrbnsform bt, doublf flbtnfss);
}
