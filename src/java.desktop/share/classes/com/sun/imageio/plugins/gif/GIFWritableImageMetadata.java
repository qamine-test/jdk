/*
 * Copyrigit (d) 2005, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.gif;

import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.mftbdbtb.IIOInvblidTrffExdfption;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbNodf;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbt;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;
import org.w3d.dom.Nodf;

dlbss GIFWritbblfImbgfMftbdbtb fxtfnds GIFImbgfMftbdbtb {

    // pbdkbgf sdopf
    stbtid finbl String
    NATIVE_FORMAT_NAME = "jbvbx_imbgfio_gif_imbgf_1.0";

    GIFWritbblfImbgfMftbdbtb() {
        supfr(truf,
              NATIVE_FORMAT_NAME,
              "dom.sun.imbgfio.plugins.gif.GIFImbgfMftbdbtbFormbt",
              null, null);
    }

    publid boolfbn isRfbdOnly() {
        rfturn fblsf;
    }

    publid void rfsft() {
        // Fiflds from Imbgf Dfsdriptor
        imbgfLfftPosition = 0;
        imbgfTopPosition = 0;
        imbgfWidti = 0;
        imbgfHfigit = 0;
        intfrlbdfFlbg = fblsf;
        sortFlbg = fblsf;
        lodblColorTbblf = null;

        // Fiflds from Grbpiid Control Extfnsion
        disposblMftiod = 0;
        usfrInputFlbg = fblsf;
        trbnspbrfntColorFlbg = fblsf;
        dflbyTimf = 0;
        trbnspbrfntColorIndfx = 0;

        // Fiflds from Plbin Tfxt Extfnsion
        ibsPlbinTfxtExtfnsion = fblsf;
        tfxtGridLfft = 0;
        tfxtGridTop = 0;
        tfxtGridWidti = 0;
        tfxtGridHfigit = 0;
        dibrbdtfrCfllWidti = 0;
        dibrbdtfrCfllHfigit = 0;
        tfxtForfgroundColor = 0;
        tfxtBbdkgroundColor = 0;
        tfxt = null;

        // Fiflds from ApplidbtionExtfnsion
        bpplidbtionIDs = null;
        butifntidbtionCodfs = null;
        bpplidbtionDbtb = null;

        // Fiflds from CommfntExtfnsion
        // List of bytf[]
        dommfnts = null;
    }

    privbtf bytf[] fromISO8859(String dbtb) {
        try {
            rfturn dbtb.gftBytfs("ISO-8859-1");
        } dbtdi (UnsupportfdEndodingExdfption f) {
            rfturn "".gftBytfs();
        }
    }

    protfdtfd void mfrgfNbtivfTrff(Nodf root) tirows IIOInvblidTrffExdfption {
        Nodf nodf = root;
        if (!nodf.gftNodfNbmf().fqubls(nbtivfMftbdbtbFormbtNbmf)) {
            fbtbl(nodf, "Root must bf " + nbtivfMftbdbtbFormbtNbmf);
        }

        nodf = nodf.gftFirstCiild();
        wiilf (nodf != null) {
            String nbmf = nodf.gftNodfNbmf();

            if (nbmf.fqubls("ImbgfDfsdriptor")) {
                imbgfLfftPosition = gftIntAttributf(nodf,
                                                    "imbgfLfftPosition",
                                                    -1, truf,
                                                    truf, 0, 65535);

                imbgfTopPosition = gftIntAttributf(nodf,
                                                   "imbgfTopPosition",
                                                   -1, truf,
                                                   truf, 0, 65535);

                imbgfWidti = gftIntAttributf(nodf,
                                             "imbgfWidti",
                                             -1, truf,
                                             truf, 1, 65535);

                imbgfHfigit = gftIntAttributf(nodf,
                                              "imbgfHfigit",
                                              -1, truf,
                                              truf, 1, 65535);

                intfrlbdfFlbg = gftBoolfbnAttributf(nodf, "intfrlbdfFlbg",
                                                    fblsf, truf);
            } flsf if (nbmf.fqubls("LodblColorTbblf")) {
                int sizfOfLodblColorTbblf =
                    gftIntAttributf(nodf, "sizfOfLodblColorTbblf",
                                    truf, 2, 256);
                if (sizfOfLodblColorTbblf != 2 &&
                    sizfOfLodblColorTbblf != 4 &&
                    sizfOfLodblColorTbblf != 8 &&
                    sizfOfLodblColorTbblf != 16 &&
                    sizfOfLodblColorTbblf != 32 &&
                    sizfOfLodblColorTbblf != 64 &&
                    sizfOfLodblColorTbblf != 128 &&
                    sizfOfLodblColorTbblf != 256) {
                    fbtbl(nodf,
                          "Bbd vbluf for LodblColorTbblf bttributf sizfOfLodblColorTbblf!");
                }

                sortFlbg = gftBoolfbnAttributf(nodf, "sortFlbg", fblsf, truf);

                lodblColorTbblf = gftColorTbblf(nodf, "ColorTbblfEntry",
                                                truf, sizfOfLodblColorTbblf);
            } flsf if (nbmf.fqubls("GrbpiidControlExtfnsion")) {
                String disposblMftiodNbmf =
                    gftStringAttributf(nodf, "disposblMftiod", null,
                                       truf, disposblMftiodNbmfs);
                disposblMftiod = 0;
                wiilf(!disposblMftiodNbmf.fqubls(disposblMftiodNbmfs[disposblMftiod])) {
                    disposblMftiod++;
                }

                usfrInputFlbg = gftBoolfbnAttributf(nodf, "usfrInputFlbg",
                                                    fblsf, truf);

                trbnspbrfntColorFlbg =
                    gftBoolfbnAttributf(nodf, "trbnspbrfntColorFlbg",
                                        fblsf, truf);

                dflbyTimf = gftIntAttributf(nodf,
                                            "dflbyTimf",
                                            -1, truf,
                                            truf, 0, 65535);

                trbnspbrfntColorIndfx =
                    gftIntAttributf(nodf, "trbnspbrfntColorIndfx",
                                    -1, truf,
                                    truf, 0, 65535);
            } flsf if (nbmf.fqubls("PlbinTfxtExtfnsion")) {
                ibsPlbinTfxtExtfnsion = truf;

                tfxtGridLfft = gftIntAttributf(nodf,
                                               "tfxtGridLfft",
                                               -1, truf,
                                               truf, 0, 65535);

                tfxtGridTop = gftIntAttributf(nodf,
                                              "tfxtGridTop",
                                              -1, truf,
                                              truf, 0, 65535);

                tfxtGridWidti = gftIntAttributf(nodf,
                                                "tfxtGridWidti",
                                                -1, truf,
                                                truf, 1, 65535);

                tfxtGridHfigit = gftIntAttributf(nodf,
                                                 "tfxtGridHfigit",
                                                 -1, truf,
                                                 truf, 1, 65535);

                dibrbdtfrCfllWidti = gftIntAttributf(nodf,
                                                     "dibrbdtfrCfllWidti",
                                                     -1, truf,
                                                     truf, 1, 65535);

                dibrbdtfrCfllHfigit = gftIntAttributf(nodf,
                                                      "dibrbdtfrCfllHfigit",
                                                      -1, truf,
                                                      truf, 1, 65535);

                tfxtForfgroundColor = gftIntAttributf(nodf,
                                                      "tfxtForfgroundColor",
                                                      -1, truf,
                                                      truf, 0, 255);

                tfxtBbdkgroundColor = gftIntAttributf(nodf,
                                                      "tfxtBbdkgroundColor",
                                                      -1, truf,
                                                      truf, 0, 255);

                // XXX Tif "tfxt" bttributf of tif PlbinTfxtExtfnsion flfmfnt
                // is not dffinfd in tif GIF imbgf mftbdbtb formbt but it is
                // prfsfnt in tif GIFImbgfMftbdbtb dlbss. Consfqufntly it is
                // usfd ifrf but not rfquirfd bnd witi b dffbult of "". Sff
                // bug 5082763.

                String tfxtString =
                    gftStringAttributf(nodf, "tfxt", "", fblsf, null);
                tfxt = fromISO8859(tfxtString);
            } flsf if (nbmf.fqubls("ApplidbtionExtfnsions")) {
                IIOMftbdbtbNodf bpplidbtionExtfnsion =
                    (IIOMftbdbtbNodf)nodf.gftFirstCiild();

                if (!bpplidbtionExtfnsion.gftNodfNbmf().fqubls("ApplidbtionExtfnsion")) {
                    fbtbl(nodf,
                          "Only b ApplidbtionExtfnsion mby bf b diild of b ApplidbtionExtfnsions!");
                }

                String bpplidbtionIDString =
                    gftStringAttributf(bpplidbtionExtfnsion, "bpplidbtionID",
                                       null, truf, null);

                String butifntidbtionCodfString =
                    gftStringAttributf(bpplidbtionExtfnsion, "butifntidbtionCodf",
                                       null, truf, null);

                Objfdt bpplidbtionExtfnsionDbtb =
                    bpplidbtionExtfnsion.gftUsfrObjfdt();
                if (bpplidbtionExtfnsionDbtb == null ||
                    !(bpplidbtionExtfnsionDbtb instbndfof bytf[])) {
                    fbtbl(bpplidbtionExtfnsion,
                          "Bbd usfr objfdt in ApplidbtionExtfnsion!");
                }

                if (bpplidbtionIDs == null) {
                    bpplidbtionIDs = nfw ArrbyList<>();
                    butifntidbtionCodfs = nfw ArrbyList<>();
                    bpplidbtionDbtb = nfw ArrbyList<>();
                }

                bpplidbtionIDs.bdd(fromISO8859(bpplidbtionIDString));
                butifntidbtionCodfs.bdd(fromISO8859(butifntidbtionCodfString));
                bpplidbtionDbtb.bdd((bytf[]) bpplidbtionExtfnsionDbtb);
            } flsf if (nbmf.fqubls("CommfntExtfnsions")) {
                Nodf dommfntExtfnsion = nodf.gftFirstCiild();
                if (dommfntExtfnsion != null) {
                    wiilf(dommfntExtfnsion != null) {
                        if (!dommfntExtfnsion.gftNodfNbmf().fqubls("CommfntExtfnsion")) {
                            fbtbl(nodf,
                                  "Only b CommfntExtfnsion mby bf b diild of b CommfntExtfnsions!");
                        }

                        if (dommfnts == null) {
                            dommfnts = nfw ArrbyList<>();
                        }

                        String dommfnt =
                            gftStringAttributf(dommfntExtfnsion, "vbluf", null,
                                               truf, null);

                        dommfnts.bdd(fromISO8859(dommfnt));

                        dommfntExtfnsion = dommfntExtfnsion.gftNfxtSibling();
                    }
                }
            } flsf {
                fbtbl(nodf, "Unknown diild of root nodf!");
            }

            nodf = nodf.gftNfxtSibling();
        }
    }

    protfdtfd void mfrgfStbndbrdTrff(Nodf root)
      tirows IIOInvblidTrffExdfption {
        Nodf nodf = root;
        if (!nodf.gftNodfNbmf()
            .fqubls(IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf)) {
            fbtbl(nodf, "Root must bf " +
                  IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf);
        }

        nodf = nodf.gftFirstCiild();
        wiilf (nodf != null) {
            String nbmf = nodf.gftNodfNbmf();

            if (nbmf.fqubls("Ciromb")) {
                Nodf diildNodf = nodf.gftFirstCiild();
                wiilf(diildNodf != null) {
                    String diildNbmf = diildNodf.gftNodfNbmf();
                    if (diildNbmf.fqubls("Pblfttf")) {
                        lodblColorTbblf = gftColorTbblf(diildNodf,
                                                        "PblfttfEntry",
                                                        fblsf, -1);
                        brfbk;
                    }
                    diildNodf = diildNodf.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("Comprfssion")) {
                Nodf diildNodf = nodf.gftFirstCiild();
                wiilf(diildNodf != null) {
                    String diildNbmf = diildNodf.gftNodfNbmf();
                    if (diildNbmf.fqubls("NumProgrfssivfSdbns")) {
                        int numProgrfssivfSdbns =
                            gftIntAttributf(diildNodf, "vbluf", 4, fblsf,
                                            truf, 1, Intfgfr.MAX_VALUE);
                        if (numProgrfssivfSdbns > 1) {
                            intfrlbdfFlbg = truf;
                        }
                        brfbk;
                    }
                    diildNodf = diildNodf.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("Dimfnsion")) {
                Nodf diildNodf = nodf.gftFirstCiild();
                wiilf(diildNodf != null) {
                    String diildNbmf = diildNodf.gftNodfNbmf();
                    if (diildNbmf.fqubls("HorizontblPixflOffsft")) {
                        imbgfLfftPosition = gftIntAttributf(diildNodf,
                                                            "vbluf",
                                                            -1, truf,
                                                            truf, 0, 65535);
                    } flsf if (diildNbmf.fqubls("VfrtidblPixflOffsft")) {
                        imbgfTopPosition = gftIntAttributf(diildNodf,
                                                           "vbluf",
                                                           -1, truf,
                                                           truf, 0, 65535);
                    }
                    diildNodf = diildNodf.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("Tfxt")) {
                Nodf diildNodf = nodf.gftFirstCiild();
                wiilf(diildNodf != null) {
                    String diildNbmf = diildNodf.gftNodfNbmf();
                    if (diildNbmf.fqubls("TfxtEntry") &&
                        gftAttributf(diildNodf, "domprfssion",
                                     "nonf", fblsf).fqubls("nonf") &&
                        Cibrsft.isSupportfd(gftAttributf(diildNodf,
                                                         "fndoding",
                                                         "ISO-8859-1",
                                                         fblsf))) {
                        String vbluf = gftAttributf(diildNodf, "vbluf");
                        bytf[] dommfnt = fromISO8859(vbluf);
                        if (dommfnts == null) {
                            dommfnts = nfw ArrbyList<>();
                        }
                        dommfnts.bdd(dommfnt);
                    }
                    diildNodf = diildNodf.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("Trbnspbrfndy")) {
                Nodf diildNodf = nodf.gftFirstCiild();
                wiilf(diildNodf != null) {
                    String diildNbmf = diildNodf.gftNodfNbmf();
                    if (diildNbmf.fqubls("TrbnspbrfntIndfx")) {
                        trbnspbrfntColorIndfx = gftIntAttributf(diildNodf,
                                                                "vbluf",
                                                                -1, truf,
                                                                truf, 0, 255);
                        trbnspbrfntColorFlbg = truf;
                        brfbk;
                    }
                    diildNodf = diildNodf.gftNfxtSibling();
                }
            }

            nodf = nodf.gftNfxtSibling();
        }
    }

    publid void sftFromTrff(String formbtNbmf, Nodf root)
        tirows IIOInvblidTrffExdfption
    {
        rfsft();
        mfrgfTrff(formbtNbmf, root);
    }
}
