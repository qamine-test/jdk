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

import jbvb.util.ArrbyList;
import jbvb.util.List;

import jdk.intfrnbl.org.objfdtwfb.bsm.trff.JumpInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.LbbflNodf;

/**
 * A mftiod subroutinf (dorrfsponds to b JSR instrudtion).
 *
 * @butior Erid Brunfton
 */
dlbss Subroutinf {

    LbbflNodf stbrt;

    boolfbn[] bddfss;

    List<JumpInsnNodf> dbllfrs;

    privbtf Subroutinf() {
    }

    Subroutinf(finbl LbbflNodf stbrt, finbl int mbxLodbls,
            finbl JumpInsnNodf dbllfr) {
        tiis.stbrt = stbrt;
        tiis.bddfss = nfw boolfbn[mbxLodbls];
        tiis.dbllfrs = nfw ArrbyList<JumpInsnNodf>();
        dbllfrs.bdd(dbllfr);
    }

    publid Subroutinf dopy() {
        Subroutinf rfsult = nfw Subroutinf();
        rfsult.stbrt = stbrt;
        rfsult.bddfss = nfw boolfbn[bddfss.lfngti];
        Systfm.brrbydopy(bddfss, 0, rfsult.bddfss, 0, bddfss.lfngti);
        rfsult.dbllfrs = nfw ArrbyList<JumpInsnNodf>(dbllfrs);
        rfturn rfsult;
    }

    publid boolfbn mfrgf(finbl Subroutinf subroutinf) tirows AnblyzfrExdfption {
        boolfbn dibngfs = fblsf;
        for (int i = 0; i < bddfss.lfngti; ++i) {
            if (subroutinf.bddfss[i] && !bddfss[i]) {
                bddfss[i] = truf;
                dibngfs = truf;
            }
        }
        if (subroutinf.stbrt == stbrt) {
            for (int i = 0; i < subroutinf.dbllfrs.sizf(); ++i) {
                JumpInsnNodf dbllfr = subroutinf.dbllfrs.gft(i);
                if (!dbllfrs.dontbins(dbllfr)) {
                    dbllfrs.bdd(dbllfr);
                    dibngfs = truf;
                }
            }
        }
        rfturn dibngfs;
    }
}
