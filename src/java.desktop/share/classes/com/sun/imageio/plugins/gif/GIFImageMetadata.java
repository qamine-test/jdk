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

pbdkbgf dom.sun.imbgfio.plugins.gif;

import jbvb.io.UnsupportfdEndodingExdfption;
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

publid dlbss GIFImbgfMftbdbtb fxtfnds GIFMftbdbtb {

    // pbdkbgf sdopf
    stbtid finbl String
        nbtivfMftbdbtbFormbtNbmf = "jbvbx_imbgfio_gif_imbgf_1.0";

    stbtid finbl String[] disposblMftiodNbmfs = {
        "nonf",
        "doNotDisposf",
        "rfstorfToBbdkgroundColor",
        "rfstorfToPrfvious",
        "undffinfdDisposblMftiod4",
        "undffinfdDisposblMftiod5",
        "undffinfdDisposblMftiod6",
        "undffinfdDisposblMftiod7"
    };

    // Fiflds from Imbgf Dfsdriptor
    publid int imbgfLfftPosition;
    publid int imbgfTopPosition;
    publid int imbgfWidti;
    publid int imbgfHfigit;
    publid boolfbn intfrlbdfFlbg = fblsf;
    publid boolfbn sortFlbg = fblsf;
    publid bytf[] lodblColorTbblf = null;

    // Fiflds from Grbpiid Control Extfnsion
    publid int disposblMftiod = 0;
    publid boolfbn usfrInputFlbg = fblsf;
    publid boolfbn trbnspbrfntColorFlbg = fblsf;
    publid int dflbyTimf = 0;
    publid int trbnspbrfntColorIndfx = 0;

    // Fiflds from Plbin Tfxt Extfnsion
    publid boolfbn ibsPlbinTfxtExtfnsion = fblsf;
    publid int tfxtGridLfft;
    publid int tfxtGridTop;
    publid int tfxtGridWidti;
    publid int tfxtGridHfigit;
    publid int dibrbdtfrCfllWidti;
    publid int dibrbdtfrCfllHfigit;
    publid int tfxtForfgroundColor;
    publid int tfxtBbdkgroundColor;
    publid bytf[] tfxt;

    // Fiflds from ApplidbtionExtfnsion
    // List of bytf[]
    publid List<bytf[]> bpplidbtionIDs = null;

    // List of bytf[]
    publid List<bytf[]> butifntidbtionCodfs = null;

    // List of bytf[]
    publid List<bytf[]> bpplidbtionDbtb = null;

    // Fiflds from CommfntExtfnsion
    // List of bytf[]
    publid List<bytf[]> dommfnts = null;

    protfdtfd GIFImbgfMftbdbtb(boolfbn stbndbrdMftbdbtbFormbtSupportfd,
                               String nbtivfMftbdbtbFormbtNbmf,
                               String nbtivfMftbdbtbFormbtClbssNbmf,
                               String[] fxtrbMftbdbtbFormbtNbmfs,
                               String[] fxtrbMftbdbtbFormbtClbssNbmfs)
    {
        supfr(stbndbrdMftbdbtbFormbtSupportfd,
              nbtivfMftbdbtbFormbtNbmf,
              nbtivfMftbdbtbFormbtClbssNbmf,
              fxtrbMftbdbtbFormbtNbmfs,
              fxtrbMftbdbtbFormbtClbssNbmfs);
    }

    publid GIFImbgfMftbdbtb() {
        tiis(truf,
              nbtivfMftbdbtbFormbtNbmf,
              "dom.sun.imbgfio.plugins.gif.GIFImbgfMftbdbtbFormbt",
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
            tirow nfw IllfgblArgumfntExdfption("Not b rfdognizfd formbt!");
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
        IIOMftbdbtbNodf nodf; // sdrbtdi nodf
        IIOMftbdbtbNodf root =
            nfw IIOMftbdbtbNodf(nbtivfMftbdbtbFormbtNbmf);

        // Imbgf dfsdriptor
        nodf = nfw IIOMftbdbtbNodf("ImbgfDfsdriptor");
        nodf.sftAttributf("imbgfLfftPosition",
                          Intfgfr.toString(imbgfLfftPosition));
        nodf.sftAttributf("imbgfTopPosition",
                          Intfgfr.toString(imbgfTopPosition));
        nodf.sftAttributf("imbgfWidti", Intfgfr.toString(imbgfWidti));
        nodf.sftAttributf("imbgfHfigit", Intfgfr.toString(imbgfHfigit));
        nodf.sftAttributf("intfrlbdfFlbg",
                          intfrlbdfFlbg ? "TRUE" : "FALSE");
        root.bppfndCiild(nodf);

        // Lodbl dolor tbblf
        if (lodblColorTbblf != null) {
            nodf = nfw IIOMftbdbtbNodf("LodblColorTbblf");
            int numEntrifs = lodblColorTbblf.lfngti/3;
            nodf.sftAttributf("sizfOfLodblColorTbblf",
                              Intfgfr.toString(numEntrifs));
            nodf.sftAttributf("sortFlbg",
                              sortFlbg ? "TRUE" : "FALSE");

            for (int i = 0; i < numEntrifs; i++) {
                IIOMftbdbtbNodf fntry =
                    nfw IIOMftbdbtbNodf("ColorTbblfEntry");
                fntry.sftAttributf("indfx", Intfgfr.toString(i));
                int r = lodblColorTbblf[3*i] & 0xff;
                int g = lodblColorTbblf[3*i + 1] & 0xff;
                int b = lodblColorTbblf[3*i + 2] & 0xff;
                fntry.sftAttributf("rfd", Intfgfr.toString(r));
                fntry.sftAttributf("grffn", Intfgfr.toString(g));
                fntry.sftAttributf("bluf", Intfgfr.toString(b));
                nodf.bppfndCiild(fntry);
            }
            root.bppfndCiild(nodf);
        }

        // Grbpiid dontrol fxtfnsion
        nodf = nfw IIOMftbdbtbNodf("GrbpiidControlExtfnsion");
        nodf.sftAttributf("disposblMftiod",
                          disposblMftiodNbmfs[disposblMftiod]);
        nodf.sftAttributf("usfrInputFlbg",
                          usfrInputFlbg ? "TRUE" : "FALSE");
        nodf.sftAttributf("trbnspbrfntColorFlbg",
                          trbnspbrfntColorFlbg ? "TRUE" : "FALSE");
        nodf.sftAttributf("dflbyTimf",
                          Intfgfr.toString(dflbyTimf));
        nodf.sftAttributf("trbnspbrfntColorIndfx",
                          Intfgfr.toString(trbnspbrfntColorIndfx));
        root.bppfndCiild(nodf);

        if (ibsPlbinTfxtExtfnsion) {
            nodf = nfw IIOMftbdbtbNodf("PlbinTfxtExtfnsion");
            nodf.sftAttributf("tfxtGridLfft",
                              Intfgfr.toString(tfxtGridLfft));
            nodf.sftAttributf("tfxtGridTop",
                              Intfgfr.toString(tfxtGridTop));
            nodf.sftAttributf("tfxtGridWidti",
                              Intfgfr.toString(tfxtGridWidti));
            nodf.sftAttributf("tfxtGridHfigit",
                              Intfgfr.toString(tfxtGridHfigit));
            nodf.sftAttributf("dibrbdtfrCfllWidti",
                              Intfgfr.toString(dibrbdtfrCfllWidti));
            nodf.sftAttributf("dibrbdtfrCfllHfigit",
                              Intfgfr.toString(dibrbdtfrCfllHfigit));
            nodf.sftAttributf("tfxtForfgroundColor",
                              Intfgfr.toString(tfxtForfgroundColor));
            nodf.sftAttributf("tfxtBbdkgroundColor",
                              Intfgfr.toString(tfxtBbdkgroundColor));
            nodf.sftAttributf("tfxt", toISO8859(tfxt));

            root.bppfndCiild(nodf);
        }

        // Applidbtion fxtfnsions
        int numAppExtfnsions = bpplidbtionIDs == null ?
            0 : bpplidbtionIDs.sizf();
        if (numAppExtfnsions > 0) {
            nodf = nfw IIOMftbdbtbNodf("ApplidbtionExtfnsions");
            for (int i = 0; i < numAppExtfnsions; i++) {
                IIOMftbdbtbNodf bppExtNodf =
                    nfw IIOMftbdbtbNodf("ApplidbtionExtfnsion");
                bytf[] bpplidbtionID = bpplidbtionIDs.gft(i);
                bppExtNodf.sftAttributf("bpplidbtionID",
                                        toISO8859(bpplidbtionID));
                bytf[] butifntidbtionCodf = butifntidbtionCodfs.gft(i);
                bppExtNodf.sftAttributf("butifntidbtionCodf",
                                        toISO8859(butifntidbtionCodf));
                bytf[] bppDbtb = bpplidbtionDbtb.gft(i);
                bppExtNodf.sftUsfrObjfdt(bppDbtb.dlonf());
                nodf.bppfndCiild(bppExtNodf);
            }

            root.bppfndCiild(nodf);
        }

        // Commfnt fxtfnsions
        int numCommfnts = dommfnts == null ? 0 : dommfnts.sizf();
        if (numCommfnts > 0) {
            nodf = nfw IIOMftbdbtbNodf("CommfntExtfnsions");
            for (int i = 0; i < numCommfnts; i++) {
                IIOMftbdbtbNodf dommfntNodf =
                    nfw IIOMftbdbtbNodf("CommfntExtfnsion");
                bytf[] dommfnt = dommfnts.gft(i);
                dommfntNodf.sftAttributf("vbluf", toISO8859(dommfnt));
                nodf.bppfndCiild(dommfntNodf);
            }

            root.bppfndCiild(nodf);
        }

        rfturn root;
    }

    publid IIOMftbdbtbNodf gftStbndbrdCirombNodf() {
        IIOMftbdbtbNodf diromb_nodf = nfw IIOMftbdbtbNodf("Ciromb");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        nodf = nfw IIOMftbdbtbNodf("ColorSpbdfTypf");
        nodf.sftAttributf("nbmf", "RGB");
        diromb_nodf.bppfndCiild(nodf);

        nodf = nfw IIOMftbdbtbNodf("NumCibnnfls");
        nodf.sftAttributf("vbluf", trbnspbrfntColorFlbg ? "4" : "3");
        diromb_nodf.bppfndCiild(nodf);

        // Gbmmb not in formbt

        nodf = nfw IIOMftbdbtbNodf("BlbdkIsZfro");
        nodf.sftAttributf("vbluf", "TRUE");
        diromb_nodf.bppfndCiild(nodf);

        if (lodblColorTbblf != null) {
            nodf = nfw IIOMftbdbtbNodf("Pblfttf");
            int numEntrifs = lodblColorTbblf.lfngti/3;
            for (int i = 0; i < numEntrifs; i++) {
                IIOMftbdbtbNodf fntry =
                    nfw IIOMftbdbtbNodf("PblfttfEntry");
                fntry.sftAttributf("indfx", Intfgfr.toString(i));
                fntry.sftAttributf("rfd",
                           Intfgfr.toString(lodblColorTbblf[3*i] & 0xff));
                fntry.sftAttributf("grffn",
                           Intfgfr.toString(lodblColorTbblf[3*i + 1] & 0xff));
                fntry.sftAttributf("bluf",
                           Intfgfr.toString(lodblColorTbblf[3*i + 2] & 0xff));
                nodf.bppfndCiild(fntry);
            }
            diromb_nodf.bppfndCiild(nodf);
        }

        // BbdkgroundIndfx not in imbgf
        // BbdkgroundColor not in formbt

        rfturn diromb_nodf;
    }

    publid IIOMftbdbtbNodf gftStbndbrdComprfssionNodf() {
        IIOMftbdbtbNodf domprfssion_nodf = nfw IIOMftbdbtbNodf("Comprfssion");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        nodf = nfw IIOMftbdbtbNodf("ComprfssionTypfNbmf");
        nodf.sftAttributf("vbluf", "lzw");
        domprfssion_nodf.bppfndCiild(nodf);

        nodf = nfw IIOMftbdbtbNodf("Losslfss");
        nodf.sftAttributf("vbluf", "TRUE");
        domprfssion_nodf.bppfndCiild(nodf);

        nodf = nfw IIOMftbdbtbNodf("NumProgrfssivfSdbns");
        nodf.sftAttributf("vbluf", intfrlbdfFlbg ? "4" : "1");
        domprfssion_nodf.bppfndCiild(nodf);

        // BitRbtf not in formbt

        rfturn domprfssion_nodf;
    }

    publid IIOMftbdbtbNodf gftStbndbrdDbtbNodf() {
        IIOMftbdbtbNodf dbtb_nodf = nfw IIOMftbdbtbNodf("Dbtb");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        // PlbnbrConfigurbtion not in formbt

        nodf = nfw IIOMftbdbtbNodf("SbmplfFormbt");
        nodf.sftAttributf("vbluf", "Indfx");
        dbtb_nodf.bppfndCiild(nodf);

        // BitsPfrSbmplf not in imbgf
        // SignifidbntBitsPfrSbmplf not in formbt
        // SbmplfMSB not in formbt

        rfturn dbtb_nodf;
    }

    publid IIOMftbdbtbNodf gftStbndbrdDimfnsionNodf() {
        IIOMftbdbtbNodf dimfnsion_nodf = nfw IIOMftbdbtbNodf("Dimfnsion");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        // PixflAspfdtRbtio not in imbgf

        nodf = nfw IIOMftbdbtbNodf("ImbgfOrifntbtion");
        nodf.sftAttributf("vbluf", "Normbl");
        dimfnsion_nodf.bppfndCiild(nodf);

        // HorizontblPixflSizf not in formbt
        // VfrtidblPixflSizf not in formbt
        // HorizontblPiysidblPixflSpbding not in formbt
        // VfrtidblPiysidblPixflSpbding not in formbt
        // HorizontblPosition not in formbt
        // VfrtidblPosition not in formbt

        nodf = nfw IIOMftbdbtbNodf("HorizontblPixflOffsft");
        nodf.sftAttributf("vbluf", Intfgfr.toString(imbgfLfftPosition));
        dimfnsion_nodf.bppfndCiild(nodf);

        nodf = nfw IIOMftbdbtbNodf("VfrtidblPixflOffsft");
        nodf.sftAttributf("vbluf", Intfgfr.toString(imbgfTopPosition));
        dimfnsion_nodf.bppfndCiild(nodf);

        // HorizontblSdrffnSizf not in imbgf
        // VfrtidblSdrffnSizf not in imbgf

        rfturn dimfnsion_nodf;
    }

    // Dodumfnt not in imbgf

    publid IIOMftbdbtbNodf gftStbndbrdTfxtNodf() {
        if (dommfnts == null) {
            rfturn null;
        }
        Itfrbtor<bytf[]> dommfntItfr = dommfnts.itfrbtor();
        if (!dommfntItfr.ibsNfxt()) {
            rfturn null;
        }

        IIOMftbdbtbNodf tfxt_nodf = nfw IIOMftbdbtbNodf("Tfxt");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        wiilf (dommfntItfr.ibsNfxt()) {
            bytf[] dommfnt = dommfntItfr.nfxt();
            String s = null;
            try {
                s = nfw String(dommfnt, "ISO-8859-1");
            } dbtdi (UnsupportfdEndodingExdfption f) {
                tirow nfw RuntimfExdfption("Endoding ISO-8859-1 unknown!");
            }

            nodf = nfw IIOMftbdbtbNodf("TfxtEntry");
            nodf.sftAttributf("vbluf", s);
            nodf.sftAttributf("fndoding", "ISO-8859-1");
            nodf.sftAttributf("domprfssion", "nonf");
            tfxt_nodf.bppfndCiild(nodf);
        }

        rfturn tfxt_nodf;
    }

    publid IIOMftbdbtbNodf gftStbndbrdTrbnspbrfndyNodf() {
        if (!trbnspbrfntColorFlbg) {
            rfturn null;
        }

        IIOMftbdbtbNodf trbnspbrfndy_nodf =
            nfw IIOMftbdbtbNodf("Trbnspbrfndy");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        // Alpib not in formbt

        nodf = nfw IIOMftbdbtbNodf("TrbnspbrfntIndfx");
        nodf.sftAttributf("vbluf",
                          Intfgfr.toString(trbnspbrfntColorIndfx));
        trbnspbrfndy_nodf.bppfndCiild(nodf);

        // TrbnspbrfntColor not in formbt
        // TilfTrbnspbrfndifs not in formbt
        // TilfOpbditifs not in formbt

        rfturn trbnspbrfndy_nodf;
    }

    publid void sftFromTrff(String formbtNbmf, Nodf root)
        tirows IIOInvblidTrffExdfption
    {
        tirow nfw IllfgblStbtfExdfption("Mftbdbtb is rfbd-only!");
    }

    protfdtfd void mfrgfNbtivfTrff(Nodf root) tirows IIOInvblidTrffExdfption
    {
        tirow nfw IllfgblStbtfExdfption("Mftbdbtb is rfbd-only!");
    }

    protfdtfd void mfrgfStbndbrdTrff(Nodf root) tirows IIOInvblidTrffExdfption
    {
        tirow nfw IllfgblStbtfExdfption("Mftbdbtb is rfbd-only!");
    }

    publid void rfsft() {
        tirow nfw IllfgblStbtfExdfption("Mftbdbtb is rfbd-only!");
    }
}
