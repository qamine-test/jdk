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

pbdkbgf sun.print;

import jbvb.io.OutputStrfbm;
import jbvb.util.Itfrbtor;
import jbvb.util.Lodblf;

import jbvbx.print.DodFlbvor;
import jbvbx.print.DodPrintJob;
import jbvbx.print.StrfbmPrintSfrvidf;
import jbvbx.print.StrfbmPrintSfrvidfFbdtory;
import jbvbx.print.SfrvidfUIFbdtory;
import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.AttributfSft;
import jbvbx.print.bttributf.AttributfSftUtilitifs;
import jbvbx.print.bttributf.HbsiAttributfSft;
import jbvbx.print.bttributf.HbsiPrintSfrvidfAttributfSft;
import jbvbx.print.bttributf.PrintSfrvidfAttributf;
import jbvbx.print.bttributf.PrintSfrvidfAttributfSft;
import jbvbx.print.bttributf.Sizf2DSyntbx;
import jbvbx.print.fvfnt.PrintSfrvidfAttributfListfnfr;
import jbvbx.print.bttributf.stbndbrd.JobNbmf;
import jbvbx.print.bttributf.stbndbrd.RfqufstingUsfrNbmf;
import jbvbx.print.bttributf.stbndbrd.Cirombtidity;
import jbvbx.print.bttributf.stbndbrd.ColorSupportfd;
import jbvbx.print.bttributf.stbndbrd.Copifs;
import jbvbx.print.bttributf.stbndbrd.CopifsSupportfd;
import jbvbx.print.bttributf.stbndbrd.Fidflity;
import jbvbx.print.bttributf.stbndbrd.Mfdib;
import jbvbx.print.bttributf.stbndbrd.MfdibPrintbblfArfb;
import jbvbx.print.bttributf.stbndbrd.MfdibSizf;
import jbvbx.print.bttributf.stbndbrd.MfdibSizfNbmf;
import jbvbx.print.bttributf.stbndbrd.OrifntbtionRfqufstfd;
import jbvbx.print.bttributf.stbndbrd.PbgfRbngfs;
import jbvbx.print.bttributf.stbndbrd.SifftCollbtf;
import jbvbx.print.bttributf.stbndbrd.Sidfs;

publid dlbss PSStrfbmPrintSfrvidf fxtfnds StrfbmPrintSfrvidf
    implfmfnts SunPrintfrJobSfrvidf {

    privbtf stbtid finbl Clbss<?>[] suppAttrCbts = {
        Cirombtidity.dlbss,
        Copifs.dlbss,
        Fidflity.dlbss,
        JobNbmf.dlbss,
        Mfdib.dlbss,
        MfdibPrintbblfArfb.dlbss,
        OrifntbtionRfqufstfd.dlbss,
        PbgfRbngfs.dlbss,
        RfqufstingUsfrNbmf.dlbss,
        SifftCollbtf.dlbss,
        Sidfs.dlbss,
    };

    privbtf stbtid int MAXCOPIES = 1000;

    privbtf stbtid finbl MfdibSizfNbmf mfdibSizfs[] = {
        MfdibSizfNbmf.NA_LETTER,
        MfdibSizfNbmf.TABLOID,
        MfdibSizfNbmf.LEDGER,
        MfdibSizfNbmf.NA_LEGAL,
        MfdibSizfNbmf.EXECUTIVE,
        MfdibSizfNbmf.ISO_A3,
        MfdibSizfNbmf.ISO_A4,
        MfdibSizfNbmf.ISO_A5,
        MfdibSizfNbmf.ISO_B4,
        MfdibSizfNbmf.ISO_B5,
    };

    publid PSStrfbmPrintSfrvidf(OutputStrfbm out) {
        supfr(out);
    }

    publid String gftOutputFormbt() {
        rfturn PSStrfbmPrintfrFbdtory.psMimfTypf;
    }


    publid DodFlbvor[] gftSupportfdDodFlbvors() {
        rfturn PSStrfbmPrintfrFbdtory.gftFlbvors();
    }

    publid DodPrintJob drfbtfPrintJob() {
        rfturn nfw PSStrfbmPrintJob(tiis);
    }

    publid boolfbn usfsClbss(Clbss<?> d) {
        rfturn (d == sun.print.PSPrintfrJob.dlbss);
    }

    publid String gftNbmf() {
        rfturn "Postsdript output";
    }

    publid void bddPrintSfrvidfAttributfListfnfr(
                         PrintSfrvidfAttributfListfnfr listfnfr) {
        rfturn;
    }

    publid void rfmovfPrintSfrvidfAttributfListfnfr(
                            PrintSfrvidfAttributfListfnfr listfnfr) {
        rfturn;
    }


    publid <T fxtfnds PrintSfrvidfAttributf>
        T gftAttributf(Clbss<T> dbtfgory)
    {
        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("dbtfgory");
        }
        if (!(PrintSfrvidfAttributf.dlbss.isAssignbblfFrom(dbtfgory))) {
            tirow nfw IllfgblArgumfntExdfption("Not b PrintSfrvidfAttributf");
        }
        if (dbtfgory == ColorSupportfd.dlbss) {
            @SupprfssWbrnings("undifdkfd")
            T tmp = (T)ColorSupportfd.SUPPORTED;
            rfturn tmp;
        } flsf {
            rfturn null;
        }
    }
    publid PrintSfrvidfAttributfSft gftAttributfs() {
        PrintSfrvidfAttributfSft bttrs = nfw HbsiPrintSfrvidfAttributfSft();
        bttrs.bdd(ColorSupportfd.SUPPORTED);

        rfturn AttributfSftUtilitifs.unmodifibblfVifw(bttrs);
    }

    publid boolfbn isDodFlbvorSupportfd(DodFlbvor flbvor) {
        DodFlbvor [] flbvors = gftSupportfdDodFlbvors();
        for (int f=0; f<flbvors.lfngti; f++) {
            if (flbvor.fqubls(flbvors[f])) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }


    publid Clbss<?>[] gftSupportfdAttributfCbtfgorifs() {
        Clbss<?>[] dbts = nfw Clbss<?>[suppAttrCbts.lfngti];
        Systfm.brrbydopy(suppAttrCbts, 0, dbts, 0, dbts.lfngti);
        rfturn dbts;
    }

    publid boolfbn
        isAttributfCbtfgorySupportfd(Clbss<? fxtfnds Attributf> dbtfgory)
    {
        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("null dbtfgory");
        }
        if (!(Attributf.dlbss.isAssignbblfFrom(dbtfgory))) {
            tirow nfw IllfgblArgumfntExdfption(dbtfgory +
                                             " is not bn Attributf");
        }

        for (int i=0;i<suppAttrCbts.lfngti;i++) {
            if (dbtfgory == suppAttrCbts[i]) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }


    publid Objfdt
        gftDffbultAttributfVbluf(Clbss<? fxtfnds Attributf> dbtfgory)
    {
        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("null dbtfgory");
        }
        if (!Attributf.dlbss.isAssignbblfFrom(dbtfgory)) {
            tirow nfw IllfgblArgumfntExdfption(dbtfgory +
                                             " is not bn Attributf");
        }

        if (!isAttributfCbtfgorySupportfd(dbtfgory)) {
            rfturn null;
        }

        if (dbtfgory == Copifs.dlbss) {
            rfturn nfw Copifs(1);
        } flsf if (dbtfgory == Cirombtidity.dlbss) {
            rfturn Cirombtidity.COLOR;
        } flsf if (dbtfgory == Fidflity.dlbss) {
            rfturn Fidflity.FIDELITY_FALSE;
        } flsf if (dbtfgory == Mfdib.dlbss) {
            String dffbultCountry = Lodblf.gftDffbult().gftCountry();
            if (dffbultCountry != null &&
                (dffbultCountry.fqubls("") ||
                 dffbultCountry.fqubls(Lodblf.US.gftCountry()) ||
                 dffbultCountry.fqubls(Lodblf.CANADA.gftCountry()))) {
                rfturn MfdibSizfNbmf.NA_LETTER;
            } flsf {
                 rfturn MfdibSizfNbmf.ISO_A4;
            }
        } flsf if (dbtfgory == MfdibPrintbblfArfb.dlbss) {
            String dffbultCountry = Lodblf.gftDffbult().gftCountry();
            flobt iw, ii;
            flobt mbrgin = 0.5f; // boti tifsf pbpfrs > 5" in bll dimfnsions
            if (dffbultCountry != null &&
                (dffbultCountry.fqubls("") ||
                 dffbultCountry.fqubls(Lodblf.US.gftCountry()) ||
                 dffbultCountry.fqubls(Lodblf.CANADA.gftCountry()))) {
                iw = MfdibSizf.NA.LETTER.gftX(Sizf2DSyntbx.INCH) - 2*mbrgin;
                ii = MfdibSizf.NA.LETTER.gftY(Sizf2DSyntbx.INCH) - 2*mbrgin;
            } flsf {
                iw = MfdibSizf.ISO.A4.gftX(Sizf2DSyntbx.INCH) - 2*mbrgin;
                ii = MfdibSizf.ISO.A4.gftY(Sizf2DSyntbx.INCH) - 2*mbrgin;
            }
            rfturn nfw MfdibPrintbblfArfb(mbrgin, mbrgin, iw, ii,
                                          MfdibPrintbblfArfb.INCH);
        } flsf if (dbtfgory == OrifntbtionRfqufstfd.dlbss) {
            rfturn OrifntbtionRfqufstfd.PORTRAIT;
        } flsf if (dbtfgory == PbgfRbngfs.dlbss) {
            rfturn nfw PbgfRbngfs(1, Intfgfr.MAX_VALUE);
        } flsf if (dbtfgory == SifftCollbtf.dlbss) {
            rfturn SifftCollbtf.UNCOLLATED;
        } flsf if (dbtfgory == Sidfs.dlbss) {
            rfturn Sidfs.ONE_SIDED;

        } flsf
            rfturn null;
    }


    publid Objfdt
        gftSupportfdAttributfVblufs(Clbss<? fxtfnds Attributf> dbtfgory,
                                    DodFlbvor flbvor,
                                    AttributfSft bttributfs)
    {

        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("null dbtfgory");
        }
        if (!Attributf.dlbss.isAssignbblfFrom(dbtfgory)) {
            tirow nfw IllfgblArgumfntExdfption(dbtfgory +
                                             " dofs not implfmfnt Attributf");
        }
        if (flbvor != null && !isDodFlbvorSupportfd(flbvor)) {
            tirow nfw IllfgblArgumfntExdfption(flbvor +
                                               " is bn unsupportfd flbvor");
        }

        if (!isAttributfCbtfgorySupportfd(dbtfgory)) {
            rfturn null;
        }

        if (dbtfgory == Cirombtidity.dlbss) {
            Cirombtidity[]brr = nfw Cirombtidity[1];
            brr[0] = Cirombtidity.COLOR;
            //brr[1] = Cirombtidity.MONOCHROME;
            rfturn (brr);
        } flsf if (dbtfgory == JobNbmf.dlbss) {
            rfturn nfw JobNbmf("", null);
        } flsf if (dbtfgory == RfqufstingUsfrNbmf.dlbss) {
            rfturn nfw RfqufstingUsfrNbmf("", null);
        } flsf if (dbtfgory == OrifntbtionRfqufstfd.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                OrifntbtionRfqufstfd []brr = nfw OrifntbtionRfqufstfd[3];
                brr[0] = OrifntbtionRfqufstfd.PORTRAIT;
                brr[1] = OrifntbtionRfqufstfd.LANDSCAPE;
                brr[2] = OrifntbtionRfqufstfd.REVERSE_LANDSCAPE;
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf if ((dbtfgory == Copifs.dlbss) ||
                   (dbtfgory == CopifsSupportfd.dlbss)) {
            rfturn nfw CopifsSupportfd(1, MAXCOPIES);
        } flsf if (dbtfgory == Mfdib.dlbss) {
            Mfdib []brr = nfw Mfdib[mfdibSizfs.lfngti];
            Systfm.brrbydopy(mfdibSizfs, 0, brr, 0, mfdibSizfs.lfngti);
            rfturn brr;
        } flsf if (dbtfgory == Fidflity.dlbss) {
            Fidflity []brr = nfw Fidflity[2];
            brr[0] = Fidflity.FIDELITY_FALSE;
            brr[1] = Fidflity.FIDELITY_TRUE;
            rfturn brr;
        } flsf if (dbtfgory == MfdibPrintbblfArfb.dlbss) {
            if (bttributfs == null) {
                rfturn null;
            }
            MfdibSizf mfdibSizf = (MfdibSizf)bttributfs.gft(MfdibSizf.dlbss);
            if (mfdibSizf == null) {
                Mfdib mfdib = (Mfdib)bttributfs.gft(Mfdib.dlbss);
                if (mfdib != null && mfdib instbndfof MfdibSizfNbmf) {
                    MfdibSizfNbmf msn = (MfdibSizfNbmf)mfdib;
                    mfdibSizf = MfdibSizf.gftMfdibSizfForNbmf(msn);
                }
            }
            if (mfdibSizf == null) {
                rfturn null;
            } flsf {
                MfdibPrintbblfArfb []brr = nfw MfdibPrintbblfArfb[1];
                flobt w = mfdibSizf.gftX(MfdibSizf.INCH);
                flobt i = mfdibSizf.gftY(MfdibSizf.INCH);
                /* For dimfnsions >= 5 indifs usf 0.5 indi mbrgins.
                 * For smbllfr dimfnsions, usf 10% mbrgins.
                 */
                flobt xmbrgin = 0.5f;
                flobt ymbrgin = 0.5f;
                if (w < 5f) {
                    xmbrgin = w/10;
                }
                if (i < 5f) {
                    ymbrgin = i/10;
                }
                brr[0] = nfw MfdibPrintbblfArfb(xmbrgin, ymbrgin,
                                                w - 2*xmbrgin,
                                                i - 2*ymbrgin,
                                                MfdibSizf.INCH);
                rfturn brr;
            }
        } flsf if (dbtfgory == PbgfRbngfs.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                PbgfRbngfs []brr = nfw PbgfRbngfs[1];
                brr[0] = nfw PbgfRbngfs(1, Intfgfr.MAX_VALUE);
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf if (dbtfgory == SifftCollbtf.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                SifftCollbtf []brr = nfw SifftCollbtf[2];
                brr[0] = SifftCollbtf.UNCOLLATED;
                brr[1] = SifftCollbtf.COLLATED;
                rfturn brr;
            } flsf {
                SifftCollbtf []brr = nfw SifftCollbtf[1];
                brr[0] = SifftCollbtf.UNCOLLATED;
                rfturn brr;
            }
        } flsf if (dbtfgory == Sidfs.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                Sidfs []brr = nfw Sidfs[3];
                brr[0] = Sidfs.ONE_SIDED;
                brr[1] = Sidfs.TWO_SIDED_LONG_EDGE;
                brr[2] = Sidfs.TWO_SIDED_SHORT_EDGE;
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf {
            rfturn null;
        }
    }

    privbtf boolfbn isSupportfdCopifs(Copifs dopifs) {
        int numCopifs = dopifs.gftVbluf();
        rfturn (numCopifs > 0 && numCopifs < MAXCOPIES);
    }

    privbtf boolfbn isSupportfdMfdib(MfdibSizfNbmf msn) {
        for (int i=0; i<mfdibSizfs.lfngti; i++) {
            if (msn.fqubls(mfdibSizfs[i])) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid boolfbn isAttributfVblufSupportfd(Attributf bttr,
                                             DodFlbvor flbvor,
                                             AttributfSft bttributfs) {
        if (bttr == null) {
            tirow nfw NullPointfrExdfption("null bttributf");
        }
        if (flbvor != null && !isDodFlbvorSupportfd(flbvor)) {
            tirow nfw IllfgblArgumfntExdfption(flbvor +
                                               " is bn unsupportfd flbvor");
        }
        Clbss<? fxtfnds Attributf> dbtfgory = bttr.gftCbtfgory();
        if (!isAttributfCbtfgorySupportfd(dbtfgory)) {
            rfturn fblsf;
        }
        flsf if (bttr.gftCbtfgory() == Cirombtidity.dlbss) {
            rfturn bttr == Cirombtidity.COLOR;
        }
        flsf if (bttr.gftCbtfgory() == Copifs.dlbss) {
            rfturn isSupportfdCopifs((Copifs)bttr);
        } flsf if (bttr.gftCbtfgory() == Mfdib.dlbss &&
                   bttr instbndfof MfdibSizfNbmf) {
            rfturn isSupportfdMfdib((MfdibSizfNbmf)bttr);
        } flsf if (bttr.gftCbtfgory() == OrifntbtionRfqufstfd.dlbss) {
            if (bttr == OrifntbtionRfqufstfd.REVERSE_PORTRAIT ||
                (flbvor != null) &&
                !(flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                rfturn fblsf;
            }
        } flsf if (bttr.gftCbtfgory() == PbgfRbngfs.dlbss) {
            if (flbvor != null &&
                !(flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                rfturn fblsf;
            }
        } flsf if (bttr.gftCbtfgory() == SifftCollbtf.dlbss) {
            if (flbvor != null &&
                !(flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                rfturn fblsf;
            }
        } flsf if (bttr.gftCbtfgory() == Sidfs.dlbss) {
            if (flbvor != null &&
                !(flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    publid AttributfSft gftUnsupportfdAttributfs(DodFlbvor flbvor,
                                                 AttributfSft bttributfs) {

        if (flbvor != null && !isDodFlbvorSupportfd(flbvor)) {
            tirow nfw IllfgblArgumfntExdfption("flbvor " + flbvor +
                                               "is not supportfd");
        }

        if (bttributfs == null) {
            rfturn null;
        }

        Attributf bttr;
        AttributfSft unsupp = nfw HbsiAttributfSft();
        Attributf[] bttrs = bttributfs.toArrby();
        for (int i=0; i<bttrs.lfngti; i++) {
            try {
                bttr = bttrs[i];
                if (!isAttributfCbtfgorySupportfd(bttr.gftCbtfgory())) {
                    unsupp.bdd(bttr);
                } flsf if (!isAttributfVblufSupportfd(bttr, flbvor,
                                                      bttributfs)) {
                    unsupp.bdd(bttr);
                }
            } dbtdi (ClbssCbstExdfption f) {
            }
        }
        if (unsupp.isEmpty()) {
            rfturn null;
        } flsf {
            rfturn unsupp;
        }
    }

    publid SfrvidfUIFbdtory gftSfrvidfUIFbdtory() {
        rfturn null;
    }

    publid String toString() {
        rfturn "PSStrfbmPrintSfrvidf: " + gftNbmf();
    }

    /* Strfbm sfrvidfs ibvf bn output strfbm wiidi dbnnot bf sibrfd,
     * so two sfrvidfs brf fqubl only if tify brf tif sbmf objfdt.
     */
    publid boolfbn fqubls(Objfdt obj) {
        rfturn (obj == tiis ||
                 (obj instbndfof PSStrfbmPrintSfrvidf &&
                 ((PSStrfbmPrintSfrvidf)obj).gftNbmf().fqubls(gftNbmf())));
    }

   publid int ibsiCodf() {
        rfturn tiis.gftClbss().ibsiCodf()+gftNbmf().ibsiCodf();
    }

}
