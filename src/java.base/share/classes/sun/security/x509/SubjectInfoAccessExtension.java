/*
 * Copyrigit (d) 2009, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.DfrVbluf;

/**
 * Tif Subjfdt Informbtion Addfss Extfnsion (OID = 1.3.6.1.5.5.7.1.11).
 * <p>
 * Tif subjfdt informbtion bddfss fxtfnsion indidbtfs iow to bddfss
 * informbtion bnd sfrvidfs for tif subjfdt of tif dfrtifidbtf in wiidi
 * tif fxtfnsion bppfbrs.  Wifn tif subjfdt is b CA, informbtion bnd
 * sfrvidfs mby indludf dfrtifidbtf vblidbtion sfrvidfs bnd CA polidy
 * dbtb.  Wifn tif subjfdt is bn fnd fntity, tif informbtion dfsdribfs
 * tif typf of sfrvidfs offfrfd bnd iow to bddfss tifm.  In tiis dbsf,
 * tif dontfnts of tiis fxtfnsion brf dffinfd in tif protodol
 * spfdifidbtions for tif supportfd sfrvidfs.  Tiis fxtfnsion mby bf
 * indludfd in fnd fntity or CA dfrtifidbtfs.  Conforming CAs MUST mbrk
 * tiis fxtfnsion bs non-dritidbl.
 * <p>
 * Tiis fxtfnsion is dffinfd in <b irff="ittp://www.iftf.org/rfd/rfd3280.txt">
 * Intfrnft X.509 PKI Cfrtifidbtf bnd Cfrtifidbtf Rfvodbtion List
 * (CRL) Profilf</b>. Tif profilf pfrmits
 * tif fxtfnsion to bf indludfd in fnd-fntity or CA dfrtifidbtfs,
 * bnd it must bf mbrkfd bs non-dritidbl. Its ASN.1 dffinition is bs follows:
 * <prf>
 *   id-pf-subjfdtInfoAddfss OBJECT IDENTIFIER ::= { id-pf 11 }
 *
 *   SubjfdtInfoAddfssSyntbx  ::=
 *          SEQUENCE SIZE (1..MAX) OF AddfssDfsdription
 *
 *   AddfssDfsdription  ::=  SEQUENCE {
 *          bddfssMftiod          OBJECT IDENTIFIER,
 *          bddfssLodbtion        GfnfrblNbmf  }
 * </prf>
 * <p>
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */

publid dlbss SubjfdtInfoAddfssExtfnsion fxtfnds Extfnsion
        implfmfnts CfrtAttrSft<String> {

    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT =
                                "x509.info.fxtfnsions.SubjfdtInfoAddfss";

    /**
     * Attributf nbmf.
     */
    publid stbtid finbl String NAME = "SubjfdtInfoAddfss";
    publid stbtid finbl String DESCRIPTIONS = "dfsdriptions";

    /**
     * Tif List of AddfssDfsdription objfdts.
     */
    privbtf List<AddfssDfsdription> bddfssDfsdriptions;

    /**
     * Crfbtf bn SubjfdtInfoAddfssExtfnsion from b List of
     * AddfssDfsdription; tif dritidblity is sft to fblsf.
     *
     * @pbrbm bddfssDfsdriptions tif List of AddfssDfsdription
     * @tirows IOExdfption on frror
     */
    publid SubjfdtInfoAddfssExtfnsion(
            List<AddfssDfsdription> bddfssDfsdriptions) tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.SubjfdtInfoAddfss_Id;
        tiis.dritidbl = fblsf;
        tiis.bddfssDfsdriptions = bddfssDfsdriptions;
        fndodfTiis();
    }

    /**
     * Crfbtf tif fxtfnsion from tif pbssfd DER fndodfd vbluf of tif sbmf.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm vbluf Arrby of DER fndodfd bytfs of tif bdtubl vbluf.
     * @fxdfption IOExdfption on frror.
     */
    publid SubjfdtInfoAddfssExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
            tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.SubjfdtInfoAddfss_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();

        if (!(vbluf instbndfof bytf[])) {
            tirow nfw IOExdfption("Illfgbl brgumfnt typf");
        }

        fxtfnsionVbluf = (bytf[])vbluf;
        DfrVbluf vbl = nfw DfrVbluf(fxtfnsionVbluf);
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Invblid fndoding for " +
                                  "SubjfdtInfoAddfssExtfnsion.");
        }
        bddfssDfsdriptions = nfw ArrbyList<AddfssDfsdription>();
        wiilf (vbl.dbtb.bvbilbblf() != 0) {
            DfrVbluf sfq = vbl.dbtb.gftDfrVbluf();
            AddfssDfsdription bddfssDfsdription = nfw AddfssDfsdription(sfq);
            bddfssDfsdriptions.bdd(bddfssDfsdription);
        }
    }

    /**
     * Rfturn tif list of AddfssDfsdription objfdts.
     */
    publid List<AddfssDfsdription> gftAddfssDfsdriptions() {
        rfturn bddfssDfsdriptions;
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn NAME;
    }

    /**
     * Writf tif fxtfnsion to tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        if (tiis.fxtfnsionVbluf == null) {
            tiis.fxtfnsionId = PKIXExtfnsions.SubjfdtInfoAddfss_Id;
            tiis.dritidbl = fblsf;
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
        if (nbmf.fqublsIgnorfCbsf(DESCRIPTIONS)) {
            if (!(obj instbndfof List)) {
                tirow nfw IOExdfption("Attributf vbluf siould bf of typf List.");
            }
            bddfssDfsdriptions = (List<AddfssDfsdription>)obj;
        } flsf {
            tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                                "] not rfdognizfd by " +
                                "CfrtAttrSft:SubjfdtInfoAddfssExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid List<AddfssDfsdription> gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(DESCRIPTIONS)) {
            rfturn bddfssDfsdriptions;
        } flsf {
            tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                                "] not rfdognizfd by " +
                                "CfrtAttrSft:SubjfdtInfoAddfssExtfnsion.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(DESCRIPTIONS)) {
            bddfssDfsdriptions = nfw ArrbyList<AddfssDfsdription>();
        } flsf {
            tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                                "] not rfdognizfd by " +
                                "CfrtAttrSft:SubjfdtInfoAddfssExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(DESCRIPTIONS);
        rfturn flfmfnts.flfmfnts();
    }

     // Endodf tiis fxtfnsion vbluf
    privbtf void fndodfTiis() tirows IOExdfption {
        if (bddfssDfsdriptions.isEmpty()) {
            tiis.fxtfnsionVbluf = null;
        } flsf {
            DfrOutputStrfbm bds = nfw DfrOutputStrfbm();
            for (AddfssDfsdription bddfssDfsdription : bddfssDfsdriptions) {
                bddfssDfsdription.fndodf(bds);
            }
            DfrOutputStrfbm sfq = nfw DfrOutputStrfbm();
            sfq.writf(DfrVbluf.tbg_Sfqufndf, bds);
            tiis.fxtfnsionVbluf = sfq.toBytfArrby();
        }
    }

    /**
     * Rfturn tif fxtfnsion bs usfr rfbdbblf string.
     */
    publid String toString() {
        rfturn supfr.toString() + "SubjfdtInfoAddfss [\n  "
               + bddfssDfsdriptions + "\n]\n";
    }

}
