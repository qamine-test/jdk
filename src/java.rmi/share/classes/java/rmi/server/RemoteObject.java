/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.sfrvfr;

import jbvb.rmi.Rfmotf;
import jbvb.rmi.NoSudiObjfdtExdfption;
import jbvb.lbng.rfflfdt.Proxy;
import sun.rmi.sfrvfr.Util;

/**
 * Tif <dodf>RfmotfObjfdt</dodf> dlbss implfmfnts tif
 * <dodf>jbvb.lbng.Objfdt</dodf> bfibvior for rfmotf objfdts.
 * <dodf>RfmotfObjfdt</dodf> providfs tif rfmotf sfmbntids of Objfdt by
 * implfmfnting mftiods for ibsiCodf, fqubls, bnd toString.
 *
 * @butior      Ann Wollrbti
 * @butior      Lbird Dornin
 * @butior      Pftfr Jonfs
 * @sindf       1.1
 */
publid bbstrbdt dlbss RfmotfObjfdt implfmfnts Rfmotf, jbvb.io.Sfriblizbblf {

    /** Tif objfdt's rfmotf rfffrfndf. */
    trbnsifnt protfdtfd RfmotfRff rff;

    /** indidbtf dompbtibility witi JDK 1.1.x vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = -3215090123894869218L;

    /**
     * Crfbtfs b rfmotf objfdt.
     */
    protfdtfd RfmotfObjfdt() {
        rff = null;
    }

    /**
     * Crfbtfs b rfmotf objfdt, initiblizfd witi tif spfdififd rfmotf
     * rfffrfndf.
     * @pbrbm nfwrff rfmotf rfffrfndf
     */
    protfdtfd RfmotfObjfdt(RfmotfRff nfwrff) {
        rff = nfwrff;
    }

    /**
     * Rfturns tif rfmotf rfffrfndf for tif rfmotf objfdt.
     *
     * <p>Notf: Tif objfdt rfturnfd from tiis mftiod mby bf bn instbndf of
     * bn implfmfntbtion-spfdifid dlbss.  Tif <dodf>RfmotfObjfdt</dodf>
     * dlbss fnsurfs sfriblizbtion portbbility of its instbndfs' rfmotf
     * rfffrfndfs tirougi tif bfibvior of its dustom
     * <dodf>writfObjfdt</dodf> bnd <dodf>rfbdObjfdt</dodf> mftiods.  An
     * instbndf of <dodf>RfmotfRff</dodf> siould not bf sfriblizfd outsidf
     * of its <dodf>RfmotfObjfdt</dodf> wrbppfr instbndf or tif rfsult mby
     * bf unportbblf.
     *
     * @rfturn rfmotf rfffrfndf for tif rfmotf objfdt
     * @sindf 1.2
     */
    publid RfmotfRff gftRff() {
        rfturn rff;
    }

    /**
     * Rfturns tif stub for tif rfmotf objfdt <dodf>obj</dodf> pbssfd
     * bs b pbrbmftfr. Tiis opfrbtion is only vblid <i>bftfr</i>
     * tif objfdt ibs bffn fxportfd.
     * @pbrbm obj tif rfmotf objfdt wiosf stub is nffdfd
     * @rfturn tif stub for tif rfmotf objfdt, <dodf>obj</dodf>.
     * @fxdfption NoSudiObjfdtExdfption if tif stub for tif
     * rfmotf objfdt dould not bf found.
     * @sindf 1.2
     */
    publid stbtid Rfmotf toStub(Rfmotf obj) tirows NoSudiObjfdtExdfption {
        if (obj instbndfof RfmotfStub ||
            (obj != null &&
             Proxy.isProxyClbss(obj.gftClbss()) &&
             Proxy.gftInvodbtionHbndlfr(obj) instbndfof
             RfmotfObjfdtInvodbtionHbndlfr))
        {
            rfturn obj;
        } flsf {
            rfturn sun.rmi.trbnsport.ObjfdtTbblf.gftStub(obj);
        }
    }

    /**
     * Rfturns b ibsidodf for b rfmotf objfdt.  Two rfmotf objfdt stubs
     * tibt rfffr to tif sbmf rfmotf objfdt will ibvf tif sbmf ibsi dodf
     * (in ordfr to support rfmotf objfdts bs kfys in ibsi tbblfs).
     *
     * @sff             jbvb.util.Hbsitbblf
     */
    publid int ibsiCodf() {
        rfturn (rff == null) ? supfr.ibsiCodf() : rff.rfmotfHbsiCodf();
    }

    /**
     * Compbrfs two rfmotf objfdts for fqublity.
     * Rfturns b boolfbn tibt indidbtfs wiftifr tiis rfmotf objfdt is
     * fquivblfnt to tif spfdififd Objfdt. Tiis mftiod is usfd wifn b
     * rfmotf objfdt is storfd in b ibsitbblf.
     * If tif spfdififd Objfdt is not itsflf bn instbndf of RfmotfObjfdt,
     * tifn tiis mftiod dflfgbtfs by rfturning tif rfsult of invoking tif
     * <dodf>fqubls</dodf> mftiod of its pbrbmftfr witi tiis rfmotf objfdt
     * bs tif brgumfnt.
     * @pbrbm   obj     tif Objfdt to dompbrf witi
     * @rfturn  truf if tifsf Objfdts brf fqubl; fblsf otifrwisf.
     * @sff             jbvb.util.Hbsitbblf
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof RfmotfObjfdt) {
            if (rff == null) {
                rfturn obj == tiis;
            } flsf {
                rfturn rff.rfmotfEqubls(((RfmotfObjfdt)obj).rff);
            }
        } flsf if (obj != null) {
            /*
             * Fix for 4099660: if objfdt is not bn instbndf of RfmotfObjfdt,
             * usf tif rfsult of its fqubls mftiod, to support symmftry is b
             * rfmotf objfdt implfmfntbtion dlbss tibt dofs not fxtfnd
             * RfmotfObjfdt wisifs to support fqublity witi its stub objfdts.
             */
            rfturn obj.fqubls(tiis);
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns b String tibt rfprfsfnts tif vbluf of tiis rfmotf objfdt.
     */
    publid String toString() {
        String dlbssnbmf = Util.gftUnqublififdNbmf(gftClbss());
        rfturn (rff == null) ? dlbssnbmf :
            dlbssnbmf + "[" + rff.rfmotfToString() + "]";
    }

    /**
     * <dodf>writfObjfdt</dodf> for dustom sfriblizbtion.
     *
     * <p>Tiis mftiod writfs tiis objfdt's sfriblizfd form for tiis dlbss
     * bs follows:
     *
     * <p>Tif {@link RfmotfRff#gftRffClbss(jbvb.io.ObjfdtOutput) gftRffClbss}
     * mftiod is invokfd on tiis objfdt's <dodf>rff</dodf> fifld
     * to obtbin its fxtfrnbl rff typf nbmf.
     * If tif vbluf rfturnfd by <dodf>gftRffClbss</dodf> wbs
     * b non-<dodf>null</dodf> string of lfngti grfbtfr tibn zfro,
     * tif <dodf>writfUTF</dodf> mftiod is invokfd on <dodf>out</dodf>
     * witi tif vbluf rfturnfd by <dodf>gftRffClbss</dodf>, bnd tifn
     * tif <dodf>writfExtfrnbl</dodf> mftiod is invokfd on
     * tiis objfdt's <dodf>rff</dodf> fifld pbssing <dodf>out</dodf>
     * bs tif brgumfnt; otifrwisf,
     * tif <dodf>writfUTF</dodf> mftiod is invokfd on <dodf>out</dodf>
     * witi b zfro-lfngti string (<dodf>""</dodf>), bnd tifn
     * tif <dodf>writfObjfdt</dodf> mftiod is invokfd on <dodf>out</dodf>
     * pbssing tiis objfdt's <dodf>rff</dodf> fifld bs tif brgumfnt.
     *
     * @sfriblDbtb
     *
     * Tif sfriblizfd dbtb for tiis dlbss domprisfs b string (writtfn witi
     * <dodf>ObjfdtOutput.writfUTF</dodf>) tibt is fitifr tif fxtfrnbl
     * rff typf nbmf of tif dontbinfd <dodf>RfmotfRff</dodf> instbndf
     * (tif <dodf>rff</dodf> fifld) or b zfro-lfngti string, followfd by
     * fitifr tif fxtfrnbl form of tif <dodf>rff</dodf> fifld bs writtfn by
     * its <dodf>writfExtfrnbl</dodf> mftiod if tif string wbs of non-zfro
     * lfngti, or tif sfriblizfd form of tif <dodf>rff</dodf> fifld bs
     * writtfn by pbssing it to tif sfriblizbtion strfbm's
     * <dodf>writfObjfdt</dodf> if tif string wbs of zfro lfngti.
     *
     * <p>If tiis objfdt is bn instbndf of
     * {@link RfmotfStub} or {@link RfmotfObjfdtInvodbtionHbndlfr}
     * tibt wbs rfturnfd from bny of
     * tif <dodf>UnidbstRfmotfObjfdt.fxportObjfdt</dodf> mftiods
     * bnd dustom sodkft fbdtorifs brf not usfd,
     * tif fxtfrnbl rff typf nbmf is <dodf>"UnidbstRff"</dodf>.
     *
     * If tiis objfdt is bn instbndf of
     * <dodf>RfmotfStub</dodf> or <dodf>RfmotfObjfdtInvodbtionHbndlfr</dodf>
     * tibt wbs rfturnfd from bny of
     * tif <dodf>UnidbstRfmotfObjfdt.fxportObjfdt</dodf> mftiods
     * bnd dustom sodkft fbdtorifs brf usfd,
     * tif fxtfrnbl rff typf nbmf is <dodf>"UnidbstRff2"</dodf>.
     *
     * If tiis objfdt is bn instbndf of
     * <dodf>RfmotfStub</dodf> or <dodf>RfmotfObjfdtInvodbtionHbndlfr</dodf>
     * tibt wbs rfturnfd from bny of
     * tif <dodf>jbvb.rmi.bdtivbtion.Adtivbtbblf.fxportObjfdt</dodf> mftiods,
     * tif fxtfrnbl rff typf nbmf is <dodf>"AdtivbtbblfRff"</dodf>.
     *
     * If tiis objfdt is bn instbndf of
     * <dodf>RfmotfStub</dodf> or <dodf>RfmotfObjfdtInvodbtionHbndlfr</dodf>
     * tibt wbs rfturnfd from
     * tif <dodf>RfmotfObjfdt.toStub</dodf> mftiod (bnd tif brgumfnt pbssfd
     * to <dodf>toStub</dodf> wbs not itsflf b <dodf>RfmotfStub</dodf>),
     * tif fxtfrnbl rff typf nbmf is b fundtion of iow tif rfmotf objfdt
     * pbssfd to <dodf>toStub</dodf> wbs fxportfd, bs dfsdribfd bbovf.
     *
     * If tiis objfdt is bn instbndf of
     * <dodf>RfmotfStub</dodf> or <dodf>RfmotfObjfdtInvodbtionHbndlfr</dodf>
     * tibt wbs originblly drfbtfd vib dfsfriblizbtion,
     * tif fxtfrnbl rff typf nbmf is tif sbmf bs tibt wiidi wbs rfbd
     * wifn tiis objfdt wbs dfsfriblizfd.
     *
     * <p>If tiis objfdt is bn instbndf of
     * <dodf>jbvb.rmi.sfrvfr.UnidbstRfmotfObjfdt</dodf> tibt dofs not
     * usf dustom sodkft fbdtorifs,
     * tif fxtfrnbl rff typf nbmf is <dodf>"UnidbstSfrvfrRff"</dodf>.
     *
     * If tiis objfdt is bn instbndf of
     * <dodf>UnidbstRfmotfObjfdt</dodf> tibt dofs
     * usf dustom sodkft fbdtorifs,
     * tif fxtfrnbl rff typf nbmf is <dodf>"UnidbstSfrvfrRff2"</dodf>.
     *
     * <p>Following is tif dbtb tibt must bf writtfn by tif
     * <dodf>writfExtfrnbl</dodf> mftiod bnd rfbd by tif
     * <dodf>rfbdExtfrnbl</dodf> mftiod of <dodf>RfmotfRff</dodf>
     * implfmfntbtion dlbssfs tibt dorrfspond to tif fbdi of tif
     * dffinfd fxtfrnbl rff typf nbmfs:
     *
     * <p>For <dodf>"UnidbstRff"</dodf>:
     *
     * <ul>
     *
     * <li>tif iostnbmf of tif rfffrfndfd rfmotf objfdt,
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfUTF(String)}
     *
     * <li>tif port of tif rfffrfndfd rfmotf objfdt,
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfInt(int)}
     *
     * <li>tif dbtb writtfn bs b rfsult of dblling
     * {link jbvb.rmi.sfrvfr.ObjID#writf(jbvb.io.ObjfdtOutput)}
     * on tif <dodf>ObjID</dodf> instbndf dontbinfd in tif rfffrfndf
     *
     * <li>tif boolfbn vbluf <dodf>fblsf</dodf>,
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfBoolfbn(boolfbn)}
     *
     * </ul>
     *
     * <p>For <dodf>"UnidbstRff2"</dodf> witi b
     * <dodf>null</dodf> dlifnt sodkft fbdtory:
     *
     * <ul>
     *
     * <li>tif bytf vbluf <dodf>0x00</dodf>
     * (indidbting <dodf>null</dodf> dlifnt sodkft fbdtory),
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfBytf(int)}
     *
     * <li>tif iostnbmf of tif rfffrfndfd rfmotf objfdt,
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfUTF(String)}
     *
     * <li>tif port of tif rfffrfndfd rfmotf objfdt,
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfInt(int)}
     *
     * <li>tif dbtb writtfn bs b rfsult of dblling
     * {link jbvb.rmi.sfrvfr.ObjID#writf(jbvb.io.ObjfdtOutput)}
     * on tif <dodf>ObjID</dodf> instbndf dontbinfd in tif rfffrfndf
     *
     * <li>tif boolfbn vbluf <dodf>fblsf</dodf>,
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfBoolfbn(boolfbn)}
     *
     * </ul>
     *
     * <p>For <dodf>"UnidbstRff2"</dodf> witi b
     * non-<dodf>null</dodf> dlifnt sodkft fbdtory:
     *
     * <ul>
     *
     * <li>tif bytf vbluf <dodf>0x01</dodf>
     * (indidbting non-<dodf>null</dodf> dlifnt sodkft fbdtory),
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfBytf(int)}
     *
     * <li>tif iostnbmf of tif rfffrfndfd rfmotf objfdt,
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfUTF(String)}
     *
     * <li>tif port of tif rfffrfndfd rfmotf objfdt,
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfInt(int)}
     *
     * <li>b dlifnt sodkft fbdtory (objfdt of typf
     * <dodf>jbvb.rmi.sfrvfr.RMIClifntSodkftFbdtory</dodf>),
     * writtfn by pbssing it to bn invodbtion of
     * <dodf>writfObjfdt</dodf> on tif strfbm instbndf
     *
     * <li>tif dbtb writtfn bs b rfsult of dblling
     * {link jbvb.rmi.sfrvfr.ObjID#writf(jbvb.io.ObjfdtOutput)}
     * on tif <dodf>ObjID</dodf> instbndf dontbinfd in tif rfffrfndf
     *
     * <li>tif boolfbn vbluf <dodf>fblsf</dodf>,
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfBoolfbn(boolfbn)}
     *
     * </ul>
     *
     * <p>For <dodf>"AdtivbtbblfRff"</dodf> witi b
     * <dodf>null</dodf> nfstfd rfmotf rfffrfndf:
     *
     * <ul>
     *
     * <li>bn instbndf of
     * <dodf>jbvb.rmi.bdtivbtion.AdtivbtionID</dodf>,
     * writtfn by pbssing it to bn invodbtion of
     * <dodf>writfObjfdt</dodf> on tif strfbm instbndf
     *
     * <li>b zfro-lfngti string (<dodf>""</dodf>),
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfUTF(String)}
     *
     * </ul>
     *
     * <p>For <dodf>"AdtivbtbblfRff"</dodf> witi b
     * non-<dodf>null</dodf> nfstfd rfmotf rfffrfndf:
     *
     * <ul>
     *
     * <li>bn instbndf of
     * <dodf>jbvb.rmi.bdtivbtion.AdtivbtionID</dodf>,
     * writtfn by pbssing it to bn invodbtion of
     * <dodf>writfObjfdt</dodf> on tif strfbm instbndf
     *
     * <li>tif fxtfrnbl rff typf nbmf of tif nfstfd rfmotf rfffrfndf,
     * wiidi must bf <dodf>"UnidbstRff2"</dodf>,
     * writtfn by {@link jbvb.io.ObjfdtOutput#writfUTF(String)}
     *
     * <li>tif fxtfrnbl form of tif nfstfd rfmotf rfffrfndf,
     * writtfn by invoking its <dodf>writfExtfrnbl</dodf> mftiod
     * witi tif strfbm instbndf
     * (sff tif dfsdription of tif fxtfrnbl form for
     * <dodf>"UnidbstRff2"</dodf> bbovf)
     *
     * </ul>
     *
     * <p>For <dodf>"UnidbstSfrvfrRff"</dodf> bnd
     * <dodf>"UnidbstSfrvfrRff2"</dodf>, no dbtb is writtfn by tif
     * <dodf>writfExtfrnbl</dodf> mftiod or rfbd by tif
     * <dodf>rfbdExtfrnbl</dodf> mftiod.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm out)
        tirows jbvb.io.IOExdfption, jbvb.lbng.ClbssNotFoundExdfption
    {
        if (rff == null) {
            tirow nfw jbvb.rmi.MbrsiblExdfption("Invblid rfmotf objfdt");
        } flsf {
            String rffClbssNbmf = rff.gftRffClbss(out);
            if (rffClbssNbmf == null || rffClbssNbmf.lfngti() == 0) {
                /*
                 * No rfffrfndf dlbss nbmf spfdififd, so sfriblizf
                 * rfmotf rfffrfndf.
                 */
                out.writfUTF("");
                out.writfObjfdt(rff);
            } flsf {
                /*
                 * Built-in rfffrfndf dlbss spfdififd, so dflfgbtf
                 * to rfffrfndf to writf out its fxtfrnbl form.
                 */
                out.writfUTF(rffClbssNbmf);
                rff.writfExtfrnbl(out);
            }
        }
    }

    /**
     * <dodf>rfbdObjfdt</dodf> for dustom sfriblizbtion.
     *
     * <p>Tiis mftiod rfbds tiis objfdt's sfriblizfd form for tiis dlbss
     * bs follows:
     *
     * <p>Tif <dodf>rfbdUTF</dodf> mftiod is invokfd on <dodf>in</dodf>
     * to rfbd tif fxtfrnbl rff typf nbmf for tif <dodf>RfmotfRff</dodf>
     * instbndf to bf fillfd in to tiis objfdt's <dodf>rff</dodf> fifld.
     * If tif string rfturnfd by <dodf>rfbdUTF</dodf> ibs lfngti zfro,
     * tif <dodf>rfbdObjfdt</dodf> mftiod is invokfd on <dodf>in</dodf>,
     * bnd tibn tif vbluf rfturnfd by <dodf>rfbdObjfdt</dodf> is dbst to
     * <dodf>RfmotfRff</dodf> bnd tiis objfdt's <dodf>rff</dodf> fifld is
     * sft to tibt vbluf.
     * Otifrwisf, tiis objfdt's <dodf>rff</dodf> fifld is sft to b
     * <dodf>RfmotfRff</dodf> instbndf tibt is drfbtfd of bn
     * implfmfntbtion-spfdifid dlbss dorrfsponding to tif fxtfrnbl rff
     * typf nbmf rfturnfd by <dodf>rfbdUTF</dodf>, bnd tifn
     * tif <dodf>rfbdExtfrnbl</dodf> mftiod is invokfd on
     * tiis objfdt's <dodf>rff</dodf> fifld.
     *
     * <p>If tif fxtfrnbl rff typf nbmf is
     * <dodf>"UnidbstRff"</dodf>, <dodf>"UnidbstSfrvfrRff"</dodf>,
     * <dodf>"UnidbstRff2"</dodf>, <dodf>"UnidbstSfrvfrRff2"</dodf>,
     * or <dodf>"AdtivbtbblfRff"</dodf>, b dorrfsponding
     * implfmfntbtion-spfdifid dlbss must bf found, bnd its
     * <dodf>rfbdExtfrnbl</dodf> mftiod must rfbd tif sfribl dbtb
     * for tibt fxtfrnbl rff typf nbmf bs spfdififd to bf writtfn
     * in tif <b>sfriblDbtb</b> dodumfntbtion for tiis dlbss.
     * If tif fxtfrnbl rff typf nbmf is bny otifr string (of non-zfro
     * lfngti), b <dodf>ClbssNotFoundExdfption</dodf> will bf tirown,
     * unlfss tif implfmfntbtion providfs bn implfmfntbtion-spfdifid
     * dlbss dorrfsponding to tibt fxtfrnbl rff typf nbmf, in wiidi
     * dbsf tiis objfdt's <dodf>rff</dodf> fifld will bf sft to bn
     * instbndf of tibt implfmfntbtion-spfdifid dlbss.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm in)
        tirows jbvb.io.IOExdfption, jbvb.lbng.ClbssNotFoundExdfption
    {
        String rffClbssNbmf = in.rfbdUTF();
        if (rffClbssNbmf == null || rffClbssNbmf.lfngti() == 0) {
            /*
             * No rfffrfndf dlbss nbmf spfdififd, so donstrudt
             * rfmotf rfffrfndf from its sfriblizfd form.
             */
            rff = (RfmotfRff) in.rfbdObjfdt();
        } flsf {
            /*
             * Built-in rfffrfndf dlbss spfdififd, so dflfgbtf to
             * intfrnbl rfffrfndf dlbss to initiblizf its fiflds from
             * its fxtfrnbl form.
             */
            String intfrnblRffClbssNbmf =
                RfmotfRff.pbdkbgfPrffix + "." + rffClbssNbmf;
            Clbss<?> rffClbss = Clbss.forNbmf(intfrnblRffClbssNbmf);
            try {
                rff = (RfmotfRff) rffClbss.nfwInstbndf();

                /*
                 * If tiis stfp fbils, bssumf wf found bn intfrnbl
                 * dlbss tibt is not mfbnt to bf b sfriblizbblf rff
                 * typf.
                 */
            } dbtdi (InstbntibtionExdfption f) {
                tirow nfw ClbssNotFoundExdfption(intfrnblRffClbssNbmf, f);
            } dbtdi (IllfgblAddfssExdfption f) {
                tirow nfw ClbssNotFoundExdfption(intfrnblRffClbssNbmf, f);
            } dbtdi (ClbssCbstExdfption f) {
                tirow nfw ClbssNotFoundExdfption(intfrnblRffClbssNbmf, f);
            }
            rff.rfbdExtfrnbl(in);
        }
    }
}
