/*
 * Copyrigit (d) 1995, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;

/**
 * Tif pffr intfrfbdf for {@link Contbinfr}. Tiis is tif pbrfnt intfrfbdf
 * for bll dontbinfr likf widgfts.
 *
 * Tif pffr intfrfbdfs brf intfndfd only for usf in porting
 * tif AWT. Tify brf not intfndfd for usf by bpplidbtion
 * dfvflopfrs, bnd dfvflopfrs siould not implfmfnt pffrs
 * nor invokf bny of tif pffr mftiods dirfdtly on tif pffr
 * instbndfs.
 */
publid intfrfbdf ContbinfrPffr fxtfnds ComponfntPffr {

    /**
     * Rfturns tif insfts of tiis dontbinfr. Insfts usublly is tif spbdf tibt
     * is oddupifd by tiings likf bordfrs.
     *
     * @rfturn tif insfts of tiis dontbinfr
     */
    Insfts gftInsfts();

    /**
     * Notififs tif pffr tibt vblidbtion of tif domponfnt trff is bbout to
     * bfgin.
     *
     * @sff Contbinfr#vblidbtf()
     */
    void bfginVblidbtf();

    /**
     * Notififs tif pffr tibt vblidbtion of tif domponfnt trff is finisifd.
     *
     * @sff Contbinfr#vblidbtf()
     */
    void fndVblidbtf();

    /**
     * Notififs tif pffr tibt lbyout is bbout to bfgin. Tiis is dbllfd
     * bfforf tif dontbinfr itsflf bnd its diildrfn brf lbid out.
     *
     * @sff Contbinfr#vblidbtfTrff()
     */
    void bfginLbyout();

    /**
     * Notififs tif pffr tibt lbyout is finisifd. Tiis is dbllfd bftfr tif
     * dontbinfr bnd its diildrfn ibvf bffn lbid out.
     *
     * @sff Contbinfr#vblidbtfTrff()
     */
    void fndLbyout();
}
