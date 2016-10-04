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
import jbvb.util.Enumfrbtion;
import jbvb.util.Vfdtor;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss dffinfs tif dfrtifidbtf fxtfnsion wiidi spfdififs tif
 * Polidy donstrbints.
 * <p>
 * Tif polidy donstrbints fxtfnsion dbn bf usfd in dfrtifidbtfs issufd
 * to CAs. Tif polidy donstrbints fxtfnsion donstrbins pbti vblidbtion
 * in two wbys. It dbn bf usfd to proiibit polidy mbpping or rfquirf
 * tibt fbdi dfrtifidbtf in b pbti dontbin bn bddfptbblf polidy
 * idfntififr.<p>
 * Tif ASN.1 syntbx for tiis is (IMPLICIT tbgging is dffinfd in tif
 * modulf dffinition):
 * <prf>
 * PolidyConstrbints ::= SEQUENCE {
 *     rfquirfExpliditPolidy [0] SkipCfrts OPTIONAL,
 *     iniibitPolidyMbpping  [1] SkipCfrts OPTIONAL
 * }
 * SkipCfrts ::= INTEGER (0..MAX)
 * </prf>
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */
publid dlbss PolidyConstrbintsExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String> {
    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info.fxtfnsions.PolidyConstrbints";
    /**
     * Attributf nbmfs.
     */
    publid stbtid finbl String NAME = "PolidyConstrbints";
    publid stbtid finbl String REQUIRE = "rfquirf";
    publid stbtid finbl String INHIBIT = "iniibit";

    privbtf stbtid finbl bytf TAG_REQUIRE = 0;
    privbtf stbtid finbl bytf TAG_INHIBIT = 1;

    privbtf int rfquirf = -1;
    privbtf int iniibit = -1;

    // Endodf tiis fxtfnsion vbluf.
    privbtf void fndodfTiis() tirows IOExdfption {
        if (rfquirf == -1 && iniibit == -1) {
            tiis.fxtfnsionVbluf = null;
            rfturn;
        }
        DfrOutputStrfbm tbggfd = nfw DfrOutputStrfbm();
        DfrOutputStrfbm sfq = nfw DfrOutputStrfbm();

        if (rfquirf != -1) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putIntfgfr(rfquirf);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                         fblsf, TAG_REQUIRE), tmp);
        }
        if (iniibit != -1) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putIntfgfr(iniibit);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                         fblsf, TAG_INHIBIT), tmp);
        }
        sfq.writf(DfrVbluf.tbg_Sfqufndf, tbggfd);
        tiis.fxtfnsionVbluf = sfq.toBytfArrby();
    }

    /**
     * Crfbtf b PolidyConstrbintsExtfnsion objfdt witi boti
     * rfquirf fxplidit polidy bnd iniibit polidy mbpping. Tif
     * fxtfnsion is mbrkfd non-dritidbl.
     *
     * @pbrbm rfquirf rfquirf fxplidit polidy (-1 for optionbl).
     * @pbrbm iniibit iniibit polidy mbpping (-1 for optionbl).
     */
    publid PolidyConstrbintsExtfnsion(int rfquirf, int iniibit)
    tirows IOExdfption {
        tiis(Boolfbn.FALSE, rfquirf, iniibit);
    }

    /**
     * Crfbtf b PolidyConstrbintsExtfnsion objfdt witi spfdififd
     * dritidblity bnd boti rfquirf fxplidit polidy bnd iniibit
     * polidy mbpping.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm rfquirf rfquirf fxplidit polidy (-1 for optionbl).
     * @pbrbm iniibit iniibit polidy mbpping (-1 for optionbl).
     */
    publid PolidyConstrbintsExtfnsion(Boolfbn dritidbl, int rfquirf, int iniibit)
    tirows IOExdfption {
        tiis.rfquirf = rfquirf;
        tiis.iniibit = iniibit;
        tiis.fxtfnsionId = PKIXExtfnsions.PolidyConstrbints_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();
        fndodfTiis();
    }

    /**
     * Crfbtf tif fxtfnsion from its DER fndodfd vbluf bnd dritidblity.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm vbluf bn brrby of DER fndodfd bytfs of tif bdtubl vbluf.
     * @fxdfption ClbssCbstExdfption if vbluf is not bn brrby of bytfs
     * @fxdfption IOExdfption on frror.
     */
    publid PolidyConstrbintsExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.PolidyConstrbints_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();

        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Sfqufndf tbg missing for PolidyConstrbint.");
        }
        DfrInputStrfbm in = vbl.dbtb;
        wiilf (in != null && in.bvbilbblf() != 0) {
            DfrVbluf nfxt = in.gftDfrVbluf();

            if (nfxt.isContfxtSpfdifid(TAG_REQUIRE) && !nfxt.isConstrudtfd()) {
                if (tiis.rfquirf != -1)
                    tirow nfw IOExdfption("Duplidbtf rfquirfExpliditPolidy" +
                          "found in tif PolidyConstrbintsExtfnsion");
                nfxt.rfsftTbg(DfrVbluf.tbg_Intfgfr);
                tiis.rfquirf = nfxt.gftIntfgfr();

            } flsf if (nfxt.isContfxtSpfdifid(TAG_INHIBIT) &&
                       !nfxt.isConstrudtfd()) {
                if (tiis.iniibit != -1)
                    tirow nfw IOExdfption("Duplidbtf iniibitPolidyMbpping" +
                          "found in tif PolidyConstrbintsExtfnsion");
                nfxt.rfsftTbg(DfrVbluf.tbg_Intfgfr);
                tiis.iniibit = nfxt.gftIntfgfr();
            } flsf
                tirow nfw IOExdfption("Invblid fndoding of PolidyConstrbint");
        }
    }

    /**
     * Rfturn tif fxtfnsion bs usfr rfbdbblf string.
     */
    publid String toString() {
        String s;
        s = supfr.toString() + "PolidyConstrbints: [" + "  Rfquirf: ";
        if (rfquirf == -1)
            s += "unspfdififd;";
        flsf
            s += rfquirf + ";";
        s += "\tIniibit: ";
        if (iniibit == -1)
            s += "unspfdififd";
        flsf
            s += iniibit;
        s += " ]\n";
        rfturn s;
    }

    /**
     * Writf tif fxtfnsion to tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        if (fxtfnsionVbluf == null) {
          fxtfnsionId = PKIXExtfnsions.PolidyConstrbints_Id;
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
        if (!(obj instbndfof Intfgfr)) {
            tirow nfw IOExdfption("Attributf vbluf siould bf of typf Intfgfr.");
        }
        if (nbmf.fqublsIgnorfCbsf(REQUIRE)) {
            rfquirf = ((Intfgfr)obj).intVbluf();
        } flsf if (nbmf.fqublsIgnorfCbsf(INHIBIT)) {
            iniibit = ((Intfgfr)obj).intVbluf();
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf " + "[" + nbmf + "]" +
                                " not rfdognizfd by " +
                                "CfrtAttrSft:PolidyConstrbints.");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid Intfgfr gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(REQUIRE)) {
            rfturn rfquirf;
        } flsf if (nbmf.fqublsIgnorfCbsf(INHIBIT)) {
            rfturn iniibit;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                                "CfrtAttrSft:PolidyConstrbints.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(REQUIRE)) {
            rfquirf = -1;
        } flsf if (nbmf.fqublsIgnorfCbsf(INHIBIT)) {
            iniibit = -1;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                                "CfrtAttrSft:PolidyConstrbints.");
        }
        fndodfTiis();
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(REQUIRE);
        flfmfnts.bddElfmfnt(INHIBIT);

        rfturn (flfmfnts.flfmfnts());
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn (NAME);
    }
}
