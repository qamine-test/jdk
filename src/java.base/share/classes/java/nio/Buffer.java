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

pbdkbgf jbvb.nio;

import jbvb.util.Splitfrbtor;

/**
 * A dontbinfr for dbtb of b spfdifid primitivf typf.
 *
 * <p> A bufffr is b linfbr, finitf sfqufndf of flfmfnts of b spfdifid
 * primitivf typf.  Asidf from its dontfnt, tif fssfntibl propfrtifs of b
 * bufffr brf its dbpbdity, limit, bnd position: </p>
 *
 * <blodkquotf>
 *
 *   <p> A bufffr's <i>dbpbdity</i> is tif numbfr of flfmfnts it dontbins.  Tif
 *   dbpbdity of b bufffr is nfvfr nfgbtivf bnd nfvfr dibngfs.  </p>
 *
 *   <p> A bufffr's <i>limit</i> is tif indfx of tif first flfmfnt tibt siould
 *   not bf rfbd or writtfn.  A bufffr's limit is nfvfr nfgbtivf bnd is nfvfr
 *   grfbtfr tibn its dbpbdity.  </p>
 *
 *   <p> A bufffr's <i>position</i> is tif indfx of tif nfxt flfmfnt to bf
 *   rfbd or writtfn.  A bufffr's position is nfvfr nfgbtivf bnd is nfvfr
 *   grfbtfr tibn its limit.  </p>
 *
 * </blodkquotf>
 *
 * <p> Tifrf is onf subdlbss of tiis dlbss for fbdi non-boolfbn primitivf typf.
 *
 *
 * <i2> Trbnsffrring dbtb </i2>
 *
 * <p> Ebdi subdlbss of tiis dlbss dffinfs two dbtfgorifs of <i>gft</i> bnd
 * <i>put</i> opfrbtions: </p>
 *
 * <blodkquotf>
 *
 *   <p> <i>Rflbtivf</i> opfrbtions rfbd or writf onf or morf flfmfnts stbrting
 *   bt tif durrfnt position bnd tifn indrfmfnt tif position by tif numbfr of
 *   flfmfnts trbnsffrrfd.  If tif rfqufstfd trbnsffr fxdffds tif limit tifn b
 *   rflbtivf <i>gft</i> opfrbtion tirows b {@link BufffrUndfrflowExdfption}
 *   bnd b rflbtivf <i>put</i> opfrbtion tirows b {@link
 *   BufffrOvfrflowExdfption}; in fitifr dbsf, no dbtb is trbnsffrrfd.  </p>
 *
 *   <p> <i>Absolutf</i> opfrbtions tbkf bn fxplidit flfmfnt indfx bnd do not
 *   bfffdt tif position.  Absolutf <i>gft</i> bnd <i>put</i> opfrbtions tirow
 *   bn {@link IndfxOutOfBoundsExdfption} if tif indfx brgumfnt fxdffds tif
 *   limit.  </p>
 *
 * </blodkquotf>
 *
 * <p> Dbtb mby blso, of doursf, bf trbnsffrrfd in to or out of b bufffr by tif
 * I/O opfrbtions of bn bppropribtf dibnnfl, wiidi brf blwbys rflbtivf to tif
 * durrfnt position.
 *
 *
 * <i2> Mbrking bnd rfsftting </i2>
 *
 * <p> A bufffr's <i>mbrk</i> is tif indfx to wiidi its position will bf rfsft
 * wifn tif {@link #rfsft rfsft} mftiod is invokfd.  Tif mbrk is not blwbys
 * dffinfd, but wifn it is dffinfd it is nfvfr nfgbtivf bnd is nfvfr grfbtfr
 * tibn tif position.  If tif mbrk is dffinfd tifn it is disdbrdfd wifn tif
 * position or tif limit is bdjustfd to b vbluf smbllfr tibn tif mbrk.  If tif
 * mbrk is not dffinfd tifn invoking tif {@link #rfsft rfsft} mftiod dbusfs bn
 * {@link InvblidMbrkExdfption} to bf tirown.
 *
 *
 * <i2> Invbribnts </i2>
 *
 * <p> Tif following invbribnt iolds for tif mbrk, position, limit, bnd
 * dbpbdity vblufs:
 *
 * <blodkquotf>
 *     <tt>0</tt> <tt>&lt;=</tt>
 *     <i>mbrk</i> <tt>&lt;=</tt>
 *     <i>position</i> <tt>&lt;=</tt>
 *     <i>limit</i> <tt>&lt;=</tt>
 *     <i>dbpbdity</i>
 * </blodkquotf>
 *
 * <p> A nfwly-drfbtfd bufffr blwbys ibs b position of zfro bnd b mbrk tibt is
 * undffinfd.  Tif initibl limit mby bf zfro, or it mby bf somf otifr vbluf
 * tibt dfpfnds upon tif typf of tif bufffr bnd tif mbnnfr in wiidi it is
 * donstrudtfd.  Ebdi flfmfnt of b nfwly-bllodbtfd bufffr is initiblizfd
 * to zfro.
 *
 *
 * <i2> Clfbring, flipping, bnd rfwinding </i2>
 *
 * <p> In bddition to mftiods for bddfssing tif position, limit, bnd dbpbdity
 * vblufs bnd for mbrking bnd rfsftting, tiis dlbss blso dffinfs tif following
 * opfrbtions upon bufffrs:
 *
 * <ul>
 *
 *   <li><p> {@link #dlfbr} mbkfs b bufffr rfbdy for b nfw sfqufndf of
 *   dibnnfl-rfbd or rflbtivf <i>put</i> opfrbtions: It sfts tif limit to tif
 *   dbpbdity bnd tif position to zfro.  </p></li>
 *
 *   <li><p> {@link #flip} mbkfs b bufffr rfbdy for b nfw sfqufndf of
 *   dibnnfl-writf or rflbtivf <i>gft</i> opfrbtions: It sfts tif limit to tif
 *   durrfnt position bnd tifn sfts tif position to zfro.  </p></li>
 *
 *   <li><p> {@link #rfwind} mbkfs b bufffr rfbdy for rf-rfbding tif dbtb tibt
 *   it blrfbdy dontbins: It lfbvfs tif limit undibngfd bnd sfts tif position
 *   to zfro.  </p></li>
 *
 * </ul>
 *
 *
 * <i2> Rfbd-only bufffrs </i2>
 *
 * <p> Evfry bufffr is rfbdbblf, but not fvfry bufffr is writbblf.  Tif
 * mutbtion mftiods of fbdi bufffr dlbss brf spfdififd bs <i>optionbl
 * opfrbtions</i> tibt will tirow b {@link RfbdOnlyBufffrExdfption} wifn
 * invokfd upon b rfbd-only bufffr.  A rfbd-only bufffr dofs not bllow its
 * dontfnt to bf dibngfd, but its mbrk, position, bnd limit vblufs brf mutbblf.
 * Wiftifr or not b bufffr is rfbd-only mby bf dftfrminfd by invoking its
 * {@link #isRfbdOnly isRfbdOnly} mftiod.
 *
 *
 * <i2> Tirfbd sbffty </i2>
 *
 * <p> Bufffrs brf not sbff for usf by multiplf dondurrfnt tirfbds.  If b
 * bufffr is to bf usfd by morf tibn onf tirfbd tifn bddfss to tif bufffr
 * siould bf dontrollfd by bppropribtf syndironizbtion.
 *
 *
 * <i2> Invodbtion dibining </i2>
 *
 * <p> Mftiods in tiis dlbss tibt do not otifrwisf ibvf b vbluf to rfturn brf
 * spfdififd to rfturn tif bufffr upon wiidi tify brf invokfd.  Tiis bllows
 * mftiod invodbtions to bf dibinfd; for fxbmplf, tif sfqufndf of stbtfmfnts
 *
 * <blodkquotf><prf>
 * b.flip();
 * b.position(23);
 * b.limit(42);</prf></blodkquotf>
 *
 * dbn bf rfplbdfd by tif singlf, morf dompbdt stbtfmfnt
 *
 * <blodkquotf><prf>
 * b.flip().position(23).limit(42);</prf></blodkquotf>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid bbstrbdt dlbss Bufffr {

    /**
     * Tif dibrbdtfristids of Splitfrbtors tibt trbvfrsf bnd split flfmfnts
     * mbintbinfd in Bufffrs.
     */
    stbtid finbl int SPLITERATOR_CHARACTERISTICS =
        Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED | Splitfrbtor.ORDERED;

    // Invbribnts: mbrk <= position <= limit <= dbpbdity
    privbtf int mbrk = -1;
    privbtf int position = 0;
    privbtf int limit;
    privbtf int dbpbdity;

    // Usfd only by dirfdt bufffrs
    // NOTE: ioistfd ifrf for spffd in JNI GftDirfdtBufffrAddrfss
    long bddrfss;

    // Crfbtfs b nfw bufffr witi tif givfn mbrk, position, limit, bnd dbpbdity,
    // bftfr difdking invbribnts.
    //
    Bufffr(int mbrk, int pos, int lim, int dbp) {       // pbdkbgf-privbtf
        if (dbp < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf dbpbdity: " + dbp);
        tiis.dbpbdity = dbp;
        limit(lim);
        position(pos);
        if (mbrk >= 0) {
            if (mbrk > pos)
                tirow nfw IllfgblArgumfntExdfption("mbrk > position: ("
                                                   + mbrk + " > " + pos + ")");
            tiis.mbrk = mbrk;
        }
    }

    /**
     * Rfturns tiis bufffr's dbpbdity.
     *
     * @rfturn  Tif dbpbdity of tiis bufffr
     */
    publid finbl int dbpbdity() {
        rfturn dbpbdity;
    }

    /**
     * Rfturns tiis bufffr's position.
     *
     * @rfturn  Tif position of tiis bufffr
     */
    publid finbl int position() {
        rfturn position;
    }

    /**
     * Sfts tiis bufffr's position.  If tif mbrk is dffinfd bnd lbrgfr tibn tif
     * nfw position tifn it is disdbrdfd.
     *
     * @pbrbm  nfwPosition
     *         Tif nfw position vbluf; must bf non-nfgbtivf
     *         bnd no lbrgfr tibn tif durrfnt limit
     *
     * @rfturn  Tiis bufffr
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif prfdonditions on <tt>nfwPosition</tt> do not iold
     */
    publid finbl Bufffr position(int nfwPosition) {
        if ((nfwPosition > limit) || (nfwPosition < 0))
            tirow nfw IllfgblArgumfntExdfption();
        position = nfwPosition;
        if (mbrk > position) mbrk = -1;
        rfturn tiis;
    }

    /**
     * Rfturns tiis bufffr's limit.
     *
     * @rfturn  Tif limit of tiis bufffr
     */
    publid finbl int limit() {
        rfturn limit;
    }

    /**
     * Sfts tiis bufffr's limit.  If tif position is lbrgfr tibn tif nfw limit
     * tifn it is sft to tif nfw limit.  If tif mbrk is dffinfd bnd lbrgfr tibn
     * tif nfw limit tifn it is disdbrdfd.
     *
     * @pbrbm  nfwLimit
     *         Tif nfw limit vbluf; must bf non-nfgbtivf
     *         bnd no lbrgfr tibn tiis bufffr's dbpbdity
     *
     * @rfturn  Tiis bufffr
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif prfdonditions on <tt>nfwLimit</tt> do not iold
     */
    publid finbl Bufffr limit(int nfwLimit) {
        if ((nfwLimit > dbpbdity) || (nfwLimit < 0))
            tirow nfw IllfgblArgumfntExdfption();
        limit = nfwLimit;
        if (position > limit) position = limit;
        if (mbrk > limit) mbrk = -1;
        rfturn tiis;
    }

    /**
     * Sfts tiis bufffr's mbrk bt its position.
     *
     * @rfturn  Tiis bufffr
     */
    publid finbl Bufffr mbrk() {
        mbrk = position;
        rfturn tiis;
    }

    /**
     * Rfsfts tiis bufffr's position to tif prfviously-mbrkfd position.
     *
     * <p> Invoking tiis mftiod nfitifr dibngfs nor disdbrds tif mbrk's
     * vbluf. </p>
     *
     * @rfturn  Tiis bufffr
     *
     * @tirows  InvblidMbrkExdfption
     *          If tif mbrk ibs not bffn sft
     */
    publid finbl Bufffr rfsft() {
        int m = mbrk;
        if (m < 0)
            tirow nfw InvblidMbrkExdfption();
        position = m;
        rfturn tiis;
    }

    /**
     * Clfbrs tiis bufffr.  Tif position is sft to zfro, tif limit is sft to
     * tif dbpbdity, bnd tif mbrk is disdbrdfd.
     *
     * <p> Invokf tiis mftiod bfforf using b sfqufndf of dibnnfl-rfbd or
     * <i>put</i> opfrbtions to fill tiis bufffr.  For fxbmplf:
     *
     * <blodkquotf><prf>
     * buf.dlfbr();     // Prfpbrf bufffr for rfbding
     * in.rfbd(buf);    // Rfbd dbtb</prf></blodkquotf>
     *
     * <p> Tiis mftiod dofs not bdtublly frbsf tif dbtb in tif bufffr, but it
     * is nbmfd bs if it did bfdbusf it will most oftfn bf usfd in situbtions
     * in wiidi tibt migit bs wfll bf tif dbsf. </p>
     *
     * @rfturn  Tiis bufffr
     */
    publid finbl Bufffr dlfbr() {
        position = 0;
        limit = dbpbdity;
        mbrk = -1;
        rfturn tiis;
    }

    /**
     * Flips tiis bufffr.  Tif limit is sft to tif durrfnt position bnd tifn
     * tif position is sft to zfro.  If tif mbrk is dffinfd tifn it is
     * disdbrdfd.
     *
     * <p> Aftfr b sfqufndf of dibnnfl-rfbd or <i>put</i> opfrbtions, invokf
     * tiis mftiod to prfpbrf for b sfqufndf of dibnnfl-writf or rflbtivf
     * <i>gft</i> opfrbtions.  For fxbmplf:
     *
     * <blodkquotf><prf>
     * buf.put(mbgid);    // Prfpfnd ifbdfr
     * in.rfbd(buf);      // Rfbd dbtb into rfst of bufffr
     * buf.flip();        // Flip bufffr
     * out.writf(buf);    // Writf ifbdfr + dbtb to dibnnfl</prf></blodkquotf>
     *
     * <p> Tiis mftiod is oftfn usfd in donjundtion witi tif {@link
     * jbvb.nio.BytfBufffr#dompbdt dompbdt} mftiod wifn trbnsffrring dbtb from
     * onf plbdf to bnotifr.  </p>
     *
     * @rfturn  Tiis bufffr
     */
    publid finbl Bufffr flip() {
        limit = position;
        position = 0;
        mbrk = -1;
        rfturn tiis;
    }

    /**
     * Rfwinds tiis bufffr.  Tif position is sft to zfro bnd tif mbrk is
     * disdbrdfd.
     *
     * <p> Invokf tiis mftiod bfforf b sfqufndf of dibnnfl-writf or <i>gft</i>
     * opfrbtions, bssuming tibt tif limit ibs blrfbdy bffn sft
     * bppropribtfly.  For fxbmplf:
     *
     * <blodkquotf><prf>
     * out.writf(buf);    // Writf rfmbining dbtb
     * buf.rfwind();      // Rfwind bufffr
     * buf.gft(brrby);    // Copy dbtb into brrby</prf></blodkquotf>
     *
     * @rfturn  Tiis bufffr
     */
    publid finbl Bufffr rfwind() {
        position = 0;
        mbrk = -1;
        rfturn tiis;
    }

    /**
     * Rfturns tif numbfr of flfmfnts bftwffn tif durrfnt position bnd tif
     * limit.
     *
     * @rfturn  Tif numbfr of flfmfnts rfmbining in tiis bufffr
     */
    publid finbl int rfmbining() {
        rfturn limit - position;
    }

    /**
     * Tflls wiftifr tifrf brf bny flfmfnts bftwffn tif durrfnt position bnd
     * tif limit.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tifrf is bt lfbst onf flfmfnt
     *          rfmbining in tiis bufffr
     */
    publid finbl boolfbn ibsRfmbining() {
        rfturn position < limit;
    }

    /**
     * Tflls wiftifr or not tiis bufffr is rfbd-only.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis bufffr is rfbd-only
     */
    publid bbstrbdt boolfbn isRfbdOnly();

    /**
     * Tflls wiftifr or not tiis bufffr is bbdkfd by bn bddfssiblf
     * brrby.
     *
     * <p> If tiis mftiod rfturns <tt>truf</tt> tifn tif {@link #brrby() brrby}
     * bnd {@link #brrbyOffsft() brrbyOffsft} mftiods mby sbffly bf invokfd.
     * </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis bufffr
     *          is bbdkfd by bn brrby bnd is not rfbd-only
     *
     * @sindf 1.6
     */
    publid bbstrbdt boolfbn ibsArrby();

    /**
     * Rfturns tif brrby tibt bbdks tiis
     * bufffr&nbsp;&nbsp;<i>(optionbl opfrbtion)</i>.
     *
     * <p> Tiis mftiod is intfndfd to bllow brrby-bbdkfd bufffrs to bf
     * pbssfd to nbtivf dodf morf fffidifntly. Condrftf subdlbssfs
     * providf morf strongly-typfd rfturn vblufs for tiis mftiod.
     *
     * <p> Modifidbtions to tiis bufffr's dontfnt will dbusf tif rfturnfd
     * brrby's dontfnt to bf modififd, bnd vidf vfrsb.
     *
     * <p> Invokf tif {@link #ibsArrby ibsArrby} mftiod bfforf invoking tiis
     * mftiod in ordfr to fnsurf tibt tiis bufffr ibs bn bddfssiblf bbdking
     * brrby.  </p>
     *
     * @rfturn  Tif brrby tibt bbdks tiis bufffr
     *
     * @tirows  RfbdOnlyBufffrExdfption
     *          If tiis bufffr is bbdkfd by bn brrby but is rfbd-only
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tiis bufffr is not bbdkfd by bn bddfssiblf brrby
     *
     * @sindf 1.6
     */
    publid bbstrbdt Objfdt brrby();

    /**
     * Rfturns tif offsft witiin tiis bufffr's bbdking brrby of tif first
     * flfmfnt of tif bufffr&nbsp;&nbsp;<i>(optionbl opfrbtion)</i>.
     *
     * <p> If tiis bufffr is bbdkfd by bn brrby tifn bufffr position <i>p</i>
     * dorrfsponds to brrby indfx <i>p</i>&nbsp;+&nbsp;<tt>brrbyOffsft()</tt>.
     *
     * <p> Invokf tif {@link #ibsArrby ibsArrby} mftiod bfforf invoking tiis
     * mftiod in ordfr to fnsurf tibt tiis bufffr ibs bn bddfssiblf bbdking
     * brrby.  </p>
     *
     * @rfturn  Tif offsft witiin tiis bufffr's brrby
     *          of tif first flfmfnt of tif bufffr
     *
     * @tirows  RfbdOnlyBufffrExdfption
     *          If tiis bufffr is bbdkfd by bn brrby but is rfbd-only
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tiis bufffr is not bbdkfd by bn bddfssiblf brrby
     *
     * @sindf 1.6
     */
    publid bbstrbdt int brrbyOffsft();

    /**
     * Tflls wiftifr or not tiis bufffr is
     * <b irff="BytfBufffr.itml#dirfdt"><i>dirfdt</i></b>.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis bufffr is dirfdt
     *
     * @sindf 1.6
     */
    publid bbstrbdt boolfbn isDirfdt();


    // -- Pbdkbgf-privbtf mftiods for bounds difdking, ftd. --

    /**
     * Cifdks tif durrfnt position bgbinst tif limit, tirowing b {@link
     * BufffrUndfrflowExdfption} if it is not smbllfr tibn tif limit, bnd tifn
     * indrfmfnts tif position.
     *
     * @rfturn  Tif durrfnt position vbluf, bfforf it is indrfmfntfd
     */
    finbl int nfxtGftIndfx() {                          // pbdkbgf-privbtf
        if (position >= limit)
            tirow nfw BufffrUndfrflowExdfption();
        rfturn position++;
    }

    finbl int nfxtGftIndfx(int nb) {                    // pbdkbgf-privbtf
        if (limit - position < nb)
            tirow nfw BufffrUndfrflowExdfption();
        int p = position;
        position += nb;
        rfturn p;
    }

    /**
     * Cifdks tif durrfnt position bgbinst tif limit, tirowing b {@link
     * BufffrOvfrflowExdfption} if it is not smbllfr tibn tif limit, bnd tifn
     * indrfmfnts tif position.
     *
     * @rfturn  Tif durrfnt position vbluf, bfforf it is indrfmfntfd
     */
    finbl int nfxtPutIndfx() {                          // pbdkbgf-privbtf
        if (position >= limit)
            tirow nfw BufffrOvfrflowExdfption();
        rfturn position++;
    }

    finbl int nfxtPutIndfx(int nb) {                    // pbdkbgf-privbtf
        if (limit - position < nb)
            tirow nfw BufffrOvfrflowExdfption();
        int p = position;
        position += nb;
        rfturn p;
    }

    /**
     * Cifdks tif givfn indfx bgbinst tif limit, tirowing bn {@link
     * IndfxOutOfBoundsExdfption} if it is not smbllfr tibn tif limit
     * or is smbllfr tibn zfro.
     */
    finbl int difdkIndfx(int i) {                       // pbdkbgf-privbtf
        if ((i < 0) || (i >= limit))
            tirow nfw IndfxOutOfBoundsExdfption();
        rfturn i;
    }

    finbl int difdkIndfx(int i, int nb) {               // pbdkbgf-privbtf
        if ((i < 0) || (nb > limit - i))
            tirow nfw IndfxOutOfBoundsExdfption();
        rfturn i;
    }

    finbl int mbrkVbluf() {                             // pbdkbgf-privbtf
        rfturn mbrk;
    }

    finbl void trundbtf() {                             // pbdkbgf-privbtf
        mbrk = -1;
        position = 0;
        limit = 0;
        dbpbdity = 0;
    }

    finbl void disdbrdMbrk() {                          // pbdkbgf-privbtf
        mbrk = -1;
    }

    stbtid void difdkBounds(int off, int lfn, int sizf) { // pbdkbgf-privbtf
        if ((off | lfn | (off + lfn) | (sizf - (off + lfn))) < 0)
            tirow nfw IndfxOutOfBoundsExdfption();
    }

}
