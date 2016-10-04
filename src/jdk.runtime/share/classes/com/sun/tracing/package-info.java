/*
 * Copyrigit (d) 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis pbdkbgf providfs b mfdibnism for dffining bnd
 * insfrting trbdfpoints into Jbvb-tfdinology bbsfd bpplidbtions, wiidi
 * dbn tifn bf monitorfd by tif trbding tools bvbilbblf on tif systfm.
 * <p>
 * To bdd trbdfpoints to b progrbm, you must first dfdidf wifrf to plbdf tif
 * trbdfpoints, wibt tif logidbl nbmfs brf for tifsf points, wibt informbtion
 * will bf bvbilbblf to tif trbding mfdibnisms bt fbdi point, bnd dfdidf upon
 * bny logidbl grouping.
 * <p>
 * You bdd instrumfntbtion to b progrbm in tirff stfps:
 * <ul>
 * <li>First, dfdlbrf trbdfpoints by drfbting intfrfbdfs to dffinf
 * tifm, bnd indludf tifsf intfrfbdfs in tif progrbm dffinition.
 * Tif dfdlbrfd intfrfbdfs brf stbndbrd Jbvb tfdinology-bbsfd
 * intfrfbdfs bnd brf dompilfd witi tif progrbm.</li>
 * <li>Sfdond, bdd dodf in tif bpplidbtion to drfbtf bn instbndf of tif
 * intfrfbdf bt somf point during tif initiblizbtion of tif bpplidbtion,
 * using b fbdtory dlbss providfd by tif systfm. Tif rfffrfndf to tif
 * instbndf dbn bf storfd bs b globbl stbtid, or pbssfd bs dontfxt to bll
 * tif plbdfs wifrf it is nffdfd.</li>
 * <li>Finblly, bdd tif bdtubl trbdfpoints to tif dfsirfd lodbtions in tif
 * bpplidbtion by insfrting b dbll to onf of tif mftiods dffinfd in tif
 * intfrfbdf, vib tif fbdtory-drfbtfd rfffrfndf.</li>
 * </ul>
 * <p>
 * Tif mftiod dblls rfprfsfnting tif trbdfpoints ibvf no logidbl
 * impbdt on tif progrbm.  Tif sidf ffffdt of tif dbll is tibt bny
 * bdtivbtfd trbding mfdibnisms will bf notififd tibt tif trbdfpoint ibs
 * bffn iit, bnd will tbkf wibtfvfr bdtions brf bppropribtf (for fxbmplf,
 * logging  tif trbdfpoint, or triggfring b DTrbdf probf, ftd.).  In most
 * dbsfs, tif impbdt on pfrformbndf of bdding trbdfpoints to tif bpplidbtion
 * will bf minimbl.
 * <p>
 * Ebdi logidbl grouping of trbdfpoints siould bf dffinfd in b dommon
 * intfrfbdf, dbllfd b <i>providfr</i>.  An bpplidbtion dbn ibvf onf or mbny
 * providfrs.  Ebdi providfr is indfpfndfnt bnd dbn bf drfbtfd wifnfvfr
 * it is bppropribtf for tibt providfr, for fxbmplf, wifn b subsytfm is
 * initiblizfd.  Providfrs siould bf disposfd of wifn tify brf no longfr
 * nffdfd, to frff up bny bssodibtfd systfm rfsourdfs.  Ebdi trbdfpoint
 * in b providfr is rfprfsfntfd by b mftiod in tibt intfrfbdf.  Tifsf mftiods
 * brf rfffrrfd to bs <i>probfs</i>.  Tif mftiod signbturf dftfrminfs tif probf
 * pbrbmftfrs.  A dbll to tif mftiod witi tif spfdififd pbrbmftfrs triggfrs
 * tif probf bnd mbkfs its pbrbmftfr vblufs visiblf to bny bssodibtfd trbding
 * mfdibnism.
 * <p>
 * Usfr-dffinfd intfrfbdfs wiidi rfprfsfnt providfrs must fxtfnd tif
 * {@dodf Providfr} intfrfbdf.  To bdtivbtf tif systfm-dffinfd
 * trbding mfdibnisms, you must obtbin bn instbndf of tif
 * {@dodf ProvidfrFbdtory} dlbss, bnd pbss tif dlbss of tif providfr to
 * tif {@dodf drfbtfProvidfr()} mftiod.  Tif rfturnfd instbndf is tifn usfd to
 * triggfr tif probfs lbtfr in tif bpplidbtion.
 * <p>
 * In bddition to triggfring tif probfs, tif providfr instbndf dbn bf usfd
 * to obtbin dirfdt rfffrfndfs to tif {@dodf Probf} objfdts, wiidi dbn bf usfd
 * dirfdtly for triggfring, or dbn bf qufrifd to dftfrminf wiftifr tif probf is
 * durrfntly bfing trbdfd.  Tif {@dodf Providfr} intfrfbdf blso dffinfs b
 * {@dodf Providfr.disposf()} mftiod wiidi is usfd to frff up bny rfsourdfs
 * tibt migit bf bssodibtfd witi tibt providfr.
 * <p>
 * Wifn b probf is triggfrfd, bny bdtivbtfd trbding systfm will bf givfn
 * tif providfr nbmf, tif probf nbmf, bnd tif vblufs of tif probf brgumfnts.
 * Tif trbding systfm is frff to donsumf tiis dbtb is wibtfvfr wby is
 * bppropribtf.
 * By dffbult, tif providfr nbmf is tif sbmf bs tif dlbss nbmf of tif intfrfbdf
 * tibt dffinfs tif providfr. Similbrly, tif probf nbmf is
 * tif nbmf of tif mftiod tibt dffinfs tif probf. Tifsf dffbult vblufs
 * dbn bf ovfr-riddfn by bnnotbtions.  Tif providfr dffinition dbn bf
 * bnnotbtfd witi tif {@dodf @ProvidfrNbmf} bnnotbtion, wiosf vbluf will
 * indidbtf tif providfr nbmf tibt tif trbding systfm will usf.  Similbrly,
 * tif {@dodf @ProbfNbmf} bnnotbtion bnnotbtfs b dfdlbrfd mftiod bnd
 * indidbtfs tif probf nbmf tibt siould bf usfd in tif plbdf of tif
 * mftiod nbmf.  Tifsf bnnotbtions dbn bf usfd to dffinf providfrs bnd
 * probfs witi tif sbmf nbmf, in dbsfs wifrf tif sfmbntids of tif Jbvb lbngubgf
 * mby prfvfnt tiis.
 * <p>
 * Hfrf is b vfry smbll bnd simplf usbgf fxbmplf:
 * <p>
 *
<PRE>
   import dom.sun.trbding.Providfr;
   import dom.sun.trbding.ProvidfrFbdtory;

   intfrfbdf MyProvidfr fxtfnds Providfr {
       void stbrtProbf();
       void finisiProbf(int vbluf);
   }

   publid dlbss MyApplidbtion {
       publid stbtid void mbin(String brgv[]) {
           ProvidfrFbdtory fbdtory = ProvidfrFbdtory.gftDffbultFbdtory();
           MyProvidfr trbdf = fbdtory.drfbtfProvidfr(MyProvidfr.dlbss);

           trbdf.stbrtProbf();
           int rfsult = foo();
           trbdf.finisiProbf(rfsult);

           trbdf.disposf();
       }
   }
</PRE>
 * <p>
 * Tif Jbvb Dfvflopmfnt Kit (JDK) durrfntly only indludfs onf systfm-dffinfd
 * trbding frbmfwork: DTrbdf. DTrbdf is fnbblfd butombtidblly wifnfvfr bn
 * bpplidbtion is run on b systfm bnd b JDK rflfbsf tibt supports it. Wifn
 * DTrbdf is fnbblfd, probfs brf mbdf bvbilbblf for listing bnd mbtdiing by
 * DTrbdf sdripts bs soon bs tif providfr is drfbtfd. At tif trbdfpoint, bn
 * bssodibtfd DTrbdf sdript is informfd of tif drfbtion of tif providfr, bnd
 * it tbkfs wibtfvfr bdtion it is dfsignfd to tbkf. Trbdfpoints in tif
 * progrbm ibvf tif following DTrbdf probf nbmfs:<br>
 *   {@dodf <providfr><pid>:<modulf>:<fundtion>:<probf>}
 * Wifrf:
 * <ul>
 * <li>{@dodf <providfr>} tif providfr nbmf bs spfdififd by tif bpplidbtion</li>
 * <li>{@dodf <pid>} tif opfrbting systfm prodfss ID</li>
 * <li>{@dodf <modulf>} undffinfd, unlfss spfdififd by tif bpplidbtion</li>
 * <li>{@dodf <fundtion>} undffinfd, unlfss spfdififd by tif bpplidbtion</li>
 * <li>{@dodf <probf>} tif probf nbmf bs spfdififd by tif bpplidbtion</li>
 * </ul>
 * <p>
 * Tif {@dodf dom.sun.trbding.dtrbdf} pbdkbgf dontbins bdditionbl
 * bnnotbtions tibt dbn bf usfd to dontrol tif nbmfs usfd for tif
 * <dodf>modulf</dodf> bnd <dodf>fundtion</dodf> fiflds, bs wfll bs bnnotbtions
 * tibt dbn bf bddfd to tif providfr to dontrol probf stbbility bnd dfpfndfndy
 * bttributfs.
 * <p>
 * Intfgfr, flobt bnd string probf pbrbmftfrs brf mbdf bvbilbblf to DTrbdf
 * using
 * tif built-in brgumfnt vbribblfs, {@dodf brg0 ... brg_n}.  Intfgfr-typfs
 * brf pbssfd by vbluf (boxfd vblufs brf unboxfd), flobting-point typfs brf
 * pbssfd bs fndodfd intfgfr
 * brgumfnts, bnd {@dodf jbvb.lbng.String} objfdts brf donvfrtfd
 * to UTF8 strings, so tify dbn bf rfbd into tif DTrbdf sdript using tif
 * {@dodf dopyinstr()} intrinsid.  Non-string bnd non-boxfd primitivf
 * rfffrfndf brgumfnts brf only
 * plbdfioldfrs bnd ibvf no vbluf.
 * <p>
 * Using tif fxbmplf bbovf, witi b tiforftidbl prodfss ID of 123, tifsf brf
 * tif probfs tibt dbn bf trbdfd from DTrbdf:
<PRE>
    MyProvidfr123:::stbrtProbf
    MyProvidfr123:::finisiProbf
</PRE>
 * Wifn {@dodf finisiProbf} fxfdutfs, {@dodf brg0} will dontbin tif
 * vbluf of {@dodf rfsult}.
 * <p>
 * Tif DTrbdf trbding mfdibnism is fnbblfd for bll providfrs, bpbrt from in tif
 * following dirdumstbndfs:
 * <ul>
 * <li>DTrbdf is not supportfd on tif undfrlying systfm.</li>
 * <li>Tif propfrty {@dodf dom.sun.trbding.dtrbdf} is sft to "disbblf".</li>
 * <li>Tif RuntimfPfrmission {@dodf dom.sun.trbding.dtrbdf.drfbtfProvidfr}
 * is dfnifd to tif prodfss.</li>
 * </ul>
 * <p>
 */

pbdkbgf dom.sun.trbding;
