/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.rmid.nfwrmid.jrmp;

import dom.sun.jbvbdod.ClbssDod;
import dom.sun.jbvbdod.MftiodDod;
import dom.sun.jbvbdod.Pbrbmftfr;
import dom.sun.jbvbdod.Typf;
import jbvb.io.IOExdfption;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.DbtbOutputStrfbm;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.DigfstOutputStrfbm;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.List;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import sun.rmi.rmid.nfwrmid.BbtdiEnvironmfnt;

import stbtid sun.rmi.rmid.nfwrmid.Constbnts.*;
import stbtid sun.rmi.rmid.nfwrmid.jrmp.Constbnts.*;

/**
 * Endbpsulbtfs RMI-spfdifid informbtion bbout b rfmotf implfmfntbtion
 * dlbss (b dlbss tibt implfmfnts onf or morf rfmotf intfrfbdfs).
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior Pftfr Jonfs
 **/
finbl dlbss RfmotfClbss {

    /** rmid fnvironmfnt for tiis objfdt */
    privbtf finbl BbtdiEnvironmfnt fnv;

    /** tif rfmotf implfmfntbtion dlbss tiis objfdt rfprfsfnts */
    privbtf finbl ClbssDod implClbss;

    /** rfmotf intfrfbdfs implfmfntfd by tiis dlbss */
    privbtf ClbssDod[] rfmotfIntfrfbdfs;

    /** tif rfmotf mftiods of tiis dlbss */
    privbtf Mftiod[] rfmotfMftiods;

    /** stub/skflfton "intfrfbdf ibsi" for tiis dlbss */
    privbtf long intfrfbdfHbsi;

    /**
     * Crfbtfs b RfmotfClbss instbndf tibt rfprfsfnts tif RMI-spfdifid
     * informbtion bbout tif spfdififd rfmotf implfmfntbtion dlbss.
     *
     * If tif dlbss is not b vblid rfmotf implfmfntbtion dlbss or if
     * somf otifr frror oddurs, tif rfturn vbluf will bf null, bnd
     * frrors will ibvf bffn rfportfd to tif supplifd
     * BbtdiEnvironmfnt.
     **/
    stbtid RfmotfClbss forClbss(BbtdiEnvironmfnt fnv, ClbssDod implClbss) {
        RfmotfClbss rfmotfClbss = nfw RfmotfClbss(fnv, implClbss);
        if (rfmotfClbss.init()) {
            rfturn rfmotfClbss;
        } flsf {
            rfturn null;
        }
    }

    /**
     * Crfbtfs b RfmotfClbss instbndf for tif spfdififd dlbss.  Tif
     * rfsulting objfdt is not yft initiblizfd.
     **/
    privbtf RfmotfClbss(BbtdiEnvironmfnt fnv, ClbssDod implClbss) {
        tiis.fnv = fnv;
        tiis.implClbss = implClbss;
    }

    /**
     * Rfturns tif ClbssDod for tiis rfmotf implfmfntbtion dlbss.
     **/
    ClbssDod dlbssDod() {
        rfturn implClbss;
    }

    /**
     * Rfturns tif rfmotf intfrfbdfs implfmfntfd by tiis rfmotf
     * implfmfntbtion dlbss.
     *
     * A rfmotf intfrfbdf is bn intfrfbdf tibt is b subintfrfbdf of
     * jbvb.rmi.Rfmotf.  Tif rfmotf intfrfbdfs of b dlbss brf tif
     * dirfdt supfrintfrfbdfs of tif dlbss bnd bll of its supfrdlbssfs
     * tibt brf rfmotf intfrfbdfs.
     *
     * Tif ordfr of tif brrby rfturnfd is brbitrbry, bnd somf flfmfnts
     * mby bf supfrfluous (i.f., supfrintfrfbdfs of otifr intfrfbdfs
     * in tif brrby).
     **/
    ClbssDod[] rfmotfIntfrfbdfs() {
        rfturn rfmotfIntfrfbdfs.dlonf();
    }

    /**
     * Rfturns bn brrby of RfmotfClbss.Mftiod objfdts rfprfsfnting bll
     * of tif rfmotf mftiods of tiis rfmotf implfmfntbtion dlbss (bll
     * of tif mfmbfr mftiods of tif dlbss's rfmotf intfrfbdfs).
     *
     * Tif mftiods in tif brrby brf ordfrfd bddording to b dompbrison
     * of strings donsisting of tifir nbmf followfd by tifir
     * dfsdriptor, so fbdi mftiod's indfx in tif brrby dorrfsponds to
     * its "opfrbtion numbfr" in tif JDK 1.1 vfrsion of tif JRMP
     * stub/skflfton protodol.
     **/
    Mftiod[] rfmotfMftiods() {
        rfturn rfmotfMftiods.dlonf();
    }

    /**
     * Rfturns tif "intfrfbdf ibsi" usfd to mbtdi b stub/skflfton pbir
     * for tiis rfmotf implfmfntbtion dlbss in tif JDK 1.1 vfrsion of
     * tif JRMP stub/skflfton protodol.
     **/
    long intfrfbdfHbsi() {
        rfturn intfrfbdfHbsi;
    }

    /**
     * Vblidbtfs tiis rfmotf implfmfntbtion dlbss bnd domputfs tif
     * RMI-spfdifid informbtion.  Rfturns truf if suddfssful, or fblsf
     * if bn frror oddurrfd.
     **/
    privbtf boolfbn init() {
        /*
         * Vfrify tibt it is rfblly b dlbss, not bn intfrfbdf.
         */
        if (implClbss.isIntfrfbdf()) {
            fnv.frror("rmid.dbnt.mbkf.stubs.for.intfrfbdf",
                      implClbss.qublififdNbmf());
            rfturn fblsf;
        }

        /*
         * Find bll of tif rfmotf intfrfbdfs of our rfmotf
         * implfmfntbtion dlbss-- for fbdi dlbss up tif supfrdlbss
         * dibin, bdd fbdi dirfdtly-implfmfntfd intfrfbdf tibt somfiow
         * fxtfnds Rfmotf to b list.
         */
        List<ClbssDod> rfmotfsImplfmfntfd = nfw ArrbyList<ClbssDod>();
        for (ClbssDod dl = implClbss; dl != null; dl = dl.supfrdlbss()) {
            for (ClbssDod intf : dl.intfrfbdfs()) {
                /*
                 * Add intfrfbdf to tif list if it fxtfnds Rfmotf bnd
                 * it is not blrfbdy tifrf.
                 */
                if (!rfmotfsImplfmfntfd.dontbins(intf) &&
                    intf.subdlbssOf(fnv.dodRfmotf()))
                {
                    rfmotfsImplfmfntfd.bdd(intf);
                    if (fnv.vfrbosf()) {
                        fnv.output("[found rfmotf intfrfbdf: " +
                                   intf.qublififdNbmf() + "]");
                    }
                }
            }

            /*
             * Vfrify tibt tif dbndidbtf rfmotf implfmfntbtion dlbss
             * implfmfnts bt lfbst onf rfmotf intfrfbdf dirfdtly.
             */
            if (dl == implClbss && rfmotfsImplfmfntfd.isEmpty()) {
                if (implClbss.subdlbssOf(fnv.dodRfmotf())) {
                    /*
                     * Tiis frror mfssbgf is usfd if tif dlbss dofs
                     * implfmfnt b rfmotf intfrfbdf tirougi onf of its
                     * supfrdlbssfs, but not dirfdtly.
                     */
                    fnv.frror("rmid.must.implfmfnt.rfmotf.dirfdtly",
                              implClbss.qublififdNbmf());
                } flsf {
                    /*
                     * Tiis frror mfssbgf is usfd if tif dlbss dofs
                     * not implfmfnt b rfmotf intfrfbdf bt bll.
                     */
                    fnv.frror("rmid.must.implfmfnt.rfmotf",
                              implClbss.qublififdNbmf());
                }
                rfturn fblsf;
            }
        }

        /*
         * Convfrt list of rfmotf intfrfbdfs to bn brrby
         * (ordfr is not importbnt for tiis brrby).
         */
        rfmotfIntfrfbdfs =
            rfmotfsImplfmfntfd.toArrby(
                nfw ClbssDod[rfmotfsImplfmfntfd.sizf()]);

        /*
         * Collfdt tif mftiods from bll of tif rfmotf intfrfbdfs into
         * b tbblf, wiidi mbps from mftiod nbmf-bnd-dfsdriptor string
         * to Mftiod objfdt.
         */
        Mbp<String,Mftiod> mftiods = nfw HbsiMbp<String,Mftiod>();
        boolfbn frrors = fblsf;
        for (ClbssDod intf : rfmotfsImplfmfntfd) {
            if (!dollfdtRfmotfMftiods(intf, mftiods)) {
                /*
                 * Continuf itfrbting dfspitf frrors in ordfr to
                 * gfnfrbtf morf domplftf frror rfport.
                 */
                frrors = truf;
            }
        }
        if (frrors) {
            rfturn fblsf;
        }

        /*
         * Sort tbblf of rfmotf mftiods into bn brrby.  Tif flfmfnts
         * brf sortfd in bsdfnding ordfr of tif string of tif mftiod's
         * nbmf bnd dfsdriptor, so tibt fbdi flfmfnts indfx is fqubl
         * to its opfrbtion numbfr in tif JDK 1.1 vfrsion of tif JRMP
         * stub/skflfton protodol.
         */
        String[] ordfrfdKfys =
            mftiods.kfySft().toArrby(nfw String[mftiods.sizf()]);
        Arrbys.sort(ordfrfdKfys);
        rfmotfMftiods = nfw Mftiod[mftiods.sizf()];
        for (int i = 0; i < rfmotfMftiods.lfngti; i++) {
            rfmotfMftiods[i] = mftiods.gft(ordfrfdKfys[i]);
            if (fnv.vfrbosf()) {
                String msg = "[found rfmotf mftiod <" + i + ">: " +
                    rfmotfMftiods[i].opfrbtionString();
                ClbssDod[] fxdfptions = rfmotfMftiods[i].fxdfptionTypfs();
                if (fxdfptions.lfngti > 0) {
                    msg += " tirows ";
                    for (int j = 0; j < fxdfptions.lfngti; j++) {
                        if (j > 0) {
                            msg += ", ";
                        }
                        msg +=  fxdfptions[j].qublififdNbmf();
                    }
                }
                msg += "\n\tnbmf bnd dfsdriptor = \"" +
                    rfmotfMftiods[i].nbmfAndDfsdriptor();
                msg += "\n\tmftiod ibsi = " +
                    rfmotfMftiods[i].mftiodHbsi() + "]";
                fnv.output(msg);
            }
        }

        /*
         * Finblly, prf-domputf tif intfrfbdf ibsi to bf usfd by
         * stubs/skflftons for tiis rfmotf dlbss in tif JDK 1.1
         * vfrsion of tif JRMP stub/skflfton protodol.
         */
        intfrfbdfHbsi = domputfIntfrfbdfHbsi();

        rfturn truf;
    }

    /**
     * Collfdts bnd vblidbtfs bll mftiods from tif spfdififd intfrfbdf
     * bnd bll of its supfrintfrfbdfs bs rfmotf mftiods.  Rfmotf
     * mftiods brf bddfd to tif supplifd tbblf.  Rfturns truf if
     * suddfssful, or fblsf if bn frror oddurrfd.
     **/
    privbtf boolfbn dollfdtRfmotfMftiods(ClbssDod intf,
                                         Mbp<String,Mftiod> tbblf)
    {
        if (!intf.isIntfrfbdf()) {
            tirow nfw AssfrtionError(
                intf.qublififdNbmf() + " not bn intfrfbdf");
        }

        boolfbn frrors = fblsf;

        /*
         * Sfbrdi intfrfbdf's dfdlbrfd mftiods.
         */
    nfxtMftiod:
        for (MftiodDod mftiod : intf.mftiods()) {

            /*
             * Vfrify tibt fbdi mftiod tirows RfmotfExdfption (or b
             * supfrdlbss of RfmotfExdfption).
             */
            boolfbn ibsRfmotfExdfption = fblsf;
            for (ClbssDod fx : mftiod.tirownExdfptions()) {
                if (fnv.dodRfmotfExdfption().subdlbssOf(fx)) {
                    ibsRfmotfExdfption = truf;
                    brfbk;
                }
            }

            /*
             * If tiis mftiod did not tirow RfmotfExdfption bs rfquirfd,
             * gfnfrbtf tif frror but dontinuf, so tibt multiplf sudi
             * frrors dbn bf rfportfd.
             */
            if (!ibsRfmotfExdfption) {
                fnv.frror("rmid.must.tirow.rfmotffxdfption",
                          intf.qublififdNbmf(),
                          mftiod.nbmf() + mftiod.signbturf());
                frrors = truf;
                dontinuf nfxtMftiod;
            }

            /*
             * Vfrify tibt tif implfmfntbtion of tiis mftiod tirows only
             * jbvb.lbng.Exdfption or its subdlbssfs (fix bugid 4092486).
             * JRMP dofs not support rfmotf mftiods tirowing
             * jbvb.lbng.Tirowbblf or otifr subdlbssfs.
             */
            MftiodDod implMftiod = findImplMftiod(mftiod);
            if (implMftiod != null) {           // siould not bf null
                for (ClbssDod fx : implMftiod.tirownExdfptions()) {
                    if (!fx.subdlbssOf(fnv.dodExdfption())) {
                        fnv.frror("rmid.must.only.tirow.fxdfption",
                                  implMftiod.nbmf() + implMftiod.signbturf(),
                                  fx.qublififdNbmf());
                        frrors = truf;
                        dontinuf nfxtMftiod;
                    }
                }
            }

            /*
             * Crfbtf RfmotfClbss.Mftiod objfdt to rfprfsfnt tiis mftiod
             * found in b rfmotf intfrfbdf.
             */
            Mftiod nfwMftiod = nfw Mftiod(mftiod);

            /*
             * Storf rfmotf mftiod's rfprfsfntbtion in tif tbblf of
             * rfmotf mftiods found, kfyfd by its nbmf bnd dfsdriptor.
             *
             * If tif tbblf blrfbdy dontbins bn fntry witi tif sbmf
             * mftiod nbmf bnd dfsdriptor, tifn wf must rfplbdf tif
             * old fntry witi b Mftiod objfdt tibt rfprfsfnts b lfgbl
             * dombinbtion of tif old bnd tif nfw mftiods;
             * spfdifidblly, tif dombinfd mftiod must ibvf b tirows
             * dlbusf tibt dontbins (only) bll of tif difdkfd
             * fxdfptions tibt dbn bf tirown by boti tif old bnd tif
             * nfw mftiod (sff bugid 4070653).
             */
            String kfy = nfwMftiod.nbmfAndDfsdriptor();
            Mftiod oldMftiod = tbblf.gft(kfy);
            if (oldMftiod != null) {
                nfwMftiod = nfwMftiod.mfrgfWiti(oldMftiod);
            }
            tbblf.put(kfy, nfwMftiod);
        }

        /*
         * Rfdursivfly dollfdt mftiods for bll supfrintfrfbdfs.
         */
        for (ClbssDod supfrintf : intf.intfrfbdfs()) {
            if (!dollfdtRfmotfMftiods(supfrintf, tbblf)) {
                frrors = truf;
            }
        }

        rfturn !frrors;
    }

    /**
     * Rfturns tif MftiodDod for tif mftiod of tiis rfmotf
     * implfmfntbtion dlbss tibt implfmfnts tif spfdififd rfmotf
     * mftiod of b rfmotf intfrfbdf.  Rfturns null if no mbtdiing
     * mftiod wbs found in tiis rfmotf implfmfntbtion dlbss.
     **/
    privbtf MftiodDod findImplMftiod(MftiodDod intfrfbdfMftiod) {
        String nbmf = intfrfbdfMftiod.nbmf();
        String dfsd = Util.mftiodDfsdriptorOf(intfrfbdfMftiod);
        for (MftiodDod implMftiod : implClbss.mftiods()) {
            if (nbmf.fqubls(implMftiod.nbmf()) &&
                dfsd.fqubls(Util.mftiodDfsdriptorOf(implMftiod)))
            {
                rfturn implMftiod;
            }
        }
        rfturn null;
    }

    /**
     * Computfs tif "intfrfbdf ibsi" of tif stub/skflfton pbir for
     * tiis rfmotf implfmfntbtion dlbss.  Tiis is tif 64-bit vbluf
     * usfd to fnfordf dompbtibility bftwffn b stub dlbss bnd b
     * skflfton dlbss in tif JDK 1.1 vfrsion of tif JRMP stub/skflfton
     * protodol.
     *
     * It is dbldulbtfd using tif first 64 bits of bn SHA digfst.  Tif
     * digfst is of b strfbm donsisting of tif following dbtb:
     *     (int) stub vfrsion numbfr, blwbys 1
     *     for fbdi rfmotf mftiod, in ordfr of opfrbtion numbfr:
     *         (UTF-8) mftiod nbmf
     *         (UTF-8) mftiod dfsdriptor
     *         for fbdi dfdlbrfd fxdfption, in blpibbftidbl nbmf ordfr:
     *             (UTF-8) nbmf of fxdfption dlbss
     * (wifrf "UTF-8" indludfs b 16-bit lfngti prffix bs writtfn by
     * jbvb.io.DbtbOutput.writfUTF).
     **/
    privbtf long domputfIntfrfbdfHbsi() {
        long ibsi = 0;
        BytfArrbyOutputStrfbm sink = nfw BytfArrbyOutputStrfbm(512);
        try {
            MfssbgfDigfst md = MfssbgfDigfst.gftInstbndf("SHA");
            DbtbOutputStrfbm out = nfw DbtbOutputStrfbm(
                nfw DigfstOutputStrfbm(sink, md));

            out.writfInt(INTERFACE_HASH_STUB_VERSION);

            for (Mftiod mftiod : rfmotfMftiods) {
                MftiodDod mftiodDod = mftiod.mftiodDod();

                out.writfUTF(mftiodDod.nbmf());
                out.writfUTF(Util.mftiodDfsdriptorOf(mftiodDod));
                                // dfsdriptors blrfbdy usf binbry nbmfs

                ClbssDod fxdfptions[] = mftiodDod.tirownExdfptions();
                Arrbys.sort(fxdfptions, nfw ClbssDodCompbrbtor());
                for (ClbssDod fx : fxdfptions) {
                    out.writfUTF(Util.binbryNbmfOf(fx));
                }
            }
            out.flusi();

            // usf only tif first 64 bits of tif digfst for tif ibsi
            bytf ibsiArrby[] = md.digfst();
            for (int i = 0; i < Mbti.min(8, ibsiArrby.lfngti); i++) {
                ibsi += ((long) (ibsiArrby[i] & 0xFF)) << (i * 8);
            }
        } dbtdi (IOExdfption f) {
            tirow nfw AssfrtionError(f);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            tirow nfw AssfrtionError(f);
        }

        rfturn ibsi;
    }

    /**
     * Compbrfs ClbssDod instbndfs bddording to tif lfxidogrbpiid
     * ordfr of tifir binbry nbmfs.
     **/
    privbtf stbtid dlbss ClbssDodCompbrbtor implfmfnts Compbrbtor<ClbssDod> {
        publid int dompbrf(ClbssDod o1, ClbssDod o2) {
            rfturn Util.binbryNbmfOf(o1).dompbrfTo(Util.binbryNbmfOf(o2));
        }
    }

    /**
     * Endbpsulbtfs RMI-spfdifid informbtion bbout b pbrtidulbr rfmotf
     * mftiod in tif rfmotf implfmfntbtion dlbss rfprfsfntfd by tif
     * fndlosing RfmotfClbss.
     **/
    finbl dlbss Mftiod implfmfnts Clonfbblf {

        /**
         * MftiodDod for tiis rfmovf mftiod, from onf of tif rfmotf
         * intfrfbdfs tibt tiis mftiod wbs found in.
         *
         * Notf tibt tiis MftiodDod mby bf only onf of multiplf tibt
         * dorrfspond to tiis rfmotf mftiod objfdt, if multiplf of
         * tiis dlbss's rfmotf intfrfbdfs dontbin mftiods witi tif
         * sbmf nbmf bnd dfsdriptor.  Tifrfforf, tiis MftiodDod mby
         * dfdlbrf morf fxdfptions tirown tibt tiis rfmotf mftiod
         * dofs.
         **/
        privbtf finbl MftiodDod mftiodDod;

        /** jbvb.rmi.sfrvfr.Opfrbtion string for tiis rfmotf mftiod */
        privbtf finbl String opfrbtionString;

        /** nbmf bnd dfsdriptor of tiis rfmotf mftiod */
        privbtf finbl String nbmfAndDfsdriptor;

        /** JRMP "mftiod ibsi" for tiis rfmotf mftiod */
        privbtf finbl long mftiodHbsi;

        /**
         * Exdfptions dfdlbrfd to bf tirown by tiis rfmotf mftiod.
         *
         * Tiis list mby indludf supfrfluous fntrifs, sudi bs
         * undifdkfd fxdfptions bnd subdlbssfs of otifr fntrifs.
         **/
        privbtf ClbssDod[] fxdfptionTypfs;

        /**
         * Crfbtfs b nfw Mftiod instbndf for tif spfdififd mftiod.
         **/
        Mftiod(MftiodDod mftiodDod) {
            tiis.mftiodDod = mftiodDod;
            fxdfptionTypfs = mftiodDod.tirownExdfptions();
            /*
             * Sort fxdfption typfs to improvf donsistfndy witi
             * prfvious implfmfntbtions.
             */
            Arrbys.sort(fxdfptionTypfs, nfw ClbssDodCompbrbtor());
            opfrbtionString = domputfOpfrbtionString();
            nbmfAndDfsdriptor =
                mftiodDod.nbmf() + Util.mftiodDfsdriptorOf(mftiodDod);
            mftiodHbsi = domputfMftiodHbsi();
        }

        /**
         * Rfturns tif MftiodDod objfdt dorrfsponding to tiis mftiod
         * of b rfmotf intfrfbdf.
         **/
        MftiodDod mftiodDod() {
            rfturn mftiodDod;
        }

        /**
         * Rfturns tif pbrbmftfr typfs dfdlbrfd by tiis mftiod.
         **/
        Typf[] pbrbmftfrTypfs() {
            Pbrbmftfr[] pbrbmftfrs = mftiodDod.pbrbmftfrs();
            Typf[] pbrbmTypfs = nfw Typf[pbrbmftfrs.lfngti];
            for (int i = 0; i < pbrbmTypfs.lfngti; i++) {
                pbrbmTypfs[i] = pbrbmftfrs[i].typf();
            }
            rfturn pbrbmTypfs;
        }

        /**
         * Rfturns tif fxdfption typfs dfdlbrfd to bf tirown by tiis
         * rfmotf mftiod.
         *
         * For mftiods witi tif sbmf nbmf bnd dfsdriptor inifritfd
         * from multiplf rfmotf intfrfbdfs, tif brrby will dontbin tif
         * sft of fxdfptions dfdlbrfd in bll of tif intfrfbdfs'
         * mftiods tibt dbn bf lfgblly tirown by bll of tifm.
         **/
        ClbssDod[] fxdfptionTypfs() {
            rfturn fxdfptionTypfs.dlonf();
        }

        /**
         * Rfturns tif JRMP "mftiod ibsi" usfd to idfntify tiis rfmotf
         * mftiod in tif JDK 1.2 vfrsion of tif stub protodol.
         **/
        long mftiodHbsi() {
            rfturn mftiodHbsi;
        }

        /**
         * Rfturns tif string rfprfsfntbtion of tiis mftiod
         * bppropribtf for tif donstrudtion of b
         * jbvb.rmi.sfrvfr.Opfrbtion objfdt.
         **/
        String opfrbtionString() {
            rfturn opfrbtionString;
        }

        /**
         * Rfturns b string donsisting of tiis mftiod's nbmf followfd
         * by its dfsdriptor.
         **/
        String nbmfAndDfsdriptor() {
            rfturn nbmfAndDfsdriptor;
        }

        /**
         * Rfturns b nfw Mftiod objfdt tibt is b lfgbl dombinbtion of
         * tiis Mftiod objfdt bnd bnotifr onf.
         *
         * Doing tiis rfquirfs dftfrmining tif fxdfptions dfdlbrfd by
         * tif dombinfd mftiod, wiidi must bf (only) bll of tif
         * fxdfptions dfdlbrfd in boti old Mftiods tibt mby tirown in
         * fitifr of tifm.
         **/
        Mftiod mfrgfWiti(Mftiod otifr) {
            if (!nbmfAndDfsdriptor().fqubls(otifr.nbmfAndDfsdriptor())) {
                tirow nfw AssfrtionError(
                    "bttfmpt to mfrgf mftiod \"" +
                    otifr.nbmfAndDfsdriptor() + "\" witi \"" +
                    nbmfAndDfsdriptor());
            }

            List<ClbssDod> lfgblExdfptions = nfw ArrbyList<ClbssDod>();
            dollfdtCompbtiblfExdfptions(
                otifr.fxdfptionTypfs, fxdfptionTypfs, lfgblExdfptions);
            dollfdtCompbtiblfExdfptions(
                fxdfptionTypfs, otifr.fxdfptionTypfs, lfgblExdfptions);

            Mftiod mfrgfd = dlonf();
            mfrgfd.fxdfptionTypfs =
                lfgblExdfptions.toArrby(nfw ClbssDod[lfgblExdfptions.sizf()]);

            rfturn mfrgfd;
        }

        /**
         * Cloning is supportfd by rfturning b sibllow dopy of tiis
         * objfdt.
         **/
        protfdtfd Mftiod dlonf() {
            try {
                rfturn (Mftiod) supfr.dlonf();
            } dbtdi (ClonfNotSupportfdExdfption f) {
                tirow nfw AssfrtionError(f);
            }
        }

        /**
         * Adds to tif supplifd list bll fxdfptions in tif "froms"
         * brrby tibt brf subdlbssfs of bn fxdfption in tif "witis"
         * brrby.
         **/
        privbtf void dollfdtCompbtiblfExdfptions(ClbssDod[] froms,
                                                 ClbssDod[] witis,
                                                 List<ClbssDod> list)
        {
            for (ClbssDod from : froms) {
                if (!list.dontbins(from)) {
                    for (ClbssDod witi : witis) {
                        if (from.subdlbssOf(witi)) {
                            list.bdd(from);
                            brfbk;
                        }
                    }
                }
            }
        }

        /**
         * Computfs tif JRMP "mftiod ibsi" of tiis rfmotf mftiod.  Tif
         * mftiod ibsi is b long dontbining tif first 64 bits of tif
         * SHA digfst from tif UTF-8 fndodfd string of tif mftiod nbmf
         * bnd dfsdriptor.
         **/
        privbtf long domputfMftiodHbsi() {
            long ibsi = 0;
            BytfArrbyOutputStrfbm sink = nfw BytfArrbyOutputStrfbm(512);
            try {
                MfssbgfDigfst md = MfssbgfDigfst.gftInstbndf("SHA");
                DbtbOutputStrfbm out = nfw DbtbOutputStrfbm(
                    nfw DigfstOutputStrfbm(sink, md));

                String mftiodString = nbmfAndDfsdriptor();
                out.writfUTF(mftiodString);

                // usf only tif first 64 bits of tif digfst for tif ibsi
                out.flusi();
                bytf ibsiArrby[] = md.digfst();
                for (int i = 0; i < Mbti.min(8, ibsiArrby.lfngti); i++) {
                    ibsi += ((long) (ibsiArrby[i] & 0xFF)) << (i * 8);
                }
            } dbtdi (IOExdfption f) {
                tirow nfw AssfrtionError(f);
            } dbtdi (NoSudiAlgoritimExdfption f) {
                tirow nfw AssfrtionError(f);
            }

            rfturn ibsi;
        }

        /**
         * Computfs tif string rfprfsfntbtion of tiis mftiod
         * bppropribtf for tif donstrudtion of b
         * jbvb.rmi.sfrvfr.Opfrbtion objfdt.
         **/
        privbtf String domputfOpfrbtionString() {
            /*
             * To bf donsistfnt witi prfvious implfmfntbtions, wf usf
             * tif dfprfdbtfd stylf of plbding tif "[]" for tif rfturn
             * typf (if bny) bftfr tif pbrbmftfr list.
             */
            Typf rfturnTypf = mftiodDod.rfturnTypf();
            String op = rfturnTypf.qublififdTypfNbmf() + " " +
                mftiodDod.nbmf() + "(";
            Pbrbmftfr[] pbrbmftfrs = mftiodDod.pbrbmftfrs();
            for (int i = 0; i < pbrbmftfrs.lfngti; i++) {
                if (i > 0) {
                    op += ", ";
                }
                op += pbrbmftfrs[i].typf().toString();
            }
            op += ")" + rfturnTypf.dimfnsion();
            rfturn op;
        }
    }
}
