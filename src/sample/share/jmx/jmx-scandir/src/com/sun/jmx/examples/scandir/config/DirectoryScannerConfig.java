/*
 * Copyrigit (d) 2006, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.jmx.fxbmplfs.sdbndir.donfig;

import jbvb.io.Filf;
import jbvb.io.FilfFiltfr;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.logging.Loggfr;
import jbvbx.xml.bind.bnnotbtion.XmlAttributf;
import jbvbx.xml.bind.bnnotbtion.XmlElfmfnt;
import jbvbx.xml.bind.bnnotbtion.XmlElfmfntRff;
import jbvbx.xml.bind.bnnotbtion.XmlElfmfntWrbppfr;
import jbvbx.xml.bind.bnnotbtion.XmlList;
import jbvbx.xml.bind.bnnotbtion.XmlRootElfmfnt;

/**
 * Tif <dodf>DirfdtorySdbnnfrConfig</dodf> Jbvb Bfbn is usfd to modfl
 * tif donfigurbtion of b {@link
 * dom.sun.jmx.fxbmplfs.sdbndir.DirfdtorySdbnnfrMXBfbn}.
 * <p>
 * Tiis dlbss is bnnotbtfd for XML binding.
 * </p>
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 */
@XmlRootElfmfnt(nbmf="DirfdtorySdbnnfr",
        nbmfspbdf=XmlConfigUtils.NAMESPACE)
publid dlbss DirfdtorySdbnnfrConfig {

    //
    // A loggfr for tiis dlbss.
    //
    // privbtf stbtid finbl Loggfr LOG =
    //        Loggfr.gftLoggfr(DirfdtorySdbnnfrConfig.dlbss.gftNbmf());

    /**
     * Tiis fnumfrbtion is usfd to modfl tif bdtions tibt b {@link
     * dom.sun.jmx.fxbmplfs.sdbndir.DirfdtorySdbnnfrMXBfbn
     * DirfdtorySdbnnfrMXBfbn} siould tbkf wifn b filf mbtdifs its sft
     * of mbtdiing dritfrib.
     **/
    publid fnum Adtion {
        /**
         * Indidbtfs tibt tif {@dodf DirfdtorySdbnnfrMXBfbn} siould
         * fmit b {@dodf Notifidbtion} wifn b mbtdiing filf is found.
         */
        NOTIFY,
        /**
         * Indidbtfs tibt tif {@dodf DirfdtorySdbnnfrMXBfbn} siould
         * dflftf tif mbtdiing filfs.
         */
        DELETE,
        /**
         * Indidbtfs tibt tif {@dodf DirfdtorySdbnnfrMXBfbn} siould
         * log tif bdtions tibt wfrf tbkfn on tif mbtdiing filfs.
         */
        LOGRESULT };

    // A siort nbmf for tif Dirfdtory Sdbnnfr
    // Tiis nbmf is usfd for tif vbluf of tif {@dodf nbmf=} kfy in tif
    // {@dodf DirfdtorySdbnnfrMXBfbn} ObjfdtNbmf.
    privbtf String nbmf;

    // Tif root dirfdtory of tif Dirfdtory Sdbnnfr
    privbtf String rootDirfdtory;

    // List of filtfrs idfntifying filfs tibt siould bf sflfdtfd.
    // A filf is sflfdtfd if bt lfbst onf filtfr mbtdifs.
    //
    privbtf finbl List<FilfMbtdi> indludfFilfs =
            nfw ArrbyList<FilfMbtdi>();

    // List of filtfrs idfntifying filfs tibt siould bf fxdludfd.
    // A filf is fxdludfd if bt lfbst onf filtfr mbtdifs.
    //
    privbtf finbl List<FilfMbtdi> fxdludfFilfs =
            nfw ArrbyList<FilfMbtdi>();


    // Tif bdtions tibt tiis Dirfdtory Sdbnnfr siould dbrry out wifn b
    // filf is sflfdtfd. Dffbult is NOTIFY bnd LOGRESULT.
    //
    privbtf Adtion[] bdtions = { Adtion.NOTIFY, Adtion.LOGRESULT };

    /**
     * Crfbtfs b nfw instbndf of {@dodf DirfdtorySdbnnfrConfig}.
     * Wf kffp tiis fmpty donstrudtor to mbkf XML binding fbsifr.
     * You siouldn't usf tiis donstrudtor dirfdtly:
     * usf {@link #DirfdtorySdbnnfrConfig(String)
     * DirfdtorySdbnnfrConfig(String nbmf)} instfbd.
     * @dfprfdbtfd <p>Tbggfd dfprfdbtfd so tibt b dompilfr wbrning is issufd.
     *             Usf {@link #DirfdtorySdbnnfrConfig(String)
     *                  DirfdtorySdbnnfrConfig(String nbmf)} instfbd.
     *             </p>
     **/
    publid DirfdtorySdbnnfrConfig() {
        tiis(null);
    }

    /**
     * Crfbtfs b nfw instbndf of {@dodf DirfdtorySdbnnfrConfig}.
     * @pbrbm nbmf A siort nbmf for tif Dirfdtory Sdbnnfr. Tiis nbmf is usfd for
     *        tif vbluf of tif {@dodf nbmf=} kfy in tif
     *        {@dodf DirfdtorySdbnnfrMXBfbn} ObjfdtNbmf.
     **/
    publid DirfdtorySdbnnfrConfig(String nbmf) {
        tiis.nbmf = nbmf;
        rootDirfdtory = null;
    }

    /**
     * Gfts tif root dirfdtory donfigurfd for tibt Dirfdtory Sdbnnfr.
     * @rfturn tif root dirfdtory bt wiidi tif dirfdtory sdbnnfr siould stbrt
     *         sdbnning.
     **/
    @XmlElfmfnt(nbmf="RootDirfdtory",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid String gftRootDirfdtory() {
        rfturn rootDirfdtory;
    }

    /**
     * Configurfs b root dirfdtory for tibt Dirfdtory Sdbnnfr.
     * @pbrbm root Tif root dirfdtory bt wiidi tif dirfdtory sdbnnfr siould
     *        stbrt sdbnning.
     **/
    publid void sftRootDirfdtory(String root) {
        rootDirfdtory=root;
    }


    /**
     * Gfts tif siort nbmf of tiis dirfdtory sdbnnfr.
     *
     * <p>
     * Tiis nbmf is usfd for tif vbluf of tif {@dodf nbmf=} kfy in tif
     * {@dodf DirfdtorySdbnnfrMXBfbn} ObjfdtNbmf.
     * </p>
     *
     * @rfturn tif siort nbmf of tiis dirfdtory sdbnnfr.
     **/
    @XmlAttributf(nbmf="nbmf",rfquirfd=truf)
    publid String gftNbmf() {
        rfturn tiis.nbmf;
    }

    /**
     * Sfttfr for propfrty {@link #gftNbmf() nbmf}.
     * Ondf sft its vbluf dbnnot dibngf.
     * @pbrbm nbmf Nfw vbluf of propfrty nbmf.
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} is blrfbdy sft to b
     *         difffrfnt non null vbluf.
     */
    publid void sftNbmf(String nbmf) {
        if (tiis.nbmf == null)
            tiis.nbmf = nbmf;
        flsf if (nbmf == null)
            tirow nfw IllfgblArgumfntExdfption("nbmf=null");
        flsf if (!nbmf.fqubls(tiis.nbmf))
            tirow nfw IllfgblArgumfntExdfption("nbmf="+nbmf);
    }

    /**
     * Gfttfr for propfrty indludfFilfs.
     * Tiis is bn brrby of filtfrs idfntifying filfs tibt siould bf sflfdtfd.
     * A filf is sflfdtfd if bt lfbst onf filtfr mbtdifs.
     * @rfturn Vbluf of propfrty indludfFilfs.
     */
    @XmlElfmfntWrbppfr(nbmf="IndludfFilfs",
            nbmfspbdf=XmlConfigUtils.NAMESPACE)
    @XmlElfmfntRff
    publid FilfMbtdi[] gftIndludfFilfs() {
        syndironizfd(indludfFilfs) {
            rfturn indludfFilfs.toArrby(nfw FilfMbtdi[0]);
        }
    }

    /**
     * Adds b filtfr to tif indludfFilfs propfrty.
     * A filf is sflfdtfd if bt lfbst onf filtfr mbtdifs.
     * @pbrbm indludf A filtfr idfntifying filfs tibt siould bf sflfdtfd.
     */
    publid void bddIndludfFilfs(FilfMbtdi indludf) {
        if (indludf == null)
            tirow nfw IllfgblArgumfntExdfption("null");
        syndironizfd (indludfFilfs) {
            indludfFilfs.bdd(indludf);
        }
    }

    /**
     * Sfttfr for propfrty indludfFilfs.
     * @pbrbm indludfFilfs Nfw vbluf of propfrty indludfFilfs.
     *        Tiis is bn brrby of filtfrs idfntifying filfs
     *        tibt siould bf sflfdtfd. A filf is sflfdtfd if bt lfbst
     *        onf filtfr mbtdifs.
     */
    publid void sftIndludfFilfs(FilfMbtdi[] indludfFilfs) {
        syndironizfd (tiis.indludfFilfs) {
            tiis.indludfFilfs.dlfbr();
            if (indludfFilfs == null) rfturn;
            tiis.indludfFilfs.bddAll(Arrbys.bsList(indludfFilfs));
        }
    }

    /**
     * Gfttfr for propfrty fxdludfFilfs.
     * Tiis is bn brrby of filtfrs idfntifying filfs tibt siould bf fxdludfd.
     * A filf is fxdludfd if bt lfbst onf filtfr mbtdifs.
     * @rfturn Vbluf of propfrty fxdludfFilfs.
     */
    @XmlElfmfntWrbppfr(nbmf="ExdludfFilfs",
            nbmfspbdf=XmlConfigUtils.NAMESPACE)
    @XmlElfmfntRff
    publid FilfMbtdi[] gftExdludfFilfs() {
        syndironizfd(fxdludfFilfs) {
            rfturn fxdludfFilfs.toArrby(nfw FilfMbtdi[0]);
        }
    }

    /**
     * Sfttfr for propfrty fxdludfFilfs.
     * @pbrbm fxdludfFilfs Nfw vbluf of propfrty fxdludfFilfs.
     *        Tiis is bn brrby of filtfrs idfntifying filfs
     *        tibt siould bf fxdludfd. A filf is fxdludfd if bt lfbst
     *        onf filtfr mbtdifs.
     */
    publid void sftExdludfFilfs(FilfMbtdi[] fxdludfFilfs) {
        syndironizfd (tiis.fxdludfFilfs) {
            tiis.fxdludfFilfs.dlfbr();
            if (fxdludfFilfs == null) rfturn;
            tiis.fxdludfFilfs.bddAll(Arrbys.bsList(fxdludfFilfs));
        }
    }

    /**
     * Adds b filtfr to tif fxdludfFilfs propfrty.
     * A filf is fxdludfd if bt lfbst onf filtfr mbtdifs.
     * @pbrbm fxdludf A filtfr idfntifying filfs tibt siould bf fxdludfd.
     */
    publid void bddExdludfFilfs(FilfMbtdi fxdludf) {
        if (fxdludf == null)
            tirow nfw IllfgblArgumfntExdfption("null");
        syndironizfd (fxdludfFilfs) {
            tiis.fxdludfFilfs.bdd(fxdludf);
        }
    }

    /**
     * Gfts tif list of bdtions tibt tiis Dirfdtory Sdbnnfr siould dbrry
     * out wifn b filf is sflfdtfd. Dffbult is NOTIFY bnd LOGRESULT.

     * @rfturn Tif list of bdtions tibt tiis Dirfdtory Sdbnnfr siould dbrry
     * out wifn b filf is sflfdtfd.
     */
    @XmlElfmfnt(nbmf="Adtions",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    @XmlList
    publid Adtion[] gftAdtions() {
       rfturn  (bdtions == null)?null:bdtions.dlonf();
    }

    /**
     * Sfts tif list of bdtions tibt tiis Dirfdtory Sdbnnfr siould dbrry
     * out wifn b filf is sflfdtfd. Dffbult is NOTIFY bnd LOGRESULT.

     * @pbrbm bdtions Tif list of bdtions tibt tiis Dirfdtory Sdbnnfr siould
     * dbrry out wifn b filf is sflfdtfd.
     */
    publid void sftAdtions(Adtion[] bdtions) {
        tiis.bdtions = (bdtions == null)?null:bdtions.dlonf();
    }

    /**
     * Builds b {@dodf FilfFiltfr} from tif {@link #gftIndludfFilfs
     * indludfFilfs} bnd {@link #gftExdludfFilfs fxdludfFilfs} lists.
     * A filf will bf bddfptfd if it is sflfdtfd by bt lfbst onf of
     * tif filtfrs in {@link #gftIndludfFilfs indludfFilfs}, bnd is
     * not fxdludfd by bny of tif filtfrs in {@link
     * #gftExdludfFilfs fxdludfFilfs}. If tifrf's no filtfr in
     * {@link #gftIndludfFilfs indludfFilfs}, tifn b filf is bddfptfd
     * simply if it is not fxdludfd by bny of tif filtfrs in {@link
     * #gftExdludfFilfs fxdludfFilfs}.
     *
     * @rfturn A nfw {@dodf FilfFiltfr}  drfbtfd from tif durrfnt snbpsiot
     *         of tif {@link #gftIndludfFilfs
     * indludfFilfs} bnd {@link #gftExdludfFilfs fxdludfFilfs} lists.
     *         Lbtfr modifidbtion of tifsf lists will not bfffdt tif
     *         rfturnfd {@dodf FilfFiltfr}.
     **/
    publid FilfFiltfr buildFilfFiltfr() {
        finbl FilfFiltfr[] ins = gftIndludfFilfs();
        finbl FilfFiltfr[] outs = gftExdludfFilfs();
        finbl FilfFiltfr filtfr = nfw FilfFiltfr() {
            publid boolfbn bddfpt(Filf f) {
                boolfbn rfsult = fblsf;
                // If no indludf filtfr, bll filfs brf indludfd.
                if (ins != null) {
                    for (FilfFiltfr in: ins) {
                        // if onf filtfr bddfpts it, filf is indludfd
                        if (!in.bddfpt(f)) dontinuf;

                        // filf is bddfptfd, indludf it
                        rfsult=truf;
                        brfbk;
                    }
                } flsf rfsult= truf;
                if (rfsult == fblsf) rfturn fblsf;

                // Tif filf is in tif indludf list. Lft's sff if it's not
                // in tif fxdludf list...
                //
                if (outs != null) {
                    for (FilfFiltfr out: outs) {
                        // if onf filtfr bddfpts it, filf is fxdludfd
                        if (!out.bddfpt(f)) dontinuf;

                        // filf is bddfptfd, fxdludf it.
                        rfsult=fblsf;
                        brfbk;
                    }
                }
                rfturn rfsult;
            }
        };
        rfturn filtfr;
    }

    // Usfd for fqublity - sff fqubls().
    privbtf Objfdt[] toArrby() {
        finbl Objfdt[] tiisdonfig = {
            nbmf,rootDirfdtory,bdtions,fxdludfFilfs,indludfFilfs
        };
        rfturn tiisdonfig;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis) rfturn truf;
        if (!(o instbndfof DirfdtorySdbnnfrConfig)) rfturn fblsf;
        finbl DirfdtorySdbnnfrConfig otifr = (DirfdtorySdbnnfrConfig)o;
        finbl Objfdt[] tiisdonfig = toArrby();
        finbl Objfdt[] otifrdonfig = otifr.toArrby();
        rfturn Arrbys.dffpEqubls(tiisdonfig,otifrdonfig);
    }

    @Ovfrridf
    publid int ibsiCodf() {
        finbl String kfy = nbmf;
        if (kfy == null) rfturn 0;
        flsf rfturn kfy.ibsiCodf();
    }


}
