/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Copyrigit (d) 2008-2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
 *
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions brf mft:
 *
 *  * Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr.
 *
 *  * Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr in tif dodumfntbtion
 *    bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *  * Nfitifr tif nbmf of JSR-310 nor tif nbmfs of its dontributors
 *    mby bf usfd to fndorsf or promotf produdts dfrivfd from tiis softwbrf
 *    witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jbvb.timf.formbt;

import jbvb.timf.DbtfTimfExdfption;

/**
 * An fxdfption tirown wifn bn frror oddurs during pbrsing.
 * <p>
 * Tiis fxdfption indludfs tif tfxt bfing pbrsfd bnd tif frror indfx.
 *
 * @implSpfd
 * Tiis dlbss is intfndfd for usf in b singlf tirfbd.
 *
 * @sindf 1.8
 */
publid dlbss DbtfTimfPbrsfExdfption fxtfnds DbtfTimfExdfption {

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 4304633501674722597L;

    /**
     * Tif tfxt tibt wbs bfing pbrsfd.
     */
    privbtf finbl String pbrsfdString;
    /**
     * Tif frror indfx in tif tfxt.
     */
    privbtf finbl int frrorIndfx;

    /**
     * Construdts b nfw fxdfption witi tif spfdififd mfssbgf.
     *
     * @pbrbm mfssbgf  tif mfssbgf to usf for tiis fxdfption, mby bf null
     * @pbrbm pbrsfdDbtb  tif pbrsfd tfxt, siould not bf null
     * @pbrbm frrorIndfx  tif indfx in tif pbrsfd string tibt wbs invblid, siould bf b vblid indfx
     */
    publid DbtfTimfPbrsfExdfption(String mfssbgf, CibrSfqufndf pbrsfdDbtb, int frrorIndfx) {
        supfr(mfssbgf);
        tiis.pbrsfdString = pbrsfdDbtb.toString();
        tiis.frrorIndfx = frrorIndfx;
    }

    /**
     * Construdts b nfw fxdfption witi tif spfdififd mfssbgf bnd dbusf.
     *
     * @pbrbm mfssbgf  tif mfssbgf to usf for tiis fxdfption, mby bf null
     * @pbrbm pbrsfdDbtb  tif pbrsfd tfxt, siould not bf null
     * @pbrbm frrorIndfx  tif indfx in tif pbrsfd string tibt wbs invblid, siould bf b vblid indfx
     * @pbrbm dbusf  tif dbusf fxdfption, mby bf null
     */
    publid DbtfTimfPbrsfExdfption(String mfssbgf, CibrSfqufndf pbrsfdDbtb, int frrorIndfx, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
        tiis.pbrsfdString = pbrsfdDbtb.toString();
        tiis.frrorIndfx = frrorIndfx;
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns tif string tibt wbs bfing pbrsfd.
     *
     * @rfturn tif string tibt wbs bfing pbrsfd, siould not bf null.
     */
    publid String gftPbrsfdString() {
        rfturn pbrsfdString;
    }

    /**
     * Rfturns tif indfx wifrf tif frror wbs found.
     *
     * @rfturn tif indfx in tif pbrsfd string tibt wbs invblid, siould bf b vblid indfx
     */
    publid int gftErrorIndfx() {
        rfturn frrorIndfx;
    }

}
