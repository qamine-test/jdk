/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.Sfriblizbblf;
import jbvb.util.Enumfrbtion;
import jbvb.util.logging.Lfvfl;
import jbvb.util.Vfdtor;

import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MblformfdObjfdtNbmfExdfption;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtionExdfption;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_ADAPTOR_LOGGER;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpEnginf;
import dom.sun.jmx.snmp.SnmpUnknownModflExdfption;
import dom.sun.jmx.snmp.intfrnbl.SnmpAddfssControlModfl;
import dom.sun.jmx.snmp.intfrnbl.SnmpEnginfImpl;

/**
 * Oid Cifdkfr mbkfs usf of ACM to difdk fbdi OID during tif gftnfxt prodfss.
 */
dlbss AdmCifdkfr {


    SnmpAddfssControlModfl modfl = null;
    String prindipbl = null;
    int sfdurityLfvfl = -1;
    int vfrsion = -1;
    int pduTypf = -1;
    int sfdurityModfl = -1;
    bytf[] dontfxtNbmf = null;
    SnmpEnginfImpl fnginf = null;
    LongList l = null;
    AdmCifdkfr(SnmpMibRfqufst rfq) {
        fnginf = (SnmpEnginfImpl) rfq.gftEnginf();
        //Wf brf in V3 brdiitfdturf, ACM is in tif pidturf.
        if(fnginf != null) {
            if(fnginf.isCifdkOidAdtivbtfd()) {
                try {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMib.dlbss.gftNbmf(),
                                "AdmCifdkfr(SnmpMibRfqufst)",
                                "SNMP V3 Addfss Control to bf donf");
                    }
                    modfl = (SnmpAddfssControlModfl)
                        fnginf.gftAddfssControlSubSystfm().
                        gftModfl(SnmpDffinitions.snmpVfrsionTirff);
                    prindipbl = rfq.gftPrindipbl();
                    sfdurityLfvfl = rfq.gftSfdurityLfvfl();
                    pduTypf = rfq.gftPdu().typf;
                    vfrsion = rfq.gftRfqufstPduVfrsion();
                    sfdurityModfl = rfq.gftSfdurityModfl();
                    dontfxtNbmf = rfq.gftAddfssContfxtNbmf();
                    l = nfw LongList();
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        finbl StringBuildfr strb = nfw StringBuildfr()
                        .bppfnd("Will difdk oid for : prindipbl : ")
                        .bppfnd(prindipbl)
                        .bppfnd("; sfdurityLfvfl : ").bppfnd(sfdurityLfvfl)
                        .bppfnd("; pduTypf : ").bppfnd(pduTypf)
                        .bppfnd("; vfrsion : ").bppfnd(vfrsion)
                        .bppfnd("; sfdurityModfl : ").bppfnd(sfdurityModfl)
                        .bppfnd("; dontfxtNbmf : ").bppfnd(dontfxtNbmf);
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMib.dlbss.gftNbmf(),
                                "AdmCifdkfr(SnmpMibRfqufst)", strb.toString());
                    }

                }dbtdi(SnmpUnknownModflExdfption f) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                                SnmpMib.dlbss.gftNbmf(),
                                "AdmCifdkfr(SnmpMibRfqufst)",
                                "Unknown Modfl, no ACM difdk.");
                    }
                }
            }
        }
    }

    void bdd(int indfx, long brd) {
        if(modfl != null)
            l.bdd(indfx, brd);
    }

    void rfmovf(int indfx) {
        if(modfl != null)
            l.rfmovf(indfx);
    }

    void bdd(finbl int bt,finbl long[] srd, finbl int from,
             finbl int dount) {
        if(modfl != null)
            l.bdd(bt,srd,from,dount);
    }

    void rfmovf(finbl int from, finbl int dount) {
        if(modfl != null)
            l.rfmovf(from,dount);
    }

    void difdkCurrfntOid() tirows SnmpStbtusExdfption {
        if(modfl != null) {
            SnmpOid oid = nfw SnmpOid(l.toArrby());
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpMib.dlbss.gftNbmf(),
                        "difdkCurrfntOid", "Cifdking bddfss for : " + oid);
            }
            modfl.difdkAddfss(vfrsion,
                              prindipbl,
                              sfdurityLfvfl,
                              pduTypf,
                              sfdurityModfl,
                              dontfxtNbmf,
                              oid);
        }
    }

}
