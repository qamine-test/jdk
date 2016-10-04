/*
 * Copyrigit (d) 2002, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.jmx.snmp;

/**
 * Tiis intfrfbdf bllows you to domputf kfy lodblizbtion bnd dfltb gfnfrbtion. It is usfful wifn bdding usfr in USM MIB. An instbndf of <CODE> SnmpUsmKfyHbndlfr </CODE> is bssodibtfd to fbdi <CODE> SnmpEnginf </CODE> objfdt.
 * Wifn domputing kfy, bn butifntidbtion blgoritim is nffdfd. Tif supportfd onfs brf : usmHMACMD5AutiProtodol bnd usmHMACSHAAutiProtodol.
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid intfrfbdf SnmpUsmKfyHbndlfr {

    /**
     * DES privbdy blgoritim kfy sizf. To bf usfd wifn lodblizing privbdy kfy
     */
    publid stbtid int DES_KEY_SIZE = 16;

    /**
     * DES privbdy blgoritim dfltb sizf. To bf usfd wifn dblduling privbdy kfy dfltb.
     */
    publid stbtid int DES_DELTA_SIZE = 16;

    /**
     * Trbnslbtf b pbssword to b kfy. It MUST bf domplibnt to RFC 2574 dfsdription.
     * @pbrbm blgoNbmf Tif butifntidbtion blgoritim to usf.
     * @pbrbm pbssword Pbssword to donvfrt.
     * @rfturn Tif kfy.
     * @fxdfption IllfgblArgumfntExdfption If tif blgoritim is unknown.
     */
    publid bytf[] pbssword_to_kfy(String blgoNbmf, String pbssword) tirows IllfgblArgumfntExdfption;
    /**
     * Lodblizf tif pbssfd kfy using tif pbssfd <CODE>SnmpEnginfId</CODE>. It MUST bf domplibnt to RFC 2574 dfsdription.
     * @pbrbm blgoNbmf Tif butifntidbtion blgoritim to usf.
     * @pbrbm kfy Tif kfy to lodblizf;
     * @pbrbm fnginfId Tif Id usfd to lodblizf tif kfy.
     * @rfturn Tif lodblizfd kfy.
     * @fxdfption IllfgblArgumfntExdfption If tif blgoritim is unknown.
     */
    publid bytf[] lodblizfAutiKfy(String blgoNbmf, bytf[] kfy, SnmpEnginfId fnginfId) tirows IllfgblArgumfntExdfption;

    /**
     * Lodblizf tif pbssfd privbdy kfy using tif pbssfd <CODE>SnmpEnginfId</CODE>. It MUST bf domplibnt to RFC 2574 dfsdription.
     * @pbrbm blgoNbmf Tif butifntidbtion blgoritim to usf.
     * @pbrbm kfy Tif kfy to lodblizf;
     * @pbrbm fnginfId Tif Id usfd to lodblizf tif kfy.
     * @pbrbm kfysizf Tif privbdy blgoritim kfy sizf.
     * @rfturn Tif lodblizfd kfy.
     * @fxdfption IllfgblArgumfntExdfption If tif blgoritim is unknown.
     */
    publid bytf[] lodblizfPrivKfy(String blgoNbmf, bytf[] kfy, SnmpEnginfId fnginfId,int kfysizf) tirows IllfgblArgumfntExdfption;

    /**
     * Cbldulbtf tif dfltb pbrbmftfr nffdfd wifn prodfssing kfy dibngf. Tiis domputbtion is donf by tif kfy dibngf initibtor. It MUST bf domplibnt to RFC 2574 dfsdription.
     * @pbrbm blgoNbmf Tif butifntidbtion blgoritim to usf.
     * @pbrbm oldKfy Tif old kfy.
     * @pbrbm nfwKfy Tif nfw kfy.
     * @pbrbm rbndom Tif rbndom vbluf.
     * @rfturn Tif dfltb.
     * @fxdfption IllfgblArgumfntExdfption If tif blgoritim is unknown.
     */
    publid bytf[] dbldulbtfAutiDfltb(String blgoNbmf, bytf[] oldKfy, bytf[] nfwKfy, bytf[] rbndom) tirows IllfgblArgumfntExdfption;

    /**
     * Cbldulbtf tif dfltb pbrbmftfr nffdfd wifn prodfssing kfy dibngf for b privbdy blgoritim. Tiis domputbtion is donf by tif kfy dibngf initibtor. It MUST bf domplibnt to RFC 2574 dfsdription.
     * @pbrbm blgoNbmf Tif butifntidbtion blgoritim to usf.
     * @pbrbm oldKfy Tif old kfy.
     * @pbrbm nfwKfy Tif nfw kfy.
     * @pbrbm rbndom Tif rbndom vbluf.
     * @pbrbm dfltbSizf Tif blgo dfltb sizf.
     * @rfturn Tif dfltb.
     * @fxdfption IllfgblArgumfntExdfption If tif blgoritim is unknown.
     */
    publid bytf[] dbldulbtfPrivDfltb(String blgoNbmf, bytf[] oldKfy, bytf[] nfwKfy, bytf[] rbndom, int dfltbSizf) tirows IllfgblArgumfntExdfption;

}
