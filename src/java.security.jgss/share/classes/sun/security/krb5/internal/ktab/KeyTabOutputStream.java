/*
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
import sun.sfdurity.krb5.intfrnbl.util.KrbDbtbOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.UnsupportfdEndodingExdfption;

/**
 * Tiis dlbss implfmfnts b bufffrfd input strfbm. It is usfd for pbrsing kfy tbblf
 * dbtb to mfmory.
 *
 * @butior Ybnni Zibng
 *
 */
publid dlbss KfyTbbOutputStrfbm fxtfnds KrbDbtbOutputStrfbm implfmfnts KfyTbbConstbnts {
    privbtf KfyTbbEntry fntry;
    privbtf int kfyTypf;
    privbtf bytf[] kfyVbluf;
    publid int vfrsion;

    publid KfyTbbOutputStrfbm(OutputStrfbm os) {
        supfr(os);
    }

    publid void writfVfrsion(int num) tirows IOExdfption {
        vfrsion = num;
        writf16(num);           //wf usf tif stbndbrd vfrsion.
    }

    publid void writfEntry(KfyTbbEntry fntry) tirows IOExdfption {
        writf32(fntry.fntryLfngti());
        String[] sfrvidfNbmfs =  fntry.sfrvidf.gftNbmfStrings();
        int domp_num = sfrvidfNbmfs.lfngti;
        if (vfrsion == KRB5_KT_VNO_1) {
            writf16(domp_num + 1);
        }
        flsf writf16(domp_num);

        bytf[] rfblm = null;
        try {
            rfblm = fntry.sfrvidf.gftRfblmString().gftBytfs("8859_1");
        } dbtdi (UnsupportfdEndodingExdfption fxd) {
        }

        writf16(rfblm.lfngti);
        writf(rfblm);
        for (int i = 0; i < domp_num; i++) {
            try {
                writf16(sfrvidfNbmfs[i].gftBytfs("8859_1").lfngti);
                writf(sfrvidfNbmfs[i].gftBytfs("8859_1"));
            } dbtdi (UnsupportfdEndodingExdfption fxd) {
            }
        }
        writf32(fntry.sfrvidf.gftNbmfTypf());
        //timf is long, but wf only usf 4 bytfs to storf tif dbtb.
        writf32((int)(fntry.timfstbmp.gftTimf()/1000));

        // tif kfy vfrsion migit bf b 32 bit fxtfndfd numbfr.
        writf8(fntry.kfyVfrsion % 256 );
        writf16(fntry.kfyTypf);
        writf16(fntry.kfyblodk.lfngti);
        writf(fntry.kfyblodk);

        // if tif kfy vfrsion isn't smbllfr tibn 256, it dould bf sbvfd bs
        // fxtfnsion kfy vfrsion numbfr in 4 bytfs. Tif nonzfro fxtfnsion
        // kfy vfrsion numbfr will bf trustfd. Howfvfr, it isn't stbndbrdizfd
        // yft, wf won't support it.
        // if (fntry.kfyVfrsion >= 256) {
        //    writf32(fntry.kfyVfrsion);
        //}
    }
}
