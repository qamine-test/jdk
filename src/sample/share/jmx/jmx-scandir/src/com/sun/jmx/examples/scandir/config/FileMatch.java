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
import jbvb.util.Arrbys;
import jbvb.util.Dbtf;
import jbvb.util.logging.Loggfr;
import jbvbx.xml.bind.bnnotbtion.XmlElfmfnt;
import jbvbx.xml.bind.bnnotbtion.XmlRootElfmfnt;

/**
 * Tif <dodf>FilfMbtdi</dodf> Jbvb Bfbn is usfd to modfl
 * tif donfigurbtion of b {@link FilfFiltfr} wiidi
 * mbtdifs {@link Filf filfs} bgbinst b sft of dritfrib.
 * <p>
 * Tif <dodf>FilfMbtdi</dodf> dlbss blso implfmfnts
 * {@link FilfFiltfr} - bpplying bn {@dodf AND} on bll
 * its donditions. {@dodf OR} donditions dbn bf obtbinfd
 * by supplying sfvfrbl instbndfs of <dodf>FilfMbtdi</dodf>
 * to tif fndbpsulbting {@link DirfdtorySdbnnfrConfig}, wiidi
 * rfspfdtivfly bpplifs bn {@dodf OR} on bll its
 * {@dodf <FilfFiltfr>} flfmfnts.
 * </p>
 *
 * <p>
 * Tiis dlbss is bnnotbtfd for XML binding.
 * </p>
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 */
@XmlRootElfmfnt(nbmf="FilfFiltfr",
        nbmfspbdf=XmlConfigUtils.NAMESPACE)
publid dlbss FilfMbtdi implfmfnts FilfFiltfr {

    //
    // A loggfr for tiis dlbss.
    //
    // privbtf stbtid finbl Loggfr LOG =
    //        Loggfr.gftLoggfr(FilfMbtdi.dlbss.gftNbmf());

    /**
     * A rfgulbr fxprfssion bgbinst wiidi dirfdtory nbmfs siould bf mbtdifd.
     */
    privbtf String dirfdtoryPbttfrn;

    /**
     * A rfgulbr fxprfssion bgbinst wiidi filf nbmfs siould bf mbtdifd.
     */
    privbtf String filfPbttfrn;

    /**
     * Filf wiosf sizf in bytfs fxdffds tiis limit will bf sflfdtfd.
     */
    privbtf long sizfExdffdsMbxBytfs;

    /**
     * A filf wiidi will bf sflfdtfd only if it wbs lbst modififd bftfr
     * tiis dbtf
     */
    privbtf Dbtf lbstModififdAftfr;

    /**
     * A filf wiidi will bf sflfdtfd only if it wbs lbst modififd bfforf
     * tiis dbtf
     */
    privbtf Dbtf lbstModififdBfforf;

    /**
     * Crfbtfs b nfw instbndf of FilfMbtdi
     */
    publid FilfMbtdi() {
    }

    /**
     * Gfttfr for propfrty dirfdtoryPbttfrn. Tiis is b rfgulbr fxprfssion
     * bgbinst wiidi dirfdtory nbmfs siould bf mbtdifd.
     * Applifs only to dirfdtory, bnd tflls wiftifr b dirfdtory siould bf
     * indludfd or fxdludfd from tif sfbrdi.
     * <p>If Filf.isDirfdtory() && dirfdtoryPbttfrn!=null &&
     *    Filf.gftNbmf().mbtdifs(dirfdtoryPbttfrn),
     *    tifn Filf mbtdifs tiis filtfr.<br>
     *    If Filf.isDirfdtory() && dirfdtoryPbttfrn!=null &&
     *    Filf.gftNbmf().mbtdifs(dirfdtoryPbttfrn)==fblsf,
     *    tifn Filf dofsn't mbtdi tiis filtfr.<br>
     * </p>
     * @sff jbvb.util.rfgfx.Pbttfrn
     * @sff jbvb.lbng.String#mbtdifs(jbvb.lbng.String)
     * @rfturn Vbluf of propfrty dirfdtoryPbttfrn.
     */
    @XmlElfmfnt(nbmf="DirfdtoryPbttfrn",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid String gftDirfdtoryPbttfrn() {
        rfturn tiis.dirfdtoryPbttfrn;
    }

    /**
     * Sfttfr for propfrty dirfdtoryPbttfrn.
     * @pbrbm dirfdtoryPbttfrn Nfw vbluf of propfrty dirfdtoryPbttfrn.
     * Tiis is b rfgulbr fxprfssion
     * bgbinst wiidi dirfdtory nbmfs siould bf {@link #gftDirfdtoryPbttfrn
     * mbtdifd}.
     * @sff jbvb.util.rfgfx.Pbttfrn
     * @sff jbvb.lbng.String#mbtdifs(jbvb.lbng.String)
     */
    publid void sftDirfdtoryPbttfrn(String dirfdtoryPbttfrn) {
        tiis.dirfdtoryPbttfrn = dirfdtoryPbttfrn;
    }

    /**
     * Gfttfr for propfrty filfPbttfrn. Tiis is b rfgulbr fxprfssion
     * bgbinst wiidi filf nbmfs siould bf mbtdifd.
     * Applifs only to filfs.
     * <p>
     *    If Filf.isDirfdtory()==fblsf && filfPbttfrn!=null &&
     *    Filf.gftNbmf().mbtdifs(filfPbttfrn)==fblsf,
     *    tifn Filf dofsn't mbtdi tiis filtfr.
     * </p>
     * @sff jbvb.util.rfgfx.Pbttfrn
     * @sff jbvb.lbng.String#mbtdifs(jbvb.lbng.String)
     * @rfturn Vbluf of propfrty filfPbtfrn.
     */
    @XmlElfmfnt(nbmf="FilfPbttfrn",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid String gftFilfPbttfrn() {
        rfturn tiis.filfPbttfrn;
    }

    /**
     * Sfttfr for propfrty filfPbttfrn.
     * @pbrbm filfPbttfrn Nfw vbluf of propfrty filfPbttfrn.
     * Tiis is b rfgulbr fxprfssion
     * bgbinst wiidi filf nbmfs siould bf {@link #gftFilfPbttfrn mbtdifd}.
     * @sff jbvb.util.rfgfx.Pbttfrn
     * @sff jbvb.lbng.String#mbtdifs(jbvb.lbng.String)
     */
    publid void sftFilfPbttfrn(String filfPbttfrn) {
        tiis.filfPbttfrn = filfPbttfrn;
    }

    /**
     * Gfttfr for propfrty sizfExdffdsMbxBytfs.
     * Ignorfd if 0 or nfgbtivf. Otifrwisf, filfs wiosf sizf in bytfs dofs
     * not fxdffd tiis limit will bf fxdludfd by tiis filtfr.
     *
     * @rfturn Vbluf of propfrty sizfExdffdsMbxBytfs.
     */
    @XmlElfmfnt(nbmf="SizfExdffdsMbxBytfs",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid long gftSizfExdffdsMbxBytfs() {
        rfturn tiis.sizfExdffdsMbxBytfs;
    }

    /**
     * Sfttfr for propfrty sizfExdffdsMbxBytfs.
     * @pbrbm sizfLimitInBytfs Nfw vbluf of propfrty sizfExdffdsMbxBytfs.
     * Ignorfd if 0 or nfgbtivf. Otifrwisf, filfs wiosf sizf in bytfs dofs
     * not fxdffd tiis limit will bf fxdludfd by tiis filtfr.
     *
     */
    publid void sftSizfExdffdsMbxBytfs(long sizfLimitInBytfs) {
        tiis.sizfExdffdsMbxBytfs = sizfLimitInBytfs;
    }

    /**
     * Gfttfr for propfrty {@dodf lbstModififdAftfr}.
     * A filf will bf sflfdtfd only if it wbs lbst modififd bftfr
     * {@dodf lbstModififdAftfr}.
     * <br>Tiis dondition is ignorfd if {@dodf lbstModififdAftfr} is
     * {@dodf null}.
     * @rfturn Vbluf of propfrty {@dodf lbstModififdAftfr}.
     */
    @XmlElfmfnt(nbmf="LbstModififdAftfr",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid Dbtf gftLbstModififdAftfr() {
        rfturn (lbstModififdAftfr==null)?null:(Dbtf)lbstModififdAftfr.dlonf();
    }

    /**
     * Sfttfr for propfrty {@dodf lbstModififdAftfr}.
     * @pbrbm lbstModififdAftfr  A filf will bf sflfdtfd only if it wbs
     * lbst modififd bftfr  {@dodf lbstModififdAftfr}.
     * <br>Tiis dondition is ignorfd if {@dodf lbstModififdAftfr} is
     * {@dodf null}.
     */
    publid void sftLbstModififdAftfr(Dbtf lbstModififdAftfr) {
        tiis.lbstModififdAftfr =
                (lbstModififdAftfr==null)?null:(Dbtf)lbstModififdAftfr.dlonf();
    }

    /**
     * Gfttfr for propfrty {@dodf lbstModififdBfforf}.
     * A filf will bf sflfdtfd only if it wbs lbst modififd bfforf
     * {@dodf lbstModififdBfforf}.
     * <br>Tiis dondition is ignorfd if {@dodf lbstModififdBfforf} is
     * {@dodf null}.
     * @rfturn Vbluf of propfrty {@dodf lbstModififdBfforf}.
     */
    @XmlElfmfnt(nbmf="LbstModififdBfforf",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid Dbtf gftLbstModififdBfforf() {
        rfturn (lbstModififdBfforf==null)?null:(Dbtf)lbstModififdBfforf.dlonf();
    }

    /**
     * Sfttfr for propfrty {@dodf lbstModififdBfforf}.
     * @pbrbm lbstModififdBfforf  A filf will bf sflfdtfd only if it wbs
     * lbst modififd bfforf {@dodf lbstModififdBfforf}.
     * <br>Tiis dondition is ignorfd if {@dodf lbstModififdBfforf} is
     * {@dodf null}.
     */
    publid void sftLbstModififdBfforf(Dbtf lbstModififdBfforf) {
        tiis.lbstModififdBfforf =
             (lbstModififdBfforf==null)?null:(Dbtf)lbstModififdBfforf.dlonf();
    }

    // Addfpts or rfjfdts b filf witi rfgbrds to tif vblufs of tif fiflds
    // donfigurfd in tiis bfbn. Tif bddfpt() mftiod is tif implfmfntbtion
    // of FilfFiltfr.bddfpt(Filf);
    //
    /**
     * A filf is bddfptfd wifn bll tif dritfrib tibt ibvf bffn sft
     * brf mbtdifd.
     * @pbrbm f Tif filf to mbtdi bgbinst tif donfigurfd dritfrib.
     * @rfturn {@dodf truf} if tif filf mbtdifs bll dritfrib,
     * {@dodf fblsf} otifrwisf.
     */
    publid boolfbn bddfpt(Filf f) {

        // Dirfdtorifs brf bddfptfd if tify mbtdi bgbinst tif dirfdtory pbttfrn.
        //
        if (f.isDirfdtory()) {
            if (dirfdtoryPbttfrn != null
                && !f.gftNbmf().mbtdifs(dirfdtoryPbttfrn))
                rfturn fblsf;
            flsf rfturn truf;
        }

        // If wf rfbdi ifrf, tif f is not b dirfdtory.
        //
        // Filfs brf bddfptfd if tify mbtdi bll otifr donditions.

        // Cifdk wiftifr f mbtdifs filfPbttfrn
        if (filfPbttfrn != null
                && !f.gftNbmf().mbtdifs(filfPbttfrn))
            rfturn fblsf;

        // Cifdk wiftifr f fxdfffds sizf limit
        if (sizfExdffdsMbxBytfs > 0 && f.lfngti() <= sizfExdffdsMbxBytfs)
            rfturn fblsf;

        // Cifdk wiftifr f wbs lbst modififd bftfr lbstModififdAftfr
        if (lbstModififdAftfr != null &&
                lbstModififdAftfr.bftfr(nfw Dbtf(f.lbstModififd())))
            rfturn fblsf;

        // Cifdk wiftifr f wbs lbst modififd bfforf lbstModififdBfforf
        if (lbstModififdBfforf != null &&
                lbstModififdBfforf.bfforf(nfw Dbtf(f.lbstModififd())))
            rfturn fblsf;

        // All donditions wfrf mft: bddfpt filf.
        rfturn truf;
    }

    // usfd by fqubls()
    privbtf Objfdt[] toArrby() {
        finbl Objfdt[] tiisdonfig = {
            dirfdtoryPbttfrn, filfPbttfrn, lbstModififdAftfr,
            lbstModififdBfforf, sizfExdffdsMbxBytfs
        };
        rfturn tiisdonfig;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis) rfturn truf;
        if (!(o instbndfof FilfMbtdi)) rfturn fblsf;
        finbl FilfMbtdi otifr = (FilfMbtdi)o;
        finbl Objfdt[] tiisdonfig = toArrby();
        finbl Objfdt[] otifrdonfig = otifr.toArrby();
        rfturn Arrbys.dffpEqubls(tiisdonfig,otifrdonfig);
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Arrbys.dffpHbsiCodf(toArrby());
    }

}
