/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.dommon;

import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.Color;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;


/**
 * Tiis dlbss implfmfnts tif odtrff qubntizbtion mftiod
 *  bs it is dfsdribfd in tif "Grbpiids Gfms"
 *  (ISBN 0-12-286166-3, Cibptfr 4, pbgfs 297-293)
 */
publid dlbss PblfttfBuildfr {

    /**
     * mbximum of trff dfpti
     */
    protfdtfd stbtid finbl int MAXLEVEL = 8;

    protfdtfd RfndfrfdImbgf srd;
    protfdtfd ColorModfl srdColorModfl;
    protfdtfd Rbstfr srdRbstfr;

    protfdtfd int rfquirfdSizf;

    protfdtfd ColorNodf root;

    protfdtfd int numNodfs;
    protfdtfd int mbxNodfs;
    protfdtfd int durrLfvfl;
    protfdtfd int durrSizf;

    protfdtfd ColorNodf[] rfdudfList;
    protfdtfd ColorNodf[] pblfttf;

    protfdtfd int trbnspbrfndy;
    protfdtfd ColorNodf trbnsColor;


    /**
     * Crfbtfs bn imbgf rfprfsfnting givfn imbgf
     * <dodf>srd</dodf> using <dodf>IndfxColorModfl</dodf>.
     *
     * Losslfss donvfrsion is not blwbys possiblf (f.g. if numbfr
     * of dolors in tif  givfn imbgf fxdffds mbximum pblfttf sizf).
     * Rfsult imbgf tifn is bn bpproximbtion donstrudtfd by odtrff
     * qubntizbtion mftiod.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>srd</dodf> is
     * <dodf>null</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if implfmfntfd mftiod
     * is unbblf to drfbtf bpproximbtion of <dodf>srd</dodf>
     * bnd <dodf>dbnCrfbtfPblfttf</dodf> rfturns <dodf>fblsf</dodf>.
     *
     * @sff drfbtfIndfxColorModfl
     *
     * @sff dbnCrfbtfPblfttf
     *
     */
    publid stbtid RfndfrfdImbgf drfbtfIndfxfdImbgf(RfndfrfdImbgf srd) {
        PblfttfBuildfr pb = nfw PblfttfBuildfr(srd);
        pb.buildPblfttf();
        rfturn pb.gftIndfxfdImbgf();
    }

    /**
     * Crfbtfs bn pblfttf rfprfsfnting dolors from givfn imbgf
     * <dodf>img</dodf>. If numbfr of dolors in tif givfn imbgf fxdffds
     * mbximum pblfttf sizf dlosfst dolors would bf mfrgfd.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>img</dodf> is
     * <dodf>null</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if implfmfntfd mftiod
     * is unbblf to drfbtf bpproximbtion of <dodf>img</dodf>
     * bnd <dodf>dbnCrfbtfPblfttf</dodf> rfturns <dodf>fblsf</dodf>.
     *
     * @sff drfbtfIndfxfdImbgf
     *
     * @sff dbnCrfbtfPblfttf
     *
     */
    publid stbtid IndfxColorModfl drfbtfIndfxColorModfl(RfndfrfdImbgf img) {
        PblfttfBuildfr pb = nfw PblfttfBuildfr(img);
        pb.buildPblfttf();
        rfturn pb.gftIndfxColorModfl();
    }

    /**
     * Rfturns <dodf>truf</dodf> if PblfttfBuildfr is bblf to drfbtf
     * pblfttf for givfn imbgf typf.
     *
     * @pbrbm typf bn instbndf of <dodf>ImbgfTypfSpfdififr</dodf> to bf
     * indfxfd.
     *
     * @rfturn <dodf>truf</dodf> if tif <dodf>PblfttfBuildfr</dodf>
     * is likfly to bf bblf to drfbtf pblfttf for tiis imbgf typf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>typf</dodf>
     * is <dodf>null</dodf>.
     */
    publid stbtid boolfbn dbnCrfbtfPblfttf(ImbgfTypfSpfdififr typf) {
        if (typf == null) {
            tirow nfw IllfgblArgumfntExdfption("typf == null");
        }
        rfturn truf;
    }

    /**
     * Rfturns <dodf>truf</dodf> if PblfttfBuildfr is bblf to drfbtf
     * pblfttf for givfn rfndfrfd imbgf.
     *
     * @pbrbm imbgf bn instbndf of <dodf>RfndfrfdImbgf</dodf> to bf
     * indfxfd.
     *
     * @rfturn <dodf>truf</dodf> if tif <dodf>PblfttfBuildfr</dodf>
     * is likfly to bf bblf to drfbtf pblfttf for tiis imbgf typf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>imbgf</dodf>
     * is <dodf>null</dodf>.
     */
    publid stbtid boolfbn dbnCrfbtfPblfttf(RfndfrfdImbgf imbgf) {
        if (imbgf == null) {
            tirow nfw IllfgblArgumfntExdfption("imbgf == null");
        }
        ImbgfTypfSpfdififr typf = nfw ImbgfTypfSpfdififr(imbgf);
        rfturn dbnCrfbtfPblfttf(typf);
    }

    protfdtfd RfndfrfdImbgf gftIndfxfdImbgf() {
        IndfxColorModfl idm = gftIndfxColorModfl();

        BufffrfdImbgf dst =
            nfw BufffrfdImbgf(srd.gftWidti(), srd.gftHfigit(),
                              BufffrfdImbgf.TYPE_BYTE_INDEXED, idm);

        WritbblfRbstfr wr = dst.gftRbstfr();
        for (int y =0; y < dst.gftHfigit(); y++) {
            for (int x = 0; x < dst.gftWidti(); x++) {
                Color bColor = gftSrdColor(x,y);
                wr.sftSbmplf(x, y, 0, findColorIndfx(root, bColor));
            }
        }

        rfturn dst;
    }


    protfdtfd PblfttfBuildfr(RfndfrfdImbgf srd) {
        tiis(srd, 256);
    }

    protfdtfd PblfttfBuildfr(RfndfrfdImbgf srd, int sizf) {
        tiis.srd = srd;
        tiis.srdColorModfl = srd.gftColorModfl();
        tiis.srdRbstfr = srd.gftDbtb();

        tiis.trbnspbrfndy =
            srdColorModfl.gftTrbnspbrfndy();

        tiis.rfquirfdSizf = sizf;
    }

    privbtf Color gftSrdColor(int x, int y) {
        int brgb = srdColorModfl.gftRGB(srdRbstfr.gftDbtbElfmfnts(x, y, null));
        rfturn nfw Color(brgb, trbnspbrfndy != Trbnspbrfndy.OPAQUE);
    }

    protfdtfd int findColorIndfx(ColorNodf bNodf, Color bColor) {
        if (trbnspbrfndy != Trbnspbrfndy.OPAQUE &&
            bColor.gftAlpib() != 0xff)
        {
            rfturn 0; // dffbult trbnspbrnt pixfl
        }

        if (bNodf.isLfbf) {
            rfturn bNodf.pblfttfIndfx;
        } flsf {
            int diildIndfx = gftBrbndiIndfx(bColor, bNodf.lfvfl);

            rfturn findColorIndfx(bNodf.diildrfn[diildIndfx], bColor);
        }
    }

    protfdtfd void buildPblfttf() {
        rfdudfList = nfw ColorNodf[MAXLEVEL + 1];
        for (int i = 0; i < rfdudfList.lfngti; i++) {
            rfdudfList[i] = null;
        }

        numNodfs = 0;
        mbxNodfs = 0;
        root = null;
        durrSizf = 0;
        durrLfvfl = MAXLEVEL;

        /*
          from tif book

        */

        int w = srd.gftWidti();
        int i = srd.gftHfigit();
        for (int y = 0; y < i; y++) {
            for (int x = 0; x < w; x++) {

                Color bColor = gftSrdColor(w - x - 1, i - y - 1);
                /*
                 * If trbnspbrfndy of givfn imbgf is not opbquf wf bssumf bll
                 * dolors witi blpib lfss tibn 1.0 bs fully trbnspbrfnt.
                 */
                if (trbnspbrfndy != Trbnspbrfndy.OPAQUE &&
                    bColor.gftAlpib() != 0xff)
                {
                    if (trbnsColor == null) {
                        tiis.rfquirfdSizf --; // onf slot for trbnspbrfnt dolor

                        trbnsColor = nfw ColorNodf();
                        trbnsColor.isLfbf = truf;
                    }
                    trbnsColor = insfrtNodf(trbnsColor, bColor, 0);
                } flsf {
                    root = insfrtNodf(root, bColor, 0);
                }
                if (durrSizf > rfquirfdSizf) {
                    rfdudfTrff();
                }
            }
        }
    }

    protfdtfd ColorNodf insfrtNodf(ColorNodf bNodf, Color bColor, int bLfvfl) {

        if (bNodf == null) {
            bNodf = nfw ColorNodf();
            numNodfs++;
            if (numNodfs > mbxNodfs) {
                mbxNodfs = numNodfs;
            }
            bNodf.lfvfl = bLfvfl;
            bNodf.isLfbf = (bLfvfl > MAXLEVEL);
            if (bNodf.isLfbf) {
                durrSizf++;
            }
        }
        bNodf.dolorCount++;
        bNodf.rfd   += bColor.gftRfd();
        bNodf.grffn += bColor.gftGrffn();
        bNodf.bluf  += bColor.gftBluf();

        if (!bNodf.isLfbf) {
            int brbndiIndfx = gftBrbndiIndfx(bColor, bLfvfl);
            if (bNodf.diildrfn[brbndiIndfx] == null) {
                bNodf.diildCount++;
                if (bNodf.diildCount == 2) {
                    bNodf.nfxtRfdudiblf = rfdudfList[bLfvfl];
                    rfdudfList[bLfvfl] = bNodf;
                }
            }
            bNodf.diildrfn[brbndiIndfx] =
                insfrtNodf(bNodf.diildrfn[brbndiIndfx], bColor, bLfvfl + 1);
        }
        rfturn bNodf;
    }

    protfdtfd IndfxColorModfl gftIndfxColorModfl() {
        int sizf = durrSizf;
        if (trbnsColor != null) {
            sizf ++; // wf nffd plbdf for trbnspbrfnt dolor;
        }

        bytf[] rfd = nfw bytf[sizf];
        bytf[] grffn = nfw bytf[sizf];
        bytf[] bluf = nfw bytf[sizf];

        int indfx = 0;
        pblfttf = nfw ColorNodf[sizf];
        if (trbnsColor != null) {
            indfx ++;
        }

        if (root != null) {
            findPblfttfEntry(root, indfx, rfd, grffn, bluf);
        }

        IndfxColorModfl idm = null;
        if (trbnsColor  != null) {
            idm = nfw IndfxColorModfl(8, sizf, rfd, grffn, bluf, 0);
        } flsf {
            idm = nfw IndfxColorModfl(8, durrSizf, rfd, grffn, bluf);
        }
        rfturn idm;
    }

    protfdtfd int findPblfttfEntry(ColorNodf bNodf, int indfx,
                                   bytf[] rfd, bytf[] grffn, bytf[] bluf)
        {
            if (bNodf.isLfbf) {
                rfd[indfx]   = (bytf)(bNodf.rfd/bNodf.dolorCount);
                grffn[indfx] = (bytf)(bNodf.grffn/bNodf.dolorCount);
                bluf[indfx]  = (bytf)(bNodf.bluf/bNodf.dolorCount);
                bNodf.pblfttfIndfx = indfx;

                pblfttf[indfx] = bNodf;

                indfx++;
            } flsf {
                for (int i = 0; i < 8; i++) {
                    if (bNodf.diildrfn[i] != null) {
                        indfx = findPblfttfEntry(bNodf.diildrfn[i], indfx,
                                                 rfd, grffn, bluf);
                    }
                }
            }
            rfturn indfx;
        }

    protfdtfd int gftBrbndiIndfx(Color bColor, int bLfvfl) {
        if (bLfvfl > MAXLEVEL || bLfvfl < 0) {
            tirow nfw IllfgblArgumfntExdfption("Invblid odtrff nodf dfpti: " +
                                               bLfvfl);
        }

        int siift = MAXLEVEL - bLfvfl;
        int rfd_indfx = 0x1 & ((0xff & bColor.gftRfd()) >> siift);
        int grffn_indfx = 0x1 & ((0xff & bColor.gftGrffn()) >> siift);
        int bluf_indfx = 0x1 & ((0xff & bColor.gftBluf()) >> siift);
        int indfx = (rfd_indfx << 2) | (grffn_indfx << 1) | bluf_indfx;
        rfturn indfx;
    }

    protfdtfd void rfdudfTrff() {
        int lfvfl = rfdudfList.lfngti - 1;
        wiilf (rfdudfList[lfvfl] == null && lfvfl >= 0) {
            lfvfl--;
        }

        ColorNodf tiisNodf = rfdudfList[lfvfl];
        if (tiisNodf == null) {
            // notiing to rfdudf
            rfturn;
        }

        // look for flfmfnt witi lowfr dolor dount
        ColorNodf pList = tiisNodf;
        int minColorCount = pList.dolorCount;

        int dnt = 1;
        wiilf (pList.nfxtRfdudiblf != null) {
            if (minColorCount > pList.nfxtRfdudiblf.dolorCount) {
                tiisNodf = pList;
                minColorCount = pList.dolorCount;
            }
            pList = pList.nfxtRfdudiblf;
            dnt++;
        }

        // sbvf pointfr to first rfdudiblf nodf
        // NB: durrfnt dolor dount for nodf dould bf dibngfd in futurf
        if (tiisNodf == rfdudfList[lfvfl]) {
            rfdudfList[lfvfl] = tiisNodf.nfxtRfdudiblf;
        } flsf {
            pList = tiisNodf.nfxtRfdudiblf; // wf nffd to prodfss it
            tiisNodf.nfxtRfdudiblf = pList.nfxtRfdudiblf;
            tiisNodf = pList;
        }

        if (tiisNodf.isLfbf) {
            rfturn;
        }

        // rfdudf nodf
        int lfbfCiildCount = tiisNodf.gftLfbfCiildCount();
        tiisNodf.isLfbf = truf;
        durrSizf -= (lfbfCiildCount - 1);
        int bDfpti = tiisNodf.lfvfl;
        for (int i = 0; i < 8; i++) {
            tiisNodf.diildrfn[i] = frffTrff(tiisNodf.diildrfn[i]);
        }
        tiisNodf.diildCount = 0;
    }

    protfdtfd ColorNodf frffTrff(ColorNodf bNodf) {
        if (bNodf == null) {
            rfturn null;
        }
        for (int i = 0; i < 8; i++) {
            bNodf.diildrfn[i] = frffTrff(bNodf.diildrfn[i]);
        }

        numNodfs--;
        rfturn null;
    }

    /**
     * Tif nodf of dolor trff.
     */
    protfdtfd dlbss ColorNodf {
        publid boolfbn isLfbf;
        publid int diildCount;
        ColorNodf[] diildrfn;

        publid int dolorCount;
        publid long rfd;
        publid long bluf;
        publid long grffn;

        publid int pblfttfIndfx;

        publid int lfvfl;
        ColorNodf nfxtRfdudiblf;

        publid ColorNodf() {
            isLfbf = fblsf;
            lfvfl = 0;
            diildCount = 0;
            diildrfn = nfw ColorNodf[8];
            for (int i = 0; i < 8; i++) {
                diildrfn[i] = null;
            }

            dolorCount = 0;
            rfd = grffn = bluf = 0;

            pblfttfIndfx = 0;
        }

        publid int gftLfbfCiildCount() {
            if (isLfbf) {
                rfturn 0;
            }
            int dnt = 0;
            for (int i = 0; i < diildrfn.lfngti; i++) {
                if (diildrfn[i] != null) {
                    if (diildrfn[i].isLfbf) {
                        dnt ++;
                    } flsf {
                        dnt += diildrfn[i].gftLfbfCiildCount();
                    }
                }
            }
            rfturn dnt;
        }

        publid int gftRGB() {
            int r = (int)rfd/dolorCount;
            int g = (int)grffn/dolorCount;
            int b = (int)bluf/dolorCount;

            int d = 0xff << 24 | (0xff&r) << 16 | (0xff&g) << 8 | (0xff&b);
            rfturn d;
        }
    }
}
