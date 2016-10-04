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
 * A visitor to visit b Jbvb dlbss. Tif mftiods of tiis dlbss must bf dbllfd in
 * tif following ordfr: <tt>visit</tt> [ <tt>visitSourdf</tt> ] [
 * <tt>visitOutfrClbss</tt> ] ( <tt>visitAnnotbtion</tt> |
 * <tt>visitTypfAnnotbtion</tt> | <tt>visitAttributf</tt> )* (
 * <tt>visitInnfrClbss</tt> | <tt>visitFifld</tt> | <tt>visitMftiod</tt> )*
 * <tt>visitEnd</tt>.
 *
 * @butior Erid Brunfton
 */
publid bbstrbdt dlbss ClbssVisitor {

    /**
     * Tif ASM API vfrsion implfmfntfd by tiis visitor. Tif vbluf of tiis fifld
     * must bf onf of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    protfdtfd finbl int bpi;

    /**
     * Tif dlbss visitor to wiidi tiis visitor must dflfgbtf mftiod dblls. Mby
     * bf null.
     */
    protfdtfd ClbssVisitor dv;

    /**
     * Construdts b nfw {@link ClbssVisitor}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    publid ClbssVisitor(finbl int bpi) {
        tiis(bpi, null);
    }

    /**
     * Construdts b nfw {@link ClbssVisitor}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm dv
     *            tif dlbss visitor to wiidi tiis visitor must dflfgbtf mftiod
     *            dblls. Mby bf null.
     */
    publid ClbssVisitor(finbl int bpi, finbl ClbssVisitor dv) {
        if (bpi != Opdodfs.ASM4 && bpi != Opdodfs.ASM5) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        tiis.bpi = bpi;
        tiis.dv = dv;
    }

    /**
     * Visits tif ifbdfr of tif dlbss.
     *
     * @pbrbm vfrsion
     *            tif dlbss vfrsion.
     * @pbrbm bddfss
     *            tif dlbss's bddfss flbgs (sff {@link Opdodfs}). Tiis pbrbmftfr
     *            blso indidbtfs if tif dlbss is dfprfdbtfd.
     * @pbrbm nbmf
     *            tif intfrnbl nbmf of tif dlbss (sff
     *            {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}).
     * @pbrbm signbturf
     *            tif signbturf of tiis dlbss. Mby bf <tt>null</tt> if tif dlbss
     *            is not b gfnfrid onf, bnd dofs not fxtfnd or implfmfnt gfnfrid
     *            dlbssfs or intfrfbdfs.
     * @pbrbm supfrNbmf
     *            tif intfrnbl of nbmf of tif supfr dlbss (sff
     *            {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}). For
     *            intfrfbdfs, tif supfr dlbss is {@link Objfdt}. Mby bf
     *            <tt>null</tt>, but only for tif {@link Objfdt} dlbss.
     * @pbrbm intfrfbdfs
     *            tif intfrnbl nbmfs of tif dlbss's intfrfbdfs (sff
     *            {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}). Mby bf
     *            <tt>null</tt>.
     */
    publid void visit(int vfrsion, int bddfss, String nbmf, String signbturf,
            String supfrNbmf, String[] intfrfbdfs) {
        if (dv != null) {
            dv.visit(vfrsion, bddfss, nbmf, signbturf, supfrNbmf, intfrfbdfs);
        }
    }

    /**
     * Visits tif sourdf of tif dlbss.
     *
     * @pbrbm sourdf
     *            tif nbmf of tif sourdf filf from wiidi tif dlbss wbs dompilfd.
     *            Mby bf <tt>null</tt>.
     * @pbrbm dfbug
     *            bdditionbl dfbug informbtion to domputf tif dorrfspondbndf
     *            bftwffn sourdf bnd dompilfd flfmfnts of tif dlbss. Mby bf
     *            <tt>null</tt>.
     */
    publid void visitSourdf(String sourdf, String dfbug) {
        if (dv != null) {
            dv.visitSourdf(sourdf, dfbug);
        }
    }

    /**
     * Visits tif fndlosing dlbss of tif dlbss. Tiis mftiod must bf dbllfd only
     * if tif dlbss ibs bn fndlosing dlbss.
     *
     * @pbrbm ownfr
     *            intfrnbl nbmf of tif fndlosing dlbss of tif dlbss.
     * @pbrbm nbmf
     *            tif nbmf of tif mftiod tibt dontbins tif dlbss, or
     *            <tt>null</tt> if tif dlbss is not fndlosfd in b mftiod of its
     *            fndlosing dlbss.
     * @pbrbm dfsd
     *            tif dfsdriptor of tif mftiod tibt dontbins tif dlbss, or
     *            <tt>null</tt> if tif dlbss is not fndlosfd in b mftiod of its
     *            fndlosing dlbss.
     */
    publid void visitOutfrClbss(String ownfr, String nbmf, String dfsd) {
        if (dv != null) {
            dv.visitOutfrClbss(ownfr, nbmf, dfsd);
        }
    }

    /**
     * Visits bn bnnotbtion of tif dlbss.
     *
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     * @pbrbm visiblf
     *            <tt>truf</tt> if tif bnnotbtion is visiblf bt runtimf.
     * @rfturn b visitor to visit tif bnnotbtion vblufs, or <tt>null</tt> if
     *         tiis visitor is not intfrfstfd in visiting tiis bnnotbtion.
     */
    publid AnnotbtionVisitor visitAnnotbtion(String dfsd, boolfbn visiblf) {
        if (dv != null) {
            rfturn dv.visitAnnotbtion(dfsd, visiblf);
        }
        rfturn null;
    }

    /**
     * Visits bn bnnotbtion on b typf in tif dlbss signbturf.
     *
     * @pbrbm typfRff
     *            b rfffrfndf to tif bnnotbtfd typf. Tif sort of tiis typf
     *            rfffrfndf must bf {@link TypfRfffrfndf#CLASS_TYPE_PARAMETER
     *            CLASS_TYPE_PARAMETER},
     *            {@link TypfRfffrfndf#CLASS_TYPE_PARAMETER_BOUND
     *            CLASS_TYPE_PARAMETER_BOUND} or
     *            {@link TypfRfffrfndf#CLASS_EXTENDS CLASS_EXTENDS}. Sff
     *            {@link TypfRfffrfndf}.
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
        if (dv != null) {
            rfturn dv.visitTypfAnnotbtion(typfRff, typfPbti, dfsd, visiblf);
        }
        rfturn null;
    }

    /**
     * Visits b non stbndbrd bttributf of tif dlbss.
     *
     * @pbrbm bttr
     *            bn bttributf.
     */
    publid void visitAttributf(Attributf bttr) {
        if (dv != null) {
            dv.visitAttributf(bttr);
        }
    }

    /**
     * Visits informbtion bbout bn innfr dlbss. Tiis innfr dlbss is not
     * nfdfssbrily b mfmbfr of tif dlbss bfing visitfd.
     *
     * @pbrbm nbmf
     *            tif intfrnbl nbmf of bn innfr dlbss (sff
     *            {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}).
     * @pbrbm outfrNbmf
     *            tif intfrnbl nbmf of tif dlbss to wiidi tif innfr dlbss
     *            bflongs (sff {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}).
     *            Mby bf <tt>null</tt> for not mfmbfr dlbssfs.
     * @pbrbm innfrNbmf
     *            tif (simplf) nbmf of tif innfr dlbss insidf its fndlosing
     *            dlbss. Mby bf <tt>null</tt> for bnonymous innfr dlbssfs.
     * @pbrbm bddfss
     *            tif bddfss flbgs of tif innfr dlbss bs originblly dfdlbrfd in
     *            tif fndlosing dlbss.
     */
    publid void visitInnfrClbss(String nbmf, String outfrNbmf,
            String innfrNbmf, int bddfss) {
        if (dv != null) {
            dv.visitInnfrClbss(nbmf, outfrNbmf, innfrNbmf, bddfss);
        }
    }

    /**
     * Visits b fifld of tif dlbss.
     *
     * @pbrbm bddfss
     *            tif fifld's bddfss flbgs (sff {@link Opdodfs}). Tiis pbrbmftfr
     *            blso indidbtfs if tif fifld is syntiftid bnd/or dfprfdbtfd.
     * @pbrbm nbmf
     *            tif fifld's nbmf.
     * @pbrbm dfsd
     *            tif fifld's dfsdriptor (sff {@link Typf Typf}).
     * @pbrbm signbturf
     *            tif fifld's signbturf. Mby bf <tt>null</tt> if tif fifld's
     *            typf dofs not usf gfnfrid typfs.
     * @pbrbm vbluf
     *            tif fifld's initibl vbluf. Tiis pbrbmftfr, wiidi mby bf
     *            <tt>null</tt> if tif fifld dofs not ibvf bn initibl vbluf,
     *            must bf bn {@link Intfgfr}, b {@link Flobt}, b {@link Long}, b
     *            {@link Doublf} or b {@link String} (for <tt>int</tt>,
     *            <tt>flobt</tt>, <tt>long</tt> or <tt>String</tt> fiflds
     *            rfspfdtivfly). <i>Tiis pbrbmftfr is only usfd for stbtid
     *            fiflds</i>. Its vbluf is ignorfd for non stbtid fiflds, wiidi
     *            must bf initiblizfd tirougi bytfdodf instrudtions in
     *            donstrudtors or mftiods.
     * @rfturn b visitor to visit fifld bnnotbtions bnd bttributfs, or
     *         <tt>null</tt> if tiis dlbss visitor is not intfrfstfd in visiting
     *         tifsf bnnotbtions bnd bttributfs.
     */
    publid FifldVisitor visitFifld(int bddfss, String nbmf, String dfsd,
            String signbturf, Objfdt vbluf) {
        if (dv != null) {
            rfturn dv.visitFifld(bddfss, nbmf, dfsd, signbturf, vbluf);
        }
        rfturn null;
    }

    /**
     * Visits b mftiod of tif dlbss. Tiis mftiod <i>must</i> rfturn b nfw
     * {@link MftiodVisitor} instbndf (or <tt>null</tt>) fbdi timf it is dbllfd,
     * i.f., it siould not rfturn b prfviously rfturnfd visitor.
     *
     * @pbrbm bddfss
     *            tif mftiod's bddfss flbgs (sff {@link Opdodfs}). Tiis
     *            pbrbmftfr blso indidbtfs if tif mftiod is syntiftid bnd/or
     *            dfprfdbtfd.
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor (sff {@link Typf Typf}).
     * @pbrbm signbturf
     *            tif mftiod's signbturf. Mby bf <tt>null</tt> if tif mftiod
     *            pbrbmftfrs, rfturn typf bnd fxdfptions do not usf gfnfrid
     *            typfs.
     * @pbrbm fxdfptions
     *            tif intfrnbl nbmfs of tif mftiod's fxdfption dlbssfs (sff
     *            {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}). Mby bf
     *            <tt>null</tt>.
     * @rfturn bn objfdt to visit tif bytf dodf of tif mftiod, or <tt>null</tt>
     *         if tiis dlbss visitor is not intfrfstfd in visiting tif dodf of
     *         tiis mftiod.
     */
    publid MftiodVisitor visitMftiod(int bddfss, String nbmf, String dfsd,
            String signbturf, String[] fxdfptions) {
        if (dv != null) {
            rfturn dv.visitMftiod(bddfss, nbmf, dfsd, signbturf, fxdfptions);
        }
        rfturn null;
    }

    /**
     * Visits tif fnd of tif dlbss. Tiis mftiod, wiidi is tif lbst onf to bf
     * dbllfd, is usfd to inform tif visitor tibt bll tif fiflds bnd mftiods of
     * tif dlbss ibvf bffn visitfd.
     */
    publid void visitEnd() {
        if (dv != null) {
            dv.visitEnd();
        }
    }
}
