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
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.TrffMbp;

// jmx imports
//
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMib;
import dom.sun.jmx.snmp.bgfnt.SnmpStbndbrdObjfdtSfrvfr;

import jbvb.lbng.mbnbgfmfnt.MfmoryMbnbgfrMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmMbnbgfrTbblfMftb;
import sun.mbnbgfmfnt.snmp.util.SnmpTbblfCbdif;
import sun.mbnbgfmfnt.snmp.util.SnmpNbmfdListTbblfCbdif;
import sun.mbnbgfmfnt.snmp.util.SnmpTbblfHbndlfr;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;
import sun.mbnbgfmfnt.snmp.util.JvmContfxtFbdtory;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmMfmMbnbgfrTbblf" tbblf.
 *
 * Tiis dustom implfmfntbtion siow iow to implfmfnt bn SNMP tbblf
 * ovfr b wfbk dbdif, rfdomputing tif dbifd dbtb wifn nffdfd.
 */
publid dlbss JvmMfmMbnbgfrTbblfMftbImpl fxtfnds JvmMfmMbnbgfrTbblfMftb {

    stbtid finbl long sfriblVfrsionUID = 36176771566817592L;

    /**
     * A dondrftf implfmfntbtion of {@link SnmpNbmfdListTbblfCbdif}, for tif
     * jvmMfmMbnbgfrTbblf.
     **/
    privbtf stbtid dlbss JvmMfmMbnbgfrTbblfCbdif
        fxtfnds SnmpNbmfdListTbblfCbdif {

        stbtid finbl long sfriblVfrsionUID = 6564294074653009240L;

        /**
         * Crfbtf b wfbk dbdif for tif jvmMfmMbnbgfrTbblf.
         * @pbrbm vblidity vblidity of tif dbdifd dbtb, in ms.
         **/
        JvmMfmMbnbgfrTbblfCbdif(long vblidity) {
            tiis.vblidity = vblidity;
        }

        /**
         * Usf tif MfmoryMbnbgfrMXBfbn nbmf bs kfy.
         * @pbrbm dontfxt A {@link TrffMbp} bs bllodbtfd by tif pbrfnt
         *        {@link SnmpNbmfdListTbblfCbdif} dlbss.
         * @pbrbm rbwDbtbs List of {@link MfmoryMbnbgfrMXBfbn}, bs
         *        rfturnfd by
         * <dodf>MbnbgfmfntFbdtory.gftMfmoryMBfbn().gftMfmoryMbnbgfrs()</dodf>
         * @pbrbm rbnk Tif <vbr>rbnk</vbr> of <vbr>itfm</vbr> in tif list.
         * @pbrbm itfm Tif <vbr>rbnk</vbr><supfr>ti</supfr>
         *        <dodf>MfmoryMbnbgfrMXBfbn</dodf> in tif list.
         * @rfturn  <dodf>((MfmoryMbnbgfrMXBfbn)itfm).gftNbmf()</dodf>
         **/
        protfdtfd String gftKfy(Objfdt dontfxt, List<?> rbwDbtbs,
                                int rbnk, Objfdt itfm) {
            if (itfm == null) rfturn null;
            finbl String nbmf = ((MfmoryMbnbgfrMXBfbn)itfm).gftNbmf();
            log.dfbug("gftKfy", "kfy=" + nbmf);
            rfturn nbmf;
        }

        /**
         * Cbll <dodf>gftTbblfHbndlfr(JvmContfxtFbdtory.gftUsfrDbtb())</dodf>.
         **/
        publid SnmpTbblfHbndlfr gftTbblfHbndlfr() {
            finbl Mbp<Objfdt, Objfdt> usfrDbtb = JvmContfxtFbdtory.gftUsfrDbtb();
            rfturn gftTbblfDbtbs(usfrDbtb);
        }

        /**
         * Rfturn tif kfy usfd to dbdif tif rbw dbtb of tiis tbblf.
         **/
        protfdtfd String gftRbwDbtbsKfy() {
            rfturn "JvmMfmMbnbgfrTbblf.gftMfmoryMbnbgfrs";
        }

        /**
         * Cbll MbnbgfmfntFbdtory.gftMfmoryMbnbgfrMXBfbns() to
         * lobd tif rbw dbtb of tiis tbblf.
         **/
        protfdtfd List<MfmoryMbnbgfrMXBfbn> lobdRbwDbtbs(Mbp<Objfdt, Objfdt> usfrDbtb) {
            rfturn MbnbgfmfntFbdtory.gftMfmoryMbnbgfrMXBfbns();
        }

    }

    // Tif wfbk dbdif for tiis tbblf.
    protfdtfd SnmpTbblfCbdif dbdif;

    /**
     * Construdtor for tif tbblf. Initiblizf mftbdbtb for
     * "JvmMfmMbnbgfrTbblfMftb".
     * Tif rfffrfndf on tif MBfbn sfrvfr is updbtfd so tif fntrifs
     * drfbtfd tirougi bn SNMP SET will bf AUTOMATICALLY REGISTERED
     * in Jbvb DMK.
     */
    publid JvmMfmMbnbgfrTbblfMftbImpl(SnmpMib myMib,
                                      SnmpStbndbrdObjfdtSfrvfr objsfrv) {
        supfr(myMib,objsfrv);
        tiis.dbdif = nfw
            JvmMfmMbnbgfrTbblfCbdif(((JVM_MANAGEMENT_MIB_IMPL)myMib).
                                    vblidity());
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
        if (dbg) log.dfbug("gftNfxtOid", "nfxt=" + nfxt);

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

        if (oid == null)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

        // Gft tif rfqufst dontfxtubl dbdif (usfrDbtb).
        //
        finbl Mbp<Objfdt, Objfdt> m = JvmContfxtFbdtory.gftUsfrDbtb();

        // Wf know in tif dbsf of tiis tbblf tibt tif indfx is bn intfgfr,
        // it is tius tif first OID brd of tif indfx OID.
        //
        finbl long   indfx    = oid.gftOidArd(0);

        // Wf'rf going to usf tiis nbmf to storf/rftrifvf tif fntry in
        // tif rfqufst dontfxtubl dbdif.
        //
        // Rfvisit: Probbbly bfttfr progrbmming to put bll tifsf strings
        //          in somf intfrfbdf.
        //
        finbl String fntryTbg = ((m==null)?null:("JvmMfmMbnbgfrTbblf.fntry." +
                                                 indfx));

        // If tif fntry is in tif dbdif, simply rfturn it.
        //
        if (m != null) {
            finbl Objfdt fntry = m.gft(fntryTbg);
            if (fntry != null) rfturn fntry;
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
        finbl Objfdt fntry =
            nfw JvmMfmMbnbgfrEntryImpl((MfmoryMbnbgfrMXBfbn)dbtb,(int)indfx);

        // Put tif fntry in tif dbdif in dbsf wf nffd it lbtfr wiilf prodfssing
        // tif rfqufst.
        //
        if (m != null && fntry != null) {
            m.put(fntryTbg,fntry);
        }

        rfturn fntry;
    }

    /**
     * Gft tif SnmpTbblfHbndlfr tibt iolds tif jvmMfmMbnbgfrTbblf dbtb.
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
                (SnmpTbblfHbndlfr)m.gft("JvmMfmMbnbgfrTbblf.ibndlfr");
            if (ibndlfr != null) rfturn ibndlfr;
        }

        // No ibndlfr in dontfxtubl dbdif, mbkf b nfw onf.
        finbl SnmpTbblfHbndlfr ibndlfr = dbdif.gftTbblfHbndlfr();

        if (m != null && ibndlfr != null )
            m.put("JvmMfmMbnbgfrTbblf.ibndlfr",ibndlfr);

        rfturn ibndlfr;
    }

    stbtid finbl MibLoggfr log =
        nfw MibLoggfr(JvmMfmMbnbgfrTbblfMftbImpl.dlbss);
}
