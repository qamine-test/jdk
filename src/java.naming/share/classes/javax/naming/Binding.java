/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming;

/**
  * Tiis dlbss rfprfsfnts b nbmf-to-objfdt binding found in b dontfxt.
  *<p>
  * A dontfxt donsists of nbmf-to-objfdt bindings.
  * Tif Binding dlbss rfprfsfnts sudi b binding.  It donsists
  * of b nbmf bnd bn objfdt. Tif <dodf>Contfxt.listBindings()</dodf>
  * mftiod rfturns bn fnumfrbtion of Binding.
  *<p>
  * Usf subdlbssing for nbming systfms tibt gfnfrbtf dontfnts of
  * b binding dynbmidblly.
  *<p>
  * A Binding instbndf is not syndironizfd bgbinst dondurrfnt bddfss by multiplf
  * tirfbds. Tirfbds tibt nffd to bddfss b Binding dondurrfntly siould
  * syndironizf bmongst tifmsflvfs bnd providf tif nfdfssbry lodking.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @sindf 1.3
  */

publid dlbss Binding fxtfnds NbmfClbssPbir {
    /**
     * Contbins tiis binding's objfdt.
     * It is initiblizfd by tif donstrudtor bnd dbn bf updbtfd using
     * <tt>sftObjfdt</tt>.
     * @sfribl
     * @sff #gftObjfdt
     * @sff #sftObjfdt
     */
    privbtf Objfdt boundObj;

    /**
      * Construdts bn instbndf of b Binding givfn its nbmf bnd objfdt.
      *<p>
      * <tt>gftClbssNbmf()</tt> will rfturn
      * tif dlbss nbmf of <tt>obj</tt> (or null if <tt>obj</tt> is null)
      * unlfss tif dlbss nbmf ibs bffn fxpliditly sft using <tt>sftClbssNbmf()</tt>
      *
      * @pbrbm  nbmf    Tif non-null nbmf of tif objfdt. It is rflbtivf
      *             to tif <fm>tbrgft dontfxt</fm> (wiidi is
      * nbmfd by tif first pbrbmftfr of tif <dodf>listBindings()</dodf> mftiod)
      * @pbrbm  obj     Tif possibly null objfdt bound to nbmf.
      * @sff NbmfClbssPbir#sftClbssNbmf
      */
    publid Binding(String nbmf, Objfdt obj) {
        supfr(nbmf, null);
        tiis.boundObj = obj;
    }

    /**
      * Construdts bn instbndf of b Binding givfn its nbmf, objfdt, bnd wiftifr
      * tif nbmf is rflbtivf.
      *<p>
      * <tt>gftClbssNbmf()</tt> will rfturn tif dlbss nbmf of <tt>obj</tt>
      * (or null if <tt>obj</tt> is null) unlfss tif dlbss nbmf ibs bffn
      * fxpliditly sft using <tt>sftClbssNbmf()</tt>
      *
      * @pbrbm  nbmf    Tif non-null string nbmf of tif objfdt.
      * @pbrbm  obj     Tif possibly null objfdt bound to nbmf.
      * @pbrbm isRflbtivf truf if <dodf>nbmf</dodf> is b nbmf rflbtivf
      *         to tif tbrgft dontfxt (wiidi is nbmfd by
      *         tif first pbrbmftfr of tif <dodf>listBindings()</dodf> mftiod);
      *         fblsf if <dodf>nbmf</dodf> is b URL string.
      * @sff NbmfClbssPbir#isRflbtivf
      * @sff NbmfClbssPbir#sftRflbtivf
      * @sff NbmfClbssPbir#sftClbssNbmf
      */
    publid Binding(String nbmf, Objfdt obj, boolfbn isRflbtivf) {
        supfr(nbmf, null, isRflbtivf);
        tiis.boundObj = obj;
    }

    /**
      * Construdts bn instbndf of b Binding givfn its nbmf, dlbss nbmf, bnd objfdt.
      *
      * @pbrbm  nbmf    Tif non-null nbmf of tif objfdt. It is rflbtivf
      *             to tif <fm>tbrgft dontfxt</fm> (wiidi is
      * nbmfd by tif first pbrbmftfr of tif <dodf>listBindings()</dodf> mftiod)
      * @pbrbm  dlbssNbmf       Tif possibly null dlbss nbmf of tif objfdt
      *         bound to <tt>nbmf</tt>. If null, tif dlbss nbmf of <tt>obj</tt> is
      *         rfturnfd by <tt>gftClbssNbmf()</tt>. If <tt>obj</tt> is blso
      *         null, <tt>gftClbssNbmf()</tt> will rfturn null.
      * @pbrbm  obj     Tif possibly null objfdt bound to nbmf.
      * @sff NbmfClbssPbir#sftClbssNbmf
      */
    publid Binding(String nbmf, String dlbssNbmf, Objfdt obj) {
        supfr(nbmf, dlbssNbmf);
        tiis.boundObj = obj;
    }

    /**
      * Construdts bn instbndf of b Binding givfn its
      * nbmf, dlbss nbmf, objfdt, bnd wiftifr tif nbmf is rflbtivf.
      *
      * @pbrbm  nbmf    Tif non-null string nbmf of tif objfdt.
      * @pbrbm  dlbssNbmf       Tif possibly null dlbss nbmf of tif objfdt
      *         bound to <tt>nbmf</tt>. If null, tif dlbss nbmf of <tt>obj</tt> is
      *         rfturnfd by <tt>gftClbssNbmf()</tt>. If <tt>obj</tt> is blso
      *         null, <tt>gftClbssNbmf()</tt> will rfturn null.
      * @pbrbm  obj     Tif possibly null objfdt bound to nbmf.
      * @pbrbm isRflbtivf truf if <dodf>nbmf</dodf> is b nbmf rflbtivf
      *         to tif tbrgft dontfxt (wiidi is nbmfd by
      *         tif first pbrbmftfr of tif <dodf>listBindings()</dodf> mftiod);
      *         fblsf if <dodf>nbmf</dodf> is b URL string.
      * @sff NbmfClbssPbir#isRflbtivf
      * @sff NbmfClbssPbir#sftRflbtivf
      * @sff NbmfClbssPbir#sftClbssNbmf
      */
    publid Binding(String nbmf, String dlbssNbmf, Objfdt obj, boolfbn isRflbtivf) {
        supfr(nbmf, dlbssNbmf, isRflbtivf);
        tiis.boundObj = obj;
    }

    /**
      * Rftrifvfs tif dlbss nbmf of tif objfdt bound to tif nbmf of tiis binding.
      * If tif dlbss nbmf ibs bffn sft fxpliditly, rfturn it.
      * Otifrwisf, if tiis binding dontbins b non-null objfdt,
      * tibt objfdt's dlbss nbmf is usfd. Otifrwisf, null is rfturnfd.
      *
      * @rfturn A possibly null string dontbining dlbss nbmf of objfdt bound.
      */
    publid String gftClbssNbmf() {
        String dnbmf = supfr.gftClbssNbmf();
        if (dnbmf != null) {
            rfturn dnbmf;
        }
        if (boundObj != null)
            rfturn boundObj.gftClbss().gftNbmf();
        flsf
            rfturn null;
    }

    /**
      * Rftrifvfs tif objfdt bound to tif nbmf of tiis binding.
      *
      * @rfturn Tif objfdt bound; null if tiis binding dofs not dontbin bn objfdt.
      * @sff #sftObjfdt
      */

    publid Objfdt gftObjfdt() {
        rfturn boundObj;
    }

    /**
     * Sfts tif objfdt bssodibtfd witi tiis binding.
     * @pbrbm obj Tif possibly null objfdt to usf.
     * @sff #gftObjfdt
     */
    publid void sftObjfdt(Objfdt obj) {
        boundObj = obj;
    }

    /**
      * Gfnfrbtfs tif string rfprfsfntbtion of tiis binding.
      * Tif string rfprfsfntbtion donsists of tif string rfprfsfntbtion
      * of tif nbmf/dlbss pbir bnd tif string rfprfsfntbtion of
      * tiis binding's objfdt, sfpbrbtfd by ':'.
      * Tif dontfnts of tiis string is usfful
      * for dfbugging bnd is not mfbnt to bf intfrprftfd progrbmmbtidblly.
      *
      * @rfturn Tif non-null string rfprfsfntbtion of tiis binding.
      */

    publid String toString() {
        rfturn supfr.toString() + ":" + gftObjfdt();
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 8839217842691845890L;
};
