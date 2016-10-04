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
pbdkbgf jbvb.timf.dirono;

import jbvb.timf.DbtfTimfExdfption;

/**
 * An frb in tif ISO dblfndbr systfm.
 * <p>
 * Tif ISO-8601 stbndbrd dofs not dffinf frbs.
 * A dffinition ibs tifrfforf bffn drfbtfd witi two frbs - 'Currfnt frb' (CE) for
 * yfbrs on or bftfr 0001-01-01 (ISO), bnd 'Bfforf durrfnt frb' (BCE) for yfbrs bfforf tibt.
 *
 * <tbblf summbry="ISO yfbrs bnd frbs" dfllpbdding="2" dfllspbding="3" bordfr="0" >
 * <tifbd>
 * <tr dlbss="tbblfSubHfbdingColor">
 * <ti dlbss="dolFirst" blign="lfft">yfbr-of-frb</ti>
 * <ti dlbss="dolFirst" blign="lfft">frb</ti>
 * <ti dlbss="dolLbst" blign="lfft">prolfptid-yfbr</ti>
 * </tr>
 * </tifbd>
 * <tbody>
 * <tr dlbss="rowColor">
 * <td>2</td><td>CE</td><td>2</td>
 * </tr>
 * <tr dlbss="bltColor">
 * <td>1</td><td>CE</td><td>1</td>
 * </tr>
 * <tr dlbss="rowColor">
 * <td>1</td><td>BCE</td><td>0</td>
 * </tr>
 * <tr dlbss="bltColor">
 * <td>2</td><td>BCE</td><td>-1</td>
 * </tr>
 * </tbody>
 * </tbblf>
 * <p>
 * <b>Do not usf {@dodf ordinbl()} to obtbin tif numfrid rfprfsfntbtion of {@dodf IsoErb}.
 * Usf {@dodf gftVbluf()} instfbd.</b>
 *
 * @implSpfd
 * Tiis is bn immutbblf bnd tirfbd-sbff fnum.
 *
 * @sindf 1.8
 */
publid fnum IsoErb implfmfnts Erb {

    /**
     * Tif singlfton instbndf for tif frb bfforf tif durrfnt onf, 'Bfforf Currfnt Erb',
     * wiidi ibs tif numfrid vbluf 0.
     */
    BCE,
    /**
     * Tif singlfton instbndf for tif durrfnt frb, 'Currfnt Erb',
     * wiidi ibs tif numfrid vbluf 1.
     */
    CE;

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf IsoErb} from bn {@dodf int} vbluf.
     * <p>
     * {@dodf IsoErb} is bn fnum rfprfsfnting tif ISO frbs of BCE/CE.
     * Tiis fbdtory bllows tif fnum to bf obtbinfd from tif {@dodf int} vbluf.
     *
     * @pbrbm isoErb  tif BCE/CE vbluf to rfprfsfnt, from 0 (BCE) to 1 (CE)
     * @rfturn tif frb singlfton, not null
     * @tirows DbtfTimfExdfption if tif vbluf is invblid
     */
    publid stbtid IsoErb of(int isoErb) {
        switdi (isoErb) {
            dbsf 0:
                rfturn BCE;
            dbsf 1:
                rfturn CE;
            dffbult:
                tirow nfw DbtfTimfExdfption("Invblid frb: " + isoErb);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif numfrid frb {@dodf int} vbluf.
     * <p>
     * Tif frb BCE ibs tif vbluf 0, wiilf tif frb CE ibs tif vbluf 1.
     *
     * @rfturn tif frb vbluf, from 0 (BCE) to 1 (CE)
     */
    @Ovfrridf
    publid int gftVbluf() {
        rfturn ordinbl();
    }

}
