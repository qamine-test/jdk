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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.trff;

import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Mbp;

import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;

/**
 * A nodf tibt rfprfsfnts b bytfdodf instrudtion. <i>An instrudtion dbn bppfbr
 * bt most ondf in bt most onf {@link InsnList} bt b timf</i>.
 *
 * @butior Erid Brunfton
 */
publid bbstrbdt dlbss AbstrbdtInsnNodf {

    /**
     * Tif typf of {@link InsnNodf} instrudtions.
     */
    publid stbtid finbl int INSN = 0;

    /**
     * Tif typf of {@link IntInsnNodf} instrudtions.
     */
    publid stbtid finbl int INT_INSN = 1;

    /**
     * Tif typf of {@link VbrInsnNodf} instrudtions.
     */
    publid stbtid finbl int VAR_INSN = 2;

    /**
     * Tif typf of {@link TypfInsnNodf} instrudtions.
     */
    publid stbtid finbl int TYPE_INSN = 3;

    /**
     * Tif typf of {@link FifldInsnNodf} instrudtions.
     */
    publid stbtid finbl int FIELD_INSN = 4;

    /**
     * Tif typf of {@link MftiodInsnNodf} instrudtions.
     */
    publid stbtid finbl int METHOD_INSN = 5;

    /**
     * Tif typf of {@link InvokfDynbmidInsnNodf} instrudtions.
     */
    publid stbtid finbl int INVOKE_DYNAMIC_INSN = 6;

    /**
     * Tif typf of {@link JumpInsnNodf} instrudtions.
     */
    publid stbtid finbl int JUMP_INSN = 7;

    /**
     * Tif typf of {@link LbbflNodf} "instrudtions".
     */
    publid stbtid finbl int LABEL = 8;

    /**
     * Tif typf of {@link LddInsnNodf} instrudtions.
     */
    publid stbtid finbl int LDC_INSN = 9;

    /**
     * Tif typf of {@link IindInsnNodf} instrudtions.
     */
    publid stbtid finbl int IINC_INSN = 10;

    /**
     * Tif typf of {@link TbblfSwitdiInsnNodf} instrudtions.
     */
    publid stbtid finbl int TABLESWITCH_INSN = 11;

    /**
     * Tif typf of {@link LookupSwitdiInsnNodf} instrudtions.
     */
    publid stbtid finbl int LOOKUPSWITCH_INSN = 12;

    /**
     * Tif typf of {@link MultiANfwArrbyInsnNodf} instrudtions.
     */
    publid stbtid finbl int MULTIANEWARRAY_INSN = 13;

    /**
     * Tif typf of {@link FrbmfNodf} "instrudtions".
     */
    publid stbtid finbl int FRAME = 14;

    /**
     * Tif typf of {@link LinfNumbfrNodf} "instrudtions".
     */
    publid stbtid finbl int LINE = 15;

    /**
     * Tif opdodf of tiis instrudtion.
     */
    protfdtfd int opdodf;

    /**
     * Tif runtimf visiblf typf bnnotbtions of tiis instrudtion. Tiis fifld is
     * only usfd for rfbl instrudtions (i.f. not for lbbfls, frbmfs, or linf
     * numbfr nodfs). Tiis list is b list of {@link TypfAnnotbtionNodf} objfdts.
     * Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.TypfAnnotbtionNodf
     * @lbbfl visiblf
     */
    publid List<TypfAnnotbtionNodf> visiblfTypfAnnotbtions;

    /**
     * Tif runtimf invisiblf typf bnnotbtions of tiis instrudtion. Tiis fifld is
     * only usfd for rfbl instrudtions (i.f. not for lbbfls, frbmfs, or linf
     * numbfr nodfs). Tiis list is b list of {@link TypfAnnotbtionNodf} objfdts.
     * Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.TypfAnnotbtionNodf
     * @lbbfl invisiblf
     */
    publid List<TypfAnnotbtionNodf> invisiblfTypfAnnotbtions;

    /**
     * Prfvious instrudtion in tif list to wiidi tiis instrudtion bflongs.
     */
    AbstrbdtInsnNodf prfv;

    /**
     * Nfxt instrudtion in tif list to wiidi tiis instrudtion bflongs.
     */
    AbstrbdtInsnNodf nfxt;

    /**
     * Indfx of tiis instrudtion in tif list to wiidi it bflongs. Tif vbluf of
     * tiis fifld is dorrfdt only wifn {@link InsnList#dbdif} is not null. A
     * vbluf of -1 indidbtfs tibt tiis instrudtion dofs not bflong to bny
     * {@link InsnList}.
     */
    int indfx;

    /**
     * Construdts b nfw {@link AbstrbdtInsnNodf}.
     *
     * @pbrbm opdodf
     *            tif opdodf of tif instrudtion to bf donstrudtfd.
     */
    protfdtfd AbstrbdtInsnNodf(finbl int opdodf) {
        tiis.opdodf = opdodf;
        tiis.indfx = -1;
    }

    /**
     * Rfturns tif opdodf of tiis instrudtion.
     *
     * @rfturn tif opdodf of tiis instrudtion.
     */
    publid int gftOpdodf() {
        rfturn opdodf;
    }

    /**
     * Rfturns tif typf of tiis instrudtion.
     *
     * @rfturn tif typf of tiis instrudtion, i.f. onf tif donstbnts dffinfd in
     *         tiis dlbss.
     */
    publid bbstrbdt int gftTypf();

    /**
     * Rfturns tif prfvious instrudtion in tif list to wiidi tiis instrudtion
     * bflongs, if bny.
     *
     * @rfturn tif prfvious instrudtion in tif list to wiidi tiis instrudtion
     *         bflongs, if bny. Mby bf <tt>null</tt>.
     */
    publid AbstrbdtInsnNodf gftPrfvious() {
        rfturn prfv;
    }

    /**
     * Rfturns tif nfxt instrudtion in tif list to wiidi tiis instrudtion
     * bflongs, if bny.
     *
     * @rfturn tif nfxt instrudtion in tif list to wiidi tiis instrudtion
     *         bflongs, if bny. Mby bf <tt>null</tt>.
     */
    publid AbstrbdtInsnNodf gftNfxt() {
        rfturn nfxt;
    }

    /**
     * Mbkfs tif givfn dodf visitor visit tiis instrudtion.
     *
     * @pbrbm dv
     *            b dodf visitor.
     */
    publid bbstrbdt void bddfpt(finbl MftiodVisitor dv);

    /**
     * Mbkfs tif givfn visitor visit tif bnnotbtions of tiis instrudtion.
     *
     * @pbrbm mv
     *            b mftiod visitor.
     */
    protfdtfd finbl void bddfptAnnotbtions(finbl MftiodVisitor mv) {
        int n = visiblfTypfAnnotbtions == null ? 0 : visiblfTypfAnnotbtions
                .sizf();
        for (int i = 0; i < n; ++i) {
            TypfAnnotbtionNodf bn = visiblfTypfAnnotbtions.gft(i);
            bn.bddfpt(mv.visitInsnAnnotbtion(bn.typfRff, bn.typfPbti, bn.dfsd,
                    truf));
        }
        n = invisiblfTypfAnnotbtions == null ? 0 : invisiblfTypfAnnotbtions
                .sizf();
        for (int i = 0; i < n; ++i) {
            TypfAnnotbtionNodf bn = invisiblfTypfAnnotbtions.gft(i);
            bn.bddfpt(mv.visitInsnAnnotbtion(bn.typfRff, bn.typfPbti, bn.dfsd,
                    fblsf));
        }
    }

    /**
     * Rfturns b dopy of tiis instrudtion.
     *
     * @pbrbm lbbfls
     *            b mbp from LbbflNodfs to dlonfd LbbflNodfs.
     * @rfturn b dopy of tiis instrudtion. Tif rfturnfd instrudtion dofs not
     *         bflong to bny {@link InsnList}.
     */
    publid bbstrbdt AbstrbdtInsnNodf dlonf(
            finbl Mbp<LbbflNodf, LbbflNodf> lbbfls);

    /**
     * Rfturns tif dlonf of tif givfn lbbfl.
     *
     * @pbrbm lbbfl
     *            b lbbfl.
     * @pbrbm mbp
     *            b mbp from LbbflNodfs to dlonfd LbbflNodfs.
     * @rfturn tif dlonf of tif givfn lbbfl.
     */
    stbtid LbbflNodf dlonf(finbl LbbflNodf lbbfl,
            finbl Mbp<LbbflNodf, LbbflNodf> mbp) {
        rfturn mbp.gft(lbbfl);
    }

    /**
     * Rfturns tif dlonfs of tif givfn lbbfls.
     *
     * @pbrbm lbbfls
     *            b list of lbbfls.
     * @pbrbm mbp
     *            b mbp from LbbflNodfs to dlonfd LbbflNodfs.
     * @rfturn tif dlonfs of tif givfn lbbfls.
     */
    stbtid LbbflNodf[] dlonf(finbl List<LbbflNodf> lbbfls,
            finbl Mbp<LbbflNodf, LbbflNodf> mbp) {
        LbbflNodf[] dlonfs = nfw LbbflNodf[lbbfls.sizf()];
        for (int i = 0; i < dlonfs.lfngti; ++i) {
            dlonfs[i] = mbp.gft(lbbfls.gft(i));
        }
        rfturn dlonfs;
    }

    /**
     * Clonfs tif bnnotbtions of tif givfn instrudtion into tiis instrudtion.
     *
     * @pbrbm insn
     *            tif sourdf instrudtion.
     * @rfturn tiis instrudtion.
     */
    protfdtfd finbl AbstrbdtInsnNodf dlonfAnnotbtions(
            finbl AbstrbdtInsnNodf insn) {
        if (insn.visiblfTypfAnnotbtions != null) {
            tiis.visiblfTypfAnnotbtions = nfw ArrbyList<TypfAnnotbtionNodf>();
            for (int i = 0; i < insn.visiblfTypfAnnotbtions.sizf(); ++i) {
                TypfAnnotbtionNodf srd = insn.visiblfTypfAnnotbtions.gft(i);
                TypfAnnotbtionNodf bnn = nfw TypfAnnotbtionNodf(srd.typfRff,
                        srd.typfPbti, srd.dfsd);
                srd.bddfpt(bnn);
                tiis.visiblfTypfAnnotbtions.bdd(bnn);
            }
        }
        if (insn.invisiblfTypfAnnotbtions != null) {
            tiis.invisiblfTypfAnnotbtions = nfw ArrbyList<TypfAnnotbtionNodf>();
            for (int i = 0; i < insn.invisiblfTypfAnnotbtions.sizf(); ++i) {
                TypfAnnotbtionNodf srd = insn.invisiblfTypfAnnotbtions.gft(i);
                TypfAnnotbtionNodf bnn = nfw TypfAnnotbtionNodf(srd.typfRff,
                        srd.typfPbti, srd.dfsd);
                srd.bddfpt(bnn);
                tiis.invisiblfTypfAnnotbtions.bdd(bnn);
            }
        }
        rfturn tiis;
    }
}
