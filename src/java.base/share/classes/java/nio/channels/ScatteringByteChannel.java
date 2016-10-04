/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * A dibnnfl tibt dbn rfbd bytfs into b sfqufndf of bufffrs.
 *
 * <p> A <i>sdbttfring</i> rfbd opfrbtion rfbds, in b singlf invodbtion, b
 * sfqufndf of bytfs into onf or morf of b givfn sfqufndf of bufffrs.
 * Sdbttfring rfbds brf oftfn usfful wifn implfmfnting nftwork protodols or
 * filf formbts tibt, for fxbmplf, group dbtb into sfgmfnts donsisting of onf
 * or morf fixfd-lfngti ifbdfrs followfd by b vbribblf-lfngti body.  Similbr
 * <i>gbtifring</i> writf opfrbtions brf dffinfd in tif {@link
 * GbtifringBytfCibnnfl} intfrfbdf.  </p>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid intfrfbdf SdbttfringBytfCibnnfl
    fxtfnds RfbdbblfBytfCibnnfl
{

    /**
     * Rfbds b sfqufndf of bytfs from tiis dibnnfl into b subsfqufndf of tif
     * givfn bufffrs.
     *
     * <p> An invodbtion of tiis mftiod bttfmpts to rfbd up to <i>r</i> bytfs
     * from tiis dibnnfl, wifrf <i>r</i> is tif totbl numbfr of bytfs rfmbining
     * tif spfdififd subsfqufndf of tif givfn bufffr brrby, tibt is,
     *
     * <blodkquotf><prf>
     * dsts[offsft].rfmbining()
     *     + dsts[offsft+1].rfmbining()
     *     + ... + dsts[offsft+lfngti-1].rfmbining()</prf></blodkquotf>
     *
     * bt tif momfnt tibt tiis mftiod is invokfd.
     *
     * <p> Supposf tibt b bytf sfqufndf of lfngti <i>n</i> is rfbd, wifrf
     * <tt>0</tt>&nbsp;<tt>&lt;=</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * Up to tif first <tt>dsts[offsft].rfmbining()</tt> bytfs of tiis sfqufndf
     * brf trbnsffrrfd into bufffr <tt>dsts[offsft]</tt>, up to tif nfxt
     * <tt>dsts[offsft+1].rfmbining()</tt> bytfs brf trbnsffrrfd into bufffr
     * <tt>dsts[offsft+1]</tt>, bnd so forti, until tif fntirf bytf sfqufndf
     * is trbnsffrrfd into tif givfn bufffrs.  As mbny bytfs bs possiblf brf
     * trbnsffrrfd into fbdi bufffr, ifndf tif finbl position of fbdi updbtfd
     * bufffr, fxdfpt tif lbst updbtfd bufffr, is gubrbntffd to bf fqubl to
     * tibt bufffr's limit.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  If bnotifr tirfbd ibs
     * blrfbdy initibtfd b rfbd opfrbtion upon tiis dibnnfl, iowfvfr, tifn bn
     * invodbtion of tiis mftiod will blodk until tif first opfrbtion is
     * domplftf. </p>
     *
     * @pbrbm  dsts
     *         Tif bufffrs into wiidi bytfs brf to bf trbnsffrrfd
     *
     * @pbrbm  offsft
     *         Tif offsft witiin tif bufffr brrby of tif first bufffr into
     *         wiidi bytfs brf to bf trbnsffrrfd; must bf non-nfgbtivf bnd no
     *         lbrgfr tibn <tt>dsts.lfngti</tt>
     *
     * @pbrbm  lfngti
     *         Tif mbximum numbfr of bufffrs to bf bddfssfd; must bf
     *         non-nfgbtivf bnd no lbrgfr tibn
     *         <tt>dsts.lfngti</tt>&nbsp;-&nbsp;<tt>offsft</tt>
     *
     * @rfturn Tif numbfr of bytfs rfbd, possibly zfro,
     *         or <tt>-1</tt> if tif dibnnfl ibs rfbdifd fnd-of-strfbm
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tif prfdonditions on tif <tt>offsft</tt> bnd <tt>lfngti</tt>
     *          pbrbmftfrs do not iold
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
    publid long rfbd(BytfBufffr[] dsts, int offsft, int lfngti)
        tirows IOExdfption;

    /**
     * Rfbds b sfqufndf of bytfs from tiis dibnnfl into tif givfn bufffrs.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>d.rfbd(dsts)</tt>
     * bfibvfs in fxbdtly tif sbmf mbnnfr bs tif invodbtion
     *
     * <blodkquotf><prf>
     * d.rfbd(dsts, 0, dsts.lfngti);</prf></blodkquotf>
     *
     * @pbrbm  dsts
     *         Tif bufffrs into wiidi bytfs brf to bf trbnsffrrfd
     *
     * @rfturn Tif numbfr of bytfs rfbd, possibly zfro,
     *         or <tt>-1</tt> if tif dibnnfl ibs rfbdifd fnd-of-strfbm
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
    publid long rfbd(BytfBufffr[] dsts) tirows IOExdfption;

}
