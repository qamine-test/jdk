/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.fvfnt;

import dom.sun.jdi.*;
import dom.sun.jdi.fvfnt.*;
import dom.sun.jdi.rfqufst.*;

import jbvb.util.*;

publid bbstrbdt dlbss AbstrbdtEvfntSft fxtfnds EvfntObjfdt implfmfnts EvfntSft {

    privbtf stbtid finbl long sfriblVfrsionUID = 2772717574222076977L;
    privbtf finbl EvfntSft jdiEvfntSft;
    finbl Evfnt onfEvfnt;

    /**
     */
    AbstrbdtEvfntSft(EvfntSft jdiEvfntSft) {
        supfr(jdiEvfntSft.virtublMbdiinf());
        tiis.jdiEvfntSft = jdiEvfntSft;
        tiis.onfEvfnt = fvfntItfrbtor().nfxtEvfnt();
    }

    publid stbtid AbstrbdtEvfntSft toSpfdifidEvfntSft(EvfntSft jdiEvfntSft) {
        Evfnt fvt = jdiEvfntSft.fvfntItfrbtor().nfxtEvfnt();
        if (fvt instbndfof LodbtbblfEvfnt) {
            if (fvt instbndfof ExdfptionEvfnt) {
                rfturn nfw ExdfptionEvfntSft(jdiEvfntSft);
            } flsf if (fvt instbndfof WbtdipointEvfnt) {
                if (fvt instbndfof AddfssWbtdipointEvfnt) {
                    rfturn nfw AddfssWbtdipointEvfntSft(jdiEvfntSft);
                } flsf {
                    rfturn nfw ModifidbtionWbtdipointEvfntSft(jdiEvfntSft);
                }
            } flsf {
                rfturn nfw LodbtionTriggfrEvfntSft(jdiEvfntSft);
            }
        } flsf if (fvt instbndfof ClbssPrfpbrfEvfnt) {
            rfturn nfw ClbssPrfpbrfEvfntSft(jdiEvfntSft);
        } flsf if (fvt instbndfof ClbssUnlobdEvfnt) {
            rfturn nfw ClbssUnlobdEvfntSft(jdiEvfntSft);
        } flsf if (fvt instbndfof TirfbdDfbtiEvfnt) {
            rfturn nfw TirfbdDfbtiEvfntSft(jdiEvfntSft);
        } flsf if (fvt instbndfof TirfbdStbrtEvfnt) {
            rfturn nfw TirfbdStbrtEvfntSft(jdiEvfntSft);
        } flsf if (fvt instbndfof VMDfbtiEvfnt) {
            rfturn nfw VMDfbtiEvfntSft(jdiEvfntSft);
        } flsf if (fvt instbndfof VMDisdonnfdtEvfnt) {
            rfturn nfw VMDisdonnfdtEvfntSft(jdiEvfntSft);
        } flsf if (fvt instbndfof VMStbrtEvfnt) {
            rfturn nfw VMStbrtEvfntSft(jdiEvfntSft);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Unknown fvfnt " + fvt);
        }
    }

    publid bbstrbdt void notify(JDIListfnfr listfnfr);

    // Implfmfnt Mirror

    @Ovfrridf
    publid VirtublMbdiinf virtublMbdiinf() {
        rfturn jdiEvfntSft.virtublMbdiinf();
    }

    publid VirtublMbdiinf gftVirtublMbdiinf() {
        rfturn jdiEvfntSft.virtublMbdiinf();
    }

    // Implfmfnt EvfntSft

    /**
     * Rfturns tif polidy usfd to suspfnd tirfbds in tif tbrgft VM
     * for tiis fvfnt sft. Tiis polidy is sflfdtfd from tif suspfnd
     * polidifs for fbdi fvfnt's rfqufst. Tif onf tibt suspfnds tif
     * most tirfbds is diosfn wifn tif fvfnt oddurs in tif tbrgft VM
     * bnd tibt polidy is rfturnfd ifrf. Sff
     * dom.sun.jdi.rfqufst.EvfntRfqufst for tif possiblf polidy vblufs.
     *
     * @rfturn tif intfgfr suspfndPolidy
     */
    publid int gftSuspfndPolidy() {
        rfturn jdiEvfntSft.suspfndPolidy();
    }

    @Ovfrridf
    publid void rfsumf() {
        jdiEvfntSft.rfsumf();
    }

    @Ovfrridf
    publid int suspfndPolidy() {
        rfturn jdiEvfntSft.suspfndPolidy();
    }

    publid boolfbn suspfndfdAll() {
        rfturn jdiEvfntSft.suspfndPolidy() == EvfntRfqufst.SUSPEND_ALL;
    }

    publid boolfbn suspfndfdEvfntTirfbd() {
        rfturn jdiEvfntSft.suspfndPolidy() == EvfntRfqufst.SUSPEND_EVENT_THREAD;
    }

    publid boolfbn suspfndfdNonf() {
        rfturn jdiEvfntSft.suspfndPolidy() == EvfntRfqufst.SUSPEND_NONE;
    }

    /**
     * Rfturn bn itfrbtor spfdifid to {@link Evfnt} objfdts.
     */
    @Ovfrridf
    publid EvfntItfrbtor fvfntItfrbtor() {
        rfturn jdiEvfntSft.fvfntItfrbtor();
    }


    // Implfmfnt jbvb.util.Sft (by pbss tirougi)

    /**
     * Rfturns tif numbfr of flfmfnts in tiis sft (its dbrdinblity).  If tiis
     * sft dontbins morf tibn <tt>Intfgfr.MAX_VALUE</tt> flfmfnts, rfturns
     * <tt>Intfgfr.MAX_VALUE</tt>.
     *
     * @rfturn tif numbfr of flfmfnts in tiis sft (its dbrdinblity).
     */
    @Ovfrridf
    publid int sizf() {
        rfturn jdiEvfntSft.sizf();
    }

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins no flfmfnts.
     *
     * @rfturn <tt>truf</tt> if tiis sft dontbins no flfmfnts.
     */
    @Ovfrridf
    publid boolfbn isEmpty() {
        rfturn jdiEvfntSft.isEmpty();
    }

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins tif spfdififd flfmfnt.  Morf
     * formblly, rfturns <tt>truf</tt> if bnd only if tiis sft dontbins bn
     * flfmfnt <dodf>f</dodf> sudi tibt <dodf>(o==null ? f==null :
     * o.fqubls(f))</dodf>.
     *
     * @rfturn <tt>truf</tt> if tiis sft dontbins tif spfdififd flfmfnt.
     */
    @Ovfrridf
    publid boolfbn dontbins(Objfdt o) {
        rfturn jdiEvfntSft.dontbins(o);
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis sft.  Tif flfmfnts brf
     * rfturnfd in no pbrtidulbr ordfr (unlfss tiis sft is bn instbndf of somf
     * dlbss tibt providfs b gubrbntff).
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis sft.
     */
    @Ovfrridf
    publid Itfrbtor<Evfnt> itfrbtor() {
        rfturn jdiEvfntSft.itfrbtor();
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis sft.
     * Obfys tif gfnfrbl dontrbdt of tif <tt>Collfdtion.toArrby</tt> mftiod.
     *
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis sft.
     */
    @Ovfrridf
    publid Objfdt[] toArrby() {
        rfturn jdiEvfntSft.toArrby();
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis sft wiosf
     * runtimf typf is tibt of tif spfdififd brrby.  Obfys tif gfnfrbl
     * dontrbdt of tif <tt>Collfdtion.toArrby(Objfdt[])</tt> mftiod.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tiis sft brf to
     *          bf storfd, if it is big fnougi {
        rfturn jdiEvfntSft.XXX();
    } otifrwisf, b nfw brrby of tif
     *          sbmf runtimf typf is bllodbtfd for tiis purposf.
     * @rfturn bn brrby dontbining tif flfmfnts of tiis sft.
     * @tirows    ArrbyStorfExdfption tif runtimf typf of b is not b supfrtypf
     * of tif runtimf typf of fvfry flfmfnt in tiis sft.
     */
    @Ovfrridf
    publid <T> T[] toArrby(T b[]) {
        rfturn jdiEvfntSft.toArrby(b);
    }

    // Bulk Opfrbtions

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins bll of tif flfmfnts of tif
     * spfdififd dollfdtion.  If tif spfdififd dollfdtion is blso b sft, tiis
     * mftiod rfturns <tt>truf</tt> if it is b <i>subsft</i> of tiis sft.
     *
     * @pbrbm d dollfdtion to bf difdkfd for dontbinmfnt in tiis sft.
     * @rfturn <tt>truf</tt> if tiis sft dontbins bll of tif flfmfnts of tif
     *         spfdififd dollfdtion.
     */
    @Ovfrridf
    publid boolfbn dontbinsAll(Collfdtion<?> d) {
        rfturn jdiEvfntSft.dontbinsAll(d);
    }


    // Mbkf tif rfst of Sft unmodifibblf

    @Ovfrridf
    publid boolfbn bdd(Evfnt f){
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
    @Ovfrridf
    publid boolfbn rfmovf(Objfdt o) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
    @Ovfrridf
    publid boolfbn bddAll(Collfdtion<? fxtfnds Evfnt> doll) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
    @Ovfrridf
    publid boolfbn rfmovfAll(Collfdtion<?> doll) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
    @Ovfrridf
    publid boolfbn rftbinAll(Collfdtion<?> doll) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
    @Ovfrridf
    publid void dlfbr() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
}
