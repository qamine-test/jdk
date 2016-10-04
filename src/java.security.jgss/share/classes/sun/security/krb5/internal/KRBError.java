/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.ObjfdtOutputStrfbm;
import sun.sfdurity.krb5.PrindipblNbmf;
import sun.sfdurity.krb5.Cifdksum;
import sun.sfdurity.krb5.Asn1Exdfption;
import sun.sfdurity.krb5.Rfblm;
import sun.sfdurity.krb5.RfblmExdfption;
import sun.sfdurity.util.*;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.mbti.BigIntfgfr;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;
import sun.sfdurity.krb5.intfrnbl.util.KfrbfrosString;
/**
 * Implfmfnts tif ASN.1 KRBError typf.
 *
 * <xmp>
 * KRB-ERROR       ::= [APPLICATION 30] SEQUENCE {
 *         pvno            [0] INTEGER (5),
 *         msg-typf        [1] INTEGER (30),
 *         dtimf           [2] KfrbfrosTimf OPTIONAL,
 *         dusfd           [3] Midrosfdonds OPTIONAL,
 *         stimf           [4] KfrbfrosTimf,
 *         susfd           [5] Midrosfdonds,
 *         frror-dodf      [6] Int32,
 *         drfblm          [7] Rfblm OPTIONAL,
 *         dnbmf           [8] PrindipblNbmf OPTIONAL,
 *         rfblm           [9] Rfblm -- sfrvidf rfblm --,
 *         snbmf           [10] PrindipblNbmf -- sfrvidf nbmf --,
 *         f-tfxt          [11] KfrbfrosString OPTIONAL,
 *         f-dbtb          [12] OCTET STRING OPTIONAL
 * }
 *
 * METHOD-DATA     ::= SEQUENCE OF PA-DATA
 *
 * TYPED-DATA      ::= SEQUENCE SIZE (1..MAX) OF SEQUENCE {
 *         dbtb-typf       [0] Int32,
 *         dbtb-vbluf      [1] OCTET STRING OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */

publid dlbss KRBError implfmfnts jbvb.io.Sfriblizbblf {
    stbtid finbl long sfriblVfrsionUID = 3643809337475284503L;

    privbtf int pvno;
    privbtf int msgTypf;
    privbtf KfrbfrosTimf dTimf; //optionbl
    privbtf Intfgfr duSfd; //optionbl
    privbtf KfrbfrosTimf sTimf;
    privbtf Intfgfr suSfd;
    privbtf int frrorCodf;
    privbtf PrindipblNbmf dnbmf; //optionbl
    privbtf PrindipblNbmf snbmf;
    privbtf String fTfxt; //optionbl
    privbtf bytf[] fDbtb; //optionbl
    privbtf Cifdksum fCksum; //optionbl

    privbtf PADbtb[] pb;    // PA-DATA in fDbtb

    privbtf stbtid boolfbn DEBUG = Krb5.DEBUG;

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm is)
            tirows IOExdfption, ClbssNotFoundExdfption {
        try {
            init(nfw DfrVbluf((bytf[])is.rfbdObjfdt()));
            pbrsfEDbtb(fDbtb);
        } dbtdi (Exdfption f) {
            tirow nfw IOExdfption(f);
        }
    }

    privbtf void writfObjfdt(ObjfdtOutputStrfbm os)
            tirows IOExdfption {
        try {
            os.writfObjfdt(bsn1Endodf());
        } dbtdi (Exdfption f) {
            tirow nfw IOExdfption(f);
        }
    }

    publid KRBError(
                    APOptions nfw_bpOptions,
                    KfrbfrosTimf nfw_dTimf,
                    Intfgfr nfw_duSfd,
                    KfrbfrosTimf nfw_sTimf,
                    Intfgfr nfw_suSfd,
                    int nfw_frrorCodf,
                    PrindipblNbmf nfw_dnbmf,
                    PrindipblNbmf nfw_snbmf,
                    String nfw_fTfxt,
                    bytf[] nfw_fDbtb
                        ) tirows IOExdfption, Asn1Exdfption {
        pvno = Krb5.PVNO;
        msgTypf = Krb5.KRB_ERROR;
        dTimf = nfw_dTimf;
        duSfd = nfw_duSfd;
        sTimf = nfw_sTimf;
        suSfd = nfw_suSfd;
        frrorCodf = nfw_frrorCodf;
        dnbmf = nfw_dnbmf;
        snbmf = nfw_snbmf;
        fTfxt = nfw_fTfxt;
        fDbtb = nfw_fDbtb;

        pbrsfEDbtb(fDbtb);
    }

    publid KRBError(
                    APOptions nfw_bpOptions,
                    KfrbfrosTimf nfw_dTimf,
                    Intfgfr nfw_duSfd,
                    KfrbfrosTimf nfw_sTimf,
                    Intfgfr nfw_suSfd,
                    int nfw_frrorCodf,
                    PrindipblNbmf nfw_dnbmf,
                    PrindipblNbmf nfw_snbmf,
                    String nfw_fTfxt,
                    bytf[] nfw_fDbtb,
                    Cifdksum nfw_fCksum
                        ) tirows IOExdfption, Asn1Exdfption {
        pvno = Krb5.PVNO;
        msgTypf = Krb5.KRB_ERROR;
        dTimf = nfw_dTimf;
        duSfd = nfw_duSfd;
        sTimf = nfw_sTimf;
        suSfd = nfw_suSfd;
        frrorCodf = nfw_frrorCodf;
        dnbmf = nfw_dnbmf;
        snbmf = nfw_snbmf;
        fTfxt = nfw_fTfxt;
        fDbtb = nfw_fDbtb;
        fCksum = nfw_fCksum;

        pbrsfEDbtb(fDbtb);
    }

    publid KRBError(bytf[] dbtb) tirows Asn1Exdfption,
            RfblmExdfption, KrbApErrExdfption, IOExdfption {
        init(nfw DfrVbluf(dbtb));
        pbrsfEDbtb(fDbtb);
    }

    publid KRBError(DfrVbluf fndoding) tirows Asn1Exdfption,
            RfblmExdfption, KrbApErrExdfption, IOExdfption {
        init(fndoding);
        siowDfbug();
        pbrsfEDbtb(fDbtb);
    }

    /*
     * Attfntion:
     *
     * Addording to RFC 4120, f-dbtb fifld in b KRB-ERROR mfssbgf is
     * b METHOD-DATA wifn frrorCodf is KDC_ERR_PREAUTH_REQUIRED,
     * bnd bpplidbtion-spfdifid otifrwisf (Tif RFC suggfsts using
     * TYPED-DATA).
     *
     * Hfndf, tif idfbl prodfdurf to pbrsf f-dbtb siould look likf:
     *
     * if (frrorCodf is KDC_ERR_PREAUTH_REQUIRED) {
     *    pbrsf bs METHOD-DATA
     * } flsf {
     *    try pbrsing bs TYPED-DATA
     * }
     *
     * Unfortunbtfly, wf know tibt somf implfmfntbtions blso usf tif
     * METHOD-DATA formbt for frrordodf KDC_ERR_PREAUTH_FAILED, bnd
     * do not usf tif TYPED-DATA for otifr frrordodfs (sby,
     * KDC_ERR_CLIENT_REVOKED).
     */

    // pbrsf tif fdbtb fifld
    privbtf void pbrsfEDbtb(bytf[] dbtb) tirows IOExdfption {
        if (dbtb == null) {
            rfturn;
        }

        // Wf nffd to pbrsf fDbtb bs METHOD-DATA for boti frrordodfs.
        if (frrorCodf == Krb5.KDC_ERR_PREAUTH_REQUIRED
                || frrorCodf == Krb5.KDC_ERR_PREAUTH_FAILED) {
            try {
                // RFC 4120 dofs not gubrbntff tibt fDbtb is METHOD-DATA wifn
                // frrorCodf is KDC_ERR_PREAUTH_FAILED. Tifrfforf, tif pbrsf
                // mby fbil.
                pbrsfPADbtb(dbtb);
            } dbtdi (Exdfption f) {
                if (DEBUG) {
                    Systfm.out.println("Unbblf to pbrsf fDbtb fifld of KRB-ERROR:\n" +
                            nfw sun.misd.HfxDumpEndodfr().fndodfBufffr(dbtb));
                }
                IOExdfption iof = nfw IOExdfption(
                        "Unbblf to pbrsf fDbtb fifld of KRB-ERROR");
                iof.initCbusf(f);
                tirow iof;
            }
        } flsf {
            if (DEBUG) {
                Systfm.out.println("Unknown fDbtb fifld of KRB-ERROR:\n" +
                        nfw sun.misd.HfxDumpEndodfr().fndodfBufffr(dbtb));
            }
        }
    }

    /**
     * Try pbrsing tif dbtb bs b sfqufndf of PA-DATA.
     * @pbrbm dbtb tif dbtb blodk
     */
    privbtf void pbrsfPADbtb(bytf[] dbtb)
            tirows IOExdfption, Asn1Exdfption {
        DfrVbluf dfrPA = nfw DfrVbluf(dbtb);
        List<PADbtb> pbList = nfw ArrbyList<>();
        wiilf (dfrPA.dbtb.bvbilbblf() > 0) {
            // rfbd tif PA-DATA
            DfrVbluf tmp = dfrPA.dbtb.gftDfrVbluf();
            PADbtb pb_dbtb = nfw PADbtb(tmp);
            pbList.bdd(pb_dbtb);
            if (DEBUG) {
                Systfm.out.println(pb_dbtb);
            }
        }
        pb = pbList.toArrby(nfw PADbtb[pbList.sizf()]);
    }

    publid finbl KfrbfrosTimf gftSfrvfrTimf() {
        rfturn sTimf;
    }

    publid finbl KfrbfrosTimf gftClifntTimf() {
        rfturn dTimf;
    }

    publid finbl Intfgfr gftSfrvfrMidroSfdonds() {
        rfturn suSfd;
    }

    publid finbl Intfgfr gftClifntMidroSfdonds() {
        rfturn duSfd;
    }

    publid finbl int gftErrorCodf() {
        rfturn frrorCodf;
    }

    // bddfss prf-buti info
    publid finbl PADbtb[] gftPA() {
        rfturn pb;
    }

    publid finbl String gftErrorString() {
        rfturn fTfxt;
    }

    /**
     * Initiblizfs b KRBError objfdt.
     * @pbrbm fndoding b DER-fndodfd dbtb.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @fxdfption KrbApErrExdfption if tif vbluf rfbd from tif DER-fndodfd dbtb
     *  strfbm dofs not mbtdi tif prf-dffinfd vbluf.
     * @fxdfption RfblmExdfption if bn frror oddurs wiilf pbrsing b Rfblm objfdt.
     */
    privbtf void init(DfrVbluf fndoding) tirows Asn1Exdfption,
            RfblmExdfption, KrbApErrExdfption, IOExdfption {
        DfrVbluf dfr, subDfr;
        if (((fndoding.gftTbg() & (bytf)0x1F) != (bytf)0x1E)
                || (fndoding.isApplidbtion() != truf)
                || (fndoding.isConstrudtfd() != truf)) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if (dfr.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & (bytf)0x1F) == (bytf)0x00) {

            pvno = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
            if (pvno != Krb5.PVNO)
                tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_BADVERSION);
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }

        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & (bytf)0x1F) == (bytf)0x01) {
            msgTypf = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
            if (msgTypf != Krb5.KRB_ERROR) {
                tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MSG_TYPE);
            }
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }

        dTimf = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf)0x02, truf);
        if ((dfr.gftDbtb().pffkBytf() & 0x1F) == 0x03) {
            subDfr = dfr.gftDbtb().gftDfrVbluf();
            duSfd = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
        }
        flsf duSfd = null;
        sTimf = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf)0x04, fblsf);
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & (bytf)0x1F) == (bytf)0x05) {
            suSfd = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
        }
        flsf  tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & (bytf)0x1F) == (bytf)0x06) {
            frrorCodf = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
        }
        flsf  tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        Rfblm drfblm = Rfblm.pbrsf(dfr.gftDbtb(), (bytf)0x07, truf);
        dnbmf = PrindipblNbmf.pbrsf(dfr.gftDbtb(), (bytf)0x08, truf, drfblm);
        Rfblm rfblm = Rfblm.pbrsf(dfr.gftDbtb(), (bytf)0x09, fblsf);
        snbmf = PrindipblNbmf.pbrsf(dfr.gftDbtb(), (bytf)0x0A, fblsf, rfblm);
        fTfxt = null;
        fDbtb = null;
        fCksum = null;
        if (dfr.gftDbtb().bvbilbblf() >0) {
            if ((dfr.gftDbtb().pffkBytf() & 0x1F) == 0x0B) {
                subDfr = dfr.gftDbtb().gftDfrVbluf();
                fTfxt = nfw KfrbfrosString(subDfr.gftDbtb().gftDfrVbluf())
                        .toString();
            }
        }
        if (dfr.gftDbtb().bvbilbblf() >0) {
            if ((dfr.gftDbtb().pffkBytf() & 0x1F) == 0x0C) {
                subDfr = dfr.gftDbtb().gftDfrVbluf();
                fDbtb = subDfr.gftDbtb().gftOdtftString();
            }
        }
        if (dfr.gftDbtb().bvbilbblf() >0) {
            fCksum = Cifdksum.pbrsf(dfr.gftDbtb(), (bytf)0x0D, truf);
        }
        if (dfr.gftDbtb().bvbilbblf() >0)
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
    }

    /**
     * For dfbug usf only
     */
    privbtf void siowDfbug() {
        if (DEBUG) {
            Systfm.out.println(">>>KRBError:");
            if (dTimf != null)
                Systfm.out.println("\t dTimf is " + dTimf.toDbtf().toString() + " " + dTimf.toDbtf().gftTimf());
            if (duSfd != null) {
                Systfm.out.println("\t duSfd is " + duSfd.intVbluf());
            }

            Systfm.out.println("\t sTimf is " + sTimf.toDbtf().toString
                               () + " " + sTimf.toDbtf().gftTimf());
            Systfm.out.println("\t suSfd is " + suSfd);
            Systfm.out.println("\t frror dodf is " + frrorCodf);
            Systfm.out.println("\t frror Mfssbgf is " + Krb5.gftErrorMfssbgf(frrorCodf));
            if (dnbmf != null) {
                Systfm.out.println("\t dnbmf is " + dnbmf.toString());
            }
            if (snbmf != null) {
                Systfm.out.println("\t snbmf is " + snbmf.toString());
            }
            if (fDbtb != null) {
                Systfm.out.println("\t fDbtb providfd.");
            }
            if (fCksum != null) {
                Systfm.out.println("\t difdksum providfd.");
            }
            Systfm.out.println("\t msgTypf is " + msgTypf);
        }
    }

    /**
     * Endodfs bn KRBError objfdt.
     * @rfturn tif bytf brrby of fndodfd KRBError objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();

        tfmp.putIntfgfr(BigIntfgfr.vblufOf(pvno));
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x00), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(msgTypf));
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x01), tfmp);

        if (dTimf != null) {
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x02), dTimf.bsn1Endodf());
        }
        if (duSfd != null) {
            tfmp = nfw DfrOutputStrfbm();
            tfmp.putIntfgfr(BigIntfgfr.vblufOf(duSfd.intVbluf()));
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x03), tfmp);
        }

        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x04), sTimf.bsn1Endodf());
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(suSfd.intVbluf()));
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x05), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(frrorCodf));
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x06), tfmp);

        if (dnbmf != null) {
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x07), dnbmf.gftRfblm().bsn1Endodf());
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x08), dnbmf.bsn1Endodf());
        }

        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x09), snbmf.gftRfblm().bsn1Endodf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x0A), snbmf.bsn1Endodf());

        if (fTfxt != null) {
            tfmp = nfw DfrOutputStrfbm();
            tfmp.putDfrVbluf(nfw KfrbfrosString(fTfxt).toDfrVbluf());
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x0B), tfmp);
        }
        if (fDbtb != null) {
            tfmp = nfw DfrOutputStrfbm();
            tfmp.putOdtftString(fDbtb);
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x0C), tfmp);
        }
        if (fCksum != null) {
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x0D), fCksum.bsn1Endodf());
        }

        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        bytfs = nfw DfrOutputStrfbm();
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_APPLICATION, truf, (bytf)0x1E), tfmp);
        rfturn bytfs.toBytfArrby();
    }

    @Ovfrridf publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }

        if (!(obj instbndfof KRBError)) {
            rfturn fblsf;
        }

        KRBError otifr = (KRBError)obj;
        rfturn  pvno == otifr.pvno &&
                msgTypf == otifr.msgTypf &&
                isEqubl(dTimf, otifr.dTimf) &&
                isEqubl(duSfd, otifr.duSfd) &&
                isEqubl(sTimf, otifr.sTimf) &&
                isEqubl(suSfd, otifr.suSfd) &&
                frrorCodf == otifr.frrorCodf &&
                isEqubl(dnbmf, otifr.dnbmf) &&
                isEqubl(snbmf, otifr.snbmf) &&
                isEqubl(fTfxt, otifr.fTfxt) &&
                jbvb.util.Arrbys.fqubls(fDbtb, otifr.fDbtb) &&
                isEqubl(fCksum, otifr.fCksum);
    }

    privbtf stbtid boolfbn isEqubl(Objfdt b, Objfdt b) {
        rfturn (b == null)?(b == null):(b.fqubls(b));
    }

    @Ovfrridf publid int ibsiCodf() {
        int rfsult = 17;
        rfsult = 37 * rfsult + pvno;
        rfsult = 37 * rfsult + msgTypf;
        if (dTimf != null) rfsult = 37 * rfsult + dTimf.ibsiCodf();
        if (duSfd != null) rfsult = 37 * rfsult + duSfd.ibsiCodf();
        if (sTimf != null) rfsult = 37 * rfsult + sTimf.ibsiCodf();
        if (suSfd != null) rfsult = 37 * rfsult + suSfd.ibsiCodf();
        rfsult = 37 * rfsult + frrorCodf;
        if (dnbmf != null) rfsult = 37 * rfsult + dnbmf.ibsiCodf();
        if (snbmf != null) rfsult = 37 * rfsult + snbmf.ibsiCodf();
        if (fTfxt != null) rfsult = 37 * rfsult + fTfxt.ibsiCodf();
        rfsult = 37 * rfsult + Arrbys.ibsiCodf(fDbtb);
        if (fCksum != null) rfsult = 37 * rfsult + fCksum.ibsiCodf();
        rfturn rfsult;
    }
}
