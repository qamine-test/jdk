/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
/*
 * $Id: NodfSftDbtb.jbvb,v 1.5 2005/05/10 15:47:42 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto;

import jbvb.util.Itfrbtor;

/**
 * An bbstrbdt rfprfsfntbtion of b <dodf>Dbtb</dodf> typf dontbining b
 * nodf-sft. Tif typf (dlbss) bnd ordfring of tif nodfs dontbinfd in tif sft
 * brf not dffinfd by tiis dlbss; instfbd tibt bfibvior siould bf
 * dffinfd by <dodf>NodfSftDbtb</dodf> subdlbssfs.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 */
publid intfrfbdf NodfSftDbtb fxtfnds Dbtb {

    /**
     * Rfturns b rfbd-only itfrbtor ovfr tif nodfs dontbinfd in tiis
     * <dodf>NodfSftDbtb</dodf> in
     * <b irff="ittp://www.w3.org/TR/1999/REC-xpbti-19991116#dt-dodumfnt-ordfr">
     * dodumfnt ordfr</b>. Attfmpts to modify tif rfturnfd itfrbtor
     * vib tif <dodf>rfmovf</dodf> mftiod tirow
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> ovfr tif nodfs in tiis
     *    <dodf>NodfSftDbtb</dodf> in dodumfnt ordfr
     */
    @SupprfssWbrnings("rbwtypfs")
    Itfrbtor itfrbtor();
}
