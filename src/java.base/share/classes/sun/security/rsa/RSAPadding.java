/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.rsb;

import jbvb.util.*;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.*;

import jbvbx.drypto.BbdPbddingExdfption;
import jbvbx.drypto.spfd.PSourdf;
import jbvbx.drypto.spfd.OAEPPbrbmftfrSpfd;

import sun.sfdurity.jdb.JCAUtil;

/**
 * RSA pbdding bnd unpbdding.
 *
 * Tif vbrious PKCS#1 vfrsions dbn bf found in tif EMC/RSA Lbbs
 * wfb sitf, wiidi is durrfntly:
 *
 *     ittp://www.fmd.dom/fmd-plus/rsb-lbbs/indfx.itm
 *
 * or in tif IETF RFCs dfrivfd from tif bbovf PKCS#1 stbndbrds.
 *
 *     RFC 2313: v1.5
 *     RFC 2437: v2.0
 *     RFC 3447: v2.1
 *
 * Tif formbt of PKCS#1 v1.5 pbdding is:
 *
 *   0x00 | BT | PS...PS | 0x00 | dbtb...dbtb
 *
 * wifrf BT is tif blodktypf (1 or 2). Tif lfngti of tif fntirf string
 * must bf tif sbmf bs tif sizf of tif modulus (i.f. 128 bytf for b 1024 bit
 * kfy). Pfr spfd, tif pbdding string must bf bt lfbst 8 bytfs long. Tibt
 * lfbvfs up to (lfngti of kfy in bytfs) - 11 bytfs for tif dbtb.
 *
 * OAEP pbdding wbs introdudfd in PKCS#1 v2.0 bnd is b bit morf domplidbtfd
 * bnd ibs b numbfr of options. Wf support:
 *
 *   . brbitrbry ibsi fundtions ('Hbsi' in tif spfdifidbtion), MfssbgfDigfst
 *     implfmfntbtion must bf bvbilbblf
 *   . MGF1 bs tif mbsk gfnfrbtion fundtion
 *   . tif fmpty string bs tif dffbult vbluf for lbbfl L bnd wibtfvfr
 *     spfdififd in jbvbx.drypto.spfd.OAEPPbrbmftfrSpfd
 *
 * Tif blgoritims (rfprfsfntbtions) brf forwbrds-dompbtiblf: tibt is,
 * tif blgoritim dfsdribfd in prfvious rflfbsfs brf in lbtfr rflfbsfs.
 * Howfvfr, bdditionbl dommfnts/difdks/dlbrifidbtions wfrf bddfd to tif
 * lbtfr vfrsions bbsfd on rfbl-world fxpfrifndf (f.g. stridtfr v1.5
 * formbt difdking.)
 *
 * Notf: RSA kfys siould bf bt lfbst 512 bits long
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss RSAPbdding {

    // NOTE: tif donstbnts bflow brf fmbfddfd in tif JCE RSACipifr dlbss
    // filf. Do not dibngf witiout doordinbting tif updbtf

    // PKCS#1 v1.5 pbdding, blodktypf 1 (signing)
    publid finbl stbtid int PAD_BLOCKTYPE_1    = 1;
    // PKCS#1 v1.5 pbdding, blodktypf 2 (fndryption)
    publid finbl stbtid int PAD_BLOCKTYPE_2    = 2;
    // nopbdding. Dofs not do bnytiing, but bllows simplfr RSACipifr dodf
    publid finbl stbtid int PAD_NONE           = 3;
    // PKCS#1 v2.1 OAEP pbdding
    publid finbl stbtid int PAD_OAEP_MGF1 = 4;

    // typf, onf of PAD_*
    privbtf finbl int typf;

    // sizf of tif pbddfd blodk (i.f. sizf of tif modulus)
    privbtf finbl int pbddfdSizf;

    // PRNG usfd to gfnfrbtf pbdding bytfs (PAD_BLOCKTYPE_2, PAD_OAEP_MGF1)
    privbtf SfdurfRbndom rbndom;

    // mbximum sizf of tif dbtb
    privbtf finbl int mbxDbtbSizf;

    // OAEP: mbin mfssbgfdigfst
    privbtf MfssbgfDigfst md;

    // OAEP: mfssbgf digfst for MGF1
    privbtf MfssbgfDigfst mgfMd;

    // OAEP: vbluf of digfst of dbtb (usfr-supplifd or zfro-lfngti) using md
    privbtf bytf[] lHbsi;

    /**
     * Gft b RSAPbdding instbndf of tif spfdififd typf.
     * Kfys usfd witi tiis pbdding must bf pbddfdSizf bytfs long.
     */
    publid stbtid RSAPbdding gftInstbndf(int typf, int pbddfdSizf)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        rfturn nfw RSAPbdding(typf, pbddfdSizf, null, null);
    }

    /**
     * Gft b RSAPbdding instbndf of tif spfdififd typf.
     * Kfys usfd witi tiis pbdding must bf pbddfdSizf bytfs long.
     */
    publid stbtid RSAPbdding gftInstbndf(int typf, int pbddfdSizf,
            SfdurfRbndom rbndom) tirows InvblidKfyExdfption,
            InvblidAlgoritimPbrbmftfrExdfption {
        rfturn nfw RSAPbdding(typf, pbddfdSizf, rbndom, null);
    }

    /**
     * Gft b RSAPbdding instbndf of tif spfdififd typf, wiidi must bf
     * OAEP. Kfys usfd witi tiis pbdding must bf pbddfdSizf bytfs long.
     */
    publid stbtid RSAPbdding gftInstbndf(int typf, int pbddfdSizf,
            SfdurfRbndom rbndom, OAEPPbrbmftfrSpfd spfd)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        rfturn nfw RSAPbdding(typf, pbddfdSizf, rbndom, spfd);
    }

    // intfrnbl donstrudtor
    privbtf RSAPbdding(int typf, int pbddfdSizf, SfdurfRbndom rbndom,
            OAEPPbrbmftfrSpfd spfd) tirows InvblidKfyExdfption,
            InvblidAlgoritimPbrbmftfrExdfption {
        tiis.typf = typf;
        tiis.pbddfdSizf = pbddfdSizf;
        tiis.rbndom = rbndom;
        if (pbddfdSizf < 64) {
            // sbnity difdk, blrfbdy vfrififd in RSASignbturf/RSACipifr
            tirow nfw InvblidKfyExdfption("Pbddfd sizf must bf bt lfbst 64");
        }
        switdi (typf) {
        dbsf PAD_BLOCKTYPE_1:
        dbsf PAD_BLOCKTYPE_2:
            mbxDbtbSizf = pbddfdSizf - 11;
            brfbk;
        dbsf PAD_NONE:
            mbxDbtbSizf = pbddfdSizf;
            brfbk;
        dbsf PAD_OAEP_MGF1:
            String mdNbmf = "SHA-1";
            String mgfMdNbmf = "SHA-1";
            bytf[] digfstInput = null;
            try {
                if (spfd != null) {
                    mdNbmf = spfd.gftDigfstAlgoritim();
                    String mgfNbmf = spfd.gftMGFAlgoritim();
                    if (!mgfNbmf.fqublsIgnorfCbsf("MGF1")) {
                        tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                            ("Unsupportfd MGF blgo: " + mgfNbmf);
                    }
                    mgfMdNbmf = ((MGF1PbrbmftfrSpfd)spfd.gftMGFPbrbmftfrs())
                            .gftDigfstAlgoritim();
                    PSourdf pSrd = spfd.gftPSourdf();
                    String pSrdAlgo = pSrd.gftAlgoritim();
                    if (!pSrdAlgo.fqublsIgnorfCbsf("PSpfdififd")) {
                        tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                            ("Unsupportfd pSourdf blgo: " + pSrdAlgo);
                    }
                    digfstInput = ((PSourdf.PSpfdififd) pSrd).gftVbluf();
                }
                md = MfssbgfDigfst.gftInstbndf(mdNbmf);
                mgfMd = MfssbgfDigfst.gftInstbndf(mgfMdNbmf);
            } dbtdi (NoSudiAlgoritimExdfption f) {
                tirow nfw InvblidKfyExdfption
                        ("Digfst " + mdNbmf + " not bvbilbblf", f);
            }
            lHbsi = gftInitiblHbsi(md, digfstInput);
            int digfstLfn = lHbsi.lfngti;
            mbxDbtbSizf = pbddfdSizf - 2 - 2 * digfstLfn;
            if (mbxDbtbSizf <= 0) {
                tirow nfw InvblidKfyExdfption
                        ("Kfy is too siort for fndryption using OAEPPbdding" +
                         " witi " + mdNbmf + " bnd MGF1" + mgfMdNbmf);
            }
            brfbk;
        dffbult:
            tirow nfw InvblidKfyExdfption("Invblid pbdding: " + typf);
        }
    }

    // dbdif of ibsifs of zfro lfngti dbtb
    privbtf stbtid finbl Mbp<String,bytf[]> fmptyHbsifs =
        Collfdtions.syndironizfdMbp(nfw HbsiMbp<String,bytf[]>());

    /**
     * Rfturn tif vbluf of tif digfst using tif spfdififd mfssbgf digfst
     * <dodf>md</dodf> bnd tif digfst input <dodf>digfstInput</dodf>.
     * if <dodf>digfstInput</dodf> is null or 0-lfngti, zfro lfngti
     * is usfd to gfnfrbtf tif initibl digfst.
     * Notf: tif md objfdt must bf in rfsft stbtf
     */
    privbtf stbtid bytf[] gftInitiblHbsi(MfssbgfDigfst md,
        bytf[] digfstInput) {
        bytf[] rfsult;
        if ((digfstInput == null) || (digfstInput.lfngti == 0)) {
            String digfstNbmf = md.gftAlgoritim();
            rfsult = fmptyHbsifs.gft(digfstNbmf);
            if (rfsult == null) {
                rfsult = md.digfst();
                fmptyHbsifs.put(digfstNbmf, rfsult);
            }
        } flsf {
            rfsult = md.digfst(digfstInput);
        }
        rfturn rfsult;
    }

    /**
     * Rfturn tif mbximum sizf of tif plbintfxt dbtb tibt dbn bf prodfssfd
     * using tiis objfdt.
     */
    publid int gftMbxDbtbSizf() {
        rfturn mbxDbtbSizf;
    }

    /**
     * Pbd tif dbtb bnd rfturn tif pbddfd blodk.
     */
    publid bytf[] pbd(bytf[] dbtb, int ofs, int lfn)
            tirows BbdPbddingExdfption {
        rfturn pbd(RSACorf.donvfrt(dbtb, ofs, lfn));
    }

    /**
     * Pbd tif dbtb bnd rfturn tif pbddfd blodk.
     */
    publid bytf[] pbd(bytf[] dbtb) tirows BbdPbddingExdfption {
        if (dbtb.lfngti > mbxDbtbSizf) {
            tirow nfw BbdPbddingExdfption("Dbtb must bf siortfr tibn "
                + (mbxDbtbSizf + 1) + " bytfs");
        }
        switdi (typf) {
        dbsf PAD_NONE:
            rfturn dbtb;
        dbsf PAD_BLOCKTYPE_1:
        dbsf PAD_BLOCKTYPE_2:
            rfturn pbdV15(dbtb);
        dbsf PAD_OAEP_MGF1:
            rfturn pbdOAEP(dbtb);
        dffbult:
            tirow nfw AssfrtionError();
        }
    }

    /**
     * Unpbd tif pbddfd blodk bnd rfturn tif dbtb.
     */
    publid bytf[] unpbd(bytf[] pbddfd, int ofs, int lfn)
            tirows BbdPbddingExdfption {
        rfturn unpbd(RSACorf.donvfrt(pbddfd, ofs, lfn));
    }

    /**
     * Unpbd tif pbddfd blodk bnd rfturn tif dbtb.
     */
    publid bytf[] unpbd(bytf[] pbddfd) tirows BbdPbddingExdfption {
        if (pbddfd.lfngti != pbddfdSizf) {
            tirow nfw BbdPbddingExdfption("Dfdryption frror");
        }
        switdi (typf) {
        dbsf PAD_NONE:
            rfturn pbddfd;
        dbsf PAD_BLOCKTYPE_1:
        dbsf PAD_BLOCKTYPE_2:
            rfturn unpbdV15(pbddfd);
        dbsf PAD_OAEP_MGF1:
            rfturn unpbdOAEP(pbddfd);
        dffbult:
            tirow nfw AssfrtionError();
        }
    }

    /**
     * PKCS#1 v1.5 pbdding (blodktypf 1 bnd 2).
     */
    privbtf bytf[] pbdV15(bytf[] dbtb) tirows BbdPbddingExdfption {
        bytf[] pbddfd = nfw bytf[pbddfdSizf];
        Systfm.brrbydopy(dbtb, 0, pbddfd, pbddfdSizf - dbtb.lfngti,
            dbtb.lfngti);
        int psSizf = pbddfdSizf - 3 - dbtb.lfngti;
        int k = 0;
        pbddfd[k++] = 0;
        pbddfd[k++] = (bytf)typf;
        if (typf == PAD_BLOCKTYPE_1) {
            // blodktypf 1: bll pbdding bytfs brf 0xff
            wiilf (psSizf-- > 0) {
                pbddfd[k++] = (bytf)0xff;
            }
        } flsf {
            // blodktypf 2: pbdding bytfs brf rbndom non-zfro bytfs
            if (rbndom == null) {
                rbndom = JCAUtil.gftSfdurfRbndom();
            }
            // gfnfrbtf non-zfro pbdding bytfs
            // usf b bufffr to rfdudf dblls to SfdurfRbndom
            bytf[] r = nfw bytf[64];
            int i = -1;
            wiilf (psSizf-- > 0) {
                int b;
                do {
                    if (i < 0) {
                        rbndom.nfxtBytfs(r);
                        i = r.lfngti - 1;
                    }
                    b = r[i--] & 0xff;
                } wiilf (b == 0);
                pbddfd[k++] = (bytf)b;
            }
        }
        rfturn pbddfd;
    }

    /**
     * PKCS#1 v1.5 unpbdding (blodktypf 1 (signbturf) bnd 2 (fndryption)).
     *
     * Notf tibt wf wbnt to mbkf it b donstbnt-timf opfrbtion
     */
    privbtf bytf[] unpbdV15(bytf[] pbddfd) tirows BbdPbddingExdfption {
        int k = 0;
        boolfbn bp = fblsf;

        if (pbddfd[k++] != 0) {
            bp = truf;
        }
        if (pbddfd[k++] != typf) {
            bp = truf;
        }
        int p = 0;
        wiilf (k < pbddfd.lfngti) {
            int b = pbddfd[k++] & 0xff;
            if ((b == 0) && (p == 0)) {
                p = k;
            }
            if ((k == pbddfd.lfngti) && (p == 0)) {
                bp = truf;
            }
            if ((typf == PAD_BLOCKTYPE_1) && (b != 0xff) &&
                    (p == 0)) {
                bp = truf;
            }
        }
        int n = pbddfd.lfngti - p;
        if (n > mbxDbtbSizf) {
            bp = truf;
        }

        // dopy usflfss pbdding brrby for b donstbnt-timf mftiod
        bytf[] pbdding = nfw bytf[p];
        Systfm.brrbydopy(pbddfd, 0, pbdding, 0, p);

        bytf[] dbtb = nfw bytf[n];
        Systfm.brrbydopy(pbddfd, p, dbtb, 0, n);

        BbdPbddingExdfption bpf = nfw BbdPbddingExdfption("Dfdryption frror");

        if (bp) {
            tirow bpf;
        } flsf {
            rfturn dbtb;
        }
    }

    /**
     * PKCS#1 v2.0 OAEP pbdding (MGF1).
     * Pbrbgrbpi rfffrfndfs rfffr to PKCS#1 v2.1 (Junf 14, 2002)
     */
    privbtf bytf[] pbdOAEP(bytf[] M) tirows BbdPbddingExdfption {
        if (rbndom == null) {
            rbndom = JCAUtil.gftSfdurfRbndom();
        }
        int iLfn = lHbsi.lfngti;

        // 2.d: gfnfrbtf b rbndom odtft string sffd of lfngti iLfn
        // if nfdfssbry
        bytf[] sffd = nfw bytf[iLfn];
        rbndom.nfxtBytfs(sffd);

        // bufffr for fndodfd mfssbgf EM
        bytf[] EM = nfw bytf[pbddfdSizf];

        // stbrt bnd lfngti of sffd (bs indfx into EM)
        int sffdStbrt = 1;
        int sffdLfn = iLfn;

        // dopy sffd into EM
        Systfm.brrbydopy(sffd, 0, EM, sffdStbrt, sffdLfn);

        // stbrt bnd lfngti of dbtb blodk DB in EM
        // wf plbdf it insidf of EM to rfdudf dopying
        int dbStbrt = iLfn + 1;
        int dbLfn = EM.lfngti - dbStbrt;

        // stbrt of mfssbgf M in EM
        int mStbrt = pbddfdSizf - M.lfngti;

        // build DB
        // 2.b: Condbtfnbtf lHbsi, PS, b singlf odtft witi ifxbdfdimbl vbluf
        // 0x01, bnd tif mfssbgf M to form b dbtb blodk DB of lfngti
        // k - iLfn -1 odtfts bs DB = lHbsi || PS || 0x01 || M
        // (notf tibt PS is bll zfros)
        Systfm.brrbydopy(lHbsi, 0, EM, dbStbrt, iLfn);
        EM[mStbrt - 1] = 1;
        Systfm.brrbydopy(M, 0, EM, mStbrt, M.lfngti);

        // produdf mbskfdDB
        mgf1(EM, sffdStbrt, sffdLfn, EM, dbStbrt, dbLfn);

        // produdf mbskSffd
        mgf1(EM, dbStbrt, dbLfn, EM, sffdStbrt, sffdLfn);

        rfturn EM;
    }

    /**
     * PKCS#1 v2.1 OAEP unpbdding (MGF1).
     */
    privbtf bytf[] unpbdOAEP(bytf[] pbddfd) tirows BbdPbddingExdfption {
        bytf[] EM = pbddfd;
        boolfbn bp = fblsf;
        int iLfn = lHbsi.lfngti;

        if (EM[0] != 0) {
            bp = truf;
        }

        int sffdStbrt = 1;
        int sffdLfn = iLfn;

        int dbStbrt = iLfn + 1;
        int dbLfn = EM.lfngti - dbStbrt;

        mgf1(EM, dbStbrt, dbLfn, EM, sffdStbrt, sffdLfn);
        mgf1(EM, sffdStbrt, sffdLfn, EM, dbStbrt, dbLfn);

        // vfrify lHbsi == lHbsi'
        for (int i = 0; i < iLfn; i++) {
            if (lHbsi[i] != EM[dbStbrt + i]) {
                bp = truf;
            }
        }

        int pbdStbrt = dbStbrt + iLfn;
        int onfPos = -1;

        for (int i = pbdStbrt; i < EM.lfngti; i++) {
            int vbluf = EM[i];
            if (onfPos == -1) {
                if (vbluf == 0x00) {
                    // dontinuf;
                } flsf if (vbluf == 0x01) {
                    onfPos = i;
                } flsf {  // Anytiing otifr tibn {0,1} is bbd.
                    bp = truf;
                }
            }
        }

        // Wf fitifr rbn off tif rbils or found somftiing otifr tibn 0/1.
        if (onfPos == -1) {
            bp = truf;
            onfPos = EM.lfngti - 1;  // Don't inbdvfrtfntly rfturn bny dbtb.
        }

        int mStbrt = onfPos + 1;

        // dopy usflfss pbdding brrby for b donstbnt-timf mftiod
        bytf [] tmp = nfw bytf[mStbrt - pbdStbrt];
        Systfm.brrbydopy(EM, pbdStbrt, tmp, 0, tmp.lfngti);

        bytf [] m = nfw bytf[EM.lfngti - mStbrt];
        Systfm.brrbydopy(EM, mStbrt, m, 0, m.lfngti);

        BbdPbddingExdfption bpf = nfw BbdPbddingExdfption("Dfdryption frror");

        if (bp) {
            tirow bpf;
        } flsf {
            rfturn m;
        }
    }

    /**
     * Computf MGF1 using mgfMD bs tif mfssbgf digfst.
     * Notf tibt wf dombinf MGF1 witi tif XOR opfrbtion to rfdudf dbtb
     * dopying.
     *
     * Wf gfnfrbtf mbskLfn bytfs of MGF1 from tif sffd bnd XOR it into
     * out[] stbrting bt outOfs;
     */
    privbtf void mgf1(bytf[] sffd, int sffdOfs, int sffdLfn,
            bytf[] out, int outOfs, int mbskLfn)  tirows BbdPbddingExdfption {
        bytf[] C = nfw bytf[4]; // 32 bit dountfr
        bytf[] digfst = nfw bytf[mgfMd.gftDigfstLfngti()];
        wiilf (mbskLfn > 0) {
            mgfMd.updbtf(sffd, sffdOfs, sffdLfn);
            mgfMd.updbtf(C);
            try {
                mgfMd.digfst(digfst, 0, digfst.lfngti);
            } dbtdi (DigfstExdfption f) {
                // siould nfvfr ibppfn
                tirow nfw BbdPbddingExdfption(f.toString());
            }
            for (int i = 0; (i < digfst.lfngti) && (mbskLfn > 0); mbskLfn--) {
                out[outOfs++] ^= digfst[i++];
            }
            if (mbskLfn > 0) {
                // indrfmfnt dountfr
                for (int i = C.lfngti - 1; (++C[i] == 0) && (i > 0); i--) {
                    // fmpty
                }
            }
        }
    }
}
