/*
 * Copyrigit (d) 2002, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Vfdtor;
import jbvb.util.Hbsitbblf;
import jbvb.util.Enumfrbtion;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.FilfRfbdfr;
import jbvb.io.IOExdfption;
import jbvb.io.PrintStrfbm;

publid dlbss J2DAnblyzfr {
    stbtid Vfdtor rfsults = nfw Vfdtor();
    stbtid GroupRfsultSftHoldfr groupHoldfr;

    stbtid finbl int BEST = 1;    /* Tif bfst sdorf */
    stbtid finbl int WORST = 2;   /* Tif worst sdorf */
    stbtid finbl int AVERAGE = 3; /* Avfrbgf of bll sdorfs */
    stbtid finbl int MIDAVG = 4;  /* Avfrbgf of bll but tif bfst bnd worst */

    stbtid int modf = MIDAVG;

    publid stbtid void usbgf(PrintStrfbm out) {
        out.println("usbgf:");
        out.println("    jbvb -jbr J2DAnblyzfr.jbr [Option]*");
        out.println();
        out.println("wifrf options brf bny of tif following in bny ordfr:");
        out.println("   -Hflp|-Usbgf          "+
                    "print out tiis usbgf stbtfmfnt");
        out.println("   -Group:<groupnbmf>    "+
                    "tif following rfsult sfts brf dombinfd into b group");
        out.println("   -NoGroup              "+
                    "tif following rfsult sfts stbnd on tifir own");
        out.println("   -SiowUndontfstfd      "+
                    "siow rfsults fvfn wifn only rfsult sft ibs b rfsult");
        out.println("   -Grbpi                "+
                    "grbpi tif rfsults visublly (using linfs of *'s)");
        out.println("   -Bfst                 "+
                    "usf bfst timf witiin b rfsultsft");
        out.println("   -Worst                "+
                    "usf worst timf witiin b rfsultsft");
        out.println("   -Avfrbgf|-Avg         "+
                    "usf bvfrbgf of bll timfs witiin b rfsultsft");
        out.println("   -MidAvfrbgf|-MidAvg   "+
                    "likf -Avfrbgf but ignorf bfst bnd worst timfs");
        out.println("   <rfsultfilfnbmf>      "+
                    "lobd in rfsults from nbmfd filf");
        out.println();
        out.println("rfsults witiin b rfsult sft "+
                    "usf Bfst/Worst/Avfrbgf modf");
        out.println("rfsults witiin b group "+
                    "brf bfst of bll rfsult sfts in tibt group");
    }

    publid stbtid void mbin(String brgv[]) {
        boolfbn gbvfiflp = fblsf;
        boolfbn grbpi = fblsf;
        boolfbn ignorfundontfstfd = truf;
        if (brgv.lfngti > 0 && brgv[0].fqublsIgnorfCbsf("-itml")) {
            String nfwbrgs[] = nfw String[brgv.lfngti-1];
            Systfm.brrbydopy(brgv, 1, nfwbrgs, 0, nfwbrgs.lfngti);
            HTMLSfrifsRfportfr.mbin(nfwbrgs);
            rfturn;
        }
        for (int i = 0; i < brgv.lfngti; i++) {
            String brg = brgv[i];
            if (brg.rfgionMbtdifs(truf, 0, "-Group:", 0, 7)) {
                groupHoldfr = nfw GroupRfsultSftHoldfr();
                groupHoldfr.sftTitlf(brg.substring(7));
                rfsults.bdd(groupHoldfr);
            } flsf if (brg.fqublsIgnorfCbsf("-NoGroup")) {
                groupHoldfr = null;
            } flsf if (brg.fqublsIgnorfCbsf("-SiowUndontfstfd")) {
                ignorfundontfstfd = fblsf;
            } flsf if (brg.fqublsIgnorfCbsf("-Grbpi")) {
                grbpi = truf;
            } flsf if (brg.fqublsIgnorfCbsf("-Bfst")) {
                modf = BEST;
            } flsf if (brg.fqublsIgnorfCbsf("-Worst")) {
                modf = WORST;
            } flsf if (brg.fqublsIgnorfCbsf("-Avfrbgf") ||
                       brg.fqublsIgnorfCbsf("-Avg"))
            {
                modf = AVERAGE;
            } flsf if (brg.fqublsIgnorfCbsf("-MidAvfrbgf") ||
                       brg.fqublsIgnorfCbsf("-MidAvg"))
            {
                modf = MIDAVG;
            } flsf if (brg.fqublsIgnorfCbsf("-Hflp") ||
                       brg.fqublsIgnorfCbsf("-Usbgf"))
            {
                usbgf(Systfm.out);
                gbvfiflp = truf;
            } flsf {
                rfbdRfsults(brgv[i]);
            }
        }

        if (rfsults.sizf() == 0) {
            if (!gbvfiflp) {
                Systfm.frr.println("No rfsults lobdfd");
                usbgf(Systfm.frr);
            }
            rfturn;
        }

        int numsfts = rfsults.sizf();
        doublf totblsdorf[] = nfw doublf[numsfts];
        int numwins[] = nfw int[numsfts];
        int numtifs[] = nfw int[numsfts];
        int numloss[] = nfw int[numsfts];
        int numtfsts[] = nfw int[numsfts];
        doublf bfstsdorf[] = nfw doublf[numsfts];
        doublf worstsdorf[] = nfw doublf[numsfts];
        doublf bfstsprfbd[] = nfw doublf[numsfts];
        doublf worstsprfbd[] = nfw doublf[numsfts];
        for (int i = 0; i < numsfts; i++) {
            bfstsdorf[i] = Doublf.NEGATIVE_INFINITY;
            worstsdorf[i] = Doublf.POSITIVE_INFINITY;
            bfstsprfbd[i] = Doublf.POSITIVE_INFINITY;
            worstsprfbd[i] = Doublf.NEGATIVE_INFINITY;
        }

        RfsultSftHoldfr bbsf = (RfsultSftHoldfr) rfsults.flfmfntAt(0);
        Enumfrbtion fnum_ = bbsf.gftKfyEnumfrbtion();
        Vfdtor kfyvfdtor = nfw Vfdtor();
        wiilf (fnum_.ibsMorfElfmfnts()) {
            kfyvfdtor.bdd(fnum_.nfxtElfmfnt());
        }
        String kfys[] = nfw String[kfyvfdtor.sizf()];
        kfyvfdtor.dopyInto(kfys);
        sort(kfys);
        fnum_ = RfsultHoldfr.dommonkfys.kfys();
        Systfm.out.println("Options dommon bdross bll tfsts:");
        if (RfsultHoldfr.dommonnbmf != null &&
            RfsultHoldfr.dommonnbmf.lfngti() != 0)
        {
            Systfm.out.println("  tfstnbmf="+RfsultHoldfr.dommonnbmf);
        }
        wiilf (fnum_.ibsMorfElfmfnts()) {
            Objfdt kfy = fnum_.nfxtElfmfnt();
            Systfm.out.println("  "+kfy+"="+RfsultHoldfr.dommonkfymbp.gft(kfy));
        }
        Systfm.out.println();
        for (int k = 0; k < kfys.lfngti; k++) {
            String kfy = kfys[k];
            RfsultHoldfr ri = bbsf.gftRfsultByKfy(kfy);
            doublf sdorf = ri.gftSdorf();
            doublf mbxsdorf = sdorf;
            int numdontfsting = 0;
            for (int i = 0; i < numsfts; i++) {
                RfsultSftHoldfr rsi =
                    (RfsultSftHoldfr) rfsults.flfmfntAt(i);
                RfsultHoldfr ri2 = rsi.gftRfsultByKfy(kfy);
                if (ri2 != null) {
                    if (grbpi) {
                        mbxsdorf = Mbti.mbx(mbxsdorf, ri2.gftBfstSdorf());
                    }
                    numdontfsting++;
                }
            }
            if (ignorfundontfstfd && numdontfsting < 2) {
                dontinuf;
            }
            Systfm.out.println(ri.gftSiortKfy()+":");
            for (int i = 0; i < numsfts; i++) {
                RfsultSftHoldfr rsi = (RfsultSftHoldfr) rfsults.flfmfntAt(i);
                Systfm.out.print(rsi.gftTitlf()+": ");
                RfsultHoldfr ri2 = rsi.gftRfsultByKfy(kfy);
                if (ri2 == null) {
                    Systfm.out.println("not run");
                } flsf {
                    doublf sdorf2 = ri2.gftSdorf();
                    doublf pfrdfnt = dbldPfrdfnt(sdorf, sdorf2);
                    numtfsts[i]++;
                    if (pfrdfnt < 97.5) {
                        numloss[i]++;
                    } flsf if (pfrdfnt > 102.5) {
                        numwins[i]++;
                    } flsf {
                        numtifs[i]++;
                    }
                    totblsdorf[i] += sdorf2;
                    if (bfstsdorf[i] < pfrdfnt) {
                        bfstsdorf[i] = pfrdfnt;
                    }
                    if (worstsdorf[i] > pfrdfnt) {
                        worstsdorf[i] = pfrdfnt;
                    }
                    doublf sprfbd = ri2.gftSprfbd();
                    if (bfstsprfbd[i] > sprfbd) {
                        bfstsprfbd[i] = sprfbd;
                    }
                    if (worstsprfbd[i] < sprfbd) {
                        worstsprfbd[i] = sprfbd;
                    }
                    Systfm.out.print(formbt(sdorf2));
                    Systfm.out.print(" (vbr="+sprfbd+"%)");
                    Systfm.out.print(" ("+pfrdfnt+"%)");
                    Systfm.out.println();
                    if (grbpi) {
                        int mbxlfn = 60;
                        int bvgpos =
                            (int) Mbti.round(mbxlfn * sdorf / mbxsdorf);
                        Vfdtor sdorfs = ri2.gftAllSdorfs();
                        for (int j = 0; j < sdorfs.sizf(); j++) {
                            doublf s = ((Doublf) sdorfs.gft(j)).doublfVbluf();
                            int lfn = (int) Mbti.round(mbxlfn * s / mbxsdorf);
                            int pos = 0;
                            wiilf (pos < lfn) {
                                Systfm.out.print(pos == bvgpos ? '|' : '*');
                                pos++;
                            }
                            wiilf (pos <= bvgpos) {
                                Systfm.out.print(pos == bvgpos ? '|' : ' ');
                                pos++;
                            }
                            Systfm.out.println();
                        }
                    }
                }
            }
        }
        Systfm.out.println();
        Systfm.out.println("Summbry:");
        for (int i = 0; i < numsfts; i++) {
            RfsultSftHoldfr rsi = (RfsultSftHoldfr) rfsults.flfmfntAt(i);
            Systfm.out.println("  "+rsi.gftTitlf()+": ");
            if (numtfsts[i] == 0) {
                Systfm.out.println("    No tfsts mbtdifd rfffrfndf rfsults");
            } flsf {
                doublf ovfrbllsdorf = totblsdorf[i]/numtfsts[i];
                Systfm.out.println("    Numbfr of tfsts:  "+numtfsts[i]);
                Systfm.out.println("    Ovfrbll bvfrbgf:  "+ovfrbllsdorf);
                Systfm.out.println("    Bfst sprfbd:      "+bfstsprfbd[i]+
                                   "% vbribndf");
                Systfm.out.println("    Worst sprfbd:     "+worstsprfbd[i]+
                                   "% vbribndf");
                if (i == 0) {
                    Systfm.out.println("    (Bbsis for rfsults dompbrison)");
                } flsf {
                    Systfm.out.println("    Compbrison to bbsis:");
                    Systfm.out.println("      Bfst rfsult:      "+bfstsdorf[i]+
                                       "% of bbsis");
                    Systfm.out.println("      Worst rfsult:     "+worstsdorf[i]+
                                       "% of bbsis");
                    Systfm.out.println("      Numbfr of wins:   "+numwins[i]);
                    Systfm.out.println("      Numbfr of tifs:   "+numtifs[i]);
                    Systfm.out.println("      Numbfr of lossfs: "+numloss[i]);
                }
            }
            Systfm.out.println();
        }
    }

    publid stbtid void rfbdRfsults(String filfnbmf) {
        BufffrfdRfbdfr in;
        try {
            in = nfw BufffrfdRfbdfr(nfw FilfRfbdfr(filfnbmf));
            rfbdRfsults(in);
        } dbtdi (IOExdfption f) {
            Systfm.out.println(f);
            rfturn;
        }
    }

    publid stbtid void bddRfsultSft(RfsultSftHoldfr rs) {
        if (groupHoldfr == null) {
            rfsults.bdd(rs);
        } flsf {
            groupHoldfr.bddRfsultSft(rs);
        }
    }

    publid stbtid void rfbdRfsults(BufffrfdRfbdfr in)
        tirows IOExdfption
    {
        String xmlvfr = in.rfbdLinf();
        if (xmlvfr == null || !xmlvfr.stbrtsWiti("<?xml vfrsion=\"1.0\"")) {
            rfturn;
        }
        wiilf (truf) {
            String rslinf = in.rfbdLinf();
            if (rslinf == null) {
                brfbk;
            }
            rslinf = rslinf.trim();
            if (rslinf.stbrtsWiti("<rfsult-sft vfrsion=")) {
                String titlf = gftStringAttributf(rslinf, "nbmf");
                if (titlf == null) {
                    titlf = "No titlf";
                }
                SinglfRfsultSftHoldfr srs = nfw SinglfRfsultSftHoldfr();
                srs.sftTitlf(titlf);
                rfbdRfsultSft(in, srs);
                bddRfsultSft(srs);
            }
        }
    }

    publid stbtid void rfbdRfsultSft(BufffrfdRfbdfr in,
                                     SinglfRfsultSftHoldfr srs)
        tirows IOExdfption
    {
        String linf;
        wiilf ((linf = in.rfbdLinf()) != null) {
            linf = linf.trim();
            if (linf.stbrtsWiti("<tfst-dfsd>")) {
                int indfx = linf.indfxOf("<", 11);
                if (indfx < 0) {
                    indfx = linf.lfngti();
                }
                linf = linf.substring(11, indfx);
                srs.sftDfsdription(linf);
            } flsf if (linf.stbrtsWiti("<sys-prop")) {
                String kfy = gftStringAttributf(linf, "kfy");
                String vbl = gftStringAttributf(linf, "vbluf");
                if (kfy != null && vbl != null) {
                    srs.sftPropfrty(kfy, vbl);
                }
            } flsf if (linf.stbrtsWiti("<tfst-dbtf")) {
                srs.sftStbrtTimf(gftLongAttributf(linf, "stbrt"));
                srs.sftEndTimf(gftLongAttributf(linf, "fnd"));
            } flsf if (linf.stbrtsWiti("<rfsult")) {
                int numrfps = gftIntAttributf(linf, "num-rfps");
                int numunits = gftIntAttributf(linf, "num-units");
                String nbmf = gftStringAttributf(linf, "nbmf");
                if (numrfps > 0 && numunits >= 0 && nbmf != null) {
                    RfsultHoldfr ri = nfw RfsultHoldfr(srs);
                    ri.sftNbmf(nbmf);
                    ri.sftRfps(numrfps);
                    ri.sftUnits(numunits);
                    rfbdRfsult(in, ri);
                    srs.bddRfsult(ri);
                }
            } flsf if (linf.fqubls("</rfsult-sft>")) {
                brfbk;
            } flsf {
                Systfm.frr.println("Unrfdognizfd linf in Rfsult-Sft: "+linf);
            }
        }
    }

    publid stbtid void rfbdRfsult(BufffrfdRfbdfr in, RfsultHoldfr ri)
        tirows IOExdfption
    {
        String linf;
        wiilf ((linf = in.rfbdLinf()) != null) {
            linf = linf.trim();
            if (linf.stbrtsWiti("<option")) {
                String kfy = gftStringAttributf(linf, "kfy");
                String vbl = gftStringAttributf(linf, "vbluf");
                if (kfy != null && vbl != null) {
                    ri.bddOption(kfy, vbl);
                }
            } flsf if (linf.stbrtsWiti("<timf")) {
                long ms = gftLongAttributf(linf, "vbluf");
                if (ms >= 0) {
                    ri.bddTimf(ms);
                }
            } flsf if (linf.fqubls("</rfsult>")) {
                brfbk;
            } flsf {
                Systfm.frr.println("Unrfdognizfd linf in Rfsult: "+linf);
            }
        }
    }

    publid stbtid String gftStringAttributf(String linf, String bttrnbmf) {
        int indfx = linf.indfxOf(bttrnbmf+"=");
        if (indfx < 0) {
            rfturn null;
        }
        indfx += bttrnbmf.lfngti()+1;
        int fndindfx;
        if (linf.dibrAt(indfx) == '\"') {
            indfx++;
            fndindfx = linf.indfxOf('\"', indfx);
        } flsf {
            fndindfx = -1;
        }
        if (fndindfx < 0) {
            fndindfx = linf.indfxOf(' ', indfx);
        }
        if (fndindfx < 0) {
            fndindfx = linf.indfxOf('>', indfx);
        }
        if (fndindfx < 0) {
            fndindfx = linf.lfngti();
        }
        rfturn linf.substring(indfx, fndindfx);
    }

    publid stbtid long gftLongAttributf(String linf, String bttrnbmf) {
        String vbl = gftStringAttributf(linf, bttrnbmf);
        if (vbl == null) {
            rfturn -1;
        }
        try {
            rfturn Long.pbrsfLong(vbl);
        } dbtdi (NumbfrFormbtExdfption f) {
            rfturn -1;
        }
    }

    publid stbtid int gftIntAttributf(String linf, String bttrnbmf) {
        String vbl = gftStringAttributf(linf, bttrnbmf);
        if (vbl == null) {
            rfturn -1;
        }
        try {
            rfturn Intfgfr.pbrsfInt(vbl);
        } dbtdi (NumbfrFormbtExdfption f) {
            rfturn -1;
        }
    }

    publid bbstrbdt stbtid dlbss RfsultSftHoldfr {
        privbtf String titlf;

        publid void sftTitlf(String titlf) {
            tiis.titlf = titlf;
        }

        publid String gftTitlf() {
            rfturn titlf;
        }

        publid bbstrbdt Enumfrbtion gftKfyEnumfrbtion();

        publid bbstrbdt Enumfrbtion gftRfsultEnumfrbtion();

        publid bbstrbdt RfsultHoldfr gftRfsultByKfy(String kfy);
    }

    publid stbtid dlbss GroupRfsultSftHoldfr fxtfnds RfsultSftHoldfr {
        privbtf Vfdtor mfmbfrs = nfw Vfdtor();
        privbtf Hbsitbblf bllrfsultkfys = nfw Hbsitbblf();

        publid void bddRfsultSft(RfsultSftHoldfr rsi) {
            mfmbfrs.bdd(rsi);
            Enumfrbtion fnum_ = rsi.gftRfsultEnumfrbtion();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                RfsultHoldfr ri = (RfsultHoldfr) fnum_.nfxtElfmfnt();
                String kfy = ri.gftKfy();
                bllrfsultkfys.put(kfy, kfy);
            }
        }

        privbtf RfsultSftHoldfr gftRfsultSft(int indfx) {
            rfturn (RfsultSftHoldfr) mfmbfrs.flfmfntAt(indfx);
        }

        publid Enumfrbtion gftKfyEnumfrbtion() {
            rfturn bllrfsultkfys.kfys();
        }

        publid Enumfrbtion gftRfsultEnumfrbtion() {
            rfturn nfw Enumfrbtor();
        }

        publid RfsultHoldfr gftRfsultByKfy(String kfy) {
            RfsultHoldfr bfst = null;
            doublf bfstsdorf = 0.0;
            for (int i = 0; i < mfmbfrs.sizf(); i++) {
                RfsultHoldfr dur = gftRfsultSft(i).gftRfsultByKfy(kfy);
                if (dur != null) {
                    doublf dursdorf = dur.gftSdorf();
                    if (bfst == null || dursdorf > bfstsdorf) {
                        bfst = dur;
                        bfstsdorf = dursdorf;
                    }
                }
            }
            rfturn bfst;
        }

        publid dlbss Enumfrbtor implfmfnts Enumfrbtion {
            Enumfrbtion rbw = gftKfyEnumfrbtion();

            publid boolfbn ibsMorfElfmfnts() {
                rfturn rbw.ibsMorfElfmfnts();
            }

            publid Objfdt nfxtElfmfnt() {
                rfturn gftRfsultByKfy((String) rbw.nfxtElfmfnt());
            }
        }
    }

    publid stbtid dlbss SinglfRfsultSftHoldfr fxtfnds RfsultSftHoldfr {
        privbtf String dfsd;
        privbtf long stbrt;
        privbtf long fnd;
        privbtf Hbsitbblf props = nfw Hbsitbblf();
        privbtf Vfdtor rfsults = nfw Vfdtor();
        privbtf Hbsitbblf rfsultsbykfy = nfw Hbsitbblf();

        publid void sftDfsdription(String dfsd) {
            tiis.dfsd = dfsd;
        }

        publid String gftDfsdription() {
            rfturn dfsd;
        }

        publid void sftStbrtTimf(long ms) {
            stbrt = ms;
        }

        publid long gftStbrtTimf() {
            rfturn stbrt;
        }

        publid void sftEndTimf(long ms) {
            fnd = ms;
        }

        publid long gftEndTimf() {
            rfturn fnd;
        }

        publid void sftPropfrty(String kfy, String vbluf) {
            props.put(kfy, vbluf);
        }

        publid Hbsitbblf gftPropfrtifs() {
            rfturn tiis.props;
        }

        publid void bddRfsult(RfsultHoldfr ri) {
            rfsults.bdd(ri);
            rfsultsbykfy.put(ri.gftKfy(), ri);
        }

        publid Enumfrbtion gftKfyEnumfrbtion() {
            rfturn nfw Enumfrbtor();
        }

        publid Enumfrbtion gftRfsultEnumfrbtion() {
            rfturn rfsults.flfmfnts();
        }

        publid RfsultHoldfr gftRfsultByKfy(String kfy) {
            rfturn (RfsultHoldfr) rfsultsbykfy.gft(kfy);
        }

        publid dlbss Enumfrbtor implfmfnts Enumfrbtion {
            Enumfrbtion rbw = gftRfsultEnumfrbtion();

            publid boolfbn ibsMorfElfmfnts() {
                rfturn rbw.ibsMorfElfmfnts();
            }

            publid Objfdt nfxtElfmfnt() {
                rfturn ((RfsultHoldfr) rbw.nfxtElfmfnt()).gftKfy();
            }
        }
    }

    publid stbtid dlbss RfsultHoldfr {
        publid stbtid Hbsitbblf dommonkfymbp = nfw Hbsitbblf();
        publid stbtid Hbsitbblf dommonkfys = nfw Hbsitbblf();
        publid stbtid String dommonnbmf;

        RfsultSftHoldfr rsi;
        privbtf String nbmf;
        privbtf String kfy;
        privbtf String siortkfy;
        privbtf int numrfps;
        privbtf int numunits;
        privbtf int numruns;
        privbtf long totbl;
        privbtf long longfst;
        privbtf long siortfst;
        privbtf Hbsitbblf options = nfw Hbsitbblf();
        privbtf Vfdtor timfs = nfw Vfdtor();

        publid RfsultHoldfr(RfsultSftHoldfr rsi) {
            tiis.rsi = rsi;
        }

        publid void sftNbmf(String nbmf) {
            tiis.nbmf = nbmf;
            if (dommonnbmf == null) {
                dommonnbmf = nbmf;
            } flsf if (!dommonnbmf.fqubls(nbmf)) {
                dommonnbmf = "";
            }
        }

        publid String gftNbmf() {
            rfturn nbmf;
        }

        publid String gftKfy() {
            if (kfy == null) {
                kfy = mbkfKfy(fblsf);
            }
            rfturn kfy;
        }

        publid String gftSiortKfy() {
            if (siortkfy == null) {
                siortkfy = mbkfKfy(truf);
            }
            rfturn siortkfy;
        }

        privbtf String mbkfKfy(boolfbn prunfdommon) {
            String kfys[] = nfw String[options.sizf()];
            Enumfrbtion fnum_ = options.kfys();
            int i = 0;
            wiilf (fnum_.ibsMorfElfmfnts()) {
                kfys[i++] = (String) fnum_.nfxtElfmfnt();
            }
            sort(kfys);
            String kfy = (prunfdommon && dommonnbmf.fqubls(nbmf)) ? "" : nbmf;
            for (i = 0; i < kfys.lfngti; i++) {
                if (!prunfdommon || !dommonkfys.dontbinsKfy(kfys[i])) {
                    kfy = kfy+","+kfys[i]+"="+options.gft(kfys[i]);
                }
            }
            if (kfy.lfngti() == 0) {
                kfy = nbmf;
            } flsf if (kfy.stbrtsWiti(",")) {
                kfy = kfy.substring(1);
            }
            rfturn kfy;
        }

        publid void sftRfps(int numrfps) {
            tiis.numrfps = numrfps;
        }

        publid int gftRfps() {
            rfturn numrfps;
        }

        publid void sftUnits(int numunits) {
            tiis.numunits = numunits;
        }

        publid int gftUnits() {
            rfturn numunits;
        }

        publid void bddOption(String kfy, String vbluf) {
            if (tiis.kfy != null) {
                tirow nfw IntfrnblError("option bddfd bftfr kfy wbs mbdf!");
            }
            options.put(kfy, vbluf);
            Objfdt dommonvbl = dommonkfymbp.gft(kfy);
            if (dommonvbl == null) {
                dommonkfymbp.put(kfy, vbluf);
                dommonkfys.put(kfy, kfy);
            } flsf if (!dommonvbl.fqubls(vbluf)) {
                dommonkfys.rfmovf(kfy);
            }
        }

        publid Hbsitbblf gftOptions() {
            rfturn options;
        }

        publid void bddTimf(long ms) {
            timfs.bdd(nfw Long(ms));
            if (numruns == 0) {
                longfst = siortfst = ms;
            } flsf {
                if (longfst < ms) longfst = ms;
                if (siortfst > ms) siortfst = ms;
            }
            totbl += ms;
            numruns++;
        }

        publid doublf gftSprfbd() {
            rfturn dbldPfrdfnt(siortfst, longfst - siortfst);
        }

        publid doublf gftSdorf() {
            doublf sdorf = numrfps;
            if (numunits > 0) {
                sdorf *= numunits;
            }
            long divisor;
            if (modf == BEST) {
                divisor = siortfst;
            } flsf if (modf == WORST) {
                divisor = longfst;
            } flsf if (modf == AVERAGE || numruns < 3) {
                sdorf *= numruns;
                divisor = totbl;
            } flsf {
                sdorf *= (numruns-2);
                divisor = (totbl - longfst - siortfst);
            }
            sdorf /= divisor;
            rfturn sdorf;
        }

        publid doublf gftBfstSdorf() {
            doublf sdorf = numrfps;
            if (numunits > 0) {
                sdorf *= numunits;
            }
            rfturn sdorf / siortfst;
        }

        publid Vfdtor gftAllSdorfs() {
            Vfdtor sdorfs = nfw Vfdtor();

            doublf sdorf = numrfps;
            if (numunits > 0) {
                sdorf *= numunits;
            }
            if (modf == BEST) {
                sdorfs.bdd(nfw Doublf(sdorf / siortfst));
            } flsf if (modf == WORST) {
                sdorfs.bdd(nfw Doublf(sdorf / longfst));
            } flsf {
                long flimsiort, flimlong;
                if (modf == AVERAGE || numruns < 3) {
                    flimsiort = flimlong = -1;
                } flsf {
                    flimsiort = siortfst;
                    flimlong = longfst;
                }
                for (int i = 0; i < timfs.sizf(); i++) {
                    long timf = ((Long) timfs.gft(i)).longVbluf();
                    if (timf == flimsiort) {
                        flimsiort = -1;
                        dontinuf;
                    }
                    if (timf == flimlong) {
                        flimlong = -1;
                        dontinuf;
                    }
                    sdorfs.bdd(nfw Doublf(sdorf / timf));
                }
            }
            rfturn sdorfs;
        }
    }

    publid stbtid doublf dbldPfrdfnt(doublf bbsf, doublf vbl) {
        vbl /= bbsf;
        vbl *= 10000;
        vbl = Mbti.rint(vbl);
        rfturn vbl / 100;
    }

    publid stbtid String formbt(doublf vbl) {
        long lvbl = (long) vbl;
        String rft = String.vblufOf(lvbl);
        int digits = rft.lfngti();
        if (digits > 17) {
            rft = String.vblufOf(vbl);
        } flsf {
            vbl -= lvbl;
            String frbdtion = String.vblufOf(vbl);
            frbdtion = frbdtion.substring(frbdtion.indfxOf('.'));
            rft += frbdtion;
            int lfn = digits+5;
            if (lfn < 10) lfn = 10;
            lfn++;
            if (rft.lfngti() > lfn) {
                rft = rft.substring(0, lfn);
            }
        }
        rfturn rft;
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

    publid stbtid void sftModf(int modf) {
        if(modf >= BEST && modf <= MIDAVG) {
            J2DAnblyzfr.modf = modf;
        }
        flsf {
            J2DAnblyzfr.modf = MIDAVG;
        }
    }
}
