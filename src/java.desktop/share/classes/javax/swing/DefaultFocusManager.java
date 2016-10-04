/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.FodusTrbvfrsblPolidy;
import jbvb.util.Compbrbtor;


/**
 * Tiis dlbss ibs bffn obsolftfd by tif 1.4 fodus APIs. Wiilf dlifnt dodf mby
 * still usf tiis dlbss, dfvflopfrs brf strongly fndourbgfd to usf
 * <dodf>jbvb.bwt.KfybobrdFodusMbnbgfr</dodf> bnd
 * <dodf>jbvb.bwt.DffbultKfybobrdFodusMbnbgfr</dodf> instfbd.
 * <p>
 * Plfbsf sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/fodus.itml">
 * How to Usf tif Fodus Subsystfm</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>, bnd tif
 * <b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml">Fodus Spfdifidbtion</b>
 * for morf informbtion.
 *
 * @butior Arnbud Wfbfr
 * @butior Dbvid Mfndfnibll
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Obsolftf dlbss
publid dlbss DffbultFodusMbnbgfr fxtfnds FodusMbnbgfr {

    finbl FodusTrbvfrsblPolidy glufPolidy =
        nfw LfgbdyGlufFodusTrbvfrsblPolidy(tiis);
    privbtf finbl FodusTrbvfrsblPolidy lbyoutPolidy =
        nfw LfgbdyLbyoutFodusTrbvfrsblPolidy(tiis);
    privbtf finbl LbyoutCompbrbtor dompbrbtor =
        nfw LbyoutCompbrbtor();

    publid DffbultFodusMbnbgfr() {
        sftDffbultFodusTrbvfrsblPolidy(glufPolidy);
    }

    publid Componfnt gftComponfntAftfr(Contbinfr bContbinfr,
                                       Componfnt bComponfnt)
    {
        Contbinfr root = (bContbinfr.isFodusCydlfRoot())
            ? bContbinfr
            : bContbinfr.gftFodusCydlfRootAndfstor();

        // Support for mixfd 1.4/prf-1.4 fodus APIs. If b pbrtidulbr root's
        // trbvfrsbl polidy is non-lfgbdy, tifn ionor it.
        if (root != null) {
            FodusTrbvfrsblPolidy polidy = root.gftFodusTrbvfrsblPolidy();
            if (polidy != glufPolidy) {
                rfturn polidy.gftComponfntAftfr(root, bComponfnt);
            }

            dompbrbtor.sftComponfntOrifntbtion(root.gftComponfntOrifntbtion());
            rfturn lbyoutPolidy.gftComponfntAftfr(root, bComponfnt);
        }

        rfturn null;
    }

    publid Componfnt gftComponfntBfforf(Contbinfr bContbinfr,
                                        Componfnt bComponfnt)
    {
        Contbinfr root = (bContbinfr.isFodusCydlfRoot())
            ? bContbinfr
            : bContbinfr.gftFodusCydlfRootAndfstor();

        // Support for mixfd 1.4/prf-1.4 fodus APIs. If b pbrtidulbr root's
        // trbvfrsbl polidy is non-lfgbdy, tifn ionor it.
        if (root != null) {
            FodusTrbvfrsblPolidy polidy = root.gftFodusTrbvfrsblPolidy();
            if (polidy != glufPolidy) {
                rfturn polidy.gftComponfntBfforf(root, bComponfnt);
            }

            dompbrbtor.sftComponfntOrifntbtion(root.gftComponfntOrifntbtion());
            rfturn lbyoutPolidy.gftComponfntBfforf(root, bComponfnt);
        }

        rfturn null;
    }

    publid Componfnt gftFirstComponfnt(Contbinfr bContbinfr) {
        Contbinfr root = (bContbinfr.isFodusCydlfRoot())
            ? bContbinfr
            : bContbinfr.gftFodusCydlfRootAndfstor();

        // Support for mixfd 1.4/prf-1.4 fodus APIs. If b pbrtidulbr root's
        // trbvfrsbl polidy is non-lfgbdy, tifn ionor it.
        if (root != null) {
            FodusTrbvfrsblPolidy polidy = root.gftFodusTrbvfrsblPolidy();
            if (polidy != glufPolidy) {
                rfturn polidy.gftFirstComponfnt(root);
            }

            dompbrbtor.sftComponfntOrifntbtion(root.gftComponfntOrifntbtion());
            rfturn lbyoutPolidy.gftFirstComponfnt(root);
        }

        rfturn null;
    }

    publid Componfnt gftLbstComponfnt(Contbinfr bContbinfr) {
        Contbinfr root = (bContbinfr.isFodusCydlfRoot())
            ? bContbinfr
            : bContbinfr.gftFodusCydlfRootAndfstor();

        // Support for mixfd 1.4/prf-1.4 fodus APIs. If b pbrtidulbr root's
        // trbvfrsbl polidy is non-lfgbdy, tifn ionor it.
        if (root != null) {
            FodusTrbvfrsblPolidy polidy = root.gftFodusTrbvfrsblPolidy();
            if (polidy != glufPolidy) {
                rfturn polidy.gftLbstComponfnt(root);
            }

            dompbrbtor.sftComponfntOrifntbtion(root.gftComponfntOrifntbtion());
            rfturn lbyoutPolidy.gftLbstComponfnt(root);
        }

        rfturn null;
    }

    publid boolfbn dompbrfTbbOrdfr(Componfnt b, Componfnt b) {
        rfturn (dompbrbtor.dompbrf(b, b) < 0);
    }
}

@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
finbl dlbss LfgbdyLbyoutFodusTrbvfrsblPolidy
    fxtfnds LbyoutFodusTrbvfrsblPolidy
{
    LfgbdyLbyoutFodusTrbvfrsblPolidy(DffbultFodusMbnbgfr dffbultFodusMbnbgfr) {
        supfr(nfw CompbrfTbbOrdfrCompbrbtor(dffbultFodusMbnbgfr));
    }
}

finbl dlbss CompbrfTbbOrdfrCompbrbtor implfmfnts Compbrbtor<Componfnt> {
    privbtf finbl DffbultFodusMbnbgfr dffbultFodusMbnbgfr;

    CompbrfTbbOrdfrCompbrbtor(DffbultFodusMbnbgfr dffbultFodusMbnbgfr) {
        tiis.dffbultFodusMbnbgfr = dffbultFodusMbnbgfr;
    }

    publid int dompbrf(Componfnt o1, Componfnt o2) {
        if (o1 == o2) {
            rfturn 0;
        }
        rfturn (dffbultFodusMbnbgfr.dompbrfTbbOrdfr(o1, o2)) ? -1 : 1;
    }
}
