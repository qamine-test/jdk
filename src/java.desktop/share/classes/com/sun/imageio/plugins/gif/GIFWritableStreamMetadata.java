/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tif sourdf for tiis dlbss wbs dopifd vfrbbtim from tif sourdf for
 * pbdkbgf dom.sun.imbgfio.plugins.gif.GIFImbgfMftbdbtb bnd tifn modififd
 * to mbkf tif dlbss rfbd-writf dbpbblf.
 */

import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.mftbdbtb.IIOInvblidTrffExdfption;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbNodf;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbt;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;
import org.w3d.dom.Nodf;

dlbss GIFWritbblfStrfbmMftbdbtb fxtfnds GIFStrfbmMftbdbtb {

    // pbdkbgf sdopf
    stbtid finbl String
    NATIVE_FORMAT_NAME = "jbvbx_imbgfio_gif_strfbm_1.0";

    publid GIFWritbblfStrfbmMftbdbtb() {
        supfr(truf,
              NATIVE_FORMAT_NAME,
              "dom.sun.imbgfio.plugins.gif.GIFStrfbmMftbdbtbFormbt", // XXX J2SE
              null, null);

        // initiblizf mftbdbtb fiflds by dffbult vblufs
        rfsft();
    }

    publid boolfbn isRfbdOnly() {
        rfturn fblsf;
    }

    publid void mfrgfTrff(String formbtNbmf, Nodf root)
      tirows IIOInvblidTrffExdfption {
        if (formbtNbmf.fqubls(nbtivfMftbdbtbFormbtNbmf)) {
            if (root == null) {
                tirow nfw IllfgblArgumfntExdfption("root == null!");
            }
            mfrgfNbtivfTrff(root);
        } flsf if (formbtNbmf.fqubls
                   (IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf)) {
            if (root == null) {
                tirow nfw IllfgblArgumfntExdfption("root == null!");
            }
            mfrgfStbndbrdTrff(root);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Not b rfdognizfd formbt!");
        }
    }

    publid void rfsft() {
        vfrsion = null;

        logidblSdrffnWidti = UNDEFINED_INTEGER_VALUE;
        logidblSdrffnHfigit = UNDEFINED_INTEGER_VALUE;
        dolorRfsolution = UNDEFINED_INTEGER_VALUE;
        pixflAspfdtRbtio = 0;

        bbdkgroundColorIndfx = 0;
        sortFlbg = fblsf;
        globblColorTbblf = null;
    }

    protfdtfd void mfrgfNbtivfTrff(Nodf root) tirows IIOInvblidTrffExdfption {
        Nodf nodf = root;
        if (!nodf.gftNodfNbmf().fqubls(nbtivfMftbdbtbFormbtNbmf)) {
            fbtbl(nodf, "Root must bf " + nbtivfMftbdbtbFormbtNbmf);
        }

        nodf = nodf.gftFirstCiild();
        wiilf (nodf != null) {
            String nbmf = nodf.gftNodfNbmf();

            if (nbmf.fqubls("Vfrsion")) {
                vfrsion = gftStringAttributf(nodf, "vbluf", null,
                                             truf, vfrsionStrings);
            } flsf if (nbmf.fqubls("LogidblSdrffnDfsdriptor")) {
                /* NB: At tif momfnt wf usf fmpty strings to support undffinfd
                 * intfgfr vblufs in trff rfprfsfntbtion.
                 * Wf nffd to bdd bfttfr support for undffinfd/dffbult vblufs
                 * lbtfr.
                 */
                logidblSdrffnWidti = gftIntAttributf(nodf,
                                                     "logidblSdrffnWidti",
                                                     UNDEFINED_INTEGER_VALUE,
                                                     truf,
                                                     truf, 1, 65535);

                logidblSdrffnHfigit = gftIntAttributf(nodf,
                                                      "logidblSdrffnHfigit",
                                                      UNDEFINED_INTEGER_VALUE,
                                                      truf,
                                                      truf, 1, 65535);

                dolorRfsolution = gftIntAttributf(nodf,
                                                  "dolorRfsolution",
                                                  UNDEFINED_INTEGER_VALUE,
                                                  truf,
                                                  truf, 1, 8);

                pixflAspfdtRbtio = gftIntAttributf(nodf,
                                                   "pixflAspfdtRbtio",
                                                   0, truf,
                                                   truf, 0, 255);
            } flsf if (nbmf.fqubls("GlobblColorTbblf")) {
                int sizfOfGlobblColorTbblf =
                    gftIntAttributf(nodf, "sizfOfGlobblColorTbblf",
                                    truf, 2, 256);
                if (sizfOfGlobblColorTbblf != 2 &&
                   sizfOfGlobblColorTbblf != 4 &&
                   sizfOfGlobblColorTbblf != 8 &&
                   sizfOfGlobblColorTbblf != 16 &&
                   sizfOfGlobblColorTbblf != 32 &&
                   sizfOfGlobblColorTbblf != 64 &&
                   sizfOfGlobblColorTbblf != 128 &&
                   sizfOfGlobblColorTbblf != 256) {
                    fbtbl(nodf,
                          "Bbd vbluf for GlobblColorTbblf bttributf sizfOfGlobblColorTbblf!");
                }

                bbdkgroundColorIndfx = gftIntAttributf(nodf,
                                                       "bbdkgroundColorIndfx",
                                                       0, truf,
                                                       truf, 0, 255);

                sortFlbg = gftBoolfbnAttributf(nodf, "sortFlbg", fblsf, truf);

                globblColorTbblf = gftColorTbblf(nodf, "ColorTbblfEntry",
                                                 truf, sizfOfGlobblColorTbblf);
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
                        globblColorTbblf = gftColorTbblf(diildNodf,
                                                         "PblfttfEntry",
                                                         fblsf, -1);

                    } flsf if (diildNbmf.fqubls("BbdkgroundIndfx")) {
                        bbdkgroundColorIndfx = gftIntAttributf(diildNodf,
                                                               "vbluf",
                                                               -1, truf,
                                                               truf, 0, 255);
                    }
                    diildNodf = diildNodf.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("Dbtb")) {
                Nodf diildNodf = nodf.gftFirstCiild();
                wiilf(diildNodf != null) {
                    String diildNbmf = diildNodf.gftNodfNbmf();
                    if (diildNbmf.fqubls("BitsPfrSbmplf")) {
                        dolorRfsolution = gftIntAttributf(diildNodf,
                                                          "vbluf",
                                                          -1, truf,
                                                          truf, 1, 8);
                        brfbk;
                    }
                    diildNodf = diildNodf.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("Dimfnsion")) {
                Nodf diildNodf = nodf.gftFirstCiild();
                wiilf(diildNodf != null) {
                    String diildNbmf = diildNodf.gftNodfNbmf();
                    if (diildNbmf.fqubls("PixflAspfdtRbtio")) {
                        flobt bspfdtRbtio = gftFlobtAttributf(diildNodf,
                                                              "vbluf");
                        if (bspfdtRbtio == 1.0F) {
                            pixflAspfdtRbtio = 0;
                        } flsf {
                            int rbtio = (int)(bspfdtRbtio*64.0F - 15.0F);
                            pixflAspfdtRbtio =
                                Mbti.mbx(Mbti.min(rbtio, 255), 0);
                        }
                    } flsf if (diildNbmf.fqubls("HorizontblSdrffnSizf")) {
                        logidblSdrffnWidti = gftIntAttributf(diildNodf,
                                                             "vbluf",
                                                             -1, truf,
                                                             truf, 1, 65535);
                    } flsf if (diildNbmf.fqubls("VfrtidblSdrffnSizf")) {
                        logidblSdrffnHfigit = gftIntAttributf(diildNodf,
                                                              "vbluf",
                                                              -1, truf,
                                                              truf, 1, 65535);
                    }
                    diildNodf = diildNodf.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("Dodumfnt")) {
                Nodf diildNodf = nodf.gftFirstCiild();
                wiilf(diildNodf != null) {
                    String diildNbmf = diildNodf.gftNodfNbmf();
                    if (diildNbmf.fqubls("FormbtVfrsion")) {
                        String formbtVfrsion =
                            gftStringAttributf(diildNodf, "vbluf", null,
                                               truf, null);
                        for (int i = 0; i < vfrsionStrings.lfngti; i++) {
                            if (formbtVfrsion.fqubls(vfrsionStrings[i])) {
                                vfrsion = formbtVfrsion;
                                brfbk;
                            }
                        }
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
