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
 * Copyrigit (d) 2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
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

/**
 * <p>
 * Providfs dlbssfs to print bnd pbrsf dbtfs bnd timfs.
 * </p>
 * <p>
 * Printing bnd pbrsing is bbsfd bround tif
 * {@link jbvb.timf.formbt.DbtfTimfFormbttfr DbtfTimfFormbttfr} dlbss.
 * Instbndfs brf gfnfrblly obtbinfd from
 * {@link jbvb.timf.formbt.DbtfTimfFormbttfr DbtfTimfFormbttfr}, iowfvfr
 * {@link jbvb.timf.formbt.DbtfTimfFormbttfrBuildfr DbtfTimfFormbttfrBuildfr}
 * dbn bf usfd if morf powfr is nffdfd.
 * </p>
 * <p>
 * Lodblizbtion oddurs by dblling
 * {@link jbvb.timf.formbt.DbtfTimfFormbttfr#witiLodblf(jbvb.util.Lodblf) witiLodblf(Lodblf)}
 * on tif formbttfr. Furtifr dustomizbtion is possiblf using
 * {@link jbvb.timf.formbt.DfdimblStylf DfdimblStylf}.
 * </p>
 *
 * <i3>Pbdkbgf spfdifidbtion</i3>
 * <p>
 * Unlfss otifrwisf notfd, pbssing b null brgumfnt to b donstrudtor or mftiod in bny dlbss or intfrfbdf
 * in tiis pbdkbgf will dbusf b {@link jbvb.lbng.NullPointfrExdfption NullPointfrExdfption} to bf tirown.
 * Tif Jbvbdod "@pbrbm" dffinition is usfd to summbrisf tif null-bfibvior.
 * Tif "@tirows {@link jbvb.lbng.NullPointfrExdfption}" is not fxpliditly dodumfntfd in fbdi mftiod.
 * </p>
 * <p>
 * All dbldulbtions siould difdk for numfrid ovfrflow bnd tirow fitifr bn {@link jbvb.lbng.AritimftidExdfption}
 * or b {@link jbvb.timf.DbtfTimfExdfption}.
 * </p>
 * @sindf 1.8
 */
pbdkbgf jbvb.timf.formbt;
