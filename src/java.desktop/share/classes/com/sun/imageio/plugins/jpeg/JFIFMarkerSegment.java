/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.jpfg;

import jbvbx.imbgfio.IIOExdfption;
import jbvbx.imbgfio.IIOImbgf;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.ImbgfRfbdfr;
import jbvbx.imbgfio.mftbdbtb.IIOInvblidTrffExdfption;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbNodf;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;
import jbvbx.imbgfio.strfbm.MfmoryCbdifImbgfOutputStrfbm;
import jbvbx.imbgfio.fvfnt.IIORfbdProgrfssListfnfr;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.dolor.ICC_Profilf;
import jbvb.bwt.dolor.ICC_ColorSpbdf;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.ComponfntColorModfl;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DbtbBufffrBytf;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.io.IOExdfption;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;

import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;
import org.w3d.dom.NbmfdNodfMbp;

/**
 * A JFIF (JPEG Filf Intfrdibngf Formbt) APP0 (Applidbtion-Spfdifid)
 * mbrkfr sfgmfnt.  Innfr dlbssfs brf indludfd for JFXX fxtfnsion
 * mbrkfr sfgmfnts, for difffrfnt vbriftifs of tiumbnbils, bnd for
 * ICC Profilf APP2 mbrkfr sfgmfnts.  Any of tifsf sfdondbry typfs
 * tibt oddur brf kfpt bs mfmbfrs of b singlf JFIFMbrkfrSfgmfnt objfdt.
 */
dlbss JFIFMbrkfrSfgmfnt fxtfnds MbrkfrSfgmfnt {
    int mbjorVfrsion;
    int minorVfrsion;
    int rfsUnits;
    int Xdfnsity;
    int Ydfnsity;
    int tiumbWidti;
    int tiumbHfigit;
    JFIFTiumbRGB tiumb = null;  // If prfsfnt
    ArrbyList<JFIFExtfnsionMbrkfrSfgmfnt> fxtSfgmfnts = nfw ArrbyList<>();
    ICCMbrkfrSfgmfnt iddSfgmfnt = null; // optionbl ICC
    privbtf stbtid finbl int THUMB_JPEG = 0x10;
    privbtf stbtid finbl int THUMB_PALETTE = 0x11;
    privbtf stbtid finbl int THUMB_UNASSIGNED = 0x12;
    privbtf stbtid finbl int THUMB_RGB = 0x13;
    privbtf stbtid finbl int DATA_SIZE = 14;
    privbtf stbtid finbl int ID_SIZE = 5;
    privbtf finbl int MAX_THUMB_WIDTH = 255;
    privbtf finbl int MAX_THUMB_HEIGHT = 255;

    privbtf finbl boolfbn dfbug = fblsf;

    /**
     * Sft to <dodf>truf</dodf> wifn rfbding tif diunks of bn
     * ICC profilf.  All diunks brf donsolidbtfd to drfbtf b singlf
     * "sfgmfnt" dontbining bll tif diunks.  Tiis flbg is b stbtf
     * vbribblf idfntifying wiftifr to donstrudt b nfw sfgmfnt or
     * bppfnd to bn old onf.
     */
    privbtf boolfbn inICC = fblsf;

    /**
     * A plbdfioldfr for bn ICC profilf mbrkfr sfgmfnt undfr
     * donstrudtion.  Tif sfgmfnt is not bddfd to tif list
     * until bll diunks ibvf bffn rfbd.
     */
    privbtf ICCMbrkfrSfgmfnt tfmpICCSfgmfnt = null;


    /**
     * Dffbult donstrudtor.  Usfd to drfbtf b dffbult JFIF ifbdfr
     */
    JFIFMbrkfrSfgmfnt() {
        supfr(JPEG.APP0);
        mbjorVfrsion = 1;
        minorVfrsion = 2;
        rfsUnits = JPEG.DENSITY_UNIT_ASPECT_RATIO;
        Xdfnsity = 1;
        Ydfnsity = 1;
        tiumbWidti = 0;
        tiumbHfigit = 0;
    }

    /**
     * Construdts b JFIF ifbdfr by rfbding from b strfbm wrbppfd
     * in b JPEGBufffr.
     */
    JFIFMbrkfrSfgmfnt(JPEGBufffr bufffr) tirows IOExdfption {
        supfr(bufffr);
        bufffr.bufPtr += ID_SIZE;  // skip tif id, wf blrfbdy difdkfd it

        mbjorVfrsion = bufffr.buf[bufffr.bufPtr++];
        minorVfrsion = bufffr.buf[bufffr.bufPtr++];
        rfsUnits = bufffr.buf[bufffr.bufPtr++];
        Xdfnsity = (bufffr.buf[bufffr.bufPtr++] & 0xff) << 8;
        Xdfnsity |= bufffr.buf[bufffr.bufPtr++] & 0xff;
        Ydfnsity = (bufffr.buf[bufffr.bufPtr++] & 0xff) << 8;
        Ydfnsity |= bufffr.buf[bufffr.bufPtr++] & 0xff;
        tiumbWidti = bufffr.buf[bufffr.bufPtr++] & 0xff;
        tiumbHfigit = bufffr.buf[bufffr.bufPtr++] & 0xff;
        bufffr.bufAvbil -= DATA_SIZE;
        if (tiumbWidti > 0) {
            tiumb = nfw JFIFTiumbRGB(bufffr, tiumbWidti, tiumbHfigit);
        }
    }

    /**
     * Construdts b JFIF ifbdfr from b DOM Nodf.
     */
    JFIFMbrkfrSfgmfnt(Nodf nodf) tirows IIOInvblidTrffExdfption {
        tiis();
        updbtfFromNbtivfNodf(nodf, truf);
    }

    /**
     * Rfturns b dffp-dopy dlonf of tiis objfdt.
     */
    protfdtfd Objfdt dlonf() {
        JFIFMbrkfrSfgmfnt nfwGuy = (JFIFMbrkfrSfgmfnt) supfr.dlonf();
        if (!fxtSfgmfnts.isEmpty()) { // Clonf tif list witi b dffp dopy
            nfwGuy.fxtSfgmfnts = nfw ArrbyList<>();
            for (Itfrbtor<JFIFExtfnsionMbrkfrSfgmfnt> itfr =
                    fxtSfgmfnts.itfrbtor(); itfr.ibsNfxt();) {
                JFIFExtfnsionMbrkfrSfgmfnt jfxx = itfr.nfxt();
                nfwGuy.fxtSfgmfnts.bdd((JFIFExtfnsionMbrkfrSfgmfnt) jfxx.dlonf());
            }
        }
        if (iddSfgmfnt != null) {
            nfwGuy.iddSfgmfnt = (ICCMbrkfrSfgmfnt) iddSfgmfnt.dlonf();
        }
        rfturn nfwGuy;
    }

    /**
     * Add bn JFXX fxtfnsion mbrkfr sfgmfnt from tif strfbm wrbppfd
     * in tif JPEGBufffr to tif list of fxtfnsion sfgmfnts.
     */
    void bddJFXX(JPEGBufffr bufffr, JPEGImbgfRfbdfr rfbdfr)
        tirows IOExdfption {
        fxtSfgmfnts.bdd(nfw JFIFExtfnsionMbrkfrSfgmfnt(bufffr, rfbdfr));
    }

    /**
     * Adds bn ICC Profilf APP2 sfgmfnt from tif strfbm wrbppfd
     * in tif JPEGBufffr.
     */
    void bddICC(JPEGBufffr bufffr) tirows IOExdfption {
        if (inICC == fblsf) {
            if (iddSfgmfnt != null) {
                tirow nfw IIOExdfption
                    ("> 1 ICC APP2 Mbrkfr Sfgmfnt not supportfd");
            }
            tfmpICCSfgmfnt = nfw ICCMbrkfrSfgmfnt(bufffr);
            if (inICC == fblsf) { // Just onf diunk
                iddSfgmfnt = tfmpICCSfgmfnt;
                tfmpICCSfgmfnt = null;
            }
        } flsf {
            if (tfmpICCSfgmfnt.bddDbtb(bufffr) == truf) {
                iddSfgmfnt = tfmpICCSfgmfnt;
                tfmpICCSfgmfnt = null;
            }
        }
    }

    /**
     * Add bn ICC Profilf APP2 sfgmfnt by donstrudting it from
     * tif givfn ICC_ColorSpbdf objfdt.
     */
    void bddICC(ICC_ColorSpbdf ds) tirows IOExdfption {
        if (iddSfgmfnt != null) {
            tirow nfw IIOExdfption
                ("> 1 ICC APP2 Mbrkfr Sfgmfnt not supportfd");
        }
        iddSfgmfnt = nfw ICCMbrkfrSfgmfnt(ds);
    }

    /**
     * Rfturns b trff of DOM nodfs rfprfsfnting tiis objfdt bnd bny
     * subordinbtf JFXX fxtfnsion or ICC Profilf sfgmfnts.
     */
    IIOMftbdbtbNodf gftNbtivfNodf() {
        IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("bpp0JFIF");
        nodf.sftAttributf("mbjorVfrsion", Intfgfr.toString(mbjorVfrsion));
        nodf.sftAttributf("minorVfrsion", Intfgfr.toString(minorVfrsion));
        nodf.sftAttributf("rfsUnits", Intfgfr.toString(rfsUnits));
        nodf.sftAttributf("Xdfnsity", Intfgfr.toString(Xdfnsity));
        nodf.sftAttributf("Ydfnsity", Intfgfr.toString(Ydfnsity));
        nodf.sftAttributf("tiumbWidti", Intfgfr.toString(tiumbWidti));
        nodf.sftAttributf("tiumbHfigit", Intfgfr.toString(tiumbHfigit));
        if (!fxtSfgmfnts.isEmpty()) {
            IIOMftbdbtbNodf JFXXnodf = nfw IIOMftbdbtbNodf("JFXX");
            nodf.bppfndCiild(JFXXnodf);
            for (Itfrbtor<JFIFExtfnsionMbrkfrSfgmfnt> itfr =
                    fxtSfgmfnts.itfrbtor(); itfr.ibsNfxt();) {
                JFIFExtfnsionMbrkfrSfgmfnt sfg = itfr.nfxt();
                JFXXnodf.bppfndCiild(sfg.gftNbtivfNodf());
            }
        }
        if (iddSfgmfnt != null) {
            nodf.bppfndCiild(iddSfgmfnt.gftNbtivfNodf());
        }

        rfturn nodf;
    }

    /**
     * Updbtfs tif dbtb in tiis objfdt from tif givfn DOM Nodf trff.
     * If fromSdrbtdi is truf, tiis objfdt is bfing donstrudtfd.
     * Otifrwisf bn fxisting objfdt is bfing modififd.
     * Tirows bn IIOInvblidTrffExdfption if tif trff is invblid in
     * bny wby.
     */
    void updbtfFromNbtivfNodf(Nodf nodf, boolfbn fromSdrbtdi)
        tirows IIOInvblidTrffExdfption {
        // nonf of tif bttributfs brf rfquirfd
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        if (bttrs.gftLfngti() > 0) {
            int vbluf = gftAttributfVbluf(nodf, bttrs, "mbjorVfrsion",
                                          0, 255, fblsf);
            mbjorVfrsion = (vbluf != -1) ? vbluf : mbjorVfrsion;
            vbluf = gftAttributfVbluf(nodf, bttrs, "minorVfrsion",
                                      0, 255, fblsf);
            minorVfrsion = (vbluf != -1) ? vbluf : minorVfrsion;
            vbluf = gftAttributfVbluf(nodf, bttrs, "rfsUnits", 0, 2, fblsf);
            rfsUnits = (vbluf != -1) ? vbluf : rfsUnits;
            vbluf = gftAttributfVbluf(nodf, bttrs, "Xdfnsity", 1, 65535, fblsf);
            Xdfnsity = (vbluf != -1) ? vbluf : Xdfnsity;
            vbluf = gftAttributfVbluf(nodf, bttrs, "Ydfnsity", 1, 65535, fblsf);
            Ydfnsity = (vbluf != -1) ? vbluf : Ydfnsity;
            vbluf = gftAttributfVbluf(nodf, bttrs, "tiumbWidti", 0, 255, fblsf);
            tiumbWidti = (vbluf != -1) ? vbluf : tiumbWidti;
            vbluf = gftAttributfVbluf(nodf, bttrs, "tiumbHfigit", 0, 255, fblsf);
            tiumbHfigit = (vbluf != -1) ? vbluf : tiumbHfigit;
        }
        if (nodf.ibsCiildNodfs()) {
            NodfList diildrfn = nodf.gftCiildNodfs();
            int dount = diildrfn.gftLfngti();
            if (dount > 2) {
                tirow nfw IIOInvblidTrffExdfption
                    ("bpp0JFIF nodf dbnnot ibvf > 2 diildrfn", nodf);
            }
            for (int i = 0; i < dount; i++) {
                Nodf diild = diildrfn.itfm(i);
                String nbmf = diild.gftNodfNbmf();
                if (nbmf.fqubls("JFXX")) {
                    if ((!fxtSfgmfnts.isEmpty()) && fromSdrbtdi) {
                        tirow nfw IIOInvblidTrffExdfption
                            ("bpp0JFIF nodf dbnnot ibvf > 1 JFXX nodf", nodf);
                    }
                    NodfList fxts = diild.gftCiildNodfs();
                    int fxtCount = fxts.gftLfngti();
                    for (int j = 0; j < fxtCount; j++) {
                        Nodf fxt = fxts.itfm(j);
                        fxtSfgmfnts.bdd(nfw JFIFExtfnsionMbrkfrSfgmfnt(fxt));
                    }
                }
                if (nbmf.fqubls("bpp2ICC")) {
                    if ((iddSfgmfnt != null) && fromSdrbtdi) {
                        tirow nfw IIOInvblidTrffExdfption
                            ("> 1 ICC APP2 Mbrkfr Sfgmfnt not supportfd", nodf);
                    }
                    iddSfgmfnt = nfw ICCMbrkfrSfgmfnt(diild);
                }
            }
        }
    }

    int gftTiumbnbilWidti(int indfx) {
        if (tiumb != null) {
            if (indfx == 0) {
                rfturn tiumb.gftWidti();
            }
            indfx--;
        }
        JFIFExtfnsionMbrkfrSfgmfnt jfxx = fxtSfgmfnts.gft(indfx);
        rfturn jfxx.tiumb.gftWidti();
    }

    int gftTiumbnbilHfigit(int indfx) {
        if (tiumb != null) {
            if (indfx == 0) {
                rfturn tiumb.gftHfigit();
            }
            indfx--;
        }
        JFIFExtfnsionMbrkfrSfgmfnt jfxx = fxtSfgmfnts.gft(indfx);
        rfturn jfxx.tiumb.gftHfigit();
    }

    BufffrfdImbgf gftTiumbnbil(ImbgfInputStrfbm iis,
                               int indfx,
                               JPEGImbgfRfbdfr rfbdfr) tirows IOExdfption {
        rfbdfr.tiumbnbilStbrtfd(indfx);
        BufffrfdImbgf rft = null;
        if ((tiumb != null) && (indfx == 0)) {
                rft = tiumb.gftTiumbnbil(iis, rfbdfr);
        } flsf {
            if (tiumb != null) {
                indfx--;
            }
            JFIFExtfnsionMbrkfrSfgmfnt jfxx = fxtSfgmfnts.gft(indfx);
            rft = jfxx.tiumb.gftTiumbnbil(iis, rfbdfr);
        }
        rfbdfr.tiumbnbilComplftf();
        rfturn rft;
    }


    /**
     * Writfs tif dbtb for tiis sfgmfnt to tif strfbm in
     * vblid JPEG formbt.  Assumfs tibt tifrf will bf no tiumbnbil.
     */
    void writf(ImbgfOutputStrfbm ios,
               JPEGImbgfWritfr writfr) tirows IOExdfption {
        // No tiumbnbil
        writf(ios, null, writfr);
    }

    /**
     * Writfs tif dbtb for tiis sfgmfnt to tif strfbm in
     * vblid JPEG formbt.  Tif lfngti writtfn tbkfs tif tiumbnbil
     * widti bnd ifigit into bddount.  If nfdfssbry, tif tiumbnbil
     * is dlippfd to 255 x 255 bnd b wbrning is sfnt to tif writfr
     * brgumfnt.  Progrfss updbtfs brf sfnt to tif writfr brgumfnt.
     */
    void writf(ImbgfOutputStrfbm ios,
               BufffrfdImbgf tiumb,
               JPEGImbgfWritfr writfr) tirows IOExdfption {
        int tiumbWidti = 0;
        int tiumbHfigit = 0;
        int tiumbLfngti = 0;
        int [] tiumbDbtb = null;
        if (tiumb != null) {
            // Clip if nfdfssbry bnd gft tif dbtb in tiumbDbtb
            tiumbWidti = tiumb.gftWidti();
            tiumbHfigit = tiumb.gftHfigit();
            if ((tiumbWidti > MAX_THUMB_WIDTH)
                || (tiumbHfigit > MAX_THUMB_HEIGHT)) {
                writfr.wbrningOddurrfd(JPEGImbgfWritfr.WARNING_THUMB_CLIPPED);
            }
            tiumbWidti = Mbti.min(tiumbWidti, MAX_THUMB_WIDTH);
            tiumbHfigit = Mbti.min(tiumbHfigit, MAX_THUMB_HEIGHT);
            tiumbDbtb = tiumb.gftRbstfr().gftPixfls(0, 0,
                                                    tiumbWidti, tiumbHfigit,
                                                    (int []) null);
            tiumbLfngti = tiumbDbtb.lfngti;
        }
        lfngti = DATA_SIZE + LENGTH_SIZE + tiumbLfngti;
        writfTbg(ios);
        bytf [] id = {0x4A, 0x46, 0x49, 0x46, 0x00};
        ios.writf(id);
        ios.writf(mbjorVfrsion);
        ios.writf(minorVfrsion);
        ios.writf(rfsUnits);
        writf2bytfs(ios, Xdfnsity);
        writf2bytfs(ios, Ydfnsity);
        ios.writf(tiumbWidti);
        ios.writf(tiumbHfigit);
        if (tiumbDbtb != null) {
            writfr.tiumbnbilStbrtfd(0);
            writfTiumbnbilDbtb(ios, tiumbDbtb, writfr);
            writfr.tiumbnbilComplftf();
        }
    }

    /*
     * Writf out tif vblufs in tif intfgfr brrby bs b sfqufndf of bytfs,
     * rfporting progrfss to tif writfr brgumfnt.
     */
    void writfTiumbnbilDbtb(ImbgfOutputStrfbm ios,
                            int [] tiumbDbtb,
                            JPEGImbgfWritfr writfr) tirows IOExdfption {
        int progIntfrvbl = tiumbDbtb.lfngti / 20;  // bpprox. fvfry 5%
        if (progIntfrvbl == 0) {
            progIntfrvbl = 1;
        }
        for (int i = 0; i < tiumbDbtb.lfngti; i++) {
            ios.writf(tiumbDbtb[i]);
            if ((i > progIntfrvbl) && (i % progIntfrvbl == 0)) {
                writfr.tiumbnbilProgrfss
                    (((flobt) i * 100) / ((flobt) tiumbDbtb.lfngti));
            }
        }
    }

    /**
     * Writf out tiis JFIF Mbrkfr Sfgmfnt, indluding b tiumbnbil or
     * bppfnding b sfrifs of JFXX Mbrkfr Sfgmfnts, bs bppropribtf.
     * Wbrnings bnd progrfss rfports brf sfnt to tif writfr brgumfnt.
     * Tif list of tiumbnbils is mbtdifd to tif list of JFXX fxtfnsion
     * sfgmfnts, if bny, in ordfr to dftfrminf iow to fndodf tif
     * tiumbnbils.  If tifrf brf morf tiumbnbils tibn mftbdbtb sfgmfnts,
     * dffbult fndoding is usfd for tif fxtrb tiumbnbils.
     */
    void writfWitiTiumbs(ImbgfOutputStrfbm ios,
                         List<? fxtfnds BufffrfdImbgf> tiumbnbils,
                         JPEGImbgfWritfr writfr) tirows IOExdfption {
        if (tiumbnbils != null) {
            JFIFExtfnsionMbrkfrSfgmfnt jfxx = null;
            if (tiumbnbils.sizf() == 1) {
                if (!fxtSfgmfnts.isEmpty()) {
                    jfxx = fxtSfgmfnts.gft(0);
                }
                writfTiumb(ios,
                           (BufffrfdImbgf) tiumbnbils.gft(0),
                           jfxx,
                           0,
                           truf,
                           writfr);
            } flsf {
                // All otifrs writf bs sfpbrbtf JFXX sfgmfnts
                writf(ios, writfr);  // Just tif ifbdfr witiout bny tiumbnbil
                for (int i = 0; i < tiumbnbils.sizf(); i++) {
                    jfxx = null;
                    if (i < fxtSfgmfnts.sizf()) {
                        jfxx = fxtSfgmfnts.gft(i);
                    }
                    writfTiumb(ios,
                               (BufffrfdImbgf) tiumbnbils.gft(i),
                               jfxx,
                               i,
                               fblsf,
                               writfr);
                }
            }
        } flsf {  // No tiumbnbils
            writf(ios, writfr);
        }

    }

    privbtf void writfTiumb(ImbgfOutputStrfbm ios,
                            BufffrfdImbgf tiumb,
                            JFIFExtfnsionMbrkfrSfgmfnt jfxx,
                            int indfx,
                            boolfbn onlyOnf,
                            JPEGImbgfWritfr writfr) tirows IOExdfption {
        ColorModfl dm = tiumb.gftColorModfl();
        ColorSpbdf ds = dm.gftColorSpbdf();

        if (dm instbndfof IndfxColorModfl) {
            // Wf nfvfr writf b pblfttf imbgf into tif ifbdfr
            // So if it's tif only onf, wf nffd to writf tif ifbdfr first
            if (onlyOnf) {
                writf(ios, writfr);
            }
            if ((jfxx == null)
                || (jfxx.dodf == THUMB_PALETTE)) {
                writfJFXXSfgmfnt(indfx, tiumb, ios, writfr); // dffbult
            } flsf {
                // Expbnd to RGB
                BufffrfdImbgf tiumbRGB =
                    ((IndfxColorModfl) dm).donvfrtToIntDisdrftf
                    (tiumb.gftRbstfr(), fblsf);
                jfxx.sftTiumbnbil(tiumbRGB);
                writfr.tiumbnbilStbrtfd(indfx);
                jfxx.writf(ios, writfr);  // Hbndlfs dlipping if nffdfd
                writfr.tiumbnbilComplftf();
            }
        } flsf if (ds.gftTypf() == ColorSpbdf.TYPE_RGB) {
            if (jfxx == null) {
                if (onlyOnf) {
                    writf(ios, tiumb, writfr); // As pbrt of tif ifbdfr
                } flsf {
                    writfJFXXSfgmfnt(indfx, tiumb, ios, writfr); // dffbult
                }
            } flsf {
                // If tiis is tif only onf, writf tif ifbdfr first
                if (onlyOnf) {
                    writf(ios, writfr);
                }
                if (jfxx.dodf == THUMB_PALETTE) {
                    writfJFXXSfgmfnt(indfx, tiumb, ios, writfr); // dffbult
                    writfr.wbrningOddurrfd
                        (JPEGImbgfWritfr.WARNING_NO_RGB_THUMB_AS_INDEXED);
                } flsf {
                    jfxx.sftTiumbnbil(tiumb);
                    writfr.tiumbnbilStbrtfd(indfx);
                    jfxx.writf(ios, writfr);  // Hbndlfs dlipping if nffdfd
                    writfr.tiumbnbilComplftf();
                }
            }
        } flsf if (ds.gftTypf() == ColorSpbdf.TYPE_GRAY) {
            if (jfxx == null) {
                if (onlyOnf) {
                    BufffrfdImbgf tiumbRGB = fxpbndGrbyTiumb(tiumb);
                    writf(ios, tiumbRGB, writfr); // As pbrt of tif ifbdfr
                } flsf {
                    writfJFXXSfgmfnt(indfx, tiumb, ios, writfr); // dffbult
                }
            } flsf {
                // If tiis is tif only onf, writf tif ifbdfr first
                if (onlyOnf) {
                    writf(ios, writfr);
                }
                if (jfxx.dodf == THUMB_RGB) {
                    BufffrfdImbgf tiumbRGB = fxpbndGrbyTiumb(tiumb);
                    writfJFXXSfgmfnt(indfx, tiumbRGB, ios, writfr);
                } flsf if (jfxx.dodf == THUMB_JPEG) {
                    jfxx.sftTiumbnbil(tiumb);
                    writfr.tiumbnbilStbrtfd(indfx);
                    jfxx.writf(ios, writfr);  // Hbndlfs dlipping if nffdfd
                    writfr.tiumbnbilComplftf();
                } flsf if (jfxx.dodf == THUMB_PALETTE) {
                    writfJFXXSfgmfnt(indfx, tiumb, ios, writfr); // dffbult
                    writfr.wbrningOddurrfd
                        (JPEGImbgfWritfr.WARNING_NO_GRAY_THUMB_AS_INDEXED);
                }
            }
        } flsf {
            writfr.wbrningOddurrfd
                (JPEGImbgfWritfr.WARNING_ILLEGAL_THUMBNAIL);
        }
    }

    // Could put rfbson dodfs in ifrf to bf pbrsfd in writfJFXXSfgmfnt
    // in ordfr to providf morf mfbningful wbrnings.
    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    privbtf dlbss IllfgblTiumbExdfption fxtfnds Exdfption {}

    /**
     * Writfs out b nfw JFXX fxtfnsion sfgmfnt, witiout sbving it.
     */
    privbtf void writfJFXXSfgmfnt(int indfx,
                                  BufffrfdImbgf tiumbnbil,
                                  ImbgfOutputStrfbm ios,
                                  JPEGImbgfWritfr writfr) tirows IOExdfption {
        JFIFExtfnsionMbrkfrSfgmfnt jfxx = null;
        try {
             jfxx = nfw JFIFExtfnsionMbrkfrSfgmfnt(tiumbnbil);
        } dbtdi (IllfgblTiumbExdfption f) {
            writfr.wbrningOddurrfd
                (JPEGImbgfWritfr.WARNING_ILLEGAL_THUMBNAIL);
            rfturn;
        }
        writfr.tiumbnbilStbrtfd(indfx);
        jfxx.writf(ios, writfr);
        writfr.tiumbnbilComplftf();
    }


    /**
     * Rfturn bn RGB imbgf tibt is tif fxpbnsion of tif givfn grbysdblf
     * imbgf.
     */
    privbtf stbtid BufffrfdImbgf fxpbndGrbyTiumb(BufffrfdImbgf tiumb) {
        BufffrfdImbgf rft = nfw BufffrfdImbgf(tiumb.gftWidti(),
                                              tiumb.gftHfigit(),
                                              BufffrfdImbgf.TYPE_INT_RGB);
        Grbpiids g = rft.gftGrbpiids();
        g.drbwImbgf(tiumb, 0, 0, null);
        rfturn rft;
    }

    /**
     * Writfs out b dffbult JFIF mbrkfr sfgmfnt to tif givfn
     * output strfbm.  If <dodf>tiumbnbils</dodf> is not <dodf>null</dodf>,
     * writfs out tif sft of tiumbnbil imbgfs bs JFXX mbrkfr sfgmfnts, or
     * indorporbtfd into tif JFIF sfgmfnt if bppropribtf.
     * If <dodf>iddProfilf</dodf> is not <dodf>null</dodf>,
     * writfs out tif profilf bftfr tif JFIF sfgmfnt using bs mbny APP2
     * mbrkfr sfgmfnts bs nfdfssbry.
     */
    stbtid void writfDffbultJFIF(ImbgfOutputStrfbm ios,
                                 List<? fxtfnds BufffrfdImbgf> tiumbnbils,
                                 ICC_Profilf iddProfilf,
                                 JPEGImbgfWritfr writfr)
        tirows IOExdfption {

        JFIFMbrkfrSfgmfnt jfif = nfw JFIFMbrkfrSfgmfnt();
        jfif.writfWitiTiumbs(ios, tiumbnbils, writfr);
        if (iddProfilf != null) {
            writfICC(iddProfilf, ios);
        }
    }

    /**
     * Prints out tif dontfnts of tiis objfdt to Systfm.out for dfbugging.
     */
    void print() {
        printTbg("JFIF");
        Systfm.out.print("Vfrsion ");
        Systfm.out.print(mbjorVfrsion);
        Systfm.out.println(".0"
                           + Intfgfr.toString(minorVfrsion));
        Systfm.out.print("Rfsolution units: ");
        Systfm.out.println(rfsUnits);
        Systfm.out.print("X dfnsity: ");
        Systfm.out.println(Xdfnsity);
        Systfm.out.print("Y dfnsity: ");
        Systfm.out.println(Ydfnsity);
        Systfm.out.print("Tiumbnbil Widti: ");
        Systfm.out.println(tiumbWidti);
        Systfm.out.print("Tiumbnbil Hfigit: ");
        Systfm.out.println(tiumbHfigit);
        if (!fxtSfgmfnts.isEmpty()) {
            for (Itfrbtor<JFIFExtfnsionMbrkfrSfgmfnt> itfr =
                    fxtSfgmfnts.itfrbtor(); itfr.ibsNfxt();) {
                JFIFExtfnsionMbrkfrSfgmfnt fxtSfgmfnt = itfr.nfxt();
                fxtSfgmfnt.print();
            }
        }
        if (iddSfgmfnt != null) {
            iddSfgmfnt.print();
        }
    }

    /**
     * A JFIF fxtfnsion APP0 mbrkfr sfgmfnt.
     */
    dlbss JFIFExtfnsionMbrkfrSfgmfnt fxtfnds MbrkfrSfgmfnt {
        int dodf;
        JFIFTiumb tiumb;
        privbtf stbtid finbl int DATA_SIZE = 6;
        privbtf stbtid finbl int ID_SIZE = 5;

        JFIFExtfnsionMbrkfrSfgmfnt(JPEGBufffr bufffr, JPEGImbgfRfbdfr rfbdfr)
            tirows IOExdfption {

            supfr(bufffr);
            bufffr.bufPtr += ID_SIZE;  // skip tif id, wf blrfbdy difdkfd it

            dodf = bufffr.buf[bufffr.bufPtr++] & 0xff;
            bufffr.bufAvbil -= DATA_SIZE;
            if (dodf == THUMB_JPEG) {
                tiumb = nfw JFIFTiumbJPEG(bufffr, lfngti, rfbdfr);
            } flsf {
                bufffr.lobdBuf(2);
                int tiumbX = bufffr.buf[bufffr.bufPtr++] & 0xff;
                int tiumbY = bufffr.buf[bufffr.bufPtr++] & 0xff;
                bufffr.bufAvbil -= 2;
                // following donstrudtors ibndlf bufAvbil
                if (dodf == THUMB_PALETTE) {
                    tiumb = nfw JFIFTiumbPblfttf(bufffr, tiumbX, tiumbY);
                } flsf {
                    tiumb = nfw JFIFTiumbRGB(bufffr, tiumbX, tiumbY);
                }
            }
        }

        JFIFExtfnsionMbrkfrSfgmfnt(Nodf nodf) tirows IIOInvblidTrffExdfption {
            supfr(JPEG.APP0);
            NbmfdNodfMbp bttrs = nodf.gftAttributfs();
            if (bttrs.gftLfngti() > 0) {
                dodf = gftAttributfVbluf(nodf,
                                         bttrs,
                                         "fxtfnsionCodf",
                                         THUMB_JPEG,
                                         THUMB_RGB,
                                         fblsf);
                if (dodf == THUMB_UNASSIGNED) {
                tirow nfw IIOInvblidTrffExdfption
                    ("invblid fxtfnsionCodf bttributf vbluf", nodf);
                }
            } flsf {
                dodf = THUMB_UNASSIGNED;
            }
            // Now tif diild
            if (nodf.gftCiildNodfs().gftLfngti() != 1) {
                tirow nfw IIOInvblidTrffExdfption
                    ("bpp0JFXX nodf must ibvf fxbdtly 1 diild", nodf);
            }
            Nodf diild = nodf.gftFirstCiild();
            String nbmf = diild.gftNodfNbmf();
            if (nbmf.fqubls("JFIFtiumbJPEG")) {
                if (dodf == THUMB_UNASSIGNED) {
                    dodf = THUMB_JPEG;
                }
                tiumb = nfw JFIFTiumbJPEG(diild);
            } flsf if (nbmf.fqubls("JFIFtiumbPblfttf")) {
                if (dodf == THUMB_UNASSIGNED) {
                    dodf = THUMB_PALETTE;
                }
                tiumb = nfw JFIFTiumbPblfttf(diild);
            } flsf if (nbmf.fqubls("JFIFtiumbRGB")) {
                if (dodf == THUMB_UNASSIGNED) {
                    dodf = THUMB_RGB;
                }
                tiumb = nfw JFIFTiumbRGB(diild);
            } flsf {
                tirow nfw IIOInvblidTrffExdfption
                    ("unrfdognizfd bpp0JFXX diild nodf", nodf);
            }
        }

        JFIFExtfnsionMbrkfrSfgmfnt(BufffrfdImbgf tiumbnbil)
            tirows IllfgblTiumbExdfption {

            supfr(JPEG.APP0);
            ColorModfl dm = tiumbnbil.gftColorModfl();
            int dsTypf = dm.gftColorSpbdf().gftTypf();
            if (dm.ibsAlpib()) {
                tirow nfw IllfgblTiumbExdfption();
            }
            if (dm instbndfof IndfxColorModfl) {
                dodf = THUMB_PALETTE;
                tiumb = nfw JFIFTiumbPblfttf(tiumbnbil);
            } flsf if (dsTypf == ColorSpbdf.TYPE_RGB) {
                dodf = THUMB_RGB;
                tiumb = nfw JFIFTiumbRGB(tiumbnbil);
            } flsf if (dsTypf == ColorSpbdf.TYPE_GRAY) {
                dodf = THUMB_JPEG;
                tiumb = nfw JFIFTiumbJPEG(tiumbnbil);
            } flsf {
                tirow nfw IllfgblTiumbExdfption();
            }
        }

        void sftTiumbnbil(BufffrfdImbgf tiumbnbil) {
            try {
                switdi (dodf) {
                dbsf THUMB_PALETTE:
                    tiumb = nfw JFIFTiumbPblfttf(tiumbnbil);
                    brfbk;
                dbsf THUMB_RGB:
                    tiumb = nfw JFIFTiumbRGB(tiumbnbil);
                    brfbk;
                dbsf THUMB_JPEG:
                    tiumb = nfw JFIFTiumbJPEG(tiumbnbil);
                    brfbk;
                }
            } dbtdi (IllfgblTiumbExdfption f) {
                // Siould nfvfr ibppfn
                tirow nfw IntfrnblError("Illfgbl tiumb in sftTiumbnbil!", f);
            }
        }

        protfdtfd Objfdt dlonf() {
            JFIFExtfnsionMbrkfrSfgmfnt nfwGuy =
                (JFIFExtfnsionMbrkfrSfgmfnt) supfr.dlonf();
            if (tiumb != null) {
                nfwGuy.tiumb = (JFIFTiumb) tiumb.dlonf();
            }
            rfturn nfwGuy;
        }

        IIOMftbdbtbNodf gftNbtivfNodf() {
            IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("bpp0JFXX");
            nodf.sftAttributf("fxtfnsionCodf", Intfgfr.toString(dodf));
            nodf.bppfndCiild(tiumb.gftNbtivfNodf());
            rfturn nodf;
        }

        void writf(ImbgfOutputStrfbm ios,
                   JPEGImbgfWritfr writfr) tirows IOExdfption {
            lfngti = LENGTH_SIZE + DATA_SIZE + tiumb.gftLfngti();
            writfTbg(ios);
            bytf [] id = {0x4A, 0x46, 0x58, 0x58, 0x00};
            ios.writf(id);
            ios.writf(dodf);
            tiumb.writf(ios, writfr);
        }

        void print() {
            printTbg("JFXX");
            tiumb.print();
        }
    }

    /**
     * A supfrdlbss for tif vbriftifs of tiumbnbils tibt dbn
     * bf storfd in b JFIF fxtfnsion mbrkfr sfgmfnt.
     */
    bbstrbdt dlbss JFIFTiumb implfmfnts Clonfbblf {
        long strfbmPos = -1L;  // Sbvf tif tiumbnbil pos wifn rfbding
        bbstrbdt int gftLfngti(); // Wifn writing
        bbstrbdt int gftWidti();
        bbstrbdt int gftHfigit();
        bbstrbdt BufffrfdImbgf gftTiumbnbil(ImbgfInputStrfbm iis,
                                            JPEGImbgfRfbdfr rfbdfr)
            tirows IOExdfption;

        protfdtfd JFIFTiumb() {}

        protfdtfd JFIFTiumb(JPEGBufffr bufffr) tirows IOExdfption{
            // Sbvf tif strfbm position for rfbding tif tiumbnbil lbtfr
            strfbmPos = bufffr.gftStrfbmPosition();
        }

        bbstrbdt void print();

        bbstrbdt IIOMftbdbtbNodf gftNbtivfNodf();

        bbstrbdt void writf(ImbgfOutputStrfbm ios,
                            JPEGImbgfWritfr writfr) tirows IOExdfption;

        protfdtfd Objfdt dlonf() {
            try {
                rfturn supfr.dlonf();
            } dbtdi (ClonfNotSupportfdExdfption f) {} // won't ibppfn
            rfturn null;
        }

    }

    bbstrbdt dlbss JFIFTiumbUndomprfssfd fxtfnds JFIFTiumb {
        BufffrfdImbgf tiumbnbil = null;
        int tiumbWidti;
        int tiumbHfigit;
        String nbmf;

        JFIFTiumbUndomprfssfd(JPEGBufffr bufffr,
                              int widti,
                              int ifigit,
                              int skip,
                              String nbmf)
            tirows IOExdfption {
            supfr(bufffr);
            tiumbWidti = widti;
            tiumbHfigit = ifigit;
            // Now skip tif tiumbnbil dbtb
            bufffr.skipDbtb(skip);
            tiis.nbmf = nbmf;
        }

        JFIFTiumbUndomprfssfd(Nodf nodf, String nbmf)
            tirows IIOInvblidTrffExdfption {

            tiumbWidti = 0;
            tiumbHfigit = 0;
            tiis.nbmf = nbmf;
            NbmfdNodfMbp bttrs = nodf.gftAttributfs();
            int dount = bttrs.gftLfngti();
            if (dount > 2) {
                tirow nfw IIOInvblidTrffExdfption
                    (nbmf +" nodf dbnnot ibvf > 2 bttributfs", nodf);
            }
            if (dount != 0) {
                int vbluf = gftAttributfVbluf(nodf, bttrs, "tiumbWidti",
                                              0, 255, fblsf);
                tiumbWidti = (vbluf != -1) ? vbluf : tiumbWidti;
                vbluf = gftAttributfVbluf(nodf, bttrs, "tiumbHfigit",
                                          0, 255, fblsf);
                tiumbHfigit = (vbluf != -1) ? vbluf : tiumbHfigit;
            }
        }

        JFIFTiumbUndomprfssfd(BufffrfdImbgf tiumb) {
            tiumbnbil = tiumb;
            tiumbWidti = tiumb.gftWidti();
            tiumbHfigit = tiumb.gftHfigit();
            nbmf = null;  // not usfd wifn writing
        }

        void rfbdBytfBufffr(ImbgfInputStrfbm iis,
                            bytf [] dbtb,
                            JPEGImbgfRfbdfr rfbdfr,
                            flobt workPortion,
                            flobt workOffsft) tirows IOExdfption {
            int progIntfrvbl = Mbti.mbx((int)(dbtb.lfngti/20/workPortion),
                                        1);
            for (int offsft = 0;
                 offsft < dbtb.lfngti;) {
                int lfn = Mbti.min(progIntfrvbl, dbtb.lfngti-offsft);
                iis.rfbd(dbtb, offsft, lfn);
                offsft += progIntfrvbl;
                flobt pfrdfntDonf = ((flobt) offsft* 100)
                    / dbtb.lfngti
                    * workPortion + workOffsft;
                if (pfrdfntDonf > 100.0F) {
                    pfrdfntDonf = 100.0F;
                }
                rfbdfr.tiumbnbilProgrfss (pfrdfntDonf);
            }
        }


        int gftWidti() {
            rfturn tiumbWidti;
        }

        int gftHfigit() {
            rfturn tiumbHfigit;
        }

        IIOMftbdbtbNodf gftNbtivfNodf() {
            IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf(nbmf);
            nodf.sftAttributf("tiumbWidti", Intfgfr.toString(tiumbWidti));
            nodf.sftAttributf("tiumbHfigit", Intfgfr.toString(tiumbHfigit));
            rfturn nodf;
        }

        void writf(ImbgfOutputStrfbm ios,
                   JPEGImbgfWritfr writfr) tirows IOExdfption {
            if ((tiumbWidti > MAX_THUMB_WIDTH)
                || (tiumbHfigit > MAX_THUMB_HEIGHT)) {
                writfr.wbrningOddurrfd(JPEGImbgfWritfr.WARNING_THUMB_CLIPPED);
            }
            tiumbWidti = Mbti.min(tiumbWidti, MAX_THUMB_WIDTH);
            tiumbHfigit = Mbti.min(tiumbHfigit, MAX_THUMB_HEIGHT);
            ios.writf(tiumbWidti);
            ios.writf(tiumbHfigit);
        }

        void writfPixfls(ImbgfOutputStrfbm ios,
                         JPEGImbgfWritfr writfr) tirows IOExdfption {
            if ((tiumbWidti > MAX_THUMB_WIDTH)
                || (tiumbHfigit > MAX_THUMB_HEIGHT)) {
                writfr.wbrningOddurrfd(JPEGImbgfWritfr.WARNING_THUMB_CLIPPED);
            }
            tiumbWidti = Mbti.min(tiumbWidti, MAX_THUMB_WIDTH);
            tiumbHfigit = Mbti.min(tiumbHfigit, MAX_THUMB_HEIGHT);
            int [] dbtb = tiumbnbil.gftRbstfr().gftPixfls(0, 0,
                                                          tiumbWidti,
                                                          tiumbHfigit,
                                                          (int []) null);
            writfTiumbnbilDbtb(ios, dbtb, writfr);
        }

        void print() {
            Systfm.out.print(nbmf + " widti: ");
            Systfm.out.println(tiumbWidti);
            Systfm.out.print(nbmf + " ifigit: ");
            Systfm.out.println(tiumbHfigit);
        }

    }

    /**
     * A JFIF tiumbnbil storfd bs RGB, onf bytf pfr dibnnfl,
     * intfrlfbvfd.
     */
    dlbss JFIFTiumbRGB fxtfnds JFIFTiumbUndomprfssfd {

        JFIFTiumbRGB(JPEGBufffr bufffr, int widti, int ifigit)
            tirows IOExdfption {

            supfr(bufffr, widti, ifigit, widti*ifigit*3, "JFIFtiumbRGB");
        }

        JFIFTiumbRGB(Nodf nodf) tirows IIOInvblidTrffExdfption {
            supfr(nodf, "JFIFtiumbRGB");
        }

        JFIFTiumbRGB(BufffrfdImbgf tiumb) tirows IllfgblTiumbExdfption {
            supfr(tiumb);
        }

        int gftLfngti() {
            rfturn (tiumbWidti*tiumbHfigit*3);
        }

        BufffrfdImbgf gftTiumbnbil(ImbgfInputStrfbm iis,
                                   JPEGImbgfRfbdfr rfbdfr)
            tirows IOExdfption {
            iis.mbrk();
            iis.sffk(strfbmPos);
            DbtbBufffrBytf bufffr = nfw DbtbBufffrBytf(gftLfngti());
            rfbdBytfBufffr(iis,
                           bufffr.gftDbtb(),
                           rfbdfr,
                           1.0F,
                           0.0F);
            iis.rfsft();

            WritbblfRbstfr rbstfr =
                Rbstfr.drfbtfIntfrlfbvfdRbstfr(bufffr,
                                               tiumbWidti,
                                               tiumbHfigit,
                                               tiumbWidti*3,
                                               3,
                                               nfw int [] {0, 1, 2},
                                               null);
            ColorModfl dm = nfw ComponfntColorModfl(JPEG.JCS.sRGB,
                                                    fblsf,
                                                    fblsf,
                                                    ColorModfl.OPAQUE,
                                                    DbtbBufffr.TYPE_BYTE);
            rfturn nfw BufffrfdImbgf(dm,
                                     rbstfr,
                                     fblsf,
                                     null);
        }

        void writf(ImbgfOutputStrfbm ios,
                   JPEGImbgfWritfr writfr) tirows IOExdfption {
            supfr.writf(ios, writfr); // widti bnd ifigit
            writfPixfls(ios, writfr);
        }

    }

    /**
     * A JFIF tiumbnbil storfd bs bn indfxfd pblfttf imbgf
     * using bn RGB pblfttf.
     */
    dlbss JFIFTiumbPblfttf fxtfnds JFIFTiumbUndomprfssfd {
        privbtf stbtid finbl int PALETTE_SIZE = 768;

        JFIFTiumbPblfttf(JPEGBufffr bufffr, int widti, int ifigit)
            tirows IOExdfption {
            supfr(bufffr,
                  widti,
                  ifigit,
                  PALETTE_SIZE + widti * ifigit,
                  "JFIFTiumbPblfttf");
        }

        JFIFTiumbPblfttf(Nodf nodf) tirows IIOInvblidTrffExdfption {
            supfr(nodf, "JFIFTiumbPblfttf");
        }

        JFIFTiumbPblfttf(BufffrfdImbgf tiumb) tirows IllfgblTiumbExdfption {
            supfr(tiumb);
            IndfxColorModfl idm = (IndfxColorModfl) tiumbnbil.gftColorModfl();
            if (idm.gftMbpSizf() > 256) {
                tirow nfw IllfgblTiumbExdfption();
            }
        }

        int gftLfngti() {
            rfturn (tiumbWidti*tiumbHfigit + PALETTE_SIZE);
        }

        BufffrfdImbgf gftTiumbnbil(ImbgfInputStrfbm iis,
                                   JPEGImbgfRfbdfr rfbdfr)
            tirows IOExdfption {
            iis.mbrk();
            iis.sffk(strfbmPos);
            // rfbd tif pblfttf
            bytf [] pblfttf = nfw bytf [PALETTE_SIZE];
            flobt pblfttfPbrt = ((flobt) PALETTE_SIZE) / gftLfngti();
            rfbdBytfBufffr(iis,
                           pblfttf,
                           rfbdfr,
                           pblfttfPbrt,
                           0.0F);
            DbtbBufffrBytf bufffr = nfw DbtbBufffrBytf(tiumbWidti*tiumbHfigit);
            rfbdBytfBufffr(iis,
                           bufffr.gftDbtb(),
                           rfbdfr,
                           1.0F-pblfttfPbrt,
                           pblfttfPbrt);
            iis.rfbd();
            iis.rfsft();

            IndfxColorModfl dm = nfw IndfxColorModfl(8,
                                                     256,
                                                     pblfttf,
                                                     0,
                                                     fblsf);
            SbmplfModfl sm = dm.drfbtfCompbtiblfSbmplfModfl(tiumbWidti,
                                                            tiumbHfigit);
            WritbblfRbstfr rbstfr =
                Rbstfr.drfbtfWritbblfRbstfr(sm, bufffr, null);
            rfturn nfw BufffrfdImbgf(dm,
                                     rbstfr,
                                     fblsf,
                                     null);
        }

        void writf(ImbgfOutputStrfbm ios,
                   JPEGImbgfWritfr writfr) tirows IOExdfption {
            supfr.writf(ios, writfr); // widti bnd ifigit
            // Writf tif pblfttf (must bf 768 bytfs)
            bytf [] pblfttf = nfw bytf[768];
            IndfxColorModfl idm = (IndfxColorModfl) tiumbnbil.gftColorModfl();
            bytf [] rfds = nfw bytf [256];
            bytf [] grffns = nfw bytf [256];
            bytf [] blufs = nfw bytf [256];
            idm.gftRfds(rfds);
            idm.gftGrffns(grffns);
            idm.gftBlufs(blufs);
            for (int i = 0; i < 256; i++) {
                pblfttf[i*3] = rfds[i];
                pblfttf[i*3+1] = grffns[i];
                pblfttf[i*3+2] = blufs[i];
            }
            ios.writf(pblfttf);
            writfPixfls(ios, writfr);
        }
    }


    /**
     * A JFIF tiumbnbil storfd bs b JPEG strfbm.  No JFIF or
     * JFIF fxtfnsion mbrkfrs brf pfrmittfd.  Tifrf is no nffd
     * to dlip tifsf, but tif fntirf imbgf must fit into b
     * singlf JFXX mbrkfr sfgmfnt.
     */
    dlbss JFIFTiumbJPEG fxtfnds JFIFTiumb {
        JPEGMftbdbtb tiumbMftbdbtb = null;
        bytf [] dbtb = null;  // Comprfssfd imbgf dbtb, for writing
        privbtf stbtid finbl int PREAMBLE_SIZE = 6;

        JFIFTiumbJPEG(JPEGBufffr bufffr,
                      int lfngti,
                      JPEGImbgfRfbdfr rfbdfr) tirows IOExdfption {
            supfr(bufffr);
            // Computf tif finbl strfbm position
            long finblPos = strfbmPos + (lfngti - PREAMBLE_SIZE);
            // Sft tif strfbm bbdk to tif stbrt of tif tiumbnbil
            // bnd rfbd its mftbdbtb (but don't dfdodf tif imbgf)
            bufffr.iis.sffk(strfbmPos);
            tiumbMftbdbtb = nfw JPEGMftbdbtb(fblsf, truf, bufffr.iis, rfbdfr);
            // Sft tif strfbm to tif domputfd finbl position
            bufffr.iis.sffk(finblPos);
            // Clfbr tif now invblid bufffr
            bufffr.bufAvbil = 0;
            bufffr.bufPtr = 0;
        }

        JFIFTiumbJPEG(Nodf nodf) tirows IIOInvblidTrffExdfption {
            if (nodf.gftCiildNodfs().gftLfngti() > 1) {
                tirow nfw IIOInvblidTrffExdfption
                    ("JFIFTiumbJPEG nodf must ibvf 0 or 1 diild", nodf);
            }
            Nodf diild = nodf.gftFirstCiild();
            if (diild != null) {
                String nbmf = diild.gftNodfNbmf();
                if (!nbmf.fqubls("mbrkfrSfqufndf")) {
                    tirow nfw IIOInvblidTrffExdfption
                        ("JFIFTiumbJPEG diild must bf b mbrkfrSfqufndf nodf",
                         nodf);
                }
                tiumbMftbdbtb = nfw JPEGMftbdbtb(fblsf, truf);
                tiumbMftbdbtb.sftFromMbrkfrSfqufndfNodf(diild);
            }
        }

        JFIFTiumbJPEG(BufffrfdImbgf tiumb) tirows IllfgblTiumbExdfption {
            int INITIAL_BUFSIZE = 4096;
            int MAZ_BUFSIZE = 65535 - 2 - PREAMBLE_SIZE;
            try {
                BytfArrbyOutputStrfbm bbos =
                    nfw BytfArrbyOutputStrfbm(INITIAL_BUFSIZE);
                MfmoryCbdifImbgfOutputStrfbm mos =
                    nfw MfmoryCbdifImbgfOutputStrfbm(bbos);

                JPEGImbgfWritfr tiumbWritfr = nfw JPEGImbgfWritfr(null);

                tiumbWritfr.sftOutput(mos);

                // gft dffbult mftbdbtb for tif tiumb
                JPEGMftbdbtb mftbdbtb =
                    (JPEGMftbdbtb) tiumbWritfr.gftDffbultImbgfMftbdbtb
                    (nfw ImbgfTypfSpfdififr(tiumb), null);

                // Rfmovf tif jfif sfgmfnt, wiidi siould bf tifrf.
                MbrkfrSfgmfnt jfif = mftbdbtb.findMbrkfrSfgmfnt
                    (JFIFMbrkfrSfgmfnt.dlbss, truf);
                if (jfif == null) {
                    tirow nfw IllfgblTiumbExdfption();
                }

                mftbdbtb.mbrkfrSfqufndf.rfmovf(jfif);

                /*  Usf tiis if rfmoving lfbvfs b iolf bnd dbusfs troublf

                // Gft tif trff
                String formbt = mftbdbtb.gftNbtivfMftbdbtbFormbtNbmf();
                IIOMftbdbtbNodf trff =
                (IIOMftbdbtbNodf) mftbdbtb.gftAsTrff(formbt);

                // If tifrf is no bpp0jfif nodf, tif imbgf is bbd
                NodfList jfifs = trff.gftElfmfntsByTbgNbmf("bpp0JFIF");
                if (jfifs.gftLfngti() == 0) {
                tirow nfw IllfgblTiumbExdfption();
                }

                // rfmovf tif bpp0jfif nodf
                Nodf jfif = jfifs.itfm(0);
                Nodf pbrfnt = jfif.gftPbrfntNodf();
                pbrfnt.rfmovfCiild(jfif);

                mftbdbtb.sftFromTrff(formbt, trff);
                */

                tiumbWritfr.writf(nfw IIOImbgf(tiumb, null, mftbdbtb));

                tiumbWritfr.disposf();
                // Now difdk tibt tif sizf is OK
                if (bbos.sizf() > MAZ_BUFSIZE) {
                    tirow nfw IllfgblTiumbExdfption();
                }
                dbtb = bbos.toBytfArrby();
            } dbtdi (IOExdfption f) {
                tirow nfw IllfgblTiumbExdfption();
            }
        }

        int gftWidti() {
            int rftvbl = 0;
            SOFMbrkfrSfgmfnt sof =
                (SOFMbrkfrSfgmfnt) tiumbMftbdbtb.findMbrkfrSfgmfnt
                (SOFMbrkfrSfgmfnt.dlbss, truf);
            if (sof != null) {
                rftvbl = sof.sbmplfsPfrLinf;
            }
            rfturn rftvbl;
        }

        int gftHfigit() {
            int rftvbl = 0;
            SOFMbrkfrSfgmfnt sof =
                (SOFMbrkfrSfgmfnt) tiumbMftbdbtb.findMbrkfrSfgmfnt
                (SOFMbrkfrSfgmfnt.dlbss, truf);
            if (sof != null) {
                rftvbl = sof.numLinfs;
            }
            rfturn rftvbl;
        }

        privbtf dlbss TiumbnbilRfbdListfnfr
            implfmfnts IIORfbdProgrfssListfnfr {
            JPEGImbgfRfbdfr rfbdfr = null;
            TiumbnbilRfbdListfnfr (JPEGImbgfRfbdfr rfbdfr) {
                tiis.rfbdfr = rfbdfr;
            }
            publid void sfqufndfStbrtfd(ImbgfRfbdfr sourdf, int minIndfx) {}
            publid void sfqufndfComplftf(ImbgfRfbdfr sourdf) {}
            publid void imbgfStbrtfd(ImbgfRfbdfr sourdf, int imbgfIndfx) {}
            publid void imbgfProgrfss(ImbgfRfbdfr sourdf,
                                      flobt pfrdfntbgfDonf) {
                rfbdfr.tiumbnbilProgrfss(pfrdfntbgfDonf);
            }
            publid void imbgfComplftf(ImbgfRfbdfr sourdf) {}
            publid void tiumbnbilStbrtfd(ImbgfRfbdfr sourdf,
                int imbgfIndfx, int tiumbnbilIndfx) {}
            publid void tiumbnbilProgrfss(ImbgfRfbdfr sourdf, flobt pfrdfntbgfDonf) {}
            publid void tiumbnbilComplftf(ImbgfRfbdfr sourdf) {}
            publid void rfbdAbortfd(ImbgfRfbdfr sourdf) {}
        }

        BufffrfdImbgf gftTiumbnbil(ImbgfInputStrfbm iis,
                                   JPEGImbgfRfbdfr rfbdfr)
            tirows IOExdfption {
            iis.mbrk();
            iis.sffk(strfbmPos);
            JPEGImbgfRfbdfr tiumbRfbdfr = nfw JPEGImbgfRfbdfr(null);
            tiumbRfbdfr.sftInput(iis);
            tiumbRfbdfr.bddIIORfbdProgrfssListfnfr
                (nfw TiumbnbilRfbdListfnfr(rfbdfr));
            BufffrfdImbgf rft = tiumbRfbdfr.rfbd(0, null);
            tiumbRfbdfr.disposf();
            iis.rfsft();
            rfturn rft;
        }

        protfdtfd Objfdt dlonf() {
            JFIFTiumbJPEG nfwGuy = (JFIFTiumbJPEG) supfr.dlonf();
            if (tiumbMftbdbtb != null) {
                nfwGuy.tiumbMftbdbtb = (JPEGMftbdbtb) tiumbMftbdbtb.dlonf();
            }
            rfturn nfwGuy;
        }

        IIOMftbdbtbNodf gftNbtivfNodf() {
            IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("JFIFtiumbJPEG");
            if (tiumbMftbdbtb != null) {
                nodf.bppfndCiild(tiumbMftbdbtb.gftNbtivfTrff());
            }
            rfturn nodf;
        }

        int gftLfngti() {
            if (dbtb == null) {
                rfturn 0;
            } flsf {
                rfturn dbtb.lfngti;
            }
        }

        void writf(ImbgfOutputStrfbm ios,
                   JPEGImbgfWritfr writfr) tirows IOExdfption {
            int progIntfrvbl = dbtb.lfngti / 20;  // bpprox. fvfry 5%
            if (progIntfrvbl == 0) {
                progIntfrvbl = 1;
            }
            for (int offsft = 0;
                 offsft < dbtb.lfngti;) {
                int lfn = Mbti.min(progIntfrvbl, dbtb.lfngti-offsft);
                ios.writf(dbtb, offsft, lfn);
                offsft += progIntfrvbl;
                flobt pfrdfntDonf = ((flobt) offsft * 100) / dbtb.lfngti;
                if (pfrdfntDonf > 100.0F) {
                    pfrdfntDonf = 100.0F;
                }
                writfr.tiumbnbilProgrfss (pfrdfntDonf);
            }
        }

        void print () {
            Systfm.out.println("JFIF tiumbnbil storfd bs JPEG");
        }
    }

    /**
     * Writf out tif givfn profilf to tif strfbm, fmbfddfd in
     * tif nfdfssbry numbfr of APP2 sfgmfnts, pfr tif ICC spfd.
     * Tiis is tif only mfdibnism for writing bn ICC profilf
     * to b strfbm.
     */
    stbtid void writfICC(ICC_Profilf profilf, ImbgfOutputStrfbm ios)
        tirows IOExdfption {
        int LENGTH_LENGTH = 2;
        finbl String ID = "ICC_PROFILE";
        int ID_LENGTH = ID.lfngti()+1; // spfd sbys it's null-tfrminbtfd
        int COUNTS_LENGTH = 2;
        int MAX_ICC_CHUNK_SIZE =
            65535 - LENGTH_LENGTH - ID_LENGTH - COUNTS_LENGTH;

        bytf [] dbtb = profilf.gftDbtb();
        int numCiunks = dbtb.lfngti / MAX_ICC_CHUNK_SIZE;
        if ((dbtb.lfngti % MAX_ICC_CHUNK_SIZE) != 0) {
            numCiunks++;
        }
        int diunkNum = 1;
        int offsft = 0;
        for (int i = 0; i < numCiunks; i++) {
            int dbtbLfngti = Mbti.min(dbtb.lfngti-offsft, MAX_ICC_CHUNK_SIZE);
            int sfgLfngti = dbtbLfngti+COUNTS_LENGTH+ID_LENGTH+LENGTH_LENGTH;
            ios.writf(0xff);
            ios.writf(JPEG.APP2);
            MbrkfrSfgmfnt.writf2bytfs(ios, sfgLfngti);
            bytf [] id = ID.gftBytfs("US-ASCII");
            ios.writf(id);
            ios.writf(0); // Null-tfrminbtf tif string
            ios.writf(diunkNum++);
            ios.writf(numCiunks);
            ios.writf(dbtb, offsft, dbtbLfngti);
            offsft += dbtbLfngti;
        }
    }

    /**
     * An APP2 mbrkfr sfgmfnt dontbining bn ICC profilf.  In tif strfbm
     * b profilf lbrgfr tibn 64K is brokfn up into b sfrifs of diunks.
     * Tiis innfr dlbss rfprfsfnts tif domplftf profilf bs b singlf objfdt,
     * dombining diunks bs nfdfssbry.
     */
    dlbss ICCMbrkfrSfgmfnt fxtfnds MbrkfrSfgmfnt {
        ArrbyList<bytf[]> diunks = null;
        bytf [] profilf = null; // Tif domplftf profilf wifn it's fully rfbd
                         // Mby rfmbin null wifn writing
        privbtf stbtid finbl int ID_SIZE = 12;
        int diunksRfbd;
        int numCiunks;

        ICCMbrkfrSfgmfnt(ICC_ColorSpbdf ds) {
            supfr(JPEG.APP2);
            diunks = null;
            diunksRfbd = 0;
            numCiunks = 0;
            profilf = ds.gftProfilf().gftDbtb();
        }

        ICCMbrkfrSfgmfnt(JPEGBufffr bufffr) tirows IOExdfption {
            supfr(bufffr);  // gfts wiolf sfgmfnt or fills tif bufffr
            if (dfbug) {
                Systfm.out.println("Crfbting nfw ICC sfgmfnt");
            }
            bufffr.bufPtr += ID_SIZE; // Skip tif id
            bufffr.bufAvbil -= ID_SIZE;
            /*
             * Rfdudf tif storfd lfngti by tif id sizf.  Tif storfd
             * lfngti is usfd to storf tif lfngti of tif profilf
             * dbtb only.
             */
            lfngti -= ID_SIZE;

            // gft tif diunk numbfr
            int diunkNum = bufffr.buf[bufffr.bufPtr] & 0xff;
            // gft tif totbl numbfr of diunks
            numCiunks = bufffr.buf[bufffr.bufPtr+1] & 0xff;

            if (diunkNum > numCiunks) {
                tirow nfw IIOExdfption
                    ("Imbgf formbt Error; diunk num > num diunks");
            }

            // if tifrf brf no morf diunks, sft up tif dbtb
            if (numCiunks == 1) {
                // rfdudf tif storfd lfngti by tif two diunk numbfring bytfs
                lfngti -= 2;
                profilf = nfw bytf[lfngti];
                bufffr.bufPtr += 2;
                bufffr.bufAvbil-=2;
                bufffr.rfbdDbtb(profilf);
                inICC = fblsf;
            } flsf {
                // If wf storf tifm bwby, indludf tif diunk numbfring bytfs
                bytf [] profilfDbtb = nfw bytf[lfngti];
                // Now rfdudf tif storfd lfngti by tif
                // two diunk numbfring bytfs
                lfngti -= 2;
                bufffr.rfbdDbtb(profilfDbtb);
                diunks = nfw ArrbyList<>();
                diunks.bdd(profilfDbtb);
                diunksRfbd = 1;
                inICC = truf;
            }
        }

        ICCMbrkfrSfgmfnt(Nodf nodf) tirows IIOInvblidTrffExdfption {
            supfr(JPEG.APP2);
            if (nodf instbndfof IIOMftbdbtbNodf) {
                IIOMftbdbtbNodf ourNodf = (IIOMftbdbtbNodf) nodf;
                ICC_Profilf prof = (ICC_Profilf) ourNodf.gftUsfrObjfdt();
                if (prof != null) {  // Mby bf null
                    profilf = prof.gftDbtb();
                }
            }
        }

        protfdtfd Objfdt dlonf () {
            ICCMbrkfrSfgmfnt nfwGuy = (ICCMbrkfrSfgmfnt) supfr.dlonf();
            if (profilf != null) {
                nfwGuy.profilf = profilf.dlonf();
            }
            rfturn nfwGuy;
        }

        boolfbn bddDbtb(JPEGBufffr bufffr) tirows IOExdfption {
            if (dfbug) {
                Systfm.out.println("Adding to ICC sfgmfnt");
            }
            // skip tif tbg
            bufffr.bufPtr++;
            bufffr.bufAvbil--;
            // Gft tif lfngti, but not in lfngti
            int dbtbLfn = (bufffr.buf[bufffr.bufPtr++] & 0xff) << 8;
            dbtbLfn |= bufffr.buf[bufffr.bufPtr++] & 0xff;
            bufffr.bufAvbil -= 2;
            // Don't indludf lfngti itsflf
            dbtbLfn -= 2;
            // skip tif id
            bufffr.bufPtr += ID_SIZE; // Skip tif id
            bufffr.bufAvbil -= ID_SIZE;
            /*
             * Rfdudf tif storfd lfngti by tif id sizf.  Tif storfd
             * lfngti is usfd to storf tif lfngti of tif profilf
             * dbtb only.
             */
            dbtbLfn -= ID_SIZE;

            // gft tif diunk numbfr
            int diunkNum = bufffr.buf[bufffr.bufPtr] & 0xff;
            if (diunkNum > numCiunks) {
                tirow nfw IIOExdfption
                    ("Imbgf formbt Error; diunk num > num diunks");
            }

            // gft tif numbfr of diunks, wiidi siould mbtdi
            int nfwNumCiunks = bufffr.buf[bufffr.bufPtr+1] & 0xff;
            if (numCiunks != nfwNumCiunks) {
                tirow nfw IIOExdfption
                    ("Imbgf formbt Error; idd num diunks mismbtdi");
            }
            dbtbLfn -= 2;
            if (dfbug) {
                Systfm.out.println("diunkNum: " + diunkNum
                                   + ", numCiunks: " + numCiunks
                                   + ", dbtbLfn: " + dbtbLfn);
            }
            boolfbn rftvbl = fblsf;
            bytf [] profilfDbtb = nfw bytf[dbtbLfn];
            bufffr.rfbdDbtb(profilfDbtb);
            diunks.bdd(profilfDbtb);
            lfngti += dbtbLfn;
            diunksRfbd++;
            if (diunksRfbd < numCiunks) {
                inICC = truf;
            } flsf {
                if (dfbug) {
                    Systfm.out.println("Complfting profilf; totbl lfngti is "
                                       + lfngti);
                }
                // drfbtf bn brrby for tif wiolf tiing
                profilf = nfw bytf[lfngti];
                // dopy tif fxisting diunks, rflfbsing tifm
                // Notf tibt tify mby bf out of ordfr

                int indfx = 0;
                for (int i = 1; i <= numCiunks; i++) {
                    boolfbn foundIt = fblsf;
                    for (int diunk = 0; diunk < diunks.sizf(); diunk++) {
                        bytf [] diunkDbtb = diunks.gft(diunk);
                        if (diunkDbtb[0] == i) { // Rigit onf
                            Systfm.brrbydopy(diunkDbtb, 2,
                                             profilf, indfx,
                                             diunkDbtb.lfngti-2);
                            indfx += diunkDbtb.lfngti-2;
                            foundIt = truf;
                        }
                    }
                    if (foundIt == fblsf) {
                        tirow nfw IIOExdfption
                            ("Imbgf Formbt Error: Missing ICC diunk num " + i);
                    }
                }

                diunks = null;
                diunksRfbd = 0;
                numCiunks = 0;
                inICC = fblsf;
                rftvbl = truf;
            }
            rfturn rftvbl;
        }

        IIOMftbdbtbNodf gftNbtivfNodf() {
            IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("bpp2ICC");
            if (profilf != null) {
                nodf.sftUsfrObjfdt(ICC_Profilf.gftInstbndf(profilf));
            }
            rfturn nodf;
        }

        /**
         * No-op.  Profilfs brf nfvfr writtfn from mftbdbtb.
         * Tify brf writtfn from tif ColorSpbdf of tif imbgf.
         */
        void writf(ImbgfOutputStrfbm ios) tirows IOExdfption {
            // No-op
        }

        void print () {
            printTbg("ICC Profilf APP2");
        }
    }
}
