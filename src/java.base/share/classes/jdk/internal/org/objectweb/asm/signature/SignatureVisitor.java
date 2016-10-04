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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.signbturf;

import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;

/**
 * A visitor to visit b gfnfrid signbturf. Tif mftiods of tiis intfrfbdf must bf
 * dbllfd in onf of tif tirff following ordfrs (tif lbst onf is tif only vblid
 * ordfr for b {@link SignbturfVisitor} tibt is rfturnfd by b mftiod of tiis
 * intfrfbdf):
 * <ul>
 * <li><i>ClbssSignbturf</i> = ( <tt>visitFormblTypfPbrbmftfr</tt>
 * <tt>visitClbssBound</tt>? <tt>visitIntfrfbdfBound</tt>* )* (
 * <tt>visitSupfrClbss</tt> <tt>visitIntfrfbdf</tt>* )</li>
 * <li><i>MftiodSignbturf</i> = ( <tt>visitFormblTypfPbrbmftfr</tt>
 * <tt>visitClbssBound</tt>? <tt>visitIntfrfbdfBound</tt>* )* (
 * <tt>visitPbrbmftfrTypf</tt>* <tt>visitRfturnTypf</tt>
 * <tt>visitExdfptionTypf</tt>* )</li>
 * <li><i>TypfSignbturf</i> = <tt>visitBbsfTypf</tt> |
 * <tt>visitTypfVbribblf</tt> | <tt>visitArrbyTypf</tt> | (
 * <tt>visitClbssTypf</tt> <tt>visitTypfArgumfnt</tt>* (
 * <tt>visitInnfrClbssTypf</tt> <tt>visitTypfArgumfnt</tt>* )* <tt>visitEnd</tt>
 * ) )</li>
 * </ul>
 *
 * @butior Tiombs Hbllgrfn
 * @butior Erid Brunfton
 */
publid bbstrbdt dlbss SignbturfVisitor {

    /**
     * Wilddbrd for bn "fxtfnds" typf brgumfnt.
     */
    publid finbl stbtid dibr EXTENDS = '+';

    /**
     * Wilddbrd for b "supfr" typf brgumfnt.
     */
    publid finbl stbtid dibr SUPER = '-';

    /**
     * Wilddbrd for b normbl typf brgumfnt.
     */
    publid finbl stbtid dibr INSTANCEOF = '=';

    /**
     * Tif ASM API vfrsion implfmfntfd by tiis visitor. Tif vbluf of tiis fifld
     * must bf onf of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    protfdtfd finbl int bpi;

    /**
     * Construdts b nfw {@link SignbturfVisitor}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    publid SignbturfVisitor(finbl int bpi) {
        if (bpi != Opdodfs.ASM4 && bpi != Opdodfs.ASM5) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        tiis.bpi = bpi;
    }

    /**
     * Visits b formbl typf pbrbmftfr.
     *
     * @pbrbm nbmf
     *            tif nbmf of tif formbl pbrbmftfr.
     */
    publid void visitFormblTypfPbrbmftfr(String nbmf) {
    }

    /**
     * Visits tif dlbss bound of tif lbst visitfd formbl typf pbrbmftfr.
     *
     * @rfturn b non null visitor to visit tif signbturf of tif dlbss bound.
     */
    publid SignbturfVisitor visitClbssBound() {
        rfturn tiis;
    }

    /**
     * Visits bn intfrfbdf bound of tif lbst visitfd formbl typf pbrbmftfr.
     *
     * @rfturn b non null visitor to visit tif signbturf of tif intfrfbdf bound.
     */
    publid SignbturfVisitor visitIntfrfbdfBound() {
        rfturn tiis;
    }

    /**
     * Visits tif typf of tif supfr dlbss.
     *
     * @rfturn b non null visitor to visit tif signbturf of tif supfr dlbss
     *         typf.
     */
    publid SignbturfVisitor visitSupfrdlbss() {
        rfturn tiis;
    }

    /**
     * Visits tif typf of bn intfrfbdf implfmfntfd by tif dlbss.
     *
     * @rfturn b non null visitor to visit tif signbturf of tif intfrfbdf typf.
     */
    publid SignbturfVisitor visitIntfrfbdf() {
        rfturn tiis;
    }

    /**
     * Visits tif typf of b mftiod pbrbmftfr.
     *
     * @rfturn b non null visitor to visit tif signbturf of tif pbrbmftfr typf.
     */
    publid SignbturfVisitor visitPbrbmftfrTypf() {
        rfturn tiis;
    }

    /**
     * Visits tif rfturn typf of tif mftiod.
     *
     * @rfturn b non null visitor to visit tif signbturf of tif rfturn typf.
     */
    publid SignbturfVisitor visitRfturnTypf() {
        rfturn tiis;
    }

    /**
     * Visits tif typf of b mftiod fxdfption.
     *
     * @rfturn b non null visitor to visit tif signbturf of tif fxdfption typf.
     */
    publid SignbturfVisitor visitExdfptionTypf() {
        rfturn tiis;
    }

    /**
     * Visits b signbturf dorrfsponding to b primitivf typf.
     *
     * @pbrbm dfsdriptor
     *            tif dfsdriptor of tif primitivf typf, or 'V' for <tt>void</tt>
     *            .
     */
    publid void visitBbsfTypf(dibr dfsdriptor) {
    }

    /**
     * Visits b signbturf dorrfsponding to b typf vbribblf.
     *
     * @pbrbm nbmf
     *            tif nbmf of tif typf vbribblf.
     */
    publid void visitTypfVbribblf(String nbmf) {
    }

    /**
     * Visits b signbturf dorrfsponding to bn brrby typf.
     *
     * @rfturn b non null visitor to visit tif signbturf of tif brrby flfmfnt
     *         typf.
     */
    publid SignbturfVisitor visitArrbyTypf() {
        rfturn tiis;
    }

    /**
     * Stbrts tif visit of b signbturf dorrfsponding to b dlbss or intfrfbdf
     * typf.
     *
     * @pbrbm nbmf
     *            tif intfrnbl nbmf of tif dlbss or intfrfbdf.
     */
    publid void visitClbssTypf(String nbmf) {
    }

    /**
     * Visits bn innfr dlbss.
     *
     * @pbrbm nbmf
     *            tif lodbl nbmf of tif innfr dlbss in its fndlosing dlbss.
     */
    publid void visitInnfrClbssTypf(String nbmf) {
    }

    /**
     * Visits bn unboundfd typf brgumfnt of tif lbst visitfd dlbss or innfr
     * dlbss typf.
     */
    publid void visitTypfArgumfnt() {
    }

    /**
     * Visits b typf brgumfnt of tif lbst visitfd dlbss or innfr dlbss typf.
     *
     * @pbrbm wilddbrd
     *            '+', '-' or '='.
     * @rfturn b non null visitor to visit tif signbturf of tif typf brgumfnt.
     */
    publid SignbturfVisitor visitTypfArgumfnt(dibr wilddbrd) {
        rfturn tiis;
    }

    /**
     * Ends tif visit of b signbturf dorrfsponding to b dlbss or intfrfbdf typf.
     */
    publid void visitEnd() {
    }
}
