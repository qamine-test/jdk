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


/**
 * <p>Dfsdribfs bn bttributf of bn opfn MBfbn.</p>
 *
 * <p>Tiis intfrfbdf dfdlbrfs tif sbmf mftiods bs tif dlbss {@link
 * jbvbx.mbnbgfmfnt.MBfbnAttributfInfo}.  A dlbss implfmfnting tiis
 * intfrfbdf (typidblly {@link OpfnMBfbnAttributfInfoSupport}) siould
 * fxtfnd {@link jbvbx.mbnbgfmfnt.MBfbnAttributfInfo}.</p>
 *
 *
 * @sindf 1.5
 */
publid intfrfbdf OpfnMBfbnAttributfInfo fxtfnds OpfnMBfbnPbrbmftfrInfo {


    // Rf-dfdlbrfs tif mftiods tibt brf in dlbss MBfbnAttributfInfo of JMX 1.0
    // (tifsf will bf rfmovfd wifn MBfbnAttributfInfo is mbdf b pbrfnt intfrfbdf of tiis intfrfbdf)

    /**
     * Rfturns <tt>truf</tt> if tif bttributf dfsdribfd by tiis <tt>OpfnMBfbnAttributfInfo</tt> instbndf is rfbdbblf,
     * <tt>fblsf</tt> otifrwisf.
     *
     * @rfturn truf if tif bttributf is rfbdbblf.
     */
    publid boolfbn isRfbdbblf() ;

    /**
     * Rfturns <tt>truf</tt> if tif bttributf dfsdribfd by tiis <tt>OpfnMBfbnAttributfInfo</tt> instbndf is writbblf,
     * <tt>fblsf</tt> otifrwisf.
     *
     * @rfturn truf if tif bttributf is writbblf.
     */
    publid boolfbn isWritbblf() ;

    /**
     * Rfturns <tt>truf</tt> if tif bttributf dfsdribfd by tiis <tt>OpfnMBfbnAttributfInfo</tt> instbndf
     * is bddfssfd tirougi b <tt>is<i>XXX</i></tt> gfttfr (bpplifs only to <tt>boolfbn</tt> bnd <tt>Boolfbn</tt> vblufs),
     * <tt>fblsf</tt> otifrwisf.
     *
     * @rfturn truf if tif bttributf is bddfssfd tirougi <tt>is<i>XXX</i></tt>.
     */
    publid boolfbn isIs() ;


    // dommodity mftiods
    //

    /**
     * Compbrfs tif spfdififd <vbr>obj</vbr> pbrbmftfr witi tiis <dodf>OpfnMBfbnAttributfInfo</dodf> instbndf for fqublity.
     * <p>
     * Rfturns <tt>truf</tt> if bnd only if bll of tif following stbtfmfnts brf truf:
     * <ul>
     * <li><vbr>obj</vbr> is non null,</li>
     * <li><vbr>obj</vbr> blso implfmfnts tif <dodf>OpfnMBfbnAttributfInfo</dodf> intfrfbdf,</li>
     * <li>tifir nbmfs brf fqubl</li>
     * <li>tifir opfn typfs brf fqubl</li>
     * <li>tifir bddfss propfrtifs (isRfbdbblf, isWritbblf bnd isIs) brf fqubl</li>
     * <li>tifir dffbult, min, mbx bnd lfgbl vblufs brf fqubl.</li>
     * </ul>
     * Tiis fnsurfs tibt tiis <tt>fqubls</tt> mftiod works propfrly for <vbr>obj</vbr> pbrbmftfrs wiidi brf
     * difffrfnt implfmfntbtions of tif <dodf>OpfnMBfbnAttributfInfo</dodf> intfrfbdf.
     * <br>&nbsp;
     * @pbrbm  obj  tif objfdt to bf dompbrfd for fqublity witi tiis <dodf>OpfnMBfbnAttributfInfo</dodf> instbndf;
     *
     * @rfturn  <dodf>truf</dodf> if tif spfdififd objfdt is fqubl to tiis <dodf>OpfnMBfbnAttributfInfo</dodf> instbndf.
     */
    publid boolfbn fqubls(Objfdt obj);

    /**
     * Rfturns tif ibsi dodf vbluf for tiis <dodf>OpfnMBfbnAttributfInfo</dodf> instbndf.
     * <p>
     * Tif ibsi dodf of bn <dodf>OpfnMBfbnAttributfInfo</dodf> instbndf is tif sum of tif ibsi dodfs
     * of bll flfmfnts of informbtion usfd in <dodf>fqubls</dodf> dompbrisons
     * (if: its nbmf, its <i>opfn typf</i>, bnd its dffbult, min, mbx bnd lfgbl vblufs).
     * <p>
     * Tiis fnsurfs tibt <dodf> t1.fqubls(t2) </dodf> implifs tibt <dodf> t1.ibsiCodf()==t2.ibsiCodf() </dodf>
     * for bny two <dodf>OpfnMBfbnAttributfInfo</dodf> instbndfs <dodf>t1</dodf> bnd <dodf>t2</dodf>,
     * bs rfquirfd by tif gfnfrbl dontrbdt of tif mftiod
     * {@link Objfdt#ibsiCodf() Objfdt.ibsiCodf()}.
     *
     * @rfturn  tif ibsi dodf vbluf for tiis <dodf>OpfnMBfbnAttributfInfo</dodf> instbndf
     */
    publid int ibsiCodf();

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>OpfnMBfbnAttributfInfo</dodf> instbndf.
     * <p>
     * Tif string rfprfsfntbtion donsists of tif nbmf of tiis dlbss (if <dodf>jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnMBfbnAttributfInfo</dodf>),
     * tif string rfprfsfntbtion of tif nbmf bnd opfn typf of tif dfsdribfd bttributf,
     * bnd tif string rfprfsfntbtion of its dffbult, min, mbx bnd lfgbl vblufs.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>OpfnMBfbnAttributfInfo</dodf> instbndf
     */
    publid String toString();


    // mftiods spfdifid to opfn MBfbns brf inifritfd from
    // OpfnMBfbnPbrbmftfrInfo
    //

}
