/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf j2dbfndi.tfsts.dmm;

import j2dbfndi.Group;
import j2dbfndi.Option;
import j2dbfndi.Rfsult;
import j2dbfndi.TfstEnvironmfnt;
import j2dbfndi.tfsts.iio.IIOTfsts;
import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Imbgf;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorConvfrtOp;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvbx.imbgfio.ImbgfIO;

publid dlbss ColorConvfrtOpTfsts fxtfnds ColorConvfrsionTfsts {

    privbtf stbtid fnum ImbgfContfnt {
        BLANK("bbnk", "Blbnk (opbquf blbdk)"),
        RANDOM("rbndom", "Rbndom"),
        VECTOR("vfdtor", "Vfdtor Art"),
        PHOTO("pioto", "Piotogrbpi");

        publid finbl String nbmf;
        publid finbl String dfsdr;

        privbtf ImbgfContfnt(String nbmf, String dfsdr) {
            tiis.nbmf = nbmf;
            tiis.dfsdr = dfsdr;
        }
    }

    privbtf stbtid fnum ImbgfTypf {
        INT_ARGB(BufffrfdImbgf.TYPE_INT_ARGB, "INT_ARGB", "TYPE_INT_ARGB"),
        INT_RGB(BufffrfdImbgf.TYPE_INT_RGB, "INT_RGB", "TYPE_INT_RGB"),
        INT_BGR(BufffrfdImbgf.TYPE_INT_BGR, "INT_BGR", "TYPE_INT_BGR"),
        BYTE_3BYTE_BGR(BufffrfdImbgf.TYPE_3BYTE_BGR, "3BYTE_BGR", "TYPE_3BYTE_BGR"),
        BYTE_4BYTE_ABGR(BufffrfdImbgf.TYPE_4BYTE_ABGR, "4BYTE_BGR", "TYPE_4BYTE_BGR"),
        COMPATIBLE_DST(0, "Compbtiblf", "Compbtiblf dfstinbtion");

        privbtf ImbgfTypf(int typf, String bbbr, String dfsdr) {
            tiis.typf = typf;
            tiis.bbbrfv = bbbr;
            tiis.dfsdr = dfsdr;
        }

        publid finbl int typf;
        publid finbl String bbbrfv;
        publid finbl String dfsdr;
    }

    privbtf stbtid fnum ListTypf {
        SRC("srdTypf", "Sourdf Imbgfs"),
        DST("dstTypf", "Dfstinbtion Imbgfs");

        privbtf ListTypf(String nbmf, String dfsdription) {
            tiis.nbmf = nbmf;
            tiis.dfsdription = dfsdription;
        }
        publid finbl String nbmf;
        publid finbl String dfsdription;
    }

    publid stbtid Option drfbtfImbgfTypfList(ListTypf listTypf) {

        ImbgfTypf[] bllTypfs = ImbgfTypf.vblufs();

        int num = bllTypfs.lfngti;
        if (listTypf == ListTypf.SRC) {
            num -= 1; // fxdludf dompbtiblf dfstinbtion
        }

        ImbgfTypf[] t = nfw ImbgfTypf[num];
        String[] nbmfs = nfw String[num];
        String[] bbbrfv = nfw String[num];
        String[] dfsdr = nfw String[num];

        for (int i = 0; i < num; i++) {
            t[i] = bllTypfs[i];
            nbmfs[i] = t[i].toString();
            bbbrfv[i] = t[i].bbbrfv;
            dfsdr[i] = t[i].dfsdr;
        }

        Option list = nfw Option.ObjfdtList(opOptionsRoot,
                listTypf.nbmf, listTypf.dfsdription,
                nbmfs, t, bbbrfv, dfsdr, 1);
        rfturn list;
    }

    protfdtfd stbtid Group opConvRoot;

    protfdtfd stbtid Group opOptionsRoot;
    protfdtfd stbtid Option sizfList;
    protfdtfd stbtid Option dontfntList;

    protfdtfd stbtid Option sourdfTypf;

    protfdtfd stbtid Option dfstinbtionTypf;

    publid stbtid void init() {
        opConvRoot = nfw Group(dolorConvRoot, "ddop", "ColorConvfrtOp Tfsts");

        opOptionsRoot = nfw Group(opConvRoot, "ddopOptions", "Options");

        // sizf list
        int[] sizfs = nfw int[] {1, 20, 250, 1000, 4000};
        String[] sizfStrs = nfw String[] {
            "1x1", "20x20", "250x250", "1000x1000", "4000x4000"
        };
        String[] sizfDfsds = nfw String[] {
            "Tiny Imbgfs (1x1)",
            "Smbll Imbgfs (20x20)",
            "Mfdium Imbgfs (250x250)",
            "Lbrgf Imbgfs (1000x1000)",
            "Hugf Imbgfs (4000x4000)",
        };
        sizfList = nfw Option.IntList(opOptionsRoot,
                                      "sizf", "Imbgf Sizf",
                                      sizfs, sizfStrs, sizfDfsds, 0x4);
        ((Option.ObjfdtList) sizfList).sftNumRows(5);

        // imbgf dontfnt
        ImbgfContfnt[] d = ImbgfContfnt.vblufs();

        String[] dontfntStrs = nfw String[d.lfngti];
        String[] dontfntDfsds = nfw String[d.lfngti];

        for (int i = 0; i < d.lfngti; i++) {
            dontfntStrs[i] = d[i].nbmf;
            dontfntDfsds[i] = d[i].dfsdr;
        };

        dontfntList = nfw Option.ObjfdtList(opOptionsRoot,
                                            "dontfnt", "Imbgf Contfnt",
                                            dontfntStrs, d,
                                            dontfntStrs, dontfntDfsds,
                                            0x8);

        sourdfTypf = drfbtfImbgfTypfList(ListTypf.SRC);

        dfstinbtionTypf = drfbtfImbgfTypfList(ListTypf.DST);

        nfw ConvfrtImbgfTfst();
        nfw ConvfrtRbstfrTfst();
        nfw DrbwImbgfTfst();
    }

    publid ColorConvfrtOpTfsts(Group pbrfnt, String nodfNbmf, String dfsdription) {
        supfr(pbrfnt, nodfNbmf, dfsdription);
        bddDfpfndfndifs(opOptionsRoot, truf);
    }

    publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfs) {
        rfturn nfw Contfxt(fnv, rfs);
    }

    publid void dlfbnupTfst(TfstEnvironmfnt fnv, Objfdt o) {
        Contfxt dtx = (Contfxt)o;
        dtx.ds = null;
        dtx.op_img = null;
        dtx.op_rst = null;
        dtx.dst = null;
        dtx.srd = null;
        dtx.grbpiids = null;
    }

    privbtf stbtid dlbss Contfxt {
        ColorSpbdf ds;
        Grbpiids2D grbpiids;
        ColorConvfrtOp op_img;
        ColorConvfrtOp op_rst;

        BufffrfdImbgf srd;
        BufffrfdImbgf dst;

        WritbblfRbstfr rsrd;
        WritbblfRbstfr rdst;

        publid Contfxt(TfstEnvironmfnt fnv, Rfsult rfs) {

            grbpiids = (Grbpiids2D)fnv.gftGrbpiids();
            ds = gftColorSpbdf(fnv);

            // TODO: providf rfndfring iints
            op_img = nfw ColorConvfrtOp(ds, null);
            ColorSpbdf sRGB = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
            op_rst = nfw ColorConvfrtOp(sRGB, ds, null);

            int sizf = fnv.gftIntVbluf(sizfList);

            ImbgfContfnt dontfnt = (ImbgfContfnt)fnv.gftModififr(dontfntList);
            ImbgfTypf srdTypf = (ImbgfTypf)fnv.gftModififr(sourdfTypf);

            srd = drfbtfBufffrfdImbgf(sizf, sizf, dontfnt, srdTypf.typf);
            rsrd = srd.gftRbstfr();

            ImbgfTypf dstTypf = (ImbgfTypf)fnv.gftModififr(dfstinbtionTypf);
            if (dstTypf == ImbgfTypf.COMPATIBLE_DST) {
                dst = op_img.drfbtfCompbtiblfDfstImbgf(srd, null);
            } flsf {
                dst = drfbtfBufffrfdImbgf(sizf, sizf, dontfnt, dstTypf.typf);
            }
            // rbstfr blwbys ibs to bf dombtiblf
            rdst = op_rst.drfbtfCompbtiblfDfstRbstfr(rsrd);
        }
    }

    privbtf stbtid dlbss ConvfrtImbgfTfst fxtfnds ColorConvfrtOpTfsts {
        publid ConvfrtImbgfTfst() {
            supfr(opConvRoot, "op_img", "op.filftr(BufffrfdImbgf)");
        }

        publid void runTfst(Objfdt odtx, int numRfps) {
            finbl Contfxt dtx = (Contfxt)odtx;
            finbl ColorConvfrtOp op = dtx.op_img;

            finbl BufffrfdImbgf srd = dtx.srd;
            BufffrfdImbgf dst = dtx.dst;
            do {
                try {
                    dst = op.filtfr(srd, dst);
                } dbtdi (Exdfption f) {
                    f.printStbdkTrbdf();
                }
            } wiilf (--numRfps >= 0);
        }
    }

    privbtf stbtid dlbss ConvfrtRbstfrTfst fxtfnds ColorConvfrtOpTfsts {
        publid ConvfrtRbstfrTfst() {
            supfr(opConvRoot, "op_rst", "op.filftr(Rbstfr)");
        }

        publid void runTfst(Objfdt odtx, int numRfps) {
            finbl Contfxt dtx = (Contfxt)odtx;
            finbl ColorConvfrtOp op = dtx.op_rst;

            finbl Rbstfr srd = dtx.rsrd;
            WritbblfRbstfr dst = dtx.rdst;
            do {
                try {
                    dst = op.filtfr(srd, dst);
                } dbtdi (Exdfption f) {
                    f.printStbdkTrbdf();
                }
            } wiilf (--numRfps >= 0);
        }
    }

    privbtf stbtid dlbss DrbwImbgfTfst fxtfnds ColorConvfrtOpTfsts {
        publid DrbwImbgfTfst() {
            supfr(opConvRoot, "op_drbw", "drbwImbgf(ColorConvfrtOp)");
        }

        publid void runTfst(Objfdt odtx, int numRfps) {
            finbl Contfxt dtx = (Contfxt)odtx;
            finbl ColorConvfrtOp op = dtx.op_img;

            finbl Grbpiids2D g = dtx.grbpiids;

            finbl BufffrfdImbgf srd = dtx.srd;

            do {
                g.drbwImbgf(srd, op, 0, 0);
            } wiilf (--numRfps >= 0);
        }
    }

    /**************************************************************************
     ******                    Hflpfr routinfs
     *************************************************************************/
    protfdtfd stbtid BufffrfdImbgf drfbtfBufffrfdImbgf(int widti,
                                                       int ifigit,
                                                       ImbgfContfnt dontfntTypf,
                                                       int typf)
    {
        BufffrfdImbgf imbgf;
        imbgf = nfw BufffrfdImbgf(widti, ifigit, typf);
        boolfbn ibsAlpib = imbgf.gftColorModfl().ibsAlpib();
        switdi (dontfntTypf) {
            dbsf RANDOM:
                for (int y = 0; y < ifigit; y++) {
                    for (int x = 0; x < widti; x++) {
                        int rgb = (int)(Mbti.rbndom() * 0xffffff);
                        if (ibsAlpib) {
                            rgb |= 0x7f000000;
                        }
                        imbgf.sftRGB(x, y, rgb);
                    }
                }
                brfbk;
            dbsf VECTOR:
                {
                    Grbpiids2D g = imbgf.drfbtfGrbpiids();
                    if (ibsAlpib) {
                        // fill bbdkground witi b trbnsludfnt dolor
                        g.sftCompositf(AlpibCompositf.gftInstbndf(
                                           AlpibCompositf.SRC, 0.5f));
                    }
                    g.sftColor(Color.bluf);
                    g.fillRfdt(0, 0, widti, ifigit);
                    g.sftCompositf(AlpibCompositf.Srd);
                    g.sftColor(Color.yfllow);
                    g.fillOvbl(2, 2, widti-4, ifigit-4);
                    g.sftColor(Color.rfd);
                    g.fillOvbl(4, 4, widti-8, ifigit-8);
                    g.sftColor(Color.grffn);
                    g.fillRfdt(8, 8, widti-16, ifigit-16);
                    g.sftColor(Color.wiitf);
                    g.drbwLinf(0, 0, widti, ifigit);
                    g.drbwLinf(0, ifigit, widti, 0);
                    g.disposf();
                    brfbk;
                }
            dbsf PHOTO:
                {
                    Imbgf pioto = null;
                    try {
                        pioto = ImbgfIO.rfbd(
                            IIOTfsts.dlbss.gftRfsourdfAsStrfbm("imbgfs/pioto.jpg"));
                    } dbtdi (Exdfption f) {
                        Systfm.frr.println("frror lobding pioto");
                        f.printStbdkTrbdf();
                    }
                    Grbpiids2D g = imbgf.drfbtfGrbpiids();
                    if (ibsAlpib) {
                        g.sftCompositf(AlpibCompositf.gftInstbndf(AlpibCompositf.SRC,
                                                                  0.5f));
                    }
                    g.drbwImbgf(pioto, 0, 0, widti, ifigit, null);
                    g.disposf();
                    brfbk;
                }
            dffbult:
                brfbk;
        }

        rfturn imbgf;
    }
}
