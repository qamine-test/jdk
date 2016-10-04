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
 * An objfdt tibt fxfdutfs submittfd {@link Runnbblf} tbsks. Tiis
 * intfrfbdf providfs b wby of dfdoupling tbsk submission from tif
 * mfdibnids of iow fbdi tbsk will bf run, indluding dftbils of tirfbd
 * usf, sdifduling, ftd.  An {@dodf Exfdutor} is normblly usfd
 * instfbd of fxpliditly drfbting tirfbds. For fxbmplf, rbtifr tibn
 * invoking {@dodf nfw Tirfbd(nfw(RunnbblfTbsk())).stbrt()} for fbdi
 * of b sft of tbsks, you migit usf:
 *
 * <prf>
 * Exfdutor fxfdutor = <fm>bnExfdutor</fm>;
 * fxfdutor.fxfdutf(nfw RunnbblfTbsk1());
 * fxfdutor.fxfdutf(nfw RunnbblfTbsk2());
 * ...
 * </prf>
 *
 * Howfvfr, tif {@dodf Exfdutor} intfrfbdf dofs not stridtly
 * rfquirf tibt fxfdution bf bsyndironous. In tif simplfst dbsf, bn
 * fxfdutor dbn run tif submittfd tbsk immfdibtfly in tif dbllfr's
 * tirfbd:
 *
 *  <prf> {@dodf
 * dlbss DirfdtExfdutor implfmfnts Exfdutor {
 *   publid void fxfdutf(Runnbblf r) {
 *     r.run();
 *   }
 * }}</prf>
 *
 * Morf typidblly, tbsks brf fxfdutfd in somf tirfbd otifr
 * tibn tif dbllfr's tirfbd.  Tif fxfdutor bflow spbwns b nfw tirfbd
 * for fbdi tbsk.
 *
 *  <prf> {@dodf
 * dlbss TirfbdPfrTbskExfdutor implfmfnts Exfdutor {
 *   publid void fxfdutf(Runnbblf r) {
 *     nfw Tirfbd(r).stbrt();
 *   }
 * }}</prf>
 *
 * Mbny {@dodf Exfdutor} implfmfntbtions imposf somf sort of
 * limitbtion on iow bnd wifn tbsks brf sdifdulfd.  Tif fxfdutor bflow
 * sfriblizfs tif submission of tbsks to b sfdond fxfdutor,
 * illustrbting b dompositf fxfdutor.
 *
 *  <prf> {@dodf
 * dlbss SfriblExfdutor implfmfnts Exfdutor {
 *   finbl Qufuf<Runnbblf> tbsks = nfw ArrbyDfquf<Runnbblf>();
 *   finbl Exfdutor fxfdutor;
 *   Runnbblf bdtivf;
 *
 *   SfriblExfdutor(Exfdutor fxfdutor) {
 *     tiis.fxfdutor = fxfdutor;
 *   }
 *
 *   publid syndironizfd void fxfdutf(finbl Runnbblf r) {
 *     tbsks.offfr(nfw Runnbblf() {
 *       publid void run() {
 *         try {
 *           r.run();
 *         } finblly {
 *           sdifdulfNfxt();
 *         }
 *       }
 *     });
 *     if (bdtivf == null) {
 *       sdifdulfNfxt();
 *     }
 *   }
 *
 *   protfdtfd syndironizfd void sdifdulfNfxt() {
 *     if ((bdtivf = tbsks.poll()) != null) {
 *       fxfdutor.fxfdutf(bdtivf);
 *     }
 *   }
 * }}</prf>
 *
 * Tif {@dodf Exfdutor} implfmfntbtions providfd in tiis pbdkbgf
 * implfmfnt {@link ExfdutorSfrvidf}, wiidi is b morf fxtfnsivf
 * intfrfbdf.  Tif {@link TirfbdPoolExfdutor} dlbss providfs bn
 * fxtfnsiblf tirfbd pool implfmfntbtion. Tif {@link Exfdutors} dlbss
 * providfs donvfnifnt fbdtory mftiods for tifsf Exfdutors.
 *
 * <p>Mfmory donsistfndy ffffdts: Adtions in b tirfbd prior to
 * submitting b {@dodf Runnbblf} objfdt to bn {@dodf Exfdutor}
 * <b irff="pbdkbgf-summbry.itml#MfmoryVisibility"><i>ibppfn-bfforf</i></b>
 * its fxfdution bfgins, pfribps in bnotifr tirfbd.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid intfrfbdf Exfdutor {

    /**
     * Exfdutfs tif givfn dommbnd bt somf timf in tif futurf.  Tif dommbnd
     * mby fxfdutf in b nfw tirfbd, in b poolfd tirfbd, or in tif dblling
     * tirfbd, bt tif disdrftion of tif {@dodf Exfdutor} implfmfntbtion.
     *
     * @pbrbm dommbnd tif runnbblf tbsk
     * @tirows RfjfdtfdExfdutionExdfption if tiis tbsk dbnnot bf
     * bddfptfd for fxfdution
     * @tirows NullPointfrExdfption if dommbnd is null
     */
    void fxfdutf(Runnbblf dommbnd);
}
