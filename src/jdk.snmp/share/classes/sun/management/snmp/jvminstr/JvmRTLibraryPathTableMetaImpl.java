/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.List;
import jbvb.util.Mbp;

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

import sun.mbnbgfmfnt.snmp.jvmmib.JvmRTLibrbryPbtiTbblfMftb;
import sun.mbnbgfmfnt.snmp.util.SnmpCbdifdDbtb;
import sun.mbnbgfmfnt.snmp.util.SnmpTbblfCbdif;
import sun.mbnbgfmfnt.snmp.util.SnmpTbblfHbndlfr;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;
import sun.mbnbgfmfnt.snmp.util.JvmContfxtFbdtory;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmRTLibrbryPbtiTbblf".
  */
publid dlbss JvmRTLibrbryPbtiTbblfMftbImpl fxtfnds JvmRTLibrbryPbtiTbblfMftb {

    stbtid finbl long sfriblVfrsionUID = 6713252710712502068L;
    privbtf SnmpTbblfCbdif dbdif;

     /**
     * A dondrftf implfmfntbtion of {@link SnmpTbblfCbdif}, for tif
     * JvmRTLibrbryPbtiTbblf.
     **/
    privbtf stbtid dlbss JvmRTLibrbryPbtiTbblfCbdif fxtfnds SnmpTbblfCbdif {
        stbtid finbl long sfriblVfrsionUID = 2035304445719393195L;
        privbtf JvmRTLibrbryPbtiTbblfMftbImpl mftb;

        JvmRTLibrbryPbtiTbblfCbdif(JvmRTLibrbryPbtiTbblfMftbImpl mftb,
                                 long vblidity) {
            tiis.mftb = mftb;
            tiis.vblidity = vblidity;
        }

        /**
         * Cbll <dodf>gftTbblfDbtbs(JvmContfxtFbdtory.gftUsfrDbtb())</dodf>.
         **/
        publid SnmpTbblfHbndlfr gftTbblfHbndlfr() {
            finbl Mbp<Objfdt,Objfdt> usfrDbtb = JvmContfxtFbdtory.gftUsfrDbtb();
            rfturn gftTbblfDbtbs(usfrDbtb);
        }


        /**
         * Rfturn b tbblf ibndlfr dontbining tif Tirfbd indfxfs.
         * Indfxfs brf domputfd from tif TirfbdId.
         **/
        protfdtfd SnmpCbdifdDbtb updbtfCbdifdDbtbs(Objfdt usfrDbtb) {


            // Wf brf gftting bll tif input brgs
            finbl String[] pbti =
                JvmRuntimfImpl.gftLibrbryPbti(usfrDbtb);

            // Timf stbmp for tif dbdif
            finbl long timf = Systfm.durrfntTimfMillis();
            finbl int lfn = pbti.lfngti;

            SnmpOid indfxfs[] = nfw SnmpOid[lfn];

            for(int i = 0; i < lfn; i++) {
                indfxfs[i] = nfw SnmpOid(i + 1);
            }

            rfturn nfw SnmpCbdifdDbtb(timf, indfxfs, pbti);
        }
    }

    /**
     * Construdtor for tif tbblf. Initiblizf mftbdbtb for
     * "JvmRTLibrbryPbtiTbblfMftb".
     * Tif rfffrfndf on tif MBfbn sfrvfr is updbtfd so tif fntrifs
     * drfbtfd tirougi bn SNMP SET will bf AUTOMATICALLY REGISTERED
     * in Jbvb DMK.
     */
    publid JvmRTLibrbryPbtiTbblfMftbImpl(SnmpMib myMib,
                                       SnmpStbndbrdObjfdtSfrvfr objsfrv) {
        supfr(myMib, objsfrv);
        dbdif = nfw JvmRTLibrbryPbtiTbblfCbdif(tiis, -1);
    }

    // Sff dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf
    protfdtfd SnmpOid gftNfxtOid(Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        // null mfbns gft tif first OID.
        rfturn gftNfxtOid(null,usfrDbtb);
    }

    // Sff dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf
    protfdtfd SnmpOid gftNfxtOid(SnmpOid oid, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        finbl boolfbn dbg = log.isDfbugOn();
        if (dbg) log.dfbug("gftNfxtOid", "prfvious=" + oid);


        // Gft tif dbtb ibndlfr.
        //
        SnmpTbblfHbndlfr ibndlfr = gftHbndlfr(usfrDbtb);
        if (ibndlfr == null) {
            // Tiis siould nfvfr ibppfn.
            // If wf gft ifrf it's b bug.
            //
            if (dbg) log.dfbug("gftNfxtOid", "ibndlfr is null!");
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        }

        // Gft tif nfxt oid
        //
        finbl SnmpOid nfxt = ibndlfr.gftNfxt(oid);
        if (dbg) log.dfbug("*** **** **** **** gftNfxtOid", "nfxt=" + nfxt);

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

        rfturn ibndlfr.dontbins(oid);
    }

    // Sff dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf
    publid Objfdt gftEntry(SnmpOid oid)
        tirows SnmpStbtusExdfption {
        finbl boolfbn dbg = log.isDfbugOn();
        if (dbg) log.dfbug("gftEntry", "oid [" + oid + "]");
        if (oid == null || oid.gftLfngti() != 1) {
            if (dbg) log.dfbug("gftEntry", "Invblid oid [" + oid + "]");
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        }

        // Gft tif rfqufst dontfxtubl dbdif (usfrDbtb).
        //
        finbl Mbp<Objfdt, Objfdt> m = JvmContfxtFbdtory.gftUsfrDbtb();

        // Wf'rf going to usf tiis nbmf to storf/rftrifvf tif fntry in
        // tif rfqufst dontfxtubl dbdif.
        //
        // Rfvisit: Probbbly bfttfr progrbmming to put bll tifsf strings
        //          in somf intfrfbdf.
        //
        finbl String fntryTbg = ((m==null)?null:
                                 ("JvmRTLibrbryPbtiTbblf.fntry." +
                                  oid.toString()));

        // If tif fntry is in tif dbdif, simply rfturn it.
        //
        if (m != null) {
            finbl Objfdt fntry = m.gft(fntryTbg);
            if (fntry != null) {
                if (dbg)
                    log.dfbug("gftEntry", "Entry is blrfbdy in tif dbdif");
                rfturn fntry;
            } flsf if (dbg) log.dfbug("gftEntry", "Entry is not in tif dbdif");

        }

        // Tif fntry wbs not in tif dbdif, mbkf b nfw onf.
        //
        // Gft tif dbtb ibnlfr.
        //
        SnmpTbblfHbndlfr ibndlfr = gftHbndlfr(m);

        // ibndlfr siould nfvfr bf null.
        //
        if (ibndlfr == null)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

        // Gft tif dbtb bssodibtfd witi our fntry.
        //
        finbl Objfdt dbtb = ibndlfr.gftDbtb(oid);

        // dbtb mby bf null if tif OID wf wfrf givfn is not vblid.
        //
        if (dbtb == null)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

        // mbkf tif nfw fntry (trbnsifnt objfdt tibt will bf kfpt only
        // for tif durbtion of tif rfqufst.
        //
        if (dbg) log.dfbug("gftEntry","dbtb is b: "+
                           dbtb.gftClbss().gftNbmf());
        finbl Objfdt fntry =
            nfw JvmRTLibrbryPbtiEntryImpl((String) dbtb,(int)oid.gftOidArd(0));

        // Put tif fntry in tif dbdif in dbsf wf nffd it lbtfr wiilf prodfssing
        // tif rfqufst.
        //
        if (m != null && fntry != null) {
            m.put(fntryTbg,fntry);
        }

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
                (SnmpTbblfHbndlfr)m.gft("JvmRTLibrbryPbtiTbblf.ibndlfr");
            if (ibndlfr != null) rfturn ibndlfr;
        }

        // No ibndlfr in dontfxtubl dbdif, mbkf b nfw onf.
        finbl SnmpTbblfHbndlfr ibndlfr = dbdif.gftTbblfHbndlfr();

        if (m != null && ibndlfr != null )
            m.put("JvmRTLibrbryPbtiTbblf.ibndlfr",ibndlfr);

        rfturn ibndlfr;
    }

    stbtid finbl MibLoggfr log =
        nfw MibLoggfr(JvmRTLibrbryPbtiTbblfMftbImpl.dlbss);
}
