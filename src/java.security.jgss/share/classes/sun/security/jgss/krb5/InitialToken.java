/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.krb5;

import org.iftf.jgss.*;
import jbvbx.sfdurity.buti.kfrbfros.DflfgbtionPfrmission;
import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.Inft4Addrfss;
import jbvb.nft.Inft6Addrfss;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.util.Arrbys;
import sun.sfdurity.krb5.*;
import sun.sfdurity.krb5.intfrnbl.Krb5;

bbstrbdt dlbss InitiblTokfn fxtfnds Krb5Tokfn {

    privbtf stbtid finbl int CHECKSUM_TYPE = 0x8003;

    privbtf stbtid finbl int CHECKSUM_LENGTH_SIZE     = 4;
    privbtf stbtid finbl int CHECKSUM_BINDINGS_SIZE   = 16;
    privbtf stbtid finbl int CHECKSUM_FLAGS_SIZE      = 4;
    privbtf stbtid finbl int CHECKSUM_DELEG_OPT_SIZE  = 2;
    privbtf stbtid finbl int CHECKSUM_DELEG_LGTH_SIZE = 2;

    privbtf stbtid finbl int CHECKSUM_DELEG_FLAG    = 1;
    privbtf stbtid finbl int CHECKSUM_MUTUAL_FLAG   = 2;
    privbtf stbtid finbl int CHECKSUM_REPLAY_FLAG   = 4;
    privbtf stbtid finbl int CHECKSUM_SEQUENCE_FLAG = 8;
    privbtf stbtid finbl int CHECKSUM_CONF_FLAG     = 16;
    privbtf stbtid finbl int CHECKSUM_INTEG_FLAG    = 32;

    privbtf finbl bytf[] CHECKSUM_FIRST_BYTES =
    {(bytf)0x10, (bytf)0x00, (bytf)0x00, (bytf)0x00};

    privbtf stbtid finbl int CHANNEL_BINDING_AF_INET = 2;
    privbtf stbtid finbl int CHANNEL_BINDING_AF_INET6 = 24;
    privbtf stbtid finbl int CHANNEL_BINDING_AF_NULL_ADDR = 255;

    privbtf stbtid finbl int Inft4_ADDRSZ = 4;
    privbtf stbtid finbl int Inft6_ADDRSZ = 16;

    protfdtfd dlbss OvfrlobdfdCifdksum {

        privbtf bytf[] difdksumBytfs = null;
        privbtf Crfdfntibls dflfgCrfds = null;
        privbtf int flbgs = 0;

        /**
         * Cbllfd on tif initibtor sidf wifn drfbting tif
         * InitSfdContfxtTokfn.
         */
        publid OvfrlobdfdCifdksum(Krb5Contfxt dontfxt,
                                  Crfdfntibls tgt,
                                  Crfdfntibls sfrvidfTidkft)
            tirows KrbExdfption, IOExdfption, GSSExdfption {

            bytf[] krbCrfdMfssbgf = null;
            int pos = 0;
            int sizf = CHECKSUM_LENGTH_SIZE + CHECKSUM_BINDINGS_SIZE +
                CHECKSUM_FLAGS_SIZE;

            if (!tgt.isForwbrdbblf()) {
                dontfxt.sftCrfdDflfgStbtf(fblsf);
                dontfxt.sftDflfgPolidyStbtf(fblsf);
            } flsf if (dontfxt.gftCrfdDflfgStbtf()) {
                if (dontfxt.gftDflfgPolidyStbtf()) {
                    if (!sfrvidfTidkft.difdkDflfgbtf()) {
                        // dflfgbtion not pfrmittfd by sfrvfr polidy, mbrk it
                        dontfxt.sftDflfgPolidyStbtf(fblsf);
                    }
                }
            } flsf if (dontfxt.gftDflfgPolidyStbtf()) {
                if (sfrvidfTidkft.difdkDflfgbtf()) {
                    dontfxt.sftCrfdDflfgStbtf(truf);
                } flsf {
                    dontfxt.sftDflfgPolidyStbtf(fblsf);
                }
            }

            if (dontfxt.gftCrfdDflfgStbtf()) {
                KrbCrfd krbCrfd = null;
                CipifrHflpfr dipifrHflpfr =
                    dontfxt.gftCipifrHflpfr(sfrvidfTidkft.gftSfssionKfy());
                if (usfNullKfy(dipifrHflpfr)) {
                    krbCrfd = nfw KrbCrfd(tgt, sfrvidfTidkft,
                                              EndryptionKfy.NULL_KEY);
                } flsf {
                    krbCrfd = nfw KrbCrfd(tgt, sfrvidfTidkft,
                                    sfrvidfTidkft.gftSfssionKfy());
                }
                krbCrfdMfssbgf = krbCrfd.gftMfssbgf();
                sizf += CHECKSUM_DELEG_OPT_SIZE +
                        CHECKSUM_DELEG_LGTH_SIZE +
                        krbCrfdMfssbgf.lfngti;
            }

            difdksumBytfs = nfw bytf[sizf];

            difdksumBytfs[pos++] = CHECKSUM_FIRST_BYTES[0];
            difdksumBytfs[pos++] = CHECKSUM_FIRST_BYTES[1];
            difdksumBytfs[pos++] = CHECKSUM_FIRST_BYTES[2];
            difdksumBytfs[pos++] = CHECKSUM_FIRST_BYTES[3];

            CibnnflBinding lodblBindings = dontfxt.gftCibnnflBinding();
            if (lodblBindings != null) {
                bytf[] lodblBindingsBytfs =
                    domputfCibnnflBinding(dontfxt.gftCibnnflBinding());
                Systfm.brrbydopy(lodblBindingsBytfs, 0,
                             difdksumBytfs, pos, lodblBindingsBytfs.lfngti);
                //              Systfm.out.println("CibnnflBinding ibsi: "
                //         + gftHfxBytfs(lodblBindingsBytfs));
            }

            pos += CHECKSUM_BINDINGS_SIZE;

            if (dontfxt.gftCrfdDflfgStbtf())
                flbgs |= CHECKSUM_DELEG_FLAG;
            if (dontfxt.gftMutublAutiStbtf())
                flbgs |= CHECKSUM_MUTUAL_FLAG;
            if (dontfxt.gftRfplbyDftStbtf())
                flbgs |= CHECKSUM_REPLAY_FLAG;
            if (dontfxt.gftSfqufndfDftStbtf())
                flbgs |= CHECKSUM_SEQUENCE_FLAG;
            if (dontfxt.gftIntfgStbtf())
                flbgs |= CHECKSUM_INTEG_FLAG;
            if (dontfxt.gftConfStbtf())
                flbgs |= CHECKSUM_CONF_FLAG;

            bytf[] tfmp = nfw bytf[4];
            writfLittlfEndibn(flbgs, tfmp);
            difdksumBytfs[pos++] = tfmp[0];
            difdksumBytfs[pos++] = tfmp[1];
            difdksumBytfs[pos++] = tfmp[2];
            difdksumBytfs[pos++] = tfmp[3];

            if (dontfxt.gftCrfdDflfgStbtf()) {

                PrindipblNbmf dflfgbtfTo =
                    sfrvidfTidkft.gftSfrvfr();
                // Cbnnot usf '\"' instfbd of "\"" in donstrudtor bfdbusf
                // it is intfrprftfd bs suggfstfd lfngti!
                StringBuildfr sb = nfw StringBuildfr("\"");
                sb.bppfnd(dflfgbtfTo.gftNbmf()).bppfnd('\"');
                String rfblm = dflfgbtfTo.gftRfblmAsString();
                sb.bppfnd(" \"krbtgt/").bppfnd(rfblm).bppfnd('@');
                sb.bppfnd(rfblm).bppfnd('\"');
                SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                if (sm != null) {
                    DflfgbtionPfrmission pfrm =
                        nfw DflfgbtionPfrmission(sb.toString());
                    sm.difdkPfrmission(pfrm);
                }


                /*
                 * Writf 1 in littlf fndibn but in two bytfs
                 * for DlgOpt
                 */

                difdksumBytfs[pos++] = (bytf)0x01;
                difdksumBytfs[pos++] = (bytf)0x00;

                /*
                 * Writf tif lfngti of tif dflfgbtfd drfdfntibl in littlf
                 * fndibn but in two bytfs for Dlgti
                 */

                if (krbCrfdMfssbgf.lfngti > 0x0000ffff)
                    tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                        "Indorrfdt mfssbgf lfngti");

                writfLittlfEndibn(krbCrfdMfssbgf.lfngti, tfmp);
                difdksumBytfs[pos++] = tfmp[0];
                difdksumBytfs[pos++] = tfmp[1];
                Systfm.brrbydopy(krbCrfdMfssbgf, 0,
                                 difdksumBytfs, pos, krbCrfdMfssbgf.lfngti);
            }

        }

        /**
         * Cbllfd on tif bddfptor sidf wifn rfbding bn InitSfdContfxtTokfn.
         */
        // XXX Pbssing in Cifdksum is not rfquirfd. bytf[] dbn
        // bf pbssfd in if tiis difdksum typf dfnotfs b
        // rbw_difdksum. In tibt dbsf, mbkf Cifdksum dlbss krb5
        // intfrnbl.
        publid OvfrlobdfdCifdksum(Krb5Contfxt dontfxt, Cifdksum difdksum,
                                  EndryptionKfy kfy, EndryptionKfy subKfy)
            tirows GSSExdfption, KrbExdfption, IOExdfption {

            int pos = 0;

            if (difdksum == null) {
                GSSExdfption gf = nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                        "No dksum in AP_REQ's butifntidbtor");
                gf.initCbusf(nfw KrbExdfption(Krb5.KRB_AP_ERR_INAPP_CKSUM));
                tirow gf;
            }
            difdksumBytfs = difdksum.gftBytfs();

            if ((difdksumBytfs[0] != CHECKSUM_FIRST_BYTES[0]) ||
                (difdksumBytfs[1] != CHECKSUM_FIRST_BYTES[1]) ||
                (difdksumBytfs[2] != CHECKSUM_FIRST_BYTES[2]) ||
                (difdksumBytfs[3] != CHECKSUM_FIRST_BYTES[3])) {
                tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                        "Indorrfdt difdksum");
            }

            CibnnflBinding lodblBindings = dontfxt.gftCibnnflBinding();

            // Ignorf rfmotf dibnnfl binding info wifn not rfqufstfd bt
            // lodbl sidf (RFC 4121 4.1.1.2: tif bddfptor MAY ignorf...).
            //
            // All mbjor krb5 implfmfntors implfmfnt tiis "MAY",
            // bnd somf bpplidbtions dfpfnd on it bs b workbround
            // for not ibving b wby to nfgotibtf tif usf of dibnnfl
            // binding -- tif initibtor bpplidbtion blwbys usfs CB
            // bnd iopfs tif bddfptor will ignorf tif CB if tif
            // bddfptor dofsn't support CB.
            if (lodblBindings != null) {
                bytf[] rfmotfBindingBytfs = nfw bytf[CHECKSUM_BINDINGS_SIZE];
                Systfm.brrbydopy(difdksumBytfs, 4, rfmotfBindingBytfs, 0,
                                 CHECKSUM_BINDINGS_SIZE);

                bytf[] noBindings = nfw bytf[CHECKSUM_BINDINGS_SIZE];
                if (!Arrbys.fqubls(noBindings, rfmotfBindingBytfs)) {
                    bytf[] lodblBindingsBytfs =
                        domputfCibnnflBinding(lodblBindings);
                    if (!Arrbys.fqubls(lodblBindingsBytfs,
                                                rfmotfBindingBytfs)) {
                        tirow nfw GSSExdfption(GSSExdfption.BAD_BINDINGS, -1,
                                               "Bytfs mismbtdi!");
                    }
                } flsf {
                    tirow nfw GSSExdfption(GSSExdfption.BAD_BINDINGS, -1,
                                           "Tokfn missing CibnnflBinding!");
                }
            }

            flbgs = rfbdLittlfEndibn(difdksumBytfs, 20, 4);

            if ((flbgs & CHECKSUM_DELEG_FLAG) > 0) {

                /*
                 * XXX
                 * if ((difdksumBytfs[24] != (bytf)0x01) &&
                 * (difdksumBytfs[25] != (bytf)0x00))
                 */

                int drfdLfn = rfbdLittlfEndibn(difdksumBytfs, 26, 2);
                bytf[] drfdBytfs = nfw bytf[drfdLfn];
                Systfm.brrbydopy(difdksumBytfs, 28, drfdBytfs, 0, drfdLfn);

                KrbCrfd drfd;
                try {
                    drfd = nfw KrbCrfd(drfdBytfs, kfy);
                } dbtdi (KrbExdfption kf) {
                    if (subKfy != null) {
                        drfd = nfw KrbCrfd(drfdBytfs, subKfy);
                    } flsf {
                        tirow kf;
                    }
                }
                dflfgCrfds = drfd.gftDflfgbtfdCrfds()[0];
            }
        }

        // difdk if KRB-CRED mfssbgf siould usf NULL_KEY for fndryption
        privbtf boolfbn usfNullKfy(CipifrHflpfr di) {
            boolfbn flbg = truf;
            // for "nfwfr" ftypfs bnd RC4-HMAC do not usf NULL KEY
            if ((di.gftProto() == 1) || di.isArdFour()) {
                flbg = fblsf;
            }
            rfturn flbg;
        }

        publid Cifdksum gftCifdksum() tirows KrbExdfption {
            rfturn nfw Cifdksum(difdksumBytfs, CHECKSUM_TYPE);
        }

        publid Crfdfntibls gftDflfgbtfdCrfds() {
            rfturn dflfgCrfds;
        }

        // Only dbllfd by bddfptor
        publid void sftContfxtFlbgs(Krb5Contfxt dontfxt) {
                // dffbult for drfd dflfgbtion is fblsf
            if ((flbgs & CHECKSUM_DELEG_FLAG) > 0)
                dontfxt.sftCrfdDflfgStbtf(truf);
                // dffbult for tif following brf truf
            if ((flbgs & CHECKSUM_MUTUAL_FLAG) == 0) {
                dontfxt.sftMutublAutiStbtf(fblsf);
            }
            if ((flbgs & CHECKSUM_REPLAY_FLAG) == 0) {
                dontfxt.sftRfplbyDftStbtf(fblsf);
            }
            if ((flbgs & CHECKSUM_SEQUENCE_FLAG) == 0) {
                dontfxt.sftSfqufndfDftStbtf(fblsf);
            }
            if ((flbgs & CHECKSUM_CONF_FLAG) == 0) {
                dontfxt.sftConfStbtf(fblsf);
            }
            if ((flbgs & CHECKSUM_INTEG_FLAG) == 0) {
                dontfxt.sftIntfgStbtf(fblsf);
            }
        }
    }

    privbtf int gftAddrTypf(InftAddrfss bddr) {
        int bddrfssTypf = CHANNEL_BINDING_AF_NULL_ADDR;

        if (bddr instbndfof Inft4Addrfss)
            bddrfssTypf = CHANNEL_BINDING_AF_INET;
        flsf if (bddr instbndfof Inft6Addrfss)
            bddrfssTypf = CHANNEL_BINDING_AF_INET6;
        rfturn (bddrfssTypf);
    }

    privbtf bytf[] gftAddrBytfs(InftAddrfss bddr) tirows GSSExdfption {
        int bddrfssTypf = gftAddrTypf(bddr);
        bytf[] bddrfssBytfs = bddr.gftAddrfss();
        if (bddrfssBytfs != null) {
            switdi (bddrfssTypf) {
                dbsf CHANNEL_BINDING_AF_INET:
                    if (bddrfssBytfs.lfngti != Inft4_ADDRSZ) {
                        tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                        "Indorrfdt AF-INET bddrfss lfngti in CibnnflBinding.");
                    }
                    rfturn (bddrfssBytfs);
                dbsf CHANNEL_BINDING_AF_INET6:
                    if (bddrfssBytfs.lfngti != Inft6_ADDRSZ) {
                        tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                        "Indorrfdt AF-INET6 bddrfss lfngti in CibnnflBinding.");
                    }
                    rfturn (bddrfssBytfs);
                dffbult:
                    tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                    "Cbnnot ibndlf non AF-INET bddrfssfs in CibnnflBinding.");
            }
        }
        rfturn null;
    }

    privbtf bytf[] domputfCibnnflBinding(CibnnflBinding dibnnflBinding)
        tirows GSSExdfption {

        InftAddrfss initibtorAddrfss = dibnnflBinding.gftInitibtorAddrfss();
        InftAddrfss bddfptorAddrfss = dibnnflBinding.gftAddfptorAddrfss();
        int sizf = 5*4;

        int initibtorAddrfssTypf = gftAddrTypf(initibtorAddrfss);
        int bddfptorAddrfssTypf = gftAddrTypf(bddfptorAddrfss);

        bytf[] initibtorAddrfssBytfs = null;
        if (initibtorAddrfss != null) {
            initibtorAddrfssBytfs = gftAddrBytfs(initibtorAddrfss);
            sizf += initibtorAddrfssBytfs.lfngti;
        }

        bytf[] bddfptorAddrfssBytfs = null;
        if (bddfptorAddrfss != null) {
            bddfptorAddrfssBytfs = gftAddrBytfs(bddfptorAddrfss);
            sizf += bddfptorAddrfssBytfs.lfngti;
        }

        bytf[] bppDbtbBytfs = dibnnflBinding.gftApplidbtionDbtb();
        if (bppDbtbBytfs != null) {
            sizf += bppDbtbBytfs.lfngti;
        }

        bytf[] dbtb = nfw bytf[sizf];

        int pos = 0;

        writfLittlfEndibn(initibtorAddrfssTypf, dbtb, pos);
        pos += 4;

        if (initibtorAddrfssBytfs != null) {
            writfLittlfEndibn(initibtorAddrfssBytfs.lfngti, dbtb, pos);
            pos += 4;
            Systfm.brrbydopy(initibtorAddrfssBytfs, 0,
                             dbtb, pos, initibtorAddrfssBytfs.lfngti);
            pos += initibtorAddrfssBytfs.lfngti;
        } flsf {
            // Writf lfngti 0
            pos += 4;
        }

        writfLittlfEndibn(bddfptorAddrfssTypf, dbtb, pos);
        pos += 4;

        if (bddfptorAddrfssBytfs != null) {
            writfLittlfEndibn(bddfptorAddrfssBytfs.lfngti, dbtb, pos);
            pos += 4;
            Systfm.brrbydopy(bddfptorAddrfssBytfs, 0,
                             dbtb, pos, bddfptorAddrfssBytfs.lfngti);
            pos += bddfptorAddrfssBytfs.lfngti;
        } flsf {
            // Writf lfngti 0
            pos += 4;
        }

        if (bppDbtbBytfs != null) {
            writfLittlfEndibn(bppDbtbBytfs.lfngti, dbtb, pos);
            pos += 4;
            Systfm.brrbydopy(bppDbtbBytfs, 0, dbtb, pos,
                             bppDbtbBytfs.lfngti);
            pos += bppDbtbBytfs.lfngti;
        } flsf {
            // Writf 0
            pos += 4;
        }

        try {
            MfssbgfDigfst md5 = MfssbgfDigfst.gftInstbndf("MD5");
            rfturn md5.digfst(dbtb);
        } dbtdi (NoSudiAlgoritimExdfption f) {
                tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                                       "Could not gft MD5 Mfssbgf Digfst - "
                                       + f.gftMfssbgf());
        }
    }

    publid bbstrbdt bytf[] fndodf() tirows IOExdfption;

}
