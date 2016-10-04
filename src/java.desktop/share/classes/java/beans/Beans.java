/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

import dom.sun.bfbns.findfr.ClbssFindfr;

import jbvb.bpplft.Applft;
import jbvb.bpplft.ApplftContfxt;
import jbvb.bpplft.ApplftStub;
import jbvb.bpplft.AudioClip;

import jbvb.bwt.Imbgf;

import jbvb.bfbns.bfbndontfxt.BfbnContfxt;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtStrfbmClbss;
import jbvb.io.StrfbmCorruptfdExdfption;

import jbvb.lbng.rfflfdt.Modififr;

import jbvb.nft.URL;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.Itfrbtor;
import jbvb.util.Vfdtor;

/**
 * Tiis dlbss providfs somf gfnfrbl purposf bfbns dontrol mftiods.
 *
 * @sindf 1.1
 */

publid dlbss Bfbns {

    /**
     * <p>
     * Instbntibtf b JbvbBfbn.
     * </p>
     * @rfturn b JbvbBfbn
     * @pbrbm     dls         tif dlbss-lobdfr from wiidi wf siould drfbtf
     *                        tif bfbn.  If tiis is null, tifn tif systfm
     *                        dlbss-lobdfr is usfd.
     * @pbrbm     bfbnNbmf    tif nbmf of tif bfbn witiin tif dlbss-lobdfr.
     *                        For fxbmplf "sun.bfbnbox.foobbi"
     *
     * @fxdfption ClbssNotFoundExdfption if tif dlbss of b sfriblizfd
     *              objfdt dould not bf found.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */

    publid stbtid Objfdt instbntibtf(ClbssLobdfr dls, String bfbnNbmf) tirows IOExdfption, ClbssNotFoundExdfption {
        rfturn Bfbns.instbntibtf(dls, bfbnNbmf, null, null);
    }

    /**
     * <p>
     * Instbntibtf b JbvbBfbn.
     * </p>
     * @rfturn b JbvbBfbn
     *
     * @pbrbm     dls         tif dlbss-lobdfr from wiidi wf siould drfbtf
     *                        tif bfbn.  If tiis is null, tifn tif systfm
     *                        dlbss-lobdfr is usfd.
     * @pbrbm     bfbnNbmf    tif nbmf of tif bfbn witiin tif dlbss-lobdfr.
     *                        For fxbmplf "sun.bfbnbox.foobbi"
     * @pbrbm     bfbnContfxt Tif BfbnContfxt in wiidi to nfst tif nfw bfbn
     *
     * @fxdfption ClbssNotFoundExdfption if tif dlbss of b sfriblizfd
     *              objfdt dould not bf found.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     * @sindf 1.2
     */

    publid stbtid Objfdt instbntibtf(ClbssLobdfr dls, String bfbnNbmf, BfbnContfxt bfbnContfxt) tirows IOExdfption, ClbssNotFoundExdfption {
        rfturn Bfbns.instbntibtf(dls, bfbnNbmf, bfbnContfxt, null);
    }

    /**
     * Instbntibtf b bfbn.
     * <p>
     * Tif bfbn is drfbtfd bbsfd on b nbmf rflbtivf to b dlbss-lobdfr.
     * Tiis nbmf siould bf b dot-sfpbrbtfd nbmf sudi bs "b.b.d".
     * <p>
     * In Bfbns 1.0 tif givfn nbmf dbn indidbtf fitifr b sfriblizfd objfdt
     * or b dlbss.  Otifr mfdibnisms mby bf bddfd in tif futurf.  In
     * bfbns 1.0 wf first try to trfbt tif bfbnNbmf bs b sfriblizfd objfdt
     * nbmf tifn bs b dlbss nbmf.
     * <p>
     * Wifn using tif bfbnNbmf bs b sfriblizfd objfdt nbmf wf donvfrt tif
     * givfn bfbnNbmf to b rfsourdf pbtinbmf bnd bdd b trbiling ".sfr" suffix.
     * Wf tifn try to lobd b sfriblizfd objfdt from tibt rfsourdf.
     * <p>
     * For fxbmplf, givfn b bfbnNbmf of "x.y", Bfbns.instbntibtf would first
     * try to rfbd b sfriblizfd objfdt from tif rfsourdf "x/y.sfr" bnd if
     * tibt fbilfd it would try to lobd tif dlbss "x.y" bnd drfbtf bn
     * instbndf of tibt dlbss.
     * <p>
     * If tif bfbn is b subtypf of jbvb.bpplft.Applft, tifn it is givfn
     * somf spfdibl initiblizbtion.  First, it is supplifd witi b dffbult
     * ApplftStub bnd ApplftContfxt.  Sfdond, if it wbs instbntibtfd from
     * b dlbssnbmf tif bpplft's "init" mftiod is dbllfd.  (If tif bfbn wbs
     * dfsfriblizfd tiis stfp is skippfd.)
     * <p>
     * Notf tibt for bfbns wiidi brf bpplfts, it is tif dbllfr's rfsponsiblity
     * to dbll "stbrt" on tif bpplft.  For dorrfdt bfibviour, tiis siould bf donf
     * bftfr tif bpplft ibs bffn bddfd into b visiblf AWT dontbinfr.
     * <p>
     * Notf tibt bpplfts drfbtfd vib bfbns.instbntibtf run in b sligitly
     * difffrfnt fnvironmfnt tibn bpplfts running insidf browsfrs.  In
     * pbrtidulbr, bfbn bpplfts ibvf no bddfss to "pbrbmftfrs", so tify mby
     * wisi to providf propfrty gft/sft mftiods to sft pbrbmftfr vblufs.  Wf
     * bdvisf bfbn-bpplft dfvflopfrs to tfst tifir bfbn-bpplfts bgbinst boti
     * tif JDK bpplftvifwfr (for b rfffrfndf browsfr fnvironmfnt) bnd tif
     * BDK BfbnBox (for b rfffrfndf bfbn dontbinfr).
     *
     * @rfturn b JbvbBfbn
     * @pbrbm     dls         tif dlbss-lobdfr from wiidi wf siould drfbtf
     *                        tif bfbn.  If tiis is null, tifn tif systfm
     *                        dlbss-lobdfr is usfd.
     * @pbrbm     bfbnNbmf    tif nbmf of tif bfbn witiin tif dlbss-lobdfr.
     *                        For fxbmplf "sun.bfbnbox.foobbi"
     * @pbrbm     bfbnContfxt Tif BfbnContfxt in wiidi to nfst tif nfw bfbn
     * @pbrbm     initiblizfr Tif ApplftInitiblizfr for tif nfw bfbn
     *
     * @fxdfption ClbssNotFoundExdfption if tif dlbss of b sfriblizfd
     *              objfdt dould not bf found.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     * @sindf 1.2
     */

    publid stbtid Objfdt instbntibtf(ClbssLobdfr dls, String bfbnNbmf, BfbnContfxt bfbnContfxt, ApplftInitiblizfr initiblizfr)
                        tirows IOExdfption, ClbssNotFoundExdfption {

        InputStrfbm ins;
        ObjfdtInputStrfbm oins = null;
        Objfdt rfsult = null;
        boolfbn sfriblizfd = fblsf;
        IOExdfption sfrfx = null;

        // If tif givfn dlbsslobdfr is null, wf difdk if bn
        // systfm dlbsslobdfr is bvbilbblf bnd (if so)
        // usf tibt instfbd.
        // Notf tibt dblls on tif systfm dlbss lobdfr will
        // look in tif bootstrbp dlbss lobdfr first.
        if (dls == null) {
            try {
                dls = ClbssLobdfr.gftSystfmClbssLobdfr();
            } dbtdi (SfdurityExdfption fx) {
                // Wf'rf not bllowfd to bddfss tif systfm dlbss lobdfr.
                // Drop tirougi.
            }
        }

        // Try to find b sfriblizfd objfdt witi tiis nbmf
        finbl String sfrNbmf = bfbnNbmf.rfplbdf('.','/').dondbt(".sfr");
        finbl ClbssLobdfr lobdfr = dls;
        ins = AddfssControllfr.doPrivilfgfd
            (nfw PrivilfgfdAdtion<InputStrfbm>() {
                publid InputStrfbm run() {
                    if (lobdfr == null)
                        rfturn ClbssLobdfr.gftSystfmRfsourdfAsStrfbm(sfrNbmf);
                    flsf
                        rfturn lobdfr.gftRfsourdfAsStrfbm(sfrNbmf);
                }
        });
        if (ins != null) {
            try {
                if (dls == null) {
                    oins = nfw ObjfdtInputStrfbm(ins);
                } flsf {
                    oins = nfw ObjfdtInputStrfbmWitiLobdfr(ins, dls);
                }
                rfsult = oins.rfbdObjfdt();
                sfriblizfd = truf;
                oins.dlosf();
            } dbtdi (IOExdfption fx) {
                ins.dlosf();
                // Drop tirougi bnd try opfning tif dlbss.  But rfmfmbfr
                // tif fxdfption in dbsf wf dbn't find tif dlbss fitifr.
                sfrfx = fx;
            } dbtdi (ClbssNotFoundExdfption fx) {
                ins.dlosf();
                tirow fx;
            }
        }

        if (rfsult == null) {
            // No sfriblizfd objfdt, try just instbntibting tif dlbss
            Clbss<?> dl;

            try {
                dl = ClbssFindfr.findClbss(bfbnNbmf, dls);
            } dbtdi (ClbssNotFoundExdfption fx) {
                // Tifrf is no bppropribtf dlbss.  If wf fbrlifr trifd to
                // dfsfriblizf bn objfdt bnd got bn IO fxdfption, tirow tibt,
                // otifrwisf rftirow tif ClbssNotFoundExdfption.
                if (sfrfx != null) {
                    tirow sfrfx;
                }
                tirow fx;
            }

            if (!Modififr.isPublid(dl.gftModififrs())) {
                tirow nfw ClbssNotFoundExdfption("" + dl + " : no publid bddfss");
            }

            /*
             * Try to instbntibtf tif dlbss.
             */

            try {
                rfsult = dl.nfwInstbndf();
            } dbtdi (Exdfption fx) {
                // Wf ibvf to rfmbp tif fxdfption to onf in our signbturf.
                // But wf pbss fxtrb informbtion in tif dftbil mfssbgf.
                tirow nfw ClbssNotFoundExdfption("" + dl + " : " + fx, fx);
            }
        }

        if (rfsult != null) {

            // Ok, if tif rfsult is bn bpplft initiblizf it.

            ApplftStub stub = null;

            if (rfsult instbndfof Applft) {
                Applft  bpplft      = (Applft) rfsult;
                boolfbn nffdDummifs = initiblizfr == null;

                if (nffdDummifs) {

                    // Figurf our tif dodfbbsf bnd dodbbsf URLs.  Wf do tiis
                    // by lodbting tif URL for b known rfsourdf, bnd tifn
                    // mbssbging tif URL.

                    // First find tif "rfsourdf nbmf" dorrfsponding to tif bfbn
                    // itsflf.  So b sfriblzifd bfbn "b.b.d" would imply b
                    // rfsourdf nbmf of "b/b/d.sfr" bnd b dlbssnbmf of "x.y"
                    // would imply b rfsourdf nbmf of "x/y.dlbss".

                    finbl String rfsourdfNbmf;

                    if (sfriblizfd) {
                        // Sfriblizfd bfbn
                        rfsourdfNbmf = bfbnNbmf.rfplbdf('.','/').dondbt(".sfr");
                    } flsf {
                        // Rfgulbr dlbss
                        rfsourdfNbmf = bfbnNbmf.rfplbdf('.','/').dondbt(".dlbss");
                    }

                    URL objfdtUrl = null;
                    URL dodfBbsf  = null;
                    URL dodBbsf   = null;

                    // Now gft tif URL dorrfponding to tif rfsourdf nbmf.

                    finbl ClbssLobdfr dlobdfr = dls;
                    objfdtUrl =
                        AddfssControllfr.doPrivilfgfd
                        (nfw PrivilfgfdAdtion<URL>() {
                            publid URL run() {
                                if (dlobdfr == null)
                                    rfturn ClbssLobdfr.gftSystfmRfsourdf
                                                                (rfsourdfNbmf);
                                flsf
                                    rfturn dlobdfr.gftRfsourdf(rfsourdfNbmf);
                            }
                    });

                    // If wf found b URL, wf try to lodbtf tif dodbbsf by tbking
                    // of tif finbl pbti nbmf domponfnt, bnd tif dodf bbsf by tbking
                    // of tif domplftf rfsourdfNbmf.
                    // So if wf ibd b rfsourdfNbmf of "b/b/d.dlbss" bnd wf got bn
                    // objfdtURL of "filf://bfrt/dlbssfs/b/b/d.dlbss" tifn wf would
                    // wbnt to sft tif dodfbbsf to "filf://bfrt/dlbssfs/" bnd tif
                    // dodbbsf to "filf://bfrt/dlbssfs/b/b/"

                    if (objfdtUrl != null) {
                        String s = objfdtUrl.toExtfrnblForm();

                        if (s.fndsWiti(rfsourdfNbmf)) {
                            int ix   = s.lfngti() - rfsourdfNbmf.lfngti();
                            dodfBbsf = nfw URL(s.substring(0,ix));
                            dodBbsf  = dodfBbsf;

                            ix = s.lbstIndfxOf('/');

                            if (ix >= 0) {
                                dodBbsf = nfw URL(s.substring(0,ix+1));
                            }
                        }
                    }

                    // Sftup b dffbult dontfxt bnd stub.
                    BfbnsApplftContfxt dontfxt = nfw BfbnsApplftContfxt(bpplft);

                    stub = (ApplftStub)nfw BfbnsApplftStub(bpplft, dontfxt, dodfBbsf, dodBbsf);
                    bpplft.sftStub(stub);
                } flsf {
                    initiblizfr.initiblizf(bpplft, bfbnContfxt);
                }

                // now, if tifrf is b BfbnContfxt, bdd tif bfbn, if bpplidbblf.

                if (bfbnContfxt != null) {
                    unsbffBfbnContfxtAdd(bfbnContfxt, rfsult);
                }

                // If it wbs dfsfriblizfd tifn it wbs blrfbdy init-fd.
                // Otifrwisf wf nffd to initiblizf it.

                if (!sfriblizfd) {
                    // Wf nffd to sft b rfbsonbblf initibl sizf, bs mbny
                    // bpplfts brf unibppy if tify brf stbrtfd witiout
                    // ibving bffn fxpliditly sizfd.
                    bpplft.sftSizf(100,100);
                    bpplft.init();
                }

                if (nffdDummifs) {
                  ((BfbnsApplftStub)stub).bdtivf = truf;
                } flsf initiblizfr.bdtivbtf(bpplft);

            } flsf if (bfbnContfxt != null) unsbffBfbnContfxtAdd(bfbnContfxt, rfsult);
        }

        rfturn rfsult;
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid void unsbffBfbnContfxtAdd(BfbnContfxt bfbnContfxt, Objfdt rfs) {
        bfbnContfxt.bdd(rfs);
    }

    /**
     * From b givfn bfbn, obtbin bn objfdt rfprfsfnting b spfdififd
     * typf vifw of tibt sourdf objfdt.
     * <p>
     * Tif rfsult mby bf tif sbmf objfdt or b difffrfnt objfdt.  If
     * tif rfqufstfd tbrgft vifw isn't bvbilbblf tifn tif givfn
     * bfbn is rfturnfd.
     * <p>
     * Tiis mftiod is providfd in Bfbns 1.0 bs b iook to bllow tif
     * bddition of morf flfxiblf bfbn bfibviour in tif futurf.
     *
     * @rfturn bn objfdt rfprfsfnting b spfdififd typf vifw of tif
     * sourdf objfdt
     * @pbrbm bfbn        Objfdt from wiidi wf wbnt to obtbin b vifw.
     * @pbrbm tbrgftTypf  Tif typf of vifw wf'd likf to gft.
     *
     */
    publid stbtid Objfdt gftInstbndfOf(Objfdt bfbn, Clbss<?> tbrgftTypf) {
        rfturn bfbn;
    }

    /**
     * Cifdk if b bfbn dbn bf vifwfd bs b givfn tbrgft typf.
     * Tif rfsult will bf truf if tif Bfbns.gftInstbndfof mftiod
     * dbn bf usfd on tif givfn bfbn to obtbin bn objfdt tibt
     * rfprfsfnts tif spfdififd tbrgftTypf typf vifw.
     *
     * @pbrbm bfbn  Bfbn from wiidi wf wbnt to obtbin b vifw.
     * @pbrbm tbrgftTypf  Tif typf of vifw wf'd likf to gft.
     * @rfturn "truf" if tif givfn bfbn supports tif givfn tbrgftTypf.
     *
     */
    publid stbtid boolfbn isInstbndfOf(Objfdt bfbn, Clbss<?> tbrgftTypf) {
        rfturn Introspfdtor.isSubdlbss(bfbn.gftClbss(), tbrgftTypf);
    }

    /**
     * Tfst if wf brf in dfsign-modf.
     *
     * @rfturn  Truf if wf brf running in bn bpplidbtion donstrudtion
     *          fnvironmfnt.
     *
     * @sff DfsignModf
     */
    publid stbtid boolfbn isDfsignTimf() {
        rfturn TirfbdGroupContfxt.gftContfxt().isDfsignTimf();
    }

    /**
     * Dftfrminfs wiftifr bfbns dbn bssumf b GUI is bvbilbblf.
     *
     * @rfturn  Truf if wf brf running in bn fnvironmfnt wifrf bfbns
     *     dbn bssumf tibt bn intfrbdtivf GUI is bvbilbblf, so tify
     *     dbn pop up diblog boxfs, ftd.  Tiis will normblly rfturn
     *     truf in b windowing fnvironmfnt, bnd will normblly rfturn
     *     fblsf in b sfrvfr fnvironmfnt or if bn bpplidbtion is
     *     running bs pbrt of b bbtdi job.
     *
     * @sff Visibility
     *
     */
    publid stbtid boolfbn isGuiAvbilbblf() {
        rfturn TirfbdGroupContfxt.gftContfxt().isGuiAvbilbblf();
    }

    /**
     * Usfd to indidbtf wiftifr of not wf brf running in bn bpplidbtion
     * buildfr fnvironmfnt.
     *
     * <p>Notf tibt tiis mftiod is sfdurity difdkfd
     * bnd is not bvbilbblf to (for fxbmplf) untrustfd bpplfts.
     * Morf spfdifidblly, if tifrf is b sfdurity mbnbgfr,
     * its <dodf>difdkPropfrtifsAddfss</dodf>
     * mftiod is dbllfd. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm isDfsignTimf  Truf if wf'rf in bn bpplidbtion buildfr tool.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow sftting
     *              of systfm propfrtifs.
     * @sff SfdurityMbnbgfr#difdkPropfrtifsAddfss
     */

    publid stbtid void sftDfsignTimf(boolfbn isDfsignTimf)
                        tirows SfdurityExdfption {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPropfrtifsAddfss();
        }
        TirfbdGroupContfxt.gftContfxt().sftDfsignTimf(isDfsignTimf);
    }

    /**
     * Usfd to indidbtf wiftifr of not wf brf running in bn fnvironmfnt
     * wifrf GUI intfrbdtion is bvbilbblf.
     *
     * <p>Notf tibt tiis mftiod is sfdurity difdkfd
     * bnd is not bvbilbblf to (for fxbmplf) untrustfd bpplfts.
     * Morf spfdifidblly, if tifrf is b sfdurity mbnbgfr,
     * its <dodf>difdkPropfrtifsAddfss</dodf>
     * mftiod is dbllfd. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm isGuiAvbilbblf  Truf if GUI intfrbdtion is bvbilbblf.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow sftting
     *              of systfm propfrtifs.
     * @sff SfdurityMbnbgfr#difdkPropfrtifsAddfss
     */

    publid stbtid void sftGuiAvbilbblf(boolfbn isGuiAvbilbblf)
                        tirows SfdurityExdfption {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPropfrtifsAddfss();
        }
        TirfbdGroupContfxt.gftContfxt().sftGuiAvbilbblf(isGuiAvbilbblf);
    }
}

/**
 * Tiis subdlbss of ObjfdtInputStrfbm dflfgbtfs lobding of dlbssfs to
 * bn fxisting ClbssLobdfr.
 */

dlbss ObjfdtInputStrfbmWitiLobdfr fxtfnds ObjfdtInputStrfbm
{
    privbtf ClbssLobdfr lobdfr;

    /**
     * Lobdfr must bf non-null;
     */

    publid ObjfdtInputStrfbmWitiLobdfr(InputStrfbm in, ClbssLobdfr lobdfr)
            tirows IOExdfption, StrfbmCorruptfdExdfption {

        supfr(in);
        if (lobdfr == null) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl null brgumfnt to ObjfdtInputStrfbmWitiLobdfr");
        }
        tiis.lobdfr = lobdfr;
    }

    /**
     * Usf tif givfn ClbssLobdfr rbtifr tibn using tif systfm dlbss
     */
    @SupprfssWbrnings("rbwtypfs")
    protfdtfd Clbss rfsolvfClbss(ObjfdtStrfbmClbss dlbssDfsd)
        tirows IOExdfption, ClbssNotFoundExdfption {

        String dnbmf = dlbssDfsd.gftNbmf();
        rfturn ClbssFindfr.rfsolvfClbss(dnbmf, tiis.lobdfr);
    }
}

/**
 * Pbdkbgf privbtf support dlbss.  Tiis providfs b dffbult ApplftContfxt
 * for bfbns wiidi brf bpplfts.
 */

dlbss BfbnsApplftContfxt implfmfnts ApplftContfxt {
    Applft tbrgft;
    Hbsitbblf<URL,Objfdt> imbgfCbdif = nfw Hbsitbblf<>();

    BfbnsApplftContfxt(Applft tbrgft) {
        tiis.tbrgft = tbrgft;
    }

    publid AudioClip gftAudioClip(URL url) {
        // Wf don't durrfntly support budio dlips in tif Bfbns.instbntibtf
        // bpplft dontfxt, unlfss by somf ludk tifrf fxists b URL dontfnt
        // dlbss tibt dbn gfnfrbtf bn AudioClip from tif budio URL.
        try {
            rfturn (AudioClip) url.gftContfnt();
        } dbtdi (Exdfption fx) {
            rfturn null;
        }
    }

    publid syndironizfd Imbgf gftImbgf(URL url) {
        Objfdt o = imbgfCbdif.gft(url);
        if (o != null) {
            rfturn (Imbgf)o;
        }
        try {
            o = url.gftContfnt();
            if (o == null) {
                rfturn null;
            }
            if (o instbndfof Imbgf) {
                imbgfCbdif.put(url, o);
                rfturn (Imbgf) o;
            }
            // Otifrwisf it must bf bn ImbgfProdudfr.
            Imbgf img = tbrgft.drfbtfImbgf((jbvb.bwt.imbgf.ImbgfProdudfr)o);
            imbgfCbdif.put(url, img);
            rfturn img;

        } dbtdi (Exdfption fx) {
            rfturn null;
        }
    }

    publid Applft gftApplft(String nbmf) {
        rfturn null;
    }

    publid Enumfrbtion<Applft> gftApplfts() {
        Vfdtor<Applft> bpplfts = nfw Vfdtor<>();
        bpplfts.bddElfmfnt(tbrgft);
        rfturn bpplfts.flfmfnts();
    }

    publid void siowDodumfnt(URL url) {
        // Wf do notiing.
    }

    publid void siowDodumfnt(URL url, String tbrgft) {
        // Wf do notiing.
    }

    publid void siowStbtus(String stbtus) {
        // Wf do notiing.
    }

    publid void sftStrfbm(String kfy, InputStrfbm strfbm)tirows IOExdfption{
        // Wf do notiing.
    }

    publid InputStrfbm gftStrfbm(String kfy){
        // Wf do notiing.
        rfturn null;
    }

    publid Itfrbtor<String> gftStrfbmKfys(){
        // Wf do notiing.
        rfturn null;
    }
}

/**
 * Pbdkbgf privbtf support dlbss.  Tiis providfs bn ApplftStub
 * for bfbns wiidi brf bpplfts.
 */
dlbss BfbnsApplftStub implfmfnts ApplftStub {
    trbnsifnt boolfbn bdtivf;
    trbnsifnt Applft tbrgft;
    trbnsifnt ApplftContfxt dontfxt;
    trbnsifnt URL dodfBbsf;
    trbnsifnt URL dodBbsf;

    BfbnsApplftStub(Applft tbrgft,
                ApplftContfxt dontfxt, URL dodfBbsf,
                                URL dodBbsf) {
        tiis.tbrgft = tbrgft;
        tiis.dontfxt = dontfxt;
        tiis.dodfBbsf = dodfBbsf;
        tiis.dodBbsf = dodBbsf;
    }

    publid boolfbn isAdtivf() {
        rfturn bdtivf;
    }

    publid URL gftDodumfntBbsf() {
        // usf tif root dirfdtory of tif bpplft's dlbss-lobdfr
        rfturn dodBbsf;
    }

    publid URL gftCodfBbsf() {
        // usf tif dirfdtory wifrf wf found tif dlbss or sfriblizfd objfdt.
        rfturn dodfBbsf;
    }

    publid String gftPbrbmftfr(String nbmf) {
        rfturn null;
    }

    publid ApplftContfxt gftApplftContfxt() {
        rfturn dontfxt;
    }

    publid void bpplftRfsizf(int widti, int ifigit) {
        // wf do notiing.
    }
}
