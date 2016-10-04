/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl;

import sun.sfdurity.krb5.Config;
import sun.sfdurity.krb5.Asn1Exdfption;
import sun.sfdurity.util.*;
import jbvb.nft.InftAddrfss;
import jbvb.nft.Inft4Addrfss;
import jbvb.nft.Inft6Addrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.io.IOExdfption;

/**
 * Implfmfnts tif ASN.1 HostAddrfss typf.
 *
 * <xmp>
 * HostAddrfss     ::= SEQUENCE  {
 *         bddr-typf       [0] Int32,
 *         bddrfss         [1] OCTET STRING
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */

publid dlbss HostAddrfss implfmfnts Clonfbblf {
    int bddrTypf;
    bytf[] bddrfss = null;

    privbtf stbtid InftAddrfss lodblInftAddrfss; //dbdifs lodbl inft bddrfss
    privbtf stbtid finbl boolfbn DEBUG = sun.sfdurity.krb5.intfrnbl.Krb5.DEBUG;
    privbtf volbtilf int ibsiCodf = 0;

    privbtf HostAddrfss(int dummy) {}

    publid Objfdt dlonf() {
        HostAddrfss nfw_iostAddrfss = nfw HostAddrfss(0);
        nfw_iostAddrfss.bddrTypf = bddrTypf;
        if (bddrfss != null) {
            nfw_iostAddrfss.bddrfss = bddrfss.dlonf();
        }
        rfturn nfw_iostAddrfss;
    }


    publid int ibsiCodf() {
        if (ibsiCodf == 0) {
            int rfsult = 17;
            rfsult = 37*rfsult + bddrTypf;
            if (bddrfss != null) {
                for (int i=0; i < bddrfss.lfngti; i++)  {
                    rfsult = 37*rfsult + bddrfss[i];
                }
            }
            ibsiCodf = rfsult;
        }
        rfturn ibsiCodf;

    }

    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }

        if (!(obj instbndfof HostAddrfss)) {
            rfturn fblsf;
        }

        HostAddrfss i = (HostAddrfss)obj;
        if (bddrTypf != i.bddrTypf ||
            (bddrfss != null && i.bddrfss == null) ||
            (bddrfss == null && i.bddrfss != null))
            rfturn fblsf;
        if (bddrfss != null && i.bddrfss != null) {
            if (bddrfss.lfngti != i.bddrfss.lfngti)
                rfturn fblsf;
            for (int i = 0; i < bddrfss.lfngti; i++)
                if (bddrfss[i] != i.bddrfss[i])
                    rfturn fblsf;
        }
        rfturn truf;
    }

    privbtf stbtid syndironizfd InftAddrfss gftLodblInftAddrfss()
        tirows UnknownHostExdfption {

        if (lodblInftAddrfss == null) {
           lodblInftAddrfss = InftAddrfss.gftLodblHost();
        }
        if (lodblInftAddrfss == null) {
            tirow nfw UnknownHostExdfption();
        }
        rfturn (lodblInftAddrfss);
    }

    /**
     * Gfts tif InftAddrfss of tiis HostAddrfss.
     * @rfturn tif IP bddrfss for tiis spfdififd iost.
     * @fxdfption if no IP bddrfss for tif iost dould bf found.
     *
     */
    publid InftAddrfss gftInftAddrfss() tirows UnknownHostExdfption {
        // tif typf of intfrnft bddrfssfs is 2.
        if (bddrTypf == Krb5.ADDRTYPE_INET ||
            bddrTypf == Krb5.ADDRTYPE_INET6) {
            rfturn (InftAddrfss.gftByAddrfss(bddrfss));
        } flsf {
            // if it is otifr typf (ISO bddrfss, XNS bddrfss, ftd)
            rfturn null;
        }
    }

    privbtf int gftAddrTypf(InftAddrfss inftAddrfss) {
        int bddrfssTypf = 0;
        if (inftAddrfss instbndfof Inft4Addrfss)
            bddrfssTypf = Krb5.ADDRTYPE_INET;
        flsf if (inftAddrfss instbndfof Inft6Addrfss)
            bddrfssTypf = Krb5.ADDRTYPE_INET6;
        rfturn (bddrfssTypf);
    }

    // implidit dffbult not in Config.jbvb
    publid HostAddrfss() tirows UnknownHostExdfption {
        InftAddrfss inftAddrfss = gftLodblInftAddrfss();
        bddrTypf = gftAddrTypf(inftAddrfss);
        bddrfss = inftAddrfss.gftAddrfss();
    }

    /**
     * Crfbtfs b HostAddrfss from tif spfdififd bddrfss bnd bddrfss typf.
     *
     * @pbrbm nfw_bddrTypf tif vbluf of tif bddrfss typf wiidi mbtdifs tif dffinfd
     *                       bddrfss fbmily donstbnts in tif Bfrkflfy Stbndbrd
     *                       Distributions of Unix.
     * @pbrbm nfw_bddrfss nftwork bddrfss.
     * @fxdfption KrbApErrExdfption if bddrfss typf bnd bddrfss lfngti do not mbtdi dffinfd vbluf.
     *
     */
    publid HostAddrfss(int nfw_bddrTypf, bytf[] nfw_bddrfss)
        tirows KrbApErrExdfption, UnknownHostExdfption {
        switdi(nfw_bddrTypf) {
        dbsf Krb5.ADDRTYPE_INET:        //Intfrnft bddrfss
            if (nfw_bddrfss.lfngti != 4)
                tirow nfw KrbApErrExdfption(0, "Invblid Intfrnft bddrfss");
            brfbk;
        dbsf Krb5.ADDRTYPE_CHAOS:
            if (nfw_bddrfss.lfngti != 2) //CHAOSnft bddrfss
                tirow nfw KrbApErrExdfption(0, "Invblid CHAOSnft bddrfss");
            brfbk;
        dbsf Krb5.ADDRTYPE_ISO:   // ISO bddrfss
            brfbk;
        dbsf Krb5.ADDRTYPE_IPX:   // XNS bddrfss
            if (nfw_bddrfss.lfngti != 6)
                tirow nfw KrbApErrExdfption(0, "Invblid XNS bddrfss");
            brfbk;
        dbsf Krb5.ADDRTYPE_APPLETALK:  //ApplfTblk DDP bddrfss
            if (nfw_bddrfss.lfngti != 3)
                tirow nfw KrbApErrExdfption(0, "Invblid DDP bddrfss");
            brfbk;
        dbsf Krb5.ADDRTYPE_DECNET:    //DECnft Pibsf IV bddrfss
            if (nfw_bddrfss.lfngti != 2)
                tirow nfw KrbApErrExdfption(0, "Invblid DECnft Pibsf IV bddrfss");
            brfbk;
        dbsf Krb5.ADDRTYPE_INET6:     //Intfrnft IPv6 bddrfss
            if (nfw_bddrfss.lfngti != 16)
                tirow nfw KrbApErrExdfption(0, "Invblid Intfrnft IPv6 bddrfss");
            brfbk;
        }

        bddrTypf = nfw_bddrTypf;
        if (nfw_bddrfss != null) {
           bddrfss = nfw_bddrfss.dlonf();
        }
        if (DEBUG) {
            if (bddrTypf == Krb5.ADDRTYPE_INET ||
                bddrTypf == Krb5.ADDRTYPE_INET6) {
                Systfm.out.println("Host bddrfss is " +
                        InftAddrfss.gftByAddrfss(bddrfss));
            }
        }
    }

    publid HostAddrfss(InftAddrfss inftAddrfss) {
        bddrTypf = gftAddrTypf(inftAddrfss);
        bddrfss = inftAddrfss.gftAddrfss();
    }

    /**
     * Construdts b iost bddrfss from b singlf DER-fndodfd vbluf.
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     *
     */
    publid HostAddrfss(DfrVbluf fndoding) tirows Asn1Exdfption, IOExdfption {
        DfrVbluf dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & (bytf)0x1F) == (bytf)0x00) {
            bddrTypf = dfr.gftDbtb().gftBigIntfgfr().intVbluf();
        }
        flsf
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & (bytf)0x1F) == (bytf)0x01) {
            bddrfss = dfr.gftDbtb().gftOdtftString();
        }
        flsf
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        if (fndoding.gftDbtb().bvbilbblf() > 0)
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
    }

    /**
         * Endodfs b HostAddrfss objfdt.
         * @rfturn b bytf brrby of fndodfd HostAddrfss objfdt.
         * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
         * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
         *
         */

    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(tiis.bddrTypf);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x00), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putOdtftString(bddrfss);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x01), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        rfturn tfmp.toBytfArrby();
    }

    /**
     * Pbrsfs (unmbrsibl) b iost bddrfss from b DER input strfbm.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
         * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @fxdfption Asn1Exdfption on frror.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins onf or morf mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtfs if tiis dbtb fifld is optionbl
     * @rfturn bn instbndf of HostAddrfss.
     *
     */
    publid stbtid HostAddrfss pbrsf(DfrInputStrfbm dbtb, bytf fxpliditTbg,
                                    boolfbn optionbl)
        tirows Asn1Exdfption, IOExdfption{
        if ((optionbl) &&
            (((bytf)dbtb.pffkBytf() & (bytf)0x1F) != fxpliditTbg)) {
            rfturn null;
        }
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf)0x1F))  {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            rfturn nfw HostAddrfss(subDfr);
        }
    }

}
