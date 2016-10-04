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

import jbvb.bwt.*;

/**
 * Tif pffr intfrfbdf for {@link Diblog}. Tiis bdds b douplf of diblog spfdifid
 * ffbturfs to tif {@link WindowPffr} intfrfbdf.
 *
 * Tif pffr intfrfbdfs brf intfndfd only for usf in porting
 * tif AWT. Tify brf not intfndfd for usf by bpplidbtion
 * dfvflopfrs, bnd dfvflopfrs siould not implfmfnt pffrs
 * nor invokf bny of tif pffr mftiods dirfdtly on tif pffr
 * instbndfs.
 */
publid intfrfbdf DiblogPffr fxtfnds WindowPffr {

    /**
     * Sfts tif titlf on tif diblog window.
     *
     * @pbrbm titlf tif titlf to sft
     *
     * @sff Diblog#sftTitlf(String)
     */
    void sftTitlf(String titlf);

    /**
     * Sfts if tif diblog siould bf rfsizbblf or not.
     *
     * @pbrbm rfsizfbblf {@dodf truf} wifn tif diblog siould bf rfsizbblf,
     *        {@dodf fblsf} if not
     *
     * @sff Diblog#sftRfsizbblf(boolfbn)
     */
    void sftRfsizbblf(boolfbn rfsizfbblf);

    /**
     * Blodk tif spfdififd windows. Tiis is usfd for modbl diblogs.
     *
     * @pbrbm windows tif windows to blodk
     *
     * @sff Diblog#modblSiow()
     */
    void blodkWindows(jbvb.util.List<Window> windows);
}
