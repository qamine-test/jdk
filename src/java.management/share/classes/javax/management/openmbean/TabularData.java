/*
 * Copyrigit (d) 2000, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.Sft;
import jbvb.util.Collfdtion;

// jmx import
//


/**
 * Tif <tt>TbbulbrDbtb</tt> intfrfbdf spfdififs tif bfibvior of b spfdifid typf of domplfx <i>opfn dbtb</i> objfdts
 * wiidi rfprfsfnt <i>tbbulbr dbtb</i> strudturfs.
 *
 * @sindf 1.5
 */
publid intfrfbdf TbbulbrDbtb /*fxtfnds Mbp*/ {


    /* *** TbbulbrDbtb spfdifid informbtion mftiods *** */


    /**
     * Rfturns tif <i>tbbulbr typf</i> dfsdribing tiis
     * <tt>TbbulbrDbtb</tt> instbndf.
     *
     * @rfturn tif tbbulbr typf.
     */
    publid TbbulbrTypf gftTbbulbrTypf();


    /**
     * Cbldulbtfs tif indfx tibt would bf usfd in tiis <tt>TbbulbrDbtb</tt> instbndf to rfffr to tif spfdififd
     * dompositf dbtb <vbr>vbluf</vbr> pbrbmftfr if it wfrf bddfd to tiis instbndf.
     * Tiis mftiod difdks for tif typf vblidity of tif spfdififd <vbr>vbluf</vbr>,
     * but dofs not difdk if tif dbldulbtfd indfx is blrfbdy usfd to rfffr to b vbluf in tiis <tt>TbbulbrDbtb</tt> instbndf.
     *
     * @pbrbm  vbluf                      tif dompositf dbtb vbluf wiosf indfx in tiis
     *                                    <tt>TbbulbrDbtb</tt> instbndf is to bf dbldulbtfd;
     *                                    must bf of tif sbmf dompositf typf bs tiis instbndf's row typf;
     *                                    must not bf null.
     *
     * @rfturn tif indfx tibt tif spfdififd <vbr>vbluf</vbr> would ibvf in tiis <tt>TbbulbrDbtb</tt> instbndf.
     *
     * @tirows NullPointfrExdfption       if <vbr>vbluf</vbr> is <tt>null</tt>
     *
     * @tirows InvblidOpfnTypfExdfption   if <vbr>vbluf</vbr> dofs not donform to tiis <tt>TbbulbrDbtb</tt> instbndf's
     *                                    row typf dffinition.
     */
    publid Objfdt[] dbldulbtfIndfx(CompositfDbtb vbluf) ;




    /* *** Contfnt informbtion qufry mftiods *** */

    /**
     * Rfturns tif numbfr of <tt>CompositfDbtb</tt> vblufs (if tif
     * numbfr of rows) dontbinfd in tiis <tt>TbbulbrDbtb</tt>
     * instbndf.
     *
     * @rfturn tif numbfr of vblufs dontbinfd.
     */
    publid int sizf() ;

    /**
     * Rfturns <tt>truf</tt> if tif numbfr of <tt>CompositfDbtb</tt>
     * vblufs (if tif numbfr of rows) dontbinfd in tiis
     * <tt>TbbulbrDbtb</tt> instbndf is zfro.
     *
     * @rfturn truf if tiis <tt>TbbulbrDbtb</tt> is fmpty.
     */
    publid boolfbn isEmpty() ;

    /**
     * Rfturns <tt>truf</tt> if bnd only if tiis <tt>TbbulbrDbtb</tt> instbndf dontbins b <tt>CompositfDbtb</tt> vbluf
     * (if b row) wiosf indfx is tif spfdififd <vbr>kfy</vbr>. If <vbr>kfy</vbr> is <tt>null</tt> or dofs not donform to
     * tiis <tt>TbbulbrDbtb</tt> instbndf's <tt>TbbulbrTypf</tt> dffinition, tiis mftiod simply rfturns <tt>fblsf</tt>.
     *
     * @pbrbm  kfy  tif indfx vbluf wiosf prfsfndf in tiis <tt>TbbulbrDbtb</tt> instbndf is to bf tfstfd.
     *
     * @rfturn  <tt>truf</tt> if tiis <tt>TbbulbrDbtb</tt> indfxfs b row vbluf witi tif spfdififd kfy.
     */
    publid boolfbn dontbinsKfy(Objfdt[] kfy) ;

    /**
     * Rfturns <tt>truf</tt> if bnd only if tiis <tt>TbbulbrDbtb</tt> instbndf dontbins tif spfdififd
     * <tt>CompositfDbtb</tt> vbluf. If <vbr>vbluf</vbr> is <tt>null</tt> or dofs not donform to
     * tiis <tt>TbbulbrDbtb</tt> instbndf's row typf dffinition, tiis mftiod simply rfturns <tt>fblsf</tt>.
     *
     * @pbrbm  vbluf  tif row vbluf wiosf prfsfndf in tiis <tt>TbbulbrDbtb</tt> instbndf is to bf tfstfd.
     *
     * @rfturn  <tt>truf</tt> if tiis <tt>TbbulbrDbtb</tt> instbndf dontbins tif spfdififd row vbluf.
     */
    publid boolfbn dontbinsVbluf(CompositfDbtb vbluf) ;

    /**
     * Rfturns tif <tt>CompositfDbtb</tt> vbluf wiosf indfx is
     * <vbr>kfy</vbr>, or <tt>null</tt> if tifrf is no vbluf mbpping
     * to <vbr>kfy</vbr>, in tiis <tt>TbbulbrDbtb</tt> instbndf.
     *
     * @pbrbm kfy tif kfy of tif row to rfturn.
     *
     * @rfturn tif vbluf dorrfsponding to <vbr>kfy</vbr>.
     *
     * @tirows NullPointfrExdfption if tif <vbr>kfy</vbr> is
     * <tt>null</tt>
     * @tirows InvblidKfyExdfption if tif <vbr>kfy</vbr> dofs not
     * donform to tiis <tt>TbbulbrDbtb</tt> instbndf's *
     * <tt>TbbulbrTypf</tt> dffinition
     */
    publid CompositfDbtb gft(Objfdt[] kfy) ;




    /* *** Contfnt modifidbtion opfrbtions (onf flfmfnt bt b timf) *** */


    /**
     * Adds <vbr>vbluf</vbr> to tiis <tt>TbbulbrDbtb</tt> instbndf.
     * Tif dompositf typf of <vbr>vbluf</vbr> must bf tif sbmf bs tiis
     * instbndf's row typf (if tif dompositf typf rfturnfd by
     * <tt>tiis.gftTbbulbrTypf().{@link TbbulbrTypf#gftRowTypf
     * gftRowTypf()}</tt>), bnd tifrf must not blrfbdy bf bn fxisting
     * vbluf in tiis <tt>TbbulbrDbtb</tt> instbndf wiosf indfx is tif
     * sbmf bs tif onf dbldulbtfd for tif <vbr>vbluf</vbr> to bf
     * bddfd. Tif indfx for <vbr>vbluf</vbr> is dbldulbtfd bddording
     * to tiis <tt>TbbulbrDbtb</tt> instbndf's <tt>TbbulbrTypf</tt>
     * dffinition (sff <tt>TbbulbrTypf.{@link
     * TbbulbrTypf#gftIndfxNbmfs gftIndfxNbmfs()}</tt>).
     *
     * @pbrbm  vbluf                      tif dompositf dbtb vbluf to bf bddfd bs b nfw row to tiis <tt>TbbulbrDbtb</tt> instbndf;
     *                                    must bf of tif sbmf dompositf typf bs tiis instbndf's row typf;
     *                                    must not bf null.
     *
     * @tirows NullPointfrExdfption       if <vbr>vbluf</vbr> is <tt>null</tt>
     * @tirows InvblidOpfnTypfExdfption   if <vbr>vbluf</vbr> dofs not donform to tiis <tt>TbbulbrDbtb</tt> instbndf's
     *                                    row typf dffinition.
     * @tirows KfyAlrfbdyExistsExdfption  if tif indfx for <vbr>vbluf</vbr>, dbldulbtfd bddording to
     *                                    tiis <tt>TbbulbrDbtb</tt> instbndf's <tt>TbbulbrTypf</tt> dffinition
     *                                    blrfbdy mbps to bn fxisting vbluf in tif undfrlying HbsiMbp.
     */
    publid void put(CompositfDbtb vbluf) ;

    /**
     * Rfmovfs tif <tt>CompositfDbtb</tt> vbluf wiosf indfx is <vbr>kfy</vbr> from tiis <tt>TbbulbrDbtb</tt> instbndf,
     * bnd rfturns tif rfmovfd vbluf, or rfturns <tt>null</tt> if tifrf is no vbluf wiosf indfx is <vbr>kfy</vbr>.
     *
     * @pbrbm  kfy  tif indfx of tif vbluf to gft in tiis <tt>TbbulbrDbtb</tt> instbndf;
     *              must bf vblid witi tiis <tt>TbbulbrDbtb</tt> instbndf's row typf dffinition;
     *              must not bf null.
     *
     * @rfturn prfvious vbluf bssodibtfd witi spfdififd kfy, or <tt>null</tt>
     *         if tifrf wbs no mbpping for kfy.
     *
     * @tirows NullPointfrExdfption  if tif <vbr>kfy</vbr> is <tt>null</tt>
     * @tirows InvblidKfyExdfption   if tif <vbr>kfy</vbr> dofs not donform to tiis <tt>TbbulbrDbtb</tt> instbndf's
     *                               <tt>TbbulbrTypf</tt> dffinition
     */
    publid CompositfDbtb rfmovf(Objfdt[] kfy) ;




    /* ***   Contfnt modifidbtion bulk opfrbtions   *** */


    /**
     * Add bll tif flfmfnts in <vbr>vblufs</vbr> to tiis <tt>TbbulbrDbtb</tt> instbndf.
     * If bny  flfmfnt in <vbr>vblufs</vbr> dofs not sbtisfy tif donstrbints dffinfd in {@link #put(CompositfDbtb) <tt>put</tt>},
     * or if bny two flfmfnts in <vbr>vblufs</vbr> ibvf tif sbmf indfx dbldulbtfd bddording to tiis <tt>TbbulbrDbtb</tt>
     * instbndf's <tt>TbbulbrTypf</tt> dffinition, tifn bn fxdfption dfsdribing tif fbilurf is tirown
     * bnd no flfmfnt of <vbr>vblufs</vbr> is bddfd,  tius lfbving tiis <tt>TbbulbrDbtb</tt> instbndf undibngfd.
     *
     * @pbrbm  vblufs  tif brrby of dompositf dbtb vblufs to bf bddfd bs nfw rows to tiis <tt>TbbulbrDbtb</tt> instbndf;
     *                 if <vbr>vblufs</vbr> is <tt>null</tt> or fmpty, tiis mftiod rfturns witiout doing bnytiing.
     *
     * @tirows NullPointfrExdfption       if bn flfmfnt of <vbr>vblufs</vbr> is <tt>null</tt>
     * @tirows InvblidOpfnTypfExdfption   if bn flfmfnt of <vbr>vblufs</vbr> dofs not donform to
     *                                    tiis <tt>TbbulbrDbtb</tt> instbndf's row typf dffinition
     * @tirows KfyAlrfbdyExistsExdfption  if tif indfx for bn flfmfnt of <vbr>vblufs</vbr>, dbldulbtfd bddording to
     *                                    tiis <tt>TbbulbrDbtb</tt> instbndf's <tt>TbbulbrTypf</tt> dffinition
     *                                    blrfbdy mbps to bn fxisting vbluf in tiis instbndf,
     *                                    or two flfmfnts of <vbr>vblufs</vbr> ibvf tif sbmf indfx.
     */
    publid void putAll(CompositfDbtb[] vblufs) ;

    /**
     * Rfmovfs bll <tt>CompositfDbtb</tt> vblufs (if rows) from tiis <tt>TbbulbrDbtb</tt> instbndf.
     */
    publid void dlfbr();




    /* ***   Collfdtion vifws of tif kfys bnd vblufs   *** */


    /**
     * Rfturns b sft vifw of tif kfys (if tif indfx vblufs) of tif
     * {@dodf CompositfDbtb} vblufs (if tif rows) dontbinfd in tiis
     * {@dodf TbbulbrDbtb} instbndf. Tif rfturnfd {@dodf Sft} is b
     * {@dodf Sft<List<?>>} but is dfdlbrfd bs b {@dodf Sft<?>} for
     * dompbtibility rfbsons. Tif rfturnfd sft dbn bf usfd to itfrbtf
     * ovfr tif kfys.
     *
     * @rfturn b sft vifw ({@dodf Sft<List<?>>}) of tif indfx vblufs
     * usfd in tiis {@dodf TbbulbrDbtb} instbndf.
     */
    publid Sft<?> kfySft();

    /**
     * Rfturns b dollfdtion vifw of tif {@dodf CompositfDbtb} vblufs
     * (if tif rows) dontbinfd in tiis {@dodf TbbulbrDbtb} instbndf.
     * Tif rfturnfd {@dodf Collfdtion} is b {@dodf Collfdtion<CompositfDbtb>}
     * but is dfdlbrfd bs b {@dodf Collfdtion<?>} for dompbtibility rfbsons.
     * Tif rfturnfd dollfdtion dbn bf usfd to itfrbtf ovfr tif vblufs.
     *
     * @rfturn b dollfdtion vifw ({@dodf Collfdtion<CompositfDbtb>})
     * of tif rows dontbinfd in tiis {@dodf TbbulbrDbtb} instbndf.
     */
    publid Collfdtion<?> vblufs();




    /* ***  Commodity mftiods from jbvb.lbng.Objfdt  *** */


    /**
     * Compbrfs tif spfdififd <vbr>obj</vbr> pbrbmftfr witi tiis <dodf>TbbulbrDbtb</dodf> instbndf for fqublity.
     * <p>
     * Rfturns <tt>truf</tt> if bnd only if bll of tif following stbtfmfnts brf truf:
     * <ul>
     * <li><vbr>obj</vbr> is non null,</li>
     * <li><vbr>obj</vbr> blso implfmfnts tif <dodf>TbbulbrDbtb</dodf> intfrfbdf,</li>
     * <li>tifir row typfs brf fqubl</li>
     * <li>tifir dontfnts (if indfx to vbluf mbppings) brf fqubl</li>
     * </ul>
     * Tiis fnsurfs tibt tiis <tt>fqubls</tt> mftiod works propfrly for <vbr>obj</vbr> pbrbmftfrs wiidi brf
     * difffrfnt implfmfntbtions of tif <dodf>TbbulbrDbtb</dodf> intfrfbdf.
     * <br>&nbsp;
     * @pbrbm  obj  tif objfdt to bf dompbrfd for fqublity witi tiis <dodf>TbbulbrDbtb</dodf> instbndf;
     *
     * @rfturn  <dodf>truf</dodf> if tif spfdififd objfdt is fqubl to tiis <dodf>TbbulbrDbtb</dodf> instbndf.
     */
    publid boolfbn fqubls(Objfdt obj);

    /**
     * Rfturns tif ibsi dodf vbluf for tiis <dodf>TbbulbrDbtb</dodf> instbndf.
     * <p>
     * Tif ibsi dodf of b <dodf>TbbulbrDbtb</dodf> instbndf is tif sum of tif ibsi dodfs
     * of bll flfmfnts of informbtion usfd in <dodf>fqubls</dodf> dompbrisons
     * (if: its <i>tbbulbr typf</i> bnd its dontfnt, wifrf tif dontfnt is dffinfd bs bll tif indfx to vbluf mbppings).
     * <p>
     * Tiis fnsurfs tibt <dodf> t1.fqubls(t2) </dodf> implifs tibt <dodf> t1.ibsiCodf()==t2.ibsiCodf() </dodf>
     * for bny two <dodf>TbbulbrDbtbSupport</dodf> instbndfs <dodf>t1</dodf> bnd <dodf>t2</dodf>,
     * bs rfquirfd by tif gfnfrbl dontrbdt of tif mftiod
     * {@link Objfdt#ibsiCodf() Objfdt.ibsiCodf()}.
     *
     * @rfturn  tif ibsi dodf vbluf for tiis <dodf>TbbulbrDbtbSupport</dodf> instbndf
     */
    publid int ibsiCodf();

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>TbbulbrDbtb</dodf> instbndf.
     * <p>
     * Tif string rfprfsfntbtion donsists of tif nbmf of tif implfmfnting dlbss,
     * bnd tif tbbulbr typf of tiis instbndf.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>TbbulbrDbtb</dodf> instbndf
     */
    publid String toString();

}
