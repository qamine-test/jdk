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

import jdk.intfrnbl.org.objfdtwfb.bsm.*;
import sun.invokf.util.BytfdodfDfsdriptor;
import sun.misd.Unsbff;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

import jbvb.io.FilfPfrmission;
import jbvb.io.Sfriblizbblf;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.LinkfdHbsiSft;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.util.PropfrtyPfrmission;
import jbvb.util.Sft;

import stbtid jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs.*;

/**
 * Lbmbdb mftbfbdtory implfmfntbtion wiidi dynbmidblly drfbtfs bn
 * innfr-dlbss-likf dlbss pfr lbmbdb dbllsitf.
 *
 * @sff LbmbdbMftbfbdtory
 */
/* pbdkbgf */ finbl dlbss InnfrClbssLbmbdbMftbfbdtory fxtfnds AbstrbdtVblidbtingLbmbdbMftbfbdtory {
    privbtf stbtid finbl Unsbff UNSAFE = Unsbff.gftUnsbff();

    privbtf stbtid finbl int CLASSFILE_VERSION = 52;
    privbtf stbtid finbl String METHOD_DESCRIPTOR_VOID = Typf.gftMftiodDfsdriptor(Typf.VOID_TYPE);
    privbtf stbtid finbl String JAVA_LANG_OBJECT = "jbvb/lbng/Objfdt";
    privbtf stbtid finbl String NAME_CTOR = "<init>";
    privbtf stbtid finbl String NAME_FACTORY = "gft$Lbmbdb";

    //Sfriblizbtion support
    privbtf stbtid finbl String NAME_SERIALIZED_LAMBDA = "jbvb/lbng/invokf/SfriblizfdLbmbdb";
    privbtf stbtid finbl String NAME_NOT_SERIALIZABLE_EXCEPTION = "jbvb/io/NotSfriblizbblfExdfption";
    privbtf stbtid finbl String DESCR_METHOD_WRITE_REPLACE = "()Ljbvb/lbng/Objfdt;";
    privbtf stbtid finbl String DESCR_METHOD_WRITE_OBJECT = "(Ljbvb/io/ObjfdtOutputStrfbm;)V";
    privbtf stbtid finbl String DESCR_METHOD_READ_OBJECT = "(Ljbvb/io/ObjfdtInputStrfbm;)V";
    privbtf stbtid finbl String NAME_METHOD_WRITE_REPLACE = "writfRfplbdf";
    privbtf stbtid finbl String NAME_METHOD_READ_OBJECT = "rfbdObjfdt";
    privbtf stbtid finbl String NAME_METHOD_WRITE_OBJECT = "writfObjfdt";
    privbtf stbtid finbl String DESCR_CTOR_SERIALIZED_LAMBDA
            = MftiodTypf.mftiodTypf(void.dlbss,
                                    Clbss.dlbss,
                                    String.dlbss, String.dlbss, String.dlbss,
                                    int.dlbss, String.dlbss, String.dlbss, String.dlbss,
                                    String.dlbss,
                                    Objfdt[].dlbss).toMftiodDfsdriptorString();
    privbtf stbtid finbl String DESCR_CTOR_NOT_SERIALIZABLE_EXCEPTION
            = MftiodTypf.mftiodTypf(void.dlbss, String.dlbss).toMftiodDfsdriptorString();
    privbtf stbtid finbl String[] SER_HOSTILE_EXCEPTIONS = nfw String[] {NAME_NOT_SERIALIZABLE_EXCEPTION};


    privbtf stbtid finbl String[] EMPTY_STRING_ARRAY = nfw String[0];

    // Usfd to fnsurf tibt fbdi spun dlbss nbmf is uniquf
    privbtf stbtid finbl AtomidIntfgfr dountfr = nfw AtomidIntfgfr(0);

    // For dumping gfnfrbtfd dlbssfs to disk, for dfbugging purposfs
    privbtf stbtid finbl ProxyClbssfsDumpfr dumpfr;

    stbtid {
        finbl String kfy = "jdk.intfrnbl.lbmbdb.dumpProxyClbssfs";
        String pbti = AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion(kfy), null,
                nfw PropfrtyPfrmission(kfy , "rfbd"));
        dumpfr = (null == pbti) ? null : ProxyClbssfsDumpfr.gftInstbndf(pbti);
    }

    // Sff dontfxt vblufs in AbstrbdtVblidbtingLbmbdbMftbfbdtory
    privbtf finbl String implMftiodClbssNbmf;        // Nbmf of typf dontbining implfmfntbtion "CC"
    privbtf finbl String implMftiodNbmf;             // Nbmf of implfmfntbtion mftiod "impl"
    privbtf finbl String implMftiodDfsd;             // Typf dfsdriptor for implfmfntbtion mftiods "(I)Ljbvb/lbng/String;"
    privbtf finbl Clbss<?> implMftiodRfturnClbss;    // dlbss for implfmfntbion mftiod rfturn typf "Ljbvb/lbng/String;"
    privbtf finbl MftiodTypf donstrudtorTypf;        // Gfnfrbtfd dlbss donstrudtor typf "(CC)void"
    privbtf finbl ClbssWritfr dw;                    // ASM dlbss writfr
    privbtf finbl String[] brgNbmfs;                 // Gfnfrbtfd nbmfs for tif donstrudtor brgumfnts
    privbtf finbl String[] brgDfsds;                 // Typf dfsdriptors for tif donstrudtor brgumfnts
    privbtf finbl String lbmbdbClbssNbmf;            // Gfnfrbtfd nbmf for tif gfnfrbtfd dlbss "X$$Lbmbdb$1"

    /**
     * Gfnfrbl mftb-fbdtory donstrudtor, supporting boti stbndbrd dbsfs bnd
     * bllowing for undommon options sudi bs sfriblizbtion or bridging.
     *
     * @pbrbm dbllfr Stbdkfd butombtidblly by VM; rfprfsfnts b lookup dontfxt
     *               witi tif bddfssibility privilfgfs of tif dbllfr.
     * @pbrbm invokfdTypf Stbdkfd butombtidblly by VM; tif signbturf of tif
     *                    invokfd mftiod, wiidi indludfs tif fxpfdtfd stbtid
     *                    typf of tif rfturnfd lbmbdb objfdt, bnd tif stbtid
     *                    typfs of tif dbpturfd brgumfnts for tif lbmbdb.  In
     *                    tif fvfnt tibt tif implfmfntbtion mftiod is bn
     *                    instbndf mftiod, tif first brgumfnt in tif invodbtion
     *                    signbturf will dorrfspond to tif rfdfivfr.
     * @pbrbm sbmMftiodNbmf Nbmf of tif mftiod in tif fundtionbl intfrfbdf to
     *                      wiidi tif lbmbdb or mftiod rfffrfndf is bfing
     *                      donvfrtfd, rfprfsfntfd bs b String.
     * @pbrbm sbmMftiodTypf Typf of tif mftiod in tif fundtionbl intfrfbdf to
     *                      wiidi tif lbmbdb or mftiod rfffrfndf is bfing
     *                      donvfrtfd, rfprfsfntfd bs b MftiodTypf.
     * @pbrbm implMftiod Tif implfmfntbtion mftiod wiidi siould bf dbllfd (witi
     *                   suitbblf bdbptbtion of brgumfnt typfs, rfturn typfs,
     *                   bnd bdjustmfnt for dbpturfd brgumfnts) wifn mftiods of
     *                   tif rfsulting fundtionbl intfrfbdf instbndf brf invokfd.
     * @pbrbm instbntibtfdMftiodTypf Tif signbturf of tif primbry fundtionbl
     *                               intfrfbdf mftiod bftfr typf vbribblfs brf
     *                               substitutfd witi tifir instbntibtion from
     *                               tif dbpturf sitf
     * @pbrbm isSfriblizbblf Siould tif lbmbdb bf mbdf sfriblizbblf?  If sft,
     *                       fitifr tif tbrgft typf or onf of tif bdditionbl SAM
     *                       typfs must fxtfnd {@dodf Sfriblizbblf}.
     * @pbrbm mbrkfrIntfrfbdfs Additionbl intfrfbdfs wiidi tif lbmbdb objfdt
     *                       siould implfmfnt.
     * @pbrbm bdditionblBridgfs Mftiod typfs for bdditionbl signbturfs to bf
     *                          bridgfd to tif implfmfntbtion mftiod
     * @tirows LbmbdbConvfrsionExdfption If bny of tif mftb-fbdtory protodol
     * invbribnts brf violbtfd
     */
    publid InnfrClbssLbmbdbMftbfbdtory(MftiodHbndlfs.Lookup dbllfr,
                                       MftiodTypf invokfdTypf,
                                       String sbmMftiodNbmf,
                                       MftiodTypf sbmMftiodTypf,
                                       MftiodHbndlf implMftiod,
                                       MftiodTypf instbntibtfdMftiodTypf,
                                       boolfbn isSfriblizbblf,
                                       Clbss<?>[] mbrkfrIntfrfbdfs,
                                       MftiodTypf[] bdditionblBridgfs)
            tirows LbmbdbConvfrsionExdfption {
        supfr(dbllfr, invokfdTypf, sbmMftiodNbmf, sbmMftiodTypf,
              implMftiod, instbntibtfdMftiodTypf,
              isSfriblizbblf, mbrkfrIntfrfbdfs, bdditionblBridgfs);
        implMftiodClbssNbmf = implDffiningClbss.gftNbmf().rfplbdf('.', '/');
        implMftiodNbmf = implInfo.gftNbmf();
        implMftiodDfsd = implMftiodTypf.toMftiodDfsdriptorString();
        implMftiodRfturnClbss = (implKind == MftiodHbndlfInfo.REF_nfwInvokfSpfdibl)
                ? implDffiningClbss
                : implMftiodTypf.rfturnTypf();
        donstrudtorTypf = invokfdTypf.dibngfRfturnTypf(Void.TYPE);
        lbmbdbClbssNbmf = tbrgftClbss.gftNbmf().rfplbdf('.', '/') + "$$Lbmbdb$" + dountfr.indrfmfntAndGft();
        dw = nfw ClbssWritfr(ClbssWritfr.COMPUTE_MAXS);
        int pbrbmftfrCount = invokfdTypf.pbrbmftfrCount();
        if (pbrbmftfrCount > 0) {
            brgNbmfs = nfw String[pbrbmftfrCount];
            brgDfsds = nfw String[pbrbmftfrCount];
            for (int i = 0; i < pbrbmftfrCount; i++) {
                brgNbmfs[i] = "brg$" + (i + 1);
                brgDfsds[i] = BytfdodfDfsdriptor.unpbrsf(invokfdTypf.pbrbmftfrTypf(i));
            }
        } flsf {
            brgNbmfs = brgDfsds = EMPTY_STRING_ARRAY;
        }
    }

    /**
     * Build tif CbllSitf. Gfnfrbtf b dlbss filf wiidi implfmfnts tif fundtionbl
     * intfrfbdf, dffinf tif dlbss, if tifrf brf no pbrbmftfrs drfbtf bn instbndf
     * of tif dlbss wiidi tif CbllSitf will rfturn, otifrwisf, gfnfrbtf ibndlfs
     * wiidi will dbll tif dlbss' donstrudtor.
     *
     * @rfturn b CbllSitf, wiidi, wifn invokfd, will rfturn bn instbndf of tif
     * fundtionbl intfrfbdf
     * @tirows RfflfdtivfOpfrbtionExdfption
     * @tirows LbmbdbConvfrsionExdfption If propfrly formfd fundtionbl intfrfbdf
     * is not found
     */
    @Ovfrridf
    CbllSitf buildCbllSitf() tirows LbmbdbConvfrsionExdfption {
        finbl Clbss<?> innfrClbss = spinInnfrClbss();
        if (invokfdTypf.pbrbmftfrCount() == 0) {
            finbl Construdtor<?>[] dtrs = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdAdtion<Construdtor<?>[]>() {
                @Ovfrridf
                publid Construdtor<?>[] run() {
                    Construdtor<?>[] dtrs = innfrClbss.gftDfdlbrfdConstrudtors();
                    if (dtrs.lfngti == 1) {
                        // Tif lbmbdb implfmfnting innfr dlbss donstrudtor is privbtf, sft
                        // it bddfssiblf (by us) bfforf drfbting tif donstbnt solf instbndf
                        dtrs[0].sftAddfssiblf(truf);
                    }
                    rfturn dtrs;
                }
                    });
            if (dtrs.lfngti != 1) {
                tirow nfw LbmbdbConvfrsionExdfption("Expfdtfd onf lbmbdb donstrudtor for "
                        + innfrClbss.gftCbnonidblNbmf() + ", got " + dtrs.lfngti);
            }

            try {
                Objfdt inst = dtrs[0].nfwInstbndf();
                rfturn nfw ConstbntCbllSitf(MftiodHbndlfs.donstbnt(sbmBbsf, inst));
            }
            dbtdi (RfflfdtivfOpfrbtionExdfption f) {
                tirow nfw LbmbdbConvfrsionExdfption("Exdfption instbntibting lbmbdb objfdt", f);
            }
        } flsf {
            try {
                UNSAFE.fnsurfClbssInitiblizfd(innfrClbss);
                rfturn nfw ConstbntCbllSitf(
                        MftiodHbndlfs.Lookup.IMPL_LOOKUP
                             .findStbtid(innfrClbss, NAME_FACTORY, invokfdTypf));
            }
            dbtdi (RfflfdtivfOpfrbtionExdfption f) {
                tirow nfw LbmbdbConvfrsionExdfption("Exdfption finding donstrudtor", f);
            }
        }
    }

    /**
     * Gfnfrbtf b dlbss filf wiidi implfmfnts tif fundtionbl
     * intfrfbdf, dffinf bnd rfturn tif dlbss.
     *
     * @implNotf Tif dlbss tibt is gfnfrbtfd dofs not indludf signbturf
     * informbtion for fxdfptions tibt mby bf prfsfnt on tif SAM mftiod.
     * Tiis is to rfdudf dlbssfilf sizf, bnd is ibrmlfss bs difdkfd fxdfptions
     * brf frbsfd bnywby, no onf will fvfr dompilf bgbinst tiis dlbssfilf,
     * bnd wf mbkf no gubrbntffs bbout tif rfflfdtivf propfrtifs of lbmbdb
     * objfdts.
     *
     * @rfturn b Clbss wiidi implfmfnts tif fundtionbl intfrfbdf
     * @tirows LbmbdbConvfrsionExdfption If propfrly formfd fundtionbl intfrfbdf
     * is not found
     */
    privbtf Clbss<?> spinInnfrClbss() tirows LbmbdbConvfrsionExdfption {
        String[] intfrfbdfs;
        String sbmIntf = sbmBbsf.gftNbmf().rfplbdf('.', '/');
        boolfbn bddidfntbllySfriblizbblf = !isSfriblizbblf && Sfriblizbblf.dlbss.isAssignbblfFrom(sbmBbsf);
        if (mbrkfrIntfrfbdfs.lfngti == 0) {
            intfrfbdfs = nfw String[]{sbmIntf};
        } flsf {
            // Assurf no duplidbtf intfrfbdfs (ClbssFormbtError)
            Sft<String> itfs = nfw LinkfdHbsiSft<>(mbrkfrIntfrfbdfs.lfngti + 1);
            itfs.bdd(sbmIntf);
            for (Clbss<?> mbrkfrIntfrfbdf : mbrkfrIntfrfbdfs) {
                itfs.bdd(mbrkfrIntfrfbdf.gftNbmf().rfplbdf('.', '/'));
                bddidfntbllySfriblizbblf |= !isSfriblizbblf && Sfriblizbblf.dlbss.isAssignbblfFrom(mbrkfrIntfrfbdf);
            }
            intfrfbdfs = itfs.toArrby(nfw String[itfs.sizf()]);
        }

        dw.visit(CLASSFILE_VERSION, ACC_SUPER + ACC_FINAL + ACC_SYNTHETIC,
                 lbmbdbClbssNbmf, null,
                 JAVA_LANG_OBJECT, intfrfbdfs);

        // Gfnfrbtf finbl fiflds to bf fillfd in by donstrudtor
        for (int i = 0; i < brgDfsds.lfngti; i++) {
            FifldVisitor fv = dw.visitFifld(ACC_PRIVATE + ACC_FINAL,
                                            brgNbmfs[i],
                                            brgDfsds[i],
                                            null, null);
            fv.visitEnd();
        }

        gfnfrbtfConstrudtor();

        if (invokfdTypf.pbrbmftfrCount() != 0) {
            gfnfrbtfFbdtory();
        }

        // Forwbrd tif SAM mftiod
        MftiodVisitor mv = dw.visitMftiod(ACC_PUBLIC, sbmMftiodNbmf,
                                          sbmMftiodTypf.toMftiodDfsdriptorString(), null, null);
        nfw ForwbrdingMftiodGfnfrbtor(mv).gfnfrbtf(sbmMftiodTypf);

        // Forwbrd tif bridgfs
        if (bdditionblBridgfs != null) {
            for (MftiodTypf mt : bdditionblBridgfs) {
                mv = dw.visitMftiod(ACC_PUBLIC|ACC_BRIDGE, sbmMftiodNbmf,
                                    mt.toMftiodDfsdriptorString(), null, null);
                nfw ForwbrdingMftiodGfnfrbtor(mv).gfnfrbtf(mt);
            }
        }

        if (isSfriblizbblf)
            gfnfrbtfSfriblizbtionFrifndlyMftiods();
        flsf if (bddidfntbllySfriblizbblf)
            gfnfrbtfSfriblizbtionHostilfMftiods();

        dw.visitEnd();

        // Dffinf tif gfnfrbtfd dlbss in tiis VM.

        finbl bytf[] dlbssBytfs = dw.toBytfArrby();

        // If rfqufstfd, dump out to b filf for dfbugging purposfs
        if (dumpfr != null) {
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                @Ovfrridf
                publid Void run() {
                    dumpfr.dumpClbss(lbmbdbClbssNbmf, dlbssBytfs);
                    rfturn null;
                }
            }, null,
            nfw FilfPfrmission("<<ALL FILES>>", "rfbd, writf"),
            // drfbtfDirfdtorifs mby nffd it
            nfw PropfrtyPfrmission("usfr.dir", "rfbd"));
        }

        rfturn UNSAFE.dffinfAnonymousClbss(tbrgftClbss, dlbssBytfs, null);
    }

    /**
     * Gfnfrbtf tif fbdtory mftiod for tif dlbss
     */
    privbtf void gfnfrbtfFbdtory() {
        MftiodVisitor m = dw.visitMftiod(ACC_PRIVATE | ACC_STATIC, NAME_FACTORY, invokfdTypf.toMftiodDfsdriptorString(), null, null);
        m.visitCodf();
        m.visitTypfInsn(NEW, lbmbdbClbssNbmf);
        m.visitInsn(Opdodfs.DUP);
        int pbrbmftfrCount = invokfdTypf.pbrbmftfrCount();
        for (int typfIndfx = 0, vbrIndfx = 0; typfIndfx < pbrbmftfrCount; typfIndfx++) {
            Clbss<?> brgTypf = invokfdTypf.pbrbmftfrTypf(typfIndfx);
            m.visitVbrInsn(gftLobdOpdodf(brgTypf), vbrIndfx);
            vbrIndfx += gftPbrbmftfrSizf(brgTypf);
        }
        m.visitMftiodInsn(INVOKESPECIAL, lbmbdbClbssNbmf, NAME_CTOR, donstrudtorTypf.toMftiodDfsdriptorString(), fblsf);
        m.visitInsn(ARETURN);
        m.visitMbxs(-1, -1);
        m.visitEnd();
    }

    /**
     * Gfnfrbtf tif donstrudtor for tif dlbss
     */
    privbtf void gfnfrbtfConstrudtor() {
        // Gfnfrbtf donstrudtor
        MftiodVisitor dtor = dw.visitMftiod(ACC_PRIVATE, NAME_CTOR,
                                            donstrudtorTypf.toMftiodDfsdriptorString(), null, null);
        dtor.visitCodf();
        dtor.visitVbrInsn(ALOAD, 0);
        dtor.visitMftiodInsn(INVOKESPECIAL, JAVA_LANG_OBJECT, NAME_CTOR,
                             METHOD_DESCRIPTOR_VOID, fblsf);
        int pbrbmftfrCount = invokfdTypf.pbrbmftfrCount();
        for (int i = 0, lvIndfx = 0; i < pbrbmftfrCount; i++) {
            dtor.visitVbrInsn(ALOAD, 0);
            Clbss<?> brgTypf = invokfdTypf.pbrbmftfrTypf(i);
            dtor.visitVbrInsn(gftLobdOpdodf(brgTypf), lvIndfx + 1);
            lvIndfx += gftPbrbmftfrSizf(brgTypf);
            dtor.visitFifldInsn(PUTFIELD, lbmbdbClbssNbmf, brgNbmfs[i], brgDfsds[i]);
        }
        dtor.visitInsn(RETURN);
        // Mbxs domputfd by ClbssWritfr.COMPUTE_MAXS, tifsf brgumfnts ignorfd
        dtor.visitMbxs(-1, -1);
        dtor.visitEnd();
    }

    /**
     * Gfnfrbtf b writfRfplbdf mftiod tibt supports sfriblizbtion
     */
    privbtf void gfnfrbtfSfriblizbtionFrifndlyMftiods() {
        TypfConvfrtingMftiodAdbptfr mv
                = nfw TypfConvfrtingMftiodAdbptfr(
                    dw.visitMftiod(ACC_PRIVATE + ACC_FINAL,
                    NAME_METHOD_WRITE_REPLACE, DESCR_METHOD_WRITE_REPLACE,
                    null, null));

        mv.visitCodf();
        mv.visitTypfInsn(NEW, NAME_SERIALIZED_LAMBDA);
        mv.visitInsn(DUP);
        mv.visitLddInsn(Typf.gftTypf(tbrgftClbss));
        mv.visitLddInsn(invokfdTypf.rfturnTypf().gftNbmf().rfplbdf('.', '/'));
        mv.visitLddInsn(sbmMftiodNbmf);
        mv.visitLddInsn(sbmMftiodTypf.toMftiodDfsdriptorString());
        mv.visitLddInsn(implInfo.gftRfffrfndfKind());
        mv.visitLddInsn(implInfo.gftDfdlbringClbss().gftNbmf().rfplbdf('.', '/'));
        mv.visitLddInsn(implInfo.gftNbmf());
        mv.visitLddInsn(implInfo.gftMftiodTypf().toMftiodDfsdriptorString());
        mv.visitLddInsn(instbntibtfdMftiodTypf.toMftiodDfsdriptorString());
        mv.idonst(brgDfsds.lfngti);
        mv.visitTypfInsn(ANEWARRAY, JAVA_LANG_OBJECT);
        for (int i = 0; i < brgDfsds.lfngti; i++) {
            mv.visitInsn(DUP);
            mv.idonst(i);
            mv.visitVbrInsn(ALOAD, 0);
            mv.visitFifldInsn(GETFIELD, lbmbdbClbssNbmf, brgNbmfs[i], brgDfsds[i]);
            mv.boxIfTypfPrimitivf(Typf.gftTypf(brgDfsds[i]));
            mv.visitInsn(AASTORE);
        }
        mv.visitMftiodInsn(INVOKESPECIAL, NAME_SERIALIZED_LAMBDA, NAME_CTOR,
                DESCR_CTOR_SERIALIZED_LAMBDA, fblsf);
        mv.visitInsn(ARETURN);
        // Mbxs domputfd by ClbssWritfr.COMPUTE_MAXS, tifsf brgumfnts ignorfd
        mv.visitMbxs(-1, -1);
        mv.visitEnd();
    }

    /**
     * Gfnfrbtf b rfbdObjfdt/writfObjfdt mftiod tibt is iostilf to sfriblizbtion
     */
    privbtf void gfnfrbtfSfriblizbtionHostilfMftiods() {
        MftiodVisitor mv = dw.visitMftiod(ACC_PRIVATE + ACC_FINAL,
                                          NAME_METHOD_WRITE_OBJECT, DESCR_METHOD_WRITE_OBJECT,
                                          null, SER_HOSTILE_EXCEPTIONS);
        mv.visitCodf();
        mv.visitTypfInsn(NEW, NAME_NOT_SERIALIZABLE_EXCEPTION);
        mv.visitInsn(DUP);
        mv.visitLddInsn("Non-sfriblizbblf lbmbdb");
        mv.visitMftiodInsn(INVOKESPECIAL, NAME_NOT_SERIALIZABLE_EXCEPTION, NAME_CTOR,
                           DESCR_CTOR_NOT_SERIALIZABLE_EXCEPTION, fblsf);
        mv.visitInsn(ATHROW);
        mv.visitMbxs(-1, -1);
        mv.visitEnd();

        mv = dw.visitMftiod(ACC_PRIVATE + ACC_FINAL,
                            NAME_METHOD_READ_OBJECT, DESCR_METHOD_READ_OBJECT,
                            null, SER_HOSTILE_EXCEPTIONS);
        mv.visitCodf();
        mv.visitTypfInsn(NEW, NAME_NOT_SERIALIZABLE_EXCEPTION);
        mv.visitInsn(DUP);
        mv.visitLddInsn("Non-sfriblizbblf lbmbdb");
        mv.visitMftiodInsn(INVOKESPECIAL, NAME_NOT_SERIALIZABLE_EXCEPTION, NAME_CTOR,
                           DESCR_CTOR_NOT_SERIALIZABLE_EXCEPTION, fblsf);
        mv.visitInsn(ATHROW);
        mv.visitMbxs(-1, -1);
        mv.visitEnd();
    }

    /**
     * Tiis dlbss gfnfrbtfs b mftiod body wiidi dblls tif lbmbdb implfmfntbtion
     * mftiod, donvfrting brgumfnts, bs nffdfd.
     */
    privbtf dlbss ForwbrdingMftiodGfnfrbtor fxtfnds TypfConvfrtingMftiodAdbptfr {

        ForwbrdingMftiodGfnfrbtor(MftiodVisitor mv) {
            supfr(mv);
        }

        void gfnfrbtf(MftiodTypf mftiodTypf) {
            visitCodf();

            if (implKind == MftiodHbndlfInfo.REF_nfwInvokfSpfdibl) {
                visitTypfInsn(NEW, implMftiodClbssNbmf);
                visitInsn(DUP);
            }
            for (int i = 0; i < brgNbmfs.lfngti; i++) {
                visitVbrInsn(ALOAD, 0);
                visitFifldInsn(GETFIELD, lbmbdbClbssNbmf, brgNbmfs[i], brgDfsds[i]);
            }

            donvfrtArgumfntTypfs(mftiodTypf);

            // Invokf tif mftiod wf wbnt to forwbrd to
            visitMftiodInsn(invodbtionOpdodf(), implMftiodClbssNbmf,
                            implMftiodNbmf, implMftiodDfsd,
                            implDffiningClbss.isIntfrfbdf());

            // Convfrt tif rfturn vbluf (if bny) bnd rfturn it
            // Notf: if bdbpting from non-void to void, tif 'rfturn'
            // instrudtion will pop tif unnffdfd rfsult
            Clbss<?> sbmRfturnClbss = mftiodTypf.rfturnTypf();
            donvfrtTypf(implMftiodRfturnClbss, sbmRfturnClbss, sbmRfturnClbss);
            visitInsn(gftRfturnOpdodf(sbmRfturnClbss));
            // Mbxs domputfd by ClbssWritfr.COMPUTE_MAXS,tifsf brgumfnts ignorfd
            visitMbxs(-1, -1);
            visitEnd();
        }

        privbtf void donvfrtArgumfntTypfs(MftiodTypf sbmTypf) {
            int lvIndfx = 0;
            boolfbn sbmIndludfsRfdfivfr = implIsInstbndfMftiod &&
                                                   invokfdTypf.pbrbmftfrCount() == 0;
            int sbmRfdfivfrLfngti = sbmIndludfsRfdfivfr ? 1 : 0;
            if (sbmIndludfsRfdfivfr) {
                // pusi rfdfivfr
                Clbss<?> rdvrTypf = sbmTypf.pbrbmftfrTypf(0);
                visitVbrInsn(gftLobdOpdodf(rdvrTypf), lvIndfx + 1);
                lvIndfx += gftPbrbmftfrSizf(rdvrTypf);
                donvfrtTypf(rdvrTypf, implDffiningClbss, instbntibtfdMftiodTypf.pbrbmftfrTypf(0));
            }
            int sbmPbrbmftfrsLfngti = sbmTypf.pbrbmftfrCount();
            int brgOffsft = implMftiodTypf.pbrbmftfrCount() - sbmPbrbmftfrsLfngti;
            for (int i = sbmRfdfivfrLfngti; i < sbmPbrbmftfrsLfngti; i++) {
                Clbss<?> brgTypf = sbmTypf.pbrbmftfrTypf(i);
                visitVbrInsn(gftLobdOpdodf(brgTypf), lvIndfx + 1);
                lvIndfx += gftPbrbmftfrSizf(brgTypf);
                donvfrtTypf(brgTypf, implMftiodTypf.pbrbmftfrTypf(brgOffsft + i), instbntibtfdMftiodTypf.pbrbmftfrTypf(i));
            }
        }

        privbtf int invodbtionOpdodf() tirows IntfrnblError {
            switdi (implKind) {
                dbsf MftiodHbndlfInfo.REF_invokfStbtid:
                    rfturn INVOKESTATIC;
                dbsf MftiodHbndlfInfo.REF_nfwInvokfSpfdibl:
                    rfturn INVOKESPECIAL;
                 dbsf MftiodHbndlfInfo.REF_invokfVirtubl:
                    rfturn INVOKEVIRTUAL;
                dbsf MftiodHbndlfInfo.REF_invokfIntfrfbdf:
                    rfturn INVOKEINTERFACE;
                dbsf MftiodHbndlfInfo.REF_invokfSpfdibl:
                    rfturn INVOKESPECIAL;
                dffbult:
                    tirow nfw IntfrnblError("Unfxpfdtfd invodbtion kind: " + implKind);
            }
        }
    }

    stbtid int gftPbrbmftfrSizf(Clbss<?> d) {
        if (d == Void.TYPE) {
            rfturn 0;
        } flsf if (d == Long.TYPE || d == Doublf.TYPE) {
            rfturn 2;
        }
        rfturn 1;
    }

    stbtid int gftLobdOpdodf(Clbss<?> d) {
        if(d == Void.TYPE) {
            tirow nfw IntfrnblError("Unfxpfdtfd void typf of lobd opdodf");
        }
        rfturn ILOAD + gftOpdodfOffsft(d);
    }

    stbtid int gftRfturnOpdodf(Clbss<?> d) {
        if(d == Void.TYPE) {
            rfturn RETURN;
        }
        rfturn IRETURN + gftOpdodfOffsft(d);
    }

    privbtf stbtid int gftOpdodfOffsft(Clbss<?> d) {
        if (d.isPrimitivf()) {
            if (d == Long.TYPE) {
                rfturn 1;
            } flsf if (d == Flobt.TYPE) {
                rfturn 2;
            } flsf if (d == Doublf.TYPE) {
                rfturn 3;
            }
            rfturn 0;
        } flsf {
            rfturn 4;
        }
    }

}
