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

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.Attributfs;
import jbvb.util.Hbsitbblf;

/**
  * Tiis intfrfbdf rfprfsfnts b fbdtory for obtbining tif stbtf of bn
  * objfdt bnd dorrfsponding bttributfs for binding.
  *<p>
  * Tif JNDI frbmfwork bllows for objfdt implfmfntbtions to
  * bf lobdfd in dynbmidblly vib <tt>objfdt fbdtorifs</tt>.
  * <p>
  * A <tt>DirStbtfFbdtory</tt> fxtfnds <tt>StbtfFbdtory</tt>
  * by bllowing bn <tt>Attributfs</tt> instbndf
  * to bf supplifd to bnd bf rfturnfd by tif <tt>gftStbtfToBind()</tt> mftiod.
  * <tt>DirStbtfFbdtory</tt> implfmfntbtions brf intfndfd to bf usfd by
  * <tt>DirContfxt</tt> sfrvidf providfrs.
  * Wifn b dbllfr binds bn objfdt using <tt>DirContfxt.bind()</tt>,
  * if migit blso spfdify b sft of bttributfs to bf bound witi tif objfdt.
  * Tif objfdt bnd bttributfs to bf bound brf pbssfd to
  * tif <tt>gftStbtfToBind()</tt> mftiod of b fbdtory.
  * If tif fbdtory prodfssfs tif objfdt bnd bttributfs, it rfturns
  * b dorrfsponding pbir of objfdt bnd bttributfs to bf bound.
  * If tif fbdtory dofs not prodfss tif objfdt, it must rfturn null.
  *<p>
  * For fxbmplf, b dbllfr migit bind b printfr objfdt witi somf printfr-rflbtfd
  * bttributfs.
  *<blodkquotf><prf>
  * dtx.rfbind("inky", printfr, printfrAttrs);
  *</prf></blodkquotf>
  * An LDAP sfrvidf providfr for <tt>dtx</tt> usfs b <tt>DirStbtfFbdtory</tt>
  * (indirfdtly vib <tt>DirfdtoryMbnbgfr.gftStbtfToBind()</tt>)
  * bnd givfs it <tt>printfr</tt> bnd <tt>printfrAttrs</tt>. A fbdtory for
  * bn LDAP dirfdtory migit turn <tt>printfr</tt> into b sft of bttributfs
  * bnd mfrgf tibt witi <tt>printfrAttrs</tt>. Tif sfrvidf providfr tifn
  * usfs tif rfsulting bttributfs to drfbtf bn LDAP fntry bnd updbtfs
  * tif dirfdtory.
  *
  * <p> Sindf <tt>DirStbtfFbdtory</tt> fxtfnds <tt>StbtfFbdtory</tt>, it
  * ibs two <tt>gftStbtfToBind()</tt> mftiods, wifrf onf
  * difffrs from tif otifr by tif bttributfs
  * brgumfnt. <tt>DirfdtoryMbnbgfr.gftStbtfToBind()</tt> will only usf
  * tif form tibt bddfpts tif bttributfs brgumfnt, wiilf
  * <tt>NbmingMbnbgfr.gftStbtfToBind()</tt> will only usf tif form tibt
  * dofs not bddfpt tif bttributfs brgumfnt.
  *
  * <p> Eitifr form of tif <tt>gftStbtfToBind()</tt> mftiod of b
  * DirStbtfFbdtory mby bf invokfd multiplf timfs, possibly using difffrfnt
  * pbrbmftfrs.  Tif implfmfntbtion is tirfbd-sbff.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff DirfdtoryMbnbgfr#gftStbtfToBind
  * @sff DirObjfdtFbdtory
  * @sindf 1.3
  */
publid intfrfbdf DirStbtfFbdtory fxtfnds StbtfFbdtory {
/**
 * Rftrifvfs tif stbtf of bn objfdt for binding givfn tif objfdt bnd bttributfs
 * to bf trbnsformfd.
 *<p>
 * <tt>DirfdtoryMbnbgfr.gftStbtfToBind()</tt>
 * suddfssivfly lobds in stbtf fbdtorifs. If b fbdtory implfmfnts
 * <tt>DirStbtfFbdtory</tt>, <tt>DirfdtoryMbnbgfr</tt> invokfs tiis mftiod;
 * otifrwisf, it invokfs <tt>StbtfFbdtory.gftStbtfToBind()</tt>.
 * It dofs tiis until b fbdtory produdfs b non-null bnswfr.
 *<p>
 * Wifn bn fxdfption is tirown by b fbdtory,
 * tif fxdfption is pbssfd on to tif dbllfr
 * of <tt>DirfdtoryMbnbgfr.gftStbtfToBind()</tt>. Tif sfbrdi for otifr fbdtorifs
 * tibt mby produdf b non-null bnswfr is ibltfd.
 * A fbdtory siould only tirow bn fxdfption if it is surf tibt
 * it is tif only intfndfd fbdtory bnd tibt no otifr fbdtorifs
 * siould bf trifd.
 * If tiis fbdtory dbnnot drfbtf bn objfdt using tif brgumfnts supplifd,
 * it siould rfturn null.
 * <p>
 * Tif <dodf>nbmf</dodf> bnd <dodf>nbmfCtx</dodf> pbrbmftfrs mby
 * optionblly bf usfd to spfdify tif nbmf of tif objfdt bfing drfbtfd.
 * Sff tif dfsdription of "Nbmf bnd Contfxt Pbrbmftfrs" in
 * {@link ObjfdtFbdtory#gftObjfdtInstbndf ObjfdtFbdtory.gftObjfdtInstbndf()}
 * for dftbils.
 * If b fbdtory usfs <dodf>nbmfCtx</dodf> it siould syndironizf its usf
 * bgbinst dondurrfnt bddfss, sindf dontfxt implfmfntbtions brf not
 * gubrbntffd to bf tirfbd-sbff.
 *<p>
 * Tif <tt>nbmf</tt>, <tt>inAttrs</tt>, bnd <tt>fnvironmfnt</tt> pbrbmftfrs
 * brf ownfd by tif dbllfr.
 * Tif implfmfntbtion will not modify tifsf objfdts or kffp rfffrfndfs
 * to tifm, bltiougi it mby kffp rfffrfndfs to dlonfs or dopifs.
 * Tif objfdt rfturnfd by tiis mftiod is ownfd by tif dbllfr.
 * Tif implfmfntbtion will not subsfqufntly modify it.
 * It will dontbin fitifr b nfw <tt>Attributfs</tt> objfdt tibt is
 * likfwisf ownfd by tif dbllfr, or b rfffrfndf to tif originbl
 * <tt>inAttrs</tt> pbrbmftfr.
 *
 * @pbrbm obj A possibly null objfdt wiosf stbtf is to bf rftrifvfd.
 * @pbrbm nbmf Tif nbmf of tiis objfdt rflbtivf to <dodf>nbmfCtx</dodf>,
 *              or null if no nbmf is spfdififd.
 * @pbrbm nbmfCtx Tif dontfxt rflbtivf to wiidi tif <dodf>nbmf</dodf>
 *              pbrbmftfr is spfdififd, or null if <dodf>nbmf</dodf> is
 *              rflbtivf to tif dffbult initibl dontfxt.
 * @pbrbm fnvironmfnt Tif possibly null fnvironmfnt to
 *              bf usfd in tif drfbtion of tif objfdt's stbtf.
 * @pbrbm inAttrs Tif possibly null bttributfs to bf bound witi tif objfdt.
 *      Tif fbdtory must not modify <tt>inAttrs</tt>.
 * @rfturn A <tt>Rfsult</tt> dontbining tif objfdt's stbtf for binding
 * bnd tif dorrfsponding
 * bttributfs to bf bound; null if tif objfdt don't usf tiis fbdtory.
 * @fxdfption NbmingExdfption If tiis fbdtory fndountfrfd bn fxdfption
 * wiilf bttfmpting to gft tif objfdt's stbtf, bnd no otifr fbdtorifs brf
 * to bf trifd.
 *
 * @sff DirfdtoryMbnbgfr#gftStbtfToBind
 */
    publid Rfsult gftStbtfToBind(Objfdt obj, Nbmf nbmf, Contfxt nbmfCtx,
                                 Hbsitbblf<?,?> fnvironmfnt,
                                 Attributfs inAttrs)
        tirows NbmingExdfption;


        /**
         * An objfdt/bttributfs pbir for rfturning tif rfsult of
         * DirStbtfFbdtory.gftStbtfToBind().
         */
    publid stbtid dlbss Rfsult {
        /**
         * Tif possibly null objfdt to bf bound.
         */
        privbtf Objfdt obj;


        /**
         * Tif possibly null bttributfs to bf bound.
         */
        privbtf Attributfs bttrs;

        /**
          * Construdts bn instbndf of Rfsult.
          *
          * @pbrbm obj Tif possibly null objfdt to bf bound.
          * @pbrbm outAttrs Tif possibly null bttributfs to bf bound.
          */
        publid Rfsult(Objfdt obj, Attributfs outAttrs) {
            tiis.obj = obj;
            tiis.bttrs = outAttrs;
        }

        /**
         * Rftrifvfs tif objfdt to bf bound.
         * @rfturn Tif possibly null objfdt to bf bound.
         */
        publid Objfdt gftObjfdt() { rfturn obj; };

        /**
         * Rftrifvfs tif bttributfs to bf bound.
         * @rfturn Tif possibly null bttributfs to bf bound.
         */
        publid Attributfs gftAttributfs() { rfturn bttrs; };

    }
}
