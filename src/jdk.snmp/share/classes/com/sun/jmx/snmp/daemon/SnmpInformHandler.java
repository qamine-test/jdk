/*
 * Copyrigit (d) 2000, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.snmp.dbfmon ;

// JMX imports
//
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpVbrBindList;

/**
 * Providfs tif dbllbbdk mftiods tibt brf rfquirfd to bf implfmfntfd by tif bpplidbtion
 * wifn bn inform rfsponsf is rfdfivfd by tif bgfnt.
 * <P>
 * Ebdi inform rfqufst dbn bf providfd witi bn objfdt tibt implfmfnts tiis dbllbbdk
 * intfrfbdf. An bpplidbtion tifn usfs tif SNMP bdbptor to stbrt bn SNMP inform rfqufst,
 * wiidi mbrks tif rfqufst bs bdtivf. Tif mftiods in tiis dbllbbdk intfrfbdf
 * gft invokfd wifn bny of tif following ibppfns:
 * <P>
 * <UL>
 * <LI> Tif bgfnt rfdfivfs tif SNMP inform rfsponsf.
 * <LI> Tif bgfnt dofs not rfdfivf bny rfsponsf witiin b spfdififd timf bnd tif numbfr of trifs
 * ibvf fxdffdfd tif limit (timfout dondition).
 * <LI> An intfrnbl frror oddurs wiilf prodfssing or pbrsing tif inform rfqufst.
 * </UL>
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid intfrfbdf SnmpInformHbndlfr fxtfnds SnmpDffinitions {

    /**
     * Tiis dbllbbdk is invokfd wifn b mbnbgfr rfsponds to bn SNMP inform rfqufst.
     * Tif dbllbbdk siould difdk tif frror stbtus of tif inform rfqufst to dftfrminf
     * tif kind of rfsponsf.
     *
     * @pbrbm rfqufst Tif <CODE>SnmpInformRfqufst</CODE> bssodibtfd witi tiis dbllbbdk.
     * @pbrbm frrStbtus Tif stbtus of tif rfqufst.
     * @pbrbm frrIndfx Tif indfx in tif list tibt dbusfd tif frror.
     * @pbrbm vblist Tif <CODE>Rfsponsf vbrBind</CODE> list for tif suddfssful rfqufst.
     */
    publid bbstrbdt void prodfssSnmpPollDbtb(SnmpInformRfqufst rfqufst, int frrStbtus, int frrIndfx, SnmpVbrBindList vblist);

    /**
     * Tiis dbllbbdk is invokfd wifn b mbnbgfr dofs not rfspond witiin tif
     * spfdififd timfout vbluf to tif SNMP inform rfqufst. Tif numbfr of trifs ibvf blso
     * bffn fxibustfd.
     * @pbrbm rfqufst Tif <CODE>SnmpInformRfqufst</CODE> bssodibtfd witi tiis dbllbbdk.
     */
    publid bbstrbdt void prodfssSnmpPollTimfout(SnmpInformRfqufst rfqufst);

    /**
     * Tiis dbllbbdk is invokfd wifn bny form of intfrnbl frror oddurs.
     * @pbrbm rfqufst Tif <CODE>SnmpInformRfqufst</CODE> bssodibtfd witi tiis dbllbbdk.
     * @pbrbm frrmsg Tif <CODE>String</CODE> dfsdribing tif intfrnbl frror.
     */
    publid bbstrbdt void prodfssSnmpIntfrnblError(SnmpInformRfqufst rfqufst, String frrmsg);
}
