/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.imbgfio.ImbgfWritfr;
import jbvbx.imbgfio.ImbgfWritfPbrbm;
import jbvbx.imbgfio.IIOImbgf;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;
import jbvbx.imbgfio.mftbdbtb.IIOInvblidTrffExdfption;
import jbvbx.imbgfio.spi.ImbgfWritfrSpi;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;
import jbvbx.imbgfio.plugins.jpfg.JPEGImbgfWritfPbrbm;
import jbvbx.imbgfio.plugins.jpfg.JPEGQTbblf;
import jbvbx.imbgfio.plugins.jpfg.JPEGHuffmbnTbblf;

import org.w3d.dom.Nodf;

import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DbtbBufffrBytf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.ColorConvfrtOp;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.dolor.ICC_ColorSpbdf;
import jbvb.bwt.dolor.ICC_Profilf;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Trbnspbrfndy;

import jbvb.io.IOExdfption;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;

import sun.jbvb2d.Disposfr;
import sun.jbvb2d.DisposfrRfdord;

publid dlbss JPEGImbgfWritfr fxtfnds ImbgfWritfr {

    ///////// Privbtf vbribblfs

    privbtf boolfbn dfbug = fblsf;

    /**
     * Tif following vbribblf dontbins b pointfr to tif IJG librbry
     * strudturf for tiis rfbdfr.  It is bssignfd in tif donstrudtor
     * bnd tifn is pbssfd in to fvfry nbtivf dbll.  It is sft to 0
     * by disposf to bvoid disposing twidf.
     */
    privbtf long strudtPointfr = 0;


    /** Tif output strfbm wf writf to */
    privbtf ImbgfOutputStrfbm ios = null;

    /** Tif Rbstfr wf will writf from */
    privbtf Rbstfr srdRbs = null;

    /** An intfrmfdibtf Rbstfr iolding domprfssor-frifndly dbtb */
    privbtf WritbblfRbstfr rbstfr = null;

    /**
     * Sft to truf if wf brf writing bn imbgf witi bn
     * indfxfd ColorModfl
     */
    privbtf boolfbn indfxfd = fblsf;
    privbtf IndfxColorModfl indfxCM = null;

    privbtf boolfbn donvfrtTosRGB = fblsf;  // Usfd by PiotoYCC only
    privbtf WritbblfRbstfr donvfrtfd = null;

    privbtf boolfbn isAlpibPrfmultiplifd = fblsf;
    privbtf ColorModfl srdCM = null;

    /**
     * If tifrf brf tiumbnbils to bf writtfn, tiis is tif list.
     */
    privbtf List<? fxtfnds BufffrfdImbgf> tiumbnbils = null;

    /**
     * If mftbdbtb siould indludf bn idd profilf, storf it ifrf.
     */
    privbtf ICC_Profilf iddProfilf = null;

    privbtf int sourdfXOffsft = 0;
    privbtf int sourdfYOffsft = 0;
    privbtf int sourdfWidti = 0;
    privbtf int [] srdBbnds = null;
    privbtf int sourdfHfigit = 0;

    /** Usfd wifn dblling listfnfrs */
    privbtf int durrfntImbgf = 0;

    privbtf ColorConvfrtOp donvfrtOp = null;

    privbtf JPEGQTbblf [] strfbmQTbblfs = null;
    privbtf JPEGHuffmbnTbblf[] strfbmDCHuffmbnTbblfs = null;
    privbtf JPEGHuffmbnTbblf[] strfbmACHuffmbnTbblfs = null;

    // Pbrbmftfrs for writing mftbdbtb
    privbtf boolfbn ignorfJFIF = fblsf;  // If it's tifrf, usf it
    privbtf boolfbn fordfJFIF = fblsf;  // Add onf for tif tiumbnbils
    privbtf boolfbn ignorfAdobf = fblsf;  // If it's tifrf, usf it
    privbtf int nfwAdobfTrbnsform = JPEG.ADOBE_IMPOSSIBLE;  // Cibngf if nffdfd
    privbtf boolfbn writfDffbultJFIF = fblsf;
    privbtf boolfbn writfAdobf = fblsf;
    privbtf JPEGMftbdbtb mftbdbtb = null;

    privbtf boolfbn sfqufndfPrfpbrfd = fblsf;

    privbtf int numSdbns = 0;

    /** Tif rfffrfnt to bf rfgistfrfd witi tif Disposfr. */
    privbtf Objfdt disposfrRfffrfnt = nfw Objfdt();

    /** Tif DisposfrRfdord tibt ibndlfs tif bdtubl disposbl of tiis writfr. */
    privbtf DisposfrRfdord disposfrRfdord;

    ///////// End of Privbtf vbribblfs

    ///////// Protfdtfd vbribblfs

    protfdtfd stbtid finbl int WARNING_DEST_IGNORED = 0;
    protfdtfd stbtid finbl int WARNING_STREAM_METADATA_IGNORED = 1;
    protfdtfd stbtid finbl int WARNING_DEST_METADATA_COMP_MISMATCH = 2;
    protfdtfd stbtid finbl int WARNING_DEST_METADATA_JFIF_MISMATCH = 3;
    protfdtfd stbtid finbl int WARNING_DEST_METADATA_ADOBE_MISMATCH = 4;
    protfdtfd stbtid finbl int WARNING_IMAGE_METADATA_JFIF_MISMATCH = 5;
    protfdtfd stbtid finbl int WARNING_IMAGE_METADATA_ADOBE_MISMATCH = 6;
    protfdtfd stbtid finbl int WARNING_METADATA_NOT_JPEG_FOR_RASTER = 7;
    protfdtfd stbtid finbl int WARNING_NO_BANDS_ON_INDEXED = 8;
    protfdtfd stbtid finbl int WARNING_ILLEGAL_THUMBNAIL = 9;
    protfdtfd stbtid finbl int WARNING_IGNORING_THUMBS = 10;
    protfdtfd stbtid finbl int WARNING_FORCING_JFIF = 11;
    protfdtfd stbtid finbl int WARNING_THUMB_CLIPPED = 12;
    protfdtfd stbtid finbl int WARNING_METADATA_ADJUSTED_FOR_THUMB = 13;
    protfdtfd stbtid finbl int WARNING_NO_RGB_THUMB_AS_INDEXED = 14;
    protfdtfd stbtid finbl int WARNING_NO_GRAY_THUMB_AS_INDEXED = 15;

    privbtf stbtid finbl int MAX_WARNING = WARNING_NO_GRAY_THUMB_AS_INDEXED;

    ///////// End of Protfdtfd vbribblfs

    ///////// stbtid initiblizfr

    stbtid {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("jbvbjpfg");
                    rfturn null;
                }
            });
        initWritfrIDs(JPEGQTbblf.dlbss,
                      JPEGHuffmbnTbblf.dlbss);
    }

    //////// Publid API

    publid JPEGImbgfWritfr(ImbgfWritfrSpi originbtor) {
        supfr(originbtor);
        strudtPointfr = initJPEGImbgfWritfr();
        disposfrRfdord = nfw JPEGWritfrDisposfrRfdord(strudtPointfr);
        Disposfr.bddRfdord(disposfrRfffrfnt, disposfrRfdord);
    }

    publid void sftOutput(Objfdt output) {
        sftTirfbdLodk();
        try {
            dbLodk.difdk();

            supfr.sftOutput(output); // vblidbtfs output
            rfsftIntfrnblStbtf();
            ios = (ImbgfOutputStrfbm) output; // so tiis will blwbys work
            // Sft tif nbtivf dfstinbtion
            sftDfst(strudtPointfr);
        } finblly {
            dlfbrTirfbdLodk();
        }
    }

    publid ImbgfWritfPbrbm gftDffbultWritfPbrbm() {
        rfturn nfw JPEGImbgfWritfPbrbm(null);
    }

    publid IIOMftbdbtb gftDffbultStrfbmMftbdbtb(ImbgfWritfPbrbm pbrbm) {
        sftTirfbdLodk();
        try {
            rfturn nfw JPEGMftbdbtb(pbrbm, tiis);
        } finblly {
            dlfbrTirfbdLodk();
        }
    }

    publid IIOMftbdbtb
        gftDffbultImbgfMftbdbtb(ImbgfTypfSpfdififr imbgfTypf,
                                ImbgfWritfPbrbm pbrbm) {
        sftTirfbdLodk();
        try {
            rfturn nfw JPEGMftbdbtb(imbgfTypf, pbrbm, tiis);
        } finblly {
            dlfbrTirfbdLodk();
        }
    }

    publid IIOMftbdbtb donvfrtStrfbmMftbdbtb(IIOMftbdbtb inDbtb,
                                             ImbgfWritfPbrbm pbrbm) {
        // Tifrf isn't mudi wf dbn do.  If it's onf of ours, tifn
        // rfturn it.  Otifrwisf just rfturn null.  Wf usf it only
        // for tbblfs, so wf dbn't gft b dffbult bnd modify it,
        // bs tiis will usublly not bf wibt is intfndfd.
        if (inDbtb instbndfof JPEGMftbdbtb) {
            JPEGMftbdbtb jpfgDbtb = (JPEGMftbdbtb) inDbtb;
            if (jpfgDbtb.isStrfbm) {
                rfturn inDbtb;
            }
        }
        rfturn null;
    }

    publid IIOMftbdbtb
        donvfrtImbgfMftbdbtb(IIOMftbdbtb inDbtb,
                             ImbgfTypfSpfdififr imbgfTypf,
                             ImbgfWritfPbrbm pbrbm) {
        sftTirfbdLodk();
        try {
            rfturn donvfrtImbgfMftbdbtbOnTirfbd(inDbtb, imbgfTypf, pbrbm);
        } finblly {
            dlfbrTirfbdLodk();
        }
    }

    privbtf IIOMftbdbtb
        donvfrtImbgfMftbdbtbOnTirfbd(IIOMftbdbtb inDbtb,
                                     ImbgfTypfSpfdififr imbgfTypf,
                                     ImbgfWritfPbrbm pbrbm) {
        // If it's onf of ours, just rfturn it
        if (inDbtb instbndfof JPEGMftbdbtb) {
            JPEGMftbdbtb jpfgDbtb = (JPEGMftbdbtb) inDbtb;
            if (!jpfgDbtb.isStrfbm) {
                rfturn inDbtb;
            } flsf {
                // Cbn't donvfrt strfbm mftbdbtb to imbgf mftbdbtb
                // XXX Mbybf tiis siould put out b wbrning?
                rfturn null;
            }
        }
        // If it's not onf of ours, drfbtf b dffbult bnd sft it from
        // tif stbndbrd trff from tif input, if it fxists.
        if (inDbtb.isStbndbrdMftbdbtbFormbtSupportfd()) {
            String formbtNbmf =
                IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf;
            Nodf trff = inDbtb.gftAsTrff(formbtNbmf);
            if (trff != null) {
                JPEGMftbdbtb jpfgDbtb = nfw JPEGMftbdbtb(imbgfTypf,
                                                         pbrbm,
                                                         tiis);
                try {
                    jpfgDbtb.sftFromTrff(formbtNbmf, trff);
                } dbtdi (IIOInvblidTrffExdfption f) {
                    // Otifr plug-in gfnfrbtfs bogus stbndbrd trff
                    // XXX Mbybf tiis siould put out b wbrning?
                    rfturn null;
                }

                rfturn jpfgDbtb;
            }
        }
        rfturn null;
    }

    publid int gftNumTiumbnbilsSupportfd(ImbgfTypfSpfdififr imbgfTypf,
                                         ImbgfWritfPbrbm pbrbm,
                                         IIOMftbdbtb strfbmMftbdbtb,
                                         IIOMftbdbtb imbgfMftbdbtb) {
        if (jfifOK(imbgfTypf, pbrbm, strfbmMftbdbtb, imbgfMftbdbtb)) {
            rfturn Intfgfr.MAX_VALUE;
        }
        rfturn 0;
    }

    stbtid finbl Dimfnsion [] prfffrrfdTiumbSizfs = {nfw Dimfnsion(1, 1),
                                                     nfw Dimfnsion(255, 255)};

    publid Dimfnsion[] gftPrfffrrfdTiumbnbilSizfs(ImbgfTypfSpfdififr imbgfTypf,
                                                  ImbgfWritfPbrbm pbrbm,
                                                  IIOMftbdbtb strfbmMftbdbtb,
                                                  IIOMftbdbtb imbgfMftbdbtb) {
        if (jfifOK(imbgfTypf, pbrbm, strfbmMftbdbtb, imbgfMftbdbtb)) {
            rfturn prfffrrfdTiumbSizfs.dlonf();
        }
        rfturn null;
    }

    privbtf boolfbn jfifOK(ImbgfTypfSpfdififr imbgfTypf,
                           ImbgfWritfPbrbm pbrbm,
                           IIOMftbdbtb strfbmMftbdbtb,
                           IIOMftbdbtb imbgfMftbdbtb) {
        // If tif imbgf typf bnd mftbdbtb brf JFIF dompbtiblf, rfturn truf
        if ((imbgfTypf != null) &&
            (!JPEG.isJFIFdomplibnt(imbgfTypf, truf))) {
            rfturn fblsf;
        }
        if (imbgfMftbdbtb != null) {
            JPEGMftbdbtb mftbdbtb = null;
            if (imbgfMftbdbtb instbndfof JPEGMftbdbtb) {
                mftbdbtb = (JPEGMftbdbtb) imbgfMftbdbtb;
            } flsf {
                mftbdbtb = (JPEGMftbdbtb)donvfrtImbgfMftbdbtb(imbgfMftbdbtb,
                                                              imbgfTypf,
                                                              pbrbm);
            }
            // mftbdbtb must ibvf b jfif nodf
            if (mftbdbtb.findMbrkfrSfgmfnt
                (JFIFMbrkfrSfgmfnt.dlbss, truf) == null){
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    publid boolfbn dbnWritfRbstfrs() {
        rfturn truf;
    }

    publid void writf(IIOMftbdbtb strfbmMftbdbtb,
                      IIOImbgf imbgf,
                      ImbgfWritfPbrbm pbrbm) tirows IOExdfption {
        sftTirfbdLodk();
        try {
            dbLodk.difdk();

            writfOnTirfbd(strfbmMftbdbtb, imbgf, pbrbm);
        } finblly {
            dlfbrTirfbdLodk();
        }
    }

    privbtf void writfOnTirfbd(IIOMftbdbtb strfbmMftbdbtb,
                      IIOImbgf imbgf,
                      ImbgfWritfPbrbm pbrbm) tirows IOExdfption {

        if (ios == null) {
            tirow nfw IllfgblStbtfExdfption("Output ibs not bffn sft!");
        }

        if (imbgf == null) {
            tirow nfw IllfgblArgumfntExdfption("imbgf is null!");
        }

        // if strfbmMftbdbtb is not null, issuf b wbrning
        if (strfbmMftbdbtb != null) {
            wbrningOddurrfd(WARNING_STREAM_METADATA_IGNORED);
        }

        // Obtbin tif rbstfr bnd imbgf, if tifrf is onf
        boolfbn rbstfrOnly = imbgf.ibsRbstfr();

        RfndfrfdImbgf rimbgf = null;
        if (rbstfrOnly) {
            srdRbs = imbgf.gftRbstfr();
        } flsf {
            rimbgf = imbgf.gftRfndfrfdImbgf();
            if (rimbgf instbndfof BufffrfdImbgf) {
                // Usf tif Rbstfr dirfdtly.
                srdRbs = ((BufffrfdImbgf)rimbgf).gftRbstfr();
            } flsf if (rimbgf.gftNumXTilfs() == 1 &&
                       rimbgf.gftNumYTilfs() == 1)
            {
                // Gft tif uniquf tilf.
                srdRbs = rimbgf.gftTilf(rimbgf.gftMinTilfX(),
                                        rimbgf.gftMinTilfY());

                // Ensurf tif Rbstfr ibs dimfnsions of tif imbgf,
                // bs tif tilf dimfnsions migit difffr.
                if (srdRbs.gftWidti() != rimbgf.gftWidti() ||
                    srdRbs.gftHfigit() != rimbgf.gftHfigit())
                {
                    srdRbs = srdRbs.drfbtfCiild(srdRbs.gftMinX(),
                                                srdRbs.gftMinY(),
                                                rimbgf.gftWidti(),
                                                rimbgf.gftHfigit(),
                                                srdRbs.gftMinX(),
                                                srdRbs.gftMinY(),
                                                null);
                }
            } flsf {
                // Imbgf is tilfd so gft b dontiguous rbstfr by dopying.
                srdRbs = rimbgf.gftDbtb();
            }
        }

        // Now dftfrminf if wf brf using b bbnd subsft

        // By dffbult, wf brf using bll sourdf bbnds
        int numSrdBbnds = srdRbs.gftNumBbnds();
        indfxfd = fblsf;
        indfxCM = null;
        ColorModfl dm = null;
        ColorSpbdf ds = null;
        isAlpibPrfmultiplifd = fblsf;
        srdCM = null;
        if (!rbstfrOnly) {
            dm = rimbgf.gftColorModfl();
            if (dm != null) {
                ds = dm.gftColorSpbdf();
                if (dm instbndfof IndfxColorModfl) {
                    indfxfd = truf;
                    indfxCM = (IndfxColorModfl) dm;
                    numSrdBbnds = dm.gftNumComponfnts();
                }
                if (dm.isAlpibPrfmultiplifd()) {
                    isAlpibPrfmultiplifd = truf;
                    srdCM = dm;
                }
            }
        }

        srdBbnds = JPEG.bbndOffsfts[numSrdBbnds-1];
        int numBbndsUsfd = numSrdBbnds;
        // Consult tif pbrbm to dftfrminf if wf'rf writing b subsft

        if (pbrbm != null) {
            int[] sBbnds = pbrbm.gftSourdfBbnds();
            if (sBbnds != null) {
                if (indfxfd) {
                    wbrningOddurrfd(WARNING_NO_BANDS_ON_INDEXED);
                } flsf {
                    srdBbnds = sBbnds;
                    numBbndsUsfd = srdBbnds.lfngti;
                    if (numBbndsUsfd > numSrdBbnds) {
                        tirow nfw IIOExdfption
                        ("ImbgfWritfPbrbm spfdififs too mbny sourdf bbnds");
                    }
                }
            }
        }

        boolfbn usingBbndSubsft = (numBbndsUsfd != numSrdBbnds);
        boolfbn fullImbgf = ((!rbstfrOnly) && (!usingBbndSubsft));

        int [] bbndSizfs = null;
        if (!indfxfd) {
            bbndSizfs = srdRbs.gftSbmplfModfl().gftSbmplfSizf();
            // If tiis is b subsft, wf must bdjust bbndSizfs
            if (usingBbndSubsft) {
                int [] tfmp = nfw int [numBbndsUsfd];
                for (int i = 0; i < numBbndsUsfd; i++) {
                    tfmp[i] = bbndSizfs[srdBbnds[i]];
                }
                bbndSizfs = tfmp;
            }
        } flsf {
            int [] tfmpSizf = srdRbs.gftSbmplfModfl().gftSbmplfSizf();
            bbndSizfs = nfw int [numSrdBbnds];
            for (int i = 0; i < numSrdBbnds; i++) {
                bbndSizfs[i] = tfmpSizf[0];  // All tif sbmf
            }
        }

        for (int i = 0; i < bbndSizfs.lfngti; i++) {
            // 4450894 pbrt 1: Tif IJG librbrifs brf dompilfd so tify only
            // ibndlf <= 8-bit sbmplfs.  Wf now difdk tif bbnd sizfs bnd tirow
            // bn fxdfption for imbgfs, sudi bs USHORT_GRAY, witi > 8 bits
            // pfr sbmplf.
            if (bbndSizfs[i] <= 0 || bbndSizfs[i] > 8) {
                tirow nfw IIOExdfption("Illfgbl bbnd sizf: siould bf 0 < sizf <= 8");
            }
            // 4450894 pbrt 2: Wf fxpbnd IndfxColorModfl imbgfs to full 24-
            // or 32-bit in grbbPixfls() for fbdi sdbnlinf.  For indfxfd
            // imbgfs sudi bs BYTE_BINARY, wf nffd to fnsurf tibt wf updbtf
            // bbndSizfs to bddount for tif sdbling from 1-bit bbnd sizfs
            // to 8-bit.
            if (indfxfd) {
                bbndSizfs[i] = 8;
            }
        }

        if (dfbug) {
            Systfm.out.println("numSrdBbnds is " + numSrdBbnds);
            Systfm.out.println("numBbndsUsfd is " + numBbndsUsfd);
            Systfm.out.println("usingBbndSubsft is " + usingBbndSubsft);
            Systfm.out.println("fullImbgf is " + fullImbgf);
            Systfm.out.print("Bbnd sizfs:");
            for (int i = 0; i< bbndSizfs.lfngti; i++) {
                Systfm.out.print(" " + bbndSizfs[i]);
            }
            Systfm.out.println();
        }

        // Dfstinbtion typf, if tifrf is onf
        ImbgfTypfSpfdififr dfstTypf = null;
        if (pbrbm != null) {
            dfstTypf = pbrbm.gftDfstinbtionTypf();
            // Ignorf dfst typf if wf brf writing b domplftf imbgf
            if ((fullImbgf) && (dfstTypf != null)) {
                wbrningOddurrfd(WARNING_DEST_IGNORED);
                dfstTypf = null;
            }
        }

        // Exbminf tif pbrbm

        sourdfXOffsft = srdRbs.gftMinX();
        sourdfYOffsft = srdRbs.gftMinY();
        int imbgfWidti = srdRbs.gftWidti();
        int imbgfHfigit = srdRbs.gftHfigit();
        sourdfWidti = imbgfWidti;
        sourdfHfigit = imbgfHfigit;
        int pfriodX = 1;
        int pfriodY = 1;
        int gridX = 0;
        int gridY = 0;
        JPEGQTbblf [] qTbblfs = null;
        JPEGHuffmbnTbblf[] DCHuffmbnTbblfs = null;
        JPEGHuffmbnTbblf[] ACHuffmbnTbblfs = null;
        boolfbn optimizfHuffmbn = fblsf;
        JPEGImbgfWritfPbrbm jpbrbm = null;
        int progrfssivfModf = ImbgfWritfPbrbm.MODE_DISABLED;

        if (pbrbm != null) {

            Rfdtbnglf sourdfRfgion = pbrbm.gftSourdfRfgion();
            if (sourdfRfgion != null) {
                Rfdtbnglf imbgfBounds = nfw Rfdtbnglf(sourdfXOffsft,
                                                      sourdfYOffsft,
                                                      sourdfWidti,
                                                      sourdfHfigit);
                sourdfRfgion = sourdfRfgion.intfrsfdtion(imbgfBounds);
                sourdfXOffsft = sourdfRfgion.x;
                sourdfYOffsft = sourdfRfgion.y;
                sourdfWidti = sourdfRfgion.widti;
                sourdfHfigit = sourdfRfgion.ifigit;
            }

            if (sourdfWidti + sourdfXOffsft > imbgfWidti) {
                sourdfWidti = imbgfWidti - sourdfXOffsft;
            }
            if (sourdfHfigit + sourdfYOffsft > imbgfHfigit) {
                sourdfHfigit = imbgfHfigit - sourdfYOffsft;
            }

            pfriodX = pbrbm.gftSourdfXSubsbmpling();
            pfriodY = pbrbm.gftSourdfYSubsbmpling();
            gridX = pbrbm.gftSubsbmplingXOffsft();
            gridY = pbrbm.gftSubsbmplingYOffsft();

            switdi(pbrbm.gftComprfssionModf()) {
            dbsf ImbgfWritfPbrbm.MODE_DISABLED:
                tirow nfw IIOExdfption("JPEG domprfssion dbnnot bf disbblfd");
            dbsf ImbgfWritfPbrbm.MODE_EXPLICIT:
                flobt qublity = pbrbm.gftComprfssionQublity();
                qublity = JPEG.donvfrtToLinfbrQublity(qublity);
                qTbblfs = nfw JPEGQTbblf[2];
                qTbblfs[0] = JPEGQTbblf.K1Luminbndf.gftSdblfdInstbndf
                    (qublity, truf);
                qTbblfs[1] = JPEGQTbblf.K2Cirominbndf.gftSdblfdInstbndf
                    (qublity, truf);
                brfbk;
            dbsf ImbgfWritfPbrbm.MODE_DEFAULT:
                qTbblfs = nfw JPEGQTbblf[2];
                qTbblfs[0] = JPEGQTbblf.K1Div2Luminbndf;
                qTbblfs[1] = JPEGQTbblf.K2Div2Cirominbndf;
                brfbk;
            // Wf'll ibndlf tif mftbdbtb dbsf lbtfr
            }

            progrfssivfModf = pbrbm.gftProgrfssivfModf();

            if (pbrbm instbndfof JPEGImbgfWritfPbrbm) {
                jpbrbm = (JPEGImbgfWritfPbrbm)pbrbm;
                optimizfHuffmbn = jpbrbm.gftOptimizfHuffmbnTbblfs();
            }
        }

        // Now fxbminf tif mftbdbtb
        IIOMftbdbtb mdbtb = imbgf.gftMftbdbtb();
        if (mdbtb != null) {
            if (mdbtb instbndfof JPEGMftbdbtb) {
                mftbdbtb = (JPEGMftbdbtb) mdbtb;
                if (dfbug) {
                    Systfm.out.println
                        ("Wf ibvf mftbdbtb, bnd it's JPEG mftbdbtb");
                }
            } flsf {
                if (!rbstfrOnly) {
                    ImbgfTypfSpfdififr typf = dfstTypf;
                    if (typf == null) {
                        typf = nfw ImbgfTypfSpfdififr(rimbgf);
                    }
                    mftbdbtb = (JPEGMftbdbtb) donvfrtImbgfMftbdbtb(mdbtb,
                                                                   typf,
                                                                   pbrbm);
                } flsf {
                    wbrningOddurrfd(WARNING_METADATA_NOT_JPEG_FOR_RASTER);
                }
            }
        }

        // First sft b dffbult stbtf

        ignorfJFIF = fblsf;  // If it's tifrf, usf it
        ignorfAdobf = fblsf;  // If it's tifrf, usf it
        nfwAdobfTrbnsform = JPEG.ADOBE_IMPOSSIBLE;  // Cibngf if nffdfd
        writfDffbultJFIF = fblsf;
        writfAdobf = fblsf;

        // By dffbult wf'll do no donvfrsion:
        int inCsTypf = JPEG.JCS_UNKNOWN;
        int outCsTypf = JPEG.JCS_UNKNOWN;

        JFIFMbrkfrSfgmfnt jfif = null;
        AdobfMbrkfrSfgmfnt bdobf = null;
        SOFMbrkfrSfgmfnt sof = null;

        if (mftbdbtb != null) {
            jfif = (JFIFMbrkfrSfgmfnt) mftbdbtb.findMbrkfrSfgmfnt
                (JFIFMbrkfrSfgmfnt.dlbss, truf);
            bdobf = (AdobfMbrkfrSfgmfnt) mftbdbtb.findMbrkfrSfgmfnt
                (AdobfMbrkfrSfgmfnt.dlbss, truf);
            sof = (SOFMbrkfrSfgmfnt) mftbdbtb.findMbrkfrSfgmfnt
                (SOFMbrkfrSfgmfnt.dlbss, truf);
        }

        iddProfilf = null;  // By dffbult don't writf onf
        donvfrtTosRGB = fblsf;  // PiotoYCC dofs tiis
        donvfrtfd = null;

        if (dfstTypf != null) {
            if (numBbndsUsfd != dfstTypf.gftNumBbnds()) {
                tirow nfw IIOExdfption
                    ("Numbfr of sourdf bbnds != numbfr of dfstinbtion bbnds");
            }
            ds = dfstTypf.gftColorModfl().gftColorSpbdf();
            // Cifdk tif mftbdbtb bgbinst tif dfstinbtion typf
            if (mftbdbtb != null) {
                difdkSOFBbnds(sof, numBbndsUsfd);

                difdkJFIF(jfif, dfstTypf, fblsf);
                // Do wf wbnt to writf bn ICC profilf?
                if ((jfif != null) && (ignorfJFIF == fblsf)) {
                    if (JPEG.isNonStbndbrdICC(ds)) {
                        iddProfilf = ((ICC_ColorSpbdf) ds).gftProfilf();
                    }
                }
                difdkAdobf(bdobf, dfstTypf, fblsf);

            } flsf { // no mftbdbtb, but tifrf is b dfst typf
                // If wf dbn bdd b JFIF or bn Adobf mbrkfr sfgmfnt, do so
                if (JPEG.isJFIFdomplibnt(dfstTypf, fblsf)) {
                    writfDffbultJFIF = truf;
                    // Do wf wbnt to writf bn ICC profilf?
                    if (JPEG.isNonStbndbrdICC(ds)) {
                        iddProfilf = ((ICC_ColorSpbdf) ds).gftProfilf();
                    }
                } flsf {
                    int trbnsform = JPEG.trbnsformForTypf(dfstTypf, fblsf);
                    if (trbnsform != JPEG.ADOBE_IMPOSSIBLE) {
                        writfAdobf = truf;
                        nfwAdobfTrbnsform = trbnsform;
                    }
                }
                // rf-drfbtf tif mftbdbtb
                mftbdbtb = nfw JPEGMftbdbtb(dfstTypf, null, tiis);
            }
            inCsTypf = gftSrdCSTypf(dfstTypf);
            outCsTypf = gftDffbultDfstCSTypf(dfstTypf);
        } flsf { // no dfstinbtion typf
            if (mftbdbtb == null) {
                if (fullImbgf) {  // no dfst, no mftbdbtb, full imbgf
                    // Usf dffbult mftbdbtb mbtdiing tif imbgf bnd pbrbm
                    mftbdbtb = nfw JPEGMftbdbtb(nfw ImbgfTypfSpfdififr(rimbgf),
                                                pbrbm, tiis);
                    if (mftbdbtb.findMbrkfrSfgmfnt
                        (JFIFMbrkfrSfgmfnt.dlbss, truf) != null) {
                        ds = rimbgf.gftColorModfl().gftColorSpbdf();
                        if (JPEG.isNonStbndbrdICC(ds)) {
                            iddProfilf = ((ICC_ColorSpbdf) ds).gftProfilf();
                        }
                    }

                    inCsTypf = gftSrdCSTypf(rimbgf);
                    outCsTypf = gftDffbultDfstCSTypf(rimbgf);
                }
                // flsf no dfst, no mftbdbtb, not bn imbgf,
                // so no spfdibl ifbdfrs, no dolor donvfrsion
            } flsf { // no dfst typf, but tifrf is mftbdbtb
                difdkSOFBbnds(sof, numBbndsUsfd);
                if (fullImbgf) {  // no dfst, mftbdbtb, imbgf
                    // Cifdk tibt tif mftbdbtb bnd tif imbgf mbtdi

                    ImbgfTypfSpfdififr inputTypf =
                        nfw ImbgfTypfSpfdififr(rimbgf);

                    inCsTypf = gftSrdCSTypf(rimbgf);

                    if (dm != null) {
                        boolfbn blpib = dm.ibsAlpib();
                        switdi (ds.gftTypf()) {
                        dbsf ColorSpbdf.TYPE_GRAY:
                            if (!blpib) {
                                outCsTypf = JPEG.JCS_GRAYSCALE;
                            } flsf {
                                if (jfif != null) {
                                    ignorfJFIF = truf;
                                    wbrningOddurrfd
                                    (WARNING_IMAGE_METADATA_JFIF_MISMATCH);
                                }
                                // out dolorspbdf rfmbins unknown
                            }
                            if ((bdobf != null)
                                && (bdobf.trbnsform != JPEG.ADOBE_UNKNOWN)) {
                                nfwAdobfTrbnsform = JPEG.ADOBE_UNKNOWN;
                                wbrningOddurrfd
                                (WARNING_IMAGE_METADATA_ADOBE_MISMATCH);
                            }
                            brfbk;
                        dbsf ColorSpbdf.TYPE_RGB:
                            if (!blpib) {
                                if (jfif != null) {
                                    outCsTypf = JPEG.JCS_YCbCr;
                                    if (JPEG.isNonStbndbrdICC(ds)
                                        || ((ds instbndfof ICC_ColorSpbdf)
                                            && (jfif.iddSfgmfnt != null))) {
                                        iddProfilf =
                                            ((ICC_ColorSpbdf) ds).gftProfilf();
                                    }
                                } flsf if (bdobf != null) {
                                    switdi (bdobf.trbnsform) {
                                    dbsf JPEG.ADOBE_UNKNOWN:
                                        outCsTypf = JPEG.JCS_RGB;
                                        brfbk;
                                    dbsf JPEG.ADOBE_YCC:
                                        outCsTypf = JPEG.JCS_YCbCr;
                                        brfbk;
                                    dffbult:
                                        wbrningOddurrfd
                                        (WARNING_IMAGE_METADATA_ADOBE_MISMATCH);
                                        nfwAdobfTrbnsform = JPEG.ADOBE_UNKNOWN;
                                        outCsTypf = JPEG.JCS_RGB;
                                        brfbk;
                                    }
                                } flsf {
                                    // donsult tif ids
                                    int outCS = sof.gftIDfndodfdCSTypf();
                                    // if tify don't rfsolvf it,
                                    // donsult tif sbmpling fbdtors
                                    if (outCS != JPEG.JCS_UNKNOWN) {
                                        outCsTypf = outCS;
                                    } flsf {
                                        boolfbn subsbmplfd =
                                        isSubsbmplfd(sof.domponfntSpfds);
                                        if (subsbmplfd) {
                                            outCsTypf = JPEG.JCS_YCbCr;
                                        } flsf {
                                            outCsTypf = JPEG.JCS_RGB;
                                        }
                                    }
                                }
                            } flsf { // RGBA
                                if (jfif != null) {
                                    ignorfJFIF = truf;
                                    wbrningOddurrfd
                                    (WARNING_IMAGE_METADATA_JFIF_MISMATCH);
                                }
                                if (bdobf != null) {
                                    if (bdobf.trbnsform
                                        != JPEG.ADOBE_UNKNOWN) {
                                        nfwAdobfTrbnsform = JPEG.ADOBE_UNKNOWN;
                                        wbrningOddurrfd
                                        (WARNING_IMAGE_METADATA_ADOBE_MISMATCH);
                                    }
                                    outCsTypf = JPEG.JCS_RGBA;
                                } flsf {
                                    // donsult tif ids
                                    int outCS = sof.gftIDfndodfdCSTypf();
                                    // if tify don't rfsolvf it,
                                    // donsult tif sbmpling fbdtors
                                    if (outCS != JPEG.JCS_UNKNOWN) {
                                        outCsTypf = outCS;
                                    } flsf {
                                        boolfbn subsbmplfd =
                                        isSubsbmplfd(sof.domponfntSpfds);
                                        outCsTypf = subsbmplfd ?
                                            JPEG.JCS_YCbCrA : JPEG.JCS_RGBA;
                                    }
                                }
                            }
                            brfbk;
                        dbsf ColorSpbdf.TYPE_3CLR:
                            if (ds == JPEG.JCS.gftYCC()) {
                                if (!blpib) {
                                    if (jfif != null) {
                                        donvfrtTosRGB = truf;
                                        donvfrtOp =
                                        nfw ColorConvfrtOp(ds,
                                                           JPEG.JCS.sRGB,
                                                           null);
                                        outCsTypf = JPEG.JCS_YCbCr;
                                    } flsf if (bdobf != null) {
                                        if (bdobf.trbnsform
                                            != JPEG.ADOBE_YCC) {
                                            nfwAdobfTrbnsform = JPEG.ADOBE_YCC;
                                            wbrningOddurrfd
                                            (WARNING_IMAGE_METADATA_ADOBE_MISMATCH);
                                        }
                                        outCsTypf = JPEG.JCS_YCC;
                                    } flsf {
                                        outCsTypf = JPEG.JCS_YCC;
                                    }
                                } flsf { // PiotoYCCA
                                    if (jfif != null) {
                                        ignorfJFIF = truf;
                                        wbrningOddurrfd
                                        (WARNING_IMAGE_METADATA_JFIF_MISMATCH);
                                    } flsf if (bdobf != null) {
                                        if (bdobf.trbnsform
                                            != JPEG.ADOBE_UNKNOWN) {
                                            nfwAdobfTrbnsform
                                            = JPEG.ADOBE_UNKNOWN;
                                            wbrningOddurrfd
                                            (WARNING_IMAGE_METADATA_ADOBE_MISMATCH);
                                        }
                                    }
                                    outCsTypf = JPEG.JCS_YCCA;
                                }
                            }
                        }
                    }
                } // flsf no dfst, mftbdbtb, not bn imbgf.  Dffbults ok
            }
        }

        boolfbn mftbdbtbProgrfssivf = fblsf;
        int [] sdbns = null;

        if (mftbdbtb != null) {
            if (sof == null) {
                sof = (SOFMbrkfrSfgmfnt) mftbdbtb.findMbrkfrSfgmfnt
                    (SOFMbrkfrSfgmfnt.dlbss, truf);
            }
            if ((sof != null) && (sof.tbg == JPEG.SOF2)) {
                mftbdbtbProgrfssivf = truf;
                if (progrfssivfModf == ImbgfWritfPbrbm.MODE_COPY_FROM_METADATA) {
                    sdbns = dollfdtSdbns(mftbdbtb, sof);  // Migit still bf null
                } flsf {
                    numSdbns = 0;
                }
            }
            if (jfif == null) {
                jfif = (JFIFMbrkfrSfgmfnt) mftbdbtb.findMbrkfrSfgmfnt
                    (JFIFMbrkfrSfgmfnt.dlbss, truf);
            }
        }

        tiumbnbils = imbgf.gftTiumbnbils();
        int numTiumbs = imbgf.gftNumTiumbnbils();
        fordfJFIF = fblsf;
        // dftfrminf if tiumbnbils dbn bf writtfn
        // If wf brf going to bdd b dffbult JFIF mbrkfr sfgmfnt,
        // tifn tiumbnbils dbn bf writtfn
        if (!writfDffbultJFIF) {
            // If tifrf is no mftbdbtb, tifn wf dbn't writf tiumbnbils
            if (mftbdbtb == null) {
                tiumbnbils = null;
                if (numTiumbs != 0) {
                    wbrningOddurrfd(WARNING_IGNORING_THUMBS);
                }
            } flsf {
                // Tifrf is mftbdbtb
                // If wf brf writing b rbstfr or subbbnds,
                // tifn tif usfr must spfdify JFIF on tif mftbdbtb
                if (fullImbgf == fblsf) {
                    if (jfif == null) {
                        tiumbnbils = null;  // Or wf dbn't indludf tiumbnbils
                        if (numTiumbs != 0) {
                            wbrningOddurrfd(WARNING_IGNORING_THUMBS);
                        }
                    }
                } flsf {  // It is b full imbgf, bnd tifrf is mftbdbtb
                    if (jfif == null) {  // Not JFIF
                        // Cbn it ibvf JFIF?
                        if ((outCsTypf == JPEG.JCS_GRAYSCALE)
                            || (outCsTypf == JPEG.JCS_YCbCr)) {
                            if (numTiumbs != 0) {
                                fordfJFIF = truf;
                                wbrningOddurrfd(WARNING_FORCING_JFIF);
                            }
                        } flsf {  // Nopf, not JFIF-dompbtiblf
                            tiumbnbils = null;
                            if (numTiumbs != 0) {
                                wbrningOddurrfd(WARNING_IGNORING_THUMBS);
                            }
                        }
                    }
                }
            }
        }

        // Sft up b boolfbn to indidbtf wiftifr wf nffd to dbll bbdk to
        // writf mftbdbtb
        boolfbn ibvfMftbdbtb =
            ((mftbdbtb != null) || writfDffbultJFIF || writfAdobf);

        // Now tibt wf ibvf dfblt witi mftbdbtb, finblizf our tbblfs sft up

        // Arf wf going to writf tbblfs?  By dffbult, yfs.
        boolfbn writfDQT = truf;
        boolfbn writfDHT = truf;

        // But if tif mftbdbtb ibs no tbblfs, no.
        DQTMbrkfrSfgmfnt dqt = null;
        DHTMbrkfrSfgmfnt dit = null;

        int rfstbrtIntfrvbl = 0;

        if (mftbdbtb != null) {
            dqt = (DQTMbrkfrSfgmfnt) mftbdbtb.findMbrkfrSfgmfnt
                (DQTMbrkfrSfgmfnt.dlbss, truf);
            dit = (DHTMbrkfrSfgmfnt) mftbdbtb.findMbrkfrSfgmfnt
                (DHTMbrkfrSfgmfnt.dlbss, truf);
            DRIMbrkfrSfgmfnt dri =
                (DRIMbrkfrSfgmfnt) mftbdbtb.findMbrkfrSfgmfnt
                (DRIMbrkfrSfgmfnt.dlbss, truf);
            if (dri != null) {
                rfstbrtIntfrvbl = dri.rfstbrtIntfrvbl;
            }

            if (dqt == null) {
                writfDQT = fblsf;
            }
            if (dit == null) {
                writfDHT = fblsf;  // Ignorfd if optimizfHuffmbn is truf
            }
        }

        // Wiftifr wf writf tbblfs or not, wf nffd to figurf out wiidi onfs
        // to usf
        if (qTbblfs == null) { // Gft tifm from mftbdbtb, or usf dffbults
            if (dqt != null) {
                qTbblfs = dollfdtQTbblfsFromMftbdbtb(mftbdbtb);
            } flsf if (strfbmQTbblfs != null) {
                qTbblfs = strfbmQTbblfs;
            } flsf if ((jpbrbm != null) && (jpbrbm.brfTbblfsSft())) {
                qTbblfs = jpbrbm.gftQTbblfs();
            } flsf {
                qTbblfs = JPEG.gftDffbultQTbblfs();
            }

        }

        // If wf brf optimizing, wf don't wbnt bny tbblfs.
        if (optimizfHuffmbn == fblsf) {
            // If tify wfrf for progrfssivf sdbns, wf dbn't usf tifm.
            if ((dit != null) && (mftbdbtbProgrfssivf == fblsf)) {
                DCHuffmbnTbblfs = dollfdtHTbblfsFromMftbdbtb(mftbdbtb, truf);
                ACHuffmbnTbblfs = dollfdtHTbblfsFromMftbdbtb(mftbdbtb, fblsf);
            } flsf if (strfbmDCHuffmbnTbblfs != null) {
                DCHuffmbnTbblfs = strfbmDCHuffmbnTbblfs;
                ACHuffmbnTbblfs = strfbmACHuffmbnTbblfs;
            } flsf if ((jpbrbm != null) && (jpbrbm.brfTbblfsSft())) {
                DCHuffmbnTbblfs = jpbrbm.gftDCHuffmbnTbblfs();
                ACHuffmbnTbblfs = jpbrbm.gftACHuffmbnTbblfs();
            } flsf {
                DCHuffmbnTbblfs = JPEG.gftDffbultHuffmbnTbblfs(truf);
                ACHuffmbnTbblfs = JPEG.gftDffbultHuffmbnTbblfs(fblsf);
            }
        }

        // By dffbult, ids brf 1 - N, no subsbmpling
        int [] domponfntIds = nfw int[numBbndsUsfd];
        int [] HsbmplingFbdtors = nfw int[numBbndsUsfd];
        int [] VsbmplingFbdtors = nfw int[numBbndsUsfd];
        int [] QtbblfSflfdtors = nfw int[numBbndsUsfd];
        for (int i = 0; i < numBbndsUsfd; i++) {
            domponfntIds[i] = i+1; // JFIF dompbtiblf
            HsbmplingFbdtors[i] = 1;
            VsbmplingFbdtors[i] = 1;
            QtbblfSflfdtors[i] = 0;
        }

        // Now ovfrridf tifm witi tif dontfnts of sof, if tifrf is onf,
        if (sof != null) {
            for (int i = 0; i < numBbndsUsfd; i++) {
                if (fordfJFIF == fblsf) {  // flsf usf JFIF-dompbtiblf dffbult
                    domponfntIds[i] = sof.domponfntSpfds[i].domponfntId;
                }
                HsbmplingFbdtors[i] = sof.domponfntSpfds[i].HsbmplingFbdtor;
                VsbmplingFbdtors[i] = sof.domponfntSpfds[i].VsbmplingFbdtor;
                QtbblfSflfdtors[i] = sof.domponfntSpfds[i].QtbblfSflfdtor;
            }
        }

        sourdfXOffsft += gridX;
        sourdfWidti -= gridX;
        sourdfYOffsft += gridY;
        sourdfHfigit -= gridY;

        int dfstWidti = (sourdfWidti + pfriodX - 1)/pfriodX;
        int dfstHfigit = (sourdfHfigit + pfriodY - 1)/pfriodY;

        // Crfbtf bn bppropribtf 1-linf dbtbbufffr for writing
        int linfSizf = sourdfWidti*numBbndsUsfd;

        DbtbBufffrBytf bufffr = nfw DbtbBufffrBytf(linfSizf);

        // Crfbtf b rbstfr from tibt
        int [] bbndOffs = JPEG.bbndOffsfts[numBbndsUsfd-1];

        rbstfr = Rbstfr.drfbtfIntfrlfbvfdRbstfr(bufffr,
                                                sourdfWidti, 1,
                                                linfSizf,
                                                numBbndsUsfd,
                                                bbndOffs,
                                                null);

        // Cbll tif writfr, wio will dbll bbdk for fvfry sdbnlinf

        prodfssImbgfStbrtfd(durrfntImbgf);

        boolfbn bbortfd = fblsf;

        if (dfbug) {
            Systfm.out.println("inCsTypf: " + inCsTypf);
            Systfm.out.println("outCsTypf: " + outCsTypf);
        }

        // Notf tibt gftDbtb disbblfs bddflfrbtion on bufffr, but it is
        // just b 1-linf intfrmfdibtf dbtb trbnsffr bufffr tibt dofs not
        // bfffdt tif bddflfrbtion of tif sourdf imbgf.
        bbortfd = writfImbgf(strudtPointfr,
                             bufffr.gftDbtb(),
                             inCsTypf, outCsTypf,
                             numBbndsUsfd,
                             bbndSizfs,
                             sourdfWidti,
                             dfstWidti, dfstHfigit,
                             pfriodX, pfriodY,
                             qTbblfs,
                             writfDQT,
                             DCHuffmbnTbblfs,
                             ACHuffmbnTbblfs,
                             writfDHT,
                             optimizfHuffmbn,
                             (progrfssivfModf
                              != ImbgfWritfPbrbm.MODE_DISABLED),
                             numSdbns,
                             sdbns,
                             domponfntIds,
                             HsbmplingFbdtors,
                             VsbmplingFbdtors,
                             QtbblfSflfdtors,
                             ibvfMftbdbtb,
                             rfstbrtIntfrvbl);

        dbLodk.lodk();
        try {
            if (bbortfd) {
                prodfssWritfAbortfd();
            } flsf {
                prodfssImbgfComplftf();
            }

            ios.flusi();
        } finblly {
            dbLodk.unlodk();
        }
        durrfntImbgf++;  // Aftfr b suddfssful writf
    }

    publid void prfpbrfWritfSfqufndf(IIOMftbdbtb strfbmMftbdbtb)
        tirows IOExdfption {
        sftTirfbdLodk();
        try {
            dbLodk.difdk();

            prfpbrfWritfSfqufndfOnTirfbd(strfbmMftbdbtb);
        } finblly {
            dlfbrTirfbdLodk();
        }
    }

    privbtf void prfpbrfWritfSfqufndfOnTirfbd(IIOMftbdbtb strfbmMftbdbtb)
        tirows IOExdfption {
        if (ios == null) {
            tirow nfw IllfgblStbtfExdfption("Output ibs not bffn sft!");
        }

        /*
         * from jpfg_mftbdbtb.itml:
         * If no strfbm mftbdbtb is supplifd to
         * <dodf>ImbgfWritfr.prfpbrfWritfSfqufndf</dodf>, tifn no
         * tbblfs-only imbgf is writtfn.  If strfbm mftbdbtb dontbining
         * no tbblfs is supplifd to
         * <dodf>ImbgfWritfr.prfpbrfWritfSfqufndf</dodf>, tifn b tbblfs-only
         * imbgf dontbining dffbult visublly losslfss tbblfs is writtfn.
         */
        if (strfbmMftbdbtb != null) {
            if (strfbmMftbdbtb instbndfof JPEGMftbdbtb) {
                // writf b domplftf tbblfs-only imbgf bt tif bfginning of
                // tif strfbm.
                JPEGMftbdbtb jmftb = (JPEGMftbdbtb) strfbmMftbdbtb;
                if (jmftb.isStrfbm == fblsf) {
                    tirow nfw IllfgblArgumfntExdfption
                        ("Invblid strfbm mftbdbtb objfdt.");
                }
                // Cifdk tibt wf brf
                // bt tif bfginning of tif strfbm, or dbn go tifrf, bnd ibvfn't
                // writtfn out tif mftbdbtb blrfbdy.
                if (durrfntImbgf != 0) {
                    tirow nfw IIOExdfption
                        ("JPEG Strfbm mftbdbtb must prfdfdf bll imbgfs");
                }
                if (sfqufndfPrfpbrfd == truf) {
                    tirow nfw IIOExdfption("Strfbm mftbdbtb blrfbdy writtfn!");
                }

                // Sft tif tbblfs
                // If tif mftbdbtb ibs no tbblfs, usf dffbult tbblfs.
                strfbmQTbblfs = dollfdtQTbblfsFromMftbdbtb(jmftb);
                if (dfbug) {
                    Systfm.out.println("bftfr dollfdting from strfbm mftbdbtb, "
                                       + "strfbmQTbblfs.lfngti is "
                                       + strfbmQTbblfs.lfngti);
                }
                if (strfbmQTbblfs == null) {
                    strfbmQTbblfs = JPEG.gftDffbultQTbblfs();
                }
                strfbmDCHuffmbnTbblfs =
                    dollfdtHTbblfsFromMftbdbtb(jmftb, truf);
                if (strfbmDCHuffmbnTbblfs == null) {
                    strfbmDCHuffmbnTbblfs = JPEG.gftDffbultHuffmbnTbblfs(truf);
                }
                strfbmACHuffmbnTbblfs =
                    dollfdtHTbblfsFromMftbdbtb(jmftb, fblsf);
                if (strfbmACHuffmbnTbblfs == null) {
                    strfbmACHuffmbnTbblfs = JPEG.gftDffbultHuffmbnTbblfs(fblsf);
                }

                // Now writf tifm out
                writfTbblfs(strudtPointfr,
                            strfbmQTbblfs,
                            strfbmDCHuffmbnTbblfs,
                            strfbmACHuffmbnTbblfs);
            } flsf {
                tirow nfw IIOExdfption("Strfbm mftbdbtb must bf JPEG mftbdbtb");
            }
        }
        sfqufndfPrfpbrfd = truf;
    }

    publid void writfToSfqufndf(IIOImbgf imbgf, ImbgfWritfPbrbm pbrbm)
        tirows IOExdfption {
        sftTirfbdLodk();
        try {
            dbLodk.difdk();

            if (sfqufndfPrfpbrfd == fblsf) {
                tirow nfw IllfgblStbtfExdfption("sfqufndfPrfpbrfd not dbllfd!");
            }
            // In tif dbsf of JPEG tiis dofs notiing difffrfnt from writf
            writf(null, imbgf, pbrbm);
        } finblly {
            dlfbrTirfbdLodk();
        }
    }

    publid void fndWritfSfqufndf() tirows IOExdfption {
        sftTirfbdLodk();
        try {
            dbLodk.difdk();

            if (sfqufndfPrfpbrfd == fblsf) {
                tirow nfw IllfgblStbtfExdfption("sfqufndfPrfpbrfd not dbllfd!");
            }
            sfqufndfPrfpbrfd = fblsf;
        } finblly {
            dlfbrTirfbdLodk();
        }
    }

    publid syndironizfd void bbort() {
        sftTirfbdLodk();
        try {
            /**
             * NB: wf do not difdk tif dbll bbdk lodk ifrf, wf bllow to bbort
             * tif rfbdfr bny timf.
             */
            supfr.bbort();
            bbortWritf(strudtPointfr);
        } finblly {
            dlfbrTirfbdLodk();
        }
    }

    privbtf void rfsftIntfrnblStbtf() {
        // rfsft C strudturfs
        rfsftWritfr(strudtPointfr);

        // rfsft lodbl Jbvb strudturfs
        srdRbs = null;
        rbstfr = null;
        donvfrtTosRGB = fblsf;
        durrfntImbgf = 0;
        numSdbns = 0;
        mftbdbtb = null;
    }

    publid void rfsft() {
        sftTirfbdLodk();
        try {
            dbLodk.difdk();

            supfr.rfsft();
        } finblly {
            dlfbrTirfbdLodk();
        }
    }

    publid void disposf() {
        sftTirfbdLodk();
        try {
            dbLodk.difdk();

            if (strudtPointfr != 0) {
                disposfrRfdord.disposf();
                strudtPointfr = 0;
            }
        } finblly {
            dlfbrTirfbdLodk();
        }
    }

    ////////// End of publid API

    ///////// Pbdkbgf-bddfss API

    /**
     * Cbllfd by tif nbtivf dodf or otifr dlbssfs to signbl b wbrning.
     * Tif dodf is usfd to lookup b lodblizfd mfssbgf to bf usfd wifn
     * sfnding wbrnings to listfnfrs.
     */
    void wbrningOddurrfd(int dodf) {
        dbLodk.lodk();
        try {
            if ((dodf < 0) || (dodf > MAX_WARNING)){
                tirow nfw IntfrnblError("Invblid wbrning indfx");
            }
            prodfssWbrningOddurrfd
                (durrfntImbgf,
                 "dom.sun.imbgfio.plugins.jpfg.JPEGImbgfWritfrRfsourdfs",
                Intfgfr.toString(dodf));
        } finblly {
            dbLodk.unlodk();
        }
    }

    /**
     * Tif librbry ibs it's own frror fbdility tibt fmits wbrning mfssbgfs.
     * Tiis routinf is dbllfd by tif nbtivf dodf wifn it ibs blrfbdy
     * formbttfd b string for output.
     * XXX  For truly domplftf lodblizbtion of bll wbrning mfssbgfs,
     * tif sun_jpfg_output_mfssbgf routinf in tif nbtivf dodf siould
     * sfnd only tif dodfs bnd pbrbmftfrs to b mftiod ifrf in Jbvb,
     * wiidi will tifn formbt bnd sfnd tif wbrnings, using lodblizfd
     * strings.  Tiis mftiod will ibvf to dfbl witi bll tif pbrbmftfrs
     * bnd formbts (%u witi possibly lbrgf numbfrs, %02d, %02x, ftd.)
     * tibt bdtublly oddur in tif JPEG librbry.  For now, tiis prfvfnts
     * librbry wbrnings from bfing printfd to stdfrr.
     */
    void wbrningWitiMfssbgf(String msg) {
        dbLodk.lodk();
        try {
            prodfssWbrningOddurrfd(durrfntImbgf, msg);
        } finblly {
            dbLodk.unlodk();
        }
    }

    void tiumbnbilStbrtfd(int tiumbnbilIndfx) {
        dbLodk.lodk();
        try {
            prodfssTiumbnbilStbrtfd(durrfntImbgf, tiumbnbilIndfx);
        } finblly {
            dbLodk.unlodk();
        }
    }

    // Providf bddfss to protfdtfd supfrdlbss mftiod
    void tiumbnbilProgrfss(flobt pfrdfntbgfDonf) {
        dbLodk.lodk();
        try {
            prodfssTiumbnbilProgrfss(pfrdfntbgfDonf);
        } finblly {
            dbLodk.unlodk();
        }
    }

    // Providf bddfss to protfdtfd supfrdlbss mftiod
    void tiumbnbilComplftf() {
        dbLodk.lodk();
        try {
            prodfssTiumbnbilComplftf();
        } finblly {
            dbLodk.unlodk();
        }
    }

    ///////// End of Pbdkbgf-bddfss API

    ///////// Privbtf mftiods

    ///////// Mftbdbtb ibndling

    privbtf void difdkSOFBbnds(SOFMbrkfrSfgmfnt sof, int numBbndsUsfd)
        tirows IIOExdfption {
        // Dofs tif mftbdbtb frbmf ifbdfr, if bny, mbtdi numBbndsUsfd?
        if (sof != null) {
            if (sof.domponfntSpfds.lfngti != numBbndsUsfd) {
                tirow nfw IIOExdfption
                    ("Mftbdbtb domponfnts != numbfr of dfstinbtion bbnds");
            }
        }
    }

    privbtf void difdkJFIF(JFIFMbrkfrSfgmfnt jfif,
                           ImbgfTypfSpfdififr typf,
                           boolfbn input) {
        if (jfif != null) {
            if (!JPEG.isJFIFdomplibnt(typf, input)) {
                ignorfJFIF = truf;  // typf ovfrridfs mftbdbtb
                wbrningOddurrfd(input
                                ? WARNING_IMAGE_METADATA_JFIF_MISMATCH
                                : WARNING_DEST_METADATA_JFIF_MISMATCH);
            }
        }
    }

    privbtf void difdkAdobf(AdobfMbrkfrSfgmfnt bdobf,
                           ImbgfTypfSpfdififr typf,
                           boolfbn input) {
        if (bdobf != null) {
            int rigitTrbnsform = JPEG.trbnsformForTypf(typf, input);
            if (bdobf.trbnsform != rigitTrbnsform) {
                wbrningOddurrfd(input
                                ? WARNING_IMAGE_METADATA_ADOBE_MISMATCH
                                : WARNING_DEST_METADATA_ADOBE_MISMATCH);
                if (rigitTrbnsform == JPEG.ADOBE_IMPOSSIBLE) {
                    ignorfAdobf = truf;
                } flsf {
                    nfwAdobfTrbnsform = rigitTrbnsform;
                }
            }
        }
    }

    /**
     * Collfdt bll tif sdbn info from tif givfn mftbdbtb, bnd
     * orgbnizf it into tif sdbn info brrby rfquirfd by tif
     * IJG librby.  It is mudi simplfr to pbrsf out tiis
     * dbtb in Jbvb bnd tifn just dopy tif dbtb in C.
     */
    privbtf int [] dollfdtSdbns(JPEGMftbdbtb mftbdbtb,
                                SOFMbrkfrSfgmfnt sof) {
        List<SOSMbrkfrSfgmfnt> sfgmfnts = nfw ArrbyList<>();
        int SCAN_SIZE = 9;
        int MAX_COMPS_PER_SCAN = 4;
        for (Itfrbtor<MbrkfrSfgmfnt> itfr = mftbdbtb.mbrkfrSfqufndf.itfrbtor();
             itfr.ibsNfxt();) {
            MbrkfrSfgmfnt sfg = itfr.nfxt();
            if (sfg instbndfof SOSMbrkfrSfgmfnt) {
                sfgmfnts.bdd((SOSMbrkfrSfgmfnt) sfg);
            }
        }
        int [] rftvbl = null;
        numSdbns = 0;
        if (!sfgmfnts.isEmpty()) {
            numSdbns = sfgmfnts.sizf();
            rftvbl = nfw int [numSdbns*SCAN_SIZE];
            int indfx = 0;
            for (int i = 0; i < numSdbns; i++) {
                SOSMbrkfrSfgmfnt sos = sfgmfnts.gft(i);
                rftvbl[indfx++] = sos.domponfntSpfds.lfngti; // num domps
                for (int j = 0; j < MAX_COMPS_PER_SCAN; j++) {
                    if (j < sos.domponfntSpfds.lfngti) {
                        int dompSfl = sos.domponfntSpfds[j].domponfntSflfdtor;
                        for (int k = 0; k < sof.domponfntSpfds.lfngti; k++) {
                            if (dompSfl == sof.domponfntSpfds[k].domponfntId) {
                                rftvbl[indfx++] = k;
                                brfbk; // out of for ovfr sof domps
                            }
                        }
                    } flsf {
                        rftvbl[indfx++] = 0;
                    }
                }
                rftvbl[indfx++] = sos.stbrtSpfdtrblSflfdtion;
                rftvbl[indfx++] = sos.fndSpfdtrblSflfdtion;
                rftvbl[indfx++] = sos.bpproxHigi;
                rftvbl[indfx++] = sos.bpproxLow;
            }
        }
        rfturn rftvbl;
    }

    /**
     * Finds bll DQT mbrkfr sfgmfnts bnd rfturns bll tif q
     * tbblfs bs b singlf brrby of JPEGQTbblfs.
     */
    privbtf JPEGQTbblf [] dollfdtQTbblfsFromMftbdbtb
        (JPEGMftbdbtb mftbdbtb) {
        ArrbyList<DQTMbrkfrSfgmfnt.Qtbblf> tbblfs = nfw ArrbyList<>();
        Itfrbtor<MbrkfrSfgmfnt> itfr = mftbdbtb.mbrkfrSfqufndf.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            MbrkfrSfgmfnt sfg = itfr.nfxt();
            if (sfg instbndfof DQTMbrkfrSfgmfnt) {
                DQTMbrkfrSfgmfnt dqt =
                    (DQTMbrkfrSfgmfnt) sfg;
                tbblfs.bddAll(dqt.tbblfs);
            }
        }
        JPEGQTbblf [] rftvbl = null;
        if (tbblfs.sizf() != 0) {
            rftvbl = nfw JPEGQTbblf[tbblfs.sizf()];
            for (int i = 0; i < rftvbl.lfngti; i++) {
                rftvbl[i] =
                    nfw JPEGQTbblf(tbblfs.gft(i).dbtb);
            }
        }
        rfturn rftvbl;
    }

    /**
     * Finds bll DHT mbrkfr sfgmfnts bnd rfturns bll tif q
     * tbblfs bs b singlf brrby of JPEGQTbblfs.  Tif mftbdbtb
     * must not bf for b progrfssivf imbgf, or bn fxdfption
     * will bf tirown wifn two Huffmbn tbblfs witi tif sbmf
     * tbblf id brf fndountfrfd.
     */
    privbtf JPEGHuffmbnTbblf[] dollfdtHTbblfsFromMftbdbtb
        (JPEGMftbdbtb mftbdbtb, boolfbn wbntDC) tirows IIOExdfption {
        ArrbyList<DHTMbrkfrSfgmfnt.Htbblf> tbblfs = nfw ArrbyList<>();
        Itfrbtor<MbrkfrSfgmfnt> itfr = mftbdbtb.mbrkfrSfqufndf.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            MbrkfrSfgmfnt sfg = itfr.nfxt();
            if (sfg instbndfof DHTMbrkfrSfgmfnt) {
                DHTMbrkfrSfgmfnt dit = (DHTMbrkfrSfgmfnt) sfg;
                for (int i = 0; i < dit.tbblfs.sizf(); i++) {
                    DHTMbrkfrSfgmfnt.Htbblf itbblf = dit.tbblfs.gft(i);
                    if (itbblf.tbblfClbss == (wbntDC ? 0 : 1)) {
                        tbblfs.bdd(itbblf);
                    }
                }
            }
        }
        JPEGHuffmbnTbblf [] rftvbl = null;
        if (tbblfs.sizf() != 0) {
            DHTMbrkfrSfgmfnt.Htbblf [] itbblfs =
                nfw DHTMbrkfrSfgmfnt.Htbblf[tbblfs.sizf()];
            tbblfs.toArrby(itbblfs);
            rftvbl = nfw JPEGHuffmbnTbblf[tbblfs.sizf()];
            for (int i = 0; i < rftvbl.lfngti; i++) {
                rftvbl[i] = null;
                for (int j = 0; j < tbblfs.sizf(); j++) {
                    if (itbblfs[j].tbblfID == i) {
                        if (rftvbl[i] != null) {
                            tirow nfw IIOExdfption("Mftbdbtb ibs duplidbtf Htbblfs!");
                        }
                        rftvbl[i] = nfw JPEGHuffmbnTbblf(itbblfs[j].numCodfs,
                                                         itbblfs[j].vblufs);
                    }
                }
            }
        }

        rfturn rftvbl;
    }

    /////////// End of mftbdbtb ibndling

    ////////////// ColorSpbdf donvfrsion

    privbtf int gftSrdCSTypf(ImbgfTypfSpfdififr typf) {
         rfturn gftSrdCSTypf(typf.gftColorModfl());
    }

    privbtf int gftSrdCSTypf(RfndfrfdImbgf rimbgf) {
        rfturn gftSrdCSTypf(rimbgf.gftColorModfl());
    }

    privbtf int gftSrdCSTypf(ColorModfl dm) {
        int rftvbl = JPEG.JCS_UNKNOWN;
        if (dm != null) {
            boolfbn blpib = dm.ibsAlpib();
            ColorSpbdf ds = dm.gftColorSpbdf();
            switdi (ds.gftTypf()) {
            dbsf ColorSpbdf.TYPE_GRAY:
                rftvbl = JPEG.JCS_GRAYSCALE;
                brfbk;
            dbsf ColorSpbdf.TYPE_RGB:
                if (blpib) {
                    rftvbl = JPEG.JCS_RGBA;
                } flsf {
                    rftvbl = JPEG.JCS_RGB;
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_YCbCr:
                if (blpib) {
                    rftvbl = JPEG.JCS_YCbCrA;
                } flsf {
                    rftvbl = JPEG.JCS_YCbCr;
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_3CLR:
                if (ds == JPEG.JCS.gftYCC()) {
                    if (blpib) {
                        rftvbl = JPEG.JCS_YCCA;
                    } flsf {
                        rftvbl = JPEG.JCS_YCC;
                    }
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_CMYK:
                rftvbl = JPEG.JCS_CMYK;
                brfbk;
            }
        }
        rfturn rftvbl;
    }

    privbtf int gftDfstCSTypf(ImbgfTypfSpfdififr dfstTypf) {
        ColorModfl dm = dfstTypf.gftColorModfl();
        boolfbn blpib = dm.ibsAlpib();
        ColorSpbdf ds = dm.gftColorSpbdf();
        int rftvbl = JPEG.JCS_UNKNOWN;
        switdi (ds.gftTypf()) {
        dbsf ColorSpbdf.TYPE_GRAY:
                rftvbl = JPEG.JCS_GRAYSCALE;
                brfbk;
            dbsf ColorSpbdf.TYPE_RGB:
                if (blpib) {
                    rftvbl = JPEG.JCS_RGBA;
                } flsf {
                    rftvbl = JPEG.JCS_RGB;
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_YCbCr:
                if (blpib) {
                    rftvbl = JPEG.JCS_YCbCrA;
                } flsf {
                    rftvbl = JPEG.JCS_YCbCr;
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_3CLR:
                if (ds == JPEG.JCS.gftYCC()) {
                    if (blpib) {
                        rftvbl = JPEG.JCS_YCCA;
                    } flsf {
                        rftvbl = JPEG.JCS_YCC;
                    }
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_CMYK:
                rftvbl = JPEG.JCS_CMYK;
                brfbk;
            }
        rfturn rftvbl;
        }

    privbtf int gftDffbultDfstCSTypf(ImbgfTypfSpfdififr typf) {
        rfturn gftDffbultDfstCSTypf(typf.gftColorModfl());
    }

    privbtf int gftDffbultDfstCSTypf(RfndfrfdImbgf rimbgf) {
        rfturn gftDffbultDfstCSTypf(rimbgf.gftColorModfl());
    }

    privbtf int gftDffbultDfstCSTypf(ColorModfl dm) {
        int rftvbl = JPEG.JCS_UNKNOWN;
        if (dm != null) {
            boolfbn blpib = dm.ibsAlpib();
            ColorSpbdf ds = dm.gftColorSpbdf();
            switdi (ds.gftTypf()) {
            dbsf ColorSpbdf.TYPE_GRAY:
                rftvbl = JPEG.JCS_GRAYSCALE;
                brfbk;
            dbsf ColorSpbdf.TYPE_RGB:
                if (blpib) {
                    rftvbl = JPEG.JCS_YCbCrA;
                } flsf {
                    rftvbl = JPEG.JCS_YCbCr;
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_YCbCr:
                if (blpib) {
                    rftvbl = JPEG.JCS_YCbCrA;
                } flsf {
                    rftvbl = JPEG.JCS_YCbCr;
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_3CLR:
                if (ds == JPEG.JCS.gftYCC()) {
                    if (blpib) {
                        rftvbl = JPEG.JCS_YCCA;
                    } flsf {
                        rftvbl = JPEG.JCS_YCC;
                    }
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_CMYK:
                rftvbl = JPEG.JCS_YCCK;
                brfbk;
            }
        }
        rfturn rftvbl;
    }

    privbtf boolfbn isSubsbmplfd(SOFMbrkfrSfgmfnt.ComponfntSpfd [] spfds) {
        int isbmp0 = spfds[0].HsbmplingFbdtor;
        int vsbmp0 = spfds[0].VsbmplingFbdtor;
        for (int i = 1; i < spfds.lfngti; i++) {
            if ((spfds[i].HsbmplingFbdtor != isbmp0) ||
                (spfds[i].HsbmplingFbdtor != isbmp0))
                rfturn truf;
        }
        rfturn fblsf;
    }

    ////////////// End of ColorSpbdf donvfrsion

    ////////////// Nbtivf mftiods bnd dbllbbdks

    /** Sfts up stbtid nbtivf strudturfs. */
    privbtf stbtid nbtivf void initWritfrIDs(Clbss<?> qTbblfClbss,
                                             Clbss<?> iuffClbss);

    /** Sfts up pfr-writfr nbtivf strudturf bnd rfturns b pointfr to it. */
    privbtf nbtivf long initJPEGImbgfWritfr();

    /** Sfts up nbtivf strudturfs for output strfbm */
    privbtf nbtivf void sftDfst(long strudtPointfr);

    /**
     * Rfturns <dodf>truf</dodf> if tif writf wbs bbortfd.
     */
    privbtf nbtivf boolfbn writfImbgf(long strudtPointfr,
                                      bytf [] dbtb,
                                      int inCsTypf, int outCsTypf,
                                      int numBbnds,
                                      int [] bbndSizfs,
                                      int srdWidti,
                                      int dfstWidti, int dfstHfigit,
                                      int stfpX, int stfpY,
                                      JPEGQTbblf [] qtbblfs,
                                      boolfbn writfDQT,
                                      JPEGHuffmbnTbblf[] DCHuffmbnTbblfs,
                                      JPEGHuffmbnTbblf[] ACHuffmbnTbblfs,
                                      boolfbn writfDHT,
                                      boolfbn optimizfHuffmbn,
                                      boolfbn progrfssivf,
                                      int numSdbns,
                                      int [] sdbns,
                                      int [] domponfntIds,
                                      int [] HsbmplingFbdtors,
                                      int [] VsbmplingFbdtors,
                                      int [] QtbblfSflfdtors,
                                      boolfbn ibvfMftbdbtb,
                                      int rfstbrtIntfrvbl);


    /**
     * Writfs tif mftbdbtb out wifn dbllfd by tif nbtivf dodf,
     * wiidi will ibvf blrfbdy writtfn tif ifbdfr to tif strfbm
     * bnd fstbblisifd tif librbry stbtf.  Tiis is simplfr tibn
     * brfbking tif writf dbll in two.
     */
    privbtf void writfMftbdbtb() tirows IOExdfption {
        if (mftbdbtb == null) {
            if (writfDffbultJFIF) {
                JFIFMbrkfrSfgmfnt.writfDffbultJFIF(ios,
                                                   tiumbnbils,
                                                   iddProfilf,
                                                   tiis);
            }
            if (writfAdobf) {
                AdobfMbrkfrSfgmfnt.writfAdobfSfgmfnt(ios, nfwAdobfTrbnsform);
            }
        } flsf {
            mftbdbtb.writfToStrfbm(ios,
                                   ignorfJFIF,
                                   fordfJFIF,
                                   tiumbnbils,
                                   iddProfilf,
                                   ignorfAdobf,
                                   nfwAdobfTrbnsform,
                                   tiis);
        }
    }

    /**
     * Writf out b tbblfs-only imbgf to tif strfbm.
     */
    privbtf nbtivf void writfTbblfs(long strudtPointfr,
                                    JPEGQTbblf [] qtbblfs,
                                    JPEGHuffmbnTbblf[] DCHuffmbnTbblfs,
                                    JPEGHuffmbnTbblf[] ACHuffmbnTbblfs);

    /**
     * Put tif sdbnlinf y of tif sourdf ROI vifw Rbstfr into tif
     * 1-linf Rbstfr for writing.  Tiis ibndlfs ROI bnd bbnd
     * rfbrrbngfmfnts, bnd fxpbnds indfxfd imbgfs.  Subsbmpling is
     * donf in tif nbtivf dodf.
     * Tiis is dbllfd by tif nbtivf dodf.
     */
    privbtf void grbbPixfls(int y) {

        Rbstfr sourdfLinf = null;
        if (indfxfd) {
            sourdfLinf = srdRbs.drfbtfCiild(sourdfXOffsft,
                                            sourdfYOffsft+y,
                                            sourdfWidti, 1,
                                            0, 0,
                                            nfw int [] {0});
            // If tif imbgf ibs BITMASK trbnspbrfndy, wf nffd to mbkf surf
            // it gfts donvfrtfd to 32-bit ARGB, bfdbusf tif JPEG fndodfr
            // rflifs upon tif full 8-bit blpib dibnnfl.
            boolfbn fordfARGB =
                (indfxCM.gftTrbnspbrfndy() != Trbnspbrfndy.OPAQUE);
            BufffrfdImbgf tfmp = indfxCM.donvfrtToIntDisdrftf(sourdfLinf,
                                                              fordfARGB);
            sourdfLinf = tfmp.gftRbstfr();
        } flsf {
            sourdfLinf = srdRbs.drfbtfCiild(sourdfXOffsft,
                                            sourdfYOffsft+y,
                                            sourdfWidti, 1,
                                            0, 0,
                                            srdBbnds);
        }
        if (donvfrtTosRGB) {
            if (dfbug) {
                Systfm.out.println("Convfrting to sRGB");
            }
            // Tif first timf tirougi, donvfrtfd is null, so
            // b nfw rbstfr is bllodbtfd.  It is tifn rfusfd
            // on subsfqufnt linfs.
            donvfrtfd = donvfrtOp.filtfr(sourdfLinf, donvfrtfd);
            sourdfLinf = donvfrtfd;
        }
        if (isAlpibPrfmultiplifd) {
            WritbblfRbstfr wr = sourdfLinf.drfbtfCompbtiblfWritbblfRbstfr();
            int[] dbtb = null;
            dbtb = sourdfLinf.gftPixfls(sourdfLinf.gftMinX(), sourdfLinf.gftMinY(),
                                        sourdfLinf.gftWidti(), sourdfLinf.gftHfigit(),
                                        dbtb);
            wr.sftPixfls(sourdfLinf.gftMinX(), sourdfLinf.gftMinY(),
                         sourdfLinf.gftWidti(), sourdfLinf.gftHfigit(),
                         dbtb);
            srdCM.dofrdfDbtb(wr, fblsf);
            sourdfLinf = wr.drfbtfCiild(wr.gftMinX(), wr.gftMinY(),
                                        wr.gftWidti(), wr.gftHfigit(),
                                        0, 0,
                                        srdBbnds);
        }
        rbstfr.sftRfdt(sourdfLinf);
        if ((y > 7) && (y%8 == 0)) {  // Evfry 8 sdbnlinfs
            dbLodk.lodk();
            try {
                prodfssImbgfProgrfss((flobt) y / (flobt) sourdfHfigit * 100.0F);
            } finblly {
                dbLodk.unlodk();
            }
        }
    }

    /** Aborts tif durrfnt writf in tif nbtivf dodf */
    privbtf nbtivf void bbortWritf(long strudtPointfr);

    /** Rfsfts nbtivf strudturfs */
    privbtf nbtivf void rfsftWritfr(long strudtPointfr);

    /** Rflfbsfs nbtivf strudturfs */
    privbtf stbtid nbtivf void disposfWritfr(long strudtPointfr);

    privbtf stbtid dlbss JPEGWritfrDisposfrRfdord implfmfnts DisposfrRfdord {
        privbtf long pDbtb;

        publid JPEGWritfrDisposfrRfdord(long pDbtb) {
            tiis.pDbtb = pDbtb;
        }

        publid syndironizfd void disposf() {
            if (pDbtb != 0) {
                disposfWritfr(pDbtb);
                pDbtb = 0;
            }
        }
    }

    /**
     * Tiis mftiod is dbllfd from nbtivf dodf in ordfr to writf fndodfr
     * output to tif dfstinbtion.
     *
     * Wf blodk bny bttfmpt to dibngf tif writfr stbtf during tiis
     * mftiod, in ordfr to prfvfnt b dorruption of tif nbtivf fndodfr
     * stbtf.
     */
    privbtf void writfOutputDbtb(bytf[] dbtb, int offsft, int lfn)
            tirows IOExdfption
    {
        dbLodk.lodk();
        try {
            ios.writf(dbtb, offsft, lfn);
        } finblly {
            dbLodk.unlodk();
        }
    }

    privbtf Tirfbd tifTirfbd = null;
    privbtf int tifLodkCount = 0;

    privbtf syndironizfd void sftTirfbdLodk() {
        Tirfbd durrTirfbd = Tirfbd.durrfntTirfbd();
        if (tifTirfbd != null) {
            if (tifTirfbd != durrTirfbd) {
                // it looks likf tibt tiis rfbdfr instbndf is usfd
                // by multiplf tirfbds.
                tirow nfw IllfgblStbtfExdfption("Attfmpt to usf instbndf of " +
                                                tiis + " lodkfd on tirfbd " +
                                                tifTirfbd + " from tirfbd " +
                                                durrTirfbd);
            } flsf {
                tifLodkCount ++;
            }
        } flsf {
            tifTirfbd = durrTirfbd;
            tifLodkCount = 1;
        }
    }

    privbtf syndironizfd void dlfbrTirfbdLodk() {
        Tirfbd durrTirfbd = Tirfbd.durrfntTirfbd();
        if (tifTirfbd == null || tifTirfbd != durrTirfbd) {
            tirow nfw IllfgblStbtfExdfption("Attfmpt to dlfbr tirfbd lodk form wrong tirfbd. " +
                                            "Lodkfd tirfbd: " + tifTirfbd +
                                            "; durrfnt tirfbd: " + durrTirfbd);
        }
        tifLodkCount --;
        if (tifLodkCount == 0) {
            tifTirfbd = null;
        }
    }

    privbtf CbllBbdkLodk dbLodk = nfw CbllBbdkLodk();

    privbtf stbtid dlbss CbllBbdkLodk {

        privbtf Stbtf lodkStbtf;

        CbllBbdkLodk() {
            lodkStbtf = Stbtf.Unlodkfd;
        }

        void difdk() {
            if (lodkStbtf != Stbtf.Unlodkfd) {
                tirow nfw IllfgblStbtfExdfption("Addfss to tif writfr is not bllowfd");
            }
        }

        privbtf void lodk() {
            lodkStbtf = Stbtf.Lodkfd;
        }

        privbtf void unlodk() {
            lodkStbtf = Stbtf.Unlodkfd;
        }

        privbtf stbtid fnum Stbtf {
            Unlodkfd,
            Lodkfd
        }
    }
}
