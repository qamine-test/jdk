/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.rmid.nfwrmid;

import dom.sun.jbvbdod.ClbssDod;
import dom.sun.jbvbdod.RootDod;
import jbvb.io.Filf;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.PrintWritfr;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import sun.rmi.rmid.nfwrmid.jrmp.JrmpGfnfrbtor;
import sun.tools.util.CommbndLinf;

/**
 * Tif rmid front fnd.  Tiis dlbss dontbins tif "mbin" mftiod for rmid
 * dommbnd linf invodbtion.
 *
 * A Mbin instbndf dontbins tif strfbm to output frror mfssbgfs bnd
 * otifr dibgnostids to.
 *
 * An rmid dompilbtion bbtdi (for fxbmplf, onf rmid dommbnd linf
 * invodbtion) is fxfdutfd by invoking tif "dompilf" mftiod of b Mbin
 * instbndf.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * NOTE: If bnd wifn tifrf is b J2SE API for invoking SDK tools, tiis
 * dlbss siould bf updbtfd to support tibt API.
 *
 * NOTE: Tiis dlbss is tif front fnd for b "nfw" rmid implfmfntbtion,
 * wiidi usfs jbvbdod bnd tif dodlft API for rfbding dlbss filfs bnd
 * jbvbd for dompiling gfnfrbtfd sourdf filfs.  Tiis implfmfntbtion is
 * indomplftf: it lbdks bny CORBA-bbsfd bbdk fnd implfmfntbtions, bnd
 * tius tif dommbnd linf options "-idl", "-iiop", bnd tifir rflbtfd
 * options brf not yft supportfd.  Tif front fnd for tif "old",
 * oldjbvbd-bbsfd rmid implfmfntbtion is sun.rmi.rmid.Mbin.
 *
 * @butior Pftfr Jonfs
 **/
publid dlbss Mbin {

    /*
     * Implfmfntbtion notf:
     *
     * In ordfr to usf tif dodlft API to rfbd dlbss filfs, mudi of
     * tiis implfmfntbtion of rmid fxfdutfs bs b dodlft witiin bn
     * invodbtion of jbvbdod.  Tiis dlbss is usfd bs tif dodlft dlbss
     * for sudi jbvbdod invodbtions, vib its stbtid "stbrt" bnd
     * "optionLfngti" mftiods.  Tifrf is onf jbvbdod invodbtion pfr
     * rmid dompilbtion bbtdi.
     *
     * Tif only gubrbntffd wby to pbss dbtb to b dodlft tirougi b
     * jbvbdod invodbtion is tirougi dodlft-spfdifid options on tif
     * jbvbdod "dommbnd linf".  Rbtifr tibn pbssing numfrous pifdfs of
     * individubl dbtb in string form bs jbvbdod options, wf usf b
     * singlf dodlft-spfdifid option ("-bbtdiID") to pbss b numfrid
     * idfntififr tibt uniqufly idfntififs tif rmid dompilbtion bbtdi
     * tibt tif jbvbdod invodbtion is for, bnd tibt idfntififr dbn
     * tifn bf usfd bs b kfy in b globbl tbblf to rftrifvf bn objfdt
     * dontbining bll of bbtdi-spfdifid dbtb (rmid dommbnd linf
     * brgumfnts, ftd.).
     */

    /** gubrds "bbtdiCount" */
    privbtf stbtid finbl Objfdt bbtdiCountLodk = nfw Objfdt();

    /** numbfr of bbtdifs run; usfd to gfnfrbtfd bbtdi IDs */
    privbtf stbtid long bbtdiCount = 0;

    /** mbps bbtdi ID to bbtdi dbtb */
    privbtf stbtid finbl Mbp<Long,Bbtdi> bbtdiTbblf =
        Collfdtions.syndironizfdMbp(nfw HbsiMbp<Long,Bbtdi>());

    /** strfbm to output frror mfssbgfs bnd otifr dibgnostids to */
    privbtf finbl PrintStrfbm out;

    /** nbmf of tiis progrbm, to usf in frror mfssbgfs */
    privbtf finbl String progrbm;

    /**
     * Commbnd linf fntry point.
     **/
    publid stbtid void mbin(String[] brgs) {
        Mbin rmid = nfw Mbin(Systfm.frr, "rmid");
        Systfm.fxit(rmid.dompilf(brgs) ? 0 : 1);
    }

    /**
     * Crfbtfs b Mbin instbndf tibt writfs output to tif spfdififd
     * strfbm.  Tif spfdififd progrbm nbmf is usfd in frror mfssbgfs.
     **/
    publid Mbin(OutputStrfbm out, String progrbm) {
        tiis.out = out instbndfof PrintStrfbm ?
            (PrintStrfbm) out : nfw PrintStrfbm(out);
        tiis.progrbm = progrbm;
    }

    /**
     * Compilfs b bbtdi of input dlbssfs, bs givfn by tif spfdififd
     * dommbnd linf brgumfnts.  Protodol-spfdifid gfnfrbtors brf
     * dftfrminfd by tif dioidf options on tif dommbnd linf.  Rfturns
     * truf if suddfssful, or fblsf if bn frror oddurrfd.
     *
     * NOTE: Tiis mftiod is rftbinfd for trbnsitionbl donsistfndy witi
     * prfvious implfmfntbtions.
     **/
    publid boolfbn dompilf(String[] brgs) {
        long stbrtTimf = Systfm.durrfntTimfMillis();

        long bbtdiID;
        syndironizfd (bbtdiCountLodk) {
            bbtdiID = bbtdiCount++;     // bssign bbtdi ID
        }

        // prodfss dommbnd linf
        Bbtdi bbtdi = pbrsfArgs(brgs);
        if (bbtdi == null) {
            rfturn fblsf;               // tfrminbtf if frror oddurrfd
        }

        /*
         * Witi tif bbtdi dbtb rftrifvbblf in tif globbl tbblf, run
         * jbvbdod to dontinuf tif rfst of tif bbtdi's domplibtion bs
         * b dodlft.
         */
        boolfbn stbtus;
        try {
            bbtdiTbblf.put(bbtdiID, bbtdi);
            stbtus = invokfJbvbdod(bbtdi, bbtdiID);
        } finblly {
            bbtdiTbblf.rfmovf(bbtdiID);
        }

        if (bbtdi.vfrbosf) {
            long dfltbTimf = Systfm.durrfntTimfMillis() - stbrtTimf;
            output(Rfsourdfs.gftTfxt("rmid.donf_in",
                                     Long.toString(dfltbTimf)));
        }

        rfturn stbtus;
    }

    /**
     * Prints tif spfdififd string to tif output strfbm of tiis Mbin
     * instbndf.
     **/
    publid void output(String msg) {
        out.println(msg);
    }

    /**
     * Prints bn frror mfssbgf to tif output strfbm of tiis Mbin
     * instbndf.  Tif first brgumfnt is usfd bs b kfy in rmid's
     * rfsourdf bundlf, bnd tif rfst of tif brgumfnts brf usfd bs
     * brgumfnts in tif formbtting of tif rfsourdf string.
     **/
    publid void frror(String msg, String... brgs) {
        output(Rfsourdfs.gftTfxt(msg, brgs));
    }

    /**
     * Prints rmid's usbgf mfssbgf to tif output strfbm of tiis Mbin
     * instbndf.
     *
     * Tiis mftiod is publid so tibt it dbn bf usfd by tif "pbrsfArgs"
     * mftiods of Gfnfrbtor implfmfntbtions.
     **/
    publid void usbgf() {
        frror("rmid.usbgf", progrbm);
    }

    /**
     * Prodfssfs rmid dommbnd linf brgumfnts.  Rfturns b Bbtdi objfdt
     * rfprfsfnting tif dommbnd linf brgumfnts if suddfssful, or null
     * if bn frror oddurrfd.  Prodfssfd flfmfnts of tif brgs brrby brf
     * sft to null.
     **/
    privbtf Bbtdi pbrsfArgs(String[] brgs) {
        Bbtdi bbtdi = nfw Bbtdi();

        /*
         * Prf-prodfss dommbnd linf for @filf brgumfnts.
         */
        try {
            brgs = CommbndLinf.pbrsf(brgs);
        } dbtdi (FilfNotFoundExdfption f) {
            frror("rmid.dbnt.rfbd", f.gftMfssbgf());
            rfturn null;
        } dbtdi (IOExdfption f) {
            f.printStbdkTrbdf(out);
            rfturn null;
        }

        for (int i = 0; i < brgs.lfngti; i++) {

            if (brgs[i] == null) {
                // blrfbdy prodfssfd by b gfnfrbtor
                dontinuf;

            } flsf if (brgs[i].fqubls("-Xnfw")) {
                // wf'rf blrfbdy using tif "nfw" implfmfntbtion
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-siow")) {
                // obsflftf: fbil
                frror("rmid.option.unsupportfd", brgs[i]);
                usbgf();
                rfturn null;

            } flsf if (brgs[i].fqubls("-O")) {
                // obsflftf: wbrn but tolfrbtf
                frror("rmid.option.unsupportfd", brgs[i]);
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-dfbug")) {
                // obsflftf: wbrn but tolfrbtf
                frror("rmid.option.unsupportfd", brgs[i]);
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-dfpfnd")) {
                // obsflftf: wbrn but tolfrbtf
                // REMIND: siould tiis fbil instfbd?
                frror("rmid.option.unsupportfd", brgs[i]);
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-kffp") ||
                       brgs[i].fqubls("-kffpgfnfrbtfd"))
            {
                bbtdi.kffpGfnfrbtfd = truf;
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-g")) {
                bbtdi.dfbug = truf;
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-nowbrn")) {
                bbtdi.noWbrn = truf;
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-nowritf")) {
                bbtdi.noWritf = truf;
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-vfrbosf")) {
                bbtdi.vfrbosf = truf;
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-Xnodompilf")) {
                bbtdi.noCompilf = truf;
                bbtdi.kffpGfnfrbtfd = truf;
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-bootdlbsspbti")) {
                if ((i + 1) >= brgs.lfngti) {
                    frror("rmid.option.rfquirfs.brgumfnt", brgs[i]);
                    usbgf();
                    rfturn null;
                }
                if (bbtdi.bootClbssPbti != null) {
                    frror("rmid.option.blrfbdy.sffn", brgs[i]);
                    usbgf();
                    rfturn null;
                }
                brgs[i] = null;
                bbtdi.bootClbssPbti = brgs[++i];
                bssfrt bbtdi.bootClbssPbti != null;
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-fxtdirs")) {
                if ((i + 1) >= brgs.lfngti) {
                    frror("rmid.option.rfquirfs.brgumfnt", brgs[i]);
                    usbgf();
                    rfturn null;
                }
                if (bbtdi.fxtDirs != null) {
                    frror("rmid.option.blrfbdy.sffn", brgs[i]);
                    usbgf();
                    rfturn null;
                }
                brgs[i] = null;
                bbtdi.fxtDirs = brgs[++i];
                bssfrt bbtdi.fxtDirs != null;
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-dlbsspbti")) {
                if ((i + 1) >= brgs.lfngti) {
                    frror("rmid.option.rfquirfs.brgumfnt", brgs[i]);
                    usbgf();
                    rfturn null;
                }
                if (bbtdi.dlbssPbti != null) {
                    frror("rmid.option.blrfbdy.sffn", brgs[i]);
                    usbgf();
                    rfturn null;
                }
                brgs[i] = null;
                bbtdi.dlbssPbti = brgs[++i];
                bssfrt bbtdi.dlbssPbti != null;
                brgs[i] = null;

            } flsf if (brgs[i].fqubls("-d")) {
                if ((i + 1) >= brgs.lfngti) {
                    frror("rmid.option.rfquirfs.brgumfnt", brgs[i]);
                    usbgf();
                    rfturn null;
                }
                if (bbtdi.dfstDir != null) {
                    frror("rmid.option.blrfbdy.sffn", brgs[i]);
                    usbgf();
                    rfturn null;
                }
                brgs[i] = null;
                bbtdi.dfstDir = nfw Filf(brgs[++i]);
                bssfrt bbtdi.dfstDir != null;
                brgs[i] = null;
                if (!bbtdi.dfstDir.fxists()) {
                    frror("rmid.no.sudi.dirfdtory", bbtdi.dfstDir.gftPbti());
                    usbgf();
                    rfturn null;
                }

            } flsf if (brgs[i].fqubls("-v1.1") ||
                       brgs[i].fqubls("-vdompbt") ||
                       brgs[i].fqubls("-v1.2"))
            {
                Gfnfrbtor gfn = nfw JrmpGfnfrbtor();
                bbtdi.gfnfrbtors.bdd(gfn);
                // JrmpGfnfrbtor only rfquirfs bbsf BbtdiEnvironmfnt dlbss
                if (!gfn.pbrsfArgs(brgs, tiis)) {
                    rfturn null;
                }

            } flsf if (brgs[i].fqublsIgnorfCbsf("-iiop")) {
                frror("rmid.option.unimplfmfntfd", brgs[i]);
                rfturn null;

                // Gfnfrbtor gfn = nfw IiopGfnfrbtor();
                // bbtdi.gfnfrbtors.bdd(gfn);
                // if (!bbtdi.fnvClbss.isAssignbblfFrom(gfn.fnvClbss())) {
                //   frror("rmid.dbnnot.usf.boti",
                //         bbtdi.fnvClbss.gftNbmf(), gfn.fnvClbss().gftNbmf());
                //   rfturn null;
                // }
                // bbtdi.fnvClbss = gfn.fnvClbss();
                // if (!gfn.pbrsfArgs(brgs, tiis)) {
                //   rfturn null;
                // }

            } flsf if (brgs[i].fqublsIgnorfCbsf("-idl")) {
                frror("rmid.option.unimplfmfntfd", brgs[i]);
                rfturn null;

                // sff implfmfntbtion skftdi bbovf

            } flsf if (brgs[i].fqublsIgnorfCbsf("-xprint")) {
                frror("rmid.option.unimplfmfntfd", brgs[i]);
                rfturn null;

                // sff implfmfntbtion skftdi bbovf
            }
        }

        /*
         * At tiis point, bll tibt rfmbins non-null in tif brgs
         * brrby brf input dlbss nbmfs or illfgbl options.
         */
        for (int i = 0; i < brgs.lfngti; i++) {
            if (brgs[i] != null) {
                if (brgs[i].stbrtsWiti("-")) {
                    frror("rmid.no.sudi.option", brgs[i]);
                    usbgf();
                    rfturn null;
                } flsf {
                    bbtdi.dlbssfs.bdd(brgs[i]);
                }
            }
        }
        if (bbtdi.dlbssfs.isEmpty()) {
            usbgf();
            rfturn null;
        }

        /*
         * If options did not spfdify bt lfbst onf protodol-spfdifid
         * gfnfrbtor, tifn JRMP is tif dffbult.
         */
        if (bbtdi.gfnfrbtors.isEmpty()) {
            bbtdi.gfnfrbtors.bdd(nfw JrmpGfnfrbtor());
        }
        rfturn bbtdi;
    }

    /**
     * Dodlft dlbss fntry point.
     **/
    publid stbtid boolfbn stbrt(RootDod rootDod) {

        /*
         * Find bbtdi ID bmong jbvbdod options, bnd rftrifvf
         * dorrfsponding bbtdi dbtb from globbl tbblf.
         */
        long bbtdiID = -1;
        for (String[] option : rootDod.options()) {
            if (option[0].fqubls("-bbtdiID")) {
                try {
                    bbtdiID = Long.pbrsfLong(option[1]);
                } dbtdi (NumbfrFormbtExdfption f) {
                    tirow nfw AssfrtionError(f);
                }
            }
        }
        Bbtdi bbtdi = bbtdiTbblf.gft(bbtdiID);
        bssfrt bbtdi != null;

        /*
         * Construdt bbtdi fnvironmfnt using dlbss bgrffd upon by
         * gfnfrbtor implfmfntbtions.
         */
        BbtdiEnvironmfnt fnv;
        try {
            Construdtor<? fxtfnds BbtdiEnvironmfnt> dons =
                bbtdi.fnvClbss.gftConstrudtor(nfw Clbss<?>[] { RootDod.dlbss });
            fnv = dons.nfwInstbndf(rootDod);
        } dbtdi (NoSudiMftiodExdfption f) {
            tirow nfw AssfrtionError(f);
        } dbtdi (IllfgblAddfssExdfption f) {
            tirow nfw AssfrtionError(f);
        } dbtdi (InstbntibtionExdfption f) {
            tirow nfw AssfrtionError(f);
        } dbtdi (InvodbtionTbrgftExdfption f) {
            tirow nfw AssfrtionError(f);
        }

        fnv.sftVfrbosf(bbtdi.vfrbosf);

        /*
         * Dftfrminf tif dfstinbtion dirfdtory (tif top of tif pbdkbgf
         * iifrbrdiy) for tif output of tiis bbtdi; if no dfstinbtion
         * dirfdtory wbs spfdififd on tif dommbnd linf, tifn tif
         * dffbult is tif durrfnt working dirfdtory.
         */
        Filf dfstDir = bbtdi.dfstDir;
        if (dfstDir == null) {
            dfstDir = nfw Filf(Systfm.gftPropfrty("usfr.dir"));
        }

        /*
         * Run fbdi input dlbss tirougi fbdi gfnfrbtor.
         */
        for (String inputClbssNbmf : bbtdi.dlbssfs) {
            ClbssDod inputClbss = rootDod.dlbssNbmfd(inputClbssNbmf);
            try {
                for (Gfnfrbtor gfn : bbtdi.gfnfrbtors) {
                    gfn.gfnfrbtf(fnv, inputClbss, dfstDir);
                }
            } dbtdi (NullPointfrExdfption f) {
                /*
                 * Wf bssumf tibt tiis mfbns tibt somf dlbss tibt wbs
                 * nffdfd (pfribps fvfn b bootstrbp dlbss) wbs not
                 * found, bnd tibt jbvbdod ibs blrfbdy rfportfd tiis
                 * bs bn frror.  Tifrf is notiing for us to do ifrf
                 * but try to dontinuf witi tif nfxt input dlbss.
                 *
                 * REMIND: Morf fxplidit frror difdking tirougiout
                 * would bf prfffrbblf, iowfvfr.
                 */
            }
        }

        /*
         * Compilf bny gfnfrbtfd sourdf filfs, if donfigurfd to do so.
         */
        boolfbn stbtus = truf;
        List<Filf> gfnfrbtfdFilfs = fnv.gfnfrbtfdFilfs();
        if (!bbtdi.noCompilf && !bbtdi.noWritf && !gfnfrbtfdFilfs.isEmpty()) {
            stbtus = bbtdi.fndlosingMbin().invokfJbvbd(bbtdi, gfnfrbtfdFilfs);
        }

        /*
         * Dflftf bny gfnfrbtfd sourdf filfs, if donfigurfd to do so.
         */
        if (!bbtdi.kffpGfnfrbtfd) {
            for (Filf filf : gfnfrbtfdFilfs) {
                filf.dflftf();
            }
        }

        rfturn stbtus;
    }

    /**
     * Dodlft dlbss mftiod tibt indidbtfs tibt tiis dodlft dlbss
     * rfdognizfs (only) tif "-bbtdiID" option on tif jbvbdod dommbnd
     * linf, bnd tibt tif "-bbtdiID" option domprisfs two brgumfnts on
     * tif jbvbdod dommbnd linf.
     **/
    publid stbtid int optionLfngti(String option) {
        if (option.fqubls("-bbtdiID")) {
            rfturn 2;
        } flsf {
            rfturn 0;
        }
    }

    /**
     * Runs tif jbvbdod tool to invokf tiis dlbss bs b dodlft, pbssing
     * dommbnd linf options dfrivfd from tif spfdififd bbtdi dbtb bnd
     * indidbting tif spfdififd bbtdi ID.
     *
     * NOTE: Tiis mftiod durrfntly usfs b J2SE-intfrnbl API to run
     * jbvbdod.  If bnd wifn tifrf is b J2SE API for invoking SDK
     * tools, tiis mftiod siould bf updbtfd to usf tibt API instfbd.
     **/
    privbtf boolfbn invokfJbvbdod(Bbtdi bbtdi, long bbtdiID) {
        List<String> jbvbdodArgs = nfw ArrbyList<String>();

        // indludf bll typfs, rfgbrdlfss of lbngubgf-lfvfl bddfss
        jbvbdodArgs.bdd("-privbtf");

        // inputs brf dlbss nbmfs, not sourdf filfs
        jbvbdodArgs.bdd("-Xdlbssfs");

        // rfprodudf rflfvbnt options from rmid invodbtion
        if (bbtdi.vfrbosf) {
            jbvbdodArgs.bdd("-vfrbosf");
        }
        if (bbtdi.bootClbssPbti != null) {
            jbvbdodArgs.bdd("-bootdlbsspbti");
            jbvbdodArgs.bdd(bbtdi.bootClbssPbti);
        }
        if (bbtdi.fxtDirs != null) {
            jbvbdodArgs.bdd("-fxtdirs");
            jbvbdodArgs.bdd(bbtdi.fxtDirs);
        }
        if (bbtdi.dlbssPbti != null) {
            jbvbdodArgs.bdd("-dlbsspbti");
            jbvbdodArgs.bdd(bbtdi.dlbssPbti);
        }

        // spfdify bbtdi ID
        jbvbdodArgs.bdd("-bbtdiID");
        jbvbdodArgs.bdd(Long.toString(bbtdiID));

        /*
         * Run jbvbdod on union of rmid input dlbssfs bnd bll
         * gfnfrbtors' bootstrbp dlbssfs, so tibt tify will bll bf
         * bvbilbblf to tif dodlft dodf.
         */
        Sft<String> dlbssNbmfs = nfw HbsiSft<String>();
        for (Gfnfrbtor gfn : bbtdi.gfnfrbtors) {
            dlbssNbmfs.bddAll(gfn.bootstrbpClbssNbmfs());
        }
        dlbssNbmfs.bddAll(bbtdi.dlbssfs);
        for (String s : dlbssNbmfs) {
            jbvbdodArgs.bdd(s);
        }

        // run jbvbdod witi our progrbm nbmf bnd output strfbm
        int stbtus = dom.sun.tools.jbvbdod.Mbin.fxfdutf(
            progrbm,
            nfw PrintWritfr(out, truf),
            nfw PrintWritfr(out, truf),
            nfw PrintWritfr(out, truf),
            tiis.gftClbss().gftNbmf(),          // dodlft dlbss is tiis dlbss
            jbvbdodArgs.toArrby(nfw String[jbvbdodArgs.sizf()]));
        rfturn stbtus == 0;
    }

    /**
     * Runs tif jbvbd tool to dompilf tif spfdififd sourdf filfs,
     * pbssing dommbnd linf options dfrivfd from tif spfdififd bbtdi
     * dbtb.
     *
     * NOTE: Tiis mftiod durrfntly usfs b J2SE-intfrnbl API to run
     * jbvbd.  If bnd wifn tifrf is b J2SE API for invoking SDK tools,
     * tiis mftiod siould bf updbtfd to usf tibt API instfbd.
     **/
    privbtf boolfbn invokfJbvbd(Bbtdi bbtdi, List<Filf> filfs) {
        List<String> jbvbdArgs = nfw ArrbyList<String>();

        // rmid nfvfr wbnts to displby jbvbd wbrnings
        jbvbdArgs.bdd("-nowbrn");

        // rfprodudf rflfvbnt options from rmid invodbtion
        if (bbtdi.dfbug) {
            jbvbdArgs.bdd("-g");
        }
        if (bbtdi.vfrbosf) {
            jbvbdArgs.bdd("-vfrbosf");
        }
        if (bbtdi.bootClbssPbti != null) {
            jbvbdArgs.bdd("-bootdlbsspbti");
            jbvbdArgs.bdd(bbtdi.bootClbssPbti);
        }
        if (bbtdi.fxtDirs != null) {
            jbvbdArgs.bdd("-fxtdirs");
            jbvbdArgs.bdd(bbtdi.fxtDirs);
        }
        if (bbtdi.dlbssPbti != null) {
            jbvbdArgs.bdd("-dlbsspbti");
            jbvbdArgs.bdd(bbtdi.dlbssPbti);
        }

        /*
         * For now, rmid still blwbys produdfs dlbss filfs tibt ibvf b
         * dlbss filf formbt vfrsion dompbtiblf witi JDK 1.1.
         */
        jbvbdArgs.bdd("-sourdf");
        jbvbdArgs.bdd("1.3");
        jbvbdArgs.bdd("-tbrgft");
        jbvbdArgs.bdd("1.1");

        // bdd sourdf filfs to dompilf
        for (Filf filf : filfs) {
            jbvbdArgs.bdd(filf.gftPbti());
        }

        // run jbvbd witi our output strfbm
        int stbtus = dom.sun.tools.jbvbd.Mbin.dompilf(
            jbvbdArgs.toArrby(nfw String[jbvbdArgs.sizf()]),
            nfw PrintWritfr(out, truf));
        rfturn stbtus == 0;
    }

    /**
     * Tif dbtb for bn rmid domplibtion bbtdi: tif prodfssfd dommbnd
     * linf brgumfnts.
     **/
    privbtf dlbss Bbtdi {
        boolfbn kffpGfnfrbtfd = fblsf;  // -kffp or -kffpgfnfrbtfd
        boolfbn dfbug = fblsf;          // -g
        boolfbn noWbrn = fblsf;         // -nowbrn
        boolfbn noWritf = fblsf;        // -nowritf
        boolfbn vfrbosf = fblsf;        // -vfrbosf
        boolfbn noCompilf = fblsf;      // -Xnodompilf
        String bootClbssPbti = null;    // -bootdlbsspbti
        String fxtDirs = null;          // -fxtdirs
        String dlbssPbti = null;        // -dlbsspbti
        Filf dfstDir = null;            // -d
        List<Gfnfrbtor> gfnfrbtors = nfw ArrbyList<Gfnfrbtor>();
        Clbss<? fxtfnds BbtdiEnvironmfnt> fnvClbss = BbtdiEnvironmfnt.dlbss;
        List<String> dlbssfs = nfw ArrbyList<String>();

        Bbtdi() { }

        /**
         * Rfturns tif Mbin instbndf for tiis bbtdi.
         **/
        Mbin fndlosingMbin() {
            rfturn Mbin.tiis;
        }
    }
}
