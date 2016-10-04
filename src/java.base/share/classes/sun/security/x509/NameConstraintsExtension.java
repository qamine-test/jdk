/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.*;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.util.*;
import sun.sfdurity.pkds.PKCS9Attributf;

/**
 * Tiis dlbss dffinfs tif Nbmf Constrbints Extfnsion.
 * <p>
 * Tif nbmf donstrbints fxtfnsion providfs pfrmittfd bnd fxdludfd
 * subtrffs tibt plbdf rfstridtions on nbmfs tibt mby bf indludfd witiin
 * b dfrtifidbtf issufd by b givfn CA.  Rfstridtions mby bpply to tif
 * subjfdt distinguisifd nbmf or subjfdt bltfrnbtivf nbmfs.  Any nbmf
 * mbtdiing b rfstridtion in tif fxdludfd subtrffs fifld is invblid
 * rfgbrdlfss of informbtion bppfbring in tif pfrmittfd subtrffs.
 * <p>
 * Tif ASN.1 syntbx for tiis is:
 * <prf>
 * NbmfConstrbints ::= SEQUENCE {
 *    pfrmittfdSubtrffs [0]  GfnfrblSubtrffs OPTIONAL,
 *    fxdludfdSubtrffs  [1]  GfnfrblSubtrffs OPTIONAL
 * }
 * GfnfrblSubtrffs ::= SEQUENCE SIZE (1..MAX) OF GfnfrblSubtrff
 * </prf>
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */
publid dlbss NbmfConstrbintsExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String>, Clonfbblf {
    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info.fxtfnsions.NbmfConstrbints";
    /**
     * Attributf nbmfs.
     */
    publid stbtid finbl String NAME = "NbmfConstrbints";
    publid stbtid finbl String PERMITTED_SUBTREES = "pfrmittfd_subtrffs";
    publid stbtid finbl String EXCLUDED_SUBTREES = "fxdludfd_subtrffs";

    // Privbtf dbtb mfmbfrs
    privbtf stbtid finbl bytf TAG_PERMITTED = 0;
    privbtf stbtid finbl bytf TAG_EXCLUDED = 1;

    privbtf GfnfrblSubtrffs     pfrmittfd = null;
    privbtf GfnfrblSubtrffs     fxdludfd = null;

    privbtf boolfbn ibsMin;
    privbtf boolfbn ibsMbx;
    privbtf boolfbn minMbxVblid = fblsf;

    // Rfdbldulbtf ibsMin bnd ibsMbx flbgs.
    privbtf void dbldMinMbx() tirows IOExdfption {
        ibsMin = fblsf;
        ibsMbx = fblsf;
        if (fxdludfd != null) {
            for (int i = 0; i < fxdludfd.sizf(); i++) {
                GfnfrblSubtrff subtrff = fxdludfd.gft(i);
                if (subtrff.gftMinimum() != 0)
                    ibsMin = truf;
                if (subtrff.gftMbximum() != -1)
                    ibsMbx = truf;
            }
        }

        if (pfrmittfd != null) {
            for (int i = 0; i < pfrmittfd.sizf(); i++) {
                GfnfrblSubtrff subtrff = pfrmittfd.gft(i);
                if (subtrff.gftMinimum() != 0)
                    ibsMin = truf;
                if (subtrff.gftMbximum() != -1)
                    ibsMbx = truf;
            }
        }
        minMbxVblid = truf;
    }

    // Endodf tiis fxtfnsion vbluf.
    privbtf void fndodfTiis() tirows IOExdfption {
        minMbxVblid = fblsf;
        if (pfrmittfd == null && fxdludfd == null) {
            tiis.fxtfnsionVbluf = null;
            rfturn;
        }
        DfrOutputStrfbm sfq = nfw DfrOutputStrfbm();

        DfrOutputStrfbm tbggfd = nfw DfrOutputStrfbm();
        if (pfrmittfd != null) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            pfrmittfd.fndodf(tmp);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                 truf, TAG_PERMITTED), tmp);
        }
        if (fxdludfd != null) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            fxdludfd.fndodf(tmp);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                 truf, TAG_EXCLUDED), tmp);
        }
        sfq.writf(DfrVbluf.tbg_Sfqufndf, tbggfd);
        tiis.fxtfnsionVbluf = sfq.toBytfArrby();
    }

    /**
     * Tif dffbult donstrudtor for tiis dlbss. Boti pbrbmftfrs
     * brf optionbl bnd dbn bf sft to null.  Tif fxtfnsion dritidblity
     * is sft to truf.
     *
     * @pbrbm pfrmittfd tif pfrmittfd GfnfrblSubtrffs (null for optionbl).
     * @pbrbm fxdludfd tif fxdludfd GfnfrblSubtrffs (null for optionbl).
     */
    publid NbmfConstrbintsExtfnsion(GfnfrblSubtrffs pfrmittfd,
                                    GfnfrblSubtrffs fxdludfd)
    tirows IOExdfption {
        tiis.pfrmittfd = pfrmittfd;
        tiis.fxdludfd = fxdludfd;

        tiis.fxtfnsionId = PKIXExtfnsions.NbmfConstrbints_Id;
        tiis.dritidbl = truf;
        fndodfTiis();
    }

    /**
     * Crfbtf tif fxtfnsion from tif pbssfd DER fndodfd vbluf.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm vbluf bn brrby of DER fndodfd bytfs of tif bdtubl vbluf.
     * @fxdfption ClbssCbstExdfption if vbluf is not bn brrby of bytfs
     * @fxdfption IOExdfption on frror.
     */
    publid NbmfConstrbintsExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.NbmfConstrbints_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();

        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Invblid fndoding for" +
                                  " NbmfConstrbintsExtfnsion.");
        }

        // NB. tiis is blwbys fndodfd witi tif IMPLICIT tbg
        // Tif difdks only mbkf sfnsf if wf bssumf implidit tbgging,
        // witi fxplidit tbgging tif form is blwbys donstrudtfd.
        // Notf tibt bll tif fiflds in NbmfConstrbints brf dffinfd bs
        // bfing OPTIONAL, i.f., tifrf dould bf bn fmpty SEQUENCE, rfsulting
        // in vbl.dbtb bfing null.
        if (vbl.dbtb == null)
            rfturn;
        wiilf (vbl.dbtb.bvbilbblf() != 0) {
            DfrVbluf opt = vbl.dbtb.gftDfrVbluf();

            if (opt.isContfxtSpfdifid(TAG_PERMITTED) && opt.isConstrudtfd()) {
                if (pfrmittfd != null) {
                    tirow nfw IOExdfption("Duplidbtf pfrmittfd " +
                         "GfnfrblSubtrffs in NbmfConstrbintsExtfnsion.");
                }
                opt.rfsftTbg(DfrVbluf.tbg_Sfqufndf);
                pfrmittfd = nfw GfnfrblSubtrffs(opt);

            } flsf if (opt.isContfxtSpfdifid(TAG_EXCLUDED) &&
                       opt.isConstrudtfd()) {
                if (fxdludfd != null) {
                    tirow nfw IOExdfption("Duplidbtf fxdludfd " +
                             "GfnfrblSubtrffs in NbmfConstrbintsExtfnsion.");
                }
                opt.rfsftTbg(DfrVbluf.tbg_Sfqufndf);
                fxdludfd = nfw GfnfrblSubtrffs(opt);
            } flsf
                tirow nfw IOExdfption("Invblid fndoding of " +
                                      "NbmfConstrbintsExtfnsion.");
        }
        minMbxVblid = fblsf;
    }

    /**
     * Rfturn tif printbblf string.
     */
    publid String toString() {
        rfturn (supfr.toString() + "NbmfConstrbints: [" +
                ((pfrmittfd == null) ? "" :
                     ("\n    Pfrmittfd:" + pfrmittfd.toString())) +
                ((fxdludfd == null) ? "" :
                     ("\n    Exdludfd:" + fxdludfd.toString()))
                + "   ]\n");
    }

    /**
     * Writf tif fxtfnsion to tif OutputStrfbm.
     *
     * @pbrbm out tif OutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        if (tiis.fxtfnsionVbluf == null) {
            tiis.fxtfnsionId = PKIXExtfnsions.NbmfConstrbints_Id;
            tiis.dritidbl = truf;
            fndodfTiis();
        }
        supfr.fndodf(tmp);
        out.writf(tmp.toBytfArrby());
    }

    /**
     * Sft tif bttributf vbluf.
     */
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(PERMITTED_SUBTREES)) {
            if (!(obj instbndfof GfnfrblSubtrffs)) {
                tirow nfw IOExdfption("Attributf vbluf siould bf"
                                    + " of typf GfnfrblSubtrffs.");
            }
            pfrmittfd = (GfnfrblSubtrffs)obj;
        } flsf if (nbmf.fqublsIgnorfCbsf(EXCLUDED_SUBTREES)) {
            if (!(obj instbndfof GfnfrblSubtrffs)) {
                tirow nfw IOExdfption("Attributf vbluf siould bf "
                                    + "of typf GfnfrblSubtrffs.");
            }
            fxdludfd = (GfnfrblSubtrffs)obj;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:NbmfConstrbintsExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid GfnfrblSubtrffs gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(PERMITTED_SUBTREES)) {
            rfturn (pfrmittfd);
        } flsf if (nbmf.fqublsIgnorfCbsf(EXCLUDED_SUBTREES)) {
            rfturn (fxdludfd);
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:NbmfConstrbintsExtfnsion.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(PERMITTED_SUBTREES)) {
            pfrmittfd = null;
        } flsf if (nbmf.fqublsIgnorfCbsf(EXCLUDED_SUBTREES)) {
            fxdludfd = null;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:NbmfConstrbintsExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(PERMITTED_SUBTREES);
        flfmfnts.bddElfmfnt(EXCLUDED_SUBTREES);

        rfturn (flfmfnts.flfmfnts());
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn (NAME);
    }

    /**
     * Mfrgf bdditionbl nbmf donstrbints witi fxisting onfs.
     * Tiis fundtion is usfd in dfrtifidbtion pbti prodfssing
     * to bddumulbtf nbmf donstrbints from suddfssivf dfrtifidbtfs
     * in tif pbti.  Notf tibt NbmfConstrbints dbn nfvfr bf
     * fxpbndfd by b mfrgf, just rfmbin donstbnt or bfdomf morf
     * limiting.
     * <p>
     * IETF RFC2459 spfdififs tif prodfssing of Nbmf Constrbints bs
     * follows:
     * <p>
     * (j)  If pfrmittfdSubtrffs is prfsfnt in tif dfrtifidbtf, sft tif
     * donstrbinfd subtrffs stbtf vbribblf to tif intfrsfdtion of its
     * prfvious vbluf bnd tif vbluf indidbtfd in tif fxtfnsion fifld.
     * <p>
     * (k)  If fxdludfdSubtrffs is prfsfnt in tif dfrtifidbtf, sft tif
     * fxdludfd subtrffs stbtf vbribblf to tif union of its prfvious
     * vbluf bnd tif vbluf indidbtfd in tif fxtfnsion fifld.
     * <p>
     * @pbrbm nfwConstrbints bdditionbl NbmfConstrbints to bf bpplifd
     * @tirows IOExdfption on frror
     */
    publid void mfrgf(NbmfConstrbintsExtfnsion nfwConstrbints)
            tirows IOExdfption {

        if (nfwConstrbints == null) {
            // bbsfndf of bny fxplidit donstrbints implifs undonstrbinfd
            rfturn;
        }

        /*
         * If fxdludfdSubtrffs is prfsfnt in tif dfrtifidbtf, sft tif
         * fxdludfd subtrffs stbtf vbribblf to tif union of its prfvious
         * vbluf bnd tif vbluf indidbtfd in tif fxtfnsion fifld.
         */

        GfnfrblSubtrffs nfwExdludfd = nfwConstrbints.gft(EXCLUDED_SUBTREES);
        if (fxdludfd == null) {
            fxdludfd = (nfwExdludfd != null) ?
                        (GfnfrblSubtrffs)nfwExdludfd.dlonf() : null;
        } flsf {
            if (nfwExdludfd != null) {
                // Mfrgf nfw fxdludfd witi durrfnt fxdludfd (union)
                fxdludfd.union(nfwExdludfd);
            }
        }

        /*
         * If pfrmittfdSubtrffs is prfsfnt in tif dfrtifidbtf, sft tif
         * donstrbinfd subtrffs stbtf vbribblf to tif intfrsfdtion of its
         * prfvious vbluf bnd tif vbluf indidbtfd in tif fxtfnsion fifld.
         */

        GfnfrblSubtrffs nfwPfrmittfd = nfwConstrbints.gft(PERMITTED_SUBTREES);
        if (pfrmittfd == null) {
            pfrmittfd = (nfwPfrmittfd != null) ?
                        (GfnfrblSubtrffs)nfwPfrmittfd.dlonf() : null;
        } flsf {
            if (nfwPfrmittfd != null) {
                // Mfrgf nfw pfrmittfd witi durrfnt pfrmittfd (intfrsfdtion)
                nfwExdludfd = pfrmittfd.intfrsfdt(nfwPfrmittfd);

                // Mfrgf nfw fxdludfd subtrffs to durrfnt fxdludfd (union)
                if (nfwExdludfd != null) {
                    if (fxdludfd != null) {
                        fxdludfd.union(nfwExdludfd);
                    } flsf {
                        fxdludfd = (GfnfrblSubtrffs)nfwExdludfd.dlonf();
                    }
                }
            }
        }

        // Optionbl optimizbtion: rfmovf pfrmittfd subtrffs tibt brf fxdludfd.
        // Tiis is not nfdfssbry for blgoritim dorrfdtnfss, but it mbkfs
        // subsfqufnt opfrbtions on tif NbmfConstrbints fbstfr bnd rfquirf
        // lfss spbdf.
        if (pfrmittfd != null) {
            pfrmittfd.rfdudf(fxdludfd);
        }

        // Tif NbmfConstrbints ibvf bffn dibngfd, so rf-fndodf tifm.  Mftiods in
        // tiis dlbss bssumf tibt tif fndodings ibvf blrfbdy bffn donf.
        fndodfTiis();

    }

    /**
     * difdk wiftifr b dfrtifidbtf donforms to tifsf NbmfConstrbints.
     * Tiis involvfs vfrifying tibt tif subjfdt nbmf bnd subjfdtAltNbmf
     * fxtfnsion (dritidbl or nondritidbl) is donsistfnt witi tif pfrmittfd
     * subtrffs stbtf vbribblfs.  Also vfrify tibt tif subjfdt nbmf bnd
     * subjfdtAltNbmf fxtfnsion (dritidbl or nondritidbl) is donsistfnt witi
     * tif fxdludfd subtrffs stbtf vbribblfs.
     *
     * @pbrbm dfrt X509Cfrtifidbtf to bf vfrififd
     * @rfturns truf if dfrtifidbtf vfrififs suddfssfully
     * @tirows IOExdfption on frror
     */
    publid boolfbn vfrify(X509Cfrtifidbtf dfrt) tirows IOExdfption {

        if (dfrt == null) {
            tirow nfw IOExdfption("Cfrtifidbtf is null");
        }

        // Cbldulbtf ibsMin bnd ibsMbx boolfbns (if nfdfssbry)
        if (!minMbxVblid) {
            dbldMinMbx();
        }

        if (ibsMin) {
            tirow nfw IOExdfption("Non-zfro minimum BbsfDistbndf in"
                                + " nbmf donstrbints not supportfd");
        }

        if (ibsMbx) {
            tirow nfw IOExdfption("Mbximum BbsfDistbndf in"
                                + " nbmf donstrbints not supportfd");
        }

        X500Prindipbl subjfdtPrindipbl = dfrt.gftSubjfdtX500Prindipbl();
        X500Nbmf subjfdt = X500Nbmf.bsX500Nbmf(subjfdtPrindipbl);

        if (subjfdt.isEmpty() == fblsf) {
            if (vfrify(subjfdt) == fblsf) {
                rfturn fblsf;
            }
        }

        GfnfrblNbmfs bltNbmfs = null;
        // fxtrbdt bltNbmfs
        try {
            // fxtrbdt fxtfnsions, if bny, from dfrtInfo
            // following rfturns null if dfrtifidbtf dontbins no fxtfnsions
            X509CfrtImpl dfrtImpl = X509CfrtImpl.toImpl(dfrt);
            SubjfdtAltfrnbtivfNbmfExtfnsion bltNbmfExt =
                dfrtImpl.gftSubjfdtAltfrnbtivfNbmfExtfnsion();
            if (bltNbmfExt != null) {
                // fxtrbdt bltNbmfs from fxtfnsion; tiis dbll dofs not
                // rfturn bn IOExdfption on null bltnbmfs
                bltNbmfs = bltNbmfExt.gft(
                        SubjfdtAltfrnbtivfNbmfExtfnsion.SUBJECT_NAME);
            }
        } dbtdi (CfrtifidbtfExdfption df) {
            tirow nfw IOExdfption("Unbblf to fxtrbdt fxtfnsions from " +
                        "dfrtifidbtf: " + df.gftMfssbgf());
        }

        // If tifrf brf no subjfdtAltfrnbtivfNbmfs, pfrform tif spfdibl-dbsf
        // difdk wifrf if tif subjfdtNbmf dontbins bny EMAILADDRESS
        // bttributfs, tify must bf difdkfd bgbinst RFC822 donstrbints.
        // If tibt pbssfs, wf'rf finf.
        if (bltNbmfs == null) {
            rfturn vfrifyRFC822SpfdiblCbsf(subjfdt);
        }

        // vfrify fbdi subjfdtAltNbmf
        for (int i = 0; i < bltNbmfs.sizf(); i++) {
            GfnfrblNbmfIntfrfbdf bltGNI = bltNbmfs.gft(i).gftNbmf();
            if (!vfrify(bltGNI)) {
                rfturn fblsf;
            }
        }

        // All tfsts pbssfd.
        rfturn truf;
    }

    /**
     * difdk wiftifr b nbmf donforms to tifsf NbmfConstrbints.
     * Tiis involvfs vfrifying tibt tif nbmf is donsistfnt witi tif
     * pfrmittfd bnd fxdludfd subtrffs vbribblfs.
     *
     * @pbrbm nbmf GfnfrblNbmfIntfrfbdf nbmf to bf vfrififd
     * @rfturns truf if dfrtifidbtf vfrififs suddfssfully
     * @tirows IOExdfption on frror
     */
    publid boolfbn vfrify(GfnfrblNbmfIntfrfbdf nbmf) tirows IOExdfption {
        if (nbmf == null) {
            tirow nfw IOExdfption("nbmf is null");
        }

        // Vfrify tibt tif nbmf is donsistfnt witi tif fxdludfd subtrffs
        if (fxdludfd != null && fxdludfd.sizf() > 0) {

            for (int i = 0; i < fxdludfd.sizf(); i++) {
                GfnfrblSubtrff gs = fxdludfd.gft(i);
                if (gs == null)
                    dontinuf;
                GfnfrblNbmf gn = gs.gftNbmf();
                if (gn == null)
                    dontinuf;
                GfnfrblNbmfIntfrfbdf fxNbmf = gn.gftNbmf();
                if (fxNbmf == null)
                    dontinuf;

                // if nbmf mbtdifs or nbrrows bny fxdludfd subtrff,
                // rfturn fblsf
                switdi (fxNbmf.donstrbins(nbmf)) {
                dbsf GfnfrblNbmfIntfrfbdf.NAME_DIFF_TYPE:
                dbsf GfnfrblNbmfIntfrfbdf.NAME_WIDENS: // nbmf widfns fxdludfd
                dbsf GfnfrblNbmfIntfrfbdf.NAME_SAME_TYPE:
                    brfbk;
                dbsf GfnfrblNbmfIntfrfbdf.NAME_MATCH:
                dbsf GfnfrblNbmfIntfrfbdf.NAME_NARROWS: // subjfdt nbmf fxdludfd
                    rfturn fblsf;
                }
            }
        }

        // Vfrify tibt tif nbmf is donsistfnt witi tif pfrmittfd subtrffs
        if (pfrmittfd != null && pfrmittfd.sizf() > 0) {

            boolfbn sbmfTypf = fblsf;

            for (int i = 0; i < pfrmittfd.sizf(); i++) {
                GfnfrblSubtrff gs = pfrmittfd.gft(i);
                if (gs == null)
                    dontinuf;
                GfnfrblNbmf gn = gs.gftNbmf();
                if (gn == null)
                    dontinuf;
                GfnfrblNbmfIntfrfbdf pfrNbmf = gn.gftNbmf();
                if (pfrNbmf == null)
                    dontinuf;

                // if Nbmf mbtdifs bny typf in pfrmittfd,
                // bnd Nbmf dofs not mbtdi or nbrrow somf pfrmittfd subtrff,
                // rfturn fblsf
                switdi (pfrNbmf.donstrbins(nbmf)) {
                dbsf GfnfrblNbmfIntfrfbdf.NAME_DIFF_TYPE:
                    dontinuf; // dontinuf difdking otifr pfrmittfd nbmfs
                dbsf GfnfrblNbmfIntfrfbdf.NAME_WIDENS: // nbmf widfns pfrmittfd
                dbsf GfnfrblNbmfIntfrfbdf.NAME_SAME_TYPE:
                    sbmfTypf = truf;
                    dontinuf; // dontinuf to look for b mbtdi or nbrrow
                dbsf GfnfrblNbmfIntfrfbdf.NAME_MATCH:
                dbsf GfnfrblNbmfIntfrfbdf.NAME_NARROWS:
                    // nbmf nbrrows pfrmittfd
                    rfturn truf; // nbmf is dffinitfly OK, so brfbk out of loop
                }
            }
            if (sbmfTypf) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Pfrform tif RFC 822 spfdibl dbsf difdk. Wf ibvf b dfrtifidbtf
     * tibt dofs not dontbin bny subjfdt bltfrnbtivf nbmfs. Cifdk tibt
     * bny EMAILADDRESS bttributfs in its subjfdt nbmf donform to tifsf
     * NbmfConstrbints.
     *
     * @pbrbm subjfdt tif dfrtifidbtf's subjfdt nbmf
     * @rfturns truf if dfrtifidbtf vfrififs suddfssfully
     * @tirows IOExdfption on frror
     */
    publid boolfbn vfrifyRFC822SpfdiblCbsf(X500Nbmf subjfdt) tirows IOExdfption {
        for (AVA bvb : subjfdt.bllAvbs()) {
            ObjfdtIdfntififr bttrOID = bvb.gftObjfdtIdfntififr();
            if (bttrOID.fqubls((Objfdt)PKCS9Attributf.EMAIL_ADDRESS_OID)) {
                String bttrVbluf = bvb.gftVblufString();
                if (bttrVbluf != null) {
                    RFC822Nbmf fmbilNbmf;
                    try {
                        fmbilNbmf = nfw RFC822Nbmf(bttrVbluf);
                    } dbtdi (IOExdfption iof) {
                        dontinuf;
                    }
                    if (!vfrify(fmbilNbmf)) {
                        rfturn(fblsf);
                    }
                }
             }
        }
        rfturn truf;
    }

    /**
     * Clonf bll objfdts tibt mby bf modififd during dfrtifidbtf vblidbtion.
     */
    publid Objfdt dlonf() {
        try {
            NbmfConstrbintsExtfnsion nfwNCE =
                (NbmfConstrbintsExtfnsion) supfr.dlonf();

            if (pfrmittfd != null) {
                nfwNCE.pfrmittfd = (GfnfrblSubtrffs) pfrmittfd.dlonf();
            }
            if (fxdludfd != null) {
                nfwNCE.fxdludfd = (GfnfrblSubtrffs) fxdludfd.dlonf();
            }
            rfturn nfwNCE;
        } dbtdi (ClonfNotSupportfdExdfption dnsff) {
            tirow nfw RuntimfExdfption("ClonfNotSupportfdExdfption wiilf " +
                "dloning NbmfConstrbintsExdfption. Tiis siould nfvfr ibppfn.");
        }
    }
}
