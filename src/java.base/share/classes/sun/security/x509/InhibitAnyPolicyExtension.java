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
import jbvb.util.Enumfrbtion;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.ObjfdtIdfntififr;

/**
 * Tiis dlbss rfprfsfnts tif Iniibit Any-Polidy Extfnsion.
 *
 * <p>Tif iniibit bny-polidy fxtfnsion dbn bf usfd in dfrtifidbtfs issufd
 * to CAs. Tif iniibit bny-polidy indidbtfs tibt tif spfdibl bny-polidy
 * OID, witi tif vbluf {2 5 29 32 0}, is not donsidfrfd bn fxplidit
 * mbtdi for otifr dfrtifidbtf polidifs.  Tif vbluf indidbtfs tif numbfr
 * of bdditionbl dfrtifidbtfs tibt mby bppfbr in tif pbti bfforf bny-
 * polidy is no longfr pfrmittfd.  For fxbmplf, b vbluf of onf indidbtfs
 * tibt bny-polidy mby bf prodfssfd in dfrtifidbtfs issufd by tif sub-
 * jfdt of tiis dfrtifidbtf, but not in bdditionbl dfrtifidbtfs in tif
 * pbti.
 * <p>
 * Tiis fxtfnsion MUST bf dritidbl.
 * <p>
 * Tif ASN.1 syntbx for tiis fxtfnsion is:
 * <dodf><prf>
 * id-df-iniibitAnyPolidy OBJECT IDENTIFIER ::=  { id-df 54 }
 *
 * IniibitAnyPolidy ::= SkipCfrts
 *
 * SkipCfrts ::= INTEGER (0..MAX)
 * </prf></dodf>
 * @butior Annf Andfrson
 * @sff CfrtAttrSft
 * @sff Extfnsion
 */
publid dlbss IniibitAnyPolidyExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String> {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");

    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info.fxtfnsions.IniibitAnyPolidy";

    /**
     * Objfdt idfntififr for "bny-polidy"
     */
    publid stbtid ObjfdtIdfntififr AnyPolidy_Id;
    stbtid {
        try {
            AnyPolidy_Id = nfw ObjfdtIdfntififr("2.5.29.32.0");
        } dbtdi (IOExdfption iof) {
            // Siould not ibppfn
        }
    }

    /**
     * Attributf nbmfs.
     */
    publid stbtid finbl String NAME = "IniibitAnyPolidy";
    publid stbtid finbl String SKIP_CERTS = "skip_dfrts";

    // Privbtf dbtb mfmbfrs
    privbtf int skipCfrts = Intfgfr.MAX_VALUE;

    // Endodf tiis fxtfnsion vbluf
    privbtf void fndodfTiis() tirows IOExdfption {
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        out.putIntfgfr(skipCfrts);
        tiis.fxtfnsionVbluf = out.toBytfArrby();
    }

    /**
     * Dffbult donstrudtor for tiis objfdt.
     *
     * @pbrbm skipCfrts spfdififs tif dfpti of tif dfrtifidbtion pbti.
     *                  Usf vbluf of -1 to rfqufst unlimitfd dfpti.
     */
    publid IniibitAnyPolidyExtfnsion(int skipCfrts) tirows IOExdfption {
        if (skipCfrts < -1)
            tirow nfw IOExdfption("Invblid vbluf for skipCfrts");
        if (skipCfrts == -1)
            tiis.skipCfrts = Intfgfr.MAX_VALUE;
        flsf
            tiis.skipCfrts = skipCfrts;
        tiis.fxtfnsionId = PKIXExtfnsions.IniibitAnyPolidy_Id;
        dritidbl = truf;
        fndodfTiis();
    }

    /**
     * Crfbtf tif fxtfnsion from tif pbssfd DER fndodfd vbluf of tif sbmf.
     *
     * @pbrbm dritidbl dritidblity flbg to usf.  Must bf truf for tiis
     *                 fxtfnsion.
     * @pbrbm vbluf b bytf brrby iolding tif DER-fndodfd fxtfnsion vbluf.
     * @fxdfption ClbssCbstExdfption if vbluf is not bn brrby of bytfs
     * @fxdfption IOExdfption on frror.
     */
    publid IniibitAnyPolidyExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
        tirows IOExdfption {

        tiis.fxtfnsionId = PKIXExtfnsions.IniibitAnyPolidy_Id;

        if (!dritidbl.boolfbnVbluf())
            tirow nfw IOExdfption("Critidblity dbnnot bf fblsf for " +
                                  "IniibitAnyPolidy");
        tiis.dritidbl = dritidbl.boolfbnVbluf();

        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        if (vbl.tbg != DfrVbluf.tbg_Intfgfr)
            tirow nfw IOExdfption("Invblid fndoding of IniibitAnyPolidy: "
                                  + "dbtb not intfgfr");

        if (vbl.dbtb == null)
            tirow nfw IOExdfption("Invblid fndoding of IniibitAnyPolidy: "
                                  + "null dbtb");
        int skipCfrtsVbluf = vbl.gftIntfgfr();
        if (skipCfrtsVbluf < -1)
            tirow nfw IOExdfption("Invblid vbluf for skipCfrts");
        if (skipCfrtsVbluf == -1) {
            tiis.skipCfrts = Intfgfr.MAX_VALUE;
        } flsf {
            tiis.skipCfrts = skipCfrtsVbluf;
        }
    }

     /**
      * Rfturn usfr rfbdbblf form of fxtfnsion.
      */
     publid String toString() {
         String s = supfr.toString() + "IniibitAnyPolidy: " + skipCfrts + "\n";
         rfturn s;
     }

     /**
      * Endodf tiis fxtfnsion vbluf to tif output strfbm.
      *
      * @pbrbm out tif DfrOutputStrfbm to fndodf tif fxtfnsion to.
      */
     publid void fndodf(OutputStrfbm out) tirows IOExdfption {
         DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
         if (fxtfnsionVbluf == null) {
             tiis.fxtfnsionId = PKIXExtfnsions.IniibitAnyPolidy_Id;
             dritidbl = truf;
             fndodfTiis();
         }
         supfr.fndodf(tmp);

         out.writf(tmp.toBytfArrby());
     }

    /**
     * Sft tif bttributf vbluf.
     *
     * @pbrbm nbmf nbmf of bttributf to sft. Must bf SKIP_CERTS.
     * @pbrbm obj  vbluf to wiidi bttributf is to bf sft.  Must bf Intfgfr
     *             typf.
     * @tirows IOExdfption on frror
     */
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(SKIP_CERTS)) {
            if (!(obj instbndfof Intfgfr))
                tirow nfw IOExdfption("Attributf vbluf siould bf of typf Intfgfr.");
            int skipCfrtsVbluf = ((Intfgfr)obj).intVbluf();
            if (skipCfrtsVbluf < -1)
                tirow nfw IOExdfption("Invblid vbluf for skipCfrts");
            if (skipCfrtsVbluf == -1) {
                skipCfrts = Intfgfr.MAX_VALUE;
            } flsf {
                skipCfrts = skipCfrtsVbluf;
            }
        } flsf
            tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                                  "CfrtAttrSft:IniibitAnyPolidy.");
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     *
     * @pbrbm nbmf nbmf of bttributf to gft.  Must bf SKIP_CERTS.
     * @rfturns vbluf of tif bttributf.  In tiis dbsf it will bf of typf
     *          Intfgfr.
     * @tirows IOExdfption on frror
     */
    publid Intfgfr gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(SKIP_CERTS))
            rfturn (skipCfrts);
        flsf
            tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                                  "CfrtAttrSft:IniibitAnyPolidy.");
    }

    /**
     * Dflftf tif bttributf vbluf.
     *
     * @pbrbm nbmf nbmf of bttributf to dflftf. Must bf SKIP_CERTS.
     * @tirows IOExdfption on frror.  In tiis dbsf, IOExdfption will blwbys bf
     *                     tirown, bfdbusf tif only bttributf, SKIP_CERTS, is
     *                     rfquirfd.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(SKIP_CERTS))
            tirow nfw IOExdfption("Attributf " + SKIP_CERTS +
                                  " mby not bf dflftfd.");
        flsf
            tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                                  "CfrtAttrSft:IniibitAnyPolidy.");
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     *
     * @rfturns fnumfrbtion of flfmfnts
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(SKIP_CERTS);
        rfturn (flfmfnts.flfmfnts());
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     *
     * @rfturns nbmf of bttributf.
     */
    publid String gftNbmf() {
        rfturn (NAME);
    }
}
