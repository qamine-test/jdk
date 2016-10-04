/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.snmp.bgfnt;

// jbvb imports
//
import dom.sun.jmx.snmp.SnmpDffinitions;
import jbvb.io.Sfriblizbblf;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

/**
 * Rfprfsfnts b nodf in bn SNMP MIB wiidi dorrfsponds to b tbblf fntry
 * mftb nodf.
 * <P>
 * Tiis dlbss is usfd by tif dlbss gfnfrbtfd by <CODE>mibgfn</CODE>.
 * You siould not nffd to usf tiis dlbss dirfdtly.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss SnmpMibEntry fxtfnds SnmpMibNodf
    implfmfnts Sfriblizbblf {

    /**
     * Tflls wiftifr tif givfn brd idfntififs b vbribblf (sdblbr objfdt) in
     * tiis fntry.
     *
     * @pbrbm brd An OID brd.
     *
     * @rfturn <CODE>truf</CODE> if `brd' lfbds to b vbribblf.
     */
    publid bbstrbdt boolfbn isVbribblf(long brd);

    /**
     * Tflls wiftifr tif givfn brd idfntififs b rfbdbblf sdblbr objfdt in
     * tiis fntry.
     *
     * @pbrbm brd An OID brd.
     *
     * @rfturn <CODE>truf</CODE> if `brd' lfbds to b rfbdbblf vbribblf.
     */
    publid bbstrbdt boolfbn isRfbdbblf(long brd);

    /**
     * Gft tif nfxt OID brd dorrfsponding to b rfbdbblf sdblbr vbribblf.
     *
     */
    publid long gftNfxtVbrId(long id, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        long nfxtvbr = supfr.gftNfxtVbrId(id,usfrDbtb);
        wiilf (!isRfbdbblf(nfxtvbr))
            nfxtvbr = supfr.gftNfxtVbrId(nfxtvbr,usfrDbtb);
        rfturn nfxtvbr;
    }

    /**
     * Cifdks wiftifr tif givfn OID brd idfntififs b vbribblf (dolumnbr
     * objfdt).
     *
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @fxdfption If tif givfn `brd' dofs not idfntify bny vbribblf in tiis
     *    group, tirows bn SnmpStbtusExdfption.
     */
    publid void vblidbtfVbrId(long brd, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        if (isVbribblf(brd) == fblsf) {
            tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspNoSudiNbmf);
        }
    }

    /**
     * Gfnfrid ibndling of tif <CODE>gft</CODE> opfrbtion.
     * <p>Tif bdtubl implfmfntbtion of tiis mftiod will bf gfnfrbtfd
     * by mibgfn. Usublly, tiis implfmfntbtion only dflfgbtfs tif
     * job to somf otifr providfd runtimf dlbss, wiidi knows iow to
     * bddfss tif MBfbn. Tif durrfnt toolkit tius providfs two
     * implfmfntbtions:
     * <ul><li>Tif stbndbrd implfmfntbtion will dirfdtly bddfss tif
     *         MBfbn tirougi b jbvb rfffrfndf,</li>
     *     <li>Tif gfnfrid implfmfntbtion will bddfss tif MBfbn tirougi
     *         tif MBfbn sfrvfr.</li>
     * </ul>
     * <p>Boti implfmfntbtions rfly upon spfdifid - bnd distindt, sft of
     * mibgfn gfnfrbtfd mftiods.
     * <p> You dbn ovfrridf tiis mftiod if you nffd to implfmfnt somf
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs.
     * <p>
     *
     * @pbrbm rfq   Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm dfpti Tif dfpti rfbdifd in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd wiilf bddfssing
     *  tif MIB nodf.
     */
    bbstrbdt publid void gft(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption;

    /**
     * Gfnfrid ibndling of tif <CODE>sft</CODE> opfrbtion.
     * <p>Tif bdtubl implfmfntbtion of tiis mftiod will bf gfnfrbtfd
     * by mibgfn. Usublly, tiis implfmfntbtion only dflfgbtfs tif
     * job to somf otifr providfd runtimf dlbss, wiidi knows iow to
     * bddfss tif MBfbn. Tif durrfnt toolkit tius providfs two
     * implfmfntbtions:
     * <ul><li>Tif stbndbrd implfmfntbtion will dirfdtly bddfss tif
     *         MBfbn tirougi b jbvb rfffrfndf,</li>
     *     <li>Tif gfnfrid implfmfntbtion will bddfss tif MBfbn tirougi
     *         tif MBfbn sfrvfr.</li>
     * </ul>
     * <p>Boti implfmfntbtions rfly upon spfdifid - bnd distindt, sft of
     * mibgfn gfnfrbtfd mftiods.
     * <p> You dbn ovfrridf tiis mftiod if you nffd to implfmfnt somf
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs.
     * <p>
     *
     * @pbrbm rfq   Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm dfpti Tif dfpti rfbdifd in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd wiilf bddfssing
     *  tif MIB nodf.
     */
    bbstrbdt publid void sft(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption;

    /**
     * Gfnfrid ibndling of tif <CODE>difdk</CODE> opfrbtion.
     *
     * <p>Tif bdtubl implfmfntbtion of tiis mftiod will bf gfnfrbtfd
     * by mibgfn. Usublly, tiis implfmfntbtion only dflfgbtfs tif
     * job to somf otifr providfd runtimf dlbss, wiidi knows iow to
     * bddfss tif MBfbn. Tif durrfnt toolkit tius providfs two
     * implfmfntbtions:
     * <ul><li>Tif stbndbrd implfmfntbtion will dirfdtly bddfss tif
     *         MBfbn tirougi b jbvb rfffrfndf,</li>
     *     <li>Tif gfnfrid implfmfntbtion will bddfss tif MBfbn tirougi
     *         tif MBfbn sfrvfr.</li>
     * </ul>
     * <p>Boti implfmfntbtions rfly upon spfdifid - bnd distindt, sft of
     * mibgfn gfnfrbtfd mftiods.
     * <p> You dbn ovfrridf tiis mftiod if you nffd to implfmfnt somf
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs, or if you nffd to implfmfnt somf donsistfndy
     * difdks bftwffn tif difffrfnt vblufs providfd in tif vbrbind list.
     * <p>
     *
     * @pbrbm rfq   Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm dfpti Tif dfpti rfbdifd in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd wiilf bddfssing
     *  tif MIB nodf.
     */
    bbstrbdt publid void difdk(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption;

}
