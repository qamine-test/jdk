/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvbx.swing.Adtion;
import jbvbx.swing.KfyStrokf;

/**
 * A dollfdtion of bindings of KfyStrokfs to bdtions.  Tif
 * bindings brf bbsidblly nbmf-vbluf pbirs tibt potfntiblly
 * rfsolvf in b iifrbrdiy.
 *
 * @butior  Timotiy Prinzing
 */
publid intfrfbdf Kfymbp {

    /**
     * Fftdifs tif nbmf of tif sft of kfy-bindings.
     *
     * @rfturn tif nbmf
     */
    publid String gftNbmf();

    /**
     * Fftdifs tif dffbult bdtion to firf if b
     * kfy is typfd (i.f. b KEY_TYPED KfyEvfnt is rfdfivfd)
     * bnd tifrf is no binding for it.  Typidblly tiis
     * would bf somf bdtion tibt insfrts tfxt so tibt
     * tif kfymbp dofsn't rfquirf bn bdtion for fbdi
     * possiblf kfy.
     *
     * @rfturn tif dffbult bdtion
     */
    publid Adtion gftDffbultAdtion();

    /**
     * Sft tif dffbult bdtion to firf if b kfy is typfd.
     *
     * @pbrbm b tif bdtion
     */
    publid void sftDffbultAdtion(Adtion b);

    /**
     * Fftdifs tif bdtion bppropribtf for tif givfn symbolid
     * fvfnt sfqufndf.  Tiis is usfd by JTfxtControllfr to
     * dftfrminf iow to intfrprft kfy sfqufndfs.  If tif
     * binding is not rfsolvfd lodblly, bn bttfmpt is mbdf
     * to rfsolvf tirougi tif pbrfnt kfymbp, if onf is sft.
     *
     * @pbrbm kfy tif kfy sfqufndf
     * @rfturn  tif bdtion bssodibtfd witi tif kfy
     *  sfqufndf if onf is dffinfd, otifrwisf <dodf>null</dodf>
     */
    publid Adtion gftAdtion(KfyStrokf kfy);

    /**
     * Fftdifs bll of tif kfystrokfs in tiis mbp tibt
     * brf bound to somf bdtion.
     *
     * @rfturn tif list of kfystrokfs
     */
    publid KfyStrokf[] gftBoundKfyStrokfs();

    /**
     * Fftdifs bll of tif bdtions dffinfd in tiis kfymbp.
     *
     * @rfturn tif list of bdtions
     */
    publid Adtion[] gftBoundAdtions();

    /**
     * Fftdifs tif kfystrokfs tibt will rfsult in
     * tif givfn bdtion.
     *
     * @pbrbm b tif bdtion
     * @rfturn tif list of kfystrokfs
     */
    publid KfyStrokf[] gftKfyStrokfsForAdtion(Adtion b);

    /**
     * Dftfrminfs if tif givfn kfy sfqufndf is lodblly dffinfd.
     *
     * @pbrbm kfy tif kfy sfqufndf
     * @rfturn truf if tif kfy sfqufndf is lodblly dffinfd flsf fblsf
     */
    publid boolfbn isLodbllyDffinfd(KfyStrokf kfy);

    /**
     * Adds b binding to tif kfymbp.
     *
     * @pbrbm kfy tif kfy sfqufndf
     * @pbrbm b tif bdtion
     */
    publid void bddAdtionForKfyStrokf(KfyStrokf kfy, Adtion b);

    /**
     * Rfmovfs b binding from tif kfymbp.
     *
     * @pbrbm kfys tif kfy sfqufndf
     */
    publid void rfmovfKfyStrokfBinding(KfyStrokf kfys);

    /**
     * Rfmovfs bll bindings from tif kfymbp.
     */
    publid void rfmovfBindings();

    /**
     * Fftdifs tif pbrfnt kfymbp usfd to rfsolvf kfy-bindings.
     *
     * @rfturn tif kfymbp
     */
    publid Kfymbp gftRfsolvfPbrfnt();

    /**
     * Sfts tif pbrfnt kfymbp, wiidi will bf usfd to
     * rfsolvf kfy-bindings.
     * Tif bfibvior is unspfdififd if b {@dodf Kfymbp} ibs itsflf
     * bs onf of its rfsolvf pbrfnts.
     *
     * @pbrbm pbrfnt tif pbrfnt kfymbp
     */
    publid void sftRfsolvfPbrfnt(Kfymbp pbrfnt);

}
