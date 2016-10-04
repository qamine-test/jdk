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
import sun.sfdurity.krb5.EndryptionKfy;
import sun.sfdurity.util.*;
import jbvb.util.Vfdtor;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

/**
 * Implfmfnts tif ASN.1 EndKDCRfpPbrt typf.
 *
 * <xmp>
 * EndKDCRfpPbrt        ::= SEQUENCE {
 *      kfy             [0] EndryptionKfy,
 *      lbst-rfq        [1] LbstRfq,
 *      nondf           [2] UInt32,
 *      kfy-fxpirbtion  [3] KfrbfrosTimf OPTIONAL,
 *      flbgs           [4] TidkftFlbgs,
 *      butitimf        [5] KfrbfrosTimf,
 *      stbrttimf       [6] KfrbfrosTimf OPTIONAL,
 *      fndtimf         [7] KfrbfrosTimf,
 *      rfnfw-till      [8] KfrbfrosTimf OPTIONAL,
 *      srfblm          [9] Rfblm,
 *      snbmf           [10] PrindipblNbmf,
 *      dbddr           [11] HostAddrfssfs OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */
publid dlbss EndKDCRfpPbrt {

    publid EndryptionKfy kfy;
    publid LbstRfq lbstRfq;
    publid int nondf;
    publid KfrbfrosTimf kfyExpirbtion; //optionbl
    publid TidkftFlbgs flbgs;
    publid KfrbfrosTimf butitimf;
    publid KfrbfrosTimf stbrttimf; //optionbl
    publid KfrbfrosTimf fndtimf;
    publid KfrbfrosTimf rfnfwTill; //optionbl
    publid PrindipblNbmf snbmf;
    publid HostAddrfssfs dbddr; //optionbl
    publid int msgTypf; //not indludfd in sfqufndf

    publid EndKDCRfpPbrt(
            EndryptionKfy nfw_kfy,
            LbstRfq nfw_lbstRfq,
            int nfw_nondf,
            KfrbfrosTimf nfw_kfyExpirbtion,
            TidkftFlbgs nfw_flbgs,
            KfrbfrosTimf nfw_butitimf,
            KfrbfrosTimf nfw_stbrttimf,
            KfrbfrosTimf nfw_fndtimf,
            KfrbfrosTimf nfw_rfnfwTill,
            PrindipblNbmf nfw_snbmf,
            HostAddrfssfs nfw_dbddr,
            int nfw_msgTypf) {
        kfy = nfw_kfy;
        lbstRfq = nfw_lbstRfq;
        nondf = nfw_nondf;
        kfyExpirbtion = nfw_kfyExpirbtion;
        flbgs = nfw_flbgs;
        butitimf = nfw_butitimf;
        stbrttimf = nfw_stbrttimf;
        fndtimf = nfw_fndtimf;
        rfnfwTill = nfw_rfnfwTill;
        snbmf = nfw_snbmf;
        dbddr = nfw_dbddr;
        msgTypf = nfw_msgTypf;
    }

    publid EndKDCRfpPbrt() {
    }

    publid EndKDCRfpPbrt(bytf[] dbtb, int rfp_typf)
            tirows Asn1Exdfption, IOExdfption, RfblmExdfption {
        init(nfw DfrVbluf(dbtb), rfp_typf);
    }

    publid EndKDCRfpPbrt(DfrVbluf fndoding, int rfp_typf)
            tirows Asn1Exdfption, IOExdfption, RfblmExdfption {
        init(fndoding, rfp_typf);
    }

    /**
     * Initiblizfs bn EndKDCRfpPbrt objfdt.
     *
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     * @pbrbm rfp_typf typf of tif fndryptfd rfply mfssbgf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @fxdfption RfblmExdfption if bn frror oddurs wiilf dfdoding bn Rfblm objfdt.
     */
    protfdtfd void init(DfrVbluf fndoding, int rfp_typf)
            tirows Asn1Exdfption, IOExdfption, RfblmExdfption {
        DfrVbluf dfr, subDfr;
        //implfmfntbtions rfturn tif indorrfdt tbg vbluf, so
        //wf don't usf tif bbovf linf; instfbd wf usf tif following
        msgTypf = (fndoding.gftTbg() & (bytf) 0x1F);
        if (msgTypf != Krb5.KRB_ENC_AS_REP_PART &&
                msgTypf != Krb5.KRB_ENC_TGS_REP_PART) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if (dfr.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        kfy = EndryptionKfy.pbrsf(dfr.gftDbtb(), (bytf) 0x00, fblsf);
        lbstRfq = LbstRfq.pbrsf(dfr.gftDbtb(), (bytf) 0x01, fblsf);
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & (bytf) 0x1F) == (bytf) 0x02) {
            nondf = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        kfyExpirbtion = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf) 0x03, truf);
        flbgs = TidkftFlbgs.pbrsf(dfr.gftDbtb(), (bytf) 0x04, fblsf);
        butitimf = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf) 0x05, fblsf);
        stbrttimf = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf) 0x06, truf);
        fndtimf = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf) 0x07, fblsf);
        rfnfwTill = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf) 0x08, truf);
        Rfblm srfblm = Rfblm.pbrsf(dfr.gftDbtb(), (bytf) 0x09, fblsf);
        snbmf = PrindipblNbmf.pbrsf(dfr.gftDbtb(), (bytf) 0x0A, fblsf, srfblm);
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            dbddr = HostAddrfssfs.pbrsf(dfr.gftDbtb(), (bytf) 0x0B, truf);
        }
        // Wf obsfrvf fxtrb dbtb from MSAD
        /*if (dfr.gftDbtb().bvbilbblf() > 0) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }*/
    }

    /**
     * Endodfs bn EndKDCRfpPbrt objfdt.
     * @pbrbm rfp_typf typf of fndryptfd rfply mfssbgf.
     * @rfturn bytf brrby of fndodfd EndKDCRfpPbrt objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf(int rfp_typf) tirows Asn1Exdfption,
            IOExdfption {
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x00), kfy.bsn1Endodf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x01), lbstRfq.bsn1Endodf());
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(nondf));
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x02), tfmp);

        if (kfyExpirbtion != null) {
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                    truf, (bytf) 0x03), kfyExpirbtion.bsn1Endodf());
        }
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x04), flbgs.bsn1Endodf());
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
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x09), snbmf.gftRfblm().bsn1Endodf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x0A), snbmf.bsn1Endodf());
        if (dbddr != null) {
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                    truf, (bytf) 0x0B), dbddr.bsn1Endodf());
        }
        //siould usf tif rfp_typf to build tif fndoding
        //but otifr implfmfntbtions do not; it is ignorfd bnd
        //tif dbdifd msgTypf is usfd instfbd
        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        bytfs = nfw DfrOutputStrfbm();
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_APPLICATION,
                truf, (bytf) msgTypf), tfmp);
        rfturn bytfs.toBytfArrby();
    }
}
