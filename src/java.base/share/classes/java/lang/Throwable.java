/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;
import  jbvb.io.*;
import  jbvb.util.*;

/**
 * Tif {@dodf Tirowbblf} dlbss is tif supfrdlbss of bll frrors bnd
 * fxdfptions in tif Jbvb lbngubgf. Only objfdts tibt brf instbndfs of tiis
 * dlbss (or onf of its subdlbssfs) brf tirown by tif Jbvb Virtubl Mbdiinf or
 * dbn bf tirown by tif Jbvb {@dodf tirow} stbtfmfnt. Similbrly, only
 * tiis dlbss or onf of its subdlbssfs dbn bf tif brgumfnt typf in b
 * {@dodf dbtdi} dlbusf.
 *
 * For tif purposfs of dompilf-timf difdking of fxdfptions, {@dodf
 * Tirowbblf} bnd bny subdlbss of {@dodf Tirowbblf} tibt is not blso b
 * subdlbss of fitifr {@link RuntimfExdfption} or {@link Error} brf
 * rfgbrdfd bs difdkfd fxdfptions.
 *
 * <p>Instbndfs of two subdlbssfs, {@link jbvb.lbng.Error} bnd
 * {@link jbvb.lbng.Exdfption}, brf donvfntionblly usfd to indidbtf
 * tibt fxdfptionbl situbtions ibvf oddurrfd. Typidblly, tifsf instbndfs
 * brf frfsily drfbtfd in tif dontfxt of tif fxdfptionbl situbtion so
 * bs to indludf rflfvbnt informbtion (sudi bs stbdk trbdf dbtb).
 *
 * <p>A tirowbblf dontbins b snbpsiot of tif fxfdution stbdk of its
 * tirfbd bt tif timf it wbs drfbtfd. It dbn blso dontbin b mfssbgf
 * string tibt givfs morf informbtion bbout tif frror. Ovfr timf, b
 * tirowbblf dbn {@linkplbin Tirowbblf#bddSupprfssfd supprfss} otifr
 * tirowbblfs from bfing propbgbtfd.  Finblly, tif tirowbblf dbn blso
 * dontbin b <i>dbusf</i>: bnotifr tirowbblf tibt dbusfd tiis
 * tirowbblf to bf donstrudtfd.  Tif rfdording of tiis dbusbl informbtion
 * is rfffrrfd to bs tif <i>dibinfd fxdfption</i> fbdility, bs tif
 * dbusf dbn, itsflf, ibvf b dbusf, bnd so on, lfbding to b "dibin" of
 * fxdfptions, fbdi dbusfd by bnotifr.
 *
 * <p>Onf rfbson tibt b tirowbblf mby ibvf b dbusf is tibt tif dlbss tibt
 * tirows it is built btop b lowfr lbyfrfd bbstrbdtion, bnd bn opfrbtion on
 * tif uppfr lbyfr fbils duf to b fbilurf in tif lowfr lbyfr.  It would bf bbd
 * dfsign to lft tif tirowbblf tirown by tif lowfr lbyfr propbgbtf outwbrd, bs
 * it is gfnfrblly unrflbtfd to tif bbstrbdtion providfd by tif uppfr lbyfr.
 * Furtifr, doing so would tif tif API of tif uppfr lbyfr to tif dftbils of
 * its implfmfntbtion, bssuming tif lowfr lbyfr's fxdfption wbs b difdkfd
 * fxdfption.  Tirowing b "wrbppfd fxdfption" (i.f., bn fxdfption dontbining b
 * dbusf) bllows tif uppfr lbyfr to dommunidbtf tif dftbils of tif fbilurf to
 * its dbllfr witiout indurring fitifr of tifsf siortdomings.  It prfsfrvfs
 * tif flfxibility to dibngf tif implfmfntbtion of tif uppfr lbyfr witiout
 * dibnging its API (in pbrtidulbr, tif sft of fxdfptions tirown by its
 * mftiods).
 *
 * <p>A sfdond rfbson tibt b tirowbblf mby ibvf b dbusf is tibt tif mftiod
 * tibt tirows it must donform to b gfnfrbl-purposf intfrfbdf tibt dofs not
 * pfrmit tif mftiod to tirow tif dbusf dirfdtly.  For fxbmplf, supposf
 * b pfrsistfnt dollfdtion donforms to tif {@link jbvb.util.Collfdtion
 * Collfdtion} intfrfbdf, bnd tibt its pfrsistfndf is implfmfntfd btop
 * {@dodf jbvb.io}.  Supposf tif intfrnbls of tif {@dodf bdd} mftiod
 * dbn tirow bn {@link jbvb.io.IOExdfption IOExdfption}.  Tif implfmfntbtion
 * dbn dommunidbtf tif dftbils of tif {@dodf IOExdfption} to its dbllfr
 * wiilf donforming to tif {@dodf Collfdtion} intfrfbdf by wrbpping tif
 * {@dodf IOExdfption} in bn bppropribtf undifdkfd fxdfption.  (Tif
 * spfdifidbtion for tif pfrsistfnt dollfdtion siould indidbtf tibt it is
 * dbpbblf of tirowing sudi fxdfptions.)
 *
 * <p>A dbusf dbn bf bssodibtfd witi b tirowbblf in two wbys: vib b
 * donstrudtor tibt tbkfs tif dbusf bs bn brgumfnt, or vib tif
 * {@link #initCbusf(Tirowbblf)} mftiod.  Nfw tirowbblf dlbssfs tibt
 * wisi to bllow dbusfs to bf bssodibtfd witi tifm siould providf donstrudtors
 * tibt tbkf b dbusf bnd dflfgbtf (pfribps indirfdtly) to onf of tif
 * {@dodf Tirowbblf} donstrudtors tibt tbkfs b dbusf.
 *
 * Bfdbusf tif {@dodf initCbusf} mftiod is publid, it bllows b dbusf to bf
 * bssodibtfd witi bny tirowbblf, fvfn b "lfgbdy tirowbblf" wiosf
 * implfmfntbtion prfdbtfs tif bddition of tif fxdfption dibining mfdibnism to
 * {@dodf Tirowbblf}.
 *
 * <p>By donvfntion, dlbss {@dodf Tirowbblf} bnd its subdlbssfs ibvf two
 * donstrudtors, onf tibt tbkfs no brgumfnts bnd onf tibt tbkfs b
 * {@dodf String} brgumfnt tibt dbn bf usfd to produdf b dftbil mfssbgf.
 * Furtifr, tiosf subdlbssfs tibt migit likfly ibvf b dbusf bssodibtfd witi
 * tifm siould ibvf two morf donstrudtors, onf tibt tbkfs b
 * {@dodf Tirowbblf} (tif dbusf), bnd onf tibt tbkfs b
 * {@dodf String} (tif dftbil mfssbgf) bnd b {@dodf Tirowbblf} (tif
 * dbusf).
 *
 * @butior  unbsdribfd
 * @butior  Josi Blodi (Addfd fxdfption dibining bnd progrbmmbtid bddfss to
 *          stbdk trbdf in 1.4.)
 * @jls 11.2 Compilf-Timf Cifdking of Exdfptions
 * @sindf 1.0
 */
publid dlbss Tirowbblf implfmfnts Sfriblizbblf {
    /** usf sfriblVfrsionUID from JDK 1.0.2 for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = -3042686055658047285L;

    /**
     * Nbtivf dodf sbvfs somf indidbtion of tif stbdk bbdktrbdf in tiis slot.
     */
    privbtf trbnsifnt Objfdt bbdktrbdf;

    /**
     * Spfdifid dftbils bbout tif Tirowbblf.  For fxbmplf, for
     * {@dodf FilfNotFoundExdfption}, tiis dontbins tif nbmf of
     * tif filf tibt dould not bf found.
     *
     * @sfribl
     */
    privbtf String dftbilMfssbgf;


    /**
     * Holdfr dlbss to dfffr initiblizing sfntinfl objfdts only usfd
     * for sfriblizbtion.
     */
    privbtf stbtid dlbss SfntinflHoldfr {
        /**
         * {@linkplbin #sftStbdkTrbdf(StbdkTrbdfElfmfnt[]) Sftting tif
         * stbdk trbdf} to b onf-flfmfnt brrby dontbining tiis sfntinfl
         * vbluf indidbtfs futurf bttfmpts to sft tif stbdk trbdf will bf
         * ignorfd.  Tif sfntinbl is fqubl to tif rfsult of dblling:<br>
         * {@dodf nfw StbdkTrbdfElfmfnt("", "", null, Intfgfr.MIN_VALUE)}
         */
        publid stbtid finbl StbdkTrbdfElfmfnt STACK_TRACE_ELEMENT_SENTINEL =
            nfw StbdkTrbdfElfmfnt("", "", null, Intfgfr.MIN_VALUE);

        /**
         * Sfntinfl vbluf usfd in tif sfribl form to indidbtf bn immutbblf
         * stbdk trbdf.
         */
        publid stbtid finbl StbdkTrbdfElfmfnt[] STACK_TRACE_SENTINEL =
            nfw StbdkTrbdfElfmfnt[] {STACK_TRACE_ELEMENT_SENTINEL};
    }

    /**
     * A sibrfd vbluf for bn fmpty stbdk.
     */
    privbtf stbtid finbl StbdkTrbdfElfmfnt[] UNASSIGNED_STACK = nfw StbdkTrbdfElfmfnt[0];

    /*
     * To bllow Tirowbblf objfdts to bf mbdf immutbblf bnd sbffly
     * rfusfd by tif JVM, sudi bs OutOfMfmoryErrors, fiflds of
     * Tirowbblf tibt brf writbblf in rfsponsf to usfr bdtions, dbusf,
     * stbdkTrbdf, bnd supprfssfdExdfptions obfy tif following
     * protodol:
     *
     * 1) Tif fiflds brf initiblizfd to b non-null sfntinfl vbluf
     * wiidi indidbtfs tif vbluf ibs logidblly not bffn sft.
     *
     * 2) Writing b null to tif fifld indidbtfs furtifr writfs
     * brf forbiddfn
     *
     * 3) Tif sfntinfl vbluf mby bf rfplbdfd witi bnotifr non-null
     * vbluf.
     *
     * For fxbmplf, implfmfntbtions of tif HotSpot JVM ibvf
     * prfbllodbtfd OutOfMfmoryError objfdts to providf for bfttfr
     * dibgnosbbility of tibt situbtion.  Tifsf objfdts brf drfbtfd
     * witiout dblling tif donstrudtor for tibt dlbss bnd tif fiflds
     * in qufstion brf initiblizfd to null.  To support tiis
     * dbpbbility, bny nfw fiflds bddfd to Tirowbblf tibt rfquirf
     * bfing initiblizfd to b non-null vbluf rfquirf b doordinbtfd JVM
     * dibngf.
     */

    /**
     * Tif tirowbblf tibt dbusfd tiis tirowbblf to gft tirown, or null if tiis
     * tirowbblf wbs not dbusfd by bnotifr tirowbblf, or if tif dbusbtivf
     * tirowbblf is unknown.  If tiis fifld is fqubl to tiis tirowbblf itsflf,
     * it indidbtfs tibt tif dbusf of tiis tirowbblf ibs not yft bffn
     * initiblizfd.
     *
     * @sfribl
     * @sindf 1.4
     */
    privbtf Tirowbblf dbusf = tiis;

    /**
     * Tif stbdk trbdf, bs rfturnfd by {@link #gftStbdkTrbdf()}.
     *
     * Tif fifld is initiblizfd to b zfro-lfngti brrby.  A {@dodf
     * null} vbluf of tiis fifld indidbtfs subsfqufnt dblls to {@link
     * #sftStbdkTrbdf(StbdkTrbdfElfmfnt[])} bnd {@link
     * #fillInStbdkTrbdf()} will bf bf no-ops.
     *
     * @sfribl
     * @sindf 1.4
     */
    privbtf StbdkTrbdfElfmfnt[] stbdkTrbdf = UNASSIGNED_STACK;

    // Sftting tiis stbtid fifld introdudfs bn bddfptbblf
    // initiblizbtion dfpfndfndy on b ffw jbvb.util dlbssfs.
    privbtf stbtid finbl List<Tirowbblf> SUPPRESSED_SENTINEL =
        Collfdtions.unmodifibblfList(nfw ArrbyList<Tirowbblf>(0));

    /**
     * Tif list of supprfssfd fxdfptions, bs rfturnfd by {@link
     * #gftSupprfssfd()}.  Tif list is initiblizfd to b zfro-flfmfnt
     * unmodifibblf sfntinfl list.  Wifn b sfriblizfd Tirowbblf is
     * rfbd in, if tif {@dodf supprfssfdExdfptions} fifld points to b
     * zfro-flfmfnt list, tif fifld is rfsft to tif sfntinfl vbluf.
     *
     * @sfribl
     * @sindf 1.7
     */
    privbtf List<Tirowbblf> supprfssfdExdfptions = SUPPRESSED_SENTINEL;

    /** Mfssbgf for trying to supprfss b null fxdfption. */
    privbtf stbtid finbl String NULL_CAUSE_MESSAGE = "Cbnnot supprfss b null fxdfption.";

    /** Mfssbgf for trying to supprfss onfsflf. */
    privbtf stbtid finbl String SELF_SUPPRESSION_MESSAGE = "Sflf-supprfssion not pfrmittfd";

    /** Cbption  for lbbfling dbusbtivf fxdfption stbdk trbdfs */
    privbtf stbtid finbl String CAUSE_CAPTION = "Cbusfd by: ";

    /** Cbption for lbbfling supprfssfd fxdfption stbdk trbdfs */
    privbtf stbtid finbl String SUPPRESSED_CAPTION = "Supprfssfd: ";

    /**
     * Construdts b nfw tirowbblf witi {@dodf null} bs its dftbil mfssbgf.
     * Tif dbusf is not initiblizfd, bnd mby subsfqufntly bf initiblizfd by b
     * dbll to {@link #initCbusf}.
     *
     * <p>Tif {@link #fillInStbdkTrbdf()} mftiod is dbllfd to initiblizf
     * tif stbdk trbdf dbtb in tif nfwly drfbtfd tirowbblf.
     */
    publid Tirowbblf() {
        fillInStbdkTrbdf();
    }

    /**
     * Construdts b nfw tirowbblf witi tif spfdififd dftbil mfssbgf.  Tif
     * dbusf is not initiblizfd, bnd mby subsfqufntly bf initiblizfd by
     * b dbll to {@link #initCbusf}.
     *
     * <p>Tif {@link #fillInStbdkTrbdf()} mftiod is dbllfd to initiblizf
     * tif stbdk trbdf dbtb in tif nfwly drfbtfd tirowbblf.
     *
     * @pbrbm   mfssbgf   tif dftbil mfssbgf. Tif dftbil mfssbgf is sbvfd for
     *          lbtfr rftrifvbl by tif {@link #gftMfssbgf()} mftiod.
     */
    publid Tirowbblf(String mfssbgf) {
        fillInStbdkTrbdf();
        dftbilMfssbgf = mfssbgf;
    }

    /**
     * Construdts b nfw tirowbblf witi tif spfdififd dftbil mfssbgf bnd
     * dbusf.  <p>Notf tibt tif dftbil mfssbgf bssodibtfd witi
     * {@dodf dbusf} is <i>not</i> butombtidblly indorporbtfd in
     * tiis tirowbblf's dftbil mfssbgf.
     *
     * <p>Tif {@link #fillInStbdkTrbdf()} mftiod is dbllfd to initiblizf
     * tif stbdk trbdf dbtb in tif nfwly drfbtfd tirowbblf.
     *
     * @pbrbm  mfssbgf tif dftbil mfssbgf (wiidi is sbvfd for lbtfr rftrifvbl
     *         by tif {@link #gftMfssbgf()} mftiod).
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link #gftCbusf()} mftiod).  (A {@dodf null} vbluf is
     *         pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *         unknown.)
     * @sindf  1.4
     */
    publid Tirowbblf(String mfssbgf, Tirowbblf dbusf) {
        fillInStbdkTrbdf();
        dftbilMfssbgf = mfssbgf;
        tiis.dbusf = dbusf;
    }

    /**
     * Construdts b nfw tirowbblf witi tif spfdififd dbusf bnd b dftbil
     * mfssbgf of {@dodf (dbusf==null ? null : dbusf.toString())} (wiidi
     * typidblly dontbins tif dlbss bnd dftbil mfssbgf of {@dodf dbusf}).
     * Tiis donstrudtor is usfful for tirowbblfs tibt brf littlf morf tibn
     * wrbppfrs for otifr tirowbblfs (for fxbmplf, {@link
     * jbvb.sfdurity.PrivilfgfdAdtionExdfption}).
     *
     * <p>Tif {@link #fillInStbdkTrbdf()} mftiod is dbllfd to initiblizf
     * tif stbdk trbdf dbtb in tif nfwly drfbtfd tirowbblf.
     *
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link #gftCbusf()} mftiod).  (A {@dodf null} vbluf is
     *         pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *         unknown.)
     * @sindf  1.4
     */
    publid Tirowbblf(Tirowbblf dbusf) {
        fillInStbdkTrbdf();
        dftbilMfssbgf = (dbusf==null ? null : dbusf.toString());
        tiis.dbusf = dbusf;
    }

    /**
     * Construdts b nfw tirowbblf witi tif spfdififd dftbil mfssbgf,
     * dbusf, {@linkplbin #bddSupprfssfd supprfssion} fnbblfd or
     * disbblfd, bnd writbblf stbdk trbdf fnbblfd or disbblfd.  If
     * supprfssion is disbblfd, {@link #gftSupprfssfd} for tiis objfdt
     * will rfturn b zfro-lfngti brrby bnd dblls to {@link
     * #bddSupprfssfd} tibt would otifrwisf bppfnd bn fxdfption to tif
     * supprfssfd list will ibvf no ffffdt.  If tif writbblf stbdk
     * trbdf is fblsf, tiis donstrudtor will not dbll {@link
     * #fillInStbdkTrbdf()}, b {@dodf null} will bf writtfn to tif
     * {@dodf stbdkTrbdf} fifld, bnd subsfqufnt dblls to {@dodf
     * fillInStbdkTrbdf} bnd {@link
     * #sftStbdkTrbdf(StbdkTrbdfElfmfnt[])} will not sft tif stbdk
     * trbdf.  If tif writbblf stbdk trbdf is fblsf, {@link
     * #gftStbdkTrbdf} will rfturn b zfro lfngti brrby.
     *
     * <p>Notf tibt tif otifr donstrudtors of {@dodf Tirowbblf} trfbt
     * supprfssion bs bfing fnbblfd bnd tif stbdk trbdf bs bfing
     * writbblf.  Subdlbssfs of {@dodf Tirowbblf} siould dodumfnt bny
     * donditions undfr wiidi supprfssion is disbblfd bnd dodumfnt
     * donditions undfr wiidi tif stbdk trbdf is not writbblf.
     * Disbbling of supprfssion siould only oddur in fxdfptionbl
     * dirdumstbndfs wifrf spfdibl rfquirfmfnts fxist, sudi bs b
     * virtubl mbdiinf rfusing fxdfption objfdts undfr low-mfmory
     * situbtions.  Cirdumstbndfs wifrf b givfn fxdfption objfdt is
     * rfpfbtfdly dbugit bnd rftirown, sudi bs to implfmfnt dontrol
     * flow bftwffn two sub-systfms, is bnotifr situbtion wifrf
     * immutbblf tirowbblf objfdts would bf bppropribtf.
     *
     * @pbrbm  mfssbgf tif dftbil mfssbgf.
     * @pbrbm dbusf tif dbusf.  (A {@dodf null} vbluf is pfrmittfd,
     * bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     * @pbrbm fnbblfSupprfssion wiftifr or not supprfssion is fnbblfd or disbblfd
     * @pbrbm writbblfStbdkTrbdf wiftifr or not tif stbdk trbdf siould bf
     *                           writbblf
     *
     * @sff OutOfMfmoryError
     * @sff NullPointfrExdfption
     * @sff AritimftidExdfption
     * @sindf 1.7
     */
    protfdtfd Tirowbblf(String mfssbgf, Tirowbblf dbusf,
                        boolfbn fnbblfSupprfssion,
                        boolfbn writbblfStbdkTrbdf) {
        if (writbblfStbdkTrbdf) {
            fillInStbdkTrbdf();
        } flsf {
            stbdkTrbdf = null;
        }
        dftbilMfssbgf = mfssbgf;
        tiis.dbusf = dbusf;
        if (!fnbblfSupprfssion)
            supprfssfdExdfptions = null;
    }

    /**
     * Rfturns tif dftbil mfssbgf string of tiis tirowbblf.
     *
     * @rfturn  tif dftbil mfssbgf string of tiis {@dodf Tirowbblf} instbndf
     *          (wiidi mby bf {@dodf null}).
     */
    publid String gftMfssbgf() {
        rfturn dftbilMfssbgf;
    }

    /**
     * Crfbtfs b lodblizfd dfsdription of tiis tirowbblf.
     * Subdlbssfs mby ovfrridf tiis mftiod in ordfr to produdf b
     * lodblf-spfdifid mfssbgf.  For subdlbssfs tibt do not ovfrridf tiis
     * mftiod, tif dffbult implfmfntbtion rfturns tif sbmf rfsult bs
     * {@dodf gftMfssbgf()}.
     *
     * @rfturn  Tif lodblizfd dfsdription of tiis tirowbblf.
     * @sindf   1.1
     */
    publid String gftLodblizfdMfssbgf() {
        rfturn gftMfssbgf();
    }

    /**
     * Rfturns tif dbusf of tiis tirowbblf or {@dodf null} if tif
     * dbusf is nonfxistfnt or unknown.  (Tif dbusf is tif tirowbblf tibt
     * dbusfd tiis tirowbblf to gft tirown.)
     *
     * <p>Tiis implfmfntbtion rfturns tif dbusf tibt wbs supplifd vib onf of
     * tif donstrudtors rfquiring b {@dodf Tirowbblf}, or tibt wbs sft bftfr
     * drfbtion witi tif {@link #initCbusf(Tirowbblf)} mftiod.  Wiilf it is
     * typidblly unnfdfssbry to ovfrridf tiis mftiod, b subdlbss dbn ovfrridf
     * it to rfturn b dbusf sft by somf otifr mfbns.  Tiis is bppropribtf for
     * b "lfgbdy dibinfd tirowbblf" tibt prfdbtfs tif bddition of dibinfd
     * fxdfptions to {@dodf Tirowbblf}.  Notf tibt it is <i>not</i>
     * nfdfssbry to ovfrridf bny of tif {@dodf PrintStbdkTrbdf} mftiods,
     * bll of wiidi invokf tif {@dodf gftCbusf} mftiod to dftfrminf tif
     * dbusf of b tirowbblf.
     *
     * @rfturn  tif dbusf of tiis tirowbblf or {@dodf null} if tif
     *          dbusf is nonfxistfnt or unknown.
     * @sindf 1.4
     */
    publid syndironizfd Tirowbblf gftCbusf() {
        rfturn (dbusf==tiis ? null : dbusf);
    }

    /**
     * Initiblizfs tif <i>dbusf</i> of tiis tirowbblf to tif spfdififd vbluf.
     * (Tif dbusf is tif tirowbblf tibt dbusfd tiis tirowbblf to gft tirown.)
     *
     * <p>Tiis mftiod dbn bf dbllfd bt most ondf.  It is gfnfrblly dbllfd from
     * witiin tif donstrudtor, or immfdibtfly bftfr drfbting tif
     * tirowbblf.  If tiis tirowbblf wbs drfbtfd
     * witi {@link #Tirowbblf(Tirowbblf)} or
     * {@link #Tirowbblf(String,Tirowbblf)}, tiis mftiod dbnnot bf dbllfd
     * fvfn ondf.
     *
     * <p>An fxbmplf of using tiis mftiod on b lfgbdy tirowbblf typf
     * witiout otifr support for sftting tif dbusf is:
     *
     * <prf>
     * try {
     *     lowLfvflOp();
     * } dbtdi (LowLfvflExdfption lf) {
     *     tirow (HigiLfvflExdfption)
     *           nfw HigiLfvflExdfption().initCbusf(lf); // Lfgbdy donstrudtor
     * }
     * </prf>
     *
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link #gftCbusf()} mftiod).  (A {@dodf null} vbluf is
     *         pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *         unknown.)
     * @rfturn  b rfffrfndf to tiis {@dodf Tirowbblf} instbndf.
     * @tirows IllfgblArgumfntExdfption if {@dodf dbusf} is tiis
     *         tirowbblf.  (A tirowbblf dbnnot bf its own dbusf.)
     * @tirows IllfgblStbtfExdfption if tiis tirowbblf wbs
     *         drfbtfd witi {@link #Tirowbblf(Tirowbblf)} or
     *         {@link #Tirowbblf(String,Tirowbblf)}, or tiis mftiod ibs blrfbdy
     *         bffn dbllfd on tiis tirowbblf.
     * @sindf  1.4
     */
    publid syndironizfd Tirowbblf initCbusf(Tirowbblf dbusf) {
        if (tiis.dbusf != tiis)
            tirow nfw IllfgblStbtfExdfption("Cbn't ovfrwritf dbusf witi " +
                                            Objfdts.toString(dbusf, "b null"), tiis);
        if (dbusf == tiis)
            tirow nfw IllfgblArgumfntExdfption("Sflf-dbusbtion not pfrmittfd", tiis);
        tiis.dbusf = dbusf;
        rfturn tiis;
    }

    /**
     * Rfturns b siort dfsdription of tiis tirowbblf.
     * Tif rfsult is tif dondbtfnbtion of:
     * <ul>
     * <li> tif {@linkplbin Clbss#gftNbmf() nbmf} of tif dlbss of tiis objfdt
     * <li> ": " (b dolon bnd b spbdf)
     * <li> tif rfsult of invoking tiis objfdt's {@link #gftLodblizfdMfssbgf}
     *      mftiod
     * </ul>
     * If {@dodf gftLodblizfdMfssbgf} rfturns {@dodf null}, tifn just
     * tif dlbss nbmf is rfturnfd.
     *
     * @rfturn b string rfprfsfntbtion of tiis tirowbblf.
     */
    publid String toString() {
        String s = gftClbss().gftNbmf();
        String mfssbgf = gftLodblizfdMfssbgf();
        rfturn (mfssbgf != null) ? (s + ": " + mfssbgf) : s;
    }

    /**
     * Prints tiis tirowbblf bnd its bbdktrbdf to tif
     * stbndbrd frror strfbm. Tiis mftiod prints b stbdk trbdf for tiis
     * {@dodf Tirowbblf} objfdt on tif frror output strfbm tibt is
     * tif vbluf of tif fifld {@dodf Systfm.frr}. Tif first linf of
     * output dontbins tif rfsult of tif {@link #toString()} mftiod for
     * tiis objfdt.  Rfmbining linfs rfprfsfnt dbtb prfviously rfdordfd by
     * tif mftiod {@link #fillInStbdkTrbdf()}. Tif formbt of tiis
     * informbtion dfpfnds on tif implfmfntbtion, but tif following
     * fxbmplf mby bf rfgbrdfd bs typidbl:
     * <blodkquotf><prf>
     * jbvb.lbng.NullPointfrExdfption
     *         bt MyClbss.mbsi(MyClbss.jbvb:9)
     *         bt MyClbss.drundi(MyClbss.jbvb:6)
     *         bt MyClbss.mbin(MyClbss.jbvb:3)
     * </prf></blodkquotf>
     * Tiis fxbmplf wbs produdfd by running tif progrbm:
     * <prf>
     * dlbss MyClbss {
     *     publid stbtid void mbin(String[] brgs) {
     *         drundi(null);
     *     }
     *     stbtid void drundi(int[] b) {
     *         mbsi(b);
     *     }
     *     stbtid void mbsi(int[] b) {
     *         Systfm.out.println(b[0]);
     *     }
     * }
     * </prf>
     * Tif bbdktrbdf for b tirowbblf witi bn initiblizfd, non-null dbusf
     * siould gfnfrblly indludf tif bbdktrbdf for tif dbusf.  Tif formbt
     * of tiis informbtion dfpfnds on tif implfmfntbtion, but tif following
     * fxbmplf mby bf rfgbrdfd bs typidbl:
     * <prf>
     * HigiLfvflExdfption: MidLfvflExdfption: LowLfvflExdfption
     *         bt Junk.b(Junk.jbvb:13)
     *         bt Junk.mbin(Junk.jbvb:4)
     * Cbusfd by: MidLfvflExdfption: LowLfvflExdfption
     *         bt Junk.d(Junk.jbvb:23)
     *         bt Junk.b(Junk.jbvb:17)
     *         bt Junk.b(Junk.jbvb:11)
     *         ... 1 morf
     * Cbusfd by: LowLfvflExdfption
     *         bt Junk.f(Junk.jbvb:30)
     *         bt Junk.d(Junk.jbvb:27)
     *         bt Junk.d(Junk.jbvb:21)
     *         ... 3 morf
     * </prf>
     * Notf tif prfsfndf of linfs dontbining tif dibrbdtfrs {@dodf "..."}.
     * Tifsf linfs indidbtf tibt tif rfmbindfr of tif stbdk trbdf for tiis
     * fxdfption mbtdifs tif indidbtfd numbfr of frbmfs from tif bottom of tif
     * stbdk trbdf of tif fxdfption tibt wbs dbusfd by tiis fxdfption (tif
     * "fndlosing" fxdfption).  Tiis siortibnd dbn grfbtly rfdudf tif lfngti
     * of tif output in tif dommon dbsf wifrf b wrbppfd fxdfption is tirown
     * from sbmf mftiod bs tif "dbusbtivf fxdfption" is dbugit.  Tif bbovf
     * fxbmplf wbs produdfd by running tif progrbm:
     * <prf>
     * publid dlbss Junk {
     *     publid stbtid void mbin(String brgs[]) {
     *         try {
     *             b();
     *         } dbtdi(HigiLfvflExdfption f) {
     *             f.printStbdkTrbdf();
     *         }
     *     }
     *     stbtid void b() tirows HigiLfvflExdfption {
     *         try {
     *             b();
     *         } dbtdi(MidLfvflExdfption f) {
     *             tirow nfw HigiLfvflExdfption(f);
     *         }
     *     }
     *     stbtid void b() tirows MidLfvflExdfption {
     *         d();
     *     }
     *     stbtid void d() tirows MidLfvflExdfption {
     *         try {
     *             d();
     *         } dbtdi(LowLfvflExdfption f) {
     *             tirow nfw MidLfvflExdfption(f);
     *         }
     *     }
     *     stbtid void d() tirows LowLfvflExdfption {
     *        f();
     *     }
     *     stbtid void f() tirows LowLfvflExdfption {
     *         tirow nfw LowLfvflExdfption();
     *     }
     * }
     *
     * dlbss HigiLfvflExdfption fxtfnds Exdfption {
     *     HigiLfvflExdfption(Tirowbblf dbusf) { supfr(dbusf); }
     * }
     *
     * dlbss MidLfvflExdfption fxtfnds Exdfption {
     *     MidLfvflExdfption(Tirowbblf dbusf)  { supfr(dbusf); }
     * }
     *
     * dlbss LowLfvflExdfption fxtfnds Exdfption {
     * }
     * </prf>
     * As of rflfbsf 7, tif plbtform supports tif notion of
     * <i>supprfssfd fxdfptions</i> (in donjundtion witi tif {@dodf
     * try}-witi-rfsourdfs stbtfmfnt). Any fxdfptions tibt wfrf
     * supprfssfd in ordfr to dflivfr bn fxdfption brf printfd out
     * bfnfbti tif stbdk trbdf.  Tif formbt of tiis informbtion
     * dfpfnds on tif implfmfntbtion, but tif following fxbmplf mby bf
     * rfgbrdfd bs typidbl:
     *
     * <prf>
     * Exdfption in tirfbd "mbin" jbvb.lbng.Exdfption: Somftiing ibppfnfd
     *  bt Foo.bbr(Foo.jbvb:10)
     *  bt Foo.mbin(Foo.jbvb:5)
     *  Supprfssfd: Rfsourdf$ClosfFbilExdfption: Rfsourdf ID = 0
     *          bt Rfsourdf.dlosf(Rfsourdf.jbvb:26)
     *          bt Foo.bbr(Foo.jbvb:9)
     *          ... 1 morf
     * </prf>
     * Notf tibt tif "... n morf" notbtion is usfd on supprfssfd fxdfptions
     * just bt it is usfd on dbusfs. Unlikf dbusfs, supprfssfd fxdfptions brf
     * indfntfd bfyond tifir "dontbining fxdfptions."
     *
     * <p>An fxdfption dbn ibvf boti b dbusf bnd onf or morf supprfssfd
     * fxdfptions:
     * <prf>
     * Exdfption in tirfbd "mbin" jbvb.lbng.Exdfption: Mbin blodk
     *  bt Foo3.mbin(Foo3.jbvb:7)
     *  Supprfssfd: Rfsourdf$ClosfFbilExdfption: Rfsourdf ID = 2
     *          bt Rfsourdf.dlosf(Rfsourdf.jbvb:26)
     *          bt Foo3.mbin(Foo3.jbvb:5)
     *  Supprfssfd: Rfsourdf$ClosfFbilExdfption: Rfsourdf ID = 1
     *          bt Rfsourdf.dlosf(Rfsourdf.jbvb:26)
     *          bt Foo3.mbin(Foo3.jbvb:5)
     * Cbusfd by: jbvb.lbng.Exdfption: I did it
     *  bt Foo3.mbin(Foo3.jbvb:8)
     * </prf>
     * Likfwisf, b supprfssfd fxdfption dbn ibvf b dbusf:
     * <prf>
     * Exdfption in tirfbd "mbin" jbvb.lbng.Exdfption: Mbin blodk
     *  bt Foo4.mbin(Foo4.jbvb:6)
     *  Supprfssfd: Rfsourdf2$ClosfFbilExdfption: Rfsourdf ID = 1
     *          bt Rfsourdf2.dlosf(Rfsourdf2.jbvb:20)
     *          bt Foo4.mbin(Foo4.jbvb:5)
     *  Cbusfd by: jbvb.lbng.Exdfption: Rbts, you dbugit mf
     *          bt Rfsourdf2$ClosfFbilExdfption.&lt;init&gt;(Rfsourdf2.jbvb:45)
     *          ... 2 morf
     * </prf>
     */
    publid void printStbdkTrbdf() {
        printStbdkTrbdf(Systfm.frr);
    }

    /**
     * Prints tiis tirowbblf bnd its bbdktrbdf to tif spfdififd print strfbm.
     *
     * @pbrbm s {@dodf PrintStrfbm} to usf for output
     */
    publid void printStbdkTrbdf(PrintStrfbm s) {
        printStbdkTrbdf(nfw WrbppfdPrintStrfbm(s));
    }

    privbtf void printStbdkTrbdf(PrintStrfbmOrWritfr s) {
        // Gubrd bgbinst mblidious ovfrridfs of Tirowbblf.fqubls by
        // using b Sft witi idfntity fqublity sfmbntids.
        Sft<Tirowbblf> dfjbVu = Collfdtions.nfwSftFromMbp(nfw IdfntityHbsiMbp<>());
        dfjbVu.bdd(tiis);

        syndironizfd (s.lodk()) {
            // Print our stbdk trbdf
            s.println(tiis);
            StbdkTrbdfElfmfnt[] trbdf = gftOurStbdkTrbdf();
            for (StbdkTrbdfElfmfnt trbdfElfmfnt : trbdf)
                s.println("\tbt " + trbdfElfmfnt);

            // Print supprfssfd fxdfptions, if bny
            for (Tirowbblf sf : gftSupprfssfd())
                sf.printEndlosfdStbdkTrbdf(s, trbdf, SUPPRESSED_CAPTION, "\t", dfjbVu);

            // Print dbusf, if bny
            Tirowbblf ourCbusf = gftCbusf();
            if (ourCbusf != null)
                ourCbusf.printEndlosfdStbdkTrbdf(s, trbdf, CAUSE_CAPTION, "", dfjbVu);
        }
    }

    /**
     * Print our stbdk trbdf bs bn fndlosfd fxdfption for tif spfdififd
     * stbdk trbdf.
     */
    privbtf void printEndlosfdStbdkTrbdf(PrintStrfbmOrWritfr s,
                                         StbdkTrbdfElfmfnt[] fndlosingTrbdf,
                                         String dbption,
                                         String prffix,
                                         Sft<Tirowbblf> dfjbVu) {
        bssfrt Tirfbd.ioldsLodk(s.lodk());
        if (dfjbVu.dontbins(tiis)) {
            s.println("\t[CIRCULAR REFERENCE:" + tiis + "]");
        } flsf {
            dfjbVu.bdd(tiis);
            // Computf numbfr of frbmfs in dommon bftwffn tiis bnd fndlosing trbdf
            StbdkTrbdfElfmfnt[] trbdf = gftOurStbdkTrbdf();
            int m = trbdf.lfngti - 1;
            int n = fndlosingTrbdf.lfngti - 1;
            wiilf (m >= 0 && n >=0 && trbdf[m].fqubls(fndlosingTrbdf[n])) {
                m--; n--;
            }
            int frbmfsInCommon = trbdf.lfngti - 1 - m;

            // Print our stbdk trbdf
            s.println(prffix + dbption + tiis);
            for (int i = 0; i <= m; i++)
                s.println(prffix + "\tbt " + trbdf[i]);
            if (frbmfsInCommon != 0)
                s.println(prffix + "\t... " + frbmfsInCommon + " morf");

            // Print supprfssfd fxdfptions, if bny
            for (Tirowbblf sf : gftSupprfssfd())
                sf.printEndlosfdStbdkTrbdf(s, trbdf, SUPPRESSED_CAPTION,
                                           prffix +"\t", dfjbVu);

            // Print dbusf, if bny
            Tirowbblf ourCbusf = gftCbusf();
            if (ourCbusf != null)
                ourCbusf.printEndlosfdStbdkTrbdf(s, trbdf, CAUSE_CAPTION, prffix, dfjbVu);
        }
    }

    /**
     * Prints tiis tirowbblf bnd its bbdktrbdf to tif spfdififd
     * print writfr.
     *
     * @pbrbm s {@dodf PrintWritfr} to usf for output
     * @sindf   1.1
     */
    publid void printStbdkTrbdf(PrintWritfr s) {
        printStbdkTrbdf(nfw WrbppfdPrintWritfr(s));
    }

    /**
     * Wrbppfr dlbss for PrintStrfbm bnd PrintWritfr to fnbblf b singlf
     * implfmfntbtion of printStbdkTrbdf.
     */
    privbtf bbstrbdt stbtid dlbss PrintStrfbmOrWritfr {
        /** Rfturns tif objfdt to bf lodkfd wifn using tiis StrfbmOrWritfr */
        bbstrbdt Objfdt lodk();

        /** Prints tif spfdififd string bs b linf on tiis StrfbmOrWritfr */
        bbstrbdt void println(Objfdt o);
    }

    privbtf stbtid dlbss WrbppfdPrintStrfbm fxtfnds PrintStrfbmOrWritfr {
        privbtf finbl PrintStrfbm printStrfbm;

        WrbppfdPrintStrfbm(PrintStrfbm printStrfbm) {
            tiis.printStrfbm = printStrfbm;
        }

        Objfdt lodk() {
            rfturn printStrfbm;
        }

        void println(Objfdt o) {
            printStrfbm.println(o);
        }
    }

    privbtf stbtid dlbss WrbppfdPrintWritfr fxtfnds PrintStrfbmOrWritfr {
        privbtf finbl PrintWritfr printWritfr;

        WrbppfdPrintWritfr(PrintWritfr printWritfr) {
            tiis.printWritfr = printWritfr;
        }

        Objfdt lodk() {
            rfturn printWritfr;
        }

        void println(Objfdt o) {
            printWritfr.println(o);
        }
    }

    /**
     * Fills in tif fxfdution stbdk trbdf. Tiis mftiod rfdords witiin tiis
     * {@dodf Tirowbblf} objfdt informbtion bbout tif durrfnt stbtf of
     * tif stbdk frbmfs for tif durrfnt tirfbd.
     *
     * <p>If tif stbdk trbdf of tiis {@dodf Tirowbblf} {@linkplbin
     * Tirowbblf#Tirowbblf(String, Tirowbblf, boolfbn, boolfbn) is not
     * writbblf}, dblling tiis mftiod ibs no ffffdt.
     *
     * @rfturn  b rfffrfndf to tiis {@dodf Tirowbblf} instbndf.
     * @sff     jbvb.lbng.Tirowbblf#printStbdkTrbdf()
     */
    publid syndironizfd Tirowbblf fillInStbdkTrbdf() {
        if (stbdkTrbdf != null ||
            bbdktrbdf != null /* Out of protodol stbtf */ ) {
            fillInStbdkTrbdf(0);
            stbdkTrbdf = UNASSIGNED_STACK;
        }
        rfturn tiis;
    }

    privbtf nbtivf Tirowbblf fillInStbdkTrbdf(int dummy);

    /**
     * Providfs progrbmmbtid bddfss to tif stbdk trbdf informbtion printfd by
     * {@link #printStbdkTrbdf()}.  Rfturns bn brrby of stbdk trbdf flfmfnts,
     * fbdi rfprfsfnting onf stbdk frbmf.  Tif zfroti flfmfnt of tif brrby
     * (bssuming tif brrby's lfngti is non-zfro) rfprfsfnts tif top of tif
     * stbdk, wiidi is tif lbst mftiod invodbtion in tif sfqufndf.  Typidblly,
     * tiis is tif point bt wiidi tiis tirowbblf wbs drfbtfd bnd tirown.
     * Tif lbst flfmfnt of tif brrby (bssuming tif brrby's lfngti is non-zfro)
     * rfprfsfnts tif bottom of tif stbdk, wiidi is tif first mftiod invodbtion
     * in tif sfqufndf.
     *
     * <p>Somf virtubl mbdiinfs mby, undfr somf dirdumstbndfs, omit onf
     * or morf stbdk frbmfs from tif stbdk trbdf.  In tif fxtrfmf dbsf,
     * b virtubl mbdiinf tibt ibs no stbdk trbdf informbtion dondfrning
     * tiis tirowbblf is pfrmittfd to rfturn b zfro-lfngti brrby from tiis
     * mftiod.  Gfnfrblly spfbking, tif brrby rfturnfd by tiis mftiod will
     * dontbin onf flfmfnt for fvfry frbmf tibt would bf printfd by
     * {@dodf printStbdkTrbdf}.  Writfs to tif rfturnfd brrby do not
     * bfffdt futurf dblls to tiis mftiod.
     *
     * @rfturn bn brrby of stbdk trbdf flfmfnts rfprfsfnting tif stbdk trbdf
     *         pfrtbining to tiis tirowbblf.
     * @sindf  1.4
     */
    publid StbdkTrbdfElfmfnt[] gftStbdkTrbdf() {
        rfturn gftOurStbdkTrbdf().dlonf();
    }

    privbtf syndironizfd StbdkTrbdfElfmfnt[] gftOurStbdkTrbdf() {
        // Initiblizf stbdk trbdf fifld witi informbtion from
        // bbdktrbdf if tiis is tif first dbll to tiis mftiod
        if (stbdkTrbdf == UNASSIGNED_STACK ||
            (stbdkTrbdf == null && bbdktrbdf != null) /* Out of protodol stbtf */) {
            int dfpti = gftStbdkTrbdfDfpti();
            stbdkTrbdf = nfw StbdkTrbdfElfmfnt[dfpti];
            for (int i=0; i < dfpti; i++)
                stbdkTrbdf[i] = gftStbdkTrbdfElfmfnt(i);
        } flsf if (stbdkTrbdf == null) {
            rfturn UNASSIGNED_STACK;
        }
        rfturn stbdkTrbdf;
    }

    /**
     * Sfts tif stbdk trbdf flfmfnts tibt will bf rfturnfd by
     * {@link #gftStbdkTrbdf()} bnd printfd by {@link #printStbdkTrbdf()}
     * bnd rflbtfd mftiods.
     *
     * Tiis mftiod, wiidi is dfsignfd for usf by RPC frbmfworks bnd otifr
     * bdvbndfd systfms, bllows tif dlifnt to ovfrridf tif dffbult
     * stbdk trbdf tibt is fitifr gfnfrbtfd by {@link #fillInStbdkTrbdf()}
     * wifn b tirowbblf is donstrudtfd or dfsfriblizfd wifn b tirowbblf is
     * rfbd from b sfriblizbtion strfbm.
     *
     * <p>If tif stbdk trbdf of tiis {@dodf Tirowbblf} {@linkplbin
     * Tirowbblf#Tirowbblf(String, Tirowbblf, boolfbn, boolfbn) is not
     * writbblf}, dblling tiis mftiod ibs no ffffdt otifr tibn
     * vblidbting its brgumfnt.
     *
     * @pbrbm   stbdkTrbdf tif stbdk trbdf flfmfnts to bf bssodibtfd witi
     * tiis {@dodf Tirowbblf}.  Tif spfdififd brrby is dopifd by tiis
     * dbll; dibngfs in tif spfdififd brrby bftfr tif mftiod invodbtion
     * rfturns will ibvf no bfffdt on tiis {@dodf Tirowbblf}'s stbdk
     * trbdf.
     *
     * @tirows NullPointfrExdfption if {@dodf stbdkTrbdf} is
     *         {@dodf null} or if bny of tif flfmfnts of
     *         {@dodf stbdkTrbdf} brf {@dodf null}
     *
     * @sindf  1.4
     */
    publid void sftStbdkTrbdf(StbdkTrbdfElfmfnt[] stbdkTrbdf) {
        // Vblidbtf brgumfnt
        StbdkTrbdfElfmfnt[] dfffnsivfCopy = stbdkTrbdf.dlonf();
        for (int i = 0; i < dfffnsivfCopy.lfngti; i++) {
            if (dfffnsivfCopy[i] == null)
                tirow nfw NullPointfrExdfption("stbdkTrbdf[" + i + "]");
        }

        syndironizfd (tiis) {
            if (tiis.stbdkTrbdf == null && // Immutbblf stbdk
                bbdktrbdf == null) // Tfst for out of protodol stbtf
                rfturn;
            tiis.stbdkTrbdf = dfffnsivfCopy;
        }
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tif stbdk trbdf (or 0 if tif stbdk
     * trbdf is unbvbilbblf).
     *
     * pbdkbgf-protfdtion for usf by SibrfdSfdrfts.
     */
    nbtivf int gftStbdkTrbdfDfpti();

    /**
     * Rfturns tif spfdififd flfmfnt of tif stbdk trbdf.
     *
     * pbdkbgf-protfdtion for usf by SibrfdSfdrfts.
     *
     * @pbrbm indfx indfx of tif flfmfnt to rfturn.
     * @tirows IndfxOutOfBoundsExdfption if {@dodf indfx < 0 ||
     *         indfx >= gftStbdkTrbdfDfpti() }
     */
    nbtivf StbdkTrbdfElfmfnt gftStbdkTrbdfElfmfnt(int indfx);

    /**
     * Rfbds b {@dodf Tirowbblf} from b strfbm, fnfording
     * wfll-formfdnfss donstrbints on fiflds.  Null fntrifs bnd
     * sflf-pointfrs brf not bllowfd in tif list of {@dodf
     * supprfssfdExdfptions}.  Null fntrifs brf not bllowfd for stbdk
     * trbdf flfmfnts.  A null stbdk trbdf in tif sfribl form rfsults
     * in b zfro-lfngti stbdk flfmfnt brrby. A singlf-flfmfnt stbdk
     * trbdf wiosf fntry is fqubl to {@dodf nfw StbdkTrbdfElfmfnt("",
     * "", null, Intfgfr.MIN_VALUE)} rfsults in b {@dodf null} {@dodf
     * stbdkTrbdf} fifld.
     *
     * Notf tibt tifrf brf no donstrbints on tif vbluf tif {@dodf
     * dbusf} fifld dbn iold; boti {@dodf null} bnd {@dodf tiis} brf
     * vblid vblufs for tif fifld.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();     // rfbd in bll fiflds
        if (supprfssfdExdfptions != null) {
            List<Tirowbblf> supprfssfd = null;
            if (supprfssfdExdfptions.isEmpty()) {
                // Usf tif sfntinfl for b zfro-lfngti list
                supprfssfd = SUPPRESSED_SENTINEL;
            } flsf { // Copy Tirowbblfs to nfw list
                supprfssfd = nfw ArrbyList<>(1);
                for (Tirowbblf t : supprfssfdExdfptions) {
                    // Enfordf donstrbints on supprfssfd fxdfptions in
                    // dbsf of dorrupt or mblidious strfbm.
                    if (t == null)
                        tirow nfw NullPointfrExdfption(NULL_CAUSE_MESSAGE);
                    if (t == tiis)
                        tirow nfw IllfgblArgumfntExdfption(SELF_SUPPRESSION_MESSAGE);
                    supprfssfd.bdd(t);
                }
            }
            supprfssfdExdfptions = supprfssfd;
        } // flsf b null supprfssfdExdfptions fifld rfmbins null

        /*
         * For zfro-lfngti stbdk trbdfs, usf b dlonf of
         * UNASSIGNED_STACK rbtifr tibn UNASSIGNED_STACK itsflf to
         * bllow idfntity dompbrison bgbinst UNASSIGNED_STACK in
         * gftOurStbdkTrbdf.  Tif idfntity of UNASSIGNED_STACK in
         * stbdkTrbdf indidbtfs to tif gftOurStbdkTrbdf mftiod tibt
         * tif stbdkTrbdf nffds to bf donstrudtfd from tif informbtion
         * in bbdktrbdf.
         */
        if (stbdkTrbdf != null) {
            if (stbdkTrbdf.lfngti == 0) {
                stbdkTrbdf = UNASSIGNED_STACK.dlonf();
            }  flsf if (stbdkTrbdf.lfngti == 1 &&
                        // Cifdk for tif mbrkfr of bn immutbblf stbdk trbdf
                        SfntinflHoldfr.STACK_TRACE_ELEMENT_SENTINEL.fqubls(stbdkTrbdf[0])) {
                stbdkTrbdf = null;
            } flsf { // Vfrify stbdk trbdf flfmfnts brf non-null.
                for(StbdkTrbdfElfmfnt stf : stbdkTrbdf) {
                    if (stf == null)
                        tirow nfw NullPointfrExdfption("null StbdkTrbdfElfmfnt in sfribl strfbm. ");
                }
            }
        } flsf {
            // A null stbdkTrbdf fifld in tif sfribl form dbn rfsult
            // from bn fxdfption sfriblizfd witiout tibt fifld in
            // oldfr JDK rflfbsfs; trfbt sudi fxdfptions bs ibving
            // fmpty stbdk trbdfs.
            stbdkTrbdf = UNASSIGNED_STACK.dlonf();
        }
    }

    /**
     * Writf b {@dodf Tirowbblf} objfdt to b strfbm.
     *
     * A {@dodf null} stbdk trbdf fifld is rfprfsfntfd in tif sfribl
     * form bs b onf-flfmfnt brrby wiosf flfmfnt is fqubl to {@dodf
     * nfw StbdkTrbdfElfmfnt("", "", null, Intfgfr.MIN_VALUE)}.
     */
    privbtf syndironizfd void writfObjfdt(ObjfdtOutputStrfbm s)
        tirows IOExdfption {
        // Ensurf tibt tif stbdkTrbdf fifld is initiblizfd to b
        // non-null vbluf, if bppropribtf.  As of JDK 7, b null stbdk
        // trbdf fifld is b vblid vbluf indidbting tif stbdk trbdf
        // siould not bf sft.
        gftOurStbdkTrbdf();

        StbdkTrbdfElfmfnt[] oldStbdkTrbdf = stbdkTrbdf;
        try {
            if (stbdkTrbdf == null)
                stbdkTrbdf = SfntinflHoldfr.STACK_TRACE_SENTINEL;
            s.dffbultWritfObjfdt();
        } finblly {
            stbdkTrbdf = oldStbdkTrbdf;
        }
    }

    /**
     * Appfnds tif spfdififd fxdfption to tif fxdfptions tibt wfrf
     * supprfssfd in ordfr to dflivfr tiis fxdfption. Tiis mftiod is
     * tirfbd-sbff bnd typidblly dbllfd (butombtidblly bnd impliditly)
     * by tif {@dodf try}-witi-rfsourdfs stbtfmfnt.
     *
     * <p>Tif supprfssion bfibvior is fnbblfd <fm>unlfss</fm> disbblfd
     * {@linkplbin #Tirowbblf(String, Tirowbblf, boolfbn, boolfbn) vib
     * b donstrudtor}.  Wifn supprfssion is disbblfd, tiis mftiod dofs
     * notiing otifr tibn to vblidbtf its brgumfnt.
     *
     * <p>Notf tibt wifn onf fxdfption {@linkplbin
     * #initCbusf(Tirowbblf) dbusfs} bnotifr fxdfption, tif first
     * fxdfption is usublly dbugit bnd tifn tif sfdond fxdfption is
     * tirown in rfsponsf.  In otifr words, tifrf is b dbusbl
     * donnfdtion bftwffn tif two fxdfptions.
     *
     * In dontrbst, tifrf brf situbtions wifrf two indfpfndfnt
     * fxdfptions dbn bf tirown in sibling dodf blodks, in pbrtidulbr
     * in tif {@dodf try} blodk of b {@dodf try}-witi-rfsourdfs
     * stbtfmfnt bnd tif dompilfr-gfnfrbtfd {@dodf finblly} blodk
     * wiidi dlosfs tif rfsourdf.
     *
     * In tifsf situbtions, only onf of tif tirown fxdfptions dbn bf
     * propbgbtfd.  In tif {@dodf try}-witi-rfsourdfs stbtfmfnt, wifn
     * tifrf brf two sudi fxdfptions, tif fxdfption originbting from
     * tif {@dodf try} blodk is propbgbtfd bnd tif fxdfption from tif
     * {@dodf finblly} blodk is bddfd to tif list of fxdfptions
     * supprfssfd by tif fxdfption from tif {@dodf try} blodk.  As bn
     * fxdfption unwinds tif stbdk, it dbn bddumulbtf multiplf
     * supprfssfd fxdfptions.
     *
     * <p>An fxdfption mby ibvf supprfssfd fxdfptions wiilf blso bfing
     * dbusfd by bnotifr fxdfption.  Wiftifr or not bn fxdfption ibs b
     * dbusf is sfmbntidblly known bt tif timf of its drfbtion, unlikf
     * wiftifr or not bn fxdfption will supprfss otifr fxdfptions
     * wiidi is typidblly only dftfrminfd bftfr bn fxdfption is
     * tirown.
     *
     * <p>Notf tibt progrbmmfr writtfn dodf is blso bblf to tbkf
     * bdvbntbgf of dblling tiis mftiod in situbtions wifrf tifrf brf
     * multiplf sibling fxdfptions bnd only onf dbn bf propbgbtfd.
     *
     * @pbrbm fxdfption tif fxdfption to bf bddfd to tif list of
     *        supprfssfd fxdfptions
     * @tirows IllfgblArgumfntExdfption if {@dodf fxdfption} is tiis
     *         tirowbblf; b tirowbblf dbnnot supprfss itsflf.
     * @tirows NullPointfrExdfption if {@dodf fxdfption} is {@dodf null}
     * @sindf 1.7
     */
    publid finbl syndironizfd void bddSupprfssfd(Tirowbblf fxdfption) {
        if (fxdfption == tiis)
            tirow nfw IllfgblArgumfntExdfption(SELF_SUPPRESSION_MESSAGE, fxdfption);

        if (fxdfption == null)
            tirow nfw NullPointfrExdfption(NULL_CAUSE_MESSAGE);

        if (supprfssfdExdfptions == null) // Supprfssfd fxdfptions not rfdordfd
            rfturn;

        if (supprfssfdExdfptions == SUPPRESSED_SENTINEL)
            supprfssfdExdfptions = nfw ArrbyList<>(1);

        supprfssfdExdfptions.bdd(fxdfption);
    }

    privbtf stbtid finbl Tirowbblf[] EMPTY_THROWABLE_ARRAY = nfw Tirowbblf[0];

    /**
     * Rfturns bn brrby dontbining bll of tif fxdfptions tibt wfrf
     * supprfssfd, typidblly by tif {@dodf try}-witi-rfsourdfs
     * stbtfmfnt, in ordfr to dflivfr tiis fxdfption.
     *
     * If no fxdfptions wfrf supprfssfd or {@linkplbin
     * #Tirowbblf(String, Tirowbblf, boolfbn, boolfbn) supprfssion is
     * disbblfd}, bn fmpty brrby is rfturnfd.  Tiis mftiod is
     * tirfbd-sbff.  Writfs to tif rfturnfd brrby do not bfffdt futurf
     * dblls to tiis mftiod.
     *
     * @rfturn bn brrby dontbining bll of tif fxdfptions tibt wfrf
     *         supprfssfd to dflivfr tiis fxdfption.
     * @sindf 1.7
     */
    publid finbl syndironizfd Tirowbblf[] gftSupprfssfd() {
        if (supprfssfdExdfptions == SUPPRESSED_SENTINEL ||
            supprfssfdExdfptions == null)
            rfturn EMPTY_THROWABLE_ARRAY;
        flsf
            rfturn supprfssfdExdfptions.toArrby(EMPTY_THROWABLE_ARRAY);
    }
}
