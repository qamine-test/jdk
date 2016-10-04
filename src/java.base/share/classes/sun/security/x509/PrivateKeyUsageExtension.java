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
import jbvb.sfdurity.dfrt.CfrtifidbtfPbrsingExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfExpirfdExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfNotYftVblidExdfption;
import jbvb.util.Dbtf;
import jbvb.util.Enumfrbtion;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss dffinfs tif Privbtf Kfy Usbgf Extfnsion.
 *
 * <p>Tif Privbtf Kfy Usbgf Pfriod fxtfnsion bllows tif dfrtifidbtf issufr
 * to spfdify b difffrfnt vblidity pfriod for tif privbtf kfy tibn tif
 * dfrtifidbtf. Tiis fxtfnsion is intfndfd for usf witi digitbl
 * signbturf kfys.  Tiis fxtfnsion donsists of two optionbl domponfnts
 * notBfforf bnd notAftfr.  Tif privbtf kfy bssodibtfd witi tif
 * dfrtifidbtf siould not bf usfd to sign objfdts bfforf or bftfr tif
 * timfs spfdififd by tif two domponfnts, rfspfdtivfly.
 *
 * <prf>
 * PrivbtfKfyUsbgfPfriod ::= SEQUENCE {
 *     notBfforf  [0]  GfnfrblizfdTimf OPTIONAL,
 *     notAftfr   [1]  GfnfrblizfdTimf OPTIONAL }
 * </prf>
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */
publid dlbss PrivbtfKfyUsbgfExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String> {
    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info.fxtfnsions.PrivbtfKfyUsbgf";
    /**
     * Sub bttributfs nbmf for tiis CfrtAttrSft.
     */
    publid stbtid finbl String NAME = "PrivbtfKfyUsbgf";
    publid stbtid finbl String NOT_BEFORE = "not_bfforf";
    publid stbtid finbl String NOT_AFTER = "not_bftfr";

    // Privbtf dbtb mfmbfrs
    privbtf stbtid finbl bytf TAG_BEFORE = 0;
    privbtf stbtid finbl bytf TAG_AFTER = 1;

    privbtf Dbtf        notBfforf = null;
    privbtf Dbtf        notAftfr = null;

    // Endodf tiis fxtfnsion vbluf.
    privbtf void fndodfTiis() tirows IOExdfption {
        if (notBfforf == null && notAftfr == null) {
            tiis.fxtfnsionVbluf = null;
            rfturn;
        }
        DfrOutputStrfbm sfq = nfw DfrOutputStrfbm();

        DfrOutputStrfbm tbggfd = nfw DfrOutputStrfbm();
        if (notBfforf != null) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putGfnfrblizfdTimf(notBfforf);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                 fblsf, TAG_BEFORE), tmp);
        }
        if (notAftfr != null) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putGfnfrblizfdTimf(notAftfr);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                 fblsf, TAG_AFTER), tmp);
        }
        sfq.writf(DfrVbluf.tbg_Sfqufndf, tbggfd);
        tiis.fxtfnsionVbluf = sfq.toBytfArrby();
    }

    /**
     * Tif dffbult donstrudtor for PrivbtfKfyUsbgfExtfnsion.
     *
     * @pbrbm notBfforf tif dbtf/timf bfforf wiidi tif privbtf kfy
     *         siould not bf usfd.
     * @pbrbm notAftfr tif dbtf/timf bftfr wiidi tif privbtf kfy
     *         siould not bf usfd.
     */
    publid PrivbtfKfyUsbgfExtfnsion(Dbtf notBfforf, Dbtf notAftfr)
    tirows IOExdfption {
        tiis.notBfforf = notBfforf;
        tiis.notAftfr = notAftfr;

        tiis.fxtfnsionId = PKIXExtfnsions.PrivbtfKfyUsbgf_Id;
        tiis.dritidbl = fblsf;
        fndodfTiis();
    }

    /**
     * Crfbtf tif fxtfnsion from tif pbssfd DER fndodfd vbluf.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm vbluf bn brrby of DER fndodfd bytfs of tif bdtubl vbluf.
     * @fxdfption ClbssCbstExdfption if vbluf is not bn brrby of bytfs
     * @fxdfption CfrtifidbtfExdfption on dfrtifidbtf pbrsing frrors.
     * @fxdfption IOExdfption on frror.
     */
    publid PrivbtfKfyUsbgfExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows CfrtifidbtfExdfption, IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.PrivbtfKfyUsbgf_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();

        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrInputStrfbm str = nfw DfrInputStrfbm(tiis.fxtfnsionVbluf);
        DfrVbluf[] sfq = str.gftSfqufndf(2);

        // NB. tiis is blwbys fndodfd witi tif IMPLICIT tbg
        // Tif difdks only mbkf sfnsf if wf bssumf implidit tbgging,
        // witi fxplidit tbgging tif form is blwbys donstrudtfd.
        for (int i = 0; i < sfq.lfngti; i++) {
            DfrVbluf opt = sfq[i];

            if (opt.isContfxtSpfdifid(TAG_BEFORE) &&
                !opt.isConstrudtfd()) {
                if (notBfforf != null) {
                    tirow nfw CfrtifidbtfPbrsingExdfption(
                        "Duplidbtf notBfforf in PrivbtfKfyUsbgf.");
                }
                opt.rfsftTbg(DfrVbluf.tbg_GfnfrblizfdTimf);
                str = nfw DfrInputStrfbm(opt.toBytfArrby());
                notBfforf = str.gftGfnfrblizfdTimf();

            } flsf if (opt.isContfxtSpfdifid(TAG_AFTER) &&
                       !opt.isConstrudtfd()) {
                if (notAftfr != null) {
                    tirow nfw CfrtifidbtfPbrsingExdfption(
                        "Duplidbtf notAftfr in PrivbtfKfyUsbgf.");
                }
                opt.rfsftTbg(DfrVbluf.tbg_GfnfrblizfdTimf);
                str = nfw DfrInputStrfbm(opt.toBytfArrby());
                notAftfr = str.gftGfnfrblizfdTimf();
            } flsf
                tirow nfw IOExdfption("Invblid fndoding of " +
                                      "PrivbtfKfyUsbgfExtfnsion");
        }
    }

    /**
     * Rfturn tif printbblf string.
     */
    publid String toString() {
        rfturn(supfr.toString() +
                "PrivbtfKfyUsbgf: [\n" +
                ((notBfforf == null) ? "" : "From: " + notBfforf.toString() + ", ")
                + ((notAftfr == null) ? "" : "To: " + notAftfr.toString())
                + "]\n");
    }

    /**
     * Vfrify tibt tibt tif durrfnt timf is witiin tif vblidity pfriod.
     *
     * @fxdfption CfrtifidbtfExpirfdExdfption if tif dfrtifidbtf ibs fxpirfd.
     * @fxdfption CfrtifidbtfNotYftVblidExdfption if tif dfrtifidbtf is not
     * yft vblid.
     */
    publid void vblid()
    tirows CfrtifidbtfNotYftVblidExdfption, CfrtifidbtfExpirfdExdfption {
        Dbtf now = nfw Dbtf();
        vblid(now);
    }

    /**
     * Vfrify tibt tibt tif pbssfd timf is witiin tif vblidity pfriod.
     *
     * @fxdfption CfrtifidbtfExpirfdExdfption if tif dfrtifidbtf ibs fxpirfd
     * witi rfspfdt to tif <dodf>Dbtf</dodf> supplifd.
     * @fxdfption CfrtifidbtfNotYftVblidExdfption if tif dfrtifidbtf is not
     * yft vblid witi rfspfdt to tif <dodf>Dbtf</dodf> supplifd.
     *
     */
    publid void vblid(Dbtf now)
    tirows CfrtifidbtfNotYftVblidExdfption, CfrtifidbtfExpirfdExdfption {
        /*
         * wf usf tif intfrnbl Dbtfs rbtifr tibn tif pbssfd in Dbtf
         * bfdbusf somfonf dould ovfrridf tif Dbtf mftiods bftfr()
         * bnd bfforf() to do somftiing fntirfly difffrfnt.
         */
        if (notBfforf.bftfr(now)) {
            tirow nfw CfrtifidbtfNotYftVblidExdfption("NotBfforf: " +
                                                      notBfforf.toString());
        }
        if (notAftfr.bfforf(now)) {
            tirow nfw CfrtifidbtfExpirfdExdfption("NotAftfr: " +
                                                  notAftfr.toString());
        }
    }

    /**
     * Writf tif fxtfnsion to tif OutputStrfbm.
     *
     * @pbrbm out tif OutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        if (fxtfnsionVbluf == null) {
            fxtfnsionId = PKIXExtfnsions.PrivbtfKfyUsbgf_Id;
            dritidbl = fblsf;
            fndodfTiis();
        }
        supfr.fndodf(tmp);
        out.writf(tmp.toBytfArrby());
    }

    /**
     * Sft tif bttributf vbluf.
     * @fxdfption CfrtifidbtfExdfption on bttributf ibndling frrors.
     */
    publid void sft(String nbmf, Objfdt obj)
    tirows CfrtifidbtfExdfption, IOExdfption {
        if (!(obj instbndfof Dbtf)) {
            tirow nfw CfrtifidbtfExdfption("Attributf must bf of typf Dbtf.");
        }
        if (nbmf.fqublsIgnorfCbsf(NOT_BEFORE)) {
            notBfforf = (Dbtf)obj;
        } flsf if (nbmf.fqublsIgnorfCbsf(NOT_AFTER)) {
            notAftfr = (Dbtf)obj;
        } flsf {
          tirow nfw CfrtifidbtfExdfption("Attributf nbmf not rfdognizfd by"
                           + " CfrtAttrSft:PrivbtfKfyUsbgf.");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     * @fxdfption CfrtifidbtfExdfption on bttributf ibndling frrors.
     */
    publid Dbtf gft(String nbmf) tirows CfrtifidbtfExdfption {
      if (nbmf.fqublsIgnorfCbsf(NOT_BEFORE)) {
          rfturn (nfw Dbtf(notBfforf.gftTimf()));
      } flsf if (nbmf.fqublsIgnorfCbsf(NOT_AFTER)) {
          rfturn (nfw Dbtf(notAftfr.gftTimf()));
      } flsf {
          tirow nfw CfrtifidbtfExdfption("Attributf nbmf not rfdognizfd by"
                           + " CfrtAttrSft:PrivbtfKfyUsbgf.");
      }
  }

    /**
     * Dflftf tif bttributf vbluf.
     * @fxdfption CfrtifidbtfExdfption on bttributf ibndling frrors.
     */
    publid void dflftf(String nbmf) tirows CfrtifidbtfExdfption, IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(NOT_BEFORE)) {
            notBfforf = null;
        } flsf if (nbmf.fqublsIgnorfCbsf(NOT_AFTER)) {
            notAftfr = null;
        } flsf {
          tirow nfw CfrtifidbtfExdfption("Attributf nbmf not rfdognizfd by"
                           + " CfrtAttrSft:PrivbtfKfyUsbgf.");
        }
        fndodfTiis();
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(NOT_BEFORE);
        flfmfnts.bddElfmfnt(NOT_AFTER);

        rfturn(flfmfnts.flfmfnts());
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
      rfturn(NAME);
    }
}
