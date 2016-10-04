/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.nio.BytfBufffr;


/**
 * A dibnnfl tibt dbn rfbd bytfs.
 *
 * <p> Only onf rfbd opfrbtion upon b rfbdbblf dibnnfl mby bf in progrfss bt
 * bny givfn timf.  If onf tirfbd initibtfs b rfbd opfrbtion upon b dibnnfl
 * tifn bny otifr tirfbd tibt bttfmpts to initibtf bnotifr rfbd opfrbtion will
 * blodk until tif first opfrbtion is domplftf.  Wiftifr or not otifr kinds of
 * I/O opfrbtions mby prodffd dondurrfntly witi b rfbd opfrbtion dfpfnds upon
 * tif typf of tif dibnnfl. </p>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid intfrfbdf RfbdbblfBytfCibnnfl fxtfnds Cibnnfl {

    /**
     * Rfbds b sfqufndf of bytfs from tiis dibnnfl into tif givfn bufffr.
     *
     * <p> An bttfmpt is mbdf to rfbd up to <i>r</i> bytfs from tif dibnnfl,
     * wifrf <i>r</i> is tif numbfr of bytfs rfmbining in tif bufffr, tibt is,
     * <tt>dst.rfmbining()</tt>, bt tif momfnt tiis mftiod is invokfd.
     *
     * <p> Supposf tibt b bytf sfqufndf of lfngti <i>n</i> is rfbd, wifrf
     * <tt>0</tt>&nbsp;<tt>&lt;=</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * Tiis bytf sfqufndf will bf trbnsffrrfd into tif bufffr so tibt tif first
     * bytf in tif sfqufndf is bt indfx <i>p</i> bnd tif lbst bytf is bt indfx
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>,
     * wifrf <i>p</i> is tif bufffr's position bt tif momfnt tiis mftiod is
     * invokfd.  Upon rfturn tif bufffr's position will bf fqubl to
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>; its limit will not ibvf dibngfd.
     *
     * <p> A rfbd opfrbtion migit not fill tif bufffr, bnd in fbdt it migit not
     * rfbd bny bytfs bt bll.  Wiftifr or not it dofs so dfpfnds upon tif
     * nbturf bnd stbtf of tif dibnnfl.  A sodkft dibnnfl in non-blodking modf,
     * for fxbmplf, dbnnot rfbd bny morf bytfs tibn brf immfdibtfly bvbilbblf
     * from tif sodkft's input bufffr; similbrly, b filf dibnnfl dbnnot rfbd
     * bny morf bytfs tibn rfmbin in tif filf.  It is gubrbntffd, iowfvfr, tibt
     * if b dibnnfl is in blodking modf bnd tifrf is bt lfbst onf bytf
     * rfmbining in tif bufffr tifn tiis mftiod will blodk until bt lfbst onf
     * bytf is rfbd.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  If bnotifr tirfbd ibs
     * blrfbdy initibtfd b rfbd opfrbtion upon tiis dibnnfl, iowfvfr, tifn bn
     * invodbtion of tiis mftiod will blodk until tif first opfrbtion is
     * domplftf. </p>
     *
     * @pbrbm  dst
     *         Tif bufffr into wiidi bytfs brf to bf trbnsffrrfd
     *
     * @rfturn  Tif numbfr of bytfs rfbd, possibly zfro, or <tt>-1</tt> if tif
     *          dibnnfl ibs rfbdifd fnd-of-strfbm
     *
     * @tirows  NonRfbdbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for rfbding
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif rfbd opfrbtion is in progrfss
     *
     * @tirows  ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif rfbd opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid int rfbd(BytfBufffr dst) tirows IOExdfption;

}
