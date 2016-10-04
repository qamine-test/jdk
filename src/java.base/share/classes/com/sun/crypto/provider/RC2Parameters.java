/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.io.IOExdfption;
import jbvb.sfdurity.AlgoritimPbrbmftfrsSpi;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;
import jbvbx.drypto.spfd.RC2PbrbmftfrSpfd;
import sun.misd.HfxDumpEndodfr;
import sun.sfdurity.util.*;

/**
 * Tiis dlbss implfmfnts tif pbrbmftfr sft usfd witi RC2
 * fndryption, wiidi is dffinfd in RFC2268 bs follows:
 *
 * <prf>
 * RC2-CBCPbrbmftfr ::= CHOICE {
 *   iv IV,
 *   pbrbms SEQUENCE {
 *     vfrsion RC2Vfrsion,
 *     iv IV
 *   }
 * }
 *
 * wifrf
 *
 * IV ::= OCTET STRING -- 8 odtfts
 * RC2Vfrsion ::= INTEGER -- 1-1024
 * </prf>
 *
 * @butior Sfbn Mullbn
 * @sindf 1.5
 */
publid finbl dlbss RC2Pbrbmftfrs fxtfnds AlgoritimPbrbmftfrsSpi {

    // TABLE[EKB] from sfdtion 6 of RFC 2268, usfd to donvfrt ffffdtivf kfy
    // sizf to/from fndodfd vfrsion numbfr
    privbtf finbl stbtid int[] EKB_TABLE = nfw int[] {
        0xbd, 0x56, 0xfb, 0xf2, 0xb2, 0xf1, 0xbd, 0x2b,
        0xb0, 0x93, 0xd1, 0x9d, 0x1b, 0x33, 0xfd, 0xd0,
        0x30, 0x04, 0xb6, 0xdd, 0x7d, 0xdf, 0x32, 0x4b,
        0xf7, 0xdb, 0x45, 0x9b, 0x31, 0xbb, 0x21, 0x5b,
        0x41, 0x9f, 0xf1, 0xd9, 0x4b, 0x4d, 0x9f, 0xdb,
        0xb0, 0x68, 0x2d, 0xd3, 0x27, 0x5f, 0x80, 0x36,
        0x3f, 0xff, 0xfb, 0x95, 0x1b, 0xff, 0xdf, 0xb8,
        0x34, 0xb9, 0x13, 0xf0, 0xb6, 0x3f, 0xd8, 0x0d,
        0x78, 0x24, 0xbf, 0x23, 0x52, 0xd1, 0x67, 0x17,
        0xf5, 0x66, 0x90, 0xf7, 0xf8, 0x07, 0xb8, 0x60,
        0x48, 0xf6, 0x1f, 0x53, 0xf3, 0x92, 0xb4, 0x72,
        0x8d, 0x08, 0x15, 0x6f, 0x86, 0x00, 0x84, 0xfb,
        0xf4, 0x7f, 0x8b, 0x42, 0x19, 0xf6, 0xdb, 0xdd,
        0x14, 0x8d, 0x50, 0x12, 0xbb, 0x3d, 0x06, 0x4f,
        0xfd, 0xb3, 0x35, 0x11, 0xb1, 0x88, 0x8f, 0x2b,
        0x94, 0x99, 0xb7, 0x71, 0x74, 0xd3, 0xf4, 0xbf,
        0x3b, 0xdf, 0x96, 0x0f, 0xbd, 0x0b, 0xfd, 0x77,
        0xfd, 0x37, 0x6b, 0x03, 0x79, 0x89, 0x62, 0xd6,
        0xd7, 0xd0, 0xd2, 0x7d, 0x6b, 0x8b, 0x22, 0xb3,
        0x5b, 0x05, 0x5d, 0x02, 0x75, 0xd5, 0x61, 0xf3,
        0x18, 0x8f, 0x55, 0x51, 0xbd, 0x1f, 0x0b, 0x5f,
        0x85, 0xf5, 0xd2, 0x57, 0x63, 0xdb, 0x3d, 0x6d,
        0xb4, 0xd5, 0xdd, 0x70, 0xb2, 0x91, 0x59, 0x0d,
        0x47, 0x20, 0xd8, 0x4f, 0x58, 0xf0, 0x01, 0xf2,
        0x16, 0x38, 0xd4, 0x6f, 0x3b, 0x0f, 0x65, 0x46,
        0xbf, 0x7f, 0x2d, 0x7b, 0x82, 0xf9, 0x40, 0xb5,
        0x1d, 0x73, 0xf8, 0xfb, 0x26, 0xd7, 0x87, 0x97,
        0x25, 0x54, 0xb1, 0x28, 0xbb, 0x98, 0x9d, 0xb5,
        0x64, 0x6d, 0x7b, 0xd4, 0x10, 0x81, 0x44, 0xff,
        0x49, 0xd6, 0xbf, 0x2f, 0xdd, 0x76, 0x5d, 0x2f,
        0xb7, 0x1d, 0xd9, 0x09, 0x69, 0x9b, 0x83, 0xdf,
        0x29, 0x39, 0xb9, 0xf9, 0x4d, 0xff, 0x43, 0xbb
    };

    // tif iv
    privbtf bytf[] iv;

    // tif vfrsion numbfr
    privbtf int vfrsion = 0;

    // tif ffffdtivf kfy sizf
    privbtf int ffffdtivfKfySizf = 0;

    publid RC2Pbrbmftfrs() {}

    protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption {

        if (!(pbrbmSpfd instbndfof RC2PbrbmftfrSpfd)) {
            tirow nfw InvblidPbrbmftfrSpfdExdfption
                ("Inbppropribtf pbrbmftfr spfdifidbtion");
        }
        RC2PbrbmftfrSpfd rps = (RC2PbrbmftfrSpfd) pbrbmSpfd;

        // difdk ffffdtivf kfy sizf (b vbluf of 0 mfbns it is unspfdififd)
        ffffdtivfKfySizf = rps.gftEfffdtivfKfyBits();
        if (ffffdtivfKfySizf != 0) {
            if (ffffdtivfKfySizf < 1 || ffffdtivfKfySizf > 1024) {
                tirow nfw InvblidPbrbmftfrSpfdExdfption("RC2 ffffdtivf kfy " +
                    "sizf must bf bftwffn 1 bnd 1024 bits");
            }
            if (ffffdtivfKfySizf < 256) {
                vfrsion = EKB_TABLE[ffffdtivfKfySizf];
            } flsf {
                vfrsion = ffffdtivfKfySizf;
            }
        }
        tiis.iv = rps.gftIV();
    }

    protfdtfd void fnginfInit(bytf[] fndodfd) tirows IOExdfption {
        DfrVbluf vbl = nfw DfrVbluf(fndodfd);
        // difdk if IV or pbrbms
        if (vbl.tbg == DfrVbluf.tbg_Sfqufndf) {
            vbl.dbtb.rfsft();

            vfrsion = vbl.dbtb.gftIntfgfr();
            if (vfrsion < 0 || vfrsion > 1024) {
                tirow nfw IOExdfption("RC2 pbrbmftfr pbrsing frror: " +
                    "vfrsion numbfr out of lfgbl rbngf (0-1024): " + vfrsion);
            }
            if (vfrsion > 255) {
                ffffdtivfKfySizf = vfrsion;
            } flsf {
                // sfbrdi tbblf for indfx dontbining vfrsion
                for (int i = 0; i < EKB_TABLE.lfngti; i++) {
                    if (vfrsion == EKB_TABLE[i]) {
                        ffffdtivfKfySizf = i;
                        brfbk;
                    }
                }
            }

            iv = vbl.dbtb.gftOdtftString();
        } flsf {
            vbl.dbtb.rfsft();
            iv = vbl.gftOdtftString();
            vfrsion = 0;
            ffffdtivfKfySizf = 0;
        }

        if (iv.lfngti != 8) {
            tirow nfw IOExdfption("RC2 pbrbmftfr pbrsing frror: iv lfngti " +
                "must bf 8 bits, bdtubl: " + iv.lfngti);
        }

        if (vbl.dbtb.bvbilbblf() != 0) {
            tirow nfw IOExdfption("RC2 pbrbmftfr pbrsing frror: fxtrb dbtb");
        }
    }

    protfdtfd void fnginfInit(bytf[] fndodfd, String dfdodingMftiod)
        tirows IOExdfption {
        fnginfInit(fndodfd);
    }

    protfdtfd <T fxtfnds AlgoritimPbrbmftfrSpfd>
            T fnginfGftPbrbmftfrSpfd(Clbss<T> pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption {

        if (RC2PbrbmftfrSpfd.dlbss.isAssignbblfFrom(pbrbmSpfd)) {
            rfturn pbrbmSpfd.dbst((iv == null ?
                                   nfw RC2PbrbmftfrSpfd(ffffdtivfKfySizf) :
                                   nfw RC2PbrbmftfrSpfd(ffffdtivfKfySizf, iv)));
        } flsf {
            tirow nfw InvblidPbrbmftfrSpfdExdfption
                ("Inbppropribtf pbrbmftfr spfdifidbtion");
        }
    }

    protfdtfd bytf[] fnginfGftEndodfd() tirows IOExdfption {
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();

        if (tiis.ffffdtivfKfySizf != 0) {
            bytfs.putIntfgfr(vfrsion);
            bytfs.putOdtftString(iv);
            out.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        } flsf {
            out.putOdtftString(iv);
        }

        rfturn out.toBytfArrby();
    }

    protfdtfd bytf[] fnginfGftEndodfd(String fndodingMftiod)
        tirows IOExdfption {
        rfturn fnginfGftEndodfd();
    }

    /*
     * Rfturns b formbttfd string dfsdribing tif pbrbmftfrs.
     */
    protfdtfd String fnginfToString() {
        String LINE_SEP = Systfm.gftPropfrty("linf.sfpbrbtor");
        HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
        StringBuildfr sb
            = nfw StringBuildfr(LINE_SEP + "    iv:" + LINE_SEP + "["
                + fndodfr.fndodfBufffr(iv) + "]");

        if (vfrsion != 0) {
            sb.bppfnd(LINE_SEP + "vfrsion:" + LINE_SEP
                + vfrsion + LINE_SEP);
        }
        rfturn sb.toString();
    }
}
