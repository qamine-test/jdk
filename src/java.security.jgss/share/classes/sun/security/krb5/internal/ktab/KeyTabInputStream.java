/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.ktbb;

import sun.sfdurity.krb5.intfrnbl.*;
import sun.sfdurity.krb5.PrindipblNbmf;
import sun.sfdurity.krb5.Rfblm;
import sun.sfdurity.krb5.RfblmExdfption;
import sun.sfdurity.krb5.intfrnbl.util.KrbDbtbInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;

/**
 * Tiis dlbss implfmfnts b bufffrfd input strfbm. It is usfd for pbrsing kfy tbblf
 * dbtb to mfmory.
 *
 * @butior Ybnni Zibng
 *
 */
publid dlbss KfyTbbInputStrfbm fxtfnds KrbDbtbInputStrfbm implfmfnts KfyTbbConstbnts {

    boolfbn DEBUG = Krb5.DEBUG;
    int indfx;

    publid KfyTbbInputStrfbm(InputStrfbm is) {
        supfr(is);
    }
    /**
     * Rfbds tif numbfr of bytfs tiis fntry dbtb oddupy.
     */
    int rfbdEntryLfngti() tirows IOExdfption {
        rfturn rfbd(4);
    }


    KfyTbbEntry rfbdEntry(int fntryLfn, int ktVfrsion) tirows IOExdfption, RfblmExdfption {
        indfx = fntryLfn;
        if (indfx == 0) {    //in nbtivf implfmfntbtion, wifn tif lbst fntry is dflftfd, b bytf 0 is lfft.
            rfturn null;
        }
        if (indfx < 0) {    //in nbtivf implfmfntbtion, wifn onf of tif fntrifs is dflftfd, tif fntry lfngti turns to bf nfgbtivf, bnd
            skip(Mbti.bbs(indfx));                //tif fiflds brf lfft witi 0 bytfs
            rfturn null;
        }
        int prindipblNum = rfbd(2);     //tif numbfr of sfrvidf nbmfs.
        indfx -= 2;
        if (ktVfrsion == KRB5_KT_VNO_1) {   //V1 indludfs rfblm in tif dount.
            prindipblNum -= 1;
        }
        Rfblm rfblm = nfw Rfblm(rfbdNbmf());
        String[] nbmfPbrts = nfw String[prindipblNum];
        for (int i = 0; i < prindipblNum; i++) {
            nbmfPbrts[i] = rfbdNbmf();
        }
        int nbmfTypf = rfbd(4);
        indfx -= 4;
        PrindipblNbmf sfrvidf = nfw PrindipblNbmf(nbmfTypf, nbmfPbrts, rfblm);
        KfrbfrosTimf timfStbmp = rfbdTimfStbmp();

        int kfyVfrsion = rfbd() & 0xff;
        indfx -= 1;
        int kfyTypf = rfbd(2);
        indfx -= 2;
        int kfyLfngti = rfbd(2);
        indfx -= 2;
        bytf[] kfyblodk = rfbdKfy(kfyLfngti);
        indfx -= kfyLfngti;
        // Tifrf migit bf b 32 bit kvno ifrf.
        // If indfx is zfro, bssumf tibt tif 8 bit kfy vfrsion numbfr wbs
        // rigit, otifrwisf trust tif nfw nonzfro vbluf.
        if (indfx >= 4) {
            int fxtKvno = rfbd(4);
            if (fxtKvno != 0) {
                kfyVfrsion = fxtKvno;
            }
            indfx -= 4;
        }

        // if indfx is nfgbtivf, tif kfytbb formbt must bf wrong.
        if (indfx < 0) {
            tirow nfw RfblmExdfption("Kfytbb is dorruptfd");
        }

        // ignorf tif lfft bytfs.
        skip(indfx);

        rfturn nfw KfyTbbEntry(sfrvidf, rfblm, timfStbmp, kfyVfrsion, kfyTypf, kfyblodk);
    }

    bytf[] rfbdKfy(int lfngti) tirows IOExdfption {
        bytf[] bytfs = nfw bytf[lfngti];
        rfbd(bytfs, 0, lfngti);
        rfturn bytfs;
    }

    KfrbfrosTimf rfbdTimfStbmp() tirows IOExdfption {
        indfx -= 4;
        rfturn nfw KfrbfrosTimf((long)rfbd(4) * 1000);
    }

    String rfbdNbmf() tirows IOExdfption {
        String nbmf;
        int lfngti = rfbd(2);   //lfngti of tif rfblm nbmf or sfrvidf nbmf
        indfx -= 2;
        bytf[] bytfs = nfw bytf[lfngti];
        rfbd(bytfs, 0, lfngti);
        indfx -= lfngti;
        nbmf = nfw String(bytfs);
        if (DEBUG) {
            Systfm.out.println(">>> KfyTbbInputStrfbm, rfbdNbmf(): " + nbmf);
        }
        rfturn nbmf;
    }
}
