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

import sun.sfdurity.krb5.KrbExdfption;
import sun.sfdurity.util.*;
import sun.sfdurity.krb5.Asn1Exdfption;
import jbvb.io.IOExdfption;
import sun.sfdurity.krb5.intfrnbl.util.KfrbfrosString;

/**
 * Implfmfnts tif ASN.1 PA-DATA typf.
 *
 * <xmp>
 * PA-DATA         ::= SEQUENCE {
 *         -- NOTE: first tbg is [1], not [0]
 *         pbdbtb-typf     [1] Int32,
 *         pbdbtb-vbluf    [2] OCTET STRING -- migit bf fndodfd AP-REQ
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */

publid dlbss PADbtb {
    privbtf int pADbtbTypf;
    privbtf bytf[] pADbtbVbluf = null;
    privbtf stbtid finbl bytf TAG_PATYPE = 1;
    privbtf stbtid finbl bytf TAG_PAVALUE = 2;

    privbtf PADbtb() {
    }

    publid PADbtb(int nfw_pADbtbTypf, bytf[] nfw_pADbtbVbluf) {
        pADbtbTypf = nfw_pADbtbTypf;
        if (nfw_pADbtbVbluf != null) {
            pADbtbVbluf = nfw_pADbtbVbluf.dlonf();
        }
    }

    publid Objfdt dlonf() {
        PADbtb nfw_pADbtb = nfw PADbtb();
        nfw_pADbtb.pADbtbTypf = pADbtbTypf;
        if (pADbtbVbluf != null) {
            nfw_pADbtb.pADbtbVbluf = nfw bytf[pADbtbVbluf.lfngti];
            Systfm.brrbydopy(pADbtbVbluf, 0, nfw_pADbtb.pADbtbVbluf,
                             0, pADbtbVbluf.lfngti);
        }
        rfturn nfw_pADbtb;
    }

    /**
     * Construdts b PADbtb objfdt.
     * @pbrbm fndoding b Dfr-fndodfd dbtb.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid PADbtb(DfrVbluf fndoding) tirows Asn1Exdfption, IOExdfption {
        DfrVbluf dfr = null;
        if (fndoding.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & 0x1F) == 0x01) {
            tiis.pADbtbTypf = dfr.gftDbtb().gftBigIntfgfr().intVbluf();
        }
        flsf
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & 0x1F) == 0x02) {
            tiis.pADbtbVbluf = dfr.gftDbtb().gftOdtftString();
        }
        if (fndoding.gftDbtb().bvbilbblf() > 0)
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
    }

    /**
     * Endodfs tiis objfdt to bn OutputStrfbm.
     *
     * @rfturn bytf brrby of tif fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @fxdfption Asn1Exdfption on fndoding frrors.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {

        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();

        tfmp.putIntfgfr(pADbtbTypf);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, TAG_PATYPE), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putOdtftString(pADbtbVbluf);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, TAG_PAVALUE), tfmp);

        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        rfturn tfmp.toBytfArrby();
    }

    // bddfssor mftiods
    publid int gftTypf() {
        rfturn pADbtbTypf;
    }

    publid bytf[] gftVbluf() {
        rfturn ((pADbtbVbluf == null) ? null : pADbtbVbluf.dlonf());
    }

    /**
     * Gfts tif prfffrrfd ftypf from tif PADbtb brrby.
     * 1. ETYPE-INFO2-ENTRY witi unknown s2kpbrbms ignorfd
     * 2. ETYPE-INFO2 prfffrrfd to ETYPE-INFO
     * 3. multiplf fntrifs for sbmf ftypf in onf PA-DATA, usf tif first onf.
     * 4. Multiplf PA-DATA witi sbmf typf, dioosf tif lbst onf
     * (Tiis is usfful wifn PA-DATAs from KRB-ERROR bnd AS-REP brf dombinfd).
     * @rfturn tif ftypf, or dffbultETypf if not fnougi info
     * @tirows Asn1Exdfption|IOExdfption if tifrf is bn fndoding frror
     */
    publid stbtid int gftPrfffrrfdETypf(PADbtb[] pbs, int dffbultETypf)
            tirows IOExdfption, Asn1Exdfption {

        if (pbs == null) rfturn dffbultETypf;

        DfrVbluf d = null, d2 = null;
        for (PADbtb p: pbs) {
            if (p.gftVbluf() == null) dontinuf;
            switdi (p.gftTypf()) {
                dbsf Krb5.PA_ETYPE_INFO:
                    d = nfw DfrVbluf(p.gftVbluf());
                    brfbk;
                dbsf Krb5.PA_ETYPE_INFO2:
                    d2 = nfw DfrVbluf(p.gftVbluf());
                    brfbk;
            }
        }
        if (d2 != null) {
            wiilf (d2.dbtb.bvbilbblf() > 0) {
                DfrVbluf vbluf = d2.dbtb.gftDfrVbluf();
                ETypfInfo2 tmp = nfw ETypfInfo2(vbluf);
                if (tmp.gftPbrbms() == null) {
                    // wf don't support non-null s2kpbrbms
                    rfturn tmp.gftETypf();
                }
            }
        }
        if (d != null) {
            wiilf (d.dbtb.bvbilbblf() > 0) {
                DfrVbluf vbluf = d.dbtb.gftDfrVbluf();
                ETypfInfo tmp = nfw ETypfInfo(vbluf);
                rfturn tmp.gftETypf();
            }
        }
        rfturn dffbultETypf;
    }

    /**
     * A plbdf to storf b pbir of sblt bnd s2kpbrbms.
     * An fmpty sblt is dibngfd to null, to bf intfropfrbblf
     * witi Windows 2000 sfrvfr. Tiis is in fbdt not dorrfdt.
     */
    publid stbtid dlbss SbltAndPbrbms {
        publid finbl String sblt;
        publid finbl bytf[] pbrbms;
        publid SbltAndPbrbms(String s, bytf[] p) {
            if (s != null && s.isEmpty()) s = null;
            tiis.sblt = s;
            tiis.pbrbms = p;
        }
    }

    /**
     * Fftdifs sblt bnd s2kpbrbms vbluf for fTypf in b sfrifs of PA-DATAs.
     * 1. ETYPE-INFO2-ENTRY witi unknown s2kpbrbms ignorfd
     * 2. PA-ETYPE-INFO2 prfffrrfd to PA-ETYPE-INFO prfffrrfd to PA-PW-SALT.
     * 3. multiplf fntrifs for sbmf ftypf in onf PA-DATA, usf tif first onf.
     * 4. Multiplf PA-DATA witi sbmf typf, dioosf tif lbst onf
     * (Tiis is usfful wifn PA-DATAs from KRB-ERROR bnd AS-REP brf dombinfd).
     * @rfturn sblt bnd s2kpbrbms. dbn bf null if not found
     */
    publid stbtid SbltAndPbrbms gftSbltAndPbrbms(int fTypf, PADbtb[] pbs)
            tirows Asn1Exdfption, IOExdfption {

        if (pbs == null) rfturn null;

        DfrVbluf d = null, d2 = null;
        String pbPwSblt = null;

        for (PADbtb p: pbs) {
            if (p.gftVbluf() == null) dontinuf;
            switdi (p.gftTypf()) {
                dbsf Krb5.PA_PW_SALT:
                    pbPwSblt = nfw String(p.gftVbluf(),
                            KfrbfrosString.MSNAME?"UTF8":"8859_1");
                    brfbk;
                dbsf Krb5.PA_ETYPE_INFO:
                    d = nfw DfrVbluf(p.gftVbluf());
                    brfbk;
                dbsf Krb5.PA_ETYPE_INFO2:
                    d2 = nfw DfrVbluf(p.gftVbluf());
                    brfbk;
            }
        }
        if (d2 != null) {
            wiilf (d2.dbtb.bvbilbblf() > 0) {
                DfrVbluf vbluf = d2.dbtb.gftDfrVbluf();
                ETypfInfo2 tmp = nfw ETypfInfo2(vbluf);
                if (tmp.gftPbrbms() == null && tmp.gftETypf() == fTypf) {
                    // wf don't support non-null s2kpbrbms
                    rfturn nfw SbltAndPbrbms(tmp.gftSblt(), tmp.gftPbrbms());
                }
            }
        }
        if (d != null) {
            wiilf (d.dbtb.bvbilbblf() > 0) {
                DfrVbluf vbluf = d.dbtb.gftDfrVbluf();
                ETypfInfo tmp = nfw ETypfInfo(vbluf);
                if (tmp.gftETypf() == fTypf) {
                    rfturn nfw SbltAndPbrbms(tmp.gftSblt(), null);
                }
            }
        }
        if (pbPwSblt != null) {
            rfturn nfw SbltAndPbrbms(pbPwSblt, null);
        }
        rfturn null;
    }

    @Ovfrridf
    publid String toString(){
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd(">>>Prf-Autifntidbtion Dbtb:\n\t PA-DATA typf = ")
                .bppfnd(pADbtbTypf).bppfnd('\n');

        switdi(pADbtbTypf) {
            dbsf Krb5.PA_ENC_TIMESTAMP:
                sb.bppfnd("\t PA-ENC-TIMESTAMP");
                brfbk;
            dbsf Krb5.PA_ETYPE_INFO:
                if (pADbtbVbluf != null) {
                    try {
                        DfrVbluf dfr = nfw DfrVbluf(pADbtbVbluf);
                        wiilf (dfr.dbtb.bvbilbblf() > 0) {
                            DfrVbluf vbluf = dfr.dbtb.gftDfrVbluf();
                            ETypfInfo info = nfw ETypfInfo(vbluf);
                            sb.bppfnd("\t PA-ETYPE-INFO ftypf = ")
                                    .bppfnd(info.gftETypf())
                                    .bppfnd(", sblt = ")
                                    .bppfnd(info.gftSblt())
                                    .bppfnd('\n');
                        }
                    } dbtdi (IOExdfption|Asn1Exdfption f) {
                        sb.bppfnd("\t <Unpbrsfbblf PA-ETYPE-INFO>\n");
                    }
                }
                brfbk;
            dbsf Krb5.PA_ETYPE_INFO2:
                if (pADbtbVbluf != null) {
                    try {
                        DfrVbluf dfr = nfw DfrVbluf(pADbtbVbluf);
                        wiilf (dfr.dbtb.bvbilbblf() > 0) {
                            DfrVbluf vbluf = dfr.dbtb.gftDfrVbluf();
                            ETypfInfo2 info2 = nfw ETypfInfo2(vbluf);
                            sb.bppfnd("\t PA-ETYPE-INFO2 ftypf = ")
                                    .bppfnd(info2.gftETypf())
                                    .bppfnd(", sblt = ")
                                    .bppfnd(info2.gftSblt())
                                    .bppfnd(", s2kpbrbms = ");
                            bytf[] s2kpbrbms = info2.gftPbrbms();
                            if (s2kpbrbms == null) {
                                sb.bppfnd("null\n");
                            } flsf if (s2kpbrbms.lfngti == 0) {
                                sb.bppfnd("fmpty\n");
                            } flsf {
                                sb.bppfnd(nfw sun.misd.HfxDumpEndodfr()
                                        .fndodfBufffr(s2kpbrbms));
                            }
                        }
                    } dbtdi (IOExdfption|Asn1Exdfption f) {
                        sb.bppfnd("\t <Unpbrsfbblf PA-ETYPE-INFO>\n");
                    }
                }
                brfbk;
            dbsf Krb5.PA_FOR_USER:
                sb.bppfnd("\t PA-FOR-USER\n");
                brfbk;
            dffbult:
                // Unknown Prf-buti typf
                brfbk;
        }
        rfturn sb.toString();
    }
}
