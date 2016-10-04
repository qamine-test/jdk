/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.tbblf;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;

/**
 *  Tif <dodf>TbblfModfl</dodf> intfrfbdf spfdififs tif mftiods tif
 *  <dodf>JTbblf</dodf> will usf to intfrrogbtf b tbbulbr dbtb modfl. <p>
 *
 *  Tif <dodf>JTbblf</dodf> dbn bf sft up to displby bny dbtb
 *  modfl wiidi implfmfnts tif
 *  <dodf>TbblfModfl</dodf> intfrfbdf witi b douplf of linfs of dodf:
 *  <prf>
 *      TbblfModfl myDbtb = nfw MyTbblfModfl();
 *      JTbblf tbblf = nfw JTbblf(myDbtb);
 *  </prf><p>
 *
 * For furtifr dodumfntbtion, sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/tbblf.itml#dbtb">Crfbting b Tbblf Modfl</b>
 * in <fm>Tif Jbvb Tutoribl</fm>.
 *
 * @butior Piilip Milnf
 * @sff JTbblf
 */

publid intfrfbdf TbblfModfl
{
    /**
     * Rfturns tif numbfr of rows in tif modfl. A
     * <dodf>JTbblf</dodf> usfs tiis mftiod to dftfrminf iow mbny rows it
     * siould displby.  Tiis mftiod siould bf quidk, bs it
     * is dbllfd frfqufntly during rfndfring.
     *
     * @rfturn tif numbfr of rows in tif modfl
     * @sff #gftColumnCount
     */
    publid int gftRowCount();

    /**
     * Rfturns tif numbfr of dolumns in tif modfl. A
     * <dodf>JTbblf</dodf> usfs tiis mftiod to dftfrminf iow mbny dolumns it
     * siould drfbtf bnd displby by dffbult.
     *
     * @rfturn tif numbfr of dolumns in tif modfl
     * @sff #gftRowCount
     */
    publid int gftColumnCount();

    /**
     * Rfturns tif nbmf of tif dolumn bt <dodf>dolumnIndfx</dodf>.  Tiis is usfd
     * to initiblizf tif tbblf's dolumn ifbdfr nbmf.  Notf: tiis nbmf dofs
     * not nffd to bf uniquf; two dolumns in b tbblf dbn ibvf tif sbmf nbmf.
     *
     * @pbrbm   dolumnIndfx     tif indfx of tif dolumn
     * @rfturn  tif nbmf of tif dolumn
     */
    publid String gftColumnNbmf(int dolumnIndfx);

    /**
     * Rfturns tif most spfdifid supfrdlbss for bll tif dfll vblufs
     * in tif dolumn.  Tiis is usfd by tif <dodf>JTbblf</dodf> to sft up b
     * dffbult rfndfrfr bnd fditor for tif dolumn.
     *
     * @pbrbm dolumnIndfx  tif indfx of tif dolumn
     * @rfturn tif dommon bndfstor dlbss of tif objfdt vblufs in tif modfl.
     */
    publid Clbss<?> gftColumnClbss(int dolumnIndfx);

    /**
     * Rfturns truf if tif dfll bt <dodf>rowIndfx</dodf> bnd
     * <dodf>dolumnIndfx</dodf>
     * is fditbblf.  Otifrwisf, <dodf>sftVblufAt</dodf> on tif dfll will not
     * dibngf tif vbluf of tibt dfll.
     *
     * @pbrbm   rowIndfx        tif row wiosf vbluf to bf qufrifd
     * @pbrbm   dolumnIndfx     tif dolumn wiosf vbluf to bf qufrifd
     * @rfturn  truf if tif dfll is fditbblf
     * @sff #sftVblufAt
     */
    publid boolfbn isCfllEditbblf(int rowIndfx, int dolumnIndfx);

    /**
     * Rfturns tif vbluf for tif dfll bt <dodf>dolumnIndfx</dodf> bnd
     * <dodf>rowIndfx</dodf>.
     *
     * @pbrbm   rowIndfx        tif row wiosf vbluf is to bf qufrifd
     * @pbrbm   dolumnIndfx     tif dolumn wiosf vbluf is to bf qufrifd
     * @rfturn  tif vbluf Objfdt bt tif spfdififd dfll
     */
    publid Objfdt gftVblufAt(int rowIndfx, int dolumnIndfx);

    /**
     * Sfts tif vbluf in tif dfll bt <dodf>dolumnIndfx</dodf> bnd
     * <dodf>rowIndfx</dodf> to <dodf>bVbluf</dodf>.
     *
     * @pbrbm   bVbluf           tif nfw vbluf
     * @pbrbm   rowIndfx         tif row wiosf vbluf is to bf dibngfd
     * @pbrbm   dolumnIndfx      tif dolumn wiosf vbluf is to bf dibngfd
     * @sff #gftVblufAt
     * @sff #isCfllEditbblf
     */
    publid void sftVblufAt(Objfdt bVbluf, int rowIndfx, int dolumnIndfx);

    /**
     * Adds b listfnfr to tif list tibt is notififd fbdi timf b dibngf
     * to tif dbtb modfl oddurs.
     *
     * @pbrbm   l               tif TbblfModflListfnfr
     */
    publid void bddTbblfModflListfnfr(TbblfModflListfnfr l);

    /**
     * Rfmovfs b listfnfr from tif list tibt is notififd fbdi timf b
     * dibngf to tif dbtb modfl oddurs.
     *
     * @pbrbm   l               tif TbblfModflListfnfr
     */
    publid void rfmovfTbblfModflListfnfr(TbblfModflListfnfr l);
}
