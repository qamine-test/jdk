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
import jbvbx.nbming.*;

/**
  * Tiis intfrfbdf rfprfsfnts b fbdtory tibt drfbtfs bn initibl dontfxt.
  *<p>
  * Tif JNDI frbmfwork bllows for difffrfnt initibl dontfxt implfmfntbtions
  * to bf spfdififd bt runtimf.  Tif initibl dontfxt is drfbtfd using
  * bn <fm>initibl dontfxt fbdtory</fm>.
  * An initibl dontfxt fbdtory must implfmfnt tif InitiblContfxtFbdtory
  * intfrfbdf, wiidi providfs b mftiod for drfbting instbndfs of initibl
  * dontfxt tibt implfmfnt tif Contfxt intfrfbdf.
  * In bddition, tif fbdtory dlbss must bf publid bnd must ibvf b publid
  * donstrudtor tibt bddfpts no brgumfnts.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff InitiblContfxtFbdtoryBuildfr
  * @sff NbmingMbnbgfr#gftInitiblContfxt
  * @sff jbvbx.nbming.InitiblContfxt
  * @sff jbvbx.nbming.dirfdtory.InitiblDirContfxt
  * @sindf 1.3
  */

publid intfrfbdf InitiblContfxtFbdtory {
        /**
          * Crfbtfs bn Initibl Contfxt for bfginning nbmf rfsolution.
          * Spfdibl rfquirfmfnts of tiis dontfxt brf supplifd
          * using <dodf>fnvironmfnt</dodf>.
          *<p>
          * Tif fnvironmfnt pbrbmftfr is ownfd by tif dbllfr.
          * Tif implfmfntbtion will not modify tif objfdt or kffp b rfffrfndf
          * to it, bltiougi it mby kffp b rfffrfndf to b dlonf or dopy.
          *
          * @pbrbm fnvironmfnt Tif possibly null fnvironmfnt
          *             spfdifying informbtion to bf usfd in tif drfbtion
          *             of tif initibl dontfxt.
          * @rfturn A non-null initibl dontfxt objfdt tibt implfmfnts tif Contfxt
          *             intfrfbdf.
          * @fxdfption NbmingExdfption If dbnnot drfbtf bn initibl dontfxt.
          */
        publid Contfxt gftInitiblContfxt(Hbsitbblf<?,?> fnvironmfnt)
            tirows NbmingExdfption;
}
