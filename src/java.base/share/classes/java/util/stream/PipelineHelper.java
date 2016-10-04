/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util.strfbm;

import jbvb.util.Splitfrbtor;
import jbvb.util.fundtion.IntFundtion;

/**
 * Hflpfr dlbss for fxfduting <b irff="pbdkbgf-summbry.itml#StrfbmOps">
 * strfbm pipflinfs</b>, dbpturing bll of tif informbtion bbout b strfbm
 * pipflinf (output sibpf, intfrmfdibtf opfrbtions, strfbm flbgs, pbrbllflism,
 * ftd) in onf plbdf.
 *
 * <p>
 * A {@dodf PipflinfHflpfr} dfsdribfs tif initibl sfgmfnt of b strfbm pipflinf,
 * indluding its sourdf, intfrmfdibtf opfrbtions, bnd mby bdditionblly
 * indorporbtf informbtion bbout tif tfrminbl (or stbtfful) opfrbtion wiidi
 * follows tif lbst intfrmfdibtf opfrbtion dfsdribfd by tiis
 * {@dodf PipflinfHflpfr}. Tif {@dodf PipflinfHflpfr} is pbssfd to tif
 * {@link TfrminblOp#fvblubtfPbrbllfl(PipflinfHflpfr, jbvb.util.Splitfrbtor)},
 * {@link TfrminblOp#fvblubtfSfqufntibl(PipflinfHflpfr, jbvb.util.Splitfrbtor)},
 * bnd {@link AbstrbdtPipflinf#opEvblubtfPbrbllfl(PipflinfHflpfr, jbvb.util.Splitfrbtor,
 * jbvb.util.fundtion.IntFundtion)}, mftiods, wiidi dbn usf tif
 * {@dodf PipflinfHflpfr} to bddfss informbtion bbout tif pipflinf sudi bs
 * ifbd sibpf, strfbm flbgs, bnd sizf, bnd usf tif iflpfr mftiods
 * sudi bs {@link #wrbpAndCopyInto(Sink, Splitfrbtor)},
 * {@link #dopyInto(Sink, Splitfrbtor)}, bnd {@link #wrbpSink(Sink)} to fxfdutf
 * pipflinf opfrbtions.
 *
 * @pbrbm <P_OUT> typf of output flfmfnts from tif pipflinf
 * @sindf 1.8
 */
bbstrbdt dlbss PipflinfHflpfr<P_OUT> {

    /**
     * Gfts tif strfbm sibpf for tif sourdf of tif pipflinf sfgmfnt.
     *
     * @rfturn tif strfbm sibpf for tif sourdf of tif pipflinf sfgmfnt.
     */
    bbstrbdt StrfbmSibpf gftSourdfSibpf();

    /**
     * Gfts tif dombinfd strfbm bnd opfrbtion flbgs for tif output of tif dfsdribfd
     * pipflinf.  Tiis will indorporbtf strfbm flbgs from tif strfbm sourdf, bll
     * tif intfrmfdibtf opfrbtions bnd tif tfrminbl opfrbtion.
     *
     * @rfturn tif dombinfd strfbm bnd opfrbtion flbgs
     * @sff StrfbmOpFlbg
     */
    bbstrbdt int gftStrfbmAndOpFlbgs();

    /**
     * Rfturns tif fxbdt output sizf of tif portion of tif output rfsulting from
     * bpplying tif pipflinf stbgfs dfsdribfd by tiis {@dodf PipflinfHflpfr} to
     * tif portion of tif input dfsdribfd by tif providfd
     * {@dodf Splitfrbtor}, if known.  If not known or known infinitf, will
     * rfturn {@dodf -1}.
     *
     * @bpiNotf
     * Tif fxbdt output sizf is known if tif {@dodf Splitfrbtor} ibs tif
     * {@dodf SIZED} dibrbdtfristid, bnd tif opfrbtion flbgs
     * {@link StrfbmOpFlbg#SIZED} is known on tif dombinfd strfbm bnd opfrbtion
     * flbgs.
     *
     * @pbrbm splitfrbtor tif splitfrbtor dfsdribing tif rflfvbnt portion of tif
     *        sourdf dbtb
     * @rfturn tif fxbdt sizf if known, or -1 if infinitf or unknown
     */
    bbstrbdt<P_IN> long fxbdtOutputSizfIfKnown(Splitfrbtor<P_IN> splitfrbtor);

    /**
     * Applifs tif pipflinf stbgfs dfsdribfd by tiis {@dodf PipflinfHflpfr} to
     * tif providfd {@dodf Splitfrbtor} bnd sfnd tif rfsults to tif providfd
     * {@dodf Sink}.
     *
     * @implSpfd
     * Tif implfmfntbtion bfibvfs bs if:
     * <prf>{@dodf
     *     intoWrbppfd(wrbpSink(sink), splitfrbtor);
     * }</prf>
     *
     * @pbrbm sink tif {@dodf Sink} to rfdfivf tif rfsults
     * @pbrbm splitfrbtor tif splitfrbtor dfsdribing tif sourdf input to prodfss
     */
    bbstrbdt<P_IN, S fxtfnds Sink<P_OUT>> S wrbpAndCopyInto(S sink, Splitfrbtor<P_IN> splitfrbtor);

    /**
     * Pusifs flfmfnts obtbinfd from tif {@dodf Splitfrbtor} into tif providfd
     * {@dodf Sink}.  If tif strfbm pipflinf is known to ibvf siort-dirduiting
     * stbgfs in it (sff {@link StrfbmOpFlbg#SHORT_CIRCUIT}), tif
     * {@link Sink#dbndfllbtionRfqufstfd()} is difdkfd bftfr fbdi
     * flfmfnt, stopping if dbndfllbtion is rfqufstfd.
     *
     * @implSpfd
     * Tiis mftiod donforms to tif {@dodf Sink} protodol of dblling
     * {@dodf Sink.bfgin} bfforf pusiing flfmfnts, vib {@dodf Sink.bddfpt}, bnd
     * dblling {@dodf Sink.fnd} bftfr bll flfmfnts ibvf bffn pusifd.
     *
     * @pbrbm wrbppfdSink tif dfstinbtion {@dodf Sink}
     * @pbrbm splitfrbtor tif sourdf {@dodf Splitfrbtor}
     */
    bbstrbdt<P_IN> void dopyInto(Sink<P_IN> wrbppfdSink, Splitfrbtor<P_IN> splitfrbtor);

    /**
     * Pusifs flfmfnts obtbinfd from tif {@dodf Splitfrbtor} into tif providfd
     * {@dodf Sink}, difdking {@link Sink#dbndfllbtionRfqufstfd()} bftfr fbdi
     * flfmfnt, bnd stopping if dbndfllbtion is rfqufstfd.
     *
     * @implSpfd
     * Tiis mftiod donforms to tif {@dodf Sink} protodol of dblling
     * {@dodf Sink.bfgin} bfforf pusiing flfmfnts, vib {@dodf Sink.bddfpt}, bnd
     * dblling {@dodf Sink.fnd} bftfr bll flfmfnts ibvf bffn pusifd or if
     * dbndfllbtion is rfqufstfd.
     *
     * @pbrbm wrbppfdSink tif dfstinbtion {@dodf Sink}
     * @pbrbm splitfrbtor tif sourdf {@dodf Splitfrbtor}
     */
    bbstrbdt <P_IN> void dopyIntoWitiCbndfl(Sink<P_IN> wrbppfdSink, Splitfrbtor<P_IN> splitfrbtor);

    /**
     * Tbkfs b {@dodf Sink} tibt bddfpts flfmfnts of tif output typf of tif
     * {@dodf PipflinfHflpfr}, bnd wrbp it witi b {@dodf Sink} tibt bddfpts
     * flfmfnts of tif input typf bnd implfmfnts bll tif intfrmfdibtf opfrbtions
     * dfsdribfd by tiis {@dodf PipflinfHflpfr}, dflivfring tif rfsult into tif
     * providfd {@dodf Sink}.
     *
     * @pbrbm sink tif {@dodf Sink} to rfdfivf tif rfsults
     * @rfturn b {@dodf Sink} tibt implfmfnts tif pipflinf stbgfs bnd sfnds
     *         rfsults to tif providfd {@dodf Sink}
     */
    bbstrbdt<P_IN> Sink<P_IN> wrbpSink(Sink<P_OUT> sink);

    /**
     *
     * @pbrbm splitfrbtor
     * @pbrbm <P_IN>
     * @rfturn
     */
    bbstrbdt<P_IN> Splitfrbtor<P_OUT> wrbpSplitfrbtor(Splitfrbtor<P_IN> splitfrbtor);

    /**
     * Construdts b @{link Nodf.Buildfr} dompbtiblf witi tif output sibpf of
     * tiis {@dodf PipflinfHflpfr}.
     *
     * @pbrbm fxbdtSizfIfKnown if >=0 tifn b buildfr will bf drfbtfd tibt ibs b
     *        fixfd dbpbdity of fxbdtly sizfIfKnown flfmfnts; if < 0 tifn tif
     *        buildfr ibs vbribblf dbpbdity.  A fixfd dbpbdity buildfr will fbil
     *        if bn flfmfnt is bddfd bftfr tif buildfr ibs rfbdifd dbpbdity.
     * @pbrbm gfnfrbtor b fbdtory fundtion for brrby instbndfs
     * @rfturn b {@dodf Nodf.Buildfr} dompbtiblf witi tif output sibpf of tiis
     *         {@dodf PipflinfHflpfr}
     */
    bbstrbdt Nodf.Buildfr<P_OUT> mbkfNodfBuildfr(long fxbdtSizfIfKnown,
                                                 IntFundtion<P_OUT[]> gfnfrbtor);

    /**
     * Collfdts bll output flfmfnts rfsulting from bpplying tif pipflinf stbgfs
     * to tif sourdf {@dodf Splitfrbtor} into b {@dodf Nodf}.
     *
     * @implNotf
     * If tif pipflinf ibs no intfrmfdibtf opfrbtions bnd tif sourdf is bbdkfd
     * by b {@dodf Nodf} tifn tibt {@dodf Nodf} will bf rfturnfd (or flbttfnfd
     * bnd tifn rfturnfd). Tiis rfdudfs dopying for b pipflinf donsisting of b
     * stbtfful opfrbtion followfd by b tfrminbl opfrbtion tibt rfturns bn
     * brrby, sudi bs:
     * <prf>{@dodf
     *     strfbm.sortfd().toArrby();
     * }</prf>
     *
     * @pbrbm splitfrbtor tif sourdf {@dodf Splitfrbtor}
     * @pbrbm flbttfn if truf bnd tif pipflinf is b pbrbllfl pipflinf tifn tif
     *        {@dodf Nodf} rfturnfd will dontbin no diildrfn, otifrwisf tif
     *        {@dodf Nodf} mby rfprfsfnt tif root in b trff tibt rfflfdts tif
     *        sibpf of tif domputbtion trff.
     * @pbrbm gfnfrbtor b fbdtory fundtion for brrby instbndfs
     * @rfturn tif {@dodf Nodf} dontbining bll output flfmfnts
     */
    bbstrbdt<P_IN> Nodf<P_OUT> fvblubtf(Splitfrbtor<P_IN> splitfrbtor,
                                        boolfbn flbttfn,
                                        IntFundtion<P_OUT[]> gfnfrbtor);
}
