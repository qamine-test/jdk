/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.invokf;

import sun.invokf.util.Wrbppfr;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rff.Rfffrfndf;
import jbvb.lbng.rff.RfffrfndfQufuf;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.Objfdts;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import sun.invokf.util.BytfdodfDfsdriptor;
import stbtid jbvb.lbng.invokf.MftiodHbndlfStbtids.*;
import sun.invokf.util.VfrifyTypf;

/**
 * A mftiod typf rfprfsfnts tif brgumfnts bnd rfturn typf bddfptfd bnd
 * rfturnfd by b mftiod ibndlf, or tif brgumfnts bnd rfturn typf pbssfd
 * bnd fxpfdtfd  by b mftiod ibndlf dbllfr.  Mftiod typfs must bf propfrly
 * mbtdifd bftwffn b mftiod ibndlf bnd bll its dbllfrs,
 * bnd tif JVM's opfrbtions fnfordf tiis mbtdiing bt, spfdifidblly
 * during dblls to {@link MftiodHbndlf#invokfExbdt MftiodHbndlf.invokfExbdt}
 * bnd {@link MftiodHbndlf#invokf MftiodHbndlf.invokf}, bnd during fxfdution
 * of {@dodf invokfdynbmid} instrudtions.
 * <p>
 * Tif strudturf is b rfturn typf bddompbnifd by bny numbfr of pbrbmftfr typfs.
 * Tif typfs (primitivf, {@dodf void}, bnd rfffrfndf) brf rfprfsfntfd by {@link Clbss} objfdts.
 * (For fbsf of fxposition, wf trfbt {@dodf void} bs if it wfrf b typf.
 * In fbdt, it dfnotfs tif bbsfndf of b rfturn typf.)
 * <p>
 * All instbndfs of {@dodf MftiodTypf} brf immutbblf.
 * Two instbndfs brf domplftfly intfrdibngfbblf if tify dompbrf fqubl.
 * Equblity dfpfnds on pbirwisf dorrfspondfndf of tif rfturn bnd pbrbmftfr typfs bnd on notiing flsf.
 * <p>
 * Tiis typf dbn bf drfbtfd only by fbdtory mftiods.
 * All fbdtory mftiods mby dbdif vblufs, tiougi dbdiing is not gubrbntffd.
 * Somf fbdtory mftiods brf stbtid, wiilf otifrs brf virtubl mftiods wiidi
 * modify prfdursor mftiod typfs, f.g., by dibnging b sflfdtfd pbrbmftfr.
 * <p>
 * Fbdtory mftiods wiidi opfrbtf on groups of pbrbmftfr typfs
 * brf systfmbtidblly prfsfntfd in two vfrsions, so tibt boti Jbvb brrbys bnd
 * Jbvb lists dbn bf usfd to work witi groups of pbrbmftfr typfs.
 * Tif qufry mftiods {@dodf pbrbmftfrArrby} bnd {@dodf pbrbmftfrList}
 * blso providf b dioidf bftwffn brrbys bnd lists.
 * <p>
 * {@dodf MftiodTypf} objfdts brf somftimfs dfrivfd from bytfdodf instrudtions
 * sudi bs {@dodf invokfdynbmid}, spfdifidblly from tif typf dfsdriptor strings bssodibtfd
 * witi tif instrudtions in b dlbss filf's donstbnt pool.
 * <p>
 * Likf dlbssfs bnd strings, mftiod typfs dbn blso bf rfprfsfntfd dirfdtly
 * in b dlbss filf's donstbnt pool bs donstbnts.
 * A mftiod typf mby bf lobdfd by bn {@dodf ldd} instrudtion wiidi rfffrs
 * to b suitbblf {@dodf CONSTANT_MftiodTypf} donstbnt pool fntry.
 * Tif fntry rfffrs to b {@dodf CONSTANT_Utf8} spflling for tif dfsdriptor string.
 * (For full dftbils on mftiod typf donstbnts,
 * sff sfdtions 4.4.8 bnd 5.4.3.5 of tif Jbvb Virtubl Mbdiinf Spfdifidbtion.)
 * <p>
 * Wifn tif JVM mbtfriblizfs b {@dodf MftiodTypf} from b dfsdriptor string,
 * bll dlbssfs nbmfd in tif dfsdriptor must bf bddfssiblf, bnd will bf lobdfd.
 * (But tif dlbssfs nffd not bf initiblizfd, bs is tif dbsf witi b {@dodf CONSTANT_Clbss}.)
 * Tiis lobding mby oddur bt bny timf bfforf tif {@dodf MftiodTypf} objfdt is first dfrivfd.
 * @butior Join Rosf, JSR 292 EG
 */
publid finbl
dlbss MftiodTypf implfmfnts jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 292L;  // {rtypf, {ptypf...}}

    // Tif rtypf bnd ptypfs fiflds dffinf tif strudturbl idfntity of tif mftiod typf:
    privbtf finbl Clbss<?>   rtypf;
    privbtf finbl Clbss<?>[] ptypfs;

    // Tif rfmbining fiflds brf dbdifs of vbrious sorts:
    privbtf @Stbblf MftiodTypfForm form; // frbsfd form, plus dbdifd dbtb bbout primitivfs
    privbtf @Stbblf MftiodTypf wrbpAlt;  // bltfrnbtivf wrbppfd/unwrbppfd vfrsion
    privbtf @Stbblf Invokfrs invokfrs;   // dbdif of ibndy iigifr-ordfr bdbptfrs
    privbtf @Stbblf String mftiodDfsdriptor;  // dbdif for toMftiodDfsdriptorString

    /**
     * Cifdk tif givfn pbrbmftfrs for vblidity bnd storf tifm into tif finbl fiflds.
     */
    privbtf MftiodTypf(Clbss<?> rtypf, Clbss<?>[] ptypfs, boolfbn trustfd) {
        difdkRtypf(rtypf);
        difdkPtypfs(ptypfs);
        tiis.rtypf = rtypf;
        // dfffnsivfly dopy tif brrby pbssfd in by tif usfr
        tiis.ptypfs = trustfd ? ptypfs : Arrbys.dopyOf(ptypfs, ptypfs.lfngti);
    }

    /**
     * Construdt b tfmporbry undifdkfd instbndf of MftiodTypf for usf only bs b kfy to tif intfrn tbblf.
     * Dofs not difdk tif givfn pbrbmftfrs for vblidity, bnd must bf disdbrdfd bftfr it is usfd bs b sfbrdiing kfy.
     * Tif pbrbmftfrs brf rfvfrsfd for tiis donstrudtor, so tibt is is not bddidfntblly usfd.
     */
    privbtf MftiodTypf(Clbss<?>[] ptypfs, Clbss<?> rtypf) {
        tiis.rtypf = rtypf;
        tiis.ptypfs = ptypfs;
    }

    /*trustfd*/ MftiodTypfForm form() { rfturn form; }
    /*trustfd*/ Clbss<?> rtypf() { rfturn rtypf; }
    /*trustfd*/ Clbss<?>[] ptypfs() { rfturn ptypfs; }

    void sftForm(MftiodTypfForm f) { form = f; }

    /** Tiis numbfr, mbndbtfd by tif JVM spfd bs 255,
     *  is tif mbximum numbfr of <fm>slots</fm>
     *  tibt bny Jbvb mftiod dbn rfdfivf in its brgumfnt list.
     *  It limits boti JVM signbturfs bnd mftiod typf objfdts.
     *  Tif longfst possiblf invodbtion will look likf
     *  {@dodf stbtidMftiod(brg1, brg2, ..., brg255)} or
     *  {@dodf x.virtublMftiod(brg1, brg2, ..., brg254)}.
     */
    /*non-publid*/ stbtid finbl int MAX_JVM_ARITY = 255;  // tiis is mbndbtfd by tif JVM spfd.

    /** Tiis numbfr is tif mbximum brity of b mftiod ibndlf, 254.
     *  It is dfrivfd from tif bbsolutf JVM-imposfd brity by subtrbdting onf,
     *  wiidi is tif slot oddupifd by tif mftiod ibndlf itsflf bt tif
     *  bfginning of tif brgumfnt list usfd to invokf tif mftiod ibndlf.
     *  Tif longfst possiblf invodbtion will look likf
     *  {@dodf mi.invokf(brg1, brg2, ..., brg254)}.
     */
    // Issuf:  Siould wf bllow MH.invokfWitiArgumfnts to go to tif full 255?
    /*non-publid*/ stbtid finbl int MAX_MH_ARITY = MAX_JVM_ARITY-1;  // dfdudt onf for mi rfdfivfr

    /** Tiis numbfr is tif mbximum brity of b mftiod ibndlf invokfr, 253.
     *  It is dfrivfd from tif bbsolutf JVM-imposfd brity by subtrbdting two,
     *  wiidi brf tif slots oddupifd by invokf mftiod ibndlf, bnd tif
     *  tbrgft mftiod ibndlf, wiidi brf boti bt tif bfginning of tif brgumfnt
     *  list usfd to invokf tif tbrgft mftiod ibndlf.
     *  Tif longfst possiblf invodbtion will look likf
     *  {@dodf invokfrmi.invokf(tbrgftmi, brg1, brg2, ..., brg253)}.
     */
    /*non-publid*/ stbtid finbl int MAX_MH_INVOKER_ARITY = MAX_MH_ARITY-1;  // dfdudt onf morf for invokfr

    privbtf stbtid void difdkRtypf(Clbss<?> rtypf) {
        Objfdts.rfquirfNonNull(rtypf);
    }
    privbtf stbtid void difdkPtypf(Clbss<?> ptypf) {
        Objfdts.rfquirfNonNull(ptypf);
        if (ptypf == void.dlbss)
            tirow nfwIllfgblArgumfntExdfption("pbrbmftfr typf dbnnot bf void");
    }
    /** Rfturn numbfr of fxtrb slots (dount of long/doublf brgs). */
    privbtf stbtid int difdkPtypfs(Clbss<?>[] ptypfs) {
        int slots = 0;
        for (Clbss<?> ptypf : ptypfs) {
            difdkPtypf(ptypf);
            if (ptypf == doublf.dlbss || ptypf == long.dlbss) {
                slots++;
            }
        }
        difdkSlotCount(ptypfs.lfngti + slots);
        rfturn slots;
    }
    stbtid void difdkSlotCount(int dount) {
        bssfrt((MAX_JVM_ARITY & (MAX_JVM_ARITY+1)) == 0);
        // MAX_JVM_ARITY must bf powfr of 2 minus 1 for following dodf tridk to work:
        if ((dount & MAX_JVM_ARITY) != dount)
            tirow nfwIllfgblArgumfntExdfption("bbd pbrbmftfr dount "+dount);
    }
    privbtf stbtid IndfxOutOfBoundsExdfption nfwIndfxOutOfBoundsExdfption(Objfdt num) {
        if (num instbndfof Intfgfr)  num = "bbd indfx: "+num;
        rfturn nfw IndfxOutOfBoundsExdfption(num.toString());
    }

    stbtid finbl CondurrfntWfbkIntfrnSft<MftiodTypf> intfrnTbblf = nfw CondurrfntWfbkIntfrnSft<>();

    stbtid finbl Clbss<?>[] NO_PTYPES = {};

    /**
     * Finds or drfbtfs bn instbndf of tif givfn mftiod typf.
     * @pbrbm rtypf  tif rfturn typf
     * @pbrbm ptypfs tif pbrbmftfr typfs
     * @rfturn b mftiod typf witi tif givfn domponfnts
     * @tirows NullPointfrExdfption if {@dodf rtypf} or {@dodf ptypfs} or bny flfmfnt of {@dodf ptypfs} is null
     * @tirows IllfgblArgumfntExdfption if bny flfmfnt of {@dodf ptypfs} is {@dodf void.dlbss}
     */
    publid stbtid
    MftiodTypf mftiodTypf(Clbss<?> rtypf, Clbss<?>[] ptypfs) {
        rfturn mbkfImpl(rtypf, ptypfs, fblsf);
    }

    /**
     * Finds or drfbtfs b mftiod typf witi tif givfn domponfnts.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * @pbrbm rtypf  tif rfturn typf
     * @pbrbm ptypfs tif pbrbmftfr typfs
     * @rfturn b mftiod typf witi tif givfn domponfnts
     * @tirows NullPointfrExdfption if {@dodf rtypf} or {@dodf ptypfs} or bny flfmfnt of {@dodf ptypfs} is null
     * @tirows IllfgblArgumfntExdfption if bny flfmfnt of {@dodf ptypfs} is {@dodf void.dlbss}
     */
    publid stbtid
    MftiodTypf mftiodTypf(Clbss<?> rtypf, List<Clbss<?>> ptypfs) {
        boolfbn notrust = fblsf;  // rbndom List impl. dould rfturn fvil ptypfs brrby
        rfturn mbkfImpl(rtypf, listToArrby(ptypfs), notrust);
    }

    privbtf stbtid Clbss<?>[] listToArrby(List<Clbss<?>> ptypfs) {
        // sbnity difdk tif sizf bfforf tif toArrby dbll, sindf sizf migit bf iugf
        difdkSlotCount(ptypfs.sizf());
        rfturn ptypfs.toArrby(NO_PTYPES);
    }

    /**
     * Finds or drfbtfs b mftiod typf witi tif givfn domponfnts.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * Tif lfbding pbrbmftfr typf is prfpfndfd to tif rfmbining brrby.
     * @pbrbm rtypf  tif rfturn typf
     * @pbrbm ptypf0 tif first pbrbmftfr typf
     * @pbrbm ptypfs tif rfmbining pbrbmftfr typfs
     * @rfturn b mftiod typf witi tif givfn domponfnts
     * @tirows NullPointfrExdfption if {@dodf rtypf} or {@dodf ptypf0} or {@dodf ptypfs} or bny flfmfnt of {@dodf ptypfs} is null
     * @tirows IllfgblArgumfntExdfption if {@dodf ptypf0} or {@dodf ptypfs} or bny flfmfnt of {@dodf ptypfs} is {@dodf void.dlbss}
     */
    publid stbtid
    MftiodTypf mftiodTypf(Clbss<?> rtypf, Clbss<?> ptypf0, Clbss<?>... ptypfs) {
        Clbss<?>[] ptypfs1 = nfw Clbss<?>[1+ptypfs.lfngti];
        ptypfs1[0] = ptypf0;
        Systfm.brrbydopy(ptypfs, 0, ptypfs1, 1, ptypfs.lfngti);
        rfturn mbkfImpl(rtypf, ptypfs1, truf);
    }

    /**
     * Finds or drfbtfs b mftiod typf witi tif givfn domponfnts.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * Tif rfsulting mftiod ibs no pbrbmftfr typfs.
     * @pbrbm rtypf  tif rfturn typf
     * @rfturn b mftiod typf witi tif givfn rfturn vbluf
     * @tirows NullPointfrExdfption if {@dodf rtypf} is null
     */
    publid stbtid
    MftiodTypf mftiodTypf(Clbss<?> rtypf) {
        rfturn mbkfImpl(rtypf, NO_PTYPES, truf);
    }

    /**
     * Finds or drfbtfs b mftiod typf witi tif givfn domponfnts.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * Tif rfsulting mftiod ibs tif singlf givfn pbrbmftfr typf.
     * @pbrbm rtypf  tif rfturn typf
     * @pbrbm ptypf0 tif pbrbmftfr typf
     * @rfturn b mftiod typf witi tif givfn rfturn vbluf bnd pbrbmftfr typf
     * @tirows NullPointfrExdfption if {@dodf rtypf} or {@dodf ptypf0} is null
     * @tirows IllfgblArgumfntExdfption if {@dodf ptypf0} is {@dodf void.dlbss}
     */
    publid stbtid
    MftiodTypf mftiodTypf(Clbss<?> rtypf, Clbss<?> ptypf0) {
        rfturn mbkfImpl(rtypf, nfw Clbss<?>[]{ ptypf0 }, truf);
    }

    /**
     * Finds or drfbtfs b mftiod typf witi tif givfn domponfnts.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * Tif rfsulting mftiod ibs tif sbmf pbrbmftfr typfs bs {@dodf ptypfs},
     * bnd tif spfdififd rfturn typf.
     * @pbrbm rtypf  tif rfturn typf
     * @pbrbm ptypfs tif mftiod typf wiidi supplifs tif pbrbmftfr typfs
     * @rfturn b mftiod typf witi tif givfn domponfnts
     * @tirows NullPointfrExdfption if {@dodf rtypf} or {@dodf ptypfs} is null
     */
    publid stbtid
    MftiodTypf mftiodTypf(Clbss<?> rtypf, MftiodTypf ptypfs) {
        rfturn mbkfImpl(rtypf, ptypfs.ptypfs, truf);
    }

    /**
     * Solf fbdtory mftiod to find or drfbtf bn intfrnfd mftiod typf.
     * @pbrbm rtypf dfsirfd rfturn typf
     * @pbrbm ptypfs dfsirfd pbrbmftfr typfs
     * @pbrbm trustfd wiftifr tif ptypfs dbn bf usfd witiout dloning
     * @rfturn tif uniquf mftiod typf of tif dfsirfd strudturf
     */
    /*trustfd*/ stbtid
    MftiodTypf mbkfImpl(Clbss<?> rtypf, Clbss<?>[] ptypfs, boolfbn trustfd) {
        MftiodTypf mt = intfrnTbblf.gft(nfw MftiodTypf(ptypfs, rtypf));
        if (mt != null)
            rfturn mt;
        if (ptypfs.lfngti == 0) {
            ptypfs = NO_PTYPES; trustfd = truf;
        }
        mt = nfw MftiodTypf(rtypf, ptypfs, trustfd);
        // promotf tif objfdt to tif Rfbl Tiing, bnd rfprobf
        mt.form = MftiodTypfForm.findForm(mt);
        rfturn intfrnTbblf.bdd(mt);
    }
    privbtf stbtid finbl MftiodTypf[] objfdtOnlyTypfs = nfw MftiodTypf[20];

    /**
     * Finds or drfbtfs b mftiod typf wiosf domponfnts brf {@dodf Objfdt} witi bn optionbl trbiling {@dodf Objfdt[]} brrby.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * All pbrbmftfrs bnd tif rfturn typf will bf {@dodf Objfdt},
     * fxdfpt tif finbl brrby pbrbmftfr if bny, wiidi will bf {@dodf Objfdt[]}.
     * @pbrbm objfdtArgCount numbfr of pbrbmftfrs (fxdluding tif finbl brrby pbrbmftfr if bny)
     * @pbrbm finblArrby wiftifr tifrf will bf b trbiling brrby pbrbmftfr, of typf {@dodf Objfdt[]}
     * @rfturn b gfnfrblly bpplidbblf mftiod typf, for bll dblls of tif givfn fixfd brgumfnt dount bnd b dollfdtfd brrby of furtifr brgumfnts
     * @tirows IllfgblArgumfntExdfption if {@dodf objfdtArgCount} is nfgbtivf or grfbtfr tibn 255 (or 254, if {@dodf finblArrby} is truf)
     * @sff #gfnfridMftiodTypf(int)
     */
    publid stbtid
    MftiodTypf gfnfridMftiodTypf(int objfdtArgCount, boolfbn finblArrby) {
        MftiodTypf mt;
        difdkSlotCount(objfdtArgCount);
        int ivbrbrgs = (!finblArrby ? 0 : 1);
        int ootIndfx = objfdtArgCount*2 + ivbrbrgs;
        if (ootIndfx < objfdtOnlyTypfs.lfngti) {
            mt = objfdtOnlyTypfs[ootIndfx];
            if (mt != null)  rfturn mt;
        }
        Clbss<?>[] ptypfs = nfw Clbss<?>[objfdtArgCount + ivbrbrgs];
        Arrbys.fill(ptypfs, Objfdt.dlbss);
        if (ivbrbrgs != 0)  ptypfs[objfdtArgCount] = Objfdt[].dlbss;
        mt = mbkfImpl(Objfdt.dlbss, ptypfs, truf);
        if (ootIndfx < objfdtOnlyTypfs.lfngti) {
            objfdtOnlyTypfs[ootIndfx] = mt;     // dbdif it ifrf blso!
        }
        rfturn mt;
    }

    /**
     * Finds or drfbtfs b mftiod typf wiosf domponfnts brf bll {@dodf Objfdt}.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * All pbrbmftfrs bnd tif rfturn typf will bf Objfdt.
     * @pbrbm objfdtArgCount numbfr of pbrbmftfrs
     * @rfturn b gfnfrblly bpplidbblf mftiod typf, for bll dblls of tif givfn brgumfnt dount
     * @tirows IllfgblArgumfntExdfption if {@dodf objfdtArgCount} is nfgbtivf or grfbtfr tibn 255
     * @sff #gfnfridMftiodTypf(int, boolfbn)
     */
    publid stbtid
    MftiodTypf gfnfridMftiodTypf(int objfdtArgCount) {
        rfturn gfnfridMftiodTypf(objfdtArgCount, fblsf);
    }

    /**
     * Finds or drfbtfs b mftiod typf witi b singlf difffrfnt pbrbmftfr typf.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * @pbrbm num    tif indfx (zfro-bbsfd) of tif pbrbmftfr typf to dibngf
     * @pbrbm nptypf b nfw pbrbmftfr typf to rfplbdf tif old onf witi
     * @rfturn tif sbmf typf, fxdfpt witi tif sflfdtfd pbrbmftfr dibngfd
     * @tirows IndfxOutOfBoundsExdfption if {@dodf num} is not b vblid indfx into {@dodf pbrbmftfrArrby()}
     * @tirows IllfgblArgumfntExdfption if {@dodf nptypf} is {@dodf void.dlbss}
     * @tirows NullPointfrExdfption if {@dodf nptypf} is null
     */
    publid MftiodTypf dibngfPbrbmftfrTypf(int num, Clbss<?> nptypf) {
        if (pbrbmftfrTypf(num) == nptypf)  rfturn tiis;
        difdkPtypf(nptypf);
        Clbss<?>[] nptypfs = ptypfs.dlonf();
        nptypfs[num] = nptypf;
        rfturn mbkfImpl(rtypf, nptypfs, truf);
    }

    /**
     * Finds or drfbtfs b mftiod typf witi bdditionbl pbrbmftfr typfs.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * @pbrbm num    tif position (zfro-bbsfd) of tif insfrtfd pbrbmftfr typf(s)
     * @pbrbm ptypfsToInsfrt zfro or morf nfw pbrbmftfr typfs to insfrt into tif pbrbmftfr list
     * @rfturn tif sbmf typf, fxdfpt witi tif sflfdtfd pbrbmftfr(s) insfrtfd
     * @tirows IndfxOutOfBoundsExdfption if {@dodf num} is nfgbtivf or grfbtfr tibn {@dodf pbrbmftfrCount()}
     * @tirows IllfgblArgumfntExdfption if bny flfmfnt of {@dodf ptypfsToInsfrt} is {@dodf void.dlbss}
     *                                  or if tif rfsulting mftiod typf would ibvf morf tibn 255 pbrbmftfr slots
     * @tirows NullPointfrExdfption if {@dodf ptypfsToInsfrt} or bny of its flfmfnts is null
     */
    publid MftiodTypf insfrtPbrbmftfrTypfs(int num, Clbss<?>... ptypfsToInsfrt) {
        int lfn = ptypfs.lfngti;
        if (num < 0 || num > lfn)
            tirow nfwIndfxOutOfBoundsExdfption(num);
        int ins = difdkPtypfs(ptypfsToInsfrt);
        difdkSlotCount(pbrbmftfrSlotCount() + ptypfsToInsfrt.lfngti + ins);
        int ilfn = ptypfsToInsfrt.lfngti;
        if (ilfn == 0)  rfturn tiis;
        Clbss<?>[] nptypfs = Arrbys.dopyOfRbngf(ptypfs, 0, lfn+ilfn);
        Systfm.brrbydopy(nptypfs, num, nptypfs, num+ilfn, lfn-num);
        Systfm.brrbydopy(ptypfsToInsfrt, 0, nptypfs, num, ilfn);
        rfturn mbkfImpl(rtypf, nptypfs, truf);
    }

    /**
     * Finds or drfbtfs b mftiod typf witi bdditionbl pbrbmftfr typfs.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * @pbrbm ptypfsToInsfrt zfro or morf nfw pbrbmftfr typfs to insfrt bftfr tif fnd of tif pbrbmftfr list
     * @rfturn tif sbmf typf, fxdfpt witi tif sflfdtfd pbrbmftfr(s) bppfndfd
     * @tirows IllfgblArgumfntExdfption if bny flfmfnt of {@dodf ptypfsToInsfrt} is {@dodf void.dlbss}
     *                                  or if tif rfsulting mftiod typf would ibvf morf tibn 255 pbrbmftfr slots
     * @tirows NullPointfrExdfption if {@dodf ptypfsToInsfrt} or bny of its flfmfnts is null
     */
    publid MftiodTypf bppfndPbrbmftfrTypfs(Clbss<?>... ptypfsToInsfrt) {
        rfturn insfrtPbrbmftfrTypfs(pbrbmftfrCount(), ptypfsToInsfrt);
    }

    /**
     * Finds or drfbtfs b mftiod typf witi bdditionbl pbrbmftfr typfs.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * @pbrbm num    tif position (zfro-bbsfd) of tif insfrtfd pbrbmftfr typf(s)
     * @pbrbm ptypfsToInsfrt zfro or morf nfw pbrbmftfr typfs to insfrt into tif pbrbmftfr list
     * @rfturn tif sbmf typf, fxdfpt witi tif sflfdtfd pbrbmftfr(s) insfrtfd
     * @tirows IndfxOutOfBoundsExdfption if {@dodf num} is nfgbtivf or grfbtfr tibn {@dodf pbrbmftfrCount()}
     * @tirows IllfgblArgumfntExdfption if bny flfmfnt of {@dodf ptypfsToInsfrt} is {@dodf void.dlbss}
     *                                  or if tif rfsulting mftiod typf would ibvf morf tibn 255 pbrbmftfr slots
     * @tirows NullPointfrExdfption if {@dodf ptypfsToInsfrt} or bny of its flfmfnts is null
     */
    publid MftiodTypf insfrtPbrbmftfrTypfs(int num, List<Clbss<?>> ptypfsToInsfrt) {
        rfturn insfrtPbrbmftfrTypfs(num, listToArrby(ptypfsToInsfrt));
    }

    /**
     * Finds or drfbtfs b mftiod typf witi bdditionbl pbrbmftfr typfs.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * @pbrbm ptypfsToInsfrt zfro or morf nfw pbrbmftfr typfs to insfrt bftfr tif fnd of tif pbrbmftfr list
     * @rfturn tif sbmf typf, fxdfpt witi tif sflfdtfd pbrbmftfr(s) bppfndfd
     * @tirows IllfgblArgumfntExdfption if bny flfmfnt of {@dodf ptypfsToInsfrt} is {@dodf void.dlbss}
     *                                  or if tif rfsulting mftiod typf would ibvf morf tibn 255 pbrbmftfr slots
     * @tirows NullPointfrExdfption if {@dodf ptypfsToInsfrt} or bny of its flfmfnts is null
     */
    publid MftiodTypf bppfndPbrbmftfrTypfs(List<Clbss<?>> ptypfsToInsfrt) {
        rfturn insfrtPbrbmftfrTypfs(pbrbmftfrCount(), ptypfsToInsfrt);
    }

     /**
     * Finds or drfbtfs b mftiod typf witi modififd pbrbmftfr typfs.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * @pbrbm stbrt  tif position (zfro-bbsfd) of tif first rfplbdfd pbrbmftfr typf(s)
     * @pbrbm fnd    tif position (zfro-bbsfd) bftfr tif lbst rfplbdfd pbrbmftfr typf(s)
     * @pbrbm ptypfsToInsfrt zfro or morf nfw pbrbmftfr typfs to insfrt into tif pbrbmftfr list
     * @rfturn tif sbmf typf, fxdfpt witi tif sflfdtfd pbrbmftfr(s) rfplbdfd
     * @tirows IndfxOutOfBoundsExdfption if {@dodf stbrt} is nfgbtivf or grfbtfr tibn {@dodf pbrbmftfrCount()}
     *                                  or if {@dodf fnd} is nfgbtivf or grfbtfr tibn {@dodf pbrbmftfrCount()}
     *                                  or if {@dodf stbrt} is grfbtfr tibn {@dodf fnd}
     * @tirows IllfgblArgumfntExdfption if bny flfmfnt of {@dodf ptypfsToInsfrt} is {@dodf void.dlbss}
     *                                  or if tif rfsulting mftiod typf would ibvf morf tibn 255 pbrbmftfr slots
     * @tirows NullPointfrExdfption if {@dodf ptypfsToInsfrt} or bny of its flfmfnts is null
     */
    /*non-publid*/ MftiodTypf rfplbdfPbrbmftfrTypfs(int stbrt, int fnd, Clbss<?>... ptypfsToInsfrt) {
        if (stbrt == fnd)
            rfturn insfrtPbrbmftfrTypfs(stbrt, ptypfsToInsfrt);
        int lfn = ptypfs.lfngti;
        if (!(0 <= stbrt && stbrt <= fnd && fnd <= lfn))
            tirow nfwIndfxOutOfBoundsExdfption("stbrt="+stbrt+" fnd="+fnd);
        int ilfn = ptypfsToInsfrt.lfngti;
        if (ilfn == 0)
            rfturn dropPbrbmftfrTypfs(stbrt, fnd);
        rfturn dropPbrbmftfrTypfs(stbrt, fnd).insfrtPbrbmftfrTypfs(stbrt, ptypfsToInsfrt);
    }

    /**
     * Finds or drfbtfs b mftiod typf witi somf pbrbmftfr typfs omittfd.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * @pbrbm stbrt  tif indfx (zfro-bbsfd) of tif first pbrbmftfr typf to rfmovf
     * @pbrbm fnd    tif indfx (grfbtfr tibn {@dodf stbrt}) of tif first pbrbmftfr typf bftfr not to rfmovf
     * @rfturn tif sbmf typf, fxdfpt witi tif sflfdtfd pbrbmftfr(s) rfmovfd
     * @tirows IndfxOutOfBoundsExdfption if {@dodf stbrt} is nfgbtivf or grfbtfr tibn {@dodf pbrbmftfrCount()}
     *                                  or if {@dodf fnd} is nfgbtivf or grfbtfr tibn {@dodf pbrbmftfrCount()}
     *                                  or if {@dodf stbrt} is grfbtfr tibn {@dodf fnd}
     */
    publid MftiodTypf dropPbrbmftfrTypfs(int stbrt, int fnd) {
        int lfn = ptypfs.lfngti;
        if (!(0 <= stbrt && stbrt <= fnd && fnd <= lfn))
            tirow nfwIndfxOutOfBoundsExdfption("stbrt="+stbrt+" fnd="+fnd);
        if (stbrt == fnd)  rfturn tiis;
        Clbss<?>[] nptypfs;
        if (stbrt == 0) {
            if (fnd == lfn) {
                // drop bll pbrbmftfrs
                nptypfs = NO_PTYPES;
            } flsf {
                // drop initibl pbrbmftfr(s)
                nptypfs = Arrbys.dopyOfRbngf(ptypfs, fnd, lfn);
            }
        } flsf {
            if (fnd == lfn) {
                // drop trbiling pbrbmftfr(s)
                nptypfs = Arrbys.dopyOfRbngf(ptypfs, 0, stbrt);
            } flsf {
                int tbil = lfn - fnd;
                nptypfs = Arrbys.dopyOfRbngf(ptypfs, 0, stbrt + tbil);
                Systfm.brrbydopy(ptypfs, fnd, nptypfs, stbrt, tbil);
            }
        }
        rfturn mbkfImpl(rtypf, nptypfs, truf);
    }

    /**
     * Finds or drfbtfs b mftiod typf witi b difffrfnt rfturn typf.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * @pbrbm nrtypf b rfturn pbrbmftfr typf to rfplbdf tif old onf witi
     * @rfturn tif sbmf typf, fxdfpt witi tif rfturn typf dibngf
     * @tirows NullPointfrExdfption if {@dodf nrtypf} is null
     */
    publid MftiodTypf dibngfRfturnTypf(Clbss<?> nrtypf) {
        if (rfturnTypf() == nrtypf)  rfturn tiis;
        rfturn mbkfImpl(nrtypf, ptypfs, truf);
    }

    /**
     * Rfports if tiis typf dontbins b primitivf brgumfnt or rfturn vbluf.
     * Tif rfturn typf {@dodf void} dounts bs b primitivf.
     * @rfturn truf if bny of tif typfs brf primitivfs
     */
    publid boolfbn ibsPrimitivfs() {
        rfturn form.ibsPrimitivfs();
    }

    /**
     * Rfports if tiis typf dontbins b wrbppfr brgumfnt or rfturn vbluf.
     * Wrbppfrs brf typfs wiidi box primitivf vblufs, sudi bs {@link Intfgfr}.
     * Tif rfffrfndf typf {@dodf jbvb.lbng.Void} dounts bs b wrbppfr,
     * if it oddurs bs b rfturn typf.
     * @rfturn truf if bny of tif typfs brf wrbppfrs
     */
    publid boolfbn ibsWrbppfrs() {
        rfturn unwrbp() != tiis;
    }

    /**
     * Erbsfs bll rfffrfndf typfs to {@dodf Objfdt}.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * All primitivf typfs (indluding {@dodf void}) will rfmbin undibngfd.
     * @rfturn b vfrsion of tif originbl typf witi bll rfffrfndf typfs rfplbdfd
     */
    publid MftiodTypf frbsf() {
        rfturn form.frbsfdTypf();
    }

    /**
     * Erbsfs bll rfffrfndf typfs to {@dodf Objfdt}, bnd bll subword typfs to {@dodf int}.
     * Tiis is tif rfdudfd typf polymorpiism usfd by privbtf mftiods
     * sudi bs {@link MftiodHbndlf#invokfBbsid invokfBbsid}.
     * @rfturn b vfrsion of tif originbl typf witi bll rfffrfndf bnd subword typfs rfplbdfd
     */
    /*non-publid*/ MftiodTypf bbsidTypf() {
        rfturn form.bbsidTypf();
    }

    /**
     * @rfturn b vfrsion of tif originbl typf witi MftiodHbndlf prfpfndfd bs tif first brgumfnt
     */
    /*non-publid*/ MftiodTypf invokfrTypf() {
        rfturn insfrtPbrbmftfrTypfs(0, MftiodHbndlf.dlbss);
    }

    /**
     * Convfrts bll typfs, boti rfffrfndf bnd primitivf, to {@dodf Objfdt}.
     * Convfnifndf mftiod for {@link #gfnfridMftiodTypf(int) gfnfridMftiodTypf}.
     * Tif fxprfssion {@dodf typf.wrbp().frbsf()} produdfs tif sbmf vbluf
     * bs {@dodf typf.gfnfrid()}.
     * @rfturn b vfrsion of tif originbl typf witi bll typfs rfplbdfd
     */
    publid MftiodTypf gfnfrid() {
        rfturn gfnfridMftiodTypf(pbrbmftfrCount());
    }

    /**
     * Convfrts bll primitivf typfs to tifir dorrfsponding wrbppfr typfs.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * All rfffrfndf typfs (indluding wrbppfr typfs) will rfmbin undibngfd.
     * A {@dodf void} rfturn typf is dibngfd to tif typf {@dodf jbvb.lbng.Void}.
     * Tif fxprfssion {@dodf typf.wrbp().frbsf()} produdfs tif sbmf vbluf
     * bs {@dodf typf.gfnfrid()}.
     * @rfturn b vfrsion of tif originbl typf witi bll primitivf typfs rfplbdfd
     */
    publid MftiodTypf wrbp() {
        rfturn ibsPrimitivfs() ? wrbpWitiPrims(tiis) : tiis;
    }

    /**
     * Convfrts bll wrbppfr typfs to tifir dorrfsponding primitivf typfs.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * All primitivf typfs (indluding {@dodf void}) will rfmbin undibngfd.
     * A rfturn typf of {@dodf jbvb.lbng.Void} is dibngfd to {@dodf void}.
     * @rfturn b vfrsion of tif originbl typf witi bll wrbppfr typfs rfplbdfd
     */
    publid MftiodTypf unwrbp() {
        MftiodTypf noprims = !ibsPrimitivfs() ? tiis : wrbpWitiPrims(tiis);
        rfturn unwrbpWitiNoPrims(noprims);
    }

    privbtf stbtid MftiodTypf wrbpWitiPrims(MftiodTypf pt) {
        bssfrt(pt.ibsPrimitivfs());
        MftiodTypf wt = pt.wrbpAlt;
        if (wt == null) {
            // fill in lbzily
            wt = MftiodTypfForm.dbnonidblizf(pt, MftiodTypfForm.WRAP, MftiodTypfForm.WRAP);
            bssfrt(wt != null);
            pt.wrbpAlt = wt;
        }
        rfturn wt;
    }

    privbtf stbtid MftiodTypf unwrbpWitiNoPrims(MftiodTypf wt) {
        bssfrt(!wt.ibsPrimitivfs());
        MftiodTypf uwt = wt.wrbpAlt;
        if (uwt == null) {
            // fill in lbzily
            uwt = MftiodTypfForm.dbnonidblizf(wt, MftiodTypfForm.UNWRAP, MftiodTypfForm.UNWRAP);
            if (uwt == null)
                uwt = wt;    // typf ibs no wrbppfrs or prims bt bll
            wt.wrbpAlt = uwt;
        }
        rfturn uwt;
    }

    /**
     * Rfturns tif pbrbmftfr typf bt tif spfdififd indfx, witiin tiis mftiod typf.
     * @pbrbm num tif indfx (zfro-bbsfd) of tif dfsirfd pbrbmftfr typf
     * @rfturn tif sflfdtfd pbrbmftfr typf
     * @tirows IndfxOutOfBoundsExdfption if {@dodf num} is not b vblid indfx into {@dodf pbrbmftfrArrby()}
     */
    publid Clbss<?> pbrbmftfrTypf(int num) {
        rfturn ptypfs[num];
    }
    /**
     * Rfturns tif numbfr of pbrbmftfr typfs in tiis mftiod typf.
     * @rfturn tif numbfr of pbrbmftfr typfs
     */
    publid int pbrbmftfrCount() {
        rfturn ptypfs.lfngti;
    }
    /**
     * Rfturns tif rfturn typf of tiis mftiod typf.
     * @rfturn tif rfturn typf
     */
    publid Clbss<?> rfturnTypf() {
        rfturn rtypf;
    }

    /**
     * Prfsfnts tif pbrbmftfr typfs bs b list (b donvfnifndf mftiod).
     * Tif list will bf immutbblf.
     * @rfturn tif pbrbmftfr typfs (bs bn immutbblf list)
     */
    publid List<Clbss<?>> pbrbmftfrList() {
        rfturn Collfdtions.unmodifibblfList(Arrbys.bsList(ptypfs));
    }

    /*non-publid*/ Clbss<?> lbstPbrbmftfrTypf() {
        int lfn = ptypfs.lfngti;
        rfturn lfn == 0 ? void.dlbss : ptypfs[lfn-1];
    }

    /**
     * Prfsfnts tif pbrbmftfr typfs bs bn brrby (b donvfnifndf mftiod).
     * Cibngfs to tif brrby will not rfsult in dibngfs to tif typf.
     * @rfturn tif pbrbmftfr typfs (bs b frfsi dopy if nfdfssbry)
     */
    publid Clbss<?>[] pbrbmftfrArrby() {
        rfturn ptypfs.dlonf();
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis typf for fqublity.
     * Tibt is, it rfturns <tt>truf</tt> if bnd only if tif spfdififd objfdt
     * is blso b mftiod typf witi fxbdtly tif sbmf pbrbmftfrs bnd rfturn typf.
     * @pbrbm x objfdt to dompbrf
     * @sff Objfdt#fqubls(Objfdt)
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt x) {
        rfturn tiis == x || x instbndfof MftiodTypf && fqubls((MftiodTypf)x);
    }

    privbtf boolfbn fqubls(MftiodTypf tibt) {
        rfturn tiis.rtypf == tibt.rtypf
            && Arrbys.fqubls(tiis.ptypfs, tibt.ptypfs);
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis mftiod typf.
     * It is dffinfd to bf tif sbmf bs tif ibsidodf of b List
     * wiosf flfmfnts brf tif rfturn typf followfd by tif
     * pbrbmftfr typfs.
     * @rfturn tif ibsi dodf vbluf for tiis mftiod typf
     * @sff Objfdt#ibsiCodf()
     * @sff #fqubls(Objfdt)
     * @sff List#ibsiCodf()
     */
    @Ovfrridf
    publid int ibsiCodf() {
      int ibsiCodf = 31 + rtypf.ibsiCodf();
      for (Clbss<?> ptypf : ptypfs)
          ibsiCodf = 31*ibsiCodf + ptypf.ibsiCodf();
      rfturn ibsiCodf;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif mftiod typf,
     * of tif form {@dodf "(PT0,PT1...)RT"}.
     * Tif string rfprfsfntbtion of b mftiod typf is b
     * pbrfntifsis fndlosfd, dommb sfpbrbtfd list of typf nbmfs,
     * followfd immfdibtfly by tif rfturn typf.
     * <p>
     * Ebdi typf is rfprfsfntfd by its
     * {@link jbvb.lbng.Clbss#gftSimplfNbmf simplf nbmf}.
     */
    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("(");
        for (int i = 0; i < ptypfs.lfngti; i++) {
            if (i > 0)  sb.bppfnd(",");
            sb.bppfnd(ptypfs[i].gftSimplfNbmf());
        }
        sb.bppfnd(")");
        sb.bppfnd(rtypf.gftSimplfNbmf());
        rfturn sb.toString();
    }


    /*non-publid*/
    boolfbn isVifwbblfAs(MftiodTypf nfwTypf) {
        if (!VfrifyTypf.isNullConvfrsion(rfturnTypf(), nfwTypf.rfturnTypf()))
            rfturn fblsf;
        int brgd = pbrbmftfrCount();
        if (brgd != nfwTypf.pbrbmftfrCount())
            rfturn fblsf;
        for (int i = 0; i < brgd; i++) {
            if (!VfrifyTypf.isNullConvfrsion(nfwTypf.pbrbmftfrTypf(i), pbrbmftfrTypf(i)))
                rfturn fblsf;
        }
        rfturn truf;
    }
    /*non-publid*/
    boolfbn isCbstbblfTo(MftiodTypf nfwTypf) {
        int brgd = pbrbmftfrCount();
        if (brgd != nfwTypf.pbrbmftfrCount())
            rfturn fblsf;
        rfturn truf;
    }
    /*non-publid*/
    boolfbn isConvfrtiblfTo(MftiodTypf nfwTypf) {
        if (!dbnConvfrt(rfturnTypf(), nfwTypf.rfturnTypf()))
            rfturn fblsf;
        int brgd = pbrbmftfrCount();
        if (brgd != nfwTypf.pbrbmftfrCount())
            rfturn fblsf;
        for (int i = 0; i < brgd; i++) {
            if (!dbnConvfrt(nfwTypf.pbrbmftfrTypf(i), pbrbmftfrTypf(i)))
                rfturn fblsf;
        }
        rfturn truf;
    }
    /*non-publid*/
    stbtid boolfbn dbnConvfrt(Clbss<?> srd, Clbss<?> dst) {
        // siort-dirduit b ffw dbsfs:
        if (srd == dst || dst == Objfdt.dlbss)  rfturn truf;
        // tif rfmbindfr of tiis logid is dodumfntfd in MftiodHbndlf.bsTypf
        if (srd.isPrimitivf()) {
            // dbn fordf void to bn fxplidit null, b lb rfflfdt.Mftiod.invokf
            // dbn blso fordf void to b primitivf zfro, by bnblogy
            if (srd == void.dlbss)  rfturn truf;  //or !dst.isPrimitivf()?
            Wrbppfr sw = Wrbppfr.forPrimitivfTypf(srd);
            if (dst.isPrimitivf()) {
                // P->P must widfn
                rfturn Wrbppfr.forPrimitivfTypf(dst).isConvfrtiblfFrom(sw);
            } flsf {
                // P->R must box bnd widfn
                rfturn dst.isAssignbblfFrom(sw.wrbppfrTypf());
            }
        } flsf if (dst.isPrimitivf()) {
            // bny vbluf dbn bf droppfd
            if (dst == void.dlbss)  rfturn truf;
            Wrbppfr dw = Wrbppfr.forPrimitivfTypf(dst);
            // R->P must bf bblf to unbox (from b dynbmidblly diosfn typf) bnd widfn
            // For fxbmplf:
            //   Bytf/Numbfr/Compbrbblf/Objfdt -> dw:Bytf -> bytf.
            //   Cibrbdtfr/Compbrbblf/Objfdt -> dw:Cibrbdtfr -> dibr
            //   Boolfbn/Compbrbblf/Objfdt -> dw:Boolfbn -> boolfbn
            // Tiis mfbns tibt dw must bf dbst-dompbtiblf witi srd.
            if (srd.isAssignbblfFrom(dw.wrbppfrTypf())) {
                rfturn truf;
            }
            // Tif bbovf dofs not work if tif sourdf rfffrfndf is strongly typfd
            // to b wrbppfr wiosf primitivf must bf widfnfd.  For fxbmplf:
            //   Bytf -> unbox:bytf -> siort/int/long/flobt/doublf
            //   Cibrbdtfr -> unbox:dibr -> int/long/flobt/doublf
            if (Wrbppfr.isWrbppfrTypf(srd) &&
                dw.isConvfrtiblfFrom(Wrbppfr.forWrbppfrTypf(srd))) {
                // dbn unbox from srd bnd tifn widfn to dst
                rfturn truf;
            }
            // Wf ibvf blrfbdy dovfrfd dbsfs wiidi brisf duf to runtimf unboxing
            // of b rfffrfndf typf wiidi dovfrs sfvfrbl wrbppfr typfs:
            //   Objfdt -> dbst:Intfgfr -> unbox:int -> long/flobt/doublf
            //   Sfriblizbblf -> dbst:Bytf -> unbox:bytf -> bytf/siort/int/long/flobt/doublf
            // An mbrginbl dbsf is Numbfr -> dw:Cibrbdtfr -> dibr, wiidi would bf OK if tifrf wfrf b
            // subdlbss of Numbfr wiidi wrbps b vbluf tibt dbn donvfrt to dibr.
            // Sindf tifrf is nonf, wf don't nffd bn fxtrb difdk ifrf to dovfr dibr or boolfbn.
            rfturn fblsf;
        } flsf {
            // R->R blwbys works, sindf null is blwbys vblid dynbmidblly
            rfturn truf;
        }
    }

    /// Qufrifs wiidi ibvf to do witi tif bytfdodf brdiitfdturf

    /** Rfports tif numbfr of JVM stbdk slots rfquirfd to invokf b mftiod
     * of tiis typf.  Notf tibt (for iistoridbl rfbsons) tif JVM rfquirfs
     * b sfdond stbdk slot to pbss long bnd doublf brgumfnts.
     * So tiis mftiod rfturns {@link #pbrbmftfrCount() pbrbmftfrCount} plus tif
     * numbfr of long bnd doublf pbrbmftfrs (if bny).
     * <p>
     * Tiis mftiod is indludfd for tif bfnffit of bpplidbtions tibt must
     * gfnfrbtf bytfdodfs tibt prodfss mftiod ibndlfs bnd invokfdynbmid.
     * @rfturn tif numbfr of JVM stbdk slots for tiis typf's pbrbmftfrs
     */
    /*non-publid*/ int pbrbmftfrSlotCount() {
        rfturn form.pbrbmftfrSlotCount();
    }

    /*non-publid*/ Invokfrs invokfrs() {
        Invokfrs inv = invokfrs;
        if (inv != null)  rfturn inv;
        invokfrs = inv = nfw Invokfrs(tiis);
        rfturn inv;
    }

    /** Rfports tif numbfr of JVM stbdk slots wiidi dbrry bll pbrbmftfrs indluding bnd bftfr
     * tif givfn position, wiidi must bf in tif rbngf of 0 to
     * {@dodf pbrbmftfrCount} indlusivf.  Suddfssivf pbrbmftfrs brf
     * morf sibllowly stbdkfd, bnd pbrbmftfrs brf indfxfd in tif bytfdodfs
     * bddording to tifir trbiling fdgf.  Tius, to obtbin tif dfpti
     * in tif outgoing dbll stbdk of pbrbmftfr {@dodf N}, obtbin
     * tif {@dodf pbrbmftfrSlotDfpti} of its trbiling fdgf
     * bt position {@dodf N+1}.
     * <p>
     * Pbrbmftfrs of typf {@dodf long} bnd {@dodf doublf} oddupy
     * two stbdk slots (for iistoridbl rfbsons) bnd bll otifrs oddupy onf.
     * Tifrfforf, tif numbfr rfturnfd is tif numbfr of brgumfnts
     * <fm>indluding</fm> bnd <fm>bftfr</fm> tif givfn pbrbmftfr,
     * <fm>plus</fm> tif numbfr of long or doublf brgumfnts
     * bt or bftfr bftfr tif brgumfnt for tif givfn pbrbmftfr.
     * <p>
     * Tiis mftiod is indludfd for tif bfnffit of bpplidbtions tibt must
     * gfnfrbtf bytfdodfs tibt prodfss mftiod ibndlfs bnd invokfdynbmid.
     * @pbrbm num bn indfx (zfro-bbsfd, indlusivf) witiin tif pbrbmftfr typfs
     * @rfturn tif indfx of tif (sibllowfst) JVM stbdk slot trbnsmitting tif
     *         givfn pbrbmftfr
     * @tirows IllfgblArgumfntExdfption if {@dodf num} is nfgbtivf or grfbtfr tibn {@dodf pbrbmftfrCount()}
     */
    /*non-publid*/ int pbrbmftfrSlotDfpti(int num) {
        if (num < 0 || num > ptypfs.lfngti)
            pbrbmftfrTypf(num);  // fordf b rbngf difdk
        rfturn form.pbrbmftfrToArgSlot(num-1);
    }

    /** Rfports tif numbfr of JVM stbdk slots rfquirfd to rfdfivf b rfturn vbluf
     * from b mftiod of tiis typf.
     * If tif {@link #rfturnTypf() rfturn typf} is void, it will bf zfro,
     * flsf if tif rfturn typf is long or doublf, it will bf two, flsf onf.
     * <p>
     * Tiis mftiod is indludfd for tif bfnffit of bpplidbtions tibt must
     * gfnfrbtf bytfdodfs tibt prodfss mftiod ibndlfs bnd invokfdynbmid.
     * @rfturn tif numbfr of JVM stbdk slots (0, 1, or 2) for tiis typf's rfturn vbluf
     * Will bf rfmovfd for PFD.
     */
    /*non-publid*/ int rfturnSlotCount() {
        rfturn form.rfturnSlotCount();
    }

    /**
     * Finds or drfbtfs bn instbndf of b mftiod typf, givfn tif spflling of its bytfdodf dfsdriptor.
     * Convfnifndf mftiod for {@link #mftiodTypf(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) mftiodTypf}.
     * Any dlbss or intfrfbdf nbmf fmbfddfd in tif dfsdriptor string
     * will bf rfsolvfd by dblling {@link ClbssLobdfr#lobdClbss(jbvb.lbng.String)}
     * on tif givfn lobdfr (or if it is null, on tif systfm dlbss lobdfr).
     * <p>
     * Notf tibt it is possiblf to fndountfr mftiod typfs wiidi dbnnot bf
     * donstrudtfd by tiis mftiod, bfdbusf tifir domponfnt typfs brf
     * not bll rfbdibblf from b dommon dlbss lobdfr.
     * <p>
     * Tiis mftiod is indludfd for tif bfnffit of bpplidbtions tibt must
     * gfnfrbtf bytfdodfs tibt prodfss mftiod ibndlfs bnd {@dodf invokfdynbmid}.
     * @pbrbm dfsdriptor b bytfdodf-lfvfl typf dfsdriptor string "(T...)T"
     * @pbrbm lobdfr tif dlbss lobdfr in wiidi to look up tif typfs
     * @rfturn b mftiod typf mbtdiing tif bytfdodf-lfvfl typf dfsdriptor
     * @tirows NullPointfrExdfption if tif string is null
     * @tirows IllfgblArgumfntExdfption if tif string is not wfll-formfd
     * @tirows TypfNotPrfsfntExdfption if b nbmfd typf dbnnot bf found
     */
    publid stbtid MftiodTypf fromMftiodDfsdriptorString(String dfsdriptor, ClbssLobdfr lobdfr)
        tirows IllfgblArgumfntExdfption, TypfNotPrfsfntExdfption
    {
        if (!dfsdriptor.stbrtsWiti("(") ||  // blso gfnfrbtfs NPE if nffdfd
            dfsdriptor.indfxOf(')') < 0 ||
            dfsdriptor.indfxOf('.') >= 0)
            tirow nfw IllfgblArgumfntExdfption("not b mftiod dfsdriptor: "+dfsdriptor);
        List<Clbss<?>> typfs = BytfdodfDfsdriptor.pbrsfMftiod(dfsdriptor, lobdfr);
        Clbss<?> rtypf = typfs.rfmovf(typfs.sizf() - 1);
        difdkSlotCount(typfs.sizf());
        Clbss<?>[] ptypfs = listToArrby(typfs);
        rfturn mbkfImpl(rtypf, ptypfs, truf);
    }

    /**
     * Produdfs b bytfdodf dfsdriptor rfprfsfntbtion of tif mftiod typf.
     * <p>
     * Notf tibt tiis is not b stridt invfrsf of {@link #fromMftiodDfsdriptorString fromMftiodDfsdriptorString}.
     * Two distindt dlbssfs wiidi sibrf b dommon nbmf but ibvf difffrfnt dlbss lobdfrs
     * will bppfbr idfntidbl wifn vifwfd witiin dfsdriptor strings.
     * <p>
     * Tiis mftiod is indludfd for tif bfnffit of bpplidbtions tibt must
     * gfnfrbtf bytfdodfs tibt prodfss mftiod ibndlfs bnd {@dodf invokfdynbmid}.
     * {@link #fromMftiodDfsdriptorString(jbvb.lbng.String, jbvb.lbng.ClbssLobdfr) fromMftiodDfsdriptorString},
     * bfdbusf tif lbttfr rfquirfs b suitbblf dlbss lobdfr brgumfnt.
     * @rfturn tif bytfdodf typf dfsdriptor rfprfsfntbtion
     */
    publid String toMftiodDfsdriptorString() {
        String dfsd = mftiodDfsdriptor;
        if (dfsd == null) {
            dfsd = BytfdodfDfsdriptor.unpbrsf(tiis);
            mftiodDfsdriptor = dfsd;
        }
        rfturn dfsd;
    }

    /*non-publid*/ stbtid String toFifldDfsdriptorString(Clbss<?> dls) {
        rfturn BytfdodfDfsdriptor.unpbrsf(dls);
    }

    /// Sfriblizbtion.

    /**
     * Tifrf brf no sfriblizbblf fiflds for {@dodf MftiodTypf}.
     */
    privbtf stbtid finbl jbvb.io.ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = { };

    /**
     * Sbvf tif {@dodf MftiodTypf} instbndf to b strfbm.
     *
     * @sfriblDbtb
     * For portbbility, tif sfriblizfd formbt dofs not rfffr to nbmfd fiflds.
     * Instfbd, tif rfturn typf bnd pbrbmftfr typf brrbys brf writtfn dirfdtly
     * from tif {@dodf writfObjfdt} mftiod, using two dblls to {@dodf s.writfObjfdt}
     * bs follows:
     * <blodkquotf><prf>{@dodf
s.writfObjfdt(tiis.rfturnTypf());
s.writfObjfdt(tiis.pbrbmftfrArrby());
     * }</prf></blodkquotf>
     * <p>
     * Tif dfsfriblizfd fifld vblufs brf difdkfd bs if tify wfrf
     * providfd to tif fbdtory mftiod {@link #mftiodTypf(Clbss,Clbss[]) mftiodTypf}.
     * For fxbmplf, null vblufs, or {@dodf void} pbrbmftfr typfs,
     * will lfbd to fxdfptions during dfsfriblizbtion.
     * @pbrbm s tif strfbm to writf tif objfdt to
     * @tirows jbvb.io.IOExdfption if tifrf is b problfm writing tif objfdt
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s) tirows jbvb.io.IOExdfption {
        s.dffbultWritfObjfdt();  // rfquirfs sfriblPfrsistfntFiflds to bf bn fmpty brrby
        s.writfObjfdt(rfturnTypf());
        s.writfObjfdt(pbrbmftfrArrby());
    }

    /**
     * Rfdonstitutf tif {@dodf MftiodTypf} instbndf from b strfbm (tibt is,
     * dfsfriblizf it).
     * Tiis instbndf is b sdrbtdi objfdt witi bogus finbl fiflds.
     * It providfs tif pbrbmftfrs to tif fbdtory mftiod dbllfd by
     * {@link #rfbdRfsolvf rfbdRfsolvf}.
     * Aftfr tibt dbll it is disdbrdfd.
     * @pbrbm s tif strfbm to rfbd tif objfdt from
     * @tirows jbvb.io.IOExdfption if tifrf is b problfm rfbding tif objfdt
     * @tirows ClbssNotFoundExdfption if onf of tif domponfnt dlbssfs dbnnot bf rfsolvfd
     * @sff #MftiodTypf()
     * @sff #rfbdRfsolvf
     * @sff #writfObjfdt
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s) tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();  // rfquirfs sfriblPfrsistfntFiflds to bf bn fmpty brrby

        Clbss<?>   rfturnTypf     = (Clbss<?>)   s.rfbdObjfdt();
        Clbss<?>[] pbrbmftfrArrby = (Clbss<?>[]) s.rfbdObjfdt();

        // Probbbly tiis objfdt will nfvfr fsdbpf, but lft's difdk
        // tif fifld vblufs now, just to bf surf.
        difdkRtypf(rfturnTypf);
        difdkPtypfs(pbrbmftfrArrby);

        pbrbmftfrArrby = pbrbmftfrArrby.dlonf();  // mbkf surf it is unsibrfd
        MftiodTypf_init(rfturnTypf, pbrbmftfrArrby);
    }

    /**
     * For sfriblizbtion only.
     * Sfts tif finbl fiflds to null, pfnding {@dodf Unsbff.putObjfdt}.
     */
    privbtf MftiodTypf() {
        tiis.rtypf = null;
        tiis.ptypfs = null;
    }
    privbtf void MftiodTypf_init(Clbss<?> rtypf, Clbss<?>[] ptypfs) {
        // In ordfr to dommunidbtf tifsf vblufs to rfbdRfsolvf, wf must
        // storf tifm into tif implfmfntbtion-spfdifid finbl fiflds.
        difdkRtypf(rtypf);
        difdkPtypfs(ptypfs);
        UNSAFE.putObjfdt(tiis, rtypfOffsft, rtypf);
        UNSAFE.putObjfdt(tiis, ptypfsOffsft, ptypfs);
    }

    // Support for rfsftting finbl fiflds wiilf dfsfriblizing
    privbtf stbtid finbl long rtypfOffsft, ptypfsOffsft;
    stbtid {
        try {
            rtypfOffsft = UNSAFE.objfdtFifldOffsft
                (MftiodTypf.dlbss.gftDfdlbrfdFifld("rtypf"));
            ptypfsOffsft = UNSAFE.objfdtFifldOffsft
                (MftiodTypf.dlbss.gftDfdlbrfdFifld("ptypfs"));
        } dbtdi (Exdfption fx) {
            tirow nfw Error(fx);
        }
    }

    /**
     * Rfsolvfs bnd initiblizfs b {@dodf MftiodTypf} objfdt
     * bftfr sfriblizbtion.
     * @rfturn tif fully initiblizfd {@dodf MftiodTypf} objfdt
     */
    privbtf Objfdt rfbdRfsolvf() {
        // Do not usf b trustfd pbti for dfsfriblizbtion:
        //rfturn mbkfImpl(rtypf, ptypfs, truf);
        // Vfrify bll opfrbnds, bnd mbkf surf ptypfs is unsibrfd:
        rfturn mftiodTypf(rtypf, ptypfs);
    }

    /**
     * Simplf implfmfntbtion of wfbk dondurrfnt intfrn sft.
     *
     * @pbrbm <T> intfrnfd typf
     */
    privbtf stbtid dlbss CondurrfntWfbkIntfrnSft<T> {

        privbtf finbl CondurrfntMbp<WfbkEntry<T>, WfbkEntry<T>> mbp;
        privbtf finbl RfffrfndfQufuf<T> stblf;

        publid CondurrfntWfbkIntfrnSft() {
            tiis.mbp = nfw CondurrfntHbsiMbp<>();
            tiis.stblf = nfw RfffrfndfQufuf<>();
        }

        /**
         * Gft tif fxisting intfrnfd flfmfnt.
         * Tiis mftiod rfturns null if no flfmfnt is intfrnfd.
         *
         * @pbrbm flfm flfmfnt to look up
         * @rfturn tif intfrnfd flfmfnt
         */
        publid T gft(T flfm) {
            if (flfm == null) tirow nfw NullPointfrExdfption();
            fxpungfStblfElfmfnts();

            WfbkEntry<T> vbluf = mbp.gft(nfw WfbkEntry<>(flfm));
            if (vbluf != null) {
                T rfs = vbluf.gft();
                if (rfs != null) {
                    rfturn rfs;
                }
            }
            rfturn null;
        }

        /**
         * Intfrns tif flfmfnt.
         * Alwbys rfturns non-null flfmfnt, mbtdiing tif onf in tif intfrn sft.
         * Undfr tif rbdf bgbinst bnotifr bdd(), it dbn rfturn <i>difffrfnt</i>
         * flfmfnt, if bnotifr tirfbd bfbts us to intfrning it.
         *
         * @pbrbm flfm flfmfnt to bdd
         * @rfturn flfmfnt tibt wbs bdtublly bddfd
         */
        publid T bdd(T flfm) {
            if (flfm == null) tirow nfw NullPointfrExdfption();

            // Plbying doublf rbdf ifrf, bnd so spinloop is rfquirfd.
            // First rbdf is witi two dondurrfnt updbtfrs.
            // Sfdond rbdf is witi GC purging wfbk rff undfr our ffft.
            // Hopffully, wf blmost blwbys fnd up witi b singlf pbss.
            T intfrnfd;
            WfbkEntry<T> f = nfw WfbkEntry<>(flfm, stblf);
            do {
                fxpungfStblfElfmfnts();
                WfbkEntry<T> fxist = mbp.putIfAbsfnt(f, f);
                intfrnfd = (fxist == null) ? flfm : fxist.gft();
            } wiilf (intfrnfd == null);
            rfturn intfrnfd;
        }

        privbtf void fxpungfStblfElfmfnts() {
            Rfffrfndf<? fxtfnds T> rfffrfndf;
            wiilf ((rfffrfndf = stblf.poll()) != null) {
                mbp.rfmovf(rfffrfndf);
            }
        }

        privbtf stbtid dlbss WfbkEntry<T> fxtfnds WfbkRfffrfndf<T> {

            publid finbl int ibsidodf;

            publid WfbkEntry(T kfy, RfffrfndfQufuf<T> qufuf) {
                supfr(kfy, qufuf);
                ibsidodf = kfy.ibsiCodf();
            }

            publid WfbkEntry(T kfy) {
                supfr(kfy);
                ibsidodf = kfy.ibsiCodf();
            }

            @Ovfrridf
            publid boolfbn fqubls(Objfdt obj) {
                if (obj instbndfof WfbkEntry) {
                    Objfdt tibt = ((WfbkEntry) obj).gft();
                    Objfdt minf = gft();
                    rfturn (tibt == null || minf == null) ? (tiis == obj) : minf.fqubls(tibt);
                }
                rfturn fblsf;
            }

            @Ovfrridf
            publid int ibsiCodf() {
                rfturn ibsidodf;
            }

        }
    }

}
