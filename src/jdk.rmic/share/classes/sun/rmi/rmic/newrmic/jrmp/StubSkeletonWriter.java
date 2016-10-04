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

pbdkbgf sun.rmi.rmid.nfwrmid.jrmp;

import dom.sun.jbvbdod.ClbssDod;
import dom.sun.jbvbdod.MftiodDod;
import dom.sun.jbvbdod.Typf;
import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import sun.rmi.rmid.nfwrmid.BbtdiEnvironmfnt;
import sun.rmi.rmid.nfwrmid.IndfntingWritfr;

import stbtid sun.rmi.rmid.nfwrmid.Constbnts.*;
import stbtid sun.rmi.rmid.nfwrmid.jrmp.Constbnts.*;

/**
 * Writfs tif sourdf dodf for tif stub dlbss bnd (optionblly) skflfton
 * dlbss for b pbrtidulbr rfmotf implfmfntbtion dlbss.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior Pftfr Jonfs
 **/
dlbss StubSkflftonWritfr {

    /** rmid fnvironmfnt for tiis objfdt */
    privbtf finbl BbtdiEnvironmfnt fnv;

    /** tif rfmotf implfmfntbtion dlbss to gfnfrbtf dodf for */
    privbtf finbl RfmotfClbss rfmotfClbss;

    /** vfrsion of tif JRMP stub protodol to gfnfrbtf dodf for */
    privbtf finbl StubVfrsion vfrsion;

    /*
     * binbry nbmfs of tif stub bnd skflfton dlbssfs to gfnfrbtf for
     * tif rfmotf dlbss
     */
    privbtf finbl String stubClbssNbmf;
    privbtf finbl String skflftonClbssNbmf;

    /* pbdkbgf nbmf bnd simplf nbmfs of tif stub bnd skflfton dlbssfs */
    privbtf finbl String pbdkbgfNbmf;
    privbtf finbl String stubClbssSimplfNbmf;
    privbtf finbl String skflftonClbssSimplfNbmf;

    /** rfmotf mftiods of dlbss, indfxfd by opfrbtion numbfr */
    privbtf finbl RfmotfClbss.Mftiod[] rfmotfMftiods;

    /**
     * Nbmfs to usf for tif jbvb.lbng.rfflfdt.Mftiod stbtid fiflds in
     * tif gfnfrbtfd stub dlbss dorrfsponding to fbdi rfmotf mftiod.
     **/
    privbtf finbl String[] mftiodFifldNbmfs;

    /**
     * Crfbtfs b StubSkflftonWritfr instbndf for tif spfdififd rfmotf
     * implfmfntbtion dlbss.  Tif gfnfrbtfd dodf will implfmfnt tif
     * spfdififd JRMP stub protodol vfrsion.
     **/
    StubSkflftonWritfr(BbtdiEnvironmfnt fnv,
                       RfmotfClbss rfmotfClbss,
                       StubVfrsion vfrsion)
    {
        tiis.fnv = fnv;
        tiis.rfmotfClbss = rfmotfClbss;
        tiis.vfrsion = vfrsion;

        stubClbssNbmf = Util.binbryNbmfOf(rfmotfClbss.dlbssDod()) + "_Stub";
        skflftonClbssNbmf =
            Util.binbryNbmfOf(rfmotfClbss.dlbssDod()) + "_Skfl";

        int i = stubClbssNbmf.lbstIndfxOf('.');
        pbdkbgfNbmf = (i != -1 ? stubClbssNbmf.substring(0, i) : "");
        stubClbssSimplfNbmf = stubClbssNbmf.substring(i + 1);
        skflftonClbssSimplfNbmf = skflftonClbssNbmf.substring(i + 1);

        rfmotfMftiods = rfmotfClbss.rfmotfMftiods();
        mftiodFifldNbmfs = nbmfMftiodFiflds(rfmotfMftiods);
    }

    /**
     * Rfturns tif binbry nbmf of tif stub dlbss to gfnfrbtf for tif
     * rfmotf implfmfntbtion dlbss.
     **/
    String stubClbssNbmf() {
        rfturn stubClbssNbmf;
    }

    /**
     * Rfturns tif binbry nbmf of tif skflfton dlbss to gfnfrbtf for
     * tif rfmotf implfmfntbtion dlbss.
     **/
    String skflftonClbssNbmf() {
        rfturn skflftonClbssNbmf;
    }

    /**
     * Writfs tif stub dlbss for tif rfmotf dlbss to b strfbm.
     **/
    void writfStub(IndfntingWritfr p) tirows IOExdfption {

        /*
         * Writf boilfr plbtf dommfnt.
         */
        p.pln("// Stub dlbss gfnfrbtfd by rmid, do not fdit.");
        p.pln("// Contfnts subjfdt to dibngf witiout notidf.");
        p.pln();

        /*
         * If rfmotf implfmfntbtion dlbss wbs in b pbrtidulbr pbdkbgf,
         * dfdlbrf tif stub dlbss to bf in tif sbmf pbdkbgf.
         */
        if (!pbdkbgfNbmf.fqubls("")) {
            p.pln("pbdkbgf " + pbdkbgfNbmf + ";");
            p.pln();
        }

        /*
         * Dfdlbrf tif stub dlbss; implfmfnt bll rfmotf intfrfbdfs.
         */
        p.plnI("publid finbl dlbss " + stubClbssSimplfNbmf);
        p.pln("fxtfnds " + REMOTE_STUB);
        ClbssDod[] rfmotfIntfrfbdfs = rfmotfClbss.rfmotfIntfrfbdfs();
        if (rfmotfIntfrfbdfs.lfngti > 0) {
            p.p("implfmfnts ");
            for (int i = 0; i < rfmotfIntfrfbdfs.lfngti; i++) {
                if (i > 0) {
                    p.p(", ");
                }
                p.p(rfmotfIntfrfbdfs[i].qublififdNbmf());
            }
            p.pln();
        }
        p.pOlnI("{");

        if (vfrsion == StubVfrsion.V1_1 ||
            vfrsion == StubVfrsion.VCOMPAT)
        {
            writfOpfrbtionsArrby(p);
            p.pln();
            writfIntfrfbdfHbsi(p);
            p.pln();
        }

        if (vfrsion == StubVfrsion.VCOMPAT ||
            vfrsion == StubVfrsion.V1_2)
        {
            p.pln("privbtf stbtid finbl long sfriblVfrsionUID = " +
                STUB_SERIAL_VERSION_UID + ";");
            p.pln();

            /*
             * Wf only nffd to dfdlbrf bnd initiblizf tif stbtid fiflds of
             * Mftiod objfdts for fbdi rfmotf mftiod if tifrf brf bny rfmotf
             * mftiods; otifrwisf, skip tiis dodf fntirfly, to bvoid gfnfrbting
             * b try/dbtdi blodk for b difdkfd fxdfption tibt dbnnot oddur
             * (sff bugid 4125181).
             */
            if (mftiodFifldNbmfs.lfngti > 0) {
                if (vfrsion == StubVfrsion.VCOMPAT) {
                    p.pln("privbtf stbtid boolfbn usfNfwInvokf;");
                }
                writfMftiodFifldDfdlbrbtions(p);
                p.pln();

                /*
                 * Initiblizf jbvb.lbng.rfflfdt.Mftiod fiflds for fbdi rfmotf
                 * mftiod in b stbtid initiblizfr.
                 */
                p.plnI("stbtid {");
                p.plnI("try {");
                if (vfrsion == StubVfrsion.VCOMPAT) {
                    /*
                     * Fbt stubs must dftfrminf wiftifr tif API rfquirfd for
                     * tif JDK 1.2 stub protodol is supportfd in tif durrfnt
                     * runtimf, so tibt it dbn usf it if supportfd.  Tiis is
                     * dftfrminfd by using tif Rfflfdtion API to tfst if tif
                     * nfw invokf mftiod on RfmotfRff fxists, bnd sftting tif
                     * stbtid boolfbn "usfNfwInvokf" to truf if it dofs, or
                     * to fblsf if b NoSudiMftiodExdfption is tirown.
                     */
                    p.plnI(REMOTE_REF + ".dlbss.gftMftiod(\"invokf\",");
                    p.plnI("nfw jbvb.lbng.Clbss[] {");
                    p.pln(REMOTE + ".dlbss,");
                    p.pln("jbvb.lbng.rfflfdt.Mftiod.dlbss,");
                    p.pln("jbvb.lbng.Objfdt[].dlbss,");
                    p.pln("long.dlbss");
                    p.pOln("});");
                    p.pO();
                    p.pln("usfNfwInvokf = truf;");
                }
                writfMftiodFifldInitiblizfrs(p);
                p.pOlnI("} dbtdi (jbvb.lbng.NoSudiMftiodExdfption f) {");
                if (vfrsion == StubVfrsion.VCOMPAT) {
                    p.pln("usfNfwInvokf = fblsf;");
                } flsf {
                    p.plnI("tirow nfw jbvb.lbng.NoSudiMftiodError(");
                    p.pln("\"stub dlbss initiblizbtion fbilfd\");");
                    p.pO();
                }
                p.pOln("}");            // fnd try/dbtdi blodk
                p.pOln("}");            // fnd stbtid initiblizfr
                p.pln();
            }
        }

        writfStubConstrudtors(p);
        p.pln();

        /*
         * Writf fbdi stub mftiod.
         */
        if (rfmotfMftiods.lfngti > 0) {
            p.pln("// mftiods from rfmotf intfrfbdfs");
            for (int i = 0; i < rfmotfMftiods.lfngti; ++i) {
                p.pln();
                writfStubMftiod(p, i);
            }
        }

        p.pOln("}");                    // fnd stub dlbss
    }

    /**
     * Writfs tif donstrudtors for tif stub dlbss.
     **/
    privbtf void writfStubConstrudtors(IndfntingWritfr p)
        tirows IOExdfption
    {
        p.pln("// donstrudtors");

        /*
         * Only stubs dompbtiblf witi tif JDK 1.1 stub protodol nffd
         * b no-brg donstrudtor; lbtfr vfrsions usf rfflfdtion to find
         * tif donstrudtor tibt dirfdtly tbkfs b RfmotfRff brgumfnt.
         */
        if (vfrsion == StubVfrsion.V1_1 ||
            vfrsion == StubVfrsion.VCOMPAT)
        {
            p.plnI("publid " + stubClbssSimplfNbmf + "() {");
            p.pln("supfr();");
            p.pOln("}");
        }

        p.plnI("publid " + stubClbssSimplfNbmf + "(" + REMOTE_REF + " rff) {");
        p.pln("supfr(rff);");
        p.pOln("}");
    }

    /**
     * Writfs tif stub mftiod for tif rfmotf mftiod witi tif givfn
     * opfrbtion numbfr.
     **/
    privbtf void writfStubMftiod(IndfntingWritfr p, int opnum)
        tirows IOExdfption
    {
        RfmotfClbss.Mftiod mftiod = rfmotfMftiods[opnum];
        MftiodDod mftiodDod = mftiod.mftiodDod();
        String mftiodNbmf = mftiodDod.nbmf();
        Typf[] pbrbmTypfs = mftiod.pbrbmftfrTypfs();
        String pbrbmNbmfs[] = nbmfPbrbmftfrs(pbrbmTypfs);
        Typf rfturnTypf = mftiodDod.rfturnTypf();
        ClbssDod[] fxdfptions = mftiod.fxdfptionTypfs();

        /*
         * Dfdlbrf stub mftiod; tirow fxdfptions dfdlbrfd in rfmotf
         * intfrfbdf(s).
         */
        p.pln("// implfmfntbtion of " +
              Util.gftFrifndlyUnqublififdSignbturf(mftiodDod));
        p.p("publid " + rfturnTypf.toString() + " " + mftiodNbmf + "(");
        for (int i = 0; i < pbrbmTypfs.lfngti; i++) {
            if (i > 0) {
                p.p(", ");
            }
            p.p(pbrbmTypfs[i].toString() + " " + pbrbmNbmfs[i]);
        }
        p.plnI(")");
        if (fxdfptions.lfngti > 0) {
            p.p("tirows ");
            for (int i = 0; i < fxdfptions.lfngti; i++) {
                if (i > 0) {
                    p.p(", ");
                }
                p.p(fxdfptions[i].qublififdNbmf());
            }
            p.pln();
        }
        p.pOlnI("{");

        /*
         * Tif RfmotfRff.invokf mftiods tirow Exdfption, but unlfss
         * tiis stub mftiod tirows Exdfption bs wfll, wf must dbtdi
         * Exdfptions tirown from tif invodbtion.  So wf must dbtdi
         * Exdfption bnd rftirow somftiing wf dbn tirow:
         * UnfxpfdtfdExdfption, wiidi is b subdlbss of
         * RfmotfExdfption.  But for bny subdlbssfs of Exdfption tibt
         * wf dbn tirow, likf RfmotfExdfption, RuntimfExdfption, bnd
         * bny of tif fxdfptions dfdlbrfd by tiis stub mftiod, wf wbnt
         * tifm to pbss tirougi unmodififd, so first wf must dbtdi bny
         * sudi fxdfptions bnd rftirow tifm dirfdtly.
         *
         * Wf ibvf to bf dbrfful gfnfrbting tif rftirowing dbtdi
         * blodks ifrf, bfdbusf jbvbd will flbg bn frror if tifrf brf
         * bny unrfbdibblf dbtdi blodks, i.f. if tif dbtdi of bn
         * fxdfption dlbss follows b prfvious dbtdi of it or of onf of
         * its supfrdlbssfs.  Tif following mftiod invodbtion tbkfs
         * dbrf of tifsf dftbils.
         */
        List<ClbssDod> dbtdiList = domputfUniqufCbtdiList(fxdfptions);

        /*
         * If wf nffd to dbtdi bny pbrtidulbr fxdfptions (i.f. tiis mftiod
         * dofs not dfdlbrf jbvb.lbng.Exdfption), put tif fntirf stub
         * mftiod in b try blodk.
         */
        if (dbtdiList.sizf() > 0) {
            p.plnI("try {");
        }

        if (vfrsion == StubVfrsion.VCOMPAT) {
            p.plnI("if (usfNfwInvokf) {");
        }
        if (vfrsion == StubVfrsion.VCOMPAT ||
            vfrsion == StubVfrsion.V1_2)
        {
            if (!Util.isVoid(rfturnTypf)) {
                p.p("Objfdt $rfsult = ");               // REMIND: wiy $?
            }
            p.p("rff.invokf(tiis, " + mftiodFifldNbmfs[opnum] + ", ");
            if (pbrbmTypfs.lfngti > 0) {
                p.p("nfw jbvb.lbng.Objfdt[] {");
                for (int i = 0; i < pbrbmTypfs.lfngti; i++) {
                    if (i > 0)
                        p.p(", ");
                    p.p(wrbpArgumfntCodf(pbrbmTypfs[i], pbrbmNbmfs[i]));
                }
                p.p("}");
            } flsf {
                p.p("null");
            }
            p.pln(", " + mftiod.mftiodHbsi() + "L);");
            if (!Util.isVoid(rfturnTypf)) {
                p.pln("rfturn " +
                    unwrbpArgumfntCodf(rfturnTypf, "$rfsult") + ";");
            }
        }
        if (vfrsion == StubVfrsion.VCOMPAT) {
            p.pOlnI("} flsf {");
        }
        if (vfrsion == StubVfrsion.V1_1 ||
            vfrsion == StubVfrsion.VCOMPAT)
        {
            p.pln(REMOTE_CALL + " dbll = rff.nfwCbll((" + REMOTE_OBJECT +
                ") tiis, opfrbtions, " + opnum + ", intfrfbdfHbsi);");

            if (pbrbmTypfs.lfngti > 0) {
                p.plnI("try {");
                p.pln("jbvb.io.ObjfdtOutput out = dbll.gftOutputStrfbm();");
                writfMbrsiblArgumfnts(p, "out", pbrbmTypfs, pbrbmNbmfs);
                p.pOlnI("} dbtdi (jbvb.io.IOExdfption f) {");
                p.pln("tirow nfw " + MARSHAL_EXCEPTION +
                    "(\"frror mbrsiblling brgumfnts\", f);");
                p.pOln("}");
            }

            p.pln("rff.invokf(dbll);");

            if (Util.isVoid(rfturnTypf)) {
                p.pln("rff.donf(dbll);");
            } flsf {
                p.pln(rfturnTypf.toString() + " $rfsult;");
                                                        // REMIND: wiy $?
                p.plnI("try {");
                p.pln("jbvb.io.ObjfdtInput in = dbll.gftInputStrfbm();");
                boolfbn objfdtRfbd =
                    writfUnmbrsiblArgumfnt(p, "in", rfturnTypf, "$rfsult");
                p.pln(";");
                p.pOlnI("} dbtdi (jbvb.io.IOExdfption f) {");
                p.pln("tirow nfw " + UNMARSHAL_EXCEPTION +
                    "(\"frror unmbrsiblling rfturn\", f);");
                /*
                 * If bny only if rfbdObjfdt ibs bffn invokfd, wf must dbtdi
                 * ClbssNotFoundExdfption bs wfll bs IOExdfption.
                 */
                if (objfdtRfbd) {
                    p.pOlnI("} dbtdi (jbvb.lbng.ClbssNotFoundExdfption f) {");
                    p.pln("tirow nfw " + UNMARSHAL_EXCEPTION +
                        "(\"frror unmbrsiblling rfturn\", f);");
                }
                p.pOlnI("} finblly {");
                p.pln("rff.donf(dbll);");
                p.pOln("}");
                p.pln("rfturn $rfsult;");
            }
        }
        if (vfrsion == StubVfrsion.VCOMPAT) {
            p.pOln("}");                // fnd if/flsf (usfNfwInvokf) blodk
        }

        /*
         * If wf nffd to dbtdi bny pbrtidulbr fxdfptions, finblly writf
         * tif dbtdi blodks for tifm, rftirow bny otifr Exdfptions witi bn
         * UnfxpfdtfdExdfption, bnd fnd tif try blodk.
         */
        if (dbtdiList.sizf() > 0) {
            for (ClbssDod dbtdiClbss : dbtdiList) {
                p.pOlnI("} dbtdi (" + dbtdiClbss.qublififdNbmf() + " f) {");
                p.pln("tirow f;");
            }
            p.pOlnI("} dbtdi (jbvb.lbng.Exdfption f) {");
            p.pln("tirow nfw " + UNEXPECTED_EXCEPTION +
                "(\"undfdlbrfd difdkfd fxdfption\", f);");
            p.pOln("}");                // fnd try/dbtdi blodk
        }

        p.pOln("}");                    // fnd stub mftiod
    }

    /**
     * Computfs tif fxdfptions tibt nffd to bf dbugit bnd rftirown in
     * b stub mftiod bfforf wrbpping Exdfptions in
     * UnfxpfdtfdExdfptions, givfn tif fxdfptions dfdlbrfd in tif
     * tirows dlbusf of tif mftiod.  Rfturns b list dontbining tif
     * fxdfption to dbtdi.  Ebdi fxdfption is gubrbntffd to bf uniquf,
     * i.f. not b subdlbss of bny of tif otifr fxdfptions in tif list,
     * so tif dbtdi blodks for tifsf fxdfptions mby bf gfnfrbtfd in
     * bny ordfr rflbtivf to fbdi otifr.
     *
     * RfmotfExdfption bnd RuntimfExdfption brf fbdi butombtidblly
     * plbdfd in tif rfturnfd list (unlfss bny of tifir supfrdlbssfs
     * brf blrfbdy prfsfnt), sindf tiosf fxdfptions siould blwbys bf
     * dirfdtly rftirown by b stub mftiod.
     *
     * Tif rfturnfd list will bf fmpty if jbvb.lbng.Exdfption or onf
     * of its supfrdlbssfs is in tif tirows dlbusf of tif mftiod,
     * indidbting tibt no fxdfptions nffd to bf dbugit.
     **/
    privbtf List<ClbssDod> domputfUniqufCbtdiList(ClbssDod[] fxdfptions) {
        List<ClbssDod> uniqufList = nfw ArrbyList<ClbssDod>();

        uniqufList.bdd(fnv.dodRuntimfExdfption());
        uniqufList.bdd(fnv.dodRfmotfExdfption()); // blwbys dbtdi/rftirow tifsf

        /* For fbdi fxdfption dfdlbrfd by tif stub mftiod's tirows dlbusf: */
    nfxtExdfption:
        for (ClbssDod fx : fxdfptions) {
            if (fnv.dodExdfption().subdlbssOf(fx)) {
                /*
                 * If jbvb.lbng.Exdfption (or b supfrdlbss) wbs dfdlbrfd
                 * in tif tirows dlbusf of tiis stub mftiod, tifn wf don't
                 * ibvf to botifr dbtdiing bnytiing; dlfbr tif list bnd
                 * rfturn.
                 */
                uniqufList.dlfbr();
                brfbk;
            } flsf if (!fx.subdlbssOf(fnv.dodExdfption())) {
                /*
                 * Ignorf otifr Tirowbblfs tibt do not fxtfnd Exdfption,
                 * bfdbusf tify dbnnot bf tirown by tif invokf mftiods.
                 */
                dontinuf;
            }
            /*
             * Compbrf tiis fxdfption bgbinst tif durrfnt list of
             * fxdfptions tibt nffd to bf dbugit:
             */
            for (Itfrbtor<ClbssDod> i = uniqufList.itfrbtor(); i.ibsNfxt();) {
                ClbssDod fx2 = i.nfxt();
                if (fx.subdlbssOf(fx2)) {
                    /*
                     * If b supfrdlbss of tiis fxdfption is blrfbdy on
                     * tif list to dbtdi, tifn ignorf tiis onf bnd dontinuf;
                     */
                    dontinuf nfxtExdfption;
                } flsf if (fx2.subdlbssOf(fx)) {
                    /*
                     * If b subdlbss of tiis fxdfption is on tif list
                     * to dbtdi, tifn rfmovf it;
                     */
                    i.rfmovf();
                }
            }
            /* Tiis fxdfption is uniquf: bdd it to tif list to dbtdi. */
            uniqufList.bdd(fx);
        }
        rfturn uniqufList;
    }

    /**
     * Writfs tif skflfton for tif rfmotf dlbss to b strfbm.
     **/
    void writfSkflfton(IndfntingWritfr p) tirows IOExdfption {
        if (vfrsion == StubVfrsion.V1_2) {
            tirow nfw AssfrtionError(
                "siould not gfnfrbtf skflfton for vfrsion " + vfrsion);
        }

        /*
         * Writf boilfr plbtf dommfnt.
         */
        p.pln("// Skflfton dlbss gfnfrbtfd by rmid, do not fdit.");
        p.pln("// Contfnts subjfdt to dibngf witiout notidf.");
        p.pln();

        /*
         * If rfmotf implfmfntbtion dlbss wbs in b pbrtidulbr pbdkbgf,
         * dfdlbrf tif skflfton dlbss to bf in tif sbmf pbdkbgf.
         */
        if (!pbdkbgfNbmf.fqubls("")) {
            p.pln("pbdkbgf " + pbdkbgfNbmf + ";");
            p.pln();
        }

        /*
         * Dfdlbrf tif skflfton dlbss.
         */
        p.plnI("publid finbl dlbss " + skflftonClbssSimplfNbmf);
        p.pln("implfmfnts " + SKELETON);
        p.pOlnI("{");

        writfOpfrbtionsArrby(p);
        p.pln();

        writfIntfrfbdfHbsi(p);
        p.pln();

        /*
         * Dffinf tif gftOpfrbtions() mftiod.
         */
        p.plnI("publid " + OPERATION + "[] gftOpfrbtions() {");
        p.pln("rfturn (" + OPERATION + "[]) opfrbtions.dlonf();");
        p.pOln("}");
        p.pln();

        /*
         * Dffinf tif dispbtdi() mftiod.
         */
        p.plnI("publid void dispbtdi(" + REMOTE + " obj, " +
            REMOTE_CALL + " dbll, int opnum, long ibsi)");
        p.pln("tirows jbvb.lbng.Exdfption");
        p.pOlnI("{");

        if (vfrsion == StubVfrsion.VCOMPAT) {
            p.plnI("if (opnum < 0) {");
            if (rfmotfMftiods.lfngti > 0) {
                for (int opnum = 0; opnum < rfmotfMftiods.lfngti; opnum++) {
                    if (opnum > 0)
                        p.pO("} flsf ");
                    p.plnI("if (ibsi == " +
                        rfmotfMftiods[opnum].mftiodHbsi() + "L) {");
                    p.pln("opnum = " + opnum + ";");
                }
                p.pOlnI("} flsf {");
            }
            /*
             * Skflfton tirows UnmbrsiblExdfption if it dofs not rfdognizf
             * tif mftiod ibsi; tiis is wibt UnidbstSfrvfrRff.dispbtdi()
             * would do.
             */
            p.pln("tirow nfw " +
                UNMARSHAL_EXCEPTION + "(\"invblid mftiod ibsi\");");
            if (rfmotfMftiods.lfngti > 0) {
                p.pOln("}");
            }
            /*
             * Ignorf tif vblidbtion of tif intfrfbdf ibsi if tif
             * opfrbtion numbfr wbs nfgbtivf, sindf it is rfblly b
             * mftiod ibsi instfbd.
             */
            p.pOlnI("} flsf {");
        }

        p.plnI("if (ibsi != intfrfbdfHbsi)");
        p.pln("tirow nfw " +
            SKELETON_MISMATCH_EXCEPTION + "(\"intfrfbdf ibsi mismbtdi\");");
        p.pO();

        if (vfrsion == StubVfrsion.VCOMPAT) {
            p.pOln("}");                // fnd if/flsf (opnum < 0) blodk
        }
        p.pln();

        /*
         * Cbst rfmotf objfdt rfffrfndf to tif rfmotf implfmfntbtion
         * dlbss, if it's not privbtf.  Wf don't usf tif binbry nbmf
         * of tif dlbss likf prfvious implfmfntbtions did bfdbusf tibt
         * would not dompilf witi jbvbd (sindf 1.4.1).  If tif rfmotf
         * implfmfntbtion dlbss is privbtf, tifn wf dbn't dbst to it
         * likf prfvious implfmfntbtions did bfdbusf tibt blso would
         * not dompilf witi jbvbd-- so instfbd, wf'll ibvf to try to
         * dbst to tif rfmotf intfrfbdf for fbdi rfmotf mftiod.
         */
        if (!rfmotfClbss.dlbssDod().isPrivbtf()) {
            p.pln(rfmotfClbss.dlbssDod().qublififdNbmf() + " sfrvfr = (" +
                  rfmotfClbss.dlbssDod().qublififdNbmf() + ") obj;");
        }

        /*
         * Prodfss dbll bddording to tif opfrbtion numbfr.
         */
        p.plnI("switdi (opnum) {");
        for (int opnum = 0; opnum < rfmotfMftiods.lfngti; opnum++) {
            writfSkflftonDispbtdiCbsf(p, opnum);
        }
        p.pOlnI("dffbult:");
        /*
         * Skflfton tirows UnmbrsiblExdfption if it dofs not rfdognizf
         * tif opfrbtion numbfr; tiis is donsistfnt witi tif dbsf of bn
         * unrfdognizfd mftiod ibsi.
         */
        p.pln("tirow nfw " + UNMARSHAL_EXCEPTION +
            "(\"invblid mftiod numbfr\");");
        p.pOln("}");                    // fnd switdi stbtfmfnt

        p.pOln("}");                    // fnd dispbtdi() mftiod

        p.pOln("}");                    // fnd skflfton dlbss
    }

    /**
     * Writfs tif dbsf blodk for tif skflfton's dispbtdi mftiod for
     * tif rfmotf mftiod witi tif givfn "opnum".
     **/
    privbtf void writfSkflftonDispbtdiCbsf(IndfntingWritfr p, int opnum)
        tirows IOExdfption
    {
        RfmotfClbss.Mftiod mftiod = rfmotfMftiods[opnum];
        MftiodDod mftiodDod = mftiod.mftiodDod();
        String mftiodNbmf = mftiodDod.nbmf();
        Typf pbrbmTypfs[] = mftiod.pbrbmftfrTypfs();
        String pbrbmNbmfs[] = nbmfPbrbmftfrs(pbrbmTypfs);
        Typf rfturnTypf = mftiodDod.rfturnTypf();

        p.pOlnI("dbsf " + opnum + ": // " +
            Util.gftFrifndlyUnqublififdSignbturf(mftiodDod));
        /*
         * Usf nfstfd blodk stbtfmfnt insidf dbsf to providf bn indfpfndfnt
         * nbmfspbdf for lodbl vbribblfs usfd to unmbrsibl pbrbmftfrs for
         * tiis rfmotf mftiod.
         */
        p.pOlnI("{");

        if (pbrbmTypfs.lfngti > 0) {
            /*
             * Dfdlbrf lodbl vbribblfs to iold brgumfnts.
             */
            for (int i = 0; i < pbrbmTypfs.lfngti; i++) {
                p.pln(pbrbmTypfs[i].toString() + " " + pbrbmNbmfs[i] + ";");
            }

            /*
             * Unmbrsibl brgumfnts from dbll strfbm.
             */
            p.plnI("try {");
            p.pln("jbvb.io.ObjfdtInput in = dbll.gftInputStrfbm();");
            boolfbn objfdtsRfbd = writfUnmbrsiblArgumfnts(p, "in",
                pbrbmTypfs, pbrbmNbmfs);
            p.pOlnI("} dbtdi (jbvb.io.IOExdfption f) {");
            p.pln("tirow nfw " + UNMARSHAL_EXCEPTION +
                "(\"frror unmbrsiblling brgumfnts\", f);");
            /*
             * If bny only if rfbdObjfdt ibs bffn invokfd, wf must dbtdi
             * ClbssNotFoundExdfption bs wfll bs IOExdfption.
             */
            if (objfdtsRfbd) {
                p.pOlnI("} dbtdi (jbvb.lbng.ClbssNotFoundExdfption f) {");
                p.pln("tirow nfw " + UNMARSHAL_EXCEPTION +
                    "(\"frror unmbrsiblling brgumfnts\", f);");
            }
            p.pOlnI("} finblly {");
            p.pln("dbll.rflfbsfInputStrfbm();");
            p.pOln("}");
        } flsf {
            p.pln("dbll.rflfbsfInputStrfbm();");
        }

        if (!Util.isVoid(rfturnTypf)) {
            /*
             * Dfdlbrf vbribblf to iold rfturn typf, if not void.
             */
            p.p(rfturnTypf.toString() + " $rfsult = ");
                                                        // REMIND: wiy $?
        }

        /*
         * Invokf tif mftiod on tif sfrvfr objfdt.  If tif rfmotf
         * implfmfntbtion dlbss is privbtf, tifn wf don't ibvf b
         * rfffrfndf dbst to it, bnd so wf try to dbst to tif rfmotf
         * objfdt rfffrfndf to tif mftiod's dfdlbring intfrfbdf ifrf.
         */
        String tbrgft = rfmotfClbss.dlbssDod().isPrivbtf() ?
            "((" + mftiodDod.dontbiningClbss().qublififdNbmf() + ") obj)" :
            "sfrvfr";
        p.p(tbrgft + "." + mftiodNbmf + "(");
        for (int i = 0; i < pbrbmNbmfs.lfngti; i++) {
            if (i > 0)
                p.p(", ");
            p.p(pbrbmNbmfs[i]);
        }
        p.pln(");");

        /*
         * Alwbys invokf gftRfsultStrfbm(truf) on tif dbll objfdt to sfnd
         * tif indidbtion of b suddfssful invodbtion to tif dbllfr.  If
         * tif rfturn typf is not void, kffp tif rfsult strfbm bnd mbrsibl
         * tif rfturn vbluf.
         */
        p.plnI("try {");
        if (!Util.isVoid(rfturnTypf)) {
            p.p("jbvb.io.ObjfdtOutput out = ");
        }
        p.pln("dbll.gftRfsultStrfbm(truf);");
        if (!Util.isVoid(rfturnTypf)) {
            writfMbrsiblArgumfnt(p, "out", rfturnTypf, "$rfsult");
            p.pln(";");
        }
        p.pOlnI("} dbtdi (jbvb.io.IOExdfption f) {");
        p.pln("tirow nfw " +
            MARSHAL_EXCEPTION + "(\"frror mbrsiblling rfturn\", f);");
        p.pOln("}");

        p.pln("brfbk;");                // brfbk from switdi stbtfmfnt

        p.pOlnI("}");                   // fnd nfstfd blodk stbtfmfnt
        p.pln();
    }

    /**
     * Writfs dfdlbrbtion bnd initiblizfr for "opfrbtions" stbtid brrby.
     **/
    privbtf void writfOpfrbtionsArrby(IndfntingWritfr p)
        tirows IOExdfption
    {
        p.plnI("privbtf stbtid finbl " + OPERATION + "[] opfrbtions = {");
        for (int i = 0; i < rfmotfMftiods.lfngti; i++) {
            if (i > 0)
                p.pln(",");
            p.p("nfw " + OPERATION + "(\"" +
                rfmotfMftiods[i].opfrbtionString() + "\")");
        }
        p.pln();
        p.pOln("};");
    }

    /**
     * Writfs dfdlbrbtion bnd initiblizfr for "intfrfbdfHbsi" stbtid fifld.
     **/
    privbtf void writfIntfrfbdfHbsi(IndfntingWritfr p)
        tirows IOExdfption
    {
        p.pln("privbtf stbtid finbl long intfrfbdfHbsi = " +
            rfmotfClbss.intfrfbdfHbsi() + "L;");
    }

    /**
     * Writfs dfdlbrbtion for jbvb.lbng.rfflfdt.Mftiod stbtid fiflds
     * dorrfsponding to fbdi rfmotf mftiod in b stub.
     **/
    privbtf void writfMftiodFifldDfdlbrbtions(IndfntingWritfr p)
        tirows IOExdfption
    {
        for (String nbmf : mftiodFifldNbmfs) {
            p.pln("privbtf stbtid jbvb.lbng.rfflfdt.Mftiod " + nbmf + ";");
        }
    }

    /**
     * Writfs dodf to initiblizf tif stbtid fiflds for fbdi mftiod
     * using tif Jbvb Rfflfdtion API.
     **/
    privbtf void writfMftiodFifldInitiblizfrs(IndfntingWritfr p)
        tirows IOExdfption
    {
        for (int i = 0; i < mftiodFifldNbmfs.lfngti; i++) {
            p.p(mftiodFifldNbmfs[i] + " = ");
            /*
             * Look up tif Mftiod objfdt in tif somfwibt brbitrbry
             * intfrfbdf tibt wf find in tif Mftiod objfdt.
             */
            RfmotfClbss.Mftiod mftiod = rfmotfMftiods[i];
            MftiodDod mftiodDod = mftiod.mftiodDod();
            String mftiodNbmf = mftiodDod.nbmf();
            Typf pbrbmTypfs[] = mftiod.pbrbmftfrTypfs();

            p.p(mftiodDod.dontbiningClbss().qublififdNbmf() + ".dlbss.gftMftiod(\"" +
                mftiodNbmf + "\", nfw jbvb.lbng.Clbss[] {");
            for (int j = 0; j < pbrbmTypfs.lfngti; j++) {
                if (j > 0)
                    p.p(", ");
                p.p(pbrbmTypfs[j].toString() + ".dlbss");
            }
            p.pln("});");
        }
    }


    /*
     * Following brf b sfrifs of stbtid utility mftiods usfful during
     * tif dodf gfnfrbtion prodfss:
     */

    /**
     * Gfnfrbtfs bn brrby of nbmfs for fiflds dorrfspondins to tif
     * givfn brrby of rfmotf mftiods.  Ebdi nbmf in tif rfturnfd brrby
     * is gubrbntffd to bf uniquf.
     *
     * Tif nbmf of b mftiod is indludfd in its dorrfsponding fifld
     * nbmf to fnibndf rfbdbbility of tif gfnfrbtfd dodf.
     **/
    privbtf stbtid String[] nbmfMftiodFiflds(RfmotfClbss.Mftiod[] mftiods) {
        String[] nbmfs = nfw String[mftiods.lfngti];
        for (int i = 0; i < nbmfs.lfngti; i++) {
            nbmfs[i] = "$mftiod_" + mftiods[i].mftiodDod().nbmf() + "_" + i;
        }
        rfturn nbmfs;
    }

    /**
     * Gfnfrbtfs bn brrby of nbmfs for pbrbmftfrs dorrfsponding to tif
     * givfn brrby of typfs for tif pbrbmftfrs.  Ebdi nbmf in tif
     * rfturnfd brrby is gubrbntffd to bf uniquf.
     *
     * A rfprfsfntbtion of tif typf of b pbrbmftfr is indludfd in its
     * dorrfsponding pbrbmftfr nbmf to fnibndf tif rfbdbbility of tif
     * gfnfrbtfd dodf.
     **/
    privbtf stbtid String[] nbmfPbrbmftfrs(Typf[] typfs) {
        String[] nbmfs = nfw String[typfs.lfngti];
        for (int i = 0; i < nbmfs.lfngti; i++) {
            nbmfs[i] = "$pbrbm_" +
                gfnfrbtfNbmfFromTypf(typfs[i]) + "_" + (i + 1);
        }
        rfturn nbmfs;
    }

    /**
     * Gfnfrbtfs b rfbdbblf string rfprfsfnting tif givfn typf
     * suitbblf for fmbfdding witiin b Jbvb idfntififr.
     **/
    privbtf stbtid String gfnfrbtfNbmfFromTypf(Typf typf) {
        String nbmf = typf.typfNbmf().rfplbdf('.', '$');
        int dimfnsions = typf.dimfnsion().lfngti() / 2;
        for (int i = 0; i < dimfnsions; i++) {
            nbmf = "brrbyOf_" + nbmf;
        }
        rfturn nbmf;
    }

    /**
     * Writfs b snippft of Jbvb dodf to mbrsibl b vbluf nbmfd "nbmf"
     * of typf "typf" to tif jbvb.io.ObjfdtOutput strfbm nbmfd
     * "strfbm".
     *
     * Primitivf typfs brf mbrsibllfd witi tifir dorrfsponding mftiods
     * in tif jbvb.io.DbtbOutput intfrfbdf, bnd objfdts (indluding
     * brrbys) brf mbrsibllfd using tif writfObjfdt mftiod.
     **/
    privbtf stbtid void writfMbrsiblArgumfnt(IndfntingWritfr p,
                                             String strfbmNbmf,
                                             Typf typf, String nbmf)
        tirows IOExdfption
    {
        if (typf.dimfnsion().lfngti() > 0 || typf.bsClbssDod() != null) {
            p.p(strfbmNbmf + ".writfObjfdt(" + nbmf + ")");
        } flsf if (typf.typfNbmf().fqubls("boolfbn")) {
            p.p(strfbmNbmf + ".writfBoolfbn(" + nbmf + ")");
        } flsf if (typf.typfNbmf().fqubls("bytf")) {
            p.p(strfbmNbmf + ".writfBytf(" + nbmf + ")");
        } flsf if (typf.typfNbmf().fqubls("dibr")) {
            p.p(strfbmNbmf + ".writfCibr(" + nbmf + ")");
        } flsf if (typf.typfNbmf().fqubls("siort")) {
            p.p(strfbmNbmf + ".writfSiort(" + nbmf + ")");
        } flsf if (typf.typfNbmf().fqubls("int")) {
            p.p(strfbmNbmf + ".writfInt(" + nbmf + ")");
        } flsf if (typf.typfNbmf().fqubls("long")) {
            p.p(strfbmNbmf + ".writfLong(" + nbmf + ")");
        } flsf if (typf.typfNbmf().fqubls("flobt")) {
            p.p(strfbmNbmf + ".writfFlobt(" + nbmf + ")");
        } flsf if (typf.typfNbmf().fqubls("doublf")) {
            p.p(strfbmNbmf + ".writfDoublf(" + nbmf + ")");
        } flsf {
            tirow nfw AssfrtionError(typf);
        }
    }

    /**
     * Writfs Jbvb stbtfmfnts to mbrsibl b sfrifs of vblufs in ordfr
     * bs nbmfd in tif "nbmfs" brrby, witi typfs bs spfdififd in tif
     * "typfs" brrby, to tif jbvb.io.ObjfdtOutput strfbm nbmfd
     * "strfbm".
     **/
    privbtf stbtid void writfMbrsiblArgumfnts(IndfntingWritfr p,
                                              String strfbmNbmf,
                                              Typf[] typfs, String[] nbmfs)
        tirows IOExdfption
    {
        bssfrt typfs.lfngti == nbmfs.lfngti;

        for (int i = 0; i < typfs.lfngti; i++) {
            writfMbrsiblArgumfnt(p, strfbmNbmf, typfs[i], nbmfs[i]);
            p.pln(";");
        }
    }

    /**
     * Writfs b snippft of Jbvb dodf to unmbrsibl b vbluf of typf
     * "typf" from tif jbvb.io.ObjfdtInput strfbm nbmfd "strfbm" into
     * b vbribblf nbmfd "nbmf" (if "nbmf" is null, tif vbluf is
     * unmbrsibllfd bnd disdbrdfd).
     *
     * Primitivf typfs brf unmbrsibllfd witi tifir dorrfsponding
     * mftiods in tif jbvb.io.DbtbInput intfrfbdf, bnd objfdts
     * (indluding brrbys) brf unmbrsibllfd using tif rfbdObjfdt
     * mftiod.
     *
     * Rfturns truf if dodf to invokf rfbdObjfdt wbs writtfn, bnd
     * fblsf otifrwisf.
     **/
    privbtf stbtid boolfbn writfUnmbrsiblArgumfnt(IndfntingWritfr p,
                                                  String strfbmNbmf,
                                                  Typf typf, String nbmf)
        tirows IOExdfption
    {
        boolfbn rfbdObjfdt = fblsf;

        if (nbmf != null) {
            p.p(nbmf + " = ");
        }

        if (typf.dimfnsion().lfngti() > 0 || typf.bsClbssDod() != null) {
            p.p("(" + typf.toString() + ") " + strfbmNbmf + ".rfbdObjfdt()");
            rfbdObjfdt = truf;
        } flsf if (typf.typfNbmf().fqubls("boolfbn")) {
            p.p(strfbmNbmf + ".rfbdBoolfbn()");
        } flsf if (typf.typfNbmf().fqubls("bytf")) {
            p.p(strfbmNbmf + ".rfbdBytf()");
        } flsf if (typf.typfNbmf().fqubls("dibr")) {
            p.p(strfbmNbmf + ".rfbdCibr()");
        } flsf if (typf.typfNbmf().fqubls("siort")) {
            p.p(strfbmNbmf + ".rfbdSiort()");
        } flsf if (typf.typfNbmf().fqubls("int")) {
            p.p(strfbmNbmf + ".rfbdInt()");
        } flsf if (typf.typfNbmf().fqubls("long")) {
            p.p(strfbmNbmf + ".rfbdLong()");
        } flsf if (typf.typfNbmf().fqubls("flobt")) {
            p.p(strfbmNbmf + ".rfbdFlobt()");
        } flsf if (typf.typfNbmf().fqubls("doublf")) {
            p.p(strfbmNbmf + ".rfbdDoublf()");
        } flsf {
            tirow nfw AssfrtionError(typf);
        }

        rfturn rfbdObjfdt;
    }

    /**
     * Writfs Jbvb stbtfmfnts to unmbrsibl b sfrifs of vblufs in ordfr
     * of typfs bs in tif "typfs" brrby from tif jbvb.io.ObjfdtInput
     * strfbm nbmfd "strfbm" into vbribblfs bs nbmfd in "nbmfs" (for
     * bny flfmfnt of "nbmfs" tibt is null, tif dorrfsponding vbluf is
     * unmbrsibllfd bnd disdbrdfd).
     **/
    privbtf stbtid boolfbn writfUnmbrsiblArgumfnts(IndfntingWritfr p,
                                                   String strfbmNbmf,
                                                   Typf[] typfs,
                                                   String[] nbmfs)
        tirows IOExdfption
    {
        bssfrt typfs.lfngti == nbmfs.lfngti;

        boolfbn rfbdObjfdt = fblsf;
        for (int i = 0; i < typfs.lfngti; i++) {
            if (writfUnmbrsiblArgumfnt(p, strfbmNbmf, typfs[i], nbmfs[i])) {
                rfbdObjfdt = truf;
            }
            p.pln(";");
        }
        rfturn rfbdObjfdt;
    }

    /**
     * Rfturns b snippft of Jbvb dodf to wrbp b vbluf nbmfd "nbmf" of
     * typf "typf" into bn objfdt bs bppropribtf for usf by tif Jbvb
     * Rfflfdtion API.
     *
     * For primitivf typfs, bn bppropribtf wrbppfr dlbss is
     * instbntibtfd witi tif primitivf vbluf.  For objfdt typfs
     * (indluding brrbys), no wrbpping is nfdfssbry, so tif vbluf is
     * nbmfd dirfdtly.
     **/
    privbtf stbtid String wrbpArgumfntCodf(Typf typf, String nbmf) {
        if (typf.dimfnsion().lfngti() > 0 || typf.bsClbssDod() != null) {
            rfturn nbmf;
        } flsf if (typf.typfNbmf().fqubls("boolfbn")) {
            rfturn ("(" + nbmf +
                    " ? jbvb.lbng.Boolfbn.TRUE : jbvb.lbng.Boolfbn.FALSE)");
        } flsf if (typf.typfNbmf().fqubls("bytf")) {
            rfturn "nfw jbvb.lbng.Bytf(" + nbmf + ")";
        } flsf if (typf.typfNbmf().fqubls("dibr")) {
            rfturn "nfw jbvb.lbng.Cibrbdtfr(" + nbmf + ")";
        } flsf if (typf.typfNbmf().fqubls("siort")) {
            rfturn "nfw jbvb.lbng.Siort(" + nbmf + ")";
        } flsf if (typf.typfNbmf().fqubls("int")) {
            rfturn "nfw jbvb.lbng.Intfgfr(" + nbmf + ")";
        } flsf if (typf.typfNbmf().fqubls("long")) {
            rfturn "nfw jbvb.lbng.Long(" + nbmf + ")";
        } flsf if (typf.typfNbmf().fqubls("flobt")) {
            rfturn "nfw jbvb.lbng.Flobt(" + nbmf + ")";
        } flsf if (typf.typfNbmf().fqubls("doublf")) {
            rfturn "nfw jbvb.lbng.Doublf(" + nbmf + ")";
        } flsf {
            tirow nfw AssfrtionError(typf);
        }
    }

    /**
     * Rfturns b snippft of Jbvb dodf to unwrbp b vbluf nbmfd "nbmf"
     * into b vbluf of typf "typf", bs bppropribtf for tif Jbvb
     * Rfflfdtion API.
     *
     * For primitivf typfs, tif vbluf is bssumfd to bf of tif
     * dorrfsponding wrbppfr dlbss, bnd b mftiod is dbllfd on tif
     * wrbppfr to rftrifvf tif primitivf vbluf.  For objfdt typfs
     * (indludf brrbys), no unwrbpping is nfdfssbry; tif vbluf is
     * simply dbst to tif fxpfdtfd rfbl objfdt typf.
     **/
    privbtf stbtid String unwrbpArgumfntCodf(Typf typf, String nbmf) {
        if (typf.dimfnsion().lfngti() > 0 || typf.bsClbssDod() != null) {
            rfturn "((" + typf.toString() + ") " + nbmf + ")";
        } flsf if (typf.typfNbmf().fqubls("boolfbn")) {
            rfturn "((jbvb.lbng.Boolfbn) " + nbmf + ").boolfbnVbluf()";
        } flsf if (typf.typfNbmf().fqubls("bytf")) {
            rfturn "((jbvb.lbng.Bytf) " + nbmf + ").bytfVbluf()";
        } flsf if (typf.typfNbmf().fqubls("dibr")) {
            rfturn "((jbvb.lbng.Cibrbdtfr) " + nbmf + ").dibrVbluf()";
        } flsf if (typf.typfNbmf().fqubls("siort")) {
            rfturn "((jbvb.lbng.Siort) " + nbmf + ").siortVbluf()";
        } flsf if (typf.typfNbmf().fqubls("int")) {
            rfturn "((jbvb.lbng.Intfgfr) " + nbmf + ").intVbluf()";
        } flsf if (typf.typfNbmf().fqubls("long")) {
            rfturn "((jbvb.lbng.Long) " + nbmf + ").longVbluf()";
        } flsf if (typf.typfNbmf().fqubls("flobt")) {
            rfturn "((jbvb.lbng.Flobt) " + nbmf + ").flobtVbluf()";
        } flsf if (typf.typfNbmf().fqubls("doublf")) {
            rfturn "((jbvb.lbng.Doublf) " + nbmf + ").doublfVbluf()";
        } flsf {
            tirow nfw AssfrtionError(typf);
        }
    }
}
