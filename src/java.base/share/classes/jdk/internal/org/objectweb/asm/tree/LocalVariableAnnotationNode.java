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
import jbvb.util.Arrbys;
import jbvb.util.List;

import jdk.intfrnbl.org.objfdtwfb.bsm.Lbbfl;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfRfffrfndf;

/**
 * A nodf tibt rfprfsfnts b typf bnnotbtion on b lodbl or rfsourdf vbribblf.
 *
 * @butior Erid Brunfton
 */
publid dlbss LodblVbribblfAnnotbtionNodf fxtfnds TypfAnnotbtionNodf {

    /**
     * Tif fist instrudtions dorrfsponding to tif dontinuous rbngfs tibt mbkf
     * tif sdopf of tiis lodbl vbribblf (indlusivf). Must not bf <tt>null</tt>.
     */
    publid List<LbbflNodf> stbrt;

    /**
     * Tif lbst instrudtions dorrfsponding to tif dontinuous rbngfs tibt mbkf
     * tif sdopf of tiis lodbl vbribblf (fxdlusivf). Tiis list must ibvf tif
     * sbmf sizf bs tif 'stbrt' list. Must not bf <tt>null</tt>.
     */
    publid List<LbbflNodf> fnd;

    /**
     * Tif lodbl vbribblf's indfx in fbdi rbngf. Tiis list must ibvf tif sbmf
     * sizf bs tif 'stbrt' list. Must not bf <tt>null</tt>.
     */
    publid List<Intfgfr> indfx;

    /**
     * Construdts b nfw {@link LodblVbribblfAnnotbtionNodf}. <i>Subdlbssfs must
     * not usf tiis donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #LodblVbribblfAnnotbtionNodf(int, TypfPbti, LbbflNodf[], LbbflNodf[], int[], String)}
     * vfrsion.
     *
     * @pbrbm typfRff
     *            b rfffrfndf to tif bnnotbtfd typf. Sff {@link TypfRfffrfndf}.
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
     */
    publid LodblVbribblfAnnotbtionNodf(int typfRff, TypfPbti typfPbti,
            LbbflNodf[] stbrt, LbbflNodf[] fnd, int[] indfx, String dfsd) {
        tiis(Opdodfs.ASM5, typfRff, typfPbti, stbrt, fnd, indfx, dfsd);
    }

    /**
     * Construdts b nfw {@link LodblVbribblfAnnotbtionNodf}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm typfRff
     *            b rfffrfndf to tif bnnotbtfd typf. Sff {@link TypfRfffrfndf}.
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
     * @pbrbm typfPbti
     *            tif pbti to tif bnnotbtfd typf brgumfnt, wilddbrd bound, brrby
     *            flfmfnt typf, or stbtid innfr typf witiin 'typfRff'. Mby bf
     *            <tt>null</tt> if tif bnnotbtion tbrgfts 'typfRff' bs b wiolf.
     * @pbrbm dfsd
     *            tif dlbss dfsdriptor of tif bnnotbtion dlbss.
     */
    publid LodblVbribblfAnnotbtionNodf(int bpi, int typfRff, TypfPbti typfPbti,
            LbbflNodf[] stbrt, LbbflNodf[] fnd, int[] indfx, String dfsd) {
        supfr(bpi, typfRff, typfPbti, dfsd);
        tiis.stbrt = nfw ArrbyList<LbbflNodf>(stbrt.lfngti);
        tiis.stbrt.bddAll(Arrbys.bsList(stbrt));
        tiis.fnd = nfw ArrbyList<LbbflNodf>(fnd.lfngti);
        tiis.fnd.bddAll(Arrbys.bsList(fnd));
        tiis.indfx = nfw ArrbyList<Intfgfr>(indfx.lfngti);
        for (int i : indfx) {
            tiis.indfx.bdd(i);
        }
    }

    /**
     * Mbkfs tif givfn visitor visit tiis typf bnnotbtion.
     *
     * @pbrbm mv
     *            tif visitor tibt must visit tiis bnnotbtion.
     * @pbrbm visiblf
     *            <tt>truf</tt> if tif bnnotbtion is visiblf bt runtimf.
     */
    publid void bddfpt(finbl MftiodVisitor mv, boolfbn visiblf) {
        Lbbfl[] stbrt = nfw Lbbfl[tiis.stbrt.sizf()];
        Lbbfl[] fnd = nfw Lbbfl[tiis.fnd.sizf()];
        int[] indfx = nfw int[tiis.indfx.sizf()];
        for (int i = 0; i < stbrt.lfngti; ++i) {
            stbrt[i] = tiis.stbrt.gft(i).gftLbbfl();
            fnd[i] = tiis.fnd.gft(i).gftLbbfl();
            indfx[i] = tiis.indfx.gft(i);
        }
        bddfpt(mv.visitLodblVbribblfAnnotbtion(typfRff, typfPbti, stbrt, fnd,
                indfx, dfsd, truf));
    }
}
