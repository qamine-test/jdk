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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm;

/**
 * A visitor to visit b Jbvb mftiod. Tif mftiods of tiis dlbss must bf dbllfd in
 * tif following ordfr: ( <tt>visitPbrbmftfr</tt> )* [
 * <tt>visitAnnotbtionDffbult</tt> ] ( <tt>visitAnnotbtion</tt> |
 * <tt>visitTypfAnnotbtion</tt> | <tt>visitAttributf</tt> )* [
 * <tt>visitCodf</tt> ( <tt>visitFrbmf</tt> | <tt>visit<i>X</i>Insn</tt> |
 * <tt>visitLbbfl</tt> | <tt>visitInsnAnnotbtion</tt> |
 * <tt>visitTryCbtdiBlodk</tt> | <tt>visitTryCbtdiBlodkAnnotbtion</tt> |
 * <tt>visitLodblVbribblf</tt> | <tt>visitLodblVbribblfAnnotbtion</tt> |
 * <tt>visitLinfNumbfr</tt> )* <tt>visitMbxs</tt> ] <tt>visitEnd</tt>. In
 * bddition, tif <tt>visit<i>X</i>Insn</tt> bnd <tt>visitLbbfl</tt> mftiods must
 * bf dbllfd in tif sfqufntibl ordfr of tif bytfdodf instrudtions of tif visitfd
 * dodf, <tt>visitInsnAnnotbtion</tt> must bf dbllfd <i>bftfr</i> tif bnnotbtfd
 * instrudtion, <tt>visitTryCbtdiBlodk</tt> must bf dbllfd <i>bfforf</i> tif
 * lbbfls pbssfd bs brgumfnts ibvf bffn visitfd,
 * <tt>visitTryCbtdiBlodkAnnotbtion</tt> must bf dbllfd <i>bftfr</i> tif
 * dorrfsponding try dbtdi blodk ibs bffn visitfd, bnd tif
 * <tt>visitLodblVbribblf</tt>, <tt>visitLodblVbribblfAnnotbtion</tt> bnd
 * <tt>visitLinfNumbfr</tt> mftiods must bf dbllfd <i>bftfr</i> tif lbbfls
 * pbssfd bs brgumfnts ibvf bffn visitfd.
 *
 * @butior Erid Brunfton
 */
publid bbstrbdt dlbss MftiodVisitor {

    /**
     * Tif ASM API vfrsion implfmfntfd by tiis visitor. Tif vbluf of tiis fifld
     * must bf onf of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    protfdtfd finbl int bpi;

    /**
     * Tif mftiod visitor to wiidi tiis visitor must dflfgbtf mftiod dblls. Mby
     * bf null.
     */
    protfdtfd MftiodVisitor mv;

    /**
     * Construdts b nfw {@link MftiodVisitor}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    publid MftiodVisitor(finbl int bpi) {
        tiis(bpi, null);
    }

    /**
     * Construdts b nfw {@link MftiodVisitor}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm mv
     *            tif mftiod visitor to wiidi tiis visitor must dflfgbtf mftiod
     *            dblls. Mby bf null.
     */
    publid MftiodVisitor(finbl int bpi, finbl MftiodVisitor mv) {
        if (bpi != Opdodfs.ASM4 && bpi != Opdodfs.ASM5) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        tiis.bpi = bpi;
        tiis.mv = mv;
    }

    // -------------------------------------------------------------------------
    // Pbrbmftfrs, bnnotbtions bnd non stbndbrd bttributfs
    // -------------------------------------------------------------------------

    /**
     * Visits b pbrbmftfr of tiis mftiod.
     *
     * @pbrbm nbmf
     *            pbrbmftfr nbmf or null if nonf is providfd.
     * @pbrbm bddfss
     *            tif pbrbmftfr's bddfss flbgs, only <tt>ACC_FINAL</tt>,
     *            <tt>ACC_SYNTHETIC</tt> or/bnd <tt>ACC_MANDATED</tt> brf
     *            bllowfd (sff {@link Opdodfs}).
     */
    publid void visitPbrbmftfr(String nbmf, int bddfss) {
        if (bpi < Opdodfs.ASM5) {
            tirow nfw RuntimfExdfption();
        }
        if (mv != null) {
            mv.visitPbrbmftfr(nbmf, bddfss);
        }
    }

    /**
     * Visits tif dffbult vbluf of tiis bnnotbtion intfrfbdf mftiod.
     *
     * @rfturn b visitor to tif visit tif bdtubl dffbult vbluf of tiis
     *         bnnotbtion intfrfbdf mftiod, or <tt>null</tt> if tiis visitor is
     *         not intfrfstfd in visiting tiis dffbult vbluf. Tif 'nbmf'
     *         pbrbmftfrs pbssfd to tif mftiods of tiis bnnotbtion visitor brf
     *         ignorfd. Morfovfr, fxbdly onf visit mftiod must bf dbllfd on tiis
     *         bnnotbtion visitor, followfd by visitEnd.
     */
    publid AnnotbtionVisitor visitAnnotbtionDffbult() {
        if (mv != null) {
            rfturn mv.visitAnnotbtionDffbult();
        }
        rfturn null;
    }

    /**
     * Visits bn bnnotbtion of tiis mftiod.
     *
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     * @pbrbm visiblf
     *            <tt>truf</tt> if tif bnnotbtion is visiblf bt runtimf.
     * @rfturn b visitor to visit tif bnnotbtion vblufs, or <tt>null</tt> if
     *         tiis visitor is not intfrfstfd in visiting tiis bnnotbtion.
     */
    publid AnnotbtionVisitor visitAnnotbtion(String dfsd, boolfbn visiblf) {
        if (mv != null) {
            rfturn mv.visitAnnotbtion(dfsd, visiblf);
        }
        rfturn null;
    }

    /**
     * Visits bn bnnotbtion on b typf in tif mftiod signbturf.
     *
     * @pbrbm typfRff
     *            b rfffrfndf to tif bnnotbtfd typf. Tif sort of tiis typf
     *            rfffrfndf must bf {@link TypfRfffrfndf#METHOD_TYPE_PARAMETER
     *            METHOD_TYPE_PARAMETER},
     *            {@link TypfRfffrfndf#METHOD_TYPE_PARAMETER_BOUND
     *            METHOD_TYPE_PARAMETER_BOUND},
     *            {@link TypfRfffrfndf#METHOD_RETURN METHOD_RETURN},
     *            {@link TypfRfffrfndf#METHOD_RECEIVER METHOD_RECEIVER},
     *            {@link TypfRfffrfndf#METHOD_FORMAL_PARAMETER
     *            METHOD_FORMAL_PARAMETER} or {@link TypfRfffrfndf#THROWS
     *            THROWS}. Sff {@link TypfRfffrfndf}.
     * @pbrbm typfPbti
     *            tif pbti to tif bnnotbtfd typf brgumfnt, wilddbrd bound, brrby
     *            flfmfnt typf, or stbtid innfr typf witiin 'typfRff'. Mby bf
     *            <tt>null</tt> if tif bnnotbtion tbrgfts 'typfRff' bs b wiolf.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     * @pbrbm visiblf
     *            <tt>truf</tt> if tif bnnotbtion is visiblf bt runtimf.
     * @rfturn b visitor to visit tif bnnotbtion vblufs, or <tt>null</tt> if
     *         tiis visitor is not intfrfstfd in visiting tiis bnnotbtion.
     */
    publid AnnotbtionVisitor visitTypfAnnotbtion(int typfRff,
            TypfPbti typfPbti, String dfsd, boolfbn visiblf) {
        if (bpi < Opdodfs.ASM5) {
            tirow nfw RuntimfExdfption();
        }
        if (mv != null) {
            rfturn mv.visitTypfAnnotbtion(typfRff, typfPbti, dfsd, visiblf);
        }
        rfturn null;
    }

    /**
     * Visits bn bnnotbtion of b pbrbmftfr tiis mftiod.
     *
     * @pbrbm pbrbmftfr
     *            tif pbrbmftfr indfx.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     * @pbrbm visiblf
     *            <tt>truf</tt> if tif bnnotbtion is visiblf bt runtimf.
     * @rfturn b visitor to visit tif bnnotbtion vblufs, or <tt>null</tt> if
     *         tiis visitor is not intfrfstfd in visiting tiis bnnotbtion.
     */
    publid AnnotbtionVisitor visitPbrbmftfrAnnotbtion(int pbrbmftfr,
            String dfsd, boolfbn visiblf) {
        if (mv != null) {
            rfturn mv.visitPbrbmftfrAnnotbtion(pbrbmftfr, dfsd, visiblf);
        }
        rfturn null;
    }

    /**
     * Visits b non stbndbrd bttributf of tiis mftiod.
     *
     * @pbrbm bttr
     *            bn bttributf.
     */
    publid void visitAttributf(Attributf bttr) {
        if (mv != null) {
            mv.visitAttributf(bttr);
        }
    }

    /**
     * Stbrts tif visit of tif mftiod's dodf, if bny (i.f. non bbstrbdt mftiod).
     */
    publid void visitCodf() {
        if (mv != null) {
            mv.visitCodf();
        }
    }

    /**
     * Visits tif durrfnt stbtf of tif lodbl vbribblfs bnd opfrbnd stbdk
     * flfmfnts. Tiis mftiod must(*) bf dbllfd <i>just bfforf</i> bny
     * instrudtion <b>i</b> tibt follows bn undonditionbl brbndi instrudtion
     * sudi bs GOTO or THROW, tibt is tif tbrgft of b jump instrudtion, or tibt
     * stbrts bn fxdfption ibndlfr blodk. Tif visitfd typfs must dfsdribf tif
     * vblufs of tif lodbl vbribblfs bnd of tif opfrbnd stbdk flfmfnts <i>just
     * bfforf</i> <b>i</b> is fxfdutfd.<br>
     * <br>
     * (*) tiis is mbndbtory only for dlbssfs wiosf vfrsion is grfbtfr tibn or
     * fqubl to {@link Opdodfs#V1_6 V1_6}. <br>
     * <br>
     * Tif frbmfs of b mftiod must bf givfn fitifr in fxpbndfd form, or in
     * domprfssfd form (bll frbmfs must usf tif sbmf formbt, i.f. you must not
     * mix fxpbndfd bnd domprfssfd frbmfs witiin b singlf mftiod):
     * <ul>
     * <li>In fxpbndfd form, bll frbmfs must ibvf tif F_NEW typf.</li>
     * <li>In domprfssfd form, frbmfs brf bbsidblly "dfltbs" from tif stbtf of
     * tif prfvious frbmf:
     * <ul>
     * <li>{@link Opdodfs#F_SAME} rfprfsfnting frbmf witi fxbdtly tif sbmf
     * lodbls bs tif prfvious frbmf bnd witi tif fmpty stbdk.</li>
     * <li>{@link Opdodfs#F_SAME1} rfprfsfnting frbmf witi fxbdtly tif sbmf
     * lodbls bs tif prfvious frbmf bnd witi singlf vbluf on tif stbdk (
     * <dodf>nStbdk</dodf> is 1 bnd <dodf>stbdk[0]</dodf> dontbins vbluf for tif
     * typf of tif stbdk itfm).</li>
     * <li>{@link Opdodfs#F_APPEND} rfprfsfnting frbmf witi durrfnt lodbls brf
     * tif sbmf bs tif lodbls in tif prfvious frbmf, fxdfpt tibt bdditionbl
     * lodbls brf dffinfd (<dodf>nLodbl</dodf> is 1, 2 or 3 bnd
     * <dodf>lodbl</dodf> flfmfnts dontbins vblufs rfprfsfnting bddfd typfs).</li>
     * <li>{@link Opdodfs#F_CHOP} rfprfsfnting frbmf witi durrfnt lodbls brf tif
     * sbmf bs tif lodbls in tif prfvious frbmf, fxdfpt tibt tif lbst 1-3 lodbls
     * brf bbsfnt bnd witi tif fmpty stbdk (<dodf>nLodbls</dodf> is 1, 2 or 3).</li>
     * <li>{@link Opdodfs#F_FULL} rfprfsfnting domplftf frbmf dbtb.</li>
     * </ul>
     * </li>
     * </ul>
     * <br>
     * In boti dbsfs tif first frbmf, dorrfsponding to tif mftiod's pbrbmftfrs
     * bnd bddfss flbgs, is implidit bnd must not bf visitfd. Also, it is
     * illfgbl to visit two or morf frbmfs for tif sbmf dodf lodbtion (i.f., bt
     * lfbst onf instrudtion must bf visitfd bftwffn two dblls to visitFrbmf).
     *
     * @pbrbm typf
     *            tif typf of tiis stbdk mbp frbmf. Must bf
     *            {@link Opdodfs#F_NEW} for fxpbndfd frbmfs, or
     *            {@link Opdodfs#F_FULL}, {@link Opdodfs#F_APPEND},
     *            {@link Opdodfs#F_CHOP}, {@link Opdodfs#F_SAME} or
     *            {@link Opdodfs#F_APPEND}, {@link Opdodfs#F_SAME1} for
     *            domprfssfd frbmfs.
     * @pbrbm nLodbl
     *            tif numbfr of lodbl vbribblfs in tif visitfd frbmf.
     * @pbrbm lodbl
     *            tif lodbl vbribblf typfs in tiis frbmf. Tiis brrby must not bf
     *            modififd. Primitivf typfs brf rfprfsfntfd by
     *            {@link Opdodfs#TOP}, {@link Opdodfs#INTEGER},
     *            {@link Opdodfs#FLOAT}, {@link Opdodfs#LONG},
     *            {@link Opdodfs#DOUBLE},{@link Opdodfs#NULL} or
     *            {@link Opdodfs#UNINITIALIZED_THIS} (long bnd doublf brf
     *            rfprfsfntfd by b singlf flfmfnt). Rfffrfndf typfs brf
     *            rfprfsfntfd by String objfdts (rfprfsfnting intfrnbl nbmfs),
     *            bnd uninitiblizfd typfs by Lbbfl objfdts (tiis lbbfl
     *            dfsignbtfs tif NEW instrudtion tibt drfbtfd tiis uninitiblizfd
     *            vbluf).
     * @pbrbm nStbdk
     *            tif numbfr of opfrbnd stbdk flfmfnts in tif visitfd frbmf.
     * @pbrbm stbdk
     *            tif opfrbnd stbdk typfs in tiis frbmf. Tiis brrby must not bf
     *            modififd. Its dontfnt ibs tif sbmf formbt bs tif "lodbl"
     *            brrby.
     * @tirows IllfgblStbtfExdfption
     *             if b frbmf is visitfd just bftfr bnotifr onf, witiout bny
     *             instrudtion bftwffn tif two (unlfss tiis frbmf is b
     *             Opdodfs#F_SAME frbmf, in wiidi dbsf it is silfntly ignorfd).
     */
    publid void visitFrbmf(int typf, int nLodbl, Objfdt[] lodbl, int nStbdk,
            Objfdt[] stbdk) {
        if (mv != null) {
            mv.visitFrbmf(typf, nLodbl, lodbl, nStbdk, stbdk);
        }
    }

    // -------------------------------------------------------------------------
    // Normbl instrudtions
    // -------------------------------------------------------------------------

    /**
     * Visits b zfro opfrbnd instrudtion.
     *
     * @pbrbm opdodf
     *            tif opdodf of tif instrudtion to bf visitfd. Tiis opdodf is
     *            fitifr NOP, ACONST_NULL, ICONST_M1, ICONST_0, ICONST_1,
     *            ICONST_2, ICONST_3, ICONST_4, ICONST_5, LCONST_0, LCONST_1,
     *            FCONST_0, FCONST_1, FCONST_2, DCONST_0, DCONST_1, IALOAD,
     *            LALOAD, FALOAD, DALOAD, AALOAD, BALOAD, CALOAD, SALOAD,
     *            IASTORE, LASTORE, FASTORE, DASTORE, AASTORE, BASTORE, CASTORE,
     *            SASTORE, POP, POP2, DUP, DUP_X1, DUP_X2, DUP2, DUP2_X1,
     *            DUP2_X2, SWAP, IADD, LADD, FADD, DADD, ISUB, LSUB, FSUB, DSUB,
     *            IMUL, LMUL, FMUL, DMUL, IDIV, LDIV, FDIV, DDIV, IREM, LREM,
     *            FREM, DREM, INEG, LNEG, FNEG, DNEG, ISHL, LSHL, ISHR, LSHR,
     *            IUSHR, LUSHR, IAND, LAND, IOR, LOR, IXOR, LXOR, I2L, I2F, I2D,
     *            L2I, L2F, L2D, F2I, F2L, F2D, D2I, D2L, D2F, I2B, I2C, I2S,
     *            LCMP, FCMPL, FCMPG, DCMPL, DCMPG, IRETURN, LRETURN, FRETURN,
     *            DRETURN, ARETURN, RETURN, ARRAYLENGTH, ATHROW, MONITORENTER,
     *            or MONITOREXIT.
     */
    publid void visitInsn(int opdodf) {
        if (mv != null) {
            mv.visitInsn(opdodf);
        }
    }

    /**
     * Visits bn instrudtion witi b singlf int opfrbnd.
     *
     * @pbrbm opdodf
     *            tif opdodf of tif instrudtion to bf visitfd. Tiis opdodf is
     *            fitifr BIPUSH, SIPUSH or NEWARRAY.
     * @pbrbm opfrbnd
     *            tif opfrbnd of tif instrudtion to bf visitfd.<br>
     *            Wifn opdodf is BIPUSH, opfrbnd vbluf siould bf bftwffn
     *            Bytf.MIN_VALUE bnd Bytf.MAX_VALUE.<br>
     *            Wifn opdodf is SIPUSH, opfrbnd vbluf siould bf bftwffn
     *            Siort.MIN_VALUE bnd Siort.MAX_VALUE.<br>
     *            Wifn opdodf is NEWARRAY, opfrbnd vbluf siould bf onf of
     *            {@link Opdodfs#T_BOOLEAN}, {@link Opdodfs#T_CHAR},
     *            {@link Opdodfs#T_FLOAT}, {@link Opdodfs#T_DOUBLE},
     *            {@link Opdodfs#T_BYTE}, {@link Opdodfs#T_SHORT},
     *            {@link Opdodfs#T_INT} or {@link Opdodfs#T_LONG}.
     */
    publid void visitIntInsn(int opdodf, int opfrbnd) {
        if (mv != null) {
            mv.visitIntInsn(opdodf, opfrbnd);
        }
    }

    /**
     * Visits b lodbl vbribblf instrudtion. A lodbl vbribblf instrudtion is bn
     * instrudtion tibt lobds or storfs tif vbluf of b lodbl vbribblf.
     *
     * @pbrbm opdodf
     *            tif opdodf of tif lodbl vbribblf instrudtion to bf visitfd.
     *            Tiis opdodf is fitifr ILOAD, LLOAD, FLOAD, DLOAD, ALOAD,
     *            ISTORE, LSTORE, FSTORE, DSTORE, ASTORE or RET.
     * @pbrbm vbr
     *            tif opfrbnd of tif instrudtion to bf visitfd. Tiis opfrbnd is
     *            tif indfx of b lodbl vbribblf.
     */
    publid void visitVbrInsn(int opdodf, int vbr) {
        if (mv != null) {
            mv.visitVbrInsn(opdodf, vbr);
        }
    }

    /**
     * Visits b typf instrudtion. A typf instrudtion is bn instrudtion tibt
     * tbkfs tif intfrnbl nbmf of b dlbss bs pbrbmftfr.
     *
     * @pbrbm opdodf
     *            tif opdodf of tif typf instrudtion to bf visitfd. Tiis opdodf
     *            is fitifr NEW, ANEWARRAY, CHECKCAST or INSTANCEOF.
     * @pbrbm typf
     *            tif opfrbnd of tif instrudtion to bf visitfd. Tiis opfrbnd
     *            must bf tif intfrnbl nbmf of bn objfdt or brrby dlbss (sff
     *            {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}).
     */
    publid void visitTypfInsn(int opdodf, String typf) {
        if (mv != null) {
            mv.visitTypfInsn(opdodf, typf);
        }
    }

    /**
     * Visits b fifld instrudtion. A fifld instrudtion is bn instrudtion tibt
     * lobds or storfs tif vbluf of b fifld of bn objfdt.
     *
     * @pbrbm opdodf
     *            tif opdodf of tif typf instrudtion to bf visitfd. Tiis opdodf
     *            is fitifr GETSTATIC, PUTSTATIC, GETFIELD or PUTFIELD.
     * @pbrbm ownfr
     *            tif intfrnbl nbmf of tif fifld's ownfr dlbss (sff
     *            {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}).
     * @pbrbm nbmf
     *            tif fifld's nbmf.
     * @pbrbm dfsd
     *            tif fifld's dfsdriptor (sff {@link Typf Typf}).
     */
    publid void visitFifldInsn(int opdodf, String ownfr, String nbmf,
            String dfsd) {
        if (mv != null) {
            mv.visitFifldInsn(opdodf, ownfr, nbmf, dfsd);
        }
    }

    /**
     * Visits b mftiod instrudtion. A mftiod instrudtion is bn instrudtion tibt
     * invokfs b mftiod.
     *
     * @pbrbm opdodf
     *            tif opdodf of tif typf instrudtion to bf visitfd. Tiis opdodf
     *            is fitifr INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or
     *            INVOKEINTERFACE.
     * @pbrbm ownfr
     *            tif intfrnbl nbmf of tif mftiod's ownfr dlbss (sff
     *            {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}).
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor (sff {@link Typf Typf}).
     */
    @Dfprfdbtfd
    publid void visitMftiodInsn(int opdodf, String ownfr, String nbmf,
            String dfsd) {
        if (bpi >= Opdodfs.ASM5) {
            boolfbn itf = opdodf == Opdodfs.INVOKEINTERFACE;
            visitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
            rfturn;
        }
        if (mv != null) {
            mv.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd);
        }
    }

    /**
     * Visits b mftiod instrudtion. A mftiod instrudtion is bn instrudtion tibt
     * invokfs b mftiod.
     *
     * @pbrbm opdodf
     *            tif opdodf of tif typf instrudtion to bf visitfd. Tiis opdodf
     *            is fitifr INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or
     *            INVOKEINTERFACE.
     * @pbrbm ownfr
     *            tif intfrnbl nbmf of tif mftiod's ownfr dlbss (sff
     *            {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}).
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor (sff {@link Typf Typf}).
     * @pbrbm itf
     *            if tif mftiod's ownfr dlbss is bn intfrfbdf.
     */
    publid void visitMftiodInsn(int opdodf, String ownfr, String nbmf,
            String dfsd, boolfbn itf) {
        if (bpi < Opdodfs.ASM5) {
            if (itf != (opdodf == Opdodfs.INVOKEINTERFACE)) {
                tirow nfw IllfgblArgumfntExdfption(
                        "INVOKESPECIAL/STATIC on intfrfbdfs rfquirf ASM 5");
            }
            visitMftiodInsn(opdodf, ownfr, nbmf, dfsd);
            rfturn;
        }
        if (mv != null) {
            mv.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
        }
    }

    /**
     * Visits bn invokfdynbmid instrudtion.
     *
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor (sff {@link Typf Typf}).
     * @pbrbm bsm
     *            tif bootstrbp mftiod.
     * @pbrbm bsmArgs
     *            tif bootstrbp mftiod donstbnt brgumfnts. Ebdi brgumfnt must bf
     *            bn {@link Intfgfr}, {@link Flobt}, {@link Long},
     *            {@link Doublf}, {@link String}, {@link Typf} or {@link Hbndlf}
     *            vbluf. Tiis mftiod is bllowfd to modify tif dontfnt of tif
     *            brrby so b dbllfr siould fxpfdt tibt tiis brrby mby dibngf.
     */
    publid void visitInvokfDynbmidInsn(String nbmf, String dfsd, Hbndlf bsm,
            Objfdt... bsmArgs) {
        if (mv != null) {
            mv.visitInvokfDynbmidInsn(nbmf, dfsd, bsm, bsmArgs);
        }
    }

    /**
     * Visits b jump instrudtion. A jump instrudtion is bn instrudtion tibt mby
     * jump to bnotifr instrudtion.
     *
     * @pbrbm opdodf
     *            tif opdodf of tif typf instrudtion to bf visitfd. Tiis opdodf
     *            is fitifr IFEQ, IFNE, IFLT, IFGE, IFGT, IFLE, IF_ICMPEQ,
     *            IF_ICMPNE, IF_ICMPLT, IF_ICMPGE, IF_ICMPGT, IF_ICMPLE,
     *            IF_ACMPEQ, IF_ACMPNE, GOTO, JSR, IFNULL or IFNONNULL.
     * @pbrbm lbbfl
     *            tif opfrbnd of tif instrudtion to bf visitfd. Tiis opfrbnd is
     *            b lbbfl tibt dfsignbtfs tif instrudtion to wiidi tif jump
     *            instrudtion mby jump.
     */
    publid void visitJumpInsn(int opdodf, Lbbfl lbbfl) {
        if (mv != null) {
            mv.visitJumpInsn(opdodf, lbbfl);
        }
    }

    /**
     * Visits b lbbfl. A lbbfl dfsignbtfs tif instrudtion tibt will bf visitfd
     * just bftfr it.
     *
     * @pbrbm lbbfl
     *            b {@link Lbbfl Lbbfl} objfdt.
     */
    publid void visitLbbfl(Lbbfl lbbfl) {
        if (mv != null) {
            mv.visitLbbfl(lbbfl);
        }
    }

    // -------------------------------------------------------------------------
    // Spfdibl instrudtions
    // -------------------------------------------------------------------------

    /**
     * Visits b LDC instrudtion. Notf tibt nfw donstbnt typfs mby bf bddfd in
     * futurf vfrsions of tif Jbvb Virtubl Mbdiinf. To fbsily dftfdt nfw
     * donstbnt typfs, implfmfntbtions of tiis mftiod siould difdk for
     * unfxpfdtfd donstbnt typfs, likf tiis:
     *
     * <prf>
     * if (dst instbndfof Intfgfr) {
     *     // ...
     * } flsf if (dst instbndfof Flobt) {
     *     // ...
     * } flsf if (dst instbndfof Long) {
     *     // ...
     * } flsf if (dst instbndfof Doublf) {
     *     // ...
     * } flsf if (dst instbndfof String) {
     *     // ...
     * } flsf if (dst instbndfof Typf) {
     *     int sort = ((Typf) dst).gftSort();
     *     if (sort == Typf.OBJECT) {
     *         // ...
     *     } flsf if (sort == Typf.ARRAY) {
     *         // ...
     *     } flsf if (sort == Typf.METHOD) {
     *         // ...
     *     } flsf {
     *         // tirow bn fxdfption
     *     }
     * } flsf if (dst instbndfof Hbndlf) {
     *     // ...
     * } flsf {
     *     // tirow bn fxdfption
     * }
     * </prf>
     *
     * @pbrbm dst
     *            tif donstbnt to bf lobdfd on tif stbdk. Tiis pbrbmftfr must bf
     *            b non null {@link Intfgfr}, b {@link Flobt}, b {@link Long}, b
     *            {@link Doublf}, b {@link String}, b {@link Typf} of OBJECT or
     *            ARRAY sort for <tt>.dlbss</tt> donstbnts, for dlbssfs wiosf
     *            vfrsion is 49.0, b {@link Typf} of METHOD sort or b
     *            {@link Hbndlf} for MftiodTypf bnd MftiodHbndlf donstbnts, for
     *            dlbssfs wiosf vfrsion is 51.0.
     */
    publid void visitLddInsn(Objfdt dst) {
        if (mv != null) {
            mv.visitLddInsn(dst);
        }
    }

    /**
     * Visits bn IINC instrudtion.
     *
     * @pbrbm vbr
     *            indfx of tif lodbl vbribblf to bf indrfmfntfd.
     * @pbrbm indrfmfnt
     *            bmount to indrfmfnt tif lodbl vbribblf by.
     */
    publid void visitIindInsn(int vbr, int indrfmfnt) {
        if (mv != null) {
            mv.visitIindInsn(vbr, indrfmfnt);
        }
    }

    /**
     * Visits b TABLESWITCH instrudtion.
     *
     * @pbrbm min
     *            tif minimum kfy vbluf.
     * @pbrbm mbx
     *            tif mbximum kfy vbluf.
     * @pbrbm dflt
     *            bfginning of tif dffbult ibndlfr blodk.
     * @pbrbm lbbfls
     *            bfginnings of tif ibndlfr blodks. <tt>lbbfls[i]</tt> is tif
     *            bfginning of tif ibndlfr blodk for tif <tt>min + i</tt> kfy.
     */
    publid void visitTbblfSwitdiInsn(int min, int mbx, Lbbfl dflt,
            Lbbfl... lbbfls) {
        if (mv != null) {
            mv.visitTbblfSwitdiInsn(min, mbx, dflt, lbbfls);
        }
    }

    /**
     * Visits b LOOKUPSWITCH instrudtion.
     *
     * @pbrbm dflt
     *            bfginning of tif dffbult ibndlfr blodk.
     * @pbrbm kfys
     *            tif vblufs of tif kfys.
     * @pbrbm lbbfls
     *            bfginnings of tif ibndlfr blodks. <tt>lbbfls[i]</tt> is tif
     *            bfginning of tif ibndlfr blodk for tif <tt>kfys[i]</tt> kfy.
     */
    publid void visitLookupSwitdiInsn(Lbbfl dflt, int[] kfys, Lbbfl[] lbbfls) {
        if (mv != null) {
            mv.visitLookupSwitdiInsn(dflt, kfys, lbbfls);
        }
    }

    /**
     * Visits b MULTIANEWARRAY instrudtion.
     *
     * @pbrbm dfsd
     *            bn brrby typf dfsdriptor (sff {@link Typf Typf}).
     * @pbrbm dims
     *            numbfr of dimfnsions of tif brrby to bllodbtf.
     */
    publid void visitMultiANfwArrbyInsn(String dfsd, int dims) {
        if (mv != null) {
            mv.visitMultiANfwArrbyInsn(dfsd, dims);
        }
    }

    /**
     * Visits bn bnnotbtion on bn instrudtion. Tiis mftiod must bf dbllfd just
     * <i>bftfr</i> tif bnnotbtfd instrudtion. It dbn bf dbllfd sfvfrbl timfs
     * for tif sbmf instrudtion.
     *
     * @pbrbm typfRff
     *            b rfffrfndf to tif bnnotbtfd typf. Tif sort of tiis typf
     *            rfffrfndf must bf {@link TypfRfffrfndf#INSTANCEOF INSTANCEOF},
     *            {@link TypfRfffrfndf#NEW NEW},
     *            {@link TypfRfffrfndf#CONSTRUCTOR_REFERENCE
     *            CONSTRUCTOR_REFERENCE}, {@link TypfRfffrfndf#METHOD_REFERENCE
     *            METHOD_REFERENCE}, {@link TypfRfffrfndf#CAST CAST},
     *            {@link TypfRfffrfndf#CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
     *            CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT},
     *            {@link TypfRfffrfndf#METHOD_INVOCATION_TYPE_ARGUMENT
     *            METHOD_INVOCATION_TYPE_ARGUMENT},
     *            {@link TypfRfffrfndf#CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
     *            CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT}, or
     *            {@link TypfRfffrfndf#METHOD_REFERENCE_TYPE_ARGUMENT
     *            METHOD_REFERENCE_TYPE_ARGUMENT}. Sff {@link TypfRfffrfndf}.
     * @pbrbm typfPbti
     *            tif pbti to tif bnnotbtfd typf brgumfnt, wilddbrd bound, brrby
     *            flfmfnt typf, or stbtid innfr typf witiin 'typfRff'. Mby bf
     *            <tt>null</tt> if tif bnnotbtion tbrgfts 'typfRff' bs b wiolf.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     * @pbrbm visiblf
     *            <tt>truf</tt> if tif bnnotbtion is visiblf bt runtimf.
     * @rfturn b visitor to visit tif bnnotbtion vblufs, or <tt>null</tt> if
     *         tiis visitor is not intfrfstfd in visiting tiis bnnotbtion.
     */
    publid AnnotbtionVisitor visitInsnAnnotbtion(int typfRff,
            TypfPbti typfPbti, String dfsd, boolfbn visiblf) {
        if (bpi < Opdodfs.ASM5) {
            tirow nfw RuntimfExdfption();
        }
        if (mv != null) {
            rfturn mv.visitInsnAnnotbtion(typfRff, typfPbti, dfsd, visiblf);
        }
        rfturn null;
    }

    // -------------------------------------------------------------------------
    // Exdfptions tbblf fntrifs, dfbug informbtion, mbx stbdk bnd mbx lodbls
    // -------------------------------------------------------------------------

    /**
     * Visits b try dbtdi blodk.
     *
     * @pbrbm stbrt
     *            bfginning of tif fxdfption ibndlfr's sdopf (indlusivf).
     * @pbrbm fnd
     *            fnd of tif fxdfption ibndlfr's sdopf (fxdlusivf).
     * @pbrbm ibndlfr
     *            bfginning of tif fxdfption ibndlfr's dodf.
     * @pbrbm typf
     *            intfrnbl nbmf of tif typf of fxdfptions ibndlfd by tif
     *            ibndlfr, or <tt>null</tt> to dbtdi bny fxdfptions (for
     *            "finblly" blodks).
     * @tirows IllfgblArgumfntExdfption
     *             if onf of tif lbbfls ibs blrfbdy bffn visitfd by tiis visitor
     *             (by tif {@link #visitLbbfl visitLbbfl} mftiod).
     */
    publid void visitTryCbtdiBlodk(Lbbfl stbrt, Lbbfl fnd, Lbbfl ibndlfr,
            String typf) {
        if (mv != null) {
            mv.visitTryCbtdiBlodk(stbrt, fnd, ibndlfr, typf);
        }
    }

    /**
     * Visits bn bnnotbtion on bn fxdfption ibndlfr typf. Tiis mftiod must bf
     * dbllfd <i>bftfr</i> tif {@link #visitTryCbtdiBlodk} for tif bnnotbtfd
     * fxdfption ibndlfr. It dbn bf dbllfd sfvfrbl timfs for tif sbmf fxdfption
     * ibndlfr.
     *
     * @pbrbm typfRff
     *            b rfffrfndf to tif bnnotbtfd typf. Tif sort of tiis typf
     *            rfffrfndf must bf {@link TypfRfffrfndf#EXCEPTION_PARAMETER
     *            EXCEPTION_PARAMETER}. Sff {@link TypfRfffrfndf}.
     * @pbrbm typfPbti
     *            tif pbti to tif bnnotbtfd typf brgumfnt, wilddbrd bound, brrby
     *            flfmfnt typf, or stbtid innfr typf witiin 'typfRff'. Mby bf
     *            <tt>null</tt> if tif bnnotbtion tbrgfts 'typfRff' bs b wiolf.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     * @pbrbm visiblf
     *            <tt>truf</tt> if tif bnnotbtion is visiblf bt runtimf.
     * @rfturn b visitor to visit tif bnnotbtion vblufs, or <tt>null</tt> if
     *         tiis visitor is not intfrfstfd in visiting tiis bnnotbtion.
     */
    publid AnnotbtionVisitor visitTryCbtdiAnnotbtion(int typfRff,
            TypfPbti typfPbti, String dfsd, boolfbn visiblf) {
        if (bpi < Opdodfs.ASM5) {
            tirow nfw RuntimfExdfption();
        }
        if (mv != null) {
            rfturn mv.visitTryCbtdiAnnotbtion(typfRff, typfPbti, dfsd, visiblf);
        }
        rfturn null;
    }

    /**
     * Visits b lodbl vbribblf dfdlbrbtion.
     *
     * @pbrbm nbmf
     *            tif nbmf of b lodbl vbribblf.
     * @pbrbm dfsd
     *            tif typf dfsdriptor of tiis lodbl vbribblf.
     * @pbrbm signbturf
     *            tif typf signbturf of tiis lodbl vbribblf. Mby bf
     *            <tt>null</tt> if tif lodbl vbribblf typf dofs not usf gfnfrid
     *            typfs.
     * @pbrbm stbrt
     *            tif first instrudtion dorrfsponding to tif sdopf of tiis lodbl
     *            vbribblf (indlusivf).
     * @pbrbm fnd
     *            tif lbst instrudtion dorrfsponding to tif sdopf of tiis lodbl
     *            vbribblf (fxdlusivf).
     * @pbrbm indfx
     *            tif lodbl vbribblf's indfx.
     * @tirows IllfgblArgumfntExdfption
     *             if onf of tif lbbfls ibs not blrfbdy bffn visitfd by tiis
     *             visitor (by tif {@link #visitLbbfl visitLbbfl} mftiod).
     */
    publid void visitLodblVbribblf(String nbmf, String dfsd, String signbturf,
            Lbbfl stbrt, Lbbfl fnd, int indfx) {
        if (mv != null) {
            mv.visitLodblVbribblf(nbmf, dfsd, signbturf, stbrt, fnd, indfx);
        }
    }

    /**
     * Visits bn bnnotbtion on b lodbl vbribblf typf.
     *
     * @pbrbm typfRff
     *            b rfffrfndf to tif bnnotbtfd typf. Tif sort of tiis typf
     *            rfffrfndf must bf {@link TypfRfffrfndf#LOCAL_VARIABLE
     *            LOCAL_VARIABLE} or {@link TypfRfffrfndf#RESOURCE_VARIABLE
     *            RESOURCE_VARIABLE}. Sff {@link TypfRfffrfndf}.
     * @pbrbm typfPbti
     *            tif pbti to tif bnnotbtfd typf brgumfnt, wilddbrd bound, brrby
     *            flfmfnt typf, or stbtid innfr typf witiin 'typfRff'. Mby bf
     *            <tt>null</tt> if tif bnnotbtion tbrgfts 'typfRff' bs b wiolf.
     * @pbrbm stbrt
     *            tif fist instrudtions dorrfsponding to tif dontinuous rbngfs
     *            tibt mbkf tif sdopf of tiis lodbl vbribblf (indlusivf).
     * @pbrbm fnd
     *            tif lbst instrudtions dorrfsponding to tif dontinuous rbngfs
     *            tibt mbkf tif sdopf of tiis lodbl vbribblf (fxdlusivf). Tiis
     *            brrby must ibvf tif sbmf sizf bs tif 'stbrt' brrby.
     * @pbrbm indfx
     *            tif lodbl vbribblf's indfx in fbdi rbngf. Tiis brrby must ibvf
     *            tif sbmf sizf bs tif 'stbrt' brrby.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     * @pbrbm visiblf
     *            <tt>truf</tt> if tif bnnotbtion is visiblf bt runtimf.
     * @rfturn b visitor to visit tif bnnotbtion vblufs, or <tt>null</tt> if
     *         tiis visitor is not intfrfstfd in visiting tiis bnnotbtion.
     */
    publid AnnotbtionVisitor visitLodblVbribblfAnnotbtion(int typfRff,
            TypfPbti typfPbti, Lbbfl[] stbrt, Lbbfl[] fnd, int[] indfx,
            String dfsd, boolfbn visiblf) {
        if (bpi < Opdodfs.ASM5) {
            tirow nfw RuntimfExdfption();
        }
        if (mv != null) {
            rfturn mv.visitLodblVbribblfAnnotbtion(typfRff, typfPbti, stbrt,
                    fnd, indfx, dfsd, visiblf);
        }
        rfturn null;
    }

    /**
     * Visits b linf numbfr dfdlbrbtion.
     *
     * @pbrbm linf
     *            b linf numbfr. Tiis numbfr rfffrs to tif sourdf filf from
     *            wiidi tif dlbss wbs dompilfd.
     * @pbrbm stbrt
     *            tif first instrudtion dorrfsponding to tiis linf numbfr.
     * @tirows IllfgblArgumfntExdfption
     *             if <tt>stbrt</tt> ibs not blrfbdy bffn visitfd by tiis
     *             visitor (by tif {@link #visitLbbfl visitLbbfl} mftiod).
     */
    publid void visitLinfNumbfr(int linf, Lbbfl stbrt) {
        if (mv != null) {
            mv.visitLinfNumbfr(linf, stbrt);
        }
    }

    /**
     * Visits tif mbximum stbdk sizf bnd tif mbximum numbfr of lodbl vbribblfs
     * of tif mftiod.
     *
     * @pbrbm mbxStbdk
     *            mbximum stbdk sizf of tif mftiod.
     * @pbrbm mbxLodbls
     *            mbximum numbfr of lodbl vbribblfs for tif mftiod.
     */
    publid void visitMbxs(int mbxStbdk, int mbxLodbls) {
        if (mv != null) {
            mv.visitMbxs(mbxStbdk, mbxLodbls);
        }
    }

    /**
     * Visits tif fnd of tif mftiod. Tiis mftiod, wiidi is tif lbst onf to bf
     * dbllfd, is usfd to inform tif visitor tibt bll tif bnnotbtions bnd
     * bttributfs of tif mftiod ibvf bffn visitfd.
     */
    publid void visitEnd() {
        if (mv != null) {
            mv.visitEnd();
        }
    }
}
