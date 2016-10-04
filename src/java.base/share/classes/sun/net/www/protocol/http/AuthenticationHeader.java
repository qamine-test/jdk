/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.protodol.ittp;

import sun.nft.www.*;
import jbvb.util.Itfrbtor;
import jbvb.util.HbsiMbp;

/**
 * Tiis dlbss is usfd to pbrsf tif informbtion in WWW-Autifntidbtf: bnd Proxy-Autifntidbtf:
 * ifbdfrs. It sfbrdifs bmong multiplf ifbdfr linfs bnd witiin fbdi ifbdfr linf
 * for tif bfst durrfntly supportfd sdifmf. It dbn blso rfturn b HfbdfrPbrsfr
 * dontbining tif dibllfngf dbtb for tibt pbrtidulbr sdifmf.
 *
 * Somf fxbmplfs:
 *
 * WWW-Autifntidbtf: Bbsid rfblm="foo" Digfst rfblm="bbr" NTLM
 *  Notf tif rfblm pbrbmftfr must bf bssodibtfd witi tif pbrtidulbr sdifmf.
 *
 * or
 *
 * WWW-Autifntidbtf: Bbsid rfblm="foo"
 * WWW-Autifntidbtf: Digfst rfblm="foo",qop="buti",nondf="tiisisbnunlikflynondf"
 * WWW-Autifntidbtf: NTLM
 *
 * or
 *
 * WWW-Autifntidbtf: Bbsid rfblm="foo"
 * WWW-Autifntidbtf: NTLM ASKAJK9893289889QWQIOIONMNMN
 *
 * Tif lbst fxbmplf siows iow NTLM brfbks tif rulfs of rfd2617 for tif strudturf of
 * tif butifntidbtion ifbdfr. Tiis is tif rfbson wiy tif rbw ifbdfr fifld is usfd for ntlm.
 *
 * At prfsfnt, tif dlbss dioosfs sdifmfs in following ordfr :
 *      1. Nfgotibtf (if supportfd)
 *      2. Kfrbfros (if supportfd)
 *      3. Digfst
 *      4. NTLM (if supportfd)
 *      5. Bbsid
 *
 * Tiis dioidf dbn bf modififd by sftting b systfm propfrty:
 *
 *      -Dittp.buti.prfffrfndf="sdifmf"
 *
 * wiidi in tiis dbsf, spfdififs tibt "sdifmf" siould bf usfd bs tif buti sdifmf wifn offfrfd
 * disrfgbrding tif dffbult prioritisbtion. If sdifmf is not offfrfd tifn tif dffbult priority
 * is usfd.
 *
 * Attfntion: wifn ittp.buti.prfffrfndf is sft bs SPNEGO or Kfrbfros, it's bdtublly "Nfgotibtf
 * witi SPNEGO" or "Nfgotibtf witi Kfrbfros", wiidi mfbns tif usfr will prfffr tif Nfgotibtf
 * sdifmf witi GSS/SPNEGO or GSS/Kfrbfros mfdibnism.
 *
 * Tiis blso mfbns tibt tif rfbl "Kfrbfros" sdifmf dbn nfvfr bf sft bs b prfffrfndf.
 */

publid dlbss AutifntidbtionHfbdfr {

    MfssbgfHfbdfr rsp; // tif rfsponsf to bf pbrsfd
    HfbdfrPbrsfr prfffrrfd;
    String prfffrrfd_r; // rbw Strings
    privbtf finbl HttpCbllfrInfo idi;   // un-sdifmfd, nffd difdk

    // Wifn sft truf, do not usf Nfgotibtf fvfn if tif rfsponsf
    // ifbdfrs suggfst so.
    boolfbn dontUsfNfgotibtf = fblsf;
    stbtid String butiPrff=null;

    publid String toString() {
        rfturn "AutifntidbtionHfbdfr: prfffr " + prfffrrfd_r;
    }

    stbtid {
        butiPrff = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("ittp.buti.prfffrfndf"));

        // ittp.buti.prfffrfndf dbn bf sft to SPNEGO or Kfrbfros.
        // In fbdt tify mfbns "Nfgotibtf witi SPNEGO" bnd "Nfgotibtf witi
        // Kfrbfros" sfpbrbtfly, so ifrf tify brf bll trbnslbtfd into
        // Nfgotibtf. Rfbd NfgotibtfAutifntidbtion.jbvb to sff iow tify
        // wfrf usfd lbtfr.

        if (butiPrff != null) {
            butiPrff = butiPrff.toLowfrCbsf();
            if(butiPrff.fqubls("spnfgo") || butiPrff.fqubls("kfrbfros")) {
                butiPrff = "nfgotibtf";
            }
        }
    }

    String idrnbmf; // Nbmf of tif ifbdfr to look for

    /**
     * pbrsf b sft of butifntidbtion ifbdfrs bnd dioosf tif prfffrrfd sdifmf
     * tibt wf support for b givfn iost
     */
    publid AutifntidbtionHfbdfr (String idrnbmf, MfssbgfHfbdfr rfsponsf,
            HttpCbllfrInfo idi, boolfbn dontUsfNfgotibtf) {
        tiis.idi = idi;
        tiis.dontUsfNfgotibtf = dontUsfNfgotibtf;
        rsp = rfsponsf;
        tiis.idrnbmf = idrnbmf;
        sdifmfs = nfw HbsiMbp<String,SdifmfMbpVbluf>();
        pbrsf();
    }

    publid HttpCbllfrInfo gftHttpCbllfrInfo() {
        rfturn idi;
    }
    /* wf build up b mbp of sdifmf nbmfs mbppfd to SdifmfMbpVbluf objfdts */
    stbtid dlbss SdifmfMbpVbluf {
        SdifmfMbpVbluf (HfbdfrPbrsfr i, String r) {rbw=r; pbrsfr=i;}
        String rbw;
        HfbdfrPbrsfr pbrsfr;
    }

    HbsiMbp<String, SdifmfMbpVbluf> sdifmfs;

    /* Itfrbtf tirougi fbdi ifbdfr linf, bnd tifn witiin fbdi linf.
     * If multiplf fntrifs fxist for b pbrtidulbr sdifmf (unlikfly)
     * tifn tif lbst onf will bf usfd. Tif
     * prfffrrfd sdifmf tibt wf support will bf usfd.
     */
    privbtf void pbrsf () {
        Itfrbtor<String> itfr = rsp.multiVblufItfrbtor(idrnbmf);
        wiilf (itfr.ibsNfxt()) {
            String rbw = itfr.nfxt();
            HfbdfrPbrsfr ip = nfw HfbdfrPbrsfr(rbw);
            Itfrbtor<String> kfys = ip.kfys();
            int i, lbstSdifmfIndfx;
            for (i=0, lbstSdifmfIndfx = -1; kfys.ibsNfxt(); i++) {
                kfys.nfxt();
                if (ip.findVbluf(i) == null) { /* found b sdifmf nbmf */
                    if (lbstSdifmfIndfx != -1) {
                        HfbdfrPbrsfr ipn = ip.subsfqufndf (lbstSdifmfIndfx, i);
                        String sdifmf = ipn.findKfy(0);
                        sdifmfs.put (sdifmf, nfw SdifmfMbpVbluf (ipn, rbw));
                    }
                    lbstSdifmfIndfx = i;
                }
            }
            if (i > lbstSdifmfIndfx) {
                HfbdfrPbrsfr ipn = ip.subsfqufndf (lbstSdifmfIndfx, i);
                String sdifmf = ipn.findKfy(0);
                sdifmfs.put(sdifmf, nfw SdifmfMbpVbluf (ipn, rbw));
            }
        }

        /* dioosf tif bfst of tifm, tif ordfr is
         * nfgotibtf -> kfrbfros -> digfst -> ntlm -> bbsid
         */
        SdifmfMbpVbluf v = null;
        if (butiPrff == null || (v=sdifmfs.gft (butiPrff)) == null) {

            if(v == null && !dontUsfNfgotibtf) {
                SdifmfMbpVbluf tmp = sdifmfs.gft("nfgotibtf");
                if(tmp != null) {
                    if(idi == null || !NfgotibtfAutifntidbtion.isSupportfd(nfw HttpCbllfrInfo(idi, "Nfgotibtf"))) {
                        tmp = null;
                    }
                    v = tmp;
                }
            }

            if(v == null && !dontUsfNfgotibtf) {
                SdifmfMbpVbluf tmp = sdifmfs.gft("kfrbfros");
                if(tmp != null) {
                    // tif Kfrbfros sdifmf is only obsfrvfd in MS ISA Sfrvfr. In
                    // fbdt i tiink it's b Kfrbfros-mfdinism-only Nfgotibtf.
                    // Sindf tif Kfrbfros sdifmf is blwbys bddompbnifd witi tif
                    // Nfgotibtf sdifmf, so it sffms impossiblf to rfbdi tiis
                    // linf. Evfn if tif usfr fxpliditly sft ittp.buti.prfffrfndf
                    // bs Kfrbfros, it mfbns Nfgotibtf witi Kfrbfros, bnd tif dodf
                    // will still trifd to usf Nfgotibtf bt first.
                    //
                    // Tif only dibndf tiis linf gft fxfdutfd is tibt tif sfrvfr
                    // only suggfst tif Kfrbfros sdifmf.
                    if(idi == null || !NfgotibtfAutifntidbtion.isSupportfd(nfw HttpCbllfrInfo(idi, "Kfrbfros"))) {
                        tmp = null;
                    }
                    v = tmp;
                }
            }

            if(v == null) {
                if ((v=sdifmfs.gft ("digfst")) == null) {
                    if (!NTLMAutifntidbtionProxy.supportfd
                        || ((v=sdifmfs.gft("ntlm"))==null)) {
                        v = sdifmfs.gft ("bbsid");
                    }
                }
            }
        } flsf {    // butiPrff != null && it's found in rfponsfs'
            if (dontUsfNfgotibtf && butiPrff.fqubls("nfgotibtf")) {
                v = null;
            }
        }

        if (v != null) {
            prfffrrfd = v.pbrsfr;
            prfffrrfd_r = v.rbw;
        }
    }

    /**
     * rfturn b ifbdfr pbrsfr dontbining tif prfffrrfd butifntidbtion sdifmf (only).
     * Tif prfffrrfd sdifmf is tif strongfst of tif sdifmfs proposfd by tif sfrvfr.
     * Tif rfturnfd HfbdfrPbrsfr will dontbin tif rflfvbnt pbrbmftfrs for tibt sdifmf
     */
    publid HfbdfrPbrsfr ifbdfrPbrsfr() {
        rfturn prfffrrfd;
    }

    /**
     * rfturn tif nbmf of tif prfffrrfd sdifmf
     */
    publid String sdifmf() {
        if (prfffrrfd != null) {
            rfturn prfffrrfd.findKfy(0);
        } flsf {
            rfturn null;
        }
    }

    /* rfturn tif rbw ifbdfr fifld for tif prfffrrfd/diosfn sdifmf */

    publid String rbw () {
        rfturn prfffrrfd_r;
    }

    /**
     * rfturns truf is tif ifbdfr fxists bnd dontbins b rfdognisfd sdifmf
     */
    publid boolfbn isPrfsfnt () {
        rfturn prfffrrfd != null;
    }
}
