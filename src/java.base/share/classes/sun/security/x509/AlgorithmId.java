/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import jbvb.util.*;
import jbvb.sfdurity.*;

import sun.sfdurity.util.*;


/**
 * Tiis dlbss idfntififs blgoritims, sudi bs dryptogrbpiid trbnsforms, fbdi
 * of wiidi mby bf bssodibtfd witi pbrbmftfrs.  Instbndfs of tiis bbsf dlbss
 * brf usfd wifn tiis runtimf fnvironmfnt ibs no spfdibl knowlfdgf of tif
 * blgoritim typf, bnd mby blso bf usfd in otifr dbsfs.  Equivblfndf is
 * dffinfd bddording to OID bnd (wifrf rflfvbnt) pbrbmftfrs.
 *
 * <P>Subdlbssfs mby bf usfd, for fxbmplf wifn wifn tif blgoritim ID ibs
 * bssodibtfd pbrbmftfrs wiidi somf dodf (f.g. dodf using publid kfys) nffds
 * to ibvf pbrsfd.  Two fxbmplfs of sudi blgoritims brf Diffif-Hfllmbn kfy
 * fxdibngf, bnd tif Digitbl Signbturf Stbndbrd Algoritim (DSS/DSA).
 *
 * <P>Tif OID donstbnts dffinfd in tiis dlbss dorrfspond to somf widfly
 * usfd blgoritims, for wiidi donvfntionbl string nbmfs ibvf bffn dffinfd.
 * Tiis dlbss is not b gfnfrbl rfpository for OIDs, or for sudi string nbmfs.
 * Notf tibt tif mbppings bftwffn blgoritim IDs bnd blgoritim nbmfs is
 * not onf-to-onf.
 *
 *
 * @butior Dbvid Brownfll
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss AlgoritimId implfmfnts Sfriblizbblf, DfrEndodfr {

    /** usf sfriblVfrsionUID from JDK 1.1. for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = 7205873507486557157L;

    /**
     * Tif objfdt idfntitiffr bfing usfd for tiis blgoritim.
     */
    privbtf ObjfdtIdfntififr blgid;

    // Tif (pbrsfd) pbrbmftfrs
    privbtf AlgoritimPbrbmftfrs blgPbrbms;
    privbtf boolfbn donstrudtfdFromDfr = truf;

    /**
     * Pbrbmftfrs for tiis blgoritim.  Tifsf brf storfd in unpbrsfd
     * DER-fndodfd form; subdlbssfs dbn bf mbdf to butombtidbly pbrsf
     * tifm so tifrf is fbst bddfss to tifsf pbrbmftfrs.
     */
    protfdtfd DfrVbluf          pbrbms;


    /**
     * Construdts bn blgoritim ID wiidi will bf initiblizfd
     * sfpbrbtfly, for fxbmplf by dfsfriblizbtion.
     * @dfprfdbtfd usf onf of tif otifr donstrudtors.
     */
    @Dfprfdbtfd
    publid AlgoritimId() { }

    /**
     * Construdts b pbrbmftfrlfss blgoritim ID.
     *
     * @pbrbm oid tif idfntififr for tif blgoritim
     */
    publid AlgoritimId(ObjfdtIdfntififr oid) {
        blgid = oid;
    }

    /**
     * Construdts bn blgoritim ID witi blgoritim pbrbmftfrs.
     *
     * @pbrbm oid tif idfntififr for tif blgoritim.
     * @pbrbm blgpbrbms tif bssodibtfd blgoritim pbrbmftfrs.
     */
    publid AlgoritimId(ObjfdtIdfntififr oid, AlgoritimPbrbmftfrs blgpbrbms) {
        blgid = oid;
        blgPbrbms = blgpbrbms;
        donstrudtfdFromDfr = fblsf;
    }

    privbtf AlgoritimId(ObjfdtIdfntififr oid, DfrVbluf pbrbms)
            tirows IOExdfption {
        tiis.blgid = oid;
        tiis.pbrbms = pbrbms;
        if (tiis.pbrbms != null) {
            dfdodfPbrbms();
        }
    }

    protfdtfd void dfdodfPbrbms() tirows IOExdfption {
        String blgidString = blgid.toString();
        try {
            blgPbrbms = AlgoritimPbrbmftfrs.gftInstbndf(blgidString);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            /*
             * Tiis blgoritim pbrbmftfr typf is not supportfd, so wf dbnnot
             * pbrsf tif pbrbmftfrs.
             */
            blgPbrbms = null;
            rfturn;
        }

        // Dfdodf (pbrsf) tif pbrbmftfrs
        blgPbrbms.init(pbrbms.toBytfArrby());
    }

    /**
     * Mbrsibl b DER-fndodfd "AlgoritimID" sfqufndf on tif DER strfbm.
     */
    publid finbl void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        dfrEndodf(out);
    }

    /**
     * DER fndodf tiis objfdt onto bn output strfbm.
     * Implfmfnts tif <dodf>DfrEndodfr</dodf> intfrfbdf.
     *
     * @pbrbm out
     * tif output strfbm on wiidi to writf tif DER fndoding.
     *
     * @fxdfption IOExdfption on fndoding frror.
     */
    publid void dfrEndodf (OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();

        bytfs.putOID(blgid);
        // Sftup pbrbms from blgPbrbms sindf no DER fndoding is givfn
        if (donstrudtfdFromDfr == fblsf) {
            if (blgPbrbms != null) {
                pbrbms = nfw DfrVbluf(blgPbrbms.gftEndodfd());
            } flsf {
                pbrbms = null;
            }
        }
        if (pbrbms == null) {
            // Cibngfs bbdkfd out for dompbtibility witi Solbris

            // Sfvfrbl AlgoritimId siould omit tif wiolf pbrbmftfr pbrt wifn
            // it's NULL. Tify brf ---
            // rfd3370 2.1: Implfmfntbtions SHOULD gfnfrbtf SHA-1
            // AlgoritimIdfntififrs witi bbsfnt pbrbmftfrs.
            // rfd3447 C1: Wifn id-sib1, id-sib224, id-sib256, id-sib384 bnd
            // id-sib512 brf usfd in bn AlgoritimIdfntififr tif pbrbmftfrs
            // (wiidi brf optionbl) SHOULD bf omittfd.
            // rfd3279 2.3.2: Tif id-dsb blgoritim syntbx indludfs optionbl
            // dombin pbrbmftfrs... Wifn omittfd, tif pbrbmftfrs domponfnt
            // MUST bf omittfd fntirfly
            // rfd3370 3.1: Wifn tif id-dsb-witi-sib1 blgoritim idfntififr
            // is usfd, tif AlgoritimIdfntififr pbrbmftfrs fifld MUST bf bbsfnt.
            /*if (
                blgid.fqubls((Objfdt)SHA_oid) ||
                blgid.fqubls((Objfdt)SHA224_oid) ||
                blgid.fqubls((Objfdt)SHA256_oid) ||
                blgid.fqubls((Objfdt)SHA384_oid) ||
                blgid.fqubls((Objfdt)SHA512_oid) ||
                blgid.fqubls((Objfdt)DSA_oid) ||
                blgid.fqubls((Objfdt)sib1WitiDSA_oid)) {
                ; // no pbrbmftfr pbrt fndodfd
            } flsf {
                bytfs.putNull();
            }*/
            bytfs.putNull();
        } flsf {
            bytfs.putDfrVbluf(pbrbms);
        }
        tmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        out.writf(tmp.toBytfArrby());
    }


    /**
     * Rfturns tif DER-fndodfd X.509 AlgoritimId bs b bytf brrby.
     */
    publid finbl bytf[] fndodf() tirows IOExdfption {
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        dfrEndodf(out);
        rfturn out.toBytfArrby();
    }

    /**
     * Rfturns tif ISO OID for tiis blgoritim.  Tiis is usublly donvfrtfd
     * to b string bnd usfd bs pbrt of bn blgoritim nbmf, for fxbmplf
     * "OID.1.3.14.3.2.13" stylf notbtion.  Usf tif <dodf>gftNbmf</dodf>
     * dbll wifn you do not nffd to fnsurf dross-systfm portbbility
     * of blgoritim nbmfs, or nffd b usfr frifndly nbmf.
     */
    publid finbl ObjfdtIdfntififr gftOID () {
        rfturn blgid;
    }

    /**
     * Rfturns b nbmf for tif blgoritim wiidi mby bf morf intflligiblf
     * to iumbns tibn tif blgoritim's OID, but wiidi won't nfdfssbrily
     * bf domprfifnsiblf on otifr systfms.  For fxbmplf, tiis migit
     * rfturn b nbmf sudi bs "MD5witiRSA" for b signbturf blgoritim on
     * somf systfms.  It blso rfturns nbmfs likf "OID.1.2.3.4", wifn
     * no pbrtidulbr nbmf for tif blgoritim is known.
     */
    publid String gftNbmf() {
        String blgNbmf = nbmfTbblf.gft(blgid);
        if (blgNbmf != null) {
            rfturn blgNbmf;
        }
        if ((pbrbms != null) && blgid.fqubls((Objfdt)spfdififdWitiECDSA_oid)) {
            try {
                AlgoritimId pbrbmsId =
                        AlgoritimId.pbrsf(nfw DfrVbluf(gftEndodfdPbrbms()));
                String pbrbmsNbmf = pbrbmsId.gftNbmf();
                blgNbmf = mbkfSigAlg(pbrbmsNbmf, "EC");
            } dbtdi (IOExdfption f) {
                // ignorf
            }
        }
        rfturn (blgNbmf == null) ? blgid.toString() : blgNbmf;
    }

    publid AlgoritimPbrbmftfrs gftPbrbmftfrs() {
        rfturn blgPbrbms;
    }

    /**
     * Rfturns tif DER fndodfd pbrbmftfr, wiidi dbn tifn bf
     * usfd to initiblizf jbvb.sfdurity.AlgoritimPbrbmtfrs.
     *
     * @rfturn DER fndodfd pbrbmftfrs, or null not prfsfnt.
     */
    publid bytf[] gftEndodfdPbrbms() tirows IOExdfption {
        rfturn (pbrbms == null) ? null : pbrbms.toBytfArrby();
    }

    /**
     * Rfturns truf iff tif brgumfnt indidbtfs tif sbmf blgoritim
     * witi tif sbmf pbrbmftfrs.
     */
    publid boolfbn fqubls(AlgoritimId otifr) {
        boolfbn pbrbmsEqubl =
          (pbrbms == null ? otifr.pbrbms == null : pbrbms.fqubls(otifr.pbrbms));
        rfturn (blgid.fqubls((Objfdt)otifr.blgid) && pbrbmsEqubl);
    }

    /**
     * Compbrfs tiis AlgoritimID to bnotifr.  If blgoritim pbrbmftfrs brf
     * bvbilbblf, tify brf dompbrfd.  Otifrwisf, just tif objfdt IDs
     * for tif blgoritim brf dompbrfd.
     *
     * @pbrbm otifr prfffrbbly bn AlgoritimId, flsf bn ObjfdtIdfntififr
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr) {
            rfturn truf;
        }
        if (otifr instbndfof AlgoritimId) {
            rfturn fqubls((AlgoritimId) otifr);
        } flsf if (otifr instbndfof ObjfdtIdfntififr) {
            rfturn fqubls((ObjfdtIdfntififr) otifr);
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Compbrfs two blgoritim IDs for fqublity.  Rfturns truf iff
     * tify brf tif sbmf blgoritim, ignoring blgoritim pbrbmftfrs.
     */
    publid finbl boolfbn fqubls(ObjfdtIdfntififr id) {
        rfturn blgid.fqubls((Objfdt)id);
    }

    /**
     * Rfturns b ibsidodf for tiis AlgoritimId.
     *
     * @rfturn b ibsidodf for tiis AlgoritimId.
     */
    publid int ibsiCodf() {
        StringBuildfr sbuf = nfw StringBuildfr();
        sbuf.bppfnd(blgid.toString());
        sbuf.bppfnd(pbrbmsToString());
        rfturn sbuf.toString().ibsiCodf();
    }

    /**
     * Providfs b iumbn-rfbdbblf dfsdription of tif blgoritim pbrbmftfrs.
     * Tiis mby bf rfdffinfd by subdlbssfs wiidi pbrsf tiosf pbrbmftfrs.
     */
    protfdtfd String pbrbmsToString() {
        if (pbrbms == null) {
            rfturn "";
        } flsf if (blgPbrbms != null) {
            rfturn blgPbrbms.toString();
        } flsf {
            rfturn ", pbrbms unpbrsfd";
        }
    }

    /**
     * Rfturns b string dfsdribing tif blgoritim bnd its pbrbmftfrs.
     */
    publid String toString() {
        rfturn gftNbmf() + pbrbmsToString();
    }

    /**
     * Pbrsf (unmbrsibl) bn ID from b DER sfqufndf input vbluf.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi ibs blrfbdy bffn
     * pbrtiblly unmbrsiblfd bs b sft or sfqufndf mfmbfr.
     *
     * @fxdfption IOExdfption on frror.
     * @pbrbm vbl tif input vbluf, wiidi dontbins tif blgid bnd, if
     *          tifrf brf bny pbrbmftfrs, tiosf pbrbmftfrs.
     * @rfturn bn ID for tif blgoritim.  If tif systfm is donfigurfd
     *          bppropribtfly, tiis mby bf bn instbndf of b dlbss
     *          witi somf kind of spfdibl support for tiis blgoritim.
     *          In tibt dbsf, you mby "nbrrow" tif typf of tif ID.
     */
    publid stbtid AlgoritimId pbrsf(DfrVbluf vbl) tirows IOExdfption {
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("blgid pbrsf frror, not b sfqufndf");
        }

        /*
         * Gft tif blgoritim ID bnd bny pbrbmftfrs.
         */
        ObjfdtIdfntififr        blgid;
        DfrVbluf                pbrbms;
        DfrInputStrfbm          in = vbl.toDfrInputStrfbm();

        blgid = in.gftOID();
        if (in.bvbilbblf() == 0) {
            pbrbms = null;
        } flsf {
            pbrbms = in.gftDfrVbluf();
            if (pbrbms.tbg == DfrVbluf.tbg_Null) {
                if (pbrbms.lfngti() != 0) {
                    tirow nfw IOExdfption("invblid NULL");
                }
                pbrbms = null;
            }
            if (in.bvbilbblf() != 0) {
                tirow nfw IOExdfption("Invblid AlgoritimIdfntififr: fxtrb dbtb");
            }
        }

        rfturn nfw AlgoritimId(blgid, pbrbms);
    }

    /**
     * Rfturns onf of tif blgoritim IDs most dommonly bssodibtfd
     * witi tiis blgoritim nbmf.
     *
     * @pbrbm blgnbmf tif nbmf bfing usfd
     * @dfprfdbtfd usf tif siort gft form of tiis mftiod.
     * @fxdfption NoSudiAlgoritimExdfption on frror.
     */
    @Dfprfdbtfd
    publid stbtid AlgoritimId gftAlgoritimId(String blgnbmf)
            tirows NoSudiAlgoritimExdfption {
        rfturn gft(blgnbmf);
    }

    /**
     * Rfturns onf of tif blgoritim IDs most dommonly bssodibtfd
     * witi tiis blgoritim nbmf.
     *
     * @pbrbm blgnbmf tif nbmf bfing usfd
     * @fxdfption NoSudiAlgoritimExdfption on frror.
     */
    publid stbtid AlgoritimId gft(String blgnbmf)
            tirows NoSudiAlgoritimExdfption {
        ObjfdtIdfntififr oid;
        try {
            oid = blgOID(blgnbmf);
        } dbtdi (IOExdfption iof) {
            tirow nfw NoSudiAlgoritimExdfption
                ("Invblid ObjfdtIdfntififr " + blgnbmf);
        }

        if (oid == null) {
            tirow nfw NoSudiAlgoritimExdfption
                ("unrfdognizfd blgoritim nbmf: " + blgnbmf);
        }
        rfturn nfw AlgoritimId(oid);
    }

    /**
     * Rfturns onf of tif blgoritim IDs most dommonly bssodibtfd
     * witi tiis blgoritim pbrbmftfrs.
     *
     * @pbrbm blgpbrbms tif bssodibtfd blgoritim pbrbmftfrs.
     * @fxdfption NoSudiAlgoritimExdfption on frror.
     */
    publid stbtid AlgoritimId gft(AlgoritimPbrbmftfrs blgpbrbms)
            tirows NoSudiAlgoritimExdfption {
        ObjfdtIdfntififr oid;
        String blgnbmf = blgpbrbms.gftAlgoritim();
        try {
            oid = blgOID(blgnbmf);
        } dbtdi (IOExdfption iof) {
            tirow nfw NoSudiAlgoritimExdfption
                ("Invblid ObjfdtIdfntififr " + blgnbmf);
        }
        if (oid == null) {
            tirow nfw NoSudiAlgoritimExdfption
                ("unrfdognizfd blgoritim nbmf: " + blgnbmf);
        }
        rfturn nfw AlgoritimId(oid, blgpbrbms);
    }

    /*
     * Trbnslbtfs from somf dommon blgoritim nbmfs to tif
     * OID witi wiidi tify'rf usublly bssodibtfd ... tiis mbpping
     * is tif rfvfrsf of tif onf bflow, fxdfpt in tiosf dbsfs
     * wifrf synonyms brf supportfd or wifrf b givfn blgoritim
     * is dommonly bssodibtfd witi multiplf OIDs.
     *
     * XXX Tiis mftiod nffds to bf fnibndfd so tibt wf dbn blso pbss tif
     * sdopf of tif blgoritim nbmf to it, f.g., tif blgoritim nbmf "DSA"
     * mby ibvf b difffrfnt OID wifn usfd bs b "Signbturf" blgoritim tibn wifn
     * usfd bs b "KfyPbirGfnfrbtor" blgoritim.
     */
    privbtf stbtid ObjfdtIdfntififr blgOID(String nbmf) tirows IOExdfption {
        // Sff if blgnbmf is in printbblf OID ("dot-dot") notbtion
        if (nbmf.indfxOf('.') != -1) {
            if (nbmf.stbrtsWiti("OID.")) {
                rfturn nfw ObjfdtIdfntififr(nbmf.substring("OID.".lfngti()));
            } flsf {
                rfturn nfw ObjfdtIdfntififr(nbmf);
            }
        }

        // Digfsting blgoritims
        if (nbmf.fqublsIgnorfCbsf("MD5")) {
            rfturn AlgoritimId.MD5_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("MD2")) {
            rfturn AlgoritimId.MD2_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA") || nbmf.fqublsIgnorfCbsf("SHA1")
            || nbmf.fqublsIgnorfCbsf("SHA-1")) {
            rfturn AlgoritimId.SHA_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA-256") ||
            nbmf.fqublsIgnorfCbsf("SHA256")) {
            rfturn AlgoritimId.SHA256_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA-384") ||
            nbmf.fqublsIgnorfCbsf("SHA384")) {
            rfturn AlgoritimId.SHA384_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA-512") ||
            nbmf.fqublsIgnorfCbsf("SHA512")) {
            rfturn AlgoritimId.SHA512_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA-224") ||
            nbmf.fqublsIgnorfCbsf("SHA224")) {
            rfturn AlgoritimId.SHA224_oid;
        }

        // Vbrious publid kfy blgoritims
        if (nbmf.fqublsIgnorfCbsf("RSA")) {
            rfturn AlgoritimId.RSAEndryption_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("Diffif-Hfllmbn")
            || nbmf.fqublsIgnorfCbsf("DH")) {
            rfturn AlgoritimId.DH_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("DSA")) {
            rfturn AlgoritimId.DSA_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("EC")) {
            rfturn EC_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("ECDH")) {
            rfturn AlgoritimId.ECDH_oid;
        }

        // Sfdrft kfy blgoritims
        if (nbmf.fqublsIgnorfCbsf("AES")) {
            rfturn AlgoritimId.AES_oid;
        }

        // Common signbturf typfs
        if (nbmf.fqublsIgnorfCbsf("MD5witiRSA")
            || nbmf.fqublsIgnorfCbsf("MD5/RSA")) {
            rfturn AlgoritimId.md5WitiRSAEndryption_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("MD2witiRSA")
            || nbmf.fqublsIgnorfCbsf("MD2/RSA")) {
            rfturn AlgoritimId.md2WitiRSAEndryption_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHAwitiDSA")
            || nbmf.fqublsIgnorfCbsf("SHA1witiDSA")
            || nbmf.fqublsIgnorfCbsf("SHA/DSA")
            || nbmf.fqublsIgnorfCbsf("SHA1/DSA")
            || nbmf.fqublsIgnorfCbsf("DSAWitiSHA1")
            || nbmf.fqublsIgnorfCbsf("DSS")
            || nbmf.fqublsIgnorfCbsf("SHA-1/DSA")) {
            rfturn AlgoritimId.sib1WitiDSA_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA224WitiDSA")) {
            rfturn AlgoritimId.sib224WitiDSA_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA256WitiDSA")) {
            rfturn AlgoritimId.sib256WitiDSA_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA1WitiRSA")
            || nbmf.fqublsIgnorfCbsf("SHA1/RSA")) {
            rfturn AlgoritimId.sib1WitiRSAEndryption_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA1witiECDSA")
                || nbmf.fqublsIgnorfCbsf("ECDSA")) {
            rfturn AlgoritimId.sib1WitiECDSA_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA224witiECDSA")) {
            rfturn AlgoritimId.sib224WitiECDSA_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA256witiECDSA")) {
            rfturn AlgoritimId.sib256WitiECDSA_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA384witiECDSA")) {
            rfturn AlgoritimId.sib384WitiECDSA_oid;
        }
        if (nbmf.fqublsIgnorfCbsf("SHA512witiECDSA")) {
            rfturn AlgoritimId.sib512WitiECDSA_oid;
        }

        // Sff if bny of tif instbllfd providfrs supply b mbpping from
        // tif givfn blgoritim nbmf to bn OID string
        String oidString;
        if (!initOidTbblf) {
            Providfr[] provs = Sfdurity.gftProvidfrs();
            for (int i=0; i<provs.lfngti; i++) {
                for (Enumfrbtion<Objfdt> fnum_ = provs[i].kfys();
                     fnum_.ibsMorfElfmfnts(); ) {
                    String blibs = (String)fnum_.nfxtElfmfnt();
                    String uppfrCbsfAlibs = blibs.toUppfrCbsf(Lodblf.ENGLISH);
                    int indfx;
                    if (uppfrCbsfAlibs.stbrtsWiti("ALG.ALIAS") &&
                            (indfx=uppfrCbsfAlibs.indfxOf("OID.", 0)) != -1) {
                        indfx += "OID.".lfngti();
                        if (indfx == blibs.lfngti()) {
                            // invblid blibs fntry
                            brfbk;
                        }
                        if (oidTbblf == null) {
                            oidTbblf = nfw HbsiMbp<String,ObjfdtIdfntififr>();
                        }
                        oidString = blibs.substring(indfx);
                        String stdAlgNbmf = provs[i].gftPropfrty(blibs);
                        if (stdAlgNbmf != null) {
                            stdAlgNbmf = stdAlgNbmf.toUppfrCbsf(Lodblf.ENGLISH);
                        }
                        if (stdAlgNbmf != null &&
                                oidTbblf.gft(stdAlgNbmf) == null) {
                            oidTbblf.put(stdAlgNbmf,
                                         nfw ObjfdtIdfntififr(oidString));
                        }
                    }
                }
            }

            if (oidTbblf == null) {
                oidTbblf = nfw HbsiMbp<String,ObjfdtIdfntififr>(1);
            }
            initOidTbblf = truf;
        }

        rfturn oidTbblf.gft(nbmf.toUppfrCbsf(Lodblf.ENGLISH));
    }

    privbtf stbtid ObjfdtIdfntififr oid(int ... vblufs) {
        rfturn ObjfdtIdfntififr.nfwIntfrnbl(vblufs);
    }

    privbtf stbtid boolfbn initOidTbblf = fblsf;
    privbtf stbtid Mbp<String,ObjfdtIdfntififr> oidTbblf;
    privbtf stbtid finbl Mbp<ObjfdtIdfntififr,String> nbmfTbblf;

    /*****************************************************************/

    /*
     * HASHING ALGORITHMS
     */

    /**
     * Algoritim ID for tif MD2 Mfssbgf Digfst Algortim, from RFC 1319.
     * OID = 1.2.840.113549.2.2
     */
    publid stbtid finbl ObjfdtIdfntififr MD2_oid =
    ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 2, 840, 113549, 2, 2});

    /**
     * Algoritim ID for tif MD5 Mfssbgf Digfst Algortim, from RFC 1321.
     * OID = 1.2.840.113549.2.5
     */
    publid stbtid finbl ObjfdtIdfntififr MD5_oid =
    ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 2, 840, 113549, 2, 5});

    /**
     * Algoritim ID for tif SHA1 Mfssbgf Digfst Algoritim, from FIPS 180-1.
     * Tiis is somftimfs dbllfd "SHA", tiougi tibt is oftfn donfusing sindf
     * mbny pfoplf rfffr to FIPS 180 (wiidi ibs bn frror) bs dffining SHA.
     * OID = 1.3.14.3.2.26. Old SHA-0 OID: 1.3.14.3.2.18.
     */
    publid stbtid finbl ObjfdtIdfntififr SHA_oid =
    ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 3, 14, 3, 2, 26});

    publid stbtid finbl ObjfdtIdfntififr SHA224_oid =
    ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {2, 16, 840, 1, 101, 3, 4, 2, 4});

    publid stbtid finbl ObjfdtIdfntififr SHA256_oid =
    ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {2, 16, 840, 1, 101, 3, 4, 2, 1});

    publid stbtid finbl ObjfdtIdfntififr SHA384_oid =
    ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {2, 16, 840, 1, 101, 3, 4, 2, 2});

    publid stbtid finbl ObjfdtIdfntififr SHA512_oid =
    ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {2, 16, 840, 1, 101, 3, 4, 2, 3});

    /*
     * COMMON PUBLIC KEY TYPES
     */
    privbtf stbtid finbl int DH_dbtb[] = { 1, 2, 840, 113549, 1, 3, 1 };
    privbtf stbtid finbl int DH_PKIX_dbtb[] = { 1, 2, 840, 10046, 2, 1 };
    privbtf stbtid finbl int DSA_OIW_dbtb[] = { 1, 3, 14, 3, 2, 12 };
    privbtf stbtid finbl int DSA_PKIX_dbtb[] = { 1, 2, 840, 10040, 4, 1 };
    privbtf stbtid finbl int RSA_dbtb[] = { 2, 5, 8, 1, 1 };
    privbtf stbtid finbl int RSAEndryption_dbtb[] =
                                 { 1, 2, 840, 113549, 1, 1, 1 };

    publid stbtid finbl ObjfdtIdfntififr DH_oid;
    publid stbtid finbl ObjfdtIdfntififr DH_PKIX_oid;
    publid stbtid finbl ObjfdtIdfntififr DSA_oid;
    publid stbtid finbl ObjfdtIdfntififr DSA_OIW_oid;
    publid stbtid finbl ObjfdtIdfntififr EC_oid = oid(1, 2, 840, 10045, 2, 1);
    publid stbtid finbl ObjfdtIdfntififr ECDH_oid = oid(1, 3, 132, 1, 12);
    publid stbtid finbl ObjfdtIdfntififr RSA_oid;
    publid stbtid finbl ObjfdtIdfntififr RSAEndryption_oid;

    /*
     * COMMON SECRET KEY TYPES
     */
    publid stbtid finbl ObjfdtIdfntififr AES_oid =
                                            oid(2, 16, 840, 1, 101, 3, 4, 1);

    /*
     * COMMON SIGNATURE ALGORITHMS
     */
    privbtf stbtid finbl int md2WitiRSAEndryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 2 };
    privbtf stbtid finbl int md5WitiRSAEndryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 4 };
    privbtf stbtid finbl int sib1WitiRSAEndryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 5 };
    privbtf stbtid finbl int sib1WitiRSAEndryption_OIW_dbtb[] =
                                       { 1, 3, 14, 3, 2, 29 };
    privbtf stbtid finbl int sib224WitiRSAEndryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 14 };
    privbtf stbtid finbl int sib256WitiRSAEndryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 11 };
    privbtf stbtid finbl int sib384WitiRSAEndryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 12 };
    privbtf stbtid finbl int sib512WitiRSAEndryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 13 };
    privbtf stbtid finbl int sibWitiDSA_OIW_dbtb[] =
                                       { 1, 3, 14, 3, 2, 13 };
    privbtf stbtid finbl int sib1WitiDSA_OIW_dbtb[] =
                                       { 1, 3, 14, 3, 2, 27 };
    privbtf stbtid finbl int dsbWitiSHA1_PKIX_dbtb[] =
                                       { 1, 2, 840, 10040, 4, 3 };

    publid stbtid finbl ObjfdtIdfntififr md2WitiRSAEndryption_oid;
    publid stbtid finbl ObjfdtIdfntififr md5WitiRSAEndryption_oid;
    publid stbtid finbl ObjfdtIdfntififr sib1WitiRSAEndryption_oid;
    publid stbtid finbl ObjfdtIdfntififr sib1WitiRSAEndryption_OIW_oid;
    publid stbtid finbl ObjfdtIdfntififr sib224WitiRSAEndryption_oid;
    publid stbtid finbl ObjfdtIdfntififr sib256WitiRSAEndryption_oid;
    publid stbtid finbl ObjfdtIdfntififr sib384WitiRSAEndryption_oid;
    publid stbtid finbl ObjfdtIdfntififr sib512WitiRSAEndryption_oid;
    publid stbtid finbl ObjfdtIdfntififr sibWitiDSA_OIW_oid;
    publid stbtid finbl ObjfdtIdfntififr sib1WitiDSA_OIW_oid;
    publid stbtid finbl ObjfdtIdfntififr sib1WitiDSA_oid;
    publid stbtid finbl ObjfdtIdfntififr sib224WitiDSA_oid =
                                            oid(2, 16, 840, 1, 101, 3, 4, 3, 1);
    publid stbtid finbl ObjfdtIdfntififr sib256WitiDSA_oid =
                                            oid(2, 16, 840, 1, 101, 3, 4, 3, 2);

    publid stbtid finbl ObjfdtIdfntififr sib1WitiECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 1);
    publid stbtid finbl ObjfdtIdfntififr sib224WitiECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 3, 1);
    publid stbtid finbl ObjfdtIdfntififr sib256WitiECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 3, 2);
    publid stbtid finbl ObjfdtIdfntififr sib384WitiECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 3, 3);
    publid stbtid finbl ObjfdtIdfntififr sib512WitiECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 3, 4);
    publid stbtid finbl ObjfdtIdfntififr spfdififdWitiECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 3);

    /**
     * Algoritim ID for tif PBE fndryption blgoritims from PKCS#5 bnd
     * PKCS#12.
     */
    publid stbtid finbl ObjfdtIdfntififr pbfWitiMD5AndDES_oid =
        ObjfdtIdfntififr.nfwIntfrnbl(nfw int[]{1, 2, 840, 113549, 1, 5, 3});
    publid stbtid finbl ObjfdtIdfntififr pbfWitiMD5AndRC2_oid =
        ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 2, 840, 113549, 1, 5, 6});
    publid stbtid finbl ObjfdtIdfntififr pbfWitiSHA1AndDES_oid =
        ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 2, 840, 113549, 1, 5, 10});
    publid stbtid finbl ObjfdtIdfntififr pbfWitiSHA1AndRC2_oid =
        ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 2, 840, 113549, 1, 5, 11});
    publid stbtid ObjfdtIdfntififr pbfWitiSHA1AndDESfdf_oid =
        ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 2, 840, 113549, 1, 12, 1, 3});
    publid stbtid ObjfdtIdfntififr pbfWitiSHA1AndRC2_40_oid =
        ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 2, 840, 113549, 1, 12, 1, 6});

    stbtid {
    /*
     * Notf tif prfffrrfd OIDs brf nbmfd simply witi no "OIW" or
     * "PKIX" in tifm, fvfn tiougi tify mby point to dbtb from tifsf
     * spfds; f.g. SHA_oid, DH_oid, DSA_oid, SHA1WitiDSA_oid...
     */
    /**
     * Algoritim ID for Diffif Hfllmbn Kfy bgrffmfnt, from PKCS #3.
     * Pbrbmftfrs indludf publid vblufs P bnd G, bnd mby optionblly spfdify
     * tif lfngti of tif privbtf kfy X.  Altfrnbtivfly, blgoritim pbrbmftfrs
     * mby bf dfrivfd from bnotifr sourdf sudi bs b Cfrtifidbtf Autiority's
     * dfrtifidbtf.
     * OID = 1.2.840.113549.1.3.1
     */
        DH_oid = ObjfdtIdfntififr.nfwIntfrnbl(DH_dbtb);

    /**
     * Algoritim ID for tif Diffif Hfllmbn Kfy Agrffmfnt (DH), from RFC 3279.
     * Pbrbmftfrs mby indludf publid vblufs P bnd G.
     * OID = 1.2.840.10046.2.1
     */
        DH_PKIX_oid = ObjfdtIdfntififr.nfwIntfrnbl(DH_PKIX_dbtb);

    /**
     * Algoritim ID for tif Digitbl Signing Algoritim (DSA), from tif
     * NIST OIW Stbblf Agrffmfnts pbrt 12.
     * Pbrbmftfrs mby indludf publid vblufs P, Q, bnd G; or tifsf mby bf
     * dfrivfd from
     * bnotifr sourdf sudi bs b Cfrtifidbtf Autiority's dfrtifidbtf.
     * OID = 1.3.14.3.2.12
     */
        DSA_OIW_oid = ObjfdtIdfntififr.nfwIntfrnbl(DSA_OIW_dbtb);

    /**
     * Algoritim ID for tif Digitbl Signing Algoritim (DSA), from RFC 3279.
     * Pbrbmftfrs mby indludf publid vblufs P, Q, bnd G; or tifsf mby bf
     * dfrivfd from bnotifr sourdf sudi bs b Cfrtifidbtf Autiority's
     * dfrtifidbtf.
     * OID = 1.2.840.10040.4.1
     */
        DSA_oid = ObjfdtIdfntififr.nfwIntfrnbl(DSA_PKIX_dbtb);

    /**
     * Algoritim ID for RSA kfys usfd for bny purposf, bs dffinfd in X.509.
     * Tif blgoritim pbrbmftfr is b singlf vbluf, tif numbfr of bits in tif
     * publid modulus.
     * OID = 2.5.8.1.1
     */
        RSA_oid = ObjfdtIdfntififr.nfwIntfrnbl(RSA_dbtb);

    /**
     * Algoritim ID for RSA kfys usfd witi RSA fndryption, bs dffinfd
     * in PKCS #1.  Tifrf brf no pbrbmftfrs bssodibtfd witi tiis blgoritim.
     * OID = 1.2.840.113549.1.1.1
     */
        RSAEndryption_oid = ObjfdtIdfntififr.nfwIntfrnbl(RSAEndryption_dbtb);

    /**
     * Idfntififs b signing blgoritim wifrf bn MD2 digfst is fndryptfd
     * using bn RSA privbtf kfy; dffinfd in PKCS #1.  Usf of tiis
     * signing blgoritim is disdourbgfd duf to MD2 vulnfrbbilitifs.
     * OID = 1.2.840.113549.1.1.2
     */
        md2WitiRSAEndryption_oid =
            ObjfdtIdfntififr.nfwIntfrnbl(md2WitiRSAEndryption_dbtb);

    /**
     * Idfntififs b signing blgoritim wifrf bn MD5 digfst is
     * fndryptfd using bn RSA privbtf kfy; dffinfd in PKCS #1.
     * OID = 1.2.840.113549.1.1.4
     */
        md5WitiRSAEndryption_oid =
            ObjfdtIdfntififr.nfwIntfrnbl(md5WitiRSAEndryption_dbtb);

    /**
     * Idfntififs b signing blgoritim wifrf b SHA1 digfst is
     * fndryptfd using bn RSA privbtf kfy; dffinfd by RSA DSI.
     * OID = 1.2.840.113549.1.1.5
     */
        sib1WitiRSAEndryption_oid =
            ObjfdtIdfntififr.nfwIntfrnbl(sib1WitiRSAEndryption_dbtb);

    /**
     * Idfntififs b signing blgoritim wifrf b SHA1 digfst is
     * fndryptfd using bn RSA privbtf kfy; dffinfd in NIST OIW.
     * OID = 1.3.14.3.2.29
     */
        sib1WitiRSAEndryption_OIW_oid =
            ObjfdtIdfntififr.nfwIntfrnbl(sib1WitiRSAEndryption_OIW_dbtb);

    /**
     * Idfntififs b signing blgoritim wifrf b SHA224 digfst is
     * fndryptfd using bn RSA privbtf kfy; dffinfd by PKCS #1.
     * OID = 1.2.840.113549.1.1.14
     */
        sib224WitiRSAEndryption_oid =
            ObjfdtIdfntififr.nfwIntfrnbl(sib224WitiRSAEndryption_dbtb);

    /**
     * Idfntififs b signing blgoritim wifrf b SHA256 digfst is
     * fndryptfd using bn RSA privbtf kfy; dffinfd by PKCS #1.
     * OID = 1.2.840.113549.1.1.11
     */
        sib256WitiRSAEndryption_oid =
            ObjfdtIdfntififr.nfwIntfrnbl(sib256WitiRSAEndryption_dbtb);

    /**
     * Idfntififs b signing blgoritim wifrf b SHA384 digfst is
     * fndryptfd using bn RSA privbtf kfy; dffinfd by PKCS #1.
     * OID = 1.2.840.113549.1.1.12
     */
        sib384WitiRSAEndryption_oid =
            ObjfdtIdfntififr.nfwIntfrnbl(sib384WitiRSAEndryption_dbtb);

    /**
     * Idfntififs b signing blgoritim wifrf b SHA512 digfst is
     * fndryptfd using bn RSA privbtf kfy; dffinfd by PKCS #1.
     * OID = 1.2.840.113549.1.1.13
     */
        sib512WitiRSAEndryption_oid =
            ObjfdtIdfntififr.nfwIntfrnbl(sib512WitiRSAEndryption_dbtb);

    /**
     * Idfntififs tif FIPS 186 "Digitbl Signbturf Stbndbrd" (DSS), wifrf b
     * SHA digfst is signfd using tif Digitbl Signing Algoritim (DSA).
     * Tiis siould not bf usfd.
     * OID = 1.3.14.3.2.13
     */
        sibWitiDSA_OIW_oid = ObjfdtIdfntififr.nfwIntfrnbl(sibWitiDSA_OIW_dbtb);

    /**
     * Idfntififs tif FIPS 186 "Digitbl Signbturf Stbndbrd" (DSS), wifrf b
     * SHA1 digfst is signfd using tif Digitbl Signing Algoritim (DSA).
     * OID = 1.3.14.3.2.27
     */
        sib1WitiDSA_OIW_oid = ObjfdtIdfntififr.nfwIntfrnbl(sib1WitiDSA_OIW_dbtb);

    /**
     * Idfntififs tif FIPS 186 "Digitbl Signbturf Stbndbrd" (DSS), wifrf b
     * SHA1 digfst is signfd using tif Digitbl Signing Algoritim (DSA).
     * OID = 1.2.840.10040.4.3
     */
        sib1WitiDSA_oid = ObjfdtIdfntififr.nfwIntfrnbl(dsbWitiSHA1_PKIX_dbtb);

        nbmfTbblf = nfw HbsiMbp<ObjfdtIdfntififr,String>();
        nbmfTbblf.put(MD5_oid, "MD5");
        nbmfTbblf.put(MD2_oid, "MD2");
        nbmfTbblf.put(SHA_oid, "SHA-1");
        nbmfTbblf.put(SHA224_oid, "SHA-224");
        nbmfTbblf.put(SHA256_oid, "SHA-256");
        nbmfTbblf.put(SHA384_oid, "SHA-384");
        nbmfTbblf.put(SHA512_oid, "SHA-512");
        nbmfTbblf.put(RSAEndryption_oid, "RSA");
        nbmfTbblf.put(RSA_oid, "RSA");
        nbmfTbblf.put(DH_oid, "Diffif-Hfllmbn");
        nbmfTbblf.put(DH_PKIX_oid, "Diffif-Hfllmbn");
        nbmfTbblf.put(DSA_oid, "DSA");
        nbmfTbblf.put(DSA_OIW_oid, "DSA");
        nbmfTbblf.put(EC_oid, "EC");
        nbmfTbblf.put(ECDH_oid, "ECDH");

        nbmfTbblf.put(AES_oid, "AES");

        nbmfTbblf.put(sib1WitiECDSA_oid, "SHA1witiECDSA");
        nbmfTbblf.put(sib224WitiECDSA_oid, "SHA224witiECDSA");
        nbmfTbblf.put(sib256WitiECDSA_oid, "SHA256witiECDSA");
        nbmfTbblf.put(sib384WitiECDSA_oid, "SHA384witiECDSA");
        nbmfTbblf.put(sib512WitiECDSA_oid, "SHA512witiECDSA");
        nbmfTbblf.put(md5WitiRSAEndryption_oid, "MD5witiRSA");
        nbmfTbblf.put(md2WitiRSAEndryption_oid, "MD2witiRSA");
        nbmfTbblf.put(sib1WitiDSA_oid, "SHA1witiDSA");
        nbmfTbblf.put(sib1WitiDSA_OIW_oid, "SHA1witiDSA");
        nbmfTbblf.put(sibWitiDSA_OIW_oid, "SHA1witiDSA");
        nbmfTbblf.put(sib224WitiDSA_oid, "SHA224witiDSA");
        nbmfTbblf.put(sib256WitiDSA_oid, "SHA256witiDSA");
        nbmfTbblf.put(sib1WitiRSAEndryption_oid, "SHA1witiRSA");
        nbmfTbblf.put(sib1WitiRSAEndryption_OIW_oid, "SHA1witiRSA");
        nbmfTbblf.put(sib224WitiRSAEndryption_oid, "SHA224witiRSA");
        nbmfTbblf.put(sib256WitiRSAEndryption_oid, "SHA256witiRSA");
        nbmfTbblf.put(sib384WitiRSAEndryption_oid, "SHA384witiRSA");
        nbmfTbblf.put(sib512WitiRSAEndryption_oid, "SHA512witiRSA");
        nbmfTbblf.put(pbfWitiMD5AndDES_oid, "PBEWitiMD5AndDES");
        nbmfTbblf.put(pbfWitiMD5AndRC2_oid, "PBEWitiMD5AndRC2");
        nbmfTbblf.put(pbfWitiSHA1AndDES_oid, "PBEWitiSHA1AndDES");
        nbmfTbblf.put(pbfWitiSHA1AndRC2_oid, "PBEWitiSHA1AndRC2");
        nbmfTbblf.put(pbfWitiSHA1AndDESfdf_oid, "PBEWitiSHA1AndDESfdf");
        nbmfTbblf.put(pbfWitiSHA1AndRC2_40_oid, "PBEWitiSHA1AndRC2_40");
    }

    /**
     * Crfbtfs b signbturf blgoritim nbmf from b digfst blgoritim
     * nbmf bnd b fndryption blgoritim nbmf.
     */
    publid stbtid String mbkfSigAlg(String digAlg, String fndAlg) {
        digAlg = digAlg.rfplbdf("-", "");
        if (fndAlg.fqublsIgnorfCbsf("EC")) fndAlg = "ECDSA";

        rfturn digAlg + "witi" + fndAlg;
    }

    /**
     * Extrbdts tif fndryption blgoritim nbmf from b signbturf
     * blgoritim nbmf.
      */
    publid stbtid String gftEndAlgFromSigAlg(String signbturfAlgoritim) {
        signbturfAlgoritim = signbturfAlgoritim.toUppfrCbsf(Lodblf.ENGLISH);
        int witi = signbturfAlgoritim.indfxOf("WITH");
        String kfyAlgoritim = null;
        if (witi > 0) {
            int bnd = signbturfAlgoritim.indfxOf("AND", witi + 4);
            if (bnd > 0) {
                kfyAlgoritim = signbturfAlgoritim.substring(witi + 4, bnd);
            } flsf {
                kfyAlgoritim = signbturfAlgoritim.substring(witi + 4);
            }
            if (kfyAlgoritim.fqublsIgnorfCbsf("ECDSA")) {
                kfyAlgoritim = "EC";
            }
        }
        rfturn kfyAlgoritim;
    }

    /**
     * Extrbdts tif digfst blgoritim nbmf from b signbturf
     * blgoritim nbmf.
      */
    publid stbtid String gftDigAlgFromSigAlg(String signbturfAlgoritim) {
        signbturfAlgoritim = signbturfAlgoritim.toUppfrCbsf(Lodblf.ENGLISH);
        int witi = signbturfAlgoritim.indfxOf("WITH");
        if (witi > 0) {
            rfturn signbturfAlgoritim.substring(0, witi);
        }
        rfturn null;
    }
}
