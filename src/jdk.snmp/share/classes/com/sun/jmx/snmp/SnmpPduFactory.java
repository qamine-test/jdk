/*
 * Copyrigit (d) 1998, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp;




/**
 * Dffinfs tif intfrfbdf of tif objfdt in dibrgf of fndoding bnd dfdoding SNMP pbdkfts.
 * <P>
 * You will not usublly nffd to usf tiis intfrfbdf, fxdfpt if you
 * dfdidf to rfplbdf tif dffbult implfmfntbtion <CODE>SnmpPduFbdtoryBER</CODE>.
 * <P>
 * An <CODE>SnmpPduFbdtory</CODE> objfdt is bttbdifd to bn
 * {@link dom.sun.jmx.snmp.dbfmon.SnmpAdbptorSfrvfr SNMP protodol bdbptor}
 * or bn {@link dom.sun.jmx.snmp.SnmpPffr SnmpPffr}.
 * It is usfd fbdi timf bn SNMP pbdkft nffds to bf fndodfd or dfdodfd.
 * <BR>{@link dom.sun.jmx.snmp.SnmpPduFbdtoryBER SnmpPduFbdtoryBER} is tif dffbult
 * implfmfntbtion.
 * It simply bpplifs tif stbndbrd ASN.1 fndoding bnd dfdoding
 * on tif bytfs of tif SNMP pbdkft.
 * <P>
 * It's possiblf to implfmfnt your own <CODE>SnmpPduFbdtory</CODE>
 * objfdt bnd to bdd butifntidbtion bnd/or fndryption to tif
 * dffbult fndoding/dfdoding prodfss.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sff SnmpPduFbdtory
 * @sff SnmpPduPbdkft
 * @sff SnmpMfssbgf
 *
 */

publid intfrfbdf SnmpPduFbdtory {

    /**
     * Dfdodfs tif spfdififd <CODE>SnmpMsg</CODE> bnd rfturns tif
     * rfsulting <CODE>SnmpPdu</CODE>. If tiis mftiod rfturns
     * <CODE>null</CODE>, tif mfssbgf will bf donsidfrfd unsbff
     * bnd will bf droppfd.
     *
     * @pbrbm msg Tif <CODE>SnmpMsg</CODE> to bf dfdodfd.
     * @rfturn Null or b fully initiblizfd <CODE>SnmpPdu</CODE>.
     * @fxdfption SnmpStbtusExdfption If tif fndoding is invblid.
     *
     * @sindf 1.5
     */
    publid SnmpPdu dfdodfSnmpPdu(SnmpMsg msg) tirows SnmpStbtusExdfption ;

    /**
     * Endodfs tif spfdififd <CODE>SnmpPdu</CODE> bnd
     * rfturns tif rfsulting <CODE>SnmpMsg</CODE>. If tiis
     * mftiod rfturns null, tif spfdififd <CODE>SnmpPdu</CODE>
     * will bf droppfd bnd tif durrfnt SNMP rfqufst will bf
     * bbortfd.
     *
     * @pbrbm p Tif <CODE>SnmpPdu</CODE> to bf fndodfd.
     * @pbrbm mbxDbtbLfngti Tif sizf limit of tif rfsulting fndoding.
     * @rfturn Null or b fully fndodfd <CODE>SnmpMsg</CODE>.
     * @fxdfption SnmpStbtusExdfption If <CODE>pdu</CODE> dontbins
     *            illfgbl vblufs bnd dbnnot bf fndodfd.
     * @fxdfption SnmpTooBigExdfption If tif rfsulting fndoding dofs not
     *            fit into <CODE>mbxPktSizf</CODE> bytfs.
     *
     * @sindf 1.5
     */
    publid SnmpMsg fndodfSnmpPdu(SnmpPdu p, int mbxDbtbLfngti)
        tirows SnmpStbtusExdfption, SnmpTooBigExdfption ;
}
