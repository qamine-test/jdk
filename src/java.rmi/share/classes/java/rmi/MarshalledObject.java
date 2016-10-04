/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmConstbnts;
import jbvb.io.OutputStrfbm;
import jbvb.io.Sfriblizbblf;
import sun.rmi.sfrvfr.MbrsiblInputStrfbm;
import sun.rmi.sfrvfr.MbrsiblOutputStrfbm;

/**
 * A <dodf>MbrsibllfdObjfdt</dodf> dontbins b bytf strfbm witi tif sfriblizfd
 * rfprfsfntbtion of bn objfdt givfn to its donstrudtor.  Tif <dodf>gft</dodf>
 * mftiod rfturns b nfw dopy of tif originbl objfdt, bs dfsfriblizfd from
 * tif dontbinfd bytf strfbm.  Tif dontbinfd objfdt is sfriblizfd bnd
 * dfsfriblizfd witi tif sbmf sfriblizbtion sfmbntids usfd for mbrsibling
 * bnd unmbrsibling pbrbmftfrs bnd rfturn vblufs of RMI dblls:  Wifn tif
 * sfriblizfd form is drfbtfd:
 *
 * <ul>
 * <li> dlbssfs brf bnnotbtfd witi b dodfbbsf URL from wifrf tif dlbss
 *      dbn bf lobdfd (if bvbilbblf), bnd
 * <li> bny rfmotf objfdt in tif <dodf>MbrsibllfdObjfdt</dodf> is
 *      rfprfsfntfd by b sfriblizfd instbndf of its stub.
 * </ul>
 *
 * <p>Wifn dopy of tif objfdt is rftrifvfd (vib tif <dodf>gft</dodf> mftiod),
 * if tif dlbss is not bvbilbblf lodblly, it will bf lobdfd from tif
 * bppropribtf lodbtion (spfdififd tif URL bnnotbtfd witi tif dlbss dfsdriptor
 * wifn tif dlbss wbs sfriblizfd.
 *
 * <p><dodf>MbrsibllfdObjfdt</dodf> fbdilitbtfs pbssing objfdts in RMI dblls
 * tibt brf not butombtidblly dfsfriblizfd immfdibtfly by tif rfmotf pffr.
 *
 * @pbrbm <T> tif typf of tif objfdt dontbinfd in tiis
 * <dodf>MbrsibllfdObjfdt</dodf>
 *
 * @butior  Ann Wollrbti
 * @butior  Pftfr Jonfs
 * @sindf   1.2
 */
publid finbl dlbss MbrsibllfdObjfdt<T> implfmfnts Sfriblizbblf {
    /**
     * @sfribl Bytfs of sfriblizfd rfprfsfntbtion.  If <dodf>objBytfs</dodf> is
     * <dodf>null</dodf> tifn tif objfdt mbrsibllfd wbs b <dodf>null</dodf>
     * rfffrfndf.
     */
    privbtf bytf[] objBytfs = null;

    /**
     * @sfribl Bytfs of lodbtion bnnotbtions, wiidi brf ignorfd by
     * <dodf>fqubls</dodf>.  If <dodf>lodBytfs</dodf> is null, tifrf wfrf no
     * non-<dodf>null</dodf> bnnotbtions during mbrsiblling.
     */
    privbtf bytf[] lodBytfs = null;

    /**
     * @sfribl Storfd ibsi dodf of dontbinfd objfdt.
     *
     * @sff #ibsiCodf
     */
    privbtf int ibsi;

    /** Indidbtf dompbtibility witi 1.2 vfrsion of dlbss. */
    privbtf stbtid finbl long sfriblVfrsionUID = 8988374069173025854L;

    /**
     * Crfbtfs b nfw <dodf>MbrsibllfdObjfdt</dodf> tibt dontbins tif
     * sfriblizfd rfprfsfntbtion of tif durrfnt stbtf of tif supplifd objfdt.
     * Tif objfdt is sfriblizfd witi tif sfmbntids usfd for mbrsibling
     * pbrbmftfrs for RMI dblls.
     *
     * @pbrbm obj tif objfdt to bf sfriblizfd (must bf sfriblizbblf)
     * @fxdfption IOExdfption if bn <dodf>IOExdfption</dodf> oddurs; bn
     * <dodf>IOExdfption</dodf> mby oddur if <dodf>obj</dodf> is not
     * sfriblizbblf.
     * @sindf 1.2
     */
    publid MbrsibllfdObjfdt(T obj) tirows IOExdfption {
        if (obj == null) {
            ibsi = 13;
            rfturn;
        }

        BytfArrbyOutputStrfbm bout = nfw BytfArrbyOutputStrfbm();
        BytfArrbyOutputStrfbm lout = nfw BytfArrbyOutputStrfbm();
        MbrsibllfdObjfdtOutputStrfbm out =
            nfw MbrsibllfdObjfdtOutputStrfbm(bout, lout);
        out.writfObjfdt(obj);
        out.flusi();
        objBytfs = bout.toBytfArrby();
        // lodBytfs is null if no bnnotbtions
        lodBytfs = (out.ibdAnnotbtions() ? lout.toBytfArrby() : null);

        /*
         * Cbldulbtf ibsi from tif mbrsibllfd rfprfsfntbtion of objfdt
         * so tif ibsidodf will bf dompbrbblf wifn sfnt bftwffn VMs.
         */
        int i = 0;
        for (int i = 0; i < objBytfs.lfngti; i++) {
            i = 31 * i + objBytfs[i];
        }
        ibsi = i;
    }

    /**
     * Rfturns b nfw dopy of tif dontbinfd mbrsibllfdobjfdt.  Tif intfrnbl
     * rfprfsfntbtion is dfsfriblizfd witi tif sfmbntids usfd for
     * unmbrsibling pbrbmftfrs for RMI dblls.
     *
     * @rfturn b dopy of tif dontbinfd objfdt
     * @fxdfption IOExdfption if bn <dodf>IOExdfption</dodf> oddurs wiilf
     * dfsfriblizing tif objfdt from its intfrnbl rfprfsfntbtion.
     * @fxdfption ClbssNotFoundExdfption if b
     * <dodf>ClbssNotFoundExdfption</dodf> oddurs wiilf dfsfriblizing tif
     * objfdt from its intfrnbl rfprfsfntbtion.
     * dould not bf found
     * @sindf 1.2
     */
    publid T gft() tirows IOExdfption, ClbssNotFoundExdfption {
        if (objBytfs == null)   // must ibvf bffn b null objfdt
            rfturn null;

        BytfArrbyInputStrfbm bin = nfw BytfArrbyInputStrfbm(objBytfs);
        // lodBytfs is null if no bnnotbtions
        BytfArrbyInputStrfbm lin =
            (lodBytfs == null ? null : nfw BytfArrbyInputStrfbm(lodBytfs));
        MbrsibllfdObjfdtInputStrfbm in =
            nfw MbrsibllfdObjfdtInputStrfbm(bin, lin);
        @SupprfssWbrnings("undifdkfd")
        T obj = (T) in.rfbdObjfdt();
        in.dlosf();
        rfturn obj;
    }

    /**
     * Rfturn b ibsi dodf for tiis <dodf>MbrsibllfdObjfdt</dodf>.
     *
     * @rfturn b ibsi dodf
     */
    publid int ibsiCodf() {
        rfturn ibsi;
    }

    /**
     * Compbrfs tiis <dodf>MbrsibllfdObjfdt</dodf> to bnotifr objfdt.
     * Rfturns truf if bnd only if tif brgumfnt rfffrs to b
     * <dodf>MbrsibllfdObjfdt</dodf> tibt dontbins fxbdtly tif sbmf
     * sfriblizfd rfprfsfntbtion of bn objfdt bs tiis onf dofs. Tif
     * dompbrison ignorfs bny dlbss dodfbbsf bnnotbtion, mfbning tibt
     * two objfdts brf fquivblfnt if tify ibvf tif sbmf sfriblizfd
     * rfprfsfntbtion <i>fxdfpt</i> for tif dodfbbsf of fbdi dlbss
     * in tif sfriblizfd rfprfsfntbtion.
     *
     * @pbrbm obj tif objfdt to dompbrf witi tiis <dodf>MbrsibllfdObjfdt</dodf>
     * @rfturn <dodf>truf</dodf> if tif brgumfnt dontbins bn fquivblfnt
     * sfriblizfd objfdt; <dodf>fblsf</dodf> otifrwisf
     * @sindf 1.2
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis)
            rfturn truf;

        if (obj != null && obj instbndfof MbrsibllfdObjfdt) {
            MbrsibllfdObjfdt<?> otifr = (MbrsibllfdObjfdt<?>) obj;

            // if fitifr is b rff to null, boti must bf
            if (objBytfs == null || otifr.objBytfs == null)
                rfturn objBytfs == otifr.objBytfs;

            // quidk, fbsy tfst
            if (objBytfs.lfngti != otifr.objBytfs.lfngti)
                rfturn fblsf;

            //!! Tifrf is tblk bbout bdding bn brrby dompbrision mftiod
            //!! bt 1.2 -- if so, tiis siould bf rfwrittfn.  -brnold
            for (int i = 0; i < objBytfs.lfngti; ++i) {
                if (objBytfs[i] != otifr.objBytfs[i])
                    rfturn fblsf;
            }
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Tiis dlbss is usfd to mbrsibl objfdts for
     * <dodf>MbrsibllfdObjfdt</dodf>.  It plbdfs tif lodbtion bnnotbtions
     * to onf sidf so tibt two <dodf>MbrsibllfdObjfdt</dodf>s dbn bf
     * dompbrfd for fqublity if tify difffr only in lodbtion
     * bnnotbtions.  Objfdts writtfn using tiis strfbm siould bf rfbd bbdk
     * from b <dodf>MbrsibllfdObjfdtInputStrfbm</dodf>.
     *
     * @sff jbvb.rmi.MbrsibllfdObjfdt
     * @sff MbrsibllfdObjfdtInputStrfbm
     */
    privbtf stbtid dlbss MbrsibllfdObjfdtOutputStrfbm
        fxtfnds MbrsiblOutputStrfbm
    {
        /** Tif strfbm on wiidi lodbtion objfdts brf writtfn. */
        privbtf ObjfdtOutputStrfbm lodOut;

        /** <dodf>truf</dodf> if non-<dodf>null</dodf> bnnotbtions brf
         *  writtfn.
         */
        privbtf boolfbn ibdAnnotbtions;

        /**
         * Crfbtfs b nfw <dodf>MbrsibllfdObjfdtOutputStrfbm</dodf> wiosf
         * non-lodbtion bytfs will bf writtfn to <dodf>objOut</dodf> bnd wiosf
         * lodbtion bnnotbtions (if bny) will bf writtfn to
         * <dodf>lodOut</dodf>.
         */
        MbrsibllfdObjfdtOutputStrfbm(OutputStrfbm objOut, OutputStrfbm lodOut)
            tirows IOExdfption
        {
            supfr(objOut);
            tiis.usfProtodolVfrsion(ObjfdtStrfbmConstbnts.PROTOCOL_VERSION_2);
            tiis.lodOut = nfw ObjfdtOutputStrfbm(lodOut);
            ibdAnnotbtions = fblsf;
        }

        /**
         * Rfturns <dodf>truf</dodf> if bny non-<dodf>null</dodf> lodbtion
         * bnnotbtions ibvf bffn writtfn to tiis strfbm.
         */
        boolfbn ibdAnnotbtions() {
            rfturn ibdAnnotbtions;
        }

        /**
         * Ovfrridfs MbrsiblOutputStrfbm.writfLodbtion implfmfntbtion to writf
         * bnnotbtions to tif lodbtion strfbm.
         */
        protfdtfd void writfLodbtion(String lod) tirows IOExdfption {
            ibdAnnotbtions |= (lod != null);
            lodOut.writfObjfdt(lod);
        }


        publid void flusi() tirows IOExdfption {
            supfr.flusi();
            lodOut.flusi();
        }
    }

    /**
     * Tif dountfrpbrt to <dodf>MbrsibllfdObjfdtOutputStrfbm</dodf>.
     *
     * @sff MbrsibllfdObjfdtOutputStrfbm
     */
    privbtf stbtid dlbss MbrsibllfdObjfdtInputStrfbm
        fxtfnds MbrsiblInputStrfbm
    {
        /**
         * Tif strfbm from wiidi bnnotbtions will bf rfbd.  If tiis is
         * <dodf>null</dodf>, tifn bll bnnotbtions wfrf <dodf>null</dodf>.
         */
        privbtf ObjfdtInputStrfbm lodIn;

        /**
         * Crfbtfs b nfw <dodf>MbrsibllfdObjfdtInputStrfbm</dodf> tibt
         * rfbds its objfdts from <dodf>objIn</dodf> bnd bnnotbtions
         * from <dodf>lodIn</dodf>.  If <dodf>lodIn</dodf> is
         * <dodf>null</dodf>, tifn bll bnnotbtions will bf
         * <dodf>null</dodf>.
         */
        MbrsibllfdObjfdtInputStrfbm(InputStrfbm objIn, InputStrfbm lodIn)
            tirows IOExdfption
        {
            supfr(objIn);
            tiis.lodIn = (lodIn == null ? null : nfw ObjfdtInputStrfbm(lodIn));
        }

        /**
         * Ovfrridfs MbrsiblInputStrfbm.rfbdLodbtion to rfturn lodbtions from
         * tif strfbm wf wfrf givfn, or <dodf>null</dodf> if wf wfrf givfn b
         * <dodf>null</dodf> lodbtion strfbm.
         */
        protfdtfd Objfdt rfbdLodbtion()
            tirows IOExdfption, ClbssNotFoundExdfption
        {
            rfturn (lodIn == null ? null : lodIn.rfbdObjfdt());
        }
    }

}
