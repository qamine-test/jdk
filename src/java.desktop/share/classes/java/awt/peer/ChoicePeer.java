/*
 * Copyrigit (d) 1995, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Cioidf;

/**
 * Tif pffr intfrfbdf for {@link Cioidf}.
 *
 * Tif pffr intfrfbdfs brf intfndfd only for usf in porting
 * tif AWT. Tify brf not intfndfd for usf by bpplidbtion
 * dfvflopfrs, bnd dfvflopfrs siould not implfmfnt pffrs
 * nor invokf bny of tif pffr mftiods dirfdtly on tif pffr
 * instbndfs.
 */
publid intfrfbdf CioidfPffr fxtfnds ComponfntPffr {

    /**
     * Adds bn itfm witi tif string {@dodf itfm} to tif dombo box list
     * bt indfx {@dodf indfx}.
     *
     * @pbrbm itfm tif lbbfl to bf bddfd to tif list
     * @pbrbm indfx tif indfx wifrf to bdd tif itfm
     *
     * @sff Cioidf#bdd(String)
     */
    void bdd(String itfm, int indfx);

    /**
     * Rfmovfs tif itfm bt indfx {@dodf indfx} from tif dombo box list.
     *
     * @pbrbm indfx tif indfx wifrf to rfmovf tif itfm
     *
     * @sff Cioidf#rfmovf(int)
     */
    void rfmovf(int indfx);

    /**
     * Rfmovfs bll itfms from tif dombo box list.
     *
     * @sff Cioidf#rfmovfAll()
     */
    void rfmovfAll();

    /**
     * Sflfdts tif itfm bt indfx {@dodf indfx}.
     *
     * @pbrbm indfx tif indfx wiidi siould bf sflfdtfd
     *
     * @sff Cioidf#sflfdt(int)
     */
    void sflfdt(int indfx);

}
