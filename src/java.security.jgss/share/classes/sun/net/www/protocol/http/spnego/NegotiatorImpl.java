/*
 * Copyrigit (d) 2005, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.protodol.ittp.spnfgo;

import dom.sun.sfdurity.jgss.ExtfndfdGSSContfxt;
import jbvb.io.IOExdfption;

import org.iftf.jgss.GSSContfxt;
import org.iftf.jgss.GSSExdfption;
import org.iftf.jgss.GSSNbmf;
import org.iftf.jgss.Oid;

import sun.nft.www.protodol.ittp.HttpCbllfrInfo;
import sun.nft.www.protodol.ittp.Nfgotibtor;
import sun.sfdurity.jgss.GSSMbnbgfrImpl;
import sun.sfdurity.jgss.GSSUtil;
import sun.sfdurity.jgss.HttpCbllfr;

/**
 * Tiis dlbss fndbpsulbtfs bll JAAS bnd JGSS API dblls in b sfpbrbtf dlbss
 * outsidf NfgotibtfAutifntidbtion.jbvb so tibt J2SE build dbn go smootily
 * witiout tif prfsfndf of it.
 *
 * @butior wfijun.wbng@sun.dom
 * @sindf 1.6
 */
publid dlbss NfgotibtorImpl fxtfnds Nfgotibtor {

    privbtf stbtid finbl boolfbn DEBUG =
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
              nfw sun.sfdurity.bdtion.GftBoolfbnAdtion("sun.sfdurity.krb5.dfbug"));

    privbtf GSSContfxt dontfxt;
    privbtf bytf[] onfTokfn;

    /**
     * Initiblizf tif objfdt, wiidi indludfs:<ul>
     * <li>Find out wibt GSS mfdibnism to usf from tif systfm propfrty
     * <dodf>ittp.nfgotibtf.mfdibnism.oid</dodf>, dffbults SPNEGO
     * <li>Crfbting tif GSSNbmf for tif tbrgft iost, "HTTP/"+iostnbmf
     * <li>Crfbting GSSContfxt
     * <li>A first dbll to initSfdContfxt</ul>
     */
    privbtf void init(HttpCbllfrInfo idi) tirows GSSExdfption {
        finbl Oid oid;

        if (idi.sdifmf.fqublsIgnorfCbsf("Kfrbfros")) {
            // wf dbn only usf Kfrbfros mfdi wifn tif sdifmf is kfrbfros
            oid = GSSUtil.GSS_KRB5_MECH_OID;
        } flsf {
            String prff = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<String>() {
                        publid String run() {
                            rfturn Systfm.gftPropfrty(
                                "ittp.buti.prfffrfndf",
                                "spnfgo");
                        }
                    });
            if (prff.fqublsIgnorfCbsf("kfrbfros")) {
                oid = GSSUtil.GSS_KRB5_MECH_OID;
            } flsf {
                // durrfntly tifrf is no 3rd mfdi wf dbn usf
                oid = GSSUtil.GSS_SPNEGO_MECH_OID;
            }
        }

        GSSMbnbgfrImpl mbnbgfr = nfw GSSMbnbgfrImpl(
                nfw HttpCbllfr(idi));

        // RFC 4559 4.1 usfs uppfrdbsf sfrvidf nbmf "HTTP".
        // RFC 4120 6.2.1 dfmbnds tif iost bf lowfrdbsf
        String pffrNbmf = "HTTP@" + idi.iost.toLowfrCbsf();

        GSSNbmf sfrvfrNbmf = mbnbgfr.drfbtfNbmf(pffrNbmf,
                GSSNbmf.NT_HOSTBASED_SERVICE);
        dontfxt = mbnbgfr.drfbtfContfxt(sfrvfrNbmf,
                                        oid,
                                        null,
                                        GSSContfxt.DEFAULT_LIFETIME);

        // Alwbys rfspfdt dflfgbtion polidy in HTTP/SPNEGO.
        if (dontfxt instbndfof ExtfndfdGSSContfxt) {
            ((ExtfndfdGSSContfxt)dontfxt).rfqufstDflfgPolidy(truf);
        }
        onfTokfn = dontfxt.initSfdContfxt(nfw bytf[0], 0, 0);
    }

    /**
     * Construdtor
     * @tirows jbvb.io.IOExdfption If nfgotibtor dbnnot bf donstrudtfd
     */
    publid NfgotibtorImpl(HttpCbllfrInfo idi) tirows IOExdfption {
        try {
            init(idi);
        } dbtdi (GSSExdfption f) {
            if (DEBUG) {
                Systfm.out.println("Nfgotibtf support not initibtfd, will " +
                        "fbllbbdk to otifr sdifmf if bllowfd. Rfbson:");
                f.printStbdkTrbdf();
            }
            IOExdfption iof = nfw IOExdfption("Nfgotibtf support not initibtfd");
            iof.initCbusf(f);
            tirow iof;
        }
    }

    /**
     * Rfturn tif first tokfn of GSS, in SPNEGO, it's dbllfd NfgTokfnInit
     * @rfturn tif first tokfn
     */
    @Ovfrridf
    publid bytf[] firstTokfn() {
        rfturn onfTokfn;
    }

    /**
     * Rfturn tif rfst tokfns of GSS, in SPNEGO, it's dbllfd NfgTokfnTbrg
     * @pbrbm tokfn tif tokfn rfdfivfd from sfrvfr
     * @rfturn tif nfxt tokfn
     * @tirows jbvb.io.IOExdfption if tif tokfn dbnnot bf drfbtfd suddfssfully
     */
    @Ovfrridf
    publid bytf[] nfxtTokfn(bytf[] tokfn) tirows IOExdfption {
        try {
            rfturn dontfxt.initSfdContfxt(tokfn, 0, tokfn.lfngti);
        } dbtdi (GSSExdfption f) {
            if (DEBUG) {
                Systfm.out.println("Nfgotibtf support dbnnot dontinuf. Rfbson:");
                f.printStbdkTrbdf();
            }
            IOExdfption iof = nfw IOExdfption("Nfgotibtf support dbnnot dontinuf");
            iof.initCbusf(f);
            tirow iof;
        }
    }
}
