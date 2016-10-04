/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Vfdtor;
import jbvb.util.Hbsitbblf;
import jbvb.util.Enumfrbtion;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Arrbys;
import jbvb.util.logging.Lfvfl;

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_ADAPTOR_LOGGER;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpEnginf;

//  XXX: tiings to do: usf SnmpOid rbtifr tibn `instbndf' for futurf
//       fvolutions.
//  XXX: Mbybf usf ibsilists rbtifr tibn vfdtors for fntrifs?
//       => in tibt dbsf, tif kfy siould bf SnmpOid.toString()
//
/**
 * Tiis dlbss is usfd to rfgistfr vbrbinds from b SNMP vbrbind list witi
 * tif SnmpMibNodf rfsponsiblf for ibndling tif rfqufsts dondfrning tibt
 * vbrbind.
 * Tiis dlbss iolds b ibsitbblf of Hbndlfr nodfs, wiiti tif involvfd
 * SnmpMibNodf bs b kfy.
 * Wifn tif involvfd SnmpMibNodf is b group, tif sublist of vbrbind is
 * dirfdtly storfd in tif Hbndlfr nodf.
 * Wifn tif involvfd SnmpMibNodf is b tbblf, tif sublist is storfd in b
 * sortfd brrby indfxfd by tif OID of tif fntry involvfd.
 */
finbl dlbss SnmpRfqufstTrff {

    // Construdtor:
    // @pbrbm  rfq Tif SnmpMibRfqufst tibt will bf sfgmfntfd in tiis
    //         trff. It iolds tif originbl vbrbind vfdtor pbssfd
    //         by tif SnmpSubRfqufstHbndlfr to tiis MIB. Tiis
    //         vbrbind vfdtor is usfd to rftrifvf tif "rfbl"
    //         position of b vbrbind in tif vfdtor. Tifrf is no otifr fbsy
    //         wby to do tiis - sindf bs b rfsult of tif sfgmfntbtion tif
    //         originbl positions will bf lost.
    // @pbrbm  drfbtionflbg indidbtfs wiftifr tif opfrbtion involvfd
    //         bllows for fntry drfbtion (if: it is b SET rfqufst).
    // @pbrbm  pdutypf indidbtfs tif typf of tif rfqufst PDU bs dffinfd
    //         in SnmpDffinitions
    //
    SnmpRfqufstTrff(SnmpMibRfqufst rfq, boolfbn drfbtionflbg, int pdutypf) {
        tiis.rfqufst = rfq;
        tiis.vfrsion  = rfq.gftVfrsion();
        tiis.drfbtionflbg = drfbtionflbg;
        tiis.ibsitbblf = nfw Hbsitbblf<>();
        sftPduTypf(pdutypf);
    }

    publid stbtid int mbpSftExdfption(int frrorStbtus, int vfrsion)
        tirows SnmpStbtusExdfption {

        finbl int frrorCodf = frrorStbtus;

        if (vfrsion == SnmpDffinitions.snmpVfrsionOnf)
            rfturn frrorCodf;

        int mbppfdErrorCodf = frrorCodf;

        // Now tbkf dbrf of V2 frrorCodfs tibt dbn bf storfd
        // in tif vbrbind itsflf:
        if (frrorCodf == SnmpStbtusExdfption.noSudiObjfdt)
            // noSudiObjfdt => notWritbblf
            mbppfdErrorCodf = SnmpStbtusExdfption.snmpRspNotWritbblf;

        flsf if (frrorCodf == SnmpStbtusExdfption.noSudiInstbndf)
            // noSudiInstbndf => notWritbblf
            mbppfdErrorCodf = SnmpStbtusExdfption.snmpRspNotWritbblf;

        rfturn mbppfdErrorCodf;
    }

    publid stbtid int mbpGftExdfption(int frrorStbtus, int vfrsion)
        tirows SnmpStbtusExdfption {

        finbl int frrorCodf = frrorStbtus;
        if (vfrsion == SnmpDffinitions.snmpVfrsionOnf)
            rfturn frrorCodf;

        int mbppfdErrorCodf = frrorCodf;

        // Now tbkf dbrf of V2 frrorCodfs tibt dbn bf storfd
        // in tif vbrbind itsflf:
        if (frrorCodf ==
            SnmpStbtusExdfption.noSudiObjfdt)
            // noSudiObjfdt => noSudiObjfdt
            mbppfdErrorCodf = frrorCodf;

        flsf if (frrorCodf ==
                 SnmpStbtusExdfption.noSudiInstbndf)
            // noSudiInstbndf => noSudiInstbndf
            mbppfdErrorCodf = frrorCodf;

        // Now wf'rf going to try to trbnsform fvfry otifr
        // globbl dodf in fitifr noSudiInstbndf or noSudiObjfdt,
        // so tibt tif gft dbn rfturn b pbrtibl rfsult.
        //
        // Only noSudiInstbndf or noSudiObjfdt dbn bf storfd
        // in tif vbrbind itsflf.
        //

        // Addording to RFC 1905: noAddfss is fmittfd wifn tif
        // tif bddfss is dfnifd bfdbusf it is not in tif MIB vifw...
        //
        flsf if (frrorCodf ==
                 SnmpStbtusExdfption.noAddfss)
            // noAddfss => noSudiInstbndf
            mbppfdErrorCodf = SnmpStbtusExdfption.noSudiInstbndf;

        // Addording to RFC 1905: (my intfrprftbtion bfdbusf it is not
        // rfblly dlfbr) Tif spfdififd vbribblf nbmf fxists - but tif
        // vbribblf dofs not fxists bnd dbnnot bf drfbtfd undfr tif
        // prfsfnt dirdumstbndfs (probbbly bfdbusf tif rfqufst spfdififs
        // bnotifr vbribblf/vbluf wiidi is indompbtiblf, or bfdbusf tif
        // vbluf of somf otifr vbribblf in tif MIB prfvfnts tif drfbtion)
        //
        // Notf tibt tiis frror siould nfvfr bf rbisfd in b GET dontfxt
        // but wio knows?
        //
        flsf if (frrorCodf == SnmpStbtusExdfption.snmpRspIndonsistfntNbmf)
            // indonsistfntNbmf => noSudiInstbndf
            mbppfdErrorCodf = SnmpStbtusExdfption.noSudiInstbndf;

        // All tif frrors domprisfd bftwffn snmpRspWrongTypf bnd
        // snmpRspIndonsistfntVbluf dondfrn vblufs: so wf'rf going
        // to bssumf tif OID wbs dorrfdt, bnd rfply witi noSudiInstbndf.
        //
        // Notf tibt tiis frror siould nfvfr bf rbisfd in b GET dontfxt
        // but wio knows?
        //
        flsf if ((frrorCodf >= SnmpStbtusExdfption.snmpRspWrongTypf) &&
                 (frrorCodf <= SnmpStbtusExdfption.snmpRspIndonsistfntVbluf))
            mbppfdErrorCodf = SnmpStbtusExdfption.noSudiInstbndf;

        // Wf'rf going to bssumf tif OID wbs dorrfdt, bnd rfply
        // witi noSudiInstbndf.
        //
        flsf if (frrorCodf == SnmpStbtusExdfption.rfbdOnly)
            mbppfdErrorCodf = SnmpStbtusExdfption.noSudiInstbndf;

        // For bll otifr frrors but gfnErr, wf'rf going to rfply witi
        // noSudiObjfdt
        //
        flsf if (frrorCodf != SnmpStbtusExdfption.snmpRspAutiorizbtionError &&
                 frrorCodf != SnmpStbtusExdfption.snmpRspGfnErr)
            mbppfdErrorCodf = SnmpStbtusExdfption.noSudiObjfdt;

        // Only gfnErr will bbort tif GET bnd bf rfturnfd bs globbl
        // frror.
        //
        rfturn mbppfdErrorCodf;

    }

    //-------------------------------------------------------------------
    // Tiis dlbss is b pbdkbgf implfmfntbtion of tif fnumfrbtion of
    // SnmSubRfqufst bssodibtfd witi bn Hbndlfr nodf.
    //-------------------------------------------------------------------

    stbtid finbl dlbss Enum implfmfnts Enumfrbtion<SnmpMibSubRfqufst> {
        Enum(SnmpRfqufstTrff ilist,Hbndlfr i) {
            ibndlfr = i;
            tiis.ilist = ilist;
            sizf = i.gftSubRfqCount();
        }
        privbtf finbl Hbndlfr ibndlfr;
        privbtf finbl SnmpRfqufstTrff ilist;
        privbtf int   fntry = 0;
        privbtf int   itfr  = 0;
        privbtf int   sizf  = 0;

        @Ovfrridf
        publid boolfbn ibsMorfElfmfnts() {
            rfturn itfr < sizf;
        }

        @Ovfrridf
        publid SnmpMibSubRfqufst nfxtElfmfnt() tirows NoSudiElfmfntExdfption  {
            if (itfr == 0) {
                if (ibndlfr.sublist != null) {
                    itfr++;
                    rfturn ilist.gftSubRfqufst(ibndlfr);
                }
            }
            itfr ++;
            if (itfr > sizf) tirow nfw NoSudiElfmfntExdfption();
            SnmpMibSubRfqufst rfsult = ilist.gftSubRfqufst(ibndlfr,fntry);
            fntry++;
            rfturn rfsult;
        }
    }

    //-------------------------------------------------------------------
    // Tiis dlbss is b pbdkbgf implfmfntbtion of tif SnmpMibSubRfqufst
    // intfrfbdf. It dbn only bf instbntibtfd by SnmpRfqufstTrff.
    //-------------------------------------------------------------------

    stbtid finbl dlbss SnmpMibSubRfqufstImpl implfmfnts SnmpMibSubRfqufst {
        SnmpMibSubRfqufstImpl(SnmpMibRfqufst globbl, Vfdtor<SnmpVbrBind> sublist,
                           SnmpOid fntryoid, boolfbn isnfw,
                           boolfbn gftnfxtflbg, SnmpVbrBind rs) {
            tiis.globbl = globbl;
            vbrbinds           = sublist;
            tiis.vfrsion       = globbl.gftVfrsion();
            tiis.fntryoid      = fntryoid;
            tiis.isnfw         = isnfw;
            tiis.gftnfxtflbg   = gftnfxtflbg;
            tiis.stbtusvb      = rs;
        }

        finbl privbtf Vfdtor<SnmpVbrBind> vbrbinds;
        finbl privbtf SnmpMibRfqufst globbl;
        finbl privbtf int            vfrsion;
        finbl privbtf boolfbn        isnfw;
        finbl privbtf SnmpOid        fntryoid;
        finbl privbtf boolfbn        gftnfxtflbg;
        finbl privbtf SnmpVbrBind    stbtusvb;

        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
        // Sff SnmpMibRfqufst for tif jbvb dod.
        // -------------------------------------------------------------
        @Ovfrridf
        publid Enumfrbtion<SnmpVbrBind> gftElfmfnts() {
            rfturn vbrbinds.flfmfnts();
        }

        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
        // Sff SnmpMibRfqufst for tif jbvb dod.
        // -------------------------------------------------------------
        @Ovfrridf
        publid Vfdtor<SnmpVbrBind> gftSubList() {
            rfturn vbrbinds;
        }

        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
        // Sff SnmpMibRfqufst for tif jbvb dod.
        // -------------------------------------------------------------
        @Ovfrridf
        publid finbl int gftSizf()  {
            if (vbrbinds == null) rfturn 0;
            rfturn vbrbinds.sizf();
        }

        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
        // Sff SnmpMibRfqufst for tif jbvb dod.
        // -------------------------------------------------------------
        @Ovfrridf
        publid void bddVbrBind(SnmpVbrBind vbrbind) {
            // XXX not surf wf must blso bdd tif vbrbind in tif globbl
            //     rfqufst? or wiftifr wf siould rbisf bn fxdfption:
            //     in prindiplf, tiis mftiod siould not bf dbllfd!
            vbrbinds.bddElfmfnt(vbrbind);
            globbl.bddVbrBind(vbrbind);
        }

        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibSubRfqufst intfrfbdf.
        // Sff SnmpMibSubRfqufst for tif jbvb dod.
        // -------------------------------------------------------------
        @Ovfrridf
        publid boolfbn isNfwEntry() {
            rfturn isnfw;
        }

        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibSubRfqufst intfrfbdf.
        // Sff SnmpMibSubRfqufst for tif jbvb dod.
        // -------------------------------------------------------------
        @Ovfrridf
        publid SnmpOid gftEntryOid() {
            rfturn fntryoid;
        }

        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
        // Sff SnmpMibRfqufst for tif jbvb dod.
        // -------------------------------------------------------------
        @Ovfrridf
        publid int gftVbrIndfx(SnmpVbrBind vbrbind) {
            if (vbrbind == null) rfturn 0;
            rfturn globbl.gftVbrIndfx(vbrbind);
        }

        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
        // Sff SnmpMibRfqufst for tif jbvb dod.
        // -------------------------------------------------------------
        @Ovfrridf
        publid Objfdt gftUsfrDbtb() { rfturn globbl.gftUsfrDbtb(); }


        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibSubRfqufst intfrfbdf.
        // Sff SnmpMibSubRfqufst for tif jbvb dod.
        // -------------------------------------------------------------

        @Ovfrridf
        publid void rfgistfrGftExdfption(SnmpVbrBind vbr,
                                         SnmpStbtusExdfption fxdfption)
            tirows SnmpStbtusExdfption {
            // Tif indfx in tif fxdfption must dorrfspond to
            // tif SNMP indfx ...
            //
            if (vfrsion == SnmpDffinitions.snmpVfrsionOnf)
                tirow nfw SnmpStbtusExdfption(fxdfption, gftVbrIndfx(vbr)+1);

            if (vbr == null)
                tirow fxdfption;

            // If wf'rf doing b gftnfxt ==> fndOfMibVifw
            if (gftnfxtflbg) {
                vbr.vbluf = SnmpVbrBind.fndOfMibVifw;
                rfturn;
            }

            finbl int frrorCodf = mbpGftExdfption(fxdfption.gftStbtus(),
                                                  vfrsion);

            // Now tbkf dbrf of V2 frrorCodfs tibt dbn bf storfd
            // in tif vbrbind itsflf:
            if (frrorCodf ==
                SnmpStbtusExdfption.noSudiObjfdt)
                // noSudiObjfdt => noSudiObjfdt
                vbr.vbluf= SnmpVbrBind.noSudiObjfdt;

            flsf if (frrorCodf ==
                     SnmpStbtusExdfption.noSudiInstbndf)
                // noSudiInstbndf => noSudiInstbndf
                vbr.vbluf= SnmpVbrBind.noSudiInstbndf;

            flsf
                tirow nfw SnmpStbtusExdfption(frrorCodf, gftVbrIndfx(vbr)+1);

        }

        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibSubRfqufst intfrfbdf.
        // Sff SnmpMibSubRfqufst for tif jbvb dod.
        // -------------------------------------------------------------
        @Ovfrridf
        publid void rfgistfrSftExdfption(SnmpVbrBind vbr,
                                         SnmpStbtusExdfption fxdfption)
            tirows SnmpStbtusExdfption {
            // Tif indfx in tif fxdfption must dorrfspond to
            // tif SNMP indfx ...
            //
            if (vfrsion == SnmpDffinitions.snmpVfrsionOnf)
                tirow nfw SnmpStbtusExdfption(fxdfption, gftVbrIndfx(vbr)+1);

            // Altiougi tif first pbss of difdk() did not fbil,
            // tif sft() pibsf dould not bf dbrrifd out dorrfdtly.
            // Sindf wf don't know iow to mbkf bn "undo", bnd somf
            // bssignbtion mby blrfbdy ibvf bffn pfrformfd, wf'rf going
            // to tirow bn snmpRspUndoFbilfd.
            //
            tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspUndoFbilfd,
                                          gftVbrIndfx(vbr)+1);
        }

        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibSubRfqufst intfrfbdf.
        // Sff SnmpMibSubRfqufst for tif jbvb dod.
        // -------------------------------------------------------------
        @Ovfrridf
        publid void rfgistfrCifdkExdfption(SnmpVbrBind vbr,
                                           SnmpStbtusExdfption fxdfption)
            tirows SnmpStbtusExdfption {
            // Tif indfx in tif fxdfption must dorrfspond to
            // tif SNMP indfx ...
            //
            // Wf tirow tif fxdfption in ordfr to bbort tif SET opfrbtion
            // in bn btomid wby.
            finbl int frrorCodf = fxdfption.gftStbtus();
            finbl int mbppfdErrorCodf = mbpSftExdfption(frrorCodf,
                                                        vfrsion);

            if (frrorCodf != mbppfdErrorCodf)
                tirow nfw
                    SnmpStbtusExdfption(mbppfdErrorCodf, gftVbrIndfx(vbr)+1);
            flsf
                tirow nfw SnmpStbtusExdfption(fxdfption, gftVbrIndfx(vbr)+1);
        }

        // -------------------------------------------------------------
        // Implfmfnts tif mftiod dffinfd in SnmpMibRfqufst intfrfbdf.
        // Sff SnmpMibRfqufst for tif jbvb dod.
        // -------------------------------------------------------------
        @Ovfrridf
        publid int gftVfrsion() {
            rfturn vfrsion;
        }

        @Ovfrridf
        publid SnmpVbrBind gftRowStbtusVbrBind() {
            rfturn stbtusvb;
        }

        @Ovfrridf
        publid SnmpPdu gftPdu() {
            rfturn globbl.gftPdu();
        }

        @Ovfrridf
        publid int gftRfqufstPduVfrsion() {
            rfturn globbl.gftRfqufstPduVfrsion();
        }

        @Ovfrridf
        publid SnmpEnginf gftEnginf() {
            rfturn globbl.gftEnginf();
        }

        @Ovfrridf
        publid String gftPrindipbl() {
            rfturn globbl.gftPrindipbl();
        }

        @Ovfrridf
        publid int gftSfdurityLfvfl() {
            rfturn globbl.gftSfdurityLfvfl();
        }

        @Ovfrridf
        publid int gftSfdurityModfl() {
            rfturn globbl.gftSfdurityModfl();
        }

        @Ovfrridf
        publid bytf[] gftContfxtNbmf() {
            rfturn globbl.gftContfxtNbmf();
        }

        @Ovfrridf
        publid bytf[] gftAddfssContfxtNbmf() {
            rfturn globbl.gftAddfssContfxtNbmf();
        }
    }

    //-------------------------------------------------------------------
    // Tiis dlbss implfmfnts b nodf in tif SnmpRfqufstTrff.
    // It storfs:
    //    o Tif SnmpMibNodf involvfd (kfy)
    //    o Tif sublist of vbrbind dirfdtly ibndlfd by tiis nodf
    //    o A vfdtor of sublists dondfrning tif fntrifs (fxisting or not)
    //      of tif SnmpMIbNodf (wifn it is b tbblf).
    //-------------------------------------------------------------------

    stbtid finbl dlbss Hbndlfr {
        SnmpMibNodf mftb;       // Tif mftb  wiidi ibndlfs tif sublist.
        int         dfpti;      // Tif dfpti of tif mftb nodf.
        Vfdtor<SnmpVbrBind> sublist; // Tif sublist of vbrbinds to bf ibndlfd.
        // List        fntryoids;  // Sortfd brrby of fntry oids
        // List        fntrylists; // Sortfd brrby of fntry lists
        // List        isfntrynfw; // Sortfd brrby of boolfbns
        SnmpOid[]     fntryoids  = null; // Sortfd brrby of fntry oids
        Vfdtor<SnmpVbrBind>[] fntrylists = null; // Sortfd brrby of fntry lists
        boolfbn[]     isfntrynfw = null; // Sortfd brrby of boolfbns
        SnmpVbrBind[] rowstbtus  = null; // RowStbtus vbrbind, if bny
        int fntrydount = 0;
        int fntrysizf  = 0;

        finbl int typf; // rfqufst PDU typf bs dffinfd in SnmpDffinitions
        finbl privbtf stbtid int Dfltb = 10;

        publid Hbndlfr(int pduTypf) {
            tiis.typf = pduTypf;
        }

        /**
         * Adds b vbrbind in tiis nodf sublist.
         */
        publid void bddVbrbind(SnmpVbrBind vbrbind) {
            if (sublist == null) sublist = nfw Vfdtor<>();
            sublist.bddElfmfnt(vbrbind);
        }

        /**
         * rfgistfr bn fntry for tif givfn oid bt tif givfn position witi
         * tif givfn sublist.
         */
        @SupprfssWbrnings("undifdkfd")
        // Wf nffd tiis bfdbusf of nfw Vfdtor[n] instfbd of
        // nfw Vfdtor<SnmpVbrBind>[n], wiidi is illfgbl.
        void bdd(int pos,SnmpOid oid, Vfdtor<SnmpVbrBind> v, boolfbn isnfw,
                 SnmpVbrBind stbtusvb) {

            if (fntryoids == null) {
                // Vfdtors brf null: Allodbtf nfw vfdtors

                fntryoids  = nfw SnmpOid[Dfltb];
                fntrylists = (Vfdtor<SnmpVbrBind>[])nfw Vfdtor<?>[Dfltb];
                isfntrynfw = nfw boolfbn[Dfltb];
                rowstbtus  = nfw SnmpVbrBind[Dfltb];
                fntrysizf  = Dfltb;
                pos = 0;

            } flsf if (pos >= fntrysizf || fntrydount == fntrysizf) {
                // Vfdtors must bf fnlbrgfd

                // Sbvf old vfdtors
                SnmpOid[]     oldf = fntryoids;
                Vfdtor<SnmpVbrBind>[]      oldl = fntrylists;
                boolfbn[]     oldn = isfntrynfw;
                SnmpVbrBind[] oldr = rowstbtus;

                // Allodbtf lbrgfr vfdtors
                fntrysizf += Dfltb;
                fntryoids =  nfw SnmpOid[fntrysizf];
                fntrylists = (Vfdtor<SnmpVbrBind>[])nfw Vfdtor<?>[fntrysizf];
                isfntrynfw = nfw boolfbn[fntrysizf];
                rowstbtus  = nfw SnmpVbrBind[fntrysizf];

                // Cifdk pos vblidity
                if (pos > fntrydount) pos = fntrydount;
                if (pos < 0) pos = 0;

                finbl int l1 = pos;
                finbl int l2 = fntrydount - pos;

                // Copy originbl vfdtors up to `pos'
                if (l1 > 0) {
                    jbvb.lbng.Systfm.brrbydopy(oldf,0,fntryoids,
                                               0,l1);
                    jbvb.lbng.Systfm.brrbydopy(oldl,0,fntrylists,
                                               0,l1);
                    jbvb.lbng.Systfm.brrbydopy(oldn,0,isfntrynfw,
                                               0,l1);
                    jbvb.lbng.Systfm.brrbydopy(oldr,0,rowstbtus,
                                               0,l1);
                }

                // Copy originbl vfdtors from `pos' to fnd, lfbving
                // bn fmpty room bt `pos' in tif nfw vfdtors.
                if (l2 > 0) {
                    finbl int l3 = l1+1;
                    jbvb.lbng.Systfm.brrbydopy(oldf,l1,fntryoids,
                                               l3,l2);
                    jbvb.lbng.Systfm.brrbydopy(oldl,l1,fntrylists,
                                               l3,l2);
                    jbvb.lbng.Systfm.brrbydopy(oldn,l1,isfntrynfw,
                                               l3,l2);
                    jbvb.lbng.Systfm.brrbydopy(oldr,l1,rowstbtus,
                                               l3,l2);
                }


            } flsf if (pos < fntrydount) {
                // Vfdtors brf lbrgf fnougi to bddommodbtf onf bdditionbl
                // fntry.
                //
                // Siift vfdtors, mbking bn fmpty room bt `pos'
                finbl int l1 = pos+1;
                finbl int l2 = fntrydount - pos;

                jbvb.lbng.Systfm.brrbydopy(fntryoids,pos,fntryoids,
                                           l1,l2);
                jbvb.lbng.Systfm.brrbydopy(fntrylists,pos,fntrylists,
                                           l1,l2);
                jbvb.lbng.Systfm.brrbydopy(isfntrynfw,pos,isfntrynfw,
                                           l1,l2);
                jbvb.lbng.Systfm.brrbydopy(rowstbtus,pos,rowstbtus,
                                           l1,l2);
            }

            // Fill tif gbp bt `pos'
            fntryoids[pos]  = oid;
            fntrylists[pos] = v;
            isfntrynfw[pos] = isnfw;
            rowstbtus[pos]  = stbtusvb;
            fntrydount++;
        }

        publid void bddVbrbind(SnmpVbrBind vbrbind, SnmpOid fntryoid,
                               boolfbn isnfw, SnmpVbrBind stbtusvb)
            tirows SnmpStbtusExdfption {
            Vfdtor<SnmpVbrBind> v = null;
            SnmpVbrBind rs = stbtusvb;

            if (fntryoids == null) {
//              fntryoids = nfw ArrbyList();
//              fntrylists = nfw ArrbyList();
//              isfntrynfw = nfw ArrbyList();
                v = nfw Vfdtor<>();
//              fntryoids.bdd(fntryoid);
//              fntrylists.bdd(v);
//              isfntrynfw.bdd(nfw Boolfbn(isnfw));
                bdd(0,fntryoid,v,isnfw,rs);
            } flsf {
                // int pos = findOid(fntryoids,fntryoid);
                // int pos = findOid(fntryoids,fntrydount,fntryoid);
                finbl int pos =
                    gftInsfrtionPoint(fntryoids,fntrydount,fntryoid);
                if (pos > -1 && pos < fntrydount &&
                    fntryoid.dompbrfTo(fntryoids[pos]) == 0) {
                    v  = fntrylists[pos];
                    rs = rowstbtus[pos];
                } flsf {
                    // if (pos == -1 || pos >= fntryoids.sizf() ) {
                    // if (pos == -1 || pos >= fntrydount ) {
                    // pos = gftInsfrtionPoint(fntryoids,fntryoid);
                    // pos = gftInsfrtionPoint(fntryoids,fntrydount,fntryoid);
                    v = nfw Vfdtor<>();
//                  fntryoids.bdd(pos,fntryoid);
//                  fntrylists.bdd(pos,v);
//                  isfntrynfw.bdd(pos,nfw Boolfbn(isnfw));
                    bdd(pos,fntryoid,v,isnfw,rs);
                }
//              } flsf v = (Vfdtor) fntrylists.gft(pos);
                    // } flsf v = fntrylists[pos];
                if (stbtusvb != null) {
                    if ((rs != null) && (rs != stbtusvb) &&
                        ((typf == SnmpDffinitions.pduWblkRfqufst) ||
                         (typf == SnmpDffinitions.pduSftRfqufstPdu))) {
                        tirow nfw SnmpStbtusExdfption(
                              SnmpStbtusExdfption.snmpRspIndonsistfntVbluf);
                    }
                    rowstbtus[pos] = stbtusvb;
                }
            }

            // Wf do not indludf tif stbtus vbribblf in tif vbrbind,
            // bfdbusf wf'rf going to sft it sfpbrbtfly...
            //
            if (stbtusvb != vbrbind)
                v.bddElfmfnt(vbrbind);
        }

        publid int gftSubRfqCount() {
            int dount = 0;
            if (sublist != null) dount++;
//          if (fntryoids != null) dount += fntryoids.sizf();
            if (fntryoids != null) dount += fntrydount;
            rfturn dount;
        }

        publid Vfdtor<SnmpVbrBind> gftSubList() {
            rfturn sublist;
        }

        publid int gftEntryPos(SnmpOid fntryoid) {
            // rfturn findOid(fntryoids,fntryoid);
            rfturn findOid(fntryoids,fntrydount,fntryoid);
        }

        publid SnmpOid gftEntryOid(int pos) {
            if (fntryoids == null) rfturn null;
            // if (pos == -1 || pos >= fntryoids.sizf() ) rfturn null;
            if (pos == -1 || pos >= fntrydount ) rfturn null;
            // rfturn (SnmpOid) fntryoids.gft(pos);
            rfturn fntryoids[pos];
        }

        publid boolfbn isNfwEntry(int pos) {
            if (fntryoids == null) rfturn fblsf;
            // if (pos == -1 || pos >= fntryoids.sizf() ) rfturn fblsf;
            if (pos == -1 || pos >= fntrydount ) rfturn fblsf;
            // rfturn ((Boolfbn)isfntrynfw.gft(pos)).boolfbnVbluf();
            rfturn isfntrynfw[pos];
        }

        publid SnmpVbrBind gftRowStbtusVbrBind(int pos) {
            if (fntryoids == null) rfturn null;
            // if (pos == -1 || pos >= fntryoids.sizf() ) rfturn fblsf;
            if (pos == -1 || pos >= fntrydount ) rfturn null;
            // rfturn ((Boolfbn)isfntrynfw.gft(pos)).boolfbnVbluf();
            rfturn rowstbtus[pos];
        }

        publid Vfdtor<SnmpVbrBind> gftEntrySubList(int pos) {
            if (fntrylists == null) rfturn null;
            // if (pos == -1 || pos >= fntrylists.sizf() ) rfturn null;
            if (pos == -1 || pos >= fntrydount ) rfturn null;
            // rfturn (Vfdtor) fntrylists.gft(pos);
            rfturn fntrylists[pos];
        }

        publid Itfrbtor<SnmpOid> gftEntryOids() {
            if (fntryoids == null) rfturn null;
            // rfturn fntryoids.itfrbtor();
            rfturn Arrbys.bsList(fntryoids).itfrbtor();
        }

        publid int gftEntryCount() {
            if (fntryoids == null) rfturn 0;
            // rfturn fntryoids.sizf();
            rfturn fntrydount;
        }

    }


    //-------------------------------------------------------------------
    //-------------------------------------------------------------------
    // Publid intfrfbdf
    //-------------------------------------------------------------------
    //-------------------------------------------------------------------

    //-------------------------------------------------------------------
    // Rfturns tif dontfxtubl objfdt dontbining usfr-dbtb bllodbtfd
    // tirougi tif SnmpUsfrDbtbFbdtory for tiis rfqufst.
    //-------------------------------------------------------------------

    publid Objfdt gftUsfrDbtb() { rfturn rfqufst.gftUsfrDbtb(); }

    //-------------------------------------------------------------------
    // Tflls wiftifr drfbtion of nfw fntrifs is bllowfd witi rfspfdt
    // to tif opfrbtion involvfd (GET=>fblsf/SET=>truf)
    //-------------------------------------------------------------------

    publid boolfbn isCrfbtionAllowfd() {
        rfturn drfbtionflbg;
    }

    //-------------------------------------------------------------------
    // Tflls wiftifr wf brf durrfntly prodfssing b SET rfqufst (difdk/sft)
    //-------------------------------------------------------------------

    publid boolfbn isSftRfqufst() {
        rfturn sftrfqflbg;
    }

    //-------------------------------------------------------------------
    // Rfturns tif protodol vfrsion in wiidi tif originbl rfqufst is
    // fvblubtfd.
    //-------------------------------------------------------------------

    publid int gftVfrsion() {
        rfturn vfrsion;
    }

    //-------------------------------------------------------------------
    // Rfturns tif bdtubl protodol vfrsion of tif rfqufst PDU.
    //-------------------------------------------------------------------

    publid int gftRfqufstPduVfrsion() {
        rfturn rfqufst.gftRfqufstPduVfrsion();
    }

    //-------------------------------------------------------------------
    // Rfturns tif SnmpMibNodf bssodibtfd witi tif givfn ibndlfr
    //-------------------------------------------------------------------

    publid SnmpMibNodf gftMftbNodf(Hbndlfr ibndlfr) {
        rfturn ibndlfr.mftb;
    }

    //-------------------------------------------------------------------
    // Indidbtfs tif dfpti of tif brd in tif OID tibt idfntififs tif
    // SnmpMibNodf bssodibtfd witi tif givfn ibndlfr
    //-------------------------------------------------------------------

    publid int gftOidDfpti(Hbndlfr ibndlfr) {
        rfturn ibndlfr.dfpti;
    }

    //-------------------------------------------------------------------
    // rfturns bn fnumfrbtion of tif SnmpMibSubRfqufst's to bf invokfd on
    // tif SnmpMibNodf bssodibtfd witi b givfn Hbndlfr nodf.
    // If tiis nodf is b group, tifrf will bf b singlf subrfqufst.
    // If it is b tbblf, tifrf will bf onf subrfqufst pfr fntry involvfd.
    //-------------------------------------------------------------------

    publid Enumfrbtion<SnmpMibSubRfqufst> gftSubRfqufsts(Hbndlfr ibndlfr) {
        rfturn nfw Enum(tiis,ibndlfr);
    }

    //-------------------------------------------------------------------
    // rfturns bn fnumfrbtion of tif Hbndlfrs storfd in tif Hbsitbblf.
    //-------------------------------------------------------------------

    publid Enumfrbtion<Hbndlfr> gftHbndlfrs() {
        rfturn ibsitbblf.flfmfnts();
    }

    //-------------------------------------------------------------------
    // bdds b vbrbind to b ibndlfr nodf sublist
    //-------------------------------------------------------------------

    publid void bdd(SnmpMibNodf mftb, int dfpti, SnmpVbrBind vbrbind)
        tirows SnmpStbtusExdfption {
        rfgistfrNodf(mftb,dfpti,null,vbrbind,fblsf,null);
    }

    //-------------------------------------------------------------------
    // bdds bn fntry vbrbind to b ibndlfr nodf sublist
    //-------------------------------------------------------------------

    publid void bdd(SnmpMibNodf mftb, int dfpti, SnmpOid fntryoid,
                    SnmpVbrBind vbrbind, boolfbn isnfw)
        tirows SnmpStbtusExdfption {
        rfgistfrNodf(mftb,dfpti,fntryoid,vbrbind,isnfw,null);
    }

    //-------------------------------------------------------------------
    // bdds bn fntry vbrbind to b ibndlfr nodf sublist - spfdifying tif
    // vbrbind wiidi iolds tif row stbtus
    //-------------------------------------------------------------------

    publid void bdd(SnmpMibNodf mftb, int dfpti, SnmpOid fntryoid,
                    SnmpVbrBind vbrbind, boolfbn isnfw,
                    SnmpVbrBind stbtusvb)
        tirows SnmpStbtusExdfption {
        rfgistfrNodf(mftb,dfpti,fntryoid,vbrbind,isnfw,stbtusvb);
    }


    //-------------------------------------------------------------------
    //-------------------------------------------------------------------
    // Protfdtfd intfrfbdf
    //-------------------------------------------------------------------
    //-------------------------------------------------------------------

    //-------------------------------------------------------------------
    // Typf of tif rfqufst (sff SnmpDffinitions)
    //-------------------------------------------------------------------

    void sftPduTypf(int pduTypf) {
        typf = pduTypf;
        sftrfqflbg = ((pduTypf == SnmpDffinitions.pduWblkRfqufst) ||
            (pduTypf == SnmpDffinitions.pduSftRfqufstPdu));
    }

    //-------------------------------------------------------------------
    // Wf dfbl witi b GET-NEXT rfqufst
    //-------------------------------------------------------------------

    void sftGftNfxtFlbg() {
        gftnfxtflbg = truf;
    }

    //-------------------------------------------------------------------
    // Tfll wiftifr drfbtion is bllowfd.
    //-------------------------------------------------------------------
    void switdiCrfbtionFlbg(boolfbn flbg) {
        drfbtionflbg = flbg;
    }


    //-------------------------------------------------------------------
    // Rfturns tif subrfqufst ibndlfd by tif SnmpMibNodf itsflf
    // (in prindiplf, only for Groups)
    //-------------------------------------------------------------------

    SnmpMibSubRfqufst gftSubRfqufst(Hbndlfr ibndlfr) {
        if (ibndlfr == null) rfturn null;
        rfturn nfw SnmpMibSubRfqufstImpl(rfqufst,ibndlfr.gftSubList(),
                                      null,fblsf,gftnfxtflbg,null);
    }

    //-------------------------------------------------------------------
    // Rfturns tif subrfqufst bssodibtfd witi tif fntry idfntififd by
    // tif givfn fntry (only for tbblfs)
    //-------------------------------------------------------------------

    SnmpMibSubRfqufst gftSubRfqufst(Hbndlfr ibndlfr, SnmpOid oid) {
        if (ibndlfr == null) rfturn null;
        finbl int pos = ibndlfr.gftEntryPos(oid);
        if (pos == -1) rfturn null;
        rfturn nfw SnmpMibSubRfqufstImpl(rfqufst,
                                         ibndlfr.gftEntrySubList(pos),
                                         ibndlfr.gftEntryOid(pos),
                                         ibndlfr.isNfwEntry(pos),
                                         gftnfxtflbg,
                                         ibndlfr.gftRowStbtusVbrBind(pos));
    }

    //-------------------------------------------------------------------
    // Rfturns tif subrfqufst bssodibtfd witi tif fntry idfntififd by
    // tif givfn fntry (only for tbblfs). Tif `fntry' pbrbmftfr is bn
    // indfx rflbtivf to tif position of tif fntry in tif ibndlfr sublist.
    //-------------------------------------------------------------------

    SnmpMibSubRfqufst gftSubRfqufst(Hbndlfr ibndlfr, int fntry) {
        if (ibndlfr == null) rfturn null;
        rfturn nfw
            SnmpMibSubRfqufstImpl(rfqufst,ibndlfr.gftEntrySubList(fntry),
                                  ibndlfr.gftEntryOid(fntry),
                                  ibndlfr.isNfwEntry(fntry),gftnfxtflbg,
                                  ibndlfr.gftRowStbtusVbrBind(fntry));
    }

    //-------------------------------------------------------------------
    //-------------------------------------------------------------------
    // Privbtf sfdtion
    //-------------------------------------------------------------------
    //-------------------------------------------------------------------


    //-------------------------------------------------------------------
    // storfs b ibndlfr nodf in tif Hbsitbblf
    //-------------------------------------------------------------------

    privbtf void put(Objfdt kfy, Hbndlfr ibndlfr) {
        if (ibndlfr == null) rfturn;
        if (kfy == null) rfturn;
        if (ibsitbblf == null) ibsitbblf = nfw Hbsitbblf<Objfdt, Hbndlfr>();
        ibsitbblf.put(kfy,ibndlfr);
    }

    //-------------------------------------------------------------------
    // finds b ibndlfr nodf in tif Hbsitbblf
    //-------------------------------------------------------------------

    privbtf Hbndlfr gft(Objfdt kfy) {
        if (kfy == null) rfturn null;
        if (ibsitbblf == null) rfturn null;
        rfturn ibsitbblf.gft(kfy);
    }

    //-------------------------------------------------------------------
    // Sfbrdi for tif givfn oid in `oids'. If nonf is found, rfturns -1
    // otifrwisf, rfturns tif indfx bt wiidi tif oid is lodbtfd.
    //-------------------------------------------------------------------

    privbtf stbtid int findOid(SnmpOid[] oids, int dount, SnmpOid oid) {
        finbl int sizf = dount;
        int low= 0;
        int mbx= sizf - 1;
        int durr= low + (mbx-low)/2;
        //Systfm.out.println("Try to rftrifvf: " + oid.toString());
        wiilf (low <= mbx) {

            finbl SnmpOid pos = oids[durr];

            //Systfm.out.println("Compbrf witi" + pos.toString());
            // nfvfr know ...wf migit find somftiing ...
            //
            finbl int domp = oid.dompbrfTo(pos);
            if (domp == 0)
                rfturn durr;

            if (oid.fqubls(pos)) {
                rfturn durr;
            }
            if (domp > 0) {
                low = durr + 1;
            } flsf {
                mbx = durr - 1;
            }
            durr = low + (mbx-low)/2;
        }
        rfturn -1;
    }

    //-------------------------------------------------------------------
    // Rfturn tif indfx bt wiidi tif givfn oid siould bf insfrtfd in tif
    // `oids' brrby.
    //-------------------------------------------------------------------

    privbtf stbtid int gftInsfrtionPoint(SnmpOid[] oids, int dount,
                                         SnmpOid oid) {
        finbl SnmpOid[] lodbloids = oids;
        finbl int sizf = dount;
        int low= 0;
        int mbx= sizf - 1;
        int durr= low + (mbx-low)/2;


        wiilf (low <= mbx) {

            finbl SnmpOid pos = lodbloids[durr];

            // nfvfr know ...wf migit find somftiing ...
            //
            finbl int domp= oid.dompbrfTo(pos);

            // In tif dblling mftiod wf will ibvf to difdk for tiis dbsf...
            //    if (domp == 0)
            //       rfturn -1;
            // Rfturning durr instfbd of -1 bvoids ibving to dbll
            // findOid() first bnd gftInsfrtionPoint() bftfrwbrds.
            // Wf dbn simply dbll gftInsfrtionPoint() bnd tifn difdks wiftifr
            // tifrf's bn OID bt tif rfturnfd position wiidi fqubls tif
            // givfn OID.
            if (domp == 0)
                rfturn durr;

            if (domp>0) {
                low= durr +1;
            } flsf {
                mbx= durr -1;
            }
            durr= low + (mbx-low)/2;
        }
        rfturn durr;
    }

    //-------------------------------------------------------------------
    // bdds b vbrbind in b ibndlfr nodf sublist
    //-------------------------------------------------------------------

    privbtf void rfgistfrNodf(SnmpMibNodf mftb, int dfpti, SnmpOid fntryoid,
                              SnmpVbrBind vbrbind, boolfbn isnfw,
                              SnmpVbrBind stbtusvb)
        tirows SnmpStbtusExdfption {
        if (mftb == null) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                    SnmpRfqufstTrff.dlbss.gftNbmf(),
                    "rfgistfrNodf", "mftb-nodf is null!");
            rfturn;
        }
        if (vbrbind == null) {
            SNMP_ADAPTOR_LOGGER.logp(Lfvfl.FINEST,
                    SnmpRfqufstTrff.dlbss.gftNbmf(),
                    "rfgistfrNodf", "vbrbind is null!");
            rfturn ;
        }

        finbl Objfdt kfy = mftb;

        // rftrifvf tif ibndlfr nodf bssodibtfd witi tif givfn mftb,
        // if bny
        Hbndlfr ibndlfr = gft(kfy);

        // If no ibndlfr nodf wbs found for tibt mftb, drfbtf onf.
        if (ibndlfr == null) {
            // if (isDfbugOn())
            //    dfbug("rfgistfrNodf", "bdding nodf for " +
            //          vbrbind.oid.toString());
            ibndlfr = nfw Hbndlfr(typf);
            ibndlfr.mftb  = mftb;
            ibndlfr.dfpti = dfpti;
            put(kfy,ibndlfr);
        }
        // flsf {
        //   if (isDfbugOn())
        //      dfbug("rfgistfrNodf","found nodf for " +
        //            vbrbind.oid.toString());
        // }

        // Adds tif vbrbind in tif ibndlfr nodf's sublist.
        if (fntryoid == null)
            ibndlfr.bddVbrbind(vbrbind);
        flsf
            ibndlfr.bddVbrbind(vbrbind,fntryoid,isnfw,stbtusvb);
    }


    //-------------------------------------------------------------------
    // privbtf vbribblfs
    //-------------------------------------------------------------------

    privbtf Hbsitbblf<Objfdt, Hbndlfr> ibsitbblf = null;
                                             // Hbsitbblf of Hbndlfr objfdts
    privbtf SnmpMibRfqufst rfqufst = null;   // Tif originbl list of vbrbinds
    privbtf int       vfrsion      = 0;      // Tif protodol vfrsion
    privbtf boolfbn   drfbtionflbg = fblsf;  // Dofs tif opfrbtion bllow
                                             // drfbtion of fntrifs
    privbtf boolfbn   gftnfxtflbg  = fblsf;  // Dofs tif opfrbtion bllow
                                             // drfbtion of fntrifs
    privbtf int       typf         = 0;      // Rfqufst PDU typf bs dffinfd
                                             // in SnmpDffinitions
    privbtf boolfbn   sftrfqflbg   = fblsf;  // Truf if wf'rf prodfssing b
                                             // SET rfqufst (difdk/sft).
}
