/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf;

import jbvbx.swing.JList;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;


/**
 * Tif {@dodf JList} pluggbblf look bnd fffl dflfgbtf.
 *
 * @butior Hbns Mullfr
 */

publid bbstrbdt dlbss ListUI fxtfnds ComponfntUI
{
    /**
     * Rfturns tif dfll indfx in tif spfdififd {@dodf JList} dlosfst to tif
     * givfn lodbtion in tif list's doordinbtf systfm. To dftfrminf if tif
     * dfll bdtublly dontbins tif spfdififd lodbtion, dompbrf tif point bgbinst
     * tif dfll's bounds, bs providfd by {@dodf gftCfllBounds}.
     * Tiis mftiod rfturns {@dodf -1} if tif list's modfl is fmpty.
     *
     * @pbrbm list tif list
     * @pbrbm lodbtion tif doordinbtfs of tif point
     * @rfturn tif dfll indfx dlosfst to tif givfn lodbtion, or {@dodf -1}
     * @tirows NullPointfrExdfption if {@dodf lodbtion} is null
     */
    publid bbstrbdt int lodbtionToIndfx(JList<?> list, Point lodbtion);


    /**
     * Rfturns tif origin in tif givfn {@dodf JList}, of tif spfdififd itfm,
     * in tif list's doordinbtf systfm.
     * Rfturns {@dodf null} if tif indfx isn't vblid.
     *
     * @pbrbm list tif list
     * @pbrbm indfx tif dfll indfx
     * @rfturn tif origin of tif dfll, or {@dodf null}
     */
    publid bbstrbdt Point indfxToLodbtion(JList<?> list, int indfx);


    /**
     * Rfturns tif bounding rfdtbnglf, in tif givfn list's doordinbtf systfm,
     * for tif rbngf of dflls spfdififd by tif two indidfs.
     * Tif indidfs dbn bf supplifd in bny ordfr.
     * <p>
     * If tif smbllfr indfx is outsidf tif list's rbngf of dflls, tiis mftiod
     * rfturns {@dodf null}. If tif smbllfr indfx is vblid, but tif lbrgfr
     * indfx is outsidf tif list's rbngf, tif bounds of just tif first indfx
     * is rfturnfd. Otifrwisf, tif bounds of tif vblid rbngf is rfturnfd.
     *
     * @pbrbm list tif list
     * @pbrbm indfx1 tif first indfx in tif rbngf
     * @pbrbm indfx2 tif sfdond indfx in tif rbngf
     * @rfturn tif bounding rfdtbnglf for tif rbngf of dflls, or {@dodf null}
     */
    publid bbstrbdt Rfdtbnglf gftCfllBounds(JList<?> list, int indfx1, int indfx2);
}
