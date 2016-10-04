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
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.logging.Lfvfl;
import jbvb.io.IntfrruptfdIOExdfption;
import jbvb.nft.DbtbgrbmSodkft;
import jbvb.nft.DbtbgrbmPbdkft;
import jbvb.nft.SodkftExdfption;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import dom.sun.jmx.snmp.SnmpMfssbgf;
import dom.sun.jmx.snmp.SnmpPduFbdtory;
import dom.sun.jmx.snmp.SnmpPduBulk;
import dom.sun.jmx.snmp.SnmpPduPbdkft;
import dom.sun.jmx.snmp.SnmpPduRfqufst;
import dom.sun.jmx.snmp.SnmpPduTrbp;
import dom.sun.jmx.snmp.SnmpVbluf;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpVbrBindList;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpTooBigExdfption;
import dom.sun.jmx.snmp.SnmpDbtbTypfEnums;

// RI imports
//
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_ADAPTOR_LOGGER;

// SNMP runtimf import
//
import dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt;
import dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory;
//import dom.sun.jmx.snmp.IPAdl.IPAdl;
import dom.sun.jmx.snmp.InftAddrfssAdl;


dlbss SnmpRfqufstHbndlfr fxtfnds ClifntHbndlfr implfmfnts SnmpDffinitions {

    privbtf trbnsifnt DbtbgrbmSodkft       sodkft = null ;
    privbtf trbnsifnt DbtbgrbmPbdkft       pbdkft = null ;
    privbtf trbnsifnt Vfdtor<SnmpMibAgfnt> mibs = null ;

    /**
     * Contbins tif list of sub-rfqufsts bssodibtfd to tif durrfnt rfqufst.
     */
    privbtf trbnsifnt Hbsitbblf<SnmpMibAgfnt, SnmpSubRfqufstHbndlfr> subs = null;

    /**
     * Rfffrfndf on tif MIBS
     */
    privbtf trbnsifnt SnmpMibTrff root;

    privbtf trbnsifnt InftAddrfssAdl      ipbdl = null ;
    privbtf trbnsifnt SnmpPduFbdtory      pduFbdtory = null ;
    privbtf trbnsifnt SnmpUsfrDbtbFbdtory usfrDbtbFbdtory = null ;
    privbtf trbnsifnt SnmpAdbptorSfrvfr bdbptor = null;
    /**
     * Full donstrudtor
     */
    publid SnmpRfqufstHbndlfr(SnmpAdbptorSfrvfr sfrvfr, int id,
                              DbtbgrbmSodkft s, DbtbgrbmPbdkft p,
                              SnmpMibTrff trff, Vfdtor<SnmpMibAgfnt> m,
                              InftAddrfssAdl b,
                              SnmpPduFbdtory fbdtory,
                              SnmpUsfrDbtbFbdtory dbtbFbdtory,
                              MBfbnSfrvfr f, ObjfdtNbmf n)
    {
        supfr(sfrvfr, id, f, n);

        // Nffd b rfffrfndf on SnmpAdbptorSfrvfr for gftNfxt & gftBulk,
        // in dbsf of oid fqublity (mib ovfrlbpping).
        //
        bdbptor = sfrvfr;
        sodkft = s;
        pbdkft = p;
        root= trff;
        mibs = nfw Vfdtor<>(m);
        subs= nfw Hbsitbblf<>(mibs.sizf());
        ipbdl = b;
        pduFbdtory = fbdtory ;
        usfrDbtbFbdtory = dbtbFbdtory ;
        //tirfbd.stbrt();
    }

    /**
     * Trfbt tif rfqufst bvbilbblf in 'pbdkft' bnd sfnd tif rfsult
     * bbdk to tif dlifnt.
     * Notf: wf ovfrwritf 'pbdkft' witi tif rfsponsf bytfs.
     */
    @Ovfrridf
    publid void doRun() {

        // Trbdf tif input pbdkft
        //
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                    "doRun","Pbdkft rfdfivfd:\n" +
                    SnmpMfssbgf.dumpHfxBufffr(pbdkft.gftDbtb(), 0, pbdkft.gftLfngti()));
        }

        // Lft's build tif rfsponsf pbdkft
        //
        DbtbgrbmPbdkft rfspPbdkft = mbkfRfsponsfPbdkft(pbdkft) ;

        // Trbdf tif output pbdkft
        //
        if ((SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) && (rfspPbdkft != null)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                    "doRun","Pbdkft to bf sfnt:\n" +
                    SnmpMfssbgf.dumpHfxBufffr(rfspPbdkft.gftDbtb(), 0, rfspPbdkft.gftLfngti()));
        }

        // Sfnd tif rfsponsf pbdkft if bny
        //
        if (rfspPbdkft != null) {
            try {
                sodkft.sfnd(rfspPbdkft) ;
            } dbtdi (SodkftExdfption f) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    if (f.gftMfssbgf().fqubls(IntfrruptSysCbllMsg)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                            "doRun", "intfrruptfd");
                    } flsf {
                      SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                            "doRun", "I/O fxdfption", f);
                    }
                }
            } dbtdi(IntfrruptfdIOExdfption f) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                        "doRun", "intfrruptfd");
                }
            } dbtdi(Exdfption f) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                        "doRun", "fbilurf wifn sfnding rfsponsf", f);
                }
            }
        }
    }

    /**
     * Hfrf wf mbkf b rfsponsf pbdkft from b rfqufst pbdkft.
     * Wf rfturn null if tifrf no rfsponsf pbdkft to sfnt.
     */
    privbtf DbtbgrbmPbdkft mbkfRfsponsfPbdkft(DbtbgrbmPbdkft rfqPbdkft) {
        DbtbgrbmPbdkft rfspPbdkft = null ;

        // Trbnsform tif rfqufst pbdkft into b rfqufst SnmpMfssbgf
        //
        SnmpMfssbgf rfqMsg = nfw SnmpMfssbgf() ;
        try {
            rfqMsg.dfdodfMfssbgf(rfqPbdkft.gftDbtb(), rfqPbdkft.gftLfngti()) ;
            rfqMsg.bddrfss = rfqPbdkft.gftAddrfss() ;
            rfqMsg.port = rfqPbdkft.gftPort() ;
        }
        dbtdi(SnmpStbtusExdfption x) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                    "mbkfRfsponsfPbdkft", "pbdkft dfdoding fbilfd", x);
            }
            rfqMsg = null ;
            ((SnmpAdbptorSfrvfr)bdbptorSfrvfr).indSnmpInASNPbrsfErrs(1) ;
        }

        // Mbkf tif rfsponsf SnmpMfssbgf if bny
        //
        SnmpMfssbgf rfspMsg = null ;
        if (rfqMsg != null) {
            rfspMsg = mbkfRfsponsfMfssbgf(rfqMsg) ;
        }

        // Try to trbnsform tif rfsponsf SnmpMfssbgf into rfsponsf pbdkft.
        // NOTE: wf ovfrwritf tif rfqufst pbdkft.
        //
        if (rfspMsg != null) {
            try {
                rfqPbdkft.sftLfngti(rfspMsg.fndodfMfssbgf(rfqPbdkft.gftDbtb())) ;
                rfspPbdkft = rfqPbdkft ;
            }
            dbtdi(SnmpTooBigExdfption x) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                        "mbkfRfsponsfPbdkft", "rfsponsf mfssbgf is too big");
                }
                try {
                    rfspMsg = nfwTooBigMfssbgf(rfqMsg) ;
                    rfqPbdkft.sftLfngti(rfspMsg.fndodfMfssbgf(rfqPbdkft.gftDbtb())) ;
                    rfspPbdkft = rfqPbdkft ;
                }
                dbtdi(SnmpTooBigExdfption xx) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                            "mbkfRfsponsfPbdkft", "'too big' is 'too big' !!!");
                    }
                    bdbptor.indSnmpSilfntDrops(1);
                }
            }
        }

        rfturn rfspPbdkft ;
    }

    /**
     * Hfrf wf mbkf b rfsponsf mfssbgf from b rfqufst mfssbgf.
     * Wf rfturn null if tifrf is no mfssbgf to rfply.
     */
    privbtf SnmpMfssbgf mbkfRfsponsfMfssbgf(SnmpMfssbgf rfqMsg) {
        SnmpMfssbgf rfspMsg = null ;

        // Trbnsform tif rfqufst mfssbgf into b rfqufst pdu
        //
        SnmpPduPbdkft rfqPdu;
        Objfdt usfrDbtb = null;
        try {
            rfqPdu = (SnmpPduPbdkft)pduFbdtory.dfdodfSnmpPdu(rfqMsg) ;
            if (rfqPdu != null && usfrDbtbFbdtory != null)
                usfrDbtb = usfrDbtbFbdtory.bllodbtfUsfrDbtb(rfqPdu);
        }
        dbtdi(SnmpStbtusExdfption x) {
            rfqPdu = null ;
            SnmpAdbptorSfrvfr snmpSfrvfr = (SnmpAdbptorSfrvfr)bdbptorSfrvfr ;
            snmpSfrvfr.indSnmpInASNPbrsfErrs(1) ;
            if (x.gftStbtus()== SnmpDffinitions.snmpWrongSnmpVfrsion)
                snmpSfrvfr.indSnmpInBbdVfrsions(1) ;
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                    "mbkfRfsponsfMfssbgf", "mfssbgf dfdoding fbilfd", x);
            }
        }

        // Mbkf tif rfsponsf pdu if bny
        //
        SnmpPduPbdkft rfspPdu = null ;
        if (rfqPdu != null) {
            rfspPdu = mbkfRfsponsfPdu(rfqPdu,usfrDbtb) ;
            try {
                if (usfrDbtbFbdtory != null)
                    usfrDbtbFbdtory.rflfbsfUsfrDbtb(usfrDbtb,rfspPdu);
            } dbtdi (SnmpStbtusExdfption x) {
                rfspPdu = null;
            }
        }

        // Try to trbnsform tif rfsponsf pdu into b rfsponsf mfssbgf if bny
        //
        if (rfspPdu != null) {
            try {
                rfspMsg = (SnmpMfssbgf)pduFbdtory.
                    fndodfSnmpPdu(rfspPdu, pbdkft.gftDbtb().lfngti) ;
            }
            dbtdi(SnmpStbtusExdfption x) {
                rfspMsg = null ;
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                        "mbkfRfsponsfMfssbgf", "fbilurf wifn fndoding tif rfsponsf mfssbgf", x);
                }
            }
            dbtdi(SnmpTooBigExdfption x) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                        "mbkfRfsponsfMfssbgf", "rfsponsf mfssbgf is too big");
                }

                try {
                    // if tif PDU is too smbll, wiy siould wf try to do
                    // rfdovfry ?
                    //
                    if (pbdkft.gftDbtb().lfngti <=32)
                        tirow x;
                    int pos= x.gftVbrBindCount();
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                            "mbkfRfsponsfMfssbgf", "fbil on flfmfnt" + pos);
                    }
                    int old;
                    wiilf (truf) {
                        try {
                            rfspPdu = rfdudfRfsponsfPdu(rfqPdu, rfspPdu, pos) ;
                            rfspMsg = (SnmpMfssbgf)pduFbdtory.
                                fndodfSnmpPdu(rfspPdu,
                                              pbdkft.gftDbtb().lfngti -32) ;
                            brfbk;
                        } dbtdi (SnmpTooBigExdfption xx) {
                            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                                    "mbkfRfsponsfMfssbgf", "rfsponsf mfssbgf is still too big");
                            }
                            old= pos;
                            pos= xx.gftVbrBindCount();
                            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                                    "mbkfRfsponsfMfssbgf","fbil on flfmfnt" + pos);
                            }
                            if (pos == old) {
                                // wf dbn not go bny furtifr in trying to
                                // rfdudf tif mfssbgf !
                                //
                                tirow xx;
                            }
                        }
                    }// fnd of loop
                } dbtdi(SnmpStbtusExdfption xx) {
                    rfspMsg = null ;
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                           "mbkfRfsponsfMfssbgf", "fbilurf wifn fndoding tif rfsponsf mfssbgf", xx);
                    }
                }
                dbtdi(SnmpTooBigExdfption xx) {
                    try {
                        rfspPdu = nfwTooBigPdu(rfqPdu) ;
                        rfspMsg = (SnmpMfssbgf)pduFbdtory.
                            fndodfSnmpPdu(rfspPdu, pbdkft.gftDbtb().lfngti) ;
                    }
                    dbtdi(SnmpTooBigExdfption xxx) {
                        rfspMsg = null ;
                        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                               "mbkfRfsponsfMfssbgf", "'too big' is 'too big' !!!");
                        }
                        bdbptor.indSnmpSilfntDrops(1);
                    }
                    dbtdi(Exdfption xxx) {
                        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                               "mbkfRfsponsfMfssbgf", "Got unfxpfdtfd fxdfption", xxx);
                        }
                        rfspMsg = null ;
                    }
                }
                dbtdi(Exdfption xx) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                           "mbkfRfsponsfMfssbgf", "Got unfxpfdtfd fxdfption", xx);
                    }
                    rfspMsg = null ;
                }
            }
        }
        rfturn rfspMsg ;
    }

    /**
     * Hfrf wf mbkf b rfsponsf pdu from b rfqufst pdu.
     * Wf rfturn null if tifrf is no pdu to rfply.
     */
    privbtf SnmpPduPbdkft mbkfRfsponsfPdu(SnmpPduPbdkft rfqPdu,
                                          Objfdt usfrDbtb) {

        SnmpAdbptorSfrvfr snmpSfrvfr = (SnmpAdbptorSfrvfr)bdbptorSfrvfr ;
        SnmpPduPbdkft rfspPdu = null ;

        snmpSfrvfr.updbtfRfqufstCountfrs(rfqPdu.typf) ;
        if (rfqPdu.vbrBindList != null)
            snmpSfrvfr.updbtfVbrCountfrs(rfqPdu.typf,
                                         rfqPdu.vbrBindList.lfngti) ;

        if (difdkPduTypf(rfqPdu)) {
            rfspPdu = difdkAdl(rfqPdu) ;
            if (rfspPdu == null) { // rfqPdu is bddfptfd by ACLs
                if (mibs.sizf() < 1) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                           "mbkfRfsponsfPdu", "Rfqufst " + rfqPdu.rfqufstId +
                           " rfdfivfd but no MIB rfgistfrfd.");
                    }
                    rfturn mbkfNoMibErrorPdu((SnmpPduRfqufst)rfqPdu, usfrDbtb);
                }
                switdi(rfqPdu.typf) {
                dbsf SnmpPduPbdkft.pduGftRfqufstPdu:
                dbsf SnmpPduPbdkft.pduGftNfxtRfqufstPdu:
                dbsf SnmpPduPbdkft.pduSftRfqufstPdu:
                    rfspPdu = mbkfGftSftRfsponsfPdu((SnmpPduRfqufst)rfqPdu,
                                                    usfrDbtb) ;
                    brfbk ;

                dbsf SnmpPduPbdkft.pduGftBulkRfqufstPdu:
                    rfspPdu = mbkfGftBulkRfsponsfPdu((SnmpPduBulk)rfqPdu,
                                                     usfrDbtb) ;
                    brfbk ;
                }
            }
            flsf { // rfqPdu is rfjfdtfd by ACLs
                // rfspPdu dontbins tif frror rfsponsf to bf sfnt.
                // Wf sfnd tiis rfsponsf only if butiRfsEnbblfd is truf.
                if (!snmpSfrvfr.gftAutiRfspEnbblfd()) { // No rfsponsf siould bf sfnt
                    rfspPdu = null ;
                }
                if (snmpSfrvfr.gftAutiTrbpEnbblfd()) { // A trbp must bf sfnt
                    try {
                        snmpSfrvfr.snmpV1Trbp(SnmpPduTrbp.
                                              trbpAutifntidbtionFbilurf, 0,
                                              nfw SnmpVbrBindList()) ;
                    }
                    dbtdi(Exdfption x) {
                        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                               "mbkfRfsponsfPdu", "Fbilurf wifn sfnding butifntidbtion trbp", x);
                        }
                    }
                }
            }
        }
        rfturn rfspPdu ;
    }

    //
    // Gfnfrbtfs b rfsponsf pbdkft, filling tif vblufs in tif
    // vbrbindlist witi onf of fndOfMibVifw, noSudiObjfdt, noSudiInstbndf
    // bddording to tif vbluf of <dodf>stbtus</dodf>
    //
    // @pbrbm stbtusTbg siould bf onf of:
    //        <li>SnmpDbtbTypfEnums.frrEndOfMibVifwTbg</li>
    //        <li>SnmpDbtbTypfEnums.frrNoSudiObjfdtTbg</li>
    //        <li>SnmpDbtbTypfEnums.frrNoSudiInstbndfTbg</li>
    //
    SnmpPduPbdkft mbkfErrorVbrbindPdu(SnmpPduPbdkft rfq, int stbtusTbg) {

        finbl SnmpVbrBind[] vblist = rfq.vbrBindList;
        finbl int lfngti = vblist.lfngti;

        switdi (stbtusTbg) {
        dbsf SnmpDbtbTypfEnums.frrEndOfMibVifwTbg:
            for (int i=0 ; i<lfngti ; i++)
                vblist[i].vbluf = SnmpVbrBind.fndOfMibVifw;
            brfbk;
        dbsf SnmpDbtbTypfEnums.frrNoSudiObjfdtTbg:
            for (int i=0 ; i<lfngti ; i++)
                vblist[i].vbluf = SnmpVbrBind.noSudiObjfdt;
            brfbk;
        dbsf SnmpDbtbTypfEnums.frrNoSudiInstbndfTbg:
            for (int i=0 ; i<lfngti ; i++)
                vblist[i].vbluf = SnmpVbrBind.noSudiInstbndf;
            brfbk;
        dffbult:
            rfturn nfwErrorRfsponsfPdu(rfq,snmpRspGfnErr,1);
        }
        rfturn nfwVblidRfsponsfPdu(rfq,vblist);
    }

    // Gfnfrbtfs bn bppropribtf rfsponsf wifn no mib is rfgistfrfd in
    // tif bdbptor.
    //
    // <li>If tif vfrsion is V1:</li>
    // <ul><li>Gfnfrbtfs b NoSudiNbmf frror V1 rfsponsf PDU</li></ul>
    // <li>If tif vfrsion is V2:</li>
    // <ul><li>If tif rfqufst is b GET, fills tif vbrbind list witi
    //         NoSudiObjfdt's</li>
    //     <li>If tif rfqufst is b GET-NEXT/GET-BULK, fills tif vbrbind
    //         list witi EndOfMibVifw's</li>
    //     <li>If tif rfqufst is b SET, gfnfrbtfs b NoAddfss frror V2
    //          rfsponsf PDU</li>
    // </ul>
    //
    //
    SnmpPduPbdkft mbkfNoMibErrorPdu(SnmpPduRfqufst rfq, Objfdt usfrDbtb) {
        // Tifrf is no bgfnt rfgistfrfd
        //
        if (rfq.vfrsion == SnmpDffinitions.snmpVfrsionOnf) {
            // Vfrsion 1: => NoSudiNbmf
            rfturn
                nfwErrorRfsponsfPdu(rfq,snmpRspNoSudiNbmf,1);
        } flsf if (rfq.vfrsion == SnmpDffinitions.snmpVfrsionTwo) {
            // Vfrsion 2: => dfpfnds on PDU typf
            switdi (rfq.typf) {
            dbsf pduSftRfqufstPdu :
            dbsf pduWblkRfqufst :
                // SET rfqufst => NoAddfss
                rfturn
                    nfwErrorRfsponsfPdu(rfq,snmpRspNoAddfss,1);
            dbsf pduGftRfqufstPdu :
                // GET rfqufst => NoSudiObjfdt
                rfturn
                    mbkfErrorVbrbindPdu(rfq,SnmpDbtbTypfEnums.
                                        frrNoSudiObjfdtTbg);
            dbsf pduGftNfxtRfqufstPdu :
            dbsf pduGftBulkRfqufstPdu :
                // GET-NEXT or GET-BULK => EndOfMibVifw
                rfturn
                    mbkfErrorVbrbindPdu(rfq,SnmpDbtbTypfEnums.
                                        frrEndOfMibVifwTbg);
            dffbult:
            }
        }
        // Somftiing wrong ifrf: => snmpRspGfnErr
        rfturn nfwErrorRfsponsfPdu(rfq,snmpRspGfnErr,1);
    }

    /**
     * Hfrf wf mbkf tif rfsponsf pdu from b gft/sft rfqufst pdu.
     * At tiis lfvfl, tif rfsult is nfvfr null.
     */
    privbtf SnmpPduPbdkft mbkfGftSftRfsponsfPdu(SnmpPduRfqufst rfq,
                                                Objfdt usfrDbtb) {

        // Crfbtf tif trifbd group spfdifid for ibndling sub-rfqufsts
        // bssodibtfd to tif durrfnt rfqufst. Usf tif invokf id
        //
        // Nidf idfb to usf b tirfbd group on b rfqufst bbsis.
        // Howfvfr tif impbdt on pfrformbndf is tfrriblf !
        // tifGroup= nfw TirfbdGroup(tirfbd.gftTirfbdGroup(),
        //                "rfqufst " + String.vblufOf(rfq.rfqufstId));

        // Lft's build tif vbrBindList for tif rfsponsf pdu
        //

        if (rfq.vbrBindList == null) {
            // Good ! Lft's mbkf b full rfsponsf pdu.
            //
            rfturn nfwVblidRfsponsfPdu(rfq, null) ;
        }

        // First wf nffd to split tif rfqufst into subrfqufsts
        //
        splitRfqufst(rfq);
        int nbSubRfqufst= subs.sizf();
        if (nbSubRfqufst == 1)
            rfturn turboProdfssingGftSft(rfq,usfrDbtb);


        // Exfdutf bll tif subrfqufsts rfsulting from tif split of tif
        // vbrbind list.
        //
        SnmpPduPbdkft rfsult= fxfdutfSubRfqufst(rfq,usfrDbtb);
        if (rfsult != null)
            // It mfbns tibt bn frror oddurrfd. Tif frror is blrfbdy
            // formbttfd by tif fxfdutfSubRfqufst
            // mftiod.
            rfturn rfsult;

        // So fbr so good. So wf nffd to dondbtfnbtf bll tif bnswfrs.
        //
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
               "mbkfGftSftRfsponsfPdu",
               "Build tif unififd rfsponsf for rfqufst " + rfq.rfqufstId);
        }
        rfturn mfrgfRfsponsfs(rfq);
    }

    /**
     * Tif mftiod runs bll tif sub-rfqufsts bssodibtfd to tif durrfnt
     * instbndf of SnmpRfqufstHbndlfr.
     */
    privbtf SnmpPduPbdkft fxfdutfSubRfqufst(SnmpPduPbdkft rfq,
                                            Objfdt usfrDbtb) {

        int frrorStbtus = SnmpDffinitions.snmpRspNoError ;

        int i;
        // If it's b sft rfqufst, wf must first difdk bny vbrBind
        //
        if (rfq.typf == pduSftRfqufstPdu) {

            i=0;
            for(Enumfrbtion<SnmpSubRfqufstHbndlfr> f= subs.flfmfnts(); f.ibsMorfElfmfnts() ; i++) {
                // Indidbtf to tif sub rfqufst tibt b difdk must bf invokfd ...
                // OK wf siould ibvf dffinfd out own tbg for tibt !
                //
                SnmpSubRfqufstHbndlfr sub= f.nfxtElfmfnt();
                sub.sftUsfrDbtb(usfrDbtb);
                sub.typf= pduWblkRfqufst;

                sub.run();

                sub.typf= pduSftRfqufstPdu;

                if (sub.gftErrorStbtus() != SnmpDffinitions.snmpRspNoError) {
                    // No point to go bny furtifr.
                    //
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                           "fxfdutfSubRfqufst", "bn frror oddurs");
                    }

                    rfturn nfwErrorRfsponsfPdu(rfq, frrorStbtus,
                                               sub.gftErrorIndfx() + 1) ;
                }
            }
        }// fnd prodfssing difdk opfrbtion for b sft PDU.

        // Lft's stbrt tif sub-rfqufsts.
        //
        i=0;
        for(Enumfrbtion<SnmpSubRfqufstHbndlfr> f= subs.flfmfnts(); f.ibsMorfElfmfnts() ;i++) {
            SnmpSubRfqufstHbndlfr sub= f.nfxtElfmfnt();
        /* NPCTE fix for bugId 4492741, fsd 0, 16-August 2001 */
            sub.sftUsfrDbtb(usfrDbtb);
        /* fnd of NPCTE fix for bugId 4492741 */

            sub.run();

            if (sub.gftErrorStbtus() != SnmpDffinitions.snmpRspNoError) {
                // No point to go bny furtifr.
                //
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                       "fxfdutfSubRfqufst", "bn frror oddurs");
                }

                rfturn nfwErrorRfsponsfPdu(rfq, frrorStbtus,
                                           sub.gftErrorIndfx() + 1) ;
            }
        }

        // fvfrytiing is ok
        //
        rfturn null;
    }

    /**
     * Optimizf wifn tifrf is only onf sub rfqufst
     */
    privbtf SnmpPduPbdkft turboProdfssingGftSft(SnmpPduRfqufst rfq,
                                                Objfdt usfrDbtb) {

        int frrorStbtus;
        SnmpSubRfqufstHbndlfr sub = subs.flfmfnts().nfxtElfmfnt();
        sub.sftUsfrDbtb(usfrDbtb);

        // Indidbtf to tif sub rfqufst tibt b difdk must bf invokfd ...
        // OK wf siould ibvf dffinfd out own tbg for tibt !
        //
        if (rfq.typf == SnmpDffinitions.pduSftRfqufstPdu) {
            sub.typf= pduWblkRfqufst;
            sub.run();
            sub.typf= pduSftRfqufstPdu;

            // Cifdk tif frror stbtus.
            //
            frrorStbtus= sub.gftErrorStbtus();
            if (frrorStbtus != SnmpDffinitions.snmpRspNoError) {
                // No point to go bny furtifr.
                //
                rfturn nfwErrorRfsponsfPdu(rfq, frrorStbtus,
                                           sub.gftErrorIndfx() + 1) ;
            }
        }

        // prodfss tif opfrbtion
        //

        sub.run();
        frrorStbtus= sub.gftErrorStbtus();
        if (frrorStbtus != SnmpDffinitions.snmpRspNoError) {
            // No point to go bny furtifr.
            //
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                   "turboProdfssingGftSft", "bn frror oddurs");
            }
            int rfblIndfx= sub.gftErrorIndfx() + 1;
            rfturn nfwErrorRfsponsfPdu(rfq, frrorStbtus, rfblIndfx) ;
        }

        // So fbr so good. So wf nffd to dondbtfnbtf bll tif bnswfrs.
        //

        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
               "turboProdfssingGftSft",  "build tif unififd rfsponsf for rfqufst "
                + rfq.rfqufstId);
        }
        rfturn mfrgfRfsponsfs(rfq);
    }

    /**
     * Hfrf wf mbkf tif rfsponsf pdu for b bulk rfqufst.
     * At tiis lfvfl, tif rfsult is nfvfr null.
     */
    privbtf SnmpPduPbdkft mbkfGftBulkRfsponsfPdu(SnmpPduBulk rfq,
                                                 Objfdt usfrDbtb) {

        SnmpVbrBind[] rfspVbrBindList;

        // RFC 1905, Sfdtion 4.2.3, p14
        int L = rfq.vbrBindList.lfngti ;
        int N = Mbti.mbx(Mbti.min(rfq.nonRfpfbtfrs, L), 0) ;
        int M = Mbti.mbx(rfq.mbxRfpftitions, 0) ;
        int R = L - N ;

        if (rfq.vbrBindList == null) {
            // Good ! Lft's mbkf b full rfsponsf pdu.
            //
            rfturn nfwVblidRfsponsfPdu(rfq, null) ;
        }

        // Split tif rfqufst into subrfqufsts.
        //
        splitBulkRfqufst(rfq, N, M, R);
        SnmpPduPbdkft rfsult= fxfdutfSubRfqufst(rfq,usfrDbtb);
        if (rfsult != null)
            rfturn rfsult;

        rfspVbrBindList= mfrgfBulkRfsponsfs(N + (M * R));

        // Now wf rfmovf usflfss trbiling fndOfMibVifw.
        //
        int m2 ; // rfspVbrBindList[m2] itfm bnd nfxt brf going to bf rfmovfd
        int t = rfspVbrBindList.lfngti ;
        wiilf ((t > N) && (rfspVbrBindList[t-1].
                           vbluf.fqubls(SnmpVbrBind.fndOfMibVifw))) {
            t-- ;
        }
        if (t == N)
            m2 = N + R ;
        flsf
            m2 = N + ((t -1 -N) / R + 2) * R ; // Trivibl, of doursf...
        if (m2 < rfspVbrBindList.lfngti) {
            SnmpVbrBind[] trundbtfdList = nfw SnmpVbrBind[m2] ;
            for (int i = 0 ; i < m2 ; i++) {
                trundbtfdList[i] = rfspVbrBindList[i] ;
            }
            rfspVbrBindList = trundbtfdList ;
        }

        // Good ! Lft's mbkf b full rfsponsf pdu.
        //
        rfturn nfwVblidRfsponsfPdu(rfq, rfspVbrBindList) ;
    }

    /**
     * Cifdk tif typf of tif pdu: only tif gft/sft/bulk rfqufst
     * brf bddfptfd.
     */
    privbtf boolfbn difdkPduTypf(SnmpPduPbdkft pdu) {

        boolfbn rfsult;

        switdi(pdu.typf) {

        dbsf SnmpDffinitions.pduGftRfqufstPdu:
        dbsf SnmpDffinitions.pduGftNfxtRfqufstPdu:
        dbsf SnmpDffinitions.pduSftRfqufstPdu:
        dbsf SnmpDffinitions.pduGftBulkRfqufstPdu:
            rfsult = truf ;
            brfbk;

        dffbult:
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                   "difdkPduTypf", "dbnnot rfspond to tiis kind of PDU");
            }
            rfsult = fblsf ;
            brfbk;
        }

        rfturn rfsult ;
    }

    /**
     * Cifdk if tif spfdififd pdu is donform to tif ACL.
     * Tiis mftiod rfturns null if tif pdu is ok. If not, it rfturns
     * tif rfsponsf pdu to bf rfplifd.
     */
    privbtf SnmpPduPbdkft difdkAdl(SnmpPduPbdkft pdu) {
        SnmpPduPbdkft rfsponsf = null ;
        String dommunity = nfw String(pdu.dommunity) ;

        // Wf difdk tif pdu typf bnd drfbtf bn frror rfsponsf if
        // tif difdk fbilfd.
        //
        if (ipbdl != null) {
            if (pdu.typf == SnmpDffinitions.pduSftRfqufstPdu) {
                if (!ipbdl.difdkWritfPfrmission(pdu.bddrfss, dommunity)) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                           "difdkAdl", "sfndfr is " + pdu.bddrfss +
                              " witi " + dommunity +". Sfndfr ibs no writf pfrmission");
                    }
                    int frr = SnmpSubRfqufstHbndlfr.
                        mbpErrorStbtus(SnmpDffinitions.
                                       snmpRspAutiorizbtionError,
                                       pdu.vfrsion, pdu.typf);
                    rfsponsf = nfwErrorRfsponsfPdu(pdu, frr, 0) ;
                }
                flsf {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                           "difdkAdl", "sfndfr is " + pdu.bddrfss +
                              " witi " + dommunity +". Sfndfr ibs writf pfrmission");
                    }
                }
            }
            flsf {
                if (!ipbdl.difdkRfbdPfrmission(pdu.bddrfss, dommunity)) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                           "difdkAdl", "sfndfr is " + pdu.bddrfss +
                              " witi " + dommunity +". Sfndfr ibs no rfbd pfrmission");
                    }
                    int frr = SnmpSubRfqufstHbndlfr.
                        mbpErrorStbtus(SnmpDffinitions.
                                       snmpRspAutiorizbtionError,
                                       pdu.vfrsion, pdu.typf);
                    rfsponsf = nfwErrorRfsponsfPdu(pdu,
                                                   frr,
                                                   0);
                    SnmpAdbptorSfrvfr snmpSfrvfr =
                        (SnmpAdbptorSfrvfr)bdbptorSfrvfr;
                    snmpSfrvfr.updbtfErrorCountfrs(SnmpDffinitions.
                                                   snmpRspNoSudiNbmf);
                }
                flsf {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                           "difdkAdl", "sfndfr is " + pdu.bddrfss +
                              " witi " + dommunity +". Sfndfr ibs rfbd pfrmission");
                    }
                }
            }
        }

        // If tif rfsponsf is not null, tiis mfbns tif pdu is rfjfdtfd.
        // So lft's updbtf tif stbtistids.
        //
        if (rfsponsf != null) {
            SnmpAdbptorSfrvfr snmpSfrvfr = (SnmpAdbptorSfrvfr)bdbptorSfrvfr ;
            snmpSfrvfr.indSnmpInBbdCommunityUsfs(1) ;
            if (ipbdl.difdkCommunity(dommunity) == fblsf)
                snmpSfrvfr.indSnmpInBbdCommunityNbmfs(1) ;
        }

        rfturn rfsponsf ;
    }

    /**
     * Mbkf b rfsponsf pdu witi tif spfdififd frror stbtus bnd indfx.
     * NOTE: tif rfsponsf pdu sibrf its vbrBindList witi tif rfqufst pdu.
     */
    privbtf SnmpPduRfqufst nfwVblidRfsponsfPdu(SnmpPduPbdkft rfqPdu,
                                               SnmpVbrBind[] vbrBindList) {
        SnmpPduRfqufst rfsult = nfw SnmpPduRfqufst() ;

        rfsult.bddrfss = rfqPdu.bddrfss ;
        rfsult.port = rfqPdu.port ;
        rfsult.vfrsion = rfqPdu.vfrsion ;
        rfsult.dommunity = rfqPdu.dommunity ;
        rfsult.typf = SnmpPduRfqufst.pduGftRfsponsfPdu ;
        rfsult.rfqufstId = rfqPdu.rfqufstId ;
        rfsult.frrorStbtus = SnmpDffinitions.snmpRspNoError ;
        rfsult.frrorIndfx = 0 ;
        rfsult.vbrBindList = vbrBindList ;

        ((SnmpAdbptorSfrvfr)bdbptorSfrvfr).
            updbtfErrorCountfrs(rfsult.frrorStbtus) ;

        rfturn rfsult ;
    }

    /**
     * Mbkf b rfsponsf pdu witi tif spfdififd frror stbtus bnd indfx.
     * NOTE: tif rfsponsf pdu sibrf its vbrBindList witi tif rfqufst pdu.
     */
    privbtf SnmpPduRfqufst nfwErrorRfsponsfPdu(SnmpPduPbdkft rfq,int s,int i) {
        SnmpPduRfqufst rfsult = nfwVblidRfsponsfPdu(rfq, null) ;
        rfsult.frrorStbtus = s ;
        rfsult.frrorIndfx = i ;
        rfsult.vbrBindList = rfq.vbrBindList ;

        ((SnmpAdbptorSfrvfr)bdbptorSfrvfr).
            updbtfErrorCountfrs(rfsult.frrorStbtus) ;

        rfturn rfsult ;
    }

    privbtf SnmpMfssbgf nfwTooBigMfssbgf(SnmpMfssbgf rfqMsg)
        tirows SnmpTooBigExdfption {
        SnmpMfssbgf rfsult = null ;
        SnmpPduPbdkft rfqPdu;

        try {
            rfqPdu = (SnmpPduPbdkft)pduFbdtory.dfdodfSnmpPdu(rfqMsg) ;
            if (rfqPdu != null) {
                SnmpPduPbdkft rfspPdu = nfwTooBigPdu(rfqPdu) ;
                rfsult = (SnmpMfssbgf)pduFbdtory.
                    fndodfSnmpPdu(rfspPdu, pbdkft.gftDbtb().lfngti) ;
            }
        }
        dbtdi(SnmpStbtusExdfption x) {
            // Tiis siould not oddur bfdbusf dfdodfIndomingRfqufst ibs normblly
            // bffn suddfssfully dbllfd bfforf.
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                   "nfwTooBigMfssbgf", "Intfrnbl frror", x);
            }
            tirow nfw IntfrnblError(x) ;
        }

        rfturn rfsult ;
    }

    privbtf SnmpPduPbdkft nfwTooBigPdu(SnmpPduPbdkft rfq) {
        SnmpPduRfqufst rfsult =
            nfwErrorRfsponsfPdu(rfq, SnmpDffinitions.snmpRspTooBig, 0) ;
        rfsult.vbrBindList = null ;
        rfturn rfsult ;
    }

    privbtf SnmpPduPbdkft rfdudfRfsponsfPdu(SnmpPduPbdkft rfq,
                                            SnmpPduPbdkft rfsp,
                                            int bddfptfdVbCount)
        tirows SnmpTooBigExdfption {

        // Rfdudtion dbn bf bttfmptfd only on bulk rfsponsf
        //
        if (rfq.typf != SnmpPduPbdkft.pduGftBulkRfqufstPdu) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                   "rfdudfRfsponsfPdu", "dbnnot rfmovf bnytiing");
            }
            tirow nfw SnmpTooBigExdfption(bddfptfdVbCount) ;
        }

        // Wf'rf going to rfdudf tif vbrbind list.
        // First dftfrminf wiidi itfms siould bf rfmovfd.
        // Nfxt duplidbtf bnd rfplbdf tif fxisting list by tif rfdudfd onf.
        //
        // bddfptfdVbCount is tif numbfr of vbrbind wiidi ibvf bffn
        // suddfssfully fndodfd bfforf rfbdiing bufffrSizf:
        //   * wifn it is >= 2, wf split tif vbrbindlist bt tiis
        //     position (-1 to bf sbff),
        //   * wifn it is 1, wf only put onf (big?) itfm in tif vbrbindlist
        //   * wifn it is 0 (in fbdt, bddfptfdVbCount is not bvbilbblf),
        //     wf split tif vbrbindlist by 2.
        //
        int vbCount;
        if (bddfptfdVbCount >= 3)
            vbCount = Mbti.min(bddfptfdVbCount - 1, rfsp.vbrBindList.lfngti) ;
        flsf if (bddfptfdVbCount == 1)
            vbCount = 1 ;
        flsf // bddfptfdCount == 0 if it is unknown
            vbCount = rfsp.vbrBindList.lfngti / 2 ;

        if (vbCount < 1) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                   "rfdudfRfsponsfPdu", "dbnnot rfmovf bnytiing");
            }
            tirow nfw SnmpTooBigExdfption(bddfptfdVbCount) ;
        }
        flsf {
            SnmpVbrBind[] nfwVbList = nfw SnmpVbrBind[vbCount] ;
            for (int i = 0 ; i < vbCount ; i++) {
                nfwVbList[i] = rfsp.vbrBindList[i] ;
            }
            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, dbgTbg,
                   "rfdudfRfsponsfPdu", (rfsp.vbrBindList.lfngti - nfwVbList.lfngti) +
                    " itfms ibvf bffn rfmovfd");
            }
            rfsp.vbrBindList = nfwVbList ;
        }

        rfturn rfsp ;
    }

    /**
     * Tif mftiod tbkfs tif indoming rfqufsts bnd split it into subrfqufsts.
     */
    privbtf void splitRfqufst(SnmpPduRfqufst rfq) {

        int nbAgfnts= mibs.sizf();
        SnmpMibAgfnt bgfnt = mibs.firstElfmfnt();
        if (nbAgfnts == 1) {
            // Tbkf bll tif oids dontbinfd in tif rfqufst bnd
            //
            subs.put(bgfnt, nfw SnmpSubRfqufstHbndlfr(bgfnt, rfq, truf));
            rfturn;
        }

        // For tif gft nfxt opfrbtion wf brf going to sfnd tif vbrbind list
        // to bll bgfnts
        //
        if (rfq.typf == pduGftNfxtRfqufstPdu) {
            for(Enumfrbtion<SnmpMibAgfnt> f= mibs.flfmfnts(); f.ibsMorfElfmfnts(); ) {
                finbl SnmpMibAgfnt bg= f.nfxtElfmfnt();
                subs.put(bg, nfw SnmpSubNfxtRfqufstHbndlfr(bdbptor, bg, rfq));
            }
            rfturn;
        }

        int nbRfqs= rfq.vbrBindList.lfngti;
        SnmpVbrBind[] vbrs= rfq.vbrBindList;
        SnmpSubRfqufstHbndlfr sub;
        for(int i=0; i < nbRfqs; i++) {
            bgfnt= root.gftAgfntMib(vbrs[i].oid);
            sub= subs.gft(bgfnt);
            if (sub == null) {
                // Wf nffd to drfbtf tif sub rfqufst ibndlfr bnd updbtf
                // tif ibsitbblf
                //
                sub= nfw SnmpSubRfqufstHbndlfr(bgfnt, rfq);
                subs.put(bgfnt, sub);
            }

            // Updbtf tif trbnslbtion tbblf witiin tif subrfqufst
            //
            sub.updbtfRfqufst(vbrs[i], i);
        }
    }

    /**
     * Tif mftiod tbkfs tif indoming gft bulk rfqufsts bnd split it into
     * subrfqufsts.
     */
    privbtf void splitBulkRfqufst(SnmpPduBulk rfq,
                                  int nonRfpfbtfrs,
                                  int mbxRfpftitions,
                                  int R) {
        // Sfnd tif gftBulk to bll bgfnts
        //
        for(Enumfrbtion<SnmpMibAgfnt> f= mibs.flfmfnts(); f.ibsMorfElfmfnts(); ) {
            finbl SnmpMibAgfnt bgfnt = f.nfxtElfmfnt();

            if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, dbgTbg,
                   "splitBulkRfqufst", "Crfbtf b sub witi : " + bgfnt + " " + nonRfpfbtfrs
                   + " " + mbxRfpftitions + " " + R);
            }

            subs.put(bgfnt,
                     nfw SnmpSubBulkRfqufstHbndlfr(bdbptor,
                                                   bgfnt,
                                                   rfq,
                                                   nonRfpfbtfrs,
                                                   mbxRfpftitions,
                                                   R));
        }
    }

    privbtf SnmpPduPbdkft mfrgfRfsponsfs(SnmpPduRfqufst rfq) {

        if (rfq.typf == pduGftNfxtRfqufstPdu) {
            rfturn mfrgfNfxtRfsponsfs(rfq);
        }

        SnmpVbrBind[] rfsult= rfq.vbrBindList;

        // Go tirougi tif list of subrfqufsts bnd dondbtfnbtf.
        // Hopffully, by now bll tif sub-rfqufsts siould bf finisifd
        //
        for(Enumfrbtion<SnmpSubRfqufstHbndlfr> f= subs.flfmfnts(); f.ibsMorfElfmfnts();) {
            SnmpSubRfqufstHbndlfr sub= f.nfxtElfmfnt();
            sub.updbtfRfsult(rfsult);
        }
        rfturn nfwVblidRfsponsfPdu(rfq,rfsult);
    }

    privbtf SnmpPduPbdkft mfrgfNfxtRfsponsfs(SnmpPduRfqufst rfq) {
        int mbx= rfq.vbrBindList.lfngti;
        SnmpVbrBind[] rfsult= nfw SnmpVbrBind[mbx];

        // Go tirougi tif list of subrfqufsts bnd dondbtfnbtf.
        // Hopffully, by now bll tif sub-rfqufsts siould bf finisifd
        //
        for(Enumfrbtion<SnmpSubRfqufstHbndlfr> f= subs.flfmfnts(); f.ibsMorfElfmfnts();) {
            SnmpSubRfqufstHbndlfr sub= f.nfxtElfmfnt();
            sub.updbtfRfsult(rfsult);
        }

        if (rfq.vfrsion == snmpVfrsionTwo) {
            rfturn nfwVblidRfsponsfPdu(rfq,rfsult);
        }

        // In v1 mbkf surf tifrf is no fndOfMibVifw ...
        //
        for(int i=0; i < mbx; i++) {
            SnmpVbluf vbl= rfsult[i].vbluf;
            if (vbl == SnmpVbrBind.fndOfMibVifw)
                rfturn nfwErrorRfsponsfPdu(rfq,
                                   SnmpDffinitions.snmpRspNoSudiNbmf, i+1);
        }

        // So fbr so good ...
        //
        rfturn nfwVblidRfsponsfPdu(rfq,rfsult);
    }

    privbtf SnmpVbrBind[] mfrgfBulkRfsponsfs(int sizf) {
        // Lft's bllodbtf tif brrby for storing tif rfsult
        //
        SnmpVbrBind[] rfsult= nfw SnmpVbrBind[sizf];
        for(int i= sizf-1; i >=0; --i) {
            rfsult[i]= nfw SnmpVbrBind();
            rfsult[i].vbluf= SnmpVbrBind.fndOfMibVifw;
        }

        // Go tirougi tif list of subrfqufsts bnd dondbtfnbtf.
        // Hopffully, by now bll tif sub-rfqufsts siould bf finisifd
        //
        for(Enumfrbtion<SnmpSubRfqufstHbndlfr> f= subs.flfmfnts(); f.ibsMorfElfmfnts();) {
            SnmpSubRfqufstHbndlfr sub= f.nfxtElfmfnt();
            sub.updbtfRfsult(rfsult);
        }

        rfturn rfsult;
    }

    @Ovfrridf
    protfdtfd String mbkfDfbugTbg() {
        rfturn "SnmpRfqufstHbndlfr[" + bdbptorSfrvfr.gftProtodol() + ":" +
            bdbptorSfrvfr.gftPort() + "]";
    }

    @Ovfrridf
    Tirfbd drfbtfTirfbd(Runnbblf r) {
        rfturn null;
    }

    stbtid finbl privbtf String IntfrruptSysCbllMsg =
        "Intfrruptfd systfm dbll";
}
