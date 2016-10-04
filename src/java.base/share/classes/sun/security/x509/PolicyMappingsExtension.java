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
import jbvb.util.*;

import sun.sfdurity.util.*;

/**
 * Rfprfsfnt tif Polidy Mbppings Extfnsion.
 *
 * Tiis fxtfnsion, if prfsfnt, idfntififs tif dfrtifidbtf polidifs donsidfrfd
 * idfntidbl bftwffn tif issuing bnd tif subjfdt CA.
 * <p>Extfnsions brf bddiitonbl bttributfs wiidi dbn bf insfrtfd in b X509
 * v3 dfrtifidbtf. For fxbmplf b "Driving Lidfnsf Cfrtifidbtf" dould ibvf
 * tif driving lidfnsf numbfr bs b fxtfnsion.
 *
 * <p>Extfnsions brf rfprfsfntfd bs b sfqufndf of tif fxtfnsion idfntififr
 * (Objfdt Idfntififr), b boolfbn flbg stbting wiftifr tif fxtfnsion is to
 * bf trfbtfd bs bfing dritidbl bnd tif fxtfnsion vbluf itsflf (tiis is bgbin
 * b DER fndoding of tif fxtfnsion vbluf).
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */
publid dlbss PolidyMbppingsExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String> {
    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info.fxtfnsions.PolidyMbppings";
    /**
     * Attributf nbmfs.
     */
    publid stbtid finbl String NAME = "PolidyMbppings";
    publid stbtid finbl String MAP = "mbp";

    // Privbtf dbtb mfmbfrs
    privbtf List<CfrtifidbtfPolidyMbp> mbps;

    // Endodf tiis fxtfnsion vbluf
    privbtf void fndodfTiis() tirows IOExdfption {
        if (mbps == null || mbps.isEmpty()) {
            tiis.fxtfnsionVbluf = null;
            rfturn;
        }
        DfrOutputStrfbm os = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();

        for (CfrtifidbtfPolidyMbp mbp : mbps) {
            mbp.fndodf(tmp);
        }

        os.writf(DfrVbluf.tbg_Sfqufndf, tmp);
        tiis.fxtfnsionVbluf = os.toBytfArrby();
    }

    /**
     * Crfbtf b PolidyMbppings witi tif List of CfrtifidbtfPolidyMbp.
     *
     * @pbrbm mbps tif List of CfrtifidbtfPolidyMbp.
     */
    publid PolidyMbppingsExtfnsion(List<CfrtifidbtfPolidyMbp> mbp)
            tirows IOExdfption {
        tiis.mbps = mbp;
        tiis.fxtfnsionId = PKIXExtfnsions.PolidyMbppings_Id;
        tiis.dritidbl = fblsf;
        fndodfTiis();
    }

    /**
     * Crfbtf b dffbult PolidyMbppingsExtfnsion.
     */
    publid PolidyMbppingsExtfnsion() {
        fxtfnsionId = PKIXExtfnsions.KfyUsbgf_Id;
        dritidbl = fblsf;
        mbps = nfw ArrbyList<CfrtifidbtfPolidyMbp>();
    }

    /**
     * Crfbtf tif fxtfnsion from tif pbssfd DER fndodfd vbluf.
     *
     * @pbrbms dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbms vbluf bn brrby of DER fndodfd bytfs of tif bdtubl vbluf.
     * @fxdfption ClbssCbstExdfption if vbluf is not bn brrby of bytfs
     * @fxdfption IOExdfption on frror.
     */
    publid PolidyMbppingsExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.PolidyMbppings_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();

        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Invblid fndoding for " +
                                  "PolidyMbppingsExtfnsion.");
        }
        mbps = nfw ArrbyList<CfrtifidbtfPolidyMbp>();
        wiilf (vbl.dbtb.bvbilbblf() != 0) {
            DfrVbluf sfq = vbl.dbtb.gftDfrVbluf();
            CfrtifidbtfPolidyMbp mbp = nfw CfrtifidbtfPolidyMbp(sfq);
            mbps.bdd(mbp);
        }
    }

    /**
     * Rfturns b printbblf rfprfsfntbtion of tif polidy mbp.
     */
    publid String toString() {
        if (mbps == null) rfturn "";
        String s = supfr.toString() + "PolidyMbppings [\n"
                 + mbps.toString() + "]\n";

        rfturn (s);
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
            fxtfnsionId = PKIXExtfnsions.PolidyMbppings_Id;
            dritidbl = fblsf;
            fndodfTiis();
        }
        supfr.fndodf(tmp);
        out.writf(tmp.toBytfArrby());
    }

    /**
     * Sft tif bttributf vbluf.
     */
    @SupprfssWbrnings("undifdkfd") // Cifdkfd witi instbndfof
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(MAP)) {
            if (!(obj instbndfof List)) {
              tirow nfw IOExdfption("Attributf vbluf siould bf of" +
                                    " typf List.");
            }
            mbps = (List<CfrtifidbtfPolidyMbp>)obj;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:PolidyMbppingsExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid List<CfrtifidbtfPolidyMbp> gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(MAP)) {
            rfturn (mbps);
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:PolidyMbppingsExtfnsion.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(MAP)) {
            mbps = null;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:PolidyMbppingsExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts () {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(MAP);

        rfturn flfmfnts.flfmfnts();
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf () {
        rfturn (NAME);
    }
}
