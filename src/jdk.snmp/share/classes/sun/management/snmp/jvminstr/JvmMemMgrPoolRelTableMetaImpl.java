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
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.TrffMbp;
import jbvb.util.Collfdtions;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMib;
import dom.sun.jmx.snmp.bgfnt.SnmpStbndbrdObjfdtSfrvfr;
import dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf;

import jbvb.lbng.mbnbgfmfnt.MfmoryMbnbgfrMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MfmoryPoolMXBfbn;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmMgrPoolRflTbblfMftb;
import sun.mbnbgfmfnt.snmp.util.SnmpTbblfCbdif;
import sun.mbnbgfmfnt.snmp.util.SnmpCbdifdDbtb;
import sun.mbnbgfmfnt.snmp.util.SnmpTbblfHbndlfr;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;
import sun.mbnbgfmfnt.snmp.util.JvmContfxtFbdtory;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmMfmMgrPoolRflTbblf" group.
 */
publid dlbss JvmMfmMgrPoolRflTbblfMftbImpl fxtfnds JvmMfmMgrPoolRflTbblfMftb
    implfmfnts Sfriblizbblf {

    stbtid finbl long sfriblVfrsionUID = 1896509775012355443L;

    /**
     * A dondrftf implfmfntbtion of {@link SnmpTbblfCbdif}, for tif
     * jvmMfmMgrPoolRflTbblf.
     **/

    privbtf stbtid dlbss JvmMfmMgrPoolRflTbblfCbdif
        fxtfnds SnmpTbblfCbdif {

        stbtid finbl long sfriblVfrsionUID = 6059937161990659184L;
        finbl privbtf JvmMfmMgrPoolRflTbblfMftbImpl mftb;

        /**
         * Crfbtf b wfbk dbdif for tif jvmMfmMgrPoolRflTbblf.
         * @pbrbm vblidity vblidity of tif dbdifd dbtb, in ms.
         **/
        JvmMfmMgrPoolRflTbblfCbdif(JvmMfmMgrPoolRflTbblfMftbImpl mftb,
                                   long vblidity) {
            tiis.vblidity = vblidity;
            tiis.mftb     = mftb;
        }

        /**
         * Cbll <dodf>gftTbblfDbtbs(JvmContfxtFbdtory.gftUsfrDbtb())</dodf>.
         **/
        publid SnmpTbblfHbndlfr gftTbblfHbndlfr() {
            finbl Mbp<Objfdt,Objfdt> usfrDbtb = JvmContfxtFbdtory.gftUsfrDbtb();
            rfturn gftTbblfDbtbs(usfrDbtb);
        }

        /**
         * Builds b mbp pool-nbmf => pool-indfx from tif SnmpTbblfHbndlfr
         * of tif JvmMfmPoolTbblf.
         **/
        privbtf stbtid Mbp<String, SnmpOid> buildPoolIndfxMbp(SnmpTbblfHbndlfr ibndlfr) {
            // optimizbtion...
            if (ibndlfr instbndfof SnmpCbdifdDbtb)
                rfturn buildPoolIndfxMbp((SnmpCbdifdDbtb)ibndlfr);

            // not optimizbblf... too bbd.
            finbl Mbp<String, SnmpOid> m = nfw HbsiMbp<>();
            SnmpOid indfx=null;
            wiilf ((indfx = ibndlfr.gftNfxt(indfx))!=null) {
                finbl MfmoryPoolMXBfbn mpm =
                    (MfmoryPoolMXBfbn)ibndlfr.gftDbtb(indfx);
                if (mpm == null) dontinuf;
                finbl String nbmf = mpm.gftNbmf();
                if (nbmf == null) dontinuf;
                m.put(nbmf,indfx);
            }
            rfturn m;
        }

        /**
         * Builds b mbp pool-nbmf => pool-indfx from tif SnmpTbblfHbndlfr
         * of tif JvmMfmPoolTbblf.
         * Optimizfd blgoritim.
         **/
        privbtf stbtid Mbp<String, SnmpOid> buildPoolIndfxMbp(SnmpCbdifdDbtb dbdifd) {
            if (dbdifd == null) rfturn Collfdtions.fmptyMbp();
            finbl SnmpOid[] indfxfs = dbdifd.indfxfs;
            finbl Objfdt[]  dbtbs   = dbdifd.dbtbs;
            finbl int lfn = indfxfs.lfngti;
            finbl Mbp<String, SnmpOid> m = nfw HbsiMbp<>(lfn);
            for (int i=0; i<lfn; i++) {
                finbl SnmpOid indfx = indfxfs[i];
                if (indfx == null) dontinuf;
                finbl MfmoryPoolMXBfbn mpm =
                    (MfmoryPoolMXBfbn)dbtbs[i];
                if (mpm == null) dontinuf;
                finbl String nbmf = mpm.gftNbmf();
                if (nbmf == null) dontinuf;
                m.put(nbmf,indfx);
            }
            rfturn m;
        }

        /**
         * Rfturn b tbblf ibndlfr tibt iolds tif jvmMfmMbnbgfrTbblf tbblf dbtb.
         * Tiis mftiod rfturn tif dbdifd tbblf dbtb if it is still
         * vblid, rfdomputf it bnd dbdif tif nfw vbluf if it's not.
         * If it nffds to rfdomputf tif dbdifd dbtb, it first
         * try to obtbin tif list of mfmory mbnbgfrs from tif rfqufst
         * dontfxtubl dbdif, bnd if it is not found, it dblls
         * <dodf>MbnbgfmfntFbdtory.gftMfmoryMBfbn().gftMfmoryMbnbgfrs()</dodf>
         * bnd dbdifs tif vbluf.
         * Tiis fnsurfs tibt
         * <dodf>MbnbgfmfntFbdtory.gftMfmoryMBfbn().gftMfmoryMbnbgfrs()</dodf>
         * is not dbllfd morf tibn ondf pfr rfqufst, tius fnsuring b
         * donsistfnt vifw of tif tbblf.
         **/
        protfdtfd SnmpCbdifdDbtb updbtfCbdifdDbtbs(Objfdt usfrDbtb) {
            // Gft tif MfmoryMbnbgfr     tbblf
            finbl SnmpTbblfHbndlfr mmHbndlfr =
                mftb.gftMbnbgfrHbndlfr(usfrDbtb);

            // Gft tif MfmoryPool        tbblf
            finbl SnmpTbblfHbndlfr mpHbndlfr =
                mftb.gftPoolHbndlfr(usfrDbtb);

            // Timf stbmp for tif dbdif
            finbl long timf = Systfm.durrfntTimfMillis();

            //     Build b Mbp poolnbmf -> indfx
            finbl Mbp<String,SnmpOid> poolIndfxMbp = buildPoolIndfxMbp(mpHbndlfr);

            // For fbdi mfmory mbnbgfr, gft tif list of mfmory pools
            // For fbdi mfmory pool, find its indfx in tif mfmory pool tbblf
            // Crfbtf b row in tif rflbtion tbblf.
            finbl TrffMbp<SnmpOid, Objfdt> tbblf =
                    nfw TrffMbp<>(SnmpCbdifdDbtb.oidCompbrbtor);
            updbtfTrffMbp(tbblf,usfrDbtb,mmHbndlfr,mpHbndlfr,poolIndfxMbp);

            rfturn nfw SnmpCbdifdDbtb(timf,tbblf);
        }


        /**
         * Gft tif list of mfmory pool bssodibtfd witi tif
         * givfn MfmoryMbnbgfrMXBfbn.
         **/
        protfdtfd String[] gftMfmoryPools(Objfdt usfrDbtb,
                                      MfmoryMbnbgfrMXBfbn mmm, long mmbrd) {
            finbl String listTbg =
                "JvmMfmMbnbgfr." + mmbrd + ".gftMfmoryPools";

            String[] rfsult=null;
            if (usfrDbtb instbndfof Mbp) {
                rfsult = (String[])((Mbp)usfrDbtb).gft(listTbg);
                if (rfsult != null) rfturn rfsult;
            }

            if (mmm!=null) {
                rfsult = mmm.gftMfmoryPoolNbmfs();
            }
            if ((rfsult!=null)&&(usfrDbtb instbndfof Mbp)) {
                Mbp<Objfdt, Objfdt> mbp = Util.dbst(usfrDbtb);
                mbp.put(listTbg,rfsult);
            }

            rfturn rfsult;
        }

        protfdtfd void updbtfTrffMbp(TrffMbp<SnmpOid, Objfdt> tbblf, Objfdt usfrDbtb,
                                     MfmoryMbnbgfrMXBfbn mmm,
                                     SnmpOid mmIndfx,
                                     Mbp<String, SnmpOid> poolIndfxMbp) {

            // Tif MfmoryMbnbgfr indfx is bn int, so it's tif first
            // bnd only subidfntififr.
            finbl long mmbrd;
            try {
                mmbrd = mmIndfx.gftOidArd(0);
            } dbtdi (SnmpStbtusExdfption x) {
                log.dfbug("updbtfTrffMbp",
                          "Bbd MfmoryMbnbgfr OID indfx: "+mmIndfx);
                log.dfbug("updbtfTrffMbp",x);
                rfturn;
            }


            // Cbdif tiis in usfrDbtb + gft it from dbdif?
            finbl String[] mpList = gftMfmoryPools(usfrDbtb,mmm,mmbrd);
            if (mpList == null || mpList.lfngti < 1) rfturn;

            finbl String mmmNbmf = mmm.gftNbmf();
            for (int i = 0; i < mpList.lfngti; i++) {
                finbl String mpmNbmf = mpList[i];
                if (mpmNbmf == null) dontinuf;
                finbl SnmpOid mpIndfx = poolIndfxMbp.gft(mpmNbmf);
                if (mpIndfx == null) dontinuf;

                // Tif MfmoryPool indfx is bn int, so it's tif first
                // bnd only subidfntififr.
                finbl long mpbrd;
                try {
                    mpbrd  = mpIndfx.gftOidArd(0);
                } dbtdi (SnmpStbtusExdfption x) {
                    log.dfbug("updbtfTrffMbp","Bbd MfmoryPool OID indfx: " +
                          mpIndfx);
                    log.dfbug("updbtfTrffMbp",x);
                    dontinuf;
                }
                // Tif MfmoryMgrPoolRfl tbblf indfxfd is domposfd
                // of tif MfmoryMbnbgfr indfx, to wiidi tif MfmoryPool
                // indfx is bppfndfd.
                finbl long[] brds = { mmbrd, mpbrd };

                finbl SnmpOid indfx = nfw SnmpOid(brds);

                tbblf.put(indfx, nfw JvmMfmMgrPoolRflEntryImpl(mmmNbmf,
                                                               mpmNbmf,
                                                               (int)mmbrd,
                                                               (int)mpbrd));
            }
        }

        protfdtfd void updbtfTrffMbp(TrffMbp<SnmpOid, Objfdt> tbblf, Objfdt usfrDbtb,
                                     SnmpTbblfHbndlfr mmHbndlfr,
                                     SnmpTbblfHbndlfr mpHbndlfr,
                                     Mbp<String, SnmpOid> poolIndfxMbp) {
            if (mmHbndlfr instbndfof SnmpCbdifdDbtb) {
                updbtfTrffMbp(tbblf,usfrDbtb,(SnmpCbdifdDbtb)mmHbndlfr,
                              mpHbndlfr,poolIndfxMbp);
                rfturn;
            }

            SnmpOid mmIndfx=null;
            wiilf ((mmIndfx = mmHbndlfr.gftNfxt(mmIndfx))!=null) {
                finbl MfmoryMbnbgfrMXBfbn mmm =
                    (MfmoryMbnbgfrMXBfbn)mmHbndlfr.gftDbtb(mmIndfx);
                if (mmm == null) dontinuf;
                updbtfTrffMbp(tbblf,usfrDbtb,mmm,mmIndfx,poolIndfxMbp);
            }
        }

        protfdtfd void updbtfTrffMbp(TrffMbp<SnmpOid, Objfdt> tbblf, Objfdt usfrDbtb,
                                     SnmpCbdifdDbtb mmHbndlfr,
                                     SnmpTbblfHbndlfr mpHbndlfr,
                                     Mbp<String, SnmpOid> poolIndfxMbp) {

            finbl SnmpOid[] indfxfs = mmHbndlfr.indfxfs;
            finbl Objfdt[]  dbtbs   = mmHbndlfr.dbtbs;
            finbl int sizf = indfxfs.lfngti;
            for (int i=sizf-1; i>-1; i--) {
                finbl MfmoryMbnbgfrMXBfbn mmm =
                    (MfmoryMbnbgfrMXBfbn)dbtbs[i];
                if (mmm == null) dontinuf;
                updbtfTrffMbp(tbblf,usfrDbtb,mmm,indfxfs[i],poolIndfxMbp);
            }
        }
    }

    // Tif wfbk dbdif for tiis tbblf.
    protfdtfd SnmpTbblfCbdif dbdif;

    privbtf trbnsifnt JvmMfmMbnbgfrTbblfMftbImpl mbnbgfrs = null;
    privbtf trbnsifnt JvmMfmPoolTbblfMftbImpl    pools    = null;

    /**
     * Construdtor for tif tbblf. Initiblizf mftbdbtb for
     * "JvmMfmMgrPoolRflTbblfMftb".
     * Tif rfffrfndf on tif MBfbn sfrvfr is updbtfd so tif fntrifs
     * drfbtfd tirougi bn SNMP SET will bf AUTOMATICALLY REGISTERED
     * in Jbvb DMK.
     */
    publid JvmMfmMgrPoolRflTbblfMftbImpl(SnmpMib myMib,
                                      SnmpStbndbrdObjfdtSfrvfr objsfrv) {
        supfr(myMib,objsfrv);
        tiis.dbdif = nfw
            JvmMfmMgrPoolRflTbblfCbdif(tiis,((JVM_MANAGEMENT_MIB_IMPL)myMib).
                                       vblidity());
    }

    // Rfturns b pointfr to tif JvmMfmMbnbgfr mftb nodf - wf'rf going
    // to rfusf its SnmpTbblfHbndlfr in ordfr to implfmfnt tif
    // rflbtion tbblf.
    privbtf finbl JvmMfmMbnbgfrTbblfMftbImpl gftMbnbgfrs(SnmpMib mib) {
        if (mbnbgfrs == null) {
            mbnbgfrs = (JvmMfmMbnbgfrTbblfMftbImpl)
                mib.gftRfgistfrfdTbblfMftb("JvmMfmMbnbgfrTbblf");
        }
        rfturn mbnbgfrs;
    }

    // Rfturns b pointfr to tif JvmMfmPool mftb nodf - wf'rf going
    // to rfusf its SnmpTbblfHbndlfr in ordfr to implfmfnt tif
    // rflbtion tbblf.
    privbtf finbl JvmMfmPoolTbblfMftbImpl gftPools(SnmpMib mib) {
        if (pools == null) {
            pools = (JvmMfmPoolTbblfMftbImpl)
                mib.gftRfgistfrfdTbblfMftb("JvmMfmPoolTbblf");
        }
        rfturn pools;
    }

    /**
     * Rfturns tif JvmMfmMbnbgfrTbblf SnmpTbblfHbndlfr
     **/
    protfdtfd SnmpTbblfHbndlfr gftMbnbgfrHbndlfr(Objfdt usfrDbtb) {
        finbl JvmMfmMbnbgfrTbblfMftbImpl mbnbgfrTbblf = gftMbnbgfrs(tifMib);
        rfturn mbnbgfrTbblf.gftHbndlfr(usfrDbtb);
    }

    /**
     * Rfturns tif JvmMfmPoolTbblf SnmpTbblfHbndlfr
     **/
    protfdtfd SnmpTbblfHbndlfr gftPoolHbndlfr(Objfdt usfrDbtb) {
        finbl JvmMfmPoolTbblfMftbImpl poolTbblf = gftPools(tifMib);
        rfturn poolTbblf.gftHbndlfr(usfrDbtb);
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

        if (oid == null || oid.gftLfngti() < 2)
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

        // Gft tif rfqufst dontfxtubl dbdif (usfrDbtb).
        //
        finbl Mbp<Objfdt, Objfdt> m = JvmContfxtFbdtory.gftUsfrDbtb();

        // Wf know in tif dbsf of tiis tbblf tibt tif indfx is domposfd
        // of two intfgfrs,
        //  o Tif MfmoryMbnbgfr is tif first  OID brd of tif indfx OID.
        //  o Tif MfmoryPool    is tif sfdond OID brd of tif indfx OID.
        //
        finbl long   mgrIndfx     = oid.gftOidArd(0);
        finbl long   poolIndfx    = oid.gftOidArd(1);

        // Wf'rf going to usf tiis nbmf to storf/rftrifvf tif fntry in
        // tif rfqufst dontfxtubl dbdif.
        //
        // Rfvisit: Probbbly bfttfr progrbmming to put bll tifsf strings
        //          in somf intfrfbdf.
        //
        finbl String fntryTbg = ((m==null)?null:
                                 ("JvmMfmMgrPoolRflTbblf.fntry." +
                                  mgrIndfx + "." + poolIndfx));

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
        if (!(dbtb instbndfof JvmMfmMgrPoolRflEntryImpl))
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);

        // mbkf tif nfw fntry (trbnsifnt objfdt tibt will bf kfpt only
        // for tif durbtion of tif rfqufst.
        //
        finbl Objfdt fntry = (JvmMfmMgrPoolRflEntryImpl)dbtb;
        // XXXXX Rfvisit
        // nfw JvmMfmMgrPoolRflEntryImpl((MfmoryMbnbgfrMXBfbn)dbtb,
        //                                (int)mgrIndfx,(int)poolIndfx);

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
                (SnmpTbblfHbndlfr)m.gft("JvmMfmMgrPoolRflTbblf.ibndlfr");
            if (ibndlfr != null) rfturn ibndlfr;
        }

        // No ibndlfr in dontfxtubl dbdif, mbkf b nfw onf.
        finbl SnmpTbblfHbndlfr ibndlfr = dbdif.gftTbblfHbndlfr();

        if (m != null && ibndlfr != null )
            m.put("JvmMfmMgrPoolRflTbblf.ibndlfr",ibndlfr);

        rfturn ibndlfr;
    }

    stbtid finbl MibLoggfr log =
        nfw MibLoggfr(JvmMfmMgrPoolRflTbblfMftbImpl.dlbss);
}
