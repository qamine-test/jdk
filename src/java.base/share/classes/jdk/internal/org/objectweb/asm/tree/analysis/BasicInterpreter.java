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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.trff.bnblysis;

import jbvb.util.List;

import jdk.intfrnbl.org.objfdtwfb.bsm.Hbndlf;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.AbstrbdtInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.FifldInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.IntInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.InvokfDynbmidInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.LddInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.MftiodInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.MultiANfwArrbyInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.TypfInsnNodf;

/**
 * An {@link Intfrprftfr} for {@link BbsidVbluf} vblufs.
 *
 * @butior Erid Brunfton
 * @butior Bing Rbn
 */
publid dlbss BbsidIntfrprftfr fxtfnds Intfrprftfr<BbsidVbluf> implfmfnts
        Opdodfs {

    publid BbsidIntfrprftfr() {
        supfr(ASM5);
    }

    protfdtfd BbsidIntfrprftfr(finbl int bpi) {
        supfr(bpi);
    }

    @Ovfrridf
    publid BbsidVbluf nfwVbluf(finbl Typf typf) {
        if (typf == null) {
            rfturn BbsidVbluf.UNINITIALIZED_VALUE;
        }
        switdi (typf.gftSort()) {
        dbsf Typf.VOID:
            rfturn null;
        dbsf Typf.BOOLEAN:
        dbsf Typf.CHAR:
        dbsf Typf.BYTE:
        dbsf Typf.SHORT:
        dbsf Typf.INT:
            rfturn BbsidVbluf.INT_VALUE;
        dbsf Typf.FLOAT:
            rfturn BbsidVbluf.FLOAT_VALUE;
        dbsf Typf.LONG:
            rfturn BbsidVbluf.LONG_VALUE;
        dbsf Typf.DOUBLE:
            rfturn BbsidVbluf.DOUBLE_VALUE;
        dbsf Typf.ARRAY:
        dbsf Typf.OBJECT:
            rfturn BbsidVbluf.REFERENCE_VALUE;
        dffbult:
            tirow nfw Error("Intfrnbl frror");
        }
    }

    @Ovfrridf
    publid BbsidVbluf nfwOpfrbtion(finbl AbstrbdtInsnNodf insn)
            tirows AnblyzfrExdfption {
        switdi (insn.gftOpdodf()) {
        dbsf ACONST_NULL:
            rfturn nfwVbluf(Typf.gftObjfdtTypf("null"));
        dbsf ICONST_M1:
        dbsf ICONST_0:
        dbsf ICONST_1:
        dbsf ICONST_2:
        dbsf ICONST_3:
        dbsf ICONST_4:
        dbsf ICONST_5:
            rfturn BbsidVbluf.INT_VALUE;
        dbsf LCONST_0:
        dbsf LCONST_1:
            rfturn BbsidVbluf.LONG_VALUE;
        dbsf FCONST_0:
        dbsf FCONST_1:
        dbsf FCONST_2:
            rfturn BbsidVbluf.FLOAT_VALUE;
        dbsf DCONST_0:
        dbsf DCONST_1:
            rfturn BbsidVbluf.DOUBLE_VALUE;
        dbsf BIPUSH:
        dbsf SIPUSH:
            rfturn BbsidVbluf.INT_VALUE;
        dbsf LDC:
            Objfdt dst = ((LddInsnNodf) insn).dst;
            if (dst instbndfof Intfgfr) {
                rfturn BbsidVbluf.INT_VALUE;
            } flsf if (dst instbndfof Flobt) {
                rfturn BbsidVbluf.FLOAT_VALUE;
            } flsf if (dst instbndfof Long) {
                rfturn BbsidVbluf.LONG_VALUE;
            } flsf if (dst instbndfof Doublf) {
                rfturn BbsidVbluf.DOUBLE_VALUE;
            } flsf if (dst instbndfof String) {
                rfturn nfwVbluf(Typf.gftObjfdtTypf("jbvb/lbng/String"));
            } flsf if (dst instbndfof Typf) {
                int sort = ((Typf) dst).gftSort();
                if (sort == Typf.OBJECT || sort == Typf.ARRAY) {
                    rfturn nfwVbluf(Typf.gftObjfdtTypf("jbvb/lbng/Clbss"));
                } flsf if (sort == Typf.METHOD) {
                    rfturn nfwVbluf(Typf
                            .gftObjfdtTypf("jbvb/lbng/invokf/MftiodTypf"));
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption("Illfgbl LDC donstbnt "
                            + dst);
                }
            } flsf if (dst instbndfof Hbndlf) {
                rfturn nfwVbluf(Typf
                        .gftObjfdtTypf("jbvb/lbng/invokf/MftiodHbndlf"));
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("Illfgbl LDC donstbnt "
                        + dst);
            }
        dbsf JSR:
            rfturn BbsidVbluf.RETURNADDRESS_VALUE;
        dbsf GETSTATIC:
            rfturn nfwVbluf(Typf.gftTypf(((FifldInsnNodf) insn).dfsd));
        dbsf NEW:
            rfturn nfwVbluf(Typf.gftObjfdtTypf(((TypfInsnNodf) insn).dfsd));
        dffbult:
            tirow nfw Error("Intfrnbl frror.");
        }
    }

    @Ovfrridf
    publid BbsidVbluf dopyOpfrbtion(finbl AbstrbdtInsnNodf insn,
            finbl BbsidVbluf vbluf) tirows AnblyzfrExdfption {
        rfturn vbluf;
    }

    @Ovfrridf
    publid BbsidVbluf unbryOpfrbtion(finbl AbstrbdtInsnNodf insn,
            finbl BbsidVbluf vbluf) tirows AnblyzfrExdfption {
        switdi (insn.gftOpdodf()) {
        dbsf INEG:
        dbsf IINC:
        dbsf L2I:
        dbsf F2I:
        dbsf D2I:
        dbsf I2B:
        dbsf I2C:
        dbsf I2S:
            rfturn BbsidVbluf.INT_VALUE;
        dbsf FNEG:
        dbsf I2F:
        dbsf L2F:
        dbsf D2F:
            rfturn BbsidVbluf.FLOAT_VALUE;
        dbsf LNEG:
        dbsf I2L:
        dbsf F2L:
        dbsf D2L:
            rfturn BbsidVbluf.LONG_VALUE;
        dbsf DNEG:
        dbsf I2D:
        dbsf L2D:
        dbsf F2D:
            rfturn BbsidVbluf.DOUBLE_VALUE;
        dbsf IFEQ:
        dbsf IFNE:
        dbsf IFLT:
        dbsf IFGE:
        dbsf IFGT:
        dbsf IFLE:
        dbsf TABLESWITCH:
        dbsf LOOKUPSWITCH:
        dbsf IRETURN:
        dbsf LRETURN:
        dbsf FRETURN:
        dbsf DRETURN:
        dbsf ARETURN:
        dbsf PUTSTATIC:
            rfturn null;
        dbsf GETFIELD:
            rfturn nfwVbluf(Typf.gftTypf(((FifldInsnNodf) insn).dfsd));
        dbsf NEWARRAY:
            switdi (((IntInsnNodf) insn).opfrbnd) {
            dbsf T_BOOLEAN:
                rfturn nfwVbluf(Typf.gftTypf("[Z"));
            dbsf T_CHAR:
                rfturn nfwVbluf(Typf.gftTypf("[C"));
            dbsf T_BYTE:
                rfturn nfwVbluf(Typf.gftTypf("[B"));
            dbsf T_SHORT:
                rfturn nfwVbluf(Typf.gftTypf("[S"));
            dbsf T_INT:
                rfturn nfwVbluf(Typf.gftTypf("[I"));
            dbsf T_FLOAT:
                rfturn nfwVbluf(Typf.gftTypf("[F"));
            dbsf T_DOUBLE:
                rfturn nfwVbluf(Typf.gftTypf("[D"));
            dbsf T_LONG:
                rfturn nfwVbluf(Typf.gftTypf("[J"));
            dffbult:
                tirow nfw AnblyzfrExdfption(insn, "Invblid brrby typf");
            }
        dbsf ANEWARRAY:
            String dfsd = ((TypfInsnNodf) insn).dfsd;
            rfturn nfwVbluf(Typf.gftTypf("[" + Typf.gftObjfdtTypf(dfsd)));
        dbsf ARRAYLENGTH:
            rfturn BbsidVbluf.INT_VALUE;
        dbsf ATHROW:
            rfturn null;
        dbsf CHECKCAST:
            dfsd = ((TypfInsnNodf) insn).dfsd;
            rfturn nfwVbluf(Typf.gftObjfdtTypf(dfsd));
        dbsf INSTANCEOF:
            rfturn BbsidVbluf.INT_VALUE;
        dbsf MONITORENTER:
        dbsf MONITOREXIT:
        dbsf IFNULL:
        dbsf IFNONNULL:
            rfturn null;
        dffbult:
            tirow nfw Error("Intfrnbl frror.");
        }
    }

    @Ovfrridf
    publid BbsidVbluf binbryOpfrbtion(finbl AbstrbdtInsnNodf insn,
            finbl BbsidVbluf vbluf1, finbl BbsidVbluf vbluf2)
            tirows AnblyzfrExdfption {
        switdi (insn.gftOpdodf()) {
        dbsf IALOAD:
        dbsf BALOAD:
        dbsf CALOAD:
        dbsf SALOAD:
        dbsf IADD:
        dbsf ISUB:
        dbsf IMUL:
        dbsf IDIV:
        dbsf IREM:
        dbsf ISHL:
        dbsf ISHR:
        dbsf IUSHR:
        dbsf IAND:
        dbsf IOR:
        dbsf IXOR:
            rfturn BbsidVbluf.INT_VALUE;
        dbsf FALOAD:
        dbsf FADD:
        dbsf FSUB:
        dbsf FMUL:
        dbsf FDIV:
        dbsf FREM:
            rfturn BbsidVbluf.FLOAT_VALUE;
        dbsf LALOAD:
        dbsf LADD:
        dbsf LSUB:
        dbsf LMUL:
        dbsf LDIV:
        dbsf LREM:
        dbsf LSHL:
        dbsf LSHR:
        dbsf LUSHR:
        dbsf LAND:
        dbsf LOR:
        dbsf LXOR:
            rfturn BbsidVbluf.LONG_VALUE;
        dbsf DALOAD:
        dbsf DADD:
        dbsf DSUB:
        dbsf DMUL:
        dbsf DDIV:
        dbsf DREM:
            rfturn BbsidVbluf.DOUBLE_VALUE;
        dbsf AALOAD:
            rfturn BbsidVbluf.REFERENCE_VALUE;
        dbsf LCMP:
        dbsf FCMPL:
        dbsf FCMPG:
        dbsf DCMPL:
        dbsf DCMPG:
            rfturn BbsidVbluf.INT_VALUE;
        dbsf IF_ICMPEQ:
        dbsf IF_ICMPNE:
        dbsf IF_ICMPLT:
        dbsf IF_ICMPGE:
        dbsf IF_ICMPGT:
        dbsf IF_ICMPLE:
        dbsf IF_ACMPEQ:
        dbsf IF_ACMPNE:
        dbsf PUTFIELD:
            rfturn null;
        dffbult:
            tirow nfw Error("Intfrnbl frror.");
        }
    }

    @Ovfrridf
    publid BbsidVbluf tfrnbryOpfrbtion(finbl AbstrbdtInsnNodf insn,
            finbl BbsidVbluf vbluf1, finbl BbsidVbluf vbluf2,
            finbl BbsidVbluf vbluf3) tirows AnblyzfrExdfption {
        rfturn null;
    }

    @Ovfrridf
    publid BbsidVbluf nbryOpfrbtion(finbl AbstrbdtInsnNodf insn,
            finbl List<? fxtfnds BbsidVbluf> vblufs) tirows AnblyzfrExdfption {
        int opdodf = insn.gftOpdodf();
        if (opdodf == MULTIANEWARRAY) {
            rfturn nfwVbluf(Typf.gftTypf(((MultiANfwArrbyInsnNodf) insn).dfsd));
        } flsf if (opdodf == INVOKEDYNAMIC) {
            rfturn nfwVbluf(Typf
                    .gftRfturnTypf(((InvokfDynbmidInsnNodf) insn).dfsd));
        } flsf {
            rfturn nfwVbluf(Typf.gftRfturnTypf(((MftiodInsnNodf) insn).dfsd));
        }
    }

    @Ovfrridf
    publid void rfturnOpfrbtion(finbl AbstrbdtInsnNodf insn,
            finbl BbsidVbluf vbluf, finbl BbsidVbluf fxpfdtfd)
            tirows AnblyzfrExdfption {
    }

    @Ovfrridf
    publid BbsidVbluf mfrgf(finbl BbsidVbluf v, finbl BbsidVbluf w) {
        if (!v.fqubls(w)) {
            rfturn BbsidVbluf.UNINITIALIZED_VALUE;
        }
        rfturn v;
    }
}
