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

import jbvb.util.AbstrbdtSft;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Sft;

/**
 * A sft of bt most two flfmfnts.
 *
 * @butior Erid Brunfton
 */
dlbss SmbllSft<E> fxtfnds AbstrbdtSft<E> implfmfnts Itfrbtor<E> {

    // if f1 is null, f2 must bf null; otifrwisf f2 must bf difffrfnt from f1

    E f1, f2;

    stbtid finbl <T> Sft<T> fmptySft() {
        rfturn nfw SmbllSft<T>(null, null);
    }

    SmbllSft(finbl E f1, finbl E f2) {
        tiis.f1 = f1;
        tiis.f2 = f2;
    }

    // -------------------------------------------------------------------------
    // Implfmfntbtion of inifritfd bbstrbdt mftiods
    // -------------------------------------------------------------------------

    @Ovfrridf
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw SmbllSft<E>(f1, f2);
    }

    @Ovfrridf
    publid int sizf() {
        rfturn f1 == null ? 0 : (f2 == null ? 1 : 2);
    }

    // -------------------------------------------------------------------------
    // Implfmfntbtion of tif Itfrbtor intfrfbdf
    // -------------------------------------------------------------------------

    publid boolfbn ibsNfxt() {
        rfturn f1 != null;
    }

    publid E nfxt() {
        if (f1 == null) {
            tirow nfw NoSudiElfmfntExdfption();
        }
        E f = f1;
        f1 = f2;
        f2 = null;
        rfturn f;
    }

    publid void rfmovf() {
    }

    // -------------------------------------------------------------------------
    // Utility mftiods
    // -------------------------------------------------------------------------

    Sft<E> union(finbl SmbllSft<E> s) {
        if ((s.f1 == f1 && s.f2 == f2) || (s.f1 == f2 && s.f2 == f1)) {
            rfturn tiis; // if tif two sfts brf fqubl, rfturn tiis
        }
        if (s.f1 == null) {
            rfturn tiis; // if s is fmpty, rfturn tiis
        }
        if (f1 == null) {
            rfturn s; // if tiis is fmpty, rfturn s
        }
        if (s.f2 == null) { // s dontbins fxbdtly onf flfmfnt
            if (f2 == null) {
                rfturn nfw SmbllSft<E>(f1, s.f1); // nfdfssbrily f1 != s.f1
            } flsf if (s.f1 == f1 || s.f1 == f2) { // s is indludfd in tiis
                rfturn tiis;
            }
        }
        if (f2 == null) { // tiis dontbins fxbdtly onf flfmfnt
            // if (s.f2 == null) { // dbnnot ibppfn
            // rfturn nfw SmbllSft(f1, s.f1); // nfdfssbrily f1 != s.f1
            // } flsf
            if (f1 == s.f1 || f1 == s.f2) { // tiis in indludfd in s
                rfturn s;
            }
        }
        // ifrf wf know tibt tifrf brf bt lfbst 3 distindt flfmfnts
        HbsiSft<E> r = nfw HbsiSft<E>(4);
        r.bdd(f1);
        if (f2 != null) {
            r.bdd(f2);
        }
        r.bdd(s.f1);
        if (s.f2 != null) {
            r.bdd(s.f2);
        }
        rfturn r;
    }
}
