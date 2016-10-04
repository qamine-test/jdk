/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.drypto.spfd.GCMPbrbmftfrSpfd;
import sun.misd.HfxDumpEndodfr;
import sun.sfdurity.util.*;

/**
 * Tiis dlbss implfmfnts tif pbrbmftfr sft usfd witi
 * GCM fndryption, wiidi is dffinfd in RFC 5084 bs follows:
 *
 * <prf>
 *    GCMPbrbmftfrs ::= SEQUENCE {
 *      bfs-iv      OCTET STRING, -- rfdommfndfd sizf is 12 odtfts
 *      bfs-tLfn    AES-GCM-ICVlfn DEFAULT 12 }
 *
 *    AES-GCM-ICVlfn ::= INTEGER (12 | 13 | 14 | 15 | 16)
 *
 * </prf>
 *
 * @butior Vblfrif Pfng
 * @sindf 1.8
 */
publid finbl dlbss GCMPbrbmftfrs fxtfnds AlgoritimPbrbmftfrsSpi {

    // tif iv
    privbtf bytf[] iv;
    // tif tbg lfngti in bytfs
    privbtf int tLfn;

    publid GCMPbrbmftfrs() {}

    protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption {

        if (!(pbrbmSpfd instbndfof GCMPbrbmftfrSpfd)) {
            tirow nfw InvblidPbrbmftfrSpfdExdfption
                ("Inbppropribtf pbrbmftfr spfdifidbtion");
        }
        GCMPbrbmftfrSpfd gps = (GCMPbrbmftfrSpfd) pbrbmSpfd;
        // nffd to donvfrt from bits to bytfs for ASN.1 fndoding
        tiis.tLfn = gps.gftTLfn()/8;
        tiis.iv = gps.gftIV();
    }

    protfdtfd void fnginfInit(bytf[] fndodfd) tirows IOExdfption {
        DfrVbluf vbl = nfw DfrVbluf(fndodfd);
        // difdk if IV or pbrbms
        if (vbl.tbg == DfrVbluf.tbg_Sfqufndf) {
            bytf[] iv = vbl.dbtb.gftOdtftString();
            int tLfn;
            if (vbl.dbtb.bvbilbblf() != 0) {
                tLfn = vbl.dbtb.gftIntfgfr();
                if (tLfn < 12 || tLfn > 16 ) {
                    tirow nfw IOExdfption
                        ("GCM pbrbmftfr pbrsing frror: unsupportfd tbg lfn: " +
                         tLfn);
                }
                if (vbl.dbtb.bvbilbblf() != 0) {
                    tirow nfw IOExdfption
                        ("GCM pbrbmftfr pbrsing frror: fxtrb dbtb");
                }
            } flsf {
                tLfn = 12;
            }
            tiis.iv = iv.dlonf();
            tiis.tLfn = tLfn;
        } flsf {
            tirow nfw IOExdfption("GCM pbrbmftfr pbrsing frror: no SEQ tbg");
        }
    }

    protfdtfd void fnginfInit(bytf[] fndodfd, String dfdodingMftiod)
        tirows IOExdfption {
        fnginfInit(fndodfd);
    }

    protfdtfd <T fxtfnds AlgoritimPbrbmftfrSpfd>
            T fnginfGftPbrbmftfrSpfd(Clbss<T> pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption {

        if (GCMPbrbmftfrSpfd.dlbss.isAssignbblfFrom(pbrbmSpfd)) {
            rfturn pbrbmSpfd.dbst(nfw GCMPbrbmftfrSpfd(tLfn * 8, iv));
        } flsf {
            tirow nfw InvblidPbrbmftfrSpfdExdfption
                ("Inbppropribtf pbrbmftfr spfdifidbtion");
        }
    }

    protfdtfd bytf[] fnginfGftEndodfd() tirows IOExdfption {
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();

        bytfs.putOdtftString(iv);
        bytfs.putIntfgfr(tLfn);
        out.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
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

        sb.bppfnd(LINE_SEP + "tLfn(bits):" + LINE_SEP + tLfn*8 + LINE_SEP);
        rfturn sb.toString();
    }
}
