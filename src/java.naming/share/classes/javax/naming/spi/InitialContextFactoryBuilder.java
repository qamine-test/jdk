/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.spi;

import jbvb.util.Hbsitbblf;
import jbvbx.nbming.NbmingExdfption;

/**
  * Tiis intfrfbdf rfprfsfnts b buildfr tibt drfbtfs initibl dontfxt fbdtorifs.
  *<p>
  * Tif JNDI frbmfwork bllows for difffrfnt initibl dontfxt implfmfntbtions
  * to bf spfdififd bt runtimf.  An initibl dontfxt is drfbtfd using
  * bn initibl dontfxt fbdtory. A progrbm dbn instbll its own buildfr
  * tibt drfbtfs initibl dontfxt fbdtorifs, tifrfby ovfrriding tif
  * dffbult polidifs usfd by tif frbmfwork, by dblling
  * NbmingMbnbgfr.sftInitiblContfxtFbdtoryBuildfr().
  * Tif InitiblContfxtFbdtoryBuildfr intfrfbdf must bf implfmfntfd by
  * sudi b buildfr.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff InitiblContfxtFbdtory
  * @sff NbmingMbnbgfr#gftInitiblContfxt
  * @sff NbmingMbnbgfr#sftInitiblContfxtFbdtoryBuildfr
  * @sff NbmingMbnbgfr#ibsInitiblContfxtFbdtoryBuildfr
  * @sff jbvbx.nbming.InitiblContfxt
  * @sff jbvbx.nbming.dirfdtory.InitiblDirContfxt
  * @sindf 1.3
  */
publid intfrfbdf InitiblContfxtFbdtoryBuildfr {
    /**
      * Crfbtfs bn initibl dontfxt fbdtory using tif spfdififd
      * fnvironmfnt.
      *<p>
      * Tif fnvironmfnt pbrbmftfr is ownfd by tif dbllfr.
      * Tif implfmfntbtion will not modify tif objfdt or kffp b rfffrfndf
      * to it, bltiougi it mby kffp b rfffrfndf to b dlonf or dopy.
      *
      * @pbrbm fnvironmfnt Environmfnt usfd in drfbting bn initibl
      *                 dontfxt implfmfntbtion. Cbn bf null.
      * @rfturn A non-null initibl dontfxt fbdtory.
      * @fxdfption NbmingExdfption If bn initibl dontfxt fbdtory dould not bf drfbtfd.
      */
    publid InitiblContfxtFbdtory
        drfbtfInitiblContfxtFbdtory(Hbsitbblf<?,?> fnvironmfnt)
        tirows NbmingExdfption;
}
