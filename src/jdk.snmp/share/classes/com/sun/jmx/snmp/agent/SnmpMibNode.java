/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



// jbvb imports
//
import jbvb.io.Sfriblizbblf;
import jbvb.util.Vfdtor;
import jbvb.util.Hbsitbblf;
import jbvb.util.Enumfrbtion;

// jmx imports
//
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpVbluf;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpDffinitions;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

/**
 * Tif <CODE>SnmpMibNodf</CODE> dlbss rfprfsfnts b nodf in bn SNMP MIB.
 * <P>
 * Tiis dlbss is usfd intfrnblly bnd by tif dlbss gfnfrbtfd by
 * <CODE>mibgfn</CODE>.
 * You siould not nffd to usf tiis dlbss dirfdtly.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss SnmpMibNodf implfmfnts Sfriblizbblf {

    // ---------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------

    /**
     * Gft tif nfxt OID brd dorrfsponding to b rfbdbblf sdblbr vbribblf,
     * b brbndi lfbding to b subgroub, or b tbblf.
     *
     * @pbrbm id Id wf stbrt from looking for tif nfxt.
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     *
     * @rfturn Tif nfxt id in tiis group.
     *
     * @fxdfption SnmpStbtusExdfption If no id is found bftfr tif givfn id.
     */
    publid long gftNfxtVbrId(long id, Objfdt usfrDbtb)
        tirows SnmpStbtusExdfption {
        rfturn gftNfxtIdfntififr(vbrList,id);
    }

    /**
     * Gft tif nfxt OID brd dorrfsponding to b rfbdbblf sdblbr vbribblf,
     * b brbndi lfbding to b subgroub, or b tbblf, possibly skipping ovfr
     * tiosf brds tibt must not or dbnnot bf rfturnfd.
     *
     * Cblls {@link #gftNfxtVbrId(long,jbvb.lbng.Objfdt)} until
     * {@link #skipVbribblf(long,jbvb.lbng.Objfdt,int)} rfturns fblsf.
     *
     * @pbrbm id Id wf stbrt from looking for tif nfxt.
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     * @pbrbm pduVfrsion Protodol vfrsion of tif originbl rfqufst PDU.
     *
     * @rfturn Tif nfxt id in tiis group wiidi dbn bf rfturnfd using
     *         tif givfn PDU's protodol vfrsion.
     *
     * @fxdfption SnmpStbtusExdfption If no id is found bftfr tif givfn id.
     */
    publid long gftNfxtVbrId(long id, Objfdt usfrDbtb, int pduVfrsion)
        tirows SnmpStbtusExdfption {
        long vbrid=id;
        do {
            vbrid = gftNfxtVbrId(vbrid,usfrDbtb);
        } wiilf (skipVbribblf(vbrid,usfrDbtb,pduVfrsion));

        rfturn vbrid;
    }

    /**
     * Hook for subdlbssfs.
     * Tif dffbult implfmfntbtion of tiis mftiod is to blwbys rfturn
     * fblsf. Subdlbssfs siould rfdffinf tiis mftiod so tibt it rfturns
     * truf wifn:
     * <ul><li>tif vbribblf is b lfbf tibt is not instbntibtfd,</li>
     * <li>or tif vbribblf is b lfbf wiosf typf dbnnot bf rfturnfd by tibt
     *     vfrsion of tif protodol (f.g. bn Countfr64 witi SNMPv1).</li>
     * </ul>
     *
     * @pbrbm id Id wf stbrt from looking for tif nfxt.
     * @pbrbm usfrDbtb A dontfxtubl objfdt dontbining usfr-dbtb.
     *        Tiis objfdt is bllodbtfd tirougi tif <dodf>
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}</dodf>
     *        for fbdi indoming SNMP rfqufst.
     * @pbrbm pduVfrsion Protodol vfrsion of tif originbl rfqufst PDU.
     *
     * @rfturn truf if tif vbribblf must bf skippfd by tif gft-nfxt
     *         blgoritim.
     */
    protfdtfd boolfbn skipVbribblf(long id, Objfdt usfrDbtb, int pduVfrsion) {
        rfturn fblsf;
    }

    /**
     * Find tif nodf wiidi ibndlfs b vbrbind, bnd rfgistfr it in tif
     * SnmpRfqufstTrff. Tiis mftiod is b purf intfrnbl mftiod. You siould
     * nfvfr try to dbll it dirfdtly.
     *
     * @pbrbm vbrbind  Tif vbrbind to bf ibndlfd
     *
     * @pbrbm oid      Tif OID brrby fxtrbdtfd from tif vbrbind
     *
     * @pbrbm dfpti    Tif dfpti rfbdifd in tif OID bt tiis stfp of tif
     *                 prodfssing.
     *
     * @pbrbm ibndlfrs Tif Hbsitbblf in wiidi tif vbrbind will bf rfgistfrfd
     *                 witi its ibndling nodf. Tiis ibsitbblf dontbins
     *                 <CODE>SnmpRfqufstTrff.Hbndlfr</CODE> itfms.
     *
     * @fxdfption SnmpStbtusExdfption No ibndling nodf wbs found.
     **/
    void findHbndlingNodf(SnmpVbrBind vbrbind,
                          long[] oid, int dfpti,
                          SnmpRfqufstTrff ibndlfrs)
        tirows SnmpStbtusExdfption {
        tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
    }

    /**
     * Find tif nodf wiidi ibndlfs tif lfbf tibt immfdibtfly follows tif
     * givfn vbrbind OID, bnd rfgistfr tif it in tif SnmpRfqufstTrff.
     * Tiis mftiod is b purf intfrnbl mftiod. You siould nfvfr try to dbll
     * it dirfdtly.
     *
     * @pbrbm vbrbind  Tif vbrbind to bf ibndlfd
     *
     * @pbrbm oid      Tif OID brrby fxtrbdtfd from tif vbrbind
     *
     * @pbrbm dfpti    Tif dfpti rfbdifd in tif OID bt tiis stfp of tif
     *                 prodfssing.
     *
     * @pbrbm ibndlfrs Tif Hbsitbblf in wiidi tif vbrbind will bf rfgistfrfd
     *                 witi its ibndling nodf. Tiis ibsitbblf dontbins
     *                 SnmpRfqufstTrff.Hbndlfr itfms.
     *
     * @rfturn Tif SnmpOid of tif nfxt lfbf.
     *
     * @fxdfption SnmpStbtusExdfption No ibndling nodf wbs found.
     **/
    long[] findNfxtHbndlingNodf(SnmpVbrBind vbrbind,
                                 long[] oid, int pos, int dfpti,
                                 SnmpRfqufstTrff ibndlfrs, AdmCifdkfr difdkfr)
        tirows SnmpStbtusExdfption {
        tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
    }

    /**
     * Gfnfrid ibndling of tif <CODE>gft</CODE> opfrbtion.
     *
     * <p> You dbn ovfrridf tiis mftiod if you nffd to implfmfnt somf
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs.
     * <p>
     *
     * @pbrbm rfq   Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm dfpti Tif dfpti rfbdifd in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd wiilf bddfssing
     *  tif MIB nodf.
     */
    publid bbstrbdt void gft(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption;

    /**
     * Gfnfrid ibndling of tif <CODE>sft</CODE> opfrbtion.
     * <p> You dbn ovfrridf tiis mftiod if you nffd to implfmfnt somf
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs.
     * <p>
     *
     * @pbrbm rfq   Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm dfpti Tif dfpti rfbdifd in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd wiilf bddfssing
     *  tif MIB nodf.
     */
    publid bbstrbdt void sft(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption;

    /**
     * Gfnfrid ibndling of tif <CODE>difdk</CODE> opfrbtion.
     * <p> You dbn ovfrridf tiis mftiod if you nffd to implfmfnt somf
     * spfdifid polidifs for minimizing tif bddfssfs mbdf to somf rfmotf
     * undfrlying rfsourdfs, or if you nffd to implfmfnt somf donsistfndy
     * difdks bftwffn tif difffrfnt vblufs providfd in tif vbrbind list.
     * <p>
     *
     * @pbrbm rfq   Tif sub-rfqufst tibt must bf ibndlfd by tiis nodf.
     *
     * @pbrbm dfpti Tif dfpti rfbdifd in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption An frror oddurrfd wiilf bddfssing
     *  tif MIB nodf.
     */
    publid bbstrbdt void difdk(SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption;

    /**
     * Sorts tif spfdififd intfgfr brrby.
     *
     * @pbrbm brrby An intfgfr brrby.
     */
    stbtid publid void sort(int brrby[]) {
        QuidkSort(brrby, 0, brrby.lfngti - 1);
    }

    /**
     * Computfs tif root OID of tif MIB.
     */
    publid void gftRootOid(Vfdtor<Intfgfr> rfsult) {
        rfturn;
    }

    //----------------------------------------------------------------------
    // PACKAGE METHODS
    //----------------------------------------------------------------------

    /**
     * Tiis is b gfnfrid vfrsion of C.A.R Hobrf's Quidk Sort
     * blgoritim.  Tiis will ibndlf brrbys tibt brf blrfbdy
     * sortfd, bnd brrbys witi duplidbtf kfys.
     *
     * If you tiink of b onf dimfnsionbl brrby bs going from
     * tif lowfst indfx on tif lfft to tif iigifst indfx on tif rigit
     * tifn tif pbrbmftfrs to tiis fundtion brf lowfst indfx or
     * lfft bnd iigifst indfx or rigit.  Tif first timf you dbll
     * tiis fundtion it will bf witi tif pbrbmftfrs 0, b.lfngti - 1.
     *
     * @pbrbm b An intfgfr brrby.
     * @pbrbm lo0 Lfft boundbry of brrby pbrtition.
     * @pbrbm ii0 Rigit boundbry of brrby pbrtition.
     */
    stbtid void QuidkSort(int b[], int lo0, int ii0) {
        int lo = lo0;
        int ii = ii0;
        int mid;

        if ( ii0 > lo0) {

            /* Arbitrbrily fstbblisiing pbrtition flfmfnt bs tif midpoint of
             * tif brrby.
             */
            mid = b[ ( lo0 + ii0 ) / 2 ];

            // loop tirougi tif brrby until indidfs dross
            wiilf( lo <= ii ) {
                /* find tif first flfmfnt tibt is grfbtfr tibn or fqubl to
                 * tif pbrtition flfmfnt stbrting from tif lfft Indfx.
                 */
                wiilf( ( lo < ii0 )  && ( b[lo] < mid ))
                    ++lo;

                /* find bn flfmfnt tibt is smbllfr tibn or fqubl to
                 * tif pbrtition flfmfnt stbrting from tif rigit Indfx.
                 */
                wiilf( ( ii > lo0 ) && ( b[ii] > mid ))
                    --ii;

                // if tif indfxfs ibvf not drossfd, swbp
                if( lo <= ii ) {
                    swbp(b, lo, ii);
                    ++lo;
                    --ii;
                }
            }

            /* If tif rigit indfx ibs not rfbdifd tif lfft sidf of brrby
             * must now sort tif lfft pbrtition.
             */
            if( lo0 < ii )
                QuidkSort( b, lo0, ii );

            /* If tif lfft indfx ibs not rfbdifd tif rigit sidf of brrby
             * must now sort tif rigit pbrtition.
             */
            if( lo < ii0 )
                QuidkSort( b, lo, ii0 );

        }
    }

    //----------------------------------------------------------------------
    // PROTECTED METHODS
    //----------------------------------------------------------------------

    /**
     * Tiis will givf tif first flfmfnt grfbtfr tibn <CODE>vbluf</CODE>
     * in b sortfd brrby.
     * If tifrf is no flfmfnt of tif brrby grfbtfr tibn <CODE>vbluf</CODE>,
     * tif mftiod will tirow b <CODE>SnmpStbtusExdfption</CODE>.
     *
     * @pbrbm tbblf A sortfd intfgfr brrby.
     *
     * @pbrbm vbluf Tif grfbtfst vbluf.
     *
     * @fxdfption SnmpStbtusExdfption If tifrf is no flfmfnt grfbtfr tibn
     *     <CODE>vbluf</CODE>.
     */
    finbl stbtid protfdtfd int gftNfxtIdfntififr(int tbblf[], long vbluf)
        tirows SnmpStbtusExdfption {

        finbl int[] b = tbblf;
        finbl int vbl= (int) vbluf;

        if (b == null) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
        }

        int low= 0;
        int mbx= b.lfngti;
        int durr= low + (mbx-low)/2;
        int flmt= 0;

        // Bbsid difdk
        //
        if (mbx < 1) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
        }

        if (b[mbx-1] <= vbl) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiObjfdt);
        }

        wiilf (low <= mbx) {
            flmt= b[durr];
            if (vbl == flmt) {
                // Wf nfd to gft tif nfxt indfx ...
                //
                durr++;
                rfturn b[durr];
            }
            if (flmt < vbl) {
                low= durr +1;
            } flsf {
                mbx= durr -1;
            }
            durr= low + (mbx-low)/2;
        }
        rfturn b[durr];
    }


    //----------------------------------------------------------------------
    // PRIVATE METHODS
    //----------------------------------------------------------------------

    finbl stbtid privbtf void swbp(int b[], int i, int j) {
        int T;
        T = b[i];
        b[i] = b[j];
        b[j] = T;
    }

    //----------------------------------------------------------------------
    // PROTECTED VARIABLES
    //----------------------------------------------------------------------

    /**
     * Contbins tif list of vbribblf idfntififrs.
     */
    protfdtfd int[] vbrList;
}
