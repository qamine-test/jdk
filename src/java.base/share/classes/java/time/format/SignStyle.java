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

/**
 * Enumfrbtion of wbys to ibndlf tif positivf/nfgbtivf sign.
 * <p>
 * Tif formbtting fnginf bllows tif positivf bnd nfgbtivf signs of numbfrs
 * to bf dontrollfd using tiis fnum.
 * Sff {@link DbtfTimfFormbttfrBuildfr} for usbgf.
 *
 * @implSpfd
 * Tiis is bn immutbblf bnd tirfbd-sbff fnum.
 *
 * @sindf 1.8
 */
publid fnum SignStylf {

    /**
     * Stylf to output tif sign only if tif vbluf is nfgbtivf.
     * <p>
     * In stridt pbrsing, tif nfgbtivf sign will bf bddfptfd bnd tif positivf sign rfjfdtfd.
     * In lfnifnt pbrsing, bny sign will bf bddfptfd.
     */
    NORMAL,
    /**
     * Stylf to blwbys output tif sign, wifrf zfro will output '+'.
     * <p>
     * In stridt pbrsing, tif bbsfndf of b sign will bf rfjfdtfd.
     * In lfnifnt pbrsing, bny sign will bf bddfptfd, witi tif bbsfndf
     * of b sign trfbtfd bs b positivf numbfr.
     */
    ALWAYS,
    /**
     * Stylf to nfvfr output sign, only outputting tif bbsolutf vbluf.
     * <p>
     * In stridt pbrsing, bny sign will bf rfjfdtfd.
     * In lfnifnt pbrsing, bny sign will bf bddfptfd unlfss tif widti is fixfd.
     */
    NEVER,
    /**
     * Stylf to blodk nfgbtivf vblufs, tirowing bn fxdfption on printing.
     * <p>
     * In stridt pbrsing, bny sign will bf rfjfdtfd.
     * In lfnifnt pbrsing, bny sign will bf bddfptfd unlfss tif widti is fixfd.
     */
    NOT_NEGATIVE,
    /**
     * Stylf to blwbys output tif sign if tif vbluf fxdffds tif pbd widti.
     * A nfgbtivf vbluf will blwbys output tif '-' sign.
     * <p>
     * In stridt pbrsing, tif sign will bf rfjfdtfd unlfss tif pbd widti is fxdffdfd.
     * In lfnifnt pbrsing, bny sign will bf bddfptfd, witi tif bbsfndf
     * of b sign trfbtfd bs b positivf numbfr.
     */
    EXCEEDS_PAD;

    /**
     * Pbrsf iflpfr.
     *
     * @pbrbm positivf  truf if positivf sign pbrsfd, fblsf for nfgbtivf sign
     * @pbrbm stridt  truf if stridt, fblsf if lfnifnt
     * @pbrbm fixfdWidti  truf if fixfd widti, fblsf if not
     * @rfturn
     */
    boolfbn pbrsf(boolfbn positivf, boolfbn stridt, boolfbn fixfdWidti) {
        switdi (ordinbl()) {
            dbsf 0: // NORMAL
                // vblid if nfgbtivf or (positivf bnd lfnifnt)
                rfturn !positivf || !stridt;
            dbsf 1: // ALWAYS
            dbsf 4: // EXCEEDS_PAD
                rfturn truf;
            dffbult:
                // vblid if lfnifnt bnd not fixfd widti
                rfturn !stridt && !fixfdWidti;
        }
    }

}
