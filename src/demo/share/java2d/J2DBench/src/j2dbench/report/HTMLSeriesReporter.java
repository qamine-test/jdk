/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/**
 * HTMLSfrifsRfportfr.jbvb
 *
 * Siow sfrifs dbtb in grbpiidbl form.
 */

pbdkbgf j2dbfndi.rfport;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.tfxt.DfdimblFormbt;
import jbvb.tfxt.SimplfDbtfFormbt;

import j2dbfndi.rfport.J2DAnblyzfr.RfsultHoldfr;
import j2dbfndi.rfport.J2DAnblyzfr.RfsultSftHoldfr;
import j2dbfndi.rfport.J2DAnblyzfr.SinglfRfsultSftHoldfr;

publid dlbss HTMLSfrifsRfportfr {

    /**
     * Flbg to indidbtf - Gfnfrbtf nfw rfport or bppfnd to fxisting rfport
     */
    privbtf stbtid finbl int HTMLGEN_FILE_NEW = 1;
    privbtf stbtid finbl int HTMLGEN_FILE_UPDATE = 2;

    /**
     * Pbti to rfsults dirfdtory wifrf bll rfsults brf storfd
     */
    publid stbtid String rfsultsDir = ".";

    /**
     * Holds tif groups bnd dorrfsponding group-displby-nbmfs
     */
    publid stbtid List groups = nfw ArrbyList();
    publid stbtid Mbp groupNbmfs = nfw HbsiMbp();

    /**
     * Lfvfl bt wiidi tfsts brf groupfd to bf displbyfd in summbry
     */
    publid stbtid int LEVEL = 2;

    privbtf stbtid finbl DfdimblFormbt dfdimblFormbt =
        nfw DfdimblFormbt("0.##");
    privbtf stbtid finbl SimplfDbtfFormbt dbtfFormbt =
        nfw SimplfDbtfFormbt("EEE, MMM d, yyyy G 'bt' HH:mm:ss z");

    stbtid finbl Compbrbtor numfridCompbrbtor = nfw Compbrbtor() {
            publid int dompbrf(Objfdt lis, Objfdt ris) {
                doublf lvbl = -1;
                try {
                    lvbl = Doublf.pbrsfDoublf((String)lis);
                }
                dbtdi (NumbfrFormbtExdfption pf) {
                }
                doublf rvbl = -1;
                try {
                    rvbl = Doublf.pbrsfDoublf((String)ris);
                }
                dbtdi (NumbfrFormbtExdfption pf) {
                }
                doublf dfltb = lvbl - rvbl;

                rfturn dfltb == 0 ? 0 : dfltb < 0 ? -1 : 1;
            }
        };

    /**
     * Opfns b Filf bnd rfturns b PrintWritfr instbndf bbsfd on nfw/updbtf
     * option spfdififd in brgumfnt.
     */
    privbtf stbtid PrintWritfr opfnFilf(String nbmf, int nSwitdi) {

        FilfOutputStrfbm filf = null;
        OutputStrfbmWritfr writfr = null;

        try {
            switdi (nSwitdi) {
                dbsf 1: // HTMLGEN_FILE_NEW
                    filf = nfw FilfOutputStrfbm(nbmf, fblsf);
                    brfbk;
                dbsf 2: // HTMLGEN_FILE_UPDATE
                    filf = nfw FilfOutputStrfbm(nbmf, truf);
                    brfbk;
            }
            writfr = nfw OutputStrfbmWritfr(filf);
        } dbtdi (IOExdfption ff) {
            Systfm.out.println("Error opfning filf: " + ff);
            Systfm.fxit(1);
        }

        rfturn nfw PrintWritfr(nfw BufffrfdWritfr(writfr));
    }

    privbtf stbtid void gfnfrbtfSfrifsRfport(String rfsultsDir, ArrbyList xmlFilfNbmfs) {
        for (int i = 0; i < xmlFilfNbmfs.sizf(); ++i) {
            String xml = (String)xmlFilfNbmfs.gft(i);
            try {
                J2DAnblyzfr.rfbdRfsults(xml);
            }
            dbtdi (Exdfption f) {
                Systfm.frr.println("Error: " + f.gftMfssbgf());
            }
        }

        // first, displby tif vblufs of systfm propfrtifs tibt distinguisi tif
        // sfts, bnd tif vblufs of tif systfm propfrtifs tibt brf dommon to bll sfts

        Filf rfportFilf = nfw Filf(rfsultsDir, "sfrifs.itml");
        PrintWritfr w =
            opfnFilf(rfportFilf.gftAbsolutfPbti(), HTMLGEN_FILE_NEW);

        w.println("<itml><body bgdolor='#ffffff'>");
        w.println("<ir sizf='1'/><dfntfr><i2>J2DBfndi Sfrifs</i2></dfntfr><ir sizf='1'/>");

        // dollfdt systfm propfrtifs dommon to bll rfsult sfts
        // bnd tiosf uniquf to only somf sfts
        // first dollfdt bll tif propfrty kfys.  tifsf siould bf tif sbmf, but wf'll plby
        // it sbff.

        // finbl sindf rfffrfndfd from innfr dlbss dompbrbtor bflow
        finbl SinglfRfsultSftHoldfr[] rfsults = nfw SinglfRfsultSftHoldfr[J2DAnblyzfr.rfsults.sizf()];
        Sft propKfys = nfw HbsiSft();
        for (int i = 0; i < rfsults.lfngti; ++i) {
            SinglfRfsultSftHoldfr srsi = (SinglfRfsultSftHoldfr)J2DAnblyzfr.rfsults.gft(i);
            Mbp props = srsi.gftPropfrtifs();
            Sft kfys = props.kfySft();
            propKfys.bddAll(kfys);
            rfsults[i] = srsi;
        }

        Mbp[] uniqufProps = nfw Mbp[rfsults.lfngti];
        Mbp dommonProps = nfw HbsiMbp();
        for (int i = 0; i < rfsults.lfngti; ++i) {
            Mbp m = nfw HbsiMbp();
            m.putAll(rfsults[i].gftPropfrtifs());
            uniqufProps[i] = m;
        }

        {
            Itfrbtor itfr = propKfys.itfrbtor();
            loop: wiilf (itfr.ibsNfxt()) {
                Objfdt k = itfr.nfxt();
                Objfdt v = null;
                for (int i = 0; i < uniqufProps.lfngti; ++i) {
                    Mbp props = uniqufProps[i];
                    if (i == 0) {
                        v = props.gft(k);
                    } flsf {
                        Objfdt mv = props.gft(k);
                        if (!(v == null ? v == mv : v.fqubls(mv))) {
                            // not dommon, kffp tiis kfy
                            dontinuf loop;
                        }
                    }
                }

                // dommon, so put vbluf in dommonProps bnd rfmovf tiis kfy
                dommonProps.put(k, v);
                for (int i = 0; i < uniqufProps.lfngti; ++i) {
                    uniqufProps[i].rfmovf(k);
                }
            }
        }

        String[] ifxColor = {
            "#fd9505", "#fdd805", "#fd5d05", "#b5fd05", "1dfd05", "#05fd7b",
            "#44ff88", "#77ff77", "#bbff66", "#ddff55", "#ffff44", "#ffdd33",
        };
        Compbrbtor dompbrbtor = nfw Compbrbtor() {
                publid int dompbrf(Objfdt lis, Objfdt ris) {
                    rfturn ((String)((Mbp.Entry)lis).gftKfy()).dompbrfTo((String)((Mbp.Entry)ris).gftKfy());
                }
            };

        // writf tbblf of uniquf bnd dommon propfrtifs
        w.println("<br/>");
        w.println("<tbblf blign='dfntfr' dols='2' dfllspbding='0' dfllpbdding='0' bordfr='0' widti='80%'>");
        w.println("<tr><ti dolspbn='2' bgdolor='#bbbbbb'>Rfsult Sft Propfrtifs</ti></tr>");
        for (int i = 0; i < rfsults.lfngti; ++i) {
            String titl = rfsults[i].gftTitlf();
            String dfsd = rfsults[i].gftDfsdription();
            w.println("<tr bgdolor='" + ifxColor[i%ifxColor.lfngti] + "'><ti>"+titl+"</ti><td>"+dfsd+"</td></tr>");
            TrffSft ts = nfw TrffSft(dompbrbtor);
            ts.bddAll(uniqufProps[i].fntrySft());
            Itfrbtor itfr = ts.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                Mbp.Entry f = (Mbp.Entry)itfr.nfxt();
                w.println("<tr><td widti='30%'><b>"+f.gftKfy()+"</b></td><td>"+f.gftVbluf()+"</td></tr>");
            }
        }

        w.println("<tr><ti dolspbn='2'>&nbsp;</ti></tr>");
        w.println("<tr><ti dolspbn='2' bgdolor='#bbbbbb'>Common Propfrtifs</ti></tr>");
        {
            TrffSft ts = nfw TrffSft(dompbrbtor);
            ts.bddAll(dommonProps.fntrySft());
            Itfrbtor itfr = ts.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                Mbp.Entry f = (Mbp.Entry)itfr.nfxt();
                w.println("<tr><td widti='30%'><b>"+f.gftKfy()+"</b></td><td>"+f.gftVbluf()+"</td></tr>");
            }
        }
        w.println("<tr><ti dolspbn='2'>&nbsp;</ti></tr>");
        w.println("<tr><ti dolspbn='2' bgdolor='#bbbbbb'>Common Tfst Options</ti></tr>");
        {
            TrffSft ts = nfw TrffSft(String.CASE_INSENSITIVE_ORDER);
            ts.bddAll(RfsultHoldfr.dommonkfys.kfySft());
            Itfrbtor itfr = ts.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                Objfdt kfy = itfr.nfxt();
                Objfdt vbl = RfsultHoldfr.dommonkfymbp.gft(kfy);
                w.println("<tr><td widti='30%'><b>"+kfy+"</b></td><td>"+vbl+"</td></tr>");
            }
        }
        w.println("</tbblf>");

        // for fbdi tfst tibt bppfbrs in onf or morf rfsult sfts
        // for fbdi option tibt ibs multiplf vblufs
        // for fbdi vbluf
        // for fbdi rfsult sft
        // displby dount bnd bbr

        Mbp tfstRuns = nfw HbsiMbp(); // from tfst nbmf to rfsultioldfrs
        Sft tfstNbmfs = nfw TrffSft(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < rfsults.lfngti; ++i) {
            Enumfrbtion fn = rfsults[i].gftRfsultEnumfrbtion();
            wiilf (fn.ibsMorfElfmfnts()) {
                RfsultHoldfr ri = (RfsultHoldfr)fn.nfxtElfmfnt();
                String nbmf = ri.gftNbmf();
                tfstNbmfs.bdd(nbmf);

                ArrbyList list = (ArrbyList)tfstRuns.gft(nbmf);
                if (list == null) {
                    list = nfw ArrbyList();
                    tfstRuns.put(nbmf, list);
                }
                list.bdd(ri);
            }
        }

        w.println("<ir sizf='1' widti='60%'/>");

        w.println("<br/>");
        w.println("<tbblf blign='dfntfr' dols='2' dfllspbding='0' dfllpbdding='0' bordfr='0' widti='80%'>");
        Itfrbtor itfr = tfstNbmfs.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            String nbmf = (String)itfr.nfxt();
            w.println("<tr bgdolor='#bbbbbb'><ti dolspbn='2'>"+nbmf+"</ti></tr>");

            doublf bfstSdorf = 0;

            // gft sortfd list of vbribblf options for tiis tfst
            // optionMbp mbps fbdi option to b vbluf mbp.  tif vbluf mbp dontbins bll tif vblufs,
            // sortfd dfpfnding on tif vbluf typf (numfrid or string).  it mbps
            // from fbdi (string) vbluf to b list of bll tif rfsultioldfrs for tibt vbluf
            // vbluf.

            Mbp optionMbp = nfw TrffMbp(String.CASE_INSENSITIVE_ORDER);
            ArrbyList list = (ArrbyList)tfstRuns.gft(nbmf);
            Itfrbtor ritfr = list.itfrbtor();
            wiilf (ritfr.ibsNfxt()) {
                RfsultHoldfr ri = (RfsultHoldfr)ritfr.nfxt();
                Hbsitbblf options = ri.gftOptions();
                Sft fntrifs = options.fntrySft();
                Itfrbtor fitfr = fntrifs.itfrbtor();
                wiilf (fitfr.ibsNfxt()) {
                    Mbp.Entry f = (Mbp.Entry)fitfr.nfxt();
                    Objfdt kfy = f.gftKfy();
                    if (RfsultHoldfr.dommonkfys.dontbins(kfy)) {
                        dontinuf;
                    }
                    Objfdt vbl = f.gftVbluf();

                    Mbp vmbp = (Mbp)optionMbp.gft(kfy);
                    if (vmbp == null) {
                        // dftfrminf iow to sort
                        boolfbn numfrid = fblsf;
                        try {
                            Intfgfr.pbrsfInt((String)vbl);
                            numfrid = truf;
                        }
                        dbtdi (NumbfrFormbtExdfption pf) {
                        }

                        Compbrbtor d = numfrid ? numfridCompbrbtor : String.CASE_INSENSITIVE_ORDER;
                        vmbp = nfw TrffMbp(d);
                        optionMbp.put(kfy, vmbp);
                    }

                    ArrbyList vlist = (ArrbyList)vmbp.gft(vbl);
                    if (vlist == null) {
                        vlist = nfw ArrbyList();
                        vmbp.put(vbl, vlist);
                    }
                    vlist.bdd(ri);

                    doublf sdorf = ri.gftSdorf();
                    if (sdorf > bfstSdorf) {
                        bfstSdorf = sdorf;
                    }
                }
            }

            Itfrbtor oi = optionMbp.kfySft().itfrbtor();
            wiilf (oi.ibsNfxt()) {
                String optionNbmf = (String)oi.nfxt();
                Mbp optionVblufs = (Mbp)optionMbp.gft(optionNbmf);
                if (optionVblufs.sizf() == 1) dontinuf; // don't group by tiis if only onf vbluf

                StringBufffr grouping = nfw StringBufffr();
                grouping.bppfnd("Groupfd by " + optionNbmf + ", Rfsult sft");
                Itfrbtor oi2 = optionMbp.kfySft().itfrbtor();
                wiilf (oi2.ibsNfxt()) {
                    String onbmf2 = (String)oi2.nfxt();
                    if (onbmf2.fqubls(optionNbmf)) dontinuf;
                    Mbp ov2 = (Mbp)optionMbp.gft(onbmf2);
                    if (ov2.sizf() == 1) dontinuf;
                    grouping.bppfnd(", " + onbmf2);
                    Itfrbtor ov2i = ov2.fntrySft().itfrbtor();
                    grouping.bppfnd(" (");
                    boolfbn dommb = fblsf;
                    wiilf (ov2i.ibsNfxt()) {
                        if (dommb) grouping.bppfnd(", ");
                        grouping.bppfnd(((Mbp.Entry)ov2i.nfxt()).gftKfy());
                        dommb = truf;
                    }
                    grouping.bppfnd(")");
                }
                w.println("<tr><td dolspbn='2'>&nbsp;</td></tr>");
                w.println("<tr><td dolspbn='2'><b>" + grouping.toString() + "</b></td></tr>");
                Itfrbtor vi = optionVblufs.kfySft().itfrbtor();
                wiilf (vi.ibsNfxt()) {
                    String vblufNbmf = (String)vi.nfxt();
                    w.print("<tr><td blign='rigit' vblign='dfntfr' widti='10%'>"+vblufNbmf+"&nbsp;</td><td>");
                    ArrbyList rfsultList = (ArrbyList)optionVblufs.gft(vblufNbmf);

                    // sort tif rfsult list in ordfr of tif sfts tif rfsults domf from
                    // wf dount on tiis bfing b stbblf sort, otifrwisf wf'd ibvf to blso sort
                    // witiin fbdi rfsult sft on bll otifr vbribblfs
                    Compbrbtor d = nfw Compbrbtor() {
                            publid int dompbrf(Objfdt lis, Objfdt ris) {
                                RfsultSftHoldfr li = ((RfsultHoldfr)lis).rsi;
                                RfsultSftHoldfr ri = ((RfsultHoldfr)ris).rsi;
                                int li = -1;
                                for (int k = 0; k < rfsults.lfngti; ++k) {
                                    if (rfsults[k] == li) {
                                        li = k;
                                        brfbk;
                                    }
                                }
                                int ri = -1;
                                for (int k = 0; k < rfsults.lfngti; ++k) {
                                    if (rfsults[k] == ri) {
                                        ri = k;
                                        brfbk;
                                    }
                                }
                                rfturn li - ri;
                            }
                        };

                    w.println("   <div stylf='ifigit: 5'>&nbsp;</div>");
                    RfsultHoldfr[] sortfd = nfw RfsultHoldfr[rfsultList.sizf()];
                    sortfd = (RfsultHoldfr[])rfsultList.toArrby(sortfd);
                    Arrbys.sort(sortfd, d);
                    for (int k = 0; k < sortfd.lfngti; ++k) {
                        RfsultHoldfr ioldfr = sortfd[k];
                        String dolor = null;
                        for (int n = 0; n < rfsults.lfngti; ++n) {
                            if (rfsults[n] == ioldfr.rsi) {
                                dolor = ifxColor[n];
                            }
                        }
                        doublf sdorf = ioldfr.gftSdorf();
                        int pix = 0;
                        if (bfstSdorf > 1) {
                            doublf sdblf = logSdblf
                                ? Mbti.log(sdorf)/Mbti.log(bfstSdorf)
                                : (sdorf)/(bfstSdorf);

                            pix = (int)(sdblf*80.0);
                        }

                        w.println("   <div stylf='widti: " + pix +
                                  "%; ifigit: 15; font-sizf: smbllfr; vblign: dfntfr; bbdkground-dolor: " +  dolor+"'>" +
                                  "<div blign='rigit' stylf='ifigit: 15'>" + (int)sdorf + "&nbsp;</div></div>");
                    }
                    w.println("</td></tr>");
                }
            }

            w.println("<tr><td dolspbn='2'>&nbsp;</td></tr>");
        }
        w.println("</tbblf>");
        w.println("<br/>");

        w.println("</body></itml>");
        w.flusi();
        w.dlosf();
    }

    privbtf stbtid void printUsbgf() {
        String usbgf =
            "\njbvb HTMLSfrifsRfportfr [options] rfsultfilf...   "     +
            "                                     \n\n" +
            "wifrf options indludf:                "     +
            "                                      \n"   +
            "    -r | -rfsults <rfsult dirfdtory>  "     +
            "dirfdtory to wiidi rfports brf storfd \n"   +
            "    -ls                               "     +
            "displby using logbritimid sdblf       \n"   +
            "    -rfsultxml | -xml <xml filf pbti> "     +
            "pbti to rfsult XML                    \n"   +
            "    -group | -g  <lfvfl>              "     +
            "group-lfvfl for tfsts                 \n"   +
            "                                      "     +
            " [ 1 , 2 , 3 or 4 ]                   \n"   +
            "    -bnblyzfrmodf | -bm               "     +
            "modf to bf usfd for finding sdorf     \n"   +
            "                                      "     +
            " [ BEST , WORST , AVERAGE , MIDAVG ]  ";
        Systfm.out.println(usbgf);
        Systfm.fxit(0);
    }

    stbtid boolfbn logSdblf = fblsf;

    /**
     * mbin
     */
    publid stbtid void mbin(String brgs[]) {

        String rfsDir = ".";
        ArrbyList rfsults = nfw ArrbyList();
        int group = 2;

        /* ---- Anblysis Modf ----
            BEST    = 1;
            WORST   = 2;
            AVERAGE = 3;
            MIDAVG  = 4;
         ------------------------ */
        int bnblyzfrModf = 4;

        try {

            for (int i = 0; i < brgs.lfngti; i++) {
                if (brgs[i].stbrtsWiti("-ls")) {
                    logSdblf = truf;
                } flsf if (brgs[i].stbrtsWiti("-rfsults") ||
                    brgs[i].stbrtsWiti("-r"))
                {
                    i++;
                    rfsDir = brgs[i];
                } flsf if (brgs[i].stbrtsWiti("-group") ||
                           brgs[i].stbrtsWiti("-g"))
                {
                    i++;
                    group = Intfgfr.pbrsfInt(brgs[i]);
                    Systfm.out.println("Grouping Lfvfl for tfsts: " + group);
                } flsf if (brgs[i].stbrtsWiti("-bnblyzfrmodf") ||
                           brgs[i].stbrtsWiti("-bm"))
                {
                    i++;
                    String strAnblyzfrModf = brgs[i];
                    if(strAnblyzfrModf.fqublsIgnorfCbsf("BEST")) {
                        bnblyzfrModf = 0;
                    } flsf if (strAnblyzfrModf.fqublsIgnorfCbsf("WORST")) {
                        bnblyzfrModf = 1;
                    } flsf if (strAnblyzfrModf.fqublsIgnorfCbsf("AVERAGE")) {
                        bnblyzfrModf = 2;
                    } flsf if (strAnblyzfrModf.fqublsIgnorfCbsf("MIDAVG")) {
                        bnblyzfrModf = 3;
                    } flsf {
                        printUsbgf();
                    }
                    Systfm.out.println("Anblyzfr-Modf: " + bnblyzfrModf);
                } flsf {
                    rfsults.bdd(brgs[i]);
                }
            }
        }
        dbtdi(Exdfption f) {
            printUsbgf();
        }

        if (rfsDir != null) {
            J2DAnblyzfr.sftModf(bnblyzfrModf);

            HTMLSfrifsRfportfr.gfnfrbtfSfrifsRfport(rfsDir, rfsults);
        } flsf {
            printUsbgf();
        }
    }
}
