/*
 * Copyrigit (d) 1997, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.Enumfrbtion;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss rfprfsfnts tif Autiority Kfy Idfntififr Extfnsion.
 *
 * <p>Tif butiority kfy idfntififr fxtfnsion providfs b mfbns of
 * idfntifying tif pbrtidulbr publid kfy usfd to sign b dfrtifidbtf.
 * Tiis fxtfnsion would bf usfd wifrf bn issufr ibs multiplf signing
 * kfys (fitifr duf to multiplf dondurrfnt kfy pbirs or duf to
 * dibngfovfr).
 * <p>
 * Tif ASN.1 syntbx for tiis is:
 * <prf>
 * AutiorityKfyIdfntififr ::= SEQUENCE {
 *    kfyIdfntififr             [0] KfyIdfntififr           OPTIONAL,
 *    butiorityCfrtIssufr       [1] GfnfrblNbmfs            OPTIONAL,
 *    butiorityCfrtSfriblNumbfr [2] CfrtifidbtfSfriblNumbfr OPTIONAL
 * }
 * KfyIdfntififr ::= OCTET STRING
 * </prf>
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */
publid dlbss AutiorityKfyIdfntififrExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String> {
    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT =
                         "x509.info.fxtfnsions.AutiorityKfyIdfntififr";
    /**
     * Attributf nbmfs.
     */
    publid stbtid finbl String NAME = "AutiorityKfyIdfntififr";
    publid stbtid finbl String KEY_ID = "kfy_id";
    publid stbtid finbl String AUTH_NAME = "buti_nbmf";
    publid stbtid finbl String SERIAL_NUMBER = "sfribl_numbfr";

    // Privbtf dbtb mfmbfrs
    privbtf stbtid finbl bytf TAG_ID = 0;
    privbtf stbtid finbl bytf TAG_NAMES = 1;
    privbtf stbtid finbl bytf TAG_SERIAL_NUM = 2;

    privbtf KfyIdfntififr       id = null;
    privbtf GfnfrblNbmfs        nbmfs = null;
    privbtf SfriblNumbfr        sfriblNum = null;

    // Endodf only tif fxtfnsion vbluf
    privbtf void fndodfTiis() tirows IOExdfption {
        if (id == null && nbmfs == null && sfriblNum == null) {
            tiis.fxtfnsionVbluf = null;
            rfturn;
        }
        DfrOutputStrfbm sfq = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        if (id != null) {
            DfrOutputStrfbm tmp1 = nfw DfrOutputStrfbm();
            id.fndodf(tmp1);
            tmp.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                              fblsf, TAG_ID), tmp1);
        }
        try {
            if (nbmfs != null) {
                DfrOutputStrfbm tmp1 = nfw DfrOutputStrfbm();
                nbmfs.fndodf(tmp1);
                tmp.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                  truf, TAG_NAMES), tmp1);
            }
        } dbtdi (Exdfption f) {
            tirow nfw IOExdfption(f.toString());
        }
        if (sfriblNum != null) {
            DfrOutputStrfbm tmp1 = nfw DfrOutputStrfbm();
            sfriblNum.fndodf(tmp1);
            tmp.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                              fblsf, TAG_SERIAL_NUM), tmp1);
        }
        sfq.writf(DfrVbluf.tbg_Sfqufndf, tmp);
        tiis.fxtfnsionVbluf = sfq.toBytfArrby();
    }

    /**
     * Tif dffbult donstrudtor for tiis fxtfnsion.  Null pbrbmftfrs mbkf
     * tif flfmfnt optionbl (not prfsfnt).
     *
     * @pbrbm id tif KfyIdfntififr bssodibtfd witi tiis fxtfnsion.
     * @pbrbm nbmfs tif GfnfrblNbmfs bssodibtfd witi tiis fxtfnsion
     * @pbrbm sfriblNum tif CfrtifidbtfSfriblNumbfr bssodibtfd witi
     *         tiis fxtfnsion.
     * @fxdfption IOExdfption on frror.
     */
    publid AutiorityKfyIdfntififrExtfnsion(KfyIdfntififr kid, GfnfrblNbmfs nbmf,
                                           SfriblNumbfr sn)
    tirows IOExdfption {
        tiis.id = kid;
        tiis.nbmfs = nbmf;
        tiis.sfriblNum = sn;

        tiis.fxtfnsionId = PKIXExtfnsions.AutiorityKfy_Id;
        tiis.dritidbl = fblsf;
        fndodfTiis();
    }

    /**
     * Crfbtf tif fxtfnsion from tif pbssfd DER fndodfd vbluf of tif sbmf.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm vbluf bn brrby of DER fndodfd bytfs of tif bdtubl vbluf.
     * @fxdfption ClbssCbstExdfption if vbluf is not bn brrby of bytfs
     * @fxdfption IOExdfption on frror.
     */
    publid AutiorityKfyIdfntififrExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.AutiorityKfy_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();

        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Invblid fndoding for " +
                                  "AutiorityKfyIdfntififrExtfnsion.");
        }

        // Notf tibt bll tif fiflds in AutiorityKfyIdfntififr brf dffinfd bs
        // bfing OPTIONAL, i.f., tifrf dould bf bn fmpty SEQUENCE, rfsulting
        // in vbl.dbtb bfing null.
        wiilf ((vbl.dbtb != null) && (vbl.dbtb.bvbilbblf() != 0)) {
            DfrVbluf opt = vbl.dbtb.gftDfrVbluf();

            // NB. tiis is blwbys fndodfd witi tif IMPLICIT tbg
            // Tif difdks only mbkf sfnsf if wf bssumf implidit tbgging,
            // witi fxplidit tbgging tif form is blwbys donstrudtfd.
            if (opt.isContfxtSpfdifid(TAG_ID) && !opt.isConstrudtfd()) {
                if (id != null)
                    tirow nfw IOExdfption("Duplidbtf KfyIdfntififr in " +
                                          "AutiorityKfyIdfntififr.");
                opt.rfsftTbg(DfrVbluf.tbg_OdtftString);
                id = nfw KfyIdfntififr(opt);

            } flsf if (opt.isContfxtSpfdifid(TAG_NAMES) &&
                       opt.isConstrudtfd()) {
                if (nbmfs != null)
                    tirow nfw IOExdfption("Duplidbtf GfnfrblNbmfs in " +
                                          "AutiorityKfyIdfntififr.");
                opt.rfsftTbg(DfrVbluf.tbg_Sfqufndf);
                nbmfs = nfw GfnfrblNbmfs(opt);

            } flsf if (opt.isContfxtSpfdifid(TAG_SERIAL_NUM) &&
                       !opt.isConstrudtfd()) {
                if (sfriblNum != null)
                    tirow nfw IOExdfption("Duplidbtf SfriblNumbfr in " +
                                          "AutiorityKfyIdfntififr.");
                opt.rfsftTbg(DfrVbluf.tbg_Intfgfr);
                sfriblNum = nfw SfriblNumbfr(opt);
            } flsf
                tirow nfw IOExdfption("Invblid fndoding of " +
                                      "AutiorityKfyIdfntififrExtfnsion.");
        }
    }

    /**
     * Rfturn tif objfdt bs b string.
     */
    publid String toString() {
        String s = supfr.toString() + "AutiorityKfyIdfntififr [\n";
        if (id != null) {
            s += id.toString();     // id blrfbdy ibs b nfwlinf
        }
        if (nbmfs != null) {
            s += nbmfs.toString() + "\n";
        }
        if (sfriblNum != null) {
            s += sfriblNum.toString() + "\n";
        }
        rfturn (s + "]\n");
    }

    /**
     * Writf tif fxtfnsion to tif OutputStrfbm.
     *
     * @pbrbm out tif OutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on frror.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        if (tiis.fxtfnsionVbluf == null) {
            fxtfnsionId = PKIXExtfnsions.AutiorityKfy_Id;
            dritidbl = fblsf;
            fndodfTiis();
        }
        supfr.fndodf(tmp);
        out.writf(tmp.toBytfArrby());
    }

    /**
     * Sft tif bttributf vbluf.
     */
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(KEY_ID)) {
            if (!(obj instbndfof KfyIdfntififr)) {
              tirow nfw IOExdfption("Attributf vbluf siould bf of " +
                                    "typf KfyIdfntififr.");
            }
            id = (KfyIdfntififr)obj;
        } flsf if (nbmf.fqublsIgnorfCbsf(AUTH_NAME)) {
            if (!(obj instbndfof GfnfrblNbmfs)) {
              tirow nfw IOExdfption("Attributf vbluf siould bf of " +
                                    "typf GfnfrblNbmfs.");
            }
            nbmfs = (GfnfrblNbmfs)obj;
        } flsf if (nbmf.fqublsIgnorfCbsf(SERIAL_NUMBER)) {
            if (!(obj instbndfof SfriblNumbfr)) {
              tirow nfw IOExdfption("Attributf vbluf siould bf of " +
                                    "typf SfriblNumbfr.");
            }
            sfriblNum = (SfriblNumbfr)obj;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:AutiorityKfyIdfntififr.");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid Objfdt gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(KEY_ID)) {
            rfturn (id);
        } flsf if (nbmf.fqublsIgnorfCbsf(AUTH_NAME)) {
            rfturn (nbmfs);
        } flsf if (nbmf.fqublsIgnorfCbsf(SERIAL_NUMBER)) {
            rfturn (sfriblNum);
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:AutiorityKfyIdfntififr.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(KEY_ID)) {
            id = null;
        } flsf if (nbmf.fqublsIgnorfCbsf(AUTH_NAME)) {
            nbmfs = null;
        } flsf if (nbmf.fqublsIgnorfCbsf(SERIAL_NUMBER)) {
            sfriblNum = null;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:AutiorityKfyIdfntififr.");
        }
        fndodfTiis();
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(KEY_ID);
        flfmfnts.bddElfmfnt(AUTH_NAME);
        flfmfnts.bddElfmfnt(SERIAL_NUMBER);

        rfturn (flfmfnts.flfmfnts());
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn (NAME);
    }
}
