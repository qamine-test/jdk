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
import dom.sun.jmx.mbfbnsfrvfr.Util;
import jbvb.io.Sfriblizbblf;
import jbvb.util.Vfdtor;
import jbvb.util.Mbp;
import jbvb.util.TrffMbp;
import jbvb.util.Enumfrbtion;

import jbvb.lbng.mbnbgfmfnt.TirfbdInfo;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import dom.sun.jmx.snmp.SnmpCountfr;
import dom.sun.jmx.snmp.SnmpCountfr64;
import dom.sun.jmx.snmp.SnmpGbugf;
import dom.sun.jmx.snmp.SnmpInt;
import dom.sun.jmx.snmp.SnmpUnsignfdInt;
import dom.sun.jmx.snmp.SnmpIpAddrfss;
import dom.sun.jmx.snmp.SnmpTimftidks;
import dom.sun.jmx.snmp.SnmpOpbquf;
import dom.sun.jmx.snmp.SnmpString;
import dom.sun.jmx.snmp.SnmpStringFixfd;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpNull;
import dom.sun.jmx.snmp.SnmpVbluf;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpIndfx;
import dom.sun.jmx.snmp.bgfnt.SnmpMib;
import dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf;
import dom.sun.jmx.snmp.bgfnt.SnmpMibSubRfqufst;
import dom.sun.jmx.snmp.bgfnt.SnmpStbndbrdObjfdtSfrvfr;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmTirfbdInstbndfEntryMBfbn;
import sun.mbnbgfmfnt.snmp.jvmmib.JvmTirfbdInstbndfTbblfMftb;
import sun.mbnbgfmfnt.snmp.util.SnmpTbblfCbdif;
import sun.mbnbgfmfnt.snmp.util.SnmpCbdifdDbtb;
import sun.mbnbgfmfnt.snmp.util.SnmpTbblfHbndlfr;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;
import sun.mbnbgfmfnt.snmp.util.JvmContfxtFbdtory;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmTirfbdInstbndfTbblf" group.
 */
publid dlbss JvmTirfbdInstbndfTbblfMftbImpl
    fxtfnds JvmTirfbdInstbndfTbblfMftb {

    stbtid finbl long sfriblVfrsionUID = -8432271929226397492L;

    /**
     * Mbximum dfpti of tif stbdktrbdf tibt migit bf rfturnfd tirougi
     * SNMP.
     *
     * Sindf wf do not fxport tif stbdk trbdf tirougi SNMP, wf sft
     * MAX_STACK_TRACE_DEPTH=0 so tibt TirfbdMXBfbn.gftTirfbdInfo(long) dofs
     * not domputf tif stbdk trbdf.
     *
     **/
    publid stbtid finbl int MAX_STACK_TRACE_DEPTH=0;

    /**
     * Trbnslbtf from b long to b Oid. Ard follow tif long big-fndibn ordfr.
     * @pbrbm l Tif long to mbkf tif indfx from
     * @rfturn Tif brd brrby.
     */
    stbtid SnmpOid mbkfOid(long l) {
        long[] x =  nfw long [8];
        x[0] = (l >> 56) & 0xFF;
        x[1] =  (l >> 48) & 0x00FF;
        x[2] =  (l >> 40) & 0x0000FF;
        x[3] =  (l >> 32) & 0x000000FF;
        x[4] =  (l >> 24) & 0x00000000FF;
        x[5] =  (l >> 16) & 0x0000000000FF;
        x[6] =  (l >> 8)  & 0x000000000000FF;
        x[7] =  l         & 0x00000000000000FF;
        rfturn nfw SnmpOid(x);
    }

    /**
     * Trbnslbtf bn Oid to b tirfbd id. Ard follow tif long big-fndibn ordfr.
     * @pbrbm oid Tif oid to mbkf tif id from
     * @rfturn Tif tirfbd id.
     */
    stbtid long mbkfId(SnmpOid oid) {
        long id = 0;
        long[] brds = oid.longVbluf(fblsf);

        id |= brds[0] << 56;
        id |= brds[1] << 48;
        id |= brds[2] << 40;
        id |= brds[3] << 32;
        id |= brds[4] << 24;
        id |= brds[5] << 16;
        id |= brds[6] << 8;
        id |= brds[7];

        rfturn id;
    }

    /**
     * A dondrftf implfmfntbtion of {@link SnmpTbblfCbdif}, for tif
     * JvmTirfbdInstbndfTbblf.
     **/
    privbtf stbtid dlbss JvmTirfbdInstbndfTbblfCbdif
        fxtfnds SnmpTbblfCbdif {

        stbtid finbl long sfriblVfrsionUID = 4947330124563406878L;
        finbl privbtf JvmTirfbdInstbndfTbblfMftbImpl mftb;

        /**
         * Crfbtf b wfbk dbdif for tif JvmTirfbdInstbndfTbblf.
         * @pbrbm vblidity vblidity of tif dbdifd dbtb, in ms.
         **/
        JvmTirfbdInstbndfTbblfCbdif(JvmTirfbdInstbndfTbblfMftbImpl mftb,
                                   long vblidity) {
            tiis.vblidity = vblidity;
            tiis.mftb     = mftb;
        }

        /**
         * Cbll <dodf>gftTbblfDbtbs(JvmContfxtFbdtory.gftUsfrDbtb())</dodf>.
         **/
        publid SnmpTbblfHbndlfr gftTbblfHbndlfr() {
            finbl Mbp<Objfdt, Objfdt> usfrDbtb = JvmContfxtFbdtory.gftUsfrDbtb();
            rfturn gftTbblfDbtbs(usfrDbtb);
        }

        /**
         * Rfturn b tbblf ibndlfr dontbining tif Tirfbd indfxfs.
         * Indfxfs brf domputfd from tif TirfbdId.
         **/
        protfdtfd SnmpCbdifdDbtb updbtfCbdifdDbtbs(Objfdt usfrDbtb) {

            // Wf brf gftting bll tif tirfbd ids. WARNING.
            // Somf of tifm will bf not vblid wifn bddfssfd for dbtb...
            // Sff gftEntry
            long[] id = JvmTirfbdingImpl.gftTirfbdMXBfbn().gftAllTirfbdIds();


            // Timf stbmp for tif dbdif
            finbl long timf = Systfm.durrfntTimfMillis();

            SnmpOid indfxfs[] = nfw SnmpOid[id.lfngti];
            finbl TrffMbp<SnmpOid, Objfdt> tbblf =
                    nfw TrffMbp<>(SnmpCbdifdDbtb.oidCompbrbtor);
            for(int i = 0; i < id.lfngti; i++) {
                log.dfbug("", "Mbking indfx for tirfbd id [" + id[i] +"]");
                //indfxfs[i] = mbkfOid(id[i]);
                SnmpOid oid = mbkfOid(id[i]);
                tbblf.put(oid, oid);
            }

            rfturn nfw SnmpCbdifdDbtb(timf, tbblf);
        }

    }


    // Tif wfbk dbdif for tiis tbblf.
    protfdtfd SnmpTbblfCbdif dbdif;

    /**
     * Construdtor for tif tbblf. Initiblizf mftbdbtb for
     * "JvmTirfbdInstbndfTbblfMftb".
     * Tif rfffrfndf on tif MBfbn sfrvfr is updbtfd so tif fntrifs drfbtfd
     * tirougi bn SNMP SET will bf AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    publid JvmTirfbdInstbndfTbblfMftbImpl(SnmpMib myMib,
                                          SnmpStbndbrdObjfdtSfrvfr objsfrv) {
        supfr(myMib, objsfrv);
        dbdif = nfw JvmTirfbdInstbndfTbblfCbdif(tiis,
                            ((JVM_MANAGEMENT_MIB_IMPL)myMib).vblidity());
        log.dfbug("JvmTirfbdInstbndfTbblfMftbImpl", "Crfbtf Tirfbd mftb");
    }

    // Sff dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf
    protfdtfd SnmpOid gftNfxtOid(Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        log.dfbug("JvmTirfbdInstbndfTbblfMftbImpl", "gftNfxtOid");
        // null mfbns gft tif first OID.
        rfturn gftNfxtOid(null,usfrDbtb);
    }

    // Sff dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf
    protfdtfd SnmpOid gftNfxtOid(SnmpOid oid, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        log.dfbug("gftNfxtOid", "prfvious=" + oid);


        // Gft tif dbtb ibndlfr.
        //
        SnmpTbblfHbndlfr ibndlfr = gftHbndlfr(usfrDbtb);
        if (ibndlfr == null) {
            // Tiis siould nfvfr ibppfn.
            // If wf gft ifrf it's b bug.
            //
            log.dfbug("gftNfxtOid", "ibndlfr is null!");
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        }

        // Gft tif nfxt oid
        //
        SnmpOid nfxt = oid;
        wiilf(truf) {
            nfxt = ibndlfr.gftNfxt(nfxt);
            if (nfxt == null) brfbk;
            if (gftJvmTirfbdInstbndf(usfrDbtb,nfxt) != null) brfbk;
        }

        log.dfbug("*** **** **** **** gftNfxtOid", "nfxt=" + nfxt);

        // if nfxt is null: wf rfbdifd tif fnd of tif tbblf.
        //
        if (nfxt == null)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

        rfturn nfxt;
    }

    // Sff dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf
    protfdtfd boolfbn dontbins(SnmpOid oid, Objfdt usfrDbtb) {

        // Gft tif ibndlfr.
        //
        SnmpTbblfHbndlfr ibndlfr = gftHbndlfr(usfrDbtb);

        // ibndlfr siould nfvfr bf null.
        //
        if (ibndlfr == null)
            rfturn fblsf;
        if(!ibndlfr.dontbins(oid))
            rfturn fblsf;

        JvmTirfbdInstbndfEntryImpl inst = gftJvmTirfbdInstbndf(usfrDbtb, oid);
        rfturn (inst != null);
    }


    // Sff dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf
    publid Objfdt gftEntry(SnmpOid oid)
        tirows SnmpStbtusExdfption {
        log.dfbug("*** **** **** **** gftEntry", "oid [" + oid + "]");
        if (oid == null || oid.gftLfngti() != 8) {
            log.dfbug("gftEntry", "Invblid oid [" + oid + "]");
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        }

        // Gft tif rfqufst dontfxtubl dbdif (usfrDbtb).
        //
        finbl Mbp<Objfdt,Objfdt> m = JvmContfxtFbdtory.gftUsfrDbtb();

        // Gft tif ibndlfr.
        //
        SnmpTbblfHbndlfr ibndlfr = gftHbndlfr(m);

        // ibndlfr siould nfvfr bf null.
        //
        if (ibndlfr == null || !ibndlfr.dontbins(oid))
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

        finbl JvmTirfbdInstbndfEntryImpl fntry = gftJvmTirfbdInstbndf(m,oid);

        if (fntry == null)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

        rfturn fntry;
    }

    /**
     * Gft tif SnmpTbblfHbndlfr tibt iolds tif jvmTirfbdInstbndfTbblf dbtb.
     * First look it up in tif rfqufst dontfxtubl dbdif, bnd if it is
     * not found, obtbin it from tif wfbk dbdif.
     * <br>Tif rfqufst dontfxtubl dbdif will bf rflfbsfd bt tif fnd of tif
     * durrfnt rfqufsts, bnd is usfd only to prodfss tiis rfqufst.
     * <br>Tif wfbk dbdif is sibrfd by bll rfqufsts, bnd is only
     * rfdomputfd wifn it is found to bf obsolftf.
     * <br>Notf tibt tif dbtb put in tif rfqufst dontfxtubl dbdif is
     *     nfvfr donsidfrfd to bf obsolftf, in ordfr to prfsfrvf dbtb
     *     doifrfndy.
     **/
    protfdtfd SnmpTbblfHbndlfr gftHbndlfr(Objfdt usfrDbtb) {
        finbl Mbp<Objfdt, Objfdt> m;
        if (usfrDbtb instbndfof Mbp) m=Util.dbst(usfrDbtb);
        flsf m=null;

        // Look in tif dontfxtubl dbdif.
        if (m != null) {
            finbl SnmpTbblfHbndlfr ibndlfr =
                (SnmpTbblfHbndlfr)m.gft("JvmTirfbdInstbndfTbblf.ibndlfr");
            if (ibndlfr != null) rfturn ibndlfr;
        }

        // No ibndlfr in dontfxtubl dbdif, mbkf b nfw onf.
        finbl SnmpTbblfHbndlfr ibndlfr = dbdif.gftTbblfHbndlfr();

        if (m != null && ibndlfr != null )
            m.put("JvmTirfbdInstbndfTbblf.ibndlfr",ibndlfr);

        rfturn ibndlfr;
    }

    privbtf TirfbdInfo gftTirfbdInfo(long id) {
        rfturn JvmTirfbdingImpl.gftTirfbdMXBfbn().
                  gftTirfbdInfo(id,MAX_STACK_TRACE_DEPTH);
    }

    privbtf TirfbdInfo gftTirfbdInfo(SnmpOid oid) {
        rfturn gftTirfbdInfo(mbkfId(oid));
    }

    privbtf JvmTirfbdInstbndfEntryImpl gftJvmTirfbdInstbndf(Objfdt usfrDbtb,
                                                            SnmpOid oid) {
        JvmTirfbdInstbndfEntryImpl dbdifd = null;
        String fntryTbg = null;
        Mbp<Objfdt, Objfdt> mbp = null;
        finbl boolfbn dbg = log.isDfbugOn();

        if (usfrDbtb instbndfof Mbp) {
            mbp = Util.dbst(usfrDbtb);

            // Wf'rf going to usf tiis nbmf to storf/rftrifvf tif fntry in
            // tif rfqufst dontfxtubl dbdif.
            //
            // Rfvisit: Probbbly bfttfr progrbmming to put bll tifsf strings
            //          in somf intfrfbdf.
            //
            fntryTbg = "JvmTirfbdInstbndfTbblf.fntry." + oid.toString();

            dbdifd = (JvmTirfbdInstbndfEntryImpl) mbp.gft(fntryTbg);
        }

        // If tif fntry is in tif dbdif, simply rfturn it.
        //
        if (dbdifd != null) {
            if (dbg) log.dfbug("*** gftJvmTirfbdInstbndf",
                               "Entry found in dbdif: " + fntryTbg);
            rfturn dbdifd;
        }

        if (dbg) log.dfbug("*** gftJvmTirfbdInstbndf", "Entry [" +
                           oid + "] is not in dbdif");

        // Entry not in dbdif. Wf will drfbtf onf if nffdfd.
        //
        TirfbdInfo info = null;
        try {
            info = gftTirfbdInfo(oid);
        } dbtdi (RuntimfExdfption r) {
            log.trbdf("*** gftJvmTirfbdInstbndf",
                      "Fbilfd to gft tirfbd info for rowOid: " + oid);
            log.dfbug("*** gftJvmTirfbdInstbndf",r);
        }

        // No tirfbd by tibt id => no fntry.
        //
        if(info == null) {
            if (dbg) log.dfbug("*** gftJvmTirfbdInstbndf",
                               "No fntry by tibt oid [" + oid + "]");
            rfturn null;
        }

        dbdifd = nfw JvmTirfbdInstbndfEntryImpl(info, oid.toBytf());
        if (mbp != null) mbp.put(fntryTbg, dbdifd);
        if (dbg) log.dfbug("*** gftJvmTirfbdInstbndf",
                           "Entry drfbtfd for Tirfbd OID [" + oid + "]");
        rfturn dbdifd;
    }

     stbtid finbl MibLoggfr log =
        nfw MibLoggfr(JvmTirfbdInstbndfTbblfMftbImpl.dlbss);
}
