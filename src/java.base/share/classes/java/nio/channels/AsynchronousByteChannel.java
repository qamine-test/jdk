/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibnnfls;

import jbvb.nio.BytfBufffr;
import jbvb.util.dondurrfnt.Futurf;

/**
 * An bsyndironous dibnnfl tibt dbn rfbd bnd writf bytfs.
 *
 * <p> Somf dibnnfls mby not bllow morf tibn onf rfbd or writf to bf outstbnding
 * bt bny givfn timf. If b tirfbd invokfs b rfbd mftiod bfforf b prfvious rfbd
 * opfrbtion ibs domplftfd tifn b {@link RfbdPfndingExdfption} will bf tirown.
 * Similbrly, if b writf mftiod is invokfd bfforf b prfvious writf ibs domplftfd
 * tifn {@link WritfPfndingExdfption} is tirown. Wiftifr or not otifr kinds of
 * I/O opfrbtions mby prodffd dondurrfntly witi b rfbd opfrbtion dfpfnds upon
 * tif typf of tif dibnnfl.
 *
 * <p> Notf tibt {@link jbvb.nio.BytfBufffr BytfBufffrs} brf not sbff for usf by
 * multiplf dondurrfnt tirfbds. Wifn b rfbd or writf opfrbtion is initibtfd tifn
 * dbrf must bf tbkfn to fnsurf tibt tif bufffr is not bddfssfd until tif
 * opfrbtion domplftfs.
 *
 * @sff Cibnnfls#nfwInputStrfbm(AsyndironousBytfCibnnfl)
 * @sff Cibnnfls#nfwOutputStrfbm(AsyndironousBytfCibnnfl)
 *
 * @sindf 1.7
 */

publid intfrfbdf AsyndironousBytfCibnnfl
    fxtfnds AsyndironousCibnnfl
{
    /**
     * Rfbds b sfqufndf of bytfs from tiis dibnnfl into tif givfn bufffr.
     *
     * <p> Tiis mftiod initibtfs bn bsyndironous rfbd opfrbtion to rfbd b
     * sfqufndf of bytfs from tiis dibnnfl into tif givfn bufffr. Tif {@dodf
     * ibndlfr} pbrbmftfr is b domplftion ibndlfr tibt is invokfd wifn tif rfbd
     * opfrbtion domplftfs (or fbils). Tif rfsult pbssfd to tif domplftion
     * ibndlfr is tif numbfr of bytfs rfbd or {@dodf -1} if no bytfs dould bf
     * rfbd bfdbusf tif dibnnfl ibs rfbdifd fnd-of-strfbm.
     *
     * <p> Tif rfbd opfrbtion mby rfbd up to <i>r</i> bytfs from tif dibnnfl,
     * wifrf <i>r</i> is tif numbfr of bytfs rfmbining in tif bufffr, tibt is,
     * {@dodf dst.rfmbining()} bt tif timf tibt tif rfbd is bttfmptfd. Wifrf
     * <i>r</i> is 0, tif rfbd opfrbtion domplftfs immfdibtfly witi b rfsult of
     * {@dodf 0} witiout initibting bn I/O opfrbtion.
     *
     * <p> Supposf tibt b bytf sfqufndf of lfngti <i>n</i> is rfbd, wifrf
     * <tt>0</tt>&nbsp;<tt>&lt;</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * Tiis bytf sfqufndf will bf trbnsffrrfd into tif bufffr so tibt tif first
     * bytf in tif sfqufndf is bt indfx <i>p</i> bnd tif lbst bytf is bt indfx
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>,
     * wifrf <i>p</i> is tif bufffr's position bt tif momfnt tif rfbd is
     * pfrformfd. Upon domplftion tif bufffr's position will bf fqubl to
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>; its limit will not ibvf dibngfd.
     *
     * <p> Bufffrs brf not sbff for usf by multiplf dondurrfnt tirfbds so dbrf
     * siould bf tbkfn to not bddfss tif bufffr until tif opfrbtion ibs
     * domplftfd.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf. Somf dibnnfl typfs mby not
     * bllow morf tibn onf rfbd to bf outstbnding bt bny givfn timf. If b tirfbd
     * initibtfs b rfbd opfrbtion bfforf b prfvious rfbd opfrbtion ibs
     * domplftfd tifn b {@link RfbdPfndingExdfption} will bf tirown.
     *
     * @pbrbm   <A>
     *          Tif typf of tif bttbdimfnt
     * @pbrbm   dst
     *          Tif bufffr into wiidi bytfs brf to bf trbnsffrrfd
     * @pbrbm   bttbdimfnt
     *          Tif objfdt to bttbdi to tif I/O opfrbtion; dbn bf {@dodf null}
     * @pbrbm   ibndlfr
     *          Tif domplftion ibndlfr
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif bufffr is rfbd-only
     * @tirows  RfbdPfndingExdfption
     *          If tif dibnnfl dofs not bllow morf tibn onf rfbd to bf outstbnding
     *          bnd b prfvious rfbd ibs not domplftfd
     * @tirows  SiutdownCibnnflGroupExdfption
     *          If tif dibnnfl is bssodibtfd witi b {@link AsyndironousCibnnflGroup
     *          group} tibt ibs tfrminbtfd
     */
    <A> void rfbd(BytfBufffr dst,
                  A bttbdimfnt,
                  ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr);

    /**
     * Rfbds b sfqufndf of bytfs from tiis dibnnfl into tif givfn bufffr.
     *
     * <p> Tiis mftiod initibtfs bn bsyndironous rfbd opfrbtion to rfbd b
     * sfqufndf of bytfs from tiis dibnnfl into tif givfn bufffr. Tif mftiod
     * bfibvfs in fxbdtly tif sbmf mbnnfr bs tif {@link
     * #rfbd(BytfBufffr,Objfdt,ComplftionHbndlfr)
     * rfbd(BytfBufffr,Objfdt,ComplftionHbndlfr)} mftiod fxdfpt tibt instfbd
     * of spfdifying b domplftion ibndlfr, tiis mftiod rfturns b {@dodf Futurf}
     * rfprfsfnting tif pfnding rfsult. Tif {@dodf Futurf}'s {@link Futurf#gft()
     * gft} mftiod rfturns tif numbfr of bytfs rfbd or {@dodf -1} if no bytfs
     * dould bf rfbd bfdbusf tif dibnnfl ibs rfbdifd fnd-of-strfbm.
     *
     * @pbrbm   dst
     *          Tif bufffr into wiidi bytfs brf to bf trbnsffrrfd
     *
     * @rfturn  A Futurf rfprfsfnting tif rfsult of tif opfrbtion
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif bufffr is rfbd-only
     * @tirows  RfbdPfndingExdfption
     *          If tif dibnnfl dofs not bllow morf tibn onf rfbd to bf outstbnding
     *          bnd b prfvious rfbd ibs not domplftfd
     */
    Futurf<Intfgfr> rfbd(BytfBufffr dst);

    /**
     * Writfs b sfqufndf of bytfs to tiis dibnnfl from tif givfn bufffr.
     *
     * <p> Tiis mftiod initibtfs bn bsyndironous writf opfrbtion to writf b
     * sfqufndf of bytfs to tiis dibnnfl from tif givfn bufffr. Tif {@dodf
     * ibndlfr} pbrbmftfr is b domplftion ibndlfr tibt is invokfd wifn tif writf
     * opfrbtion domplftfs (or fbils). Tif rfsult pbssfd to tif domplftion
     * ibndlfr is tif numbfr of bytfs writtfn.
     *
     * <p> Tif writf opfrbtion mby writf up to <i>r</i> bytfs to tif dibnnfl,
     * wifrf <i>r</i> is tif numbfr of bytfs rfmbining in tif bufffr, tibt is,
     * {@dodf srd.rfmbining()} bt tif timf tibt tif writf is bttfmptfd. Wifrf
     * <i>r</i> is 0, tif writf opfrbtion domplftfs immfdibtfly witi b rfsult of
     * {@dodf 0} witiout initibting bn I/O opfrbtion.
     *
     * <p> Supposf tibt b bytf sfqufndf of lfngti <i>n</i> is writtfn, wifrf
     * <tt>0</tt>&nbsp;<tt>&lt;</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * Tiis bytf sfqufndf will bf trbnsffrrfd from tif bufffr stbrting bt indfx
     * <i>p</i>, wifrf <i>p</i> is tif bufffr's position bt tif momfnt tif
     * writf is pfrformfd; tif indfx of tif lbst bytf writtfn will bf
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>.
     * Upon domplftion tif bufffr's position will bf fqubl to
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>; its limit will not ibvf dibngfd.
     *
     * <p> Bufffrs brf not sbff for usf by multiplf dondurrfnt tirfbds so dbrf
     * siould bf tbkfn to not bddfss tif bufffr until tif opfrbtion ibs
     * domplftfd.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf. Somf dibnnfl typfs mby not
     * bllow morf tibn onf writf to bf outstbnding bt bny givfn timf. If b tirfbd
     * initibtfs b writf opfrbtion bfforf b prfvious writf opfrbtion ibs
     * domplftfd tifn b {@link WritfPfndingExdfption} will bf tirown.
     *
     * @pbrbm   <A>
     *          Tif typf of tif bttbdimfnt
     * @pbrbm   srd
     *          Tif bufffr from wiidi bytfs brf to bf rftrifvfd
     * @pbrbm   bttbdimfnt
     *          Tif objfdt to bttbdi to tif I/O opfrbtion; dbn bf {@dodf null}
     * @pbrbm   ibndlfr
     *          Tif domplftion ibndlfr objfdt
     *
     * @tirows  WritfPfndingExdfption
     *          If tif dibnnfl dofs not bllow morf tibn onf writf to bf outstbnding
     *          bnd b prfvious writf ibs not domplftfd
     * @tirows  SiutdownCibnnflGroupExdfption
     *          If tif dibnnfl is bssodibtfd witi b {@link AsyndironousCibnnflGroup
     *          group} tibt ibs tfrminbtfd
     */
    <A> void writf(BytfBufffr srd,
                   A bttbdimfnt,
                   ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr);

    /**
     * Writfs b sfqufndf of bytfs to tiis dibnnfl from tif givfn bufffr.
     *
     * <p> Tiis mftiod initibtfs bn bsyndironous writf opfrbtion to writf b
     * sfqufndf of bytfs to tiis dibnnfl from tif givfn bufffr. Tif mftiod
     * bfibvfs in fxbdtly tif sbmf mbnnfr bs tif {@link
     * #writf(BytfBufffr,Objfdt,ComplftionHbndlfr)
     * writf(BytfBufffr,Objfdt,ComplftionHbndlfr)} mftiod fxdfpt tibt instfbd
     * of spfdifying b domplftion ibndlfr, tiis mftiod rfturns b {@dodf Futurf}
     * rfprfsfnting tif pfnding rfsult. Tif {@dodf Futurf}'s {@link Futurf#gft()
     * gft} mftiod rfturns tif numbfr of bytfs writtfn.
     *
     * @pbrbm   srd
     *          Tif bufffr from wiidi bytfs brf to bf rftrifvfd
     *
     * @rfturn A Futurf rfprfsfnting tif rfsult of tif opfrbtion
     *
     * @tirows  WritfPfndingExdfption
     *          If tif dibnnfl dofs not bllow morf tibn onf writf to bf outstbnding
     *          bnd b prfvious writf ibs not domplftfd
     */
    Futurf<Intfgfr> writf(BytfBufffr srd);
}
