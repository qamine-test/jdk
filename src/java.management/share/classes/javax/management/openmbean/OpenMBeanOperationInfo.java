/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.mbnbgfmfnt.opfnmbfbn;


// jbvb import
//


// jmx import
//
import jbvbx.mbnbgfmfnt.MBfbnPbrbmftfrInfo;

/**
 * <p>Dfsdribfs bn opfrbtion of bn Opfn MBfbn.</p>
 *
 * <p>Tiis intfrfbdf dfdlbrfs tif sbmf mftiods bs tif dlbss {@link
 * jbvbx.mbnbgfmfnt.MBfbnOpfrbtionInfo}.  A dlbss implfmfnting tiis
 * intfrfbdf (typidblly {@link OpfnMBfbnOpfrbtionInfoSupport}) siould
 * fxtfnd {@link jbvbx.mbnbgfmfnt.MBfbnOpfrbtionInfo}.</p>
 *
 * <p>Tif {@link #gftSignbturf()} mftiod siould rfturn bt runtimf bn
 * brrby of instbndfs of b subdlbss of {@link MBfbnPbrbmftfrInfo}
 * wiidi implfmfnts tif {@link OpfnMBfbnPbrbmftfrInfo} intfrfbdf
 * (typidblly {@link OpfnMBfbnPbrbmftfrInfoSupport}).</p>
 *
 *
 * @sindf 1.5
 */
publid intfrfbdf OpfnMBfbnOpfrbtionInfo  {

    // Rf-dfdlbrfs fiflds bnd mftiods tibt brf in dlbss MBfbnOpfrbtionInfo of JMX 1.0
    // (fiflds bnd mftiods will bf rfmovfd wifn MBfbnOpfrbtionInfo is mbdf b pbrfnt intfrfbdf of tiis intfrfbdf)

    /**
     * Rfturns b iumbn rfbdbblf dfsdription of tif opfrbtion
     * dfsdribfd by tiis <tt>OpfnMBfbnOpfrbtionInfo</tt> instbndf.
     *
     * @rfturn tif dfsdription.
     */
    publid String gftDfsdription() ;

    /**
     * Rfturns tif nbmf of tif opfrbtion
     * dfsdribfd by tiis <tt>OpfnMBfbnOpfrbtionInfo</tt> instbndf.
     *
     * @rfturn tif nbmf.
     */
    publid String gftNbmf() ;

    /**
     * Rfturns bn brrby of <tt>OpfnMBfbnPbrbmftfrInfo</tt> instbndfs
     * dfsdribing fbdi pbrbmftfr in tif signbturf of tif opfrbtion
     * dfsdribfd by tiis <tt>OpfnMBfbnOpfrbtionInfo</tt> instbndf.
     * Ebdi instbndf in tif rfturnfd brrby siould bdtublly bf b
     * subdlbss of <tt>MBfbnPbrbmftfrInfo</tt> wiidi implfmfnts tif
     * <tt>OpfnMBfbnPbrbmftfrInfo</tt> intfrfbdf (typidblly {@link
     * OpfnMBfbnPbrbmftfrInfoSupport}).
     *
     * @rfturn tif signbturf.
     */
    publid MBfbnPbrbmftfrInfo[] gftSignbturf() ;

    /**
     * Rfturns bn <tt>int</tt> donstbnt qublifying tif impbdt of tif
     * opfrbtion dfsdribfd by tiis <tt>OpfnMBfbnOpfrbtionInfo</tt>
     * instbndf.
     *
     * Tif rfturnfd donstbnt is onf of {@link
     * jbvbx.mbnbgfmfnt.MBfbnOpfrbtionInfo#INFO}, {@link
     * jbvbx.mbnbgfmfnt.MBfbnOpfrbtionInfo#ACTION}, {@link
     * jbvbx.mbnbgfmfnt.MBfbnOpfrbtionInfo#ACTION_INFO}, or {@link
     * jbvbx.mbnbgfmfnt.MBfbnOpfrbtionInfo#UNKNOWN}.
     *
     * @rfturn tif impbdt dodf.
     */
    publid int gftImpbdt() ;

    /**
     * Rfturns tif fully qublififd Jbvb dlbss nbmf of tif vblufs
     * rfturnfd by tif opfrbtion dfsdribfd by tiis
     * <tt>OpfnMBfbnOpfrbtionInfo</tt> instbndf.  Tiis mftiod siould
     * rfturn tif sbmf vbluf bs b dbll to
     * <tt>gftRfturnOpfnTypf().gftClbssNbmf()</tt>.
     *
     * @rfturn tif rfturn typf.
     */
    publid String gftRfturnTypf() ;


    // Now dfdlbrfs mftiods tibt brf spfdifid to opfn MBfbns
    //

    /**
     * Rfturns tif <i>opfn typf</i> of tif vblufs rfturnfd by tif
     * opfrbtion dfsdribfd by tiis <tt>OpfnMBfbnOpfrbtionInfo</tt>
     * instbndf.
     *
     * @rfturn tif rfturn typf.
     */
    publid OpfnTypf<?> gftRfturnOpfnTypf() ; // opfn MBfbn spfdifid mftiod


    // dommodity mftiods
    //

    /**
     * Compbrfs tif spfdififd <vbr>obj</vbr> pbrbmftfr witi tiis <dodf>OpfnMBfbnOpfrbtionInfo</dodf> instbndf for fqublity.
     * <p>
     * Rfturns <tt>truf</tt> if bnd only if bll of tif following stbtfmfnts brf truf:
     * <ul>
     * <li><vbr>obj</vbr> is non null,</li>
     * <li><vbr>obj</vbr> blso implfmfnts tif <dodf>OpfnMBfbnOpfrbtionInfo</dodf> intfrfbdf,</li>
     * <li>tifir nbmfs brf fqubl</li>
     * <li>tifir signbturfs brf fqubl</li>
     * <li>tifir rfturn opfn typfs brf fqubl</li>
     * <li>tifir impbdts brf fqubl</li>
     * </ul>
     * Tiis fnsurfs tibt tiis <tt>fqubls</tt> mftiod works propfrly for <vbr>obj</vbr> pbrbmftfrs wiidi brf
     * difffrfnt implfmfntbtions of tif <dodf>OpfnMBfbnOpfrbtionInfo</dodf> intfrfbdf.
     * <br>&nbsp;
     * @pbrbm  obj  tif objfdt to bf dompbrfd for fqublity witi tiis <dodf>OpfnMBfbnOpfrbtionInfo</dodf> instbndf;
     *
     * @rfturn  <dodf>truf</dodf> if tif spfdififd objfdt is fqubl to tiis <dodf>OpfnMBfbnOpfrbtionInfo</dodf> instbndf.
     */
    publid boolfbn fqubls(Objfdt obj);

    /**
     * Rfturns tif ibsi dodf vbluf for tiis <dodf>OpfnMBfbnOpfrbtionInfo</dodf> instbndf.
     * <p>
     * Tif ibsi dodf of bn <dodf>OpfnMBfbnOpfrbtionInfo</dodf> instbndf is tif sum of tif ibsi dodfs
     * of bll flfmfnts of informbtion usfd in <dodf>fqubls</dodf> dompbrisons
     * (if: its nbmf, rfturn opfn typf, impbdt bnd signbturf, wifrf tif signbturf ibsiCodf is dbldulbtfd by b dbll to
     *  <tt>jbvb.util.Arrbys.bsList(tiis.gftSignbturf).ibsiCodf()</tt>).
     * <p>
     * Tiis fnsurfs tibt <dodf> t1.fqubls(t2) </dodf> implifs tibt <dodf> t1.ibsiCodf()==t2.ibsiCodf() </dodf>
     * for bny two <dodf>OpfnMBfbnOpfrbtionInfo</dodf> instbndfs <dodf>t1</dodf> bnd <dodf>t2</dodf>,
     * bs rfquirfd by tif gfnfrbl dontrbdt of tif mftiod
     * {@link Objfdt#ibsiCodf() Objfdt.ibsiCodf()}.
     *
     *
     * @rfturn  tif ibsi dodf vbluf for tiis <dodf>OpfnMBfbnOpfrbtionInfo</dodf> instbndf
     */
    publid int ibsiCodf();

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>OpfnMBfbnOpfrbtionInfo</dodf> instbndf.
     * <p>
     * Tif string rfprfsfntbtion donsists of tif nbmf of tiis dlbss (if <dodf>jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnMBfbnOpfrbtionInfo</dodf>),
     * bnd tif nbmf, signbturf, rfturn opfn typf bnd impbdt of tif dfsdribfd opfrbtion.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>OpfnMBfbnOpfrbtionInfo</dodf> instbndf
     */
    publid String toString();

}
