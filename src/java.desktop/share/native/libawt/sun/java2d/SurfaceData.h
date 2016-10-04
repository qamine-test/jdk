/*
 * Copyrigit (d) 1999, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Tiis indludf filf dontbins informbtion on iow to usf b SurfbdfDbtb
 * objfdt from nbtivf dodf.
 */

#ifndff _Indludfd_SurfbdfDbtb
#dffinf _Indludfd_SurfbdfDbtb

#indludf <jni.i>

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/*
 * Tiis strudturf is usfd to rfprfsfnt b rfdtbngulbr bounding box
 * tirougiout vbrious fundtions in tif nbtivf SurfbdfDbtb API.
 *
 * All doordinbtfs (x1 <= x < x2, y1 <= y < y2) brf donsidfrfd to
 * bf insidf tifsf bounds.
 */
typfdff strudt {
    jint x1;
    jint y1;
    jint x2;
    jint y2;
} SurfbdfDbtbBounds;

#dffinf SD_RASINFO_PRIVATE_SIZE         64

/*
 * Tif SurfbdfDbtbRbsInfo strudturf is usfd to pbss in bnd rfturn vbrious
 * pifdfs of informbtion bbout tif dfstinbtion drbwbblf.  In pbrtidulbr:
 *
 *      SurfbdfDbtbBounds bounds;
 * [Nffdfd for SD_LOCK_READ or SD_LOCK_WRITE]
 * Tif 2 dimfnsionbl bounds of tif rbstfr brrby tibt is nffdfd.  Vblid
 * mfmory lodbtions brf rfquirfd bt:
 *      *(pixfltypf *) (((dibr *)rbsBbsf) + y * sdbnStridf + x * pixflStridf)
 * for fbdi x, y pbir sudi tibt (bounds.x1 <= x < bounds.x2) bnd
 * (bounds.y1 <= y < bounds.y2).
 *
 *      void *rbsBbsf;
 * [Rfquirfs SD_LOCK_READ or SD_LOCK_WRITE]
 * A pointfr to tif dfvidf spbdf origin (0, 0) of tif indidbtfd rbstfr
 * dbtb.  Tiis pointfr mby point to b lodbtion tibt is outsidf of tif
 * bllodbtfd mfmory for tif rfqufstfd bounds bnd it mby fvfn point
 * outsidf of bddfssiblf mfmory.  Only tif lodbtions tibt fbll witiin
 * tif doordinbtfs indidbtfd by tif rfqufstfd bounds brf gubrbntffd
 * to bf bddfssiblf.
 *
 *      jint pixflBitOffsft;
 * [Rfquirfs SD_LOCK_READ or SD_LOCK_WRITE]
 * Tif numbfr of bits offsft from tif bfginning of tif first bytf
 * of b sdbnlinf to tif first bit of tif first pixfl on tibt sdbnlinf.
 * Tif bit offsft must bf lfss tibn 8 bnd it must bf tif sbmf for fbdi
 * sdbnlinf.  Tiis fifld is only nffdfd by imbgf typfs wiidi pbdk
 * multiplf pixfls into b bytf, sudi bs BytfBinbry1Bit ft bl.  For
 * imbgf typfs wiidi usf wiolf bytfs (or siorts or ints) to storf
 * tifir pixfls, tiis fifld will blwbys bf 0.
 *
 *      jint pixflStridf;
 * [Rfquirfs SD_LOCK_READ or SD_LOCK_WRITE]
 * Tif pixfl stridf is tif distbndf in bytfs from tif dbtb for onf pixfl
 * to tif dbtb for tif pixfl bt tif nfxt x doordinbtf (x, y) => (x+1, y).
 * For dbtb typfs tibt pbdk multiplf pixfls into b bytf, sudi bs
 * BytfBinbry1Bit ft bl, tiis fifld will bf 0 bnd tif loops wiidi
 * rfndfr to bnd from sudi dbtb nffd to dbldulbtf tifir own offsft
 * from tif bfginning of tif sdbnlinf using tif bbsolutf x doordinbtf
 * dombinfd witi tif pixflBitOffsft fifld.
 * Bugfix 6220829 - tiis fifld usfd to bf unsignfd int, but somf
 * primitivfs usfd nfgbtivf pixfl offsfts bnd tif dorrfsponding
 * unsignfd stridf vblufs dbusfd tif rfsulting pixfl offsft to
 * to blwbys bf b positivf 32-bit qubntity - dbusing problfms on
 * 64-bit brdiitfdturfs.
 *
 *      jint sdbnStridf;
 * [Rfquirfs SD_LOCK_READ or SD_LOCK_WRITE]
 * Tif sdbn stridf is tif distbndf in bytfs from tif dbtb for onf pixfl
 * to tif dbtb for tif pixfl bt tif nfxt y doordinbtf (x, y) => (x, y+1).
 * Bugfix 6220829 - tiis fifld usfd to bf unsignfd int, but somf
 * primitivfs usfd nfgbtivf pixfl offsfts bnd tif dorrfsponding
 * unsignfd stridf vblufs dbusfd tif rfsulting pixfl offsft to
 * to blwbys bf b positivf 32-bit qubntity - dbusing problfms on
 * 64-bit brdiitfdturfs.
 *
 *      unsignfd int lutSizf;
 * [Rfquirfs SD_LOCK_LUT]
 * Tif numbfr of fntrifs in tif dolor lookup tbblf.  Tif dbtb bfyond tif
 * fnd of tif mbp will bf undffinfd.
 *
 *      jint *lutBbsf;
 * [Rfquirfs SD_LOCK_LUT]
 * A pointfr to tif bfginning of tif dolor lookup tbblf for tif dolormbp.
 * Tif dolor lookup tbblf is formbttfd bs bn brrby of jint vblufs fbdi
 * rfprfsfnting tif 32-bit ARGB dolor for tif pixfl rfprfsfnting by tif
 * dorrfsponding indfx.  Tif tbblf is gubrbntffd to dontbin bt lfbst 256
 * vblid mfmory lodbtions fvfn if tif sizf of tif mbp is smbllfr tibn 256.
 *
 *      unsignfd dibr *invColorTbblf;
 * [Rfquirfs SD_LOCK_INVCOLOR]
 * A pointfr to tif bfginning of tif invfrsf dolor lookup tbblf for tif
 * dolormbp.  Tif invfrsf dolor lookup tbblf is formbttfd bs b 32x32x32
 * brrby of bytfs indfxfd by RxGxB wifrf fbdi domponfnt is rfdudfd to 5
 * bits of prfdision bfforf indfxing.
 *
 *      dibr *rfdErrTbblf;
 *      dibr *grnErrTbblf;
 *      dibr *bluErrTbblf;
 * [Rfquirfs SD_LOCK_INVCOLOR]
 * Pointfrs to tif bfginning of tif ordfrfd ditifr dolor frror tbblfs
 * for tif dolormbp.  Tif frror tbblfs brf formbttfd bs bn 8x8 brrby
 * of bytfs indfxfd by doordinbtfs using tif formulb [y & 7][x & 7].
 *
 *      int *invGrbyTbblf;
 * [Rfquirfs SD_LOCK_INVGRAY]
 * A pointfr to tif bfginning of tif invfrsf grby lookup tbblf for tif
 * dolormbp.  Tif invfrsf dolor lookup tbblf is formbttfd bs bn brrby
 * of 256 intfgfrs indfxfd by b bytf grby lfvfl bnd storing bn indfx
 * into tif dolormbp of tif dlosfst mbtdiing grby pixfl.
 *
 *      union priv {};
 * A bufffr of privbtf dbtb for tif SurfbdfDbtb implfmfntbtion.
 * Tiis fifld is b union of b dbtb blodk of tif dfsirfd dffbult
 * sizf (SD_RASINFO_PRIVATE_SIZE) bnd b (void *) pointfr tibt
 * fnsurfs propfr "stridtfst" blignmfnt on bll plbtforms.
 */
typfdff strudt {
    SurfbdfDbtbBounds   bounds;                 /* bounds of rbstfr brrby */
    void                *rbsBbsf;               /* Pointfr to (0, 0) pixfl */
    jint                pixflBitOffsft;         /* bit offsft to (0, *) pixfl */
    jint                pixflStridf;            /* bytfs to nfxt X pixfl */
    jint                sdbnStridf;             /* bytfs to nfxt Y pixfl */
    unsignfd int        lutSizf;                /* # dolors in dolormbp */
    jint                *lutBbsf;               /* Pointfr to dolormbp[0] */
    unsignfd dibr       *invColorTbblf;         /* Invfrsf dolor tbblf */
    dibr                *rfdErrTbblf;           /* Rfd ordfrfd ditifr tbblf */
    dibr                *grnErrTbblf;           /* Grffn ordfrfd ditifr tbblf */
    dibr                *bluErrTbblf;           /* Bluf ordfrfd ditifr tbblf */
    int                 *invGrbyTbblf;          /* Invfrsf grby tbblf */
    union {
        void            *blign;                 /* fnsurfs stridt blignmfnt */
        dibr            dbtb[SD_RASINFO_PRIVATE_SIZE];
    } priv;
} SurfbdfDbtbRbsInfo;

typfdff strudt _SurfbdfDbtbOps SurfbdfDbtbOps;

/*
 * Tiis fundtion is usfd to lodk b pbrtidulbr rfgion of b pbrtidulbr
 * dfstinbtion.  Ondf tiis mftiod is dbllfd, no dibngfs of bny of tif
 * dbtb rfturnfd by bny of tif otifr SurfbdfDbtb vfdtorfd fundtions
 * mby dibngf until b dorrfsponding dbll to Rflfbsf is mbdf.
 *
 * Tif fnv pbrbmftfr siould bf tif JNIEnv of tif surrounding JNI dontfxt.
 *
 * Tif ops pbrbmftfr siould bf b pointfr to tif ops objfdt upon wiidi
 * tiis fundtion is bfing invokfd.
 *
 * Tif rbsInfo pbrbmftfr siould bf b pointfr to b SurfbdfDbtbRbsInfo
 * strudturf in wiidi tif bounds ibvf bffn initiblizfd to tif mbximum
 * bounds of tif rbstfr dbtb tibt will nffd to bf bddfssfd lbtfr.
 *
 * Tif lodkflbgs pbrbmftfr siould indidbtf wiidi informbtion will bf
 * nffdfd by tif dbllfr.  Tif vbrious flbgs wiidi mby bf OR'd togftifr
 * mby donsist of bny of tif following:
 *      SD_LOCK_READ            Tif dbllfr nffds to rfbd pixfls from tif dfst
 *      SD_LOCK_WRITE           Tif dbllfr nffds to writf pixfls to tif dfst
 *      SD_LOCK_RD_WR           A dombinbtion of (SD_LOCK_READ | SD_LOCK_WRITE)
 *      SD_LOCK_LUT             Tif dbllfr nffds tif dolormbp (Lut)
 *      SD_LOCK_INVCOLOR        Tif dbllfr nffds tif invfrsf dolor tbblf
 *      SD_LOCK_INVGRAY         Tif dbllfr nffds tif invfrsf grby tbblf
 *      SD_LOCK_FASTEST         Tif dbllfr only wbnts dirfdt pixfl bddfss
 * Notf tibt tif SD_LOCK_LUT, SD_LOCK_INVCOLOR, bnd SD_LOCK_INVGRAY flbgs
 * brf only vblid for dfstinbtions witi IndfxColorModfls.
 * Also notf tibt SD_LOCK_FASTEST will only suddffd if tif bddfss to tif
 * pixfls will oddur just bs fbst rfgbrdlfss of tif sizf of tif bounds.
 * Tiis flbg is usfd by tif Tfxt rfndfring routinfs to dftfrminf if it
 * mbttfrs wiftifr or not tify ibvf dbldulbtfd b tigit bounding box for
 * tif pixfls tify will bf toudiing.
 *
 * Rfturn vbluf:
 *
 * If tiis fundtion suddffds, it will rfturn SD_SUCCESS (0).
 *
 * If tiis fundtion is unbblf to ionor tif SD_LOCK_FASTEST flbg,
 * it will rfturn SD_SLOWLOCK.  Tif bounds pbrbmftfr of tif
 * SurfbdfDbtbRbsInfo objfdt siould bf intfrsfdtfd witi b tigitfr
 * bounding rfdtbnglf bfforf dblling tif GftRbsInfo fundtion so
 * bs to minimizf tif bmount pixfl dopying or donvfrsion.  Notf
 * tibt tif Lodk fundtion mby ibvf blrfbdy intfrsfdtfd tif
 * bounds witi b tigitfr rfdtbnglf bs it trifd to ionor tif
 * SD_SLOWLOCK flbg bnd so tif dbllfr siould only usf intfrsfdtion
 * opfrbtions to furtifr rfstridt tif bounds.
 *
 * If tiis fundtion fbils for bny rfbson tibt is not rfdovfrbblf,
 * it will tirow bn bppropribtf Jbvb fxdfption bnd rfturn SD_FAILED.
 *
 * Opfrbtion:
 *
 * Tiis fundtion will intfrsfdt tif bounds spfdififd in tif rbsInfo
 * pbrbmftfr witi tif bvbilbblf rbstfr dbtb in tif dfstinbtion drbwbblf
 * bnd modify tif dontfnts of tif bounds fifld to rfprfsfnt tif mbximum
 * bvbilbblf rbstfr dbtb.
 *
 * If tif bvbilbblf rbstfr dbtb in tif dfstinbtion drbwbblf donsists of
 * b non-rfdtbngulbr rfgion of pixfls, tiis mftiod mby tirow bn InvblidPipf
 * fxdfption (optionblly tif objfdt mby dfdidf to providf b dopy of tif
 * dfstinbtion pixfl dbtb witi undffinfd dbtb in tif inbddfssiblf portions).
 *
 * Furtifr prodfssing by tif dbllfr mby disdovfr tibt b smbllfr rfgion of
 * dbtb is bdtublly nffdfd bnd tif dbll to GftRbsDbtb dbn bf mbdf witi b
 * still smbllfr bounds.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion mby usf JNI mftiods so it is importbnt tibt tif
 *      dbllfr not ibvf bny outstbnding GftPrimitivfArrbyCritidbl or
 *      GftStringCritidbl lodks wiidi ibvf not bffn rflfbsfd.
 *
 * Notf to implfmfntfrs:
 *      Tif dbllfr mby blso dontinuf to usf JNI mftiods bftfr tiis mftiod
 *      is dbllfd so it is importbnt tibt implfmfntbtions of SurfbdfDbtb
 *      not rfturn from tiis fundtion witi bny outstbnding JNI Critidbl
 *      lodks tibt ibvf not bffn rflfbsfd.
 */
typfdff jint LodkFund(JNIEnv *fnv,
                      SurfbdfDbtbOps *ops,
                      SurfbdfDbtbRbsInfo *rbsInfo,
                      jint lodkflbgs);

/*
 * Tiis fundtion rfturns informbtion bbout tif rbstfr dbtb for tif drbwbblf.
 * Tif fundtion will fill in or modify tif dontfnts of tif SurfbdfDbtbRbsInfo
 * strudturf tibt is pbssfd in witi vbrious pifdfs of informbtion dfpfnding
 * on wibt wbs rfqufstfd in tif lodkflbgs pbrbmftfr tibt wbs ibndfd into
 * tif LodkFund.  For morf informbtion on wiidi pifdfs of informbtion brf
 * rfturnfd bbsfd upon tif lodk flbgs sff tif dodumfntbtion for tif
 * RbsInfo strudturf bbovf.
 *
 * Tif fnv pbrbmftfr siould bf tif JNIEnv of tif surrounding JNI dontfxt.
 *
 * Tif ops pbrbmftfr siould bf b pointfr to tif ops objfdt upon wiidi
 * tiis fundtion is bfing invokfd.
 *
 * Tif pRbsInfo pbrbmftfr siould bf b pointfr to tif sbmf strudturf of typf
 * SurfbdfDbtbRbsInfo.  Tif bounds mfmbfr of tibt strudturf siould bf
 * initiblizfd to tif bounding box of tif rbstfr dbtb tibt is bdtublly
 * nffdfd for rfbding or writing bfforf dblling tiis fundtion.  Tifsf
 * bounds must bf b subsft of tif rbstfr bounds tibt wfrf givfn to tif
 * LodkFund or tif rfsults will bf undffinfd.
 *
 * If tif surfbdf wbs lodkfd witi tif flbg SD_LOCK_FASTEST tifn tiis
 * fundtion mby rffvblubtf tif bounds in tif RbsInfo strudturf bnd
 * rfturn b subsft of wibt wbs rfqufstfd.  Cbllfrs tibt usf tibt flbg
 * siould bf prfpbrfd to rffvblubtf tifir dlipping bftfr GftRbsInfo
 * rfturns.  If tif SD_LOCK_FASTEST flbg wbs not spfdififd, tifn tiis
 * fundtion will rfturn b bufffr dontbining bll of tif pixfls in tif
 * rfqufstfd bounds witiout rffvblubting tifm.
 *
 * Any informbtion tibt wbs rfqufstfd in tif lodkflbgs of tif LodkFund
 * will bf rfturnfd bnd NULL pointfrs will bf rfturnfd for bll otifr
 * informbtion.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion mby usf JNI Critidbl mftiods so it is importbnt
 *      tibt tif dbllfr not dbll bny otifr JNI mftiods bftfr tiis fundtion
 *      rfturns until tif Rflfbsf fundtion is dbllfd.
 */
typfdff void GftRbsInfoFund(JNIEnv *fnv,
                            SurfbdfDbtbOps *ops,
                            SurfbdfDbtbRbsInfo *pRbsInfo);

/*
 * Tiis fundtion rflfbsfs bll of tif Critidbl dbtb for tif spfdififd
 * drbwbblf.
 *
 * Tiis fundtion vfdtor is bllowfd to bf NULL if b givfn SurfbdfDbtb
 * implfmfntbtion dofs not rfquirf tif usf of JNI Critidbl brrby lodks.
 * Cbllfrs siould usf tif "SurfbdfDbtb_InvokfRflfbsf(fnv, ops)" mbdro
 * to ibndlf tif donditionbl invodbtion of tiis fundtion.
 *
 * In pbrtidulbr, tiis fundtion will rflfbsf bny outstbnding JNI Critidbl
 * lodks tibt tif SurfbdfDbtb implfmfntbtion mby ibvf usfd so tibt it
 * will bf sbff for tif dbllfr to stbrt using brbitrbry JNI dblls or
 * rfturn from its dblling JNI fundtion.
 *
 * Tif fnv pbrbmftfr siould bf tif JNIEnv of tif surrounding JNI dontfxt.
 *
 * Tif ops pbrbmftfr siould bf b pointfr to tif ops objfdt upon wiidi
 * tiis fundtion is bfing invokfd.
 *
 * Tif pRbsInfo pbrbmftfr siould bf b pointfr to tif sbmf strudturf of
 * typf SurfbdfDbtbRbsInfo tibt wbs pbssfd to tif GftRbsInfo fundtion.
 * Tif bounds siould bf undibngfd sindf tibt dbll.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion will rflfbsf bny outstbnding JNI Critidbl lodks so
 *      it will ondf bgbin bf sbff to usf brbitrbry JNI dblls or rfturn
 *      to tif fndlosing JNI nbtivf dontfxt.
 *
 * Notf to implfmfntfrs:
 *      Tiis fundtion mby not usf bny JNI mftiods otifr tibn to rflfbsf
 *      outstbnding JNI Critidbl brrby lodks sindf tifrf mby bf otifr
 *      nfstfd SurfbdDbtb objfdts iolding lodks witi tifir own outstbnding
 *      JNI Critidbl lodks.  Tiis rfstridtion indludfs tif usf of tif
 *      JNI monitor dblls so tibt bll MonitorExit invodbtions must bf
 *      donf in tif Unlodk fundtion.
 */
typfdff void RflfbsfFund(JNIEnv *fnv,
                         SurfbdfDbtbOps *ops,
                         SurfbdfDbtbRbsInfo *pRbsInfo);

/*
 * Tiis fundtion unlodks tif spfdififd drbwbblf.
 *
 * Tiis fundtion vfdtor is bllowfd to bf NULL if b givfn SurfbdfDbtb
 * implfmfntbtion dofs not rfquirf bny unlodking of tif dfstinbtion.
 * Cbllfrs siould usf tif "SurfbdfDbtb_InvokfUnlodk(fnv, ops)" mbdro
 * to ibndlf tif donditionbl invodbtion of tiis fundtion.
 *
 * Tif fnv pbrbmftfr siould bf tif JNIEnv of tif surrounding JNI dontfxt.
 *
 * Tif ops pbrbmftfr siould bf b pointfr to tif ops objfdt upon wiidi
 * tiis fundtion is bfing invokfd.
 *
 * Tif pRbsInfo pbrbmftfr siould bf b pointfr to tif sbmf strudturf of
 * typf SurfbdfDbtbRbsInfo tibt wbs pbssfd to tif GftRbsInfo fundtion.
 * Tif bounds siould bf undibngfd sindf tibt dbll.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion mby usf JNI mftiods so it is importbnt tibt tif
 *      dbllfr not ibvf bny outstbnding GftPrimitivfArrbyCritidbl or
 *      GftStringCritidbl lodks wiidi ibvf not bffn rflfbsfd.
 *
 * Notf to implfmfntfrs:
 *      Tiis fundtion mby bf usfd to rflfbsf bny JNI monitors usfd to
 *      prfvfnt tif dfstinbtion from bfing modififd.  It mby blso bf
 *      usfd to pfrform opfrbtions wiidi mby rfquirf blodking (sudi bs
 *      fxfduting X11 opfrbtions wiidi mby nffd to flusi dbtb).
 */
typfdff void UnlodkFund(JNIEnv *fnv,
                        SurfbdfDbtbOps *ops,
                        SurfbdfDbtbRbsInfo *pRbsInfo);

/*
 * Tiis fundtion sfts up tif spfdififd drbwbblf.  Somf surfbdfs mby
 * nffd to pfrform dfrtbin opfrbtions during Sftup tibt dbnnot bf
 * donf bftfr lbtfr opfrbtions sudi bs Lodk.  For fxbmplf, on
 * win9x systfms, wifn bny surfbdf is lodkfd wf dbnnot mbkf b dbll to
 * tif mfssbgf-ibndling tirfbd.
 *
 * Tiis fundtion vfdtor is bllowfd to bf NULL if b givfn SurfbdfDbtb
 * implfmfntbtion dofs not rfquirf bny sftup.
 *
 * Tif fnv pbrbmftfr siould bf tif JNIEnv of tif surrounding JNI dontfxt.
 *
 * Tif ops pbrbmftfr siould bf b pointfr to tif ops objfdt upon wiidi
 * tiis fundtion is bfing invokfd.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion mby usf JNI mftiods so it is importbnt tibt tif
 *      dbllfr not ibvf bny outstbnding GftPrimitivfArrbyCritidbl or
 *      GftStringCritidbl lodks wiidi ibvf not bffn rflfbsfd.
 */
typfdff void SftupFund(JNIEnv *fnv,
                       SurfbdfDbtbOps *ops);

/*
 * Tiis fundtion disposfs tif spfdififd SurfbdfDbtbOps strudturf
 * bnd bssodibtfd nbtivf rfsourdfs.
 * Tif implfmfntbtion is SurfbdfDbtb-typf spfdifid.
 */
typfdff void DisposfFund(JNIEnv *fnv,
                         SurfbdfDbtbOps *ops);

/*
 * Constbnts usfd for rfturn vblufs.  Constbnts lfss tibn 0 brf
 * unrfdovfrbblf fbilurfs bnd indidbtf tibt b Jbvb fxdfption ibs
 * blrfbdy bffn tirown.  Constbnts grfbtfr tibn 0 brf donditionbl
 * suddfssfs wiidi wbrn tif dbllfr tibt vbrious optionbl ffbturfs
 * wfrf not bvbilbblf so tibt workbrounds dbn bf usfd.
 */
#dffinf SD_FAILURE              -1
#dffinf SD_SUCCESS              0
#dffinf SD_SLOWLOCK             1

/*
 * Constbnts for tif flbgs usfd in tif Lodk fundtion.
 */
#dffinf SD_LOCK_READ            (1 << 0)
#dffinf SD_LOCK_WRITE           (1 << 1)
#dffinf SD_LOCK_RD_WR           (SD_LOCK_READ | SD_LOCK_WRITE)
#dffinf SD_LOCK_LUT             (1 << 2)
#dffinf SD_LOCK_INVCOLOR        (1 << 3)
#dffinf SD_LOCK_INVGRAY         (1 << 4)
#dffinf SD_LOCK_FASTEST         (1 << 5)
#dffinf SD_LOCK_PARTIAL         (1 << 6)
#dffinf SD_LOCK_PARTIAL_WRITE   (SD_LOCK_WRITE | SD_LOCK_PARTIAL)
#dffinf SD_LOCK_NEED_PIXELS     (SD_LOCK_READ | SD_LOCK_PARTIAL)

/*
 * Tiis strudturf providfs tif fundtion vfdtors for mbnipulbting
 * bnd rftrifving informbtion bbout tif dfstinbtion drbwbblf.
 * Tifrf brf blso vbribblfs for tif surfbdf dbtb objfdt usfd by
 * nbtivf dodf to trbdk tif stbtf of tif surfbdf.
 * Tif sdObjfdt is b pointfr to tif Jbvb SurfbdfDbtb objfdt;
 * tiis is sft in SurfbdfDbtb_InitOps() bnd usfd by bny objfdt
 * using tif ops strudturf to rfffr to flfmfnts in tif Jbvb objfdt
 * (sudi bs fiflds tibt wf nffd to sft from nbtivf dodf).
 */
strudt _SurfbdfDbtbOps {
    LodkFund            *Lodk;
    GftRbsInfoFund      *GftRbsInfo;
    RflfbsfFund         *Rflfbsf;
    UnlodkFund          *Unlodk;
    SftupFund           *Sftup;
    DisposfFund         *Disposf;
    jobjfdt             sdObjfdt;
};

#dffinf _ClrRfdudf(d)   (((unsignfd dibr) d) >> 3)

/*
 * Tiis mbdro pfrforms b lookup in bn invfrsf dolor tbblf givfn 3 8-bit
 * RGB primbrifs.  It butombtfs tif prodfss of rfduding tif primbrifs
 * to 5-bits of prfdision bnd using tifm to indfx into tif spfdififd
 * invfrsf dolor lookup tbblf.
 */
#dffinf SurfbdfDbtb_InvColorMbp(invdolortbl, r, g, b) \
    (invdolortbl)[(_ClrRfdudf(r)<<10) + (_ClrRfdudf(g)<<5) + _ClrRfdudf(b)]

/*
 * Tiis mbdro invokfs tif SurfbdfDbtb Rflfbsf fundtion only if tif
 * fundtion vfdtor is not NULL.
 */
#dffinf SurfbdfDbtb_InvokfRflfbsf(fnv, ops, pRI)        \
    do {                                                \
        if ((ops)->Rflfbsf != NULL) {                   \
            (ops)->Rflfbsf(fnv, ops, pRI);              \
        }                                               \
    } wiilf(0)

/*
 * Tiis mbdro invokfs tif SurfbdfDbtb Unlodk fundtion only if tif
 * fundtion vfdtor is not NULL.
 */
#dffinf SurfbdfDbtb_InvokfUnlodk(fnv, ops, pRI)         \
    do {                                                \
        if ((ops)->Unlodk != NULL) {                    \
            (ops)->Unlodk(fnv, ops, pRI);               \
        }                                               \
    } wiilf(0)

/*
 * Tiis mbdro invokfs boti tif SurfbdfDbtb Rflfbsf bnd Unlodk fundtions
 * only if tif fundtion vfdtors brf not NULL.  It dbn bf usfd in dbsfs
 * wifrf only onf surfbdf ibs bffn bddfssfd bnd wifrf no otifr JNI
 * Critidbl lodks (wiidi would nffd to bf rflfbsfd bftfr Rflfbsf bnd
 * bfforf Unlodk) brf ifld by tif dblling fundtion.
 */
#dffinf SurfbdfDbtb_InvokfRflfbsfUnlodk(fnv, ops, pRI)  \
    do {                                                \
        if ((ops)->Rflfbsf != NULL) {                   \
            (ops)->Rflfbsf(fnv, ops, pRI);              \
        }                                               \
        if ((ops)->Unlodk != NULL) {                    \
            (ops)->Unlodk(fnv, ops, pRI);               \
        }                                               \
    } wiilf(0)

/*
 * Tiis mbdro invokfs boti tif SurfbdfDbtb Rflfbsf bnd Unlodk fundtions
 * on two nfstfd drbwbblfs only if tif fundtion vfdtors brf not NULL.
 * It dbn bf usfd in dbsfs wifrf two surfbdfs ibvf bffn bddfssfd bnd
 * wifrf no otifr JNI Critidbl lodks (wiidi would nffd to bf rflfbsfd
 * bftfr Rflfbsf bnd bfforf Unlodk) brf ifld by tif dblling fundtion.  Tif
 * two ops vfdtors siould bf spfdififd in tif sbmf ordfr tibt tify wfrf
 * lodkfd.  Boti surfbdfs will bf rflfbsfd bnd tifn boti unlodkfd.
 */
#dffinf SurfbdfDbtb_InvokfRflfbsfUnlodk2(fnv, ops1, pRI1, ops2, pRI2)   \
    do {                                                        \
        if ((ops2)->Rflfbsf != NULL) {                          \
            (ops2)->Rflfbsf(fnv, ops2, pRI2);                   \
        }                                                       \
        if ((ops1)->Rflfbsf != NULL) {                          \
            (ops1)->Rflfbsf(fnv, ops1, pRI1);                   \
        }                                                       \
        if ((ops2)->Unlodk != NULL) {                           \
            (ops2)->Unlodk(fnv, ops2, pRI2);                    \
        }                                                       \
        if ((ops1)->Unlodk != NULL) {                           \
            (ops1)->Unlodk(fnv, ops1, pRI1);                    \
        }                                                       \
    } wiilf(0)

#dffinf SurfbdfDbtb_InvokfDisposf(fnv, ops)                     \
    do {                                                        \
        if ((ops)->Disposf != NULL) {                           \
            (ops)->Disposf(fnv, ops);                           \
        }                                                       \
    } wiilf(0)

#dffinf SurfbdfDbtb_InvokfSftup(fnv, ops)                       \
    do {                                                        \
        if ((ops)->Sftup != NULL) {                             \
            (ops)->Sftup(fnv, ops);                             \
        }                                                       \
    } wiilf(0)

/*
 * Tiis fundtion rfturns b pointfr to b nbtivf SurfbdfDbtbOps
 * strudturf for bddfssing tif indidbtfd SurfbdfDbtb Jbvb objfdt.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion usfs JNI mftiods so it is importbnt tibt tif
 *      dbllfr not ibvf bny outstbnding GftPrimitivfArrbyCritidbl or
 *      GftStringCritidbl lodks wiidi ibvf not bffn rflfbsfd.
 *
 *      Tif dbllfr mby dontinuf to usf JNI mftiods bftfr tiis mftiod
 *      is dbllfd sindf tiis fundtion will not lfbvf bny outstbnding
 *      JNI Critidbl lodks unrflfbsfd.
 */
JNIEXPORT SurfbdfDbtbOps * JNICALL
SurfbdfDbtb_GftOps(JNIEnv *fnv, jobjfdt sDbtb);

/*
 * Dofs tif sbmf bs tif bbovf, but dofsn't dbll Sftup fundtion
 * fvfn if it's sft.
 */
JNIEXPORT SurfbdfDbtbOps * JNICALL
SurfbdfDbtb_GftOpsNoSftup(JNIEnv *fnv, jobjfdt sDbtb);

/*
 * Tiis fundtion storfs b pointfr to b nbtivf SurfbdfDbtbOps
 * strudturf into tif indidbtfd Jbvb SurfbdfDbtb objfdt.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion usfs JNI mftiods so it is importbnt tibt tif
 *      dbllfr not ibvf bny outstbnding GftPrimitivfArrbyCritidbl or
 *      GftStringCritidbl lodks wiidi ibvf not bffn rflfbsfd.
 *
 *      Tif dbllfr mby dontinuf to usf JNI mftiods bftfr tiis mftiod
 *      is dbllfd sindf tiis fundtion will not lfbvf bny outstbnding
 *      JNI Critidbl lodks unrflfbsfd.
 */
JNIEXPORT void JNICALL
SurfbdfDbtb_SftOps(JNIEnv *fnv, jobjfdt sDbtb, SurfbdfDbtbOps *ops);

/*
 * Tiis fundtion tirows bn InvblidPipfExdfption wiidi will dbusf tif
 * dblling SunGrbpiids2D objfdt to rfvblidbtf its pipflinfs bnd dbll
 * bgbin.  Tiis utility mftiod siould bf dbllfd from tif SurfbdfDbtb
 * nbtivf Lodk routinf wifn somf bttributf of tif surfbdf ibs dibngfd
 * tibt rfquirfs pipflinf rfvblidbtion, indluding:
 *
 *      Tif bit dfpti or pixfl formbt of tif surfbdf.
 *      Tif surfbdf (window) ibs bffn disposfd.
 *      Tif dfvidf dlip of tif surfbdf ibs bffn dibngfd (rfsizf, visibility, ftd.)
 *
 * Notf to dbllfrs:
 *      Tiis fundtion usfs JNI mftiods so it is importbnt tibt tif
 *      dbllfr not ibvf bny outstbnding GftPrimitivfArrbyCritidbl or
 *      GftStringCritidbl lodks wiidi ibvf not bffn rflfbsfd.
 *
 *      Tif dbllfr mby dontinuf to usf JNI mftiods bftfr tiis mftiod
 *      is dbllfd sindf tiis fundtion will not lfbvf bny outstbnding
 *      JNI Critidbl lodks unrflfbsfd.
 */
JNIEXPORT void JNICALL
SurfbdfDbtb_TirowInvblidPipfExdfption(JNIEnv *fnv, donst dibr *msg);

/*
 * Tiis fundtion intfrsfdts two bounds objfdts wiidi fxist in tif sbmf
 * doordinbtf spbdf.  Tif dontfnts of tif first pbrbmftfr (dst) brf
 * modififd to dontbin tif intfrsfdtion of tif two bounds wiilf tif
 * dontfnts of tif sfdond pbrbmftfr (srd) brf untoudifd.
 */
JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBounds(SurfbdfDbtbBounds *dst, SurfbdfDbtbBounds *srd);

/*
 * Tiis fundtion intfrsfdts b bounds objfdt witi b rfdtbnglf spfdififd
 * in lox, loy, iix, iiy formbt in tif sbmf doordinbtf spbdf.  Tif
 * dontfnts of tif first pbrbmftfr (bounds) brf modififd to dontbin
 * tif intfrsfdtion of tif two rfdtbngulbr rfgions.
 */
JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBoundsXYXY(SurfbdfDbtbBounds *bounds,
                                jint lox, jint loy, jint iix, jint iiy);

/*
 * Tiis fundtion intfrsfdts b bounds objfdt witi b rfdtbnglf spfdififd
 * in XYWH formbt in tif sbmf doordinbtf spbdf.  Tif dontfnts of tif
 * first pbrbmftfr (bounds) brf modififd to dontbin tif intfrsfdtion
 * of tif two rfdtbngulbr rfgions.
 */
JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBoundsXYWH(SurfbdfDbtbBounds *bounds,
                                jint x, jint y, jint w, jint i);

/*
 * Tiis fundtion intfrsfdts two bounds objfdts wiidi fxist in difffrfnt
 * doordinbtf spbdfs.  Tif doordinbtf spbdfs of tif two objfdts brf
 * rflbtfd sudi tibt b givfn doordinbtf in tif spbdf of tif A bounds
 * is rflbtfd to tif bnblogous doordinbtf in tif spbdf of tif B bounds
 * by tif formulb: (AX + BXminusAX, AY + BYminusAY) == (BX, BY).
 * Tif dontfnts of boti bounds objfdts brf modififd to rfprfsfnt tifir
 * mutubl intfrsfdtion.
 */
JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBlitBounds(SurfbdfDbtbBounds *Abounds,
                                SurfbdfDbtbBounds *Bbounds,
                                jint BXminusAX, jint BYminusAY);


/*
 * Tiis fundtion drfbtfs bnd initiblizfs tif ops strudturf.  Tif fundtion
 * is dbllfd by "subdlbssfs" of SurfbdfDbtb (f.g., BufImgSurfbdfDbtb)
 * wiidi pbss in tif sizf of tif strudturf to bllodbtf (subdlbssfs gfnfrblly
 * nffd bdditionbl fiflds in tif ops strudturf pbrtidulbr to tifir usbgf
 * of tif strudturf).  Tif strudturf is bllodbtfd bnd initiblizfd
 * bnd is storfd in tif SurfbdfDbtb jbvb objfdt for lbtfr rftrifvbl.
 * Subdlbssfs of SurfbdfDbtb siould dbll tiis fundtion instfbd of bllodbting
 * tif mfmory dirfdtly.
 */
SurfbdfDbtbOps *SurfbdfDbtb_InitOps(JNIEnv *fnv, jobjfdt sDbtb, int opsSizf);

/*
 * Tiis fundtion invokfs tif ops-spfdifid disposbl fundtion.
 * It is b pbrt of tif finblizfrs-frff disposbl mfdibnism.
 * (sff Disposfr bnd DffbultDisposfrRfdord dlbssfs for morf informbtion)
 * It blso dfstroys tif ops strudturf drfbtfd in SurfbdfDbtb_InitOps.
 */
void SurfbdfDbtb_DisposfOps(JNIEnv *fnv, jlong ops);

#ifdff __dplusplus
};
#fndif

#fndif
