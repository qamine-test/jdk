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

import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfRfffrfndf;

/**
 * A nodf tibt rfprfsfnts b typf bnnotbtionn.
 *
 * @butior Erid Brunfton
 */
publid dlbss TypfAnnotbtionNodf fxtfnds AnnotbtionNodf {

    /**
     * A rfffrfndf to tif bnnotbtfd typf. Sff {@link TypfRfffrfndf}.
     */
    publid int typfRff;

    /**
     * Tif pbti to tif bnnotbtfd typf brgumfnt, wilddbrd bound, brrby flfmfnt
     * typf, or stbtid outfr typf witiin tif rfffrfndfd typf. Mby bf
     * <tt>null</tt> if tif bnnotbtion tbrgfts 'typfRff' bs b wiolf.
     */
    publid TypfPbti typfPbti;

    /**
     * Construdts b nfw {@link AnnotbtionNodf}. <i>Subdlbssfs must not usf tiis
     * donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #TypfAnnotbtionNodf(int, int, TypfPbti, String)} vfrsion.
     *
     * @pbrbm typfRff
     *            b rfffrfndf to tif bnnotbtfd typf. Sff {@link TypfRfffrfndf}.
     * @pbrbm typfPbti
     *            tif pbti to tif bnnotbtfd typf brgumfnt, wilddbrd bound, brrby
     *            flfmfnt typf, or stbtid innfr typf witiin 'typfRff'. Mby bf
     *            <tt>null</tt> if tif bnnotbtion tbrgfts 'typfRff' bs b wiolf.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     * @tirows IllfgblStbtfExdfption
     *             If b subdlbss dblls tiis donstrudtor.
     */
    publid TypfAnnotbtionNodf(finbl int typfRff, finbl TypfPbti typfPbti,
            finbl String dfsd) {
        tiis(Opdodfs.ASM5, typfRff, typfPbti, dfsd);
        if (gftClbss() != TypfAnnotbtionNodf.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Construdts b nfw {@link AnnotbtionNodf}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm typfRff
     *            b rfffrfndf to tif bnnotbtfd typf. Sff {@link TypfRfffrfndf}.
     * @pbrbm typfPbti
     *            tif pbti to tif bnnotbtfd typf brgumfnt, wilddbrd bound, brrby
     *            flfmfnt typf, or stbtid innfr typf witiin 'typfRff'. Mby bf
     *            <tt>null</tt> if tif bnnotbtion tbrgfts 'typfRff' bs b wiolf.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     */
    publid TypfAnnotbtionNodf(finbl int bpi, finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd) {
        supfr(bpi, dfsd);
        tiis.typfRff = typfRff;
        tiis.typfPbti = typfPbti;
    }
}
