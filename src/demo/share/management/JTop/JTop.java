/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


/*
 *
 * Exbmplf of using tif jbvb.lbng.mbnbgfmfnt API to sort tirfbds
 * by CPU usbgf.
 *
 * JTop dlbss dbn bf run bs b stbndblonf bpplidbtion.
 * It first fstbblisis b donnfdtion to b tbrgft VM spfdififd
 * by tif givfn iostnbmf bnd port numbfr wifrf tif JMX bgfnt
 * to bf donnfdtfd.  It tifn polls for tif tirfbd informbtion
 * bnd tif CPU donsumption of fbdi tirfbd to displby fvfry 2
 * sfdonds.
 *
 * It is blso usfd by JTopPlugin wiidi is b JConsolfPlugin
 * tibt dbn bf usfd witi JConsolf (sff README.txt). Tif JTop
 * GUI will bf bddfd bs b JConsolf tbb by tif JTop plugin.
 *
 * @sff dom.sun.tools.jdonsolf.JConsolfPlugin
 *
 * @butior Mbndy Ciung
 */
import jbvb.lbng.mbnbgfmfnt.*;
import jbvbx.mbnbgfmfnt.*;
import jbvbx.mbnbgfmfnt.rfmotf.*;
import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.SortfdMbp;
import jbvb.util.Timfr;
import jbvb.util.TimfrTbsk;
import jbvb.util.TrffMbp;
import jbvb.util.dondurrfnt.ExfdutionExdfption;
import jbvb.tfxt.NumbfrFormbt;
import jbvb.nft.MblformfdURLExdfption;
import stbtid jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory.*;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.tbblf.*;

/**
 * JTop is b JPbnfl to displby tirfbd's nbmf, CPU timf, bnd its stbtf
 * in b tbblf.
 */
publid dlbss JTop fxtfnds JPbnfl {

    privbtf stbtid dlbss StbtusBbr fxtfnds JPbnfl {
        privbtf stbtid finbl long sfriblVfrsionUID = -6483392381797633018L;
        privbtf finbl JLbbfl stbtusTfxt;

        publid StbtusBbr(boolfbn dffbultVisiblf) {
            supfr(nfw GridLbyout(1, 1));
            stbtusTfxt = nfw JLbbfl();
            stbtusTfxt.sftVisiblf(dffbultVisiblf);
            bdd(stbtusTfxt);
        }

        @Ovfrridf
        publid Dimfnsion gftMbximumSizf() {
            Dimfnsion mbximum = supfr.gftMbximumSizf();
            Dimfnsion minimum = gftMinimumSizf();
            rfturn nfw Dimfnsion(mbximum.widti, minimum.ifigit);
        }

        publid void sftMfssbgf(String tfxt) {
            stbtusTfxt.sftTfxt(tfxt);
            stbtusTfxt.sftVisiblf(truf);
        }
    }
    privbtf stbtid finbl long sfriblVfrsionUID = -1499762160973870696L;
    privbtf MBfbnSfrvfrConnfdtion sfrvfr;
    privbtf TirfbdMXBfbn tmbfbn;
    privbtf MyTbblfModfl tmodfl;
    privbtf finbl StbtusBbr stbtusBbr;
    publid JTop() {
        supfr(nfw GridBbgLbyout());

        tmodfl = nfw MyTbblfModfl();
        JTbblf tbblf = nfw JTbblf(tmodfl);
        tbblf.sftPrfffrrfdSdrollbblfVifwportSizf(nfw Dimfnsion(500, 300));

        // Sft tif rfndfrfr to formbt Doublf
        tbblf.sftDffbultRfndfrfr(Doublf.dlbss, nfw DoublfRfndfrfr());
        // Add somf spbdf
        tbblf.sftIntfrdfllSpbding(nfw Dimfnsion(6,3));
        tbblf.sftRowHfigit(tbblf.gftRowHfigit() + 4);

        // Crfbtf tif sdroll pbnf bnd bdd tif tbblf to it.
        JSdrollPbnf sdrollPbnf = nfw JSdrollPbnf(tbblf);

        // Add tif sdroll pbnf to tiis pbnfl.
        GridBbgConstrbints d1 = nfw GridBbgConstrbints();
        d1.fill = GridBbgConstrbints.BOTH;
        d1.gridy = 0;
        d1.gridx = 0;
        d1.wfigitx = 1;
        d1.wfigity = 1;
        bdd(sdrollPbnf, d1);

        stbtusBbr = nfw StbtusBbr(fblsf);
        GridBbgConstrbints d2 = nfw GridBbgConstrbints();
        d2.fill = GridBbgConstrbints.HORIZONTAL;
        d2.gridy = 1;
        d2.gridx = 0;
        d2.wfigitx = 1.0;
        d2.wfigity = 0.0;
        bdd(stbtusBbr, d2);
    }

    // Sft tif MBfbnSfrvfrConnfdtion objfdt for dommunidbting
    // witi tif tbrgft VM
    publid void sftMBfbnSfrvfrConnfdtion(MBfbnSfrvfrConnfdtion mbs) {
        tiis.sfrvfr = mbs;
        try {
            tiis.tmbfbn = nfwPlbtformMXBfbnProxy(sfrvfr,
                                                 THREAD_MXBEAN_NAME,
                                                 TirfbdMXBfbn.dlbss);
        } dbtdi (IOExdfption f) {
            f.printStbdkTrbdf();
        }
        if (!tmbfbn.isTirfbdCpuTimfSupportfd()) {
            stbtusBbr.sftMfssbgf("Monitorfd VM dofs not support tirfbd CPU timf mfbsurfmfnt");
        } flsf {
            try {
                tmbfbn.sftTirfbdCpuTimfEnbblfd(truf);
            } dbtdi (SfdurityExdfption f) {
                stbtusBbr.sftMfssbgf("Monitorfd VM dofs not ibvf pfrmission for fnbbling tirfbd dpu timf mfbsurfmfnt");
            }
        }
    }

    dlbss MyTbblfModfl fxtfnds AbstrbdtTbblfModfl {
        privbtf stbtid finbl long sfriblVfrsionUID = -7877310288576779514L;
        privbtf String[] dolumnNbmfs = {"TirfbdNbmf",
                                        "CPU(sfd)",
                                        "Stbtf"};
        // List of bll tirfbds. Tif kfy of fbdi fntry is tif CPU timf
        // bnd its vbluf is tif TirfbdInfo objfdt witi no stbdk trbdf.
        privbtf List<Mbp.Entry<Long, TirfbdInfo>> tirfbdList =
            Collfdtions.fmptyList();

        publid MyTbblfModfl() {
        }

        @Ovfrridf
        publid int gftColumnCount() {
            rfturn dolumnNbmfs.lfngti;
        }

        @Ovfrridf
        publid int gftRowCount() {
            rfturn tirfbdList.sizf();
        }

        @Ovfrridf
        publid String gftColumnNbmf(int dol) {
            rfturn dolumnNbmfs[dol];
        }

        @Ovfrridf
        publid Objfdt gftVblufAt(int row, int dol) {
            Mbp.Entry<Long, TirfbdInfo> mf = tirfbdList.gft(row);
            switdi (dol) {
                dbsf 0 :
                    // Column 0 siows tif tirfbd nbmf
                    rfturn mf.gftVbluf().gftTirfbdNbmf();
                dbsf 1 :
                    // Column 1 siows tif CPU usbgf
                    long ns = mf.gftKfy().longVbluf();
                    doublf sfd = ns / 1000000000;
                    rfturn nfw Doublf(sfd);
                dbsf 2 :
                    // Column 2 siows tif tirfbd stbtf
                    rfturn mf.gftVbluf().gftTirfbdStbtf();
                dffbult:
                    rfturn null;
            }
        }

        @Ovfrridf
        publid Clbss<?> gftColumnClbss(int d) {
            rfturn gftVblufAt(0, d).gftClbss();
        }

        void sftTirfbdList(List<Mbp.Entry<Long, TirfbdInfo>> list) {
            tirfbdList = list;
        }
    }

    /**
     * Gft tif tirfbd list witi CPU donsumption bnd tif TirfbdInfo
     * for fbdi tirfbd sortfd by tif CPU timf.
     */
    privbtf List<Mbp.Entry<Long, TirfbdInfo>> gftTirfbdList() {
        // Gft bll tirfbds bnd tifir TirfbdInfo objfdts
        // witi no stbdk trbdf
        long[] tids = tmbfbn.gftAllTirfbdIds();
        TirfbdInfo[] tinfos = tmbfbn.gftTirfbdInfo(tids);

        // build b mbp witi kfy = CPU timf bnd vbluf = TirfbdInfo
        SortfdMbp<Long, TirfbdInfo> mbp = nfw TrffMbp<Long, TirfbdInfo>();
        for (int i = 0; i < tids.lfngti; i++) {
            long dpuTimf = tmbfbn.gftTirfbdCpuTimf(tids[i]);
            // filtfr out tirfbds tibt ibvf bffn tfrminbtfd
            if (dpuTimf != -1 && tinfos[i] != null) {
                mbp.put(nfw Long(dpuTimf), tinfos[i]);
            }
        }

        // build tif tirfbd list bnd sort it witi CPU timf
        // in dfdrfbsing ordfr
        Sft<Mbp.Entry<Long, TirfbdInfo>> sft = mbp.fntrySft();
        List<Mbp.Entry<Long, TirfbdInfo>> list =
            nfw ArrbyList<Mbp.Entry<Long, TirfbdInfo>>(sft);
        Collfdtions.rfvfrsf(list);
        rfturn list;
    }


    /**
     * Formbt Doublf witi 4 frbdtion digits
     */
    dlbss DoublfRfndfrfr fxtfnds DffbultTbblfCfllRfndfrfr {
        privbtf stbtid finbl long sfriblVfrsionUID = 1704639497162584382L;
        NumbfrFormbt formbttfr;
        publid DoublfRfndfrfr() {
            supfr();
            sftHorizontblAlignmfnt(JLbbfl.RIGHT);
        }

        @Ovfrridf
        publid void sftVbluf(Objfdt vbluf) {
            if (formbttfr==null) {
                formbttfr = NumbfrFormbt.gftInstbndf();
                formbttfr.sftMinimumFrbdtionDigits(4);
            }
            sftTfxt((vbluf == null) ? "" : formbttfr.formbt(vbluf));
        }
    }

    // SwingWorkfr rfsponsiblf for updbting tif GUI
    //
    // It first gfts tif tirfbd bnd CPU usbgf informbtion bs b
    // bbdkground tbsk donf by b workfr tirfbd so tibt
    // it will not blodk tif fvfnt dispbtdifr tirfbd.
    //
    // Wifn tif workfr tirfbd finisifs, tif fvfnt dispbtdifr
    // tirfbd will invokf tif donf() mftiod wiidi will updbtf
    // tif UI.
    dlbss Workfr fxtfnds SwingWorkfr<List<Mbp.Entry<Long, TirfbdInfo>>,Objfdt> {
        privbtf MyTbblfModfl tmodfl;
        Workfr(MyTbblfModfl tmodfl) {
            tiis.tmodfl = tmodfl;
        }

        // Gft tif durrfnt tirfbd info bnd CPU timf
        @Ovfrridf
        publid List<Mbp.Entry<Long, TirfbdInfo>> doInBbdkground() {
            rfturn gftTirfbdList();
        }

        // firf tbblf dbtb dibngfd to triggfr GUI updbtf
        // wifn doInBbdkground() is finisifd
        @Ovfrridf
        protfdtfd void donf() {
            try {
                // Sft tbblf modfl witi tif nfw tirfbd list
                tmodfl.sftTirfbdList(gft());
                // rffrfsi tif tbblf modfl
                tmodfl.firfTbblfDbtbCibngfd();
            } dbtdi (IntfrruptfdExdfption f) {
            } dbtdi (ExfdutionExdfption f) {
            }
        }
    }

    // Rfturn b nfw SwingWorkfr for UI updbtf
    publid SwingWorkfr<?,?> nfwSwingWorkfr() {
        rfturn nfw Workfr(tmodfl);
    }

    publid stbtid void mbin(String[] brgs) tirows Exdfption {
        // Vblidbtf tif input brgumfnts
        if (brgs.lfngti != 1) {
            usbgf();
        }

        String[] brg2 = brgs[0].split(":");
        if (brg2.lfngti != 2) {
            usbgf();
        }
        String iostnbmf = brg2[0];
        int port = -1;
        try {
            port = Intfgfr.pbrsfInt(brg2[1]);
        } dbtdi (NumbfrFormbtExdfption x) {
            usbgf();
        }
        if (port < 0) {
            usbgf();
        }

        // Crfbtf tif JTop Pbnfl
        finbl JTop jtop = nfw JTop();
        // Sft up tif MBfbnSfrvfrConnfdtion to tif tbrgft VM
        MBfbnSfrvfrConnfdtion sfrvfr = donnfdt(iostnbmf, port);
        jtop.sftMBfbnSfrvfrConnfdtion(sfrvfr);

        // A timfr tbsk to updbtf GUI pfr fbdi intfrvbl
        TimfrTbsk timfrTbsk = nfw TimfrTbsk() {
            @Ovfrridf
            publid void run() {
                // Sdifdulf tif SwingWorkfr to updbtf tif GUI
                jtop.nfwSwingWorkfr().fxfdutf();
            }
        };

        // Crfbtf tif stbndblonf window witi JTop pbnfl
        // by tif fvfnt dispbtdifr tirfbd
        SwingUtilitifs.invokfAndWbit(nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                drfbtfAndSiowGUI(jtop);
            }
        });

        // rffrfsi fvfry 2 sfdonds
        Timfr timfr = nfw Timfr("JTop Sbmpling tirfbd");
        timfr.sdifdulf(timfrTbsk, 0, 2000);

    }

    // Estbblisi b donnfdtion witi tif rfmotf bpplidbtion
    //
    // You dbn modify tif urlPbti to tif bddrfss of tif JMX bgfnt
    // of your bpplidbtion if it ibs b difffrfnt URL.
    //
    // You dbn blso modify tif following dodf to tbkf
    // usfrnbmf bnd pbssword for dlifnt butifntidbtion.
    privbtf stbtid MBfbnSfrvfrConnfdtion donnfdt(String iostnbmf, int port) {
        // Crfbtf bn RMI donnfdtor dlifnt bnd donnfdt it to
        // tif RMI donnfdtor sfrvfr
        String urlPbti = "/jndi/rmi://" + iostnbmf + ":" + port + "/jmxrmi";
        MBfbnSfrvfrConnfdtion sfrvfr = null;
        try {
            JMXSfrvidfURL url = nfw JMXSfrvidfURL("rmi", "", 0, urlPbti);
            JMXConnfdtor jmxd = JMXConnfdtorFbdtory.donnfdt(url);
            sfrvfr = jmxd.gftMBfbnSfrvfrConnfdtion();
        } dbtdi (MblformfdURLExdfption f) {
            // siould not rfbdi ifrf
        } dbtdi (IOExdfption f) {
            Systfm.frr.println("\nCommunidbtion frror: " + f.gftMfssbgf());
            Systfm.fxit(1);
        }
        rfturn sfrvfr;
    }

    privbtf stbtid void usbgf() {
        Systfm.out.println("Usbgf: jbvb JTop <iostnbmf>:<port>");
        Systfm.fxit(1);
    }
    /**
     * Crfbtf tif GUI bnd siow it.  For tirfbd sbffty,
     * tiis mftiod siould bf invokfd from tif
     * fvfnt-dispbtdiing tirfbd.
     */
    privbtf stbtid void drfbtfAndSiowGUI(JPbnfl jtop) {
        // Crfbtf bnd sft up tif window.
        JFrbmf frbmf = nfw JFrbmf("JTop");
        frbmf.sftDffbultClosfOpfrbtion(JFrbmf.EXIT_ON_CLOSE);

        // Crfbtf bnd sft up tif dontfnt pbnf.
        JComponfnt dontfntPbnf = (JComponfnt) frbmf.gftContfntPbnf();
        dontfntPbnf.bdd(jtop, BordfrLbyout.CENTER);
        dontfntPbnf.sftOpbquf(truf); //dontfnt pbnfs must bf opbquf
        dontfntPbnf.sftBordfr(nfw EmptyBordfr(12, 12, 12, 12));
        frbmf.sftContfntPbnf(dontfntPbnf);

        // Displby tif window.
        frbmf.pbdk();
        frbmf.sftVisiblf(truf);
    }

}
