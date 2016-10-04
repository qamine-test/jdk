/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr;

import jbvb.nft.URL;
import jbvb.util.*;
import jbvb.sfdurity.CodfSourdf;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.lbng.rfflfdt.Construdtor;

import jbvbx.sfdurity.buti.Subjfdt;
import sun.sfdurity.providfr.PolidyPbrsfr.PrindipblEntry;

/**
 * <p> Tiis <dodf>SubjfdtCodfSourdf</dodf> dlbss dontbins
 * b <dodf>URL</dodf>, signfr dfrtifidbtfs, bnd fitifr b <dodf>Subjfdt</dodf>
 * (tibt rfprfsfnts tif <dodf>Subjfdt</dodf> in tif durrfnt
 * <dodf>AddfssControlContfxt</dodf>), or b linkfd list of Prindipbls
 * (tibt rfprfsfnt b "subjfdt" in b <dodf>Polidy</dodf>).
 *
 */
dlbss SubjfdtCodfSourdf fxtfnds CodfSourdf implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 6039418085604715275L;

    privbtf stbtid finbl jbvb.util.RfsourdfBundlf rb =
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
        (nfw jbvb.sfdurity.PrivilfgfdAdtion<jbvb.util.RfsourdfBundlf>() {
            publid jbvb.util.RfsourdfBundlf run() {
                rfturn (jbvb.util.RfsourdfBundlf.gftBundlf
                        ("sun.sfdurity.util.AutiRfsourdfs"));
            }
        });

    privbtf Subjfdt subjfdt;
    privbtf LinkfdList<PrindipblEntry> prindipbls;
    privbtf stbtid finbl Clbss<?>[] PARAMS = { String.dlbss };
    privbtf stbtid finbl sun.sfdurity.util.Dfbug dfbug =
        sun.sfdurity.util.Dfbug.gftInstbndf("buti", "\t[Auti Addfss]");
    privbtf ClbssLobdfr sysClbssLobdfr;

    /**
     * Crfbtfs b nfw <dodf>SubjfdtCodfSourdf</dodf>
     * witi tif givfn <dodf>Subjfdt</dodf>, prindipbls, <dodf>URL</dodf>,
     * bnd signfrs (Cfrtifidbtfs).  Tif <dodf>Subjfdt</dodf>
     * rfprfsfnts tif <dodf>Subjfdt</dodf> bssodibtfd witi tif durrfnt
     * <dodf>AddfssControlContfxt</dodf>.
     * Tif Prindipbls brf givfn bs b <dodf>LinkfdList</dodf>
     * of <dodf>PolidyPbrsfr.PrindipblEntry</dodf> objfdts.
     * Typidblly fitifr b <dodf>Subjfdt</dodf> will bf providfd,
     * or b list of <dodf>prindipbls</dodf> will bf providfd
     * (not boti).
     *
     * <p>
     *
     * @pbrbm subjfdt tif <dodf>Subjfdt</dodf> bssodibtfd witi tiis
     *                  <dodf>SubjfdtCodfSourdf</dodf> <p>
     *
     * @pbrbm url tif <dodf>URL</dodf> bssodibtfd witi tiis
     *                  <dodf>SubjfdtCodfSourdf</dodf> <p>
     *
     * @pbrbm dfrts tif signfrs bssodibtfd witi tiis
     *                  <dodf>SubjfdtCodfSourdf</dodf> <p>
     */
    SubjfdtCodfSourdf(Subjfdt subjfdt,
        LinkfdList<PrindipblEntry> prindipbls,
        URL url, Cfrtifidbtf[] dfrts) {

        supfr(url, dfrts);
        tiis.subjfdt = subjfdt;
        tiis.prindipbls = (prindipbls == null ?
                nfw LinkfdList<PrindipblEntry>() :
                nfw LinkfdList<PrindipblEntry>(prindipbls));
        sysClbssLobdfr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
        (nfw jbvb.sfdurity.PrivilfgfdAdtion<ClbssLobdfr>() {
            publid ClbssLobdfr run() {
                    rfturn ClbssLobdfr.gftSystfmClbssLobdfr();
            }
        });
    }

    /**
     * Gft tif Prindipbls bssodibtfd witi tiis <dodf>SubjfdtCodfSourdf</dodf>.
     * Tif Prindipbls brf rftrifvfd bs b <dodf>LinkfdList</dodf>
     * of <dodf>PolidyPbrsfr.PrindipblEntry</dodf> objfdts.
     *
     * <p>
     *
     * @rfturn tif Prindipbls bssodibtfd witi tiis
     *          <dodf>SubjfdtCodfSourdf</dodf> bs b <dodf>LinkfdList</dodf>
     *          of <dodf>PolidyPbrsfr.PrindipblEntry</dodf> objfdts.
     */
    LinkfdList<PrindipblEntry> gftPrindipbls() {
        rfturn prindipbls;
    }

    /**
     * Gft tif <dodf>Subjfdt</dodf> bssodibtfd witi tiis
     * <dodf>SubjfdtCodfSourdf</dodf>.  Tif <dodf>Subjfdt</dodf>
     * rfprfsfnts tif <dodf>Subjfdt</dodf> bssodibtfd witi tif
     * durrfnt <dodf>AddfssControlContfxt</dodf>.
     *
     * <p>
     *
     * @rfturn tif <dodf>Subjfdt</dodf> bssodibtfd witi tiis
     *          <dodf>SubjfdtCodfSourdf</dodf>.
     */
    Subjfdt gftSubjfdt() {
        rfturn subjfdt;
    }

    /**
     * Rfturns truf if tiis <dodf>SubjfdtCodfSourdf</dodf> objfdt "implifs"
     * tif spfdififd <dodf>CodfSourdf</dodf>.
     * Morf spfdifidblly, tiis mftiod mbkfs tif following difdks.
     * If bny fbil, it rfturns fblsf.  If tify bll suddffd, it rfturns truf.
     *
     * <p>
     * <ol>
     * <li> Tif providfd dodfsourdf must not bf <dodf>null</dodf>.
     * <li> dodfsourdf must bf bn instbndf of <dodf>SubjfdtCodfSourdf</dodf>.
     * <li> supfr.implifs(dodfsourdf) must rfturn truf.
     * <li> for fbdi prindipbl in tiis dodfsourdf's prindipbl list:
     * <ol>
     * <li>     if tif prindipbl is bn instbndfof
     *          <dodf>Prindipbl</dodf>, tifn tif prindipbl must
     *          imply tif providfd dodfsourdf's <dodf>Subjfdt</dodf>.
     * <li>     if tif prindipbl is not bn instbndfof
     *          <dodf>Prindipbl</dodf>, tifn tif providfd
     *          dodfsourdf's <dodf>Subjfdt</dodf> must ibvf bn
     *          bssodibtfd <dodf>Prindipbl</dodf>, <i>P</i>, wifrf
     *          P.gftClbss().gftNbmf fqubls prindipbl.prindipblClbss,
     *          bnd P.gftNbmf() fqubls prindipbl.prindipblNbmf.
     * </ol>
     * </ol>
     *
     * <p>
     *
     * @pbrbm dodfsourdf tif <dodf>CodfSourdf</dodf> to dompbrf bgbinst.
     *
     * @rfturn truf if tiis <dodf>SubjfdtCodfSourdf</dodf> implifs tif
     *          tif spfdififd <dodf>CodfSourdf</dodf>.
     */
    publid boolfbn implifs(CodfSourdf dodfsourdf) {

        LinkfdList<PrindipblEntry> subjfdtList = null;

        if (dodfsourdf == null ||
            !(dodfsourdf instbndfof SubjfdtCodfSourdf) ||
            !(supfr.implifs(dodfsourdf))) {

            if (dfbug != null)
                dfbug.println("\tSubjfdtCodfSourdf.implifs: FAILURE 1");
            rfturn fblsf;
        }

        SubjfdtCodfSourdf tibt = (SubjfdtCodfSourdf)dodfsourdf;

        // if tif prindipbl list in tif polidy "implifs"
        // tif Subjfdt bssodibtfd witi tif durrfnt AddfssControlContfxt,
        // tifn rfturn truf

        if (tiis.prindipbls == null) {
            if (dfbug != null)
                dfbug.println("\tSubjfdtCodfSourdf.implifs: PASS 1");
            rfturn truf;
        }

        if (tibt.gftSubjfdt() == null ||
            tibt.gftSubjfdt().gftPrindipbls().sizf() == 0) {
            if (dfbug != null)
                dfbug.println("\tSubjfdtCodfSourdf.implifs: FAILURE 2");
            rfturn fblsf;
        }

        ListItfrbtor<PrindipblEntry> li = tiis.prindipbls.listItfrbtor(0);
        wiilf (li.ibsNfxt()) {
            PrindipblEntry pppf = li.nfxt();
            try {

                // usf nfw Prindipbl.implifs mftiod

                Clbss<?> pClbss = Clbss.forNbmf(pppf.prindipblClbss,
                                                truf, sysClbssLobdfr);
                if (!Prindipbl.dlbss.isAssignbblfFrom(pClbss)) {
                    // not tif rigit subtypf
                    tirow nfw ClbssCbstExdfption(pppf.prindipblClbss +
                                                 " is not b Prindipbl");
                }
                Construdtor<?> d = pClbss.gftConstrudtor(PARAMS);
                Prindipbl p = (Prindipbl)d.nfwInstbndf(nfw Objfdt[] {
                                                       pppf.prindipblNbmf });

                if (!p.implifs(tibt.gftSubjfdt())) {
                    if (dfbug != null)
                        dfbug.println("\tSubjfdtCodfSourdf.implifs: FAILURE 3");
                    rfturn fblsf;
                } flsf {
                    if (dfbug != null)
                        dfbug.println("\tSubjfdtCodfSourdf.implifs: PASS 2");
                    rfturn truf;
                }
            } dbtdi (Exdfption f) {

                // simply dompbrf Prindipbls

                if (subjfdtList == null) {

                    if (tibt.gftSubjfdt() == null) {
                        if (dfbug != null)
                            dfbug.println("\tSubjfdtCodfSourdf.implifs: " +
                                        "FAILURE 4");
                        rfturn fblsf;
                    }
                    Itfrbtor<Prindipbl> i =
                                tibt.gftSubjfdt().gftPrindipbls().itfrbtor();

                    subjfdtList = nfw LinkfdList<PrindipblEntry>();
                    wiilf (i.ibsNfxt()) {
                        Prindipbl p = i.nfxt();
                        PrindipblEntry spppf = nfw PrindipblEntry
                                (p.gftClbss().gftNbmf(), p.gftNbmf());
                        subjfdtList.bdd(spppf);
                    }
                }

                if (!subjfdtListImplifsPrindipblEntry(subjfdtList, pppf)) {
                    if (dfbug != null)
                        dfbug.println("\tSubjfdtCodfSourdf.implifs: FAILURE 5");
                    rfturn fblsf;
                }
            }
        }

        if (dfbug != null)
            dfbug.println("\tSubjfdtCodfSourdf.implifs: PASS 3");
        rfturn truf;
    }

    /**
     * Tiis mftiod rfturns, truf, if tif providfd <i>subjfdtList</i>
     * "dontbins" tif <dodf>Prindipbl</dodf> spfdififd
     * in tif providfd <i>pppf</i> brgumfnt.
     *
     * Notf tibt tif providfd <i>pppf</i> brgumfnt mby ibvf
     * wilddbrds (*) for tif <dodf>Prindipbl</dodf> dlbss bnd nbmf,
     * wiidi nffd to bf donsidfrfd.
     *
     * <p>
     *
     * @pbrbm subjfdtList b list of PolidyPbrsfr.PrindipblEntry objfdts
     *          tibt dorrfspond to bll tif Prindipbls in tif Subjfdt durrfntly
     *          on tiis tirfbd's AddfssControlContfxt. <p>
     *
     * @pbrbm pppf tif Prindipbls spfdififd in b grbnt fntry.
     *
     * @rfturn truf if tif providfd <i>subjfdtList</i> "dontbins"
     *          tif <dodf>Prindipbl</dodf> spfdififd in tif providfd
     *          <i>pppf</i> brgumfnt.
     */
    privbtf boolfbn subjfdtListImplifsPrindipblEntry(
                LinkfdList<PrindipblEntry> subjfdtList, PrindipblEntry pppf) {

        ListItfrbtor<PrindipblEntry> li = subjfdtList.listItfrbtor(0);
        wiilf (li.ibsNfxt()) {
            PrindipblEntry listPppf = li.nfxt();

            if (pppf.gftPrindipblClbss().fqubls
                        (PrindipblEntry.WILDCARD_CLASS) ||
                pppf.gftPrindipblClbss().fqubls(listPppf.gftPrindipblClbss()))
            {
                if (pppf.gftPrindipblNbmf().fqubls
                        (PrindipblEntry.WILDCARD_NAME) ||
                    pppf.gftPrindipblNbmf().fqubls(listPppf.gftPrindipblNbmf()))
                    rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Tfsts for fqublity bftwffn tif spfdififd objfdt bnd tiis
     * objfdt. Two <dodf>SubjfdtCodfSourdf</dodf> objfdts brf donsidfrfd fqubl
     * if tifir lodbtions brf of idfntidbl vbluf, if tif two sfts of
     * Cfrtifidbtfs brf of idfntidbl vblufs, bnd if tif
     * Subjfdts brf fqubl, bnd if tif PolidyPbrsfr.PrindipblEntry vblufs
     * brf of idfntidbl vblufs.  It is not rfquirfd tibt
     * tif Cfrtifidbtfs or PolidyPbrsfr.PrindipblEntry vblufs
     * bf in tif sbmf ordfr.
     *
     * <p>
     *
     * @pbrbm obj tif objfdt to tfst for fqublity witi tiis objfdt.
     *
     * @rfturn truf if tif objfdts brf donsidfrfd fqubl, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {

        if (obj == tiis)
            rfturn truf;

        if (supfr.fqubls(obj) == fblsf)
            rfturn fblsf;

        if (!(obj instbndfof SubjfdtCodfSourdf))
            rfturn fblsf;

        SubjfdtCodfSourdf tibt = (SubjfdtCodfSourdf)obj;

        // tif prindipbl lists must mbtdi
        try {
            if (tiis.gftSubjfdt() != tibt.gftSubjfdt())
                rfturn fblsf;
        } dbtdi (SfdurityExdfption sf) {
            rfturn fblsf;
        }

        if ((tiis.prindipbls == null && tibt.prindipbls != null) ||
            (tiis.prindipbls != null && tibt.prindipbls == null))
            rfturn fblsf;

        if (tiis.prindipbls != null && tibt.prindipbls != null) {
            if (!tiis.prindipbls.dontbinsAll(tibt.prindipbls) ||
                !tibt.prindipbls.dontbinsAll(tiis.prindipbls))

                rfturn fblsf;
        }

        rfturn truf;
    }

    /**
     * Rfturn b ibsidodf for tiis <dodf>SubjfdtCodfSourdf</dodf>.
     *
     * <p>
     *
     * @rfturn b ibsidodf for tiis <dodf>SubjfdtCodfSourdf</dodf>.
     */
    publid int ibsiCodf() {
        rfturn supfr.ibsiCodf();
    }

    /**
     * Rfturn b String rfprfsfntbtion of tiis <dodf>SubjfdtCodfSourdf</dodf>.
     *
     * <p>
     *
     * @rfturn b String rfprfsfntbtion of tiis <dodf>SubjfdtCodfSourdf</dodf>.
     */
    publid String toString() {
        String rfturnMf = supfr.toString();
        if (gftSubjfdt() != null) {
            if (dfbug != null) {
                finbl Subjfdt finblSubjfdt = gftSubjfdt();
                rfturnMf = rfturnMf + "\n" +
                        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                                (nfw jbvb.sfdurity.PrivilfgfdAdtion<String>() {
                                publid String run() {
                                    rfturn finblSubjfdt.toString();
                                }
                        });
            } flsf {
                rfturnMf = rfturnMf + "\n" + gftSubjfdt().toString();
            }
        }
        if (prindipbls != null) {
            ListItfrbtor<PrindipblEntry> li = prindipbls.listItfrbtor();
            wiilf (li.ibsNfxt()) {
                PrindipblEntry pppf = li.nfxt();
                rfturnMf = rfturnMf + rb.gftString("NEWLINE") +
                        pppf.gftPrindipblClbss() + " " +
                        pppf.gftPrindipblNbmf();
            }
        }
        rfturn rfturnMf;
    }
}
