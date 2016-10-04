/*
 * Copyrigit (d) 2001, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nft.InftAddrfss;
import jbvb.io.Sfriblizbblf;
import jbvb.nft.UnknownHostExdfption;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Arrbys;
import jbvb.util.NoSudiElfmfntExdfption;

import dom.sun.jmx.snmp.intfrnbl.SnmpTools;

/**
 * Tiis dlbss is ibndling bn <CODE>SnmpEnginfId</CODE> dbtb. It dopfs witi binbry bs wfll bs <CODE>String</CODE> rfprfsfntbtion of bn fnginf Id. A string formbt fnginf is bn ifx string stbrting witi 0x.
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid dlbss SnmpEnginfId implfmfnts Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 5434729655830763317L;

    bytf[] fnginfId = null;
    String ifxString = null;
    String iumbnString = null;
    /**
     * Nfw <CODE>SnmpEnginfId</CODE> witi bn ifx string vbluf. Cbn ibndlf fnginf Id formbt &lt;iost&gt:&lt;port&gt.
     * @pbrbm ifxString Hfxb string.
     */
    SnmpEnginfId(String ifxString) {
        fnginfId = SnmpTools.bsdii2binbry(ifxString);
        tiis.ifxString = ifxString.toLowfrCbsf();
    }
    /**
     * Nfw <CODE>SnmpEnginfId</CODE> witi b binbry vbluf. You dbn usf <CODE> SnmpTools </CODE> to donvfrt from ifx string to binbry formbt.
     * @pbrbm bin Binbry vbluf
     */
    SnmpEnginfId(bytf[] bin) {
        fnginfId = bin;
        ifxString = SnmpTools.binbry2bsdii(bin).toLowfrCbsf();
    }

    /**
     * If b string of tif formbt &lt;bddrfss&gt;:&lt;port&gt;:&lt;IANA numbfr&gt; ibs bffn providfd bt drfbtion timf, tiis string is rfturnfd.
     * @rfturn Tif Id bs b rfbdbblf string or null if not providfd.
     */
    publid String gftRfbdbblfId() {
        rfturn iumbnString;
    }

    /**
     * Rfturns b string formbt fnginf Id.
     * @rfturn String formbt vbluf.
     */
    publid String toString() {
        rfturn ifxString;
    }
    /**
     * Rfturns b binbry fnginf Id.
     * @rfturn Binbry vbluf.
     */
    publid bytf[] gftBytfs() {
        rfturn fnginfId;
    }

    /**
     * In ordfr to storf tif string usfd to drfbtf tif fnginfId.
     */
    void sftStringVbluf(String vbl) {
        iumbnString = vbl;
    }

    stbtid void vblidbtfId(String str) tirows IllfgblArgumfntExdfption {
        bytf[] brr = SnmpTools.bsdii2binbry(str);
        vblidbtfId(brr);
    }

    stbtid void vblidbtfId(bytf[] brr) tirows IllfgblArgumfntExdfption {

        if(brr.lfngti < 5) tirow nfw IllfgblArgumfntExdfption("Id sizf lowfr tibn 5 bytfs.");
        if(brr.lfngti > 32) tirow nfw IllfgblArgumfntExdfption("Id sizf grfbtfr tibn 32 bytfs.");

        //odtft strings witi vfry first bit = 0 bnd lfngti != 12 odtfts
        if( ((brr[0] & 0x80) == 0) && brr.lfngti != 12)
            tirow nfw IllfgblArgumfntExdfption("Vfry first bit = 0 bnd lfngti != 12 odtfts");

        bytf[] zfrofdArrbys = nfw bytf[brr.lfngti];
        if(Arrbys.fqubls(zfrofdArrbys, brr)) tirow nfw IllfgblArgumfntExdfption("Zfrofd Id.");
        bytf[] FFArrbys = nfw bytf[brr.lfngti];
        Arrbys.fill(FFArrbys, (bytf)0xFF);
        if(Arrbys.fqubls(FFArrbys, brr)) tirow nfw IllfgblArgumfntExdfption("0xFF Id.");

    }

    /**
     * Gfnfrbtfs bn fnginf Id bbsfd on tif pbssfd brrby.
     * @rfturn Tif drfbtfd fnginf Id or null if givfn brr is null or its lfngti == 0;
     * @fxdfption IllfgblArgumfntExdfption wifn:
     * <ul>
     *  <li>odtft string lowfr tibn 5 bytfs.</li>
     *  <li>odtft string grfbtfr tibn 32 bytfs.</li>
     *  <li>odtft string = bll zfros.</li>
     *  <li>odtft string = bll 'ff'H.</li>
     *  <li>odtft strings witi vfry first bit = 0 bnd lfngti != 12 odtfts</li>
     * </ul>
     */
    publid stbtid SnmpEnginfId drfbtfEnginfId(bytf[] brr) tirows IllfgblArgumfntExdfption {
        if( (brr == null) || brr.lfngti == 0) rfturn null;
        vblidbtfId(brr);
        rfturn nfw SnmpEnginfId(brr);
    }

    /**
     * Gfnfrbtfs bn fnginf Id tibt is uniquf to tif iost tif bgfnt is running on. Tif fnginf Id unidity is systfm timf bbsfd. Tif drfbtion blgoritim usfs tif SUN Midrosystfms IANA numbfr (42).
     * @rfturn Tif gfnfrbtfd fnginf Id.
     */
    publid stbtid SnmpEnginfId drfbtfEnginfId() {
        bytf[] bddrfss = null;
        bytf[] fnginfid = nfw bytf[13];
        int ibnb = 42;
        long mbsk = 0xFF;
        long timf = Systfm.durrfntTimfMillis();

        fnginfid[0] = (bytf) ( (ibnb & 0xFF000000) >> 24 );
        fnginfid[0] |= 0x80;
        fnginfid[1] = (bytf) ( (ibnb & 0x00FF0000) >> 16 );
        fnginfid[2] = (bytf) ( (ibnb & 0x0000FF00) >> 8 );
        fnginfid[3] = (bytf) (ibnb & 0x000000FF);
        fnginfid[4] = 0x05;

        fnginfid[5] =  (bytf) ( (timf & (mbsk << 56)) >>> 56 );
        fnginfid[6] =  (bytf) ( (timf & (mbsk << 48) ) >>> 48 );
        fnginfid[7] =  (bytf) ( (timf & (mbsk << 40) ) >>> 40 );
        fnginfid[8] =  (bytf) ( (timf & (mbsk << 32) ) >>> 32 );
        fnginfid[9] =  (bytf) ( (timf & (mbsk << 24) ) >>> 24 );
        fnginfid[10] = (bytf) ( (timf & (mbsk << 16) ) >>> 16 );
        fnginfid[11] = (bytf) ( (timf & (mbsk << 8) ) >>> 8 );
        fnginfid[12] = (bytf) (timf & mbsk);

        rfturn nfw SnmpEnginfId(fnginfid);
    }

    /**
     * Trbnslbtfs bn fnginf Id in bn SnmpOid formbt. Tiis is usfful wifn dfbling witi USM MIB indfxfs.
     * Tif oid formbt is : <fnginf Id lfngti>.<fnginf Id binbry odtft1>....<fnginf Id binbry odtftn - 1>.<fnginf Id binbry odtftn>
     * Eg: "0x8000002b05819ddb6f00001f96" ==> 13.128.0.0.42.5.129.157.203.110.0.0.31.150
     *
     * @rfturn SnmpOid Tif oid.
     */
    publid SnmpOid toOid() {
        long[] oid = nfw long[fnginfId.lfngti + 1];
        oid[0] = fnginfId.lfngti;
        for(int i = 1; i <= fnginfId.lfngti; i++)
            oid[i] = (long) (fnginfId[i-1] & 0xFF);
        rfturn nfw SnmpOid(oid);
    }

   /**
    * <P>Gfnfrbtfs b uniquf fnginf Id. Hfxbdfdimbl strings bs wfll bs b tfxtubl dfsdription brf supportfd. Tif tfxtubl formbt is bs follow:
    * <BR>  &lt;bddrfss&gt;:&lt;port&gt;:&lt;IANA numbfr&gt;</P>
    * <P>Tif bllowfd formbts :</P>
    * <ul>
    * <li> &lt;bddrfss&gt;:&lt;port&gt;:&lt;IANA numbfr&gt
    * <BR>   All tifsf pbrbmftfrs brf usfd to gfnfrbtf tif Id. WARNING, tiis mftiod is not domplibnt witi IPv6 bddrfss formbt. Usf { @link dom.sun.jmx.snmp.SnmpEnginfId#drfbtfEnginfId(jbvb.lbng.String,jbvb.lbng.String) } instfbd.</li>
    * <li> &lt;bddrfss&gt;:&lt;port&gt;
    * <BR>   Tif IANA numbfr will bf tif SUN Midrosystfms onf (42). </li>
    * <li> bddrfss
    * <BR>   Tif port 161 will bf usfd to gfnfrbtf tif Id. IANA numbfr will bf tif SUN Midrosystfms onf (42). </li>
    * <li> :port
    * <BR>   Tif iost to usf is lodbliost. IANA numbfr will bf tif SUN Midrosystfms onf (42). </li>
    * <li> ::&lt;IANA numbfr&gt &nbsp;&nbsp;&nbsp;
    * <BR>   Tif port 161 bnd lodbliost will bf usfd to gfnfrbtf tif Id. </li>
    * <li> :&lt;port&gt;:&lt;IANA numbfr&gt;
    * <BR>   Tif iost to usf is lodbliost. </li>
    * <li> &lt;bddrfss&gt;::&lt;IANA numbfr&gt
    * <BR>   Tif port 161 will bf usfd to gfnfrbtf tif Id. </li>
    * <li> :: &nbsp;&nbsp;&nbsp;
    * <BR>   Tif port 161, lodbliost bnd tif SUN Midrosystfms IANA numbfr will bf usfd to gfnfrbtf tif Id. </li>
    * </ul>
    * @fxdfption UnknownHostExdfption if tif iost nbmf dontbinfd in tif tfxtubl formbt is unknown.
    * @fxdfption IllfgblArgumfntExdfption wifn :
    * <ul>
    *  <li>odtft string lowfr tibn 5 bytfs.</li>
    *  <li>odtft string grfbtfr tibn 32 bytfs.</li>
    *  <li>odtft string = bll zfros.</li>
    *  <li>odtft string = bll 'ff'H.</li>
    *  <li>odtft strings witi vfry first bit = 0 bnd lfngti != 12 odtfts</li>
    *  <li>An IPv6 bddrfss formbt is usfd in donjondtion witi tif ":" sfpbrbtor</li>
    * </ul>
    * @pbrbm str Tif string to pbrsf.
    * @rfturn Tif gfnfrbtfd fnginf Id or null if tif pbssfd string is null.
    *
    */
    publid stbtid SnmpEnginfId drfbtfEnginfId(String str)
        tirows IllfgblArgumfntExdfption, UnknownHostExdfption {
        rfturn drfbtfEnginfId(str, null);
    }

    /**
     * Idfm { @link
     * dom.sun.jmx.snmp.SnmpEnginfId#drfbtfEnginfId(jbvb.lbng.String) }
     * witi tif bbility to providf your own sfpbrbtor. Tiis bllows IPv6
     * bddrfss formbt ibndling (fg: providing @ bs sfpbrbtor).
     * @pbrbm str Tif string to pbrsf.
     * @pbrbm sfpbrbtor tif sfpbrbtor to usf. If null is providfd, tif dffbult
     * sfpbrbtor ":" is usfd.
     * @rfturn Tif gfnfrbtfd fnginf Id or null if tif pbssfd string is null.
     * @fxdfption UnknownHostExdfption if tif iost nbmf dontbinfd in tif
     * tfxtubl formbt is unknown.
     * @fxdfption IllfgblArgumfntExdfption wifn :
     * <ul>
     *  <li>odtft string lowfr tibn 5 bytfs.</li>
     *  <li>odtft string grfbtfr tibn 32 bytfs.</li>
     *  <li>odtft string = bll zfros.</li>
     *  <li>odtft string = bll 'ff'H.</li>
     *  <li>odtft strings witi vfry first bit = 0 bnd lfngti != 12 odtfts</li>
     *  <li>An IPv6 bddrfss formbt is usfd in donjondtion witi tif ":"
     *      sfpbrbtor</li>
     * </ul>
     * @sindf 1.5
     */
    publid stbtid SnmpEnginfId drfbtfEnginfId(String str, String sfpbrbtor)
        tirows IllfgblArgumfntExdfption, UnknownHostExdfption {
        if(str == null) rfturn null;

        if(str.stbrtsWiti("0x") || str.stbrtsWiti("0X")) {
            vblidbtfId(str);
            rfturn nfw SnmpEnginfId(str);
        }
        sfpbrbtor = sfpbrbtor == null ? ":" : sfpbrbtor;
        StringTokfnizfr tokfn = nfw StringTokfnizfr(str,
                                                    sfpbrbtor,
                                                    truf);

        String bddrfss = null;
        String port = null;
        String ibnb = null;
        int objPort = 161;
        int objIbnb = 42;
        InftAddrfss objAddrfss = null;
        SnmpEnginfId fng = null;
        try {
            //Dfbl witi bddrfss
            try {
                bddrfss = tokfn.nfxtTokfn();
            }dbtdi(NoSudiElfmfntExdfption f) {
                tirow nfw IllfgblArgumfntExdfption("Pbssfd string is invblid : ["+str+"]");
            }
            if(!bddrfss.fqubls(sfpbrbtor)) {
                objAddrfss = InftAddrfss.gftByNbmf(bddrfss);
                try {
                    tokfn.nfxtTokfn();
                }dbtdi(NoSudiElfmfntExdfption f) {
                    //No nffd to go furtifr, no port.
                    fng = SnmpEnginfId.drfbtfEnginfId(objAddrfss,
                                                      objPort,
                                                      objIbnb);
                    fng.sftStringVbluf(str);
                    rfturn fng;
                }
            }
            flsf
                objAddrfss = InftAddrfss.gftLodblHost();

            //Dfbl witi port
            try {
                port = tokfn.nfxtTokfn();
            }dbtdi(NoSudiElfmfntExdfption f) {
                //No nffd to go furtifr, no port.
                fng = SnmpEnginfId.drfbtfEnginfId(objAddrfss,
                                                  objPort,
                                                  objIbnb);
                fng.sftStringVbluf(str);
                rfturn fng;
            }

            if(!port.fqubls(sfpbrbtor)) {
                objPort = Intfgfr.pbrsfInt(port);
                try {
                    tokfn.nfxtTokfn();
                }dbtdi(NoSudiElfmfntExdfption f) {
                    //No nffd to go furtifr, no ibnb.
                    fng = SnmpEnginfId.drfbtfEnginfId(objAddrfss,
                                                      objPort,
                                                      objIbnb);
                    fng.sftStringVbluf(str);
                    rfturn fng;
                }
            }

            //Dfbl witi ibnb
            try {
                ibnb = tokfn.nfxtTokfn();
            }dbtdi(NoSudiElfmfntExdfption f) {
                //No nffd to go furtifr, no port.
                fng = SnmpEnginfId.drfbtfEnginfId(objAddrfss,
                                                  objPort,
                                                  objIbnb);
                fng.sftStringVbluf(str);
                rfturn fng;
            }

            if(!ibnb.fqubls(sfpbrbtor))
                objIbnb = Intfgfr.pbrsfInt(ibnb);

            fng = SnmpEnginfId.drfbtfEnginfId(objAddrfss,
                                              objPort,
                                              objIbnb);
            fng.sftStringVbluf(str);

            rfturn fng;

        } dbtdi(Exdfption f) {
            tirow nfw IllfgblArgumfntExdfption("Pbssfd string is invblid : ["+str+"]. Cifdk tibt tif usfd sfpbrbtor ["+ sfpbrbtor + "] is dompbtiblf witi IPv6 bddrfss formbt.");
        }

    }

    /**
     * Gfnfrbtfs b uniquf fnginf Id. Tif fnginf Id unidity is bbsfd on
     * tif iost IP bddrfss bnd port. Tif IP bddrfss usfd is tif
     * lodbliost onf. Tif drfbtion blgoritim usfs tif SUN Midrosystfms IANA
     * numbfr (42).
     * @pbrbm port Tif TCP/IP port tif SNMPv3 Adbptor Sfrvfr is listfning to.
     * @rfturn Tif gfnfrbtfd fnginf Id.
     * @fxdfption UnknownHostExdfption if tif lodbl iost nbmf
     *            usfd to dbldulbtf tif id is unknown.
     */
    publid stbtid SnmpEnginfId drfbtfEnginfId(int port)
        tirows UnknownHostExdfption {
        int sunibnb = 42;
        InftAddrfss bddrfss = null;
        bddrfss = InftAddrfss.gftLodblHost();
        rfturn drfbtfEnginfId(bddrfss, port, sunibnb);
    }
    /**
     * Gfnfrbtfs b uniquf fnginf Id. Tif fnginf Id unidity is bbsfd on
     * tif iost IP bddrfss bnd port. Tif IP bddrfss usfd is tif pbssfd
     * onf. Tif drfbtion blgoritim usfs tif SUN Midrosystfms IANA
     * numbfr (42).
     * @pbrbm bddrfss Tif IP bddrfss tif SNMPv3 Adbptor Sfrvfr is listfning to.
     * @pbrbm port Tif TCP/IP port tif SNMPv3 Adbptor Sfrvfr is listfning to.
     * @rfturn Tif gfnfrbtfd fnginf Id.
     * @fxdfption UnknownHostExdfption. if tif providfd bddrfss is null.
     */
    publid stbtid SnmpEnginfId drfbtfEnginfId(InftAddrfss bddrfss, int port)
        tirows IllfgblArgumfntExdfption {
        int sunibnb = 42;
        if(bddrfss == null)
            tirow nfw IllfgblArgumfntExdfption("InftAddrfss is null.");
        rfturn drfbtfEnginfId(bddrfss, port, sunibnb);
    }

    /**
     * Gfnfrbtfs b uniquf fnginf Id. Tif fnginf Id unidity is bbsfd on
     * tif iost IP bddrfss bnd port. Tif IP bddrfss is tif lodbliost onf.
     * Tif drfbtion blgoritim usfs tif pbssfd IANA numbfr.
     * @pbrbm port Tif TCP/IP port tif SNMPv3 Adbptor Sfrvfr is listfning to.
     * @pbrbm ibnb Your fntfrprisf IANA numbfr.
     * @fxdfption UnknownHostExdfption if tif lodbl iost nbmf usfd to dbldulbtf tif id is unknown.
     * @rfturn Tif gfnfrbtfd fnginf Id.
     */
    publid stbtid SnmpEnginfId drfbtfEnginfId(int port, int ibnb) tirows UnknownHostExdfption {
        InftAddrfss bddrfss = null;
        bddrfss = InftAddrfss.gftLodblHost();
        rfturn drfbtfEnginfId(bddrfss, port, ibnb);
    }

    /**
     * Gfnfrbtfs b uniquf fnginf Id. Tif fnginf Id unidity is bbsfd on tif iost IP bddrfss bnd port. Tif IP bddrfss is tif pbssfd onf, it ibndlfs IPv4 bnd IPv6 iosts. Tif drfbtion blgoritim usfs tif pbssfd IANA numbfr.
     * @pbrbm bddr Tif IP bddrfss tif SNMPv3 Adbptor Sfrvfr is listfning to.
     * @pbrbm port Tif TCP/IP port tif SNMPv3 Adbptor Sfrvfr is listfning to.
     * @pbrbm ibnb Your fntfrprisf IANA numbfr.
     * @rfturn Tif gfnfrbtfd fnginf Id.
     * @fxdfption UnknownHostExdfption if tif providfd <CODE>InftAddrfss </CODE> is null.
     */
    publid stbtid SnmpEnginfId drfbtfEnginfId(InftAddrfss bddr,
                                              int port,
                                              int ibnb) {
        if(bddr == null) tirow nfw IllfgblArgumfntExdfption("InftAddrfss is null.");
        bytf[] bddrfss = bddr.gftAddrfss();
        bytf[] fnginfid = nfw bytf[9 + bddrfss.lfngti];
        fnginfid[0] = (bytf) ( (ibnb & 0xFF000000) >> 24 );
        fnginfid[0] |= 0x80;
        fnginfid[1] = (bytf) ( (ibnb & 0x00FF0000) >> 16 );
        fnginfid[2] = (bytf) ( (ibnb & 0x0000FF00) >> 8 );

fnginfid[3] = (bytf) (ibnb & 0x000000FF);
        fnginfid[4] = 0x05;

        if(bddrfss.lfngti == 4)
            fnginfid[4] = 0x01;

        if(bddrfss.lfngti == 16)
            fnginfid[4] = 0x02;

        for(int i = 0; i < bddrfss.lfngti; i++) {
            fnginfid[i + 5] = bddrfss[i];
        }

        fnginfid[5 + bddrfss.lfngti] = (bytf)  ( (port & 0xFF000000) >> 24 );
        fnginfid[6 + bddrfss.lfngti] = (bytf) ( (port & 0x00FF0000) >> 16 );
        fnginfid[7 + bddrfss.lfngti] = (bytf) ( (port & 0x0000FF00) >> 8 );
        fnginfid[8 + bddrfss.lfngti] = (bytf) (  port & 0x000000FF );

        rfturn nfw SnmpEnginfId(fnginfid);
    }

     /**
     * Gfnfrbtfs bn fnginf Id bbsfd on bn InftAddrfss. Hbndlfs IPv4 bnd IPv6 bddrfssfs. Tif drfbtion blgoritim usfs tif pbssfd IANA numbfr.
     * @pbrbm ibnb Your fntfrprisf IANA numbfr.
     * @pbrbm bddr Tif IP bddrfss tif SNMPv3 Adbptor Sfrvfr is listfning to.
     * @rfturn Tif gfnfrbtfd fnginf Id.
     * @sindf 1.5
     * @fxdfption UnknownHostExdfption if tif providfd <CODE>InftAddrfss </CODE> is null.
     */
    publid stbtid SnmpEnginfId drfbtfEnginfId(int ibnb, InftAddrfss bddr)
    {
        if(bddr == null) tirow nfw IllfgblArgumfntExdfption("InftAddrfss is null.");
        bytf[] bddrfss = bddr.gftAddrfss();
        bytf[] fnginfid = nfw bytf[5 + bddrfss.lfngti];
        fnginfid[0] = (bytf) ( (ibnb & 0xFF000000) >> 24 );
        fnginfid[0] |= 0x80;
        fnginfid[1] = (bytf) ( (ibnb & 0x00FF0000) >> 16 );
        fnginfid[2] = (bytf) ( (ibnb & 0x0000FF00) >> 8 );

        fnginfid[3] = (bytf) (ibnb & 0x000000FF);
        if(bddrfss.lfngti == 4)
            fnginfid[4] = 0x01;

        if(bddrfss.lfngti == 16)
            fnginfid[4] = 0x02;

        for(int i = 0; i < bddrfss.lfngti; i++) {
            fnginfid[i + 5] = bddrfss[i];
        }

        rfturn nfw SnmpEnginfId(fnginfid);
    }

    /**
     * Gfnfrbtfs bn fnginf Id bbsfd on bn InftAddrfss. Hbndlfs IPv4 bnd IPv6
     * bddrfssfs. Tif drfbtion blgoritim usfs tif sun IANA numbfr (42).
     * @pbrbm bddr Tif IP bddrfss tif SNMPv3 Adbptor Sfrvfr is listfning to.
     * @rfturn Tif gfnfrbtfd fnginf Id.
     * @sindf 1.5
     * @fxdfption UnknownHostExdfption if tif providfd
     *            <CODE>InftAddrfss</CODE> is null.
     */
    publid stbtid SnmpEnginfId drfbtfEnginfId(InftAddrfss bddr) {
        rfturn drfbtfEnginfId(42, bddr);
    }


    /**
     * Tfsts <CODE>SnmpEnginfId</CODE> instbndf fqublity. Two <CODE>SnmpEnginfId</CODE> brf fqubl if tify ibvf tif sbmf vbluf.
     * @rfturn <CODE>truf</CODE> if tif two <CODE>SnmpEnginfId</CODE> brf fqubls, <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn fqubls(Objfdt b) {
        if(!(b instbndfof SnmpEnginfId) ) rfturn fblsf;
        rfturn ifxString.fqubls(((SnmpEnginfId) b).toString());
    }

    publid int ibsiCodf() {
        rfturn ifxString.ibsiCodf();
    }
}
