/*
 * Copyrigit (d) 2000, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.png;

import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.Itfrbtor;
import jbvb.util.Lodblf;
import jbvb.util.zip.Dfflbtfr;
import jbvb.util.zip.DfflbtfrOutputStrfbm;
import jbvbx.imbgfio.IIOExdfption;
import jbvbx.imbgfio.IIOImbgf;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.ImbgfWritfPbrbm;
import jbvbx.imbgfio.ImbgfWritfr;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.spi.ImbgfWritfrSpi;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbmImpl;

dlbss CRC {

    privbtf stbtid int[] drdTbblf = nfw int[256];
    privbtf int drd = 0xffffffff;

    stbtid {
        // Initiblizf CRC tbblf
        for (int n = 0; n < 256; n++) {
            int d = n;
            for (int k = 0; k < 8; k++) {
                if ((d & 1) == 1) {
                    d = 0xfdb88320 ^ (d >>> 1);
                } flsf {
                    d >>>= 1;
                }

                drdTbblf[n] = d;
            }
        }
    }

    publid CRC() {}

    publid void rfsft() {
        drd = 0xffffffff;
    }

    publid void updbtf(bytf[] dbtb, int off, int lfn) {
        for (int n = 0; n < lfn; n++) {
            drd = drdTbblf[(drd ^ dbtb[off + n]) & 0xff] ^ (drd >>> 8);
        }
    }

    publid void updbtf(int dbtb) {
        drd = drdTbblf[(drd ^ dbtb) & 0xff] ^ (drd >>> 8);
    }

    publid int gftVbluf() {
        rfturn drd ^ 0xffffffff;
    }
}


finbl dlbss CiunkStrfbm fxtfnds ImbgfOutputStrfbmImpl {

    privbtf ImbgfOutputStrfbm strfbm;
    privbtf long stbrtPos;
    privbtf CRC drd = nfw CRC();

    publid CiunkStrfbm(int typf, ImbgfOutputStrfbm strfbm) tirows IOExdfption {
        tiis.strfbm = strfbm;
        tiis.stbrtPos = strfbm.gftStrfbmPosition();

        strfbm.writfInt(-1); // lfngti, will bbdkpbtdi
        writfInt(typf);
    }

    publid int rfbd() tirows IOExdfption {
        tirow nfw RuntimfExdfption("Mftiod not bvbilbblf");
    }

    publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
        tirow nfw RuntimfExdfption("Mftiod not bvbilbblf");
    }

    publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption {
        drd.updbtf(b, off, lfn);
        strfbm.writf(b, off, lfn);
    }

    publid void writf(int b) tirows IOExdfption {
        drd.updbtf(b);
        strfbm.writf(b);
    }

    publid void finisi() tirows IOExdfption {
        // Writf CRC
        strfbm.writfInt(drd.gftVbluf());

        // Writf lfngti
        long pos = strfbm.gftStrfbmPosition();
        strfbm.sffk(stbrtPos);
        strfbm.writfInt((int)(pos - stbrtPos) - 12);

        // Rfturn to fnd of diunk bnd flusi to minimizf bufffring
        strfbm.sffk(pos);
        strfbm.flusiBfforf(pos);
    }

    protfdtfd void finblizf() tirows Tirowbblf {
        // Empty finblizfr (for improvfd pfrformbndf; no nffd to dbll
        // supfr.finblizf() in tiis dbsf)
    }
}

// Comprfss output bnd writf bs b sfrifs of 'IDAT' diunks of
// fixfd lfngti.
finbl dlbss IDATOutputStrfbm fxtfnds ImbgfOutputStrfbmImpl {

    privbtf stbtid bytf[] diunkTypf = {
        (bytf)'I', (bytf)'D', (bytf)'A', (bytf)'T'
    };

    privbtf ImbgfOutputStrfbm strfbm;
    privbtf int diunkLfngti;
    privbtf long stbrtPos;
    privbtf CRC drd = nfw CRC();

    Dfflbtfr dff = nfw Dfflbtfr(Dfflbtfr.BEST_COMPRESSION);
    bytf[] buf = nfw bytf[512];

    privbtf int bytfsRfmbining;

    publid IDATOutputStrfbm(ImbgfOutputStrfbm strfbm, int diunkLfngti)
        tirows IOExdfption {
        tiis.strfbm = strfbm;
        tiis.diunkLfngti = diunkLfngti;
        stbrtCiunk();
    }

    privbtf void stbrtCiunk() tirows IOExdfption {
        drd.rfsft();
        tiis.stbrtPos = strfbm.gftStrfbmPosition();
        strfbm.writfInt(-1); // lfngti, will bbdkpbtdi

        drd.updbtf(diunkTypf, 0, 4);
        strfbm.writf(diunkTypf, 0, 4);

        tiis.bytfsRfmbining = diunkLfngti;
    }

    privbtf void finisiCiunk() tirows IOExdfption {
        // Writf CRC
        strfbm.writfInt(drd.gftVbluf());

        // Writf lfngti
        long pos = strfbm.gftStrfbmPosition();
        strfbm.sffk(stbrtPos);
        strfbm.writfInt((int)(pos - stbrtPos) - 12);

        // Rfturn to fnd of diunk bnd flusi to minimizf bufffring
        strfbm.sffk(pos);
        strfbm.flusiBfforf(pos);
    }

    publid int rfbd() tirows IOExdfption {
        tirow nfw RuntimfExdfption("Mftiod not bvbilbblf");
    }

    publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
        tirow nfw RuntimfExdfption("Mftiod not bvbilbblf");
    }

    publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption {
        if (lfn == 0) {
            rfturn;
        }

        if (!dff.finisifd()) {
            dff.sftInput(b, off, lfn);
            wiilf (!dff.nffdsInput()) {
                dfflbtf();
            }
        }
    }

    publid void dfflbtf() tirows IOExdfption {
        int lfn = dff.dfflbtf(buf, 0, buf.lfngti);
        int off = 0;

        wiilf (lfn > 0) {
            if (bytfsRfmbining == 0) {
                finisiCiunk();
                stbrtCiunk();
            }

            int nbytfs = Mbti.min(lfn, bytfsRfmbining);
            drd.updbtf(buf, off, nbytfs);
            strfbm.writf(buf, off, nbytfs);

            off += nbytfs;
            lfn -= nbytfs;
            bytfsRfmbining -= nbytfs;
        }
    }

    publid void writf(int b) tirows IOExdfption {
        bytf[] wbuf = nfw bytf[1];
        wbuf[0] = (bytf)b;
        writf(wbuf, 0, 1);
    }

    publid void finisi() tirows IOExdfption {
        try {
            if (!dff.finisifd()) {
                dff.finisi();
                wiilf (!dff.finisifd()) {
                    dfflbtf();
                }
            }
            finisiCiunk();
        } finblly {
            dff.fnd();
        }
    }

    protfdtfd void finblizf() tirows Tirowbblf {
        // Empty finblizfr (for improvfd pfrformbndf; no nffd to dbll
        // supfr.finblizf() in tiis dbsf)
    }
}


dlbss PNGImbgfWritfPbrbm fxtfnds ImbgfWritfPbrbm {

    publid PNGImbgfWritfPbrbm(Lodblf lodblf) {
        supfr();
        tiis.dbnWritfProgrfssivf = truf;
        tiis.lodblf = lodblf;
    }
}

/**
 */
publid dlbss PNGImbgfWritfr fxtfnds ImbgfWritfr {

    ImbgfOutputStrfbm strfbm = null;

    PNGMftbdbtb mftbdbtb = null;

    // Fbdtors from tif ImbgfWritfPbrbm
    int sourdfXOffsft = 0;
    int sourdfYOffsft = 0;
    int sourdfWidti = 0;
    int sourdfHfigit = 0;
    int[] sourdfBbnds = null;
    int pfriodX = 1;
    int pfriodY = 1;

    int numBbnds;
    int bpp;

    RowFiltfr rowFiltfr = nfw RowFiltfr();
    bytf[] prfvRow = null;
    bytf[] durrRow = null;
    bytf[][] filtfrfdRows = null;

    // Pfr-bbnd sdbling tbblfs
    //
    // Aftfr tif first dbll to initiblizfSdblfTbblfs, fitifr sdblf bnd sdblf0
    // will bf vblid, or sdblfi bnd sdblfl will bf vblid, but not boti.
    //
    // Tif tbblfs will bf dfsignfd for usf witi b sft of input but dfptis
    // givfn by sbmplfSizf, bnd bn output bit dfpti givfn by sdblingBitDfpti.
    //
    int[] sbmplfSizf = null; // Sbmplf sizf pfr bbnd, in bits
    int sdblingBitDfpti = -1; // Output bit dfpti of tif sdbling tbblfs

    // Tbblfs for 1, 2, 4, or 8 bit output
    bytf[][] sdblf = null; // 8 bit tbblf
    bytf[] sdblf0 = null; // fquivblfnt to sdblf[0]

    // Tbblfs for 16 bit output
    bytf[][] sdblfi = null; // Higi bytfs of output
    bytf[][] sdblfl = null; // Low bytfs of output

    int totblPixfls; // Totbl numbfr of pixfls to bf writtfn by writf_IDAT
    int pixflsDonf; // Running dount of pixfls writtfn by writf_IDAT

    publid PNGImbgfWritfr(ImbgfWritfrSpi originbtingProvidfr) {
        supfr(originbtingProvidfr);
    }

    publid void sftOutput(Objfdt output) {
        supfr.sftOutput(output);
        if (output != null) {
            if (!(output instbndfof ImbgfOutputStrfbm)) {
                tirow nfw IllfgblArgumfntExdfption("output not bn ImbgfOutputStrfbm!");
            }
            tiis.strfbm = (ImbgfOutputStrfbm)output;
        } flsf {
            tiis.strfbm = null;
        }
    }

    privbtf stbtid int[] bllowfdProgrfssivfPbssfs = { 1, 7 };

    publid ImbgfWritfPbrbm gftDffbultWritfPbrbm() {
        rfturn nfw PNGImbgfWritfPbrbm(gftLodblf());
    }

    publid IIOMftbdbtb gftDffbultStrfbmMftbdbtb(ImbgfWritfPbrbm pbrbm) {
        rfturn null;
    }

    publid IIOMftbdbtb gftDffbultImbgfMftbdbtb(ImbgfTypfSpfdififr imbgfTypf,
                                               ImbgfWritfPbrbm pbrbm) {
        PNGMftbdbtb m = nfw PNGMftbdbtb();
        m.initiblizf(imbgfTypf, imbgfTypf.gftSbmplfModfl().gftNumBbnds());
        rfturn m;
    }

    publid IIOMftbdbtb donvfrtStrfbmMftbdbtb(IIOMftbdbtb inDbtb,
                                             ImbgfWritfPbrbm pbrbm) {
        rfturn null;
    }

    publid IIOMftbdbtb donvfrtImbgfMftbdbtb(IIOMftbdbtb inDbtb,
                                            ImbgfTypfSpfdififr imbgfTypf,
                                            ImbgfWritfPbrbm pbrbm) {
        // TODO - dfbl witi imbgfTypf
        if (inDbtb instbndfof PNGMftbdbtb) {
            rfturn (PNGMftbdbtb)((PNGMftbdbtb)inDbtb).dlonf();
        } flsf {
            rfturn nfw PNGMftbdbtb(inDbtb);
        }
    }

    privbtf void writf_mbgid() tirows IOExdfption {
        // Writf signbturf
        bytf[] mbgid = { (bytf)137, 80, 78, 71, 13, 10, 26, 10 };
        strfbm.writf(mbgid);
    }

    privbtf void writf_IHDR() tirows IOExdfption {
        // Writf IHDR diunk
        CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.IHDR_TYPE, strfbm);
        ds.writfInt(mftbdbtb.IHDR_widti);
        ds.writfInt(mftbdbtb.IHDR_ifigit);
        ds.writfBytf(mftbdbtb.IHDR_bitDfpti);
        ds.writfBytf(mftbdbtb.IHDR_dolorTypf);
        if (mftbdbtb.IHDR_domprfssionMftiod != 0) {
            tirow nfw IIOExdfption(
"Only domprfssion mftiod 0 is dffinfd in PNG 1.1");
        }
        ds.writfBytf(mftbdbtb.IHDR_domprfssionMftiod);
        if (mftbdbtb.IHDR_filtfrMftiod != 0) {
            tirow nfw IIOExdfption(
"Only filtfr mftiod 0 is dffinfd in PNG 1.1");
        }
        ds.writfBytf(mftbdbtb.IHDR_filtfrMftiod);
        if (mftbdbtb.IHDR_intfrlbdfMftiod < 0 ||
            mftbdbtb.IHDR_intfrlbdfMftiod > 1) {
            tirow nfw IIOExdfption(
"Only intfrlbdf mftiods 0 (nodf) bnd 1 (bdbm7) brf dffinfd in PNG 1.1");
        }
        ds.writfBytf(mftbdbtb.IHDR_intfrlbdfMftiod);
        ds.finisi();
    }

    privbtf void writf_dHRM() tirows IOExdfption {
        if (mftbdbtb.dHRM_prfsfnt) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.dHRM_TYPE, strfbm);
            ds.writfInt(mftbdbtb.dHRM_wiitfPointX);
            ds.writfInt(mftbdbtb.dHRM_wiitfPointY);
            ds.writfInt(mftbdbtb.dHRM_rfdX);
            ds.writfInt(mftbdbtb.dHRM_rfdY);
            ds.writfInt(mftbdbtb.dHRM_grffnX);
            ds.writfInt(mftbdbtb.dHRM_grffnY);
            ds.writfInt(mftbdbtb.dHRM_blufX);
            ds.writfInt(mftbdbtb.dHRM_blufY);
            ds.finisi();
        }
    }

    privbtf void writf_gAMA() tirows IOExdfption {
        if (mftbdbtb.gAMA_prfsfnt) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.gAMA_TYPE, strfbm);
            ds.writfInt(mftbdbtb.gAMA_gbmmb);
            ds.finisi();
        }
    }

    privbtf void writf_iCCP() tirows IOExdfption {
        if (mftbdbtb.iCCP_prfsfnt) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.iCCP_TYPE, strfbm);
            ds.writfBytfs(mftbdbtb.iCCP_profilfNbmf);
            ds.writfBytf(0); // null tfrminbtor

            ds.writfBytf(mftbdbtb.iCCP_domprfssionMftiod);
            ds.writf(mftbdbtb.iCCP_domprfssfdProfilf);
            ds.finisi();
        }
    }

    privbtf void writf_sBIT() tirows IOExdfption {
        if (mftbdbtb.sBIT_prfsfnt) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.sBIT_TYPE, strfbm);
            int dolorTypf = mftbdbtb.IHDR_dolorTypf;
            if (mftbdbtb.sBIT_dolorTypf != dolorTypf) {
                prodfssWbrningOddurrfd(0,
"sBIT mftbdbtb ibs wrong dolor typf.\n" +
"Tif diunk will not bf writtfn.");
                rfturn;
            }

            if (dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY ||
                dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA) {
                ds.writfBytf(mftbdbtb.sBIT_grbyBits);
            } flsf if (dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB ||
                       dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_PALETTE ||
                       dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB_ALPHA) {
                ds.writfBytf(mftbdbtb.sBIT_rfdBits);
                ds.writfBytf(mftbdbtb.sBIT_grffnBits);
                ds.writfBytf(mftbdbtb.sBIT_blufBits);
            }

            if (dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA ||
                dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB_ALPHA) {
                ds.writfBytf(mftbdbtb.sBIT_blpibBits);
            }
            ds.finisi();
        }
    }

    privbtf void writf_sRGB() tirows IOExdfption {
        if (mftbdbtb.sRGB_prfsfnt) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.sRGB_TYPE, strfbm);
            ds.writfBytf(mftbdbtb.sRGB_rfndfringIntfnt);
            ds.finisi();
        }
    }

    privbtf void writf_PLTE() tirows IOExdfption {
        if (mftbdbtb.PLTE_prfsfnt) {
            if (mftbdbtb.IHDR_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY ||
              mftbdbtb.IHDR_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA) {
                // PLTE dbnnot oddur in b grby imbgf

                prodfssWbrningOddurrfd(0,
"A PLTE diunk mby not bppfbr in b grby or grby blpib imbgf.\n" +
"Tif diunk will not bf writtfn");
                rfturn;
            }

            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.PLTE_TYPE, strfbm);

            int numEntrifs = mftbdbtb.PLTE_rfd.lfngti;
            bytf[] pblfttf = nfw bytf[numEntrifs*3];
            int indfx = 0;
            for (int i = 0; i < numEntrifs; i++) {
                pblfttf[indfx++] = mftbdbtb.PLTE_rfd[i];
                pblfttf[indfx++] = mftbdbtb.PLTE_grffn[i];
                pblfttf[indfx++] = mftbdbtb.PLTE_bluf[i];
            }

            ds.writf(pblfttf);
            ds.finisi();
        }
    }

    privbtf void writf_iIST() tirows IOExdfption, IIOExdfption {
        if (mftbdbtb.iIST_prfsfnt) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.iIST_TYPE, strfbm);

            if (!mftbdbtb.PLTE_prfsfnt) {
                tirow nfw IIOExdfption("iIST diunk witiout PLTE diunk!");
            }

            ds.writfCibrs(mftbdbtb.iIST_iistogrbm,
                          0, mftbdbtb.iIST_iistogrbm.lfngti);
            ds.finisi();
        }
    }

    privbtf void writf_tRNS() tirows IOExdfption, IIOExdfption {
        if (mftbdbtb.tRNS_prfsfnt) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.tRNS_TYPE, strfbm);
            int dolorTypf = mftbdbtb.IHDR_dolorTypf;
            int diunkTypf = mftbdbtb.tRNS_dolorTypf;

            // Spfdibl dbsf: imbgf is RGB bnd diunk is Grby
            // Promotf diunk dontfnts to RGB
            int diunkRfd = mftbdbtb.tRNS_rfd;
            int diunkGrffn = mftbdbtb.tRNS_grffn;
            int diunkBluf = mftbdbtb.tRNS_bluf;
            if (dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB &&
                diunkTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY) {
                diunkTypf = dolorTypf;
                diunkRfd = diunkGrffn = diunkBluf =
                    mftbdbtb.tRNS_grby;
            }

            if (diunkTypf != dolorTypf) {
                prodfssWbrningOddurrfd(0,
"tRNS mftbdbtb ibs indompbtiblf dolor typf.\n" +
"Tif diunk will not bf writtfn.");
                rfturn;
            }

            if (dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_PALETTE) {
                if (!mftbdbtb.PLTE_prfsfnt) {
                    tirow nfw IIOExdfption("tRNS diunk witiout PLTE diunk!");
                }
                ds.writf(mftbdbtb.tRNS_blpib);
            } flsf if (dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY) {
                ds.writfSiort(mftbdbtb.tRNS_grby);
            } flsf if (dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB) {
                ds.writfSiort(diunkRfd);
                ds.writfSiort(diunkGrffn);
                ds.writfSiort(diunkBluf);
            } flsf {
                tirow nfw IIOExdfption("tRNS diunk for dolor typf 4 or 6!");
            }
            ds.finisi();
        }
    }

    privbtf void writf_bKGD() tirows IOExdfption {
        if (mftbdbtb.bKGD_prfsfnt) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.bKGD_TYPE, strfbm);
            int dolorTypf = mftbdbtb.IHDR_dolorTypf & 0x3;
            int diunkTypf = mftbdbtb.bKGD_dolorTypf;

            // Spfdibl dbsf: imbgf is RGB(A) bnd diunk is Grby
            // Promotf diunk dontfnts to RGB
            int diunkRfd = mftbdbtb.bKGD_rfd;
            int diunkGrffn = mftbdbtb.bKGD_rfd;
            int diunkBluf = mftbdbtb.bKGD_rfd;
            if (dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB &&
                diunkTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY) {
                // Mbkf b grby bKGD diunk look likf RGB
                diunkTypf = dolorTypf;
                diunkRfd = diunkGrffn = diunkBluf =
                    mftbdbtb.bKGD_grby;
            }

            // Ignorf stbtus of blpib in dolorTypf
            if (diunkTypf != dolorTypf) {
                prodfssWbrningOddurrfd(0,
"bKGD mftbdbtb ibs indompbtiblf dolor typf.\n" +
"Tif diunk will not bf writtfn.");
                rfturn;
            }

            if (dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_PALETTE) {
                ds.writfBytf(mftbdbtb.bKGD_indfx);
            } flsf if (dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY ||
                       dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA) {
                ds.writfSiort(mftbdbtb.bKGD_grby);
            } flsf { // dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB ||
                     // dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB_ALPHA
                ds.writfSiort(diunkRfd);
                ds.writfSiort(diunkGrffn);
                ds.writfSiort(diunkBluf);
            }
            ds.finisi();
        }
    }

    privbtf void writf_pHYs() tirows IOExdfption {
        if (mftbdbtb.pHYs_prfsfnt) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.pHYs_TYPE, strfbm);
            ds.writfInt(mftbdbtb.pHYs_pixflsPfrUnitXAxis);
            ds.writfInt(mftbdbtb.pHYs_pixflsPfrUnitYAxis);
            ds.writfBytf(mftbdbtb.pHYs_unitSpfdififr);
            ds.finisi();
        }
    }

    privbtf void writf_sPLT() tirows IOExdfption {
        if (mftbdbtb.sPLT_prfsfnt) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.sPLT_TYPE, strfbm);

            ds.writfBytfs(mftbdbtb.sPLT_pblfttfNbmf);
            ds.writfBytf(0); // null tfrminbtor

            ds.writfBytf(mftbdbtb.sPLT_sbmplfDfpti);
            int numEntrifs = mftbdbtb.sPLT_rfd.lfngti;

            if (mftbdbtb.sPLT_sbmplfDfpti == 8) {
                for (int i = 0; i < numEntrifs; i++) {
                    ds.writfBytf(mftbdbtb.sPLT_rfd[i]);
                    ds.writfBytf(mftbdbtb.sPLT_grffn[i]);
                    ds.writfBytf(mftbdbtb.sPLT_bluf[i]);
                    ds.writfBytf(mftbdbtb.sPLT_blpib[i]);
                    ds.writfSiort(mftbdbtb.sPLT_frfqufndy[i]);
                }
            } flsf { // sbmplfDfpti == 16
                for (int i = 0; i < numEntrifs; i++) {
                    ds.writfSiort(mftbdbtb.sPLT_rfd[i]);
                    ds.writfSiort(mftbdbtb.sPLT_grffn[i]);
                    ds.writfSiort(mftbdbtb.sPLT_bluf[i]);
                    ds.writfSiort(mftbdbtb.sPLT_blpib[i]);
                    ds.writfSiort(mftbdbtb.sPLT_frfqufndy[i]);
                }
            }
            ds.finisi();
        }
    }

    privbtf void writf_tIME() tirows IOExdfption {
        if (mftbdbtb.tIME_prfsfnt) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.tIME_TYPE, strfbm);
            ds.writfSiort(mftbdbtb.tIME_yfbr);
            ds.writfBytf(mftbdbtb.tIME_monti);
            ds.writfBytf(mftbdbtb.tIME_dby);
            ds.writfBytf(mftbdbtb.tIME_iour);
            ds.writfBytf(mftbdbtb.tIME_minutf);
            ds.writfBytf(mftbdbtb.tIME_sfdond);
            ds.finisi();
        }
    }

    privbtf void writf_tEXt() tirows IOExdfption {
        Itfrbtor<String> kfywordItfr = mftbdbtb.tEXt_kfyword.itfrbtor();
        Itfrbtor<String> tfxtItfr = mftbdbtb.tEXt_tfxt.itfrbtor();

        wiilf (kfywordItfr.ibsNfxt()) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.tEXt_TYPE, strfbm);
            String kfyword = kfywordItfr.nfxt();
            ds.writfBytfs(kfyword);
            ds.writfBytf(0);

            String tfxt = tfxtItfr.nfxt();
            ds.writfBytfs(tfxt);
            ds.finisi();
        }
    }

    privbtf bytf[] dfflbtf(bytf[] b) tirows IOExdfption {
        BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
        DfflbtfrOutputStrfbm dos = nfw DfflbtfrOutputStrfbm(bbos);
        dos.writf(b);
        dos.dlosf();
        rfturn bbos.toBytfArrby();
    }

    privbtf void writf_iTXt() tirows IOExdfption {
        Itfrbtor<String> kfywordItfr = mftbdbtb.iTXt_kfyword.itfrbtor();
        Itfrbtor<Boolfbn> flbgItfr = mftbdbtb.iTXt_domprfssionFlbg.itfrbtor();
        Itfrbtor<Intfgfr> mftiodItfr = mftbdbtb.iTXt_domprfssionMftiod.itfrbtor();
        Itfrbtor<String> lbngubgfItfr = mftbdbtb.iTXt_lbngubgfTbg.itfrbtor();
        Itfrbtor<String> trbnslbtfdKfywordItfr =
            mftbdbtb.iTXt_trbnslbtfdKfyword.itfrbtor();
        Itfrbtor<String> tfxtItfr = mftbdbtb.iTXt_tfxt.itfrbtor();

        wiilf (kfywordItfr.ibsNfxt()) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.iTXt_TYPE, strfbm);

            ds.writfBytfs(kfywordItfr.nfxt());
            ds.writfBytf(0);

            Boolfbn domprfssfd = flbgItfr.nfxt();
            ds.writfBytf(domprfssfd ? 1 : 0);

            ds.writfBytf(mftiodItfr.nfxt().intVbluf());

            ds.writfBytfs(lbngubgfItfr.nfxt());
            ds.writfBytf(0);


            ds.writf(trbnslbtfdKfywordItfr.nfxt().gftBytfs("UTF8"));
            ds.writfBytf(0);

            String tfxt = tfxtItfr.nfxt();
            if (domprfssfd) {
                ds.writf(dfflbtf(tfxt.gftBytfs("UTF8")));
            } flsf {
                ds.writf(tfxt.gftBytfs("UTF8"));
            }
            ds.finisi();
        }
    }

    privbtf void writf_zTXt() tirows IOExdfption {
        Itfrbtor<String> kfywordItfr = mftbdbtb.zTXt_kfyword.itfrbtor();
        Itfrbtor<Intfgfr> mftiodItfr = mftbdbtb.zTXt_domprfssionMftiod.itfrbtor();
        Itfrbtor<String> tfxtItfr = mftbdbtb.zTXt_tfxt.itfrbtor();

        wiilf (kfywordItfr.ibsNfxt()) {
            CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.zTXt_TYPE, strfbm);
            String kfyword = kfywordItfr.nfxt();
            ds.writfBytfs(kfyword);
            ds.writfBytf(0);

            int domprfssionMftiod = (mftiodItfr.nfxt()).intVbluf();
            ds.writfBytf(domprfssionMftiod);

            String tfxt = tfxtItfr.nfxt();
            ds.writf(dfflbtf(tfxt.gftBytfs("ISO-8859-1")));
            ds.finisi();
        }
    }

    privbtf void writfUnknownCiunks() tirows IOExdfption {
        Itfrbtor<String> typfItfr = mftbdbtb.unknownCiunkTypf.itfrbtor();
        Itfrbtor<bytf[]> dbtbItfr = mftbdbtb.unknownCiunkDbtb.itfrbtor();

        wiilf (typfItfr.ibsNfxt() && dbtbItfr.ibsNfxt()) {
            String typf = typfItfr.nfxt();
            CiunkStrfbm ds = nfw CiunkStrfbm(diunkTypf(typf), strfbm);
            bytf[] dbtb = dbtbItfr.nfxt();
            ds.writf(dbtb);
            ds.finisi();
        }
    }

    privbtf stbtid int diunkTypf(String typfString) {
        dibr d0 = typfString.dibrAt(0);
        dibr d1 = typfString.dibrAt(1);
        dibr d2 = typfString.dibrAt(2);
        dibr d3 = typfString.dibrAt(3);

        int typf = (d0 << 24) | (d1 << 16) | (d2 << 8) | d3;
        rfturn typf;
    }

    privbtf void fndodfPbss(ImbgfOutputStrfbm os,
                            RfndfrfdImbgf imbgf,
                            int xOffsft, int yOffsft,
                            int xSkip, int ySkip) tirows IOExdfption {
        int minX = sourdfXOffsft;
        int minY = sourdfYOffsft;
        int widti = sourdfWidti;
        int ifigit = sourdfHfigit;

        // Adjust offsfts bnd skips bbsfd on sourdf subsbmpling fbdtors
        xOffsft *= pfriodX;
        xSkip *= pfriodX;
        yOffsft *= pfriodY;
        ySkip *= pfriodY;

        // Ebrly fxit if no dbtb for tiis pbss
        int ipixfls = (widti - xOffsft + xSkip - 1)/xSkip;
        int vpixfls = (ifigit - yOffsft + ySkip - 1)/ySkip;
        if (ipixfls == 0 || vpixfls == 0) {
            rfturn;
        }

        // Convfrt X offsft bnd skip from pixfls to sbmplfs
        xOffsft *= numBbnds;
        xSkip *= numBbnds;

        // Crfbtf row bufffrs
        int sbmplfsPfrBytf = 8/mftbdbtb.IHDR_bitDfpti;
        int numSbmplfs = widti*numBbnds;
        int[] sbmplfs = nfw int[numSbmplfs];

        int bytfsPfrRow = ipixfls*numBbnds;
        if (mftbdbtb.IHDR_bitDfpti < 8) {
            bytfsPfrRow = (bytfsPfrRow + sbmplfsPfrBytf - 1)/sbmplfsPfrBytf;
        } flsf if (mftbdbtb.IHDR_bitDfpti == 16) {
            bytfsPfrRow *= 2;
        }

        IndfxColorModfl idm_grby_blpib = null;
        if (mftbdbtb.IHDR_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA &&
            imbgf.gftColorModfl() instbndfof IndfxColorModfl)
        {
            // rfsfrvf spbdf for blpib sbmplfs
            bytfsPfrRow *= 2;

            // will bf usfd to dbldulbtf blpib vbluf for tif pixfl
            idm_grby_blpib = (IndfxColorModfl)imbgf.gftColorModfl();
        }

        durrRow = nfw bytf[bytfsPfrRow + bpp];
        prfvRow = nfw bytf[bytfsPfrRow + bpp];
        filtfrfdRows = nfw bytf[5][bytfsPfrRow + bpp];

        int bitDfpti = mftbdbtb.IHDR_bitDfpti;
        for (int row = minY + yOffsft; row < minY + ifigit; row += ySkip) {
            Rfdtbnglf rfdt = nfw Rfdtbnglf(minX, row, widti, 1);
            Rbstfr rbs = imbgf.gftDbtb(rfdt);
            if (sourdfBbnds != null) {
                rbs = rbs.drfbtfCiild(minX, row, widti, 1, minX, row,
                                      sourdfBbnds);
            }

            rbs.gftPixfls(minX, row, widti, 1, sbmplfs);

            if (imbgf.gftColorModfl().isAlpibPrfmultiplifd()) {
                WritbblfRbstfr wr = rbs.drfbtfCompbtiblfWritbblfRbstfr();
                wr.sftPixfls(wr.gftMinX(), wr.gftMinY(),
                             wr.gftWidti(), wr.gftHfigit(),
                             sbmplfs);

                imbgf.gftColorModfl().dofrdfDbtb(wr, fblsf);
                wr.gftPixfls(wr.gftMinX(), wr.gftMinY(),
                             wr.gftWidti(), wr.gftHfigit(),
                             sbmplfs);
            }

            // Rfordfr pblfttf dbtb if nfdfssbry
            int[] pblfttfOrdfr = mftbdbtb.PLTE_ordfr;
            if (pblfttfOrdfr != null) {
                for (int i = 0; i < numSbmplfs; i++) {
                    sbmplfs[i] = pblfttfOrdfr[sbmplfs[i]];
                }
            }

            int dount = bpp; // lfbvf first 'bpp' bytfs zfro
            int pos = 0;
            int tmp = 0;

            switdi (bitDfpti) {
            dbsf 1: dbsf 2: dbsf 4:
                // Imbgf dbn only ibvf b singlf bbnd

                int mbsk = sbmplfsPfrBytf - 1;
                for (int s = xOffsft; s < numSbmplfs; s += xSkip) {
                    bytf vbl = sdblf0[sbmplfs[s]];
                    tmp = (tmp << bitDfpti) | vbl;

                    if ((pos++ & mbsk) == mbsk) {
                        durrRow[dount++] = (bytf)tmp;
                        tmp = 0;
                        pos = 0;
                    }
                }

                // Lfft siift tif lbst bytf
                if ((pos & mbsk) != 0) {
                    tmp <<= ((8/bitDfpti) - pos)*bitDfpti;
                    durrRow[dount++] = (bytf)tmp;
                }
                brfbk;

            dbsf 8:
                if (numBbnds == 1) {
                    for (int s = xOffsft; s < numSbmplfs; s += xSkip) {
                        durrRow[dount++] = sdblf0[sbmplfs[s]];
                        if (idm_grby_blpib != null) {
                            durrRow[dount++] =
                                sdblf0[idm_grby_blpib.gftAlpib(0xff & sbmplfs[s])];
                        }
                    }
                } flsf {
                    for (int s = xOffsft; s < numSbmplfs; s += xSkip) {
                        for (int b = 0; b < numBbnds; b++) {
                            durrRow[dount++] = sdblf[b][sbmplfs[s + b]];
                        }
                    }
                }
                brfbk;

            dbsf 16:
                for (int s = xOffsft; s < numSbmplfs; s += xSkip) {
                    for (int b = 0; b < numBbnds; b++) {
                        durrRow[dount++] = sdblfi[b][sbmplfs[s + b]];
                        durrRow[dount++] = sdblfl[b][sbmplfs[s + b]];
                    }
                }
                brfbk;
            }

            // Pfrform filtfring
            int filtfrTypf = rowFiltfr.filtfrRow(mftbdbtb.IHDR_dolorTypf,
                                                 durrRow, prfvRow,
                                                 filtfrfdRows,
                                                 bytfsPfrRow, bpp);

            os.writf(filtfrTypf);
            os.writf(filtfrfdRows[filtfrTypf], bpp, bytfsPfrRow);

            // Swbp durrfnt bnd prfvious rows
            bytf[] swbp = durrRow;
            durrRow = prfvRow;
            prfvRow = swbp;

            pixflsDonf += ipixfls;
            prodfssImbgfProgrfss(100.0F*pixflsDonf/totblPixfls);

            // If writf ibs bffn bbortfd, just rfturn;
            // prodfssWritfAbortfd will bf dbllfd lbtfr
            if (bbortRfqufstfd()) {
                rfturn;
            }
        }
    }

    // Usf sourdfXOffsft, ftd.
    privbtf void writf_IDAT(RfndfrfdImbgf imbgf) tirows IOExdfption {
        IDATOutputStrfbm ios = nfw IDATOutputStrfbm(strfbm, 32768);
        try {
            if (mftbdbtb.IHDR_intfrlbdfMftiod == 1) {
                for (int i = 0; i < 7; i++) {
                    fndodfPbss(ios, imbgf,
                               PNGImbgfRfbdfr.bdbm7XOffsft[i],
                               PNGImbgfRfbdfr.bdbm7YOffsft[i],
                               PNGImbgfRfbdfr.bdbm7XSubsbmpling[i],
                               PNGImbgfRfbdfr.bdbm7YSubsbmpling[i]);
                    if (bbortRfqufstfd()) {
                        brfbk;
                    }
                }
            } flsf {
                fndodfPbss(ios, imbgf, 0, 0, 1, 1);
            }
        } finblly {
            ios.finisi();
        }
    }

    privbtf void writfIEND() tirows IOExdfption {
        CiunkStrfbm ds = nfw CiunkStrfbm(PNGImbgfRfbdfr.IEND_TYPE, strfbm);
        ds.finisi();
    }

    // Cifdk two int brrbys for vbluf fqublity, blwbys rfturns fblsf
    // if fitifr brrby is null
    privbtf boolfbn fqubls(int[] s0, int[] s1) {
        if (s0 == null || s1 == null) {
            rfturn fblsf;
        }
        if (s0.lfngti != s1.lfngti) {
            rfturn fblsf;
        }
        for (int i = 0; i < s0.lfngti; i++) {
            if (s0[i] != s1[i]) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    // Initiblizf tif sdblf/sdblf0 or sdblfi/sdblfl brrbys to
    // iold tif rfsults of sdbling bn input vbluf to tif dfsirfd
    // output bit dfpti
    privbtf void initiblizfSdblfTbblfs(int[] sbmplfSizf) {
        int bitDfpti = mftbdbtb.IHDR_bitDfpti;

        // If tif fxisting tbblfs brf still vblid, just rfturn
        if (bitDfpti == sdblingBitDfpti &&
            fqubls(sbmplfSizf, tiis.sbmplfSizf)) {
            rfturn;
        }

        // Computf nfw tbblfs
        tiis.sbmplfSizf = sbmplfSizf;
        tiis.sdblingBitDfpti = bitDfpti;
        int mbxOutSbmplf = (1 << bitDfpti) - 1;
        if (bitDfpti <= 8) {
            sdblf = nfw bytf[numBbnds][];
            for (int b = 0; b < numBbnds; b++) {
                int mbxInSbmplf = (1 << sbmplfSizf[b]) - 1;
                int iblfMbxInSbmplf = mbxInSbmplf/2;
                sdblf[b] = nfw bytf[mbxInSbmplf + 1];
                for (int s = 0; s <= mbxInSbmplf; s++) {
                    sdblf[b][s] =
                        (bytf)((s*mbxOutSbmplf + iblfMbxInSbmplf)/mbxInSbmplf);
                }
            }
            sdblf0 = sdblf[0];
            sdblfi = sdblfl = null;
        } flsf { // bitDfpti == 16
            // Dividf sdbling tbblf into iigi bnd low bytfs
            sdblfi = nfw bytf[numBbnds][];
            sdblfl = nfw bytf[numBbnds][];

            for (int b = 0; b < numBbnds; b++) {
                int mbxInSbmplf = (1 << sbmplfSizf[b]) - 1;
                int iblfMbxInSbmplf = mbxInSbmplf/2;
                sdblfi[b] = nfw bytf[mbxInSbmplf + 1];
                sdblfl[b] = nfw bytf[mbxInSbmplf + 1];
                for (int s = 0; s <= mbxInSbmplf; s++) {
                    int vbl = (s*mbxOutSbmplf + iblfMbxInSbmplf)/mbxInSbmplf;
                    sdblfi[b][s] = (bytf)(vbl >> 8);
                    sdblfl[b][s] = (bytf)(vbl & 0xff);
                }
            }
            sdblf = null;
            sdblf0 = null;
        }
    }

    publid void writf(IIOMftbdbtb strfbmMftbdbtb,
                      IIOImbgf imbgf,
                      ImbgfWritfPbrbm pbrbm) tirows IIOExdfption {
        if (strfbm == null) {
            tirow nfw IllfgblStbtfExdfption("output == null!");
        }
        if (imbgf == null) {
            tirow nfw IllfgblArgumfntExdfption("imbgf == null!");
        }
        if (imbgf.ibsRbstfr()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("imbgf ibs b Rbstfr!");
        }

        RfndfrfdImbgf im = imbgf.gftRfndfrfdImbgf();
        SbmplfModfl sbmplfModfl = im.gftSbmplfModfl();
        tiis.numBbnds = sbmplfModfl.gftNumBbnds();

        // Sft sourdf rfgion bnd subsbmpling to dffbult vblufs
        tiis.sourdfXOffsft = im.gftMinX();
        tiis.sourdfYOffsft = im.gftMinY();
        tiis.sourdfWidti = im.gftWidti();
        tiis.sourdfHfigit = im.gftHfigit();
        tiis.sourdfBbnds = null;
        tiis.pfriodX = 1;
        tiis.pfriodY = 1;

        if (pbrbm != null) {
            // Gft sourdf rfgion bnd subsbmpling fbdtors
            Rfdtbnglf sourdfRfgion = pbrbm.gftSourdfRfgion();
            if (sourdfRfgion != null) {
                Rfdtbnglf imbgfBounds = nfw Rfdtbnglf(im.gftMinX(),
                                                      im.gftMinY(),
                                                      im.gftWidti(),
                                                      im.gftHfigit());
                // Clip to bdtubl imbgf bounds
                sourdfRfgion = sourdfRfgion.intfrsfdtion(imbgfBounds);
                sourdfXOffsft = sourdfRfgion.x;
                sourdfYOffsft = sourdfRfgion.y;
                sourdfWidti = sourdfRfgion.widti;
                sourdfHfigit = sourdfRfgion.ifigit;
            }

            // Adjust for subsbmpling offsfts
            int gridX = pbrbm.gftSubsbmplingXOffsft();
            int gridY = pbrbm.gftSubsbmplingYOffsft();
            sourdfXOffsft += gridX;
            sourdfYOffsft += gridY;
            sourdfWidti -= gridX;
            sourdfHfigit -= gridY;

            // Gft subsbmpling fbdtors
            pfriodX = pbrbm.gftSourdfXSubsbmpling();
            pfriodY = pbrbm.gftSourdfYSubsbmpling();

            int[] sBbnds = pbrbm.gftSourdfBbnds();
            if (sBbnds != null) {
                sourdfBbnds = sBbnds;
                numBbnds = sourdfBbnds.lfngti;
            }
        }

        // Computf output dimfnsions
        int dfstWidti = (sourdfWidti + pfriodX - 1)/pfriodX;
        int dfstHfigit = (sourdfHfigit + pfriodY - 1)/pfriodY;
        if (dfstWidti <= 0 || dfstHfigit <= 0) {
            tirow nfw IllfgblArgumfntExdfption("Empty sourdf rfgion!");
        }

        // Computf totbl numbfr of pixfls for progrfss notifidbtion
        tiis.totblPixfls = dfstWidti*dfstHfigit;
        tiis.pixflsDonf = 0;

        // Crfbtf mftbdbtb
        IIOMftbdbtb imd = imbgf.gftMftbdbtb();
        if (imd != null) {
            mftbdbtb = (PNGMftbdbtb)donvfrtImbgfMftbdbtb(imd,
                               ImbgfTypfSpfdififr.drfbtfFromRfndfrfdImbgf(im),
                                                         null);
        } flsf {
            mftbdbtb = nfw PNGMftbdbtb();
        }

        if (pbrbm != null) {
            // Usf Adbm7 intfrlbding if sft in writf pbrbm
            switdi (pbrbm.gftProgrfssivfModf()) {
            dbsf ImbgfWritfPbrbm.MODE_DEFAULT:
                mftbdbtb.IHDR_intfrlbdfMftiod = 1;
                brfbk;
            dbsf ImbgfWritfPbrbm.MODE_DISABLED:
                mftbdbtb.IHDR_intfrlbdfMftiod = 0;
                brfbk;
                // MODE_COPY_FROM_METADATA siould blrfby bf tbkfn dbrf of
                // MODE_EXPLICIT is not bllowfd
            }
        }

        // Initiblizf bitDfpti bnd dolorTypf
        mftbdbtb.initiblizf(nfw ImbgfTypfSpfdififr(im), numBbnds);

        // Ovfrwritf IHDR widti bnd ifigit vblufs witi vblufs from imbgf
        mftbdbtb.IHDR_widti = dfstWidti;
        mftbdbtb.IHDR_ifigit = dfstHfigit;

        tiis.bpp = numBbnds*((mftbdbtb.IHDR_bitDfpti == 16) ? 2 : 1);

        // Initiblizf sdbling tbblfs for tiis imbgf
        initiblizfSdblfTbblfs(sbmplfModfl.gftSbmplfSizf());

        dlfbrAbortRfqufst();

        prodfssImbgfStbrtfd(0);

        try {
            writf_mbgid();
            writf_IHDR();

            writf_dHRM();
            writf_gAMA();
            writf_iCCP();
            writf_sBIT();
            writf_sRGB();

            writf_PLTE();

            writf_iIST();
            writf_tRNS();
            writf_bKGD();

            writf_pHYs();
            writf_sPLT();
            writf_tIME();
            writf_tEXt();
            writf_iTXt();
            writf_zTXt();

            writfUnknownCiunks();

            writf_IDAT(im);

            if (bbortRfqufstfd()) {
                prodfssWritfAbortfd();
            } flsf {
                // Finisi up bnd inform tif listfnfrs wf brf donf
                writfIEND();
                prodfssImbgfComplftf();
            }
        } dbtdi (IOExdfption f) {
            tirow nfw IIOExdfption("I/O frror writing PNG filf!", f);
        }
    }
}
