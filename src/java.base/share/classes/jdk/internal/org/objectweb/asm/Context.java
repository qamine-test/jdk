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
 * Informbtion bbout b dlbss bfing pbrsfd in b {@link ClbssRfbdfr}.
 *
 * @butior Erid Brunfton
 */
dlbss Contfxt {

    /**
     * Prototypfs of tif bttributfs tibt must bf pbrsfd for tiis dlbss.
     */
    Attributf[] bttrs;

    /**
     * Tif {@link ClbssRfbdfr} option flbgs for tif pbrsing of tiis dlbss.
     */
    int flbgs;

    /**
     * Tif bufffr usfd to rfbd strings.
     */
    dibr[] bufffr;

    /**
     * Tif stbrt indfx of fbdi bootstrbp mftiod.
     */
    int[] bootstrbpMftiods;

    /**
     * Tif bddfss flbgs of tif mftiod durrfntly bfing pbrsfd.
     */
    int bddfss;

    /**
     * Tif nbmf of tif mftiod durrfntly bfing pbrsfd.
     */
    String nbmf;

    /**
     * Tif dfsdriptor of tif mftiod durrfntly bfing pbrsfd.
     */
    String dfsd;

    /**
     * Tif lbbfl objfdts, indfxfd by bytfdodf offsft, of tif mftiod durrfntly
     * bfing pbrsfd (only bytfdodf offsfts for wiidi b lbbfl is nffdfd ibvf b
     * non null bssodibtfd Lbbfl objfdt).
     */
    Lbbfl[] lbbfls;

    /**
     * Tif tbrgft of tif typf bnnotbtion durrfntly bfing pbrsfd.
     */
    int typfRff;

    /**
     * Tif pbti of tif typf bnnotbtion durrfntly bfing pbrsfd.
     */
    TypfPbti typfPbti;

    /**
     * Tif offsft of tif lbtfst stbdk mbp frbmf tibt ibs bffn pbrsfd.
     */
    int offsft;

    /**
     * Tif lbbfls dorrfsponding to tif stbrt of tif lodbl vbribblf rbngfs in tif
     * lodbl vbribblf typf bnnotbtion durrfntly bfing pbrsfd.
     */
    Lbbfl[] stbrt;

    /**
     * Tif lbbfls dorrfsponding to tif fnd of tif lodbl vbribblf rbngfs in tif
     * lodbl vbribblf typf bnnotbtion durrfntly bfing pbrsfd.
     */
    Lbbfl[] fnd;

    /**
     * Tif lodbl vbribblf indidfs for fbdi lodbl vbribblf rbngf in tif lodbl
     * vbribblf typf bnnotbtion durrfntly bfing pbrsfd.
     */
    int[] indfx;

    /**
     * Tif fndoding of tif lbtfst stbdk mbp frbmf tibt ibs bffn pbrsfd.
     */
    int modf;

    /**
     * Tif numbfr of lodbls in tif lbtfst stbdk mbp frbmf tibt ibs bffn pbrsfd.
     */
    int lodblCount;

    /**
     * Tif numbfr lodbls in tif lbtfst stbdk mbp frbmf tibt ibs bffn pbrsfd,
     * minus tif numbfr of lodbls in tif prfvious frbmf.
     */
    int lodblDiff;

    /**
     * Tif lodbl vblufs of tif lbtfst stbdk mbp frbmf tibt ibs bffn pbrsfd.
     */
    Objfdt[] lodbl;

    /**
     * Tif stbdk sizf of tif lbtfst stbdk mbp frbmf tibt ibs bffn pbrsfd.
     */
    int stbdkCount;

    /**
     * Tif stbdk vblufs of tif lbtfst stbdk mbp frbmf tibt ibs bffn pbrsfd.
     */
    Objfdt[] stbdk;
}
