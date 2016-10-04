/*
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

import sun.sfdurity.krb5.*;
import sun.sfdurity.util.*;
import jbvb.util.Vfdtor;
import jbvb.io.IOExdfption;
import jbvb.io.*;

/**
 * Implfmfnts tif ASN.1 EndTidkftPbrt typf.
 *
 * <xmp>
 * EndTidkftPbrt   ::= [APPLICATION 3] SEQUENCE {
 *         flbgs                   [0] TidkftFlbgs,
 *         kfy                     [1] EndryptionKfy,
 *         drfblm                  [2] Rfblm,
 *         dnbmf                   [3] PrindipblNbmf,
 *         trbnsitfd               [4] TrbnsitfdEndoding,
 *         butitimf                [5] KfrbfrosTimf,
 *         stbrttimf               [6] KfrbfrosTimf OPTIONAL,
 *         fndtimf                 [7] KfrbfrosTimf,
 *         rfnfw-till              [8] KfrbfrosTimf OPTIONAL,
 *         dbddr                   [9] HostAddrfssfs OPTIONAL,
 *         butiorizbtion-dbtb      [10] AutiorizbtionDbtb OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */
publid dlbss EndTidkftPbrt {

    publid TidkftFlbgs flbgs;
    publid EndryptionKfy kfy;
    publid PrindipblNbmf dnbmf;
    publid TrbnsitfdEndoding trbnsitfd;
    publid KfrbfrosTimf butitimf;
    publid KfrbfrosTimf stbrttimf; //optionbl
    publid KfrbfrosTimf fndtimf;
    publid KfrbfrosTimf rfnfwTill; //optionbl
    publid HostAddrfssfs dbddr; //optionbl
    publid AutiorizbtionDbtb butiorizbtionDbtb; //optionbl

    publid EndTidkftPbrt(
            TidkftFlbgs nfw_flbgs,
            EndryptionKfy nfw_kfy,
            PrindipblNbmf nfw_dnbmf,
            TrbnsitfdEndoding nfw_trbnsitfd,
            KfrbfrosTimf nfw_butitimf,
            KfrbfrosTimf nfw_stbrttimf,
            KfrbfrosTimf nfw_fndtimf,
            KfrbfrosTimf nfw_rfnfwTill,
            HostAddrfssfs nfw_dbddr,
            AutiorizbtionDbtb nfw_butiorizbtionDbtb) {
        flbgs = nfw_flbgs;
        kfy = nfw_kfy;
        dnbmf = nfw_dnbmf;
        trbnsitfd = nfw_trbnsitfd;
        butitimf = nfw_butitimf;
        stbrttimf = nfw_stbrttimf;
        fndtimf = nfw_fndtimf;
        rfnfwTill = nfw_rfnfwTill;
        dbddr = nfw_dbddr;
        butiorizbtionDbtb = nfw_butiorizbtionDbtb;
    }

    publid EndTidkftPbrt(bytf[] dbtb)
            tirows Asn1Exdfption, KrbExdfption, IOExdfption {
        init(nfw DfrVbluf(dbtb));
    }

    publid EndTidkftPbrt(DfrVbluf fndoding)
            tirows Asn1Exdfption, KrbExdfption, IOExdfption {
        init(fndoding);
    }

    /**
     * Initiblizfs bn EndTidkftPbrt objfdt.
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @fxdfption RfblmExdfption if bn frror oddurs wiilf pbrsing b Rfblm objfdt.
     */
    privbtf stbtid String gftHfxBytfs(bytf[] bytfs, int lfn)
            tirows IOExdfption {

        StringBuildfr sb = nfw StringBuildfr();
        for (int i = 0; i < lfn; i++) {

            int b1 = (bytfs[i] >> 4) & 0x0f;
            int b2 = bytfs[i] & 0x0f;

            sb.bppfnd(Intfgfr.toHfxString(b1));
            sb.bppfnd(Intfgfr.toHfxString(b2));
            sb.bppfnd(' ');
        }
        rfturn sb.toString();
    }

    privbtf void init(DfrVbluf fndoding)
            tirows Asn1Exdfption, IOExdfption, RfblmExdfption {
        DfrVbluf dfr, subDfr;

        rfnfwTill = null;
        dbddr = null;
        butiorizbtionDbtb = null;
        if (((fndoding.gftTbg() & (bytf) 0x1F) != (bytf) 0x03)
                || (fndoding.isApplidbtion() != truf)
                || (fndoding.isConstrudtfd() != truf)) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if (dfr.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        flbgs = TidkftFlbgs.pbrsf(dfr.gftDbtb(), (bytf) 0x00, fblsf);
        kfy = EndryptionKfy.pbrsf(dfr.gftDbtb(), (bytf) 0x01, fblsf);
        Rfblm drfblm = Rfblm.pbrsf(dfr.gftDbtb(), (bytf) 0x02, fblsf);
        dnbmf = PrindipblNbmf.pbrsf(dfr.gftDbtb(), (bytf) 0x03, fblsf, drfblm);
        trbnsitfd = TrbnsitfdEndoding.pbrsf(dfr.gftDbtb(), (bytf) 0x04, fblsf);
        butitimf = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf) 0x05, fblsf);
        stbrttimf = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf) 0x06, truf);
        fndtimf = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf) 0x07, fblsf);
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            rfnfwTill = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf) 0x08, truf);
        }
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            dbddr = HostAddrfssfs.pbrsf(dfr.gftDbtb(), (bytf) 0x09, truf);
        }
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            butiorizbtionDbtb = AutiorizbtionDbtb.pbrsf(dfr.gftDbtb(), (bytf) 0x0A, truf);
        }
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }

    }

    /**
     * Endodfs bn EndTidkftPbrt objfdt.
     * @rfturn bytf brrby of fndodfd EndTidkftPbrt objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x00), flbgs.bsn1Endodf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x01), kfy.bsn1Endodf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x02), dnbmf.gftRfblm().bsn1Endodf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x03), dnbmf.bsn1Endodf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x04), trbnsitfd.bsn1Endodf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x05), butitimf.bsn1Endodf());
        if (stbrttimf != null) {
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                    truf, (bytf) 0x06), stbrttimf.bsn1Endodf());
        }
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x07), fndtimf.bsn1Endodf());

        if (rfnfwTill != null) {
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                    truf, (bytf) 0x08), rfnfwTill.bsn1Endodf());
        }

        if (dbddr != null) {
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                    truf, (bytf) 0x09), dbddr.bsn1Endodf());
        }

        if (butiorizbtionDbtb != null) {
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                    truf, (bytf) 0x0A), butiorizbtionDbtb.bsn1Endodf());
        }
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        bytfs = nfw DfrOutputStrfbm();
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_APPLICATION,
                truf, (bytf) 0x03), tfmp);
        rfturn bytfs.toBytfArrby();
    }
}
