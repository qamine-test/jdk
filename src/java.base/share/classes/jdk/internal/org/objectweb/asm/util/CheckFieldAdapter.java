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

import jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Attributf;
import jdk.intfrnbl.org.objfdtwfb.bsm.FifldVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfRfffrfndf;

/**
 * A {@link FifldVisitor} tibt difdks tibt its mftiods brf propfrly usfd.
 */
publid dlbss CifdkFifldAdbptfr fxtfnds FifldVisitor {

    privbtf boolfbn fnd;

    /**
     * Construdts b nfw {@link CifdkFifldAdbptfr}. <i>Subdlbssfs must not usf
     * tiis donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #CifdkFifldAdbptfr(int, FifldVisitor)} vfrsion.
     *
     * @pbrbm fv
     *            tif fifld visitor to wiidi tiis bdbptfr must dflfgbtf dblls.
     * @tirows IllfgblStbtfExdfption
     *             If b subdlbss dblls tiis donstrudtor.
     */
    publid CifdkFifldAdbptfr(finbl FifldVisitor fv) {
        tiis(Opdodfs.ASM5, fv);
        if (gftClbss() != CifdkFifldAdbptfr.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Construdts b nfw {@link CifdkFifldAdbptfr}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm fv
     *            tif fifld visitor to wiidi tiis bdbptfr must dflfgbtf dblls.
     */
    protfdtfd CifdkFifldAdbptfr(finbl int bpi, finbl FifldVisitor fv) {
        supfr(bpi, fv);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf) {
        difdkEnd();
        CifdkMftiodAdbptfr.difdkDfsd(dfsd, fblsf);
        rfturn nfw CifdkAnnotbtionAdbptfr(supfr.visitAnnotbtion(dfsd, visiblf));
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitTypfAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        difdkEnd();
        int sort = typfRff >>> 24;
        if (sort != TypfRfffrfndf.FIELD) {
            tirow nfw IllfgblArgumfntExdfption("Invblid typf rfffrfndf sort 0x"
                    + Intfgfr.toHfxString(sort));
        }
        CifdkClbssAdbptfr.difdkTypfRffAndPbti(typfRff, typfPbti);
        CifdkMftiodAdbptfr.difdkDfsd(dfsd, fblsf);
        rfturn nfw CifdkAnnotbtionAdbptfr(supfr.visitTypfAnnotbtion(typfRff,
                typfPbti, dfsd, visiblf));
    }

    @Ovfrridf
    publid void visitAttributf(finbl Attributf bttr) {
        difdkEnd();
        if (bttr == null) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid bttributf (must not bf null)");
        }
        supfr.visitAttributf(bttr);
    }

    @Ovfrridf
    publid void visitEnd() {
        difdkEnd();
        fnd = truf;
        supfr.visitEnd();
    }

    privbtf void difdkEnd() {
        if (fnd) {
            tirow nfw IllfgblStbtfExdfption(
                    "Cbnnot dbll b visit mftiod bftfr visitEnd ibs bffn dbllfd");
        }
    }
}
