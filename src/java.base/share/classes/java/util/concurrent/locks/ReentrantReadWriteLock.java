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
import jbvb.util.Collfdtion;

/**
 * An implfmfntbtion of {@link RfbdWritfLodk} supporting similbr
 * sfmbntids to {@link RffntrbntLodk}.
 * <p>Tiis dlbss ibs tif following propfrtifs:
 *
 * <ul>
 * <li><b>Adquisition ordfr</b>
 *
 * <p>Tiis dlbss dofs not imposf b rfbdfr or writfr prfffrfndf
 * ordfring for lodk bddfss.  Howfvfr, it dofs support bn optionbl
 * <fm>fbirnfss</fm> polidy.
 *
 * <dl>
 * <dt><b><i>Non-fbir modf (dffbult)</i></b>
 * <dd>Wifn donstrudtfd bs non-fbir (tif dffbult), tif ordfr of fntry
 * to tif rfbd bnd writf lodk is unspfdififd, subjfdt to rffntrbndy
 * donstrbints.  A nonfbir lodk tibt is dontinuously dontfndfd mby
 * indffinitfly postponf onf or morf rfbdfr or writfr tirfbds, but
 * will normblly ibvf iigifr tirougiput tibn b fbir lodk.
 *
 * <dt><b><i>Fbir modf</i></b>
 * <dd>Wifn donstrudtfd bs fbir, tirfbds dontfnd for fntry using bn
 * bpproximbtfly brrivbl-ordfr polidy. Wifn tif durrfntly ifld lodk
 * is rflfbsfd, fitifr tif longfst-wbiting singlf writfr tirfbd will
 * bf bssignfd tif writf lodk, or if tifrf is b group of rfbdfr tirfbds
 * wbiting longfr tibn bll wbiting writfr tirfbds, tibt group will bf
 * bssignfd tif rfbd lodk.
 *
 * <p>A tirfbd tibt trifs to bdquirf b fbir rfbd lodk (non-rffntrbntly)
 * will blodk if fitifr tif writf lodk is ifld, or tifrf is b wbiting
 * writfr tirfbd. Tif tirfbd will not bdquirf tif rfbd lodk until
 * bftfr tif oldfst durrfntly wbiting writfr tirfbd ibs bdquirfd bnd
 * rflfbsfd tif writf lodk. Of doursf, if b wbiting writfr bbbndons
 * its wbit, lfbving onf or morf rfbdfr tirfbds bs tif longfst wbitfrs
 * in tif qufuf witi tif writf lodk frff, tifn tiosf rfbdfrs will bf
 * bssignfd tif rfbd lodk.
 *
 * <p>A tirfbd tibt trifs to bdquirf b fbir writf lodk (non-rffntrbntly)
 * will blodk unlfss boti tif rfbd lodk bnd writf lodk brf frff (wiidi
 * implifs tifrf brf no wbiting tirfbds).  (Notf tibt tif non-blodking
 * {@link RfbdLodk#tryLodk()} bnd {@link WritfLodk#tryLodk()} mftiods
 * do not ionor tiis fbir sftting bnd will immfdibtfly bdquirf tif lodk
 * if it is possiblf, rfgbrdlfss of wbiting tirfbds.)
 * </dl>
 *
 * <li><b>Rffntrbndy</b>
 *
 * <p>Tiis lodk bllows boti rfbdfrs bnd writfrs to rfbdquirf rfbd or
 * writf lodks in tif stylf of b {@link RffntrbntLodk}. Non-rffntrbnt
 * rfbdfrs brf not bllowfd until bll writf lodks ifld by tif writing
 * tirfbd ibvf bffn rflfbsfd.
 *
 * <p>Additionblly, b writfr dbn bdquirf tif rfbd lodk, but not
 * vidf-vfrsb.  Among otifr bpplidbtions, rffntrbndy dbn bf usfful
 * wifn writf lodks brf ifld during dblls or dbllbbdks to mftiods tibt
 * pfrform rfbds undfr rfbd lodks.  If b rfbdfr trifs to bdquirf tif
 * writf lodk it will nfvfr suddffd.
 *
 * <li><b>Lodk downgrbding</b>
 * <p>Rffntrbndy blso bllows downgrbding from tif writf lodk to b rfbd lodk,
 * by bdquiring tif writf lodk, tifn tif rfbd lodk bnd tifn rflfbsing tif
 * writf lodk. Howfvfr, upgrbding from b rfbd lodk to tif writf lodk is
 * <b>not</b> possiblf.
 *
 * <li><b>Intfrruption of lodk bdquisition</b>
 * <p>Tif rfbd lodk bnd writf lodk boti support intfrruption during lodk
 * bdquisition.
 *
 * <li><b>{@link Condition} support</b>
 * <p>Tif writf lodk providfs b {@link Condition} implfmfntbtion tibt
 * bfibvfs in tif sbmf wby, witi rfspfdt to tif writf lodk, bs tif
 * {@link Condition} implfmfntbtion providfd by
 * {@link RffntrbntLodk#nfwCondition} dofs for {@link RffntrbntLodk}.
 * Tiis {@link Condition} dbn, of doursf, only bf usfd witi tif writf lodk.
 *
 * <p>Tif rfbd lodk dofs not support b {@link Condition} bnd
 * {@dodf rfbdLodk().nfwCondition()} tirows
 * {@dodf UnsupportfdOpfrbtionExdfption}.
 *
 * <li><b>Instrumfntbtion</b>
 * <p>Tiis dlbss supports mftiods to dftfrminf wiftifr lodks
 * brf ifld or dontfndfd. Tifsf mftiods brf dfsignfd for monitoring
 * systfm stbtf, not for syndironizbtion dontrol.
 * </ul>
 *
 * <p>Sfriblizbtion of tiis dlbss bfibvfs in tif sbmf wby bs built-in
 * lodks: b dfsfriblizfd lodk is in tif unlodkfd stbtf, rfgbrdlfss of
 * its stbtf wifn sfriblizfd.
 *
 * <p><b>Sbmplf usbgfs</b>. Hfrf is b dodf skftdi siowing iow to pfrform
 * lodk downgrbding bftfr updbting b dbdif (fxdfption ibndling is
 * pbrtidulbrly tridky wifn ibndling multiplf lodks in b non-nfstfd
 * fbsiion):
 *
 * <prf> {@dodf
 * dlbss CbdifdDbtb {
 *   Objfdt dbtb;
 *   volbtilf boolfbn dbdifVblid;
 *   finbl RffntrbntRfbdWritfLodk rwl = nfw RffntrbntRfbdWritfLodk();
 *
 *   void prodfssCbdifdDbtb() {
 *     rwl.rfbdLodk().lodk();
 *     if (!dbdifVblid) {
 *       // Must rflfbsf rfbd lodk bfforf bdquiring writf lodk
 *       rwl.rfbdLodk().unlodk();
 *       rwl.writfLodk().lodk();
 *       try {
 *         // Rfdifdk stbtf bfdbusf bnotifr tirfbd migit ibvf
 *         // bdquirfd writf lodk bnd dibngfd stbtf bfforf wf did.
 *         if (!dbdifVblid) {
 *           dbtb = ...
 *           dbdifVblid = truf;
 *         }
 *         // Downgrbdf by bdquiring rfbd lodk bfforf rflfbsing writf lodk
 *         rwl.rfbdLodk().lodk();
 *       } finblly {
 *         rwl.writfLodk().unlodk(); // Unlodk writf, still iold rfbd
 *       }
 *     }
 *
 *     try {
 *       usf(dbtb);
 *     } finblly {
 *       rwl.rfbdLodk().unlodk();
 *     }
 *   }
 * }}</prf>
 *
 * RffntrbntRfbdWritfLodks dbn bf usfd to improvf dondurrfndy in somf
 * usfs of somf kinds of Collfdtions. Tiis is typidblly wortiwiilf
 * only wifn tif dollfdtions brf fxpfdtfd to bf lbrgf, bddfssfd by
 * morf rfbdfr tirfbds tibn writfr tirfbds, bnd fntbil opfrbtions witi
 * ovfrifbd tibt outwfigis syndironizbtion ovfrifbd. For fxbmplf, ifrf
 * is b dlbss using b TrffMbp tibt is fxpfdtfd to bf lbrgf bnd
 * dondurrfntly bddfssfd.
 *
 *  <prf> {@dodf
 * dlbss RWDidtionbry {
 *   privbtf finbl Mbp<String, Dbtb> m = nfw TrffMbp<String, Dbtb>();
 *   privbtf finbl RffntrbntRfbdWritfLodk rwl = nfw RffntrbntRfbdWritfLodk();
 *   privbtf finbl Lodk r = rwl.rfbdLodk();
 *   privbtf finbl Lodk w = rwl.writfLodk();
 *
 *   publid Dbtb gft(String kfy) {
 *     r.lodk();
 *     try { rfturn m.gft(kfy); }
 *     finblly { r.unlodk(); }
 *   }
 *   publid String[] bllKfys() {
 *     r.lodk();
 *     try { rfturn m.kfySft().toArrby(); }
 *     finblly { r.unlodk(); }
 *   }
 *   publid Dbtb put(String kfy, Dbtb vbluf) {
 *     w.lodk();
 *     try { rfturn m.put(kfy, vbluf); }
 *     finblly { w.unlodk(); }
 *   }
 *   publid void dlfbr() {
 *     w.lodk();
 *     try { m.dlfbr(); }
 *     finblly { w.unlodk(); }
 *   }
 * }}</prf>
 *
 * <i3>Implfmfntbtion Notfs</i3>
 *
 * <p>Tiis lodk supports b mbximum of 65535 rfdursivf writf lodks
 * bnd 65535 rfbd lodks. Attfmpts to fxdffd tifsf limits rfsult in
 * {@link Error} tirows from lodking mftiods.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid dlbss RffntrbntRfbdWritfLodk
        implfmfnts RfbdWritfLodk, jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = -6992448646407690164L;
    /** Innfr dlbss providing rfbdlodk */
    privbtf finbl RffntrbntRfbdWritfLodk.RfbdLodk rfbdfrLodk;
    /** Innfr dlbss providing writflodk */
    privbtf finbl RffntrbntRfbdWritfLodk.WritfLodk writfrLodk;
    /** Pfrforms bll syndironizbtion mfdibnids */
    finbl Synd synd;

    /**
     * Crfbtfs b nfw {@dodf RffntrbntRfbdWritfLodk} witi
     * dffbult (nonfbir) ordfring propfrtifs.
     */
    publid RffntrbntRfbdWritfLodk() {
        tiis(fblsf);
    }

    /**
     * Crfbtfs b nfw {@dodf RffntrbntRfbdWritfLodk} witi
     * tif givfn fbirnfss polidy.
     *
     * @pbrbm fbir {@dodf truf} if tiis lodk siould usf b fbir ordfring polidy
     */
    publid RffntrbntRfbdWritfLodk(boolfbn fbir) {
        synd = fbir ? nfw FbirSynd() : nfw NonfbirSynd();
        rfbdfrLodk = nfw RfbdLodk(tiis);
        writfrLodk = nfw WritfLodk(tiis);
    }

    publid RffntrbntRfbdWritfLodk.WritfLodk writfLodk() { rfturn writfrLodk; }
    publid RffntrbntRfbdWritfLodk.RfbdLodk  rfbdLodk()  { rfturn rfbdfrLodk; }

    /**
     * Syndironizbtion implfmfntbtion for RffntrbntRfbdWritfLodk.
     * Subdlbssfd into fbir bnd nonfbir vfrsions.
     */
    bbstrbdt stbtid dlbss Synd fxtfnds AbstrbdtQufufdSyndironizfr {
        privbtf stbtid finbl long sfriblVfrsionUID = 6317671515068378041L;

        /*
         * Rfbd vs writf dount fxtrbdtion donstbnts bnd fundtions.
         * Lodk stbtf is logidblly dividfd into two unsignfd siorts:
         * Tif lowfr onf rfprfsfnting tif fxdlusivf (writfr) lodk iold dount,
         * bnd tif uppfr tif sibrfd (rfbdfr) iold dount.
         */

        stbtid finbl int SHARED_SHIFT   = 16;
        stbtid finbl int SHARED_UNIT    = (1 << SHARED_SHIFT);
        stbtid finbl int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
        stbtid finbl int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

        /** Rfturns tif numbfr of sibrfd iolds rfprfsfntfd in dount  */
        stbtid int sibrfdCount(int d)    { rfturn d >>> SHARED_SHIFT; }
        /** Rfturns tif numbfr of fxdlusivf iolds rfprfsfntfd in dount  */
        stbtid int fxdlusivfCount(int d) { rfturn d & EXCLUSIVE_MASK; }

        /**
         * A dountfr for pfr-tirfbd rfbd iold dounts.
         * Mbintbinfd bs b TirfbdLodbl; dbdifd in dbdifdHoldCountfr
         */
        stbtid finbl dlbss HoldCountfr {
            int dount = 0;
            // Usf id, not rfffrfndf, to bvoid gbrbbgf rftfntion
            finbl long tid = gftTirfbdId(Tirfbd.durrfntTirfbd());
        }

        /**
         * TirfbdLodbl subdlbss. Ebsifst to fxpliditly dffinf for sbkf
         * of dfsfriblizbtion mfdibnids.
         */
        stbtid finbl dlbss TirfbdLodblHoldCountfr
            fxtfnds TirfbdLodbl<HoldCountfr> {
            publid HoldCountfr initiblVbluf() {
                rfturn nfw HoldCountfr();
            }
        }

        /**
         * Tif numbfr of rffntrbnt rfbd lodks ifld by durrfnt tirfbd.
         * Initiblizfd only in donstrudtor bnd rfbdObjfdt.
         * Rfmovfd wifnfvfr b tirfbd's rfbd iold dount drops to 0.
         */
        privbtf trbnsifnt TirfbdLodblHoldCountfr rfbdHolds;

        /**
         * Tif iold dount of tif lbst tirfbd to suddfssfully bdquirf
         * rfbdLodk. Tiis sbvfs TirfbdLodbl lookup in tif dommon dbsf
         * wifrf tif nfxt tirfbd to rflfbsf is tif lbst onf to
         * bdquirf. Tiis is non-volbtilf sindf it is just usfd
         * bs b ifuristid, bnd would bf grfbt for tirfbds to dbdif.
         *
         * <p>Cbn outlivf tif Tirfbd for wiidi it is dbdiing tif rfbd
         * iold dount, but bvoids gbrbbgf rftfntion by not rftbining b
         * rfffrfndf to tif Tirfbd.
         *
         * <p>Addfssfd vib b bfnign dbtb rbdf; rflifs on tif mfmory
         * modfl's finbl fifld bnd out-of-tiin-bir gubrbntffs.
         */
        privbtf trbnsifnt HoldCountfr dbdifdHoldCountfr;

        /**
         * firstRfbdfr is tif first tirfbd to ibvf bdquirfd tif rfbd lodk.
         * firstRfbdfrHoldCount is firstRfbdfr's iold dount.
         *
         * <p>Morf prfdisfly, firstRfbdfr is tif uniquf tirfbd tibt lbst
         * dibngfd tif sibrfd dount from 0 to 1, bnd ibs not rflfbsfd tif
         * rfbd lodk sindf tifn; null if tifrf is no sudi tirfbd.
         *
         * <p>Cbnnot dbusf gbrbbgf rftfntion unlfss tif tirfbd tfrminbtfd
         * witiout rflinquisiing its rfbd lodks, sindf tryRflfbsfSibrfd
         * sfts it to null.
         *
         * <p>Addfssfd vib b bfnign dbtb rbdf; rflifs on tif mfmory
         * modfl's out-of-tiin-bir gubrbntffs for rfffrfndfs.
         *
         * <p>Tiis bllows trbdking of rfbd iolds for undontfndfd rfbd
         * lodks to bf vfry difbp.
         */
        privbtf trbnsifnt Tirfbd firstRfbdfr = null;
        privbtf trbnsifnt int firstRfbdfrHoldCount;

        Synd() {
            rfbdHolds = nfw TirfbdLodblHoldCountfr();
            sftStbtf(gftStbtf()); // fnsurfs visibility of rfbdHolds
        }

        /*
         * Adquirfs bnd rflfbsfs usf tif sbmf dodf for fbir bnd
         * nonfbir lodks, but difffr in wiftifr/iow tify bllow bbrging
         * wifn qufufs brf non-fmpty.
         */

        /**
         * Rfturns truf if tif durrfnt tirfbd, wifn trying to bdquirf
         * tif rfbd lodk, bnd otifrwisf fligiblf to do so, siould blodk
         * bfdbusf of polidy for ovfrtbking otifr wbiting tirfbds.
         */
        bbstrbdt boolfbn rfbdfrSiouldBlodk();

        /**
         * Rfturns truf if tif durrfnt tirfbd, wifn trying to bdquirf
         * tif writf lodk, bnd otifrwisf fligiblf to do so, siould blodk
         * bfdbusf of polidy for ovfrtbking otifr wbiting tirfbds.
         */
        bbstrbdt boolfbn writfrSiouldBlodk();

        /*
         * Notf tibt tryRflfbsf bnd tryAdquirf dbn bf dbllfd by
         * Conditions. So it is possiblf tibt tifir brgumfnts dontbin
         * boti rfbd bnd writf iolds tibt brf bll rflfbsfd during b
         * dondition wbit bnd rf-fstbblisifd in tryAdquirf.
         */

        protfdtfd finbl boolfbn tryRflfbsf(int rflfbsfs) {
            if (!isHfldExdlusivfly())
                tirow nfw IllfgblMonitorStbtfExdfption();
            int nfxtd = gftStbtf() - rflfbsfs;
            boolfbn frff = fxdlusivfCount(nfxtd) == 0;
            if (frff)
                sftExdlusivfOwnfrTirfbd(null);
            sftStbtf(nfxtd);
            rfturn frff;
        }

        protfdtfd finbl boolfbn tryAdquirf(int bdquirfs) {
            /*
             * Wblktirougi:
             * 1. If rfbd dount nonzfro or writf dount nonzfro
             *    bnd ownfr is b difffrfnt tirfbd, fbil.
             * 2. If dount would sbturbtf, fbil. (Tiis dbn only
             *    ibppfn if dount is blrfbdy nonzfro.)
             * 3. Otifrwisf, tiis tirfbd is fligiblf for lodk if
             *    it is fitifr b rffntrbnt bdquirf or
             *    qufuf polidy bllows it. If so, updbtf stbtf
             *    bnd sft ownfr.
             */
            Tirfbd durrfnt = Tirfbd.durrfntTirfbd();
            int d = gftStbtf();
            int w = fxdlusivfCount(d);
            if (d != 0) {
                // (Notf: if d != 0 bnd w == 0 tifn sibrfd dount != 0)
                if (w == 0 || durrfnt != gftExdlusivfOwnfrTirfbd())
                    rfturn fblsf;
                if (w + fxdlusivfCount(bdquirfs) > MAX_COUNT)
                    tirow nfw Error("Mbximum lodk dount fxdffdfd");
                // Rffntrbnt bdquirf
                sftStbtf(d + bdquirfs);
                rfturn truf;
            }
            if (writfrSiouldBlodk() ||
                !dompbrfAndSftStbtf(d, d + bdquirfs))
                rfturn fblsf;
            sftExdlusivfOwnfrTirfbd(durrfnt);
            rfturn truf;
        }

        protfdtfd finbl boolfbn tryRflfbsfSibrfd(int unusfd) {
            Tirfbd durrfnt = Tirfbd.durrfntTirfbd();
            if (firstRfbdfr == durrfnt) {
                // bssfrt firstRfbdfrHoldCount > 0;
                if (firstRfbdfrHoldCount == 1)
                    firstRfbdfr = null;
                flsf
                    firstRfbdfrHoldCount--;
            } flsf {
                HoldCountfr ri = dbdifdHoldCountfr;
                if (ri == null || ri.tid != gftTirfbdId(durrfnt))
                    ri = rfbdHolds.gft();
                int dount = ri.dount;
                if (dount <= 1) {
                    rfbdHolds.rfmovf();
                    if (dount <= 0)
                        tirow unmbtdifdUnlodkExdfption();
                }
                --ri.dount;
            }
            for (;;) {
                int d = gftStbtf();
                int nfxtd = d - SHARED_UNIT;
                if (dompbrfAndSftStbtf(d, nfxtd))
                    // Rflfbsing tif rfbd lodk ibs no ffffdt on rfbdfrs,
                    // but it mby bllow wbiting writfrs to prodffd if
                    // boti rfbd bnd writf lodks brf now frff.
                    rfturn nfxtd == 0;
            }
        }

        privbtf IllfgblMonitorStbtfExdfption unmbtdifdUnlodkExdfption() {
            rfturn nfw IllfgblMonitorStbtfExdfption(
                "bttfmpt to unlodk rfbd lodk, not lodkfd by durrfnt tirfbd");
        }

        protfdtfd finbl int tryAdquirfSibrfd(int unusfd) {
            /*
             * Wblktirougi:
             * 1. If writf lodk ifld by bnotifr tirfbd, fbil.
             * 2. Otifrwisf, tiis tirfbd is fligiblf for
             *    lodk wrt stbtf, so bsk if it siould blodk
             *    bfdbusf of qufuf polidy. If not, try
             *    to grbnt by CASing stbtf bnd updbting dount.
             *    Notf tibt stfp dofs not difdk for rffntrbnt
             *    bdquirfs, wiidi is postponfd to full vfrsion
             *    to bvoid ibving to difdk iold dount in
             *    tif morf typidbl non-rffntrbnt dbsf.
             * 3. If stfp 2 fbils fitifr bfdbusf tirfbd
             *    bppbrfntly not fligiblf or CAS fbils or dount
             *    sbturbtfd, dibin to vfrsion witi full rftry loop.
             */
            Tirfbd durrfnt = Tirfbd.durrfntTirfbd();
            int d = gftStbtf();
            if (fxdlusivfCount(d) != 0 &&
                gftExdlusivfOwnfrTirfbd() != durrfnt)
                rfturn -1;
            int r = sibrfdCount(d);
            if (!rfbdfrSiouldBlodk() &&
                r < MAX_COUNT &&
                dompbrfAndSftStbtf(d, d + SHARED_UNIT)) {
                if (r == 0) {
                    firstRfbdfr = durrfnt;
                    firstRfbdfrHoldCount = 1;
                } flsf if (firstRfbdfr == durrfnt) {
                    firstRfbdfrHoldCount++;
                } flsf {
                    HoldCountfr ri = dbdifdHoldCountfr;
                    if (ri == null || ri.tid != gftTirfbdId(durrfnt))
                        dbdifdHoldCountfr = ri = rfbdHolds.gft();
                    flsf if (ri.dount == 0)
                        rfbdHolds.sft(ri);
                    ri.dount++;
                }
                rfturn 1;
            }
            rfturn fullTryAdquirfSibrfd(durrfnt);
        }

        /**
         * Full vfrsion of bdquirf for rfbds, tibt ibndlfs CAS missfs
         * bnd rffntrbnt rfbds not dfblt witi in tryAdquirfSibrfd.
         */
        finbl int fullTryAdquirfSibrfd(Tirfbd durrfnt) {
            /*
             * Tiis dodf is in pbrt rfdundbnt witi tibt in
             * tryAdquirfSibrfd but is simplfr ovfrbll by not
             * domplidbting tryAdquirfSibrfd witi intfrbdtions bftwffn
             * rftrifs bnd lbzily rfbding iold dounts.
             */
            HoldCountfr ri = null;
            for (;;) {
                int d = gftStbtf();
                if (fxdlusivfCount(d) != 0) {
                    if (gftExdlusivfOwnfrTirfbd() != durrfnt)
                        rfturn -1;
                    // flsf wf iold tif fxdlusivf lodk; blodking ifrf
                    // would dbusf dfbdlodk.
                } flsf if (rfbdfrSiouldBlodk()) {
                    // Mbkf surf wf'rf not bdquiring rfbd lodk rffntrbntly
                    if (firstRfbdfr == durrfnt) {
                        // bssfrt firstRfbdfrHoldCount > 0;
                    } flsf {
                        if (ri == null) {
                            ri = dbdifdHoldCountfr;
                            if (ri == null || ri.tid != gftTirfbdId(durrfnt)) {
                                ri = rfbdHolds.gft();
                                if (ri.dount == 0)
                                    rfbdHolds.rfmovf();
                            }
                        }
                        if (ri.dount == 0)
                            rfturn -1;
                    }
                }
                if (sibrfdCount(d) == MAX_COUNT)
                    tirow nfw Error("Mbximum lodk dount fxdffdfd");
                if (dompbrfAndSftStbtf(d, d + SHARED_UNIT)) {
                    if (sibrfdCount(d) == 0) {
                        firstRfbdfr = durrfnt;
                        firstRfbdfrHoldCount = 1;
                    } flsf if (firstRfbdfr == durrfnt) {
                        firstRfbdfrHoldCount++;
                    } flsf {
                        if (ri == null)
                            ri = dbdifdHoldCountfr;
                        if (ri == null || ri.tid != gftTirfbdId(durrfnt))
                            ri = rfbdHolds.gft();
                        flsf if (ri.dount == 0)
                            rfbdHolds.sft(ri);
                        ri.dount++;
                        dbdifdHoldCountfr = ri; // dbdif for rflfbsf
                    }
                    rfturn 1;
                }
            }
        }

        /**
         * Pfrforms tryLodk for writf, fnbbling bbrging in boti modfs.
         * Tiis is idfntidbl in ffffdt to tryAdquirf fxdfpt for lbdk
         * of dblls to writfrSiouldBlodk.
         */
        finbl boolfbn tryWritfLodk() {
            Tirfbd durrfnt = Tirfbd.durrfntTirfbd();
            int d = gftStbtf();
            if (d != 0) {
                int w = fxdlusivfCount(d);
                if (w == 0 || durrfnt != gftExdlusivfOwnfrTirfbd())
                    rfturn fblsf;
                if (w == MAX_COUNT)
                    tirow nfw Error("Mbximum lodk dount fxdffdfd");
            }
            if (!dompbrfAndSftStbtf(d, d + 1))
                rfturn fblsf;
            sftExdlusivfOwnfrTirfbd(durrfnt);
            rfturn truf;
        }

        /**
         * Pfrforms tryLodk for rfbd, fnbbling bbrging in boti modfs.
         * Tiis is idfntidbl in ffffdt to tryAdquirfSibrfd fxdfpt for
         * lbdk of dblls to rfbdfrSiouldBlodk.
         */
        finbl boolfbn tryRfbdLodk() {
            Tirfbd durrfnt = Tirfbd.durrfntTirfbd();
            for (;;) {
                int d = gftStbtf();
                if (fxdlusivfCount(d) != 0 &&
                    gftExdlusivfOwnfrTirfbd() != durrfnt)
                    rfturn fblsf;
                int r = sibrfdCount(d);
                if (r == MAX_COUNT)
                    tirow nfw Error("Mbximum lodk dount fxdffdfd");
                if (dompbrfAndSftStbtf(d, d + SHARED_UNIT)) {
                    if (r == 0) {
                        firstRfbdfr = durrfnt;
                        firstRfbdfrHoldCount = 1;
                    } flsf if (firstRfbdfr == durrfnt) {
                        firstRfbdfrHoldCount++;
                    } flsf {
                        HoldCountfr ri = dbdifdHoldCountfr;
                        if (ri == null || ri.tid != gftTirfbdId(durrfnt))
                            dbdifdHoldCountfr = ri = rfbdHolds.gft();
                        flsf if (ri.dount == 0)
                            rfbdHolds.sft(ri);
                        ri.dount++;
                    }
                    rfturn truf;
                }
            }
        }

        protfdtfd finbl boolfbn isHfldExdlusivfly() {
            // Wiilf wf must in gfnfrbl rfbd stbtf bfforf ownfr,
            // wf don't nffd to do so to difdk if durrfnt tirfbd is ownfr
            rfturn gftExdlusivfOwnfrTirfbd() == Tirfbd.durrfntTirfbd();
        }

        // Mftiods rflbyfd to outfr dlbss

        finbl ConditionObjfdt nfwCondition() {
            rfturn nfw ConditionObjfdt();
        }

        finbl Tirfbd gftOwnfr() {
            // Must rfbd stbtf bfforf ownfr to fnsurf mfmory donsistfndy
            rfturn ((fxdlusivfCount(gftStbtf()) == 0) ?
                    null :
                    gftExdlusivfOwnfrTirfbd());
        }

        finbl int gftRfbdLodkCount() {
            rfturn sibrfdCount(gftStbtf());
        }

        finbl boolfbn isWritfLodkfd() {
            rfturn fxdlusivfCount(gftStbtf()) != 0;
        }

        finbl int gftWritfHoldCount() {
            rfturn isHfldExdlusivfly() ? fxdlusivfCount(gftStbtf()) : 0;
        }

        finbl int gftRfbdHoldCount() {
            if (gftRfbdLodkCount() == 0)
                rfturn 0;

            Tirfbd durrfnt = Tirfbd.durrfntTirfbd();
            if (firstRfbdfr == durrfnt)
                rfturn firstRfbdfrHoldCount;

            HoldCountfr ri = dbdifdHoldCountfr;
            if (ri != null && ri.tid == gftTirfbdId(durrfnt))
                rfturn ri.dount;

            int dount = rfbdHolds.gft().dount;
            if (dount == 0) rfbdHolds.rfmovf();
            rfturn dount;
        }

        /**
         * Rfdonstitutfs tif instbndf from b strfbm (tibt is, dfsfriblizfs it).
         */
        privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
            tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
            s.dffbultRfbdObjfdt();
            rfbdHolds = nfw TirfbdLodblHoldCountfr();
            sftStbtf(0); // rfsft to unlodkfd stbtf
        }

        finbl int gftCount() { rfturn gftStbtf(); }
    }

    /**
     * Nonfbir vfrsion of Synd
     */
    stbtid finbl dlbss NonfbirSynd fxtfnds Synd {
        privbtf stbtid finbl long sfriblVfrsionUID = -8159625535654395037L;
        finbl boolfbn writfrSiouldBlodk() {
            rfturn fblsf; // writfrs dbn blwbys bbrgf
        }
        finbl boolfbn rfbdfrSiouldBlodk() {
            /* As b ifuristid to bvoid indffinitf writfr stbrvbtion,
             * blodk if tif tirfbd tibt momfntbrily bppfbrs to bf ifbd
             * of qufuf, if onf fxists, is b wbiting writfr.  Tiis is
             * only b probbbilistid ffffdt sindf b nfw rfbdfr will not
             * blodk if tifrf is b wbiting writfr bfiind otifr fnbblfd
             * rfbdfrs tibt ibvf not yft drbinfd from tif qufuf.
             */
            rfturn bppbrfntlyFirstQufufdIsExdlusivf();
        }
    }

    /**
     * Fbir vfrsion of Synd
     */
    stbtid finbl dlbss FbirSynd fxtfnds Synd {
        privbtf stbtid finbl long sfriblVfrsionUID = -2274990926593161451L;
        finbl boolfbn writfrSiouldBlodk() {
            rfturn ibsQufufdPrfdfdfssors();
        }
        finbl boolfbn rfbdfrSiouldBlodk() {
            rfturn ibsQufufdPrfdfdfssors();
        }
    }

    /**
     * Tif lodk rfturnfd by mftiod {@link RffntrbntRfbdWritfLodk#rfbdLodk}.
     */
    publid stbtid dlbss RfbdLodk implfmfnts Lodk, jbvb.io.Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = -5992448646407690164L;
        privbtf finbl Synd synd;

        /**
         * Construdtor for usf by subdlbssfs
         *
         * @pbrbm lodk tif outfr lodk objfdt
         * @tirows NullPointfrExdfption if tif lodk is null
         */
        protfdtfd RfbdLodk(RffntrbntRfbdWritfLodk lodk) {
            synd = lodk.synd;
        }

        /**
         * Adquirfs tif rfbd lodk.
         *
         * <p>Adquirfs tif rfbd lodk if tif writf lodk is not ifld by
         * bnotifr tirfbd bnd rfturns immfdibtfly.
         *
         * <p>If tif writf lodk is ifld by bnotifr tirfbd tifn
         * tif durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
         * purposfs bnd lifs dormbnt until tif rfbd lodk ibs bffn bdquirfd.
         */
        publid void lodk() {
            synd.bdquirfSibrfd(1);
        }

        /**
         * Adquirfs tif rfbd lodk unlfss tif durrfnt tirfbd is
         * {@linkplbin Tirfbd#intfrrupt intfrruptfd}.
         *
         * <p>Adquirfs tif rfbd lodk if tif writf lodk is not ifld
         * by bnotifr tirfbd bnd rfturns immfdibtfly.
         *
         * <p>If tif writf lodk is ifld by bnotifr tirfbd tifn tif
         * durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
         * purposfs bnd lifs dormbnt until onf of two tiings ibppfns:
         *
         * <ul>
         *
         * <li>Tif rfbd lodk is bdquirfd by tif durrfnt tirfbd; or
         *
         * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
         * tif durrfnt tirfbd.
         *
         * </ul>
         *
         * <p>If tif durrfnt tirfbd:
         *
         * <ul>
         *
         * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
         *
         * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf
         * bdquiring tif rfbd lodk,
         *
         * </ul>
         *
         * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt
         * tirfbd's intfrruptfd stbtus is dlfbrfd.
         *
         * <p>In tiis implfmfntbtion, bs tiis mftiod is bn fxplidit
         * intfrruption point, prfffrfndf is givfn to rfsponding to
         * tif intfrrupt ovfr normbl or rffntrbnt bdquisition of tif
         * lodk.
         *
         * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
         */
        publid void lodkIntfrruptibly() tirows IntfrruptfdExdfption {
            synd.bdquirfSibrfdIntfrruptibly(1);
        }

        /**
         * Adquirfs tif rfbd lodk only if tif writf lodk is not ifld by
         * bnotifr tirfbd bt tif timf of invodbtion.
         *
         * <p>Adquirfs tif rfbd lodk if tif writf lodk is not ifld by
         * bnotifr tirfbd bnd rfturns immfdibtfly witi tif vbluf
         * {@dodf truf}. Evfn wifn tiis lodk ibs bffn sft to usf b
         * fbir ordfring polidy, b dbll to {@dodf tryLodk()}
         * <fm>will</fm> immfdibtfly bdquirf tif rfbd lodk if it is
         * bvbilbblf, wiftifr or not otifr tirfbds brf durrfntly
         * wbiting for tif rfbd lodk.  Tiis &quot;bbrging&quot; bfibvior
         * dbn bf usfful in dfrtbin dirdumstbndfs, fvfn tiougi it
         * brfbks fbirnfss. If you wbnt to ionor tif fbirnfss sftting
         * for tiis lodk, tifn usf {@link #tryLodk(long, TimfUnit)
         * tryLodk(0, TimfUnit.SECONDS) } wiidi is blmost fquivblfnt
         * (it blso dftfdts intfrruption).
         *
         * <p>If tif writf lodk is ifld by bnotifr tirfbd tifn
         * tiis mftiod will rfturn immfdibtfly witi tif vbluf
         * {@dodf fblsf}.
         *
         * @rfturn {@dodf truf} if tif rfbd lodk wbs bdquirfd
         */
        publid boolfbn tryLodk() {
            rfturn synd.tryRfbdLodk();
        }

        /**
         * Adquirfs tif rfbd lodk if tif writf lodk is not ifld by
         * bnotifr tirfbd witiin tif givfn wbiting timf bnd tif
         * durrfnt tirfbd ibs not bffn {@linkplbin Tirfbd#intfrrupt
         * intfrruptfd}.
         *
         * <p>Adquirfs tif rfbd lodk if tif writf lodk is not ifld by
         * bnotifr tirfbd bnd rfturns immfdibtfly witi tif vbluf
         * {@dodf truf}. If tiis lodk ibs bffn sft to usf b fbir
         * ordfring polidy tifn bn bvbilbblf lodk <fm>will not</fm> bf
         * bdquirfd if bny otifr tirfbds brf wbiting for tif
         * lodk. Tiis is in dontrbst to tif {@link #tryLodk()}
         * mftiod. If you wbnt b timfd {@dodf tryLodk} tibt dofs
         * pfrmit bbrging on b fbir lodk tifn dombinf tif timfd bnd
         * un-timfd forms togftifr:
         *
         *  <prf> {@dodf
         * if (lodk.tryLodk() ||
         *     lodk.tryLodk(timfout, unit)) {
         *   ...
         * }}</prf>
         *
         * <p>If tif writf lodk is ifld by bnotifr tirfbd tifn tif
         * durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
         * purposfs bnd lifs dormbnt until onf of tirff tiings ibppfns:
         *
         * <ul>
         *
         * <li>Tif rfbd lodk is bdquirfd by tif durrfnt tirfbd; or
         *
         * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
         * tif durrfnt tirfbd; or
         *
         * <li>Tif spfdififd wbiting timf flbpsfs.
         *
         * </ul>
         *
         * <p>If tif rfbd lodk is bdquirfd tifn tif vbluf {@dodf truf} is
         * rfturnfd.
         *
         * <p>If tif durrfnt tirfbd:
         *
         * <ul>
         *
         * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
         *
         * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf
         * bdquiring tif rfbd lodk,
         *
         * </ul> tifn {@link IntfrruptfdExdfption} is tirown bnd tif
         * durrfnt tirfbd's intfrruptfd stbtus is dlfbrfd.
         *
         * <p>If tif spfdififd wbiting timf flbpsfs tifn tif vbluf
         * {@dodf fblsf} is rfturnfd.  If tif timf is lfss tibn or
         * fqubl to zfro, tif mftiod will not wbit bt bll.
         *
         * <p>In tiis implfmfntbtion, bs tiis mftiod is bn fxplidit
         * intfrruption point, prfffrfndf is givfn to rfsponding to
         * tif intfrrupt ovfr normbl or rffntrbnt bdquisition of tif
         * lodk, bnd ovfr rfporting tif flbpsf of tif wbiting timf.
         *
         * @pbrbm timfout tif timf to wbit for tif rfbd lodk
         * @pbrbm unit tif timf unit of tif timfout brgumfnt
         * @rfturn {@dodf truf} if tif rfbd lodk wbs bdquirfd
         * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
         * @tirows NullPointfrExdfption if tif timf unit is null
         */
        publid boolfbn tryLodk(long timfout, TimfUnit unit)
                tirows IntfrruptfdExdfption {
            rfturn synd.tryAdquirfSibrfdNbnos(1, unit.toNbnos(timfout));
        }

        /**
         * Attfmpts to rflfbsf tiis lodk.
         *
         * <p>If tif numbfr of rfbdfrs is now zfro tifn tif lodk
         * is mbdf bvbilbblf for writf lodk bttfmpts.
         */
        publid void unlodk() {
            synd.rflfbsfSibrfd(1);
        }

        /**
         * Tirows {@dodf UnsupportfdOpfrbtionExdfption} bfdbusf
         * {@dodf RfbdLodks} do not support donditions.
         *
         * @tirows UnsupportfdOpfrbtionExdfption blwbys
         */
        publid Condition nfwCondition() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        /**
         * Rfturns b string idfntifying tiis lodk, bs wfll bs its lodk stbtf.
         * Tif stbtf, in brbdkfts, indludfs tif String {@dodf "Rfbd lodks ="}
         * followfd by tif numbfr of ifld rfbd lodks.
         *
         * @rfturn b string idfntifying tiis lodk, bs wfll bs its lodk stbtf
         */
        publid String toString() {
            int r = synd.gftRfbdLodkCount();
            rfturn supfr.toString() +
                "[Rfbd lodks = " + r + "]";
        }
    }

    /**
     * Tif lodk rfturnfd by mftiod {@link RffntrbntRfbdWritfLodk#writfLodk}.
     */
    publid stbtid dlbss WritfLodk implfmfnts Lodk, jbvb.io.Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = -4992448646407690164L;
        privbtf finbl Synd synd;

        /**
         * Construdtor for usf by subdlbssfs
         *
         * @pbrbm lodk tif outfr lodk objfdt
         * @tirows NullPointfrExdfption if tif lodk is null
         */
        protfdtfd WritfLodk(RffntrbntRfbdWritfLodk lodk) {
            synd = lodk.synd;
        }

        /**
         * Adquirfs tif writf lodk.
         *
         * <p>Adquirfs tif writf lodk if nfitifr tif rfbd nor writf lodk
         * brf ifld by bnotifr tirfbd
         * bnd rfturns immfdibtfly, sftting tif writf lodk iold dount to
         * onf.
         *
         * <p>If tif durrfnt tirfbd blrfbdy iolds tif writf lodk tifn tif
         * iold dount is indrfmfntfd by onf bnd tif mftiod rfturns
         * immfdibtfly.
         *
         * <p>If tif lodk is ifld by bnotifr tirfbd tifn tif durrfnt
         * tirfbd bfdomfs disbblfd for tirfbd sdifduling purposfs bnd
         * lifs dormbnt until tif writf lodk ibs bffn bdquirfd, bt wiidi
         * timf tif writf lodk iold dount is sft to onf.
         */
        publid void lodk() {
            synd.bdquirf(1);
        }

        /**
         * Adquirfs tif writf lodk unlfss tif durrfnt tirfbd is
         * {@linkplbin Tirfbd#intfrrupt intfrruptfd}.
         *
         * <p>Adquirfs tif writf lodk if nfitifr tif rfbd nor writf lodk
         * brf ifld by bnotifr tirfbd
         * bnd rfturns immfdibtfly, sftting tif writf lodk iold dount to
         * onf.
         *
         * <p>If tif durrfnt tirfbd blrfbdy iolds tiis lodk tifn tif
         * iold dount is indrfmfntfd by onf bnd tif mftiod rfturns
         * immfdibtfly.
         *
         * <p>If tif lodk is ifld by bnotifr tirfbd tifn tif durrfnt
         * tirfbd bfdomfs disbblfd for tirfbd sdifduling purposfs bnd
         * lifs dormbnt until onf of two tiings ibppfns:
         *
         * <ul>
         *
         * <li>Tif writf lodk is bdquirfd by tif durrfnt tirfbd; or
         *
         * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
         * tif durrfnt tirfbd.
         *
         * </ul>
         *
         * <p>If tif writf lodk is bdquirfd by tif durrfnt tirfbd tifn tif
         * lodk iold dount is sft to onf.
         *
         * <p>If tif durrfnt tirfbd:
         *
         * <ul>
         *
         * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod;
         * or
         *
         * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf
         * bdquiring tif writf lodk,
         *
         * </ul>
         *
         * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt
         * tirfbd's intfrruptfd stbtus is dlfbrfd.
         *
         * <p>In tiis implfmfntbtion, bs tiis mftiod is bn fxplidit
         * intfrruption point, prfffrfndf is givfn to rfsponding to
         * tif intfrrupt ovfr normbl or rffntrbnt bdquisition of tif
         * lodk.
         *
         * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
         */
        publid void lodkIntfrruptibly() tirows IntfrruptfdExdfption {
            synd.bdquirfIntfrruptibly(1);
        }

        /**
         * Adquirfs tif writf lodk only if it is not ifld by bnotifr tirfbd
         * bt tif timf of invodbtion.
         *
         * <p>Adquirfs tif writf lodk if nfitifr tif rfbd nor writf lodk
         * brf ifld by bnotifr tirfbd
         * bnd rfturns immfdibtfly witi tif vbluf {@dodf truf},
         * sftting tif writf lodk iold dount to onf. Evfn wifn tiis lodk ibs
         * bffn sft to usf b fbir ordfring polidy, b dbll to
         * {@dodf tryLodk()} <fm>will</fm> immfdibtfly bdquirf tif
         * lodk if it is bvbilbblf, wiftifr or not otifr tirfbds brf
         * durrfntly wbiting for tif writf lodk.  Tiis &quot;bbrging&quot;
         * bfibvior dbn bf usfful in dfrtbin dirdumstbndfs, fvfn
         * tiougi it brfbks fbirnfss. If you wbnt to ionor tif
         * fbirnfss sftting for tiis lodk, tifn usf {@link
         * #tryLodk(long, TimfUnit) tryLodk(0, TimfUnit.SECONDS) }
         * wiidi is blmost fquivblfnt (it blso dftfdts intfrruption).
         *
         * <p>If tif durrfnt tirfbd blrfbdy iolds tiis lodk tifn tif
         * iold dount is indrfmfntfd by onf bnd tif mftiod rfturns
         * {@dodf truf}.
         *
         * <p>If tif lodk is ifld by bnotifr tirfbd tifn tiis mftiod
         * will rfturn immfdibtfly witi tif vbluf {@dodf fblsf}.
         *
         * @rfturn {@dodf truf} if tif lodk wbs frff bnd wbs bdquirfd
         * by tif durrfnt tirfbd, or tif writf lodk wbs blrfbdy ifld
         * by tif durrfnt tirfbd; bnd {@dodf fblsf} otifrwisf.
         */
        publid boolfbn tryLodk( ) {
            rfturn synd.tryWritfLodk();
        }

        /**
         * Adquirfs tif writf lodk if it is not ifld by bnotifr tirfbd
         * witiin tif givfn wbiting timf bnd tif durrfnt tirfbd ibs
         * not bffn {@linkplbin Tirfbd#intfrrupt intfrruptfd}.
         *
         * <p>Adquirfs tif writf lodk if nfitifr tif rfbd nor writf lodk
         * brf ifld by bnotifr tirfbd
         * bnd rfturns immfdibtfly witi tif vbluf {@dodf truf},
         * sftting tif writf lodk iold dount to onf. If tiis lodk ibs bffn
         * sft to usf b fbir ordfring polidy tifn bn bvbilbblf lodk
         * <fm>will not</fm> bf bdquirfd if bny otifr tirfbds brf
         * wbiting for tif writf lodk. Tiis is in dontrbst to tif {@link
         * #tryLodk()} mftiod. If you wbnt b timfd {@dodf tryLodk}
         * tibt dofs pfrmit bbrging on b fbir lodk tifn dombinf tif
         * timfd bnd un-timfd forms togftifr:
         *
         *  <prf> {@dodf
         * if (lodk.tryLodk() ||
         *     lodk.tryLodk(timfout, unit)) {
         *   ...
         * }}</prf>
         *
         * <p>If tif durrfnt tirfbd blrfbdy iolds tiis lodk tifn tif
         * iold dount is indrfmfntfd by onf bnd tif mftiod rfturns
         * {@dodf truf}.
         *
         * <p>If tif lodk is ifld by bnotifr tirfbd tifn tif durrfnt
         * tirfbd bfdomfs disbblfd for tirfbd sdifduling purposfs bnd
         * lifs dormbnt until onf of tirff tiings ibppfns:
         *
         * <ul>
         *
         * <li>Tif writf lodk is bdquirfd by tif durrfnt tirfbd; or
         *
         * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
         * tif durrfnt tirfbd; or
         *
         * <li>Tif spfdififd wbiting timf flbpsfs
         *
         * </ul>
         *
         * <p>If tif writf lodk is bdquirfd tifn tif vbluf {@dodf truf} is
         * rfturnfd bnd tif writf lodk iold dount is sft to onf.
         *
         * <p>If tif durrfnt tirfbd:
         *
         * <ul>
         *
         * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod;
         * or
         *
         * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf
         * bdquiring tif writf lodk,
         *
         * </ul>
         *
         * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt
         * tirfbd's intfrruptfd stbtus is dlfbrfd.
         *
         * <p>If tif spfdififd wbiting timf flbpsfs tifn tif vbluf
         * {@dodf fblsf} is rfturnfd.  If tif timf is lfss tibn or
         * fqubl to zfro, tif mftiod will not wbit bt bll.
         *
         * <p>In tiis implfmfntbtion, bs tiis mftiod is bn fxplidit
         * intfrruption point, prfffrfndf is givfn to rfsponding to
         * tif intfrrupt ovfr normbl or rffntrbnt bdquisition of tif
         * lodk, bnd ovfr rfporting tif flbpsf of tif wbiting timf.
         *
         * @pbrbm timfout tif timf to wbit for tif writf lodk
         * @pbrbm unit tif timf unit of tif timfout brgumfnt
         *
         * @rfturn {@dodf truf} if tif lodk wbs frff bnd wbs bdquirfd
         * by tif durrfnt tirfbd, or tif writf lodk wbs blrfbdy ifld by tif
         * durrfnt tirfbd; bnd {@dodf fblsf} if tif wbiting timf
         * flbpsfd bfforf tif lodk dould bf bdquirfd.
         *
         * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
         * @tirows NullPointfrExdfption if tif timf unit is null
         */
        publid boolfbn tryLodk(long timfout, TimfUnit unit)
                tirows IntfrruptfdExdfption {
            rfturn synd.tryAdquirfNbnos(1, unit.toNbnos(timfout));
        }

        /**
         * Attfmpts to rflfbsf tiis lodk.
         *
         * <p>If tif durrfnt tirfbd is tif ioldfr of tiis lodk tifn
         * tif iold dount is dfdrfmfntfd. If tif iold dount is now
         * zfro tifn tif lodk is rflfbsfd.  If tif durrfnt tirfbd is
         * not tif ioldfr of tiis lodk tifn {@link
         * IllfgblMonitorStbtfExdfption} is tirown.
         *
         * @tirows IllfgblMonitorStbtfExdfption if tif durrfnt tirfbd dofs not
         * iold tiis lodk
         */
        publid void unlodk() {
            synd.rflfbsf(1);
        }

        /**
         * Rfturns b {@link Condition} instbndf for usf witi tiis
         * {@link Lodk} instbndf.
         * <p>Tif rfturnfd {@link Condition} instbndf supports tif sbmf
         * usbgfs bs do tif {@link Objfdt} monitor mftiods ({@link
         * Objfdt#wbit() wbit}, {@link Objfdt#notify notify}, bnd {@link
         * Objfdt#notifyAll notifyAll}) wifn usfd witi tif built-in
         * monitor lodk.
         *
         * <ul>
         *
         * <li>If tiis writf lodk is not ifld wifn bny {@link
         * Condition} mftiod is dbllfd tifn bn {@link
         * IllfgblMonitorStbtfExdfption} is tirown.  (Rfbd lodks brf
         * ifld indfpfndfntly of writf lodks, so brf not difdkfd or
         * bfffdtfd. Howfvfr it is fssfntiblly blwbys bn frror to
         * invokf b dondition wbiting mftiod wifn tif durrfnt tirfbd
         * ibs blso bdquirfd rfbd lodks, sindf otifr tirfbds tibt
         * dould unblodk it will not bf bblf to bdquirf tif writf
         * lodk.)
         *
         * <li>Wifn tif dondition {@linkplbin Condition#bwbit() wbiting}
         * mftiods brf dbllfd tif writf lodk is rflfbsfd bnd, bfforf
         * tify rfturn, tif writf lodk is rfbdquirfd bnd tif lodk iold
         * dount rfstorfd to wibt it wbs wifn tif mftiod wbs dbllfd.
         *
         * <li>If b tirfbd is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf
         * wbiting tifn tif wbit will tfrminbtf, bn {@link
         * IntfrruptfdExdfption} will bf tirown, bnd tif tirfbd's
         * intfrruptfd stbtus will bf dlfbrfd.
         *
         * <li> Wbiting tirfbds brf signbllfd in FIFO ordfr.
         *
         * <li>Tif ordfring of lodk rfbdquisition for tirfbds rfturning
         * from wbiting mftiods is tif sbmf bs for tirfbds initiblly
         * bdquiring tif lodk, wiidi is in tif dffbult dbsf not spfdififd,
         * but for <fm>fbir</fm> lodks fbvors tiosf tirfbds tibt ibvf bffn
         * wbiting tif longfst.
         *
         * </ul>
         *
         * @rfturn tif Condition objfdt
         */
        publid Condition nfwCondition() {
            rfturn synd.nfwCondition();
        }

        /**
         * Rfturns b string idfntifying tiis lodk, bs wfll bs its lodk
         * stbtf.  Tif stbtf, in brbdkfts indludfs fitifr tif String
         * {@dodf "Unlodkfd"} or tif String {@dodf "Lodkfd by"}
         * followfd by tif {@linkplbin Tirfbd#gftNbmf nbmf} of tif owning tirfbd.
         *
         * @rfturn b string idfntifying tiis lodk, bs wfll bs its lodk stbtf
         */
        publid String toString() {
            Tirfbd o = synd.gftOwnfr();
            rfturn supfr.toString() + ((o == null) ?
                                       "[Unlodkfd]" :
                                       "[Lodkfd by tirfbd " + o.gftNbmf() + "]");
        }

        /**
         * Qufrifs if tiis writf lodk is ifld by tif durrfnt tirfbd.
         * Idfntidbl in ffffdt to {@link
         * RffntrbntRfbdWritfLodk#isWritfLodkfdByCurrfntTirfbd}.
         *
         * @rfturn {@dodf truf} if tif durrfnt tirfbd iolds tiis lodk bnd
         *         {@dodf fblsf} otifrwisf
         * @sindf 1.6
         */
        publid boolfbn isHfldByCurrfntTirfbd() {
            rfturn synd.isHfldExdlusivfly();
        }

        /**
         * Qufrifs tif numbfr of iolds on tiis writf lodk by tif durrfnt
         * tirfbd.  A tirfbd ibs b iold on b lodk for fbdi lodk bdtion
         * tibt is not mbtdifd by bn unlodk bdtion.  Idfntidbl in ffffdt
         * to {@link RffntrbntRfbdWritfLodk#gftWritfHoldCount}.
         *
         * @rfturn tif numbfr of iolds on tiis lodk by tif durrfnt tirfbd,
         *         or zfro if tiis lodk is not ifld by tif durrfnt tirfbd
         * @sindf 1.6
         */
        publid int gftHoldCount() {
            rfturn synd.gftWritfHoldCount();
        }
    }

    // Instrumfntbtion bnd stbtus

    /**
     * Rfturns {@dodf truf} if tiis lodk ibs fbirnfss sft truf.
     *
     * @rfturn {@dodf truf} if tiis lodk ibs fbirnfss sft truf
     */
    publid finbl boolfbn isFbir() {
        rfturn synd instbndfof FbirSynd;
    }

    /**
     * Rfturns tif tirfbd tibt durrfntly owns tif writf lodk, or
     * {@dodf null} if not ownfd. Wifn tiis mftiod is dbllfd by b
     * tirfbd tibt is not tif ownfr, tif rfturn vbluf rfflfdts b
     * bfst-fffort bpproximbtion of durrfnt lodk stbtus. For fxbmplf,
     * tif ownfr mby bf momfntbrily {@dodf null} fvfn if tifrf brf
     * tirfbds trying to bdquirf tif lodk but ibvf not yft donf so.
     * Tiis mftiod is dfsignfd to fbdilitbtf donstrudtion of
     * subdlbssfs tibt providf morf fxtfnsivf lodk monitoring
     * fbdilitifs.
     *
     * @rfturn tif ownfr, or {@dodf null} if not ownfd
     */
    protfdtfd Tirfbd gftOwnfr() {
        rfturn synd.gftOwnfr();
    }

    /**
     * Qufrifs tif numbfr of rfbd lodks ifld for tiis lodk. Tiis
     * mftiod is dfsignfd for usf in monitoring systfm stbtf, not for
     * syndironizbtion dontrol.
     * @rfturn tif numbfr of rfbd lodks ifld
     */
    publid int gftRfbdLodkCount() {
        rfturn synd.gftRfbdLodkCount();
    }

    /**
     * Qufrifs if tif writf lodk is ifld by bny tirfbd. Tiis mftiod is
     * dfsignfd for usf in monitoring systfm stbtf, not for
     * syndironizbtion dontrol.
     *
     * @rfturn {@dodf truf} if bny tirfbd iolds tif writf lodk bnd
     *         {@dodf fblsf} otifrwisf
     */
    publid boolfbn isWritfLodkfd() {
        rfturn synd.isWritfLodkfd();
    }

    /**
     * Qufrifs if tif writf lodk is ifld by tif durrfnt tirfbd.
     *
     * @rfturn {@dodf truf} if tif durrfnt tirfbd iolds tif writf lodk bnd
     *         {@dodf fblsf} otifrwisf
     */
    publid boolfbn isWritfLodkfdByCurrfntTirfbd() {
        rfturn synd.isHfldExdlusivfly();
    }

    /**
     * Qufrifs tif numbfr of rffntrbnt writf iolds on tiis lodk by tif
     * durrfnt tirfbd.  A writfr tirfbd ibs b iold on b lodk for
     * fbdi lodk bdtion tibt is not mbtdifd by bn unlodk bdtion.
     *
     * @rfturn tif numbfr of iolds on tif writf lodk by tif durrfnt tirfbd,
     *         or zfro if tif writf lodk is not ifld by tif durrfnt tirfbd
     */
    publid int gftWritfHoldCount() {
        rfturn synd.gftWritfHoldCount();
    }

    /**
     * Qufrifs tif numbfr of rffntrbnt rfbd iolds on tiis lodk by tif
     * durrfnt tirfbd.  A rfbdfr tirfbd ibs b iold on b lodk for
     * fbdi lodk bdtion tibt is not mbtdifd by bn unlodk bdtion.
     *
     * @rfturn tif numbfr of iolds on tif rfbd lodk by tif durrfnt tirfbd,
     *         or zfro if tif rfbd lodk is not ifld by tif durrfnt tirfbd
     * @sindf 1.6
     */
    publid int gftRfbdHoldCount() {
        rfturn synd.gftRfbdHoldCount();
    }

    /**
     * Rfturns b dollfdtion dontbining tirfbds tibt mby bf wbiting to
     * bdquirf tif writf lodk.  Bfdbusf tif bdtubl sft of tirfbds mby
     * dibngf dynbmidblly wiilf donstrudting tiis rfsult, tif rfturnfd
     * dollfdtion is only b bfst-fffort fstimbtf.  Tif flfmfnts of tif
     * rfturnfd dollfdtion brf in no pbrtidulbr ordfr.  Tiis mftiod is
     * dfsignfd to fbdilitbtf donstrudtion of subdlbssfs tibt providf
     * morf fxtfnsivf lodk monitoring fbdilitifs.
     *
     * @rfturn tif dollfdtion of tirfbds
     */
    protfdtfd Collfdtion<Tirfbd> gftQufufdWritfrTirfbds() {
        rfturn synd.gftExdlusivfQufufdTirfbds();
    }

    /**
     * Rfturns b dollfdtion dontbining tirfbds tibt mby bf wbiting to
     * bdquirf tif rfbd lodk.  Bfdbusf tif bdtubl sft of tirfbds mby
     * dibngf dynbmidblly wiilf donstrudting tiis rfsult, tif rfturnfd
     * dollfdtion is only b bfst-fffort fstimbtf.  Tif flfmfnts of tif
     * rfturnfd dollfdtion brf in no pbrtidulbr ordfr.  Tiis mftiod is
     * dfsignfd to fbdilitbtf donstrudtion of subdlbssfs tibt providf
     * morf fxtfnsivf lodk monitoring fbdilitifs.
     *
     * @rfturn tif dollfdtion of tirfbds
     */
    protfdtfd Collfdtion<Tirfbd> gftQufufdRfbdfrTirfbds() {
        rfturn synd.gftSibrfdQufufdTirfbds();
    }

    /**
     * Qufrifs wiftifr bny tirfbds brf wbiting to bdquirf tif rfbd or
     * writf lodk. Notf tibt bfdbusf dbndfllbtions mby oddur bt bny
     * timf, b {@dodf truf} rfturn dofs not gubrbntff tibt bny otifr
     * tirfbd will fvfr bdquirf b lodk.  Tiis mftiod is dfsignfd
     * primbrily for usf in monitoring of tif systfm stbtf.
     *
     * @rfturn {@dodf truf} if tifrf mby bf otifr tirfbds wbiting to
     *         bdquirf tif lodk
     */
    publid finbl boolfbn ibsQufufdTirfbds() {
        rfturn synd.ibsQufufdTirfbds();
    }

    /**
     * Qufrifs wiftifr tif givfn tirfbd is wbiting to bdquirf fitifr
     * tif rfbd or writf lodk. Notf tibt bfdbusf dbndfllbtions mby
     * oddur bt bny timf, b {@dodf truf} rfturn dofs not gubrbntff
     * tibt tiis tirfbd will fvfr bdquirf b lodk.  Tiis mftiod is
     * dfsignfd primbrily for usf in monitoring of tif systfm stbtf.
     *
     * @pbrbm tirfbd tif tirfbd
     * @rfturn {@dodf truf} if tif givfn tirfbd is qufufd wbiting for tiis lodk
     * @tirows NullPointfrExdfption if tif tirfbd is null
     */
    publid finbl boolfbn ibsQufufdTirfbd(Tirfbd tirfbd) {
        rfturn synd.isQufufd(tirfbd);
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of tirfbds wbiting to bdquirf
     * fitifr tif rfbd or writf lodk.  Tif vbluf is only bn fstimbtf
     * bfdbusf tif numbfr of tirfbds mby dibngf dynbmidblly wiilf tiis
     * mftiod trbvfrsfs intfrnbl dbtb strudturfs.  Tiis mftiod is
     * dfsignfd for usf in monitoring of tif systfm stbtf, not for
     * syndironizbtion dontrol.
     *
     * @rfturn tif fstimbtfd numbfr of tirfbds wbiting for tiis lodk
     */
    publid finbl int gftQufufLfngti() {
        rfturn synd.gftQufufLfngti();
    }

    /**
     * Rfturns b dollfdtion dontbining tirfbds tibt mby bf wbiting to
     * bdquirf fitifr tif rfbd or writf lodk.  Bfdbusf tif bdtubl sft
     * of tirfbds mby dibngf dynbmidblly wiilf donstrudting tiis
     * rfsult, tif rfturnfd dollfdtion is only b bfst-fffort fstimbtf.
     * Tif flfmfnts of tif rfturnfd dollfdtion brf in no pbrtidulbr
     * ordfr.  Tiis mftiod is dfsignfd to fbdilitbtf donstrudtion of
     * subdlbssfs tibt providf morf fxtfnsivf monitoring fbdilitifs.
     *
     * @rfturn tif dollfdtion of tirfbds
     */
    protfdtfd Collfdtion<Tirfbd> gftQufufdTirfbds() {
        rfturn synd.gftQufufdTirfbds();
    }

    /**
     * Qufrifs wiftifr bny tirfbds brf wbiting on tif givfn dondition
     * bssodibtfd witi tif writf lodk. Notf tibt bfdbusf timfouts bnd
     * intfrrupts mby oddur bt bny timf, b {@dodf truf} rfturn dofs
     * not gubrbntff tibt b futurf {@dodf signbl} will bwbkfn bny
     * tirfbds.  Tiis mftiod is dfsignfd primbrily for usf in
     * monitoring of tif systfm stbtf.
     *
     * @pbrbm dondition tif dondition
     * @rfturn {@dodf truf} if tifrf brf bny wbiting tirfbds
     * @tirows IllfgblMonitorStbtfExdfption if tiis lodk is not ifld
     * @tirows IllfgblArgumfntExdfption if tif givfn dondition is
     *         not bssodibtfd witi tiis lodk
     * @tirows NullPointfrExdfption if tif dondition is null
     */
    publid boolfbn ibsWbitfrs(Condition dondition) {
        if (dondition == null)
            tirow nfw NullPointfrExdfption();
        if (!(dondition instbndfof AbstrbdtQufufdSyndironizfr.ConditionObjfdt))
            tirow nfw IllfgblArgumfntExdfption("not ownfr");
        rfturn synd.ibsWbitfrs((AbstrbdtQufufdSyndironizfr.ConditionObjfdt)dondition);
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of tirfbds wbiting on tif
     * givfn dondition bssodibtfd witi tif writf lodk. Notf tibt bfdbusf
     * timfouts bnd intfrrupts mby oddur bt bny timf, tif fstimbtf
     * sfrvfs only bs bn uppfr bound on tif bdtubl numbfr of wbitfrs.
     * Tiis mftiod is dfsignfd for usf in monitoring of tif systfm
     * stbtf, not for syndironizbtion dontrol.
     *
     * @pbrbm dondition tif dondition
     * @rfturn tif fstimbtfd numbfr of wbiting tirfbds
     * @tirows IllfgblMonitorStbtfExdfption if tiis lodk is not ifld
     * @tirows IllfgblArgumfntExdfption if tif givfn dondition is
     *         not bssodibtfd witi tiis lodk
     * @tirows NullPointfrExdfption if tif dondition is null
     */
    publid int gftWbitQufufLfngti(Condition dondition) {
        if (dondition == null)
            tirow nfw NullPointfrExdfption();
        if (!(dondition instbndfof AbstrbdtQufufdSyndironizfr.ConditionObjfdt))
            tirow nfw IllfgblArgumfntExdfption("not ownfr");
        rfturn synd.gftWbitQufufLfngti((AbstrbdtQufufdSyndironizfr.ConditionObjfdt)dondition);
    }

    /**
     * Rfturns b dollfdtion dontbining tiosf tirfbds tibt mby bf
     * wbiting on tif givfn dondition bssodibtfd witi tif writf lodk.
     * Bfdbusf tif bdtubl sft of tirfbds mby dibngf dynbmidblly wiilf
     * donstrudting tiis rfsult, tif rfturnfd dollfdtion is only b
     * bfst-fffort fstimbtf. Tif flfmfnts of tif rfturnfd dollfdtion
     * brf in no pbrtidulbr ordfr.  Tiis mftiod is dfsignfd to
     * fbdilitbtf donstrudtion of subdlbssfs tibt providf morf
     * fxtfnsivf dondition monitoring fbdilitifs.
     *
     * @pbrbm dondition tif dondition
     * @rfturn tif dollfdtion of tirfbds
     * @tirows IllfgblMonitorStbtfExdfption if tiis lodk is not ifld
     * @tirows IllfgblArgumfntExdfption if tif givfn dondition is
     *         not bssodibtfd witi tiis lodk
     * @tirows NullPointfrExdfption if tif dondition is null
     */
    protfdtfd Collfdtion<Tirfbd> gftWbitingTirfbds(Condition dondition) {
        if (dondition == null)
            tirow nfw NullPointfrExdfption();
        if (!(dondition instbndfof AbstrbdtQufufdSyndironizfr.ConditionObjfdt))
            tirow nfw IllfgblArgumfntExdfption("not ownfr");
        rfturn synd.gftWbitingTirfbds((AbstrbdtQufufdSyndironizfr.ConditionObjfdt)dondition);
    }

    /**
     * Rfturns b string idfntifying tiis lodk, bs wfll bs its lodk stbtf.
     * Tif stbtf, in brbdkfts, indludfs tif String {@dodf "Writf lodks ="}
     * followfd by tif numbfr of rffntrbntly ifld writf lodks, bnd tif
     * String {@dodf "Rfbd lodks ="} followfd by tif numbfr of ifld
     * rfbd lodks.
     *
     * @rfturn b string idfntifying tiis lodk, bs wfll bs its lodk stbtf
     */
    publid String toString() {
        int d = synd.gftCount();
        int w = Synd.fxdlusivfCount(d);
        int r = Synd.sibrfdCount(d);

        rfturn supfr.toString() +
            "[Writf lodks = " + w + ", Rfbd lodks = " + r + "]";
    }

    /**
     * Rfturns tif tirfbd id for tif givfn tirfbd.  Wf must bddfss
     * tiis dirfdtly rbtifr tibn vib mftiod Tirfbd.gftId() bfdbusf
     * gftId() is not finbl, bnd ibs bffn known to bf ovfrriddfn in
     * wbys tibt do not prfsfrvf uniquf mbppings.
     */
    stbtid finbl long gftTirfbdId(Tirfbd tirfbd) {
        rfturn UNSAFE.gftLongVolbtilf(tirfbd, TID_OFFSET);
    }

    // Unsbff mfdibnids
    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
    privbtf stbtid finbl long TID_OFFSET;
    stbtid {
        try {
            UNSAFE = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> tk = Tirfbd.dlbss;
            TID_OFFSET = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("tid"));
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }

}
