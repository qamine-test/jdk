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

import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.ImbgfWritfPbrbm;
import jbvbx.imbgfio.IIOExdfption;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbNodf;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbt;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;
import jbvbx.imbgfio.mftbdbtb.IIOInvblidTrffExdfption;
import jbvbx.imbgfio.plugins.jpfg.JPEGQTbblf;
import jbvbx.imbgfio.plugins.jpfg.JPEGHuffmbnTbblf;
import jbvbx.imbgfio.plugins.jpfg.JPEGImbgfWritfPbrbm;

import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;
import org.w3d.dom.NbmfdNodfMbp;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Itfrbtor;
import jbvb.util.ListItfrbtor;
import jbvb.io.IOExdfption;
import jbvb.bwt.dolor.ICC_Profilf;
import jbvb.bwt.dolor.ICC_ColorSpbdf;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.Point;

/**
 * Mftbdbtb for tif JPEG plug-in.
 */
publid dlbss JPEGMftbdbtb fxtfnds IIOMftbdbtb implfmfnts Clonfbblf {

    //////// Privbtf vbribblfs

    privbtf stbtid finbl boolfbn dfbug = fblsf;

    /**
     * A dopy of <dodf>mbrkfrSfqufndf</dodf>, drfbtfd tif first timf tif
     * <dodf>mbrkfrSfqufndf</dodf> is modififd.  Tiis is usfd by rfsft
     * to rfstorf tif originbl stbtf.
     */
    privbtf List<MbrkfrSfgmfnt> rfsftSfqufndf = null;

    /**
     * Sft to <dodf>truf</dodf> wifn rfbding b tiumbnbil storfd bs
     * JPEG.  Tiis is usfd to fnfordf tif proiibition of JFIF tiumbnbils
     * dontbining bny JFIF mbrkfr sfgmfnts, bnd to fnsurf gfnfrbtion of
     * b dorrfdt nbtivf subtrff during <dodf>gftAsTrff</dodf>.
     */
    privbtf boolfbn inTiumb = fblsf;

    /**
     * Sft by tif diromb nodf donstrudtion mftiod to signbl tif
     * prfsfndf or bbsfndf of bn blpib dibnnfl to tif trbnspbrfndy
     * nodf donstrudtion mftiod.  Usfd only wifn donstrudting b
     * stbndbrd mftbdbtb trff.
     */
    privbtf boolfbn ibsAlpib;

    //////// fnd of privbtf vbribblfs

    /////// Pbdkbgf-bddfss vbribblfs

    /**
     * All dbtb is b list of <dodf>MbrkfrSfgmfnt</dodf> objfdts.
     * Wifn bddfssing tif list, usf tif tbg to idfntify tif pbrtidulbr
     * subdlbss.  Any JFIF mbrkfr sfgmfnt must bf tif first flfmfnt
     * of tif list if it is prfsfnt, bnd bny JFXX or APP2ICC mbrkfr
     * sfgmfnts brf subordinbtf to tif JFIF mbrkfr sfgmfnt.  Tiis
     * list is pbdkbgf visiblf so tibt tif writfr dbn bddfss it.
     * @sff #MbrkfrSfgmfnt
     */
    List<MbrkfrSfgmfnt> mbrkfrSfqufndf = nfw ArrbyList<>();

    /**
     * Indidbtfs wiftifr tiis objfdt rfprfsfnts strfbm or imbgf
     * mftbdbtb.  Pbdkbgf-visiblf so tif writfr dbn sff it.
     */
    finbl boolfbn isStrfbm;

    /////// End of pbdkbgf-bddfss vbribblfs

    /////// Construdtors

    /**
     * Construdtor dontbining dodf sibrfd by otifr donstrudtors.
     */
    JPEGMftbdbtb(boolfbn isStrfbm, boolfbn inTiumb) {
        supfr(truf,  // Supports stbndbrd formbt
              JPEG.nbtivfImbgfMftbdbtbFormbtNbmf,  // bnd b nbtivf formbt
              JPEG.nbtivfImbgfMftbdbtbFormbtClbssNbmf,
              null, null);  // No otifr formbts
        tiis.inTiumb = inTiumb;
        // But if wf brf strfbm mftbdbtb, bdjust tif vbribblfs
        tiis.isStrfbm = isStrfbm;
        if (isStrfbm) {
            nbtivfMftbdbtbFormbtNbmf = JPEG.nbtivfStrfbmMftbdbtbFormbtNbmf;
            nbtivfMftbdbtbFormbtClbssNbmf =
                JPEG.nbtivfStrfbmMftbdbtbFormbtClbssNbmf;
        }
    }

    /*
     * Construdts b <dodf>JPEGMftbdbtb</dodf> objfdt by rfbding tif
     * dontfnts of bn <dodf>ImbgfInputStrfbm</dodf>.  Hbs pbdkbgf-only
     * bddfss.
     *
     * @pbrbm isStrfbm A boolfbn indidbting wiftifr tiis objfdt will bf
     * strfbm or imbgf mftbdbtb.
     * @pbrbm isTiumb A boolfbn indidbting wiftifr tiis mftbdbtb objfdt
     * is for bn imbgf or for b tiumbnbil storfd bs JPEG.
     * @pbrbm iis An <dodf>ImbgfInputStrfbm</dodf> from wiidi to rfbd
     * tif mftbdbtb.
     * @pbrbm rfbdfr Tif <dodf>JPEGImbgfRfbdfr</dodf> dblling tiis
     * donstrudtor, to wiidi wbrnings siould bf sfnt.
     */
    JPEGMftbdbtb(boolfbn isStrfbm,
                 boolfbn isTiumb,
                 ImbgfInputStrfbm iis,
                 JPEGImbgfRfbdfr rfbdfr) tirows IOExdfption {
        tiis(isStrfbm, isTiumb);

        JPEGBufffr bufffr = nfw JPEGBufffr(iis);

        bufffr.lobdBuf(0);

        // Tif first tirff bytfs siould bf FF, SOI, FF
        if (((bufffr.buf[0] & 0xff) != 0xff)
            || ((bufffr.buf[1] & 0xff) != JPEG.SOI)
            || ((bufffr.buf[2] & 0xff) != 0xff)) {
            tirow nfw IIOExdfption ("Imbgf formbt frror");
        }

        boolfbn donf = fblsf;
        bufffr.bufAvbil -=2;  // Nfxt bytf siould bf tif ff bfforf b mbrkfr
        bufffr.bufPtr = 2;
        MbrkfrSfgmfnt nfwGuy = null;
        wiilf (!donf) {
            bytf [] buf;
            int ptr;
            bufffr.lobdBuf(1);
            if (dfbug) {
                Systfm.out.println("top of loop");
                bufffr.print(10);
            }
            bufffr.sdbnForFF(rfbdfr);
            switdi (bufffr.buf[bufffr.bufPtr] & 0xff) {
            dbsf 0:
                if (dfbug) {
                    Systfm.out.println("Skipping 0");
                }
                bufffr.bufAvbil--;
                bufffr.bufPtr++;
                brfbk;
            dbsf JPEG.SOF0:
            dbsf JPEG.SOF1:
            dbsf JPEG.SOF2:
                if (isStrfbm) {
                    tirow nfw IIOExdfption
                        ("SOF not pfrmittfd in strfbm mftbdbtb");
                }
                nfwGuy = nfw SOFMbrkfrSfgmfnt(bufffr);
                brfbk;
            dbsf JPEG.DQT:
                nfwGuy = nfw DQTMbrkfrSfgmfnt(bufffr);
                brfbk;
            dbsf JPEG.DHT:
                nfwGuy = nfw DHTMbrkfrSfgmfnt(bufffr);
                brfbk;
            dbsf JPEG.DRI:
                nfwGuy = nfw DRIMbrkfrSfgmfnt(bufffr);
                brfbk;
            dbsf JPEG.APP0:
                // Eitifr JFIF, JFXX, or unknown APP0
                bufffr.lobdBuf(8); // tbg, lfngti, id
                buf = bufffr.buf;
                ptr = bufffr.bufPtr;
                if ((buf[ptr+3] == 'J')
                    && (buf[ptr+4] == 'F')
                    && (buf[ptr+5] == 'I')
                    && (buf[ptr+6] == 'F')
                    && (buf[ptr+7] == 0)) {
                    if (inTiumb) {
                        rfbdfr.wbrningOddurrfd
                            (JPEGImbgfRfbdfr.WARNING_NO_JFIF_IN_THUMB);
                        // Lfbvf nfwGuy null
                        // Rfbd b dummy to skip tif sfgmfnt
                        JFIFMbrkfrSfgmfnt dummy =
                            nfw JFIFMbrkfrSfgmfnt(bufffr);
                    } flsf if (isStrfbm) {
                        tirow nfw IIOExdfption
                            ("JFIF not pfrmittfd in strfbm mftbdbtb");
                    } flsf if (mbrkfrSfqufndf.isEmpty() == fblsf) {
                        tirow nfw IIOExdfption
                            ("JFIF APP0 must bf first mbrkfr bftfr SOI");
                    } flsf {
                        nfwGuy = nfw JFIFMbrkfrSfgmfnt(bufffr);
                    }
                } flsf if ((buf[ptr+3] == 'J')
                           && (buf[ptr+4] == 'F')
                           && (buf[ptr+5] == 'X')
                           && (buf[ptr+6] == 'X')
                           && (buf[ptr+7] == 0)) {
                    if (isStrfbm) {
                        tirow nfw IIOExdfption
                            ("JFXX not pfrmittfd in strfbm mftbdbtb");
                    }
                    if (inTiumb) {
                        tirow nfw IIOExdfption
                          ("JFXX mbrkfrs not bllowfd in JFIF JPEG tiumbnbil");
                    }
                    JFIFMbrkfrSfgmfnt jfif =
                        (JFIFMbrkfrSfgmfnt) findMbrkfrSfgmfnt
                               (JFIFMbrkfrSfgmfnt.dlbss, truf);
                    if (jfif == null) {
                        tirow nfw IIOExdfption
                            ("JFXX fndountfrfd witiout prior JFIF!");
                    }
                    jfif.bddJFXX(bufffr, rfbdfr);
                    // nfwGuy rfmbins null
                } flsf {
                    nfwGuy = nfw MbrkfrSfgmfnt(bufffr);
                    nfwGuy.lobdDbtb(bufffr);
                }
                brfbk;
            dbsf JPEG.APP2:
                // Eitifr bn ICC profilf or unknown APP2
                bufffr.lobdBuf(15); // tbg, lfngti, id
                if ((bufffr.buf[bufffr.bufPtr+3] == 'I')
                    && (bufffr.buf[bufffr.bufPtr+4] == 'C')
                    && (bufffr.buf[bufffr.bufPtr+5] == 'C')
                    && (bufffr.buf[bufffr.bufPtr+6] == '_')
                    && (bufffr.buf[bufffr.bufPtr+7] == 'P')
                    && (bufffr.buf[bufffr.bufPtr+8] == 'R')
                    && (bufffr.buf[bufffr.bufPtr+9] == 'O')
                    && (bufffr.buf[bufffr.bufPtr+10] == 'F')
                    && (bufffr.buf[bufffr.bufPtr+11] == 'I')
                    && (bufffr.buf[bufffr.bufPtr+12] == 'L')
                    && (bufffr.buf[bufffr.bufPtr+13] == 'E')
                    && (bufffr.buf[bufffr.bufPtr+14] == 0)
                    ) {
                    if (isStrfbm) {
                        tirow nfw IIOExdfption
                            ("ICC profilfs not pfrmittfd in strfbm mftbdbtb");
                    }

                    JFIFMbrkfrSfgmfnt jfif =
                        (JFIFMbrkfrSfgmfnt) findMbrkfrSfgmfnt
                        (JFIFMbrkfrSfgmfnt.dlbss, truf);
                    if (jfif == null) {
                        nfwGuy = nfw MbrkfrSfgmfnt(bufffr);
                        nfwGuy.lobdDbtb(bufffr);
                    } flsf {
                        jfif.bddICC(bufffr);
                    }
                    // nfwGuy rfmbins null
                } flsf {
                    nfwGuy = nfw MbrkfrSfgmfnt(bufffr);
                    nfwGuy.lobdDbtb(bufffr);
                }
                brfbk;
            dbsf JPEG.APP14:
                // Eitifr Adobf or unknown APP14
                bufffr.lobdBuf(8); // tbg, lfngti, id
                if ((bufffr.buf[bufffr.bufPtr+3] == 'A')
                    && (bufffr.buf[bufffr.bufPtr+4] == 'd')
                    && (bufffr.buf[bufffr.bufPtr+5] == 'o')
                    && (bufffr.buf[bufffr.bufPtr+6] == 'b')
                    && (bufffr.buf[bufffr.bufPtr+7] == 'f')) {
                    if (isStrfbm) {
                        tirow nfw IIOExdfption
                      ("Adobf APP14 mbrkfrs not pfrmittfd in strfbm mftbdbtb");
                    }
                    nfwGuy = nfw AdobfMbrkfrSfgmfnt(bufffr);
                } flsf {
                    nfwGuy = nfw MbrkfrSfgmfnt(bufffr);
                    nfwGuy.lobdDbtb(bufffr);
                }

                brfbk;
            dbsf JPEG.COM:
                nfwGuy = nfw COMMbrkfrSfgmfnt(bufffr);
                brfbk;
            dbsf JPEG.SOS:
                if (isStrfbm) {
                    tirow nfw IIOExdfption
                        ("SOS not pfrmittfd in strfbm mftbdbtb");
                }
                nfwGuy = nfw SOSMbrkfrSfgmfnt(bufffr);
                brfbk;
            dbsf JPEG.RST0:
            dbsf JPEG.RST1:
            dbsf JPEG.RST2:
            dbsf JPEG.RST3:
            dbsf JPEG.RST4:
            dbsf JPEG.RST5:
            dbsf JPEG.RST6:
            dbsf JPEG.RST7:
                if (dfbug) {
                    Systfm.out.println("Rfstbrt Mbrkfr");
                }
                bufffr.bufPtr++; // Just skip it
                bufffr.bufAvbil--;
                brfbk;
            dbsf JPEG.EOI:
                donf = truf;
                bufffr.bufPtr++;
                bufffr.bufAvbil--;
                brfbk;
            dffbult:
                nfwGuy = nfw MbrkfrSfgmfnt(bufffr);
                nfwGuy.lobdDbtb(bufffr);
                nfwGuy.unknown = truf;
                brfbk;
            }
            if (nfwGuy != null) {
                mbrkfrSfqufndf.bdd(nfwGuy);
                if (dfbug) {
                    nfwGuy.print();
                }
                nfwGuy = null;
            }
        }

        // Now tibt wf'vf rfbd up to tif EOI, wf nffd to pusi bbdk
        // wibtfvfr is lfft in tif bufffr, so tibt tif nfxt rfbd
        // in tif nbtivf dodf will work.

        bufffr.pusiBbdk();

        if (!isConsistfnt()) {
            tirow nfw IIOExdfption("Indonsistfnt mftbdbtb rfbd from strfbm");
        }
    }

    /**
     * Construdts b dffbult strfbm <dodf>JPEGMftbdbtb</dodf> objfdt bppropribtf
     * for tif givfn writf pbrbmftfrs.
     */
    JPEGMftbdbtb(ImbgfWritfPbrbm pbrbm, JPEGImbgfWritfr writfr) {
        tiis(truf, fblsf);

        JPEGImbgfWritfPbrbm jpbrbm = null;

        if ((pbrbm != null) && (pbrbm instbndfof JPEGImbgfWritfPbrbm)) {
            jpbrbm = (JPEGImbgfWritfPbrbm) pbrbm;
            if (!jpbrbm.brfTbblfsSft()) {
                jpbrbm = null;
            }
        }
        if (jpbrbm != null) {
            mbrkfrSfqufndf.bdd(nfw DQTMbrkfrSfgmfnt(jpbrbm.gftQTbblfs()));
            mbrkfrSfqufndf.bdd
                (nfw DHTMbrkfrSfgmfnt(jpbrbm.gftDCHuffmbnTbblfs(),
                                      jpbrbm.gftACHuffmbnTbblfs()));
        } flsf {
            // dffbult tbblfs.
            mbrkfrSfqufndf.bdd(nfw DQTMbrkfrSfgmfnt(JPEG.gftDffbultQTbblfs()));
            mbrkfrSfqufndf.bdd(nfw DHTMbrkfrSfgmfnt(JPEG.gftDffbultHuffmbnTbblfs(truf),
                                                    JPEG.gftDffbultHuffmbnTbblfs(fblsf)));
        }

        // Dfffnsivf progrbmming
        if (!isConsistfnt()) {
            tirow nfw IntfrnblError("Dffbult strfbm mftbdbtb is indonsistfnt");
        }
    }

    /**
     * Construdts b dffbult imbgf <dodf>JPEGMftbdbtb</dodf> objfdt bppropribtf
     * for tif givfn imbgf typf bnd writf pbrbmftfrs.
     */
    JPEGMftbdbtb(ImbgfTypfSpfdififr imbgfTypf,
                 ImbgfWritfPbrbm pbrbm,
                 JPEGImbgfWritfr writfr) {
        tiis(fblsf, fblsf);

        boolfbn wbntJFIF = truf;
        boolfbn wbntAdobf = fblsf;
        int trbnsform = JPEG.ADOBE_UNKNOWN;
        boolfbn willSubsbmplf = truf;
        boolfbn wbntICC = fblsf;
        boolfbn wbntProg = fblsf;
        boolfbn wbntOptimizfd = fblsf;
        boolfbn wbntExtfndfd = fblsf;
        boolfbn wbntQTbblfs = truf;
        boolfbn wbntHTbblfs = truf;
        flobt qublity = JPEG.DEFAULT_QUALITY;
        bytf[] domponfntIDs = { 1, 2, 3, 4};
        int numComponfnts = 0;

        ImbgfTypfSpfdififr dfstTypf = null;

        if (pbrbm != null) {
            dfstTypf = pbrbm.gftDfstinbtionTypf();
            if (dfstTypf != null) {
                if (imbgfTypf != null) {
                    // Ignorf tif dfstinbtion typf.
                    writfr.wbrningOddurrfd
                        (JPEGImbgfWritfr.WARNING_DEST_IGNORED);
                    dfstTypf = null;
                }
            }
            // Tif only progrfssivf modf tibt mbkfs sfnsf ifrf is MODE_DEFAULT
            if (pbrbm.dbnWritfProgrfssivf()) {
                // tif pbrbm mby not bf onf of ours, so it mby rfturn fblsf.
                // If so, tif following would tirow bn fxdfption
                if (pbrbm.gftProgrfssivfModf() == ImbgfWritfPbrbm.MODE_DEFAULT) {
                    wbntProg = truf;
                    wbntOptimizfd = truf;
                    wbntHTbblfs = fblsf;
                }
            }

            if (pbrbm instbndfof JPEGImbgfWritfPbrbm) {
                JPEGImbgfWritfPbrbm jpbrbm = (JPEGImbgfWritfPbrbm) pbrbm;
                if (jpbrbm.brfTbblfsSft()) {
                    wbntQTbblfs = fblsf;  // If tif pbrbm ibs tifm, mftbdbtb siouldn't
                    wbntHTbblfs = fblsf;
                    if ((jpbrbm.gftDCHuffmbnTbblfs().lfngti > 2)
                            || (jpbrbm.gftACHuffmbnTbblfs().lfngti > 2)) {
                        wbntExtfndfd = truf;
                    }
                }
                // Progrfssivf fordfs optimizfd, rfgbrdlfss of pbrbm sftting
                // so donsult tif pbrbm rf optimizfd only if not progrfssivf
                if (!wbntProg) {
                    wbntOptimizfd = jpbrbm.gftOptimizfHuffmbnTbblfs();
                    if (wbntOptimizfd) {
                        wbntHTbblfs = fblsf;
                    }
                }
            }

            // domprfssion qublity siould dftfrminf tif q tbblfs.  Notf tibt tiis
            // will bf ignorfd if wf blrfbdy dfdidfd not to drfbtf bny.
            // Agbin, tif pbrbm mby not bf onf of ours, so wf must difdk tibt it
            // supports domprfssion sfttings
            if (pbrbm.dbnWritfComprfssfd()) {
                if (pbrbm.gftComprfssionModf() == ImbgfWritfPbrbm.MODE_EXPLICIT) {
                    qublity = pbrbm.gftComprfssionQublity();
                }
            }
        }

        // Wf brf donf witi tif pbrbm, now for tif imbgf typfs

        ColorSpbdf ds = null;
        if (dfstTypf != null) {
            ColorModfl dm = dfstTypf.gftColorModfl();
            numComponfnts = dm.gftNumComponfnts();
            boolfbn ibsExtrbComponfnts = (dm.gftNumColorComponfnts() != numComponfnts);
            boolfbn ibsAlpib = dm.ibsAlpib();
            ds = dm.gftColorSpbdf();
            int typf = ds.gftTypf();
            switdi(typf) {
            dbsf ColorSpbdf.TYPE_GRAY:
                willSubsbmplf = fblsf;
                if (ibsExtrbComponfnts) {  // f.g. blpib
                    wbntJFIF = fblsf;
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_3CLR:
                if (ds == JPEG.JCS.gftYCC()) {
                    wbntJFIF = fblsf;
                    domponfntIDs[0] = (bytf) 'Y';
                    domponfntIDs[1] = (bytf) 'C';
                    domponfntIDs[2] = (bytf) 'd';
                    if (ibsAlpib) {
                        domponfntIDs[3] = (bytf) 'A';
                    }
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_YCbCr:
                if (ibsExtrbComponfnts) { // f.g. K or blpib
                    wbntJFIF = fblsf;
                    if (!ibsAlpib) { // Not blpib, so must bf K
                        wbntAdobf = truf;
                        trbnsform = JPEG.ADOBE_YCCK;
                    }
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_RGB:  // witi or witiout blpib
                wbntJFIF = fblsf;
                wbntAdobf = truf;
                willSubsbmplf = fblsf;
                domponfntIDs[0] = (bytf) 'R';
                domponfntIDs[1] = (bytf) 'G';
                domponfntIDs[2] = (bytf) 'B';
                if (ibsAlpib) {
                    domponfntIDs[3] = (bytf) 'A';
                }
                brfbk;
            dffbult:
                // Evfrytiing flsf is not subsbmplfd, gfts no spfdibl mbrkfr,
                // bnd domponfnt ids brf 1 - N
                wbntJFIF = fblsf;
                willSubsbmplf = fblsf;
            }
        } flsf if (imbgfTypf != null) {
            ColorModfl dm = imbgfTypf.gftColorModfl();
            numComponfnts = dm.gftNumComponfnts();
            boolfbn ibsExtrbComponfnts = (dm.gftNumColorComponfnts() != numComponfnts);
            boolfbn ibsAlpib = dm.ibsAlpib();
            ds = dm.gftColorSpbdf();
            int typf = ds.gftTypf();
            switdi(typf) {
            dbsf ColorSpbdf.TYPE_GRAY:
                willSubsbmplf = fblsf;
                if (ibsExtrbComponfnts) {  // f.g. blpib
                    wbntJFIF = fblsf;
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_RGB:  // witi or witiout blpib
                // witiout blpib wf just bddfpt tif JFIF dffbults
                if (ibsAlpib) {
                    wbntJFIF = fblsf;
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_3CLR:
                wbntJFIF = fblsf;
                willSubsbmplf = fblsf;
                if (ds.fqubls(ColorSpbdf.gftInstbndf(ColorSpbdf.CS_PYCC))) {
                    willSubsbmplf = truf;
                    wbntAdobf = truf;
                    domponfntIDs[0] = (bytf) 'Y';
                    domponfntIDs[1] = (bytf) 'C';
                    domponfntIDs[2] = (bytf) 'd';
                    if (ibsAlpib) {
                        domponfntIDs[3] = (bytf) 'A';
                    }
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_YCbCr:
                if (ibsExtrbComponfnts) { // f.g. K or blpib
                    wbntJFIF = fblsf;
                    if (!ibsAlpib) {  // tifn it must bf K
                        wbntAdobf = truf;
                        trbnsform = JPEG.ADOBE_YCCK;
                    }
                }
                brfbk;
            dbsf ColorSpbdf.TYPE_CMYK:
                wbntJFIF = fblsf;
                wbntAdobf = truf;
                trbnsform = JPEG.ADOBE_YCCK;
                brfbk;

            dffbult:
                // Evfrytiing flsf is not subsbmplfd, gfts no spfdibl mbrkfr,
                // bnd domponfnt ids brf 0 - N
                wbntJFIF = fblsf;
                willSubsbmplf = fblsf;
            }

        }

        // do wf wbnt bn ICC profilf?
        if (wbntJFIF && JPEG.isNonStbndbrdICC(ds)) {
            wbntICC = truf;
        }

        // Now stfp tirougi tif mbrkfrs, donsulting our vbribblfs.
        if (wbntJFIF) {
            JFIFMbrkfrSfgmfnt jfif = nfw JFIFMbrkfrSfgmfnt();
            mbrkfrSfqufndf.bdd(jfif);
            if (wbntICC) {
                try {
                    jfif.bddICC((ICC_ColorSpbdf)ds);
                } dbtdi (IOExdfption f) {} // Cbn't ibppfn ifrf
            }
        }
        // Adobf
        if (wbntAdobf) {
            mbrkfrSfqufndf.bdd(nfw AdobfMbrkfrSfgmfnt(trbnsform));
        }

        // dqt
        if (wbntQTbblfs) {
            mbrkfrSfqufndf.bdd(nfw DQTMbrkfrSfgmfnt(qublity, willSubsbmplf));
        }

        // dit
        if (wbntHTbblfs) {
            mbrkfrSfqufndf.bdd(nfw DHTMbrkfrSfgmfnt(willSubsbmplf));
        }

        // sof
        mbrkfrSfqufndf.bdd(nfw SOFMbrkfrSfgmfnt(wbntProg,
                                                wbntExtfndfd,
                                                willSubsbmplf,
                                                domponfntIDs,
                                                numComponfnts));

        // sos
        if (!wbntProg) {  // Dffbult progrfssion sdbns brf donf in tif writfr
            mbrkfrSfqufndf.bdd(nfw SOSMbrkfrSfgmfnt(willSubsbmplf,
                                                    domponfntIDs,
                                                    numComponfnts));
        }

        // Dfffnsivf progrbmming
        if (!isConsistfnt()) {
            tirow nfw IntfrnblError("Dffbult imbgf mftbdbtb is indonsistfnt");
        }
    }

    ////// End of donstrudtors

    // Utilitifs for dfbling witi tif mbrkfr sfqufndf.
    // Tif first onfs ibvf pbdkbgf bddfss for bddfss from tif writfr.

    /**
     * Rfturns tif first MbrkfrSfgmfnt objfdt in tif list
     * witi tif givfn tbg, or null if nonf is found.
     */
    MbrkfrSfgmfnt findMbrkfrSfgmfnt(int tbg) {
        Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            MbrkfrSfgmfnt sfg = itfr.nfxt();
            if (sfg.tbg == tbg) {
                rfturn sfg;
            }
        }
        rfturn null;
    }

    /**
     * Rfturns tif first or lbst MbrkfrSfgmfnt objfdt in tif list
     * of tif givfn dlbss, or null if nonf is found.
     */
    MbrkfrSfgmfnt findMbrkfrSfgmfnt(Clbss<? fxtfnds MbrkfrSfgmfnt> dls, boolfbn first) {
        if (first) {
            Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                MbrkfrSfgmfnt sfg = itfr.nfxt();
                if (dls.isInstbndf(sfg)) {
                    rfturn sfg;
                }
            }
        } flsf {
            ListItfrbtor<MbrkfrSfgmfnt> itfr =
                mbrkfrSfqufndf.listItfrbtor(mbrkfrSfqufndf.sizf());
            wiilf (itfr.ibsPrfvious()) {
                MbrkfrSfgmfnt sfg = itfr.prfvious();
                if (dls.isInstbndf(sfg)) {
                    rfturn sfg;
                }
            }
        }
        rfturn null;
    }

    /**
     * Rfturns tif indfx of tif first or lbst MbrkfrSfgmfnt in tif list
     * of tif givfn dlbss, or -1 if nonf is found.
     */
    privbtf int findMbrkfrSfgmfntPosition(Clbss<? fxtfnds MbrkfrSfgmfnt> dls,
                                          boolfbn first) {
        if (first) {
            ListItfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.listItfrbtor();
            for (int i = 0; itfr.ibsNfxt(); i++) {
                MbrkfrSfgmfnt sfg = itfr.nfxt();
                if (dls.isInstbndf(sfg)) {
                    rfturn i;
                }
            }
        } flsf {
            ListItfrbtor<MbrkfrSfgmfnt> itfr =
                    mbrkfrSfqufndf.listItfrbtor(mbrkfrSfqufndf.sizf());
            for (int i = mbrkfrSfqufndf.sizf()-1; itfr.ibsPrfvious(); i--) {
                MbrkfrSfgmfnt sfg = itfr.prfvious();
                if (dls.isInstbndf(sfg)) {
                    rfturn i;
                }
            }
        }
        rfturn -1;
    }

    privbtf int findLbstUnknownMbrkfrSfgmfntPosition() {
        ListItfrbtor<MbrkfrSfgmfnt> itfr =
            mbrkfrSfqufndf.listItfrbtor(mbrkfrSfqufndf.sizf());
        for (int i = mbrkfrSfqufndf.sizf()-1; itfr.ibsPrfvious(); i--) {
            MbrkfrSfgmfnt sfg = itfr.prfvious();
            if (sfg.unknown == truf) {
                rfturn i;
            }
        }
        rfturn -1;
    }

    // Implfmfnt Clonfbblf, but rfstridt bddfss

    protfdtfd Objfdt dlonf() {
        JPEGMftbdbtb nfwGuy = null;
        try {
            nfwGuy = (JPEGMftbdbtb) supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {} // won't ibppfn
        if (mbrkfrSfqufndf != null) {
            nfwGuy.mbrkfrSfqufndf = dlonfSfqufndf();
        }
        nfwGuy.rfsftSfqufndf = null;
        rfturn nfwGuy;
    }

    /**
     * Rfturns b dffp dopy of tif durrfnt mbrkfr sfqufndf.
     */
    privbtf List<MbrkfrSfgmfnt> dlonfSfqufndf() {
        if (mbrkfrSfqufndf == null) {
            rfturn null;
        }
        List<MbrkfrSfgmfnt> rftvbl = nfw ArrbyList<>(mbrkfrSfqufndf.sizf());
        Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
        wiilf(itfr.ibsNfxt()) {
            MbrkfrSfgmfnt sfg = itfr.nfxt();
            rftvbl.bdd((MbrkfrSfgmfnt) sfg.dlonf());
        }

        rfturn rftvbl;
    }


    // Trff mftiods

    publid Nodf gftAsTrff(String formbtNbmf) {
        if (formbtNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("null formbtNbmf!");
        }
        if (isStrfbm) {
            if (formbtNbmf.fqubls(JPEG.nbtivfStrfbmMftbdbtbFormbtNbmf)) {
                rfturn gftNbtivfTrff();
            }
        } flsf {
            if (formbtNbmf.fqubls(JPEG.nbtivfImbgfMftbdbtbFormbtNbmf)) {
                rfturn gftNbtivfTrff();
            }
            if (formbtNbmf.fqubls
                    (IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf)) {
                rfturn gftStbndbrdTrff();
            }
        }
        tirow  nfw IllfgblArgumfntExdfption("Unsupportfd formbt nbmf: "
                                                + formbtNbmf);
    }

    IIOMftbdbtbNodf gftNbtivfTrff() {
        IIOMftbdbtbNodf root;
        IIOMftbdbtbNodf top;
        Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
        if (isStrfbm) {
            root = nfw IIOMftbdbtbNodf(JPEG.nbtivfStrfbmMftbdbtbFormbtNbmf);
            top = root;
        } flsf {
            IIOMftbdbtbNodf sfqufndf = nfw IIOMftbdbtbNodf("mbrkfrSfqufndf");
            if (!inTiumb) {
                root = nfw IIOMftbdbtbNodf(JPEG.nbtivfImbgfMftbdbtbFormbtNbmf);
                IIOMftbdbtbNodf ifbdfr = nfw IIOMftbdbtbNodf("JPEGvbrifty");
                root.bppfndCiild(ifbdfr);
                JFIFMbrkfrSfgmfnt jfif = (JFIFMbrkfrSfgmfnt)
                    findMbrkfrSfgmfnt(JFIFMbrkfrSfgmfnt.dlbss, truf);
                if (jfif != null) {
                    itfr.nfxt();  // JFIF must bf first, so tiis skips it
                    ifbdfr.bppfndCiild(jfif.gftNbtivfNodf());
                }
                root.bppfndCiild(sfqufndf);
            } flsf {
                root = sfqufndf;
            }
            top = sfqufndf;
        }
        wiilf(itfr.ibsNfxt()) {
            MbrkfrSfgmfnt sfg = itfr.nfxt();
            top.bppfndCiild(sfg.gftNbtivfNodf());
        }
        rfturn root;
    }

    // Stbndbrd trff nodf mftiods

    protfdtfd IIOMftbdbtbNodf gftStbndbrdCirombNodf() {
        ibsAlpib = fblsf;  // Unlfss wf find otifrwisf

        // Colorspbdf typf - follow tif rulfs in tif spfd
        // First gft tif SOF mbrkfr sfgmfnt, if tifrf is onf
        SOFMbrkfrSfgmfnt sof = (SOFMbrkfrSfgmfnt)
            findMbrkfrSfgmfnt(SOFMbrkfrSfgmfnt.dlbss, truf);
        if (sof == null) {
            // No imbgf, so no diromb
            rfturn null;
        }

        IIOMftbdbtbNodf diromb = nfw IIOMftbdbtbNodf("Ciromb");
        IIOMftbdbtbNodf dsTypf = nfw IIOMftbdbtbNodf("ColorSpbdfTypf");
        diromb.bppfndCiild(dsTypf);

        // gft tif numbfr of dibnnfls
        int numCibnnfls = sof.domponfntSpfds.lfngti;

        IIOMftbdbtbNodf numCibnNodf = nfw IIOMftbdbtbNodf("NumCibnnfls");
        diromb.bppfndCiild(numCibnNodf);
        numCibnNodf.sftAttributf("vbluf", Intfgfr.toString(numCibnnfls));

        // is tifrf b JFIF mbrkfr sfgmfnt?
        if (findMbrkfrSfgmfnt(JFIFMbrkfrSfgmfnt.dlbss, truf) != null) {
            if (numCibnnfls == 1) {
                dsTypf.sftAttributf("nbmf", "GRAY");
            } flsf {
                dsTypf.sftAttributf("nbmf", "YCbCr");
            }
            rfturn diromb;
        }

        // How bbout bn Adobf mbrkfr sfgmfnt?
        AdobfMbrkfrSfgmfnt bdobf =
            (AdobfMbrkfrSfgmfnt) findMbrkfrSfgmfnt(AdobfMbrkfrSfgmfnt.dlbss, truf);
        if (bdobf != null){
            switdi (bdobf.trbnsform) {
            dbsf JPEG.ADOBE_YCCK:
                dsTypf.sftAttributf("nbmf", "YCCK");
                brfbk;
            dbsf JPEG.ADOBE_YCC:
                dsTypf.sftAttributf("nbmf", "YCbCr");
                brfbk;
            dbsf JPEG.ADOBE_UNKNOWN:
                if (numCibnnfls == 3) {
                    dsTypf.sftAttributf("nbmf", "RGB");
                } flsf if (numCibnnfls == 4) {
                    dsTypf.sftAttributf("nbmf", "CMYK");
                }
                brfbk;
            }
            rfturn diromb;
        }

        // Nfitifr mbrkfr.  Cifdk domponfnts
        if (numCibnnfls < 3) {
            dsTypf.sftAttributf("nbmf", "GRAY");
            if (numCibnnfls == 2) {
                ibsAlpib = truf;
            }
            rfturn diromb;
        }

        boolfbn idsArfJFIF = truf;

        for (int i = 0; i < sof.domponfntSpfds.lfngti; i++) {
            int id = sof.domponfntSpfds[i].domponfntId;
            if ((id < 1) || (id >= sof.domponfntSpfds.lfngti)) {
                idsArfJFIF = fblsf;
            }
        }

        if (idsArfJFIF) {
            dsTypf.sftAttributf("nbmf", "YCbCr");
            if (numCibnnfls == 4) {
                ibsAlpib = truf;
            }
            rfturn diromb;
        }

        // Cifdk bgbinst tif lfttfrs
        if ((sof.domponfntSpfds[0].domponfntId == 'R')
            && (sof.domponfntSpfds[1].domponfntId == 'G')
            && (sof.domponfntSpfds[2].domponfntId == 'B')){

            dsTypf.sftAttributf("nbmf", "RGB");
            if ((numCibnnfls == 4)
                && (sof.domponfntSpfds[3].domponfntId == 'A')) {
                ibsAlpib = truf;
            }
            rfturn diromb;
        }

        if ((sof.domponfntSpfds[0].domponfntId == 'Y')
            && (sof.domponfntSpfds[1].domponfntId == 'C')
            && (sof.domponfntSpfds[2].domponfntId == 'd')){

            dsTypf.sftAttributf("nbmf", "PiotoYCC");
            if ((numCibnnfls == 4)
                && (sof.domponfntSpfds[3].domponfntId == 'A')) {
                ibsAlpib = truf;
            }
            rfturn diromb;
        }

        // Finblly, 3-dibnnfl subsbmplfd brf YCbCr, unsubsbmplfd brf RGB
        // 4-dibnnfl subsbmplfd brf YCbCrA, unsubsbmplfd brf CMYK

        boolfbn subsbmplfd = fblsf;

        int ifbdtor = sof.domponfntSpfds[0].HsbmplingFbdtor;
        int vfbdtor = sof.domponfntSpfds[0].VsbmplingFbdtor;

        for (int i = 1; i<sof.domponfntSpfds.lfngti; i++) {
            if ((sof.domponfntSpfds[i].HsbmplingFbdtor != ifbdtor)
                || (sof.domponfntSpfds[i].VsbmplingFbdtor != vfbdtor)){
                subsbmplfd = truf;
                brfbk;
            }
        }

        if (subsbmplfd) {
            dsTypf.sftAttributf("nbmf", "YCbCr");
            if (numCibnnfls == 4) {
                ibsAlpib = truf;
            }
            rfturn diromb;
        }

        // Not subsbmplfd.  numCibnnfls < 3 is tbkfn dbrf of bbovf
        if (numCibnnfls == 3) {
            dsTypf.sftAttributf("nbmf", "RGB");
        } flsf {
            dsTypf.sftAttributf("nbmf", "CMYK");
        }

        rfturn diromb;
    }

    protfdtfd IIOMftbdbtbNodf gftStbndbrdComprfssionNodf() {

        IIOMftbdbtbNodf domprfssion = nfw IIOMftbdbtbNodf("Comprfssion");

        // ComprfssionTypfNbmf
        IIOMftbdbtbNodf nbmf = nfw IIOMftbdbtbNodf("ComprfssionTypfNbmf");
        nbmf.sftAttributf("vbluf", "JPEG");
        domprfssion.bppfndCiild(nbmf);

        // Losslfss - fblsf
        IIOMftbdbtbNodf losslfss = nfw IIOMftbdbtbNodf("Losslfss");
        losslfss.sftAttributf("vbluf", "FALSE");
        domprfssion.bppfndCiild(losslfss);

        // NumProgrfssivfSdbns - dount sos sfgmfnts
        int sosCount = 0;
        Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            MbrkfrSfgmfnt ms = itfr.nfxt();
            if (ms.tbg == JPEG.SOS) {
                sosCount++;
            }
        }
        if (sosCount != 0) {
            IIOMftbdbtbNodf prog = nfw IIOMftbdbtbNodf("NumProgrfssivfSdbns");
            prog.sftAttributf("vbluf", Intfgfr.toString(sosCount));
            domprfssion.bppfndCiild(prog);
        }

        rfturn domprfssion;
    }

    protfdtfd IIOMftbdbtbNodf gftStbndbrdDimfnsionNodf() {
        // If wf ibvf b JFIF mbrkfr sfgmfnt, wf know b littlf
        // otifrwisf bll wf know is tif orifntbtion, wiidi is blwbys normbl
        IIOMftbdbtbNodf dim = nfw IIOMftbdbtbNodf("Dimfnsion");
        IIOMftbdbtbNodf orifnt = nfw IIOMftbdbtbNodf("ImbgfOrifntbtion");
        orifnt.sftAttributf("vbluf", "normbl");
        dim.bppfndCiild(orifnt);

        JFIFMbrkfrSfgmfnt jfif =
            (JFIFMbrkfrSfgmfnt) findMbrkfrSfgmfnt(JFIFMbrkfrSfgmfnt.dlbss, truf);
        if (jfif != null) {

            // Aspfdt Rbtio is widti of pixfl / ifigit of pixfl
            flobt bspfdtRbtio;
            if (jfif.rfsUnits == 0) {
                // In tiis dbsf tify just fndodf bspfdt rbtio dirfdtly
                bspfdtRbtio = ((flobt) jfif.Xdfnsity)/jfif.Ydfnsity;
            } flsf {
                // Tify brf truf dfnsitifs (f.g. dpi) bnd must bf invfrtfd
                bspfdtRbtio = ((flobt) jfif.Ydfnsity)/jfif.Xdfnsity;
            }
            IIOMftbdbtbNodf bspfdt = nfw IIOMftbdbtbNodf("PixflAspfdtRbtio");
            bspfdt.sftAttributf("vbluf", Flobt.toString(bspfdtRbtio));
            dim.insfrtBfforf(bspfdt, orifnt);

            // Pixfl sizf
            if (jfif.rfsUnits != 0) {
                // 1 == dpi, 2 == dpd
                flobt sdblf = (jfif.rfsUnits == 1) ? 25.4F : 10.0F;

                IIOMftbdbtbNodf ioriz =
                    nfw IIOMftbdbtbNodf("HorizontblPixflSizf");
                ioriz.sftAttributf("vbluf",
                                   Flobt.toString(sdblf/jfif.Xdfnsity));
                dim.bppfndCiild(ioriz);

                IIOMftbdbtbNodf vfrt =
                    nfw IIOMftbdbtbNodf("VfrtidblPixflSizf");
                vfrt.sftAttributf("vbluf",
                                  Flobt.toString(sdblf/jfif.Ydfnsity));
                dim.bppfndCiild(vfrt);
            }
        }
        rfturn dim;
    }

    protfdtfd IIOMftbdbtbNodf gftStbndbrdTfxtNodf() {
        IIOMftbdbtbNodf tfxt = null;
        // Add b tfxt fntry for fbdi COM Mbrkfr Sfgmfnt
        if (findMbrkfrSfgmfnt(JPEG.COM) != null) {
            tfxt = nfw IIOMftbdbtbNodf("Tfxt");
            Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                MbrkfrSfgmfnt sfg = itfr.nfxt();
                if (sfg.tbg == JPEG.COM) {
                    COMMbrkfrSfgmfnt dom = (COMMbrkfrSfgmfnt) sfg;
                    IIOMftbdbtbNodf fntry = nfw IIOMftbdbtbNodf("TfxtEntry");
                    fntry.sftAttributf("kfyword", "dommfnt");
                    fntry.sftAttributf("vbluf", dom.gftCommfnt());
                tfxt.bppfndCiild(fntry);
                }
            }
        }
        rfturn tfxt;
    }

    protfdtfd IIOMftbdbtbNodf gftStbndbrdTrbnspbrfndyNodf() {
        IIOMftbdbtbNodf trbns = null;
        if (ibsAlpib == truf) {
            trbns = nfw IIOMftbdbtbNodf("Trbnspbrfndy");
            IIOMftbdbtbNodf blpib = nfw IIOMftbdbtbNodf("Alpib");
            blpib.sftAttributf("vbluf", "nonprfmultiplifd"); // Alwbys bssumf
            trbns.bppfndCiild(blpib);
        }
        rfturn trbns;
    }

    // Editing

    publid boolfbn isRfbdOnly() {
        rfturn fblsf;
    }

    publid void mfrgfTrff(String formbtNbmf, Nodf root)
        tirows IIOInvblidTrffExdfption {
        if (formbtNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("null formbtNbmf!");
        }
        if (root == null) {
            tirow nfw IllfgblArgumfntExdfption("null root!");
        }
        List<MbrkfrSfgmfnt> dopy = null;
        if (rfsftSfqufndf == null) {
            rfsftSfqufndf = dlonfSfqufndf();  // Dffp dopy
            dopy = rfsftSfqufndf;  // Avoid dloning twidf
        } flsf {
            dopy = dlonfSfqufndf();
        }
        if (isStrfbm &&
            (formbtNbmf.fqubls(JPEG.nbtivfStrfbmMftbdbtbFormbtNbmf))) {
                mfrgfNbtivfTrff(root);
        } flsf if (!isStrfbm &&
                   (formbtNbmf.fqubls(JPEG.nbtivfImbgfMftbdbtbFormbtNbmf))) {
            mfrgfNbtivfTrff(root);
        } flsf if (!isStrfbm &&
                   (formbtNbmf.fqubls
                    (IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf))) {
            mfrgfStbndbrdTrff(root);
        } flsf {
            tirow  nfw IllfgblArgumfntExdfption("Unsupportfd formbt nbmf: "
                                                + formbtNbmf);
        }
        if (!isConsistfnt()) {
            mbrkfrSfqufndf = dopy;
            tirow nfw IIOInvblidTrffExdfption
                ("Mfrgfd trff is invblid; originbl rfstorfd", root);
        }
    }

    privbtf void mfrgfNbtivfTrff(Nodf root) tirows IIOInvblidTrffExdfption {
        String nbmf = root.gftNodfNbmf();
        if (nbmf != ((isStrfbm) ? JPEG.nbtivfStrfbmMftbdbtbFormbtNbmf
                                : JPEG.nbtivfImbgfMftbdbtbFormbtNbmf)) {
            tirow nfw IIOInvblidTrffExdfption("Invblid root nodf nbmf: " + nbmf,
                                              root);
        }
        if (root.gftCiildNodfs().gftLfngti() != 2) { // JPEGvbrifty bnd mbrkfrSfqufndf
            tirow nfw IIOInvblidTrffExdfption(
                "JPEGvbrifty bnd mbrkfrSfqufndf nodfs must bf prfsfnt", root);
        }
        mfrgfJFIFsubtrff(root.gftFirstCiild());
        mfrgfSfqufndfSubtrff(root.gftLbstCiild());
    }

    /**
     * Mfrgf b JFIF subtrff into tif mbrkfr sfqufndf, if tif subtrff
     * is non-fmpty.
     * If b JFIF mbrkfr fxists, updbtf it from tif subtrff.
     * If nonf fxists, drfbtf onf from tif subtrff bnd insfrt it bt tif
     * bfginning of tif mbrkfr sfqufndf.
     */
    privbtf void mfrgfJFIFsubtrff(Nodf JPEGvbrifty)
        tirows IIOInvblidTrffExdfption {
        if (JPEGvbrifty.gftCiildNodfs().gftLfngti() != 0) {
            Nodf jfifNodf = JPEGvbrifty.gftFirstCiild();
            // is tifrf blrfbdy b jfif mbrkfr sfgmfnt?
            JFIFMbrkfrSfgmfnt jfifSfg =
                (JFIFMbrkfrSfgmfnt) findMbrkfrSfgmfnt(JFIFMbrkfrSfgmfnt.dlbss, truf);
            if (jfifSfg != null) {
                jfifSfg.updbtfFromNbtivfNodf(jfifNodf, fblsf);
            } flsf {
                // Add it bs tif first flfmfnt in tif list.
                mbrkfrSfqufndf.bdd(0, nfw JFIFMbrkfrSfgmfnt(jfifNodf));
            }
        }
    }

    privbtf void mfrgfSfqufndfSubtrff(Nodf sfqufndfTrff)
        tirows IIOInvblidTrffExdfption {
        NodfList diildrfn = sfqufndfTrff.gftCiildNodfs();
        for (int i = 0; i < diildrfn.gftLfngti(); i++) {
            Nodf nodf = diildrfn.itfm(i);
            String nbmf = nodf.gftNodfNbmf();
            if (nbmf.fqubls("dqt")) {
                mfrgfDQTNodf(nodf);
            } flsf if (nbmf.fqubls("dit")) {
                mfrgfDHTNodf(nodf);
            } flsf if (nbmf.fqubls("dri")) {
                mfrgfDRINodf(nodf);
            } flsf if (nbmf.fqubls("dom")) {
                mfrgfCOMNodf(nodf);
            } flsf if (nbmf.fqubls("bpp14Adobf")) {
                mfrgfAdobfNodf(nodf);
            } flsf if (nbmf.fqubls("unknown")) {
                mfrgfUnknownNodf(nodf);
            } flsf if (nbmf.fqubls("sof")) {
                mfrgfSOFNodf(nodf);
            } flsf if (nbmf.fqubls("sos")) {
                mfrgfSOSNodf(nodf);
            } flsf {
                tirow nfw IIOInvblidTrffExdfption("Invblid nodf: " + nbmf, nodf);
            }
        }
    }

    /**
     * Mfrgf tif givfn DQT nodf into tif mbrkfr sfqufndf.  If tifrf blrfbdy
     * fxist DQT mbrkfr sfgmfnts in tif sfqufndf, tifn fbdi tbblf in tif
     * nodf rfplbdfs tif first tbblf, in bny DQT sfgmfnt, witi tif sbmf
     * tbblf id.  If nonf of tif fxisting DQT sfgmfnts dontbin b tbblf witi
     * tif sbmf id, tifn tif tbblf is bddfd to tif lbst fxisting DQT sfgmfnt.
     * If tifrf brf no DQT sfgmfnts, tifn b nfw onf is drfbtfd bnd bddfd
     * bs follows:
     * If tifrf brf DHT sfgmfnts, tif nfw DQT sfgmfnt is insfrtfd bfforf tif
     * first onf.
     * If tifrf brf no DHT sfgmfnts, tif nfw DQT sfgmfnt is insfrtfd bfforf
     * bn SOF sfgmfnt, if tifrf is onf.
     * If tifrf is no SOF sfgmfnt, tif nfw DQT sfgmfnt is insfrtfd bfforf
     * tif first SOS sfgmfnt, if tifrf is onf.
     * If tifrf is no SOS sfgmfnt, tif nfw DQT sfgmfnt is bddfd to tif fnd
     * of tif sfqufndf.
     */
    privbtf void mfrgfDQTNodf(Nodf nodf) tirows IIOInvblidTrffExdfption {
        // First dollfdt bny fxisting DQT nodfs into b lodbl list
        ArrbyList<DQTMbrkfrSfgmfnt> oldDQTs = nfw ArrbyList<>();
        Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            MbrkfrSfgmfnt sfg = itfr.nfxt();
            if (sfg instbndfof DQTMbrkfrSfgmfnt) {
                oldDQTs.bdd((DQTMbrkfrSfgmfnt) sfg);
            }
        }
        if (!oldDQTs.isEmpty()) {
            NodfList diildrfn = nodf.gftCiildNodfs();
            for (int i = 0; i < diildrfn.gftLfngti(); i++) {
                Nodf diild = diildrfn.itfm(i);
                int diildID = MbrkfrSfgmfnt.gftAttributfVbluf(diild,
                                                              null,
                                                              "qtbblfId",
                                                              0, 3,
                                                              truf);
                DQTMbrkfrSfgmfnt dqt = null;
                int tbblfIndfx = -1;
                for (int j = 0; j < oldDQTs.sizf(); j++) {
                    DQTMbrkfrSfgmfnt tfstDQT = oldDQTs.gft(j);
                    for (int k = 0; k < tfstDQT.tbblfs.sizf(); k++) {
                        DQTMbrkfrSfgmfnt.Qtbblf tfstTbblf = tfstDQT.tbblfs.gft(k);
                        if (diildID == tfstTbblf.tbblfID) {
                            dqt = tfstDQT;
                            tbblfIndfx = k;
                            brfbk;
                        }
                    }
                    if (dqt != null) brfbk;
                }
                if (dqt != null) {
                    dqt.tbblfs.sft(tbblfIndfx, dqt.gftQtbblfFromNodf(diild));
                } flsf {
                    dqt = oldDQTs.gft(oldDQTs.sizf()-1);
                    dqt.tbblfs.bdd(dqt.gftQtbblfFromNodf(diild));
                }
            }
        } flsf {
            DQTMbrkfrSfgmfnt nfwGuy = nfw DQTMbrkfrSfgmfnt(nodf);
            int firstDHT = findMbrkfrSfgmfntPosition(DHTMbrkfrSfgmfnt.dlbss, truf);
            int firstSOF = findMbrkfrSfgmfntPosition(SOFMbrkfrSfgmfnt.dlbss, truf);
            int firstSOS = findMbrkfrSfgmfntPosition(SOSMbrkfrSfgmfnt.dlbss, truf);
            if (firstDHT != -1) {
                mbrkfrSfqufndf.bdd(firstDHT, nfwGuy);
            } flsf if (firstSOF != -1) {
                mbrkfrSfqufndf.bdd(firstSOF, nfwGuy);
            } flsf if (firstSOS != -1) {
                mbrkfrSfqufndf.bdd(firstSOS, nfwGuy);
            } flsf {
                mbrkfrSfqufndf.bdd(nfwGuy);
            }
        }
    }

    /**
     * Mfrgf tif givfn DHT nodf into tif mbrkfr sfqufndf.  If tifrf blrfbdy
     * fxist DHT mbrkfr sfgmfnts in tif sfqufndf, tifn fbdi tbblf in tif
     * nodf rfplbdfs tif first tbblf, in bny DHT sfgmfnt, witi tif sbmf
     * tbblf dlbss bnd tbblf id.  If nonf of tif fxisting DHT sfgmfnts dontbin
     * b tbblf witi tif sbmf dlbss bnd id, tifn tif tbblf is bddfd to tif lbst
     * fxisting DHT sfgmfnt.
     * If tifrf brf no DHT sfgmfnts, tifn b nfw onf is drfbtfd bnd bddfd
     * bs follows:
     * If tifrf brf DQT sfgmfnts, tif nfw DHT sfgmfnt is insfrtfd immfdibtfly
     * following tif lbst DQT sfgmfnt.
     * If tifrf brf no DQT sfgmfnts, tif nfw DHT sfgmfnt is insfrtfd bfforf
     * bn SOF sfgmfnt, if tifrf is onf.
     * If tifrf is no SOF sfgmfnt, tif nfw DHT sfgmfnt is insfrtfd bfforf
     * tif first SOS sfgmfnt, if tifrf is onf.
     * If tifrf is no SOS sfgmfnt, tif nfw DHT sfgmfnt is bddfd to tif fnd
     * of tif sfqufndf.
     */
    privbtf void mfrgfDHTNodf(Nodf nodf) tirows IIOInvblidTrffExdfption {
        // First dollfdt bny fxisting DQT nodfs into b lodbl list
        ArrbyList<DHTMbrkfrSfgmfnt> oldDHTs = nfw ArrbyList<>();
        Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            MbrkfrSfgmfnt sfg = itfr.nfxt();
            if (sfg instbndfof DHTMbrkfrSfgmfnt) {
                oldDHTs.bdd((DHTMbrkfrSfgmfnt) sfg);
            }
        }
        if (!oldDHTs.isEmpty()) {
            NodfList diildrfn = nodf.gftCiildNodfs();
            for (int i = 0; i < diildrfn.gftLfngti(); i++) {
                Nodf diild = diildrfn.itfm(i);
                NbmfdNodfMbp bttrs = diild.gftAttributfs();
                int diildID = MbrkfrSfgmfnt.gftAttributfVbluf(diild,
                                                              bttrs,
                                                              "itbblfId",
                                                              0, 3,
                                                              truf);
                int diildClbss = MbrkfrSfgmfnt.gftAttributfVbluf(diild,
                                                                 bttrs,
                                                                 "dlbss",
                                                                 0, 1,
                                                                 truf);
                DHTMbrkfrSfgmfnt dit = null;
                int tbblfIndfx = -1;
                for (int j = 0; j < oldDHTs.sizf(); j++) {
                    DHTMbrkfrSfgmfnt tfstDHT = oldDHTs.gft(j);
                    for (int k = 0; k < tfstDHT.tbblfs.sizf(); k++) {
                        DHTMbrkfrSfgmfnt.Htbblf tfstTbblf = tfstDHT.tbblfs.gft(k);
                        if ((diildID == tfstTbblf.tbblfID) &&
                            (diildClbss == tfstTbblf.tbblfClbss)) {
                            dit = tfstDHT;
                            tbblfIndfx = k;
                            brfbk;
                        }
                    }
                    if (dit != null) brfbk;
                }
                if (dit != null) {
                    dit.tbblfs.sft(tbblfIndfx, dit.gftHtbblfFromNodf(diild));
                } flsf {
                    dit = oldDHTs.gft(oldDHTs.sizf()-1);
                    dit.tbblfs.bdd(dit.gftHtbblfFromNodf(diild));
                }
            }
        } flsf {
            DHTMbrkfrSfgmfnt nfwGuy = nfw DHTMbrkfrSfgmfnt(nodf);
            int lbstDQT = findMbrkfrSfgmfntPosition(DQTMbrkfrSfgmfnt.dlbss, fblsf);
            int firstSOF = findMbrkfrSfgmfntPosition(SOFMbrkfrSfgmfnt.dlbss, truf);
            int firstSOS = findMbrkfrSfgmfntPosition(SOSMbrkfrSfgmfnt.dlbss, truf);
            if (lbstDQT != -1) {
                mbrkfrSfqufndf.bdd(lbstDQT+1, nfwGuy);
            } flsf if (firstSOF != -1) {
                mbrkfrSfqufndf.bdd(firstSOF, nfwGuy);
            } flsf if (firstSOS != -1) {
                mbrkfrSfqufndf.bdd(firstSOS, nfwGuy);
            } flsf {
                mbrkfrSfqufndf.bdd(nfwGuy);
            }
        }
    }

    /**
     * Mfrgf tif givfn DRI nodf into tif mbrkfr sfqufndf.
     * If tifrf blrfbdy fxists b DRI mbrkfr sfgmfnt, tif rfstbrt intfrvbl
     * vbluf is updbtfd.
     * If tifrf is no DRI sfgmfnt, tifn b nfw onf is drfbtfd bnd bddfd bs
     * follows:
     * If tifrf is bn SOF sfgmfnt, tif nfw DRI sfgmfnt is insfrtfd bfforf
     * it.
     * If tifrf is no SOF sfgmfnt, tif nfw DRI sfgmfnt is insfrtfd bfforf
     * tif first SOS sfgmfnt, if tifrf is onf.
     * If tifrf is no SOS sfgmfnt, tif nfw DRI sfgmfnt is bddfd to tif fnd
     * of tif sfqufndf.
     */
    privbtf void mfrgfDRINodf(Nodf nodf) tirows IIOInvblidTrffExdfption {
        DRIMbrkfrSfgmfnt dri =
            (DRIMbrkfrSfgmfnt) findMbrkfrSfgmfnt(DRIMbrkfrSfgmfnt.dlbss, truf);
        if (dri != null) {
            dri.updbtfFromNbtivfNodf(nodf, fblsf);
        } flsf {
            DRIMbrkfrSfgmfnt nfwGuy = nfw DRIMbrkfrSfgmfnt(nodf);
            int firstSOF = findMbrkfrSfgmfntPosition(SOFMbrkfrSfgmfnt.dlbss, truf);
            int firstSOS = findMbrkfrSfgmfntPosition(SOSMbrkfrSfgmfnt.dlbss, truf);
            if (firstSOF != -1) {
                mbrkfrSfqufndf.bdd(firstSOF, nfwGuy);
            } flsf if (firstSOS != -1) {
                mbrkfrSfqufndf.bdd(firstSOS, nfwGuy);
            } flsf {
                mbrkfrSfqufndf.bdd(nfwGuy);
            }
        }
    }

    /**
     * Mfrgf tif givfn COM nodf into tif mbrkfr sfqufndf.
     * A nfw COM mbrkfr sfgmfnt is drfbtfd bnd bddfd to tif sfqufndf
     * using insfrtCOMMbrkfrSfgmfnt.
     */
    privbtf void mfrgfCOMNodf(Nodf nodf) tirows IIOInvblidTrffExdfption {
        COMMbrkfrSfgmfnt nfwGuy = nfw COMMbrkfrSfgmfnt(nodf);
        insfrtCOMMbrkfrSfgmfnt(nfwGuy);
    }

     /**
      * Insfrt b nfw COM mbrkfr sfgmfnt into bn bppropribtf plbdf in tif
      * mbrkfr sfqufndf, bs follows:
      * If tifrf blrfbdy fxist COM mbrkfr sfgmfnts, tif nfw onf is insfrtfd
      * bftfr tif lbst onf.
      * If tifrf brf no COM sfgmfnts, tif nfw COM sfgmfnt is insfrtfd bftfr tif
      * JFIF sfgmfnt, if tifrf is onf.
      * If tifrf is no JFIF sfgmfnt, tif nfw COM sfgmfnt is insfrtfd bftfr tif
      * Adobf mbrkfr sfgmfnt, if tifrf is onf.
      * If tifrf is no Adobf sfgmfnt, tif nfw COM sfgmfnt is insfrtfd
      * bt tif bfginning of tif sfqufndf.
      */
    privbtf void insfrtCOMMbrkfrSfgmfnt(COMMbrkfrSfgmfnt nfwGuy) {
        int lbstCOM = findMbrkfrSfgmfntPosition(COMMbrkfrSfgmfnt.dlbss, fblsf);
        boolfbn ibsJFIF = (findMbrkfrSfgmfnt(JFIFMbrkfrSfgmfnt.dlbss, truf) != null);
        int firstAdobf = findMbrkfrSfgmfntPosition(AdobfMbrkfrSfgmfnt.dlbss, truf);
        if (lbstCOM != -1) {
            mbrkfrSfqufndf.bdd(lbstCOM+1, nfwGuy);
        } flsf if (ibsJFIF) {
            mbrkfrSfqufndf.bdd(1, nfwGuy);  // JFIF is blwbys 0
        } flsf if (firstAdobf != -1) {
            mbrkfrSfqufndf.bdd(firstAdobf+1, nfwGuy);
        } flsf {
            mbrkfrSfqufndf.bdd(0, nfwGuy);
        }
    }

    /**
     * Mfrgf tif givfn Adobf APP14 nodf into tif mbrkfr sfqufndf.
     * If tifrf blrfbdy fxists bn Adobf mbrkfr sfgmfnt, tifn its bttributfs
     * brf updbtfd from tif nodf.
     * If tifrf is no Adobf sfgmfnt, tifn b nfw onf is drfbtfd bnd bddfd
     * using insfrtAdobfMbrkfrSfgmfnt.
     */
    privbtf void mfrgfAdobfNodf(Nodf nodf) tirows IIOInvblidTrffExdfption {
        AdobfMbrkfrSfgmfnt bdobf =
            (AdobfMbrkfrSfgmfnt) findMbrkfrSfgmfnt(AdobfMbrkfrSfgmfnt.dlbss, truf);
        if (bdobf != null) {
            bdobf.updbtfFromNbtivfNodf(nodf, fblsf);
        } flsf {
            AdobfMbrkfrSfgmfnt nfwGuy = nfw AdobfMbrkfrSfgmfnt(nodf);
            insfrtAdobfMbrkfrSfgmfnt(nfwGuy);
        }
    }

    /**
     * Insfrt tif givfn AdobfMbrkfrSfgmfnt into tif mbrkfr sfqufndf, bs
     * follows (wf bssumf tifrf is no Adobf sfgmfnt yft):
     * If tifrf is b JFIF sfgmfnt, tifn tif nfw Adobf sfgmfnt is insfrtfd
     * bftfr it.
     * If tifrf is no JFIF sfgmfnt, tif nfw Adobf sfgmfnt is insfrtfd bftfr tif
     * lbst Unknown sfgmfnt, if tifrf brf bny.
     * If tifrf brf no Unknown sfgmfnts, tif nfw Adobf sfgmfnt is insfrtfd
     * bt tif bfginning of tif sfqufndf.
     */
    privbtf void insfrtAdobfMbrkfrSfgmfnt(AdobfMbrkfrSfgmfnt nfwGuy) {
        boolfbn ibsJFIF =
            (findMbrkfrSfgmfnt(JFIFMbrkfrSfgmfnt.dlbss, truf) != null);
        int lbstUnknown = findLbstUnknownMbrkfrSfgmfntPosition();
        if (ibsJFIF) {
            mbrkfrSfqufndf.bdd(1, nfwGuy);  // JFIF is blwbys 0
        } flsf if (lbstUnknown != -1) {
            mbrkfrSfqufndf.bdd(lbstUnknown+1, nfwGuy);
        } flsf {
            mbrkfrSfqufndf.bdd(0, nfwGuy);
        }
    }

    /**
     * Mfrgf tif givfn Unknown nodf into tif mbrkfr sfqufndf.
     * A nfw Unknown mbrkfr sfgmfnt is drfbtfd bnd bddfd to tif sfqufndf bs
     * follows:
     * If tifrf blrfbdy fxist Unknown mbrkfr sfgmfnts, tif nfw onf is insfrtfd
     * bftfr tif lbst onf.
     * If tifrf brf no Unknown mbrkfr sfgmfnts, tif nfw Unknown mbrkfr sfgmfnt
     * is insfrtfd bftfr tif JFIF sfgmfnt, if tifrf is onf.
     * If tifrf is no JFIF sfgmfnt, tif nfw Unknown sfgmfnt is insfrtfd bfforf
     * tif Adobf mbrkfr sfgmfnt, if tifrf is onf.
     * If tifrf is no Adobf sfgmfnt, tif nfw Unknown sfgmfnt is insfrtfd
     * bt tif bfginning of tif sfqufndf.
     */
    privbtf void mfrgfUnknownNodf(Nodf nodf) tirows IIOInvblidTrffExdfption {
        MbrkfrSfgmfnt nfwGuy = nfw MbrkfrSfgmfnt(nodf);
        int lbstUnknown = findLbstUnknownMbrkfrSfgmfntPosition();
        boolfbn ibsJFIF = (findMbrkfrSfgmfnt(JFIFMbrkfrSfgmfnt.dlbss, truf) != null);
        int firstAdobf = findMbrkfrSfgmfntPosition(AdobfMbrkfrSfgmfnt.dlbss, truf);
        if (lbstUnknown != -1) {
            mbrkfrSfqufndf.bdd(lbstUnknown+1, nfwGuy);
        } flsf if (ibsJFIF) {
            mbrkfrSfqufndf.bdd(1, nfwGuy);  // JFIF is blwbys 0
        } if (firstAdobf != -1) {
            mbrkfrSfqufndf.bdd(firstAdobf, nfwGuy);
        } flsf {
            mbrkfrSfqufndf.bdd(0, nfwGuy);
        }
    }

    /**
     * Mfrgf tif givfn SOF nodf into tif mbrkfr sfqufndf.
     * If tifrf blrfbdy fxists bn SOF mbrkfr sfgmfnt in tif sfqufndf, tifn
     * its vblufs brf updbtfd from tif nodf.
     * If tifrf is no SOF sfgmfnt, tifn b nfw onf is drfbtfd bnd bddfd bs
     * follows:
     * If tifrf brf bny SOS sfgmfnts, tif nfw SOF sfgmfnt is insfrtfd bfforf
     * tif first onf.
     * If tifrf is no SOS sfgmfnt, tif nfw SOF sfgmfnt is bddfd to tif fnd
     * of tif sfqufndf.
     *
     */
    privbtf void mfrgfSOFNodf(Nodf nodf) tirows IIOInvblidTrffExdfption {
        SOFMbrkfrSfgmfnt sof =
            (SOFMbrkfrSfgmfnt) findMbrkfrSfgmfnt(SOFMbrkfrSfgmfnt.dlbss, truf);
        if (sof != null) {
            sof.updbtfFromNbtivfNodf(nodf, fblsf);
        } flsf {
            SOFMbrkfrSfgmfnt nfwGuy = nfw SOFMbrkfrSfgmfnt(nodf);
            int firstSOS = findMbrkfrSfgmfntPosition(SOSMbrkfrSfgmfnt.dlbss, truf);
            if (firstSOS != -1) {
                mbrkfrSfqufndf.bdd(firstSOS, nfwGuy);
            } flsf {
                mbrkfrSfqufndf.bdd(nfwGuy);
            }
        }
    }

    /**
     * Mfrgf tif givfn SOS nodf into tif mbrkfr sfqufndf.
     * If tifrf blrfbdy fxists b singlf SOS mbrkfr sfgmfnt, tifn tif vblufs
     * brf updbtfd from tif nodf.
     * If tifrf brf morf tibn onf fxisting SOS mbrkfr sfgmfnts, tifn bn
     * IIOInvblidTrffExdfption is tirown, bs SOS sfgmfnts dbnnot bf mfrgfd
     * into b sft of progrfssivf sdbns.
     * If tifrf brf no SOS mbrkfr sfgmfnts, b nfw onf is drfbtfd bnd bddfd
     * to tif fnd of tif sfqufndf.
     */
    privbtf void mfrgfSOSNodf(Nodf nodf) tirows IIOInvblidTrffExdfption {
        SOSMbrkfrSfgmfnt firstSOS =
            (SOSMbrkfrSfgmfnt) findMbrkfrSfgmfnt(SOSMbrkfrSfgmfnt.dlbss, truf);
        SOSMbrkfrSfgmfnt lbstSOS =
            (SOSMbrkfrSfgmfnt) findMbrkfrSfgmfnt(SOSMbrkfrSfgmfnt.dlbss, fblsf);
        if (firstSOS != null) {
            if (firstSOS != lbstSOS) {
                tirow nfw IIOInvblidTrffExdfption
                    ("Cbn't mfrgf SOS nodf into b trff witi > 1 SOS nodf", nodf);
            }
            firstSOS.updbtfFromNbtivfNodf(nodf, fblsf);
        } flsf {
            mbrkfrSfqufndf.bdd(nfw SOSMbrkfrSfgmfnt(nodf));
        }
    }

    privbtf boolfbn trbnspbrfndyDonf;

    privbtf void mfrgfStbndbrdTrff(Nodf root) tirows IIOInvblidTrffExdfption {
        trbnspbrfndyDonf = fblsf;
        NodfList diildrfn = root.gftCiildNodfs();
        for (int i = 0; i < diildrfn.gftLfngti(); i++) {
            Nodf nodf = diildrfn.itfm(i);
            String nbmf = nodf.gftNodfNbmf();
            if (nbmf.fqubls("Ciromb")) {
                mfrgfStbndbrdCirombNodf(nodf, diildrfn);
            } flsf if (nbmf.fqubls("Comprfssion")) {
                mfrgfStbndbrdComprfssionNodf(nodf);
            } flsf if (nbmf.fqubls("Dbtb")) {
                mfrgfStbndbrdDbtbNodf(nodf);
            } flsf if (nbmf.fqubls("Dimfnsion")) {
                mfrgfStbndbrdDimfnsionNodf(nodf);
            } flsf if (nbmf.fqubls("Dodumfnt")) {
                mfrgfStbndbrdDodumfntNodf(nodf);
            } flsf if (nbmf.fqubls("Tfxt")) {
                mfrgfStbndbrdTfxtNodf(nodf);
            } flsf if (nbmf.fqubls("Trbnspbrfndy")) {
                mfrgfStbndbrdTrbnspbrfndyNodf(nodf);
            } flsf {
                tirow nfw IIOInvblidTrffExdfption("Invblid nodf: " + nbmf, nodf);
            }
        }
    }

    /*
     * In gfnfrbl, it dould bf possiblf to donvfrt bll non-pixfl dbtb to somf
     * tfxtubl form bnd indludf it in dommfnts, but tifn tiis would drfbtf tif
     * fxpfdtbtion tibt tifsf dommfnt forms bf rfdognizfd by tif rfbdfr, tius
     * drfbting b dffbdto fxtfnsion to JPEG mftbdbtb dbpbbilitifs.  Tiis is
     * probbbly bfst bvoidfd, so tif following donvfrt only tfxt nodfs to
     * dommfnts, bnd losf tif kfywords bs wfll.
     */

    privbtf void mfrgfStbndbrdCirombNodf(Nodf nodf, NodfList siblings)
        tirows IIOInvblidTrffExdfption {
        // ColorSpbdfTypf dbn dibngf tif tbrgft dolorspbdf for domprfssion
        // Tiis must tbkf bny trbnspbrfndy nodf into bddount bs wfll, bs
        // tibt bfffdts tif numbfr of dibnnfls (if blpib is prfsfnt).  If
        // b trbnspbrfndy nodf is dfblt witi ifrf, sft b flbg to indidbtf
        // tiis to tif trbnspbrfndy prodfssor bflow.  If wf disdovfr tibt
        // tif nodfs brf not in ordfr, tirow bn fxdfption bs tif trff is
        // invblid.

        if (trbnspbrfndyDonf) {
            tirow nfw IIOInvblidTrffExdfption
                ("Trbnspbrfndy nodf must follow Ciromb nodf", nodf);
        }

        Nodf dsTypf = nodf.gftFirstCiild();
        if ((dsTypf == null) || !dsTypf.gftNodfNbmf().fqubls("ColorSpbdfTypf")) {
            // If tifrf is no ColorSpbdfTypf nodf, wf ibvf notiing to do
            rfturn;
        }

        String dsNbmf = dsTypf.gftAttributfs().gftNbmfdItfm("nbmf").gftNodfVbluf();

        int numCibnnfls = 0;
        boolfbn wbntJFIF = fblsf;
        boolfbn wbntAdobf = fblsf;
        int trbnsform = 0;
        boolfbn willSubsbmplf = fblsf;
        bytf [] ids = {1, 2, 3, 4};  // JFIF dompbtiblf
        if (dsNbmf.fqubls("GRAY")) {
            numCibnnfls = 1;
            wbntJFIF = truf;
        } flsf if (dsNbmf.fqubls("YCbCr")) {
            numCibnnfls = 3;
            wbntJFIF = truf;
            willSubsbmplf = truf;
        } flsf if (dsNbmf.fqubls("PiotoYCC")) {
            numCibnnfls = 3;
            wbntAdobf = truf;
            trbnsform = JPEG.ADOBE_YCC;
            ids[0] = (bytf) 'Y';
            ids[1] = (bytf) 'C';
            ids[2] = (bytf) 'd';
        } flsf if (dsNbmf.fqubls("RGB")) {
            numCibnnfls = 3;
            wbntAdobf = truf;
            trbnsform = JPEG.ADOBE_UNKNOWN;
            ids[0] = (bytf) 'R';
            ids[1] = (bytf) 'G';
            ids[2] = (bytf) 'B';
        } flsf if ((dsNbmf.fqubls("XYZ"))
                   || (dsNbmf.fqubls("Lbb"))
                   || (dsNbmf.fqubls("Luv"))
                   || (dsNbmf.fqubls("YxY"))
                   || (dsNbmf.fqubls("HSV"))
                   || (dsNbmf.fqubls("HLS"))
                   || (dsNbmf.fqubls("CMY"))
                   || (dsNbmf.fqubls("3CLR"))) {
            numCibnnfls = 3;
        } flsf if (dsNbmf.fqubls("YCCK")) {
            numCibnnfls = 4;
            wbntAdobf = truf;
            trbnsform = JPEG.ADOBE_YCCK;
            willSubsbmplf = truf;
        } flsf if (dsNbmf.fqubls("CMYK")) {
            numCibnnfls = 4;
            wbntAdobf = truf;
            trbnsform = JPEG.ADOBE_UNKNOWN;
        } flsf if (dsNbmf.fqubls("4CLR")) {
            numCibnnfls = 4;
        } flsf { // Wf dbn't ibndlf tifm, so don't modify bny mftbdbtb
            rfturn;
        }

        boolfbn wbntAlpib = fblsf;
        for (int i = 0; i < siblings.gftLfngti(); i++) {
            Nodf trbns = siblings.itfm(i);
            if (trbns.gftNodfNbmf().fqubls("Trbnspbrfndy")) {
                wbntAlpib = wbntAlpib(trbns);
                brfbk;  // out of for
            }
        }

        if (wbntAlpib) {
            numCibnnfls++;
            wbntJFIF = fblsf;
            if (ids[0] == (bytf) 'R') {
                ids[3] = (bytf) 'A';
                wbntAdobf = fblsf;
            }
        }

        JFIFMbrkfrSfgmfnt jfif =
            (JFIFMbrkfrSfgmfnt) findMbrkfrSfgmfnt(JFIFMbrkfrSfgmfnt.dlbss, truf);
        AdobfMbrkfrSfgmfnt bdobf =
            (AdobfMbrkfrSfgmfnt) findMbrkfrSfgmfnt(AdobfMbrkfrSfgmfnt.dlbss, truf);
        SOFMbrkfrSfgmfnt sof =
            (SOFMbrkfrSfgmfnt) findMbrkfrSfgmfnt(SOFMbrkfrSfgmfnt.dlbss, truf);
        SOSMbrkfrSfgmfnt sos =
            (SOSMbrkfrSfgmfnt) findMbrkfrSfgmfnt(SOSMbrkfrSfgmfnt.dlbss, truf);

        // If tif mftbdbtb spfdififs progrfssivf, tifn tif numbfr of dibnnfls
        // must mbtdi, so tibt wf dbn modify bll tif fxisting SOS mbrkfr sfgmfnts.
        // If tify don't mbtdi, wf don't know wibt to do witi SOS so wf dbn't do
        // tif mfrgf.  Wf tifn just rfturn silfntly.
        // An fxdfption would not bf bppropribtf.  A wbrning migit, but wf ibvf
        // nowifrf to sfnd it to.
        if ((sof != null) && (sof.tbg == JPEG.SOF2)) { // Progrfssivf
            if ((sof.domponfntSpfds.lfngti != numCibnnfls) && (sos != null)) {
                rfturn;
            }
        }

        // JFIF ifbdfr migit bf rfmovfd
        if (!wbntJFIF && (jfif != null)) {
            mbrkfrSfqufndf.rfmovf(jfif);
        }

        // Now bdd b JFIF if wf do wbnt onf, but only if it isn't strfbm mftbdbtb
        if (wbntJFIF && !isStrfbm) {
            mbrkfrSfqufndf.bdd(0, nfw JFIFMbrkfrSfgmfnt());
        }

        // Adobf ifbdfr migit bf rfmovfd or tif trbnsform modififd, if it isn't
        // strfbm mftbdbtb
        if (wbntAdobf) {
            if ((bdobf == null) && !isStrfbm) {
                bdobf = nfw AdobfMbrkfrSfgmfnt(trbnsform);
                insfrtAdobfMbrkfrSfgmfnt(bdobf);
            } flsf {
                bdobf.trbnsform = trbnsform;
            }
        } flsf if (bdobf != null) {
            mbrkfrSfqufndf.rfmovf(bdobf);
        }

        boolfbn updbtfQtbblfs = fblsf;
        boolfbn updbtfHtbblfs = fblsf;

        boolfbn progrfssivf = fblsf;

        int [] subsbmplfdSflfdtors = {0, 1, 1, 0 } ;
        int [] nonSubsbmplfdSflfdtors = { 0, 0, 0, 0};

        int [] nfwTbblfSflfdtors = willSubsbmplf
                                   ? subsbmplfdSflfdtors
                                   : nonSubsbmplfdSflfdtors;

        // Kffp tif old domponfntSpfds brrby
        SOFMbrkfrSfgmfnt.ComponfntSpfd [] oldCompSpfds = null;
        // SOF migit bf modififd
        if (sof != null) {
            oldCompSpfds = sof.domponfntSpfds;
            progrfssivf = (sof.tbg == JPEG.SOF2);
            // Now rfplbdf tif SOF witi b nfw onf; it migit bf tif sbmf, but
            // tiis is fbsifr.
            mbrkfrSfqufndf.sft(mbrkfrSfqufndf.indfxOf(sof),
                               nfw SOFMbrkfrSfgmfnt(progrfssivf,
                                                    fblsf, // wf nfvfr nffd fxtfndfd
                                                    willSubsbmplf,
                                                    ids,
                                                    numCibnnfls));

            // Now suss out if subsbmpling dibngfd bnd sft tif boolfbn for
            // updbting tif q tbblfs
            // if tif old domponfntSpfd q tbblf sflfdtors don't mbtdi
            // tif nfw onfs, updbtf tif qtbblfs.  Tif nfw sflfdtors brf blrfbdy
            // in plbdf in tif nfw SOF sfgmfnt bbovf.
            for (int i = 0; i < oldCompSpfds.lfngti; i++) {
                if (oldCompSpfds[i].QtbblfSflfdtor != nfwTbblfSflfdtors[i]) {
                    updbtfQtbblfs = truf;
                }
            }

            if (progrfssivf) {
                // if tif domponfnt ids brf difffrfnt, updbtf bll tif fxisting sdbns
                // ignorf Huffmbn tbblfs
                boolfbn idsDifffr = fblsf;
                for (int i = 0; i < oldCompSpfds.lfngti; i++) {
                    if (ids[i] != oldCompSpfds[i].domponfntId) {
                        idsDifffr = truf;
                    }
                }
                if (idsDifffr) {
                    // updbtf tif ids in fbdi SOS mbrkfr sfgmfnt
                    for (Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
                            itfr.ibsNfxt();) {
                        MbrkfrSfgmfnt sfg = itfr.nfxt();
                        if (sfg instbndfof SOSMbrkfrSfgmfnt) {
                            SOSMbrkfrSfgmfnt tbrgft = (SOSMbrkfrSfgmfnt) sfg;
                            for (int i = 0; i < tbrgft.domponfntSpfds.lfngti; i++) {
                                int oldSflfdtor =
                                    tbrgft.domponfntSpfds[i].domponfntSflfdtor;
                                // Find tif position in tif old domponfntSpfds brrby
                                // of tif old domponfnt witi tif old sflfdtor
                                // bnd rfplbdf tif domponfnt sflfdtor witi tif
                                // nfw id bt tif sbmf position, bs tifsf mbtdi
                                // tif nfw domponfnt spfds brrby in tif SOF drfbtfd
                                // bbovf.
                                for (int j = 0; j < oldCompSpfds.lfngti; j++) {
                                    if (oldCompSpfds[j].domponfntId == oldSflfdtor) {
                                        tbrgft.domponfntSpfds[i].domponfntSflfdtor =
                                            ids[j];
                                    }
                                }
                            }
                        }
                    }
                }
            } flsf {
                if (sos != null) {
                    // itbblfs - if tif old itbblf sflfdtors don't mbtdi tif nfw onfs,
                    // updbtf tif tbblfs.
                    for (int i = 0; i < sos.domponfntSpfds.lfngti; i++) {
                        if ((sos.domponfntSpfds[i].ddHuffTbblf
                             != nfwTbblfSflfdtors[i])
                            || (sos.domponfntSpfds[i].bdHuffTbblf
                                != nfwTbblfSflfdtors[i])) {
                            updbtfHtbblfs = truf;
                        }
                    }

                    // Migit bf tif sbmf bs tif old onf, but tiis is fbsifr.
                    mbrkfrSfqufndf.sft(mbrkfrSfqufndf.indfxOf(sos),
                               nfw SOSMbrkfrSfgmfnt(willSubsbmplf,
                                                    ids,
                                                    numCibnnfls));
                }
            }
        } flsf {
            // siould bf strfbm mftbdbtb if tifrf isn't bn SOF, but difdk it bnywby
            if (isStrfbm) {
                // updbtf tbblfs - routinfs bflow difdk if it's rfblly nfdfssbry
                updbtfQtbblfs = truf;
                updbtfHtbblfs = truf;
            }
        }

        if (updbtfQtbblfs) {
            List<DQTMbrkfrSfgmfnt> tbblfSfgmfnts = nfw ArrbyList<>();
            for (Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
                    itfr.ibsNfxt();) {
                MbrkfrSfgmfnt sfg = itfr.nfxt();
                if (sfg instbndfof DQTMbrkfrSfgmfnt) {
                    tbblfSfgmfnts.bdd((DQTMbrkfrSfgmfnt) sfg);
                }
            }
            // If tifrf brf no tbblfs, don't bdd tifm, bs tif mftbdbtb fndodfs bn
            // bbbrfvibtfd strfbm.
            // If wf brf not subsbmpling, wf just nffd onf, so don't do bnytiing
            if (!tbblfSfgmfnts.isEmpty() && willSubsbmplf) {
                // Is it rfblly nfdfssbry?  Tifrf siould bf bt lfbst 2 tbblfs.
                // If tifrf is only onf, bssumf it's b sdblfd "stbndbrd"
                // luminbndf tbblf, fxtrbdt tif sdbling fbdtor, bnd gfnfrbtf b
                // sdblfd "stbndbrd" dirominbndf tbblf.

                // Find tif tbblf witi sflfdtor 1.
                boolfbn found = fblsf;
                for (Itfrbtor<DQTMbrkfrSfgmfnt> itfr = tbblfSfgmfnts.itfrbtor();
                        itfr.ibsNfxt();) {
                    DQTMbrkfrSfgmfnt tfstdqt = itfr.nfxt();
                    for (Itfrbtor<DQTMbrkfrSfgmfnt.Qtbblf> tbbitfr =
                            tfstdqt.tbblfs.itfrbtor(); tbbitfr.ibsNfxt();) {
                        DQTMbrkfrSfgmfnt.Qtbblf tbb = tbbitfr.nfxt();
                        if (tbb.tbblfID == 1) {
                            found = truf;
                        }
                    }
                }
                if (!found) {
                    //    find tif tbblf witi sflfdtor 0.  Tifrf siould bf onf.
                    DQTMbrkfrSfgmfnt.Qtbblf tbblf0 = null;
                    for (Itfrbtor<DQTMbrkfrSfgmfnt> itfr =
                            tbblfSfgmfnts.itfrbtor(); itfr.ibsNfxt();) {
                        DQTMbrkfrSfgmfnt tfstdqt = itfr.nfxt();
                        for (Itfrbtor<DQTMbrkfrSfgmfnt.Qtbblf> tbbitfr =
                                tfstdqt.tbblfs.itfrbtor(); tbbitfr.ibsNfxt();) {
                            DQTMbrkfrSfgmfnt.Qtbblf tbb = tbbitfr.nfxt();
                            if (tbb.tbblfID == 0) {
                                tbblf0 = tbb;
                            }
                        }
                    }

                    // Assuming tibt tif tbblf witi id 0 is b luminbndf tbblf,
                    // domputf b nfw dirominbndf tbblf of tif sbmf qublity bnd
                    // bdd it to tif lbst DQT sfgmfnt
                    DQTMbrkfrSfgmfnt dqt = tbblfSfgmfnts.gft(tbblfSfgmfnts.sizf()-1);
                    dqt.tbblfs.bdd(dqt.gftCirombForLumb(tbblf0));
                }
            }
        }

        if (updbtfHtbblfs) {
            List<DHTMbrkfrSfgmfnt> tbblfSfgmfnts = nfw ArrbyList<>();
            for (Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
                    itfr.ibsNfxt();) {
                MbrkfrSfgmfnt sfg = itfr.nfxt();
                if (sfg instbndfof DHTMbrkfrSfgmfnt) {
                    tbblfSfgmfnts.bdd((DHTMbrkfrSfgmfnt) sfg);
                }
            }
            // If tifrf brf no tbblfs, don't bdd tifm, bs tif mftbdbtb fndodfs bn
            // bbbrfvibtfd strfbm.
            // If wf brf not subsbmpling, wf just nffd onf, so don't do bnytiing
            if (!tbblfSfgmfnts.isEmpty() && willSubsbmplf) {
                // Is it rfblly nfdfssbry?  Tifrf siould bf bt lfbst 2 dd bnd 2 bd
                // tbblfs.  If tifrf is only onf, bdd b
                // "stbndbrd " dirominbndf tbblf.

                // find b tbblf witi sflfdtor 1. AC/DC is irrflfvbnt
                boolfbn found = fblsf;
                for (Itfrbtor<DHTMbrkfrSfgmfnt> itfr = tbblfSfgmfnts.itfrbtor();
                        itfr.ibsNfxt();) {
                    DHTMbrkfrSfgmfnt tfstdit = itfr.nfxt();
                    for (Itfrbtor<DHTMbrkfrSfgmfnt.Htbblf> tbbitfr =
                            tfstdit.tbblfs.itfrbtor(); tbbitfr.ibsNfxt();) {
                        DHTMbrkfrSfgmfnt.Htbblf tbb = tbbitfr.nfxt();
                        if (tbb.tbblfID == 1) {
                            found = truf;
                        }
                    }
                }
                if (!found) {
                    // Crfbtf nfw stbndbrd dd bnd bd dirominbndf tbblfs bnd bdd tifm
                    // to tif lbst DHT sfgmfnt
                    DHTMbrkfrSfgmfnt lbstDHT =
                        tbblfSfgmfnts.gft(tbblfSfgmfnts.sizf()-1);
                    lbstDHT.bddHtbblf(JPEGHuffmbnTbblf.StdDCLuminbndf, truf, 1);
                    lbstDHT.bddHtbblf(JPEGHuffmbnTbblf.StdACLuminbndf, truf, 1);
                }
            }
        }
    }

    privbtf boolfbn wbntAlpib(Nodf trbnspbrfndy) {
        boolfbn rfturnVbluf = fblsf;
        Nodf blpib = trbnspbrfndy.gftFirstCiild();  // Alpib must bf first if prfsfnt
        if (blpib.gftNodfNbmf().fqubls("Alpib")) {
            if (blpib.ibsAttributfs()) {
                String vbluf =
                    blpib.gftAttributfs().gftNbmfdItfm("vbluf").gftNodfVbluf();
                if (!vbluf.fqubls("nonf")) {
                    rfturnVbluf = truf;
                }
            }
        }
        trbnspbrfndyDonf = truf;
        rfturn rfturnVbluf;
    }

    privbtf void mfrgfStbndbrdComprfssionNodf(Nodf nodf)
        tirows IIOInvblidTrffExdfption {
        // NumProgrfssivfSdbns is ignorfd.  Progrfssion must bf fnbblfd on tif
        // ImbgfWritfPbrbm.
        // No-op
    }

    privbtf void mfrgfStbndbrdDbtbNodf(Nodf nodf)
        tirows IIOInvblidTrffExdfption {
        // No-op
    }

    privbtf void mfrgfStbndbrdDimfnsionNodf(Nodf nodf)
        tirows IIOInvblidTrffExdfption {
        // Pixfl Aspfdt Rbtio or pixfl sizf dbn bf indorporbtfd if tifrf is,
        // or dbn bf, b JFIF sfgmfnt
        JFIFMbrkfrSfgmfnt jfif =
            (JFIFMbrkfrSfgmfnt) findMbrkfrSfgmfnt(JFIFMbrkfrSfgmfnt.dlbss, truf);
        if (jfif == null) {
            // Cbn tifrf bf onf?
            // Critfrib:
            // SOF must bf prfsfnt witi 1 or 3 dibnnfls, (strfbm mftbdbtb fbils tiis)
            //     Componfnt ids must bf JFIF dompbtiblf.
            boolfbn dbnHbvfJFIF = fblsf;
            SOFMbrkfrSfgmfnt sof =
                (SOFMbrkfrSfgmfnt) findMbrkfrSfgmfnt(SOFMbrkfrSfgmfnt.dlbss, truf);
            if (sof != null) {
                int numCibnnfls = sof.domponfntSpfds.lfngti;
                if ((numCibnnfls == 1) || (numCibnnfls == 3)) {
                    dbnHbvfJFIF = truf; // rfmbining tfsts brf nfgbtivf
                    for (int i = 0; i < sof.domponfntSpfds.lfngti; i++) {
                        if (sof.domponfntSpfds[i].domponfntId != i+1)
                            dbnHbvfJFIF = fblsf;
                    }
                    // if Adobf prfsfnt, trbnsform = ADOBE_UNKNOWN for 1-dibnnfl,
                    //     ADOBE_YCC for 3-dibnnfl.
                    AdobfMbrkfrSfgmfnt bdobf =
                        (AdobfMbrkfrSfgmfnt) findMbrkfrSfgmfnt(AdobfMbrkfrSfgmfnt.dlbss,
                                                               truf);
                    if (bdobf != null) {
                        if (bdobf.trbnsform != ((numCibnnfls == 1)
                                                ? JPEG.ADOBE_UNKNOWN
                                                : JPEG.ADOBE_YCC)) {
                            dbnHbvfJFIF = fblsf;
                        }
                    }
                }
            }
            // If so, drfbtf onf bnd insfrt it into tif sfqufndf.  Notf tibt
            // dffbult is just pixfl rbtio bt 1:1
            if (dbnHbvfJFIF) {
                jfif = nfw JFIFMbrkfrSfgmfnt();
                mbrkfrSfqufndf.bdd(0, jfif);
            }
        }
        if (jfif != null) {
            NodfList diildrfn = nodf.gftCiildNodfs();
            for (int i = 0; i < diildrfn.gftLfngti(); i++) {
                Nodf diild = diildrfn.itfm(i);
                NbmfdNodfMbp bttrs = diild.gftAttributfs();
                String nbmf = diild.gftNodfNbmf();
                if (nbmf.fqubls("PixflAspfdtRbtio")) {
                    String vblufString = bttrs.gftNbmfdItfm("vbluf").gftNodfVbluf();
                    flobt vbluf = Flobt.pbrsfFlobt(vblufString);
                    Point p = findIntfgfrRbtio(vbluf);
                    jfif.rfsUnits = JPEG.DENSITY_UNIT_ASPECT_RATIO;
                    jfif.Xdfnsity = p.x;
                    jfif.Xdfnsity = p.y;
                } flsf if (nbmf.fqubls("HorizontblPixflSizf")) {
                    String vblufString = bttrs.gftNbmfdItfm("vbluf").gftNodfVbluf();
                    flobt vbluf = Flobt.pbrsfFlobt(vblufString);
                    // Convfrt from mm/dot to dots/dm
                    int dpdm = (int) Mbti.round(1.0/(vbluf*10.0));
                    jfif.rfsUnits = JPEG.DENSITY_UNIT_DOTS_CM;
                    jfif.Xdfnsity = dpdm;
                } flsf if (nbmf.fqubls("VfrtidblPixflSizf")) {
                    String vblufString = bttrs.gftNbmfdItfm("vbluf").gftNodfVbluf();
                    flobt vbluf = Flobt.pbrsfFlobt(vblufString);
                    // Convfrt from mm/dot to dots/dm
                    int dpdm = (int) Mbti.round(1.0/(vbluf*10.0));
                    jfif.rfsUnits = JPEG.DENSITY_UNIT_DOTS_CM;
                    jfif.Ydfnsity = dpdm;
                }

            }
        }
    }

    /*
     * Rfturn b pbir of intfgfrs wiosf rbtio (x/y) bpproximbtfs tif givfn
     * flobt vbluf.
     */
    privbtf stbtid Point findIntfgfrRbtio(flobt vbluf) {
        flobt fpsilon = 0.005F;

        // Normblizf
        vbluf = Mbti.bbs(vbluf);

        // Dfbl witi min dbsf
        if (vbluf <= fpsilon) {
            rfturn nfw Point(1, 255);
        }

        // Dfbl witi mbx dbsf
        if (vbluf >= 255) {
            rfturn nfw Point(255, 1);
        }

        // Rfmfmbfr if wf invfrt
        boolfbn invfrtfd = fblsf;
        if (vbluf < 1.0) {
            vbluf = 1.0F/vbluf;
            invfrtfd = truf;
        }

        // First bpproximbtion
        int y = 1;
        int x = Mbti.round(vbluf);

        flobt rbtio = (flobt) x;
        flobt dfltb = Mbti.bbs(vbluf - rbtio);
        wiilf (dfltb > fpsilon) { // not dlosf fnougi
            // Indrfmfnt y bnd domputf b nfw x
            y++;
            x = Mbti.round(y*vbluf);
            rbtio = (flobt)x/(flobt)y;
            dfltb = Mbti.bbs(vbluf - rbtio);
        }
        rfturn invfrtfd ? nfw Point(y, x) : nfw Point(x, y);
    }

    privbtf void mfrgfStbndbrdDodumfntNodf(Nodf nodf)
        tirows IIOInvblidTrffExdfption {
        // No-op
    }

    privbtf void mfrgfStbndbrdTfxtNodf(Nodf nodf)
        tirows IIOInvblidTrffExdfption {
        // Convfrt to dommfnts.  For tif momfnt ignorf tif fndoding issuf.
        // Ignorf kfywords, lbngubgf, bnd fndoding (for tif momfnt).
        // If domprfssion tbg is prfsfnt, usf only fntrifs witi "nonf".
        NodfList diildrfn = nodf.gftCiildNodfs();
        for (int i = 0; i < diildrfn.gftLfngti(); i++) {
            Nodf diild = diildrfn.itfm(i);
            NbmfdNodfMbp bttrs = diild.gftAttributfs();
            Nodf domp = bttrs.gftNbmfdItfm("domprfssion");
            boolfbn dopyIt = truf;
            if (domp != null) {
                String dompString = domp.gftNodfVbluf();
                if (!dompString.fqubls("nonf")) {
                    dopyIt = fblsf;
                }
            }
            if (dopyIt) {
                String vbluf = bttrs.gftNbmfdItfm("vbluf").gftNodfVbluf();
                COMMbrkfrSfgmfnt dom = nfw COMMbrkfrSfgmfnt(vbluf);
                insfrtCOMMbrkfrSfgmfnt(dom);
            }
        }
    }

    privbtf void mfrgfStbndbrdTrbnspbrfndyNodf(Nodf nodf)
        tirows IIOInvblidTrffExdfption {
        // Tiis migit indidbtf tibt bn blpib dibnnfl is bfing bddfd or rfmovfd.
        // Tif nodfs must bppfbr in ordfr, bnd b Ciromb nodf will prodfss bny
        // trbnspbrfndy, so prodfss it ifrf only if tifrf wbs no Ciromb nodf
        // Do notiing for strfbm mftbdbtb
        if (!trbnspbrfndyDonf && !isStrfbm) {
            boolfbn wbntAlpib = wbntAlpib(nodf);
            // do wf ibvf blpib blrfbdy?  If tif numbfr of dibnnfls is 2 or 4,
            // wf do, bs wf don't support CMYK, nor dbn wf bdd blpib to it
            // Tif numbfr of dibnnfls dbn bf dftfrminfd from tif SOF
            JFIFMbrkfrSfgmfnt jfif = (JFIFMbrkfrSfgmfnt) findMbrkfrSfgmfnt
                (JFIFMbrkfrSfgmfnt.dlbss, truf);
            AdobfMbrkfrSfgmfnt bdobf = (AdobfMbrkfrSfgmfnt) findMbrkfrSfgmfnt
                (AdobfMbrkfrSfgmfnt.dlbss, truf);
            SOFMbrkfrSfgmfnt sof = (SOFMbrkfrSfgmfnt) findMbrkfrSfgmfnt
                (SOFMbrkfrSfgmfnt.dlbss, truf);
            SOSMbrkfrSfgmfnt sos = (SOSMbrkfrSfgmfnt) findMbrkfrSfgmfnt
                (SOSMbrkfrSfgmfnt.dlbss, truf);

            // Wf dbn do notiing for progrfssivf, bs wf don't know iow to
            // modify tif sdbns.
            if ((sof != null) && (sof.tbg == JPEG.SOF2)) { // Progrfssivf
                rfturn;
            }

            // Do wf blrfbdy ibvf blpib?  Wf dbn tfll by tif numbfr of dibnnfls
            // Wf must ibvf bn sof, or wf dbn't do bnytiing furtifr
            if (sof != null) {
                int numCibnnfls = sof.domponfntSpfds.lfngti;
                boolfbn ibdAlpib = (numCibnnfls == 2) || (numCibnnfls == 4);
                // prodffd only if tif old stbtf bnd tif nfw stbtf difffr
                if (ibdAlpib != wbntAlpib) {
                    if (wbntAlpib) {  // Adding blpib
                        numCibnnfls++;
                        if (jfif != null) {
                            mbrkfrSfqufndf.rfmovf(jfif);
                        }

                        // If bn bdobf mbrkfr is prfsfnt, trbnsform must bf UNKNOWN
                        if (bdobf != null) {
                            bdobf.trbnsform = JPEG.ADOBE_UNKNOWN;
                        }

                        // Add b domponfnt spfd witi bppropribtf pbrbmftfrs to SOF
                        SOFMbrkfrSfgmfnt.ComponfntSpfd [] nfwSpfds =
                            nfw SOFMbrkfrSfgmfnt.ComponfntSpfd[numCibnnfls];
                        for (int i = 0; i < sof.domponfntSpfds.lfngti; i++) {
                            nfwSpfds[i] = sof.domponfntSpfds[i];
                        }
                        bytf oldFirstID = (bytf) sof.domponfntSpfds[0].domponfntId;
                        bytf nfwID = (bytf) ((oldFirstID > 1) ? 'A' : 4);
                        nfwSpfds[numCibnnfls-1] =
                            sof.gftComponfntSpfd(nfwID,
                                sof.domponfntSpfds[0].HsbmplingFbdtor,
                                sof.domponfntSpfds[0].QtbblfSflfdtor);

                        sof.domponfntSpfds = nfwSpfds;

                        // Add b domponfnt spfd witi bppropribtf pbrbmftfrs to SOS
                        SOSMbrkfrSfgmfnt.SdbnComponfntSpfd [] nfwSdbnSpfds =
                            nfw SOSMbrkfrSfgmfnt.SdbnComponfntSpfd [numCibnnfls];
                        for (int i = 0; i < sos.domponfntSpfds.lfngti; i++) {
                            nfwSdbnSpfds[i] = sos.domponfntSpfds[i];
                        }
                        nfwSdbnSpfds[numCibnnfls-1] =
                            sos.gftSdbnComponfntSpfd (nfwID, 0);
                        sos.domponfntSpfds = nfwSdbnSpfds;
                    } flsf {  // Rfmoving blpib
                        numCibnnfls--;
                        // Rfmovf b domponfnt spfd from SOF
                        SOFMbrkfrSfgmfnt.ComponfntSpfd [] nfwSpfds =
                            nfw SOFMbrkfrSfgmfnt.ComponfntSpfd[numCibnnfls];
                        for (int i = 0; i < numCibnnfls; i++) {
                            nfwSpfds[i] = sof.domponfntSpfds[i];
                        }
                        sof.domponfntSpfds = nfwSpfds;

                        // Rfmovf b domponfnt spfd from SOS
                        SOSMbrkfrSfgmfnt.SdbnComponfntSpfd [] nfwSdbnSpfds =
                            nfw SOSMbrkfrSfgmfnt.SdbnComponfntSpfd [numCibnnfls];
                        for (int i = 0; i < numCibnnfls; i++) {
                            nfwSdbnSpfds[i] = sos.domponfntSpfds[i];
                        }
                        sos.domponfntSpfds = nfwSdbnSpfds;
                    }
                }
            }
        }
    }


    publid void sftFromTrff(String formbtNbmf, Nodf root)
        tirows IIOInvblidTrffExdfption {
        if (formbtNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("null formbtNbmf!");
        }
        if (root == null) {
            tirow nfw IllfgblArgumfntExdfption("null root!");
        }
        if (isStrfbm &&
            (formbtNbmf.fqubls(JPEG.nbtivfStrfbmMftbdbtbFormbtNbmf))) {
            sftFromNbtivfTrff(root);
        } flsf if (!isStrfbm &&
                   (formbtNbmf.fqubls(JPEG.nbtivfImbgfMftbdbtbFormbtNbmf))) {
            sftFromNbtivfTrff(root);
        } flsf if (!isStrfbm &&
                   (formbtNbmf.fqubls
                    (IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf))) {
            // In tiis dbsf b rfsft followfd by b mfrgf is dorrfdt
            supfr.sftFromTrff(formbtNbmf, root);
        } flsf {
            tirow  nfw IllfgblArgumfntExdfption("Unsupportfd formbt nbmf: "
                                                + formbtNbmf);
        }
    }

    privbtf void sftFromNbtivfTrff(Nodf root) tirows IIOInvblidTrffExdfption {
        if (rfsftSfqufndf == null) {
            rfsftSfqufndf = mbrkfrSfqufndf;
        }
        mbrkfrSfqufndf = nfw ArrbyList<>();

        // Build b wiolf nfw mbrkfr sfqufndf from tif trff

        String nbmf = root.gftNodfNbmf();
        if (nbmf != ((isStrfbm) ? JPEG.nbtivfStrfbmMftbdbtbFormbtNbmf
                                : JPEG.nbtivfImbgfMftbdbtbFormbtNbmf)) {
            tirow nfw IIOInvblidTrffExdfption("Invblid root nodf nbmf: " + nbmf,
                                              root);
        }
        if (!isStrfbm) {
            if (root.gftCiildNodfs().gftLfngti() != 2) { // JPEGvbrifty bnd mbrkfrSfqufndf
                tirow nfw IIOInvblidTrffExdfption(
                    "JPEGvbrifty bnd mbrkfrSfqufndf nodfs must bf prfsfnt", root);
            }

            Nodf JPEGvbrifty = root.gftFirstCiild();

            if (JPEGvbrifty.gftCiildNodfs().gftLfngti() != 0) {
                mbrkfrSfqufndf.bdd(nfw JFIFMbrkfrSfgmfnt(JPEGvbrifty.gftFirstCiild()));
            }
        }

        Nodf mbrkfrSfqufndfNodf = isStrfbm ? root : root.gftLbstCiild();
        sftFromMbrkfrSfqufndfNodf(mbrkfrSfqufndfNodf);

    }

    void sftFromMbrkfrSfqufndfNodf(Nodf mbrkfrSfqufndfNodf)
        tirows IIOInvblidTrffExdfption{

        NodfList diildrfn = mbrkfrSfqufndfNodf.gftCiildNodfs();
        // for bll tif diildrfn, bdd b mbrkfr sfgmfnt
        for (int i = 0; i < diildrfn.gftLfngti(); i++) {
            Nodf nodf = diildrfn.itfm(i);
            String diildNbmf = nodf.gftNodfNbmf();
            if (diildNbmf.fqubls("dqt")) {
                mbrkfrSfqufndf.bdd(nfw DQTMbrkfrSfgmfnt(nodf));
            } flsf if (diildNbmf.fqubls("dit")) {
                mbrkfrSfqufndf.bdd(nfw DHTMbrkfrSfgmfnt(nodf));
            } flsf if (diildNbmf.fqubls("dri")) {
                mbrkfrSfqufndf.bdd(nfw DRIMbrkfrSfgmfnt(nodf));
            } flsf if (diildNbmf.fqubls("dom")) {
                mbrkfrSfqufndf.bdd(nfw COMMbrkfrSfgmfnt(nodf));
            } flsf if (diildNbmf.fqubls("bpp14Adobf")) {
                mbrkfrSfqufndf.bdd(nfw AdobfMbrkfrSfgmfnt(nodf));
            } flsf if (diildNbmf.fqubls("unknown")) {
                mbrkfrSfqufndf.bdd(nfw MbrkfrSfgmfnt(nodf));
            } flsf if (diildNbmf.fqubls("sof")) {
                mbrkfrSfqufndf.bdd(nfw SOFMbrkfrSfgmfnt(nodf));
            } flsf if (diildNbmf.fqubls("sos")) {
                mbrkfrSfqufndf.bdd(nfw SOSMbrkfrSfgmfnt(nodf));
            } flsf {
                tirow nfw IIOInvblidTrffExdfption("Invblid "
                    + (isStrfbm ? "strfbm " : "imbgf ") + "diild: "
                    + diildNbmf, nodf);
            }
        }
    }

    /**
     * Cifdk tibt tiis mftbdbtb objfdt is in b donsistfnt stbtf bnd
     * rfturn <dodf>truf</dodf> if it is or <dodf>fblsf</dodf>
     * otifrwisf.  All tif donstrudtors bnd modififrs siould dbll
     * tiis mftiod bt tif fnd to gubrbntff tibt tif dbtb is blwbys
     * donsistfnt, bs tif writfr rflifs on tiis.
     */
    privbtf boolfbn isConsistfnt() {
        SOFMbrkfrSfgmfnt sof =
            (SOFMbrkfrSfgmfnt) findMbrkfrSfgmfnt(SOFMbrkfrSfgmfnt.dlbss,
                                                 truf);
        JFIFMbrkfrSfgmfnt jfif =
            (JFIFMbrkfrSfgmfnt) findMbrkfrSfgmfnt(JFIFMbrkfrSfgmfnt.dlbss,
                                                  truf);
        AdobfMbrkfrSfgmfnt bdobf =
            (AdobfMbrkfrSfgmfnt) findMbrkfrSfgmfnt(AdobfMbrkfrSfgmfnt.dlbss,
                                                   truf);
        boolfbn rftvbl = truf;
        if (!isStrfbm) {
            if (sof != null) {
                // SOF numBbnds = totbl sdbn bbnds
                int numSOFBbnds = sof.domponfntSpfds.lfngti;
                int numSdbnBbnds = dountSdbnBbnds();
                if (numSdbnBbnds != 0) {  // No SOS is OK
                    if (numSdbnBbnds != numSOFBbnds) {
                        rftvbl = fblsf;
                    }
                }
                // If JFIF is prfsfnt, domponfnt ids brf 1-3, bbnds brf 1 or 3
                if (jfif != null) {
                    if ((numSOFBbnds != 1) && (numSOFBbnds != 3)) {
                        rftvbl = fblsf;
                    }
                    for (int i = 0; i < numSOFBbnds; i++) {
                        if (sof.domponfntSpfds[i].domponfntId != i+1) {
                            rftvbl = fblsf;
                        }
                    }

                    // If boti JFIF bnd Adobf brf prfsfnt,
                    // Adobf trbnsform == unknown for grby,
                    // YCC for 3-dibn.
                    if ((bdobf != null)
                        && (((numSOFBbnds == 1)
                             && (bdobf.trbnsform != JPEG.ADOBE_UNKNOWN))
                            || ((numSOFBbnds == 3)
                                && (bdobf.trbnsform != JPEG.ADOBE_YCC)))) {
                        rftvbl = fblsf;
                    }
                }
            } flsf {
                // strfbm dbn't ibvf jfif, bdobf, sof, or sos
                SOSMbrkfrSfgmfnt sos =
                    (SOSMbrkfrSfgmfnt) findMbrkfrSfgmfnt(SOSMbrkfrSfgmfnt.dlbss,
                                                         truf);
                if ((jfif != null) || (bdobf != null)
                    || (sof != null) || (sos != null)) {
                    rftvbl = fblsf;
                }
            }
        }
        rfturn rftvbl;
    }

    /**
     * Rfturns tif totbl numbfr of bbnds rfffrfndfd in bll SOS mbrkfr
     * sfgmfnts, indluding 0 if tifrf brf no SOS mbrkfr sfgmfnts.
     */
    privbtf int dountSdbnBbnds() {
        List<Intfgfr> ids = nfw ArrbyList<>();
        Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
        wiilf(itfr.ibsNfxt()) {
            MbrkfrSfgmfnt sfg = itfr.nfxt();
            if (sfg instbndfof SOSMbrkfrSfgmfnt) {
                SOSMbrkfrSfgmfnt sos = (SOSMbrkfrSfgmfnt) sfg;
                SOSMbrkfrSfgmfnt.SdbnComponfntSpfd [] spfds = sos.domponfntSpfds;
                for (int i = 0; i < spfds.lfngti; i++) {
                    Intfgfr id = spfds[i].domponfntSflfdtor;
                    if (!ids.dontbins(id)) {
                        ids.bdd(id);
                    }
                }
            }
        }

        rfturn ids.sizf();
    }

    ///// Writfr support

    void writfToStrfbm(ImbgfOutputStrfbm ios,
                       boolfbn ignorfJFIF,
                       boolfbn fordfJFIF,
                       List<? fxtfnds BufffrfdImbgf> tiumbnbils,
                       ICC_Profilf iddProfilf,
                       boolfbn ignorfAdobf,
                       int nfwAdobfTrbnsform,
                       JPEGImbgfWritfr writfr)
        tirows IOExdfption {
        if (fordfJFIF) {
            // Writf b dffbult JFIF sfgmfnt, indluding tiumbnbils
            // Tiis won't bf duplidbtfd bflow bfdbusf fordfJFIF will bf
            // sft only if tifrf is no JFIF prfsfnt blrfbdy.
            JFIFMbrkfrSfgmfnt.writfDffbultJFIF(ios,
                                               tiumbnbils,
                                               iddProfilf,
                                               writfr);
            if ((ignorfAdobf == fblsf)
                && (nfwAdobfTrbnsform != JPEG.ADOBE_IMPOSSIBLE)) {
                if ((nfwAdobfTrbnsform != JPEG.ADOBE_UNKNOWN)
                    && (nfwAdobfTrbnsform != JPEG.ADOBE_YCC)) {
                    // Not dompbtiblf, so ignorf Adobf.
                    ignorfAdobf = truf;
                    writfr.wbrningOddurrfd
                        (JPEGImbgfWritfr.WARNING_METADATA_ADJUSTED_FOR_THUMB);
                }
            }
        }
        // Itfrbtf ovfr fbdi MbrkfrSfgmfnt
        Itfrbtor<MbrkfrSfgmfnt> itfr = mbrkfrSfqufndf.itfrbtor();
        wiilf(itfr.ibsNfxt()) {
            MbrkfrSfgmfnt sfg = itfr.nfxt();
            if (sfg instbndfof JFIFMbrkfrSfgmfnt) {
                if (ignorfJFIF == fblsf) {
                    JFIFMbrkfrSfgmfnt jfif = (JFIFMbrkfrSfgmfnt) sfg;
                    jfif.writfWitiTiumbs(ios, tiumbnbils, writfr);
                    if (iddProfilf != null) {
                        JFIFMbrkfrSfgmfnt.writfICC(iddProfilf, ios);
                    }
                } // Otifrwisf ignorf it, bs rfqufstfd
            } flsf if (sfg instbndfof AdobfMbrkfrSfgmfnt) {
                if (ignorfAdobf == fblsf) {
                    if (nfwAdobfTrbnsform != JPEG.ADOBE_IMPOSSIBLE) {
                        AdobfMbrkfrSfgmfnt nfwAdobf =
                            (AdobfMbrkfrSfgmfnt) sfg.dlonf();
                        nfwAdobf.trbnsform = nfwAdobfTrbnsform;
                        nfwAdobf.writf(ios);
                    } flsf if (fordfJFIF) {
                        // If bdobf isn't JFIF dompbtiblf, ignorf it
                        AdobfMbrkfrSfgmfnt bdobf = (AdobfMbrkfrSfgmfnt) sfg;
                        if ((bdobf.trbnsform == JPEG.ADOBE_UNKNOWN)
                            || (bdobf.trbnsform == JPEG.ADOBE_YCC)) {
                            bdobf.writf(ios);
                        } flsf {
                            writfr.wbrningOddurrfd
                         (JPEGImbgfWritfr.WARNING_METADATA_ADJUSTED_FOR_THUMB);
                        }
                    } flsf {
                        sfg.writf(ios);
                    }
                } // Otifrwisf ignorf it, bs rfqufstfd
            } flsf {
                sfg.writf(ios);
            }
        }
    }

    //// End of writfr support

    publid void rfsft() {
        if (rfsftSfqufndf != null) {  // Otifrwisf no nffd to rfsft
            mbrkfrSfqufndf = rfsftSfqufndf;
            rfsftSfqufndf = null;
        }
    }

    publid void print() {
        for (int i = 0; i < mbrkfrSfqufndf.sizf(); i++) {
            MbrkfrSfgmfnt sfg = mbrkfrSfqufndf.gft(i);
            sfg.print();
        }
    }

}
