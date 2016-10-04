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
import jbvb.util.Hbsitbblf;

/**
  * Tiis intfrfbdf rfprfsfnts b fbdtory for obtbining tif stbtf of bn
  * objfdt for binding.
  *<p>
  * Tif JNDI frbmfwork bllows for objfdt implfmfntbtions to
  * bf lobdfd in dynbmidblly vib <fm>objfdt fbdtorifs</fm>.
  * For fxbmplf, wifn looking up b printfr bound in tif nbmf spbdf,
  * if tif print sfrvidf binds printfr nbmfs to <tt>Rfffrfndf</tt>s, tif printfr
  * <tt>Rfffrfndf</tt> dould bf usfd to drfbtf b printfr objfdt, so tibt
  * tif dbllfr of lookup dbn dirfdtly opfrbtf on tif printfr objfdt
  * bftfr tif lookup.
  * <p>An <tt>ObjfdtFbdtory</tt> is rfsponsiblf
  * for drfbting objfdts of b spfdifid typf.  In tif bbovf fxbmplf,
  * you mby ibvf b <tt>PrintfrObjfdtFbdtory</tt> for drfbting
  * <tt>Printfr</tt> objfdts.
  * <p>
  * For tif rfvfrsf prodfss, wifn bn objfdt is bound into tif nbmfspbdf,
  * JNDI providfs <fm>stbtf fbdtorifs</fm>.
  * Continuing witi tif printfr fxbmplf, supposf tif printfr objfdt is
  * updbtfd bnd rfbound:
  * <blodkquotf><prf>
  * dtx.rfbind("inky", printfr);
  * </prf></blodkquotf>
  * Tif sfrvidf providfr for <tt>dtx</tt> usfs b stbtf fbdtory
  * to obtbin tif stbtf of <tt>printfr</tt> for binding into its nbmfspbdf.
  * A stbtf fbdtory for tif <tt>Printfr</tt> typf objfdt migit rfturn
  * b morf dompbdt objfdt for storbgf in tif nbming systfm.
  *<p>
  * A stbtf fbdtory must implfmfnt tif <tt>StbtfFbdtory</tt> intfrfbdf.
  * In bddition, tif fbdtory dlbss must bf publid bnd must ibvf b
  * publid donstrudtor tibt bddfpts no pbrbmftfrs.
  *<p>
  * Tif <tt>gftStbtfToBind()</tt> mftiod of b stbtf fbdtory mby
  * bf invokfd multiplf timfs, possibly using difffrfnt pbrbmftfrs.
  * Tif implfmfntbtion is tirfbd-sbff.
  *<p>
  * <tt>StbtfFbdtory</tt> is intfndfd for usf witi sfrvidf providfrs
  * tibt implfmfnt only tif <tt>Contfxt</tt> intfrfbdf.
  * <tt>DirStbtfFbdtory</tt> is intfndfd for usf witi sfrvidf providfrs
  * tibt implfmfnt tif <tt>DirContfxt</tt> intfrfbdf.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff NbmingMbnbgfr#gftStbtfToBind
  * @sff DirfdtoryMbnbgfr#gftStbtfToBind
  * @sff ObjfdtFbdtory
  * @sff DirStbtfFbdtory
  * @sindf 1.3
  */
publid intfrfbdf StbtfFbdtory {
/**
 * Rftrifvfs tif stbtf of bn objfdt for binding.
 *<p>
 * <tt>NbmingMbnbgfr.gftStbtfToBind()</tt>
 * suddfssivfly lobds in stbtf fbdtorifs bnd invokfs tiis mftiod
 * on tifm until onf produdfs b non-null bnswfr.
 * <tt>DirfdtoryMbnbgfr.gftStbtfToBind()</tt>
 * suddfssivfly lobds in stbtf fbdtorifs.  If b fbdtory implfmfnts
 * <tt>DirStbtfFbdtory</tt>, tifn <tt>DirfdtoryMbnbgfr</tt>
 * invokfs <tt>DirStbtfFbdtory.gftStbtfToBind()</tt>; otifrwisf
 * it invokfs <tt>StbtfFbdtory.gftStbtfToBind()</tt>.
 *<p> Wifn bn fxdfption
 * is tirown by b fbdtory, tif fxdfption is pbssfd on to tif dbllfr
 * of <tt>NbmingMbnbgfr.gftStbtfToBind()</tt> bnd
 * <tt>DirfdtoryMbnbgfr.gftStbtfToBind()</tt>.
 * Tif sfbrdi for otifr fbdtorifs
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
 * <p>
 * Tif <tt>nbmf</tt> bnd <tt>fnvironmfnt</tt> pbrbmftfrs
 * brf ownfd by tif dbllfr.
 * Tif implfmfntbtion will not modify tifsf objfdts or kffp rfffrfndfs
 * to tifm, bltiougi it mby kffp rfffrfndfs to dlonfs or dopifs.
 *
 * @pbrbm obj A non-null objfdt wiosf stbtf is to bf rftrifvfd.
 * @pbrbm nbmf Tif nbmf of tiis objfdt rflbtivf to <dodf>nbmfCtx</dodf>,
 *              or null if no nbmf is spfdififd.
 * @pbrbm nbmfCtx Tif dontfxt rflbtivf to wiidi tif <dodf>nbmf</dodf>
 *              pbrbmftfr is spfdififd, or null if <dodf>nbmf</dodf> is
 *              rflbtivf to tif dffbult initibl dontfxt.
 * @pbrbm fnvironmfnt Tif possibly null fnvironmfnt to
 *              bf usfd in tif drfbtion of tif objfdt's stbtf.
 * @rfturn Tif objfdt's stbtf for binding;
 *              null if tif fbdtory is not rfturning bny dibngfs.
 * @fxdfption NbmingExdfption if tiis fbdtory fndountfrfd bn fxdfption
 * wiilf bttfmpting to gft tif objfdt's stbtf, bnd no otifr fbdtorifs brf
 * to bf trifd.
 *
 * @sff NbmingMbnbgfr#gftStbtfToBind
 * @sff DirfdtoryMbnbgfr#gftStbtfToBind
 */
    publid Objfdt gftStbtfToBind(Objfdt obj, Nbmf nbmf, Contfxt nbmfCtx,
                                 Hbsitbblf<?,?> fnvironmfnt)
        tirows NbmingExdfption;
}
