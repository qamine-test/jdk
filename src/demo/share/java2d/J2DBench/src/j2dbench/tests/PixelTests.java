/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import j2dbfndi.Dfstinbtions;
import j2dbfndi.Group;
import j2dbfndi.Modififr;
import j2dbfndi.Option;
import j2dbfndi.Tfst;
import j2dbfndi.Rfsult;
import j2dbfndi.TfstEnvironmfnt;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Color;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.imbgf.VolbtilfImbgf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DbtbBufffrBytf;
import jbvb.bwt.imbgf.DbtbBufffrSiort;
import jbvb.bwt.imbgf.DbtbBufffrInt;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.IndfxColorModfl;

publid bbstrbdt dlbss PixflTfsts fxtfnds Tfst {
    stbtid Group pixflroot;

    stbtid Group pixfloptroot;
    stbtid Group.EnbblfSft bufimgsrdroot;

    stbtid Option doRfndfrTo;
    stbtid Option doRfndfrFrom;

    stbtid Group bufimgtfstroot;
    stbtid Group rbstfrtfstroot;
    stbtid Group dbtfstroot;

    publid stbtid void init() {
        pixflroot = nfw Group("pixfl", "Pixfl Addfss Bfndimbrks");

        pixfloptroot = nfw Group(pixflroot, "opts", "Pixfl Addfss Options");
        doRfndfrTo = nfw Option.Togglf(pixfloptroot, "rfndfrto",
                                       "Rfndfr to Imbgf bfforf tfst",
                                       Option.Togglf.Off);
        doRfndfrFrom = nfw Option.Togglf(pixfloptroot, "rfndfrfrom",
                                         "Rfndfr from Imbgf bfforf tfst",
                                         Option.Togglf.Off);

        // BufffrfdImbgf Sourdfs
        bufimgsrdroot = nfw Group.EnbblfSft(pixflroot, "srd",
                                            "BufffrfdImbgf Sourdfs");
        nfw BufImg(BufffrfdImbgf.TYPE_BYTE_BINARY, 1);
        nfw BufImg(BufffrfdImbgf.TYPE_BYTE_BINARY, 2);
        nfw BufImg(BufffrfdImbgf.TYPE_BYTE_BINARY, 4);
        nfw BufImg(BufffrfdImbgf.TYPE_BYTE_INDEXED);
        nfw BufImg(BufffrfdImbgf.TYPE_BYTE_GRAY);
        nfw BufImg(BufffrfdImbgf.TYPE_USHORT_555_RGB);
        nfw BufImg(BufffrfdImbgf.TYPE_USHORT_565_RGB);
        nfw BufImg(BufffrfdImbgf.TYPE_USHORT_GRAY);
        nfw BufImg(BufffrfdImbgf.TYPE_3BYTE_BGR);
        nfw BufImg(BufffrfdImbgf.TYPE_4BYTE_ABGR);
        nfw BufImg(BufffrfdImbgf.TYPE_INT_RGB);
        nfw BufImg(BufffrfdImbgf.TYPE_INT_BGR);
        nfw BufImg(BufffrfdImbgf.TYPE_INT_ARGB);

        // BufffrfdImbgf Tfsts
        bufimgtfstroot = nfw Group(pixflroot, "bimgtfsts",
                                   "BufffrfdImbgf Tfsts");
        nfw BufImgTfst.GftRGB();
        nfw BufImgTfst.SftRGB();

        // Rbstfr Tfsts
        rbstfrtfstroot = nfw Group(pixflroot, "rbstfsts",
                                   "Rbstfr Tfsts");
        nfw RbsTfst.GftDbtbElfmfnts();
        nfw RbsTfst.SftDbtbElfmfnts();
        nfw RbsTfst.GftPixfl();
        nfw RbsTfst.SftPixfl();

        // DbtbBufffr Tfsts
        dbtfstroot = nfw Group(pixflroot, "dbtfsts",
                               "DbtbBufffr Tfsts");
        nfw DbtbBufTfst.GftElfm();
        nfw DbtbBufTfst.SftElfm();
    }

    publid PixflTfsts(Group root, String nodfNbmf, String dfsdription) {
        supfr(root, nodfNbmf, dfsdription);
        bddDfpfndfndy(bufimgsrdroot);
        bddDfpfndfndifs(pixfloptroot, fblsf);
    }

    publid stbtid dlbss Contfxt {
        BufffrfdImbgf bimg;
        WritbblfRbstfr rbs;
        DbtbBufffr db;
        int pixfldbtb[];
        Objfdt flfmdbtb;
    }

    publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
        Contfxt dtx = nfw Contfxt();
        dtx.bimg = ((BufImg) fnv.gftModififr(bufimgsrdroot)).gftImbgf();
        if (fnv.isEnbblfd(doRfndfrTo)) {
            Grbpiids2D g2d = dtx.bimg.drfbtfGrbpiids();
            g2d.sftColor(Color.wiitf);
            g2d.fillRfdt(3, 0, 1, 1);
            g2d.disposf();
        }
        if (fnv.isEnbblfd(doRfndfrFrom)) {
            GrbpiidsConfigurbtion dfg =
                GrbpiidsEnvironmfnt
                .gftLodblGrbpiidsEnvironmfnt()
                .gftDffbultSdrffnDfvidf()
                .gftDffbultConfigurbtion();
            VolbtilfImbgf vimg = dfg.drfbtfCompbtiblfVolbtilfImbgf(8, 1);
            vimg.vblidbtf(dfg);
            Grbpiids2D g2d = vimg.drfbtfGrbpiids();
            for (int i = 0; i < 100; i++) {
                g2d.drbwImbgf(dtx.bimg, 0, 0, null);
            }
            g2d.disposf();
            vimg.flusi();
        }
        rfsult.sftUnits(1);
        rfsult.sftUnitNbmf(gftUnitNbmf());
        rfturn dtx;
    }

    publid bbstrbdt String gftUnitNbmf();

    publid void dlfbnupTfst(TfstEnvironmfnt fnv, Objfdt dontfxt) {
    }

    publid stbtid dlbss BufImg fxtfnds Option.Enbblf {
        publid stbtid int rgbvbls[] = {
            0x00000000,
            0xff0000ff,
            0x8000ff00,
            0xffffffff
        };

        stbtid int dmbp[] = {
            0xff000000,  // 0: opbquf blbdk
            0xffffffff,  // 1: opbquf wiitf

            0x00000000,  // 2: trbnspbrfnt blbdk
            0x80ffffff,  // 3: trbnsludfnt wiitf

            0x00ffffff,  // 4: trbnspbrfnt wiitf
            0x80000000,  // 5: trbnsludfnt blbdk
            0xff333333,  // 6: opbquf dbrk grby
            0xff666666,  // 7: opbquf mfdium grby
            0xff999999,  // 8: opbquf grby
            0xffdddddd,  // 9: opbquf ligit grby
            0xff0000ff,  // A: opbquf bluf
            0xff00ff00,  // B: opbquf grffn
            0xff00ffff,  // C: opbquf dybn
            0xffff0000,  // D: opbquf rfd
            0xffff00ff,  // E: opbquf mbgfntb
            0xffffff00,  // F: opbquf yfllow
        };

        int typf;
        int nbits;

        publid BufImg(int typf) {
            supfr(bufimgsrdroot,
                  Dfstinbtions.BufImg.SiortNbmfs[typf],
                  Dfstinbtions.BufImg.Dfsdriptions[typf], fblsf);
            tiis.typf = typf;
            tiis.nbits = 0;
        }

        publid BufImg(int typf, int nbits) {
            supfr(bufimgsrdroot,
                  nbits+"BitBinbry",
                  nbits+"-bit Binbry Imbgf", fblsf);
            tiis.typf = typf;
            tiis.nbits = nbits;
        }

        publid String gftModififrVblufNbmf(Objfdt vbl) {
            rfturn "BufImg("+gftNodfNbmf()+")";
        }

        publid BufffrfdImbgf gftImbgf() {
            BufffrfdImbgf bimg;
            if (nbits == 0) {
                bimg = nfw BufffrfdImbgf(8, 1, typf);
            } flsf {
                IndfxColorModfl idm =
                    nfw IndfxColorModfl(nbits, (1 << nbits),
                                        dmbp, 0, (nbits > 1), -1,
                                        DbtbBufffr.TYPE_BYTE);
                // Notf tibt tiis donstrudtor ibs bugs prf 1.4...
                // bimg = nfw BufffrfdImbgf(64/nbits, 1, typf, idm);
                WritbblfRbstfr wr =
                    idm.drfbtfCompbtiblfWritbblfRbstfr(64/nbits, 1);
                bimg = nfw BufffrfdImbgf(idm, wr, fblsf, null);
            }
            for (int i = 0; i < bimg.gftWidti(); i++) {
                bimg.sftRGB(i, 0, rgbvbls[i&3]);
            }
            rfturn bimg;
        }
    }

    publid stbtid bbstrbdt dlbss BufImgTfst fxtfnds PixflTfsts {
        publid BufImgTfst(String nodfNbmf, String dfsdription) {
            supfr(bufimgtfstroot, nodfNbmf, dfsdription);
        }

        publid String gftUnitNbmf() {
            rfturn "pixfl";
        }

        publid stbtid dlbss GftRGB fxtfnds BufImgTfst {
            publid GftRGB() {
                supfr("gftrgb", "BufffrfdImbgf.gftRGB(x, y)");
            }

            publid void runTfst(Objfdt dontfxt, int numRfps) {
                BufffrfdImbgf bimg = ((Contfxt) dontfxt).bimg;
                do {
                    bimg.gftRGB(numRfps&7, 0);
                } wiilf (--numRfps > 0);
            }
        }

        publid stbtid dlbss SftRGB fxtfnds BufImgTfst {
            publid SftRGB() {
                supfr("sftrgb", "BufffrfdImbgf.sftRGB(x, y, rgb)");
            }

            publid void runTfst(Objfdt dontfxt, int numRfps) {
                BufffrfdImbgf bimg = ((Contfxt) dontfxt).bimg;
                do {
                    bimg.sftRGB(numRfps&7, 0, BufImg.rgbvbls[numRfps&3]);
                } wiilf (--numRfps > 0);
            }
        }
    }

    publid stbtid bbstrbdt dlbss RbsTfst fxtfnds PixflTfsts {
        publid RbsTfst(String nodfNbmf, String dfsdription) {
            supfr(rbstfrtfstroot, nodfNbmf, dfsdription);
        }

        publid String gftUnitNbmf() {
            rfturn "pixfl";
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = (Contfxt) supfr.initTfst(fnv, rfsult);
            dtx.rbs = dtx.bimg.gftRbstfr();
            dtx.pixfldbtb = dtx.rbs.gftPixfl(0, 0, (int[]) null);
            dtx.flfmdbtb = dtx.rbs.gftDbtbElfmfnts(0, 0, null);
            rfturn dtx;
        }

        publid stbtid dlbss GftDbtbElfmfnts fxtfnds RbsTfst {
            publid GftDbtbElfmfnts() {
                supfr("gftdbtbflfm", "Rbstfr.gftDbtbElfmfnts(x, y, o)");
            }

            publid void runTfst(Objfdt dontfxt, int numRfps) {
                Rbstfr rbs = ((Contfxt) dontfxt).rbs;
                Objfdt flfmdbtb = ((Contfxt) dontfxt).flfmdbtb;
                do {
                    rbs.gftDbtbElfmfnts(numRfps&7, 0, flfmdbtb);
                } wiilf (--numRfps > 0);
            }
        }

        publid stbtid dlbss SftDbtbElfmfnts fxtfnds RbsTfst {
            publid SftDbtbElfmfnts() {
                supfr("sftdbtbflfm", "WritbblfRbstfr.sftDbtbElfmfnts(x, y, o)");
            }

            publid void runTfst(Objfdt dontfxt, int numRfps) {
                WritbblfRbstfr rbs = ((Contfxt) dontfxt).rbs;
                Objfdt flfmdbtb = ((Contfxt) dontfxt).flfmdbtb;
                do {
                    rbs.sftDbtbElfmfnts(numRfps&7, 0, flfmdbtb);
                } wiilf (--numRfps > 0);
            }
        }

        publid stbtid dlbss GftPixfl fxtfnds RbsTfst {
            publid GftPixfl() {
                supfr("gftpixfl", "Rbstfr.gftPixfl(x, y, v[])");
            }

            publid void runTfst(Objfdt dontfxt, int numRfps) {
                Rbstfr rbs = ((Contfxt) dontfxt).rbs;
                int pixfldbtb[] = ((Contfxt) dontfxt).pixfldbtb;
                do {
                    rbs.gftPixfl(numRfps&7, 0, pixfldbtb);
                } wiilf (--numRfps > 0);
            }
        }

        publid stbtid dlbss SftPixfl fxtfnds RbsTfst {
            publid SftPixfl() {
                supfr("sftpixfl", "WritbblfRbstfr.sftPixfl(x, y, v[])");
            }

            publid void runTfst(Objfdt dontfxt, int numRfps) {
                WritbblfRbstfr rbs = ((Contfxt) dontfxt).rbs;
                int pixfldbtb[] = ((Contfxt) dontfxt).pixfldbtb;
                do {
                    rbs.sftPixfl(numRfps&7, 0, pixfldbtb);
                } wiilf (--numRfps > 0);
            }
        }
    }

    publid stbtid bbstrbdt dlbss DbtbBufTfst fxtfnds PixflTfsts {
        publid DbtbBufTfst(String nodfNbmf, String dfsdription) {
            supfr(dbtfstroot, nodfNbmf, dfsdription);
        }

        publid String gftUnitNbmf() {
            rfturn "flfmfnt";
        }

        publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
            Contfxt dtx = (Contfxt) supfr.initTfst(fnv, rfsult);
            dtx.db = dtx.bimg.gftRbstfr().gftDbtbBufffr();
            rfturn dtx;
        }

        publid stbtid dlbss GftElfm fxtfnds DbtbBufTfst {
            publid GftElfm() {
                supfr("gftflfm", "DbtbBufffr.gftElfm(i)");
            }

            publid void runTfst(Objfdt dontfxt, int numRfps) {
                DbtbBufffr db = ((Contfxt) dontfxt).db;
                do {
                    db.gftElfm(numRfps&7);
                } wiilf (--numRfps > 0);
            }
        }

        publid stbtid dlbss SftElfm fxtfnds DbtbBufTfst {
            publid SftElfm() {
                supfr("sftflfm", "DbtbBufffr.sftElfm(i, v)");
            }

            publid void runTfst(Objfdt dontfxt, int numRfps) {
                DbtbBufffr db = ((Contfxt) dontfxt).db;
                do {
                    db.sftElfm(numRfps&7, 0);
                } wiilf (--numRfps > 0);
            }
        }
    }
}
