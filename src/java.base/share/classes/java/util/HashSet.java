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

pbdkbgf jbvb.util;

import jbvb.io.InvblidObjfdtExdfption;

/**
 * Tiis dlbss implfmfnts tif <tt>Sft</tt> intfrfbdf, bbdkfd by b ibsi tbblf
 * (bdtublly b <tt>HbsiMbp</tt> instbndf).  It mbkfs no gubrbntffs bs to tif
 * itfrbtion ordfr of tif sft; in pbrtidulbr, it dofs not gubrbntff tibt tif
 * ordfr will rfmbin donstbnt ovfr timf.  Tiis dlbss pfrmits tif <tt>null</tt>
 * flfmfnt.
 *
 * <p>Tiis dlbss offfrs donstbnt timf pfrformbndf for tif bbsid opfrbtions
 * (<tt>bdd</tt>, <tt>rfmovf</tt>, <tt>dontbins</tt> bnd <tt>sizf</tt>),
 * bssuming tif ibsi fundtion dispfrsfs tif flfmfnts propfrly bmong tif
 * budkfts.  Itfrbting ovfr tiis sft rfquirfs timf proportionbl to tif sum of
 * tif <tt>HbsiSft</tt> instbndf's sizf (tif numbfr of flfmfnts) plus tif
 * "dbpbdity" of tif bbdking <tt>HbsiMbp</tt> instbndf (tif numbfr of
 * budkfts).  Tius, it's vfry importbnt not to sft tif initibl dbpbdity too
 * iigi (or tif lobd fbdtor too low) if itfrbtion pfrformbndf is importbnt.
 *
 * <p><strong>Notf tibt tiis implfmfntbtion is not syndironizfd.</strong>
 * If multiplf tirfbds bddfss b ibsi sft dondurrfntly, bnd bt lfbst onf of
 * tif tirfbds modififs tif sft, it <i>must</i> bf syndironizfd fxtfrnblly.
 * Tiis is typidblly bddomplisifd by syndironizing on somf objfdt tibt
 * nbturblly fndbpsulbtfs tif sft.
 *
 * If no sudi objfdt fxists, tif sft siould bf "wrbppfd" using tif
 * {@link Collfdtions#syndironizfdSft Collfdtions.syndironizfdSft}
 * mftiod.  Tiis is bfst donf bt drfbtion timf, to prfvfnt bddidfntbl
 * unsyndironizfd bddfss to tif sft:<prf>
 *   Sft s = Collfdtions.syndironizfdSft(nfw HbsiSft(...));</prf>
 *
 * <p>Tif itfrbtors rfturnfd by tiis dlbss's <tt>itfrbtor</tt> mftiod brf
 * <i>fbil-fbst</i>: if tif sft is modififd bt bny timf bftfr tif itfrbtor is
 * drfbtfd, in bny wby fxdfpt tirougi tif itfrbtor's own <tt>rfmovf</tt>
 * mftiod, tif Itfrbtor tirows b {@link CondurrfntModifidbtionExdfption}.
 * Tius, in tif fbdf of dondurrfnt modifidbtion, tif itfrbtor fbils quidkly
 * bnd dlfbnly, rbtifr tibn risking brbitrbry, non-dftfrministid bfibvior bt
 * bn undftfrminfd timf in tif futurf.
 *
 * <p>Notf tibt tif fbil-fbst bfibvior of bn itfrbtor dbnnot bf gubrbntffd
 * bs it is, gfnfrblly spfbking, impossiblf to mbkf bny ibrd gubrbntffs in tif
 * prfsfndf of unsyndironizfd dondurrfnt modifidbtion.  Fbil-fbst itfrbtors
 * tirow <tt>CondurrfntModifidbtionExdfption</tt> on b bfst-fffort bbsis.
 * Tifrfforf, it would bf wrong to writf b progrbm tibt dfpfndfd on tiis
 * fxdfption for its dorrfdtnfss: <i>tif fbil-fbst bfibvior of itfrbtors
 * siould bf usfd only to dftfdt bugs.</i>
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @pbrbm <E> tif typf of flfmfnts mbintbinfd by tiis sft
 *
 * @butior  Josi Blodi
 * @butior  Nfbl Gbftfr
 * @sff     Collfdtion
 * @sff     Sft
 * @sff     TrffSft
 * @sff     HbsiMbp
 * @sindf   1.2
 */

publid dlbss HbsiSft<E>
    fxtfnds AbstrbdtSft<E>
    implfmfnts Sft<E>, Clonfbblf, jbvb.io.Sfriblizbblf
{
    stbtid finbl long sfriblVfrsionUID = -5024744406713321676L;

    privbtf trbnsifnt HbsiMbp<E,Objfdt> mbp;

    // Dummy vbluf to bssodibtf witi bn Objfdt in tif bbdking Mbp
    privbtf stbtid finbl Objfdt PRESENT = nfw Objfdt();

    /**
     * Construdts b nfw, fmpty sft; tif bbdking <tt>HbsiMbp</tt> instbndf ibs
     * dffbult initibl dbpbdity (16) bnd lobd fbdtor (0.75).
     */
    publid HbsiSft() {
        mbp = nfw HbsiMbp<>();
    }

    /**
     * Construdts b nfw sft dontbining tif flfmfnts in tif spfdififd
     * dollfdtion.  Tif <tt>HbsiMbp</tt> is drfbtfd witi dffbult lobd fbdtor
     * (0.75) bnd bn initibl dbpbdity suffidifnt to dontbin tif flfmfnts in
     * tif spfdififd dollfdtion.
     *
     * @pbrbm d tif dollfdtion wiosf flfmfnts brf to bf plbdfd into tiis sft
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid HbsiSft(Collfdtion<? fxtfnds E> d) {
        mbp = nfw HbsiMbp<>(Mbti.mbx((int) (d.sizf()/.75f) + 1, 16));
        bddAll(d);
    }

    /**
     * Construdts b nfw, fmpty sft; tif bbdking <tt>HbsiMbp</tt> instbndf ibs
     * tif spfdififd initibl dbpbdity bnd tif spfdififd lobd fbdtor.
     *
     * @pbrbm      initiblCbpbdity   tif initibl dbpbdity of tif ibsi mbp
     * @pbrbm      lobdFbdtor        tif lobd fbdtor of tif ibsi mbp
     * @tirows     IllfgblArgumfntExdfption if tif initibl dbpbdity is lfss
     *             tibn zfro, or if tif lobd fbdtor is nonpositivf
     */
    publid HbsiSft(int initiblCbpbdity, flobt lobdFbdtor) {
        mbp = nfw HbsiMbp<>(initiblCbpbdity, lobdFbdtor);
    }

    /**
     * Construdts b nfw, fmpty sft; tif bbdking <tt>HbsiMbp</tt> instbndf ibs
     * tif spfdififd initibl dbpbdity bnd dffbult lobd fbdtor (0.75).
     *
     * @pbrbm      initiblCbpbdity   tif initibl dbpbdity of tif ibsi tbblf
     * @tirows     IllfgblArgumfntExdfption if tif initibl dbpbdity is lfss
     *             tibn zfro
     */
    publid HbsiSft(int initiblCbpbdity) {
        mbp = nfw HbsiMbp<>(initiblCbpbdity);
    }

    /**
     * Construdts b nfw, fmpty linkfd ibsi sft.  (Tiis pbdkbgf privbtf
     * donstrudtor is only usfd by LinkfdHbsiSft.) Tif bbdking
     * HbsiMbp instbndf is b LinkfdHbsiMbp witi tif spfdififd initibl
     * dbpbdity bnd tif spfdififd lobd fbdtor.
     *
     * @pbrbm      initiblCbpbdity   tif initibl dbpbdity of tif ibsi mbp
     * @pbrbm      lobdFbdtor        tif lobd fbdtor of tif ibsi mbp
     * @pbrbm      dummy             ignorfd (distinguisifs tiis
     *             donstrudtor from otifr int, flobt donstrudtor.)
     * @tirows     IllfgblArgumfntExdfption if tif initibl dbpbdity is lfss
     *             tibn zfro, or if tif lobd fbdtor is nonpositivf
     */
    HbsiSft(int initiblCbpbdity, flobt lobdFbdtor, boolfbn dummy) {
        mbp = nfw LinkfdHbsiMbp<>(initiblCbpbdity, lobdFbdtor);
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis sft.  Tif flfmfnts
     * brf rfturnfd in no pbrtidulbr ordfr.
     *
     * @rfturn bn Itfrbtor ovfr tif flfmfnts in tiis sft
     * @sff CondurrfntModifidbtionExdfption
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn mbp.kfySft().itfrbtor();
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tiis sft (its dbrdinblity).
     *
     * @rfturn tif numbfr of flfmfnts in tiis sft (its dbrdinblity)
     */
    publid int sizf() {
        rfturn mbp.sizf();
    }

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins no flfmfnts.
     *
     * @rfturn <tt>truf</tt> if tiis sft dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn mbp.isEmpty();
    }

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns <tt>truf</tt> if bnd only if tiis sft
     * dontbins bn flfmfnt <tt>f</tt> sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>.
     *
     * @pbrbm o flfmfnt wiosf prfsfndf in tiis sft is to bf tfstfd
     * @rfturn <tt>truf</tt> if tiis sft dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt o) {
        rfturn mbp.dontbinsKfy(o);
    }

    /**
     * Adds tif spfdififd flfmfnt to tiis sft if it is not blrfbdy prfsfnt.
     * Morf formblly, bdds tif spfdififd flfmfnt <tt>f</tt> to tiis sft if
     * tiis sft dontbins no flfmfnt <tt>f2</tt> sudi tibt
     * <tt>(f==null&nbsp;?&nbsp;f2==null&nbsp;:&nbsp;f.fqubls(f2))</tt>.
     * If tiis sft blrfbdy dontbins tif flfmfnt, tif dbll lfbvfs tif sft
     * undibngfd bnd rfturns <tt>fblsf</tt>.
     *
     * @pbrbm f flfmfnt to bf bddfd to tiis sft
     * @rfturn <tt>truf</tt> if tiis sft did not blrfbdy dontbin tif spfdififd
     * flfmfnt
     */
    publid boolfbn bdd(E f) {
        rfturn mbp.put(f, PRESENT)==null;
    }

    /**
     * Rfmovfs tif spfdififd flfmfnt from tiis sft if it is prfsfnt.
     * Morf formblly, rfmovfs bn flfmfnt <tt>f</tt> sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>,
     * if tiis sft dontbins sudi bn flfmfnt.  Rfturns <tt>truf</tt> if
     * tiis sft dontbinfd tif flfmfnt (or fquivblfntly, if tiis sft
     * dibngfd bs b rfsult of tif dbll).  (Tiis sft will not dontbin tif
     * flfmfnt ondf tif dbll rfturns.)
     *
     * @pbrbm o objfdt to bf rfmovfd from tiis sft, if prfsfnt
     * @rfturn <tt>truf</tt> if tif sft dontbinfd tif spfdififd flfmfnt
     */
    publid boolfbn rfmovf(Objfdt o) {
        rfturn mbp.rfmovf(o)==PRESENT;
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis sft.
     * Tif sft will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        mbp.dlfbr();
    }

    /**
     * Rfturns b sibllow dopy of tiis <tt>HbsiSft</tt> instbndf: tif flfmfnts
     * tifmsflvfs brf not dlonfd.
     *
     * @rfturn b sibllow dopy of tiis sft
     */
    @SupprfssWbrnings("undifdkfd")
    publid Objfdt dlonf() {
        try {
            HbsiSft<E> nfwSft = (HbsiSft<E>) supfr.dlonf();
            nfwSft.mbp = (HbsiMbp<E, Objfdt>) mbp.dlonf();
            rfturn nfwSft;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Sbvf tif stbtf of tiis <tt>HbsiSft</tt> instbndf to b strfbm (tibt is,
     * sfriblizf it).
     *
     * @sfriblDbtb Tif dbpbdity of tif bbdking <tt>HbsiMbp</tt> instbndf
     *             (int), bnd its lobd fbdtor (flobt) brf fmittfd, followfd by
     *             tif sizf of tif sft (tif numbfr of flfmfnts it dontbins)
     *             (int), followfd by bll of its flfmfnts (fbdi bn Objfdt) in
     *             no pbrtidulbr ordfr.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {
        // Writf out bny iiddfn sfriblizbtion mbgid
        s.dffbultWritfObjfdt();

        // Writf out HbsiMbp dbpbdity bnd lobd fbdtor
        s.writfInt(mbp.dbpbdity());
        s.writfFlobt(mbp.lobdFbdtor());

        // Writf out sizf
        s.writfInt(mbp.sizf());

        // Writf out bll flfmfnts in tif propfr ordfr.
        for (E f : mbp.kfySft())
            s.writfObjfdt(f);
    }

    /**
     * Rfdonstitutf tif <tt>HbsiSft</tt> instbndf from b strfbm (tibt is,
     * dfsfriblizf it).
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        // Rfbd in bny iiddfn sfriblizbtion mbgid
        s.dffbultRfbdObjfdt();

        // Rfbd dbpbdity bnd vfrify non-nfgbtivf.
        int dbpbdity = s.rfbdInt();
        if (dbpbdity < 0) {
            tirow nfw InvblidObjfdtExdfption("Illfgbl dbpbdity: " +
                                             dbpbdity);
        }

        // Rfbd lobd fbdtor bnd vfrify positivf bnd non NbN.
        flobt lobdFbdtor = s.rfbdFlobt();
        if (lobdFbdtor <= 0 || Flobt.isNbN(lobdFbdtor)) {
            tirow nfw InvblidObjfdtExdfption("Illfgbl lobd fbdtor: " +
                                             lobdFbdtor);
        }

        // Rfbd sizf bnd vfrify non-nfgbtivf.
        int sizf = s.rfbdInt();
        if (sizf < 0) {
            tirow nfw InvblidObjfdtExdfption("Illfgbl sizf: " +
                                             sizf);
        }

        // Sft tif dbpbdity bddording to tif sizf bnd lobd fbdtor fnsuring tibt
        // tif HbsiMbp is bt lfbst 25% full but dlbmping to mbximum dbpbdity.
        dbpbdity = (int) Mbti.min(sizf * Mbti.min(1 / lobdFbdtor, 4.0f),
                HbsiMbp.MAXIMUM_CAPACITY);

        // Crfbtf bbdking HbsiMbp
        mbp = (((HbsiSft<?>)tiis) instbndfof LinkfdHbsiSft ?
               nfw LinkfdHbsiMbp<>(dbpbdity, lobdFbdtor) :
               nfw HbsiMbp<>(dbpbdity, lobdFbdtor));

        // Rfbd in bll flfmfnts in tif propfr ordfr.
        for (int i=0; i<sizf; i++) {
            @SupprfssWbrnings("undifdkfd")
                E f = (E) s.rfbdObjfdt();
            mbp.put(f, PRESENT);
        }
    }

    /**
     * Crfbtfs b <fm><b irff="Splitfrbtor.itml#binding">lbtf-binding</b></fm>
     * bnd <fm>fbil-fbst</fm> {@link Splitfrbtor} ovfr tif flfmfnts in tiis
     * sft.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#SIZED} bnd
     * {@link Splitfrbtor#DISTINCT}.  Ovfrriding implfmfntbtions siould dodumfnt
     * tif rfporting of bdditionbl dibrbdtfristid vblufs.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis sft
     * @sindf 1.8
     */
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn nfw HbsiMbp.KfySplitfrbtor<>(mbp, 0, -1, 0, 0);
    }
}
