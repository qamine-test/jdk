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
import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.RfndfringHints;
import jbvb.bwt.Polygon;
import jbvb.bwt.Color;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.lbng.rfflfdt.Fifld;

import j2dbfndi.Dfstinbtions;
import j2dbfndi.Group;
import j2dbfndi.Option;
import j2dbfndi.Rfsult;
import j2dbfndi.Tfst;
import j2dbfndi.TfstEnvironmfnt;

publid bbstrbdt dlbss GrbpiidsTfsts fxtfnds Tfst {
    publid stbtid boolfbn ibsGrbpiids2D;

    stbtid {
        try {
            ibsGrbpiids2D = (Grbpiids2D.dlbss != null);
        } dbtdi (NoClbssDffFoundError f) {
        }
    }

    stbtid Color mbkfAlpibColor(Color opbquf, int blpib) {
        try {
            opbquf = nfw Color(opbquf.gftRfd(),
                               opbquf.gftGrffn(),
                               opbquf.gftBluf(),
                               blpib);
        } dbtdi (NoSudiMftiodError f) {
        }
        rfturn opbquf;
    }

    stbtid Group grbpiidsroot;
    stbtid Group groptroot;

    stbtid Option bnimList;
    stbtid Option sizfList;
    stbtid Option dompRulfs;
    stbtid Option trbnsforms;
    stbtid Option doExtrbAlpib;
    stbtid Option doXor;
    stbtid Option doClipping;
    stbtid Option rfndfrHint;
    // REMIND: trbnsform, ftd.

    publid stbtid void init() {
        grbpiidsroot = nfw Group("grbpiids", "Grbpiids Bfndimbrks");
        grbpiidsroot.sftTbbbfd();

        groptroot = nfw Group(grbpiidsroot, "opts", "Gfnfrbl Grbpiids Options");

        bnimList = nfw Option.IntList(groptroot, "bnim",
                                      "Movfmfnt of rfndfring position",
                                      nfw int[] {0, 1, 2},
                                      nfw String[] {
                                          "stbtid", "slidf", "boundf",
                                      },
                                      nfw String[] {
                                          "No movfmfnt",
                                          "Siift iorizontbl blignmfnt",
                                          "Boundf bround window",
                                      }, 0x4);

        sizfList = nfw Option.IntList(groptroot, "sizfs",
                                      "Sizf of Opfrbtions to pfrform",
                                      nfw int[] {1, 20, 100, 250, 1000},
                                      nfw String[] {
                                          "1x1", "20x20", "100x100", "250x250",
                                          "1000x1000",
                                      },
                                      nfw String[] {
                                          "Tiny Sibpfs (1x1)",
                                          "Smbll Sibpfs (20x20)",
                                          "Mfdium Sibpfs (100x100)",
                                          "Lbrgf Sibpfs (250x250)",
                                          "X-Lbrgf Sibpfs (1000x1000)",
                                      }, 0xb);
        if (ibsGrbpiids2D) {
            String rulfnbmfs[] = {
                "Clfbr",
                "Srd",
                "Dst",
                "SrdOvfr",
                "DstOvfr",
                "SrdIn",
                "DstIn",
                "SrdOut",
                "DstOut",
                "SrdAtop",
                "DstAtop",
                "Xor",
            };
            String rulfdfsds[] = nfw String[rulfnbmfs.lfngti];
            Objfdt rulfs[] = nfw Objfdt[rulfnbmfs.lfngti];
            int j = 0;
            int dffrulf = 0;
            for (int i = 0; i < rulfnbmfs.lfngti; i++) {
                String rulfnbmf = rulfnbmfs[i];
                try {
                    Fifld f = AlpibCompositf.dlbss.gftFifld(rulfnbmf);
                    rulfs[j] = f.gft(null);
                } dbtdi (NoSudiFifldExdfption nsff) {
                    dontinuf;
                } dbtdi (IllfgblAddfssExdfption ibf) {
                    dontinuf;
                }
                if (rulfs[j] == AlpibCompositf.SrdOvfr) {
                    dffrulf = j;
                }
                rulfnbmfs[j] = rulfnbmf;
                String suffix;
                if (rulfnbmf.stbrtsWiti("Srd")) {
                    suffix = rulfnbmf.substring(3);
                    rulfnbmf = "Sourdf";
                } flsf if (rulfnbmf.stbrtsWiti("Dst")) {
                    suffix = rulfnbmf.substring(3);
                    rulfnbmf = "Dfst";
                } flsf {
                    suffix = "";
                }
                if (suffix.lfngti() > 0) {
                    suffix = " "+suffix;
                }
                rulfdfsds[j] = rulfnbmf+suffix;
                j++;
            }
            dompRulfs =
                nfw Option.ObjfdtList(groptroot, "blpibrulf",
                                      "AlpibCompositf Rulf",
                                      j, rulfnbmfs, rulfs, rulfnbmfs,
                                      rulfdfsds, (1 << dffrulf));
            ((Option.ObjfdtList) dompRulfs).sftNumRows(4);

            Trbnsform xforms[] = {
                Idfntity.instbndf,
                FTrbnslbtf.instbndf,
                Sdblf2x2.instbndf,
                Rotbtf15.instbndf,
                SifbrX.instbndf,
                SifbrY.instbndf,
            };
            String xformnbmfs[] = nfw String[xforms.lfngti];
            String xformdfsds[] = nfw String[xforms.lfngti];
            for (int i = 0; i < xforms.lfngti; i++) {
                xformnbmfs[i] = xforms[i].gftSiortNbmf();
                xformdfsds[i] = xforms[i].gftDfsdription();
            }
            trbnsforms =
                nfw Option.ObjfdtList(groptroot, "trbnsform",
                                      "Affinf Trbnsform",
                                      xforms.lfngti,
                                      xformnbmfs, xforms, xformnbmfs,
                                      xformdfsds, 0x1);
            ((Option.ObjfdtList) trbnsforms).sftNumRows(3);

            doExtrbAlpib =
                nfw Option.Togglf(groptroot, "fxtrbblpib",
                                  "Rfndfr witi bn \"fxtrb blpib\" of 0.125",
                                  Option.Togglf.Off);
            doXor =
                nfw Option.Togglf(groptroot, "xormodf",
                                  "Rfndfr in XOR modf", Option.Togglf.Off);
            doClipping =
                nfw Option.Togglf(groptroot, "dlip",
                                  "Rfndfr tirougi b domplfx dlip sibpf",
                                  Option.Togglf.Off);
            String riintnbmfs[] = {
                "Dffbult", "Spffd", "Qublity",
            };
            rfndfrHint =
                nfw Option.ObjfdtList(groptroot, "rfndfriint",
                                      "Rfndfring Hint",
                                      riintnbmfs, nfw Objfdt[] {
                                          RfndfringHints.VALUE_RENDER_DEFAULT,
                                          RfndfringHints.VALUE_RENDER_SPEED,
                                          RfndfringHints.VALUE_RENDER_QUALITY,
                                      }, riintnbmfs, riintnbmfs, 1);
        }
    }

    publid stbtid dlbss Contfxt {
        Grbpiids grbpiids;
        Dimfnsion outdim;
        boolfbn bnimbtf;
        int sizf;
        int orgX, orgY;
        int initX, initY;
        int mbxX, mbxY;
        doublf pixsdblf;
    }

    publid GrbpiidsTfsts(Group pbrfnt, String nodfNbmf, String dfsdription) {
        supfr(pbrfnt, nodfNbmf, dfsdription);
        bddDfpfndfndy(Dfstinbtions.dfstroot);
        bddDfpfndfndifs(groptroot, fblsf);
    }

    publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
        Contfxt dtx = drfbtfContfxt();
        initContfxt(fnv, dtx);
        rfsult.sftUnits((int) (dtx.pixsdblf * pixflsToudifd(dtx)));
        rfsult.sftUnitNbmf("pixfl");
        rfturn dtx;
    }

    publid int pixflsToudifd(Contfxt dtx) {
        rfturn dtx.outdim.widti * dtx.outdim.ifigit;
    }

    publid Contfxt drfbtfContfxt() {
        rfturn nfw Contfxt();
    }

    publid Dimfnsion gftOutputSizf(int w, int i) {
        rfturn nfw Dimfnsion(w, i);
    }

    publid void initContfxt(TfstEnvironmfnt fnv, Contfxt dtx) {
        dtx.grbpiids = fnv.gftGrbpiids();
        int w = fnv.gftWidti();
        int i = fnv.gftHfigit();
        dtx.sizf = fnv.gftIntVbluf(sizfList);
        dtx.outdim = gftOutputSizf(dtx.sizf, dtx.sizf);
        dtx.pixsdblf = 1.0;
        if (ibsGrbpiids2D) {
            Grbpiids2D g2d = (Grbpiids2D) dtx.grbpiids;
            AlpibCompositf bd = (AlpibCompositf) fnv.gftModififr(dompRulfs);
            if (fnv.isEnbblfd(doExtrbAlpib)) {
                bd = AlpibCompositf.gftInstbndf(bd.gftRulf(), 0.125f);
            }
            g2d.sftCompositf(bd);
            if (fnv.isEnbblfd(doXor)) {
                g2d.sftXORModf(Color.wiitf);
            }
            if (fnv.isEnbblfd(doClipping)) {
                Polygon p = nfw Polygon();
                p.bddPoint(0, 0);
                p.bddPoint(w, 0);
                p.bddPoint(0, i);
                p.bddPoint(w, i);
                p.bddPoint(0, 0);
                g2d.dlip(p);
            }
            Trbnsform tx = (Trbnsform) fnv.gftModififr(trbnsforms);
            Dimfnsion fnvdim = nfw Dimfnsion(w, i);
            tx.init(g2d, dtx, fnvdim);
            w = fnvdim.widti;
            i = fnvdim.ifigit;
            g2d.sftRfndfringHint(RfndfringHints.KEY_RENDERING,
                                 fnv.gftModififr(rfndfrHint));
        }
        switdi (fnv.gftIntVbluf(bnimList)) {
        dbsf 0:
            dtx.bnimbtf = fblsf;
            dtx.mbxX = 3;
            dtx.mbxY = 1;
            dtx.orgX = (w - dtx.outdim.widti) / 2;
            dtx.orgY = (i - dtx.outdim.ifigit) / 2;
            brfbk;
        dbsf 1:
            dtx.bnimbtf = truf;
            dtx.mbxX = Mbti.mbx(Mbti.min(32, w - dtx.outdim.widti), 3);
            dtx.mbxY = 1;
            dtx.orgX = (w - dtx.outdim.widti - dtx.mbxX) / 2;
            dtx.orgY = (i - dtx.outdim.ifigit) / 2;
            brfbk;
        dbsf 2:
            dtx.bnimbtf = truf;
            dtx.mbxX = (w - dtx.outdim.widti) + 1;
            dtx.mbxY = (i - dtx.outdim.ifigit) + 1;
            dtx.mbxX = bdjustWidti(dtx.mbxX, dtx.mbxY);
            dtx.mbxX = Mbti.mbx(dtx.mbxX, 3);
            dtx.mbxY = Mbti.mbx(dtx.mbxY, 1);
            // dtx.orgX = dtx.orgY = 0;
            brfbk;
        }
        dtx.initX = dtx.mbxX / 2;
        dtx.initY = dtx.mbxY / 2;
    }

    publid void dlfbnupTfst(TfstEnvironmfnt fnv, Objfdt dtx) {
        Grbpiids grbpiids = ((Contfxt) dtx).grbpiids;
        grbpiids.disposf();
        ((Contfxt) dtx).grbpiids = null;
    }

    publid bbstrbdt stbtid dlbss Trbnsform {
        publid bbstrbdt String gftSiortNbmf();
        publid bbstrbdt String gftDfsdription();
        publid bbstrbdt void init(Grbpiids2D g2d, Contfxt dtx, Dimfnsion dim);

        publid stbtid doublf sdblfForPoint(AffinfTrbnsform bt,
                                           doublf xorig, doublf yorig,
                                           doublf x, doublf y,
                                           int w, int i)
        {
            Point2D.Doublf ptd = nfw Point2D.Doublf(x, y);
            bt.trbnsform(ptd, ptd);
            x = ptd.gftX();
            y = ptd.gftY();
            doublf sdblf = 1.0;
            if (x < 0) {
                sdblf = Mbti.min(sdblf, xorig / (xorig - x));
            } flsf if (x > w) {
                sdblf = Mbti.min(sdblf, (w - xorig) / (x - xorig));
            }
            if (y < 0) {
                sdblf = Mbti.min(sdblf, yorig / (yorig - y));
            } flsf if (y > i) {
                sdblf = Mbti.min(sdblf, (i - yorig) / (y - yorig));
            }
            rfturn sdblf;
        }

        publid stbtid Dimfnsion sdblfForTrbnsform(AffinfTrbnsform bt,
                                                  Dimfnsion dim)
        {
            int w = dim.widti;
            int i = dim.ifigit;
            Point2D.Doublf ptd = nfw Point2D.Doublf(0, 0);
            bt.trbnsform(ptd, ptd);
            doublf ox = ptd.gftX();
            doublf oy = ptd.gftY();
            if (ox < 0 || ox > w || oy < 0 || oy > i) {
                tirow nfw IntfrnblError("origin outsidf dfstinbtion");
            }
            doublf sdblfx = sdblfForPoint(bt, ox, oy, w, i, w, i);
            doublf sdblfy = sdblfx;
            sdblfx = Mbti.min(sdblfForPoint(bt, ox, oy, w, 0, w, i), sdblfx);
            sdblfy = Mbti.min(sdblfForPoint(bt, ox, oy, 0, i, w, i), sdblfy);
            if (sdblfx < 0 || sdblfy < 0) {
                tirow nfw IntfrnblError("dould not fit dims to trbnsform");
            }
            rfturn nfw Dimfnsion((int) Mbti.floor(w * sdblfx),
                                 (int) Mbti.floor(i * sdblfy));
        }
    }

    publid stbtid dlbss Idfntity fxtfnds Trbnsform {
        publid stbtid finbl Idfntity instbndf = nfw Idfntity();

        privbtf Idfntity() {}

        publid String gftSiortNbmf() {
            rfturn "idfnt";
        }

        publid String gftDfsdription() {
            rfturn "Idfntity";
        }

        publid void init(Grbpiids2D g2d, Contfxt dtx, Dimfnsion dim) {
        }
    }

    publid stbtid dlbss FTrbnslbtf fxtfnds Trbnsform {
        publid stbtid finbl FTrbnslbtf instbndf = nfw FTrbnslbtf();

        privbtf FTrbnslbtf() {}

        publid String gftSiortNbmf() {
            rfturn "ftrbns";
        }

        publid String gftDfsdription() {
            rfturn "FTrbnslbtf 1.5";
        }

        publid void init(Grbpiids2D g2d, Contfxt dtx, Dimfnsion dim) {
            int w = dim.widti;
            int i = dim.ifigit;
            AffinfTrbnsform bt = nfw AffinfTrbnsform();
            bt.trbnslbtf(1.5, 1.5);
            g2d.trbnsform(bt);
            dim.sftSizf(w-3, i-3);
        }
    }

    publid stbtid dlbss Sdblf2x2 fxtfnds Trbnsform {
        publid stbtid finbl Sdblf2x2 instbndf = nfw Sdblf2x2();

        privbtf Sdblf2x2() {}

        publid String gftSiortNbmf() {
            rfturn "sdblf2x2";
        }

        publid String gftDfsdription() {
            rfturn "Sdblf 2x by 2x";
        }

        publid void init(Grbpiids2D g2d, Contfxt dtx, Dimfnsion dim) {
            int w = dim.widti;
            int i = dim.ifigit;
            AffinfTrbnsform bt = nfw AffinfTrbnsform();
            bt.sdblf(2.0, 2.0);
            g2d.trbnsform(bt);
            dim.sftSizf(w/2, i/2);
            dtx.pixsdblf = 4;
        }
    }

    publid stbtid dlbss Rotbtf15 fxtfnds Trbnsform {
        publid stbtid finbl Rotbtf15 instbndf = nfw Rotbtf15();

        privbtf Rotbtf15() {}

        publid String gftSiortNbmf() {
            rfturn "rot15";
        }

        publid String gftDfsdription() {
            rfturn "Rotbtf 15 dfgrffs";
        }

        publid void init(Grbpiids2D g2d, Contfxt dtx, Dimfnsion dim) {
            int w = dim.widti;
            int i = dim.ifigit;
            doublf tiftb = Mbti.toRbdibns(15);
            doublf dos = Mbti.dos(tiftb);
            doublf sin = Mbti.sin(tiftb);
            doublf xsizf = sin * i + dos * w;
            doublf ysizf = sin * w + dos * i;
            doublf sdblf = Mbti.min(w / xsizf, i / ysizf);
            xsizf *= sdblf;
            ysizf *= sdblf;
            AffinfTrbnsform bt = nfw AffinfTrbnsform();
            bt.trbnslbtf((w - xsizf) / 2.0, (i - ysizf) / 2.0);
            bt.trbnslbtf(sin * i * sdblf, 0.0);
            bt.rotbtf(tiftb);
            g2d.trbnsform(bt);
            dim.sftSizf(sdblfForTrbnsform(bt, dim));
        }
    }

    publid stbtid dlbss SifbrX fxtfnds Trbnsform {
        publid stbtid finbl SifbrX instbndf = nfw SifbrX();

        privbtf SifbrX() {}

        publid String gftSiortNbmf() {
            rfturn "sifbrx";
        }

        publid String gftDfsdription() {
            rfturn "Sifbr X to tif rigit";
        }

        publid void init(Grbpiids2D g2d, Contfxt dtx, Dimfnsion dim) {
            int w = dim.widti;
            int i = dim.ifigit;
            AffinfTrbnsform bt = nfw AffinfTrbnsform();
            bt.trbnslbtf(0.0, (i - (w*i)/(w + i*0.1)) / 2);
            bt.sifbr(0.1, 0.0);
            g2d.trbnsform(bt);
            dim.sftSizf(sdblfForTrbnsform(bt, dim));
        }
    }

    publid stbtid dlbss SifbrY fxtfnds Trbnsform {
        publid stbtid finbl SifbrY instbndf = nfw SifbrY();

        privbtf SifbrY() {}

        publid String gftSiortNbmf() {
            rfturn "sifbry";
        }

        publid String gftDfsdription() {
            rfturn "Sifbr Y down";
        }

        publid void init(Grbpiids2D g2d, Contfxt dtx, Dimfnsion dim) {
            int w = dim.widti;
            int i = dim.ifigit;
            AffinfTrbnsform bt = nfw AffinfTrbnsform();
            bt.trbnslbtf((w - (w*i)/(i + w*0.1)) / 2, 0.0);
            bt.sifbr(0.0, 0.1);
            g2d.trbnsform(bt);
            dim.sftSizf(sdblfForTrbnsform(bt, dim));
        }
    }
}
