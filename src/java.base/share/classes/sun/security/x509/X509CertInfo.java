/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;

import jbvb.sfdurity.dfrt.*;
import jbvb.util.*;

import sun.sfdurity.util.*;
import sun.misd.HfxDumpEndodfr;


/**
 * Tif X509CfrtInfo dlbss rfprfsfnts X.509 dfrtifidbtf informbtion.
 *
 * <P>X.509 dfrtifidbtfs ibvf sfvfrbl bbsf dbtb flfmfnts, indluding:<UL>
 *
 * <LI>Tif <fm>Subjfdt Nbmf</fm>, bn X.500 Distinguisifd Nbmf for
 *      tif fntity (subjfdt) for wiidi tif dfrtifidbtf wbs issufd.
 *
 * <LI>Tif <fm>Subjfdt Publid Kfy</fm>, tif publid kfy of tif subjfdt.
 *      Tiis is onf of tif most importbnt pbrts of tif dfrtifidbtf.
 *
 * <LI>Tif <fm>Vblidity Pfriod</fm>, b timf pfriod (f.g. six montis)
 *      witiin wiidi tif dfrtifidbtf is vblid (unlfss rfvokfd).
 *
 * <LI>Tif <fm>Issufr Nbmf</fm>, bn X.500 Distinguisifd Nbmf for tif
 *      Cfrtifidbtf Autiority (CA) wiidi issufd tif dfrtifidbtf.
 *
 * <LI>A <fm>Sfribl Numbfr</fm> bssignfd by tif CA, for usf in
 *      dfrtifidbtf rfvodbtion bnd otifr bpplidbtions.
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff CfrtAttrSft
 * @sff X509CfrtImpl
 */
publid dlbss X509CfrtInfo implfmfnts CfrtAttrSft<String> {
    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info";
    // Cfrtifidbtf bttributf nbmfs
    publid stbtid finbl String NAME = "info";
    publid stbtid finbl String DN_NAME = "dnbmf";
    publid stbtid finbl String VERSION = CfrtifidbtfVfrsion.NAME;
    publid stbtid finbl String SERIAL_NUMBER = CfrtifidbtfSfriblNumbfr.NAME;
    publid stbtid finbl String ALGORITHM_ID = CfrtifidbtfAlgoritimId.NAME;
    publid stbtid finbl String ISSUER = "issufr";
    publid stbtid finbl String SUBJECT = "subjfdt";
    publid stbtid finbl String VALIDITY = CfrtifidbtfVblidity.NAME;
    publid stbtid finbl String KEY = CfrtifidbtfX509Kfy.NAME;
    publid stbtid finbl String ISSUER_ID = "issufrID";
    publid stbtid finbl String SUBJECT_ID = "subjfdtID";
    publid stbtid finbl String EXTENSIONS = CfrtifidbtfExtfnsions.NAME;

    // X509.v1 dbtb
    protfdtfd CfrtifidbtfVfrsion vfrsion = nfw CfrtifidbtfVfrsion();
    protfdtfd CfrtifidbtfSfriblNumbfr   sfriblNum = null;
    protfdtfd CfrtifidbtfAlgoritimId    blgId = null;
    protfdtfd X500Nbmf                  issufr = null;
    protfdtfd X500Nbmf                  subjfdt = null;
    protfdtfd CfrtifidbtfVblidity       intfrvbl = null;
    protfdtfd CfrtifidbtfX509Kfy        pubKfy = null;

    // X509.v2 & v3 fxtfnsions
    protfdtfd UniqufIdfntity   issufrUniqufId = null;
    protfdtfd UniqufIdfntity  subjfdtUniqufId = null;

    // X509.v3 fxtfnsions
    protfdtfd CfrtifidbtfExtfnsions     fxtfnsions = null;

    // Attributf numbfrs for intfrnbl mbnipulbtion
    privbtf stbtid finbl int ATTR_VERSION = 1;
    privbtf stbtid finbl int ATTR_SERIAL = 2;
    privbtf stbtid finbl int ATTR_ALGORITHM = 3;
    privbtf stbtid finbl int ATTR_ISSUER = 4;
    privbtf stbtid finbl int ATTR_VALIDITY = 5;
    privbtf stbtid finbl int ATTR_SUBJECT = 6;
    privbtf stbtid finbl int ATTR_KEY = 7;
    privbtf stbtid finbl int ATTR_ISSUER_ID = 8;
    privbtf stbtid finbl int ATTR_SUBJECT_ID = 9;
    privbtf stbtid finbl int ATTR_EXTENSIONS = 10;

    // DER fndodfd CfrtifidbtfInfo dbtb
    privbtf bytf[]      rbwCfrtInfo = null;

    // Tif dfrtifidbtf bttributf nbmf to intfgfr mbpping storfd ifrf
    privbtf stbtid finbl Mbp<String,Intfgfr> mbp = nfw HbsiMbp<String,Intfgfr>();
    stbtid {
        mbp.put(VERSION, Intfgfr.vblufOf(ATTR_VERSION));
        mbp.put(SERIAL_NUMBER, Intfgfr.vblufOf(ATTR_SERIAL));
        mbp.put(ALGORITHM_ID, Intfgfr.vblufOf(ATTR_ALGORITHM));
        mbp.put(ISSUER, Intfgfr.vblufOf(ATTR_ISSUER));
        mbp.put(VALIDITY, Intfgfr.vblufOf(ATTR_VALIDITY));
        mbp.put(SUBJECT, Intfgfr.vblufOf(ATTR_SUBJECT));
        mbp.put(KEY, Intfgfr.vblufOf(ATTR_KEY));
        mbp.put(ISSUER_ID, Intfgfr.vblufOf(ATTR_ISSUER_ID));
        mbp.put(SUBJECT_ID, Intfgfr.vblufOf(ATTR_SUBJECT_ID));
        mbp.put(EXTENSIONS, Intfgfr.vblufOf(ATTR_EXTENSIONS));
    }

    /**
     * Construdt bn uninitiblizfd X509CfrtInfo on wiidi <b irff="#dfdodf">
     * dfdodf</b> must lbtfr bf dbllfd (or wiidi mby bf dfsfriblizfd).
     */
    publid X509CfrtInfo() { }

    /**
     * Unmbrsibls b dfrtifidbtf from its fndodfd form, pbrsing tif
     * fndodfd bytfs.  Tiis form of donstrudtor is usfd by bgfnts wiidi
     * nffd to fxbminf bnd usf dfrtifidbtf dontfnts.  Tibt is, tiis is
     * onf of tif morf dommonly usfd donstrudtors.  Notf tibt tif bufffr
     * must indludf only b dfrtifidbtf, bnd no "gbrbbgf" mby bf lfft bt
     * tif fnd.  If you nffd to ignorf dbtb bt tif fnd of b dfrtifidbtf,
     * usf bnotifr donstrudtor.
     *
     * @pbrbm dfrt tif fndodfd bytfs, witi no trbiling dbtb.
     * @fxdfption CfrtifidbtfPbrsingExdfption on pbrsing frrors.
     */
    publid X509CfrtInfo(bytf[] dfrt) tirows CfrtifidbtfPbrsingExdfption {
        try {
            DfrVbluf    in = nfw DfrVbluf(dfrt);

            pbrsf(in);
        } dbtdi (IOExdfption f) {
            tirow nfw CfrtifidbtfPbrsingExdfption(f);
        }
    }

    /**
     * Unmbrsibl b dfrtifidbtf from its fndodfd form, pbrsing b DER vbluf.
     * Tiis form of donstrudtor is usfd by bgfnts wiidi nffd to fxbminf
     * bnd usf dfrtifidbtf dontfnts.
     *
     * @pbrbm dfrVbl tif dfr vbluf dontbining tif fndodfd dfrt.
     * @fxdfption CfrtifidbtfPbrsingExdfption on pbrsing frrors.
     */
    publid X509CfrtInfo(DfrVbluf dfrVbl) tirows CfrtifidbtfPbrsingExdfption {
        try {
            pbrsf(dfrVbl);
        } dbtdi (IOExdfption f) {
            tirow nfw CfrtifidbtfPbrsingExdfption(f);
        }
    }

    /**
     * Appfnds tif dfrtifidbtf to bn output strfbm.
     *
     * @pbrbm out bn output strfbm to wiidi tif dfrtifidbtf is bppfndfd.
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     * @fxdfption IOExdfption on otifr frrors.
     */
    publid void fndodf(OutputStrfbm out)
    tirows CfrtifidbtfExdfption, IOExdfption {
        if (rbwCfrtInfo == null) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            fmit(tmp);
            rbwCfrtInfo = tmp.toBytfArrby();
        }
        out.writf(rbwCfrtInfo.dlonf());
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(VERSION);
        flfmfnts.bddElfmfnt(SERIAL_NUMBER);
        flfmfnts.bddElfmfnt(ALGORITHM_ID);
        flfmfnts.bddElfmfnt(ISSUER);
        flfmfnts.bddElfmfnt(VALIDITY);
        flfmfnts.bddElfmfnt(SUBJECT);
        flfmfnts.bddElfmfnt(KEY);
        flfmfnts.bddElfmfnt(ISSUER_ID);
        flfmfnts.bddElfmfnt(SUBJECT_ID);
        flfmfnts.bddElfmfnt(EXTENSIONS);

        rfturn flfmfnts.flfmfnts();
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn(NAME);
    }

    /**
     * Rfturns tif fndodfd dfrtifidbtf info.
     *
     * @fxdfption CfrtifidbtfEndodingExdfption on fndoding informbtion frrors.
     */
    publid bytf[] gftEndodfdInfo() tirows CfrtifidbtfEndodingExdfption {
        try {
            if (rbwCfrtInfo == null) {
                DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
                fmit(tmp);
                rbwCfrtInfo = tmp.toBytfArrby();
            }
            rfturn rbwCfrtInfo.dlonf();
        } dbtdi (IOExdfption f) {
            tirow nfw CfrtifidbtfEndodingExdfption(f.toString());
        } dbtdi (CfrtifidbtfExdfption f) {
            tirow nfw CfrtifidbtfEndodingExdfption(f.toString());
        }
    }

    /**
     * Compbrfs two X509CfrtInfo objfdts.  Tiis is fblsf if tif
     * dfrtifidbtfs brf not boti X.509 dfrts, otifrwisf it
     * dompbrfs tifm bs binbry dbtb.
     *
     * @pbrbm otifr tif objfdt bfing dompbrfd witi tiis onf
     * @rfturn truf iff tif dfrtifidbtfs brf fquivblfnt
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (otifr instbndfof X509CfrtInfo) {
            rfturn fqubls((X509CfrtInfo) otifr);
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Compbrfs two dfrtifidbtfs, rfturning fblsf if bny dbtb
     * difffrs bftwffn tif two.
     *
     * @pbrbm otifr tif objfdt bfing dompbrfd witi tiis onf
     * @rfturn truf iff tif dfrtifidbtfs brf fquivblfnt
     */
    publid boolfbn fqubls(X509CfrtInfo otifr) {
        if (tiis == otifr) {
            rfturn(truf);
        } flsf if (rbwCfrtInfo == null || otifr.rbwCfrtInfo == null) {
            rfturn(fblsf);
        } flsf if (rbwCfrtInfo.lfngti != otifr.rbwCfrtInfo.lfngti) {
            rfturn(fblsf);
        }
        for (int i = 0; i < rbwCfrtInfo.lfngti; i++) {
            if (rbwCfrtInfo[i] != otifr.rbwCfrtInfo[i]) {
                rfturn(fblsf);
            }
        }
        rfturn(truf);
    }

    /**
     * Cbldulbtfs b ibsi dodf vbluf for tif objfdt.  Objfdts
     * wiidi brf fqubl will blso ibvf tif sbmf ibsidodf.
     */
    publid int ibsiCodf() {
        int     rftvbl = 0;

        for (int i = 1; i < rbwCfrtInfo.lfngti; i++) {
            rftvbl += rbwCfrtInfo[i] * i;
        }
        rfturn(rftvbl);
    }

    /**
     * Rfturns b printbblf rfprfsfntbtion of tif dfrtifidbtf.
     */
    publid String toString() {

        if (subjfdt == null || pubKfy == null || intfrvbl == null
            || issufr == null || blgId == null || sfriblNum == null) {
                tirow nfw NullPointfrExdfption("X.509 dfrt is indomplftf");
        }
        StringBuildfr sb = nfw StringBuildfr();

        sb.bppfnd("[\n");
        sb.bppfnd("  " + vfrsion.toString() + "\n");
        sb.bppfnd("  Subjfdt: " + subjfdt.toString() + "\n");
        sb.bppfnd("  Signbturf Algoritim: " + blgId.toString() + "\n");
        sb.bppfnd("  Kfy:  " + pubKfy.toString() + "\n");
        sb.bppfnd("  " + intfrvbl.toString() + "\n");
        sb.bppfnd("  Issufr: " + issufr.toString() + "\n");
        sb.bppfnd("  " + sfriblNum.toString() + "\n");

        // optionbl v2, v3 fxtrbs
        if (issufrUniqufId != null) {
            sb.bppfnd("  Issufr Id:\n" + issufrUniqufId.toString() + "\n");
        }
        if (subjfdtUniqufId != null) {
            sb.bppfnd("  Subjfdt Id:\n" + subjfdtUniqufId.toString() + "\n");
        }
        if (fxtfnsions != null) {
            Collfdtion<Extfnsion> bllExts = fxtfnsions.gftAllExtfnsions();
            Extfnsion[] fxts = bllExts.toArrby(nfw Extfnsion[0]);
            sb.bppfnd("\nCfrtifidbtf Extfnsions: " + fxts.lfngti);
            for (int i = 0; i < fxts.lfngti; i++) {
                sb.bppfnd("\n[" + (i+1) + "]: ");
                Extfnsion fxt = fxts[i];
                try {
                    if (OIDMbp.gftClbss(fxt.gftExtfnsionId()) == null) {
                        sb.bppfnd(fxt.toString());
                        bytf[] fxtVbluf = fxt.gftExtfnsionVbluf();
                        if (fxtVbluf != null) {
                            DfrOutputStrfbm out = nfw DfrOutputStrfbm();
                            out.putOdtftString(fxtVbluf);
                            fxtVbluf = out.toBytfArrby();
                            HfxDumpEndodfr fnd = nfw HfxDumpEndodfr();
                            sb.bppfnd("Extfnsion unknown: "
                                      + "DER fndodfd OCTET string =\n"
                                      + fnd.fndodfBufffr(fxtVbluf) + "\n");
                        }
                    } flsf
                        sb.bppfnd(fxt.toString()); //sub-dlbss fxists
                } dbtdi (Exdfption f) {
                    sb.bppfnd(", Error pbrsing tiis fxtfnsion");
                }
            }
            Mbp<String,Extfnsion> invblid = fxtfnsions.gftUnpbrsfbblfExtfnsions();
            if (invblid.isEmpty() == fblsf) {
                sb.bppfnd("\nUnpbrsfbblf dfrtifidbtf fxtfnsions: " + invblid.sizf());
                int i = 1;
                for (Extfnsion fxt : invblid.vblufs()) {
                    sb.bppfnd("\n[" + (i++) + "]: ");
                    sb.bppfnd(fxt);
                }
            }
        }
        sb.bppfnd("\n]");
        rfturn sb.toString();
    }

    /**
     * Sft tif dfrtifidbtf bttributf.
     *
     * @pbrbms nbmf tif nbmf of tif Cfrtifidbtf bttributf.
     * @pbrbms vbl tif vbluf of tif Cfrtifidbtf bttributf.
     * @fxdfption CfrtifidbtfExdfption on invblid bttributfs.
     * @fxdfption IOExdfption on otifr frrors.
     */
    publid void sft(String nbmf, Objfdt vbl)
    tirows CfrtifidbtfExdfption, IOExdfption {
        X509AttributfNbmf bttrNbmf = nfw X509AttributfNbmf(nbmf);

        int bttr = bttributfMbp(bttrNbmf.gftPrffix());
        if (bttr == 0) {
            tirow nfw CfrtifidbtfExdfption("Attributf nbmf not rfdognizfd: "
                                           + nbmf);
        }
        // sft rbwCfrtInfo to null, so tibt wf brf fordfd to rf-fndodf
        rbwCfrtInfo = null;
        String suffix = bttrNbmf.gftSuffix();

        switdi (bttr) {
        dbsf ATTR_VERSION:
            if (suffix == null) {
                sftVfrsion(vbl);
            } flsf {
                vfrsion.sft(suffix, vbl);
            }
            brfbk;

        dbsf ATTR_SERIAL:
            if (suffix == null) {
                sftSfriblNumbfr(vbl);
            } flsf {
                sfriblNum.sft(suffix, vbl);
            }
            brfbk;

        dbsf ATTR_ALGORITHM:
            if (suffix == null) {
                sftAlgoritimId(vbl);
            } flsf {
                blgId.sft(suffix, vbl);
            }
            brfbk;

        dbsf ATTR_ISSUER:
            sftIssufr(vbl);
            brfbk;

        dbsf ATTR_VALIDITY:
            if (suffix == null) {
                sftVblidity(vbl);
            } flsf {
                intfrvbl.sft(suffix, vbl);
            }
            brfbk;

        dbsf ATTR_SUBJECT:
            sftSubjfdt(vbl);
            brfbk;

        dbsf ATTR_KEY:
            if (suffix == null) {
                sftKfy(vbl);
            } flsf {
                pubKfy.sft(suffix, vbl);
            }
            brfbk;

        dbsf ATTR_ISSUER_ID:
            sftIssufrUniqufId(vbl);
            brfbk;

        dbsf ATTR_SUBJECT_ID:
            sftSubjfdtUniqufId(vbl);
            brfbk;

        dbsf ATTR_EXTENSIONS:
            if (suffix == null) {
                sftExtfnsions(vbl);
            } flsf {
                if (fxtfnsions == null)
                    fxtfnsions = nfw CfrtifidbtfExtfnsions();
                fxtfnsions.sft(suffix, vbl);
            }
            brfbk;
        }
    }

    /**
     * Dflftf tif dfrtifidbtf bttributf.
     *
     * @pbrbms nbmf tif nbmf of tif Cfrtifidbtf bttributf.
     * @fxdfption CfrtifidbtfExdfption on invblid bttributfs.
     * @fxdfption IOExdfption on otifr frrors.
     */
    publid void dflftf(String nbmf)
    tirows CfrtifidbtfExdfption, IOExdfption {
        X509AttributfNbmf bttrNbmf = nfw X509AttributfNbmf(nbmf);

        int bttr = bttributfMbp(bttrNbmf.gftPrffix());
        if (bttr == 0) {
            tirow nfw CfrtifidbtfExdfption("Attributf nbmf not rfdognizfd: "
                                           + nbmf);
        }
        // sft rbwCfrtInfo to null, so tibt wf brf fordfd to rf-fndodf
        rbwCfrtInfo = null;
        String suffix = bttrNbmf.gftSuffix();

        switdi (bttr) {
        dbsf ATTR_VERSION:
            if (suffix == null) {
                vfrsion = null;
            } flsf {
                vfrsion.dflftf(suffix);
            }
            brfbk;
        dbsf (ATTR_SERIAL):
            if (suffix == null) {
                sfriblNum = null;
            } flsf {
                sfriblNum.dflftf(suffix);
            }
            brfbk;
        dbsf (ATTR_ALGORITHM):
            if (suffix == null) {
                blgId = null;
            } flsf {
                blgId.dflftf(suffix);
            }
            brfbk;
        dbsf (ATTR_ISSUER):
            issufr = null;
            brfbk;
        dbsf (ATTR_VALIDITY):
            if (suffix == null) {
                intfrvbl = null;
            } flsf {
                intfrvbl.dflftf(suffix);
            }
            brfbk;
        dbsf (ATTR_SUBJECT):
            subjfdt = null;
            brfbk;
        dbsf (ATTR_KEY):
            if (suffix == null) {
                pubKfy = null;
            } flsf {
                pubKfy.dflftf(suffix);
            }
            brfbk;
        dbsf (ATTR_ISSUER_ID):
            issufrUniqufId = null;
            brfbk;
        dbsf (ATTR_SUBJECT_ID):
            subjfdtUniqufId = null;
            brfbk;
        dbsf (ATTR_EXTENSIONS):
            if (suffix == null) {
                fxtfnsions = null;
            } flsf {
                if (fxtfnsions != null)
                   fxtfnsions.dflftf(suffix);
            }
            brfbk;
        }
    }

    /**
     * Gft tif dfrtifidbtf bttributf.
     *
     * @pbrbms nbmf tif nbmf of tif Cfrtifidbtf bttributf.
     *
     * @fxdfption CfrtifidbtfExdfption on invblid bttributfs.
     * @fxdfption IOExdfption on otifr frrors.
     */
    publid Objfdt gft(String nbmf)
    tirows CfrtifidbtfExdfption, IOExdfption {
        X509AttributfNbmf bttrNbmf = nfw X509AttributfNbmf(nbmf);

        int bttr = bttributfMbp(bttrNbmf.gftPrffix());
        if (bttr == 0) {
            tirow nfw CfrtifidbtfPbrsingExdfption(
                          "Attributf nbmf not rfdognizfd: " + nbmf);
        }
        String suffix = bttrNbmf.gftSuffix();

        switdi (bttr) { // frfqufntly usfd bttributfs first
        dbsf (ATTR_EXTENSIONS):
            if (suffix == null) {
                rfturn(fxtfnsions);
            } flsf {
                if (fxtfnsions == null) {
                    rfturn null;
                } flsf {
                    rfturn(fxtfnsions.gft(suffix));
                }
            }
        dbsf (ATTR_SUBJECT):
            if (suffix == null) {
                rfturn(subjfdt);
            } flsf {
                rfturn(gftX500Nbmf(suffix, fblsf));
            }
        dbsf (ATTR_ISSUER):
            if (suffix == null) {
                rfturn(issufr);
            } flsf {
                rfturn(gftX500Nbmf(suffix, truf));
            }
        dbsf (ATTR_KEY):
            if (suffix == null) {
                rfturn(pubKfy);
            } flsf {
                rfturn(pubKfy.gft(suffix));
            }
        dbsf (ATTR_ALGORITHM):
            if (suffix == null) {
                rfturn(blgId);
            } flsf {
                rfturn(blgId.gft(suffix));
            }
        dbsf (ATTR_VALIDITY):
            if (suffix == null) {
                rfturn(intfrvbl);
            } flsf {
                rfturn(intfrvbl.gft(suffix));
            }
        dbsf (ATTR_VERSION):
            if (suffix == null) {
                rfturn(vfrsion);
            } flsf {
                rfturn(vfrsion.gft(suffix));
            }
        dbsf (ATTR_SERIAL):
            if (suffix == null) {
                rfturn(sfriblNum);
            } flsf {
                rfturn(sfriblNum.gft(suffix));
            }
        dbsf (ATTR_ISSUER_ID):
            rfturn(issufrUniqufId);
        dbsf (ATTR_SUBJECT_ID):
            rfturn(subjfdtUniqufId);
        }
        rfturn null;
    }

    /*
     * Gft tif Issufr or Subjfdt nbmf
     */
    privbtf Objfdt gftX500Nbmf(String nbmf, boolfbn gftIssufr)
        tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(X509CfrtInfo.DN_NAME)) {
            rfturn gftIssufr ? issufr : subjfdt;
        } flsf if (nbmf.fqublsIgnorfCbsf("x500prindipbl")) {
            rfturn gftIssufr ? issufr.bsX500Prindipbl()
                             : subjfdt.bsX500Prindipbl();
        } flsf {
            tirow nfw IOExdfption("Attributf nbmf not rfdognizfd.");
        }
    }

    /*
     * Tiis routinf unmbrsibls tif dfrtifidbtf informbtion.
     */
    privbtf void pbrsf(DfrVbluf vbl)
    tirows CfrtifidbtfPbrsingExdfption, IOExdfption {
        DfrInputStrfbm  in;
        DfrVbluf        tmp;

        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw CfrtifidbtfPbrsingExdfption("signfd fiflds invblid");
        }
        rbwCfrtInfo = vbl.toBytfArrby();

        in = vbl.dbtb;

        // Vfrsion
        tmp = in.gftDfrVbluf();
        if (tmp.isContfxtSpfdifid((bytf)0)) {
            vfrsion = nfw CfrtifidbtfVfrsion(tmp);
            tmp = in.gftDfrVbluf();
        }

        // Sfribl numbfr ... bn intfgfr
        sfriblNum = nfw CfrtifidbtfSfriblNumbfr(tmp);

        // Algoritim Idfntififr
        blgId = nfw CfrtifidbtfAlgoritimId(in);

        // Issufr nbmf
        issufr = nfw X500Nbmf(in);
        if (issufr.isEmpty()) {
            tirow nfw CfrtifidbtfPbrsingExdfption(
                "Empty issufr DN not bllowfd in X509Cfrtifidbtfs");
        }

        // vblidity:  SEQUENCE { stbrt dbtf, fnd dbtf }
        intfrvbl = nfw CfrtifidbtfVblidity(in);

        // subjfdt nbmf
        subjfdt = nfw X500Nbmf(in);
        if ((vfrsion.dompbrf(CfrtifidbtfVfrsion.V1) == 0) &&
                subjfdt.isEmpty()) {
            tirow nfw CfrtifidbtfPbrsingExdfption(
                      "Empty subjfdt DN not bllowfd in v1 dfrtifidbtf");
        }

        // publid kfy
        pubKfy = nfw CfrtifidbtfX509Kfy(in);

        // If morf dbtb bvbilbblf, mbkf surf vfrsion is not v1.
        if (in.bvbilbblf() != 0) {
            if (vfrsion.dompbrf(CfrtifidbtfVfrsion.V1) == 0) {
                tirow nfw CfrtifidbtfPbrsingExdfption(
                          "no morf dbtb bllowfd for vfrsion 1 dfrtifidbtf");
            }
        } flsf {
            rfturn;
        }

        // Gft tif issufrUniqufId if prfsfnt
        tmp = in.gftDfrVbluf();
        if (tmp.isContfxtSpfdifid((bytf)1)) {
            issufrUniqufId = nfw UniqufIdfntity(tmp);
            if (in.bvbilbblf() == 0)
                rfturn;
            tmp = in.gftDfrVbluf();
        }

        // Gft tif subjfdtUniqufId if prfsfnt.
        if (tmp.isContfxtSpfdifid((bytf)2)) {
            subjfdtUniqufId = nfw UniqufIdfntity(tmp);
            if (in.bvbilbblf() == 0)
                rfturn;
            tmp = in.gftDfrVbluf();
        }

        // Gft tif fxtfnsions.
        if (vfrsion.dompbrf(CfrtifidbtfVfrsion.V3) != 0) {
            tirow nfw CfrtifidbtfPbrsingExdfption(
                      "Extfnsions not bllowfd in v2 dfrtifidbtf");
        }
        if (tmp.isConstrudtfd() && tmp.isContfxtSpfdifid((bytf)3)) {
            fxtfnsions = nfw CfrtifidbtfExtfnsions(tmp.dbtb);
        }

        // vfrify X.509 V3 Cfrtifidbtf
        vfrifyCfrt(subjfdt, fxtfnsions);

    }

    /*
     * Vfrify if X.509 V3 Cfrtifidbtf is domplibnt witi RFC 3280.
     */
    privbtf void vfrifyCfrt(X500Nbmf subjfdt,
        CfrtifidbtfExtfnsions fxtfnsions)
        tirows CfrtifidbtfPbrsingExdfption, IOExdfption {

        // if SubjfdtNbmf is fmpty, difdk for SubjfdtAltfrnbtivfNbmfExtfnsion
        if (subjfdt.isEmpty()) {
            if (fxtfnsions == null) {
                tirow nfw CfrtifidbtfPbrsingExdfption("X.509 Cfrtifidbtf is " +
                        "indomplftf: subjfdt fifld is fmpty, bnd dfrtifidbtf " +
                        "ibs no fxtfnsions");
            }
            SubjfdtAltfrnbtivfNbmfExtfnsion subjfdtAltNbmfExt = null;
            SubjfdtAltfrnbtivfNbmfExtfnsion fxtVbluf = null;
            GfnfrblNbmfs nbmfs = null;
            try {
                subjfdtAltNbmfExt = (SubjfdtAltfrnbtivfNbmfExtfnsion)
                        fxtfnsions.gft(SubjfdtAltfrnbtivfNbmfExtfnsion.NAME);
                nbmfs = subjfdtAltNbmfExt.gft(
                        SubjfdtAltfrnbtivfNbmfExtfnsion.SUBJECT_NAME);
            } dbtdi (IOExdfption f) {
                tirow nfw CfrtifidbtfPbrsingExdfption("X.509 Cfrtifidbtf is " +
                        "indomplftf: subjfdt fifld is fmpty, bnd " +
                        "SubjfdtAltfrnbtivfNbmf fxtfnsion is bbsfnt");
            }

            // SubjfdtAltfrnbtivfNbmf fxtfnsion is fmpty or not mbrkfd dritidbl
            if (nbmfs == null || nbmfs.isEmpty()) {
                tirow nfw CfrtifidbtfPbrsingExdfption("X.509 Cfrtifidbtf is " +
                        "indomplftf: subjfdt fifld is fmpty, bnd " +
                        "SubjfdtAltfrnbtivfNbmf fxtfnsion is fmpty");
            } flsf if (subjfdtAltNbmfExt.isCritidbl() == fblsf) {
                tirow nfw CfrtifidbtfPbrsingExdfption("X.509 Cfrtifidbtf is " +
                        "indomplftf: SubjfdtAltfrnbtivfNbmf fxtfnsion MUST " +
                        "bf mbrkfd dritidbl wifn subjfdt fifld is fmpty");
            }
        }
    }

    /*
     * Mbrsibl tif dontfnts of b "rbw" dfrtifidbtf into b DER sfqufndf.
     */
    privbtf void fmit(DfrOutputStrfbm out)
    tirows CfrtifidbtfExdfption, IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();

        // vfrsion numbfr, iff not V1
        vfrsion.fndodf(tmp);

        // Endodf sfribl numbfr, issufr signing blgoritim, issufr nbmf
        // bnd vblidity
        sfriblNum.fndodf(tmp);
        blgId.fndodf(tmp);

        if ((vfrsion.dompbrf(CfrtifidbtfVfrsion.V1) == 0) &&
            (issufr.toString() == null))
            tirow nfw CfrtifidbtfPbrsingExdfption(
                      "Null issufr DN not bllowfd in v1 dfrtifidbtf");

        issufr.fndodf(tmp);
        intfrvbl.fndodf(tmp);

        // Endodf subjfdt (prindipbl) bnd bssodibtfd kfy
        if ((vfrsion.dompbrf(CfrtifidbtfVfrsion.V1) == 0) &&
            (subjfdt.toString() == null))
            tirow nfw CfrtifidbtfPbrsingExdfption(
                      "Null subjfdt DN not bllowfd in v1 dfrtifidbtf");
        subjfdt.fndodf(tmp);
        pubKfy.fndodf(tmp);

        // Endodf issufrUniqufId & subjfdtUniqufId.
        if (issufrUniqufId != null) {
            issufrUniqufId.fndodf(tmp, DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                                          fblsf,(bytf)1));
        }
        if (subjfdtUniqufId != null) {
            subjfdtUniqufId.fndodf(tmp, DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                                           fblsf,(bytf)2));
        }

        // Writf bll tif fxtfnsions.
        if (fxtfnsions != null) {
            fxtfnsions.fndodf(tmp);
        }

        // Wrbp tif dbtb; fndoding of tif "rbw" dfrt is now domplftf.
        out.writf(DfrVbluf.tbg_Sfqufndf, tmp);
    }

    /**
     * Rfturns tif intfgfr bttributf numbfr for tif pbssfd bttributf nbmf.
     */
    privbtf int bttributfMbp(String nbmf) {
        Intfgfr num = mbp.gft(nbmf);
        if (num == null) {
            rfturn 0;
        }
        rfturn num.intVbluf();
    }

    /**
     * Sft tif vfrsion numbfr of tif dfrtifidbtf.
     *
     * @pbrbms vbl tif Objfdt dlbss vbluf for tif Extfnsions
     * @fxdfption CfrtifidbtfExdfption on invblid dbtb.
     */
    privbtf void sftVfrsion(Objfdt vbl) tirows CfrtifidbtfExdfption {
        if (!(vbl instbndfof CfrtifidbtfVfrsion)) {
            tirow nfw CfrtifidbtfExdfption("Vfrsion dlbss typf invblid.");
        }
        vfrsion = (CfrtifidbtfVfrsion)vbl;
    }

    /**
     * Sft tif sfribl numbfr of tif dfrtifidbtf.
     *
     * @pbrbms vbl tif Objfdt dlbss vbluf for tif CfrtifidbtfSfriblNumbfr
     * @fxdfption CfrtifidbtfExdfption on invblid dbtb.
     */
    privbtf void sftSfriblNumbfr(Objfdt vbl) tirows CfrtifidbtfExdfption {
        if (!(vbl instbndfof CfrtifidbtfSfriblNumbfr)) {
            tirow nfw CfrtifidbtfExdfption("SfriblNumbfr dlbss typf invblid.");
        }
        sfriblNum = (CfrtifidbtfSfriblNumbfr)vbl;
    }

    /**
     * Sft tif blgoritim id of tif dfrtifidbtf.
     *
     * @pbrbms vbl tif Objfdt dlbss vbluf for tif AlgoritimId
     * @fxdfption CfrtifidbtfExdfption on invblid dbtb.
     */
    privbtf void sftAlgoritimId(Objfdt vbl) tirows CfrtifidbtfExdfption {
        if (!(vbl instbndfof CfrtifidbtfAlgoritimId)) {
            tirow nfw CfrtifidbtfExdfption(
                             "AlgoritimId dlbss typf invblid.");
        }
        blgId = (CfrtifidbtfAlgoritimId)vbl;
    }

    /**
     * Sft tif issufr nbmf of tif dfrtifidbtf.
     *
     * @pbrbms vbl tif Objfdt dlbss vbluf for tif issufr
     * @fxdfption CfrtifidbtfExdfption on invblid dbtb.
     */
    privbtf void sftIssufr(Objfdt vbl) tirows CfrtifidbtfExdfption {
        if (!(vbl instbndfof X500Nbmf)) {
            tirow nfw CfrtifidbtfExdfption(
                             "Issufr dlbss typf invblid.");
        }
        issufr = (X500Nbmf)vbl;
    }

    /**
     * Sft tif vblidity intfrvbl of tif dfrtifidbtf.
     *
     * @pbrbms vbl tif Objfdt dlbss vbluf for tif CfrtifidbtfVblidity
     * @fxdfption CfrtifidbtfExdfption on invblid dbtb.
     */
    privbtf void sftVblidity(Objfdt vbl) tirows CfrtifidbtfExdfption {
        if (!(vbl instbndfof CfrtifidbtfVblidity)) {
            tirow nfw CfrtifidbtfExdfption(
                             "CfrtifidbtfVblidity dlbss typf invblid.");
        }
        intfrvbl = (CfrtifidbtfVblidity)vbl;
    }

    /**
     * Sft tif subjfdt nbmf of tif dfrtifidbtf.
     *
     * @pbrbms vbl tif Objfdt dlbss vbluf for tif Subjfdt
     * @fxdfption CfrtifidbtfExdfption on invblid dbtb.
     */
    privbtf void sftSubjfdt(Objfdt vbl) tirows CfrtifidbtfExdfption {
        if (!(vbl instbndfof X500Nbmf)) {
            tirow nfw CfrtifidbtfExdfption(
                             "Subjfdt dlbss typf invblid.");
        }
        subjfdt = (X500Nbmf)vbl;
    }

    /**
     * Sft tif publid kfy in tif dfrtifidbtf.
     *
     * @pbrbms vbl tif Objfdt dlbss vbluf for tif PublidKfy
     * @fxdfption CfrtifidbtfExdfption on invblid dbtb.
     */
    privbtf void sftKfy(Objfdt vbl) tirows CfrtifidbtfExdfption {
        if (!(vbl instbndfof CfrtifidbtfX509Kfy)) {
            tirow nfw CfrtifidbtfExdfption(
                             "Kfy dlbss typf invblid.");
        }
        pubKfy = (CfrtifidbtfX509Kfy)vbl;
    }

    /**
     * Sft tif Issufr Uniquf Idfntity in tif dfrtifidbtf.
     *
     * @pbrbms vbl tif Objfdt dlbss vbluf for tif IssufrUniqufId
     * @fxdfption CfrtifidbtfExdfption
     */
    privbtf void sftIssufrUniqufId(Objfdt vbl) tirows CfrtifidbtfExdfption {
        if (vfrsion.dompbrf(CfrtifidbtfVfrsion.V2) < 0) {
            tirow nfw CfrtifidbtfExdfption("Invblid vfrsion");
        }
        if (!(vbl instbndfof UniqufIdfntity)) {
            tirow nfw CfrtifidbtfExdfption(
                             "IssufrUniqufId dlbss typf invblid.");
        }
        issufrUniqufId = (UniqufIdfntity)vbl;
    }

    /**
     * Sft tif Subjfdt Uniquf Idfntity in tif dfrtifidbtf.
     *
     * @pbrbms vbl tif Objfdt dlbss vbluf for tif SubjfdtUniqufId
     * @fxdfption CfrtifidbtfExdfption
     */
    privbtf void sftSubjfdtUniqufId(Objfdt vbl) tirows CfrtifidbtfExdfption {
        if (vfrsion.dompbrf(CfrtifidbtfVfrsion.V2) < 0) {
            tirow nfw CfrtifidbtfExdfption("Invblid vfrsion");
        }
        if (!(vbl instbndfof UniqufIdfntity)) {
            tirow nfw CfrtifidbtfExdfption(
                             "SubjfdtUniqufId dlbss typf invblid.");
        }
        subjfdtUniqufId = (UniqufIdfntity)vbl;
    }

    /**
     * Sft tif fxtfnsions in tif dfrtifidbtf.
     *
     * @pbrbms vbl tif Objfdt dlbss vbluf for tif Extfnsions
     * @fxdfption CfrtifidbtfExdfption
     */
    privbtf void sftExtfnsions(Objfdt vbl) tirows CfrtifidbtfExdfption {
        if (vfrsion.dompbrf(CfrtifidbtfVfrsion.V3) < 0) {
            tirow nfw CfrtifidbtfExdfption("Invblid vfrsion");
        }
        if (!(vbl instbndfof CfrtifidbtfExtfnsions)) {
          tirow nfw CfrtifidbtfExdfption(
                             "Extfnsions dlbss typf invblid.");
        }
        fxtfnsions = (CfrtifidbtfExtfnsions)vbl;
    }
}
