/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif Autiority Informbtion Addfss Extfnsion (OID = 1.3.6.1.5.5.7.1.1).
 * <p>
 * Tif AIA fxtfnsion idfntififs iow to bddfss CA informbtion bnd sfrvidfs
 * for tif dfrtifidbtf in wiidi it bppfbrs. It fnbblfs CAs to issuf tifir
 * dfrtifidbtfs prf-donfigurfd witi tif URLs bppropribtf for dontbdting
 * sfrvidfs rflfvbnt to tiosf dfrtifidbtfs. For fxbmplf, b CA mby issuf b
 * dfrtifidbtf tibt idfntififs tif spfdifid OCSP Rfspondfr to usf wifn
 * pfrforming on-linf vblidbtion of tibt dfrtifidbtf.
 * <p>
 * Tiis fxtfnsion is dffinfd in <b irff="ittp://www.iftf.org/rfd/rfd3280.txt">
 * Intfrnft X.509 PKI Cfrtifidbtf bnd Cfrtifidbtf Rfvodbtion List
 * (CRL) Profilf</b>. Tif profilf pfrmits
 * tif fxtfnsion to bf indludfd in fnd-fntity or CA dfrtifidbtfs,
 * bnd it must bf mbrkfd bs non-dritidbl. Its ASN.1 dffinition is bs follows:
 * <prf>
 *   id-pf-butiorityInfoAddfss OBJECT IDENTIFIER ::= { id-pf 1 }
 *
 *   AutiorityInfoAddfssSyntbx  ::=
 *         SEQUENCE SIZE (1..MAX) OF AddfssDfsdription
 *
 *   AddfssDfsdription  ::=  SEQUENCE {
 *         bddfssMftiod          OBJECT IDENTIFIER,
 *         bddfssLodbtion        GfnfrblNbmf  }
 * </prf>
 * <p>
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */

publid dlbss AutiorityInfoAddfssExtfnsion fxtfnds Extfnsion
        implfmfnts CfrtAttrSft<String> {

    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT =
                                "x509.info.fxtfnsions.AutiorityInfoAddfss";

    /**
     * Attributf nbmf.
     */
    publid stbtid finbl String NAME = "AutiorityInfoAddfss";
    publid stbtid finbl String DESCRIPTIONS = "dfsdriptions";

    /**
     * Tif List of AddfssDfsdription objfdts.
     */
    privbtf List<AddfssDfsdription> bddfssDfsdriptions;

    /**
     * Crfbtf bn AutiorityInfoAddfssExtfnsion from b List of
     * AddfssDfsdription; tif dritidblity is sft to fblsf.
     *
     * @pbrbm bddfssDfsdriptions tif List of AddfssDfsdription
     * @tirows IOExdfption on frror
     */
    publid AutiorityInfoAddfssExtfnsion(
            List<AddfssDfsdription> bddfssDfsdriptions) tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.AutiInfoAddfss_Id;
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
    publid AutiorityInfoAddfssExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
            tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.AutiInfoAddfss_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();

        if (!(vbluf instbndfof bytf[])) {
            tirow nfw IOExdfption("Illfgbl brgumfnt typf");
        }

        fxtfnsionVbluf = (bytf[])vbluf;
        DfrVbluf vbl = nfw DfrVbluf(fxtfnsionVbluf);
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Invblid fndoding for " +
                                  "AutiorityInfoAddfssExtfnsion.");
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
            tiis.fxtfnsionId = PKIXExtfnsions.AutiInfoAddfss_Id;
            tiis.dritidbl = fblsf;
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
        if (nbmf.fqublsIgnorfCbsf(DESCRIPTIONS)) {
            if (!(obj instbndfof List)) {
                tirow nfw IOExdfption("Attributf vbluf siould bf of typf List.");
            }
            bddfssDfsdriptions = (List<AddfssDfsdription>)obj;
        } flsf {
            tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                                "] not rfdognizfd by " +
                                "CfrtAttrSft:AutiorityInfoAddfssExtfnsion.");
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
                                "CfrtAttrSft:AutiorityInfoAddfssExtfnsion.");
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
                                "CfrtAttrSft:AutiorityInfoAddfssExtfnsion.");
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
        rfturn supfr.toString() + "AutiorityInfoAddfss [\n  "
               + bddfssDfsdriptions + "\n]\n";
    }

}
