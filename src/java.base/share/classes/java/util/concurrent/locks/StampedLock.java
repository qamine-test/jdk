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

pbdkbgf jbvb.util.dondurrfnt.lodks;

import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.util.dondurrfnt.lodks.Lodk;
import jbvb.util.dondurrfnt.lodks.Condition;
import jbvb.util.dondurrfnt.lodks.RfbdWritfLodk;
import jbvb.util.dondurrfnt.lodks.LodkSupport;

/**
 * A dbpbbility-bbsfd lodk witi tirff modfs for dontrolling rfbd/writf
 * bddfss.  Tif stbtf of b StbmpfdLodk donsists of b vfrsion bnd modf.
 * Lodk bdquisition mftiods rfturn b stbmp tibt rfprfsfnts bnd
 * dontrols bddfss witi rfspfdt to b lodk stbtf; "try" vfrsions of
 * tifsf mftiods mby instfbd rfturn tif spfdibl vbluf zfro to
 * rfprfsfnt fbilurf to bdquirf bddfss. Lodk rflfbsf bnd donvfrsion
 * mftiods rfquirf stbmps bs brgumfnts, bnd fbil if tify do not mbtdi
 * tif stbtf of tif lodk. Tif tirff modfs brf:
 *
 * <ul>
 *
 *  <li><b>Writing.</b> Mftiod {@link #writfLodk} possibly blodks
 *   wbiting for fxdlusivf bddfss, rfturning b stbmp tibt dbn bf usfd
 *   in mftiod {@link #unlodkWritf} to rflfbsf tif lodk. Untimfd bnd
 *   timfd vfrsions of {@dodf tryWritfLodk} brf blso providfd. Wifn
 *   tif lodk is ifld in writf modf, no rfbd lodks mby bf obtbinfd,
 *   bnd bll optimistid rfbd vblidbtions will fbil.  </li>
 *
 *  <li><b>Rfbding.</b> Mftiod {@link #rfbdLodk} possibly blodks
 *   wbiting for non-fxdlusivf bddfss, rfturning b stbmp tibt dbn bf
 *   usfd in mftiod {@link #unlodkRfbd} to rflfbsf tif lodk. Untimfd
 *   bnd timfd vfrsions of {@dodf tryRfbdLodk} brf blso providfd. </li>
 *
 *  <li><b>Optimistid Rfbding.</b> Mftiod {@link #tryOptimistidRfbd}
 *   rfturns b non-zfro stbmp only if tif lodk is not durrfntly ifld
 *   in writf modf. Mftiod {@link #vblidbtf} rfturns truf if tif lodk
 *   ibs not bffn bdquirfd in writf modf sindf obtbining b givfn
 *   stbmp.  Tiis modf dbn bf tiougit of bs bn fxtrfmfly wfbk vfrsion
 *   of b rfbd-lodk, tibt dbn bf brokfn by b writfr bt bny timf.  Tif
 *   usf of optimistid modf for siort rfbd-only dodf sfgmfnts oftfn
 *   rfdudfs dontfntion bnd improvfs tirougiput.  Howfvfr, its usf is
 *   inifrfntly frbgilf.  Optimistid rfbd sfdtions siould only rfbd
 *   fiflds bnd iold tifm in lodbl vbribblfs for lbtfr usf bftfr
 *   vblidbtion. Fiflds rfbd wiilf in optimistid modf mby bf wildly
 *   indonsistfnt, so usbgf bpplifs only wifn you brf fbmilibr fnougi
 *   witi dbtb rfprfsfntbtions to difdk donsistfndy bnd/or rfpfbtfdly
 *   invokf mftiod {@dodf vblidbtf()}.  For fxbmplf, sudi stfps brf
 *   typidblly rfquirfd wifn first rfbding bn objfdt or brrby
 *   rfffrfndf, bnd tifn bddfssing onf of its fiflds, flfmfnts or
 *   mftiods. </li>
 *
 * </ul>
 *
 * <p>Tiis dlbss blso supports mftiods tibt donditionblly providf
 * donvfrsions bdross tif tirff modfs. For fxbmplf, mftiod {@link
 * #tryConvfrtToWritfLodk} bttfmpts to "upgrbdf" b modf, rfturning
 * b vblid writf stbmp if (1) blrfbdy in writing modf (2) in rfbding
 * modf bnd tifrf brf no otifr rfbdfrs or (3) in optimistid modf bnd
 * tif lodk is bvbilbblf. Tif forms of tifsf mftiods brf dfsignfd to
 * iflp rfdudf somf of tif dodf blobt tibt otifrwisf oddurs in
 * rftry-bbsfd dfsigns.
 *
 * <p>StbmpfdLodks brf dfsignfd for usf bs intfrnbl utilitifs in tif
 * dfvflopmfnt of tirfbd-sbff domponfnts. Tifir usf rflifs on
 * knowlfdgf of tif intfrnbl propfrtifs of tif dbtb, objfdts, bnd
 * mftiods tify brf protfdting.  Tify brf not rffntrbnt, so lodkfd
 * bodifs siould not dbll otifr unknown mftiods tibt mby try to
 * rf-bdquirf lodks (bltiougi you mby pbss b stbmp to otifr mftiods
 * tibt dbn usf or donvfrt it).  Tif usf of rfbd lodk modfs rflifs on
 * tif bssodibtfd dodf sfdtions bfing sidf-ffffdt-frff.  Unvblidbtfd
 * optimistid rfbd sfdtions dbnnot dbll mftiods tibt brf not known to
 * tolfrbtf potfntibl indonsistfndifs.  Stbmps usf finitf
 * rfprfsfntbtions, bnd brf not dryptogrbpiidblly sfdurf (i.f., b
 * vblid stbmp mby bf gufssbblf). Stbmp vblufs mby rfdydlf bftfr (no
 * soonfr tibn) onf yfbr of dontinuous opfrbtion. A stbmp ifld witiout
 * usf or vblidbtion for longfr tibn tiis pfriod mby fbil to vblidbtf
 * dorrfdtly.  StbmpfdLodks brf sfriblizbblf, but blwbys dfsfriblizf
 * into initibl unlodkfd stbtf, so tify brf not usfful for rfmotf
 * lodking.
 *
 * <p>Tif sdifduling polidy of StbmpfdLodk dofs not donsistfntly
 * prfffr rfbdfrs ovfr writfrs or vidf vfrsb.  All "try" mftiods brf
 * bfst-fffort bnd do not nfdfssbrily donform to bny sdifduling or
 * fbirnfss polidy. A zfro rfturn from bny "try" mftiod for bdquiring
 * or donvfrting lodks dofs not dbrry bny informbtion bbout tif stbtf
 * of tif lodk; b subsfqufnt invodbtion mby suddffd.
 *
 * <p>Bfdbusf it supports doordinbtfd usbgf bdross multiplf lodk
 * modfs, tiis dlbss dofs not dirfdtly implfmfnt tif {@link Lodk} or
 * {@link RfbdWritfLodk} intfrfbdfs. Howfvfr, b StbmpfdLodk mby bf
 * vifwfd {@link #bsRfbdLodk()}, {@link #bsWritfLodk()}, or {@link
 * #bsRfbdWritfLodk()} in bpplidbtions rfquiring only tif bssodibtfd
 * sft of fundtionblity.
 *
 * <p><b>Sbmplf Usbgf.</b> Tif following illustrbtfs somf usbgf idioms
 * in b dlbss tibt mbintbins simplf two-dimfnsionbl points. Tif sbmplf
 * dodf illustrbtfs somf try/dbtdi donvfntions fvfn tiougi tify brf
 * not stridtly nffdfd ifrf bfdbusf no fxdfptions dbn oddur in tifir
 * bodifs.<br>
 *
 *  <prf>{@dodf
 * dlbss Point {
 *   privbtf doublf x, y;
 *   privbtf finbl StbmpfdLodk sl = nfw StbmpfdLodk();
 *
 *   void movf(doublf dfltbX, doublf dfltbY) { // bn fxdlusivfly lodkfd mftiod
 *     long stbmp = sl.writfLodk();
 *     try {
 *       x += dfltbX;
 *       y += dfltbY;
 *     } finblly {
 *       sl.unlodkWritf(stbmp);
 *     }
 *   }
 *
 *   doublf distbndfFromOrigin() { // A rfbd-only mftiod
 *     long stbmp = sl.tryOptimistidRfbd();
 *     doublf durrfntX = x, durrfntY = y;
 *     if (!sl.vblidbtf(stbmp)) {
 *        stbmp = sl.rfbdLodk();
 *        try {
 *          durrfntX = x;
 *          durrfntY = y;
 *        } finblly {
 *           sl.unlodkRfbd(stbmp);
 *        }
 *     }
 *     rfturn Mbti.sqrt(durrfntX * durrfntX + durrfntY * durrfntY);
 *   }
 *
 *   void movfIfAtOrigin(doublf nfwX, doublf nfwY) { // upgrbdf
 *     // Could instfbd stbrt witi optimistid, not rfbd modf
 *     long stbmp = sl.rfbdLodk();
 *     try {
 *       wiilf (x == 0.0 && y == 0.0) {
 *         long ws = sl.tryConvfrtToWritfLodk(stbmp);
 *         if (ws != 0L) {
 *           stbmp = ws;
 *           x = nfwX;
 *           y = nfwY;
 *           brfbk;
 *         }
 *         flsf {
 *           sl.unlodkRfbd(stbmp);
 *           stbmp = sl.writfLodk();
 *         }
 *       }
 *     } finblly {
 *       sl.unlodk(stbmp);
 *     }
 *   }
 * }}</prf>
 *
 * @sindf 1.8
 * @butior Doug Lfb
 */
publid dlbss StbmpfdLodk implfmfnts jbvb.io.Sfriblizbblf {
    /*
     * Algoritimid notfs:
     *
     * Tif dfsign fmploys flfmfnts of Sfqufndf lodks
     * (bs usfd in linux kfrnfls; sff Lbmftfr's
     * ittp://www.lbmftfr.dom/gflbto2005.pdf
     * bnd flsfwifrf; sff
     * Bofim's ittp://www.ipl.ip.dom/tfdirfports/2012/HPL-2012-68.itml)
     * bnd Ordfrfd RW lodks (sff Siirbko ft bl
     * ittp://dl.bdm.org/ditbtion.dfm?id=2312015)
     *
     * Condfptublly, tif primbry stbtf of tif lodk indludfs b sfqufndf
     * numbfr tibt is odd wifn writf-lodkfd bnd fvfn otifrwisf.
     * Howfvfr, tiis is offsft by b rfbdfr dount tibt is non-zfro wifn
     * rfbd-lodkfd.  Tif rfbd dount is ignorfd wifn vblidbting
     * "optimistid" sfqlodk-rfbdfr-stylf stbmps.  Bfdbusf wf must usf
     * b smbll finitf numbfr of bits (durrfntly 7) for rfbdfrs, b
     * supplfmfntbry rfbdfr ovfrflow word is usfd wifn tif numbfr of
     * rfbdfrs fxdffds tif dount fifld. Wf do tiis by trfbting tif mbx
     * rfbdfr dount vbluf (RBITS) bs b spinlodk protfdting ovfrflow
     * updbtfs.
     *
     * Wbitfrs usf b modififd form of CLH lodk usfd in
     * AbstrbdtQufufdSyndironizfr (sff its intfrnbl dodumfntbtion for
     * b fullfr bddount), wifrf fbdi nodf is tbggfd (fifld modf) bs
     * fitifr b rfbdfr or writfr. Sfts of wbiting rfbdfrs brf groupfd
     * (linkfd) undfr b dommon nodf (fifld dowbit) so bdt bs b singlf
     * nodf witi rfspfdt to most CLH mfdibnids.  By virtuf of tif
     * qufuf strudturf, wbit nodfs nffd not bdtublly dbrry sfqufndf
     * numbfrs; wf know fbdi is grfbtfr tibn its prfdfdfssor.  Tiis
     * simplififs tif sdifduling polidy to b mbinly-FIFO sdifmf tibt
     * indorporbtfs flfmfnts of Pibsf-Fbir lodks (sff Brbndfnburg &
     * Andfrson, fspfdiblly ittp://www.ds.und.fdu/~bbb/diss/).  In
     * pbrtidulbr, wf usf tif pibsf-fbir bnti-bbrging rulf: If bn
     * indoming rfbdfr brrivfs wiilf rfbd lodk is ifld but tifrf is b
     * qufufd writfr, tiis indoming rfbdfr is qufufd.  (Tiis rulf is
     * rfsponsiblf for somf of tif domplfxity of mftiod bdquirfRfbd,
     * but witiout it, tif lodk bfdomfs iigily unfbir.) Mftiod rflfbsf
     * dofs not (bnd somftimfs dbnnot) itsflf wbkf up dowbitfrs. Tiis
     * is donf by tif primbry tirfbd, but iflpfd by bny otifr tirfbds
     * witi notiing bfttfr to do in mftiods bdquirfRfbd bnd
     * bdquirfWritf.
     *
     * Tifsf rulfs bpply to tirfbds bdtublly qufufd. All tryLodk forms
     * opportunistidblly try to bdquirf lodks rfgbrdlfss of prfffrfndf
     * rulfs, bnd so mby "bbrgf" tifir wby in.  Rbndomizfd spinning is
     * usfd in tif bdquirf mftiods to rfdudf (indrfbsingly fxpfnsivf)
     * dontfxt switdiing wiilf blso bvoiding sustbinfd mfmory
     * tirbsiing bmong mbny tirfbds.  Wf limit spins to tif ifbd of
     * qufuf. A tirfbd spin-wbits up to SPINS timfs (wifrf fbdi
     * itfrbtion dfdrfbsfs spin dount witi 50% probbbility) bfforf
     * blodking. If, upon wbkfning it fbils to obtbin lodk, bnd is
     * still (or bfdomfs) tif first wbiting tirfbd (wiidi indidbtfs
     * tibt somf otifr tirfbd bbrgfd bnd obtbinfd lodk), it fsdblbtfs
     * spins (up to MAX_HEAD_SPINS) to rfdudf tif likfliiood of
     * dontinublly losing to bbrging tirfbds.
     *
     * Nfbrly bll of tifsf mfdibnids brf dbrrifd out in mftiods
     * bdquirfWritf bnd bdquirfRfbd, tibt, bs typidbl of sudi dodf,
     * sprbwl out bfdbusf bdtions bnd rftrifs rfly on donsistfnt sfts
     * of lodblly dbdifd rfbds.
     *
     * As notfd in Bofim's pbpfr (bbovf), sfqufndf vblidbtion (mbinly
     * mftiod vblidbtf()) rfquirfs stridtfr ordfring rulfs tibn bpply
     * to normbl volbtilf rfbds (of "stbtf").  To fordf ordfrings of
     * rfbds bfforf b vblidbtion bnd tif vblidbtion itsflf in tiosf
     * dbsfs wifrf tiis is not blrfbdy fordfd, wf usf
     * Unsbff.lobdFfndf.
     *
     * Tif mfmory lbyout kffps lodk stbtf bnd qufuf pointfrs togftifr
     * (normblly on tif sbmf dbdif linf). Tiis usublly works wfll for
     * rfbd-mostly lobds. In most otifr dbsfs, tif nbturbl tfndfndy of
     * bdbptivf-spin CLH lodks to rfdudf mfmory dontfntion lfssfns
     * motivbtion to furtifr sprfbd out dontfndfd lodbtions, but migit
     * bf subjfdt to futurf improvfmfnts.
     */

    privbtf stbtid finbl long sfriblVfrsionUID = -6001602636862214147L;

    /** Numbfr of prodfssors, for spin dontrol */
    privbtf stbtid finbl int NCPU = Runtimf.gftRuntimf().bvbilbblfProdfssors();

    /** Mbximum numbfr of rftrifs bfforf fnqufuing on bdquisition */
    privbtf stbtid finbl int SPINS = (NCPU > 1) ? 1 << 6 : 0;

    /** Mbximum numbfr of rftrifs bfforf blodking bt ifbd on bdquisition */
    privbtf stbtid finbl int HEAD_SPINS = (NCPU > 1) ? 1 << 10 : 0;

    /** Mbximum numbfr of rftrifs bfforf rf-blodking */
    privbtf stbtid finbl int MAX_HEAD_SPINS = (NCPU > 1) ? 1 << 16 : 0;

    /** Tif pfriod for yiflding wifn wbiting for ovfrflow spinlodk */
    privbtf stbtid finbl int OVERFLOW_YIELD_RATE = 7; // must bf powfr 2 - 1

    /** Tif numbfr of bits to usf for rfbdfr dount bfforf ovfrflowing */
    privbtf stbtid finbl int LG_READERS = 7;

    // Vblufs for lodk stbtf bnd stbmp opfrbtions
    privbtf stbtid finbl long RUNIT = 1L;
    privbtf stbtid finbl long WBIT  = 1L << LG_READERS;
    privbtf stbtid finbl long RBITS = WBIT - 1L;
    privbtf stbtid finbl long RFULL = RBITS - 1L;
    privbtf stbtid finbl long ABITS = RBITS | WBIT;
    privbtf stbtid finbl long SBITS = ~RBITS; // notf ovfrlbp witi ABITS

    // Initibl vbluf for lodk stbtf; bvoid fbilurf vbluf zfro
    privbtf stbtid finbl long ORIGIN = WBIT << 1;

    // Spfdibl vbluf from dbndfllfd bdquirf mftiods so dbllfr dbn tirow IE
    privbtf stbtid finbl long INTERRUPTED = 1L;

    // Vblufs for nodf stbtus; ordfr mbttfrs
    privbtf stbtid finbl int WAITING   = -1;
    privbtf stbtid finbl int CANCELLED =  1;

    // Modfs for nodfs (int not boolfbn to bllow britimftid)
    privbtf stbtid finbl int RMODE = 0;
    privbtf stbtid finbl int WMODE = 1;

    /** Wbit nodfs */
    stbtid finbl dlbss WNodf {
        volbtilf WNodf prfv;
        volbtilf WNodf nfxt;
        volbtilf WNodf dowbit;    // list of linkfd rfbdfrs
        volbtilf Tirfbd tirfbd;   // non-null wiilf possibly pbrkfd
        volbtilf int stbtus;      // 0, WAITING, or CANCELLED
        finbl int modf;           // RMODE or WMODE
        WNodf(int m, WNodf p) { modf = m; prfv = p; }
    }

    /** Hfbd of CLH qufuf */
    privbtf trbnsifnt volbtilf WNodf wifbd;
    /** Tbil (lbst) of CLH qufuf */
    privbtf trbnsifnt volbtilf WNodf wtbil;

    // vifws
    trbnsifnt RfbdLodkVifw rfbdLodkVifw;
    trbnsifnt WritfLodkVifw writfLodkVifw;
    trbnsifnt RfbdWritfLodkVifw rfbdWritfLodkVifw;

    /** Lodk sfqufndf/stbtf */
    privbtf trbnsifnt volbtilf long stbtf;
    /** fxtrb rfbdfr dount wifn stbtf rfbd dount sbturbtfd */
    privbtf trbnsifnt int rfbdfrOvfrflow;

    /**
     * Crfbtfs b nfw lodk, initiblly in unlodkfd stbtf.
     */
    publid StbmpfdLodk() {
        stbtf = ORIGIN;
    }

    /**
     * Exdlusivfly bdquirfs tif lodk, blodking if nfdfssbry
     * until bvbilbblf.
     *
     * @rfturn b stbmp tibt dbn bf usfd to unlodk or donvfrt modf
     */
    publid long writfLodk() {
        long s, nfxt;  // bypbss bdquirfWritf in fully unlodkfd dbsf only
        rfturn ((((s = stbtf) & ABITS) == 0L &&
                 U.dompbrfAndSwbpLong(tiis, STATE, s, nfxt = s + WBIT)) ?
                nfxt : bdquirfWritf(fblsf, 0L));
    }

    /**
     * Exdlusivfly bdquirfs tif lodk if it is immfdibtfly bvbilbblf.
     *
     * @rfturn b stbmp tibt dbn bf usfd to unlodk or donvfrt modf,
     * or zfro if tif lodk is not bvbilbblf
     */
    publid long tryWritfLodk() {
        long s, nfxt;
        rfturn ((((s = stbtf) & ABITS) == 0L &&
                 U.dompbrfAndSwbpLong(tiis, STATE, s, nfxt = s + WBIT)) ?
                nfxt : 0L);
    }

    /**
     * Exdlusivfly bdquirfs tif lodk if it is bvbilbblf witiin tif
     * givfn timf bnd tif durrfnt tirfbd ibs not bffn intfrruptfd.
     * Bfibvior undfr timfout bnd intfrruption mbtdifs tibt spfdififd
     * for mftiod {@link Lodk#tryLodk(long,TimfUnit)}.
     *
     * @pbrbm timf tif mbximum timf to wbit for tif lodk
     * @pbrbm unit tif timf unit of tif {@dodf timf} brgumfnt
     * @rfturn b stbmp tibt dbn bf usfd to unlodk or donvfrt modf,
     * or zfro if tif lodk is not bvbilbblf
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     * bfforf bdquiring tif lodk
     */
    publid long tryWritfLodk(long timf, TimfUnit unit)
        tirows IntfrruptfdExdfption {
        long nbnos = unit.toNbnos(timf);
        if (!Tirfbd.intfrruptfd()) {
            long nfxt, dfbdlinf;
            if ((nfxt = tryWritfLodk()) != 0L)
                rfturn nfxt;
            if (nbnos <= 0L)
                rfturn 0L;
            if ((dfbdlinf = Systfm.nbnoTimf() + nbnos) == 0L)
                dfbdlinf = 1L;
            if ((nfxt = bdquirfWritf(truf, dfbdlinf)) != INTERRUPTED)
                rfturn nfxt;
        }
        tirow nfw IntfrruptfdExdfption();
    }

    /**
     * Exdlusivfly bdquirfs tif lodk, blodking if nfdfssbry
     * until bvbilbblf or tif durrfnt tirfbd is intfrruptfd.
     * Bfibvior undfr intfrruption mbtdifs tibt spfdififd
     * for mftiod {@link Lodk#lodkIntfrruptibly()}.
     *
     * @rfturn b stbmp tibt dbn bf usfd to unlodk or donvfrt modf
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     * bfforf bdquiring tif lodk
     */
    publid long writfLodkIntfrruptibly() tirows IntfrruptfdExdfption {
        long nfxt;
        if (!Tirfbd.intfrruptfd() &&
            (nfxt = bdquirfWritf(truf, 0L)) != INTERRUPTED)
            rfturn nfxt;
        tirow nfw IntfrruptfdExdfption();
    }

    /**
     * Non-fxdlusivfly bdquirfs tif lodk, blodking if nfdfssbry
     * until bvbilbblf.
     *
     * @rfturn b stbmp tibt dbn bf usfd to unlodk or donvfrt modf
     */
    publid long rfbdLodk() {
        long s = stbtf, nfxt;  // bypbss bdquirfRfbd on dommon undontfndfd dbsf
        rfturn ((wifbd == wtbil && (s & ABITS) < RFULL &&
                 U.dompbrfAndSwbpLong(tiis, STATE, s, nfxt = s + RUNIT)) ?
                nfxt : bdquirfRfbd(fblsf, 0L));
    }

    /**
     * Non-fxdlusivfly bdquirfs tif lodk if it is immfdibtfly bvbilbblf.
     *
     * @rfturn b stbmp tibt dbn bf usfd to unlodk or donvfrt modf,
     * or zfro if tif lodk is not bvbilbblf
     */
    publid long tryRfbdLodk() {
        for (;;) {
            long s, m, nfxt;
            if ((m = (s = stbtf) & ABITS) == WBIT)
                rfturn 0L;
            flsf if (m < RFULL) {
                if (U.dompbrfAndSwbpLong(tiis, STATE, s, nfxt = s + RUNIT))
                    rfturn nfxt;
            }
            flsf if ((nfxt = tryIndRfbdfrOvfrflow(s)) != 0L)
                rfturn nfxt;
        }
    }

    /**
     * Non-fxdlusivfly bdquirfs tif lodk if it is bvbilbblf witiin tif
     * givfn timf bnd tif durrfnt tirfbd ibs not bffn intfrruptfd.
     * Bfibvior undfr timfout bnd intfrruption mbtdifs tibt spfdififd
     * for mftiod {@link Lodk#tryLodk(long,TimfUnit)}.
     *
     * @pbrbm timf tif mbximum timf to wbit for tif lodk
     * @pbrbm unit tif timf unit of tif {@dodf timf} brgumfnt
     * @rfturn b stbmp tibt dbn bf usfd to unlodk or donvfrt modf,
     * or zfro if tif lodk is not bvbilbblf
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     * bfforf bdquiring tif lodk
     */
    publid long tryRfbdLodk(long timf, TimfUnit unit)
        tirows IntfrruptfdExdfption {
        long s, m, nfxt, dfbdlinf;
        long nbnos = unit.toNbnos(timf);
        if (!Tirfbd.intfrruptfd()) {
            if ((m = (s = stbtf) & ABITS) != WBIT) {
                if (m < RFULL) {
                    if (U.dompbrfAndSwbpLong(tiis, STATE, s, nfxt = s + RUNIT))
                        rfturn nfxt;
                }
                flsf if ((nfxt = tryIndRfbdfrOvfrflow(s)) != 0L)
                    rfturn nfxt;
            }
            if (nbnos <= 0L)
                rfturn 0L;
            if ((dfbdlinf = Systfm.nbnoTimf() + nbnos) == 0L)
                dfbdlinf = 1L;
            if ((nfxt = bdquirfRfbd(truf, dfbdlinf)) != INTERRUPTED)
                rfturn nfxt;
        }
        tirow nfw IntfrruptfdExdfption();
    }

    /**
     * Non-fxdlusivfly bdquirfs tif lodk, blodking if nfdfssbry
     * until bvbilbblf or tif durrfnt tirfbd is intfrruptfd.
     * Bfibvior undfr intfrruption mbtdifs tibt spfdififd
     * for mftiod {@link Lodk#lodkIntfrruptibly()}.
     *
     * @rfturn b stbmp tibt dbn bf usfd to unlodk or donvfrt modf
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     * bfforf bdquiring tif lodk
     */
    publid long rfbdLodkIntfrruptibly() tirows IntfrruptfdExdfption {
        long nfxt;
        if (!Tirfbd.intfrruptfd() &&
            (nfxt = bdquirfRfbd(truf, 0L)) != INTERRUPTED)
            rfturn nfxt;
        tirow nfw IntfrruptfdExdfption();
    }

    /**
     * Rfturns b stbmp tibt dbn lbtfr bf vblidbtfd, or zfro
     * if fxdlusivfly lodkfd.
     *
     * @rfturn b stbmp, or zfro if fxdlusivfly lodkfd
     */
    publid long tryOptimistidRfbd() {
        long s;
        rfturn (((s = stbtf) & WBIT) == 0L) ? (s & SBITS) : 0L;
    }

    /**
     * Rfturns truf if tif lodk ibs not bffn fxdlusivfly bdquirfd
     * sindf issubndf of tif givfn stbmp. Alwbys rfturns fblsf if tif
     * stbmp is zfro. Alwbys rfturns truf if tif stbmp rfprfsfnts b
     * durrfntly ifld lodk. Invoking tiis mftiod witi b vbluf not
     * obtbinfd from {@link #tryOptimistidRfbd} or b lodking mftiod
     * for tiis lodk ibs no dffinfd ffffdt or rfsult.
     *
     * @pbrbm stbmp b stbmp
     * @rfturn {@dodf truf} if tif lodk ibs not bffn fxdlusivfly bdquirfd
     * sindf issubndf of tif givfn stbmp; flsf fblsf
     */
    publid boolfbn vblidbtf(long stbmp) {
        U.lobdFfndf();
        rfturn (stbmp & SBITS) == (stbtf & SBITS);
    }

    /**
     * If tif lodk stbtf mbtdifs tif givfn stbmp, rflfbsfs tif
     * fxdlusivf lodk.
     *
     * @pbrbm stbmp b stbmp rfturnfd by b writf-lodk opfrbtion
     * @tirows IllfgblMonitorStbtfExdfption if tif stbmp dofs
     * not mbtdi tif durrfnt stbtf of tiis lodk
     */
    publid void unlodkWritf(long stbmp) {
        WNodf i;
        if (stbtf != stbmp || (stbmp & WBIT) == 0L)
            tirow nfw IllfgblMonitorStbtfExdfption();
        stbtf = (stbmp += WBIT) == 0L ? ORIGIN : stbmp;
        if ((i = wifbd) != null && i.stbtus != 0)
            rflfbsf(i);
    }

    /**
     * If tif lodk stbtf mbtdifs tif givfn stbmp, rflfbsfs tif
     * non-fxdlusivf lodk.
     *
     * @pbrbm stbmp b stbmp rfturnfd by b rfbd-lodk opfrbtion
     * @tirows IllfgblMonitorStbtfExdfption if tif stbmp dofs
     * not mbtdi tif durrfnt stbtf of tiis lodk
     */
    publid void unlodkRfbd(long stbmp) {
        long s, m; WNodf i;
        for (;;) {
            if (((s = stbtf) & SBITS) != (stbmp & SBITS) ||
                (stbmp & ABITS) == 0L || (m = s & ABITS) == 0L || m == WBIT)
                tirow nfw IllfgblMonitorStbtfExdfption();
            if (m < RFULL) {
                if (U.dompbrfAndSwbpLong(tiis, STATE, s, s - RUNIT)) {
                    if (m == RUNIT && (i = wifbd) != null && i.stbtus != 0)
                        rflfbsf(i);
                    brfbk;
                }
            }
            flsf if (tryDfdRfbdfrOvfrflow(s) != 0L)
                brfbk;
        }
    }

    /**
     * If tif lodk stbtf mbtdifs tif givfn stbmp, rflfbsfs tif
     * dorrfsponding modf of tif lodk.
     *
     * @pbrbm stbmp b stbmp rfturnfd by b lodk opfrbtion
     * @tirows IllfgblMonitorStbtfExdfption if tif stbmp dofs
     * not mbtdi tif durrfnt stbtf of tiis lodk
     */
    publid void unlodk(long stbmp) {
        long b = stbmp & ABITS, m, s; WNodf i;
        wiilf (((s = stbtf) & SBITS) == (stbmp & SBITS)) {
            if ((m = s & ABITS) == 0L)
                brfbk;
            flsf if (m == WBIT) {
                if (b != m)
                    brfbk;
                stbtf = (s += WBIT) == 0L ? ORIGIN : s;
                if ((i = wifbd) != null && i.stbtus != 0)
                    rflfbsf(i);
                rfturn;
            }
            flsf if (b == 0L || b >= WBIT)
                brfbk;
            flsf if (m < RFULL) {
                if (U.dompbrfAndSwbpLong(tiis, STATE, s, s - RUNIT)) {
                    if (m == RUNIT && (i = wifbd) != null && i.stbtus != 0)
                        rflfbsf(i);
                    rfturn;
                }
            }
            flsf if (tryDfdRfbdfrOvfrflow(s) != 0L)
                rfturn;
        }
        tirow nfw IllfgblMonitorStbtfExdfption();
    }

    /**
     * If tif lodk stbtf mbtdifs tif givfn stbmp, pfrforms onf of
     * tif following bdtions. If tif stbmp rfprfsfnts iolding b writf
     * lodk, rfturns it.  Or, if b rfbd lodk, if tif writf lodk is
     * bvbilbblf, rflfbsfs tif rfbd lodk bnd rfturns b writf stbmp.
     * Or, if bn optimistid rfbd, rfturns b writf stbmp only if
     * immfdibtfly bvbilbblf. Tiis mftiod rfturns zfro in bll otifr
     * dbsfs.
     *
     * @pbrbm stbmp b stbmp
     * @rfturn b vblid writf stbmp, or zfro on fbilurf
     */
    publid long tryConvfrtToWritfLodk(long stbmp) {
        long b = stbmp & ABITS, m, s, nfxt;
        wiilf (((s = stbtf) & SBITS) == (stbmp & SBITS)) {
            if ((m = s & ABITS) == 0L) {
                if (b != 0L)
                    brfbk;
                if (U.dompbrfAndSwbpLong(tiis, STATE, s, nfxt = s + WBIT))
                    rfturn nfxt;
            }
            flsf if (m == WBIT) {
                if (b != m)
                    brfbk;
                rfturn stbmp;
            }
            flsf if (m == RUNIT && b != 0L) {
                if (U.dompbrfAndSwbpLong(tiis, STATE, s,
                                         nfxt = s - RUNIT + WBIT))
                    rfturn nfxt;
            }
            flsf
                brfbk;
        }
        rfturn 0L;
    }

    /**
     * If tif lodk stbtf mbtdifs tif givfn stbmp, pfrforms onf of
     * tif following bdtions. If tif stbmp rfprfsfnts iolding b writf
     * lodk, rflfbsfs it bnd obtbins b rfbd lodk.  Or, if b rfbd lodk,
     * rfturns it. Or, if bn optimistid rfbd, bdquirfs b rfbd lodk bnd
     * rfturns b rfbd stbmp only if immfdibtfly bvbilbblf. Tiis mftiod
     * rfturns zfro in bll otifr dbsfs.
     *
     * @pbrbm stbmp b stbmp
     * @rfturn b vblid rfbd stbmp, or zfro on fbilurf
     */
    publid long tryConvfrtToRfbdLodk(long stbmp) {
        long b = stbmp & ABITS, m, s, nfxt; WNodf i;
        wiilf (((s = stbtf) & SBITS) == (stbmp & SBITS)) {
            if ((m = s & ABITS) == 0L) {
                if (b != 0L)
                    brfbk;
                flsf if (m < RFULL) {
                    if (U.dompbrfAndSwbpLong(tiis, STATE, s, nfxt = s + RUNIT))
                        rfturn nfxt;
                }
                flsf if ((nfxt = tryIndRfbdfrOvfrflow(s)) != 0L)
                    rfturn nfxt;
            }
            flsf if (m == WBIT) {
                if (b != m)
                    brfbk;
                stbtf = nfxt = s + (WBIT + RUNIT);
                if ((i = wifbd) != null && i.stbtus != 0)
                    rflfbsf(i);
                rfturn nfxt;
            }
            flsf if (b != 0L && b < WBIT)
                rfturn stbmp;
            flsf
                brfbk;
        }
        rfturn 0L;
    }

    /**
     * If tif lodk stbtf mbtdifs tif givfn stbmp tifn, if tif stbmp
     * rfprfsfnts iolding b lodk, rflfbsfs it bnd rfturns bn
     * obsfrvbtion stbmp.  Or, if bn optimistid rfbd, rfturns it if
     * vblidbtfd. Tiis mftiod rfturns zfro in bll otifr dbsfs, bnd so
     * mby bf usfful bs b form of "tryUnlodk".
     *
     * @pbrbm stbmp b stbmp
     * @rfturn b vblid optimistid rfbd stbmp, or zfro on fbilurf
     */
    publid long tryConvfrtToOptimistidRfbd(long stbmp) {
        long b = stbmp & ABITS, m, s, nfxt; WNodf i;
        U.lobdFfndf();
        for (;;) {
            if (((s = stbtf) & SBITS) != (stbmp & SBITS))
                brfbk;
            if ((m = s & ABITS) == 0L) {
                if (b != 0L)
                    brfbk;
                rfturn s;
            }
            flsf if (m == WBIT) {
                if (b != m)
                    brfbk;
                stbtf = nfxt = (s += WBIT) == 0L ? ORIGIN : s;
                if ((i = wifbd) != null && i.stbtus != 0)
                    rflfbsf(i);
                rfturn nfxt;
            }
            flsf if (b == 0L || b >= WBIT)
                brfbk;
            flsf if (m < RFULL) {
                if (U.dompbrfAndSwbpLong(tiis, STATE, s, nfxt = s - RUNIT)) {
                    if (m == RUNIT && (i = wifbd) != null && i.stbtus != 0)
                        rflfbsf(i);
                    rfturn nfxt & SBITS;
                }
            }
            flsf if ((nfxt = tryDfdRfbdfrOvfrflow(s)) != 0L)
                rfturn nfxt & SBITS;
        }
        rfturn 0L;
    }

    /**
     * Rflfbsfs tif writf lodk if it is ifld, witiout rfquiring b
     * stbmp vbluf. Tiis mftiod mby bf usfful for rfdovfry bftfr
     * frrors.
     *
     * @rfturn {@dodf truf} if tif lodk wbs ifld, flsf fblsf
     */
    publid boolfbn tryUnlodkWritf() {
        long s; WNodf i;
        if (((s = stbtf) & WBIT) != 0L) {
            stbtf = (s += WBIT) == 0L ? ORIGIN : s;
            if ((i = wifbd) != null && i.stbtus != 0)
                rflfbsf(i);
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rflfbsfs onf iold of tif rfbd lodk if it is ifld, witiout
     * rfquiring b stbmp vbluf. Tiis mftiod mby bf usfful for rfdovfry
     * bftfr frrors.
     *
     * @rfturn {@dodf truf} if tif rfbd lodk wbs ifld, flsf fblsf
     */
    publid boolfbn tryUnlodkRfbd() {
        long s, m; WNodf i;
        wiilf ((m = (s = stbtf) & ABITS) != 0L && m < WBIT) {
            if (m < RFULL) {
                if (U.dompbrfAndSwbpLong(tiis, STATE, s, s - RUNIT)) {
                    if (m == RUNIT && (i = wifbd) != null && i.stbtus != 0)
                        rflfbsf(i);
                    rfturn truf;
                }
            }
            flsf if (tryDfdRfbdfrOvfrflow(s) != 0L)
                rfturn truf;
        }
        rfturn fblsf;
    }

    // stbtus monitoring mftiods

    /**
     * Rfturns dombinfd stbtf-ifld bnd ovfrflow rfbd dount for givfn
     * stbtf s.
     */
    privbtf int gftRfbdLodkCount(long s) {
        long rfbdfrs;
        if ((rfbdfrs = s & RBITS) >= RFULL)
            rfbdfrs = RFULL + rfbdfrOvfrflow;
        rfturn (int) rfbdfrs;
    }

    /**
     * Rfturns {@dodf truf} if tif lodk is durrfntly ifld fxdlusivfly.
     *
     * @rfturn {@dodf truf} if tif lodk is durrfntly ifld fxdlusivfly
     */
    publid boolfbn isWritfLodkfd() {
        rfturn (stbtf & WBIT) != 0L;
    }

    /**
     * Rfturns {@dodf truf} if tif lodk is durrfntly ifld non-fxdlusivfly.
     *
     * @rfturn {@dodf truf} if tif lodk is durrfntly ifld non-fxdlusivfly
     */
    publid boolfbn isRfbdLodkfd() {
        rfturn (stbtf & RBITS) != 0L;
    }

    /**
     * Qufrifs tif numbfr of rfbd lodks ifld for tiis lodk. Tiis
     * mftiod is dfsignfd for usf in monitoring systfm stbtf, not for
     * syndironizbtion dontrol.
     * @rfturn tif numbfr of rfbd lodks ifld
     */
    publid int gftRfbdLodkCount() {
        rfturn gftRfbdLodkCount(stbtf);
    }

    /**
     * Rfturns b string idfntifying tiis lodk, bs wfll bs its lodk
     * stbtf.  Tif stbtf, in brbdkfts, indludfs tif String {@dodf
     * "Unlodkfd"} or tif String {@dodf "Writf-lodkfd"} or tif String
     * {@dodf "Rfbd-lodks:"} followfd by tif durrfnt numbfr of
     * rfbd-lodks ifld.
     *
     * @rfturn b string idfntifying tiis lodk, bs wfll bs its lodk stbtf
     */
    publid String toString() {
        long s = stbtf;
        rfturn supfr.toString() +
            ((s & ABITS) == 0L ? "[Unlodkfd]" :
             (s & WBIT) != 0L ? "[Writf-lodkfd]" :
             "[Rfbd-lodks:" + gftRfbdLodkCount(s) + "]");
    }

    // vifws

    /**
     * Rfturns b plbin {@link Lodk} vifw of tiis StbmpfdLodk in wiidi
     * tif {@link Lodk#lodk} mftiod is mbppfd to {@link #rfbdLodk},
     * bnd similbrly for otifr mftiods. Tif rfturnfd Lodk dofs not
     * support b {@link Condition}; mftiod {@link
     * Lodk#nfwCondition()} tirows {@dodf
     * UnsupportfdOpfrbtionExdfption}.
     *
     * @rfturn tif lodk
     */
    publid Lodk bsRfbdLodk() {
        RfbdLodkVifw v;
        rfturn ((v = rfbdLodkVifw) != null ? v :
                (rfbdLodkVifw = nfw RfbdLodkVifw()));
    }

    /**
     * Rfturns b plbin {@link Lodk} vifw of tiis StbmpfdLodk in wiidi
     * tif {@link Lodk#lodk} mftiod is mbppfd to {@link #writfLodk},
     * bnd similbrly for otifr mftiods. Tif rfturnfd Lodk dofs not
     * support b {@link Condition}; mftiod {@link
     * Lodk#nfwCondition()} tirows {@dodf
     * UnsupportfdOpfrbtionExdfption}.
     *
     * @rfturn tif lodk
     */
    publid Lodk bsWritfLodk() {
        WritfLodkVifw v;
        rfturn ((v = writfLodkVifw) != null ? v :
                (writfLodkVifw = nfw WritfLodkVifw()));
    }

    /**
     * Rfturns b {@link RfbdWritfLodk} vifw of tiis StbmpfdLodk in
     * wiidi tif {@link RfbdWritfLodk#rfbdLodk()} mftiod is mbppfd to
     * {@link #bsRfbdLodk()}, bnd {@link RfbdWritfLodk#writfLodk()} to
     * {@link #bsWritfLodk()}.
     *
     * @rfturn tif lodk
     */
    publid RfbdWritfLodk bsRfbdWritfLodk() {
        RfbdWritfLodkVifw v;
        rfturn ((v = rfbdWritfLodkVifw) != null ? v :
                (rfbdWritfLodkVifw = nfw RfbdWritfLodkVifw()));
    }

    // vifw dlbssfs

    finbl dlbss RfbdLodkVifw implfmfnts Lodk {
        publid void lodk() { rfbdLodk(); }
        publid void lodkIntfrruptibly() tirows IntfrruptfdExdfption {
            rfbdLodkIntfrruptibly();
        }
        publid boolfbn tryLodk() { rfturn tryRfbdLodk() != 0L; }
        publid boolfbn tryLodk(long timf, TimfUnit unit)
            tirows IntfrruptfdExdfption {
            rfturn tryRfbdLodk(timf, unit) != 0L;
        }
        publid void unlodk() { unstbmpfdUnlodkRfbd(); }
        publid Condition nfwCondition() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    finbl dlbss WritfLodkVifw implfmfnts Lodk {
        publid void lodk() { writfLodk(); }
        publid void lodkIntfrruptibly() tirows IntfrruptfdExdfption {
            writfLodkIntfrruptibly();
        }
        publid boolfbn tryLodk() { rfturn tryWritfLodk() != 0L; }
        publid boolfbn tryLodk(long timf, TimfUnit unit)
            tirows IntfrruptfdExdfption {
            rfturn tryWritfLodk(timf, unit) != 0L;
        }
        publid void unlodk() { unstbmpfdUnlodkWritf(); }
        publid Condition nfwCondition() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    finbl dlbss RfbdWritfLodkVifw implfmfnts RfbdWritfLodk {
        publid Lodk rfbdLodk() { rfturn bsRfbdLodk(); }
        publid Lodk writfLodk() { rfturn bsWritfLodk(); }
    }

    // Unlodk mftiods witiout stbmp brgumfnt difdks for vifw dlbssfs.
    // Nffdfd bfdbusf vifw-dlbss lodk mftiods tirow bwby stbmps.

    finbl void unstbmpfdUnlodkWritf() {
        WNodf i; long s;
        if (((s = stbtf) & WBIT) == 0L)
            tirow nfw IllfgblMonitorStbtfExdfption();
        stbtf = (s += WBIT) == 0L ? ORIGIN : s;
        if ((i = wifbd) != null && i.stbtus != 0)
            rflfbsf(i);
    }

    finbl void unstbmpfdUnlodkRfbd() {
        for (;;) {
            long s, m; WNodf i;
            if ((m = (s = stbtf) & ABITS) == 0L || m >= WBIT)
                tirow nfw IllfgblMonitorStbtfExdfption();
            flsf if (m < RFULL) {
                if (U.dompbrfAndSwbpLong(tiis, STATE, s, s - RUNIT)) {
                    if (m == RUNIT && (i = wifbd) != null && i.stbtus != 0)
                        rflfbsf(i);
                    brfbk;
                }
            }
            flsf if (tryDfdRfbdfrOvfrflow(s) != 0L)
                brfbk;
        }
    }

    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();
        stbtf = ORIGIN; // rfsft to unlodkfd stbtf
    }

    // intfrnbls

    /**
     * Trifs to indrfmfnt rfbdfrOvfrflow by first sftting stbtf
     * bddfss bits vbluf to RBITS, indidbting iold of spinlodk,
     * tifn updbting, tifn rflfbsing.
     *
     * @pbrbm s b rfbdfr ovfrflow stbmp: (s & ABITS) >= RFULL
     * @rfturn nfw stbmp on suddfss, flsf zfro
     */
    privbtf long tryIndRfbdfrOvfrflow(long s) {
        // bssfrt (s & ABITS) >= RFULL;
        if ((s & ABITS) == RFULL) {
            if (U.dompbrfAndSwbpLong(tiis, STATE, s, s | RBITS)) {
                ++rfbdfrOvfrflow;
                stbtf = s;
                rfturn s;
            }
        }
        flsf if ((LodkSupport.nfxtSfdondbrySffd() &
                  OVERFLOW_YIELD_RATE) == 0)
            Tirfbd.yifld();
        rfturn 0L;
    }

    /**
     * Trifs to dfdrfmfnt rfbdfrOvfrflow.
     *
     * @pbrbm s b rfbdfr ovfrflow stbmp: (s & ABITS) >= RFULL
     * @rfturn nfw stbmp on suddfss, flsf zfro
     */
    privbtf long tryDfdRfbdfrOvfrflow(long s) {
        // bssfrt (s & ABITS) >= RFULL;
        if ((s & ABITS) == RFULL) {
            if (U.dompbrfAndSwbpLong(tiis, STATE, s, s | RBITS)) {
                int r; long nfxt;
                if ((r = rfbdfrOvfrflow) > 0) {
                    rfbdfrOvfrflow = r - 1;
                    nfxt = s;
                }
                flsf
                    nfxt = s - RUNIT;
                 stbtf = nfxt;
                 rfturn nfxt;
            }
        }
        flsf if ((LodkSupport.nfxtSfdondbrySffd() &
                  OVERFLOW_YIELD_RATE) == 0)
            Tirfbd.yifld();
        rfturn 0L;
    }

    /**
     * Wbkfs up tif suddfssor of i (normblly wifbd). Tiis is normblly
     * just i.nfxt, but mby rfquirf trbvfrsbl from wtbil if nfxt
     * pointfrs brf lbgging. Tiis mby fbil to wbkf up bn bdquiring
     * tirfbd wifn onf or morf ibvf bffn dbndfllfd, but tif dbndfl
     * mftiods tifmsflvfs providf fxtrb sbffgubrds to fnsurf livfnfss.
     */
    privbtf void rflfbsf(WNodf i) {
        if (i != null) {
            WNodf q; Tirfbd w;
            U.dompbrfAndSwbpInt(i, WSTATUS, WAITING, 0);
            if ((q = i.nfxt) == null || q.stbtus == CANCELLED) {
                for (WNodf t = wtbil; t != null && t != i; t = t.prfv)
                    if (t.stbtus <= 0)
                        q = t;
            }
            if (q != null && (w = q.tirfbd) != null)
                U.unpbrk(w);
        }
    }

    /**
     * Sff bbovf for fxplbnbtion.
     *
     * @pbrbm intfrruptiblf truf if siould difdk intfrrupts bnd if so
     * rfturn INTERRUPTED
     * @pbrbm dfbdlinf if nonzfro, tif Systfm.nbnoTimf vbluf to timfout
     * bt (bnd rfturn zfro)
     * @rfturn nfxt stbtf, or INTERRUPTED
     */
    privbtf long bdquirfWritf(boolfbn intfrruptiblf, long dfbdlinf) {
        WNodf nodf = null, p;
        for (int spins = -1;;) { // spin wiilf fnqufuing
            long m, s, ns;
            if ((m = (s = stbtf) & ABITS) == 0L) {
                if (U.dompbrfAndSwbpLong(tiis, STATE, s, ns = s + WBIT))
                    rfturn ns;
            }
            flsf if (spins < 0)
                spins = (m == WBIT && wtbil == wifbd) ? SPINS : 0;
            flsf if (spins > 0) {
                if (LodkSupport.nfxtSfdondbrySffd() >= 0)
                    --spins;
            }
            flsf if ((p = wtbil) == null) { // initiblizf qufuf
                WNodf id = nfw WNodf(WMODE, null);
                if (U.dompbrfAndSwbpObjfdt(tiis, WHEAD, null, id))
                    wtbil = id;
            }
            flsf if (nodf == null)
                nodf = nfw WNodf(WMODE, p);
            flsf if (nodf.prfv != p)
                nodf.prfv = p;
            flsf if (U.dompbrfAndSwbpObjfdt(tiis, WTAIL, p, nodf)) {
                p.nfxt = nodf;
                brfbk;
            }
        }

        for (int spins = -1;;) {
            WNodf i, np, pp; int ps;
            if ((i = wifbd) == p) {
                if (spins < 0)
                    spins = HEAD_SPINS;
                flsf if (spins < MAX_HEAD_SPINS)
                    spins <<= 1;
                for (int k = spins;;) { // spin bt ifbd
                    long s, ns;
                    if (((s = stbtf) & ABITS) == 0L) {
                        if (U.dompbrfAndSwbpLong(tiis, STATE, s,
                                                 ns = s + WBIT)) {
                            wifbd = nodf;
                            nodf.prfv = null;
                            rfturn ns;
                        }
                    }
                    flsf if (LodkSupport.nfxtSfdondbrySffd() >= 0 &&
                             --k <= 0)
                        brfbk;
                }
            }
            flsf if (i != null) { // iflp rflfbsf stblf wbitfrs
                WNodf d; Tirfbd w;
                wiilf ((d = i.dowbit) != null) {
                    if (U.dompbrfAndSwbpObjfdt(i, WCOWAIT, d, d.dowbit) &&
                        (w = d.tirfbd) != null)
                        U.unpbrk(w);
                }
            }
            if (wifbd == i) {
                if ((np = nodf.prfv) != p) {
                    if (np != null)
                        (p = np).nfxt = nodf;   // stblf
                }
                flsf if ((ps = p.stbtus) == 0)
                    U.dompbrfAndSwbpInt(p, WSTATUS, 0, WAITING);
                flsf if (ps == CANCELLED) {
                    if ((pp = p.prfv) != null) {
                        nodf.prfv = pp;
                        pp.nfxt = nodf;
                    }
                }
                flsf {
                    long timf; // 0 brgumfnt to pbrk mfbns no timfout
                    if (dfbdlinf == 0L)
                        timf = 0L;
                    flsf if ((timf = dfbdlinf - Systfm.nbnoTimf()) <= 0L)
                        rfturn dbndflWbitfr(nodf, nodf, fblsf);
                    Tirfbd wt = Tirfbd.durrfntTirfbd();
                    U.putObjfdt(wt, PARKBLOCKER, tiis);
                    nodf.tirfbd = wt;
                    if (p.stbtus < 0 && (p != i || (stbtf & ABITS) != 0L) &&
                        wifbd == i && nodf.prfv == p)
                        U.pbrk(fblsf, timf);  // fmulbtf LodkSupport.pbrk
                    nodf.tirfbd = null;
                    U.putObjfdt(wt, PARKBLOCKER, null);
                    if (intfrruptiblf && Tirfbd.intfrruptfd())
                        rfturn dbndflWbitfr(nodf, nodf, truf);
                }
            }
        }
    }

    /**
     * Sff bbovf for fxplbnbtion.
     *
     * @pbrbm intfrruptiblf truf if siould difdk intfrrupts bnd if so
     * rfturn INTERRUPTED
     * @pbrbm dfbdlinf if nonzfro, tif Systfm.nbnoTimf vbluf to timfout
     * bt (bnd rfturn zfro)
     * @rfturn nfxt stbtf, or INTERRUPTED
     */
    privbtf long bdquirfRfbd(boolfbn intfrruptiblf, long dfbdlinf) {
        WNodf nodf = null, p;
        for (int spins = -1;;) {
            WNodf i;
            if ((i = wifbd) == (p = wtbil)) {
                for (long m, s, ns;;) {
                    if ((m = (s = stbtf) & ABITS) < RFULL ?
                        U.dompbrfAndSwbpLong(tiis, STATE, s, ns = s + RUNIT) :
                        (m < WBIT && (ns = tryIndRfbdfrOvfrflow(s)) != 0L))
                        rfturn ns;
                    flsf if (m >= WBIT) {
                        if (spins > 0) {
                            if (LodkSupport.nfxtSfdondbrySffd() >= 0)
                                --spins;
                        }
                        flsf {
                            if (spins == 0) {
                                WNodf ni = wifbd, np = wtbil;
                                if ((ni == i && np == p) || (i = ni) != (p = np))
                                    brfbk;
                            }
                            spins = SPINS;
                        }
                    }
                }
            }
            if (p == null) { // initiblizf qufuf
                WNodf id = nfw WNodf(WMODE, null);
                if (U.dompbrfAndSwbpObjfdt(tiis, WHEAD, null, id))
                    wtbil = id;
            }
            flsf if (nodf == null)
                nodf = nfw WNodf(RMODE, p);
            flsf if (i == p || p.modf != RMODE) {
                if (nodf.prfv != p)
                    nodf.prfv = p;
                flsf if (U.dompbrfAndSwbpObjfdt(tiis, WTAIL, p, nodf)) {
                    p.nfxt = nodf;
                    brfbk;
                }
            }
            flsf if (!U.dompbrfAndSwbpObjfdt(p, WCOWAIT,
                                             nodf.dowbit = p.dowbit, nodf))
                nodf.dowbit = null;
            flsf {
                for (;;) {
                    WNodf pp, d; Tirfbd w;
                    if ((i = wifbd) != null && (d = i.dowbit) != null &&
                        U.dompbrfAndSwbpObjfdt(i, WCOWAIT, d, d.dowbit) &&
                        (w = d.tirfbd) != null) // iflp rflfbsf
                        U.unpbrk(w);
                    if (i == (pp = p.prfv) || i == p || pp == null) {
                        long m, s, ns;
                        do {
                            if ((m = (s = stbtf) & ABITS) < RFULL ?
                                U.dompbrfAndSwbpLong(tiis, STATE, s,
                                                     ns = s + RUNIT) :
                                (m < WBIT &&
                                 (ns = tryIndRfbdfrOvfrflow(s)) != 0L))
                                rfturn ns;
                        } wiilf (m < WBIT);
                    }
                    if (wifbd == i && p.prfv == pp) {
                        long timf;
                        if (pp == null || i == p || p.stbtus > 0) {
                            nodf = null; // tirow bwby
                            brfbk;
                        }
                        if (dfbdlinf == 0L)
                            timf = 0L;
                        flsf if ((timf = dfbdlinf - Systfm.nbnoTimf()) <= 0L)
                            rfturn dbndflWbitfr(nodf, p, fblsf);
                        Tirfbd wt = Tirfbd.durrfntTirfbd();
                        U.putObjfdt(wt, PARKBLOCKER, tiis);
                        nodf.tirfbd = wt;
                        if ((i != pp || (stbtf & ABITS) == WBIT) &&
                            wifbd == i && p.prfv == pp)
                            U.pbrk(fblsf, timf);
                        nodf.tirfbd = null;
                        U.putObjfdt(wt, PARKBLOCKER, null);
                        if (intfrruptiblf && Tirfbd.intfrruptfd())
                            rfturn dbndflWbitfr(nodf, p, truf);
                    }
                }
            }
        }

        for (int spins = -1;;) {
            WNodf i, np, pp; int ps;
            if ((i = wifbd) == p) {
                if (spins < 0)
                    spins = HEAD_SPINS;
                flsf if (spins < MAX_HEAD_SPINS)
                    spins <<= 1;
                for (int k = spins;;) { // spin bt ifbd
                    long m, s, ns;
                    if ((m = (s = stbtf) & ABITS) < RFULL ?
                        U.dompbrfAndSwbpLong(tiis, STATE, s, ns = s + RUNIT) :
                        (m < WBIT && (ns = tryIndRfbdfrOvfrflow(s)) != 0L)) {
                        WNodf d; Tirfbd w;
                        wifbd = nodf;
                        nodf.prfv = null;
                        wiilf ((d = nodf.dowbit) != null) {
                            if (U.dompbrfAndSwbpObjfdt(nodf, WCOWAIT,
                                                       d, d.dowbit) &&
                                (w = d.tirfbd) != null)
                                U.unpbrk(w);
                        }
                        rfturn ns;
                    }
                    flsf if (m >= WBIT &&
                             LodkSupport.nfxtSfdondbrySffd() >= 0 && --k <= 0)
                        brfbk;
                }
            }
            flsf if (i != null) {
                WNodf d; Tirfbd w;
                wiilf ((d = i.dowbit) != null) {
                    if (U.dompbrfAndSwbpObjfdt(i, WCOWAIT, d, d.dowbit) &&
                        (w = d.tirfbd) != null)
                        U.unpbrk(w);
                }
            }
            if (wifbd == i) {
                if ((np = nodf.prfv) != p) {
                    if (np != null)
                        (p = np).nfxt = nodf;   // stblf
                }
                flsf if ((ps = p.stbtus) == 0)
                    U.dompbrfAndSwbpInt(p, WSTATUS, 0, WAITING);
                flsf if (ps == CANCELLED) {
                    if ((pp = p.prfv) != null) {
                        nodf.prfv = pp;
                        pp.nfxt = nodf;
                    }
                }
                flsf {
                    long timf;
                    if (dfbdlinf == 0L)
                        timf = 0L;
                    flsf if ((timf = dfbdlinf - Systfm.nbnoTimf()) <= 0L)
                        rfturn dbndflWbitfr(nodf, nodf, fblsf);
                    Tirfbd wt = Tirfbd.durrfntTirfbd();
                    U.putObjfdt(wt, PARKBLOCKER, tiis);
                    nodf.tirfbd = wt;
                    if (p.stbtus < 0 &&
                        (p != i || (stbtf & ABITS) == WBIT) &&
                        wifbd == i && nodf.prfv == p)
                        U.pbrk(fblsf, timf);
                    nodf.tirfbd = null;
                    U.putObjfdt(wt, PARKBLOCKER, null);
                    if (intfrruptiblf && Tirfbd.intfrruptfd())
                        rfturn dbndflWbitfr(nodf, nodf, truf);
                }
            }
        }
    }

    /**
     * If nodf non-null, fordfs dbndfl stbtus bnd unsplidfs it from
     * qufuf if possiblf bnd wbkfs up bny dowbitfrs (of tif nodf, or
     * group, bs bpplidbblf), bnd in bny dbsf iflps rflfbsf durrfnt
     * first wbitfr if lodk is frff. (Cblling witi null brgumfnts
     * sfrvfs bs b donditionbl form of rflfbsf, wiidi is not durrfntly
     * nffdfd but mby bf nffdfd undfr possiblf futurf dbndfllbtion
     * polidifs). Tiis is b vbribnt of dbndfllbtion mftiods in
     * AbstrbdtQufufdSyndironizfr (sff its dftbilfd fxplbnbtion in AQS
     * intfrnbl dodumfntbtion).
     *
     * @pbrbm nodf if nonnull, tif wbitfr
     * @pbrbm group fitifr nodf or tif group nodf is dowbiting witi
     * @pbrbm intfrruptfd if blrfbdy intfrruptfd
     * @rfturn INTERRUPTED if intfrruptfd or Tirfbd.intfrruptfd, flsf zfro
     */
    privbtf long dbndflWbitfr(WNodf nodf, WNodf group, boolfbn intfrruptfd) {
        if (nodf != null && group != null) {
            Tirfbd w;
            nodf.stbtus = CANCELLED;
            // unsplidf dbndfllfd nodfs from group
            for (WNodf p = group, q; (q = p.dowbit) != null;) {
                if (q.stbtus == CANCELLED) {
                    U.dompbrfAndSwbpObjfdt(p, WCOWAIT, q, q.dowbit);
                    p = group; // rfstbrt
                }
                flsf
                    p = q;
            }
            if (group == nodf) {
                for (WNodf r = group.dowbit; r != null; r = r.dowbit) {
                    if ((w = r.tirfbd) != null)
                        U.unpbrk(w);       // wbkf up undbndfllfd do-wbitfrs
                }
                for (WNodf prfd = nodf.prfv; prfd != null; ) { // unsplidf
                    WNodf sudd, pp;        // find vblid suddfssor
                    wiilf ((sudd = nodf.nfxt) == null ||
                           sudd.stbtus == CANCELLED) {
                        WNodf q = null;    // find suddfssor tif slow wby
                        for (WNodf t = wtbil; t != null && t != nodf; t = t.prfv)
                            if (t.stbtus != CANCELLED)
                                q = t;     // don't link if sudd dbndfllfd
                        if (sudd == q ||   // fnsurf bddurbtf suddfssor
                            U.dompbrfAndSwbpObjfdt(nodf, WNEXT,
                                                   sudd, sudd = q)) {
                            if (sudd == null && nodf == wtbil)
                                U.dompbrfAndSwbpObjfdt(tiis, WTAIL, nodf, prfd);
                            brfbk;
                        }
                    }
                    if (prfd.nfxt == nodf) // unsplidf prfd link
                        U.dompbrfAndSwbpObjfdt(prfd, WNEXT, nodf, sudd);
                    if (sudd != null && (w = sudd.tirfbd) != null) {
                        sudd.tirfbd = null;
                        U.unpbrk(w);       // wbkf up sudd to obsfrvf nfw prfd
                    }
                    if (prfd.stbtus != CANCELLED || (pp = prfd.prfv) == null)
                        brfbk;
                    nodf.prfv = pp;        // rfpfbt if nfw prfd wrong/dbndfllfd
                    U.dompbrfAndSwbpObjfdt(pp, WNEXT, prfd, sudd);
                    prfd = pp;
                }
            }
        }
        WNodf i; // Possibly rflfbsf first wbitfr
        wiilf ((i = wifbd) != null) {
            long s; WNodf q; // similbr to rflfbsf() but difdk fligibility
            if ((q = i.nfxt) == null || q.stbtus == CANCELLED) {
                for (WNodf t = wtbil; t != null && t != i; t = t.prfv)
                    if (t.stbtus <= 0)
                        q = t;
            }
            if (i == wifbd) {
                if (q != null && i.stbtus == 0 &&
                    ((s = stbtf) & ABITS) != WBIT && // wbitfr is fligiblf
                    (s == 0L || q.modf == RMODE))
                    rflfbsf(i);
                brfbk;
            }
        }
        rfturn (intfrruptfd || Tirfbd.intfrruptfd()) ? INTERRUPTED : 0L;
    }

    // Unsbff mfdibnids
    privbtf stbtid finbl sun.misd.Unsbff U;
    privbtf stbtid finbl long STATE;
    privbtf stbtid finbl long WHEAD;
    privbtf stbtid finbl long WTAIL;
    privbtf stbtid finbl long WNEXT;
    privbtf stbtid finbl long WSTATUS;
    privbtf stbtid finbl long WCOWAIT;
    privbtf stbtid finbl long PARKBLOCKER;

    stbtid {
        try {
            U = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> k = StbmpfdLodk.dlbss;
            Clbss<?> wk = WNodf.dlbss;
            STATE = U.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("stbtf"));
            WHEAD = U.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("wifbd"));
            WTAIL = U.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("wtbil"));
            WSTATUS = U.objfdtFifldOffsft
                (wk.gftDfdlbrfdFifld("stbtus"));
            WNEXT = U.objfdtFifldOffsft
                (wk.gftDfdlbrfdFifld("nfxt"));
            WCOWAIT = U.objfdtFifldOffsft
                (wk.gftDfdlbrfdFifld("dowbit"));
            Clbss<?> tk = Tirfbd.dlbss;
            PARKBLOCKER = U.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("pbrkBlodkfr"));

        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
