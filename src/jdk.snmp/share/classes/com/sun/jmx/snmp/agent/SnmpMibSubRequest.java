/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Enumfrbtion;
import jbvb.util.Vfdtor;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpOid;
// import dom.sun.jmx.snmp.SnmpIndfx;

/**
 * Tiis intfrfbdf modfls bn SNMP sub rfqufst to bf pfrformfd on b spfdifid
 * SNMP MIB nodf. Tif nodf involvfd dbn bf fitifr bn SNMP group, bn SNMP tbblf,
 * or bn SNMP tbblf fntry (dondfptubl row). Tif dondfptubl row mby or mby not
 * blrfbdy fxist. If tif row did not fxist bt tif timf wifn tif rfqufst
 * wbs rfdfivfd, tif <CODE>isNfwEntry()</CODE> mftiod will rfturn <CODE>
 * truf</CODE>.
 * <p>
 * Objfdts implfmfnting tiis intfrfbdf will bf bllodbtfd by tif SNMP fnginf.
 * You will nfvfr nffd to implfmfnt tiis intfrfbdf. You will only usf it.
 * </p>
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */
publid intfrfbdf SnmpMibSubRfqufst fxtfnds SnmpMibRfqufst {
    /**
     * Rfturn tif list of vbrbind to bf ibndlfd by tif SNMP MIB nodf.
     * <p>
     * <b>Notf:</b> <ul>
     * <i>In dbsf of SET opfrbtion, if tiis nodf is b tbblf row wiidi
     * dontbins b dontrol vbribblf (bs idfntififd by tif tbblf's
     * isRowStbtus() mftiod) tif dontrol vbribblf will not
     * bf indludfd in tiis list: it will bf obtbinfd by dblling
     * gftRowStbtusVbrBind(). Tiis will bllow you to ibndlf tif dontrol
     * vbribblf spfdifidblly.</i><br>
     * You will nfvfr nffd to worry bbout tiis unlfss you nffd to
     * implfmfnt b non stbndbrd mfdibnism for ibndling row
     * drfbtion bnd dflftion.
     * </ul>
     * <p>
     * @rfturn Tif flfmfnts of tif fnumfrbtion brf instbndfs of
     *         {@link dom.sun.jmx.snmp.SnmpVbrBind}
     */
    @Ovfrridf
    publid Enumfrbtion<SnmpVbrBind> gftElfmfnts();

    /**
     * Rfturn tif list of vbrbind to bf ibndlfd by tif SNMP MIB nodf.
     * <p>
     * <b>Notf:</b> <ul>
     * <i>In dbsf of SET opfrbtion, if tiis nodf is b tbblf row wiidi
     * dontbins b dontrol vbribblf (bs idfntififd by tif tbblf's
     * isRowStbtus() mftiod) tif dontrol vbribblf will not
     * bf indludfd in tiis list: it will bf obtbinfd by dblling
     * gftRowStbtusVbrBind(). Tiis will bllow you to ibndlf tif dontrol
     * vbribblf spfdifidblly.</i><br>
     * You will nfvfr nffd to worry bbout tiis unlfss you nffd to
     * implfmfnt b non stbndbrd mfdibnism for ibndling row
     * drfbtion bnd dflftion.
     * </ul>
     * <p>
     * @rfturn Tif flfmfnts of tif vfdtor brf instbndfs of
     *         {@link dom.sun.jmx.snmp.SnmpVbrBind}
     */
    @Ovfrridf
    publid Vfdtor<SnmpVbrBind> gftSubList();

    /**
     * Rfturn tif pbrt of tif OID idfntifying tif tbblf fntry involvfd.
     * <p>
     *
     * @rfturn {@link dom.sun.jmx.snmp.SnmpOid} or <CODE>null</CODE>
     *         if tif rfqufst is not dirfdtfd to bn fntry.
     */
    publid SnmpOid     gftEntryOid();

    /**
     * Indidbtf wiftifr tif fntry involvfd is b nfw fntry.
     * Tiis mftiod will rfturn <CODE>truf</CODE> if tif fntry wbs not
     * found wifn tif rfqufst wbs prodfssfd. As b donsfqufndf, <CODE>
     * truf</CODE> mfbns tibt fitifr tif fntry dofs not fxist yft,
     * or it ibs bffn drfbtfd wiilf prodfssing tiis rfqufst.
     * Tif rfsult of tiis mftiod is only signifidbnt wifn bn fntry
     * is involvfd.
     *
     * <p>
     * @rfturn <CODE>truf</CODE> If tif fntry did not fxist,
     *  or <CODE>fblsf</CODE> if tif fntry involvfd wbs found.
     */
    publid boolfbn     isNfwEntry();

    /**
     * Rfturn tif vbrbind tibt iolds tif RowStbtus vbribblf.
     * It dorrfsponds to tif vbrbind tibt wbs idfntififd by
     * tif <dodf>isRowStbtus()</dodf> mftiod gfnfrbtfd by mibgfn
     * on {@link dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf} dfrivbtivfs.
     * <ul><li>In SMIv2, it is tif vbrbind wiidi dontbins tif dolumnbr
     *         objfdt implfmfnting tif RowStbtus TEXTUAL-CONVENTION.</li>
     *      <li>In SMIv1 notiing spfdibl is gfnfrbtfd</li>
     *      <ul>You mby iowfvfr subdlbss tif gfnfrbtfd tbblf mftbdbtb
     *          dlbss in ordfr to providf your own implfmfntbtion of
     *          isRowStbtus(), gftRowAdtion(), isRowRfbdy() bnd
     *          sftRowStbtus()
     *          (sff  {@link dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf}).</ul>
     * </ul>
     * <p>
     * @rfturn b vbrbind tibt sfrvfs to dontrol tif tbblf modifidbtion.
     *         <dodf>null</dodf> mfbns tibt no sudi vbrbind dould bf
     *         idfntififd.<br>
     *         <b>Notf:</b><i>Tif runtimf will only try to idfntify
     *         tif RowStbtus vbrbind wifn prodfssing bn
     *         SNMP SET rfqufst. In tiis dbsf, tif idfntififd
     *         vbrbind will not bf indludfd in tif sft of vbrbinds
     *         rfturnfd by gftSubList() bnd gftElfmfnts().
     *         </i>
     *
     **/
    publid SnmpVbrBind gftRowStbtusVbrBind();

    /**
     * Tiis mftiod siould bf dbllfd wifn b stbtus fxdfption nffds to
     * bf rbisfd for b givfn vbrbind of bn SNMP GET rfqufst. Tiis mftiod
     * pfrforms bll tif nfdfssbry donvfrsions (SNMPv1 <=> SNMPv2) bnd
     * propbgbtfs tif fxdfption if nffdfd:
     * If tif vfrsion is SNMP v1, tif fxdfption is propbgbtfd.
     * If tif vfrsion is SNMP v2, tif fxdfption is storfd in tif vbrbind.
     * Tiis mftiod blso tbkfs dbrf of sftting tif dorrfdt vbluf of tif
     * indfx fifld.
     * <p>
     *
     * @pbrbm vbrbind Tif vbrbind for wiidi tif fxdfption is
     *        rfgistfrfd. Notf tibt tiis vbrbind <b>must</b> ibvf
     *        bffn obtbinfd from tif fnumfrbtion rfturnfd by
     *        <CODE>gftElfmfnts()</CODE>, or from tif vfdtor
     *        rfturnfd by <CODE>gftSubList()</CODE>
     *
     * @pbrbm fxdfption Tif fxdfption to bf rfgistfrfd for tif givfn vbrbind.
     *
     */
    publid void rfgistfrGftExdfption(SnmpVbrBind vbrbind,
                                     SnmpStbtusExdfption fxdfption)
        tirows SnmpStbtusExdfption;

    /**
     * Tiis mftiod siould bf dbllfd wifn b stbtus fxdfption nffds to
     * bf rbisfd for b givfn vbrbind of bn SNMP SET rfqufst. Tiis mftiod
     * pfrforms bll tif nfdfssbry donvfrsions (SNMPv1 <=> SNMPv2) bnd
     * propbgbtfs tif fxdfption if nffdfd.
     * Tiis mftiod blso tbkfs dbrf of sftting tif dorrfdt vbluf of tif
     * indfx fifld.
     * <p>
     *
     * @pbrbm vbrbind Tif vbrbind for wiidi tif fxdfption is
     *        rfgistfrfd. Notf tibt tiis vbrbind <b>must</b> ibvf
     *        bffn obtbinfd from tif fnumfrbtion rfturnfd by
     *        <CODE>gftElfmfnts()</CODE>, or from tif vfdtor
     *        rfturnfd by <CODE>gftSubList()</CODE>
     *
     * @pbrbm fxdfption Tif fxdfption to bf rfgistfrfd for tif givfn vbrbind.
     *
     */
    publid void rfgistfrSftExdfption(SnmpVbrBind vbrbind,
                                     SnmpStbtusExdfption fxdfption)
        tirows SnmpStbtusExdfption;

    /**
     * Tiis mftiod siould bf dbllfd wifn b stbtus fxdfption nffds to
     * bf rbisfd wifn difdking b givfn vbrbind for bn SNMP SET rfqufst.
     * Tiis mftiod pfrforms bll tif nfdfssbry donvfrsions (SNMPv1 <=>
     * SNMPv2) bnd propbgbtfs tif fxdfption if nffdfd.
     * Tiis mftiod blso tbkfs dbrf of sftting tif dorrfdt vbluf of tif
     * indfx fifld.
     * <p>
     *
     * @pbrbm vbrbind Tif vbrbind for wiidi tif fxdfption is
     *        rfgistfrfd. Notf tibt tiis vbrbind <b>must</b> ibvf
     *        bffn obtbinfd from tif fnumfrbtion rfturnfd by
     *        <CODE>gftElfmfnts()</CODE>, or from tif vfdtor
     *        rfturnfd by <CODE>gftSubList()</CODE>
     *
     * @pbrbm fxdfption Tif fxdfption to bf rfgistfrfd for tif givfn vbrbind.
     *
     */
    publid void rfgistfrCifdkExdfption(SnmpVbrBind vbrbind,
                                       SnmpStbtusExdfption fxdfption)
        tirows SnmpStbtusExdfption;
}
