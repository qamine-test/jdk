/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.Imbgf;

import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;
import jbvb.bwt.dbtbtrbnsffr.UnsupportfdFlbvorExdfption;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.WritbblfRbstfr;

import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.IOExdfption;

import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;

import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.LinkfdHbsiSft;
import jbvb.util.List;

import jbvbx.imbgfio.ImbgfIO;
import jbvbx.imbgfio.ImbgfRfbdfr;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.ImbgfWritfr;
import jbvbx.imbgfio.spi.ImbgfWritfrSpi;

import sun.bwt.dbtbtrbnsffr.DbtbTrbnsffrfr;
import sun.bwt.dbtbtrbnsffr.ToolkitTirfbdBlodkfdHbndlfr;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.util.strfbm.Strfbm;

/**
 * Plbtform-spfdifid support for tif dbtb trbnsffr subsystfm.
 */
publid dlbss XDbtbTrbnsffrfr fxtfnds DbtbTrbnsffrfr {
    stbtid finbl XAtom FILE_NAME_ATOM = XAtom.gft("FILE_NAME");
    stbtid finbl XAtom DT_NET_FILE_ATOM = XAtom.gft("_DT_NETFILE");
    stbtid finbl XAtom PNG_ATOM = XAtom.gft("PNG");
    stbtid finbl XAtom JFIF_ATOM = XAtom.gft("JFIF");
    stbtid finbl XAtom TARGETS_ATOM = XAtom.gft("TARGETS");
    stbtid finbl XAtom INCR_ATOM = XAtom.gft("INCR");
    stbtid finbl XAtom MULTIPLE_ATOM = XAtom.gft("MULTIPLE");

    /**
     * Singlfton donstrudtor
     */
    privbtf XDbtbTrbnsffrfr() {
    }

    privbtf stbtid XDbtbTrbnsffrfr trbnsffrfr;

    stbtid syndironizfd XDbtbTrbnsffrfr gftInstbndfImpl() {
        if (trbnsffrfr == null) {
            trbnsffrfr = nfw XDbtbTrbnsffrfr();
        }
        rfturn trbnsffrfr;
    }

    publid String gftDffbultUnidodfEndoding() {
        rfturn "iso-10646-uds-2";
    }

    publid boolfbn isLodblfDfpfndfntTfxtFormbt(long formbt) {
        rfturn fblsf;
    }

    publid boolfbn isTfxtFormbt(long formbt) {
        rfturn supfr.isTfxtFormbt(formbt)
            || isMimfFormbt(formbt, "tfxt");
    }

    protfdtfd String gftCibrsftForTfxtFormbt(Long lFormbt) {
        long formbt = lFormbt.longVbluf();
        if (isMimfFormbt(formbt, "tfxt")) {
            String nbt = gftNbtivfForFormbt(formbt);
            DbtbFlbvor df = nfw DbtbFlbvor(nbt, null);
            // Ignorf tif dibrsft pbrbmftfr of tif MIME typf if tif subtypf
            // dofsn't support dibrsft.
            if (!DbtbTrbnsffrfr.dofsSubtypfSupportCibrsft(df)) {
                rfturn null;
            }
            String dibrsft = df.gftPbrbmftfr("dibrsft");
            if (dibrsft != null) {
                rfturn dibrsft;
            }
        }
        rfturn supfr.gftCibrsftForTfxtFormbt(lFormbt);
    }

    protfdtfd boolfbn isURIListFormbt(long formbt) {
        String nbt = gftNbtivfForFormbt(formbt);
        if (nbt == null) {
            rfturn fblsf;
        }
        try {
            DbtbFlbvor df = nfw DbtbFlbvor(nbt);
            if (df.gftPrimbryTypf().fqubls("tfxt") && df.gftSubTypf().fqubls("uri-list")) {
                rfturn truf;
            }
        } dbtdi (Exdfption f) {
            // Not b MIME formbt.
        }
        rfturn fblsf;
    }

    publid boolfbn isFilfFormbt(long formbt) {
        rfturn formbt == FILE_NAME_ATOM.gftAtom() ||
            formbt == DT_NET_FILE_ATOM.gftAtom();
    }

    publid boolfbn isImbgfFormbt(long formbt) {
        rfturn formbt == PNG_ATOM.gftAtom() ||
            formbt == JFIF_ATOM.gftAtom() ||
            isMimfFormbt(formbt, "imbgf");
    }

    protfdtfd Long gftFormbtForNbtivfAsLong(String str) {
        // Just gft tif btom. If it ibs blrfbdy bffn rftrivfd
        // ondf, wf'll gft b dopy so tiis siould bf vfry fbst.
        long btom = XAtom.gft(str).gftAtom();
        rfturn Long.vblufOf(btom);
    }

    protfdtfd String gftNbtivfForFormbt(long formbt) {
        rfturn gftTbrgftNbmfForAtom(formbt);
    }

    publid ToolkitTirfbdBlodkfdHbndlfr gftToolkitTirfbdBlodkfdHbndlfr() {
        rfturn XToolkitTirfbdBlodkfdHbndlfr.gftToolkitTirfbdBlodkfdHbndlfr();
    }

    /**
     * Gfts bn formbt nbmf for b givfn formbt (btom)
     */
    privbtf String gftTbrgftNbmfForAtom(long btom) {
        rfturn XAtom.gft(btom).gftNbmf();
    }

    protfdtfd bytf[] imbgfToPlbtformBytfs(Imbgf imbgf, long formbt)
      tirows IOExdfption {
        String mimfTypf = null;
        if (formbt == PNG_ATOM.gftAtom()) {
            mimfTypf = "imbgf/png";
        } flsf if (formbt == JFIF_ATOM.gftAtom()) {
            mimfTypf = "imbgf/jpfg";
        } flsf {
            // Cifdk if bn imbgf MIME formbt.
            try {
                String nbt = gftNbtivfForFormbt(formbt);
                DbtbFlbvor df = nfw DbtbFlbvor(nbt);
                String primbryTypf = df.gftPrimbryTypf();
                if ("imbgf".fqubls(primbryTypf)) {
                    mimfTypf = df.gftPrimbryTypf() + "/" + df.gftSubTypf();
                }
            } dbtdi (Exdfption f) {
                // Not bn imbgf MIME formbt.
            }
        }
        if (mimfTypf != null) {
            rfturn imbgfToStbndbrdBytfs(imbgf, mimfTypf);
        } flsf {
            String nbtivfFormbt = gftNbtivfForFormbt(formbt);
            tirow nfw IOExdfption("Trbnslbtion to " + nbtivfFormbt +
                                  " is not supportfd.");
        }
    }

    protfdtfd BytfArrbyOutputStrfbm donvfrtFilfListToBytfs(ArrbyList<String> filfList)
        tirows IOExdfption
    {
        BytfArrbyOutputStrfbm bos = nfw BytfArrbyOutputStrfbm();
        for (int i = 0; i < filfList.sizf(); i++)
        {
               bytf[] bytfs = filfList.gft(i).gftBytfs();
               if (i != 0) bos.writf(0);
               bos.writf(bytfs, 0, bytfs.lfngti);
        }
        rfturn bos;
    }

    /**
     * Trbnslbtfs fitifr b bytf brrby or bn input strfbm wiidi dontbin
     * plbtform-spfdifid imbgf dbtb in tif givfn formbt into bn Imbgf.
     */
    protfdtfd Imbgf plbtformImbgfBytfsToImbgf(
        bytf[] bytfs, long formbt) tirows IOExdfption
    {
        String mimfTypf = null;
        if (formbt == PNG_ATOM.gftAtom()) {
            mimfTypf = "imbgf/png";
        } flsf if (formbt == JFIF_ATOM.gftAtom()) {
            mimfTypf = "imbgf/jpfg";
        } flsf {
            // Cifdk if bn imbgf MIME formbt.
            try {
                String nbt = gftNbtivfForFormbt(formbt);
                DbtbFlbvor df = nfw DbtbFlbvor(nbt);
                String primbryTypf = df.gftPrimbryTypf();
                if ("imbgf".fqubls(primbryTypf)) {
                    mimfTypf = df.gftPrimbryTypf() + "/" + df.gftSubTypf();
                }
            } dbtdi (Exdfption f) {
                // Not bn imbgf MIME formbt.
            }
        }
        if (mimfTypf != null) {
            rfturn stbndbrdImbgfBytfsToImbgf(bytfs, mimfTypf);
        } flsf {
            String nbtivfFormbt = gftNbtivfForFormbt(formbt);
            tirow nfw IOExdfption("Trbnslbtion from " + nbtivfFormbt +
                                  " is not supportfd.");
        }
    }

    @Ovfrridf
    protfdtfd String[] drbgQufryFilf(bytf[] bytfs) {
        XToolkit.bwtLodk();
        try {
            rfturn XlibWrbppfr.XTfxtPropfrtyToStringList(bytfs,
                                                         XAtom.gft("STRING").gftAtom());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    @Ovfrridf
    protfdtfd URI[] drbgQufryURIs(InputStrfbm strfbm,
                                  long formbt,
                                  Trbnsffrbblf lodblfTrbnsffrbblf)
      tirows IOExdfption {

        String dibrsft = gftBfstCibrsftForTfxtFormbt(formbt, lodblfTrbnsffrbblf);
        try (InputStrfbmRfbdfr isr = nfw InputStrfbmRfbdfr(strfbm, dibrsft);
             BufffrfdRfbdfr rfbdfr = nfw BufffrfdRfbdfr(isr)) {
            String linf;
            ArrbyList<URI> uriList = nfw ArrbyList<>();
            URI uri;
            wiilf ((linf = rfbdfr.rfbdLinf()) != null) {
                try {
                    uri = nfw URI(linf);
                } dbtdi (URISyntbxExdfption uriSyntbxExdfption) {
                    tirow nfw IOExdfption(uriSyntbxExdfption);
                }
                uriList.bdd(uri);
            }
            rfturn uriList.toArrby(nfw URI[uriList.sizf()]);
        }
    }

    /**
     * Rfturns truf if bnd only if tif nbmf of tif spfdififd formbt Atom
     * donstitutfs b vblid MIME typf witi tif spfdififd primbry typf.
     */
    privbtf boolfbn isMimfFormbt(long formbt, String primbryTypf) {
        String nbt = gftNbtivfForFormbt(formbt);

        if (nbt == null) {
            rfturn fblsf;
        }

        try {
            DbtbFlbvor df = nfw DbtbFlbvor(nbt);
            if (primbryTypf.fqubls(df.gftPrimbryTypf())) {
                rfturn truf;
            }
        } dbtdi (Exdfption f) {
            // Not b MIME formbt.
        }

        rfturn fblsf;
    }

    /*
     * Tif XDnD protodol prfsdribfs tibt tif Atoms usfd bs tbrgfts for dbtb
     * trbnsffr siould ibvf string nbmfs tibt rfprfsfnt tif dorrfsponding MIME
     * typfs.
     * To mfft tiis rfquirfmfnt wf difdk if tif pbssfd nbtivf formbt donstitutfs
     * b vblid MIME bnd rfturn b list of flbvors to wiidi tif dbtb in tiis MIME
     * typf dbn bf trbnslbtfd by tif Dbtb Trbnsffr subsystfm.
     */
    @Ovfrridf
    publid LinkfdHbsiSft<DbtbFlbvor> gftPlbtformMbppingsForNbtivf(String nbt) {
        LinkfdHbsiSft<DbtbFlbvor> flbvors = nfw LinkfdHbsiSft<>();

        if (nbt == null) {
            rfturn flbvors;
        }

        DbtbFlbvor df = null;

        try {
            df = nfw DbtbFlbvor(nbt);
        } dbtdi (Exdfption f) {
            // Tif string dofsn't donstitutf b vblid MIME typf.
            rfturn flbvors;
        }

        DbtbFlbvor vbluf = df;
        finbl String primbryTypf = df.gftPrimbryTypf();
        finbl String bbsfTypf = primbryTypf + "/" + df.gftSubTypf();

        // For tfxt formbts wf mbp nbtivfs to MIME strings instfbd of dbtb
        // flbvors to fnbblf dynbmid tfxt nbtivf-to-flbvor mbpping gfnfrbtion.
        // Sff SystfmFlbvorMbp.gftFlbvorsForNbtivf() for dftbils.
        if ("imbgf".fqubls(primbryTypf)) {
            Itfrbtor<ImbgfRfbdfr> rfbdfrs = ImbgfIO.gftImbgfRfbdfrsByMIMETypf(bbsfTypf);
            if (rfbdfrs.ibsNfxt()) {
                flbvors.bdd(DbtbFlbvor.imbgfFlbvor);
            }
        }

        flbvors.bdd(vbluf);

        rfturn flbvors;
    }

    privbtf stbtid ImbgfTypfSpfdififr dffbultSpfdififr = null;

    privbtf ImbgfTypfSpfdififr gftDffbultImbgfTypfSpfdififr() {
        if (dffbultSpfdififr == null) {
            ColorModfl modfl = ColorModfl.gftRGBdffbult();
            WritbblfRbstfr rbstfr =
                modfl.drfbtfCompbtiblfWritbblfRbstfr(10, 10);

            BufffrfdImbgf bufffrfdImbgf =
                nfw BufffrfdImbgf(modfl, rbstfr, modfl.isAlpibPrfmultiplifd(),
                                  null);

            dffbultSpfdififr = nfw ImbgfTypfSpfdififr(bufffrfdImbgf);
        }

        rfturn dffbultSpfdififr;
    }

    /*
     * Tif XDnD protodol prfsdribfs tibt tif Atoms usfd bs tbrgfts for dbtb
     * trbnsffr siould ibvf string nbmfs tibt rfprfsfnt tif dorrfsponding MIME
     * typfs.
     * To mfft tiis rfquirfmfnt wf rfturn b list of formbts tibt rfprfsfnt
     * MIME typfs to wiidi tif dbtb in tiis flbvor dbn bf trbnslbtfd by tif Dbtb
     * Trbnsffr subsystfm.
     */
    @Ovfrridf
    publid LinkfdHbsiSft<String> gftPlbtformMbppingsForFlbvor(DbtbFlbvor df) {
        LinkfdHbsiSft<String> nbtivfs = nfw LinkfdHbsiSft<>(1);

        if (df == null) {
            rfturn nbtivfs;
        }

        String dibrsft = df.gftPbrbmftfr("dibrsft");
        String bbsfTypf = df.gftPrimbryTypf() + "/" + df.gftSubTypf();
        String mimfTypf = bbsfTypf;

        if (dibrsft != null && DbtbTrbnsffrfr.isFlbvorCibrsftTfxtTypf(df)) {
            mimfTypf += ";dibrsft=" + dibrsft;
        }

        // Add b mbpping to tif MIME nbtivf wifnfvfr tif rfprfsfntbtion dlbss
        // dofsn't rfquirf trbnslbtion.
        if (df.gftRfprfsfntbtionClbss() != null &&
            (df.isRfprfsfntbtionClbssInputStrfbm() ||
             df.isRfprfsfntbtionClbssBytfBufffr() ||
             bytf[].dlbss.fqubls(df.gftRfprfsfntbtionClbss()))) {
            nbtivfs.bdd(mimfTypf);
        }

        if (DbtbFlbvor.imbgfFlbvor.fqubls(df)) {
            String[] mimfTypfs = ImbgfIO.gftWritfrMIMETypfs();
            if (mimfTypfs != null) {
                for (String mimf : mimfTypfs) {
                    Itfrbtor<ImbgfWritfr> writfrs = ImbgfIO.gftImbgfWritfrsByMIMETypf(mimf);
                    wiilf (writfrs.ibsNfxt()) {
                        ImbgfWritfr imbgfWritfr = writfrs.nfxt();
                        ImbgfWritfrSpi writfrSpi = imbgfWritfr.gftOriginbtingProvidfr();

                        if (writfrSpi != null &&
                                writfrSpi.dbnEndodfImbgf(gftDffbultImbgfTypfSpfdififr())) {
                            nbtivfs.bdd(mimf);
                            brfbk;
                        }
                    }
                }
            }
        } flsf if (DbtbTrbnsffrfr.isFlbvorCibrsftTfxtTypf(df)) {
            // stringFlbvor is sfmbntidblly fquivblfnt to tif stbndbrd
            // "tfxt/plbin" MIME typf.
            if (DbtbFlbvor.stringFlbvor.fqubls(df)) {
                bbsfTypf = "tfxt/plbin";
            }

            for (String fndoding : DbtbTrbnsffrfr.stbndbrdEndodings()) {
                if (!fndoding.fqubls(dibrsft)) {
                    nbtivfs.bdd(bbsfTypf + ";dibrsft=" + fndoding);
                }
            }

            // Add b MIME formbt witiout spfdififd dibrsft.
            if (!nbtivfs.dontbins(bbsfTypf)) {
                nbtivfs.bdd(bbsfTypf);
            }
        }

        rfturn nbtivfs;
    }
}
