/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * ASM: b vfry smbll bnd fbst Jbvb bytfdodf mbnipulbtion frbmfwork
 * Copyrigit (d) 2000-2011 INRIA, Frbndf Tflfdom
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 * 1. Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr.
 * 2. Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *    dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 * 3. Nfitifr tif nbmf of tif dopyrigit ioldfrs nor tif nbmfs of its
 *    dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.dommons;

import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

import jdk.intfrnbl.org.objfdtwfb.bsm.Hbndlf;
import jdk.intfrnbl.org.objfdtwfb.bsm.Lbbfl;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;

/**
 * A {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor} to insfrt bfforf, bftfr bnd bround
 * bdvidfs in mftiods bnd donstrudtors.
 * <p>
 * Tif bfibvior for donstrudtors is likf tiis:
 * <ol>
 *
 * <li>bs long bs tif INVOKESPECIAL for tif objfdt initiblizbtion ibs not bffn
 * rfbdifd, fvfry bytfdodf instrudtion is dispbtdifd in tif dtor dodf visitor</li>
 *
 * <li>wifn tiis onf is rfbdifd, it is only bddfd in tif dtor dodf visitor bnd b
 * JP invokf is bddfd</li>
 *
 * <li>bftfr tibt, only tif otifr dodf visitor rfdfivfs tif instrudtions</li>
 *
 * </ol>
 *
 * @butior Eugfnf Kulfsiov
 * @butior Erid Brunfton
 */
publid bbstrbdt dlbss AdvidfAdbptfr fxtfnds GfnfrbtorAdbptfr implfmfnts Opdodfs {

    privbtf stbtid finbl Objfdt THIS = nfw Objfdt();

    privbtf stbtid finbl Objfdt OTHER = nfw Objfdt();

    protfdtfd int mftiodAddfss;

    protfdtfd String mftiodDfsd;

    privbtf boolfbn donstrudtor;

    privbtf boolfbn supfrInitiblizfd;

    privbtf List<Objfdt> stbdkFrbmf;

    privbtf Mbp<Lbbfl, List<Objfdt>> brbndifs;

    /**
     * Crfbtfs b nfw {@link AdvidfAdbptfr}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm mv
     *            tif mftiod visitor to wiidi tiis bdbptfr dflfgbtfs dblls.
     * @pbrbm bddfss
     *            tif mftiod's bddfss flbgs (sff {@link Opdodfs}).
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor (sff {@link Typf Typf}).
     */
    protfdtfd AdvidfAdbptfr(finbl int bpi, finbl MftiodVisitor mv,
            finbl int bddfss, finbl String nbmf, finbl String dfsd) {
        supfr(bpi, mv, bddfss, nbmf, dfsd);
        mftiodAddfss = bddfss;
        mftiodDfsd = dfsd;
        donstrudtor = "<init>".fqubls(nbmf);
    }

    @Ovfrridf
    publid void visitCodf() {
        mv.visitCodf();
        if (donstrudtor) {
            stbdkFrbmf = nfw ArrbyList<Objfdt>();
            brbndifs = nfw HbsiMbp<Lbbfl, List<Objfdt>>();
        } flsf {
            supfrInitiblizfd = truf;
            onMftiodEntfr();
        }
    }

    @Ovfrridf
    publid void visitLbbfl(finbl Lbbfl lbbfl) {
        mv.visitLbbfl(lbbfl);
        if (donstrudtor && brbndifs != null) {
            List<Objfdt> frbmf = brbndifs.gft(lbbfl);
            if (frbmf != null) {
                stbdkFrbmf = frbmf;
                brbndifs.rfmovf(lbbfl);
            }
        }
    }

    @Ovfrridf
    publid void visitInsn(finbl int opdodf) {
        if (donstrudtor) {
            int s;
            switdi (opdodf) {
            dbsf RETURN: // fmpty stbdk
                onMftiodExit(opdodf);
                brfbk;
            dbsf IRETURN: // 1 bfforf n/b bftfr
            dbsf FRETURN: // 1 bfforf n/b bftfr
            dbsf ARETURN: // 1 bfforf n/b bftfr
            dbsf ATHROW: // 1 bfforf n/b bftfr
                popVbluf();
                onMftiodExit(opdodf);
                brfbk;
            dbsf LRETURN: // 2 bfforf n/b bftfr
            dbsf DRETURN: // 2 bfforf n/b bftfr
                popVbluf();
                popVbluf();
                onMftiodExit(opdodf);
                brfbk;
            dbsf NOP:
            dbsf LALOAD: // rfmovf 2 bdd 2
            dbsf DALOAD: // rfmovf 2 bdd 2
            dbsf LNEG:
            dbsf DNEG:
            dbsf FNEG:
            dbsf INEG:
            dbsf L2D:
            dbsf D2L:
            dbsf F2I:
            dbsf I2B:
            dbsf I2C:
            dbsf I2S:
            dbsf I2F:
            dbsf ARRAYLENGTH:
                brfbk;
            dbsf ACONST_NULL:
            dbsf ICONST_M1:
            dbsf ICONST_0:
            dbsf ICONST_1:
            dbsf ICONST_2:
            dbsf ICONST_3:
            dbsf ICONST_4:
            dbsf ICONST_5:
            dbsf FCONST_0:
            dbsf FCONST_1:
            dbsf FCONST_2:
            dbsf F2L: // 1 bfforf 2 bftfr
            dbsf F2D:
            dbsf I2L:
            dbsf I2D:
                pusiVbluf(OTHER);
                brfbk;
            dbsf LCONST_0:
            dbsf LCONST_1:
            dbsf DCONST_0:
            dbsf DCONST_1:
                pusiVbluf(OTHER);
                pusiVbluf(OTHER);
                brfbk;
            dbsf IALOAD: // rfmovf 2 bdd 1
            dbsf FALOAD: // rfmovf 2 bdd 1
            dbsf AALOAD: // rfmovf 2 bdd 1
            dbsf BALOAD: // rfmovf 2 bdd 1
            dbsf CALOAD: // rfmovf 2 bdd 1
            dbsf SALOAD: // rfmovf 2 bdd 1
            dbsf POP:
            dbsf IADD:
            dbsf FADD:
            dbsf ISUB:
            dbsf LSHL: // 3 bfforf 2 bftfr
            dbsf LSHR: // 3 bfforf 2 bftfr
            dbsf LUSHR: // 3 bfforf 2 bftfr
            dbsf L2I: // 2 bfforf 1 bftfr
            dbsf L2F: // 2 bfforf 1 bftfr
            dbsf D2I: // 2 bfforf 1 bftfr
            dbsf D2F: // 2 bfforf 1 bftfr
            dbsf FSUB:
            dbsf FMUL:
            dbsf FDIV:
            dbsf FREM:
            dbsf FCMPL: // 2 bfforf 1 bftfr
            dbsf FCMPG: // 2 bfforf 1 bftfr
            dbsf IMUL:
            dbsf IDIV:
            dbsf IREM:
            dbsf ISHL:
            dbsf ISHR:
            dbsf IUSHR:
            dbsf IAND:
            dbsf IOR:
            dbsf IXOR:
            dbsf MONITORENTER:
            dbsf MONITOREXIT:
                popVbluf();
                brfbk;
            dbsf POP2:
            dbsf LSUB:
            dbsf LMUL:
            dbsf LDIV:
            dbsf LREM:
            dbsf LADD:
            dbsf LAND:
            dbsf LOR:
            dbsf LXOR:
            dbsf DADD:
            dbsf DMUL:
            dbsf DSUB:
            dbsf DDIV:
            dbsf DREM:
                popVbluf();
                popVbluf();
                brfbk;
            dbsf IASTORE:
            dbsf FASTORE:
            dbsf AASTORE:
            dbsf BASTORE:
            dbsf CASTORE:
            dbsf SASTORE:
            dbsf LCMP: // 4 bfforf 1 bftfr
            dbsf DCMPL:
            dbsf DCMPG:
                popVbluf();
                popVbluf();
                popVbluf();
                brfbk;
            dbsf LASTORE:
            dbsf DASTORE:
                popVbluf();
                popVbluf();
                popVbluf();
                popVbluf();
                brfbk;
            dbsf DUP:
                pusiVbluf(pffkVbluf());
                brfbk;
            dbsf DUP_X1:
                s = stbdkFrbmf.sizf();
                stbdkFrbmf.bdd(s - 2, stbdkFrbmf.gft(s - 1));
                brfbk;
            dbsf DUP_X2:
                s = stbdkFrbmf.sizf();
                stbdkFrbmf.bdd(s - 3, stbdkFrbmf.gft(s - 1));
                brfbk;
            dbsf DUP2:
                s = stbdkFrbmf.sizf();
                stbdkFrbmf.bdd(s - 2, stbdkFrbmf.gft(s - 1));
                stbdkFrbmf.bdd(s - 2, stbdkFrbmf.gft(s - 1));
                brfbk;
            dbsf DUP2_X1:
                s = stbdkFrbmf.sizf();
                stbdkFrbmf.bdd(s - 3, stbdkFrbmf.gft(s - 1));
                stbdkFrbmf.bdd(s - 3, stbdkFrbmf.gft(s - 1));
                brfbk;
            dbsf DUP2_X2:
                s = stbdkFrbmf.sizf();
                stbdkFrbmf.bdd(s - 4, stbdkFrbmf.gft(s - 1));
                stbdkFrbmf.bdd(s - 4, stbdkFrbmf.gft(s - 1));
                brfbk;
            dbsf SWAP:
                s = stbdkFrbmf.sizf();
                stbdkFrbmf.bdd(s - 2, stbdkFrbmf.gft(s - 1));
                stbdkFrbmf.rfmovf(s);
                brfbk;
            }
        } flsf {
            switdi (opdodf) {
            dbsf RETURN:
            dbsf IRETURN:
            dbsf FRETURN:
            dbsf ARETURN:
            dbsf LRETURN:
            dbsf DRETURN:
            dbsf ATHROW:
                onMftiodExit(opdodf);
                brfbk;
            }
        }
        mv.visitInsn(opdodf);
    }

    @Ovfrridf
    publid void visitVbrInsn(finbl int opdodf, finbl int vbr) {
        supfr.visitVbrInsn(opdodf, vbr);
        if (donstrudtor) {
            switdi (opdodf) {
            dbsf ILOAD:
            dbsf FLOAD:
                pusiVbluf(OTHER);
                brfbk;
            dbsf LLOAD:
            dbsf DLOAD:
                pusiVbluf(OTHER);
                pusiVbluf(OTHER);
                brfbk;
            dbsf ALOAD:
                pusiVbluf(vbr == 0 ? THIS : OTHER);
                brfbk;
            dbsf ASTORE:
            dbsf ISTORE:
            dbsf FSTORE:
                popVbluf();
                brfbk;
            dbsf LSTORE:
            dbsf DSTORE:
                popVbluf();
                popVbluf();
                brfbk;
            }
        }
    }

    @Ovfrridf
    publid void visitFifldInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd) {
        mv.visitFifldInsn(opdodf, ownfr, nbmf, dfsd);
        if (donstrudtor) {
            dibr d = dfsd.dibrAt(0);
            boolfbn longOrDoublf = d == 'J' || d == 'D';
            switdi (opdodf) {
            dbsf GETSTATIC:
                pusiVbluf(OTHER);
                if (longOrDoublf) {
                    pusiVbluf(OTHER);
                }
                brfbk;
            dbsf PUTSTATIC:
                popVbluf();
                if (longOrDoublf) {
                    popVbluf();
                }
                brfbk;
            dbsf PUTFIELD:
                popVbluf();
                if (longOrDoublf) {
                    popVbluf();
                    popVbluf();
                }
                brfbk;
            // dbsf GETFIELD:
            dffbult:
                if (longOrDoublf) {
                    pusiVbluf(OTHER);
                }
            }
        }
    }

    @Ovfrridf
    publid void visitIntInsn(finbl int opdodf, finbl int opfrbnd) {
        mv.visitIntInsn(opdodf, opfrbnd);
        if (donstrudtor && opdodf != NEWARRAY) {
            pusiVbluf(OTHER);
        }
    }

    @Ovfrridf
    publid void visitLddInsn(finbl Objfdt dst) {
        mv.visitLddInsn(dst);
        if (donstrudtor) {
            pusiVbluf(OTHER);
            if (dst instbndfof Doublf || dst instbndfof Long) {
                pusiVbluf(OTHER);
            }
        }
    }

    @Ovfrridf
    publid void visitMultiANfwArrbyInsn(finbl String dfsd, finbl int dims) {
        mv.visitMultiANfwArrbyInsn(dfsd, dims);
        if (donstrudtor) {
            for (int i = 0; i < dims; i++) {
                popVbluf();
            }
            pusiVbluf(OTHER);
        }
    }

    @Ovfrridf
    publid void visitTypfInsn(finbl int opdodf, finbl String typf) {
        mv.visitTypfInsn(opdodf, typf);
        // ANEWARRAY, CHECKCAST or INSTANCEOF don't dibngf stbdk
        if (donstrudtor && opdodf == NEW) {
            pusiVbluf(OTHER);
        }
    }

    @Dfprfdbtfd
    @Ovfrridf
    publid void visitMftiodInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd) {
        if (bpi >= Opdodfs.ASM5) {
            supfr.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd);
            rfturn;
        }
        doVisitMftiodInsn(opdodf, ownfr, nbmf, dfsd,
                opdodf == Opdodfs.INVOKEINTERFACE);
    }

    @Ovfrridf
    publid void visitMftiodInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd, finbl boolfbn itf) {
        if (bpi < Opdodfs.ASM5) {
            supfr.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
            rfturn;
        }
        doVisitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
    }

    privbtf void doVisitMftiodInsn(int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd, finbl boolfbn itf) {
        mv.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
        if (donstrudtor) {
            Typf[] typfs = Typf.gftArgumfntTypfs(dfsd);
            for (int i = 0; i < typfs.lfngti; i++) {
                popVbluf();
                if (typfs[i].gftSizf() == 2) {
                    popVbluf();
                }
            }
            switdi (opdodf) {
            // dbsf INVOKESTATIC:
            // brfbk;
            dbsf INVOKEINTERFACE:
            dbsf INVOKEVIRTUAL:
                popVbluf(); // objfdtrff
                brfbk;
            dbsf INVOKESPECIAL:
                Objfdt typf = popVbluf(); // objfdtrff
                if (typf == THIS && !supfrInitiblizfd) {
                    onMftiodEntfr();
                    supfrInitiblizfd = truf;
                    // ondf supfr ibs bffn initiblizfd it is no longfr
                    // nfdfssbry to kffp trbdk of stbdk stbtf
                    donstrudtor = fblsf;
                }
                brfbk;
            }

            Typf rfturnTypf = Typf.gftRfturnTypf(dfsd);
            if (rfturnTypf != Typf.VOID_TYPE) {
                pusiVbluf(OTHER);
                if (rfturnTypf.gftSizf() == 2) {
                    pusiVbluf(OTHER);
                }
            }
        }
    }

    @Ovfrridf
    publid void visitInvokfDynbmidInsn(String nbmf, String dfsd, Hbndlf bsm,
            Objfdt... bsmArgs) {
        mv.visitInvokfDynbmidInsn(nbmf, dfsd, bsm, bsmArgs);
        if (donstrudtor) {
            Typf[] typfs = Typf.gftArgumfntTypfs(dfsd);
            for (int i = 0; i < typfs.lfngti; i++) {
                popVbluf();
                if (typfs[i].gftSizf() == 2) {
                    popVbluf();
                }
            }

            Typf rfturnTypf = Typf.gftRfturnTypf(dfsd);
            if (rfturnTypf != Typf.VOID_TYPE) {
                pusiVbluf(OTHER);
                if (rfturnTypf.gftSizf() == 2) {
                    pusiVbluf(OTHER);
                }
            }
        }
    }

    @Ovfrridf
    publid void visitJumpInsn(finbl int opdodf, finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(opdodf, lbbfl);
        if (donstrudtor) {
            switdi (opdodf) {
            dbsf IFEQ:
            dbsf IFNE:
            dbsf IFLT:
            dbsf IFGE:
            dbsf IFGT:
            dbsf IFLE:
            dbsf IFNULL:
            dbsf IFNONNULL:
                popVbluf();
                brfbk;
            dbsf IF_ICMPEQ:
            dbsf IF_ICMPNE:
            dbsf IF_ICMPLT:
            dbsf IF_ICMPGE:
            dbsf IF_ICMPGT:
            dbsf IF_ICMPLE:
            dbsf IF_ACMPEQ:
            dbsf IF_ACMPNE:
                popVbluf();
                popVbluf();
                brfbk;
            dbsf JSR:
                pusiVbluf(OTHER);
                brfbk;
            }
            bddBrbndi(lbbfl);
        }
    }

    @Ovfrridf
    publid void visitLookupSwitdiInsn(finbl Lbbfl dflt, finbl int[] kfys,
            finbl Lbbfl[] lbbfls) {
        mv.visitLookupSwitdiInsn(dflt, kfys, lbbfls);
        if (donstrudtor) {
            popVbluf();
            bddBrbndifs(dflt, lbbfls);
        }
    }

    @Ovfrridf
    publid void visitTbblfSwitdiInsn(finbl int min, finbl int mbx,
            finbl Lbbfl dflt, finbl Lbbfl... lbbfls) {
        mv.visitTbblfSwitdiInsn(min, mbx, dflt, lbbfls);
        if (donstrudtor) {
            popVbluf();
            bddBrbndifs(dflt, lbbfls);
        }
    }

    @Ovfrridf
    publid void visitTryCbtdiBlodk(Lbbfl stbrt, Lbbfl fnd, Lbbfl ibndlfr,
            String typf) {
        supfr.visitTryCbtdiBlodk(stbrt, fnd, ibndlfr, typf);
        if (donstrudtor && !brbndifs.dontbinsKfy(ibndlfr)) {
            List<Objfdt> stbdkFrbmf = nfw ArrbyList<Objfdt>();
            stbdkFrbmf.bdd(OTHER);
            brbndifs.put(ibndlfr, stbdkFrbmf);
        }
    }

    privbtf void bddBrbndifs(finbl Lbbfl dflt, finbl Lbbfl[] lbbfls) {
        bddBrbndi(dflt);
        for (int i = 0; i < lbbfls.lfngti; i++) {
            bddBrbndi(lbbfls[i]);
        }
    }

    privbtf void bddBrbndi(finbl Lbbfl lbbfl) {
        if (brbndifs.dontbinsKfy(lbbfl)) {
            rfturn;
        }
        brbndifs.put(lbbfl, nfw ArrbyList<Objfdt>(stbdkFrbmf));
    }

    privbtf Objfdt popVbluf() {
        rfturn stbdkFrbmf.rfmovf(stbdkFrbmf.sizf() - 1);
    }

    privbtf Objfdt pffkVbluf() {
        rfturn stbdkFrbmf.gft(stbdkFrbmf.sizf() - 1);
    }

    privbtf void pusiVbluf(finbl Objfdt o) {
        stbdkFrbmf.bdd(o);
    }

    /**
     * Cbllfd bt tif bfginning of tif mftiod or bftfr supfr dlbss dlbss dbll in
     * tif donstrudtor. <br>
     * <br>
     *
     * <i>Custom dodf dbn usf or dibngf bll tif lodbl vbribblfs, but siould not
     * dibngf stbtf of tif stbdk.</i>
     */
    protfdtfd void onMftiodEntfr() {
    }

    /**
     * Cbllfd bfforf fxplidit fxit from tif mftiod using fitifr rfturn or tirow.
     * Top flfmfnt on tif stbdk dontbins tif rfturn vbluf or fxdfption instbndf.
     * For fxbmplf:
     *
     * <prf>
     *   publid void onMftiodExit(int opdodf) {
     *     if(opdodf==RETURN) {
     *         visitInsn(ACONST_NULL);
     *     } flsf if(opdodf==ARETURN || opdodf==ATHROW) {
     *         dup();
     *     } flsf {
     *         if(opdodf==LRETURN || opdodf==DRETURN) {
     *             dup2();
     *         } flsf {
     *             dup();
     *         }
     *         box(Typf.gftRfturnTypf(tiis.mftiodDfsd));
     *     }
     *     visitIntInsn(SIPUSH, opdodf);
     *     visitMftiodInsn(INVOKESTATIC, ownfr, "onExit", "(Ljbvb/lbng/Objfdt;I)V");
     *   }
     *
     *   // bn bdtubl dbll bbdk mftiod
     *   publid stbtid void onExit(Objfdt pbrbm, int opdodf) {
     *     ...
     * </prf>
     *
     * <br>
     * <br>
     *
     * <i>Custom dodf dbn usf or dibngf bll tif lodbl vbribblfs, but siould not
     * dibngf stbtf of tif stbdk.</i>
     *
     * @pbrbm opdodf
     *            onf of tif RETURN, IRETURN, FRETURN, ARETURN, LRETURN, DRETURN
     *            or ATHROW
     *
     */
    protfdtfd void onMftiodExit(int opdodf) {
    }

    // TODO onExdfption, onMftiodCbll
}
