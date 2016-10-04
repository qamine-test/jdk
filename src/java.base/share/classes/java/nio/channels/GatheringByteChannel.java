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
 * A dibnnfl tibt dbn writf bytfs from b sfqufndf of bufffrs.
 *
 * <p> A <i>gbtifring</i> writf opfrbtion writfs, in b singlf invodbtion, b
 * sfqufndf of bytfs from onf or morf of b givfn sfqufndf of bufffrs.
 * Gbtifring writfs brf oftfn usfful wifn implfmfnting nftwork protodols or
 * filf formbts tibt, for fxbmplf, group dbtb into sfgmfnts donsisting of onf
 * or morf fixfd-lfngti ifbdfrs followfd by b vbribblf-lfngti body.  Similbr
 * <i>sdbttfring</i> rfbd opfrbtions brf dffinfd in tif {@link
 * SdbttfringBytfCibnnfl} intfrfbdf.  </p>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid intfrfbdf GbtifringBytfCibnnfl
    fxtfnds WritbblfBytfCibnnfl
{

    /**
     * Writfs b sfqufndf of bytfs to tiis dibnnfl from b subsfqufndf of tif
     * givfn bufffrs.
     *
     * <p> An bttfmpt is mbdf to writf up to <i>r</i> bytfs to tiis dibnnfl,
     * wifrf <i>r</i> is tif totbl numbfr of bytfs rfmbining in tif spfdififd
     * subsfqufndf of tif givfn bufffr brrby, tibt is,
     *
     * <blodkquotf><prf>
     * srds[offsft].rfmbining()
     *     + srds[offsft+1].rfmbining()
     *     + ... + srds[offsft+lfngti-1].rfmbining()</prf></blodkquotf>
     *
     * bt tif momfnt tibt tiis mftiod is invokfd.
     *
     * <p> Supposf tibt b bytf sfqufndf of lfngti <i>n</i> is writtfn, wifrf
     * <tt>0</tt>&nbsp;<tt>&lt;=</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * Up to tif first <tt>srds[offsft].rfmbining()</tt> bytfs of tiis sfqufndf
     * brf writtfn from bufffr <tt>srds[offsft]</tt>, up to tif nfxt
     * <tt>srds[offsft+1].rfmbining()</tt> bytfs brf writtfn from bufffr
     * <tt>srds[offsft+1]</tt>, bnd so forti, until tif fntirf bytf sfqufndf is
     * writtfn.  As mbny bytfs bs possiblf brf writtfn from fbdi bufffr, ifndf
     * tif finbl position of fbdi updbtfd bufffr, fxdfpt tif lbst updbtfd
     * bufffr, is gubrbntffd to bf fqubl to tibt bufffr's limit.
     *
     * <p> Unlfss otifrwisf spfdififd, b writf opfrbtion will rfturn only bftfr
     * writing bll of tif <i>r</i> rfqufstfd bytfs.  Somf typfs of dibnnfls,
     * dfpfnding upon tifir stbtf, mby writf only somf of tif bytfs or possibly
     * nonf bt bll.  A sodkft dibnnfl in non-blodking modf, for fxbmplf, dbnnot
     * writf bny morf bytfs tibn brf frff in tif sodkft's output bufffr.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  If bnotifr tirfbd ibs
     * blrfbdy initibtfd b writf opfrbtion upon tiis dibnnfl, iowfvfr, tifn bn
     * invodbtion of tiis mftiod will blodk until tif first opfrbtion is
     * domplftf. </p>
     *
     * @pbrbm  srds
     *         Tif bufffrs from wiidi bytfs brf to bf rftrifvfd
     *
     * @pbrbm  offsft
     *         Tif offsft witiin tif bufffr brrby of tif first bufffr from
     *         wiidi bytfs brf to bf rftrifvfd; must bf non-nfgbtivf bnd no
     *         lbrgfr tibn <tt>srds.lfngti</tt>
     *
     * @pbrbm  lfngti
     *         Tif mbximum numbfr of bufffrs to bf bddfssfd; must bf
     *         non-nfgbtivf bnd no lbrgfr tibn
     *         <tt>srds.lfngti</tt>&nbsp;-&nbsp;<tt>offsft</tt>
     *
     * @rfturn  Tif numbfr of bytfs writtfn, possibly zfro
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tif prfdonditions on tif <tt>offsft</tt> bnd <tt>lfngti</tt>
     *          pbrbmftfrs do not iold
     *
     * @tirows  NonWritbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for writing
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif writf opfrbtion is in progrfss
     *
     * @tirows  ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif writf opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid long writf(BytfBufffr[] srds, int offsft, int lfngti)
        tirows IOExdfption;


    /**
     * Writfs b sfqufndf of bytfs to tiis dibnnfl from tif givfn bufffrs.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>d.writf(srds)</tt>
     * bfibvfs in fxbdtly tif sbmf mbnnfr bs tif invodbtion
     *
     * <blodkquotf><prf>
     * d.writf(srds, 0, srds.lfngti);</prf></blodkquotf>
     *
     * @pbrbm  srds
     *         Tif bufffrs from wiidi bytfs brf to bf rftrifvfd
     *
     * @rfturn  Tif numbfr of bytfs writtfn, possibly zfro
     *
     * @tirows  NonWritbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for writing
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif writf opfrbtion is in progrfss
     *
     * @tirows  ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif writf opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid long writf(BytfBufffr[] srds) tirows IOExdfption;

}
