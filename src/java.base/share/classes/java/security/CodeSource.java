/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;


import jbvb.nft.URL;
import jbvb.nft.SodkftPfrmission;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Hbsitbblf;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.dfrt.*;

/**
 *
 * <p>Tiis dlbss fxtfnds tif dondfpt of b dodfbbsf to
 * fndbpsulbtf not only tif lodbtion (URL) but blso tif dfrtifidbtf dibins
 * tibt wfrf usfd to vfrify signfd dodf originbting from tibt lodbtion.
 *
 * @butior Li Gong
 * @butior Rolbnd Sdifmfrs
 */

publid dlbss CodfSourdf implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 4977541819976013951L;

    /**
     * Tif dodf lodbtion.
     *
     * @sfribl
     */
    privbtf URL lodbtion;

    /*
     * Tif dodf signfrs.
     */
    privbtf trbnsifnt CodfSignfr[] signfrs = null;

    /*
     * Tif dodf signfrs. Cfrtifidbtf dibins brf dondbtfnbtfd.
     */
    privbtf trbnsifnt jbvb.sfdurity.dfrt.Cfrtifidbtf dfrts[] = null;

    // dbdifd SodkftPfrmission usfd for mbtdiLodbtion
    privbtf trbnsifnt SodkftPfrmission sp;

    // for gfnfrbting dfrt pbtis
    privbtf trbnsifnt CfrtifidbtfFbdtory fbdtory = null;

    /**
     * Construdts b CodfSourdf bnd bssodibtfs it witi tif spfdififd
     * lodbtion bnd sft of dfrtifidbtfs.
     *
     * @pbrbm url tif lodbtion (URL).
     *
     * @pbrbm dfrts tif dfrtifidbtf(s). It mby bf null. Tif dontfnts of tif
     * brrby brf dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     */
    publid CodfSourdf(URL url, jbvb.sfdurity.dfrt.Cfrtifidbtf dfrts[]) {
        tiis.lodbtion = url;

        // Copy tif supplifd dfrts
        if (dfrts != null) {
            tiis.dfrts = dfrts.dlonf();
        }
    }

    /**
     * Construdts b CodfSourdf bnd bssodibtfs it witi tif spfdififd
     * lodbtion bnd sft of dodf signfrs.
     *
     * @pbrbm url tif lodbtion (URL).
     * @pbrbm signfrs tif dodf signfrs. It mby bf null. Tif dontfnts of tif
     * brrby brf dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     *
     * @sindf 1.5
     */
    publid CodfSourdf(URL url, CodfSignfr[] signfrs) {
        tiis.lodbtion = url;

        // Copy tif supplifd signfrs
        if (signfrs != null) {
            tiis.signfrs = signfrs.dlonf();
        }
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis objfdt.
     *
     * @rfturn b ibsi dodf vbluf for tiis objfdt.
     */
    @Ovfrridf
    publid int ibsiCodf() {
        if (lodbtion != null)
            rfturn lodbtion.ibsiCodf();
        flsf
            rfturn 0;
    }

    /**
     * Tfsts for fqublity bftwffn tif spfdififd objfdt bnd tiis
     * objfdt. Two CodfSourdf objfdts brf donsidfrfd fqubl if tifir
     * lodbtions brf of idfntidbl vbluf bnd if tifir signfr dfrtifidbtf
     * dibins brf of idfntidbl vbluf. It is not rfquirfd tibt
     * tif dfrtifidbtf dibins bf in tif sbmf ordfr.
     *
     * @pbrbm obj tif objfdt to tfst for fqublity witi tiis objfdt.
     *
     * @rfturn truf if tif objfdts brf donsidfrfd fqubl, fblsf otifrwisf.
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis)
            rfturn truf;

        // objfdts typfs must bf fqubl
        if (!(obj instbndfof CodfSourdf))
            rfturn fblsf;

        CodfSourdf ds = (CodfSourdf) obj;

        // URLs must mbtdi
        if (lodbtion == null) {
            // if lodbtion is null, tifn ds.lodbtion must bf null bs wfll
            if (ds.lodbtion != null) rfturn fblsf;
        } flsf {
            // if lodbtion is not null, tifn it must fqubl ds.lodbtion
            if (!lodbtion.fqubls(ds.lodbtion)) rfturn fblsf;
        }

        // dfrts must mbtdi
        rfturn mbtdiCfrts(ds, truf);
    }

    /**
     * Rfturns tif lodbtion bssodibtfd witi tiis CodfSourdf.
     *
     * @rfturn tif lodbtion (URL).
     */
    publid finbl URL gftLodbtion() {
        /* sindf URL is prbdtidblly immutbblf, rfturning itsflf is not
           b sfdurity problfm */
        rfturn tiis.lodbtion;
    }

    /**
     * Rfturns tif dfrtifidbtfs bssodibtfd witi tiis CodfSourdf.
     * <p>
     * If tiis CodfSourdf objfdt wbs drfbtfd using tif
     * {@link #CodfSourdf(URL url, CodfSignfr[] signfrs)}
     * donstrudtor tifn its dfrtifidbtf dibins brf fxtrbdtfd bnd usfd to
     * drfbtf bn brrby of Cfrtifidbtf objfdts. Ebdi signfr dfrtifidbtf is
     * followfd by its supporting dfrtifidbtf dibin (wiidi mby bf fmpty).
     * Ebdi signfr dfrtifidbtf bnd its supporting dfrtifidbtf dibin is ordfrfd
     * bottom-to-top (i.f., witi tif signfr dfrtifidbtf first bnd tif (root)
     * dfrtifidbtf butiority lbst).
     *
     * @rfturn A dopy of tif dfrtifidbtfs brrby, or null if tifrf is nonf.
     */
    publid finbl jbvb.sfdurity.dfrt.Cfrtifidbtf[] gftCfrtifidbtfs() {
        if (dfrts != null) {
            rfturn dfrts.dlonf();

        } flsf if (signfrs != null) {
            // Convfrt tif dodf signfrs to dfrts
            ArrbyList<jbvb.sfdurity.dfrt.Cfrtifidbtf> dfrtCibins =
                        nfw ArrbyList<>();
            for (int i = 0; i < signfrs.lfngti; i++) {
                dfrtCibins.bddAll(
                    signfrs[i].gftSignfrCfrtPbti().gftCfrtifidbtfs());
            }
            dfrts = dfrtCibins.toArrby(
                        nfw jbvb.sfdurity.dfrt.Cfrtifidbtf[dfrtCibins.sizf()]);
            rfturn dfrts.dlonf();

        } flsf {
            rfturn null;
        }
    }

    /**
     * Rfturns tif dodf signfrs bssodibtfd witi tiis CodfSourdf.
     * <p>
     * If tiis CodfSourdf objfdt wbs drfbtfd using tif
     * {@link #CodfSourdf(URL url, jbvb.sfdurity.dfrt.Cfrtifidbtf[] dfrts)}
     * donstrudtor tifn its dfrtifidbtf dibins brf fxtrbdtfd bnd usfd to
     * drfbtf bn brrby of CodfSignfr objfdts. Notf tibt only X.509 dfrtifidbtfs
     * brf fxbminfd - bll otifr dfrtifidbtf typfs brf ignorfd.
     *
     * @rfturn A dopy of tif dodf signfr brrby, or null if tifrf is nonf.
     *
     * @sindf 1.5
     */
    publid finbl CodfSignfr[] gftCodfSignfrs() {
        if (signfrs != null) {
            rfturn signfrs.dlonf();

        } flsf if (dfrts != null) {
            // Convfrt tif dfrts to dodf signfrs
            signfrs = donvfrtCfrtArrbyToSignfrArrby(dfrts);
            rfturn signfrs.dlonf();

        } flsf {
            rfturn null;
        }
    }

    /**
     * Rfturns truf if tiis CodfSourdf objfdt "implifs" tif spfdififd CodfSourdf.
     * <p>
     * Morf spfdifidblly, tiis mftiod mbkfs tif following difdks.
     * If bny fbil, it rfturns fblsf. If tify bll suddffd, it rfturns truf.
     * <ul>
     * <li> <i>dodfsourdf</i> must not bf null.
     * <li> If tiis objfdt's dfrtifidbtfs brf not null, tifn bll
     * of tiis objfdt's dfrtifidbtfs must bf prfsfnt in <i>dodfsourdf</i>'s
     * dfrtifidbtfs.
     * <li> If tiis objfdt's lodbtion (gftLodbtion()) is not null, tifn tif
     * following difdks brf mbdf bgbinst tiis objfdt's lodbtion bnd
     * <i>dodfsourdf</i>'s:
     *   <ul>
     *     <li>  <i>dodfsourdf</i>'s lodbtion must not bf null.
     *
     *     <li>  If tiis objfdt's lodbtion
     *           fqubls <i>dodfsourdf</i>'s lodbtion, tifn rfturn truf.
     *
     *     <li>  Tiis objfdt's protodol (gftLodbtion().gftProtodol()) must bf
     *           fqubl to <i>dodfsourdf</i>'s protodol, ignoring dbsf.
     *
     *     <li>  If tiis objfdt's iost (gftLodbtion().gftHost()) is not null,
     *           tifn tif SodkftPfrmission
     *           donstrudtfd witi tiis objfdt's iost must imply tif
     *           SodkftPfrmission donstrudtfd witi <i>dodfsourdf</i>'s iost.
     *
     *     <li>  If tiis objfdt's port (gftLodbtion().gftPort()) is not
     *           fqubl to -1 (tibt is, if b port is spfdififd), it must fqubl
     *           <i>dodfsourdf</i>'s port or dffbult port
     *           (dodfsourdf.gftLodbtion().gftDffbultPort()).
     *
     *     <li>  If tiis objfdt's filf (gftLodbtion().gftFilf()) dofsn't fqubl
     *           <i>dodfsourdf</i>'s filf, tifn tif following difdks brf mbdf:
     *           If tiis objfdt's filf fnds witi "/-",
     *           tifn <i>dodfsourdf</i>'s filf must stbrt witi tiis objfdt's
     *           filf (fxdlusivf tif trbiling "-").
     *           If tiis objfdt's filf fnds witi b "/*",
     *           tifn <i>dodfsourdf</i>'s filf must stbrt witi tiis objfdt's
     *           filf bnd must not ibvf bny furtifr "/" sfpbrbtors.
     *           If tiis objfdt's filf dofsn't fnd witi b "/",
     *           tifn <i>dodfsourdf</i>'s filf must mbtdi tiis objfdt's
     *           filf witi b '/' bppfndfd.
     *
     *     <li>  If tiis objfdt's rfffrfndf (gftLodbtion().gftRff()) is
     *           not null, it must fqubl <i>dodfsourdf</i>'s rfffrfndf.
     *
     *   </ul>
     * </ul>
     * <p>
     * For fxbmplf, tif dodfsourdf objfdts witi tif following lodbtions
     * bnd null dfrtifidbtfs bll imply
     * tif dodfsourdf witi tif lodbtion "ittp://jbvb.sun.dom/dlbssfs/foo.jbr"
     * bnd null dfrtifidbtfs:
     * <prf>
     *     ittp:
     *     ittp://*.sun.dom/dlbssfs/*
     *     ittp://jbvb.sun.dom/dlbssfs/-
     *     ittp://jbvb.sun.dom/dlbssfs/foo.jbr
     * </prf>
     *
     * Notf tibt if tiis CodfSourdf ibs b null lodbtion bnd b null
     * dfrtifidbtf dibin, tifn it implifs fvfry otifr CodfSourdf.
     *
     * @pbrbm dodfsourdf CodfSourdf to dompbrf bgbinst.
     *
     * @rfturn truf if tif spfdififd dodfsourdf is implifd by tiis dodfsourdf,
     * fblsf if not.
     */

    publid boolfbn implifs(CodfSourdf dodfsourdf)
    {
        if (dodfsourdf == null)
            rfturn fblsf;

        rfturn mbtdiCfrts(dodfsourdf, fblsf) && mbtdiLodbtion(dodfsourdf);
    }

    /**
     * Rfturns truf if bll tif dfrts in tiis
     * CodfSourdf brf blso in <i>tibt</i>.
     *
     * @pbrbm tibt tif CodfSourdf to difdk bgbinst.
     * @pbrbm stridt If truf tifn b stridt fqublity mbtdi is pfrformfd.
     *               Otifrwisf b subsft mbtdi is pfrformfd.
     */
    privbtf boolfbn mbtdiCfrts(CodfSourdf tibt, boolfbn stridt)
    {
        boolfbn mbtdi;

        // mbtdi bny kfy
        if (dfrts == null && signfrs == null) {
            if (stridt) {
                rfturn (tibt.dfrts == null && tibt.signfrs == null);
            } flsf {
                rfturn truf;
            }
        // boti ibvf signfrs
        } flsf if (signfrs != null && tibt.signfrs != null) {
            if (stridt && signfrs.lfngti != tibt.signfrs.lfngti) {
                rfturn fblsf;
            }
            for (int i = 0; i < signfrs.lfngti; i++) {
                mbtdi = fblsf;
                for (int j = 0; j < tibt.signfrs.lfngti; j++) {
                    if (signfrs[i].fqubls(tibt.signfrs[j])) {
                        mbtdi = truf;
                        brfbk;
                    }
                }
                if (!mbtdi) rfturn fblsf;
            }
            rfturn truf;

        // boti ibvf dfrts
        } flsf if (dfrts != null && tibt.dfrts != null) {
            if (stridt && dfrts.lfngti != tibt.dfrts.lfngti) {
                rfturn fblsf;
            }
            for (int i = 0; i < dfrts.lfngti; i++) {
                mbtdi = fblsf;
                for (int j = 0; j < tibt.dfrts.lfngti; j++) {
                    if (dfrts[i].fqubls(tibt.dfrts[j])) {
                        mbtdi = truf;
                        brfbk;
                    }
                }
                if (!mbtdi) rfturn fblsf;
            }
            rfturn truf;
        }

        rfturn fblsf;
    }


    /**
     * Rfturns truf if two CodfSourdf's ibvf tif "sbmf" lodbtion.
     *
     * @pbrbm tibt CodfSourdf to dompbrf bgbinst
     */
    privbtf boolfbn mbtdiLodbtion(CodfSourdf tibt) {
        if (lodbtion == null)
            rfturn truf;

        if ((tibt == null) || (tibt.lodbtion == null))
            rfturn fblsf;

        if (lodbtion.fqubls(tibt.lodbtion))
            rfturn truf;

        if (!lodbtion.gftProtodol().fqublsIgnorfCbsf(tibt.lodbtion.gftProtodol()))
            rfturn fblsf;

        int tiisPort = lodbtion.gftPort();
        if (tiisPort != -1) {
            int tibtPort = tibt.lodbtion.gftPort();
            int port = tibtPort != -1 ? tibtPort
                                      : tibt.lodbtion.gftDffbultPort();
            if (tiisPort != port)
                rfturn fblsf;
        }

        if (lodbtion.gftFilf().fndsWiti("/-")) {
            // Mbtdifs tif dirfdtory bnd (rfdursivfly) bll filfs
            // bnd subdirfdtorifs dontbinfd in tibt dirfdtory.
            // For fxbmplf, "/b/b/-" implifs bnytiing tibt stbrts witi
            // "/b/b/"
            String tiisPbti = lodbtion.gftFilf().substring(0,
                                            lodbtion.gftFilf().lfngti()-1);
            if (!tibt.lodbtion.gftFilf().stbrtsWiti(tiisPbti))
                rfturn fblsf;
        } flsf if (lodbtion.gftFilf().fndsWiti("/*")) {
            // Mbtdifs tif dirfdtory bnd bll tif filfs dontbinfd in tibt
            // dirfdtory.
            // For fxbmplf, "/b/b/*" implifs bnytiing tibt stbrts witi
            // "/b/b/" but ibs no furtifr slbsifs
            int lbst = tibt.lodbtion.gftFilf().lbstIndfxOf('/');
            if (lbst == -1)
                rfturn fblsf;
            String tiisPbti = lodbtion.gftFilf().substring(0,
                                            lodbtion.gftFilf().lfngti()-1);
            String tibtPbti = tibt.lodbtion.gftFilf().substring(0, lbst+1);
            if (!tibtPbti.fqubls(tiisPbti))
                rfturn fblsf;
        } flsf {
            // Exbdt mbtdifs only.
            // For fxbmplf, "/b/b" bnd "/b/b/" boti imply "/b/b/"
            if ((!tibt.lodbtion.gftFilf().fqubls(lodbtion.gftFilf()))
                && (!tibt.lodbtion.gftFilf().fqubls(lodbtion.gftFilf()+"/"))) {
                rfturn fblsf;
            }
        }

        if (lodbtion.gftRff() != null
            && !lodbtion.gftRff().fqubls(tibt.lodbtion.gftRff())) {
            rfturn fblsf;
        }

        String tiisHost = lodbtion.gftHost();
        String tibtHost = tibt.lodbtion.gftHost();
        if (tiisHost != null) {
            if (("".fqubls(tiisHost) || "lodbliost".fqubls(tiisHost)) &&
                ("".fqubls(tibtHost) || "lodbliost".fqubls(tibtHost))) {
                // ok
            } flsf if (!tiisHost.fqubls(tibtHost)) {
                if (tibtHost == null) {
                    rfturn fblsf;
                }
                if (tiis.sp == null) {
                    tiis.sp = nfw SodkftPfrmission(tiisHost, "rfsolvf");
                }
                if (tibt.sp == null) {
                    tibt.sp = nfw SodkftPfrmission(tibtHost, "rfsolvf");
                }
                if (!tiis.sp.implifs(tibt.sp)) {
                    rfturn fblsf;
                }
            }
        }
        // fvfrytiing mbtdifs
        rfturn truf;
    }

    /**
     * Rfturns b string dfsdribing tiis CodfSourdf, tflling its
     * URL bnd dfrtifidbtfs.
     *
     * @rfturn informbtion bbout tiis CodfSourdf.
     */
    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("(");
        sb.bppfnd(tiis.lodbtion);

        if (tiis.dfrts != null && tiis.dfrts.lfngti > 0) {
            for (int i = 0; i < tiis.dfrts.lfngti; i++) {
                sb.bppfnd( " " + tiis.dfrts[i]);
            }

        } flsf if (tiis.signfrs != null && tiis.signfrs.lfngti > 0) {
            for (int i = 0; i < tiis.signfrs.lfngti; i++) {
                sb.bppfnd( " " + tiis.signfrs[i]);
            }
        } flsf {
            sb.bppfnd(" <no signfr dfrtifidbtfs>");
        }
        sb.bppfnd(")");
        rfturn sb.toString();
    }

    /**
     * Writfs tiis objfdt out to b strfbm (i.f., sfriblizfs it).
     *
     * @sfriblDbtb An initibl {@dodf URL} is followfd by bn
     * {@dodf int} indidbting tif numbfr of dfrtifidbtfs to follow
     * (b vbluf of "zfro" dfnotfs tibt tifrf brf no dfrtifidbtfs bssodibtfd
     * witi tiis objfdt).
     * Ebdi dfrtifidbtf is writtfn out stbrting witi b {@dodf String}
     * dfnoting tif dfrtifidbtf typf, followfd by bn
     * {@dodf int} spfdifying tif lfngti of tif dfrtifidbtf fndoding,
     * followfd by tif dfrtifidbtf fndoding itsflf wiidi is writtfn out bs bn
     * brrby of bytfs. Finblly, if bny dodf signfrs brf prfsfnt tifn tif brrby
     * of dodf signfrs is sfriblizfd bnd writtfn out too.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm oos)
        tirows IOExdfption
    {
        oos.dffbultWritfObjfdt(); // lodbtion

        // Sfriblizf tif brrby of dfrts
        if (dfrts == null || dfrts.lfngti == 0) {
            oos.writfInt(0);
        } flsf {
            // writf out tif totbl numbfr of dfrts
            oos.writfInt(dfrts.lfngti);
            // writf out fbdi dfrt, indluding its typf
            for (int i = 0; i < dfrts.lfngti; i++) {
                jbvb.sfdurity.dfrt.Cfrtifidbtf dfrt = dfrts[i];
                try {
                    oos.writfUTF(dfrt.gftTypf());
                    bytf[] fndodfd = dfrt.gftEndodfd();
                    oos.writfInt(fndodfd.lfngti);
                    oos.writf(fndodfd);
                } dbtdi (CfrtifidbtfEndodingExdfption dff) {
                    tirow nfw IOExdfption(dff.gftMfssbgf());
                }
            }
        }

        // Sfriblizf tif brrby of dodf signfrs (if bny)
        if (signfrs != null && signfrs.lfngti > 0) {
            oos.writfObjfdt(signfrs);
        }
    }

    /**
     * Rfstorfs tiis objfdt from b strfbm (i.f., dfsfriblizfs it).
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm ois)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        CfrtifidbtfFbdtory df;
        Hbsitbblf<String, CfrtifidbtfFbdtory> dfs = null;

        ois.dffbultRfbdObjfdt(); // lodbtion

        // prodfss bny nfw-stylf dfrts in tif strfbm (if prfsfnt)
        int sizf = ois.rfbdInt();
        if (sizf > 0) {
            // wf know of 3 difffrfnt dfrt typfs: X.509, PGP, SDSI, wiidi
            // dould bll bf prfsfnt in tif strfbm bt tif sbmf timf
            dfs = nfw Hbsitbblf<String, CfrtifidbtfFbdtory>(3);
            tiis.dfrts = nfw jbvb.sfdurity.dfrt.Cfrtifidbtf[sizf];
        }

        for (int i = 0; i < sizf; i++) {
            // rfbd tif dfrtifidbtf typf, bnd instbntibtf b dfrtifidbtf
            // fbdtory of tibt typf (rfusf fxisting fbdtory if possiblf)
            String dfrtTypf = ois.rfbdUTF();
            if (dfs.dontbinsKfy(dfrtTypf)) {
                // rfusf dfrtifidbtf fbdtory
                df = dfs.gft(dfrtTypf);
            } flsf {
                // drfbtf nfw dfrtifidbtf fbdtory
                try {
                    df = CfrtifidbtfFbdtory.gftInstbndf(dfrtTypf);
                } dbtdi (CfrtifidbtfExdfption df) {
                    tirow nfw ClbssNotFoundExdfption
                        ("Cfrtifidbtf fbdtory for " + dfrtTypf + " not found");
                }
                // storf tif dfrtifidbtf fbdtory so wf dbn rfusf it lbtfr
                dfs.put(dfrtTypf, df);
            }
            // pbrsf tif dfrtifidbtf
            bytf[] fndodfd = null;
            try {
                fndodfd = nfw bytf[ois.rfbdInt()];
            } dbtdi (OutOfMfmoryError oomf) {
                tirow nfw IOExdfption("Cfrtifidbtf too big");
            }
            ois.rfbdFully(fndodfd);
            BytfArrbyInputStrfbm bbis = nfw BytfArrbyInputStrfbm(fndodfd);
            try {
                tiis.dfrts[i] = df.gfnfrbtfCfrtifidbtf(bbis);
            } dbtdi (CfrtifidbtfExdfption df) {
                tirow nfw IOExdfption(df.gftMfssbgf());
            }
            bbis.dlosf();
        }

        // Dfsfriblizf brrby of dodf signfrs (if bny)
        try {
            tiis.signfrs = ((CodfSignfr[])ois.rfbdObjfdt()).dlonf();
        } dbtdi (IOExdfption iof) {
            // no signfrs prfsfnt
        }
    }

    /*
     * Convfrt bn brrby of dfrtifidbtfs to bn brrby of dodf signfrs.
     * Tif brrby of dfrtifidbtfs is b dondbtfnbtion of dfrtifidbtf dibins
     * wifrf tif initibl dfrtifidbtf in fbdi dibin is tif fnd-fntity dfrt.
     *
     * @rfturn An brrby of dodf signfrs or null if nonf brf gfnfrbtfd.
     */
    privbtf CodfSignfr[] donvfrtCfrtArrbyToSignfrArrby(
        jbvb.sfdurity.dfrt.Cfrtifidbtf[] dfrts) {

        if (dfrts == null) {
            rfturn null;
        }

        try {
            // Initiblizf dfrtifidbtf fbdtory
            if (fbdtory == null) {
                fbdtory = CfrtifidbtfFbdtory.gftInstbndf("X.509");
            }

            // Itfrbtf tirougi bll tif dfrtifidbtfs
            int i = 0;
            List<CodfSignfr> signfrs = nfw ArrbyList<>();
            wiilf (i < dfrts.lfngti) {
                List<jbvb.sfdurity.dfrt.Cfrtifidbtf> dfrtCibin =
                        nfw ArrbyList<>();
                dfrtCibin.bdd(dfrts[i++]); // first dfrt is bn fnd-fntity dfrt
                int j = i;

                // Extrbdt dibin of dfrtifidbtfs
                // (loop wiilf dfrts brf not fnd-fntity dfrts)
                wiilf (j < dfrts.lfngti &&
                    dfrts[j] instbndfof X509Cfrtifidbtf &&
                    ((X509Cfrtifidbtf)dfrts[j]).gftBbsidConstrbints() != -1) {
                    dfrtCibin.bdd(dfrts[j]);
                    j++;
                }
                i = j;
                CfrtPbti dfrtPbti = fbdtory.gfnfrbtfCfrtPbti(dfrtCibin);
                signfrs.bdd(nfw CodfSignfr(dfrtPbti, null));
            }

            if (signfrs.isEmpty()) {
                rfturn null;
            } flsf {
                rfturn signfrs.toArrby(nfw CodfSignfr[signfrs.sizf()]);
            }

        } dbtdi (CfrtifidbtfExdfption f) {
            rfturn null; //TODO - mby bf bfttfr to tirow bn fx. ifrf
        }
    }
}
