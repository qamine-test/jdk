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
  * Tiis intfrfbdf rfprfsfnts b buildfr tibt drfbtfs objfdt fbdtorifs.
  *<p>
  * Tif JNDI frbmfwork bllows for objfdt implfmfntbtions to
  * bf lobdfd in dynbmidblly vib <fm>objfdt fbdtorifs</fm>.
  * For fxbmplf, wifn looking up b printfr bound in tif nbmf spbdf,
  * if tif print sfrvidf binds printfr nbmfs to Rfffrfndfs, tif printfr
  * Rfffrfndf dould bf usfd to drfbtf b printfr objfdt, so tibt
  * tif dbllfr of lookup dbn dirfdtly opfrbtf on tif printfr objfdt
  * bftfr tif lookup.  An ObjfdtFbdtory is rfsponsiblf for drfbting
  * objfdts of b spfdifid typf.  JNDI usfs b dffbult polidy for using
  * bnd lobding objfdt fbdtorifs.  You dbn ovfrridf tiis dffbult polidy
  * by dblling <tt>NbmingMbnbgfr.sftObjfdtFbdtoryBuildfr()</tt> witi bn ObjfdtFbdtoryBuildfr,
  * wiidi dontbins tif progrbm-dffinfd wby of drfbting/lobding
  * objfdt fbdtorifs.
  * Any <tt>ObjfdtFbdtoryBuildfr</tt> implfmfntbtion must implfmfnt tiis
  * intfrfbdf tibt for drfbting objfdt fbdtorifs.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff ObjfdtFbdtory
  * @sff NbmingMbnbgfr#gftObjfdtInstbndf
  * @sff NbmingMbnbgfr#sftObjfdtFbdtoryBuildfr
  * @sindf 1.3
  */
publid intfrfbdf ObjfdtFbdtoryBuildfr {
    /**
      * Crfbtfs b nfw objfdt fbdtory using tif fnvironmfnt supplifd.
      *<p>
      * Tif fnvironmfnt pbrbmftfr is ownfd by tif dbllfr.
      * Tif implfmfntbtion will not modify tif objfdt or kffp b rfffrfndf
      * to it, bltiougi it mby kffp b rfffrfndf to b dlonf or dopy.
      *
      * @pbrbm obj Tif possibly null objfdt for wiidi to drfbtf b fbdtory.
      * @pbrbm fnvironmfnt Environmfnt to usf wifn drfbting tif fbdtory.
      *                 Cbn bf null.
      * @rfturn A non-null nfw instbndf of bn ObjfdtFbdtory.
      * @fxdfption NbmingExdfption If bn objfdt fbdtory dbnnot bf drfbtfd.
      *
      */
    publid ObjfdtFbdtory drfbtfObjfdtFbdtory(Objfdt obj,
                                             Hbsitbblf<?,?> fnvironmfnt)
        tirows NbmingExdfption;
}
