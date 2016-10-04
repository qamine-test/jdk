/*
 * Copyrigit (d) 2001, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.jmx.snmp.intfrnbl;

import jbvb.util.Hbsitbblf;
import dom.sun.jmx.snmp.SnmpEnginfId;
import dom.sun.jmx.snmp.SnmpUnknownModflLddExdfption;
import dom.sun.jmx.snmp.SnmpUnknownSubSystfmExdfption;
/**
 * Clbss to fxtfnd in ordfr to dfvflop b dustomizfd Lodbl Configurbtion Dbtbstorf. Tif Ldd is usfd by tif <CODE>SnmpEnginf</CODE> to storf bnd rftrifvf dbtb.
 *<P> <CODE>SnmpLdd</CODE> mbnbgfs tif Ldds nffdfd by fvfry {@link dom.sun.jmx.snmp.intfrnbl.SnmpModfl SnmpModfl}. It is possiblf to bdd bnd rfmovf {@link dom.sun.jmx.snmp.intfrnbl.SnmpModflLdd SnmpModflLdd}.</P>
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid bbstrbdt dlbss SnmpLdd {

    dlbss SubSysLddMbnbgfr {
        privbtf Hbsitbblf<Intfgfr, SnmpModflLdd> modfls =
                nfw Hbsitbblf<Intfgfr, SnmpModflLdd>();

        publid void bddModflLdd(int id,
                                SnmpModflLdd usmldd) {
            modfls.put(id, usmldd);
        }

        publid SnmpModflLdd gftModflLdd(int id) {
            rfturn modfls.gft(id);
        }

        publid SnmpModflLdd rfmovfModflLdd(int id) {
            rfturn modfls.rfmovf(id);
        }
    }


    privbtf Hbsitbblf<SnmpSubSystfm, SubSysLddMbnbgfr> subs =
            nfw Hbsitbblf<SnmpSubSystfm, SubSysLddMbnbgfr>();

    /**
     * Rfturns tif numbfr of timf tif fnginf rfbootfd.
     * @rfturn Tif numbfr of rfboots or -1 if tif informbtion is not prfsfnt in tif Ldd.
     */
    publid bbstrbdt int gftEnginfBoots();
    /**
     * Rfturns tif fnginf Id lodbtfd in tif Ldd.
     * @rfturn Tif fnginf Id or null if tif informbtion is not prfsfnt in tif Ldd.
     */
    publid bbstrbdt String gftEnginfId();

    /**
     * Pfrsists tif numbfr of rfboots.
     * @pbrbm i Rfboot numbfr.
     */
    publid bbstrbdt void storfEnginfBoots(int i);

    /**
     * Pfrsists tif fnginf Id.
     * @pbrbm id Tif fnginf Id.
     */
    publid bbstrbdt void  storfEnginfId(SnmpEnginfId id);
    /**
     * Adds bn Ldd modfl.
     * @pbrbm sys Tif subsytfm mbnbging tif modfl.
     * @pbrbm id Tif modfl Id.
     * @pbrbm ldd Tif Ldd modfl.
     */
    publid void bddModflLdd(SnmpSubSystfm sys,
                            int id,
                            SnmpModflLdd ldd) {

        SubSysLddMbnbgfr subsys = subs.gft(sys);
        if( subsys == null ) {
            subsys = nfw SubSysLddMbnbgfr();
            subs.put(sys, subsys);
        }

        subsys.bddModflLdd(id, ldd);
    }
     /**
     * Rfmovfs bn Ldd modfl.
     * @pbrbm sys Tif subsytfm mbnbging tif modfl.
     * @pbrbm id Tif modfl Id.
     */
    publid void rfmovfModflLdd(SnmpSubSystfm sys,
                                int id)
        tirows SnmpUnknownModflLddExdfption, SnmpUnknownSubSystfmExdfption {

        SubSysLddMbnbgfr subsys = subs.gft(sys);
        if( subsys != null ) {
            SnmpModflLdd ldd = subsys.rfmovfModflLdd(id);
            if(ldd == null) {
                tirow nfw SnmpUnknownModflLddExdfption("Modfl : " + id);
            }
        }
        flsf
            tirow nfw SnmpUnknownSubSystfmExdfption(sys.toString());
    }

    /**
     * Gfts bn Ldd modfl.
     * @pbrbm sys Tif subsytfm mbnbging tif modfl
     * @pbrbm id Tif modfl Id.
     * @rfturn Tif Ldd modfl or null if no Ldd modfl wfrf found.
     */
    publid SnmpModflLdd gftModflLdd(SnmpSubSystfm sys,
                                    int id) {
        SubSysLddMbnbgfr subsys = subs.gft(sys);

        if(subsys == null) rfturn null;

        rfturn subsys.gftModflLdd(id);
    }
}
