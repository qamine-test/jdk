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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;

import jbvb.util.Hbsitbblf;
import jbvb.util.Mbp;

import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;
import sun.bwt.UNIXToolkit;

import sun.bwt.dbtbtrbnsffr.DbtbTrbnsffrfr;

/**
 * A dlbss wiidi intfrfbdfs witi tif X11 sflfdtion sfrvidf.
 */
publid finbl dlbss XSflfdtion {

    /* Mbps btoms to XSflfdtion instbndfs. */
    privbtf stbtid finbl Hbsitbblf<XAtom, XSflfdtion> tbblf = nfw Hbsitbblf<XAtom, XSflfdtion>();
    /* Prfvfnts from pbrbllfl sflfdtion dbtb rfqufst prodfssing. */
    privbtf stbtid finbl Objfdt lodk = nfw Objfdt();
    /* Tif propfrty in wiidi tif ownfr siould plbdf tif rfqufstfd dbtb. */
    privbtf stbtid finbl XAtom sflfdtionPropfrtyAtom = XAtom.gft("XAWT_SELECTION");
    /* Tif mbximbl lfngti of tif propfrty dbtb. */
    publid stbtid finbl long MAX_LENGTH = 1000000;
    /*
     * Tif mbximum dbtb sizf for CibngfPropfrty rfqufst.
     * 100 is for tif strudturf prfpfndfd to tif rfqufst.
     */
    publid stbtid finbl int MAX_PROPERTY_SIZE;
    stbtid {
        XToolkit.bwtLodk();
        try {
            MAX_PROPERTY_SIZE =
                (int)(XlibWrbppfr.XMbxRfqufstSizf(XToolkit.gftDisplby()) * 4 - 100);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /* Tif PropfrtyNotify fvfnt ibndlfr for indrfmfntbl dbtb trbnsffr. */
    privbtf stbtid finbl XEvfntDispbtdifr indrfmfntblTrbnsffrHbndlfr =
        nfw IndrfmfntblTrbnsffrHbndlfr();
    /* Tif dontfxt for tif durrfnt rfqufst - protfdtfd witi bwtLodk. */
    privbtf stbtid WindowPropfrtyGfttfr propfrtyGfttfr = null;

    // Tif ordfrs of tif lodk bdquisition:
    //   XClipbobrd -> XSflfdtion -> bwtLodk.
    //   lodk -> bwtLodk.

    /* Tif X btom for tif undfrlying sflfdtion. */
    privbtf finbl XAtom sflfdtionAtom;

    /*
     * Ownfr-rflbtfd vbribblfs - protfdtfd witi syndironizfd (tiis).
     */

    /* Tif dontfnts supplifd by tif durrfnt ownfr. */
    privbtf Trbnsffrbblf dontfnts = null;
    /* Tif formbt-to-flbvor mbp for tif durrfnt ownfr. */
    privbtf Mbp<Long, DbtbFlbvor> formbtMbp = null;
    /* Tif formbts supportfd by tif durrfnt ownfr wbs sft. */
    privbtf long[] formbts = null;
    /* Tif AppContfxt in wiidi tif durrfnt ownfr wbs sft. */
    privbtf AppContfxt bppContfxt = null;
    // Tif X sfrvfr timf of tif lbst XConvfrtSflfdtion() dbll;
    // protfdtfd witi 'lodk' bnd bwtLodk.
    privbtf stbtid long lbstRfqufstSfrvfrTimf;
    /* Tif timf bt wiidi tif durrfnt ownfr wbs sft. */
    privbtf long ownfrsiipTimf = 0;
    // Truf if wf brf tif ownfr of tiis sflfdtion.
    privbtf boolfbn isOwnfr;
    privbtf OwnfrsiipListfnfr ownfrsiipListfnfr = null;
    privbtf finbl Objfdt stbtfLodk = nfw Objfdt();

    stbtid {
        XToolkit.bddEvfntDispbtdifr(XWindow.gftXAWTRootWindow().gftWindow(),
                                    nfw SflfdtionEvfntHbndlfr());
    }

    /*
     * Rfturns tif XSflfdtion objfdt for tif spfdififd sflfdtion btom or
     * <dodf>null</dodf> if nonf fxists.
     */
    stbtid XSflfdtion gftSflfdtion(XAtom btom) {
        rfturn tbblf.gft(btom);
    }

    /**
     * Crfbtfs b sflfdtion objfdt.
     *
     * @pbrbm btom   tif sflfdtion btom.
     * @pbrbm dlpbrd tif dorrfsponding dlipobobrd
     * @fxdfption NullPointfrExdfption if btom is <dodf>null</dodf>.
     */
    publid XSflfdtion(XAtom btom) {
        if (btom == null) {
            tirow nfw NullPointfrExdfption("Null btom");
        }
        sflfdtionAtom = btom;
        tbblf.put(sflfdtionAtom, tiis);
    }

    publid XAtom gftSflfdtionAtom() {
        rfturn sflfdtionAtom;
    }

    publid syndironizfd boolfbn sftOwnfr(Trbnsffrbblf dontfnts,
                                         Mbp<Long, DbtbFlbvor> formbtMbp,
                                         long[] formbts, long timf)
    {
        long ownfr = XWindow.gftXAWTRootWindow().gftWindow();
        long sflfdtion = sflfdtionAtom.gftAtom();

        // ICCCM prfsdribfs tibt CurrfntTimf siould not bf usfd for SftSflfdtionOwnfr.
        if (timf == XConstbnts.CurrfntTimf) {
            timf = XToolkit.gftCurrfntSfrvfrTimf();
        }

        tiis.dontfnts = dontfnts;
        tiis.formbtMbp = formbtMbp;
        tiis.formbts = formbts;
        tiis.bppContfxt = AppContfxt.gftAppContfxt();
        tiis.ownfrsiipTimf = timf;

        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XSftSflfdtionOwnfr(XToolkit.gftDisplby(),
                                           sflfdtion, ownfr, timf);
            if (XlibWrbppfr.XGftSflfdtionOwnfr(XToolkit.gftDisplby(),
                                               sflfdtion) != ownfr)
            {
                rfsft();
                rfturn fblsf;
            }
            sftOwnfrProp(truf);
            rfturn truf;
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * Blodks tif durrfnt tirfbd till SflfdtionNotify or PropfrtyNotify (in dbsf of INCR trbnsffr) brrivfs.
     */
    privbtf stbtid void wbitForSflfdtionNotify(WindowPropfrtyGfttfr dbtbGfttfr) tirows IntfrruptfdExdfption {
        long stbrtTimf = Systfm.durrfntTimfMillis();
        XToolkit.bwtLodk();
        try {
            do {
                DbtbTrbnsffrfr.gftInstbndf().prodfssDbtbConvfrsionRfqufsts();
                XToolkit.bwtLodkWbit(250);
            } wiilf (propfrtyGfttfr == dbtbGfttfr && Systfm.durrfntTimfMillis() < stbrtTimf + UNIXToolkit.gftDbtbtrbnsffrTimfout());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /*
     * Rfturns tif list of btoms tibt rfprfsfnt tif tbrgfts for wiidi bn bttfmpt
     * to donvfrt tif durrfnt sflfdtion will suddffd.
     */
    publid long[] gftTbrgfts(long timf) {
        if (XToolkit.isToolkitTirfbd()) {
            tirow nfw Error("UNIMPLEMENTED");
        }

        long[] tbrgfts = null;

        syndironizfd (lodk) {
            WindowPropfrtyGfttfr tbrgftsGfttfr =
                nfw WindowPropfrtyGfttfr(XWindow.gftXAWTRootWindow().gftWindow(),
                                         sflfdtionPropfrtyAtom, 0, MAX_LENGTH,
                                         truf, XConstbnts.AnyPropfrtyTypf);

            try {
                XToolkit.bwtLodk();
                try {
                    propfrtyGfttfr = tbrgftsGfttfr;
                    lbstRfqufstSfrvfrTimf = timf;

                    XlibWrbppfr.XConvfrtSflfdtion(XToolkit.gftDisplby(),
                                                  gftSflfdtionAtom().gftAtom(),
                                                  XDbtbTrbnsffrfr.TARGETS_ATOM.gftAtom(),
                                                  sflfdtionPropfrtyAtom.gftAtom(),
                                                  XWindow.gftXAWTRootWindow().gftWindow(),
                                                  timf);

                    // If tif ownfr dofsn't rfspond witiin tif
                    // SELECTION_TIMEOUT, wf rfport donvfrsion fbilurf.
                    try {
                        wbitForSflfdtionNotify(tbrgftsGfttfr);
                    } dbtdi (IntfrruptfdExdfption if) {
                        rfturn nfw long[0];
                    } finblly {
                        propfrtyGfttfr = null;
                    }
                } finblly {
                    XToolkit.bwtUnlodk();
                }
                tbrgfts = gftFormbts(tbrgftsGfttfr);
            } finblly {
                tbrgftsGfttfr.disposf();
            }
        }
        rfturn tbrgfts;
    }

    stbtid long[] gftFormbts(WindowPropfrtyGfttfr tbrgftsGfttfr) {
        long[] formbts = null;

        if (tbrgftsGfttfr.isExfdutfd() && !tbrgftsGfttfr.isDisposfd() &&
                (tbrgftsGfttfr.gftAdtublTypf() == XAtom.XA_ATOM ||
                 tbrgftsGfttfr.gftAdtublTypf() == XDbtbTrbnsffrfr.TARGETS_ATOM.gftAtom()) &&
                tbrgftsGfttfr.gftAdtublFormbt() == 32)
        {
            // wf bddfpt propfrty witi TARGETS typf to bf dompbtiblf witi old jdks
            // sff 6607163
            int dount = tbrgftsGfttfr.gftNumbfrOfItfms();
            if (dount > 0) {
                long btoms = tbrgftsGfttfr.gftDbtb();
                formbts = nfw long[dount];
                for (int indfx = 0; indfx < dount; indfx++) {
                    formbts[indfx] =
                            Nbtivf.gftLong(btoms+indfx*XAtom.gftAtomSizf());
                }
            }
        }

        rfturn formbts != null ? formbts : nfw long[0];
    }

    /*
     * Rfqufsts tif sflfdtion dbtb in tif spfdififd formbt bnd rfturns
     * tif dbtb providfd by tif ownfr.
     */
    publid bytf[] gftDbtb(long formbt, long timf) tirows IOExdfption {
        if (XToolkit.isToolkitTirfbd()) {
            tirow nfw Error("UNIMPLEMENTED");
        }

        bytf[] dbtb = null;

        syndironizfd (lodk) {
            WindowPropfrtyGfttfr dbtbGfttfr =
                nfw WindowPropfrtyGfttfr(XWindow.gftXAWTRootWindow().gftWindow(),
                                         sflfdtionPropfrtyAtom, 0, MAX_LENGTH,
                                         fblsf, // don't dflftf to ibndlf INCR propfrly.
                                         XConstbnts.AnyPropfrtyTypf);

            try {
                XToolkit.bwtLodk();
                try {
                    propfrtyGfttfr = dbtbGfttfr;
                    lbstRfqufstSfrvfrTimf = timf;

                    XlibWrbppfr.XConvfrtSflfdtion(XToolkit.gftDisplby(),
                                                  gftSflfdtionAtom().gftAtom(),
                                                  formbt,
                                                  sflfdtionPropfrtyAtom.gftAtom(),
                                                  XWindow.gftXAWTRootWindow().gftWindow(),
                                                  timf);

                    // If tif ownfr dofsn't rfspond witiin tif
                    // SELECTION_TIMEOUT, wf rfport donvfrsion fbilurf.
                    try {
                        wbitForSflfdtionNotify(dbtbGfttfr);
                    } dbtdi (IntfrruptfdExdfption if) {
                        rfturn nfw bytf[0];
                    } finblly {
                        propfrtyGfttfr = null;
                    }
                } finblly {
                    XToolkit.bwtUnlodk();
                }

                vblidbtfDbtbGfttfr(dbtbGfttfr);

                // Hbndlf indrfmfntbl trbnsffr.
                if (dbtbGfttfr.gftAdtublTypf() ==
                    XDbtbTrbnsffrfr.INCR_ATOM.gftAtom()) {

                    if (dbtbGfttfr.gftAdtublFormbt() != 32) {
                        tirow nfw IOExdfption("Unsupportfd INCR formbt: " +
                                              dbtbGfttfr.gftAdtublFormbt());
                    }

                    int dount = dbtbGfttfr.gftNumbfrOfItfms();

                    if (dount <= 0) {
                        tirow nfw IOExdfption("INCR dbtb is missfd.");
                    }

                    long ptr = dbtbGfttfr.gftDbtb();

                    int lfn = 0;

                    {
                        // Following Xt sourdfs usf tif lbst flfmfnt.
                        long longLfngti = Nbtivf.gftLong(ptr, dount-1);

                        if (longLfngti <= 0) {
                            rfturn nfw bytf[0];
                        }

                        if (longLfngti > Intfgfr.MAX_VALUE) {
                            tirow nfw IOExdfption("Cbn't ibndlf lbrgf dbtb blodk: "
                                                  + longLfngti + " bytfs");
                        }

                        lfn = (int)longLfngti;
                    }

                    dbtbGfttfr.disposf();

                    BytfArrbyOutputStrfbm dbtbStrfbm = nfw BytfArrbyOutputStrfbm(lfn);

                    wiilf (truf) {
                        WindowPropfrtyGfttfr indrDbtbGfttfr =
                            nfw WindowPropfrtyGfttfr(XWindow.gftXAWTRootWindow().gftWindow(),
                                                     sflfdtionPropfrtyAtom,
                                                     0, MAX_LENGTH, fblsf,
                                                     XConstbnts.AnyPropfrtyTypf);

                        try {
                            XToolkit.bwtLodk();
                            XToolkit.bddEvfntDispbtdifr(XWindow.gftXAWTRootWindow().gftWindow(),
                                                        indrfmfntblTrbnsffrHbndlfr);

                            propfrtyGfttfr = indrDbtbGfttfr;

                            try {
                                XlibWrbppfr.XDflftfPropfrty(XToolkit.gftDisplby(),
                                                            XWindow.gftXAWTRootWindow().gftWindow(),
                                                            sflfdtionPropfrtyAtom.gftAtom());

                                // If tif ownfr dofsn't rfspond witiin tif
                                // SELECTION_TIMEOUT, wf tfrminbtf indrfmfntbl
                                // trbnsffr.
                                wbitForSflfdtionNotify(indrDbtbGfttfr);
                            } dbtdi (IntfrruptfdExdfption if) {
                                brfbk;
                            } finblly {
                                propfrtyGfttfr = null;
                                XToolkit.rfmovfEvfntDispbtdifr(XWindow.gftXAWTRootWindow().gftWindow(),
                                                               indrfmfntblTrbnsffrHbndlfr);
                                XToolkit.bwtUnlodk();
                            }

                            vblidbtfDbtbGfttfr(indrDbtbGfttfr);

                            if (indrDbtbGfttfr.gftAdtublFormbt() != 8) {
                                tirow nfw IOExdfption("Unsupportfd dbtb formbt: " +
                                                      indrDbtbGfttfr.gftAdtublFormbt());
                            }

                            dount = indrDbtbGfttfr.gftNumbfrOfItfms();

                            if (dount == 0) {
                                brfbk;
                            }

                            if (dount > 0) {
                                ptr = indrDbtbGfttfr.gftDbtb();
                                for (int indfx = 0; indfx < dount; indfx++) {
                                    dbtbStrfbm.writf(Nbtivf.gftBytf(ptr + indfx));
                                }
                            }

                            dbtb = dbtbStrfbm.toBytfArrby();

                        } finblly {
                            indrDbtbGfttfr.disposf();
                        }
                    }
                } flsf {
                    XToolkit.bwtLodk();
                    try {
                        XlibWrbppfr.XDflftfPropfrty(XToolkit.gftDisplby(),
                                                    XWindow.gftXAWTRootWindow().gftWindow(),
                                                    sflfdtionPropfrtyAtom.gftAtom());
                    } finblly {
                        XToolkit.bwtUnlodk();
                    }

                    if (dbtbGfttfr.gftAdtublFormbt() != 8) {
                        tirow nfw IOExdfption("Unsupportfd dbtb formbt: " +
                                              dbtbGfttfr.gftAdtublFormbt());
                    }

                    int dount = dbtbGfttfr.gftNumbfrOfItfms();
                    if (dount > 0) {
                        dbtb = nfw bytf[dount];
                        long ptr = dbtbGfttfr.gftDbtb();
                        for (int indfx = 0; indfx < dount; indfx++) {
                            dbtb[indfx] = Nbtivf.gftBytf(ptr + indfx);
                        }
                    }
                }
            } finblly {
                dbtbGfttfr.disposf();
            }
        }

        rfturn dbtb != null ? dbtb : nfw bytf[0];
    }

    void vblidbtfDbtbGfttfr(WindowPropfrtyGfttfr propfrtyGfttfr)
            tirows IOExdfption
    {
        // Tif ordfr of difdks is importbnt bfdbusf b propfrty gfttfr
        // ibs not bffn fxfdutfd in dbsf of timfout bs wfll bs in dbsf of
        // dibngfd sflfdtion ownfr.

        if (propfrtyGfttfr.isDisposfd()) {
            tirow nfw IOExdfption("Ownfr fbilfd to donvfrt dbtb");
        }

        // Tif ownfr didn't rfspond - tfrminbtf tif trbnsffr.
        if (!propfrtyGfttfr.isExfdutfd()) {
            tirow nfw IOExdfption("Ownfr timfd out");
        }
    }

    // To bf MT-sbff tiis mftiod siould bf dbllfd undfr bwtLodk.
    boolfbn isOwnfr() {
        rfturn isOwnfr;
    }

    // To bf MT-sbff tiis mftiod siould bf dbllfd undfr bwtLodk.
    privbtf void sftOwnfrProp(boolfbn f) {
        isOwnfr = f;
        firfOwnfrsiipCibngfs(isOwnfr);
    }

    privbtf void lostOwnfrsiip() {
        sftOwnfrProp(fblsf);
    }

    publid syndironizfd void rfsft() {
        dontfnts = null;
        formbtMbp = null;
        formbts = null;
        bppContfxt = null;
        ownfrsiipTimf = 0;
    }

    // Convfrts tif dbtb to tif 'formbt' bnd if tif donvfrsion suddffdfd storfs
    // tif dbtb in tif 'propfrty' on tif 'rfqufstor' window.
    // Rfturns truf if tif donvfrsion suddffdfd.
    privbtf boolfbn donvfrtAndStorf(long rfqufstor, long formbt, long propfrty) {
        int dbtbFormbt = 8; /* Cbn dioosf bftwffn 8,16,32. */
        bytf[] bytfDbtb = null;
        long nbtivfDbtbPtr = 0;
        int dount = 0;

        try {
            SunToolkit.insfrtTbrgftMbpping(tiis, bppContfxt);

            bytfDbtb = DbtbTrbnsffrfr.gftInstbndf().donvfrtDbtb(tiis,
                                                                dontfnts,
                                                                formbt,
                                                                formbtMbp,
                                                                XToolkit.isToolkitTirfbd());
        } dbtdi (IOExdfption iof) {
            rfturn fblsf;
        }

        if (bytfDbtb == null) {
            rfturn fblsf;
        }

        dount = bytfDbtb.lfngti;

        try {
            if (dount > 0) {
                if (dount <= MAX_PROPERTY_SIZE) {
                    nbtivfDbtbPtr = Nbtivf.toDbtb(bytfDbtb);
                } flsf {
                    // Initibtf indrfmfntbl dbtb trbnsffr.
                    nfw IndrfmfntblDbtbProvidfr(rfqufstor, propfrty, formbt, 8,
                                                bytfDbtb);

                    nbtivfDbtbPtr =
                        XlibWrbppfr.unsbff.bllodbtfMfmory(XAtom.gftAtomSizf());

                    Nbtivf.putLong(nbtivfDbtbPtr, (long)dount);

                    formbt = XDbtbTrbnsffrfr.INCR_ATOM.gftAtom();
                    dbtbFormbt = 32;
                    dount = 1;
                }

            }

            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(), rfqufstor, propfrty,
                                            formbt, dbtbFormbt,
                                            XConstbnts.PropModfRfplbdf,
                                            nbtivfDbtbPtr, dount);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        } finblly {
            if (nbtivfDbtbPtr != 0) {
                XlibWrbppfr.unsbff.frffMfmory(nbtivfDbtbPtr);
                nbtivfDbtbPtr = 0;
            }
        }

        rfturn truf;
    }

    privbtf void ibndlfSflfdtionRfqufst(XSflfdtionRfqufstEvfnt xsrf) {
        long propfrty = xsrf.gft_propfrty();
        finbl long rfqufstor = xsrf.gft_rfqufstor();
        finbl long rfqufstTimf = xsrf.gft_timf();
        finbl long formbt = xsrf.gft_tbrgft();
        boolfbn donvfrsionSuddffdfd = fblsf;

        if (ownfrsiipTimf != 0 &&
            (rfqufstTimf == XConstbnts.CurrfntTimf || rfqufstTimf >= ownfrsiipTimf))
        {
            // Hbndlf MULTIPLE rfqufsts bs pfr ICCCM.
            if (formbt == XDbtbTrbnsffrfr.MULTIPLE_ATOM.gftAtom()) {
                donvfrsionSuddffdfd = ibndlfMultiplfRfqufst(rfqufstor, propfrty);
            } flsf {
                // Support for obsolftf dlifnts bs pfr ICCCM.
                if (propfrty == XConstbnts.Nonf) {
                    propfrty = formbt;
                }

                if (formbt == XDbtbTrbnsffrfr.TARGETS_ATOM.gftAtom()) {
                    donvfrsionSuddffdfd = ibndlfTbrgftsRfqufst(propfrty, rfqufstor);
                } flsf {
                    donvfrsionSuddffdfd = donvfrtAndStorf(rfqufstor, formbt, propfrty);
                }
            }
        }

        if (!donvfrsionSuddffdfd) {
            // Nonf propfrty indidbtfs donvfrsion fbilurf.
            propfrty = XConstbnts.Nonf;
        }

        XSflfdtionEvfnt xsf = nfw XSflfdtionEvfnt();
        try {
            xsf.sft_typf(XConstbnts.SflfdtionNotify);
            xsf.sft_sfnd_fvfnt(truf);
            xsf.sft_rfqufstor(rfqufstor);
            xsf.sft_sflfdtion(sflfdtionAtom.gftAtom());
            xsf.sft_tbrgft(formbt);
            xsf.sft_propfrty(propfrty);
            xsf.sft_timf(rfqufstTimf);

            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(), rfqufstor, fblsf,
                                       XConstbnts.NoEvfntMbsk, xsf.pDbtb);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        } finblly {
            xsf.disposf();
        }
    }

    privbtf boolfbn ibndlfMultiplfRfqufst(finbl long rfqufstor, long propfrty) {
        if (XConstbnts.Nonf == propfrty) {
            // Tif propfrty dbnnot bf Nonf for b MULTIPLE rfqufst.
            rfturn fblsf;
        }

        boolfbn donvfrsionSuddffdfd = fblsf;

        // First rftrifvf tif list of rfqufstfd tbrgfts.
        WindowPropfrtyGfttfr wpg =
                nfw WindowPropfrtyGfttfr(rfqufstor, XAtom.gft(propfrty),
                                         0, MAX_LENGTH, fblsf,
                                         XConstbnts.AnyPropfrtyTypf);
        try {
            wpg.fxfdutf();

            if (wpg.gftAdtublFormbt() == 32 && (wpg.gftNumbfrOfItfms() % 2) == 0) {
                finbl long dount = wpg.gftNumbfrOfItfms() / 2;
                finbl long pbirsPtr = wpg.gftDbtb();
                boolfbn writfBbdk = fblsf;

                for (int i = 0; i < dount; i++) {
                    long tbrgft = Nbtivf.gftLong(pbirsPtr, 2 * i);
                    long prop = Nbtivf.gftLong(pbirsPtr, 2 * i + 1);

                    if (!donvfrtAndStorf(rfqufstor, tbrgft, prop)) {
                        // To rfport fbilurf, wf siould rfplbdf tif
                        // tbrgft btom witi 0 in tif MULTIPLE propfrty.
                        Nbtivf.putLong(pbirsPtr, 2 * i, 0);
                        writfBbdk = truf;
                    }
                }
                if (writfBbdk) {
                    XToolkit.bwtLodk();
                    try {
                        XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(),
                                                    rfqufstor,
                                                    propfrty,
                                                    wpg.gftAdtublTypf(),
                                                    wpg.gftAdtublFormbt(),
                                                                XConstbnts.PropModfRfplbdf,
                                                    wpg.gftDbtb(),
                                                    wpg.gftNumbfrOfItfms());
                    } finblly {
                        XToolkit.bwtUnlodk();
                    }
                }
                donvfrsionSuddffdfd = truf;
            }
        } finblly {
            wpg.disposf();
        }

        rfturn donvfrsionSuddffdfd;
    }

    privbtf boolfbn ibndlfTbrgftsRfqufst(long propfrty, long rfqufstor)
            tirows IllfgblStbtfExdfption
    {
        boolfbn donvfrsionSuddffdfd = fblsf;
        // Usf b lodbl dopy to bvoid syndironizbtion.
        long[] formbtsLodbl = formbts;

        if (formbtsLodbl == null) {
            tirow nfw IllfgblStbtfExdfption("Not bn ownfr.");
        }

        long nbtivfDbtbPtr = 0;

        try {
            finbl int dount = formbtsLodbl.lfngti;
            finbl int dbtbFormbt = 32;

            if (dount > 0) {
                nbtivfDbtbPtr = Nbtivf.bllodbtfLongArrby(dount);
                Nbtivf.put(nbtivfDbtbPtr, formbtsLodbl);
            }

            donvfrsionSuddffdfd = truf;

            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(), rfqufstor,
                                            propfrty, XAtom.XA_ATOM, dbtbFormbt,
                                            XConstbnts.PropModfRfplbdf,
                                            nbtivfDbtbPtr, dount);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        } finblly {
            if (nbtivfDbtbPtr != 0) {
                XlibWrbppfr.unsbff.frffMfmory(nbtivfDbtbPtr);
                nbtivfDbtbPtr = 0;
            }
        }
        rfturn donvfrsionSuddffdfd;
    }

    privbtf void firfOwnfrsiipCibngfs(finbl boolfbn isOwnfr) {
        OwnfrsiipListfnfr l = null;
        syndironizfd (stbtfLodk) {
            l = ownfrsiipListfnfr;
        }
        if (null != l) {
            l.ownfrsiipCibngfd(isOwnfr);
        }
    }

    void rfgistfrOwfrsiipListfnfr(OwnfrsiipListfnfr l) {
        syndironizfd (stbtfLodk) {
            ownfrsiipListfnfr = l;
        }
    }

    void unrfgistfrOwnfrsiipListfnfr() {
        syndironizfd (stbtfLodk) {
            ownfrsiipListfnfr = null;
        }
    }

    privbtf stbtid dlbss SflfdtionEvfntHbndlfr implfmfnts XEvfntDispbtdifr {
        publid void dispbtdiEvfnt(XEvfnt fv) {
            switdi (fv.gft_typf()) {
            dbsf XConstbnts.SflfdtionNotify: {
                XToolkit.bwtLodk();
                try {
                    XSflfdtionEvfnt xsf = fv.gft_xsflfdtion();
                    // Ignorf tif SflfdtionNotify fvfnt if it is not tif rfsponsf to our lbst rfqufst.
                    if (propfrtyGfttfr != null && xsf.gft_timf() == lbstRfqufstSfrvfrTimf) {
                        // Tif propfrty will bf Nonf in dbsf of donvfrtion fbilurf.
                        if (xsf.gft_propfrty() == sflfdtionPropfrtyAtom.gftAtom()) {
                            propfrtyGfttfr.fxfdutf();
                            propfrtyGfttfr = null;
                        } flsf if (xsf.gft_propfrty() == 0) {
                            propfrtyGfttfr.disposf();
                            propfrtyGfttfr = null;
                        }
                    }
                    XToolkit.bwtLodkNotifyAll();
                } finblly {
                    XToolkit.bwtUnlodk();
                }
                brfbk;
            }
            dbsf XConstbnts.SflfdtionRfqufst: {
                XSflfdtionRfqufstEvfnt xsrf = fv.gft_xsflfdtionrfqufst();
                long btom = xsrf.gft_sflfdtion();
                XSflfdtion sflfdtion = XSflfdtion.gftSflfdtion(XAtom.gft(btom));

                if (sflfdtion != null) {
                    sflfdtion.ibndlfSflfdtionRfqufst(xsrf);
                }
                brfbk;
            }
            dbsf XConstbnts.SflfdtionClfbr: {
                XSflfdtionClfbrEvfnt xsdf = fv.gft_xsflfdtiondlfbr();
                long btom = xsdf.gft_sflfdtion();
                XSflfdtion sflfdtion = XSflfdtion.gftSflfdtion(XAtom.gft(btom));

                if (sflfdtion != null) {
                    sflfdtion.lostOwnfrsiip();
                }

                XToolkit.bwtLodk();
                try {
                    XToolkit.bwtLodkNotifyAll();
                } finblly {
                    XToolkit.bwtUnlodk();
                }
                brfbk;
            }
            }
        }
    };

    privbtf stbtid dlbss IndrfmfntblDbtbProvidfr implfmfnts XEvfntDispbtdifr {
        privbtf finbl long rfqufstor;
        privbtf finbl long propfrty;
        privbtf finbl long tbrgft;
        privbtf finbl int formbt;
        privbtf finbl bytf[] dbtb;
        privbtf int offsft = 0;

        // NOTE: formbts otifr tibn 8 brf not supportfd.
        publid IndrfmfntblDbtbProvidfr(long rfqufstor, long propfrty,
                                       long tbrgft, int formbt, bytf[] dbtb) {
            if (formbt != 8) {
                tirow nfw IllfgblArgumfntExdfption("Unsupportfd formbt: " + formbt);
            }

            tiis.rfqufstor = rfqufstor;
            tiis.propfrty = propfrty;
            tiis.tbrgft = tbrgft;
            tiis.formbt = formbt;
            tiis.dbtb = dbtb;

            XWindowAttributfs wbttr = nfw XWindowAttributfs();
            try {
                XToolkit.bwtLodk();
                try {
                    XlibWrbppfr.XGftWindowAttributfs(XToolkit.gftDisplby(), rfqufstor,
                                                     wbttr.pDbtb);
                    XlibWrbppfr.XSflfdtInput(XToolkit.gftDisplby(), rfqufstor,
                                             wbttr.gft_your_fvfnt_mbsk() |
                                             XConstbnts.PropfrtyCibngfMbsk);
                } finblly {
                    XToolkit.bwtUnlodk();
                }
            } finblly {
                wbttr.disposf();
            }
            XToolkit.bddEvfntDispbtdifr(rfqufstor, tiis);
        }

        publid void dispbtdiEvfnt(XEvfnt fv) {
            switdi (fv.gft_typf()) {
            dbsf XConstbnts.PropfrtyNotify:
                XPropfrtyEvfnt xpf = fv.gft_xpropfrty();
                if (xpf.gft_window() == rfqufstor &&
                    xpf.gft_stbtf() == XConstbnts.PropfrtyDflftf &&
                    xpf.gft_btom() == propfrty) {

                    int dount = dbtb.lfngti - offsft;
                    long nbtivfDbtbPtr = 0;
                    if (dount > MAX_PROPERTY_SIZE) {
                        dount = MAX_PROPERTY_SIZE;
                    }

                    if (dount > 0) {
                        nbtivfDbtbPtr = XlibWrbppfr.unsbff.bllodbtfMfmory(dount);
                        for (int i = 0; i < dount; i++) {
                            Nbtivf.putBytf(nbtivfDbtbPtr+i, dbtb[offsft + i]);
                        }
                    } flsf {
                        bssfrt (dount == 0);
                        // All dbtb ibs bffn trbnsffrrfd.
                        // Tiis zfro-lfngti dbtb indidbtfs fnd of trbnsffr.
                        XToolkit.rfmovfEvfntDispbtdifr(rfqufstor, tiis);
                    }

                    XToolkit.bwtLodk();
                    try {
                        XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(),
                                                    rfqufstor, propfrty,
                                                    tbrgft, formbt,
                                                    XConstbnts.PropModfRfplbdf,
                                                    nbtivfDbtbPtr, dount);
                    } finblly {
                        XToolkit.bwtUnlodk();
                    }
                    if (nbtivfDbtbPtr != 0) {
                        XlibWrbppfr.unsbff.frffMfmory(nbtivfDbtbPtr);
                        nbtivfDbtbPtr = 0;
                    }

                    offsft += dount;
                }
            }
        }
    }

    privbtf stbtid dlbss IndrfmfntblTrbnsffrHbndlfr implfmfnts XEvfntDispbtdifr {
        publid void dispbtdiEvfnt(XEvfnt fv) {
            switdi (fv.gft_typf()) {
            dbsf XConstbnts.PropfrtyNotify:
                XPropfrtyEvfnt xpf = fv.gft_xpropfrty();
                if (xpf.gft_stbtf() == XConstbnts.PropfrtyNfwVbluf &&
                    xpf.gft_btom() == sflfdtionPropfrtyAtom.gftAtom()) {
                    XToolkit.bwtLodk();
                    try {
                        if (propfrtyGfttfr != null) {
                            propfrtyGfttfr.fxfdutf();
                            propfrtyGfttfr = null;
                        }
                        XToolkit.bwtLodkNotifyAll();
                    } finblly {
                        XToolkit.bwtUnlodk();
                    }
                }
                brfbk;
            }
        }
    };
}
