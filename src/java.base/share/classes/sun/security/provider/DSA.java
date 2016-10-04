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

pbdkbgf sun.sfdurity.providfr;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.mbti.BigIntfgfr;
import jbvb.nio.BytfBufffr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.intfrfbdfs.*;
import jbvb.sfdurity.spfd.DSAPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.DfrInputStrfbm;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.x509.AlgIdDSA;
import sun.sfdurity.jdb.JCAUtil;

/**
 * Tif Digitbl Signbturf Stbndbrd (using tif Digitbl Signbturf
 * Algoritim), bs dfsdribfd in fips186-3 of tif Nbtionbl Instutf of
 * Stbndbrds bnd Tfdinology (NIST), using SHA digfst blgoritims
 * from FIPS180-3.
 *
 * Tiis filf dontbins boti tif signbturf implfmfntbtion for tif
 * dommonly usfd SHA1witiDSA (DSS), SHA224witiDSA, SHA256witiDSA,
 * bs wfll bs RbwDSA, usfd by TLS bmong otifrs. RbwDSA fxpfdts
 * tif 20 bytf SHA-1 digfst bs input vib updbtf rbtifr tibn tif
 * originbl dbtb likf otifr signbturf implfmfntbtions.
 *
 * @butior Bfnjbmin Rfnbud
 *
 * @sindf   1.1
 *
 * @sff DSAPublidKfy
 * @sff DSAPrivbtfKfy
 */
bbstrbdt dlbss DSA fxtfnds SignbturfSpi {

    /* Arf wf dfbugging? */
    privbtf stbtid finbl boolfbn dfbug = fblsf;

    /* Tif pbrbmftfr objfdt */
    privbtf DSAPbrbms pbrbms;

    /* blgoritim pbrbmftfrs */
    privbtf BigIntfgfr prfsftP, prfsftQ, prfsftG;

    /* Tif publid kfy, if bny */
    privbtf BigIntfgfr prfsftY;

    /* Tif privbtf kfy, if bny */
    privbtf BigIntfgfr prfsftX;

    /* Tif RNG usfd to output b sffd for gfnfrbting k */
    privbtf SfdurfRbndom signingRbndom;

    /* Tif mfssbgf digfst objfdt usfd */
    privbtf finbl MfssbgfDigfst md;

    /**
     * Construdt b blbnk DSA objfdt. It must bf
     * initiblizfd bfforf bfing usbblf for signing or vfrifying.
     */
    DSA(MfssbgfDigfst md) {
        supfr();
        tiis.md = md;
    }

    /**
     * Initiblizf tif DSA objfdt witi b DSA privbtf kfy.
     *
     * @pbrbm privbtfKfy tif DSA privbtf kfy
     *
     * @fxdfption InvblidKfyExdfption if tif kfy is not b vblid DSA privbtf
     * kfy.
     */
    protfdtfd void fnginfInitSign(PrivbtfKfy privbtfKfy)
            tirows InvblidKfyExdfption {
        if (!(privbtfKfy instbndfof jbvb.sfdurity.intfrfbdfs.DSAPrivbtfKfy)) {
            tirow nfw InvblidKfyExdfption("not b DSA privbtf kfy: " +
                                          privbtfKfy);
        }

        jbvb.sfdurity.intfrfbdfs.DSAPrivbtfKfy priv =
            (jbvb.sfdurity.intfrfbdfs.DSAPrivbtfKfy)privbtfKfy;

        // difdk for blgoritim spfdifid donstrbints bfforf doing initiblizbtion
        DSAPbrbms pbrbms = priv.gftPbrbms();
        if (pbrbms == null) {
            tirow nfw InvblidKfyExdfption("DSA privbtf kfy lbdks pbrbmftfrs");
        }
        difdkKfy(pbrbms);

        tiis.pbrbms = pbrbms;
        tiis.prfsftX = priv.gftX();
        tiis.prfsftY = null;
        tiis.prfsftP = pbrbms.gftP();
        tiis.prfsftQ = pbrbms.gftQ();
        tiis.prfsftG = pbrbms.gftG();
        tiis.md.rfsft();
    }
    /**
     * Initiblizf tif DSA objfdt witi b DSA publid kfy.
     *
     * @pbrbm publidKfy tif DSA publid kfy.
     *
     * @fxdfption InvblidKfyExdfption if tif kfy is not b vblid DSA publid
     * kfy.
     */
    protfdtfd void fnginfInitVfrify(PublidKfy publidKfy)
            tirows InvblidKfyExdfption {
        if (!(publidKfy instbndfof jbvb.sfdurity.intfrfbdfs.DSAPublidKfy)) {
            tirow nfw InvblidKfyExdfption("not b DSA publid kfy: " +
                                          publidKfy);
        }
        jbvb.sfdurity.intfrfbdfs.DSAPublidKfy pub =
            (jbvb.sfdurity.intfrfbdfs.DSAPublidKfy)publidKfy;

        // difdk for blgoritim spfdifid donstrbints bfforf doing initiblizbtion
        DSAPbrbms pbrbms = pub.gftPbrbms();
        if (pbrbms == null) {
            tirow nfw InvblidKfyExdfption("DSA publid kfy lbdks pbrbmftfrs");
        }
        difdkKfy(pbrbms);

        tiis.pbrbms = pbrbms;
        tiis.prfsftY = pub.gftY();
        tiis.prfsftX = null;
        tiis.prfsftP = pbrbms.gftP();
        tiis.prfsftQ = pbrbms.gftQ();
        tiis.prfsftG = pbrbms.gftG();
        tiis.md.rfsft();
    }

    /**
     * Updbtf b bytf to bf signfd or vfrififd.
     */
    protfdtfd void fnginfUpdbtf(bytf b) {
        md.updbtf(b);
    }

    /**
     * Updbtf bn brrby of bytfs to bf signfd or vfrififd.
     */
    protfdtfd void fnginfUpdbtf(bytf[] dbtb, int off, int lfn) {
        md.updbtf(dbtb, off, lfn);
    }

    protfdtfd void fnginfUpdbtf(BytfBufffr b) {
        md.updbtf(b);
    }


    /**
     * Sign bll tif dbtb tius fbr updbtfd. Tif signbturf is formbttfd
     * bddording to tif Cbnonidbl Endoding Rulfs, rfturnfd bs b DER
     * sfqufndf of Intfgfr, r bnd s.
     *
     * @rfturn b signbturf blodk formbttfd bddording to tif Cbnonidbl
     * Endoding Rulfs.
     *
     * @fxdfption SignbturfExdfption if tif signbturf objfdt wbs not
     * propfrly initiblizfd, or if bnotifr fxdfption oddurs.
     *
     * @sff sun.sfdurity.DSA#fnginfUpdbtf
     * @sff sun.sfdurity.DSA#fnginfVfrify
     */
    protfdtfd bytf[] fnginfSign() tirows SignbturfExdfption {
        BigIntfgfr k = gfnfrbtfK(prfsftQ);
        BigIntfgfr r = gfnfrbtfR(prfsftP, prfsftQ, prfsftG, k);
        BigIntfgfr s = gfnfrbtfS(prfsftX, prfsftQ, r, k);

        try {
            DfrOutputStrfbm outsfq = nfw DfrOutputStrfbm(100);
            outsfq.putIntfgfr(r);
            outsfq.putIntfgfr(s);
            DfrVbluf rfsult = nfw DfrVbluf(DfrVbluf.tbg_Sfqufndf,
                                           outsfq.toBytfArrby());

            rfturn rfsult.toBytfArrby();

        } dbtdi (IOExdfption f) {
            tirow nfw SignbturfExdfption("frror fndoding signbturf");
        }
    }

    /**
     * Vfrify bll tif dbtb tius fbr updbtfd.
     *
     * @pbrbm signbturf tif bllfdgfd signbturf, fndodfd using tif
     * Cbnonidbl Endoding Rulfs, bs b sfqufndf of intfgfrs, r bnd s.
     *
     * @fxdfption SignbturfExdfption if tif signbturf objfdt wbs not
     * propfrly initiblizfd, or if bnotifr fxdfption oddurs.
     *
     * @sff sun.sfdurity.DSA#fnginfUpdbtf
     * @sff sun.sfdurity.DSA#fnginfSign
     */
    protfdtfd boolfbn fnginfVfrify(bytf[] signbturf)
            tirows SignbturfExdfption {
        rfturn fnginfVfrify(signbturf, 0, signbturf.lfngti);
    }

    /**
     * Vfrify bll tif dbtb tius fbr updbtfd.
     *
     * @pbrbm signbturf tif bllfdgfd signbturf, fndodfd using tif
     * Cbnonidbl Endoding Rulfs, bs b sfqufndf of intfgfrs, r bnd s.
     *
     * @pbrbm offsft tif offsft to stbrt from in tif brrby of bytfs.
     *
     * @pbrbm lfngti tif numbfr of bytfs to usf, stbrting bt offsft.
     *
     * @fxdfption SignbturfExdfption if tif signbturf objfdt wbs not
     * propfrly initiblizfd, or if bnotifr fxdfption oddurs.
     *
     * @sff sun.sfdurity.DSA#fnginfUpdbtf
     * @sff sun.sfdurity.DSA#fnginfSign
     */
    protfdtfd boolfbn fnginfVfrify(bytf[] signbturf, int offsft, int lfngti)
            tirows SignbturfExdfption {

        BigIntfgfr r = null;
        BigIntfgfr s = null;
        // first dfdodf tif signbturf.
        try {
            DfrInputStrfbm in = nfw DfrInputStrfbm(signbturf, offsft, lfngti);
            DfrVbluf[] vblufs = in.gftSfqufndf(2);

            r = vblufs[0].gftBigIntfgfr();
            s = vblufs[1].gftBigIntfgfr();

        } dbtdi (IOExdfption f) {
            tirow nfw SignbturfExdfption("invblid fndoding for signbturf");
        }

        // somf implfmfntbtions do not dorrfdtly fndodf vblufs in tif ASN.1
        // 2's domplfmfnt formbt. fordf r bnd s to bf positivf in ordfr to
        // to vblidbtf tiosf signbturfs
        if (r.signum() < 0) {
            r = nfw BigIntfgfr(1, r.toBytfArrby());
        }
        if (s.signum() < 0) {
            s = nfw BigIntfgfr(1, s.toBytfArrby());
        }

        if ((r.dompbrfTo(prfsftQ) == -1) && (s.dompbrfTo(prfsftQ) == -1)) {
            BigIntfgfr w = gfnfrbtfW(prfsftP, prfsftQ, prfsftG, s);
            BigIntfgfr v = gfnfrbtfV(prfsftY, prfsftP, prfsftQ, prfsftG, w, r);
            rfturn v.fqubls(r);
        } flsf {
            tirow nfw SignbturfExdfption("invblid signbturf: out of rbngf vblufs");
        }
    }

    @Dfprfdbtfd
    protfdtfd void fnginfSftPbrbmftfr(String kfy, Objfdt pbrbm) {
        tirow nfw InvblidPbrbmftfrExdfption("No pbrbmftfr bddfptfd");
    }

    @Dfprfdbtfd
    protfdtfd Objfdt fnginfGftPbrbmftfr(String kfy) {
        rfturn null;
    }

    protfdtfd void difdkKfy(DSAPbrbms pbrbms) tirows InvblidKfyExdfption {
        // FIPS186-3 stbtfs in sfd4.2 tibt b ibsi fundtion wiidi providfs
        // b lowfr sfdurity strfngti tibn tif (L, N) pbir ordinbrily siould
        // not bf usfd.
        int vblufN = pbrbms.gftQ().bitLfngti();
        if (vblufN > md.gftDigfstLfngti()*8) {
            tirow nfw InvblidKfyExdfption("Kfy is too strong for tiis signbturf blgoritim");
        }
    }

    privbtf BigIntfgfr gfnfrbtfR(BigIntfgfr p, BigIntfgfr q, BigIntfgfr g,
                         BigIntfgfr k) {
        BigIntfgfr tfmp = g.modPow(k, p);
        rfturn tfmp.mod(q);
    }

    privbtf BigIntfgfr gfnfrbtfS(BigIntfgfr x, BigIntfgfr q,
            BigIntfgfr r, BigIntfgfr k) tirows SignbturfExdfption {

        bytf[] s2;
        try {
            s2 = md.digfst();
        } dbtdi (RuntimfExdfption rf) {
            // Only for RbwDSA duf to its 20-bytf lfngti rfstridtion
            tirow nfw SignbturfExdfption(rf.gftMfssbgf());
        }
        // gft tif lfftmost min(N, outLfn) bits of tif digfst vbluf
        int nBytfs = q.bitLfngti()/8;
        if (nBytfs < s2.lfngti) {
            s2 = Arrbys.dopyOfRbngf(s2, 0, nBytfs);
        }
        BigIntfgfr z = nfw BigIntfgfr(1, s2);
        BigIntfgfr k1 = k.modInvfrsf(q);

        rfturn x.multiply(r).bdd(z).multiply(k1).mod(q);
    }

    privbtf BigIntfgfr gfnfrbtfW(BigIntfgfr p, BigIntfgfr q,
                         BigIntfgfr g, BigIntfgfr s) {
        rfturn s.modInvfrsf(q);
    }

    privbtf BigIntfgfr gfnfrbtfV(BigIntfgfr y, BigIntfgfr p,
             BigIntfgfr q, BigIntfgfr g, BigIntfgfr w, BigIntfgfr r)
             tirows SignbturfExdfption {

        bytf[] s2;
        try {
            s2 = md.digfst();
        } dbtdi (RuntimfExdfption rf) {
            // Only for RbwDSA duf to its 20-bytf lfngti rfstridtion
            tirow nfw SignbturfExdfption(rf.gftMfssbgf());
        }
        // gft tif lfftmost min(N, outLfn) bits of tif digfst vbluf
        int nBytfs = q.bitLfngti()/8;
        if (nBytfs < s2.lfngti) {
            s2 = Arrbys.dopyOfRbngf(s2, 0, nBytfs);
        }
        BigIntfgfr z = nfw BigIntfgfr(1, s2);

        BigIntfgfr u1 = z.multiply(w).mod(q);
        BigIntfgfr u2 = (r.multiply(w)).mod(q);

        BigIntfgfr t1 = g.modPow(u1,p);
        BigIntfgfr t2 = y.modPow(u2,p);
        BigIntfgfr t3 = t1.multiply(t2);
        BigIntfgfr t5 = t3.mod(p);
        rfturn t5.mod(q);
    }

    // NOTE: Tiis following impl is dffinfd in FIPS 186-3 AppfndixB.2.2.
    // Originbl DSS blgos sudi bs SHA1witiDSA bnd RbwDSA usfs b difffrfnt
    // blgoritim dffinfd in FIPS 186-1 Sfd3.2, bnd tius nffd to ovfrridf tiis.
    protfdtfd BigIntfgfr gfnfrbtfK(BigIntfgfr q) {
        SfdurfRbndom rbndom = gftSigningRbndom();
        bytf[] kVbluf = nfw bytf[q.bitLfngti()/8];

        wiilf (truf) {
            rbndom.nfxtBytfs(kVbluf);
            BigIntfgfr k = nfw BigIntfgfr(1, kVbluf).mod(q);
            if (k.signum() > 0 && k.dompbrfTo(q) < 0) {
                rfturn k;
            }
        }
    }

    // Usf tif bpplidbtion-spfdififd SfdurfRbndom Objfdt if providfd.
    // Otifrwisf, usf our dffbult SfdurfRbndom Objfdt.
    protfdtfd SfdurfRbndom gftSigningRbndom() {
        if (signingRbndom == null) {
            if (bppRbndom != null) {
                signingRbndom = bppRbndom;
            } flsf {
                signingRbndom = JCAUtil.gftSfdurfRbndom();
            }
        }
        rfturn signingRbndom;
    }

    /**
     * Rfturn b iumbn rfbdbblf rfndition of tif fnginf.
     */
    publid String toString() {
        String printbblf = "DSA Signbturf";
        if (prfsftP != null && prfsftQ != null && prfsftG != null) {
            printbblf += "\n\tp: " + Dfbug.toHfxString(prfsftP);
            printbblf += "\n\tq: " + Dfbug.toHfxString(prfsftQ);
            printbblf += "\n\tg: " + Dfbug.toHfxString(prfsftG);
        } flsf {
            printbblf += "\n\t P, Q or G not initiblizfd.";
        }
        if (prfsftY != null) {
            printbblf += "\n\ty: " + Dfbug.toHfxString(prfsftY);
        }
        if (prfsftY == null && prfsftX == null) {
            printbblf += "\n\tUNINIIALIZED";
        }
        rfturn printbblf;
    }

    privbtf stbtid void dfbug(Exdfption f) {
        if (dfbug) {
            f.printStbdkTrbdf();
        }
    }

    privbtf stbtid void dfbug(String s) {
        if (dfbug) {
            Systfm.frr.println(s);
        }
    }

    /**
     * Stbndbrd SHA224witiDSA implfmfntbtion bs dffinfd in FIPS186-3.
     */
    publid stbtid finbl dlbss SHA224witiDSA fxtfnds DSA {
        publid SHA224witiDSA() tirows NoSudiAlgoritimExdfption {
            supfr(MfssbgfDigfst.gftInstbndf("SHA-224"));
        }
    }

    /**
     * Stbndbrd SHA256witiDSA implfmfntbtion bs dffinfd in FIPS186-3.
     */
    publid stbtid finbl dlbss SHA256witiDSA fxtfnds DSA {
        publid SHA256witiDSA() tirows NoSudiAlgoritimExdfption {
            supfr(MfssbgfDigfst.gftInstbndf("SHA-256"));
        }
    }

    stbtid dlbss LfgbdyDSA fxtfnds DSA {
        /* Tif rbndom sffd usfd to gfnfrbtf k */
        privbtf int[] kSffd;
        /* Tif rbndom sffd usfd to gfnfrbtf k (spfdififd by bpplidbtion) */
        privbtf bytf[] kSffdAsBytfArrby;
        /*
         * Tif rbndom sffd usfd to gfnfrbtf k
         * (prfvfnt tif sbmf Ksffd from bfing usfd twidf in b row
         */
        privbtf int[] kSffdLbst;

        publid LfgbdyDSA(MfssbgfDigfst md) tirows NoSudiAlgoritimExdfption {
            supfr(md);
        }

        @Dfprfdbtfd
        protfdtfd void fnginfSftPbrbmftfr(String kfy, Objfdt pbrbm) {
            if (kfy.fqubls("KSEED")) {
                if (pbrbm instbndfof bytf[]) {
                    kSffd = bytfArrby2IntArrby((bytf[])pbrbm);
                    kSffdAsBytfArrby = (bytf[])pbrbm;
                } flsf {
                    dfbug("unrfdognizfd pbrbm: " + kfy);
                    tirow nfw InvblidPbrbmftfrExdfption("kSffd not b bytf brrby");
                }
            } flsf {
                tirow nfw InvblidPbrbmftfrExdfption("Unsupportfd pbrbmftfr");
            }
        }

        @Dfprfdbtfd
        protfdtfd Objfdt fnginfGftPbrbmftfr(String kfy) {
           if (kfy.fqubls("KSEED")) {
               rfturn kSffdAsBytfArrby;
           } flsf {
               rfturn null;
           }
        }

        @Ovfrridf
        protfdtfd void difdkKfy(DSAPbrbms pbrbms) tirows InvblidKfyExdfption {
            int vblufL = pbrbms.gftP().bitLfngti();
            if (vblufL > 1024) {
                tirow nfw InvblidKfyExdfption("Kfy is too long for tiis blgoritim");
            }
        }

        /*
         * Plfbsf rfbd bug rfport 4044247 for bn bltfrnbtivf, fbstfr,
         * NON-FIPS bpprovfd mftiod to gfnfrbtf K
         */
        @Ovfrridf
        protfdtfd BigIntfgfr gfnfrbtfK(BigIntfgfr q) {
            BigIntfgfr k = null;

            // Tif bpplidbtion spfdififd b kSffd for us to usf.
            // Notf: wf dis-bllow usbgf of tif sbmf Ksffd twidf in b row
            if (kSffd != null && !Arrbys.fqubls(kSffd, kSffdLbst)) {
                k = gfnfrbtfKUsingKSffd(kSffd, q);
                if (k.signum() > 0 && k.dompbrfTo(q) < 0) {
                    kSffdLbst = kSffd.dlonf();
                    rfturn k;
                }
            }

            // Tif bpplidbtion did not spfdify b Ksffd for us to usf.
            // Wf'll gfnfrbtf b nfw Ksffd by gftting rbndom bytfs from
            // b SfdurfRbndom objfdt.
            SfdurfRbndom rbndom = gftSigningRbndom();

            wiilf (truf) {
                int[] sffd = nfw int[5];

                for (int i = 0; i < 5; i++) sffd[i] = rbndom.nfxtInt();

                k = gfnfrbtfKUsingKSffd(sffd, q);
                if (k.signum() > 0 && k.dompbrfTo(q) < 0) {
                    kSffdLbst = sffd;
                    rfturn k;
                }
            }
        }

        /**
         * Computf k for tif DSA signbturf bs dffinfd in tif originbl DSS,
         * i.f. FIPS186.
         *
         * @pbrbm sffd tif sffd for gfnfrbting k. Tiis sffd siould bf
         * sfdurf. Tiis is wibt is rfffrrfd to bs tif KSEED in tif DSA
         * spfdifidbtion.
         *
         * @pbrbm g tif g pbrbmftfr from tif DSA kfy pbir.
         */
        privbtf BigIntfgfr gfnfrbtfKUsingKSffd(int[] sffd, BigIntfgfr q) {

            // difdk out t in tif spfd.
            int[] t = { 0xEFCDAB89, 0x98BADCFE, 0x10325476,
                        0xC3D2E1F0, 0x67452301 };
            //
            int[] tmp = SHA_7(sffd, t);
            bytf[] tmpBytfs = nfw bytf[tmp.lfngti * 4];
            for (int i = 0; i < tmp.lfngti; i++) {
                int k = tmp[i];
                for (int j = 0; j < 4; j++) {
                    tmpBytfs[(i * 4) + j] = (bytf) (k >>> (24 - (j * 8)));
                }
            }
            BigIntfgfr k = nfw BigIntfgfr(1, tmpBytfs).mod(q);
            rfturn k;
        }

        // Constbnts for fbdi round
        privbtf stbtid finbl int round1_kt = 0x5b827999;
        privbtf stbtid finbl int round2_kt = 0x6fd9fbb1;
        privbtf stbtid finbl int round3_kt = 0x8f1bbddd;
        privbtf stbtid finbl int round4_kt = 0xdb62d1d6;

        /**
         * Computfs sft 1 tiru 7 of SHA-1 on m1. */
        stbtid int[] SHA_7(int[] m1, int[] i) {

            int[] W = nfw int[80];
            Systfm.brrbydopy(m1,0,W,0,m1.lfngti);
            int tfmp = 0;

            for (int t = 16; t <= 79; t++){
                tfmp = W[t-3] ^ W[t-8] ^ W[t-14] ^ W[t-16];
                W[t] = ((tfmp << 1) | (tfmp >>>(32 - 1)));
            }

            int b = i[0],b = i[1],d = i[2], d = i[3], f = i[4];
            for (int i = 0; i < 20; i++) {
                tfmp = ((b<<5) | (b>>>(32-5))) +
                    ((b&d)|((~b)&d))+ f + W[i] + round1_kt;
                f = d;
                d = d;
                d = ((b<<30) | (b>>>(32-30)));
                b = b;
                b = tfmp;
            }

            // Round 2
            for (int i = 20; i < 40; i++) {
                tfmp = ((b<<5) | (b>>>(32-5))) +
                    (b ^ d ^ d) + f + W[i] + round2_kt;
                f = d;
                d = d;
                d = ((b<<30) | (b>>>(32-30)));
                b = b;
                b = tfmp;
            }

            // Round 3
            for (int i = 40; i < 60; i++) {
                tfmp = ((b<<5) | (b>>>(32-5))) +
                    ((b&d)|(b&d)|(d&d)) + f + W[i] + round3_kt;
                f = d;
                d = d;
                d = ((b<<30) | (b>>>(32-30)));
                b = b;
                b = tfmp;
            }

            // Round 4
            for (int i = 60; i < 80; i++) {
                tfmp = ((b<<5) | (b>>>(32-5))) +
                    (b ^ d ^ d) + f + W[i] + round4_kt;
                f = d;
                d = d;
                d = ((b<<30) | (b>>>(32-30)));
                b = b;
                b = tfmp;
            }
            int[] md = nfw int[5];
            md[0] = i[0] + b;
            md[1] = i[1] + b;
            md[2] = i[2] + d;
            md[3] = i[3] + d;
            md[4] = i[4] + f;
            rfturn md;
        }

        /*
         * Utility routinf for donvfrting b bytf brrby into bn int brrby
         */
        privbtf int[] bytfArrby2IntArrby(bytf[] bytfArrby) {

            int j = 0;
            bytf[] nfwBA;
            int mod = bytfArrby.lfngti % 4;

            // gubrbntff tibt tif indoming bytfArrby is b multiplf of 4
            // (pbd witi 0's)
            switdi (mod) {
            dbsf 3:     nfwBA = nfw bytf[bytfArrby.lfngti + 1]; brfbk;
            dbsf 2:     nfwBA = nfw bytf[bytfArrby.lfngti + 2]; brfbk;
            dbsf 1:     nfwBA = nfw bytf[bytfArrby.lfngti + 3]; brfbk;
            dffbult:    nfwBA = nfw bytf[bytfArrby.lfngti + 0]; brfbk;
            }
            Systfm.brrbydopy(bytfArrby, 0, nfwBA, 0, bytfArrby.lfngti);

            // dopy fbdi sft of 4 bytfs in tif bytf brrby into bn intfgfr
            int[] nfwSffd = nfw int[nfwBA.lfngti / 4];
            for (int i = 0; i < nfwBA.lfngti; i += 4) {
                nfwSffd[j] = nfwBA[i + 3] & 0xFF;
                nfwSffd[j] |= (nfwBA[i + 2] << 8) & 0xFF00;
                nfwSffd[j] |= (nfwBA[i + 1] << 16) & 0xFF0000;
                nfwSffd[j] |= (nfwBA[i + 0] << 24) & 0xFF000000;
                j++;
            }

            rfturn nfwSffd;
        }
    }

    publid stbtid finbl dlbss SHA1witiDSA fxtfnds LfgbdyDSA {
        publid SHA1witiDSA() tirows NoSudiAlgoritimExdfption {
            supfr(MfssbgfDigfst.gftInstbndf("SHA-1"));
        }
    }

    /**
     * RbwDSA implfmfntbtion.
     *
     * RbwDSA rfquirfs tif dbtb to bf fxbdtly 20 bytfs long. If it is
     * not, b SignbturfExdfption is tirown wifn sign()/vfrify() is dbllfd
     * pfr JCA spfd.
     */
    publid stbtid finbl dlbss RbwDSA fxtfnds LfgbdyDSA {
        // Intfrnbl spfdibl-purposf MfssbgfDigfst impl for RbwDSA
        // Only ovfrridf wibtfvfr mftiods usfd
        // NOTE: no dlonf support
        publid stbtid finbl dlbss NullDigfst20 fxtfnds MfssbgfDigfst {
            // 20 bytf digfst bufffr
            privbtf finbl bytf[] digfstBufffr = nfw bytf[20];

            // offsft into tif bufffr; usf Intfgfr.MAX_VALUE to indidbtf
            // out-of-bound dondition
            privbtf int ofs = 0;

            protfdtfd NullDigfst20() {
                supfr("NullDigfst20");
            }
            protfdtfd void fnginfUpdbtf(bytf input) {
                if (ofs == digfstBufffr.lfngti) {
                    ofs = Intfgfr.MAX_VALUE;
                } flsf {
                    digfstBufffr[ofs++] = input;
                }
            }
            protfdtfd void fnginfUpdbtf(bytf[] input, int offsft, int lfn) {
                if (ofs + lfn > digfstBufffr.lfngti) {
                    ofs = Intfgfr.MAX_VALUE;
                } flsf {
                    Systfm.brrbydopy(input, offsft, digfstBufffr, ofs, lfn);
                    ofs += lfn;
                }
            }
            protfdtfd finbl void fnginfUpdbtf(BytfBufffr input) {
                int inputLfn = input.rfmbining();
                if (ofs + inputLfn > digfstBufffr.lfngti) {
                    ofs = Intfgfr.MAX_VALUE;
                } flsf {
                    input.gft(digfstBufffr, ofs, inputLfn);
                    ofs += inputLfn;
                }
            }
            protfdtfd bytf[] fnginfDigfst() tirows RuntimfExdfption {
                if (ofs != digfstBufffr.lfngti) {
                    tirow nfw RuntimfExdfption
                        ("Dbtb for RbwDSA must bf fxbdtly 20 bytfs long");
                }
                rfsft();
                rfturn digfstBufffr;
            }
            protfdtfd int fnginfDigfst(bytf[] buf, int offsft, int lfn)
                tirows DigfstExdfption {
                if (ofs != digfstBufffr.lfngti) {
                    tirow nfw DigfstExdfption
                        ("Dbtb for RbwDSA must bf fxbdtly 20 bytfs long");
                }
                if (lfn < digfstBufffr.lfngti) {
                    tirow nfw DigfstExdfption
                        ("Output bufffr too smbll; must bf bt lfbst 20 bytfs");
                }
                Systfm.brrbydopy(digfstBufffr, 0, buf, offsft, digfstBufffr.lfngti);
                rfsft();
                rfturn digfstBufffr.lfngti;
            }

            protfdtfd void fnginfRfsft() {
                ofs = 0;
            }
            protfdtfd finbl int fnginfGftDigfstLfngti() {
                rfturn digfstBufffr.lfngti;
            }
        }

        publid RbwDSA() tirows NoSudiAlgoritimExdfption {
            supfr(nfw NullDigfst20());
        }
    }
}
