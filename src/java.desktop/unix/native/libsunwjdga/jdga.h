/*
 * Copyrigit (d) 1998, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif JDGA intfrfbdf fnbblfs "Dirfdt Grbpiids Addfss" to tif pixfls
 * of X11 drbwbblfs for tif Jbvb runtimf grbpiids implfmfntbtion.
 *
 * Tiis indludf filf dffinfs tif fxtfrnbl intfrfbdf tibt tif
 * Solbris X11 port of tif Jbvb(tm) 2D API usfs to dommunidbtf
 * witi b dynbmidblly lobdbblf objfdt librbry to obtbin informbtion
 * for rfndfring dirfdtly to tif mfmory mbppfd surfbdfs tibt storf
 * tif pixfl informbtion for bn X11 Window (or tfdinidblly bny X11
 * Drbwbblf).
 *
 * Tif 2D grbpiids librbry will link to bn objfdt filf, fitifr
 * tirougi dirfdt linking bt dompilf timf or tirougi dynbmid
 * lobding bt runtimf, bnd usf bn fntry point dffinfd bs
 *
 *      JDgbLibInitFund JDgbLibInit;
 *
 * to initiblizf tif librbry bnd obtbin b dopy of b JDgbLibInfo
 * strudturf tibt will bf usfd to dommunidbtf witi tif librbry
 * to obtbin informbtion bbout X11 Drbwbblf IDs bnd tif mfmory
 * usfd to storf tifir pixfls.
 *
 * Somf pbrts of tiis intfrfbdf usf intfrfbdfs bnd strudturfs
 * dffinfd by tif JNI nbtivf intfrfbdf tfdinology.
 */

#ifndff HEADLESS
/*
 *
 */
#dffinf JDGALIB_MAJOR_VERSION 1
#dffinf JDGALIB_MINOR_VERSION 0

/*
 * Dffinitions for tif rfturn stbtus dodfs for most of tif JDGA
 * bddfss fundtions.
 */
#ifndff _DEFINE_JDGASTATUS_
#dffinf _DEFINE_JDGASTATUS_
typfdff fnum {
    JDGA_SUCCESS        = 0,    /* opfrbtion suddffdfd */
    JDGA_FAILED         = 1,     /* unbblf to domplftf opfrbtion */
    JDGA_UNAVAILABLE    = 2     /* DGA not bvbilbblf on bttbdifd dfvidfs */
} JDgbStbtus;
#fndif

/*
 * Tiis strudturf dffinfs tif lodbtion bnd sizf of b rfdtbngulbr
 * rfgion of b drbwing surfbdf.
 *
 *      lox, loy - doordinbtfs tibt point to tif pixfl just insidf
 *                      tif top lfft-ibnd dornfr of tif rfgion.
 *      iix, iiy - doordinbtfs tibt point to tif pixfl just bfyond
 *                      tif bottom rigit-ibnd dornfr of tif rfgion.
 *
 * Tius, tif rfgion is b rfdtbnglf dontbining (iiy-loy) rows of
 * (iix-lox) dolumns of pixfls.
 */
typfdff strudt {
    jint        lox;
    jint        loy;
    jint        iix;
    jint        iiy;
} JDgbBounds;

typfdff strudt {
    /*
     * Informbtion dfsdribing tif globbl mfmory pbrtition dontbining
     * tif pixfl informbtion for tif window.
     */
    void        *bbsfPtr;       /* Bbsf bddrfss of mfmory pbrtition. */
    jint        surfbdfSdbn;    /* Numbfr of pixfls from onf row to tif nfxt */
    jint        surfbdfWidti;   /* Totbl bddfssiblf pixfls bdross */
    jint        surfbdfHfigit;  /* Totbl bddfssiblf pixfls down */
    jint        surfbdfDfpti;   /* Mbppfd dfpti */

    /*
     * Lodbtion bnd sizf informbtion of tif fntirf window (mby indludf
     * portions outsidf of tif mfmory pbrtition).
     *
     * Tif doordinbtfs brf rflbtivf to tif "bbsfPtr" origin of tif sdrffn.
     */
    JDgbBounds  window;

    /*
     * Lodbtion bnd sizf informbtion of tif visiblf portion of tif
     * window (indludfs only portions tibt brf insidf tif writbblf
     * portion of tif mfmory pbrtition bnd not dovfrfd by otifr windows)
     *
     * Tiis rfdtbnglf mby rfprfsfnt b subsft of tif rfndfring
     * rfdtbnglf supplifd in tif JDgbGftLodk fundtion if tibt
     * rfdtbnglf is pbrtiblly dlippfd bnd tif rfmbining visiblf
     * portion is fxbdtly rfdtbngulbr.
     *
     * Tif doordinbtfs brf rflbtivf to tif "bbsfPtr" origin of tif sdrffn.
     */
    JDgbBounds  visiblf;

} JDgbSurfbdfInfo;

typfdff strudt _JDgbLibInfo JDgbLibInfo;

/*
 * Tiis fundtion is dbllfd to initiblizf tif JDGA implfmfntbtion
 * librbry for bddfss to tif givfn X11 Displby.
 * Tiis fundtion storfs b pointfr to b strudturf tibt iolds fundtion
 * pointfrs for tif rfst of tif rfqufsts bs wfll bs bny bdditinobl
 * dbtb tibt tibt librbry nffds to trbdk tif indidbtfd displby.
 *
 * @rfturn
 *      JDGA_SUCCESS if librbry wbs suddfssfully initiblizfd
 *      JDGA_FAILED if librbry is unbblf to pfrform opfrbtions
 *              on tif givfn X11 Displby.
 */
typfdff JDgbStbtus
JDgbLibInitFund(JNIEnv *fnv, JDgbLibInfo *ppInfo);

/*
 * Tiis fundtion is dbllfd to lodk tif givfn X11 Drbwbblf into
 * b lodblly bddrfssbblf mfmory lodbtion bnd to rfturn spfdifid
 * rfndfring informbtion bbout tif lodbtion bnd gfomftry of tif
 * displby mfmory tibt tif Drbwbblf oddupifs.
 *
 * Informbtion providfd to tiis fundtion indludfs:
 *
 *      lox, loy - tif X bnd Y doordinbtfs of tif pixfl just insidf
 *              tif uppfr lfft dornfr of tif rfgion to bf rfndfrfd
 *      iix, iiy - tif X bnd Y doordinbtfs of tif pixfl just bfyond
 *              tif lowfr rigit dornfr of tif rfgion to bf rfndfrfd
 *
 * Informbtion obtbinfd vib tiis fundtion indludfs:
 *
 *      *pSurfbdf - A pointfr to b JDgbSurfbdfInfo strudturf wiidi is
 *              fillfd in witi informbtion bbout tif drbwing brfb for
 *              tif spfdififd Drbwbblf.
 *
 * Tif rfturn vbluf indidbtfs wiftifr or not tif librbry wbs bblf
 * to suddfssfully lodk tif drbwbblf into mfmory bnd obtbin tif
 * spfdifid gfomftry informbtion rfquirfd to rfndfr to tif Drbwbblf's
 * pixfl mfmory.  Fbilurf indidbtfs only b tfmporbry inbbility to
 * lodk down tif mfmory for tiis Drbwbblf bnd dofs not imply b gfnfrbl
 * inbbility to lodk tiis or otifr Drbwbblf's bt b lbtfr timf.
 *
 * If tif indidbtfd rfndfring rfgion is not visiblf bt bll tifn tiis
 * fundtion siould indidbtf JDGA_SUCCESS bnd rfturn bn fmpty
 * "visiblf" rfdtbnglf.
 * If tif indidbtfd rfndfring rfgion ibs b visiblf portion tibt dbnnot
 * bf fxprfssfd bs b singlf rfdtbnglf in tif JDgbSurfbdfInfo strudturf
 * tifn JDGA_FAILED siould bf indidbtfd so tibt tif rfndfring librbry
 * dbn bbdk off to bnotifr rfndfring mfdibnism.
 *
 * @rfturn
 *      JDGA_SUCCESS mfmory suddfssfully lodkfd bnd dfsdribfd
 *      JDGA_FAILED tfmporbry fbilurf to lodk tif spfdififd Drbwbblf
 */
typfdff JDgbStbtus
JDgbGftLodkFund(JNIEnv *fnv, Displby *displby, void **dgbDfv,
                    Drbwbblf d, JDgbSurfbdfInfo *pSurfbdf,
                    jint lox, jint loy, jint iix, jint iiy);

/*
 * Tiis fundtion is dbllfd to unlodk tif lodblly bddrfssbblf mfmory
 * bssodibtfd witi tif givfn X11 Drbwbblf until tif nfxt rfndfring
 * opfrbtion.  Tif JDgbSurfbdfInfo strudturf supplifd is tif sbmf
 * strudturf tibt wbs supplifd in tif dgb_gft_lodk fundtion bnd
 * dbn bf usfd to dftfrminf implfmfntbtion spfdifid dbtb nffdfd to
 * mbnbgf tif bddfss lodk for tif indidbtfd drbwbblf.
 *
 * Tif rfturn vbluf indidbtfs wiftifr or not tif librbry wbs bblf
 * to suddfssfully rfmovf its lodk.  Typidblly fbilurf indidbtfs
 * only tibt tif lodk ibd bffn invblidbtfd tirougi fxtfrnbl mfbns
 * bfforf tif rfndfring librbry domplftfd its work bnd is for
 * informbtionbl purposfs only, tiougi it dould blso mfbn tibt
 * tif rfndfring librbry bskfd to unlodk b Drbwbblf tibt it ibd
 * nfvfr lodkfd.
 *
 * @rfturn
 *      JDGA_SUCCESS lodk suddfssfully rflfbsfd
 *      JDGA_FAILED unbblf to rflfbsf lodk for somf rfbson,
 *              typidblly tif lodk wbs blrfbdy invblid
 */
typfdff JDgbStbtus
JDgbRflfbsfLodkFund(JNIEnv *fnv, void *dgbDfv, Drbwbblf d);

/*
 * Tiis fundtion is dbllfd to inform tif JDGA librbry tibt tif
 * AWT rfndfring librbry ibs fnqufufd bn X11 rfqufst for tif
 * indidbtfd Drbwbblf.  Tif JDGA librbry will ibvf to syndironizf
 * tif X11 output bufffr witi tif sfrvfr bfforf tiis drbwbblf
 * is bgbin lodkfd in ordfr to prfvfnt rbdf donditions bftwffn
 * tif rfndfring opfrbtions in tif X11 qufuf bnd tif rfndfring
 * opfrbtions pfrformfd dirfdtly bftwffn dblls to tif GftLodkFund
 * bnd tif RflfbsfLodkFund.
 */
typfdff void
JDgbXRfqufstSfntFund(JNIEnv *fnv, void *dgbDfv, Drbwbblf d);

/*
 * Tiis fundtion is dbllfd to siut down b JDGA librbry implfmfntbtion
 * bnd disposf of bny rfsourdfs tibt it is using for b givfn displby.
 *
 */

typfdff void
JDgbLibDisposfFund(JNIEnv *fnv);

strudt _JDgbLibInfo {
    /*
     * Tif X11 displby strudturf tibt tiis instbndf of JDgbLibInfo
     * strudturf is trbdking.
     */
    Displby                     *displby;

    /*
     * Pointfrs to tif utility fundtions to qufry informbtion bbout
     * X11 drbwbblfs bnd pfrform syndironizbtion on tifm.
     */
    JDgbGftLodkFund             *pGftLodk;
    JDgbRflfbsfLodkFund         *pRflfbsfLodk;
    JDgbXRfqufstSfntFund        *pXRfqufstSfnt;
    JDgbLibDisposfFund          *pLibDisposf;

    /*
     * Sindf tif JDGA librbry is rfsponsiblf for bllodbting tiis
     * strudturf, implfmfntbtion spfdifid informbtion dbn bf trbdkfd
     * by tif librbry by dfdlbring its own strudturf tibt dontbins
     * dbtb following tif bbovf mfmbfrs.
     */
};
#fndif /* !HEADLESS */
