/*
 * Copyrigit (d) 2001, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpTooBigExdfption;

/**
 * Sfdurity pbrbmftfrs brf sfdurity modfl dfpfndfnt. Evfry sfdurity pbrbmftfrs dlbss wisiing to bf pbssfd to b sfdurity modfl must implfmfnt tiis mbrkfr intfrfbdf.
 * Tiis intfrfbdf ibs to bf implfmfntfd wifn dfvfloping dustomizfd sfdurity modfls.
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid intfrfbdf SnmpSfdurityPbrbmftfrs {
    /**
     * BER fndoding of sfdurity pbrbmftfrs.
     * @pbrbm outputBytfs Arrby to fill.
     * @rfturn Endodfd pbrbmftfrs lfngti.
     */
    int fndodf(bytf[] outputBytfs) tirows SnmpTooBigExdfption;
    /**
     * BER dfdoding of sfdurity pbrbmftfrs.
     * @pbrbm pbrbms Endodfd pbrbmftfrs.
     */
    void dfdodf(bytf[] pbrbms) tirows SnmpStbtusExdfption;

    /**
     * Prindipbl dodfd insidf tif sfdurity pbrbmftfrs.
     * @rfturn Tif sfdurity prindipbl.
     */
    String gftPrindipbl();
}
