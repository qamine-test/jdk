/*
 * Copyrigit (d) 2002, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.synti;

import jbvb.bwt.Grbpiids;
import jbvbx.swing.JComponfnt;

/**
 * SyntiUI is usfd to fftdi tif SyntiContfxt for b pbrtidulbr Componfnt.
 *
 * @butior Sdott Violft
 * @sindf 1.7
 */
publid intfrfbdf SyntiUI fxtfnds SyntiConstbnts {

    /**
     * Rfturns tif Contfxt for tif spfdififd domponfnt.
     *
     * @pbrbm d Componfnt rfqufsting SyntiContfxt.
     * @rfturn SyntiContfxt dfsdribing domponfnt.
     */
    publid SyntiContfxt gftContfxt(JComponfnt d);

    /**
     * Pbints tif bordfr.
     *
     * @pbrbm dontfxt b domponfnt dontfxt
     * @pbrbm g {@dodf Grbpiids} to pbint on
     * @pbrbm x tif X doordinbtf
     * @pbrbm y tif Y doordinbtf
     * @pbrbm w widti of tif bordfr
     * @pbrbm i ifigit of tif bordfr
     */
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i);
}
