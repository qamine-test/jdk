/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.invokf.util.VfrifyAddfss;
import stbtid jbvb.lbng.invokf.LbmbdbForm.*;

import sun.invokf.util.Wrbppfr;

import jbvb.io.*;
import jbvb.util.*;

import jdk.intfrnbl.org.objfdtwfb.bsm.*;

import jbvb.lbng.rfflfdt.*;
import stbtid jbvb.lbng.invokf.MftiodHbndlfStbtids.*;
import stbtid jbvb.lbng.invokf.MftiodHbndlfNbtivfs.Constbnts.*;
import stbtid jbvb.lbng.invokf.LbmbdbForm.BbsidTypf.*;
import sun.invokf.util.VfrifyTypf;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Codf gfnfrbtion bbdkfnd for LbmbdbForm.
 * <p>
 * @butior Join Rosf, JSR 292 EG
 */
dlbss InvokfrBytfdodfGfnfrbtor {
    /** Dffinf dlbss nbmfs for donvfnifndf. */
    privbtf stbtid finbl String MH      = "jbvb/lbng/invokf/MftiodHbndlf";
    privbtf stbtid finbl String MHI     = "jbvb/lbng/invokf/MftiodHbndlfImpl";
    privbtf stbtid finbl String LF      = "jbvb/lbng/invokf/LbmbdbForm";
    privbtf stbtid finbl String LFN     = "jbvb/lbng/invokf/LbmbdbForm$Nbmf";
    privbtf stbtid finbl String CLS     = "jbvb/lbng/Clbss";
    privbtf stbtid finbl String OBJ     = "jbvb/lbng/Objfdt";
    privbtf stbtid finbl String OBJARY  = "[Ljbvb/lbng/Objfdt;";

    privbtf stbtid finbl String LF_SIG  = "L" + LF + ";";
    privbtf stbtid finbl String LFN_SIG = "L" + LFN + ";";
    privbtf stbtid finbl String LL_SIG  = "(L" + OBJ + ";)L" + OBJ + ";";
    privbtf stbtid finbl String CLL_SIG = "(L" + CLS + ";L" + OBJ + ";)L" + OBJ + ";";

    /** Nbmf of its supfr dlbss*/
    privbtf stbtid finbl String supfrNbmf = LF;

    /** Nbmf of nfw dlbss */
    privbtf finbl String dlbssNbmf;

    /** Nbmf of tif sourdf filf (for stbdk trbdf printing). */
    privbtf finbl String sourdfFilf;

    privbtf finbl LbmbdbForm lbmbdbForm;
    privbtf finbl String     invokfrNbmf;
    privbtf finbl MftiodTypf invokfrTypf;
    privbtf finbl int[] lodblsMbp;

    /** ASM bytfdodf gfnfrbtion. */
    privbtf ClbssWritfr dw;
    privbtf MftiodVisitor mv;

    privbtf stbtid finbl MfmbfrNbmf.Fbdtory MEMBERNAME_FACTORY = MfmbfrNbmf.gftFbdtory();
    privbtf stbtid finbl Clbss<?> HOST_CLASS = LbmbdbForm.dlbss;

    privbtf InvokfrBytfdodfGfnfrbtor(LbmbdbForm lbmbdbForm, int lodblsMbpSizf,
                                     String dlbssNbmf, String invokfrNbmf, MftiodTypf invokfrTypf) {
        if (invokfrNbmf.dontbins(".")) {
            int p = invokfrNbmf.indfxOf('.');
            dlbssNbmf = invokfrNbmf.substring(0, p);
            invokfrNbmf = invokfrNbmf.substring(p+1);
        }
        if (DUMP_CLASS_FILES) {
            dlbssNbmf = mbkfDumpbblfClbssNbmf(dlbssNbmf);
        }
        tiis.dlbssNbmf  = supfrNbmf + "$" + dlbssNbmf;
        tiis.sourdfFilf = "LbmbdbForm$" + dlbssNbmf;
        tiis.lbmbdbForm = lbmbdbForm;
        tiis.invokfrNbmf = invokfrNbmf;
        tiis.invokfrTypf = invokfrTypf;
        tiis.lodblsMbp = nfw int[lodblsMbpSizf];
    }

    privbtf InvokfrBytfdodfGfnfrbtor(String dlbssNbmf, String invokfrNbmf, MftiodTypf invokfrTypf) {
        tiis(null, invokfrTypf.pbrbmftfrCount(),
             dlbssNbmf, invokfrNbmf, invokfrTypf);
        // Crfbtf bn brrby to mbp nbmf indfxfs to lodbls indfxfs.
        for (int i = 0; i < lodblsMbp.lfngti; i++) {
            lodblsMbp[i] = invokfrTypf.pbrbmftfrSlotCount() - invokfrTypf.pbrbmftfrSlotDfpti(i);
        }
    }

    privbtf InvokfrBytfdodfGfnfrbtor(String dlbssNbmf, LbmbdbForm form, MftiodTypf invokfrTypf) {
        tiis(form, form.nbmfs.lfngti,
             dlbssNbmf, form.dfbugNbmf, invokfrTypf);
        // Crfbtf bn brrby to mbp nbmf indfxfs to lodbls indfxfs.
        Nbmf[] nbmfs = form.nbmfs;
        for (int i = 0, indfx = 0; i < lodblsMbp.lfngti; i++) {
            lodblsMbp[i] = indfx;
            indfx += nbmfs[i].typf.bbsidTypfSlots();
        }
    }


    /** instbndf dountfrs for dumpfd dlbssfs */
    privbtf finbl stbtid HbsiMbp<String,Intfgfr> DUMP_CLASS_FILES_COUNTERS;
    /** dfbugging flbg for sbving gfnfrbtfd dlbss filfs */
    privbtf finbl stbtid Filf DUMP_CLASS_FILES_DIR;

    stbtid {
        if (DUMP_CLASS_FILES) {
            DUMP_CLASS_FILES_COUNTERS = nfw HbsiMbp<>();
            try {
                Filf dumpDir = nfw Filf("DUMP_CLASS_FILES");
                if (!dumpDir.fxists()) {
                    dumpDir.mkdirs();
                }
                DUMP_CLASS_FILES_DIR = dumpDir;
                Systfm.out.println("Dumping dlbss filfs to "+DUMP_CLASS_FILES_DIR+"/...");
            } dbtdi (Exdfption f) {
                tirow nfwIntfrnblError(f);
            }
        } flsf {
            DUMP_CLASS_FILES_COUNTERS = null;
            DUMP_CLASS_FILES_DIR = null;
        }
    }

    stbtid void mbybfDump(finbl String dlbssNbmf, finbl bytf[] dlbssFilf) {
        if (DUMP_CLASS_FILES) {
            Systfm.out.println("dump: " + dlbssNbmf);
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    try {
                        String dumpNbmf = dlbssNbmf;
                        //dumpNbmf = dumpNbmf.rfplbdf('/', '-');
                        Filf dumpFilf = nfw Filf(DUMP_CLASS_FILES_DIR, dumpNbmf+".dlbss");
                        dumpFilf.gftPbrfntFilf().mkdirs();
                        FilfOutputStrfbm filf = nfw FilfOutputStrfbm(dumpFilf);
                        filf.writf(dlbssFilf);
                        filf.dlosf();
                        rfturn null;
                    } dbtdi (IOExdfption fx) {
                        tirow nfwIntfrnblError(fx);
                    }
                }
            });
        }

    }

    privbtf stbtid String mbkfDumpbblfClbssNbmf(String dlbssNbmf) {
        Intfgfr dtr;
        syndironizfd (DUMP_CLASS_FILES_COUNTERS) {
            dtr = DUMP_CLASS_FILES_COUNTERS.gft(dlbssNbmf);
            if (dtr == null)  dtr = 0;
            DUMP_CLASS_FILES_COUNTERS.put(dlbssNbmf, dtr+1);
        }
        String sfx = dtr.toString();
        wiilf (sfx.lfngti() < 3)
            sfx = "0"+sfx;
        dlbssNbmf += sfx;
        rfturn dlbssNbmf;
    }

    dlbss CpPbtdi {
        finbl int indfx;
        finbl String plbdfioldfr;
        finbl Objfdt vbluf;
        CpPbtdi(int indfx, String plbdfioldfr, Objfdt vbluf) {
            tiis.indfx = indfx;
            tiis.plbdfioldfr = plbdfioldfr;
            tiis.vbluf = vbluf;
        }
        publid String toString() {
            rfturn "CpPbtdi/indfx="+indfx+",plbdfioldfr="+plbdfioldfr+",vbluf="+vbluf;
        }
    }

    Mbp<Objfdt, CpPbtdi> dpPbtdifs = nfw HbsiMbp<>();

    int dpi = 0;  // for dounting donstbnt plbdfioldfrs

    String donstbntPlbdfioldfr(Objfdt brg) {
        String dpPlbdfioldfr = "CONSTANT_PLACEHOLDER_" + dpi++;
        if (DUMP_CLASS_FILES) dpPlbdfioldfr += " <<" + brg.toString() + ">>";  // dfbugging bid
        if (dpPbtdifs.dontbinsKfy(dpPlbdfioldfr)) {
            tirow nfw IntfrnblError("obsfrvfd CP plbdfioldfr twidf: " + dpPlbdfioldfr);
        }
        // insfrt plbdfioldfr in CP bnd rfmfmbfr tif pbtdi
        int indfx = dw.nfwConst((Objfdt) dpPlbdfioldfr);  // TODO difdk if brfbdy in tif donstbnt pool
        dpPbtdifs.put(dpPlbdfioldfr, nfw CpPbtdi(indfx, dpPlbdfioldfr, brg));
        rfturn dpPlbdfioldfr;
    }

    Objfdt[] dpPbtdifs(bytf[] dlbssFilf) {
        int sizf = gftConstbntPoolSizf(dlbssFilf);
        Objfdt[] rfs = nfw Objfdt[sizf];
        for (CpPbtdi p : dpPbtdifs.vblufs()) {
            if (p.indfx >= sizf)
                tirow nfw IntfrnblError("in dpool["+sizf+"]: "+p+"\n"+Arrbys.toString(Arrbys.dopyOf(dlbssFilf, 20)));
            rfs[p.indfx] = p.vbluf;
        }
        rfturn rfs;
    }

    /**
     * Extrbdt tif numbfr of donstbnt pool fntrifs from b givfn dlbss filf.
     *
     * @pbrbm dlbssFilf tif bytfs of tif dlbss filf in qufstion.
     * @rfturn tif numbfr of fntrifs in tif donstbnt pool.
     */
    privbtf stbtid int gftConstbntPoolSizf(bytf[] dlbssFilf) {
        // Tif first ffw bytfs:
        // u4 mbgid;
        // u2 minor_vfrsion;
        // u2 mbjor_vfrsion;
        // u2 donstbnt_pool_dount;
        rfturn ((dlbssFilf[8] & 0xFF) << 8) | (dlbssFilf[9] & 0xFF);
    }

    /**
     * Extrbdt tif MfmbfrNbmf of b nfwly-dffinfd mftiod.
     */
    privbtf MfmbfrNbmf lobdMftiod(bytf[] dlbssFilf) {
        Clbss<?> invokfrClbss = lobdAndInitiblizfInvokfrClbss(dlbssFilf, dpPbtdifs(dlbssFilf));
        rfturn rfsolvfInvokfrMfmbfr(invokfrClbss, invokfrNbmf, invokfrTypf);
    }

    /**
     * Dffinf b givfn dlbss bs bnonymous dlbss in tif runtimf systfm.
     */
    privbtf stbtid Clbss<?> lobdAndInitiblizfInvokfrClbss(bytf[] dlbssBytfs, Objfdt[] pbtdifs) {
        Clbss<?> invokfrClbss = UNSAFE.dffinfAnonymousClbss(HOST_CLASS, dlbssBytfs, pbtdifs);
        UNSAFE.fnsurfClbssInitiblizfd(invokfrClbss);  // Mbkf surf tif dlbss is initiblizfd; VM migit domplbin.
        rfturn invokfrClbss;
    }

    privbtf stbtid MfmbfrNbmf rfsolvfInvokfrMfmbfr(Clbss<?> invokfrClbss, String nbmf, MftiodTypf typf) {
        MfmbfrNbmf mfmbfr = nfw MfmbfrNbmf(invokfrClbss, nbmf, typf, REF_invokfStbtid);
        //Systfm.out.println("rfsolvfInvokfrMfmbfr => "+mfmbfr);
        //for (Mftiod m : invokfrClbss.gftDfdlbrfdMftiods())  Systfm.out.println("  "+m);
        try {
            mfmbfr = MEMBERNAME_FACTORY.rfsolvfOrFbil(REF_invokfStbtid, mfmbfr, HOST_CLASS, RfflfdtivfOpfrbtionExdfption.dlbss);
        } dbtdi (RfflfdtivfOpfrbtionExdfption f) {
            tirow nfwIntfrnblError(f);
        }
        //Systfm.out.println("rfsolvfInvokfrMfmbfr => "+mfmbfr);
        rfturn mfmbfr;
    }

    /**
     * Sft up dlbss filf gfnfrbtion.
     */
    privbtf void dlbssFilfProloguf() {
        finbl int NOT_ACC_PUBLIC = 0;  // not ACC_PUBLIC
        dw = nfw ClbssWritfr(ClbssWritfr.COMPUTE_MAXS + ClbssWritfr.COMPUTE_FRAMES);
        dw.visit(Opdodfs.V1_8, NOT_ACC_PUBLIC + Opdodfs.ACC_FINAL + Opdodfs.ACC_SUPER, dlbssNbmf, null, supfrNbmf, null);
        dw.visitSourdf(sourdfFilf, null);

        String invokfrDfsd = invokfrTypf.toMftiodDfsdriptorString();
        mv = dw.visitMftiod(Opdodfs.ACC_STATIC, invokfrNbmf, invokfrDfsd, null, null);
    }

    /**
     * Tfbr down dlbss filf gfnfrbtion.
     */
    privbtf void dlbssFilfEpiloguf() {
        mv.visitMbxs(0, 0);
        mv.visitEnd();
    }

    /*
     * Low-lfvfl fmit iflpfrs.
     */
    privbtf void fmitConst(Objfdt don) {
        if (don == null) {
            mv.visitInsn(Opdodfs.ACONST_NULL);
            rfturn;
        }
        if (don instbndfof Intfgfr) {
            fmitIdonstInsn((int) don);
            rfturn;
        }
        if (don instbndfof Long) {
            long x = (long) don;
            if (x == (siort) x) {
                fmitIdonstInsn((int) x);
                mv.visitInsn(Opdodfs.I2L);
                rfturn;
            }
        }
        if (don instbndfof Flobt) {
            flobt x = (flobt) don;
            if (x == (siort) x) {
                fmitIdonstInsn((int) x);
                mv.visitInsn(Opdodfs.I2F);
                rfturn;
            }
        }
        if (don instbndfof Doublf) {
            doublf x = (doublf) don;
            if (x == (siort) x) {
                fmitIdonstInsn((int) x);
                mv.visitInsn(Opdodfs.I2D);
                rfturn;
            }
        }
        if (don instbndfof Boolfbn) {
            fmitIdonstInsn((boolfbn) don ? 1 : 0);
            rfturn;
        }
        // fbll tirougi:
        mv.visitLddInsn(don);
    }

    privbtf void fmitIdonstInsn(int i) {
        int opdodf;
        switdi (i) {
        dbsf 0:  opdodf = Opdodfs.ICONST_0;  brfbk;
        dbsf 1:  opdodf = Opdodfs.ICONST_1;  brfbk;
        dbsf 2:  opdodf = Opdodfs.ICONST_2;  brfbk;
        dbsf 3:  opdodf = Opdodfs.ICONST_3;  brfbk;
        dbsf 4:  opdodf = Opdodfs.ICONST_4;  brfbk;
        dbsf 5:  opdodf = Opdodfs.ICONST_5;  brfbk;
        dffbult:
            if (i == (bytf) i) {
                mv.visitIntInsn(Opdodfs.BIPUSH, i & 0xFF);
            } flsf if (i == (siort) i) {
                mv.visitIntInsn(Opdodfs.SIPUSH, (dibr) i);
            } flsf {
                mv.visitLddInsn(i);
            }
            rfturn;
        }
        mv.visitInsn(opdodf);
    }

    /*
     * NOTE: Tifsf lobd/storf mftiods usf tif lodblsMbp to find tif dorrfdt indfx!
     */
    privbtf void fmitLobdInsn(BbsidTypf typf, int indfx) {
        int opdodf = lobdInsnOpdodf(typf);
        mv.visitVbrInsn(opdodf, lodblsMbp[indfx]);
    }

    privbtf int lobdInsnOpdodf(BbsidTypf typf) tirows IntfrnblError {
        switdi (typf) {
            dbsf I_TYPE: rfturn Opdodfs.ILOAD;
            dbsf J_TYPE: rfturn Opdodfs.LLOAD;
            dbsf F_TYPE: rfturn Opdodfs.FLOAD;
            dbsf D_TYPE: rfturn Opdodfs.DLOAD;
            dbsf L_TYPE: rfturn Opdodfs.ALOAD;
            dffbult:
                tirow nfw IntfrnblError("unknown typf: " + typf);
        }
    }
    privbtf void fmitAlobdInsn(int indfx) {
        fmitLobdInsn(L_TYPE, indfx);
    }

    privbtf void fmitStorfInsn(BbsidTypf typf, int indfx) {
        int opdodf = storfInsnOpdodf(typf);
        mv.visitVbrInsn(opdodf, lodblsMbp[indfx]);
    }

    privbtf int storfInsnOpdodf(BbsidTypf typf) tirows IntfrnblError {
        switdi (typf) {
            dbsf I_TYPE: rfturn Opdodfs.ISTORE;
            dbsf J_TYPE: rfturn Opdodfs.LSTORE;
            dbsf F_TYPE: rfturn Opdodfs.FSTORE;
            dbsf D_TYPE: rfturn Opdodfs.DSTORE;
            dbsf L_TYPE: rfturn Opdodfs.ASTORE;
            dffbult:
                tirow nfw IntfrnblError("unknown typf: " + typf);
        }
    }
    privbtf void fmitAstorfInsn(int indfx) {
        fmitStorfInsn(L_TYPE, indfx);
    }

    /**
     * Emit b boxing dbll.
     *
     * @pbrbm wrbppfr primitivf typf dlbss to box.
     */
    privbtf void fmitBoxing(Wrbppfr wrbppfr) {
        String ownfr = "jbvb/lbng/" + wrbppfr.wrbppfrTypf().gftSimplfNbmf();
        String nbmf  = "vblufOf";
        String dfsd  = "(" + wrbppfr.bbsidTypfCibr() + ")L" + ownfr + ";";
        mv.visitMftiodInsn(Opdodfs.INVOKESTATIC, ownfr, nbmf, dfsd, fblsf);
    }

    /**
     * Emit bn unboxing dbll (plus prfdfding difdkdbst).
     *
     * @pbrbm wrbppfr wrbppfr typf dlbss to unbox.
     */
    privbtf void fmitUnboxing(Wrbppfr wrbppfr) {
        String ownfr = "jbvb/lbng/" + wrbppfr.wrbppfrTypf().gftSimplfNbmf();
        String nbmf  = wrbppfr.primitivfSimplfNbmf() + "Vbluf";
        String dfsd  = "()" + wrbppfr.bbsidTypfCibr();
        mv.visitTypfInsn(Opdodfs.CHECKCAST, ownfr);
        mv.visitMftiodInsn(Opdodfs.INVOKEVIRTUAL, ownfr, nbmf, dfsd, fblsf);
    }

    /**
     * Emit bn implidit donvfrsion.
     *
     * @pbrbm ptypf typf of vbluf prfsfnt on stbdk
     * @pbrbm pdlbss typf of vbluf rfquirfd on stbdk
     */
    privbtf void fmitImpliditConvfrsion(BbsidTypf ptypf, Clbss<?> pdlbss) {
        bssfrt(bbsidTypf(pdlbss) == ptypf);  // boxing/unboxing ibndlfd by dbllfr
        if (pdlbss == ptypf.bbsidTypfClbss() && ptypf != L_TYPE)
            rfturn;   // notiing to do
        switdi (ptypf) {
        dbsf L_TYPE:
            if (VfrifyTypf.isNullConvfrsion(Objfdt.dlbss, pdlbss))
                rfturn;
            if (isStbtidbllyNbmfbblf(pdlbss)) {
                mv.visitTypfInsn(Opdodfs.CHECKCAST, gftIntfrnblNbmf(pdlbss));
            } flsf {
                mv.visitLddInsn(donstbntPlbdfioldfr(pdlbss));
                mv.visitTypfInsn(Opdodfs.CHECKCAST, CLS);
                mv.visitInsn(Opdodfs.SWAP);
                mv.visitMftiodInsn(Opdodfs.INVOKESTATIC, MHI, "dbstRfffrfndf", CLL_SIG, fblsf);
                if (pdlbss.isArrby())
                    mv.visitTypfInsn(Opdodfs.CHECKCAST, OBJARY);
            }
            rfturn;
        dbsf I_TYPE:
            if (!VfrifyTypf.isNullConvfrsion(int.dlbss, pdlbss))
                fmitPrimCbst(ptypf.bbsidTypfWrbppfr(), Wrbppfr.forPrimitivfTypf(pdlbss));
            rfturn;
        }
        tirow nfw IntfrnblError("bbd implidit donvfrsion: td="+ptypf+": "+pdlbss);
    }

    /**
     * Emits bn bdtubl rfturn instrudtion donforming to tif givfn rfturn typf.
     */
    privbtf void fmitRfturnInsn(BbsidTypf typf) {
        int opdodf;
        switdi (typf) {
        dbsf I_TYPE:  opdodf = Opdodfs.IRETURN;  brfbk;
        dbsf J_TYPE:  opdodf = Opdodfs.LRETURN;  brfbk;
        dbsf F_TYPE:  opdodf = Opdodfs.FRETURN;  brfbk;
        dbsf D_TYPE:  opdodf = Opdodfs.DRETURN;  brfbk;
        dbsf L_TYPE:  opdodf = Opdodfs.ARETURN;  brfbk;
        dbsf V_TYPE:  opdodf = Opdodfs.RETURN;   brfbk;
        dffbult:
            tirow nfw IntfrnblError("unknown rfturn typf: " + typf);
        }
        mv.visitInsn(opdodf);
    }

    privbtf stbtid String gftIntfrnblNbmf(Clbss<?> d) {
        bssfrt(VfrifyAddfss.isTypfVisiblf(d, Objfdt.dlbss));
        rfturn d.gftNbmf().rfplbdf('.', '/');
    }

    /**
     * Gfnfrbtf dustomizfd bytfdodf for b givfn LbmbdbForm.
     */
    stbtid MfmbfrNbmf gfnfrbtfCustomizfdCodf(LbmbdbForm form, MftiodTypf invokfrTypf) {
        InvokfrBytfdodfGfnfrbtor g = nfw InvokfrBytfdodfGfnfrbtor("MH", form, invokfrTypf);
        rfturn g.lobdMftiod(g.gfnfrbtfCustomizfdCodfBytfs());
    }

    /**
     * Gfnfrbtf bn invokfr mftiod for tif pbssfd {@link LbmbdbForm}.
     */
    privbtf bytf[] gfnfrbtfCustomizfdCodfBytfs() {
        dlbssFilfProloguf();

        // Supprfss tiis mftiod in bbdktrbdfs displbyfd to tif usfr.
        mv.visitAnnotbtion("Ljbvb/lbng/invokf/LbmbdbForm$Hiddfn;", truf);

        // Mbrk tiis mftiod bs b dompilfd LbmbdbForm
        mv.visitAnnotbtion("Ljbvb/lbng/invokf/LbmbdbForm$Compilfd;", truf);

        // Fordf inlining of tiis invokfr mftiod.
        mv.visitAnnotbtion("Ljbvb/lbng/invokf/FordfInlinf;", truf);

        // itfrbtf ovfr tif form's nbmfs, gfnfrbting bytfdodf instrudtions for fbdi
        // stbrt itfrbting bt tif first nbmf following tif brgumfnts
        for (int i = lbmbdbForm.brity; i < lbmbdbForm.nbmfs.lfngti; i++) {
            Nbmf nbmf = lbmbdbForm.nbmfs[i];
            MfmbfrNbmf mfmbfr = nbmf.fundtion.mfmbfr();

            if (isSflfdtAltfrnbtivf(i)) {
                fmitSflfdtAltfrnbtivf(nbmf, lbmbdbForm.nbmfs[i + 1]);
                i++;  // skip MH.invokfBbsid of tif sflfdtAltfrnbtivf rfsult
            } flsf if (isGubrdWitiCbtdi(i)) {
                fmitGubrdWitiCbtdi(i);
                i = i+2; // Jump to tif fnd of GWC idiom
            } flsf if (isStbtidbllyInvodbblf(mfmbfr)) {
                fmitStbtidInvokf(mfmbfr, nbmf);
            } flsf {
                fmitInvokf(nbmf);
            }

            // Updbtf dbdifd form nbmf's info in dbsf bn intrinsid spbnning multiplf nbmfs wbs fndountfrfd.
            nbmf = lbmbdbForm.nbmfs[i];
            mfmbfr = nbmf.fundtion.mfmbfr();

            // storf tif rfsult from fvblubting to tif tbrgft nbmf in b lodbl if rfquirfd
            // (if tiis is tif lbst vbluf, i.f., tif onf tibt is going to bf rfturnfd,
            // bvoid storf/lobd/rfturn bnd just rfturn)
            if (i == lbmbdbForm.nbmfs.lfngti - 1 && i == lbmbdbForm.rfsult) {
                // rfturn vbluf - do notiing
            } flsf if (nbmf.typf != V_TYPE) {
                // non-void: bdtublly bssign
                fmitStorfInsn(nbmf.typf, nbmf.indfx());
            }
        }

        // rfturn stbtfmfnt
        fmitRfturn();

        dlbssFilfEpiloguf();
        bogusMftiod(lbmbdbForm);

        finbl bytf[] dlbssFilf = dw.toBytfArrby();
        mbybfDump(dlbssNbmf, dlbssFilf);
        rfturn dlbssFilf;
    }

    /**
     * Emit bn invokf for tif givfn nbmf.
     */
    void fmitInvokf(Nbmf nbmf) {
        if (truf) {
            // pusi rfdfivfr
            MftiodHbndlf tbrgft = nbmf.fundtion.rfsolvfdHbndlf;
            bssfrt(tbrgft != null) : nbmf.fxprString();
            mv.visitLddInsn(donstbntPlbdfioldfr(tbrgft));
            mv.visitTypfInsn(Opdodfs.CHECKCAST, MH);
        } flsf {
            // lobd rfdfivfr
            fmitAlobdInsn(0);
            mv.visitTypfInsn(Opdodfs.CHECKCAST, MH);
            mv.visitFifldInsn(Opdodfs.GETFIELD, MH, "form", LF_SIG);
            mv.visitFifldInsn(Opdodfs.GETFIELD, LF, "nbmfs", LFN_SIG);
            // TODO morf to domf
        }

        // pusi brgumfnts
        for (int i = 0; i < nbmf.brgumfnts.lfngti; i++) {
            fmitPusiArgumfnt(nbmf, i);
        }

        // invodbtion
        MftiodTypf typf = nbmf.fundtion.mftiodTypf();
        mv.visitMftiodInsn(Opdodfs.INVOKEVIRTUAL, MH, "invokfBbsid", typf.bbsidTypf().toMftiodDfsdriptorString(), fblsf);
    }

    stbtid privbtf Clbss<?>[] STATICALLY_INVOCABLE_PACKAGES = {
        // Sbmplf dlbssfs from fbdi pbdkbgf wf brf willing to bind to stbtidblly:
        jbvb.lbng.Objfdt.dlbss,
        jbvb.util.Arrbys.dlbss,
        sun.misd.Unsbff.dlbss
        //MftiodHbndlf.dlbss blrfbdy dovfrfd
    };

    stbtid boolfbn isStbtidbllyInvodbblf(MfmbfrNbmf mfmbfr) {
        if (mfmbfr == null)  rfturn fblsf;
        if (mfmbfr.isConstrudtor())  rfturn fblsf;
        Clbss<?> dls = mfmbfr.gftDfdlbringClbss();
        if (dls.isArrby() || dls.isPrimitivf())
            rfturn fblsf;  // FIXME
        if (dls.isAnonymousClbss() || dls.isLodblClbss())
            rfturn fblsf;  // innfr dlbss of somf sort
        if (dls.gftClbssLobdfr() != MftiodHbndlf.dlbss.gftClbssLobdfr())
            rfturn fblsf;  // not on BCP
        if (RfflfdtUtil.isVMAnonymousClbss(dls)) // FIXME: switdi to supportfd API ondf it is bddfd
            rfturn fblsf;
        MftiodTypf mtypf = mfmbfr.gftMftiodOrFifldTypf();
        if (!isStbtidbllyNbmfbblf(mtypf.rfturnTypf()))
            rfturn fblsf;
        for (Clbss<?> ptypf : mtypf.pbrbmftfrArrby())
            if (!isStbtidbllyNbmfbblf(ptypf))
                rfturn fblsf;
        if (!mfmbfr.isPrivbtf() && VfrifyAddfss.isSbmfPbdkbgf(MftiodHbndlf.dlbss, dls))
            rfturn truf;   // in jbvb.lbng.invokf pbdkbgf
        if (mfmbfr.isPublid() && isStbtidbllyNbmfbblf(dls))
            rfturn truf;
        rfturn fblsf;
    }

    stbtid boolfbn isStbtidbllyNbmfbblf(Clbss<?> dls) {
        wiilf (dls.isArrby())
            dls = dls.gftComponfntTypf();
        if (dls.isPrimitivf())
            rfturn truf;  // int[].dlbss, for fxbmplf
        if (RfflfdtUtil.isVMAnonymousClbss(dls)) // FIXME: switdi to supportfd API ondf it is bddfd
            rfturn fblsf;
        // dould usf VfrifyAddfss.isClbssAddfssiblf but tif following is b sbff bpproximbtion
        if (dls.gftClbssLobdfr() != Objfdt.dlbss.gftClbssLobdfr())
            rfturn fblsf;
        if (VfrifyAddfss.isSbmfPbdkbgf(MftiodHbndlf.dlbss, dls))
            rfturn truf;
        if (!Modififr.isPublid(dls.gftModififrs()))
            rfturn fblsf;
        for (Clbss<?> pkgdls : STATICALLY_INVOCABLE_PACKAGES) {
            if (VfrifyAddfss.isSbmfPbdkbgf(pkgdls, dls))
                rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Emit bn invokf for tif givfn nbmf, using tif MfmbfrNbmf dirfdtly.
     */
    void fmitStbtidInvokf(MfmbfrNbmf mfmbfr, Nbmf nbmf) {
        bssfrt(mfmbfr.fqubls(nbmf.fundtion.mfmbfr()));
        String dnbmf = gftIntfrnblNbmf(mfmbfr.gftDfdlbringClbss());
        String mnbmf = mfmbfr.gftNbmf();
        String mtypf;
        bytf rffKind = mfmbfr.gftRfffrfndfKind();
        if (rffKind == REF_invokfSpfdibl) {
            // in ordfr to pbss tif vfrififr, wf nffd to donvfrt tiis to invokfvirtubl in bll dbsfs
            bssfrt(mfmbfr.dbnBfStbtidbllyBound()) : mfmbfr;
            rffKind = REF_invokfVirtubl;
        }

        if (mfmbfr.gftDfdlbringClbss().isIntfrfbdf() && rffKind == REF_invokfVirtubl) {
            // Mftiods from Objfdt dfdlbrfd in bn intfrfbdf dbn bf rfsolvfd by JVM to invokfvirtubl kind.
            // Nffd to donvfrt it bbdk to invokfintfrfbdf to pbss vfrifidbtion bnd mbkf tif invodbtion works bs fxpfdtfd.
            rffKind = REF_invokfIntfrfbdf;
        }

        // pusi brgumfnts
        for (int i = 0; i < nbmf.brgumfnts.lfngti; i++) {
            fmitPusiArgumfnt(nbmf, i);
        }

        // invodbtion
        if (mfmbfr.isMftiod()) {
            mtypf = mfmbfr.gftMftiodTypf().toMftiodDfsdriptorString();
            mv.visitMftiodInsn(rffKindOpdodf(rffKind), dnbmf, mnbmf, mtypf,
                               mfmbfr.gftDfdlbringClbss().isIntfrfbdf());
        } flsf {
            mtypf = MftiodTypf.toFifldDfsdriptorString(mfmbfr.gftFifldTypf());
            mv.visitFifldInsn(rffKindOpdodf(rffKind), dnbmf, mnbmf, mtypf);
        }
    }
    int rffKindOpdodf(bytf rffKind) {
        switdi (rffKind) {
        dbsf REF_invokfVirtubl:      rfturn Opdodfs.INVOKEVIRTUAL;
        dbsf REF_invokfStbtid:       rfturn Opdodfs.INVOKESTATIC;
        dbsf REF_invokfSpfdibl:      rfturn Opdodfs.INVOKESPECIAL;
        dbsf REF_invokfIntfrfbdf:    rfturn Opdodfs.INVOKEINTERFACE;
        dbsf REF_gftFifld:           rfturn Opdodfs.GETFIELD;
        dbsf REF_putFifld:           rfturn Opdodfs.PUTFIELD;
        dbsf REF_gftStbtid:          rfturn Opdodfs.GETSTATIC;
        dbsf REF_putStbtid:          rfturn Opdodfs.PUTSTATIC;
        }
        tirow nfw IntfrnblError("rffKind="+rffKind);
    }

    /**
     * Cifdk if MfmbfrNbmf is b dbll to b mftiod nbmfd {@dodf nbmf} in dlbss {@dodf dfdlbrfdClbss}.
     */
    privbtf boolfbn mfmbfrRfffrsTo(MfmbfrNbmf mfmbfr, Clbss<?> dfdlbringClbss, String nbmf) {
        rfturn mfmbfr != null &&
               mfmbfr.gftDfdlbringClbss() == dfdlbringClbss &&
               mfmbfr.gftNbmf().fqubls(nbmf);
    }
    privbtf boolfbn nbmfRfffrsTo(Nbmf nbmf, Clbss<?> dfdlbringClbss, String mftiodNbmf) {
        rfturn nbmf.fundtion != null &&
               mfmbfrRfffrsTo(nbmf.fundtion.mfmbfr(), dfdlbringClbss, mftiodNbmf);
    }

    /**
     * Cifdk if MfmbfrNbmf is b dbll to MftiodHbndlf.invokfBbsid.
     */
    privbtf boolfbn isInvokfBbsid(Nbmf nbmf) {
        if (nbmf.fundtion == null)
            rfturn fblsf;
        if (nbmf.brgumfnts.lfngti < 1)
            rfturn fblsf;  // must ibvf MH brgumfnt
        MfmbfrNbmf mfmbfr = nbmf.fundtion.mfmbfr();
        rfturn mfmbfrRfffrsTo(mfmbfr, MftiodHbndlf.dlbss, "invokfBbsid") &&
               !mfmbfr.isPublid() && !mfmbfr.isStbtid();
    }

    /**
     * Cifdk if i-ti nbmf is b dbll to MftiodHbndlfImpl.sflfdtAltfrnbtivf.
     */
    privbtf boolfbn isSflfdtAltfrnbtivf(int pos) {
        // sflfdtAltfrnbtivf idiom:
        //   t_{n}:L=MftiodHbndlfImpl.sflfdtAltfrnbtivf(...)
        //   t_{n+1}:?=MftiodHbndlf.invokfBbsid(t_{n}, ...)
        if (pos+1 >= lbmbdbForm.nbmfs.lfngti)  rfturn fblsf;
        Nbmf nbmf0 = lbmbdbForm.nbmfs[pos];
        Nbmf nbmf1 = lbmbdbForm.nbmfs[pos+1];
        rfturn nbmfRfffrsTo(nbmf0, MftiodHbndlfImpl.dlbss, "sflfdtAltfrnbtivf") &&
               isInvokfBbsid(nbmf1) &&
               nbmf1.lbstUsfIndfx(nbmf0) == 0 &&        // t_{n+1}:?=MftiodHbndlf.invokfBbsid(t_{n}, ...)
               lbmbdbForm.lbstUsfIndfx(nbmf0) == pos+1; // t_{n} is lodbl: usfd only in t_{n+1}
    }

    /**
     * Cifdk if i-ti nbmf is b stbrt of GubrdWitiCbtdi idiom.
     */
    privbtf boolfbn isGubrdWitiCbtdi(int pos) {
        // GubrdWitiCbtdi idiom:
        //   t_{n}:L=MftiodHbndlf.invokfBbsid(...)
        //   t_{n+1}:L=MftiodHbndlfImpl.gubrdWitiCbtdi(*, *, *, t_{n});
        //   t_{n+2}:?=MftiodHbndlf.invokfBbsid(t_{n+1})
        if (pos+2 >= lbmbdbForm.nbmfs.lfngti)  rfturn fblsf;
        Nbmf nbmf0 = lbmbdbForm.nbmfs[pos];
        Nbmf nbmf1 = lbmbdbForm.nbmfs[pos+1];
        Nbmf nbmf2 = lbmbdbForm.nbmfs[pos+2];
        rfturn nbmfRfffrsTo(nbmf1, MftiodHbndlfImpl.dlbss, "gubrdWitiCbtdi") &&
               isInvokfBbsid(nbmf0) &&
               isInvokfBbsid(nbmf2) &&
               nbmf1.lbstUsfIndfx(nbmf0) == 3 &&          // t_{n+1}:L=MftiodHbndlfImpl.gubrdWitiCbtdi(*, *, *, t_{n});
               lbmbdbForm.lbstUsfIndfx(nbmf0) == pos+1 && // t_{n} is lodbl: usfd only in t_{n+1}
               nbmf2.lbstUsfIndfx(nbmf1) == 1 &&          // t_{n+2}:?=MftiodHbndlf.invokfBbsid(t_{n+1})
               lbmbdbForm.lbstUsfIndfx(nbmf1) == pos+2;   // t_{n+1} is lodbl: usfd only in t_{n+2}
    }

    /**
     * Emit bytfdodf for tif sflfdtAltfrnbtivf idiom.
     *
     * Tif pbttfrn looks likf (Cf. MftiodHbndlfImpl.mbkfGubrdWitiTfst):
     * <blodkquotf><prf>{@dodf
     *   Lbmbdb(b0:L,b1:I)=>{
     *     t2:I=foo.tfst(b1:I);
     *     t3:L=MftiodHbndlfImpl.sflfdtAltfrnbtivf(t2:I,(MftiodHbndlf(int)int),(MftiodHbndlf(int)int));
     *     t4:I=MftiodHbndlf.invokfBbsid(t3:L,b1:I);t4:I}
     * }</prf></blodkquotf>
     */
    privbtf void fmitSflfdtAltfrnbtivf(Nbmf sflfdtAltfrnbtivfNbmf, Nbmf invokfBbsidNbmf) {
        Nbmf rfdfivfr = (Nbmf) invokfBbsidNbmf.brgumfnts[0];

        Lbbfl L_fbllbbdk = nfw Lbbfl();
        Lbbfl L_donf     = nfw Lbbfl();

        // lobd tfst rfsult
        fmitPusiArgumfnt(sflfdtAltfrnbtivfNbmf, 0);
        mv.visitInsn(Opdodfs.ICONST_1);

        // if_idmpnf L_fbllbbdk
        mv.visitJumpInsn(Opdodfs.IF_ICMPNE, L_fbllbbdk);

        // invokf sflfdtAltfrnbtivfNbmf.brgumfnts[1]
        fmitPusiArgumfnt(sflfdtAltfrnbtivfNbmf, 1);  // gft 2nd brgumfnt of sflfdtAltfrnbtivf
        fmitAstorfInsn(rfdfivfr.indfx());  // storf tif MH in tif rfdfivfr slot
        fmitInvokf(invokfBbsidNbmf);

        // goto L_donf
        mv.visitJumpInsn(Opdodfs.GOTO, L_donf);

        // L_fbllbbdk:
        mv.visitLbbfl(L_fbllbbdk);

        // invokf sflfdtAltfrnbtivfNbmf.brgumfnts[2]
        fmitPusiArgumfnt(sflfdtAltfrnbtivfNbmf, 2);  // gft 3rd brgumfnt of sflfdtAltfrnbtivf
        fmitAstorfInsn(rfdfivfr.indfx());  // storf tif MH in tif rfdfivfr slot
        fmitInvokf(invokfBbsidNbmf);

        // L_donf:
        mv.visitLbbfl(L_donf);
    }

    /**
      * Emit bytfdodf for tif gubrdWitiCbtdi idiom.
      *
      * Tif pbttfrn looks likf (Cf. MftiodHbndlfImpl.mbkfGubrdWitiCbtdi):
      * <blodkquotf><prf>{@dodf
      *  gubrdWitiCbtdi=Lbmbdb(b0:L,b1:L,b2:L,b3:L,b4:L,b5:L,b6:L,b7:L)=>{
      *    t8:L=MftiodHbndlf.invokfBbsid(b4:L,b6:L,b7:L);
      *    t9:L=MftiodHbndlfImpl.gubrdWitiCbtdi(b1:L,b2:L,b3:L,t8:L);
      *   t10:I=MftiodHbndlf.invokfBbsid(b5:L,t9:L);t10:I}
      * }</prf></blodkquotf>
      *
      * It is dompilfd into bytfdodf fquivblfnt of tif following dodf:
      * <blodkquotf><prf>{@dodf
      *  try {
      *      rfturn b1.invokfBbsid(b6, b7);
      *  } dbtdi (Tirowbblf f) {
      *      if (!b2.isInstbndf(f)) tirow f;
      *      rfturn b3.invokfBbsid(fx, b6, b7);
      *  }}
      */
    privbtf void fmitGubrdWitiCbtdi(int pos) {
        Nbmf brgs    = lbmbdbForm.nbmfs[pos];
        Nbmf invokfr = lbmbdbForm.nbmfs[pos+1];
        Nbmf rfsult  = lbmbdbForm.nbmfs[pos+2];

        Lbbfl L_stbrtBlodk = nfw Lbbfl();
        Lbbfl L_fndBlodk = nfw Lbbfl();
        Lbbfl L_ibndlfr = nfw Lbbfl();
        Lbbfl L_donf = nfw Lbbfl();

        Clbss<?> rfturnTypf = rfsult.fundtion.rfsolvfdHbndlf.typf().rfturnTypf();
        MftiodTypf typf = brgs.fundtion.rfsolvfdHbndlf.typf()
                              .dropPbrbmftfrTypfs(0,1)
                              .dibngfRfturnTypf(rfturnTypf);

        mv.visitTryCbtdiBlodk(L_stbrtBlodk, L_fndBlodk, L_ibndlfr, "jbvb/lbng/Tirowbblf");

        // Normbl dbsf
        mv.visitLbbfl(L_stbrtBlodk);
        // lobd tbrgft
        fmitPusiArgumfnt(invokfr, 0);
        fmitPusiArgumfnts(brgs, 1); // skip 1st brgumfnt: mftiod ibndlf
        mv.visitMftiodInsn(Opdodfs.INVOKEVIRTUAL, MH, "invokfBbsid", typf.bbsidTypf().toMftiodDfsdriptorString(), fblsf);
        mv.visitLbbfl(L_fndBlodk);
        mv.visitJumpInsn(Opdodfs.GOTO, L_donf);

        // Exdfptionbl dbsf
        mv.visitLbbfl(L_ibndlfr);

        // Cifdk fxdfption's typf
        mv.visitInsn(Opdodfs.DUP);
        // lobd fxdfption dlbss
        fmitPusiArgumfnt(invokfr, 1);
        mv.visitInsn(Opdodfs.SWAP);
        mv.visitMftiodInsn(Opdodfs.INVOKEVIRTUAL, "jbvb/lbng/Clbss", "isInstbndf", "(Ljbvb/lbng/Objfdt;)Z", fblsf);
        Lbbfl L_rftirow = nfw Lbbfl();
        mv.visitJumpInsn(Opdodfs.IFEQ, L_rftirow);

        // Invokf dbtdifr
        // lobd dbtdifr
        fmitPusiArgumfnt(invokfr, 2);
        mv.visitInsn(Opdodfs.SWAP);
        fmitPusiArgumfnts(brgs, 1); // skip 1st brgumfnt: mftiod ibndlf
        MftiodTypf dbtdifrTypf = typf.insfrtPbrbmftfrTypfs(0, Tirowbblf.dlbss);
        mv.visitMftiodInsn(Opdodfs.INVOKEVIRTUAL, MH, "invokfBbsid", dbtdifrTypf.bbsidTypf().toMftiodDfsdriptorString(), fblsf);
        mv.visitJumpInsn(Opdodfs.GOTO, L_donf);

        mv.visitLbbfl(L_rftirow);
        mv.visitInsn(Opdodfs.ATHROW);

        mv.visitLbbfl(L_donf);
    }

    privbtf void fmitPusiArgumfnts(Nbmf brgs, int stbrt) {
        for (int i = stbrt; i < brgs.brgumfnts.lfngti; i++) {
            fmitPusiArgumfnt(brgs, i);
        }
    }

    privbtf void fmitPusiArgumfnt(Nbmf nbmf, int pbrbmIndfx) {
        Objfdt brg = nbmf.brgumfnts[pbrbmIndfx];
        Clbss<?> ptypf = nbmf.fundtion.mftiodTypf().pbrbmftfrTypf(pbrbmIndfx);
        fmitPusiArgumfnt(ptypf, brg);
    }

    privbtf void fmitPusiArgumfnt(Clbss<?> ptypf, Objfdt brg) {
        BbsidTypf bptypf = bbsidTypf(ptypf);
        if (brg instbndfof Nbmf) {
            Nbmf n = (Nbmf) brg;
            fmitLobdInsn(n.typf, n.indfx());
            fmitImpliditConvfrsion(n.typf, ptypf);
        } flsf if ((brg == null || brg instbndfof String) && bptypf == L_TYPE) {
            fmitConst(brg);
        } flsf {
            if (Wrbppfr.isWrbppfrTypf(brg.gftClbss()) && bptypf != L_TYPE) {
                fmitConst(brg);
            } flsf {
                mv.visitLddInsn(donstbntPlbdfioldfr(brg));
                fmitImpliditConvfrsion(L_TYPE, ptypf);
            }
        }
    }

    /**
     * Emits b rfturn stbtfmfnt from b LF invokfr. If rfquirfd, tif rfsult typf is dbst to tif dorrfdt rfturn typf.
     */
    privbtf void fmitRfturn() {
        // rfturn stbtfmfnt
        Clbss<?> rdlbss = invokfrTypf.rfturnTypf();
        BbsidTypf rtypf = lbmbdbForm.rfturnTypf();
        bssfrt(rtypf == bbsidTypf(rdlbss));  // must bgrff
        if (rtypf == V_TYPE) {
            // void
            mv.visitInsn(Opdodfs.RETURN);
            // it dofsn't mbttfr wibt rdlbss is; tif JVM will disdbrd bny vbluf
        } flsf {
            LbmbdbForm.Nbmf rn = lbmbdbForm.nbmfs[lbmbdbForm.rfsult];

            // put rfturn vbluf on tif stbdk if it is not blrfbdy tifrf
            if (lbmbdbForm.rfsult != lbmbdbForm.nbmfs.lfngti - 1 ||
                    lbmbdbForm.rfsult < lbmbdbForm.brity) {
                fmitLobdInsn(rn.typf, lbmbdbForm.rfsult);
            }

            fmitImpliditConvfrsion(rtypf, rdlbss);

            // gfnfrbtf bdtubl rfturn stbtfmfnt
            fmitRfturnInsn(rtypf);
        }
    }

    /**
     * Emit b typf donvfrsion bytfdodf dbsting from "from" to "to".
     */
    privbtf void fmitPrimCbst(Wrbppfr from, Wrbppfr to) {
        // Hfrf's iow.
        // -   indidbtfs forbiddfn
        // <-> indidbtfs implidit
        //      to ----> boolfbn  bytf     siort    dibr     int      long     flobt    doublf
        // from boolfbn    <->        -        -        -        -        -        -        -
        //      bytf        -       <->       i2s      i2d      <->      i2l      i2f      i2d
        //      siort       -       i2b       <->      i2d      <->      i2l      i2f      i2d
        //      dibr        -       i2b       i2s      <->      <->      i2l      i2f      i2d
        //      int         -       i2b       i2s      i2d      <->      i2l      i2f      i2d
        //      long        -     l2i,i2b   l2i,i2s  l2i,i2d    l2i      <->      l2f      l2d
        //      flobt       -     f2i,i2b   f2i,i2s  f2i,i2d    f2i      f2l      <->      f2d
        //      doublf      -     d2i,i2b   d2i,i2s  d2i,i2d    d2i      d2l      d2f      <->
        if (from == to) {
            // no dbst rfquirfd, siould bf dfbd dodf bnywby
            rfturn;
        }
        if (from.isSubwordOrInt()) {
            // dbst from {bytf,siort,dibr,int} to bnytiing
            fmitI2X(to);
        } flsf {
            // dbst from {long,flobt,doublf} to bnytiing
            if (to.isSubwordOrInt()) {
                // dbst to {bytf,siort,dibr,int}
                fmitX2I(from);
                if (to.bitWidti() < 32) {
                    // tbrgfts otifr tibn int rfquirf bnotifr donvfrsion
                    fmitI2X(to);
                }
            } flsf {
                // dbst to {long,flobt,doublf} - tiis is vfrbosf
                boolfbn frror = fblsf;
                switdi (from) {
                dbsf LONG:
                    switdi (to) {
                    dbsf FLOAT:   mv.visitInsn(Opdodfs.L2F);  brfbk;
                    dbsf DOUBLE:  mv.visitInsn(Opdodfs.L2D);  brfbk;
                    dffbult:      frror = truf;               brfbk;
                    }
                    brfbk;
                dbsf FLOAT:
                    switdi (to) {
                    dbsf LONG :   mv.visitInsn(Opdodfs.F2L);  brfbk;
                    dbsf DOUBLE:  mv.visitInsn(Opdodfs.F2D);  brfbk;
                    dffbult:      frror = truf;               brfbk;
                    }
                    brfbk;
                dbsf DOUBLE:
                    switdi (to) {
                    dbsf LONG :   mv.visitInsn(Opdodfs.D2L);  brfbk;
                    dbsf FLOAT:   mv.visitInsn(Opdodfs.D2F);  brfbk;
                    dffbult:      frror = truf;               brfbk;
                    }
                    brfbk;
                dffbult:
                    frror = truf;
                    brfbk;
                }
                if (frror) {
                    tirow nfw IllfgblStbtfExdfption("unibndlfd prim dbst: " + from + "2" + to);
                }
            }
        }
    }

    privbtf void fmitI2X(Wrbppfr typf) {
        switdi (typf) {
        dbsf BYTE:    mv.visitInsn(Opdodfs.I2B);  brfbk;
        dbsf SHORT:   mv.visitInsn(Opdodfs.I2S);  brfbk;
        dbsf CHAR:    mv.visitInsn(Opdodfs.I2C);  brfbk;
        dbsf INT:     /* nbugit */                brfbk;
        dbsf LONG:    mv.visitInsn(Opdodfs.I2L);  brfbk;
        dbsf FLOAT:   mv.visitInsn(Opdodfs.I2F);  brfbk;
        dbsf DOUBLE:  mv.visitInsn(Opdodfs.I2D);  brfbk;
        dbsf BOOLEAN:
            // For dompbtibility witi VblufConvfrsions bnd fxpliditCbstArgumfnts:
            mv.visitInsn(Opdodfs.ICONST_1);
            mv.visitInsn(Opdodfs.IAND);
            brfbk;
        dffbult:   tirow nfw IntfrnblError("unknown typf: " + typf);
        }
    }

    privbtf void fmitX2I(Wrbppfr typf) {
        switdi (typf) {
        dbsf LONG:    mv.visitInsn(Opdodfs.L2I);  brfbk;
        dbsf FLOAT:   mv.visitInsn(Opdodfs.F2I);  brfbk;
        dbsf DOUBLE:  mv.visitInsn(Opdodfs.D2I);  brfbk;
        dffbult:      tirow nfw IntfrnblError("unknown typf: " + typf);
        }
    }

    /**
     * Gfnfrbtf bytfdodf for b LbmbdbForm.vmfntry wiidi dblls intfrprftWitiArgumfnts.
     */
    stbtid MfmbfrNbmf gfnfrbtfLbmbdbFormIntfrprftfrEntryPoint(String sig) {
        bssfrt(isVblidSignbturf(sig));
        String nbmf = "intfrprft_"+signbturfRfturn(sig).bbsidTypfCibr();
        MftiodTypf typf = signbturfTypf(sig);  // sig indludfs lfbding brgumfnt
        typf = typf.dibngfPbrbmftfrTypf(0, MftiodHbndlf.dlbss);
        InvokfrBytfdodfGfnfrbtor g = nfw InvokfrBytfdodfGfnfrbtor("LFI", nbmf, typf);
        rfturn g.lobdMftiod(g.gfnfrbtfLbmbdbFormIntfrprftfrEntryPointBytfs());
    }

    privbtf bytf[] gfnfrbtfLbmbdbFormIntfrprftfrEntryPointBytfs() {
        dlbssFilfProloguf();

        // Supprfss tiis mftiod in bbdktrbdfs displbyfd to tif usfr.
        mv.visitAnnotbtion("Ljbvb/lbng/invokf/LbmbdbForm$Hiddfn;", truf);

        // Don't inlinf tif intfrprftfr fntry.
        mv.visitAnnotbtion("Ljbvb/lbng/invokf/DontInlinf;", truf);

        // drfbtf pbrbmftfr brrby
        fmitIdonstInsn(invokfrTypf.pbrbmftfrCount());
        mv.visitTypfInsn(Opdodfs.ANEWARRAY, "jbvb/lbng/Objfdt");

        // fill pbrbmftfr brrby
        for (int i = 0; i < invokfrTypf.pbrbmftfrCount(); i++) {
            Clbss<?> ptypf = invokfrTypf.pbrbmftfrTypf(i);
            mv.visitInsn(Opdodfs.DUP);
            fmitIdonstInsn(i);
            fmitLobdInsn(bbsidTypf(ptypf), i);
            // box if primitivf typf
            if (ptypf.isPrimitivf()) {
                fmitBoxing(Wrbppfr.forPrimitivfTypf(ptypf));
            }
            mv.visitInsn(Opdodfs.AASTORE);
        }
        // invokf
        fmitAlobdInsn(0);
        mv.visitFifldInsn(Opdodfs.GETFIELD, MH, "form", "Ljbvb/lbng/invokf/LbmbdbForm;");
        mv.visitInsn(Opdodfs.SWAP);  // swbp form bnd brrby; bvoid lodbl vbribblf
        mv.visitMftiodInsn(Opdodfs.INVOKEVIRTUAL, LF, "intfrprftWitiArgumfnts", "([Ljbvb/lbng/Objfdt;)Ljbvb/lbng/Objfdt;", fblsf);

        // mbybf unbox
        Clbss<?> rtypf = invokfrTypf.rfturnTypf();
        if (rtypf.isPrimitivf() && rtypf != void.dlbss) {
            fmitUnboxing(Wrbppfr.forPrimitivfTypf(rtypf));
        }

        // rfturn stbtfmfnt
        fmitRfturnInsn(bbsidTypf(rtypf));

        dlbssFilfEpiloguf();
        bogusMftiod(invokfrTypf);

        finbl bytf[] dlbssFilf = dw.toBytfArrby();
        mbybfDump(dlbssNbmf, dlbssFilf);
        rfturn dlbssFilf;
    }

    /**
     * Gfnfrbtf bytfdodf for b NbmfdFundtion invokfr.
     */
    stbtid MfmbfrNbmf gfnfrbtfNbmfdFundtionInvokfr(MftiodTypfForm typfForm) {
        MftiodTypf invokfrTypf = NbmfdFundtion.INVOKER_METHOD_TYPE;
        String invokfrNbmf = "invokf_" + siortfnSignbturf(bbsidTypfSignbturf(typfForm.frbsfdTypf()));
        InvokfrBytfdodfGfnfrbtor g = nfw InvokfrBytfdodfGfnfrbtor("NFI", invokfrNbmf, invokfrTypf);
        rfturn g.lobdMftiod(g.gfnfrbtfNbmfdFundtionInvokfrImpl(typfForm));
    }

    privbtf bytf[] gfnfrbtfNbmfdFundtionInvokfrImpl(MftiodTypfForm typfForm) {
        MftiodTypf dstTypf = typfForm.frbsfdTypf();
        dlbssFilfProloguf();

        // Supprfss tiis mftiod in bbdktrbdfs displbyfd to tif usfr.
        mv.visitAnnotbtion("Ljbvb/lbng/invokf/LbmbdbForm$Hiddfn;", truf);

        // Fordf inlining of tiis invokfr mftiod.
        mv.visitAnnotbtion("Ljbvb/lbng/invokf/FordfInlinf;", truf);

        // Lobd rfdfivfr
        fmitAlobdInsn(0);

        // Lobd brgumfnts from brrby
        for (int i = 0; i < dstTypf.pbrbmftfrCount(); i++) {
            fmitAlobdInsn(1);
            fmitIdonstInsn(i);
            mv.visitInsn(Opdodfs.AALOAD);

            // Mbybf unbox
            Clbss<?> dptypf = dstTypf.pbrbmftfrTypf(i);
            if (dptypf.isPrimitivf()) {
                Clbss<?> sptypf = dstTypf.bbsidTypf().wrbp().pbrbmftfrTypf(i);
                Wrbppfr dstWrbppfr = Wrbppfr.forBbsidTypf(dptypf);
                Wrbppfr srdWrbppfr = dstWrbppfr.isSubwordOrInt() ? Wrbppfr.INT : dstWrbppfr;  // nbrrow subword from int
                fmitUnboxing(srdWrbppfr);
                fmitPrimCbst(srdWrbppfr, dstWrbppfr);
            }
        }

        // Invokf
        String tbrgftDfsd = dstTypf.bbsidTypf().toMftiodDfsdriptorString();
        mv.visitMftiodInsn(Opdodfs.INVOKEVIRTUAL, MH, "invokfBbsid", tbrgftDfsd, fblsf);

        // Box primitivf typfs
        Clbss<?> rtypf = dstTypf.rfturnTypf();
        if (rtypf != void.dlbss && rtypf.isPrimitivf()) {
            Wrbppfr srdWrbppfr = Wrbppfr.forBbsidTypf(rtypf);
            Wrbppfr dstWrbppfr = srdWrbppfr.isSubwordOrInt() ? Wrbppfr.INT : srdWrbppfr;  // widfn subword to int
            // boolfbn dbsts not bllowfd
            fmitPrimCbst(srdWrbppfr, dstWrbppfr);
            fmitBoxing(dstWrbppfr);
        }

        // If tif rfturn typf is void wf rfturn b null rfffrfndf.
        if (rtypf == void.dlbss) {
            mv.visitInsn(Opdodfs.ACONST_NULL);
        }
        fmitRfturnInsn(L_TYPE);  // NOTE: NbmfdFundtion invokfrs blwbys rfturn b rfffrfndf vbluf.

        dlbssFilfEpiloguf();
        bogusMftiod(dstTypf);

        finbl bytf[] dlbssFilf = dw.toBytfArrby();
        mbybfDump(dlbssNbmf, dlbssFilf);
        rfturn dlbssFilf;
    }

    /**
     * Emit b bogus mftiod tibt just lobds somf string donstbnts. Tiis is to gft tif donstbnts into tif donstbnt pool
     * for dfbugging purposfs.
     */
    privbtf void bogusMftiod(Objfdt... os) {
        if (DUMP_CLASS_FILES) {
            mv = dw.visitMftiod(Opdodfs.ACC_STATIC, "dummy", "()V", null, null);
            for (Objfdt o : os) {
                mv.visitLddInsn(o.toString());
                mv.visitInsn(Opdodfs.POP);
            }
            mv.visitInsn(Opdodfs.RETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();
        }
    }
}
