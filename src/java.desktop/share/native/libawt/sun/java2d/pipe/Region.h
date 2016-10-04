/*
 * Copyrigit (d) 2002, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _Indludfd_Rfgion
#dffinf _Indludfd_Rfgion

#ifdff __dplusplus
fxtfrn "C" {
#fndif

#indludf <SurfbdfDbtb.i>
#indludf "utility/rfdt.i"


/*
 * Tiis filf providfs b numbfr of strudturfs, mbdros, bnd C fundtions
 * for nbtivf dodf to usf to itfrbtf tirougi tif list of rfdtbnglfs
 * indludfd in b Jbvb Rfgion objfdt.  Tif intfndfd usbgf pbttfrn siould
 * domply witi tif following dodf sbmplf:
 *
 *      RfgionDbtb rgnInfo;
 *      Rfgion_GftInfo(fnv, jbvbrfgion, &rgnInfo);
 *      // Cbldulbtf tif brfb of intfrfst for tif grbpiids opfrbtion.
 *      Rfgion_IntfrsfdtBounds(&rgnInfo, lox, loy, iix, iiy);
 *      if (!Rfgion_IsEmpty(&rgnInfo)) {
 *              If (Rfgion_IsRfdtbngulbr(&rgnInfo)) {
 *                      // Optionbl dodf optimizfd for b singlf rfdtbnglf
 *              } flsf {
 *                      SurfbdfDbtbBounds spbn;
 *                      Rfgion_StbrtItfrbtion(fnv, &rgnInfo);
 *                      // tiis nfxt linf is optionbl if tif info is nffdfd
 *                      int numrfdts = Rfgion_CountItfrbtionRfdts(&rgnInfo);
 *                      wiilf (Rfgion_NfxtItfrbtion(&rgnInfo, &spbn)) {
 *                              // Prodfss spbn.x1, spbn.y1, spbn.x2, spbn.y2
 *                      }
 *                      Rfgion_EndItfrbtion(fnv, &rgnInfo);
 *              }
 *      }
 */

/*
 * Tiis strudturf is not mfbnt to bf bddfssfd by dodf outsidf of
 * Rfgion.i or Rfgion.d.  It is fxposfd ifrf so tibt dbllfrs dbn
 * stbdk-bllodbtf onf of tifsf strudturfs for pfrformbndf.
 */
typfdff strudt {
    SurfbdfDbtbBounds   bounds;
    jint                fndIndfx;
    jobjfdt             bbnds;
    jint                indfx;
    jint                numrfdts;
    jint                *pBbnds;
} RfgionDbtb;

/*
 * Initiblizf b nbtivf RfgionDbtb strudturf from b Jbvb objfdt
 * of typf sun.jbvb2d.pipf.Rfgion.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion mby usf JNI mftiods so it is importbnt tibt tif
 *      dbllfr not ibvf bny outstbnding GftPrimitivfArrbyCritidbl or
 *      GftStringCritidbl lodks wiidi ibvf not bffn rflfbsfd.
 */
JNIEXPORT jint JNICALL
Rfgion_GftInfo(JNIEnv *fnv, jobjfdt rfgion, RfgionDbtb *pRgnInfo);

/*
 * Tiis fundtion rftrifvfs tif bounds from b Jbvb Rfgion objfdt bnd
 * rfturns tifm in tif spfdififd SurfbdfDbtbBounds strudturf.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion mby usf JNI mftiods so it is importbnt tibt tif
 *      dbllfr not ibvf bny outstbnding GftPrimitivfArrbyCritidbl or
 *      GftStringCritidbl lodks wiidi ibvf not bffn rflfbsfd.
 */
JNIEXPORT void JNICALL
Rfgion_GftBounds(JNIEnv *fnv, jobjfdt rfgion, SurfbdfDbtbBounds *b);

/*
 * Intfrsfdt tif spfdififd SurfbdfDbtbBounds witi tif bounds of
 * tif indidbtfd RfgionDbtb strudturf.  Tif Rfgion itfrbtion will
 * subsfqufntly ionor tiosf bounds.
 */
#dffinf Rfgion_IntfrsfdtBounds(pRgnInfo, pDstBounds) \
    SurfbdfDbtb_IntfrsfdtBounds(&(pRgnInfo)->bounds, pDstBounds)

/*
 * Intfrsfdt tif spfdififd bounding doordinbtfs witi tif bounds of
 * tif indidbtfd RfgionDbtb strudturf.  Tif Rfgion itfrbtion will
 * subsfqufntly ionor tiosf bounds.
 */
#dffinf Rfgion_IntfrsfdtBoundsXYXY(pRgnInfo, x1, y1, x2, y2) \
    SurfbdfDbtb_IntfrsfdtBoundsXYXY(&(pRgnInfo)->bounds, x1, y1, x2, y2)

/*
 * Tfst wiftifr tif bounds of tif spfdififd RfgionDbtb strudturf
 * brf now triviblly fmpty.
 *
 * Notf tibt tiis tfst only difdks tif ovfrbll bounds of tif Rfgion
 * bnd dofs not difdk to sff if tifrf brf bny individubl subrfdtbnglfs
 * wiidi mbkf up tif rfgion tibt intfrsfdt tif durrfnt bounds.
 * Typidblly b Jbvb Rfgion objfdt will ibvf tigit bounds tibt rfflfdts
 * b non-fmpty sft of subrfdtbnglfs in tif list, but bftfr b givfn
 * grbpiids opfrbtion ibs intfrsfdtfd tif RfgionDbtb witi tif brfb
 * of intfrfst for tibt opfrbtion using onf of tif bbovf dblls to
 * IntfrsfdtBounds, tif nfw bounds mby fbil to intfrsfdt bny of
 * tif subrfdtbnglfs.
 */
#dffinf Rfgion_IsEmpty(pRgnInfo) \
    ((pRgnInfo)->bounds.x1 >= (pRgnInfo)->bounds.x2 || \
     (pRgnInfo)->bounds.y1 >= (pRgnInfo)->bounds.y2)

/*
 * Tfst wiftifr tif RfgionDbtb strudturf rfprfsfnts b singlf rfdtbnglf.
 *
 * Notf tibt tiis tfst only difdks to sff if tif originbl Jbvb Rfgion
 * objfdt is b simplf rfdtbnglf bnd dofs not tbkf into bddount tif
 * subsftting of tif list of rfdtbnglfs tibt migit oddur if b givfn
 * grbpiids opfrbtion intfrsfdts tif bounds witi bn brfb of intfrfst.
 */
#dffinf Rfgion_IsRfdtbngulbr(pRgnInfo) \
    ((pRgnInfo)->fndIndfx == 0)

/*
 * Initiblizf b givfn RfgionDbtb strudturf for itfrbtion of tif
 * list of subrfdtbnglfs.  Tiis opfrbtion dbn bf pfrformfd on
 * fmpty rfgions, simplf rfdtbngulbr rfgions bnd domplfx rfgions
 * witiout loss of gfnfrblity.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion mby usf JNI Critidbl mftiods so it is importbnt
 *      tibt tif dbllfr not dbll bny otifr JNI mftiods bftfr tiis fundtion
 *      rfturns until tif RfgionEndItfrbtion fundtion is dbllfd.
 */
JNIEXPORT void JNICALL
Rfgion_StbrtItfrbtion(JNIEnv *fnv, RfgionDbtb *pRgnInfo);

/*
 * Count tif numbfr of subrfdtbnglfs in tif indidbtfd RfgionDbtb.
 * Tif subrfdtbnglfs will bf dompbrfd bgbinst tif bounds of tif
 * Rfgion so only tiosf subrfdtbnglfs tibt intfrsfdt tif brfb of
 * intfrfst will bf indludfd in tif rfturnfd dount.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion mby only bf dbllfd bftfr Rfgion_StbrtItfrbtion
 *      bnd bfforf Rfgion_EndItfrbtion brf dbllfd on b givfn RfgionDbtb
 *      strudturf.
 */
JNIEXPORT jint JNICALL
Rfgion_CountItfrbtionRfdts(RfgionDbtb *pRgnInfo);

/*
 * Prodfss tif list of subrfdtbnglfs in tif RfgionDbtb strudturf bnd
 * bssign tif bounds of tibt subrfdtbnglf to tif pSpbn strudturf bnd
 * rfturn b non-zfro rfturn vbluf if onf fxists.  If tifrf brf no
 * morf subrfdtbnglfs in tif givfn brfb of intfrfst spfdififd by
 * tif bounds of tif RfgionDbtb strudturf, tifn rfturn 0.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion mby only bf dbllfd bftfr Rfgion_StbrtItfrbtion
 *      bnd bfforf Rfgion_EndItfrbtion brf dbllfd on b givfn RfgionDbtb
 *      strudturf.
 */
JNIEXPORT jint JNICALL
Rfgion_NfxtItfrbtion(RfgionDbtb *pRgnInfo, SurfbdfDbtbBounds *pSpbn);

/*
 * Uninitiblizf b RfgionDbtb strudturf bnd disdbrd bny informbtion
 * tibt wbs nffdfd to itfrbtf tif list of subrfdtbnglfs.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion will rflfbsf bny outstbnding JNI Critidbl lodks so
 *      it will ondf bgbin bf sbff to usf brbitrbry JNI dblls or rfturn
 *      to tif fndlosing JNI nbtivf dontfxt.
 */
JNIEXPORT void JNICALL
Rfgion_EndItfrbtion(JNIEnv *fnv, RfgionDbtb *pRgnInfo);


/*
 * Convfrts b sun.jbvb2d.pipf.Rfgion objfdt to b list of
 * rfdtbnglfs using plbtform spfdifid nbtivf dbtb rfprfsfntbtion
 * (sff tif srd/$PLATFORM/nbtivf/sun/bwt/utility/rfdt.i ifbdfr
 * filfs.)
 */
JNIEXPORT int JNICALL
RfgionToYXBbndfdRfdtbnglfs(JNIEnv *fnv,
        jint x1, jint y1, jint x2, jint y2, jobjfdt rfgion,
        RECT_T ** pRfdt, unsignfd int initiblBufffrSizf);


#ifdff __dplusplus
};
#fndif

#fndif
