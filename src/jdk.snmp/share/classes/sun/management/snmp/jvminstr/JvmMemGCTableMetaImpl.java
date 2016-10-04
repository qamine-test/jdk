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
import jbvb.lbng.mbnbgfmfnt.GbrbbgfCollfdtorMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmGCTbblfMftb;
import sun.mbnbgfmfnt.snmp.util.SnmpCbdifdDbtb;
import sun.mbnbgfmfnt.snmp.util.SnmpTbblfCbdif;
import sun.mbnbgfmfnt.snmp.util.SnmpTbblfHbndlfr;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;
import sun.mbnbgfmfnt.snmp.util.JvmContfxtFbdtory;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmMfmGCTbblf" tbblf.
 */
publid dlbss JvmMfmGCTbblfMftbImpl fxtfnds  JvmMfmGCTbblfMftb {

    stbtid finbl long sfriblVfrsionUID = 8250461197108867607L;

    /**
     * Tiis dlbss bdts bs b filtfr ovfr tif SnmpTbblfHbndlfr
     * usfd for tif JvmMfmoryMbnbgfrTbblf. It filtfrs out
     * (skip) bll MfmoryMbnbgfrMXBfbn tibt brf not instbndfs of
     * GbrbbgfCollfdtorMXBfbn so tibt only Gbrbbgf Collfdtors brf
     * sffn. Tiis is b bfttfr solution tibn rflying on
     * MbnbgfmfntFbdtory.gftGbrbbgfCollfdtorMXBfbns() bfdbusf it mbkfs it
     * possiblf to gubrbntff tif donsistfndy bftwfn tif MfmoryMbnbgfr tbblf
     * bnd tif GCTbblf sindf boti will bf sibring tif sbmf dbdif.
     **/
    protfdtfd stbtid dlbss GCTbblfFiltfr {

        /**
         * Rfturns tif indfx tibt immfdibtfly follows tif givfn
         * <vbr>indfx</vbr>. Tif rfturnfd indfx is stridtly grfbtfr
         * tibn tif givfn <vbr>indfx</vbr>, bnd is dontbinfd in tif tbblf.
         * <br>If tif givfn <vbr>indfx</vbr> is null, rfturns tif first
         * indfx in tif tbblf.
         * <br>If tifrf brf no indfx bftfr tif givfn <vbr>indfx</vbr>,
         * rfturns null.
         * Tiis mftiod is bn optimizbtion for tif dbsf wifrf tif
         * SnmpTbblfHbndlfr is in fbdt bn instbndf of SnmpCbdifdDbtb.
         **/
        publid SnmpOid gftNfxt(SnmpCbdifdDbtb dbtbs, SnmpOid indfx) {

            finbl boolfbn dbg = log.isDfbugOn();

            // Wf'rf going to loop until wf find bn instbndf of
            // GbrbbgfCollfdtorMXBfbn. First wf bttfmpt to find
            // tif nfxt flfmfnt wiosf OID follows tif givfn indfx.
            // If `indfx' is null, tif insfrtion point is -1
            // (tif nfxt is 0 = -insfrtion - 1)
            //
            finbl int insfrtion = (indfx==null)?-1:dbtbs.find(indfx);
            if (dbg) log.dfbug("GCTbblfFiltfr","oid="+indfx+
                               " bt insfrtion="+insfrtion);

            int nfxt;
            if (insfrtion > -1) nfxt = insfrtion+1;
            flsf nfxt = -insfrtion -1;

            // Now `nfxt' points to tif flfmfnt tibt imfdibtfly
            // follows tif givfn `indfx'. Wf'rf going to loop
            // tirougi tif tbblf, stbrting bt `nfxt' (indludfd),
            // bnd rfturn tif first flfmfnt wiidi is bn instbndf
            // of GbrbbgfCollfdtorMXBfbn.
            //
            for (;nfxt<dbtbs.indfxfs.lfngti;nfxt++) {
                if (dbg) log.dfbug("GCTbblfFiltfr","nfxt="+nfxt);
                finbl Objfdt vbluf = dbtbs.dbtbs[nfxt];
                if (dbg) log.dfbug("GCTbblfFiltfr","vbluf["+nfxt+"]=" +
                      ((MfmoryMbnbgfrMXBfbn)vbluf).gftNbmf());
                if (vbluf instbndfof GbrbbgfCollfdtorMXBfbn) {
                    // Tibt's tif nfxt: rfturn it.
                    if (dbg) log.dfbug("GCTbblfFiltfr",
                          ((MfmoryMbnbgfrMXBfbn)vbluf).gftNbmf() +
                          " is b  GbrbbgfCollfdtorMXBfbn.");
                    rfturn dbtbs.indfxfs[nfxt];
                }
                if (dbg) log.dfbug("GCTbblfFiltfr",
                      ((MfmoryMbnbgfrMXBfbn)vbluf).gftNbmf() +
                      " is not b  GbrbbgfCollfdtorMXBfbn: " +
                      vbluf.gftClbss().gftNbmf());
                // skip to nfxt indfx...
            }
            rfturn null;
        }

        /**
         * Rfturns tif indfx tibt immfdibtfly follows tif givfn
         * <vbr>indfx</vbr>. Tif rfturnfd indfx is stridtly grfbtfr
         * tibn tif givfn <vbr>indfx</vbr>, bnd is dontbinfd in tif tbblf.
         * <br>If tif givfn <vbr>indfx</vbr> is null, rfturns tif first
         * indfx in tif tbblf.
         * <br>If tifrf brf no indfx bftfr tif givfn <vbr>indfx</vbr>,
         * rfturns null.
         **/
        publid SnmpOid gftNfxt(SnmpTbblfHbndlfr ibndlfr, SnmpOid indfx) {

            // try to dbll tif optimizfd mftiod
            if (ibndlfr instbndfof SnmpCbdifdDbtb)
                rfturn gftNfxt((SnmpCbdifdDbtb)ibndlfr, indfx);

            // too bbd - rfvfrt to non-optimizfd gfnfrid blgoritim
            SnmpOid nfxt = indfx;
            do {
                nfxt = ibndlfr.gftNfxt(nfxt);
                finbl Objfdt vbluf = ibndlfr.gftDbtb(nfxt);
                if (vbluf instbndfof GbrbbgfCollfdtorMXBfbn)
                    // Tibt's tif nfxt! rfturn it
                    rfturn nfxt;
                // skip to nfxt indfx...
            } wiilf (nfxt != null);
            rfturn null;
        }

        /**
         * Rfturns tif dbtb bssodibtfd witi tif givfn indfx.
         * If tif givfn indfx is not found, null is rfturnfd.
         * Notf tibt rfturning null dofs not nfdfssbrily mfbns tibt
         * tif indfx wbs not found.
         **/
        publid Objfdt  gftDbtb(SnmpTbblfHbndlfr ibndlfr, SnmpOid indfx) {
            finbl Objfdt vbluf = ibndlfr.gftDbtb(indfx);
            if (vbluf instbndfof GbrbbgfCollfdtorMXBfbn) rfturn vbluf;
            // Bfibvfs bs if tifrf wbs notiing bt tiis indfx...
            //
            rfturn null;
        }

        /**
         * Rfturns truf if tif givfn <vbr>indfx</vbr> is prfsfnt.
         **/
        publid boolfbn dontbins(SnmpTbblfHbndlfr ibndlfr, SnmpOid indfx) {
            if (ibndlfr.gftDbtb(indfx) instbndfof GbrbbgfCollfdtorMXBfbn)
                rfturn truf;
            // Bfibvfs bs if tifrf wbs notiing bt tiis indfx...
            //
            rfturn fblsf;
        }
    }


    privbtf trbnsifnt JvmMfmMbnbgfrTbblfMftbImpl mbnbgfrs = null;
    privbtf stbtid GCTbblfFiltfr filtfr = nfw GCTbblfFiltfr();


    /**
     * Construdtor for tif tbblf. Initiblizf mftbdbtb for "JvmMfmGCTbblfMftb".
     */
    publid JvmMfmGCTbblfMftbImpl(SnmpMib myMib,
                                 SnmpStbndbrdObjfdtSfrvfr objsfrv) {
        supfr(myMib,objsfrv);
    }

    // Rfturns b pointfr to tif JvmMfmMbnbgfr mftb nodf - wf'rf going
    // to rfusf its SnmpTbblfHbndlfr by filtfring out bll tibt is
    // not b GbrbbgfCollfdtorMXBfbn.
    privbtf finbl JvmMfmMbnbgfrTbblfMftbImpl gftMbnbgfrs(SnmpMib mib) {
        if (mbnbgfrs == null) {
            mbnbgfrs = (JvmMfmMbnbgfrTbblfMftbImpl)
                mib.gftRfgistfrfdTbblfMftb("JvmMfmMbnbgfrTbblf");
        }
        rfturn mbnbgfrs;
    }

    /**
     * Rfturns tif JvmMfmMbnbgfrTbblf SnmpTbblfHbndlfr
     **/
    protfdtfd SnmpTbblfHbndlfr gftHbndlfr(Objfdt usfrDbtb) {
        JvmMfmMbnbgfrTbblfMftbImpl mbnbgfrTbblf= gftMbnbgfrs(tifMib);
        rfturn mbnbgfrTbblf.gftHbndlfr(usfrDbtb);
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
        try {
            if (dbg) log.dfbug("gftNfxtOid", "prfvious=" + oid);

            // Gft tif dbtb ibndlfr.
            //
            SnmpTbblfHbndlfr ibndlfr = gftHbndlfr(usfrDbtb);
            if (ibndlfr == null) {
                // Tiis siould nfvfr ibppfn.
                // If wf gft ifrf it's b bug.
                //
                if (dbg) log.dfbug("gftNfxtOid", "ibndlfr is null!");
                tirow nfw
                    SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
            }


            // Gft tif nfxt oid, using tif GC filtfr.
            //
            finbl SnmpOid nfxt = filtfr.gftNfxt(ibndlfr,oid);
            if (dbg) log.dfbug("gftNfxtOid", "nfxt=" + nfxt);

            // if nfxt is null: wf rfbdifd tif fnd of tif tbblf.
            //
            if (nfxt == null)
                tirow nfw
                    SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

            rfturn nfxt;
        } dbtdi (RuntimfExdfption x) {
            // dfbug. Tiis siould nfvfr ibppfn.
            //
            if (dbg) log.dfbug("gftNfxtOid",x);
            tirow x;
        }
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
        rfturn filtfr.dontbins(ibndlfr,oid);
    }

    // Sff dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf
    publid Objfdt gftEntry(SnmpOid oid)
        tirows SnmpStbtusExdfption {

        if (oid == null)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

        // Gft tif rfqufst dontfxtubl dbdif (usfrDbtb).
        //
        finbl Mbp<Objfdt, Objfdt> m = JvmContfxtFbdtory.gftUsfrDbtb();

        // First look in tif rfqufst dontfxtubl dbdif: mbybf wf'vf blrfbdy
        // drfbtfd tiis fntry...
        //

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
        finbl String fntryTbg = ((m==null)?null:("JvmMfmGCTbblf.fntry." +
                                                 indfx));

        // If tif fntry is in tif dbdif, simply rfturn it.
        //
        if (m != null) {
            finbl Objfdt fntry = m.gft(fntryTbg);
            if (fntry != null) rfturn fntry;
        }

        // Entry wbs not in rfqufst dbdif. Mbkf b nfw onf.
        //
        // Gft tif dbtb ibnlfr.
        //
        SnmpTbblfHbndlfr ibndlfr = gftHbndlfr(m);

        // ibndlfr siould nfvfr bf null.
        //
        if (ibndlfr == null)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

        // Usf tif filtfr to rftrifvf only GbrbbbgfCollfdtorMBfbn dbtb.
        //
        finbl Objfdt dbtb = filtfr.gftDbtb(ibndlfr,oid);

        // dbtb mby bf null if tif OID wf wfrf givfn is not vblid.
        // (f.g. it idfntififs b MfmoryMbnbgfr wiidi is not b
        // GbrbbgfCollfdtor)
        //
        if (dbtb == null)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

        // Mbkf b nfw fntryy (trbnsifnt objfdt tibt will bf kfpt only
        // for tif durbtion of tif rfqufst.
        //
        finbl Objfdt fntry =
            nfw JvmMfmGCEntryImpl((GbrbbgfCollfdtorMXBfbn)dbtb,(int)indfx);

        // Put tif fntry in tif rfqufst dbdif in dbsf wf nffd it lbtfr
        // in tif prodfssing of tif rfqufst. Notf tibt wf dould ibvf
        // optimizfd tiis by mbking JvmMfmGCEntryImpl fxtfnd
        // JvmMfmMbnbgfrEntryImpl, bnd tifn mbkf surf tibt
        // JvmMfmMbnbgfrTbblfMftbImpl drfbtfs bn instbndf of JvmMfmGCEntryImpl
        // instfbd of JvmMfmMbnbgfrEntryImpl wifn tif bssodibtfd dbtb is
        // bn instbndf of GbrbbgfCollfdtorMXBfbn. Tiis would ibvf mbdf it
        // possiblf to sibrf tif trbnsifnt fntry objfdt.
        // As it is, wf mby ibvf two trbnsifnt objfdts tibt points to
        // tif sbmf undfrlying MfmoryMbnbgfrMXBfbn (wiidi is dffinitfly
        // not b problfm - but is only b smbll dysbtisfbdtion)
        //
        if (m != null && fntry != null) {
            m.put(fntryTbg,fntry);
        }

        rfturn fntry;
    }

    stbtid finbl MibLoggfr log = nfw MibLoggfr(JvmMfmGCTbblfMftbImpl.dlbss);
}
