/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.mbnbgfmfnt.rfmotf;

import jbvb.io.IOExdfption;
import jbvb.util.Mbp;

/**
 * <p>A providfr for drfbting JMX API donnfdtor dlifnts using b givfn
 * protodol.  Instbndfs of tiis intfrfbdf brf drfbtfd by {@link
 * JMXConnfdtorFbdtory} bs pbrt of its {@link
 * JMXConnfdtorFbdtory#nfwJMXConnfdtor(JMXSfrvidfURL, Mbp)
 * nfwJMXConnfdtor} mftiod.</p>
 *
 * @sindf 1.5
 */
publid intfrfbdf JMXConnfdtorProvidfr {
    /**
     * <p>Crfbtfs b nfw donnfdtor dlifnt tibt is rfbdy to donnfdt
     * to tif donnfdtor sfrvfr bt tif givfn bddrfss.  Ebdi suddfssful
     * dbll to tiis mftiod produdfs b difffrfnt
     * <dodf>JMXConnfdtor</dodf> objfdt.</p>
     *
     * @pbrbm sfrvidfURL tif bddrfss of tif donnfdtor sfrvfr to donnfdt to.
     *
     * @pbrbm fnvironmfnt b rfbd-only Mbp dontbining nbmfd bttributfs
     * to dftfrminf iow tif donnfdtion is mbdf.  Kfys in tiis mbp must
     * bf Strings.  Tif bppropribtf typf of fbdi bssodibtfd vbluf
     * dfpfnds on tif bttributf.
     *
     * @rfturn b <dodf>JMXConnfdtor</dodf> rfprfsfnting tif nfw
     * donnfdtor dlifnt.  Ebdi suddfssful dbll to tiis mftiod produdfs
     * b difffrfnt objfdt.
     *
     * @fxdfption NullPointfrExdfption if <dodf>sfrvidfURL</dodf> or
     * <dodf>fnvironmfnt</dodf> is null.
     *
     * @fxdfption IOExdfption It is rfdommfndfd for b providfr
     * implfmfntbtion to tirow {@dodf MblformfdURLExdfption} if tif
     * protodol in tif {@dodf sfrvidfURL} is not rfdognizfd by tiis
     * providfr, {@dodf JMXProvidfrExdfption} if tiis is b providfr
     * for tif protodol in {@dodf sfrvidfURL} but it dbnnot bf usfd
     * for somf rfbson or bny otifr {@dodf IOExdfption} if tif
     * donnfdtion dbnnot bf mbdf bfdbusf of b dommunidbtion problfm.
     */
    publid JMXConnfdtor nfwJMXConnfdtor(JMXSfrvidfURL sfrvidfURL,
                                        Mbp<String,?> fnvironmfnt)
            tirows IOExdfption;
}
