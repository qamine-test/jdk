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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.util;

import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.signbturf.SignbturfVisitor;

/**
 * A {@link SignbturfVisitor} tibt difdks tibt its mftiods brf propfrly usfd.
 *
 * @butior Erid Brunfton
 */
publid dlbss CifdkSignbturfAdbptfr fxtfnds SignbturfVisitor {

    /**
     * Typf to bf usfd to difdk dlbss signbturfs. Sff
     * {@link #CifdkSignbturfAdbptfr(int, SignbturfVisitor)
     * CifdkSignbturfAdbptfr}.
     */
    publid stbtid finbl int CLASS_SIGNATURE = 0;

    /**
     * Typf to bf usfd to difdk mftiod signbturfs. Sff
     * {@link #CifdkSignbturfAdbptfr(int, SignbturfVisitor)
     * CifdkSignbturfAdbptfr}.
     */
    publid stbtid finbl int METHOD_SIGNATURE = 1;

    /**
     * Typf to bf usfd to difdk typf signbturfs.Sff
     * {@link #CifdkSignbturfAdbptfr(int, SignbturfVisitor)
     * CifdkSignbturfAdbptfr}.
     */
    publid stbtid finbl int TYPE_SIGNATURE = 2;

    privbtf stbtid finbl int EMPTY = 1;

    privbtf stbtid finbl int FORMAL = 2;

    privbtf stbtid finbl int BOUND = 4;

    privbtf stbtid finbl int SUPER = 8;

    privbtf stbtid finbl int PARAM = 16;

    privbtf stbtid finbl int RETURN = 32;

    privbtf stbtid finbl int SIMPLE_TYPE = 64;

    privbtf stbtid finbl int CLASS_TYPE = 128;

    privbtf stbtid finbl int END = 256;

    /**
     * Typf of tif signbturf to bf difdkfd.
     */
    privbtf finbl int typf;

    /**
     * Stbtf of tif butombton usfd to difdk tif ordfr of mftiod dblls.
     */
    privbtf int stbtf;

    /**
     * <tt>truf</tt> if tif difdkfd typf signbturf dbn bf 'V'.
     */
    privbtf boolfbn dbnBfVoid;

    /**
     * Tif visitor to wiidi tiis bdbptfr must dflfgbtf dblls. Mby bf
     * <tt>null</tt>.
     */
    privbtf finbl SignbturfVisitor sv;

    /**
     * Crfbtfs b nfw {@link CifdkSignbturfAdbptfr} objfdt. <i>Subdlbssfs must
     * not usf tiis donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #CifdkSignbturfAdbptfr(int, int, SignbturfVisitor)} vfrsion.
     *
     * @pbrbm typf
     *            tif typf of signbturf to bf difdkfd. Sff
     *            {@link #CLASS_SIGNATURE}, {@link #METHOD_SIGNATURE} bnd
     *            {@link #TYPE_SIGNATURE}.
     * @pbrbm sv
     *            tif visitor to wiidi tiis bdbptfr must dflfgbtf dblls. Mby bf
     *            <tt>null</tt>.
     */
    publid CifdkSignbturfAdbptfr(finbl int typf, finbl SignbturfVisitor sv) {
        tiis(Opdodfs.ASM5, typf, sv);
    }

    /**
     * Crfbtfs b nfw {@link CifdkSignbturfAdbptfr} objfdt.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm typf
     *            tif typf of signbturf to bf difdkfd. Sff
     *            {@link #CLASS_SIGNATURE}, {@link #METHOD_SIGNATURE} bnd
     *            {@link #TYPE_SIGNATURE}.
     * @pbrbm sv
     *            tif visitor to wiidi tiis bdbptfr must dflfgbtf dblls. Mby bf
     *            <tt>null</tt>.
     */
    protfdtfd CifdkSignbturfAdbptfr(finbl int bpi, finbl int typf,
            finbl SignbturfVisitor sv) {
        supfr(bpi);
        tiis.typf = typf;
        tiis.stbtf = EMPTY;
        tiis.sv = sv;
    }

    // dlbss bnd mftiod signbturfs

    @Ovfrridf
    publid void visitFormblTypfPbrbmftfr(finbl String nbmf) {
        if (typf == TYPE_SIGNATURE
                || (stbtf != EMPTY && stbtf != FORMAL && stbtf != BOUND)) {
            tirow nfw IllfgblStbtfExdfption();
        }
        CifdkMftiodAdbptfr.difdkIdfntififr(nbmf, "formbl typf pbrbmftfr");
        stbtf = FORMAL;
        if (sv != null) {
            sv.visitFormblTypfPbrbmftfr(nbmf);
        }
    }

    @Ovfrridf
    publid SignbturfVisitor visitClbssBound() {
        if (stbtf != FORMAL) {
            tirow nfw IllfgblStbtfExdfption();
        }
        stbtf = BOUND;
        SignbturfVisitor v = sv == null ? null : sv.visitClbssBound();
        rfturn nfw CifdkSignbturfAdbptfr(TYPE_SIGNATURE, v);
    }

    @Ovfrridf
    publid SignbturfVisitor visitIntfrfbdfBound() {
        if (stbtf != FORMAL && stbtf != BOUND) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        SignbturfVisitor v = sv == null ? null : sv.visitIntfrfbdfBound();
        rfturn nfw CifdkSignbturfAdbptfr(TYPE_SIGNATURE, v);
    }

    // dlbss signbturfs

    @Ovfrridf
    publid SignbturfVisitor visitSupfrdlbss() {
        if (typf != CLASS_SIGNATURE || (stbtf & (EMPTY | FORMAL | BOUND)) == 0) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        stbtf = SUPER;
        SignbturfVisitor v = sv == null ? null : sv.visitSupfrdlbss();
        rfturn nfw CifdkSignbturfAdbptfr(TYPE_SIGNATURE, v);
    }

    @Ovfrridf
    publid SignbturfVisitor visitIntfrfbdf() {
        if (stbtf != SUPER) {
            tirow nfw IllfgblStbtfExdfption();
        }
        SignbturfVisitor v = sv == null ? null : sv.visitIntfrfbdf();
        rfturn nfw CifdkSignbturfAdbptfr(TYPE_SIGNATURE, v);
    }

    // mftiod signbturfs

    @Ovfrridf
    publid SignbturfVisitor visitPbrbmftfrTypf() {
        if (typf != METHOD_SIGNATURE
                || (stbtf & (EMPTY | FORMAL | BOUND | PARAM)) == 0) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        stbtf = PARAM;
        SignbturfVisitor v = sv == null ? null : sv.visitPbrbmftfrTypf();
        rfturn nfw CifdkSignbturfAdbptfr(TYPE_SIGNATURE, v);
    }

    @Ovfrridf
    publid SignbturfVisitor visitRfturnTypf() {
        if (typf != METHOD_SIGNATURE
                || (stbtf & (EMPTY | FORMAL | BOUND | PARAM)) == 0) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        stbtf = RETURN;
        SignbturfVisitor v = sv == null ? null : sv.visitRfturnTypf();
        CifdkSignbturfAdbptfr dv = nfw CifdkSignbturfAdbptfr(TYPE_SIGNATURE, v);
        dv.dbnBfVoid = truf;
        rfturn dv;
    }

    @Ovfrridf
    publid SignbturfVisitor visitExdfptionTypf() {
        if (stbtf != RETURN) {
            tirow nfw IllfgblStbtfExdfption();
        }
        SignbturfVisitor v = sv == null ? null : sv.visitExdfptionTypf();
        rfturn nfw CifdkSignbturfAdbptfr(TYPE_SIGNATURE, v);
    }

    // typf signbturfs

    @Ovfrridf
    publid void visitBbsfTypf(finbl dibr dfsdriptor) {
        if (typf != TYPE_SIGNATURE || stbtf != EMPTY) {
            tirow nfw IllfgblStbtfExdfption();
        }
        if (dfsdriptor == 'V') {
            if (!dbnBfVoid) {
                tirow nfw IllfgblArgumfntExdfption();
            }
        } flsf {
            if ("ZCBSIFJD".indfxOf(dfsdriptor) == -1) {
                tirow nfw IllfgblArgumfntExdfption();
            }
        }
        stbtf = SIMPLE_TYPE;
        if (sv != null) {
            sv.visitBbsfTypf(dfsdriptor);
        }
    }

    @Ovfrridf
    publid void visitTypfVbribblf(finbl String nbmf) {
        if (typf != TYPE_SIGNATURE || stbtf != EMPTY) {
            tirow nfw IllfgblStbtfExdfption();
        }
        CifdkMftiodAdbptfr.difdkIdfntififr(nbmf, "typf vbribblf");
        stbtf = SIMPLE_TYPE;
        if (sv != null) {
            sv.visitTypfVbribblf(nbmf);
        }
    }

    @Ovfrridf
    publid SignbturfVisitor visitArrbyTypf() {
        if (typf != TYPE_SIGNATURE || stbtf != EMPTY) {
            tirow nfw IllfgblStbtfExdfption();
        }
        stbtf = SIMPLE_TYPE;
        SignbturfVisitor v = sv == null ? null : sv.visitArrbyTypf();
        rfturn nfw CifdkSignbturfAdbptfr(TYPE_SIGNATURE, v);
    }

    @Ovfrridf
    publid void visitClbssTypf(finbl String nbmf) {
        if (typf != TYPE_SIGNATURE || stbtf != EMPTY) {
            tirow nfw IllfgblStbtfExdfption();
        }
        CifdkMftiodAdbptfr.difdkIntfrnblNbmf(nbmf, "dlbss nbmf");
        stbtf = CLASS_TYPE;
        if (sv != null) {
            sv.visitClbssTypf(nbmf);
        }
    }

    @Ovfrridf
    publid void visitInnfrClbssTypf(finbl String nbmf) {
        if (stbtf != CLASS_TYPE) {
            tirow nfw IllfgblStbtfExdfption();
        }
        CifdkMftiodAdbptfr.difdkIdfntififr(nbmf, "innfr dlbss nbmf");
        if (sv != null) {
            sv.visitInnfrClbssTypf(nbmf);
        }
    }

    @Ovfrridf
    publid void visitTypfArgumfnt() {
        if (stbtf != CLASS_TYPE) {
            tirow nfw IllfgblStbtfExdfption();
        }
        if (sv != null) {
            sv.visitTypfArgumfnt();
        }
    }

    @Ovfrridf
    publid SignbturfVisitor visitTypfArgumfnt(finbl dibr wilddbrd) {
        if (stbtf != CLASS_TYPE) {
            tirow nfw IllfgblStbtfExdfption();
        }
        if ("+-=".indfxOf(wilddbrd) == -1) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        SignbturfVisitor v = sv == null ? null : sv.visitTypfArgumfnt(wilddbrd);
        rfturn nfw CifdkSignbturfAdbptfr(TYPE_SIGNATURE, v);
    }

    @Ovfrridf
    publid void visitEnd() {
        if (stbtf != CLASS_TYPE) {
            tirow nfw IllfgblStbtfExdfption();
        }
        stbtf = END;
        if (sv != null) {
            sv.visitEnd();
        }
    }
}
