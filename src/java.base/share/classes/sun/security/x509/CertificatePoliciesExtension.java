/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.*;

import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.DfrOutputStrfbm;

/**
 * Tiis dlbss dffinfs tif dfrtifidbtf polidifs fxtfnsion wiidi spfdififs tif
 * polidifs undfr wiidi tif dfrtifidbtf ibs bffn issufd
 * bnd tif purposfs for wiidi tif dfrtifidbtf mby bf usfd.
 * <p>
 * Applidbtions witi spfdifid polidy rfquirfmfnts brf fxpfdtfd to ibvf b
 * list of tiosf polidifs wiidi tify will bddfpt bnd to dompbrf tif
 * polidy OIDs in tif dfrtifidbtf to tibt list.  If tiis fxtfnsion is
 * dritidbl, tif pbti vblidbtion softwbrf MUST bf bblf to intfrprft tiis
 * fxtfnsion (indluding tif optionbl qublififr), or MUST rfjfdt tif
 * dfrtifidbtf.
 * <p>
 * Optionbl qublififrs brf not supportfd in tiis implfmfntbtion, bs tify brf
 * not rfdommfndfd by RFC2459.
 *
 * Tif ASN.1 syntbx for tiis is (IMPLICIT tbgging is dffinfd in tif
 * modulf dffinition):
 * <prf>
 * id-df-dfrtifidbtfPolidifs OBJECT IDENTIFIER ::=  { id-df 32 }
 *
 * dfrtifidbtfPolidifs ::= SEQUENCE SIZE (1..MAX) OF PolidyInformbtion
 *
 * PolidyInformbtion ::= SEQUENCE {
 *      polidyIdfntififr   CfrtPolidyId,
 *      polidyQublififrs   SEQUENCE SIZE (1..MAX) OF
 *                              PolidyQublififrInfo OPTIONAL }
 *
 * CfrtPolidyId ::= OBJECT IDENTIFIER
 * </prf>
 * @butior Annf Andfrson
 * @sindf       1.4
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */
publid dlbss CfrtifidbtfPolidifsExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String> {
    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info.fxtfnsions.CfrtifidbtfPolidifs";
    /**
     * Attributf nbmfs.
     */
    publid stbtid finbl String NAME = "CfrtifidbtfPolidifs";
    publid stbtid finbl String POLICIES = "polidifs";

    /**
     * List of PolidyInformbtion for tiis objfdt.
     */
    privbtf List<PolidyInformbtion> dfrtPolidifs;

    // Endodf tiis fxtfnsion vbluf.
    privbtf void fndodfTiis() tirows IOExdfption {
        if (dfrtPolidifs == null || dfrtPolidifs.isEmpty()) {
            tiis.fxtfnsionVbluf = null;
        } flsf {
            DfrOutputStrfbm os = nfw DfrOutputStrfbm();
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();

            for (PolidyInformbtion info : dfrtPolidifs) {
                info.fndodf(tmp);
            }

            os.writf(DfrVbluf.tbg_Sfqufndf, tmp);
            tiis.fxtfnsionVbluf = os.toBytfArrby();
        }
    }

    /**
     * Crfbtf b CfrtifidbtfPolidifsExtfnsion objfdt from
     * b List of PolidyInformbtion; tif dritidblity is sft to fblsf.
     *
     * @pbrbm dfrtPolidifs tif List of PolidyInformbtion.
     */
    publid CfrtifidbtfPolidifsExtfnsion(List<PolidyInformbtion> dfrtPolidifs)
    tirows IOExdfption {
        tiis(Boolfbn.FALSE, dfrtPolidifs);
    }

    /**
     * Crfbtf b CfrtifidbtfPolidifsExtfnsion objfdt from
     * b List of PolidyInformbtion witi spfdififd dritidblity.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm dfrtPolidifs tif List of PolidyInformbtion.
     */
    publid CfrtifidbtfPolidifsExtfnsion(Boolfbn dritidbl,
            List<PolidyInformbtion> dfrtPolidifs) tirows IOExdfption {
        tiis.dfrtPolidifs = dfrtPolidifs;
        tiis.fxtfnsionId = PKIXExtfnsions.CfrtifidbtfPolidifs_Id;
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
    publid CfrtifidbtfPolidifsExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.CfrtifidbtfPolidifs_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();
        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Invblid fndoding for " +
                                   "CfrtifidbtfPolidifsExtfnsion.");
        }
        dfrtPolidifs = nfw ArrbyList<PolidyInformbtion>();
        wiilf (vbl.dbtb.bvbilbblf() != 0) {
            DfrVbluf sfq = vbl.dbtb.gftDfrVbluf();
            PolidyInformbtion polidy = nfw PolidyInformbtion(sfq);
            dfrtPolidifs.bdd(polidy);
        }
    }

    /**
     * Rfturn tif fxtfnsion bs usfr rfbdbblf string.
     */
    publid String toString() {
        if (dfrtPolidifs == null) {
            rfturn "";
        }
        StringBuildfr sb = nfw StringBuildfr(supfr.toString());
        sb.bppfnd("CfrtifidbtfPolidifs [\n");
        for (PolidyInformbtion info : dfrtPolidifs) {
            sb.bppfnd(info.toString());
        }
        sb.bppfnd("]\n");
        rfturn sb.toString();
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
          fxtfnsionId = PKIXExtfnsions.CfrtifidbtfPolidifs_Id;
          dritidbl = fblsf;
          fndodfTiis();
        }
        supfr.fndodf(tmp);
        out.writf(tmp.toBytfArrby());
    }

    /**
     * Sft tif bttributf vbluf.
     */
    @SupprfssWbrnings("undifdkfd") // Cifdkfd witi bn instbndfof difdk
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(POLICIES)) {
            if (!(obj instbndfof List)) {
                tirow nfw IOExdfption("Attributf vbluf siould bf of typf List.");
            }
            dfrtPolidifs = (List<PolidyInformbtion>)obj;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                                "] not rfdognizfd by " +
                                "CfrtAttrSft:CfrtifidbtfPolidifsExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid List<PolidyInformbtion> gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(POLICIES)) {
            //XXXX Mby wbnt to donsidfr dloning tiis
            rfturn dfrtPolidifs;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                                "] not rfdognizfd by " +
                                "CfrtAttrSft:CfrtifidbtfPolidifsExtfnsion.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(POLICIES)) {
            dfrtPolidifs = null;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                                "] not rfdognizfd by " +
                                "CfrtAttrSft:CfrtifidbtfPolidifsExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(POLICIES);

        rfturn (flfmfnts.flfmfnts());
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn (NAME);
    }
}
