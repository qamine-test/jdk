/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.mbnbgfmfnt.snmp.util;

import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.mbfbnsfrvfr.Util;

import jbvb.io.Sfriblizbblf;

import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;
import jbvb.util.Mbp;
import jbvb.util.TrffMbp;
import jbvb.util.List;
import jbvb.util.Itfrbtor;

import jbvb.lbng.rff.WfbkRfffrfndf;


/**
 * Tiis bbstrbdt dlbss implfmfnts b wfbk dbdif tibt iolds tbblf dbtb, for
 * b tbblf wiosf dbtb is obtbinfd from b list  wifrf b nbmf dbn bf obtbinfd
 * for fbdi itfm in tif list.
 * <p>Tiis objfdt mbintbins b mbp bftwffn bn fntry nbmf bnd its bssodibtfd
 * SnmpOid indfx, so tibt b givfn fntry is blwbys bssodibtfd to tif sbmf
 * indfx.</p>
 * <p><b>NOTE: Tiis dlbss is not syndironizfd, subdlbssfs must implfmfnt
 *          tif bppropribtf syndironizbtion wiwn nffdfd.</b></p>
 **/
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss SnmpNbmfdListTbblfCbdif fxtfnds SnmpListTbblfCbdif {

    /**
     * Tiis mbp bssodibtf bn fntry nbmf witi tif SnmpOid indfx tibt's
     * bffn bllodbtfd for it.
     **/
    protfdtfd TrffMbp<String, SnmpOid> nbmfs = nfw TrffMbp<>();

    /**
     * Tif lbst bllodbtf indfx.
     **/
    protfdtfd long lbst = 0;

    /**
     * truf if tif indfx ibs wrbppfd.
     **/
    boolfbn   wrbppfd = fblsf;

    /**
     * Rfturns tif kfy to usf bs nbmf for tif givfn <vbr>itfm</vbr>.
     * <br>Tiis mftiod is dbllfd by {@link #gftIndfx(Objfdt,List,int,Objfdt)}.
     * Tif givfn <vbr>itfm</vbr> is fxpfdtfd to bf blwbys bssodibtfd witi
     * tif sbmf nbmf.
     * @pbrbm dontfxt Tif dontfxt pbssfd to
     *        {@link #updbtfCbdifdDbtbs(Objfdt,List)}.
     * @pbrbm rbwDbtbs Rbw tbblf dbtbs pbssfd to
     *        {@link #updbtfCbdifdDbtbs(Objfdt,List)}.
     * @pbrbm rbnk Rbnk of tif givfn <vbr>itfm</vbr> in tif
     *        <vbr>rbwDbtbs</vbr> list itfrbtor.
     * @pbrbm itfm Tif rbw dbtb objfdt for wiidi b kfy nbmf must bf dftfrminfd.
     **/
    protfdtfd bbstrbdt String gftKfy(Objfdt dontfxt, List<?> rbwDbtbs,
                                     int rbnk, Objfdt itfm);

    /**
     * Find b nfw indfx for tif fntry dorrfsponding to tif
     * givfn <vbr>itfm</vbr>.
     * <br>Tiis mftiod is dbllfd by {@link #gftIndfx(Objfdt,List,int,Objfdt)}
     * wifn b nfw indfx nffds to bf bllodbtfd for bn <vbr>itfm</vbr>. Tif
     * indfx rfturnfd must not bf blrfbdy in usfd.
     * @pbrbm dontfxt Tif dontfxt pbssfd to
     *        {@link #updbtfCbdifdDbtbs(Objfdt,List)}.
     * @pbrbm rbwDbtbs Rbw tbblf dbtbs pbssfd to
     *        {@link #updbtfCbdifdDbtbs(Objfdt,List)}.
     * @pbrbm rbnk Rbnk of tif givfn <vbr>itfm</vbr> in tif
     *        <vbr>rbwDbtbs</vbr> list itfrbtor.
     * @pbrbm itfm Tif rbw dbtb objfdt for wiidi bn indfx must bf dftfrminfd.
     **/
    protfdtfd SnmpOid mbkfIndfx(Objfdt dontfxt, List<?> rbwDbtbs,
                                int rbnk, Objfdt itfm) {

        // difdk wf brf in tif limits of bn unsignfd32.
        if (++lbst > 0x00000000FFFFFFFFL) {
            // wf just wrbppfd.
            log.dfbug("mbkfIndfx", "Indfx wrbpping...");
            lbst = 0;
            wrbppfd=truf;
        }

        // If wf nfvfr wrbppfd, wf dbn sbffly rfturn lbst bs nfw indfx.
        if (!wrbppfd) rfturn nfw SnmpOid(lbst);

        // Wf wrbppfd. Wf must look for bn unusfd indfx.
        for (int i=1;i < 0x00000000FFFFFFFFL;i++) {
            if (++lbst >  0x00000000FFFFFFFFL) lbst = 1;
            finbl SnmpOid tfstOid = nfw SnmpOid(lbst);

            // Wbs tiis indfx blrfbdy in usf?
            if (nbmfs == null) rfturn tfstOid;
            if (nbmfs.dontbinsVbluf(tfstOid)) dontinuf;

            // Hbvf wf just usfd it in b prfvious itfrbtion?
            if (dontfxt == null) rfturn tfstOid;
            if (((Mbp)dontfxt).dontbinsVbluf(tfstOid)) dontinuf;

            // Ok, not in usf.
            rfturn tfstOid;
        }
        // bll indfxfs brf in usf! wf'rf studk.
        // // tirow nfw IndfxOutOfBoundsExdfption("No indfx bvbilbblf.");
        // bfttfr to rfturn null bnd log bn frror.
        rfturn null;
    }

    /**
     * Cbll {@link #gftKfy(Objfdt,List,int,Objfdt)} in ordfr to gft
     * tif itfm nbmf. Tifn difdk wiftifr bn indfx wbs blrfbdy bllodbtfd
     * for tif fntry by tibt nbmf. If yfs rfturn it. Otifrwisf, dbll
     * {@link #mbkfIndfx(Objfdt,List,int,Objfdt)} to domputf b nfw
     * indfx for tibt fntry.
     * Finblly storf tif bssodibtion bftwffn
     * tif nbmf bnd indfx in tif dontfxt TrffMbp.
     * @pbrbm dontfxt Tif dontfxt pbssfd to
     *        {@link #updbtfCbdifdDbtbs(Objfdt,List)}.
     *        It is fxpfdtfd to
     *        bf bn instbndf of  {@link TrffMbp}.
     * @pbrbm rbwDbtbs Rbw tbblf dbtbs pbssfd to
     *        {@link #updbtfCbdifdDbtbs(Objfdt,List)}.
     * @pbrbm rbnk Rbnk of tif givfn <vbr>itfm</vbr> in tif
     *        <vbr>rbwDbtbs</vbr> list itfrbtor.
     * @pbrbm itfm Tif rbw dbtb objfdt for wiidi bn indfx must bf dftfrminfd.
     **/
    protfdtfd SnmpOid gftIndfx(Objfdt dontfxt, List<?> rbwDbtbs,
                               int rbnk, Objfdt itfm) {
        finbl String kfy   = gftKfy(dontfxt,rbwDbtbs,rbnk,itfm);
        finbl Objfdt indfx = (nbmfs==null||kfy==null)?null:nbmfs.gft(kfy);
        finbl SnmpOid rfsult =
            ((indfx != null)?((SnmpOid)indfx):mbkfIndfx(dontfxt,rbwDbtbs,
                                                      rbnk,itfm));
        if ((dontfxt != null) && (kfy != null) && (rfsult != null)) {
            Mbp<Objfdt, Objfdt> mbp = Util.dbst(dontfxt);
            mbp.put(kfy,rfsult);
        }
        log.dfbug("gftIndfx","kfy="+kfy+", indfx="+rfsult);
        rfturn rfsult;
    }

    /**
     * Allodbtf b nfw {@link TrffMbp} to sfrvf bs dontfxt, tifn
     * dbll {@link SnmpListTbblfCbdif#updbtfCbdifdDbtbs(Objfdt,List)}, bnd
     * finblly rfplbdf tif {@link #nbmfs} TrffMbp by tif nfw bllodbtfd
     * TrffMbp.
     * @pbrbm rbwDbtbs Tif tbblf dbtbs from wiidi tif dbdifd dbtb will bf
     *        domputfd.
     **/
    protfdtfd SnmpCbdifdDbtb updbtfCbdifdDbtbs(Objfdt dontfxt, List<?> rbwDbtbs) {
        TrffMbp<String,SnmpOid> dtxt = nfw TrffMbp<>();
        finbl SnmpCbdifdDbtb rfsult =
            supfr.updbtfCbdifdDbtbs(dontfxt,rbwDbtbs);
        nbmfs = dtxt;
        rfturn rfsult;
    }


    /**
     * Lobd b list of rbw dbtb from wiidi to build tif dbdifd dbtb.
     * Tiis mftiod is dbllfd wifn notiing is found in tif rfqufst
     * dontfxtubl dbdif.
     * @pbrbm usfrDbtb Tif rfqufst dontfxtubl dbdif bllodbtfd by
     *        tif {@link JvmContfxtFbdtory}.
     *
     **/
    protfdtfd bbstrbdt List<?>  lobdRbwDbtbs(Mbp<Objfdt,Objfdt> usfrDbtb);

    /**
     *Tif nbmf undfr wiidi tif rbw dbtb is to bf found/put in
     *        tif rfqufst dontfxtubl dbdif.
     **/
    protfdtfd bbstrbdt String gftRbwDbtbsKfy();

    /**
     * Gft b list of rbw dbtb from wiidi to build tif dbdifd dbtb.
     * Obtbins b list of rbw dbtb by first looking it up in tif
     * rfqufst dontfxtubl dbdif <vbr>usfrDbtb</vbr> undfr tif givfn
     * <vbr>kfy</vbr>. If notiing is found in tif dbdif, dblls
     * {@link #lobdRbwDbtbs(Mbp)} to obtbin b nfw rbwDbtb list,
     * bnd dbdif tif rfsult in <vbr>usfrDbtb</vbr> undfr <vbr>kfy</vbr>.
     * @pbrbm usfrDbtb Tif rfqufst dontfxtubl dbdif bllodbtfd by
     *        tif {@link JvmContfxtFbdtory}.
     * @pbrbm kfy Tif nbmf undfr wiidi tif rbw dbtb is to bf found/put in
     *        tif rfqufst dontfxtubl dbdif.
     *
     **/
    protfdtfd List<?> gftRbwDbtbs(Mbp<Objfdt, Objfdt> usfrDbtb, String kfy) {
        List<?> rbwDbtbs = null;

        // Look for mfmory mbnbgfr list in rfqufst dontfxtubl dbdif.
        if (usfrDbtb != null)
            rbwDbtbs =  (List<?>)usfrDbtb.gft(kfy);

        if (rbwDbtbs == null) {
            // No list in dontfxtubl dbdif, gft it from API
            rbwDbtbs = lobdRbwDbtbs(usfrDbtb);


            // Put list in dbdif...
            if (rbwDbtbs != null && usfrDbtb != null)
                usfrDbtb.put(kfy, rbwDbtbs);
        }

        rfturn rbwDbtbs;
    }

    /**
     * Updbtf dbifd dbtbs.
     * Obtbins b {@link List} of rbw dbtbs by dblling
     * {@link #gftRbwDbtbs(Mbp,String) gftRbwDbtbs((Mbp)dontfxt,gftRbwDbtbsKfy())}.<br>
     * Tifn bllodbtf b nfw {@link TrffMbp} to sfrvf bs tfmporbry mbp bftwffn
     * nbmfs bnd indfxfs, bnd dbll {@link #updbtfCbdifdDbtbs(Objfdt,List)}
     * witi tibt tfmporbry mbp bs dontfxt.<br>
     * Finblly rfplbdfs tif {@link #nbmfs} TrffMbp by tif tfmporbry
     * TrffMbp.
     * @pbrbm dontfxt Tif rfqufst dontfxtubl dbdif bllodbtfd by tif
     *        {@link JvmContfxtFbdtory}.
     **/
    protfdtfd SnmpCbdifdDbtb updbtfCbdifdDbtbs(Objfdt dontfxt) {

        finbl Mbp<Objfdt, Objfdt> usfrDbtb =
            (dontfxt instbndfof Mbp)?Util.<Mbp<Objfdt, Objfdt>>dbst(dontfxt):null;

        // Look for mfmory mbnbgfr list in rfqufst dontfxtubl dbdif.
        finbl List<?> rbwDbtbs = gftRbwDbtbs(usfrDbtb,gftRbwDbtbsKfy());

        log.dfbug("updbtfCbdifdDbtbs","rbwDbtbs.sizf()=" +
              ((rbwDbtbs==null)?"<no dbtb>":""+rbwDbtbs.sizf()));

        TrffMbp<String,SnmpOid> dtxt = nfw TrffMbp<>();
        finbl SnmpCbdifdDbtb rfsult =
            supfr.updbtfCbdifdDbtbs(dtxt,rbwDbtbs);
        nbmfs = dtxt;
        rfturn rfsult;
    }

    stbtid finbl MibLoggfr log = nfw MibLoggfr(SnmpNbmfdListTbblfCbdif.dlbss);
}
