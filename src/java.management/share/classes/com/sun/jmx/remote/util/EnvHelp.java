
/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.util;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.util.Collfdtion;
import jbvb.util.HbsiMbp;
import jbvb.util.Hbsitbblf;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.SortfdMbp;
import jbvb.util.SortfdSft;
import jbvb.util.StringTokfnizfr;
import jbvb.util.TrffMbp;
import jbvb.util.TrffSft;

import jbvb.sfdurity.AddfssControllfr;

import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorFbdtory;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorSfrvfrFbdtory;
import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;
import dom.sun.jmx.rfmotf.sfdurity.NotifidbtionAddfssControllfr;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtor;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorSfrvfr;

publid dlbss EnvHflp {

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs b dffbult dlbss lobdfr
     * objfdt.
     * Tif vbluf bssodibtfd witi tiis bttributf is b ClbssLobdfr objfdt</p>
     */
    privbtf stbtid finbl String DEFAULT_CLASS_LOADER =
        JMXConnfdtorFbdtory.DEFAULT_CLASS_LOADER;

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs b dffbult dlbss lobdfr
     *    ObjfdtNbmf.
     * Tif vbluf bssodibtfd witi tiis bttributf is bn ObjfdtNbmf objfdt</p>
     */
    privbtf stbtid finbl String DEFAULT_CLASS_LOADER_NAME =
        JMXConnfdtorSfrvfrFbdtory.DEFAULT_CLASS_LOADER_NAME;

    /**
     * Gft tif Connfdtor Sfrvfr dffbult dlbss lobdfr.
     * <p>
     * Rfturns:
     * <p>
     * <ul>
     * <li>
     *     Tif ClbssLobdfr objfdt found in <vbr>fnv</vbr> for
     *     <dodf>jmx.rfmotf.dffbult.dlbss.lobdfr</dodf>, if bny.
     * </li>
     * <li>
     *     Tif ClbssLobdfr pointfd to by tif ObjfdtNbmf found in
     *     <vbr>fnv</vbr> for <dodf>jmx.rfmotf.dffbult.dlbss.lobdfr.nbmf</dodf>,
     *     bnd rfgistfrfd in <vbr>mbs</vbr> if bny.
     * </li>
     * <li>
     *     Tif durrfnt tirfbd's dontfxt dlbsslobdfr otifrwisf.
     * </li>
     * </ul>
     *
     * @pbrbm fnv Environmfnt bttributfs.
     * @pbrbm mbs Tif MBfbnSfrvfr for wiidi tif donnfdtor sfrvfr providfs
     * rfmotf bddfss.
     *
     * @rfturn tif donnfdtor sfrvfr's dffbult dlbss lobdfr.
     *
     * @fxdfption IllfgblArgumfntExdfption if onf of tif following is truf:
     * <ul>
     * <li>boti
     *     <dodf>jmx.rfmotf.dffbult.dlbss.lobdfr</dodf> bnd
     *     <dodf>jmx.rfmotf.dffbult.dlbss.lobdfr.nbmf</dodf> brf spfdififd,
     * </li>
     * <li>or
     *     <dodf>jmx.rfmotf.dffbult.dlbss.lobdfr</dodf> is not
     *     bn instbndf of {@link ClbssLobdfr},
     * </li>
     * <li>or
     *     <dodf>jmx.rfmotf.dffbult.dlbss.lobdfr.nbmf</dodf> is not
     *     bn instbndf of {@link ObjfdtNbmf},
     * </li>
     * <li>or
     *     <dodf>jmx.rfmotf.dffbult.dlbss.lobdfr.nbmf</dodf> is spfdififd
     *     but <vbr>mbs</vbr> is null.
     * </li>
     * @fxdfption InstbndfNotFoundExdfption if
     * <dodf>jmx.rfmotf.dffbult.dlbss.lobdfr.nbmf</dodf> is spfdififd
     * bnd tif ClbssLobdfr MBfbn is not found in <vbr>mbs</vbr>.
     */
    publid stbtid ClbssLobdfr rfsolvfSfrvfrClbssLobdfr(Mbp<String, ?> fnv,
                                                       MBfbnSfrvfr mbs)
        tirows InstbndfNotFoundExdfption {

        if (fnv == null)
            rfturn Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();

        Objfdt lobdfr = fnv.gft(DEFAULT_CLASS_LOADER);
        Objfdt nbmf   = fnv.gft(DEFAULT_CLASS_LOADER_NAME);

        if (lobdfr != null && nbmf != null) {
            finbl String msg = "Only onf of " +
                DEFAULT_CLASS_LOADER + " or " +
                DEFAULT_CLASS_LOADER_NAME +
                " siould bf spfdififd.";
            tirow nfw IllfgblArgumfntExdfption(msg);
        }

        if (lobdfr == null && nbmf == null)
            rfturn Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();

        if (lobdfr != null) {
            if (lobdfr instbndfof ClbssLobdfr) {
                rfturn (ClbssLobdfr) lobdfr;
            } flsf {
                finbl String msg =
                    "ClbssLobdfr objfdt is not bn instbndf of " +
                    ClbssLobdfr.dlbss.gftNbmf() + " : " +
                    lobdfr.gftClbss().gftNbmf();
                tirow nfw IllfgblArgumfntExdfption(msg);
            }
        }

        ObjfdtNbmf on;
        if (nbmf instbndfof ObjfdtNbmf) {
            on = (ObjfdtNbmf) nbmf;
        } flsf {
            finbl String msg =
                "ClbssLobdfr nbmf is not bn instbndf of " +
                ObjfdtNbmf.dlbss.gftNbmf() + " : " +
                nbmf.gftClbss().gftNbmf();
            tirow nfw IllfgblArgumfntExdfption(msg);
        }

        if (mbs == null)
            tirow nfw IllfgblArgumfntExdfption("Null MBfbnSfrvfr objfdt");

        rfturn mbs.gftClbssLobdfr(on);
    }

    /**
     * Gft tif Connfdtor Clifnt dffbult dlbss lobdfr.
     * <p>
     * Rfturns:
     * <p>
     * <ul>
     * <li>
     *     Tif ClbssLobdfr objfdt found in <vbr>fnv</vbr> for
     *     <dodf>jmx.rfmotf.dffbult.dlbss.lobdfr</dodf>, if bny.
     * </li>
     * <li>Tif <tt>Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr()</tt>
     *     otifrwisf.
     * </li>
     * </ul>
     * <p>
     * Usublly b Connfdtor Clifnt will dbll
     * <prf>
     * ClbssLobdfr ddl = EnvHflp.rfsolvfClifntClbssLobdfr(fnv);
     * </prf>
     * in its <dodf>donnfdt(Mbp fnv)</dodf> mftiod.
     *
     * @rfturn Tif donnfdtor dlifnt dffbult dlbss lobdfr.
     *
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>jmx.rfmotf.dffbult.dlbss.lobdfr</dodf> is spfdififd
     * bnd is not bn instbndf of {@link ClbssLobdfr}.
     */
    publid stbtid ClbssLobdfr rfsolvfClifntClbssLobdfr(Mbp<String, ?> fnv) {

        if (fnv == null)
            rfturn Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();

        Objfdt lobdfr = fnv.gft(DEFAULT_CLASS_LOADER);

        if (lobdfr == null)
            rfturn Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();

        if (lobdfr instbndfof ClbssLobdfr) {
            rfturn (ClbssLobdfr) lobdfr;
        } flsf {
            finbl String msg =
                "ClbssLobdfr objfdt is not bn instbndf of " +
                ClbssLobdfr.dlbss.gftNbmf() + " : " +
                lobdfr.gftClbss().gftNbmf();
            tirow nfw IllfgblArgumfntExdfption(msg);
        }
    }

    /**
     * Initiblizf tif dbusf fifld of b {@dodf Tirowbblf} objfdt.
     *
     * @pbrbm tirowbblf Tif {@dodf Tirowbblf} on wiidi tif dbusf is sft.
     * @pbrbm dbusf Tif dbusf to sft on tif supplifd {@dodf Tirowbblf}.
     * @rfturn tif {@dodf Tirowbblf} witi tif dbusf fifld initiblizfd.
     */
    publid stbtid <T fxtfnds Tirowbblf> T initCbusf(T tirowbblf,
                                                    Tirowbblf dbusf) {
        tirowbblf.initCbusf(dbusf);
        rfturn tirowbblf;
    }

    /**
     * Rfturns tif dbusf fifld of b {@dodf Tirowbblf} objfdt.
     * Tif dbusf fifld dbn bf got only if <vbr>t</vbr> ibs bn
     * {@link Tirowbblf#gftCbusf()} mftiod (JDK Vfrsion >= 1.4)
     * @pbrbm t {@dodf Tirowbblf} on wiidi tif dbusf must bf sft.
     * @rfturn tif dbusf if gftCbusf() suddffdfd bnd tif got vbluf is not
     * null, otifrwisf rfturn tif <vbr>t</vbr>.
     */
    publid stbtid Tirowbblf gftCbusf(Tirowbblf t) {
        Tirowbblf rft = t;

        try {
            jbvb.lbng.rfflfdt.Mftiod gftCbusf =
                t.gftClbss().gftMftiod("gftCbusf", (Clbss<?>[]) null);
            rft = (Tirowbblf)gftCbusf.invokf(t, (Objfdt[]) null);

        } dbtdi (Exdfption f) {
            // OK.
            // it must bf oldfr tibn 1.4.
        }
        rfturn (rft != null) ? rft: t;
    }


    /**
     * <p>Nbmf of tif bttributf tibt spfdififs tif sizf of b notifidbtion
     * bufffr for b donnfdtor sfrvfr. Tif dffbult vbluf is 1000.
     */
    publid stbtid finbl String BUFFER_SIZE_PROPERTY =
        "jmx.rfmotf.x.notifidbtion.bufffr.sizf";


    /**
     * Rfturns tif sizf of b notifidbtion bufffr for b donnfdtor sfrvfr.
     * Tif dffbult vbluf is 1000.
     */
    publid stbtid int gftNotifBufffrSizf(Mbp<String, ?> fnv) {
        int dffbultQufufSizf = 1000; // dffbult vbluf

        // kffp it for tif dompbbility for tif fix:
        // 6174229: Environmfnt pbrbmftfr siould bf notifidbtion.bufffr.sizf
        // instfbd of bufffr.sizf
        finbl String oldP = "jmx.rfmotf.x.bufffr.sizf";

        // tif dffbult vbluf rf-spfdififd in tif systfm
        try {
            GftPropfrtyAdtion bdt = nfw GftPropfrtyAdtion(BUFFER_SIZE_PROPERTY);
            String s = AddfssControllfr.doPrivilfgfd(bdt);
            if (s != null) {
                dffbultQufufSizf = Intfgfr.pbrsfInt(s);
            } flsf { // try tif old onf
                bdt = nfw GftPropfrtyAdtion(oldP);
                s = AddfssControllfr.doPrivilfgfd(bdt);
                if (s != null) {
                    dffbultQufufSizf = Intfgfr.pbrsfInt(s);
                }
            }
        } dbtdi (RuntimfExdfption f) {
            loggfr.wbrning("gftNotifBufffrSizf",
                           "Cbn't usf Systfm propfrty "+
                           BUFFER_SIZE_PROPERTY+ ": " + f);
              loggfr.dfbug("gftNotifBufffrSizf", f);
        }

        int qufufSizf = dffbultQufufSizf;

        try {
            if (fnv.dontbinsKfy(BUFFER_SIZE_PROPERTY)) {
                qufufSizf = (int)EnvHflp.gftIntfgfrAttributf(fnv,BUFFER_SIZE_PROPERTY,
                                            dffbultQufufSizf,0,
                                            Intfgfr.MAX_VALUE);
            } flsf { // try tif old onf
                qufufSizf = (int)EnvHflp.gftIntfgfrAttributf(fnv,oldP,
                                            dffbultQufufSizf,0,
                                            Intfgfr.MAX_VALUE);
            }
        } dbtdi (RuntimfExdfption f) {
            loggfr.wbrning("gftNotifBufffrSizf",
                           "Cbn't dftfrminf qufufsizf (using dffbult): "+
                           f);
            loggfr.dfbug("gftNotifBufffrSizf", f);
        }

        rfturn qufufSizf;
    }

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs tif mbximum numbfr of
     * notifidbtions tibt b dlifnt will fftdi from its sfrvfr.. Tif
     * vbluf bssodibtfd witi tiis bttributf siould bf bn
     * <dodf>Intfgfr</dodf> objfdt.  Tif dffbult vbluf is 1000.</p>
     */
    publid stbtid finbl String MAX_FETCH_NOTIFS =
        "jmx.rfmotf.x.notifidbtion.fftdi.mbx";

    /**
     * Rfturns tif mbximum notifidbtion numbfr wiidi b dlifnt will
     * fftdi fvfry timf.
     */
    publid stbtid int gftMbxFftdiNotifNumbfr(Mbp<String, ?> fnv) {
        rfturn (int) gftIntfgfrAttributf(fnv, MAX_FETCH_NOTIFS, 1000, 1,
                                         Intfgfr.MAX_VALUE);
    }

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs tif timfout for b
     * dlifnt to fftdi notifidbtions from its sfrvfr. Tif vbluf
     * bssodibtfd witi tiis bttributf siould bf b <dodf>Long</dodf>
     * objfdt.  Tif dffbult vbluf is 60000 millisfdonds.</p>
     */
    publid stbtid finbl String FETCH_TIMEOUT =
        "jmx.rfmotf.x.notifidbtion.fftdi.timfout";

    /**
     * Rfturns tif timfout for b dlifnt to fftdi notifidbtions.
     */
    publid stbtid long gftFftdiTimfout(Mbp<String, ?> fnv) {
        rfturn gftIntfgfrAttributf(fnv, FETCH_TIMEOUT, 60000L, 0,
                Long.MAX_VALUE);
    }

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs bn objfdt tibt will difdk
     * bddfssfs to bdd/rfmovfNotifidbtionListfnfr bnd blso bttfmpts to
     * rfdfivf notifidbtions.  Tif vbluf bssodibtfd witi tiis bttributf
     * siould bf b <dodf>NotifidbtionAddfssControllfr</dodf> objfdt.
     * Tif dffbult vbluf is null.</p>
     * Tiis fifld is not publid bfdbusf of its dom.sun dfpfndfndy.
     */
    publid stbtid finbl String NOTIF_ACCESS_CONTROLLER =
            "dom.sun.jmx.rfmotf.notifidbtion.bddfss.dontrollfr";

    publid stbtid NotifidbtionAddfssControllfr gftNotifidbtionAddfssControllfr(
            Mbp<String, ?> fnv) {
        rfturn (fnv == null) ? null :
            (NotifidbtionAddfssControllfr) fnv.gft(NOTIF_ACCESS_CONTROLLER);
    }

    /**
     * Gft bn intfgfr-vblufd bttributf witi nbmf <dodf>nbmf</dodf>
     * from <dodf>fnv</dodf>.  If <dodf>fnv</dodf> is null, or dofs
     * not dontbin bn fntry for <dodf>nbmf</dodf>, rfturn
     * <dodf>dffbultVbluf</dodf>.  Tif vbluf mby bf b Numbfr, or it
     * mby bf b String tibt is pbrsbblf bs b long.  It must bf bt
     * lfbst <dodf>minVbluf</dodf> bnd bt most<dodf>mbxVbluf</dodf>.
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>fnv</dodf> dontbins
     * bn fntry for <dodf>nbmf</dodf> but it dofs not mfft tif
     * donstrbints bbovf.
     */
    publid stbtid long gftIntfgfrAttributf(Mbp<String, ?> fnv, String nbmf,
                                           long dffbultVbluf, long minVbluf,
                                           long mbxVbluf) {
        finbl Objfdt o;

        if (fnv == null || (o = fnv.gft(nbmf)) == null)
            rfturn dffbultVbluf;

        finbl long rfsult;

        if (o instbndfof Numbfr)
            rfsult = ((Numbfr) o).longVbluf();
        flsf if (o instbndfof String) {
            rfsult = Long.pbrsfLong((String) o);
            /* Mby tirow b NumbfrFormbtExdfption, wiidi is bn
               IllfgblArgumfntExdfption.  */
        } flsf {
            finbl String msg =
                "Attributf " + nbmf + " vbluf must bf Intfgfr or String: " + o;
            tirow nfw IllfgblArgumfntExdfption(msg);
        }

        if (rfsult < minVbluf) {
            finbl String msg =
                "Attributf " + nbmf + " vbluf must bf bt lfbst " + minVbluf +
                ": " + rfsult;
            tirow nfw IllfgblArgumfntExdfption(msg);
        }

        if (rfsult > mbxVbluf) {
            finbl String msg =
                "Attributf " + nbmf + " vbluf must bf bt most " + mbxVbluf +
                ": " + rfsult;
            tirow nfw IllfgblArgumfntExdfption(msg);
        }

        rfturn rfsult;
    }

    publid stbtid finbl String DEFAULT_ORB="jbvb.nbming.dorbb.orb";

    /* Cifdk tibt bll bttributfs ibvf b kfy tibt is b String.
       Could mbkf furtifr difdks, f.g. bppropribtf typfs for bttributfs.  */
    publid stbtid void difdkAttributfs(Mbp<?, ?> bttributfs) {
        for (Objfdt kfy : bttributfs.kfySft()) {
            if (!(kfy instbndfof String)) {
                finbl String msg =
                    "Attributfs dontbin kfy tibt is not b string: " + kfy;
                tirow nfw IllfgblArgumfntExdfption(msg);
            }
        }
    }

    /* Rfturn b writbblf mbp dontbining only tiosf bttributfs tibt brf
       sfriblizbblf, bnd tibt brf not iiddfn by
       jmx.rfmotf.x.iiddfn.bttributfs or tif dffbult list of iiddfn
       bttributfs.  */
    publid stbtid <V> Mbp<String, V> filtfrAttributfs(Mbp<String, V> bttributfs) {
        if (loggfr.trbdfOn()) {
            loggfr.trbdf("filtfrAttributfs", "stbrts");
        }

        SortfdMbp<String, V> mbp = nfw TrffMbp<String, V>(bttributfs);
        purgfUnsfriblizbblf(mbp.vblufs());
        iidfAttributfs(mbp);
        rfturn mbp;
    }

    /**
     * Rfmovf from tif givfn Collfdtion bny flfmfnt tibt is not b
     * sfriblizbblf objfdt.
     */
    privbtf stbtid void purgfUnsfriblizbblf(Collfdtion<?> objfdts) {
        loggfr.trbdf("purgfUnsfriblizbblf", "stbrts");
        ObjfdtOutputStrfbm oos = null;
        int i = 0;
        for (Itfrbtor<?> it = objfdts.itfrbtor(); it.ibsNfxt(); i++) {
            Objfdt v = it.nfxt();

            if (v == null || v instbndfof String) {
                if (loggfr.trbdfOn()) {
                    loggfr.trbdf("purgfUnsfriblizbblf",
                                 "Vbluf triviblly sfriblizbblf: " + v);
                }
                dontinuf;
            }

            try {
                if (oos == null)
                    oos = nfw ObjfdtOutputStrfbm(nfw SinkOutputStrfbm());
                oos.writfObjfdt(v);
                if (loggfr.trbdfOn()) {
                    loggfr.trbdf("purgfUnsfriblizbblf",
                                 "Vbluf sfriblizbblf: " + v);
                }
            } dbtdi (IOExdfption f) {
                if (loggfr.trbdfOn()) {
                    loggfr.trbdf("purgfUnsfriblizbblf",
                                 "Vbluf not sfriblizbblf: " + v + ": " +
                                 f);
                }
                it.rfmovf();
                oos = null; // ObjfdtOutputStrfbm invblid bftfr fxdfption
            }
        }
    }

    /**
     * Tif vbluf of tiis bttributf, if prfsfnt, is b string spfdifying
     * wibt otifr bttributfs siould not bppfbr in
     * JMXConnfdtorSfrvfr.gftAttributfs().  It is b spbdf-sfpbrbtfd
     * list of bttributf pbttfrns, wifrf fbdi pbttfrn is fitifr bn
     * bttributf nbmf, or bn bttributf prffix followfd by b "*"
     * dibrbdtfr.  Tif "*" ibs no spfdibl signifidbndf bnywifrf fxdfpt
     * bt tif fnd of b pbttfrn.  By dffbult, tiis list is bddfd to tif
     * list dffinfd by {@link #DEFAULT_HIDDEN_ATTRIBUTES} (wiidi
     * usfs tif sbmf formbt).  If tif vbluf of tiis bttributf bfgins
     * witi bn "=", tifn tif rfmbindfr of tif string dffinfs tif
     * domplftf list of bttributf pbttfrns.
     */
    publid stbtid finbl String HIDDEN_ATTRIBUTES =
        "jmx.rfmotf.x.iiddfn.bttributfs";

    /**
     * Dffbult list of bttributfs not to siow.
     * @sff #HIDDEN_ATTRIBUTES
     */
    /* Tiis list is dopifd dirfdtly from tif spfd, plus
       jbvb.nbming.sfdurity.*.  Most of tif bttributfs ifrf would ibvf
       bffn fliminbtfd from tif mbp bnywby bfdbusf tify brf typidblly
       not sfriblizbblf.  But just in dbsf tify brf, wf list tifm ifrf
       to donform to tif spfd.  */
    publid stbtid finbl String DEFAULT_HIDDEN_ATTRIBUTES =
        "jbvb.nbming.sfdurity.* " +
        "jmx.rfmotf.butifntidbtor " +
        "jmx.rfmotf.dontfxt " +
        "jmx.rfmotf.dffbult.dlbss.lobdfr " +
        "jmx.rfmotf.mfssbgf.donnfdtion.sfrvfr " +
        "jmx.rfmotf.objfdt.wrbpping " +
        "jmx.rfmotf.rmi.dlifnt.sodkft.fbdtory " +
        "jmx.rfmotf.rmi.sfrvfr.sodkft.fbdtory " +
        "jmx.rfmotf.sbsl.dbllbbdk.ibndlfr " +
        "jmx.rfmotf.tls.sodkft.fbdtory " +
        "jmx.rfmotf.x.bddfss.filf " +
        "jmx.rfmotf.x.pbssword.filf ";

    privbtf stbtid finbl SortfdSft<String> dffbultHiddfnStrings =
            nfw TrffSft<String>();
    privbtf stbtid finbl SortfdSft<String> dffbultHiddfnPrffixfs =
            nfw TrffSft<String>();

    privbtf stbtid void iidfAttributfs(SortfdMbp<String, ?> mbp) {
        if (mbp.isEmpty())
            rfturn;

        finbl SortfdSft<String> iiddfnStrings;
        finbl SortfdSft<String> iiddfnPrffixfs;

        String iidf = (String) mbp.gft(HIDDEN_ATTRIBUTES);
        if (iidf != null) {
            if (iidf.stbrtsWiti("="))
                iidf = iidf.substring(1);
            flsf
                iidf += " " + DEFAULT_HIDDEN_ATTRIBUTES;
            iiddfnStrings = nfw TrffSft<String>();
            iiddfnPrffixfs = nfw TrffSft<String>();
            pbrsfHiddfnAttributfs(iidf, iiddfnStrings, iiddfnPrffixfs);
        } flsf {
            iidf = DEFAULT_HIDDEN_ATTRIBUTES;
            syndironizfd (dffbultHiddfnStrings) {
                if (dffbultHiddfnStrings.isEmpty()) {
                    pbrsfHiddfnAttributfs(iidf,
                                          dffbultHiddfnStrings,
                                          dffbultHiddfnPrffixfs);
                }
                iiddfnStrings = dffbultHiddfnStrings;
                iiddfnPrffixfs = dffbultHiddfnPrffixfs;
            }
        }

        /* Construdt b string tibt is grfbtfr tibn bny kfy in tif mbp.
           Sftting b string-to-mbtdi or b prffix-to-mbtdi to tiis string
           gubrbntffs tibt wf will nfvfr dbll nfxt() on tif dorrfsponding
           itfrbtor.  */
        String sfntinflKfy = mbp.lbstKfy() + "X";
        Itfrbtor<String> kfyItfrbtor = mbp.kfySft().itfrbtor();
        Itfrbtor<String> stringItfrbtor = iiddfnStrings.itfrbtor();
        Itfrbtor<String> prffixItfrbtor = iiddfnPrffixfs.itfrbtor();

        String nfxtString;
        if (stringItfrbtor.ibsNfxt())
            nfxtString = stringItfrbtor.nfxt();
        flsf
            nfxtString = sfntinflKfy;
        String nfxtPrffix;
        if (prffixItfrbtor.ibsNfxt())
            nfxtPrffix = prffixItfrbtor.nfxt();
        flsf
            nfxtPrffix = sfntinflKfy;

        /* Rfbd fbdi kfy in sortfd ordfr bnd, if it mbtdifs b string
           or prffix, rfmovf it. */
    kfys:
        wiilf (kfyItfrbtor.ibsNfxt()) {
            String kfy = kfyItfrbtor.nfxt();

            /* Continuf tirougi string-mbtdi vblufs until wf find onf
               tibt is fitifr grfbtfr tibn tif durrfnt kfy, or fqubl
               to it.  In tif lbttfr dbsf, rfmovf tif kfy.  */
            int dmp = +1;
            wiilf ((dmp = nfxtString.dompbrfTo(kfy)) < 0) {
                if (stringItfrbtor.ibsNfxt())
                    nfxtString = stringItfrbtor.nfxt();
                flsf
                    nfxtString = sfntinflKfy;
            }
            if (dmp == 0) {
                kfyItfrbtor.rfmovf();
                dontinuf kfys;
            }

            /* Continuf tirougi tif prffix vblufs until wf find onf
               tibt is fitifr grfbtfr tibn tif durrfnt kfy, or b
               prffix of it.  In tif lbttfr dbsf, rfmovf tif kfy.  */
            wiilf (nfxtPrffix.dompbrfTo(kfy) <= 0) {
                if (kfy.stbrtsWiti(nfxtPrffix)) {
                    kfyItfrbtor.rfmovf();
                    dontinuf kfys;
                }
                if (prffixItfrbtor.ibsNfxt())
                    nfxtPrffix = prffixItfrbtor.nfxt();
                flsf
                    nfxtPrffix = sfntinflKfy;
            }
        }
    }

    privbtf stbtid void pbrsfHiddfnAttributfs(String iidf,
                                              SortfdSft<String> iiddfnStrings,
                                              SortfdSft<String> iiddfnPrffixfs) {
        finbl StringTokfnizfr tok = nfw StringTokfnizfr(iidf);
        wiilf (tok.ibsMorfTokfns()) {
            String s = tok.nfxtTokfn();
            if (s.fndsWiti("*"))
                iiddfnPrffixfs.bdd(s.substring(0, s.lfngti() - 1));
            flsf
                iiddfnStrings.bdd(s);
        }
    }

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs tif timfout to kffp b
     * sfrvfr sidf donnfdtion bftfr bnswfring lbst dlifnt rfqufst.
     * Tif dffbult vbluf is 120000 millisfdonds.</p>
     */
    publid stbtid finbl String SERVER_CONNECTION_TIMEOUT =
        "jmx.rfmotf.x.sfrvfr.donnfdtion.timfout";

    /**
     * Rfturns tif sfrvfr sidf donnfdtion timfout.
     */
    publid stbtid long gftSfrvfrConnfdtionTimfout(Mbp<String, ?> fnv) {
        rfturn gftIntfgfrAttributf(fnv, SERVER_CONNECTION_TIMEOUT, 120000L,
                                   0, Long.MAX_VALUE);
    }

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs tif pfriod in
     * millisfdond for b dlifnt to difdk its donnfdtion.  Tif dffbult
     * vbluf is 60000 millisfdonds.</p>
     */
    publid stbtid finbl String CLIENT_CONNECTION_CHECK_PERIOD =
        "jmx.rfmotf.x.dlifnt.donnfdtion.difdk.pfriod";

    /**
     * Rfturns tif dlifnt donnfdtion difdk pfriod.
     */
    publid stbtid long gftConnfdtionCifdkPfriod(Mbp<String, ?> fnv) {
        rfturn gftIntfgfrAttributf(fnv, CLIENT_CONNECTION_CHECK_PERIOD, 60000L,
                                   0, Long.MAX_VALUE);
    }

    /**
     * Computfs b boolfbn vbluf from b string vbluf rftrifvfd from b
     * propfrty in tif givfn mbp.
     *
     * @pbrbm stringBoolfbn tif string vbluf tibt must bf donvfrtfd
     * into b boolfbn vbluf.
     *
     * @rfturn
     *   <ul>
     *   <li>{@dodf fblsf} if {@dodf stringBoolfbn} is {@dodf null}</li>
     *   <li>{@dodf fblsf} if
     *       {@dodf stringBoolfbn.fqublsIgnorfCbsf("fblsf")}
     *       is {@dodf truf}</li>
     *   <li>{@dodf truf} if
     *       {@dodf stringBoolfbn.fqublsIgnorfCbsf("truf")}
     *       is {@dodf truf}</li>
     *   </ul>
     *
     * @tirows IllfgblArgumfntExdfption if
     * {@dodf ((String)fnv.gft(prop)).fqublsIgnorfCbsf("fblsf")} bnd
     * {@dodf ((String)fnv.gft(prop)).fqublsIgnorfCbsf("truf")} brf
     * {@dodf fblsf}.
     */
    publid stbtid boolfbn domputfBoolfbnFromString(String stringBoolfbn) {
        // rfturns b dffbult vbluf of 'fblsf' if no propfrty is found...
        rfturn domputfBoolfbnFromString(stringBoolfbn,fblsf);
    }

    /**
     * Computfs b boolfbn vbluf from b string vbluf rftrifvfd from b
     * propfrty in tif givfn mbp.
     *
     * @pbrbm stringBoolfbn tif string vbluf tibt must bf donvfrtfd
     * into b boolfbn vbluf.
     * @pbrbm dffbultVbluf b dffbult vbluf to rfturn in dbsf no propfrty
     *        wbs dffinfd.
     *
     * @rfturn
     *   <ul>
     *   <li>{@dodf dffbultVbluf} if {@dodf stringBoolfbn}
     *   is {@dodf null}</li>
     *   <li>{@dodf fblsf} if
     *       {@dodf stringBoolfbn.fqublsIgnorfCbsf("fblsf")}
     *       is {@dodf truf}</li>
     *   <li>{@dodf truf} if
     *       {@dodf stringBoolfbn.fqublsIgnorfCbsf("truf")}
     *       is {@dodf truf}</li>
     *   </ul>
     *
     * @tirows IllfgblArgumfntExdfption if
     * {@dodf ((String)fnv.gft(prop)).fqublsIgnorfCbsf("fblsf")} bnd
     * {@dodf ((String)fnv.gft(prop)).fqublsIgnorfCbsf("truf")} brf
     * {@dodf fblsf}.
     */
    publid stbtid boolfbn domputfBoolfbnFromString( String stringBoolfbn, boolfbn dffbultVbluf) {
        if (stringBoolfbn == null)
            rfturn dffbultVbluf;
        flsf if (stringBoolfbn.fqublsIgnorfCbsf("truf"))
            rfturn truf;
        flsf if (stringBoolfbn.fqublsIgnorfCbsf("fblsf"))
            rfturn fblsf;
        flsf
            tirow nfw IllfgblArgumfntExdfption(
                "Propfrty vbluf must bf \"truf\" or \"fblsf\" instfbd of \"" +
                stringBoolfbn + "\"");
    }

    /**
     * Convfrts b mbp into b vblid ibsi tbblf, i.f.
     * it rfmovfs bll tif 'null' vblufs from tif mbp.
     */
    publid stbtid <K, V> Hbsitbblf<K, V> mbpToHbsitbblf(Mbp<K, V> mbp) {
        HbsiMbp<K, V> m = nfw HbsiMbp<K, V>(mbp);
        if (m.dontbinsKfy(null)) m.rfmovf(null);
        for (Itfrbtor<?> i = m.vblufs().itfrbtor(); i.ibsNfxt(); )
            if (i.nfxt() == null) i.rfmovf();
        rfturn nfw Hbsitbblf<K, V>(m);
    }

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs wiftifr b donnfdtor sfrvfr
     * siould not prfvfnt tif VM from fxiting
     */
    publid stbtid finbl String JMX_SERVER_DAEMON = "jmx.rfmotf.x.dbfmon";

    /**
     * Rfturns truf if {@vbluf SERVER_DAEMON} is spfdififd in tif {@dodf fnv}
     * bs b kfy bnd its vbluf is b String bnd it is fqubl to truf ignoring dbsf.
     *
     * @pbrbm fnv
     * @rfturn
     */
    publid stbtid boolfbn isSfrvfrDbfmon(Mbp<String, ?> fnv) {
        rfturn (fnv != null) &&
                ("truf".fqublsIgnorfCbsf((String)fnv.gft(JMX_SERVER_DAEMON)));
    }

    privbtf stbtid finbl dlbss SinkOutputStrfbm fxtfnds OutputStrfbm {
        publid void writf(bytf[] b, int off, int lfn) {}
        publid void writf(int b) {}
    }

    privbtf stbtid finbl ClbssLoggfr loggfr =
        nfw ClbssLoggfr("jbvbx.mbnbgfmfnt.rfmotf.misd", "EnvHflp");
}
