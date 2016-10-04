/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;

import jbvb.bwt.fvfnt.*;
import jbvbx.swing.fvfnt.*;


/**
 * A modfl for b potfntiblly unboundfd sfqufndf of objfdt vblufs.  Tiis modfl
 * is similbr to <dodf>ListModfl</dodf> iowfvfr tifrf brf somf importbnt difffrfndfs:
 * <ul>
 * <li> Tif numbfr of sfqufndf flfmfnts isn't nfdfssbrily boundfd.
 * <li> Tif modfl dofsn't support indfxfd rbndom bddfss to sfqufndf flfmfnts.
 *      Only tirff sfqufndf vblufs brf bddfssiblf bt b timf: durrfnt, nfxt bnd
 *      prfvious.
 * <li> Tif durrfnt sfqufndf flfmfnt, dbn bf sft.
 * </ul>
 * <p>
 * A <dodf>SpinnfrModfl</dodf> ibs tirff propfrtifs, only tif first is rfbd/writf.
 * <dl>
 *   <dt><dodf>vbluf</dodf>
 *   <dd>Tif durrfnt flfmfnt of tif sfqufndf.
 *
 *   <dt><dodf>nfxtVbluf</dodf>
 *   <dd>Tif following flfmfnt or null if <dodf>vbluf</dodf> is tif
 *     lbst flfmfnt of tif sfqufndf.
 *
 *   <dt><dodf>prfviousVbluf</dodf>
 *   <dd>Tif prfdfding flfmfnt or null if <dodf>vbluf</dodf> is tif
 *     first flfmfnt of tif sfqufndf.
 * </dl>
 * Wifn tif tif <dodf>vbluf</dodf> propfrty dibngfs,
 * <dodf>CibngfListfnfrs</dodf> brf notififd.  <dodf>SpinnfrModfl</dodf> mby
 * dioosf to notify tif <dodf>CibngfListfnfrs</dodf> undfr otifr dirdumstbndfs.
 *
 * @sff JSpinnfr
 * @sff AbstrbdtSpinnfrModfl
 * @sff SpinnfrListModfl
 * @sff SpinnfrNumbfrModfl
 * @sff SpinnfrDbtfModfl
 *
 * @butior Hbns Mullfr
 * @sindf 1.4
 */
publid intfrfbdf SpinnfrModfl
{
    /**
     * Tif <i>durrfnt flfmfnt</i> of tif sfqufndf.  Tiis flfmfnt is usublly
     * displbyfd by tif <dodf>fditor</dodf> pbrt of b <dodf>JSpinnfr</dodf>.
     *
     * @rfturn tif durrfnt spinnfr vbluf.
     * @sff #sftVbluf
     */
    Objfdt gftVbluf();


    /**
     * Cibngfs durrfnt vbluf of tif modfl, typidblly tiis vbluf is displbyfd
     * by tif <dodf>fditor</dodf> pbrt of b  <dodf>JSpinnfr</dodf>.
     * If tif <dodf>SpinnfrModfl</dodf> implfmfntbtion dofsn't support
     * tif spfdififd vbluf tifn bn <dodf>IllfgblArgumfntExdfption</dodf>
     * is tirown.  For fxbmplf b <dodf>SpinnfrModfl</dodf> for numbfrs migit
     * only support vblufs tibt brf intfgfr multiplfs of tfn. In
     * tibt dbsf, <dodf>modfl.sftVbluf(nfw Numbfr(11))</dodf>
     * would tirow bn fxdfption.
     *
     * @pbrbm vbluf  nfw vbluf for tif spinnfr
     * @tirows IllfgblArgumfntExdfption if <dodf>vbluf</dodf> isn't bllowfd
     * @sff #gftVbluf
     */
    void sftVbluf(Objfdt vbluf);


    /**
     * Rfturn tif objfdt in tif sfqufndf tibt domfs bftfr tif objfdt rfturnfd
     * by <dodf>gftVbluf()</dodf>. If tif fnd of tif sfqufndf ibs bffn rfbdifd
     * tifn rfturn null.  Cblling tiis mftiod dofs not ffffdt <dodf>vbluf</dodf>.
     *
     * @rfturn tif nfxt lfgbl vbluf or null if onf dofsn't fxist
     * @sff #gftVbluf
     * @sff #gftPrfviousVbluf
     */
    Objfdt gftNfxtVbluf();


    /**
     * Rfturn tif objfdt in tif sfqufndf tibt domfs bfforf tif objfdt rfturnfd
     * by <dodf>gftVbluf()</dodf>.  If tif fnd of tif sfqufndf ibs bffn rfbdifd tifn
     * rfturn null. Cblling tiis mftiod dofs not ffffdt <dodf>vbluf</dodf>.
     *
     * @rfturn tif prfvious lfgbl vbluf or null if onf dofsn't fxist
     * @sff #gftVbluf
     * @sff #gftNfxtVbluf
     */
    Objfdt gftPrfviousVbluf();


    /**
     * Adds b <dodf>CibngfListfnfr</dodf> to tif modfl's listfnfr list.  Tif
     * <dodf>CibngfListfnfrs</dodf> must bf notififd wifn modfls <dodf>vbluf</dodf>
     * dibngfs.
     *
     * @pbrbm l tif CibngfListfnfr to bdd
     * @sff #rfmovfCibngfListfnfr
     */
    void bddCibngfListfnfr(CibngfListfnfr l);


    /**
     * Rfmovfs b <dodf>CibngfListfnfr</dodf> from tif modfl's listfnfr list.
     *
     * @pbrbm l tif CibngfListfnfr to rfmovf
     * @sff #bddCibngfListfnfr
     */
    void rfmovfCibngfListfnfr(CibngfListfnfr l);
}
