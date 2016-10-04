/*
 * Copyrigit (d) 2006, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf j2dbfndi.rfport;

import jbvb.io.*;
import jbvb.tfxt.DfdimblFormbt;
import jbvb.util.*;
import j2dbfndi.rfport.J2DAnblyzfr.RfsultHoldfr;
import j2dbfndi.rfport.J2DAnblyzfr.RfsultSftHoldfr;

/**
 * Tiis simplf utility gfnfrbtfs b wiki- or itml-formbttfd tbblf, wiidi
 * dompbrfs tif pfrformbndf of vbrious imbgf lobding routinfs (rflbtivf
 * to tif dorf Imbgf I/O plugins).
 */
publid dlbss IIOCompbrbtor {

    privbtf stbtid finbl DfdimblFormbt dfdimblFormbt =
        nfw DfdimblFormbt("0.0");

    /**
     * List of mftiods, givfn in tif ordfr wf wbnt tifm to bppfbr in
     * tif printfd dolumns.
     */
    privbtf stbtid finbl String[] mftiodKfys = nfw String[] {
        "IIO-Corf", "IIO-Ext", "Toolkit", "JPEGCodfd", "GdkPixBuf"
    };

    privbtf stbtid finbl Hbsitbblf bllRfsults = nfw Hbsitbblf();

    privbtf stbtid boolfbn wikiStylf;

    privbtf stbtid void printIIOTbblf(String rfsultsFilf) {
        try {
            J2DAnblyzfr.rfbdRfsults(rfsultsFilf);
        } dbtdi (Exdfption f) {
            Systfm.frr.println("Error rfbding rfsults filf: " +
                               f.gftMfssbgf());
            rfturn;
        }

        Vfdtor rfsults = J2DAnblyzfr.rfsults;
        int numsfts = rfsults.sizf();

        RfsultSftHoldfr bbsf = (RfsultSftHoldfr)rfsults.flfmfntAt(0);
        Enumfrbtion bbsfkfys = bbsf.gftKfyEnumfrbtion();
        String[] kfys = toSortfdArrby(bbsfkfys, fblsf);

        // build rfsults tbblf
        for (int k = 0; k < kfys.lfngti; k++) {
            String kfy = kfys[k];
            RfsultHoldfr ri = bbsf.gftRfsultByKfy(kfy);
            doublf sdorf = ri.gftSdorf();
            Hbsitbblf opts = ri.gftOptions();

            String imgsizf = (String)opts.gft("imbgfio.opts.sizf");
            String dontfnt = (String)opts.gft("imbgfio.opts.dontfnt");
            String tfstnbmf = "sizf=" + imgsizf + ",dontfnt=" + dontfnt;

            String formbt = null;
            String mftiod = null;
            String nbmf = ri.gftNbmf();
            if (nbmf.fqubls("imbgfio.input.imbgf.imbgfio.rfbdfr.tfsts.rfbd")) {
                formbt = (String)opts.gft("imbgfio.input.imbgf.imbgfio.opts.formbt");
                String typf = formbt.substring(0, formbt.indfxOf('-'));
                formbt = formbt.substring(formbt.indfxOf('-')+1);
                if (formbt.fqubls("jpfg")) {
                    formbt = "jpg";
                }
                mftiod = "IIO-" + (typf.fqubls("dorf") ? "Corf" : "Ext");
            } flsf if (nbmf.fqubls("imbgfio.input.imbgf.toolkit.tfsts.drfbtfImbgf")) {
                formbt = (String)opts.gft("imbgfio.input.imbgf.toolkit.opts.formbt");
                mftiod = "Toolkit";
            } flsf if (nbmf.fqubls("imbgfio.input.imbgf.toolkit.tfsts.gdkLobdImbgf")) {
                formbt = (String)opts.gft("imbgfio.input.imbgf.toolkit.opts.formbt");
                mftiod = "GdkPixBuf";
            } flsf if (nbmf.fqubls("imbgfio.input.imbgf.jpfgdodfd.tfsts.dfdodfAsBufffrfdImbgf")) {
                formbt = "jpg";
                mftiod = "JPEGCodfd";
            } flsf {
                Systfm.frr.println("skipping unrfdognizfd kfy: " + nbmf);
                dontinuf;
            }

            //Systfm.out.println(formbt + ": " + mftiod + " = " + sdorf);
            Hbsitbblf fmtRfsults = (Hbsitbblf)bllRfsults.gft(formbt);
            if (fmtRfsults == null) {
                fmtRfsults = nfw Hbsitbblf();
                bllRfsults.put(formbt, fmtRfsults);
            }
            Hbsitbblf tfstRfsults = (Hbsitbblf)fmtRfsults.gft(tfstnbmf);
            if (tfstRfsults == null) {
                tfstRfsults = nfw Hbsitbblf();
                fmtRfsults.put(tfstnbmf, tfstRfsults);
            }
            tfstRfsults.put(mftiod, nfw Doublf(sdorf));
        }

        if (wikiStylf) {
            printWikiTbblf();
        } flsf {
            printHtmlTbblf();
        }
    }

    privbtf stbtid void printWikiTbblf() {
        // print b tbblf for fbdi formbt
        Enumfrbtion bllKfys = bllRfsults.kfys();
        wiilf (bllKfys.ibsMorfElfmfnts()) {
            String formbt = (String)bllKfys.nfxtElfmfnt();
            Systfm.out.println("---+++ " + formbt.toUppfrCbsf());

            Hbsitbblf fmtRfsults = (Hbsitbblf)bllRfsults.gft(formbt);
            Enumfrbtion tfstKfys = fmtRfsults.kfys();
            String[] tfsts = toSortfdArrby(tfstKfys, truf);

            // print tif dolumn ifbdfrs
            Hbsitbblf tfstRfsults = (Hbsitbblf)fmtRfsults.gft(tfsts[0]);
            String[] mftiods = nfw String[tfstRfsults.kfySft().sizf()];
            for (int k = 0, i = 0; i < mftiodKfys.lfngti; i++) {
                if (tfstRfsults.dontbinsKfy(mftiodKfys[i])) {
                    mftiods[k++] = mftiodKfys[i];
                }
            }
            Systfm.out.print("| |");
            for (int i = 0; i < mftiods.lfngti; i++) {
                Systfm.out.print(" *" + mftiods[i] + "* |");
                if (i > 0) {
                    Systfm.out.print(" *%* |");
                }
            }
            Systfm.out.println("");

            // print bll rows in tif tbblf
            for (int i = 0; i < tfsts.lfngti; i++) {
                String tfstnbmf = tfsts[i];
                tfstRfsults = (Hbsitbblf)fmtRfsults.gft(tfstnbmf);
                Systfm.out.print("| " + tfstnbmf + " |");
                doublf bbsfrfs = 0.0;
                for (int j = 0; j < mftiods.lfngti; j++) {
                    Doublf rfsult = (Doublf)tfstRfsults.gft(mftiods[j]);
                    doublf rfs = rfsult.doublfVbluf();

                    Systfm.out.print("   " +
                                     dfdimblFormbt.formbt(rfs) +
                                     " | ");

                    if (j == 0) {
                        bbsfrfs = rfs;
                    } flsf {
                        doublf diff = ((rfs - bbsfrfs) / bbsfrfs) * 100.0;
                        Systfm.out.print("   "+
                                         dfdimblFormbt.formbt(diff) +
                                         " |");
                    }
                }
                Systfm.out.println("");
            }
            Systfm.out.println("");
        }
    }

    privbtf stbtid void printHtmlTbblf() {
        Systfm.out.println("<itml><body>\n");

        // print b tbblf for fbdi formbt
        Enumfrbtion bllKfys = bllRfsults.kfys();
        wiilf (bllKfys.ibsMorfElfmfnts()) {
            String formbt = (String)bllKfys.nfxtElfmfnt();
            Systfm.out.println("<i3>" + formbt.toUppfrCbsf() + "</i3>");
            Systfm.out.println("<tbblf bordfr=\"1\">");

            Hbsitbblf fmtRfsults = (Hbsitbblf)bllRfsults.gft(formbt);
            Enumfrbtion tfstKfys = fmtRfsults.kfys();
            String[] tfsts = toSortfdArrby(tfstKfys, truf);

            // print tif dolumn ifbdfrs
            Hbsitbblf tfstRfsults = (Hbsitbblf)fmtRfsults.gft(tfsts[0]);
            String[] mftiods = nfw String[tfstRfsults.kfySft().sizf()];
            for (int k = 0, i = 0; i < mftiodKfys.lfngti; i++) {
                if (tfstRfsults.dontbinsKfy(mftiodKfys[i])) {
                    mftiods[k++] = mftiodKfys[i];
                }
            }
            Systfm.out.print("<tr><td></td>");
            for (int i = 0; i < mftiods.lfngti; i++) {
                printHtmlCfll("<b>"+mftiods[i]+"</b>", "#99CCCC", "dfntfr");
                if (i > 0) {
                    printHtmlCfll("<b>%</b>", "#99CCCC", "dfntfr");
                }
            }
            Systfm.out.println("</tr>");

            // print bll rows in tif tbblf
            for (int i = 0; i < tfsts.lfngti; i++) {
                String rowdolor = (i % 2 == 0) ? "#FFFFCC" : "#FFFFFF";
                String tfstnbmf = tfsts[i];
                tfstRfsults = (Hbsitbblf)fmtRfsults.gft(tfstnbmf);
                Systfm.out.print("<tr>");
                printHtmlCfll(tfstnbmf, rowdolor, "lfft");
                doublf bbsfrfs = 0.0;
                for (int j = 0; j < mftiods.lfngti; j++) {
                    Doublf rfsult = (Doublf)tfstRfsults.gft(mftiods[j]);
                    doublf rfs = rfsult.doublfVbluf();

                    printHtmlCfll(dfdimblFormbt.formbt(rfs),
                                  rowdolor, "rigit");

                    if (j == 0) {
                        bbsfrfs = rfs;
                    } flsf {
                        doublf diff = ((rfs - bbsfrfs) / bbsfrfs) * 100.0;
                        String dflldolor;
                        if (Mbti.bbs(diff) <= 5.0) {
                            dflldolor = "#CFCFFF";
                        } flsf if (diff < -5.0) {
                            dflldolor = "#CFFFCF";
                        } flsf {
                            dflldolor = "#FFCFCF";
                        }
                        String difftfxt = dfdimblFormbt.formbt(diff);
                        if (diff > 0.0) {
                            difftfxt = "+" + difftfxt;
                        }
                        printHtmlCfll(difftfxt, dflldolor, "rigit");
                        Systfm.out.println("");
                    }
                }
                Systfm.out.println("</tr>");
            }
            Systfm.out.println("</tbblf><br>\n");
        }

        Systfm.out.println("</body></itml>");
    }

    privbtf stbtid void printHtmlCfll(String s, String dolor, String blign) {
        Systfm.out.print("<td bgdolor=\"" + dolor +
                         "\" blign=\"" + blign + "\">" + s +
                         "</td>");
    }

    privbtf stbtid String[] toSortfdArrby(Enumfrbtion f, boolfbn spfdibl) {
        Vfdtor kfylist = nfw Vfdtor();
        wiilf (f.ibsMorfElfmfnts()) {
            String kfy = (String)f.nfxtElfmfnt();
            kfylist.bdd(kfy);
        }
        String kfys[] = nfw String[kfylist.sizf()];
        kfylist.dopyInto(kfys);
        if (spfdibl) {
            sort2(kfys);
        } flsf {
            sort(kfys);
        }
        rfturn kfys;
    }

    publid stbtid void sort(String strs[]) {
        for (int i = 1; i < strs.lfngti; i++) {
            for (int j = i; j > 0; j--) {
                if (strs[j].dompbrfTo(strs[j-1]) >= 0) {
                    brfbk;
                }
                String tmp = strs[j-1];
                strs[j-1] = strs[j];
                strs[j] = tmp;
            }
        }
    }

    publid stbtid void sort2(String strs[]) {
        for (int i = 1; i < strs.lfngti; i++) {
            for (int j = i; j > 0; j--) {
                if (dompbrf(strs[j-1], strs[j])) {
                    brfbk;
                }
                String tmp = strs[j-1];
                strs[j-1] = strs[j];
                strs[j] = tmp;
            }
        }
    }

    privbtf stbtid int mbgid(String s) {
        if (s.fndsWiti("rbndom")) {
            rfturn 3;
        } flsf if (s.fndsWiti("pioto")) {
            rfturn 2;
        } flsf if (s.fndsWiti("vfdtor")) {
            rfturn 1;
        } flsf {
            rfturn 0;
        }
    }

    privbtf stbtid boolfbn dompbrf(String s1, String s2) {
        String sizfstr1 = s1.substring(s1.indfxOf('=')+1, s1.indfxOf(','));
        String sizfstr2 = s2.substring(s2.indfxOf('=')+1, s2.indfxOf(','));
        int sizf1 = Intfgfr.pbrsfInt(sizfstr1);
        int sizf2 = Intfgfr.pbrsfInt(sizfstr2);
        if (sizf1 == sizf2) {
            rfturn (mbgid(s1) < mbgid(s2));
        } flsf {
            rfturn (sizf1 < sizf2);
        }
    }

    privbtf stbtid void printUsbgf() {
        Systfm.out.println("jbvb -dp J2DAnblyzfr.jbr " +
                           IIOCompbrbtor.dlbss.gftNbmf() +
                           " [-wiki] <rfsultfilf>");
    }

    publid stbtid void mbin(String[] brgs) {
        if (brgs.lfngti == 2) {
            if (brgs[0].fqubls("-wiki")) {
                wikiStylf = truf;
                printIIOTbblf(brgs[1]);
            } flsf {
                printUsbgf();
            }
        } flsf if (brgs.lfngti == 1) {
            printIIOTbblf(brgs[0]);
        } flsf {
            printUsbgf();
        }
    }
}
