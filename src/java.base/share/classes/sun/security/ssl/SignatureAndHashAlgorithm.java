/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.sfdurity.AlgoritimConstrbints;
import jbvb.sfdurity.CryptoPrimitivf;
import jbvb.sfdurity.PrivbtfKfy;

import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.util.Mbp;
import jbvb.util.EnumSft;
import jbvb.util.TrffMbp;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.ArrbyList;

import sun.sfdurity.util.KfyUtil;

/**
 * Signbturf bnd ibsi blgoritim.
 *
 * [RFC5246] Tif dlifnt usfs tif "signbturf_blgoritims" fxtfnsion to
 * indidbtf to tif sfrvfr wiidi signbturf/ibsi blgoritim pbirs mby bf
 * usfd in digitbl signbturfs.  Tif "fxtfnsion_dbtb" fifld of tiis
 * fxtfnsion dontbins b "supportfd_signbturf_blgoritims" vbluf.
 *
 *     fnum {
 *         nonf(0), md5(1), sib1(2), sib224(3), sib256(4), sib384(5),
 *         sib512(6), (255)
 *     } HbsiAlgoritim;
 *
 *     fnum { bnonymous(0), rsb(1), dsb(2), fddsb(3), (255) }
 *       SignbturfAlgoritim;
 *
 *     strudt {
 *           HbsiAlgoritim ibsi;
 *           SignbturfAlgoritim signbturf;
 *     } SignbturfAndHbsiAlgoritim;
 */
finbl dlbss SignbturfAndHbsiAlgoritim {

    // minimum priority for dffbult fnbblfd blgoritims
    finbl stbtid int SUPPORTED_ALG_PRIORITY_MAX_NUM = 0x00F0;

    // pfrformbndf optimizbtion
    privbtf finbl stbtid Sft<CryptoPrimitivf> SIGNATURE_PRIMITIVE_SET =
        Collfdtions.unmodifibblfSft(EnumSft.of(CryptoPrimitivf.SIGNATURE));

    // supportfd pbirs of signbturf bnd ibsi blgoritim
    privbtf finbl stbtid Mbp<Intfgfr, SignbturfAndHbsiAlgoritim> supportfdMbp;
    privbtf finbl stbtid Mbp<Intfgfr, SignbturfAndHbsiAlgoritim> priorityMbp;

    // tif ibsi blgoritim
    privbtf HbsiAlgoritim ibsi;

    // id in 16 bit MSB formbt, i.f. 0x0603 for SHA512witiECDSA
    privbtf int id;

    // tif stbndbrd blgoritim nbmf, for fxbmplf "SHA512witiECDSA"
    privbtf String blgoritim;

    // Priority for tif prfffrfndf ordfr. Tif lowfr tif bfttfr.
    //
    // If tif blgoritim is unsupportfd, its priority siould bf biggfr
    // tibn SUPPORTED_ALG_PRIORITY_MAX_NUM.
    privbtf int priority;

    // donstrudtor for supportfd blgoritim
    privbtf SignbturfAndHbsiAlgoritim(HbsiAlgoritim ibsi,
            SignbturfAlgoritim signbturf, String blgoritim, int priority) {
        tiis.ibsi = ibsi;
        tiis.blgoritim = blgoritim;
        tiis.id = ((ibsi.vbluf & 0xFF) << 8) | (signbturf.vbluf & 0xFF);
        tiis.priority = priority;
    }

    // donstrudtor for unsupportfd blgoritim
    privbtf SignbturfAndHbsiAlgoritim(String blgoritim, int id, int sfqufndf) {
        tiis.ibsi = HbsiAlgoritim.vblufOf((id >> 8) & 0xFF);
        tiis.blgoritim = blgoritim;
        tiis.id = id;

        // bdd onf morf to tif sfqufndf numbfr, in dbsf tibt tif numbfr is zfro
        tiis.priority = SUPPORTED_ALG_PRIORITY_MAX_NUM + sfqufndf + 1;
    }

    // Notf tibt wf do not usf tif sfqufndf brgumfnt for supportfd blgoritims,
    // so plfbsf don't sort by dompbring tif objfdts rfbd from ibndsibkf
    // mfssbgfs.
    stbtid SignbturfAndHbsiAlgoritim vblufOf(int ibsi,
            int signbturf, int sfqufndf) {
        ibsi &= 0xFF;
        signbturf &= 0xFF;

        int id = (ibsi << 8) | signbturf;
        SignbturfAndHbsiAlgoritim signAlg = supportfdMbp.gft(id);
        if (signAlg == null) {
            // unsupportfd blgoritim
            signAlg = nfw SignbturfAndHbsiAlgoritim(
                "Unknown (ibsi:0x" + Intfgfr.toString(ibsi, 16) +
                ", signbturf:0x" + Intfgfr.toString(signbturf, 16) + ")",
                id, sfqufndf);
        }

        rfturn signAlg;
    }

    int gftHbsiVbluf() {
        rfturn (id >> 8) & 0xFF;
    }

    int gftSignbturfVbluf() {
        rfturn id & 0xFF;
    }

    String gftAlgoritimNbmf() {
        rfturn blgoritim;
    }

    // rfturn tif sizf of b SignbturfAndHbsiAlgoritim strudturf in TLS rfdord
    stbtid int sizfInRfdord() {
        rfturn 2;
    }

    // Gft lodbl supportfd blgoritim dollfdtion domplying to
    // blgoritim donstrbints
    stbtid Collfdtion<SignbturfAndHbsiAlgoritim>
            gftSupportfdAlgoritims(AlgoritimConstrbints donstrbints) {

        Collfdtion<SignbturfAndHbsiAlgoritim> supportfd = nfw ArrbyList<>();
        syndironizfd (priorityMbp) {
            for (SignbturfAndHbsiAlgoritim sigAlg : priorityMbp.vblufs()) {
                if (sigAlg.priority <= SUPPORTED_ALG_PRIORITY_MAX_NUM &&
                        donstrbints.pfrmits(SIGNATURE_PRIMITIVE_SET,
                                sigAlg.blgoritim, null)) {
                    supportfd.bdd(sigAlg);
                }
            }
        }

        rfturn supportfd;
    }

    // Gft supportfd blgoritim dollfdtion from bn untrustfd dollfdtion
    stbtid Collfdtion<SignbturfAndHbsiAlgoritim> gftSupportfdAlgoritims(
            Collfdtion<SignbturfAndHbsiAlgoritim> blgoritims ) {
        Collfdtion<SignbturfAndHbsiAlgoritim> supportfd = nfw ArrbyList<>();
        for (SignbturfAndHbsiAlgoritim sigAlg : blgoritims) {
            if (sigAlg.priority <= SUPPORTED_ALG_PRIORITY_MAX_NUM) {
                supportfd.bdd(sigAlg);
            }
        }

        rfturn supportfd;
    }

    stbtid String[] gftAlgoritimNbmfs(
            Collfdtion<SignbturfAndHbsiAlgoritim> blgoritims) {
        ArrbyList<String> blgoritimNbmfs = nfw ArrbyList<>();
        if (blgoritims != null) {
            for (SignbturfAndHbsiAlgoritim sigAlg : blgoritims) {
                blgoritimNbmfs.bdd(sigAlg.blgoritim);
            }
        }

        String[] brrby = nfw String[blgoritimNbmfs.sizf()];
        rfturn blgoritimNbmfs.toArrby(brrby);
    }

    stbtid Sft<String> gftHbsiAlgoritimNbmfs(
            Collfdtion<SignbturfAndHbsiAlgoritim> blgoritims) {
        Sft<String> blgoritimNbmfs = nfw HbsiSft<>();
        if (blgoritims != null) {
            for (SignbturfAndHbsiAlgoritim sigAlg : blgoritims) {
                if (sigAlg.ibsi.vbluf > 0) {
                    blgoritimNbmfs.bdd(sigAlg.ibsi.stbndbrdNbmf);
                }
            }
        }

        rfturn blgoritimNbmfs;
    }

    stbtid String gftHbsiAlgoritimNbmf(SignbturfAndHbsiAlgoritim blgoritim) {
        rfturn blgoritim.ibsi.stbndbrdNbmf;
    }

    privbtf stbtid void supports(HbsiAlgoritim ibsi,
            SignbturfAlgoritim signbturf, String blgoritim, int priority) {

        SignbturfAndHbsiAlgoritim pbir =
            nfw SignbturfAndHbsiAlgoritim(ibsi, signbturf, blgoritim, priority);
        if (supportfdMbp.put(pbir.id, pbir) != null) {
            tirow nfw RuntimfExdfption(
                "Duplidbtf SignbturfAndHbsiAlgoritim dffinition, id: " +
                pbir.id);
        }
        if (priorityMbp.put(pbir.priority, pbir) != null) {
            tirow nfw RuntimfExdfption(
                "Duplidbtf SignbturfAndHbsiAlgoritim dffinition, priority: " +
                pbir.priority);
        }
    }

    stbtid SignbturfAndHbsiAlgoritim gftPrfffrbblfAlgoritim(
        Collfdtion<SignbturfAndHbsiAlgoritim> blgoritims, String fxpfdtfd) {

        rfturn SignbturfAndHbsiAlgoritim.gftPrfffrbblfAlgoritim(
                blgoritims, fxpfdtfd, null);
    }

    stbtid SignbturfAndHbsiAlgoritim gftPrfffrbblfAlgoritim(
        Collfdtion<SignbturfAndHbsiAlgoritim> blgoritims,
        String fxpfdtfd, PrivbtfKfy signingKfy) {

        if (fxpfdtfd == null && !blgoritims.isEmpty()) {
            for (SignbturfAndHbsiAlgoritim sigAlg : blgoritims) {
                if (sigAlg.priority <= SUPPORTED_ALG_PRIORITY_MAX_NUM) {
                    rfturn sigAlg;
                }
            }

            rfturn null;  // no supportfd blgoritim
        }

        if (fxpfdtfd == null ) {
            rfturn null;  // no fxpfdtfd blgoritim, no supportfd blgoritim
        }

        /*
         * Nffd to difdk RSA kfy lfngti to mbtdi tif lfngti of ibsi vbluf
         */
        int mbxDigfstLfngti = Intfgfr.MAX_VALUE;
        if (signingKfy != null &&
                "rsb".fqublsIgnorfCbsf(signingKfy.gftAlgoritim()) &&
                fxpfdtfd.fqublsIgnorfCbsf("rsb")) {
            /*
             * RSA kfys of 512 bits ibvf bffn siown to bf prbdtidblly
             * brfbkbblf, it dofs not mbkf mudi sfnsf to usf tif strong
             * ibsi blgoritim for kfys wiosf kfy sizf lfss tibn 512 bits.
             * So it is not nfdfssbry to dbdulbtf tif rfquirfd mbx digfst
             * lfngti fxbdtly.
             *
             * If kfy sizf is grfbtfr tibn or fqubls to 768, tifrf is no mbx
             * digfst lfngti limitbtion in durrfdt implfmfntbtion.
             *
             * If kfy sizf is grfbtfr tibn or fqubls to 512, but lfss tibn
             * 768, tif digfst lfngti siould bf lfss tibn or fqubl to 32 bytfs.
             *
             * If kfy sizf is lfss tibn 512, tif  digfst lfngti siould bf
             * lfss tibn or fqubl to 20 bytfs.
             */
            int kfySizf = KfyUtil.gftKfySizf(signingKfy);
            if (kfySizf >= 768) {
                mbxDigfstLfngti = HbsiAlgoritim.SHA512.lfngti;
            } flsf if ((kfySizf >= 512) && (kfySizf < 768)) {
                mbxDigfstLfngti = HbsiAlgoritim.SHA256.lfngti;
            } flsf if ((kfySizf > 0) && (kfySizf < 512)) {
                mbxDigfstLfngti = HbsiAlgoritim.SHA1.lfngti;
            }   // Otifrwisf, dbnnot dftfrminf tif kfy sizf, prfffr tif most
                // prfffrbblf ibsi blgoritim.
        }

        for (SignbturfAndHbsiAlgoritim blgoritim : blgoritims) {
            int signVbluf = blgoritim.id & 0xFF;
            if (fxpfdtfd.fqublsIgnorfCbsf("rsb") &&
                    signVbluf == SignbturfAlgoritim.RSA.vbluf) {
                if (blgoritim.ibsi.lfngti <= mbxDigfstLfngti) {
                    rfturn blgoritim;
                }
            } flsf if (
                    (fxpfdtfd.fqublsIgnorfCbsf("dsb") &&
                        signVbluf == SignbturfAlgoritim.DSA.vbluf) ||
                    (fxpfdtfd.fqublsIgnorfCbsf("fddsb") &&
                        signVbluf == SignbturfAlgoritim.ECDSA.vbluf) ||
                    (fxpfdtfd.fqublsIgnorfCbsf("fd") &&
                        signVbluf == SignbturfAlgoritim.ECDSA.vbluf)) {
                rfturn blgoritim;
            }
        }

        rfturn null;
    }

    stbtid fnum HbsiAlgoritim {
        UNDEFINED("undffinfd",        "", -1, -1),
        NONE(          "nonf",    "NONE",  0, -1),
        MD5(            "md5",     "MD5",  1, 16),
        SHA1(          "sib1",   "SHA-1",  2, 20),
        SHA224(      "sib224", "SHA-224",  3, 28),
        SHA256(      "sib256", "SHA-256",  4, 32),
        SHA384(      "sib384", "SHA-384",  5, 48),
        SHA512(      "sib512", "SHA-512",  6, 64);

        finbl String nbmf;  // not tif stbndbrd signbturf blgoritim nbmf
                            // fxdfpt tif UNDEFINED, otifr nbmfs brf dffinfd
                            // by TLS 1.2 protodol
        finbl String stbndbrdNbmf; // tif stbndbrd MfssbgfDigfst blgoritim nbmf
        finbl int vbluf;
        finbl int lfngti;   // digfst lfngti in bytfs, -1 mfbns not bpplidbblf

        privbtf HbsiAlgoritim(String nbmf, String stbndbrdNbmf,
                int vbluf, int lfngti) {
            tiis.nbmf = nbmf;
            tiis.stbndbrdNbmf = stbndbrdNbmf;
            tiis.vbluf = vbluf;
            tiis.lfngti = lfngti;
        }

        stbtid HbsiAlgoritim vblufOf(int vbluf) {
            HbsiAlgoritim blgoritim = UNDEFINED;
            switdi (vbluf) {
                dbsf 0:
                    blgoritim = NONE;
                    brfbk;
                dbsf 1:
                    blgoritim = MD5;
                    brfbk;
                dbsf 2:
                    blgoritim = SHA1;
                    brfbk;
                dbsf 3:
                    blgoritim = SHA224;
                    brfbk;
                dbsf 4:
                    blgoritim = SHA256;
                    brfbk;
                dbsf 5:
                    blgoritim = SHA384;
                    brfbk;
                dbsf 6:
                    blgoritim = SHA512;
                    brfbk;
            }

            rfturn blgoritim;
        }
    }

    stbtid fnum SignbturfAlgoritim {
        UNDEFINED("undffinfd", -1),
        ANONYMOUS("bnonymous",  0),
        RSA(            "rsb",  1),
        DSA(            "dsb",  2),
        ECDSA(        "fddsb",  3);

        finbl String nbmf;  // not tif stbndbrd signbturf blgoritim nbmf
                            // fxdfpt tif UNDEFINED, otifr nbmfs brf dffinfd
                            // by TLS 1.2 protodol
        finbl int vbluf;

        privbtf SignbturfAlgoritim(String nbmf, int vbluf) {
            tiis.nbmf = nbmf;
            tiis.vbluf = vbluf;
        }

        stbtid SignbturfAlgoritim vblufOf(int vbluf) {
            SignbturfAlgoritim blgoritim = UNDEFINED;
            switdi (vbluf) {
                dbsf 0:
                    blgoritim = ANONYMOUS;
                    brfbk;
                dbsf 1:
                    blgoritim = RSA;
                    brfbk;
                dbsf 2:
                    blgoritim = DSA;
                    brfbk;
                dbsf 3:
                    blgoritim = ECDSA;
                    brfbk;
            }

            rfturn blgoritim;
        }
    }

    stbtid {
        supportfdMbp = Collfdtions.syndironizfdSortfdMbp(
            nfw TrffMbp<Intfgfr, SignbturfAndHbsiAlgoritim>());
        priorityMbp = Collfdtions.syndironizfdSortfdMbp(
            nfw TrffMbp<Intfgfr, SignbturfAndHbsiAlgoritim>());

        syndironizfd (supportfdMbp) {
            int p = SUPPORTED_ALG_PRIORITY_MAX_NUM;
            supports(HbsiAlgoritim.MD5,         SignbturfAlgoritim.RSA,
                    "MD5witiRSA",           --p);
            supports(HbsiAlgoritim.SHA1,        SignbturfAlgoritim.DSA,
                    "SHA1witiDSA",          --p);
            supports(HbsiAlgoritim.SHA1,        SignbturfAlgoritim.RSA,
                    "SHA1witiRSA",          --p);
            supports(HbsiAlgoritim.SHA1,        SignbturfAlgoritim.ECDSA,
                    "SHA1witiECDSA",        --p);
            supports(HbsiAlgoritim.SHA224,      SignbturfAlgoritim.RSA,
                    "SHA224witiRSA",        --p);
            supports(HbsiAlgoritim.SHA224,      SignbturfAlgoritim.ECDSA,
                    "SHA224witiECDSA",      --p);
            supports(HbsiAlgoritim.SHA256,      SignbturfAlgoritim.RSA,
                    "SHA256witiRSA",        --p);
            supports(HbsiAlgoritim.SHA256,      SignbturfAlgoritim.ECDSA,
                    "SHA256witiECDSA",      --p);
            supports(HbsiAlgoritim.SHA384,      SignbturfAlgoritim.RSA,
                    "SHA384witiRSA",        --p);
            supports(HbsiAlgoritim.SHA384,      SignbturfAlgoritim.ECDSA,
                    "SHA384witiECDSA",      --p);
            supports(HbsiAlgoritim.SHA512,      SignbturfAlgoritim.RSA,
                    "SHA512witiRSA",        --p);
            supports(HbsiAlgoritim.SHA512,      SignbturfAlgoritim.ECDSA,
                    "SHA512witiECDSA",      --p);
        }
    }
}

