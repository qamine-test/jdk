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
 * A visitor to visit b Jbvb bnnotbtion. Tif mftiods of tiis dlbss must bf
 * dbllfd in tif following ordfr: ( <tt>visit</tt> | <tt>visitEnum</tt> |
 * <tt>visitAnnotbtion</tt> | <tt>visitArrby</tt> )* <tt>visitEnd</tt>.
 *
 * @butior Erid Brunfton
 * @butior Eugfnf Kulfsiov
 */
publid bbstrbdt dlbss AnnotbtionVisitor {

    /**
     * Tif ASM API vfrsion implfmfntfd by tiis visitor. Tif vbluf of tiis fifld
     * must bf onf of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    protfdtfd finbl int bpi;

    /**
     * Tif bnnotbtion visitor to wiidi tiis visitor must dflfgbtf mftiod dblls.
     * Mby bf null.
     */
    protfdtfd AnnotbtionVisitor bv;

    /**
     * Construdts b nfw {@link AnnotbtionVisitor}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    publid AnnotbtionVisitor(finbl int bpi) {
        tiis(bpi, null);
    }

    /**
     * Construdts b nfw {@link AnnotbtionVisitor}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm bv
     *            tif bnnotbtion visitor to wiidi tiis visitor must dflfgbtf
     *            mftiod dblls. Mby bf null.
     */
    publid AnnotbtionVisitor(finbl int bpi, finbl AnnotbtionVisitor bv) {
        if (bpi != Opdodfs.ASM4 && bpi != Opdodfs.ASM5) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        tiis.bpi = bpi;
        tiis.bv = bv;
    }

    /**
     * Visits b primitivf vbluf of tif bnnotbtion.
     *
     * @pbrbm nbmf
     *            tif vbluf nbmf.
     * @pbrbm vbluf
     *            tif bdtubl vbluf, wiosf typf must bf {@link Bytf},
     *            {@link Boolfbn}, {@link Cibrbdtfr}, {@link Siort},
     *            {@link Intfgfr} , {@link Long}, {@link Flobt}, {@link Doublf},
     *            {@link String} or {@link Typf} or OBJECT or ARRAY sort. Tiis
     *            vbluf dbn blso bf bn brrby of bytf, boolfbn, siort, dibr, int,
     *            long, flobt or doublf vblufs (tiis is fquivblfnt to using
     *            {@link #visitArrby visitArrby} bnd visiting fbdi brrby flfmfnt
     *            in turn, but is morf donvfnifnt).
     */
    publid void visit(String nbmf, Objfdt vbluf) {
        if (bv != null) {
            bv.visit(nbmf, vbluf);
        }
    }

    /**
     * Visits bn fnumfrbtion vbluf of tif bnnotbtion.
     *
     * @pbrbm nbmf
     *            tif vbluf nbmf.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif fnumfrbtion dlbss.
     * @pbrbm vbluf
     *            tif bdtubl fnumfrbtion vbluf.
     */
    publid void visitEnum(String nbmf, String dfsd, String vbluf) {
        if (bv != null) {
            bv.visitEnum(nbmf, dfsd, vbluf);
        }
    }

    /**
     * Visits b nfstfd bnnotbtion vbluf of tif bnnotbtion.
     *
     * @pbrbm nbmf
     *            tif vbluf nbmf.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif nfstfd bnnotbtion dlbss.
     * @rfturn b visitor to visit tif bdtubl nfstfd bnnotbtion vbluf, or
     *         <tt>null</tt> if tiis visitor is not intfrfstfd in visiting tiis
     *         nfstfd bnnotbtion. <i>Tif nfstfd bnnotbtion vbluf must bf fully
     *         visitfd bfforf dblling otifr mftiods on tiis bnnotbtion
     *         visitor</i>.
     */
    publid AnnotbtionVisitor visitAnnotbtion(String nbmf, String dfsd) {
        if (bv != null) {
            rfturn bv.visitAnnotbtion(nbmf, dfsd);
        }
        rfturn null;
    }

    /**
     * Visits bn brrby vbluf of tif bnnotbtion. Notf tibt brrbys of primitivf
     * typfs (sudi bs bytf, boolfbn, siort, dibr, int, long, flobt or doublf)
     * dbn bf pbssfd bs vbluf to {@link #visit visit}. Tiis is wibt
     * {@link ClbssRfbdfr} dofs.
     *
     * @pbrbm nbmf
     *            tif vbluf nbmf.
     * @rfturn b visitor to visit tif bdtubl brrby vbluf flfmfnts, or
     *         <tt>null</tt> if tiis visitor is not intfrfstfd in visiting tifsf
     *         vblufs. Tif 'nbmf' pbrbmftfrs pbssfd to tif mftiods of tiis
     *         visitor brf ignorfd. <i>All tif brrby vblufs must bf visitfd
     *         bfforf dblling otifr mftiods on tiis bnnotbtion visitor</i>.
     */
    publid AnnotbtionVisitor visitArrby(String nbmf) {
        if (bv != null) {
            rfturn bv.visitArrby(nbmf);
        }
        rfturn null;
    }

    /**
     * Visits tif fnd of tif bnnotbtion.
     */
    publid void visitEnd() {
        if (bv != null) {
            bv.visitEnd();
        }
    }
}
