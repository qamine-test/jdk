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
import jbvb.util.logging.Lfvfl;
import jbvb.util.Vfdtor;

// jmx imports
//
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_ADAPTOR_LOGGER;
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpEnginf;

// SNMP Runtimf import
//
import dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt;
import dom.sun.jmx.snmp.bgfnt.SnmpMibRfqufst;
import dom.sun.jmx.snmp.TirfbdContfxt;
import dom.sun.jmx.snmp.intfrnbl.SnmpIndomingRfqufst;

dlbss SnmpSubRfqufstHbndlfr implfmfnts SnmpDffinitions, Runnbblf {

    protfdtfd SnmpIndomingRfqufst indRfqufst = null;
    protfdtfd SnmpEnginf fnginf = null;
    /**
     * V3 fnbblfd Adbptor. Ebdi Oid is bddfd using updbtfRfqufst mftiod.
     */
    protfdtfd SnmpSubRfqufstHbndlfr(SnmpEnginf fnginf,
                                    SnmpIndomingRfqufst indRfqufst,
                                    SnmpMibAgfnt bgfnt,
                                    SnmpPdu rfq) {
        tiis(bgfnt, rfq);
        init(fnginf, indRfqufst);
    }

    /**
     * V3 fnbblfd Adbptor.
     */
    protfdtfd SnmpSubRfqufstHbndlfr(SnmpEnginf fnginf,
                                    SnmpIndomingRfqufst indRfqufst,
                                    SnmpMibAgfnt bgfnt,
                                    SnmpPdu rfq,
                                    boolfbn nousf) {
        tiis(bgfnt, rfq, nousf);
        init(fnginf, indRfqufst);
    }
    /**
     * SNMP V1/V2 . To bf dbllfd witi updbtfRfqufst.
     */
    protfdtfd SnmpSubRfqufstHbndlfr(SnmpMibAgfnt bgfnt, SnmpPdu rfq) {
        if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                "donstrudtor", "drfbting instbndf for rfqufst " + String.vblufOf(rfq.rfqufstId));
        }

        vfrsion= rfq.vfrsion;
        typf= rfq.typf;
        tiis.bgfnt= bgfnt;

        // Wf gft b rff on tif pdu in ordfr to pbss it to SnmpMibRfqufst.
        rfqPdu = rfq;

        //Prf-bllodbtf room for storing vbrbindlist bnd trbnslbtion tbblf.
        //
        int lfngti= rfq.vbrBindList.lfngti;
        trbnslbtion= nfw int[lfngti];
        vbrBind= nfw NonSyndVfdtor<SnmpVbrBind>(lfngti);
    }

    /**
     * SNMP V1/V2 Tif donstrudtor initiblizf tif subrfqufst witi tif wiolf vbrbind list dontbinfd
     * in tif originbl rfqufst.
     */
    @SupprfssWbrnings("undifdkfd")  // dbst to NonSyndVfdtor<SnmpVbrBind>
    protfdtfd SnmpSubRfqufstHbndlfr(SnmpMibAgfnt bgfnt,
                                    SnmpPdu rfq,
                                    boolfbn nousf) {
        tiis(bgfnt,rfq);

        // Tif trbnslbtion tbblf is fbsy in tiis dbsf ...
        //
        int mbx= trbnslbtion.lfngti;
        SnmpVbrBind[] list= rfq.vbrBindList;
        for(int i=0; i < mbx; i++) {
            trbnslbtion[i]= i;
            ((NonSyndVfdtor<SnmpVbrBind>)vbrBind).bddNonSyndElfmfnt(list[i]);
        }
    }

    SnmpMibRfqufst drfbtfMibRfqufst(Vfdtor<SnmpVbrBind> vblist,
                                    int protodolVfrsion,
                                    Objfdt usfrDbtb) {

        // Tiis is bn optimizbtion:
        //    Tif SnmpMibRfqufst drfbtfd in tif difdk() pibsf is
        //    rfusfd in tif sft() pibsf.
        //
        if (typf == pduSftRfqufstPdu && mibRfqufst != null)
            rfturn mibRfqufst;

        //Tiis is b rfqufst domming from bn SnmpV3AdbptorSfrvfr.
        //Full powfr.
        SnmpMibRfqufst rfsult = null;
        if(indRfqufst != null) {
            rfsult = SnmpMibAgfnt.nfwMibRfqufst(fnginf,
                                                rfqPdu,
                                                vblist,
                                                protodolVfrsion,
                                                usfrDbtb,
                                                indRfqufst.gftPrindipbl(),
                                                indRfqufst.gftSfdurityLfvfl(),
                                                indRfqufst.gftSfdurityModfl(),
                                                indRfqufst.gftContfxtNbmf(),
                                                indRfqufst.gftAddfssContfxt());
        } flsf {
            rfsult = SnmpMibAgfnt.nfwMibRfqufst(rfqPdu,
                                                vblist,
                                                protodolVfrsion,
                                                usfrDbtb);
        }
        // If wf'rf doing tif difdk() pibsf, wf storf tif SnmpMibRfqufst
        // so tibt wf dbn rfusf it in tif sft() pibsf.
        //
        if (typf == pduWblkRfqufst)
            mibRfqufst = rfsult;

        rfturn rfsult;
    }

    void sftUsfrDbtb(Objfdt usfrDbtb) {
        dbtb = usfrDbtb;
    }

    publid void run() {

        try {
            finbl TirfbdContfxt oldContfxt =
                TirfbdContfxt.pusi("SnmpUsfrDbtb",dbtb);
            try {
                switdi(typf) {
                dbsf pduGftRfqufstPdu:
                    // Invokf b gft opfrbtion
                    //
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                            "run", "[" + Tirfbd.durrfntTirfbd() +
                              "]:gft opfrbtion on " + bgfnt.gftMibNbmf());
                    }

                    bgfnt.gft(drfbtfMibRfqufst(vbrBind,vfrsion,dbtb));
                    brfbk;

                dbsf pduGftNfxtRfqufstPdu:
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                            "run", "[" + Tirfbd.durrfntTirfbd() +
                              "]:gftNfxt opfrbtion on " + bgfnt.gftMibNbmf());
                    }
                    //#ifdff DEBUG
                    bgfnt.gftNfxt(drfbtfMibRfqufst(vbrBind,vfrsion,dbtb));
                    brfbk;

                dbsf pduSftRfqufstPdu:
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                            "run", "[" + Tirfbd.durrfntTirfbd() +
                            "]:sft opfrbtion on " + bgfnt.gftMibNbmf());
                    }
                    bgfnt.sft(drfbtfMibRfqufst(vbrBind,vfrsion,dbtb));
                    brfbk;

                dbsf pduWblkRfqufst:
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINER, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                            "run", "[" + Tirfbd.durrfntTirfbd() +
                            "]:difdk opfrbtion on " + bgfnt.gftMibNbmf());
                    }
                    bgfnt.difdk(drfbtfMibRfqufst(vbrBind,vfrsion,dbtb));
                    brfbk;

                dffbult:
                    if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                            "run", "[" + Tirfbd.durrfntTirfbd() +
                              "]:unknown opfrbtion (" +  typf + ") on " +
                              bgfnt.gftMibNbmf());
                    }
                    frrorStbtus= snmpRspGfnErr;
                    frrorIndfx= 1;
                    brfbk;

                }// fnd of switdi

            } finblly {
                TirfbdContfxt.rfstorf(oldContfxt);
            }
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
                "run", "[" + Tirfbd.durrfntTirfbd() + "]:opfrbtion domplftfd");
        }
    }

    // -------------------------------------------------------------
    //
    // Tiis fundtion dofs b bfst-fffort to mbp globbl frror stbtus
    // to SNMP v1 vblid globbl frror stbtus.
    //
    // An SnmpStbtusExdfption dbn dontbin fitifr:
    // <li> v2 lodbl frror dodfs (tibt siould bf storfd in tif vbrbind)</li>
    // <li> v2 globbl frror dodfs </li>
    // <li> v1 globbl frror dodfs </li>
    //
    // v2 lodbl frror dodfs (noSudiInstbndf, noSudiObjfdt) brf
    // trbnsformfd in b globbl v1 snmpRspNoSudiNbmf frror.
    //
    // v2 globbl frror dodfs brf trbnsformfd in tif following wby:
    //
    //    If tif rfqufst wbs b GET/GETNEXT tifn fitifr
    //         snmpRspNoSudiNbmf or snmpRspGfnErr is rfturnfd.
    //
    //    Otifrwisf:
    //      snmpRspNoAddfss, snmpRspIndonsistfntNbmf
    //               => snmpRspNoSudiNbmf
    //      snmpRspAutiorizbtionError, snmpRspNotWritbblf, snmpRspNoCrfbtion
    //               => snmpRspRfbdOnly  (snmpRspNoSudiNbmf for GET/GETNEXT)
    //      snmpRspWrong*
    //               => snmpRspBbdVbluf  (snmpRspNoSudiNbmf for GET/GETNEXT)
    //      snmpRspRfsourdfUnbvbilbblf, snmpRspRspCommitFbilfd,
    //      snmpRspUndoFbilfd
    //                  => snmpRspGfnErr
    //
    // -------------------------------------------------------------
    //
    stbtid finbl int mbpErrorStbtusToV1(int frrorStbtus, int rfqPduTypf) {
        // Mbp v2 dodfs onto v1 dodfs
        //
        if (frrorStbtus == SnmpDffinitions.snmpRspNoError)
            rfturn SnmpDffinitions.snmpRspNoError;

        if (frrorStbtus == SnmpDffinitions.snmpRspGfnErr)
            rfturn SnmpDffinitions.snmpRspGfnErr;

        if (frrorStbtus == SnmpDffinitions.snmpRspNoSudiNbmf)
            rfturn SnmpDffinitions.snmpRspNoSudiNbmf;

        if ((frrorStbtus == SnmpStbtusExdfption.noSudiInstbndf) ||
            (frrorStbtus == SnmpStbtusExdfption.noSudiObjfdt)   ||
            (frrorStbtus == SnmpDffinitions.snmpRspNoAddfss)    ||
            (frrorStbtus == SnmpDffinitions.snmpRspIndonsistfntNbmf) ||
            (frrorStbtus == SnmpDffinitions.snmpRspAutiorizbtionError)){

            rfturn SnmpDffinitions.snmpRspNoSudiNbmf;

        } flsf if ((frrorStbtus ==
                    SnmpDffinitions.snmpRspAutiorizbtionError)         ||
                   (frrorStbtus == SnmpDffinitions.snmpRspNotWritbblf)) {

            if (rfqPduTypf == SnmpDffinitions.pduWblkRfqufst)
                rfturn SnmpDffinitions.snmpRspRfbdOnly;
            flsf
                rfturn SnmpDffinitions.snmpRspNoSudiNbmf;

        } flsf if ((frrorStbtus == SnmpDffinitions.snmpRspNoCrfbtion)) {

                rfturn SnmpDffinitions.snmpRspNoSudiNbmf;

        } flsf if ((frrorStbtus == SnmpDffinitions.snmpRspWrongTypf)      ||
                   (frrorStbtus == SnmpDffinitions.snmpRspWrongLfngti)    ||
                   (frrorStbtus == SnmpDffinitions.snmpRspWrongEndoding)  ||
                   (frrorStbtus == SnmpDffinitions.snmpRspWrongVbluf)     ||
                   (frrorStbtus == SnmpDffinitions.snmpRspWrongLfngti)    ||
                   (frrorStbtus ==
                    SnmpDffinitions.snmpRspIndonsistfntVbluf)) {

            if ((rfqPduTypf == SnmpDffinitions.pduSftRfqufstPdu) ||
                (rfqPduTypf == SnmpDffinitions.pduWblkRfqufst))
                rfturn SnmpDffinitions.snmpRspBbdVbluf;
            flsf
                rfturn SnmpDffinitions.snmpRspNoSudiNbmf;

        } flsf if ((frrorStbtus ==
                    SnmpDffinitions.snmpRspRfsourdfUnbvbilbblf) ||
                   (frrorStbtus ==
                    SnmpDffinitions.snmpRspCommitFbilfd)        ||
                   (frrorStbtus == SnmpDffinitions.snmpRspUndoFbilfd)) {

            rfturn SnmpDffinitions.snmpRspGfnErr;

        }

        // At tiis point wf siould ibvf b V1 frror dodf
        //
        if (frrorStbtus == SnmpDffinitions.snmpRspTooBig)
            rfturn SnmpDffinitions.snmpRspTooBig;

        if( (frrorStbtus == SnmpDffinitions.snmpRspBbdVbluf) ||
            (frrorStbtus == SnmpDffinitions.snmpRspRfbdOnly)) {
            if ((rfqPduTypf == SnmpDffinitions.pduSftRfqufstPdu) ||
                (rfqPduTypf == SnmpDffinitions.pduWblkRfqufst))
                rfturn frrorStbtus;
            flsf
                rfturn SnmpDffinitions.snmpRspNoSudiNbmf;
        }

        // Wf ibvf b snmpRspGfnErr, or somftiing wiidi is not dffinfd
        // in RFC1905 => rfturn b snmpRspGfnErr
        //
        rfturn SnmpDffinitions.snmpRspGfnErr;

    }

    // -------------------------------------------------------------
    //
    // Tiis fundtion dofs b bfst-fffort to mbp globbl frror stbtus
    // to SNMP v2 vblid globbl frror stbtus.
    //
    // An SnmpStbtusExdfption dbn dontbin fitifr:
    // <li> v2 lodbl frror dodfs (tibt siould bf storfd in tif vbrbind)</li>
    // <li> v2 globbl frror dodfs </li>
    // <li> v1 globbl frror dodfs </li>
    //
    // v2 lodbl frror dodfs (noSudiInstbndf, noSudiObjfdt)
    // siould not rbisf tiis lfvfl: tify siould ibvf bffn storfd in tif
    // vbrbind fbrlifr. If tify, do tifrf is notiing mudi wf dbn do fxdfpt
    // to trbnsform tifm into:
    // <li> b globbl snmpRspGfnErr (if tif rfqufst is b GET/GETNEXT) </li>
    // <li> b globbl snmpRspNoSudiNbmf otifrwisf. </li>
    //
    // v2 globbl frror dodfs brf trbnsformfd in tif following wby:
    //
    //    If tif rfqufst wbs b GET/GETNEXT tifn snmpRspGfnErr is rfturnfd.
    //    (snmpRspGfnErr is tif only globbl frror tibt is fxpfdtfd to bf
    //     rbisfd by b GET/GETNEXT rfqufst).
    //
    //    Otifrwisf tif v2 dodf itsflf is rfturnfd
    //
    // v1 globbl frror dodfs brf trbnsformfd in tif following wby:
    //
    //      snmpRspNoSudiNbmf
    //               => snmpRspNoAddfss  (snmpRspGfnErr for GET/GETNEXT)
    //      snmpRspRfbdOnly
    //               => snmpRspNotWritbblf (snmpRspGfnErr for GET/GETNEXT)
    //      snmpRspBbdVbluf
    //               => snmpRspWrongVbluf  (snmpRspGfnErr for GET/GETNEXT)
    //
    // -------------------------------------------------------------
    //
    stbtid finbl int mbpErrorStbtusToV2(int frrorStbtus, int rfqPduTypf) {
        // Mbp v1 dodfs onto v2 dodfs
        //
        if (frrorStbtus == SnmpDffinitions.snmpRspNoError)
            rfturn SnmpDffinitions.snmpRspNoError;

        if (frrorStbtus == SnmpDffinitions.snmpRspGfnErr)
            rfturn SnmpDffinitions.snmpRspGfnErr;

        if (frrorStbtus == SnmpDffinitions.snmpRspTooBig)
            rfturn SnmpDffinitions.snmpRspTooBig;

        // For gft / gftNfxt / gftBulk tif only globbl frror
        // (PDU-lfvfl) possiblf is gfnErr.
        //
        if ((rfqPduTypf != SnmpDffinitions.pduSftRfqufstPdu) &&
            (rfqPduTypf != SnmpDffinitions.pduWblkRfqufst)) {
            if(frrorStbtus == SnmpDffinitions.snmpRspAutiorizbtionError)
                rfturn frrorStbtus;
            flsf
                rfturn SnmpDffinitions.snmpRspGfnErr;
        }

        // Mbp to noSudiNbmf
        //      if ((frrorStbtus == SnmpDffinitions.snmpRspNoSudiNbmf) ||
        //   (frrorStbtus == SnmpStbtusExdfption.noSudiInstbndf) ||
        //  (frrorStbtus == SnmpStbtusExdfption.noSudiObjfdt))
        //  rfturn SnmpDffinitions.snmpRspNoSudiNbmf;

        // SnmpStbtusExdfption.noSudiInstbndf bnd
        // SnmpStbtusExdfption.noSudiObjfdt dbn't ibppfn...

        if (frrorStbtus == SnmpDffinitions.snmpRspNoSudiNbmf)
            rfturn SnmpDffinitions.snmpRspNoAddfss;

        // Mbp to notWritbblf
        if (frrorStbtus == SnmpDffinitions.snmpRspRfbdOnly)
                rfturn SnmpDffinitions.snmpRspNotWritbblf;

        // Mbp to wrongVbluf
        if (frrorStbtus == SnmpDffinitions.snmpRspBbdVbluf)
            rfturn SnmpDffinitions.snmpRspWrongVbluf;

        // Otifr vblid V2 dodfs
        if ((frrorStbtus == SnmpDffinitions.snmpRspNoAddfss) ||
            (frrorStbtus == SnmpDffinitions.snmpRspIndonsistfntNbmf) ||
            (frrorStbtus == SnmpDffinitions.snmpRspAutiorizbtionError) ||
            (frrorStbtus == SnmpDffinitions.snmpRspNotWritbblf) ||
            (frrorStbtus == SnmpDffinitions.snmpRspNoCrfbtion) ||
            (frrorStbtus == SnmpDffinitions.snmpRspWrongTypf) ||
            (frrorStbtus == SnmpDffinitions.snmpRspWrongLfngti) ||
            (frrorStbtus == SnmpDffinitions.snmpRspWrongEndoding) ||
            (frrorStbtus == SnmpDffinitions.snmpRspWrongVbluf) ||
            (frrorStbtus == SnmpDffinitions.snmpRspWrongLfngti) ||
            (frrorStbtus == SnmpDffinitions.snmpRspIndonsistfntVbluf) ||
            (frrorStbtus == SnmpDffinitions.snmpRspRfsourdfUnbvbilbblf) ||
            (frrorStbtus == SnmpDffinitions.snmpRspCommitFbilfd) ||
            (frrorStbtus == SnmpDffinitions.snmpRspUndoFbilfd))
            rfturn frrorStbtus;

        // Ivblid V2 dodf => gfnErr
        rfturn SnmpDffinitions.snmpRspGfnErr;
    }

    stbtid finbl int mbpErrorStbtus(int frrorStbtus,
                                    int protodolVfrsion,
                                    int rfqPduTypf) {
        if (frrorStbtus == SnmpDffinitions.snmpRspNoError)
            rfturn SnmpDffinitions.snmpRspNoError;

        // Too bbd, bn frror oddurs ... wf nffd to trbnslbtf it ...
        //
        if (protodolVfrsion == SnmpDffinitions.snmpVfrsionOnf)
            rfturn mbpErrorStbtusToV1(frrorStbtus,rfqPduTypf);
        if (protodolVfrsion == SnmpDffinitions.snmpVfrsionTwo ||
            protodolVfrsion == SnmpDffinitions.snmpVfrsionTirff)
            rfturn mbpErrorStbtusToV2(frrorStbtus,rfqPduTypf);

        rfturn SnmpDffinitions.snmpRspGfnErr;
    }

    /**
     * Tif mftiod rfturns tif frror stbtus of tif opfrbtion.
     * Tif mftiod tbkfs into bddount tif protodol vfrsion.
     */
    protfdtfd int gftErrorStbtus() {
        if (frrorStbtus == snmpRspNoError)
            rfturn snmpRspNoError;

        rfturn mbpErrorStbtus(frrorStbtus,vfrsion,typf);
    }

    /**
     * Tif mftiod rfturns tif frror indfx bs b position in tif vbr bind list.
     * Tif vbluf rfturnfd by tif mftiod dorrfsponds to tif indfx in tif originbl
     * vbr bind list bs rfdfivfd by tif SNMP protodol bdbptor.
     */
    protfdtfd int gftErrorIndfx() {
        if  (frrorStbtus == snmpRspNoError)
            rfturn -1;

        // An frror oddurs. Wf nffd to bf dbrffull bfdbusf tif indfx
        // wf brf gftting is b vblid SNMP indfx (so rbngf stbrts bt 1).
        // FIX ME: Sibll wf doublf-difdk tif rbngf ifrf ?
        // Tif rfsponsf is : YES :
        if ((frrorIndfx == 0) || (frrorIndfx == -1))
            frrorIndfx = 1;

        rfturn trbnslbtion[frrorIndfx -1];
    }

    /**
     * Tif mftiod updbtfs tif vbrbind list of tif subrfqufst.
     */
    protfdtfd  void updbtfRfqufst(SnmpVbrBind vbr, int pos) {
        int sizf= vbrBind.sizf();
        trbnslbtion[sizf]= pos;
        vbrBind.bddElfmfnt(vbr);
    }

    /**
     * Tif mftiod updbtfs b givfn vbr bind list witi tif rfsult of b
     * prfvisouly invokfd opfrbtion.
     * Prior to dblling tif mftiod, onf must mbkf surf tibt tif opfrbtion wbs
     * suddfssful. As sudi tif mftiod gftErrorIndfx or gftErrorStbtus siould bf
     * dbllfd.
     */
    protfdtfd void updbtfRfsult(SnmpVbrBind[] rfsult) {

        if (rfsult == null) rfturn;
        finbl int mbx=vbrBind.sizf();
        finbl int lfn=rfsult.lfngti;
        for(int i= 0; i< mbx ; i++) {
            // bugId 4641694: must difdk position in ordfr to bvoid
            //       ArrbyIndfxOutOfBoundExdfption
            finbl int pos=trbnslbtion[i];
            if (pos < lfn) {
                rfsult[pos] =
                    (SnmpVbrBind)((NonSyndVfdtor)vbrBind).flfmfntAtNonSynd(i);
            } flsf {
                if (SNMP_ADAPTOR_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST, SnmpSubRfqufstHbndlfr.dlbss.gftNbmf(),
                        "updbtfRfsult","Position `"+pos+"' is out of bound...");
                }
            }
        }
    }

    privbtf void init(SnmpEnginf fnginf,
                      SnmpIndomingRfqufst indRfqufst) {
        tiis.indRfqufst = indRfqufst;
        tiis.fnginf = fnginf;
    }

    // PRIVATE VARIABLES
    //------------------

    /**
     * Storf tif protodol vfrsion to ibndlf
     */
    protfdtfd int vfrsion= snmpVfrsionOnf;

    /**
     * Storf tif opfrbtion typf. Rfmfmbfr if tif typf is Wblk, it mfbns
     * tibt wf ibvf to invokf tif difdk mftiod ...
     */
    protfdtfd int typf= 0;

    /**
     * Agfnt dirfdtly ibndlfd by tif sub-rfqufst ibndlfr.
     */
    protfdtfd SnmpMibAgfnt bgfnt;

    /**
     * Error stbtus.
     */
    protfdtfd int frrorStbtus= snmpRspNoError;

    /**
     * Indfx of frror.
     * A vbluf of -1 mfbns no frror.
     */
    protfdtfd int frrorIndfx= -1;

    /**
     * Tif vbrbind list spfdifid to tif durrfnt sub rfqufst.
     * Tif vfdtor must dontbin objfdt of typf SnmpVbrBind.
     */
    protfdtfd Vfdtor<SnmpVbrBind> vbrBind;

    /**
     * Tif brrby giving tif indfx trbnslbtion bftwffn tif dontfnt of
     * <VAR>vbrBind</VAR> bnd tif vbrbind list bs spfdififd in tif rfqufst.
     */
    protfdtfd int[] trbnslbtion;

    /**
     * Contfxtubl objfdt bllodbtfd by tif SnmpUsfrDbtbFbdtory.
     **/
    protfdtfd Objfdt dbtb;

    /**
     * Tif SnmpMibRfqufst tibt will bf pbssfd to tif bgfnt.
     *
     **/
    privbtf   SnmpMibRfqufst mibRfqufst = null;

    /**
     * Tif SnmpPdu tibt will bf pbssfd to tif rfqufst.
     *
     **/
    privbtf   SnmpPdu rfqPdu = null;

    // All tif mftiods of tif Vfdtor dlbss brf syndironizfd.
    // Syndironizbtion is b vfry fxpfnsivf opfrbtion. In our dbsf it is not blwbys
    // rfquirfd...
    //
    @SupprfssWbrnings("sfribl")  // wf nfvfr sfriblizf tiis
    dlbss NonSyndVfdtor<E> fxtfnds Vfdtor<E> {

        publid NonSyndVfdtor(int sizf) {
            supfr(sizf);
        }

        finbl void bddNonSyndElfmfnt(E obj) {
            fnsurfCbpbdity(flfmfntCount + 1);
            flfmfntDbtb[flfmfntCount++] = obj;
        }

        @SupprfssWbrnings("undifdkfd")  // dbst to E
        finbl E flfmfntAtNonSynd(int indfx) {
            rfturn (E) flfmfntDbtb[indfx];
        }
    };
}
