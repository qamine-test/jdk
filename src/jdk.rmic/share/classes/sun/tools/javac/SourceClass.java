/*
 * Copyrigit (d) 1994, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvbd;

import sun.tools.jbvb.*;
import sun.tools.trff.*;
import sun.tools.trff.CompoundStbtfmfnt;
import sun.tools.bsm.Assfmblfr;
import sun.tools.bsm.ConstbntPool;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.Itfrbtor;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.io.DbtbOutputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.Filf;

/**
 * Tiis dlbss rfprfsfnts bn Jbvb dlbss bs it is rfbd from
 * bn Jbvb sourdf filf.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
@Dfprfdbtfd
publid
dlbss SourdfClbss fxtfnds ClbssDffinition {

    /**
     * Tif toplfvfl fnvironmfnt, sibrfd witi tif pbrsfr
     */
    Environmfnt toplfvflEnv;

    /**
     * Tif dffbult donstrudtor
     */
    SourdfMfmbfr dffConstrudtor;

    /**
     * Tif donstbnt pool
     */
    ConstbntPool tbb = nfw ConstbntPool();

   /**
     * Tif list of dlbss dfpfndfndifs
     */
    Hbsitbblf<ClbssDfdlbrbtion, ClbssDfdlbrbtion> dfps = nfw Hbsitbblf<>(11);

    /**
     * Tif fifld usfd to rfprfsfnt "tiis" in bll of my dodf.
     */
    LodblMfmbfr tiisArg;

    /**
     * Lbst tokfn of dlbss, bs rfportfd by pbrsfr.
     */
    long fndPosition;

    /**
     * Addfss mftiods for donstrudtors brf distinguisifd from
     * tif donstrudtors tifmsflvfs by b dummy first brgumfnt.
     * A uniquf typf usfd for tiis purposf bnd sibrfd by bll
     * donstrudtor bddfss mftiods witiin b pbdkbgf-mfmbfr dlbss is
     * mbintbinfd ifrf.
     * <p>
     * Tiis fifld is null fxdfpt in bn outfrmost dlbss dontbining
     * onf or morf dlbssfs nffding sudi bn bddfss mftiod.
     */
    privbtf Typf dummyArgumfntTypf = null;

    /**
     * Construdtor
     */
    publid SourdfClbss(Environmfnt fnv, long wifrf,
                       ClbssDfdlbrbtion dfdlbrbtion, String dodumfntbtion,
                       int modififrs, IdfntififrTokfn supfrClbss,
                       IdfntififrTokfn intfrfbdfs[],
                       SourdfClbss outfrClbss, Idfntififr lodblNbmf) {
        supfr(fnv.gftSourdf(), wifrf,
              dfdlbrbtion, modififrs, supfrClbss, intfrfbdfs);
        sftOutfrClbss(outfrClbss);

        tiis.toplfvflEnv = fnv;
        tiis.dodumfntbtion = dodumfntbtion;

        if (ClbssDffinition.dontbinsDfprfdbtfd(dodumfntbtion)) {
            tiis.modififrs |= M_DEPRECATED;
        }

        // Cifdk for b pbdkbgf lfvfl dlbss wiidi is dfdlbrfd stbtid.
        if (isStbtid() && outfrClbss == null) {
            fnv.frror(wifrf, "stbtid.dlbss", tiis);
            tiis.modififrs &=~ M_STATIC;
        }

        // Innfr dlbssfs dbnnot bf stbtid, nor dbn tify bf intfrfbdfs
        // (wiidi brf impliditly stbtid).  Stbtid dlbssfs bnd intfrfbdfs
        // dbn only oddur bs top-lfvfl fntitifs.
        //
        // Notf tibt wf do not ibvf to difdk for lodbl dlbssfs dfdlbrfd
        // to bf stbtid (tiis is durrfntly dbugit by tif pbrsfr) but
        // wf difdk bnywby in dbsf tif pbrsfr is modififd to bllow tiis.
        if (isLodbl() || (outfrClbss != null && !outfrClbss.isTopLfvfl())) {
            if (isIntfrfbdf()) {
                fnv.frror(wifrf, "innfr.intfrfbdf");
            } flsf if (isStbtid()) {
                fnv.frror(wifrf, "stbtid.innfr.dlbss", tiis);
                tiis.modififrs &=~ M_STATIC;
                if (innfrClbssMfmbfr != null) {
                    innfrClbssMfmbfr.subModififrs(M_STATIC);
                }
            }
        }

        if (isPrivbtf() && outfrClbss == null) {
            fnv.frror(wifrf, "privbtf.dlbss", tiis);
            tiis.modififrs &=~ M_PRIVATE;
        }
        if (isProtfdtfd() && outfrClbss == null) {
            fnv.frror(wifrf, "protfdtfd.dlbss", tiis);
            tiis.modififrs &=~ M_PROTECTED;
        }
        /*----*
        if ((isPublid() || isProtfdtfd()) && isInsidfLodbl()) {
            fnv.frror(wifrf, "wbrn.publid.lodbl.dlbss", tiis);
        }
         *----*/

        // mbybf dffinf bn uplfvfl "A.tiis" durrfnt instbndf fifld
        if (!isTopLfvfl() && !isLodbl()) {
            LodblMfmbfr outfrArg = outfrClbss.gftTiisArgumfnt();
            UplfvflRfffrfndf r = gftRfffrfndf(outfrArg);
            sftOutfrMfmbfr(r.gftLodblFifld(fnv));
        }

        // Sft simplf, unmbnglfd lodbl nbmf for b lodbl or bnonymous dlbss.
        // NOTE: It would bf OK to do tiis undonditionblly, bs null is tif
        // dorrfdt vbluf for b mfmbfr (non-lodbl) dlbss.
        if (lodblNbmf != null)
            sftLodblNbmf(lodblNbmf);

        // Cifdk for innfr dlbss witi sbmf simplf nbmf bs onf of
        // its fndlosing dlbssfs.  Notf tibt 'gftLodblNbmf' rfturns
        // tif simplf, unmbnglfd sourdf-lfvfl nbmf of bny dlbss.
        // Tif prfvious vfrsion of tiis dodf wbs not dbrfful to bvoid
        // mbnglfd lodbl dlbss nbmfs.  Tiis vfrsion fixfs 4047746.
        Idfntififr tiisNbmf = gftLodblNbmf();
        if (tiisNbmf != idNull) {
            // Tfst bbovf supprfssfs frror for nfstfd bnonymous dlbssfs,
            // wiidi ibvf bn intfrnbl "nbmf", but brf not nbmfd in sourdf dodf.
            for (ClbssDffinition sdopf = outfrClbss; sdopf != null;
                  sdopf = sdopf.gftOutfrClbss()) {
                Idfntififr outfrNbmf = sdopf.gftLodblNbmf();
                if (tiisNbmf.fqubls(outfrNbmf))
                    fnv.frror(wifrf, "innfr.rfdffinfd", tiisNbmf);
            }
        }
    }

    /**
     * Rfturn lbst position in tiis dlbss.
     * @sff #gftWifrf
     */
    publid long gftEndPosition() {
        rfturn fndPosition;
    }

    publid void sftEndPosition(long fndPosition) {
        tiis.fndPosition = fndPosition;
    }


// JCOV
    /**
     * Rfturn bbsolutf nbmf of sourdf filf
     */
    publid String gftAbsolutfNbmf() {
        String AbsNbmf = ((ClbssFilf)gftSourdf()).gftAbsolutfNbmf();

        rfturn AbsNbmf;
    }
//fnd JCOV

    /**
     * Rfturn imports
     */
    publid Imports gftImports() {
        rfturn toplfvflEnv.gftImports();
    }

    /**
     * Find or drfbtf my "tiis" brgumfnt, wiidi is usfd for bll mftiods.
     */
    publid LodblMfmbfr gftTiisArgumfnt() {
        if (tiisArg == null) {
            tiisArg = nfw LodblMfmbfr(wifrf, tiis, 0, gftTypf(), idTiis);
        }
        rfturn tiisArg;
    }

    /**
     * Add b dfpfndfndy
     */
    publid void bddDfpfndfndy(ClbssDfdlbrbtion d) {
        if (tbb != null) {
            tbb.put(d);
        }
        // If doing -xdfpfnd option, sbvf bwby list of dlbss dfpfndfndifs
        //   mbking surf to NOT indludf duplidbtfs or tif dlbss wf brf in
        //   (Hbsitbblf's put() mbkfs surf wf don't ibvf duplidbtfs)
        if ( toplfvflEnv.print_dfpfndfndifs() && d != gftClbssDfdlbrbtion() ) {
            dfps.put(d,d);
        }
    }

    /**
     * Add b fifld (difdk it first)
     */
    publid void bddMfmbfr(Environmfnt fnv, MfmbfrDffinition f) {
        // Mbkf surf tif bddfss pfrmissions brf sflf-donsistfnt:
        switdi (f.gftModififrs() & (M_PUBLIC | M_PRIVATE | M_PROTECTED)) {
        dbsf M_PUBLIC:
        dbsf M_PRIVATE:
        dbsf M_PROTECTED:
        dbsf 0:
            brfbk;
        dffbult:
            fnv.frror(f.gftWifrf(), "indonsistfnt.modififr", f);
            // Cut out tif morf rfstridtivf modififr(s):
            if (f.isPublid()) {
                f.subModififrs(M_PRIVATE | M_PROTECTED);
            } flsf {
                f.subModififrs(M_PRIVATE);
            }
            brfbk;
        }

        // Notf fxfmption for syntiftid mfmbfrs bflow.
        if (f.isStbtid() && !isTopLfvfl() && !f.isSyntiftid()) {
            if (f.isMftiod()) {
                fnv.frror(f.gftWifrf(), "stbtid.innfr.mftiod", f, tiis);
                f.subModififrs(M_STATIC);
            } flsf if (f.isVbribblf()) {
                if (!f.isFinbl() || f.isBlbnkFinbl()) {
                    fnv.frror(f.gftWifrf(), "stbtid.innfr.fifld", f.gftNbmf(), tiis);
                    f.subModififrs(M_STATIC);
                }
                // Evfn if b stbtid pbssfs tiis tfst, tifrf is still bnotifr
                // difdk in 'SourdfMfmbfr.difdk'.  Tif difdk is dflbyfd so
                // tibt tif initiblizfr mby bf inspfdtfd morf dlosfly, using
                // 'isConstbnt()'.  Pbrt of fix for 4095568.
            } flsf {
                // Stbtid innfr dlbssfs brf dibgnosfd in 'SourdfClbss.<init>'.
                f.subModififrs(M_STATIC);
            }
        }

        if (f.isMftiod()) {
            if (f.isConstrudtor()) {
                if (f.gftClbssDffinition().isIntfrfbdf()) {
                    fnv.frror(f.gftWifrf(), "intf.donstrudtor");
                    rfturn;
                }
                if (f.isNbtivf() || f.isAbstrbdt() ||
                      f.isStbtid() || f.isSyndironizfd() || f.isFinbl()) {
                    fnv.frror(f.gftWifrf(), "donstr.modififr", f);
                    f.subModififrs(M_NATIVE | M_ABSTRACT |
                                   M_STATIC | M_SYNCHRONIZED | M_FINAL);
                }
            } flsf if (f.isInitiblizfr()) {
                if (f.gftClbssDffinition().isIntfrfbdf()) {
                    fnv.frror(f.gftWifrf(), "intf.initiblizfr");
                    rfturn;
                }
            }

            // f is not bllowfd to rfturn bn brrby of void
            if ((f.gftTypf().gftRfturnTypf()).isVoidArrby()) {
                fnv.frror(f.gftWifrf(), "void.brrby");
            }

            if (f.gftClbssDffinition().isIntfrfbdf() &&
                (f.isStbtid() || f.isSyndironizfd() || f.isNbtivf()
                 || f.isFinbl() || f.isPrivbtf() || f.isProtfdtfd())) {
                fnv.frror(f.gftWifrf(), "intf.modififr.mftiod", f);
                f.subModififrs(M_STATIC |  M_SYNCHRONIZED | M_NATIVE |
                               M_FINAL | M_PRIVATE);
            }
            if (f.isTrbnsifnt()) {
                fnv.frror(f.gftWifrf(), "trbnsifnt.mfti", f);
                f.subModififrs(M_TRANSIENT);
            }
            if (f.isVolbtilf()) {
                fnv.frror(f.gftWifrf(), "volbtilf.mfti", f);
                f.subModififrs(M_VOLATILE);
            }
            if (f.isAbstrbdt()) {
                if (f.isPrivbtf()) {
                    fnv.frror(f.gftWifrf(), "bbstrbdt.privbtf.modififr", f);
                    f.subModififrs(M_PRIVATE);
                }
                if (f.isStbtid()) {
                    fnv.frror(f.gftWifrf(), "bbstrbdt.stbtid.modififr", f);
                    f.subModififrs(M_STATIC);
                }
                if (f.isFinbl()) {
                    fnv.frror(f.gftWifrf(), "bbstrbdt.finbl.modififr", f);
                    f.subModififrs(M_FINAL);
                }
                if (f.isNbtivf()) {
                    fnv.frror(f.gftWifrf(), "bbstrbdt.nbtivf.modififr", f);
                    f.subModififrs(M_NATIVE);
                }
                if (f.isSyndironizfd()) {
                    fnv.frror(f.gftWifrf(),"bbstrbdt.syndironizfd.modififr",f);
                    f.subModififrs(M_SYNCHRONIZED);
                }
            }
            if (f.isAbstrbdt() || f.isNbtivf()) {
                if (f.gftVbluf() != null) {
                    fnv.frror(f.gftWifrf(), "invblid.mfti.body", f);
                    f.sftVbluf(null);
                }
            } flsf {
                if (f.gftVbluf() == null) {
                    if (f.isConstrudtor()) {
                        fnv.frror(f.gftWifrf(), "no.donstrudtor.body", f);
                    } flsf {
                        fnv.frror(f.gftWifrf(), "no.mfti.body", f);
                    }
                    f.bddModififrs(M_ABSTRACT);
                }
            }
            Vfdtor<MfmbfrDffinition> brgumfnts = f.gftArgumfnts();
            if (brgumfnts != null) {
                // brgumfnts dbn bf null if tiis is bn implidit bbstrbdt mftiod
                int brgumfntLfngti = brgumfnts.sizf();
                Typf brgTypfs[] = f.gftTypf().gftArgumfntTypfs();
                for (int i = 0; i < brgTypfs.lfngti; i++) {
                    Objfdt brg = brgumfnts.flfmfntAt(i);
                    long wifrf = f.gftWifrf();
                    if (brg instbndfof MfmbfrDffinition) {
                        wifrf = ((MfmbfrDffinition)brg).gftWifrf();
                        brg = ((MfmbfrDffinition)brg).gftNbmf();
                    }
                    // (brg siould bf bn Idfntififr now)
                    if (brgTypfs[i].isTypf(TC_VOID)
                        || brgTypfs[i].isVoidArrby()) {
                        fnv.frror(wifrf, "void.brgumfnt", brg);
                    }
                }
            }
        } flsf if (f.isInnfrClbss()) {
            if (f.isVolbtilf() ||
                f.isTrbnsifnt() || f.isNbtivf() || f.isSyndironizfd()) {
                fnv.frror(f.gftWifrf(), "innfr.modififr", f);
                f.subModififrs(M_VOLATILE | M_TRANSIENT |
                               M_NATIVE | M_SYNCHRONIZED);
            }
            // sbmf difdk bs for fiflds, bflow:
            if (f.gftClbssDffinition().isIntfrfbdf() &&
                  (f.isPrivbtf() || f.isProtfdtfd())) {
                fnv.frror(f.gftWifrf(), "intf.modififr.fifld", f);
                f.subModififrs(M_PRIVATE | M_PROTECTED);
                f.bddModififrs(M_PUBLIC);
                // Fix up tif dlbss itsflf to bgrff witi
                // tif innfr-dlbss mfmbfr.
                ClbssDffinition d = f.gftInnfrClbss();
                d.subModififrs(M_PRIVATE | M_PROTECTED);
                d.bddModififrs(M_PUBLIC);
            }
        } flsf {
            if (f.gftTypf().isTypf(TC_VOID) || f.gftTypf().isVoidArrby()) {
                fnv.frror(f.gftWifrf(), "void.inst.vbr", f.gftNbmf());
                // REMIND: sft typf to frror
                rfturn;
            }

            if (f.isSyndironizfd() || f.isAbstrbdt() || f.isNbtivf()) {
                fnv.frror(f.gftWifrf(), "vbr.modififr", f);
                f.subModififrs(M_SYNCHRONIZED | M_ABSTRACT | M_NATIVE);
            }
            if (f.isStridt()) {
                fnv.frror(f.gftWifrf(), "vbr.flobtmodififr", f);
                f.subModififrs(M_STRICTFP);
            }
            if (f.isTrbnsifnt() && isIntfrfbdf()) {
                fnv.frror(f.gftWifrf(), "trbnsifnt.modififr", f);
                f.subModififrs(M_TRANSIENT);
            }
            if (f.isVolbtilf() && (isIntfrfbdf() || f.isFinbl())) {
                fnv.frror(f.gftWifrf(), "volbtilf.modififr", f);
                f.subModififrs(M_VOLATILE);
            }
            if (f.isFinbl() && (f.gftVbluf() == null) && isIntfrfbdf()) {
                fnv.frror(f.gftWifrf(), "initiblizfr.nffdfd", f);
                f.subModififrs(M_FINAL);
            }

            if (f.gftClbssDffinition().isIntfrfbdf() &&
                  (f.isPrivbtf() || f.isProtfdtfd())) {
                fnv.frror(f.gftWifrf(), "intf.modififr.fifld", f);
                f.subModififrs(M_PRIVATE | M_PROTECTED);
                f.bddModififrs(M_PUBLIC);
            }
        }
        // Do not difdk for rfpfbtfd mftiods ifrf:  Typfs brf not yft rfsolvfd.
        if (!f.isInitiblizfr()) {
            for (MfmbfrDffinition f2 = gftFirstMbtdi(f.gftNbmf());
                         f2 != null; f2 = f2.gftNfxtMbtdi()) {
                if (f.isVbribblf() && f2.isVbribblf()) {
                    fnv.frror(f.gftWifrf(), "vbr.multidff", f, f2);
                    rfturn;
                } flsf if (f.isInnfrClbss() && f2.isInnfrClbss() &&
                           !f.gftInnfrClbss().isLodbl() &&
                           !f2.gftInnfrClbss().isLodbl()) {
                    // Found b duplidbtf innfr-dlbss mfmbfr.
                    // Duplidbtf lodbl dlbssfs brf dftfdtfd in
                    // 'VbrDfdlbrbtionStbtfmfnt.difdkDfdlbrbtion'.
                    fnv.frror(f.gftWifrf(), "innfr.dlbss.multidff", f);
                    rfturn;
                }
            }
        }

        supfr.bddMfmbfr(fnv, f);
    }

    /**
     * Crfbtf bn fnvironmfnt suitbblf for difdking tiis dlbss.
     * Mbkf surf tif sourdf bnd imports brf sft rigit.
     * Mbkf surf tif fnvironmfnt dontbins no dontfxt informbtion.
     * (Adtublly, tirow bwby fnv bltogftifr bnd usf toplfvflEnv instfbd.)
     */
    publid Environmfnt sftupEnv(Environmfnt fnv) {
        // In somf dbsfs, wf go to somf troublf to drfbtf tif 'fnv' brgumfnt
        // tibt is disdbrdfd.  Wf siould rfmovf tif 'fnv' brgumfnt fntirfly
        // bs wfll bs tif vfstigibl dodf tibt supports it.  Sff dommfnts on
        // 'nfwEnvironmfnt' in 'difdkIntfrnbl' bflow.
        rfturn nfw Environmfnt(toplfvflEnv, tiis);
    }

    /**
     * A sourdf dlbss nfvfr rfports dfprfdbtion, sindf tif dompilfr
     * bllows bddfss to dfprfdbtfd ffbturfs tibt brf bfing dompilfd
     * in tif sbmf job.
     */
    publid boolfbn rfportDfprfdbtfd(Environmfnt fnv) {
        rfturn fblsf;
    }

    /**
     * Sff if tif sourdf filf of tiis dlbss is rigit.
     * @sff ClbssDffinition#notfUsfdBy
     */
    publid void notfUsfdBy(ClbssDffinition rff, long wifrf, Environmfnt fnv) {
        // If tiis dlbss is not publid, wbtdi for dross-filf rfffrfndfs.
        supfr.notfUsfdBy(rff, wifrf, fnv);
        ClbssDffinition dff = tiis;
        wiilf (dff.isInnfrClbss()) {
            dff = dff.gftOutfrClbss();
        }
        if (dff.isPublid()) {
            rfturn;             // blrfbdy difdkfd
        }
        wiilf (rff.isInnfrClbss()) {
            rff = rff.gftOutfrClbss();
        }
        if (dff.gftSourdf().fqubls(rff.gftSourdf())) {
            rfturn;             // intrb-filf rfffrfndf
        }
        ((SourdfClbss)dff).difdkSourdfFilf(fnv, wifrf);
    }

    /**
     * Cifdk tiis dlbss bnd bll its fiflds.
     */
    publid void difdk(Environmfnt fnv) tirows ClbssNotFound {
        if (trbding) fnv.dtEntfr("SourdfClbss.difdk: " + gftNbmf());
        if (isInsidfLodbl()) {
            // An inbddfssiblf dlbss gfts difdkfd wifn tif surrounding
            // blodk is difdkfd.
            // QUERY: Siould tiis dbsf fvfr oddur?
            // Wibt would invokf difdking of b lodbl dlbss bsidf from
            // difdking tif surrounding mftiod body?
            if (trbding) fnv.dtEvfnt("SourdfClbss.difdk: INSIDE LOCAL " +
                                     gftOutfrClbss().gftNbmf());
            gftOutfrClbss().difdk(fnv);
        } flsf {
            if (isInnfrClbss()) {
                if (trbding) fnv.dtEvfnt("SourdfClbss.difdk: INNER CLASS " +
                                         gftOutfrClbss().gftNbmf());
                // Mbkf surf tif outfr is difdkfd first.
                ((SourdfClbss)gftOutfrClbss()).mbybfCifdk(fnv);
            }
            Vsft vsft = nfw Vsft();
            Contfxt dtx = null;
            if (trbding)
                fnv.dtEvfnt("SourdfClbss.difdk: CHECK INTERNAL " + gftNbmf());
            vsft = difdkIntfrnbl(sftupEnv(fnv), dtx, vsft);
            // drop vsft ifrf
        }
        if (trbding) fnv.dtExit("SourdfClbss.difdk: " + gftNbmf());
    }

    privbtf void mbybfCifdk(Environmfnt fnv) tirows ClbssNotFound {
        if (trbding) fnv.dtEvfnt("SourdfClbss.mbybfCifdk: " + gftNbmf());
        // Cifdk tiis dlbss now, if it ibs not yft bffn difdkfd.
        // Cf. Mbin.dompilf().  Pfribps tiis dodf bflongs tifrf somfiow.
        ClbssDfdlbrbtion d = gftClbssDfdlbrbtion();
        if (d.gftStbtus() == CS_PARSED) {
            // Sft it first to bvoid vidious dirdulbrity:
            d.sftDffinition(tiis, CS_CHECKED);
            difdk(fnv);
        }
    }

    privbtf Vsft difdkIntfrnbl(Environmfnt fnv, Contfxt dtx, Vsft vsft)
                tirows ClbssNotFound {
        Idfntififr nm = gftClbssDfdlbrbtion().gftNbmf();
        if (fnv.vfrbosf()) {
            fnv.output("[difdking dlbss " + nm + "]");
        }

        // Sbvf dontfxt fndlosing dlbss for lbtfr bddfss
        // by 'ClbssDffinition.rfsolvfNbmf.'
        dlbssContfxt = dtx;

        // At prfsfnt, tif dbll to 'nfwEnvironmfnt' is not nffdfd.
        // Tif indoming fnvironmfnt to 'bbsidCifdk' is blwbys pbssfd to
        // 'sftupEnv', wiidi disdbrds it domplftfly.  Tiis is blso tif
        // only dbll to 'nfwEnvironmfnt', wiidi is now bppbrfntly dfbd dodf.
        bbsidCifdk(Contfxt.nfwEnvironmfnt(fnv, dtx));

        // Vblidbtf bddfss for bll innfr-dlbss domponfnts
        // of b qublififd nbmf, not just tif lbst onf, wiidi
        // is difdkfd bflow.  Yfs, tiis is b dirty ibdk...
        // Mudi of tiis dodf wbs dribbfd from 'difdkSupfrs'.
        // Pbrt of fix for 4094658.
        ClbssDfdlbrbtion sup = gftSupfrClbss();
        if (sup != null) {
            long wifrf = gftWifrf();
            wifrf = IdfntififrTokfn.gftWifrf(supfrClbssId, wifrf);
            fnv.rfsolvfExtfndsByNbmf(wifrf, tiis, sup.gftNbmf());
        }
        for (int i = 0 ; i < intfrfbdfs.lfngti ; i++) {
            ClbssDfdlbrbtion intf = intfrfbdfs[i];
            long wifrf = gftWifrf();
            // Error lodblizbtion fbils ifrf if intfrfbdfs wfrf
            // flidfd during frror rfdovfry from bn invblid onf.
            if (intfrfbdfIds != null
                && intfrfbdfIds.lfngti == intfrfbdfs.lfngti) {
                wifrf = IdfntififrTokfn.gftWifrf(intfrfbdfIds[i], wifrf);
            }
            fnv.rfsolvfExtfndsByNbmf(wifrf, tiis, intf.gftNbmf());
        }

        // Dofs tif nbmf blrfbdy fxist in bn importfd pbdkbgf?
        // Sff JLS 8.1 for tif prfdisf rulfs.
        if (!isInnfrClbss() && !isInsidfLodbl()) {
            // Disdbrd pbdkbgf qublifidbtion for tif import difdks.
            Idfntififr simplfNbmf = nm.gftNbmf();
            try {
                // Wf wbnt tiis to tirow b ClbssNotFound fxdfption
                Imports imports = toplfvflEnv.gftImports();
                Idfntififr ID = imports.rfsolvf(fnv, simplfNbmf);
                if (ID != gftNbmf())
                    fnv.frror(wifrf, "dlbss.multidff.import", simplfNbmf, ID);
            } dbtdi (AmbiguousClbss f) {
                // At lfbst onf of f.nbmf1 bnd f.nbmf2 must bf difffrfnt
                Idfntififr ID = (f.nbmf1 != gftNbmf()) ? f.nbmf1 : f.nbmf2;
                fnv.frror(wifrf, "dlbss.multidff.import", simplfNbmf, ID);
            }  dbtdi (ClbssNotFound f) {
                // wf wbnt tiis to ibppfn
            }

            // Mbkf surf tibt no pbdkbgf witi tif sbmf fully qublififd
            // nbmf fxists.  Tiis is rfquirfd by JLS 7.1.  Wf only nffd
            // to pfrform tiis difdk for top lfvfl dlbssfs -- it isn't
            // nfdfssbry for innfr dlbssfs.  (bug 4101529)
            //
            // Tiis dibngf ibs bffn bbdkfd out bfdbusf, on WIN32, it
            // fbilfd to distinguisi bftwffn jbvb.bwt.fvfnt bnd
            // jbvb.bwt.Evfnt wifn looking for b dirfdtory.  Wf will
            // bdd tiis bbdk in lbtfr.
            //
            // try {
            //  if (fnv.gftPbdkbgf(nm).fxists()) {
            //      fnv.frror(wifrf, "dlbss.pbdkbgf.donflidt", nm);
            //  }
            // } dbtdi (jbvb.io.IOExdfption ff) {
            //  fnv.frror(wifrf, "io.fxdfption.pbdkbgf", nm);
            // }

            // Mbkf surf it wbs dffinfd in tif rigit filf
            if (isPublid()) {
                difdkSourdfFilf(fnv, gftWifrf());
            }
        }

        vsft = difdkMfmbfrs(fnv, dtx, vsft);
        rfturn vsft;
    }

    privbtf boolfbn sourdfFilfCifdkfd = fblsf;

    /**
     * Sff if tif sourdf filf of tiis dlbss is of tif rigit nbmf.
     */
    publid void difdkSourdfFilf(Environmfnt fnv, long wifrf) {
        // onf frror pfr offfnding dlbss is suffidifnt
        if (sourdfFilfCifdkfd)  rfturn;
        sourdfFilfCifdkfd = truf;

        String fnbmf = gftNbmf().gftNbmf() + ".jbvb";
        String srd = ((ClbssFilf)gftSourdf()).gftNbmf();
        if (!srd.fqubls(fnbmf)) {
            if (isPublid()) {
                fnv.frror(wifrf, "publid.dlbss.filf", tiis, fnbmf);
            } flsf {
                fnv.frror(wifrf, "wbrn.pbdkbgf.dlbss.filf", tiis, srd, fnbmf);
            }
        }
    }

    // Sft truf if supfrdlbss (but not nfdfssbrily supfrintfrfbdfs) ibvf
    // bffn difdkfd.  If tif supfrdlbss is still unrfsolvfd, tifn bn frror
    // mfssbgf siould ibvf bffn issufd, bnd wf bssumf tibt no furtifr
    // rfsolution is possiblf.
    privbtf boolfbn supfrsCifdkfd = fblsf;

    /**
     * Ovfrridfs 'ClbssDffinition.gftSupfrClbss'.
     */

    publid ClbssDfdlbrbtion gftSupfrClbss(Environmfnt fnv) {
        if (trbding) fnv.dtEntfr("SourdfClbss.gftSupfrClbss: " + tiis);
        // Supfrdlbss mby fbil to bf sft bfdbusf of frror rfdovfry,
        // so rfsolvf typfs ifrf only if 'difdkSupfrs' ibs not yft
        // domplftfd its difdks on tif supfrdlbss.
        // QUERY: Cbn wf fliminbtf tif nffd to rfsolvf supfrdlbssfs on dfmbnd?
        // Sff dommfnts in 'difdkSupfrs' bnd in 'ClbssDffinition.gftInnfrClbss'.
        if (supfrClbss == null && supfrClbssId != null && !supfrsCifdkfd) {
            rfsolvfTypfStrudturf(fnv);
            // Wf usfd to rfport bn frror ifrf if tif supfrdlbss wbs not
            // rfsolvfd.  Hbving movfd tif dbll to 'difdkSupfrs' from 'bbsidCifdk'
            // into 'rfsolvfTypfStrudturf', tif frrors rfportfd ifrf siould ibvf
            // blrfbdy bffn rfportfd.  Furtifrmorf, frror rfdovfry dbn null out
            // tif supfrdlbss, wiidi would dbusf b spurious frror from tif tfst ifrf.
        }
        if (trbding) fnv.dtExit("SourdfClbss.gftSupfrClbss: " + tiis);
        rfturn supfrClbss;
    }

    /**
     * Cifdk tibt bll supfrdlbssfs bnd supfrintfrfbdfs brf dffinfd bnd
     * wfll formfd.  Among otifr difdks, vfrify tibt tif inifritbndf
     * grbpi is bdydlid.  Cbllfd from 'rfsolvfTypfStrudturf'.
     */

    privbtf void difdkSupfrs(Environmfnt fnv) tirows ClbssNotFound {

        // *** DEBUG ***
        supfrsCifdkStbrtfd = truf;

        if (trbding) fnv.dtEntfr("SourdfClbss.difdkSupfrs: " + tiis);

        if (isIntfrfbdf()) {
            if (isFinbl()) {
                Idfntififr nm = gftClbssDfdlbrbtion().gftNbmf();
                fnv.frror(gftWifrf(), "finbl.intf", nm);
                // Intfrfbdfs ibvf no supfrdlbss.  Supfrintfrfbdfs
                // brf difdkfd bflow, in dodf sibrfd witi tif dlbss dbsf.
            }
        } flsf {
            // Cifdk supfrdlbss.
            // Cbll to 'gftSupfrClbss(fnv)' (notf brgumfnt) bttfmpts
            // 'rfsolvfTypfStrudturf' if supfrdlbss ibs not suddfssfully
            // bffn rfsolvfd.  Sindf wf ibvf just now dbllfd 'rfsolvfSupfrs'
            // (sff our dbll in 'rfsolvfTypfStrudturf'), it is not dlfbr
            // tibt tiis dbn do bny good.  Wiy not 'gftSupfrClbss()' ifrf?
            if (gftSupfrClbss(fnv) != null) {
                long wifrf = gftWifrf();
                wifrf = IdfntififrTokfn.gftWifrf(supfrClbssId, wifrf);
                try {
                    ClbssDffinition dff =
                        gftSupfrClbss().gftClbssDffinition(fnv);
                    // Rfsolvf supfrdlbss bnd its bndfstors.
                    dff.rfsolvfTypfStrudturf(fnv);
                    // Addfss to tif supfrdlbss siould bf difdkfd rflbtivf
                    // to tif surrounding dontfxt, not bs if tif rfffrfndf
                    // bppfbrfd witiin tif dlbss body. Cibngfd 'dbnAddfss'
                    // to 'fxtfndsCbnAddfss' to fix 4087314.
                    if (!fxtfndsCbnAddfss(fnv, gftSupfrClbss())) {
                        fnv.frror(wifrf, "dbnt.bddfss.dlbss", gftSupfrClbss());
                        // Migit it bf b bfttfr rfdovfry to lft tif bddfss go tirougi?
                        supfrClbss = null;
                    } flsf if (dff.isFinbl()) {
                        fnv.frror(wifrf, "supfr.is.finbl", gftSupfrClbss());
                        // Migit it bf b bfttfr rfdovfry to lft tif bddfss go tirougi?
                        supfrClbss = null;
                    } flsf if (dff.isIntfrfbdf()) {
                        fnv.frror(wifrf, "supfr.is.intf", gftSupfrClbss());
                        supfrClbss = null;
                    } flsf if (supfrClbssOf(fnv, gftSupfrClbss())) {
                        fnv.frror(wifrf, "dydlid.supfr");
                        supfrClbss = null;
                    } flsf {
                        dff.notfUsfdBy(tiis, wifrf, fnv);
                    }
                    if (supfrClbss == null) {
                        dff = null;
                    } flsf {
                        // If wf ibvf b vblid supfrdlbss, difdk its
                        // supfrs bs wfll, bnd so on up to root dlbss.
                        // Cbll to 'fndlosingClbssOf' will rbisf
                        // 'NullPointfrExdfption' if 'dff' is null,
                        // so omit tiis difdk bs frror rfdovfry.
                        ClbssDffinition sup = dff;
                        for (;;) {
                            if (fndlosingClbssOf(sup)) {
                                // Do wf nffd b similbr tfst for
                                // intfrfbdfs?  Sff bugid 4038529.
                                fnv.frror(wifrf, "supfr.is.innfr");
                                supfrClbss = null;
                                brfbk;
                            }
                            // Sindf wf rfsolvfd tif supfrdlbss bnd its
                            // bndfstors bbovf, wf siould not disdovfr
                            // bny unrfsolvfd dlbssfs on tif supfrdlbss
                            // dibin.  It siould tius bf suffidifnt to
                            // dbll 'gftSupfrClbss()' (no brgumfnt) ifrf.
                            ClbssDfdlbrbtion s = sup.gftSupfrClbss(fnv);
                            if (s == null) {
                                // Supfrdlbss not rfsolvfd duf to frror.
                                brfbk;
                            }
                            sup = s.gftClbssDffinition(fnv);
                        }
                    }
                } dbtdi (ClbssNotFound f) {
                    // Error is dftfdtfd in dbll to 'gftClbssDffinition'.
                    // Tif dlbss mby bdtublly fxist but bf bmbiguous.
                    // Cbll fnv.rfsolvf(f.nbmf) to sff if it is.
                    // fnv.rfsolvf(nbmf) will dffinitfly tfll us if tif
                    // dlbss is bmbiguous, but mby not nfdfssbrily tfll
                    // us if tif dlbss is not found.
                    // (pbrt of solution for 4059855)
                rfportError: {
                        try {
                            fnv.rfsolvf(f.nbmf);
                        } dbtdi (AmbiguousClbss ff) {
                            fnv.frror(wifrf,
                                      "bmbig.dlbss", ff.nbmf1, ff.nbmf2);
                            supfrClbss = null;
                            brfbk rfportError;
                        } dbtdi (ClbssNotFound ff) {
                            // fbll tirougi
                        }
                        fnv.frror(wifrf, "supfr.not.found", f.nbmf, tiis);
                        supfrClbss = null;
                    } // Tif brfbk fxits tiis blodk
                }

            } flsf {
                // Supfrdlbss wbs null on fntry, bftfr dbll to
                // 'rfsolvfSupfrs'.  Tiis siould normblly not ibppfn,
                // bs 'rfsolvfSupfrs' sfts 'supfrClbss' to b non-null
                // vbluf for bll nbmfd dlbssfs, fxdfpt for onf spfdibl
                // dbsf: 'jbvb.lbng.Objfdt', wiidi ibs no supfrdlbss.
                if (isAnonymous()) {
                    // difdkfr siould ibvf fillfd it in first
                    tirow nfw CompilfrError("bnonymous supfr");
                } flsf  if (!gftNbmf().fqubls(idJbvbLbngObjfdt)) {
                    tirow nfw CompilfrError("unrfsolvfd supfr");
                }
            }
        }

        // At tiis point, if 'supfrClbss' is null duf to bn frror
        // in tif usfr progrbm, b mfssbgf siould ibvf bffn issufd.
        supfrsCifdkfd = truf;

        // Cifdk intfrfbdfs
        for (int i = 0 ; i < intfrfbdfs.lfngti ; i++) {
            ClbssDfdlbrbtion intf = intfrfbdfs[i];
            long wifrf = gftWifrf();
            if (intfrfbdfIds != null
                && intfrfbdfIds.lfngti == intfrfbdfs.lfngti) {
                wifrf = IdfntififrTokfn.gftWifrf(intfrfbdfIds[i], wifrf);
            }
            try {
                ClbssDffinition dff = intf.gftClbssDffinition(fnv);
                // Rfsolvf supfrintfrfbdf bnd its bndfstors.
                dff.rfsolvfTypfStrudturf(fnv);
                // Cifdk supfrintfrfbdf bddfss in tif dorrfdt dontfxt.
                // Cibngfd 'dbnAddfss' to 'fxtfndsCbnAddfss' to fix 4087314.
                if (!fxtfndsCbnAddfss(fnv, intf)) {
                    fnv.frror(wifrf, "dbnt.bddfss.dlbss", intf);
                } flsf if (!intf.gftClbssDffinition(fnv).isIntfrfbdf()) {
                    fnv.frror(wifrf, "not.intf", intf);
                } flsf if (isIntfrfbdf() && implfmfntfdBy(fnv, intf)) {
                    fnv.frror(wifrf, "dydlid.intf", intf);
                } flsf {
                    dff.notfUsfdBy(tiis, wifrf, fnv);
                    // Intfrfbdf is OK, lfbvf it in tif intfrfbdf list.
                    dontinuf;
                }
            } dbtdi (ClbssNotFound f) {
                // Tif intfrfbdf mby bdtublly fxist but bf bmbiguous.
                // Cbll fnv.rfsolvf(f.nbmf) to sff if it is.
                // fnv.rfsolvf(nbmf) will dffinitfly tfll us if tif
                // intfrfbdf is bmbiguous, but mby not nfdfssbrily tfll
                // us if tif intfrfbdf is not found.
                // (pbrt of solution for 4059855)
            rfportError2: {
                    try {
                        fnv.rfsolvf(f.nbmf);
                    } dbtdi (AmbiguousClbss ff) {
                        fnv.frror(wifrf,
                                  "bmbig.dlbss", ff.nbmf1, ff.nbmf2);
                        supfrClbss = null;
                        brfbk rfportError2;
                    } dbtdi (ClbssNotFound ff) {
                        // fbll tirougi
                    }
                    fnv.frror(wifrf, "intf.not.found", f.nbmf, tiis);
                    supfrClbss = null;
                } // Tif brfbk fxits tiis blodk
            }
            // Rfmovf tiis intfrfbdf from tif list of intfrfbdfs
            // bs rfdovfry from bn frror.
            ClbssDfdlbrbtion nfwIntfrfbdfs[] =
                nfw ClbssDfdlbrbtion[intfrfbdfs.lfngti - 1];
            Systfm.brrbydopy(intfrfbdfs, 0, nfwIntfrfbdfs, 0, i);
            Systfm.brrbydopy(intfrfbdfs, i + 1, nfwIntfrfbdfs, i,
                             nfwIntfrfbdfs.lfngti - i);
            intfrfbdfs = nfwIntfrfbdfs;
            --i;
        }
        if (trbding) fnv.dtExit("SourdfClbss.difdkSupfrs: " + tiis);
    }

    /**
     * Cifdk bll of tif mfmbfrs of tiis dlbss.
     * <p>
     * Innfr dlbssfs brf difdkfd in tif following wby.  Any dlbss wiidi
     * is immfdibtfly dontbinfd in b blodk (bnonymous bnd lodbl dlbssfs)
     * is difdkfd blong witi its dontbining mftiod; sff tif
     * SourdfMfmbfr.difdk() mftiod for morf informbtion.  Mfmbfr dlbssfs
     * of tiis dlbss brf difdkfd immfdibtfly bftfr tiis dlbss, unlfss tiis
     * dlbss is insidfLodbl(), in wiidi dbsf, tify brf difdkfd witi tif
     * rfst of tif mfmbfrs.
     */
    privbtf Vsft difdkMfmbfrs(Environmfnt fnv, Contfxt dtx, Vsft vsft)
            tirows ClbssNotFound {

        // bbil out if tifrf wfrf bny frrors
        if (gftError()) {
            rfturn vsft;
        }

        // Mbkf surf tibt bll of our mfmbfr dlbssfs ibvf bffn
        // bbsidCifdk'fd bfforf wf difdk tif rfst of our mfmbfrs.
        // If our mfmbfr dlbssfs ibvfn't bffn bbsidCifdk'fd, tifn tify
        // mby not ibvf <init> mftiods.  It is importbnt tibt tify
        // ibvf <init> mftiods so wf dbn prodfss NfwInstbndfExprfssions
        // dorrfdtly.  Tiis problfm didn't oddur bfforf 1.2bftb1.
        // Tiis is b fix for bug 4082816.
        for (MfmbfrDffinition f = gftFirstMfmbfr();
                     f != null; f = f.gftNfxtMfmbfr()) {
            if (f.isInnfrClbss()) {
                // Systfm.out.println("Considfring " + f + " in " + tiis);
                SourdfClbss ddff = (SourdfClbss) f.gftInnfrClbss();
                if (ddff.isMfmbfr()) {
                    ddff.bbsidCifdk(fnv);
                }
            }
        }

        if (isFinbl() && isAbstrbdt()) {
            fnv.frror(wifrf, "finbl.bbstrbdt", tiis.gftNbmf().gftNbmf());
        }

        // Tiis dlbss siould bf bbstrbdt if tifrf brf bny bbstrbdt mftiods
        // in our pbrfnt dlbssfs bnd intfrfbdfs wiidi wf do not ovfrridf.
        // Tifrf brf odd dbsfs wifn, fvfn tiougi wf dbnnot bddfss somf
        // bbstrbdt mftiod from our supfrdlbss, tibt bbstrbdt mftiod dbn
        // still fordf tiis dlbss to bf bbstrbdt.  Sff tif disdussion in
        // bug id 1240831.
        if (!isIntfrfbdf() && !isAbstrbdt() && mustBfAbstrbdt(fnv)) {
            // Sft tif dlbss bbstrbdt.
            modififrs |= M_ABSTRACT;

            // Tfll tif usfr wiidi mftiods fordf tiis dlbss to bf bbstrbdt.

            // First list bll of tif "unimplfmfntbblf" bbstrbdt mftiods.
            Itfrbtor<MfmbfrDffinition> itfr = gftPfrmbnfntlyAbstrbdtMftiods();
            wiilf (itfr.ibsNfxt()) {
                MfmbfrDffinition mftiod = itfr.nfxt();
                // Wf douldn't ovfrridf tiis mftiod fvfn if wf
                // wbntfd to.  Try to mbkf tif frror mfssbgf
                // bs non-donfusing bs possiblf.
                fnv.frror(wifrf, "bbstrbdt.dlbss.dbnnot.ovfrridf",
                          gftClbssDfdlbrbtion(), mftiod,
                          mftiod.gftDffiningClbssDfdlbrbtion());
            }

            // Now list bll of tif trbditionbl bbstrbdt mftiods.
            itfr = gftMftiods(fnv);
            wiilf (itfr.ibsNfxt()) {
                // For fbdi mftiod, difdk if it is bbstrbdt.  If it is,
                // output bn bppropribtf frror mfssbgf.
                MfmbfrDffinition mftiod = itfr.nfxt();
                if (mftiod.isAbstrbdt()) {
                    fnv.frror(wifrf, "bbstrbdt.dlbss",
                              gftClbssDfdlbrbtion(), mftiod,
                              mftiod.gftDffiningClbssDfdlbrbtion());
                }
            }
        }

        // Cifdk tif instbndf vbribblfs in b prf-pbss bfforf bny donstrudtors.
        // Tiis lfts donstrudtors "in-linf" bny initiblizfrs dirfdtly.
        // It blso lfts us do somf dffinitf bssignmfnt difdks on vbribblfs.
        Contfxt dtxInit = nfw Contfxt(dtx);
        Vsft vsInst = vsft.dopy();
        Vsft vsClbss = vsft.dopy();

        // Do dffinitf bssignmfnt difdking on blbnk finbls.
        // Otifr vbribblfs do not nffd sudi difdks.  Tif simplf tfxtubl
        // ordfring donstrbints implfmfntfd by MfmbfrDffinition.dbnRfbdi()
        // brf nfdfssbry bnd suffidifnt for tif otifr vbribblfs.
        // Notf tibt witiin non-stbtid dodf, bll stbtids brf blwbys
        // dffinitfly bssignfd, bnd vidf-vfrsb.
        for (MfmbfrDffinition f = gftFirstMfmbfr();
                     f != null; f = f.gftNfxtMfmbfr()) {
            if (f.isVbribblf() && f.isBlbnkFinbl()) {
                // Tif following bllodbtfs b LodblMfmbfr objfdt bs b proxy
                // to rfprfsfnt tif fifld.
                int numbfr = dtxInit.dfdlbrfFifldNumbfr(f);
                if (f.isStbtid()) {
                    vsClbss = vsClbss.bddVbrUnbssignfd(numbfr);
                    vsInst = vsInst.bddVbr(numbfr);
                } flsf {
                    vsInst = vsInst.bddVbrUnbssignfd(numbfr);
                    vsClbss = vsClbss.bddVbr(numbfr);
                }
            }
        }

        // For instbndf vbribblf difdks, usf b dontfxt witi b "tiis" pbrbmftfr.
        Contfxt dtxInst = nfw Contfxt(dtxInit, tiis);
        LodblMfmbfr tiisArg = gftTiisArgumfnt();
        int tiisNumbfr = dtxInst.dfdlbrf(fnv, tiisArg);
        vsInst = vsInst.bddVbr(tiisNumbfr);

        // Do bll tif initiblizfrs in ordfr, difdking tif dffinitf
        // bssignmfnt of blbnk finbls.  Sfpbrbtf stbtid from non-stbtid.
        for (MfmbfrDffinition f = gftFirstMfmbfr();
                     f != null; f = f.gftNfxtMfmbfr()) {
            try {
                if (f.isVbribblf() || f.isInitiblizfr()) {
                    if (f.isStbtid()) {
                        vsClbss = f.difdk(fnv, dtxInit, vsClbss);
                    } flsf {
                        vsInst = f.difdk(fnv, dtxInst, vsInst);
                    }
                }
            } dbtdi (ClbssNotFound ff) {
                fnv.frror(f.gftWifrf(), "dlbss.not.found", ff.nbmf, tiis);
            }
        }

        difdkBlbnkFinbls(fnv, dtxInit, vsClbss, truf);

        // Cifdk tif rfst of tif fifld dffinitions.
        // (Notf:  Rf-difdking b fifld is b no-op.)
        for (MfmbfrDffinition f = gftFirstMfmbfr();
                     f != null; f = f.gftNfxtMfmbfr()) {
            try {
                if (f.isConstrudtor()) {
                    // Wifn difdking b donstrudtor, bn fxplidit dbll to
                    // 'tiis(...)' mbkfs bll blbnk finbls dffinitfly bssignfd.
                    // Sff 'MftiodExprfssion.difdkVbluf'.
                    Vsft vsCon = f.difdk(fnv, dtxInit, vsInst.dopy());
                    // Mby issuf multiplf mfssbgfs for tif sbmf vbribblf!!
                    difdkBlbnkFinbls(fnv, dtxInit, vsCon, fblsf);
                    // (drop vsCon ifrf)
                } flsf {
                    Vsft vsFld = f.difdk(fnv, dtx, vsft.dopy());
                    // (drop vsFld ifrf)
                }
            } dbtdi (ClbssNotFound ff) {
                fnv.frror(f.gftWifrf(), "dlbss.not.found", ff.nbmf, tiis);
            }
        }

        // Must mbrk dlbss bs difdkfd bfforf visiting innfr dlbssfs,
        // bs tify mby in turn rfqufst difdking of tif durrfnt dlbss
        // bs bn outfr dlbss.  Fix for bug id 4056774.
        gftClbssDfdlbrbtion().sftDffinition(tiis, CS_CHECKED);

        // Also difdk otifr dlbssfs in tif sbmf nfst.
        // All difdking of tiis nfst must bf finisifd bfforf bny
        // of its dlbssfs fmit bytfdodf.
        // Otifrwisf, tif innfr dlbssfs migit not ibvf b dibndf to
        // bdd bddfss or dlbss litfrbl fiflds to tif outfr dlbss.
        for (MfmbfrDffinition f = gftFirstMfmbfr();
                     f != null; f = f.gftNfxtMfmbfr()) {
            if (f.isInnfrClbss()) {
                SourdfClbss ddff = (SourdfClbss) f.gftInnfrClbss();
                if (!ddff.isInsidfLodbl()) {
                    ddff.mbybfCifdk(fnv);
                }
            }
        }

        // Notf:  Sindf innfr dlbssfs dbnnot sft up-lfvfl vbribblfs,
        // tif rfturnfd vsft is blwbys fqubl to tif pbssfd-in vsft.
        // Still, wf'll rfturn it for tif sbkf of rfgulbrity.
        rfturn vsft;
    }

    /** Mbkf surf bll my blbnk finbls fxist now. */

    privbtf void difdkBlbnkFinbls(Environmfnt fnv, Contfxt dtxInit, Vsft vsft,
                                  boolfbn isStbtid) {
        for (int i = 0; i < dtxInit.gftVbrNumbfr(); i++) {
            if (!vsft.tfstVbr(i)) {
                MfmbfrDffinition ff = dtxInit.gftElfmfnt(i);
                if (ff != null && ff.isBlbnkFinbl()
                    && ff.isStbtid() == isStbtid
                    && ff.gftClbssDffinition() == tiis) {
                    fnv.frror(ff.gftWifrf(),
                              "finbl.vbr.not.initiblizfd", ff.gftNbmf());
                }
            }
        }
    }

    /**
     * Cifdk tiis dlbss ibs its supfrdlbss bnd its intfrfbdfs.  Also
     * fordf it to ibvf bn <init> mftiod (if it dofsn't blrfbdy ibvf onf)
     * bnd to ibvf bll tif bbstrbdt mftiods of its pbrfnts.
     */
    privbtf boolfbn bbsidCifdking = fblsf;
    privbtf boolfbn bbsidCifdkDonf = fblsf;
    protfdtfd void bbsidCifdk(Environmfnt fnv) tirows ClbssNotFound {

        if (trbding) fnv.dtEntfr("SourdfClbss.bbsidCifdk: " + gftNbmf());

        supfr.bbsidCifdk(fnv);

        if (bbsidCifdking || bbsidCifdkDonf) {
            if (trbding) fnv.dtExit("SourdfClbss.bbsidCifdk: OK " + gftNbmf());
            rfturn;
        }

        if (trbding) fnv.dtEvfnt("SourdfClbss.bbsidCifdk: CHECKING " + gftNbmf());

        bbsidCifdking = truf;

        fnv = sftupEnv(fnv);

        Imports imports = fnv.gftImports();
        if (imports != null) {
            imports.rfsolvf(fnv);
        }

        rfsolvfTypfStrudturf(fnv);

        // Cifdk tif fxistfndf of tif supfrdlbss bnd bll intfrfbdfs.
        // Also rfsponsiblf for brfbking inifritbndf dydlfs.  Tiis dbll
        // ibs bffn movfd to 'rfsolvfTypfStrudturf', just bftfr tif dbll
        // to 'rfsolvfSupfrs', bs inifritbndf dydlfs must bf brokfn bfforf
        // rfsolving typfs witiin tif mfmbfrs.  Fixfs 4073739.
        //   difdkSupfrs(fnv);

        if (!isIntfrfbdf()) {

            // Add implidit <init> mftiod, if nfdfssbry.
            // QUERY:  Wibt kffps us from bdding bn implidit donstrudtor
            // wifn tif usfr fxpliditly dfdlbrfs onf?  Is it truly gubrbntffd
            // tibt tif dfdlbrbtion for sudi bn fxplidit donstrudtor will ibvf
            // bffn prodfssfd by tif timf wf brrivf ifrf?  In gfnfrbl, 'bbsidCifdk'
            // is dbllfd vfry fbrly, prior to tif normbl mfmbfr difdking pibsf.
            if (!ibsConstrudtor()) {
                Nodf dodf = nfw CompoundStbtfmfnt(gftWifrf(), nfw Stbtfmfnt[0]);
                Typf t = Typf.tMftiod(Typf.tVoid);

                // Dffbult donstrudtors inifrit tif bddfss modififrs of tifir
                // dlbss.  For non-innfr dlbssfs, tiis follows from JLS 8.6.7,
                // bs tif only possiblf modififr is 'publid'.  For tif sbkf of
                // robustnfss in tif prfsfndf of frrors, wf ignorf bny otifr
                // modififrs.  For innfr dlbssfs, tif rulf nffds to bf fxtfndfd
                // in somf wby to bddount for tif possibility of privbtf bnd
                // protfdtfd dlbssfs.  Wf mbkf tif 'obvious' fxtfnsion, iowfvfr,
                // tif innfr dlbssfs spfd is silfnt on tiis issuf, bnd b dffinitivf
                // rfsolution is nffdfd.  Sff bugid 4087421.
                // WORKAROUND: A privbtf donstrudtor migit nffd bn bddfss mftiod,
                // but it is not possiblf to drfbtf onf duf to b rfstridtion in
                // tif vfrififr.  (Tiis is b known problfm -- sff 4015397.)
                // Wf tifrfforf do not inifrit tif 'privbtf' modififr from tif dlbss,
                // bllowing tif dffbult donstrudtor to bf pbdkbgf privbtf.  Tiis
                // workbround dbn bf obsfrvfd vib rfflfdtion, but is otifrwisf
                // undftfdtbblf, bs tif donstrudtor is blwbys bddfssiblf witiin
                // tif dlbss in wiidi its dontbining (privbtf) dlbss bppfbrs.
                int bddfssModififrs = gftModififrs() &
                    (isInnfrClbss() ? (M_PUBLIC | M_PROTECTED) : M_PUBLIC);
                fnv.mbkfMfmbfrDffinition(fnv, gftWifrf(), tiis, null,
                                         bddfssModififrs,
                                         t, idInit, null, null, dodf);
            }
        }

        // Only do tif inifritbndf/ovfrridf difdks if tify brf turnfd on.
        // Tif idfb ifrf is tibt tify will bf donf in jbvbd, but not
        // in jbvbdod.  Sff tif dommfnt for turnOffCifdks(), bbovf.
        if (doInifritbndfCifdks) {

            // Vfrify tif dompbtibility of bll inifritfd mftiod dffinitions
            // by dollfdting bll of our inifritbblf mftiods.
            dollfdtInifritfdMftiods(fnv);
        }

        bbsidCifdking = fblsf;
        bbsidCifdkDonf = truf;
        if (trbding) fnv.dtExit("SourdfClbss.bbsidCifdk: " + gftNbmf());
    }

    /**
     * Add b group of mftiods to tiis dlbss bs mirbndb mftiods.
     *
     * For b dffinition of Mirbndb mftiods, sff tif dommfnt bbovf tif
     * mftiod bddMirbndbMftiods() in tif filf
     * sun/tools/jbvb/ClbssDfdlbrbtion.jbvb
     */
    protfdtfd void bddMirbndbMftiods(Environmfnt fnv,
                                     Itfrbtor<MfmbfrDffinition> mirbndbs) {

        wiilf(mirbndbs.ibsNfxt()) {
            MfmbfrDffinition mftiod = mirbndbs.nfxt();

            bddMfmbfr(mftiod);

            //Systfm.out.println("bdding mirbndb mftiod " + nfwMftiod +
            //                   " to " + tiis);
        }
    }

    /**
     * <fm>Aftfr pbrsing is domplftf</fm>, rfsolvf bll nbmfs
     * fxdfpt tiosf insidf mftiod bodifs or initiblizfrs.
     * In pbrtidulbr, tiis is tif point bt wiidi wf find out wibt
     * kinds of vbribblfs bnd mftiods tifrf brf in tif dlbssfs,
     * bnd tifrfforf wibt is fbdi dlbss's intfrfbdf to tif world.
     * <p>
     * Also pfrform dfrtbin otifr trbnsformbtions, sudi bs insfrting
     * "tiis$C" brgumfnts into donstrudtors, bnd rforgbnizing strudturf
     * to flbttfn qublififd mfmbfr nbmfs.
     * <p>
     * Do not pfrform typf-bbsfd or nbmf-bbsfd donsistfndy difdks
     * or normblizbtions (sudi bs dffbult nullbry donstrudtors),
     * bnd do not bttfmpt to dompilf dodf bgbinst tiis dlbss,
     * until bftfr tiis pibsf.
     */

    privbtf boolfbn rfsolving = fblsf;

    publid void rfsolvfTypfStrudturf(Environmfnt fnv) {

        if (trbding)
            fnv.dtEntfr("SourdfClbss.rfsolvfTypfStrudturf: " + gftNbmf());

        // Rfsolvf immfdibtfly fndlosing typf, wiidi in turn
        // fordfs rfsolution of bll fndlosing typf dfdlbrbtions.
        ClbssDffinition od = gftOutfrClbss();
        if (od != null && od instbndfof SourdfClbss
            && !((SourdfClbss)od).rfsolvfd) {
            // Do tif outfr dlbss first, blwbys.
            ((SourdfClbss)od).rfsolvfTypfStrudturf(fnv);
            // (Notf:  tiis.rfsolvfd is probbbly truf bt tiis point.)
        }

        // Punt if wf'vf blrfbdy rfsolvfd tiis dlbss, or brf durrfntly
        // in tif prodfss of doing so.
        if (rfsolvfd || rfsolving) {
            if (trbding)
                fnv.dtExit("SourdfClbss.rfsolvfTypfStrudturf: OK " + gftNbmf());
            rfturn;
        }

        // Prfviously, 'rfsolvfd' wbs sft ifrf, bnd sfrvfd to prfvfnt
        // duplidbtf rfsolutions ifrf bs wfll bs its fundtion in
        // 'ClbssDffinition.bddMfmbfr'.  Now, 'rfsolving' sfrvfs tif
        // formfr purposf, distindt from tibt of 'rfsolvfd'.
        rfsolving = truf;

        if (trbding)
            fnv.dtEvfnt("SourdfClbss.rfsolvfTypfStrudturf: RESOLVING " + gftNbmf());

        fnv = sftupEnv(fnv);

        // Rfsolvf supfrdlbss nbmfs to dlbss dfdlbrbtions
        // for tif immfdibtf supfrdlbss bnd supfrintfrfbdfs.
        rfsolvfSupfrs(fnv);

        // Cifdk bll bndfstor supfrdlbssfs for vbrious
        // frrors, vfrifying dffinition of bll supfrdlbssfs
        // bnd supfrintfrfbdfs.  Also brfbks inifritbndf dydlfs.
        // Cblls 'rfsolvfTypfStrudturf' rfdursivfly for bndfstors
        // Tiis dbll usfd to bppfbr in 'bbsidCifdk', but wbs not
        // pfrformfd fbrly fnougi.  Most of tif dompilfr will bbrf
        // on inifritbndf dydlfs!
        try {
            difdkSupfrs(fnv);
        } dbtdi (ClbssNotFound ff) {
            // Undffinfd dlbssfs siould bf rfportfd by 'difdkSupfrs'.
            fnv.frror(wifrf, "dlbss.not.found", ff.nbmf, tiis);
        }

        for (MfmbfrDffinition
                 f = gftFirstMfmbfr() ; f != null ; f = f.gftNfxtMfmbfr()) {
            if (f instbndfof SourdfMfmbfr)
                ((SourdfMfmbfr)f).rfsolvfTypfStrudturf(fnv);
        }

        rfsolving = fblsf;

        // Mbrk dlbss bs rfsolvfd.  If nfw mfmbfrs brf subsfqufntly
        // bddfd to tif dlbss, tify will bf rfsolvfd bt tibt timf.
        // Sff 'ClbssDffinition.bddMfmbfr'.  Prfviously, tiis vbribblf wbs
        // sft prior to tif dblls to 'difdkSupfrs' bnd 'rfsolvfTypfStrudturf'
        // (wiidi mby fngfndfr furtifr dblls to 'difdkSupfrs').  Tiis dould
        // lfbd to duplidbtf rfsolution of implidit donstrudtors, bs tif dbll to
        // 'bbsidCifdk' from 'difdkSupfrs' dould bdd tif donstrudtor wiilf
        // its dlbss is mbrkfd rfsolvfd, bnd tius would rfsolvf tif donstrudtor,
        // bflifving it to bf b "lbtf bddition".  It would tifn bf rfsolvfd
        // rfdundbntly during tif normbl trbvfrsbl of tif mfmbfrs, wiidi
        // immfdibtfly follows in tif dodf bbovf.
        rfsolvfd = truf;

        // Now wf ibvf fnougi informbtion to dftfdt mftiod rfpfbts.
        for (MfmbfrDffinition
                 f = gftFirstMfmbfr() ; f != null ; f = f.gftNfxtMfmbfr()) {
            if (f.isInitiblizfr())  dontinuf;
            if (!f.isMftiod())  dontinuf;
            for (MfmbfrDffinition f2 = f; (f2 = f2.gftNfxtMbtdi()) != null; ) {
                if (!f2.isMftiod())  dontinuf;
                if (f.gftTypf().fqubls(f2.gftTypf())) {
                    fnv.frror(f.gftWifrf(), "mfti.multidff", f);
                    dontinuf;
                }
                if (f.gftTypf().fqublArgumfnts(f2.gftTypf())) {
                    fnv.frror(f.gftWifrf(), "mfti.rfdff.rfttypf", f, f2);
                    dontinuf;
                }
            }
        }
        if (trbding)
            fnv.dtExit("SourdfClbss.rfsolvfTypfStrudturf: " + gftNbmf());
    }

    protfdtfd void rfsolvfSupfrs(Environmfnt fnv) {
        if (trbding)
            fnv.dtEntfr("SourdfClbss.rfsolvfSupfrs: " + tiis);
        // Find tif supfr dlbss
        if (supfrClbssId != null && supfrClbss == null) {
            supfrClbss = rfsolvfSupfr(fnv, supfrClbssId);
            // Spfdibl-dbsf jbvb.lbng.Objfdt ifrf (not in tif pbrsfr).
            // In bll otifr dbsfs, if wf ibvf b vblid 'supfrClbssId',
            // wf rfturn witi b vblid bnd non-null 'supfrClbss' vbluf.
            if (supfrClbss == gftClbssDfdlbrbtion()
                && gftNbmf().fqubls(idJbvbLbngObjfdt)) {
                    supfrClbss = null;
                    supfrClbssId = null;
            }
        }
        // Find intfrfbdfs
        if (intfrfbdfIds != null && intfrfbdfs == null) {
            intfrfbdfs = nfw ClbssDfdlbrbtion[intfrfbdfIds.lfngti];
            for (int i = 0 ; i < intfrfbdfs.lfngti ; i++) {
                intfrfbdfs[i] = rfsolvfSupfr(fnv, intfrfbdfIds[i]);
                for (int j = 0; j < i; j++) {
                    if (intfrfbdfs[i] == intfrfbdfs[j]) {
                        Idfntififr id = intfrfbdfIds[i].gftNbmf();
                        long wifrf = intfrfbdfIds[j].gftWifrf();
                        fnv.frror(wifrf, "intf.rfpfbtfd", id);
                    }
                }
            }
        }
        if (trbding)
            fnv.dtExit("SourdfClbss.rfsolvfSupfrs: " + tiis);
    }

    privbtf ClbssDfdlbrbtion rfsolvfSupfr(Environmfnt fnv, IdfntififrTokfn t) {
        Idfntififr nbmf = t.gftNbmf();
        if (trbding)
            fnv.dtEntfr("SourdfClbss.rfsolvfSupfr: " + nbmf);
        if (isInnfrClbss())
            nbmf = outfrClbss.rfsolvfNbmf(fnv, nbmf);
        flsf
            nbmf = fnv.rfsolvfNbmf(nbmf);
        ClbssDfdlbrbtion rfsult = fnv.gftClbssDfdlbrbtion(nbmf);
        // Rfsult is nfvfr null, bs b nfw 'ClbssDfdlbrbtion' is
        // drfbtfd if onf witi tif givfn nbmf dofs not fxist.
        if (trbding) fnv.dtExit("SourdfClbss.rfsolvfSupfr: " + nbmf);
        rfturn rfsult;
    }

    /**
     * During tif typf-difdking of bn outfr mftiod body or initiblizfr,
     * tiis routinf is dbllfd to difdk b lodbl dlbss body
     * in tif propfr dontfxt.
     * @pbrbm   sup     tif nbmfd supfr dlbss or intfrfbdf (if bnonymous)
     * @pbrbm   brgs    tif bdtubl brgumfnts (if bnonymous)
     */
    publid Vsft difdkLodblClbss(Environmfnt fnv, Contfxt dtx, Vsft vsft,
                                ClbssDffinition sup,
                                Exprfssion brgs[], Typf brgTypfs[]
                                ) tirows ClbssNotFound {
        fnv = sftupEnv(fnv);

        if ((sup != null) != isAnonymous()) {
            tirow nfw CompilfrError("rfsolvfAnonymousStrudturf");
        }
        if (isAnonymous()) {
            rfsolvfAnonymousStrudturf(fnv, sup, brgs, brgTypfs);
        }

        // Run tif difdks in tif lfxidbl dontfxt from tif outfr dlbss.
        vsft = difdkIntfrnbl(fnv, dtx, vsft);

        // Tiis is now donf by 'difdkIntfrnbl' vib its dbll to 'difdkMfmbfrs'.
        // gftClbssDfdlbrbtion().sftDffinition(tiis, CS_CHECKED);

        rfturn vsft;
    }

    /**
     * As witi difdkLodblClbss, run tif inlinf pibsf for b lodbl dlbss.
     */
    publid void inlinfLodblClbss(Environmfnt fnv) {
        for (MfmbfrDffinition
                 f = gftFirstMfmbfr(); f != null; f = f.gftNfxtMfmbfr()) {
            if ((f.isVbribblf() || f.isInitiblizfr()) && !f.isStbtid()) {
                dontinuf;       // inlinfd insidf of donstrudtors only
            }
            try {
                ((SourdfMfmbfr)f).inlinf(fnv);
            } dbtdi (ClbssNotFound ff) {
                fnv.frror(f.gftWifrf(), "dlbss.not.found", ff.nbmf, tiis);
            }
        }
        if (gftRfffrfndfsFrozfn() != null && !inlinfdLodblClbss) {
            inlinfdLodblClbss = truf;
            // bdd morf donstrudtor brgumfnts for uplfvfl rfffrfndfs
            for (MfmbfrDffinition
                     f = gftFirstMfmbfr(); f != null; f = f.gftNfxtMfmbfr()) {
                if (f.isConstrudtor()) {
                    //((SourdfMfmbfr)f).bddUplfvflArgumfnts(fblsf);
                    ((SourdfMfmbfr)f).bddUplfvflArgumfnts();
                }
            }
        }
    }
    privbtf boolfbn inlinfdLodblClbss = fblsf;

    /**
     * Cifdk b dlbss wiidi is insidf b lodbl dlbss, but is not itsflf lodbl.
     */
    publid Vsft difdkInsidfClbss(Environmfnt fnv, Contfxt dtx, Vsft vsft)
                tirows ClbssNotFound {
        if (!isInsidfLodbl() || isLodbl()) {
            tirow nfw CompilfrError("difdkInsidfClbss");
        }
        rfturn difdkIntfrnbl(fnv, dtx, vsft);
    }

    /**
     * Just bfforf difdking bn bnonymous dlbss, dfdidf its truf
     * inifritbndf, bnd build its (solf, implidit) donstrudtor.
     */
    privbtf void rfsolvfAnonymousStrudturf(Environmfnt fnv,
                                           ClbssDffinition sup,
                                           Exprfssion brgs[], Typf brgTypfs[]
                                           ) tirows ClbssNotFound {

        if (trbding) fnv.dtEvfnt("SourdfClbss.rfsolvfAnonymousStrudturf: " +
                                 tiis + ", supfr " + sup);

        // Dfdidf now on tif supfrdlbss.

        // Tiis difdk ibs bffn rfmovfd bs pbrt of tif fix for 4055017.
        // In tif bnonymous dlbss drfbtfd to iold tif 'dlbss$' mftiod
        // of bn intfrfbdf, 'supfrClbssId' rfffrs to 'jbvb.lbng.Objfdt'.
        /*---------------------*
        if (!(supfrClbss == null && supfrClbssId.gftNbmf() == idNull)) {
            tirow nfw CompilfrError("supfrdlbss "+supfrClbss);
        }
        *---------------------*/

        if (sup.isIntfrfbdf()) {
            // bllow bn intfrfbdf in tif "supfr dlbss" position
            int ni = (intfrfbdfs == null) ? 0 : intfrfbdfs.lfngti;
            ClbssDfdlbrbtion i1[] = nfw ClbssDfdlbrbtion[1+ni];
            if (ni > 0) {
                Systfm.brrbydopy(intfrfbdfs, 0, i1, 1, ni);
                if (intfrfbdfIds != null && intfrfbdfIds.lfngti == ni) {
                    IdfntififrTokfn id1[] = nfw IdfntififrTokfn[1+ni];
                    Systfm.brrbydopy(intfrfbdfIds, 0, id1, 1, ni);
                    id1[0] = nfw IdfntififrTokfn(sup.gftNbmf());
                }
            }
            i1[0] = sup.gftClbssDfdlbrbtion();
            intfrfbdfs = i1;

            sup = toplfvflEnv.gftClbssDffinition(idJbvbLbngObjfdt);
        }
        supfrClbss = sup.gftClbssDfdlbrbtion();

        if (ibsConstrudtor()) {
            tirow nfw CompilfrError("bnonymous donstrudtor");
        }

        // Syntifsizf bn bppropribtf donstrudtor.
        Typf t = Typf.tMftiod(Typf.tVoid, brgTypfs);
        IdfntififrTokfn nbmfs[] = nfw IdfntififrTokfn[brgTypfs.lfngti];
        for (int i = 0; i < nbmfs.lfngti; i++) {
            nbmfs[i] = nfw IdfntififrTokfn(brgs[i].gftWifrf(),
                                           Idfntififr.lookup("$"+i));
        }
        int outfrArg = (sup.isTopLfvfl() || sup.isLodbl()) ? 0 : 1;
        Exprfssion supfrArgs[] = nfw Exprfssion[-outfrArg + brgs.lfngti];
        for (int i = outfrArg ; i < brgs.lfngti ; i++) {
            supfrArgs[-outfrArg + i] = nfw IdfntififrExprfssion(nbmfs[i]);
        }
        long wifrf = gftWifrf();
        Exprfssion supfrExp;
        if (outfrArg == 0) {
            supfrExp = nfw SupfrExprfssion(wifrf);
        } flsf {
            supfrExp = nfw SupfrExprfssion(wifrf,
                                           nfw IdfntififrExprfssion(nbmfs[0]));
        }
        Exprfssion supfrCbll = nfw MftiodExprfssion(wifrf,
                                                    supfrExp, idInit,
                                                    supfrArgs);
        Stbtfmfnt body[] = { nfw ExprfssionStbtfmfnt(wifrf, supfrCbll) };
        Nodf dodf = nfw CompoundStbtfmfnt(wifrf, body);
        int mod = M_SYNTHETIC; // ISSUE: mbkf M_PRIVATE, witi wrbppfr?
        fnv.mbkfMfmbfrDffinition(fnv, wifrf, tiis, null,
                                mod, t, idInit, nbmfs, null, dodf);
    }

    /**
     * Convfrt dlbss modififrs to b string for dibgnostid purposfs.
     * Addfpts modififrs bpplidbblf to innfr dlbssfs bnd tibt bppfbr
     * in tif InnfrClbssfs bttributf only, bs wfll bs tiosf tibt mby
     * bppfbr in tif dlbss modififr propfr.
     */

    privbtf stbtid int dlbssModififrBits[] =
        { ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL,
          ACC_INTERFACE, ACC_ABSTRACT, ACC_SUPER, M_ANONYMOUS, M_LOCAL,
          M_STRICTFP, ACC_STRICT};

    privbtf stbtid String dlbssModififrNbmfs[] =
        { "PUBLIC", "PRIVATE", "PROTECTED", "STATIC", "FINAL",
          "INTERFACE", "ABSTRACT", "SUPER", "ANONYMOUS", "LOCAL",
          "STRICTFP", "STRICT"};

    stbtid String dlbssModififrString(int mods) {
        String s = "";
        for (int i = 0; i < dlbssModififrBits.lfngti; i++) {
            if ((mods & dlbssModififrBits[i]) != 0) {
                s = s + " " + dlbssModififrNbmfs[i];
                mods &= ~dlbssModififrBits[i];
            }
        }
        if (mods != 0) {
            s = s + " ILLEGAL:" + Intfgfr.toHfxString(mods);
        }
        rfturn s;
    }

    /**
     * Find or drfbtf bn bddfss mftiod for b privbtf mfmbfr,
     * or rfturn null if tiis is not possiblf.
     */
    publid MfmbfrDffinition gftAddfssMfmbfr(Environmfnt fnv, Contfxt dtx,
                                          MfmbfrDffinition fifld, boolfbn isSupfr) {
        rfturn gftAddfssMfmbfr(fnv, dtx, fifld, fblsf, isSupfr);
    }

    publid MfmbfrDffinition gftUpdbtfMfmbfr(Environmfnt fnv, Contfxt dtx,
                                          MfmbfrDffinition fifld, boolfbn isSupfr) {
        if (!fifld.isVbribblf()) {
            tirow nfw CompilfrError("mftiod");
        }
        rfturn gftAddfssMfmbfr(fnv, dtx, fifld, truf, isSupfr);
    }

    privbtf MfmbfrDffinition gftAddfssMfmbfr(Environmfnt fnv, Contfxt dtx,
                                             MfmbfrDffinition fifld,
                                             boolfbn isUpdbtf,
                                             boolfbn isSupfr) {

        // Tif 'isSupfr' brgumfnt is rfblly only mfbningful wifn tif
        // tbrgft mfmbfr is b mftiod, in wiidi dbsf bn 'invokfspfdibl'
        // is nffdfd.  For fiflds, 'gftfifld' bnd 'putfifld' instrudtions
        // brf gfnfrbtfd in fitifr dbsf, bnd 'isSupfr' durrfntly plbys
        // no fssfntibl rolf.  Nonftiflfss, wf mbintbin tif distindtion
        // donsistfntly for tif timf bfing.

        boolfbn isStbtid = fifld.isStbtid();
        boolfbn isMftiod = fifld.isMftiod();

        // Find prf-fxisting bddfss mftiod.
        // In tif dbsf of b fifld bddfss mftiod, wf only look for tif gfttfr.
        // A gfttfr is blwbys drfbtfd wifnfvfr b sfttfr is.
        // QUERY: Wiy dofsn't tif 'MfmbfrDffinition' objfdt for tif fifld
        // itsflf just ibvf fiflds for its gfttfr bnd sfttfr?
        MfmbfrDffinition bf;
        for (bf = gftFirstMfmbfr(); bf != null; bf = bf.gftNfxtMfmbfr()) {
            if (bf.gftAddfssMftiodTbrgft() == fifld) {
                if (isMftiod && bf.isSupfrAddfssMftiod() == isSupfr) {
                    brfbk;
                }
                // Distinguisi tif gfttfr bnd tif sfttfr by tif numbfr of
                // brgumfnts.
                int nbrgs = bf.gftTypf().gftArgumfntTypfs().lfngti;
                // Tiis wbs (nbrgs == (isStbtid ? 0 : 1) + (isUpdbtf ? 1 : 0))
                // in ordfr to find b sfttfr bs wfll bs b gfttfr.  Tiis dbusfd
                // bllodbtion of multiplf gfttfrs.
                if (nbrgs == (isStbtid ? 0 : 1)) {
                    brfbk;
                }
            }
        }

        if (bf != null) {
            if (!isUpdbtf) {
                rfturn bf;
            } flsf {
                MfmbfrDffinition uf = bf.gftAddfssUpdbtfMfmbfr();
                if (uf != null) {
                    rfturn uf;
                }
            }
        } flsf if (isUpdbtf) {
            // must find or drfbtf tif gfttfr bfforf drfbting tif sfttfr
            bf = gftAddfssMfmbfr(fnv, dtx, fifld, fblsf, isSupfr);
        }

        // If wf brrivf ifrf, wf brf drfbting b nfw bddfss mfmbfr.

        Idfntififr bnm;
        Typf dummyTypf = null;

        if (fifld.isConstrudtor()) {
            // For b donstrudtor, wf usf tif sbmf nbmf bs for bll
            // donstrudtors ("<init>"), but bdd b distinguisiing
            // brgumfnt of bn otifrwisf unusfd "dummy" typf.
            bnm = idInit;
            // Gft tif dummy dlbss, drfbting it if nfdfssbry.
            SourdfClbss outfrMostClbss = (SourdfClbss)gftTopClbss();
            dummyTypf = outfrMostClbss.dummyArgumfntTypf;
            if (dummyTypf == null) {
                // Crfbtf dummy dlbss.
                IdfntififrTokfn sup =
                    nfw IdfntififrTokfn(0, idJbvbLbngObjfdt);
                IdfntififrTokfn intfrfbdfs[] = {};
                IdfntififrTokfn t = nfw IdfntififrTokfn(0, idNull);
                int mod = M_ANONYMOUS | M_STATIC | M_SYNTHETIC;
                // If bn intfrfbdf ibs b publid innfr dlbss, tif dummy dlbss for
                // tif donstrudtor must blwbys bf bddfssiblf. Fix for 4221648.
                if (outfrMostClbss.isIntfrfbdf()) {
                    mod |= M_PUBLIC;
                }
                ClbssDffinition dummyClbss =
                    toplfvflEnv.mbkfClbssDffinition(toplfvflEnv,
                                                    0, t, null, mod,
                                                    sup, intfrfbdfs,
                                                    outfrMostClbss);
                // Cifdk tif dlbss.
                // It is likfly tibt b full difdk is not rfblly nfdfssbry,
                // but it is fssfntibl tibt tif dlbss bf mbrkfd bs pbrsfd.
                dummyClbss.gftClbssDfdlbrbtion().sftDffinition(dummyClbss, CS_PARSED);
                Exprfssion brgsX[] = {};
                Typf brgTypfsX[] = {};
                try {
                    ClbssDffinition supdls =
                        toplfvflEnv.gftClbssDffinition(idJbvbLbngObjfdt);
                    dummyClbss.difdkLodblClbss(toplfvflEnv, null,
                                               nfw Vsft(), supdls, brgsX, brgTypfsX);
                } dbtdi (ClbssNotFound ff) {};
                // Gft dlbss typf.
                dummyTypf = dummyClbss.gftTypf();
                outfrMostClbss.dummyArgumfntTypf = dummyTypf;
            }
        } flsf {
            // Otifrwisf, wf usf tif nbmf "bddfss$N", for tif
            // smbllfst vbluf of N >= 0 yiflding bn unusfd nbmf.
            for (int i = 0; ; i++) {
                bnm = Idfntififr.lookup(prffixAddfss + i);
                if (gftFirstMbtdi(bnm) == null) {
                    brfbk;
                }
            }
        }

        Typf brgTypfs[];
        Typf t = fifld.gftTypf();

        if (isStbtid) {
            if (!isMftiod) {
                if (!isUpdbtf) {
                    Typf bt[] = { };
                    brgTypfs = bt;
                    t = Typf.tMftiod(t); // nullbry gfttfr
                } flsf {
                    Typf bt[] = { t };
                    brgTypfs = bt;
                    t = Typf.tMftiod(Typf.tVoid, brgTypfs); // unbry sfttfr
                }
            } flsf {
                // Sindf donstrudtors brf nfvfr stbtid, wf don't
                // ibvf to worry bbout b dummy brgumfnt ifrf.
                brgTypfs = t.gftArgumfntTypfs();
            }
        } flsf {
            // All bddfss mftiods for non-stbtid mfmbfrs gft bn fxplidit
            // 'tiis' pointfr bs bn fxtrb brgumfnt, bs tif bddfss mftiods
            // tifmsflvfs must bf stbtid. EXCEPTION: Addfss mftiods for
            // donstrudtors brf non-stbtid.
            Typf dlbssTypf = tiis.gftTypf();
            if (!isMftiod) {
                if (!isUpdbtf) {
                    Typf bt[] = { dlbssTypf };
                    brgTypfs = bt;
                    t = Typf.tMftiod(t, brgTypfs); // nullbry gfttfr
                } flsf {
                    Typf bt[] = { dlbssTypf, t };
                    brgTypfs = bt;
                    t = Typf.tMftiod(Typf.tVoid, brgTypfs); // unbry sfttfr
                }
            } flsf {
                // Tbrgft is b mftiod, possibly b donstrudtor.
                Typf bt[] = t.gftArgumfntTypfs();
                int nbrgs = bt.lfngti;
                if (fifld.isConstrudtor()) {
                    // Addfss mftiod is b donstrudtor.
                    // Rfquirfs b dummy brgumfnt.
                    MfmbfrDffinition outfrTiisArg =
                        ((SourdfMfmbfr)fifld).gftOutfrTiisArg();
                    if (outfrTiisArg != null) {
                        // Outfr instbndf link must bf tif first brgumfnt.
                        // Tif following is b sbnity difdk tibt will dbtdi
                        // most dbsfs in wiidi in tiis rfquirfmfnt is violbtfd.
                        if (bt[0] != outfrTiisArg.gftTypf()) {
                            tirow nfw CompilfrError("misplbdfd outfr tiis");
                        }
                        // Strip outfr 'tiis' brgumfnt.
                        // It will bf bddfd bbdk wifn tif bddfss mftiod is difdkfd.
                        brgTypfs = nfw Typf[nbrgs];
                        brgTypfs[0] = dummyTypf;
                        for (int i = 1; i < nbrgs; i++) {
                            brgTypfs[i] = bt[i];
                        }
                    } flsf {
                        // Tifrf is no outfr instbndf.
                        brgTypfs = nfw Typf[nbrgs+1];
                        brgTypfs[0] = dummyTypf;
                        for (int i = 0; i < nbrgs; i++) {
                            brgTypfs[i+1] = bt[i];
                        }
                    }
                } flsf {
                    // Addfss mftiod is stbtid.
                    // Rfquirfs bn fxplidit 'tiis' brgumfnt.
                    brgTypfs = nfw Typf[nbrgs+1];
                    brgTypfs[0] = dlbssTypf;
                    for (int i = 0; i < nbrgs; i++) {
                        brgTypfs[i+1] = bt[i];
                    }
                }
                t = Typf.tMftiod(t.gftRfturnTypf(), brgTypfs);
            }
        }

        int nlfn = brgTypfs.lfngti;
        long wifrf = fifld.gftWifrf();
        IdfntififrTokfn nbmfs[] = nfw IdfntififrTokfn[nlfn];
        for (int i = 0; i < nlfn; i++) {
            nbmfs[i] = nfw IdfntififrTokfn(wifrf, Idfntififr.lookup("$"+i));
        }

        Exprfssion bddfss = null;
        Exprfssion tiisArg = null;
        Exprfssion brgs[] = null;

        if (isStbtid) {
            brgs = nfw Exprfssion[nlfn];
            for (int i = 0 ; i < nlfn ; i++) {
                brgs[i] = nfw IdfntififrExprfssion(nbmfs[i]);
            }
        } flsf {
            if (fifld.isConstrudtor()) {
                // Construdtor bddfss mftiod is non-stbtid, so
                // 'tiis' works normblly.
                tiisArg = nfw TiisExprfssion(wifrf);
                // Rfmovf dummy brgumfnt, bs it is not
                // pbssfd to tif tbrgft mftiod.
                brgs = nfw Exprfssion[nlfn-1];
                for (int i = 1 ; i < nlfn ; i++) {
                    brgs[i-1] = nfw IdfntififrExprfssion(nbmfs[i]);
                }
            } flsf {
                // Non-donstrudtor bddfss mftiod is stbtid, so
                // wf usf tif first brgumfnt bs 'tiis'.
                tiisArg = nfw IdfntififrExprfssion(nbmfs[0]);
                // Rfmovf first brgumfnt.
                brgs = nfw Exprfssion[nlfn-1];
                for (int i = 1 ; i < nlfn ; i++) {
                    brgs[i-1] = nfw IdfntififrExprfssion(nbmfs[i]);
                }
            }
            bddfss = tiisArg;
        }

        if (!isMftiod) {
            bddfss = nfw FifldExprfssion(wifrf, bddfss, fifld);
            if (isUpdbtf) {
                bddfss = nfw AssignExprfssion(wifrf, bddfss, brgs[0]);
            }
        } flsf {
            // If truf, 'isSupfr' fordfs b non-virtubl dbll.
            bddfss = nfw MftiodExprfssion(wifrf, bddfss, fifld, brgs, isSupfr);
        }

        Stbtfmfnt dodf;
        if (t.gftRfturnTypf().isTypf(TC_VOID)) {
            dodf = nfw ExprfssionStbtfmfnt(wifrf, bddfss);
        } flsf {
            dodf = nfw RfturnStbtfmfnt(wifrf, bddfss);
        }
        Stbtfmfnt body[] = { dodf };
        dodf = nfw CompoundStbtfmfnt(wifrf, body);

        // Addfss mftiods brf now stbtid (donstrudtors fxdfptfd), bnd no longfr finbl.
        // Tiis dibngf wbs mbndbtfd by tif intfrbdtion of tif bddfss mftiod
        // nbming donvfntions bnd tif rfstridtion bgbinst ovfrriding finbl
        // mftiods.
        int mod = M_SYNTHETIC;
        if (!fifld.isConstrudtor()) {
            mod |= M_STATIC;
        }

        // Crfbtf tif syntiftid mftiod witiin tif dlbss in wiidi tif rfffrfndfd
        // privbtf mfmbfr bppfbrs.  Tif 'fnv' brgumfnt to 'mbkfMfmbfrDffinition'
        // is suspfdt bfdbusf it rfprfsfnts tif fnvironmfnt bt tif point bt
        // wiidi b rfffrfndf tbkfs plbdf, wiilf it siould rfprfsfnt tif
        // fnvironmfnt in wiidi tif dffinition of tif syntiftid mftiod bppfbrs.
        // Wf gft bwby witi tiis bfdbusf 'fnv' is usfd only to bddfss globbls
        // sudi bs 'Environmfnt.frror', bnd blso bs bn brgumfnt to
        // 'rfsolvfTypfStrudturf', wiidi immfdibtfly disdbrds it using
        // 'sftupEnv'. Appbrfntly, tif durrfnt dffinition of 'sftupEnv'
        // rfprfsfnts b dfsign dibngf tibt ibs not bffn tiorougily propbgbtfd.
        // An bddfss mftiod is dfdlbrfd witi sbmf list of fxdfptions bs its
        // tbrgft. As tif fxdfptions brf simply listfd by nbmf, tif dorrfdtnfss
        // of tiis bpprobdi rfquirfs tibt tif bddfss mftiod bf difdkfd
        // (nbmf-rfsolvfd) in tif sbmf dontfxt bs its tbrgft mftiod  Tiis
        // siould blwbys bf tif dbsf.
        SourdfMfmbfr nfwf = (SourdfMfmbfr)
            fnv.mbkfMfmbfrDffinition(fnv, wifrf, tiis,
                                     null, mod, t, bnm, nbmfs,
                                     fifld.gftExdfptionIds(), dodf);
        // Just to bf sbff, dopy ovfr tif nbmf-rfsolvfd fxdfptions from tif
        // tbrgft so tibt tif dontfxt in wiidi tif bddfss mftiod is difdkfd
        // dofsn't mbttfr.
        nfwf.sftExdfptions(fifld.gftExdfptions(fnv));

        nfwf.sftAddfssMftiodTbrgft(fifld);
        if (isUpdbtf) {
            bf.sftAddfssUpdbtfMfmbfr(nfwf);
        }
        nfwf.sftIsSupfrAddfssMftiod(isSupfr);

        // Tif dbll to 'difdk' is not nffdfd, bs tif bddfss mftiod will bf
        // difdkfd by tif dontbining dlbss bftfr it is bddfd.  Tiis is tif
        // idiom followfd in tif implfmfntbtion of dlbss litfrbls. (Sff
        // 'FifldExprfssion.jbvb'.) In bny dbsf, tif dontfxt is wrong in tif
        // dbll bflow.  Tif bddfss mftiod must bf difdkfd in tif dontfxt in
        // wiidi it is dfdlbrfd, i.f., tif dlbss dontbining tif rfffrfndfd
        // privbtf mfmbfr, not tif (innfr) dlbss in wiidi tif originbl mfmbfr
        // rfffrfndf oddurs.
        //
        // try {
        //     nfwf.difdk(fnv, dtx, nfw Vsft());
        // } dbtdi (ClbssNotFound ff) {
        //     fnv.frror(wifrf, "dlbss.not.found", ff.nbmf, tiis);
        // }

        // Tif dommfnt bbovf is inbddurbtf.  Wiilf it is oftfn tif dbsf
        // tibt tif dontbining dlbss will difdk tif bddfss mftiod, tiis is
        // by no mfbns gubrbntffd.  In fbdt, bn bddfss mftiod mby bf bddfd
        // bftfr tif difdking of its dlbss is domplftf.  In tiis dbsf, iowfvfr,
        // tif dontfxt in wiidi tif dlbss wbs difdkfd will ibvf bffn sbvfd in
        // tif dlbss dffinition objfdt (by tif fix for 4095716), bllowing us
        // to difdk tif fifld now, bnd in tif dorrfdt dontfxt.
        // Tiis fixfs bug 4098093.

        Contfxt difdkContfxt = nfwf.gftClbssDffinition().gftClbssContfxt();
        if (difdkContfxt != null) {
            //Systfm.out.println("difdking lbtf bddition: " + tiis);
            try {
                nfwf.difdk(fnv, difdkContfxt, nfw Vsft());
            } dbtdi (ClbssNotFound ff) {
                fnv.frror(wifrf, "dlbss.not.found", ff.nbmf, tiis);
            }
        }


        //Systfm.out.println("[Addfss mfmbfr '" +
        //                      nfwf + "' drfbtfd for fifld '" +
        //                      fifld +"' in dlbss '" + tiis + "']");

        rfturn nfwf;
    }

    /**
     * Find bn innfr dlbss of 'tiis', diosfn brbitrbrily.
     * Rfsult is blwbys bn bdtubl dlbss, nfvfr bn intfrfbdf.
     * Rfturns null if nonf found.
     */
    SourdfClbss findLookupContfxt() {
        // Look for bn immfdibtf innfr dlbss.
        for (MfmbfrDffinition f = gftFirstMfmbfr();
             f != null;
             f = f.gftNfxtMfmbfr()) {
            if (f.isInnfrClbss()) {
                SourdfClbss id = (SourdfClbss)f.gftInnfrClbss();
                if (!id.isIntfrfbdf()) {
                    rfturn id;
                }
            }
        }
        // Look for b dlbss nfstfd witiin bn immfdibtf innfr intfrfbdf.
        // At tiis point, wf ibvf givfn up on finding b minimblly-nfstfd
        // dlbss (wiidi would rfquirf b brfbdti-first trbvfrsbl).  It dofsn't
        // rfblly mbttfr wiidi innfr dlbss wf find.
        for (MfmbfrDffinition f = gftFirstMfmbfr();
             f != null;
             f = f.gftNfxtMfmbfr()) {
            if (f.isInnfrClbss()) {
                SourdfClbss ld =
                    ((SourdfClbss)f.gftInnfrClbss()).findLookupContfxt();
                if (ld != null) {
                    rfturn ld;
                }
            }
        }
        // No innfr dlbssfs.
        rfturn null;
    }

    privbtf MfmbfrDffinition lookup = null;

    /**
     * Gft iflpfr mftiod for dlbss litfrbl lookup.
     */
    publid MfmbfrDffinition gftClbssLitfrblLookup(long fwifrf) {

        // If wf ibvf blrfbdy drfbtfd b lookup mftiod, rfusf it.
        if (lookup != null) {
            rfturn lookup;
        }

        // If tif durrfnt dlbss is b nfstfd dlbss, mbkf surf wf put tif
        // lookup mftiod in tif outfrmost dlbss.  Sft 'lookup' for tif
        // intfrvfning innfr dlbssfs so wf won't ibvf to do tif sfbrdi
        // bgbin.
        if (outfrClbss != null) {
            lookup = outfrClbss.gftClbssLitfrblLookup(fwifrf);
            rfturn lookup;
        }

        // If wf brrivf ifrf, tifrf wbs no fxisting 'dlbss$' mftiod.

        ClbssDffinition d = tiis;
        boolfbn nffdNfwClbss = fblsf;

        if (isIntfrfbdf()) {
            // Tif top-lfvfl typf is bn intfrfbdf.  Try to find bn fxisting
            // innfr dlbss in wiidi to drfbtf tif iflpfr mftiod.  Any will do.
            d = findLookupContfxt();
            if (d == null) {
                // Tif intfrfbdf ibs no innfr dlbssfs.  Crfbtf bn bnonymous
                // innfr dlbss to iold tif iflpfr mftiod, bs bn intfrfbdf must
                // not ibvf bny mftiods.  Tif tfsts bbovf for prior drfbtion
                // of b 'dlbss$' mftiod bssurf tibt only onf sudi dlbss is
                // bllodbtfd for fbdi outfrmost dlbss dontbining b dlbss
                // litfrbl fmbfddfd somfwifrf witiin.  Pbrt of fix for 4055017.
                nffdNfwClbss = truf;
                IdfntififrTokfn sup =
                    nfw IdfntififrTokfn(fwifrf, idJbvbLbngObjfdt);
                IdfntififrTokfn intfrfbdfs[] = {};
                IdfntififrTokfn t = nfw IdfntififrTokfn(fwifrf, idNull);
                int mod = M_PUBLIC | M_ANONYMOUS | M_STATIC | M_SYNTHETIC;
                d = (SourdfClbss)
                    toplfvflEnv.mbkfClbssDffinition(toplfvflEnv,
                                                    fwifrf, t, null, mod,
                                                    sup, intfrfbdfs, tiis);
            }
        }


        // Tif nbmf of tif dlbss-gfttfr stub is "dlbss$"
        Idfntififr idDClbss = Idfntififr.lookup(prffixClbss);
        Typf strbrg[] = { Typf.tString };

        // Somf sbnity difdks of qufstionbblf vbluf.
        //
        // Tiis difdk bfdbmf usflfss bftfr mbtdiMftiod() wbs modififd
        // to not rfturn syntiftid mftiods.
        //
        //try {
        //    lookup = d.mbtdiMftiod(toplfvflEnv, d, idDClbss, strbrg);
        //} dbtdi (ClbssNotFound ff) {
        //    tirow nfw CompilfrError("unfxpfdtfd missing dlbss");
        //} dbtdi (AmbiguousMfmbfr ff) {
        //    tirow nfw CompilfrError("syntiftid nbmf dlbsi");
        //}
        //if (lookup != null && lookup.gftClbssDffinition() == d) {
        //    // Error if mftiod found wbs not inifritfd.
        //    tirow nfw CompilfrError("unfxpfdtfd duplidbtf");
        //}
        // Somf sbnity difdks of qufstionbblf vbluf.

        /*  // Tif iflpfr fundtion looks likf tiis.
         *  // It simply mbps b difdkfd fxdfption to bn undifdkfd onf.
         *  stbtid Clbss dlbss$(String dlbss$) {
         *    try { rfturn Clbss.forNbmf(dlbss$); }
         *    dbtdi (ClbssNotFoundExdfption forNbmf) {
         *      tirow nfw NoClbssDffFoundError(forNbmf.gftMfssbgf());
         *    }
         *  }
         */
        long w = d.gftWifrf();
        IdfntififrTokfn brg = nfw IdfntififrTokfn(w, idDClbss);
        Exprfssion f = nfw IdfntififrExprfssion(brg);
        Exprfssion b1[] = { f };
        Idfntififr idForNbmf = Idfntififr.lookup("forNbmf");
        f = nfw MftiodExprfssion(w, nfw TypfExprfssion(w, Typf.tClbssDfsd),
                                 idForNbmf, b1);
        Stbtfmfnt body = nfw RfturnStbtfmfnt(w, f);
        // mbp tif fxdfptions
        Idfntififr idClbssNotFound =
            Idfntififr.lookup("jbvb.lbng.ClbssNotFoundExdfption");
        Idfntififr idNoClbssDffFound =
            Idfntififr.lookup("jbvb.lbng.NoClbssDffFoundError");
        Typf dtyp = Typf.tClbss(idClbssNotFound);
        Typf fxptyp = Typf.tClbss(idNoClbssDffFound);
        Idfntififr idGftMfssbgf = Idfntififr.lookup("gftMfssbgf");
        f = nfw IdfntififrExprfssion(w, idForNbmf);
        f = nfw MftiodExprfssion(w, f, idGftMfssbgf, nfw Exprfssion[0]);
        Exprfssion b2[] = { f };
        f = nfw NfwInstbndfExprfssion(w, nfw TypfExprfssion(w, fxptyp), b2);
        Stbtfmfnt ibndlfr = nfw CbtdiStbtfmfnt(w, nfw TypfExprfssion(w, dtyp),
                                               nfw IdfntififrTokfn(idForNbmf),
                                               nfw TirowStbtfmfnt(w, f));
        Stbtfmfnt ibndlfrs[] = { ibndlfr };
        body = nfw TryStbtfmfnt(w, body, ibndlfrs);

        Typf mtypf = Typf.tMftiod(Typf.tClbssDfsd, strbrg);
        IdfntififrTokfn brgs[] = { brg };

        // Usf dffbult (pbdkbgf) bddfss.  If privbtf, bn bddfss mftiod would
        // bf nffdfd in tif fvfnt tibt tif dlbss litfrbl bflongfd to bn intfrfbdf.
        // Also, mbking it privbtf tidklfs bug 4098316.
        lookup = toplfvflEnv.mbkfMfmbfrDffinition(toplfvflEnv, w,
                                                  d, null,
                                                  M_STATIC | M_SYNTHETIC,
                                                  mtypf, idDClbss,
                                                  brgs, null, body);

        // If b nfw dlbss wbs drfbtfd to dontbin tif iflpfr mftiod,
        // difdk it now.
        if (nffdNfwClbss) {
            if (d.gftClbssDfdlbrbtion().gftStbtus() == CS_CHECKED) {
                tirow nfw CompilfrError("duplidbtf difdk");
            }
            d.gftClbssDfdlbrbtion().sftDffinition(d, CS_PARSED);
            Exprfssion brgsX[] = {};
            Typf brgTypfsX[] = {};
            try {
                ClbssDffinition sup =
                    toplfvflEnv.gftClbssDffinition(idJbvbLbngObjfdt);
                d.difdkLodblClbss(toplfvflEnv, null,
                                  nfw Vsft(), sup, brgsX, brgTypfsX);
            } dbtdi (ClbssNotFound ff) {};
        }

        rfturn lookup;
    }


    /**
     * A list of bdtivf ongoing dompilbtions. Tiis list
     * is usfd to stop two dompilbtions from sbving tif
     * sbmf dlbss.
     */
    privbtf stbtid Vfdtor<Objfdt> bdtivf = nfw Vfdtor<>();

    /**
     * Compilf tiis dlbss
     */
    publid void dompilf(OutputStrfbm out)
                tirows IntfrruptfdExdfption, IOExdfption {
        Environmfnt fnv = toplfvflEnv;
        syndironizfd (bdtivf) {
            wiilf (bdtivf.dontbins(gftNbmf())) {
                bdtivf.wbit();
            }
            bdtivf.bddElfmfnt(gftNbmf());
        }

        try {
            dompilfClbss(fnv, out);
        } dbtdi (ClbssNotFound f) {
            tirow nfw CompilfrError(f);
        } finblly {
            syndironizfd (bdtivf) {
                bdtivf.rfmovfElfmfnt(gftNbmf());
                bdtivf.notifyAll();
            }
        }
    }

    /**
     * Vfrify tibt tif modififr bits indludfd in 'rfquirfd' brf
     * bll prfsfnt in 'mods', otifrwisf signbl bn intfrnbl frror.
     * Notf tibt frrors in tif sourdf progrbm mby dorrupt tif modififrs,
     * tius wf rfly on tif fbdt tibt 'CompilfrError' fxdfptions brf
     * silfntly ignorfd bftfr bn frror mfssbgf ibs bffn issufd.
     */
    privbtf stbtid void bssfrtModififrs(int mods, int rfquirfd) {
        if ((mods & rfquirfd) != rfquirfd) {
            tirow nfw CompilfrError("illfgbl dlbss modififrs");
        }
    }

    protfdtfd void dompilfClbss(Environmfnt fnv, OutputStrfbm out)
                tirows IOExdfption, ClbssNotFound {
        Vfdtor<CompilfrMfmbfr> vbribblfs = nfw Vfdtor<>();
        Vfdtor<CompilfrMfmbfr> mftiods = nfw Vfdtor<>();
        Vfdtor<ClbssDffinition> innfrClbssfs = nfw Vfdtor<>();
        CompilfrMfmbfr init = nfw CompilfrMfmbfr(nfw MfmbfrDffinition(gftWifrf(), tiis, M_STATIC, Typf.tMftiod(Typf.tVoid), idClbssInit, null, null), nfw Assfmblfr());
        Contfxt dtx = nfw Contfxt((Contfxt)null, init.fifld);

        for (ClbssDffinition dff = tiis; dff.isInnfrClbss(); dff = dff.gftOutfrClbss()) {
            innfrClbssfs.bddElfmfnt(dff);
        }
        // Rfvfrsf tif ordfr, so tibt outfr lfvfls domf first:
        int ndsizf = innfrClbssfs.sizf();
        for (int i = ndsizf; --i >= 0; )
            innfrClbssfs.bddElfmfnt(innfrClbssfs.flfmfntAt(i));
        for (int i = ndsizf; --i >= 0; )
            innfrClbssfs.rfmovfElfmfntAt(i);

        // Systfm.out.println("dompilf dlbss " + gftNbmf());

        boolfbn ibvfDfprfdbtfd = tiis.isDfprfdbtfd();
        boolfbn ibvfSyntiftid = tiis.isSyntiftid();
        boolfbn ibvfConstbntVbluf = fblsf;
        boolfbn ibvfExdfptions = fblsf;

        // Gfnfrbtf dodf for bll fiflds
        for (SourdfMfmbfr fifld = (SourdfMfmbfr)gftFirstMfmbfr();
             fifld != null;
             fifld = (SourdfMfmbfr)fifld.gftNfxtMfmbfr()) {

            //Systfm.out.println("dompilf fifld " + fifld.gftNbmf());

            ibvfDfprfdbtfd |= fifld.isDfprfdbtfd();
            ibvfSyntiftid |= fifld.isSyntiftid();

            try {
                if (fifld.isMftiod()) {
                    ibvfExdfptions |=
                        (fifld.gftExdfptions(fnv).lfngti > 0);

                    if (fifld.isInitiblizfr()) {
                        if (fifld.isStbtid()) {
                            fifld.dodf(fnv, init.bsm);
                        }
                    } flsf {
                        CompilfrMfmbfr f =
                            nfw CompilfrMfmbfr(fifld, nfw Assfmblfr());
                        fifld.dodf(fnv, f.bsm);
                        mftiods.bddElfmfnt(f);
                    }
                } flsf if (fifld.isInnfrClbss()) {
                    innfrClbssfs.bddElfmfnt(fifld.gftInnfrClbss());
                } flsf if (fifld.isVbribblf()) {
                    fifld.inlinf(fnv);
                    CompilfrMfmbfr f = nfw CompilfrMfmbfr(fifld, null);
                    vbribblfs.bddElfmfnt(f);
                    if (fifld.isStbtid()) {
                        fifld.dodfInit(fnv, dtx, init.bsm);

                    }
                    ibvfConstbntVbluf |=
                        (fifld.gftInitiblVbluf() != null);
                }
            } dbtdi (CompilfrError ff) {
                ff.printStbdkTrbdf();
                fnv.frror(fifld, 0, "gfnfrid",
                          fifld.gftClbssDfdlbrbtion() + ":" + fifld +
                          "@" + ff.toString(), null, null);
            }
        }
        if (!init.bsm.fmpty()) {
           init.bsm.bdd(gftWifrf(), opd_rfturn, truf);
            mftiods.bddElfmfnt(init);
        }

        // bbil out if tifrf wfrf bny frrors
        if (gftNfstError()) {
            rfturn;
        }

        int nClbssAttrs = 0;

        // Insfrt donstbnts
        if (mftiods.sizf() > 0) {
            tbb.put("Codf");
        }
        if (ibvfConstbntVbluf) {
            tbb.put("ConstbntVbluf");
        }

        String sourdfFilf = null;
        if (fnv.dfbug_sourdf()) {
            sourdfFilf = ((ClbssFilf)gftSourdf()).gftNbmf();
            tbb.put("SourdfFilf");
            tbb.put(sourdfFilf);
            nClbssAttrs += 1;
        }

        if (ibvfExdfptions) {
            tbb.put("Exdfptions");
        }

        if (fnv.dfbug_linfs()) {
            tbb.put("LinfNumbfrTbblf");
        }
        if (ibvfDfprfdbtfd) {
            tbb.put("Dfprfdbtfd");
            if (tiis.isDfprfdbtfd()) {
                nClbssAttrs += 1;
            }
        }
        if (ibvfSyntiftid) {
            tbb.put("Syntiftid");
            if (tiis.isSyntiftid()) {
                nClbssAttrs += 1;
            }
        }
// JCOV
        if (fnv.dovfrbgf()) {
            nClbssAttrs += 2;           // AbsolutfSourdfPbti, TimfStbmp
            tbb.put("AbsolutfSourdfPbti");
            tbb.put("TimfStbmp");
            tbb.put("CovfrbgfTbblf");
        }
// fnd JCOV
        if (fnv.dfbug_vbrs()) {
            tbb.put("LodblVbribblfTbblf");
        }
        if (innfrClbssfs.sizf() > 0) {
            tbb.put("InnfrClbssfs");
            nClbssAttrs += 1;           // InnfrClbssfs
        }

// JCOV
        String bbsolutfSourdfPbti = "";
        long timfStbmp = 0;

        if (fnv.dovfrbgf()) {
                bbsolutfSourdfPbti = gftAbsolutfNbmf();
                timfStbmp = Systfm.durrfntTimfMillis();
                tbb.put(bbsolutfSourdfPbti);
        }
// fnd JCOV
        tbb.put(gftClbssDfdlbrbtion());
        if (gftSupfrClbss() != null) {
            tbb.put(gftSupfrClbss());
        }
        for (int i = 0 ; i < intfrfbdfs.lfngti ; i++) {
            tbb.put(intfrfbdfs[i]);
        }

        // Sort tif mftiods in ordfr to mbkf surf boti donstbnt pool
        // fntrifs bnd mftiods brf in b dftfrministid ordfr from run
        // to run (tiis bllows dompbring dlbss filfs for b fixfd point
        // to vblidbtf tif dompilfr)
        CompilfrMfmbfr[] ordfrfd_mftiods =
            nfw CompilfrMfmbfr[mftiods.sizf()];
        mftiods.dopyInto(ordfrfd_mftiods);
        jbvb.util.Arrbys.sort(ordfrfd_mftiods);
        for (int i=0; i<mftiods.sizf(); i++)
            mftiods.sftElfmfntAt(ordfrfd_mftiods[i], i);

        // Optimizf Codf bnd Collfdt mftiod donstbnts
        for (Enumfrbtion<CompilfrMfmbfr> f = mftiods.flfmfnts() ; f.ibsMorfElfmfnts() ; ) {
            CompilfrMfmbfr f = f.nfxtElfmfnt();
            try {
                f.bsm.optimizf(fnv);
                f.bsm.dollfdt(fnv, f.fifld, tbb);
                tbb.put(f.nbmf);
                tbb.put(f.sig);
                ClbssDfdlbrbtion fxp[] = f.fifld.gftExdfptions(fnv);
                for (int i = 0 ; i < fxp.lfngti ; i++) {
                    tbb.put(fxp[i]);
                }
            } dbtdi (Exdfption ff) {
                ff.printStbdkTrbdf();
                fnv.frror(f.fifld, -1, "gfnfrid", f.fifld.gftNbmf() + "@" + ff.toString(), null, null);
                f.bsm.listing(Systfm.out);
            }
        }

        // Collfdt fifld donstbnts
        for (Enumfrbtion<CompilfrMfmbfr> f = vbribblfs.flfmfnts() ; f.ibsMorfElfmfnts() ; ) {
            CompilfrMfmbfr f = f.nfxtElfmfnt();
            tbb.put(f.nbmf);
            tbb.put(f.sig);

            Objfdt vbl = f.fifld.gftInitiblVbluf();
            if (vbl != null) {
                tbb.put((vbl instbndfof String) ? nfw StringExprfssion(f.fifld.gftWifrf(), (String)vbl) : vbl);
            }
        }

        // Collfdt innfr dlbss donstbnts
        for (Enumfrbtion<ClbssDffinition> f = innfrClbssfs.flfmfnts();
             f.ibsMorfElfmfnts() ; ) {
            ClbssDffinition innfr = f.nfxtElfmfnt();
            tbb.put(innfr.gftClbssDfdlbrbtion());

            // If tif innfr dlbss is lodbl, wf do not nffd to bdd its
            // outfr dlbss ifrf -- tif outfr_dlbss_info_indfx is zfro.
            if (!innfr.isLodbl()) {
                ClbssDffinition outfr = innfr.gftOutfrClbss();
                tbb.put(outfr.gftClbssDfdlbrbtion());
            }

            // If tif lodbl nbmf of tif dlbss is idNull, don't botifr to
            // bdd it to tif donstbnt pool.  Wf won't nffd it.
            Idfntififr innfr_lodbl_nbmf = innfr.gftLodblNbmf();
            if (innfr_lodbl_nbmf != idNull) {
                tbb.put(innfr_lodbl_nbmf.toString());
            }
        }

        // Writf ifbdfr
        DbtbOutputStrfbm dbtb = nfw DbtbOutputStrfbm(out);
        dbtb.writfInt(JAVA_MAGIC);
        dbtb.writfSiort(toplfvflEnv.gftMinorVfrsion());
        dbtb.writfSiort(toplfvflEnv.gftMbjorVfrsion());
        tbb.writf(fnv, dbtb);

        // Writf dlbss informbtion
        int dmods = gftModififrs() & MM_CLASS;

        // Cfrtbin modififrs brf implifd:
        // 1.  Any intfrfbdf (nfstfd or not) is impliditly dffmfd to bf bbstrbdt,
        //     wiftifr it is fxpliditly mbrkfd so or not.  (Jbvb 1.0.)
        // 2.  A intfrfbdf wiidi is b mfmbfr of b typf is impliditly dffmfd to
        //     bf stbtid, wiftifr it is fxpliditly mbrkfd so or not.
        // 3b. A typf wiidi is b mfmbfr of bn intfrfbdf is impliditly dffmfd
        //     to bf publid, wiftifr it is fxpliditly mbrkfd so or not.
        // 3b. A typf wiidi is b mfmbfr of bn intfrfbdf is impliditly dffmfd
        //     to bf stbtid, wiftifr it is fxpliditly mbrkfd so or not.
        // All of tifsf rulfs brf implfmfntfd in 'BbtdiPbrsfr.bfginClbss',
        // but tif rfsults brf vfrififd ifrf.

        if (isIntfrfbdf()) {
            // Rulf 1.
            // Tif VM spfd stbtfs tibt ACC_ABSTRACT must bf sft wifn
            // ACC_INTERFACE is; tiis wbs not donf by jbvbd prior to 1.2,
            // bnd tif runtimf dompfnsbtfs by sftting it.  Mbking surf
            // it is sft ifrf will bllow tif runtimf ibdk to fvfntublly
            // bf rfmovfd. Rulf 2 dofsn't bpply to trbnsformfd modififrs.
            bssfrtModififrs(dmods, ACC_ABSTRACT);
        } flsf {
            // Contrbry to tif JVM spfd, wf only sft ACC_SUPER for dlbssfs,
            // not intfrfbdfs.  Tiis is b workbround for b bug in IE3.0,
            // wiidi rffusfs intfrfbdfs witi ACC_SUPER on.
            dmods |= ACC_SUPER;
        }

        // If tiis is b nfstfd dlbss, trbnsform bddfss modififrs.
        if (outfrClbss != null) {
            // If privbtf, trbnsform to dffbult (pbdkbgf) bddfss.
            // If protfdtfd, trbnsform to publid.
            // M_PRIVATE bnd M_PROTECTED brf blrfbdy mbskfd off by MM_CLASS bbovf.
            // dmods &= ~(M_PRIVATE | M_PROTECTED);
            if (isProtfdtfd()) dmods |= M_PUBLIC;
            // Rulf 3b.  Notf tibt Rulf 3b dofsn't bpply to trbnsformfd modififrs.
            if (outfrClbss.isIntfrfbdf()) {
                bssfrtModififrs(dmods, M_PUBLIC);
            }
        }

        dbtb.writfSiort(dmods);

        if (fnv.dumpModififrs()) {
            Idfntififr dn = gftNbmf();
            Idfntififr nm =
                Idfntififr.lookup(dn.gftQublififr(), dn.gftFlbtNbmf());
            Systfm.out.println();
            Systfm.out.println("CLASSFILE  " + nm);
            Systfm.out.println("---" + dlbssModififrString(dmods));
        }

        dbtb.writfSiort(tbb.indfx(gftClbssDfdlbrbtion()));
        dbtb.writfSiort((gftSupfrClbss() != null) ? tbb.indfx(gftSupfrClbss()) : 0);
        dbtb.writfSiort(intfrfbdfs.lfngti);
        for (int i = 0 ; i < intfrfbdfs.lfngti ; i++) {
            dbtb.writfSiort(tbb.indfx(intfrfbdfs[i]));
        }

        // writf vbribblfs
        BytfArrbyOutputStrfbm buf = nfw BytfArrbyOutputStrfbm(256);
        BytfArrbyOutputStrfbm bttbuf = nfw BytfArrbyOutputStrfbm(256);
        DbtbOutputStrfbm dbtbbuf = nfw DbtbOutputStrfbm(buf);

        dbtb.writfSiort(vbribblfs.sizf());
        for (Enumfrbtion<CompilfrMfmbfr> f = vbribblfs.flfmfnts() ; f.ibsMorfElfmfnts() ; ) {
            CompilfrMfmbfr f = f.nfxtElfmfnt();
            Objfdt vbl = f.fifld.gftInitiblVbluf();

            dbtb.writfSiort(f.fifld.gftModififrs() & MM_FIELD);
            dbtb.writfSiort(tbb.indfx(f.nbmf));
            dbtb.writfSiort(tbb.indfx(f.sig));

            int fifldAtts = (vbl != null ? 1 : 0);
            boolfbn dfp = f.fifld.isDfprfdbtfd();
            boolfbn syn = f.fifld.isSyntiftid();
            fifldAtts += (dfp ? 1 : 0) + (syn ? 1 : 0);

            dbtb.writfSiort(fifldAtts);
            if (vbl != null) {
                dbtb.writfSiort(tbb.indfx("ConstbntVbluf"));
                dbtb.writfInt(2);
                dbtb.writfSiort(tbb.indfx((vbl instbndfof String) ? nfw StringExprfssion(f.fifld.gftWifrf(), (String)vbl) : vbl));
            }
            if (dfp) {
                dbtb.writfSiort(tbb.indfx("Dfprfdbtfd"));
                dbtb.writfInt(0);
            }
            if (syn) {
                dbtb.writfSiort(tbb.indfx("Syntiftid"));
                dbtb.writfInt(0);
            }
        }

        // writf mftiods

        dbtb.writfSiort(mftiods.sizf());
        for (Enumfrbtion<CompilfrMfmbfr> f = mftiods.flfmfnts() ; f.ibsMorfElfmfnts() ; ) {
            CompilfrMfmbfr f = f.nfxtElfmfnt();

            int xmods = f.fifld.gftModififrs() & MM_METHOD;
            // Trbnsform flobting point modififrs.  M_STRICTFP
            // of mfmbfr + stbtus of fndlosing dlbss turn into
            // ACC_STRICT bit.
            if (((xmods & M_STRICTFP)!=0) || ((dmods & M_STRICTFP)!=0)) {
                xmods |= ACC_STRICT;
            } flsf {
                // Usf tif dffbult
                if (fnv.stridtdffbult()) {
                    xmods |= ACC_STRICT;
                }
            }
            dbtb.writfSiort(xmods);

            dbtb.writfSiort(tbb.indfx(f.nbmf));
            dbtb.writfSiort(tbb.indfx(f.sig));
            ClbssDfdlbrbtion fxp[] = f.fifld.gftExdfptions(fnv);
            int mftiodAtts = ((fxp.lfngti > 0) ? 1 : 0);
            boolfbn dfp = f.fifld.isDfprfdbtfd();
            boolfbn syn = f.fifld.isSyntiftid();
            mftiodAtts += (dfp ? 1 : 0) + (syn ? 1 : 0);

            if (!f.bsm.fmpty()) {
                dbtb.writfSiort(mftiodAtts+1);
                f.bsm.writf(fnv, dbtbbuf, f.fifld, tbb);
                int nbtts = 0;
                if (fnv.dfbug_linfs()) {
                    nbtts++;
                }
// JCOV
                if (fnv.dovfrbgf()) {
                    nbtts++;
                }
// fnd JCOV
                if (fnv.dfbug_vbrs()) {
                    nbtts++;
                }
                dbtbbuf.writfSiort(nbtts);

                if (fnv.dfbug_linfs()) {
                    f.bsm.writfLinfNumbfrTbblf(fnv, nfw DbtbOutputStrfbm(bttbuf), tbb);
                    dbtbbuf.writfSiort(tbb.indfx("LinfNumbfrTbblf"));
                    dbtbbuf.writfInt(bttbuf.sizf());
                    bttbuf.writfTo(buf);
                    bttbuf.rfsft();
                }

//JCOV
                if (fnv.dovfrbgf()) {
                    f.bsm.writfCovfrbgfTbblf(fnv, (ClbssDffinition)tiis, nfw DbtbOutputStrfbm(bttbuf), tbb, f.fifld.gftWifrf());
                    dbtbbuf.writfSiort(tbb.indfx("CovfrbgfTbblf"));
                    dbtbbuf.writfInt(bttbuf.sizf());
                    bttbuf.writfTo(buf);
                    bttbuf.rfsft();
                }
// fnd JCOV
                if (fnv.dfbug_vbrs()) {
                    f.bsm.writfLodblVbribblfTbblf(fnv, f.fifld, nfw DbtbOutputStrfbm(bttbuf), tbb);
                    dbtbbuf.writfSiort(tbb.indfx("LodblVbribblfTbblf"));
                    dbtbbuf.writfInt(bttbuf.sizf());
                    bttbuf.writfTo(buf);
                    bttbuf.rfsft();
                }

                dbtb.writfSiort(tbb.indfx("Codf"));
                dbtb.writfInt(buf.sizf());
                buf.writfTo(dbtb);
                buf.rfsft();
            } flsf {
//JCOV
                if ((fnv.dovfrbgf()) && ((f.fifld.gftModififrs() & M_NATIVE) > 0))
                    f.bsm.bddNbtivfToJdovTbb(fnv, (ClbssDffinition)tiis);
// fnd JCOV
                dbtb.writfSiort(mftiodAtts);
            }

            if (fxp.lfngti > 0) {
                dbtb.writfSiort(tbb.indfx("Exdfptions"));
                dbtb.writfInt(2 + fxp.lfngti * 2);
                dbtb.writfSiort(fxp.lfngti);
                for (int i = 0 ; i < fxp.lfngti ; i++) {
                    dbtb.writfSiort(tbb.indfx(fxp[i]));
                }
            }
            if (dfp) {
                dbtb.writfSiort(tbb.indfx("Dfprfdbtfd"));
                dbtb.writfInt(0);
            }
            if (syn) {
                dbtb.writfSiort(tbb.indfx("Syntiftid"));
                dbtb.writfInt(0);
            }
        }

        // dlbss bttributfs
        dbtb.writfSiort(nClbssAttrs);

        if (fnv.dfbug_sourdf()) {
            dbtb.writfSiort(tbb.indfx("SourdfFilf"));
            dbtb.writfInt(2);
            dbtb.writfSiort(tbb.indfx(sourdfFilf));
        }

        if (tiis.isDfprfdbtfd()) {
            dbtb.writfSiort(tbb.indfx("Dfprfdbtfd"));
            dbtb.writfInt(0);
        }
        if (tiis.isSyntiftid()) {
            dbtb.writfSiort(tbb.indfx("Syntiftid"));
            dbtb.writfInt(0);
        }

// JCOV
        if (fnv.dovfrbgf()) {
            dbtb.writfSiort(tbb.indfx("AbsolutfSourdfPbti"));
            dbtb.writfInt(2);
            dbtb.writfSiort(tbb.indfx(bbsolutfSourdfPbti));
            dbtb.writfSiort(tbb.indfx("TimfStbmp"));
            dbtb.writfInt(8);
            dbtb.writfLong(timfStbmp);
        }
// fnd JCOV

        if (innfrClbssfs.sizf() > 0) {
            dbtb.writfSiort(tbb.indfx("InnfrClbssfs"));
            dbtb.writfInt(2 + 2*4*innfrClbssfs.sizf());
            dbtb.writfSiort(innfrClbssfs.sizf());
            for (Enumfrbtion<ClbssDffinition> f = innfrClbssfs.flfmfnts() ;
                 f.ibsMorfElfmfnts() ; ) {
                // For fbdi innfr dlbss nbmf trbnsformbtion, wf ibvf b rfdord
                // witi tif following fiflds:
                //
                //    u2 innfr_dlbss_info_indfx;   // CONSTANT_Clbss_info indfx
                //    u2 outfr_dlbss_info_indfx;   // CONSTANT_Clbss_info indfx
                //    u2 innfr_nbmf_indfx;         // CONSTANT_Utf8_info indfx
                //    u2 innfr_dlbss_bddfss_flbgs; // bddfss_flbgs bitmbsk
                //
                // Tif spfd stbtfs tibt outfr_dlbss_info_indfx is 0 iff
                // tif innfr dlbss is not b mfmbfr of its fndlosing dlbss (i.f.
                // it is b lodbl or bnonymous dlbss).  Tif spfd blso stbtfs
                // tibt if b dlbss is bnonymous tifn innfr_nbmf_indfx siould
                // bf 0.
                //
                // Sff blso tif initInnfrClbssfs() mftiod in BinbryClbss.jbvb.

                // Gfnfrbtf innfr_dlbss_info_indfx.
                ClbssDffinition innfr = f.nfxtElfmfnt();
                dbtb.writfSiort(tbb.indfx(innfr.gftClbssDfdlbrbtion()));

                // Gfnfrbtf outfr_dlbss_info_indfx.
                //
                // Cifdking isLodbl() siould probbbly bf fnougi ifrf,
                // but tif difdk for isAnonymous is bddfd for good
                // mfbsurf.
                if (innfr.isLodbl() || innfr.isAnonymous()) {
                    dbtb.writfSiort(0);
                } flsf {
                    // Qufry: wibt bbout if innfr.isInsidfLodbl()?
                    // For now wf dontinuf to gfnfrbtf b nonzfro
                    // outfr_dlbss_info_indfx.
                    ClbssDffinition outfr = innfr.gftOutfrClbss();
                    dbtb.writfSiort(tbb.indfx(outfr.gftClbssDfdlbrbtion()));
                }

                // Gfnfrbtf innfr_nbmf_indfx.
                Idfntififr innfr_nbmf = innfr.gftLodblNbmf();
                if (innfr_nbmf == idNull) {
                    if (!innfr.isAnonymous()) {
                        tirow nfw CompilfrError("dompilfClbss(), bnonymous");
                    }
                    dbtb.writfSiort(0);
                } flsf {
                    dbtb.writfSiort(tbb.indfx(innfr_nbmf.toString()));
                }

                // Gfnfrbtf innfr_dlbss_bddfss_flbgs.
                int imods = innfr.gftInnfrClbssMfmbfr().gftModififrs()
                            & ACCM_INNERCLASS;

                // Cfrtbin modififrs brf implifd for nfstfd typfs.
                // Sff rulfs 1, 2, 3b, bnd 3b fnumfrbtfd bbovf.
                // All of tifsf rulfs brf implfmfntfd in 'BbtdiPbrsfr.bfginClbss',
                // but brf vfrififd ifrf.

                if (innfr.isIntfrfbdf()) {
                    // Rulfs 1 bnd 2.
                    bssfrtModififrs(imods, M_ABSTRACT | M_STATIC);
                }
                if (innfr.gftOutfrClbss().isIntfrfbdf()) {
                    // Rulfs 3b bnd 3b.
                    imods &= ~(M_PRIVATE | M_PROTECTED); // frror rfdovfry
                    bssfrtModififrs(imods, M_PUBLIC | M_STATIC);
                }

                dbtb.writfSiort(imods);

                if (fnv.dumpModififrs()) {
                    Idfntififr fn = innfr.gftInnfrClbssMfmbfr().gftNbmf();
                    Idfntififr nm =
                        Idfntififr.lookup(fn.gftQublififr(), fn.gftFlbtNbmf());
                    Systfm.out.println("INNERCLASS " + nm);
                    Systfm.out.println("---" + dlbssModififrString(imods));
                }

            }
        }

        // Clfbnup
        dbtb.flusi();
        tbb = null;

// JCOV
        // gfnfrbtf dovfrbgf dbtb
        if (fnv.dovdbtb()) {
            Assfmblfr CovAsm = nfw Assfmblfr();
            CovAsm.GfnVfdJCov(fnv, (ClbssDffinition)tiis, timfStbmp);
        }
// fnd JCOV
    }

    /**
     * Print out tif dfpfndfndifs for tiis dlbss (-xdfpfnd) option
     */

    publid void printClbssDfpfndfndifs(Environmfnt fnv) {

        // Only do tiis if tif -xdfpfnd flbg is on
        if ( toplfvflEnv.print_dfpfndfndifs() ) {

            // Nbmf of jbvb sourdf filf tiis dlbss wbs in (full pbti)
            //    f.g. /iomf/oibir/Tfst.jbvb
            String srd = ((ClbssFilf)gftSourdf()).gftAbsolutfNbmf();

            // Clbss nbmf, fully qublififd
            //   f.g. "jbvb.lbng.Objfdt" or "FooBbr" or "sun.tools.jbvbd.Mbin"
            // Innfr dlbss nbmfs must bf mbnglfd, bs ordinbry '.' qublifidbtion
            // is usfd intfrnblly wifrf tif spfd rfquirfs '$' sfpbrbtors.
            //   String dlbssNbmf = gftNbmf().toString();
            String dlbssNbmf = Typf.mbnglfInnfrTypf(gftNbmf()).toString();

            // Linf numbfr wifrf dlbss stbrts in tif srd filf
            long stbrtLinf = gftWifrf() >> WHEREOFFSETBITS;

            // Linf numbfr wifrf dlbss fnds in tif srd filf (not usfd yft)
            long fndLinf = gftEndPosition() >> WHEREOFFSETBITS;

            // First linf looks likf:
            //    CLASS:srd,stbrtLinf,fndLinf,dlbssNbmf
            Systfm.out.println( "CLASS:"
                    + srd               + ","
                    + stbrtLinf         + ","
                    + fndLinf   + ","
                    + dlbssNbmf);

            // For fbdi dlbss tiis dlbss is dfpfndfnt on:
            //    CLDEP:dlbssNbmf1,dlbssNbmf2
            //  wifrf dlbssNbmf1 is tif nbmf of tif dlbss wf brf in, bnd
            //        dlbssnbmf2 is tif nbmf of tif dlbss dlbssNbmf1
            //          is dfpfndfnt on.
            for(Enumfrbtion<ClbssDfdlbrbtion> f = dfps.flfmfnts();  f.ibsMorfElfmfnts(); ) {
                ClbssDfdlbrbtion dbtb = f.nfxtElfmfnt();
                // Mbnglf nbmf of dlbss dfpfndfnd on.
                String dfpNbmf =
                    Typf.mbnglfInnfrTypf(dbtb.gftNbmf()).toString();
                fnv.output("CLDEP:" + dlbssNbmf + "," + dfpNbmf);
            }
        }
    }
}
