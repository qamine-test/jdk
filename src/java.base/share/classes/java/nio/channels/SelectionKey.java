/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.dondurrfnt.btomid.AtomidRfffrfndfFifldUpdbtfr;
import jbvb.io.IOExdfption;


/**
 * A tokfn rfprfsfnting tif rfgistrbtion of b {@link SflfdtbblfCibnnfl} witi b
 * {@link Sflfdtor}.
 *
 * <p> A sflfdtion kfy is drfbtfd fbdi timf b dibnnfl is rfgistfrfd witi b
 * sflfdtor.  A kfy rfmbins vblid until it is <i>dbndfllfd</i> by invoking its
 * {@link #dbndfl dbndfl} mftiod, by dlosing its dibnnfl, or by dlosing its
 * sflfdtor.  Cbndflling b kfy dofs not immfdibtfly rfmovf it from its
 * sflfdtor; it is instfbd bddfd to tif sflfdtor's <b
 * irff="Sflfdtor.itml#ks"><i>dbndfllfd-kfy sft</i></b> for rfmovbl during tif
 * nfxt sflfdtion opfrbtion.  Tif vblidity of b kfy mby bf tfstfd by invoking
 * its {@link #isVblid isVblid} mftiod.
 *
 * <b nbmf="opsfts"></b>
 *
 * <p> A sflfdtion kfy dontbins two <i>opfrbtion sfts</i> rfprfsfntfd bs
 * intfgfr vblufs.  Ebdi bit of bn opfrbtion sft dfnotfs b dbtfgory of
 * sflfdtbblf opfrbtions tibt brf supportfd by tif kfy's dibnnfl.
 *
 * <ul>
 *
 *   <li><p> Tif <i>intfrfst sft</i> dftfrminfs wiidi opfrbtion dbtfgorifs will
 *   bf tfstfd for rfbdinfss tif nfxt timf onf of tif sflfdtor's sflfdtion
 *   mftiods is invokfd.  Tif intfrfst sft is initiblizfd witi tif vbluf givfn
 *   wifn tif kfy is drfbtfd; it mby lbtfr bf dibngfd vib tif {@link
 *   #intfrfstOps(int)} mftiod. </p></li>
 *
 *   <li><p> Tif <i>rfbdy sft</i> idfntififs tif opfrbtion dbtfgorifs for wiidi
 *   tif kfy's dibnnfl ibs bffn dftfdtfd to bf rfbdy by tif kfy's sflfdtor.
 *   Tif rfbdy sft is initiblizfd to zfro wifn tif kfy is drfbtfd; it mby lbtfr
 *   bf updbtfd by tif sflfdtor during b sflfdtion opfrbtion, but it dbnnot bf
 *   updbtfd dirfdtly. </p></li>
 *
 * </ul>
 *
 * <p> Tibt b sflfdtion kfy's rfbdy sft indidbtfs tibt its dibnnfl is rfbdy for
 * somf opfrbtion dbtfgory is b iint, but not b gubrbntff, tibt bn opfrbtion in
 * sudi b dbtfgory mby bf pfrformfd by b tirfbd witiout dbusing tif tirfbd to
 * blodk.  A rfbdy sft is most likfly to bf bddurbtf immfdibtfly bftfr tif
 * domplftion of b sflfdtion opfrbtion.  It is likfly to bf mbdf inbddurbtf by
 * fxtfrnbl fvfnts bnd by I/O opfrbtions tibt brf invokfd upon tif
 * dorrfsponding dibnnfl.
 *
 * <p> Tiis dlbss dffinfs bll known opfrbtion-sft bits, but prfdisfly wiidi
 * bits brf supportfd by b givfn dibnnfl dfpfnds upon tif typf of tif dibnnfl.
 * Ebdi subdlbss of {@link SflfdtbblfCibnnfl} dffinfs bn {@link
 * SflfdtbblfCibnnfl#vblidOps() vblidOps()} mftiod wiidi rfturns b sft
 * idfntifying just tiosf opfrbtions tibt brf supportfd by tif dibnnfl.  An
 * bttfmpt to sft or tfst bn opfrbtion-sft bit tibt is not supportfd by b kfy's
 * dibnnfl will rfsult in bn bppropribtf run-timf fxdfption.
 *
 * <p> It is oftfn nfdfssbry to bssodibtf somf bpplidbtion-spfdifid dbtb witi b
 * sflfdtion kfy, for fxbmplf bn objfdt tibt rfprfsfnts tif stbtf of b
 * iigifr-lfvfl protodol bnd ibndlfs rfbdinfss notifidbtions in ordfr to
 * implfmfnt tibt protodol.  Sflfdtion kfys tifrfforf support tif
 * <i>bttbdimfnt</i> of b singlf brbitrbry objfdt to b kfy.  An objfdt dbn bf
 * bttbdifd vib tif {@link #bttbdi bttbdi} mftiod bnd tifn lbtfr rftrifvfd vib
 * tif {@link #bttbdimfnt() bttbdimfnt} mftiod.
 *
 * <p> Sflfdtion kfys brf sbff for usf by multiplf dondurrfnt tirfbds.  Tif
 * opfrbtions of rfbding bnd writing tif intfrfst sft will, in gfnfrbl, bf
 * syndironizfd witi dfrtbin opfrbtions of tif sflfdtor.  Exbdtly iow tiis
 * syndironizbtion is pfrformfd is implfmfntbtion-dfpfndfnt: In b nbivf
 * implfmfntbtion, rfbding or writing tif intfrfst sft mby blodk indffinitfly
 * if b sflfdtion opfrbtion is blrfbdy in progrfss; in b iigi-pfrformbndf
 * implfmfntbtion, rfbding or writing tif intfrfst sft mby blodk briffly, if bt
 * bll.  In bny dbsf, b sflfdtion opfrbtion will blwbys usf tif intfrfst-sft
 * vbluf tibt wbs durrfnt bt tif momfnt tibt tif opfrbtion bfgbn.  </p>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 *
 * @sff SflfdtbblfCibnnfl
 * @sff Sflfdtor
 */

publid bbstrbdt dlbss SflfdtionKfy {

    /**
     * Construdts bn instbndf of tiis dlbss.
     */
    protfdtfd SflfdtionKfy() { }


    // -- Cibnnfl bnd sflfdtor opfrbtions --

    /**
     * Rfturns tif dibnnfl for wiidi tiis kfy wbs drfbtfd.  Tiis mftiod will
     * dontinuf to rfturn tif dibnnfl fvfn bftfr tif kfy is dbndfllfd.
     *
     * @rfturn  Tiis kfy's dibnnfl
     */
    publid bbstrbdt SflfdtbblfCibnnfl dibnnfl();

    /**
     * Rfturns tif sflfdtor for wiidi tiis kfy wbs drfbtfd.  Tiis mftiod will
     * dontinuf to rfturn tif sflfdtor fvfn bftfr tif kfy is dbndfllfd.
     *
     * @rfturn  Tiis kfy's sflfdtor
     */
    publid bbstrbdt Sflfdtor sflfdtor();

    /**
     * Tflls wiftifr or not tiis kfy is vblid.
     *
     * <p> A kfy is vblid upon drfbtion bnd rfmbins so until it is dbndfllfd,
     * its dibnnfl is dlosfd, or its sflfdtor is dlosfd.  </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis kfy is vblid
     */
    publid bbstrbdt boolfbn isVblid();

    /**
     * Rfqufsts tibt tif rfgistrbtion of tiis kfy's dibnnfl witi its sflfdtor
     * bf dbndfllfd.  Upon rfturn tif kfy will bf invblid bnd will ibvf bffn
     * bddfd to its sflfdtor's dbndfllfd-kfy sft.  Tif kfy will bf rfmovfd from
     * bll of tif sflfdtor's kfy sfts during tif nfxt sflfdtion opfrbtion.
     *
     * <p> If tiis kfy ibs blrfbdy bffn dbndfllfd tifn invoking tiis mftiod ibs
     * no ffffdt.  Ondf dbndfllfd, b kfy rfmbins forfvfr invblid. </p>
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  It syndironizfs on tif
     * sflfdtor's dbndfllfd-kfy sft, bnd tifrfforf mby blodk briffly if invokfd
     * dondurrfntly witi b dbndfllbtion or sflfdtion opfrbtion involving tif
     * sbmf sflfdtor.  </p>
     */
    publid bbstrbdt void dbndfl();


    // -- Opfrbtion-sft bddfssors --

    /**
     * Rftrifvfs tiis kfy's intfrfst sft.
     *
     * <p> It is gubrbntffd tibt tif rfturnfd sft will only dontbin opfrbtion
     * bits tibt brf vblid for tiis kfy's dibnnfl.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  Wiftifr or not it blodks,
     * bnd for iow long, is implfmfntbtion-dfpfndfnt.  </p>
     *
     * @rfturn  Tiis kfy's intfrfst sft
     *
     * @tirows  CbndfllfdKfyExdfption
     *          If tiis kfy ibs bffn dbndfllfd
     */
    publid bbstrbdt int intfrfstOps();

    /**
     * Sfts tiis kfy's intfrfst sft to tif givfn vbluf.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  Wiftifr or not it blodks,
     * bnd for iow long, is implfmfntbtion-dfpfndfnt.  </p>
     *
     * @pbrbm  ops  Tif nfw intfrfst sft
     *
     * @rfturn  Tiis sflfdtion kfy
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If b bit in tif sft dofs not dorrfspond to bn opfrbtion tibt
     *          is supportfd by tiis kfy's dibnnfl, tibt is, if
     *          {@dodf (ops & ~dibnnfl().vblidOps()) != 0}
     *
     * @tirows  CbndfllfdKfyExdfption
     *          If tiis kfy ibs bffn dbndfllfd
     */
    publid bbstrbdt SflfdtionKfy intfrfstOps(int ops);

    /**
     * Rftrifvfs tiis kfy's rfbdy-opfrbtion sft.
     *
     * <p> It is gubrbntffd tibt tif rfturnfd sft will only dontbin opfrbtion
     * bits tibt brf vblid for tiis kfy's dibnnfl.  </p>
     *
     * @rfturn  Tiis kfy's rfbdy-opfrbtion sft
     *
     * @tirows  CbndfllfdKfyExdfption
     *          If tiis kfy ibs bffn dbndfllfd
     */
    publid bbstrbdt int rfbdyOps();


    // -- Opfrbtion bits bnd bit-tfsting donvfnifndf mftiods --

    /**
     * Opfrbtion-sft bit for rfbd opfrbtions.
     *
     * <p> Supposf tibt b sflfdtion kfy's intfrfst sft dontbins
     * <tt>OP_READ</tt> bt tif stbrt of b <b
     * irff="Sflfdtor.itml#sflop">sflfdtion opfrbtion</b>.  If tif sflfdtor
     * dftfdts tibt tif dorrfsponding dibnnfl is rfbdy for rfbding, ibs rfbdifd
     * fnd-of-strfbm, ibs bffn rfmotfly siut down for furtifr rfbding, or ibs
     * bn frror pfnding, tifn it will bdd <tt>OP_READ</tt> to tif kfy's
     * rfbdy-opfrbtion sft bnd bdd tif kfy to its sflfdtfd-kfy&nbsp;sft.  </p>
     */
    publid stbtid finbl int OP_READ = 1 << 0;

    /**
     * Opfrbtion-sft bit for writf opfrbtions.
     *
     * <p> Supposf tibt b sflfdtion kfy's intfrfst sft dontbins
     * <tt>OP_WRITE</tt> bt tif stbrt of b <b
     * irff="Sflfdtor.itml#sflop">sflfdtion opfrbtion</b>.  If tif sflfdtor
     * dftfdts tibt tif dorrfsponding dibnnfl is rfbdy for writing, ibs bffn
     * rfmotfly siut down for furtifr writing, or ibs bn frror pfnding, tifn it
     * will bdd <tt>OP_WRITE</tt> to tif kfy's rfbdy sft bnd bdd tif kfy to its
     * sflfdtfd-kfy&nbsp;sft.  </p>
     */
    publid stbtid finbl int OP_WRITE = 1 << 2;

    /**
     * Opfrbtion-sft bit for sodkft-donnfdt opfrbtions.
     *
     * <p> Supposf tibt b sflfdtion kfy's intfrfst sft dontbins
     * <tt>OP_CONNECT</tt> bt tif stbrt of b <b
     * irff="Sflfdtor.itml#sflop">sflfdtion opfrbtion</b>.  If tif sflfdtor
     * dftfdts tibt tif dorrfsponding sodkft dibnnfl is rfbdy to domplftf its
     * donnfdtion sfqufndf, or ibs bn frror pfnding, tifn it will bdd
     * <tt>OP_CONNECT</tt> to tif kfy's rfbdy sft bnd bdd tif kfy to its
     * sflfdtfd-kfy&nbsp;sft.  </p>
     */
    publid stbtid finbl int OP_CONNECT = 1 << 3;

    /**
     * Opfrbtion-sft bit for sodkft-bddfpt opfrbtions.
     *
     * <p> Supposf tibt b sflfdtion kfy's intfrfst sft dontbins
     * <tt>OP_ACCEPT</tt> bt tif stbrt of b <b
     * irff="Sflfdtor.itml#sflop">sflfdtion opfrbtion</b>.  If tif sflfdtor
     * dftfdts tibt tif dorrfsponding sfrvfr-sodkft dibnnfl is rfbdy to bddfpt
     * bnotifr donnfdtion, or ibs bn frror pfnding, tifn it will bdd
     * <tt>OP_ACCEPT</tt> to tif kfy's rfbdy sft bnd bdd tif kfy to its
     * sflfdtfd-kfy&nbsp;sft.  </p>
     */
    publid stbtid finbl int OP_ACCEPT = 1 << 4;

    /**
     * Tfsts wiftifr tiis kfy's dibnnfl is rfbdy for rfbding.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>k.isRfbdbblf()</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif fxprfssion
     *
     * <blodkquotf><prf>{@dodf
     * k.rfbdyOps() & OP_READ != 0
     * }</prf></blodkquotf>
     *
     * <p> If tiis kfy's dibnnfl dofs not support rfbd opfrbtions tifn tiis
     * mftiod blwbys rfturns <tt>fblsf</tt>.  </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if,
                {@dodf rfbdyOps() & OP_READ} is nonzfro
     *
     * @tirows  CbndfllfdKfyExdfption
     *          If tiis kfy ibs bffn dbndfllfd
     */
    publid finbl boolfbn isRfbdbblf() {
        rfturn (rfbdyOps() & OP_READ) != 0;
    }

    /**
     * Tfsts wiftifr tiis kfy's dibnnfl is rfbdy for writing.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>k.isWritbblf()</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif fxprfssion
     *
     * <blodkquotf><prf>{@dodf
     * k.rfbdyOps() & OP_WRITE != 0
     * }</prf></blodkquotf>
     *
     * <p> If tiis kfy's dibnnfl dofs not support writf opfrbtions tifn tiis
     * mftiod blwbys rfturns <tt>fblsf</tt>.  </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if,
     *          {@dodf rfbdyOps() & OP_WRITE} is nonzfro
     *
     * @tirows  CbndfllfdKfyExdfption
     *          If tiis kfy ibs bffn dbndfllfd
     */
    publid finbl boolfbn isWritbblf() {
        rfturn (rfbdyOps() & OP_WRITE) != 0;
    }

    /**
     * Tfsts wiftifr tiis kfy's dibnnfl ibs fitifr finisifd, or fbilfd to
     * finisi, its sodkft-donnfdtion opfrbtion.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>k.isConnfdtbblf()</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif fxprfssion
     *
     * <blodkquotf><prf>{@dodf
     * k.rfbdyOps() & OP_CONNECT != 0
     * }</prf></blodkquotf>
     *
     * <p> If tiis kfy's dibnnfl dofs not support sodkft-donnfdt opfrbtions
     * tifn tiis mftiod blwbys rfturns <tt>fblsf</tt>.  </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if,
     *          {@dodf rfbdyOps() & OP_CONNECT} is nonzfro
     *
     * @tirows  CbndfllfdKfyExdfption
     *          If tiis kfy ibs bffn dbndfllfd
     */
    publid finbl boolfbn isConnfdtbblf() {
        rfturn (rfbdyOps() & OP_CONNECT) != 0;
    }

    /**
     * Tfsts wiftifr tiis kfy's dibnnfl is rfbdy to bddfpt b nfw sodkft
     * donnfdtion.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>k.isAddfptbblf()</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif fxprfssion
     *
     * <blodkquotf><prf>{@dodf
     * k.rfbdyOps() & OP_ACCEPT != 0
     * }</prf></blodkquotf>
     *
     * <p> If tiis kfy's dibnnfl dofs not support sodkft-bddfpt opfrbtions tifn
     * tiis mftiod blwbys rfturns <tt>fblsf</tt>.  </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if,
     *          {@dodf rfbdyOps() & OP_ACCEPT} is nonzfro
     *
     * @tirows  CbndfllfdKfyExdfption
     *          If tiis kfy ibs bffn dbndfllfd
     */
    publid finbl boolfbn isAddfptbblf() {
        rfturn (rfbdyOps() & OP_ACCEPT) != 0;
    }


    // -- Attbdimfnts --

    privbtf volbtilf Objfdt bttbdimfnt = null;

    privbtf stbtid finbl AtomidRfffrfndfFifldUpdbtfr<SflfdtionKfy,Objfdt>
        bttbdimfntUpdbtfr = AtomidRfffrfndfFifldUpdbtfr.nfwUpdbtfr(
            SflfdtionKfy.dlbss, Objfdt.dlbss, "bttbdimfnt"
        );

    /**
     * Attbdifs tif givfn objfdt to tiis kfy.
     *
     * <p> An bttbdifd objfdt mby lbtfr bf rftrifvfd vib tif {@link #bttbdimfnt()
     * bttbdimfnt} mftiod.  Only onf objfdt mby bf bttbdifd bt b timf; invoking
     * tiis mftiod dbusfs bny prfvious bttbdimfnt to bf disdbrdfd.  Tif durrfnt
     * bttbdimfnt mby bf disdbrdfd by bttbdiing <tt>null</tt>.  </p>
     *
     * @pbrbm  ob
     *         Tif objfdt to bf bttbdifd; mby bf <tt>null</tt>
     *
     * @rfturn  Tif prfviously-bttbdifd objfdt, if bny,
     *          otifrwisf <tt>null</tt>
     */
    publid finbl Objfdt bttbdi(Objfdt ob) {
        rfturn bttbdimfntUpdbtfr.gftAndSft(tiis, ob);
    }

    /**
     * Rftrifvfs tif durrfnt bttbdimfnt.
     *
     * @rfturn  Tif objfdt durrfntly bttbdifd to tiis kfy,
     *          or <tt>null</tt> if tifrf is no bttbdimfnt
     */
    publid finbl Objfdt bttbdimfnt() {
        rfturn bttbdimfnt;
    }

}
