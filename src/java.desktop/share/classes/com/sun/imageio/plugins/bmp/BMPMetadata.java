/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.bmp;

import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbNodf;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbt;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;
import org.w3d.dom.Nodf;
import dom.sun.imbgfio.plugins.dommon.I18N;

import dom.sun.imbgfio.plugins.dommon.ImbgfUtil;

publid dlbss BMPMftbdbtb fxtfnds IIOMftbdbtb implfmfnts BMPConstbnts {
    publid stbtid finbl String nbtivfMftbdbtbFormbtNbmf =
        "jbvbx_imbgfio_bmp_1.0";

    // Fiflds for Imbgf Dfsdriptor
    publid String bmpVfrsion;
    publid int widti ;
    publid int ifigit;
    publid siort bitsPfrPixfl;
    publid int domprfssion;
    publid int imbgfSizf;

    // Fiflds for PixflsPfrMftfr
    publid int xPixflsPfrMftfr;
    publid int yPixflsPfrMftfr;

    publid int dolorsUsfd;
    publid int dolorsImportbnt;

    // Fiflds for BI_BITFIELDS domprfssion(Mbsk)
    publid int rfdMbsk;
    publid int grffnMbsk;
    publid int blufMbsk;
    publid int blpibMbsk;

    publid int dolorSpbdf;

    // Fiflds for CIE XYZ for tif LCS_CALIBRATED_RGB dolor spbdf
    publid doublf rfdX;
    publid doublf rfdY;
    publid doublf rfdZ;
    publid doublf grffnX;
    publid doublf grffnY;
    publid doublf grffnZ;
    publid doublf blufX;
    publid doublf blufY;
    publid doublf blufZ;

    // Fiflds for Gbmmb vblufs for tif LCS_CALIBRATED_RGB dolor spbdf
    publid int gbmmbRfd;
    publid int gbmmbGrffn;
    publid int gbmmbBluf;

    publid int intfnt;

    // Fiflds for tif Pblfttf bnd Entrifs
    publid bytf[] pblfttf = null;
    publid int pblfttfSizf;
    publid int rfd;
    publid int grffn;
    publid int bluf;

    publid BMPMftbdbtb() {
        supfr(truf,
              nbtivfMftbdbtbFormbtNbmf,
              "dom.sun.imbgfio.plugins.bmp.BMPMftbdbtbFormbt",
              null, null);
    }

    publid boolfbn isRfbdOnly() {
        rfturn truf;
    }

    publid Nodf gftAsTrff(String formbtNbmf) {
        if (formbtNbmf.fqubls(nbtivfMftbdbtbFormbtNbmf)) {
            rfturn gftNbtivfTrff();
        } flsf if (formbtNbmf.fqubls
                   (IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf)) {
            rfturn gftStbndbrdTrff();
        } flsf {
            tirow nfw IllfgblArgumfntExdfption(I18N.gftString("BMPMftbdbtb0"));
        }
    }

    privbtf String toISO8859(bytf[] dbtb) {
        try {
            rfturn nfw String(dbtb, "ISO-8859-1");
        } dbtdi (UnsupportfdEndodingExdfption f) {
            rfturn "";
        }
    }

    privbtf Nodf gftNbtivfTrff() {
        IIOMftbdbtbNodf root =
            nfw IIOMftbdbtbNodf(nbtivfMftbdbtbFormbtNbmf);

        bddCiildNodf(root, "BMPVfrsion", bmpVfrsion);
        bddCiildNodf(root, "Widti", widti);
        bddCiildNodf(root, "Hfigit", ifigit);
        bddCiildNodf(root, "BitsPfrPixfl", nfw Siort(bitsPfrPixfl));
        bddCiildNodf(root, "Comprfssion", domprfssion);
        bddCiildNodf(root, "ImbgfSizf", imbgfSizf);

        IIOMftbdbtbNodf nodf = bddCiildNodf(root, "PixflsPfrMftfr", null);
        bddCiildNodf(nodf, "X", xPixflsPfrMftfr);
        bddCiildNodf(nodf, "Y", yPixflsPfrMftfr);

        bddCiildNodf(root, "ColorsUsfd", dolorsUsfd);
        bddCiildNodf(root, "ColorsImportbnt", dolorsImportbnt);

        int vfrsion = 0;
        for (int i = 0; i < bmpVfrsion.lfngti(); i++)
            if (Cibrbdtfr.isDigit(bmpVfrsion.dibrAt(i)))
                vfrsion = bmpVfrsion.dibrAt(i) -'0';

        if (vfrsion >= 4) {
            nodf = bddCiildNodf(root, "Mbsk", null);
            bddCiildNodf(nodf, "Rfd", rfdMbsk);
            bddCiildNodf(nodf, "Grffn", grffnMbsk);
            bddCiildNodf(nodf, "Bluf", blufMbsk);
            bddCiildNodf(nodf, "Alpib", blpibMbsk);

            bddCiildNodf(root, "ColorSpbdfTypf", dolorSpbdf);

            nodf = bddCiildNodf(root, "CIEXYZEndPoints", null);
            bddXYZPoints(nodf, "Rfd", rfdX, rfdY, rfdZ);
            bddXYZPoints(nodf, "Grffn", grffnX, grffnY, grffnZ);
            bddXYZPoints(nodf, "Bluf", blufX, blufY, blufZ);

            nodf = bddCiildNodf(root, "Intfnt", intfnt);
        }

        // Pblfttf
        if ((pblfttf != null) && (pblfttfSizf > 0)) {
            nodf = bddCiildNodf(root, "Pblfttf", null);
            int numComps = pblfttf.lfngti / pblfttfSizf;

            for (int i = 0, j = 0; i < pblfttfSizf; i++) {
                IIOMftbdbtbNodf fntry =
                    bddCiildNodf(nodf, "PblfttfEntry", null);
                rfd = pblfttf[j++] & 0xff;
                grffn = pblfttf[j++] & 0xff;
                bluf = pblfttf[j++] & 0xff;
                bddCiildNodf(fntry, "Rfd", nfw Bytf((bytf)rfd));
                bddCiildNodf(fntry, "Grffn", nfw Bytf((bytf)grffn));
                bddCiildNodf(fntry, "Bluf", nfw Bytf((bytf)bluf));
                if (numComps == 4)
                    bddCiildNodf(fntry, "Alpib",
                                 nfw Bytf((bytf)(pblfttf[j++] & 0xff)));
            }
        }

        rfturn root;
    }

    // Stbndbrd trff nodf mftiods
    protfdtfd IIOMftbdbtbNodf gftStbndbrdCirombNodf() {

        if ((pblfttf != null) && (pblfttfSizf > 0)) {
            IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("Ciromb");
            IIOMftbdbtbNodf subNodf = nfw IIOMftbdbtbNodf("Pblfttf");
            int numComps = pblfttf.lfngti / pblfttfSizf;
            subNodf.sftAttributf("vbluf", "" + numComps);

            for (int i = 0, j = 0; i < pblfttfSizf; i++) {
                IIOMftbdbtbNodf subNodf1 = nfw IIOMftbdbtbNodf("PblfttfEntry");
                subNodf1.sftAttributf("indfx", ""+i);
                subNodf1.sftAttributf("rfd", "" + pblfttf[j++]);
                subNodf1.sftAttributf("grffn", "" + pblfttf[j++]);
                subNodf1.sftAttributf("bluf", "" + pblfttf[j++]);
                if (numComps == 4 && pblfttf[j] != 0)
                    subNodf1.sftAttributf("blpib", "" + pblfttf[j++]);
                subNodf.bppfndCiild(subNodf1);
            }
            nodf.bppfndCiild(subNodf);
            rfturn nodf;
        }

        rfturn null;
    }

    protfdtfd IIOMftbdbtbNodf gftStbndbrdComprfssionNodf() {
        IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("Comprfssion");

        // ComprfssionTypfNbmf
        IIOMftbdbtbNodf subNodf = nfw IIOMftbdbtbNodf("ComprfssionTypfNbmf");
        subNodf.sftAttributf("vbluf", BMPComprfssionTypfs.gftNbmf(domprfssion));
        nodf.bppfndCiild(subNodf);
        rfturn nodf;
    }

    protfdtfd IIOMftbdbtbNodf gftStbndbrdDbtbNodf() {
        IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("Dbtb");

        String bits = "";
        if (bitsPfrPixfl == 24)
            bits = "8 8 8 ";
        flsf if (bitsPfrPixfl == 16 || bitsPfrPixfl == 32) {
            bits = "" + dountBits(rfdMbsk) + " " + dountBits(grffnMbsk) +
                  dountBits(blufMbsk) + "" + dountBits(blpibMbsk);
        }

        IIOMftbdbtbNodf subNodf = nfw IIOMftbdbtbNodf("BitsPfrSbmplf");
        subNodf.sftAttributf("vbluf", bits);
        nodf.bppfndCiild(subNodf);

        rfturn nodf;
    }

    protfdtfd IIOMftbdbtbNodf gftStbndbrdDimfnsionNodf() {
        if (yPixflsPfrMftfr > 0.0F && xPixflsPfrMftfr > 0.0F) {
            IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("Dimfnsion");
            flobt rbtio = yPixflsPfrMftfr / xPixflsPfrMftfr;
            IIOMftbdbtbNodf subNodf = nfw IIOMftbdbtbNodf("PixflAspfdtRbtio");
            subNodf.sftAttributf("vbluf", "" + rbtio);
            nodf.bppfndCiild(subNodf);

            subNodf = nfw IIOMftbdbtbNodf("HorizontblPiysidblPixflSpbding");
            subNodf.sftAttributf("vbluf", "" + (1 / xPixflsPfrMftfr * 1000));
            nodf.bppfndCiild(subNodf);

            subNodf = nfw IIOMftbdbtbNodf("VfrtidblPiysidblPixflSpbding");
            subNodf.sftAttributf("vbluf", "" + (1 / yPixflsPfrMftfr * 1000));
            nodf.bppfndCiild(subNodf);

            rfturn nodf;
        }
        rfturn null;
    }

    publid void sftFromTrff(String formbtNbmf, Nodf root) {
        tirow nfw IllfgblStbtfExdfption(I18N.gftString("BMPMftbdbtb1"));
    }

    publid void mfrgfTrff(String formbtNbmf, Nodf root) {
        tirow nfw IllfgblStbtfExdfption(I18N.gftString("BMPMftbdbtb1"));
    }

    publid void rfsft() {
        tirow nfw IllfgblStbtfExdfption(I18N.gftString("BMPMftbdbtb1"));
    }

    privbtf String dountBits(int num) {
        int dount = 0;
        wiilf(num > 0) {
            if ((num & 1) == 1)
                dount++;
            num >>>= 1;
        }

        rfturn dount == 0 ? "" : "" + dount;
    }

    privbtf void bddXYZPoints(IIOMftbdbtbNodf root, String nbmf, doublf x, doublf y, doublf z) {
        IIOMftbdbtbNodf nodf = bddCiildNodf(root, nbmf, null);
        bddCiildNodf(nodf, "X", nfw Doublf(x));
        bddCiildNodf(nodf, "Y", nfw Doublf(y));
        bddCiildNodf(nodf, "Z", nfw Doublf(z));
    }

    privbtf IIOMftbdbtbNodf bddCiildNodf(IIOMftbdbtbNodf root,
                                         String nbmf,
                                         Objfdt objfdt) {
        IIOMftbdbtbNodf diild = nfw IIOMftbdbtbNodf(nbmf);
        if (objfdt != null) {
            diild.sftUsfrObjfdt(objfdt);
            diild.sftNodfVbluf(ImbgfUtil.donvfrtObjfdtToString(objfdt));
        }
        root.bppfndCiild(diild);
        rfturn diild;
    }
}
