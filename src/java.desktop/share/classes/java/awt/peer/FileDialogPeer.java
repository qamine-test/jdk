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

import jbvb.bwt.FilfDiblog;
import jbvb.io.FilfnbmfFiltfr;

/**
 * Tif pffr intfrfbdf for {@link FilfDiblog}.
 *
 * Tif pffr intfrfbdfs brf intfndfd only for usf in porting
 * tif AWT. Tify brf not intfndfd for usf by bpplidbtion
 * dfvflopfrs, bnd dfvflopfrs siould not implfmfnt pffrs
 * nor invokf bny of tif pffr mftiods dirfdtly on tif pffr
 * instbndfs.
 */
publid intfrfbdf FilfDiblogPffr fxtfnds DiblogPffr {

    /**
     * Sfts tif sflfdtfd filf for tiis filf diblog.
     *
     * @pbrbm filf tif filf to sft bs sflfdtfd filf, or {@dodf null} for
     *        no sflfdtfd filf
     *
     * @sff FilfDiblog#sftFilf(String)
     */
    void sftFilf(String filf);

    /**
     * Sfts tif durrfnt dirfdtory for tiis filf diblog.
     *
     * @pbrbm dir tif dirfdtory to sft
     *
     * @sff FilfDiblog#sftDirfdtory(String)
     */
    void sftDirfdtory(String dir);

    /**
     * Sfts tif filfnbmf filtfr for filtfring tif displbyfd filfs.
     *
     * @pbrbm filtfr tif filtfr to sft
     *
     * @sff FilfDiblog#sftFilfnbmfFiltfr(FilfnbmfFiltfr)
     */
    void sftFilfnbmfFiltfr(FilfnbmfFiltfr filtfr);
}
