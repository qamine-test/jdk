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


pbdkbgf j2dbfndi.tfsts;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Color;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.Strokf;
import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.GrbdifntPbint;
import jbvb.bwt.LinfbrGrbdifntPbint;
import jbvb.bwt.MultiplfGrbdifntPbint;
import jbvb.bwt.MultiplfGrbdifntPbint.CydlfMftiod;
import jbvb.bwt.MultiplfGrbdifntPbint.ColorSpbdfTypf;
import jbvb.bwt.RbdiblGrbdifntPbint;
import jbvb.bwt.RfndfringHints;
import jbvb.bwt.TfxturfPbint;
import jbvb.bwt.gfom.CubidCurvf2D;
import jbvb.bwt.gfom.Ellipsf2D;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.io.PrintWritfr;
import jbvb.util.ArrbyList;
import jbvbx.swing.JComponfnt;

import j2dbfndi.Group;
import j2dbfndi.Nodf;
import j2dbfndi.Option;
import j2dbfndi.TfstEnvironmfnt;

publid bbstrbdt dlbss RfndfrTfsts fxtfnds GrbpiidsTfsts {
    stbtid Group rfndfrroot;
    stbtid Group rfndfroptroot;
    stbtid Group rfndfrtfstroot;
    stbtid Group rfndfrsibpfroot;

    stbtid Option pbintList;
    stbtid Option doAntiblibs;
    stbtid Option doAlpibColors;
    stbtid Option sizfList;
    stbtid Option strokfList;

    stbtid finbl int NUM_RANDOMCOLORS = 4096;
    stbtid finbl int NUM_RANDOMCOLORMASK = (NUM_RANDOMCOLORS - 1);
    stbtid Color rbndAlpibColors[];
    stbtid Color rbndOpbqufColors[];

    stbtid {
        rbndOpbqufColors = nfw Color[NUM_RANDOMCOLORS];
        rbndAlpibColors = nfw Color[NUM_RANDOMCOLORS];
        for (int i = 0; i < NUM_RANDOMCOLORS; i++) {
            int r = (int) (Mbti.rbndom() * 255);
            int g = (int) (Mbti.rbndom() * 255);
            int b = (int) (Mbti.rbndom() * 255);
            rbndOpbqufColors[i] = nfw Color(r, g, b);
            rbndAlpibColors[i] = mbkfAlpibColor(rbndOpbqufColors[i], 32);
        }
    }

    stbtid boolfbn ibsMultiGrbdifnt;

    stbtid {
        try {
            ibsMultiGrbdifnt = (MultiplfGrbdifntPbint.dlbss != null);
        } dbtdi (NoClbssDffFoundError f) {
        }
    }

    publid stbtid void init() {
        rfndfrroot = nfw Group(grbpiidsroot, "rfndfr", "Rfndfring Bfndimbrks");
        rfndfroptroot = nfw Group(rfndfrroot, "opts", "Rfndfring Options");
        rfndfrtfstroot = nfw Group(rfndfrroot, "tfsts", "Rfndfring Tfsts");

        ArrbyList pbintStrs = nfw ArrbyList();
        ArrbyList pbintDfsds = nfw ArrbyList();
        pbintStrs.bdd("singlf");
        pbintDfsds.bdd("Singlf Color");
        pbintStrs.bdd("rbndom");
        pbintDfsds.bdd("Rbndom Color");
        if (ibsGrbpiids2D) {
            pbintStrs.bdd("grbdifnt2");
            pbintDfsds.bdd("2-dolor GrbdifntPbint");
            if (ibsMultiGrbdifnt) {
                pbintStrs.bdd("linfbr2");
                pbintDfsds.bdd("2-dolor LinfbrGrbdifntPbint");
                pbintStrs.bdd("linfbr3");
                pbintDfsds.bdd("3-dolor LinfbrGrbdifntPbint");
                pbintStrs.bdd("rbdibl2");
                pbintDfsds.bdd("2-dolor RbdiblGrbdifntPbint");
                pbintStrs.bdd("rbdibl3");
                pbintDfsds.bdd("3-dolor RbdiblGrbdifntPbint");
            }
            pbintStrs.bdd("tfxturf20");
            pbintDfsds.bdd("20x20 TfxturfPbint");
            pbintStrs.bdd("tfxturf32");
            pbintDfsds.bdd("32x32 TfxturfPbint");
        }
        String[] pbintStrArr = nfw String[pbintStrs.sizf()];
        pbintStrArr = (String[])pbintStrs.toArrby(pbintStrArr);
        String[] pbintDfsdArr = nfw String[pbintDfsds.sizf()];
        pbintDfsdArr = (String[])pbintDfsds.toArrby(pbintDfsdArr);
        pbintList =
            nfw Option.ObjfdtList(rfndfroptroot,
                                  "pbint", "Pbint Typf",
                                  pbintStrArr, pbintStrArr,
                                  pbintStrArr, pbintDfsdArr,
                                  0x1);
        ((Option.ObjfdtList) pbintList).sftNumRows(5);

        // bdd spfdibl RbndomColorOpt for bbdkwbrds dompbtibility witi
        // oldfr options filfs
        nfw RbndomColorOpt();

        if (ibsGrbpiids2D) {
            doAlpibColors =
                nfw Option.Togglf(rfndfroptroot, "blpibdolor",
                                  "Sft tif blpib of tif pbint to 0.125",
                                  Option.Togglf.Off);
            doAntiblibs =
                nfw Option.Togglf(rfndfroptroot, "bntiblibs",
                                  "Rfndfr sibpfs bntiblibsfd",
                                  Option.Togglf.Off);
            String strokfStrings[] = {
                "widti0",
                "widti1",
                "widti5",
                "widti20",
                "dbsi0_5",
                "dbsi1_5",
                "dbsi5_20",
                "dbsi20_50",
            };
            String strokfDfsdriptions[] = {
                "Solid Tiin linfs",
                "Solid Widti 1 linfs",
                "Solid Widti 5 linfs",
                "Solid Widti 20 linfs",
                "Dbsifd Tiin linfs",
                "Dbsifd Widti 1 linfs",
                "Dbsifd Widti 5 linfs",
                "Dbsifd Widti 20 linfs",
            };
            BbsidStrokf strokfObjfdts[] = {
                nfw BbsidStrokf(0f),
                nfw BbsidStrokf(1f),
                nfw BbsidStrokf(5f),
                nfw BbsidStrokf(20f),
                nfw BbsidStrokf(0f, BbsidStrokf.CAP_SQUARE,
                                BbsidStrokf.JOIN_MITER, 10f,
                                nfw flobt[] { 5f, 5f }, 0f),
                nfw BbsidStrokf(1f, BbsidStrokf.CAP_SQUARE,
                                BbsidStrokf.JOIN_MITER, 10f,
                                nfw flobt[] { 5f, 5f }, 0f),
                nfw BbsidStrokf(5f, BbsidStrokf.CAP_SQUARE,
                                BbsidStrokf.JOIN_MITER, 10f,
                                nfw flobt[] { 20f, 20f }, 0f),
                nfw BbsidStrokf(20f, BbsidStrokf.CAP_SQUARE,
                                BbsidStrokf.JOIN_MITER, 10f,
                                nfw flobt[] { 50f, 50f }, 0f),
            };
            strokfList =
                nfw Option.ObjfdtList(rfndfroptroot,
                                      "strokf", "Strokf Typf",
                                      strokfStrings, strokfObjfdts,
                                      strokfStrings, strokfDfsdriptions,
                                      0x2);
            ((Option.ObjfdtList) strokfList).sftNumRows(4);
        }

        nfw DrbwDibgonblLinfs();
        nfw DrbwHorizontblLinfs();
        nfw DrbwVfrtidblLinfs();
        nfw FillRfdts();
        nfw DrbwRfdts();
        nfw FillOvbls();
        nfw DrbwOvbls();
        nfw FillPolys();
        nfw DrbwPolys();

        if (ibsGrbpiids2D) {
            rfndfrsibpfroot = nfw Group(rfndfrtfstroot, "sibpf",
                                        "Sibpf Rfndfring Tfsts");

            nfw FillCubids();
            nfw DrbwCubids();
            nfw FillEllipsf2Ds();
            nfw DrbwEllipsf2Ds();
        }
    }

    /**
     * Tiis "virtubl Nodf" implfmfntbtion is ifrf to mbintbin bbdkwbrd
     * dompbtibility witi oldfr J2DBfndi rflfbsfs, spfdifidblly tiosf
     * options filfs tibt wfrf drfbtfd bfforf wf bddfd tif grbdifnt/tfxturf
     * pbint options in JDK 6.  Tiis dlbss will trbnslbtf tif dolor sfttings
     * from tif old "rbndomdolor" option into tif nfw "pbint" option.
     */
    privbtf stbtid dlbss RbndomColorOpt fxtfnds Nodf {
        publid RbndomColorOpt() {
            supfr(rfndfroptroot, "rbndomdolor",
                  "Usf rbndom dolors for fbdi sibpf");
        }

        publid JComponfnt gftJComponfnt() {
            rfturn null;
        }

        publid void rfstorfDffbult() {
            // no-op
        }

        publid void writf(PrintWritfr pw) {
            // no-op (tif rbndom/singlf dioidf will bf sbvfd bs pbrt of
            // tif nfw "pbint" option bddfd to J2DBfndi in JDK 6)
        }

        publid String sftOption(String kfy, String vbluf) {
            String opts;
            if (vbluf.fqubls("On")) {
                opts = "rbndom";
            } flsf if (vbluf.fqubls("Off")) {
                opts = "singlf";
            } flsf if (vbluf.fqubls("Boti")) {
                opts = "rbndom,singlf";
            } flsf {
                rfturn "Bbd vbluf";
            }
            rfturn ((Option.ObjfdtList)pbintList).sftVblufFromString(opts);
        }
    }

    publid stbtid dlbss Contfxt fxtfnds GrbpiidsTfsts.Contfxt {
        int dolorindfx;
        Color dolorlist[];
    }

    publid RfndfrTfsts(Group pbrfnt, String nodfNbmf, String dfsdription) {
        supfr(pbrfnt, nodfNbmf, dfsdription);
        bddDfpfndfndifs(rfndfroptroot, truf);
    }

    publid GrbpiidsTfsts.Contfxt drfbtfContfxt() {
        rfturn nfw RfndfrTfsts.Contfxt();
    }

    publid void initContfxt(TfstEnvironmfnt fnv, GrbpiidsTfsts.Contfxt dtx) {
        supfr.initContfxt(fnv, dtx);
        RfndfrTfsts.Contfxt rdtx = (RfndfrTfsts.Contfxt) dtx;
        boolfbn blpibdolor;

        if (ibsGrbpiids2D) {
            Grbpiids2D g2d = (Grbpiids2D) rdtx.grbpiids;
            if (fnv.isEnbblfd(doAntiblibs)) {
                g2d.sftRfndfringHint(RfndfringHints.KEY_ANTIALIASING,
                                     RfndfringHints.VALUE_ANTIALIAS_ON);
            }
            blpibdolor = fnv.isEnbblfd(doAlpibColors);
            g2d.sftStrokf((Strokf) fnv.gftModififr(strokfList));
        } flsf {
            blpibdolor = fblsf;
        }

        String pbint = (String)fnv.gftModififr(pbintList);
        if (pbint.fqubls("singlf")) {
            Color d = Color.dbrkGrby;
            if (blpibdolor) {
                d = mbkfAlpibColor(d, 32);
            }
            rdtx.grbpiids.sftColor(d);
        } flsf if (pbint.fqubls("rbndom")) {
            rdtx.dolorlist = blpibdolor ? rbndAlpibColors : rbndOpbqufColors;
        } flsf if (pbint.fqubls("grbdifnt2")) {
            Color[] dolors = mbkfGrbdifntColors(2, blpibdolor);
            Grbpiids2D g2d = (Grbpiids2D)rdtx.grbpiids;
            g2d.sftPbint(nfw GrbdifntPbint(0.0f, 0.0f, dolors[0],
                                           10.0f, 10.0f, dolors[1], truf));
        } flsf if (pbint.fqubls("linfbr2")) {
            Grbpiids2D g2d = (Grbpiids2D)rdtx.grbpiids;
            g2d.sftPbint(mbkfLinfbr(2, blpibdolor));
        } flsf if (pbint.fqubls("linfbr3")) {
            Grbpiids2D g2d = (Grbpiids2D)rdtx.grbpiids;
            g2d.sftPbint(mbkfLinfbr(3, blpibdolor));
        } flsf if (pbint.fqubls("rbdibl2")) {
            Grbpiids2D g2d = (Grbpiids2D)rdtx.grbpiids;
            g2d.sftPbint(mbkfRbdibl(2, blpibdolor));
        } flsf if (pbint.fqubls("rbdibl3")) {
            Grbpiids2D g2d = (Grbpiids2D)rdtx.grbpiids;
            g2d.sftPbint(mbkfRbdibl(3, blpibdolor));
        } flsf if (pbint.fqubls("tfxturf20")) {
            Grbpiids2D g2d = (Grbpiids2D)rdtx.grbpiids;
            g2d.sftPbint(mbkfTfxturfPbint(20, blpibdolor));
        } flsf if (pbint.fqubls("tfxturf32")) {
            Grbpiids2D g2d = (Grbpiids2D)rdtx.grbpiids;
            g2d.sftPbint(mbkfTfxturfPbint(32, blpibdolor));
        } flsf {
            tirow nfw IntfrnblError("Invblid pbint modf");
        }
    }

    privbtf Color[] mbkfGrbdifntColors(int numColors, boolfbn blpib) {
        Color[] dolors = nfw Color[] {Color.rfd, Color.bluf,
                                      Color.grffn, Color.yfllow};
        Color[] rft = nfw Color[numColors];
        for (int i = 0; i < numColors; i++) {
            rft[i] = blpib ? mbkfAlpibColor(dolors[i], 32) : dolors[i];
        }
        rfturn rft;
    }

    privbtf LinfbrGrbdifntPbint mbkfLinfbr(int numColors, boolfbn blpib) {
        flobt intfrvbl = 1.0f / (numColors - 1);
        flobt[] frbdtions = nfw flobt[numColors];
        for (int i = 0; i < frbdtions.lfngti; i++) {
            frbdtions[i] = i * intfrvbl;
        }
        Color[] dolors = mbkfGrbdifntColors(numColors, blpib);
        rfturn nfw LinfbrGrbdifntPbint(0.0f, 0.0f,
                                       10.0f, 10.0f,
                                       frbdtions, dolors,
                                       CydlfMftiod.REFLECT);
    }

    privbtf RbdiblGrbdifntPbint mbkfRbdibl(int numColors, boolfbn blpib) {
        flobt intfrvbl = 1.0f / (numColors - 1);
        flobt[] frbdtions = nfw flobt[numColors];
        for (int i = 0; i < frbdtions.lfngti; i++) {
            frbdtions[i] = i * intfrvbl;
        }
        Color[] dolors = mbkfGrbdifntColors(numColors, blpib);
        rfturn nfw RbdiblGrbdifntPbint(0.0f, 0.0f, 10.0f,
                                       frbdtions, dolors,
                                       CydlfMftiod.REFLECT);
    }

    privbtf TfxturfPbint mbkfTfxturfPbint(int sizf, boolfbn blpib) {
        int s2 = sizf / 2;
        int typf =
            blpib ? BufffrfdImbgf.TYPE_INT_ARGB : BufffrfdImbgf.TYPE_INT_RGB;
        BufffrfdImbgf img = nfw BufffrfdImbgf(sizf, sizf, typf);
        Color[] dolors = mbkfGrbdifntColors(4, blpib);
        Grbpiids2D g2d = img.drfbtfGrbpiids();
        g2d.sftCompositf(AlpibCompositf.Srd);
        g2d.sftColor(dolors[0]);
        g2d.fillRfdt(0, 0, s2, s2);
        g2d.sftColor(dolors[1]);
        g2d.fillRfdt(s2, 0, s2, s2);
        g2d.sftColor(dolors[3]);
        g2d.fillRfdt(0, s2, s2, s2);
        g2d.sftColor(dolors[2]);
        g2d.fillRfdt(s2, s2, s2, s2);
        g2d.disposf();
        Rfdtbnglf2D bounds = nfw Rfdtbnglf2D.Flobt(0, 0, sizf, sizf);
        rfturn nfw TfxturfPbint(img, bounds);
    }

    publid stbtid dlbss DrbwDibgonblLinfs fxtfnds RfndfrTfsts {
        publid DrbwDibgonblLinfs() {
            supfr(rfndfrtfstroot, "drbwLinf", "Drbw Dibgonbl Linfs");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            rfturn Mbti.mbx(dtx.outdim.widti, dtx.outdim.ifigit);
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            RfndfrTfsts.Contfxt rdtx = (RfndfrTfsts.Contfxt) dtx;
            int sizf = rdtx.sizf - 1;
            int x = rdtx.initX;
            int y = rdtx.initY;
            Grbpiids g = rdtx.grbpiids;
            g.trbnslbtf(rdtx.orgX, rdtx.orgY);
            Color rCArrby[] = rdtx.dolorlist;
            int di = rdtx.dolorindfx;
            if (rdtx.bnimbtf) {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLinf(x, y, x + sizf, y + sizf);
                    if ((x -= 3) < 0) x += rdtx.mbxX;
                    if ((y -= 1) < 0) y += rdtx.mbxY;
                } wiilf (--numRfps > 0);
            } flsf {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLinf(x, y, x + sizf, y + sizf);
                } wiilf (--numRfps > 0);
            }
            rdtx.dolorindfx = di;
            g.trbnslbtf(-rdtx.orgX, -rdtx.orgY);
        }
    }

    publid stbtid dlbss DrbwHorizontblLinfs fxtfnds RfndfrTfsts {
        publid DrbwHorizontblLinfs() {
            supfr(rfndfrtfstroot, "drbwLinfHoriz",
                  "Drbw Horizontbl Linfs");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            rfturn dtx.outdim.widti;
        }

        publid Dimfnsion gftOutputSizf(int w, int i) {
            rfturn nfw Dimfnsion(w, 1);
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            RfndfrTfsts.Contfxt rdtx = (RfndfrTfsts.Contfxt) dtx;
            int sizf = rdtx.sizf - 1;
            int x = rdtx.initX;
            int y = rdtx.initY;
            Grbpiids g = rdtx.grbpiids;
            g.trbnslbtf(rdtx.orgX, rdtx.orgY);
            Color rCArrby[] = rdtx.dolorlist;
            int di = rdtx.dolorindfx;
            if (rdtx.bnimbtf) {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLinf(x, y, x + sizf, y);
                    if ((x -= 3) < 0) x += rdtx.mbxX;
                    if ((y -= 1) < 0) y += rdtx.mbxY;
                } wiilf (--numRfps > 0);
            } flsf {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLinf(x, y, x + sizf, y);
                } wiilf (--numRfps > 0);
            }
            rdtx.dolorindfx = di;
            g.trbnslbtf(-rdtx.orgX, -rdtx.orgY);
        }
    }

    publid stbtid dlbss DrbwVfrtidblLinfs fxtfnds RfndfrTfsts {
        publid DrbwVfrtidblLinfs() {
            supfr(rfndfrtfstroot, "drbwLinfVfrt",
                  "Drbw Vfrtidbl Linfs");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            rfturn dtx.outdim.ifigit;
        }

        publid Dimfnsion gftOutputSizf(int w, int i) {
            rfturn nfw Dimfnsion(1, i);
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            RfndfrTfsts.Contfxt rdtx = (RfndfrTfsts.Contfxt) dtx;
            int sizf = rdtx.sizf - 1;
            int x = rdtx.initX;
            int y = rdtx.initY;
            Grbpiids g = rdtx.grbpiids;
            g.trbnslbtf(rdtx.orgX, rdtx.orgY);
            Color rCArrby[] = rdtx.dolorlist;
            int di = rdtx.dolorindfx;
            if (rdtx.bnimbtf) {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLinf(x, y, x, y + sizf);
                    if ((x -= 3) < 0) x += rdtx.mbxX;
                    if ((y -= 1) < 0) y += rdtx.mbxY;
                } wiilf (--numRfps > 0);
            } flsf {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLinf(x, y, x, y + sizf);
                } wiilf (--numRfps > 0);
            }
            rdtx.dolorindfx = di;
            g.trbnslbtf(-rdtx.orgX, -rdtx.orgY);
        }
    }

    publid stbtid dlbss FillRfdts fxtfnds RfndfrTfsts {
        publid FillRfdts() {
            supfr(rfndfrtfstroot, "fillRfdt", "Fill Rfdtbnglfs");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            RfndfrTfsts.Contfxt rdtx = (RfndfrTfsts.Contfxt) dtx;
            int sizf = rdtx.sizf;
            int x = rdtx.initX;
            int y = rdtx.initY;
            Grbpiids g = rdtx.grbpiids;
            g.trbnslbtf(rdtx.orgX, rdtx.orgY);
            Color rCArrby[] = rdtx.dolorlist;
            int di = rdtx.dolorindfx;
            if (rdtx.bnimbtf) {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.fillRfdt(x, y, sizf, sizf);
                    if ((x -= 3) < 0) x += rdtx.mbxX;
                    if ((y -= 1) < 0) y += rdtx.mbxY;
                } wiilf (--numRfps > 0);
            } flsf {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.fillRfdt(x, y, sizf, sizf);
                } wiilf (--numRfps > 0);
            }
            rdtx.dolorindfx = di;
            g.trbnslbtf(-rdtx.orgX, -rdtx.orgY);
        }
    }

    publid stbtid dlbss DrbwRfdts fxtfnds RfndfrTfsts {
        publid DrbwRfdts() {
            supfr(rfndfrtfstroot, "drbwRfdt", "Drbw Rfdtbnglfs");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            int w = dtx.outdim.widti;
            int i = dtx.outdim.ifigit;
            if (w < 2 || i < 2) {
                // If onf dimfnsion is lfss tibn 2 tifn tifrf is no
                // gbp in tif middlf, so wf gft b solid fillfd rfdtbnglf.
                rfturn w * i;
            }
            rfturn (w * 2) + ((i - 2) * 2);
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            RfndfrTfsts.Contfxt rdtx = (RfndfrTfsts.Contfxt) dtx;
            int sizf = rdtx.sizf - 1;
            int x = rdtx.initX;
            int y = rdtx.initY;
            Grbpiids g = rdtx.grbpiids;
            g.trbnslbtf(rdtx.orgX, rdtx.orgY);
            Color rCArrby[] = rdtx.dolorlist;
            int di = rdtx.dolorindfx;
            if (rdtx.bnimbtf) {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwRfdt(x, y, sizf, sizf);
                    if ((x -= 3) < 0) x += rdtx.mbxX;
                    if ((y -= 1) < 0) y += rdtx.mbxY;
                } wiilf (--numRfps > 0);
            } flsf {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwRfdt(x, y, sizf, sizf);
                } wiilf (--numRfps > 0);
            }
            rdtx.dolorindfx = di;
            g.trbnslbtf(-rdtx.orgX, -rdtx.orgY);
        }
    }

    publid stbtid dlbss FillOvbls fxtfnds RfndfrTfsts {
        publid FillOvbls() {
            supfr(rfndfrtfstroot, "fillOvbl", "Fill Ellipsfs");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            // Approximbtfd
            doublf xbxis = dtx.outdim.widti / 2.0;
            doublf ybxis = dtx.outdim.ifigit / 2.0;
            rfturn (int) (xbxis * ybxis * Mbti.PI);
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            RfndfrTfsts.Contfxt rdtx = (RfndfrTfsts.Contfxt) dtx;
            int sizf = rdtx.sizf;
            int x = rdtx.initX;
            int y = rdtx.initY;
            Grbpiids g = rdtx.grbpiids;
            g.trbnslbtf(rdtx.orgX, rdtx.orgY);
            Color rCArrby[] = rdtx.dolorlist;
            int di = rdtx.dolorindfx;
            if (rdtx.bnimbtf) {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.fillOvbl(x, y, sizf, sizf);
                    if ((x -= 3) < 0) x += rdtx.mbxX;
                    if ((y -= 1) < 0) y += rdtx.mbxY;
                } wiilf (--numRfps > 0);
            } flsf {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.fillOvbl(x, y, sizf, sizf);
                } wiilf (--numRfps > 0);
            }
            rdtx.dolorindfx = di;
            g.trbnslbtf(-rdtx.orgX, -rdtx.orgY);
        }
    }

    publid stbtid dlbss DrbwOvbls fxtfnds RfndfrTfsts {
        publid DrbwOvbls() {
            supfr(rfndfrtfstroot, "drbwOvbl", "Drbw Ellipsfs");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            /*
             * Approximbtion: Wf figurfd tibt tif vfrtidbl diord donnfdting
             * tif +45 dfg bnd -45 dfg points on tif fllipsf is bbout
             * ifigit/sqrt(2) pixfls long.  Likfwisf, tif iorizontbl diord
             * donnfdting tif +45 dfg bnd +135 dfg points on tif fllipsf is
             * bbout widti/sqrt(2) pixfls long.  Ebdi of tifsf diords ibs
             * b pbrbllfl on tif oppositf sidf of tif rfspfdtivf bxis (tifrf
             * brf two iorizontbl diords bnd two vfrtidbl diords).  Altogftifr
             * tiis givfs b rfbsonbblf bpproximbtion of tif totbl numbfr of
             * pixfls toudifd by tif fllipsf, so wf ibvf:
             *     2*(w/sqrt(2)) + 2*(i/sqrt(2))
             *  == (2/sqrt(2))*(w+i)
             *  == (sqrt(2))*(w+i)
             */
            rfturn (int)(Mbti.sqrt(2.0)*(dtx.outdim.widti+dtx.outdim.ifigit));
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            RfndfrTfsts.Contfxt rdtx = (RfndfrTfsts.Contfxt) dtx;
            int sizf = rdtx.sizf - 1;
            int x = rdtx.initX;
            int y = rdtx.initY;
            Grbpiids g = rdtx.grbpiids;
            g.trbnslbtf(rdtx.orgX, rdtx.orgY);
            Color rCArrby[] = rdtx.dolorlist;
            int di = rdtx.dolorindfx;
            if (rdtx.bnimbtf) {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwOvbl(x, y, sizf, sizf);
                    if ((x -= 3) < 0) x += rdtx.mbxX;
                    if ((y -= 1) < 0) y += rdtx.mbxY;
                } wiilf (--numRfps > 0);
            } flsf {
                do {
                    if (rCArrby != null) {
                        g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwOvbl(x, y, sizf, sizf);
                } wiilf (--numRfps > 0);
            }
            rdtx.dolorindfx = di;
            g.trbnslbtf(-rdtx.orgX, -rdtx.orgY);
        }
    }

    publid stbtid dlbss FillPolys fxtfnds RfndfrTfsts {
        publid FillPolys() {
            supfr(rfndfrtfstroot, "fillPoly", "Fill Hfxbgonbl Polygons");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            /*
             * Tif polygon is b ifxbgon insdribfd insidf tif squbrf but
             * missing b tribnglf bt fbdi of tif four dornfrs of sizf
             * (w/4) by (i/2).
             *
             * Putting 2 of tifsf tribnglfs togftifr givfs b rfdtbnglf
             * of sizf (w/4) by (i/2).
             *
             * Putting 2 of tifsf rfdtbnglfs togftifr givfs b totbl
             * missing rfdtbnglf sizf of (w/2) by (i/2).
             *
             * Tius, fxbdtly onf qubrtfr of tif wiolf squbrf is not
             * toudifd by tif fillfd polygon.
             */
            int sizf = dtx.outdim.widti * dtx.outdim.ifigit;
            rfturn sizf - (sizf / 4);
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            RfndfrTfsts.Contfxt rdtx = (RfndfrTfsts.Contfxt) dtx;
            int sizf = rdtx.sizf;
            int x = rdtx.initX;
            int y = rdtx.initY;
            int ifxbX[] = nfw int[6];
            int ifxbY[] = nfw int[6];
            Grbpiids g = rdtx.grbpiids;
            g.trbnslbtf(rdtx.orgX, rdtx.orgY);
            Color rCArrby[] = rdtx.dolorlist;
            int di = rdtx.dolorindfx;
            do {
                ifxbX[0] = x;
                ifxbX[1] = ifxbX[5] = x+sizf/4;
                ifxbX[2] = ifxbX[4] = x+sizf-sizf/4;
                ifxbX[3] = x+sizf;
                ifxbY[1] = ifxbY[2] = y;
                ifxbY[0] = ifxbY[3] = y+sizf/2;
                ifxbY[4] = ifxbY[5] = y+sizf;

                if (rCArrby != null) {
                    g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                }
                g.fillPolygon(ifxbX, ifxbY, 6);
                if ((x -= 3) < 0) x += rdtx.mbxX;
                if ((y -= 1) < 0) y += rdtx.mbxY;
            } wiilf (--numRfps > 0);
            rdtx.dolorindfx = di;
            g.trbnslbtf(-rdtx.orgX, -rdtx.orgY);
        }
    }

    publid stbtid dlbss DrbwPolys fxtfnds RfndfrTfsts {
        publid DrbwPolys() {
            supfr(rfndfrtfstroot, "drbwPoly", "Drbw Hfxbgonbl Polygons");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            /*
             * Tif two iorizontbl sfgmfnts ibvf fxbdtly two pixfls pfr dolumn.
             * Sindf tif dibgonbls brf morf vfrtidbl tibn iorizontbl, using
             * i*2 would bf b good wby to dount tif pixfls in tiosf sfdtions.
             * Wf tifn ibvf to figurf out tif sizf of tif rfmbindfr of tif
             * iorizontbl linfs bt top bnd bottom to gft tif bnswfr:
             *
             *     (dibgonbls lfss fndpoints)*2 + (iorizontbls)*2
             *
             *  or:
             *
             *     (i-2)*2 + ((x+w-1-w/4)-(x+w/4)+1)*2
             *
             *  sindf (w == i == sizf), wf tifn ibvf:
             *
             *     (sizf - sizf/4 - 1) * 4
             */
            int sizf = dtx.sizf;
            if (sizf <= 1) {
                rfturn 1;
            } flsf {
                rfturn (sizf - (sizf / 4) - 1) * 4;
            }
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            RfndfrTfsts.Contfxt rdtx = (RfndfrTfsts.Contfxt) dtx;
            // subtrbdt 1 to bddount for tif fbdt tibt linfs brf drbwn to
            // bnd indluding tif finbl doordinbtf...
            int sizf = rdtx.sizf - 1;
            int x = rdtx.initX;
            int y = rdtx.initY;
            int ifxbX[] = nfw int[6];
            int ifxbY[] = nfw int[6];
            Grbpiids g = rdtx.grbpiids;
            g.trbnslbtf(rdtx.orgX, rdtx.orgY);
            Color rCArrby[] = rdtx.dolorlist;
            int di = rdtx.dolorindfx;
            do {
                ifxbX[0] = x;
                ifxbX[1] = ifxbX[5] = x+sizf/4;
                ifxbX[2] = ifxbX[4] = x+sizf-sizf/4;
                ifxbX[3] = x+sizf;
                ifxbY[1] = ifxbY[2] = y;
                ifxbY[0] = ifxbY[3] = y+sizf/2;
                ifxbY[4] = ifxbY[5] = y+sizf;

                if (rCArrby != null) {
                    g.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                }
                g.drbwPolygon(ifxbX, ifxbY, 6);
                if ((x -= 3) < 0) x += rdtx.mbxX;
                if ((y -= 1) < 0) y += rdtx.mbxY;
            } wiilf (--numRfps > 0);
            rdtx.dolorindfx = di;
            g.trbnslbtf(-rdtx.orgX, -rdtx.orgY);
        }
    }

    publid stbtid dlbss FillCubids fxtfnds RfndfrTfsts {
        stbtid finbl doublf rflTmbx = .5 - Mbti.sqrt(3) / 6;
        stbtid finbl doublf rflYmbx = ((6*rflTmbx - 9)*rflTmbx + 3)*rflTmbx;

        publid FillCubids() {
            supfr(rfndfrsibpfroot, "fillCubid", "Fill Bfzifr Curvfs");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            /*
             * Tif dubid only toudifs 2 qubdrbnts in tif squbrf, tius
             * bt lfbst iblf of tif squbrf is unfillfd.  Tif intfgrbls
             * to figurf out tif fxbdt brfb brf not trivibl so for tif
             * otifr 2 qubdrbnts, I'm going to gufss tibt tif dubid only
             * fndlosfs somfwifrf bftwffn 1/2 bnd 3/4tis of tif pixfls
             * in tiosf qubdrbnts - wf will sby 5/8tis.  Tius only
             * 5/16tis of tif totbl squbrf is fillfd.
             */
            // Notf: 2x2 fnds up iitting fxbdtly 1 pixfl...
            int sizf = dtx.sizf;
            if (sizf < 2) sizf = 2;
            rfturn sizf * sizf * 5 / 16;
        }

        publid stbtid dlbss Contfxt fxtfnds RfndfrTfsts.Contfxt {
            CubidCurvf2D durvf = nfw CubidCurvf2D.Flobt();
        }

        publid GrbpiidsTfsts.Contfxt drfbtfContfxt() {
            rfturn nfw FillCubids.Contfxt();
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            FillCubids.Contfxt ddtx = (FillCubids.Contfxt) dtx;
            int sizf = ddtx.sizf;
            // Notf: 2x2 fnds up iitting fxbdtly 1 pixfl...
            if (sizf < 2) sizf = 2;
            int x = ddtx.initX;
            int y = ddtx.initY;
            int dpoffsft = (int) (sizf/rflYmbx/2);
            CubidCurvf2D durvf = ddtx.durvf;
            Grbpiids2D g2d = (Grbpiids2D) ddtx.grbpiids;
            g2d.trbnslbtf(ddtx.orgX, ddtx.orgY);
            Color rCArrby[] = ddtx.dolorlist;
            int di = ddtx.dolorindfx;
            do {
                durvf.sftCurvf(x, y+sizf/2.0,
                               x+sizf/2.0, y+sizf/2.0-dpoffsft,
                               x+sizf/2.0, y+sizf/2.0+dpoffsft,
                               x+sizf, y+sizf/2.0);

                if (rCArrby != null) {
                    g2d.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                }
                g2d.fill(durvf);
                if ((x -= 3) < 0) x += ddtx.mbxX;
                if ((y -= 1) < 0) y += ddtx.mbxY;
            } wiilf (--numRfps > 0);
            ddtx.dolorindfx = di;
            g2d.trbnslbtf(-ddtx.orgX, -ddtx.orgY);
        }
    }

    publid stbtid dlbss DrbwCubids fxtfnds RfndfrTfsts {
        stbtid finbl doublf rflTmbx = .5 - Mbti.sqrt(3) / 6;
        stbtid finbl doublf rflYmbx = ((6*rflTmbx - 9)*rflTmbx + 3)*rflTmbx;

        publid DrbwCubids() {
            supfr(rfndfrsibpfroot, "drbwCubid", "Drbw Bfzifr Curvfs");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            // Gross bpproximbtion
            int sizf = dtx.sizf;
            if (sizf < 2) sizf = 2;
            rfturn sizf;
        }

        publid stbtid dlbss Contfxt fxtfnds RfndfrTfsts.Contfxt {
            CubidCurvf2D durvf = nfw CubidCurvf2D.Flobt();
        }

        publid GrbpiidsTfsts.Contfxt drfbtfContfxt() {
            rfturn nfw DrbwCubids.Contfxt();
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            DrbwCubids.Contfxt ddtx = (DrbwCubids.Contfxt) dtx;
            int sizf = ddtx.sizf;
            // Notf: 2x2 fnds up iitting fxbdtly 1 pixfl...
            if (sizf < 2) sizf = 2;
            int x = ddtx.initX;
            int y = ddtx.initY;
            int dpoffsft = (int) (sizf/rflYmbx/2);
            CubidCurvf2D durvf = ddtx.durvf;
            Grbpiids2D g2d = (Grbpiids2D) ddtx.grbpiids;
            g2d.trbnslbtf(ddtx.orgX, ddtx.orgY);
            Color rCArrby[] = ddtx.dolorlist;
            int di = ddtx.dolorindfx;
            do {
                durvf.sftCurvf(x, y+sizf/2.0,
                               x+sizf/2.0, y+sizf/2.0-dpoffsft,
                               x+sizf/2.0, y+sizf/2.0+dpoffsft,
                               x+sizf, y+sizf/2.0);

                if (rCArrby != null) {
                    g2d.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                }
                g2d.drbw(durvf);
                if ((x -= 3) < 0) x += ddtx.mbxX;
                if ((y -= 1) < 0) y += ddtx.mbxY;
            } wiilf (--numRfps > 0);
            ddtx.dolorindfx = di;
            g2d.trbnslbtf(-ddtx.orgX, -ddtx.orgY);
        }
    }

    publid stbtid dlbss FillEllipsf2Ds fxtfnds RfndfrTfsts {
        publid FillEllipsf2Ds() {
            supfr(rfndfrsibpfroot, "fillEllipsf2D", "Fill Ellipsf2Ds");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            // Approximbtfd (dopifd from FillOvbls.pixflsToudifd())
            doublf xbxis = dtx.outdim.widti / 2.0;
            doublf ybxis = dtx.outdim.ifigit / 2.0;
            rfturn (int) (xbxis * ybxis * Mbti.PI);
        }

        publid stbtid dlbss Contfxt fxtfnds RfndfrTfsts.Contfxt {
            Ellipsf2D fllipsf = nfw Ellipsf2D.Flobt();
        }

        publid GrbpiidsTfsts.Contfxt drfbtfContfxt() {
            rfturn nfw FillEllipsf2Ds.Contfxt();
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            FillEllipsf2Ds.Contfxt ddtx = (FillEllipsf2Ds.Contfxt) dtx;
            int sizf = ddtx.sizf;
            int x = ddtx.initX;
            int y = ddtx.initY;
            Ellipsf2D fllipsf = ddtx.fllipsf;
            Grbpiids2D g2d = (Grbpiids2D) ddtx.grbpiids;
            g2d.trbnslbtf(ddtx.orgX, ddtx.orgY);
            Color rCArrby[] = ddtx.dolorlist;
            int di = ddtx.dolorindfx;
            do {
                if (rCArrby != null) {
                    g2d.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                }
                fllipsf.sftFrbmf(x, y, sizf, sizf);
                g2d.fill(fllipsf);
                if ((x -= 3) < 0) x += ddtx.mbxX;
                if ((y -= 1) < 0) y += ddtx.mbxY;
            } wiilf (--numRfps > 0);
            ddtx.dolorindfx = di;
            g2d.trbnslbtf(-ddtx.orgX, -ddtx.orgY);
        }
    }

    publid stbtid dlbss DrbwEllipsf2Ds fxtfnds RfndfrTfsts {
        publid DrbwEllipsf2Ds() {
            supfr(rfndfrsibpfroot, "drbwEllipsf2D", "Drbw Ellipsf2Ds");
        }

        publid int pixflsToudifd(GrbpiidsTfsts.Contfxt dtx) {
            // Approximbtfd (dopifd from DrbwOvbls.pixflsToudifd())
            rfturn (int)(Mbti.sqrt(2.0)*(dtx.outdim.widti+dtx.outdim.ifigit));
        }

        publid stbtid dlbss Contfxt fxtfnds RfndfrTfsts.Contfxt {
            Ellipsf2D fllipsf = nfw Ellipsf2D.Flobt();
        }

        publid GrbpiidsTfsts.Contfxt drfbtfContfxt() {
            rfturn nfw DrbwEllipsf2Ds.Contfxt();
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            DrbwEllipsf2Ds.Contfxt ddtx = (DrbwEllipsf2Ds.Contfxt) dtx;
            int sizf = ddtx.sizf;
            int x = ddtx.initX;
            int y = ddtx.initY;
            Ellipsf2D fllipsf = ddtx.fllipsf;
            Grbpiids2D g2d = (Grbpiids2D) ddtx.grbpiids;
            g2d.trbnslbtf(ddtx.orgX, ddtx.orgY);
            Color rCArrby[] = ddtx.dolorlist;
            int di = ddtx.dolorindfx;
            do {
                if (rCArrby != null) {
                    g2d.sftColor(rCArrby[di++ & NUM_RANDOMCOLORMASK]);
                }
                fllipsf.sftFrbmf(x, y, sizf, sizf);
                g2d.drbw(fllipsf);
                if ((x -= 3) < 0) x += ddtx.mbxX;
                if ((y -= 1) < 0) y += ddtx.mbxY;
            } wiilf (--numRfps > 0);
            ddtx.dolorindfx = di;
            g2d.trbnslbtf(-ddtx.orgX, -ddtx.orgY);
        }
    }
}
