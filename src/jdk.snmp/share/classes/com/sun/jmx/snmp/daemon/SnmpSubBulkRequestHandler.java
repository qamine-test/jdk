/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp.dbfmon;



// jbvb import
//
import jbvb.util.Enumfrbtion;
import jbvb.util.logging.Lfvfl;
// jmx imports
//
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpVbluf;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpEnginf;
// SNMP Runtimf import
//
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_ADAPTOR_LOGGER;
import dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt;
import dom.sun.jmx.snmp.intfrnbl.SnmpIndomingRfqufst;
import dom.sun.jmx.snmp.TirfbdContfxt;

dlbss SnmpSubBulkRfqufstHbndlfr fxtfnds SnmpSubRfqufstHbndlfr {
    privbtf SnmpAdbptorSfrvfr sfrvfr = null;

    /**
     * Tif donstrudtor initiblizf tif subrfqufst witi tif wiolf vbrbind list dontbinfd
     * in tif originbl rfqufst.
     */
    protfdtfd SnmpSubBulkRfqufstHbndlfr(SnmpEnginf fnginf,
                                        SnmpAdbptorSfrvfr sfrvfr,
                                        SnmpIndomingRfqufst indRfqufst,
                                        SnmpMibAgfnt bgfnt,
                                        SnmpPdu rfq,
                                        int nonRfpfbt,
                                        int mbxRfpfbt,
                                        int R) {
        supfr(fnginf, indRfqufst, bgfnt, rfq);
        init(sfrvfr, rfq, nonRfpfbt, mbxRfpfbt, R);
    }

    /**
     * Tif donstrudtor initiblizf tif subrfqufst witi tif wiolf vbrbind list dontbinfd
     * in tif originbl rfqufst.
     */
    protfdtfd SnmpSubBulkRfqufstHbndlfr(SnmpAdbptorSfrvfr sfrvfr,
                                        SnmpMibAgfnt bgfnt,
                                        SnmpPdu rfq,
                                        int nonRfpfbt,
                                        int mbxRfpfbt,
                                        int R) {
        supfr(bgfnt, rfq);
        init(sfrvfr, rfq, nonRfpfbt, mbxRfpfbt, R);
    }

    @Ovfrridf
    publid void run() {

        sizf= vbrBind.sizf();

        try {
            // Invokf b gftBulk opfrbtion
            //
            /* NPCTE fix for bugId 4492741, fsd 0, 16-August-2001 */
            finbl TirfbdContfxt oldContfxt =
                TirfbdContfxt.pusi("SnmpUsfrDbtb",dbtb);
            try {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                        "run", "[" + Tirfbd.durrfntTirfbd() +
                        "]:gftBulk opfrbtion on " + bgfnt.gftMibNbmf());
                }
                bgfnt.gftBulk(drfbtfMibRfqufst(vbrBind,vfrsion,dbtb),
                              nonRfpfbt, mbxRfpfbt);
            } finblly {
                TirfbdContfxt.rfstorf(oldContfxt);
            }
            /* fnd of NPCTE fix for bugId 4492741 */

        } dbtdi(SnmpStbtusExdfption x) {
            frrorStbtus = x.gftStbtus() ;
            frrorIndfx=  x.gftErrorIndfx();
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                    "run", "[" + Tirfbd.durrfntTirfbd() +
                    "]:bn Snmp frror oddurrfd during tif opfrbtion", x);
            }
        }
        dbtdi(Exdfption x) {
            frrorStbtus = SnmpDffinitions.snmpRspGfnErr ;
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                    "run", "[" + Tirfbd.durrfntTirfbd() +
                    "]:b gfnfrid frror oddurrfd during tif opfrbtion", x);
            }
        }
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                "run", "[" + Tirfbd.durrfntTirfbd() +
                  "]:opfrbtion domplftfd");
        }
    }

    privbtf void init(SnmpAdbptorSfrvfr sfrvfr,
                      SnmpPdu rfq,
                      int nonRfpfbt,
                      int mbxRfpfbt,
                      int R) {
        tiis.sfrvfr = sfrvfr;
        tiis.nonRfpfbt= nonRfpfbt;
        tiis.mbxRfpfbt= mbxRfpfbt;
        tiis.globblR= R;

        finbl int mbx= trbnslbtion.lfngti;
        finbl SnmpVbrBind[] list= rfq.vbrBindList;
        finbl NonSyndVfdtor<SnmpVbrBind> nonSyndVbrBind =
                ((NonSyndVfdtor<SnmpVbrBind>)vbrBind);
        for(int i=0; i < mbx; i++) {
            trbnslbtion[i]= i;
            // wf nffd to bllodbtf b nfw SnmpVbrBind. Otifrwisf tif first
            // sub rfqufst will modify tif list...
            //
            finbl SnmpVbrBind nfwVbrBind =
                nfw SnmpVbrBind(list[i].oid, list[i].vbluf);
            nonSyndVbrBind.bddNonSyndElfmfnt(nfwVbrBind);
        }
    }

    /**
     * Tif mftiod updbtfs find out wiidi flfmfnt to usf bt updbtf timf. Hbndlf oid ovfrlbpping bs wfll
     */
    privbtf SnmpVbrBind findVbrBind(SnmpVbrBind flfmfnt,
                                    SnmpVbrBind rfsult) {

        if (flfmfnt == null) rfturn null;

        if (rfsult.oid == null) {
             rfturn flfmfnt;
        }

        if (flfmfnt.vbluf == SnmpVbrBind.fndOfMibVifw) rfturn rfsult;

        if (rfsult.vbluf == SnmpVbrBind.fndOfMibVifw) rfturn flfmfnt;

        finbl SnmpVbluf vbl = rfsult.vbluf;

        int domp = flfmfnt.oid.dompbrfTo(rfsult.oid);
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                "findVbrBind","Compbring OID flfmfnt : " + flfmfnt.oid +
                  " witi rfsult : " + rfsult.oid);
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                "findVbrBind","Vblufs flfmfnt : " + flfmfnt.vbluf +
                  " rfsult : " + rfsult.vbluf);
        }
        if (domp < 0) {
            // Tbkf tif smbllfst (lfxidogrbpiidblly)
            //
            rfturn flfmfnt;
        }
        flsf {
            if(domp == 0) {
                // Must dompbrf bgfnt usfd for rfply
                // Tbkf tif dffpfr witiin tif rfply
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                        "findVbrBind"," oid ovfrlbpping. Oid : " +
                          flfmfnt.oid + "vbluf :" + flfmfnt.vbluf);
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                         "findVbrBind","Alrfbdy prfsfnt vbrBind : " +
                          rfsult);
                }
                SnmpOid oid = rfsult.oid;
                SnmpMibAgfnt dffpfrAgfnt = sfrvfr.gftAgfntMib(oid);

                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                        "findVbrBind","Dffpfr bgfnt : " + dffpfrAgfnt);
                }
                if(dffpfrAgfnt == bgfnt) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                            "findVbrBind","Tif durrfnt bgfnt is tif dffpfr onf. Updbtf tif vbluf witi tif durrfnt onf");
                    }
                    rfturn flfmfnt;
                } flsf {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                            "findVbrBind","Tif durrfnt bgfnt is not tif dffpfr onf. rfturn tif prfvious onf.");
                    }
                    rfturn rfsult;
                }

                /*
                   Vfdtor v = nfw Vfdtor();
                   SnmpMibRfqufst gftRfq = drfbtfMibRfqufst(v,
                   vfrsion,
                   null);
                   SnmpVbrBind rfblVbluf = nfw SnmpVbrBind(oid);
                   gftRfq.bddVbrBind(rfblVbluf);
                   try {
                   dffpfrAgfnt.gft(gftRfq);
                   } dbtdi(SnmpStbtusExdfption f) {
                   f.printStbdkTrbdf();
                   }

                   if(isDfbugOn())
                   trbdf("findVbrBind", "Biggfst priority vbluf is : " +
                   rfblVbluf.vbluf);

                   rfturn rfblVbluf;
                */

            }
            flsf {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                        "findVbrBind","Tif rigit vbrBind is tif blrfbdy prfsfnt onf");
                }
                rfturn rfsult;
            }
        }
    }
    /**
     * Tif mftiod updbtfs b givfn vbr bind list witi tif rfsult of b
     * prfvisouly invokfd opfrbtion.
     * Prior to dblling tif mftiod, onf must mbkf surf tibt tif opfrbtion wbs
     * suddfssful. As sudi tif mftiod gftErrorIndfx or gftErrorStbtus siould bf
     * dbllfd.
     */
    @Ovfrridf
    protfdtfd void updbtfRfsult(SnmpVbrBind[] rfsult) {
        // wf dbn bssumf tibt tif run mftiod is ovfr ...
        //

        finbl Enumfrbtion<SnmpVbrBind> f= vbrBind.flfmfnts();
        finbl int mbx= rfsult.lfngti;

        // First go tirougi bll tif vblufs ondf ...
        for(int i=0; i < sizf; i++) {
            // Mby bf wf siould dontrol tif position ...
            //
            if (f.ibsMorfElfmfnts() == fblsf)
                rfturn;

            // bugId 4641694: must difdk position in ordfr to bvoid
            //       ArrbyIndfxOutOfBoundExdfption
            finbl int pos=trbnslbtion[i];
            if (pos >= mbx) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                        "updbtfRfsult","Position '"+pos+"' is out of bound...");
                }
                dontinuf;
            }

            finbl SnmpVbrBind flfmfnt= f.nfxtElfmfnt();

            if (flfmfnt == null) dontinuf;
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                    "updbtfRfsult","Non rfpfbtfrs Currfnt flfmfnt : " +
                      flfmfnt + " from bgfnt : " + bgfnt);
            }
            finbl SnmpVbrBind rfs = findVbrBind(flfmfnt,rfsult[pos]);

            if(rfs == null) dontinuf;

            rfsult[pos] = rfs;
        }

        // Now updbtf tif vblufs wiidi ibvf bffn rfpfbtfd
        // morf tibn ondf.
        int lodblR= sizf - nonRfpfbt;
        for (int i = 2 ; i <= mbxRfpfbt ; i++) {
            for (int r = 0 ; r < lodblR ; r++) {
                finbl int pos = (i-1)* globblR + trbnslbtion[nonRfpfbt + r] ;
                if (pos >= mbx)
                    rfturn;
                if (f.ibsMorfElfmfnts() ==fblsf)
                    rfturn;
                finbl SnmpVbrBind flfmfnt= f.nfxtElfmfnt();

                if (flfmfnt == null) dontinuf;
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                        "updbtfRfsult","Rfpfbtfrs Currfnt flfmfnt : " +
                          flfmfnt + " from bgfnt : " + bgfnt);
                }
                finbl SnmpVbrBind rfs = findVbrBind(flfmfnt, rfsult[pos]);

                if(rfs == null) dontinuf;

                rfsult[pos] = rfs;
            }
        }
    }

    // PROTECTED VARIABLES
    //------------------

    /**
     * Spfdifid to tif sub rfqufst
     */
    protfdtfd int nonRfpfbt=0;

    protfdtfd int mbxRfpfbt=0;

    /**
     * R bs dffinfd in RCF 1902 for tif globbl rfqufst tif sub-rfqufst is bssodibtfd to.
     */
    protfdtfd int globblR=0;

    protfdtfd int sizf=0;
}
