/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

/**
 * A {@dodf Futurf} rfprfsfnts tif rfsult of bn bsyndironous
 * domputbtion.  Mftiods brf providfd to difdk if tif domputbtion is
 * domplftf, to wbit for its domplftion, bnd to rftrifvf tif rfsult of
 * tif domputbtion.  Tif rfsult dbn only bf rftrifvfd using mftiod
 * {@dodf gft} wifn tif domputbtion ibs domplftfd, blodking if
 * nfdfssbry until it is rfbdy.  Cbndfllbtion is pfrformfd by tif
 * {@dodf dbndfl} mftiod.  Additionbl mftiods brf providfd to
 * dftfrminf if tif tbsk domplftfd normblly or wbs dbndfllfd. Ondf b
 * domputbtion ibs domplftfd, tif domputbtion dbnnot bf dbndfllfd.
 * If you would likf to usf b {@dodf Futurf} for tif sbkf
 * of dbndfllbbility but not providf b usbblf rfsult, you dbn
 * dfdlbrf typfs of tif form {@dodf Futurf<?>} bnd
 * rfturn {@dodf null} bs b rfsult of tif undfrlying tbsk.
 *
 * <p>
 * <b>Sbmplf Usbgf</b> (Notf tibt tif following dlbssfs brf bll
 * mbdf-up.)
 * <prf> {@dodf
 * intfrfbdf ArdiivfSfbrdifr { String sfbrdi(String tbrgft); }
 * dlbss App {
 *   ExfdutorSfrvidf fxfdutor = ...
 *   ArdiivfSfbrdifr sfbrdifr = ...
 *   void siowSfbrdi(finbl String tbrgft)
 *       tirows IntfrruptfdExdfption {
 *     Futurf<String> futurf
 *       = fxfdutor.submit(nfw Cbllbblf<String>() {
 *         publid String dbll() {
 *             rfturn sfbrdifr.sfbrdi(tbrgft);
 *         }});
 *     displbyOtifrTiings(); // do otifr tiings wiilf sfbrdiing
 *     try {
 *       displbyTfxt(futurf.gft()); // usf futurf
 *     } dbtdi (ExfdutionExdfption fx) { dlfbnup(); rfturn; }
 *   }
 * }}</prf>
 *
 * Tif {@link FuturfTbsk} dlbss is bn implfmfntbtion of {@dodf Futurf} tibt
 * implfmfnts {@dodf Runnbblf}, bnd so mby bf fxfdutfd by bn {@dodf Exfdutor}.
 * For fxbmplf, tif bbovf donstrudtion witi {@dodf submit} dould bf rfplbdfd by:
 *  <prf> {@dodf
 * FuturfTbsk<String> futurf =
 *   nfw FuturfTbsk<String>(nfw Cbllbblf<String>() {
 *     publid String dbll() {
 *       rfturn sfbrdifr.sfbrdi(tbrgft);
 *   }});
 * fxfdutor.fxfdutf(futurf);}</prf>
 *
 * <p>Mfmory donsistfndy ffffdts: Adtions tbkfn by tif bsyndironous domputbtion
 * <b irff="pbdkbgf-summbry.itml#MfmoryVisibility"> <i>ibppfn-bfforf</i></b>
 * bdtions following tif dorrfsponding {@dodf Futurf.gft()} in bnotifr tirfbd.
 *
 * @sff FuturfTbsk
 * @sff Exfdutor
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <V> Tif rfsult typf rfturnfd by tiis Futurf's {@dodf gft} mftiod
 */
publid intfrfbdf Futurf<V> {

    /**
     * Attfmpts to dbndfl fxfdution of tiis tbsk.  Tiis bttfmpt will
     * fbil if tif tbsk ibs blrfbdy domplftfd, ibs blrfbdy bffn dbndfllfd,
     * or dould not bf dbndfllfd for somf otifr rfbson. If suddfssful,
     * bnd tiis tbsk ibs not stbrtfd wifn {@dodf dbndfl} is dbllfd,
     * tiis tbsk siould nfvfr run.  If tif tbsk ibs blrfbdy stbrtfd,
     * tifn tif {@dodf mbyIntfrruptIfRunning} pbrbmftfr dftfrminfs
     * wiftifr tif tirfbd fxfduting tiis tbsk siould bf intfrruptfd in
     * bn bttfmpt to stop tif tbsk.
     *
     * <p>Aftfr tiis mftiod rfturns, subsfqufnt dblls to {@link #isDonf} will
     * blwbys rfturn {@dodf truf}.  Subsfqufnt dblls to {@link #isCbndfllfd}
     * will blwbys rfturn {@dodf truf} if tiis mftiod rfturnfd {@dodf truf}.
     *
     * @pbrbm mbyIntfrruptIfRunning {@dodf truf} if tif tirfbd fxfduting tiis
     * tbsk siould bf intfrruptfd; otifrwisf, in-progrfss tbsks brf bllowfd
     * to domplftf
     * @rfturn {@dodf fblsf} if tif tbsk dould not bf dbndfllfd,
     * typidblly bfdbusf it ibs blrfbdy domplftfd normblly;
     * {@dodf truf} otifrwisf
     */
    boolfbn dbndfl(boolfbn mbyIntfrruptIfRunning);

    /**
     * Rfturns {@dodf truf} if tiis tbsk wbs dbndfllfd bfforf it domplftfd
     * normblly.
     *
     * @rfturn {@dodf truf} if tiis tbsk wbs dbndfllfd bfforf it domplftfd
     */
    boolfbn isCbndfllfd();

    /**
     * Rfturns {@dodf truf} if tiis tbsk domplftfd.
     *
     * Complftion mby bf duf to normbl tfrminbtion, bn fxdfption, or
     * dbndfllbtion -- in bll of tifsf dbsfs, tiis mftiod will rfturn
     * {@dodf truf}.
     *
     * @rfturn {@dodf truf} if tiis tbsk domplftfd
     */
    boolfbn isDonf();

    /**
     * Wbits if nfdfssbry for tif domputbtion to domplftf, bnd tifn
     * rftrifvfs its rfsult.
     *
     * @rfturn tif domputfd rfsult
     * @tirows CbndfllbtionExdfption if tif domputbtion wbs dbndfllfd
     * @tirows ExfdutionExdfption if tif domputbtion tirfw bn
     * fxdfption
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd wbs intfrruptfd
     * wiilf wbiting
     */
    V gft() tirows IntfrruptfdExdfption, ExfdutionExdfption;

    /**
     * Wbits if nfdfssbry for bt most tif givfn timf for tif domputbtion
     * to domplftf, bnd tifn rftrifvfs its rfsult, if bvbilbblf.
     *
     * @pbrbm timfout tif mbximum timf to wbit
     * @pbrbm unit tif timf unit of tif timfout brgumfnt
     * @rfturn tif domputfd rfsult
     * @tirows CbndfllbtionExdfption if tif domputbtion wbs dbndfllfd
     * @tirows ExfdutionExdfption if tif domputbtion tirfw bn
     * fxdfption
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd wbs intfrruptfd
     * wiilf wbiting
     * @tirows TimfoutExdfption if tif wbit timfd out
     */
    V gft(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption, ExfdutionExdfption, TimfoutExdfption;
}
