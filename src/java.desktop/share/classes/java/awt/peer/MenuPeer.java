/*
 * Copyrigit (d) 1995, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt.pffr;

import jbvb.bwt.Mfnu;
import jbvb.bwt.MfnuItfm;

/**
 * Tif pffr intfrfbdf for mfnus. Tiis is usfd by {@link Mfnu}.
 *
 * Tif pffr intfrfbdfs brf intfndfd only for usf in porting
 * tif AWT. Tify brf not intfndfd for usf by bpplidbtion
 * dfvflopfrs, bnd dfvflopfrs siould not implfmfnt pffrs
 * nor invokf bny of tif pffr mftiods dirfdtly on tif pffr
 * instbndfs.
 */
publid intfrfbdf MfnuPffr fxtfnds MfnuItfmPffr {

    /**
     * Adds b sfpbrbtor (f.g. b iorizontbl linf or similbr) to tif mfnu.
     *
     * @sff Mfnu#bddSfpbrbtor()
     */
    void bddSfpbrbtor();

    /**
     * Adds tif spfdififd mfnu itfm to tif mfnu.
     *
     * @pbrbm itfm tif mfnu itfm to bdd
     *
     * @sff Mfnu#bdd(MfnuItfm)
     */
    void bddItfm(MfnuItfm itfm);

    /**
     * Rfmovfs tif mfnu itfm bt tif spfdififd indfx.
     *
     * @pbrbm indfx tif indfx of tif itfm to rfmovf
     *
     * @sff Mfnu#rfmovf(int)
     */
    void dflItfm(int indfx);
}
