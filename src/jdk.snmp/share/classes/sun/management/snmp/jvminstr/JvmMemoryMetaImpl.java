/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.mbnbgfmfnt.snmp.jvminstr;

// jbvb imports
//
import jbvb.io.Sfriblizbblf;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMib;
import dom.sun.jmx.snmp.bgfnt.SnmpStbndbrdObjfdtSfrvfr;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmoryMftb;
import sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmMbnbgfrTbblfMftb;
import sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmGCTbblfMftb;
import sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmPoolTbblfMftb;
import sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmMgrPoolRflTbblfMftb;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;

/**
 * Tif dlbss is usfd for rfprfsfnting SNMP mftbdbtb for tif "JvmMfmory" group.
 */
publid dlbss JvmMfmoryMftbImpl fxtfnds JvmMfmoryMftb {

    stbtid finbl long sfriblVfrsionUID = -6500448253825893071L;
    /**
     * Construdtor for tif mftbdbtb bssodibtfd to "JvmMfmory".
     */
    publid JvmMfmoryMftbImpl(SnmpMib myMib, SnmpStbndbrdObjfdtSfrvfr objsfrv) {
        supfr(myMib,objsfrv);
    }

    /**
     * Fbdtory mftiod for "JvmMfmMbnbgfrTbblf" tbblf mftbdbtb dlbss.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd mftbdbtb dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm tbblfNbmf Nbmf of tif tbblf objfdt ("JvmMfmMbnbgfrTbblf")
     * @pbrbm groupNbmf Nbmf of tif group to wiidi tiis tbblf bflong
     *        ("JvmMfmory")
     * @pbrbm mib Tif SnmpMib objfdt in wiidi tiis tbblf is rfgistfrfd
     * @pbrbm sfrvfr MBfbnSfrvfr for tiis tbblf fntrifs (mby bf null)
     *
     * @rfturn An instbndf of tif mftbdbtb dlbss gfnfrbtfd for tif
     *         "JvmMfmMbnbgfrTbblf" tbblf (JvmMfmMbnbgfrTbblfMftb)
     *
     **/
    protfdtfd JvmMfmMbnbgfrTbblfMftb drfbtfJvmMfmMbnbgfrTbblfMftbNodf(
        String tbblfNbmf, String groupNbmf, SnmpMib mib, MBfbnSfrvfr sfrvfr)  {
        rfturn nfw JvmMfmMbnbgfrTbblfMftbImpl(mib, objfdtsfrvfr);
    }


    /**
     * Fbdtory mftiod for "JvmMfmGCTbblf" tbblf mftbdbtb dlbss.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd mftbdbtb dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm tbblfNbmf Nbmf of tif tbblf objfdt ("JvmMfmGCTbblf")
     * @pbrbm groupNbmf Nbmf of tif group to wiidi tiis tbblf bflong
     *        ("JvmMfmory")
     * @pbrbm mib Tif SnmpMib objfdt in wiidi tiis tbblf is rfgistfrfd
     * @pbrbm sfrvfr MBfbnSfrvfr for tiis tbblf fntrifs (mby bf null)
     *
     * @rfturn An instbndf of tif mftbdbtb dlbss gfnfrbtfd for tif
     *         "JvmMfmGCTbblf" tbblf (JvmMfmGCTbblfMftb)
     *
     **/
    protfdtfd JvmMfmGCTbblfMftb drfbtfJvmMfmGCTbblfMftbNodf(String tbblfNbmf,
                      String groupNbmf, SnmpMib mib, MBfbnSfrvfr sfrvfr)  {
        rfturn nfw JvmMfmGCTbblfMftbImpl(mib, objfdtsfrvfr);
    }


    /**
     * Fbdtory mftiod for "JvmMfmPoolTbblf" tbblf mftbdbtb dlbss.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd mftbdbtb dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm tbblfNbmf Nbmf of tif tbblf objfdt ("JvmMfmPoolTbblf")
     * @pbrbm groupNbmf Nbmf of tif group to wiidi tiis tbblf bflong
     *        ("JvmMfmory")
     * @pbrbm mib Tif SnmpMib objfdt in wiidi tiis tbblf is rfgistfrfd
     * @pbrbm sfrvfr MBfbnSfrvfr for tiis tbblf fntrifs (mby bf null)
     *
     * @rfturn An instbndf of tif mftbdbtb dlbss gfnfrbtfd for tif
     *         "JvmMfmPoolTbblf" tbblf (JvmMfmPoolTbblfMftb)
     *
     **/
    protfdtfd JvmMfmPoolTbblfMftb
        drfbtfJvmMfmPoolTbblfMftbNodf(String tbblfNbmf, String groupNbmf,
                                      SnmpMib mib, MBfbnSfrvfr sfrvfr)  {
        rfturn nfw JvmMfmPoolTbblfMftbImpl(mib, objfdtsfrvfr);
    }

    /**
     * Fbdtory mftiod for "JvmMfmMgrPoolRflTbblf" tbblf mftbdbtb dlbss.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd mftbdbtb dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm tbblfNbmf Nbmf of tif tbblf objfdt ("JvmMfmMgrPoolRflTbblf")
     * @pbrbm groupNbmf Nbmf of tif group to wiidi tiis tbblf bflong
     *        ("JvmMfmory")
     * @pbrbm mib Tif SnmpMib objfdt in wiidi tiis tbblf is rfgistfrfd
     * @pbrbm sfrvfr MBfbnSfrvfr for tiis tbblf fntrifs (mby bf null)
     *
     * @rfturn An instbndf of tif mftbdbtb dlbss gfnfrbtfd for tif
     *         "JvmMfmMgrPoolRflTbblf" tbblf (JvmMfmMgrPoolRflTbblfMftb)
     *
     **/
    protfdtfd JvmMfmMgrPoolRflTbblfMftb
        drfbtfJvmMfmMgrPoolRflTbblfMftbNodf(String tbblfNbmf,
                                            String groupNbmf,
                                            SnmpMib mib, MBfbnSfrvfr sfrvfr) {
        rfturn nfw JvmMfmMgrPoolRflTbblfMftbImpl(mib, objfdtsfrvfr);
    }

}
