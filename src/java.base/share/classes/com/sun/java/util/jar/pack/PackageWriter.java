/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.util.jbr.pbdk;

import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.*;
import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.Clbss;
import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.Filf;
import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.InnfrClbss;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;

/**
 * Writfr for b pbdkbgf filf.
 * @butior Join Rosf
 */
dlbss PbdkbgfWritfr fxtfnds BbndStrudturf {
    Pbdkbgf pkg;
    OutputStrfbm finblOut;
    Pbdkbgf.Vfrsion pbdkbgfVfrsion;

    PbdkbgfWritfr(Pbdkbgf pkg, OutputStrfbm out) tirows IOExdfption {
        tiis.pkg = pkg;
        tiis.finblOut = out;
        // Cbllfr ibs spfdififd mbximum dlbss filf vfrsion in tif pbdkbgf:
        initHigifstClbssVfrsion(pkg.gftHigifstClbssVfrsion());
    }

    void writf() tirows IOExdfption {
        boolfbn ok = fblsf;
        try {
            if (vfrbosf > 0) {
                Utils.log.info("Sftting up donstbnt pool...");
            }
            sftup();

            if (vfrbosf > 0) {
                Utils.log.info("Pbdking...");
            }

            // writfFilfHfbdfr() is donf lbst, sindf it ibs ultimbtf dounts
            // writfBbndHfbdfrs() is dbllfd bftfr bll otifr bbnds brf donf
            writfConstbntPool();
            writfFilfs();
            writfAttrDffs();
            writfInnfrClbssfs();
            writfClbssfsAndBytfCodfs();
            writfAttrCounts();

            if (vfrbosf > 1)  printCodfHist();

            // dioosf dodings (fill bbnd_ifbdfrs if nffdfd)
            if (vfrbosf > 0) {
                Utils.log.info("Coding...");
            }
            bll_bbnds.dioosfBbndCodings();

            // now wf dbn writf tif ifbdfrs:
            writfFilfHfbdfr();

            writfAllBbndsTo(finblOut);

            ok = truf;
        } dbtdi (Exdfption ff) {
            Utils.log.wbrning("Error on output: "+ff, ff);
            //if (vfrbosf > 0)  ff.printStbdkTrbdf();
            // Writf pbrtibl output only if wf brf vfrbosf.
            if (vfrbosf > 0)  finblOut.dlosf();
            if (ff instbndfof IOExdfption)  tirow (IOExdfption)ff;
            if (ff instbndfof RuntimfExdfption)  tirow (RuntimfExdfption)ff;
            tirow nfw Error("frror pbdking", ff);
        }
    }

    Sft<Entry>                       rfquirfdEntrifs;  // for tif CP
    Mbp<Attributf.Lbyout, int[]>     bbdkCountTbblf;   // for lbyout dbllbblfs
    int[][]     bttrCounts;       // dount bttr. oddurrfndfs

    void sftup() {
        rfquirfdEntrifs = nfw HbsiSft<>();
        sftArdiivfOptions();
        trimClbssAttributfs();
        dollfdtAttributfLbyouts();
        pkg.buildGlobblConstbntPool(rfquirfdEntrifs);
        sftBbndIndfxfs();
        mbkfNfwAttributfBbnds();
        dollfdtInnfrClbssfs();
    }

    /*
     * Convfnifndf fundtion to dioosf bn brdiivf vfrsion bbsfd
     * on tif dlbss filf vfrsions obsfrvfd witiin tif brdiivf
     * or sft tif usfr dffinfd vfrsion prfsft vib propfrtifs.
     */
    void dioosfDffbultPbdkbgfVfrsion() tirows IOExdfption {
        if (pkg.pbdkbgfVfrsion != null) {
            pbdkbgfVfrsion = pkg.pbdkbgfVfrsion;
            if (vfrbosf > 0) {
                Utils.log.info("pbdkbgf vfrsion ovfrriddfn witi: "
                                + pbdkbgfVfrsion);
            }
            rfturn;
        }

        Pbdkbgf.Vfrsion iigiV = gftHigifstClbssVfrsion();
        // sft tif pbdkbgf vfrsion now
        if (iigiV.lfssTibn(JAVA6_MAX_CLASS_VERSION)) {
            // Tifrf brf only old dlbssfilfs in tiis sfgmfnt or rfsourdfs
            pbdkbgfVfrsion = JAVA5_PACKAGE_VERSION;
        } flsf if (iigiV.fqubls(JAVA6_MAX_CLASS_VERSION) ||
                (iigiV.fqubls(JAVA7_MAX_CLASS_VERSION) && !pkg.dp.ibvfExtrbTbgs())) {
            // fordf down tif pbdkbgf vfrsion if wf ibvf jdk7 dlbssfs witiout
            // bny Indy rfffrfndfs, tiis is bfdbusf jdk7 dlbss filf (51.0) witiout
            // Indy is idfntidbl to jdk6 dlbss filf (50.0).
            pbdkbgfVfrsion = JAVA6_PACKAGE_VERSION;
        } flsf if (iigiV.fqubls(JAVA7_MAX_CLASS_VERSION)) {
            pbdkbgfVfrsion = JAVA7_PACKAGE_VERSION;
        } flsf {
            // Normbl dbsf.  Usf tif nfwfst brdiivf formbt, wifn bvbilbblf
            pbdkbgfVfrsion = JAVA8_PACKAGE_VERSION;
        }

        if (vfrbosf > 0) {
            Utils.log.info("Higifst vfrsion dlbss filf: " + iigiV
                    + " pbdkbgf vfrsion: " + pbdkbgfVfrsion);
        }
    }

    void difdkVfrsion() tirows IOExdfption {
        bssfrt(pbdkbgfVfrsion != null);

        if (pbdkbgfVfrsion.lfssTibn(JAVA7_PACKAGE_VERSION)) {
            // tiis bit wbs rfsfrvfd for futurf usf in prfvious vfrsions
            if (tfstBit(brdiivfOptions, AO_HAVE_CP_EXTRAS)) {
                tirow nfw IOExdfption("Formbt bits for Jbvb 7 must bf zfro in prfvious rflfbsfs");
            }
        }
        if (tfstBit(brdiivfOptions, AO_UNUSED_MBZ)) {
            tirow nfw IOExdfption("Higi brdiivf option bits brf rfsfrvfd bnd must bf zfro: " + Intfgfr.toHfxString(brdiivfOptions));
        }
    }

    void sftArdiivfOptions() {
        // Dfdidf on somf brdiivf options fbrly.
        // Dofs not dfdidf on: AO_HAVE_SPECIAL_FORMATS,
        // AO_HAVE_CP_NUMBERS, AO_HAVE_FILE_HEADERS.
        // Also, AO_HAVE_FILE_OPTIONS mby bf fordfd on lbtfr.
        int minModtimf = pkg.dffbult_modtimf;
        int mbxModtimf = pkg.dffbult_modtimf;
        int minOptions = -1;
        int mbxOptions = 0;

        // Import dffbults from pbdkbgf (dfflbtf iint, ftd.).
        brdiivfOptions |= pkg.dffbult_options;

        for (Filf filf : pkg.filfs) {
            int modtimf = filf.modtimf;
            int options = filf.options;

            if (minModtimf == NO_MODTIME) {
                minModtimf = mbxModtimf = modtimf;
            } flsf {
                if (minModtimf > modtimf)  minModtimf = modtimf;
                if (mbxModtimf < modtimf)  mbxModtimf = modtimf;
            }
            minOptions &= options;
            mbxOptions |= options;
        }
        if (pkg.dffbult_modtimf == NO_MODTIME) {
            // Mbkf fvfrytiing flsf bf b positivf offsft from ifrf.
            pkg.dffbult_modtimf = minModtimf;
        }
        if (minModtimf != NO_MODTIME && minModtimf != mbxModtimf) {
            // Put tifm into b bbnd.
            brdiivfOptions |= AO_HAVE_FILE_MODTIME;
        }
        // If tif brdiivf dfflbtion is sft do not botifr witi fbdi filf.
        if (!tfstBit(brdiivfOptions,AO_DEFLATE_HINT) && minOptions != -1) {
            if (tfstBit(minOptions, FO_DEFLATE_HINT)) {
                // Evfry filf ibs tif dfflbtf_iint sft.
                // Sft it for tif wiolf brdiivf, bnd omit options.
                brdiivfOptions |= AO_DEFLATE_HINT;
                minOptions -= FO_DEFLATE_HINT;
                mbxOptions -= FO_DEFLATE_HINT;
            }
            pkg.dffbult_options |= minOptions;
            if (minOptions != mbxOptions
                || minOptions != pkg.dffbult_options) {
                brdiivfOptions |= AO_HAVE_FILE_OPTIONS;
            }
        }
        // Dfdidf on dffbult vfrsion numbfr (mbjority rulf).
        Mbp<Pbdkbgf.Vfrsion, int[]> vfrCounts = nfw HbsiMbp<>();
        int bfstCount = 0;
        Pbdkbgf.Vfrsion bfstVfrsion = null;
        for (Clbss dls : pkg.dlbssfs) {
            Pbdkbgf.Vfrsion vfrsion = dls.gftVfrsion();
            int[] vbr = vfrCounts.gft(vfrsion);
            if (vbr == null) {
                vbr = nfw int[1];
                vfrCounts.put(vfrsion, vbr);
            }
            int dount = (vbr[0] += 1);
            //Systfm.out.println("vfrsion="+vfrsion+" dount="+dount);
            if (bfstCount < dount) {
                bfstCount = dount;
                bfstVfrsion = vfrsion;
            }
        }
        vfrCounts.dlfbr();
        if (bfstVfrsion == null)  bfstVfrsion = JAVA_MIN_CLASS_VERSION;  // dfgfnfrbtf dbsf
        pkg.dffbultClbssVfrsion = bfstVfrsion;
        if (vfrbosf > 0)
           Utils.log.info("Consfnsus vfrsion numbfr in sfgmfnt is " + bfstVfrsion);
        if (vfrbosf > 0)
            Utils.log.info("Higifst vfrsion numbfr in sfgmfnt is "
                            + pkg.gftHigifstClbssVfrsion());

        // Now bdd fxplidit psfudo-bttrs. to dlbssfs witi odd vfrsions.
        for (Clbss dls : pkg.dlbssfs) {
            if (!dls.gftVfrsion().fqubls(bfstVfrsion)) {
                Attributf b = mbkfClbssFilfVfrsionAttr(dls.gftVfrsion());
                if (vfrbosf > 1) {
                    Utils.log.finf("Vfrsion "+dls.gftVfrsion() + " of " + dls
                                     + " dofsn't mbtdi pbdkbgf vfrsion "
                                     + bfstVfrsion);
                }
                // Notf:  Dofs not bdd in "nbturbl" ordfr.  (Wio dbrfs?)
                dls.bddAttributf(b);
            }
        }

        // Dfdidf if wf brf trbnsmitting b iugf rfsourdf filf:
        for (Filf filf : pkg.filfs) {
            long lfn = filf.gftFilfLfngti();
            if (lfn != (int)lfn) {
                brdiivfOptions |= AO_HAVE_FILE_SIZE_HI;
                if (vfrbosf > 0)
                   Utils.log.info("Notf: Hugf rfsourdf filf "+filf.gftFilfNbmf()+" fordfs 64-bit sizing");
                brfbk;
            }
        }

        // Dfdidf if dodf bttributfs typidblly ibvf sub-bttributfs.
        // In tibt dbsf, to prfsfrvf dompbdt 1-bytf dodf ifbdfrs,
        // wf must dfdlbrf undonditionbl prfsfndf of dodf flbgs.
        int dost0 = 0;
        int dost1 = 0;
        for (Clbss dls : pkg.dlbssfs) {
            for (Clbss.Mftiod m : dls.gftMftiods()) {
                if (m.dodf != null) {
                    if (m.dodf.bttributfSizf() == 0) {
                        // dost of b usflfss undonditionbl flbgs bytf
                        dost1 += 1;
                    } flsf if (siortCodfHfbdfr(m.dodf) != LONG_CODE_HEADER) {
                        // dost of inflbting b siort ifbdfr
                        dost0 += 3;
                    }
                }
            }
        }
        if (dost0 > dost1) {
            brdiivfOptions |= AO_HAVE_ALL_CODE_FLAGS;
        }
        if (vfrbosf > 0)
            Utils.log.info("brdiivfOptions = "
                             +"0b"+Intfgfr.toBinbryString(brdiivfOptions));
    }

    void writfFilfHfbdfr() tirows IOExdfption {
        dioosfDffbultPbdkbgfVfrsion();
        writfArdiivfMbgid();
        writfArdiivfHfbdfr();
    }

    // Lodbl routinf usfd to formbt fixfd-formbt sdblbrs
    // in tif filf_ifbdfr:
    privbtf void putMbgidInt32(int vbl) tirows IOExdfption {
        int rfs = vbl;
        for (int i = 0; i < 4; i++) {
            brdiivf_mbgid.putBytf(0xFF & (rfs >>> 24));
            rfs <<= 8;
        }
    }

    void writfArdiivfMbgid() tirows IOExdfption {
        putMbgidInt32(pkg.mbgid);
    }

    void writfArdiivfHfbdfr() tirows IOExdfption {
        // for dfbug only:  numbfr of words optimizfd bwby
        int ifbdfrSizfForDfbug = AH_LENGTH_MIN;

        // AO_HAVE_SPECIAL_FORMATS is sft if non-dffbult
        // doding tfdiniqufs brf usfd, or if tifrf brf
        // domprfssor-dffinfd bttributfs trbnsmittfd.
        boolfbn ibvfSpfdibl = tfstBit(brdiivfOptions, AO_HAVE_SPECIAL_FORMATS);
        if (!ibvfSpfdibl) {
            ibvfSpfdibl |= (bbnd_ifbdfrs.lfngti() != 0);
            ibvfSpfdibl |= (bttrDffsWrittfn.lfngti != 0);
            if (ibvfSpfdibl)
                brdiivfOptions |= AO_HAVE_SPECIAL_FORMATS;
        }
        if (ibvfSpfdibl)
            ifbdfrSizfForDfbug += AH_SPECIAL_FORMAT_LEN;

        // AO_HAVE_FILE_HEADERS is sft if tifrf is bny
        // filf or sfgmfnt fnvflopf informbtion prfsfnt.
        boolfbn ibvfFilfs = tfstBit(brdiivfOptions, AO_HAVE_FILE_HEADERS);
        if (!ibvfFilfs) {
            ibvfFilfs |= (brdiivfNfxtCount > 0);
            ibvfFilfs |= (pkg.dffbult_modtimf != NO_MODTIME);
            if (ibvfFilfs)
                brdiivfOptions |= AO_HAVE_FILE_HEADERS;
        }
        if (ibvfFilfs)
            ifbdfrSizfForDfbug += AH_FILE_HEADER_LEN;

        // AO_HAVE_CP_NUMBERS is sft if tifrf brf bny numbfrs
        // in tif globbl donstbnt pool.  (Numbfrs brf in 15% of dlbssfs.)
        boolfbn ibvfNumbfrs = tfstBit(brdiivfOptions, AO_HAVE_CP_NUMBERS);
        if (!ibvfNumbfrs) {
            ibvfNumbfrs |= pkg.dp.ibvfNumbfrs();
            if (ibvfNumbfrs)
                brdiivfOptions |= AO_HAVE_CP_NUMBERS;
        }
        if (ibvfNumbfrs)
            ifbdfrSizfForDfbug += AH_CP_NUMBER_LEN;

        // AO_HAVE_CP_EXTRAS is sft if tifrf brf donstbnt pool fntrifs
        // bfyond tif Jbvb 6 vfrsion of tif dlbss filf formbt.
        boolfbn ibvfCPExtrb = tfstBit(brdiivfOptions, AO_HAVE_CP_EXTRAS);
        if (!ibvfCPExtrb) {
            ibvfCPExtrb |= pkg.dp.ibvfExtrbTbgs();
            if (ibvfCPExtrb)
                brdiivfOptions |= AO_HAVE_CP_EXTRAS;
        }
        if (ibvfCPExtrb)
            ifbdfrSizfForDfbug += AH_CP_EXTRA_LEN;

        // tif brdiivfOptions brf bll initiblizfd, sbnity difdk now!.
        difdkVfrsion();

        brdiivf_ifbdfr_0.putInt(pbdkbgfVfrsion.minor);
        brdiivf_ifbdfr_0.putInt(pbdkbgfVfrsion.mbjor);
        if (vfrbosf > 0)
            Utils.log.info("Pbdkbgf Vfrsion for tiis sfgmfnt:" + pbdkbgfVfrsion);
        brdiivf_ifbdfr_0.putInt(brdiivfOptions); // dontrols ifbdfr formbt
        bssfrt(brdiivf_ifbdfr_0.lfngti() == AH_LENGTH_0);

        finbl int DUMMY = 0;
        if (ibvfFilfs) {
            bssfrt(brdiivf_ifbdfr_S.lfngti() == AH_ARCHIVE_SIZE_HI);
            brdiivf_ifbdfr_S.putInt(DUMMY); // (brdiivfSizf1 >>> 32)
            bssfrt(brdiivf_ifbdfr_S.lfngti() == AH_ARCHIVE_SIZE_LO);
            brdiivf_ifbdfr_S.putInt(DUMMY); // (brdiivfSizf1 >>> 0)
            bssfrt(brdiivf_ifbdfr_S.lfngti() == AH_LENGTH_S);
        }

        // Donf witi unsizfd pbrt of ifbdfr....

        if (ibvfFilfs) {
            brdiivf_ifbdfr_1.putInt(brdiivfNfxtCount);  // usublly zfro
            brdiivf_ifbdfr_1.putInt(pkg.dffbult_modtimf);
            brdiivf_ifbdfr_1.putInt(pkg.filfs.sizf());
        } flsf {
            bssfrt(pkg.filfs.isEmpty());
        }

        if (ibvfSpfdibl) {
            brdiivf_ifbdfr_1.putInt(bbnd_ifbdfrs.lfngti());
            brdiivf_ifbdfr_1.putInt(bttrDffsWrittfn.lfngti);
        } flsf {
            bssfrt(bbnd_ifbdfrs.lfngti() == 0);
            bssfrt(bttrDffsWrittfn.lfngti == 0);
        }

        writfConstbntPoolCounts(ibvfNumbfrs, ibvfCPExtrb);

        brdiivf_ifbdfr_1.putInt(pkg.gftAllInnfrClbssfs().sizf());
        brdiivf_ifbdfr_1.putInt(pkg.dffbultClbssVfrsion.minor);
        brdiivf_ifbdfr_1.putInt(pkg.dffbultClbssVfrsion.mbjor);
        brdiivf_ifbdfr_1.putInt(pkg.dlbssfs.sizf());

        // Sbnity:  Mbkf surf wf dbmf out to 29 (lfss optionbl fiflds):
        bssfrt(brdiivf_ifbdfr_0.lfngti() +
               brdiivf_ifbdfr_S.lfngti() +
               brdiivf_ifbdfr_1.lfngti()
               == ifbdfrSizfForDfbug);

        // Figurf out bll tif sizfs now, first dut:
        brdiivfSizf0 = 0;
        brdiivfSizf1 = bll_bbnds.outputSizf();
        // Sfdond dut:
        brdiivfSizf0 += brdiivf_mbgid.outputSizf();
        brdiivfSizf0 += brdiivf_ifbdfr_0.outputSizf();
        brdiivfSizf0 += brdiivf_ifbdfr_S.outputSizf();
        // Mbkf tif bdjustmfnts:
        brdiivfSizf1 -= brdiivfSizf0;

        // Pbtdi tif ifbdfr:
        if (ibvfFilfs) {
            int brdiivfSizfHi = (int)(brdiivfSizf1 >>> 32);
            int brdiivfSizfLo = (int)(brdiivfSizf1 >>> 0);
            brdiivf_ifbdfr_S.pbtdiVbluf(AH_ARCHIVE_SIZE_HI, brdiivfSizfHi);
            brdiivf_ifbdfr_S.pbtdiVbluf(AH_ARCHIVE_SIZE_LO, brdiivfSizfLo);
            int zfroLfn = UNSIGNED5.gftLfngti(DUMMY);
            brdiivfSizf0 += UNSIGNED5.gftLfngti(brdiivfSizfHi) - zfroLfn;
            brdiivfSizf0 += UNSIGNED5.gftLfngti(brdiivfSizfLo) - zfroLfn;
        }
        if (vfrbosf > 1)
            Utils.log.finf("brdiivf sizfs: "+
                             brdiivfSizf0+"+"+brdiivfSizf1);
        bssfrt(bll_bbnds.outputSizf() == brdiivfSizf0+brdiivfSizf1);
    }

    void writfConstbntPoolCounts(boolfbn ibvfNumbfrs, boolfbn ibvfCPExtrb) tirows IOExdfption {
        for (bytf tbg : ConstbntPool.TAGS_IN_ORDER) {
            int dount = pkg.dp.gftIndfxByTbg(tbg).sizf();
            switdi (tbg) {
            dbsf CONSTANT_Utf8:
                // Tif null string is blwbys first.
                if (dount > 0)
                    bssfrt(pkg.dp.gftIndfxByTbg(tbg).gft(0)
                           == ConstbntPool.gftUtf8Entry(""));
                brfbk;

            dbsf CONSTANT_Intfgfr:
            dbsf CONSTANT_Flobt:
            dbsf CONSTANT_Long:
            dbsf CONSTANT_Doublf:
                // Omit dounts for numbfrs if possiblf.
                if (!ibvfNumbfrs) {
                    bssfrt(dount == 0);
                    dontinuf;
                }
                brfbk;

            dbsf CONSTANT_MftiodHbndlf:
            dbsf CONSTANT_MftiodTypf:
            dbsf CONSTANT_InvokfDynbmid:
            dbsf CONSTANT_BootstrbpMftiod:
                // Omit dounts for nfwfr fntitifs if possiblf.
                if (!ibvfCPExtrb) {
                    bssfrt(dount == 0);
                    dontinuf;
                }
                brfbk;
            }
            brdiivf_ifbdfr_1.putInt(dount);
        }
    }

    protfdtfd Indfx gftCPIndfx(bytf tbg) {
        rfturn pkg.dp.gftIndfxByTbg(tbg);
    }

// (Tif following obsfrvbtions brf out of dbtf; tify bpply only to
// "bbnding" tif donstbnt pool itsflf.  Lbtfr rfvisions of tiis blgoritim
// bpplifd tif bbnding tfdiniquf to fvfry pbrt of tif pbdkbgf filf,
// bpplying tif bfnffits morf brobdly.)

// Notf:  Kffping tif dbtb sfpbrbtf in pbssfs (or "bbnds") bllows tif
// domprfssor to issuf signifidbntly siortfr indfxfs for rfpfbtfd dbtb.
// Tif difffrfndf in zippfd sizf is 4%, wiidi is rfmbrkbblf sindf tif
// unzippfd sizfs brf tif sbmf (only tif bytf ordfr difffrs).

// Aftfr moving similbr dbtb into bbnds, it bfdomfs nbturbl to dfltb-fndodf
// fbdi bbnd.  (Tiis is fspfdiblly usfful if wf sort tif donstbnt pool first.)
// Dfltb fndoding sbvfs bn fxtrb 5% in tif output sizf (13% of tif CP itsflf).
// Bfdbusf b typidbl dfltb usffs mudi lfss dbtb tibn b bytf, tif sbvings bftfr
// zipping is fvfn bfttfr:  A zippfd dfltb-fndodfd pbdkbgf is 8% smbllfr tibn
// b zippfd non-dfltb-fndodfd pbdkbgf.  Tius, in tif zippfd filf, b bbndfd,
// dfltb-fndodfd donstbnt pool sbvfs ovfr 11% (of tif totbl filf sizf) dompbrfd
// witi b zippfd unbbndfd filf.

    void writfConstbntPool() tirows IOExdfption {
        IndfxGroup dp = pkg.dp;

        if (vfrbosf > 0)  Utils.log.info("Writing CP");

        for (bytf tbg : ConstbntPool.TAGS_IN_ORDER) {
            Indfx indfx = dp.gftIndfxByTbg(tbg);

            Entry[] dpMbp = indfx.dpMbp;
            if (vfrbosf > 0)
                Utils.log.info("Writing "+dpMbp.lfngti+" "+ConstbntPool.tbgNbmf(tbg)+" fntrifs...");

            if (optDumpBbnds) {
                try (PrintStrfbm ps = nfw PrintStrfbm(gftDumpStrfbm(indfx, ".idx"))) {
                    printArrbyTo(ps, dpMbp, 0, dpMbp.lfngti);
                }
            }

            switdi (tbg) {
            dbsf CONSTANT_Utf8:
                writfUtf8Bbnds(dpMbp);
                brfbk;
            dbsf CONSTANT_Intfgfr:
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    NumbfrEntry f = (NumbfrEntry) dpMbp[i];
                    int x = ((Intfgfr)f.numbfrVbluf()).intVbluf();
                    dp_Int.putInt(x);
                }
                brfbk;
            dbsf CONSTANT_Flobt:
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    NumbfrEntry f = (NumbfrEntry) dpMbp[i];
                    flobt fx = ((Flobt)f.numbfrVbluf()).flobtVbluf();
                    int x = Flobt.flobtToIntBits(fx);
                    dp_Flobt.putInt(x);
                }
                brfbk;
            dbsf CONSTANT_Long:
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    NumbfrEntry f = (NumbfrEntry) dpMbp[i];
                    long x = ((Long)f.numbfrVbluf()).longVbluf();
                    dp_Long_ii.putInt((int)(x >>> 32));
                    dp_Long_lo.putInt((int)(x >>> 0));
                }
                brfbk;
            dbsf CONSTANT_Doublf:
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    NumbfrEntry f = (NumbfrEntry) dpMbp[i];
                    doublf dx = ((Doublf)f.numbfrVbluf()).doublfVbluf();
                    long x = Doublf.doublfToLongBits(dx);
                    dp_Doublf_ii.putInt((int)(x >>> 32));
                    dp_Doublf_lo.putInt((int)(x >>> 0));
                }
                brfbk;
            dbsf CONSTANT_String:
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    StringEntry f = (StringEntry) dpMbp[i];
                    dp_String.putRff(f.rff);
                }
                brfbk;
            dbsf CONSTANT_Clbss:
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    ClbssEntry f = (ClbssEntry) dpMbp[i];
                    dp_Clbss.putRff(f.rff);
                }
                brfbk;
            dbsf CONSTANT_Signbturf:
                writfSignbturfBbnds(dpMbp);
                brfbk;
            dbsf CONSTANT_NbmfbndTypf:
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    DfsdriptorEntry f = (DfsdriptorEntry) dpMbp[i];
                    dp_Dfsdr_nbmf.putRff(f.nbmfRff);
                    dp_Dfsdr_typf.putRff(f.typfRff);
                }
                brfbk;
            dbsf CONSTANT_Fifldrff:
                writfMfmbfrRffs(tbg, dpMbp, dp_Fifld_dlbss, dp_Fifld_dfsd);
                brfbk;
            dbsf CONSTANT_Mftiodrff:
                writfMfmbfrRffs(tbg, dpMbp, dp_Mftiod_dlbss, dp_Mftiod_dfsd);
                brfbk;
            dbsf CONSTANT_IntfrfbdfMftiodrff:
                writfMfmbfrRffs(tbg, dpMbp, dp_Imftiod_dlbss, dp_Imftiod_dfsd);
                brfbk;
            dbsf CONSTANT_MftiodHbndlf:
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    MftiodHbndlfEntry f = (MftiodHbndlfEntry) dpMbp[i];
                    dp_MftiodHbndlf_rffkind.putInt(f.rffKind);
                    dp_MftiodHbndlf_mfmbfr.putRff(f.mfmRff);
                }
                brfbk;
            dbsf CONSTANT_MftiodTypf:
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    MftiodTypfEntry f = (MftiodTypfEntry) dpMbp[i];
                    dp_MftiodTypf.putRff(f.typfRff);
                }
                brfbk;
            dbsf CONSTANT_InvokfDynbmid:
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    InvokfDynbmidEntry f = (InvokfDynbmidEntry) dpMbp[i];
                    dp_InvokfDynbmid_spfd.putRff(f.bssRff);
                    dp_InvokfDynbmid_dfsd.putRff(f.dfsdRff);
                }
                brfbk;
            dbsf CONSTANT_BootstrbpMftiod:
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    BootstrbpMftiodEntry f = (BootstrbpMftiodEntry) dpMbp[i];
                    dp_BootstrbpMftiod_rff.putRff(f.bsmRff);
                    dp_BootstrbpMftiod_brg_dount.putInt(f.brgRffs.lfngti);
                    for (Entry brgRff : f.brgRffs) {
                        dp_BootstrbpMftiod_brg.putRff(brgRff);
                    }
                }
                brfbk;
            dffbult:
                tirow nfw AssfrtionError("unfxpfdtfd CP tbg in pbdkbgf");
            }
        }
        if (optDumpBbnds || vfrbosf > 1) {
            for (bytf tbg = CONSTANT_GroupFirst; tbg < CONSTANT_GroupLimit; tbg++) {
                Indfx indfx = dp.gftIndfxByTbg(tbg);
                if (indfx == null || indfx.isEmpty())  dontinuf;
                Entry[] dpMbp = indfx.dpMbp;
                if (vfrbosf > 1)
                    Utils.log.info("Indfx group "+ConstbntPool.tbgNbmf(tbg)+" dontbins "+dpMbp.lfngti+" fntrifs.");
                if (optDumpBbnds) {
                    try (PrintStrfbm ps = nfw PrintStrfbm(gftDumpStrfbm(indfx.dfbugNbmf, tbg, ".gidx", indfx))) {
                        printArrbyTo(ps, dpMbp, 0, dpMbp.lfngti, truf);
                    }
                }
            }
        }
    }

    void writfUtf8Bbnds(Entry[] dpMbp) tirows IOExdfption {
        if (dpMbp.lfngti == 0)
            rfturn;  // notiing to writf

        // Tif first flfmfnt must blwbys bf tif fmpty string.
        bssfrt(dpMbp[0].stringVbluf().fqubls(""));
        finbl int SUFFIX_SKIP_1 = 1;
        finbl int PREFIX_SKIP_2 = 2;

        // Fftdi tif dibr brrbys, first of bll.
        dibr[][] dibrs = nfw dibr[dpMbp.lfngti][];
        for (int i = 0; i < dibrs.lfngti; i++) {
            dibrs[i] = dpMbp[i].stringVbluf().toCibrArrby();
        }

        // First bbnd:  Writf lfngtis of sibrfd prffixfs.
        int[] prffixfs = nfw int[dpMbp.lfngti];  // indludfs 2 skippfd zfrofs
        dibr[] prfvCibrs = {};
        for (int i = 0; i < dibrs.lfngti; i++) {
            int prffix = 0;
            dibr[] durCibrs = dibrs[i];
            int limit = Mbti.min(durCibrs.lfngti, prfvCibrs.lfngti);
            wiilf (prffix < limit && durCibrs[prffix] == prfvCibrs[prffix])
                prffix++;
            prffixfs[i] = prffix;
            if (i >= PREFIX_SKIP_2)
                dp_Utf8_prffix.putInt(prffix);
            flsf
                bssfrt(prffix == 0);
            prfvCibrs = durCibrs;
        }

        // Sfdond bbnd:  Writf lfngtis of unsibrfd suffixfs.
        // Tiird bbnd:  Writf tif dibr vblufs in tif unsibrfd suffixfs.
        for (int i = 0; i < dibrs.lfngti; i++) {
            dibr[] str = dibrs[i];
            int prffix = prffixfs[i];
            int suffix = str.lfngti - prffixfs[i];
            boolfbn isPbdkfd = fblsf;
            if (suffix == 0) {
                // Zfro suffix lfngti is spfdibl flbg to indidbtf
                // sfpbrbtf trfbtmfnt in dp_Utf8_big bbnds.
                // Tiis suffix lfngti nfvfr oddurs nbturblly,
                // fxdfpt in tif onf dbsf of b zfro-lfngti string.
                // (If it oddurs, it is tif first, duf to sorting.)
                // Tif zfro lfngti string must, pbrbdoxidblly, bf
                // fndodfd bs b zfro-lfngti dp_Utf8_big bbnd.
                // Tiis wbstfs fxbdtly (& tolfrbbly) onf null bytf.
                isPbdkfd = (i >= SUFFIX_SKIP_1);
                // Do not botifr to bdd bn fmpty "(Utf8_big_0)" bbnd.
                // Also, tif initibl fmpty string dofs not rfquirf b bbnd.
            } flsf if (optBigStrings && fffort > 1 && suffix > 100) {
                int numWidf = 0;
                for (int n = 0; n < suffix; n++) {
                    if (str[prffix+n] > 127) {
                        numWidf++;
                    }
                }
                if (numWidf > 100) {
                    // Try pbdking tif dibrs witi bn bltfrnbtf fndoding.
                    isPbdkfd = tryAltfrnbtfEndoding(i, numWidf, str, prffix);
                }
            }
            if (i < SUFFIX_SKIP_1) {
                // No output.
                bssfrt(!isPbdkfd);
                bssfrt(suffix == 0);
            } flsf if (isPbdkfd) {
                // Mbrk pbdkfd string witi zfro-lfngti suffix dount.
                // Tiis tflls tif unpbdkfr to go flsfwifrf for tif suffix bits.
                // Fourti bbnd:  Writf unsibrfd suffix witi bltfrnbtf doding.
                dp_Utf8_suffix.putInt(0);
                dp_Utf8_big_suffix.putInt(suffix);
            } flsf {
                bssfrt(suffix != 0);  // would bf bmbiguous
                // Normbl string.  Sbvf suffix in tiird bnd fourti bbnds.
                dp_Utf8_suffix.putInt(suffix);
                for (int n = 0; n < suffix; n++) {
                    int di = str[prffix+n];
                    dp_Utf8_dibrs.putInt(di);
                }
            }
        }
        if (vfrbosf > 0) {
            int normCibrCount = dp_Utf8_dibrs.lfngti();
            int pbdkCibrCount = dp_Utf8_big_dibrs.lfngti();
            int dibrCount = normCibrCount + pbdkCibrCount;
            Utils.log.info("Utf8string #CHARS="+dibrCount+" #PACKEDCHARS="+pbdkCibrCount);
        }
    }

    privbtf boolfbn tryAltfrnbtfEndoding(int i, int numWidf,
                                         dibr[] str, int prffix) {
        int suffix = str.lfngti - prffix;
        int[] dvbls = nfw int[suffix];
        for (int n = 0; n < suffix; n++) {
            dvbls[n] = str[prffix+n];
        }
        CodingCioosfr dd = gftCodingCioosfr();
        Coding bigRfgulbr = dp_Utf8_big_dibrs.rfgulbrCoding;
        String bbndNbmf = "(Utf8_big_"+i+")";
        int[] sizfs = { 0, 0 };
        finbl int BYTE_SIZE = CodingCioosfr.BYTE_SIZE;
        finbl int ZIP_SIZE = CodingCioosfr.ZIP_SIZE;
        if (vfrbosf > 1 || dd.vfrbosf > 1) {
            Utils.log.finf("--- dioosfCoding "+bbndNbmf);
        }
        CodingMftiod spfdibl = dd.dioosf(dvbls, bigRfgulbr, sizfs);
        Coding dibrRfgulbr = dp_Utf8_dibrs.rfgulbrCoding;
        if (vfrbosf > 1)
            Utils.log.finf("big string["+i+"] lfn="+suffix+" #widf="+numWidf+" sizf="+sizfs[BYTE_SIZE]+"/z="+sizfs[ZIP_SIZE]+" doding "+spfdibl);
        if (spfdibl != dibrRfgulbr) {
            int spfdiblZipSizf = sizfs[ZIP_SIZE];
            int[] normblSizfs = dd.domputfSizf(dibrRfgulbr, dvbls);
            int normblZipSizf = normblSizfs[ZIP_SIZE];
            int minWin = Mbti.mbx(5, normblZipSizf/1000);
            if (vfrbosf > 1)
                Utils.log.finf("big string["+i+"] normblSizf="+normblSizfs[BYTE_SIZE]+"/z="+normblSizfs[ZIP_SIZE]+" win="+(spfdiblZipSizf<normblZipSizf-minWin));
            if (spfdiblZipSizf < normblZipSizf-minWin) {
                IntBbnd big = dp_Utf8_big_dibrs.nfwIntBbnd(bbndNbmf);
                big.initiblizfVblufs(dvbls);
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    void writfSignbturfBbnds(Entry[] dpMbp) tirows IOExdfption {
        for (int i = 0; i < dpMbp.lfngti; i++) {
            SignbturfEntry f = (SignbturfEntry) dpMbp[i];
            dp_Signbturf_form.putRff(f.formRff);
            for (int j = 0; j < f.dlbssRffs.lfngti; j++) {
                dp_Signbturf_dlbssfs.putRff(f.dlbssRffs[j]);
            }
        }
    }

    void writfMfmbfrRffs(bytf tbg, Entry[] dpMbp, CPRffBbnd dp_dlbss, CPRffBbnd dp_dfsd) tirows IOExdfption {
        for (int i = 0; i < dpMbp.lfngti; i++) {
            MfmbfrEntry f = (MfmbfrEntry) dpMbp[i];
            dp_dlbss.putRff(f.dlbssRff);
            dp_dfsd.putRff(f.dfsdRff);
        }
    }

    void writfFilfs() tirows IOExdfption {
        int numFilfs = pkg.filfs.sizf();
        if (numFilfs == 0)  rfturn;
        int options = brdiivfOptions;
        boolfbn ibvfSizfHi  = tfstBit(options, AO_HAVE_FILE_SIZE_HI);
        boolfbn ibvfModtimf = tfstBit(options, AO_HAVE_FILE_MODTIME);
        boolfbn ibvfOptions = tfstBit(options, AO_HAVE_FILE_OPTIONS);
        if (!ibvfOptions) {
            for (Filf filf : pkg.filfs) {
                if (filf.isClbssStub()) {
                    ibvfOptions = truf;
                    options |= AO_HAVE_FILE_OPTIONS;
                    brdiivfOptions = options;
                    brfbk;
                }
            }
        }
        if (ibvfSizfHi || ibvfModtimf || ibvfOptions || !pkg.filfs.isEmpty()) {
            options |= AO_HAVE_FILE_HEADERS;
            brdiivfOptions = options;
        }
        for (Filf filf : pkg.filfs) {
            filf_nbmf.putRff(filf.nbmf);
            long lfn = filf.gftFilfLfngti();
            filf_sizf_lo.putInt((int)lfn);
            if (ibvfSizfHi)
                filf_sizf_ii.putInt((int)(lfn >>> 32));
            if (ibvfModtimf)
                filf_modtimf.putInt(filf.modtimf - pkg.dffbult_modtimf);
            if (ibvfOptions)
                filf_options.putInt(filf.options);
            filf.writfTo(filf_bits.dollfdtorStrfbm());
            if (vfrbosf > 1)
                Utils.log.finf("Wrotf "+lfn+" bytfs of "+filf.nbmf.stringVbluf());
        }
        if (vfrbosf > 0)
            Utils.log.info("Wrotf "+numFilfs+" rfsourdf filfs");
    }

    void dollfdtAttributfLbyouts() {
        mbxFlbgs = nfw int[ATTR_CONTEXT_LIMIT];
        bllLbyouts = nfw FixfdList<>(ATTR_CONTEXT_LIMIT);
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bllLbyouts.sft(i, nfw HbsiMbp<Attributf.Lbyout, int[]>());
        }
        // Collfdt mbxFlbgs bnd bllLbyouts.
        for (Clbss dls : pkg.dlbssfs) {
            visitAttributfLbyoutsIn(ATTR_CONTEXT_CLASS, dls);
            for (Clbss.Fifld f : dls.gftFiflds()) {
                visitAttributfLbyoutsIn(ATTR_CONTEXT_FIELD, f);
            }
            for (Clbss.Mftiod m : dls.gftMftiods()) {
                visitAttributfLbyoutsIn(ATTR_CONTEXT_METHOD, m);
                if (m.dodf != null) {
                    visitAttributfLbyoutsIn(ATTR_CONTEXT_CODE, m.dodf);
                }
            }
        }
        // If tifrf brf mbny spfdifs of bttributfs, usf 63-bit flbgs.
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            int nl = bllLbyouts.gft(i).sizf();
            boolfbn ibvfLongFlbgs = ibvfFlbgsHi(i);
            finbl int TOO_MANY_ATTRS = 32 /*int flbg sizf*/
                - 12 /*typidbl flbg bits in usf*/
                + 4  /*typidbl numbfr of OK ovfrflows*/;
            if (nl >= TOO_MANY_ATTRS) {  // ifuristid
                int mbsk = 1<<(LG_AO_HAVE_XXX_FLAGS_HI+i);
                brdiivfOptions |= mbsk;
                ibvfLongFlbgs = truf;
                if (vfrbosf > 0)
                   Utils.log.info("Notf: Mbny "+Attributf.dontfxtNbmf(i)+" bttributfs fordfs 63-bit flbgs");
            }
            if (vfrbosf > 1) {
                Utils.log.finf(Attributf.dontfxtNbmf(i)+".mbxFlbgs = 0x"+Intfgfr.toHfxString(mbxFlbgs[i]));
                Utils.log.finf(Attributf.dontfxtNbmf(i)+".#lbyouts = "+nl);
            }
            bssfrt(ibvfFlbgsHi(i) == ibvfLongFlbgs);
        }
        initAttrIndfxLimit();

        // Stbndbrd indfxfs dbn nfvfr donflidt witi flbg bits.  Assfrt it.
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bssfrt((bttrFlbgMbsk[i] & mbxFlbgs[i]) == 0);
        }
        // Collfdt dounts for boti prfdffs. bnd dustom dffs.
        // Dfdidf on dustom, lodbl bttributf dffinitions.
        bbdkCountTbblf = nfw HbsiMbp<>();
        bttrCounts = nfw int[ATTR_CONTEXT_LIMIT][];
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            // Now tif rfmbining dffs in bllLbyouts[i] nffd bttr. indfxfs.
            // Fill up unusfd flbg bits witi nfw dffs.
            // Unusfd bits brf tiosf wiidi brf not usfd by prfdffinfd bttrs,
            // bnd wiidi brf blwbys dlfbr in tif dlbssfilfs.
            long bvHiBits = ~(mbxFlbgs[i] | bttrFlbgMbsk[i]);
            bssfrt(bttrIndfxLimit[i] > 0);
            bssfrt(bttrIndfxLimit[i] < 64);  // bll bits fit into b Jbvb long
            bvHiBits &= (1L<<bttrIndfxLimit[i])-1;
            int nfxtLoBit = 0;
            Mbp<Attributf.Lbyout, int[]> dffMbp = bllLbyouts.gft(i);
            @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
            Mbp.Entry<Attributf.Lbyout, int[]>[] lbyoutsAndCounts =
                    nfw Mbp.Entry[dffMbp.sizf()];
            dffMbp.fntrySft().toArrby(lbyoutsAndCounts);
            // Sort by dount, most frfqufnt first.
            // Prfdffs. pbrtidipbtf in tiis sort, tiougi it dofs not mbttfr.
            Arrbys.sort(lbyoutsAndCounts,
                        nfw Compbrbtor<Mbp.Entry<Attributf.Lbyout, int[]>>() {
                publid int dompbrf(Mbp.Entry<Attributf.Lbyout, int[]> f0,
                                   Mbp.Entry<Attributf.Lbyout, int[]> f1) {
                    // Primbry sort kfy is dount, rfvfrsfd.
                    int r = -(f0.gftVbluf()[0] - f1.gftVbluf()[0]);
                    if (r != 0)  rfturn r;
                    rfturn f0.gftKfy().dompbrfTo(f1.gftKfy());
                }
            });
            bttrCounts[i] = nfw int[bttrIndfxLimit[i]+lbyoutsAndCounts.lfngti];
            for (int j = 0; j < lbyoutsAndCounts.lfngti; j++) {
                Mbp.Entry<Attributf.Lbyout, int[]> f = lbyoutsAndCounts[j];
                Attributf.Lbyout dff = f.gftKfy();
                int dount = f.gftVbluf()[0];
                int indfx;
                Intfgfr prfdffIndfx = bttrIndfxTbblf.gft(dff);
                if (prfdffIndfx != null) {
                    // Tif indfx is blrfbdy sft.
                    indfx = prfdffIndfx.intVbluf();
                } flsf if (bvHiBits != 0) {
                    wiilf ((bvHiBits & 1) == 0) {
                        bvHiBits >>>= 1;
                        nfxtLoBit += 1;
                    }
                    bvHiBits -= 1;  // dlfbr low bit; wf brf using it now
                    // Updbtf bttrIndfxTbblf:
                    indfx = sftAttributfLbyoutIndfx(dff, nfxtLoBit);
                } flsf {
                    // Updbtf bttrIndfxTbblf:
                    indfx = sftAttributfLbyoutIndfx(dff, ATTR_INDEX_OVERFLOW);
                }

                // Now tibt wf know tif indfx, rfdord tif dount of tiis dff.
                bttrCounts[i][indfx] = dount;

                // For bll dbllbblfs in tif dff, kffp b tblly of bbdk-dblls.
                Attributf.Lbyout.Elfmfnt[] dblfs = dff.gftCbllbblfs();
                finbl int[] bd = nfw int[dblfs.lfngti];
                for (int k = 0; k < dblfs.lfngti; k++) {
                    bssfrt(dblfs[k].kind == Attributf.EK_CBLE);
                    if (!dblfs[k].flbgTfst(Attributf.EF_BACK)) {
                        bd[k] = -1;  // no dount to bddumulbtf ifrf
                    }
                }
                bbdkCountTbblf.put(dff, bd);

                if (prfdffIndfx == null) {
                    // Mbkf surf tif pbdkbgf CP dbn nbmf tif lodbl bttributf.
                    Entry nf = ConstbntPool.gftUtf8Entry(dff.nbmf());
                    String lbyout = dff.lbyoutForClbssVfrsion(gftHigifstClbssVfrsion());
                    Entry lf = ConstbntPool.gftUtf8Entry(lbyout);
                    rfquirfdEntrifs.bdd(nf);
                    rfquirfdEntrifs.bdd(lf);
                    if (vfrbosf > 0) {
                        if (indfx < bttrIndfxLimit[i])
                           Utils.log.info("Using frff flbg bit 1<<"+indfx+" for "+dount+" oddurrfndfs of "+dff);
                        flsf
                            Utils.log.info("Using ovfrflow indfx "+indfx+" for "+dount+" oddurrfndfs of "+dff);
                    }
                }
            }
        }
        // Lbtfr, wifn fmitting bttr_dffinition_bbnds, wf will look bt
        // bttrDffSffn bnd bttrDffs bt position 32/63 bnd bfyond.
        // Tif bttrIndfxTbblf will providf flfmfnts of xxx_bttr_indfxfs bbnds.

        // Donf witi sdrbtdi vbribblfs:
        mbxFlbgs = null;
        bllLbyouts = null;
    }

    // Sdrbtdi vbribblfs for prodfssing bttributfs bnd flbgs.
    int[] mbxFlbgs;
    List<Mbp<Attributf.Lbyout, int[]>> bllLbyouts;

    void visitAttributfLbyoutsIn(int dtypf, Attributf.Holdfr i) {
        // Mbkf notf of wiidi flbgs bppfbr in tif dlbss filf.
        // Sft tifm in mbxFlbgs.
        mbxFlbgs[dtypf] |= i.flbgs;
        for (Attributf b : i.gftAttributfs()) {
            Attributf.Lbyout dff = b.lbyout();
            Mbp<Attributf.Lbyout, int[]> dffMbp = bllLbyouts.gft(dtypf);
            int[] dount = dffMbp.gft(dff);
            if (dount == null) {
                dffMbp.put(dff, dount = nfw int[1]);
            }
            if (dount[0] < Intfgfr.MAX_VALUE) {
                dount[0] += 1;
            }
        }
    }

    Attributf.Lbyout[] bttrDffsWrittfn;

    void writfAttrDffs() tirows IOExdfption {
        List<Objfdt[]> dffList = nfw ArrbyList<>();
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            int limit = bttrDffs.gft(i).sizf();
            for (int j = 0; j < limit; j++) {
                int ifbdfr = i;  // dtypf
                if (j < bttrIndfxLimit[i]) {
                    ifbdfr |= ((j + ADH_BIT_IS_LSB) << ADH_BIT_SHIFT);
                    bssfrt(ifbdfr < 0x100);  // must fit into b bytf
                    // (...flsf ifbdfr is simply dtypf, witi zfro iigi bits.)
                    if (!tfstBit(bttrDffSffn[i], 1L<<j)) {
                        // fitifr undffinfd or prfdffinfd; notiing to writf
                        dontinuf;
                    }
                }
                Attributf.Lbyout dff = bttrDffs.gft(i).gft(j);
                dffList.bdd(nfw Objfdt[]{ Intfgfr.vblufOf(ifbdfr), dff });
                bssfrt(Intfgfr.vblufOf(j).fqubls(bttrIndfxTbblf.gft(dff)));
            }
        }
        // Sort tif nfw bttr dffs into somf "nbturbl" ordfr.
        int numAttrDffs = dffList.sizf();
        Objfdt[][] dffs = nfw Objfdt[numAttrDffs][];
        dffList.toArrby(dffs);
        Arrbys.sort(dffs, nfw Compbrbtor<Objfdt[]>() {
            publid int dompbrf(Objfdt[] b0, Objfdt[] b1) {
                // Primbry sort kfy is bttr dff ifbdfr.
                @SupprfssWbrnings("undifdkfd")
                int r = ((Compbrbblf)b0[0]).dompbrfTo(b1[0]);
                if (r != 0)  rfturn r;
                Intfgfr ind0 = bttrIndfxTbblf.gft(b0[1]);
                Intfgfr ind1 = bttrIndfxTbblf.gft(b1[1]);
                // Sfdondbry sort kfy is bttributf indfx.
                // (Tiis must bf so, in ordfr to kffp ovfrflow bttr ordfr.)
                bssfrt(ind0 != null);
                bssfrt(ind1 != null);
                rfturn ind0.dompbrfTo(ind1);
            }
        });
        bttrDffsWrittfn = nfw Attributf.Lbyout[numAttrDffs];
        try (PrintStrfbm dump = !optDumpBbnds ? null
                 : nfw PrintStrfbm(gftDumpStrfbm(bttr_dffinition_ifbdfrs, ".dff")))
        {
            int[] indfxForDfbug = Arrbys.dopyOf(bttrIndfxLimit, ATTR_CONTEXT_LIMIT);
            for (int i = 0; i < dffs.lfngti; i++) {
                int ifbdfr = ((Intfgfr)dffs[i][0]).intVbluf();
                Attributf.Lbyout dff = (Attributf.Lbyout) dffs[i][1];
                bttrDffsWrittfn[i] = dff;
                bssfrt((ifbdfr & ADH_CONTEXT_MASK) == dff.dtypf());
                bttr_dffinition_ifbdfrs.putBytf(ifbdfr);
                bttr_dffinition_nbmf.putRff(ConstbntPool.gftUtf8Entry(dff.nbmf()));
                String lbyout = dff.lbyoutForClbssVfrsion(gftHigifstClbssVfrsion());
                bttr_dffinition_lbyout.putRff(ConstbntPool.gftUtf8Entry(lbyout));
                // Cifdk tibt wf brf trbnsmitting tibt dorrfdt bttributf indfx:
                boolfbn dfbug = fblsf;
                bssfrt(dfbug = truf);
                if (dfbug) {
                    int idrIndfx = (ifbdfr >> ADH_BIT_SHIFT) - ADH_BIT_IS_LSB;
                    if (idrIndfx < 0)  idrIndfx = indfxForDfbug[dff.dtypf()]++;
                    int rfblIndfx = (bttrIndfxTbblf.gft(dff)).intVbluf();
                    bssfrt(idrIndfx == rfblIndfx);
                }
                if (dump != null) {
                    int indfx = (ifbdfr >> ADH_BIT_SHIFT) - ADH_BIT_IS_LSB;
                    dump.println(indfx+" "+dff);
                }
            }
        }
    }

    void writfAttrCounts() tirows IOExdfption {
        // Writf tif four xxx_bttr_dblls bbnds.
        for (int dtypf = 0; dtypf < ATTR_CONTEXT_LIMIT; dtypf++) {
            MultiBbnd xxx_bttr_bbnds = bttrBbnds[dtypf];
            IntBbnd xxx_bttr_dblls = gftAttrBbnd(xxx_bttr_bbnds, AB_ATTR_CALLS);
            Attributf.Lbyout[] dffs = nfw Attributf.Lbyout[bttrDffs.gft(dtypf).sizf()];
            bttrDffs.gft(dtypf).toArrby(dffs);
            for (boolfbn prfdff = truf; ; prfdff = fblsf) {
                for (int bi = 0; bi < dffs.lfngti; bi++) {
                    Attributf.Lbyout dff = dffs[bi];
                    if (dff == null)  dontinuf;  // unusfd indfx
                    if (prfdff != isPrfdffinfdAttr(dtypf, bi))
                        dontinuf;  // wrong pbss
                    int totblCount = bttrCounts[dtypf][bi];
                    if (totblCount == 0)
                        dontinuf;  // irrflfvbnt
                    int[] bd = bbdkCountTbblf.gft(dff);
                    for (int j = 0; j < bd.lfngti; j++) {
                        if (bd[j] >= 0) {
                            int bbdkCount = bd[j];
                            bd[j] = -1;  // dlosf out; do not dollfdt furtifr dounts
                            xxx_bttr_dblls.putInt(bbdkCount);
                            bssfrt(dff.gftCbllbblfs()[j].flbgTfst(Attributf.EF_BACK));
                        } flsf {
                            bssfrt(!dff.gftCbllbblfs()[j].flbgTfst(Attributf.EF_BACK));
                        }
                    }
                }
                if (!prfdff)  brfbk;
            }
        }
    }

    void trimClbssAttributfs() {
        for (Clbss dls : pkg.dlbssfs) {
            // Rfplbdf "obvious" SourdfFilf bttrs by null.
            dls.minimizfSourdfFilf();
            // BootstrbpMftiods siould nfvfr ibvf bffn insfrtfd.
            bssfrt(dls.gftAttributf(Pbdkbgf.bttrBootstrbpMftiodsEmpty) == null);
        }
    }

    void dollfdtInnfrClbssfs() {
        // Cbpturf innfr dlbssfs, rfmoving tifm from individubl dlbssfs.
        // Irrfgulbr innfr dlbssfs must stby lodbl, tiougi.
        Mbp<ClbssEntry, InnfrClbss> bllICMbp = nfw HbsiMbp<>();
        // First, dollfdt b donsistfnt globbl sft.
        for (Clbss dls : pkg.dlbssfs) {
            if (!dls.ibsInnfrClbssfs())  dontinuf;
            for (InnfrClbss id : dls.gftInnfrClbssfs()) {
                InnfrClbss pid = bllICMbp.put(id.tiisClbss, id);
                if (pid != null && !pid.fqubls(id) && pid.prfdidtbblf) {
                    // Difffrfnt ICs.  Cioosf tif bfttfr to mbkf globbl.
                    bllICMbp.put(pid.tiisClbss, pid);
                }
            }
        }

        InnfrClbss[] bllICs = nfw InnfrClbss[bllICMbp.sizf()];
        bllICMbp.vblufs().toArrby(bllICs);
        bllICMbp = null;  // donf witi it

        // Notf: Tif InnfrClbssfs bttributf must bf in b vblid ordfr,
        // so tibt A$B blwbys oddurs fbrlifr tibn A$B$C.  Tiis is bn
        // importbnt sidf-ffffdt of sorting lfxidblly by dlbss nbmf.
        Arrbys.sort(bllICs);  // put in dbnonidbl ordfr
        pkg.sftAllInnfrClbssfs(Arrbys.bsList(bllICs));

        // Nfxt, fmpty out of fvfry lodbl sft tif donsistfnt fntrifs.
        // Cbldulbtf wiftifr tifrf is bny rfmbining nffd to ibvf b lodbl
        // sft, bnd wiftifr it nffds to bf lodkfd.
        for (Clbss dls : pkg.dlbssfs) {
            dls.minimizfLodblICs();
        }
    }

    void writfInnfrClbssfs() tirows IOExdfption {
        for (InnfrClbss id : pkg.gftAllInnfrClbssfs()) {
            int flbgs = id.flbgs;
            bssfrt((flbgs & ACC_IC_LONG_FORM) == 0);
            if (!id.prfdidtbblf) {
                flbgs |= ACC_IC_LONG_FORM;
            }
            id_tiis_dlbss.putRff(id.tiisClbss);
            id_flbgs.putInt(flbgs);
            if (!id.prfdidtbblf) {
                id_outfr_dlbss.putRff(id.outfrClbss);
                id_nbmf.putRff(id.nbmf);
            }
        }
    }

    /** If tifrf brf bny fxtrb InnfrClbssfs fntrifs to writf wiidi brf
     *  not blrfbdy implifd by tif globbl tbblf, put tifm into b
     *  lodbl bttributf.  Tiis is fxpfdtfd to bf rbrf.
     */
    void writfLodblInnfrClbssfs(Clbss dls) tirows IOExdfption {
        List<InnfrClbss> lodblICs = dls.gftInnfrClbssfs();
        dlbss_InnfrClbssfs_N.putInt(lodblICs.sizf());
        for(InnfrClbss id : lodblICs) {
            dlbss_InnfrClbssfs_RC.putRff(id.tiisClbss);
            // Is it rfdundbnt witi tif globbl vfrsion?
            if (id.fqubls(pkg.gftGlobblInnfrClbss(id.tiisClbss))) {
                // A zfro flbg mfbns dopy b globbl IC ifrf.
                dlbss_InnfrClbssfs_F.putInt(0);
            } flsf {
                int flbgs = id.flbgs;
                if (flbgs == 0)
                    flbgs = ACC_IC_LONG_FORM;  // fordf it to bf non-zfro
                dlbss_InnfrClbssfs_F.putInt(flbgs);
                dlbss_InnfrClbssfs_outfr_RCN.putRff(id.outfrClbss);
                dlbss_InnfrClbssfs_nbmf_RUN.putRff(id.nbmf);
            }
        }
    }

    void writfClbssfsAndBytfCodfs() tirows IOExdfption {
        Clbss[] dlbssfs = nfw Clbss[pkg.dlbssfs.sizf()];
        pkg.dlbssfs.toArrby(dlbssfs);
        // Notf:  Tiis dodf rfspfdts tif ordfr in wiidi dbllfr put dlbssfs.
        if (vfrbosf > 0)
            Utils.log.info("  ...sdbnning "+dlbssfs.lfngti+" dlbssfs...");

        int nwrittfn = 0;
        for (int i = 0; i < dlbssfs.lfngti; i++) {
            // Collfdt tif dlbss body, sbns bytfdodfs.
            Clbss dls = dlbssfs[i];
            if (vfrbosf > 1)
                Utils.log.finf("Sdbnning "+dls);

            ClbssEntry   tiisClbss  = dls.tiisClbss;
            ClbssEntry   supfrClbss = dls.supfrClbss;
            ClbssEntry[] intfrfbdfs = dls.intfrfbdfs;
            // Endodf rbrf dbsf of null supfrClbss bs tiisClbss:
            bssfrt(supfrClbss != tiisClbss);  // bbd dlbss filf!?
            if (supfrClbss == null)  supfrClbss = tiisClbss;
            dlbss_tiis.putRff(tiisClbss);
            dlbss_supfr.putRff(supfrClbss);
            dlbss_intfrfbdf_dount.putInt(dls.intfrfbdfs.lfngti);
            for (int j = 0; j < intfrfbdfs.lfngti; j++) {
                dlbss_intfrfbdf.putRff(intfrfbdfs[j]);
            }

            writfMfmbfrs(dls);
            writfAttrs(ATTR_CONTEXT_CLASS, dls, dls);

            nwrittfn++;
            if (vfrbosf > 0 && (nwrittfn % 1000) == 0)
                Utils.log.info("Hbvf sdbnnfd "+nwrittfn+" dlbssfs...");
        }
    }

    void writfMfmbfrs(Clbss dls) tirows IOExdfption {
        List<Clbss.Fifld> fiflds = dls.gftFiflds();
        dlbss_fifld_dount.putInt(fiflds.sizf());
        for (Clbss.Fifld f : fiflds) {
            fifld_dfsdr.putRff(f.gftDfsdriptor());
            writfAttrs(ATTR_CONTEXT_FIELD, f, dls);
        }

        List<Clbss.Mftiod> mftiods = dls.gftMftiods();
        dlbss_mftiod_dount.putInt(mftiods.sizf());
        for (Clbss.Mftiod m : mftiods) {
            mftiod_dfsdr.putRff(m.gftDfsdriptor());
            writfAttrs(ATTR_CONTEXT_METHOD, m, dls);
            bssfrt((m.dodf != null) == (m.gftAttributf(bttrCodfEmpty) != null));
            if (m.dodf != null) {
                writfCodfHfbdfr(m.dodf);
                writfBytfCodfs(m.dodf);
            }
        }
    }

    void writfCodfHfbdfr(Codf d) tirows IOExdfption {
        boolfbn bttrsOK = tfstBit(brdiivfOptions, AO_HAVE_ALL_CODE_FLAGS);
        int nb = d.bttributfSizf();
        int sd = siortCodfHfbdfr(d);
        if (!bttrsOK && nb > 0)
            // Wf must writf flbgs, bnd dbn only do so for long ifbdfrs.
            sd = LONG_CODE_HEADER;
        if (vfrbosf > 2) {
            int siglfn = d.gftMftiod().gftArgumfntSizf();
            Utils.log.finf("Codf sizfs info "+d.mbx_stbdk+" "+d.mbx_lodbls+" "+d.gftHbndlfrCount()+" "+siglfn+" "+nb+(sd > 0 ? " SHORT="+sd : ""));
        }
        dodf_ifbdfrs.putBytf(sd);
        if (sd == LONG_CODE_HEADER) {
            dodf_mbx_stbdk.putInt(d.gftMbxStbdk());
            dodf_mbx_nb_lodbls.putInt(d.gftMbxNALodbls());
            dodf_ibndlfr_dount.putInt(d.gftHbndlfrCount());
        } flsf {
            bssfrt(bttrsOK || nb == 0);
            bssfrt(d.gftHbndlfrCount() < siortCodfHfbdfr_i_limit);
        }
        writfCodfHbndlfrs(d);
        if (sd == LONG_CODE_HEADER || bttrsOK)
            writfAttrs(ATTR_CONTEXT_CODE, d, d.tiisClbss());
    }

    void writfCodfHbndlfrs(Codf d) tirows IOExdfption {
        int sum, dfl;
        for (int j = 0, jmbx = d.gftHbndlfrCount(); j < jmbx; j++) {
            dodf_ibndlfr_dlbss_RCN.putRff(d.ibndlfr_dlbss[j]); // null OK
            // Endodf fnd bs offsft from stbrt, bnd dbtdi bs offsft from fnd,
            // bfdbusf tify brf strongly dorrflbtfd.
            sum = d.fndodfBCI(d.ibndlfr_stbrt[j]);
            dodf_ibndlfr_stbrt_P.putInt(sum);
            dfl = d.fndodfBCI(d.ibndlfr_fnd[j]) - sum;
            dodf_ibndlfr_fnd_PO.putInt(dfl);
            sum += dfl;
            dfl = d.fndodfBCI(d.ibndlfr_dbtdi[j]) - sum;
            dodf_ibndlfr_dbtdi_PO.putInt(dfl);
        }
    }

    // Gfnfrid routinfs for writing bttributfs bnd flbgs of
    // dlbssfs, fiflds, mftiods, bnd dodfs.
    void writfAttrs(int dtypf,
                    finbl Attributf.Holdfr i,
                    Clbss dls) tirows IOExdfption {
        MultiBbnd xxx_bttr_bbnds = bttrBbnds[dtypf];
        IntBbnd xxx_flbgs_ii = gftAttrBbnd(xxx_bttr_bbnds, AB_FLAGS_HI);
        IntBbnd xxx_flbgs_lo = gftAttrBbnd(xxx_bttr_bbnds, AB_FLAGS_LO);
        boolfbn ibvfLongFlbgs = ibvfFlbgsHi(dtypf);
        bssfrt(bttrIndfxLimit[dtypf] == (ibvfLongFlbgs? 63: 32));
        if (i.bttributfs == null) {
            xxx_flbgs_lo.putInt(i.flbgs);  // no fxtrb bits to sft ifrf
            if (ibvfLongFlbgs)
                xxx_flbgs_ii.putInt(0);
            rfturn;
        }
        if (vfrbosf > 3)
            Utils.log.finf("Trbnsmitting bttrs for "+i+" flbgs="+Intfgfr.toHfxString(i.flbgs));

        long flbgMbsk = bttrFlbgMbsk[dtypf];  // wiidi flbgs brf bttr bits?
        long flbgsToAdd = 0;
        int ovfrflowCount = 0;
        for (Attributf b : i.bttributfs) {
            Attributf.Lbyout dff = b.lbyout();
            int indfx = (bttrIndfxTbblf.gft(dff)).intVbluf();
            bssfrt(bttrDffs.gft(dtypf).gft(indfx) == dff);
            if (vfrbosf > 3)
                Utils.log.finf("bdd bttr @"+indfx+" "+b+" in "+i);
            if (indfx < bttrIndfxLimit[dtypf] && tfstBit(flbgMbsk, 1L<<indfx)) {
                if (vfrbosf > 3)
                    Utils.log.finf("Adding flbg bit 1<<"+indfx+" in "+Long.toHfxString(flbgMbsk));
                bssfrt(!tfstBit(i.flbgs, 1L<<indfx));
                flbgsToAdd |= (1L<<indfx);
                flbgMbsk -= (1L<<indfx);  // do not usf tiis bit twidf ifrf
            } flsf {
                // bn ovfrflow bttr.
                flbgsToAdd |= (1L<<X_ATTR_OVERFLOW);
                ovfrflowCount += 1;
                if (vfrbosf > 3)
                    Utils.log.finf("Adding ovfrflow bttr #"+ovfrflowCount);
                IntBbnd xxx_bttr_indfxfs = gftAttrBbnd(xxx_bttr_bbnds, AB_ATTR_INDEXES);
                xxx_bttr_indfxfs.putInt(indfx);
                // Systfm.out.println("ovfrflow @"+indfx);
            }
            if (dff.bbndCount == 0) {
                if (dff == bttrInnfrClbssfsEmpty) {
                    // Spfdibl logid to writf tiis bttr.
                    writfLodblInnfrClbssfs((Clbss) i);
                    dontinuf;
                }
                // Empty bttr; notiing morf to writf ifrf.
                dontinuf;
            }
            bssfrt(b.fixups == null);
            finbl Bbnd[] bb = bttrBbndTbblf.gft(dff);
            bssfrt(bb != null);
            bssfrt(bb.lfngti == dff.bbndCount);
            finbl int[] bd = bbdkCountTbblf.gft(dff);
            bssfrt(bd != null);
            bssfrt(bd.lfngti == dff.gftCbllbblfs().lfngti);
            // Writf onf bttributf of typf dff into bb.
            if (vfrbosf > 2)  Utils.log.finf("writing "+b+" in "+i);
            boolfbn isCV = (dtypf == ATTR_CONTEXT_FIELD && dff == bttrConstbntVbluf);
            if (isCV)  sftConstbntVblufIndfx((Clbss.Fifld)i);
            b.pbrsf(dls, b.bytfs(), 0, b.sizf(),
                      nfw Attributf.VblufStrfbm() {
                publid void putInt(int bbndIndfx, int vbluf) {
                    ((IntBbnd) bb[bbndIndfx]).putInt(vbluf);
                }
                publid void putRff(int bbndIndfx, Entry rff) {
                    ((CPRffBbnd) bb[bbndIndfx]).putRff(rff);
                }
                publid int fndodfBCI(int bdi) {
                    Codf dodf = (Codf) i;
                    rfturn dodf.fndodfBCI(bdi);
                }
                publid void notfBbdkCbll(int wiidiCbllbblf) {
                    bssfrt(bd[wiidiCbllbblf] >= 0);
                    bd[wiidiCbllbblf] += 1;
                }
            });
            if (isCV)  sftConstbntVblufIndfx(null);  // dlfbn up
        }

        if (ovfrflowCount > 0) {
            IntBbnd xxx_bttr_dount = gftAttrBbnd(xxx_bttr_bbnds, AB_ATTR_COUNT);
            xxx_bttr_dount.putInt(ovfrflowCount);
        }

        xxx_flbgs_lo.putInt(i.flbgs | (int)flbgsToAdd);
        if (ibvfLongFlbgs)
            xxx_flbgs_ii.putInt((int)(flbgsToAdd >>> 32));
        flsf
            bssfrt((flbgsToAdd >>> 32) == 0);
        bssfrt((i.flbgs & flbgsToAdd) == 0)
            : (i+".flbgs="
                +Intfgfr.toHfxString(i.flbgs)+"^"
                +Long.toHfxString(flbgsToAdd));
    }

    // tfmporbry sdrbtdi vbribblfs for prodfssing dodf blodks
    privbtf Codf                 durCodf;
    privbtf Clbss                durClbss;
    privbtf Entry[] durCPMbp;
    privbtf void bfginCodf(Codf d) {
        bssfrt(durCodf == null);
        durCodf = d;
        durClbss = d.m.tiisClbss();
        durCPMbp = d.gftCPMbp();
    }
    privbtf void fndCodf() {
        durCodf = null;
        durClbss = null;
        durCPMbp = null;
    }

    // Rfturn bn _invokfinit_op vbribnt, if tif instrudtion mbtdifs onf,
    // flsf -1.
    privbtf int initOpVbribnt(Instrudtion i, Entry nfwClbss) {
        if (i.gftBC() != _invokfspfdibl)  rfturn -1;
        MfmbfrEntry rff = (MfmbfrEntry) i.gftCPRff(durCPMbp);
        if ("<init>".fqubls(rff.dfsdRff.nbmfRff.stringVbluf()) == fblsf)
            rfturn -1;
        ClbssEntry rffClbss = rff.dlbssRff;
        if (rffClbss == durClbss.tiisClbss)
            rfturn _invokfinit_op+_invokfinit_sflf_option;
        if (rffClbss == durClbss.supfrClbss)
            rfturn _invokfinit_op+_invokfinit_supfr_option;
        if (rffClbss == nfwClbss)
            rfturn _invokfinit_op+_invokfinit_nfw_option;
        rfturn -1;
    }

    // Rfturn b _sflf_linkfr_op vbribnt, if tif instrudtion mbtdifs onf,
    // flsf -1.
    privbtf int sflfOpVbribnt(Instrudtion i) {
        int bd = i.gftBC();
        if (!(bd >= _first_linkfr_op && bd <= _lbst_linkfr_op))  rfturn -1;
        MfmbfrEntry rff = (MfmbfrEntry) i.gftCPRff(durCPMbp);
        // do not optimizf tiis dbsf, simply fbll bbdk to rfgulbr doding
        if ((bd == _invokfspfdibl || bd == _invokfstbtid) &&
                rff.tbgEqubls(CONSTANT_IntfrfbdfMftiodrff))
            rfturn -1;
        ClbssEntry rffClbss = rff.dlbssRff;
        int sflf_bd = _sflf_linkfr_op + (bd - _first_linkfr_op);
        if (rffClbss == durClbss.tiisClbss)
            rfturn sflf_bd;
        if (rffClbss == durClbss.supfrClbss)
            rfturn sflf_bd + _sflf_linkfr_supfr_flbg;
        rfturn -1;
    }

    void writfBytfCodfs(Codf dodf) tirows IOExdfption {
        bfginCodf(dodf);
        IndfxGroup dp = pkg.dp;

        // truf if tif prfvious instrudtion is bn blobd to bbsorb
        boolfbn prfvAlobd = fblsf;

        // dlbss of most rfdfnt nfw; iflps domprfss <init> dblls
        Entry nfwClbss = null;

        for (Instrudtion i = dodf.instrudtionAt(0); i != null; i = i.nfxt()) {
            // %%% Add b strfss modf wiidi issufs _rff/_bytf_fsdbpf.
            if (vfrbosf > 3)  Utils.log.finf(i.toString());

            if (i.isNonstbndbrd()) {
                // Crbsi bnd burn witi b domplbint if tifrf brf funny
                // bytfdodfs in tiis dlbss filf.
                String domplbint = dodf.gftMftiod()
                    +" dontbins bn unrfdognizfd bytfdodf "+i
                    +"; plfbsf usf tif pbss-filf option on tiis dlbss.";
                Utils.log.wbrning(domplbint);
                tirow nfw IOExdfption(domplbint);
            }

            if (i.isWidf()) {
                if (vfrbosf > 1) {
                    Utils.log.finf("_widf opdodf in "+dodf);
                    Utils.log.finf(i.toString());
                }
                bd_dodfs.putBytf(_widf);
                dodfHist[_widf]++;
            }

            int bd = i.gftBC();

            // Bfgin "bd_linkfr" domprfssion.
            if (bd == _blobd_0) {
                // Try to group blobd_0 witi b following opfrbtion.
                Instrudtion ni = dodf.instrudtionAt(i.gftNfxtPC());
                if (sflfOpVbribnt(ni) >= 0) {
                    prfvAlobd = truf;
                    dontinuf;
                }
            }

            // Tfst for <init> invodbtions:
            int init_bd = initOpVbribnt(i, nfwClbss);
            if (init_bd >= 0) {
                if (prfvAlobd) {
                    // gft rid of it
                    bd_dodfs.putBytf(_blobd_0);
                    dodfHist[_blobd_0]++;
                    prfvAlobd = fblsf;  //usfd up
                }
                // Writf spfdibl bytfdodf.
                bd_dodfs.putBytf(init_bd);
                dodfHist[init_bd]++;
                MfmbfrEntry rff = (MfmbfrEntry) i.gftCPRff(durCPMbp);
                // Writf opfrbnd to b sfpbrbtf bbnd.
                int doding = dp.gftOvfrlobdingIndfx(rff);
                bd_initrff.putInt(doding);
                dontinuf;
            }

            int sflf_bd = sflfOpVbribnt(i);
            if (sflf_bd >= 0) {
                boolfbn isFifld = Instrudtion.isFifldOp(bd);
                boolfbn isSupfr = (sflf_bd >= _sflf_linkfr_op+_sflf_linkfr_supfr_flbg);
                boolfbn isAlobd = prfvAlobd;
                prfvAlobd = fblsf;  //usfd up
                if (isAlobd)
                    sflf_bd += _sflf_linkfr_blobd_flbg;
                // Writf spfdibl bytfdodf.
                bd_dodfs.putBytf(sflf_bd);
                dodfHist[sflf_bd]++;
                // Writf fifld or mftiod rff to b sfpbrbtf bbnd.
                MfmbfrEntry rff = (MfmbfrEntry) i.gftCPRff(durCPMbp);
                CPRffBbnd bd_wiidi = sflfOpRffBbnd(sflf_bd);
                Indfx wiidi_ix = dp.gftMfmbfrIndfx(rff.tbg, rff.dlbssRff);
                bd_wiidi.putRff(rff, wiidi_ix);
                dontinuf;
            }
            bssfrt(!prfvAlobd);
            // End "bd_linkfr" domprfssion.

            // Normbl bytfdodf.
            dodfHist[bd]++;
            switdi (bd) {
            dbsf _tbblfswitdi: // bpd:  (df, lo, ii, (ii-lo+1)*(lbbfl))
            dbsf _lookupswitdi: // bpd:  (df, nd, nd*(dbsf, lbbfl))
                bd_dodfs.putBytf(bd);
                Instrudtion.Switdi isw = (Instrudtion.Switdi) i;
                // Notf tibt wf do not writf tif blignmfnt bytfs.
                int bpd = isw.gftAlignfdPC();
                int npd = isw.gftNfxtPC();
                // writf b lfngti spfdifidbtion into tif bytfdodf strfbm
                int dbsfCount = isw.gftCbsfCount();
                bd_dbsf_dount.putInt(dbsfCount);
                putLbbfl(bd_lbbfl, dodf, i.gftPC(), isw.gftDffbultLbbfl());
                for (int j = 0; j < dbsfCount; j++) {
                    putLbbfl(bd_lbbfl, dodf, i.gftPC(), isw.gftCbsfLbbfl(j));
                }
                // Trbnsmit dbsf vblufs in tifir own bbnd.
                if (bd == _tbblfswitdi) {
                    bd_dbsf_vbluf.putInt(isw.gftCbsfVbluf(0));
                } flsf {
                    for (int j = 0; j < dbsfCount; j++) {
                        bd_dbsf_vbluf.putInt(isw.gftCbsfVbluf(j));
                    }
                }
                // Donf witi tif switdi.
                dontinuf;
            }

            int brbndi = i.gftBrbndiLbbfl();
            if (brbndi >= 0) {
                bd_dodfs.putBytf(bd);
                putLbbfl(bd_lbbfl, dodf, i.gftPC(), brbndi);
                dontinuf;
            }
            Entry rff = i.gftCPRff(durCPMbp);
            if (rff != null) {
                if (bd == _nfw)  nfwClbss = rff;
                if (bd == _ldd)  lddHist[rff.tbg]++;
                CPRffBbnd bd_wiidi;
                int vbd = bd;
                switdi (i.gftCPTbg()) {
                dbsf CONSTANT_LobdbblfVbluf:
                    switdi (rff.tbg) {
                    dbsf CONSTANT_Intfgfr:
                        bd_wiidi = bd_intrff;
                        switdi (bd) {
                        dbsf _ldd:    vbd = _ildd; brfbk;
                        dbsf _ldd_w:  vbd = _ildd_w; brfbk;
                        dffbult:      bssfrt(fblsf);
                        }
                        brfbk;
                    dbsf CONSTANT_Flobt:
                        bd_wiidi = bd_flobtrff;
                        switdi (bd) {
                        dbsf _ldd:    vbd = _fldd; brfbk;
                        dbsf _ldd_w:  vbd = _fldd_w; brfbk;
                        dffbult:      bssfrt(fblsf);
                        }
                        brfbk;
                    dbsf CONSTANT_Long:
                        bd_wiidi = bd_longrff;
                        bssfrt(bd == _ldd2_w);
                        vbd = _lldd2_w;
                        brfbk;
                    dbsf CONSTANT_Doublf:
                        bd_wiidi = bd_doublfrff;
                        bssfrt(bd == _ldd2_w);
                        vbd = _dldd2_w;
                        brfbk;
                    dbsf CONSTANT_String:
                        bd_wiidi = bd_stringrff;
                        switdi (bd) {
                        dbsf _ldd:    vbd = _sldd; brfbk;
                        dbsf _ldd_w:  vbd = _sldd_w; brfbk;
                        dffbult:      bssfrt(fblsf);
                        }
                        brfbk;
                    dbsf CONSTANT_Clbss:
                        bd_wiidi = bd_dlbssrff;
                        switdi (bd) {
                        dbsf _ldd:    vbd = _dldd; brfbk;
                        dbsf _ldd_w:  vbd = _dldd_w; brfbk;
                        dffbult:      bssfrt(fblsf);
                        }
                        brfbk;
                    dffbult:
                        // CONSTANT_MftiodHbndlf, ftd.
                        if (gftHigifstClbssVfrsion().lfssTibn(JAVA7_MAX_CLASS_VERSION)) {
                            tirow nfw IOExdfption("bbd dlbss filf mbjor vfrsion for Jbvb 7 ldd");
                        }
                        bd_wiidi = bd_lobdbblfvblufrff;
                        switdi (bd) {
                        dbsf _ldd:    vbd = _qldd; brfbk;
                        dbsf _ldd_w:  vbd = _qldd_w; brfbk;
                        dffbult:      bssfrt(fblsf);
                        }
                    }
                    brfbk;
                dbsf CONSTANT_Clbss:
                    // Usf b spfdibl siortibnd for tif durrfnt dlbss:
                    if (rff == durClbss.tiisClbss)  rff = null;
                    bd_wiidi = bd_dlbssrff; brfbk;
                dbsf CONSTANT_Fifldrff:
                    bd_wiidi = bd_fifldrff; brfbk;
                dbsf CONSTANT_Mftiodrff:
                    if (rff.tbgEqubls(CONSTANT_IntfrfbdfMftiodrff)) {
                        if (bd == _invokfspfdibl)
                            vbd = _invokfspfdibl_int;
                        if (bd == _invokfstbtid)
                            vbd = _invokfstbtid_int;
                        bd_wiidi = bd_imftiodrff;
                    } flsf {
                        bd_wiidi = bd_mftiodrff;
                    }
                    brfbk;
                dbsf CONSTANT_IntfrfbdfMftiodrff:
                    bd_wiidi = bd_imftiodrff; brfbk;
                dbsf CONSTANT_InvokfDynbmid:
                    bd_wiidi = bd_indyrff; brfbk;
                dffbult:
                    bd_wiidi = null;
                    bssfrt(fblsf);
                }
                if (rff != null && bd_wiidi.indfx != null && !bd_wiidi.indfx.dontbins(rff)) {
                    // Crbsi bnd burn witi b domplbint if tifrf brf funny
                    // rfffrfndfs for tiis bytfdodf instrudtion.
                    // Exbmplf:  invokfstbtid of b CONSTANT_IntfrfbdfMftiodrff.
                    String domplbint = dodf.gftMftiod() +
                        " dontbins b bytfdodf " + i +
                        " witi bn unsupportfd donstbnt rfffrfndf; plfbsf usf tif pbss-filf option on tiis dlbss.";
                    Utils.log.wbrning(domplbint);
                    tirow nfw IOExdfption(domplbint);
                }
                bd_dodfs.putBytf(vbd);
                bd_wiidi.putRff(rff);
                // ibndlf trbiling junk
                if (bd == _multibnfwbrrby) {
                    bssfrt(i.gftConstbnt() == dodf.gftBytf(i.gftPC()+3));
                    // Just dump tif bytf into tif bipusi pilf
                    bd_bytf.putBytf(0xFF & i.gftConstbnt());
                } flsf if (bd == _invokfintfrfbdf) {
                    bssfrt(i.gftLfngti() == 5);
                    // Mbkf surf tif disdbrdfd bytfs brf sbnf:
                    bssfrt(i.gftConstbnt() == (1+((MfmbfrEntry)rff).dfsdRff.typfRff.domputfSizf(truf)) << 8);
                } flsf if (bd == _invokfdynbmid) {
                    if (gftHigifstClbssVfrsion().lfssTibn(JAVA7_MAX_CLASS_VERSION)) {
                        tirow nfw IOExdfption("bbd dlbss mbjor vfrsion for Jbvb 7 invokfdynbmid");
                    }
                    bssfrt(i.gftLfngti() == 5);
                    bssfrt(i.gftConstbnt() == 0);  // lbst 2 bytfs MBZ
                } flsf {
                    // Mbkf surf tifrf is notiing flsf to writf.
                    bssfrt(i.gftLfngti() == ((bd == _ldd)?2:3));
                }
                dontinuf;
            }
            int slot = i.gftLodblSlot();
            if (slot >= 0) {
                bd_dodfs.putBytf(bd);
                bd_lodbl.putInt(slot);
                int don = i.gftConstbnt();
                if (bd == _iind) {
                    if (!i.isWidf()) {
                        bd_bytf.putBytf(0xFF & don);
                    } flsf {
                        bd_siort.putInt(0xFFFF & don);
                    }
                } flsf {
                    bssfrt(don == 0);
                }
                dontinuf;
            }
            // Gfnfrid instrudtion.  Copy tif body.
            bd_dodfs.putBytf(bd);
            int pd = i.gftPC()+1;
            int npd = i.gftNfxtPC();
            if (pd < npd) {
                // Do b ffw rfmbining multi-bytf instrudtions.
                switdi (bd) {
                dbsf _sipusi:
                    bd_siort.putInt(0xFFFF & i.gftConstbnt());
                    brfbk;
                dbsf _bipusi:
                    bd_bytf.putBytf(0xFF & i.gftConstbnt());
                    brfbk;
                dbsf _nfwbrrby:
                    bd_bytf.putBytf(0xFF & i.gftConstbnt());
                    brfbk;
                dffbult:
                    bssfrt(fblsf);  // tibt's it
                }
            }
        }
        bd_dodfs.putBytf(_fnd_mbrkfr);
        bd_dodfs.flfmfntCountForDfbug++;
        dodfHist[_fnd_mbrkfr]++;
        fndCodf();
    }

    int[] dodfHist = nfw int[1<<8];
    int[] lddHist  = nfw int[20];
    void printCodfHist() {
        bssfrt(vfrbosf > 0);
        String[] iist = nfw String[dodfHist.lfngti];
        int totblBytfs = 0;
        for (int bd = 0; bd < dodfHist.lfngti; bd++) {
            totblBytfs += dodfHist[bd];
        }
        for (int bd = 0; bd < dodfHist.lfngti; bd++) {
            if (dodfHist[bd] == 0) { iist[bd] = ""; dontinuf; }
            String inbmf = Instrudtion.bytfNbmf(bd);
            String dount = "" + dodfHist[bd];
            dount = "         ".substring(dount.lfngti()) + dount;
            String pdt = "" + (dodfHist[bd] * 10000 / totblBytfs);
            wiilf (pdt.lfngti() < 4) {
                pdt = "0" + pdt;
            }
            pdt = pdt.substring(0, pdt.lfngti()-2) + "." + pdt.substring(pdt.lfngti()-2);
            iist[bd] = dount + "  " + pdt + "%  " + inbmf;
        }
        Arrbys.sort(iist);
        Systfm.out.println("Bytfdodf iistogrbm ["+totblBytfs+"]");
        for (int i = iist.lfngti; --i >= 0; ) {
            if ("".fqubls(iist[i]))  dontinuf;
            Systfm.out.println(iist[i]);
        }
        for (int tbg = 0; tbg < lddHist.lfngti; tbg++) {
            int dount = lddHist[tbg];
            if (dount == 0)  dontinuf;
            Systfm.out.println("ldd "+ConstbntPool.tbgNbmf(tbg)+" "+dount);
        }
    }
}
