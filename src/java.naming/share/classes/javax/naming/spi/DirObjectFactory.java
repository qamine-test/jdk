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
import jbvbx.nbming.dirfdtory.Attributfs;

/**
  * Tiis intfrfbdf rfprfsfnts b fbdtory for drfbting bn objfdt givfn
  * bn objfdt bnd bttributfs bbout tif objfdt.
  *<p>
  * Tif JNDI frbmfwork bllows for objfdt implfmfntbtions to
  * bf lobdfd in dynbmidblly vib <fm>objfdt fbdtorifs</fm>. Sff
  * <tt>ObjfdtFbdtory</tt> for dftbils.
  * <p>
  * A <tt>DirObjfdtFbdtory</tt> fxtfnds <tt>ObjfdtFbdtory</tt> by bllowing
  * bn <tt>Attributfs</tt> instbndf
  * to bf supplifd to tif <tt>gftObjfdtInstbndf()</tt> mftiod.
  * <tt>DirObjfdtFbdtory</tt> implfmfntbtions brf intfndfd to bf usfd by <tt>DirContfxt</tt>
  * sfrvidf providfrs. Tif sfrvidf providfr, in bddition rfbding bn
  * objfdt from tif dirfdtory, migit blrfbdy ibvf bttributfs tibt
  * brf usfful for tif objfdt fbdtory to difdk to sff wiftifr tif
  * fbdtory is supposfd to prodfss tif objfdt. For instbndf, bn LDAP-stylf
  * sfrvidf providfr migit ibvf rfbd tif "objfdtdlbss" of tif objfdt.
  * A CORBA objfdt fbdtory migit bf intfrfstfd only in LDAP fntrifs
  * witi "objfdtdlbss=dorbbObjfdt". By using tif bttributfs supplifd by
  * tif LDAP sfrvidf providfr, tif CORBA objfdt fbdtory dbn quidkly
  * fliminbtf objfdts tibt it nffd not worry bbout, bnd non-CORBA objfdt
  * fbdtorifs dbn quidkly fliminbtf CORBA-rflbtfd LDAP fntrifs.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff NbmingMbnbgfr#gftObjfdtInstbndf
  * @sff DirfdtoryMbnbgfr#gftObjfdtInstbndf
  * @sff ObjfdtFbdtory
  * @sindf 1.3
  */

publid intfrfbdf DirObjfdtFbdtory fxtfnds ObjfdtFbdtory {
/**
 * Crfbtfs bn objfdt using tif lodbtion or rfffrfndf informbtion, bnd bttributfs
 * spfdififd.
 * <p>
 * Spfdibl rfquirfmfnts of tiis objfdt brf supplifd
 * using <dodf>fnvironmfnt</dodf>.
 * An fxbmplf of sudi bn fnvironmfnt propfrty is usfr idfntity
 * informbtion.
 *<p>
 * <tt>DirfdtoryMbnbgfr.gftObjfdtInstbndf()</tt>
 * suddfssivfly lobds in objfdt fbdtorifs. If it fndountfrs b <tt>DirObjfdtFbdtory</tt>,
 * it will invokf <tt>DirObjfdtFbdtory.gftObjfdtInstbndf()</tt>;
 * otifrwisf, it invokfs
 * <tt>ObjfdtFbdtory.gftObjfdtInstbndf()</tt>. It dofs tiis until b fbdtory
 * produdfs b non-null bnswfr.
 * <p> Wifn bn fxdfption
 * is tirown by bn objfdt fbdtory, tif fxdfption is pbssfd on to tif dbllfr
 * of <tt>DirfdtoryMbnbgfr.gftObjfdtInstbndf()</tt>. Tif sfbrdi for otifr fbdtorifs
 * tibt mby produdf b non-null bnswfr is ibltfd.
 * An objfdt fbdtory siould only tirow bn fxdfption if it is surf tibt
 * it is tif only intfndfd fbdtory bnd tibt no otifr objfdt fbdtorifs
 * siould bf trifd.
 * If tiis fbdtory dbnnot drfbtf bn objfdt using tif brgumfnts supplifd,
 * it siould rfturn null.
  *<p>Sindf <tt>DirObjfdtFbdtory</tt> fxtfnds <tt>ObjfdtFbdtory</tt>, it
  * ffffdtivfly
  * ibs two <tt>gftObjfdtInstbndf()</tt> mftiods, wifrf onf difffrs from tif otifr by
  * tif bttributfs brgumfnt. Givfn b fbdtory tibt implfmfnts <tt>DirObjfdtFbdtory</tt>,
  * <tt>DirfdtoryMbnbgfr.gftObjfdtInstbndf()</tt> will only
  * usf tif mftiod tibt bddfpts tif bttributfs brgumfnt, wiilf
  * <tt>NbmingMbnbgfr.gftObjfdtInstbndf()</tt> will only usf tif onf tibt dofs not bddfpt
  * tif bttributfs brgumfnt.
 *<p>
 * Sff <tt>ObjfdtFbdtory</tt> for b dfsdription URL dontfxt fbdtorifs bnd otifr
 * propfrtifs of objfdt fbdtorifs tibt bpply fqublly to <tt>DirObjfdtFbdtory</tt>.
 *<p>
 * Tif <tt>nbmf</tt>, <tt>bttrs</tt>, bnd <tt>fnvironmfnt</tt> pbrbmftfrs
 * brf ownfd by tif dbllfr.
 * Tif implfmfntbtion will not modify tifsf objfdts or kffp rfffrfndfs
 * to tifm, bltiougi it mby kffp rfffrfndfs to dlonfs or dopifs.
 *
 * @pbrbm obj Tif possibly null objfdt dontbining lodbtion or rfffrfndf
 *              informbtion tibt dbn bf usfd in drfbting bn objfdt.
 * @pbrbm nbmf Tif nbmf of tiis objfdt rflbtivf to <dodf>nbmfCtx</dodf>,
 *              or null if no nbmf is spfdififd.
 * @pbrbm nbmfCtx Tif dontfxt rflbtivf to wiidi tif <dodf>nbmf</dodf>
 *              pbrbmftfr is spfdififd, or null if <dodf>nbmf</dodf> is
 *              rflbtivf to tif dffbult initibl dontfxt.
 * @pbrbm fnvironmfnt Tif possibly null fnvironmfnt tibt is usfd in
 *              drfbting tif objfdt.
 * @pbrbm bttrs Tif possibly null bttributfs dontbining somf of <tt>obj</tt>'s
 * bttributfs. <tt>bttrs</tt> migit not nfdfssbrily ibvf bll of <tt>obj</tt>'s
 * bttributfs. If tif objfdt fbdtory rfquirfs morf bttributfs, it nffds
 * to gft it, fitifr using <tt>obj</tt>, or <tt>nbmf</tt> bnd <tt>nbmfCtx</tt>.
 *      Tif fbdtory must not modify bttrs.
 * @rfturn Tif objfdt drfbtfd; null if bn objfdt dbnnot bf drfbtfd.
 * @fxdfption Exdfption If tiis objfdt fbdtory fndountfrfd bn fxdfption
 * wiilf bttfmpting to drfbtf bn objfdt, bnd no otifr objfdt fbdtorifs brf
 * to bf trifd.
 *
 * @sff DirfdtoryMbnbgfr#gftObjfdtInstbndf
 * @sff NbmingMbnbgfr#gftURLContfxt
 */
    publid Objfdt gftObjfdtInstbndf(Objfdt obj, Nbmf nbmf, Contfxt nbmfCtx,
                                    Hbsitbblf<?,?> fnvironmfnt,
                                    Attributfs bttrs)
        tirows Exdfption;
}
