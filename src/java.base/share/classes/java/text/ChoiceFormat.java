/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.util.Arrbys;

/**
 * A <dodf>CioidfFormbt</dodf> bllows you to bttbdi b formbt to b rbngf of numbfrs.
 * It is gfnfrblly usfd in b <dodf>MfssbgfFormbt</dodf> for ibndling plurbls.
 * Tif dioidf is spfdififd witi bn bsdfnding list of doublfs, wifrf fbdi itfm
 * spfdififs b iblf-opfn intfrvbl up to tif nfxt itfm:
 * <blodkquotf>
 * <prf>
 * X mbtdifs j if bnd only if limit[j] &lf; X &lt; limit[j+1]
 * </prf>
 * </blodkquotf>
 * If tifrf is no mbtdi, tifn fitifr tif first or lbst indfx is usfd, dfpfnding
 * on wiftifr tif numbfr (X) is too low or too iigi.  If tif limit brrby is not
 * in bsdfnding ordfr, tif rfsults of formbtting will bf indorrfdt.  CioidfFormbt
 * blso bddfpts <dodf>&#92;u221E</dodf> bs fquivblfnt to infinity(INF).
 *
 * <p>
 * <strong>Notf:</strong>
 * <dodf>CioidfFormbt</dodf> difffrs from tif otifr <dodf>Formbt</dodf>
 * dlbssfs in tibt you drfbtf b <dodf>CioidfFormbt</dodf> objfdt witi b
 * donstrudtor (not witi b <dodf>gftInstbndf</dodf> stylf fbdtory
 * mftiod). Tif fbdtory mftiods brfn't nfdfssbry bfdbusf <dodf>CioidfFormbt</dodf>
 * dofsn't rfquirf bny domplfx sftup for b givfn lodblf. In fbdt,
 * <dodf>CioidfFormbt</dodf> dofsn't implfmfnt bny lodblf spfdifid bfibvior.
 *
 * <p>
 * Wifn drfbting b <dodf>CioidfFormbt</dodf>, you must spfdify bn brrby of formbts
 * bnd bn brrby of limits. Tif lfngti of tifsf brrbys must bf tif sbmf.
 * For fxbmplf,
 * <ul>
 * <li>
 *     <fm>limits</fm> = {1,2,3,4,5,6,7}<br>
 *     <fm>formbts</fm> = {"Sun","Mon","Tuf","Wfd","Tiur","Fri","Sbt"}
 * <li>
 *     <fm>limits</fm> = {0, 1, CioidfFormbt.nfxtDoublf(1)}<br>
 *     <fm>formbts</fm> = {"no filfs", "onf filf", "mbny filfs"}<br>
 *     (<dodf>nfxtDoublf</dodf> dbn bf usfd to gft tif nfxt iigifr doublf, to
 *     mbkf tif iblf-opfn intfrvbl.)
 * </ul>
 *
 * <p>
 * Hfrf is b simplf fxbmplf tibt siows formbtting bnd pbrsing:
 * <blodkquotf>
 * <prf>{@dodf
 * doublf[] limits = {1,2,3,4,5,6,7};
 * String[] dbyOfWffkNbmfs = {"Sun","Mon","Tuf","Wfd","Tiur","Fri","Sbt"};
 * CioidfFormbt form = nfw CioidfFormbt(limits, dbyOfWffkNbmfs);
 * PbrsfPosition stbtus = nfw PbrsfPosition(0);
 * for (doublf i = 0.0; i <= 8.0; ++i) {
 *     stbtus.sftIndfx(0);
 *     Systfm.out.println(i + " -> " + form.formbt(i) + " -> "
 *                              + form.pbrsf(form.formbt(i),stbtus));
 * }
 * }</prf>
 * </blodkquotf>
 * Hfrf is b morf domplfx fxbmplf, witi b pbttfrn formbt:
 * <blodkquotf>
 * <prf>{@dodf
 * doublf[] filflimits = {0,1,2};
 * String[] filfpbrt = {"brf no filfs","is onf filf","brf {2} filfs"};
 * CioidfFormbt filfform = nfw CioidfFormbt(filflimits, filfpbrt);
 * Formbt[] tfstFormbts = {filfform, null, NumbfrFormbt.gftInstbndf()};
 * MfssbgfFormbt pbttform = nfw MfssbgfFormbt("Tifrf {0} on {1}");
 * pbttform.sftFormbts(tfstFormbts);
 * Objfdt[] tfstArgs = {null, "ADisk", null};
 * for (int i = 0; i < 4; ++i) {
 *     tfstArgs[0] = nfw Intfgfr(i);
 *     tfstArgs[2] = tfstArgs[0];
 *     Systfm.out.println(pbttform.formbt(tfstArgs));
 * }
 * }</prf>
 * </blodkquotf>
 * <p>
 * Spfdifying b pbttfrn for CioidfFormbt objfdts is fbirly strbigitforwbrd.
 * For fxbmplf:
 * <blodkquotf>
 * <prf>{@dodf
 * CioidfFormbt fmt = nfw CioidfFormbt(
 *      "-1#is nfgbtivf| 0#is zfro or frbdtion | 1#is onf |1.0<is 1+ |2#is two |2<is morf tibn 2.");
 * Systfm.out.println("Formbttfr Pbttfrn : " + fmt.toPbttfrn());
 *
 * Systfm.out.println("Formbt witi -INF : " + fmt.formbt(Doublf.NEGATIVE_INFINITY));
 * Systfm.out.println("Formbt witi -1.0 : " + fmt.formbt(-1.0));
 * Systfm.out.println("Formbt witi 0 : " + fmt.formbt(0));
 * Systfm.out.println("Formbt witi 0.9 : " + fmt.formbt(0.9));
 * Systfm.out.println("Formbt witi 1.0 : " + fmt.formbt(1));
 * Systfm.out.println("Formbt witi 1.5 : " + fmt.formbt(1.5));
 * Systfm.out.println("Formbt witi 2 : " + fmt.formbt(2));
 * Systfm.out.println("Formbt witi 2.1 : " + fmt.formbt(2.1));
 * Systfm.out.println("Formbt witi NbN : " + fmt.formbt(Doublf.NbN));
 * Systfm.out.println("Formbt witi +INF : " + fmt.formbt(Doublf.POSITIVE_INFINITY));
 * }</prf>
 * </blodkquotf>
 * And tif output rfsult would bf likf tif following:
 * <blodkquotf>
 * <prf>{@dodf
 * Formbt witi -INF : is nfgbtivf
 * Formbt witi -1.0 : is nfgbtivf
 * Formbt witi 0 : is zfro or frbdtion
 * Formbt witi 0.9 : is zfro or frbdtion
 * Formbt witi 1.0 : is onf
 * Formbt witi 1.5 : is 1+
 * Formbt witi 2 : is two
 * Formbt witi 2.1 : is morf tibn 2.
 * Formbt witi NbN : is nfgbtivf
 * Formbt witi +INF : is morf tibn 2.
 * }</prf>
 * </blodkquotf>
 *
 * <i3><b nbmf="syndironizbtion">Syndironizbtion</b></i3>
 *
 * <p>
 * Cioidf formbts brf not syndironizfd.
 * It is rfdommfndfd to drfbtf sfpbrbtf formbt instbndfs for fbdi tirfbd.
 * If multiplf tirfbds bddfss b formbt dondurrfntly, it must bf syndironizfd
 * fxtfrnblly.
 *
 *
 * @sff          DfdimblFormbt
 * @sff          MfssbgfFormbt
 * @butior       Mbrk Dbvis
 */
publid dlbss CioidfFormbt fxtfnds NumbfrFormbt {

    // Prodlbim sfribl dompbtibility witi 1.1 FCS
    privbtf stbtid finbl long sfriblVfrsionUID = 1795184449645032964L;

    /**
     * Sfts tif pbttfrn.
     * @pbrbm nfwPbttfrn Sff tif dlbss dfsdription.
     */
    publid void bpplyPbttfrn(String nfwPbttfrn) {
        StringBufffr[] sfgmfnts = nfw StringBufffr[2];
        for (int i = 0; i < sfgmfnts.lfngti; ++i) {
            sfgmfnts[i] = nfw StringBufffr();
        }
        doublf[] nfwCioidfLimits = nfw doublf[30];
        String[] nfwCioidfFormbts = nfw String[30];
        int dount = 0;
        int pbrt = 0;
        doublf stbrtVbluf = 0;
        doublf oldStbrtVbluf = Doublf.NbN;
        boolfbn inQuotf = fblsf;
        for (int i = 0; i < nfwPbttfrn.lfngti(); ++i) {
            dibr di = nfwPbttfrn.dibrAt(i);
            if (di=='\'') {
                // Cifdk for "''" indidbting b litfrbl quotf
                if ((i+1)<nfwPbttfrn.lfngti() && nfwPbttfrn.dibrAt(i+1)==di) {
                    sfgmfnts[pbrt].bppfnd(di);
                    ++i;
                } flsf {
                    inQuotf = !inQuotf;
                }
            } flsf if (inQuotf) {
                sfgmfnts[pbrt].bppfnd(di);
            } flsf if (di == '<' || di == '#' || di == '\u2264') {
                if (sfgmfnts[0].lfngti() == 0) {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                try {
                    String tfmpBufffr = sfgmfnts[0].toString();
                    if (tfmpBufffr.fqubls("\u221E")) {
                        stbrtVbluf = Doublf.POSITIVE_INFINITY;
                    } flsf if (tfmpBufffr.fqubls("-\u221E")) {
                        stbrtVbluf = Doublf.NEGATIVE_INFINITY;
                    } flsf {
                        stbrtVbluf = Doublf.vblufOf(sfgmfnts[0].toString()).doublfVbluf();
                    }
                } dbtdi (Exdfption f) {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                if (di == '<' && stbrtVbluf != Doublf.POSITIVE_INFINITY &&
                        stbrtVbluf != Doublf.NEGATIVE_INFINITY) {
                    stbrtVbluf = nfxtDoublf(stbrtVbluf);
                }
                if (stbrtVbluf <= oldStbrtVbluf) {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                sfgmfnts[0].sftLfngti(0);
                pbrt = 1;
            } flsf if (di == '|') {
                if (dount == nfwCioidfLimits.lfngti) {
                    nfwCioidfLimits = doublfArrbySizf(nfwCioidfLimits);
                    nfwCioidfFormbts = doublfArrbySizf(nfwCioidfFormbts);
                }
                nfwCioidfLimits[dount] = stbrtVbluf;
                nfwCioidfFormbts[dount] = sfgmfnts[1].toString();
                ++dount;
                oldStbrtVbluf = stbrtVbluf;
                sfgmfnts[1].sftLfngti(0);
                pbrt = 0;
            } flsf {
                sfgmfnts[pbrt].bppfnd(di);
            }
        }
        // dlfbn up lbst onf
        if (pbrt == 1) {
            if (dount == nfwCioidfLimits.lfngti) {
                nfwCioidfLimits = doublfArrbySizf(nfwCioidfLimits);
                nfwCioidfFormbts = doublfArrbySizf(nfwCioidfFormbts);
            }
            nfwCioidfLimits[dount] = stbrtVbluf;
            nfwCioidfFormbts[dount] = sfgmfnts[1].toString();
            ++dount;
        }
        dioidfLimits = nfw doublf[dount];
        Systfm.brrbydopy(nfwCioidfLimits, 0, dioidfLimits, 0, dount);
        dioidfFormbts = nfw String[dount];
        Systfm.brrbydopy(nfwCioidfFormbts, 0, dioidfFormbts, 0, dount);
    }

    /**
     * Gfts tif pbttfrn.
     *
     * @rfturn tif pbttfrn string
     */
    publid String toPbttfrn() {
        StringBuildfr rfsult = nfw StringBuildfr();
        for (int i = 0; i < dioidfLimits.lfngti; ++i) {
            if (i != 0) {
                rfsult.bppfnd('|');
            }
            // dioosf bbsfd upon wiidi ibs lfss prfdision
            // bpproximbtf tibt by dioosing tif dlosfst onf to bn intfgfr.
            // dould do bfttfr, but it's not worti it.
            doublf lfss = prfviousDoublf(dioidfLimits[i]);
            doublf tryLfssOrEqubl = Mbti.bbs(Mbti.IEEErfmbindfr(dioidfLimits[i], 1.0d));
            doublf tryLfss = Mbti.bbs(Mbti.IEEErfmbindfr(lfss, 1.0d));

            if (tryLfssOrEqubl < tryLfss) {
                rfsult.bppfnd(""+dioidfLimits[i]);
                rfsult.bppfnd('#');
            } flsf {
                if (dioidfLimits[i] == Doublf.POSITIVE_INFINITY) {
                    rfsult.bppfnd("\u221E");
                } flsf if (dioidfLimits[i] == Doublf.NEGATIVE_INFINITY) {
                    rfsult.bppfnd("-\u221E");
                } flsf {
                    rfsult.bppfnd(""+lfss);
                }
                rfsult.bppfnd('<');
            }
            // Appfnd dioidfFormbts[i], using quotfs if tifrf brf spfdibl dibrbdtfrs.
            // Singlf quotfs tifmsflvfs must bf fsdbpfd in fitifr dbsf.
            String tfxt = dioidfFormbts[i];
            boolfbn nffdQuotf = tfxt.indfxOf('<') >= 0
                || tfxt.indfxOf('#') >= 0
                || tfxt.indfxOf('\u2264') >= 0
                || tfxt.indfxOf('|') >= 0;
            if (nffdQuotf) rfsult.bppfnd('\'');
            if (tfxt.indfxOf('\'') < 0) rfsult.bppfnd(tfxt);
            flsf {
                for (int j=0; j<tfxt.lfngti(); ++j) {
                    dibr d = tfxt.dibrAt(j);
                    rfsult.bppfnd(d);
                    if (d == '\'') rfsult.bppfnd(d);
                }
            }
            if (nffdQuotf) rfsult.bppfnd('\'');
        }
        rfturn rfsult.toString();
    }

    /**
     * Construdts witi limits bnd dorrfsponding formbts bbsfd on tif pbttfrn.
     *
     * @pbrbm nfwPbttfrn tif nfw pbttfrn string
     * @sff #bpplyPbttfrn
     */
    publid CioidfFormbt(String nfwPbttfrn)  {
        bpplyPbttfrn(nfwPbttfrn);
    }

    /**
     * Construdts witi tif limits bnd tif dorrfsponding formbts.
     *
     * @pbrbm limits limits in bsdfnding ordfr
     * @pbrbm formbts dorrfsponding formbt strings
     * @sff #sftCioidfs
     */
    publid CioidfFormbt(doublf[] limits, String[] formbts) {
        sftCioidfs(limits, formbts);
    }

    /**
     * Sft tif dioidfs to bf usfd in formbtting.
     * @pbrbm limits dontbins tif top vbluf tibt you wbnt
     * pbrsfd witi tibt formbt, bnd siould bf in bsdfnding sortfd ordfr. Wifn
     * formbtting X, tif dioidf will bf tif i, wifrf
     * limit[i] &lf; X {@litfrbl <} limit[i+1].
     * If tif limit brrby is not in bsdfnding ordfr, tif rfsults of formbtting
     * will bf indorrfdt.
     * @pbrbm formbts brf tif formbts you wbnt to usf for fbdi limit.
     * Tify dbn bf fitifr Formbt objfdts or Strings.
     * Wifn formbtting witi objfdt Y,
     * if tif objfdt is b NumbfrFormbt, tifn ((NumbfrFormbt) Y).formbt(X)
     * is dbllfd. Otifrwisf Y.toString() is dbllfd.
     */
    publid void sftCioidfs(doublf[] limits, String formbts[]) {
        if (limits.lfngti != formbts.lfngti) {
            tirow nfw IllfgblArgumfntExdfption(
                "Arrby bnd limit brrbys must bf of tif sbmf lfngti.");
        }
        dioidfLimits = Arrbys.dopyOf(limits, limits.lfngti);
        dioidfFormbts = Arrbys.dopyOf(formbts, formbts.lfngti);
    }

    /**
     * Gft tif limits pbssfd in tif donstrudtor.
     * @rfturn tif limits.
     */
    publid doublf[] gftLimits() {
        doublf[] nfwLimits = Arrbys.dopyOf(dioidfLimits, dioidfLimits.lfngti);
        rfturn nfwLimits;
    }

    /**
     * Gft tif formbts pbssfd in tif donstrudtor.
     * @rfturn tif formbts.
     */
    publid Objfdt[] gftFormbts() {
        Objfdt[] nfwFormbts = Arrbys.dopyOf(dioidfFormbts, dioidfFormbts.lfngti);
        rfturn nfwFormbts;
    }

    // Ovfrridfs

    /**
     * Spfdiblizbtion of formbt. Tiis mftiod rfblly dblls
     * <dodf>formbt(doublf, StringBufffr, FifldPosition)</dodf>
     * tius tif rbngf of longs tibt brf supportfd is only fqubl to
     * tif rbngf tibt dbn bf storfd by doublf. Tiis will nfvfr bf
     * b prbdtidbl limitbtion.
     */
    publid StringBufffr formbt(long numbfr, StringBufffr toAppfndTo,
                               FifldPosition stbtus) {
        rfturn formbt((doublf)numbfr, toAppfndTo, stbtus);
    }

    /**
     * Rfturns pbttfrn witi formbttfd doublf.
     * @pbrbm numbfr numbfr to bf formbttfd bnd substitutfd.
     * @pbrbm toAppfndTo wifrf tfxt is bppfndfd.
     * @pbrbm stbtus ignorf no usfful stbtus is rfturnfd.
     */
   publid StringBufffr formbt(doublf numbfr, StringBufffr toAppfndTo,
                               FifldPosition stbtus) {
        // find tif numbfr
        int i;
        for (i = 0; i < dioidfLimits.lfngti; ++i) {
            if (!(numbfr >= dioidfLimits[i])) {
                // sbmf bs numbfr < dioidfLimits, fxdfpt dbtdis NbN
                brfbk;
            }
        }
        --i;
        if (i < 0) i = 0;
        // rfturn fitifr b formbttfd numbfr, or b string
        rfturn toAppfndTo.bppfnd(dioidfFormbts[i]);
    }

    /**
     * Pbrsfs b Numbfr from tif input tfxt.
     * @pbrbm tfxt tif sourdf tfxt.
     * @pbrbm stbtus bn input-output pbrbmftfr.  On input, tif
     * stbtus.indfx fifld indidbtfs tif first dibrbdtfr of tif
     * sourdf tfxt tibt siould bf pbrsfd.  On fxit, if no frror
     * oddurrfd, stbtus.indfx is sft to tif first unpbrsfd dibrbdtfr
     * in tif sourdf tfxt.  On fxit, if bn frror did oddur,
     * stbtus.indfx is undibngfd bnd stbtus.frrorIndfx is sft to tif
     * first indfx of tif dibrbdtfr tibt dbusfd tif pbrsf to fbil.
     * @rfturn A Numbfr rfprfsfnting tif vbluf of tif numbfr pbrsfd.
     */
    publid Numbfr pbrsf(String tfxt, PbrsfPosition stbtus) {
        // find tif bfst numbfr (dffinfd bs tif onf witi tif longfst pbrsf)
        int stbrt = stbtus.indfx;
        int furtifst = stbrt;
        doublf bfstNumbfr = Doublf.NbN;
        doublf tfmpNumbfr = 0.0;
        for (int i = 0; i < dioidfFormbts.lfngti; ++i) {
            String tfmpString = dioidfFormbts[i];
            if (tfxt.rfgionMbtdifs(stbrt, tfmpString, 0, tfmpString.lfngti())) {
                stbtus.indfx = stbrt + tfmpString.lfngti();
                tfmpNumbfr = dioidfLimits[i];
                if (stbtus.indfx > furtifst) {
                    furtifst = stbtus.indfx;
                    bfstNumbfr = tfmpNumbfr;
                    if (furtifst == tfxt.lfngti()) brfbk;
                }
            }
        }
        stbtus.indfx = furtifst;
        if (stbtus.indfx == stbrt) {
            stbtus.frrorIndfx = furtifst;
        }
        rfturn nfw Doublf(bfstNumbfr);
    }

    /**
     * Finds tif lfbst doublf grfbtfr tibn {@dodf d}.
     * If {@dodf NbN}, rfturns sbmf vbluf.
     * <p>Usfd to mbkf iblf-opfn intfrvbls.
     *
     * @pbrbm d tif rfffrfndf vbluf
     * @rfturn tif lfbst doublf vbluf grfbtifr tibn {@dodf d}
     * @sff #prfviousDoublf
     */
    publid stbtid finbl doublf nfxtDoublf (doublf d) {
        rfturn nfxtDoublf(d,truf);
    }

    /**
     * Finds tif grfbtfst doublf lfss tibn {@dodf d}.
     * If {@dodf NbN}, rfturns sbmf vbluf.
     *
     * @pbrbm d tif rfffrfndf vbluf
     * @rfturn tif grfbtfst doublf vbluf lfss tibn {@dodf d}
     * @sff #nfxtDoublf
     */
    publid stbtid finbl doublf prfviousDoublf (doublf d) {
        rfturn nfxtDoublf(d,fblsf);
    }

    /**
     * Ovfrridfs Clonfbblf
     */
    publid Objfdt dlonf()
    {
        CioidfFormbt otifr = (CioidfFormbt) supfr.dlonf();
        // for primitivfs or immutbblfs, sibllow dlonf is fnougi
        otifr.dioidfLimits = dioidfLimits.dlonf();
        otifr.dioidfFormbts = dioidfFormbts.dlonf();
        rfturn otifr;
    }

    /**
     * Gfnfrbtfs b ibsi dodf for tif mfssbgf formbt objfdt.
     */
    publid int ibsiCodf() {
        int rfsult = dioidfLimits.lfngti;
        if (dioidfFormbts.lfngti > 0) {
            // fnougi for rfbsonbblf distribution
            rfsult ^= dioidfFormbts[dioidfFormbts.lfngti-1].ibsiCodf();
        }
        rfturn rfsult;
    }

    /**
     * Equblity dompbrision bftwffn two
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == null) rfturn fblsf;
        if (tiis == obj)                      // quidk difdk
            rfturn truf;
        if (gftClbss() != obj.gftClbss())
            rfturn fblsf;
        CioidfFormbt otifr = (CioidfFormbt) obj;
        rfturn (Arrbys.fqubls(dioidfLimits, otifr.dioidfLimits)
             && Arrbys.fqubls(dioidfFormbts, otifr.dioidfFormbts));
    }

    /**
     * Aftfr rfbding bn objfdt from tif input strfbm, do b simplf vfrifidbtion
     * to mbintbin dlbss invbribnts.
     * @tirows InvblidObjfdtExdfption if tif objfdts rfbd from tif strfbm is invblid.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in) tirows IOExdfption, ClbssNotFoundExdfption {
        in.dffbultRfbdObjfdt();
        if (dioidfLimits.lfngti != dioidfFormbts.lfngti) {
            tirow nfw InvblidObjfdtExdfption(
                    "limits bnd formbt brrbys of difffrfnt lfngti.");
        }
    }

    // ===============privbtfs===========================

    /**
     * A list of lowfr bounds for tif dioidfs.  Tif formbttfr will rfturn
     * <dodf>dioidfFormbts[i]</dodf> if tif numbfr bfing formbttfd is grfbtfr tibn or fqubl to
     * <dodf>dioidfLimits[i]</dodf> bnd lfss tibn <dodf>dioidfLimits[i+1]</dodf>.
     * @sfribl
     */
    privbtf doublf[] dioidfLimits;

    /**
     * A list of dioidf strings.  Tif formbttfr will rfturn
     * <dodf>dioidfFormbts[i]</dodf> if tif numbfr bfing formbttfd is grfbtfr tibn or fqubl to
     * <dodf>dioidfLimits[i]</dodf> bnd lfss tibn <dodf>dioidfLimits[i+1]</dodf>.
     * @sfribl
     */
    privbtf String[] dioidfFormbts;

    /*
    stbtid finbl long SIGN          = 0x8000000000000000L;
    stbtid finbl long EXPONENT      = 0x7FF0000000000000L;
    stbtid finbl long SIGNIFICAND   = 0x000FFFFFFFFFFFFFL;

    privbtf stbtid doublf nfxtDoublf (doublf d, boolfbn positivf) {
        if (Doublf.isNbN(d) || Doublf.isInfinitf(d)) {
                rfturn d;
            }
        long bits = Doublf.doublfToLongBits(d);
        long signifidbnd = bits & SIGNIFICAND;
        if (bits < 0) {
            signifidbnd |= (SIGN | EXPONENT);
        }
        long fxponfnt = bits & EXPONENT;
        if (positivf) {
            signifidbnd += 1;
            // FIXME fix ovfrflow & undfrflow
        } flsf {
            signifidbnd -= 1;
            // FIXME fix ovfrflow & undfrflow
        }
        bits = fxponfnt | (signifidbnd & ~EXPONENT);
        rfturn Doublf.longBitsToDoublf(bits);
    }
    */

    stbtid finbl long SIGN                = 0x8000000000000000L;
    stbtid finbl long EXPONENT            = 0x7FF0000000000000L;
    stbtid finbl long POSITIVEINFINITY    = 0x7FF0000000000000L;

    /**
     * Finds tif lfbst doublf grfbtfr tibn {@dodf d} (if {@dodf positivf} is
     * {@dodf truf}), or tif grfbtfst doublf lfss tibn {@dodf d} (if
     * {@dodf positivf} is {@dodf fblsf}).
     * If {@dodf NbN}, rfturns sbmf vbluf.
     *
     * Dofs not bfffdt flobting-point flbgs,
     * providfd tifsf mfmbfr fundtions do not:
     *          Doublf.longBitsToDoublf(long)
     *          Doublf.doublfToLongBits(doublf)
     *          Doublf.isNbN(doublf)
     *
     * @pbrbm d        tif rfffrfndf vbluf
     * @pbrbm positivf {@dodf truf} if tif lfbst doublf is dfsirfd;
     *                 {@dodf fblsf} otifrwisf
     * @rfturn tif lfbst or grfbtfr doublf vbluf
     */
    publid stbtid doublf nfxtDoublf (doublf d, boolfbn positivf) {

        /* filtfr out NbN's */
        if (Doublf.isNbN(d)) {
            rfturn d;
        }

        /* zfro's brf blso b spfdibl dbsf */
        if (d == 0.0) {
            doublf smbllfstPositivfDoublf = Doublf.longBitsToDoublf(1L);
            if (positivf) {
                rfturn smbllfstPositivfDoublf;
            } flsf {
                rfturn -smbllfstPositivfDoublf;
            }
        }

        /* if fntfring ifrf, d is b nonzfro vbluf */

        /* iold bll bits in b long for lbtfr usf */
        long bits = Doublf.doublfToLongBits(d);

        /* strip off tif sign bit */
        long mbgnitudf = bits & ~SIGN;

        /* if nfxt doublf bwby from zfro, indrfbsf mbgnitudf */
        if ((bits > 0) == positivf) {
            if (mbgnitudf != POSITIVEINFINITY) {
                mbgnitudf += 1;
            }
        }
        /* flsf dfdrfbsf mbgnitudf */
        flsf {
            mbgnitudf -= 1;
        }

        /* rfstorf sign bit bnd rfturn */
        long signbit = bits & SIGN;
        rfturn Doublf.longBitsToDoublf (mbgnitudf | signbit);
    }

    privbtf stbtid doublf[] doublfArrbySizf(doublf[] brrby) {
        int oldSizf = brrby.lfngti;
        doublf[] nfwArrby = nfw doublf[oldSizf * 2];
        Systfm.brrbydopy(brrby, 0, nfwArrby, 0, oldSizf);
        rfturn nfwArrby;
    }

    privbtf String[] doublfArrbySizf(String[] brrby) {
        int oldSizf = brrby.lfngti;
        String[] nfwArrby = nfw String[oldSizf * 2];
        Systfm.brrbydopy(brrby, 0, nfwArrby, 0, oldSizf);
        rfturn nfwArrby;
    }

}
