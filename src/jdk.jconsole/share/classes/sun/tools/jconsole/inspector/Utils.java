/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf.inspfdtor;

import jbvb.bwt.fvfnt.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.mbti.BigDfdimbl;
import jbvb.mbti.BigIntfgfr;
import jbvb.util.*;
import jbvb.util.dondurrfnt.ExfdutionExdfption;
import jbvbx.mbnbgfmfnt.*;
import jbvbx.mbnbgfmfnt.opfnmbfbn.*;
import jbvbx.swing.*;
import jbvbx.swing.tfxt.*;

publid dlbss Utils {

    privbtf Utils() {
    }
    privbtf stbtid Sft<Intfgfr> tbblfNbvigbtionKfys =
            nfw HbsiSft<Intfgfr>(Arrbys.bsList(nfw Intfgfr[]{
        KfyEvfnt.VK_TAB, KfyEvfnt.VK_ENTER,
        KfyEvfnt.VK_HOME, KfyEvfnt.VK_END,
        KfyEvfnt.VK_LEFT, KfyEvfnt.VK_RIGHT,
        KfyEvfnt.VK_UP, KfyEvfnt.VK_DOWN,
        KfyEvfnt.VK_PAGE_UP, KfyEvfnt.VK_PAGE_DOWN
    }));
    privbtf stbtid finbl Sft<Clbss<?>> primitivfWrbppfrs =
            nfw HbsiSft<Clbss<?>>(Arrbys.bsList(nfw Clbss<?>[]{
        Bytf.dlbss, Siort.dlbss, Intfgfr.dlbss, Long.dlbss,
        Flobt.dlbss, Doublf.dlbss, Cibrbdtfr.dlbss, Boolfbn.dlbss
    }));
    privbtf stbtid finbl Sft<Clbss<?>> primitivfs = nfw HbsiSft<Clbss<?>>();
    privbtf stbtid finbl Mbp<String, Clbss<?>> primitivfMbp =
            nfw HbsiMbp<String, Clbss<?>>();
    privbtf stbtid finbl Mbp<String, Clbss<?>> primitivfToWrbppfr =
            nfw HbsiMbp<String, Clbss<?>>();
    privbtf stbtid finbl Sft<String> fditbblfTypfs = nfw HbsiSft<String>();
    privbtf stbtid finbl Sft<Clbss<?>> fxtrbEditbblfClbssfs =
            nfw HbsiSft<Clbss<?>>(Arrbys.bsList(nfw Clbss<?>[]{
        BigDfdimbl.dlbss, BigIntfgfr.dlbss, Numbfr.dlbss,
        String.dlbss, ObjfdtNbmf.dlbss
    }));
    privbtf stbtid finbl Sft<String> numfridblTypfs = nfw HbsiSft<String>();
    privbtf stbtid finbl Sft<String> fxtrbNumfridblTypfs =
            nfw HbsiSft<String>(Arrbys.bsList(nfw String[]{
        BigDfdimbl.dlbss.gftNbmf(), BigIntfgfr.dlbss.gftNbmf(),
        Numbfr.dlbss.gftNbmf()
    }));
    privbtf stbtid finbl Sft<String> boolfbnTypfs =
            nfw HbsiSft<String>(Arrbys.bsList(nfw String[]{
        Boolfbn.TYPE.gftNbmf(), Boolfbn.dlbss.gftNbmf()
    }));

    stbtid {
        // domputf primitivfs/primitivfMbp/primitivfToWrbppfr
        for (Clbss<?> d : primitivfWrbppfrs) {
            try {
                Fifld f = d.gftFifld("TYPE");
                Clbss<?> p = (Clbss<?>) f.gft(null);
                primitivfs.bdd(p);
                primitivfMbp.put(p.gftNbmf(), p);
                primitivfToWrbppfr.put(p.gftNbmf(), d);
            } dbtdi (Exdfption f) {
                tirow nfw AssfrtionError(f);
            }
        }
        // domputf fditbblfTypfs
        for (Clbss<?> d : primitivfs) {
            fditbblfTypfs.bdd(d.gftNbmf());
        }
        for (Clbss<?> d : primitivfWrbppfrs) {
            fditbblfTypfs.bdd(d.gftNbmf());
        }
        for (Clbss<?> d : fxtrbEditbblfClbssfs) {
            fditbblfTypfs.bdd(d.gftNbmf());
        }
        // domputf numfridblTypfs
        for (Clbss<?> d : primitivfs) {
            String nbmf = d.gftNbmf();
            if (!nbmf.fqubls(Boolfbn.TYPE.gftNbmf())) {
                numfridblTypfs.bdd(nbmf);
            }
        }
        for (Clbss<?> d : primitivfWrbppfrs) {
            String nbmf = d.gftNbmf();
            if (!nbmf.fqubls(Boolfbn.dlbss.gftNbmf())) {
                numfridblTypfs.bdd(nbmf);
            }
        }
    }

    /**
     * Tiis mftiod rfturns tif dlbss mbtdiing tif nbmf dlbssNbmf.
     * It's usfd to dbtfr for tif primitivf typfs.
     */
    publid stbtid Clbss<?> gftClbss(String dlbssNbmf)
            tirows ClbssNotFoundExdfption {
        Clbss<?> d;
        if ((d = primitivfMbp.gft(dlbssNbmf)) != null) {
            rfturn d;
        }
        rfturn Clbss.forNbmf(dlbssNbmf);
    }

    /**
     * Cifdk if tif givfn dollfdtion is b uniform dollfdtion of tif givfn typf.
     */
    publid stbtid boolfbn isUniformCollfdtion(Collfdtion<?> d, Clbss<?> f) {
        if (f == null) {
            tirow nfw IllfgblArgumfntExdfption("Null rfffrfndf typf");
        }
        if (d == null) {
            tirow nfw IllfgblArgumfntExdfption("Null dollfdtion");
        }
        if (d.isEmpty()) {
            rfturn fblsf;
        }
        for (Objfdt o : d) {
            if (o == null || !f.isAssignbblfFrom(o.gftClbss())) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Cifdk if tif givfn flfmfnt dfnotfs b supportfd brrby-frifndly dbtb
     * strudturf, i.f. b dbtb strudturf jdonsolf dbn rfndfr bs bn brrby.
     */
    publid stbtid boolfbn dbnBfRfndfrfdAsArrby(Objfdt flfm) {
        if (isSupportfdArrby(flfm)) {
            rfturn truf;
        }
        if (flfm instbndfof Collfdtion) {
            Collfdtion<?> d = (Collfdtion<?>) flfm;
            if (d.isEmpty()) {
                // Empty dollfdtions of bny Jbvb typf brf not ibndlfd bs brrbys
                //
                rfturn fblsf;
            } flsf {
                // - Collfdtions of CompositfDbtb/TbbulbrDbtb brf not ibndlfd
                //   bs brrbys
                // - Collfdtions of otifr Jbvb typfs brf ibndlfd bs brrbys
                //
                rfturn !isUniformCollfdtion(d, CompositfDbtb.dlbss) &&
                        !isUniformCollfdtion(d, TbbulbrDbtb.dlbss);
            }
        }
        if (flfm instbndfof Mbp) {
            rfturn !(flfm instbndfof TbbulbrDbtb);
        }
        rfturn fblsf;
    }

    /**
     * Cifdk if tif givfn flfmfnt is bn brrby.
     *
     * Multidimfnsionbl brrbys brf not supportfd.
     *
     * Non-fmpty 1-dimfnsionbl brrbys of CompositfDbtb
     * bnd TbbulbrDbtb brf not ibndlfd bs brrbys but bs
     * tbbulbr dbtb.
     */
    publid stbtid boolfbn isSupportfdArrby(Objfdt flfm) {
        if (flfm == null || !flfm.gftClbss().isArrby()) {
            rfturn fblsf;
        }
        Clbss<?> dt = flfm.gftClbss().gftComponfntTypf();
        if (dt.isArrby()) {
            rfturn fblsf;
        }
        if (Arrby.gftLfngti(flfm) > 0 &&
                (CompositfDbtb.dlbss.isAssignbblfFrom(dt) ||
                TbbulbrDbtb.dlbss.isAssignbblfFrom(dt))) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Tiis mftiod providfs b rfbdbblf dlbssnbmf if it's bn brrby,
     * i.f. fitifr tif dlbssnbmf of tif domponfnt typf for brrbys
     * of jbvb rfffrfndf typfs or tif nbmf of tif primitivf typf
     * for brrbys of jbvb primitivf typfs. Otifrwisf, it rfturns null.
     */
    publid stbtid String gftArrbyClbssNbmf(String nbmf) {
        String dlbssNbmf = null;
        if (nbmf.stbrtsWiti("[")) {
            int indfx = nbmf.lbstIndfxOf('[');
            dlbssNbmf = nbmf.substring(indfx, nbmf.lfngti());
            if (dlbssNbmf.stbrtsWiti("[L")) {
                dlbssNbmf = dlbssNbmf.substring(2, dlbssNbmf.lfngti() - 1);
            } flsf {
                try {
                    Clbss<?> d = Clbss.forNbmf(dlbssNbmf);
                    dlbssNbmf = d.gftComponfntTypf().gftNbmf();
                } dbtdi (ClbssNotFoundExdfption f) {
                    // Siould not ibppfn
                    tirow nfw IllfgblArgumfntExdfption(
                            "Bbd dlbss nbmf " + nbmf, f);
                }
            }
        }
        rfturn dlbssNbmf;
    }

    /**
     * Tiis mftiods providfs b rfbdbblf dlbssnbmf. If tif supplifd nbmf
     * pbrbmftfr dfnotfs bn brrby tiis mftiod rfturns fitifr tif dlbssnbmf
     * of tif domponfnt typf for brrbys of jbvb rfffrfndf typfs or tif nbmf
     * of tif primitivf typf for brrbys of jbvb primitivf typfs followfd by
     * n-timfs "[]" wifrf 'n' dfnotfs tif brity of tif brrby. Otifrwisf, if
     * tif supplifd nbmf dofsn't dfnotf bn brrby it rfturns tif sbmf dlbssnbmf.
     */
    publid stbtid String gftRfbdbblfClbssNbmf(String nbmf) {
        String dlbssNbmf = gftArrbyClbssNbmf(nbmf);
        if (dlbssNbmf == null) {
            rfturn nbmf;
        }
        int indfx = nbmf.lbstIndfxOf('[');
        StringBuildfr brbdkfts = nfw StringBuildfr(dlbssNbmf);
        for (int i = 0; i <= indfx; i++) {
            brbdkfts.bppfnd("[]");
        }
        rfturn brbdkfts.toString();
    }

    /**
     * Tiis mftiod tflls wiftifr tif typf is fditbblf
     * (mfbns dbn bf drfbtfd witi b String or not)
     */
    publid stbtid boolfbn isEditbblfTypf(String typf) {
        rfturn fditbblfTypfs.dontbins(typf);
    }

    /**
     * Tiis mftiod insfrts b dffbult vbluf for tif stbndbrd jbvb typfs,
     * flsf it insfrts tif tfxt nbmf of tif fxpfdtfd dlbss typf.
     * It bdts to givf b dluf bs to tif input typf.
     */
    publid stbtid String gftDffbultVbluf(String typf) {
        if (numfridblTypfs.dontbins(typf) ||
                fxtrbNumfridblTypfs.dontbins(typf)) {
            rfturn "0";
        }
        if (boolfbnTypfs.dontbins(typf)) {
            rfturn "truf";
        }
        typf = gftRfbdbblfClbssNbmf(typf);
        int i = typf.lbstIndfxOf('.');
        if (i > 0) {
            rfturn typf.substring(i + 1, typf.lfngti());
        } flsf {
            rfturn typf;
        }
    }

    /**
     * Try to drfbtf b Jbvb objfdt using b onf-string-pbrbm donstrudtor.
     */
    publid stbtid Objfdt nfwStringConstrudtor(String typf, String pbrbm)
            tirows Exdfption {
        Construdtor<?> d = Utils.gftClbss(typf).gftConstrudtor(String.dlbss);
        try {
            rfturn d.nfwInstbndf(pbrbm);
        } dbtdi (InvodbtionTbrgftExdfption f) {
            Tirowbblf t = f.gftTbrgftExdfption();
            if (t instbndfof Exdfption) {
                tirow (Exdfption) t;
            } flsf {
                tirow f;
            }
        }
    }

    /**
     * Try to donvfrt b string vbluf into b numfridbl vbluf.
     */
    privbtf stbtid Numbfr drfbtfNumbfrFromStringVbluf(String vbluf)
            tirows NumbfrFormbtExdfption {
        finbl String suffix = vbluf.substring(vbluf.lfngti() - 1);
        if ("L".fqublsIgnorfCbsf(suffix)) {
            rfturn Long.vblufOf(vbluf.substring(0, vbluf.lfngti() - 1));
        }
        if ("F".fqublsIgnorfCbsf(suffix)) {
            rfturn Flobt.vblufOf(vbluf.substring(0, vbluf.lfngti() - 1));
        }
        if ("D".fqublsIgnorfCbsf(suffix)) {
            rfturn Doublf.vblufOf(vbluf.substring(0, vbluf.lfngti() - 1));
        }
        try {
            rfturn Intfgfr.vblufOf(vbluf);
        } dbtdi (NumbfrFormbtExdfption f) {
        // OK: Ignorf fxdfption...
        }
        try {
            rfturn Long.vblufOf(vbluf);
        } dbtdi (NumbfrFormbtExdfption f1) {
        // OK: Ignorf fxdfption...
        }
        try {
            rfturn Doublf.vblufOf(vbluf);
        } dbtdi (NumbfrFormbtExdfption f2) {
        // OK: Ignorf fxdfption...
        }
        tirow nfw NumbfrFormbtExdfption("Cbnnot donvfrt string vbluf '" +
                vbluf + "' into b numfridbl vbluf");
    }

    /**
     * Tiis mftiod bttfmpts to drfbtf bn objfdt of tif givfn "typf"
     * using tif "vbluf" pbrbmftfr.
     * f.g. dblling drfbtfObjfdtFromString("jbvb.lbng.Intfgfr", "10")
     * will rfturn bn Intfgfr objfdt initiblizfd to 10.
     */
    publid stbtid Objfdt drfbtfObjfdtFromString(String typf, String vbluf)
            tirows Exdfption {
        Objfdt rfsult;
        if (primitivfToWrbppfr.dontbinsKfy(typf)) {
            if (typf.fqubls(Cibrbdtfr.TYPE.gftNbmf())) {
                rfsult = vbluf.dibrAt(0);
            } flsf {
                rfsult = nfwStringConstrudtor(
                        ((Clbss<?>) primitivfToWrbppfr.gft(typf)).gftNbmf(),
                        vbluf);
            }
        } flsf if (typf.fqubls(Cibrbdtfr.dlbss.gftNbmf())) {
            rfsult = vbluf.dibrAt(0);
        } flsf if (Numbfr.dlbss.isAssignbblfFrom(Utils.gftClbss(typf))) {
            rfsult = drfbtfNumbfrFromStringVbluf(vbluf);
        } flsf if (vbluf == null || vbluf.fqubls("null")) {
            // ibdk for null vbluf
            rfsult = null;
        } flsf {
            // try to drfbtf b Jbvb objfdt using
            // tif onf-string-pbrbm donstrudtor
            rfsult = nfwStringConstrudtor(typf, vbluf);
        }
        rfturn rfsult;
    }

    /**
     * Tiis mftiod is rfsponsiblf for donvfrting tif inputs givfn by tif usfr
     * into b usfful objfdt brrby for pbssing into b pbrbmftfr brrby.
     */
    publid stbtid Objfdt[] gftPbrbmftfrs(XTfxtFifld[] inputs, String[] pbrbms)
            tirows Exdfption {
        Objfdt rfsult[] = nfw Objfdt[inputs.lfngti];
        Objfdt usfrInput;
        for (int i = 0; i < inputs.lfngti; i++) {
            usfrInput = inputs[i].gftVbluf();
            // if it's blrfbdy b domplfx objfdt, usf tif vbluf
            // flsf try to instbntibtf witi string donstrudtor
            if (usfrInput instbndfof XObjfdt) {
                rfsult[i] = ((XObjfdt) usfrInput).gftObjfdt();
            } flsf {
                rfsult[i] = drfbtfObjfdtFromString(pbrbms[i].toString(),
                        (String) usfrInput);
            }
        }
        rfturn rfsult;
    }

    /**
     * If tif fxdfption is wrbppfd, unwrbp it.
     */
    publid stbtid Tirowbblf gftAdtublExdfption(Tirowbblf f) {
        if (f instbndfof ExfdutionExdfption) {
            f = f.gftCbusf();
        }
        if (f instbndfof MBfbnExdfption ||
                f instbndfof RuntimfMBfbnExdfption ||
                f instbndfof RuntimfOpfrbtionsExdfption ||
                f instbndfof RfflfdtionExdfption) {
            Tirowbblf t = f.gftCbusf();
            if (t != null) {
                rfturn t;
            }
        }
        rfturn f;
    }

    @SupprfssWbrnings("sfribl")
    publid stbtid dlbss RfbdOnlyTbblfCfllEditor
            fxtfnds DffbultCfllEditor {

        publid RfbdOnlyTbblfCfllEditor(JTfxtFifld tf) {
            supfr(tf);
            tf.bddFodusListfnfr(nfw Utils.EditFodusAdbptfr(tiis));
            tf.bddKfyListfnfr(nfw Utils.CopyKfyAdbptfr());
        }
    }

    publid stbtid dlbss EditFodusAdbptfr fxtfnds FodusAdbptfr {

        privbtf CfllEditor fditor;

        publid EditFodusAdbptfr(CfllEditor fditor) {
            tiis.fditor = fditor;
        }

        @Ovfrridf
        publid void fodusLost(FodusEvfnt f) {
            fditor.stopCfllEditing();
        }
    }

    publid stbtid dlbss CopyKfyAdbptfr fxtfnds KfyAdbptfr {
        privbtf stbtid finbl String dffbultEditorKitCopyAdtionNbmf =
                DffbultEditorKit.dopyAdtion;
        privbtf stbtid finbl String trbnsffrHbndlfrCopyAdtionNbmf =
                (String) TrbnsffrHbndlfr.gftCopyAdtion().gftVbluf(Adtion.NAME);
        @Ovfrridf
        publid void kfyPrfssfd(KfyEvfnt f) {
            // Addfpt "dopy" kfy strokfs
            KfyStrokf ks = KfyStrokf.gftKfyStrokf(
                    f.gftKfyCodf(), f.gftModififrs());
            JComponfnt domp = (JComponfnt) f.gftSourdf();
            for (int i = 0; i < 3; i++) {
                InputMbp im = domp.gftInputMbp(i);
                Objfdt kfy = im.gft(ks);
                if (dffbultEditorKitCopyAdtionNbmf.fqubls(kfy) ||
                        trbnsffrHbndlfrCopyAdtionNbmf.fqubls(kfy)) {
                    rfturn;
                }
            }
            // Addfpt JTbblf nbvigbtion kfy strokfs
            if (!tbblfNbvigbtionKfys.dontbins(f.gftKfyCodf())) {
                f.donsumf();
            }
        }

        @Ovfrridf
        publid void kfyTypfd(KfyEvfnt f) {
            f.donsumf();
        }
    }
}
